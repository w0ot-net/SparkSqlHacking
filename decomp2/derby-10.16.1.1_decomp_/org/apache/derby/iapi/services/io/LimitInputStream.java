package org.apache.derby.iapi.services.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LimitInputStream extends FilterInputStream implements Limit {
   protected int remainingBytes;
   protected boolean limitInPlace;

   public LimitInputStream(InputStream var1) {
      super(var1);
      this.clearLimit();
   }

   public int read() throws IOException {
      if (!this.limitInPlace) {
         return super.read();
      } else if (this.remainingBytes == 0) {
         return -1;
      } else {
         int var1 = super.read();
         if (var1 >= 0) {
            --this.remainingBytes;
         }

         return var1;
      }
   }

   public int read(byte[] var1, int var2, int var3) throws IOException {
      if (!this.limitInPlace) {
         return super.read(var1, var2, var3);
      } else if (this.remainingBytes == 0) {
         return -1;
      } else {
         if (this.remainingBytes < var3) {
            var3 = this.remainingBytes;
         }

         var3 = super.read(var1, var2, var3);
         if (var3 > 0) {
            this.remainingBytes -= var3;
         }

         return var3;
      }
   }

   public long skip(long var1) throws IOException {
      if (!this.limitInPlace) {
         return super.skip(var1);
      } else if (this.remainingBytes == 0) {
         return 0L;
      } else {
         if ((long)this.remainingBytes < var1) {
            var1 = (long)this.remainingBytes;
         }

         var1 = super.skip(var1);
         this.remainingBytes = (int)((long)this.remainingBytes - var1);
         return var1;
      }
   }

   public int available() throws IOException {
      if (!this.limitInPlace) {
         return super.available();
      } else if (this.remainingBytes == 0) {
         return 0;
      } else {
         int var1 = super.available();
         return this.remainingBytes < var1 ? this.remainingBytes : var1;
      }
   }

   public void setLimit(int var1) {
      this.remainingBytes = var1;
      this.limitInPlace = true;
   }

   public int clearLimit() {
      int var1 = this.remainingBytes;
      this.limitInPlace = false;
      this.remainingBytes = -1;
      return var1;
   }

   public void setInput(InputStream var1) {
      this.in = var1;
   }

   public boolean markSupported() {
      return false;
   }
}
