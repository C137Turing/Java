// $(document).ajaxSend(function (event, xhr, options) {
//     xhr.setRequestHeader("user_token", localStorage.getItem("userToken"));
// });
//
// $(document).ajaxError(
//     function(event, xhr, options, exc) {
//         if (xhr.status == 401) {
//             console.log("跳转到登录页面");
//             location.href = "blog_login.html";
//         }
// });
// $(document).ajaxSend(function (event, xhr, options) {
//     var userToken = localStorage.getItem("userToken");
//     // 只有当 token 存在且不为 "null" 时才设置请求头
//     if (userToken && userToken !== "null" && userToken !== "undefined") {
//         xhr.setRequestHeader("user_token", userToken);
//     }
// });
//
// $(document).ajaxError(function (event, xhr, options, exc) {
//     console.log("AJAX错误状态码:", xhr.status, "URL:", options.url);
//
//     if (xhr.status === 401) {
//         console.log("未授权，跳转到登录页面");
//         // 清除本地存储的 token
//         localStorage.removeItem("userToken");
//         // 跳转到登录页
//         window.location.href = "blog_login.html";
//     } else if (xhr.status >= 500) {
//         console.error("服务器错误:", xhr.status);
//         alert("服务器繁忙，请稍后重试");
//     }
// });
//
// // 添加页面加载时的 token 检查
// $(document).ready(function() {
//     var userToken = localStorage.getItem("userToken");
//     if (!userToken || userToken === "null" || userToken === "undefined") {
//         console.log("未检测到登录信息，准备跳转到登录页");
//         // 可以在这里添加跳转逻辑，或者等待第一个 401 响应
//     }
// });
$(document).ajaxSend(function (event, xhr, options) {
    var userToken = localStorage.getItem("userToken");

    // 更严格的token验证
    if (isValidToken(userToken)) {
        xhr.setRequestHeader("user_token", userToken);
    }
    // 不加else，让后端处理无token情况
});

function isValidToken(token) {
    return token &&
        token !== "null" &&
        token !== "undefined" &&
        token.length > 10; // 基本格式检查
}

$(document).ajaxError(function (event, xhr, options, exc) {
    console.log("AJAX错误状态码:", xhr.status, "URL:", options.url);

    // 只处理特定URL的401错误，避免静态资源请求误跳转
    if (xhr.status === 401 &&
        options.url &&
        !options.url.includes('.css') &&
        !options.url.includes('.js') &&
        !options.url.includes('.jpg') &&
        !options.url.includes('.png')) {

        console.log("未授权，跳转到登录页面");
        localStorage.removeItem("userToken");
        setTimeout(() => {
            window.location.href = "blog_login.html";
        }, 100);
    }
});