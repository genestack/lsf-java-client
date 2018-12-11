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
