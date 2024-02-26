package com.springboot.bootstrap.repository;

import com.springboot.bootstrap.entity.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, UUID> {
    List<HoaDonChiTiet> findAllByIdHoaDon(UUID id);
}
