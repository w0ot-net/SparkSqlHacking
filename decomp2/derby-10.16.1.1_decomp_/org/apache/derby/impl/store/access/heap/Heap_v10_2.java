package org.apache.derby.impl.store.access.heap;

import java.io.IOException;
import java.io.ObjectOutput;

public class Heap_v10_2 extends Heap {
   public int getTypeFormatId() {
      return 91;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      super.writeExternal_v10_2(var1);
   }
}
