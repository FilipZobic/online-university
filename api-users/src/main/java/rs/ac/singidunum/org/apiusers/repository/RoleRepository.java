package rs.ac.singidunum.org.apiusers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.org.apiusers.model.Role;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository <Role, Long> {

    @Query("SELECT a FROM Role a WHERE a.isDeleted = false")
    Iterable<Role> findAllNotDeleted();

    @Query("SELECT a FROM Role a WHERE a.isDeleted = false AND a.id = :id")
    Optional<Role> findOneByIdNotDeleted(Long id);

    @Query("SELECT a FROM Role a WHERE a.isDeleted = false AND a.name LIKE :roleName")
    Optional<Role> findByNameNotDeleted(String roleName);

    @Query("SELECT a FROM Role a WHERE a.name LIKE :roleName")
    Optional<Role> findByNameDeleted(String roleName);

    @Transactional
    @Modifying
    @Query("UPDATE Role a SET a.isDeleted = true WHERE a.id = :id")
    void softDelete(Long id);
}
