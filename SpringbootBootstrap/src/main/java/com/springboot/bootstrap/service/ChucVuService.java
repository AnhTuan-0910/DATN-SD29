package com.springboot.bootstrap.service;

import com.springboot.bootstrap.entity.ChucVu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChucVuService {

    public String generateMaCV();

    Page<ChucVu> getAll(Pageable pageable);

    ChucVu getOne(String idCV);

    List<ChucVu> findAllByTrangThai();

    void add(ChucVu chucVu);

    void update(ChucVu chucVu, String idCV);

    Page<ChucVu> searchCodeOrName(String keyword, Pageable pageable);

    Page<ChucVu> searchTrangThai(int trangThai, Pageable pageable);


}
