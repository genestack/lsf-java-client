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

#ifndef _Included_utils
#define _Included_utils

#include <jni.h>
#include <stdlib.h>


/**
 * Allocates memory and throws LSFBatchException on failure.
 */
void *mallocEx(JNIEnv *env, size_t size);

/**
 * Allocates memory and throws LSFBatchException on failure.
 */
void *callocEx(JNIEnv *env, size_t nelem, size_t elsize);



/**
 * Throws LSFBatchException.
 * If 'msg' is supplied, it is used as error message, otherwise lsb_sysmsg() is used.
 */
void throwLSFBatchException(JNIEnv *env, const char *msg);

/**
 * Throws LSFBatchException with message composed according to format string (like printf).
 */
void throwFormattedLSFBatchException(JNIEnv *env, const char *format, ...);

char *formatMessage(JNIEnv *env, const char *format, ...);
char *formatMessageV(JNIEnv *env, const char *format, va_list args);



/**
 * Returns C string from Java String or NULL.
 * Memory allocated for C string should be then released with freeUTFString().
 */
char *getUTFString(JNIEnv *env, jstring jstr);
void freeUTFString(char *str);

/**
 * Creates C string array from Java string array, the number of elements
 * is returned in *size, the string array is returned in *array.
 *
 * If stringArray is NULL or array length is 0, then *array will be set to NULL
 * and *size will be set to 0.
 *
 * The Java strings will be copied to the C string array, and callers must
 * free the returned C string array and all C strings using freeStringArray().
 *
 * Return 0 if success, -1 if failed.
 * LSFBatchException is thrown on failure, so caller doesn't need to throw it again.
 */
int getStringArray(JNIEnv *env, jobjectArray stringArray, char ***array, int *size);
void freeStringArray(char **array, int size);



/**
 * Return 0 on success, -1 on failure.
 * Exception is thrown on failure, so caller doesn't need to throw it again.
 */
int getShortField(JNIEnv *env, jclass cls, jobject obj, const char *name, short *result);
int getIntField(JNIEnv *env, jclass cls, jobject obj, const char *name, int *result);
int getLongField(JNIEnv *env, jclass cls, jobject obj, const char *name, long *result);

/**
 * Return 0 on success, -1 on failure.
 * Returned string in 'result' should be released with freeUTFString().
 * Exception is thrown on failure, so caller doesn't need to throw it again.
 */
int getStringField(JNIEnv *env, jclass cls, jobject obj, const char *name, char **result);

/**
 * Return 0 on success, -1 on failure.
 * Returned array in 'result' should be released with freeStringArray().
 * Exception is thrown on failure, so caller doesn't need to throw it again.
 */
int getStringArrayField(JNIEnv *env, jclass cls, jobject obj, const char *name, char ***result, int *size);


#endif /* _Included_utils */
