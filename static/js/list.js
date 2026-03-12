function logout(){
    localStorage.removeItem("userToken");
    localStorage.removeItem("loginUserId");
    location.href="blog_login.html";
}