#
# Copyright (c) 2011-2018 Genestack Limited. All Rights Reserved.
#
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU Lesser General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU Lesser General Public License for more details.
#
# You should have received a copy of the GNU Lesser General Public License
# along with this program.  If not, see <https://www.gnu.org/licenses/>.
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
