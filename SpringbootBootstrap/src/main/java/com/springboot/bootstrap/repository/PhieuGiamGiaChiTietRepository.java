package com.springboot.bootstrap.repository;

import com.springboot.bootstrap.entity.PhieuGiamGiaChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhieuGiamGiaChiTietRepository extends JpaRepository<PhieuGiamGiaChiTiet, UUID> {

}
