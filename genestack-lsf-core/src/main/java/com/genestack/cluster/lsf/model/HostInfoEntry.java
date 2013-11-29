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
 * <p>struct hostInfoEnt
 * <p>Host information entry.
 * <p><b>
 *     FIXME: this structure is not complete!!!
 * </b>
 */
public class HostInfoEntry {
    public String host;
/*
    int         hStatus
    int *       busySched
    int *       busyStop
    float       cpuFactor
    int         nIdx
    float *     load
    float *     loadSched
    float *     loadStop
    char *      windows
    int         userJobLimit
    int         maxJobs
    int         numJobs
    int         numRUN
    int         numSSUSP
    int         numUSUSP
    int         mig
    int         attr
    float *     realLoad
    int         numRESERVE
    int         chkSig
    float       cnsmrUsage
    float       prvdrUsage
    float       cnsmrAvail
    float       prvdrAvail
    float       maxAvail
    float       maxExitRate
    float       numExitRate
    char *      hCtrlMsg
    int         numNetwork
    struct networkInfoEnt *     networkInfoArray
    char *      affinityCpulist
    lsf_topology_t     hostTp
*/

    /**
     * <p>Default constructor
     */
    public HostInfoEntry() {
    }

    /**
     * <p>Constructor that initializes all fields.
     * @param host    The name of the host
     */
    public HostInfoEntry(String host) {
        this.host = host;
    }

    @Override
    public String toString() {
        return "HostInfo[" + host + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HostInfoEntry)) return false;
        final HostInfoEntry that = (HostInfoEntry) o;
        return host.equals(that.host);
    }

    @Override
    public int hashCode() {
        return host.hashCode();
    }
}
