package org.apache.derby.impl.store.raw.data;

final class RawField {
   private final byte[] data;

   RawField(byte[] var1) {
      this.data = var1;
   }

   byte[] getData() {
      return this.data;
   }
}
