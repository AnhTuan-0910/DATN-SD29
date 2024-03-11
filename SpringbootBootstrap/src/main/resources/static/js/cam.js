
let submitQr = document.getElementById("submitQr");
function domReady(fn) {
    if (
        document.readyState === "complete" ||
        document.readyState === "interactive"
    ) {
        setTimeout(fn, 1000);
    } else {
        document.addEventListener("DOMContentLoaded", fn);
    }
    submitQr.onclick = function () {
        modalResultQr.style.display="none";
    }
}

domReady(function () {
    let code = document.getElementById("code");
    let modalResultQr = document.getElementById("modalResultQr");
    // If found you qr code
    function onScanSuccess(decodeText, decodeResult) {
        code.setAttribute("value",decodeText);
        modalResultQr.style.display = "block";
    }

    let htmlscanner = new Html5QrcodeScanner(
        "my-qr-reader",
        { fps: 10, qrbos: 250 }
    );
    let htmlscanner1 = new Html5QrcodeScanner(
        "my-qr-reader-2",
        { fps: 10, qrbos: 250 }
    );
    let htmlscanner2 = new Html5QrcodeScanner(
        "my-qr-reader-3",
        { fps: 10, qrbos: 250 }
    );
    let htmlscanner3 = new Html5QrcodeScanner(
        "my-qr-reader-4",
        { fps: 10, qrbos: 250 }
    );
    let htmlscanner4 = new Html5QrcodeScanner(
        "my-qr-reader-5",
        { fps: 10, qrbos: 250 }
    );
    let htmlscanner5 = new Html5QrcodeScanner(
        "my-qr-reader-6",
        { fps: 10, qrbos: 250 }
    );
    htmlscanner.render(onScanSuccess);
    htmlscanner1.render(onScanSuccess);
    htmlscanner2.render(onScanSuccess);
    htmlscanner3.render(onScanSuccess);
    htmlscanner4.render(onScanSuccess);
    htmlscanner5.render(onScanSuccess);

});
