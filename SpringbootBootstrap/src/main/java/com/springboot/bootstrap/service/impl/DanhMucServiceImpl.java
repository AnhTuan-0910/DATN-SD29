package com.springboot.bootstrap.service.impl;

import com.springboot.bootstrap.entity.DanhMuc;
import com.springboot.bootstrap.repository.DanhMucRepo;
import com.springboot.bootstrap.service.DanhMucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DanhMucServiceImpl implements DanhMucService {
    @Autowired
    private DanhMucRepo danhMucRepo;

    @Override
    public Page<DanhMuc> getAll(Pageable pageable) {
        return danhMucRepo.findAll(pageable);
    }

    @Override
    public DanhMuc getOne(String id) {
        return danhMucRepo.findById(id).orElse(null);
    }

    @Override
    public void add(DanhMuc danhMuc) {
        danhMucRepo.save(danhMuc);
    }

    @Override
    public void update(DanhMuc danhMuc, String id) {
        danhMuc.setIdDanhMuc(id);
        danhMucRepo.save(danhMuc);
    }
    @Override
    public Page<DanhMuc> searchCodeOrName(String keyword, Pageable pageable) {
        return danhMucRepo.searchCodeOrName(keyword, pageable);
    }

    @Override
    public Page<DanhMuc> searchTrangThai(int trangThai, Pageable pageable) {
        return danhMucRepo.searchTrangThai(trangThai, pageable);
    }
}
