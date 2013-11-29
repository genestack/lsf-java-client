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
