/*
 * Copyright (c) 2011-2018 Genestack Limited. All Rights Reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.txt>.
 */

package com.genestack.cluster.lsf.model;

/**
 * <p>struct submit
 * <p>Submit request structure contains requirements for job submission to the batch system.
 * <p><b>
 *     FIXME: this structure is not complete!!!
 * </b>
 */
public class SubmitRequest {
    /**
     * <p>{@link LSBSubmitOptions} defines the flags constructed from bits.
     * <p>These flags correspond to some of the options of the {@code bsub}
     * command line. Use the bitwise OR to set more than one flag.
     */
    public int options;

    /**
     * <p>Bitwise OR of some of the flags from {@link LSBSubmitOptions2}.
     */
    public int options2;

    /**
     * <p>The job name.
     * <p>If {@code jobName} is {@code null}, {@link #command} is used as the job name.
     */
    public String jobName;

    /**
     * <p>Submit the job to this queue.
     * <p>If {@code queue} is {@code null}, submit the job to a system default queue.
     */
    public String queue;

    /**
     * <p>Names of invoker specified candidate hosts for running the job.
     * <p>If {@code askedHosts} is {@code null}, all qualified hosts will be considered.
     */
    public String[] askedHosts;

    /**
     * <p>The resource requirements of the job.
     * <p>If {@code resReq} is {@code null}, the batch system will try to obtain
     * resource requirements for command from the remote task lists (see ls_task).
     * If the task does not appear in the remote task lists, then the default
     * resource requirement is to run on host of the same type.
     */
    public String resReq;

    /**
     * <p>Limits on the consumption of system resources by all processes belonging to this job.
     * <p>See getrlimit() for details.
     * If an element of the array is {@code -1}, there is no limit for that resource.
     * For the constants used to index the array, see {@link RLimit}.
     */
    public final int[] rLimits = new int[RLimit.LSF_RLIM_NLIMITS];
    {
        for (int i = 0; i < RLimit.LSF_RLIM_NLIMITS; ++i) {
            rLimits[i] = RLimit.DEFAULT_RLIMIT;
        }
    }

    /**
     * <p>A host name or host model name to use for scaling
     * {@code rLimits[LSF_RLIMIT_CPU]} and {@code rLimits[LSF_RLIMIT_RUN]}.
     * <p>(See {@code lsb_queueinfo}). If hostSpec is NULL, the local host is assumed.
     */
    public String hostSpec;

    /**
     * <p>The initial number of processors needed by a (parallel) job.
     * <p>The default is {@code 1}.
     */
    public int numProcessors = 1;

    /**
     * <p>The job dependency condition.
     */
    public String dependCond;

    /**
     * <p>Time event string for scheduled repetitive jobs.
     */
    public String timeEvent;

    /**
     * <p>Dispatch the job on or after {@code beginTime}, which is the number of
     * seconds since the Epoch.
     * <p>If {@code beginTime} is {@code 0}, start the job as soon as possible.
     */
    public long beginTime;

    /**
     * <p>Job termination deadline.
     * <p>If the job is still running at {@code termTime}, it will be sent a {@code USR2} signal.
     * If the job does not terminate within 10 minutes after being sent this signal, it will be ended.
     * <p>{@code termTime} has the same representation as {@link #beginTime}.
     * If {@code termTime} is {@code 0}, allow the job to run until it reaches a resource limit.
     */
    public long termTime;

/*
    TODO: implement??? sigValue variable is obsolete.
    int     sigValue
*/

    /**
     * <p>The path name of the job's standard input file.
     * <p>If {@code inFile} is {@code null}, use {@code /dev/null} as the default.
     */
    public String inFile;

    /**
     * <p>The path name of the job's standard output file.
     * <p>If {@code outFile} is {@code null}, the job's output will be mailed to the submitter.
     */
    public String outFile;

    /**
     * <p>The path name of the job's standard error output file.
     * <p>If errFile is {@code null}, the standard error output will be merged with the standard output of the job.
     */
    public String errFile;

    /**
     * <p>When submitting a job, the command line of the job.
     * <p>When modifying a job, a mandatory parameter that should be set to {@code jobId} in string format.
     */
    public String command;

    /**
     * <p>New command line for bmod.
     */
    public String newCommand;

/*
    // TODO: implement
    time_t     chkpntPeriod
    char *     chkpntDir
    int     nxf
    struct xFile *     xf
*/

    /**
     * <p>The job pre-execution command.
     */
    public String preExecCmd;

    /**
     * <p>The user that results are mailed to.
     */
    public String mailUser;

/*
    // TODO: implement
    int     delOptions
    int     delOptions2
*/

    /**
     * <p>The name of the project the job will be charged to.
     */
    public String projectName;

    /**
     * <p>Maximum number of processors required to run the job.
     * <p>Default is {@code 1}.
     */
    public int maxNumProcessors = 1;

    /**
     * <p>Specified login shell used to initialize the execution environment
     * for the job (see the {@code -L} option of {@code bsub}).
     */
    public String loginShell;

    /**
     * <p>The name of the LSF user group (see lsb.users) to which the job will
     * belong (see the {@code -G} option of {@code bsub}).
     */
    public String userGroup;

/*
    // TODO: implement
    char *     exceptList
    int     userPriority
    char *     rsvId
    char *     jobGroup
*/

    /**
     * FIXME: JavaDoc
     */
    public String sla;

/*
    // TODO: implement
    char *     extsched
    int     warningTimePeriod
    char *     warningAction
    char *     licenseProject
*/

    /**
     * <p>Bitwise OR of some of the flags from {@link LSBSubmitOptions3}.
     */
    public int options3;

/*
    // TODO: implement
    int     delOptions3
    char *     app
    int     jsdlFlag
    char *     jsdlDoc
    void *     correlator
    char *     apsString
*/

    /**
     * <p>Post-execution commands specified by {@code -Ep} option of {@code bsub} and {@code bmod}.
     */
    public String postExecCmd;

    /**
     * <p>Current working directory specified by {@code -cwd} option of {@code bsub} and {@code bmod}.
     */
    public String cwd;

/*
    // TODO: implement
    int     runtimeEstimation
    char *     requeueEValues
    int     initChkpntPeriod
    int     migThreshold
    char *     notifyCmd
*/

    /**
     * <p>Job description.
     */
    public String jobDescription;

/*
    // TODO: implement
    struct submit_ext *     submitExt
    int     options4
    int     delOptions4
    int     numAskedClusters
    char **     askedClusters
    char *     outdir
    char *     dcTmpls
    char *     dcVmActions
    char *     networkReq
*/
}
