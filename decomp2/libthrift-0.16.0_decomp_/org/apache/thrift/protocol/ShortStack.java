package org.apache.thrift.protocol;

import java.util.Arrays;

class ShortStack {
   private short[] vector;
   private int top = 0;

   public ShortStack(int initialCapacity) {
      this.vector = new short[initialCapacity];
   }

   public short pop() {
      return this.vector[--this.top];
   }

   public void push(short pushed) {
      if (this.vector.length == this.top) {
         this.grow();
      }

      this.vector[this.top++] = pushed;
   }

   private void grow() {
      this.vector = Arrays.copyOf(this.vector, this.vector.length << 1);
   }

   public void clear() {
      this.top = 0;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("<ShortStack vector:[");

      for(int i = 0; i < this.vector.length; ++i) {
         boolean isTop = i == this.top - 1;
         short value = this.vector[i];
         if (i != 0) {
            sb.append(' ');
         }

         if (isTop) {
            sb.append(">>").append(value).append("<<");
         } else {
            sb.append(value);
         }
      }

      sb.append("]>");
      return sb.toString();
   }
}
