package mate.academy.springboot.aop.service;

import java.util.List;
import mate.academy.springboot.aop.model.Product;

public interface ProductService {
    List<Product> findAll();

    Product save(Product category);
}
