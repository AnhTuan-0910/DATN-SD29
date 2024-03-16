package com.springboot.bootstrap.controller;


import com.springboot.bootstrap.entity.Anh;
import com.springboot.bootstrap.entity.DanhMuc;
import com.springboot.bootstrap.entity.KichThuoc;
import com.springboot.bootstrap.entity.MauSac;
import com.springboot.bootstrap.entity.SanPham;
import com.springboot.bootstrap.entity.SanPhamCT;
import com.springboot.bootstrap.entity.ThuongHieu;
import com.springboot.bootstrap.service.AnhService;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
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
    @Autowired
    private AnhService anhService;

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

    @PostMapping( "/addSPCT")
    public String add(@ModelAttribute("spcta") SanPhamCT sanPhamCT,
                      @RequestParam("tenSP") String ten,
                      @RequestParam("trangThai") String trangThai,
                      @RequestParam("thuongHieu") String thuongHieu,
                      @RequestParam("danhMuc") String danhMuc,

                      @RequestParam("idms") String[] idMSAr,
                      @RequestParam("idkt") String[] idKTAr,
                      @RequestParam("p") Optional<Integer> p,
                      Model model) {
        SanPham sanPham = SanPham.builder()
                .ma(sanPhamService.generateMaSP())
                .ten(ten)
                .danhMuc(DanhMuc.builder().id(danhMuc).build())
                .thuongHieu(ThuongHieu.builder().id(thuongHieu).build())
                .trangThai(Integer.parseInt(trangThai)).build();
        sanPhamService.add(sanPham);

        for (String idKT : idKTAr) {
            for (String idMS : idMSAr) {
                double gia = 100000;
                sanPhamCT = SanPhamCT.builder()
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
                Locale vietnameseLocale = new Locale("vi", "VN");
                NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(vietnameseLocale);
                String formattedGia = currencyFormatter.format(gia);
                model.addAttribute("gia", formattedGia);

            }
        }

        Page<SanPhamCT> listSPCT = sanPhamCTService.getAllBySP(sanPham.getId(), PageRequest.of(p.orElse(0), 5));
        model.addAttribute("listSPCT", listSPCT);
        model.addAttribute("qrCodeGenerator", qrCodeGenerator);
        getAll(model);
        return "/pages/them_sp";
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
            String id = idSPCT[i];
            MultipartFile[] fs = file[i];
            for (MultipartFile f : fs) {
                try {
                    Anh anh = Anh.builder().sanPhamCT(SanPhamCT.builder().id(id).build()).ten(f.getOriginalFilename()).data((f.getBytes())).build();
                    anhService.add(anh);
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }
        for (int a = 0; a < idSPCT.length; a++) {
            String idspct = idSPCT[a];
            String soLuong = sl[a];
            String donGia = gia[a];
            String idms = idMS[a];
            String idkt = idKT[a];
            String ma = maSPCT[a];
            sanPhamCT = SanPhamCT.builder()
                    .ma(ma)
                    .sanPham(SanPham.builder().id(idSP).build())
                    .mauSac(MauSac.builder().id(idms).build())
                    .kichThuoc(KichThuoc.builder().id(idkt).build())
                    .sl(Integer.parseInt(soLuong))
                    .gia(Double.parseDouble(donGia)).build();
            sanPhamCTService.update(sanPhamCT, idspct);
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
