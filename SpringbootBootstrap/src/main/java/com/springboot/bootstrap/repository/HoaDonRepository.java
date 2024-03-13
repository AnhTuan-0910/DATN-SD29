package com.springboot.bootstrap.repository;

import com.springboot.bootstrap.entity.HoaDon;
import com.springboot.bootstrap.entity.KhachHang;
import com.springboot.bootstrap.entity.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, UUID> {
    List<HoaDon> findAllByTinhTrang(Integer tinhTrang);

    Page<HoaDon> findAll(Pageable pageable);
    List<HoaDon> findAllByMaAndNhanVienIsNullAndKhachHangAndNgayThanhToanAndNgayNhanAndNgayShipAndTinhTrang(
            String ma,KhachHang khachHang,Date ngayThanhToan,Date ngayNhan,Date ngayShip,Integer tinhTrang,Pageable pageable
    );
    List<HoaDon> findAllByMaAndNhanVienIsNullAndKhachHangIsNullAndNgayThanhToanAndNgayNhanAndNgayShipAndTinhTrang(
            String ma,Date ngayThanhToan,Date ngayNhan,Date ngayShip,Integer tinhTrang,Pageable pageable
    );
    List<HoaDon> findAllByMaAndNhanVienAndKhachHangIsNullAndNgayThanhToanAndNgayNhanAndNgayShipAndTinhTrang(
            String ma,NhanVien nhanVien,Date ngayThanhToan,Date ngayNhan,Date ngayShip,Integer tinhTrang,Pageable pageable
    );
    Page<HoaDon> findAllByMaAndNhanVienAndKhachHangAndNgayThanhToanAndNgayNhanAndNgayShipAndTinhTrang
            (String ma,NhanVien nhanVien,KhachHang khachHang,Date ngayThanhToan,Date ngayNhan,Date ngayShip,Integer tinhTrang,Pageable pageable);
}
