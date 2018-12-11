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
