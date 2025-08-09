package com.google.flatbuffers;

import java.nio.ByteBuffer;

public final class ShortVector extends BaseVector {
   public ShortVector __assign(int _vector, ByteBuffer _bb) {
      this.__reset(_vector, 2, _bb);
      return this;
   }

   public short get(int j) {
      return this.bb.getShort(this.__element(j));
   }

   public int getAsUnsigned(int j) {
      return this.get(j) & '\uffff';
   }
}
