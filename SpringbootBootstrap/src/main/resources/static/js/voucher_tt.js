<!--    JS for modal Voucher -->

    document.addEventListener('DOMContentLoaded', function () {
    var selectVoucherModal = document.getElementById('selectVoucherModal');
    var voucherInput = document.getElementById('voucherInput');
    var saveChangesBtn = document.getElementById('saveChangesBtn');

    saveChangesBtn.addEventListener('click', function () {
    var selectedRadio = selectVoucherModal.querySelector('input[type="radio"]:checked');
    if (selectedRadio) {
    var selectedValue = selectedRadio.value;
    voucherInput.value = selectedValue;
    console.log(voucherInput.value);
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
});

