package org.apache.derby.impl.store.access.btree.index;

import java.io.IOException;
import java.io.ObjectOutput;

public class B2I_v10_2 extends B2I {
   public int getTypeFormatId() {
      return 388;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      super.writeExternal_v10_2(var1);
   }
}
