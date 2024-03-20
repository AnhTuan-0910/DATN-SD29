package com.springboot.bootstrap.controller;


import com.springboot.bootstrap.entity.DTO.SanPhamDTO;
import com.springboot.bootstrap.entity.DanhMuc;
import com.springboot.bootstrap.entity.KichThuoc;
import com.springboot.bootstrap.entity.MauSac;
import com.springboot.bootstrap.entity.SanPham;
import com.springboot.bootstrap.entity.SanPhamCT;
import com.springboot.bootstrap.entity.ThuongHieu;
import com.springboot.bootstrap.service.DanhMucService;
import com.springboot.bootstrap.service.KichThuocService;
import com.springboot.bootstrap.service.MauSacService;
import com.springboot.bootstrap.service.SanPhamCTService;
import com.springboot.bootstrap.service.SanPhamService;
import com.springboot.bootstrap.service.ThuongHieuService;
import com.springboot.bootstrap.utility.QRCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/them_sp")
public class ThemSPController {
    @Autowired
    private SanPhamCTService sanPhamCTService;
    @Autowired
    private KichThuocService kichThuocService;
    @Autowired
    private MauSacService mauSacService;
    @Autowired
    private DanhMucService danhMucService;
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private ThuongHieuService thuongHieuService;
    @Autowired
    private QRCodeGenerator qrCodeGenerator;


    @GetMapping("")
    public String getAll(Model model) {
        List<DanhMuc> listDM = danhMucService.findAllByTrangThai();
        List<ThuongHieu> listTH = thuongHieuService.findAllByTrangThai();
        List<KichThuoc> listKT = kichThuocService.findAllByTrangThai();
        List<MauSac> listMS = mauSacService.findAllByTrangThai();
        model.addAttribute("listTH", listTH);
        model.addAttribute("listDM", listDM);
        model.addAttribute("listKT", listKT);
        model.addAttribute("listMS", listMS);
        return "/pages/them_sp";
    }

    @PostMapping("/addSPCT")
    public ResponseEntity<Map<String, String>> addSanPham(@RequestBody SanPhamDTO sanPhamDTO) {
        SanPham sanPham = SanPham.builder()
                .ma(sanPhamService.generateMaSP())
                .ten(sanPhamDTO.getTen())
                .danhMuc(DanhMuc.builder().id(sanPhamDTO.getDanhMuc()).build())
                .thuongHieu(ThuongHieu.builder().id(sanPhamDTO.getThuongHieu()).build())
                .trangThai(Integer.parseInt(sanPhamDTO.getTrangThai())).build();
        sanPhamService.add(sanPham);
        sanPhamDTO.setIdSP(sanPham.getId());

        for (String idKT : sanPhamDTO.getIdKTAr()) {
            for (String idMS : sanPhamDTO.getIdMSAr()) {
                double gia = 100000;
                SanPhamCT sanPhamCT = SanPhamCT.builder()
                        .ma(sanPhamCTService.generateMaSPCT())
                        .mauSac(MauSac.builder().id(idMS).build())
                        .kichThuoc(KichThuoc.builder().id(idKT).build())
                        .sanPham(SanPham.builder().id(sanPham.getId()).build())
                        .gia(gia)
                        .sl(100)
                        .trangThai(1).build();
                sanPhamCTService.add(sanPhamCT);
                qrCodeGenerator.generateQrCode(sanPhamCT.getId(), 50, 50);
                System.out.println(qrCodeGenerator.generateQrCode(sanPhamCT.getId(), 50, 50));

            }
        }

        Map<String, String> response = new HashMap<>();
        response.put("id", sanPham.getId());
        response.put("message", "Success");
        return ResponseEntity.ok(response);

    }


    @GetMapping("/showSPCT/{idSP}")
    @ResponseBody
    public Page<SanPhamCT> showSanPhamCT(@PathVariable String idSP, @RequestParam("p") int page) {

        return sanPhamCTService.getAllBySP(idSP, PageRequest.of(page, 5));
    }


    @PostMapping("/addTH")
    public String add(@ModelAttribute("tha") ThuongHieu thuongHieu,
                      @RequestParam("ten") String ten,
                      @RequestParam("p") Optional<Integer> p, Model model) {
        int trangThai = 1;
        thuongHieu = ThuongHieu.builder().ma(thuongHieuService.generateMaTH()).ten(ten).trangThai(trangThai).build();
        thuongHieuService.add(thuongHieu);

        return "redirect:/them_sp";
    }

    @GetMapping("/viewOne/")
    @ResponseBody
    public SanPhamCT viewUpdate(String id) {

        return sanPhamCTService.getOne(id);
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("spctu") SanPhamCT sanPhamCT,
                         @RequestParam("idSPCT") String idSPCT[],
                         @RequestParam("idSP") String idSP,
                         @RequestParam("idMS") String idMS[],
                         @RequestParam("idKT") String idKT[],
                         @RequestParam("ma") String maSPCT[],
                         @RequestParam("file") MultipartFile[][] file,
                         @RequestParam("sl") String sl[],
                         @RequestParam("gia") String gia[],

                         @RequestParam("p") Optional<Integer> p, Model model) {

        for (int i = 0; i < idSPCT.length; i++) {
            MultipartFile[] fs = file[i];
            String id = idSPCT[i];
            String idspct = idSPCT[i];
            String soLuong = sl[i];
            String donGia = gia[i];
            String idms = idMS[i];
            String idkt = idKT[i];
            String ma = maSPCT[i];
            for (MultipartFile f : fs) {
                try {
                    sanPhamCT = SanPhamCT.builder()
                            .ma(ma)
                            .sanPham(SanPham.builder().id(idSP).build())
                            .mauSac(MauSac.builder().id(idms).build())
                            .kichThuoc(KichThuoc.builder().id(idkt).build())
                            .data(f.getBytes())
                            .sl(Integer.parseInt(soLuong))
                            .gia(Double.parseDouble(donGia)).build();
                    sanPhamCTService.update(sanPhamCT, idspct);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        Page<SanPham> listSP = sanPhamService.getAll(PageRequest.of(p.orElse(0), 5));
        model.addAttribute("listSP", listSP);
        return "/pages/san_pham";
    }

    @PostMapping("/addDM")
    public String add(@ModelAttribute("dma") DanhMuc danhMuc,
                      @RequestParam("ten") String ten,
                      @RequestParam("p") Optional<Integer> p, Model model) {
        int trangThai = 1;
        danhMuc = DanhMuc.builder().ma(danhMucService.generateMaDM()).ten(ten).trangThai(trangThai).build();
        danhMucService.add(danhMuc);

        return "redirect:/them_sp";
    }

    @PostMapping("/addKT")
    public String add(@ModelAttribute("kta") KichThuoc kichThuoc,
                      @RequestParam("ten") String ten,
                      @RequestParam("p") Optional<Integer> p, Model model) {
        int trangThai = 1;
        kichThuoc = KichThuoc.builder().ma(kichThuocService.generateMaKT()).ten(ten).trangThai(trangThai).build();
        kichThuocService.add(kichThuoc);

        return "redirect:/them_sp";
    }

    @PostMapping("/addMS")
    public String add(@ModelAttribute("msa") MauSac mauSac,
                      @RequestParam("ten") String ten,
                      @RequestParam("p") Optional<Integer> p, Model model) {
        int trangThai = 1;
        mauSac = MauSac.builder().ma(mauSacService.generateMaMS()).ten(ten).trangThai(trangThai).build();
        mauSacService.add(mauSac);

        return "redirect:/them_sp";
    }
}
