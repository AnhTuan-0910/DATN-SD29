package com.springboot.bootstrap.service.impl;

import com.springboot.bootstrap.entity.DTO.HoaDonDTO;
import com.springboot.bootstrap.entity.HoaDon;
import com.springboot.bootstrap.entity.KhachHang;
import com.springboot.bootstrap.entity.NhanVien;
import com.springboot.bootstrap.repository.HoaDonRepository;
import com.springboot.bootstrap.repository.KhachHangRepository;
import com.springboot.bootstrap.repository.NhanVienRepo;
import com.springboot.bootstrap.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
public class HoaDonServiceImpl implements HoaDonService {
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;
    @Autowired
    private NhanVienRepo nhanVienRepo;
    @Override
    public List<HoaDon> getList() {
        return hoaDonRepository.findAll();
    }

    @Override
    public Page<HoaDon> getListSearch(HoaDonDTO hoaDon,Pageable pageable) {
        KhachHang khachHang = khachHangRepository.findByMa(hoaDon.getMaNhanVien());
        NhanVien nhanVien = nhanVienRepo.findByMa(hoaDon.getMaKhachHang());
        Integer tinhTrang=0;
        Date ngayNhan,ngayThanhToan,ngayShip;
        switch (hoaDon.getTinhTrang()){
            case "Chờ xác nhận":tinhTrang=1;break;
            case "Đang xử lý":tinhTrang=2;break;
            case "Đang vận chuyển":tinhTrang=3;break;
            case "Hoàn thành":tinhTrang=4;break;
            case "Huỷ đơn":tinhTrang=5;break;
        }
        if(hoaDon.getNgayNhan().toString().isEmpty()){
             ngayNhan = null;
        }else {
            ngayNhan = Date.valueOf(hoaDon.getNgayNhan());
        }
        if(hoaDon.getNgayShip().toString().isEmpty()){
            ngayShip = null;
        }else {
            ngayShip = Date.valueOf(hoaDon.getNgayShip());
        }
        if(hoaDon.getNgayThanhToan().toString().isEmpty()){
            ngayThanhToan = null;
        }else {
            ngayThanhToan = Date.valueOf(hoaDon.getNgayThanhToan());
        }
        return hoaDonRepository.findAllByMaAndNhanVienAndKhachHangAndNgayThanhToanAndNgayNhanAndNgayShipAndTinhTrang(
                hoaDon.getMa(),nhanVien,khachHang,ngayThanhToan,ngayNhan,ngayShip,tinhTrang,pageable
        );
    }

    @Override
    public Page<HoaDon> getAll(Pageable of) {
        return hoaDonRepository.findAll(of);
    }

    @Override
    public HoaDon getOne(UUID id) {
        return hoaDonRepository.findById(id).get();
    }

    @Override
    public List<HoaDon> renderTab() {
        return hoaDonRepository.findAllByTinhTrang(4);
    }

    @Override
    public void add(HoaDon hoaDon) {
        hoaDonRepository.save(hoaDon);
    }

    @Override
    public void delete(UUID id) {
        hoaDonRepository.deleteById(id);
    }
}
