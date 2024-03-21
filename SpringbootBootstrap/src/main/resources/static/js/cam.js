function domReady(fn) {
    document.addEventListener('DOMContentLoaded', function () {
        var tabs = document.querySelectorAll('button[data-bs-toggle="tab"]');
        setTimeout(function() {
            // Chọn tab đầu tiên
            var firstTab = tabs[0];
            firstTab.click();
        }, 100);
        tabs.forEach(function (tab) {
            tab.addEventListener('click', function () {
                let maHD = this.getAttribute('data-hd-ma');
                scanQR(maHD);
                sumbitQR(maHD,fn);
                modalVCAndKH(maHD);
                seacrchModalVCAndKH(maHD);
            });
        });

    });

}
function scanQR(maHD) {
    let code = document.getElementById("idCTSP" + maHD);
    let modalResultQr = document.getElementById("modalResultQr" + maHD);
    let span = document.getElementsByClassName("close")[0];
    span.onclick = function() {
        modalResultQr.style.display = "none";
    }
    // If found you qr code
    function onScanSuccess(decodeText, decodeResult) {
        code.setAttribute("value", decodeText);
        modalResultQr.style.display = "block";
    }


    let idScanner = 'my-qr-reader' + maHD;
    let htmlscanner = new Html5QrcodeScanner(
        idScanner,
        {fps: 10, qrbos: 250}
    );

    htmlscanner.render(onScanSuccess);

}

function sumbitQR(maHD,fn) {
    let modalResultQr = document.getElementById("modalResultQr" + maHD);
    let submitQr = document.getElementById("submitQr" + maHD);
    if (
        document.readyState === "complete" ||
        document.readyState === "interactive"
    ) {
        setTimeout(fn, 1000);
    } else {
        document.addEventListener("DOMContentLoaded", fn);
    }
    submitQr.onclick = function () {
        modalResultQr.style.display = "none";
    }

}
<!--    JS for modal Voucher -->
function modalVCAndKH(maHD) {
    var selectVoucherModal = document.getElementById('selectVoucherModal' + maHD);
    var voucherInput = document.getElementById('voucherInput' + maHD);
    var saveChangesBtn = document.getElementById('saveChangesBtn' + maHD);
    var selectKhachHangModal = document.getElementById('selectKhachHangModal'+maHD);
    var khachHangInput = document.getElementById('khachHangInput'+maHD);
    var saveChangesBtnKH = document.getElementById('saveChangesBtnKH'+maHD);
    saveChangesBtnKH.addEventListener('click', function () {
        var selectedRadio = selectKhachHangModal.querySelector('input[type="radio"]:checked');
        if (selectedRadio) {
            var selectedValue = selectedRadio.value;
            khachHangInput.value = selectedValue;
            window.location.href = 'http://localhost:8080/giao_dich?sdtKhachHang='+khachHangInput.value;
        }
    });
}

<!--    JS search for modal Voucher -->

function seacrchModalVCAndKH(maHD) {
    const searchInputVoucher = document.getElementById("searchInputVoucher" + maHD);
    const vouchers = Array.from(document.getElementsByClassName("card-voucher"+ maHD));
    searchInputVoucher.addEventListener("input", function () {
        const searchTerm = searchInputVoucher.value.trim().toLowerCase();
        vouchers.forEach(function (voucher) {
            const voucherCardId = voucher.id;
            const maVoucher = document.querySelector("#" + voucherCardId + " .modal-maVoucher").textContent.trim().toLowerCase();
            const tenVoucher = document.querySelector("#" + voucherCardId + " .modal-tenVoucher").textContent.trim().toLowerCase();
            // const description = voucher.querySelector(".voucher-description").textContent.trim().toLowerCase();
            const isVisible = maVoucher.includes(searchTerm) || tenVoucher.includes(searchTerm);

            if (isVisible) {
                voucher.style.display = "block";
            } else {
                voucher.style.display = "none";
            }
        });
    })
    const searchInputKhachHang = document.getElementById("searchInputKhachHang"+ maHD);
    const khachHangs = Array.from(document.getElementsByClassName("card-khachHang")+ maHD);
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
}

function selectVoucher(id_pgg) {
    document.getElementById('selected_id_pgg').value = id_pgg;
}

domReady();
