/*
 * Copyright (c) 2011-2013 Genestack Limited
 * All Rights Reserved
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF GENESTACK LIMITED
 * The copyright notice above does not evidence any
 * actual or intended publication of such source code.
 */

#include <stdio.h>
#include <string.h>

#include "utils.h"
#include "com_genestack_cluster_lsf_impl_LSFBatch.h"

#include <lsf/lsf.h>
#include <lsf/lsbatch.h>



/**
 * This function is requred in order to use any of the new JNI functions as of JDK/JRE 1.2.
 * See "Library and Version Management" of Java Native Interface Specification.
 */
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    return JNI_VERSION_1_2;
}



/*
 * Class:     com_genestack_cluster_lsf_impl_LSFBatch
 * Method:    init
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_com_genestack_cluster_lsf_impl_LSFBatch_init(JNIEnv *env, jobject thiz, jstring appName) {
    char *utfName = getUTFString(env, appName);
    int rc = lsb_init(utfName);
    freeUTFString(utfName);
    if (rc < 0) {
        throwLSFBatchException(env, NULL);
        return;
    }
}


jobjectArray _hostInfo_internal(JNIEnv *env, char **hosts, const int hostsSize) {
    int numHosts = hostsSize;
    struct hostInfoEnt *info = lsb_hostinfo(hosts, &numHosts);
    if (info == NULL) {
        if (hosts == NULL) {
            throwLSFBatchException(env, NULL);
        } else if (lsberrno == LSBE_BAD_HOST) {
            throwFormattedLSFBatchException(env, "Unknown host: %s", hosts[numHosts]);
        } else if (numHosts < hostsSize) {
            throwFormattedLSFBatchException(
                env,
                "Number of known hosts less than number of requested hosts: %d < %d",
                numHosts, hostsSize
            );
        } else {
            throwLSFBatchException(env, NULL);
        }
        return NULL;
    }

    jclass cls = (*env)->FindClass(env, "com/genestack/backend/tasks/lsf/model/HostInfoEntry");
    if (cls == NULL) {
        return NULL;
    }
    jmethodID constructor = (*env)->GetMethodID(env, cls, "<init>", "(Ljava/lang/String;)V");
    if (constructor == NULL) {
        return NULL;
    }

    jobjectArray array = (*env)->NewObjectArray(env, numHosts, cls, NULL);
    int i;
    struct hostInfoEnt *ptr = info;
    for (i = 0; i < numHosts; ++i, ++ptr) {
        jstring jHost = (*env)->NewStringUTF(env, ptr->host);
        if (jHost == NULL) {
            return NULL;
        }
        jobject obj = (*env)->NewObject(
            env, cls, constructor,
            jHost
        );
        if (obj == NULL) {
            return NULL;
        }
        (*env)->SetObjectArrayElement(env, array, i, obj);
        if ((*env)->ExceptionCheck(env) == JNI_TRUE) {
            return NULL;
        }
    }
    return array;
}

/*
 * Class:     com_genestack_cluster_lsf_impl_LSFBatch
 * Method:    hostInfo
 * Signature: ([Ljava/lang/String;)[Lcom/genestack/backend/tasks/lsf/model/HostInfoEntry;
 */
JNIEXPORT jobjectArray JNICALL Java_com_genestack_cluster_lsf_impl_LSFBatch_hostInfo(JNIEnv *env, jobject thiz, jobjectArray hostsArray) {
    char **hosts = NULL;
    int hostsSize = 0;
    if (hostsArray != NULL &&
            getStringArray(env, hostsArray, &hosts, &hostsSize) < 0) {
        return NULL;
    }
    jobjectArray result = _hostInfo_internal(env, hosts, hostsSize);
    freeStringArray(hosts, hostsSize);
    return result;
}

/*
 * Class:     com_genestack_cluster_lsf_impl_LSFBatch
 * Method:    localHostInfo
 * Signature: ()Lcom/genestack/backend/tasks/lsf/model/HostInfoEntry;
 */
JNIEXPORT jobject JNICALL Java_com_genestack_cluster_lsf_impl_LSFBatch_localHostInfo(JNIEnv *env, jobject thiz) {
    jobjectArray result = _hostInfo_internal(env, NULL, 1);
    if (result == NULL) {
        return NULL;
    }
    return (*env)->GetObjectArrayElement(env, result, 0);
}


