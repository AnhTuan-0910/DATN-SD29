package com.springboot.bootstrap.entity.DTO;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class GioHangDTO {
    @Id
    private String idGioHang;
    private UUID idKhachHang;
    private UUID idNhanVien;
    private List<GioHangChiTietDTO> listGioHang;
}
