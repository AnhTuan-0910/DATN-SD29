package com.springboot.bootstrap.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @ManyToOne
    @JoinColumn(name = "id_pgg")
    private PhieuGiamGia phieuGiamGia;
    @Column(name = "ma")
    private String ma;
    @Column(name = "gia")
    private Double gia;
    @Column(name = "ngay_thanh_toan")
    private Date ngayThanhToan;
    @Column(name = "ngay_ship")
    private Date ngayShip;
    @Column(name = "ngay_nhan")
    private Date ngayNhan;
    @Column(name = "tinh_trang")
    private Integer tinhTrang;
    @Column(name = "dia_chi")
    private String diaChi;
    @Column(name = "tien_ship")
    private Double tienShip;
    @Column(name = "thanh_tien")
    private Double thanhTien;
    @Column(name = "tao_luc")
    private Date taoLuc;
    @Column(name = "sua_luc")
    private Date suaLuc;
    @OneToMany(mappedBy = "hoaDon",cascade = CascadeType.ALL)
    private List<HoaDonChiTiet> listhdct;
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
