package org.apache.derby.iapi.services.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class CompressedNumber {
   public static final int MAX_INT_STORED_SIZE = 4;
   public static final int MAX_LONG_STORED_SIZE = 8;
   public static final int MAX_COMPRESSED_INT_ONE_BYTE = 63;
   public static final int MAX_COMPRESSED_INT_TWO_BYTES = 16383;

   public static final int writeInt(DataOutput var0, int var1) throws IOException {
      if (var1 < 0) {
         throw new IOException();
      } else if (var1 <= 63) {
         var0.writeByte(var1);
         return 1;
      } else if (var1 <= 16383) {
         var0.writeByte(64 | var1 >>> 8);
         var0.writeByte(var1 & 255);
         return 2;
      } else {
         var0.writeByte((var1 >>> 24 | 128) & 255);
         var0.writeByte(var1 >>> 16 & 255);
         var0.writeByte(var1 >>> 8 & 255);
         var0.writeByte(var1 & 255);
         return 4;
      }
   }

   public static final int writeInt(OutputStream var0, int var1) throws IOException {
      if (var1 < 0) {
         throw new IOException();
      } else if (var1 <= 63) {
         var0.write(var1);
         return 1;
      } else if (var1 <= 16383) {
         var0.write(64 | var1 >>> 8);
         var0.write(var1 & 255);
         return 2;
      } else {
         var0.write((var1 >>> 24 | 128) & 255);
         var0.write(var1 >>> 16 & 255);
         var0.write(var1 >>> 8 & 255);
         var0.write(var1 & 255);
         return 4;
      }
   }

   public static final int readInt(DataInput var0) throws IOException {
      int var1 = var0.readUnsignedByte();
      if ((var1 & -64) == 0) {
         return var1;
      } else {
         return (var1 & 128) == 0 ? (var1 & 63) << 8 | var0.readUnsignedByte() : (var1 & 127) << 24 | var0.readUnsignedByte() << 16 | var0.readUnsignedByte() << 8 | var0.readUnsignedByte();
      }
   }

   public static final int readInt(InputStream var0) throws IOException {
      int var1 = InputStreamUtil.readUnsignedByte(var0);
      if ((var1 & -64) == 0) {
         return var1;
      } else {
         return (var1 & 128) == 0 ? (var1 & 63) << 8 | InputStreamUtil.readUnsignedByte(var0) : (var1 & 127) << 24 | InputStreamUtil.readUnsignedByte(var0) << 16 | InputStreamUtil.readUnsignedByte(var0) << 8 | InputStreamUtil.readUnsignedByte(var0);
      }
   }

   public static final int readInt(byte[] var0, int var1) {
      byte var2 = var0[var1++];
      if ((var2 & -64) == 0) {
         return var2;
      } else {
         return (var2 & 128) == 0 ? (var2 & 63) << 8 | var0[var1] & 255 : (var2 & 127) << 24 | (var0[var1++] & 255) << 16 | (var0[var1++] & 255) << 8 | var0[var1] & 255;
      }
   }

   public static final int sizeInt(int var0) {
      if (var0 <= 63) {
         return 1;
      } else {
         return var0 <= 16383 ? 2 : 4;
      }
   }

   public static final int writeLong(DataOutput var0, long var1) throws IOException {
      if (var1 < 0L) {
         throw new IOException();
      } else if (var1 <= 16383L) {
         var0.writeByte((int)(var1 >>> 8 & 255L));
         var0.writeByte((int)(var1 & 255L));
         return 2;
      } else if (var1 <= 1073741823L) {
         var0.writeByte((int)((var1 >>> 24 | 64L) & 255L));
         var0.writeByte((int)(var1 >>> 16 & 255L));
         var0.writeByte((int)(var1 >>> 8 & 255L));
         var0.writeByte((int)(var1 & 255L));
         return 4;
      } else {
         var0.writeByte((int)((var1 >>> 56 | 128L) & 255L));
         var0.writeByte((int)(var1 >>> 48 & 255L));
         var0.writeByte((int)(var1 >>> 40 & 255L));
         var0.writeByte((int)(var1 >>> 32 & 255L));
         var0.writeByte((int)(var1 >>> 24 & 255L));
         var0.writeByte((int)(var1 >>> 16 & 255L));
         var0.writeByte((int)(var1 >>> 8 & 255L));
         var0.writeByte((int)(var1 & 255L));
         return 8;
      }
   }

   public static final int writeLong(OutputStream var0, long var1) throws IOException {
      if (var1 < 0L) {
         throw new IOException();
      } else if (var1 <= 16383L) {
         var0.write((int)(var1 >>> 8 & 255L));
         var0.write((int)(var1 & 255L));
         return 2;
      } else if (var1 <= 1073741823L) {
         var0.write((int)((var1 >>> 24 | 64L) & 255L));
         var0.write((int)(var1 >>> 16 & 255L));
         var0.write((int)(var1 >>> 8 & 255L));
         var0.write((int)(var1 & 255L));
         return 4;
      } else {
         var0.write((int)((var1 >>> 56 | 128L) & 255L));
         var0.write((int)(var1 >>> 48 & 255L));
         var0.write((int)(var1 >>> 40 & 255L));
         var0.write((int)(var1 >>> 32 & 255L));
         var0.write((int)(var1 >>> 24 & 255L));
         var0.write((int)(var1 >>> 16 & 255L));
         var0.write((int)(var1 >>> 8 & 255L));
         var0.write((int)(var1 & 255L));
         return 8;
      }
   }

   public static final long readLong(DataInput var0) throws IOException {
      int var1 = var0.readUnsignedByte();
      if ((var1 & -64) == 0) {
         return (long)(var1 << 8 | var0.readUnsignedByte());
      } else {
         return (var1 & 128) == 0 ? (long)((var1 & 63) << 24 | var0.readUnsignedByte() << 16 | var0.readUnsignedByte() << 8 | var0.readUnsignedByte()) : (long)(var1 & 127) << 56 | (long)var0.readUnsignedByte() << 48 | (long)var0.readUnsignedByte() << 40 | (long)var0.readUnsignedByte() << 32 | (long)var0.readUnsignedByte() << 24 | (long)var0.readUnsignedByte() << 16 | (long)var0.readUnsignedByte() << 8 | (long)var0.readUnsignedByte();
      }
   }

   public static final long readLong(InputStream var0) throws IOException {
      int var1 = InputStreamUtil.readUnsignedByte(var0);
      if ((var1 & -64) == 0) {
         return (long)(var1 << 8 | InputStreamUtil.readUnsignedByte(var0));
      } else if ((var1 & 128) == 0) {
         return (long)((var1 & 63) << 24 | InputStreamUtil.readUnsignedByte(var0) << 16 | InputStreamUtil.readUnsignedByte(var0) << 8 | InputStreamUtil.readUnsignedByte(var0));
      } else {
         long var2 = (long)var1;
         return (var2 & 127L) << 56 | (long)InputStreamUtil.readUnsignedByte(var0) << 48 | (long)InputStreamUtil.readUnsignedByte(var0) << 40 | (long)InputStreamUtil.readUnsignedByte(var0) << 32 | (long)InputStreamUtil.readUnsignedByte(var0) << 24 | (long)InputStreamUtil.readUnsignedByte(var0) << 16 | (long)InputStreamUtil.readUnsignedByte(var0) << 8 | (long)InputStreamUtil.readUnsignedByte(var0);
      }
   }

   public static final long readLong(byte[] var0, int var1) {
      byte var2 = var0[var1++];
      if ((var2 & -64) == 0) {
         return (long)(var2 << 8 | var0[var1] & 255);
      } else {
         return (var2 & 128) == 0 ? (long)((var2 & 63) << 24 | (var0[var1++] & 255) << 16 | (var0[var1++] & 255) << 8 | var0[var1] & 255) : (long)(var2 & 127) << 56 | (long)(var0[var1++] & 255) << 48 | (long)(var0[var1++] & 255) << 40 | (long)(var0[var1++] & 255) << 32 | (long)(var0[var1++] & 255) << 24 | (long)(var0[var1++] & 255) << 16 | (long)(var0[var1++] & 255) << 8 | (long)(var0[var1] & 255);
      }
   }

   public static final int sizeLong(long var0) {
      if (var0 <= 16383L) {
         return 2;
      } else {
         return var0 <= 1073741823L ? 4 : 8;
      }
   }
}
