package rs.ac.singidunum.org.apiusers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.org.apiusers.dto.request.AdministratorRequestDto;
import rs.ac.singidunum.org.apiusers.dto.response.AdministratorResponseDto;
import rs.ac.singidunum.org.apiusers.model.Administrator;
import rs.ac.singidunum.org.apiusers.service.AdministratorService;

import java.util.Optional;

@RestController
public class AdministratorController {

    AdministratorService service;

    @Autowired
    public void setAdministrator(AdministratorService service) {
        this.service = service;
    }

    @GetMapping(path = "/administrators")
    public ResponseEntity<Page<Administrator>> getAll(Pageable pageable) {
        return new ResponseEntity<>(this.service.findAll(pageable),HttpStatus.OK);
    }

    @GetMapping(path = "/administrators/{id}")
    public ResponseEntity<Administrator> getOne(@PathVariable(name = "id") Long id) {
        return this.service.findOne(id)
                .map(usr -> new ResponseEntity<>(usr,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping(path = "/administrators/search")
    public ResponseEntity<Administrator> getOneByUsername(@RequestParam(name = "username") String usrName){
        return this.service.findByUsername(usrName)
                .map(usr -> new ResponseEntity<>(usr, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping(path = "/administrators")
    public ResponseEntity<Administrator> saveOne(@RequestBody AdministratorRequestDto adminDto){

        Administrator administrator = new Administrator();
        administrator.setEmail(adminDto.getEmail());
        administrator.setPassword(adminDto.getPassword());
        administrator.setUsername(adminDto.getUsername());

        Administrator newAdministrator = this.service.save(administrator);
        return new ResponseEntity<>(newAdministrator, HttpStatus.OK);

    }

    @PutMapping(path = "/administrators/{id}")
    public ResponseEntity<AdministratorResponseDto> updateOne(@PathVariable(name = "id") Long id, @RequestBody AdministratorRequestDto adminDto) throws Exception{
        Optional<Administrator> administrator = this.service.findOne(id);

        if (administrator.isPresent()) {
            try {
                Administrator newAdmin = new Administrator();
                newAdmin.setEmail(adminDto.getEmail());
                newAdmin.setPassword(adminDto.getPassword());
                newAdmin.setUsername(adminDto.getUsername());
                newAdmin.setId(id);

                Administrator a = this.service.update(id, newAdmin);
                return new ResponseEntity<>(new AdministratorResponseDto(a.getId(),a.getUsername(),a.getEmail(),a.isDeleted()), HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/administrators/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
            if (this.service.findOne(id).isPresent()){
                this.service.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
