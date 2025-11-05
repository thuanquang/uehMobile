# Bai4 Project Structure

## Complete File Organization

```
Week4/Assignment/Bai4/
│
├── gradle/                          # Gradle wrapper configuration
│   ├── libs.versions.toml          # Version catalog
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
│
├── app/                             # Application module
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/bai4/
│   │   │   │   ├── MainActivity.kt       # Activity A - Task list view
│   │   │   │   ├── EditActivity.kt       # Activity B - Edit task
│   │   │   │   ├── AddActivity.kt        # Activity C - Add new task
│   │   │   │   ├── TaskModel.kt          # Data class (Parcelable)
│   │   │   │   └── TaskAdapter.kt        # RecyclerView adapter
│   │   │   │
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml     # Main list layout
│   │   │   │   │   ├── activity_edit.xml     # Edit task layout
│   │   │   │   │   ├── activity_add.xml      # Add task layout
│   │   │   │   │   └── item_task.xml         # CardView item layout
│   │   │   │   │
│   │   │   │   ├── drawable/               # Drawable resources
│   │   │   │   │   ├── ic_launcher_background.xml
│   │   │   │   │   ├── ic_launcher_foreground.xml
│   │   │   │   │   └── [other drawables]
│   │   │   │   │
│   │   │   │   ├── mipmap-*/               # App icons (hdpi, mdpi, xhdpi, etc)
│   │   │   │   │   ├── ic_launcher.webp
│   │   │   │   │   └── ic_launcher_round.webp
│   │   │   │   │
│   │   │   │   ├── values/
│   │   │   │   │   ├── strings.xml         # String resources
│   │   │   │   │   ├── themes.xml          # Light theme
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   ├── dimens.xml
│   │   │   │   │   └── attrs.xml
│   │   │   │   │
│   │   │   │   ├── values-night/
│   │   │   │   │   └── themes.xml          # Dark theme
│   │   │   │   │
│   │   │   │   └── xml/
│   │   │   │       ├── data_extraction_rules.xml
│   │   │   │       └── backup_rules.xml
│   │   │   │
│   │   │   └── AndroidManifest.xml         # App manifest
│   │   │
│   │   ├── androidTest/java/com/example/bai4/
│   │   │   └── ExampleInstrumentedTest.kt  # Instrumented tests
│   │   │
│   │   └── test/java/com/example/bai4/
│   │       └── ExampleUnitTest.kt          # Unit tests
│   │
│   ├── build/                       # Build output (generated)
│   │   ├── generated/
│   │   ├── intermediates/
│   │   ├── kotlin/
│   │   └── outputs/
│   │       └── apk/
│   │           └── debug/
│   │               └── app-debug.apk
│   │
│   ├── build.gradle.kts             # App module build configuration
│   └── proguard-rules.pro           # ProGuard rules
│
├── build.gradle.kts                 # Root project build configuration
├── settings.gradle.kts              # Gradle settings (module definitions)
├── gradle.properties                # Gradle properties
├── gradlew                          # Gradle wrapper script (Linux/Mac)
├── gradlew.bat                      # Gradle wrapper script (Windows)
├── local.properties                 # Local environment properties
│
├── README.md                        # Project overview and features
├── IMPLEMENTATION_SUMMARY.md        # Detailed implementation notes
├── QUICK_REFERENCE.md              # Quick reference guide
└── PROJECT_STRUCTURE.md            # This file
```

## Key Files Description

### Activities (src/main/java/com/example/bai4/)

**MainActivity.kt** (Activity A)
- Main entry point
- Displays task list via RecyclerView
- Initializes 4 sample tasks
- Handles "Add New Item" button click
- Manages edit and add activity results

**EditActivity.kt** (Activity B)
- Receives task data via Intent
- Displays task title and description in EditTexts
- Allows user to modify task details
- Returns updated task to MainActivity
- Supports Save/Cancel operations

**AddActivity.kt** (Activity C)
- Displays form to create new task
- Receives nextId from MainActivity
- Creates new TaskModel with user input
- Returns new task to MainActivity
- Supports Add/Cancel operations

