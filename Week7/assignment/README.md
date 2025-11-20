# Week 7 - Schedule Management App (Quản lý Lịch hẹn Cá Nhân)

A modern Android application for managing personal schedules with notifications, built following MVVM architecture pattern.

## Features

✅ **Schedule Management**
- RecyclerView list of all schedules
- Add new schedules via dialog
- Edit existing schedules
- Delete schedules with confirmation

✅ **Time Management**
- Time Picker for "From" and "To" times
- User-friendly time selection interface
- 24-hour format (HH:mm)

✅ **Notifications**
- Automatic notifications 30 minutes before scheduled time
- Background alarm scheduling
- Notification channel management for Android 8.0+

✅ **Database**
- Room database for persistent storage
- Schedule and User entities
- Full CRUD operations

✅ **User Profile**
- User profile management
- URL links for profile/resources
- Avatar and email support

## Architecture

### MVVM (Model-View-ViewModel)

- **Model**: Room Database entities and DAOs
- **View**: Activities, Fragments, and Dialogs with XML layouts
- **ViewModel**: Manages UI state and business logic
- **Repository**: Acts as a data abstraction layer

### Project Structure

```
com.example.week7assignment/
├── data/                          # Database layer
│   ├── Schedule.kt               # Schedule entity
│   ├── User.kt                   # User entity
│   ├── ScheduleDao.kt            # Schedule database access
│   ├── UserDao.kt                # User database access
│   └── ScheduleDatabase.kt       # Database setup
├── repository/                    # Data repository layer
│   └── ScheduleRepository.kt     # Repository for data operations
├── viewmodel/                     # ViewModel layer
│   ├── ScheduleViewModel.kt      # Main view model
│   └── ScheduleViewModelFactory.kt
├── ui/
│   ├── MainActivity.kt           # Main activity
│   ├── adapter/
│   │   └── ScheduleAdapter.kt    # RecyclerView adapter
│   └── dialog/
│       ├── AddScheduleDialog.kt  # Add/Edit schedule dialog
│       └── DeleteConfirmDialog.kt # Confirmation dialog
├── receiver/
│   └── ScheduleNotificationReceiver.kt  # Notification broadcast receiver
├── util/
│   └── NotificationUtil.kt       # Notification utilities
└── res/
    ├── layout/
    │   ├── activity_main.xml
    │   ├── item_schedule.xml
    │   ├── dialog_add_schedule.xml
    │   └── dialog_delete_confirm.xml
    ├── values/
    │   ├── colors.xml
    │   ├── strings.xml
    │   └── themes.xml
    └── drawable/
        └── bg_time_picker.xml
```

## Usage

### Building the Project

```bash
./gradlew build
```

### Running the App

1. Launch the app on an emulator or device
2. Click "Add Schedule" button to create a new schedule
3. Fill in the schedule details:
   - **Full Name (Họ Tên)**: Person's name
   - **Title (Tiêu đề)**: Schedule title
   - **Content (Nội dung)**: Schedule description
   - **From Time**: Start time (via TimePicker)
   - **To Time**: End time (via TimePicker)
   - **Link (Link hữu)**: Optional URL link
4. Click "Save" to add the schedule
5. The app will automatically schedule a notification 30 minutes before the start time
6. Click on a schedule to view options:
   - **Edit**: Modify the schedule
   - **Delete**: Remove the schedule
   - **Cancel**: Close the dialog

## Requirements Met

✅ Show schedule list in RecyclerView
✅ Click on schedule to show Dialog with delete/edit options
✅ Time Picker for "From" and "To" times
✅ "Add Schedule" button shows Dialog for creating schedules
✅ User profile with URL links from database
✅ Notifications 30 minutes before start time
✅ MVVM architecture pattern
✅ Room database for persistence

## Dependencies

- **AndroidX**
  - androidx.appcompat
  - androidx.core.ktx
  - androidx.lifecycle (ViewModel, LiveData)
  - androidx.activity.ktx
  - androidx.fragment.ktx

- **Room Database**
  - androidx.room:room-runtime
  - androidx.room:room-ktx
  - androidx.room:room-compiler (kapt)

- **Material Design**
  - com.google.android.material

- **Kotlin**
  - Coroutines for async operations
  - KSP for Room compiler

## API Requirements

- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 36 (Android 15)
- **Java Version**: 11

## Permissions Required

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.SCHEDULE_EXACT_ALARM" />
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
```

## Notes

- Notifications are scheduled using AlarmManager for reliability
- UI follows Material Design 3 principles
- All database operations are performed on background threads using Coroutines
- Time format is 24-hour (HH:mm)
- The app automatically handles notification permissions for Android 13+

## Future Enhancements

- Recurring schedules
- Schedule categories/tags
- Search and filter functionality
- Export schedules to calendar
- Dark mode support
- Multi-language support

---

**Week 7 Assignment** - Schedule Management Application

