package org.apache.derby.iapi.services.classfile;

import java.io.IOException;

final class CONSTANT_Float_info extends ConstantPoolEntry {
   private final float value;

   CONSTANT_Float_info(float var1) {
      super(4);
      this.value = var1;
   }

   public int hashCode() {
      return (int)this.value;
   }

   public boolean equals(Object var1) {
      if (var1 instanceof CONSTANT_Float_info) {
         return this.value == ((CONSTANT_Float_info)var1).value;
      } else {
         return false;
      }
   }

   int classFileSize() {
      return 5;
   }

   void put(ClassFormatOutput var1) throws IOException {
      super.put(var1);
      var1.writeFloat(this.value);
   }
}
