package org.apache.derby.impl.store.raw.data;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RememberBytesInputStream extends FilterInputStream {
   ByteHolder bh;
   boolean recording = true;
   boolean streamClosed = false;

   public RememberBytesInputStream(InputStream var1, ByteHolder var2) {
      super(var1);
      this.bh = var2;
   }

   public int read() throws IOException {
      int var1 = -1;
      if (!this.streamClosed) {
         var1 = super.read();
         if (var1 != -1) {
            this.bh.write(var1);
         } else {
            this.streamClosed = true;
         }
      }

      return var1;
   }

   public int read(byte[] var1, int var2, int var3) throws IOException {
      if (!this.streamClosed) {
         if (var3 + var2 > var1.length) {
            var3 = var1.length - var2;
         }

         var3 = super.read(var1, var2, var3);
         if (var3 > 0) {
            this.bh.write(var1, var2, var3);
         } else {
            this.streamClosed = true;
         }

         return var3;
      } else {
         return -1;
      }
   }

   public long fillBuf(int var1) throws IOException {
      long var2 = 0L;
      if (!this.streamClosed) {
         var2 = this.bh.write(this.in, (long)var1);
         if (var2 < (long)var1) {
            this.streamClosed = true;
         }
      }

      return var2;
   }

   public int putBuf(OutputStream var1, int var2) throws IOException {
      this.bh.startReading();
      return this.bh.read(var1, var2);
   }

   public long skip(long var1) throws IOException {
      return this.bh.write(this.in, var1);
   }

   public InputStream getReplayStream() throws IOException {
      this.bh.startReading();
      this.recording = false;
      return new ByteHolderInputStream(this.bh);
   }

   public ByteHolder getByteHolder() throws IOException {
      return this.bh;
   }

   public void clear() throws IOException {
      this.bh.clear();
      this.recording = true;
   }

   public void setInput(InputStream var1) {
      this.in = var1;
      this.streamClosed = false;
   }

   public boolean recording() {
      return this.recording;
   }

   public int available() throws IOException {
      int var1 = this.bh.available();
      var1 = var1 > 0 ? var1 : -1 * var1;
      return var1;
   }

   public int numBytesSaved() throws IOException {
      return this.bh.numBytesSaved();
   }

   public int shiftToFront() throws IOException {
      int var1 = this.bh.shiftToFront();
      return var1;
   }

   public String toString() {
      return "RememberBytesInputStream:  recording: " + this.recording + " " + this.bh;
   }
}
