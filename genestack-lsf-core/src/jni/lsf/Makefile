#
# Copyright (c) 2011-2013 Genestack Limited. All Rights Reserved.
#
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
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
