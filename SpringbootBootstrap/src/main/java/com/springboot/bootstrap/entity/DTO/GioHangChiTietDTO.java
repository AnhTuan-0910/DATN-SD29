package com.springboot.bootstrap.entity.DTO;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class GioHangChiTietDTO {
    @Id
    private String ma;
    private String tenSanPham;
    private String mauSac;
    private String kichThuoc;
    private Integer soLuong;
    private Double gia;
}
