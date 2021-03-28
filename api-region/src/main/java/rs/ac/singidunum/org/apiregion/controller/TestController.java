package rs.ac.singidunum.org.apiregion.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/helloWorld")
    public ResponseEntity<String> helloWorld(){
        return new ResponseEntity<>("Hello world", HttpStatus.OK);
    }
}
