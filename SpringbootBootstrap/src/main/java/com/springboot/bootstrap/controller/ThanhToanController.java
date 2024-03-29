package com.springboot.bootstrap.controller;

import com.springboot.bootstrap.entity.*;
import com.springboot.bootstrap.entity.DTO.SanPhamQrDTO;
import com.springboot.bootstrap.repository.HoaDonRepository;
import com.springboot.bootstrap.repository.KhachHangRepository;
import com.springboot.bootstrap.repository.PhieuGiamGiaRepository;
import com.springboot.bootstrap.service.DanhMucService;
import com.springboot.bootstrap.service.HoaDonChiTietService;
import com.springboot.bootstrap.service.HoaDonService;
import com.springboot.bootstrap.service.KhachHangService;
import com.springboot.bootstrap.service.KichThuocService;
import com.springboot.bootstrap.service.MauSacService;
import com.springboot.bootstrap.service.SanPhamCTService;
import com.springboot.bootstrap.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/giao_dich")
public class ThanhToanController {
    @Autowired
    private SanPhamCTService sanPhamCTService;
    @Autowired
    private KichThuocService kichThuocService;
    @Autowired
    private MauSacService mauSacService;
    @Autowired
    private DanhMucService danhMucService;
    @Autowired
    private ThuongHieuService thuongHieuService;
    @Autowired
    private PhieuGiamGiaRepository phieuGiamGiaRepository;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;
    @GetMapping("")
    public String getAll(Model model) {
        List<DanhMuc> listDM = danhMucService.findAllByTrangThai();
        List<ThuongHieu> listTH = thuongHieuService.findAllByTrangThai();
        List<KichThuoc> listKT = kichThuocService.findAllByTrangThai();
        List<MauSac> listMS = mauSacService.findAllByTrangThai();
        List<PhieuGiamGia> listPGG = phieuGiamGiaRepository.findAll();
        List<KhachHang> khachHang = khachHangService.findAllByTrangThai();
        List<HoaDon> listHD = hoaDonService.renderTab();
        List<PhieuGiamGia> phieuGiamGia=phieuGiamGiaRepository.findAllByTrangThai(1);
        model.addAttribute("listVoucher", phieuGiamGia);
        model.addAttribute("listKH", khachHang);
        model.addAttribute("listHD", listHD);
        model.addAttribute("listTH", listTH);
        model.addAttribute("listDM", listDM);
        model.addAttribute("listKT", listKT);
        model.addAttribute("listMS", listMS);
        model.addAttribute("spqr",new SanPhamQrDTO());
        return "/pages/giao_dich";
    }

    @PostMapping("/add_tab")
    public String addTab(@ModelAttribute("hda") HoaDon hoaDon) {
        hoaDon = HoaDon.builder().tinhTrang(1).gia(0.0).thanhTien(0.0).build();
        hoaDonService.add(hoaDon);
        return "redirect:/giao_dich";
    }

    @PostMapping("/add_voucher_to_hoa_don/{id}")
    public String addVoucherToHoaDon(@RequestParam(value = "id_pgg",required = false) UUID id_pgg,
                                     @PathVariable(value = "id", required = false) UUID id,
                                     @ModelAttribute HoaDon hoaDon) {
        HoaDon existingHoaDon = hoaDonRepository.findById(id).orElse(null);
        PhieuGiamGia existingPhieuGiamGia = phieuGiamGiaRepository.findById(id_pgg).orElse(null);
        if (existingHoaDon.getPhieuGiamGia()==null){
            existingPhieuGiamGia.setSoLuong(existingPhieuGiamGia.getSoLuong()-1);
            existingHoaDon.setPhieuGiamGia(existingPhieuGiamGia);
            if(existingPhieuGiamGia.getDonVi()==2){
                existingHoaDon.setThanhTien(existingHoaDon.getGia()-existingPhieuGiamGia.getGiaTriGiam());
            }else {
                existingHoaDon.setThanhTien(existingHoaDon.getGia()*(100-existingPhieuGiamGia.getGiaTriGiam())/100);
            }
            hoaDonRepository.save(existingHoaDon);
        }else {
            PhieuGiamGia oldPhieuGiamGia=existingHoaDon.getPhieuGiamGia();
            oldPhieuGiamGia.setSoLuong(oldPhieuGiamGia.getSoLuong()+1);
            existingPhieuGiamGia.setSoLuong(existingPhieuGiamGia.getSoLuong()-1);
            existingHoaDon.setPhieuGiamGia(existingPhieuGiamGia);
            if(existingPhieuGiamGia.getDonVi()==2){
                existingHoaDon.setThanhTien(existingHoaDon.getGia()-existingPhieuGiamGia.getGiaTriGiam());
            }else {
                existingHoaDon.setThanhTien(existingHoaDon.getGia()*(100-existingPhieuGiamGia.getGiaTriGiam())/100);
            }
            phieuGiamGiaRepository.save(oldPhieuGiamGia);
            hoaDonRepository.save(existingHoaDon);
        }
        return "redirect:/giao_dich";
    }

