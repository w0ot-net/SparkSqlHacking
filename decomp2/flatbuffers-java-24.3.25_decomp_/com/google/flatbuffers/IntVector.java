package com.google.flatbuffers;

import java.nio.ByteBuffer;

public final class IntVector extends BaseVector {
   public IntVector __assign(int _vector, ByteBuffer _bb) {
      this.__reset(_vector, 4, _bb);
      return this;
   }

   public int get(int j) {
      return this.bb.getInt(this.__element(j));
   }

   public long getAsUnsigned(int j) {
      return (long)this.get(j) & 4294967295L;
   }
}
