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
 * <p>Defines flags used for {@link JobInfoEntry#status}.
 * <p>Job terminal states are: <ul>
 *     <li>{@code JOB_STAT_EXIT}</li>
 *     <li>{@code JOB_STAT_DONE | JOB_STAT_PDONE}</li>
 *     <li>{@code JOB_STAT_DONE | JOB_STAT_PERR}</li>
 * </ul>
 */
public interface LSBJobStates {
    /**
     * <p>Null state.
     */
    int JOB_STAT_NULL = 0x00;

    /**
     * <p>The job is pending, i.e., it has not been dispatched yet.
     */
    int JOB_STAT_PEND = 0x01;

    /**
     * <p>The pending job was suspended by its owner or the LSF system
     * administrator.
     */
    int JOB_STAT_PSUSP = 0x02;

    /**
     * <p>The job is running.
     */
    int JOB_STAT_RUN = 0x04;

    /**
     * <p>The running job was suspended by the system because an execution
     * host was overloaded or the queue run window closed.
     * <p>(see lsb_queueinfo, lsb_hostinfo, and lsb.queues.)
     */
    int JOB_STAT_SSUSP = 0x08;

    /**
     * <p>The running job was suspended by its owner or the LSF system
     * administrator.
     */
    int JOB_STAT_USUSP = 0x10;

    /**
     * <p>The job has terminated with a non-zero status - it may have been
     * aborted due to an error in its execution, or killed by its owner
     * or by the LSF system administrator.
     */
    int JOB_STAT_EXIT = 0x20;

    /**
     * <p>The job has terminated with status 0.
     */
    int JOB_STAT_DONE = 0x40;

    /**
     * <p>Post job process done successfully.
     */
    int JOB_STAT_PDONE = 0x80;

    /**
     * <p>Post job process has error.
     */
    int JOB_STAT_PERR = 0x100;

    /**
     * <p>Chunk job waiting its turn to exec.
     */
    int JOB_STAT_WAIT = 0x200;

    /**
     * <p>The slave batch daemon (sbatchd) on the host on which the job is
     * processed has lost contact with the master batch daemon (mbatchd).
     */
    int JOB_STAT_UNKWN = 0x10000;
}
