package com.springboot.bootstrap.entity.DTO;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SanPhamQrDTO {
    @NotNull(message = "Không dc để trống")
    @Min(value = 1)
    private Integer soLuong;
    @NotNull
    private UUID idhd;
    @NotNull
    private UUID idspct;
}
