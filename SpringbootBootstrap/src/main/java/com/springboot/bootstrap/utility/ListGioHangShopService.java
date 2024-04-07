package com.springboot.bootstrap.utility;

import com.springboot.bootstrap.entity.DTO.GioHangDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter @Setter
public class ListGioHangShopService {
    private List<GioHangDTO> list;
}
