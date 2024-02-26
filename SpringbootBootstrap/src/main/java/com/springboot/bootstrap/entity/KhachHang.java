package com.springboot.bootstrap.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idKh;
    private String ten;
    private String ma;
}
