/*
 * Copyright (c) 2011-2013 Genestack Limited. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.genestack.cluster.lsf;

/**
 * <p>Defines information options about job.
 * <p>Contains values from {@code defs_lsb_openjobinfo_a} and {@code defs_lsb_openjobinfo}.
 */
public interface LSBOpenJobInfo {
    // -------------------------
    // defs_lsb_openjobinfo_a
    // -------------------------

    /**
     * <p>Information about all jobs, including unfinished jobs (pending,
     * running or suspended) and recently finished jobs.
     * <p>LSF remembers jobs finished within the preceding period. This period
     * is set by the parameter CLEAN_PERIOD in the {@code lsb.params} file.
     * The default is 3600 seconds (1 hour). (See {@code lsb.params}).
     * The command line equivalent is {@code bjobs -a}.
     */
    int ALL_JOB = 0x0001;

    /**
     * <p>Information about recently finished jobs.
     */
    int DONE_JOB = 0x0002;

    /**
     * <p>Information about pending jobs.
     */
    int PEND_JOB = 0x0004;

    /**
     * <p>Information about suspended jobs.
     */
    int SUSP_JOB = 0x0008;

    /**
     * <p>Information about all unfinished jobs.
     */
    int CUR_JOB = 0x0010;

    /**
     * <p>Information about the last submitted job.
     */
    int LAST_JOB = 0x0020;

    // -------------------------
    // defs_lsb_openjobinfo
    // -------------------------

    /**
     * <p>Reserved user name.
     */
    String ALL_USERS = "all";

    /**
     * <p>Information about all running jobs.
     */
    int RUN_JOB = 0x0040;

    /**
     * <p>Information about JobId only.
     */
    int JOBID_ONLY = 0x0080;

    /**
     * <p>Internal use only.
     */
    int HOST_NAME = 0x0100;

    /**
     * <p>Exclude pending jobs.
     */
    int NO_PEND_REASONS = 0x0200;

    /**
     * <p>Return group info structures.
     */
    int JGRP_INFO = 0x0400;

    /**
     * <p>Recursively search job group tree.
     */
    int JGRP_RECURSIVE = 0x0800;

    // TODO: JavaDoc
    int JGRP_ARRAY_INFO = 0x1000;
    int JOBID_ONLY_ALL = 0x02000;
    int ZOMBIE_JOB = 0x04000;
    int TRANSPARENT_MC = 0x08000;
    int EXCEPT_JOB = 0x10000;
    int MUREX_JOB = 0x20000;
    int FORWARDED_JOB = 0x40000;
    int SYM_TOP_LEVEL_ONLY = 0x80000;
    int JGRP_NAME = 0x100000;
    int COND_HOSTNAME = 0x200000;
    int FROM_BJOBSCMD = 0x400000;
    int WITH_LOPTION = 0x800000;
    int APS_JOB = 0x1000000;
    int UGRP_INFO = 0x2000000;
    int TIME_LEFT = 0x4000000;
    int FINISH_TIME = 0x8000000;
    int COM_PERCENTAGE = 0x10000000;
    int SSCHED_JOB = 0x20000000;
    int KILL_JGRP_RECURSIVE = 0x40000000;
}
