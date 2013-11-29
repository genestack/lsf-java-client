/*
 * Copyright (c) 2011-2013 Genestack Limited
 * All Rights Reserved
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF GENESTACK LIMITED
 * The copyright notice above does not evidence any
 * actual or intended publication of such source code.
 */

package com.genestack.cluster.lsf;

/**
 * <p>This exception is thrown when some LSF batch library (LSBLIB) call fails.
 * <p>Exception message contains error description returned from {@code lsb_sysmsg}
 * which corresponds to {@code lsberrno}.
 */
public class LSFBatchException extends RuntimeException {
    public LSFBatchException() {
    }

    public LSFBatchException(String message) {
        super(message);
    }
}
