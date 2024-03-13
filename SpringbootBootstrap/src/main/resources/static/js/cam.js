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
            });

        });

    });
}
function scanQR(maHD) {
    let code = document.getElementById("code" + maHD);
    let modalResultQr = document.getElementById("modalResultQr" + maHD);

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

domReady();
