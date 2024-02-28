package com.springboot.bootstrap.service.impl;

import com.springboot.bootstrap.entity.ChucVu;
import com.springboot.bootstrap.repository.ChucVuRepo;
import com.springboot.bootstrap.service.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ChucVuServiceImpl implements ChucVuService {

    @Autowired
    private ChucVuRepo chucVuRepo;

    @Override
    public Page<ChucVu> getAll(Pageable pageable) {
        return chucVuRepo.findAll(pageable);
    }

    @Override
    public ChucVu getOne(String idCV) {
        return chucVuRepo.findById(idCV).orElse(null);
    }

    @Override
    public void add(ChucVu chucVu) {
        chucVuRepo.save(chucVu);
    }

    @Override
    public void update(ChucVu chucVu, String idCV) {
        chucVu.setIdCV(idCV);
        chucVuRepo.save(chucVu);
    }

    @Override
    public Page<ChucVu> findAllByTen(String ten, Integer limit, Integer offset) {
       Pageable page = PageRequest.of(offset, limit);
       return chucVuRepo.findAllByTen(ten,page);
    }

}
