package org.apache.orc.impl;

public class BufferChunkList {
   private BufferChunk head;
   private BufferChunk tail;

   public void add(BufferChunk value) {
      if (this.head == null) {
         this.head = value;
         this.tail = value;
      } else {
         this.tail.next = value;
         value.prev = this.tail;
         value.next = null;
         this.tail = value;
      }

   }

   public BufferChunk get() {
      return this.head;
   }

   public BufferChunk get(int chunk) {
      BufferChunk ptr = this.head;

      for(int i = 0; i < chunk; ++i) {
         ptr = ptr == null ? null : (BufferChunk)ptr.next;
      }

      return ptr;
   }

   public void clear() {
      this.head = null;
      this.tail = null;
   }
}
