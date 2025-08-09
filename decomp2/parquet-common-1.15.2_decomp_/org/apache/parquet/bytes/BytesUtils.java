package org.apache.parquet.bytes;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BytesUtils {
   private static final Logger LOG = LoggerFactory.getLogger(BytesUtils.class);
   /** @deprecated */
   @Deprecated
   public static final Charset UTF8 = Charset.forName("UTF-8");

   public static int getWidthFromMaxInt(int bound) {
      return 32 - Integer.numberOfLeadingZeros(bound);
   }

   public static int readIntLittleEndian(ByteBuffer in, int offset) throws IOException {
      int ch4 = in.get(offset) & 255;
      int ch3 = in.get(offset + 1) & 255;
      int ch2 = in.get(offset + 2) & 255;
      int ch1 = in.get(offset + 3) & 255;
      return (ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0);
   }

   public static int readIntLittleEndian(byte[] in, int offset) throws IOException {
      int ch4 = in[offset] & 255;
      int ch3 = in[offset + 1] & 255;
      int ch2 = in[offset + 2] & 255;
      int ch1 = in[offset + 3] & 255;
      return (ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0);
   }

   public static int readIntLittleEndian(InputStream in) throws IOException {
      int ch1 = in.read();
      int ch2 = in.read();
      int ch3 = in.read();
      int ch4 = in.read();
      if ((ch1 | ch2 | ch3 | ch4) < 0) {
         throw new EOFException();
      } else {
         return (ch4 << 24) + (ch3 << 16) + (ch2 << 8) + (ch1 << 0);
      }
   }

   public static int readIntLittleEndianOnOneByte(InputStream in) throws IOException {
      int ch1 = in.read();
      if (ch1 < 0) {
         throw new EOFException();
      } else {
         return ch1;
      }
   }

   public static int readIntLittleEndianOnTwoBytes(InputStream in) throws IOException {
      int ch1 = in.read();
      int ch2 = in.read();
      if ((ch1 | ch2) < 0) {
         throw new EOFException();
      } else {
         return (ch2 << 8) + (ch1 << 0);
      }
   }

   public static int readIntLittleEndianOnThreeBytes(InputStream in) throws IOException {
      int ch1 = in.read();
      int ch2 = in.read();
      int ch3 = in.read();
      if ((ch1 | ch2 | ch3) < 0) {
         throw new EOFException();
      } else {
         return (ch3 << 16) + (ch2 << 8) + (ch1 << 0);
      }
   }

   public static int readIntLittleEndianPaddedOnBitWidth(InputStream in, int bitWidth) throws IOException {
      int bytesWidth = paddedByteCountFromBits(bitWidth);
      switch (bytesWidth) {
         case 0:
            return 0;
         case 1:
            return readIntLittleEndianOnOneByte(in);
         case 2:
            return readIntLittleEndianOnTwoBytes(in);
         case 3:
            return readIntLittleEndianOnThreeBytes(in);
         case 4:
            return readIntLittleEndian(in);
         default:
            throw new IOException(String.format("Encountered bitWidth (%d) that requires more than 4 bytes", bitWidth));
      }
   }

   public static void writeIntLittleEndianOnOneByte(OutputStream out, int v) throws IOException {
      out.write(v >>> 0 & 255);
   }

   public static void writeIntLittleEndianOnTwoBytes(OutputStream out, int v) throws IOException {
      out.write(v >>> 0 & 255);
      out.write(v >>> 8 & 255);
   }

   public static void writeIntLittleEndianOnThreeBytes(OutputStream out, int v) throws IOException {
      out.write(v >>> 0 & 255);
      out.write(v >>> 8 & 255);
      out.write(v >>> 16 & 255);
   }

   public static void writeIntLittleEndian(OutputStream out, int v) throws IOException {
      out.write(v >>> 0 & 255);
      out.write(v >>> 8 & 255);
      out.write(v >>> 16 & 255);
      out.write(v >>> 24 & 255);
      if (LOG.isDebugEnabled()) {
         LOG.debug("write le int: " + v + " => " + (v >>> 0 & 255) + " " + (v >>> 8 & 255) + " " + (v >>> 16 & 255) + " " + (v >>> 24 & 255));
      }

   }

   public static void writeIntLittleEndianPaddedOnBitWidth(OutputStream out, int v, int bitWidth) throws IOException {
      int bytesWidth = paddedByteCountFromBits(bitWidth);
      switch (bytesWidth) {
         case 0:
            break;
         case 1:
            writeIntLittleEndianOnOneByte(out, v);
            break;
         case 2:
            writeIntLittleEndianOnTwoBytes(out, v);
            break;
         case 3:
            writeIntLittleEndianOnThreeBytes(out, v);
            break;
         case 4:
            writeIntLittleEndian(out, v);
            break;
         default:
            throw new IOException(String.format("Encountered value (%d) that requires more than 4 bytes", v));
      }

   }

   public static int readUnsignedVarInt(InputStream in) throws IOException {
      int value = 0;

      int i;
      int b;
      for(i = 0; ((b = in.read()) & 128) != 0; i += 7) {
         value |= (b & 127) << i;
      }

      return value | b << i;
   }

   public static int readZigZagVarInt(InputStream in) throws IOException {
      int raw = readUnsignedVarInt(in);
      int temp = (raw << 31 >> 31 ^ raw) >> 1;
      return temp ^ raw & Integer.MIN_VALUE;
   }

   public static void writeUnsignedVarInt(int value, OutputStream out) throws IOException {
      while((long)(value & -128) != 0L) {
         out.write(value & 127 | 128);
         value >>>= 7;
      }

      out.write(value & 127);
   }

   public static void writeUnsignedVarInt(int value, ByteBuffer dest) throws IOException {
      while((long)(value & -128) != 0L) {
         dest.put((byte)(value & 127 | 128));
         value >>>= 7;
      }

      dest.put((byte)(value & 127));
   }

   public static void writeZigZagVarInt(int intValue, OutputStream out) throws IOException {
      writeUnsignedVarInt(intValue << 1 ^ intValue >> 31, out);
   }

   public static long readZigZagVarLong(InputStream in) throws IOException {
      long raw = readUnsignedVarLong(in);
      long temp = (raw << 63 >> 63 ^ raw) >> 1;
      return temp ^ raw & Long.MIN_VALUE;
   }

   public static long readUnsignedVarLong(InputStream in) throws IOException {
      long value = 0L;

      int i;
      long b;
      for(i = 0; ((b = (long)in.read()) & 128L) != 0L; i += 7) {
         value |= (b & 127L) << i;
      }

      return value | b << i;
   }

   public static void writeUnsignedVarLong(long value, OutputStream out) throws IOException {
      while((value & -128L) != 0L) {
         out.write((int)(value & 127L | 128L));
         value >>>= 7;
      }

      out.write((int)(value & 127L));
   }

   public static void writeUnsignedVarLong(long value, ByteBuffer out) {
      while((value & -128L) != 0L) {
         out.put((byte)((int)(value & 127L | 128L)));
         value >>>= 7;
      }

      out.put((byte)((int)(value & 127L)));
   }

   public static void writeZigZagVarLong(long longValue, OutputStream out) throws IOException {
      writeUnsignedVarLong(longValue << 1 ^ longValue >> 63, out);
   }

   public static int paddedByteCountFromBits(int bitLength) {
      return (bitLength + 7) / 8;
   }

   public static byte[] intToBytes(int value) {
      byte[] outBuffer = new byte[4];
      outBuffer[3] = (byte)(value >>> 24);
      outBuffer[2] = (byte)(value >>> 16);
      outBuffer[1] = (byte)(value >>> 8);
      outBuffer[0] = (byte)(value >>> 0);
      return outBuffer;
   }

   public static int bytesToInt(byte[] bytes) {
      return ((bytes[3] & 255) << 24) + ((bytes[2] & 255) << 16) + ((bytes[1] & 255) << 8) + ((bytes[0] & 255) << 0);
   }

   public static byte[] longToBytes(long value) {
      byte[] outBuffer = new byte[8];
      outBuffer[7] = (byte)((int)(value >>> 56));
      outBuffer[6] = (byte)((int)(value >>> 48));
      outBuffer[5] = (byte)((int)(value >>> 40));
      outBuffer[4] = (byte)((int)(value >>> 32));
      outBuffer[3] = (byte)((int)(value >>> 24));
      outBuffer[2] = (byte)((int)(value >>> 16));
      outBuffer[1] = (byte)((int)(value >>> 8));
      outBuffer[0] = (byte)((int)(value >>> 0));
      return outBuffer;
   }

   public static long bytesToLong(byte[] bytes) {
      return ((long)bytes[7] << 56) + ((long)(bytes[6] & 255) << 48) + ((long)(bytes[5] & 255) << 40) + ((long)(bytes[4] & 255) << 32) + ((long)(bytes[3] & 255) << 24) + ((long)(bytes[2] & 255) << 16) + ((long)(bytes[1] & 255) << 8) + ((long)(bytes[0] & 255) << 0);
   }

   public static byte[] booleanToBytes(boolean value) {
      byte[] outBuffer = new byte[1];
      outBuffer[0] = (byte)(value ? 1 : 0);
      return outBuffer;
   }

   public static boolean bytesToBool(byte[] bytes) {
      return (bytes[0] & 255) != 0;
   }
}
