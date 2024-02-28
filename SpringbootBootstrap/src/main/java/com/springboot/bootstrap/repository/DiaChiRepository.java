package com.springboot.bootstrap.repository;

import com.springboot.bootstrap.entity.DiaChi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DiaChiRepository extends JpaRepository<DiaChi, UUID> {
    Page<DiaChi> findAll(Pageable pageable);

    @Query("SELECT dc FROM DiaChi dc WHERE " +
            " (:trang_thai IS NULL OR dc.trang_thai = :trang_thai) " )
    Page<DiaChi> searchTrangThai(@Param("trang_thai") int trang_thai, Pageable pageable);
}
