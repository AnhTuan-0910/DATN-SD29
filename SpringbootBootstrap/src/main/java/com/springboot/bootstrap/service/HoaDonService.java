package com.springboot.bootstrap.service;

import com.springboot.bootstrap.entity.DTO.HoaDonDTO;
import com.springboot.bootstrap.entity.HoaDon;
import com.springboot.bootstrap.entity.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HoaDonService {
    List<HoaDon> getList();

    Page<HoaDon> getListSearch(HoaDonDTO hoaDon,Pageable pageable);

    Page<HoaDon> getAll(Pageable of);
}
