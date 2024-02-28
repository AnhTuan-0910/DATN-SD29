package com.springboot.bootstrap.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dia_chi")
public class DiaChi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dia_chi", nullable = false)
    private UUID idDiaChi;

//    @NotEmpty(message = "Không Được Để Trống Địa Chỉ")
    @Column(name = "dia_chi")
    private String diaChi;

//    @NotEmpty(message = "Không Được Để Trống Huyện")
    @Column(name = "huyen")
    private String huyen;

//    @NotEmpty(message = "Không Được Để Trống Tỉnh")
    @Column(name = "tinh")
    private String tinh;

//    @NotEmpty(message = "Không Được Để Trống Thành Phố")
    @Column(name = "thanh_pho")
    private String thanhPho;

//    @NotEmpty(message = "Không Được Để Trống Quốc Gia")
    @Column(name = "quoc_gia")
    private String quocGia;

//    @Pattern(message = "Nhập số Điện Thoại Chưa Đúng", regexp = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$")
//    @NotEmpty(message = "Không Được Để Trống Số Điện Thoại")
//    @Size(min = 10, message = "Số Điện Thoại Tối Thiểu 10 Số")
//    @Size(max = 10, message = "Số Điện Thoại Tối Đa 10 Số")
    @Column(name = "sdt")
    private String sdt;

    @Column(name = "trang_thai")
    private int trang_thai;

    @Column(name = "tao_luc")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.sql.Timestamp taoLuc;

    @Column(name = "sua_luc")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.sql.Timestamp suaLuc;

    @Column(name = "tao_boi")
    private String taoBoi;

    @Column(name = "sua_boi")
    private String suaBoi;

    @Override
    public int hashCode() {
        return 42;
    }
}
