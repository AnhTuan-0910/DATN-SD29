package com.springboot.bootstrap.controller;

import com.springboot.bootstrap.entity.HoaDonChiTiet;
import com.springboot.bootstrap.entity.SanPhamCT;
import com.springboot.bootstrap.service.HoaDonChiTietService;
import com.springboot.bootstrap.service.HoaDonService;
import com.springboot.bootstrap.service.SanPhamCTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;
@Controller
@RequestMapping("/hoa_don_chi_tiet")
public class HoaDonChiTietController {
    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;
    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    private SanPhamCTService sanPhamCTService;
    @PostMapping("/add")
    public String addHdct( @RequestParam(name = "idCTSP") UUID idSpct,
                          @RequestParam(name = "soLuong") Integer soLuong,
                          @RequestParam(name = "idhd") UUID idHoaDon ){
        SanPhamCT sanPhamCT = sanPhamCTService.getOne(idSpct.toString());
        sanPhamCT.setSl(sanPhamCT.getSl()-soLuong);
        sanPhamCTService.update(sanPhamCT,idSpct.toString());
        List<HoaDonChiTiet> list = hoaDonChiTietService.getList(idHoaDon);
        if(list!=null){
            for(HoaDonChiTiet hoaDonChiTiet:list){
                if(hoaDonChiTiet.getSanPhamChiTiet().getId().equalsIgnoreCase(idSpct.toString())){
                    hoaDonChiTiet.setSoLuong(hoaDonChiTiet.getSoLuong()+soLuong);
                    hoaDonChiTietService.update(hoaDonChiTiet);
                    return "redirect:/giao_dich";
                }
            }
        }
        hoaDonChiTietService.add(idHoaDon,idSpct,soLuong);
        return "redirect:/giao_dich";
    }
    @GetMapping("/delete/{idhdct}")
    public String delete(@PathVariable(name = "idhdct") UUID idhdct){
        Integer soLuong = hoaDonChiTietService.getOne(idhdct).getSoLuong();
        Integer tong = hoaDonChiTietService.getOne(idhdct).getSanPhamChiTiet().getSl();
        SanPhamCT sanPhamCT = hoaDonChiTietService.getOne(idhdct).getSanPhamChiTiet();
        sanPhamCT.setSl(tong+soLuong);
        sanPhamCTService.update(sanPhamCT,sanPhamCT.getId());
        hoaDonChiTietService.delete(idhdct);
        return "redirect:/giao_dich";
    }
    @PostMapping("/update/{idhdct}")
    public String update(@PathVariable(name = "idhdct") UUID idhdct,
                         @RequestParam(name = "soLuong") Integer soLuong){
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getOne(idhdct);
        SanPhamCT sanPhamCT = hoaDonChiTiet.getSanPhamChiTiet();
        sanPhamCT.setSl(sanPhamCT.getSl()+hoaDonChiTiet.getSoLuong()-soLuong);
        sanPhamCTService.update(sanPhamCT,sanPhamCT.getId());
        hoaDonChiTiet.setSoLuong(soLuong);
        hoaDonChiTietService.update(hoaDonChiTiet);
        return "redirect:/giao_dich";
    }
}
