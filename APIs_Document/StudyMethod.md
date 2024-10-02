
## 1.Create Study Method
**Method:** POST  
**URL:** `/api/study-methods`  
**Request Body (JSON):**
```json
{
    "name": "Pomodoro Technique",
    "description": "Phương pháp quản lý thời gian với các phiên làm việc 25 phút.",
    "thumbnail": "https://example.com/thumbnails/pomodoro.png",
    "typeId": 9,
    "detail": "<h1>Pomodoro Technique</h1><p>Phương pháp này giúp cải thiện hiệu suất qua các phiên làm việc 25 phút.</p>"
}
```
**Response Body (JSON):**  
```json
{
    "message": "Phương pháp học được tạo thành công",
    "data": {
        "id": 1,
        "name": "Pomodoro Technique",
        "description": "Phương pháp quản lý thời gian với các phiên làm việc 25 phút.",
        "thumbnail": "https://example.com/thumbnails/pomodoro.png",
        "type": {
            "id": 8,
            "name": "SoftSkills"
        },
        "detail": "<h1>Pomodoro Technique</h1><p>Phương pháp này giúp cải thiện hiệu suất qua các phiên làm việc 25 phút.</p>"
    }
}
```
**Status Code:**
- **201 Created**: Tạo mới thành công.
- **500 Internal Server Error**: Lỗi server xảy ra.

---

## 2.Get All Study Methods
**Method:** GET  
**URL:** `/api/study-methods?page=0?size=4?type=SoftSkills`  
**Response Body (JSON):**
```json
{
    "message": "Lấy danh sách phương pháp học thành công",
    "data": [
        {
            "id": 1,
            "name": "Pomodoro Technique",
            "description": "Phương pháp quản lý thời gian với các phiên làm việc 25 phút.",
            "thumbnail": "https://example.com/thumbnails/pomodoro.png",
            "type": {
                "id": 8,
                "name": "SoftSkills"
            },
        },
        {
            "id": 2,
            "name": "Mind Mapping",
            "description": "Phương pháp tổ chức thông tin dưới dạng sơ đồ.",
            "thumbnail": "https://example.com/thumbnails/mind_mapping.png",
            "type": {
                "id": 2,
                "name": "Thinking"
            },
        }
    ]
}
```
**Status Code:**
- **200 OK**: Yêu cầu thành công và trả về danh sách.
- **500 Internal Server Error**: Lỗi server xảy ra.

---

## 3.Get Study Method by ID
**Method:** GET  
**URL:** `/api/study-methods/{id}`  
**Response Body (JSON):**
```json
{
    "message": "Lấy thông tin phương pháp học thành công",
    "data": {
        "id": 1,
        "name": "Pomodoro Technique",
        "description": "Phương pháp quản lý thời gian với các phiên làm việc 25 phút.",
        "thumbnail": "https://example.com/thumbnails/pomodoro.png",
        "type": {
            "id": 8,
            "name": "SoftSkills"
        },
        "detail": "<h1>Pomodoro Technique</h1><p>Phương pháp này giúp cải thiện hiệu suất qua các phiên làm việc 25 phút.</p>"
    }
}
```
**Status Code:**
- **200 OK**: Yêu cầu thành công và trả về chi tiết phương pháp học.
- **400 Bad Request**: Không tìm thấy phương pháp học với ID đã cho.
- **500 Internal Server Error**: Lỗi server xảy ra.

---

## 4.Update Study Method
**Method:** PUT  
**URL:** `/api/study-methods/{id}`  
**Request Body (JSON):**
```json
{
    "name": "Updated Pomodoro Technique",
    "description": "Phương pháp được cập nhật với các phiên làm việc dài hơn.",
    "thumbnail": "https://example.com/thumbnails/updated_pomodoro.png",
    "subjectType": "SoftSkills",
    "detail": "<h1>Updated Pomodoro Technique</h1><p>Phương pháp được cập nhật với các phiên làm việc 30 phút.</p>"
}
```
**Response Body (JSON):**
```json
{
    "message": "Cập nhật phương pháp học thành công",
    "data": {
        "id": 1,
        "name": "Updated Pomodoro Technique",
        "description": "Phương pháp được cập nhật với các phiên làm việc dài hơn.",
        "thumbnail": "https://example.com/thumbnails/updated_pomodoro.png",
        "type": {
            "id": 8,
            "name": "SoftSkills"
        },
        "detail": "<h1>Updated Pomodoro Technique</h1><p>Phương pháp được cập nhật với các phiên làm việc 30 phút.</p>"
    }
}
```
**Status Code:**
- **200 OK**: Cập nhật thành công.
- **404 Not Found**: Không tìm thấy phương pháp học với ID đã cho.
- **500 Internal Server Error**: Lỗi server xảy ra.

---

## 5.Delete Study Method
**Method:** DELETE  
**URL:** `/api/study-methods/{id}`  
**Response Body (JSON):**
```json
No content
```
**Status Code:**
- **204 No Content**: Xóa thành công, không có nội dung trả về.
- **500 Internal Server Error**: Lỗi server xảy ra.

---
