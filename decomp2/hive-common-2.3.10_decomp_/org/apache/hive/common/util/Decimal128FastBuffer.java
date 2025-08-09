package org.apache.hive.common.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Decimal128FastBuffer {
   private final byte[][] sumBytes = new byte[5][];
   private final ByteBuffer[] sumBuffer = new ByteBuffer[5];

   public Decimal128FastBuffer() {
      this.sumBytes[0] = new byte[1];
      this.sumBuffer[0] = ByteBuffer.wrap(this.sumBytes[0]);
      this.sumBytes[1] = new byte[5];
      this.sumBuffer[1] = ByteBuffer.wrap(this.sumBytes[1]);
      this.sumBuffer[1].order(ByteOrder.BIG_ENDIAN);
      this.sumBytes[2] = new byte[9];
      this.sumBuffer[2] = ByteBuffer.wrap(this.sumBytes[2]);
      this.sumBuffer[2].order(ByteOrder.BIG_ENDIAN);
      this.sumBytes[3] = new byte[13];
      this.sumBuffer[3] = ByteBuffer.wrap(this.sumBytes[3]);
      this.sumBuffer[3].order(ByteOrder.BIG_ENDIAN);
      this.sumBytes[4] = new byte[17];
      this.sumBuffer[4] = ByteBuffer.wrap(this.sumBytes[4]);
      this.sumBuffer[4].order(ByteOrder.BIG_ENDIAN);
   }

   public ByteBuffer getByteBuffer(int index) {
      return this.sumBuffer[index];
   }

   public byte[] getBytes(int index) {
      return this.sumBytes[index];
   }
}
