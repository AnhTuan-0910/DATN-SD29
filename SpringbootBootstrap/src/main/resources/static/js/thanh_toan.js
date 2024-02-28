//fill table SanPham , phân trang, tìm kiếm
$(document).ready(function () {
    var isSearching = false;
    var isFillter = false;
    fetchProducts(0);
    //filter
    $('#formFilter').submit(function (e) {
        e.preventDefault();
        var idKichThuoc = $('#kichThuocSearch').val();
        var idMauSac = encodeURIComponent($('#mauSacSearch').val());
        var idDanhMuc = $('#danhMucSearch').val();
        var idThuongHieu = $('#thuongHieuSearch').val();
        filterProducts(idDanhMuc, idKichThuoc, idMauSac, idThuongHieu, 0);
        isFillter = true;
    });
    //search trong modal
    $('#formSearch').submit(function (e) {
        e.preventDefault();
        var keyword = $('#keyword').val();
        searchProducts(keyword, 0);
        isSearching = true;
    });

    //search ngoài modal
    $('#formSearchMaSPCT').submit(function (e) {
        e.preventDefault();
        var keyword = $('#maSPCTSearch').val();
        if (keyword.trim() !== '') {
            searchByMaSPCT(keyword);
        } else {
            Swal.fire({
                title: "Vui lòng nhập dữ liệu tìm kiếm",
                icon: "info",


            });
        }


    });

    $(document).on('click', '.page-link', function (event) {
        event.preventDefault();
        var page = $(this).data('page');
        if (isSearching) {
            searchProducts($('#keyword').val(), page);
        } else if (isFillter) {
            filterProducts($('#danhMucSearch').val(), $('#kichThuocSearch').val(), encodeURIComponent($('#mauSacSearch').val()), $('#thuongHieuSearch').val(), page);
        } else {
            fetchProducts(page);
        }


    });

    $(document).on('click', '.aBtn', function () {
        var spctId = $(this).data('id');
        console.log(spctId);
        $.get('/giao_dich/viewOne/?id=' + spctId, function (spct) {
            $('#formAddGH #maSPCT').text(spct.ma);
            $('#formAddGH #ktSPCT').text(spct.kichThuoc.ten);
            $('#formAddGH #msSPCT').text(spct.mauSac.ten).css('background-color', spct.mauSac.ten);
            $('#formAddGH #idSPCT').val(spct.id);
            $('#formAddGH #slGH').val(1);
            $('#spct').modal('hide');
            $('#addGH').modal('show');
            modalHide();

        });

    });


});

function modalHide() {
    $(document).on('click', '.cBtn', function () {
        $('#spct').modal('show');
        $('#addGH').modal('hide');// Hiển thị lại modal đầu tiên khi modal thứ hai ẩn
    });

}

//hàm filter
function filterProducts(danhMucId, kichThuocId, mauSacId, thuongHieuId, page = 0) {


    var url = '/giao_dich/filter?danhMucSearch=' + danhMucId +
        '&kichThuocSearch=' + kichThuocId +
        '&mauSacSearch=' + mauSacId +
        '&thuongHieuSearch=' + thuongHieuId +
        '&p=' + page;
    $.ajax({
        type: 'GET',
        url: url,
        success: function (data) {
            if (data) {
                $('#table1 tbody').empty();
                $.each(data.content, function (index, spct) {
                    $('#table1 tbody').append(`
   
                                                <tr>
                                                   
                                                    <td>${index + 1}</td>
                                                    <td >${spct.ma}</td>
                                                    <td >${spct.sanPham.danhMuc.ten}</td>
                                                    <td >${spct.sanPham.thuongHieu.ten}</td>
                                                    <td>${spct.kichThuoc.ten}</td>
                                                    <td>
                                                        <div class="badge" style="background-color:${spct.mauSac.ten};">${spct.mauSac.ten}</div>
                                                    </td>
                                                    <td >${spct.sl}</td>
                                                    <td >${spct.gia}</td>
                                                    <td><a class="btn btn-outline-warning aBtn" data-bs-toggle="modal" data-bs-target="#addGH" data-id="${spct.id}"><i data-feather="shopping-cart"></i></a></td>
                                                </tr>

                    `);
                });
                updatePagination(data);
            } else {
                Swal.fire({
                    title: "Không tìm thấy sản phẩm ",
                    icon: "info",


                });
            }

            feather.replace();
        },

    });
}


//hàm search ngoài modal
function searchByMaSPCT(keyword) {

    var url = '/giao_dich/viewOneByMa?maSPCTSearch=' + keyword;
    $.ajax({
        type: 'GET',
        url: url,
        success: function (data) {
            if (data) {
                $.get(url, function (spct) {
                    $('#formAddGH #maSPCT').text(spct.ma);
                    $('#formAddGH #ktSPCT').text(spct.kichThuoc.ten);
                    $('#formAddGH #msSPCT').text(spct.mauSac.ten).css('background-color', spct.mauSac.ten);
                    $('#formAddGH #idSPCT').val(spct.id);
                    $('#formAddGH #slGH').val(1);
                    $('#addGH').modal('show');


                });
            } else {
                Swal.fire({
                    title: "Không tìm thấy sản phẩm nào có mã " + keyword,
                    icon: "info",


                });
            }

        }
    });


}

