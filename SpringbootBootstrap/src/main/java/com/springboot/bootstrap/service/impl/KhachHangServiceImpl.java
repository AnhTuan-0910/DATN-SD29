package com.springboot.bootstrap.service.impl;

import com.springboot.bootstrap.entity.KhachHang;
import com.springboot.bootstrap.repository.DiaChiRepository;
import com.springboot.bootstrap.repository.KhachHangRepository;
import com.springboot.bootstrap.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.UUID;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private DiaChiRepository diaChiRepository;

    Timestamp currentTimestamp;

    @Override
    public Page<KhachHang> getAll(Pageable pageable) {
        return khachHangRepository.findAll(pageable);
    }

    @Override
    public KhachHang getOne(UUID id) {
        return khachHangRepository.findById(id).orElse(null);
    }

    @Override
    public void add(KhachHang khachHang) {
        java.util.Date currentDate = new java.util.Date();
        currentTimestamp = new Timestamp(currentDate.getTime());
        if (khachHang.getLoadAnh().getSize() > 0) {
            String anhNhanVien = ImageUpload(khachHang.getIdKhachHang(), khachHang.getLoadAnh());
            khachHang.setAnhNhanVien(anhNhanVien);
        }
        khachHang.setTaoLuc(currentTimestamp);
        khachHang.setSuaLuc(currentTimestamp);
        khachHangRepository.save(khachHang);
    }

    @Override
    public void update(KhachHang khachHang, UUID id) {
        khachHang.setIdKhachHang(id);
        java.util.Date currentDate = new java.util.Date();
        currentTimestamp = new Timestamp(currentDate.getTime());
        if (khachHang.getLoadAnh().getSize() > 0) {
            String anhNhanVien = ImageUpload(khachHang.getIdKhachHang(), khachHang.getLoadAnh());
            khachHang.setAnhNhanVien(anhNhanVien);
        }
        khachHang.setTaoLuc(currentTimestamp);
        khachHang.setSuaLuc(currentTimestamp);
        khachHangRepository.save(khachHang);
    }

    @Override
    public Page<KhachHang> searchCodeOrName(String keyword, Pageable pageable) {
        return khachHangRepository.searchCodeOrName(keyword, pageable);
    }

    @Override
    public Page<KhachHang> searchTrangThai(int trangThai, Pageable pageable) {
        return khachHangRepository.searchTrangThai(trangThai, pageable);
    }

    @Override
    public String ImageUpload(UUID id, MultipartFile khImage) {
        String fileName="";

        String khFolder = "C:/Users/Admin/Documents/DATN-SD29-master/SpringbootBootstrap/src/main/resources/static/upload";
        //Save image
        try {
            byte[] bytes = khImage.getBytes();

            //Create directory if not exists
            File file = new File(khFolder+"/"+id);
            if (!file.exists()) {
                file.mkdirs();
            }

            fileName = khImage.getName()+".jpeg";

            String fileWithFolderName = khFolder+"/"+id+"/"+fileName;

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(
                    new File(fileWithFolderName)));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
