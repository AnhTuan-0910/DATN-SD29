
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
    htmlscanner.render(onScanSuccess);
});
