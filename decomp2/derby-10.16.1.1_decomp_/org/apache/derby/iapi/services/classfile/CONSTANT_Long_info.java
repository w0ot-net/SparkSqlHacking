package org.apache.derby.iapi.services.classfile;

import java.io.IOException;

final class CONSTANT_Long_info extends ConstantPoolEntry {
   private final long value;

   CONSTANT_Long_info(long var1) {
      super(5);
      this.doubleSlot = true;
      this.value = var1;
   }

   public int hashCode() {
      return (int)this.value;
   }

   public boolean equals(Object var1) {
      if (var1 instanceof CONSTANT_Long_info) {
         return this.value == ((CONSTANT_Long_info)var1).value;
      } else {
         return false;
      }
   }

   int classFileSize() {
      return 9;
   }

   void put(ClassFormatOutput var1) throws IOException {
      super.put(var1);
      var1.writeLong(this.value);
   }
}
