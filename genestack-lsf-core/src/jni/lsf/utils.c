/*
 * Copyright (c) 2011-2018 Genestack Limited. All Rights Reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

#include <stdio.h>
#include <string.h>
#include <stdarg.h>

#include "utils.h"

#include <lsf/lsf.h>
#include <lsf/lsbatch.h>


void *mallocEx(JNIEnv *env, size_t size) {
    void *result = malloc(size);
    if (result == NULL) {
        lsberrno = LSBE_NO_MEM;
        throwLSFException(env, NULL);
        return NULL;
    }
    return result;
}

void *callocEx(JNIEnv *env, size_t nelem, size_t elsize) {
    void *result = calloc(nelem, elsize);
    if (result == NULL) {
        lsberrno = LSBE_NO_MEM;
        throwLSFException(env, NULL);
        return NULL;
    }
    return result;
}


void throwLSFBatchException(JNIEnv *env, const char *msg) {
    jclass cls = (*env)->FindClass(env, "com/genestack/cluster/lsf/LSFBatchException");
    if (cls == NULL) {
        (*env)->ExceptionDescribe(env);
        return;
    }

    const char *errMsg;
    if (msg != NULL) {
        errMsg = msg;
    } else {
        errMsg = lsb_sysmsg();
        if (errMsg == NULL) {
            errMsg = lsb_sysmsg();
            if (errMsg == NULL) {
                errMsg = "Unknown error: unable to get error message from LSBLIB";
            }
        }
    }
    (*env)->ThrowNew(env, cls, errMsg);
}

void throwFormattedLSFBatchException(JNIEnv *env, const char *format, ...) {
    va_list args;
    va_start(args, format);
    char *message = formatMessageV(env, format, args);
    va_end(args);

    if (message == NULL) {
        return;
    }
    throwLSFBatchException(env, message);
    free(message);
}

char *formatMessage(JNIEnv *env, const char *format, ...) {
    va_list args;
    va_start(args, format);
    char *message = formatMessageV(env, format, args);
    va_end(args);
    return message;
}

char *formatMessageV(JNIEnv *env, const char *format, va_list args) {
    va_list argsCopy;
    va_copy(argsCopy, args);
    int length = vsnprintf(NULL, 0, format, argsCopy);
    va_end(argsCopy);

    ++length; // zero terminator
    char *buffer = (char *)mallocEx(env, length);
    if (buffer == NULL) {
        return NULL;
    }
    vsnprintf(buffer, length, format, args);
    return buffer;
}


char *getUTFString(JNIEnv *env, jstring jstr) {
    if (jstr == NULL) {
        return NULL;
    }
    const char *str = (*env)->GetStringUTFChars(env, jstr, NULL);
    if (str == NULL) {
        return NULL;
    }
    char *copy = strdup(str);
    (*env)->ReleaseStringUTFChars(env, jstr, str);
    return copy;
}

void freeUTFString(char *str) {
    if (str) {
        free(str);
    }
}


int getStringArray(JNIEnv *env, jobjectArray stringArray, char ***array, int *size) {
    *size = 0;
    *array = NULL;
    if (stringArray == NULL) {
        return 0;
    }
    const int length = (*env)->GetArrayLength(env, stringArray);
    *size = length;
    if (length > 0) {
        *array = (char **)callocEx(env, length, sizeof(char *));
        if (*array == NULL) {
            return -1;
        }
        int i;
        char **ptr = *array;
        for (i = 0; i < length; ++i) {
            jstring jstr = (jstring)(*env)->GetObjectArrayElement(env, stringArray, i);
            if (jstr == NULL) {
                *ptr++ = NULL;
                continue;
            }
            const char* str = (*env)->GetStringUTFChars(env, jstr, NULL);
            *ptr++ = str == NULL ? NULL : strdup(str);
            (*env)->ReleaseStringUTFChars(env, jstr, str);
        }
    }
    return 0;
}

void freeStringArray(char **array, int size) {
    if (array == NULL) {
        return;
    }
    int i;
    for (i = 0; i < size; ++i) {
        freeUTFString(array[i]);
    }
    free(array);
}



#define DECLARE_FIELD_GETTER(RTYPE, SIG, MTYPE) \
    int get##MTYPE##Field(JNIEnv *env, jclass cls, jobject obj, const char *name, RTYPE *result) { \
        jfieldID fieldID = (*env)->GetFieldID(env, cls, name, SIG); \
        if (fieldID == NULL) { \
            return -1; \
        } \
        (*result) = (*env)->Get##MTYPE##Field(env, obj, fieldID); \
        return 0; \
    }

DECLARE_FIELD_GETTER(short, "S", Short)
DECLARE_FIELD_GETTER(int, "I", Int)
DECLARE_FIELD_GETTER(long, "J", Long)

int getStringField(JNIEnv *env, jclass cls, jobject obj, const char *name, char **result) {
    jfieldID fieldID = (*env)->GetFieldID(env, cls, name, "Ljava/lang/String;");
    if (fieldID == NULL) {
        return -1;
    }
    jstring jstr = (*env)->GetObjectField(env, obj, fieldID);
    (*result) = getUTFString(env, jstr);
    return 0;
}

int getStringArrayField(JNIEnv *env, jclass cls, jobject obj, const char *name, char ***result, int *size) {
    jfieldID fieldID = (*env)->GetFieldID(env, cls, name, "[Ljava/lang/String;");
    if (fieldID == NULL) {
        return -1;
    }
    jobjectArray jarray = (*env)->GetObjectField(env, obj, fieldID);
    return getStringArray(env, jarray, result, size);
}
