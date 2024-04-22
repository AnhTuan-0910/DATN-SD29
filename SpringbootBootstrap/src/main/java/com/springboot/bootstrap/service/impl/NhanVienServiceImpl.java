package com.springboot.bootstrap.service.impl;

import com.springboot.bootstrap.entity.ChucVu;
import com.springboot.bootstrap.entity.KhachHang;
import com.springboot.bootstrap.entity.NhanVien;
import com.springboot.bootstrap.repository.ChucVuRepo;
import com.springboot.bootstrap.repository.NhanVienRepo;
import com.springboot.bootstrap.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class NhanVienServiceImpl implements NhanVienService{

    @Autowired
    private NhanVienRepo nhanVienRepo;
    private static int counter = 0;

    @Autowired
    private ChucVuRepo chucVuRepo;

    @Override
    public List<NhanVien> findAll() {

        return nhanVienRepo.findAll();
    }

    @Override
    public Page<NhanVien> getAll(Pageable pageable) {

        return nhanVienRepo.findAll(pageable);
    }

    @Override
    public NhanVien getOne(String idNV) {
        NhanVien n = nhanVienRepo.findById(idNV).orElse(null);
        return n;
    }

    @Override
    public void add(NhanVien nhanVien) {

        nhanVienRepo.save(nhanVien);
    }

    @Override
    public void update(NhanVien nhanVien, String idNV) {
        nhanVien.setIdNV(idNV);
        nhanVienRepo.save(nhanVien);
    }

    @Override
    public Page<NhanVien> searchCodeOrName(String keyword, Pageable pageable) {
        return nhanVienRepo.searchCodeOrName(keyword, pageable);
    }

    @Override
    public Page<NhanVien> searchTrangThai(int trangThai, Pageable pageable) {
        return nhanVienRepo.searchTrangThai(trangThai, pageable);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NhanVien nhanVien = nhanVienRepo.findByEmail(username);
        if(nhanVien == null){
            throw new UsernameNotFoundException("Invalid username and password.");
        }
        Set<GrantedAuthority> listAuthorities = new HashSet<>();
        listAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new User(nhanVien.getIdNV(),nhanVien.getMatKhau(),listAuthorities);
    }
}
