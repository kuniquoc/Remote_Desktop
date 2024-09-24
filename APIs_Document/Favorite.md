# API Favorite Management

## Add Study Method to Favorites
**Method:** POST  
**URL:** /api/favorites?userId={userId}&studyMethodId={studyMethodId}  
**Response Body (JSON):**
```json
{
    "message": "Favorite added successfully"
}
```
**Status Code:**  
- **201 OK**: Thêm thành công.  
- **404 Not Found**: Không tìm thấy người dùng hoặc phương pháp học với ID đã cho.  
- **500 Internal Server Error**: Lỗi server xảy ra.

---

## Remove Study Method from Favorites
**Method:** DELETE  
**URL:** /api/favorites?userId={userId}&studyMethodId={studyMethodId}  
**Response Body:**  
```json
// No Content
```
**Status Code:**  
- **204 No Content**: Xóa thành công, không có nội dung trả về.  
- **404 Not Found**: Không tìm thấy người dùng hoặc phương pháp học với ID đã cho trong danh sách yêu thích.  
- **500 Internal Server Error**: Lỗi server xảy ra.

---

## Get User's Favorite Study Methods
**Method:** GET  
**URL:** /api/favorites/{userId}  
**Response Body (JSON):**
```json
{   
    "message": "User's favorite study methods retrieved successfully",
    "data": [
                {
                    "id": 2,
                    "name": "Mind Mapping",
                    "description": "Phương pháp tổ chức thông tin dưới dạng sơ đồ."
                },
                {
                    "id": 1,
                    "name": "Pomodoro Technique",
                    "description": "Phương pháp quản lý thời gian với các phiên làm việc 25 phút."
                }
            ]
}

```
**Status Code:**  
- **200 OK**: Yêu cầu thành công và trả về danh sách phương pháp học yêu thích.  
- **500 Internal Server Error**: Lỗi server xảy ra.