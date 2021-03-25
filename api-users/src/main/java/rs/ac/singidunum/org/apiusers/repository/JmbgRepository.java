package rs.ac.singidunum.org.apiusers.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.org.apiusers.model.Jmbg;

@Repository
public interface JmbgRepository extends PagingAndSortingRepository<Jmbg, String> {
}
