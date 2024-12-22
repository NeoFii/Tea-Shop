function addCart(id) {
    let data = {
        proid: id
    }
    $.ajax({
        url: '/cart/create',
        type: 'post',
        data: JSON.stringify(data),
        contentType: "application/json;charset=UTF-8",
        dataType: 'json',
        success: function (res) {
            alert("加入成功");
        },
        error: function (res) {
            alert("加入失败");
        }
    });
}

function delCart(id) {
    $.ajax({
        url: '/cart/delete/'+id,
        type: 'post',
        // data: JSON.stringify(data),
        contentType: "application/json;charset=UTF-8",
        dataType: 'json',
        success: function (res) {
            alert("删除成功");
            setTimeout(window.location.reload(), 2000);
        },
        error: function (res) {
            alert("删除失败");
        }
    });
}

function delCartAll() {
    $.ajax({
        url: '/cart/deleteAll',
        type: 'post',
        // data: JSON.stringify(data),
        contentType: "application/json;charset=UTF-8",
        dataType: 'json',
        success: function (res) {
            alert("清空成功");
            setTimeout(window.location.reload(), 2000);
        },
        error: function (res) {
            alert("清空失败");
        }
    });
}

function updateCart(id, flg) {
    setTimeout(function () {
        let data = {
            carid: id,
            quantity: $('#qty-val-' + id).text()
        }
        $.ajax({
            url: '/cart/update/' + id,
            type: 'post',
            data: JSON.stringify(data),
            contentType: "application/json;charset=UTF-8",
            dataType: 'json',
            success: function (res) {
                // alert("加入成功");
            },
            error: function (res) {
                // alert("加入失败");
            }
        });
    }, 1000);
}

function addOrder() {
    let data = {
    }
    $.ajax({
        url: '/order/create',
        type: 'post',
        data: JSON.stringify(data),
        contentType: "application/json;charset=UTF-8",
        dataType: 'json',
        success: function (res) {
            alert("支付成功");
            setTimeout(location.href='/url/info', 2000);
        },
        error: function (res) {
            alert("支付失败");
        }
    });
}

function updateInfo() {
    let data = {
        address: $('#useraddress').val(),
        realname: $('#realname').val(),
        phone: $('#phone').val()
    }
    $.ajax({
        url: '/user/update',
        type: 'post',
        data: JSON.stringify(data),
        contentType: "application/json;charset=UTF-8",
        dataType: 'json',
        success: function (res) {
            alert("保存成功");
        },
        error: function (res) {
            alert("保存失败");
        }
    });
}

function showReview(id) {
    location.href='/url/review/'+id;
}

function addReview() {
    let data = {
        orderitemid: $('#orderitemid').text(),
        content: $('#content').val()
    }
    $.ajax({
        url: '/review/create',
        type: 'post',
        data: data,
        // contentType: "application/json;charset=UTF-8",
        dataType: 'json',
        success: function (res) {
            alert("提交成功");
            setTimeout(location.href='/url/orderdetail/'+data.orderitemid, 2000);
        },
        error: function (res) {
            alert("提交失败");
        }
    });
}

function searchProduct(pageNum, initFlg) {
    let data = {
        keyword: $('#keyword').val(),
        pageNum: pageNum,
        initFlg: initFlg
    }
    if (initFlg) {
        location.href='/url/prolist/search?keyword='+data.keyword+'&pageNum='+data.pageNum+'&initFlg='+data.initFlg;
    } else {
        $.ajax({
            url: '/url/prolist/search',
            type: 'get',
            data: data,
            // contentType: "application/json;charset=UTF-8",
            // dataType: 'json',
            success: function (res) {
                $('#container').html(res);
                $('#keyword').val($('#hideKeyword').text());
            },
            error: function (res) {
                alert("检索失败");
            }
        });
    }
}

