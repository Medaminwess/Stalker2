# Stalker 2 — English Edition — Android APK

## How to Build

### Requirements
- Android Studio (free, from developer.android.com/studio)
- JDK 11 or higher

### Steps
1. Open Android Studio
2. Click **File → Open** → select this folder
3. Wait for Gradle sync to finish (~2 min)
4. Click **Build → Build Bundle(s)/APK(s) → Build APK(s)**
5. APK will be at: `app/build/outputs/apk/debug/app-debug.apk`
6. Transfer to your phone and install (allow "Unknown sources" in settings)

### What this does
- Bundles the English-translated Stalker 2 JAR (457KB) inside the APK
- Uses J2ME Loader's runtime as a library
- Auto-launches the game on app start
- No separate J2ME Loader needed

### Controls
See VirtualKeyboardLayout file for Redmi Note 8 layout.
