package com.springboot.bootstrap.repository;

import com.springboot.bootstrap.entity.ChucVu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChucVuRepo extends JpaRepository<ChucVu, String> {

    Page<ChucVu> findAll(Pageable pageable);

    Page<ChucVu> findAllByTen(String name, Pageable pageable);

}
