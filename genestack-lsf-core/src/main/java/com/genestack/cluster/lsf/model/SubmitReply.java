/*
 * Copyright (c) 2011-2013 Genestack Limited
 * All Rights Reserved
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF GENESTACK LIMITED
 * The copyright notice above does not evidence any
 * actual or intended publication of such source code.
 */

package com.genestack.cluster.lsf.model;

/**
 * <p>struct submitReply
 * <p>Submit reply structure describes new submitted job.
 */
public class SubmitReply {
    /**
     * <p>Submitted job identifier.
     */
    public final long jobID;

    /**
     * <p>The queue the job was submitted to.
     */
    public final String queue;

    public SubmitReply(long jobID, String queue) {
        this.jobID = jobID;
        this.queue = queue;
    }

    @Override
    public String toString() {
        return String.format("JOB[ID:%d;Q:%s]", jobID, queue);
    }
}
