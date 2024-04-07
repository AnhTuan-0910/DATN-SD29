package com.springboot.bootstrap.controller;

import com.springboot.bootstrap.entity.DTO.GioHangDTO;
import com.springboot.bootstrap.entity.DTO.ResponseGioHangDTO;
import com.springboot.bootstrap.entity.SanPhamCT;
import com.springboot.bootstrap.service.SanPhamCTService;
import com.springboot.bootstrap.utility.ListGioHangShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GioHangRestController {
    @Autowired
    private SanPhamCTService sanPhamCTService;
    @Autowired
    private ListGioHangShopService listGioHangShopService;
    @PostMapping("/validateGioHang")
    public ResponseGioHangDTO validateGioHang(@RequestBody List<GioHangDTO> listGioHangDTO){
        for(GioHangDTO gh:listGioHangDTO){
            SanPhamCT sanPhamCT = sanPhamCTService.getOne(gh.getIdspct().toString());
            if(gh.getSoLuong()>sanPhamCT.getSl()){
                return ResponseGioHangDTO.builder().idspct(gh.getIdspct().toString()).soLuong(sanPhamCT.getSl()).build();
            }
        }
        listGioHangShopService.setList(listGioHangDTO);
        return ResponseGioHangDTO.builder().status(200).build();
    }
}
