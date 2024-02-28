package com.springboot.bootstrap.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.util.Date;

@Entity
@Table(name = "nhan_vien")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_nhan_vien")
    private String idNV;

    @Column(name = "ma")
    private String ma;

    @Column(name = "email")
    private String email;

    @Column(name = "ten")
    private String ten;

    @Column(name = "gioi_tinh")
    private String gioiTinh;

    @Column(name = "ngay_sinh")
    private Date ngaySinh;

    @Column(name = "anh_nhan_vien")
    private byte[] anhNhanVien;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "mat_khau")
    private String matKhau;

    @Column(name = "trang_thai")
    private int trangThai;

    @Column(name = "tao_luc")
    private Date taoLuc;

    @Column(name = "sua_luc")
    private Date suaLuc;

    @Column(name = "tao_boi")
    private String taoBoi;

    @Column(name = "sua_boi")
    private String suaBoi;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_chuc_vu", referencedColumnName = "id_chuc_vu")
    private ChucVu idCV;

    @Override
    public String toString() {
        return "NhanVien{" +
                "idNV=" + idNV +
                ", ma='" + ma + '\'' +
                ", email='" + email + '\'' +
                ", ten='" + ten + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", anhNhanVien=" + anhNhanVien +
                ", diaChi='" + diaChi + '\'' +
                ", sdt='" + sdt + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", trangThai=" + trangThai +
                ", taoLuc=" + taoLuc +
                ", suaLuc=" + suaLuc +
                ", taoBoi='" + taoBoi + '\'' +
                ", suaBoi='" + suaBoi + '\'' +
                ", idCV=" + idCV +
                '}';
    }
}
