# Bai4 - App Flow and Navigation Guide

## Application Flow Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                      APP START                                   │
│                   MainActivity (A)                               │
│                  Load Sample Tasks                               │
└─────────────────────────────────────────────────────────────────┘
                              │
                              │ Display tasks in RecyclerView
                              ↓
┌─────────────────────────────────────────────────────────────────┐
│         MAIN SCREEN - Task List (RecyclerView)                  │
│                                                                  │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │ Quản lý thư mục                                         │   │
│  │ Tổng hợp tin tức thời sự                              │   │
│  └─────────────────────────────────────────────────────────┘   │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │ Do It Your Self                                         │   │
│  │ Sáo lóng kĩ gặp dễ cái hay                            │   │
│  └─────────────────────────────────────────────────────────┘   │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │ Cẩm hùng sáng tạo                                       │   │
│  │ Tổng hợp tin tức thời sự                              │   │
│  └─────────────────────────────────────────────────────────┘   │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │ Tổng hợp tin tức thời sự                              │   │
│  │ Tổng hợp tin tức thời sự                              │   │
│  └─────────────────────────────────────────────────────────┘   │
│                                                                  │
│         [Thêm Mục Mới]  OR  [Click on any task]                │
└─────────────────────────────────────────────────────────────────┘
              ↙                               ↖
             /                                 \
    (Add button clicked)              (Item clicked)
           /                                     \
          ↓                                       ↓
┌──────────────────────┐             ┌──────────────────────┐
│  AddActivity (C)     │             │ EditActivity (B)     │
│                      │             │                      │
│ Title Input:         │             │ Title:               │
│ [____________]       │             │ [____________]       │
│                      │             │                      │
│ Description Input:   │             │ Description:         │
│ [____________]       │             │ [____________]       │
│ [____________]       │             │ [____________]       │
│ [____________]       │             │                      │
│                      │             │ [Lưu]  [Hủy]       │
│ [Thêm]  [Hủy]      │             │                      │
└──────────────────────┘             └──────────────────────┘
    ↓              ↓                     ↓              ↓
  [Add]        [Cancel]             [Save]         [Cancel]
    │              │                    │              │
    │ Creates new  │ Discards           │ Updates      │ Discards
    │ TaskModel    │ data               │ TaskModel    │ changes
    │              │                    │              │
    └─────┬────────┘                    └─────┬────────┘
          │                                    │
          │ Returns to MainActivity            │
          │ setResult(RESULT_OK)               │
          │                                    │
          └────────┬─────────────────────────┘
                   │
                   ↓
        ┌──────────────────────┐
        │   MainActivity (A)    │
        │   Receives Result     │
        │                       │
        │ Update/Add task to    │
        │ adapter               │
        │                       │
        │ notifyDataSetChanged()│
        └──────────────────────┘
                   │
                   ↓
        ┌──────────────────────┐
        │   RecyclerView       │
        │   Updates Display     │
        │                       │
        │   List refreshes      │
        │   with new data       │
        └──────────────────────┘
```

## State Transitions

### MainActivity Lifecycle
```
onCreate()
    ↓
Initialize RecyclerView
    ↓
Load sample data (4 tasks)
    ↓
Setup TaskAdapter with click listener
    ↓
Display task list
    ↓
Wait for user interaction
    ↓
onPause() / onStop() / onDestroy()
```

### Edit Task Sequence
```
User clicks task item
    ↓
adapter callback triggered
    ↓
Create Intent with task data
    ↓
editLauncher.launch(intent)
    ↓
EditActivity.onCreate() - display task
    ↓
User edits title/description
    ↓
User clicks Save
    ↓
setResult(RESULT_OK, resultIntent)
    ↓
finish()
    ↓
MainActivity receives result
    ↓
adapter.updateTask(position, updatedTask)
    ↓
notifyItemChanged() in adapter
    ↓
RecyclerView refreshes at position
    ↓
User sees updated task
```

### Add Task Sequence
```
User clicks "Thêm Mục Mới" button
    ↓
Create Intent with nextId
    ↓
addLauncher.launch(intent)
    ↓
AddActivity.onCreate() - display form
    ↓
User enters title/description
    ↓
User clicks Thêm
    ↓
Create new TaskModel
    ↓
setResult(RESULT_OK, resultIntent)
    ↓
finish()
    ↓
MainActivity receives result
    ↓
adapter.addTask(newTask)
    ↓
notifyItemInserted() in adapter
    ↓
RecyclerView adds new item
    ↓
nextId incremented
    ↓
User sees new task in list
```

## Data Flow Diagram

### Edit Operation Data Flow
```
MainActivity                    EditActivity
─────────────                   ────────────

Task object ──────Intent──────→ Receives Task
Position int ──────Bundle──────→ Receives Position
                                │
                                ↓ Display in EditTexts
                                │
                   User edits task data
                                │
                                ↓ Click Save
                                │
           ←─────Intent────── Updated Task
           ←────Bundle────── Position info
                                │
Results processor               │
receives result                 │
                                │
adapter.updateTask()
notifyItemChanged()
                ↓
        RecyclerView updates