int fillIntArray(JNIEnv *env, jclass cls, jobject obj, const char *name, int *result, int size) {
    jfieldID fieldID = (*env)->GetFieldID(env, cls, name, "[I");
    if (fieldID == NULL) {
        return -1;
    }
    jintArray jarray = (*env)->GetObjectField(env, obj, fieldID);
    int jarrayLength = (*env)->GetArrayLength(env, jarray);
    if (jarrayLength != size) {
        throwFormattedLSFBatchException(
            env, "Invalid length of %s array: expected = %d, actual = %d",
            name, size, jarrayLength
        );
        return -1;
    }
    jint *jarrayItems = (*env)->GetIntArrayElements(env, jarray, NULL);
    if (jarrayItems == NULL) {
        throwFormattedLSFBatchException(
            env, "Unexpected error: Unable to read %s array", name
        );
        return -1;
    }
    int i;
    for (i = 0; i < LSF_RLIM_NLIMITS; ++i) {
        result[i] = jarrayItems[i];
    }
    (*env)->ReleaseIntArrayElements(env, jarray, jarrayItems, JNI_ABORT);
    return 0;
}

/**
 * Fills 'request' structure.
 * Returns 0 on success, -1 on failure.
 * Exception is thrown on failure, so caller doesn't need to throw it again.
 */
int _submit_fill_request(JNIEnv *env, jobject obj, struct submit *request) {
    if (obj == NULL) {
        throwLSFBatchException(env, "Request should not be null");
        return -1;
    }
    jclass cls = (*env)->GetObjectClass(env, obj);

    memset(request, 0, sizeof(*request));

    long beginTime, termTime;

    if (getIntField(env, cls, obj, "options", &request->options) < 0
        || getIntField(env, cls, obj, "options2", &request->options2) < 0
        || getStringField(env, cls, obj, "jobName", &request->jobName) < 0
        || getStringField(env, cls, obj, "queue", &request->queue) < 0
        || getIntField(env, cls, obj, "options2", &request->options2) < 0
        || getStringArrayField(env, cls, obj, "askedHosts", &request->askedHosts, &request->numAskedHosts) < 0
        || getStringField(env, cls, obj, "resReq", &request->resReq) < 0
        || fillIntArray(env, cls, obj, "rLimits", request->rLimits, LSF_RLIM_NLIMITS) < 0
        || getStringField(env, cls, obj, "hostSpec", &request->hostSpec) < 0
        || getIntField(env, cls, obj, "numProcessors", &request->numProcessors) < 0
        || getStringField(env, cls, obj, "dependCond", &request->dependCond) < 0
        || getStringField(env, cls, obj, "timeEvent", &request->timeEvent) < 0
        || getLongField(env, cls, obj, "beginTime", &beginTime) < 0
        || getLongField(env, cls, obj, "termTime", &termTime) < 0
        || getStringField(env, cls, obj, "inFile", &request->inFile) < 0
        || getStringField(env, cls, obj, "outFile", &request->outFile) < 0
        || getStringField(env, cls, obj, "errFile", &request->errFile) < 0
        || getStringField(env, cls, obj, "command", &request->command) < 0
        || getStringField(env, cls, obj, "newCommand", &request->newCommand) < 0
        || getStringField(env, cls, obj, "preExecCmd", &request->preExecCmd) < 0
        || getStringField(env, cls, obj, "mailUser", &request->mailUser) < 0
        || getStringField(env, cls, obj, "projectName", &request->projectName) < 0
        || getIntField(env, cls, obj, "maxNumProcessors", &request->maxNumProcessors) < 0
        || getStringField(env, cls, obj, "loginShell", &request->loginShell) < 0
        || getStringField(env, cls, obj, "userGroup", &request->userGroup) < 0
        || getIntField(env, cls, obj, "options3", &request->options3) < 0
        || getStringField(env, cls, obj, "postExecCmd", &request->postExecCmd) < 0
        || getStringField(env, cls, obj, "cwd", &request->cwd) < 0
        || getStringField(env, cls, obj, "jobDescription", &request->jobDescription) < 0
    ) {
        return -1;
    }

    request->beginTime = beginTime; // TODO: better conversion between long and time_t
    request->termTime = termTime; // TODO: better conversion between long and time_t

    return 0;
}

void _submit_release_request(struct submit *request) {
    freeUTFString(request->jobName);
    freeUTFString(request->queue);
    freeStringArray(request->askedHosts, request->numAskedHosts);
    freeUTFString(request->resReq);
    freeUTFString(request->hostSpec);
    freeUTFString(request->dependCond);
    freeUTFString(request->timeEvent);
    freeUTFString(request->inFile);
    freeUTFString(request->outFile);
    freeUTFString(request->errFile);
    freeUTFString(request->command);
    freeUTFString(request->newCommand);
    freeUTFString(request->preExecCmd);
    freeUTFString(request->mailUser);
    freeUTFString(request->projectName);
    freeUTFString(request->loginShell);
    freeUTFString(request->userGroup);
    freeUTFString(request->postExecCmd);
    freeUTFString(request->cwd);
    freeUTFString(request->jobDescription);
}

