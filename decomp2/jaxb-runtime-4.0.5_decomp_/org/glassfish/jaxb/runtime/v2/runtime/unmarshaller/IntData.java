package org.glassfish.jaxb.runtime.v2.runtime.unmarshaller;

import java.io.IOException;
import org.glassfish.jaxb.runtime.v2.runtime.output.Pcdata;
import org.glassfish.jaxb.runtime.v2.runtime.output.UTF8XmlOutput;

public class IntData extends Pcdata {
   private int data;
   private int length;
   private static final int[] sizeTable = new int[]{9, 99, 999, 9999, 20229, 999999, 9999999, 99999999, 202299999, Integer.MAX_VALUE};

   public void reset(int i) {
      this.data = i;
      if (i == Integer.MIN_VALUE) {
         this.length = 11;
      } else {
         this.length = i < 0 ? stringSizeOfInt(-i) + 1 : stringSizeOfInt(i);
      }

   }

   private static int stringSizeOfInt(int x) {
      int i;
      for(i = 0; x > sizeTable[i]; ++i) {
      }

      return i + 1;
   }

   public String toString() {
      return String.valueOf(this.data);
   }

   public int length() {
      return this.length;
   }

   public char charAt(int index) {
      return this.toString().charAt(index);
   }

   public CharSequence subSequence(int start, int end) {
      return this.toString().substring(start, end);
   }

   public void writeTo(UTF8XmlOutput output) throws IOException {
      output.text(this.data);
   }
}
