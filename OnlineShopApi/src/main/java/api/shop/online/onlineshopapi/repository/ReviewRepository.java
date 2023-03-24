package api.shop.online.onlineshopapi.repository;

import api.shop.online.onlineshopapi.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
