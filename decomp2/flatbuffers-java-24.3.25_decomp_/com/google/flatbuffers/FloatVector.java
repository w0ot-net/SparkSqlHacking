package com.google.flatbuffers;

import java.nio.ByteBuffer;

public final class FloatVector extends BaseVector {
   public FloatVector __assign(int _vector, ByteBuffer _bb) {
      this.__reset(_vector, 4, _bb);
      return this;
   }

   public float get(int j) {
      return this.bb.getFloat(this.__element(j));
   }
}
