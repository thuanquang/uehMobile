## Week4 Project - Detailed Functionality Documentation

### Project Completion Status: 95% (Awaiting placeholder images)

### BookModel Data Class
**File**: `BookModel.kt` ✅
**Properties**:
- `imgBook: Int` - Drawable resource ID for book cover (0 if loading from URL)
- `urlBook: String` - URL for image loading fallback
- `txtBookName: String` - Name/title of the book

### BookGridView Adapter
**File**: `BookGridView.kt` ✅
**Extends**: `ArrayAdapter<BookModel>`
**Key Methods**:
- `getCount()` - Returns number of items in list
- `getView(position, convertView, parent)` - Inflates item_book.xml and populates:
  - If imgBook != 0: Use setImageResource(imgBook)
  - Else: Use Glide to load from URL
  - Set text to txtBookName

### MainActivity - Complete Activity Lifecycle
**File**: `MainActivity.kt` ✅
**Lifecycle Methods** (all with Log.d):
- `onCreate(Bundle)` - Initialize GridView, create book list, set adapter
- `onStart()` - Log start
- `onResume()` - Log resume
- `onPause()` - Log pause
- `onStop()` - Log stop
- `onDestroy()` - Log destroy

**Data Creation**:
- 8 BookModel items created
- First: `BookModel(0, "", "Harry Potter - hình Internet")` - loads from URL
- Rest: `BookModel(R.drawable.harrypotter1-7, "", "Harry Potter - tập 1-7")`

**GridView Setup**:
- adapter = BookGridView(this, listBook)
- numColumns = 2

**Intent + Bundle** (onItemClickListener):
- Create Bundle with: imgBook (Int), txtBookName (String), urlBook (String)
- Create Intent to DetailActivity
- putExtra("bookData", bundle)
- startActivity(intent)

### DetailActivity - Intent Reception & Lifecycle
**File**: `DetailActivity.kt` ✅
**Lifecycle Methods**: onCreate, onStart, onResume, onPause, onStop, onDestroy (all with logging)

**Intent + Bundle Reception**:
- intent.getBundleExtra("bookData")
- Extract: imgBook, txtBookName, urlBook
- Display using ImageView and TextView

### Layout Files

#### activity_main.xml ✅
```xml
<LinearLayout android:orientation="vertical">
    <GridView android:id="@+id/gvBook"
              android:numColumns="2"
              android:scrollbarAlwaysDrawVerticalTrack="true"
              android:scrollbarAlwaysDrawHorizontalTrack="true" />
</LinearLayout>
```

#### item_book.xml ✅
```xml
<CardView>
    <LinearLayout android:orientation="vertical">
        <ImageView android:id="@+id/imgBook"
                   android:layout_width="200dp"
                   android:layout_height="243dp"
                   android:padding="2dp"
                   android:scaleType="fitCenter" />
        <TextView android:id="@+id/txtBookName"
                  android:layout_marginTop="249dp"
                  android:gravity="center_horizontal|center_vertical"
                  android:textColor="#3F51B5" />
    </LinearLayout>
</CardView>
```

#### activity_detail.xml ✅
```xml
<LinearLayout android:orientation="vertical" android:gravity="center_horizontal">
    <ImageView android:id="@+id/imgBook"
               android:layout_width="300dp"
               android:layout_height="400dp"
               android:layout_marginTop="30dp" />
    <TextView android:id="@+id/txtBookName"
              android:textSize="24sp"
              android:textStyle="bold"
              android:textColor="#3F51B5"
              android:gravity="center_horizontal" />
</LinearLayout>
```

### Dependencies
```gradle
// CardView for item layout
implementation("androidx.cardview:cardview:1.0.0")

// Glide for image loading
implementation("com.github.bumptech.glide:glide:4.16.0")
annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")
```

### Android Permissions (AndroidManifest.xml)
- `<uses-permission android:name="android.permission.INTERNET" />` - For Glide URL loading
- Added DetailActivity to manifest

### Required Drawable Images
**Status**: ❌ PENDING - Need to create or add:
- harrypotter1.png - 200x243dp minimum
- harrypotter2.png
- harrypotter3.png
- harrypotter4.png
- harrypotter5.png
- harrypotter6.png
- harrypotter7.png

**Note**: First book (Harry Potter - hình Internet) uses URL loading via Glide

### Testing Checklist
- [ ] App builds successfully without errors
- [ ] GridView displays with 2 columns
- [ ] Images load correctly from drawable
- [ ] Activity Lifecycle logs appear in Logcat: MainActivity onCreate → onStart → onResume
- [ ] Clicking grid item opens DetailActivity
- [ ] DetailActivity shows correct book image and name
- [ ] Back button returns to MainActivity
- [ ] Activity Lifecycle continues: onPause → onStop
- [ ] Glide URL loading works for first item (if internet available)

### Architecture Overview
```
User launches MainActivity
    ↓
onCreate() - GridView setup with BookGridView adapter
    ↓
User sees 2-column grid of 8 books
    ↓
User clicks item
    ↓
onItemClickListener creates Intent + Bundle
    ↓
DetailActivity opens with book details
    ↓
User presses back
    ↓
Returns to MainActivity (onResume)
    ↓
Lifecycle continues through onPause → onStop → (if closed) onDestroy
```
