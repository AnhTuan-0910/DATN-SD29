package com.springboot.bootstrap.service;

import com.springboot.bootstrap.entity.KichThuoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface KichThuocService {
    Page<KichThuoc> getAll(Pageable pageable);

    KichThuoc getOne(String id);

    void add(KichThuoc kichThuoc);

    void update(KichThuoc kichThuoc, String id);

    Page<KichThuoc> searchCodeOrName(String keyword, Pageable pageable);

    Page<KichThuoc> searchTrangThai(int trangThai, Pageable pageable);
}
