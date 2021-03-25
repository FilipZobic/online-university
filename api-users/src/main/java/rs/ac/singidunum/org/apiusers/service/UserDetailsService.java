package rs.ac.singidunum.org.apiusers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.org.apiusers.repository.UserRepository;

@Service
public class UserDetailsService {

    private UserRepository repository;

    @Autowired
    public UserDetailsService(UserRepository repository) {
        this.repository = repository;
    }
}
