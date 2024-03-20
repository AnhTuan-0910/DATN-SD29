package com.springboot.bootstrap.service.impl;

import com.springboot.bootstrap.entity.NhanVien;
import com.springboot.bootstrap.repository.ChucVuRepo;
import com.springboot.bootstrap.repository.NhanVienRepo;
import com.springboot.bootstrap.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanVienServiceImpl implements NhanVienService{

    @Autowired
    private NhanVienRepo nhanVienRepo;
    private static final String ma = "KT";
    private static int counter = 0;

    @Autowired
    private ChucVuRepo chucVuRepo;

    @Override
    public List<NhanVien> findAllByTrangThai() {

        return nhanVienRepo.findAllByTrangThai(1);
    }

    @Override
    public Page<NhanVien> getAll(Pageable pageable) {

        return nhanVienRepo.findAllByOrderByMaAsc(pageable);
    }

    @Override
    public NhanVien getOne(String idNV) {
        NhanVien n = nhanVienRepo.findById(idNV).orElse(null);
        return n;
    }

    @Override
    public void add(NhanVien nhanVien) {

        nhanVienRepo.save(nhanVien);
    }

    @Override
    public void update(NhanVien nhanVien, String idNV) {
        nhanVien.setIdNV(idNV);
        nhanVienRepo.save(nhanVien);
    }

    @Override
    public Page<NhanVien> searchCodeOrName(String keyword, Pageable pageable) {
        return nhanVienRepo.searchCodeOrName(keyword, pageable);
    }

    @Override
    public Page<NhanVien> searchTrangThai(int trangThai, Pageable pageable) {
        return nhanVienRepo.searchTrangThai(trangThai, pageable);
    }

    @Override
    public String generateMaNV() {
        counter++;
        return ma + String.format("%03d", counter);
    }

}
