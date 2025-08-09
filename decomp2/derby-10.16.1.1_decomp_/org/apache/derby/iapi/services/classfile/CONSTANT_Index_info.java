package org.apache.derby.iapi.services.classfile;

import java.io.IOException;

public final class CONSTANT_Index_info extends ConstantPoolEntry {
   private int i1;
   private int i2;

   CONSTANT_Index_info(int var1, int var2, int var3) {
      super(var1);
      this.i1 = var2;
      this.i2 = var3;
   }

   public int hashCode() {
      return this.tag << 16 | this.i1 << 8 ^ this.i2;
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof CONSTANT_Index_info var2)) {
         return false;
      } else {
         return this.tag == var2.tag && this.i1 == var2.i1 && this.i2 == var2.i2;
      }
   }

   void set(int var1, int var2, int var3) {
      this.tag = var1;
      this.i1 = var2;
      this.i2 = var3;
   }

   int classFileSize() {
      return 3 + (this.i2 != 0 ? 2 : 0);
   }

   void put(ClassFormatOutput var1) throws IOException {
      super.put(var1);
      var1.putU2(this.i1);
      if (this.i2 != 0) {
         var1.putU2(this.i2);
      }

   }

   public int getI1() {
      return this.i1;
   }

   public int getI2() {
      return this.i2;
   }
}
