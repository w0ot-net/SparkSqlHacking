package org.apache.hadoop.hive.serde2;

import java.io.IOException;
import org.apache.hadoop.hive.common.io.NonSyncByteArrayInputStream;
import org.apache.hadoop.hive.common.io.NonSyncByteArrayOutputStream;

public class ByteStream {
   public static class Input extends NonSyncByteArrayInputStream {
      public byte[] getData() {
         return this.buf;
      }

      public int getCount() {
         return this.count;
      }

      public void reset(byte[] argBuf, int argCount) {
         this.buf = argBuf;
         this.mark = this.pos = 0;
         this.count = argCount;
      }

      public Input() {
         super(new byte[1]);
      }

      public Input(byte[] buf) {
         super(buf);
      }

      public Input(byte[] buf, int offset, int length) {
         super(buf, offset, length);
      }
   }

   public static final class Output extends NonSyncByteArrayOutputStream implements RandomAccessOutput {
      public byte[] getData() {
         return this.buf;
      }

      public Output() {
      }

      public Output(int size) {
         super(size);
      }

      public void writeInt(long offset, int value) {
         int offset2 = (int)offset;
         this.getData()[offset2++] = (byte)(value >> 24);
         this.getData()[offset2++] = (byte)(value >> 16);
         this.getData()[offset2++] = (byte)(value >> 8);
         this.getData()[offset2] = (byte)value;
      }

      public void writeByte(long offset, byte value) {
         this.getData()[(int)offset] = value;
      }

      public void reserve(int byteCount) {
         for(int i = 0; i < byteCount; ++i) {
            this.write(0);
         }

      }

      public boolean arraysEquals(Output output) {
         if (this.count != output.count) {
            return false;
         } else {
            for(int i = 0; i < this.count; ++i) {
               if (this.buf[i] != output.buf[i]) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public interface RandomAccessOutput {
      void writeByte(long var1, byte var3);

      void writeInt(long var1, int var3);

      void reserve(int var1);

      void write(int var1);

      void write(byte[] var1) throws IOException;

      void write(byte[] var1, int var2, int var3);

      int getLength();
   }
}
