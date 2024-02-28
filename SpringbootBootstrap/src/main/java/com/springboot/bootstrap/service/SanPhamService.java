package com.springboot.bootstrap.service;

import com.springboot.bootstrap.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SanPhamService {
    public String generateMaSP();
    void add(SanPham sanPham);
    Page<SanPham> getAll(Pageable pageable);
}
