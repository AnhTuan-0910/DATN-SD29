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

import java.util.Date;

@Entity
@Table(name = "chuc_vu")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChucVu {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_chuc_vu")
    private String idCV;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

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

    @Override
    public String toString() {
        return "ChucVu{" +
                "idCV=" + idCV +
                ", ma='" + ma + '\'' +
                ", ten='" + ten + '\'' +
                ", trangThai=" + trangThai +
                ", taoLuc=" + taoLuc +
                ", suaLuc=" + suaLuc +
                ", taoBoi='" + taoBoi + '\'' +
                ", suaBoi='" + suaBoi + '\'' +
                '}';
    }
}
