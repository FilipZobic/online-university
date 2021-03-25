package rs.ac.singidunum.org.apiusers.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.org.apiusers.model.Administrator;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface AdministratorRepository extends PagingAndSortingRepository<Administrator, Long> {

    @Query("SELECT a FROM Administrator a WHERE a.isDeleted = false")
    Page<Administrator> findAllNotDeleted(Pageable pageable);

    @Query("SELECT a FROM Administrator a WHERE a.isDeleted = false AND a.id = :id")
    Optional<Administrator> findOneByIdNotDeleted(Long id);

    @Query("SELECT a FROM Administrator a WHERE a.isDeleted = false AND a.username LIKE :username")
    Optional<Administrator> findByUsernameNotDeleted(String username);

    @Transactional
    @Modifying
    @Query("UPDATE Administrator a SET a.isDeleted = true WHERE a.id = :id")
    void softDelete(Long id);
}
