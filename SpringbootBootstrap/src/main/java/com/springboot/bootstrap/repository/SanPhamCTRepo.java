package com.springboot.bootstrap.repository;

import com.springboot.bootstrap.entity.SanPhamCT;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamCTRepo extends JpaRepository<SanPhamCT, String> {
    SanPhamCT findByMa(String ma);

    Page<SanPhamCT> findAll(Pageable pageable);

    @Query("SELECT spct FROM SanPhamCT spct JOIN spct.sanPham sp JOIN spct.mauSac ms WHERE sp.id = :sanPhamId ORDER BY ms.ten")
    Page<SanPhamCT> findAllBySanPhamIdAndOrderByTenMS(String sanPhamId, Pageable pageable);

    @Query("SELECT spct FROM SanPhamCT spct JOIN spct.kichThuoc kt JOIN spct.mauSac ms JOIN spct.sanPham sp WHERE kt.id = :kichThuocId AND ms.id = :mauSacId AND sp.id= :sanPhamId")
    SanPhamCT findByMauSacAndKichThuoc(String kichThuocId, String mauSacId,String sanPhamId);


    @Query("SELECT p FROM SanPhamCT p WHERE LOWER(p.ma) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<SanPhamCT> search(String keyword, Pageable pageable);

    @Query("SELECT spct FROM SanPhamCT spct " +
            "JOIN spct.sanPham sanPham " +
            "JOIN spct.kichThuoc kichThuoc " +
            "JOIN spct.mauSac mauSac " +
            "WHERE (:danhMucId IS NULL OR LOWER(sanPham.danhMuc.ten) LIKE LOWER(CONCAT('%', :danhMucId, '%'))) " +
            "AND (:kichThuocId IS NULL OR LOWER(kichThuoc.ten) LIKE LOWER(CONCAT('%', :kichThuocId, '%'))) " +
            "AND (:mauSacId IS NULL OR LOWER(mauSac.ten) LIKE LOWER(CONCAT('%', :mauSacId, '%'))) " +
            "AND (:thuongHieuId IS NULL OR LOWER(sanPham.thuongHieu.ten) LIKE LOWER(CONCAT('%', :thuongHieuId, '%')))")
    Page<SanPhamCT> findBySanPhamDanhMucIdAndKichThuocIdAndMauSacIdAndSanPhamThuongHieuId(String danhMucId, String kichThuocId, String mauSacId, String thuongHieuId, Pageable pageable);

}
