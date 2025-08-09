package org.apache.derby.iapi.services.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;

public class ArrayOutputStream extends OutputStream implements Limit {
   private byte[] pageData;
   private int start;
   private int end;
   private int position;

   public ArrayOutputStream() {
   }

   public ArrayOutputStream(byte[] var1) {
      this.setData(var1);
   }

   public void setData(byte[] var1) {
      this.pageData = var1;
      this.start = 0;
      if (var1 != null) {
         this.end = var1.length;
      } else {
         this.end = 0;
      }

      this.position = 0;
   }

   public void write(int var1) throws IOException {
      if (this.position >= this.end) {
         throw new EOFException();
      } else {
         this.pageData[this.position++] = (byte)var1;
      }
   }

   public void write(byte[] var1, int var2, int var3) throws IOException {
      if (this.position + var3 > this.end) {
         throw new EOFException();
      } else {
         System.arraycopy(var1, var2, this.pageData, this.position, var3);
         this.position += var3;
      }
   }

   public int getPosition() {
      return this.position;
   }

   public void setPosition(int var1) throws IOException {
      if (var1 >= this.start && var1 <= this.end) {
         this.position = var1;
      } else {
         throw new EOFException();
      }
   }

   public void setLimit(int var1) throws IOException {
      if (var1 < 0) {
         throw new EOFException();
      } else if (this.position + var1 > this.end) {
         throw new EOFException();
      } else {
         this.start = this.position;
         this.end = this.position + var1;
      }
   }

   public int clearLimit() {
      int var1 = this.end - this.position;
      this.end = this.pageData.length;
      return var1;
   }
}
