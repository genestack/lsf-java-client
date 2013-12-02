# LSF Java client library

This project provides Java API for IBM Platform Load Sharing Facility (LSF).

Since original C API for LSF is not thread-safe, this API provides LSFBatch
 singleton class with required synchronization mechanisms.

## Project Setup

To build Java library you should run:

    mvn package

After that you should build JNI library that makes possible to call C LSF API
from Java code. To do that, you should:

1. change working directory to src/jni/lsf/
2. edit Makefile (set valid INCDIR and LIBDIR)
3. run:
    make

After that you can use result JAR file along with native shared libraries in
your Java applications.
