#include "com_lst_burns_mesureboottime_DeviceHelper.h"

#include "android_system_utils.h"
#include "common.h"

JNIEXPORT jlong JNICALL Java_com_lst_burns_mesureboottime_DeviceHelper_native_1getBootTime
  (JNIEnv *, jclass){
    return android_system_utils::getBootTime();
  }