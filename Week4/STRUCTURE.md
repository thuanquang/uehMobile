## Week4 - GridView BookStore App - Project Structure

### Project Overview
- **Theme**: Intent + Bundle, Activity Lifecycle, AdapterView (GridView), Image Loading Library (Glide)
- **Language**: Kotlin
- **Target SDK**: 36, Min SDK: 29
- **Package**: com.example.myapplication

### Directory Structure
```
app/src/main/
├── java/com/example/myapplication/
│   ├── MainActivity.kt          # Main activity with GridView (onCreate, onStart, onResume, onPause, onStop, onDestroy)
│   ├── DetailActivity.kt        # Detail activity showing clicked book
│   ├── BookModel.kt            # Data model (imgBook: Int, urlBook: String, txtBookName: String)
│   └── BookGridView.kt         # ArrayAdapter for GridView (getCount, getView with Glide)
├── res/
│   ├── drawable/
│   │   ├── harrypotter1.png    # Book cover images (placeholders to create)
│   │   ├── harrypotter2.png
│   │   ├── ... harrypotter8.png
│   ├── layout/
│   │   ├── activity_main.xml          # Main activity: LinearLayout + GridView (2 columns)
│   │   ├── activity_detail.xml        # Detail activity: ImageView + TextView
│   │   └── item_book.xml              # GridView item: CardView + ImageView (200x243dp) + TextView
│   ├── values/
│   │   ├── strings.xml
│   │   ├── colors.xml
│   │   └── themes.xml
└── AndroidManifest.xml          # Added INTERNET permission + DetailActivity

### Implementation Details

#### 1. BookModel.kt
```kotlin
data class BookModel(
    val imgBook: Int,
    val urlBook: String,
    val txtBookName: String
)
```

#### 2. BookGridView.kt
- Extends: ArrayAdapter<BookModel>
- getCount(): Returns list size
- getView(): Inflates item_book.xml, loads image using Glide, sets book name

#### 3. MainActivity.kt
- Activity Lifecycle: onCreate → onStart → onResume → onPause → onStop → onDestroy
- Creates 8 BookModel items (harrypotter1-7 from drawable, first one loads from URL)
- Sets GridView adapter
- Implements onItemClickListener to launch DetailActivity with Intent + Bundle

#### 4. DetailActivity.kt
- Receives Bundle via Intent
- Extracts: imgBook (Int), txtBookName (String), urlBook (String)
- Displays book image and title
- Implements Activity Lifecycle methods

### Gradle Dependencies Added
```gradle
implementation("androidx.cardview:cardview:1.0.0")
implementation("com.github.bumptech.glide:glide:4.16.0")
annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")
```

### Tasks Completed
- [x] BookModel.kt created
- [x] BookGridView.kt adapter created
- [x] MainActivity.kt with GridView and lifecycle methods
- [x] DetailActivity.kt with Intent + Bundle
- [x] activity_main.xml with GridView (2 columns)
- [x] item_book.xml with CardView layout
- [x] activity_detail.xml for detail view
- [x] build.gradle.kts updated with Glide and CardView
- [x] AndroidManifest.xml updated with permissions and activities
- [ ] Create placeholder images (harrypotter1-8) in drawable folder
