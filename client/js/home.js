const token = localStorage.getItem('token');

if (!token) {
    // Nếu không có token, chuyển hướng về trang index.html
    window.location.href = '../views/index.html';
}

document.getElementById('logout-btn').addEventListener('click', function() {
    // Xóa token khỏi localStorage khi đăng xuất
    localStorage.removeItem('token'); 

    window.location.href = '../views/index.html';
});
