package rd.service;

import com.rd.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rd.dao.ProductDao;
import rd.service.impl.IProductService;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductDao productDao;
    @Override
    public Product findByPid(Integer pid) {
        return productDao.findById(pid).get();
    }
}
