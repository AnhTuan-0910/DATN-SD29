package com.springboot.bootstrap.repository;

import com.springboot.bootstrap.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamRepo extends JpaRepository<SanPham,String> {
    Page<SanPham> findAllByOrderByMaAsc(Pageable pageable);

    @Query("SELECT sp FROM SanPham sp  WHERE sp.trangThai = 1 ORDER BY sp.ma")
    Page<SanPham> getAllByTrangThai(Pageable pageable);




}