**TaskModel.kt**
- Data class for tasks
- Properties: id, title, description
- Implements Parcelable for Intent passing
- Auto-generated CREATOR for deserialization

**TaskAdapter.kt**
- Custom RecyclerView adapter
- Manages task list display
- Handles item click events
- Methods: updateTasks(), addTask(), updateTask()
- Uses ViewHolder pattern

### Layouts (src/main/res/layout/)

**activity_main.xml**
- Root: LinearLayout
- Contains: RecyclerView for task list
- Contains: Button for adding new task
- Orientation: Vertical

**activity_edit.xml**
- Root: LinearLayout
- Contains: EditText for title
- Contains: EditText for description (multiline)
- Contains: Save and Cancel buttons
- Used by EditActivity

**activity_add.xml**
- Root: LinearLayout
- Contains: EditText for title
- Contains: EditText for description (multiline)
- Contains: Add and Cancel buttons
- Used by AddActivity

**item_task.xml**
- Root: CardView (Material Design)
- Contains: LinearLayout with title and description
- Elevation: 4dp, Radius: 8dp
- Used by TaskAdapter for each list item

### Manifest (AndroidManifest.xml)
- Declares MainActivity as launcher activity
- Declares EditActivity (no launcher)
- Declares AddActivity (no launcher)
- Targets API level 36, min API 29
- Defines app icon and label

### Gradle Configuration

**build.gradle.kts** (Root)
- Project-wide build configuration
- Gradle plugins and versions

**app/build.gradle.kts**
- App module configuration
- Namespace: com.example.bai4
- Dependencies: androidx, material, recyclerview, cardview
- View Binding: enabled
- Target SDK: 36, Min SDK: 29

**settings.gradle.kts**
- Project name: "Bai4"
- Includes: :app module
- Repository configuration

## Dependencies Overview

```
androidx.core:core-ktx              # Kotlin extensions
androidx.appcompat:appcompat         # AppCompat base
com.google.android.material:material # Material Design 3
androidx.activity:activity           # Activity base class
androidx.constraintlayout            # Layout engine
androidx.cardview:cardview           # CardView component
androidx.recyclerview:recyclerview   # RecyclerView component
junit:junit                          # Unit testing
androidx.test:runner                 # Instrumented testing
androidx.test.espresso:espresso-core # UI testing
```

## Build Process

1. **Compilation**
   - Kotlin → Java bytecode
   - Resource files → binary resources
   - View binding generates binding classes

2. **Intermediate Steps**
   - Merging manifests
   - Processing resources
   - Generating R.java
   - DEX conversion

3. **Packaging**
   - Creates APK
   - Signs with debug key (debug build)
   - Output: app-debug.apk

4. **Output**
   - build/outputs/apk/debug/app-debug.apk
   - ~3-5 MB APK size

## Resource Organization

### Strings (values/strings.xml)
- App name: "Bai4"
- Can add more strings as needed

### Themes (values/themes.xml)
- Base theme: Theme.Material3.DayNight.NoActionBar
- Light and dark variants

### Drawables
- Launcher icons in mipmap-*/ directories
- Background and foreground vectors

## Important Notes

1. **Package Name**: com.example.bai4
   - Used throughout manifests and code
   - Cannot be changed without refactoring

2. **API Levels**
   - Min: 29 (Android 10)
   - Target: 36 (Android 15)
   - Compile: 36

3. **View Binding**
   - Enabled in build.gradle.kts
   - Generates ActivityMainBinding, etc.
   - Type-safe view access

4. **Parcelable**
   - TaskModel implements Parcelable
   - Efficient for Intent data passing
   - Auto-generated CREATOR

5. **RecyclerView**
   - LinearLayoutManager for vertical list
   - TaskAdapter for item management
   - CardView items for visual appeal

## File Sizes (Approximate)

| Component | Size |
|-----------|------|
| source code | ~8 KB |
| layouts | ~3 KB |
| resources | ~200 KB |
| APK (debug) | ~3-5 MB |
| APK (release) | ~2-3 MB |

## Version Information

- Gradle: 8.0+
- Kotlin: Latest (from version catalog)
- Target API: 36 (Android 15)
- Min API: 29 (Android 10)

