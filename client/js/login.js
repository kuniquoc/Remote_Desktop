document.getElementById('loginForm').addEventListener('submit', async function(e) {
    e.preventDefault();
    
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    
    const loginData = {
        username: username,
        password: password
    };
    
    try {
        const response = await fetch('/api/auth/signin', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loginData)
        });

        const result = await response.json();

        if (response.ok) {
            window.location.href = '../views/home.html'; 
        } else {
            document.getElementById('error-message').innerText = result.message || 'Login failed';
            document.getElementById('error-message').style.display = 'block';
        }
    } catch (error) {
        console.error('Error:', error);
        document.getElementById('error-message').innerText = 'Thông tin tài khoản không hợp lệ!';
        document.getElementById('error-message').style.display = 'block';
    }
});
