package org.apache.orc.impl;

import java.io.IOException;
import java.util.function.Consumer;

public class BitFieldWriter {
   private RunLengthByteWriter output;
   private final int bitSize;
   private byte current = 0;
   private int bitsLeft = 8;

   public BitFieldWriter(PositionedOutputStream output, int bitSize) throws IOException {
      this.output = new RunLengthByteWriter(output);
      this.bitSize = bitSize;
   }

   private void writeByte() throws IOException {
      this.output.write(this.current);
      this.current = 0;
      this.bitsLeft = 8;
   }

   public void flush() throws IOException {
      if (this.bitsLeft != 8) {
         this.writeByte();
      }

      this.output.flush();
   }

   public void write(int value) throws IOException {
      int bitsToWrite = this.bitSize;

      while(bitsToWrite > this.bitsLeft) {
         this.current = (byte)(this.current | value >>> bitsToWrite - this.bitsLeft);
         bitsToWrite -= this.bitsLeft;
         value &= (1 << bitsToWrite) - 1;
         this.writeByte();
      }

      this.bitsLeft -= bitsToWrite;
      this.current = (byte)(this.current | value << this.bitsLeft);
      if (this.bitsLeft == 0) {
         this.writeByte();
      }

   }

   public void getPosition(PositionRecorder recorder) throws IOException {
      this.output.getPosition(recorder);
      recorder.addPosition((long)(8 - this.bitsLeft));
   }

   public long estimateMemory() {
      return this.output.estimateMemory();
   }

   public void changeIv(Consumer modifier) {
      this.output.changeIv(modifier);
   }
}
