package com.springboot.bootstrap.service;

import com.springboot.bootstrap.entity.KhachHang;
import org.springframework.data.domain.Page;

import java.util.List;

public interface KhachHangService {

    Page<KhachHang> getAll(Integer pageNo);

    KhachHang getOne(String id);

    void add(KhachHang khachHang);

    void update(KhachHang khachHang);

    void delete(String id);

    Page<KhachHang> searchCodeOrName(String keyword, Integer pageNo );

    List<KhachHang> findAll();

    KhachHang findBySdt(String sdt);

    public String generateMaKH();

//    Page<KhachHang> searchTrangThai( int trangThai,Pageable pageable );
//
//    String ImageUpload(UUID id, MultipartFile khImage);
}
