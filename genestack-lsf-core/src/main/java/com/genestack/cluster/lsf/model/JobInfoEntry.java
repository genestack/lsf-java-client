/*
 * Copyright (c) 2011-2013 Genestack Limited
 * All Rights Reserved
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF GENESTACK LIMITED
 * The copyright notice above does not evidence any
 * actual or intended publication of such source code.
 */

package com.genestack.cluster.lsf.model;

/**
 * <p>struct jobInfoEnt
 * <p>Job information entry.
 * <p><b>
 *     FIXME: this structure is not complete!!!
 * </b>
 */
public class JobInfoEntry {
    /**
     * <p>The job ID that the LSF system assigned to the job.
     */
    public long jobID;

    /**
     * <p>The name of the user who submitted the job.
     */
    public String user;

    /**
     * <p>The current status of the job.
     * <p>Bitwise OR of flags from {@link LSBJobStates}.
     */
    public int status;

/*
    // TODO: implement
    int *     reasonTb
    int     numReasons
    int     reasons
    int     subreasons
    int     jobPid
    time_t     submitTime
    time_t     reserveTime
    time_t     startTime
    time_t     predictedStartTime
    time_t     endTime
    time_t     lastEvent
    time_t     nextEvent
    int     duration
    float     cpuTime
    int     umask
    char *     cwd
    char *     subHomeDir
    char *     fromHost
    char **     exHosts
    int     numExHosts
    float     cpuFactor
    int     nIdx
    float *     loadSched
    float *     loadStop
    struct submit     submit
*/

    /**
     * <p>Job exit status.
     */
    public int exitStatus;

/*
    // TODO: implement
    int     execUid
    char *     execHome
    char *     execCwd
    char *     execUsername
    time_t     jRusageUpdateTime
    struct jRusage     runRusage
    int     jType
    char *     parentGroup
    char *     jName
    int     counter [NUM_JGRP_COUNTERS]
    u_short     port
    int     jobPriority
    int     numExternalMsg
    struct jobExternalMsgReply **     externalMsg
    int     clusterId
*/

    /**
     * <p>Detail reason field.
     */
    public String detailReason;

/*
    // TODO: implement
    float     idleFactor
    int     exceptMask
    char *     additionalInfo
*/

    /**
     * <p>Job termination reason.
     * <p>(see {@link LSBExitReason} <b>???</b>)
     */
    public int exitInfo;

/*
    // TODO: implement
    int     warningTimePeriod
    char *     warningAction
    char *     chargedSAAP
    char *     execRusage
    time_t     rsvInActive
    int     numLicense
    char **     licenseNames
    float     aps
    float     adminAps
    int     runTime
    int     reserveCnt
    struct reserveItem *     items
    float     adminFactorVal
    int     resizeMin
    int     resizeMax
    time_t     resizeReqTime
    int     jStartNumExHosts
    char **     jStartExHosts
    time_t     lastResizeTime
    int     numhRusages
    struct hRusage *     hostRusage
    int     maxMem
    int     avgMem
    time_t     fwdTime
    char *     srcCluster
    LS_LONG_INT     srcJobId
    char *     dstCluster
    LS_LONG_INT     dstJobId
    time_t     brunJobTime
    char *     brunJobUser
    char *     appResReq
    char *     qResReq
    char *     combinedResReq
    char *     effectiveResReq
    struct acInfo *     acinfo
    char *     outdir
    int     isInProvisioning
    int     acJobWaitTime
    int     totalProvisionTime
    char *     subcwd
    int     num_network
    struct networkAlloc *     networkAlloc
    int     numhostAffinity
    affinityHostInfo_t *     hostAffinity
*/

    /**
     * <p>Default constructor
     */
    public JobInfoEntry() {
    }

    /**
     * <p>Constructor that initializes all fields.
     */
    // TODO: implement other fields
    public JobInfoEntry(long jobID, String user, int status, int exitStatus, String detailReason, int exitInfo) {
        this.jobID = jobID;
        this.user = user;
        this.status = status;
        this.exitStatus = exitStatus;
        this.detailReason = detailReason;
        this.exitInfo = exitInfo;
    }
}