/*
 * Class:     com_genestack_cluster_lsf_impl_LSFBatch
 * Method:    submit
 * Signature: (Lcom/genestack/backend/tasks/lsf/model/SubmitRequest;)Lcom/genestack/backend/tasks/lsf/model/SubmitReply;
 */
JNIEXPORT jobject JNICALL Java_com_genestack_cluster_lsf_impl_LSFBatch_submit(JNIEnv *env, jobject thiz, jobject requestObj) {
    struct submit request;
    if (_submit_fill_request(env, requestObj, &request) < 0) {
        return NULL;
    }

    struct submitReply reply;
    memset(&reply, 0, sizeof(reply));

    LS_LONG_INT jobID = lsb_submit(&request, &reply);
    if (jobID < 0) {
        const char *message = lsb_sysmsg();
        switch (lsberrno) {
            case LSBE_RESREQ_ERR:
                throwFormattedLSFBatchException(env, "%s: %s", message, reply.badJobName);
                break;
            case LSBE_QUEUE_USE:
            case LSBE_QUEUE_CLOSED:
                throwFormattedLSFBatchException(env, "%s (queue = %s)", message, reply.queue);
                break;
            case LSBE_BAD_HOST:
            case LSBE_QUEUE_HOST:
                if (reply.badReqIndx >= 0 && reply.badReqIndx < request.numAskedHosts) {
                    throwFormattedLSFBatchException(
                        env, "%s (host = %s)", message,  request.askedHosts[reply.badReqIndx]
                    );
                } else {
                    throwLSFBatchException(env, message);
                }
                break;
            case LSBE_OVER_LIMIT:
                throwFormattedLSFBatchException(
                    env, "%s (rLimit index = %d, value = %d)",
                    message, reply.badReqIndx, request.rLimits[reply.badReqIndx]
                );
                break;
            default:
                if (reply.badJobName != NULL) {
                    throwFormattedLSFBatchException(
                        env, "%s: bad job name = %s", message, reply.badJobName
                    );
                } else if (reply.badJobId != 0) {
                    throwFormattedLSFBatchException(
                        env, "%s: bad job ID = %d", message, reply.badJobId
                    );
                } else {
                    throwLSFBatchException(env, message);
                }
                break;
        }
    }
    _submit_release_request(&request);
    if (jobID < 0) {
        return NULL;
    }

    jclass cls = (*env)->FindClass(env, "com/genestack/backend/tasks/lsf/model/SubmitReply");
    if (cls == NULL) {
        return NULL;
    }
    jmethodID constructor = (*env)->GetMethodID(env, cls, "<init>", "(JLjava/lang/String;)V");
    if (constructor == NULL) {
        return NULL;
    }

    jstring jQueue = (*env)->NewStringUTF(env, reply.queue);
    if (jQueue == NULL) {
        return NULL;
    }
    return (*env)->NewObject(env, cls, constructor, jobID, jQueue);
}


jobjectArray _userInfo_internal(JNIEnv *env, char **users, const int usersSize) {
    int numUsers = usersSize;
    struct userInfoEnt *info = lsb_userinfo(users, &numUsers);
    if (info == NULL) {
        if (users == NULL) {
            throwLSFBatchException(env, NULL);
        } else if (lsberrno == LSBE_BAD_USER) {
            throwFormattedLSFBatchException(env, "Unknown user: %s", users[numUsers]);
        } else if (numUsers < usersSize) {
            throwFormattedLSFBatchException(
                env,
                "Number of known users less than number of requested users: %d < %d",
                numUsers, usersSize
            );
        } else {
            throwLSFBatchException(env, NULL);
        }
        return NULL;
    }

    jclass cls = (*env)->FindClass(env, "com/genestack/backend/tasks/lsf/model/UserInfoEntry");
    if (cls == NULL) {
        return NULL;
    }
    jmethodID constructor = (*env)->GetMethodID(env, cls, "<init>", "(Ljava/lang/String;)V");
    if (constructor == NULL) {
        return NULL;
    }

    jobjectArray array = (*env)->NewObjectArray(env, numUsers, cls, NULL);
    int i;
    struct userInfoEnt *ptr = info;
    for (i = 0; i < numUsers; ++i, ++ptr) {
        jstring jUser = (*env)->NewStringUTF(env, ptr->user);
        if (jUser == NULL) {
            return NULL;
        }
        jobject obj = (*env)->NewObject(
            env, cls, constructor,
            jUser
        );
        if (obj == NULL) {
            return NULL;
        }
        (*env)->SetObjectArrayElement(env, array, i, obj);
        if ((*env)->ExceptionCheck(env) == JNI_TRUE) {
            return NULL;
        }
    }
    return array;
}

