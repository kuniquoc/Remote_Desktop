# API Authentication
## Đăng ký (Sign Up)
Method: POST
URL: /api/auth/signup
Payload:
{
  "username": "string",
  "password": "string"
}

Response:
{

    "message": "User registered successfully"
}

## Đăng nhập (Sign In)
Method: POST
URL: /api/auth/signin
Payload:
{
    "username": "string",
    "password": "string"
}

Response:
{
    "token": "string"
}

# API Quản Lý người dùng

## Lấy thông tin người dùng
Method: GET
URL: /api/user
Response:
{
    "fullName": "string",
    "birthday": "yyyy-mm-dd",
    "email": "string",
    "phone": "string",
    "gender": "string"
}

## Cập nhật thông tin người dùng
Method: PUT
URL: /api/user/profile
Payload:
{
    "fullName": "string",
    "birthday": "yyyy-mm-dd",
    "email": "string",
    "phone": "string",
    "gender": "string"
}

Response:
{
    "message": "User profile updated successfully"
}

## Câp nhật mật khẩu
Method: PUT
URL: /api/user/password
Payload:
{
    "newPassword": "string"
}

Response:
{
    "message": User password updated successfully
}

## Xóa người dùng
Method: DELETE
URL: /api/user
Response:
{
    "message": "User deleted successfully"
}

# Của admin

role ở đây là các role có của tk
- admin: ROLE_USER + ROLE_ADMIN
- user: ROLE_USER

## Lấy danh sách người dùng
Method: GET
URL: /api/users
Response:
[
  {
    "id": "integer",
    "roles": 
        [
            {"id": 1, "name": "ROLE_USER" },
            {"id": 2, "name": "ROLE_ADMIN" }
        ],
    "fullName": "string",
    "birthday": "yyyy-mm-dd",
    "email": "string",
    "phone": "string",
    "gender": "string"
  }
]

## Lấy thông tin theo ID
Method: GET
URL: /api/users/{id}
Response:
{
  "id": "integer",
  "roles": 
    [
        {"id": 1, "name": "ROLE_USER" },
        {"id": 2, "name": "ROLE_ADMIN" }
    ],
  "fullName": "string",
  "birthday": "yyyy-mm-dd",
  "email": "string",
  "phone": "string",
  "gender": "string"
}

## Câp nhật mật khẩu
Method: PUT
URL: /api/users/{id}/password
Payload:
{
    "newPassword": "string"
}

Response:
{
    "message": User password updated successfully
}

## Xóa người dùng
Method: DELETE
URL: /api/users/{id}
Response:
{
    "message": "User deleted successfully"
}

## Lấy tất cả quyen
Method: GET
URL: /api/roles
Response:
[
    {"id": 1, "name": "ROLE_USER" },
    {"id": 2, "name": "ROLE_ADMIN" }
]

## Câp nhật quyền
Method: PUT
URL: /api/users/{id}/role
Response:
{
    "roles": 
    [
        {"id": 1, "name": "ROLE_USER" },
        {"id": 2, "name": "ROLE_ADMIN" }
    ],
}