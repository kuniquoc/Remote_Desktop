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

# API Quản Lý Sinh Viên
## Thêm sinh viên mới
Method: POST
URL: /api/students
Payload:
{
  "name": "string",
  "dob": "yyyy-mm-dd",
  "address": "string",
  "email": "string",
  "phone": "string",
  "gender": "string"
}

Response:
{
  "message": "Student added successfully"
}

## Cập nhật thông tin sinh viên
Method: PUT
URL: /api/students/{id}
Payload:
{
  "name": "string",
  "dob": "yyyy-mm-dd",
  "address": "string",
  "email": "string",
  "phone": "string",
  "gender": "string"
}

Response:
{
  "message": "Student updated successfully"
}

## Xóa sinh viên
Method: DELETE
URL: /api/students/{id}
Response:
{
  "message": "Student deleted successfully"
}

## Lấy danh sách sinh viên
Method: GET
URL: /api/students
Response:
[
  {
    "id": "integer",
    "name": "string",
    "dob": "yyyy-mm-dd",
    "address": "string",
    "email": "string",
    "phone": "string",
    "gender": "string"
  }
]

## Lấy thông tin sinh viên theo ID
Method: GET
URL: /api/students/{id}
Response:
{
  "id": "integer",
  "name": "string",
  "dob": "yyyy-mm-dd",
  "address": "string",
  "email": "string",
  "phone": "string",
  "gender": "string"
}
