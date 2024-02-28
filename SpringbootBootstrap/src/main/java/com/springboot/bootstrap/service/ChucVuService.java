package com.springboot.bootstrap.service;

import com.springboot.bootstrap.entity.ChucVu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChucVuService {

    Page<ChucVu> getAll(Pageable pageable);

    ChucVu getOne(String idCV);

    void add(ChucVu chucVu);

    void update(ChucVu chucVu, String idCV);

    Page<ChucVu> findAllByTen(String ten, Integer limit, Integer offset);


}
