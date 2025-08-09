package org.apache.derby.iapi.services.classfile;

import java.io.IOException;

public final class CONSTANT_Utf8_info extends ConstantPoolEntry {
   private final String value;
   private int asString;
   private int asCode;

   CONSTANT_Utf8_info(String var1) {
      super(1);
      this.value = var1;
   }

   Object getKey() {
      return this.value;
   }

   int classFileSize() {
      return 3 + this.value.length();
   }

   public String toString() {
      return this.value;
   }

   int setAsCode() {
      if (ClassHolder.isExternalClassName(this.value)) {
         if (this.asString == 0) {
            this.asCode = this.getIndex();
         }

         return this.asCode;
      } else {
         return this.getIndex();
      }
   }

   int setAsString() {
      if (ClassHolder.isExternalClassName(this.value)) {
         if (this.asCode == 0) {
            this.asString = this.getIndex();
         }

         return this.asString;
      } else {
         return this.getIndex();
      }
   }

   void setAlternative(int var1) {
      if (this.asCode == 0) {
         this.asCode = var1;
      } else {
         this.asString = var1;
      }

   }

   void put(ClassFormatOutput var1) throws IOException {
      super.put(var1);
      if (this.getIndex() == this.asCode) {
         var1.writeUTF(ClassHolder.convertToInternalClassName(this.value));
      } else {
         var1.writeUTF(this.value);
      }

   }
}
