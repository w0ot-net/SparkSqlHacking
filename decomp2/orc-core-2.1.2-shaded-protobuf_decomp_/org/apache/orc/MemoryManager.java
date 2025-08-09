package org.apache.orc;

import java.io.IOException;
import org.apache.hadoop.fs.Path;

public interface MemoryManager {
   void addWriter(Path var1, long var2, Callback var4) throws IOException;

   void removeWriter(Path var1) throws IOException;

   /** @deprecated */
   void addedRow(int var1) throws IOException;

   default long checkMemory(long previousAllocation, Callback writer) throws IOException {
      this.addedRow(1024);
      return previousAllocation;
   }

   public interface Callback {
      boolean checkMemory(double var1) throws IOException;
   }
}
