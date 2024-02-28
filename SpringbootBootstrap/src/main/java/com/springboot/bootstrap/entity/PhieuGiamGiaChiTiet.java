package com.springboot.bootstrap.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "phieu_giam_gia_chi_tiet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PhieuGiamGiaChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPggct;

    @ManyToOne
    @JoinColumn(name = "id_pgg")
    private PhieuGiamGia phieuGiamGia;

    @ManyToOne
    @JoinColumn(name = "id_kh")
    private KhachHang khachHang;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "tao_luc")
    private Date taoLuc;

    @Column(name = "sua_luc")
    private Date suaLuc;

    @Column(name = "tao_boi")
    private String taoBoi;

    @Column(name = "sua_boi")
    private String suaBoi;
}
