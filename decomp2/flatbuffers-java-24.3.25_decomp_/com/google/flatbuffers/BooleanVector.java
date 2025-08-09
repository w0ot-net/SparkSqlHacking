package com.google.flatbuffers;

import java.nio.ByteBuffer;

public final class BooleanVector extends BaseVector {
   public BooleanVector __assign(int _vector, ByteBuffer _bb) {
      this.__reset(_vector, 1, _bb);
      return this;
   }

   public boolean get(int j) {
      return 0 != this.bb.get(this.__element(j));
   }
}
