# Bai4 Quick Reference Guide

## Project Overview
- **Name**: Bai4 - Task Management App
- **Type**: Android Application
- **Language**: Kotlin
- **Min SDK**: 29 | **Target SDK**: 36
- **Build System**: Gradle

## Core Activities

| Activity | Purpose | Key Features |
|----------|---------|--------------|
| **MainActivity** | View task list | RecyclerView, Add button, Sample data |
| **EditActivity** | Edit task details | EditTexts, Save/Cancel buttons |
| **AddActivity** | Create new task | Form inputs, Task creation |

## Key Classes

### TaskModel.kt
- Data class implementing Parcelable
- Properties: id, title, description
- Used for Intent data passing

### TaskAdapter.kt
- Custom RecyclerView adapter
- Manages task display and clicks
- Methods: updateTasks(), addTask(), updateTask()

### MainActivity.kt
- Displays task list via RecyclerView
- Handles edit and add operations
- Uses ActivityResult API for callbacks

### EditActivity.kt
- Allows editing existing task
- Returns updated task to MainActivity
- Receives task via Intent extras

### AddActivity.kt
- Creates new tasks
- Returns new TaskModel to MainActivity
- Receives nextId for task numbering

## Layouts

| File | Purpose |
|------|---------|
| activity_main.xml | Main list view with RecyclerView and Add button |
| item_task.xml | CardView for individual task display |
| activity_edit.xml | Edit form with EditTexts and buttons |
| activity_add.xml | Add form with EditTexts and buttons |

## Data Flow Diagrams

### Edit Task Flow
```
MainActivity
    ↓
    └─→ intent.putExtra("task", TaskModel)
        └─→ intent.putExtra("position", Int)
            ↓
            EditActivity (displays form)
            ↓
            User edits and saves
            ↓
            setResult(RESULT_OK, intent)
            └─→ intent.putExtra("task", updatedTask)
                └─→ intent.putExtra("position", Int)
                    ↓
                    MainActivity receives result
                    ↓
                    adapter.updateTask(position, task)
```

### Add Task Flow
```
MainActivity
    ↓
    └─→ intent.putExtra("nextId", Int)
        ↓
        AddActivity (displays form)
        ↓
        User enters data and adds
        ↓
        setResult(RESULT_OK, intent)
        └─→ intent.putExtra("task", newTask)
            ↓
            MainActivity receives result
            ↓
            adapter.addTask(newTask)
```

## Sample Data

```kotlin
tasks.add(TaskModel(1, "Quản lý thư mục", "Tổng hợp tin tức thời sự"))
tasks.add(TaskModel(2, "Do It Your Self", "Sáo lóng kĩ gặp dễ cái hay"))
tasks.add(TaskModel(3, "Cẩm hùng sáng tạo", "Tổng hợp tin tức thời sự"))
tasks.add(TaskModel(4, "Tổng hợp tin tức thời sự", "Tổng hợp tin tức thời sự"))
```

## Important Methods

### MainActivity
- `onCreate()` - Initialize RecyclerView and sample data
- `editLauncher.launch()` - Launch EditActivity
- `addLauncher.launch()` - Launch AddActivity
- Callbacks handle result updates

### TaskAdapter
- `onCreateViewHolder()` - Create item views
- `onBindViewHolder()` - Bind data to views
- `getItemCount()` - Return list size
- `updateTask()` - Update existing item
- `addTask()` - Add new item to list

### EditActivity / AddActivity
- `onCreate()` - Initialize form with data
- `setResult()` - Return data to caller
- `finish()` - Close activity

## Gradle Dependencies

```gradle
dependencies {
    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    
    // UI
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.cardview)
    implementation("androidx.recyclerview:recyclerview:1.3.2")
}
```

## Build & Run Commands

```bash
# Build project
./gradlew build

# Install debug APK
./gradlew installDebug

# Run debug tests
./gradlew testDebug

# Clean build
./gradlew clean build
```

## UI Strings (Vietnamese)

| Text | Meaning |
|------|---------|
| Quản lý thư mục | Manage folders |
| Thêm Mục Mới | Add new item |
| Thêm thử mục | Add folder |
| Nhập tên tên tên | Enter name |
| Nhập mô tả thử mục | Enter description |
| Lưu | Save |
| Hủy | Cancel |
| Thêm | Add |
| Chính sửa | Edit |

## Tips & Tricks

1. **View Binding**: Use `binding.viewId` instead of `findViewById()`
2. **Parcelable**: Ensures efficient Intent data passing
3. **ActivityResult**: Modern safe way to handle activity results
4. **RecyclerView Efficiency**: Only creates views for visible items
5. **CardView**: Provides elevation and shadows for material design

## Common Issues & Solutions

| Issue | Solution |
|-------|----------|
| RecyclerView not showing | Check layoutManager is set |
| Data not updating | Verify adapter methods are called |
| Intent extras not received | Check putExtra keys match getExtra keys |
| Views not binding | Ensure viewBinding is enabled in gradle |
| Null pointer exceptions | Check object initialization in onCreate |

## Testing Checklist

- [ ] App launches showing 4 tasks
- [ ] Clicking task opens EditActivity
- [ ] Editing and saving updates list
- [ ] Clicking Add opens AddActivity
- [ ] Adding task shows in list
- [ ] Cancel buttons work correctly
- [ ] RecyclerView scrolls smoothly
- [ ] No crashes on rotation

## Performance Metrics

- **Memory**: ~50-100MB (typical)
- **Launch time**: <2 seconds
- **List scroll**: 60 FPS smooth
- **APK size**: ~3-5MB

