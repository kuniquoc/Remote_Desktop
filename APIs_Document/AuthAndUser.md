## API Authentication

### Sign Up
**Method:** POST  
**URL:** /api/auth/signup  
**Request Body (JSON):**
```json
{
    "username": "your_username",
    "password": "your_password"
}
```  
**Response Body (JSON):**
```json
{
    "message": "Đăng ký người dùng thành công",
}
```
**Status Code:**  
- **201 Created**: Tạo tài khoản thành công.  
- **409 Conflict**: Tên người dùng đã tồn tại.  
- **500 Internal Server Error**: Lỗi server.

---

### Sign In
**Method:** POST  
**URL:** /api/auth/signin  
**Request Body (JSON):**
```json
{
    "username": "your_username",
    "password": "your_password"
}
```  
**Response Body (JSON):**
```json
{
    "message": "Đăng nhập thành công",
    "data": {
        "token": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcyNzE5MTQ3MiwiZXhwIjoxNzI3Mjc3ODcyfQ.HzPRUi2YJm-7EthMo4KTHlUOeNmGocfTE2KEzNsPwVgYB5VesV0gP3KOHn2zHdp-",
        "roles": [
            {
                "authority": "ROLE_USER"
            },
            {
                "authority": "ROLE_ADMIN"
            }
        ]
    }
}
```
**Status Code:**  
- **200 OK**: Đăng nhập thành công.  
- **404 Not Found**: Không tìm thấy người dùng.

---

## API User Management

### Get User Information (Authenticated)
**Method:** GET  
**URL:** /api/user/profile 
**Response Body (JSON):**
```json
{
    "message": "Thông tin người dùng được lấy thành công",
    "data": {
        "fullName": "John Doe",
        "birthday": "1990-01-01",
        "email": "johndoe@example.com",
        "phone": "1234567890",
        "gender": "Male"
    }
}
```
**Status Code:**  
- **200 OK**: Yêu cầu thành công.  
- **404 Not Found**: Không tìm thấy user  
- **403 Forbidden**: Không có quyền truy cập.  
- **500 Internal Server Error**: Lỗi server xảy ra.

---

### Update User Profile (Authenticated)
**Method:** PUT  
**URL:** /api/user/profile  
**Request Body (JSON):**
```json
{
    "fullName": "John Doe",
    "birthday": "1990-01-01",
    "email": "johndoe@example.com",
    "phone": "1234567890",
    "gender": "Male"
}
```  
**Response Body (JSON):**
```json
{
    "message": "Cập nhật thông tin người dùng thành công",
}
```
**Status Code:**  
- **200 OK**: Cập nhật thành công.  
- **404 Not Found**: Không tìm thấy user    
- **403 Forbidden**: Không có quyền truy cập.  
- **500 Internal Server Error**: Lỗi server xảy ra.

---

### Update User Password (Authenticated)
**Method:** PUT  
**URL:** /api/user/password  
**Request Body (JSON):**
```json
{
    "newPassword": "your_new_password"
}
```  
**Response Body (JSON):**
```json
{
    "message": "Cập nhật mật khẩu người dùng thành công",
}
```
**Status Code:**  
- **200 OK**: Cập nhật mật khẩu thành công.  
- **404 Not Found**: Không tìm thấy user    
- **403 Forbidden**: Không có quyền truy cập.  
- **500 Internal Server Error**: Lỗi server xảy ra.

---

### Delete User (Authenticated)
**Method:** DELETE  
**URL:** /api/user  
**Response Body:**  
```json
no content
```
**Status Code:**  
- **200 OK**: Xoá thành công.  
- **400 Bad Request**: Dữ liệu gửi không hợp lệ.  
- **403 Forbidden**: Không có quyền truy cập.  
- **500 Internal Server Error**: Lỗi server xảy ra.

---

## Admin-Only API

