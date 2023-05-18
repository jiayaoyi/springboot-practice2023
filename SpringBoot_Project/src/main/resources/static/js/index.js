$(document).ready(function() {


    function updateProductList(pageNum) {
        // 向服务器发出请求，获取新的商品列表
        $.get('/shop/loadProducts', {pageNum: pageNum}, function(shopCardsList) {
            // 清除现有的商品卡片
            var productList = $('.product-list');
            productList.empty();

            // 根据新的商品列表添加商品卡片
            shopCardsList.forEach(function(shopinfo) {
                var productCard = `
        <div class="product-card">
            <img src="img/product-image.jpg" alt="商品图片">
            <div class="product-info">
                <h4 class="product-name">${shopinfo.shopName}</h4>
                <p class="product-price-original">原价：¥${shopinfo.oldPrice}</p>
                <p class="product-price-current">现价：¥${shopinfo.price}</p>
                <p class="product-description">${shopinfo.descr}</p>
                <button class="add-to-cart" data-product-id="${shopinfo.shopId}">加入购物车</button>

            </div>
        </div>
    `;
                productList.append(productCard);
            });


            // 更新当前页码
            $('.pagination .current').removeClass('current');
            $('.pagination .page-number').eq(pageNum - 1).addClass('current');
        });
    }

    $('#first-page').on('click', function() {
        updateProductList(1);
    });

    $('#last-page').on('click', function() {
        var pageCount = parseInt($('.pagination span:last-child').text());
        updateProductList(pageCount);
    });

    $('#prev-page').on('click', function() {
        var currentPage = parseInt($('.pagination .current').text());
        if (currentPage > 1) {
            updateProductList(currentPage - 1);
        }
    });

    $('#next-page').on('click', function() {
        var currentPage = parseInt($('.pagination .current').text());
        var pageCount = parseInt($('.pagination span:last-child').text());
        if (currentPage < pageCount) {
            updateProductList(currentPage + 1);
        }
    });

    $('.pagination .page-number').on('click', function() {
        var pageNum = parseInt($(this).text());
        updateProductList(pageNum);
    });
});

$(document).ready(function() {
    $('#order_search').on('click', function() {
        let orderNumber = $('#order_number').val().toString().trim();
        if (orderNumber.length > 0) {
            $.ajax({
                url: '/shop/orderState',
                type: 'GET',
                data: { orderNumber },
                dataType: 'text',
                success: function(data) {
                    alert(data);
                },
                error: function(xhr, status, error) {
                    alert('请输入正确的订单编号！' + error);
                }
            });

        } else {
            alert("请输入订单编号！")
        }
    });
    $('#logout-btn').on('click', function() {
        $.post('/shop/logout', {}, function(response) {
            window.location.href = '/login.html';
        });
    });

    $('.product-list').on('click', '.add-to-cart', function () {
        let shopId = $(this).data('product-id');
        let user_name = $('.welcome-msg a').text();
        $.ajax({
            type: 'POST',
            url: '/cart/addToCart',
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify({shopId: shopId, user_name: user_name}),
            success: function (response) {
                alert("添加成功");
            },
            error: function(xhr, status, error) {
                alert('请求失败！' + error);
            }
        });
    });


});


