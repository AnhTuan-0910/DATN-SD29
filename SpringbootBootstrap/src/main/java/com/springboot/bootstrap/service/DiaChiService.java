package com.springboot.bootstrap.service;

import com.springboot.bootstrap.entity.DiaChi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface DiaChiService {

    Page<DiaChi> getAll(Pageable pageable);

    List<DiaChi> findAll();

    DiaChi getOne(UUID id);

    void add(DiaChi diaChi);

    void update(DiaChi diaChi,UUID id);

    Page<DiaChi> searchTrangThai( int trang_thai,Pageable pageable );
}