//hàm search trong modal
function searchProducts(keyword, page = 0) {

    var url = '/giao_dich/search?keyword=' + keyword + '&p=' + page;
    $.ajax({
        type: 'GET',
        url: url,
        success: function (data) {
            if (data) {
                $('#table1 tbody').empty();
                // Add new data
                $.each(data.content, function (index, spct) {
                    $('#table1 tbody').append(`
   
                                                <tr>
                                                    <td>${index + 1}</td>
                                                    <td >${spct.ma}</td>
                                                    <td >${spct.sanPham.danhMuc.ten}</td>
                                                    <td >${spct.sanPham.thuongHieu.ten}</td>
                                                    <td>${spct.kichThuoc.ten}</td>
                                                    <td>
                                                        <div class="badge" style="background-color:${spct.mauSac.ten};">${spct.mauSac.ten}</div>
                                                    </td>
                                                    <td >${spct.sl}</td>
                                                    <td >${spct.gia}</td>
                                                    <td><a class="btn btn-outline-warning aBtn" data-bs-toggle="modal" data-bs-target="#addGH" data-id="${spct.id}"><i data-feather="shopping-cart"></i></a></td>

                                                </tr>

                    `);
                });
                updatePagination(data);
            } else {
                Swal.fire({
                    title: "Không tìm thấy sản phẩm nào có mã " + keyword,
                    icon: "info",
                });
            }

            feather.replace();
        }
    });

}

//hàm fillTableSPCT
function fetchProducts(page = 0) {

    var url = '/giao_dich/spct?p=' + page;
    $.ajax({
        type: 'GET',
        url: url,
        success: function (data) {
            // Clear previous data
            $('#table1 tbody').empty();
            // Add new data
            $.each(data.content, function (index, spct) {
                $('#table1 tbody').append(`
   
                                                <tr>
                                                    <td>${index + 1}</td>
                                                    <td >${spct.ma}</td>
                                                    <td >${spct.sanPham.danhMuc.ten}</td>
                                                    <td >${spct.sanPham.thuongHieu.ten}</td>
                                                    <td>${spct.kichThuoc.ten}</td>
                                                    <td>
                                                        <div class="badge" style="background-color:${spct.mauSac.ten};">${spct.mauSac.ten}</div>
                                                    </td>
                                                    <td >${spct.sl}</td>
                                                    <td >${spct.gia}</td>
                                                    <td><a class="btn btn-outline-warning aBtn" data-bs-toggle="modal" data-id="${spct.id}"><i data-feather="shopping-cart"></i></a></td>

                                                </tr>

                    `);
            });
            updatePagination(data);
            feather.replace();
        }
    });
}

//hàm phân trang
function updatePagination(data) {
    var totalPages = data.totalPages;
    var currentPage = data.number + 1; // Trang hiện tại (đánh số từ 1)

    $('#page').empty();

    // Chỉ hiển thị nút "Previous" nếu không phải là trang đầu tiên
    if (currentPage > 1) {
        var prevPageLink = $('<a>', {
            class: 'page-link',
            href: '#',
            'data-page': currentPage - 2 // Giảm 2 để lấy trang trước đó
        }).text('Previous');
        var prevPageItem = $('<li>', {
            class: 'page-item'
        }).append(prevPageLink);
        $('#page').append(prevPageItem);
    }

    // Hiển thị số trang từ trang hiện tại - 2 đến trang hiện tại + 2
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


//render tab
$(document).ready(function () {
    //closeTab
    $(document).on('click', '.close-tab', function (e) {
        e.preventDefault();
        var tabId = $(this).closest('a').attr('aria-controls');
        var $tab = $(this).closest('li');


        if ($('#myTab .nav-item').length > 1) {
            $tab.remove();

            if ($('.nav-item').find('.active').length === 0) {
                $('.nav-item:first-child').find('a').addClass('active');
                $('.tab-content').find('.tab-pane:first-child').addClass('show active');
            }
        } else {
            Swal.fire("Cần có ít nhất 1 hóa đơn");
        }
    });



    //addTab
    $('#add-tab').click(function () {
        var tabCount = $('#myTab .nav-item').length;

        if (tabCount < 6) {
            var newTabId = 'hoa_don' + (tabCount + 1);
            var newNavItem = '<li class="nav-item"><a class="nav-link" data-bs-toggle="tab" data-bs-target="#' + newTabId + '" href="#' + newTabId + '" role="tab" aria-controls="' + newTabId + '" aria-selected="false">Hóa đơn ' + (tabCount + 1) + ' <i class="close close-tab" data-feather="x-circle"></i></a></li>';

            $('#myTab').append(newNavItem);
            feather.replace();

            $('#' + newTabId).tab('show');
            $('#myTab .nav-item').removeClass('active');
            $('#' + newTabId).parent().addClass('active');
        } else {
            Swal.fire("chỉ có thể tạo tối đa 6 hóa đơn!");
        }
    });


});

//fillModalAddGH




