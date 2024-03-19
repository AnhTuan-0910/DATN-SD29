$(document).ready(function () {
    valiDateSuaSPCT();
    fakeInput()
    getAnh();
    getOneSPCT();
    valiDateThemSPCT();

});

//render ảnh từ pc
function fakeInput() {
    // Lắng nghe sự kiện click trên nút tùy chỉnh
    $('.custom-file-upload').on('click', function () {
        // Kích hoạt sự kiện click trên input thực sự trong hàng chứa nút tùy chỉnh
        $(this).siblings('.imageInput').click();
    });
};

function getAnh() {
    // Lắng nghe sự kiện change trên mọi input có class 'imageInput'
    $('.imageInput').on('change', function (event) {
        var files = event.target.files;
        var imagePreviewContainer = $(this).siblings('.imagePreviewContainer')[0];
        var maxFiles = 1; // Số lượng tệp tối đa được chọn

        // Kiểm tra số lượng tệp đã chọn
        if (files.length > maxFiles) {
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "Bạn chỉ có thể chọn tối đa " + maxFiles + " tệp ảnh",
            });

            event.target.value = ''; // Xóa tất cả các tệp đã chọn
            return;
        }

        // Xóa tất cả các thẻ <img> hiện có trong container
        imagePreviewContainer.innerHTML = '';

        // Duyệt qua từng file và hiển thị hình ảnh
        for (var i = 0; i < files.length; i++) {
            var file = files[i];
            if (!file.type.startsWith('image/')) {
                continue
            }

            var reader = new FileReader();
            var img = document.createElement('img');
            img.style.maxWidth = '100px';
            img.style.maxHeight = '100px';

            reader.onload = (function (theImg) {
                return function (e) {
                    theImg.src = e.target.result;
                };
            })(img);

            reader.readAsDataURL(file);
            imagePreviewContainer.appendChild(img);
        }

    });
};

function updatePagination(data) {
    var totalPages = data.totalPages;
    var currentPage = data.number + 1; // Trang hiện tại (đánh số từ 1)

    $('#page').empty();

    // Chỉ hiển thị nút "Previous" nếu không phải là trang đầu tiên
    if (currentPage > 1) {
        var prevPageLink = $('<a>', {
            class: 'page-link',
            href: '#',
            'data-page': currentPage - 2
        }).text('Previous');
        var prevPageItem = $('<li>', {
            class: 'page-item'
        }).append(prevPageLink);
        $('#page').append(prevPageItem);
    }

    // Hiển thị số trang từ trang hiện tại - 1 đến trang hiện tại + 1
    var startPage = Math.max(1, currentPage - 1);
    var endPage = Math.min(totalPages, currentPage + 1);

    for (var i = startPage; i <= endPage; i++) {
        var pageLink = $('<a>', {
            class: 'page-link',
            href: '#',
            'data-page': i - 1
        }).text(i);
        var listItem = $('<li>', {
            class: 'page-item ' + (i === currentPage ? 'active' : '')
        }).append(pageLink);
        $('#page').append(listItem);
    }

    // Chỉ hiển thị nút "Next" nếu không phải là trang cuối cùng
    if (currentPage < totalPages) {
        var nextPageLink = $('<a>', {
            class: 'page-link',
            href: '#',
            'data-page': currentPage
        }).text('Next');
        var nextPageItem = $('<li>', {
            class: 'page-item'
        }).append(nextPageLink);
        $('#page').append(nextPageItem);
    }


}

// Thay đổi giá trị của input file và hiển thị hình ảnh
function updateImagePreview(imageData) {
    // Đặt giá trị cho input file
    $('.formUpdate #imageUpd').val('');

    // Xóa tất cả các thẻ <img> hiện có trong container
    $('.formUpdate .imagePreviewContainer').empty();

    // Tạo một thẻ img mới với dữ liệu hình ảnh
    var img = $('<img>').attr('src', 'data:image/jpeg;base64,' + imageData);
    img.css('maxWidth', '100px');
    img.css('maxHeight', '100px');


    // Thêm hình ảnh vào container
    $('.formUpdate .imagePreviewContainer').append(img);
}


