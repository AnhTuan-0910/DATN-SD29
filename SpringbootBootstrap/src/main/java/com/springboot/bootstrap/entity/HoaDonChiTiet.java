package com.springboot.bootstrap.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class HoaDonChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idHoaDonChiTiet;
    @ManyToOne
    @JoinColumn(name = "id_spct")
    private SanPhamCT sanPhamChiTiet;
    private UUID idHoaDon;
    private Double gia;
    private Integer soLuong;
    private Date taoLuc;
    private Date suaLuc;
}