```

### Add Operation Data Flow
```
MainActivity                    AddActivity
─────────────                   ──────────

nextId int ───────Intent──────→ Receives nextId
                                │
                   User fills form
                                │
                                ↓ Click Thêm
                                │
         ←─────Intent────── New TaskModel
         ←────Bundle────────
                                │
Results processor               │
receives result                 │
                                │
adapter.addTask()
notifyItemInserted()
nextId++
                ↓
        RecyclerView adds item
```

## UI Screens

### Screen 1: MainActivity
```
┌─────────────────────────────────┐
│ Quản lý thư mục                 │
├─────────────────────────────────┤
│                                 │
│  ┌─────────────────────────┐   │
│  │ Quản lý thư mục         │   │
│  │ Tổng hợp tin tức...     │   │
│  └─────────────────────────┘   │
│                                 │
│  ┌─────────────────────────┐   │
│  │ Do It Your Self         │   │
│  │ Sáo lóng kĩ gặp dễ...  │   │
│  └─────────────────────────┘   │
│                                 │
│  ┌─────────────────────────┐   │
│  │ Cẩm hùng sáng tạo       │   │
│  │ Tổng hợp tin tức...     │   │
│  └─────────────────────────┘   │
│                                 │
│  ┌─────────────────────────┐   │
│  │ Tổng hợp tin tức...     │   │
│  │ Tổng hợp tin tức...     │   │
│  └─────────────────────────┘   │
│                                 │
├─────────────────────────────────┤
│   [Thêm Mục Mới]                │
└─────────────────────────────────┘
```

### Screen 2: EditActivity
```
┌─────────────────────────────────┐
│ Thêm thử mục                    │
├─────────────────────────────────┤
│ Tên tên tên                     │
│ ┌──────────────────────────────┐│
│ │ Quản lý thư mục             ││
│ └──────────────────────────────┘│
│                                 │
│ Nhập mô tả thử mục              │
│ ┌──────────────────────────────┐│
│ │ Tổng hợp tin tức thời sự    ││
│ │                              ││
│ │                              ││
│ └──────────────────────────────┘│
│                                 │
├─────────────────────────────────┤
│  [Lưu]           [Hủy]          │
└─────────────────────────────────┘
```

### Screen 3: AddActivity
```
┌─────────────────────────────────┐
│ Chính sửa                       │
├─────────────────────────────────┤
│ Tên thư mục                     │
│ ┌──────────────────────────────┐│
│ │                              ││
│ └──────────────────────────────┘│
│                                 │
│ Tổng hợp tin tức thời sự nội    │
│ ┌──────────────────────────────┐│
│ │                              ││
│ │                              ││
│ │                              ││
│ └──────────────────────────────┘│
│                                 │
├─────────────────────────────────┤
│   [Thêm]        [Hủy]           │
└─────────────────────────────────┘
```

## Technical Flow

### Intent Data Passing (Edit)
```
Intent intent = new Intent(this, EditActivity.class);
intent.putExtra("task", taskModel);        ← Parcelable
intent.putExtra("position", position);     ← Int
editLauncher.launch(intent);
```

### Result Handling
```
ActivityResultContracts.StartActivityForResult()
    ↓
OnActivityResult callback
    ↓
Check RESULT_OK
    ↓
Get extras from Intent
    ↓
Update adapter
    ↓
Notify RecyclerView
```

### Adapter Updates
```
addTask(task)
    ├─ tasks.add(task)
    └─ notifyItemInserted(position)

updateTask(position, task)
    ├─ tasks[position] = task
    └─ notifyItemChanged(position)

updateTasks(newTasks)
    ├─ tasks.clear()
    ├─ tasks.addAll(newTasks)
    └─ notifyDataSetChanged()
```

## User Interaction Paths

### Path 1: Add New Task
1. User launches app
2. Sees 4 sample tasks
3. Clicks "Thêm Mục Mới"
4. Enters title and description
5. Clicks "Thêm"
6. Returns to list
7. New task appears at end

### Path 2: Edit Existing Task
1. User sees task list
2. Clicks on any task card
3. Sees EditActivity with task data
4. Modifies title/description
5. Clicks "Lưu"
6. Returns to list
7. Updated task shows at same position

### Path 3: Cancel Operation
1. User starts add/edit operation
2. Changes data
3. Clicks "Hủy"
4. Returns to list
5. Changes discarded
6. Original data unchanged

## Error Handling

### Null Safety
```
result.data?.getParcelableExtra<TaskModel>("task")
    ↓
Returns null if data is null
    ↓
Safe from NullPointerException
```

### Data Validation
```
EditActivity: Received task not null?
AddActivity: Title not empty?
MainActivity: Position >= 0?
```

## Performance Considerations

1. **RecyclerView Efficiency**
   - Only renders visible items
   - Reuses ViewHolders
   - Smooth scrolling with LinearLayoutManager

2. **Data Passing**
   - Uses Parcelable (not Serializable)
   - Efficient memory transfer
   - No extra copies in memory

3. **Adapter Updates**
   - Specific notifyItemChanged() for edits
   - Specific notifyItemInserted() for adds
   - Minimal redraw of unchanged items

