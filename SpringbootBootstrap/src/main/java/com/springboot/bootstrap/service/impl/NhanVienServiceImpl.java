package com.springboot.bootstrap.service.impl;

import com.springboot.bootstrap.entity.NhanVien;
import com.springboot.bootstrap.repository.NhanVienRepo;
import com.springboot.bootstrap.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NhanVienServiceImpl implements NhanVienService{

    @Autowired
    private NhanVienRepo nhanVienRepo;

    @Override
    public Page<NhanVien> getAll(Pageable pageable) {
        return nhanVienRepo.findAll(pageable);
    }

    @Override
    public NhanVien getOne(String idNV) {
        return nhanVienRepo.findById(idNV).orElse(null);
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
    public Page<NhanVien> findAllByTen(String ten, Integer limit, Integer offset) {
        Pageable page = PageRequest.of(offset, limit);
        return nhanVienRepo.findAllByTen(ten,page);
    }

}
