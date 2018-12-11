# LSF Java client library

This project provides a Java API for LSF, [IBM Platform Computing Load Sharing Facility].

Since the original C API for LSF is not thread-safe, this API provides an `LSFBatch`
singleton class with required synchronization mechanisms.

[IBM Platform Computing Load Sharing Facility]: http://www.ibm.com/systems/technicalcomputing/platformcomputing/products/lsf/

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

Send any questions to [lsf-java-client@genestack.com].

[lsf-java-client@genestack.com]: mailto:lsf-java-client@genestack.com

## License

Copyright (c) 2011-2018 Genestack Limited. All Rights Reserved.

This program is licensed under the terms of the [GNU Lesser General Public License],
also included in our repository in the COPYING file.

[GNU Lesser General Public License]: https://www.gnu.org/licenses/lgpl-3.0.en.html
