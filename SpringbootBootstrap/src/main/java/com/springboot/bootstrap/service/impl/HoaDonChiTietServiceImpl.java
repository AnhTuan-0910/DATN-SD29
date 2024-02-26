package com.springboot.bootstrap.service.impl;

import com.springboot.bootstrap.entity.HoaDonChiTiet;
import com.springboot.bootstrap.repository.HoaDonChiTietRepository;
import com.springboot.bootstrap.service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService {
    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;
    @Override
    public List<HoaDonChiTiet> getList(UUID id) {
        return hoaDonChiTietRepository.findAllByIdHoaDon(id);
    }
}
