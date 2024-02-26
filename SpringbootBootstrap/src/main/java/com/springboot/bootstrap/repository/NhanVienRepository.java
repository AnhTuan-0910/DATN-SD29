package com.springboot.bootstrap.repository;

import com.springboot.bootstrap.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, UUID> {
    NhanVien findByMa(String ma);
}
