package com.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "san_pham")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ten_san_pham")
    private String tensp;

    @Column(name = "hinh_anh_san_pham")
    private String hinhanh;

    @Column(name = "gia_san_pham")
    private String gia;

    @Column(name = "mo_ta")
    private String mota;
}
