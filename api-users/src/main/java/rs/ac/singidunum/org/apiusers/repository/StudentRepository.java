package rs.ac.singidunum.org.apiusers.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.org.apiusers.model.Student;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
}
