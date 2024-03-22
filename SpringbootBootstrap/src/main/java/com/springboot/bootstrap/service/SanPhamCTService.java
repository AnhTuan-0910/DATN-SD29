package com.springboot.bootstrap.service;

import com.springboot.bootstrap.entity.SanPhamCT;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SanPhamCTService {
    Page<SanPhamCT> searchByFilter(String danhMucId, String kichThuocId, String mauSacId, String thuongHieuId, Pageable pageable);

    SanPhamCT getOneByMa(String ma);


    void add(SanPhamCT sanPhamCT);

    SanPhamCT getOne(String id);

    void update(SanPhamCT sanPhamCT, String id);

    Page<SanPhamCT> getAllBySP(String idSP, Pageable pageable);

    SanPhamCT getByMSAndKT(String idKT,String idMS,String idSP);

    public Page<SanPhamCT> search(String keyword, Pageable pageable);

    Page<SanPhamCT> getAll(Pageable pageable);

    Page<SanPhamCT> getBySL(Pageable pageable);
}
