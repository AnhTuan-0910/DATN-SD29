package com.springboot.bootstrap.repository;

import com.springboot.bootstrap.entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, UUID> {
    List<GioHangChiTiet> findAllByGioHang_KhachHang_Ma(String ma);
}
