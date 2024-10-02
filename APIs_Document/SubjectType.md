## 1. Lấy Danh Sách Loại Môn Học
**Phương thức:** GET  
**URL:** `/api/subject-types`  

**Response Body (JSON):**
```json
{
    "message": "Lấy danh sách loại môn học thành công",
    "data": [
        {
            "id": 1,
            "name": "Loại 1"
        },
        {
            "id": 2,
            "name": "Loại 2"
        }
    ]
}
```

**Status Code:**
- **200 OK**: Lấy danh sách thành công.
- **500 Internal Server Error**: Lỗi khi lấy danh sách loại môn học.

--- 
