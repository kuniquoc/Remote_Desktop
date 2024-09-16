document.getElementById('registerForm').addEventListener('submit', async function(e) {
    e.preventDefault();
    
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    // if (password !== confirmPassword) {
    //     document.getElementById('error-message').innerText = "Passwords do not match";
    //     document.getElementById('error-message').style.display = 'block';
    //     return;
    // }

    // Confirm password - Nhập mật khẩu lần 2

    const registerData = {
        username: username,
        password: password
    };
    
    try {
        const response = await fetch('/api/auth/signup', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(registerData)
        });

        const result = await response.json();

        if (response.ok) {
            window.location.href = 'http://127.0.0.1:5500/Student_Management/client/views/home.html'; 
        } else {
            document.getElementById('error-message').innerText = result.message || 'Registration failed';
            document.getElementById('error-message').style.display = 'block';
        }
    } catch (error) {
        console.error('Error:', error);
        document.getElementById('error-message').innerText = 'Thông tin tài khoản không hợp lệ!';
        document.getElementById('error-message').style.display = 'block';
    }
});
