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

/**
 * <p>This exception is thrown when some LSF batch library (LSBLIB) call fails.
 * <p>Exception message contains error description returned from {@code lsb_sysmsg}
 * which corresponds to {@code lsberrno}.
 */
public class LSFBatchException extends RuntimeException {
    public LSFBatchException() {
    }

    public LSFBatchException(String message) {
        super(message);
    }
}
