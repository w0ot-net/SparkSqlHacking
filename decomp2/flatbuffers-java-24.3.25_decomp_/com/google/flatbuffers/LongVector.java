package com.google.flatbuffers;

import java.nio.ByteBuffer;

public final class LongVector extends BaseVector {
   public LongVector __assign(int _vector, ByteBuffer _bb) {
      this.__reset(_vector, 8, _bb);
      return this;
   }

   public long get(int j) {
      return this.bb.getLong(this.__element(j));
   }
}
