package api.shop.online.onlineshopapi.repository;

import api.shop.online.onlineshopapi.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
