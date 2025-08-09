package com.google.flatbuffers;

import java.nio.ByteBuffer;

public class BaseVector {
   private int vector;
   private int length;
   private int element_size;
   protected ByteBuffer bb;

   protected int __vector() {
      return this.vector;
   }

   protected int __element(int j) {
      return this.vector + j * this.element_size;
   }

   protected void __reset(int _vector, int _element_size, ByteBuffer _bb) {
      this.bb = _bb;
      if (this.bb != null) {
         this.vector = _vector;
         this.length = this.bb.getInt(_vector - 4);
         this.element_size = _element_size;
      } else {
         this.vector = 0;
         this.length = 0;
         this.element_size = 0;
      }

   }

   public void reset() {
      this.__reset(0, 0, (ByteBuffer)null);
   }

   public int length() {
      return this.length;
   }
}
