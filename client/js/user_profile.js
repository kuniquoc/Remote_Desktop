document.addEventListener('DOMContentLoaded', function() {
    // Check if the user is logged in by checking the token
    const token = localStorage.getItem('token');

    if (!token) {
        // Redirect to login page if token is not found
        window.location.href = '../views/login.html';
    }
    
    const profileForm = document.getElementById('profileForm');
    const passwordForm = document.getElementById('passwordForm');
    const profileMessage = document.getElementById('profile-message');
    const passwordMessage = document.getElementById('password-message');

    // Xem thông tin tài khoản
    fetch('http://localhost:8080/api/user', {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Failed to fetch user info, status code: ' + response.status);
        }
        return response.json();
    })
    .then(data => {
        if (data && Object.keys(data).length > 0) {
            document.getElementById('full_name').value = data.full_name || '';
            document.getElementById('birthday').value = data.birthday || '';
            document.getElementById('email').value = data.email || '';
            document.getElementById('phone').value = data.phone || '';
            document.getElementById('gender').value = data.gender || '';
        } else {
            profileMessage.textContent = 'Error: User data is empty or could not be fetched.';
        }
    })
    .catch(error => {
        profileMessage.textContent = 'Error fetching user info: ' + error.message;
    });


    // Cập nhật thông tin tài khoản
    profileForm.addEventListener('submit', function(e) {
        e.preventDefault();
        const updatedInfo = {
            full_name: document.getElementById('full_name').value,
            birthday: document.getElementById('birthday').value,
            email: document.getElementById('email').value,
            phone: document.getElementById('phone').value,
            gender: document.getElementById('gender').value
        };

        fetch('http://localhost:8080/api/user/profile', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            },
            body: JSON.stringify(updatedInfo)
        })
        .then(response => response.json())
        .then(data => {
            if (data.message === 'Profile updated successfully') {
                profileMessage.textContent = 'Cập nhật tài khoản thành công!';
            } else {
                profileMessage.textContent = 'Có lỗi xảy ra khi cập nhật tài khoản.';
            }
        })
        .catch(error => {
            profileMessage.textContent = 'Có lỗi xảy ra khi cập nhật tài khoản: ' + error.message;
        });
        
    });

    // Đổi mật khẩu
    passwordForm.addEventListener('submit', function(e) {
        e.preventDefault();
        const currentPassword = document.getElementById('current_password').value;
        const newPassword = document.getElementById('new_password').value;
        const confirmPassword = document.getElementById('confirm_password').value;

        if (newPassword !== confirmPassword) {
            passwordMessage.textContent = 'Mật khẩu xác nhận không đúng!';
            return;
        }

        const passwordData = {
            current_password: currentPassword,
            new_password: newPassword
        };

        fetch('http://localhost:8080/api/user/password', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            },
            body: JSON.stringify(passwordData)
        })
        .then(response => response.json())
        .then(data => {
            passwordMessage.textContent = 'Đổi mật khẩu thành công!';
        })
        .catch(error => {
            passwordMessage.textContent = 'Có lỗi xảy ra khi cập nhật mật khẩu: ' + error.message;
        });
    });
});
