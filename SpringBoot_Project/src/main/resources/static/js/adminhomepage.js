$(document).ready(function() {
    console.log('Document is ready');

    $('.order-list').on('click', '.detail-button', function() {
        var orderId = $(this).attr('data-order-id');
        var popup = $('#detail-' + orderId).clone();

        // 创建弹窗
        var popupOverlay = $('<div class="popup-overlay"></div>');
        var popupContent = $('<div class="popup-content"></div>');
        var popupClose = $('<span class="popup-close">&times;</span>');

        popupContent.append(popupClose, popup);
        popupOverlay.append(popupContent);
        $('body').append(popupOverlay);

        // 关闭弹窗
        popupOverlay.click(function(e) {
            if (e.target === this) {
                popupOverlay.remove();
            }
        });

        // 显示弹窗
        popupOverlay.show();
    });


    $(".order-item").each(function() {
        console.log('Calculating total amount for an order item');

        var totalAmount = 0;

        $(this).find(".detail-item").each(function() {
            var quantity = parseFloat($(this).find("span:nth-child(4)").text());
            var price = parseFloat($(this).find("span:nth-child(3)").text());
            totalAmount += quantity * price;
        });

        $(this).find(".order-total-amount").text(totalAmount.toFixed(2));
    });
});
