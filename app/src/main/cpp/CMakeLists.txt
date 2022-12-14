
# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.18.1)

# Declares and names the project.

project("myapplication")

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
#https://qiita.com/iwatake2222/items/eb4e135d45e5056f8d39

add_library( # Sets the name of the library.
             myapplication

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             myapplication.cpp )

# ↓↓↓ 追加 ↓↓↓
#set(OpenCV_DIR "D:/devel/opencv-4.3.0-android-sdk/OpenCV-android-sdk/sdk/native/jni")
#set(OpenCV_DIR "/home/kokawa2003/midas/MiDaS/mobile/android/opencv/native/jni")
set(OpenCV_DIR "${CMAKE_CURRENT_LIST_DIR}/../../../../opencv/native/jni")
find_package(OpenCV REQUIRED)
if(OpenCV_FOUND)
    include_directories(${OpenCV_INCLUDE_DIRS})
    target_include_directories(myapplication PUBLIC ${OpenCV_INCLUDE_DIRS})
    target_link_libraries(myapplication ${OpenCV_LIBS})
endif()
# ↑↑↑ 追加 ↑↑↑


# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
                       myapplication

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )
