package com.springboot.bootstrap.controller;

import com.springboot.bootstrap.entity.DTO.SanPhamQrDTO;
import com.springboot.bootstrap.entity.HoaDon;
import com.springboot.bootstrap.entity.HoaDonChiTiet;
import com.springboot.bootstrap.entity.SanPhamCT;
import com.springboot.bootstrap.service.HoaDonChiTietService;
import com.springboot.bootstrap.service.HoaDonService;
import com.springboot.bootstrap.service.SanPhamCTService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Controller
@Transactional
@RequestMapping("/hoa_don_chi_tiet")
public class HoaDonChiTietController {
    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;
    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    private SanPhamCTService sanPhamCTService;
    @PostMapping("/add")
    public String addHdct(@RequestParam(name = "idhd") UUID idhd,
                          @RequestParam(name = "idspct") UUID idspct,
                          @RequestParam(name = "soLuong") Integer soLuong){
        SanPhamCT sanPhamCT = sanPhamCTService.getOne(idspct.toString());
        sanPhamCT.setSl(sanPhamCT.getSl()-soLuong);
        sanPhamCTService.update(sanPhamCT,idspct.toString());
        HoaDon hoaDon = hoaDonService.getOne(idhd);
        List<HoaDonChiTiet> list = hoaDonChiTietService.getList(idhd);
        if(list!=null){
            for(HoaDonChiTiet hoaDonChiTiet:list){
                if(hoaDonChiTiet.getSanPhamChiTiet().getId().equalsIgnoreCase(idspct.toString())){
                    hoaDonChiTiet.setSoLuong(hoaDonChiTiet.getSoLuong()+soLuong);
                    hoaDonChiTiet.setGia(hoaDonChiTiet.getSoLuong()*sanPhamCT.getGia());
                    hoaDonChiTietService.update(hoaDonChiTiet);
                    hoaDon.setGia(hoaDon.getGia()+hoaDonChiTiet.getGia());
                    if(hoaDon.getPhieuGiamGia()==null){
                        hoaDon.setThanhTien(hoaDon.getGia());
                        hoaDonService.add(hoaDon);
                    }else if(hoaDon.getPhieuGiamGia().getDonVi()==2){
                        hoaDon.setThanhTien(hoaDon.getGia()-hoaDon.getPhieuGiamGia().getGiaTriGiam());
                        hoaDonService.add(hoaDon);
                    }else if(hoaDon.getPhieuGiamGia().getDonVi()==1){
                        hoaDon.setThanhTien(hoaDon.getGia()*(100-hoaDon.getPhieuGiamGia().getGiaTriGiam())/100);
                        hoaDonService.add(hoaDon);
                    }
                    return "redirect:/giao_dich";
                }
            }
        }
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(sanPhamCT,hoaDon,sanPhamCT.getGia()*soLuong,soLuong);
        hoaDonChiTietService.add(hoaDonChiTiet);
        hoaDon.setGia(hoaDon.getGia()+hoaDonChiTiet.getGia());
        if(hoaDon.getPhieuGiamGia()==null){
            hoaDon.setThanhTien(hoaDon.getGia());
            hoaDonService.add(hoaDon);
        }else if(hoaDon.getPhieuGiamGia().getDonVi()==2){
            hoaDon.setThanhTien(hoaDon.getGia()-hoaDon.getPhieuGiamGia().getGiaTriGiam());
            hoaDonService.add(hoaDon);
        }else if(hoaDon.getPhieuGiamGia().getDonVi()==1){
            hoaDon.setThanhTien(hoaDon.getGia()*(100-hoaDon.getPhieuGiamGia().getGiaTriGiam())/100);
            hoaDonService.add(hoaDon);
        }
        return "redirect:/giao_dich";
    }

    @GetMapping("/delete/{idhdct}")
    public String delete(@PathVariable(name = "idhdct") String idhdct){
        Integer soLuong = hoaDonChiTietService.getOne(UUID.fromString(idhdct)).getSoLuong();
        Integer tong = hoaDonChiTietService.getOne(UUID.fromString(idhdct)).getSanPhamChiTiet().getSl();
        SanPhamCT sanPhamCT = hoaDonChiTietService.getOne(UUID.fromString(idhdct)).getSanPhamChiTiet();
        sanPhamCT.setSl(tong+soLuong);
        sanPhamCTService.update(sanPhamCT,sanPhamCT.getId());
        HoaDon hoaDon = hoaDonChiTietService.getOne(UUID.fromString(idhdct)).getHoaDon();
        hoaDon.setGia(hoaDon.getGia()-hoaDonChiTietService.getOne(UUID.fromString(idhdct)).getGia());
        if(hoaDon.getPhieuGiamGia()==null){
            hoaDon.setThanhTien(hoaDon.getGia());
            hoaDonService.add(hoaDon);
        }else if(hoaDon.getPhieuGiamGia().getDonVi()==2){
            hoaDon.setThanhTien(hoaDon.getGia()-hoaDon.getPhieuGiamGia().getGiaTriGiam());
            hoaDonService.add(hoaDon);
        }else if(hoaDon.getPhieuGiamGia().getDonVi()==1){
            hoaDon.setThanhTien(hoaDon.getGia()*(100-hoaDon.getPhieuGiamGia().getGiaTriGiam())/100);
            hoaDonService.add(hoaDon);
        }
        hoaDonService.add(hoaDon);
        hoaDonChiTietService.delete(UUID.fromString(idhdct));
        return "redirect:/giao_dich";
    }
    @PostMapping("/update/{idhdct}")
    public String update(@PathVariable(name = "idhdct") UUID idhdct,
                         @RequestParam(name = "soLuong") Integer soLuong){
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getOne(idhdct);
        HoaDon hoaDon = hoaDonChiTietService.getOne(idhdct).getHoaDon();
        SanPhamCT sanPhamCT = hoaDonChiTiet.getSanPhamChiTiet();
        sanPhamCT.setSl(sanPhamCT.getSl()+hoaDonChiTiet.getSoLuong()-soLuong);
        sanPhamCTService.update(sanPhamCT,sanPhamCT.getId());
        hoaDon.setGia(hoaDon.getGia()-hoaDonChiTiet.getGia());
        hoaDonChiTiet.setSoLuong(soLuong);
        hoaDonChiTiet.setGia(hoaDonChiTiet.getSoLuong()*sanPhamCT.getGia());
        hoaDon.setGia(hoaDon.getGia()+hoaDonChiTiet.getGia());
        if(hoaDon.getPhieuGiamGia()==null){
            hoaDon.setThanhTien(hoaDon.getGia());
            hoaDonService.add(hoaDon);
        }else if(hoaDon.getPhieuGiamGia().getDonVi()==2){
            hoaDon.setThanhTien(hoaDon.getGia()-hoaDon.getPhieuGiamGia().getGiaTriGiam());
            hoaDonService.add(hoaDon);
        }else if(hoaDon.getPhieuGiamGia().getDonVi()==1){
            hoaDon.setThanhTien(hoaDon.getGia()*(100-hoaDon.getPhieuGiamGia().getGiaTriGiam())/100);
            hoaDonService.add(hoaDon);
        }
        hoaDonService.add(hoaDon);
        hoaDonChiTietService.update(hoaDonChiTiet);
        return "redirect:/giao_dich";
    }
}
