package org.apache.derby.iapi.services.io;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class FormatableLongHolder implements Formatable {
   private long theLong;

   public FormatableLongHolder() {
   }

   public FormatableLongHolder(long var1) {
      this.theLong = var1;
   }

   public void setLong(int var1) {
      this.theLong = (long)var1;
   }

   public long getLong() {
      return this.theLong;
   }

   public static FormatableLongHolder[] getFormatableLongHolders(long[] var0) {
      if (var0 == null) {
         return null;
      } else {
         FormatableLongHolder[] var1 = new FormatableLongHolder[var0.length];

         for(int var2 = 0; var2 < var0.length; ++var2) {
            var1[var2] = new FormatableLongHolder(var0[var2]);
         }

         return var1;
      }
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeLong(this.theLong);
   }

   public void readExternal(ObjectInput var1) throws IOException {
      this.theLong = var1.readLong();
   }

   public int getTypeFormatId() {
      return 329;
   }
}
