package com.springboot.bootstrap.repository;

import com.springboot.bootstrap.entity.HoaDon;
import com.springboot.bootstrap.entity.KhachHang;
import com.springboot.bootstrap.entity.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, UUID> {
    List<HoaDon> findAllByTinhTrang(Integer tinhTrang);

    Page<HoaDon> findAll(Pageable pageable);
    Page<HoaDon> findAllByMaContaining(String keyword,Pageable pageable);

    @Query("SELECT SUM(h.thanhTien) FROM HoaDon h")
    Double tongDoanhThu();

    //tính số đơn hàng
//    @Query("SELECT YEAR(h.taoLuc) AS Nam, MONTH(h.taoLuc) AS Thang, DAY(h.taoLuc) AS Ngay, COUNT(h) AS SoDonHang " +
//            "FROM HoaDon h " +
//            "WHERE h.taoLuc BETWEEN :tuNgay AND :denNgay " +
//            "GROUP BY YEAR(h.taoLuc), MONTH(h.taoLuc), DAY(h.taoLuc)")
//    List<Map<String, Object>> soDonHangTheoCacNgay(LocalDate tuNgay, LocalDate denNgay);
//
//    @Query("SELECT COUNT(h) AS SoDonHang " +
//            "FROM HoaDon h " +
//            "WHERE h.taoLuc BETWEEN :tuNgay AND :denNgay " +
//            "GROUP BY YEAR(h.taoLuc), MONTH(h.taoLuc)")
//    List<Map<String, Object>> soDonHangTheoCacThang(LocalDate tuNgay, LocalDate denNgay);
//
//    @Query("SELECT YEAR(h.taoLuc) AS Nam, COUNT(h) AS SoDonHang " +
//            "FROM HoaDon h " +
//            "WHERE h.taoLuc BETWEEN :tuNgay AND :denNgay " +
//            "GROUP BY YEAR(h.taoLuc)")
//    List<Map<String, Object>> soDonHangTheoCacNam(LocalDate tuNgay, LocalDate denNgay);


//    @Query("SELECT COUNT(h) FROM HoaDon h WHERE h.taoLuc BETWEEN :tuNgay AND :denNgay")
//    Integer soDonHangTheoKhoangThoiGian(LocalDate tuNgay, LocalDate denNgay);

    @Query("SELECT COUNT(h) FROM HoaDon h WHERE h.taoLuc = :taoLuc")
    Integer soDonHangTheoNgayTao(Date taoLuc);

    @Query("SELECT COUNT(h) FROM HoaDon h WHERE YEAR(h.taoLuc) = YEAR(:taoLuc) AND MONTH(h.taoLuc) = MONTH(:taoLuc)")
    Integer soDonHangTheoThangVaNam(Date taoLuc);

    @Query("SELECT COUNT(h) FROM HoaDon h WHERE YEAR(h.taoLuc) = YEAR(:taoLuc)")
    Integer soDonHangTheoNam(Date taoLuc);

    //tính doanh thu
//    @Query("SELECT COALESCE(SUM(h.thanhTien), 0) FROM HoaDon h WHERE h.ngayThanhToan BETWEEN :tuNgay AND :denNgay")
//    Double doanhThuTheoKhoangThoiGian(LocalDate tuNgay, LocalDate denNgay);

    @Query("SELECT COALESCE(SUM(h.thanhTien), 0) FROM HoaDon h WHERE h.taoLuc = :taoLuc")
    Integer doanhThuTheoNgayTao(Date taoLuc);

    @Query("SELECT COALESCE(SUM(h.thanhTien), 0) FROM HoaDon h WHERE YEAR(h.taoLuc) = YEAR(:taoLuc) AND MONTH(h.taoLuc) = MONTH(:taoLuc)")
    Integer doanhThuTheoThangVaNam(Date taoLuc);

    @Query("SELECT COALESCE(SUM(h.thanhTien), 0) FROM HoaDon h WHERE YEAR(h.taoLuc) = YEAR(:taoLuc)")
    Integer doanhThuTheoNam(Date taoLuc);

//    long countAllByTinhTrangAndHinhThucAndTaoLucBetween(int tinhTrang,int hinhThuc, Date tuNgay, Date denNgay);
//b   double sumThanhTienByNgayThanhToanBetween(Date startDate, Date endDate);

}
