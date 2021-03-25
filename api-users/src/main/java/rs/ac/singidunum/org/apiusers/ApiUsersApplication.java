package rs.ac.singidunum.org.apiusers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
@EnableEurekaClient
public class ApiUsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiUsersApplication.class, args);
    }

}
