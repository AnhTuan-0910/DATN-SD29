package com.springboot.bootstrap.repository;

import com.springboot.bootstrap.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamRepo extends JpaRepository<SanPham,String> {
    Page<SanPham> findAll(Pageable pageable);
}
