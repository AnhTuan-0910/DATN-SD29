package com.springboot.bootstrap.service;

import com.springboot.bootstrap.entity.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface KhachHangService {

    Page<KhachHang> getAll(Pageable pageable);

    KhachHang getOne(UUID id);

    void add(KhachHang khachHang);

    void update(KhachHang khachHang,UUID id);

    Page<KhachHang> searchCodeOrName(String keyword, Pageable pageable );

    Page<KhachHang> searchTrangThai( int trangThai,Pageable pageable );

    String ImageUpload(UUID id, MultipartFile khImage);
}
