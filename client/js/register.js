document.getElementById('registerForm').addEventListener('submit', async function(e) {
    e.preventDefault();
    
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    const registerData = {
        username: username,
        password: password
    };

    try {
        const response = await fetch('http://localhost:8080/api/auth/signup', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(registerData)
        });

        let result;
        const contentType = response.headers.get("content-type");

        if (contentType && contentType.includes("application/json")) {
            result = await response.json();
        } else {
            result = await response.text();
        }

        if (response.ok) {
            document.getElementById('success-message').innerText = 'Đăng ký thành công!';
            document.getElementById('success-message').style.display = 'block';

            // Delay 2s khi đăng ký thành công, rồi chuyển qua trang home.html
            setTimeout(function() {
                window.location.href = '../views/home.html'; 
            }, 2000);
        } else {
            if (response.status === 409) {
                document.getElementById('error-message').innerText = 'Tên người dùng đã tồn tại.';
                document.getElementById('error-message').style.display = 'block';
            } else if (response.status === 500) {
                document.getElementById('error-message').innerText = 'Lỗi Server, vui lòng thử lại sau.';
                document.getElementById('error-message').style.display = 'block';
            } else {
                document.getElementById('error-message').innerText = 'Đã có lỗi xảy ra.';
                document.getElementById('error-message').style.display = 'block';
            }
            }
    } catch (error) {
        console.error('Error:', error);
        document.getElementById('error-message').innerText = 'Đã có lỗi xảy ra, vui lòng thử lại sau!';
        document.getElementById('error-message').style.display = 'block';
    }
});
