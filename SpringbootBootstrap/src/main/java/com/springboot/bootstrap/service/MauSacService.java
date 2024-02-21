package com.springboot.bootstrap.service;

import com.springboot.bootstrap.entity.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MauSacService {
    Page<MauSac> getAll(Pageable pageable);

    MauSac getOne(String id);

    void add(MauSac mauSac);

    void update(MauSac mauSac, String id);

    Page<MauSac> searchCodeOrName( String keyword, Pageable pageable );

    Page<MauSac> searchTrangThai( int trangThai,Pageable pageable );
}
