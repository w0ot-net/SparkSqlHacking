package com.clearspring.analytics.util;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class Varint {
   private Varint() {
   }

   public static void writeSignedVarLong(long value, DataOutput out) throws IOException {
      writeUnsignedVarLong(value << 1 ^ value >> 63, out);
   }

   public static void writeUnsignedVarLong(long value, DataOutput out) throws IOException {
      while((value & -128L) != 0L) {
         out.writeByte((int)value & 127 | 128);
         value >>>= 7;
      }

      out.writeByte((int)value & 127);
   }

   public static void writeSignedVarInt(int value, DataOutput out) throws IOException {
      writeUnsignedVarInt(value << 1 ^ value >> 31, out);
   }

   public static void writeUnsignedVarInt(int value, DataOutput out) throws IOException {
      while((long)(value & -128) != 0L) {
         out.writeByte(value & 127 | 128);
         value >>>= 7;
      }

      out.writeByte(value & 127);
   }

   public static byte[] writeSignedVarInt(int value) {
      return writeUnsignedVarInt(value << 1 ^ value >> 31);
   }

   public static byte[] writeUnsignedVarInt(int value) {
      byte[] byteArrayList = new byte[10];

      int i;
      for(i = 0; (long)(value & -128) != 0L; value >>>= 7) {
         byteArrayList[i++] = (byte)(value & 127 | 128);
      }

      byteArrayList[i] = (byte)(value & 127);

      byte[] out;
      for(out = new byte[i + 1]; i >= 0; --i) {
         out[i] = byteArrayList[i];
      }

      return out;
   }

   public static long readSignedVarLong(DataInput in) throws IOException {
      long raw = readUnsignedVarLong(in);
      long temp = (raw << 63 >> 63 ^ raw) >> 1;
      return temp ^ raw & Long.MIN_VALUE;
   }

   public static long readUnsignedVarLong(DataInput in) throws IOException {
      long value = 0L;
      int i = 0;

      long b;
      while(((b = (long)in.readByte()) & 128L) != 0L) {
         value |= (b & 127L) << i;
         i += 7;
         if (i > 63) {
            throw new IllegalArgumentException("Variable length quantity is too long");
         }
      }

      return value | b << i;
   }

   public static int readSignedVarInt(DataInput in) throws IOException {
      int raw = readUnsignedVarInt(in);
      int temp = (raw << 31 >> 31 ^ raw) >> 1;
      return temp ^ raw & Integer.MIN_VALUE;
   }

   public static int readUnsignedVarInt(DataInput in) throws IOException {
      int value = 0;
      int i = 0;

      int b;
      while(((b = in.readByte()) & 128) != 0) {
         value |= (b & 127) << i;
         i += 7;
         if (i > 35) {
            throw new IllegalArgumentException("Variable length quantity is too long");
         }
      }

      return value | b << i;
   }

   public static int readSignedVarInt(byte[] bytes) {
      int raw = readUnsignedVarInt(bytes);
      int temp = (raw << 31 >> 31 ^ raw) >> 1;
      return temp ^ raw & Integer.MIN_VALUE;
   }

   public static int readUnsignedVarInt(byte[] bytes) {
      int value = 0;
      int i = 0;
      byte rb = -128;

      for(byte b : bytes) {
         rb = b;
         if ((b & 128) == 0) {
            break;
         }

         value |= (b & 127) << i;
         i += 7;
         if (i > 35) {
            throw new IllegalArgumentException("Variable length quantity is too long");
         }
      }

      return value | rb << i;
   }
}
