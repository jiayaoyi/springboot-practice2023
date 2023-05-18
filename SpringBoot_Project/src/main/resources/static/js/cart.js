$(document).ready(function() {
    //初始化总金额
    updateTotalAmount();
    //返回继续购物按钮
    $('#continue-shopping').click(function() {
        window.history.back();
    });

    // 清空购物车按钮点击事件
    $('#clear-cart').click(function() {
        // 向后端发送请求
        $.ajax({
            type: 'POST',
            url: '/cart/clear',
            contentType: 'application/json',
            success: function(response) {
                // 处理成功
                if (response === "success") {
                    // 从页面上删除所有购物车项目
                    $('.cart-item').remove();
                    // 更新购物车总金额
                    updateTotalAmount();
                } else {
                    alert('清空购物车失败，请重试');
                }
            },
            error: function() {
                // 处理错误
                alert('请求失败，请重试');
            }
        });
    });


    // 修改数量按钮点击事件
    $('.edit-quantity').click(function() {
        var $button = $(this);
        var $itemQuantity = $button.closest('.cart-item').find('.item-quantity');
        var isEditing = $button.data('editing');

        if (!isEditing) {
            // 变为可编辑状态
            $itemQuantity.attr('contenteditable', 'true').focus();
            $button.text('保存');
            $button.data('editing', true);
        } else {
            // 保存更改
            var newQuantity = $itemQuantity.text().trim();
            var itemId = $button.closest('.cart-item').find('.item-number').text().trim();

            // 校验输入内容
            if (isNaN(newQuantity) || newQuantity <= 0) {
                alert('请输入有效的数量');
                return;
            }

            // 向后端发送请求
            $.ajax({
                type: 'POST',
                url: '/cart/changequantity',
                contentType: 'application/json',
                data: JSON.stringify({
                    item_id: itemId,
                    new_quantity: newQuantity
                }),
                success: function(response) {
                    // 处理成功
                    if (response === "success") {
                        // 更新商品总金额
                        var $itemPrice = $button.closest('.cart-item').find('.item-price');
                        var itemPrice = parseFloat($itemPrice.text());
                        var itemTotalAmount = itemPrice * newQuantity;
                        $button.closest('.cart-item').find('.item-total').text(itemTotalAmount.toFixed(2));

                        // 更新购物车总金额
                        updateTotalAmount();
                    } else {
                        alert('修改失败，请重试');
                    }
                },
                error: function() {
                    // 处理错误
                    alert('请求失败，请重试');
                }
            });

            // 变为不可编辑状态
            $itemQuantity.attr('contenteditable', 'false');
            $button.text('修改数量');
            $button.data('editing', false);
        }
    });

    // 退回此商品按钮点击事件
    $('.remove-item').click(function() {
        let $button = $(this);
        let itemId = parseInt($button.attr('data-item-id'));

        // 向后端发送请求
        $.ajax({
            type: 'POST',
            url: '/cart/removeitem',
            contentType: 'application/json',
            data: JSON.stringify({
                item_id: itemId
            }),
            success: function(response) {
                // 处理成功
                if (response === "success") {
                    // 从页面上删除该行商品信息
                    $button.closest('.cart-item').remove();
                    // 更新购物车总金额
                    updateTotalAmount();
                } else {
                    alert('退回失败，请重试');
                }
            },
            error: function() {
                // 处理错误
                alert('请求失败，请重试');
            }
        });
    });

    //生成订单点击事件
    $('#create-order').click(function (){
        //检查购物车是否为空
        if ($('.cart-item').length === 0) {
            alert('购物车为空，无法生成订单');
            return;
        }
        //向后端发送请求
        $.ajax({
            type : 'POST',
            url : '/cart/createorder',
            success: function(response) {
                //处理成功
                if (response==='success'){
                    alert('订单生成成功')
                    $('.cart-item').remove();
                    updateTotalAmount();
                }
                else if(response==='cartisnull'){
                    alert('购物车为空')
                }
                else {
                    alert('订单生成失败')
                }
            },
            error: function() {
                // 处理错误
                alert('请求失败，请重试');
            }

        })
    })
});

function updateTotalAmount() {
    var totalAmount = 0;

    $('.cart-item').each(function() {
        var $itemTotal = $(this).find('.item-total');
        var itemTotalAmount = parseFloat($itemTotal.text());
        totalAmount += itemTotalAmount;
    });

    $('#cart-total').text(totalAmount.toFixed(2));
}
