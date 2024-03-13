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
