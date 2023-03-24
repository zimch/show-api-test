package api.shop.online.onlineshopapi.repository;

import api.shop.online.onlineshopapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
