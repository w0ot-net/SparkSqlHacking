package org.apache.orc.impl;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Arrays;
import java.util.TimeZone;
import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.orc.CompressionCodec;
import org.apache.orc.OrcFile;
import org.apache.orc.OrcProto;
import org.apache.orc.impl.writer.StreamOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SerializationUtils {
   private static final Logger LOG = LoggerFactory.getLogger(SerializationUtils.class);
   private static final int BUFFER_SIZE = 64;
   private final byte[] readBuffer = new byte[64];
   private final byte[] writeBuffer = new byte[64];
   private final int[] histBuffer = new int[32];

   public void writeVulong(OutputStream output, long value) throws IOException {
      int posn;
      for(posn = 0; (value & -128L) != 0L; value >>>= 7) {
         this.writeBuffer[posn++] = (byte)((int)(128L | value & 127L));
      }

      this.writeBuffer[posn++] = (byte)((int)value);
      output.write(this.writeBuffer, 0, posn);
   }

   public void writeVslong(OutputStream output, long value) throws IOException {
      this.writeVulong(output, value << 1 ^ value >> 63);
   }

   public static long readVulong(InputStream in) throws IOException {
      long result = 0L;
      int offset = 0;

      long b;
      do {
         b = (long)in.read();
         if (b == -1L) {
            throw new EOFException("Reading Vulong past EOF");
         }

         result |= (127L & b) << offset;
         offset += 7;
      } while(b >= 128L);

      return result;
   }

   public static long readVslong(InputStream in) throws IOException {
      long result = readVulong(in);
      return result >>> 1 ^ -(result & 1L);
   }

   public float readFloat(InputStream in) throws IOException {
      this.readFully(in, this.readBuffer, 4);
      int val = ((this.readBuffer[0] & 255) << 0) + ((this.readBuffer[1] & 255) << 8) + ((this.readBuffer[2] & 255) << 16) + ((this.readBuffer[3] & 255) << 24);
      return Float.intBitsToFloat(val);
   }

   public void skipFloat(InputStream in, int numOfFloats) throws IOException {
      IOUtils.skipFully(in, (long)numOfFloats * 4L);
   }

   public void writeFloat(OutputStream output, float value) throws IOException {
      int ser = Float.floatToIntBits(value);
      this.writeBuffer[0] = (byte)(ser >> 0 & 255);
      this.writeBuffer[1] = (byte)(ser >> 8 & 255);
      this.writeBuffer[2] = (byte)(ser >> 16 & 255);
      this.writeBuffer[3] = (byte)(ser >> 24 & 255);
      output.write(this.writeBuffer, 0, 4);
   }

   public double readDouble(InputStream in) throws IOException {
      return Double.longBitsToDouble(this.readLongLE(in));
   }

   public long readLongLE(InputStream in) throws IOException {
      this.readFully(in, this.readBuffer, 8);
      return (long)(((this.readBuffer[0] & 255) << 0) + ((this.readBuffer[1] & 255) << 8) + ((this.readBuffer[2] & 255) << 16)) + ((long)(this.readBuffer[3] & 255) << 24) + ((long)(this.readBuffer[4] & 255) << 32) + ((long)(this.readBuffer[5] & 255) << 40) + ((long)(this.readBuffer[6] & 255) << 48) + ((long)(this.readBuffer[7] & 255) << 56);
   }

   private void readFully(InputStream in, byte[] buffer, int len) throws IOException {
      int offset = 0;

      while(true) {
         int n = in.read(buffer, offset, len);
         if (n == len) {
            return;
         }

         if (n < 0) {
            throw new EOFException("Read past EOF for " + String.valueOf(in));
         }

         offset += n;
         len -= n;
      }
   }

   public void skipDouble(InputStream in, int numOfDoubles) throws IOException {
      IOUtils.skipFully(in, (long)numOfDoubles * 8L);
   }

   public void writeDouble(OutputStream output, double value) throws IOException {
      long bits = Double.doubleToLongBits(value);
      int first = (int)(bits & -1L);
      int second = (int)(bits >>> 32 & -1L);
      this.writeBuffer[0] = (byte)first;
      this.writeBuffer[4] = (byte)second;
      this.writeBuffer[5] = (byte)(second >>> 8);
      this.writeBuffer[1] = (byte)(first >>> 8);
      this.writeBuffer[2] = (byte)(first >>> 16);
      this.writeBuffer[6] = (byte)(second >>> 16);
      this.writeBuffer[7] = (byte)(second >>> 24);
      this.writeBuffer[3] = (byte)(first >>> 24);
      output.write(this.writeBuffer, 0, 8);
   }

   public static void writeBigInteger(OutputStream output, BigInteger value) throws IOException {
      value = value.shiftLeft(1);
      int sign = value.signum();
      if (sign < 0) {
         value = value.negate();
         value = value.subtract(BigInteger.ONE);
      }

      int length = value.bitLength();

      while(true) {
         long lowBits = value.longValue() & Long.MAX_VALUE;
         length -= 63;

         for(int i = 0; i < 9; ++i) {
            if (length <= 0 && (lowBits & -128L) == 0L) {
               output.write((byte)((int)lowBits));
               return;
            }

            output.write((byte)((int)(128L | lowBits & 127L)));
            lowBits >>>= 7;
         }

         value = value.shiftRight(63);
      }
   }

   public static BigInteger readBigInteger(InputStream input) throws IOException {
      BigInteger result = BigInteger.ZERO;
      long work = 0L;
      int offset = 0;

      long b;
      do {
         b = (long)input.read();
         if (b == -1L) {
            throw new EOFException("Reading BigInteger past EOF from " + String.valueOf(input));
         }

         work |= (127L & b) << offset % 63;
         offset += 7;
         if (offset == 63) {
            result = BigInteger.valueOf(work);
            work = 0L;
         } else if (offset % 63 == 0) {
            result = result.or(BigInteger.valueOf(work).shiftLeft(offset - 63));
            work = 0L;
         }
      } while(b >= 128L);

      if (work != 0L) {
         result = result.or(BigInteger.valueOf(work).shiftLeft(offset / 63 * 63));
      }

      boolean isNegative = result.testBit(0);
      if (isNegative) {
         result = result.add(BigInteger.ONE);
         result = result.negate();
      }

      result = result.shiftRight(1);
      return result;
   }

   public int findClosestNumBits(long value) {
      return this.getClosestFixedBits(this.findNumBits(value));
   }

   private int findNumBits(long value) {
      return 64 - Long.numberOfLeadingZeros(value);
   }

   public long zigzagEncode(long val) {
      return val << 1 ^ val >> 63;
   }

   public long zigzagDecode(long val) {
      return val >>> 1 ^ -(val & 1L);
   }

   public int percentileBits(long[] data, int offset, int length, double p) {
      if (!(p > (double)1.0F) && !(p <= (double)0.0F)) {
         Arrays.fill(this.histBuffer, 0);

         for(int i = offset; i < offset + length; ++i) {
            int idx = this.encodeBitWidth(this.findNumBits(data[i]));
            int var10002 = this.histBuffer[idx]++;
         }

         int perLen = (int)((double)length * ((double)1.0F - p));

         for(int i = this.histBuffer.length - 1; i >= 0; --i) {
            perLen -= this.histBuffer[i];
            if (perLen < 0) {
               return decodeBitWidth(i);
            }
         }

         return 0;
      } else {
         return -1;
      }
   }

   public long bytesToLongBE(InStream input, int n) throws IOException {
      long out = 0L;

      long val;
      for(val = 0L; n > 0; out |= val << n * 8) {
         --n;
         val = (long)input.read();
      }

      return out;
   }

   int getTotalBytesRequired(int n, int numBits) {
      return (n * numBits + 7) / 8;
   }

   public int getClosestFixedBits(int n) {
      if (n == 0) {
         return 1;
      } else if (n <= 24) {
         return n;
      } else if (n <= 26) {
         return 26;
      } else if (n <= 28) {
         return 28;
      } else if (n <= 30) {
         return 30;
      } else if (n <= 32) {
         return 32;
      } else if (n <= 40) {
         return 40;
      } else if (n <= 48) {
         return 48;
      } else {
         return n <= 56 ? 56 : 64;
      }
   }

   public int getClosestAlignedFixedBits(int n) {
      if (n != 0 && n != 1) {
         if (n > 1 && n <= 2) {
            return 2;
         } else if (n > 2 && n <= 4) {
            return 4;
         } else if (n > 4 && n <= 8) {
            return 8;
         } else if (n > 8 && n <= 16) {
            return 16;
         } else if (n > 16 && n <= 24) {
            return 24;
         } else if (n > 24 && n <= 32) {
            return 32;
         } else if (n > 32 && n <= 40) {
            return 40;
         } else if (n > 40 && n <= 48) {
            return 48;
         } else {
            return n > 48 && n <= 56 ? 56 : 64;
         }
      } else {
         return 1;
      }
   }

   public int encodeBitWidth(int n) {
      n = this.getClosestFixedBits(n);
      if (n >= 1 && n <= 24) {
         return n - 1;
      } else if (n <= 26) {
         return SerializationUtils.FixedBitSizes.TWENTYSIX.ordinal();
      } else if (n <= 28) {
         return SerializationUtils.FixedBitSizes.TWENTYEIGHT.ordinal();
      } else if (n <= 30) {
         return SerializationUtils.FixedBitSizes.THIRTY.ordinal();
      } else if (n <= 32) {
         return SerializationUtils.FixedBitSizes.THIRTYTWO.ordinal();
      } else if (n <= 40) {
         return SerializationUtils.FixedBitSizes.FORTY.ordinal();
      } else if (n <= 48) {
         return SerializationUtils.FixedBitSizes.FORTYEIGHT.ordinal();
      } else {
         return n <= 56 ? SerializationUtils.FixedBitSizes.FIFTYSIX.ordinal() : SerializationUtils.FixedBitSizes.SIXTYFOUR.ordinal();
      }
   }

   public static int decodeBitWidth(int n) {
      if (n >= SerializationUtils.FixedBitSizes.ONE.ordinal() && n <= SerializationUtils.FixedBitSizes.TWENTYFOUR.ordinal()) {
         return n + 1;
      } else if (n == SerializationUtils.FixedBitSizes.TWENTYSIX.ordinal()) {
         return 26;
      } else if (n == SerializationUtils.FixedBitSizes.TWENTYEIGHT.ordinal()) {
         return 28;
      } else if (n == SerializationUtils.FixedBitSizes.THIRTY.ordinal()) {
         return 30;
      } else if (n == SerializationUtils.FixedBitSizes.THIRTYTWO.ordinal()) {
         return 32;
      } else if (n == SerializationUtils.FixedBitSizes.FORTY.ordinal()) {
         return 40;
      } else if (n == SerializationUtils.FixedBitSizes.FORTYEIGHT.ordinal()) {
         return 48;
      } else {
         return n == SerializationUtils.FixedBitSizes.FIFTYSIX.ordinal() ? 56 : 64;
      }
   }

   public void writeInts(long[] input, int offset, int len, int bitSize, OutputStream output) throws IOException {
      if (input != null && input.length >= 1 && offset >= 0 && len >= 1 && bitSize >= 1) {
         switch (bitSize) {
            case 1:
               this.unrolledBitPack1(input, offset, len, output);
               return;
            case 2:
               this.unrolledBitPack2(input, offset, len, output);
               return;
            case 4:
               this.unrolledBitPack4(input, offset, len, output);
               return;
            case 8:
               this.unrolledBitPack8(input, offset, len, output);
               return;
            case 16:
               this.unrolledBitPack16(input, offset, len, output);
               return;
            case 24:
               this.unrolledBitPack24(input, offset, len, output);
               return;
            case 32:
               this.unrolledBitPack32(input, offset, len, output);
               return;
            case 40:
               this.unrolledBitPack40(input, offset, len, output);
               return;
            case 48:
               this.unrolledBitPack48(input, offset, len, output);
               return;
            case 56:
               this.unrolledBitPack56(input, offset, len, output);
               return;
            case 64:
               this.unrolledBitPack64(input, offset, len, output);
               return;
            default:
               int bitsLeft = 8;
               byte current = 0;
               int i = offset;

               for(; i < offset + len; ++i) {
                  long value = input[i];

                  int bitsToWrite;
                  for(bitsToWrite = bitSize; bitsToWrite > bitsLeft; bitsLeft = 8) {
                     current = (byte)((int)((long)current | value >>> bitsToWrite - bitsLeft));
                     bitsToWrite -= bitsLeft;
                     value &= (1L << bitsToWrite) - 1L;
                     output.write(current);
                     current = 0;
                  }

                  bitsLeft -= bitsToWrite;
                  current = (byte)((int)((long)current | value << bitsLeft));
                  if (bitsLeft == 0) {
                     output.write(current);
                     current = 0;
                     bitsLeft = 8;
                  }
               }

               if (bitsLeft != 8) {
                  output.write(current);
                  current = 0;
                  bitsLeft = 8;
               }

         }
      }
   }

   private void unrolledBitPack1(long[] input, int offset, int len, OutputStream output) throws IOException {
      int numHops = 8;
      int remainder = len % 8;
      int endOffset = offset + len;
      int endUnroll = endOffset - remainder;
      int val = 0;

      for(int i = offset; i < endUnroll; i += 8) {
         val = (int)((long)val | (input[i] & 1L) << 7 | (input[i + 1] & 1L) << 6 | (input[i + 2] & 1L) << 5 | (input[i + 3] & 1L) << 4 | (input[i + 4] & 1L) << 3 | (input[i + 5] & 1L) << 2 | (input[i + 6] & 1L) << 1 | input[i + 7] & 1L);
         output.write(val);
         val = 0;
      }

      if (remainder > 0) {
         int startShift = 7;

         for(int i = endUnroll; i < endOffset; ++i) {
            val = (int)((long)val | (input[i] & 1L) << startShift);
            --startShift;
         }

         output.write(val);
      }

   }

   private void unrolledBitPack2(long[] input, int offset, int len, OutputStream output) throws IOException {
      int numHops = 4;
      int remainder = len % 4;
      int endOffset = offset + len;
      int endUnroll = endOffset - remainder;
      int val = 0;

      for(int i = offset; i < endUnroll; i += 4) {
         val = (int)((long)val | (input[i] & 3L) << 6 | (input[i + 1] & 3L) << 4 | (input[i + 2] & 3L) << 2 | input[i + 3] & 3L);
         output.write(val);
         val = 0;
      }

      if (remainder > 0) {
         int startShift = 6;

         for(int i = endUnroll; i < endOffset; ++i) {
            val = (int)((long)val | (input[i] & 3L) << startShift);
            startShift -= 2;
         }

         output.write(val);
      }

   }

   private void unrolledBitPack4(long[] input, int offset, int len, OutputStream output) throws IOException {
      int numHops = 2;
      int remainder = len % 2;
      int endOffset = offset + len;
      int endUnroll = endOffset - remainder;
      int val = 0;

      for(int i = offset; i < endUnroll; i += 2) {
         val = (int)((long)val | (input[i] & 15L) << 4 | input[i + 1] & 15L);
         output.write(val);
         val = 0;
      }

      if (remainder > 0) {
         int startShift = 4;

         for(int i = endUnroll; i < endOffset; ++i) {
            val = (int)((long)val | (input[i] & 15L) << startShift);
            startShift -= 4;
         }

         output.write(val);
      }

   }

   private void unrolledBitPack8(long[] input, int offset, int len, OutputStream output) throws IOException {
      this.unrolledBitPackBytes(input, offset, len, output, 1);
   }

   private void unrolledBitPack16(long[] input, int offset, int len, OutputStream output) throws IOException {
      this.unrolledBitPackBytes(input, offset, len, output, 2);
   }

   private void unrolledBitPack24(long[] input, int offset, int len, OutputStream output) throws IOException {
      this.unrolledBitPackBytes(input, offset, len, output, 3);
   }

   private void unrolledBitPack32(long[] input, int offset, int len, OutputStream output) throws IOException {
      this.unrolledBitPackBytes(input, offset, len, output, 4);
   }

   private void unrolledBitPack40(long[] input, int offset, int len, OutputStream output) throws IOException {
      this.unrolledBitPackBytes(input, offset, len, output, 5);
   }

   private void unrolledBitPack48(long[] input, int offset, int len, OutputStream output) throws IOException {
      this.unrolledBitPackBytes(input, offset, len, output, 6);
   }

   private void unrolledBitPack56(long[] input, int offset, int len, OutputStream output) throws IOException {
      this.unrolledBitPackBytes(input, offset, len, output, 7);
   }

   private void unrolledBitPack64(long[] input, int offset, int len, OutputStream output) throws IOException {
      this.unrolledBitPackBytes(input, offset, len, output, 8);
   }

   private void unrolledBitPackBytes(long[] input, int offset, int len, OutputStream output, int numBytes) throws IOException {
      int numHops = 8;
      int remainder = len % 8;
      int endOffset = offset + len;
      int endUnroll = endOffset - remainder;

      int i;
      for(i = offset; i < endUnroll; i += 8) {
         this.writeLongBE(output, input, i, 8, numBytes);
      }

      if (remainder > 0) {
         this.writeRemainingLongs(output, i, input, remainder, numBytes);
      }

   }

   private void writeRemainingLongs(OutputStream output, int offset, long[] input, int remainder, int numBytes) throws IOException {
      int numHops = remainder;
      int idx = 0;
      switch (numBytes) {
         case 1:
            while(remainder > 0) {
               this.writeBuffer[idx] = (byte)((int)(input[offset + idx] & 255L));
               --remainder;
               ++idx;
            }
            break;
         case 2:
            while(remainder > 0) {
               this.writeLongBE2(output, input[offset + idx], idx * 2);
               --remainder;
               ++idx;
            }
            break;
         case 3:
            while(remainder > 0) {
               this.writeLongBE3(output, input[offset + idx], idx * 3);
               --remainder;
               ++idx;
            }
            break;
         case 4:
            while(remainder > 0) {
               this.writeLongBE4(output, input[offset + idx], idx * 4);
               --remainder;
               ++idx;
            }
            break;
         case 5:
            while(remainder > 0) {
               this.writeLongBE5(output, input[offset + idx], idx * 5);
               --remainder;
               ++idx;
            }
            break;
         case 6:
            while(remainder > 0) {
               this.writeLongBE6(output, input[offset + idx], idx * 6);
               --remainder;
               ++idx;
            }
            break;
         case 7:
            while(remainder > 0) {
               this.writeLongBE7(output, input[offset + idx], idx * 7);
               --remainder;
               ++idx;
            }
            break;
         case 8:
            while(remainder > 0) {
               this.writeLongBE8(output, input[offset + idx], idx * 8);
               --remainder;
               ++idx;
            }
      }

      int toWrite = numHops * numBytes;
      output.write(this.writeBuffer, 0, toWrite);
   }

   private void writeLongBE(OutputStream output, long[] input, int offset, int numHops, int numBytes) throws IOException {
      switch (numBytes) {
         case 1:
            this.writeBuffer[0] = (byte)((int)(input[offset + 0] & 255L));
            this.writeBuffer[1] = (byte)((int)(input[offset + 1] & 255L));
            this.writeBuffer[2] = (byte)((int)(input[offset + 2] & 255L));
            this.writeBuffer[3] = (byte)((int)(input[offset + 3] & 255L));
            this.writeBuffer[4] = (byte)((int)(input[offset + 4] & 255L));
            this.writeBuffer[5] = (byte)((int)(input[offset + 5] & 255L));
            this.writeBuffer[6] = (byte)((int)(input[offset + 6] & 255L));
            this.writeBuffer[7] = (byte)((int)(input[offset + 7] & 255L));
            break;
         case 2:
            this.writeLongBE2(output, input[offset + 0], 0);
            this.writeLongBE2(output, input[offset + 1], 2);
            this.writeLongBE2(output, input[offset + 2], 4);
            this.writeLongBE2(output, input[offset + 3], 6);
            this.writeLongBE2(output, input[offset + 4], 8);
            this.writeLongBE2(output, input[offset + 5], 10);
            this.writeLongBE2(output, input[offset + 6], 12);
            this.writeLongBE2(output, input[offset + 7], 14);
            break;
         case 3:
            this.writeLongBE3(output, input[offset + 0], 0);
            this.writeLongBE3(output, input[offset + 1], 3);
            this.writeLongBE3(output, input[offset + 2], 6);
            this.writeLongBE3(output, input[offset + 3], 9);
            this.writeLongBE3(output, input[offset + 4], 12);
            this.writeLongBE3(output, input[offset + 5], 15);
            this.writeLongBE3(output, input[offset + 6], 18);
            this.writeLongBE3(output, input[offset + 7], 21);
            break;
         case 4:
            this.writeLongBE4(output, input[offset + 0], 0);
            this.writeLongBE4(output, input[offset + 1], 4);
            this.writeLongBE4(output, input[offset + 2], 8);
            this.writeLongBE4(output, input[offset + 3], 12);
            this.writeLongBE4(output, input[offset + 4], 16);
            this.writeLongBE4(output, input[offset + 5], 20);
            this.writeLongBE4(output, input[offset + 6], 24);
            this.writeLongBE4(output, input[offset + 7], 28);
            break;
         case 5:
            this.writeLongBE5(output, input[offset + 0], 0);
            this.writeLongBE5(output, input[offset + 1], 5);
            this.writeLongBE5(output, input[offset + 2], 10);
            this.writeLongBE5(output, input[offset + 3], 15);
            this.writeLongBE5(output, input[offset + 4], 20);
            this.writeLongBE5(output, input[offset + 5], 25);
            this.writeLongBE5(output, input[offset + 6], 30);
            this.writeLongBE5(output, input[offset + 7], 35);
            break;
         case 6:
            this.writeLongBE6(output, input[offset + 0], 0);
            this.writeLongBE6(output, input[offset + 1], 6);
            this.writeLongBE6(output, input[offset + 2], 12);
            this.writeLongBE6(output, input[offset + 3], 18);
            this.writeLongBE6(output, input[offset + 4], 24);
            this.writeLongBE6(output, input[offset + 5], 30);
            this.writeLongBE6(output, input[offset + 6], 36);
            this.writeLongBE6(output, input[offset + 7], 42);
            break;
         case 7:
            this.writeLongBE7(output, input[offset + 0], 0);
            this.writeLongBE7(output, input[offset + 1], 7);
            this.writeLongBE7(output, input[offset + 2], 14);
            this.writeLongBE7(output, input[offset + 3], 21);
            this.writeLongBE7(output, input[offset + 4], 28);
            this.writeLongBE7(output, input[offset + 5], 35);
            this.writeLongBE7(output, input[offset + 6], 42);
            this.writeLongBE7(output, input[offset + 7], 49);
            break;
         case 8:
            this.writeLongBE8(output, input[offset + 0], 0);
            this.writeLongBE8(output, input[offset + 1], 8);
            this.writeLongBE8(output, input[offset + 2], 16);
            this.writeLongBE8(output, input[offset + 3], 24);
            this.writeLongBE8(output, input[offset + 4], 32);
            this.writeLongBE8(output, input[offset + 5], 40);
            this.writeLongBE8(output, input[offset + 6], 48);
            this.writeLongBE8(output, input[offset + 7], 56);
      }

      int toWrite = numHops * numBytes;
      output.write(this.writeBuffer, 0, toWrite);
   }

   private void writeLongBE2(OutputStream output, long val, int wbOffset) {
      this.writeBuffer[wbOffset + 0] = (byte)((int)(val >>> 8));
      this.writeBuffer[wbOffset + 1] = (byte)((int)(val >>> 0));
   }

   private void writeLongBE3(OutputStream output, long val, int wbOffset) {
      this.writeBuffer[wbOffset + 0] = (byte)((int)(val >>> 16));
      this.writeBuffer[wbOffset + 1] = (byte)((int)(val >>> 8));
      this.writeBuffer[wbOffset + 2] = (byte)((int)(val >>> 0));
   }

   private void writeLongBE4(OutputStream output, long val, int wbOffset) {
      this.writeBuffer[wbOffset + 0] = (byte)((int)(val >>> 24));
      this.writeBuffer[wbOffset + 1] = (byte)((int)(val >>> 16));
      this.writeBuffer[wbOffset + 2] = (byte)((int)(val >>> 8));
      this.writeBuffer[wbOffset + 3] = (byte)((int)(val >>> 0));
   }

   private void writeLongBE5(OutputStream output, long val, int wbOffset) {
      this.writeBuffer[wbOffset + 0] = (byte)((int)(val >>> 32));
      this.writeBuffer[wbOffset + 1] = (byte)((int)(val >>> 24));
      this.writeBuffer[wbOffset + 2] = (byte)((int)(val >>> 16));
      this.writeBuffer[wbOffset + 3] = (byte)((int)(val >>> 8));
      this.writeBuffer[wbOffset + 4] = (byte)((int)(val >>> 0));
   }

   private void writeLongBE6(OutputStream output, long val, int wbOffset) {
      this.writeBuffer[wbOffset + 0] = (byte)((int)(val >>> 40));
      this.writeBuffer[wbOffset + 1] = (byte)((int)(val >>> 32));
      this.writeBuffer[wbOffset + 2] = (byte)((int)(val >>> 24));
      this.writeBuffer[wbOffset + 3] = (byte)((int)(val >>> 16));
      this.writeBuffer[wbOffset + 4] = (byte)((int)(val >>> 8));
      this.writeBuffer[wbOffset + 5] = (byte)((int)(val >>> 0));
   }

   private void writeLongBE7(OutputStream output, long val, int wbOffset) {
      this.writeBuffer[wbOffset + 0] = (byte)((int)(val >>> 48));
      this.writeBuffer[wbOffset + 1] = (byte)((int)(val >>> 40));
      this.writeBuffer[wbOffset + 2] = (byte)((int)(val >>> 32));
      this.writeBuffer[wbOffset + 3] = (byte)((int)(val >>> 24));
      this.writeBuffer[wbOffset + 4] = (byte)((int)(val >>> 16));
      this.writeBuffer[wbOffset + 5] = (byte)((int)(val >>> 8));
      this.writeBuffer[wbOffset + 6] = (byte)((int)(val >>> 0));
   }

   private void writeLongBE8(OutputStream output, long val, int wbOffset) {
      this.writeBuffer[wbOffset + 0] = (byte)((int)(val >>> 56));
      this.writeBuffer[wbOffset + 1] = (byte)((int)(val >>> 48));
      this.writeBuffer[wbOffset + 2] = (byte)((int)(val >>> 40));
      this.writeBuffer[wbOffset + 3] = (byte)((int)(val >>> 32));
      this.writeBuffer[wbOffset + 4] = (byte)((int)(val >>> 24));
      this.writeBuffer[wbOffset + 5] = (byte)((int)(val >>> 16));
      this.writeBuffer[wbOffset + 6] = (byte)((int)(val >>> 8));
      this.writeBuffer[wbOffset + 7] = (byte)((int)(val >>> 0));
   }

   public void readInts(long[] buffer, int offset, int len, int bitSize, InStream input) throws IOException {
      int bitsLeft = 0;
      int current = 0;
      switch (bitSize) {
         case 1:
            this.unrolledUnPack1(buffer, offset, len, input);
            return;
         case 2:
            this.unrolledUnPack2(buffer, offset, len, input);
            return;
         case 4:
            this.unrolledUnPack4(buffer, offset, len, input);
            return;
         case 8:
            this.unrolledUnPack8(buffer, offset, len, input);
            return;
         case 16:
            this.unrolledUnPack16(buffer, offset, len, input);
            return;
         case 24:
            this.unrolledUnPack24(buffer, offset, len, input);
            return;
         case 32:
            this.unrolledUnPack32(buffer, offset, len, input);
            return;
         case 40:
            this.unrolledUnPack40(buffer, offset, len, input);
            return;
         case 48:
            this.unrolledUnPack48(buffer, offset, len, input);
            return;
         case 56:
            this.unrolledUnPack56(buffer, offset, len, input);
            return;
         case 64:
            this.unrolledUnPack64(buffer, offset, len, input);
            return;
         default:
            for(int i = offset; i < offset + len; ++i) {
               long result = 0L;

               int bitsLeftToRead;
               for(bitsLeftToRead = bitSize; bitsLeftToRead > bitsLeft; bitsLeft = 8) {
                  result <<= bitsLeft;
                  result |= (long)(current & (1 << bitsLeft) - 1);
                  bitsLeftToRead -= bitsLeft;
                  current = input.read();
               }

               if (bitsLeftToRead > 0) {
                  result <<= bitsLeftToRead;
                  bitsLeft -= bitsLeftToRead;
                  result |= (long)(current >> bitsLeft & (1 << bitsLeftToRead) - 1);
               }

               buffer[i] = result;
            }

      }
   }

   private void unrolledUnPack1(long[] buffer, int offset, int len, InStream input) throws IOException {
      int numHops = 8;
      int remainder = len % 8;
      int endOffset = offset + len;
      int endUnroll = endOffset - remainder;
      int val = 0;

      for(int i = offset; i < endUnroll; i += 8) {
         val = input.read();
         buffer[i] = (long)(val >>> 7 & 1);
         buffer[i + 1] = (long)(val >>> 6 & 1);
         buffer[i + 2] = (long)(val >>> 5 & 1);
         buffer[i + 3] = (long)(val >>> 4 & 1);
         buffer[i + 4] = (long)(val >>> 3 & 1);
         buffer[i + 5] = (long)(val >>> 2 & 1);
         buffer[i + 6] = (long)(val >>> 1 & 1);
         buffer[i + 7] = (long)(val & 1);
      }

      if (remainder > 0) {
         int startShift = 7;
         val = input.read();

         for(int i = endUnroll; i < endOffset; ++i) {
            buffer[i] = (long)(val >>> startShift & 1);
            --startShift;
         }
      }

   }

   private void unrolledUnPack2(long[] buffer, int offset, int len, InStream input) throws IOException {
      int numHops = 4;
      int remainder = len % 4;
      int endOffset = offset + len;
      int endUnroll = endOffset - remainder;
      int val = 0;

      for(int i = offset; i < endUnroll; i += 4) {
         val = input.read();
         buffer[i] = (long)(val >>> 6 & 3);
         buffer[i + 1] = (long)(val >>> 4 & 3);
         buffer[i + 2] = (long)(val >>> 2 & 3);
         buffer[i + 3] = (long)(val & 3);
      }

      if (remainder > 0) {
         int startShift = 6;
         val = input.read();

         for(int i = endUnroll; i < endOffset; ++i) {
            buffer[i] = (long)(val >>> startShift & 3);
            startShift -= 2;
         }
      }

   }

   private void unrolledUnPack4(long[] buffer, int offset, int len, InStream input) throws IOException {
      int numHops = 2;
      int remainder = len % 2;
      int endOffset = offset + len;
      int endUnroll = endOffset - remainder;
      int val = 0;

      for(int i = offset; i < endUnroll; i += 2) {
         val = input.read();
         buffer[i] = (long)(val >>> 4 & 15);
         buffer[i + 1] = (long)(val & 15);
      }

      if (remainder > 0) {
         int startShift = 4;
         val = input.read();

         for(int i = endUnroll; i < endOffset; ++i) {
            buffer[i] = (long)(val >>> startShift & 15);
            startShift -= 4;
         }
      }

   }

   private void unrolledUnPack8(long[] buffer, int offset, int len, InStream input) throws IOException {
      this.unrolledUnPackBytes(buffer, offset, len, input, 1);
   }

   private void unrolledUnPack16(long[] buffer, int offset, int len, InStream input) throws IOException {
      this.unrolledUnPackBytes(buffer, offset, len, input, 2);
   }

   private void unrolledUnPack24(long[] buffer, int offset, int len, InStream input) throws IOException {
      this.unrolledUnPackBytes(buffer, offset, len, input, 3);
   }

   private void unrolledUnPack32(long[] buffer, int offset, int len, InStream input) throws IOException {
      this.unrolledUnPackBytes(buffer, offset, len, input, 4);
   }

   private void unrolledUnPack40(long[] buffer, int offset, int len, InStream input) throws IOException {
      this.unrolledUnPackBytes(buffer, offset, len, input, 5);
   }

   private void unrolledUnPack48(long[] buffer, int offset, int len, InStream input) throws IOException {
      this.unrolledUnPackBytes(buffer, offset, len, input, 6);
   }

   private void unrolledUnPack56(long[] buffer, int offset, int len, InStream input) throws IOException {
      this.unrolledUnPackBytes(buffer, offset, len, input, 7);
   }

   private void unrolledUnPack64(long[] buffer, int offset, int len, InStream input) throws IOException {
      this.unrolledUnPackBytes(buffer, offset, len, input, 8);
   }

   private void unrolledUnPackBytes(long[] buffer, int offset, int len, InStream input, int numBytes) throws IOException {
      int numHops = 8;
      int remainder = len % 8;
      int endOffset = offset + len;
      int endUnroll = endOffset - remainder;

      int i;
      for(i = offset; i < endUnroll; i += 8) {
         this.readLongBE(input, buffer, i, 8, numBytes);
      }

      if (remainder > 0) {
         this.readRemainingLongs(buffer, i, input, remainder, numBytes);
      }

   }

   private void readRemainingLongs(long[] buffer, int offset, InStream input, int remainder, int numBytes) throws IOException {
      int toRead = remainder * numBytes;

      for(int bytesRead = input.read(this.readBuffer, 0, toRead); bytesRead != toRead; bytesRead += input.read(this.readBuffer, bytesRead, toRead - bytesRead)) {
      }

      int idx = 0;
      switch (numBytes) {
         case 1:
            while(remainder > 0) {
               buffer[offset++] = (long)(this.readBuffer[idx] & 255);
               --remainder;
               ++idx;
            }
            break;
         case 2:
            while(remainder > 0) {
               buffer[offset++] = this.readLongBE2(input, idx * 2);
               --remainder;
               ++idx;
            }
            break;
         case 3:
            while(remainder > 0) {
               buffer[offset++] = this.readLongBE3(input, idx * 3);
               --remainder;
               ++idx;
            }
            break;
         case 4:
            while(remainder > 0) {
               buffer[offset++] = this.readLongBE4(input, idx * 4);
               --remainder;
               ++idx;
            }
            break;
         case 5:
            while(remainder > 0) {
               buffer[offset++] = this.readLongBE5(input, idx * 5);
               --remainder;
               ++idx;
            }
            break;
         case 6:
            while(remainder > 0) {
               buffer[offset++] = this.readLongBE6(input, idx * 6);
               --remainder;
               ++idx;
            }
            break;
         case 7:
            while(remainder > 0) {
               buffer[offset++] = this.readLongBE7(input, idx * 7);
               --remainder;
               ++idx;
            }
            break;
         case 8:
            while(remainder > 0) {
               buffer[offset++] = this.readLongBE8(input, idx * 8);
               --remainder;
               ++idx;
            }
      }

   }

   private void readLongBE(InStream in, long[] buffer, int start, int numHops, int numBytes) throws IOException {
      int toRead = numHops * numBytes;

      for(int bytesRead = in.read(this.readBuffer, 0, toRead); bytesRead != toRead; bytesRead += in.read(this.readBuffer, bytesRead, toRead - bytesRead)) {
      }

      switch (numBytes) {
         case 1:
            buffer[start + 0] = (long)(this.readBuffer[0] & 255);
            buffer[start + 1] = (long)(this.readBuffer[1] & 255);
            buffer[start + 2] = (long)(this.readBuffer[2] & 255);
            buffer[start + 3] = (long)(this.readBuffer[3] & 255);
            buffer[start + 4] = (long)(this.readBuffer[4] & 255);
            buffer[start + 5] = (long)(this.readBuffer[5] & 255);
            buffer[start + 6] = (long)(this.readBuffer[6] & 255);
            buffer[start + 7] = (long)(this.readBuffer[7] & 255);
            break;
         case 2:
            buffer[start + 0] = this.readLongBE2(in, 0);
            buffer[start + 1] = this.readLongBE2(in, 2);
            buffer[start + 2] = this.readLongBE2(in, 4);
            buffer[start + 3] = this.readLongBE2(in, 6);
            buffer[start + 4] = this.readLongBE2(in, 8);
            buffer[start + 5] = this.readLongBE2(in, 10);
            buffer[start + 6] = this.readLongBE2(in, 12);
            buffer[start + 7] = this.readLongBE2(in, 14);
            break;
         case 3:
            buffer[start + 0] = this.readLongBE3(in, 0);
            buffer[start + 1] = this.readLongBE3(in, 3);
            buffer[start + 2] = this.readLongBE3(in, 6);
            buffer[start + 3] = this.readLongBE3(in, 9);
            buffer[start + 4] = this.readLongBE3(in, 12);
            buffer[start + 5] = this.readLongBE3(in, 15);
            buffer[start + 6] = this.readLongBE3(in, 18);
            buffer[start + 7] = this.readLongBE3(in, 21);
            break;
         case 4:
            buffer[start + 0] = this.readLongBE4(in, 0);
            buffer[start + 1] = this.readLongBE4(in, 4);
            buffer[start + 2] = this.readLongBE4(in, 8);
            buffer[start + 3] = this.readLongBE4(in, 12);
            buffer[start + 4] = this.readLongBE4(in, 16);
            buffer[start + 5] = this.readLongBE4(in, 20);
            buffer[start + 6] = this.readLongBE4(in, 24);
            buffer[start + 7] = this.readLongBE4(in, 28);
            break;
         case 5:
            buffer[start + 0] = this.readLongBE5(in, 0);
            buffer[start + 1] = this.readLongBE5(in, 5);
            buffer[start + 2] = this.readLongBE5(in, 10);
            buffer[start + 3] = this.readLongBE5(in, 15);
            buffer[start + 4] = this.readLongBE5(in, 20);
            buffer[start + 5] = this.readLongBE5(in, 25);
            buffer[start + 6] = this.readLongBE5(in, 30);
            buffer[start + 7] = this.readLongBE5(in, 35);
            break;
         case 6:
            buffer[start + 0] = this.readLongBE6(in, 0);
            buffer[start + 1] = this.readLongBE6(in, 6);
            buffer[start + 2] = this.readLongBE6(in, 12);
            buffer[start + 3] = this.readLongBE6(in, 18);
            buffer[start + 4] = this.readLongBE6(in, 24);
            buffer[start + 5] = this.readLongBE6(in, 30);
            buffer[start + 6] = this.readLongBE6(in, 36);
            buffer[start + 7] = this.readLongBE6(in, 42);
            break;
         case 7:
            buffer[start + 0] = this.readLongBE7(in, 0);
            buffer[start + 1] = this.readLongBE7(in, 7);
            buffer[start + 2] = this.readLongBE7(in, 14);
            buffer[start + 3] = this.readLongBE7(in, 21);
            buffer[start + 4] = this.readLongBE7(in, 28);
            buffer[start + 5] = this.readLongBE7(in, 35);
            buffer[start + 6] = this.readLongBE7(in, 42);
            buffer[start + 7] = this.readLongBE7(in, 49);
            break;
         case 8:
            buffer[start + 0] = this.readLongBE8(in, 0);
            buffer[start + 1] = this.readLongBE8(in, 8);
            buffer[start + 2] = this.readLongBE8(in, 16);
            buffer[start + 3] = this.readLongBE8(in, 24);
            buffer[start + 4] = this.readLongBE8(in, 32);
            buffer[start + 5] = this.readLongBE8(in, 40);
            buffer[start + 6] = this.readLongBE8(in, 48);
            buffer[start + 7] = this.readLongBE8(in, 56);
      }

   }

   private long readLongBE2(InStream in, int rbOffset) {
      return (long)(((this.readBuffer[rbOffset] & 255) << 8) + ((this.readBuffer[rbOffset + 1] & 255) << 0));
   }

   private long readLongBE3(InStream in, int rbOffset) {
      return (long)(((this.readBuffer[rbOffset] & 255) << 16) + ((this.readBuffer[rbOffset + 1] & 255) << 8) + ((this.readBuffer[rbOffset + 2] & 255) << 0));
   }

   private long readLongBE4(InStream in, int rbOffset) {
      return ((long)(this.readBuffer[rbOffset] & 255) << 24) + (long)((this.readBuffer[rbOffset + 1] & 255) << 16) + (long)((this.readBuffer[rbOffset + 2] & 255) << 8) + (long)((this.readBuffer[rbOffset + 3] & 255) << 0);
   }

   private long readLongBE5(InStream in, int rbOffset) {
      return ((long)(this.readBuffer[rbOffset] & 255) << 32) + ((long)(this.readBuffer[rbOffset + 1] & 255) << 24) + (long)((this.readBuffer[rbOffset + 2] & 255) << 16) + (long)((this.readBuffer[rbOffset + 3] & 255) << 8) + (long)((this.readBuffer[rbOffset + 4] & 255) << 0);
   }

   private long readLongBE6(InStream in, int rbOffset) {
      return ((long)(this.readBuffer[rbOffset] & 255) << 40) + ((long)(this.readBuffer[rbOffset + 1] & 255) << 32) + ((long)(this.readBuffer[rbOffset + 2] & 255) << 24) + (long)((this.readBuffer[rbOffset + 3] & 255) << 16) + (long)((this.readBuffer[rbOffset + 4] & 255) << 8) + (long)((this.readBuffer[rbOffset + 5] & 255) << 0);
   }

   private long readLongBE7(InStream in, int rbOffset) {
      return ((long)(this.readBuffer[rbOffset] & 255) << 48) + ((long)(this.readBuffer[rbOffset + 1] & 255) << 40) + ((long)(this.readBuffer[rbOffset + 2] & 255) << 32) + ((long)(this.readBuffer[rbOffset + 3] & 255) << 24) + (long)((this.readBuffer[rbOffset + 4] & 255) << 16) + (long)((this.readBuffer[rbOffset + 5] & 255) << 8) + (long)((this.readBuffer[rbOffset + 6] & 255) << 0);
   }

   private long readLongBE8(InStream in, int rbOffset) {
      return ((long)(this.readBuffer[rbOffset] & 255) << 56) + ((long)(this.readBuffer[rbOffset + 1] & 255) << 48) + ((long)(this.readBuffer[rbOffset + 2] & 255) << 40) + ((long)(this.readBuffer[rbOffset + 3] & 255) << 32) + ((long)(this.readBuffer[rbOffset + 4] & 255) << 24) + (long)((this.readBuffer[rbOffset + 5] & 255) << 16) + (long)((this.readBuffer[rbOffset + 6] & 255) << 8) + (long)((this.readBuffer[rbOffset + 7] & 255) << 0);
   }

   public boolean isSafeSubtract(long left, long right) {
      return (left ^ right) >= 0L || (left ^ left - right) >= 0L;
   }

   public static double convertFromUtc(TimeZone local, double time) {
      int offset = local.getOffset((long)(time * (double)1000.0F) - (long)local.getRawOffset());
      return time - (double)offset / (double)1000.0F;
   }

   public static long convertFromUtc(TimeZone local, long time) {
      int offset = local.getOffset(time - (long)local.getRawOffset());
      return time - (long)offset;
   }

   public static long convertToUtc(TimeZone local, long time) {
      int offset = local.getOffset(time);
      return time + (long)offset;
   }

   public static StreamOptions getCustomizedCodec(StreamOptions base, OrcFile.CompressionStrategy strategy, OrcProto.Stream.Kind kind) {
      if (base.getCodec() != null) {
         CompressionCodec.Options options = base.getCodecOptions();
         switch (kind) {
            case BLOOM_FILTER:
            case DATA:
            case DICTIONARY_DATA:
            case BLOOM_FILTER_UTF8:
               options = options.copy().setData(CompressionCodec.DataKind.TEXT);
               if (strategy == OrcFile.CompressionStrategy.SPEED) {
                  options.setSpeed(CompressionCodec.SpeedModifier.FAST);
               } else {
                  options.setSpeed(CompressionCodec.SpeedModifier.DEFAULT);
               }
               break;
            case LENGTH:
            case DICTIONARY_COUNT:
            case PRESENT:
            case ROW_INDEX:
            case SECONDARY:
               options = options.copy().setSpeed(CompressionCodec.SpeedModifier.FASTEST).setData(CompressionCodec.DataKind.BINARY);
               break;
            default:
               LOG.info("Missing ORC compression modifiers for " + String.valueOf(kind));
         }

         if (!base.getCodecOptions().equals(options)) {
            StreamOptions result = (new StreamOptions(base)).withCodec(base.getCodec(), options);
            return result;
         }
      }

      return base;
   }

   public static long convertBetweenTimezones(TimeZone writer, TimeZone reader, long millis) {
      long writerOffset = (long)writer.getOffset(millis);
      long readerOffset = (long)reader.getOffset(millis);
      long adjustedMillis = millis + writerOffset - readerOffset;
      long adjustedReader = (long)reader.getOffset(adjustedMillis);
      return writerOffset - adjustedReader;
   }

   public static String bytesVectorToString(BytesColumnVector vector, int elementNum) {
      if (vector.isRepeating) {
         elementNum = 0;
      }

      return !vector.noNulls && vector.isNull[elementNum] ? null : new String(vector.vector[elementNum], vector.start[elementNum], vector.length[elementNum], StandardCharsets.UTF_8);
   }

   public static Date parseDateFromString(String string) {
      try {
         Date value = Date.valueOf(string);
         return value;
      } catch (IllegalArgumentException var2) {
         return null;
      }
   }

   public static enum FixedBitSizes {
      ONE,
      TWO,
      THREE,
      FOUR,
      FIVE,
      SIX,
      SEVEN,
      EIGHT,
      NINE,
      TEN,
      ELEVEN,
      TWELVE,
      THIRTEEN,
      FOURTEEN,
      FIFTEEN,
      SIXTEEN,
      SEVENTEEN,
      EIGHTEEN,
      NINETEEN,
      TWENTY,
      TWENTYONE,
      TWENTYTWO,
      TWENTYTHREE,
      TWENTYFOUR,
      TWENTYSIX,
      TWENTYEIGHT,
      THIRTY,
      THIRTYTWO,
      FORTY,
      FORTYEIGHT,
      FIFTYSIX,
      SIXTYFOUR;

      // $FF: synthetic method
      private static FixedBitSizes[] $values() {
         return new FixedBitSizes[]{ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, ELEVEN, TWELVE, THIRTEEN, FOURTEEN, FIFTEEN, SIXTEEN, SEVENTEEN, EIGHTEEN, NINETEEN, TWENTY, TWENTYONE, TWENTYTWO, TWENTYTHREE, TWENTYFOUR, TWENTYSIX, TWENTYEIGHT, THIRTY, THIRTYTWO, FORTY, FORTYEIGHT, FIFTYSIX, SIXTYFOUR};
      }
   }
}
