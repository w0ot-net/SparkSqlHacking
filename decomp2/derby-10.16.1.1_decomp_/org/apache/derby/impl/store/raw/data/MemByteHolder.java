package org.apache.derby.impl.store.raw.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

public class MemByteHolder implements ByteHolder {
   int bufSize;
   boolean writing = true;
   Vector bufV;
   int curBufVEleAt;
   byte[] curBuf;
   int curBufPos;
   int curBufDataBytes;
   int lastBufVEleAt = 0;
   int lastBufDataBytes = 0;

   public MemByteHolder(int var1) {
      this.bufSize = var1;
      this.curBuf = new byte[var1];
      this.curBufPos = 0;
      this.bufV = new Vector(128);
      this.bufV.addElement(this.curBuf);
      this.curBufVEleAt = 0;
   }

   public void write(int var1) throws IOException {
      if (this.curBufPos >= this.curBuf.length) {
         this.getNextBuffer_w();
      }

      this.curBuf[this.curBufPos++] = (byte)var1;
   }

   public void write(byte[] var1, int var2, int var3) throws IOException {
      while(var3 > 0) {
         if (this.curBufPos >= this.curBuf.length) {
            this.getNextBuffer_w();
         }

         int var4 = var3;
         int var5 = this.curBuf.length - this.curBufPos;
         if (var3 > var5) {
            var4 = var5;
         }

         System.arraycopy(var1, var2, this.curBuf, this.curBufPos, var4);
         var2 += var4;
         this.curBufPos += var4;
         var3 -= var4;
      }

   }

   public long write(InputStream var1, long var2) throws IOException {
      long var4 = var2;
      int var6 = 0;

      do {
         if (this.curBufPos >= this.curBuf.length) {
            this.getNextBuffer_w();
         }

         int var8 = this.curBuf.length - this.curBufPos;
         int var7;
         if (var4 >= (long)var8) {
            var7 = var8;
         } else {
            var7 = (int)var4;
         }

         var6 = var1.read(this.curBuf, this.curBufPos, var7);
         if (var6 > 0) {
            var4 -= (long)var6;
            this.curBufPos += var6;
         }
      } while(var4 > 0L && var6 > 0);

      return var2 - var4;
   }

   public void clear() throws IOException {
      this.writing = true;
      this.curBuf = (byte[])this.bufV.elementAt(0);
      this.curBufVEleAt = 0;
      this.curBufPos = 0;
      this.lastBufVEleAt = 0;
      this.lastBufDataBytes = 0;
   }

   public void startReading() throws IOException {
      if (this.writing) {
         this.writing = false;
         this.lastBufDataBytes = this.curBufPos;
         this.lastBufVEleAt = this.curBufVEleAt;
      }

      this.curBuf = (byte[])this.bufV.elementAt(0);
      this.curBufVEleAt = 0;
      this.curBufPos = 0;
      if (this.curBufVEleAt == this.lastBufVEleAt) {
         this.curBufDataBytes = this.lastBufDataBytes;
      } else {
         this.curBufDataBytes = this.bufSize;
      }

   }

   public int read() throws IOException {
      if (this.curBufPos >= this.curBufDataBytes) {
         this.getNextBuffer_r();
      }

      return this.curBufPos >= this.curBufDataBytes ? -1 : 255 & this.curBuf[this.curBufPos++];
   }

   public int read(byte[] var1, int var2, int var3) throws IOException {
      return this.read(var1, var2, (OutputStream)null, var3);
   }

   public int read(OutputStream var1, int var2) throws IOException {
      return this.read((byte[])null, 0, var1, var2);
   }

   public int read(byte[] var1, int var2, OutputStream var3, int var4) throws IOException {
      int var5 = 0;
      boolean var6 = false;
      if (this.curBufPos >= this.curBufDataBytes) {
         var6 = this.getNextBuffer_r();
      }

      if (var6) {
         return -1;
      } else {
         while(var4 > 0 && !var6) {
            int var7 = this.curBufDataBytes - this.curBufPos;
            int var8;
            if (var4 >= var7) {
               var8 = var7;
            } else {
               var8 = var4;
            }

            if (var3 == null) {
               System.arraycopy(this.curBuf, this.curBufPos, var1, var2, var8);
            } else {
               var3.write(this.curBuf, this.curBufPos, var8);
            }

            var2 += var8;
            this.curBufPos += var8;
            var4 -= var8;
            var5 += var8;
            if (this.curBufPos >= this.curBufDataBytes) {
               var6 = this.getNextBuffer_r();
            }
         }

         return var5;
      }
   }

