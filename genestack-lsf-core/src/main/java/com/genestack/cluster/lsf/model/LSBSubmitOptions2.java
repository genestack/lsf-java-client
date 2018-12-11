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
 * <p>Defines flags used for {@link com.genestack.cluster.lsf.model.SubmitRequest#options2}.
 */
public interface LSBSubmitOptions2 {
    /**
     * <p>Hold the job after it is submitted.
     * <p>The job will be in {@code PSUSP} status. Equivalent to {@code bsub -H} command line option.
     */
    int SUB2_HOLD = 0x01;

    // TODO: JavaDoc
    int SUB2_MODIFY_CMD = 0x02;
    int SUB2_BSUB_BLOCK = 0x04;
    int SUB2_HOST_NT = 0x08;
    int SUB2_HOST_UX = 0x10;
    int SUB2_QUEUE_CHKPNT = 0x20;
    int SUB2_QUEUE_RERUNNABLE = 0x40;
    int SUB2_IN_FILE_SPOOL = 0x80;
    int SUB2_JOB_CMD_SPOOL = 0x100;
    int SUB2_JOB_PRIORITY = 0x200;
    int SUB2_USE_DEF_PROCLIMIT = 0x400;
    int SUB2_MODIFY_RUN_JOB = 0x800;
    int SUB2_MODIFY_PEND_JOB = 0x1000;
    int SUB2_WARNING_TIME_PERIOD = 0x2000;
    int SUB2_WARNING_ACTION = 0x4000;
    int SUB2_USE_RSV = 0x8000;
    int SUB2_TSJOB = 0x10000;
    int SUB2_LSF2TP = 0x20000;
    int SUB2_JOB_GROUP = 0x40000;
    int SUB2_SLA = 0x80000;
    int SUB2_EXTSCHED = 0x100000;
    int SUB2_LICENSE_PROJECT = 0x200000;
    int SUB2_OVERWRITE_OUT_FILE = 0x400000;
    int SUB2_OVERWRITE_ERR_FILE = 0x800000;
    int SUB2_SSM_JOB = 0x1000000;
    int SUB2_SYM_JOB = 0x2000000;
    int SUB2_SRV_JOB = 0x4000000;
    int SUB2_SYM_GRP = 0x8000000;
    int SUB2_SYM_JOB_PARENT = 0x10000000;
    int SUB2_SYM_JOB_REALTIME = 0x20000000;
    int SUB2_SYM_JOB_PERSIST_SRV = 0x40000000;
    int SUB2_SSM_JOB_PERSIST = 0x80000000;
}
