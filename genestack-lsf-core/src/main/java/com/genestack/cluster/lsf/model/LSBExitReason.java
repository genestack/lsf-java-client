/*
 * Copyright (c) 2011-2013 Genestack Limited
 * All Rights Reserved
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF GENESTACK LIMITED
 * The copyright notice above does not evidence any
 * actual or intended publication of such source code.
 */

package com.genestack.cluster.lsf.model;

/**
 * <p>Job exit reasons.
 * <p><b>
 * FIXME: This documentation has been copied from header file <code>&lt;lsbatch.h&gt;</code> and seems strange:
 * </b>
 * <p>Currently only to indicate exit due to: <ol>
 *     <li>rerunnable job being restart from last chkpnt;</li>
 *     <li>being killed while execution host is unavailable.</li>
 * </ol>
 */
public interface LSBExitReason {
    /**
     * Job finished normally 
     */
    public int EXIT_NORMAL            = 0x00000000;

    /**
     * Rerunnable job to be restarted 
     */
    public int EXIT_RESTART           = 0x00000001;

    /**
     * Job killed while host unavailable 
     */
    public int EXIT_ZOMBIE            = 0x00000002;

    /**
     * Job is finished and put into pend list 
     */
    public int FINISH_PEND            = 0x00000004;

    /**
     * The job is killed while the execution host is unreach 
     */
    public int EXIT_KILL_ZOMBIE       = 0x00000008;

    /**
     * The job in ZOMBIE is removed 
     */
    public int EXIT_ZOMBIE_JOB        = 0x00000010;

    /**
     * Rerun a job without creating a ZOMBIE job 
     */
    public int EXIT_RERUN             = 0x00000020;

    /**
     * Remote job has no mapping user name here 
     */
    public int EXIT_NO_MAPPING        = 0x00000040;

    /**
     * Remote job has no permission running here 
     */
    public int EXIT_REMOTE_PERMISSION = 0x00000080;

    /**
     * Remote job cannot run locally because of environment problem 
     */
    public int EXIT_INIT_ENVIRON      = 0x00000100;

    /**
     * Remote job failed in pre_exec command 
     */
    public int EXIT_PRE_EXEC          = 0x00000200;

    /**
     * The job is killed and will be later requeued 
     */
    public int EXIT_REQUEUE           = 0x00000400;

    /**
     * Job could not be killed but was removed from system, and the job is put into the zombie job list 
     */
    public int EXIT_REMOVE            = 0x00000800;

    /**
     * Requeue by exit value 
     */
    public int EXIT_VALUE_REQUEUE     = 0x00001000;

    /**
     * Cancel request received from remote cluster. 
     */
    public int EXIT_CANCEL            = 0x00002000;

    /**
     * MED killed job on web server 
     */
    public int EXIT_MED_KILLED        = 0x00004000;

    /**
     * Remote lease job exit on execution side, return to pend on submission 
     */
    public int EXIT_REMOTE_LEASE_JOB  = 0x00008000;

    /**
     * Exit when cwd does not exist
     */
    public int EXIT_CWD_NOTEXIST      = 0x00010000;


    /**
     * Job could not be killed but was removed from system, and the job is not put into the zombie job list
     */
    public int EXIT_REMOVE_NO_ZOMBIE  = 0x00020000;
}
