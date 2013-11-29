/*
 * Copyright (c) 2011-2013 Genestack Limited
 * All Rights Reserved
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF GENESTACK LIMITED
 * The copyright notice above does not evidence any
 * actual or intended publication of such source code.
 */

package com.genestack.cluster.lsf.model;

/**
 * <p>Contains the constants used to index the {@code rLimits} resource limit
 * array and the corresponding resource limits.
 */
public interface RLimit {
    /**
     * <p>The default value for the resource limits (unlimited).
     */
    int DEFAULT_RLIMIT = -1;

    /** cpu time in milliseconds */
    int LSF_RLIMIT_CPU       = 0;
    /** maximum file size */
    int LSF_RLIMIT_FSIZE     = 1;
    /** data size */
    int LSF_RLIMIT_DATA      = 2;
    /** stack size */
    int LSF_RLIMIT_STACK     = 3;
    /** core file size */
    int LSF_RLIMIT_CORE      = 4;
    /** resident set size */
    int LSF_RLIMIT_RSS       = 5;
    /** open files */
    int LSF_RLIMIT_NOFILE    = 6;
    /** (from HP-UX) */
    int LSF_RLIMIT_OPEN_MAX  = 7;
    /** maximum swap/virtual mem */
    int LSF_RLIMIT_VMEM      = 8;
    int LSF_RLIMIT_SWAP      = LSF_RLIMIT_VMEM;
    /** max wall-clock time limit */
    int LSF_RLIMIT_RUN       = 9;
    /** process number limit */
    int LSF_RLIMIT_PROCESS   = 10;
    /** thread number limit (introduced in LSF6.0) */
    int LSF_RLIMIT_THREAD    = 11;

    /**
     * <p>The number of resource limits.
     */
    int LSF_RLIM_NLIMITS     = 12;
}
