# LSF Java client library

This project provides a Java API for LSF, [IBM Platform Computing Load Sharing Facility]( http://www.ibm.com/systems/technicalcomputing/platformcomputing/products/lsf/).

Since the original C API for LSF is not thread-safe, this API provides an `LSFBatch`
 singleton class with required synchronization mechanisms.

## Project Setup

To build the Java library you should run:

    mvn package

After that you should build the JNI library that makes it possible to call LSF C API
from Java code. To do that, you should:

1. change working directory to src/jni/lsf/
2. edit Makefile (set valid INCDIR and LIBDIR)
3. run make

After that you can use the resulting JAR file along with native shared libraries in
your Java applications.

## Contacts

This code was originally written by Vasiliy Bout. Please send any questions to lsf-java-client@genestack.com.

## LICENSE

This work is copyright (c) 2011-2013 Genestack Limited. All Rights Reserved.

All rights reserved. This program and the accompanying materials
are made available under the terms of the Eclipse Public License v1.0
which accompanies this distribution, and is available at
http://www.eclipse.org/legal/epl-v10.html

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
