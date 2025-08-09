package org.apache.thrift.transport;

import java.util.Arrays;

public class AutoExpandingBuffer {
   private byte[] array;

   public AutoExpandingBuffer(int initialCapacity) {
      this.array = new byte[initialCapacity];
   }

   public void resizeIfNecessary(int size) {
      int currentCapacity = this.array.length;
      if (currentCapacity < size) {
         int growCapacity = currentCapacity + (currentCapacity >> 1);
         int newCapacity = Math.max(growCapacity, size);
         this.array = Arrays.copyOf(this.array, newCapacity);
      }

   }

   public byte[] array() {
      return this.array;
   }
}
