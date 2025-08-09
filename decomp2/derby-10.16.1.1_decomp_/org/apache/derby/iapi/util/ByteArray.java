package org.apache.derby.iapi.util;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public final class ByteArray {
   private byte[] array;
   private int offset;
   private int length;

   public ByteArray(byte[] var1, int var2, int var3) {
      this.array = var1;
      this.offset = var2;
      this.length = var3;
   }

   public ByteArray(byte[] var1) {
      this(var1, 0, var1.length);
   }

   public ByteArray() {
   }

   public void setBytes(byte[] var1) {
      this.array = var1;
      this.offset = 0;
      this.length = var1.length;
   }

   public void setBytes(byte[] var1, int var2) {
      this.array = var1;
      this.offset = 0;
      this.length = var2;
   }

   public void setBytes(byte[] var1, int var2, int var3) {
      this.array = var1;
      this.offset = var2;
      this.length = var3;
   }

   public boolean equals(Object var1) {
      if (var1 instanceof ByteArray var2) {
         return equals(this.array, this.offset, this.length, var2.array, var2.offset, var2.length);
      } else {
         return false;
      }
   }

   public int hashCode() {
      byte[] var1 = this.array;
      int var2 = this.length;

      for(int var3 = 0; var3 < this.length; ++var3) {
         var2 += var1[var3 + this.offset];
      }

      return var2;
   }

   public final byte[] getArray() {
      return this.array;
   }

   public final int getOffset() {
      return this.offset;
   }

   public final int getLength() {
      return this.length;
   }

   public final void setLength(int var1) {
      this.length = var1;
   }

   public void readExternal(ObjectInput var1) throws IOException {
      int var2 = this.length = var1.readInt();
      this.offset = 0;
      this.array = new byte[var2];
      var1.readFully(this.array, 0, var2);
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeInt(this.length);
      var1.write(this.array, this.offset, this.length);
   }

   private static boolean equals(byte[] var0, int var1, int var2, byte[] var3, int var4, int var5) {
      if (var2 != var5) {
         return false;
      } else {
         for(int var6 = 0; var6 < var2; ++var6) {
            if (var0[var6 + var1] != var3[var6 + var4]) {
               return false;
            }
         }

         return true;
      }
   }
}
