# Bai4 - Task Management App

A simple Android task management application demonstrating Activity lifecycle, Intent/Bundle data passing, RecyclerView, and navigation between multiple activities.

## Project Structure

### Activities

1. **MainActivity (Activity A)** - Main list view
   - Displays all tasks in a RecyclerView
   - Shows sample tasks on startup
   - "Thêm Mục Mới" (Add New Item) button to create new tasks
   - Click on any item to edit it

2. **EditActivity (Activity B)** - Edit existing task
   - Receives task data from MainActivity via Intent
   - Displays task title and description in EditTexts
   - "Lưu" (Save) button to update and return to MainActivity
   - "Hủy" (Cancel) button to discard changes
   - Returns updated task data back to MainActivity

3. **AddActivity (Activity C)** - Add new task
   - Create new tasks with title and description
   - "Thêm" (Add) button to add the new task
   - "Hủy" (Cancel) button to discard
   - Returns new task data back to MainActivity

### Data Classes

**TaskModel** - Parcelable data class
```kotlin
data class TaskModel(
    val id: Int,
    val title: String,
    val description: String
) : Parcelable
```

### Adapter

**TaskAdapter** - RecyclerView adapter for displaying tasks
- Extends `RecyclerView.Adapter<TaskViewHolder>`
- Implements click listeners for editing items
- Methods: `updateTasks()`, `addTask()`, `updateTask()`

### Layouts

- **activity_main.xml** - Main activity with RecyclerView and Add button
- **item_task.xml** - CardView item layout for each task
- **activity_edit.xml** - Edit task form
- **activity_add.xml** - Add new task form

## Features

✅ **Three Activities** with proper navigation
✅ **RecyclerView** for displaying task list
✅ **Data Transfer** via Intent/Bundle
✅ **CRUD Operations** - Create, Read, Update tasks
✅ **Activity Result** handling for bidirectional data passing
✅ **View Binding** for type-safe view references
✅ **CardView** for attractive item display

## Sample Data

The app comes with 4 pre-loaded tasks:
1. Quản lý thư mục - Tổng hợp tin tức thời sự
2. Do It Your Self - Sáo lóng kĩ gặp dễ cái hay
3. Cẩm hùng sáng tạo - Tổng hợp tin tức thời sự
4. Tổng hợp tin tức thời sự - Tổng hợp tin tức thời sự

## Usage

1. **View Tasks** - Launch the app to see the task list
2. **Edit Task** - Tap on any task to edit its details
3. **Add Task** - Click "Thêm Mục Mới" button to add a new task
4. **Update List** - Changes are reflected immediately in the RecyclerView

## Technical Details

- **Language**: Kotlin
- **Min SDK**: 29
- **Target SDK**: 36
- **Build System**: Gradle 8.0+
- **View Binding**: Enabled
- **Key Libraries**:
  - AndroidX AppCompat
  - Material Design 3
  - RecyclerView
  - CardView
  - ConstraintLayout

## Build & Run

```bash
./gradlew build
./gradlew installDebug
```

Or run directly from Android Studio.

## Implementation Notes

- **Activity Lifecycle**: Each activity properly implements onCreate() with proper view initialization
- **Data Parcelable**: TaskModel implements Parcelable for efficient Intent data passing
- **ActivityResult API**: Uses modern ActivityResultContracts for safe activity result handling
- **RecyclerView Adapter**: Implements update mechanisms for seamless list updates
- **View Binding**: All activities use ViewBinding for type-safe view access

