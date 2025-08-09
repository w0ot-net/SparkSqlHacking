package com.google.flatbuffers;

import java.nio.ByteBuffer;

public class Struct {
   protected int bb_pos;
   protected ByteBuffer bb;

   protected void __reset(int _i, ByteBuffer _bb) {
      this.bb = _bb;
      if (this.bb != null) {
         this.bb_pos = _i;
      } else {
         this.bb_pos = 0;
      }

   }

   public void __reset() {
      this.__reset(0, (ByteBuffer)null);
   }
}
