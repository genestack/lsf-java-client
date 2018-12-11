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
