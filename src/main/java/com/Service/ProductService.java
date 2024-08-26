package com.Service;
import com.Entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    void save(Product giay);
    void delete(Long id);
    List<Product> finByName(String tensanpham);
    Product findById(Long id);
    List<Product> searchGiay(String timkiem);
}