    @PostMapping("/add_khachHang_to_hoa_don/{idKH}")
    public String addKhachHangToHoaDon(@RequestParam(value = "idKhachHang",required = false) String idKhachHang,
                                     @PathVariable(value = "idKH", required = false) UUID id,
                                     @ModelAttribute HoaDon hoaDon) {
        HoaDon existingHoaDon = hoaDonRepository.findById(id).orElse(null);
        KhachHang existingKhachHang = khachHangService.getOne(idKhachHang);
        if (existingHoaDon.getKhachHang()==null){
            existingKhachHang.setTen(existingKhachHang.getTen());
            existingHoaDon.setKhachHang(existingKhachHang);
            hoaDonRepository.save(existingHoaDon);
        }
        return "redirect:/giao_dich";
    }

    @GetMapping("/spct")
    @ResponseBody
    public Page<SanPhamCT> paginate(@RequestParam("p") int page) {
        return sanPhamCTService.getBySL(PageRequest.of(page, 5));
    }
    @GetMapping("/deleteTab/")
    public String deleteTab(@RequestParam("id") String id) {
        HoaDon hoaDon = hoaDonService.getOne(UUID.fromString(id));
        hoaDon.setTinhTrang(5);
        hoaDonService.add(hoaDon);
        List<HoaDonChiTiet> list = hoaDonChiTietService.getList(UUID.fromString(id));
        for(HoaDonChiTiet hdct:list){
            SanPhamCT sanPhamCT = sanPhamCTService.getOne(hdct.getSanPhamChiTiet().getId());
            sanPhamCT.setSl(sanPhamCT.getSl()-hdct.getSoLuong());
            sanPhamCTService.add(sanPhamCT);
        }
        if(hoaDon.getPhieuGiamGia()!=null){
            PhieuGiamGia phieuGiamGia = hoaDon.getPhieuGiamGia();
            phieuGiamGia.setSoLuong(phieuGiamGia.getSoLuong()+1);
            phieuGiamGiaRepository.save(phieuGiamGia);
        }
        return "redirect:/giao_dich";
    }

    @GetMapping("/search")
    @ResponseBody
    public Page<SanPhamCT> search(@RequestParam("keyword") String keyword, @RequestParam("p") int page) {
        return sanPhamCTService.search(keyword, PageRequest.of(page, 5));
    }

    @GetMapping("/viewOne/")
    @ResponseBody
    public SanPhamCT viewOne(String id) {
        return sanPhamCTService.getOne(id);
    }

    @GetMapping("/viewOneByMa")
    @ResponseBody
    public SanPhamCT viewOneByMa(@RequestParam("maSPCTSearch") String ma) {
        return sanPhamCTService.getOneByMa(ma);
    }

    @GetMapping("/filter")
    @ResponseBody
    public Page<SanPhamCT> filter(@RequestParam("danhMucSearch") String danhMucId,
                                  @RequestParam("kichThuocSearch") String kichThuocId,
                                  @RequestParam("mauSacSearch") String mauSacId,
                                  @RequestParam("thuongHieuSearch") String thuongHieuId,
                                  @RequestParam("p") int page) {

        return sanPhamCTService.searchByFilter(danhMucId, kichThuocId, mauSacId, thuongHieuId, PageRequest.of(page, 5));
    }
}
