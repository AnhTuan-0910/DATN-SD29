$(document).ready(function () {
    $("#formAddSP").submit(function (e) {
        e.preventDefault();
        var tenSP = $("#tenSP").val();
        var danhMuc = $("#danhMuc").val();
        var trangThai = $("#trangThai").val();
        var thuongHieu = $("#thuongHieu").val();
        var idMSArray = getIdMSArray();
        var idKTArray = getIdKTArray();

        if (tenSP.trim() === '' ) {
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "Vui lòng nhập tên sản phẩm!",
            });
            return;
        }

        if (idMSArray.length === 0 || idKTArray.length === 0) {
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "Vui lòng chọn ít nhất một màu sắc và kích thước cho sản phẩm!",
            });
            return;
        }
        Swal.fire({
            title: "Tạo sản phẩm?",
            text: "Bạn có chắc muốn tạo 1 sản phẩm mới?",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Có,tôi chắc chắn"
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    type: "POST",
                    url: "/them_sp/addSPCT",
                    data: JSON.stringify({
                        ten: tenSP,
                        danhMuc: danhMuc,
                        trangThai: trangThai,
                        thuongHieu: thuongHieu,
                        idMSAr: idMSArray,
                        idKTAr: idKTArray
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (response) {

                        addSPCT(response, 0);
                        $(document).on('click', '.page-link', function (event) {
                            event.preventDefault();
                            var page = $(this).data('page');
                            addSPCT(response,page);
                        });
                    },
                    error: function (xhr, status, error) {
                        alert("Đã xảy ra lỗi khi tạo sản phẩm!");
                        console.log(xhr.responseText); // In ra nội dung lỗi từ phản hồi
                        console.log(status); // In ra trạng thái lỗi
                        console.log(error); // In ra thông tin lỗi
                    }
                });
                Swal.fire({
                    title: "Thành công!",
                    text: "Đã tạo sản phẩm thành công.",
                    icon: "success"
                });
            }
        });



    });
});
$(document).ready(function () {

    $("#confirmUpdateBtn").click(function(e) {
        if($('#table1 tbody tr').length===0){
            e.preventDefault();
            Swal.fire({
                title: "Error!",
                text: "Bạn không thể xác nhận khi chưa có sản phẩm",
                icon: "warning",

            });
            return;
        }
        if (!validateFiles() || !validateSoLuong() || !validateGia()) {

            e.preventDefault();
            return;
        }
        e.preventDefault();

        showConFirm();
    });


});


function showConFirm(){
    $("#updateFormSPCT").off("submit");
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
            $("#updateFormSPCT").submit();
            Swal.fire({
                position: "top-end",
                icon: "success",
                title: "Tạo sản phẩm mới thành công",
                showConfirmButton: false,
                timer: 1500
            });
        } else if (
            result.dismiss === Swal.DismissReason.cancel
        ) {
            swalWithBootstrapButtons.fire({
                title: "Cancelled",
                text: "Your imaginary file is safe :)",
                icon: "error"
            });
        }
    });
}
function addSPCT(response, page = 0) {
    $.ajax({
        type: "GET",
        url: "/them_sp/showSPCT/" + response.id + "?p=" + page, // Truyền idSP vừa thêm vào endpoint để lấy sản phẩm chi tiết
        success: function (data) {
            $('#table1 tbody').empty();
            $.each(data.content, function (index, spct) {

                $('#table1 tbody').append(
                    `<tr>
                                <td> ${index+1} </td>
                                <td> ${spct.sanPham.ten} </td>
                                <td> ${spct.kichThuoc.ten} </td>
                                <td> <span class="badge" style="background-color: ${spct.mauSac.ten}">${spct.mauSac.ten}</span> </td>
                                <td><input type="text" value="${spct.sl}"  name="sl" class="form-control soLuong"></td>
                                <td><input type="text" value="${spct.gia}"  name="gia" class="form-control donGia"></td>
                               
                                <td>
                                 <a class="btn btn-outline-danger"><i data-feather="trash-2"></i></a>
                                </td> 
                                <td>
                                <input type="hidden" value="${spct.id}" name="idSPCT">
                                <input type="hidden" value="${spct.ma}" name="ma">
                                <input type="hidden" value="${spct.sanPham.id}" name="idSP">
                                <input type="hidden" value="${spct.mauSac.id}" name="idMS">
                                <input type="hidden" value="${spct.kichThuoc.id}" name="idKT">
                                <a class="btn btn-outline-warning custom-file-upload"><i data-feather="plus-circle"></i></a>
                                <input type="file" class="imageInput" name="file"  style="display: none"  multiple>
                                <div class="imagePreviewContainer"></div>
                                </td>
                                </tr>`);


            })
            fakeInput();
            getAnh();
            updatePagination(data);
            feather.replace();
        },
        error: function () {
            alert("Đã xảy ra lỗi khi hiển thị sản phẩm!");
        }
    });


}


