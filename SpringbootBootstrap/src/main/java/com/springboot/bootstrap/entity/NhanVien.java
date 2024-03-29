package com.springboot.bootstrap.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.security.SecureRandom;
import java.util.UUID;

@Entity
@Table(name = "nhan_vien")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_nhan_vien")
    private String idNV;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_chuc_vu", referencedColumnName = "id_chuc_vu")
    private ChucVu idCV;

    @Column(name = "ma")
    private String ma;

    @Column(name = "email")
    private String email;

    @Column(name = "ten")
    private String ten;

    @Column(name = "gioi_tinh")
    private String gioiTinh;

    @Column(name = "ngay_sinh")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.sql.Date ngaySinh;

    @Column(name = "anh_nhan_vien")
    private byte[] anhNhanVien;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "mat_khau")
    private String matKhau;

    @Column(name = "trang_thai")
    private int trangThai;

    @Column(name = "tao_luc")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.sql.Timestamp taoLuc;

    @Column(name = "sua_luc")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.sql.Timestamp suaLuc;

    @Column(name = "tao_boi")
    private String taoBoi;

    @Column(name = "sua_boi")
    private String suaBoi;


    @Override
    public int hashCode() {
        return 29;
    }

    public static String generatePassword() {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialChars = "@";

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        int length = 8;  // Độ dài mật khẩu mong muốn

        // Chọn ít nhất 1 ký tự đặc biệt và 1 số
        password.append(specialChars.charAt(random.nextInt(specialChars.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));

        // Độ dài còn lại để hoàn thành mật khẩu
        int remainingLength = length - 2;

        String allCharacters = upper + lower + digits + specialChars;

        for (int i = 0; i < remainingLength; i++) {
            int index = random.nextInt(allCharacters.length());
            password.append(allCharacters.charAt(index));
        }
        return password.toString();
    }

    private String generateMaNhanVien() {
        // Tạo một UUID mới
        UUID uuid = UUID.randomUUID();

        // Chuyển UUID thành chuỗi và loại bỏ các ký tự '-'
        String uuidString = uuid.toString().replace("-", "");

        // Lấy 6 ký tự đầu của chuỗi UUID
        return "NV" + uuidString.toUpperCase().substring(0, 9);
    }
}
