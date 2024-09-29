## 1. Thêm lịch (Create Schedule)

**Endpoint**: `POST /api/schedules`

- **Mục đích**: Tạo một lịch mới.
  
**Request Headers**:
| Key          | Value            |
| ------------ | ---------------- |
| Content-Type | application/json |

### **Request Body**:

```json
{
  "title": "Họp team",
  "description": "Thảo luận hướng đi cho dự án",
  "location": "Phòng C202",
  "startDateTime": "2024-09-30T10:00:00",
  "endDateTime": "2024-09-30T11:00:00",
  "color": "#FF5733",
  "typeIds": [1, 2], //    Memory,Thinking,Language,Mathematics,Science,History,Arts,SoftSkills,ComputerScience,Economics,
  "studyMethodIds": [1, 3],
  "recurrence": {
    "frequency": "WEEKLY",
    "interval": 1,
    "daysOfWeek": ["MONDAY", "WEDNESDAY"],
    "endRecurrence": "2024-12-31"
  },
  "reminders": [
    {"method": 1, "minutesBefore": "30"}
  ],
  "notes": "Đừng quên mang theo slide"
}
```

**Response**:
- **Status**: `201 Created`
- **Response Body**:

```json
{
  "message": "Thêm lịch mới thành công",
  "data": {
    "id": 123
  }
}
```

**Possible Status Codes**:
- `201 Created`: Lịch đã được tạo thành công.
- `400 Bad Request`: Dữ liệu không hợp lệ hoặc thiếu trường bắt buộc.

---

## 2. Xem chi tiết lịch (Get Schedule by ID)

**Endpoint**: `GET /api/schedules/{id}`

- **Mục đích**: Xem chi tiết lịch dựa trên ID.

**Request Headers**:
| Key          | Value            |
| ------------ | ---------------- |
| Content-Type | application/json |

**Path Parameter**:
| Parameter Name | Description                  |
| -------------- | ---------------------------- |
| id             | ID của lịch cần lấy chi tiết |

**Response**:

```json
{
  "message": "Lấy thông tin lịch thành công",
  "data": {
    "id": 123,
    "title": "Meeting with team",
    "description": "Discuss the new project roadmap",
    "location": "Office Room 202",
    "startDateTime": "2024-09-30T10:00:00",
    "endDateTime": "2024-09-30T11:00:00",
    "color": "#FF5733",
    "types": [{"id": 1, "name": "Work"}],
    "studyMethods": [{"id": 1, "name": "Pomodoro"}],
    "recurrence": {
      "frequency": "WEEKLY",
      "interval": 1,
      "daysOfWeek": ["MONDAY", "WEDNESDAY"],
      "endRecurrence": "2024-12-31"
    },
    "reminders": [
      {"method": 1, "minutesBefore": "30"},

    ],
    "notes": "Don't forget to bring the slides."
  }
}
```

### **Possible Status Codes**:
- `200 OK`: Trả về chi tiết lịch thành công.
- `404 Not Found`: Lịch với ID được cung cấp không tồn tại.

---

## 3. Xem các lịch trình

**Endpoint**: `GET /api/schedules?mode=week`
 -mode(week, month, year): dạng lịch muốn xem (mặc định là month)

- **Mục đích**: Xem các lịch trình.

**Request Headers**:
| Key          | Value            |
| ------------ | ---------------- |
| Content-Type | application/json |

**Response**:

```json
{
  "message": "Lấy thông tin các lịch thành công",
  "data": [
    {
      "id": 123,
      "title": "Meeting with team",
      "location": "Office Room 202",
      "startDateTime": "2024-09-30T10:00:00",
      "endDateTime": "2024-09-30T11:00:00",
      "color": "#FF5733",
    },
    "reminders": [
      {"method": 1, "minutesBefore": "30"},

    ],
  ]
}
```

### **Possible Status Codes**:
- `200 OK`: Trả về chi tiết lịch thành công.
- `404 Not Found`: Lịch với ID được cung cấp không tồn tại.

---

## 4. Cập nhật lịch (Update Schedule)

**Endpoint**: `PUT /api/schedules/{id}`

- **Mục đích**: Cập nhật thông tin của một lịch theo ID.

**Request Headers**:
| Key          | Value            |
| ------------ | ---------------- |
| Content-Type | application/json |

**Path Parameter**:
| Parameter Name | Description              |
| -------------- | ------------------------ |
| id             | ID của lịch cần cập nhật |

**Request Body**:
- Dữ liệu cần cập nhật cho lịch.

```json
{
  "title": "Tiêu đề mới",
  "description": "Mô tả mới",
  "location": "Địa điểm mới",
  "startDateTime": "2024-09-30T10:00:00",
  "endDateTime": "2024-09-30T11:00:00",
  "color": "#FF5733",
  "typeIds": [1, 2], //    Memory,Thinking,Language,Mathematics,Science,History,Arts,SoftSkills,ComputerScience,Economics,
  "studyMethodIds": [1, 3],
  "recurrence": {
    "frequency": "WEEKLY",
    "interval": 1,
    "daysOfWeek": ["MONDAY", "WEDNESDAY"],
    "endRecurrence": "2024-12-31"
  },
  "reminders": [
    {"method": 1, "minutesBefore": "30"}
  ],
  "notes": "Ghi chú mới"
}
```

**Response**:

```json
{
  "message": "Cập nhật lịch thành công"
}
```

**Possible Status Codes**:
- `200 OK`: Lịch đã được cập nhật thành công.
- `404 Not Found`: Lịch với ID được cung cấp không tồn tại.
- `400 Bad Request`: Dữ liệu không hợp lệ hoặc thiếu trường bắt buộc.

---


## 5. Xoá lịch (Delete Schedule)

**Endpoint**: `DELETE /api/schedules/{id}`

- **Mục đích**: Xoá một lịch dựa trên ID.
**Request Headers**:
| Key          | Value            |
| ------------ | ---------------- |
| Content-Type | application/json |

**Path Parameter**:
| Parameter Name | Description         |
| -------------- | ------------------- |
| id             | ID của lịch cần xoá |

**Response**:
- **Status**: `204 No Content`
  
### **Possible Status Codes**:
- `204 No Content`: Lịch đã được xoá thành công.
- `404 Not Found`: Lịch với ID được cung cấp không tồn tại.

---