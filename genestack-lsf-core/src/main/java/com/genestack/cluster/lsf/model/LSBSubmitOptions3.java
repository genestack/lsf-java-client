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
 * <p>Defines flags used for {@link SubmitRequest#options3}.
 */
public interface LSBSubmitOptions3 {
    // TODO: JavaDoc
    int SUB3_APP = 0x01;
    int SUB3_APP_RERUNNABLE = 0x02;
    int SUB3_ABSOLUTE_PRIORITY = 0x04;
    int SUB3_DEFAULT_JOBGROUP = 0x08;
    int SUB3_POST_EXEC = 0x10;
    int SUB3_USER_SHELL_LIMITS = 0x20;
    int SUB3_CWD = 0x40;
    int SUB3_RUNTIME_ESTIMATION = 0x80;
    int SUB3_NOT_RERUNNABLE = 0x100;
    int SUB3_JOB_REQUEUE = 0x200;
    int SUB3_INIT_CHKPNT_PERIOD = 0x400;
    int SUB3_MIG_THRESHOLD = 0x800;
    int SUB3_APP_CHKPNT_DIR = 0x1000;
    int SUB3_BSUB_CHK_RESREQ = 0x2000;
    int SUB3_RUNTIME_ESTIMATION_ACC = 0x4000;
    int SUB3_RUNTIME_ESTIMATION_PERC = 0x8000;
    int SUB3_INTERACTIVE_SSH = 0x10000;
    int SUB3_XJOB_SSH = 0x20000;
    int SUB3_AUTO_RESIZE = 0x40000;
    int SUB3_RESIZE_NOTIFY_CMD = 0x80000;
    int SUB3_BULK_SUBMIT = 0x100000;
    int SUB3_INTERACTIVE_TTY = 0x200000;
    int SUB3_FLOATING_CLIENT = 0x400000;
    int SUB3_XFJOB = 0x800000;
    int SUB3_XFJOB_EXCLUSIVE = 0x1000000;
    int SUB3_JOB_DESCRIPTION = 0x2000000;
    int SUB3_SIMULATION = 0x4000000;
    int SUB3_BRUN_FORWARD = 0x8000000;
    int SUB3_CREATE_CWD_DIRECTORY = 0x40000000;
    int SUB3_BMOD_FROM_LS = 0x80000000;
}
