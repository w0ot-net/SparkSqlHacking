package org.apache.avro.mapred.tether;

import java.nio.ByteBuffer;

class TetherData {
   private int count = 1;
   private ByteBuffer buffer;

   public TetherData() {
   }

   public TetherData(ByteBuffer buffer) {
      this.buffer = buffer;
   }

   public int count() {
      return this.count;
   }

   public void count(int count) {
      this.count = count;
   }

   public ByteBuffer buffer() {
      return this.buffer;
   }

   public void buffer(ByteBuffer buffer) {
      this.buffer = buffer;
   }
}
