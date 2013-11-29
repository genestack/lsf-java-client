/*
 * Copyright (c) 2011-2013 Genestack Limited
 * All Rights Reserved
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF GENESTACK LIMITED
 * The copyright notice above does not evidence any
 * actual or intended publication of such source code.
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
