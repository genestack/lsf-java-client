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

import com.genestack.cluster.lsf.model.*;

/**
 * <p>Base class provides Java interface to some LSF batch library (LSBLIB) calls.
 * <p>All methods (and constructors) throw {@link LSFBatchException} on failure.
 */
public class LSFBatch {
    /**
     * <p>Creates {@code LSFBatch} instance and initializes the LSF batch
     * library (LSBLIB).
     * <p>If {@code appName} holds the name of your application, a logfile with
     * the same name as your application receives LSBLIB transaction information.
     * If {@code appName} is {@code null}, the logfile {@code $LSF_LOGDIR/bcmd}
     * receives LSBLIB transaction information.
     * @param appName    The name of your application
     */
    public LSFBatch(String appName) {
        System.loadLibrary("lsb4j");
        init(appName);
    }

    private native void init(String appName);

    /**
     * <h5>lsb_hostinfo</h5>
     * <p>Returns information about job server hosts.
     * <p>To get information on all hosts, set hosts to {@code null}.
     * @param hosts    An array of host or cluster names
     * @return an array with host information
     */
    public synchronized native HostInfoEntry[] hostInfo(String[] hosts);

    /**
     * <h5>lsb_hostinfo for localhost</h5>
     * <p>Returns information about localhost.
     * <p>This information is obtained by calling LSBLIB {@code lsb_hostinfo}
     * with {@code *numHosts} set to {@code 1} and {@code hosts} set to {@code NULL}.
     * @return information about localhost.
     */
    public synchronized native HostInfoEntry localHostInfo();

    /**
     * <h5>lsb_submit</h5>
     * <p>Submits or restarts a job in the batch system according to the
     * {@link SubmitRequest} specification.
     * <p>If a job can not meet the specified {@code request} requirements then
     * it is not submitted to the batch system and an {@link LSFBatchException} is thrown.
     * @param request    requirements for job submission to the batch system.
     * @return {@link SubmitReply} object
     */
    public synchronized native SubmitReply submit(SubmitRequest request);

    /**
     * <h5>lsb_userinfo</h5>
     * <p>Returns information about LSF users and user groups.
     * <p>Returns the maximum number of job slots that a user can use simultaneously
     * on any host and in the whole local LSF cluster, as well as the current number
     * of job slots used by running and suspended jobs or reserved for pending jobs.
     * The maximum numbers of job slots are defined in the LSF configuration file {@code lsb.users}.
     * The reserved user name {@code default}, defined in the {@code lsb.users} configuration file,
     * matches users not listed in the {@code lsb.users} file who have no jobs started in the system.
     * @return array with users and user groups information
     */
    public synchronized native UserInfoEntry[] userInfo(String[] users);

    /**
     * <h5>lsb_userinfo</h5>
     * <p>Returns information about current LSF user.
     * <p>Returns the maximum number of job slots that a user can use simultaneously
     * on any host and in the whole local LSF cluster, as well as the current number
     * of job slots used by running and suspended jobs or reserved for pending jobs.
     * The maximum numbers of job slots are defined in the LSF configuration file {@code lsb.users}.
     * The reserved user name {@code default}, defined in the {@code lsb.users} configuration file,
     * matches users not listed in the {@code lsb.users} file who have no jobs started in the system.
     * @return information about current user
     */
    public synchronized native UserInfoEntry currentUserInfo();

    /**
     * <p>Interface to read information about LSF jobs.
     */
    public interface JobReader {
        /**
         * <p>Handles information about LSF job.
         * @param job    LSF job information entry
         * @return {@code true} to continue reading, {@code false} to stop reading.
         */
        boolean readJob(JobInfoEntry job);
    }

    /**
     * <p>Reads information about LSF jobs.
     * <p>Reads information about all LSF jobs that satisfy the specified parameters.
     * @param reader       reader object to handle information about LSF jobs
     * @param jobId        count jobs with the given job ID (pass {@code 0} to count all jobs)
     * @param jobName      count jobs with the given job name (pass {@code null} to count all jobs)
     * @param userName     count jobs submitted by the named user or user group (pass {@code "all"} to count all users, {@code null} for current user)
     * @param queueName    count jobs belonging to the named queue (pass {@code null} to count all queues)
     * @param hostName     count jobs on the named host, host group or cluster name (pass {@code null} to count all hosts)
     * @param options      bitwise OR of flags from {@link LSBOpenJobInfo}
     */
    public synchronized native void readJobInfo(
        JobReader reader,
        long jobId,
        String jobName,
        String userName,
        String queueName,
        String hostName,
        int options
    );

    /**
     * <p>Reads information about LSF jobs, that were submitted by the current
     * user, that satisfy the specified {@code options}.
     * @param reader     reader object to handle information about LSF jobs
     * @param options    bitwise OR of flags from {@link LSBOpenJobInfo}
     */
    public synchronized void readJobInfo(JobReader reader, int options) {
        readJobInfo(reader, 0, null, null, null, null, options);
    }
}
