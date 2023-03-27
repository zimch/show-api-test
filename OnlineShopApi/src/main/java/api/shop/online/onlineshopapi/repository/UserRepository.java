package api.shop.online.onlineshopapi.repository;

import api.shop.online.onlineshopapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.login = ?1")
    User findByUsername(String username);

    Optional<User> findByLogin(String login);
}
