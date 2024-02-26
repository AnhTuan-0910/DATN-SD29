package com.springboot.bootstrap.service;

import com.springboot.bootstrap.entity.DanhMuc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DanhMucService {
    Page<DanhMuc> getAll(Pageable pageable);

    List<DanhMuc> findAllByTrangThai();

    public String generateMaDM();

    DanhMuc getOne(String id);

    void add(DanhMuc danhMuc);

    void update(DanhMuc danhMuc, String id);

    Page<DanhMuc> searchCodeOrName(String keyword, Pageable pageable);

    Page<DanhMuc> searchTrangThai(int trangThai, Pageable pageable);
}
