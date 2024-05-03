package com.springboot.bootstrap.repository;

import com.springboot.bootstrap.entity.HoaDon;
import com.springboot.bootstrap.entity.KhachHang;
import com.springboot.bootstrap.entity.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, UUID> {
    List<HoaDon> findAllByTinhTrang(Integer tinhTrang);

    Page<HoaDon> findAll(Pageable pageable);
    Page<HoaDon> findAllByMaContaining(String keyword,Pageable pageable);

}
