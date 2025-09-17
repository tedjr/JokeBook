# 😂 JokeBook (Android · Jetpack Compose)

A tiny, modern Android app that fetches jokes from the **Official Joke API** and shows them in three screens:

- **Cover** → friendly entry screen  
- **Categories** → choose `general`, `knock-knock`, or `programming`, then view joke **setups**  
- **Punchline** → tap a setup to reveal the **punchline**, with back navigation

_No API keys, no auth , just laughs :D_

---

## Features

- Kotlin + **Jetpack Compose (Material 3)**
- **Navigation Compose** for in-app navigation
- **Retrofit + Moshi** for networking/JSON
- **Coroutines + StateFlow** for UI state
- Simple **per-category cache** in the repository to reduce repeat calls

---

## Tech & Versions

- **Min SDK** 24 · **Target/Compile SDK** 36  
- **Kotlin** 2.0.21 · **AGP** 8.13.0  
- **Compose BOM** 2024.09.00  
- **Navigation Compose** 2.8.3  
- **Retrofit** 2.11.0 · **OkHttp** 4.12.0 · **Moshi** 1.15.1  
- Dependency versions managed via **`gradle/libs.versions.toml`**

---



**Data flow:** `JokeApi` → `DefaultJokeRepository` (cache) → `JokeViewModel` (StateFlow) → Compose UI.

---

## API
GET https://official-joke-api.appspot.com/jokes/{category}/ten



- `category ∈ { general, knock-knock, programming }`  
- Response: `List<Joke>` where  
  ```json
  {
    "id": 1,
    "type": "general",
    "setup": "Why did the chicken cross the road?",
    "punchline": "To get to the other side."
  }

---

## Requirements

- **Android Studio** (latest stable)
- **JDK 17** (Android Studio’s **Embedded JDK** is recommended)
- Android SDKs for **API 36**
- Internet connection (no API key)

---

## Installation & Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/tedjr/JokeBook.git
   cd JokeBook

**Open in Android Studio**
   - Go to **File → Open…**
   - Select the project root
   - Wait for **Gradle sync** to complete

3. **Ensure JDK 17**
   - In Android Studio:  
     **File → Settings → Build, Execution, Deployment → Build Tools → Gradle → Gradle JDK → Embedded JDK (17)**
   - Or pin JDK 17 in `gradle.properties` (project root):
     ```properties
     org.gradle.java.home=C:/Program Files/Android/Android Studio/jbr

## Run the App

1. **Start an emulator** or **connect a device**  
2. **Click** ▶ **Run** in Android Studio  
3. Or build from CLI:  
   ```bash
   ./gradlew installDebug
   adb shell am start -n com.example.jokebook/.MainActivity

   ./gradlew assembleDebug
   ./gradlew installDebug   # installs to a connected device



## 🧰 Useful Commands
 Build (debug)
./gradlew assembleDebug

 Install to a connected device
./gradlew installDebug

 Clean build
./gradlew clean



