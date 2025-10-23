# Week4 - Android GridView BookStore App 📱📚

A complete Kotlin Android application demonstrating **GridView AdapterView**, **Intent + Bundle**, **Activity Lifecycle**, and **Image Loading with Glide**.

## Project Overview

This university exercise builds a Harry Potter book store app with:
- A 2-column GridView displaying 8 book items
- CardView layout for each book with image and title
- Click to view detailed book information in a separate activity
- Complete Activity Lifecycle logging
- Image loading using Glide library

## Features Implemented ✅

### 1. **Data Model (BookModel.kt)**
```kotlin
data class BookModel(
    val imgBook: Int,           // Drawable resource ID
    val urlBook: String,        // Backup URL for image
    val txtBookName: String     // Book title
)
```

### 2. **Custom Adapter (BookGridView.kt)**
- Extends `ArrayAdapter<BookModel>`
- Implements `getCount()` and `getView()` methods
- Uses **Glide** for intelligent image loading:
  - Loads from drawable if available
  - Falls back to URL loading otherwise

### 3. **Main Activity (MainActivity.kt)**
**Activity Lifecycle Methods** (all logged):
- `onCreate()` - Initialize GridView and data
- `onStart()` - Activity becoming visible
- `onResume()` - Activity gaining focus
- `onPause()` - Activity losing focus
- `onStop()` - Activity no longer visible
- `onDestroy()` - Activity being destroyed

**GridView Features**:
- 2-column layout
- 8 Harry Potter books
- **Intent + Bundle** on item click:
  ```kotlin
  val bundle = Bundle().apply {
      putInt("imgBook", book.imgBook)
      putString("txtBookName", book.txtBookName)
      putString("urlBook", book.urlBook)
  }
  intent.putExtra("bookData", bundle)
  startActivity(intent)
  ```

### 4. **Detail Activity (DetailActivity.kt)**
- Receives data via Intent + Bundle
- Displays full-size book image (300x400dp)
- Shows book title
- Implements complete Activity Lifecycle

### 5. **Layouts**

#### activity_main.xml
```xml
<GridView>
  - 2 columns
  - Vertical scrolling enabled
  - Horizontal scrolling enabled
</GridView>
```

#### item_book.xml
```xml
<CardView>
  <ImageView>
    - Width: 200dp
    - Height: 243dp
    - Scale type: fitCenter
  </ImageView>
  <TextView>
    - Book title
    - Color: #3F51B5
    - Centered gravity
  </TextView>
</CardView>
```

#### activity_detail.xml
```xml
<LinearLayout vertical>
  <ImageView>
    - Width: 300dp
    - Height: 400dp
    - Top margin: 30dp
  </ImageView>
  <TextView>
    - Size: 24sp
    - Bold text
    - Color: #3F51B5
  </TextView>
</LinearLayout>
```

## Project Structure

```
app/src/main/
├── java/com/example/myapplication/
│   ├── MainActivity.kt
│   ├── DetailActivity.kt
│   ├── BookModel.kt
│   └── BookGridView.kt
├── res/
│   ├── drawable/
│   │   ├── harrypotter1.png  ← Need to create
│   │   ├── harrypotter2.png
│   │   ├── ... harrypotter7.png
│   ├── layout/
│   │   ├── activity_main.xml
│   │   ├── activity_detail.xml
│   │   └── item_book.xml
│   └── values/
│       ├── strings.xml
│       ├── colors.xml
│       └── themes.xml
└── AndroidManifest.xml
```

## Dependencies

```gradle
// AndroidX libraries (existing)
implementation(libs.androidx.core.ktx)
implementation(libs.androidx.appcompat)
implementation(libs.material)
implementation(libs.androidx.activity)
implementation(libs.androidx.constraintlayout)

// CardView for item layout
implementation("androidx.cardview:cardview:1.0.0")

// Glide for image loading
implementation("com.github.bumptech.glide:glide:4.16.0")
annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")
```

