package org.apache.derby.iapi.services.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;

public class CounterOutputStream extends OutputStream implements Limit {
   protected OutputStream out;
   private int count;
   private int limit = -1;

   public void setOutputStream(OutputStream var1) {
      this.out = var1;
      this.setLimit(-1);
   }

   public int getCount() {
      return this.count;
   }

   public void setLimit(int var1) {
      this.count = 0;
      this.limit = var1;
   }

   public int clearLimit() {
      int var1 = this.limit - this.count;
      this.limit = 0;
      return var1;
   }

   public void write(int var1) throws IOException {
      if (this.limit >= 0 && this.count + 1 > this.limit) {
         throw new EOFException();
      } else {
         if (this.out != null) {
            this.out.write(var1);
         }

         ++this.count;
      }
   }

   public void write(byte[] var1, int var2, int var3) throws IOException {
      if (this.limit >= 0 && this.count + var3 > this.limit) {
         throw new EOFException();
      } else {
         if (this.out != null) {
            this.out.write(var1, var2, var3);
         }

         this.count += var3;
      }
   }
}
