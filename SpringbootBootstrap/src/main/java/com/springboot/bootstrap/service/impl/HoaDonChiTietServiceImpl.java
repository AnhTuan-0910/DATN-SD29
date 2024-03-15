package com.springboot.bootstrap.service.impl;

import com.springboot.bootstrap.entity.HoaDon;
import com.springboot.bootstrap.entity.HoaDonChiTiet;
import com.springboot.bootstrap.entity.SanPhamCT;
import com.springboot.bootstrap.repository.HoaDonChiTietRepository;
import com.springboot.bootstrap.repository.HoaDonRepository;
import com.springboot.bootstrap.repository.SanPhamCTRepo;
import com.springboot.bootstrap.service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService {
    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;
    @Autowired
    private SanPhamCTRepo sanPhamCTRepo;
    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Override
    public List<HoaDonChiTiet> getList(UUID id) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        return hoaDonChiTietRepository.findAllByHoaDon(hoaDon);
    }

    @Override
    public void add(UUID idHoaDon, UUID idSpct, Integer soLuong) {
        String a = idSpct.toString();
        SanPhamCT sanPhamCT = sanPhamCTRepo.findById(idSpct.toString()).get();
        HoaDon hoaDon = hoaDonRepository.findById(idHoaDon).get();
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(sanPhamCT,hoaDon,sanPhamCT.getGia()*soLuong,soLuong);
        hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

    @Override
    public void update(HoaDonChiTiet hoaDonChiTiet) {
        hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

    @Override
    public void delete(UUID idhdct) {
        hoaDonChiTietRepository.deleteById(idhdct);
    }

    @Override
    public HoaDonChiTiet getOne(UUID idhdct) {
        return hoaDonChiTietRepository.findById(idhdct).get();
    }
}
