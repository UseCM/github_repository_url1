package rd.dao;

import com.rd.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface ProductDao extends JpaRepository<Product,Integer> {


}
