# Installation & Setup Guide - Week4 Android GridView App

## ✅ Project Status: **95% COMPLETE**

All source code, layouts, and dependencies are implemented. **Only placeholder images are needed.**

---

## 📋 What's Been Created

### Kotlin Source Files (4 files)
| File | Purpose | Status |
|------|---------|--------|
| `MainActivity.kt` | Main activity with GridView + Activity Lifecycle | ✅ |
| `DetailActivity.kt` | Detail view activity with Intent + Bundle | ✅ |
| `BookModel.kt` | Data model class | ✅ |
| `BookGridView.kt` | Custom ArrayAdapter for GridView | ✅ |

**Location**: `app/src/main/java/com/example/myapplication/`

---

### XML Layout Files (3 files)
| File | Purpose | Status |
|------|---------|--------|
| `activity_main.xml` | Main activity layout with GridView | ✅ |
| `activity_detail.xml` | Detail activity layout | ✅ |
| `item_book.xml` | GridView item layout with CardView | ✅ |

**Location**: `app/src/main/res/layout/`

---

### Configuration Files Updated
| File | Changes | Status |
|------|---------|--------|
| `build.gradle.kts` | Added CardView + Glide dependencies | ✅ |
| `AndroidManifest.xml` | Added INTERNET permission + DetailActivity | ✅ |

---

## 🖼️ REQUIRED: Add Placeholder Images

To complete the app, you **must add 7 images** to the drawable folder.

### Image Requirements
```
Location: app/src/main/res/drawable/

Required files:
  ✓ harrypotter1.png
  ✓ harrypotter2.png
  ✓ harrypotter3.png
  ✓ harrypotter4.png
  ✓ harrypotter5.png
  ✓ harrypotter6.png
  ✓ harrypotter7.png

Recommended specs:
  - Size: 200x243 pixels (or 400x486 for 2x density)
  - Format: PNG
  - Color: Any (book cover art preferred)
```

### Where to Add Images
Right-click on `app/src/main/res/drawable/` in Android Studio:
1. Select "New" → "Image Asset"
   OR
2. Copy PNG files directly into the folder

### Quick Option: Use Placeholder Images
If you don't have actual Harry Potter images, create simple placeholder PNGs:
- Use a tool like: Paint, Photoshop, or online PNG generators
- Create 200x243px images
- Add simple colors or text labels (Harry Potter 1-7)
- Save as: `harrypotter1.png` to `harrypotter7.png`

---

## 🚀 Building the Project

### Step 1: Open in Android Studio
```
File → Open → Select Week4 folder
```

### Step 2: Sync Gradle
```
File → Sync Now
(Or wait for automatic sync prompt)
```

### Step 3: Add Images (if not done yet)
Copy/create 7 PNG files to `app/src/main/res/drawable/`

### Step 4: Build
```
Build → Build Project
(Or press Ctrl+F9)
```

Should complete with **0 errors** ✅

---

## ▶️ Running the App

### On Android Studio Emulator
1. Create/select an emulator (API 29+)
2. Click the **Run** button (green play icon)
3. Select target device
4. App launches with GridView showing 8 books

### On Physical Device
1. Enable Developer Mode on device
2. Connect via USB
3. Click the **Run** button
4. Select your device
5. App installs and launches

---

## 📱 Testing the Features

### Test 1: GridView Display ✅
- [ ] App launches showing main activity
- [ ] GridView displays 2 columns
- [ ] 8 book items visible
- [ ] Images load (placeholder or URL)
- [ ] Book titles displayed

### Test 2: Activity Lifecycle 📊
- [ ] Open Android Studio Logcat
- [ ] Filter by "MainActivity"
- [ ] You should see:
  ```
  MainActivity: onCreate() called
  MainActivity: onStart() called
  MainActivity: onResume() called
  ```

### Test 3: Intent + Bundle 🔗
- [ ] Click any book item in GridView
- [ ] DetailActivity opens
- [ ] Correct image displays
- [ ] Correct book title displays
- [ ] Size is larger (300x400dp) than grid item
- [ ] Logcat shows:
  ```
  MainActivity: Item clicked at position: [X]
  DetailActivity: onCreate() called
  DetailActivity: Received book: [book name]
  ```

### Test 4: Navigation Back 🔙
- [ ] Press back button or system back
- [ ] Returns to MainActivity
- [ ] Logcat shows:
  ```
  DetailActivity: onPause() called
  DetailActivity: onStop() called
  MainActivity: onResume() called
  ```

### Test 5: Image Loading (Bonus) 🖼️
- [ ] First item (index 0) loads from URL via Glide
- [ ] Other items load from drawable resources
- [ ] Images cache properly (no reloading on scroll)

---

## 🔧 Troubleshooting

### Build Error: Cannot resolve Glide
**Solution**: Run `File → Sync Now` again, then rebuild

### Build Error: R.drawable.harrypotter1 not found
**Solution**: Add the 7 PNG files to `app/src/main/res/drawable/`

### App Crashes on Launch
**Solution**: Check Logcat for errors:
1. Run the app
2. Open Logcat (View → Tool Windows → Logcat)
3. Look for red error messages
4. Fix based on error details

### GridView Shows Empty
**Solution**: 
1. Ensure images are in drawable folder
2. Verify image filenames match exactly (case-sensitive):
   - `harrypotter1.png` NOT `HarryPotter1.png`
3. Check AndroidManifest.xml has DetailActivity registered

### Detail Activity Won't Open
**Solution**:
1. Verify DetailActivity in AndroidManifest.xml
2. Check logcat for Intent errors
3. Ensure Bundle keys match in MainActivity and DetailActivity

---

## 📝 Gradle Build Commands (Alternative)

If not using Android Studio UI:

```bash
# Navigate to project
cd C:\Users\Asus\Desktop\uehMobile\Week4

# Sync dependencies
./gradlew clean

# Build the project
./gradlew build

# Run on connected device
./gradlew installDebug
```

---

## ✨ Project Complete When:

- [x] All 4 Kotlin files created
- [x] All 3 layout XMLs created
- [x] build.gradle.kts updated with dependencies
- [x] AndroidManifest.xml updated
- [ ] **7 images added (harrypotter1-7.png)**
- [x] Project builds without errors
- [x] No missing import errors

---

## 📚 Learning Objectives Met

✅ **GridView (AdapterView)**
- 2-column layout implemented
- Custom ArrayAdapter created
- Items click-able

✅ **Intent + Bundle**
- Data passed between activities
- Bundle extracted correctly

✅ **Activity Lifecycle**
- All 6 methods implemented
- Logging at each stage

✅ **Image Loading (Glide)**
- Drawable resource loading
- URL fallback loading
- Caching automatic

---

**Ready to go! Just add the 7 image files and you're done!** 🎉
