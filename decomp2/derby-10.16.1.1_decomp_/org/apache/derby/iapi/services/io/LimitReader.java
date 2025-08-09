package org.apache.derby.iapi.services.io;

import java.io.IOException;
import java.io.Reader;

public final class LimitReader extends Reader implements Limit {
   private int remainingCharacters;
   private boolean limitInPlace;
   private Reader reader;

   public LimitReader(Reader var1) {
      this.reader = var1;
      this.clearLimit();
   }

   public int read() throws IOException {
      if (!this.limitInPlace) {
         return this.reader.read();
      } else if (this.remainingCharacters == 0) {
         return -1;
      } else {
         int var1 = this.reader.read();
         if (var1 >= 0) {
            --this.remainingCharacters;
         }

         return var1;
      }
   }

   public int read(char[] var1, int var2, int var3) throws IOException {
      if (!this.limitInPlace) {
         return this.reader.read(var1, var2, var3);
      } else if (this.remainingCharacters == 0) {
         return -1;
      } else {
         if (this.remainingCharacters < var3) {
            var3 = this.remainingCharacters;
         }

         var3 = this.reader.read(var1, var2, var3);
         if (var3 >= 0) {
            this.remainingCharacters -= var3;
         }

         return var3;
      }
   }

   public long skip(long var1) throws IOException {
      if (!this.limitInPlace) {
         return this.reader.skip(var1);
      } else if (this.remainingCharacters == 0) {
         return 0L;
      } else {
         if ((long)this.remainingCharacters < var1) {
            var1 = (long)this.remainingCharacters;
         }

         var1 = this.reader.skip(var1);
         this.remainingCharacters = (int)((long)this.remainingCharacters - var1);
         return var1;
      }
   }

   public void close() throws IOException {
      this.reader.close();
   }

   public void setLimit(int var1) {
      this.remainingCharacters = var1;
      this.limitInPlace = true;
   }

   public final int getLimit() {
      return this.remainingCharacters;
   }

   public int clearLimit() {
      int var1 = this.remainingCharacters;
      this.limitInPlace = false;
      this.remainingCharacters = -1;
      return var1;
   }
}
