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

package com.genestack.cluster.lsf.model;

/**
 * <p>Defines flags used for {@link SubmitRequest#options}.
 */
public interface LSBSubmitOptions {
    /**
     * <p>Flag to indicate jobName parameter has data.
     * <p>Equivalent to {@code bsub -J} command line option existence.
     */
    int SUB_JOB_NAME = 0x01;

    /**
     * <p>Flag to indicate queue parameter has data.
     * <p>Equivalent to {@code bsub -q} command line option existence.
     */
    int SUB_QUEUE = 0x02;

    /**
     * <p>Flag to indicate numAskedHosts parameter has data.
     * <p>Equivalent to {@code bsub -m} command line option existence.
     */
    int SUB_HOST = 0x04;

    /**
     * <p>Flag to indicate inFile parameter has data.
     * <p>Equivalent to {@code bsub -i} command line option existence.
     */
    int SUB_IN_FILE = 0x08;

    /**
     * <p>Flag to indicate outFile parameter has data.
     * <p>Equivalent to {@code bsub -o} command line option existence.
     */
    int SUB_OUT_FILE = 0x10;

    /**
     * <p>Flag to indicate errFile parameter has data.
     * <p>Equivalent to {@code bsub -e} command line option existence.
     */
    int SUB_ERR_FILE = 0x20;

    // TODO: JavaDoc
    int SUB_EXCLUSIVE = 0x40;
    int SUB_NOTIFY_END = 0x80;
    int SUB_NOTIFY_BEGIN = 0x100;
    int SUB_USER_GROUP = 0x200;
    int SUB_CHKPNT_PERIOD = 0x400;
    int SUB_CHKPNT_DIR = 0x800;
    int SUB_CHKPNTABLE = SUB_CHKPNT_DIR;
    int SUB_RESTART_FORCE = 0x1000;
    int SUB_RESTART = 0x2000;
    int SUB_RERUNNABLE = 0x4000;
    int SUB_WINDOW_SIG = 0x8000;
    int SUB_HOST_SPEC = 0x10000;
    int SUB_DEPEND_COND = 0x20000;
    int SUB_RES_REQ = 0x40000;
    int SUB_OTHER_FILES = 0x80000;
    int SUB_PRE_EXEC = 0x100000;
    int SUB_LOGIN_SHELL = 0x200000;
    int SUB_MAIL_USER = 0x400000;
    int SUB_MODIFY = 0x800000;
    int SUB_MODIFY_ONCE = 0x1000000;
    int SUB_PROJECT_NAME = 0x2000000;
    int SUB_INTERACTIVE = 0x4000000;
    int SUB_PTY = 0x8000000;
    int SUB_PTY_SHELL = 0x10000000;
    int SUB_EXCEPT = 0x20000000;
    int SUB_TIME_EVENT = 0x40000000;
}
