package rs.ac.singidunum.org.apiusers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.org.apiusers.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
