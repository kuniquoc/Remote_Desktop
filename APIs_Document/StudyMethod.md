## Create Study Method
**Method:** POST  
**URL:** `/api/study-methods`  
**Request Body (JSON):**
```json
{
    "name": "Pomodoro Technique",
    "description": "Phương pháp quản lý thời gian với các phiên làm việc 25 phút."
}
```
**Response Body (JSON):**  
```json
{
    "message": "Study method created successfully",
    "data": {
        "id": 1,
        "name": "Pomodoro Technique",
        "description": "Phương pháp quản lý thời gian với các phiên làm việc 25 phút."
    }
}
```
**Status Code:**
- **201 Created**: Tạo mới thành công.
- **500 Internal Server Error**: Lỗi server

---

## Get All Study Methods
**Method:** GET  
**URL:** `/api/study-methods`  
**Response Body (JSON):**
```json
{
    "message": "Study methods retrieved successfully",
    "data": [
        {
            "id": 1,
            "name": "Pomodoro Technique",
            "description": "Phương pháp quản lý thời gian với các phiên làm việc 25 phút."
        },
        {
            "id": 2,
            "name": "Mind Mapping",
            "description": "Phương pháp tổ chức thông tin dưới dạng sơ đồ."
        }
    ]
}
```
**Status Code:**
- **200 OK**: Yêu cầu thành công và trả về danh sách.
- **500 Internal Server Error**: Lỗi server xảy ra.

---

## Get Study Method by ID
**Method:** GET  
**URL:** `/api/study-methods/{id}`  
**Response Body (JSON):**
```json
{
    "message": "Study method retrieved successfully",
    "data": {
        "id": 1,
        "name": "Pomodoro Technique",
        "description": "Phương pháp quản lý thời gian với các phiên làm việc 25 phút."
    }
}
```
**Status Code:**
- **200 OK**: Yêu cầu thành công và trả về chi tiết phương pháp học.
- **404 Not Found**: Không tìm thấy phương pháp học với ID đã cho.
- **500 Internal Server Error**: Lỗi server xảy ra.

---

## Update Study Method
**Method:** PUT  
**URL:** `/api/study-methods/{id}`  
**Request Body (JSON):**
```json
{
    "name": "Updated Pomodoro Technique",
    "description": "Phương pháp được cập nhật với các phiên làm việc dài hơn."
}
```
**Response Body (JSON):**
```json
{
    "message": "Study method updated successfully",
    "data": {
        "id": 1,
        "name": "Updated Pomodoro Technique",
        "description": "Phương pháp được cập nhật với các phiên làm việc dài hơn."
    }
}
```
**Status Code:**
- **200 OK**: Cập nhật thành công.
- **404 Not Found**: Không tìm thấy phương pháp học với ID đã cho.
- **500 Internal Server Error**: Lỗi server xảy ra.

---

## Delete Study Method
**Method:** DELETE  
**URL:** `/api/study-methods/{id}`  
**Response Body (JSON):**
```json
{
    "message": "Study method deleted successfully",
    "data": null
}
```
**Status Code:**
- **204 No Content**: Xóa thành công, không có nội dung trả về.
- **404 Not Found**: Không tìm thấy phương pháp học với ID đã cho.
- **500 Internal Server Error**: Lỗi server xảy ra.
