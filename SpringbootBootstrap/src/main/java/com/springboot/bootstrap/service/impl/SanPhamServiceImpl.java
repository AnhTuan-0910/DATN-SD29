package com.springboot.bootstrap.service.impl;

import com.springboot.bootstrap.entity.SanPham;
import com.springboot.bootstrap.repository.SanPhamRepo;
import com.springboot.bootstrap.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SanPhamServiceImpl implements SanPhamService {
    @Autowired
    private SanPhamRepo sanPhamRepo;
    private static final String ma = "SP";
    private static int counter = 0;

    @Override
    public Page<SanPham> getAll(Pageable pageable) {
        return sanPhamRepo.findAllByOrderByMaAsc(pageable);
    }

    @Override
    public void add(SanPham sanPham) {
        sanPhamRepo.save(sanPham);
    }

    @Override
    public String generateMaSP() {
        counter++;
        return ma + String.format("%03d", counter);
    }
}
