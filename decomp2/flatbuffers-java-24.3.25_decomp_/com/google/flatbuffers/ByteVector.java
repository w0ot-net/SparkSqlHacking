package com.google.flatbuffers;

import java.nio.ByteBuffer;

public final class ByteVector extends BaseVector {
   public ByteVector __assign(int vector, ByteBuffer bb) {
      this.__reset(vector, 1, bb);
      return this;
   }

   public byte get(int j) {
      return this.bb.get(this.__element(j));
   }

   public int getAsUnsigned(int j) {
      return this.get(j) & 255;
   }
}
