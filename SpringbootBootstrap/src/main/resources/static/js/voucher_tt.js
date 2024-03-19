<!--    JS for modal Voucher -->

    document.addEventListener('DOMContentLoaded', function () {
        var idHoaDon=document.querySelector('[role="tabpanel"][aria-labelledby="home-tab"]');
        var selectVoucherModal = document.getElementById('selectVoucherModal'+idHoaDon.id);
        var voucherInput = document.getElementById('voucherInput');
        var saveChangesBtn = document.getElementById('saveChangesBtn');
        saveChangesBtn.addEventListener('click', function () {
            var selectedRadio = selectVoucherModal.querySelector('input[type="radio"]:checked');
            if (selectedRadio) {
                var selectedValue = selectedRadio.value;
                voucherInput.value = selectedValue;
                window.location.href = 'http://localhost:8080/giao_dich?maVoucher='+voucherInput.value;
            }
        });
    });

<!--    JS search for modal Voucher -->

    document.addEventListener("DOMContentLoaded", function () {
        const searchInputVoucher = document.getElementById("searchInputVoucher");
        const vouchers = Array.from(document.getElementsByClassName("card-voucher"));
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
        });
    });