### Get List of Users (Admin Role)
**Method:** GET  
**URL:** /api/users  
**Response Body (JSON):**
```json
{
    "message": "Thông tin các người dùng được lấy thành công",
    "data": [
        {
            "id": 1,
            "roles": [
                {"id": 1, "name": "ROLE_USER"},
                {"id": 2, "name": "ROLE_ADMIN"}
            ],
            "fullName": "John Doe",
            "birthday": "1990-01-01",
            "email": "johndoe@example.com",
            "phone": "1234567890",
            "gender": "Male"
        }
        // ... other users
    ]
}
```
**Status Code:**  
- **200 OK**: Yêu cầu thành công và trả về danh sách người dùng.   
- **403 Forbidden**: Không có quyền truy cập.  
- **500 Internal Server Error**: Lỗi server xảy ra.

---

### Get User Information by ID (Admin Role)
**Method:** GET  
**URL:** /api/users/{id}  
**Response Body (JSON):**
```json
{
    "message": "Thông tin người dùng được lấy thành công",
    "data": {
        "id": 1,
        "roles": [
            {"id": 1, "name": "ROLE_USER"},
            {"id": 2, "name": "ROLE_ADMIN"}
        ],
        "fullName": "John Doe",
        "birthday": "1990-01-01",
        "email": "johndoe@example.com",
        "phone": "1234567890",
        "gender": "Male"
    }
}
```
**Status Code:**  
- **200 OK**: Yêu cầu thành công và trả về thông tin người dùng.  
- **404 Not Found**: Không tìm thấy người dùng với ID đã cho.  
- **403 Forbidden**: Không có quyền truy cập.  
- **500 Internal Server Error**: Lỗi server xảy ra.

---

### Update User Password (Admin Role)
**Method:** PUT  
**URL:** /api/users/{id}/password  
**Request Body (JSON):**
```json
{
    "newPassword": "your_new_password"
}
```  
**Response Body (JSON):**
```json
{
    "message": "Cập nhật mật khẩu người dùng thành công",
}
```
**Status Code:**  
- **200 OK**: Cập nhật mật khẩu thành công.  
- **404 Not Found**: Không tìm thấy người dùng với ID đã cho.  
- **403 Forbidden**: Không có quyền truy cập.   
- **500 Internal Server Error**: Lỗi server xảy ra.

---

### Delete User (Admin Role)
**Method:** DELETE  
**URL:** /api/users/{id}  
**Response Body:**  
```json
no content
```
**Status Code:**  
- **204 No Content**: Xóa thành công, không có nội dung trả về.  
- **404 Not Found**: Không tìm thấy người dùng với ID đã cho.  
- **403 Forbidden**: Không có quyền truy cập.  
- **500 Internal Server Error**: Lỗi server xảy ra.

---

### Get All Roles (Admin Role)
**Method:** GET  
**URL:** /api/users/roles  
**Response Body (JSON):**
```json
{
    "message": "Quyền tài khoản cập nhật thành công",
    "data": [
        {"id": 1, "name": "ROLE_USER"},
        {"id": 2, "name": "ROLE_ADMIN"}
    ]
}
```
**Status Code:**  
- **200 OK**: Yêu cầu thành công và trả về danh sách vai trò.  
- **403 Forbidden**: Không có quyền truy cập.  
- **500 Internal Server Error**: Lỗi server xảy ra.

---

### Update User Roles (Admin Role)
**Method:** PUT  
**URL:** /api/users/{id}/role  
**Request Body (JSON):**
```json
{
    "roles": [
        {"id": 1, "name": "ROLE_USER"},
        {"id": 2, "name": "ROLE_ADMIN"}
    ]
}
```  
**Response Body (JSON):**
```json
{
    "message": "Cập nhật quyền tài khoản thành công",
    "data": {
        "roles": [
            {"id": 1, "name": "ROLE_USER"},
            {"id": 2, "name": "ROLE_ADMIN"}
        ]
    }
}
```
**Status Code:**  
- **200 OK**: Cập nhật vai trò thành công.  
- **404 Not Found**: Không tìm thấy người dùng với ID đã cho.  
- **403 Forbidden**: Không có quyền truy cập.  
- **500 Internal Server Error**: Lỗi server xảy ra.

