#
# Copyright (c) 2011-2013 Genestack Limited
# All Rights Reserved
# THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF GENESTACK LIMITED
# The copyright notice above does not evidence any
# actual or intended publication of such source code.
#

INCDIR = /u/lsfadm1/lsf/9.1/include
LIBDIR = /u/lsfadm1/lsf/9.1/linux2.6-glibc2.3-x86_64/lib

CFLAGS = -I$(INCDIR) -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux 

LSFBATCHLIB = $(LIBDIR)/libbat.so $(LIBDIR)/liblsf.so

LIB = $(LSFBATCHLIB) -lm -lnsl -ldl

LIBNAME=liblsb4j.so
OBJECTS=$(patsubst %.c,%.o,$(wildcard *.c))

.PHONY: clean all

all: $(LIBNAME)

%.o: %.c
	$(CC) -g -fpic -c $(CFLAGS) $< -o $@

%.o: %.cpp
	$(CXX) -g -fpic -c $(CFLAGS) $< -o $@


$(LIBNAME) : $(OBJECTS)
	$(CC) -g -shared -o $@ $+ $(LIB)
#	$(CC) -g -shared -o $@ $+ -Wl,--whole-archive $(LIBDIR)/liblsf.so $(LIBDIR)/libbat.so -Wl,--no-whole-archive

clean:
	-rm -f *.o
	-rm -f $(LIBNAME) *.so