   public int shiftToFront() throws IOException {
      int var1 = this.available();
      var1 = var1 > 0 ? var1 : -1 * var1;
      byte[] var2 = new byte[var1 + 1];
      int var3 = this.read(var2, 0, var1);
      this.clear();
      this.writing = true;
      this.write(var2, 0, var3);
      this.curBufDataBytes = 0;
      return var3;
   }

   public int available() {
      int var1 = this.curBufDataBytes - this.curBufPos;
      int var2 = 0;
      int var3 = 0;
      if (this.curBufVEleAt != this.lastBufVEleAt) {
         var3 = this.lastBufVEleAt - this.curBufVEleAt - 1;
         var2 = this.lastBufDataBytes;
      }

      int var4 = var1 + var2 + var3 * this.bufSize;
      return var4;
   }

   public int numBytesSaved() {
      int var1;
      if (this.writing) {
         var1 = this.curBufVEleAt * this.bufSize + this.curBufPos;
      } else {
         var1 = this.lastBufVEleAt * this.bufSize + this.lastBufDataBytes;
      }

      return var1;
   }

   public long skip(long var1) throws IOException {
      long var3 = 0L;
      boolean var5 = false;
      if (this.curBufPos >= this.curBufDataBytes) {
         var5 = this.getNextBuffer_r();
      }

      while(var1 > 0L && !var5) {
         int var6 = this.curBufDataBytes - this.curBufPos;
         int var7;
         if (var1 >= (long)var6) {
            var7 = var6;
         } else {
            var7 = (int)var1;
         }

         this.curBufPos += var7;
         var1 -= (long)var7;
         var3 += (long)var7;
         if (var1 > 0L) {
            var5 = this.getNextBuffer_r();
         }
      }

      return var3;
   }

   public boolean writingMode() {
      return this.writing;
   }

   public ByteHolder cloneEmpty() {
      return new MemByteHolder(this.bufSize);
   }

   protected void getNextBuffer_w() throws IOException {
      ++this.curBufVEleAt;
      if (this.bufV.size() <= this.curBufVEleAt) {
         this.curBuf = new byte[this.bufSize];
         this.bufV.addElement(this.curBuf);
      } else {
         this.curBuf = (byte[])this.bufV.elementAt(this.curBufVEleAt);
      }

      this.initBuffer_w();
   }

   protected void getNextBuffer_w_Sanity() {
   }

   protected void initBuffer_w() {
      this.curBufPos = 0;
   }

   protected boolean getNextBuffer_r() throws IOException {
      if (this.curBufVEleAt >= this.lastBufVEleAt) {
         return true;
      } else {
         this.curBuf = (byte[])this.bufV.elementAt(++this.curBufVEleAt);
         this.curBufPos = 0;
         if (this.curBufVEleAt == this.lastBufVEleAt) {
            this.curBufDataBytes = this.lastBufDataBytes;
         } else {
            this.curBufDataBytes = this.bufSize;
         }

         return false;
      }
   }

   private String dumpBuf(int var1) {
      StringBuffer var2 = new StringBuffer(100);
      byte[] var3 = (byte[])this.bufV.elementAt(var1);
      var2.append("(");

      for(int var4 = 0; var4 < var3.length; ++var4) {
         var2.append(var3[var4] + ".");
      }

      var2.append(")");
      return var2.toString();
   }

   public String toString() {
      boolean var10000 = this.writing;
      return " writing: " + var10000 + " curBufVEleAt: " + this.curBufVEleAt + " curBufPos: " + this.curBufPos + " curBufDataBytes: " + this.curBufDataBytes + " lastBufVEleAt: " + this.lastBufVEleAt + " lastBufDataBytes: " + this.lastBufDataBytes + " curBuf: " + this.dumpBuf(this.curBufVEleAt);
   }
}
