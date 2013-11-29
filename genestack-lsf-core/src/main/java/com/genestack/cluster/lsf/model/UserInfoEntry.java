/*
 * Copyright (c) 2011-2013 Genestack Limited
 * All Rights Reserved
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF GENESTACK LIMITED
 * The copyright notice above does not evidence any
 * actual or intended publication of such source code.
 */

package com.genestack.cluster.lsf.model;

/**
 * <p>struct userInfoEnt
 * <p>User information entry.
 * <p><b>
 *     FIXME: this structure is not complete!!!
 * </b>
 */
public class UserInfoEntry {
    /**
     * <p>Name of the user or user group
     */
    public String user;
/*
    float     procJobLimit
    int     maxJobs
    int     numStartJobs
    int     numJobs
    int     numPEND
    int     numRUN
    int     numSSUSP
    int     numUSUSP
    int     numRESERVE
    int     maxPendJobs
*/

    /**
     * <p>Default constructor
     */
    public UserInfoEntry() {
    }

    /**
     * <p>Constructor that initializes all fields.
     * @param user    user name
     */
    public UserInfoEntry(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserInfo[" + user + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInfoEntry)) return false;
        final UserInfoEntry that = (UserInfoEntry) o;
        return user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return user.hashCode();
    }
}
