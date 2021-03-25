package rs.ac.singidunum.org.apiusers.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import rs.ac.singidunum.org.apiusers.dto.response.AdministratorDto;
import rs.ac.singidunum.org.apiusers.dto.response.AdministratorResponseDto;
import rs.ac.singidunum.org.apiusers.model.Administrator;
import rs.ac.singidunum.org.apiusers.service.AdministratorService;

import java.util.Optional;

@Aspect
@Component
@Order(0)
public class ControllerAop {

    AdministratorService service;

    @Autowired
    public void setService(AdministratorService service) {
        this.service = service;
    }

    public AdministratorResponseDto createDto(Administrator a) {
        return new AdministratorResponseDto(a.getId(),a.getUsername(),a.getEmail(),a.isDeleted());
    }

    @Around("execution(* rs.ac.singidunum.org.apiusers.controller.AdministratorController.getAll())")
    public ResponseEntity<Page<AdministratorResponseDto>> all(ProceedingJoinPoint joinPoint) {
        try {
            ResponseEntity<Page<Administrator>> responseEntity = (ResponseEntity<Page<Administrator>>)joinPoint.proceed(joinPoint.getArgs());
            return new ResponseEntity<Page<AdministratorResponseDto>>(responseEntity.getBody().map(usr -> this.createDto(usr)),HttpStatus.OK);
        } catch (Exception ignored){
        } catch (Throwable ignored) {
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    // post ne radi iz nekog razloga
    @Around("execution(* rs.ac.singidunum.org.apiusers.controller.AdministratorController.*(Long))" +
            "&& execution(* rs.ac.singidunum.org.apiusers.controller.AdministratorController.getOneByUsername(String)) +" +
            "&& execution(* rs.ac.singidunum.org.apiusers.controller.AdministratorController.saveOne(Object))" +
            "&& execution(* rs.ac.singidunum.org.apiusers.controller.AdministratorController.updateOne(Long, Object))")
    public ResponseEntity<AdministratorResponseDto> one(ProceedingJoinPoint joinPoint) {
        System.out.println("AOP ACTIVATED");
        try {
            ResponseEntity<Administrator> responseEntity = (ResponseEntity<Administrator>)joinPoint.proceed(joinPoint.getArgs());
            if (responseEntity.getBody() != null) {
                return new ResponseEntity<>(this.createDto(responseEntity.getBody()), HttpStatus.OK);
            }
        } catch (Exception ignored){
        } catch (Throwable ignored) {
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @AfterThrowing(value = "execution(* rs.ac.singidunum.org.apiusers.controller.*.*(..))", throwing = "e")
    public void errorLogger(JoinPoint joinPoint, Throwable e) {
        e.printStackTrace();
    }





















//    @Order(1)
//    @Around("execution(* rs.ac.singidunum.org.apiusers.controller.AdministratorController.*(Long))")
//    public ResponseEntity<Administrator> checak(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println(10);
//        return (ResponseEntity<Administrator>) joinPoint.proceed(joinPoint.getArgs());
//    }

//    @Around("execution(* rs.ac.singidunum.org.apiusers.controller.*.*(Long, ..))")
//    public ResponseEntity<?> checkId(ProceedingJoinPoint joinPoint){
//        try {
//            if ((Long)joinPoint.getArgs()[0] <= 0) {
//                return (ResponseEntity<?>) joinPoint.proceed(joinPoint.getArgs());
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        System.out.println("Bad requests");
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
}
