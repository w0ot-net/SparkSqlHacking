package org.apache.derby.iapi.services.classfile;

import java.io.IOException;
import java.util.Vector;

class Attributes extends Vector {
   private int classFileSize;

   Attributes(int var1) {
      super(var1);
   }

   void put(ClassFormatOutput var1) throws IOException {
      int var2 = this.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         ((AttributeEntry)this.elementAt(var3)).put(var1);
      }

   }

   int classFileSize() {
      return this.classFileSize;
   }

   void addEntry(AttributeEntry var1) {
      this.addElement(var1);
      this.classFileSize += var1.classFileSize();
   }
}