/*
 * Class:     com_genestack_cluster_lsf_impl_LSFBatch
 * Method:    userInfo
 * Signature: ([Ljava/lang/String;)[Lcom/genestack/backend/tasks/lsf/model/UserInfoEntry;
 */
JNIEXPORT jobjectArray JNICALL Java_com_genestack_cluster_lsf_impl_LSFBatch_userInfo(JNIEnv *env, jobject thiz, jobjectArray usersArray) {
    char **users = NULL;
    int usersSize = 0;
    if (usersArray != NULL &&
            getStringArray(env, usersArray, &users, &usersSize) < 0) {
        return NULL;
    }
    jobjectArray result = _userInfo_internal(env, users, usersSize);
    freeStringArray(users, usersSize);
    return result;
}

/*
 * Class:     com_genestack_cluster_lsf_impl_LSFBatch
 * Method:    currentUserInfo
 * Signature: ()Lcom/genestack/backend/tasks/lsf/model/UserInfoEntry;
 */
JNIEXPORT jobject JNICALL Java_com_genestack_cluster_lsf_impl_LSFBatch_currentUserInfo(JNIEnv *env, jobject thiz) {
    jobjectArray result = _userInfo_internal(env, NULL, 1);
    if (result == NULL) {
        return NULL;
    }
    return (*env)->GetObjectArrayElement(env, result, 0);
}

void _readJobInfo_internal(
    JNIEnv *env, jobject reader, int jobId, char *jobName,
    char *userName, char *queueName, char *hostName, int options
) {
    int totalJobsNumber = lsb_openjobinfo(jobId, jobName, userName, queueName, hostName, options);
    if (totalJobsNumber < 0) {
        if (lsberrno != LSBE_NO_JOB) {
            throwLSFBatchException(env, NULL);
        }
        return;
    } else if (totalJobsNumber == 0) {
        return;
    }

    jclass cls = (*env)->GetObjectClass(env, reader);
    jmethodID readJob = (*env)->GetMethodID(env, cls, "readJob", "(Lcom/genestack/backend/tasks/lsf/model/JobInfoEntry;)Z");
    if (readJob == NULL) {
        return;
    }

    cls = (*env)->FindClass(env, "Lcom/genestack/backend/tasks/lsf/model/JobInfoEntry;");
    jmethodID constructor = (*env)->GetMethodID(env, cls, "<init>", "(JLjava/lang/String;IILjava/lang/String;I)V");


    int more = totalJobsNumber;
    struct jobInfoEnt *job;
    while (more > 0) {
        job = lsb_readjobinfo(&more);
        if (job == NULL) {
            if (lsberrno == LSBE_EOF) {
                lsb_perror(NULL);
            } else {
                throwLSFBatchException(env, NULL);
                // Exit with exception
                // Break instead of return to call lsb_closejobinfo() before exit
            }
            break;
        }

        jobject obj = (*env)->NewObject(
            env, cls, constructor,
            (jlong)job->jobId,
            (*env)->NewStringUTF(env, job->user),
            (jint)job->status,
            (jint)job->exitStatus,
            (*env)->NewStringUTF(env, job->detailReason),
            (jint)job->exitInfo
        );
        if (obj == NULL) {
            break;
        }

        jboolean res = (*env)->CallBooleanMethod(env, reader, readJob, obj);
        if ((*env)->ExceptionCheck(env) == JNI_TRUE) {
            // Exit with exception
            // Break instead of return to call lsb_closejobinfo() before exit
            break;
        }
        if (res == JNI_FALSE) {
            break;
        }
    }

    // Do not call JNI methods after while (true) {...} loop: here can be pending exceptions.
    lsb_closejobinfo();
}

/*
 * Class:     com_genestack_cluster_lsf_impl_LSFBatch
 * Method:    readJobInfo
 * Signature: (Lcom/genestack/backend/tasks/lsf/impl/LSFBatch/JobReader;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
 */
JNIEXPORT void JNICALL Java_com_genestack_cluster_lsf_impl_LSFBatch_readJobInfo(
    JNIEnv *env, jobject thiz, jobject reader, jlong jobId, jstring jobName,
    jstring userName, jstring queueName, jstring hostName, jint options
) {
    if (reader == NULL) {
        throwLSFBatchException(env, "Reader should not be null");
        return;
    }

    char *jobNameStr = getUTFString(env, jobName);
    char *userNameStr = getUTFString(env, userName);
    char *queueNameStr = getUTFString(env, queueName);
    char *hostNameStr = getUTFString(env, hostName);

    _readJobInfo_internal(env, reader, jobId, jobNameStr, userNameStr, queueNameStr, hostNameStr, options);

    freeUTFString(jobNameStr);
    freeUTFString(userNameStr);
    freeUTFString(queueNameStr);
    freeUTFString(hostNameStr);
}
