package org.apache.arrow.memory.util.hash;

import org.apache.arrow.memory.ArrowBuf;

public interface ArrowBufHasher {
   int hashCode(long var1, long var3);

   int hashCode(ArrowBuf var1, long var2, long var4);
}
