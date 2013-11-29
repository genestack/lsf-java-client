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
