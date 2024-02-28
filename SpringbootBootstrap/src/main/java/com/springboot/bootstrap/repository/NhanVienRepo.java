package com.springboot.bootstrap.repository;

import com.springboot.bootstrap.entity.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVienRepo extends JpaRepository<NhanVien, String> {

    Page<NhanVien> findAll(Pageable pageable);

    Page<NhanVien> findAllByTen(String name, Pageable pageable);

    NhanVien findByMa(String ma);
}
