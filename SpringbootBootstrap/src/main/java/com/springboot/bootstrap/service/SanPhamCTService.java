package com.springboot.bootstrap.service;

import com.springboot.bootstrap.entity.MauSac;
import com.springboot.bootstrap.entity.SanPhamCT;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SanPhamCTService {
    public String generateMaSPCT();

    void add(SanPhamCT sanPhamCT);

    SanPhamCT getOne(String id);

    void update(SanPhamCT sanPhamCT, String id);

    Page<SanPhamCT> getAllBySP(String idSP, Pageable pageable);
}
