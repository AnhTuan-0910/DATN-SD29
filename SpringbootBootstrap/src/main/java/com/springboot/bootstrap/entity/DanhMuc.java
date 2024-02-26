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


@Entity
@Table(name = "danh_muc")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DanhMuc {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idDanhMuc;
    private String ma;
    private String ten;
    private int trangThai;
}
