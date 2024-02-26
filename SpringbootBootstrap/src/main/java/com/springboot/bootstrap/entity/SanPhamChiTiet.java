package com.springboot.bootstrap.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.UUID;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SanPhamChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idSpct;
    @ManyToOne
    @JoinColumn(name = "san_pham")
    private SanPham sanPham;
}