## Android Permissions

```xml
<uses-permission android:name="android.permission.INTERNET" />
<!-- Required for Glide to load images from URLs -->
```

## Data (8 Books)

| Position | Image | Title | Type |
|----------|-------|-------|------|
| 0 | URL | Harry Potter - hình Internet | URL-loaded via Glide |
| 1 | harrypotter1 | Harry Potter - tập 1 | Drawable resource |
| 2 | harrypotter2 | Harry Potter - tập 2 | Drawable resource |
| 3 | harrypotter3 | Harry Potter - tập 3 | Drawable resource |
| 4 | harrypotter4 | Harry Potter - tập 4 | Drawable resource |
| 5 | harrypotter5 | Harry Potter - tập 5 | Drawable resource |
| 6 | harrypotter6 | Harry Potter - tập 6 | Drawable resource |
| 7 | harrypotter7 | Harry Potter - tập 7 | Drawable resource |

## How to Run

1. **Add placeholder images**: Create or add 7 images named `harrypotter1.png` to `harrypotter7.png` in `app/src/main/res/drawable/`
   - Recommended size: 200x243 pixels
   - Format: PNG

2. **Build the project**:
   ```bash
   ./gradlew build
   ```

3. **Run on emulator or device**:
   ```bash
   ./gradlew installDebug
   ```

4. **View Logcat to see Activity Lifecycle**:
   ```
   MainActivity: onCreate() called
   MainActivity: onStart() called
   MainActivity: onResume() called
   [User clicks item]
   DetailActivity: onCreate() called
   DetailActivity: onStart() called
   DetailActivity: onResume() called
   [User presses back]
   MainActivity: onResume() called
   ```

## Key Concepts Demonstrated

### ✅ AdapterView - GridView
- 2-column layout
- Custom ArrayAdapter
- View inflation and binding

### ✅ Intent + Bundle
- Pass data between activities
- Bundle contains: image resource ID, title, URL
- Received in DetailActivity and extracted

### ✅ Activity Lifecycle
- All 6 main methods implemented
- Logging at each stage
- Observable in Android Studio Logcat

### ✅ Image Loading - Glide
- Automatic caching
- Drawable resource loading
- URL fallback loading
- Error handling (implicit)

## Testing Checklist

- [ ] App builds without errors
- [ ] GridView displays 2 columns with 8 books
- [ ] Book images display correctly
- [ ] Click item opens DetailActivity
- [ ] DetailActivity shows clicked book's image and title
- [ ] Back button returns to MainActivity
- [ ] Logcat shows Activity Lifecycle transitions
- [ ] First book loads image from URL (if internet connected)

## File Status

| File | Status | Notes |
|------|--------|-------|
| BookModel.kt | ✅ Complete | Data class ready |
| BookGridView.kt | ✅ Complete | Adapter ready |
| MainActivity.kt | ✅ Complete | GridView + Lifecycle ready |
| DetailActivity.kt | ✅ Complete | Intent reception ready |
| activity_main.xml | ✅ Complete | GridView layout ready |
| activity_detail.xml | ✅ Complete | Detail layout ready |
| item_book.xml | ✅ Complete | CardView item ready |
| build.gradle.kts | ✅ Complete | Dependencies added |
| AndroidManifest.xml | ✅ Complete | Permissions + activities |
| **harrypotter1-7.png** | ❌ **PENDING** | **Need to add 7 images** |

## Next Steps

1. Add placeholder images (harrypotter1-7.png) to `app/src/main/res/drawable/`
2. Build and test the app
3. View Activity Lifecycle logs in Logcat
4. Test Intent + Bundle by clicking grid items

---

**Created**: Week4 Exercise  
**Theme**: Intent + Bundle, Activity Lifecycle, AdapterView (GridView), Image Loading  
**Language**: Kotlin  
**Target**: Android 14+ (SDK 36)
