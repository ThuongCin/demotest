package com.Res;

import java.util.List;

import com.Entity.Product;



public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByTenspContaining(String tensanpham);
    boolean existsByTenspAndHinhanh(String tensanpham, String hinhanhsanpham);
    @Query("SELECT G FROM Giay G WHERE G.tensp LIKE %?1% ")
    List<Product> searchGiay(@Param("timkiem") String timkiem);
}
