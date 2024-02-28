package com.springboot.bootstrap.service;

import com.springboot.bootstrap.entity.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NhanVienService {

    Page<NhanVien> getAll(Pageable pageable);

    NhanVien getOne(String idNV);

    void add(NhanVien nhanVien);

    void update(NhanVien nhanVien, String idNV);

    Page<NhanVien> findAllByTen(String ten, Integer limit, Integer offset);

}