function getOneSPCT() {
    $('#table1 .uBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.ajax({
            type: 'GET', // Sử dụng phương thức GET để lấy dữ liệu spct từ href
            url: href, // Gửi yêu cầu GET đến URL được chỉ định trong thuộc tính href
            success: function (spct) {
                $.ajax({
                    type: 'GET',
                    url: '/spct/convertToBase64?id=' + spct.id,
                    success: function (response) {
                        updateImagePreview(response);
                    },
                    error: function (xhr, status, error) {
                        console.error('Error:', error);
                    }

                });
                $('.formUpdate #idSPCTUpd').val(spct.id);
                $('.formUpdate #kichThuocUpd').val(spct.kichThuoc.id);
                $('.formUpdate #maSPCTUpd').val(spct.ma);
                $('.formUpdate #mauSacUpd').val(spct.mauSac.id);
                $('.formUpdate #soLuongUpd').val(spct.sl);
                $('.formUpdate #ipFileFake').val(spct.data);
                $('.formUpdate #donGiaUpd').val(spct.gia);

            },
            error: function (xhr, status, error) {
                console.error('Error:', error);
            }
        });
        $('.formUpdate #suaSPCT').modal('show');
    })

}

function valiDateSuaSPCT() {

    $("#cfUpdBtn").click(function (e) {

        if (!validateSoLuong() || !validateGia()) {

            e.preventDefault();
            return;
        }
        e.preventDefault();

        // Hiển thị thông báo xác nhận
        showConFirmUpd();
    });


}

function valiDateThemSPCT() {

    $("#cfAddBtn").click(function (e) {

        if (!validateFiles() || !validateSoLuong() || !validateGia() || !validateMSAndKT()) {

            e.preventDefault();
            return;
        }
        e.preventDefault();

        // Hiển thị thông báo xác nhận
        showConFirmAdd();
    });


}


function showConFirmUpd() {
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: "btn btn-success",
            cancelButton: "btn btn-danger"
        },
        buttonsStyling: false
    });
    swalWithBootstrapButtons.fire({
        title: "Are you sure?",
        text: "You won't be able to revert this!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Yes, delete it!",
        cancelButtonText: "No, cancel!",
        reverseButtons: true
    }).then((result) => {
        if (result.isConfirmed) {
            $("#formUpdSPCT").submit();
            Swal.fire({
                position: "top-end",
                icon: "success",
                title: "Cập nhật sản phẩm chi tiết  thành công",
                showConfirmButton: false,
                timer: 1500
            });
        }
    });
}

function showConFirmAdd() {
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: "btn btn-success",
            cancelButton: "btn btn-danger"
        },
        buttonsStyling: false
    });
    swalWithBootstrapButtons.fire({
        title: "Are you sure?",
        text: "You won't be able to revert this!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Yes",
        cancelButtonText: "No",
        reverseButtons: true
    }).then((result) => {
        if (result.isConfirmed) {
            $("#formAddSPCT").submit();
            Swal.fire({
                position: "top-end",
                icon: "success",
                title: "Tạo sản phẩm chi tiết mới thành công",
                showConfirmButton: false,
                timer: 1500
            });
        }
    });
}

function validateGia() {
    var gia = document.querySelector(".validDG").value;
    if (gia.trim() === "" || isNaN(gia)) {
        Swal.fire({
            title: "Error!",
            text: "Vui lòng nhập giá hợp lệ",
            icon: "warning",

        });
        return false;
    }
    if (gia <= 0) {
        Swal.fire({
            title: "Error!",
            text: "Giá sản phẩm phải lớn hơn 0 ",
            icon: "warning",

        });
        return false;
    }
    return true;
}

function validateMSAndKT() {
    var ktAdd = document.getElementById("vlidKT").value.trim();
    var msAdd = document.getElementById("vlidMS").value.trim();

    $.ajax({
        url: "/spct/checkMSAndKTSPCT",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({idMS: msAdd, idKT: ktAdd}),
        success: function (data) {
            if (data) {
                Swal.fire({
                    title: "Error!",
                    text: "Sản phẩm chi tiết này đã tồn tại",
                    icon: "warning"
                });
                return false;
            }

        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
        }

    });
    return true;
}

function validateFiles() {
    var fileInputs = $("#imageAdd");
    var isValid = true;

    fileInputs.each(function () {

        if ($(this).get(0).files.length === 0) {

            isValid = false;

            Swal.fire({
                title: "Error!",
                text: "Vui lòng chọn ảnh cho  sản phẩm",
                icon: "warning",

            });

            return false;
        }
    });


    return isValid;
}


function validateSoLuong() {
    var sl = document.querySelector(".validSL").value;
    if (sl.trim() === "" || isNaN(sl)) {
        Swal.fire({
            title: "Error!",
            text: "Vui lòng nhập số lượng hợp lệ ",
            icon: "warning",

        });
        return false;
    }
    if (sl <= 0) {
        Swal.fire({
            title: "Error!",
            text: "Số lượng sản phẩm phải lớn hơn 0 ",
            icon: "warning",

        });
        return false;
    }

    return true;
}



