package com.clearspring.analytics.stream.membership;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public final class DataInputBuffer extends DataInputStream {
   private Buffer buffer_;

   public DataInputBuffer() {
      this(new Buffer());
   }

   private DataInputBuffer(Buffer buffer) {
      super(buffer);
      this.buffer_ = buffer;
   }

   public void reset(byte[] input, int length) {
      this.buffer_.reset(input, 0, length);
   }

   public void reset(byte[] input, int start, int length) {
      this.buffer_.reset(input, start, length);
   }

   public int getLength() {
      return this.buffer_.getLength();
   }

   public int getPosition() {
      return this.buffer_.getPosition();
   }

   private static class Buffer extends ByteArrayInputStream {
      public Buffer() {
         super(new byte[0]);
      }

      public void reset(byte[] input, int start, int length) {
         this.buf = input;
         this.count = start + length;
         this.mark = start;
         this.pos = start;
      }

      public int getPosition() {
         return this.pos;
      }

      public void setPosition(int position) {
         this.pos = position;
      }

      public int getLength() {
         return this.count;
      }
   }
}
