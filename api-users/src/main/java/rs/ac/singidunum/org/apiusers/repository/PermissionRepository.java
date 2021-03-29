package rs.ac.singidunum.org.apiusers.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.org.apiusers.model.Permission;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository <Permission, Long> {

    @Query("SELECT a FROM Permission a WHERE a.isDeleted = false")
    Iterable<Permission> findAllNotDeleted();

    @Query("SELECT a FROM Permission a WHERE a.isDeleted = false AND a.id = :id")
    Optional<Permission> findOneByIdNotDeleted(Long id);

    @Query("SELECT a FROM Permission a WHERE a.isDeleted = false AND a.name LIKE :permissionName")
    Optional<Permission> findByNameNotDeleted(String permissionName);

    @Query("SELECT a FROM Permission a WHERE a.name LIKE :permissionName")
    Optional<Permission> findByNameDeleted(String permissionName);

    @Transactional
    @Modifying
    @Query("UPDATE Permission a SET a.isDeleted = true WHERE a.id = :id")
    void softDelete(Long id);
}