function getIdMSArray() {
    var idMSArray = [];
    $('.selected-itemMS').each(function () {
        var idMS = $(this).find('input[name="idms"]').val();
        idMSArray.push(idMS);
    });
    return idMSArray;
}

function getIdKTArray() {
    var idKTArray = [];
    $('.selected-itemKT').each(function () {
        var idKT = $(this).find('input[name="idkt"]').val();
        idKTArray.push(idKT);
    });
    return idKTArray;
}

//phân trang
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

//render kt
$(document).ready(function () {
    var selectedItems = [];

    $('.itemKT').click(function () {
        var id = $(this).data('idkt');
        var item = $(this).data('item');
        if (selectedItems.indexOf(item) === -1) {
            selectedItems.push(item);

            $('#selectedKT').append('<div class="btn btn-outline-primary selected-itemKT"  style="margin-top: 5px;" " data-item="' + item + '">' + item + '<i style="padding-left: 5px" data-feather="x"></i><input name="idkt" value="' + id + '" hidden ></div> &nbsp;');
            $(this).attr('disabled', true);
            feather.replace();
        } else {
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "Bạn đã chọn kích thước này rồi!",
            });
        }
    });

    $(document).on('click', '.selected-itemKT', function () {
        var item = $(this).data('item');
        var index = selectedItems.indexOf(item);
        if (index !== -1) {
            selectedItems.splice(index, 1);
        }
        $(this).remove();
    });

});


//render ms
$(document).ready(function () {
    var selectedItems = [];

    $('.itemMS').click(function () {
        var id = $(this).data('idms');
        var item = $(this).data('item');
        if (selectedItems.indexOf(item) === -1) {
            selectedItems.push(item);
            $('#selectedMS').append('<div class="btn btn-outline-secondary selected-itemMS " style="margin-top: 5px;background-color:' + item + '" data-item="' + item + '" >' + item + '<i style="padding-left: 5px" data-feather="x"></i><input name="idms" value="' + id + '" hidden></div> &nbsp;');
            $(this).attr('disabled', true);
            feather.replace();
        } else {
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "Bạn đã chọn màu sắc này rồi!",
            });
        }
    });

    $(document).on('click', '.selected-itemMS', function () {
        var item = $(this).data('item');
        var index = selectedItems.indexOf(item);
        if (index !== -1) {
            selectedItems.splice(index, 1);
        }
        $(this).remove();
    });

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
            img.style.maxWidth = '50px';
            img.style.maxHeight = '50px';

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


function validateGia() {
    var gia = document.querySelector(".donGia").value;
    if (gia.trim() === ""|| isNaN(gia)) {
        Swal.fire({
            title: "Error!",
            text: "Vui lòng nhập giá hợp lệ",
            icon: "warning",

        });
        return false;
    }
    if (gia<=0) {
        Swal.fire({
            title: "Error!",
            text: "Giá sản phẩm phải lớn hơn 0 ",
            icon: "warning",

        });
        return false;
    }
    return true;
}

function validateFiles() {
    var fileInputs = $("#table1 tbody tr .imageInput");
    var isValid = true;

    fileInputs.each(function() {

        if ($(this).get(0).files.length === 0) {

            isValid = false;

            Swal.fire({
                title: "Error!",
                text: "Vui lòng chọn ảnh cho từng sản phẩm",
                icon: "warning",

            });

            return false;
        }
    });


    return isValid;
}


function validateSoLuong() {
    var sl = document.querySelector(".soLuong").value;
    if (sl.trim() === "" || isNaN(sl)) {
        Swal.fire({
            title: "Error!",
            text: "Vui lòng nhập số lượng hợp lệ ",
            icon: "warning",

        });
        return false;
    }
    if (sl<=0) {
        Swal.fire({
            title: "Error!",
            text: "Số lượng sản phẩm phải lớn hơn 0 ",
            icon: "warning",

        });
        return false;}

    return true;
}