function userLogin() {
    let data = {
        username: $('#username').val(),
        password: $('#password').val()
    }
    if (data.username == '' || data.password == '') {
        alert("信息未输入！");
    } else {
        $.ajax({
            url: '/user/login',
            type: 'post',
            data: JSON.stringify(data),
            contentType: "application/json;charset=UTF-8",
            dataType: 'json',
            success: function (res) {
                if (res.code == 200) {
                    alert("登录成功");
                    if (res.data.role == 1) {
                        setTimeout(location.href='/url/index', 2000);
                    } else {
                        setTimeout(location.href='/url/promanage', 2000);
                    }
                } else {
                    alert("账号密码错误");
                }
            },
            error: function (res) {
                alert("登录失败");
            }
        });
    }
}

function userRegister() {
    let data = {
        username: $('#username').val(),
        phone: $('#phone').val(),
        password: $('#password').val()
    }
    if (data.username == '' || data.phone == '' || data.password == '') {
        alert("注册失败");
    } else {
        $.ajax({
            url: '/user/register',
            type: 'post',
            data: JSON.stringify(data),
            contentType: "application/json;charset=UTF-8",
            dataType: 'json',
            success: function (res) {
                if (res.code == 200) {
                    alert("注册成功");
                    setTimeout(location.href='/url/index', 2000);
                }
            },
            error: function (res) {
                alert("注册失败，账号已存在");
            }
        });
    }
}

function editProduct(id) {
    location.href='/url/promanage/detail?id='+id;
}

function createProduct() {
    location.href='/url/promanage/detail';
}

function saveProduct() {
    let formData = new FormData($("#info-form")[0]);
    $.ajax({
        type: "post",
        url:"/product/create",
        cache: false,
        data: formData,
        contentType: false,
        processData: false,
        success: function (res) {
            alert('保存成功');
            setTimeout(location.href='/url/promanage', 2000);
        },
        error: function(res) {
            alert('保存失败');
        }
    });
}

function deleteProduct(id) {
    if (confirm("确认删除该条记录吗？")) { //if语句内部判断确认框
        $.ajax({
            type: "post",
            url:"/product/delete/"+id,
            cache: false,
            contentType: "application/json;charset=UTF-8",
            success: function (res) {
                alert('删除成功');
                setTimeout(location.href='/url/promanage', 2000);
            },
            error: function(res) {
                alert('删除失败');
            }
        });
    }
}

function editUser(id) {
    location.href='/url/usermanage/detail?id='+id;
}

function createUser() {
    location.href='/url/usermanage/detail';
}

function saveUser() {
    let formData = new FormData($("#info-form")[0]);
    $.ajax({
        type: "post",
        url:"/user/create",
        cache: false,
        data: formData,
        contentType: false,
        processData: false,
        success: function (res) {
            alert('保存成功');
            setTimeout(location.href='/url/usermanage', 2000);
        },
        error: function(res) {
            alert('保存失败');
        }
    });
}

function deleteUser(id) {
    if (confirm("确认删除该条记录吗？")) { //if语句内部判断确认框
        $.ajax({
            type: "post",
            url:"/user/delete/"+id,
            cache: false,
            contentType: "application/json;charset=UTF-8",
            success: function (res) {
                alert('删除成功');
                setTimeout(location.href='/url/usermanage', 2000);
            },
            error: function(res) {
                alert('删除失败');
            }
        });
    }
}

function editOrder(id) {
    location.href='/url/ordermanage/detail?id='+id;
}

function saveOrder() {
    let data = {
        orderid: $('#orderid').val(),
        status: $('#status').val()
    }
    $.ajax({
        url: '/order/update',
        type: 'post',
        data: JSON.stringify(data),
        contentType: "application/json;charset=UTF-8",
        dataType: 'json',
        success: function (res) {
            alert('保存成功');
            setTimeout(location.href='/url/ordermanage', 2000);
        },
        error: function (res) {
            alert('保存失败');
        }
    });
}