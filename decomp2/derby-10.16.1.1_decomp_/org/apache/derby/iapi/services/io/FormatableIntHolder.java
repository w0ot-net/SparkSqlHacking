package org.apache.derby.iapi.services.io;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class FormatableIntHolder implements Formatable {
   private int theInt;

   public FormatableIntHolder() {
   }

   public FormatableIntHolder(int var1) {
      this.theInt = var1;
   }

   public void setInt(int var1) {
      this.theInt = var1;
   }

   public int getInt() {
      return this.theInt;
   }

   public static FormatableIntHolder[] getFormatableIntHolders(int[] var0) {
      if (var0 == null) {
         return null;
      } else {
         FormatableIntHolder[] var1 = new FormatableIntHolder[var0.length];

         for(int var2 = 0; var2 < var0.length; ++var2) {
            var1[var2] = new FormatableIntHolder(var0[var2]);
         }

         return var1;
      }
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeInt(this.theInt);
   }

   public void readExternal(ObjectInput var1) throws IOException {
      this.theInt = var1.readInt();
   }

   public int getTypeFormatId() {
      return 303;
   }
}
