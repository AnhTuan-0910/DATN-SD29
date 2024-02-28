package com.springboot.bootstrap.repository;

import com.springboot.bootstrap.entity.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, UUID> {
    Page<KhachHang> findAll(Pageable pageable);

    KhachHang findByMa(String ma);

    @Query("SELECT kh FROM KhachHang kh WHERE " +
            "LOWER(kh.ma) IS NULL OR   LOWER(kh.ma) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(kh.ten) IS NULL OR  LOWER(kh.ten) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<KhachHang> searchCodeOrName(@Param("keyword") String keyword, Pageable pageable );

    @Query("SELECT dc FROM KhachHang dc WHERE " +
            " (:trangThai IS NULL OR dc.trangThai = :trangThai) " )
    Page<KhachHang> searchTrangThai(@Param("trangThai") int trangThai, Pageable pageable);
}
