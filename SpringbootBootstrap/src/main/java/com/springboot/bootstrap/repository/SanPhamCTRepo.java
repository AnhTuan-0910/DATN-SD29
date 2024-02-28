package com.springboot.bootstrap.repository;

import com.springboot.bootstrap.entity.SanPhamCT;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamCTRepo extends JpaRepository<SanPhamCT, String> {

    @Query("SELECT spct FROM SanPhamCT spct JOIN spct.sanPham sp JOIN spct.mauSac ms WHERE sp.id = :sanPhamId ORDER BY ms.ten")
    Page<SanPhamCT> findAllBySanPhamIdAndOrderByTenMS(String sanPhamId, Pageable pageable);
}
