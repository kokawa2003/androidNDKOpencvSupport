// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("myapplication");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("myapplication")
//      }
//    }

#include <opencv2/opencv.hpp>
#include <jni.h>
#include "android/log.h"

#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, "TAG", __VA_ARGS__)
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, "TAG", __VA_ARGS__)

class Mat;

class Mat;

extern "C" JNIEXPORT jint JNICALL
Java_com_example_myapplication_MainActivity_processImage(
        JNIEnv* env,
        jobject, /* this */
        jlong   objMatSrc,
        jlong   objMatDst);

extern "C" jint Java_com_example_myapplication_MainActivity_processImage(JNIEnv *env, jobject, jlong objMatSrc,
                                                              jlong objMatDst) {

    LOGD("processimage called");


    auto* matSrc = (cv::Mat*) objMatSrc;
    auto* matDst = (cv::Mat*) objMatDst;

    /*
    static cv::Mat *matPrevious = NULL;
    if (matPrevious == NULL) {

        matPrevious = new cv::Mat(matSrc->rows, matSrc->cols, matSrc->type());
    }
    cv::absdiff(*matSrc, *matPrevious, *matDst);
    *matPrevious = *matSrc;*/
    cv::cvtColor(*matSrc, *matDst, cv::COLOR_RGB2GRAY);  // まずグレースケールへ(明るさだけの形式)
    cv::threshold(*matDst,*matDst, 128.0, 255.0, cv::THRESH_BINARY);

    return 0;
}
