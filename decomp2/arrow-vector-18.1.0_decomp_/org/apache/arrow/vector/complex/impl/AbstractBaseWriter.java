package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.complex.writer.FieldWriter;

abstract class AbstractBaseWriter implements FieldWriter {
   private int index;

   public String toString() {
      String var10000 = super.toString();
      return var10000 + "[index = " + this.index + "]";
   }

   int idx() {
      return this.index;
   }

   public int getPosition() {
      return this.index;
   }

   public void setPosition(int index) {
      this.index = index;
   }

   public void end() {
   }
}
