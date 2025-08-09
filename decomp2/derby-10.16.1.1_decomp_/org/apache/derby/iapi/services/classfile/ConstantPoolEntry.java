package org.apache.derby.iapi.services.classfile;

import java.io.IOException;

public abstract class ConstantPoolEntry {
   protected int tag;
   protected boolean doubleSlot;
   protected int index;

   protected ConstantPoolEntry(int var1) {
      this.tag = var1;
   }

   int getIndex() {
      return this.index;
   }

   void setIndex(int var1) {
      this.index = var1;
   }

   boolean doubleSlot() {
      return this.doubleSlot;
   }

   Object getKey() {
      return this;
   }

   abstract int classFileSize();

   void put(ClassFormatOutput var1) throws IOException {
      var1.putU1(this.tag);
   }

   final int getTag() {
      return this.tag;
   }

   int getI1() {
      return 0;
   }

   int getI2() {
      return 0;
   }
}
