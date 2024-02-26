package com.springboot.bootstrap.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.sql.Date;
import java.util.UUID;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "hoa_don")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_hoa_don")
    private UUID idHoaDon;
    @ManyToOne
    @JoinColumn(name = "id_kh")
    private KhachHang khachHang;
    @ManyToOne
    @JoinColumn(name = "id_nv")
    private NhanVien nhanVien;
    private String ma;
    private Double gia;
    private Date ngayThanhToan;
    private Date ngayShip;
    private Date ngayNhan;
    private Integer tinhTrang;
    @Column(name = "dia_chi")
    private String diaChi;
    private Double tienShip;
    private Double thanhTien;
    private Date taoLuc;
    private Date suaLuc;

    public HoaDon(KhachHang khachHang, NhanVien nhanVien, String ma, Date ngayThanhToan, Date ngayShip, Date ngayNhan, Integer tinhTrang) {
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.ma = ma;
        this.ngayThanhToan = ngayThanhToan;
        this.ngayShip = ngayShip;
        this.ngayNhan = ngayNhan;
        this.tinhTrang = tinhTrang;
    }
}
