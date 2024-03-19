//package com.springboot.bootstrap.service.impl;
//
//import com.springboot.bootstrap.entity.Anh;
//import com.springboot.bootstrap.entity.SanPhamCT;
//import com.springboot.bootstrap.repository.AnhRepo;
//import com.springboot.bootstrap.service.AnhService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AnhServiceImpl implements AnhService {
//    @Autowired
//    private AnhRepo anhRepo;
//
//    @Override
//    public Page<Anh> getAll(Pageable pageable) {
//        return anhRepo.findAll(pageable);
//    }
//
//    @Override
//    public Anh getBySPCT(SanPhamCT sanPhamCT) {
//        return anhRepo.findBySanPhamCT(sanPhamCT);
//    }
//
//    @Override
//    public void add(Anh anh) {
//        anhRepo.save(anh);
//    }
//}
