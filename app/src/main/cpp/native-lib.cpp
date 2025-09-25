#include <jni.h>
#include <string>
#include "BS_thread_pool.hpp"
#include "atomic_queue/atomic_queue.h"
#include "atomic_queue/atomic_queue_mutex.h"
#include "atomic_queue/barrier.h"
#include "atomic_queue/defs.h"
#include "atomic_queue/spinlock.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_ozcomcn_modellbox_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}