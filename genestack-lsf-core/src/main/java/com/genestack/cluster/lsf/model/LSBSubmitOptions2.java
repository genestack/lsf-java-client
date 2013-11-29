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
