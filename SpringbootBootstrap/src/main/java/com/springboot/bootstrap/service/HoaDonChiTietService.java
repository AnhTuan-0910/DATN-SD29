package com.springboot.bootstrap.service;

import com.springboot.bootstrap.entity.HoaDonChiTiet;

import java.util.List;
import java.util.UUID;

public interface HoaDonChiTietService {
    List<HoaDonChiTiet> getList(UUID id);

    void add(HoaDonChiTiet hoaDonChiTiet);

    void update(HoaDonChiTiet hoaDonChiTiet);

    void delete(UUID idhdct);

    HoaDonChiTiet getOne(UUID idhdct);
}
