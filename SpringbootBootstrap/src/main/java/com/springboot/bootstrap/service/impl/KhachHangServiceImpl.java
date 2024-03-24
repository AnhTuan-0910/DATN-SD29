package com.springboot.bootstrap.service.impl;

import com.springboot.bootstrap.entity.KhachHang;
import com.springboot.bootstrap.repository.KhachHangRepository;
import com.springboot.bootstrap.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;
    private static final String ma = "KH";
    private static int counter = 0;

    Timestamp currentTimestamp;

    @Override
    public Page<KhachHang> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo -1, 4);
        return khachHangRepository.findAll(pageable);
    }

    @Override
    public KhachHang getOne(String id) {
        return khachHangRepository.findById(id).orElse(null);
    }

    @Override
    public void add(KhachHang khachHang) {
        khachHang.setAnhNhanVien(null);
        java.util.Date currentDate = new java.util.Date();
        currentTimestamp = new Timestamp(currentDate.getTime());
        khachHang.setTaoLuc(currentTimestamp);
        khachHangRepository.save(khachHang);
    }

    @Override
    public void update(KhachHang khachHang) {
        khachHang.setAnhNhanVien(null);
        java.util.Date currentDate = new java.util.Date();
        currentTimestamp = new Timestamp(currentDate.getTime());
        khachHang.setSuaLuc(currentTimestamp);
        khachHangRepository.save(khachHang);
    }

    @Override
    public void delete(String id) {
        KhachHang khachHang = khachHangRepository.findById(id).orElse(null);
        if (khachHang != null) {
            khachHang.setTrangThai(khachHang.getTrangThai() == 0 ? 1 : 0);
            khachHangRepository.save(khachHang);
        }
    }

    @Override
    public Page<KhachHang> searchCodeOrName(String keyword, Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo -1, 4);
        List list = khachHangRepository.searchCodeOrName(keyword);
        Integer start = (int) pageable.getOffset();
        Integer end = (int) ((pageable.getOffset() + pageable.getPageSize()) > list.size()
                ? list.size() : pageable.getOffset() + pageable.getPageSize());
        list = list.subList(start, end);
        return new PageImpl<KhachHang>(list, pageable, khachHangRepository.searchCodeOrName(keyword).size());
    }

    @Override
    public List<KhachHang> findAll() {
        return khachHangRepository.findAll();
    }

    @Override
    public List<KhachHang> findAllByTrangThai() {
        return khachHangRepository.findAllByTrangThai(1);
    }

    @Override
    public KhachHang findBySdt(String sdt) {
        return khachHangRepository.findBySdt(sdt);
    }

    @Override
    public String generateMaKH() {
        counter++;
        return ma + String.format("%03d", counter);
    }

//    @Override
//    public Page<KhachHang> searchTrangThai(int trangThai, Pageable pageable) {
//        return khachHangRepository.searchTrangThai(trangThai, pageable);
//    }
//
//    @Override
//    public String ImageUpload(UUID id, MultipartFile khImage) {
//        String fileName="";
//
//        String khFolder = "C:/Users/Admin/Documents/DATN-SD29-master/SpringbootBootstrap/src/main/resources/static/upload";
//        //Save image
//        try {
//            byte[] bytes = khImage.getBytes();
//
//            //Create directory if not exists
//            File file = new File(khFolder+"/"+id);
//            if (!file.exists()) {
//                file.mkdirs();
//            }
//
//            fileName = khImage.getName()+".jpeg";
//
//            String fileWithFolderName = khFolder+"/"+id+"/"+fileName;
//
//            BufferedOutputStream stream = new BufferedOutputStream(
//                    new FileOutputStream(
//                    new File(fileWithFolderName)));
//            stream.write(bytes);
//            stream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return fileName;
//    }
}
