package com.springboot.bootstrap.service;

import com.springboot.bootstrap.entity.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NhanVienService {

    public String generateMaNV();

    Page<NhanVien> getAll(Pageable pageable);

    NhanVien getOne(String idNV);

    List<NhanVien> findAllByTrangThai();

    void add(NhanVien nhanVien);

    void update(NhanVien nhanVien, String idNV);

    Page<NhanVien> searchCodeOrName(String keyword, Pageable pageable);

    Page<NhanVien> searchTrangThai(int trangThai, Pageable pageable);

}
