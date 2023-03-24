package api.shop.online.onlineshopapi.repository;

import api.shop.online.onlineshopapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
