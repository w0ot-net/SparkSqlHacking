package org.apache.orc.impl;

import java.io.IOException;
import java.util.function.Consumer;

public class RunLengthByteWriter {
   static final int MIN_REPEAT_SIZE = 3;
   static final int MAX_LITERAL_SIZE = 128;
   static final int MAX_REPEAT_SIZE = 130;
   private final PositionedOutputStream output;
   private final byte[] literals = new byte[128];
   private int numLiterals = 0;
   private boolean repeat = false;
   private int tailRunLength = 0;

   public RunLengthByteWriter(PositionedOutputStream output) {
      this.output = output;
   }

   private void writeValues() throws IOException {
      if (this.numLiterals != 0) {
         if (this.repeat) {
            this.output.write(this.numLiterals - 3);
            this.output.write(this.literals, 0, 1);
         } else {
            this.output.write(-this.numLiterals);
            this.output.write(this.literals, 0, this.numLiterals);
         }

         this.repeat = false;
         this.tailRunLength = 0;
         this.numLiterals = 0;
      }

   }

   public void flush() throws IOException {
      this.writeValues();
      this.output.flush();
   }

   public void write(byte value) throws IOException {
      if (this.numLiterals == 0) {
         this.literals[this.numLiterals++] = value;
         this.tailRunLength = 1;
      } else if (this.repeat) {
         if (value == this.literals[0]) {
            ++this.numLiterals;
            if (this.numLiterals == 130) {
               this.writeValues();
            }
         } else {
            this.writeValues();
            this.literals[this.numLiterals++] = value;
            this.tailRunLength = 1;
         }
      } else {
         if (value == this.literals[this.numLiterals - 1]) {
            ++this.tailRunLength;
         } else {
            this.tailRunLength = 1;
         }

         if (this.tailRunLength == 3) {
            if (this.numLiterals + 1 == 3) {
               this.repeat = true;
               ++this.numLiterals;
            } else {
               this.numLiterals -= 2;
               this.writeValues();
               this.literals[0] = value;
               this.repeat = true;
               this.numLiterals = 3;
            }
         } else {
            this.literals[this.numLiterals++] = value;
            if (this.numLiterals == 128) {
               this.writeValues();
            }
         }
      }

   }

   public void getPosition(PositionRecorder recorder) throws IOException {
      this.output.getPosition(recorder);
      recorder.addPosition((long)this.numLiterals);
   }

   public long estimateMemory() {
      return this.output.getBufferSize() + 128L;
   }

   public void changeIv(Consumer modifier) {
      this.output.changeIv(modifier);
   }
}
