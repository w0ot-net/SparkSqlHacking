package org.apache.orc.impl;

import java.io.IOException;
import java.util.function.Consumer;

public class RunLengthIntegerWriter implements IntegerWriter {
   static final int MIN_REPEAT_SIZE = 3;
   static final int MAX_DELTA = 127;
   static final int MIN_DELTA = -128;
   static final int MAX_LITERAL_SIZE = 128;
   private static final int MAX_REPEAT_SIZE = 130;
   private final PositionedOutputStream output;
   private final boolean signed;
   private final long[] literals = new long[128];
   private int numLiterals = 0;
   private long delta = 0L;
   private boolean repeat = false;
   private int tailRunLength = 0;
   private SerializationUtils utils;

   public RunLengthIntegerWriter(PositionedOutputStream output, boolean signed) {
      this.output = output;
      this.signed = signed;
      this.utils = new SerializationUtils();
   }

   private void writeValues() throws IOException {
      if (this.numLiterals != 0) {
         if (this.repeat) {
            this.output.write(this.numLiterals - 3);
            this.output.write((byte)((int)this.delta));
            if (this.signed) {
               this.utils.writeVslong(this.output, this.literals[0]);
            } else {
               this.utils.writeVulong(this.output, this.literals[0]);
            }
         } else {
            this.output.write(-this.numLiterals);

            for(int i = 0; i < this.numLiterals; ++i) {
               if (this.signed) {
                  this.utils.writeVslong(this.output, this.literals[i]);
               } else {
                  this.utils.writeVulong(this.output, this.literals[i]);
               }
            }
         }

         this.repeat = false;
         this.numLiterals = 0;
         this.tailRunLength = 0;
      }

   }

   public void flush() throws IOException {
      this.writeValues();
      this.output.flush();
   }

   public void write(long value) throws IOException {
      if (this.numLiterals == 0) {
         this.literals[this.numLiterals++] = value;
         this.tailRunLength = 1;
      } else if (this.repeat) {
         if (value == this.literals[0] + this.delta * (long)this.numLiterals) {
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
         if (this.tailRunLength == 1) {
            this.delta = value - this.literals[this.numLiterals - 1];
            if (this.delta >= -128L && this.delta <= 127L) {
               this.tailRunLength = 2;
            } else {
               this.tailRunLength = 1;
            }
         } else if (value == this.literals[this.numLiterals - 1] + this.delta) {
            ++this.tailRunLength;
         } else {
            this.delta = value - this.literals[this.numLiterals - 1];
            if (this.delta >= -128L && this.delta <= 127L) {
               this.tailRunLength = 2;
            } else {
               this.tailRunLength = 1;
            }
         }

         if (this.tailRunLength == 3) {
            if (this.numLiterals + 1 == 3) {
               this.repeat = true;
               ++this.numLiterals;
            } else {
               this.numLiterals -= 2;
               long base = this.literals[this.numLiterals];
               this.writeValues();
               this.literals[0] = base;
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
      return this.output.getBufferSize();
   }

   public void changeIv(Consumer modifier) {
      this.output.changeIv(modifier);
   }
}
