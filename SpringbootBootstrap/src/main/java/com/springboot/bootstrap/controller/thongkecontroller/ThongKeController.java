package com.springboot.bootstrap.controller.thongkecontroller;

import com.springboot.bootstrap.entity.FormatHelper;
import com.springboot.bootstrap.repository.HoaDonChiTietRepository;
import com.springboot.bootstrap.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

@Controller
@RequestMapping("/home")
public class ThongKeController {
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

//    @Autowired
//    private NgayThang ngayThang;

    public Date getNgayHomQua(Date ngayHienTai) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(ngayHienTai);
        if (cal.get(Calendar.DAY_OF_MONTH) == 1) {
            if (cal.get(Calendar.MONTH) == Calendar.JANUARY) {
                cal.add(Calendar.YEAR, -1);
                cal.set(Calendar.MONTH, Calendar.DECEMBER);
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            } else {
                cal.add(Calendar.MONTH, -1); // Trừ một tháng
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            }
        } else {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        return new Date(cal.getTimeInMillis());
    }

    public Date getThangTruoc(Date ngayHienTai) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(ngayHienTai);
        int thangHienTai = cal.get(Calendar.MONTH);
        if (thangHienTai == Calendar.JANUARY) {
            cal.add(Calendar.YEAR, -1);
            cal.set(Calendar.MONTH, Calendar.DECEMBER);
        } else {
            cal.add(Calendar.MONTH, -1);
        }
        return new Date(cal.getTimeInMillis());
    }

    public Date getNamTruoc(Date ngayHienTai) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(ngayHienTai);
        cal.add(Calendar.YEAR, -1);

        return new Date(cal.getTimeInMillis());
    }

    @GetMapping
    public String getAll(Model model){
        Date homNay = Date.valueOf(LocalDate.now());
        FormatHelper formatHelper=new FormatHelper();
        Integer soDon=0;
        Integer sanPhamDaBan=0;
        Integer doanhThu=0;
        Integer ssSoDon=0;
        Integer ssSanPhamDaBan=0;
        Integer ssDoanhThu=0;
        if (hoaDonRepository.soDonHangTheoNgayTao(homNay)!=null){
            soDon=hoaDonRepository.soDonHangTheoNgayTao(homNay);
            ssSoDon=soDon- hoaDonRepository.soDonHangTheoNgayTao(getNgayHomQua(homNay));
        }

        if (hoaDonChiTietRepository.soSanPhamDaBanTheoNgay(homNay)!=null){
            sanPhamDaBan= hoaDonChiTietRepository.soSanPhamDaBanTheoNgay(homNay);
            ssSanPhamDaBan= sanPhamDaBan-hoaDonChiTietRepository.soSanPhamDaBanTheoNgay(getNgayHomQua(homNay));
        }
        if (hoaDonRepository.doanhThuTheoNgayTao(homNay)!=null){
            doanhThu= hoaDonRepository.doanhThuTheoNgayTao(homNay);
            ssDoanhThu=doanhThu-(hoaDonRepository.doanhThuTheoNgayTao(getNgayHomQua(homNay)));
        }

//        Integer soDon=hoaDonRepository.soDonHangTheoNgayTao(homNay);
//        Integer ssSoDon=soDon- hoaDonRepository.soDonHangTheoNgayTao(getNgayHomQua(homNay));
//        Integer sanPhamDaBan= hoaDonChiTietRepository.soSanPhamDaBanTheoNgay(homNay);
//        Integer ssSanPhamDaBan= sanPhamDaBan-hoaDonChiTietRepository.soSanPhamDaBanTheoNgay(getNgayHomQua(homNay));
//        Double doanhThu= hoaDonRepository.doanhThuTheoNgayTao(homNay);
//        Double ssDoanhThu=doanhThu-(hoaDonRepository.doanhThuTheoNgayTao(getNgayHomQua(homNay)));

        model.addAttribute("soDon",soDon);
        model.addAttribute("sanPhamDaBan",sanPhamDaBan);
        model.addAttribute("doanhThu",doanhThu);
        model.addAttribute("ssSoDon",ssSoDon);
        model.addAttribute("ssSanPhamDaBan",ssSanPhamDaBan);
        model.addAttribute("ssDoanhThu",ssDoanhThu);
        model.addAttribute("formatHelper",formatHelper);
        return "/pages/landing_page";
    }

    @GetMapping("/filter")
    public String filter(Model model, @RequestParam(value = "thoiGian",required = false) String thoiGian
            , @RequestParam(value = "tuNgay",required = false) String tuNgay
            , @RequestParam(value = "denNgay",required = false) String denNgay) {
        int soDon=0;
        int sanPhamDaBan=0;
        double doanhThu=0;
        int ssSoDon=0;
        int ssSanPhamDaBan=0;
        double ssDoanhThu=0;
        String title="Thông Số Hôm Nay";
        String ssTitle="So Sánh Với Hôm Qua";
        String titleSoDon="Thống Kê Số Đơn Đặt Theo";
        String titleSoSanPham="Thống Kê Số Sản Phẩm Đã Bán Theo";
        String titleDoanhThu="Thống Kê Doanh Thu Theo";
        Date homNay = Date.valueOf(LocalDate.now());
        FormatHelper formatHelper=new FormatHelper();
            if (thoiGian.equals("1")||thoiGian==""){
                if (hoaDonRepository.soDonHangTheoNgayTao(homNay)!=null){
                    soDon=hoaDonRepository.soDonHangTheoNgayTao(homNay);
                    ssSoDon=soDon- hoaDonRepository.soDonHangTheoNgayTao(getNgayHomQua(homNay));
                }
                if (hoaDonChiTietRepository.soSanPhamDaBanTheoNgay(homNay)!=null){
                    sanPhamDaBan= hoaDonChiTietRepository.soSanPhamDaBanTheoNgay(homNay);
                    ssSanPhamDaBan= sanPhamDaBan-hoaDonChiTietRepository.soSanPhamDaBanTheoNgay(getNgayHomQua(homNay));
                }
                if (hoaDonRepository.doanhThuTheoNgayTao(homNay)!=null){
                    doanhThu= hoaDonRepository.doanhThuTheoNgayTao(homNay);
                    ssDoanhThu=doanhThu-(hoaDonRepository.doanhThuTheoNgayTao(getNgayHomQua(homNay)));
                }
                title="Thông Số Hôm Nay";
                ssTitle="So Sánh Với Hôm Qua";
                titleSoDon=titleSoDon+" Ngày";
                titleSoSanPham=titleSoSanPham+" Ngày";
                titleDoanhThu=titleDoanhThu+" Ngày";
                // Tổng đơn trong ngày hôm nay
            }else if (thoiGian.equals("2")){
                // Tổng đơn trong tháng này
                if (hoaDonRepository.soDonHangTheoThangVaNam(homNay)!=null){
                    soDon=hoaDonRepository.soDonHangTheoThangVaNam(homNay);
                    ssSoDon=soDon- hoaDonRepository.soDonHangTheoThangVaNam(getThangTruoc(homNay));
                }
                if (hoaDonChiTietRepository.soSanPhamDaBanTheoThangVaNam(homNay)!=null){
                    sanPhamDaBan= hoaDonChiTietRepository.soSanPhamDaBanTheoThangVaNam(homNay);
                    ssSanPhamDaBan= sanPhamDaBan-hoaDonChiTietRepository.soSanPhamDaBanTheoThangVaNam(getThangTruoc(homNay));
                }
                if (hoaDonRepository.doanhThuTheoThangVaNam(homNay)!=null){
                    doanhThu= hoaDonRepository.doanhThuTheoThangVaNam(homNay);
                    ssDoanhThu=doanhThu-hoaDonRepository.doanhThuTheoThangVaNam(getThangTruoc(homNay));
                }
                title="Thông Số Tháng Này";
                ssTitle="So Sánh Với Tháng Trước";
                titleSoDon=titleSoDon+" Tháng";
                titleSoSanPham=titleSoSanPham+" Tháng";
                titleDoanhThu=titleDoanhThu+" Tháng";
            }else if (thoiGian.equals("3")){
                // Tổng đơn trong năm nay
                if (hoaDonRepository.soDonHangTheoNam(homNay)!=null){
                    soDon=hoaDonRepository.soDonHangTheoNam(homNay);
                    ssSoDon=soDon- hoaDonRepository.soDonHangTheoNam(getNamTruoc(homNay));
                }
                if (hoaDonChiTietRepository.soSanPhamDaBanTheoNam(homNay)!=null){
                    sanPhamDaBan= hoaDonChiTietRepository.soSanPhamDaBanTheoNam(homNay);
                    ssSanPhamDaBan= sanPhamDaBan-hoaDonChiTietRepository.soSanPhamDaBanTheoNam(getNamTruoc(homNay));
                }
                if (hoaDonRepository.doanhThuTheoNam(homNay)!=null){
                    doanhThu= hoaDonRepository.doanhThuTheoNam(homNay);
                    ssDoanhThu=doanhThu-hoaDonRepository.doanhThuTheoNam(getNamTruoc(homNay));
                }
                title="Thông Số Năm Nay";
                ssTitle="So Sánh Với Năm Trước";
                titleSoDon=titleSoDon+" Năm";
                titleSoSanPham=titleSoSanPham+" Năm";
                titleDoanhThu=titleDoanhThu+" Năm";
            }

        model.addAttribute("soDon",soDon);
        model.addAttribute("sanPhamDaBan",sanPhamDaBan);
        model.addAttribute("doanhThu",doanhThu);
        model.addAttribute("ssSoDon",ssSoDon);
        model.addAttribute("ssSanPhamDaBan",ssSanPhamDaBan);
        model.addAttribute("ssDoanhThu",ssDoanhThu);
        model.addAttribute("formatHelper",formatHelper);
        model.addAttribute("title",title);
        model.addAttribute("ssTitle",ssTitle);
        model.addAttribute("thoiGian",thoiGian);
        model.addAttribute("tuNgay",tuNgay);
        model.addAttribute("denNgay",denNgay);
        model.addAttribute("titleSoDon",titleSoDon);
        model.addAttribute("titleSoSanPham",titleSoSanPham);
        model.addAttribute("titleDoanhThu",titleDoanhThu);
        return "/pages/landing_page";
    }
}
