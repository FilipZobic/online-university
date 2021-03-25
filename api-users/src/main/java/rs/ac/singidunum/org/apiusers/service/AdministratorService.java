package rs.ac.singidunum.org.apiusers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.org.apiusers.model.Administrator;
import rs.ac.singidunum.org.apiusers.repository.AdministratorRepository;

import java.util.Optional;

@Service
public class AdministratorService {

    protected AdministratorRepository repository;

    public Optional<Administrator> findByUsername(String username){
        return repository.findByUsernameNotDeleted(username);
    };

    @Autowired
    public AdministratorService(AdministratorRepository repository) {
        this.repository = repository;
    }

    public Optional<Administrator> findOne(Long id) {
        return this.repository.findOneByIdNotDeleted(id);
    }

    public Page<Administrator> findAll(Pageable pageable) {
        return this.repository.findAllNotDeleted(pageable);
    }

    public Administrator save(Administrator user) {
        return this.repository.save(user);
    }

    public Administrator update(Long id, Administrator newUser) {
        newUser.setId(id);
        try {
            Optional<Administrator> oldUser = this.findOne(id);
            if (oldUser.isPresent()){
                oldUser.get().replace(newUser);
                this.save(newUser);
                return newUser;
            }
        } catch (Exception ignored){
        }
        return null;
    }

    public void delete(Long id){
        if (this.findOne(id).isEmpty()){
            throw new RuntimeException("Object does not exist");
        }
        this.repository.softDelete(id);
    }
}
