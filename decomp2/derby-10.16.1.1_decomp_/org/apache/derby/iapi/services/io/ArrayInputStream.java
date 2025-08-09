package org.apache.derby.iapi.services.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UTFDataFormatException;
import org.apache.derby.shared.common.util.ArrayUtil;

public final class ArrayInputStream extends InputStream implements LimitObjectInput {
   private byte[] pageData;
   private int start;
   private int end;
   private int position;
   private ErrorObjectInput oi;

   public ArrayInputStream() {
      this(ArrayUtil.EMPTY_BYTE_ARRAY);
   }

   public ArrayInputStream(byte[] var1) {
      this.setData(var1);
      this.oi = new FormatIdInputStream(this);
   }

   public void setData(byte[] var1) {
      this.pageData = var1;
      this.start = this.position = 0;
      this.end = var1.length;
   }

   public byte[] getData() {
      return this.pageData;
   }

   public int read() throws IOException {
      return this.position == this.end ? -1 : this.pageData[this.position++] & 255;
   }

   public int read(byte[] var1, int var2, int var3) throws IOException {
      int var4 = this.available();
      if (var3 > var4) {
         if (var4 == 0) {
            return -1;
         }

         var3 = var4;
      }

      System.arraycopy(this.pageData, this.position, var1, var2, var3);
      this.position += var3;
      return var3;
   }

   public long skip(long var1) throws IOException {
      if (var1 <= 0L) {
         return 0L;
      } else {
         long var3 = Math.min(var1, (long)this.available());
         this.position = (int)((long)this.position + var3);
         return var3;
      }
   }

   public int getPosition() {
      return this.position;
   }

   public final void setPosition(int var1) throws IOException {
      if (var1 >= this.start && var1 < this.end) {
         this.position = var1;
      } else {
         throw new EOFException();
      }
   }

   public int available() throws IOException {
      return this.end - this.position;
   }

   public void setLimit(int var1, int var2) throws IOException {
      this.start = var1;
      this.end = var1 + var2;
      this.position = this.start;
      if (var1 < 0 || var2 < 0 || this.end > this.pageData.length) {
         this.start = this.end = this.position = 0;
         throw new EOFException();
      }
   }

   public final void setLimit(int var1) throws IOException {
      this.start = this.position;
      this.end = this.position + var1;
      if (this.end > this.pageData.length) {
         this.start = this.end = this.position = 0;
         throw new EOFException();
      }
   }

   public final int clearLimit() {
      this.start = 0;
      int var1 = this.end - this.position;
      this.end = this.pageData.length;
      return var1;
   }

   public final void readFully(byte[] var1) throws IOException {
      this.readFully(var1, 0, var1.length);
   }

   public final void readFully(byte[] var1, int var2, int var3) throws IOException {
      if (var3 > this.available()) {
         throw new EOFException();
      } else {
         System.arraycopy(this.pageData, this.position, var1, var2, var3);
         this.position += var3;
      }
   }

   public final int skipBytes(int var1) throws IOException {
      return (int)this.skip((long)var1);
   }

   public final boolean readBoolean() throws IOException {
      if (this.position == this.end) {
         throw new EOFException();
      } else {
         return this.pageData[this.position++] != 0;
      }
   }

   public final byte readByte() throws IOException {
      if (this.position == this.end) {
         throw new EOFException();
      } else {
         return this.pageData[this.position++];
      }
   }

   public final int readUnsignedByte() throws IOException {
      if (this.position == this.end) {
         throw new EOFException();
      } else {
         return this.pageData[this.position++] & 255;
      }
   }

   public final short readShort() throws IOException {
      int var1 = this.position;
      byte[] var2 = this.pageData;
      if (var1 >= this.end - 1) {
         throw new EOFException();
      } else {
         int var3 = (var2[var1++] & 255) << 8 | var2[var1++] & 255;
         this.position = var1;
         return (short)var3;
      }
   }

   public final int readUnsignedShort() throws IOException {
      int var1 = this.position;
      byte[] var2 = this.pageData;
      if (var1 >= this.end - 1) {
         throw new EOFException();
      } else {
         int var3 = (var2[var1++] & 255) << 8 | var2[var1++] & 255;
         this.position = var1;
         return var3;
      }
   }

   public final char readChar() throws IOException {
      int var1 = this.position;
      byte[] var2 = this.pageData;
      if (var1 >= this.end - 1) {
         throw new EOFException();
      } else {
         int var3 = (var2[var1++] & 255) << 8 | var2[var1++] & 255;
         this.position = var1;
         return (char)var3;
      }
   }

   public final int readInt() throws IOException {
      int var1 = this.position;
      byte[] var2 = this.pageData;
      if (var1 >= this.end - 3) {
         throw new EOFException();
      } else {
         int var3 = (var2[var1++] & 255) << 24 | (var2[var1++] & 255) << 16 | (var2[var1++] & 255) << 8 | var2[var1++] & 255;
         this.position = var1;
         return var3;
      }
   }

