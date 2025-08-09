package com.google.flatbuffers;

import java.nio.ByteBuffer;

public final class UnionVector extends BaseVector {
   public UnionVector __assign(int _vector, int _element_size, ByteBuffer _bb) {
      this.__reset(_vector, _element_size, _bb);
      return this;
   }

   public Table get(Table obj, int j) {
      return Table.__union(obj, this.__element(j), this.bb);
   }
}
