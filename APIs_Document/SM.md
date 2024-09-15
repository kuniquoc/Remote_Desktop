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

# API Quản Lý Điểm Số
## Thêm điểm cho sinh viên
Method: POST
URL: /api/scores
Payload:
{
  "studentId": "integer",
  "subject": "string",
  "score": "double"
}

Response:
{
  "message": "Score added successfully"
}

## Cập nhật điểm của sinh viên
Method: PUT
URL: /api/scores/{id}
Payload:
{
  "subject": "string",
  "score": "double"
}

Response:
{
  "message": "Score updated successfully"
}

## Lấy danh sách điểm của sinh viên
Method: GET
URL: /api/scores?studentId={studentId}
Response:
[
  {
    "id": "integer",
    "subject": "string",
    "score": "double"
  }
]

## Xóa điểm của sinh viên
Method: DELETE
URL: /api/scores/{id}
Response:
{
  "message": "Score deleted successfully"
}

# API Hệ Điều Hành (File Excel)
## Đọc file Excel
Method: POST
URL: /api/excel/upload
Payload: Multipart form data

Response:
{
  "message": "File uploaded successfully",
  "students": [
    {
      "name": "string",
      "dob": "yyyy-mm-dd",
      "address": "string",
      "email": "string",
      "phone": "string",
      "gender": "string"
    }
  ]
}

## Xuất danh sách sinh viên ra file Excel
Method: GET
URL: /api/excel/download
Response: File Excel chứa danh sách sinh viên.

## Nhập danh sách sinh viên từ file Excel
Method: POST
URL: /api/excel/import
Payload: Multipart form data

Response:
{
  "message": "File imported successfully",
  "students": [
    {
      "name": "string",
      "dob": "yyyy-mm-dd",
      "address": "string",
      "email": "string",
      "phone": "string",
      "gender": "string"
    }
  ]
}

## Xuất danh sách điểm ra file Excel
Method: GET
URL: /api/excel/scores/download
Response: File Excel chứa danh sách điểm của sinh viên.

# API Quản Lý Môn Học
## Thêm môn học mới
Method: POST
URL: /api/subjects
Payload:
{
  "name": "string",
  "credits": "integer"
}

Response:
{
  "message": "Subject added successfully"
}

## Lấy danh sách môn học
Method: GET
URL: /api/subjects
Response:
[
  {
    "id": "integer",
    "name": "string",
    "credits": "integer"
  }
]

## Xóa môn học
Method: DELETE
URL: /api/subjects/{id}
Response:
{
  "message": "Subject deleted successfully"
}

# Các yêu cầu khác:
Sử dụng JWT cho bảo mật (các API /auth/** được truy cập mà không cần token, các API khác cần token để truy cập).
Sử dụng thư viện Apache POI hoặc EasyExcel để đọc và ghi file Excel.