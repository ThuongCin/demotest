package com.Service.imlp;

import com.Entity.Product;
import com.Res.ProductRepository;
import com.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class ProductServiceImlp implements ProductService {
    @Autowired
    private ProductRepository ProductRepository;

    @Override
    public List<Product> getAll() { return ProductRepository.findAll();}

    @Override
    public void save(Product giay) { ProductRepository.save(giay);}

    @Override
    public void delete(Long id) { ProductRepository.deleteById(id);}

    @Override
    public List<Product> finByName(String tensanpham) {
        return null;
    }

    public List<Product> findByName(String tensanpham) {
        return ProductRepository.findByTenspContaining(tensanpham);
    }

    @Override
    public Product findById(Long id) {return  ProductRepository.findById(id).get();}

    @Override
    public  List<Product> searchGiay(String timkiem) {
        return  this.ProductRepository.searchGiay(timkiem);
    }
}