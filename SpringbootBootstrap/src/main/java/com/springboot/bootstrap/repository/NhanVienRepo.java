package com.springboot.bootstrap.repository;

import com.springboot.bootstrap.entity.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVienRepo extends JpaRepository<NhanVien, String> {

    Page<NhanVien> findAllByOrderByMaAsc(Pageable pageable);

    List<NhanVien> findAllByTrangThai(int trangThai);

    @Query("SELECT nv FROM NhanVien nv WHERE " +
            "LOWER(nv.ma) IS NULL OR   LOWER(nv.ma) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(nv.ten) IS NULL OR  LOWER(nv.ten) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<NhanVien> searchCodeOrName(@Param("keyword") String keyword, Pageable pageable );

    @Query("SELECT nv FROM NhanVien nv WHERE " +
            " (:trangThai IS NULL OR nv.trangThai = :trangThai) " )
    Page<NhanVien> searchTrangThai(@Param("trangThai") int trangThai,Pageable pageable );

    NhanVien findByMa(String ma);
}
