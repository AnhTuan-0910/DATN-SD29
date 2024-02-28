
//render kt
    $(document).ready(function () {
    var selectedItems = [];

    $('.itemKT').click(function () {
    var id = $(this).data('idkt');
    var item = $(this).data('item');
    if (selectedItems.indexOf(item) === -1) {
    selectedItems.push(item);

    $('#selectedKT').append('<div class="btn btn-outline-primary selected-itemKT"  style="margin-top: 5px;" " data-item="' + item + '">' + item + '<i style="padding-left: 5px" data-feather="x"></i><input name="idkt" value="'+id+'" hidden ></div> &nbsp;');
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
    $('#selectedMS').append('<div class="btn btn-outline-primary selected-itemMS " style="margin-top: 5px;background-color:' + item + '" data-item="' + item + '" >' + item + '<i style="padding-left: 5px" data-feather="x"></i><input name="idms" value="'+id+'" hidden></div> &nbsp;');
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
$(document).ready(function(){
    // Lắng nghe sự kiện click trên nút tùy chỉnh
    $('.custom-file-upload').on('click', function(){
        // Kích hoạt sự kiện click trên input thực sự trong hàng chứa nút tùy chỉnh
        $(this).siblings('.imageInput').click();
    });
});

$(document).ready(function(){
    // Lắng nghe sự kiện change trên mọi input có class 'imageInput'
    $('.imageInput').on('change', function(event) {
        var files = event.target.files;
        var imagePreviewContainer = $(this).siblings('.imagePreviewContainer')[0];
        var maxFiles = 1; // Số lượng tệp tối đa được chọn

        // Kiểm tra số lượng tệp đã chọn
        if (files.length > maxFiles) {
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "Bạn chỉ có thể chọn tối đa " +maxFiles+ " tệp ảnh",
            });

            event.target.value = ''; // Xóa tất cả các tệp đã chọn
            return;
        }

        // Xóa tất cả các thẻ <img> hiện có trong container
        imagePreviewContainer.innerHTML = '';

        // Duyệt qua từng file và hiển thị hình ảnh
        for (var i = 0; i < files.length; i++) {
            var file = files[i];
            if (!file.type.startsWith('image/')){ continue }

            var reader = new FileReader();
            var img = document.createElement('img');
            img.style.maxWidth = '50px';
            img.style.maxHeight = '50px';

            reader.onload = (function(theImg) {
                return function(e) {
                    theImg.src = e.target.result;
                };
            })(img);

            reader.readAsDataURL(file);
            imagePreviewContainer.appendChild(img);
        }
    });
});