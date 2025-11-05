# Week 5: Modern Android UI with Fragments and ViewPager2

This directory contains five modernized Android applications that transition from Week4's legacy architecture (GridView, ListView, Activity-based) to modern Android patterns using Fragments, ViewPager2, TabLayout, and Custom Spinners.

## Project Overview

### 1. **Bai2 - Form Validation with Fragments**
**Status**: ✅ In Progress

Modern fragment-based form validation with custom spinner for field selection.

**Architecture**:
- `FormFragment`: Main form with EditText fields (username, email, phone, ID card, date)
- `FieldSpinnerAdapter`: Custom spinner showing available fields
- `MainActivity`: Hosts FormFragment via FragmentContainerView
- `DatePickerDialog`: Native date picker integration

**Key Features**:
- Real-time field validation
- Email, phone, and ID card format validation
- Date picker with custom date formatting
- Button state management based on form completion

**Files Created**:
```
Week5/Bai2/
├── app/src/main/java/com/example/bai2/
│   ├── MainActivity.kt
│   ├── FormFragment.kt
│   ├── FieldModel.kt
│   └── FieldSpinnerAdapter.kt
├── app/src/main/res/layout/
│   ├── activity_main.xml
│   └── fragment_form.xml
├── app/src/main/res/values/
│   ├── strings.xml
│   ├── colors.xml
│   └── themes.xml
└── gradle configuration files
```

### 2. **Bai3 - Dog Gallery with ViewPager2 & TabLayout**
**Status**: ⏳ Pending

Redesigned dog gallery using ViewPager2 for tab navigation and Fragment-based UI.

**Architecture**:
- `DogListFragment`: RecyclerView displaying dog list
- `DogDetailFragment`: Shows individual dog information
- `DogRecyclerAdapter`: Custom RecyclerView adapter
- `DogViewPagerAdapter`: FragmentStateAdapter for ViewPager2
- `MainActivity`: Sets up ViewPager2 and TabLayout

**Key Features**:
- Swipe-based navigation between dog list and details
- TabLayout for visual tab indicators
- RecyclerView for efficient list rendering
- Fragment lifecycle management and state preservation

### 3. **Bai4 - Task Management with Fragments**
**Status**: ⏳ Pending

Migrated task management app with multi-fragment architecture and CustomSpinner filtering.

**Architecture**:
- `TaskListFragment`: RecyclerView of tasks with filtering
- `AddTaskFragment`: Create new tasks
- `EditTaskFragment`: Modify existing tasks
- `FilterSpinnerAdapter`: Custom spinner for status filtering
- `MainActivity`: Fragment manager and navigation setup

**Key Features**:
- Add, edit, delete task operations
- Status-based filtering via custom spinner
- In-memory task storage
- Fragment-to-Fragment communication via arguments

### 4. **HarryPotter/List - Book Carousel with ViewPager2**
**Status**: ⏳ Pending

Book carousel implementation using ViewPager2 for horizontal scrolling.

**Architecture**:
- `BookCarouselFragment`: ViewPager2 container
- `BookDetailFragment`: Individual book information display
- `BookViewPagerAdapter`: FragmentStateAdapter for carousel
- `MainActivity`: Activity hosting ViewPager2

**Key Features**:
- Horizontal swipe navigation through books
- Page indicators for position tracking
- Smooth carousel transitions
- Fragment-based page management

### 5. **HarryPotter/Recycler - Book Browser with Fragments & Tabs**
**Status**: ⏳ Pending

Advanced book browser with category tabs, custom sorting, and RecyclerView.

**Architecture**:
- `BooksFragment`: RecyclerView of books with category filtering
- `BookDetailFragment`: Book detail display
- `BookRecyclerAdapter`: Custom RecyclerView adapter
- `SortSpinnerAdapter`: Custom spinner for sorting options
- `MainActivity`: ViewPager2 + TabLayout coordination

**Key Features**:
- Category-based tabs (Fantasy, Adventure, etc.)
- Sort options: by title, author, rating
- Dynamic list updates on tab/filter change
- Responsive RecyclerView rendering

## Technical Architecture

### Shared Patterns Across All Projects

