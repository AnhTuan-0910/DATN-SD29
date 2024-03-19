<!--    JS for modal Voucher -->

document.addEventListener('DOMContentLoaded', function () {
    var idHoaDon=document.querySelector('[role="tabpanel"][aria-labelledby="home-tab"]');
    var selectVoucherModal = document.getElementById('selectVoucherModal'+idHoaDon.id);
    var voucherInput = document.getElementById('voucherInput');
    var saveChangesBtn = document.getElementById('saveChangesBtn');
    var selectKhachHangModal = document.getElementById('selectKhachHangModal'+idHoaDon.id);
    var khachHangInput = document.getElementById('khachHangInput');
    var saveChangesBtnKH = document.getElementById('saveChangesBtnKH');
    saveChangesBtnKH.addEventListener('click', function () {
        var selectedRadio = selectKhachHangModal.querySelector('input[type="radio"]:checked');
        if (selectedRadio) {
            var selectedValue = selectedRadio.value;
            khachHangInput.value = selectedValue;
            window.location.href = 'http://localhost:8080/giao_dich?sdtKhachHang='+khachHangInput.value;
        }
    });
    saveChangesBtn.addEventListener('click', function () {
        var selectedRadio = selectVoucherModal.querySelector('input[type="radio"]:checked');
        if (selectedRadio) {
            var selectedValue = selectedRadio.value;
            voucherInput.value = selectedValue;
            window.location.href = 'http://localhost:8080/giao_dich?sdtKhachHang='+khachHangInput.value+'&maVoucher='+voucherInput.value;
        }
    });
});

<!--    JS search for modal Voucher -->


document.addEventListener("DOMContentLoaded", function () {
    const searchInputVoucher = document.getElementById("searchInputVoucher");
    const vouchers = Array.from(document.getElementsByClassName("card-voucher"));
    searchInputVoucher.addEventListener("input", function () {
        const searchTerm = searchInputVoucher.value.trim().toLowerCase();
        // console.log(searchInputVoucher.value.trim().toLowerCase())
        vouchers.forEach(function (voucher) {
            const voucherCardId = voucher.id; // Lấy id của thẻ card voucher
            // console.log(voucherCardId)
            const maVoucher = document.querySelector("#" + voucherCardId + " .modal-maVoucher").textContent.trim().toLowerCase();
            // console.log(maVoucher);
            const tenVoucher = document.querySelector("#" + voucherCardId + " .modal-tenVoucher").textContent.trim().toLowerCase();
            // const description = voucher.querySelector(".voucher-description").textContent.trim().toLowerCase();
            const isVisible = maVoucher.includes(searchTerm) || tenVoucher.includes(searchTerm);

            if (isVisible) {
                // voucher.style.visibility = "visible";
                voucher.style.display = "block";
            } else {
                // voucher.style.visibility = "hidden";
                voucher.style.display = "none";
            }
        });
    });

    const searchInputKhachHang = document.getElementById("searchInputKhachHang");
    const khachHangs = Array.from(document.getElementsByClassName("card-khachHang"));
    searchInputKhachHang.addEventListener("input", function () {
        const searchTerm = searchInputKhachHang.value.trim().toLowerCase();
        khachHangs.forEach(function (khachHang) {
            const khachHangCardId = khachHang.id;
            const maKhachHang = document.querySelector("#" + khachHangCardId + " .modal-maKhachHang").textContent.trim().toLowerCase();
            const sdtKhachHang = document.querySelector("#" + khachHangCardId + " .modal-sdtKhachHang").textContent.trim().toLowerCase();
            const tenKhachHang = document.querySelector("#" + khachHangCardId + " .modal-tenKhachHang").textContent.trim().toLowerCase();
            const isVisible = sdtKhachHang.includes(searchTerm) || tenKhachHang.includes(searchTerm) || maKhachHang.includes(searchTerm);

            if (isVisible) {
                khachHang.style.display = "block";
            } else {
                khachHang.style.display = "none";
            }
        });
    });
});

