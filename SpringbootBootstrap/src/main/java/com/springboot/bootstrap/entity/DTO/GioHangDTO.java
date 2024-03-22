package com.springboot.bootstrap.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class GioHangDTO {
    private String ma;
    private Integer soLuong;
}
