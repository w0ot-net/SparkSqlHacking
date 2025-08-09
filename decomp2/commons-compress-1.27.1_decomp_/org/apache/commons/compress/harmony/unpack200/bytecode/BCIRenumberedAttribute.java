package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;

public abstract class BCIRenumberedAttribute extends Attribute {
   protected boolean renumbered;

   public BCIRenumberedAttribute(CPUTF8 attributeName) {
      super(attributeName);
   }

   protected abstract int getLength();

   protected abstract int[] getStartPCs();

   public boolean hasBCIRenumbering() {
      return true;
   }

   public void renumber(List byteCodeOffsets) throws Pack200Exception {
      if (this.renumbered) {
         throw new Error("Trying to renumber a line number table that has already been renumbered");
      } else {
         this.renumbered = true;
         int[] startPCs = this.getStartPCs();
         Arrays.setAll(startPCs, (i) -> (Integer)byteCodeOffsets.get(startPCs[i]));
      }
   }

   public abstract String toString();

   protected abstract void writeBody(DataOutputStream var1) throws IOException;
}