#### 1. **Fragment Lifecycle Management**
```kotlin
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    // Initialize views and setup listeners
}
```

#### 2. **Custom Adapter Pattern**
- All projects use custom adapters extending `ArrayAdapter` or `RecyclerView.Adapter`
- Spinners: `*SpinnerAdapter` classes for field/filter selection
- RecyclerView: `*RecyclerAdapter` classes for list rendering

#### 3. **Fragment-to-Fragment Communication**
- Bundle arguments for passing data
- Fragment result API for two-way communication
- Shared data through parent Activity

#### 4. **ViewPager2 Integration**
```kotlin
val adapter = *ViewPagerAdapter(supportFragmentManager, lifecycle)
viewPager.adapter = adapter
TabLayoutMediator(tabLayout, viewPager) { tab, position ->
    tab.text = titles[position]
}.attach()
```

#### 5. **State Preservation**
- onSaveInstanceState/onRestoreInstanceState for configuration changes
- Fragment backstack management
- Data preservation during lifecycle transitions

## Gradle Configuration

All projects use:
- **AGP**: 8.13.0 (Android Gradle Plugin)
- **Kotlin**: 2.0.21
- **Target API**: 36 (Android 15)
- **Min API**: 29 (Android 10)

### Key Dependencies
```
androidx-fragment-ktx: 1.8.1     # Fragment support library
androidx-viewpager2: 1.1.0       # Modern ViewPager implementation
androidx-recyclerview: 1.3.2     # Efficient list rendering
material: 1.13.0                 # Material Design components
```

## Building & Running

### Build Individual Projects
```bash
cd Week5/Bai2
./gradlew build

cd Week5/Bai3
./gradlew build

# ... and so on for each project
```

### Install on Device/Emulator
```bash
./gradlew installDebug
```

### Run with Android Studio
1. Open individual project directory as project root
2. Run via Android Studio's Run button
3. Select target device/emulator

## Testing Checklist

For each project, verify:
- ✅ Fragment navigation and backstack management
- ✅ Data persistence during configuration changes (rotation)
- ✅ ViewPager2 swipe gestures (if applicable)
- ✅ TabLayout tab switching (if applicable)
- ✅ Custom Spinner dropdown functionality
- ✅ RecyclerView scrolling and item clicks (if applicable)
- ✅ Form validation (Bai2)
- ✅ CRUD operations (Bai4)

## Comparison: Week4 vs Week5

| Feature | Week4 | Week5 |
|---------|-------|-------|
| Navigation | Activity-to-Activity intents | Fragment backstack |
| List Display | GridView/ListView | RecyclerView |
| Paging | Manual Intent + Bundle | ViewPager2 + FragmentStateAdapter |
| Tabs | Custom TabView logic | Material TabLayout |
| State Management | Bundle in Intent | Fragment arguments + backstack |
| Lifecycle | Activity lifecycle | Fragment lifecycle + FragmentManager |
| Custom Selection | Simple Spinner | Custom SpinnerAdapter |
| Memory | Multiple Activity instances | Shared Activity + Fragment instances |

## Learning Outcomes

After completing Week5, students will understand:
1. **Fragment Lifecycle**: Creation, attachment, state management
2. **FragmentManager**: Transactions, backstack, state restoration
3. **ViewPager2**: Modern paging with FragmentStateAdapter
4. **TabLayout**: Material Design tab navigation
5. **Custom Adapters**: SpinnerAdapter and RecyclerView.Adapter patterns
6. **Fragment Communication**: Bundle arguments and fragment results
7. **State Preservation**: Configuration change handling
8. **Modern Architecture**: Single-Activity pattern with multiple Fragments

## Notes

- All projects maintain **functional parity** with Week4 implementations
- Minimal implementations focus on pattern education
- In-memory data storage (no persistence layer)
- No dependency injection or advanced state management frameworks
- No unit tests (educational focus on UI patterns)

## Next Steps

1. ✅ Infrastructure setup complete
2. ⏳ Implement remaining 4 projects following same patterns
3. ⏳ Test all projects on emulator/device
4. ⏳ Code quality review and cleanup
5. ⏳ Final build and validation
