package com.springboot.bootstrap.service.impl;

import com.springboot.bootstrap.entity.SanPhamCT;
import com.springboot.bootstrap.repository.SanPhamCTRepo;
import com.springboot.bootstrap.service.SanPhamCTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SanPhamCTServiceImpl implements SanPhamCTService {
    @Autowired
    private SanPhamCTRepo sanPhamCTRepo;
    private static final String ma = "SPCT";
    private static int counter = 0;


    @Override
    public SanPhamCT getOne(String id) {
        return sanPhamCTRepo.findById(id).get();
    }

    @Override
    public SanPhamCT getOneByMa(String ma) {
        return sanPhamCTRepo.findByMa(ma);
    }

    @Override
    public Page<SanPhamCT> searchByFilter(String danhMucId, String kichThuocId, String mauSacId, String thuongHieuId, Pageable pageable) {
        return sanPhamCTRepo.findBySanPhamDanhMucIdAndKichThuocIdAndMauSacIdAndSanPhamThuongHieuId(danhMucId, kichThuocId, mauSacId, thuongHieuId, pageable);
    }

    @Override
    public Page<SanPhamCT> search(String keyword, Pageable pageable) {
        return sanPhamCTRepo.search(keyword, pageable);
    }

    @Override
    public Page<SanPhamCT> getAllBySP(String idSP, Pageable pageable) {
        return sanPhamCTRepo.findAllBySanPhamIdAndOrderByTenMS(idSP, pageable);
    }
    @Override
    public void update(SanPhamCT sanPhamCT, String id) {
        sanPhamCT.setId(id);
        sanPhamCTRepo.save(sanPhamCT);

    }

    @Override
    public Page<SanPhamCT> getAll(Pageable pageable) {
        return sanPhamCTRepo.findAll(pageable);
    }

    @Override
    public void add(SanPhamCT sanPhamCT) {
        sanPhamCTRepo.save(sanPhamCT);
    }

    @Override
    public String generateMaSPCT() {
        counter++;
        return ma + String.format("%03d", counter);
    }
}
