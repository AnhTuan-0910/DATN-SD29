package com.springboot.bootstrap.controller;

import com.springboot.bootstrap.entity.DTO.GioHangChiTietDTO;
import com.springboot.bootstrap.entity.DTO.GioHangDTO;
import com.springboot.bootstrap.entity.SanPhamCT;
import com.springboot.bootstrap.service.SanPhamCTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/gio-hang")
@Controller
public class GioHangController {
    private List<GioHangChiTietDTO> list1;
    private List<GioHangChiTietDTO> list2;
    private List<GioHangChiTietDTO> list3;
    private List<GioHangChiTietDTO> list4;
    private List<GioHangChiTietDTO> list5;
    private List<GioHangChiTietDTO> list6;
    @Autowired
    private SanPhamCTService sanPhamCTService;
    @PostMapping("/add/{idGioHang}")
    public String addGiohang(@PathVariable(name = "idGioHang") String idGioHang,
                             @RequestParam(name = "code") String code,
                             @RequestParam(name = "count") Integer count,
                             Model model){
        SanPhamCT spct = sanPhamCTService.getOneByMa(code);
        GioHangChiTietDTO gioHangChiTietDTO = new GioHangChiTietDTO(code,spct.getSanPham().getTen(),spct.getMauSac().getTen(),spct.getKichThuoc().getTen(),count,spct.getGia());
        chooseList(idGioHang).add(gioHangChiTietDTO);
        model.addAttribute("listGioHang",chooseList(idGioHang));
        return null;
    }
    @GetMapping("/delete/{idGioHang}/{idghct}")
    public String deleteGioHangChiTiet(@PathVariable(name = "idGioHang") String idGioHang,
                                       @PathVariable(name = "idghct") String idghct){
        for(GioHangChiTietDTO gioHangChiTietDTO:chooseList(idGioHang)){
            if(gioHangChiTietDTO.getMa().equals(idghct)){
                chooseList(idGioHang).remove(gioHangChiTietDTO);
            }
        }
        return null;
    }
    private List<GioHangChiTietDTO> chooseList(String id){
        switch (id){
            case "tabContent1":return list1;
            case "tabContent2":return list2;
            case "tabContent3":return list3;
            case "tabContent4":return list4;
            case "tabContent5":return list5;
            case "tabContent6":return list6;
            default:return null;
        }
    }
}
