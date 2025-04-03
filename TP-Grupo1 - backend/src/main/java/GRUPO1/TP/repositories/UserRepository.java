package GRUPO1.TP.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import GRUPO1.TP.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUserName(String userName);
}
