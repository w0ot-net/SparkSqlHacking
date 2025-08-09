package org.apache.derby.iapi.services.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DynamicByteArrayOutputStream extends OutputStream {
   private static int INITIAL_SIZE = 4096;
   private byte[] buf;
   private int position;
   private int used;
   private int beginPosition;

   public DynamicByteArrayOutputStream() {
      this(INITIAL_SIZE);
   }

   public DynamicByteArrayOutputStream(int var1) {
      this.buf = new byte[var1];
   }

   public DynamicByteArrayOutputStream(byte[] var1) {
      this.buf = var1;
   }

   public DynamicByteArrayOutputStream(DynamicByteArrayOutputStream var1) {
      byte[] var2 = var1.getByteArray();
      this.buf = new byte[var2.length];
      this.write(var2, 0, var2.length);
      this.position = var1.getPosition();
      this.used = var1.getUsed();
      this.beginPosition = var1.getBeginPosition();
   }

   public void write(int var1) {
      if (this.position >= this.buf.length) {
         this.expandBuffer(INITIAL_SIZE);
      }

      this.buf[this.position++] = (byte)var1;
      if (this.position > this.used) {
         this.used = this.position;
      }

   }

   public void write(byte[] var1, int var2, int var3) {
      if (this.position + var3 > this.buf.length) {
         this.expandBuffer(var3);
      }

      System.arraycopy(var1, var2, this.buf, this.position, var3);
      this.position += var3;
      if (this.position > this.used) {
         this.used = this.position;
      }

   }

   void writeCompleteStream(InputStream var1, int var2) throws IOException {
      if (this.position + var2 > this.buf.length) {
         this.expandBuffer(var2);
      }

      InputStreamUtil.readFully(var1, this.buf, this.position, var2);
      this.position += var2;
      if (this.position > this.used) {
         this.used = this.position;
      }

   }

   public void close() {
      this.buf = null;
      this.reset();
   }

   public void reset() {
      this.position = 0;
      this.beginPosition = 0;
      this.used = 0;
   }

   public byte[] getByteArray() {
      return this.buf;
   }

   public int getUsed() {
      return this.used;
   }

   public int getPosition() {
      return this.position;
   }

   public int getBeginPosition() {
      return this.beginPosition;
   }

   public void setPosition(int var1) {
      if (var1 > this.position && var1 > this.buf.length) {
         this.expandBuffer(var1 - this.buf.length);
      }

      this.position = var1;
      if (this.position > this.used) {
         this.used = this.position;
      }

   }

   public void setBeginPosition(int var1) {
      if (var1 <= this.buf.length) {
         this.beginPosition = var1;
      }
   }

   public void discardLeft(int var1) {
      System.arraycopy(this.buf, var1, this.buf, 0, this.used - var1);
      this.position -= var1;
      this.used -= var1;
   }

   private void expandBuffer(int var1) {
      if (this.buf.length < 131072) {
         if (var1 < INITIAL_SIZE) {
            var1 = INITIAL_SIZE;
         }
      } else if (this.buf.length < 1048576) {
         if (var1 < 131072) {
            var1 = 131072;
         }
      } else if (var1 < 1048576) {
         var1 = 1048576;
      }

      int var2 = this.buf.length + var1;
      byte[] var3 = new byte[var2];
      System.arraycopy(this.buf, 0, var3, 0, this.buf.length);
      this.buf = var3;
   }
}
