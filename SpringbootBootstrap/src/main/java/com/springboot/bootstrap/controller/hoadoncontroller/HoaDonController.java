package com.springboot.bootstrap.controller.hoadoncontroller;

import com.springboot.bootstrap.entity.DTO.HoaDonDTO;
import com.springboot.bootstrap.entity.HoaDon;
import com.springboot.bootstrap.entity.ThuongHieu;
import com.springboot.bootstrap.service.impl.HoaDonChiTietServiceImpl;
import com.springboot.bootstrap.service.impl.HoaDonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/hoa_don")
public class HoaDonController {
    List<String> listTinhTrang = new ArrayList();
    public HoaDonController() {
        listTinhTrang.add("Chờ xác nhận");
        listTinhTrang.add("Chờ lấy hàng");
        listTinhTrang.add("Đang giao");
        listTinhTrang.add("Hoàn thành");
        listTinhTrang.add("Đã huỷ");
    }

    @Autowired
    private HoaDonServiceImpl hoaDonService;
    @Autowired
    private HoaDonChiTietServiceImpl hoaDonChiTietService;
    @GetMapping("")
    public String view(Model model,@RequestParam("p") Optional<Integer> p) {
        Page<HoaDon> listTH=hoaDonService.getAll(PageRequest.of(p.orElse(0), 5));
        model.addAttribute("listHoaDon",listTH);
        model.addAttribute("hoaDon",new HoaDonDTO());
        model.addAttribute("listTinhTrang",listTinhTrang);
        return "/pages/hoa_don";
    }
    @GetMapping("/view/{id}")
    public String viewOne(Model model,@RequestParam("p") Optional<Integer> p, @PathVariable("id") UUID id){
        model.addAttribute("hoaDon",hoaDonService.getOne(id));
        model.addAttribute("listHoaDonChiTiet",hoaDonChiTietService.getPage(id,PageRequest.of(p.orElse(0), 5)));
        return "/pages/hoa_don_chi_tiet";
    }
    @GetMapping("/search")
    public String search(@RequestParam("p") Optional<Integer> page,
                         @RequestParam(value = "keyword", required = false) String keyword,
                         Model model){
        Page<HoaDon> listHoaDon = hoaDonService.getListSearch(keyword,PageRequest.of(page.orElse(0), 5));
        model.addAttribute("listHoaDon",listHoaDon);
        model.addAttribute("hoaDon",new HoaDonDTO());
        model.addAttribute("listTinhTrang",listTinhTrang);
        return "/pages/hoa_don";
    }
    @GetMapping("/thanh_toan/{idhd}")
    public String thanhToan(@PathVariable("idhd") UUID id){
        HoaDon hoaDon = hoaDonService.getOne(id);
        hoaDon.setTinhTrang(4);
        hoaDonService.add(hoaDon);
        return "redirect:/giao_dich";
    }
}
