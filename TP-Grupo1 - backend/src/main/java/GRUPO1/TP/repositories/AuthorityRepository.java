package GRUPO1.TP.repositories;

import GRUPO1.TP.entities.Authority;
import GRUPO1.TP.entities.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    public Authority findByName(AuthorityName name);
}
