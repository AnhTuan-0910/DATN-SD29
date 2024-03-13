DATABASE THÊM MỚI: Thêm hàm trigger , sửa don_vi trong bảng phieu_giam_gia thành int.

CREATE TRIGGER trg_generate_ma_hoa_don
ON hoa_don
FOR INSERT
AS
BEGIN
    DECLARE @id_hoa_don uniqueidentifier;
    DECLARE @ma_hoa_don VARCHAR(20);

    SELECT @id_hoa_don = id_hoa_don FROM inserted;
    SET @ma_hoa_don = 'HD' + RIGHT(CONVERT(VARCHAR(36), @id_hoa_don), 6);

    UPDATE hoa_don SET ma = @ma_hoa_don WHERE id_hoa_don = @id_hoa_don;
END;
