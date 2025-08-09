package org.apache.derby.iapi.services.classfile;

import java.io.IOException;

class CONSTANT_Integer_info extends ConstantPoolEntry {
   private final int value;

   CONSTANT_Integer_info(int var1) {
      super(3);
      this.value = var1;
   }

   public int hashCode() {
      return this.value;
   }

   void put(ClassFormatOutput var1) throws IOException {
      super.put(var1);
      var1.putU4(this.value);
   }

   public boolean equals(Object var1) {
      if (var1 instanceof CONSTANT_Integer_info) {
         return this.value == ((CONSTANT_Integer_info)var1).value;
      } else {
         return false;
      }
   }

   int classFileSize() {
      return 5;
   }
}
