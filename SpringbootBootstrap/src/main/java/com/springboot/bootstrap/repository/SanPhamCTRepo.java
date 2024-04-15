package com.springboot.bootstrap.repository;

import com.springboot.bootstrap.entity.KichThuoc;
import com.springboot.bootstrap.entity.MauSac;
import com.springboot.bootstrap.entity.SanPhamCT;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamCTRepo extends JpaRepository<SanPhamCT, String> {
    @Query("SELECT spct FROM SanPhamCT spct JOIN spct.sanPham sp  WHERE spct.ma = :maSPCT AND sp.trangThai = 1 AND spct.sl>0 ORDER BY spct.ma")
    SanPhamCT findByMaSPCT(String maSPCT);

    Page<SanPhamCT> findAll(Pageable pageable);

    @Query("SELECT spct FROM SanPhamCT spct JOIN spct.sanPham sp  WHERE sp.trangThai = 1 AND spct.sl>0 ORDER BY spct.ma")
    Page<SanPhamCT>  findBySL(Pageable pageable);

    @Query("SELECT spct FROM SanPhamCT spct JOIN spct.sanPham sp JOIN spct.mauSac ms WHERE sp.id = :sanPhamId  ORDER BY ms.ten")
    Page<SanPhamCT> findAllBySanPhamIdAndOrderByTenMS(String sanPhamId, Pageable pageable);

    @Query("SELECT spct FROM SanPhamCT spct JOIN spct.kichThuoc kt JOIN spct.mauSac ms JOIN spct.sanPham sp WHERE kt.id = :kichThuocId AND ms.id = :mauSacId AND sp.id= :sanPhamId")
    SanPhamCT findByMSAndKT(String kichThuocId, String mauSacId, String sanPhamId);


    @Query("SELECT p FROM SanPhamCT p JOIN p.sanPham sp WHERE LOWER(p.ma) LIKE LOWER(CONCAT('%', :keyword, '%')) AND sp.trangThai = 1 AND p.sl>0")
    Page<SanPhamCT> search(String keyword, Pageable pageable);

    @Query("SELECT spct FROM SanPhamCT spct " +
            "JOIN spct.sanPham sanPham " +
            "JOIN spct.kichThuoc kichThuoc " +
            "JOIN spct.mauSac mauSac " +
            "WHERE (:danhMucId IS NULL OR LOWER(sanPham.danhMuc.ten) LIKE LOWER(CONCAT('%', :danhMucId, '%'))) " +
            "AND (:kichThuocId IS NULL OR LOWER(kichThuoc.ten) LIKE LOWER(CONCAT('%', :kichThuocId, '%'))) " +
            "AND (:mauSacId IS NULL OR LOWER(mauSac.ten) LIKE LOWER(CONCAT('%', :mauSacId, '%'))) " +
            "AND (:thuongHieuId IS NULL OR LOWER(sanPham.thuongHieu.ten) LIKE LOWER(CONCAT('%', :thuongHieuId, '%')))" +
            " AND sanPham.trangThai = 1 AND spct.sl>0 ")
    Page<SanPhamCT> findBySPDMIdAndKTIdAndMSIdAndSPTHId(String danhMucId, String kichThuocId, String mauSacId, String thuongHieuId, Pageable pageable);



    @Query("SELECT spct FROM SanPhamCT spct " +
            "JOIN spct.sanPham sanPham " +
            "JOIN spct.kichThuoc kichThuoc " +
            "JOIN spct.mauSac mauSac " +
            "WHERE (:kichThuocId IS NULL OR LOWER(kichThuoc.ten) LIKE LOWER(CONCAT('%', :kichThuocId, '%'))) " +
            "AND (:mauSacId IS NULL OR LOWER(mauSac.ten) LIKE LOWER(CONCAT('%', :mauSacId, '%'))) " +
            " AND sanPham.id = :sanPhamId ORDER BY spct.ma")
    Page<SanPhamCT> findByMSAndKTSPCT( String kichThuocId, String mauSacId, String sanPhamId, Pageable pageable);


    @Query("SELECT p FROM SanPhamCT p JOIN p.sanPham sp WHERE LOWER(p.ma) LIKE LOWER(CONCAT('%', :keyword, '%')) AND sp.id = :sanPhamId ORDER BY p.ma")
    Page<SanPhamCT> searchbyKeyWord(String keyword, Pageable pageable);

    //onl
    @Query("SELECT spct FROM SanPhamCT spct JOIN spct.sanPham sp  WHERE sp.id = :sanPhamId  ")
    List<SanPhamCT> findAllBySP(String sanPhamId);

    @Query("SELECT MAX(spct.gia) FROM SanPhamCT spct JOIN spct.sanPham sp  WHERE sp.id = :sanPhamId  ")
    String giaMax (String sanPhamId);

    @Query("SELECT MIN(spct.gia) FROM SanPhamCT spct JOIN spct.sanPham sp  WHERE sp.id = :sanPhamId  ")
    String giaMin (String sanPhamId);

    @Query("SELECT DISTINCT spct.mauSac FROM SanPhamCT spct JOIN spct.sanPham sp  WHERE sp.id = :sanPhamId  ")
    List<MauSac> mauSP(String sanPhamId);

    @Query("SELECT DISTINCT spct.kichThuoc FROM SanPhamCT spct JOIN spct.sanPham sp  WHERE sp.id = :sanPhamId  ")
    List<KichThuoc> sizeSP(String sanPhamId);

    @Query("SELECT  spct FROM SanPhamCT spct JOIN spct.sanPham sp JOIN spct.mauSac ms  JOIN spct.kichThuoc kt  WHERE sp.id = :sanPhamId AND ms.id=:idMS")
    List<SanPhamCT> findKTByMS(String sanPhamId,String idMS);

    @Query("SELECT  spct FROM SanPhamCT spct JOIN spct.sanPham sp JOIN spct.mauSac ms  JOIN spct.kichThuoc kt  WHERE sp.id = :sanPhamId AND ms.id=:idMS AND kt.id=:idKT")
    SanPhamCT findSPCTByKTAndMS(String sanPhamId,String idMS,String idKT);
}
