package com.springboot.bootstrap.service.impl;

import com.springboot.bootstrap.entity.DiaChi;
import com.springboot.bootstrap.repository.DiaChiRepository;
import com.springboot.bootstrap.service.DiaChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class DiaChiServiceImpl implements DiaChiService {

    @Autowired
    private DiaChiRepository diaChiRepository;

    Timestamp currentTimestamp;

    @Override
    public Page<DiaChi> getAll(Pageable pageable) {
        return diaChiRepository.findAll(pageable);
    }

    @Override
    public List<DiaChi> findAll() {
        return diaChiRepository.findAll();
    }

    @Override
    public DiaChi getOne(UUID id) {
        return diaChiRepository.findById(id).orElse(null);
    }

    @Override
    public void add(DiaChi diaChi) {
        java.util.Date currentDate = new java.util.Date();
        currentTimestamp = new Timestamp(currentDate.getTime());
        diaChi.setTaoLuc(currentTimestamp);
        diaChi.setSuaLuc(currentTimestamp);
        diaChiRepository.save(diaChi);
    }

    @Override
    public void update(DiaChi diaChi, UUID id) {
        diaChi.setIdDiaChi(id);
        java.util.Date currentDate = new java.util.Date();
        currentTimestamp = new Timestamp(currentDate.getTime());
        diaChi.setTaoLuc(currentTimestamp);
        diaChi.setSuaLuc(currentTimestamp);
        diaChiRepository.save(diaChi);
    }

    @Override
    public Page<DiaChi> searchTrangThai(int trang_thai, Pageable pageable) {
        return diaChiRepository.searchTrangThai(trang_thai, pageable);
    }
}
