package org.apache.derby.iapi.services.classfile;

import java.io.IOException;

class AttributeEntry {
   private int attribute_name_index;
   private ClassFormatOutput infoOut;
   byte[] infoIn;

   AttributeEntry(int var1, ClassFormatOutput var2) {
      this.attribute_name_index = var1;
      this.infoOut = var2;
   }

   AttributeEntry(ClassInput var1) throws IOException {
      this.attribute_name_index = var1.getU2();
      this.infoIn = var1.getU1Array(var1.getU4());
   }

   int getNameIndex() {
      return this.attribute_name_index;
   }

   void put(ClassFormatOutput var1) throws IOException {
      var1.putU2(this.attribute_name_index);
      if (this.infoOut != null) {
         var1.putU4(this.infoOut.size());
         this.infoOut.writeTo(var1);
      } else {
         var1.putU4(this.infoIn.length);
         var1.write(this.infoIn);
      }

   }

   int classFileSize() {
      return 6 + (this.infoOut != null ? this.infoOut.size() : this.infoIn.length);
   }
}