   public final long readLong() throws IOException {
      int var1 = this.position;
      byte[] var2 = this.pageData;
      if (var1 >= this.end - 7) {
         throw new EOFException();
      } else {
         long var3 = (long)(var2[var1++] & 255) << 56 | (long)(var2[var1++] & 255) << 48 | (long)(var2[var1++] & 255) << 40 | (long)(var2[var1++] & 255) << 32 | (long)(var2[var1++] & 255) << 24 | (long)(var2[var1++] & 255) << 16 | (long)(var2[var1++] & 255) << 8 | (long)(var2[var1++] & 255);
         this.position = var1;
         return var3;
      }
   }

   public final float readFloat() throws IOException {
      return Float.intBitsToFloat(this.readInt());
   }

   public final double readDouble() throws IOException {
      return Double.longBitsToDouble(this.readLong());
   }

   public final String readLine() throws IOException {
      return this.oi.readLine();
   }

   public final String readUTF() throws IOException {
      return this.oi.readUTF();
   }

   public final int readDerbyUTF(char[][] var1, int var2) throws IOException {
      byte[] var3 = this.pageData;
      int var4 = this.end;
      int var5 = this.position;
      int var6;
      if (var2 != 0) {
         if (var2 > var4 - var5) {
            throw new EOFException();
         }

         var6 = var2;
      } else {
         var6 = var4 - var5;
      }

      char[] var7 = var1[0];
      if (var7 == null || var6 > var7.length) {
         var7 = new char[var6];
         var1[0] = var7;
      }

      var4 = var5 + var6;
      int var8 = 0;

      while(true) {
         while(var5 < var4) {
            int var9 = var3[var5++] & 255;
            if ((var9 & 128) == 0) {
               var7[var8++] = (char)var9;
            } else if ((var9 & 96) == 64) {
               if (var5 >= var4) {
                  throw new UTFDataFormatException();
               }

               int var14 = var3[var5++] & 255;
               if ((var14 & 192) != 128) {
                  throw new UTFDataFormatException();
               }

               var7[var8++] = (char)((var9 & 31) << 6 | var14 & 63);
            } else {
               if ((var9 & 112) != 96) {
                  throw new UTFDataFormatException();
               }

               if (var5 + 1 >= var4) {
                  throw new UTFDataFormatException();
               }

               int var10 = var3[var5++] & 255;
               int var11 = var3[var5++] & 255;
               if (var9 == 224 && var10 == 0 && var11 == 0 && var2 == 0) {
                  break;
               }

               if ((var10 & 192) != 128 || (var11 & 192) != 128) {
                  throw new UTFDataFormatException();
               }

               var7[var8++] = (char)((var9 & 15) << 12 | (var10 & 63) << 6 | (var11 & 63) << 0);
            }
         }

         this.position = var5;
         return var8;
      }
   }

   public final int readCompressedInt() throws IOException {
      int var1 = this.position;
      byte[] var2 = this.pageData;

      try {
         int var3 = var2[var1++];
         if ((var3 & -64) != 0) {
            if ((var3 & 128) == 0) {
               var3 = (var3 & 63) << 8 | var2[var1++] & 255;
            } else {
               var3 = (var3 & 127) << 24 | (var2[var1++] & 255) << 16 | (var2[var1++] & 255) << 8 | var2[var1++] & 255;
            }
         }

         this.position = var1;
         return var3;
      } catch (ArrayIndexOutOfBoundsException var4) {
         throw new EOFException();
      }
   }

   public final long readCompressedLong() throws IOException {
      try {
         int var1 = this.position;
         byte[] var2 = this.pageData;
         byte var3 = var2[var1++];
         long var4;
         if ((var3 & -64) == 0) {
            var4 = (long)(var3 << 8 | var2[var1++] & 255);
         } else if ((var3 & 128) == 0) {
            var4 = (long)((var3 & 63) << 24 | (var2[var1++] & 255) << 16 | (var2[var1++] & 255) << 8 | var2[var1++] & 255);
         } else {
            var4 = (long)(var3 & 127) << 56 | (long)(var2[var1++] & 255) << 48 | (long)(var2[var1++] & 255) << 40 | (long)(var2[var1++] & 255) << 32 | (long)(var2[var1++] & 255) << 24 | (long)(var2[var1++] & 255) << 16 | (long)(var2[var1++] & 255) << 8 | (long)(var2[var1++] & 255);
         }

         this.position = var1;
         return var4;
      } catch (ArrayIndexOutOfBoundsException var6) {
         throw new EOFException();
      }
   }

   public Object readObject() throws ClassNotFoundException, IOException {
      return this.oi.readObject();
   }

   public String getErrorInfo() {
      return this.oi.getErrorInfo();
   }

   public Exception getNestedException() {
      return this.oi.getNestedException();
   }
}
