package org.apache.parquet.column.values.bitpacking;

import java.nio.ByteBuffer;

public abstract class ByteBitPackingLE {
   private static final BytePacker[] packers = new BytePacker[33];
   public static final BytePackerFactory factory;

   static {
      packers[0] = new Packer0();
      packers[1] = new Packer1();
      packers[2] = new Packer2();
      packers[3] = new Packer3();
      packers[4] = new Packer4();
      packers[5] = new Packer5();
      packers[6] = new Packer6();
      packers[7] = new Packer7();
      packers[8] = new Packer8();
      packers[9] = new Packer9();
      packers[10] = new Packer10();
      packers[11] = new Packer11();
      packers[12] = new Packer12();
      packers[13] = new Packer13();
      packers[14] = new Packer14();
      packers[15] = new Packer15();
      packers[16] = new Packer16();
      packers[17] = new Packer17();
      packers[18] = new Packer18();
      packers[19] = new Packer19();
      packers[20] = new Packer20();
      packers[21] = new Packer21();
      packers[22] = new Packer22();
      packers[23] = new Packer23();
      packers[24] = new Packer24();
      packers[25] = new Packer25();
      packers[26] = new Packer26();
      packers[27] = new Packer27();
      packers[28] = new Packer28();
      packers[29] = new Packer29();
      packers[30] = new Packer30();
      packers[31] = new Packer31();
      packers[32] = new Packer32();
      factory = new BytePackerFactory() {
         public BytePacker newBytePacker(int bitWidth) {
            return ByteBitPackingLE.packers[bitWidth];
         }
      };
   }

   private static final class Packer0 extends BytePacker {
      private Packer0() {
         super(0);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
      }
   }

   private static final class Packer1 extends BytePacker {
      private Packer1() {
         super(1);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 1 | (in[1 + inPos] & 1) << 1 | (in[2 + inPos] & 1) << 2 | (in[3 + inPos] & 1) << 3 | (in[4 + inPos] & 1) << 4 | (in[5 + inPos] & 1) << 5 | (in[6 + inPos] & 1) << 6 | (in[7 + inPos] & 1) << 7) & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 1 | (in[1 + inPos] & 1) << 1 | (in[2 + inPos] & 1) << 2 | (in[3 + inPos] & 1) << 3 | (in[4 + inPos] & 1) << 4 | (in[5 + inPos] & 1) << 5 | (in[6 + inPos] & 1) << 6 | (in[7 + inPos] & 1) << 7) & 255);
         out[1 + outPos] = (byte)((in[8 + inPos] & 1 | (in[9 + inPos] & 1) << 1 | (in[10 + inPos] & 1) << 2 | (in[11 + inPos] & 1) << 3 | (in[12 + inPos] & 1) << 4 | (in[13 + inPos] & 1) << 5 | (in[14 + inPos] & 1) << 6 | (in[15 + inPos] & 1) << 7) & 255);
         out[2 + outPos] = (byte)((in[16 + inPos] & 1 | (in[17 + inPos] & 1) << 1 | (in[18 + inPos] & 1) << 2 | (in[19 + inPos] & 1) << 3 | (in[20 + inPos] & 1) << 4 | (in[21 + inPos] & 1) << 5 | (in[22 + inPos] & 1) << 6 | (in[23 + inPos] & 1) << 7) & 255);
         out[3 + outPos] = (byte)((in[24 + inPos] & 1 | (in[25 + inPos] & 1) << 1 | (in[26 + inPos] & 1) << 2 | (in[27 + inPos] & 1) << 3 | (in[28 + inPos] & 1) << 4 | (in[29 + inPos] & 1) << 5 | (in[30 + inPos] & 1) << 6 | (in[31 + inPos] & 1) << 7) & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 1;
         out[1 + outPos] = in[0 + inPos] >> 1 & 1;
         out[2 + outPos] = in[0 + inPos] >> 2 & 1;
         out[3 + outPos] = in[0 + inPos] >> 3 & 1;
         out[4 + outPos] = in[0 + inPos] >> 4 & 1;
         out[5 + outPos] = in[0 + inPos] >> 5 & 1;
         out[6 + outPos] = in[0 + inPos] >> 6 & 1;
         out[7 + outPos] = in[0 + inPos] >> 7 & 1;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 1;
         out[1 + outPos] = in.get(0 + inPos) >> 1 & 1;
         out[2 + outPos] = in.get(0 + inPos) >> 2 & 1;
         out[3 + outPos] = in.get(0 + inPos) >> 3 & 1;
         out[4 + outPos] = in.get(0 + inPos) >> 4 & 1;
         out[5 + outPos] = in.get(0 + inPos) >> 5 & 1;
         out[6 + outPos] = in.get(0 + inPos) >> 6 & 1;
         out[7 + outPos] = in.get(0 + inPos) >> 7 & 1;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 1;
         out[1 + outPos] = in[0 + inPos] >> 1 & 1;
         out[2 + outPos] = in[0 + inPos] >> 2 & 1;
         out[3 + outPos] = in[0 + inPos] >> 3 & 1;
         out[4 + outPos] = in[0 + inPos] >> 4 & 1;
         out[5 + outPos] = in[0 + inPos] >> 5 & 1;
         out[6 + outPos] = in[0 + inPos] >> 6 & 1;
         out[7 + outPos] = in[0 + inPos] >> 7 & 1;
         out[8 + outPos] = in[1 + inPos] & 1;
         out[9 + outPos] = in[1 + inPos] >> 1 & 1;
         out[10 + outPos] = in[1 + inPos] >> 2 & 1;
         out[11 + outPos] = in[1 + inPos] >> 3 & 1;
         out[12 + outPos] = in[1 + inPos] >> 4 & 1;
         out[13 + outPos] = in[1 + inPos] >> 5 & 1;
         out[14 + outPos] = in[1 + inPos] >> 6 & 1;
         out[15 + outPos] = in[1 + inPos] >> 7 & 1;
         out[16 + outPos] = in[2 + inPos] & 1;
         out[17 + outPos] = in[2 + inPos] >> 1 & 1;
         out[18 + outPos] = in[2 + inPos] >> 2 & 1;
         out[19 + outPos] = in[2 + inPos] >> 3 & 1;
         out[20 + outPos] = in[2 + inPos] >> 4 & 1;
         out[21 + outPos] = in[2 + inPos] >> 5 & 1;
         out[22 + outPos] = in[2 + inPos] >> 6 & 1;
         out[23 + outPos] = in[2 + inPos] >> 7 & 1;
         out[24 + outPos] = in[3 + inPos] & 1;
         out[25 + outPos] = in[3 + inPos] >> 1 & 1;
         out[26 + outPos] = in[3 + inPos] >> 2 & 1;
         out[27 + outPos] = in[3 + inPos] >> 3 & 1;
         out[28 + outPos] = in[3 + inPos] >> 4 & 1;
         out[29 + outPos] = in[3 + inPos] >> 5 & 1;
         out[30 + outPos] = in[3 + inPos] >> 6 & 1;
         out[31 + outPos] = in[3 + inPos] >> 7 & 1;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 1;
         out[1 + outPos] = in.get(0 + inPos) >> 1 & 1;
         out[2 + outPos] = in.get(0 + inPos) >> 2 & 1;
         out[3 + outPos] = in.get(0 + inPos) >> 3 & 1;
         out[4 + outPos] = in.get(0 + inPos) >> 4 & 1;
         out[5 + outPos] = in.get(0 + inPos) >> 5 & 1;
         out[6 + outPos] = in.get(0 + inPos) >> 6 & 1;
         out[7 + outPos] = in.get(0 + inPos) >> 7 & 1;
         out[8 + outPos] = in.get(1 + inPos) & 1;
         out[9 + outPos] = in.get(1 + inPos) >> 1 & 1;
         out[10 + outPos] = in.get(1 + inPos) >> 2 & 1;
         out[11 + outPos] = in.get(1 + inPos) >> 3 & 1;
         out[12 + outPos] = in.get(1 + inPos) >> 4 & 1;
         out[13 + outPos] = in.get(1 + inPos) >> 5 & 1;
         out[14 + outPos] = in.get(1 + inPos) >> 6 & 1;
         out[15 + outPos] = in.get(1 + inPos) >> 7 & 1;
         out[16 + outPos] = in.get(2 + inPos) & 1;
         out[17 + outPos] = in.get(2 + inPos) >> 1 & 1;
         out[18 + outPos] = in.get(2 + inPos) >> 2 & 1;
         out[19 + outPos] = in.get(2 + inPos) >> 3 & 1;
         out[20 + outPos] = in.get(2 + inPos) >> 4 & 1;
         out[21 + outPos] = in.get(2 + inPos) >> 5 & 1;
         out[22 + outPos] = in.get(2 + inPos) >> 6 & 1;
         out[23 + outPos] = in.get(2 + inPos) >> 7 & 1;
         out[24 + outPos] = in.get(3 + inPos) & 1;
         out[25 + outPos] = in.get(3 + inPos) >> 1 & 1;
         out[26 + outPos] = in.get(3 + inPos) >> 2 & 1;
         out[27 + outPos] = in.get(3 + inPos) >> 3 & 1;
         out[28 + outPos] = in.get(3 + inPos) >> 4 & 1;
         out[29 + outPos] = in.get(3 + inPos) >> 5 & 1;
         out[30 + outPos] = in.get(3 + inPos) >> 6 & 1;
         out[31 + outPos] = in.get(3 + inPos) >> 7 & 1;
      }
   }

   private static final class Packer2 extends BytePacker {
      private Packer2() {
         super(2);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 3 | (in[1 + inPos] & 3) << 2 | (in[2 + inPos] & 3) << 4 | (in[3 + inPos] & 3) << 6) & 255);
         out[1 + outPos] = (byte)((in[4 + inPos] & 3 | (in[5 + inPos] & 3) << 2 | (in[6 + inPos] & 3) << 4 | (in[7 + inPos] & 3) << 6) & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 3 | (in[1 + inPos] & 3) << 2 | (in[2 + inPos] & 3) << 4 | (in[3 + inPos] & 3) << 6) & 255);
         out[1 + outPos] = (byte)((in[4 + inPos] & 3 | (in[5 + inPos] & 3) << 2 | (in[6 + inPos] & 3) << 4 | (in[7 + inPos] & 3) << 6) & 255);
         out[2 + outPos] = (byte)((in[8 + inPos] & 3 | (in[9 + inPos] & 3) << 2 | (in[10 + inPos] & 3) << 4 | (in[11 + inPos] & 3) << 6) & 255);
         out[3 + outPos] = (byte)((in[12 + inPos] & 3 | (in[13 + inPos] & 3) << 2 | (in[14 + inPos] & 3) << 4 | (in[15 + inPos] & 3) << 6) & 255);
         out[4 + outPos] = (byte)((in[16 + inPos] & 3 | (in[17 + inPos] & 3) << 2 | (in[18 + inPos] & 3) << 4 | (in[19 + inPos] & 3) << 6) & 255);
         out[5 + outPos] = (byte)((in[20 + inPos] & 3 | (in[21 + inPos] & 3) << 2 | (in[22 + inPos] & 3) << 4 | (in[23 + inPos] & 3) << 6) & 255);
         out[6 + outPos] = (byte)((in[24 + inPos] & 3 | (in[25 + inPos] & 3) << 2 | (in[26 + inPos] & 3) << 4 | (in[27 + inPos] & 3) << 6) & 255);
         out[7 + outPos] = (byte)((in[28 + inPos] & 3 | (in[29 + inPos] & 3) << 2 | (in[30 + inPos] & 3) << 4 | (in[31 + inPos] & 3) << 6) & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 3;
         out[1 + outPos] = in[0 + inPos] >> 2 & 3;
         out[2 + outPos] = in[0 + inPos] >> 4 & 3;
         out[3 + outPos] = in[0 + inPos] >> 6 & 3;
         out[4 + outPos] = in[1 + inPos] & 3;
         out[5 + outPos] = in[1 + inPos] >> 2 & 3;
         out[6 + outPos] = in[1 + inPos] >> 4 & 3;
         out[7 + outPos] = in[1 + inPos] >> 6 & 3;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 3;
         out[1 + outPos] = in.get(0 + inPos) >> 2 & 3;
         out[2 + outPos] = in.get(0 + inPos) >> 4 & 3;
         out[3 + outPos] = in.get(0 + inPos) >> 6 & 3;
         out[4 + outPos] = in.get(1 + inPos) & 3;
         out[5 + outPos] = in.get(1 + inPos) >> 2 & 3;
         out[6 + outPos] = in.get(1 + inPos) >> 4 & 3;
         out[7 + outPos] = in.get(1 + inPos) >> 6 & 3;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 3;
         out[1 + outPos] = in[0 + inPos] >> 2 & 3;
         out[2 + outPos] = in[0 + inPos] >> 4 & 3;
         out[3 + outPos] = in[0 + inPos] >> 6 & 3;
         out[4 + outPos] = in[1 + inPos] & 3;
         out[5 + outPos] = in[1 + inPos] >> 2 & 3;
         out[6 + outPos] = in[1 + inPos] >> 4 & 3;
         out[7 + outPos] = in[1 + inPos] >> 6 & 3;
         out[8 + outPos] = in[2 + inPos] & 3;
         out[9 + outPos] = in[2 + inPos] >> 2 & 3;
         out[10 + outPos] = in[2 + inPos] >> 4 & 3;
         out[11 + outPos] = in[2 + inPos] >> 6 & 3;
         out[12 + outPos] = in[3 + inPos] & 3;
         out[13 + outPos] = in[3 + inPos] >> 2 & 3;
         out[14 + outPos] = in[3 + inPos] >> 4 & 3;
         out[15 + outPos] = in[3 + inPos] >> 6 & 3;
         out[16 + outPos] = in[4 + inPos] & 3;
         out[17 + outPos] = in[4 + inPos] >> 2 & 3;
         out[18 + outPos] = in[4 + inPos] >> 4 & 3;
         out[19 + outPos] = in[4 + inPos] >> 6 & 3;
         out[20 + outPos] = in[5 + inPos] & 3;
         out[21 + outPos] = in[5 + inPos] >> 2 & 3;
         out[22 + outPos] = in[5 + inPos] >> 4 & 3;
         out[23 + outPos] = in[5 + inPos] >> 6 & 3;
         out[24 + outPos] = in[6 + inPos] & 3;
         out[25 + outPos] = in[6 + inPos] >> 2 & 3;
         out[26 + outPos] = in[6 + inPos] >> 4 & 3;
         out[27 + outPos] = in[6 + inPos] >> 6 & 3;
         out[28 + outPos] = in[7 + inPos] & 3;
         out[29 + outPos] = in[7 + inPos] >> 2 & 3;
         out[30 + outPos] = in[7 + inPos] >> 4 & 3;
         out[31 + outPos] = in[7 + inPos] >> 6 & 3;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 3;
         out[1 + outPos] = in.get(0 + inPos) >> 2 & 3;
         out[2 + outPos] = in.get(0 + inPos) >> 4 & 3;
         out[3 + outPos] = in.get(0 + inPos) >> 6 & 3;
         out[4 + outPos] = in.get(1 + inPos) & 3;
         out[5 + outPos] = in.get(1 + inPos) >> 2 & 3;
         out[6 + outPos] = in.get(1 + inPos) >> 4 & 3;
         out[7 + outPos] = in.get(1 + inPos) >> 6 & 3;
         out[8 + outPos] = in.get(2 + inPos) & 3;
         out[9 + outPos] = in.get(2 + inPos) >> 2 & 3;
         out[10 + outPos] = in.get(2 + inPos) >> 4 & 3;
         out[11 + outPos] = in.get(2 + inPos) >> 6 & 3;
         out[12 + outPos] = in.get(3 + inPos) & 3;
         out[13 + outPos] = in.get(3 + inPos) >> 2 & 3;
         out[14 + outPos] = in.get(3 + inPos) >> 4 & 3;
         out[15 + outPos] = in.get(3 + inPos) >> 6 & 3;
         out[16 + outPos] = in.get(4 + inPos) & 3;
         out[17 + outPos] = in.get(4 + inPos) >> 2 & 3;
         out[18 + outPos] = in.get(4 + inPos) >> 4 & 3;
         out[19 + outPos] = in.get(4 + inPos) >> 6 & 3;
         out[20 + outPos] = in.get(5 + inPos) & 3;
         out[21 + outPos] = in.get(5 + inPos) >> 2 & 3;
         out[22 + outPos] = in.get(5 + inPos) >> 4 & 3;
         out[23 + outPos] = in.get(5 + inPos) >> 6 & 3;
         out[24 + outPos] = in.get(6 + inPos) & 3;
         out[25 + outPos] = in.get(6 + inPos) >> 2 & 3;
         out[26 + outPos] = in.get(6 + inPos) >> 4 & 3;
         out[27 + outPos] = in.get(6 + inPos) >> 6 & 3;
         out[28 + outPos] = in.get(7 + inPos) & 3;
         out[29 + outPos] = in.get(7 + inPos) >> 2 & 3;
         out[30 + outPos] = in.get(7 + inPos) >> 4 & 3;
         out[31 + outPos] = in.get(7 + inPos) >> 6 & 3;
      }
   }

   private static final class Packer3 extends BytePacker {
      private Packer3() {
         super(3);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 7 | (in[1 + inPos] & 7) << 3 | (in[2 + inPos] & 7) << 6) & 255);
         out[1 + outPos] = (byte)(((in[2 + inPos] & 7) >>> 2 | (in[3 + inPos] & 7) << 1 | (in[4 + inPos] & 7) << 4 | (in[5 + inPos] & 7) << 7) & 255);
         out[2 + outPos] = (byte)(((in[5 + inPos] & 7) >>> 1 | (in[6 + inPos] & 7) << 2 | (in[7 + inPos] & 7) << 5) & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 7 | (in[1 + inPos] & 7) << 3 | (in[2 + inPos] & 7) << 6) & 255);
         out[1 + outPos] = (byte)(((in[2 + inPos] & 7) >>> 2 | (in[3 + inPos] & 7) << 1 | (in[4 + inPos] & 7) << 4 | (in[5 + inPos] & 7) << 7) & 255);
         out[2 + outPos] = (byte)(((in[5 + inPos] & 7) >>> 1 | (in[6 + inPos] & 7) << 2 | (in[7 + inPos] & 7) << 5) & 255);
         out[3 + outPos] = (byte)((in[8 + inPos] & 7 | (in[9 + inPos] & 7) << 3 | (in[10 + inPos] & 7) << 6) & 255);
         out[4 + outPos] = (byte)(((in[10 + inPos] & 7) >>> 2 | (in[11 + inPos] & 7) << 1 | (in[12 + inPos] & 7) << 4 | (in[13 + inPos] & 7) << 7) & 255);
         out[5 + outPos] = (byte)(((in[13 + inPos] & 7) >>> 1 | (in[14 + inPos] & 7) << 2 | (in[15 + inPos] & 7) << 5) & 255);
         out[6 + outPos] = (byte)((in[16 + inPos] & 7 | (in[17 + inPos] & 7) << 3 | (in[18 + inPos] & 7) << 6) & 255);
         out[7 + outPos] = (byte)(((in[18 + inPos] & 7) >>> 2 | (in[19 + inPos] & 7) << 1 | (in[20 + inPos] & 7) << 4 | (in[21 + inPos] & 7) << 7) & 255);
         out[8 + outPos] = (byte)(((in[21 + inPos] & 7) >>> 1 | (in[22 + inPos] & 7) << 2 | (in[23 + inPos] & 7) << 5) & 255);
         out[9 + outPos] = (byte)((in[24 + inPos] & 7 | (in[25 + inPos] & 7) << 3 | (in[26 + inPos] & 7) << 6) & 255);
         out[10 + outPos] = (byte)(((in[26 + inPos] & 7) >>> 2 | (in[27 + inPos] & 7) << 1 | (in[28 + inPos] & 7) << 4 | (in[29 + inPos] & 7) << 7) & 255);
         out[11 + outPos] = (byte)(((in[29 + inPos] & 7) >>> 1 | (in[30 + inPos] & 7) << 2 | (in[31 + inPos] & 7) << 5) & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 7;
         out[1 + outPos] = in[0 + inPos] >> 3 & 7;
         out[2 + outPos] = in[0 + inPos] >> 6 & 3 | in[1 + inPos] << 2 & 7;
         out[3 + outPos] = in[1 + inPos] >> 1 & 7;
         out[4 + outPos] = in[1 + inPos] >> 4 & 7;
         out[5 + outPos] = in[1 + inPos] >> 7 & 1 | in[2 + inPos] << 1 & 7;
         out[6 + outPos] = in[2 + inPos] >> 2 & 7;
         out[7 + outPos] = in[2 + inPos] >> 5 & 7;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 7;
         out[1 + outPos] = in.get(0 + inPos) >> 3 & 7;
         out[2 + outPos] = in.get(0 + inPos) >> 6 & 3 | in.get(1 + inPos) << 2 & 7;
         out[3 + outPos] = in.get(1 + inPos) >> 1 & 7;
         out[4 + outPos] = in.get(1 + inPos) >> 4 & 7;
         out[5 + outPos] = in.get(1 + inPos) >> 7 & 1 | in.get(2 + inPos) << 1 & 7;
         out[6 + outPos] = in.get(2 + inPos) >> 2 & 7;
         out[7 + outPos] = in.get(2 + inPos) >> 5 & 7;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 7;
         out[1 + outPos] = in[0 + inPos] >> 3 & 7;
         out[2 + outPos] = in[0 + inPos] >> 6 & 3 | in[1 + inPos] << 2 & 7;
         out[3 + outPos] = in[1 + inPos] >> 1 & 7;
         out[4 + outPos] = in[1 + inPos] >> 4 & 7;
         out[5 + outPos] = in[1 + inPos] >> 7 & 1 | in[2 + inPos] << 1 & 7;
         out[6 + outPos] = in[2 + inPos] >> 2 & 7;
         out[7 + outPos] = in[2 + inPos] >> 5 & 7;
         out[8 + outPos] = in[3 + inPos] & 7;
         out[9 + outPos] = in[3 + inPos] >> 3 & 7;
         out[10 + outPos] = in[3 + inPos] >> 6 & 3 | in[4 + inPos] << 2 & 7;
         out[11 + outPos] = in[4 + inPos] >> 1 & 7;
         out[12 + outPos] = in[4 + inPos] >> 4 & 7;
         out[13 + outPos] = in[4 + inPos] >> 7 & 1 | in[5 + inPos] << 1 & 7;
         out[14 + outPos] = in[5 + inPos] >> 2 & 7;
         out[15 + outPos] = in[5 + inPos] >> 5 & 7;
         out[16 + outPos] = in[6 + inPos] & 7;
         out[17 + outPos] = in[6 + inPos] >> 3 & 7;
         out[18 + outPos] = in[6 + inPos] >> 6 & 3 | in[7 + inPos] << 2 & 7;
         out[19 + outPos] = in[7 + inPos] >> 1 & 7;
         out[20 + outPos] = in[7 + inPos] >> 4 & 7;
         out[21 + outPos] = in[7 + inPos] >> 7 & 1 | in[8 + inPos] << 1 & 7;
         out[22 + outPos] = in[8 + inPos] >> 2 & 7;
         out[23 + outPos] = in[8 + inPos] >> 5 & 7;
         out[24 + outPos] = in[9 + inPos] & 7;
         out[25 + outPos] = in[9 + inPos] >> 3 & 7;
         out[26 + outPos] = in[9 + inPos] >> 6 & 3 | in[10 + inPos] << 2 & 7;
         out[27 + outPos] = in[10 + inPos] >> 1 & 7;
         out[28 + outPos] = in[10 + inPos] >> 4 & 7;
         out[29 + outPos] = in[10 + inPos] >> 7 & 1 | in[11 + inPos] << 1 & 7;
         out[30 + outPos] = in[11 + inPos] >> 2 & 7;
         out[31 + outPos] = in[11 + inPos] >> 5 & 7;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 7;
         out[1 + outPos] = in.get(0 + inPos) >> 3 & 7;
         out[2 + outPos] = in.get(0 + inPos) >> 6 & 3 | in.get(1 + inPos) << 2 & 7;
         out[3 + outPos] = in.get(1 + inPos) >> 1 & 7;
         out[4 + outPos] = in.get(1 + inPos) >> 4 & 7;
         out[5 + outPos] = in.get(1 + inPos) >> 7 & 1 | in.get(2 + inPos) << 1 & 7;
         out[6 + outPos] = in.get(2 + inPos) >> 2 & 7;
         out[7 + outPos] = in.get(2 + inPos) >> 5 & 7;
         out[8 + outPos] = in.get(3 + inPos) & 7;
         out[9 + outPos] = in.get(3 + inPos) >> 3 & 7;
         out[10 + outPos] = in.get(3 + inPos) >> 6 & 3 | in.get(4 + inPos) << 2 & 7;
         out[11 + outPos] = in.get(4 + inPos) >> 1 & 7;
         out[12 + outPos] = in.get(4 + inPos) >> 4 & 7;
         out[13 + outPos] = in.get(4 + inPos) >> 7 & 1 | in.get(5 + inPos) << 1 & 7;
         out[14 + outPos] = in.get(5 + inPos) >> 2 & 7;
         out[15 + outPos] = in.get(5 + inPos) >> 5 & 7;
         out[16 + outPos] = in.get(6 + inPos) & 7;
         out[17 + outPos] = in.get(6 + inPos) >> 3 & 7;
         out[18 + outPos] = in.get(6 + inPos) >> 6 & 3 | in.get(7 + inPos) << 2 & 7;
         out[19 + outPos] = in.get(7 + inPos) >> 1 & 7;
         out[20 + outPos] = in.get(7 + inPos) >> 4 & 7;
         out[21 + outPos] = in.get(7 + inPos) >> 7 & 1 | in.get(8 + inPos) << 1 & 7;
         out[22 + outPos] = in.get(8 + inPos) >> 2 & 7;
         out[23 + outPos] = in.get(8 + inPos) >> 5 & 7;
         out[24 + outPos] = in.get(9 + inPos) & 7;
         out[25 + outPos] = in.get(9 + inPos) >> 3 & 7;
         out[26 + outPos] = in.get(9 + inPos) >> 6 & 3 | in.get(10 + inPos) << 2 & 7;
         out[27 + outPos] = in.get(10 + inPos) >> 1 & 7;
         out[28 + outPos] = in.get(10 + inPos) >> 4 & 7;
         out[29 + outPos] = in.get(10 + inPos) >> 7 & 1 | in.get(11 + inPos) << 1 & 7;
         out[30 + outPos] = in.get(11 + inPos) >> 2 & 7;
         out[31 + outPos] = in.get(11 + inPos) >> 5 & 7;
      }
   }

   private static final class Packer4 extends BytePacker {
      private Packer4() {
         super(4);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 15 | (in[1 + inPos] & 15) << 4) & 255);
         out[1 + outPos] = (byte)((in[2 + inPos] & 15 | (in[3 + inPos] & 15) << 4) & 255);
         out[2 + outPos] = (byte)((in[4 + inPos] & 15 | (in[5 + inPos] & 15) << 4) & 255);
         out[3 + outPos] = (byte)((in[6 + inPos] & 15 | (in[7 + inPos] & 15) << 4) & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 15 | (in[1 + inPos] & 15) << 4) & 255);
         out[1 + outPos] = (byte)((in[2 + inPos] & 15 | (in[3 + inPos] & 15) << 4) & 255);
         out[2 + outPos] = (byte)((in[4 + inPos] & 15 | (in[5 + inPos] & 15) << 4) & 255);
         out[3 + outPos] = (byte)((in[6 + inPos] & 15 | (in[7 + inPos] & 15) << 4) & 255);
         out[4 + outPos] = (byte)((in[8 + inPos] & 15 | (in[9 + inPos] & 15) << 4) & 255);
         out[5 + outPos] = (byte)((in[10 + inPos] & 15 | (in[11 + inPos] & 15) << 4) & 255);
         out[6 + outPos] = (byte)((in[12 + inPos] & 15 | (in[13 + inPos] & 15) << 4) & 255);
         out[7 + outPos] = (byte)((in[14 + inPos] & 15 | (in[15 + inPos] & 15) << 4) & 255);
         out[8 + outPos] = (byte)((in[16 + inPos] & 15 | (in[17 + inPos] & 15) << 4) & 255);
         out[9 + outPos] = (byte)((in[18 + inPos] & 15 | (in[19 + inPos] & 15) << 4) & 255);
         out[10 + outPos] = (byte)((in[20 + inPos] & 15 | (in[21 + inPos] & 15) << 4) & 255);
         out[11 + outPos] = (byte)((in[22 + inPos] & 15 | (in[23 + inPos] & 15) << 4) & 255);
         out[12 + outPos] = (byte)((in[24 + inPos] & 15 | (in[25 + inPos] & 15) << 4) & 255);
         out[13 + outPos] = (byte)((in[26 + inPos] & 15 | (in[27 + inPos] & 15) << 4) & 255);
         out[14 + outPos] = (byte)((in[28 + inPos] & 15 | (in[29 + inPos] & 15) << 4) & 255);
         out[15 + outPos] = (byte)((in[30 + inPos] & 15 | (in[31 + inPos] & 15) << 4) & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 15;
         out[1 + outPos] = in[0 + inPos] >> 4 & 15;
         out[2 + outPos] = in[1 + inPos] & 15;
         out[3 + outPos] = in[1 + inPos] >> 4 & 15;
         out[4 + outPos] = in[2 + inPos] & 15;
         out[5 + outPos] = in[2 + inPos] >> 4 & 15;
         out[6 + outPos] = in[3 + inPos] & 15;
         out[7 + outPos] = in[3 + inPos] >> 4 & 15;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 15;
         out[1 + outPos] = in.get(0 + inPos) >> 4 & 15;
         out[2 + outPos] = in.get(1 + inPos) & 15;
         out[3 + outPos] = in.get(1 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(2 + inPos) & 15;
         out[5 + outPos] = in.get(2 + inPos) >> 4 & 15;
         out[6 + outPos] = in.get(3 + inPos) & 15;
         out[7 + outPos] = in.get(3 + inPos) >> 4 & 15;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 15;
         out[1 + outPos] = in[0 + inPos] >> 4 & 15;
         out[2 + outPos] = in[1 + inPos] & 15;
         out[3 + outPos] = in[1 + inPos] >> 4 & 15;
         out[4 + outPos] = in[2 + inPos] & 15;
         out[5 + outPos] = in[2 + inPos] >> 4 & 15;
         out[6 + outPos] = in[3 + inPos] & 15;
         out[7 + outPos] = in[3 + inPos] >> 4 & 15;
         out[8 + outPos] = in[4 + inPos] & 15;
         out[9 + outPos] = in[4 + inPos] >> 4 & 15;
         out[10 + outPos] = in[5 + inPos] & 15;
         out[11 + outPos] = in[5 + inPos] >> 4 & 15;
         out[12 + outPos] = in[6 + inPos] & 15;
         out[13 + outPos] = in[6 + inPos] >> 4 & 15;
         out[14 + outPos] = in[7 + inPos] & 15;
         out[15 + outPos] = in[7 + inPos] >> 4 & 15;
         out[16 + outPos] = in[8 + inPos] & 15;
         out[17 + outPos] = in[8 + inPos] >> 4 & 15;
         out[18 + outPos] = in[9 + inPos] & 15;
         out[19 + outPos] = in[9 + inPos] >> 4 & 15;
         out[20 + outPos] = in[10 + inPos] & 15;
         out[21 + outPos] = in[10 + inPos] >> 4 & 15;
         out[22 + outPos] = in[11 + inPos] & 15;
         out[23 + outPos] = in[11 + inPos] >> 4 & 15;
         out[24 + outPos] = in[12 + inPos] & 15;
         out[25 + outPos] = in[12 + inPos] >> 4 & 15;
         out[26 + outPos] = in[13 + inPos] & 15;
         out[27 + outPos] = in[13 + inPos] >> 4 & 15;
         out[28 + outPos] = in[14 + inPos] & 15;
         out[29 + outPos] = in[14 + inPos] >> 4 & 15;
         out[30 + outPos] = in[15 + inPos] & 15;
         out[31 + outPos] = in[15 + inPos] >> 4 & 15;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 15;
         out[1 + outPos] = in.get(0 + inPos) >> 4 & 15;
         out[2 + outPos] = in.get(1 + inPos) & 15;
         out[3 + outPos] = in.get(1 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(2 + inPos) & 15;
         out[5 + outPos] = in.get(2 + inPos) >> 4 & 15;
         out[6 + outPos] = in.get(3 + inPos) & 15;
         out[7 + outPos] = in.get(3 + inPos) >> 4 & 15;
         out[8 + outPos] = in.get(4 + inPos) & 15;
         out[9 + outPos] = in.get(4 + inPos) >> 4 & 15;
         out[10 + outPos] = in.get(5 + inPos) & 15;
         out[11 + outPos] = in.get(5 + inPos) >> 4 & 15;
         out[12 + outPos] = in.get(6 + inPos) & 15;
         out[13 + outPos] = in.get(6 + inPos) >> 4 & 15;
         out[14 + outPos] = in.get(7 + inPos) & 15;
         out[15 + outPos] = in.get(7 + inPos) >> 4 & 15;
         out[16 + outPos] = in.get(8 + inPos) & 15;
         out[17 + outPos] = in.get(8 + inPos) >> 4 & 15;
         out[18 + outPos] = in.get(9 + inPos) & 15;
         out[19 + outPos] = in.get(9 + inPos) >> 4 & 15;
         out[20 + outPos] = in.get(10 + inPos) & 15;
         out[21 + outPos] = in.get(10 + inPos) >> 4 & 15;
         out[22 + outPos] = in.get(11 + inPos) & 15;
         out[23 + outPos] = in.get(11 + inPos) >> 4 & 15;
         out[24 + outPos] = in.get(12 + inPos) & 15;
         out[25 + outPos] = in.get(12 + inPos) >> 4 & 15;
         out[26 + outPos] = in.get(13 + inPos) & 15;
         out[27 + outPos] = in.get(13 + inPos) >> 4 & 15;
         out[28 + outPos] = in.get(14 + inPos) & 15;
         out[29 + outPos] = in.get(14 + inPos) >> 4 & 15;
         out[30 + outPos] = in.get(15 + inPos) & 15;
         out[31 + outPos] = in.get(15 + inPos) >> 4 & 15;
      }
   }

   private static final class Packer5 extends BytePacker {
      private Packer5() {
         super(5);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 31 | (in[1 + inPos] & 31) << 5) & 255);
         out[1 + outPos] = (byte)(((in[1 + inPos] & 31) >>> 3 | (in[2 + inPos] & 31) << 2 | (in[3 + inPos] & 31) << 7) & 255);
         out[2 + outPos] = (byte)(((in[3 + inPos] & 31) >>> 1 | (in[4 + inPos] & 31) << 4) & 255);
         out[3 + outPos] = (byte)(((in[4 + inPos] & 31) >>> 4 | (in[5 + inPos] & 31) << 1 | (in[6 + inPos] & 31) << 6) & 255);
         out[4 + outPos] = (byte)(((in[6 + inPos] & 31) >>> 2 | (in[7 + inPos] & 31) << 3) & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 31 | (in[1 + inPos] & 31) << 5) & 255);
         out[1 + outPos] = (byte)(((in[1 + inPos] & 31) >>> 3 | (in[2 + inPos] & 31) << 2 | (in[3 + inPos] & 31) << 7) & 255);
         out[2 + outPos] = (byte)(((in[3 + inPos] & 31) >>> 1 | (in[4 + inPos] & 31) << 4) & 255);
         out[3 + outPos] = (byte)(((in[4 + inPos] & 31) >>> 4 | (in[5 + inPos] & 31) << 1 | (in[6 + inPos] & 31) << 6) & 255);
         out[4 + outPos] = (byte)(((in[6 + inPos] & 31) >>> 2 | (in[7 + inPos] & 31) << 3) & 255);
         out[5 + outPos] = (byte)((in[8 + inPos] & 31 | (in[9 + inPos] & 31) << 5) & 255);
         out[6 + outPos] = (byte)(((in[9 + inPos] & 31) >>> 3 | (in[10 + inPos] & 31) << 2 | (in[11 + inPos] & 31) << 7) & 255);
         out[7 + outPos] = (byte)(((in[11 + inPos] & 31) >>> 1 | (in[12 + inPos] & 31) << 4) & 255);
         out[8 + outPos] = (byte)(((in[12 + inPos] & 31) >>> 4 | (in[13 + inPos] & 31) << 1 | (in[14 + inPos] & 31) << 6) & 255);
         out[9 + outPos] = (byte)(((in[14 + inPos] & 31) >>> 2 | (in[15 + inPos] & 31) << 3) & 255);
         out[10 + outPos] = (byte)((in[16 + inPos] & 31 | (in[17 + inPos] & 31) << 5) & 255);
         out[11 + outPos] = (byte)(((in[17 + inPos] & 31) >>> 3 | (in[18 + inPos] & 31) << 2 | (in[19 + inPos] & 31) << 7) & 255);
         out[12 + outPos] = (byte)(((in[19 + inPos] & 31) >>> 1 | (in[20 + inPos] & 31) << 4) & 255);
         out[13 + outPos] = (byte)(((in[20 + inPos] & 31) >>> 4 | (in[21 + inPos] & 31) << 1 | (in[22 + inPos] & 31) << 6) & 255);
         out[14 + outPos] = (byte)(((in[22 + inPos] & 31) >>> 2 | (in[23 + inPos] & 31) << 3) & 255);
         out[15 + outPos] = (byte)((in[24 + inPos] & 31 | (in[25 + inPos] & 31) << 5) & 255);
         out[16 + outPos] = (byte)(((in[25 + inPos] & 31) >>> 3 | (in[26 + inPos] & 31) << 2 | (in[27 + inPos] & 31) << 7) & 255);
         out[17 + outPos] = (byte)(((in[27 + inPos] & 31) >>> 1 | (in[28 + inPos] & 31) << 4) & 255);
         out[18 + outPos] = (byte)(((in[28 + inPos] & 31) >>> 4 | (in[29 + inPos] & 31) << 1 | (in[30 + inPos] & 31) << 6) & 255);
         out[19 + outPos] = (byte)(((in[30 + inPos] & 31) >>> 2 | (in[31 + inPos] & 31) << 3) & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 31;
         out[1 + outPos] = in[0 + inPos] >> 5 & 7 | in[1 + inPos] << 3 & 31;
         out[2 + outPos] = in[1 + inPos] >> 2 & 31;
         out[3 + outPos] = in[1 + inPos] >> 7 & 1 | in[2 + inPos] << 1 & 31;
         out[4 + outPos] = in[2 + inPos] >> 4 & 15 | in[3 + inPos] << 4 & 31;
         out[5 + outPos] = in[3 + inPos] >> 1 & 31;
         out[6 + outPos] = in[3 + inPos] >> 6 & 3 | in[4 + inPos] << 2 & 31;
         out[7 + outPos] = in[4 + inPos] >> 3 & 31;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 31;
         out[1 + outPos] = in.get(0 + inPos) >> 5 & 7 | in.get(1 + inPos) << 3 & 31;
         out[2 + outPos] = in.get(1 + inPos) >> 2 & 31;
         out[3 + outPos] = in.get(1 + inPos) >> 7 & 1 | in.get(2 + inPos) << 1 & 31;
         out[4 + outPos] = in.get(2 + inPos) >> 4 & 15 | in.get(3 + inPos) << 4 & 31;
         out[5 + outPos] = in.get(3 + inPos) >> 1 & 31;
         out[6 + outPos] = in.get(3 + inPos) >> 6 & 3 | in.get(4 + inPos) << 2 & 31;
         out[7 + outPos] = in.get(4 + inPos) >> 3 & 31;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 31;
         out[1 + outPos] = in[0 + inPos] >> 5 & 7 | in[1 + inPos] << 3 & 31;
         out[2 + outPos] = in[1 + inPos] >> 2 & 31;
         out[3 + outPos] = in[1 + inPos] >> 7 & 1 | in[2 + inPos] << 1 & 31;
         out[4 + outPos] = in[2 + inPos] >> 4 & 15 | in[3 + inPos] << 4 & 31;
         out[5 + outPos] = in[3 + inPos] >> 1 & 31;
         out[6 + outPos] = in[3 + inPos] >> 6 & 3 | in[4 + inPos] << 2 & 31;
         out[7 + outPos] = in[4 + inPos] >> 3 & 31;
         out[8 + outPos] = in[5 + inPos] & 31;
         out[9 + outPos] = in[5 + inPos] >> 5 & 7 | in[6 + inPos] << 3 & 31;
         out[10 + outPos] = in[6 + inPos] >> 2 & 31;
         out[11 + outPos] = in[6 + inPos] >> 7 & 1 | in[7 + inPos] << 1 & 31;
         out[12 + outPos] = in[7 + inPos] >> 4 & 15 | in[8 + inPos] << 4 & 31;
         out[13 + outPos] = in[8 + inPos] >> 1 & 31;
         out[14 + outPos] = in[8 + inPos] >> 6 & 3 | in[9 + inPos] << 2 & 31;
         out[15 + outPos] = in[9 + inPos] >> 3 & 31;
         out[16 + outPos] = in[10 + inPos] & 31;
         out[17 + outPos] = in[10 + inPos] >> 5 & 7 | in[11 + inPos] << 3 & 31;
         out[18 + outPos] = in[11 + inPos] >> 2 & 31;
         out[19 + outPos] = in[11 + inPos] >> 7 & 1 | in[12 + inPos] << 1 & 31;
         out[20 + outPos] = in[12 + inPos] >> 4 & 15 | in[13 + inPos] << 4 & 31;
         out[21 + outPos] = in[13 + inPos] >> 1 & 31;
         out[22 + outPos] = in[13 + inPos] >> 6 & 3 | in[14 + inPos] << 2 & 31;
         out[23 + outPos] = in[14 + inPos] >> 3 & 31;
         out[24 + outPos] = in[15 + inPos] & 31;
         out[25 + outPos] = in[15 + inPos] >> 5 & 7 | in[16 + inPos] << 3 & 31;
         out[26 + outPos] = in[16 + inPos] >> 2 & 31;
         out[27 + outPos] = in[16 + inPos] >> 7 & 1 | in[17 + inPos] << 1 & 31;
         out[28 + outPos] = in[17 + inPos] >> 4 & 15 | in[18 + inPos] << 4 & 31;
         out[29 + outPos] = in[18 + inPos] >> 1 & 31;
         out[30 + outPos] = in[18 + inPos] >> 6 & 3 | in[19 + inPos] << 2 & 31;
         out[31 + outPos] = in[19 + inPos] >> 3 & 31;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 31;
         out[1 + outPos] = in.get(0 + inPos) >> 5 & 7 | in.get(1 + inPos) << 3 & 31;
         out[2 + outPos] = in.get(1 + inPos) >> 2 & 31;
         out[3 + outPos] = in.get(1 + inPos) >> 7 & 1 | in.get(2 + inPos) << 1 & 31;
         out[4 + outPos] = in.get(2 + inPos) >> 4 & 15 | in.get(3 + inPos) << 4 & 31;
         out[5 + outPos] = in.get(3 + inPos) >> 1 & 31;
         out[6 + outPos] = in.get(3 + inPos) >> 6 & 3 | in.get(4 + inPos) << 2 & 31;
         out[7 + outPos] = in.get(4 + inPos) >> 3 & 31;
         out[8 + outPos] = in.get(5 + inPos) & 31;
         out[9 + outPos] = in.get(5 + inPos) >> 5 & 7 | in.get(6 + inPos) << 3 & 31;
         out[10 + outPos] = in.get(6 + inPos) >> 2 & 31;
         out[11 + outPos] = in.get(6 + inPos) >> 7 & 1 | in.get(7 + inPos) << 1 & 31;
         out[12 + outPos] = in.get(7 + inPos) >> 4 & 15 | in.get(8 + inPos) << 4 & 31;
         out[13 + outPos] = in.get(8 + inPos) >> 1 & 31;
         out[14 + outPos] = in.get(8 + inPos) >> 6 & 3 | in.get(9 + inPos) << 2 & 31;
         out[15 + outPos] = in.get(9 + inPos) >> 3 & 31;
         out[16 + outPos] = in.get(10 + inPos) & 31;
         out[17 + outPos] = in.get(10 + inPos) >> 5 & 7 | in.get(11 + inPos) << 3 & 31;
         out[18 + outPos] = in.get(11 + inPos) >> 2 & 31;
         out[19 + outPos] = in.get(11 + inPos) >> 7 & 1 | in.get(12 + inPos) << 1 & 31;
         out[20 + outPos] = in.get(12 + inPos) >> 4 & 15 | in.get(13 + inPos) << 4 & 31;
         out[21 + outPos] = in.get(13 + inPos) >> 1 & 31;
         out[22 + outPos] = in.get(13 + inPos) >> 6 & 3 | in.get(14 + inPos) << 2 & 31;
         out[23 + outPos] = in.get(14 + inPos) >> 3 & 31;
         out[24 + outPos] = in.get(15 + inPos) & 31;
         out[25 + outPos] = in.get(15 + inPos) >> 5 & 7 | in.get(16 + inPos) << 3 & 31;
         out[26 + outPos] = in.get(16 + inPos) >> 2 & 31;
         out[27 + outPos] = in.get(16 + inPos) >> 7 & 1 | in.get(17 + inPos) << 1 & 31;
         out[28 + outPos] = in.get(17 + inPos) >> 4 & 15 | in.get(18 + inPos) << 4 & 31;
         out[29 + outPos] = in.get(18 + inPos) >> 1 & 31;
         out[30 + outPos] = in.get(18 + inPos) >> 6 & 3 | in.get(19 + inPos) << 2 & 31;
         out[31 + outPos] = in.get(19 + inPos) >> 3 & 31;
      }
   }

   private static final class Packer6 extends BytePacker {
      private Packer6() {
         super(6);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 63 | (in[1 + inPos] & 63) << 6) & 255);
         out[1 + outPos] = (byte)(((in[1 + inPos] & 63) >>> 2 | (in[2 + inPos] & 63) << 4) & 255);
         out[2 + outPos] = (byte)(((in[2 + inPos] & 63) >>> 4 | (in[3 + inPos] & 63) << 2) & 255);
         out[3 + outPos] = (byte)((in[4 + inPos] & 63 | (in[5 + inPos] & 63) << 6) & 255);
         out[4 + outPos] = (byte)(((in[5 + inPos] & 63) >>> 2 | (in[6 + inPos] & 63) << 4) & 255);
         out[5 + outPos] = (byte)(((in[6 + inPos] & 63) >>> 4 | (in[7 + inPos] & 63) << 2) & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 63 | (in[1 + inPos] & 63) << 6) & 255);
         out[1 + outPos] = (byte)(((in[1 + inPos] & 63) >>> 2 | (in[2 + inPos] & 63) << 4) & 255);
         out[2 + outPos] = (byte)(((in[2 + inPos] & 63) >>> 4 | (in[3 + inPos] & 63) << 2) & 255);
         out[3 + outPos] = (byte)((in[4 + inPos] & 63 | (in[5 + inPos] & 63) << 6) & 255);
         out[4 + outPos] = (byte)(((in[5 + inPos] & 63) >>> 2 | (in[6 + inPos] & 63) << 4) & 255);
         out[5 + outPos] = (byte)(((in[6 + inPos] & 63) >>> 4 | (in[7 + inPos] & 63) << 2) & 255);
         out[6 + outPos] = (byte)((in[8 + inPos] & 63 | (in[9 + inPos] & 63) << 6) & 255);
         out[7 + outPos] = (byte)(((in[9 + inPos] & 63) >>> 2 | (in[10 + inPos] & 63) << 4) & 255);
         out[8 + outPos] = (byte)(((in[10 + inPos] & 63) >>> 4 | (in[11 + inPos] & 63) << 2) & 255);
         out[9 + outPos] = (byte)((in[12 + inPos] & 63 | (in[13 + inPos] & 63) << 6) & 255);
         out[10 + outPos] = (byte)(((in[13 + inPos] & 63) >>> 2 | (in[14 + inPos] & 63) << 4) & 255);
         out[11 + outPos] = (byte)(((in[14 + inPos] & 63) >>> 4 | (in[15 + inPos] & 63) << 2) & 255);
         out[12 + outPos] = (byte)((in[16 + inPos] & 63 | (in[17 + inPos] & 63) << 6) & 255);
         out[13 + outPos] = (byte)(((in[17 + inPos] & 63) >>> 2 | (in[18 + inPos] & 63) << 4) & 255);
         out[14 + outPos] = (byte)(((in[18 + inPos] & 63) >>> 4 | (in[19 + inPos] & 63) << 2) & 255);
         out[15 + outPos] = (byte)((in[20 + inPos] & 63 | (in[21 + inPos] & 63) << 6) & 255);
         out[16 + outPos] = (byte)(((in[21 + inPos] & 63) >>> 2 | (in[22 + inPos] & 63) << 4) & 255);
         out[17 + outPos] = (byte)(((in[22 + inPos] & 63) >>> 4 | (in[23 + inPos] & 63) << 2) & 255);
         out[18 + outPos] = (byte)((in[24 + inPos] & 63 | (in[25 + inPos] & 63) << 6) & 255);
         out[19 + outPos] = (byte)(((in[25 + inPos] & 63) >>> 2 | (in[26 + inPos] & 63) << 4) & 255);
         out[20 + outPos] = (byte)(((in[26 + inPos] & 63) >>> 4 | (in[27 + inPos] & 63) << 2) & 255);
         out[21 + outPos] = (byte)((in[28 + inPos] & 63 | (in[29 + inPos] & 63) << 6) & 255);
         out[22 + outPos] = (byte)(((in[29 + inPos] & 63) >>> 2 | (in[30 + inPos] & 63) << 4) & 255);
         out[23 + outPos] = (byte)(((in[30 + inPos] & 63) >>> 4 | (in[31 + inPos] & 63) << 2) & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 63;
         out[1 + outPos] = in[0 + inPos] >> 6 & 3 | in[1 + inPos] << 2 & 63;
         out[2 + outPos] = in[1 + inPos] >> 4 & 15 | in[2 + inPos] << 4 & 63;
         out[3 + outPos] = in[2 + inPos] >> 2 & 63;
         out[4 + outPos] = in[3 + inPos] & 63;
         out[5 + outPos] = in[3 + inPos] >> 6 & 3 | in[4 + inPos] << 2 & 63;
         out[6 + outPos] = in[4 + inPos] >> 4 & 15 | in[5 + inPos] << 4 & 63;
         out[7 + outPos] = in[5 + inPos] >> 2 & 63;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 63;
         out[1 + outPos] = in.get(0 + inPos) >> 6 & 3 | in.get(1 + inPos) << 2 & 63;
         out[2 + outPos] = in.get(1 + inPos) >> 4 & 15 | in.get(2 + inPos) << 4 & 63;
         out[3 + outPos] = in.get(2 + inPos) >> 2 & 63;
         out[4 + outPos] = in.get(3 + inPos) & 63;
         out[5 + outPos] = in.get(3 + inPos) >> 6 & 3 | in.get(4 + inPos) << 2 & 63;
         out[6 + outPos] = in.get(4 + inPos) >> 4 & 15 | in.get(5 + inPos) << 4 & 63;
         out[7 + outPos] = in.get(5 + inPos) >> 2 & 63;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 63;
         out[1 + outPos] = in[0 + inPos] >> 6 & 3 | in[1 + inPos] << 2 & 63;
         out[2 + outPos] = in[1 + inPos] >> 4 & 15 | in[2 + inPos] << 4 & 63;
         out[3 + outPos] = in[2 + inPos] >> 2 & 63;
         out[4 + outPos] = in[3 + inPos] & 63;
         out[5 + outPos] = in[3 + inPos] >> 6 & 3 | in[4 + inPos] << 2 & 63;
         out[6 + outPos] = in[4 + inPos] >> 4 & 15 | in[5 + inPos] << 4 & 63;
         out[7 + outPos] = in[5 + inPos] >> 2 & 63;
         out[8 + outPos] = in[6 + inPos] & 63;
         out[9 + outPos] = in[6 + inPos] >> 6 & 3 | in[7 + inPos] << 2 & 63;
         out[10 + outPos] = in[7 + inPos] >> 4 & 15 | in[8 + inPos] << 4 & 63;
         out[11 + outPos] = in[8 + inPos] >> 2 & 63;
         out[12 + outPos] = in[9 + inPos] & 63;
         out[13 + outPos] = in[9 + inPos] >> 6 & 3 | in[10 + inPos] << 2 & 63;
         out[14 + outPos] = in[10 + inPos] >> 4 & 15 | in[11 + inPos] << 4 & 63;
         out[15 + outPos] = in[11 + inPos] >> 2 & 63;
         out[16 + outPos] = in[12 + inPos] & 63;
         out[17 + outPos] = in[12 + inPos] >> 6 & 3 | in[13 + inPos] << 2 & 63;
         out[18 + outPos] = in[13 + inPos] >> 4 & 15 | in[14 + inPos] << 4 & 63;
         out[19 + outPos] = in[14 + inPos] >> 2 & 63;
         out[20 + outPos] = in[15 + inPos] & 63;
         out[21 + outPos] = in[15 + inPos] >> 6 & 3 | in[16 + inPos] << 2 & 63;
         out[22 + outPos] = in[16 + inPos] >> 4 & 15 | in[17 + inPos] << 4 & 63;
         out[23 + outPos] = in[17 + inPos] >> 2 & 63;
         out[24 + outPos] = in[18 + inPos] & 63;
         out[25 + outPos] = in[18 + inPos] >> 6 & 3 | in[19 + inPos] << 2 & 63;
         out[26 + outPos] = in[19 + inPos] >> 4 & 15 | in[20 + inPos] << 4 & 63;
         out[27 + outPos] = in[20 + inPos] >> 2 & 63;
         out[28 + outPos] = in[21 + inPos] & 63;
         out[29 + outPos] = in[21 + inPos] >> 6 & 3 | in[22 + inPos] << 2 & 63;
         out[30 + outPos] = in[22 + inPos] >> 4 & 15 | in[23 + inPos] << 4 & 63;
         out[31 + outPos] = in[23 + inPos] >> 2 & 63;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 63;
         out[1 + outPos] = in.get(0 + inPos) >> 6 & 3 | in.get(1 + inPos) << 2 & 63;
         out[2 + outPos] = in.get(1 + inPos) >> 4 & 15 | in.get(2 + inPos) << 4 & 63;
         out[3 + outPos] = in.get(2 + inPos) >> 2 & 63;
         out[4 + outPos] = in.get(3 + inPos) & 63;
         out[5 + outPos] = in.get(3 + inPos) >> 6 & 3 | in.get(4 + inPos) << 2 & 63;
         out[6 + outPos] = in.get(4 + inPos) >> 4 & 15 | in.get(5 + inPos) << 4 & 63;
         out[7 + outPos] = in.get(5 + inPos) >> 2 & 63;
         out[8 + outPos] = in.get(6 + inPos) & 63;
         out[9 + outPos] = in.get(6 + inPos) >> 6 & 3 | in.get(7 + inPos) << 2 & 63;
         out[10 + outPos] = in.get(7 + inPos) >> 4 & 15 | in.get(8 + inPos) << 4 & 63;
         out[11 + outPos] = in.get(8 + inPos) >> 2 & 63;
         out[12 + outPos] = in.get(9 + inPos) & 63;
         out[13 + outPos] = in.get(9 + inPos) >> 6 & 3 | in.get(10 + inPos) << 2 & 63;
         out[14 + outPos] = in.get(10 + inPos) >> 4 & 15 | in.get(11 + inPos) << 4 & 63;
         out[15 + outPos] = in.get(11 + inPos) >> 2 & 63;
         out[16 + outPos] = in.get(12 + inPos) & 63;
         out[17 + outPos] = in.get(12 + inPos) >> 6 & 3 | in.get(13 + inPos) << 2 & 63;
         out[18 + outPos] = in.get(13 + inPos) >> 4 & 15 | in.get(14 + inPos) << 4 & 63;
         out[19 + outPos] = in.get(14 + inPos) >> 2 & 63;
         out[20 + outPos] = in.get(15 + inPos) & 63;
         out[21 + outPos] = in.get(15 + inPos) >> 6 & 3 | in.get(16 + inPos) << 2 & 63;
         out[22 + outPos] = in.get(16 + inPos) >> 4 & 15 | in.get(17 + inPos) << 4 & 63;
         out[23 + outPos] = in.get(17 + inPos) >> 2 & 63;
         out[24 + outPos] = in.get(18 + inPos) & 63;
         out[25 + outPos] = in.get(18 + inPos) >> 6 & 3 | in.get(19 + inPos) << 2 & 63;
         out[26 + outPos] = in.get(19 + inPos) >> 4 & 15 | in.get(20 + inPos) << 4 & 63;
         out[27 + outPos] = in.get(20 + inPos) >> 2 & 63;
         out[28 + outPos] = in.get(21 + inPos) & 63;
         out[29 + outPos] = in.get(21 + inPos) >> 6 & 3 | in.get(22 + inPos) << 2 & 63;
         out[30 + outPos] = in.get(22 + inPos) >> 4 & 15 | in.get(23 + inPos) << 4 & 63;
         out[31 + outPos] = in.get(23 + inPos) >> 2 & 63;
      }
   }

   private static final class Packer7 extends BytePacker {
      private Packer7() {
         super(7);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 127 | (in[1 + inPos] & 127) << 7) & 255);
         out[1 + outPos] = (byte)(((in[1 + inPos] & 127) >>> 1 | (in[2 + inPos] & 127) << 6) & 255);
         out[2 + outPos] = (byte)(((in[2 + inPos] & 127) >>> 2 | (in[3 + inPos] & 127) << 5) & 255);
         out[3 + outPos] = (byte)(((in[3 + inPos] & 127) >>> 3 | (in[4 + inPos] & 127) << 4) & 255);
         out[4 + outPos] = (byte)(((in[4 + inPos] & 127) >>> 4 | (in[5 + inPos] & 127) << 3) & 255);
         out[5 + outPos] = (byte)(((in[5 + inPos] & 127) >>> 5 | (in[6 + inPos] & 127) << 2) & 255);
         out[6 + outPos] = (byte)(((in[6 + inPos] & 127) >>> 6 | (in[7 + inPos] & 127) << 1) & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 127 | (in[1 + inPos] & 127) << 7) & 255);
         out[1 + outPos] = (byte)(((in[1 + inPos] & 127) >>> 1 | (in[2 + inPos] & 127) << 6) & 255);
         out[2 + outPos] = (byte)(((in[2 + inPos] & 127) >>> 2 | (in[3 + inPos] & 127) << 5) & 255);
         out[3 + outPos] = (byte)(((in[3 + inPos] & 127) >>> 3 | (in[4 + inPos] & 127) << 4) & 255);
         out[4 + outPos] = (byte)(((in[4 + inPos] & 127) >>> 4 | (in[5 + inPos] & 127) << 3) & 255);
         out[5 + outPos] = (byte)(((in[5 + inPos] & 127) >>> 5 | (in[6 + inPos] & 127) << 2) & 255);
         out[6 + outPos] = (byte)(((in[6 + inPos] & 127) >>> 6 | (in[7 + inPos] & 127) << 1) & 255);
         out[7 + outPos] = (byte)((in[8 + inPos] & 127 | (in[9 + inPos] & 127) << 7) & 255);
         out[8 + outPos] = (byte)(((in[9 + inPos] & 127) >>> 1 | (in[10 + inPos] & 127) << 6) & 255);
         out[9 + outPos] = (byte)(((in[10 + inPos] & 127) >>> 2 | (in[11 + inPos] & 127) << 5) & 255);
         out[10 + outPos] = (byte)(((in[11 + inPos] & 127) >>> 3 | (in[12 + inPos] & 127) << 4) & 255);
         out[11 + outPos] = (byte)(((in[12 + inPos] & 127) >>> 4 | (in[13 + inPos] & 127) << 3) & 255);
         out[12 + outPos] = (byte)(((in[13 + inPos] & 127) >>> 5 | (in[14 + inPos] & 127) << 2) & 255);
         out[13 + outPos] = (byte)(((in[14 + inPos] & 127) >>> 6 | (in[15 + inPos] & 127) << 1) & 255);
         out[14 + outPos] = (byte)((in[16 + inPos] & 127 | (in[17 + inPos] & 127) << 7) & 255);
         out[15 + outPos] = (byte)(((in[17 + inPos] & 127) >>> 1 | (in[18 + inPos] & 127) << 6) & 255);
         out[16 + outPos] = (byte)(((in[18 + inPos] & 127) >>> 2 | (in[19 + inPos] & 127) << 5) & 255);
         out[17 + outPos] = (byte)(((in[19 + inPos] & 127) >>> 3 | (in[20 + inPos] & 127) << 4) & 255);
         out[18 + outPos] = (byte)(((in[20 + inPos] & 127) >>> 4 | (in[21 + inPos] & 127) << 3) & 255);
         out[19 + outPos] = (byte)(((in[21 + inPos] & 127) >>> 5 | (in[22 + inPos] & 127) << 2) & 255);
         out[20 + outPos] = (byte)(((in[22 + inPos] & 127) >>> 6 | (in[23 + inPos] & 127) << 1) & 255);
         out[21 + outPos] = (byte)((in[24 + inPos] & 127 | (in[25 + inPos] & 127) << 7) & 255);
         out[22 + outPos] = (byte)(((in[25 + inPos] & 127) >>> 1 | (in[26 + inPos] & 127) << 6) & 255);
         out[23 + outPos] = (byte)(((in[26 + inPos] & 127) >>> 2 | (in[27 + inPos] & 127) << 5) & 255);
         out[24 + outPos] = (byte)(((in[27 + inPos] & 127) >>> 3 | (in[28 + inPos] & 127) << 4) & 255);
         out[25 + outPos] = (byte)(((in[28 + inPos] & 127) >>> 4 | (in[29 + inPos] & 127) << 3) & 255);
         out[26 + outPos] = (byte)(((in[29 + inPos] & 127) >>> 5 | (in[30 + inPos] & 127) << 2) & 255);
         out[27 + outPos] = (byte)(((in[30 + inPos] & 127) >>> 6 | (in[31 + inPos] & 127) << 1) & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 127;
         out[1 + outPos] = in[0 + inPos] >> 7 & 1 | in[1 + inPos] << 1 & 127;
         out[2 + outPos] = in[1 + inPos] >> 6 & 3 | in[2 + inPos] << 2 & 127;
         out[3 + outPos] = in[2 + inPos] >> 5 & 7 | in[3 + inPos] << 3 & 127;
         out[4 + outPos] = in[3 + inPos] >> 4 & 15 | in[4 + inPos] << 4 & 127;
         out[5 + outPos] = in[4 + inPos] >> 3 & 31 | in[5 + inPos] << 5 & 127;
         out[6 + outPos] = in[5 + inPos] >> 2 & 63 | in[6 + inPos] << 6 & 127;
         out[7 + outPos] = in[6 + inPos] >> 1 & 127;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 127;
         out[1 + outPos] = in.get(0 + inPos) >> 7 & 1 | in.get(1 + inPos) << 1 & 127;
         out[2 + outPos] = in.get(1 + inPos) >> 6 & 3 | in.get(2 + inPos) << 2 & 127;
         out[3 + outPos] = in.get(2 + inPos) >> 5 & 7 | in.get(3 + inPos) << 3 & 127;
         out[4 + outPos] = in.get(3 + inPos) >> 4 & 15 | in.get(4 + inPos) << 4 & 127;
         out[5 + outPos] = in.get(4 + inPos) >> 3 & 31 | in.get(5 + inPos) << 5 & 127;
         out[6 + outPos] = in.get(5 + inPos) >> 2 & 63 | in.get(6 + inPos) << 6 & 127;
         out[7 + outPos] = in.get(6 + inPos) >> 1 & 127;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 127;
         out[1 + outPos] = in[0 + inPos] >> 7 & 1 | in[1 + inPos] << 1 & 127;
         out[2 + outPos] = in[1 + inPos] >> 6 & 3 | in[2 + inPos] << 2 & 127;
         out[3 + outPos] = in[2 + inPos] >> 5 & 7 | in[3 + inPos] << 3 & 127;
         out[4 + outPos] = in[3 + inPos] >> 4 & 15 | in[4 + inPos] << 4 & 127;
         out[5 + outPos] = in[4 + inPos] >> 3 & 31 | in[5 + inPos] << 5 & 127;
         out[6 + outPos] = in[5 + inPos] >> 2 & 63 | in[6 + inPos] << 6 & 127;
         out[7 + outPos] = in[6 + inPos] >> 1 & 127;
         out[8 + outPos] = in[7 + inPos] & 127;
         out[9 + outPos] = in[7 + inPos] >> 7 & 1 | in[8 + inPos] << 1 & 127;
         out[10 + outPos] = in[8 + inPos] >> 6 & 3 | in[9 + inPos] << 2 & 127;
         out[11 + outPos] = in[9 + inPos] >> 5 & 7 | in[10 + inPos] << 3 & 127;
         out[12 + outPos] = in[10 + inPos] >> 4 & 15 | in[11 + inPos] << 4 & 127;
         out[13 + outPos] = in[11 + inPos] >> 3 & 31 | in[12 + inPos] << 5 & 127;
         out[14 + outPos] = in[12 + inPos] >> 2 & 63 | in[13 + inPos] << 6 & 127;
         out[15 + outPos] = in[13 + inPos] >> 1 & 127;
         out[16 + outPos] = in[14 + inPos] & 127;
         out[17 + outPos] = in[14 + inPos] >> 7 & 1 | in[15 + inPos] << 1 & 127;
         out[18 + outPos] = in[15 + inPos] >> 6 & 3 | in[16 + inPos] << 2 & 127;
         out[19 + outPos] = in[16 + inPos] >> 5 & 7 | in[17 + inPos] << 3 & 127;
         out[20 + outPos] = in[17 + inPos] >> 4 & 15 | in[18 + inPos] << 4 & 127;
         out[21 + outPos] = in[18 + inPos] >> 3 & 31 | in[19 + inPos] << 5 & 127;
         out[22 + outPos] = in[19 + inPos] >> 2 & 63 | in[20 + inPos] << 6 & 127;
         out[23 + outPos] = in[20 + inPos] >> 1 & 127;
         out[24 + outPos] = in[21 + inPos] & 127;
         out[25 + outPos] = in[21 + inPos] >> 7 & 1 | in[22 + inPos] << 1 & 127;
         out[26 + outPos] = in[22 + inPos] >> 6 & 3 | in[23 + inPos] << 2 & 127;
         out[27 + outPos] = in[23 + inPos] >> 5 & 7 | in[24 + inPos] << 3 & 127;
         out[28 + outPos] = in[24 + inPos] >> 4 & 15 | in[25 + inPos] << 4 & 127;
         out[29 + outPos] = in[25 + inPos] >> 3 & 31 | in[26 + inPos] << 5 & 127;
         out[30 + outPos] = in[26 + inPos] >> 2 & 63 | in[27 + inPos] << 6 & 127;
         out[31 + outPos] = in[27 + inPos] >> 1 & 127;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 127;
         out[1 + outPos] = in.get(0 + inPos) >> 7 & 1 | in.get(1 + inPos) << 1 & 127;
         out[2 + outPos] = in.get(1 + inPos) >> 6 & 3 | in.get(2 + inPos) << 2 & 127;
         out[3 + outPos] = in.get(2 + inPos) >> 5 & 7 | in.get(3 + inPos) << 3 & 127;
         out[4 + outPos] = in.get(3 + inPos) >> 4 & 15 | in.get(4 + inPos) << 4 & 127;
         out[5 + outPos] = in.get(4 + inPos) >> 3 & 31 | in.get(5 + inPos) << 5 & 127;
         out[6 + outPos] = in.get(5 + inPos) >> 2 & 63 | in.get(6 + inPos) << 6 & 127;
         out[7 + outPos] = in.get(6 + inPos) >> 1 & 127;
         out[8 + outPos] = in.get(7 + inPos) & 127;
         out[9 + outPos] = in.get(7 + inPos) >> 7 & 1 | in.get(8 + inPos) << 1 & 127;
         out[10 + outPos] = in.get(8 + inPos) >> 6 & 3 | in.get(9 + inPos) << 2 & 127;
         out[11 + outPos] = in.get(9 + inPos) >> 5 & 7 | in.get(10 + inPos) << 3 & 127;
         out[12 + outPos] = in.get(10 + inPos) >> 4 & 15 | in.get(11 + inPos) << 4 & 127;
         out[13 + outPos] = in.get(11 + inPos) >> 3 & 31 | in.get(12 + inPos) << 5 & 127;
         out[14 + outPos] = in.get(12 + inPos) >> 2 & 63 | in.get(13 + inPos) << 6 & 127;
         out[15 + outPos] = in.get(13 + inPos) >> 1 & 127;
         out[16 + outPos] = in.get(14 + inPos) & 127;
         out[17 + outPos] = in.get(14 + inPos) >> 7 & 1 | in.get(15 + inPos) << 1 & 127;
         out[18 + outPos] = in.get(15 + inPos) >> 6 & 3 | in.get(16 + inPos) << 2 & 127;
         out[19 + outPos] = in.get(16 + inPos) >> 5 & 7 | in.get(17 + inPos) << 3 & 127;
         out[20 + outPos] = in.get(17 + inPos) >> 4 & 15 | in.get(18 + inPos) << 4 & 127;
         out[21 + outPos] = in.get(18 + inPos) >> 3 & 31 | in.get(19 + inPos) << 5 & 127;
         out[22 + outPos] = in.get(19 + inPos) >> 2 & 63 | in.get(20 + inPos) << 6 & 127;
         out[23 + outPos] = in.get(20 + inPos) >> 1 & 127;
         out[24 + outPos] = in.get(21 + inPos) & 127;
         out[25 + outPos] = in.get(21 + inPos) >> 7 & 1 | in.get(22 + inPos) << 1 & 127;
         out[26 + outPos] = in.get(22 + inPos) >> 6 & 3 | in.get(23 + inPos) << 2 & 127;
         out[27 + outPos] = in.get(23 + inPos) >> 5 & 7 | in.get(24 + inPos) << 3 & 127;
         out[28 + outPos] = in.get(24 + inPos) >> 4 & 15 | in.get(25 + inPos) << 4 & 127;
         out[29 + outPos] = in.get(25 + inPos) >> 3 & 31 | in.get(26 + inPos) << 5 & 127;
         out[30 + outPos] = in.get(26 + inPos) >> 2 & 63 | in.get(27 + inPos) << 6 & 127;
         out[31 + outPos] = in.get(27 + inPos) >> 1 & 127;
      }
   }

   private static final class Packer8 extends BytePacker {
      private Packer8() {
         super(8);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 255 & 255);
         out[1 + outPos] = (byte)(in[1 + inPos] & 255 & 255);
         out[2 + outPos] = (byte)(in[2 + inPos] & 255 & 255);
         out[3 + outPos] = (byte)(in[3 + inPos] & 255 & 255);
         out[4 + outPos] = (byte)(in[4 + inPos] & 255 & 255);
         out[5 + outPos] = (byte)(in[5 + inPos] & 255 & 255);
         out[6 + outPos] = (byte)(in[6 + inPos] & 255 & 255);
         out[7 + outPos] = (byte)(in[7 + inPos] & 255 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 255 & 255);
         out[1 + outPos] = (byte)(in[1 + inPos] & 255 & 255);
         out[2 + outPos] = (byte)(in[2 + inPos] & 255 & 255);
         out[3 + outPos] = (byte)(in[3 + inPos] & 255 & 255);
         out[4 + outPos] = (byte)(in[4 + inPos] & 255 & 255);
         out[5 + outPos] = (byte)(in[5 + inPos] & 255 & 255);
         out[6 + outPos] = (byte)(in[6 + inPos] & 255 & 255);
         out[7 + outPos] = (byte)(in[7 + inPos] & 255 & 255);
         out[8 + outPos] = (byte)(in[8 + inPos] & 255 & 255);
         out[9 + outPos] = (byte)(in[9 + inPos] & 255 & 255);
         out[10 + outPos] = (byte)(in[10 + inPos] & 255 & 255);
         out[11 + outPos] = (byte)(in[11 + inPos] & 255 & 255);
         out[12 + outPos] = (byte)(in[12 + inPos] & 255 & 255);
         out[13 + outPos] = (byte)(in[13 + inPos] & 255 & 255);
         out[14 + outPos] = (byte)(in[14 + inPos] & 255 & 255);
         out[15 + outPos] = (byte)(in[15 + inPos] & 255 & 255);
         out[16 + outPos] = (byte)(in[16 + inPos] & 255 & 255);
         out[17 + outPos] = (byte)(in[17 + inPos] & 255 & 255);
         out[18 + outPos] = (byte)(in[18 + inPos] & 255 & 255);
         out[19 + outPos] = (byte)(in[19 + inPos] & 255 & 255);
         out[20 + outPos] = (byte)(in[20 + inPos] & 255 & 255);
         out[21 + outPos] = (byte)(in[21 + inPos] & 255 & 255);
         out[22 + outPos] = (byte)(in[22 + inPos] & 255 & 255);
         out[23 + outPos] = (byte)(in[23 + inPos] & 255 & 255);
         out[24 + outPos] = (byte)(in[24 + inPos] & 255 & 255);
         out[25 + outPos] = (byte)(in[25 + inPos] & 255 & 255);
         out[26 + outPos] = (byte)(in[26 + inPos] & 255 & 255);
         out[27 + outPos] = (byte)(in[27 + inPos] & 255 & 255);
         out[28 + outPos] = (byte)(in[28 + inPos] & 255 & 255);
         out[29 + outPos] = (byte)(in[29 + inPos] & 255 & 255);
         out[30 + outPos] = (byte)(in[30 + inPos] & 255 & 255);
         out[31 + outPos] = (byte)(in[31 + inPos] & 255 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255;
         out[1 + outPos] = in[1 + inPos] & 255;
         out[2 + outPos] = in[2 + inPos] & 255;
         out[3 + outPos] = in[3 + inPos] & 255;
         out[4 + outPos] = in[4 + inPos] & 255;
         out[5 + outPos] = in[5 + inPos] & 255;
         out[6 + outPos] = in[6 + inPos] & 255;
         out[7 + outPos] = in[7 + inPos] & 255;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255;
         out[1 + outPos] = in.get(1 + inPos) & 255;
         out[2 + outPos] = in.get(2 + inPos) & 255;
         out[3 + outPos] = in.get(3 + inPos) & 255;
         out[4 + outPos] = in.get(4 + inPos) & 255;
         out[5 + outPos] = in.get(5 + inPos) & 255;
         out[6 + outPos] = in.get(6 + inPos) & 255;
         out[7 + outPos] = in.get(7 + inPos) & 255;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255;
         out[1 + outPos] = in[1 + inPos] & 255;
         out[2 + outPos] = in[2 + inPos] & 255;
         out[3 + outPos] = in[3 + inPos] & 255;
         out[4 + outPos] = in[4 + inPos] & 255;
         out[5 + outPos] = in[5 + inPos] & 255;
         out[6 + outPos] = in[6 + inPos] & 255;
         out[7 + outPos] = in[7 + inPos] & 255;
         out[8 + outPos] = in[8 + inPos] & 255;
         out[9 + outPos] = in[9 + inPos] & 255;
         out[10 + outPos] = in[10 + inPos] & 255;
         out[11 + outPos] = in[11 + inPos] & 255;
         out[12 + outPos] = in[12 + inPos] & 255;
         out[13 + outPos] = in[13 + inPos] & 255;
         out[14 + outPos] = in[14 + inPos] & 255;
         out[15 + outPos] = in[15 + inPos] & 255;
         out[16 + outPos] = in[16 + inPos] & 255;
         out[17 + outPos] = in[17 + inPos] & 255;
         out[18 + outPos] = in[18 + inPos] & 255;
         out[19 + outPos] = in[19 + inPos] & 255;
         out[20 + outPos] = in[20 + inPos] & 255;
         out[21 + outPos] = in[21 + inPos] & 255;
         out[22 + outPos] = in[22 + inPos] & 255;
         out[23 + outPos] = in[23 + inPos] & 255;
         out[24 + outPos] = in[24 + inPos] & 255;
         out[25 + outPos] = in[25 + inPos] & 255;
         out[26 + outPos] = in[26 + inPos] & 255;
         out[27 + outPos] = in[27 + inPos] & 255;
         out[28 + outPos] = in[28 + inPos] & 255;
         out[29 + outPos] = in[29 + inPos] & 255;
         out[30 + outPos] = in[30 + inPos] & 255;
         out[31 + outPos] = in[31 + inPos] & 255;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255;
         out[1 + outPos] = in.get(1 + inPos) & 255;
         out[2 + outPos] = in.get(2 + inPos) & 255;
         out[3 + outPos] = in.get(3 + inPos) & 255;
         out[4 + outPos] = in.get(4 + inPos) & 255;
         out[5 + outPos] = in.get(5 + inPos) & 255;
         out[6 + outPos] = in.get(6 + inPos) & 255;
         out[7 + outPos] = in.get(7 + inPos) & 255;
         out[8 + outPos] = in.get(8 + inPos) & 255;
         out[9 + outPos] = in.get(9 + inPos) & 255;
         out[10 + outPos] = in.get(10 + inPos) & 255;
         out[11 + outPos] = in.get(11 + inPos) & 255;
         out[12 + outPos] = in.get(12 + inPos) & 255;
         out[13 + outPos] = in.get(13 + inPos) & 255;
         out[14 + outPos] = in.get(14 + inPos) & 255;
         out[15 + outPos] = in.get(15 + inPos) & 255;
         out[16 + outPos] = in.get(16 + inPos) & 255;
         out[17 + outPos] = in.get(17 + inPos) & 255;
         out[18 + outPos] = in.get(18 + inPos) & 255;
         out[19 + outPos] = in.get(19 + inPos) & 255;
         out[20 + outPos] = in.get(20 + inPos) & 255;
         out[21 + outPos] = in.get(21 + inPos) & 255;
         out[22 + outPos] = in.get(22 + inPos) & 255;
         out[23 + outPos] = in.get(23 + inPos) & 255;
         out[24 + outPos] = in.get(24 + inPos) & 255;
         out[25 + outPos] = in.get(25 + inPos) & 255;
         out[26 + outPos] = in.get(26 + inPos) & 255;
         out[27 + outPos] = in.get(27 + inPos) & 255;
         out[28 + outPos] = in.get(28 + inPos) & 255;
         out[29 + outPos] = in.get(29 + inPos) & 255;
         out[30 + outPos] = in.get(30 + inPos) & 255;
         out[31 + outPos] = in.get(31 + inPos) & 255;
      }
   }

   private static final class Packer9 extends BytePacker {
      private Packer9() {
         super(9);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 511 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 511) >>> 8 | (in[1 + inPos] & 511) << 1) & 255);
         out[2 + outPos] = (byte)(((in[1 + inPos] & 511) >>> 7 | (in[2 + inPos] & 511) << 2) & 255);
         out[3 + outPos] = (byte)(((in[2 + inPos] & 511) >>> 6 | (in[3 + inPos] & 511) << 3) & 255);
         out[4 + outPos] = (byte)(((in[3 + inPos] & 511) >>> 5 | (in[4 + inPos] & 511) << 4) & 255);
         out[5 + outPos] = (byte)(((in[4 + inPos] & 511) >>> 4 | (in[5 + inPos] & 511) << 5) & 255);
         out[6 + outPos] = (byte)(((in[5 + inPos] & 511) >>> 3 | (in[6 + inPos] & 511) << 6) & 255);
         out[7 + outPos] = (byte)(((in[6 + inPos] & 511) >>> 2 | (in[7 + inPos] & 511) << 7) & 255);
         out[8 + outPos] = (byte)((in[7 + inPos] & 511) >>> 1 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 511 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 511) >>> 8 | (in[1 + inPos] & 511) << 1) & 255);
         out[2 + outPos] = (byte)(((in[1 + inPos] & 511) >>> 7 | (in[2 + inPos] & 511) << 2) & 255);
         out[3 + outPos] = (byte)(((in[2 + inPos] & 511) >>> 6 | (in[3 + inPos] & 511) << 3) & 255);
         out[4 + outPos] = (byte)(((in[3 + inPos] & 511) >>> 5 | (in[4 + inPos] & 511) << 4) & 255);
         out[5 + outPos] = (byte)(((in[4 + inPos] & 511) >>> 4 | (in[5 + inPos] & 511) << 5) & 255);
         out[6 + outPos] = (byte)(((in[5 + inPos] & 511) >>> 3 | (in[6 + inPos] & 511) << 6) & 255);
         out[7 + outPos] = (byte)(((in[6 + inPos] & 511) >>> 2 | (in[7 + inPos] & 511) << 7) & 255);
         out[8 + outPos] = (byte)((in[7 + inPos] & 511) >>> 1 & 255);
         out[9 + outPos] = (byte)(in[8 + inPos] & 511 & 255);
         out[10 + outPos] = (byte)(((in[8 + inPos] & 511) >>> 8 | (in[9 + inPos] & 511) << 1) & 255);
         out[11 + outPos] = (byte)(((in[9 + inPos] & 511) >>> 7 | (in[10 + inPos] & 511) << 2) & 255);
         out[12 + outPos] = (byte)(((in[10 + inPos] & 511) >>> 6 | (in[11 + inPos] & 511) << 3) & 255);
         out[13 + outPos] = (byte)(((in[11 + inPos] & 511) >>> 5 | (in[12 + inPos] & 511) << 4) & 255);
         out[14 + outPos] = (byte)(((in[12 + inPos] & 511) >>> 4 | (in[13 + inPos] & 511) << 5) & 255);
         out[15 + outPos] = (byte)(((in[13 + inPos] & 511) >>> 3 | (in[14 + inPos] & 511) << 6) & 255);
         out[16 + outPos] = (byte)(((in[14 + inPos] & 511) >>> 2 | (in[15 + inPos] & 511) << 7) & 255);
         out[17 + outPos] = (byte)((in[15 + inPos] & 511) >>> 1 & 255);
         out[18 + outPos] = (byte)(in[16 + inPos] & 511 & 255);
         out[19 + outPos] = (byte)(((in[16 + inPos] & 511) >>> 8 | (in[17 + inPos] & 511) << 1) & 255);
         out[20 + outPos] = (byte)(((in[17 + inPos] & 511) >>> 7 | (in[18 + inPos] & 511) << 2) & 255);
         out[21 + outPos] = (byte)(((in[18 + inPos] & 511) >>> 6 | (in[19 + inPos] & 511) << 3) & 255);
         out[22 + outPos] = (byte)(((in[19 + inPos] & 511) >>> 5 | (in[20 + inPos] & 511) << 4) & 255);
         out[23 + outPos] = (byte)(((in[20 + inPos] & 511) >>> 4 | (in[21 + inPos] & 511) << 5) & 255);
         out[24 + outPos] = (byte)(((in[21 + inPos] & 511) >>> 3 | (in[22 + inPos] & 511) << 6) & 255);
         out[25 + outPos] = (byte)(((in[22 + inPos] & 511) >>> 2 | (in[23 + inPos] & 511) << 7) & 255);
         out[26 + outPos] = (byte)((in[23 + inPos] & 511) >>> 1 & 255);
         out[27 + outPos] = (byte)(in[24 + inPos] & 511 & 255);
         out[28 + outPos] = (byte)(((in[24 + inPos] & 511) >>> 8 | (in[25 + inPos] & 511) << 1) & 255);
         out[29 + outPos] = (byte)(((in[25 + inPos] & 511) >>> 7 | (in[26 + inPos] & 511) << 2) & 255);
         out[30 + outPos] = (byte)(((in[26 + inPos] & 511) >>> 6 | (in[27 + inPos] & 511) << 3) & 255);
         out[31 + outPos] = (byte)(((in[27 + inPos] & 511) >>> 5 | (in[28 + inPos] & 511) << 4) & 255);
         out[32 + outPos] = (byte)(((in[28 + inPos] & 511) >>> 4 | (in[29 + inPos] & 511) << 5) & 255);
         out[33 + outPos] = (byte)(((in[29 + inPos] & 511) >>> 3 | (in[30 + inPos] & 511) << 6) & 255);
         out[34 + outPos] = (byte)(((in[30 + inPos] & 511) >>> 2 | (in[31 + inPos] & 511) << 7) & 255);
         out[35 + outPos] = (byte)((in[31 + inPos] & 511) >>> 1 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & 511;
         out[1 + outPos] = in[1 + inPos] >> 1 & 127 | in[2 + inPos] << 7 & 511;
         out[2 + outPos] = in[2 + inPos] >> 2 & 63 | in[3 + inPos] << 6 & 511;
         out[3 + outPos] = in[3 + inPos] >> 3 & 31 | in[4 + inPos] << 5 & 511;
         out[4 + outPos] = in[4 + inPos] >> 4 & 15 | in[5 + inPos] << 4 & 511;
         out[5 + outPos] = in[5 + inPos] >> 5 & 7 | in[6 + inPos] << 3 & 511;
         out[6 + outPos] = in[6 + inPos] >> 6 & 3 | in[7 + inPos] << 2 & 511;
         out[7 + outPos] = in[7 + inPos] >> 7 & 1 | in[8 + inPos] << 1 & 511;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & 511;
         out[1 + outPos] = in.get(1 + inPos) >> 1 & 127 | in.get(2 + inPos) << 7 & 511;
         out[2 + outPos] = in.get(2 + inPos) >> 2 & 63 | in.get(3 + inPos) << 6 & 511;
         out[3 + outPos] = in.get(3 + inPos) >> 3 & 31 | in.get(4 + inPos) << 5 & 511;
         out[4 + outPos] = in.get(4 + inPos) >> 4 & 15 | in.get(5 + inPos) << 4 & 511;
         out[5 + outPos] = in.get(5 + inPos) >> 5 & 7 | in.get(6 + inPos) << 3 & 511;
         out[6 + outPos] = in.get(6 + inPos) >> 6 & 3 | in.get(7 + inPos) << 2 & 511;
         out[7 + outPos] = in.get(7 + inPos) >> 7 & 1 | in.get(8 + inPos) << 1 & 511;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & 511;
         out[1 + outPos] = in[1 + inPos] >> 1 & 127 | in[2 + inPos] << 7 & 511;
         out[2 + outPos] = in[2 + inPos] >> 2 & 63 | in[3 + inPos] << 6 & 511;
         out[3 + outPos] = in[3 + inPos] >> 3 & 31 | in[4 + inPos] << 5 & 511;
         out[4 + outPos] = in[4 + inPos] >> 4 & 15 | in[5 + inPos] << 4 & 511;
         out[5 + outPos] = in[5 + inPos] >> 5 & 7 | in[6 + inPos] << 3 & 511;
         out[6 + outPos] = in[6 + inPos] >> 6 & 3 | in[7 + inPos] << 2 & 511;
         out[7 + outPos] = in[7 + inPos] >> 7 & 1 | in[8 + inPos] << 1 & 511;
         out[8 + outPos] = in[9 + inPos] & 255 | in[10 + inPos] << 8 & 511;
         out[9 + outPos] = in[10 + inPos] >> 1 & 127 | in[11 + inPos] << 7 & 511;
         out[10 + outPos] = in[11 + inPos] >> 2 & 63 | in[12 + inPos] << 6 & 511;
         out[11 + outPos] = in[12 + inPos] >> 3 & 31 | in[13 + inPos] << 5 & 511;
         out[12 + outPos] = in[13 + inPos] >> 4 & 15 | in[14 + inPos] << 4 & 511;
         out[13 + outPos] = in[14 + inPos] >> 5 & 7 | in[15 + inPos] << 3 & 511;
         out[14 + outPos] = in[15 + inPos] >> 6 & 3 | in[16 + inPos] << 2 & 511;
         out[15 + outPos] = in[16 + inPos] >> 7 & 1 | in[17 + inPos] << 1 & 511;
         out[16 + outPos] = in[18 + inPos] & 255 | in[19 + inPos] << 8 & 511;
         out[17 + outPos] = in[19 + inPos] >> 1 & 127 | in[20 + inPos] << 7 & 511;
         out[18 + outPos] = in[20 + inPos] >> 2 & 63 | in[21 + inPos] << 6 & 511;
         out[19 + outPos] = in[21 + inPos] >> 3 & 31 | in[22 + inPos] << 5 & 511;
         out[20 + outPos] = in[22 + inPos] >> 4 & 15 | in[23 + inPos] << 4 & 511;
         out[21 + outPos] = in[23 + inPos] >> 5 & 7 | in[24 + inPos] << 3 & 511;
         out[22 + outPos] = in[24 + inPos] >> 6 & 3 | in[25 + inPos] << 2 & 511;
         out[23 + outPos] = in[25 + inPos] >> 7 & 1 | in[26 + inPos] << 1 & 511;
         out[24 + outPos] = in[27 + inPos] & 255 | in[28 + inPos] << 8 & 511;
         out[25 + outPos] = in[28 + inPos] >> 1 & 127 | in[29 + inPos] << 7 & 511;
         out[26 + outPos] = in[29 + inPos] >> 2 & 63 | in[30 + inPos] << 6 & 511;
         out[27 + outPos] = in[30 + inPos] >> 3 & 31 | in[31 + inPos] << 5 & 511;
         out[28 + outPos] = in[31 + inPos] >> 4 & 15 | in[32 + inPos] << 4 & 511;
         out[29 + outPos] = in[32 + inPos] >> 5 & 7 | in[33 + inPos] << 3 & 511;
         out[30 + outPos] = in[33 + inPos] >> 6 & 3 | in[34 + inPos] << 2 & 511;
         out[31 + outPos] = in[34 + inPos] >> 7 & 1 | in[35 + inPos] << 1 & 511;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & 511;
         out[1 + outPos] = in.get(1 + inPos) >> 1 & 127 | in.get(2 + inPos) << 7 & 511;
         out[2 + outPos] = in.get(2 + inPos) >> 2 & 63 | in.get(3 + inPos) << 6 & 511;
         out[3 + outPos] = in.get(3 + inPos) >> 3 & 31 | in.get(4 + inPos) << 5 & 511;
         out[4 + outPos] = in.get(4 + inPos) >> 4 & 15 | in.get(5 + inPos) << 4 & 511;
         out[5 + outPos] = in.get(5 + inPos) >> 5 & 7 | in.get(6 + inPos) << 3 & 511;
         out[6 + outPos] = in.get(6 + inPos) >> 6 & 3 | in.get(7 + inPos) << 2 & 511;
         out[7 + outPos] = in.get(7 + inPos) >> 7 & 1 | in.get(8 + inPos) << 1 & 511;
         out[8 + outPos] = in.get(9 + inPos) & 255 | in.get(10 + inPos) << 8 & 511;
         out[9 + outPos] = in.get(10 + inPos) >> 1 & 127 | in.get(11 + inPos) << 7 & 511;
         out[10 + outPos] = in.get(11 + inPos) >> 2 & 63 | in.get(12 + inPos) << 6 & 511;
         out[11 + outPos] = in.get(12 + inPos) >> 3 & 31 | in.get(13 + inPos) << 5 & 511;
         out[12 + outPos] = in.get(13 + inPos) >> 4 & 15 | in.get(14 + inPos) << 4 & 511;
         out[13 + outPos] = in.get(14 + inPos) >> 5 & 7 | in.get(15 + inPos) << 3 & 511;
         out[14 + outPos] = in.get(15 + inPos) >> 6 & 3 | in.get(16 + inPos) << 2 & 511;
         out[15 + outPos] = in.get(16 + inPos) >> 7 & 1 | in.get(17 + inPos) << 1 & 511;
         out[16 + outPos] = in.get(18 + inPos) & 255 | in.get(19 + inPos) << 8 & 511;
         out[17 + outPos] = in.get(19 + inPos) >> 1 & 127 | in.get(20 + inPos) << 7 & 511;
         out[18 + outPos] = in.get(20 + inPos) >> 2 & 63 | in.get(21 + inPos) << 6 & 511;
         out[19 + outPos] = in.get(21 + inPos) >> 3 & 31 | in.get(22 + inPos) << 5 & 511;
         out[20 + outPos] = in.get(22 + inPos) >> 4 & 15 | in.get(23 + inPos) << 4 & 511;
         out[21 + outPos] = in.get(23 + inPos) >> 5 & 7 | in.get(24 + inPos) << 3 & 511;
         out[22 + outPos] = in.get(24 + inPos) >> 6 & 3 | in.get(25 + inPos) << 2 & 511;
         out[23 + outPos] = in.get(25 + inPos) >> 7 & 1 | in.get(26 + inPos) << 1 & 511;
         out[24 + outPos] = in.get(27 + inPos) & 255 | in.get(28 + inPos) << 8 & 511;
         out[25 + outPos] = in.get(28 + inPos) >> 1 & 127 | in.get(29 + inPos) << 7 & 511;
         out[26 + outPos] = in.get(29 + inPos) >> 2 & 63 | in.get(30 + inPos) << 6 & 511;
         out[27 + outPos] = in.get(30 + inPos) >> 3 & 31 | in.get(31 + inPos) << 5 & 511;
         out[28 + outPos] = in.get(31 + inPos) >> 4 & 15 | in.get(32 + inPos) << 4 & 511;
         out[29 + outPos] = in.get(32 + inPos) >> 5 & 7 | in.get(33 + inPos) << 3 & 511;
         out[30 + outPos] = in.get(33 + inPos) >> 6 & 3 | in.get(34 + inPos) << 2 & 511;
         out[31 + outPos] = in.get(34 + inPos) >> 7 & 1 | in.get(35 + inPos) << 1 & 511;
      }
   }

   private static final class Packer10 extends BytePacker {
      private Packer10() {
         super(10);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 1023 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 1023) >>> 8 | (in[1 + inPos] & 1023) << 2) & 255);
         out[2 + outPos] = (byte)(((in[1 + inPos] & 1023) >>> 6 | (in[2 + inPos] & 1023) << 4) & 255);
         out[3 + outPos] = (byte)(((in[2 + inPos] & 1023) >>> 4 | (in[3 + inPos] & 1023) << 6) & 255);
         out[4 + outPos] = (byte)((in[3 + inPos] & 1023) >>> 2 & 255);
         out[5 + outPos] = (byte)(in[4 + inPos] & 1023 & 255);
         out[6 + outPos] = (byte)(((in[4 + inPos] & 1023) >>> 8 | (in[5 + inPos] & 1023) << 2) & 255);
         out[7 + outPos] = (byte)(((in[5 + inPos] & 1023) >>> 6 | (in[6 + inPos] & 1023) << 4) & 255);
         out[8 + outPos] = (byte)(((in[6 + inPos] & 1023) >>> 4 | (in[7 + inPos] & 1023) << 6) & 255);
         out[9 + outPos] = (byte)((in[7 + inPos] & 1023) >>> 2 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 1023 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 1023) >>> 8 | (in[1 + inPos] & 1023) << 2) & 255);
         out[2 + outPos] = (byte)(((in[1 + inPos] & 1023) >>> 6 | (in[2 + inPos] & 1023) << 4) & 255);
         out[3 + outPos] = (byte)(((in[2 + inPos] & 1023) >>> 4 | (in[3 + inPos] & 1023) << 6) & 255);
         out[4 + outPos] = (byte)((in[3 + inPos] & 1023) >>> 2 & 255);
         out[5 + outPos] = (byte)(in[4 + inPos] & 1023 & 255);
         out[6 + outPos] = (byte)(((in[4 + inPos] & 1023) >>> 8 | (in[5 + inPos] & 1023) << 2) & 255);
         out[7 + outPos] = (byte)(((in[5 + inPos] & 1023) >>> 6 | (in[6 + inPos] & 1023) << 4) & 255);
         out[8 + outPos] = (byte)(((in[6 + inPos] & 1023) >>> 4 | (in[7 + inPos] & 1023) << 6) & 255);
         out[9 + outPos] = (byte)((in[7 + inPos] & 1023) >>> 2 & 255);
         out[10 + outPos] = (byte)(in[8 + inPos] & 1023 & 255);
         out[11 + outPos] = (byte)(((in[8 + inPos] & 1023) >>> 8 | (in[9 + inPos] & 1023) << 2) & 255);
         out[12 + outPos] = (byte)(((in[9 + inPos] & 1023) >>> 6 | (in[10 + inPos] & 1023) << 4) & 255);
         out[13 + outPos] = (byte)(((in[10 + inPos] & 1023) >>> 4 | (in[11 + inPos] & 1023) << 6) & 255);
         out[14 + outPos] = (byte)((in[11 + inPos] & 1023) >>> 2 & 255);
         out[15 + outPos] = (byte)(in[12 + inPos] & 1023 & 255);
         out[16 + outPos] = (byte)(((in[12 + inPos] & 1023) >>> 8 | (in[13 + inPos] & 1023) << 2) & 255);
         out[17 + outPos] = (byte)(((in[13 + inPos] & 1023) >>> 6 | (in[14 + inPos] & 1023) << 4) & 255);
         out[18 + outPos] = (byte)(((in[14 + inPos] & 1023) >>> 4 | (in[15 + inPos] & 1023) << 6) & 255);
         out[19 + outPos] = (byte)((in[15 + inPos] & 1023) >>> 2 & 255);
         out[20 + outPos] = (byte)(in[16 + inPos] & 1023 & 255);
         out[21 + outPos] = (byte)(((in[16 + inPos] & 1023) >>> 8 | (in[17 + inPos] & 1023) << 2) & 255);
         out[22 + outPos] = (byte)(((in[17 + inPos] & 1023) >>> 6 | (in[18 + inPos] & 1023) << 4) & 255);
         out[23 + outPos] = (byte)(((in[18 + inPos] & 1023) >>> 4 | (in[19 + inPos] & 1023) << 6) & 255);
         out[24 + outPos] = (byte)((in[19 + inPos] & 1023) >>> 2 & 255);
         out[25 + outPos] = (byte)(in[20 + inPos] & 1023 & 255);
         out[26 + outPos] = (byte)(((in[20 + inPos] & 1023) >>> 8 | (in[21 + inPos] & 1023) << 2) & 255);
         out[27 + outPos] = (byte)(((in[21 + inPos] & 1023) >>> 6 | (in[22 + inPos] & 1023) << 4) & 255);
         out[28 + outPos] = (byte)(((in[22 + inPos] & 1023) >>> 4 | (in[23 + inPos] & 1023) << 6) & 255);
         out[29 + outPos] = (byte)((in[23 + inPos] & 1023) >>> 2 & 255);
         out[30 + outPos] = (byte)(in[24 + inPos] & 1023 & 255);
         out[31 + outPos] = (byte)(((in[24 + inPos] & 1023) >>> 8 | (in[25 + inPos] & 1023) << 2) & 255);
         out[32 + outPos] = (byte)(((in[25 + inPos] & 1023) >>> 6 | (in[26 + inPos] & 1023) << 4) & 255);
         out[33 + outPos] = (byte)(((in[26 + inPos] & 1023) >>> 4 | (in[27 + inPos] & 1023) << 6) & 255);
         out[34 + outPos] = (byte)((in[27 + inPos] & 1023) >>> 2 & 255);
         out[35 + outPos] = (byte)(in[28 + inPos] & 1023 & 255);
         out[36 + outPos] = (byte)(((in[28 + inPos] & 1023) >>> 8 | (in[29 + inPos] & 1023) << 2) & 255);
         out[37 + outPos] = (byte)(((in[29 + inPos] & 1023) >>> 6 | (in[30 + inPos] & 1023) << 4) & 255);
         out[38 + outPos] = (byte)(((in[30 + inPos] & 1023) >>> 4 | (in[31 + inPos] & 1023) << 6) & 255);
         out[39 + outPos] = (byte)((in[31 + inPos] & 1023) >>> 2 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & 1023;
         out[1 + outPos] = in[1 + inPos] >> 2 & 63 | in[2 + inPos] << 6 & 1023;
         out[2 + outPos] = in[2 + inPos] >> 4 & 15 | in[3 + inPos] << 4 & 1023;
         out[3 + outPos] = in[3 + inPos] >> 6 & 3 | in[4 + inPos] << 2 & 1023;
         out[4 + outPos] = in[5 + inPos] & 255 | in[6 + inPos] << 8 & 1023;
         out[5 + outPos] = in[6 + inPos] >> 2 & 63 | in[7 + inPos] << 6 & 1023;
         out[6 + outPos] = in[7 + inPos] >> 4 & 15 | in[8 + inPos] << 4 & 1023;
         out[7 + outPos] = in[8 + inPos] >> 6 & 3 | in[9 + inPos] << 2 & 1023;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & 1023;
         out[1 + outPos] = in.get(1 + inPos) >> 2 & 63 | in.get(2 + inPos) << 6 & 1023;
         out[2 + outPos] = in.get(2 + inPos) >> 4 & 15 | in.get(3 + inPos) << 4 & 1023;
         out[3 + outPos] = in.get(3 + inPos) >> 6 & 3 | in.get(4 + inPos) << 2 & 1023;
         out[4 + outPos] = in.get(5 + inPos) & 255 | in.get(6 + inPos) << 8 & 1023;
         out[5 + outPos] = in.get(6 + inPos) >> 2 & 63 | in.get(7 + inPos) << 6 & 1023;
         out[6 + outPos] = in.get(7 + inPos) >> 4 & 15 | in.get(8 + inPos) << 4 & 1023;
         out[7 + outPos] = in.get(8 + inPos) >> 6 & 3 | in.get(9 + inPos) << 2 & 1023;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & 1023;
         out[1 + outPos] = in[1 + inPos] >> 2 & 63 | in[2 + inPos] << 6 & 1023;
         out[2 + outPos] = in[2 + inPos] >> 4 & 15 | in[3 + inPos] << 4 & 1023;
         out[3 + outPos] = in[3 + inPos] >> 6 & 3 | in[4 + inPos] << 2 & 1023;
         out[4 + outPos] = in[5 + inPos] & 255 | in[6 + inPos] << 8 & 1023;
         out[5 + outPos] = in[6 + inPos] >> 2 & 63 | in[7 + inPos] << 6 & 1023;
         out[6 + outPos] = in[7 + inPos] >> 4 & 15 | in[8 + inPos] << 4 & 1023;
         out[7 + outPos] = in[8 + inPos] >> 6 & 3 | in[9 + inPos] << 2 & 1023;
         out[8 + outPos] = in[10 + inPos] & 255 | in[11 + inPos] << 8 & 1023;
         out[9 + outPos] = in[11 + inPos] >> 2 & 63 | in[12 + inPos] << 6 & 1023;
         out[10 + outPos] = in[12 + inPos] >> 4 & 15 | in[13 + inPos] << 4 & 1023;
         out[11 + outPos] = in[13 + inPos] >> 6 & 3 | in[14 + inPos] << 2 & 1023;
         out[12 + outPos] = in[15 + inPos] & 255 | in[16 + inPos] << 8 & 1023;
         out[13 + outPos] = in[16 + inPos] >> 2 & 63 | in[17 + inPos] << 6 & 1023;
         out[14 + outPos] = in[17 + inPos] >> 4 & 15 | in[18 + inPos] << 4 & 1023;
         out[15 + outPos] = in[18 + inPos] >> 6 & 3 | in[19 + inPos] << 2 & 1023;
         out[16 + outPos] = in[20 + inPos] & 255 | in[21 + inPos] << 8 & 1023;
         out[17 + outPos] = in[21 + inPos] >> 2 & 63 | in[22 + inPos] << 6 & 1023;
         out[18 + outPos] = in[22 + inPos] >> 4 & 15 | in[23 + inPos] << 4 & 1023;
         out[19 + outPos] = in[23 + inPos] >> 6 & 3 | in[24 + inPos] << 2 & 1023;
         out[20 + outPos] = in[25 + inPos] & 255 | in[26 + inPos] << 8 & 1023;
         out[21 + outPos] = in[26 + inPos] >> 2 & 63 | in[27 + inPos] << 6 & 1023;
         out[22 + outPos] = in[27 + inPos] >> 4 & 15 | in[28 + inPos] << 4 & 1023;
         out[23 + outPos] = in[28 + inPos] >> 6 & 3 | in[29 + inPos] << 2 & 1023;
         out[24 + outPos] = in[30 + inPos] & 255 | in[31 + inPos] << 8 & 1023;
         out[25 + outPos] = in[31 + inPos] >> 2 & 63 | in[32 + inPos] << 6 & 1023;
         out[26 + outPos] = in[32 + inPos] >> 4 & 15 | in[33 + inPos] << 4 & 1023;
         out[27 + outPos] = in[33 + inPos] >> 6 & 3 | in[34 + inPos] << 2 & 1023;
         out[28 + outPos] = in[35 + inPos] & 255 | in[36 + inPos] << 8 & 1023;
         out[29 + outPos] = in[36 + inPos] >> 2 & 63 | in[37 + inPos] << 6 & 1023;
         out[30 + outPos] = in[37 + inPos] >> 4 & 15 | in[38 + inPos] << 4 & 1023;
         out[31 + outPos] = in[38 + inPos] >> 6 & 3 | in[39 + inPos] << 2 & 1023;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & 1023;
         out[1 + outPos] = in.get(1 + inPos) >> 2 & 63 | in.get(2 + inPos) << 6 & 1023;
         out[2 + outPos] = in.get(2 + inPos) >> 4 & 15 | in.get(3 + inPos) << 4 & 1023;
         out[3 + outPos] = in.get(3 + inPos) >> 6 & 3 | in.get(4 + inPos) << 2 & 1023;
         out[4 + outPos] = in.get(5 + inPos) & 255 | in.get(6 + inPos) << 8 & 1023;
         out[5 + outPos] = in.get(6 + inPos) >> 2 & 63 | in.get(7 + inPos) << 6 & 1023;
         out[6 + outPos] = in.get(7 + inPos) >> 4 & 15 | in.get(8 + inPos) << 4 & 1023;
         out[7 + outPos] = in.get(8 + inPos) >> 6 & 3 | in.get(9 + inPos) << 2 & 1023;
         out[8 + outPos] = in.get(10 + inPos) & 255 | in.get(11 + inPos) << 8 & 1023;
         out[9 + outPos] = in.get(11 + inPos) >> 2 & 63 | in.get(12 + inPos) << 6 & 1023;
         out[10 + outPos] = in.get(12 + inPos) >> 4 & 15 | in.get(13 + inPos) << 4 & 1023;
         out[11 + outPos] = in.get(13 + inPos) >> 6 & 3 | in.get(14 + inPos) << 2 & 1023;
         out[12 + outPos] = in.get(15 + inPos) & 255 | in.get(16 + inPos) << 8 & 1023;
         out[13 + outPos] = in.get(16 + inPos) >> 2 & 63 | in.get(17 + inPos) << 6 & 1023;
         out[14 + outPos] = in.get(17 + inPos) >> 4 & 15 | in.get(18 + inPos) << 4 & 1023;
         out[15 + outPos] = in.get(18 + inPos) >> 6 & 3 | in.get(19 + inPos) << 2 & 1023;
         out[16 + outPos] = in.get(20 + inPos) & 255 | in.get(21 + inPos) << 8 & 1023;
         out[17 + outPos] = in.get(21 + inPos) >> 2 & 63 | in.get(22 + inPos) << 6 & 1023;
         out[18 + outPos] = in.get(22 + inPos) >> 4 & 15 | in.get(23 + inPos) << 4 & 1023;
         out[19 + outPos] = in.get(23 + inPos) >> 6 & 3 | in.get(24 + inPos) << 2 & 1023;
         out[20 + outPos] = in.get(25 + inPos) & 255 | in.get(26 + inPos) << 8 & 1023;
         out[21 + outPos] = in.get(26 + inPos) >> 2 & 63 | in.get(27 + inPos) << 6 & 1023;
         out[22 + outPos] = in.get(27 + inPos) >> 4 & 15 | in.get(28 + inPos) << 4 & 1023;
         out[23 + outPos] = in.get(28 + inPos) >> 6 & 3 | in.get(29 + inPos) << 2 & 1023;
         out[24 + outPos] = in.get(30 + inPos) & 255 | in.get(31 + inPos) << 8 & 1023;
         out[25 + outPos] = in.get(31 + inPos) >> 2 & 63 | in.get(32 + inPos) << 6 & 1023;
         out[26 + outPos] = in.get(32 + inPos) >> 4 & 15 | in.get(33 + inPos) << 4 & 1023;
         out[27 + outPos] = in.get(33 + inPos) >> 6 & 3 | in.get(34 + inPos) << 2 & 1023;
         out[28 + outPos] = in.get(35 + inPos) & 255 | in.get(36 + inPos) << 8 & 1023;
         out[29 + outPos] = in.get(36 + inPos) >> 2 & 63 | in.get(37 + inPos) << 6 & 1023;
         out[30 + outPos] = in.get(37 + inPos) >> 4 & 15 | in.get(38 + inPos) << 4 & 1023;
         out[31 + outPos] = in.get(38 + inPos) >> 6 & 3 | in.get(39 + inPos) << 2 & 1023;
      }
   }

   private static final class Packer11 extends BytePacker {
      private Packer11() {
         super(11);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 2047 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 2047) >>> 8 | (in[1 + inPos] & 2047) << 3) & 255);
         out[2 + outPos] = (byte)(((in[1 + inPos] & 2047) >>> 5 | (in[2 + inPos] & 2047) << 6) & 255);
         out[3 + outPos] = (byte)((in[2 + inPos] & 2047) >>> 2 & 255);
         out[4 + outPos] = (byte)(((in[2 + inPos] & 2047) >>> 10 | (in[3 + inPos] & 2047) << 1) & 255);
         out[5 + outPos] = (byte)(((in[3 + inPos] & 2047) >>> 7 | (in[4 + inPos] & 2047) << 4) & 255);
         out[6 + outPos] = (byte)(((in[4 + inPos] & 2047) >>> 4 | (in[5 + inPos] & 2047) << 7) & 255);
         out[7 + outPos] = (byte)((in[5 + inPos] & 2047) >>> 1 & 255);
         out[8 + outPos] = (byte)(((in[5 + inPos] & 2047) >>> 9 | (in[6 + inPos] & 2047) << 2) & 255);
         out[9 + outPos] = (byte)(((in[6 + inPos] & 2047) >>> 6 | (in[7 + inPos] & 2047) << 5) & 255);
         out[10 + outPos] = (byte)((in[7 + inPos] & 2047) >>> 3 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 2047 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 2047) >>> 8 | (in[1 + inPos] & 2047) << 3) & 255);
         out[2 + outPos] = (byte)(((in[1 + inPos] & 2047) >>> 5 | (in[2 + inPos] & 2047) << 6) & 255);
         out[3 + outPos] = (byte)((in[2 + inPos] & 2047) >>> 2 & 255);
         out[4 + outPos] = (byte)(((in[2 + inPos] & 2047) >>> 10 | (in[3 + inPos] & 2047) << 1) & 255);
         out[5 + outPos] = (byte)(((in[3 + inPos] & 2047) >>> 7 | (in[4 + inPos] & 2047) << 4) & 255);
         out[6 + outPos] = (byte)(((in[4 + inPos] & 2047) >>> 4 | (in[5 + inPos] & 2047) << 7) & 255);
         out[7 + outPos] = (byte)((in[5 + inPos] & 2047) >>> 1 & 255);
         out[8 + outPos] = (byte)(((in[5 + inPos] & 2047) >>> 9 | (in[6 + inPos] & 2047) << 2) & 255);
         out[9 + outPos] = (byte)(((in[6 + inPos] & 2047) >>> 6 | (in[7 + inPos] & 2047) << 5) & 255);
         out[10 + outPos] = (byte)((in[7 + inPos] & 2047) >>> 3 & 255);
         out[11 + outPos] = (byte)(in[8 + inPos] & 2047 & 255);
         out[12 + outPos] = (byte)(((in[8 + inPos] & 2047) >>> 8 | (in[9 + inPos] & 2047) << 3) & 255);
         out[13 + outPos] = (byte)(((in[9 + inPos] & 2047) >>> 5 | (in[10 + inPos] & 2047) << 6) & 255);
         out[14 + outPos] = (byte)((in[10 + inPos] & 2047) >>> 2 & 255);
         out[15 + outPos] = (byte)(((in[10 + inPos] & 2047) >>> 10 | (in[11 + inPos] & 2047) << 1) & 255);
         out[16 + outPos] = (byte)(((in[11 + inPos] & 2047) >>> 7 | (in[12 + inPos] & 2047) << 4) & 255);
         out[17 + outPos] = (byte)(((in[12 + inPos] & 2047) >>> 4 | (in[13 + inPos] & 2047) << 7) & 255);
         out[18 + outPos] = (byte)((in[13 + inPos] & 2047) >>> 1 & 255);
         out[19 + outPos] = (byte)(((in[13 + inPos] & 2047) >>> 9 | (in[14 + inPos] & 2047) << 2) & 255);
         out[20 + outPos] = (byte)(((in[14 + inPos] & 2047) >>> 6 | (in[15 + inPos] & 2047) << 5) & 255);
         out[21 + outPos] = (byte)((in[15 + inPos] & 2047) >>> 3 & 255);
         out[22 + outPos] = (byte)(in[16 + inPos] & 2047 & 255);
         out[23 + outPos] = (byte)(((in[16 + inPos] & 2047) >>> 8 | (in[17 + inPos] & 2047) << 3) & 255);
         out[24 + outPos] = (byte)(((in[17 + inPos] & 2047) >>> 5 | (in[18 + inPos] & 2047) << 6) & 255);
         out[25 + outPos] = (byte)((in[18 + inPos] & 2047) >>> 2 & 255);
         out[26 + outPos] = (byte)(((in[18 + inPos] & 2047) >>> 10 | (in[19 + inPos] & 2047) << 1) & 255);
         out[27 + outPos] = (byte)(((in[19 + inPos] & 2047) >>> 7 | (in[20 + inPos] & 2047) << 4) & 255);
         out[28 + outPos] = (byte)(((in[20 + inPos] & 2047) >>> 4 | (in[21 + inPos] & 2047) << 7) & 255);
         out[29 + outPos] = (byte)((in[21 + inPos] & 2047) >>> 1 & 255);
         out[30 + outPos] = (byte)(((in[21 + inPos] & 2047) >>> 9 | (in[22 + inPos] & 2047) << 2) & 255);
         out[31 + outPos] = (byte)(((in[22 + inPos] & 2047) >>> 6 | (in[23 + inPos] & 2047) << 5) & 255);
         out[32 + outPos] = (byte)((in[23 + inPos] & 2047) >>> 3 & 255);
         out[33 + outPos] = (byte)(in[24 + inPos] & 2047 & 255);
         out[34 + outPos] = (byte)(((in[24 + inPos] & 2047) >>> 8 | (in[25 + inPos] & 2047) << 3) & 255);
         out[35 + outPos] = (byte)(((in[25 + inPos] & 2047) >>> 5 | (in[26 + inPos] & 2047) << 6) & 255);
         out[36 + outPos] = (byte)((in[26 + inPos] & 2047) >>> 2 & 255);
         out[37 + outPos] = (byte)(((in[26 + inPos] & 2047) >>> 10 | (in[27 + inPos] & 2047) << 1) & 255);
         out[38 + outPos] = (byte)(((in[27 + inPos] & 2047) >>> 7 | (in[28 + inPos] & 2047) << 4) & 255);
         out[39 + outPos] = (byte)(((in[28 + inPos] & 2047) >>> 4 | (in[29 + inPos] & 2047) << 7) & 255);
         out[40 + outPos] = (byte)((in[29 + inPos] & 2047) >>> 1 & 255);
         out[41 + outPos] = (byte)(((in[29 + inPos] & 2047) >>> 9 | (in[30 + inPos] & 2047) << 2) & 255);
         out[42 + outPos] = (byte)(((in[30 + inPos] & 2047) >>> 6 | (in[31 + inPos] & 2047) << 5) & 255);
         out[43 + outPos] = (byte)((in[31 + inPos] & 2047) >>> 3 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & 2047;
         out[1 + outPos] = in[1 + inPos] >> 3 & 31 | in[2 + inPos] << 5 & 2047;
         out[2 + outPos] = in[2 + inPos] >> 6 & 3 | in[3 + inPos] << 2 & 1023 | in[4 + inPos] << 10 & 2047;
         out[3 + outPos] = in[4 + inPos] >> 1 & 127 | in[5 + inPos] << 7 & 2047;
         out[4 + outPos] = in[5 + inPos] >> 4 & 15 | in[6 + inPos] << 4 & 2047;
         out[5 + outPos] = in[6 + inPos] >> 7 & 1 | in[7 + inPos] << 1 & 511 | in[8 + inPos] << 9 & 2047;
         out[6 + outPos] = in[8 + inPos] >> 2 & 63 | in[9 + inPos] << 6 & 2047;
         out[7 + outPos] = in[9 + inPos] >> 5 & 7 | in[10 + inPos] << 3 & 2047;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & 2047;
         out[1 + outPos] = in.get(1 + inPos) >> 3 & 31 | in.get(2 + inPos) << 5 & 2047;
         out[2 + outPos] = in.get(2 + inPos) >> 6 & 3 | in.get(3 + inPos) << 2 & 1023 | in.get(4 + inPos) << 10 & 2047;
         out[3 + outPos] = in.get(4 + inPos) >> 1 & 127 | in.get(5 + inPos) << 7 & 2047;
         out[4 + outPos] = in.get(5 + inPos) >> 4 & 15 | in.get(6 + inPos) << 4 & 2047;
         out[5 + outPos] = in.get(6 + inPos) >> 7 & 1 | in.get(7 + inPos) << 1 & 511 | in.get(8 + inPos) << 9 & 2047;
         out[6 + outPos] = in.get(8 + inPos) >> 2 & 63 | in.get(9 + inPos) << 6 & 2047;
         out[7 + outPos] = in.get(9 + inPos) >> 5 & 7 | in.get(10 + inPos) << 3 & 2047;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & 2047;
         out[1 + outPos] = in[1 + inPos] >> 3 & 31 | in[2 + inPos] << 5 & 2047;
         out[2 + outPos] = in[2 + inPos] >> 6 & 3 | in[3 + inPos] << 2 & 1023 | in[4 + inPos] << 10 & 2047;
         out[3 + outPos] = in[4 + inPos] >> 1 & 127 | in[5 + inPos] << 7 & 2047;
         out[4 + outPos] = in[5 + inPos] >> 4 & 15 | in[6 + inPos] << 4 & 2047;
         out[5 + outPos] = in[6 + inPos] >> 7 & 1 | in[7 + inPos] << 1 & 511 | in[8 + inPos] << 9 & 2047;
         out[6 + outPos] = in[8 + inPos] >> 2 & 63 | in[9 + inPos] << 6 & 2047;
         out[7 + outPos] = in[9 + inPos] >> 5 & 7 | in[10 + inPos] << 3 & 2047;
         out[8 + outPos] = in[11 + inPos] & 255 | in[12 + inPos] << 8 & 2047;
         out[9 + outPos] = in[12 + inPos] >> 3 & 31 | in[13 + inPos] << 5 & 2047;
         out[10 + outPos] = in[13 + inPos] >> 6 & 3 | in[14 + inPos] << 2 & 1023 | in[15 + inPos] << 10 & 2047;
         out[11 + outPos] = in[15 + inPos] >> 1 & 127 | in[16 + inPos] << 7 & 2047;
         out[12 + outPos] = in[16 + inPos] >> 4 & 15 | in[17 + inPos] << 4 & 2047;
         out[13 + outPos] = in[17 + inPos] >> 7 & 1 | in[18 + inPos] << 1 & 511 | in[19 + inPos] << 9 & 2047;
         out[14 + outPos] = in[19 + inPos] >> 2 & 63 | in[20 + inPos] << 6 & 2047;
         out[15 + outPos] = in[20 + inPos] >> 5 & 7 | in[21 + inPos] << 3 & 2047;
         out[16 + outPos] = in[22 + inPos] & 255 | in[23 + inPos] << 8 & 2047;
         out[17 + outPos] = in[23 + inPos] >> 3 & 31 | in[24 + inPos] << 5 & 2047;
         out[18 + outPos] = in[24 + inPos] >> 6 & 3 | in[25 + inPos] << 2 & 1023 | in[26 + inPos] << 10 & 2047;
         out[19 + outPos] = in[26 + inPos] >> 1 & 127 | in[27 + inPos] << 7 & 2047;
         out[20 + outPos] = in[27 + inPos] >> 4 & 15 | in[28 + inPos] << 4 & 2047;
         out[21 + outPos] = in[28 + inPos] >> 7 & 1 | in[29 + inPos] << 1 & 511 | in[30 + inPos] << 9 & 2047;
         out[22 + outPos] = in[30 + inPos] >> 2 & 63 | in[31 + inPos] << 6 & 2047;
         out[23 + outPos] = in[31 + inPos] >> 5 & 7 | in[32 + inPos] << 3 & 2047;
         out[24 + outPos] = in[33 + inPos] & 255 | in[34 + inPos] << 8 & 2047;
         out[25 + outPos] = in[34 + inPos] >> 3 & 31 | in[35 + inPos] << 5 & 2047;
         out[26 + outPos] = in[35 + inPos] >> 6 & 3 | in[36 + inPos] << 2 & 1023 | in[37 + inPos] << 10 & 2047;
         out[27 + outPos] = in[37 + inPos] >> 1 & 127 | in[38 + inPos] << 7 & 2047;
         out[28 + outPos] = in[38 + inPos] >> 4 & 15 | in[39 + inPos] << 4 & 2047;
         out[29 + outPos] = in[39 + inPos] >> 7 & 1 | in[40 + inPos] << 1 & 511 | in[41 + inPos] << 9 & 2047;
         out[30 + outPos] = in[41 + inPos] >> 2 & 63 | in[42 + inPos] << 6 & 2047;
         out[31 + outPos] = in[42 + inPos] >> 5 & 7 | in[43 + inPos] << 3 & 2047;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & 2047;
         out[1 + outPos] = in.get(1 + inPos) >> 3 & 31 | in.get(2 + inPos) << 5 & 2047;
         out[2 + outPos] = in.get(2 + inPos) >> 6 & 3 | in.get(3 + inPos) << 2 & 1023 | in.get(4 + inPos) << 10 & 2047;
         out[3 + outPos] = in.get(4 + inPos) >> 1 & 127 | in.get(5 + inPos) << 7 & 2047;
         out[4 + outPos] = in.get(5 + inPos) >> 4 & 15 | in.get(6 + inPos) << 4 & 2047;
         out[5 + outPos] = in.get(6 + inPos) >> 7 & 1 | in.get(7 + inPos) << 1 & 511 | in.get(8 + inPos) << 9 & 2047;
         out[6 + outPos] = in.get(8 + inPos) >> 2 & 63 | in.get(9 + inPos) << 6 & 2047;
         out[7 + outPos] = in.get(9 + inPos) >> 5 & 7 | in.get(10 + inPos) << 3 & 2047;
         out[8 + outPos] = in.get(11 + inPos) & 255 | in.get(12 + inPos) << 8 & 2047;
         out[9 + outPos] = in.get(12 + inPos) >> 3 & 31 | in.get(13 + inPos) << 5 & 2047;
         out[10 + outPos] = in.get(13 + inPos) >> 6 & 3 | in.get(14 + inPos) << 2 & 1023 | in.get(15 + inPos) << 10 & 2047;
         out[11 + outPos] = in.get(15 + inPos) >> 1 & 127 | in.get(16 + inPos) << 7 & 2047;
         out[12 + outPos] = in.get(16 + inPos) >> 4 & 15 | in.get(17 + inPos) << 4 & 2047;
         out[13 + outPos] = in.get(17 + inPos) >> 7 & 1 | in.get(18 + inPos) << 1 & 511 | in.get(19 + inPos) << 9 & 2047;
         out[14 + outPos] = in.get(19 + inPos) >> 2 & 63 | in.get(20 + inPos) << 6 & 2047;
         out[15 + outPos] = in.get(20 + inPos) >> 5 & 7 | in.get(21 + inPos) << 3 & 2047;
         out[16 + outPos] = in.get(22 + inPos) & 255 | in.get(23 + inPos) << 8 & 2047;
         out[17 + outPos] = in.get(23 + inPos) >> 3 & 31 | in.get(24 + inPos) << 5 & 2047;
         out[18 + outPos] = in.get(24 + inPos) >> 6 & 3 | in.get(25 + inPos) << 2 & 1023 | in.get(26 + inPos) << 10 & 2047;
         out[19 + outPos] = in.get(26 + inPos) >> 1 & 127 | in.get(27 + inPos) << 7 & 2047;
         out[20 + outPos] = in.get(27 + inPos) >> 4 & 15 | in.get(28 + inPos) << 4 & 2047;
         out[21 + outPos] = in.get(28 + inPos) >> 7 & 1 | in.get(29 + inPos) << 1 & 511 | in.get(30 + inPos) << 9 & 2047;
         out[22 + outPos] = in.get(30 + inPos) >> 2 & 63 | in.get(31 + inPos) << 6 & 2047;
         out[23 + outPos] = in.get(31 + inPos) >> 5 & 7 | in.get(32 + inPos) << 3 & 2047;
         out[24 + outPos] = in.get(33 + inPos) & 255 | in.get(34 + inPos) << 8 & 2047;
         out[25 + outPos] = in.get(34 + inPos) >> 3 & 31 | in.get(35 + inPos) << 5 & 2047;
         out[26 + outPos] = in.get(35 + inPos) >> 6 & 3 | in.get(36 + inPos) << 2 & 1023 | in.get(37 + inPos) << 10 & 2047;
         out[27 + outPos] = in.get(37 + inPos) >> 1 & 127 | in.get(38 + inPos) << 7 & 2047;
         out[28 + outPos] = in.get(38 + inPos) >> 4 & 15 | in.get(39 + inPos) << 4 & 2047;
         out[29 + outPos] = in.get(39 + inPos) >> 7 & 1 | in.get(40 + inPos) << 1 & 511 | in.get(41 + inPos) << 9 & 2047;
         out[30 + outPos] = in.get(41 + inPos) >> 2 & 63 | in.get(42 + inPos) << 6 & 2047;
         out[31 + outPos] = in.get(42 + inPos) >> 5 & 7 | in.get(43 + inPos) << 3 & 2047;
      }
   }

   private static final class Packer12 extends BytePacker {
      private Packer12() {
         super(12);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 4095 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 4095) >>> 8 | (in[1 + inPos] & 4095) << 4) & 255);
         out[2 + outPos] = (byte)((in[1 + inPos] & 4095) >>> 4 & 255);
         out[3 + outPos] = (byte)(in[2 + inPos] & 4095 & 255);
         out[4 + outPos] = (byte)(((in[2 + inPos] & 4095) >>> 8 | (in[3 + inPos] & 4095) << 4) & 255);
         out[5 + outPos] = (byte)((in[3 + inPos] & 4095) >>> 4 & 255);
         out[6 + outPos] = (byte)(in[4 + inPos] & 4095 & 255);
         out[7 + outPos] = (byte)(((in[4 + inPos] & 4095) >>> 8 | (in[5 + inPos] & 4095) << 4) & 255);
         out[8 + outPos] = (byte)((in[5 + inPos] & 4095) >>> 4 & 255);
         out[9 + outPos] = (byte)(in[6 + inPos] & 4095 & 255);
         out[10 + outPos] = (byte)(((in[6 + inPos] & 4095) >>> 8 | (in[7 + inPos] & 4095) << 4) & 255);
         out[11 + outPos] = (byte)((in[7 + inPos] & 4095) >>> 4 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 4095 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 4095) >>> 8 | (in[1 + inPos] & 4095) << 4) & 255);
         out[2 + outPos] = (byte)((in[1 + inPos] & 4095) >>> 4 & 255);
         out[3 + outPos] = (byte)(in[2 + inPos] & 4095 & 255);
         out[4 + outPos] = (byte)(((in[2 + inPos] & 4095) >>> 8 | (in[3 + inPos] & 4095) << 4) & 255);
         out[5 + outPos] = (byte)((in[3 + inPos] & 4095) >>> 4 & 255);
         out[6 + outPos] = (byte)(in[4 + inPos] & 4095 & 255);
         out[7 + outPos] = (byte)(((in[4 + inPos] & 4095) >>> 8 | (in[5 + inPos] & 4095) << 4) & 255);
         out[8 + outPos] = (byte)((in[5 + inPos] & 4095) >>> 4 & 255);
         out[9 + outPos] = (byte)(in[6 + inPos] & 4095 & 255);
         out[10 + outPos] = (byte)(((in[6 + inPos] & 4095) >>> 8 | (in[7 + inPos] & 4095) << 4) & 255);
         out[11 + outPos] = (byte)((in[7 + inPos] & 4095) >>> 4 & 255);
         out[12 + outPos] = (byte)(in[8 + inPos] & 4095 & 255);
         out[13 + outPos] = (byte)(((in[8 + inPos] & 4095) >>> 8 | (in[9 + inPos] & 4095) << 4) & 255);
         out[14 + outPos] = (byte)((in[9 + inPos] & 4095) >>> 4 & 255);
         out[15 + outPos] = (byte)(in[10 + inPos] & 4095 & 255);
         out[16 + outPos] = (byte)(((in[10 + inPos] & 4095) >>> 8 | (in[11 + inPos] & 4095) << 4) & 255);
         out[17 + outPos] = (byte)((in[11 + inPos] & 4095) >>> 4 & 255);
         out[18 + outPos] = (byte)(in[12 + inPos] & 4095 & 255);
         out[19 + outPos] = (byte)(((in[12 + inPos] & 4095) >>> 8 | (in[13 + inPos] & 4095) << 4) & 255);
         out[20 + outPos] = (byte)((in[13 + inPos] & 4095) >>> 4 & 255);
         out[21 + outPos] = (byte)(in[14 + inPos] & 4095 & 255);
         out[22 + outPos] = (byte)(((in[14 + inPos] & 4095) >>> 8 | (in[15 + inPos] & 4095) << 4) & 255);
         out[23 + outPos] = (byte)((in[15 + inPos] & 4095) >>> 4 & 255);
         out[24 + outPos] = (byte)(in[16 + inPos] & 4095 & 255);
         out[25 + outPos] = (byte)(((in[16 + inPos] & 4095) >>> 8 | (in[17 + inPos] & 4095) << 4) & 255);
         out[26 + outPos] = (byte)((in[17 + inPos] & 4095) >>> 4 & 255);
         out[27 + outPos] = (byte)(in[18 + inPos] & 4095 & 255);
         out[28 + outPos] = (byte)(((in[18 + inPos] & 4095) >>> 8 | (in[19 + inPos] & 4095) << 4) & 255);
         out[29 + outPos] = (byte)((in[19 + inPos] & 4095) >>> 4 & 255);
         out[30 + outPos] = (byte)(in[20 + inPos] & 4095 & 255);
         out[31 + outPos] = (byte)(((in[20 + inPos] & 4095) >>> 8 | (in[21 + inPos] & 4095) << 4) & 255);
         out[32 + outPos] = (byte)((in[21 + inPos] & 4095) >>> 4 & 255);
         out[33 + outPos] = (byte)(in[22 + inPos] & 4095 & 255);
         out[34 + outPos] = (byte)(((in[22 + inPos] & 4095) >>> 8 | (in[23 + inPos] & 4095) << 4) & 255);
         out[35 + outPos] = (byte)((in[23 + inPos] & 4095) >>> 4 & 255);
         out[36 + outPos] = (byte)(in[24 + inPos] & 4095 & 255);
         out[37 + outPos] = (byte)(((in[24 + inPos] & 4095) >>> 8 | (in[25 + inPos] & 4095) << 4) & 255);
         out[38 + outPos] = (byte)((in[25 + inPos] & 4095) >>> 4 & 255);
         out[39 + outPos] = (byte)(in[26 + inPos] & 4095 & 255);
         out[40 + outPos] = (byte)(((in[26 + inPos] & 4095) >>> 8 | (in[27 + inPos] & 4095) << 4) & 255);
         out[41 + outPos] = (byte)((in[27 + inPos] & 4095) >>> 4 & 255);
         out[42 + outPos] = (byte)(in[28 + inPos] & 4095 & 255);
         out[43 + outPos] = (byte)(((in[28 + inPos] & 4095) >>> 8 | (in[29 + inPos] & 4095) << 4) & 255);
         out[44 + outPos] = (byte)((in[29 + inPos] & 4095) >>> 4 & 255);
         out[45 + outPos] = (byte)(in[30 + inPos] & 4095 & 255);
         out[46 + outPos] = (byte)(((in[30 + inPos] & 4095) >>> 8 | (in[31 + inPos] & 4095) << 4) & 255);
         out[47 + outPos] = (byte)((in[31 + inPos] & 4095) >>> 4 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & 4095;
         out[1 + outPos] = in[1 + inPos] >> 4 & 15 | in[2 + inPos] << 4 & 4095;
         out[2 + outPos] = in[3 + inPos] & 255 | in[4 + inPos] << 8 & 4095;
         out[3 + outPos] = in[4 + inPos] >> 4 & 15 | in[5 + inPos] << 4 & 4095;
         out[4 + outPos] = in[6 + inPos] & 255 | in[7 + inPos] << 8 & 4095;
         out[5 + outPos] = in[7 + inPos] >> 4 & 15 | in[8 + inPos] << 4 & 4095;
         out[6 + outPos] = in[9 + inPos] & 255 | in[10 + inPos] << 8 & 4095;
         out[7 + outPos] = in[10 + inPos] >> 4 & 15 | in[11 + inPos] << 4 & 4095;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & 4095;
         out[1 + outPos] = in.get(1 + inPos) >> 4 & 15 | in.get(2 + inPos) << 4 & 4095;
         out[2 + outPos] = in.get(3 + inPos) & 255 | in.get(4 + inPos) << 8 & 4095;
         out[3 + outPos] = in.get(4 + inPos) >> 4 & 15 | in.get(5 + inPos) << 4 & 4095;
         out[4 + outPos] = in.get(6 + inPos) & 255 | in.get(7 + inPos) << 8 & 4095;
         out[5 + outPos] = in.get(7 + inPos) >> 4 & 15 | in.get(8 + inPos) << 4 & 4095;
         out[6 + outPos] = in.get(9 + inPos) & 255 | in.get(10 + inPos) << 8 & 4095;
         out[7 + outPos] = in.get(10 + inPos) >> 4 & 15 | in.get(11 + inPos) << 4 & 4095;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & 4095;
         out[1 + outPos] = in[1 + inPos] >> 4 & 15 | in[2 + inPos] << 4 & 4095;
         out[2 + outPos] = in[3 + inPos] & 255 | in[4 + inPos] << 8 & 4095;
         out[3 + outPos] = in[4 + inPos] >> 4 & 15 | in[5 + inPos] << 4 & 4095;
         out[4 + outPos] = in[6 + inPos] & 255 | in[7 + inPos] << 8 & 4095;
         out[5 + outPos] = in[7 + inPos] >> 4 & 15 | in[8 + inPos] << 4 & 4095;
         out[6 + outPos] = in[9 + inPos] & 255 | in[10 + inPos] << 8 & 4095;
         out[7 + outPos] = in[10 + inPos] >> 4 & 15 | in[11 + inPos] << 4 & 4095;
         out[8 + outPos] = in[12 + inPos] & 255 | in[13 + inPos] << 8 & 4095;
         out[9 + outPos] = in[13 + inPos] >> 4 & 15 | in[14 + inPos] << 4 & 4095;
         out[10 + outPos] = in[15 + inPos] & 255 | in[16 + inPos] << 8 & 4095;
         out[11 + outPos] = in[16 + inPos] >> 4 & 15 | in[17 + inPos] << 4 & 4095;
         out[12 + outPos] = in[18 + inPos] & 255 | in[19 + inPos] << 8 & 4095;
         out[13 + outPos] = in[19 + inPos] >> 4 & 15 | in[20 + inPos] << 4 & 4095;
         out[14 + outPos] = in[21 + inPos] & 255 | in[22 + inPos] << 8 & 4095;
         out[15 + outPos] = in[22 + inPos] >> 4 & 15 | in[23 + inPos] << 4 & 4095;
         out[16 + outPos] = in[24 + inPos] & 255 | in[25 + inPos] << 8 & 4095;
         out[17 + outPos] = in[25 + inPos] >> 4 & 15 | in[26 + inPos] << 4 & 4095;
         out[18 + outPos] = in[27 + inPos] & 255 | in[28 + inPos] << 8 & 4095;
         out[19 + outPos] = in[28 + inPos] >> 4 & 15 | in[29 + inPos] << 4 & 4095;
         out[20 + outPos] = in[30 + inPos] & 255 | in[31 + inPos] << 8 & 4095;
         out[21 + outPos] = in[31 + inPos] >> 4 & 15 | in[32 + inPos] << 4 & 4095;
         out[22 + outPos] = in[33 + inPos] & 255 | in[34 + inPos] << 8 & 4095;
         out[23 + outPos] = in[34 + inPos] >> 4 & 15 | in[35 + inPos] << 4 & 4095;
         out[24 + outPos] = in[36 + inPos] & 255 | in[37 + inPos] << 8 & 4095;
         out[25 + outPos] = in[37 + inPos] >> 4 & 15 | in[38 + inPos] << 4 & 4095;
         out[26 + outPos] = in[39 + inPos] & 255 | in[40 + inPos] << 8 & 4095;
         out[27 + outPos] = in[40 + inPos] >> 4 & 15 | in[41 + inPos] << 4 & 4095;
         out[28 + outPos] = in[42 + inPos] & 255 | in[43 + inPos] << 8 & 4095;
         out[29 + outPos] = in[43 + inPos] >> 4 & 15 | in[44 + inPos] << 4 & 4095;
         out[30 + outPos] = in[45 + inPos] & 255 | in[46 + inPos] << 8 & 4095;
         out[31 + outPos] = in[46 + inPos] >> 4 & 15 | in[47 + inPos] << 4 & 4095;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & 4095;
         out[1 + outPos] = in.get(1 + inPos) >> 4 & 15 | in.get(2 + inPos) << 4 & 4095;
         out[2 + outPos] = in.get(3 + inPos) & 255 | in.get(4 + inPos) << 8 & 4095;
         out[3 + outPos] = in.get(4 + inPos) >> 4 & 15 | in.get(5 + inPos) << 4 & 4095;
         out[4 + outPos] = in.get(6 + inPos) & 255 | in.get(7 + inPos) << 8 & 4095;
         out[5 + outPos] = in.get(7 + inPos) >> 4 & 15 | in.get(8 + inPos) << 4 & 4095;
         out[6 + outPos] = in.get(9 + inPos) & 255 | in.get(10 + inPos) << 8 & 4095;
         out[7 + outPos] = in.get(10 + inPos) >> 4 & 15 | in.get(11 + inPos) << 4 & 4095;
         out[8 + outPos] = in.get(12 + inPos) & 255 | in.get(13 + inPos) << 8 & 4095;
         out[9 + outPos] = in.get(13 + inPos) >> 4 & 15 | in.get(14 + inPos) << 4 & 4095;
         out[10 + outPos] = in.get(15 + inPos) & 255 | in.get(16 + inPos) << 8 & 4095;
         out[11 + outPos] = in.get(16 + inPos) >> 4 & 15 | in.get(17 + inPos) << 4 & 4095;
         out[12 + outPos] = in.get(18 + inPos) & 255 | in.get(19 + inPos) << 8 & 4095;
         out[13 + outPos] = in.get(19 + inPos) >> 4 & 15 | in.get(20 + inPos) << 4 & 4095;
         out[14 + outPos] = in.get(21 + inPos) & 255 | in.get(22 + inPos) << 8 & 4095;
         out[15 + outPos] = in.get(22 + inPos) >> 4 & 15 | in.get(23 + inPos) << 4 & 4095;
         out[16 + outPos] = in.get(24 + inPos) & 255 | in.get(25 + inPos) << 8 & 4095;
         out[17 + outPos] = in.get(25 + inPos) >> 4 & 15 | in.get(26 + inPos) << 4 & 4095;
         out[18 + outPos] = in.get(27 + inPos) & 255 | in.get(28 + inPos) << 8 & 4095;
         out[19 + outPos] = in.get(28 + inPos) >> 4 & 15 | in.get(29 + inPos) << 4 & 4095;
         out[20 + outPos] = in.get(30 + inPos) & 255 | in.get(31 + inPos) << 8 & 4095;
         out[21 + outPos] = in.get(31 + inPos) >> 4 & 15 | in.get(32 + inPos) << 4 & 4095;
         out[22 + outPos] = in.get(33 + inPos) & 255 | in.get(34 + inPos) << 8 & 4095;
         out[23 + outPos] = in.get(34 + inPos) >> 4 & 15 | in.get(35 + inPos) << 4 & 4095;
         out[24 + outPos] = in.get(36 + inPos) & 255 | in.get(37 + inPos) << 8 & 4095;
         out[25 + outPos] = in.get(37 + inPos) >> 4 & 15 | in.get(38 + inPos) << 4 & 4095;
         out[26 + outPos] = in.get(39 + inPos) & 255 | in.get(40 + inPos) << 8 & 4095;
         out[27 + outPos] = in.get(40 + inPos) >> 4 & 15 | in.get(41 + inPos) << 4 & 4095;
         out[28 + outPos] = in.get(42 + inPos) & 255 | in.get(43 + inPos) << 8 & 4095;
         out[29 + outPos] = in.get(43 + inPos) >> 4 & 15 | in.get(44 + inPos) << 4 & 4095;
         out[30 + outPos] = in.get(45 + inPos) & 255 | in.get(46 + inPos) << 8 & 4095;
         out[31 + outPos] = in.get(46 + inPos) >> 4 & 15 | in.get(47 + inPos) << 4 & 4095;
      }
   }

   private static final class Packer13 extends BytePacker {
      private Packer13() {
         super(13);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 8191 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 8191) >>> 8 | (in[1 + inPos] & 8191) << 5) & 255);
         out[2 + outPos] = (byte)((in[1 + inPos] & 8191) >>> 3 & 255);
         out[3 + outPos] = (byte)(((in[1 + inPos] & 8191) >>> 11 | (in[2 + inPos] & 8191) << 2) & 255);
         out[4 + outPos] = (byte)(((in[2 + inPos] & 8191) >>> 6 | (in[3 + inPos] & 8191) << 7) & 255);
         out[5 + outPos] = (byte)((in[3 + inPos] & 8191) >>> 1 & 255);
         out[6 + outPos] = (byte)(((in[3 + inPos] & 8191) >>> 9 | (in[4 + inPos] & 8191) << 4) & 255);
         out[7 + outPos] = (byte)((in[4 + inPos] & 8191) >>> 4 & 255);
         out[8 + outPos] = (byte)(((in[4 + inPos] & 8191) >>> 12 | (in[5 + inPos] & 8191) << 1) & 255);
         out[9 + outPos] = (byte)(((in[5 + inPos] & 8191) >>> 7 | (in[6 + inPos] & 8191) << 6) & 255);
         out[10 + outPos] = (byte)((in[6 + inPos] & 8191) >>> 2 & 255);
         out[11 + outPos] = (byte)(((in[6 + inPos] & 8191) >>> 10 | (in[7 + inPos] & 8191) << 3) & 255);
         out[12 + outPos] = (byte)((in[7 + inPos] & 8191) >>> 5 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 8191 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 8191) >>> 8 | (in[1 + inPos] & 8191) << 5) & 255);
         out[2 + outPos] = (byte)((in[1 + inPos] & 8191) >>> 3 & 255);
         out[3 + outPos] = (byte)(((in[1 + inPos] & 8191) >>> 11 | (in[2 + inPos] & 8191) << 2) & 255);
         out[4 + outPos] = (byte)(((in[2 + inPos] & 8191) >>> 6 | (in[3 + inPos] & 8191) << 7) & 255);
         out[5 + outPos] = (byte)((in[3 + inPos] & 8191) >>> 1 & 255);
         out[6 + outPos] = (byte)(((in[3 + inPos] & 8191) >>> 9 | (in[4 + inPos] & 8191) << 4) & 255);
         out[7 + outPos] = (byte)((in[4 + inPos] & 8191) >>> 4 & 255);
         out[8 + outPos] = (byte)(((in[4 + inPos] & 8191) >>> 12 | (in[5 + inPos] & 8191) << 1) & 255);
         out[9 + outPos] = (byte)(((in[5 + inPos] & 8191) >>> 7 | (in[6 + inPos] & 8191) << 6) & 255);
         out[10 + outPos] = (byte)((in[6 + inPos] & 8191) >>> 2 & 255);
         out[11 + outPos] = (byte)(((in[6 + inPos] & 8191) >>> 10 | (in[7 + inPos] & 8191) << 3) & 255);
         out[12 + outPos] = (byte)((in[7 + inPos] & 8191) >>> 5 & 255);
         out[13 + outPos] = (byte)(in[8 + inPos] & 8191 & 255);
         out[14 + outPos] = (byte)(((in[8 + inPos] & 8191) >>> 8 | (in[9 + inPos] & 8191) << 5) & 255);
         out[15 + outPos] = (byte)((in[9 + inPos] & 8191) >>> 3 & 255);
         out[16 + outPos] = (byte)(((in[9 + inPos] & 8191) >>> 11 | (in[10 + inPos] & 8191) << 2) & 255);
         out[17 + outPos] = (byte)(((in[10 + inPos] & 8191) >>> 6 | (in[11 + inPos] & 8191) << 7) & 255);
         out[18 + outPos] = (byte)((in[11 + inPos] & 8191) >>> 1 & 255);
         out[19 + outPos] = (byte)(((in[11 + inPos] & 8191) >>> 9 | (in[12 + inPos] & 8191) << 4) & 255);
         out[20 + outPos] = (byte)((in[12 + inPos] & 8191) >>> 4 & 255);
         out[21 + outPos] = (byte)(((in[12 + inPos] & 8191) >>> 12 | (in[13 + inPos] & 8191) << 1) & 255);
         out[22 + outPos] = (byte)(((in[13 + inPos] & 8191) >>> 7 | (in[14 + inPos] & 8191) << 6) & 255);
         out[23 + outPos] = (byte)((in[14 + inPos] & 8191) >>> 2 & 255);
         out[24 + outPos] = (byte)(((in[14 + inPos] & 8191) >>> 10 | (in[15 + inPos] & 8191) << 3) & 255);
         out[25 + outPos] = (byte)((in[15 + inPos] & 8191) >>> 5 & 255);
         out[26 + outPos] = (byte)(in[16 + inPos] & 8191 & 255);
         out[27 + outPos] = (byte)(((in[16 + inPos] & 8191) >>> 8 | (in[17 + inPos] & 8191) << 5) & 255);
         out[28 + outPos] = (byte)((in[17 + inPos] & 8191) >>> 3 & 255);
         out[29 + outPos] = (byte)(((in[17 + inPos] & 8191) >>> 11 | (in[18 + inPos] & 8191) << 2) & 255);
         out[30 + outPos] = (byte)(((in[18 + inPos] & 8191) >>> 6 | (in[19 + inPos] & 8191) << 7) & 255);
         out[31 + outPos] = (byte)((in[19 + inPos] & 8191) >>> 1 & 255);
         out[32 + outPos] = (byte)(((in[19 + inPos] & 8191) >>> 9 | (in[20 + inPos] & 8191) << 4) & 255);
         out[33 + outPos] = (byte)((in[20 + inPos] & 8191) >>> 4 & 255);
         out[34 + outPos] = (byte)(((in[20 + inPos] & 8191) >>> 12 | (in[21 + inPos] & 8191) << 1) & 255);
         out[35 + outPos] = (byte)(((in[21 + inPos] & 8191) >>> 7 | (in[22 + inPos] & 8191) << 6) & 255);
         out[36 + outPos] = (byte)((in[22 + inPos] & 8191) >>> 2 & 255);
         out[37 + outPos] = (byte)(((in[22 + inPos] & 8191) >>> 10 | (in[23 + inPos] & 8191) << 3) & 255);
         out[38 + outPos] = (byte)((in[23 + inPos] & 8191) >>> 5 & 255);
         out[39 + outPos] = (byte)(in[24 + inPos] & 8191 & 255);
         out[40 + outPos] = (byte)(((in[24 + inPos] & 8191) >>> 8 | (in[25 + inPos] & 8191) << 5) & 255);
         out[41 + outPos] = (byte)((in[25 + inPos] & 8191) >>> 3 & 255);
         out[42 + outPos] = (byte)(((in[25 + inPos] & 8191) >>> 11 | (in[26 + inPos] & 8191) << 2) & 255);
         out[43 + outPos] = (byte)(((in[26 + inPos] & 8191) >>> 6 | (in[27 + inPos] & 8191) << 7) & 255);
         out[44 + outPos] = (byte)((in[27 + inPos] & 8191) >>> 1 & 255);
         out[45 + outPos] = (byte)(((in[27 + inPos] & 8191) >>> 9 | (in[28 + inPos] & 8191) << 4) & 255);
         out[46 + outPos] = (byte)((in[28 + inPos] & 8191) >>> 4 & 255);
         out[47 + outPos] = (byte)(((in[28 + inPos] & 8191) >>> 12 | (in[29 + inPos] & 8191) << 1) & 255);
         out[48 + outPos] = (byte)(((in[29 + inPos] & 8191) >>> 7 | (in[30 + inPos] & 8191) << 6) & 255);
         out[49 + outPos] = (byte)((in[30 + inPos] & 8191) >>> 2 & 255);
         out[50 + outPos] = (byte)(((in[30 + inPos] & 8191) >>> 10 | (in[31 + inPos] & 8191) << 3) & 255);
         out[51 + outPos] = (byte)((in[31 + inPos] & 8191) >>> 5 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & 8191;
         out[1 + outPos] = in[1 + inPos] >> 5 & 7 | in[2 + inPos] << 3 & 2047 | in[3 + inPos] << 11 & 8191;
         out[2 + outPos] = in[3 + inPos] >> 2 & 63 | in[4 + inPos] << 6 & 8191;
         out[3 + outPos] = in[4 + inPos] >> 7 & 1 | in[5 + inPos] << 1 & 511 | in[6 + inPos] << 9 & 8191;
         out[4 + outPos] = in[6 + inPos] >> 4 & 15 | in[7 + inPos] << 4 & 4095 | in[8 + inPos] << 12 & 8191;
         out[5 + outPos] = in[8 + inPos] >> 1 & 127 | in[9 + inPos] << 7 & 8191;
         out[6 + outPos] = in[9 + inPos] >> 6 & 3 | in[10 + inPos] << 2 & 1023 | in[11 + inPos] << 10 & 8191;
         out[7 + outPos] = in[11 + inPos] >> 3 & 31 | in[12 + inPos] << 5 & 8191;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & 8191;
         out[1 + outPos] = in.get(1 + inPos) >> 5 & 7 | in.get(2 + inPos) << 3 & 2047 | in.get(3 + inPos) << 11 & 8191;
         out[2 + outPos] = in.get(3 + inPos) >> 2 & 63 | in.get(4 + inPos) << 6 & 8191;
         out[3 + outPos] = in.get(4 + inPos) >> 7 & 1 | in.get(5 + inPos) << 1 & 511 | in.get(6 + inPos) << 9 & 8191;
         out[4 + outPos] = in.get(6 + inPos) >> 4 & 15 | in.get(7 + inPos) << 4 & 4095 | in.get(8 + inPos) << 12 & 8191;
         out[5 + outPos] = in.get(8 + inPos) >> 1 & 127 | in.get(9 + inPos) << 7 & 8191;
         out[6 + outPos] = in.get(9 + inPos) >> 6 & 3 | in.get(10 + inPos) << 2 & 1023 | in.get(11 + inPos) << 10 & 8191;
         out[7 + outPos] = in.get(11 + inPos) >> 3 & 31 | in.get(12 + inPos) << 5 & 8191;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & 8191;
         out[1 + outPos] = in[1 + inPos] >> 5 & 7 | in[2 + inPos] << 3 & 2047 | in[3 + inPos] << 11 & 8191;
         out[2 + outPos] = in[3 + inPos] >> 2 & 63 | in[4 + inPos] << 6 & 8191;
         out[3 + outPos] = in[4 + inPos] >> 7 & 1 | in[5 + inPos] << 1 & 511 | in[6 + inPos] << 9 & 8191;
         out[4 + outPos] = in[6 + inPos] >> 4 & 15 | in[7 + inPos] << 4 & 4095 | in[8 + inPos] << 12 & 8191;
         out[5 + outPos] = in[8 + inPos] >> 1 & 127 | in[9 + inPos] << 7 & 8191;
         out[6 + outPos] = in[9 + inPos] >> 6 & 3 | in[10 + inPos] << 2 & 1023 | in[11 + inPos] << 10 & 8191;
         out[7 + outPos] = in[11 + inPos] >> 3 & 31 | in[12 + inPos] << 5 & 8191;
         out[8 + outPos] = in[13 + inPos] & 255 | in[14 + inPos] << 8 & 8191;
         out[9 + outPos] = in[14 + inPos] >> 5 & 7 | in[15 + inPos] << 3 & 2047 | in[16 + inPos] << 11 & 8191;
         out[10 + outPos] = in[16 + inPos] >> 2 & 63 | in[17 + inPos] << 6 & 8191;
         out[11 + outPos] = in[17 + inPos] >> 7 & 1 | in[18 + inPos] << 1 & 511 | in[19 + inPos] << 9 & 8191;
         out[12 + outPos] = in[19 + inPos] >> 4 & 15 | in[20 + inPos] << 4 & 4095 | in[21 + inPos] << 12 & 8191;
         out[13 + outPos] = in[21 + inPos] >> 1 & 127 | in[22 + inPos] << 7 & 8191;
         out[14 + outPos] = in[22 + inPos] >> 6 & 3 | in[23 + inPos] << 2 & 1023 | in[24 + inPos] << 10 & 8191;
         out[15 + outPos] = in[24 + inPos] >> 3 & 31 | in[25 + inPos] << 5 & 8191;
         out[16 + outPos] = in[26 + inPos] & 255 | in[27 + inPos] << 8 & 8191;
         out[17 + outPos] = in[27 + inPos] >> 5 & 7 | in[28 + inPos] << 3 & 2047 | in[29 + inPos] << 11 & 8191;
         out[18 + outPos] = in[29 + inPos] >> 2 & 63 | in[30 + inPos] << 6 & 8191;
         out[19 + outPos] = in[30 + inPos] >> 7 & 1 | in[31 + inPos] << 1 & 511 | in[32 + inPos] << 9 & 8191;
         out[20 + outPos] = in[32 + inPos] >> 4 & 15 | in[33 + inPos] << 4 & 4095 | in[34 + inPos] << 12 & 8191;
         out[21 + outPos] = in[34 + inPos] >> 1 & 127 | in[35 + inPos] << 7 & 8191;
         out[22 + outPos] = in[35 + inPos] >> 6 & 3 | in[36 + inPos] << 2 & 1023 | in[37 + inPos] << 10 & 8191;
         out[23 + outPos] = in[37 + inPos] >> 3 & 31 | in[38 + inPos] << 5 & 8191;
         out[24 + outPos] = in[39 + inPos] & 255 | in[40 + inPos] << 8 & 8191;
         out[25 + outPos] = in[40 + inPos] >> 5 & 7 | in[41 + inPos] << 3 & 2047 | in[42 + inPos] << 11 & 8191;
         out[26 + outPos] = in[42 + inPos] >> 2 & 63 | in[43 + inPos] << 6 & 8191;
         out[27 + outPos] = in[43 + inPos] >> 7 & 1 | in[44 + inPos] << 1 & 511 | in[45 + inPos] << 9 & 8191;
         out[28 + outPos] = in[45 + inPos] >> 4 & 15 | in[46 + inPos] << 4 & 4095 | in[47 + inPos] << 12 & 8191;
         out[29 + outPos] = in[47 + inPos] >> 1 & 127 | in[48 + inPos] << 7 & 8191;
         out[30 + outPos] = in[48 + inPos] >> 6 & 3 | in[49 + inPos] << 2 & 1023 | in[50 + inPos] << 10 & 8191;
         out[31 + outPos] = in[50 + inPos] >> 3 & 31 | in[51 + inPos] << 5 & 8191;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & 8191;
         out[1 + outPos] = in.get(1 + inPos) >> 5 & 7 | in.get(2 + inPos) << 3 & 2047 | in.get(3 + inPos) << 11 & 8191;
         out[2 + outPos] = in.get(3 + inPos) >> 2 & 63 | in.get(4 + inPos) << 6 & 8191;
         out[3 + outPos] = in.get(4 + inPos) >> 7 & 1 | in.get(5 + inPos) << 1 & 511 | in.get(6 + inPos) << 9 & 8191;
         out[4 + outPos] = in.get(6 + inPos) >> 4 & 15 | in.get(7 + inPos) << 4 & 4095 | in.get(8 + inPos) << 12 & 8191;
         out[5 + outPos] = in.get(8 + inPos) >> 1 & 127 | in.get(9 + inPos) << 7 & 8191;
         out[6 + outPos] = in.get(9 + inPos) >> 6 & 3 | in.get(10 + inPos) << 2 & 1023 | in.get(11 + inPos) << 10 & 8191;
         out[7 + outPos] = in.get(11 + inPos) >> 3 & 31 | in.get(12 + inPos) << 5 & 8191;
         out[8 + outPos] = in.get(13 + inPos) & 255 | in.get(14 + inPos) << 8 & 8191;
         out[9 + outPos] = in.get(14 + inPos) >> 5 & 7 | in.get(15 + inPos) << 3 & 2047 | in.get(16 + inPos) << 11 & 8191;
         out[10 + outPos] = in.get(16 + inPos) >> 2 & 63 | in.get(17 + inPos) << 6 & 8191;
         out[11 + outPos] = in.get(17 + inPos) >> 7 & 1 | in.get(18 + inPos) << 1 & 511 | in.get(19 + inPos) << 9 & 8191;
         out[12 + outPos] = in.get(19 + inPos) >> 4 & 15 | in.get(20 + inPos) << 4 & 4095 | in.get(21 + inPos) << 12 & 8191;
         out[13 + outPos] = in.get(21 + inPos) >> 1 & 127 | in.get(22 + inPos) << 7 & 8191;
         out[14 + outPos] = in.get(22 + inPos) >> 6 & 3 | in.get(23 + inPos) << 2 & 1023 | in.get(24 + inPos) << 10 & 8191;
         out[15 + outPos] = in.get(24 + inPos) >> 3 & 31 | in.get(25 + inPos) << 5 & 8191;
         out[16 + outPos] = in.get(26 + inPos) & 255 | in.get(27 + inPos) << 8 & 8191;
         out[17 + outPos] = in.get(27 + inPos) >> 5 & 7 | in.get(28 + inPos) << 3 & 2047 | in.get(29 + inPos) << 11 & 8191;
         out[18 + outPos] = in.get(29 + inPos) >> 2 & 63 | in.get(30 + inPos) << 6 & 8191;
         out[19 + outPos] = in.get(30 + inPos) >> 7 & 1 | in.get(31 + inPos) << 1 & 511 | in.get(32 + inPos) << 9 & 8191;
         out[20 + outPos] = in.get(32 + inPos) >> 4 & 15 | in.get(33 + inPos) << 4 & 4095 | in.get(34 + inPos) << 12 & 8191;
         out[21 + outPos] = in.get(34 + inPos) >> 1 & 127 | in.get(35 + inPos) << 7 & 8191;
         out[22 + outPos] = in.get(35 + inPos) >> 6 & 3 | in.get(36 + inPos) << 2 & 1023 | in.get(37 + inPos) << 10 & 8191;
         out[23 + outPos] = in.get(37 + inPos) >> 3 & 31 | in.get(38 + inPos) << 5 & 8191;
         out[24 + outPos] = in.get(39 + inPos) & 255 | in.get(40 + inPos) << 8 & 8191;
         out[25 + outPos] = in.get(40 + inPos) >> 5 & 7 | in.get(41 + inPos) << 3 & 2047 | in.get(42 + inPos) << 11 & 8191;
         out[26 + outPos] = in.get(42 + inPos) >> 2 & 63 | in.get(43 + inPos) << 6 & 8191;
         out[27 + outPos] = in.get(43 + inPos) >> 7 & 1 | in.get(44 + inPos) << 1 & 511 | in.get(45 + inPos) << 9 & 8191;
         out[28 + outPos] = in.get(45 + inPos) >> 4 & 15 | in.get(46 + inPos) << 4 & 4095 | in.get(47 + inPos) << 12 & 8191;
         out[29 + outPos] = in.get(47 + inPos) >> 1 & 127 | in.get(48 + inPos) << 7 & 8191;
         out[30 + outPos] = in.get(48 + inPos) >> 6 & 3 | in.get(49 + inPos) << 2 & 1023 | in.get(50 + inPos) << 10 & 8191;
         out[31 + outPos] = in.get(50 + inPos) >> 3 & 31 | in.get(51 + inPos) << 5 & 8191;
      }
   }

   private static final class Packer14 extends BytePacker {
      private Packer14() {
         super(14);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 16383 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 16383) >>> 8 | (in[1 + inPos] & 16383) << 6) & 255);
         out[2 + outPos] = (byte)((in[1 + inPos] & 16383) >>> 2 & 255);
         out[3 + outPos] = (byte)(((in[1 + inPos] & 16383) >>> 10 | (in[2 + inPos] & 16383) << 4) & 255);
         out[4 + outPos] = (byte)((in[2 + inPos] & 16383) >>> 4 & 255);
         out[5 + outPos] = (byte)(((in[2 + inPos] & 16383) >>> 12 | (in[3 + inPos] & 16383) << 2) & 255);
         out[6 + outPos] = (byte)((in[3 + inPos] & 16383) >>> 6 & 255);
         out[7 + outPos] = (byte)(in[4 + inPos] & 16383 & 255);
         out[8 + outPos] = (byte)(((in[4 + inPos] & 16383) >>> 8 | (in[5 + inPos] & 16383) << 6) & 255);
         out[9 + outPos] = (byte)((in[5 + inPos] & 16383) >>> 2 & 255);
         out[10 + outPos] = (byte)(((in[5 + inPos] & 16383) >>> 10 | (in[6 + inPos] & 16383) << 4) & 255);
         out[11 + outPos] = (byte)((in[6 + inPos] & 16383) >>> 4 & 255);
         out[12 + outPos] = (byte)(((in[6 + inPos] & 16383) >>> 12 | (in[7 + inPos] & 16383) << 2) & 255);
         out[13 + outPos] = (byte)((in[7 + inPos] & 16383) >>> 6 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 16383 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 16383) >>> 8 | (in[1 + inPos] & 16383) << 6) & 255);
         out[2 + outPos] = (byte)((in[1 + inPos] & 16383) >>> 2 & 255);
         out[3 + outPos] = (byte)(((in[1 + inPos] & 16383) >>> 10 | (in[2 + inPos] & 16383) << 4) & 255);
         out[4 + outPos] = (byte)((in[2 + inPos] & 16383) >>> 4 & 255);
         out[5 + outPos] = (byte)(((in[2 + inPos] & 16383) >>> 12 | (in[3 + inPos] & 16383) << 2) & 255);
         out[6 + outPos] = (byte)((in[3 + inPos] & 16383) >>> 6 & 255);
         out[7 + outPos] = (byte)(in[4 + inPos] & 16383 & 255);
         out[8 + outPos] = (byte)(((in[4 + inPos] & 16383) >>> 8 | (in[5 + inPos] & 16383) << 6) & 255);
         out[9 + outPos] = (byte)((in[5 + inPos] & 16383) >>> 2 & 255);
         out[10 + outPos] = (byte)(((in[5 + inPos] & 16383) >>> 10 | (in[6 + inPos] & 16383) << 4) & 255);
         out[11 + outPos] = (byte)((in[6 + inPos] & 16383) >>> 4 & 255);
         out[12 + outPos] = (byte)(((in[6 + inPos] & 16383) >>> 12 | (in[7 + inPos] & 16383) << 2) & 255);
         out[13 + outPos] = (byte)((in[7 + inPos] & 16383) >>> 6 & 255);
         out[14 + outPos] = (byte)(in[8 + inPos] & 16383 & 255);
         out[15 + outPos] = (byte)(((in[8 + inPos] & 16383) >>> 8 | (in[9 + inPos] & 16383) << 6) & 255);
         out[16 + outPos] = (byte)((in[9 + inPos] & 16383) >>> 2 & 255);
         out[17 + outPos] = (byte)(((in[9 + inPos] & 16383) >>> 10 | (in[10 + inPos] & 16383) << 4) & 255);
         out[18 + outPos] = (byte)((in[10 + inPos] & 16383) >>> 4 & 255);
         out[19 + outPos] = (byte)(((in[10 + inPos] & 16383) >>> 12 | (in[11 + inPos] & 16383) << 2) & 255);
         out[20 + outPos] = (byte)((in[11 + inPos] & 16383) >>> 6 & 255);
         out[21 + outPos] = (byte)(in[12 + inPos] & 16383 & 255);
         out[22 + outPos] = (byte)(((in[12 + inPos] & 16383) >>> 8 | (in[13 + inPos] & 16383) << 6) & 255);
         out[23 + outPos] = (byte)((in[13 + inPos] & 16383) >>> 2 & 255);
         out[24 + outPos] = (byte)(((in[13 + inPos] & 16383) >>> 10 | (in[14 + inPos] & 16383) << 4) & 255);
         out[25 + outPos] = (byte)((in[14 + inPos] & 16383) >>> 4 & 255);
         out[26 + outPos] = (byte)(((in[14 + inPos] & 16383) >>> 12 | (in[15 + inPos] & 16383) << 2) & 255);
         out[27 + outPos] = (byte)((in[15 + inPos] & 16383) >>> 6 & 255);
         out[28 + outPos] = (byte)(in[16 + inPos] & 16383 & 255);
         out[29 + outPos] = (byte)(((in[16 + inPos] & 16383) >>> 8 | (in[17 + inPos] & 16383) << 6) & 255);
         out[30 + outPos] = (byte)((in[17 + inPos] & 16383) >>> 2 & 255);
         out[31 + outPos] = (byte)(((in[17 + inPos] & 16383) >>> 10 | (in[18 + inPos] & 16383) << 4) & 255);
         out[32 + outPos] = (byte)((in[18 + inPos] & 16383) >>> 4 & 255);
         out[33 + outPos] = (byte)(((in[18 + inPos] & 16383) >>> 12 | (in[19 + inPos] & 16383) << 2) & 255);
         out[34 + outPos] = (byte)((in[19 + inPos] & 16383) >>> 6 & 255);
         out[35 + outPos] = (byte)(in[20 + inPos] & 16383 & 255);
         out[36 + outPos] = (byte)(((in[20 + inPos] & 16383) >>> 8 | (in[21 + inPos] & 16383) << 6) & 255);
         out[37 + outPos] = (byte)((in[21 + inPos] & 16383) >>> 2 & 255);
         out[38 + outPos] = (byte)(((in[21 + inPos] & 16383) >>> 10 | (in[22 + inPos] & 16383) << 4) & 255);
         out[39 + outPos] = (byte)((in[22 + inPos] & 16383) >>> 4 & 255);
         out[40 + outPos] = (byte)(((in[22 + inPos] & 16383) >>> 12 | (in[23 + inPos] & 16383) << 2) & 255);
         out[41 + outPos] = (byte)((in[23 + inPos] & 16383) >>> 6 & 255);
         out[42 + outPos] = (byte)(in[24 + inPos] & 16383 & 255);
         out[43 + outPos] = (byte)(((in[24 + inPos] & 16383) >>> 8 | (in[25 + inPos] & 16383) << 6) & 255);
         out[44 + outPos] = (byte)((in[25 + inPos] & 16383) >>> 2 & 255);
         out[45 + outPos] = (byte)(((in[25 + inPos] & 16383) >>> 10 | (in[26 + inPos] & 16383) << 4) & 255);
         out[46 + outPos] = (byte)((in[26 + inPos] & 16383) >>> 4 & 255);
         out[47 + outPos] = (byte)(((in[26 + inPos] & 16383) >>> 12 | (in[27 + inPos] & 16383) << 2) & 255);
         out[48 + outPos] = (byte)((in[27 + inPos] & 16383) >>> 6 & 255);
         out[49 + outPos] = (byte)(in[28 + inPos] & 16383 & 255);
         out[50 + outPos] = (byte)(((in[28 + inPos] & 16383) >>> 8 | (in[29 + inPos] & 16383) << 6) & 255);
         out[51 + outPos] = (byte)((in[29 + inPos] & 16383) >>> 2 & 255);
         out[52 + outPos] = (byte)(((in[29 + inPos] & 16383) >>> 10 | (in[30 + inPos] & 16383) << 4) & 255);
         out[53 + outPos] = (byte)((in[30 + inPos] & 16383) >>> 4 & 255);
         out[54 + outPos] = (byte)(((in[30 + inPos] & 16383) >>> 12 | (in[31 + inPos] & 16383) << 2) & 255);
         out[55 + outPos] = (byte)((in[31 + inPos] & 16383) >>> 6 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & 16383;
         out[1 + outPos] = in[1 + inPos] >> 6 & 3 | in[2 + inPos] << 2 & 1023 | in[3 + inPos] << 10 & 16383;
         out[2 + outPos] = in[3 + inPos] >> 4 & 15 | in[4 + inPos] << 4 & 4095 | in[5 + inPos] << 12 & 16383;
         out[3 + outPos] = in[5 + inPos] >> 2 & 63 | in[6 + inPos] << 6 & 16383;
         out[4 + outPos] = in[7 + inPos] & 255 | in[8 + inPos] << 8 & 16383;
         out[5 + outPos] = in[8 + inPos] >> 6 & 3 | in[9 + inPos] << 2 & 1023 | in[10 + inPos] << 10 & 16383;
         out[6 + outPos] = in[10 + inPos] >> 4 & 15 | in[11 + inPos] << 4 & 4095 | in[12 + inPos] << 12 & 16383;
         out[7 + outPos] = in[12 + inPos] >> 2 & 63 | in[13 + inPos] << 6 & 16383;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & 16383;
         out[1 + outPos] = in.get(1 + inPos) >> 6 & 3 | in.get(2 + inPos) << 2 & 1023 | in.get(3 + inPos) << 10 & 16383;
         out[2 + outPos] = in.get(3 + inPos) >> 4 & 15 | in.get(4 + inPos) << 4 & 4095 | in.get(5 + inPos) << 12 & 16383;
         out[3 + outPos] = in.get(5 + inPos) >> 2 & 63 | in.get(6 + inPos) << 6 & 16383;
         out[4 + outPos] = in.get(7 + inPos) & 255 | in.get(8 + inPos) << 8 & 16383;
         out[5 + outPos] = in.get(8 + inPos) >> 6 & 3 | in.get(9 + inPos) << 2 & 1023 | in.get(10 + inPos) << 10 & 16383;
         out[6 + outPos] = in.get(10 + inPos) >> 4 & 15 | in.get(11 + inPos) << 4 & 4095 | in.get(12 + inPos) << 12 & 16383;
         out[7 + outPos] = in.get(12 + inPos) >> 2 & 63 | in.get(13 + inPos) << 6 & 16383;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & 16383;
         out[1 + outPos] = in[1 + inPos] >> 6 & 3 | in[2 + inPos] << 2 & 1023 | in[3 + inPos] << 10 & 16383;
         out[2 + outPos] = in[3 + inPos] >> 4 & 15 | in[4 + inPos] << 4 & 4095 | in[5 + inPos] << 12 & 16383;
         out[3 + outPos] = in[5 + inPos] >> 2 & 63 | in[6 + inPos] << 6 & 16383;
         out[4 + outPos] = in[7 + inPos] & 255 | in[8 + inPos] << 8 & 16383;
         out[5 + outPos] = in[8 + inPos] >> 6 & 3 | in[9 + inPos] << 2 & 1023 | in[10 + inPos] << 10 & 16383;
         out[6 + outPos] = in[10 + inPos] >> 4 & 15 | in[11 + inPos] << 4 & 4095 | in[12 + inPos] << 12 & 16383;
         out[7 + outPos] = in[12 + inPos] >> 2 & 63 | in[13 + inPos] << 6 & 16383;
         out[8 + outPos] = in[14 + inPos] & 255 | in[15 + inPos] << 8 & 16383;
         out[9 + outPos] = in[15 + inPos] >> 6 & 3 | in[16 + inPos] << 2 & 1023 | in[17 + inPos] << 10 & 16383;
         out[10 + outPos] = in[17 + inPos] >> 4 & 15 | in[18 + inPos] << 4 & 4095 | in[19 + inPos] << 12 & 16383;
         out[11 + outPos] = in[19 + inPos] >> 2 & 63 | in[20 + inPos] << 6 & 16383;
         out[12 + outPos] = in[21 + inPos] & 255 | in[22 + inPos] << 8 & 16383;
         out[13 + outPos] = in[22 + inPos] >> 6 & 3 | in[23 + inPos] << 2 & 1023 | in[24 + inPos] << 10 & 16383;
         out[14 + outPos] = in[24 + inPos] >> 4 & 15 | in[25 + inPos] << 4 & 4095 | in[26 + inPos] << 12 & 16383;
         out[15 + outPos] = in[26 + inPos] >> 2 & 63 | in[27 + inPos] << 6 & 16383;
         out[16 + outPos] = in[28 + inPos] & 255 | in[29 + inPos] << 8 & 16383;
         out[17 + outPos] = in[29 + inPos] >> 6 & 3 | in[30 + inPos] << 2 & 1023 | in[31 + inPos] << 10 & 16383;
         out[18 + outPos] = in[31 + inPos] >> 4 & 15 | in[32 + inPos] << 4 & 4095 | in[33 + inPos] << 12 & 16383;
         out[19 + outPos] = in[33 + inPos] >> 2 & 63 | in[34 + inPos] << 6 & 16383;
         out[20 + outPos] = in[35 + inPos] & 255 | in[36 + inPos] << 8 & 16383;
         out[21 + outPos] = in[36 + inPos] >> 6 & 3 | in[37 + inPos] << 2 & 1023 | in[38 + inPos] << 10 & 16383;
         out[22 + outPos] = in[38 + inPos] >> 4 & 15 | in[39 + inPos] << 4 & 4095 | in[40 + inPos] << 12 & 16383;
         out[23 + outPos] = in[40 + inPos] >> 2 & 63 | in[41 + inPos] << 6 & 16383;
         out[24 + outPos] = in[42 + inPos] & 255 | in[43 + inPos] << 8 & 16383;
         out[25 + outPos] = in[43 + inPos] >> 6 & 3 | in[44 + inPos] << 2 & 1023 | in[45 + inPos] << 10 & 16383;
         out[26 + outPos] = in[45 + inPos] >> 4 & 15 | in[46 + inPos] << 4 & 4095 | in[47 + inPos] << 12 & 16383;
         out[27 + outPos] = in[47 + inPos] >> 2 & 63 | in[48 + inPos] << 6 & 16383;
         out[28 + outPos] = in[49 + inPos] & 255 | in[50 + inPos] << 8 & 16383;
         out[29 + outPos] = in[50 + inPos] >> 6 & 3 | in[51 + inPos] << 2 & 1023 | in[52 + inPos] << 10 & 16383;
         out[30 + outPos] = in[52 + inPos] >> 4 & 15 | in[53 + inPos] << 4 & 4095 | in[54 + inPos] << 12 & 16383;
         out[31 + outPos] = in[54 + inPos] >> 2 & 63 | in[55 + inPos] << 6 & 16383;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & 16383;
         out[1 + outPos] = in.get(1 + inPos) >> 6 & 3 | in.get(2 + inPos) << 2 & 1023 | in.get(3 + inPos) << 10 & 16383;
         out[2 + outPos] = in.get(3 + inPos) >> 4 & 15 | in.get(4 + inPos) << 4 & 4095 | in.get(5 + inPos) << 12 & 16383;
         out[3 + outPos] = in.get(5 + inPos) >> 2 & 63 | in.get(6 + inPos) << 6 & 16383;
         out[4 + outPos] = in.get(7 + inPos) & 255 | in.get(8 + inPos) << 8 & 16383;
         out[5 + outPos] = in.get(8 + inPos) >> 6 & 3 | in.get(9 + inPos) << 2 & 1023 | in.get(10 + inPos) << 10 & 16383;
         out[6 + outPos] = in.get(10 + inPos) >> 4 & 15 | in.get(11 + inPos) << 4 & 4095 | in.get(12 + inPos) << 12 & 16383;
         out[7 + outPos] = in.get(12 + inPos) >> 2 & 63 | in.get(13 + inPos) << 6 & 16383;
         out[8 + outPos] = in.get(14 + inPos) & 255 | in.get(15 + inPos) << 8 & 16383;
         out[9 + outPos] = in.get(15 + inPos) >> 6 & 3 | in.get(16 + inPos) << 2 & 1023 | in.get(17 + inPos) << 10 & 16383;
         out[10 + outPos] = in.get(17 + inPos) >> 4 & 15 | in.get(18 + inPos) << 4 & 4095 | in.get(19 + inPos) << 12 & 16383;
         out[11 + outPos] = in.get(19 + inPos) >> 2 & 63 | in.get(20 + inPos) << 6 & 16383;
         out[12 + outPos] = in.get(21 + inPos) & 255 | in.get(22 + inPos) << 8 & 16383;
         out[13 + outPos] = in.get(22 + inPos) >> 6 & 3 | in.get(23 + inPos) << 2 & 1023 | in.get(24 + inPos) << 10 & 16383;
         out[14 + outPos] = in.get(24 + inPos) >> 4 & 15 | in.get(25 + inPos) << 4 & 4095 | in.get(26 + inPos) << 12 & 16383;
         out[15 + outPos] = in.get(26 + inPos) >> 2 & 63 | in.get(27 + inPos) << 6 & 16383;
         out[16 + outPos] = in.get(28 + inPos) & 255 | in.get(29 + inPos) << 8 & 16383;
         out[17 + outPos] = in.get(29 + inPos) >> 6 & 3 | in.get(30 + inPos) << 2 & 1023 | in.get(31 + inPos) << 10 & 16383;
         out[18 + outPos] = in.get(31 + inPos) >> 4 & 15 | in.get(32 + inPos) << 4 & 4095 | in.get(33 + inPos) << 12 & 16383;
         out[19 + outPos] = in.get(33 + inPos) >> 2 & 63 | in.get(34 + inPos) << 6 & 16383;
         out[20 + outPos] = in.get(35 + inPos) & 255 | in.get(36 + inPos) << 8 & 16383;
         out[21 + outPos] = in.get(36 + inPos) >> 6 & 3 | in.get(37 + inPos) << 2 & 1023 | in.get(38 + inPos) << 10 & 16383;
         out[22 + outPos] = in.get(38 + inPos) >> 4 & 15 | in.get(39 + inPos) << 4 & 4095 | in.get(40 + inPos) << 12 & 16383;
         out[23 + outPos] = in.get(40 + inPos) >> 2 & 63 | in.get(41 + inPos) << 6 & 16383;
         out[24 + outPos] = in.get(42 + inPos) & 255 | in.get(43 + inPos) << 8 & 16383;
         out[25 + outPos] = in.get(43 + inPos) >> 6 & 3 | in.get(44 + inPos) << 2 & 1023 | in.get(45 + inPos) << 10 & 16383;
         out[26 + outPos] = in.get(45 + inPos) >> 4 & 15 | in.get(46 + inPos) << 4 & 4095 | in.get(47 + inPos) << 12 & 16383;
         out[27 + outPos] = in.get(47 + inPos) >> 2 & 63 | in.get(48 + inPos) << 6 & 16383;
         out[28 + outPos] = in.get(49 + inPos) & 255 | in.get(50 + inPos) << 8 & 16383;
         out[29 + outPos] = in.get(50 + inPos) >> 6 & 3 | in.get(51 + inPos) << 2 & 1023 | in.get(52 + inPos) << 10 & 16383;
         out[30 + outPos] = in.get(52 + inPos) >> 4 & 15 | in.get(53 + inPos) << 4 & 4095 | in.get(54 + inPos) << 12 & 16383;
         out[31 + outPos] = in.get(54 + inPos) >> 2 & 63 | in.get(55 + inPos) << 6 & 16383;
      }
   }

   private static final class Packer15 extends BytePacker {
      private Packer15() {
         super(15);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 32767 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 32767) >>> 8 | (in[1 + inPos] & 32767) << 7) & 255);
         out[2 + outPos] = (byte)((in[1 + inPos] & 32767) >>> 1 & 255);
         out[3 + outPos] = (byte)(((in[1 + inPos] & 32767) >>> 9 | (in[2 + inPos] & 32767) << 6) & 255);
         out[4 + outPos] = (byte)((in[2 + inPos] & 32767) >>> 2 & 255);
         out[5 + outPos] = (byte)(((in[2 + inPos] & 32767) >>> 10 | (in[3 + inPos] & 32767) << 5) & 255);
         out[6 + outPos] = (byte)((in[3 + inPos] & 32767) >>> 3 & 255);
         out[7 + outPos] = (byte)(((in[3 + inPos] & 32767) >>> 11 | (in[4 + inPos] & 32767) << 4) & 255);
         out[8 + outPos] = (byte)((in[4 + inPos] & 32767) >>> 4 & 255);
         out[9 + outPos] = (byte)(((in[4 + inPos] & 32767) >>> 12 | (in[5 + inPos] & 32767) << 3) & 255);
         out[10 + outPos] = (byte)((in[5 + inPos] & 32767) >>> 5 & 255);
         out[11 + outPos] = (byte)(((in[5 + inPos] & 32767) >>> 13 | (in[6 + inPos] & 32767) << 2) & 255);
         out[12 + outPos] = (byte)((in[6 + inPos] & 32767) >>> 6 & 255);
         out[13 + outPos] = (byte)(((in[6 + inPos] & 32767) >>> 14 | (in[7 + inPos] & 32767) << 1) & 255);
         out[14 + outPos] = (byte)((in[7 + inPos] & 32767) >>> 7 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 32767 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 32767) >>> 8 | (in[1 + inPos] & 32767) << 7) & 255);
         out[2 + outPos] = (byte)((in[1 + inPos] & 32767) >>> 1 & 255);
         out[3 + outPos] = (byte)(((in[1 + inPos] & 32767) >>> 9 | (in[2 + inPos] & 32767) << 6) & 255);
         out[4 + outPos] = (byte)((in[2 + inPos] & 32767) >>> 2 & 255);
         out[5 + outPos] = (byte)(((in[2 + inPos] & 32767) >>> 10 | (in[3 + inPos] & 32767) << 5) & 255);
         out[6 + outPos] = (byte)((in[3 + inPos] & 32767) >>> 3 & 255);
         out[7 + outPos] = (byte)(((in[3 + inPos] & 32767) >>> 11 | (in[4 + inPos] & 32767) << 4) & 255);
         out[8 + outPos] = (byte)((in[4 + inPos] & 32767) >>> 4 & 255);
         out[9 + outPos] = (byte)(((in[4 + inPos] & 32767) >>> 12 | (in[5 + inPos] & 32767) << 3) & 255);
         out[10 + outPos] = (byte)((in[5 + inPos] & 32767) >>> 5 & 255);
         out[11 + outPos] = (byte)(((in[5 + inPos] & 32767) >>> 13 | (in[6 + inPos] & 32767) << 2) & 255);
         out[12 + outPos] = (byte)((in[6 + inPos] & 32767) >>> 6 & 255);
         out[13 + outPos] = (byte)(((in[6 + inPos] & 32767) >>> 14 | (in[7 + inPos] & 32767) << 1) & 255);
         out[14 + outPos] = (byte)((in[7 + inPos] & 32767) >>> 7 & 255);
         out[15 + outPos] = (byte)(in[8 + inPos] & 32767 & 255);
         out[16 + outPos] = (byte)(((in[8 + inPos] & 32767) >>> 8 | (in[9 + inPos] & 32767) << 7) & 255);
         out[17 + outPos] = (byte)((in[9 + inPos] & 32767) >>> 1 & 255);
         out[18 + outPos] = (byte)(((in[9 + inPos] & 32767) >>> 9 | (in[10 + inPos] & 32767) << 6) & 255);
         out[19 + outPos] = (byte)((in[10 + inPos] & 32767) >>> 2 & 255);
         out[20 + outPos] = (byte)(((in[10 + inPos] & 32767) >>> 10 | (in[11 + inPos] & 32767) << 5) & 255);
         out[21 + outPos] = (byte)((in[11 + inPos] & 32767) >>> 3 & 255);
         out[22 + outPos] = (byte)(((in[11 + inPos] & 32767) >>> 11 | (in[12 + inPos] & 32767) << 4) & 255);
         out[23 + outPos] = (byte)((in[12 + inPos] & 32767) >>> 4 & 255);
         out[24 + outPos] = (byte)(((in[12 + inPos] & 32767) >>> 12 | (in[13 + inPos] & 32767) << 3) & 255);
         out[25 + outPos] = (byte)((in[13 + inPos] & 32767) >>> 5 & 255);
         out[26 + outPos] = (byte)(((in[13 + inPos] & 32767) >>> 13 | (in[14 + inPos] & 32767) << 2) & 255);
         out[27 + outPos] = (byte)((in[14 + inPos] & 32767) >>> 6 & 255);
         out[28 + outPos] = (byte)(((in[14 + inPos] & 32767) >>> 14 | (in[15 + inPos] & 32767) << 1) & 255);
         out[29 + outPos] = (byte)((in[15 + inPos] & 32767) >>> 7 & 255);
         out[30 + outPos] = (byte)(in[16 + inPos] & 32767 & 255);
         out[31 + outPos] = (byte)(((in[16 + inPos] & 32767) >>> 8 | (in[17 + inPos] & 32767) << 7) & 255);
         out[32 + outPos] = (byte)((in[17 + inPos] & 32767) >>> 1 & 255);
         out[33 + outPos] = (byte)(((in[17 + inPos] & 32767) >>> 9 | (in[18 + inPos] & 32767) << 6) & 255);
         out[34 + outPos] = (byte)((in[18 + inPos] & 32767) >>> 2 & 255);
         out[35 + outPos] = (byte)(((in[18 + inPos] & 32767) >>> 10 | (in[19 + inPos] & 32767) << 5) & 255);
         out[36 + outPos] = (byte)((in[19 + inPos] & 32767) >>> 3 & 255);
         out[37 + outPos] = (byte)(((in[19 + inPos] & 32767) >>> 11 | (in[20 + inPos] & 32767) << 4) & 255);
         out[38 + outPos] = (byte)((in[20 + inPos] & 32767) >>> 4 & 255);
         out[39 + outPos] = (byte)(((in[20 + inPos] & 32767) >>> 12 | (in[21 + inPos] & 32767) << 3) & 255);
         out[40 + outPos] = (byte)((in[21 + inPos] & 32767) >>> 5 & 255);
         out[41 + outPos] = (byte)(((in[21 + inPos] & 32767) >>> 13 | (in[22 + inPos] & 32767) << 2) & 255);
         out[42 + outPos] = (byte)((in[22 + inPos] & 32767) >>> 6 & 255);
         out[43 + outPos] = (byte)(((in[22 + inPos] & 32767) >>> 14 | (in[23 + inPos] & 32767) << 1) & 255);
         out[44 + outPos] = (byte)((in[23 + inPos] & 32767) >>> 7 & 255);
         out[45 + outPos] = (byte)(in[24 + inPos] & 32767 & 255);
         out[46 + outPos] = (byte)(((in[24 + inPos] & 32767) >>> 8 | (in[25 + inPos] & 32767) << 7) & 255);
         out[47 + outPos] = (byte)((in[25 + inPos] & 32767) >>> 1 & 255);
         out[48 + outPos] = (byte)(((in[25 + inPos] & 32767) >>> 9 | (in[26 + inPos] & 32767) << 6) & 255);
         out[49 + outPos] = (byte)((in[26 + inPos] & 32767) >>> 2 & 255);
         out[50 + outPos] = (byte)(((in[26 + inPos] & 32767) >>> 10 | (in[27 + inPos] & 32767) << 5) & 255);
         out[51 + outPos] = (byte)((in[27 + inPos] & 32767) >>> 3 & 255);
         out[52 + outPos] = (byte)(((in[27 + inPos] & 32767) >>> 11 | (in[28 + inPos] & 32767) << 4) & 255);
         out[53 + outPos] = (byte)((in[28 + inPos] & 32767) >>> 4 & 255);
         out[54 + outPos] = (byte)(((in[28 + inPos] & 32767) >>> 12 | (in[29 + inPos] & 32767) << 3) & 255);
         out[55 + outPos] = (byte)((in[29 + inPos] & 32767) >>> 5 & 255);
         out[56 + outPos] = (byte)(((in[29 + inPos] & 32767) >>> 13 | (in[30 + inPos] & 32767) << 2) & 255);
         out[57 + outPos] = (byte)((in[30 + inPos] & 32767) >>> 6 & 255);
         out[58 + outPos] = (byte)(((in[30 + inPos] & 32767) >>> 14 | (in[31 + inPos] & 32767) << 1) & 255);
         out[59 + outPos] = (byte)((in[31 + inPos] & 32767) >>> 7 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & 32767;
         out[1 + outPos] = in[1 + inPos] >> 7 & 1 | in[2 + inPos] << 1 & 511 | in[3 + inPos] << 9 & 32767;
         out[2 + outPos] = in[3 + inPos] >> 6 & 3 | in[4 + inPos] << 2 & 1023 | in[5 + inPos] << 10 & 32767;
         out[3 + outPos] = in[5 + inPos] >> 5 & 7 | in[6 + inPos] << 3 & 2047 | in[7 + inPos] << 11 & 32767;
         out[4 + outPos] = in[7 + inPos] >> 4 & 15 | in[8 + inPos] << 4 & 4095 | in[9 + inPos] << 12 & 32767;
         out[5 + outPos] = in[9 + inPos] >> 3 & 31 | in[10 + inPos] << 5 & 8191 | in[11 + inPos] << 13 & 32767;
         out[6 + outPos] = in[11 + inPos] >> 2 & 63 | in[12 + inPos] << 6 & 16383 | in[13 + inPos] << 14 & 32767;
         out[7 + outPos] = in[13 + inPos] >> 1 & 127 | in[14 + inPos] << 7 & 32767;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & 32767;
         out[1 + outPos] = in.get(1 + inPos) >> 7 & 1 | in.get(2 + inPos) << 1 & 511 | in.get(3 + inPos) << 9 & 32767;
         out[2 + outPos] = in.get(3 + inPos) >> 6 & 3 | in.get(4 + inPos) << 2 & 1023 | in.get(5 + inPos) << 10 & 32767;
         out[3 + outPos] = in.get(5 + inPos) >> 5 & 7 | in.get(6 + inPos) << 3 & 2047 | in.get(7 + inPos) << 11 & 32767;
         out[4 + outPos] = in.get(7 + inPos) >> 4 & 15 | in.get(8 + inPos) << 4 & 4095 | in.get(9 + inPos) << 12 & 32767;
         out[5 + outPos] = in.get(9 + inPos) >> 3 & 31 | in.get(10 + inPos) << 5 & 8191 | in.get(11 + inPos) << 13 & 32767;
         out[6 + outPos] = in.get(11 + inPos) >> 2 & 63 | in.get(12 + inPos) << 6 & 16383 | in.get(13 + inPos) << 14 & 32767;
         out[7 + outPos] = in.get(13 + inPos) >> 1 & 127 | in.get(14 + inPos) << 7 & 32767;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & 32767;
         out[1 + outPos] = in[1 + inPos] >> 7 & 1 | in[2 + inPos] << 1 & 511 | in[3 + inPos] << 9 & 32767;
         out[2 + outPos] = in[3 + inPos] >> 6 & 3 | in[4 + inPos] << 2 & 1023 | in[5 + inPos] << 10 & 32767;
         out[3 + outPos] = in[5 + inPos] >> 5 & 7 | in[6 + inPos] << 3 & 2047 | in[7 + inPos] << 11 & 32767;
         out[4 + outPos] = in[7 + inPos] >> 4 & 15 | in[8 + inPos] << 4 & 4095 | in[9 + inPos] << 12 & 32767;
         out[5 + outPos] = in[9 + inPos] >> 3 & 31 | in[10 + inPos] << 5 & 8191 | in[11 + inPos] << 13 & 32767;
         out[6 + outPos] = in[11 + inPos] >> 2 & 63 | in[12 + inPos] << 6 & 16383 | in[13 + inPos] << 14 & 32767;
         out[7 + outPos] = in[13 + inPos] >> 1 & 127 | in[14 + inPos] << 7 & 32767;
         out[8 + outPos] = in[15 + inPos] & 255 | in[16 + inPos] << 8 & 32767;
         out[9 + outPos] = in[16 + inPos] >> 7 & 1 | in[17 + inPos] << 1 & 511 | in[18 + inPos] << 9 & 32767;
         out[10 + outPos] = in[18 + inPos] >> 6 & 3 | in[19 + inPos] << 2 & 1023 | in[20 + inPos] << 10 & 32767;
         out[11 + outPos] = in[20 + inPos] >> 5 & 7 | in[21 + inPos] << 3 & 2047 | in[22 + inPos] << 11 & 32767;
         out[12 + outPos] = in[22 + inPos] >> 4 & 15 | in[23 + inPos] << 4 & 4095 | in[24 + inPos] << 12 & 32767;
         out[13 + outPos] = in[24 + inPos] >> 3 & 31 | in[25 + inPos] << 5 & 8191 | in[26 + inPos] << 13 & 32767;
         out[14 + outPos] = in[26 + inPos] >> 2 & 63 | in[27 + inPos] << 6 & 16383 | in[28 + inPos] << 14 & 32767;
         out[15 + outPos] = in[28 + inPos] >> 1 & 127 | in[29 + inPos] << 7 & 32767;
         out[16 + outPos] = in[30 + inPos] & 255 | in[31 + inPos] << 8 & 32767;
         out[17 + outPos] = in[31 + inPos] >> 7 & 1 | in[32 + inPos] << 1 & 511 | in[33 + inPos] << 9 & 32767;
         out[18 + outPos] = in[33 + inPos] >> 6 & 3 | in[34 + inPos] << 2 & 1023 | in[35 + inPos] << 10 & 32767;
         out[19 + outPos] = in[35 + inPos] >> 5 & 7 | in[36 + inPos] << 3 & 2047 | in[37 + inPos] << 11 & 32767;
         out[20 + outPos] = in[37 + inPos] >> 4 & 15 | in[38 + inPos] << 4 & 4095 | in[39 + inPos] << 12 & 32767;
         out[21 + outPos] = in[39 + inPos] >> 3 & 31 | in[40 + inPos] << 5 & 8191 | in[41 + inPos] << 13 & 32767;
         out[22 + outPos] = in[41 + inPos] >> 2 & 63 | in[42 + inPos] << 6 & 16383 | in[43 + inPos] << 14 & 32767;
         out[23 + outPos] = in[43 + inPos] >> 1 & 127 | in[44 + inPos] << 7 & 32767;
         out[24 + outPos] = in[45 + inPos] & 255 | in[46 + inPos] << 8 & 32767;
         out[25 + outPos] = in[46 + inPos] >> 7 & 1 | in[47 + inPos] << 1 & 511 | in[48 + inPos] << 9 & 32767;
         out[26 + outPos] = in[48 + inPos] >> 6 & 3 | in[49 + inPos] << 2 & 1023 | in[50 + inPos] << 10 & 32767;
         out[27 + outPos] = in[50 + inPos] >> 5 & 7 | in[51 + inPos] << 3 & 2047 | in[52 + inPos] << 11 & 32767;
         out[28 + outPos] = in[52 + inPos] >> 4 & 15 | in[53 + inPos] << 4 & 4095 | in[54 + inPos] << 12 & 32767;
         out[29 + outPos] = in[54 + inPos] >> 3 & 31 | in[55 + inPos] << 5 & 8191 | in[56 + inPos] << 13 & 32767;
         out[30 + outPos] = in[56 + inPos] >> 2 & 63 | in[57 + inPos] << 6 & 16383 | in[58 + inPos] << 14 & 32767;
         out[31 + outPos] = in[58 + inPos] >> 1 & 127 | in[59 + inPos] << 7 & 32767;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & 32767;
         out[1 + outPos] = in.get(1 + inPos) >> 7 & 1 | in.get(2 + inPos) << 1 & 511 | in.get(3 + inPos) << 9 & 32767;
         out[2 + outPos] = in.get(3 + inPos) >> 6 & 3 | in.get(4 + inPos) << 2 & 1023 | in.get(5 + inPos) << 10 & 32767;
         out[3 + outPos] = in.get(5 + inPos) >> 5 & 7 | in.get(6 + inPos) << 3 & 2047 | in.get(7 + inPos) << 11 & 32767;
         out[4 + outPos] = in.get(7 + inPos) >> 4 & 15 | in.get(8 + inPos) << 4 & 4095 | in.get(9 + inPos) << 12 & 32767;
         out[5 + outPos] = in.get(9 + inPos) >> 3 & 31 | in.get(10 + inPos) << 5 & 8191 | in.get(11 + inPos) << 13 & 32767;
         out[6 + outPos] = in.get(11 + inPos) >> 2 & 63 | in.get(12 + inPos) << 6 & 16383 | in.get(13 + inPos) << 14 & 32767;
         out[7 + outPos] = in.get(13 + inPos) >> 1 & 127 | in.get(14 + inPos) << 7 & 32767;
         out[8 + outPos] = in.get(15 + inPos) & 255 | in.get(16 + inPos) << 8 & 32767;
         out[9 + outPos] = in.get(16 + inPos) >> 7 & 1 | in.get(17 + inPos) << 1 & 511 | in.get(18 + inPos) << 9 & 32767;
         out[10 + outPos] = in.get(18 + inPos) >> 6 & 3 | in.get(19 + inPos) << 2 & 1023 | in.get(20 + inPos) << 10 & 32767;
         out[11 + outPos] = in.get(20 + inPos) >> 5 & 7 | in.get(21 + inPos) << 3 & 2047 | in.get(22 + inPos) << 11 & 32767;
         out[12 + outPos] = in.get(22 + inPos) >> 4 & 15 | in.get(23 + inPos) << 4 & 4095 | in.get(24 + inPos) << 12 & 32767;
         out[13 + outPos] = in.get(24 + inPos) >> 3 & 31 | in.get(25 + inPos) << 5 & 8191 | in.get(26 + inPos) << 13 & 32767;
         out[14 + outPos] = in.get(26 + inPos) >> 2 & 63 | in.get(27 + inPos) << 6 & 16383 | in.get(28 + inPos) << 14 & 32767;
         out[15 + outPos] = in.get(28 + inPos) >> 1 & 127 | in.get(29 + inPos) << 7 & 32767;
         out[16 + outPos] = in.get(30 + inPos) & 255 | in.get(31 + inPos) << 8 & 32767;
         out[17 + outPos] = in.get(31 + inPos) >> 7 & 1 | in.get(32 + inPos) << 1 & 511 | in.get(33 + inPos) << 9 & 32767;
         out[18 + outPos] = in.get(33 + inPos) >> 6 & 3 | in.get(34 + inPos) << 2 & 1023 | in.get(35 + inPos) << 10 & 32767;
         out[19 + outPos] = in.get(35 + inPos) >> 5 & 7 | in.get(36 + inPos) << 3 & 2047 | in.get(37 + inPos) << 11 & 32767;
         out[20 + outPos] = in.get(37 + inPos) >> 4 & 15 | in.get(38 + inPos) << 4 & 4095 | in.get(39 + inPos) << 12 & 32767;
         out[21 + outPos] = in.get(39 + inPos) >> 3 & 31 | in.get(40 + inPos) << 5 & 8191 | in.get(41 + inPos) << 13 & 32767;
         out[22 + outPos] = in.get(41 + inPos) >> 2 & 63 | in.get(42 + inPos) << 6 & 16383 | in.get(43 + inPos) << 14 & 32767;
         out[23 + outPos] = in.get(43 + inPos) >> 1 & 127 | in.get(44 + inPos) << 7 & 32767;
         out[24 + outPos] = in.get(45 + inPos) & 255 | in.get(46 + inPos) << 8 & 32767;
         out[25 + outPos] = in.get(46 + inPos) >> 7 & 1 | in.get(47 + inPos) << 1 & 511 | in.get(48 + inPos) << 9 & 32767;
         out[26 + outPos] = in.get(48 + inPos) >> 6 & 3 | in.get(49 + inPos) << 2 & 1023 | in.get(50 + inPos) << 10 & 32767;
         out[27 + outPos] = in.get(50 + inPos) >> 5 & 7 | in.get(51 + inPos) << 3 & 2047 | in.get(52 + inPos) << 11 & 32767;
         out[28 + outPos] = in.get(52 + inPos) >> 4 & 15 | in.get(53 + inPos) << 4 & 4095 | in.get(54 + inPos) << 12 & 32767;
         out[29 + outPos] = in.get(54 + inPos) >> 3 & 31 | in.get(55 + inPos) << 5 & 8191 | in.get(56 + inPos) << 13 & 32767;
         out[30 + outPos] = in.get(56 + inPos) >> 2 & 63 | in.get(57 + inPos) << 6 & 16383 | in.get(58 + inPos) << 14 & 32767;
         out[31 + outPos] = in.get(58 + inPos) >> 1 & 127 | in.get(59 + inPos) << 7 & 32767;
      }
   }

   private static final class Packer16 extends BytePacker {
      private Packer16() {
         super(16);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & '\uffff' & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & '\uffff') >>> 8 & 255);
         out[2 + outPos] = (byte)(in[1 + inPos] & '\uffff' & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & '\uffff') >>> 8 & 255);
         out[4 + outPos] = (byte)(in[2 + inPos] & '\uffff' & 255);
         out[5 + outPos] = (byte)((in[2 + inPos] & '\uffff') >>> 8 & 255);
         out[6 + outPos] = (byte)(in[3 + inPos] & '\uffff' & 255);
         out[7 + outPos] = (byte)((in[3 + inPos] & '\uffff') >>> 8 & 255);
         out[8 + outPos] = (byte)(in[4 + inPos] & '\uffff' & 255);
         out[9 + outPos] = (byte)((in[4 + inPos] & '\uffff') >>> 8 & 255);
         out[10 + outPos] = (byte)(in[5 + inPos] & '\uffff' & 255);
         out[11 + outPos] = (byte)((in[5 + inPos] & '\uffff') >>> 8 & 255);
         out[12 + outPos] = (byte)(in[6 + inPos] & '\uffff' & 255);
         out[13 + outPos] = (byte)((in[6 + inPos] & '\uffff') >>> 8 & 255);
         out[14 + outPos] = (byte)(in[7 + inPos] & '\uffff' & 255);
         out[15 + outPos] = (byte)((in[7 + inPos] & '\uffff') >>> 8 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & '\uffff' & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & '\uffff') >>> 8 & 255);
         out[2 + outPos] = (byte)(in[1 + inPos] & '\uffff' & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & '\uffff') >>> 8 & 255);
         out[4 + outPos] = (byte)(in[2 + inPos] & '\uffff' & 255);
         out[5 + outPos] = (byte)((in[2 + inPos] & '\uffff') >>> 8 & 255);
         out[6 + outPos] = (byte)(in[3 + inPos] & '\uffff' & 255);
         out[7 + outPos] = (byte)((in[3 + inPos] & '\uffff') >>> 8 & 255);
         out[8 + outPos] = (byte)(in[4 + inPos] & '\uffff' & 255);
         out[9 + outPos] = (byte)((in[4 + inPos] & '\uffff') >>> 8 & 255);
         out[10 + outPos] = (byte)(in[5 + inPos] & '\uffff' & 255);
         out[11 + outPos] = (byte)((in[5 + inPos] & '\uffff') >>> 8 & 255);
         out[12 + outPos] = (byte)(in[6 + inPos] & '\uffff' & 255);
         out[13 + outPos] = (byte)((in[6 + inPos] & '\uffff') >>> 8 & 255);
         out[14 + outPos] = (byte)(in[7 + inPos] & '\uffff' & 255);
         out[15 + outPos] = (byte)((in[7 + inPos] & '\uffff') >>> 8 & 255);
         out[16 + outPos] = (byte)(in[8 + inPos] & '\uffff' & 255);
         out[17 + outPos] = (byte)((in[8 + inPos] & '\uffff') >>> 8 & 255);
         out[18 + outPos] = (byte)(in[9 + inPos] & '\uffff' & 255);
         out[19 + outPos] = (byte)((in[9 + inPos] & '\uffff') >>> 8 & 255);
         out[20 + outPos] = (byte)(in[10 + inPos] & '\uffff' & 255);
         out[21 + outPos] = (byte)((in[10 + inPos] & '\uffff') >>> 8 & 255);
         out[22 + outPos] = (byte)(in[11 + inPos] & '\uffff' & 255);
         out[23 + outPos] = (byte)((in[11 + inPos] & '\uffff') >>> 8 & 255);
         out[24 + outPos] = (byte)(in[12 + inPos] & '\uffff' & 255);
         out[25 + outPos] = (byte)((in[12 + inPos] & '\uffff') >>> 8 & 255);
         out[26 + outPos] = (byte)(in[13 + inPos] & '\uffff' & 255);
         out[27 + outPos] = (byte)((in[13 + inPos] & '\uffff') >>> 8 & 255);
         out[28 + outPos] = (byte)(in[14 + inPos] & '\uffff' & 255);
         out[29 + outPos] = (byte)((in[14 + inPos] & '\uffff') >>> 8 & 255);
         out[30 + outPos] = (byte)(in[15 + inPos] & '\uffff' & 255);
         out[31 + outPos] = (byte)((in[15 + inPos] & '\uffff') >>> 8 & 255);
         out[32 + outPos] = (byte)(in[16 + inPos] & '\uffff' & 255);
         out[33 + outPos] = (byte)((in[16 + inPos] & '\uffff') >>> 8 & 255);
         out[34 + outPos] = (byte)(in[17 + inPos] & '\uffff' & 255);
         out[35 + outPos] = (byte)((in[17 + inPos] & '\uffff') >>> 8 & 255);
         out[36 + outPos] = (byte)(in[18 + inPos] & '\uffff' & 255);
         out[37 + outPos] = (byte)((in[18 + inPos] & '\uffff') >>> 8 & 255);
         out[38 + outPos] = (byte)(in[19 + inPos] & '\uffff' & 255);
         out[39 + outPos] = (byte)((in[19 + inPos] & '\uffff') >>> 8 & 255);
         out[40 + outPos] = (byte)(in[20 + inPos] & '\uffff' & 255);
         out[41 + outPos] = (byte)((in[20 + inPos] & '\uffff') >>> 8 & 255);
         out[42 + outPos] = (byte)(in[21 + inPos] & '\uffff' & 255);
         out[43 + outPos] = (byte)((in[21 + inPos] & '\uffff') >>> 8 & 255);
         out[44 + outPos] = (byte)(in[22 + inPos] & '\uffff' & 255);
         out[45 + outPos] = (byte)((in[22 + inPos] & '\uffff') >>> 8 & 255);
         out[46 + outPos] = (byte)(in[23 + inPos] & '\uffff' & 255);
         out[47 + outPos] = (byte)((in[23 + inPos] & '\uffff') >>> 8 & 255);
         out[48 + outPos] = (byte)(in[24 + inPos] & '\uffff' & 255);
         out[49 + outPos] = (byte)((in[24 + inPos] & '\uffff') >>> 8 & 255);
         out[50 + outPos] = (byte)(in[25 + inPos] & '\uffff' & 255);
         out[51 + outPos] = (byte)((in[25 + inPos] & '\uffff') >>> 8 & 255);
         out[52 + outPos] = (byte)(in[26 + inPos] & '\uffff' & 255);
         out[53 + outPos] = (byte)((in[26 + inPos] & '\uffff') >>> 8 & 255);
         out[54 + outPos] = (byte)(in[27 + inPos] & '\uffff' & 255);
         out[55 + outPos] = (byte)((in[27 + inPos] & '\uffff') >>> 8 & 255);
         out[56 + outPos] = (byte)(in[28 + inPos] & '\uffff' & 255);
         out[57 + outPos] = (byte)((in[28 + inPos] & '\uffff') >>> 8 & 255);
         out[58 + outPos] = (byte)(in[29 + inPos] & '\uffff' & 255);
         out[59 + outPos] = (byte)((in[29 + inPos] & '\uffff') >>> 8 & 255);
         out[60 + outPos] = (byte)(in[30 + inPos] & '\uffff' & 255);
         out[61 + outPos] = (byte)((in[30 + inPos] & '\uffff') >>> 8 & 255);
         out[62 + outPos] = (byte)(in[31 + inPos] & '\uffff' & 255);
         out[63 + outPos] = (byte)((in[31 + inPos] & '\uffff') >>> 8 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff';
         out[1 + outPos] = in[2 + inPos] & 255 | in[3 + inPos] << 8 & '\uffff';
         out[2 + outPos] = in[4 + inPos] & 255 | in[5 + inPos] << 8 & '\uffff';
         out[3 + outPos] = in[6 + inPos] & 255 | in[7 + inPos] << 8 & '\uffff';
         out[4 + outPos] = in[8 + inPos] & 255 | in[9 + inPos] << 8 & '\uffff';
         out[5 + outPos] = in[10 + inPos] & 255 | in[11 + inPos] << 8 & '\uffff';
         out[6 + outPos] = in[12 + inPos] & 255 | in[13 + inPos] << 8 & '\uffff';
         out[7 + outPos] = in[14 + inPos] & 255 | in[15 + inPos] << 8 & '\uffff';
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff';
         out[1 + outPos] = in.get(2 + inPos) & 255 | in.get(3 + inPos) << 8 & '\uffff';
         out[2 + outPos] = in.get(4 + inPos) & 255 | in.get(5 + inPos) << 8 & '\uffff';
         out[3 + outPos] = in.get(6 + inPos) & 255 | in.get(7 + inPos) << 8 & '\uffff';
         out[4 + outPos] = in.get(8 + inPos) & 255 | in.get(9 + inPos) << 8 & '\uffff';
         out[5 + outPos] = in.get(10 + inPos) & 255 | in.get(11 + inPos) << 8 & '\uffff';
         out[6 + outPos] = in.get(12 + inPos) & 255 | in.get(13 + inPos) << 8 & '\uffff';
         out[7 + outPos] = in.get(14 + inPos) & 255 | in.get(15 + inPos) << 8 & '\uffff';
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff';
         out[1 + outPos] = in[2 + inPos] & 255 | in[3 + inPos] << 8 & '\uffff';
         out[2 + outPos] = in[4 + inPos] & 255 | in[5 + inPos] << 8 & '\uffff';
         out[3 + outPos] = in[6 + inPos] & 255 | in[7 + inPos] << 8 & '\uffff';
         out[4 + outPos] = in[8 + inPos] & 255 | in[9 + inPos] << 8 & '\uffff';
         out[5 + outPos] = in[10 + inPos] & 255 | in[11 + inPos] << 8 & '\uffff';
         out[6 + outPos] = in[12 + inPos] & 255 | in[13 + inPos] << 8 & '\uffff';
         out[7 + outPos] = in[14 + inPos] & 255 | in[15 + inPos] << 8 & '\uffff';
         out[8 + outPos] = in[16 + inPos] & 255 | in[17 + inPos] << 8 & '\uffff';
         out[9 + outPos] = in[18 + inPos] & 255 | in[19 + inPos] << 8 & '\uffff';
         out[10 + outPos] = in[20 + inPos] & 255 | in[21 + inPos] << 8 & '\uffff';
         out[11 + outPos] = in[22 + inPos] & 255 | in[23 + inPos] << 8 & '\uffff';
         out[12 + outPos] = in[24 + inPos] & 255 | in[25 + inPos] << 8 & '\uffff';
         out[13 + outPos] = in[26 + inPos] & 255 | in[27 + inPos] << 8 & '\uffff';
         out[14 + outPos] = in[28 + inPos] & 255 | in[29 + inPos] << 8 & '\uffff';
         out[15 + outPos] = in[30 + inPos] & 255 | in[31 + inPos] << 8 & '\uffff';
         out[16 + outPos] = in[32 + inPos] & 255 | in[33 + inPos] << 8 & '\uffff';
         out[17 + outPos] = in[34 + inPos] & 255 | in[35 + inPos] << 8 & '\uffff';
         out[18 + outPos] = in[36 + inPos] & 255 | in[37 + inPos] << 8 & '\uffff';
         out[19 + outPos] = in[38 + inPos] & 255 | in[39 + inPos] << 8 & '\uffff';
         out[20 + outPos] = in[40 + inPos] & 255 | in[41 + inPos] << 8 & '\uffff';
         out[21 + outPos] = in[42 + inPos] & 255 | in[43 + inPos] << 8 & '\uffff';
         out[22 + outPos] = in[44 + inPos] & 255 | in[45 + inPos] << 8 & '\uffff';
         out[23 + outPos] = in[46 + inPos] & 255 | in[47 + inPos] << 8 & '\uffff';
         out[24 + outPos] = in[48 + inPos] & 255 | in[49 + inPos] << 8 & '\uffff';
         out[25 + outPos] = in[50 + inPos] & 255 | in[51 + inPos] << 8 & '\uffff';
         out[26 + outPos] = in[52 + inPos] & 255 | in[53 + inPos] << 8 & '\uffff';
         out[27 + outPos] = in[54 + inPos] & 255 | in[55 + inPos] << 8 & '\uffff';
         out[28 + outPos] = in[56 + inPos] & 255 | in[57 + inPos] << 8 & '\uffff';
         out[29 + outPos] = in[58 + inPos] & 255 | in[59 + inPos] << 8 & '\uffff';
         out[30 + outPos] = in[60 + inPos] & 255 | in[61 + inPos] << 8 & '\uffff';
         out[31 + outPos] = in[62 + inPos] & 255 | in[63 + inPos] << 8 & '\uffff';
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff';
         out[1 + outPos] = in.get(2 + inPos) & 255 | in.get(3 + inPos) << 8 & '\uffff';
         out[2 + outPos] = in.get(4 + inPos) & 255 | in.get(5 + inPos) << 8 & '\uffff';
         out[3 + outPos] = in.get(6 + inPos) & 255 | in.get(7 + inPos) << 8 & '\uffff';
         out[4 + outPos] = in.get(8 + inPos) & 255 | in.get(9 + inPos) << 8 & '\uffff';
         out[5 + outPos] = in.get(10 + inPos) & 255 | in.get(11 + inPos) << 8 & '\uffff';
         out[6 + outPos] = in.get(12 + inPos) & 255 | in.get(13 + inPos) << 8 & '\uffff';
         out[7 + outPos] = in.get(14 + inPos) & 255 | in.get(15 + inPos) << 8 & '\uffff';
         out[8 + outPos] = in.get(16 + inPos) & 255 | in.get(17 + inPos) << 8 & '\uffff';
         out[9 + outPos] = in.get(18 + inPos) & 255 | in.get(19 + inPos) << 8 & '\uffff';
         out[10 + outPos] = in.get(20 + inPos) & 255 | in.get(21 + inPos) << 8 & '\uffff';
         out[11 + outPos] = in.get(22 + inPos) & 255 | in.get(23 + inPos) << 8 & '\uffff';
         out[12 + outPos] = in.get(24 + inPos) & 255 | in.get(25 + inPos) << 8 & '\uffff';
         out[13 + outPos] = in.get(26 + inPos) & 255 | in.get(27 + inPos) << 8 & '\uffff';
         out[14 + outPos] = in.get(28 + inPos) & 255 | in.get(29 + inPos) << 8 & '\uffff';
         out[15 + outPos] = in.get(30 + inPos) & 255 | in.get(31 + inPos) << 8 & '\uffff';
         out[16 + outPos] = in.get(32 + inPos) & 255 | in.get(33 + inPos) << 8 & '\uffff';
         out[17 + outPos] = in.get(34 + inPos) & 255 | in.get(35 + inPos) << 8 & '\uffff';
         out[18 + outPos] = in.get(36 + inPos) & 255 | in.get(37 + inPos) << 8 & '\uffff';
         out[19 + outPos] = in.get(38 + inPos) & 255 | in.get(39 + inPos) << 8 & '\uffff';
         out[20 + outPos] = in.get(40 + inPos) & 255 | in.get(41 + inPos) << 8 & '\uffff';
         out[21 + outPos] = in.get(42 + inPos) & 255 | in.get(43 + inPos) << 8 & '\uffff';
         out[22 + outPos] = in.get(44 + inPos) & 255 | in.get(45 + inPos) << 8 & '\uffff';
         out[23 + outPos] = in.get(46 + inPos) & 255 | in.get(47 + inPos) << 8 & '\uffff';
         out[24 + outPos] = in.get(48 + inPos) & 255 | in.get(49 + inPos) << 8 & '\uffff';
         out[25 + outPos] = in.get(50 + inPos) & 255 | in.get(51 + inPos) << 8 & '\uffff';
         out[26 + outPos] = in.get(52 + inPos) & 255 | in.get(53 + inPos) << 8 & '\uffff';
         out[27 + outPos] = in.get(54 + inPos) & 255 | in.get(55 + inPos) << 8 & '\uffff';
         out[28 + outPos] = in.get(56 + inPos) & 255 | in.get(57 + inPos) << 8 & '\uffff';
         out[29 + outPos] = in.get(58 + inPos) & 255 | in.get(59 + inPos) << 8 & '\uffff';
         out[30 + outPos] = in.get(60 + inPos) & 255 | in.get(61 + inPos) << 8 & '\uffff';
         out[31 + outPos] = in.get(62 + inPos) & 255 | in.get(63 + inPos) << 8 & '\uffff';
      }
   }

   private static final class Packer17 extends BytePacker {
      private Packer17() {
         super(17);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 131071 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 131071) >>> 8 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 131071) >>> 16 | (in[1 + inPos] & 131071) << 1) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 131071) >>> 7 & 255);
         out[4 + outPos] = (byte)(((in[1 + inPos] & 131071) >>> 15 | (in[2 + inPos] & 131071) << 2) & 255);
         out[5 + outPos] = (byte)((in[2 + inPos] & 131071) >>> 6 & 255);
         out[6 + outPos] = (byte)(((in[2 + inPos] & 131071) >>> 14 | (in[3 + inPos] & 131071) << 3) & 255);
         out[7 + outPos] = (byte)((in[3 + inPos] & 131071) >>> 5 & 255);
         out[8 + outPos] = (byte)(((in[3 + inPos] & 131071) >>> 13 | (in[4 + inPos] & 131071) << 4) & 255);
         out[9 + outPos] = (byte)((in[4 + inPos] & 131071) >>> 4 & 255);
         out[10 + outPos] = (byte)(((in[4 + inPos] & 131071) >>> 12 | (in[5 + inPos] & 131071) << 5) & 255);
         out[11 + outPos] = (byte)((in[5 + inPos] & 131071) >>> 3 & 255);
         out[12 + outPos] = (byte)(((in[5 + inPos] & 131071) >>> 11 | (in[6 + inPos] & 131071) << 6) & 255);
         out[13 + outPos] = (byte)((in[6 + inPos] & 131071) >>> 2 & 255);
         out[14 + outPos] = (byte)(((in[6 + inPos] & 131071) >>> 10 | (in[7 + inPos] & 131071) << 7) & 255);
         out[15 + outPos] = (byte)((in[7 + inPos] & 131071) >>> 1 & 255);
         out[16 + outPos] = (byte)((in[7 + inPos] & 131071) >>> 9 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 131071 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 131071) >>> 8 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 131071) >>> 16 | (in[1 + inPos] & 131071) << 1) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 131071) >>> 7 & 255);
         out[4 + outPos] = (byte)(((in[1 + inPos] & 131071) >>> 15 | (in[2 + inPos] & 131071) << 2) & 255);
         out[5 + outPos] = (byte)((in[2 + inPos] & 131071) >>> 6 & 255);
         out[6 + outPos] = (byte)(((in[2 + inPos] & 131071) >>> 14 | (in[3 + inPos] & 131071) << 3) & 255);
         out[7 + outPos] = (byte)((in[3 + inPos] & 131071) >>> 5 & 255);
         out[8 + outPos] = (byte)(((in[3 + inPos] & 131071) >>> 13 | (in[4 + inPos] & 131071) << 4) & 255);
         out[9 + outPos] = (byte)((in[4 + inPos] & 131071) >>> 4 & 255);
         out[10 + outPos] = (byte)(((in[4 + inPos] & 131071) >>> 12 | (in[5 + inPos] & 131071) << 5) & 255);
         out[11 + outPos] = (byte)((in[5 + inPos] & 131071) >>> 3 & 255);
         out[12 + outPos] = (byte)(((in[5 + inPos] & 131071) >>> 11 | (in[6 + inPos] & 131071) << 6) & 255);
         out[13 + outPos] = (byte)((in[6 + inPos] & 131071) >>> 2 & 255);
         out[14 + outPos] = (byte)(((in[6 + inPos] & 131071) >>> 10 | (in[7 + inPos] & 131071) << 7) & 255);
         out[15 + outPos] = (byte)((in[7 + inPos] & 131071) >>> 1 & 255);
         out[16 + outPos] = (byte)((in[7 + inPos] & 131071) >>> 9 & 255);
         out[17 + outPos] = (byte)(in[8 + inPos] & 131071 & 255);
         out[18 + outPos] = (byte)((in[8 + inPos] & 131071) >>> 8 & 255);
         out[19 + outPos] = (byte)(((in[8 + inPos] & 131071) >>> 16 | (in[9 + inPos] & 131071) << 1) & 255);
         out[20 + outPos] = (byte)((in[9 + inPos] & 131071) >>> 7 & 255);
         out[21 + outPos] = (byte)(((in[9 + inPos] & 131071) >>> 15 | (in[10 + inPos] & 131071) << 2) & 255);
         out[22 + outPos] = (byte)((in[10 + inPos] & 131071) >>> 6 & 255);
         out[23 + outPos] = (byte)(((in[10 + inPos] & 131071) >>> 14 | (in[11 + inPos] & 131071) << 3) & 255);
         out[24 + outPos] = (byte)((in[11 + inPos] & 131071) >>> 5 & 255);
         out[25 + outPos] = (byte)(((in[11 + inPos] & 131071) >>> 13 | (in[12 + inPos] & 131071) << 4) & 255);
         out[26 + outPos] = (byte)((in[12 + inPos] & 131071) >>> 4 & 255);
         out[27 + outPos] = (byte)(((in[12 + inPos] & 131071) >>> 12 | (in[13 + inPos] & 131071) << 5) & 255);
         out[28 + outPos] = (byte)((in[13 + inPos] & 131071) >>> 3 & 255);
         out[29 + outPos] = (byte)(((in[13 + inPos] & 131071) >>> 11 | (in[14 + inPos] & 131071) << 6) & 255);
         out[30 + outPos] = (byte)((in[14 + inPos] & 131071) >>> 2 & 255);
         out[31 + outPos] = (byte)(((in[14 + inPos] & 131071) >>> 10 | (in[15 + inPos] & 131071) << 7) & 255);
         out[32 + outPos] = (byte)((in[15 + inPos] & 131071) >>> 1 & 255);
         out[33 + outPos] = (byte)((in[15 + inPos] & 131071) >>> 9 & 255);
         out[34 + outPos] = (byte)(in[16 + inPos] & 131071 & 255);
         out[35 + outPos] = (byte)((in[16 + inPos] & 131071) >>> 8 & 255);
         out[36 + outPos] = (byte)(((in[16 + inPos] & 131071) >>> 16 | (in[17 + inPos] & 131071) << 1) & 255);
         out[37 + outPos] = (byte)((in[17 + inPos] & 131071) >>> 7 & 255);
         out[38 + outPos] = (byte)(((in[17 + inPos] & 131071) >>> 15 | (in[18 + inPos] & 131071) << 2) & 255);
         out[39 + outPos] = (byte)((in[18 + inPos] & 131071) >>> 6 & 255);
         out[40 + outPos] = (byte)(((in[18 + inPos] & 131071) >>> 14 | (in[19 + inPos] & 131071) << 3) & 255);
         out[41 + outPos] = (byte)((in[19 + inPos] & 131071) >>> 5 & 255);
         out[42 + outPos] = (byte)(((in[19 + inPos] & 131071) >>> 13 | (in[20 + inPos] & 131071) << 4) & 255);
         out[43 + outPos] = (byte)((in[20 + inPos] & 131071) >>> 4 & 255);
         out[44 + outPos] = (byte)(((in[20 + inPos] & 131071) >>> 12 | (in[21 + inPos] & 131071) << 5) & 255);
         out[45 + outPos] = (byte)((in[21 + inPos] & 131071) >>> 3 & 255);
         out[46 + outPos] = (byte)(((in[21 + inPos] & 131071) >>> 11 | (in[22 + inPos] & 131071) << 6) & 255);
         out[47 + outPos] = (byte)((in[22 + inPos] & 131071) >>> 2 & 255);
         out[48 + outPos] = (byte)(((in[22 + inPos] & 131071) >>> 10 | (in[23 + inPos] & 131071) << 7) & 255);
         out[49 + outPos] = (byte)((in[23 + inPos] & 131071) >>> 1 & 255);
         out[50 + outPos] = (byte)((in[23 + inPos] & 131071) >>> 9 & 255);
         out[51 + outPos] = (byte)(in[24 + inPos] & 131071 & 255);
         out[52 + outPos] = (byte)((in[24 + inPos] & 131071) >>> 8 & 255);
         out[53 + outPos] = (byte)(((in[24 + inPos] & 131071) >>> 16 | (in[25 + inPos] & 131071) << 1) & 255);
         out[54 + outPos] = (byte)((in[25 + inPos] & 131071) >>> 7 & 255);
         out[55 + outPos] = (byte)(((in[25 + inPos] & 131071) >>> 15 | (in[26 + inPos] & 131071) << 2) & 255);
         out[56 + outPos] = (byte)((in[26 + inPos] & 131071) >>> 6 & 255);
         out[57 + outPos] = (byte)(((in[26 + inPos] & 131071) >>> 14 | (in[27 + inPos] & 131071) << 3) & 255);
         out[58 + outPos] = (byte)((in[27 + inPos] & 131071) >>> 5 & 255);
         out[59 + outPos] = (byte)(((in[27 + inPos] & 131071) >>> 13 | (in[28 + inPos] & 131071) << 4) & 255);
         out[60 + outPos] = (byte)((in[28 + inPos] & 131071) >>> 4 & 255);
         out[61 + outPos] = (byte)(((in[28 + inPos] & 131071) >>> 12 | (in[29 + inPos] & 131071) << 5) & 255);
         out[62 + outPos] = (byte)((in[29 + inPos] & 131071) >>> 3 & 255);
         out[63 + outPos] = (byte)(((in[29 + inPos] & 131071) >>> 11 | (in[30 + inPos] & 131071) << 6) & 255);
         out[64 + outPos] = (byte)((in[30 + inPos] & 131071) >>> 2 & 255);
         out[65 + outPos] = (byte)(((in[30 + inPos] & 131071) >>> 10 | (in[31 + inPos] & 131071) << 7) & 255);
         out[66 + outPos] = (byte)((in[31 + inPos] & 131071) >>> 1 & 255);
         out[67 + outPos] = (byte)((in[31 + inPos] & 131071) >>> 9 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 131071;
         out[1 + outPos] = in[2 + inPos] >> 1 & 127 | in[3 + inPos] << 7 & 32767 | in[4 + inPos] << 15 & 131071;
         out[2 + outPos] = in[4 + inPos] >> 2 & 63 | in[5 + inPos] << 6 & 16383 | in[6 + inPos] << 14 & 131071;
         out[3 + outPos] = in[6 + inPos] >> 3 & 31 | in[7 + inPos] << 5 & 8191 | in[8 + inPos] << 13 & 131071;
         out[4 + outPos] = in[8 + inPos] >> 4 & 15 | in[9 + inPos] << 4 & 4095 | in[10 + inPos] << 12 & 131071;
         out[5 + outPos] = in[10 + inPos] >> 5 & 7 | in[11 + inPos] << 3 & 2047 | in[12 + inPos] << 11 & 131071;
         out[6 + outPos] = in[12 + inPos] >> 6 & 3 | in[13 + inPos] << 2 & 1023 | in[14 + inPos] << 10 & 131071;
         out[7 + outPos] = in[14 + inPos] >> 7 & 1 | in[15 + inPos] << 1 & 511 | in[16 + inPos] << 9 & 131071;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 131071;
         out[1 + outPos] = in.get(2 + inPos) >> 1 & 127 | in.get(3 + inPos) << 7 & 32767 | in.get(4 + inPos) << 15 & 131071;
         out[2 + outPos] = in.get(4 + inPos) >> 2 & 63 | in.get(5 + inPos) << 6 & 16383 | in.get(6 + inPos) << 14 & 131071;
         out[3 + outPos] = in.get(6 + inPos) >> 3 & 31 | in.get(7 + inPos) << 5 & 8191 | in.get(8 + inPos) << 13 & 131071;
         out[4 + outPos] = in.get(8 + inPos) >> 4 & 15 | in.get(9 + inPos) << 4 & 4095 | in.get(10 + inPos) << 12 & 131071;
         out[5 + outPos] = in.get(10 + inPos) >> 5 & 7 | in.get(11 + inPos) << 3 & 2047 | in.get(12 + inPos) << 11 & 131071;
         out[6 + outPos] = in.get(12 + inPos) >> 6 & 3 | in.get(13 + inPos) << 2 & 1023 | in.get(14 + inPos) << 10 & 131071;
         out[7 + outPos] = in.get(14 + inPos) >> 7 & 1 | in.get(15 + inPos) << 1 & 511 | in.get(16 + inPos) << 9 & 131071;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 131071;
         out[1 + outPos] = in[2 + inPos] >> 1 & 127 | in[3 + inPos] << 7 & 32767 | in[4 + inPos] << 15 & 131071;
         out[2 + outPos] = in[4 + inPos] >> 2 & 63 | in[5 + inPos] << 6 & 16383 | in[6 + inPos] << 14 & 131071;
         out[3 + outPos] = in[6 + inPos] >> 3 & 31 | in[7 + inPos] << 5 & 8191 | in[8 + inPos] << 13 & 131071;
         out[4 + outPos] = in[8 + inPos] >> 4 & 15 | in[9 + inPos] << 4 & 4095 | in[10 + inPos] << 12 & 131071;
         out[5 + outPos] = in[10 + inPos] >> 5 & 7 | in[11 + inPos] << 3 & 2047 | in[12 + inPos] << 11 & 131071;
         out[6 + outPos] = in[12 + inPos] >> 6 & 3 | in[13 + inPos] << 2 & 1023 | in[14 + inPos] << 10 & 131071;
         out[7 + outPos] = in[14 + inPos] >> 7 & 1 | in[15 + inPos] << 1 & 511 | in[16 + inPos] << 9 & 131071;
         out[8 + outPos] = in[17 + inPos] & 255 | in[18 + inPos] << 8 & '\uffff' | in[19 + inPos] << 16 & 131071;
         out[9 + outPos] = in[19 + inPos] >> 1 & 127 | in[20 + inPos] << 7 & 32767 | in[21 + inPos] << 15 & 131071;
         out[10 + outPos] = in[21 + inPos] >> 2 & 63 | in[22 + inPos] << 6 & 16383 | in[23 + inPos] << 14 & 131071;
         out[11 + outPos] = in[23 + inPos] >> 3 & 31 | in[24 + inPos] << 5 & 8191 | in[25 + inPos] << 13 & 131071;
         out[12 + outPos] = in[25 + inPos] >> 4 & 15 | in[26 + inPos] << 4 & 4095 | in[27 + inPos] << 12 & 131071;
         out[13 + outPos] = in[27 + inPos] >> 5 & 7 | in[28 + inPos] << 3 & 2047 | in[29 + inPos] << 11 & 131071;
         out[14 + outPos] = in[29 + inPos] >> 6 & 3 | in[30 + inPos] << 2 & 1023 | in[31 + inPos] << 10 & 131071;
         out[15 + outPos] = in[31 + inPos] >> 7 & 1 | in[32 + inPos] << 1 & 511 | in[33 + inPos] << 9 & 131071;
         out[16 + outPos] = in[34 + inPos] & 255 | in[35 + inPos] << 8 & '\uffff' | in[36 + inPos] << 16 & 131071;
         out[17 + outPos] = in[36 + inPos] >> 1 & 127 | in[37 + inPos] << 7 & 32767 | in[38 + inPos] << 15 & 131071;
         out[18 + outPos] = in[38 + inPos] >> 2 & 63 | in[39 + inPos] << 6 & 16383 | in[40 + inPos] << 14 & 131071;
         out[19 + outPos] = in[40 + inPos] >> 3 & 31 | in[41 + inPos] << 5 & 8191 | in[42 + inPos] << 13 & 131071;
         out[20 + outPos] = in[42 + inPos] >> 4 & 15 | in[43 + inPos] << 4 & 4095 | in[44 + inPos] << 12 & 131071;
         out[21 + outPos] = in[44 + inPos] >> 5 & 7 | in[45 + inPos] << 3 & 2047 | in[46 + inPos] << 11 & 131071;
         out[22 + outPos] = in[46 + inPos] >> 6 & 3 | in[47 + inPos] << 2 & 1023 | in[48 + inPos] << 10 & 131071;
         out[23 + outPos] = in[48 + inPos] >> 7 & 1 | in[49 + inPos] << 1 & 511 | in[50 + inPos] << 9 & 131071;
         out[24 + outPos] = in[51 + inPos] & 255 | in[52 + inPos] << 8 & '\uffff' | in[53 + inPos] << 16 & 131071;
         out[25 + outPos] = in[53 + inPos] >> 1 & 127 | in[54 + inPos] << 7 & 32767 | in[55 + inPos] << 15 & 131071;
         out[26 + outPos] = in[55 + inPos] >> 2 & 63 | in[56 + inPos] << 6 & 16383 | in[57 + inPos] << 14 & 131071;
         out[27 + outPos] = in[57 + inPos] >> 3 & 31 | in[58 + inPos] << 5 & 8191 | in[59 + inPos] << 13 & 131071;
         out[28 + outPos] = in[59 + inPos] >> 4 & 15 | in[60 + inPos] << 4 & 4095 | in[61 + inPos] << 12 & 131071;
         out[29 + outPos] = in[61 + inPos] >> 5 & 7 | in[62 + inPos] << 3 & 2047 | in[63 + inPos] << 11 & 131071;
         out[30 + outPos] = in[63 + inPos] >> 6 & 3 | in[64 + inPos] << 2 & 1023 | in[65 + inPos] << 10 & 131071;
         out[31 + outPos] = in[65 + inPos] >> 7 & 1 | in[66 + inPos] << 1 & 511 | in[67 + inPos] << 9 & 131071;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 131071;
         out[1 + outPos] = in.get(2 + inPos) >> 1 & 127 | in.get(3 + inPos) << 7 & 32767 | in.get(4 + inPos) << 15 & 131071;
         out[2 + outPos] = in.get(4 + inPos) >> 2 & 63 | in.get(5 + inPos) << 6 & 16383 | in.get(6 + inPos) << 14 & 131071;
         out[3 + outPos] = in.get(6 + inPos) >> 3 & 31 | in.get(7 + inPos) << 5 & 8191 | in.get(8 + inPos) << 13 & 131071;
         out[4 + outPos] = in.get(8 + inPos) >> 4 & 15 | in.get(9 + inPos) << 4 & 4095 | in.get(10 + inPos) << 12 & 131071;
         out[5 + outPos] = in.get(10 + inPos) >> 5 & 7 | in.get(11 + inPos) << 3 & 2047 | in.get(12 + inPos) << 11 & 131071;
         out[6 + outPos] = in.get(12 + inPos) >> 6 & 3 | in.get(13 + inPos) << 2 & 1023 | in.get(14 + inPos) << 10 & 131071;
         out[7 + outPos] = in.get(14 + inPos) >> 7 & 1 | in.get(15 + inPos) << 1 & 511 | in.get(16 + inPos) << 9 & 131071;
         out[8 + outPos] = in.get(17 + inPos) & 255 | in.get(18 + inPos) << 8 & '\uffff' | in.get(19 + inPos) << 16 & 131071;
         out[9 + outPos] = in.get(19 + inPos) >> 1 & 127 | in.get(20 + inPos) << 7 & 32767 | in.get(21 + inPos) << 15 & 131071;
         out[10 + outPos] = in.get(21 + inPos) >> 2 & 63 | in.get(22 + inPos) << 6 & 16383 | in.get(23 + inPos) << 14 & 131071;
         out[11 + outPos] = in.get(23 + inPos) >> 3 & 31 | in.get(24 + inPos) << 5 & 8191 | in.get(25 + inPos) << 13 & 131071;
         out[12 + outPos] = in.get(25 + inPos) >> 4 & 15 | in.get(26 + inPos) << 4 & 4095 | in.get(27 + inPos) << 12 & 131071;
         out[13 + outPos] = in.get(27 + inPos) >> 5 & 7 | in.get(28 + inPos) << 3 & 2047 | in.get(29 + inPos) << 11 & 131071;
         out[14 + outPos] = in.get(29 + inPos) >> 6 & 3 | in.get(30 + inPos) << 2 & 1023 | in.get(31 + inPos) << 10 & 131071;
         out[15 + outPos] = in.get(31 + inPos) >> 7 & 1 | in.get(32 + inPos) << 1 & 511 | in.get(33 + inPos) << 9 & 131071;
         out[16 + outPos] = in.get(34 + inPos) & 255 | in.get(35 + inPos) << 8 & '\uffff' | in.get(36 + inPos) << 16 & 131071;
         out[17 + outPos] = in.get(36 + inPos) >> 1 & 127 | in.get(37 + inPos) << 7 & 32767 | in.get(38 + inPos) << 15 & 131071;
         out[18 + outPos] = in.get(38 + inPos) >> 2 & 63 | in.get(39 + inPos) << 6 & 16383 | in.get(40 + inPos) << 14 & 131071;
         out[19 + outPos] = in.get(40 + inPos) >> 3 & 31 | in.get(41 + inPos) << 5 & 8191 | in.get(42 + inPos) << 13 & 131071;
         out[20 + outPos] = in.get(42 + inPos) >> 4 & 15 | in.get(43 + inPos) << 4 & 4095 | in.get(44 + inPos) << 12 & 131071;
         out[21 + outPos] = in.get(44 + inPos) >> 5 & 7 | in.get(45 + inPos) << 3 & 2047 | in.get(46 + inPos) << 11 & 131071;
         out[22 + outPos] = in.get(46 + inPos) >> 6 & 3 | in.get(47 + inPos) << 2 & 1023 | in.get(48 + inPos) << 10 & 131071;
         out[23 + outPos] = in.get(48 + inPos) >> 7 & 1 | in.get(49 + inPos) << 1 & 511 | in.get(50 + inPos) << 9 & 131071;
         out[24 + outPos] = in.get(51 + inPos) & 255 | in.get(52 + inPos) << 8 & '\uffff' | in.get(53 + inPos) << 16 & 131071;
         out[25 + outPos] = in.get(53 + inPos) >> 1 & 127 | in.get(54 + inPos) << 7 & 32767 | in.get(55 + inPos) << 15 & 131071;
         out[26 + outPos] = in.get(55 + inPos) >> 2 & 63 | in.get(56 + inPos) << 6 & 16383 | in.get(57 + inPos) << 14 & 131071;
         out[27 + outPos] = in.get(57 + inPos) >> 3 & 31 | in.get(58 + inPos) << 5 & 8191 | in.get(59 + inPos) << 13 & 131071;
         out[28 + outPos] = in.get(59 + inPos) >> 4 & 15 | in.get(60 + inPos) << 4 & 4095 | in.get(61 + inPos) << 12 & 131071;
         out[29 + outPos] = in.get(61 + inPos) >> 5 & 7 | in.get(62 + inPos) << 3 & 2047 | in.get(63 + inPos) << 11 & 131071;
         out[30 + outPos] = in.get(63 + inPos) >> 6 & 3 | in.get(64 + inPos) << 2 & 1023 | in.get(65 + inPos) << 10 & 131071;
         out[31 + outPos] = in.get(65 + inPos) >> 7 & 1 | in.get(66 + inPos) << 1 & 511 | in.get(67 + inPos) << 9 & 131071;
      }
   }

   private static final class Packer18 extends BytePacker {
      private Packer18() {
         super(18);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 262143 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 262143) >>> 8 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 262143) >>> 16 | (in[1 + inPos] & 262143) << 2) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 262143) >>> 6 & 255);
         out[4 + outPos] = (byte)(((in[1 + inPos] & 262143) >>> 14 | (in[2 + inPos] & 262143) << 4) & 255);
         out[5 + outPos] = (byte)((in[2 + inPos] & 262143) >>> 4 & 255);
         out[6 + outPos] = (byte)(((in[2 + inPos] & 262143) >>> 12 | (in[3 + inPos] & 262143) << 6) & 255);
         out[7 + outPos] = (byte)((in[3 + inPos] & 262143) >>> 2 & 255);
         out[8 + outPos] = (byte)((in[3 + inPos] & 262143) >>> 10 & 255);
         out[9 + outPos] = (byte)(in[4 + inPos] & 262143 & 255);
         out[10 + outPos] = (byte)((in[4 + inPos] & 262143) >>> 8 & 255);
         out[11 + outPos] = (byte)(((in[4 + inPos] & 262143) >>> 16 | (in[5 + inPos] & 262143) << 2) & 255);
         out[12 + outPos] = (byte)((in[5 + inPos] & 262143) >>> 6 & 255);
         out[13 + outPos] = (byte)(((in[5 + inPos] & 262143) >>> 14 | (in[6 + inPos] & 262143) << 4) & 255);
         out[14 + outPos] = (byte)((in[6 + inPos] & 262143) >>> 4 & 255);
         out[15 + outPos] = (byte)(((in[6 + inPos] & 262143) >>> 12 | (in[7 + inPos] & 262143) << 6) & 255);
         out[16 + outPos] = (byte)((in[7 + inPos] & 262143) >>> 2 & 255);
         out[17 + outPos] = (byte)((in[7 + inPos] & 262143) >>> 10 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 262143 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 262143) >>> 8 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 262143) >>> 16 | (in[1 + inPos] & 262143) << 2) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 262143) >>> 6 & 255);
         out[4 + outPos] = (byte)(((in[1 + inPos] & 262143) >>> 14 | (in[2 + inPos] & 262143) << 4) & 255);
         out[5 + outPos] = (byte)((in[2 + inPos] & 262143) >>> 4 & 255);
         out[6 + outPos] = (byte)(((in[2 + inPos] & 262143) >>> 12 | (in[3 + inPos] & 262143) << 6) & 255);
         out[7 + outPos] = (byte)((in[3 + inPos] & 262143) >>> 2 & 255);
         out[8 + outPos] = (byte)((in[3 + inPos] & 262143) >>> 10 & 255);
         out[9 + outPos] = (byte)(in[4 + inPos] & 262143 & 255);
         out[10 + outPos] = (byte)((in[4 + inPos] & 262143) >>> 8 & 255);
         out[11 + outPos] = (byte)(((in[4 + inPos] & 262143) >>> 16 | (in[5 + inPos] & 262143) << 2) & 255);
         out[12 + outPos] = (byte)((in[5 + inPos] & 262143) >>> 6 & 255);
         out[13 + outPos] = (byte)(((in[5 + inPos] & 262143) >>> 14 | (in[6 + inPos] & 262143) << 4) & 255);
         out[14 + outPos] = (byte)((in[6 + inPos] & 262143) >>> 4 & 255);
         out[15 + outPos] = (byte)(((in[6 + inPos] & 262143) >>> 12 | (in[7 + inPos] & 262143) << 6) & 255);
         out[16 + outPos] = (byte)((in[7 + inPos] & 262143) >>> 2 & 255);
         out[17 + outPos] = (byte)((in[7 + inPos] & 262143) >>> 10 & 255);
         out[18 + outPos] = (byte)(in[8 + inPos] & 262143 & 255);
         out[19 + outPos] = (byte)((in[8 + inPos] & 262143) >>> 8 & 255);
         out[20 + outPos] = (byte)(((in[8 + inPos] & 262143) >>> 16 | (in[9 + inPos] & 262143) << 2) & 255);
         out[21 + outPos] = (byte)((in[9 + inPos] & 262143) >>> 6 & 255);
         out[22 + outPos] = (byte)(((in[9 + inPos] & 262143) >>> 14 | (in[10 + inPos] & 262143) << 4) & 255);
         out[23 + outPos] = (byte)((in[10 + inPos] & 262143) >>> 4 & 255);
         out[24 + outPos] = (byte)(((in[10 + inPos] & 262143) >>> 12 | (in[11 + inPos] & 262143) << 6) & 255);
         out[25 + outPos] = (byte)((in[11 + inPos] & 262143) >>> 2 & 255);
         out[26 + outPos] = (byte)((in[11 + inPos] & 262143) >>> 10 & 255);
         out[27 + outPos] = (byte)(in[12 + inPos] & 262143 & 255);
         out[28 + outPos] = (byte)((in[12 + inPos] & 262143) >>> 8 & 255);
         out[29 + outPos] = (byte)(((in[12 + inPos] & 262143) >>> 16 | (in[13 + inPos] & 262143) << 2) & 255);
         out[30 + outPos] = (byte)((in[13 + inPos] & 262143) >>> 6 & 255);
         out[31 + outPos] = (byte)(((in[13 + inPos] & 262143) >>> 14 | (in[14 + inPos] & 262143) << 4) & 255);
         out[32 + outPos] = (byte)((in[14 + inPos] & 262143) >>> 4 & 255);
         out[33 + outPos] = (byte)(((in[14 + inPos] & 262143) >>> 12 | (in[15 + inPos] & 262143) << 6) & 255);
         out[34 + outPos] = (byte)((in[15 + inPos] & 262143) >>> 2 & 255);
         out[35 + outPos] = (byte)((in[15 + inPos] & 262143) >>> 10 & 255);
         out[36 + outPos] = (byte)(in[16 + inPos] & 262143 & 255);
         out[37 + outPos] = (byte)((in[16 + inPos] & 262143) >>> 8 & 255);
         out[38 + outPos] = (byte)(((in[16 + inPos] & 262143) >>> 16 | (in[17 + inPos] & 262143) << 2) & 255);
         out[39 + outPos] = (byte)((in[17 + inPos] & 262143) >>> 6 & 255);
         out[40 + outPos] = (byte)(((in[17 + inPos] & 262143) >>> 14 | (in[18 + inPos] & 262143) << 4) & 255);
         out[41 + outPos] = (byte)((in[18 + inPos] & 262143) >>> 4 & 255);
         out[42 + outPos] = (byte)(((in[18 + inPos] & 262143) >>> 12 | (in[19 + inPos] & 262143) << 6) & 255);
         out[43 + outPos] = (byte)((in[19 + inPos] & 262143) >>> 2 & 255);
         out[44 + outPos] = (byte)((in[19 + inPos] & 262143) >>> 10 & 255);
         out[45 + outPos] = (byte)(in[20 + inPos] & 262143 & 255);
         out[46 + outPos] = (byte)((in[20 + inPos] & 262143) >>> 8 & 255);
         out[47 + outPos] = (byte)(((in[20 + inPos] & 262143) >>> 16 | (in[21 + inPos] & 262143) << 2) & 255);
         out[48 + outPos] = (byte)((in[21 + inPos] & 262143) >>> 6 & 255);
         out[49 + outPos] = (byte)(((in[21 + inPos] & 262143) >>> 14 | (in[22 + inPos] & 262143) << 4) & 255);
         out[50 + outPos] = (byte)((in[22 + inPos] & 262143) >>> 4 & 255);
         out[51 + outPos] = (byte)(((in[22 + inPos] & 262143) >>> 12 | (in[23 + inPos] & 262143) << 6) & 255);
         out[52 + outPos] = (byte)((in[23 + inPos] & 262143) >>> 2 & 255);
         out[53 + outPos] = (byte)((in[23 + inPos] & 262143) >>> 10 & 255);
         out[54 + outPos] = (byte)(in[24 + inPos] & 262143 & 255);
         out[55 + outPos] = (byte)((in[24 + inPos] & 262143) >>> 8 & 255);
         out[56 + outPos] = (byte)(((in[24 + inPos] & 262143) >>> 16 | (in[25 + inPos] & 262143) << 2) & 255);
         out[57 + outPos] = (byte)((in[25 + inPos] & 262143) >>> 6 & 255);
         out[58 + outPos] = (byte)(((in[25 + inPos] & 262143) >>> 14 | (in[26 + inPos] & 262143) << 4) & 255);
         out[59 + outPos] = (byte)((in[26 + inPos] & 262143) >>> 4 & 255);
         out[60 + outPos] = (byte)(((in[26 + inPos] & 262143) >>> 12 | (in[27 + inPos] & 262143) << 6) & 255);
         out[61 + outPos] = (byte)((in[27 + inPos] & 262143) >>> 2 & 255);
         out[62 + outPos] = (byte)((in[27 + inPos] & 262143) >>> 10 & 255);
         out[63 + outPos] = (byte)(in[28 + inPos] & 262143 & 255);
         out[64 + outPos] = (byte)((in[28 + inPos] & 262143) >>> 8 & 255);
         out[65 + outPos] = (byte)(((in[28 + inPos] & 262143) >>> 16 | (in[29 + inPos] & 262143) << 2) & 255);
         out[66 + outPos] = (byte)((in[29 + inPos] & 262143) >>> 6 & 255);
         out[67 + outPos] = (byte)(((in[29 + inPos] & 262143) >>> 14 | (in[30 + inPos] & 262143) << 4) & 255);
         out[68 + outPos] = (byte)((in[30 + inPos] & 262143) >>> 4 & 255);
         out[69 + outPos] = (byte)(((in[30 + inPos] & 262143) >>> 12 | (in[31 + inPos] & 262143) << 6) & 255);
         out[70 + outPos] = (byte)((in[31 + inPos] & 262143) >>> 2 & 255);
         out[71 + outPos] = (byte)((in[31 + inPos] & 262143) >>> 10 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 262143;
         out[1 + outPos] = in[2 + inPos] >> 2 & 63 | in[3 + inPos] << 6 & 16383 | in[4 + inPos] << 14 & 262143;
         out[2 + outPos] = in[4 + inPos] >> 4 & 15 | in[5 + inPos] << 4 & 4095 | in[6 + inPos] << 12 & 262143;
         out[3 + outPos] = in[6 + inPos] >> 6 & 3 | in[7 + inPos] << 2 & 1023 | in[8 + inPos] << 10 & 262143;
         out[4 + outPos] = in[9 + inPos] & 255 | in[10 + inPos] << 8 & '\uffff' | in[11 + inPos] << 16 & 262143;
         out[5 + outPos] = in[11 + inPos] >> 2 & 63 | in[12 + inPos] << 6 & 16383 | in[13 + inPos] << 14 & 262143;
         out[6 + outPos] = in[13 + inPos] >> 4 & 15 | in[14 + inPos] << 4 & 4095 | in[15 + inPos] << 12 & 262143;
         out[7 + outPos] = in[15 + inPos] >> 6 & 3 | in[16 + inPos] << 2 & 1023 | in[17 + inPos] << 10 & 262143;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 262143;
         out[1 + outPos] = in.get(2 + inPos) >> 2 & 63 | in.get(3 + inPos) << 6 & 16383 | in.get(4 + inPos) << 14 & 262143;
         out[2 + outPos] = in.get(4 + inPos) >> 4 & 15 | in.get(5 + inPos) << 4 & 4095 | in.get(6 + inPos) << 12 & 262143;
         out[3 + outPos] = in.get(6 + inPos) >> 6 & 3 | in.get(7 + inPos) << 2 & 1023 | in.get(8 + inPos) << 10 & 262143;
         out[4 + outPos] = in.get(9 + inPos) & 255 | in.get(10 + inPos) << 8 & '\uffff' | in.get(11 + inPos) << 16 & 262143;
         out[5 + outPos] = in.get(11 + inPos) >> 2 & 63 | in.get(12 + inPos) << 6 & 16383 | in.get(13 + inPos) << 14 & 262143;
         out[6 + outPos] = in.get(13 + inPos) >> 4 & 15 | in.get(14 + inPos) << 4 & 4095 | in.get(15 + inPos) << 12 & 262143;
         out[7 + outPos] = in.get(15 + inPos) >> 6 & 3 | in.get(16 + inPos) << 2 & 1023 | in.get(17 + inPos) << 10 & 262143;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 262143;
         out[1 + outPos] = in[2 + inPos] >> 2 & 63 | in[3 + inPos] << 6 & 16383 | in[4 + inPos] << 14 & 262143;
         out[2 + outPos] = in[4 + inPos] >> 4 & 15 | in[5 + inPos] << 4 & 4095 | in[6 + inPos] << 12 & 262143;
         out[3 + outPos] = in[6 + inPos] >> 6 & 3 | in[7 + inPos] << 2 & 1023 | in[8 + inPos] << 10 & 262143;
         out[4 + outPos] = in[9 + inPos] & 255 | in[10 + inPos] << 8 & '\uffff' | in[11 + inPos] << 16 & 262143;
         out[5 + outPos] = in[11 + inPos] >> 2 & 63 | in[12 + inPos] << 6 & 16383 | in[13 + inPos] << 14 & 262143;
         out[6 + outPos] = in[13 + inPos] >> 4 & 15 | in[14 + inPos] << 4 & 4095 | in[15 + inPos] << 12 & 262143;
         out[7 + outPos] = in[15 + inPos] >> 6 & 3 | in[16 + inPos] << 2 & 1023 | in[17 + inPos] << 10 & 262143;
         out[8 + outPos] = in[18 + inPos] & 255 | in[19 + inPos] << 8 & '\uffff' | in[20 + inPos] << 16 & 262143;
         out[9 + outPos] = in[20 + inPos] >> 2 & 63 | in[21 + inPos] << 6 & 16383 | in[22 + inPos] << 14 & 262143;
         out[10 + outPos] = in[22 + inPos] >> 4 & 15 | in[23 + inPos] << 4 & 4095 | in[24 + inPos] << 12 & 262143;
         out[11 + outPos] = in[24 + inPos] >> 6 & 3 | in[25 + inPos] << 2 & 1023 | in[26 + inPos] << 10 & 262143;
         out[12 + outPos] = in[27 + inPos] & 255 | in[28 + inPos] << 8 & '\uffff' | in[29 + inPos] << 16 & 262143;
         out[13 + outPos] = in[29 + inPos] >> 2 & 63 | in[30 + inPos] << 6 & 16383 | in[31 + inPos] << 14 & 262143;
         out[14 + outPos] = in[31 + inPos] >> 4 & 15 | in[32 + inPos] << 4 & 4095 | in[33 + inPos] << 12 & 262143;
         out[15 + outPos] = in[33 + inPos] >> 6 & 3 | in[34 + inPos] << 2 & 1023 | in[35 + inPos] << 10 & 262143;
         out[16 + outPos] = in[36 + inPos] & 255 | in[37 + inPos] << 8 & '\uffff' | in[38 + inPos] << 16 & 262143;
         out[17 + outPos] = in[38 + inPos] >> 2 & 63 | in[39 + inPos] << 6 & 16383 | in[40 + inPos] << 14 & 262143;
         out[18 + outPos] = in[40 + inPos] >> 4 & 15 | in[41 + inPos] << 4 & 4095 | in[42 + inPos] << 12 & 262143;
         out[19 + outPos] = in[42 + inPos] >> 6 & 3 | in[43 + inPos] << 2 & 1023 | in[44 + inPos] << 10 & 262143;
         out[20 + outPos] = in[45 + inPos] & 255 | in[46 + inPos] << 8 & '\uffff' | in[47 + inPos] << 16 & 262143;
         out[21 + outPos] = in[47 + inPos] >> 2 & 63 | in[48 + inPos] << 6 & 16383 | in[49 + inPos] << 14 & 262143;
         out[22 + outPos] = in[49 + inPos] >> 4 & 15 | in[50 + inPos] << 4 & 4095 | in[51 + inPos] << 12 & 262143;
         out[23 + outPos] = in[51 + inPos] >> 6 & 3 | in[52 + inPos] << 2 & 1023 | in[53 + inPos] << 10 & 262143;
         out[24 + outPos] = in[54 + inPos] & 255 | in[55 + inPos] << 8 & '\uffff' | in[56 + inPos] << 16 & 262143;
         out[25 + outPos] = in[56 + inPos] >> 2 & 63 | in[57 + inPos] << 6 & 16383 | in[58 + inPos] << 14 & 262143;
         out[26 + outPos] = in[58 + inPos] >> 4 & 15 | in[59 + inPos] << 4 & 4095 | in[60 + inPos] << 12 & 262143;
         out[27 + outPos] = in[60 + inPos] >> 6 & 3 | in[61 + inPos] << 2 & 1023 | in[62 + inPos] << 10 & 262143;
         out[28 + outPos] = in[63 + inPos] & 255 | in[64 + inPos] << 8 & '\uffff' | in[65 + inPos] << 16 & 262143;
         out[29 + outPos] = in[65 + inPos] >> 2 & 63 | in[66 + inPos] << 6 & 16383 | in[67 + inPos] << 14 & 262143;
         out[30 + outPos] = in[67 + inPos] >> 4 & 15 | in[68 + inPos] << 4 & 4095 | in[69 + inPos] << 12 & 262143;
         out[31 + outPos] = in[69 + inPos] >> 6 & 3 | in[70 + inPos] << 2 & 1023 | in[71 + inPos] << 10 & 262143;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 262143;
         out[1 + outPos] = in.get(2 + inPos) >> 2 & 63 | in.get(3 + inPos) << 6 & 16383 | in.get(4 + inPos) << 14 & 262143;
         out[2 + outPos] = in.get(4 + inPos) >> 4 & 15 | in.get(5 + inPos) << 4 & 4095 | in.get(6 + inPos) << 12 & 262143;
         out[3 + outPos] = in.get(6 + inPos) >> 6 & 3 | in.get(7 + inPos) << 2 & 1023 | in.get(8 + inPos) << 10 & 262143;
         out[4 + outPos] = in.get(9 + inPos) & 255 | in.get(10 + inPos) << 8 & '\uffff' | in.get(11 + inPos) << 16 & 262143;
         out[5 + outPos] = in.get(11 + inPos) >> 2 & 63 | in.get(12 + inPos) << 6 & 16383 | in.get(13 + inPos) << 14 & 262143;
         out[6 + outPos] = in.get(13 + inPos) >> 4 & 15 | in.get(14 + inPos) << 4 & 4095 | in.get(15 + inPos) << 12 & 262143;
         out[7 + outPos] = in.get(15 + inPos) >> 6 & 3 | in.get(16 + inPos) << 2 & 1023 | in.get(17 + inPos) << 10 & 262143;
         out[8 + outPos] = in.get(18 + inPos) & 255 | in.get(19 + inPos) << 8 & '\uffff' | in.get(20 + inPos) << 16 & 262143;
         out[9 + outPos] = in.get(20 + inPos) >> 2 & 63 | in.get(21 + inPos) << 6 & 16383 | in.get(22 + inPos) << 14 & 262143;
         out[10 + outPos] = in.get(22 + inPos) >> 4 & 15 | in.get(23 + inPos) << 4 & 4095 | in.get(24 + inPos) << 12 & 262143;
         out[11 + outPos] = in.get(24 + inPos) >> 6 & 3 | in.get(25 + inPos) << 2 & 1023 | in.get(26 + inPos) << 10 & 262143;
         out[12 + outPos] = in.get(27 + inPos) & 255 | in.get(28 + inPos) << 8 & '\uffff' | in.get(29 + inPos) << 16 & 262143;
         out[13 + outPos] = in.get(29 + inPos) >> 2 & 63 | in.get(30 + inPos) << 6 & 16383 | in.get(31 + inPos) << 14 & 262143;
         out[14 + outPos] = in.get(31 + inPos) >> 4 & 15 | in.get(32 + inPos) << 4 & 4095 | in.get(33 + inPos) << 12 & 262143;
         out[15 + outPos] = in.get(33 + inPos) >> 6 & 3 | in.get(34 + inPos) << 2 & 1023 | in.get(35 + inPos) << 10 & 262143;
         out[16 + outPos] = in.get(36 + inPos) & 255 | in.get(37 + inPos) << 8 & '\uffff' | in.get(38 + inPos) << 16 & 262143;
         out[17 + outPos] = in.get(38 + inPos) >> 2 & 63 | in.get(39 + inPos) << 6 & 16383 | in.get(40 + inPos) << 14 & 262143;
         out[18 + outPos] = in.get(40 + inPos) >> 4 & 15 | in.get(41 + inPos) << 4 & 4095 | in.get(42 + inPos) << 12 & 262143;
         out[19 + outPos] = in.get(42 + inPos) >> 6 & 3 | in.get(43 + inPos) << 2 & 1023 | in.get(44 + inPos) << 10 & 262143;
         out[20 + outPos] = in.get(45 + inPos) & 255 | in.get(46 + inPos) << 8 & '\uffff' | in.get(47 + inPos) << 16 & 262143;
         out[21 + outPos] = in.get(47 + inPos) >> 2 & 63 | in.get(48 + inPos) << 6 & 16383 | in.get(49 + inPos) << 14 & 262143;
         out[22 + outPos] = in.get(49 + inPos) >> 4 & 15 | in.get(50 + inPos) << 4 & 4095 | in.get(51 + inPos) << 12 & 262143;
         out[23 + outPos] = in.get(51 + inPos) >> 6 & 3 | in.get(52 + inPos) << 2 & 1023 | in.get(53 + inPos) << 10 & 262143;
         out[24 + outPos] = in.get(54 + inPos) & 255 | in.get(55 + inPos) << 8 & '\uffff' | in.get(56 + inPos) << 16 & 262143;
         out[25 + outPos] = in.get(56 + inPos) >> 2 & 63 | in.get(57 + inPos) << 6 & 16383 | in.get(58 + inPos) << 14 & 262143;
         out[26 + outPos] = in.get(58 + inPos) >> 4 & 15 | in.get(59 + inPos) << 4 & 4095 | in.get(60 + inPos) << 12 & 262143;
         out[27 + outPos] = in.get(60 + inPos) >> 6 & 3 | in.get(61 + inPos) << 2 & 1023 | in.get(62 + inPos) << 10 & 262143;
         out[28 + outPos] = in.get(63 + inPos) & 255 | in.get(64 + inPos) << 8 & '\uffff' | in.get(65 + inPos) << 16 & 262143;
         out[29 + outPos] = in.get(65 + inPos) >> 2 & 63 | in.get(66 + inPos) << 6 & 16383 | in.get(67 + inPos) << 14 & 262143;
         out[30 + outPos] = in.get(67 + inPos) >> 4 & 15 | in.get(68 + inPos) << 4 & 4095 | in.get(69 + inPos) << 12 & 262143;
         out[31 + outPos] = in.get(69 + inPos) >> 6 & 3 | in.get(70 + inPos) << 2 & 1023 | in.get(71 + inPos) << 10 & 262143;
      }
   }

   private static final class Packer19 extends BytePacker {
      private Packer19() {
         super(19);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 524287 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 524287) >>> 8 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 524287) >>> 16 | (in[1 + inPos] & 524287) << 3) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 524287) >>> 5 & 255);
         out[4 + outPos] = (byte)(((in[1 + inPos] & 524287) >>> 13 | (in[2 + inPos] & 524287) << 6) & 255);
         out[5 + outPos] = (byte)((in[2 + inPos] & 524287) >>> 2 & 255);
         out[6 + outPos] = (byte)((in[2 + inPos] & 524287) >>> 10 & 255);
         out[7 + outPos] = (byte)(((in[2 + inPos] & 524287) >>> 18 | (in[3 + inPos] & 524287) << 1) & 255);
         out[8 + outPos] = (byte)((in[3 + inPos] & 524287) >>> 7 & 255);
         out[9 + outPos] = (byte)(((in[3 + inPos] & 524287) >>> 15 | (in[4 + inPos] & 524287) << 4) & 255);
         out[10 + outPos] = (byte)((in[4 + inPos] & 524287) >>> 4 & 255);
         out[11 + outPos] = (byte)(((in[4 + inPos] & 524287) >>> 12 | (in[5 + inPos] & 524287) << 7) & 255);
         out[12 + outPos] = (byte)((in[5 + inPos] & 524287) >>> 1 & 255);
         out[13 + outPos] = (byte)((in[5 + inPos] & 524287) >>> 9 & 255);
         out[14 + outPos] = (byte)(((in[5 + inPos] & 524287) >>> 17 | (in[6 + inPos] & 524287) << 2) & 255);
         out[15 + outPos] = (byte)((in[6 + inPos] & 524287) >>> 6 & 255);
         out[16 + outPos] = (byte)(((in[6 + inPos] & 524287) >>> 14 | (in[7 + inPos] & 524287) << 5) & 255);
         out[17 + outPos] = (byte)((in[7 + inPos] & 524287) >>> 3 & 255);
         out[18 + outPos] = (byte)((in[7 + inPos] & 524287) >>> 11 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 524287 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 524287) >>> 8 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 524287) >>> 16 | (in[1 + inPos] & 524287) << 3) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 524287) >>> 5 & 255);
         out[4 + outPos] = (byte)(((in[1 + inPos] & 524287) >>> 13 | (in[2 + inPos] & 524287) << 6) & 255);
         out[5 + outPos] = (byte)((in[2 + inPos] & 524287) >>> 2 & 255);
         out[6 + outPos] = (byte)((in[2 + inPos] & 524287) >>> 10 & 255);
         out[7 + outPos] = (byte)(((in[2 + inPos] & 524287) >>> 18 | (in[3 + inPos] & 524287) << 1) & 255);
         out[8 + outPos] = (byte)((in[3 + inPos] & 524287) >>> 7 & 255);
         out[9 + outPos] = (byte)(((in[3 + inPos] & 524287) >>> 15 | (in[4 + inPos] & 524287) << 4) & 255);
         out[10 + outPos] = (byte)((in[4 + inPos] & 524287) >>> 4 & 255);
         out[11 + outPos] = (byte)(((in[4 + inPos] & 524287) >>> 12 | (in[5 + inPos] & 524287) << 7) & 255);
         out[12 + outPos] = (byte)((in[5 + inPos] & 524287) >>> 1 & 255);
         out[13 + outPos] = (byte)((in[5 + inPos] & 524287) >>> 9 & 255);
         out[14 + outPos] = (byte)(((in[5 + inPos] & 524287) >>> 17 | (in[6 + inPos] & 524287) << 2) & 255);
         out[15 + outPos] = (byte)((in[6 + inPos] & 524287) >>> 6 & 255);
         out[16 + outPos] = (byte)(((in[6 + inPos] & 524287) >>> 14 | (in[7 + inPos] & 524287) << 5) & 255);
         out[17 + outPos] = (byte)((in[7 + inPos] & 524287) >>> 3 & 255);
         out[18 + outPos] = (byte)((in[7 + inPos] & 524287) >>> 11 & 255);
         out[19 + outPos] = (byte)(in[8 + inPos] & 524287 & 255);
         out[20 + outPos] = (byte)((in[8 + inPos] & 524287) >>> 8 & 255);
         out[21 + outPos] = (byte)(((in[8 + inPos] & 524287) >>> 16 | (in[9 + inPos] & 524287) << 3) & 255);
         out[22 + outPos] = (byte)((in[9 + inPos] & 524287) >>> 5 & 255);
         out[23 + outPos] = (byte)(((in[9 + inPos] & 524287) >>> 13 | (in[10 + inPos] & 524287) << 6) & 255);
         out[24 + outPos] = (byte)((in[10 + inPos] & 524287) >>> 2 & 255);
         out[25 + outPos] = (byte)((in[10 + inPos] & 524287) >>> 10 & 255);
         out[26 + outPos] = (byte)(((in[10 + inPos] & 524287) >>> 18 | (in[11 + inPos] & 524287) << 1) & 255);
         out[27 + outPos] = (byte)((in[11 + inPos] & 524287) >>> 7 & 255);
         out[28 + outPos] = (byte)(((in[11 + inPos] & 524287) >>> 15 | (in[12 + inPos] & 524287) << 4) & 255);
         out[29 + outPos] = (byte)((in[12 + inPos] & 524287) >>> 4 & 255);
         out[30 + outPos] = (byte)(((in[12 + inPos] & 524287) >>> 12 | (in[13 + inPos] & 524287) << 7) & 255);
         out[31 + outPos] = (byte)((in[13 + inPos] & 524287) >>> 1 & 255);
         out[32 + outPos] = (byte)((in[13 + inPos] & 524287) >>> 9 & 255);
         out[33 + outPos] = (byte)(((in[13 + inPos] & 524287) >>> 17 | (in[14 + inPos] & 524287) << 2) & 255);
         out[34 + outPos] = (byte)((in[14 + inPos] & 524287) >>> 6 & 255);
         out[35 + outPos] = (byte)(((in[14 + inPos] & 524287) >>> 14 | (in[15 + inPos] & 524287) << 5) & 255);
         out[36 + outPos] = (byte)((in[15 + inPos] & 524287) >>> 3 & 255);
         out[37 + outPos] = (byte)((in[15 + inPos] & 524287) >>> 11 & 255);
         out[38 + outPos] = (byte)(in[16 + inPos] & 524287 & 255);
         out[39 + outPos] = (byte)((in[16 + inPos] & 524287) >>> 8 & 255);
         out[40 + outPos] = (byte)(((in[16 + inPos] & 524287) >>> 16 | (in[17 + inPos] & 524287) << 3) & 255);
         out[41 + outPos] = (byte)((in[17 + inPos] & 524287) >>> 5 & 255);
         out[42 + outPos] = (byte)(((in[17 + inPos] & 524287) >>> 13 | (in[18 + inPos] & 524287) << 6) & 255);
         out[43 + outPos] = (byte)((in[18 + inPos] & 524287) >>> 2 & 255);
         out[44 + outPos] = (byte)((in[18 + inPos] & 524287) >>> 10 & 255);
         out[45 + outPos] = (byte)(((in[18 + inPos] & 524287) >>> 18 | (in[19 + inPos] & 524287) << 1) & 255);
         out[46 + outPos] = (byte)((in[19 + inPos] & 524287) >>> 7 & 255);
         out[47 + outPos] = (byte)(((in[19 + inPos] & 524287) >>> 15 | (in[20 + inPos] & 524287) << 4) & 255);
         out[48 + outPos] = (byte)((in[20 + inPos] & 524287) >>> 4 & 255);
         out[49 + outPos] = (byte)(((in[20 + inPos] & 524287) >>> 12 | (in[21 + inPos] & 524287) << 7) & 255);
         out[50 + outPos] = (byte)((in[21 + inPos] & 524287) >>> 1 & 255);
         out[51 + outPos] = (byte)((in[21 + inPos] & 524287) >>> 9 & 255);
         out[52 + outPos] = (byte)(((in[21 + inPos] & 524287) >>> 17 | (in[22 + inPos] & 524287) << 2) & 255);
         out[53 + outPos] = (byte)((in[22 + inPos] & 524287) >>> 6 & 255);
         out[54 + outPos] = (byte)(((in[22 + inPos] & 524287) >>> 14 | (in[23 + inPos] & 524287) << 5) & 255);
         out[55 + outPos] = (byte)((in[23 + inPos] & 524287) >>> 3 & 255);
         out[56 + outPos] = (byte)((in[23 + inPos] & 524287) >>> 11 & 255);
         out[57 + outPos] = (byte)(in[24 + inPos] & 524287 & 255);
         out[58 + outPos] = (byte)((in[24 + inPos] & 524287) >>> 8 & 255);
         out[59 + outPos] = (byte)(((in[24 + inPos] & 524287) >>> 16 | (in[25 + inPos] & 524287) << 3) & 255);
         out[60 + outPos] = (byte)((in[25 + inPos] & 524287) >>> 5 & 255);
         out[61 + outPos] = (byte)(((in[25 + inPos] & 524287) >>> 13 | (in[26 + inPos] & 524287) << 6) & 255);
         out[62 + outPos] = (byte)((in[26 + inPos] & 524287) >>> 2 & 255);
         out[63 + outPos] = (byte)((in[26 + inPos] & 524287) >>> 10 & 255);
         out[64 + outPos] = (byte)(((in[26 + inPos] & 524287) >>> 18 | (in[27 + inPos] & 524287) << 1) & 255);
         out[65 + outPos] = (byte)((in[27 + inPos] & 524287) >>> 7 & 255);
         out[66 + outPos] = (byte)(((in[27 + inPos] & 524287) >>> 15 | (in[28 + inPos] & 524287) << 4) & 255);
         out[67 + outPos] = (byte)((in[28 + inPos] & 524287) >>> 4 & 255);
         out[68 + outPos] = (byte)(((in[28 + inPos] & 524287) >>> 12 | (in[29 + inPos] & 524287) << 7) & 255);
         out[69 + outPos] = (byte)((in[29 + inPos] & 524287) >>> 1 & 255);
         out[70 + outPos] = (byte)((in[29 + inPos] & 524287) >>> 9 & 255);
         out[71 + outPos] = (byte)(((in[29 + inPos] & 524287) >>> 17 | (in[30 + inPos] & 524287) << 2) & 255);
         out[72 + outPos] = (byte)((in[30 + inPos] & 524287) >>> 6 & 255);
         out[73 + outPos] = (byte)(((in[30 + inPos] & 524287) >>> 14 | (in[31 + inPos] & 524287) << 5) & 255);
         out[74 + outPos] = (byte)((in[31 + inPos] & 524287) >>> 3 & 255);
         out[75 + outPos] = (byte)((in[31 + inPos] & 524287) >>> 11 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 524287;
         out[1 + outPos] = in[2 + inPos] >> 3 & 31 | in[3 + inPos] << 5 & 8191 | in[4 + inPos] << 13 & 524287;
         out[2 + outPos] = in[4 + inPos] >> 6 & 3 | in[5 + inPos] << 2 & 1023 | in[6 + inPos] << 10 & 262143 | in[7 + inPos] << 18 & 524287;
         out[3 + outPos] = in[7 + inPos] >> 1 & 127 | in[8 + inPos] << 7 & 32767 | in[9 + inPos] << 15 & 524287;
         out[4 + outPos] = in[9 + inPos] >> 4 & 15 | in[10 + inPos] << 4 & 4095 | in[11 + inPos] << 12 & 524287;
         out[5 + outPos] = in[11 + inPos] >> 7 & 1 | in[12 + inPos] << 1 & 511 | in[13 + inPos] << 9 & 131071 | in[14 + inPos] << 17 & 524287;
         out[6 + outPos] = in[14 + inPos] >> 2 & 63 | in[15 + inPos] << 6 & 16383 | in[16 + inPos] << 14 & 524287;
         out[7 + outPos] = in[16 + inPos] >> 5 & 7 | in[17 + inPos] << 3 & 2047 | in[18 + inPos] << 11 & 524287;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 524287;
         out[1 + outPos] = in.get(2 + inPos) >> 3 & 31 | in.get(3 + inPos) << 5 & 8191 | in.get(4 + inPos) << 13 & 524287;
         out[2 + outPos] = in.get(4 + inPos) >> 6 & 3 | in.get(5 + inPos) << 2 & 1023 | in.get(6 + inPos) << 10 & 262143 | in.get(7 + inPos) << 18 & 524287;
         out[3 + outPos] = in.get(7 + inPos) >> 1 & 127 | in.get(8 + inPos) << 7 & 32767 | in.get(9 + inPos) << 15 & 524287;
         out[4 + outPos] = in.get(9 + inPos) >> 4 & 15 | in.get(10 + inPos) << 4 & 4095 | in.get(11 + inPos) << 12 & 524287;
         out[5 + outPos] = in.get(11 + inPos) >> 7 & 1 | in.get(12 + inPos) << 1 & 511 | in.get(13 + inPos) << 9 & 131071 | in.get(14 + inPos) << 17 & 524287;
         out[6 + outPos] = in.get(14 + inPos) >> 2 & 63 | in.get(15 + inPos) << 6 & 16383 | in.get(16 + inPos) << 14 & 524287;
         out[7 + outPos] = in.get(16 + inPos) >> 5 & 7 | in.get(17 + inPos) << 3 & 2047 | in.get(18 + inPos) << 11 & 524287;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 524287;
         out[1 + outPos] = in[2 + inPos] >> 3 & 31 | in[3 + inPos] << 5 & 8191 | in[4 + inPos] << 13 & 524287;
         out[2 + outPos] = in[4 + inPos] >> 6 & 3 | in[5 + inPos] << 2 & 1023 | in[6 + inPos] << 10 & 262143 | in[7 + inPos] << 18 & 524287;
         out[3 + outPos] = in[7 + inPos] >> 1 & 127 | in[8 + inPos] << 7 & 32767 | in[9 + inPos] << 15 & 524287;
         out[4 + outPos] = in[9 + inPos] >> 4 & 15 | in[10 + inPos] << 4 & 4095 | in[11 + inPos] << 12 & 524287;
         out[5 + outPos] = in[11 + inPos] >> 7 & 1 | in[12 + inPos] << 1 & 511 | in[13 + inPos] << 9 & 131071 | in[14 + inPos] << 17 & 524287;
         out[6 + outPos] = in[14 + inPos] >> 2 & 63 | in[15 + inPos] << 6 & 16383 | in[16 + inPos] << 14 & 524287;
         out[7 + outPos] = in[16 + inPos] >> 5 & 7 | in[17 + inPos] << 3 & 2047 | in[18 + inPos] << 11 & 524287;
         out[8 + outPos] = in[19 + inPos] & 255 | in[20 + inPos] << 8 & '\uffff' | in[21 + inPos] << 16 & 524287;
         out[9 + outPos] = in[21 + inPos] >> 3 & 31 | in[22 + inPos] << 5 & 8191 | in[23 + inPos] << 13 & 524287;
         out[10 + outPos] = in[23 + inPos] >> 6 & 3 | in[24 + inPos] << 2 & 1023 | in[25 + inPos] << 10 & 262143 | in[26 + inPos] << 18 & 524287;
         out[11 + outPos] = in[26 + inPos] >> 1 & 127 | in[27 + inPos] << 7 & 32767 | in[28 + inPos] << 15 & 524287;
         out[12 + outPos] = in[28 + inPos] >> 4 & 15 | in[29 + inPos] << 4 & 4095 | in[30 + inPos] << 12 & 524287;
         out[13 + outPos] = in[30 + inPos] >> 7 & 1 | in[31 + inPos] << 1 & 511 | in[32 + inPos] << 9 & 131071 | in[33 + inPos] << 17 & 524287;
         out[14 + outPos] = in[33 + inPos] >> 2 & 63 | in[34 + inPos] << 6 & 16383 | in[35 + inPos] << 14 & 524287;
         out[15 + outPos] = in[35 + inPos] >> 5 & 7 | in[36 + inPos] << 3 & 2047 | in[37 + inPos] << 11 & 524287;
         out[16 + outPos] = in[38 + inPos] & 255 | in[39 + inPos] << 8 & '\uffff' | in[40 + inPos] << 16 & 524287;
         out[17 + outPos] = in[40 + inPos] >> 3 & 31 | in[41 + inPos] << 5 & 8191 | in[42 + inPos] << 13 & 524287;
         out[18 + outPos] = in[42 + inPos] >> 6 & 3 | in[43 + inPos] << 2 & 1023 | in[44 + inPos] << 10 & 262143 | in[45 + inPos] << 18 & 524287;
         out[19 + outPos] = in[45 + inPos] >> 1 & 127 | in[46 + inPos] << 7 & 32767 | in[47 + inPos] << 15 & 524287;
         out[20 + outPos] = in[47 + inPos] >> 4 & 15 | in[48 + inPos] << 4 & 4095 | in[49 + inPos] << 12 & 524287;
         out[21 + outPos] = in[49 + inPos] >> 7 & 1 | in[50 + inPos] << 1 & 511 | in[51 + inPos] << 9 & 131071 | in[52 + inPos] << 17 & 524287;
         out[22 + outPos] = in[52 + inPos] >> 2 & 63 | in[53 + inPos] << 6 & 16383 | in[54 + inPos] << 14 & 524287;
         out[23 + outPos] = in[54 + inPos] >> 5 & 7 | in[55 + inPos] << 3 & 2047 | in[56 + inPos] << 11 & 524287;
         out[24 + outPos] = in[57 + inPos] & 255 | in[58 + inPos] << 8 & '\uffff' | in[59 + inPos] << 16 & 524287;
         out[25 + outPos] = in[59 + inPos] >> 3 & 31 | in[60 + inPos] << 5 & 8191 | in[61 + inPos] << 13 & 524287;
         out[26 + outPos] = in[61 + inPos] >> 6 & 3 | in[62 + inPos] << 2 & 1023 | in[63 + inPos] << 10 & 262143 | in[64 + inPos] << 18 & 524287;
         out[27 + outPos] = in[64 + inPos] >> 1 & 127 | in[65 + inPos] << 7 & 32767 | in[66 + inPos] << 15 & 524287;
         out[28 + outPos] = in[66 + inPos] >> 4 & 15 | in[67 + inPos] << 4 & 4095 | in[68 + inPos] << 12 & 524287;
         out[29 + outPos] = in[68 + inPos] >> 7 & 1 | in[69 + inPos] << 1 & 511 | in[70 + inPos] << 9 & 131071 | in[71 + inPos] << 17 & 524287;
         out[30 + outPos] = in[71 + inPos] >> 2 & 63 | in[72 + inPos] << 6 & 16383 | in[73 + inPos] << 14 & 524287;
         out[31 + outPos] = in[73 + inPos] >> 5 & 7 | in[74 + inPos] << 3 & 2047 | in[75 + inPos] << 11 & 524287;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 524287;
         out[1 + outPos] = in.get(2 + inPos) >> 3 & 31 | in.get(3 + inPos) << 5 & 8191 | in.get(4 + inPos) << 13 & 524287;
         out[2 + outPos] = in.get(4 + inPos) >> 6 & 3 | in.get(5 + inPos) << 2 & 1023 | in.get(6 + inPos) << 10 & 262143 | in.get(7 + inPos) << 18 & 524287;
         out[3 + outPos] = in.get(7 + inPos) >> 1 & 127 | in.get(8 + inPos) << 7 & 32767 | in.get(9 + inPos) << 15 & 524287;
         out[4 + outPos] = in.get(9 + inPos) >> 4 & 15 | in.get(10 + inPos) << 4 & 4095 | in.get(11 + inPos) << 12 & 524287;
         out[5 + outPos] = in.get(11 + inPos) >> 7 & 1 | in.get(12 + inPos) << 1 & 511 | in.get(13 + inPos) << 9 & 131071 | in.get(14 + inPos) << 17 & 524287;
         out[6 + outPos] = in.get(14 + inPos) >> 2 & 63 | in.get(15 + inPos) << 6 & 16383 | in.get(16 + inPos) << 14 & 524287;
         out[7 + outPos] = in.get(16 + inPos) >> 5 & 7 | in.get(17 + inPos) << 3 & 2047 | in.get(18 + inPos) << 11 & 524287;
         out[8 + outPos] = in.get(19 + inPos) & 255 | in.get(20 + inPos) << 8 & '\uffff' | in.get(21 + inPos) << 16 & 524287;
         out[9 + outPos] = in.get(21 + inPos) >> 3 & 31 | in.get(22 + inPos) << 5 & 8191 | in.get(23 + inPos) << 13 & 524287;
         out[10 + outPos] = in.get(23 + inPos) >> 6 & 3 | in.get(24 + inPos) << 2 & 1023 | in.get(25 + inPos) << 10 & 262143 | in.get(26 + inPos) << 18 & 524287;
         out[11 + outPos] = in.get(26 + inPos) >> 1 & 127 | in.get(27 + inPos) << 7 & 32767 | in.get(28 + inPos) << 15 & 524287;
         out[12 + outPos] = in.get(28 + inPos) >> 4 & 15 | in.get(29 + inPos) << 4 & 4095 | in.get(30 + inPos) << 12 & 524287;
         out[13 + outPos] = in.get(30 + inPos) >> 7 & 1 | in.get(31 + inPos) << 1 & 511 | in.get(32 + inPos) << 9 & 131071 | in.get(33 + inPos) << 17 & 524287;
         out[14 + outPos] = in.get(33 + inPos) >> 2 & 63 | in.get(34 + inPos) << 6 & 16383 | in.get(35 + inPos) << 14 & 524287;
         out[15 + outPos] = in.get(35 + inPos) >> 5 & 7 | in.get(36 + inPos) << 3 & 2047 | in.get(37 + inPos) << 11 & 524287;
         out[16 + outPos] = in.get(38 + inPos) & 255 | in.get(39 + inPos) << 8 & '\uffff' | in.get(40 + inPos) << 16 & 524287;
         out[17 + outPos] = in.get(40 + inPos) >> 3 & 31 | in.get(41 + inPos) << 5 & 8191 | in.get(42 + inPos) << 13 & 524287;
         out[18 + outPos] = in.get(42 + inPos) >> 6 & 3 | in.get(43 + inPos) << 2 & 1023 | in.get(44 + inPos) << 10 & 262143 | in.get(45 + inPos) << 18 & 524287;
         out[19 + outPos] = in.get(45 + inPos) >> 1 & 127 | in.get(46 + inPos) << 7 & 32767 | in.get(47 + inPos) << 15 & 524287;
         out[20 + outPos] = in.get(47 + inPos) >> 4 & 15 | in.get(48 + inPos) << 4 & 4095 | in.get(49 + inPos) << 12 & 524287;
         out[21 + outPos] = in.get(49 + inPos) >> 7 & 1 | in.get(50 + inPos) << 1 & 511 | in.get(51 + inPos) << 9 & 131071 | in.get(52 + inPos) << 17 & 524287;
         out[22 + outPos] = in.get(52 + inPos) >> 2 & 63 | in.get(53 + inPos) << 6 & 16383 | in.get(54 + inPos) << 14 & 524287;
         out[23 + outPos] = in.get(54 + inPos) >> 5 & 7 | in.get(55 + inPos) << 3 & 2047 | in.get(56 + inPos) << 11 & 524287;
         out[24 + outPos] = in.get(57 + inPos) & 255 | in.get(58 + inPos) << 8 & '\uffff' | in.get(59 + inPos) << 16 & 524287;
         out[25 + outPos] = in.get(59 + inPos) >> 3 & 31 | in.get(60 + inPos) << 5 & 8191 | in.get(61 + inPos) << 13 & 524287;
         out[26 + outPos] = in.get(61 + inPos) >> 6 & 3 | in.get(62 + inPos) << 2 & 1023 | in.get(63 + inPos) << 10 & 262143 | in.get(64 + inPos) << 18 & 524287;
         out[27 + outPos] = in.get(64 + inPos) >> 1 & 127 | in.get(65 + inPos) << 7 & 32767 | in.get(66 + inPos) << 15 & 524287;
         out[28 + outPos] = in.get(66 + inPos) >> 4 & 15 | in.get(67 + inPos) << 4 & 4095 | in.get(68 + inPos) << 12 & 524287;
         out[29 + outPos] = in.get(68 + inPos) >> 7 & 1 | in.get(69 + inPos) << 1 & 511 | in.get(70 + inPos) << 9 & 131071 | in.get(71 + inPos) << 17 & 524287;
         out[30 + outPos] = in.get(71 + inPos) >> 2 & 63 | in.get(72 + inPos) << 6 & 16383 | in.get(73 + inPos) << 14 & 524287;
         out[31 + outPos] = in.get(73 + inPos) >> 5 & 7 | in.get(74 + inPos) << 3 & 2047 | in.get(75 + inPos) << 11 & 524287;
      }
   }

   private static final class Packer20 extends BytePacker {
      private Packer20() {
         super(20);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 1048575 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 1048575) >>> 8 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 1048575) >>> 16 | (in[1 + inPos] & 1048575) << 4) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 1048575) >>> 4 & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 1048575) >>> 12 & 255);
         out[5 + outPos] = (byte)(in[2 + inPos] & 1048575 & 255);
         out[6 + outPos] = (byte)((in[2 + inPos] & 1048575) >>> 8 & 255);
         out[7 + outPos] = (byte)(((in[2 + inPos] & 1048575) >>> 16 | (in[3 + inPos] & 1048575) << 4) & 255);
         out[8 + outPos] = (byte)((in[3 + inPos] & 1048575) >>> 4 & 255);
         out[9 + outPos] = (byte)((in[3 + inPos] & 1048575) >>> 12 & 255);
         out[10 + outPos] = (byte)(in[4 + inPos] & 1048575 & 255);
         out[11 + outPos] = (byte)((in[4 + inPos] & 1048575) >>> 8 & 255);
         out[12 + outPos] = (byte)(((in[4 + inPos] & 1048575) >>> 16 | (in[5 + inPos] & 1048575) << 4) & 255);
         out[13 + outPos] = (byte)((in[5 + inPos] & 1048575) >>> 4 & 255);
         out[14 + outPos] = (byte)((in[5 + inPos] & 1048575) >>> 12 & 255);
         out[15 + outPos] = (byte)(in[6 + inPos] & 1048575 & 255);
         out[16 + outPos] = (byte)((in[6 + inPos] & 1048575) >>> 8 & 255);
         out[17 + outPos] = (byte)(((in[6 + inPos] & 1048575) >>> 16 | (in[7 + inPos] & 1048575) << 4) & 255);
         out[18 + outPos] = (byte)((in[7 + inPos] & 1048575) >>> 4 & 255);
         out[19 + outPos] = (byte)((in[7 + inPos] & 1048575) >>> 12 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 1048575 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 1048575) >>> 8 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 1048575) >>> 16 | (in[1 + inPos] & 1048575) << 4) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 1048575) >>> 4 & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 1048575) >>> 12 & 255);
         out[5 + outPos] = (byte)(in[2 + inPos] & 1048575 & 255);
         out[6 + outPos] = (byte)((in[2 + inPos] & 1048575) >>> 8 & 255);
         out[7 + outPos] = (byte)(((in[2 + inPos] & 1048575) >>> 16 | (in[3 + inPos] & 1048575) << 4) & 255);
         out[8 + outPos] = (byte)((in[3 + inPos] & 1048575) >>> 4 & 255);
         out[9 + outPos] = (byte)((in[3 + inPos] & 1048575) >>> 12 & 255);
         out[10 + outPos] = (byte)(in[4 + inPos] & 1048575 & 255);
         out[11 + outPos] = (byte)((in[4 + inPos] & 1048575) >>> 8 & 255);
         out[12 + outPos] = (byte)(((in[4 + inPos] & 1048575) >>> 16 | (in[5 + inPos] & 1048575) << 4) & 255);
         out[13 + outPos] = (byte)((in[5 + inPos] & 1048575) >>> 4 & 255);
         out[14 + outPos] = (byte)((in[5 + inPos] & 1048575) >>> 12 & 255);
         out[15 + outPos] = (byte)(in[6 + inPos] & 1048575 & 255);
         out[16 + outPos] = (byte)((in[6 + inPos] & 1048575) >>> 8 & 255);
         out[17 + outPos] = (byte)(((in[6 + inPos] & 1048575) >>> 16 | (in[7 + inPos] & 1048575) << 4) & 255);
         out[18 + outPos] = (byte)((in[7 + inPos] & 1048575) >>> 4 & 255);
         out[19 + outPos] = (byte)((in[7 + inPos] & 1048575) >>> 12 & 255);
         out[20 + outPos] = (byte)(in[8 + inPos] & 1048575 & 255);
         out[21 + outPos] = (byte)((in[8 + inPos] & 1048575) >>> 8 & 255);
         out[22 + outPos] = (byte)(((in[8 + inPos] & 1048575) >>> 16 | (in[9 + inPos] & 1048575) << 4) & 255);
         out[23 + outPos] = (byte)((in[9 + inPos] & 1048575) >>> 4 & 255);
         out[24 + outPos] = (byte)((in[9 + inPos] & 1048575) >>> 12 & 255);
         out[25 + outPos] = (byte)(in[10 + inPos] & 1048575 & 255);
         out[26 + outPos] = (byte)((in[10 + inPos] & 1048575) >>> 8 & 255);
         out[27 + outPos] = (byte)(((in[10 + inPos] & 1048575) >>> 16 | (in[11 + inPos] & 1048575) << 4) & 255);
         out[28 + outPos] = (byte)((in[11 + inPos] & 1048575) >>> 4 & 255);
         out[29 + outPos] = (byte)((in[11 + inPos] & 1048575) >>> 12 & 255);
         out[30 + outPos] = (byte)(in[12 + inPos] & 1048575 & 255);
         out[31 + outPos] = (byte)((in[12 + inPos] & 1048575) >>> 8 & 255);
         out[32 + outPos] = (byte)(((in[12 + inPos] & 1048575) >>> 16 | (in[13 + inPos] & 1048575) << 4) & 255);
         out[33 + outPos] = (byte)((in[13 + inPos] & 1048575) >>> 4 & 255);
         out[34 + outPos] = (byte)((in[13 + inPos] & 1048575) >>> 12 & 255);
         out[35 + outPos] = (byte)(in[14 + inPos] & 1048575 & 255);
         out[36 + outPos] = (byte)((in[14 + inPos] & 1048575) >>> 8 & 255);
         out[37 + outPos] = (byte)(((in[14 + inPos] & 1048575) >>> 16 | (in[15 + inPos] & 1048575) << 4) & 255);
         out[38 + outPos] = (byte)((in[15 + inPos] & 1048575) >>> 4 & 255);
         out[39 + outPos] = (byte)((in[15 + inPos] & 1048575) >>> 12 & 255);
         out[40 + outPos] = (byte)(in[16 + inPos] & 1048575 & 255);
         out[41 + outPos] = (byte)((in[16 + inPos] & 1048575) >>> 8 & 255);
         out[42 + outPos] = (byte)(((in[16 + inPos] & 1048575) >>> 16 | (in[17 + inPos] & 1048575) << 4) & 255);
         out[43 + outPos] = (byte)((in[17 + inPos] & 1048575) >>> 4 & 255);
         out[44 + outPos] = (byte)((in[17 + inPos] & 1048575) >>> 12 & 255);
         out[45 + outPos] = (byte)(in[18 + inPos] & 1048575 & 255);
         out[46 + outPos] = (byte)((in[18 + inPos] & 1048575) >>> 8 & 255);
         out[47 + outPos] = (byte)(((in[18 + inPos] & 1048575) >>> 16 | (in[19 + inPos] & 1048575) << 4) & 255);
         out[48 + outPos] = (byte)((in[19 + inPos] & 1048575) >>> 4 & 255);
         out[49 + outPos] = (byte)((in[19 + inPos] & 1048575) >>> 12 & 255);
         out[50 + outPos] = (byte)(in[20 + inPos] & 1048575 & 255);
         out[51 + outPos] = (byte)((in[20 + inPos] & 1048575) >>> 8 & 255);
         out[52 + outPos] = (byte)(((in[20 + inPos] & 1048575) >>> 16 | (in[21 + inPos] & 1048575) << 4) & 255);
         out[53 + outPos] = (byte)((in[21 + inPos] & 1048575) >>> 4 & 255);
         out[54 + outPos] = (byte)((in[21 + inPos] & 1048575) >>> 12 & 255);
         out[55 + outPos] = (byte)(in[22 + inPos] & 1048575 & 255);
         out[56 + outPos] = (byte)((in[22 + inPos] & 1048575) >>> 8 & 255);
         out[57 + outPos] = (byte)(((in[22 + inPos] & 1048575) >>> 16 | (in[23 + inPos] & 1048575) << 4) & 255);
         out[58 + outPos] = (byte)((in[23 + inPos] & 1048575) >>> 4 & 255);
         out[59 + outPos] = (byte)((in[23 + inPos] & 1048575) >>> 12 & 255);
         out[60 + outPos] = (byte)(in[24 + inPos] & 1048575 & 255);
         out[61 + outPos] = (byte)((in[24 + inPos] & 1048575) >>> 8 & 255);
         out[62 + outPos] = (byte)(((in[24 + inPos] & 1048575) >>> 16 | (in[25 + inPos] & 1048575) << 4) & 255);
         out[63 + outPos] = (byte)((in[25 + inPos] & 1048575) >>> 4 & 255);
         out[64 + outPos] = (byte)((in[25 + inPos] & 1048575) >>> 12 & 255);
         out[65 + outPos] = (byte)(in[26 + inPos] & 1048575 & 255);
         out[66 + outPos] = (byte)((in[26 + inPos] & 1048575) >>> 8 & 255);
         out[67 + outPos] = (byte)(((in[26 + inPos] & 1048575) >>> 16 | (in[27 + inPos] & 1048575) << 4) & 255);
         out[68 + outPos] = (byte)((in[27 + inPos] & 1048575) >>> 4 & 255);
         out[69 + outPos] = (byte)((in[27 + inPos] & 1048575) >>> 12 & 255);
         out[70 + outPos] = (byte)(in[28 + inPos] & 1048575 & 255);
         out[71 + outPos] = (byte)((in[28 + inPos] & 1048575) >>> 8 & 255);
         out[72 + outPos] = (byte)(((in[28 + inPos] & 1048575) >>> 16 | (in[29 + inPos] & 1048575) << 4) & 255);
         out[73 + outPos] = (byte)((in[29 + inPos] & 1048575) >>> 4 & 255);
         out[74 + outPos] = (byte)((in[29 + inPos] & 1048575) >>> 12 & 255);
         out[75 + outPos] = (byte)(in[30 + inPos] & 1048575 & 255);
         out[76 + outPos] = (byte)((in[30 + inPos] & 1048575) >>> 8 & 255);
         out[77 + outPos] = (byte)(((in[30 + inPos] & 1048575) >>> 16 | (in[31 + inPos] & 1048575) << 4) & 255);
         out[78 + outPos] = (byte)((in[31 + inPos] & 1048575) >>> 4 & 255);
         out[79 + outPos] = (byte)((in[31 + inPos] & 1048575) >>> 12 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 1048575;
         out[1 + outPos] = in[2 + inPos] >> 4 & 15 | in[3 + inPos] << 4 & 4095 | in[4 + inPos] << 12 & 1048575;
         out[2 + outPos] = in[5 + inPos] & 255 | in[6 + inPos] << 8 & '\uffff' | in[7 + inPos] << 16 & 1048575;
         out[3 + outPos] = in[7 + inPos] >> 4 & 15 | in[8 + inPos] << 4 & 4095 | in[9 + inPos] << 12 & 1048575;
         out[4 + outPos] = in[10 + inPos] & 255 | in[11 + inPos] << 8 & '\uffff' | in[12 + inPos] << 16 & 1048575;
         out[5 + outPos] = in[12 + inPos] >> 4 & 15 | in[13 + inPos] << 4 & 4095 | in[14 + inPos] << 12 & 1048575;
         out[6 + outPos] = in[15 + inPos] & 255 | in[16 + inPos] << 8 & '\uffff' | in[17 + inPos] << 16 & 1048575;
         out[7 + outPos] = in[17 + inPos] >> 4 & 15 | in[18 + inPos] << 4 & 4095 | in[19 + inPos] << 12 & 1048575;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 1048575;
         out[1 + outPos] = in.get(2 + inPos) >> 4 & 15 | in.get(3 + inPos) << 4 & 4095 | in.get(4 + inPos) << 12 & 1048575;
         out[2 + outPos] = in.get(5 + inPos) & 255 | in.get(6 + inPos) << 8 & '\uffff' | in.get(7 + inPos) << 16 & 1048575;
         out[3 + outPos] = in.get(7 + inPos) >> 4 & 15 | in.get(8 + inPos) << 4 & 4095 | in.get(9 + inPos) << 12 & 1048575;
         out[4 + outPos] = in.get(10 + inPos) & 255 | in.get(11 + inPos) << 8 & '\uffff' | in.get(12 + inPos) << 16 & 1048575;
         out[5 + outPos] = in.get(12 + inPos) >> 4 & 15 | in.get(13 + inPos) << 4 & 4095 | in.get(14 + inPos) << 12 & 1048575;
         out[6 + outPos] = in.get(15 + inPos) & 255 | in.get(16 + inPos) << 8 & '\uffff' | in.get(17 + inPos) << 16 & 1048575;
         out[7 + outPos] = in.get(17 + inPos) >> 4 & 15 | in.get(18 + inPos) << 4 & 4095 | in.get(19 + inPos) << 12 & 1048575;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 1048575;
         out[1 + outPos] = in[2 + inPos] >> 4 & 15 | in[3 + inPos] << 4 & 4095 | in[4 + inPos] << 12 & 1048575;
         out[2 + outPos] = in[5 + inPos] & 255 | in[6 + inPos] << 8 & '\uffff' | in[7 + inPos] << 16 & 1048575;
         out[3 + outPos] = in[7 + inPos] >> 4 & 15 | in[8 + inPos] << 4 & 4095 | in[9 + inPos] << 12 & 1048575;
         out[4 + outPos] = in[10 + inPos] & 255 | in[11 + inPos] << 8 & '\uffff' | in[12 + inPos] << 16 & 1048575;
         out[5 + outPos] = in[12 + inPos] >> 4 & 15 | in[13 + inPos] << 4 & 4095 | in[14 + inPos] << 12 & 1048575;
         out[6 + outPos] = in[15 + inPos] & 255 | in[16 + inPos] << 8 & '\uffff' | in[17 + inPos] << 16 & 1048575;
         out[7 + outPos] = in[17 + inPos] >> 4 & 15 | in[18 + inPos] << 4 & 4095 | in[19 + inPos] << 12 & 1048575;
         out[8 + outPos] = in[20 + inPos] & 255 | in[21 + inPos] << 8 & '\uffff' | in[22 + inPos] << 16 & 1048575;
         out[9 + outPos] = in[22 + inPos] >> 4 & 15 | in[23 + inPos] << 4 & 4095 | in[24 + inPos] << 12 & 1048575;
         out[10 + outPos] = in[25 + inPos] & 255 | in[26 + inPos] << 8 & '\uffff' | in[27 + inPos] << 16 & 1048575;
         out[11 + outPos] = in[27 + inPos] >> 4 & 15 | in[28 + inPos] << 4 & 4095 | in[29 + inPos] << 12 & 1048575;
         out[12 + outPos] = in[30 + inPos] & 255 | in[31 + inPos] << 8 & '\uffff' | in[32 + inPos] << 16 & 1048575;
         out[13 + outPos] = in[32 + inPos] >> 4 & 15 | in[33 + inPos] << 4 & 4095 | in[34 + inPos] << 12 & 1048575;
         out[14 + outPos] = in[35 + inPos] & 255 | in[36 + inPos] << 8 & '\uffff' | in[37 + inPos] << 16 & 1048575;
         out[15 + outPos] = in[37 + inPos] >> 4 & 15 | in[38 + inPos] << 4 & 4095 | in[39 + inPos] << 12 & 1048575;
         out[16 + outPos] = in[40 + inPos] & 255 | in[41 + inPos] << 8 & '\uffff' | in[42 + inPos] << 16 & 1048575;
         out[17 + outPos] = in[42 + inPos] >> 4 & 15 | in[43 + inPos] << 4 & 4095 | in[44 + inPos] << 12 & 1048575;
         out[18 + outPos] = in[45 + inPos] & 255 | in[46 + inPos] << 8 & '\uffff' | in[47 + inPos] << 16 & 1048575;
         out[19 + outPos] = in[47 + inPos] >> 4 & 15 | in[48 + inPos] << 4 & 4095 | in[49 + inPos] << 12 & 1048575;
         out[20 + outPos] = in[50 + inPos] & 255 | in[51 + inPos] << 8 & '\uffff' | in[52 + inPos] << 16 & 1048575;
         out[21 + outPos] = in[52 + inPos] >> 4 & 15 | in[53 + inPos] << 4 & 4095 | in[54 + inPos] << 12 & 1048575;
         out[22 + outPos] = in[55 + inPos] & 255 | in[56 + inPos] << 8 & '\uffff' | in[57 + inPos] << 16 & 1048575;
         out[23 + outPos] = in[57 + inPos] >> 4 & 15 | in[58 + inPos] << 4 & 4095 | in[59 + inPos] << 12 & 1048575;
         out[24 + outPos] = in[60 + inPos] & 255 | in[61 + inPos] << 8 & '\uffff' | in[62 + inPos] << 16 & 1048575;
         out[25 + outPos] = in[62 + inPos] >> 4 & 15 | in[63 + inPos] << 4 & 4095 | in[64 + inPos] << 12 & 1048575;
         out[26 + outPos] = in[65 + inPos] & 255 | in[66 + inPos] << 8 & '\uffff' | in[67 + inPos] << 16 & 1048575;
         out[27 + outPos] = in[67 + inPos] >> 4 & 15 | in[68 + inPos] << 4 & 4095 | in[69 + inPos] << 12 & 1048575;
         out[28 + outPos] = in[70 + inPos] & 255 | in[71 + inPos] << 8 & '\uffff' | in[72 + inPos] << 16 & 1048575;
         out[29 + outPos] = in[72 + inPos] >> 4 & 15 | in[73 + inPos] << 4 & 4095 | in[74 + inPos] << 12 & 1048575;
         out[30 + outPos] = in[75 + inPos] & 255 | in[76 + inPos] << 8 & '\uffff' | in[77 + inPos] << 16 & 1048575;
         out[31 + outPos] = in[77 + inPos] >> 4 & 15 | in[78 + inPos] << 4 & 4095 | in[79 + inPos] << 12 & 1048575;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 1048575;
         out[1 + outPos] = in.get(2 + inPos) >> 4 & 15 | in.get(3 + inPos) << 4 & 4095 | in.get(4 + inPos) << 12 & 1048575;
         out[2 + outPos] = in.get(5 + inPos) & 255 | in.get(6 + inPos) << 8 & '\uffff' | in.get(7 + inPos) << 16 & 1048575;
         out[3 + outPos] = in.get(7 + inPos) >> 4 & 15 | in.get(8 + inPos) << 4 & 4095 | in.get(9 + inPos) << 12 & 1048575;
         out[4 + outPos] = in.get(10 + inPos) & 255 | in.get(11 + inPos) << 8 & '\uffff' | in.get(12 + inPos) << 16 & 1048575;
         out[5 + outPos] = in.get(12 + inPos) >> 4 & 15 | in.get(13 + inPos) << 4 & 4095 | in.get(14 + inPos) << 12 & 1048575;
         out[6 + outPos] = in.get(15 + inPos) & 255 | in.get(16 + inPos) << 8 & '\uffff' | in.get(17 + inPos) << 16 & 1048575;
         out[7 + outPos] = in.get(17 + inPos) >> 4 & 15 | in.get(18 + inPos) << 4 & 4095 | in.get(19 + inPos) << 12 & 1048575;
         out[8 + outPos] = in.get(20 + inPos) & 255 | in.get(21 + inPos) << 8 & '\uffff' | in.get(22 + inPos) << 16 & 1048575;
         out[9 + outPos] = in.get(22 + inPos) >> 4 & 15 | in.get(23 + inPos) << 4 & 4095 | in.get(24 + inPos) << 12 & 1048575;
         out[10 + outPos] = in.get(25 + inPos) & 255 | in.get(26 + inPos) << 8 & '\uffff' | in.get(27 + inPos) << 16 & 1048575;
         out[11 + outPos] = in.get(27 + inPos) >> 4 & 15 | in.get(28 + inPos) << 4 & 4095 | in.get(29 + inPos) << 12 & 1048575;
         out[12 + outPos] = in.get(30 + inPos) & 255 | in.get(31 + inPos) << 8 & '\uffff' | in.get(32 + inPos) << 16 & 1048575;
         out[13 + outPos] = in.get(32 + inPos) >> 4 & 15 | in.get(33 + inPos) << 4 & 4095 | in.get(34 + inPos) << 12 & 1048575;
         out[14 + outPos] = in.get(35 + inPos) & 255 | in.get(36 + inPos) << 8 & '\uffff' | in.get(37 + inPos) << 16 & 1048575;
         out[15 + outPos] = in.get(37 + inPos) >> 4 & 15 | in.get(38 + inPos) << 4 & 4095 | in.get(39 + inPos) << 12 & 1048575;
         out[16 + outPos] = in.get(40 + inPos) & 255 | in.get(41 + inPos) << 8 & '\uffff' | in.get(42 + inPos) << 16 & 1048575;
         out[17 + outPos] = in.get(42 + inPos) >> 4 & 15 | in.get(43 + inPos) << 4 & 4095 | in.get(44 + inPos) << 12 & 1048575;
         out[18 + outPos] = in.get(45 + inPos) & 255 | in.get(46 + inPos) << 8 & '\uffff' | in.get(47 + inPos) << 16 & 1048575;
         out[19 + outPos] = in.get(47 + inPos) >> 4 & 15 | in.get(48 + inPos) << 4 & 4095 | in.get(49 + inPos) << 12 & 1048575;
         out[20 + outPos] = in.get(50 + inPos) & 255 | in.get(51 + inPos) << 8 & '\uffff' | in.get(52 + inPos) << 16 & 1048575;
         out[21 + outPos] = in.get(52 + inPos) >> 4 & 15 | in.get(53 + inPos) << 4 & 4095 | in.get(54 + inPos) << 12 & 1048575;
         out[22 + outPos] = in.get(55 + inPos) & 255 | in.get(56 + inPos) << 8 & '\uffff' | in.get(57 + inPos) << 16 & 1048575;
         out[23 + outPos] = in.get(57 + inPos) >> 4 & 15 | in.get(58 + inPos) << 4 & 4095 | in.get(59 + inPos) << 12 & 1048575;
         out[24 + outPos] = in.get(60 + inPos) & 255 | in.get(61 + inPos) << 8 & '\uffff' | in.get(62 + inPos) << 16 & 1048575;
         out[25 + outPos] = in.get(62 + inPos) >> 4 & 15 | in.get(63 + inPos) << 4 & 4095 | in.get(64 + inPos) << 12 & 1048575;
         out[26 + outPos] = in.get(65 + inPos) & 255 | in.get(66 + inPos) << 8 & '\uffff' | in.get(67 + inPos) << 16 & 1048575;
         out[27 + outPos] = in.get(67 + inPos) >> 4 & 15 | in.get(68 + inPos) << 4 & 4095 | in.get(69 + inPos) << 12 & 1048575;
         out[28 + outPos] = in.get(70 + inPos) & 255 | in.get(71 + inPos) << 8 & '\uffff' | in.get(72 + inPos) << 16 & 1048575;
         out[29 + outPos] = in.get(72 + inPos) >> 4 & 15 | in.get(73 + inPos) << 4 & 4095 | in.get(74 + inPos) << 12 & 1048575;
         out[30 + outPos] = in.get(75 + inPos) & 255 | in.get(76 + inPos) << 8 & '\uffff' | in.get(77 + inPos) << 16 & 1048575;
         out[31 + outPos] = in.get(77 + inPos) >> 4 & 15 | in.get(78 + inPos) << 4 & 4095 | in.get(79 + inPos) << 12 & 1048575;
      }
   }

   private static final class Packer21 extends BytePacker {
      private Packer21() {
         super(21);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 2097151 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 2097151) >>> 8 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 2097151) >>> 16 | (in[1 + inPos] & 2097151) << 5) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 2097151) >>> 3 & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 2097151) >>> 11 & 255);
         out[5 + outPos] = (byte)(((in[1 + inPos] & 2097151) >>> 19 | (in[2 + inPos] & 2097151) << 2) & 255);
         out[6 + outPos] = (byte)((in[2 + inPos] & 2097151) >>> 6 & 255);
         out[7 + outPos] = (byte)(((in[2 + inPos] & 2097151) >>> 14 | (in[3 + inPos] & 2097151) << 7) & 255);
         out[8 + outPos] = (byte)((in[3 + inPos] & 2097151) >>> 1 & 255);
         out[9 + outPos] = (byte)((in[3 + inPos] & 2097151) >>> 9 & 255);
         out[10 + outPos] = (byte)(((in[3 + inPos] & 2097151) >>> 17 | (in[4 + inPos] & 2097151) << 4) & 255);
         out[11 + outPos] = (byte)((in[4 + inPos] & 2097151) >>> 4 & 255);
         out[12 + outPos] = (byte)((in[4 + inPos] & 2097151) >>> 12 & 255);
         out[13 + outPos] = (byte)(((in[4 + inPos] & 2097151) >>> 20 | (in[5 + inPos] & 2097151) << 1) & 255);
         out[14 + outPos] = (byte)((in[5 + inPos] & 2097151) >>> 7 & 255);
         out[15 + outPos] = (byte)(((in[5 + inPos] & 2097151) >>> 15 | (in[6 + inPos] & 2097151) << 6) & 255);
         out[16 + outPos] = (byte)((in[6 + inPos] & 2097151) >>> 2 & 255);
         out[17 + outPos] = (byte)((in[6 + inPos] & 2097151) >>> 10 & 255);
         out[18 + outPos] = (byte)(((in[6 + inPos] & 2097151) >>> 18 | (in[7 + inPos] & 2097151) << 3) & 255);
         out[19 + outPos] = (byte)((in[7 + inPos] & 2097151) >>> 5 & 255);
         out[20 + outPos] = (byte)((in[7 + inPos] & 2097151) >>> 13 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 2097151 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 2097151) >>> 8 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 2097151) >>> 16 | (in[1 + inPos] & 2097151) << 5) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 2097151) >>> 3 & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 2097151) >>> 11 & 255);
         out[5 + outPos] = (byte)(((in[1 + inPos] & 2097151) >>> 19 | (in[2 + inPos] & 2097151) << 2) & 255);
         out[6 + outPos] = (byte)((in[2 + inPos] & 2097151) >>> 6 & 255);
         out[7 + outPos] = (byte)(((in[2 + inPos] & 2097151) >>> 14 | (in[3 + inPos] & 2097151) << 7) & 255);
         out[8 + outPos] = (byte)((in[3 + inPos] & 2097151) >>> 1 & 255);
         out[9 + outPos] = (byte)((in[3 + inPos] & 2097151) >>> 9 & 255);
         out[10 + outPos] = (byte)(((in[3 + inPos] & 2097151) >>> 17 | (in[4 + inPos] & 2097151) << 4) & 255);
         out[11 + outPos] = (byte)((in[4 + inPos] & 2097151) >>> 4 & 255);
         out[12 + outPos] = (byte)((in[4 + inPos] & 2097151) >>> 12 & 255);
         out[13 + outPos] = (byte)(((in[4 + inPos] & 2097151) >>> 20 | (in[5 + inPos] & 2097151) << 1) & 255);
         out[14 + outPos] = (byte)((in[5 + inPos] & 2097151) >>> 7 & 255);
         out[15 + outPos] = (byte)(((in[5 + inPos] & 2097151) >>> 15 | (in[6 + inPos] & 2097151) << 6) & 255);
         out[16 + outPos] = (byte)((in[6 + inPos] & 2097151) >>> 2 & 255);
         out[17 + outPos] = (byte)((in[6 + inPos] & 2097151) >>> 10 & 255);
         out[18 + outPos] = (byte)(((in[6 + inPos] & 2097151) >>> 18 | (in[7 + inPos] & 2097151) << 3) & 255);
         out[19 + outPos] = (byte)((in[7 + inPos] & 2097151) >>> 5 & 255);
         out[20 + outPos] = (byte)((in[7 + inPos] & 2097151) >>> 13 & 255);
         out[21 + outPos] = (byte)(in[8 + inPos] & 2097151 & 255);
         out[22 + outPos] = (byte)((in[8 + inPos] & 2097151) >>> 8 & 255);
         out[23 + outPos] = (byte)(((in[8 + inPos] & 2097151) >>> 16 | (in[9 + inPos] & 2097151) << 5) & 255);
         out[24 + outPos] = (byte)((in[9 + inPos] & 2097151) >>> 3 & 255);
         out[25 + outPos] = (byte)((in[9 + inPos] & 2097151) >>> 11 & 255);
         out[26 + outPos] = (byte)(((in[9 + inPos] & 2097151) >>> 19 | (in[10 + inPos] & 2097151) << 2) & 255);
         out[27 + outPos] = (byte)((in[10 + inPos] & 2097151) >>> 6 & 255);
         out[28 + outPos] = (byte)(((in[10 + inPos] & 2097151) >>> 14 | (in[11 + inPos] & 2097151) << 7) & 255);
         out[29 + outPos] = (byte)((in[11 + inPos] & 2097151) >>> 1 & 255);
         out[30 + outPos] = (byte)((in[11 + inPos] & 2097151) >>> 9 & 255);
         out[31 + outPos] = (byte)(((in[11 + inPos] & 2097151) >>> 17 | (in[12 + inPos] & 2097151) << 4) & 255);
         out[32 + outPos] = (byte)((in[12 + inPos] & 2097151) >>> 4 & 255);
         out[33 + outPos] = (byte)((in[12 + inPos] & 2097151) >>> 12 & 255);
         out[34 + outPos] = (byte)(((in[12 + inPos] & 2097151) >>> 20 | (in[13 + inPos] & 2097151) << 1) & 255);
         out[35 + outPos] = (byte)((in[13 + inPos] & 2097151) >>> 7 & 255);
         out[36 + outPos] = (byte)(((in[13 + inPos] & 2097151) >>> 15 | (in[14 + inPos] & 2097151) << 6) & 255);
         out[37 + outPos] = (byte)((in[14 + inPos] & 2097151) >>> 2 & 255);
         out[38 + outPos] = (byte)((in[14 + inPos] & 2097151) >>> 10 & 255);
         out[39 + outPos] = (byte)(((in[14 + inPos] & 2097151) >>> 18 | (in[15 + inPos] & 2097151) << 3) & 255);
         out[40 + outPos] = (byte)((in[15 + inPos] & 2097151) >>> 5 & 255);
         out[41 + outPos] = (byte)((in[15 + inPos] & 2097151) >>> 13 & 255);
         out[42 + outPos] = (byte)(in[16 + inPos] & 2097151 & 255);
         out[43 + outPos] = (byte)((in[16 + inPos] & 2097151) >>> 8 & 255);
         out[44 + outPos] = (byte)(((in[16 + inPos] & 2097151) >>> 16 | (in[17 + inPos] & 2097151) << 5) & 255);
         out[45 + outPos] = (byte)((in[17 + inPos] & 2097151) >>> 3 & 255);
         out[46 + outPos] = (byte)((in[17 + inPos] & 2097151) >>> 11 & 255);
         out[47 + outPos] = (byte)(((in[17 + inPos] & 2097151) >>> 19 | (in[18 + inPos] & 2097151) << 2) & 255);
         out[48 + outPos] = (byte)((in[18 + inPos] & 2097151) >>> 6 & 255);
         out[49 + outPos] = (byte)(((in[18 + inPos] & 2097151) >>> 14 | (in[19 + inPos] & 2097151) << 7) & 255);
         out[50 + outPos] = (byte)((in[19 + inPos] & 2097151) >>> 1 & 255);
         out[51 + outPos] = (byte)((in[19 + inPos] & 2097151) >>> 9 & 255);
         out[52 + outPos] = (byte)(((in[19 + inPos] & 2097151) >>> 17 | (in[20 + inPos] & 2097151) << 4) & 255);
         out[53 + outPos] = (byte)((in[20 + inPos] & 2097151) >>> 4 & 255);
         out[54 + outPos] = (byte)((in[20 + inPos] & 2097151) >>> 12 & 255);
         out[55 + outPos] = (byte)(((in[20 + inPos] & 2097151) >>> 20 | (in[21 + inPos] & 2097151) << 1) & 255);
         out[56 + outPos] = (byte)((in[21 + inPos] & 2097151) >>> 7 & 255);
         out[57 + outPos] = (byte)(((in[21 + inPos] & 2097151) >>> 15 | (in[22 + inPos] & 2097151) << 6) & 255);
         out[58 + outPos] = (byte)((in[22 + inPos] & 2097151) >>> 2 & 255);
         out[59 + outPos] = (byte)((in[22 + inPos] & 2097151) >>> 10 & 255);
         out[60 + outPos] = (byte)(((in[22 + inPos] & 2097151) >>> 18 | (in[23 + inPos] & 2097151) << 3) & 255);
         out[61 + outPos] = (byte)((in[23 + inPos] & 2097151) >>> 5 & 255);
         out[62 + outPos] = (byte)((in[23 + inPos] & 2097151) >>> 13 & 255);
         out[63 + outPos] = (byte)(in[24 + inPos] & 2097151 & 255);
         out[64 + outPos] = (byte)((in[24 + inPos] & 2097151) >>> 8 & 255);
         out[65 + outPos] = (byte)(((in[24 + inPos] & 2097151) >>> 16 | (in[25 + inPos] & 2097151) << 5) & 255);
         out[66 + outPos] = (byte)((in[25 + inPos] & 2097151) >>> 3 & 255);
         out[67 + outPos] = (byte)((in[25 + inPos] & 2097151) >>> 11 & 255);
         out[68 + outPos] = (byte)(((in[25 + inPos] & 2097151) >>> 19 | (in[26 + inPos] & 2097151) << 2) & 255);
         out[69 + outPos] = (byte)((in[26 + inPos] & 2097151) >>> 6 & 255);
         out[70 + outPos] = (byte)(((in[26 + inPos] & 2097151) >>> 14 | (in[27 + inPos] & 2097151) << 7) & 255);
         out[71 + outPos] = (byte)((in[27 + inPos] & 2097151) >>> 1 & 255);
         out[72 + outPos] = (byte)((in[27 + inPos] & 2097151) >>> 9 & 255);
         out[73 + outPos] = (byte)(((in[27 + inPos] & 2097151) >>> 17 | (in[28 + inPos] & 2097151) << 4) & 255);
         out[74 + outPos] = (byte)((in[28 + inPos] & 2097151) >>> 4 & 255);
         out[75 + outPos] = (byte)((in[28 + inPos] & 2097151) >>> 12 & 255);
         out[76 + outPos] = (byte)(((in[28 + inPos] & 2097151) >>> 20 | (in[29 + inPos] & 2097151) << 1) & 255);
         out[77 + outPos] = (byte)((in[29 + inPos] & 2097151) >>> 7 & 255);
         out[78 + outPos] = (byte)(((in[29 + inPos] & 2097151) >>> 15 | (in[30 + inPos] & 2097151) << 6) & 255);
         out[79 + outPos] = (byte)((in[30 + inPos] & 2097151) >>> 2 & 255);
         out[80 + outPos] = (byte)((in[30 + inPos] & 2097151) >>> 10 & 255);
         out[81 + outPos] = (byte)(((in[30 + inPos] & 2097151) >>> 18 | (in[31 + inPos] & 2097151) << 3) & 255);
         out[82 + outPos] = (byte)((in[31 + inPos] & 2097151) >>> 5 & 255);
         out[83 + outPos] = (byte)((in[31 + inPos] & 2097151) >>> 13 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 2097151;
         out[1 + outPos] = in[2 + inPos] >> 5 & 7 | in[3 + inPos] << 3 & 2047 | in[4 + inPos] << 11 & 524287 | in[5 + inPos] << 19 & 2097151;
         out[2 + outPos] = in[5 + inPos] >> 2 & 63 | in[6 + inPos] << 6 & 16383 | in[7 + inPos] << 14 & 2097151;
         out[3 + outPos] = in[7 + inPos] >> 7 & 1 | in[8 + inPos] << 1 & 511 | in[9 + inPos] << 9 & 131071 | in[10 + inPos] << 17 & 2097151;
         out[4 + outPos] = in[10 + inPos] >> 4 & 15 | in[11 + inPos] << 4 & 4095 | in[12 + inPos] << 12 & 1048575 | in[13 + inPos] << 20 & 2097151;
         out[5 + outPos] = in[13 + inPos] >> 1 & 127 | in[14 + inPos] << 7 & 32767 | in[15 + inPos] << 15 & 2097151;
         out[6 + outPos] = in[15 + inPos] >> 6 & 3 | in[16 + inPos] << 2 & 1023 | in[17 + inPos] << 10 & 262143 | in[18 + inPos] << 18 & 2097151;
         out[7 + outPos] = in[18 + inPos] >> 3 & 31 | in[19 + inPos] << 5 & 8191 | in[20 + inPos] << 13 & 2097151;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 2097151;
         out[1 + outPos] = in.get(2 + inPos) >> 5 & 7 | in.get(3 + inPos) << 3 & 2047 | in.get(4 + inPos) << 11 & 524287 | in.get(5 + inPos) << 19 & 2097151;
         out[2 + outPos] = in.get(5 + inPos) >> 2 & 63 | in.get(6 + inPos) << 6 & 16383 | in.get(7 + inPos) << 14 & 2097151;
         out[3 + outPos] = in.get(7 + inPos) >> 7 & 1 | in.get(8 + inPos) << 1 & 511 | in.get(9 + inPos) << 9 & 131071 | in.get(10 + inPos) << 17 & 2097151;
         out[4 + outPos] = in.get(10 + inPos) >> 4 & 15 | in.get(11 + inPos) << 4 & 4095 | in.get(12 + inPos) << 12 & 1048575 | in.get(13 + inPos) << 20 & 2097151;
         out[5 + outPos] = in.get(13 + inPos) >> 1 & 127 | in.get(14 + inPos) << 7 & 32767 | in.get(15 + inPos) << 15 & 2097151;
         out[6 + outPos] = in.get(15 + inPos) >> 6 & 3 | in.get(16 + inPos) << 2 & 1023 | in.get(17 + inPos) << 10 & 262143 | in.get(18 + inPos) << 18 & 2097151;
         out[7 + outPos] = in.get(18 + inPos) >> 3 & 31 | in.get(19 + inPos) << 5 & 8191 | in.get(20 + inPos) << 13 & 2097151;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 2097151;
         out[1 + outPos] = in[2 + inPos] >> 5 & 7 | in[3 + inPos] << 3 & 2047 | in[4 + inPos] << 11 & 524287 | in[5 + inPos] << 19 & 2097151;
         out[2 + outPos] = in[5 + inPos] >> 2 & 63 | in[6 + inPos] << 6 & 16383 | in[7 + inPos] << 14 & 2097151;
         out[3 + outPos] = in[7 + inPos] >> 7 & 1 | in[8 + inPos] << 1 & 511 | in[9 + inPos] << 9 & 131071 | in[10 + inPos] << 17 & 2097151;
         out[4 + outPos] = in[10 + inPos] >> 4 & 15 | in[11 + inPos] << 4 & 4095 | in[12 + inPos] << 12 & 1048575 | in[13 + inPos] << 20 & 2097151;
         out[5 + outPos] = in[13 + inPos] >> 1 & 127 | in[14 + inPos] << 7 & 32767 | in[15 + inPos] << 15 & 2097151;
         out[6 + outPos] = in[15 + inPos] >> 6 & 3 | in[16 + inPos] << 2 & 1023 | in[17 + inPos] << 10 & 262143 | in[18 + inPos] << 18 & 2097151;
         out[7 + outPos] = in[18 + inPos] >> 3 & 31 | in[19 + inPos] << 5 & 8191 | in[20 + inPos] << 13 & 2097151;
         out[8 + outPos] = in[21 + inPos] & 255 | in[22 + inPos] << 8 & '\uffff' | in[23 + inPos] << 16 & 2097151;
         out[9 + outPos] = in[23 + inPos] >> 5 & 7 | in[24 + inPos] << 3 & 2047 | in[25 + inPos] << 11 & 524287 | in[26 + inPos] << 19 & 2097151;
         out[10 + outPos] = in[26 + inPos] >> 2 & 63 | in[27 + inPos] << 6 & 16383 | in[28 + inPos] << 14 & 2097151;
         out[11 + outPos] = in[28 + inPos] >> 7 & 1 | in[29 + inPos] << 1 & 511 | in[30 + inPos] << 9 & 131071 | in[31 + inPos] << 17 & 2097151;
         out[12 + outPos] = in[31 + inPos] >> 4 & 15 | in[32 + inPos] << 4 & 4095 | in[33 + inPos] << 12 & 1048575 | in[34 + inPos] << 20 & 2097151;
         out[13 + outPos] = in[34 + inPos] >> 1 & 127 | in[35 + inPos] << 7 & 32767 | in[36 + inPos] << 15 & 2097151;
         out[14 + outPos] = in[36 + inPos] >> 6 & 3 | in[37 + inPos] << 2 & 1023 | in[38 + inPos] << 10 & 262143 | in[39 + inPos] << 18 & 2097151;
         out[15 + outPos] = in[39 + inPos] >> 3 & 31 | in[40 + inPos] << 5 & 8191 | in[41 + inPos] << 13 & 2097151;
         out[16 + outPos] = in[42 + inPos] & 255 | in[43 + inPos] << 8 & '\uffff' | in[44 + inPos] << 16 & 2097151;
         out[17 + outPos] = in[44 + inPos] >> 5 & 7 | in[45 + inPos] << 3 & 2047 | in[46 + inPos] << 11 & 524287 | in[47 + inPos] << 19 & 2097151;
         out[18 + outPos] = in[47 + inPos] >> 2 & 63 | in[48 + inPos] << 6 & 16383 | in[49 + inPos] << 14 & 2097151;
         out[19 + outPos] = in[49 + inPos] >> 7 & 1 | in[50 + inPos] << 1 & 511 | in[51 + inPos] << 9 & 131071 | in[52 + inPos] << 17 & 2097151;
         out[20 + outPos] = in[52 + inPos] >> 4 & 15 | in[53 + inPos] << 4 & 4095 | in[54 + inPos] << 12 & 1048575 | in[55 + inPos] << 20 & 2097151;
         out[21 + outPos] = in[55 + inPos] >> 1 & 127 | in[56 + inPos] << 7 & 32767 | in[57 + inPos] << 15 & 2097151;
         out[22 + outPos] = in[57 + inPos] >> 6 & 3 | in[58 + inPos] << 2 & 1023 | in[59 + inPos] << 10 & 262143 | in[60 + inPos] << 18 & 2097151;
         out[23 + outPos] = in[60 + inPos] >> 3 & 31 | in[61 + inPos] << 5 & 8191 | in[62 + inPos] << 13 & 2097151;
         out[24 + outPos] = in[63 + inPos] & 255 | in[64 + inPos] << 8 & '\uffff' | in[65 + inPos] << 16 & 2097151;
         out[25 + outPos] = in[65 + inPos] >> 5 & 7 | in[66 + inPos] << 3 & 2047 | in[67 + inPos] << 11 & 524287 | in[68 + inPos] << 19 & 2097151;
         out[26 + outPos] = in[68 + inPos] >> 2 & 63 | in[69 + inPos] << 6 & 16383 | in[70 + inPos] << 14 & 2097151;
         out[27 + outPos] = in[70 + inPos] >> 7 & 1 | in[71 + inPos] << 1 & 511 | in[72 + inPos] << 9 & 131071 | in[73 + inPos] << 17 & 2097151;
         out[28 + outPos] = in[73 + inPos] >> 4 & 15 | in[74 + inPos] << 4 & 4095 | in[75 + inPos] << 12 & 1048575 | in[76 + inPos] << 20 & 2097151;
         out[29 + outPos] = in[76 + inPos] >> 1 & 127 | in[77 + inPos] << 7 & 32767 | in[78 + inPos] << 15 & 2097151;
         out[30 + outPos] = in[78 + inPos] >> 6 & 3 | in[79 + inPos] << 2 & 1023 | in[80 + inPos] << 10 & 262143 | in[81 + inPos] << 18 & 2097151;
         out[31 + outPos] = in[81 + inPos] >> 3 & 31 | in[82 + inPos] << 5 & 8191 | in[83 + inPos] << 13 & 2097151;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 2097151;
         out[1 + outPos] = in.get(2 + inPos) >> 5 & 7 | in.get(3 + inPos) << 3 & 2047 | in.get(4 + inPos) << 11 & 524287 | in.get(5 + inPos) << 19 & 2097151;
         out[2 + outPos] = in.get(5 + inPos) >> 2 & 63 | in.get(6 + inPos) << 6 & 16383 | in.get(7 + inPos) << 14 & 2097151;
         out[3 + outPos] = in.get(7 + inPos) >> 7 & 1 | in.get(8 + inPos) << 1 & 511 | in.get(9 + inPos) << 9 & 131071 | in.get(10 + inPos) << 17 & 2097151;
         out[4 + outPos] = in.get(10 + inPos) >> 4 & 15 | in.get(11 + inPos) << 4 & 4095 | in.get(12 + inPos) << 12 & 1048575 | in.get(13 + inPos) << 20 & 2097151;
         out[5 + outPos] = in.get(13 + inPos) >> 1 & 127 | in.get(14 + inPos) << 7 & 32767 | in.get(15 + inPos) << 15 & 2097151;
         out[6 + outPos] = in.get(15 + inPos) >> 6 & 3 | in.get(16 + inPos) << 2 & 1023 | in.get(17 + inPos) << 10 & 262143 | in.get(18 + inPos) << 18 & 2097151;
         out[7 + outPos] = in.get(18 + inPos) >> 3 & 31 | in.get(19 + inPos) << 5 & 8191 | in.get(20 + inPos) << 13 & 2097151;
         out[8 + outPos] = in.get(21 + inPos) & 255 | in.get(22 + inPos) << 8 & '\uffff' | in.get(23 + inPos) << 16 & 2097151;
         out[9 + outPos] = in.get(23 + inPos) >> 5 & 7 | in.get(24 + inPos) << 3 & 2047 | in.get(25 + inPos) << 11 & 524287 | in.get(26 + inPos) << 19 & 2097151;
         out[10 + outPos] = in.get(26 + inPos) >> 2 & 63 | in.get(27 + inPos) << 6 & 16383 | in.get(28 + inPos) << 14 & 2097151;
         out[11 + outPos] = in.get(28 + inPos) >> 7 & 1 | in.get(29 + inPos) << 1 & 511 | in.get(30 + inPos) << 9 & 131071 | in.get(31 + inPos) << 17 & 2097151;
         out[12 + outPos] = in.get(31 + inPos) >> 4 & 15 | in.get(32 + inPos) << 4 & 4095 | in.get(33 + inPos) << 12 & 1048575 | in.get(34 + inPos) << 20 & 2097151;
         out[13 + outPos] = in.get(34 + inPos) >> 1 & 127 | in.get(35 + inPos) << 7 & 32767 | in.get(36 + inPos) << 15 & 2097151;
         out[14 + outPos] = in.get(36 + inPos) >> 6 & 3 | in.get(37 + inPos) << 2 & 1023 | in.get(38 + inPos) << 10 & 262143 | in.get(39 + inPos) << 18 & 2097151;
         out[15 + outPos] = in.get(39 + inPos) >> 3 & 31 | in.get(40 + inPos) << 5 & 8191 | in.get(41 + inPos) << 13 & 2097151;
         out[16 + outPos] = in.get(42 + inPos) & 255 | in.get(43 + inPos) << 8 & '\uffff' | in.get(44 + inPos) << 16 & 2097151;
         out[17 + outPos] = in.get(44 + inPos) >> 5 & 7 | in.get(45 + inPos) << 3 & 2047 | in.get(46 + inPos) << 11 & 524287 | in.get(47 + inPos) << 19 & 2097151;
         out[18 + outPos] = in.get(47 + inPos) >> 2 & 63 | in.get(48 + inPos) << 6 & 16383 | in.get(49 + inPos) << 14 & 2097151;
         out[19 + outPos] = in.get(49 + inPos) >> 7 & 1 | in.get(50 + inPos) << 1 & 511 | in.get(51 + inPos) << 9 & 131071 | in.get(52 + inPos) << 17 & 2097151;
         out[20 + outPos] = in.get(52 + inPos) >> 4 & 15 | in.get(53 + inPos) << 4 & 4095 | in.get(54 + inPos) << 12 & 1048575 | in.get(55 + inPos) << 20 & 2097151;
         out[21 + outPos] = in.get(55 + inPos) >> 1 & 127 | in.get(56 + inPos) << 7 & 32767 | in.get(57 + inPos) << 15 & 2097151;
         out[22 + outPos] = in.get(57 + inPos) >> 6 & 3 | in.get(58 + inPos) << 2 & 1023 | in.get(59 + inPos) << 10 & 262143 | in.get(60 + inPos) << 18 & 2097151;
         out[23 + outPos] = in.get(60 + inPos) >> 3 & 31 | in.get(61 + inPos) << 5 & 8191 | in.get(62 + inPos) << 13 & 2097151;
         out[24 + outPos] = in.get(63 + inPos) & 255 | in.get(64 + inPos) << 8 & '\uffff' | in.get(65 + inPos) << 16 & 2097151;
         out[25 + outPos] = in.get(65 + inPos) >> 5 & 7 | in.get(66 + inPos) << 3 & 2047 | in.get(67 + inPos) << 11 & 524287 | in.get(68 + inPos) << 19 & 2097151;
         out[26 + outPos] = in.get(68 + inPos) >> 2 & 63 | in.get(69 + inPos) << 6 & 16383 | in.get(70 + inPos) << 14 & 2097151;
         out[27 + outPos] = in.get(70 + inPos) >> 7 & 1 | in.get(71 + inPos) << 1 & 511 | in.get(72 + inPos) << 9 & 131071 | in.get(73 + inPos) << 17 & 2097151;
         out[28 + outPos] = in.get(73 + inPos) >> 4 & 15 | in.get(74 + inPos) << 4 & 4095 | in.get(75 + inPos) << 12 & 1048575 | in.get(76 + inPos) << 20 & 2097151;
         out[29 + outPos] = in.get(76 + inPos) >> 1 & 127 | in.get(77 + inPos) << 7 & 32767 | in.get(78 + inPos) << 15 & 2097151;
         out[30 + outPos] = in.get(78 + inPos) >> 6 & 3 | in.get(79 + inPos) << 2 & 1023 | in.get(80 + inPos) << 10 & 262143 | in.get(81 + inPos) << 18 & 2097151;
         out[31 + outPos] = in.get(81 + inPos) >> 3 & 31 | in.get(82 + inPos) << 5 & 8191 | in.get(83 + inPos) << 13 & 2097151;
      }
   }

   private static final class Packer22 extends BytePacker {
      private Packer22() {
         super(22);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 4194303 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 4194303) >>> 8 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 4194303) >>> 16 | (in[1 + inPos] & 4194303) << 6) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 4194303) >>> 2 & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 4194303) >>> 10 & 255);
         out[5 + outPos] = (byte)(((in[1 + inPos] & 4194303) >>> 18 | (in[2 + inPos] & 4194303) << 4) & 255);
         out[6 + outPos] = (byte)((in[2 + inPos] & 4194303) >>> 4 & 255);
         out[7 + outPos] = (byte)((in[2 + inPos] & 4194303) >>> 12 & 255);
         out[8 + outPos] = (byte)(((in[2 + inPos] & 4194303) >>> 20 | (in[3 + inPos] & 4194303) << 2) & 255);
         out[9 + outPos] = (byte)((in[3 + inPos] & 4194303) >>> 6 & 255);
         out[10 + outPos] = (byte)((in[3 + inPos] & 4194303) >>> 14 & 255);
         out[11 + outPos] = (byte)(in[4 + inPos] & 4194303 & 255);
         out[12 + outPos] = (byte)((in[4 + inPos] & 4194303) >>> 8 & 255);
         out[13 + outPos] = (byte)(((in[4 + inPos] & 4194303) >>> 16 | (in[5 + inPos] & 4194303) << 6) & 255);
         out[14 + outPos] = (byte)((in[5 + inPos] & 4194303) >>> 2 & 255);
         out[15 + outPos] = (byte)((in[5 + inPos] & 4194303) >>> 10 & 255);
         out[16 + outPos] = (byte)(((in[5 + inPos] & 4194303) >>> 18 | (in[6 + inPos] & 4194303) << 4) & 255);
         out[17 + outPos] = (byte)((in[6 + inPos] & 4194303) >>> 4 & 255);
         out[18 + outPos] = (byte)((in[6 + inPos] & 4194303) >>> 12 & 255);
         out[19 + outPos] = (byte)(((in[6 + inPos] & 4194303) >>> 20 | (in[7 + inPos] & 4194303) << 2) & 255);
         out[20 + outPos] = (byte)((in[7 + inPos] & 4194303) >>> 6 & 255);
         out[21 + outPos] = (byte)((in[7 + inPos] & 4194303) >>> 14 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 4194303 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 4194303) >>> 8 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 4194303) >>> 16 | (in[1 + inPos] & 4194303) << 6) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 4194303) >>> 2 & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 4194303) >>> 10 & 255);
         out[5 + outPos] = (byte)(((in[1 + inPos] & 4194303) >>> 18 | (in[2 + inPos] & 4194303) << 4) & 255);
         out[6 + outPos] = (byte)((in[2 + inPos] & 4194303) >>> 4 & 255);
         out[7 + outPos] = (byte)((in[2 + inPos] & 4194303) >>> 12 & 255);
         out[8 + outPos] = (byte)(((in[2 + inPos] & 4194303) >>> 20 | (in[3 + inPos] & 4194303) << 2) & 255);
         out[9 + outPos] = (byte)((in[3 + inPos] & 4194303) >>> 6 & 255);
         out[10 + outPos] = (byte)((in[3 + inPos] & 4194303) >>> 14 & 255);
         out[11 + outPos] = (byte)(in[4 + inPos] & 4194303 & 255);
         out[12 + outPos] = (byte)((in[4 + inPos] & 4194303) >>> 8 & 255);
         out[13 + outPos] = (byte)(((in[4 + inPos] & 4194303) >>> 16 | (in[5 + inPos] & 4194303) << 6) & 255);
         out[14 + outPos] = (byte)((in[5 + inPos] & 4194303) >>> 2 & 255);
         out[15 + outPos] = (byte)((in[5 + inPos] & 4194303) >>> 10 & 255);
         out[16 + outPos] = (byte)(((in[5 + inPos] & 4194303) >>> 18 | (in[6 + inPos] & 4194303) << 4) & 255);
         out[17 + outPos] = (byte)((in[6 + inPos] & 4194303) >>> 4 & 255);
         out[18 + outPos] = (byte)((in[6 + inPos] & 4194303) >>> 12 & 255);
         out[19 + outPos] = (byte)(((in[6 + inPos] & 4194303) >>> 20 | (in[7 + inPos] & 4194303) << 2) & 255);
         out[20 + outPos] = (byte)((in[7 + inPos] & 4194303) >>> 6 & 255);
         out[21 + outPos] = (byte)((in[7 + inPos] & 4194303) >>> 14 & 255);
         out[22 + outPos] = (byte)(in[8 + inPos] & 4194303 & 255);
         out[23 + outPos] = (byte)((in[8 + inPos] & 4194303) >>> 8 & 255);
         out[24 + outPos] = (byte)(((in[8 + inPos] & 4194303) >>> 16 | (in[9 + inPos] & 4194303) << 6) & 255);
         out[25 + outPos] = (byte)((in[9 + inPos] & 4194303) >>> 2 & 255);
         out[26 + outPos] = (byte)((in[9 + inPos] & 4194303) >>> 10 & 255);
         out[27 + outPos] = (byte)(((in[9 + inPos] & 4194303) >>> 18 | (in[10 + inPos] & 4194303) << 4) & 255);
         out[28 + outPos] = (byte)((in[10 + inPos] & 4194303) >>> 4 & 255);
         out[29 + outPos] = (byte)((in[10 + inPos] & 4194303) >>> 12 & 255);
         out[30 + outPos] = (byte)(((in[10 + inPos] & 4194303) >>> 20 | (in[11 + inPos] & 4194303) << 2) & 255);
         out[31 + outPos] = (byte)((in[11 + inPos] & 4194303) >>> 6 & 255);
         out[32 + outPos] = (byte)((in[11 + inPos] & 4194303) >>> 14 & 255);
         out[33 + outPos] = (byte)(in[12 + inPos] & 4194303 & 255);
         out[34 + outPos] = (byte)((in[12 + inPos] & 4194303) >>> 8 & 255);
         out[35 + outPos] = (byte)(((in[12 + inPos] & 4194303) >>> 16 | (in[13 + inPos] & 4194303) << 6) & 255);
         out[36 + outPos] = (byte)((in[13 + inPos] & 4194303) >>> 2 & 255);
         out[37 + outPos] = (byte)((in[13 + inPos] & 4194303) >>> 10 & 255);
         out[38 + outPos] = (byte)(((in[13 + inPos] & 4194303) >>> 18 | (in[14 + inPos] & 4194303) << 4) & 255);
         out[39 + outPos] = (byte)((in[14 + inPos] & 4194303) >>> 4 & 255);
         out[40 + outPos] = (byte)((in[14 + inPos] & 4194303) >>> 12 & 255);
         out[41 + outPos] = (byte)(((in[14 + inPos] & 4194303) >>> 20 | (in[15 + inPos] & 4194303) << 2) & 255);
         out[42 + outPos] = (byte)((in[15 + inPos] & 4194303) >>> 6 & 255);
         out[43 + outPos] = (byte)((in[15 + inPos] & 4194303) >>> 14 & 255);
         out[44 + outPos] = (byte)(in[16 + inPos] & 4194303 & 255);
         out[45 + outPos] = (byte)((in[16 + inPos] & 4194303) >>> 8 & 255);
         out[46 + outPos] = (byte)(((in[16 + inPos] & 4194303) >>> 16 | (in[17 + inPos] & 4194303) << 6) & 255);
         out[47 + outPos] = (byte)((in[17 + inPos] & 4194303) >>> 2 & 255);
         out[48 + outPos] = (byte)((in[17 + inPos] & 4194303) >>> 10 & 255);
         out[49 + outPos] = (byte)(((in[17 + inPos] & 4194303) >>> 18 | (in[18 + inPos] & 4194303) << 4) & 255);
         out[50 + outPos] = (byte)((in[18 + inPos] & 4194303) >>> 4 & 255);
         out[51 + outPos] = (byte)((in[18 + inPos] & 4194303) >>> 12 & 255);
         out[52 + outPos] = (byte)(((in[18 + inPos] & 4194303) >>> 20 | (in[19 + inPos] & 4194303) << 2) & 255);
         out[53 + outPos] = (byte)((in[19 + inPos] & 4194303) >>> 6 & 255);
         out[54 + outPos] = (byte)((in[19 + inPos] & 4194303) >>> 14 & 255);
         out[55 + outPos] = (byte)(in[20 + inPos] & 4194303 & 255);
         out[56 + outPos] = (byte)((in[20 + inPos] & 4194303) >>> 8 & 255);
         out[57 + outPos] = (byte)(((in[20 + inPos] & 4194303) >>> 16 | (in[21 + inPos] & 4194303) << 6) & 255);
         out[58 + outPos] = (byte)((in[21 + inPos] & 4194303) >>> 2 & 255);
         out[59 + outPos] = (byte)((in[21 + inPos] & 4194303) >>> 10 & 255);
         out[60 + outPos] = (byte)(((in[21 + inPos] & 4194303) >>> 18 | (in[22 + inPos] & 4194303) << 4) & 255);
         out[61 + outPos] = (byte)((in[22 + inPos] & 4194303) >>> 4 & 255);
         out[62 + outPos] = (byte)((in[22 + inPos] & 4194303) >>> 12 & 255);
         out[63 + outPos] = (byte)(((in[22 + inPos] & 4194303) >>> 20 | (in[23 + inPos] & 4194303) << 2) & 255);
         out[64 + outPos] = (byte)((in[23 + inPos] & 4194303) >>> 6 & 255);
         out[65 + outPos] = (byte)((in[23 + inPos] & 4194303) >>> 14 & 255);
         out[66 + outPos] = (byte)(in[24 + inPos] & 4194303 & 255);
         out[67 + outPos] = (byte)((in[24 + inPos] & 4194303) >>> 8 & 255);
         out[68 + outPos] = (byte)(((in[24 + inPos] & 4194303) >>> 16 | (in[25 + inPos] & 4194303) << 6) & 255);
         out[69 + outPos] = (byte)((in[25 + inPos] & 4194303) >>> 2 & 255);
         out[70 + outPos] = (byte)((in[25 + inPos] & 4194303) >>> 10 & 255);
         out[71 + outPos] = (byte)(((in[25 + inPos] & 4194303) >>> 18 | (in[26 + inPos] & 4194303) << 4) & 255);
         out[72 + outPos] = (byte)((in[26 + inPos] & 4194303) >>> 4 & 255);
         out[73 + outPos] = (byte)((in[26 + inPos] & 4194303) >>> 12 & 255);
         out[74 + outPos] = (byte)(((in[26 + inPos] & 4194303) >>> 20 | (in[27 + inPos] & 4194303) << 2) & 255);
         out[75 + outPos] = (byte)((in[27 + inPos] & 4194303) >>> 6 & 255);
         out[76 + outPos] = (byte)((in[27 + inPos] & 4194303) >>> 14 & 255);
         out[77 + outPos] = (byte)(in[28 + inPos] & 4194303 & 255);
         out[78 + outPos] = (byte)((in[28 + inPos] & 4194303) >>> 8 & 255);
         out[79 + outPos] = (byte)(((in[28 + inPos] & 4194303) >>> 16 | (in[29 + inPos] & 4194303) << 6) & 255);
         out[80 + outPos] = (byte)((in[29 + inPos] & 4194303) >>> 2 & 255);
         out[81 + outPos] = (byte)((in[29 + inPos] & 4194303) >>> 10 & 255);
         out[82 + outPos] = (byte)(((in[29 + inPos] & 4194303) >>> 18 | (in[30 + inPos] & 4194303) << 4) & 255);
         out[83 + outPos] = (byte)((in[30 + inPos] & 4194303) >>> 4 & 255);
         out[84 + outPos] = (byte)((in[30 + inPos] & 4194303) >>> 12 & 255);
         out[85 + outPos] = (byte)(((in[30 + inPos] & 4194303) >>> 20 | (in[31 + inPos] & 4194303) << 2) & 255);
         out[86 + outPos] = (byte)((in[31 + inPos] & 4194303) >>> 6 & 255);
         out[87 + outPos] = (byte)((in[31 + inPos] & 4194303) >>> 14 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 4194303;
         out[1 + outPos] = in[2 + inPos] >> 6 & 3 | in[3 + inPos] << 2 & 1023 | in[4 + inPos] << 10 & 262143 | in[5 + inPos] << 18 & 4194303;
         out[2 + outPos] = in[5 + inPos] >> 4 & 15 | in[6 + inPos] << 4 & 4095 | in[7 + inPos] << 12 & 1048575 | in[8 + inPos] << 20 & 4194303;
         out[3 + outPos] = in[8 + inPos] >> 2 & 63 | in[9 + inPos] << 6 & 16383 | in[10 + inPos] << 14 & 4194303;
         out[4 + outPos] = in[11 + inPos] & 255 | in[12 + inPos] << 8 & '\uffff' | in[13 + inPos] << 16 & 4194303;
         out[5 + outPos] = in[13 + inPos] >> 6 & 3 | in[14 + inPos] << 2 & 1023 | in[15 + inPos] << 10 & 262143 | in[16 + inPos] << 18 & 4194303;
         out[6 + outPos] = in[16 + inPos] >> 4 & 15 | in[17 + inPos] << 4 & 4095 | in[18 + inPos] << 12 & 1048575 | in[19 + inPos] << 20 & 4194303;
         out[7 + outPos] = in[19 + inPos] >> 2 & 63 | in[20 + inPos] << 6 & 16383 | in[21 + inPos] << 14 & 4194303;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 4194303;
         out[1 + outPos] = in.get(2 + inPos) >> 6 & 3 | in.get(3 + inPos) << 2 & 1023 | in.get(4 + inPos) << 10 & 262143 | in.get(5 + inPos) << 18 & 4194303;
         out[2 + outPos] = in.get(5 + inPos) >> 4 & 15 | in.get(6 + inPos) << 4 & 4095 | in.get(7 + inPos) << 12 & 1048575 | in.get(8 + inPos) << 20 & 4194303;
         out[3 + outPos] = in.get(8 + inPos) >> 2 & 63 | in.get(9 + inPos) << 6 & 16383 | in.get(10 + inPos) << 14 & 4194303;
         out[4 + outPos] = in.get(11 + inPos) & 255 | in.get(12 + inPos) << 8 & '\uffff' | in.get(13 + inPos) << 16 & 4194303;
         out[5 + outPos] = in.get(13 + inPos) >> 6 & 3 | in.get(14 + inPos) << 2 & 1023 | in.get(15 + inPos) << 10 & 262143 | in.get(16 + inPos) << 18 & 4194303;
         out[6 + outPos] = in.get(16 + inPos) >> 4 & 15 | in.get(17 + inPos) << 4 & 4095 | in.get(18 + inPos) << 12 & 1048575 | in.get(19 + inPos) << 20 & 4194303;
         out[7 + outPos] = in.get(19 + inPos) >> 2 & 63 | in.get(20 + inPos) << 6 & 16383 | in.get(21 + inPos) << 14 & 4194303;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 4194303;
         out[1 + outPos] = in[2 + inPos] >> 6 & 3 | in[3 + inPos] << 2 & 1023 | in[4 + inPos] << 10 & 262143 | in[5 + inPos] << 18 & 4194303;
         out[2 + outPos] = in[5 + inPos] >> 4 & 15 | in[6 + inPos] << 4 & 4095 | in[7 + inPos] << 12 & 1048575 | in[8 + inPos] << 20 & 4194303;
         out[3 + outPos] = in[8 + inPos] >> 2 & 63 | in[9 + inPos] << 6 & 16383 | in[10 + inPos] << 14 & 4194303;
         out[4 + outPos] = in[11 + inPos] & 255 | in[12 + inPos] << 8 & '\uffff' | in[13 + inPos] << 16 & 4194303;
         out[5 + outPos] = in[13 + inPos] >> 6 & 3 | in[14 + inPos] << 2 & 1023 | in[15 + inPos] << 10 & 262143 | in[16 + inPos] << 18 & 4194303;
         out[6 + outPos] = in[16 + inPos] >> 4 & 15 | in[17 + inPos] << 4 & 4095 | in[18 + inPos] << 12 & 1048575 | in[19 + inPos] << 20 & 4194303;
         out[7 + outPos] = in[19 + inPos] >> 2 & 63 | in[20 + inPos] << 6 & 16383 | in[21 + inPos] << 14 & 4194303;
         out[8 + outPos] = in[22 + inPos] & 255 | in[23 + inPos] << 8 & '\uffff' | in[24 + inPos] << 16 & 4194303;
         out[9 + outPos] = in[24 + inPos] >> 6 & 3 | in[25 + inPos] << 2 & 1023 | in[26 + inPos] << 10 & 262143 | in[27 + inPos] << 18 & 4194303;
         out[10 + outPos] = in[27 + inPos] >> 4 & 15 | in[28 + inPos] << 4 & 4095 | in[29 + inPos] << 12 & 1048575 | in[30 + inPos] << 20 & 4194303;
         out[11 + outPos] = in[30 + inPos] >> 2 & 63 | in[31 + inPos] << 6 & 16383 | in[32 + inPos] << 14 & 4194303;
         out[12 + outPos] = in[33 + inPos] & 255 | in[34 + inPos] << 8 & '\uffff' | in[35 + inPos] << 16 & 4194303;
         out[13 + outPos] = in[35 + inPos] >> 6 & 3 | in[36 + inPos] << 2 & 1023 | in[37 + inPos] << 10 & 262143 | in[38 + inPos] << 18 & 4194303;
         out[14 + outPos] = in[38 + inPos] >> 4 & 15 | in[39 + inPos] << 4 & 4095 | in[40 + inPos] << 12 & 1048575 | in[41 + inPos] << 20 & 4194303;
         out[15 + outPos] = in[41 + inPos] >> 2 & 63 | in[42 + inPos] << 6 & 16383 | in[43 + inPos] << 14 & 4194303;
         out[16 + outPos] = in[44 + inPos] & 255 | in[45 + inPos] << 8 & '\uffff' | in[46 + inPos] << 16 & 4194303;
         out[17 + outPos] = in[46 + inPos] >> 6 & 3 | in[47 + inPos] << 2 & 1023 | in[48 + inPos] << 10 & 262143 | in[49 + inPos] << 18 & 4194303;
         out[18 + outPos] = in[49 + inPos] >> 4 & 15 | in[50 + inPos] << 4 & 4095 | in[51 + inPos] << 12 & 1048575 | in[52 + inPos] << 20 & 4194303;
         out[19 + outPos] = in[52 + inPos] >> 2 & 63 | in[53 + inPos] << 6 & 16383 | in[54 + inPos] << 14 & 4194303;
         out[20 + outPos] = in[55 + inPos] & 255 | in[56 + inPos] << 8 & '\uffff' | in[57 + inPos] << 16 & 4194303;
         out[21 + outPos] = in[57 + inPos] >> 6 & 3 | in[58 + inPos] << 2 & 1023 | in[59 + inPos] << 10 & 262143 | in[60 + inPos] << 18 & 4194303;
         out[22 + outPos] = in[60 + inPos] >> 4 & 15 | in[61 + inPos] << 4 & 4095 | in[62 + inPos] << 12 & 1048575 | in[63 + inPos] << 20 & 4194303;
         out[23 + outPos] = in[63 + inPos] >> 2 & 63 | in[64 + inPos] << 6 & 16383 | in[65 + inPos] << 14 & 4194303;
         out[24 + outPos] = in[66 + inPos] & 255 | in[67 + inPos] << 8 & '\uffff' | in[68 + inPos] << 16 & 4194303;
         out[25 + outPos] = in[68 + inPos] >> 6 & 3 | in[69 + inPos] << 2 & 1023 | in[70 + inPos] << 10 & 262143 | in[71 + inPos] << 18 & 4194303;
         out[26 + outPos] = in[71 + inPos] >> 4 & 15 | in[72 + inPos] << 4 & 4095 | in[73 + inPos] << 12 & 1048575 | in[74 + inPos] << 20 & 4194303;
         out[27 + outPos] = in[74 + inPos] >> 2 & 63 | in[75 + inPos] << 6 & 16383 | in[76 + inPos] << 14 & 4194303;
         out[28 + outPos] = in[77 + inPos] & 255 | in[78 + inPos] << 8 & '\uffff' | in[79 + inPos] << 16 & 4194303;
         out[29 + outPos] = in[79 + inPos] >> 6 & 3 | in[80 + inPos] << 2 & 1023 | in[81 + inPos] << 10 & 262143 | in[82 + inPos] << 18 & 4194303;
         out[30 + outPos] = in[82 + inPos] >> 4 & 15 | in[83 + inPos] << 4 & 4095 | in[84 + inPos] << 12 & 1048575 | in[85 + inPos] << 20 & 4194303;
         out[31 + outPos] = in[85 + inPos] >> 2 & 63 | in[86 + inPos] << 6 & 16383 | in[87 + inPos] << 14 & 4194303;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 4194303;
         out[1 + outPos] = in.get(2 + inPos) >> 6 & 3 | in.get(3 + inPos) << 2 & 1023 | in.get(4 + inPos) << 10 & 262143 | in.get(5 + inPos) << 18 & 4194303;
         out[2 + outPos] = in.get(5 + inPos) >> 4 & 15 | in.get(6 + inPos) << 4 & 4095 | in.get(7 + inPos) << 12 & 1048575 | in.get(8 + inPos) << 20 & 4194303;
         out[3 + outPos] = in.get(8 + inPos) >> 2 & 63 | in.get(9 + inPos) << 6 & 16383 | in.get(10 + inPos) << 14 & 4194303;
         out[4 + outPos] = in.get(11 + inPos) & 255 | in.get(12 + inPos) << 8 & '\uffff' | in.get(13 + inPos) << 16 & 4194303;
         out[5 + outPos] = in.get(13 + inPos) >> 6 & 3 | in.get(14 + inPos) << 2 & 1023 | in.get(15 + inPos) << 10 & 262143 | in.get(16 + inPos) << 18 & 4194303;
         out[6 + outPos] = in.get(16 + inPos) >> 4 & 15 | in.get(17 + inPos) << 4 & 4095 | in.get(18 + inPos) << 12 & 1048575 | in.get(19 + inPos) << 20 & 4194303;
         out[7 + outPos] = in.get(19 + inPos) >> 2 & 63 | in.get(20 + inPos) << 6 & 16383 | in.get(21 + inPos) << 14 & 4194303;
         out[8 + outPos] = in.get(22 + inPos) & 255 | in.get(23 + inPos) << 8 & '\uffff' | in.get(24 + inPos) << 16 & 4194303;
         out[9 + outPos] = in.get(24 + inPos) >> 6 & 3 | in.get(25 + inPos) << 2 & 1023 | in.get(26 + inPos) << 10 & 262143 | in.get(27 + inPos) << 18 & 4194303;
         out[10 + outPos] = in.get(27 + inPos) >> 4 & 15 | in.get(28 + inPos) << 4 & 4095 | in.get(29 + inPos) << 12 & 1048575 | in.get(30 + inPos) << 20 & 4194303;
         out[11 + outPos] = in.get(30 + inPos) >> 2 & 63 | in.get(31 + inPos) << 6 & 16383 | in.get(32 + inPos) << 14 & 4194303;
         out[12 + outPos] = in.get(33 + inPos) & 255 | in.get(34 + inPos) << 8 & '\uffff' | in.get(35 + inPos) << 16 & 4194303;
         out[13 + outPos] = in.get(35 + inPos) >> 6 & 3 | in.get(36 + inPos) << 2 & 1023 | in.get(37 + inPos) << 10 & 262143 | in.get(38 + inPos) << 18 & 4194303;
         out[14 + outPos] = in.get(38 + inPos) >> 4 & 15 | in.get(39 + inPos) << 4 & 4095 | in.get(40 + inPos) << 12 & 1048575 | in.get(41 + inPos) << 20 & 4194303;
         out[15 + outPos] = in.get(41 + inPos) >> 2 & 63 | in.get(42 + inPos) << 6 & 16383 | in.get(43 + inPos) << 14 & 4194303;
         out[16 + outPos] = in.get(44 + inPos) & 255 | in.get(45 + inPos) << 8 & '\uffff' | in.get(46 + inPos) << 16 & 4194303;
         out[17 + outPos] = in.get(46 + inPos) >> 6 & 3 | in.get(47 + inPos) << 2 & 1023 | in.get(48 + inPos) << 10 & 262143 | in.get(49 + inPos) << 18 & 4194303;
         out[18 + outPos] = in.get(49 + inPos) >> 4 & 15 | in.get(50 + inPos) << 4 & 4095 | in.get(51 + inPos) << 12 & 1048575 | in.get(52 + inPos) << 20 & 4194303;
         out[19 + outPos] = in.get(52 + inPos) >> 2 & 63 | in.get(53 + inPos) << 6 & 16383 | in.get(54 + inPos) << 14 & 4194303;
         out[20 + outPos] = in.get(55 + inPos) & 255 | in.get(56 + inPos) << 8 & '\uffff' | in.get(57 + inPos) << 16 & 4194303;
         out[21 + outPos] = in.get(57 + inPos) >> 6 & 3 | in.get(58 + inPos) << 2 & 1023 | in.get(59 + inPos) << 10 & 262143 | in.get(60 + inPos) << 18 & 4194303;
         out[22 + outPos] = in.get(60 + inPos) >> 4 & 15 | in.get(61 + inPos) << 4 & 4095 | in.get(62 + inPos) << 12 & 1048575 | in.get(63 + inPos) << 20 & 4194303;
         out[23 + outPos] = in.get(63 + inPos) >> 2 & 63 | in.get(64 + inPos) << 6 & 16383 | in.get(65 + inPos) << 14 & 4194303;
         out[24 + outPos] = in.get(66 + inPos) & 255 | in.get(67 + inPos) << 8 & '\uffff' | in.get(68 + inPos) << 16 & 4194303;
         out[25 + outPos] = in.get(68 + inPos) >> 6 & 3 | in.get(69 + inPos) << 2 & 1023 | in.get(70 + inPos) << 10 & 262143 | in.get(71 + inPos) << 18 & 4194303;
         out[26 + outPos] = in.get(71 + inPos) >> 4 & 15 | in.get(72 + inPos) << 4 & 4095 | in.get(73 + inPos) << 12 & 1048575 | in.get(74 + inPos) << 20 & 4194303;
         out[27 + outPos] = in.get(74 + inPos) >> 2 & 63 | in.get(75 + inPos) << 6 & 16383 | in.get(76 + inPos) << 14 & 4194303;
         out[28 + outPos] = in.get(77 + inPos) & 255 | in.get(78 + inPos) << 8 & '\uffff' | in.get(79 + inPos) << 16 & 4194303;
         out[29 + outPos] = in.get(79 + inPos) >> 6 & 3 | in.get(80 + inPos) << 2 & 1023 | in.get(81 + inPos) << 10 & 262143 | in.get(82 + inPos) << 18 & 4194303;
         out[30 + outPos] = in.get(82 + inPos) >> 4 & 15 | in.get(83 + inPos) << 4 & 4095 | in.get(84 + inPos) << 12 & 1048575 | in.get(85 + inPos) << 20 & 4194303;
         out[31 + outPos] = in.get(85 + inPos) >> 2 & 63 | in.get(86 + inPos) << 6 & 16383 | in.get(87 + inPos) << 14 & 4194303;
      }
   }

   private static final class Packer23 extends BytePacker {
      private Packer23() {
         super(23);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 8388607 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 8388607) >>> 8 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 8388607) >>> 16 | (in[1 + inPos] & 8388607) << 7) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 8388607) >>> 1 & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 8388607) >>> 9 & 255);
         out[5 + outPos] = (byte)(((in[1 + inPos] & 8388607) >>> 17 | (in[2 + inPos] & 8388607) << 6) & 255);
         out[6 + outPos] = (byte)((in[2 + inPos] & 8388607) >>> 2 & 255);
         out[7 + outPos] = (byte)((in[2 + inPos] & 8388607) >>> 10 & 255);
         out[8 + outPos] = (byte)(((in[2 + inPos] & 8388607) >>> 18 | (in[3 + inPos] & 8388607) << 5) & 255);
         out[9 + outPos] = (byte)((in[3 + inPos] & 8388607) >>> 3 & 255);
         out[10 + outPos] = (byte)((in[3 + inPos] & 8388607) >>> 11 & 255);
         out[11 + outPos] = (byte)(((in[3 + inPos] & 8388607) >>> 19 | (in[4 + inPos] & 8388607) << 4) & 255);
         out[12 + outPos] = (byte)((in[4 + inPos] & 8388607) >>> 4 & 255);
         out[13 + outPos] = (byte)((in[4 + inPos] & 8388607) >>> 12 & 255);
         out[14 + outPos] = (byte)(((in[4 + inPos] & 8388607) >>> 20 | (in[5 + inPos] & 8388607) << 3) & 255);
         out[15 + outPos] = (byte)((in[5 + inPos] & 8388607) >>> 5 & 255);
         out[16 + outPos] = (byte)((in[5 + inPos] & 8388607) >>> 13 & 255);
         out[17 + outPos] = (byte)(((in[5 + inPos] & 8388607) >>> 21 | (in[6 + inPos] & 8388607) << 2) & 255);
         out[18 + outPos] = (byte)((in[6 + inPos] & 8388607) >>> 6 & 255);
         out[19 + outPos] = (byte)((in[6 + inPos] & 8388607) >>> 14 & 255);
         out[20 + outPos] = (byte)(((in[6 + inPos] & 8388607) >>> 22 | (in[7 + inPos] & 8388607) << 1) & 255);
         out[21 + outPos] = (byte)((in[7 + inPos] & 8388607) >>> 7 & 255);
         out[22 + outPos] = (byte)((in[7 + inPos] & 8388607) >>> 15 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 8388607 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 8388607) >>> 8 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 8388607) >>> 16 | (in[1 + inPos] & 8388607) << 7) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 8388607) >>> 1 & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 8388607) >>> 9 & 255);
         out[5 + outPos] = (byte)(((in[1 + inPos] & 8388607) >>> 17 | (in[2 + inPos] & 8388607) << 6) & 255);
         out[6 + outPos] = (byte)((in[2 + inPos] & 8388607) >>> 2 & 255);
         out[7 + outPos] = (byte)((in[2 + inPos] & 8388607) >>> 10 & 255);
         out[8 + outPos] = (byte)(((in[2 + inPos] & 8388607) >>> 18 | (in[3 + inPos] & 8388607) << 5) & 255);
         out[9 + outPos] = (byte)((in[3 + inPos] & 8388607) >>> 3 & 255);
         out[10 + outPos] = (byte)((in[3 + inPos] & 8388607) >>> 11 & 255);
         out[11 + outPos] = (byte)(((in[3 + inPos] & 8388607) >>> 19 | (in[4 + inPos] & 8388607) << 4) & 255);
         out[12 + outPos] = (byte)((in[4 + inPos] & 8388607) >>> 4 & 255);
         out[13 + outPos] = (byte)((in[4 + inPos] & 8388607) >>> 12 & 255);
         out[14 + outPos] = (byte)(((in[4 + inPos] & 8388607) >>> 20 | (in[5 + inPos] & 8388607) << 3) & 255);
         out[15 + outPos] = (byte)((in[5 + inPos] & 8388607) >>> 5 & 255);
         out[16 + outPos] = (byte)((in[5 + inPos] & 8388607) >>> 13 & 255);
         out[17 + outPos] = (byte)(((in[5 + inPos] & 8388607) >>> 21 | (in[6 + inPos] & 8388607) << 2) & 255);
         out[18 + outPos] = (byte)((in[6 + inPos] & 8388607) >>> 6 & 255);
         out[19 + outPos] = (byte)((in[6 + inPos] & 8388607) >>> 14 & 255);
         out[20 + outPos] = (byte)(((in[6 + inPos] & 8388607) >>> 22 | (in[7 + inPos] & 8388607) << 1) & 255);
         out[21 + outPos] = (byte)((in[7 + inPos] & 8388607) >>> 7 & 255);
         out[22 + outPos] = (byte)((in[7 + inPos] & 8388607) >>> 15 & 255);
         out[23 + outPos] = (byte)(in[8 + inPos] & 8388607 & 255);
         out[24 + outPos] = (byte)((in[8 + inPos] & 8388607) >>> 8 & 255);
         out[25 + outPos] = (byte)(((in[8 + inPos] & 8388607) >>> 16 | (in[9 + inPos] & 8388607) << 7) & 255);
         out[26 + outPos] = (byte)((in[9 + inPos] & 8388607) >>> 1 & 255);
         out[27 + outPos] = (byte)((in[9 + inPos] & 8388607) >>> 9 & 255);
         out[28 + outPos] = (byte)(((in[9 + inPos] & 8388607) >>> 17 | (in[10 + inPos] & 8388607) << 6) & 255);
         out[29 + outPos] = (byte)((in[10 + inPos] & 8388607) >>> 2 & 255);
         out[30 + outPos] = (byte)((in[10 + inPos] & 8388607) >>> 10 & 255);
         out[31 + outPos] = (byte)(((in[10 + inPos] & 8388607) >>> 18 | (in[11 + inPos] & 8388607) << 5) & 255);
         out[32 + outPos] = (byte)((in[11 + inPos] & 8388607) >>> 3 & 255);
         out[33 + outPos] = (byte)((in[11 + inPos] & 8388607) >>> 11 & 255);
         out[34 + outPos] = (byte)(((in[11 + inPos] & 8388607) >>> 19 | (in[12 + inPos] & 8388607) << 4) & 255);
         out[35 + outPos] = (byte)((in[12 + inPos] & 8388607) >>> 4 & 255);
         out[36 + outPos] = (byte)((in[12 + inPos] & 8388607) >>> 12 & 255);
         out[37 + outPos] = (byte)(((in[12 + inPos] & 8388607) >>> 20 | (in[13 + inPos] & 8388607) << 3) & 255);
         out[38 + outPos] = (byte)((in[13 + inPos] & 8388607) >>> 5 & 255);
         out[39 + outPos] = (byte)((in[13 + inPos] & 8388607) >>> 13 & 255);
         out[40 + outPos] = (byte)(((in[13 + inPos] & 8388607) >>> 21 | (in[14 + inPos] & 8388607) << 2) & 255);
         out[41 + outPos] = (byte)((in[14 + inPos] & 8388607) >>> 6 & 255);
         out[42 + outPos] = (byte)((in[14 + inPos] & 8388607) >>> 14 & 255);
         out[43 + outPos] = (byte)(((in[14 + inPos] & 8388607) >>> 22 | (in[15 + inPos] & 8388607) << 1) & 255);
         out[44 + outPos] = (byte)((in[15 + inPos] & 8388607) >>> 7 & 255);
         out[45 + outPos] = (byte)((in[15 + inPos] & 8388607) >>> 15 & 255);
         out[46 + outPos] = (byte)(in[16 + inPos] & 8388607 & 255);
         out[47 + outPos] = (byte)((in[16 + inPos] & 8388607) >>> 8 & 255);
         out[48 + outPos] = (byte)(((in[16 + inPos] & 8388607) >>> 16 | (in[17 + inPos] & 8388607) << 7) & 255);
         out[49 + outPos] = (byte)((in[17 + inPos] & 8388607) >>> 1 & 255);
         out[50 + outPos] = (byte)((in[17 + inPos] & 8388607) >>> 9 & 255);
         out[51 + outPos] = (byte)(((in[17 + inPos] & 8388607) >>> 17 | (in[18 + inPos] & 8388607) << 6) & 255);
         out[52 + outPos] = (byte)((in[18 + inPos] & 8388607) >>> 2 & 255);
         out[53 + outPos] = (byte)((in[18 + inPos] & 8388607) >>> 10 & 255);
         out[54 + outPos] = (byte)(((in[18 + inPos] & 8388607) >>> 18 | (in[19 + inPos] & 8388607) << 5) & 255);
         out[55 + outPos] = (byte)((in[19 + inPos] & 8388607) >>> 3 & 255);
         out[56 + outPos] = (byte)((in[19 + inPos] & 8388607) >>> 11 & 255);
         out[57 + outPos] = (byte)(((in[19 + inPos] & 8388607) >>> 19 | (in[20 + inPos] & 8388607) << 4) & 255);
         out[58 + outPos] = (byte)((in[20 + inPos] & 8388607) >>> 4 & 255);
         out[59 + outPos] = (byte)((in[20 + inPos] & 8388607) >>> 12 & 255);
         out[60 + outPos] = (byte)(((in[20 + inPos] & 8388607) >>> 20 | (in[21 + inPos] & 8388607) << 3) & 255);
         out[61 + outPos] = (byte)((in[21 + inPos] & 8388607) >>> 5 & 255);
         out[62 + outPos] = (byte)((in[21 + inPos] & 8388607) >>> 13 & 255);
         out[63 + outPos] = (byte)(((in[21 + inPos] & 8388607) >>> 21 | (in[22 + inPos] & 8388607) << 2) & 255);
         out[64 + outPos] = (byte)((in[22 + inPos] & 8388607) >>> 6 & 255);
         out[65 + outPos] = (byte)((in[22 + inPos] & 8388607) >>> 14 & 255);
         out[66 + outPos] = (byte)(((in[22 + inPos] & 8388607) >>> 22 | (in[23 + inPos] & 8388607) << 1) & 255);
         out[67 + outPos] = (byte)((in[23 + inPos] & 8388607) >>> 7 & 255);
         out[68 + outPos] = (byte)((in[23 + inPos] & 8388607) >>> 15 & 255);
         out[69 + outPos] = (byte)(in[24 + inPos] & 8388607 & 255);
         out[70 + outPos] = (byte)((in[24 + inPos] & 8388607) >>> 8 & 255);
         out[71 + outPos] = (byte)(((in[24 + inPos] & 8388607) >>> 16 | (in[25 + inPos] & 8388607) << 7) & 255);
         out[72 + outPos] = (byte)((in[25 + inPos] & 8388607) >>> 1 & 255);
         out[73 + outPos] = (byte)((in[25 + inPos] & 8388607) >>> 9 & 255);
         out[74 + outPos] = (byte)(((in[25 + inPos] & 8388607) >>> 17 | (in[26 + inPos] & 8388607) << 6) & 255);
         out[75 + outPos] = (byte)((in[26 + inPos] & 8388607) >>> 2 & 255);
         out[76 + outPos] = (byte)((in[26 + inPos] & 8388607) >>> 10 & 255);
         out[77 + outPos] = (byte)(((in[26 + inPos] & 8388607) >>> 18 | (in[27 + inPos] & 8388607) << 5) & 255);
         out[78 + outPos] = (byte)((in[27 + inPos] & 8388607) >>> 3 & 255);
         out[79 + outPos] = (byte)((in[27 + inPos] & 8388607) >>> 11 & 255);
         out[80 + outPos] = (byte)(((in[27 + inPos] & 8388607) >>> 19 | (in[28 + inPos] & 8388607) << 4) & 255);
         out[81 + outPos] = (byte)((in[28 + inPos] & 8388607) >>> 4 & 255);
         out[82 + outPos] = (byte)((in[28 + inPos] & 8388607) >>> 12 & 255);
         out[83 + outPos] = (byte)(((in[28 + inPos] & 8388607) >>> 20 | (in[29 + inPos] & 8388607) << 3) & 255);
         out[84 + outPos] = (byte)((in[29 + inPos] & 8388607) >>> 5 & 255);
         out[85 + outPos] = (byte)((in[29 + inPos] & 8388607) >>> 13 & 255);
         out[86 + outPos] = (byte)(((in[29 + inPos] & 8388607) >>> 21 | (in[30 + inPos] & 8388607) << 2) & 255);
         out[87 + outPos] = (byte)((in[30 + inPos] & 8388607) >>> 6 & 255);
         out[88 + outPos] = (byte)((in[30 + inPos] & 8388607) >>> 14 & 255);
         out[89 + outPos] = (byte)(((in[30 + inPos] & 8388607) >>> 22 | (in[31 + inPos] & 8388607) << 1) & 255);
         out[90 + outPos] = (byte)((in[31 + inPos] & 8388607) >>> 7 & 255);
         out[91 + outPos] = (byte)((in[31 + inPos] & 8388607) >>> 15 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 8388607;
         out[1 + outPos] = in[2 + inPos] >> 7 & 1 | in[3 + inPos] << 1 & 511 | in[4 + inPos] << 9 & 131071 | in[5 + inPos] << 17 & 8388607;
         out[2 + outPos] = in[5 + inPos] >> 6 & 3 | in[6 + inPos] << 2 & 1023 | in[7 + inPos] << 10 & 262143 | in[8 + inPos] << 18 & 8388607;
         out[3 + outPos] = in[8 + inPos] >> 5 & 7 | in[9 + inPos] << 3 & 2047 | in[10 + inPos] << 11 & 524287 | in[11 + inPos] << 19 & 8388607;
         out[4 + outPos] = in[11 + inPos] >> 4 & 15 | in[12 + inPos] << 4 & 4095 | in[13 + inPos] << 12 & 1048575 | in[14 + inPos] << 20 & 8388607;
         out[5 + outPos] = in[14 + inPos] >> 3 & 31 | in[15 + inPos] << 5 & 8191 | in[16 + inPos] << 13 & 2097151 | in[17 + inPos] << 21 & 8388607;
         out[6 + outPos] = in[17 + inPos] >> 2 & 63 | in[18 + inPos] << 6 & 16383 | in[19 + inPos] << 14 & 4194303 | in[20 + inPos] << 22 & 8388607;
         out[7 + outPos] = in[20 + inPos] >> 1 & 127 | in[21 + inPos] << 7 & 32767 | in[22 + inPos] << 15 & 8388607;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 8388607;
         out[1 + outPos] = in.get(2 + inPos) >> 7 & 1 | in.get(3 + inPos) << 1 & 511 | in.get(4 + inPos) << 9 & 131071 | in.get(5 + inPos) << 17 & 8388607;
         out[2 + outPos] = in.get(5 + inPos) >> 6 & 3 | in.get(6 + inPos) << 2 & 1023 | in.get(7 + inPos) << 10 & 262143 | in.get(8 + inPos) << 18 & 8388607;
         out[3 + outPos] = in.get(8 + inPos) >> 5 & 7 | in.get(9 + inPos) << 3 & 2047 | in.get(10 + inPos) << 11 & 524287 | in.get(11 + inPos) << 19 & 8388607;
         out[4 + outPos] = in.get(11 + inPos) >> 4 & 15 | in.get(12 + inPos) << 4 & 4095 | in.get(13 + inPos) << 12 & 1048575 | in.get(14 + inPos) << 20 & 8388607;
         out[5 + outPos] = in.get(14 + inPos) >> 3 & 31 | in.get(15 + inPos) << 5 & 8191 | in.get(16 + inPos) << 13 & 2097151 | in.get(17 + inPos) << 21 & 8388607;
         out[6 + outPos] = in.get(17 + inPos) >> 2 & 63 | in.get(18 + inPos) << 6 & 16383 | in.get(19 + inPos) << 14 & 4194303 | in.get(20 + inPos) << 22 & 8388607;
         out[7 + outPos] = in.get(20 + inPos) >> 1 & 127 | in.get(21 + inPos) << 7 & 32767 | in.get(22 + inPos) << 15 & 8388607;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 8388607;
         out[1 + outPos] = in[2 + inPos] >> 7 & 1 | in[3 + inPos] << 1 & 511 | in[4 + inPos] << 9 & 131071 | in[5 + inPos] << 17 & 8388607;
         out[2 + outPos] = in[5 + inPos] >> 6 & 3 | in[6 + inPos] << 2 & 1023 | in[7 + inPos] << 10 & 262143 | in[8 + inPos] << 18 & 8388607;
         out[3 + outPos] = in[8 + inPos] >> 5 & 7 | in[9 + inPos] << 3 & 2047 | in[10 + inPos] << 11 & 524287 | in[11 + inPos] << 19 & 8388607;
         out[4 + outPos] = in[11 + inPos] >> 4 & 15 | in[12 + inPos] << 4 & 4095 | in[13 + inPos] << 12 & 1048575 | in[14 + inPos] << 20 & 8388607;
         out[5 + outPos] = in[14 + inPos] >> 3 & 31 | in[15 + inPos] << 5 & 8191 | in[16 + inPos] << 13 & 2097151 | in[17 + inPos] << 21 & 8388607;
         out[6 + outPos] = in[17 + inPos] >> 2 & 63 | in[18 + inPos] << 6 & 16383 | in[19 + inPos] << 14 & 4194303 | in[20 + inPos] << 22 & 8388607;
         out[7 + outPos] = in[20 + inPos] >> 1 & 127 | in[21 + inPos] << 7 & 32767 | in[22 + inPos] << 15 & 8388607;
         out[8 + outPos] = in[23 + inPos] & 255 | in[24 + inPos] << 8 & '\uffff' | in[25 + inPos] << 16 & 8388607;
         out[9 + outPos] = in[25 + inPos] >> 7 & 1 | in[26 + inPos] << 1 & 511 | in[27 + inPos] << 9 & 131071 | in[28 + inPos] << 17 & 8388607;
         out[10 + outPos] = in[28 + inPos] >> 6 & 3 | in[29 + inPos] << 2 & 1023 | in[30 + inPos] << 10 & 262143 | in[31 + inPos] << 18 & 8388607;
         out[11 + outPos] = in[31 + inPos] >> 5 & 7 | in[32 + inPos] << 3 & 2047 | in[33 + inPos] << 11 & 524287 | in[34 + inPos] << 19 & 8388607;
         out[12 + outPos] = in[34 + inPos] >> 4 & 15 | in[35 + inPos] << 4 & 4095 | in[36 + inPos] << 12 & 1048575 | in[37 + inPos] << 20 & 8388607;
         out[13 + outPos] = in[37 + inPos] >> 3 & 31 | in[38 + inPos] << 5 & 8191 | in[39 + inPos] << 13 & 2097151 | in[40 + inPos] << 21 & 8388607;
         out[14 + outPos] = in[40 + inPos] >> 2 & 63 | in[41 + inPos] << 6 & 16383 | in[42 + inPos] << 14 & 4194303 | in[43 + inPos] << 22 & 8388607;
         out[15 + outPos] = in[43 + inPos] >> 1 & 127 | in[44 + inPos] << 7 & 32767 | in[45 + inPos] << 15 & 8388607;
         out[16 + outPos] = in[46 + inPos] & 255 | in[47 + inPos] << 8 & '\uffff' | in[48 + inPos] << 16 & 8388607;
         out[17 + outPos] = in[48 + inPos] >> 7 & 1 | in[49 + inPos] << 1 & 511 | in[50 + inPos] << 9 & 131071 | in[51 + inPos] << 17 & 8388607;
         out[18 + outPos] = in[51 + inPos] >> 6 & 3 | in[52 + inPos] << 2 & 1023 | in[53 + inPos] << 10 & 262143 | in[54 + inPos] << 18 & 8388607;
         out[19 + outPos] = in[54 + inPos] >> 5 & 7 | in[55 + inPos] << 3 & 2047 | in[56 + inPos] << 11 & 524287 | in[57 + inPos] << 19 & 8388607;
         out[20 + outPos] = in[57 + inPos] >> 4 & 15 | in[58 + inPos] << 4 & 4095 | in[59 + inPos] << 12 & 1048575 | in[60 + inPos] << 20 & 8388607;
         out[21 + outPos] = in[60 + inPos] >> 3 & 31 | in[61 + inPos] << 5 & 8191 | in[62 + inPos] << 13 & 2097151 | in[63 + inPos] << 21 & 8388607;
         out[22 + outPos] = in[63 + inPos] >> 2 & 63 | in[64 + inPos] << 6 & 16383 | in[65 + inPos] << 14 & 4194303 | in[66 + inPos] << 22 & 8388607;
         out[23 + outPos] = in[66 + inPos] >> 1 & 127 | in[67 + inPos] << 7 & 32767 | in[68 + inPos] << 15 & 8388607;
         out[24 + outPos] = in[69 + inPos] & 255 | in[70 + inPos] << 8 & '\uffff' | in[71 + inPos] << 16 & 8388607;
         out[25 + outPos] = in[71 + inPos] >> 7 & 1 | in[72 + inPos] << 1 & 511 | in[73 + inPos] << 9 & 131071 | in[74 + inPos] << 17 & 8388607;
         out[26 + outPos] = in[74 + inPos] >> 6 & 3 | in[75 + inPos] << 2 & 1023 | in[76 + inPos] << 10 & 262143 | in[77 + inPos] << 18 & 8388607;
         out[27 + outPos] = in[77 + inPos] >> 5 & 7 | in[78 + inPos] << 3 & 2047 | in[79 + inPos] << 11 & 524287 | in[80 + inPos] << 19 & 8388607;
         out[28 + outPos] = in[80 + inPos] >> 4 & 15 | in[81 + inPos] << 4 & 4095 | in[82 + inPos] << 12 & 1048575 | in[83 + inPos] << 20 & 8388607;
         out[29 + outPos] = in[83 + inPos] >> 3 & 31 | in[84 + inPos] << 5 & 8191 | in[85 + inPos] << 13 & 2097151 | in[86 + inPos] << 21 & 8388607;
         out[30 + outPos] = in[86 + inPos] >> 2 & 63 | in[87 + inPos] << 6 & 16383 | in[88 + inPos] << 14 & 4194303 | in[89 + inPos] << 22 & 8388607;
         out[31 + outPos] = in[89 + inPos] >> 1 & 127 | in[90 + inPos] << 7 & 32767 | in[91 + inPos] << 15 & 8388607;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 8388607;
         out[1 + outPos] = in.get(2 + inPos) >> 7 & 1 | in.get(3 + inPos) << 1 & 511 | in.get(4 + inPos) << 9 & 131071 | in.get(5 + inPos) << 17 & 8388607;
         out[2 + outPos] = in.get(5 + inPos) >> 6 & 3 | in.get(6 + inPos) << 2 & 1023 | in.get(7 + inPos) << 10 & 262143 | in.get(8 + inPos) << 18 & 8388607;
         out[3 + outPos] = in.get(8 + inPos) >> 5 & 7 | in.get(9 + inPos) << 3 & 2047 | in.get(10 + inPos) << 11 & 524287 | in.get(11 + inPos) << 19 & 8388607;
         out[4 + outPos] = in.get(11 + inPos) >> 4 & 15 | in.get(12 + inPos) << 4 & 4095 | in.get(13 + inPos) << 12 & 1048575 | in.get(14 + inPos) << 20 & 8388607;
         out[5 + outPos] = in.get(14 + inPos) >> 3 & 31 | in.get(15 + inPos) << 5 & 8191 | in.get(16 + inPos) << 13 & 2097151 | in.get(17 + inPos) << 21 & 8388607;
         out[6 + outPos] = in.get(17 + inPos) >> 2 & 63 | in.get(18 + inPos) << 6 & 16383 | in.get(19 + inPos) << 14 & 4194303 | in.get(20 + inPos) << 22 & 8388607;
         out[7 + outPos] = in.get(20 + inPos) >> 1 & 127 | in.get(21 + inPos) << 7 & 32767 | in.get(22 + inPos) << 15 & 8388607;
         out[8 + outPos] = in.get(23 + inPos) & 255 | in.get(24 + inPos) << 8 & '\uffff' | in.get(25 + inPos) << 16 & 8388607;
         out[9 + outPos] = in.get(25 + inPos) >> 7 & 1 | in.get(26 + inPos) << 1 & 511 | in.get(27 + inPos) << 9 & 131071 | in.get(28 + inPos) << 17 & 8388607;
         out[10 + outPos] = in.get(28 + inPos) >> 6 & 3 | in.get(29 + inPos) << 2 & 1023 | in.get(30 + inPos) << 10 & 262143 | in.get(31 + inPos) << 18 & 8388607;
         out[11 + outPos] = in.get(31 + inPos) >> 5 & 7 | in.get(32 + inPos) << 3 & 2047 | in.get(33 + inPos) << 11 & 524287 | in.get(34 + inPos) << 19 & 8388607;
         out[12 + outPos] = in.get(34 + inPos) >> 4 & 15 | in.get(35 + inPos) << 4 & 4095 | in.get(36 + inPos) << 12 & 1048575 | in.get(37 + inPos) << 20 & 8388607;
         out[13 + outPos] = in.get(37 + inPos) >> 3 & 31 | in.get(38 + inPos) << 5 & 8191 | in.get(39 + inPos) << 13 & 2097151 | in.get(40 + inPos) << 21 & 8388607;
         out[14 + outPos] = in.get(40 + inPos) >> 2 & 63 | in.get(41 + inPos) << 6 & 16383 | in.get(42 + inPos) << 14 & 4194303 | in.get(43 + inPos) << 22 & 8388607;
         out[15 + outPos] = in.get(43 + inPos) >> 1 & 127 | in.get(44 + inPos) << 7 & 32767 | in.get(45 + inPos) << 15 & 8388607;
         out[16 + outPos] = in.get(46 + inPos) & 255 | in.get(47 + inPos) << 8 & '\uffff' | in.get(48 + inPos) << 16 & 8388607;
         out[17 + outPos] = in.get(48 + inPos) >> 7 & 1 | in.get(49 + inPos) << 1 & 511 | in.get(50 + inPos) << 9 & 131071 | in.get(51 + inPos) << 17 & 8388607;
         out[18 + outPos] = in.get(51 + inPos) >> 6 & 3 | in.get(52 + inPos) << 2 & 1023 | in.get(53 + inPos) << 10 & 262143 | in.get(54 + inPos) << 18 & 8388607;
         out[19 + outPos] = in.get(54 + inPos) >> 5 & 7 | in.get(55 + inPos) << 3 & 2047 | in.get(56 + inPos) << 11 & 524287 | in.get(57 + inPos) << 19 & 8388607;
         out[20 + outPos] = in.get(57 + inPos) >> 4 & 15 | in.get(58 + inPos) << 4 & 4095 | in.get(59 + inPos) << 12 & 1048575 | in.get(60 + inPos) << 20 & 8388607;
         out[21 + outPos] = in.get(60 + inPos) >> 3 & 31 | in.get(61 + inPos) << 5 & 8191 | in.get(62 + inPos) << 13 & 2097151 | in.get(63 + inPos) << 21 & 8388607;
         out[22 + outPos] = in.get(63 + inPos) >> 2 & 63 | in.get(64 + inPos) << 6 & 16383 | in.get(65 + inPos) << 14 & 4194303 | in.get(66 + inPos) << 22 & 8388607;
         out[23 + outPos] = in.get(66 + inPos) >> 1 & 127 | in.get(67 + inPos) << 7 & 32767 | in.get(68 + inPos) << 15 & 8388607;
         out[24 + outPos] = in.get(69 + inPos) & 255 | in.get(70 + inPos) << 8 & '\uffff' | in.get(71 + inPos) << 16 & 8388607;
         out[25 + outPos] = in.get(71 + inPos) >> 7 & 1 | in.get(72 + inPos) << 1 & 511 | in.get(73 + inPos) << 9 & 131071 | in.get(74 + inPos) << 17 & 8388607;
         out[26 + outPos] = in.get(74 + inPos) >> 6 & 3 | in.get(75 + inPos) << 2 & 1023 | in.get(76 + inPos) << 10 & 262143 | in.get(77 + inPos) << 18 & 8388607;
         out[27 + outPos] = in.get(77 + inPos) >> 5 & 7 | in.get(78 + inPos) << 3 & 2047 | in.get(79 + inPos) << 11 & 524287 | in.get(80 + inPos) << 19 & 8388607;
         out[28 + outPos] = in.get(80 + inPos) >> 4 & 15 | in.get(81 + inPos) << 4 & 4095 | in.get(82 + inPos) << 12 & 1048575 | in.get(83 + inPos) << 20 & 8388607;
         out[29 + outPos] = in.get(83 + inPos) >> 3 & 31 | in.get(84 + inPos) << 5 & 8191 | in.get(85 + inPos) << 13 & 2097151 | in.get(86 + inPos) << 21 & 8388607;
         out[30 + outPos] = in.get(86 + inPos) >> 2 & 63 | in.get(87 + inPos) << 6 & 16383 | in.get(88 + inPos) << 14 & 4194303 | in.get(89 + inPos) << 22 & 8388607;
         out[31 + outPos] = in.get(89 + inPos) >> 1 & 127 | in.get(90 + inPos) << 7 & 32767 | in.get(91 + inPos) << 15 & 8388607;
      }
   }

   private static final class Packer24 extends BytePacker {
      private Packer24() {
         super(24);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 16777215 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 16777215) >>> 8 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & 16777215) >>> 16 & 255);
         out[3 + outPos] = (byte)(in[1 + inPos] & 16777215 & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 16777215) >>> 8 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & 16777215) >>> 16 & 255);
         out[6 + outPos] = (byte)(in[2 + inPos] & 16777215 & 255);
         out[7 + outPos] = (byte)((in[2 + inPos] & 16777215) >>> 8 & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & 16777215) >>> 16 & 255);
         out[9 + outPos] = (byte)(in[3 + inPos] & 16777215 & 255);
         out[10 + outPos] = (byte)((in[3 + inPos] & 16777215) >>> 8 & 255);
         out[11 + outPos] = (byte)((in[3 + inPos] & 16777215) >>> 16 & 255);
         out[12 + outPos] = (byte)(in[4 + inPos] & 16777215 & 255);
         out[13 + outPos] = (byte)((in[4 + inPos] & 16777215) >>> 8 & 255);
         out[14 + outPos] = (byte)((in[4 + inPos] & 16777215) >>> 16 & 255);
         out[15 + outPos] = (byte)(in[5 + inPos] & 16777215 & 255);
         out[16 + outPos] = (byte)((in[5 + inPos] & 16777215) >>> 8 & 255);
         out[17 + outPos] = (byte)((in[5 + inPos] & 16777215) >>> 16 & 255);
         out[18 + outPos] = (byte)(in[6 + inPos] & 16777215 & 255);
         out[19 + outPos] = (byte)((in[6 + inPos] & 16777215) >>> 8 & 255);
         out[20 + outPos] = (byte)((in[6 + inPos] & 16777215) >>> 16 & 255);
         out[21 + outPos] = (byte)(in[7 + inPos] & 16777215 & 255);
         out[22 + outPos] = (byte)((in[7 + inPos] & 16777215) >>> 8 & 255);
         out[23 + outPos] = (byte)((in[7 + inPos] & 16777215) >>> 16 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 16777215 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 16777215) >>> 8 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & 16777215) >>> 16 & 255);
         out[3 + outPos] = (byte)(in[1 + inPos] & 16777215 & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 16777215) >>> 8 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & 16777215) >>> 16 & 255);
         out[6 + outPos] = (byte)(in[2 + inPos] & 16777215 & 255);
         out[7 + outPos] = (byte)((in[2 + inPos] & 16777215) >>> 8 & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & 16777215) >>> 16 & 255);
         out[9 + outPos] = (byte)(in[3 + inPos] & 16777215 & 255);
         out[10 + outPos] = (byte)((in[3 + inPos] & 16777215) >>> 8 & 255);
         out[11 + outPos] = (byte)((in[3 + inPos] & 16777215) >>> 16 & 255);
         out[12 + outPos] = (byte)(in[4 + inPos] & 16777215 & 255);
         out[13 + outPos] = (byte)((in[4 + inPos] & 16777215) >>> 8 & 255);
         out[14 + outPos] = (byte)((in[4 + inPos] & 16777215) >>> 16 & 255);
         out[15 + outPos] = (byte)(in[5 + inPos] & 16777215 & 255);
         out[16 + outPos] = (byte)((in[5 + inPos] & 16777215) >>> 8 & 255);
         out[17 + outPos] = (byte)((in[5 + inPos] & 16777215) >>> 16 & 255);
         out[18 + outPos] = (byte)(in[6 + inPos] & 16777215 & 255);
         out[19 + outPos] = (byte)((in[6 + inPos] & 16777215) >>> 8 & 255);
         out[20 + outPos] = (byte)((in[6 + inPos] & 16777215) >>> 16 & 255);
         out[21 + outPos] = (byte)(in[7 + inPos] & 16777215 & 255);
         out[22 + outPos] = (byte)((in[7 + inPos] & 16777215) >>> 8 & 255);
         out[23 + outPos] = (byte)((in[7 + inPos] & 16777215) >>> 16 & 255);
         out[24 + outPos] = (byte)(in[8 + inPos] & 16777215 & 255);
         out[25 + outPos] = (byte)((in[8 + inPos] & 16777215) >>> 8 & 255);
         out[26 + outPos] = (byte)((in[8 + inPos] & 16777215) >>> 16 & 255);
         out[27 + outPos] = (byte)(in[9 + inPos] & 16777215 & 255);
         out[28 + outPos] = (byte)((in[9 + inPos] & 16777215) >>> 8 & 255);
         out[29 + outPos] = (byte)((in[9 + inPos] & 16777215) >>> 16 & 255);
         out[30 + outPos] = (byte)(in[10 + inPos] & 16777215 & 255);
         out[31 + outPos] = (byte)((in[10 + inPos] & 16777215) >>> 8 & 255);
         out[32 + outPos] = (byte)((in[10 + inPos] & 16777215) >>> 16 & 255);
         out[33 + outPos] = (byte)(in[11 + inPos] & 16777215 & 255);
         out[34 + outPos] = (byte)((in[11 + inPos] & 16777215) >>> 8 & 255);
         out[35 + outPos] = (byte)((in[11 + inPos] & 16777215) >>> 16 & 255);
         out[36 + outPos] = (byte)(in[12 + inPos] & 16777215 & 255);
         out[37 + outPos] = (byte)((in[12 + inPos] & 16777215) >>> 8 & 255);
         out[38 + outPos] = (byte)((in[12 + inPos] & 16777215) >>> 16 & 255);
         out[39 + outPos] = (byte)(in[13 + inPos] & 16777215 & 255);
         out[40 + outPos] = (byte)((in[13 + inPos] & 16777215) >>> 8 & 255);
         out[41 + outPos] = (byte)((in[13 + inPos] & 16777215) >>> 16 & 255);
         out[42 + outPos] = (byte)(in[14 + inPos] & 16777215 & 255);
         out[43 + outPos] = (byte)((in[14 + inPos] & 16777215) >>> 8 & 255);
         out[44 + outPos] = (byte)((in[14 + inPos] & 16777215) >>> 16 & 255);
         out[45 + outPos] = (byte)(in[15 + inPos] & 16777215 & 255);
         out[46 + outPos] = (byte)((in[15 + inPos] & 16777215) >>> 8 & 255);
         out[47 + outPos] = (byte)((in[15 + inPos] & 16777215) >>> 16 & 255);
         out[48 + outPos] = (byte)(in[16 + inPos] & 16777215 & 255);
         out[49 + outPos] = (byte)((in[16 + inPos] & 16777215) >>> 8 & 255);
         out[50 + outPos] = (byte)((in[16 + inPos] & 16777215) >>> 16 & 255);
         out[51 + outPos] = (byte)(in[17 + inPos] & 16777215 & 255);
         out[52 + outPos] = (byte)((in[17 + inPos] & 16777215) >>> 8 & 255);
         out[53 + outPos] = (byte)((in[17 + inPos] & 16777215) >>> 16 & 255);
         out[54 + outPos] = (byte)(in[18 + inPos] & 16777215 & 255);
         out[55 + outPos] = (byte)((in[18 + inPos] & 16777215) >>> 8 & 255);
         out[56 + outPos] = (byte)((in[18 + inPos] & 16777215) >>> 16 & 255);
         out[57 + outPos] = (byte)(in[19 + inPos] & 16777215 & 255);
         out[58 + outPos] = (byte)((in[19 + inPos] & 16777215) >>> 8 & 255);
         out[59 + outPos] = (byte)((in[19 + inPos] & 16777215) >>> 16 & 255);
         out[60 + outPos] = (byte)(in[20 + inPos] & 16777215 & 255);
         out[61 + outPos] = (byte)((in[20 + inPos] & 16777215) >>> 8 & 255);
         out[62 + outPos] = (byte)((in[20 + inPos] & 16777215) >>> 16 & 255);
         out[63 + outPos] = (byte)(in[21 + inPos] & 16777215 & 255);
         out[64 + outPos] = (byte)((in[21 + inPos] & 16777215) >>> 8 & 255);
         out[65 + outPos] = (byte)((in[21 + inPos] & 16777215) >>> 16 & 255);
         out[66 + outPos] = (byte)(in[22 + inPos] & 16777215 & 255);
         out[67 + outPos] = (byte)((in[22 + inPos] & 16777215) >>> 8 & 255);
         out[68 + outPos] = (byte)((in[22 + inPos] & 16777215) >>> 16 & 255);
         out[69 + outPos] = (byte)(in[23 + inPos] & 16777215 & 255);
         out[70 + outPos] = (byte)((in[23 + inPos] & 16777215) >>> 8 & 255);
         out[71 + outPos] = (byte)((in[23 + inPos] & 16777215) >>> 16 & 255);
         out[72 + outPos] = (byte)(in[24 + inPos] & 16777215 & 255);
         out[73 + outPos] = (byte)((in[24 + inPos] & 16777215) >>> 8 & 255);
         out[74 + outPos] = (byte)((in[24 + inPos] & 16777215) >>> 16 & 255);
         out[75 + outPos] = (byte)(in[25 + inPos] & 16777215 & 255);
         out[76 + outPos] = (byte)((in[25 + inPos] & 16777215) >>> 8 & 255);
         out[77 + outPos] = (byte)((in[25 + inPos] & 16777215) >>> 16 & 255);
         out[78 + outPos] = (byte)(in[26 + inPos] & 16777215 & 255);
         out[79 + outPos] = (byte)((in[26 + inPos] & 16777215) >>> 8 & 255);
         out[80 + outPos] = (byte)((in[26 + inPos] & 16777215) >>> 16 & 255);
         out[81 + outPos] = (byte)(in[27 + inPos] & 16777215 & 255);
         out[82 + outPos] = (byte)((in[27 + inPos] & 16777215) >>> 8 & 255);
         out[83 + outPos] = (byte)((in[27 + inPos] & 16777215) >>> 16 & 255);
         out[84 + outPos] = (byte)(in[28 + inPos] & 16777215 & 255);
         out[85 + outPos] = (byte)((in[28 + inPos] & 16777215) >>> 8 & 255);
         out[86 + outPos] = (byte)((in[28 + inPos] & 16777215) >>> 16 & 255);
         out[87 + outPos] = (byte)(in[29 + inPos] & 16777215 & 255);
         out[88 + outPos] = (byte)((in[29 + inPos] & 16777215) >>> 8 & 255);
         out[89 + outPos] = (byte)((in[29 + inPos] & 16777215) >>> 16 & 255);
         out[90 + outPos] = (byte)(in[30 + inPos] & 16777215 & 255);
         out[91 + outPos] = (byte)((in[30 + inPos] & 16777215) >>> 8 & 255);
         out[92 + outPos] = (byte)((in[30 + inPos] & 16777215) >>> 16 & 255);
         out[93 + outPos] = (byte)(in[31 + inPos] & 16777215 & 255);
         out[94 + outPos] = (byte)((in[31 + inPos] & 16777215) >>> 8 & 255);
         out[95 + outPos] = (byte)((in[31 + inPos] & 16777215) >>> 16 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 16777215;
         out[1 + outPos] = in[3 + inPos] & 255 | in[4 + inPos] << 8 & '\uffff' | in[5 + inPos] << 16 & 16777215;
         out[2 + outPos] = in[6 + inPos] & 255 | in[7 + inPos] << 8 & '\uffff' | in[8 + inPos] << 16 & 16777215;
         out[3 + outPos] = in[9 + inPos] & 255 | in[10 + inPos] << 8 & '\uffff' | in[11 + inPos] << 16 & 16777215;
         out[4 + outPos] = in[12 + inPos] & 255 | in[13 + inPos] << 8 & '\uffff' | in[14 + inPos] << 16 & 16777215;
         out[5 + outPos] = in[15 + inPos] & 255 | in[16 + inPos] << 8 & '\uffff' | in[17 + inPos] << 16 & 16777215;
         out[6 + outPos] = in[18 + inPos] & 255 | in[19 + inPos] << 8 & '\uffff' | in[20 + inPos] << 16 & 16777215;
         out[7 + outPos] = in[21 + inPos] & 255 | in[22 + inPos] << 8 & '\uffff' | in[23 + inPos] << 16 & 16777215;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 16777215;
         out[1 + outPos] = in.get(3 + inPos) & 255 | in.get(4 + inPos) << 8 & '\uffff' | in.get(5 + inPos) << 16 & 16777215;
         out[2 + outPos] = in.get(6 + inPos) & 255 | in.get(7 + inPos) << 8 & '\uffff' | in.get(8 + inPos) << 16 & 16777215;
         out[3 + outPos] = in.get(9 + inPos) & 255 | in.get(10 + inPos) << 8 & '\uffff' | in.get(11 + inPos) << 16 & 16777215;
         out[4 + outPos] = in.get(12 + inPos) & 255 | in.get(13 + inPos) << 8 & '\uffff' | in.get(14 + inPos) << 16 & 16777215;
         out[5 + outPos] = in.get(15 + inPos) & 255 | in.get(16 + inPos) << 8 & '\uffff' | in.get(17 + inPos) << 16 & 16777215;
         out[6 + outPos] = in.get(18 + inPos) & 255 | in.get(19 + inPos) << 8 & '\uffff' | in.get(20 + inPos) << 16 & 16777215;
         out[7 + outPos] = in.get(21 + inPos) & 255 | in.get(22 + inPos) << 8 & '\uffff' | in.get(23 + inPos) << 16 & 16777215;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 16777215;
         out[1 + outPos] = in[3 + inPos] & 255 | in[4 + inPos] << 8 & '\uffff' | in[5 + inPos] << 16 & 16777215;
         out[2 + outPos] = in[6 + inPos] & 255 | in[7 + inPos] << 8 & '\uffff' | in[8 + inPos] << 16 & 16777215;
         out[3 + outPos] = in[9 + inPos] & 255 | in[10 + inPos] << 8 & '\uffff' | in[11 + inPos] << 16 & 16777215;
         out[4 + outPos] = in[12 + inPos] & 255 | in[13 + inPos] << 8 & '\uffff' | in[14 + inPos] << 16 & 16777215;
         out[5 + outPos] = in[15 + inPos] & 255 | in[16 + inPos] << 8 & '\uffff' | in[17 + inPos] << 16 & 16777215;
         out[6 + outPos] = in[18 + inPos] & 255 | in[19 + inPos] << 8 & '\uffff' | in[20 + inPos] << 16 & 16777215;
         out[7 + outPos] = in[21 + inPos] & 255 | in[22 + inPos] << 8 & '\uffff' | in[23 + inPos] << 16 & 16777215;
         out[8 + outPos] = in[24 + inPos] & 255 | in[25 + inPos] << 8 & '\uffff' | in[26 + inPos] << 16 & 16777215;
         out[9 + outPos] = in[27 + inPos] & 255 | in[28 + inPos] << 8 & '\uffff' | in[29 + inPos] << 16 & 16777215;
         out[10 + outPos] = in[30 + inPos] & 255 | in[31 + inPos] << 8 & '\uffff' | in[32 + inPos] << 16 & 16777215;
         out[11 + outPos] = in[33 + inPos] & 255 | in[34 + inPos] << 8 & '\uffff' | in[35 + inPos] << 16 & 16777215;
         out[12 + outPos] = in[36 + inPos] & 255 | in[37 + inPos] << 8 & '\uffff' | in[38 + inPos] << 16 & 16777215;
         out[13 + outPos] = in[39 + inPos] & 255 | in[40 + inPos] << 8 & '\uffff' | in[41 + inPos] << 16 & 16777215;
         out[14 + outPos] = in[42 + inPos] & 255 | in[43 + inPos] << 8 & '\uffff' | in[44 + inPos] << 16 & 16777215;
         out[15 + outPos] = in[45 + inPos] & 255 | in[46 + inPos] << 8 & '\uffff' | in[47 + inPos] << 16 & 16777215;
         out[16 + outPos] = in[48 + inPos] & 255 | in[49 + inPos] << 8 & '\uffff' | in[50 + inPos] << 16 & 16777215;
         out[17 + outPos] = in[51 + inPos] & 255 | in[52 + inPos] << 8 & '\uffff' | in[53 + inPos] << 16 & 16777215;
         out[18 + outPos] = in[54 + inPos] & 255 | in[55 + inPos] << 8 & '\uffff' | in[56 + inPos] << 16 & 16777215;
         out[19 + outPos] = in[57 + inPos] & 255 | in[58 + inPos] << 8 & '\uffff' | in[59 + inPos] << 16 & 16777215;
         out[20 + outPos] = in[60 + inPos] & 255 | in[61 + inPos] << 8 & '\uffff' | in[62 + inPos] << 16 & 16777215;
         out[21 + outPos] = in[63 + inPos] & 255 | in[64 + inPos] << 8 & '\uffff' | in[65 + inPos] << 16 & 16777215;
         out[22 + outPos] = in[66 + inPos] & 255 | in[67 + inPos] << 8 & '\uffff' | in[68 + inPos] << 16 & 16777215;
         out[23 + outPos] = in[69 + inPos] & 255 | in[70 + inPos] << 8 & '\uffff' | in[71 + inPos] << 16 & 16777215;
         out[24 + outPos] = in[72 + inPos] & 255 | in[73 + inPos] << 8 & '\uffff' | in[74 + inPos] << 16 & 16777215;
         out[25 + outPos] = in[75 + inPos] & 255 | in[76 + inPos] << 8 & '\uffff' | in[77 + inPos] << 16 & 16777215;
         out[26 + outPos] = in[78 + inPos] & 255 | in[79 + inPos] << 8 & '\uffff' | in[80 + inPos] << 16 & 16777215;
         out[27 + outPos] = in[81 + inPos] & 255 | in[82 + inPos] << 8 & '\uffff' | in[83 + inPos] << 16 & 16777215;
         out[28 + outPos] = in[84 + inPos] & 255 | in[85 + inPos] << 8 & '\uffff' | in[86 + inPos] << 16 & 16777215;
         out[29 + outPos] = in[87 + inPos] & 255 | in[88 + inPos] << 8 & '\uffff' | in[89 + inPos] << 16 & 16777215;
         out[30 + outPos] = in[90 + inPos] & 255 | in[91 + inPos] << 8 & '\uffff' | in[92 + inPos] << 16 & 16777215;
         out[31 + outPos] = in[93 + inPos] & 255 | in[94 + inPos] << 8 & '\uffff' | in[95 + inPos] << 16 & 16777215;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 16777215;
         out[1 + outPos] = in.get(3 + inPos) & 255 | in.get(4 + inPos) << 8 & '\uffff' | in.get(5 + inPos) << 16 & 16777215;
         out[2 + outPos] = in.get(6 + inPos) & 255 | in.get(7 + inPos) << 8 & '\uffff' | in.get(8 + inPos) << 16 & 16777215;
         out[3 + outPos] = in.get(9 + inPos) & 255 | in.get(10 + inPos) << 8 & '\uffff' | in.get(11 + inPos) << 16 & 16777215;
         out[4 + outPos] = in.get(12 + inPos) & 255 | in.get(13 + inPos) << 8 & '\uffff' | in.get(14 + inPos) << 16 & 16777215;
         out[5 + outPos] = in.get(15 + inPos) & 255 | in.get(16 + inPos) << 8 & '\uffff' | in.get(17 + inPos) << 16 & 16777215;
         out[6 + outPos] = in.get(18 + inPos) & 255 | in.get(19 + inPos) << 8 & '\uffff' | in.get(20 + inPos) << 16 & 16777215;
         out[7 + outPos] = in.get(21 + inPos) & 255 | in.get(22 + inPos) << 8 & '\uffff' | in.get(23 + inPos) << 16 & 16777215;
         out[8 + outPos] = in.get(24 + inPos) & 255 | in.get(25 + inPos) << 8 & '\uffff' | in.get(26 + inPos) << 16 & 16777215;
         out[9 + outPos] = in.get(27 + inPos) & 255 | in.get(28 + inPos) << 8 & '\uffff' | in.get(29 + inPos) << 16 & 16777215;
         out[10 + outPos] = in.get(30 + inPos) & 255 | in.get(31 + inPos) << 8 & '\uffff' | in.get(32 + inPos) << 16 & 16777215;
         out[11 + outPos] = in.get(33 + inPos) & 255 | in.get(34 + inPos) << 8 & '\uffff' | in.get(35 + inPos) << 16 & 16777215;
         out[12 + outPos] = in.get(36 + inPos) & 255 | in.get(37 + inPos) << 8 & '\uffff' | in.get(38 + inPos) << 16 & 16777215;
         out[13 + outPos] = in.get(39 + inPos) & 255 | in.get(40 + inPos) << 8 & '\uffff' | in.get(41 + inPos) << 16 & 16777215;
         out[14 + outPos] = in.get(42 + inPos) & 255 | in.get(43 + inPos) << 8 & '\uffff' | in.get(44 + inPos) << 16 & 16777215;
         out[15 + outPos] = in.get(45 + inPos) & 255 | in.get(46 + inPos) << 8 & '\uffff' | in.get(47 + inPos) << 16 & 16777215;
         out[16 + outPos] = in.get(48 + inPos) & 255 | in.get(49 + inPos) << 8 & '\uffff' | in.get(50 + inPos) << 16 & 16777215;
         out[17 + outPos] = in.get(51 + inPos) & 255 | in.get(52 + inPos) << 8 & '\uffff' | in.get(53 + inPos) << 16 & 16777215;
         out[18 + outPos] = in.get(54 + inPos) & 255 | in.get(55 + inPos) << 8 & '\uffff' | in.get(56 + inPos) << 16 & 16777215;
         out[19 + outPos] = in.get(57 + inPos) & 255 | in.get(58 + inPos) << 8 & '\uffff' | in.get(59 + inPos) << 16 & 16777215;
         out[20 + outPos] = in.get(60 + inPos) & 255 | in.get(61 + inPos) << 8 & '\uffff' | in.get(62 + inPos) << 16 & 16777215;
         out[21 + outPos] = in.get(63 + inPos) & 255 | in.get(64 + inPos) << 8 & '\uffff' | in.get(65 + inPos) << 16 & 16777215;
         out[22 + outPos] = in.get(66 + inPos) & 255 | in.get(67 + inPos) << 8 & '\uffff' | in.get(68 + inPos) << 16 & 16777215;
         out[23 + outPos] = in.get(69 + inPos) & 255 | in.get(70 + inPos) << 8 & '\uffff' | in.get(71 + inPos) << 16 & 16777215;
         out[24 + outPos] = in.get(72 + inPos) & 255 | in.get(73 + inPos) << 8 & '\uffff' | in.get(74 + inPos) << 16 & 16777215;
         out[25 + outPos] = in.get(75 + inPos) & 255 | in.get(76 + inPos) << 8 & '\uffff' | in.get(77 + inPos) << 16 & 16777215;
         out[26 + outPos] = in.get(78 + inPos) & 255 | in.get(79 + inPos) << 8 & '\uffff' | in.get(80 + inPos) << 16 & 16777215;
         out[27 + outPos] = in.get(81 + inPos) & 255 | in.get(82 + inPos) << 8 & '\uffff' | in.get(83 + inPos) << 16 & 16777215;
         out[28 + outPos] = in.get(84 + inPos) & 255 | in.get(85 + inPos) << 8 & '\uffff' | in.get(86 + inPos) << 16 & 16777215;
         out[29 + outPos] = in.get(87 + inPos) & 255 | in.get(88 + inPos) << 8 & '\uffff' | in.get(89 + inPos) << 16 & 16777215;
         out[30 + outPos] = in.get(90 + inPos) & 255 | in.get(91 + inPos) << 8 & '\uffff' | in.get(92 + inPos) << 16 & 16777215;
         out[31 + outPos] = in.get(93 + inPos) & 255 | in.get(94 + inPos) << 8 & '\uffff' | in.get(95 + inPos) << 16 & 16777215;
      }
   }

   private static final class Packer25 extends BytePacker {
      private Packer25() {
         super(25);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 33554431 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 33554431) >>> 8 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & 33554431) >>> 16 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & 33554431) >>> 24 | (in[1 + inPos] & 33554431) << 1) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 33554431) >>> 7 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & 33554431) >>> 15 & 255);
         out[6 + outPos] = (byte)(((in[1 + inPos] & 33554431) >>> 23 | (in[2 + inPos] & 33554431) << 2) & 255);
         out[7 + outPos] = (byte)((in[2 + inPos] & 33554431) >>> 6 & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & 33554431) >>> 14 & 255);
         out[9 + outPos] = (byte)(((in[2 + inPos] & 33554431) >>> 22 | (in[3 + inPos] & 33554431) << 3) & 255);
         out[10 + outPos] = (byte)((in[3 + inPos] & 33554431) >>> 5 & 255);
         out[11 + outPos] = (byte)((in[3 + inPos] & 33554431) >>> 13 & 255);
         out[12 + outPos] = (byte)(((in[3 + inPos] & 33554431) >>> 21 | (in[4 + inPos] & 33554431) << 4) & 255);
         out[13 + outPos] = (byte)((in[4 + inPos] & 33554431) >>> 4 & 255);
         out[14 + outPos] = (byte)((in[4 + inPos] & 33554431) >>> 12 & 255);
         out[15 + outPos] = (byte)(((in[4 + inPos] & 33554431) >>> 20 | (in[5 + inPos] & 33554431) << 5) & 255);
         out[16 + outPos] = (byte)((in[5 + inPos] & 33554431) >>> 3 & 255);
         out[17 + outPos] = (byte)((in[5 + inPos] & 33554431) >>> 11 & 255);
         out[18 + outPos] = (byte)(((in[5 + inPos] & 33554431) >>> 19 | (in[6 + inPos] & 33554431) << 6) & 255);
         out[19 + outPos] = (byte)((in[6 + inPos] & 33554431) >>> 2 & 255);
         out[20 + outPos] = (byte)((in[6 + inPos] & 33554431) >>> 10 & 255);
         out[21 + outPos] = (byte)(((in[6 + inPos] & 33554431) >>> 18 | (in[7 + inPos] & 33554431) << 7) & 255);
         out[22 + outPos] = (byte)((in[7 + inPos] & 33554431) >>> 1 & 255);
         out[23 + outPos] = (byte)((in[7 + inPos] & 33554431) >>> 9 & 255);
         out[24 + outPos] = (byte)((in[7 + inPos] & 33554431) >>> 17 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 33554431 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 33554431) >>> 8 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & 33554431) >>> 16 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & 33554431) >>> 24 | (in[1 + inPos] & 33554431) << 1) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 33554431) >>> 7 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & 33554431) >>> 15 & 255);
         out[6 + outPos] = (byte)(((in[1 + inPos] & 33554431) >>> 23 | (in[2 + inPos] & 33554431) << 2) & 255);
         out[7 + outPos] = (byte)((in[2 + inPos] & 33554431) >>> 6 & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & 33554431) >>> 14 & 255);
         out[9 + outPos] = (byte)(((in[2 + inPos] & 33554431) >>> 22 | (in[3 + inPos] & 33554431) << 3) & 255);
         out[10 + outPos] = (byte)((in[3 + inPos] & 33554431) >>> 5 & 255);
         out[11 + outPos] = (byte)((in[3 + inPos] & 33554431) >>> 13 & 255);
         out[12 + outPos] = (byte)(((in[3 + inPos] & 33554431) >>> 21 | (in[4 + inPos] & 33554431) << 4) & 255);
         out[13 + outPos] = (byte)((in[4 + inPos] & 33554431) >>> 4 & 255);
         out[14 + outPos] = (byte)((in[4 + inPos] & 33554431) >>> 12 & 255);
         out[15 + outPos] = (byte)(((in[4 + inPos] & 33554431) >>> 20 | (in[5 + inPos] & 33554431) << 5) & 255);
         out[16 + outPos] = (byte)((in[5 + inPos] & 33554431) >>> 3 & 255);
         out[17 + outPos] = (byte)((in[5 + inPos] & 33554431) >>> 11 & 255);
         out[18 + outPos] = (byte)(((in[5 + inPos] & 33554431) >>> 19 | (in[6 + inPos] & 33554431) << 6) & 255);
         out[19 + outPos] = (byte)((in[6 + inPos] & 33554431) >>> 2 & 255);
         out[20 + outPos] = (byte)((in[6 + inPos] & 33554431) >>> 10 & 255);
         out[21 + outPos] = (byte)(((in[6 + inPos] & 33554431) >>> 18 | (in[7 + inPos] & 33554431) << 7) & 255);
         out[22 + outPos] = (byte)((in[7 + inPos] & 33554431) >>> 1 & 255);
         out[23 + outPos] = (byte)((in[7 + inPos] & 33554431) >>> 9 & 255);
         out[24 + outPos] = (byte)((in[7 + inPos] & 33554431) >>> 17 & 255);
         out[25 + outPos] = (byte)(in[8 + inPos] & 33554431 & 255);
         out[26 + outPos] = (byte)((in[8 + inPos] & 33554431) >>> 8 & 255);
         out[27 + outPos] = (byte)((in[8 + inPos] & 33554431) >>> 16 & 255);
         out[28 + outPos] = (byte)(((in[8 + inPos] & 33554431) >>> 24 | (in[9 + inPos] & 33554431) << 1) & 255);
         out[29 + outPos] = (byte)((in[9 + inPos] & 33554431) >>> 7 & 255);
         out[30 + outPos] = (byte)((in[9 + inPos] & 33554431) >>> 15 & 255);
         out[31 + outPos] = (byte)(((in[9 + inPos] & 33554431) >>> 23 | (in[10 + inPos] & 33554431) << 2) & 255);
         out[32 + outPos] = (byte)((in[10 + inPos] & 33554431) >>> 6 & 255);
         out[33 + outPos] = (byte)((in[10 + inPos] & 33554431) >>> 14 & 255);
         out[34 + outPos] = (byte)(((in[10 + inPos] & 33554431) >>> 22 | (in[11 + inPos] & 33554431) << 3) & 255);
         out[35 + outPos] = (byte)((in[11 + inPos] & 33554431) >>> 5 & 255);
         out[36 + outPos] = (byte)((in[11 + inPos] & 33554431) >>> 13 & 255);
         out[37 + outPos] = (byte)(((in[11 + inPos] & 33554431) >>> 21 | (in[12 + inPos] & 33554431) << 4) & 255);
         out[38 + outPos] = (byte)((in[12 + inPos] & 33554431) >>> 4 & 255);
         out[39 + outPos] = (byte)((in[12 + inPos] & 33554431) >>> 12 & 255);
         out[40 + outPos] = (byte)(((in[12 + inPos] & 33554431) >>> 20 | (in[13 + inPos] & 33554431) << 5) & 255);
         out[41 + outPos] = (byte)((in[13 + inPos] & 33554431) >>> 3 & 255);
         out[42 + outPos] = (byte)((in[13 + inPos] & 33554431) >>> 11 & 255);
         out[43 + outPos] = (byte)(((in[13 + inPos] & 33554431) >>> 19 | (in[14 + inPos] & 33554431) << 6) & 255);
         out[44 + outPos] = (byte)((in[14 + inPos] & 33554431) >>> 2 & 255);
         out[45 + outPos] = (byte)((in[14 + inPos] & 33554431) >>> 10 & 255);
         out[46 + outPos] = (byte)(((in[14 + inPos] & 33554431) >>> 18 | (in[15 + inPos] & 33554431) << 7) & 255);
         out[47 + outPos] = (byte)((in[15 + inPos] & 33554431) >>> 1 & 255);
         out[48 + outPos] = (byte)((in[15 + inPos] & 33554431) >>> 9 & 255);
         out[49 + outPos] = (byte)((in[15 + inPos] & 33554431) >>> 17 & 255);
         out[50 + outPos] = (byte)(in[16 + inPos] & 33554431 & 255);
         out[51 + outPos] = (byte)((in[16 + inPos] & 33554431) >>> 8 & 255);
         out[52 + outPos] = (byte)((in[16 + inPos] & 33554431) >>> 16 & 255);
         out[53 + outPos] = (byte)(((in[16 + inPos] & 33554431) >>> 24 | (in[17 + inPos] & 33554431) << 1) & 255);
         out[54 + outPos] = (byte)((in[17 + inPos] & 33554431) >>> 7 & 255);
         out[55 + outPos] = (byte)((in[17 + inPos] & 33554431) >>> 15 & 255);
         out[56 + outPos] = (byte)(((in[17 + inPos] & 33554431) >>> 23 | (in[18 + inPos] & 33554431) << 2) & 255);
         out[57 + outPos] = (byte)((in[18 + inPos] & 33554431) >>> 6 & 255);
         out[58 + outPos] = (byte)((in[18 + inPos] & 33554431) >>> 14 & 255);
         out[59 + outPos] = (byte)(((in[18 + inPos] & 33554431) >>> 22 | (in[19 + inPos] & 33554431) << 3) & 255);
         out[60 + outPos] = (byte)((in[19 + inPos] & 33554431) >>> 5 & 255);
         out[61 + outPos] = (byte)((in[19 + inPos] & 33554431) >>> 13 & 255);
         out[62 + outPos] = (byte)(((in[19 + inPos] & 33554431) >>> 21 | (in[20 + inPos] & 33554431) << 4) & 255);
         out[63 + outPos] = (byte)((in[20 + inPos] & 33554431) >>> 4 & 255);
         out[64 + outPos] = (byte)((in[20 + inPos] & 33554431) >>> 12 & 255);
         out[65 + outPos] = (byte)(((in[20 + inPos] & 33554431) >>> 20 | (in[21 + inPos] & 33554431) << 5) & 255);
         out[66 + outPos] = (byte)((in[21 + inPos] & 33554431) >>> 3 & 255);
         out[67 + outPos] = (byte)((in[21 + inPos] & 33554431) >>> 11 & 255);
         out[68 + outPos] = (byte)(((in[21 + inPos] & 33554431) >>> 19 | (in[22 + inPos] & 33554431) << 6) & 255);
         out[69 + outPos] = (byte)((in[22 + inPos] & 33554431) >>> 2 & 255);
         out[70 + outPos] = (byte)((in[22 + inPos] & 33554431) >>> 10 & 255);
         out[71 + outPos] = (byte)(((in[22 + inPos] & 33554431) >>> 18 | (in[23 + inPos] & 33554431) << 7) & 255);
         out[72 + outPos] = (byte)((in[23 + inPos] & 33554431) >>> 1 & 255);
         out[73 + outPos] = (byte)((in[23 + inPos] & 33554431) >>> 9 & 255);
         out[74 + outPos] = (byte)((in[23 + inPos] & 33554431) >>> 17 & 255);
         out[75 + outPos] = (byte)(in[24 + inPos] & 33554431 & 255);
         out[76 + outPos] = (byte)((in[24 + inPos] & 33554431) >>> 8 & 255);
         out[77 + outPos] = (byte)((in[24 + inPos] & 33554431) >>> 16 & 255);
         out[78 + outPos] = (byte)(((in[24 + inPos] & 33554431) >>> 24 | (in[25 + inPos] & 33554431) << 1) & 255);
         out[79 + outPos] = (byte)((in[25 + inPos] & 33554431) >>> 7 & 255);
         out[80 + outPos] = (byte)((in[25 + inPos] & 33554431) >>> 15 & 255);
         out[81 + outPos] = (byte)(((in[25 + inPos] & 33554431) >>> 23 | (in[26 + inPos] & 33554431) << 2) & 255);
         out[82 + outPos] = (byte)((in[26 + inPos] & 33554431) >>> 6 & 255);
         out[83 + outPos] = (byte)((in[26 + inPos] & 33554431) >>> 14 & 255);
         out[84 + outPos] = (byte)(((in[26 + inPos] & 33554431) >>> 22 | (in[27 + inPos] & 33554431) << 3) & 255);
         out[85 + outPos] = (byte)((in[27 + inPos] & 33554431) >>> 5 & 255);
         out[86 + outPos] = (byte)((in[27 + inPos] & 33554431) >>> 13 & 255);
         out[87 + outPos] = (byte)(((in[27 + inPos] & 33554431) >>> 21 | (in[28 + inPos] & 33554431) << 4) & 255);
         out[88 + outPos] = (byte)((in[28 + inPos] & 33554431) >>> 4 & 255);
         out[89 + outPos] = (byte)((in[28 + inPos] & 33554431) >>> 12 & 255);
         out[90 + outPos] = (byte)(((in[28 + inPos] & 33554431) >>> 20 | (in[29 + inPos] & 33554431) << 5) & 255);
         out[91 + outPos] = (byte)((in[29 + inPos] & 33554431) >>> 3 & 255);
         out[92 + outPos] = (byte)((in[29 + inPos] & 33554431) >>> 11 & 255);
         out[93 + outPos] = (byte)(((in[29 + inPos] & 33554431) >>> 19 | (in[30 + inPos] & 33554431) << 6) & 255);
         out[94 + outPos] = (byte)((in[30 + inPos] & 33554431) >>> 2 & 255);
         out[95 + outPos] = (byte)((in[30 + inPos] & 33554431) >>> 10 & 255);
         out[96 + outPos] = (byte)(((in[30 + inPos] & 33554431) >>> 18 | (in[31 + inPos] & 33554431) << 7) & 255);
         out[97 + outPos] = (byte)((in[31 + inPos] & 33554431) >>> 1 & 255);
         out[98 + outPos] = (byte)((in[31 + inPos] & 33554431) >>> 9 & 255);
         out[99 + outPos] = (byte)((in[31 + inPos] & 33554431) >>> 17 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 16777215 | in[3 + inPos] << 24 & 33554431;
         out[1 + outPos] = in[3 + inPos] >> 1 & 127 | in[4 + inPos] << 7 & 32767 | in[5 + inPos] << 15 & 8388607 | in[6 + inPos] << 23 & 33554431;
         out[2 + outPos] = in[6 + inPos] >> 2 & 63 | in[7 + inPos] << 6 & 16383 | in[8 + inPos] << 14 & 4194303 | in[9 + inPos] << 22 & 33554431;
         out[3 + outPos] = in[9 + inPos] >> 3 & 31 | in[10 + inPos] << 5 & 8191 | in[11 + inPos] << 13 & 2097151 | in[12 + inPos] << 21 & 33554431;
         out[4 + outPos] = in[12 + inPos] >> 4 & 15 | in[13 + inPos] << 4 & 4095 | in[14 + inPos] << 12 & 1048575 | in[15 + inPos] << 20 & 33554431;
         out[5 + outPos] = in[15 + inPos] >> 5 & 7 | in[16 + inPos] << 3 & 2047 | in[17 + inPos] << 11 & 524287 | in[18 + inPos] << 19 & 33554431;
         out[6 + outPos] = in[18 + inPos] >> 6 & 3 | in[19 + inPos] << 2 & 1023 | in[20 + inPos] << 10 & 262143 | in[21 + inPos] << 18 & 33554431;
         out[7 + outPos] = in[21 + inPos] >> 7 & 1 | in[22 + inPos] << 1 & 511 | in[23 + inPos] << 9 & 131071 | in[24 + inPos] << 17 & 33554431;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 16777215 | in.get(3 + inPos) << 24 & 33554431;
         out[1 + outPos] = in.get(3 + inPos) >> 1 & 127 | in.get(4 + inPos) << 7 & 32767 | in.get(5 + inPos) << 15 & 8388607 | in.get(6 + inPos) << 23 & 33554431;
         out[2 + outPos] = in.get(6 + inPos) >> 2 & 63 | in.get(7 + inPos) << 6 & 16383 | in.get(8 + inPos) << 14 & 4194303 | in.get(9 + inPos) << 22 & 33554431;
         out[3 + outPos] = in.get(9 + inPos) >> 3 & 31 | in.get(10 + inPos) << 5 & 8191 | in.get(11 + inPos) << 13 & 2097151 | in.get(12 + inPos) << 21 & 33554431;
         out[4 + outPos] = in.get(12 + inPos) >> 4 & 15 | in.get(13 + inPos) << 4 & 4095 | in.get(14 + inPos) << 12 & 1048575 | in.get(15 + inPos) << 20 & 33554431;
         out[5 + outPos] = in.get(15 + inPos) >> 5 & 7 | in.get(16 + inPos) << 3 & 2047 | in.get(17 + inPos) << 11 & 524287 | in.get(18 + inPos) << 19 & 33554431;
         out[6 + outPos] = in.get(18 + inPos) >> 6 & 3 | in.get(19 + inPos) << 2 & 1023 | in.get(20 + inPos) << 10 & 262143 | in.get(21 + inPos) << 18 & 33554431;
         out[7 + outPos] = in.get(21 + inPos) >> 7 & 1 | in.get(22 + inPos) << 1 & 511 | in.get(23 + inPos) << 9 & 131071 | in.get(24 + inPos) << 17 & 33554431;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 16777215 | in[3 + inPos] << 24 & 33554431;
         out[1 + outPos] = in[3 + inPos] >> 1 & 127 | in[4 + inPos] << 7 & 32767 | in[5 + inPos] << 15 & 8388607 | in[6 + inPos] << 23 & 33554431;
         out[2 + outPos] = in[6 + inPos] >> 2 & 63 | in[7 + inPos] << 6 & 16383 | in[8 + inPos] << 14 & 4194303 | in[9 + inPos] << 22 & 33554431;
         out[3 + outPos] = in[9 + inPos] >> 3 & 31 | in[10 + inPos] << 5 & 8191 | in[11 + inPos] << 13 & 2097151 | in[12 + inPos] << 21 & 33554431;
         out[4 + outPos] = in[12 + inPos] >> 4 & 15 | in[13 + inPos] << 4 & 4095 | in[14 + inPos] << 12 & 1048575 | in[15 + inPos] << 20 & 33554431;
         out[5 + outPos] = in[15 + inPos] >> 5 & 7 | in[16 + inPos] << 3 & 2047 | in[17 + inPos] << 11 & 524287 | in[18 + inPos] << 19 & 33554431;
         out[6 + outPos] = in[18 + inPos] >> 6 & 3 | in[19 + inPos] << 2 & 1023 | in[20 + inPos] << 10 & 262143 | in[21 + inPos] << 18 & 33554431;
         out[7 + outPos] = in[21 + inPos] >> 7 & 1 | in[22 + inPos] << 1 & 511 | in[23 + inPos] << 9 & 131071 | in[24 + inPos] << 17 & 33554431;
         out[8 + outPos] = in[25 + inPos] & 255 | in[26 + inPos] << 8 & '\uffff' | in[27 + inPos] << 16 & 16777215 | in[28 + inPos] << 24 & 33554431;
         out[9 + outPos] = in[28 + inPos] >> 1 & 127 | in[29 + inPos] << 7 & 32767 | in[30 + inPos] << 15 & 8388607 | in[31 + inPos] << 23 & 33554431;
         out[10 + outPos] = in[31 + inPos] >> 2 & 63 | in[32 + inPos] << 6 & 16383 | in[33 + inPos] << 14 & 4194303 | in[34 + inPos] << 22 & 33554431;
         out[11 + outPos] = in[34 + inPos] >> 3 & 31 | in[35 + inPos] << 5 & 8191 | in[36 + inPos] << 13 & 2097151 | in[37 + inPos] << 21 & 33554431;
         out[12 + outPos] = in[37 + inPos] >> 4 & 15 | in[38 + inPos] << 4 & 4095 | in[39 + inPos] << 12 & 1048575 | in[40 + inPos] << 20 & 33554431;
         out[13 + outPos] = in[40 + inPos] >> 5 & 7 | in[41 + inPos] << 3 & 2047 | in[42 + inPos] << 11 & 524287 | in[43 + inPos] << 19 & 33554431;
         out[14 + outPos] = in[43 + inPos] >> 6 & 3 | in[44 + inPos] << 2 & 1023 | in[45 + inPos] << 10 & 262143 | in[46 + inPos] << 18 & 33554431;
         out[15 + outPos] = in[46 + inPos] >> 7 & 1 | in[47 + inPos] << 1 & 511 | in[48 + inPos] << 9 & 131071 | in[49 + inPos] << 17 & 33554431;
         out[16 + outPos] = in[50 + inPos] & 255 | in[51 + inPos] << 8 & '\uffff' | in[52 + inPos] << 16 & 16777215 | in[53 + inPos] << 24 & 33554431;
         out[17 + outPos] = in[53 + inPos] >> 1 & 127 | in[54 + inPos] << 7 & 32767 | in[55 + inPos] << 15 & 8388607 | in[56 + inPos] << 23 & 33554431;
         out[18 + outPos] = in[56 + inPos] >> 2 & 63 | in[57 + inPos] << 6 & 16383 | in[58 + inPos] << 14 & 4194303 | in[59 + inPos] << 22 & 33554431;
         out[19 + outPos] = in[59 + inPos] >> 3 & 31 | in[60 + inPos] << 5 & 8191 | in[61 + inPos] << 13 & 2097151 | in[62 + inPos] << 21 & 33554431;
         out[20 + outPos] = in[62 + inPos] >> 4 & 15 | in[63 + inPos] << 4 & 4095 | in[64 + inPos] << 12 & 1048575 | in[65 + inPos] << 20 & 33554431;
         out[21 + outPos] = in[65 + inPos] >> 5 & 7 | in[66 + inPos] << 3 & 2047 | in[67 + inPos] << 11 & 524287 | in[68 + inPos] << 19 & 33554431;
         out[22 + outPos] = in[68 + inPos] >> 6 & 3 | in[69 + inPos] << 2 & 1023 | in[70 + inPos] << 10 & 262143 | in[71 + inPos] << 18 & 33554431;
         out[23 + outPos] = in[71 + inPos] >> 7 & 1 | in[72 + inPos] << 1 & 511 | in[73 + inPos] << 9 & 131071 | in[74 + inPos] << 17 & 33554431;
         out[24 + outPos] = in[75 + inPos] & 255 | in[76 + inPos] << 8 & '\uffff' | in[77 + inPos] << 16 & 16777215 | in[78 + inPos] << 24 & 33554431;
         out[25 + outPos] = in[78 + inPos] >> 1 & 127 | in[79 + inPos] << 7 & 32767 | in[80 + inPos] << 15 & 8388607 | in[81 + inPos] << 23 & 33554431;
         out[26 + outPos] = in[81 + inPos] >> 2 & 63 | in[82 + inPos] << 6 & 16383 | in[83 + inPos] << 14 & 4194303 | in[84 + inPos] << 22 & 33554431;
         out[27 + outPos] = in[84 + inPos] >> 3 & 31 | in[85 + inPos] << 5 & 8191 | in[86 + inPos] << 13 & 2097151 | in[87 + inPos] << 21 & 33554431;
         out[28 + outPos] = in[87 + inPos] >> 4 & 15 | in[88 + inPos] << 4 & 4095 | in[89 + inPos] << 12 & 1048575 | in[90 + inPos] << 20 & 33554431;
         out[29 + outPos] = in[90 + inPos] >> 5 & 7 | in[91 + inPos] << 3 & 2047 | in[92 + inPos] << 11 & 524287 | in[93 + inPos] << 19 & 33554431;
         out[30 + outPos] = in[93 + inPos] >> 6 & 3 | in[94 + inPos] << 2 & 1023 | in[95 + inPos] << 10 & 262143 | in[96 + inPos] << 18 & 33554431;
         out[31 + outPos] = in[96 + inPos] >> 7 & 1 | in[97 + inPos] << 1 & 511 | in[98 + inPos] << 9 & 131071 | in[99 + inPos] << 17 & 33554431;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 16777215 | in.get(3 + inPos) << 24 & 33554431;
         out[1 + outPos] = in.get(3 + inPos) >> 1 & 127 | in.get(4 + inPos) << 7 & 32767 | in.get(5 + inPos) << 15 & 8388607 | in.get(6 + inPos) << 23 & 33554431;
         out[2 + outPos] = in.get(6 + inPos) >> 2 & 63 | in.get(7 + inPos) << 6 & 16383 | in.get(8 + inPos) << 14 & 4194303 | in.get(9 + inPos) << 22 & 33554431;
         out[3 + outPos] = in.get(9 + inPos) >> 3 & 31 | in.get(10 + inPos) << 5 & 8191 | in.get(11 + inPos) << 13 & 2097151 | in.get(12 + inPos) << 21 & 33554431;
         out[4 + outPos] = in.get(12 + inPos) >> 4 & 15 | in.get(13 + inPos) << 4 & 4095 | in.get(14 + inPos) << 12 & 1048575 | in.get(15 + inPos) << 20 & 33554431;
         out[5 + outPos] = in.get(15 + inPos) >> 5 & 7 | in.get(16 + inPos) << 3 & 2047 | in.get(17 + inPos) << 11 & 524287 | in.get(18 + inPos) << 19 & 33554431;
         out[6 + outPos] = in.get(18 + inPos) >> 6 & 3 | in.get(19 + inPos) << 2 & 1023 | in.get(20 + inPos) << 10 & 262143 | in.get(21 + inPos) << 18 & 33554431;
         out[7 + outPos] = in.get(21 + inPos) >> 7 & 1 | in.get(22 + inPos) << 1 & 511 | in.get(23 + inPos) << 9 & 131071 | in.get(24 + inPos) << 17 & 33554431;
         out[8 + outPos] = in.get(25 + inPos) & 255 | in.get(26 + inPos) << 8 & '\uffff' | in.get(27 + inPos) << 16 & 16777215 | in.get(28 + inPos) << 24 & 33554431;
         out[9 + outPos] = in.get(28 + inPos) >> 1 & 127 | in.get(29 + inPos) << 7 & 32767 | in.get(30 + inPos) << 15 & 8388607 | in.get(31 + inPos) << 23 & 33554431;
         out[10 + outPos] = in.get(31 + inPos) >> 2 & 63 | in.get(32 + inPos) << 6 & 16383 | in.get(33 + inPos) << 14 & 4194303 | in.get(34 + inPos) << 22 & 33554431;
         out[11 + outPos] = in.get(34 + inPos) >> 3 & 31 | in.get(35 + inPos) << 5 & 8191 | in.get(36 + inPos) << 13 & 2097151 | in.get(37 + inPos) << 21 & 33554431;
         out[12 + outPos] = in.get(37 + inPos) >> 4 & 15 | in.get(38 + inPos) << 4 & 4095 | in.get(39 + inPos) << 12 & 1048575 | in.get(40 + inPos) << 20 & 33554431;
         out[13 + outPos] = in.get(40 + inPos) >> 5 & 7 | in.get(41 + inPos) << 3 & 2047 | in.get(42 + inPos) << 11 & 524287 | in.get(43 + inPos) << 19 & 33554431;
         out[14 + outPos] = in.get(43 + inPos) >> 6 & 3 | in.get(44 + inPos) << 2 & 1023 | in.get(45 + inPos) << 10 & 262143 | in.get(46 + inPos) << 18 & 33554431;
         out[15 + outPos] = in.get(46 + inPos) >> 7 & 1 | in.get(47 + inPos) << 1 & 511 | in.get(48 + inPos) << 9 & 131071 | in.get(49 + inPos) << 17 & 33554431;
         out[16 + outPos] = in.get(50 + inPos) & 255 | in.get(51 + inPos) << 8 & '\uffff' | in.get(52 + inPos) << 16 & 16777215 | in.get(53 + inPos) << 24 & 33554431;
         out[17 + outPos] = in.get(53 + inPos) >> 1 & 127 | in.get(54 + inPos) << 7 & 32767 | in.get(55 + inPos) << 15 & 8388607 | in.get(56 + inPos) << 23 & 33554431;
         out[18 + outPos] = in.get(56 + inPos) >> 2 & 63 | in.get(57 + inPos) << 6 & 16383 | in.get(58 + inPos) << 14 & 4194303 | in.get(59 + inPos) << 22 & 33554431;
         out[19 + outPos] = in.get(59 + inPos) >> 3 & 31 | in.get(60 + inPos) << 5 & 8191 | in.get(61 + inPos) << 13 & 2097151 | in.get(62 + inPos) << 21 & 33554431;
         out[20 + outPos] = in.get(62 + inPos) >> 4 & 15 | in.get(63 + inPos) << 4 & 4095 | in.get(64 + inPos) << 12 & 1048575 | in.get(65 + inPos) << 20 & 33554431;
         out[21 + outPos] = in.get(65 + inPos) >> 5 & 7 | in.get(66 + inPos) << 3 & 2047 | in.get(67 + inPos) << 11 & 524287 | in.get(68 + inPos) << 19 & 33554431;
         out[22 + outPos] = in.get(68 + inPos) >> 6 & 3 | in.get(69 + inPos) << 2 & 1023 | in.get(70 + inPos) << 10 & 262143 | in.get(71 + inPos) << 18 & 33554431;
         out[23 + outPos] = in.get(71 + inPos) >> 7 & 1 | in.get(72 + inPos) << 1 & 511 | in.get(73 + inPos) << 9 & 131071 | in.get(74 + inPos) << 17 & 33554431;
         out[24 + outPos] = in.get(75 + inPos) & 255 | in.get(76 + inPos) << 8 & '\uffff' | in.get(77 + inPos) << 16 & 16777215 | in.get(78 + inPos) << 24 & 33554431;
         out[25 + outPos] = in.get(78 + inPos) >> 1 & 127 | in.get(79 + inPos) << 7 & 32767 | in.get(80 + inPos) << 15 & 8388607 | in.get(81 + inPos) << 23 & 33554431;
         out[26 + outPos] = in.get(81 + inPos) >> 2 & 63 | in.get(82 + inPos) << 6 & 16383 | in.get(83 + inPos) << 14 & 4194303 | in.get(84 + inPos) << 22 & 33554431;
         out[27 + outPos] = in.get(84 + inPos) >> 3 & 31 | in.get(85 + inPos) << 5 & 8191 | in.get(86 + inPos) << 13 & 2097151 | in.get(87 + inPos) << 21 & 33554431;
         out[28 + outPos] = in.get(87 + inPos) >> 4 & 15 | in.get(88 + inPos) << 4 & 4095 | in.get(89 + inPos) << 12 & 1048575 | in.get(90 + inPos) << 20 & 33554431;
         out[29 + outPos] = in.get(90 + inPos) >> 5 & 7 | in.get(91 + inPos) << 3 & 2047 | in.get(92 + inPos) << 11 & 524287 | in.get(93 + inPos) << 19 & 33554431;
         out[30 + outPos] = in.get(93 + inPos) >> 6 & 3 | in.get(94 + inPos) << 2 & 1023 | in.get(95 + inPos) << 10 & 262143 | in.get(96 + inPos) << 18 & 33554431;
         out[31 + outPos] = in.get(96 + inPos) >> 7 & 1 | in.get(97 + inPos) << 1 & 511 | in.get(98 + inPos) << 9 & 131071 | in.get(99 + inPos) << 17 & 33554431;
      }
   }

   private static final class Packer26 extends BytePacker {
      private Packer26() {
         super(26);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 67108863 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 67108863) >>> 8 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & 67108863) >>> 16 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & 67108863) >>> 24 | (in[1 + inPos] & 67108863) << 2) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 67108863) >>> 6 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & 67108863) >>> 14 & 255);
         out[6 + outPos] = (byte)(((in[1 + inPos] & 67108863) >>> 22 | (in[2 + inPos] & 67108863) << 4) & 255);
         out[7 + outPos] = (byte)((in[2 + inPos] & 67108863) >>> 4 & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & 67108863) >>> 12 & 255);
         out[9 + outPos] = (byte)(((in[2 + inPos] & 67108863) >>> 20 | (in[3 + inPos] & 67108863) << 6) & 255);
         out[10 + outPos] = (byte)((in[3 + inPos] & 67108863) >>> 2 & 255);
         out[11 + outPos] = (byte)((in[3 + inPos] & 67108863) >>> 10 & 255);
         out[12 + outPos] = (byte)((in[3 + inPos] & 67108863) >>> 18 & 255);
         out[13 + outPos] = (byte)(in[4 + inPos] & 67108863 & 255);
         out[14 + outPos] = (byte)((in[4 + inPos] & 67108863) >>> 8 & 255);
         out[15 + outPos] = (byte)((in[4 + inPos] & 67108863) >>> 16 & 255);
         out[16 + outPos] = (byte)(((in[4 + inPos] & 67108863) >>> 24 | (in[5 + inPos] & 67108863) << 2) & 255);
         out[17 + outPos] = (byte)((in[5 + inPos] & 67108863) >>> 6 & 255);
         out[18 + outPos] = (byte)((in[5 + inPos] & 67108863) >>> 14 & 255);
         out[19 + outPos] = (byte)(((in[5 + inPos] & 67108863) >>> 22 | (in[6 + inPos] & 67108863) << 4) & 255);
         out[20 + outPos] = (byte)((in[6 + inPos] & 67108863) >>> 4 & 255);
         out[21 + outPos] = (byte)((in[6 + inPos] & 67108863) >>> 12 & 255);
         out[22 + outPos] = (byte)(((in[6 + inPos] & 67108863) >>> 20 | (in[7 + inPos] & 67108863) << 6) & 255);
         out[23 + outPos] = (byte)((in[7 + inPos] & 67108863) >>> 2 & 255);
         out[24 + outPos] = (byte)((in[7 + inPos] & 67108863) >>> 10 & 255);
         out[25 + outPos] = (byte)((in[7 + inPos] & 67108863) >>> 18 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 67108863 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 67108863) >>> 8 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & 67108863) >>> 16 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & 67108863) >>> 24 | (in[1 + inPos] & 67108863) << 2) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 67108863) >>> 6 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & 67108863) >>> 14 & 255);
         out[6 + outPos] = (byte)(((in[1 + inPos] & 67108863) >>> 22 | (in[2 + inPos] & 67108863) << 4) & 255);
         out[7 + outPos] = (byte)((in[2 + inPos] & 67108863) >>> 4 & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & 67108863) >>> 12 & 255);
         out[9 + outPos] = (byte)(((in[2 + inPos] & 67108863) >>> 20 | (in[3 + inPos] & 67108863) << 6) & 255);
         out[10 + outPos] = (byte)((in[3 + inPos] & 67108863) >>> 2 & 255);
         out[11 + outPos] = (byte)((in[3 + inPos] & 67108863) >>> 10 & 255);
         out[12 + outPos] = (byte)((in[3 + inPos] & 67108863) >>> 18 & 255);
         out[13 + outPos] = (byte)(in[4 + inPos] & 67108863 & 255);
         out[14 + outPos] = (byte)((in[4 + inPos] & 67108863) >>> 8 & 255);
         out[15 + outPos] = (byte)((in[4 + inPos] & 67108863) >>> 16 & 255);
         out[16 + outPos] = (byte)(((in[4 + inPos] & 67108863) >>> 24 | (in[5 + inPos] & 67108863) << 2) & 255);
         out[17 + outPos] = (byte)((in[5 + inPos] & 67108863) >>> 6 & 255);
         out[18 + outPos] = (byte)((in[5 + inPos] & 67108863) >>> 14 & 255);
         out[19 + outPos] = (byte)(((in[5 + inPos] & 67108863) >>> 22 | (in[6 + inPos] & 67108863) << 4) & 255);
         out[20 + outPos] = (byte)((in[6 + inPos] & 67108863) >>> 4 & 255);
         out[21 + outPos] = (byte)((in[6 + inPos] & 67108863) >>> 12 & 255);
         out[22 + outPos] = (byte)(((in[6 + inPos] & 67108863) >>> 20 | (in[7 + inPos] & 67108863) << 6) & 255);
         out[23 + outPos] = (byte)((in[7 + inPos] & 67108863) >>> 2 & 255);
         out[24 + outPos] = (byte)((in[7 + inPos] & 67108863) >>> 10 & 255);
         out[25 + outPos] = (byte)((in[7 + inPos] & 67108863) >>> 18 & 255);
         out[26 + outPos] = (byte)(in[8 + inPos] & 67108863 & 255);
         out[27 + outPos] = (byte)((in[8 + inPos] & 67108863) >>> 8 & 255);
         out[28 + outPos] = (byte)((in[8 + inPos] & 67108863) >>> 16 & 255);
         out[29 + outPos] = (byte)(((in[8 + inPos] & 67108863) >>> 24 | (in[9 + inPos] & 67108863) << 2) & 255);
         out[30 + outPos] = (byte)((in[9 + inPos] & 67108863) >>> 6 & 255);
         out[31 + outPos] = (byte)((in[9 + inPos] & 67108863) >>> 14 & 255);
         out[32 + outPos] = (byte)(((in[9 + inPos] & 67108863) >>> 22 | (in[10 + inPos] & 67108863) << 4) & 255);
         out[33 + outPos] = (byte)((in[10 + inPos] & 67108863) >>> 4 & 255);
         out[34 + outPos] = (byte)((in[10 + inPos] & 67108863) >>> 12 & 255);
         out[35 + outPos] = (byte)(((in[10 + inPos] & 67108863) >>> 20 | (in[11 + inPos] & 67108863) << 6) & 255);
         out[36 + outPos] = (byte)((in[11 + inPos] & 67108863) >>> 2 & 255);
         out[37 + outPos] = (byte)((in[11 + inPos] & 67108863) >>> 10 & 255);
         out[38 + outPos] = (byte)((in[11 + inPos] & 67108863) >>> 18 & 255);
         out[39 + outPos] = (byte)(in[12 + inPos] & 67108863 & 255);
         out[40 + outPos] = (byte)((in[12 + inPos] & 67108863) >>> 8 & 255);
         out[41 + outPos] = (byte)((in[12 + inPos] & 67108863) >>> 16 & 255);
         out[42 + outPos] = (byte)(((in[12 + inPos] & 67108863) >>> 24 | (in[13 + inPos] & 67108863) << 2) & 255);
         out[43 + outPos] = (byte)((in[13 + inPos] & 67108863) >>> 6 & 255);
         out[44 + outPos] = (byte)((in[13 + inPos] & 67108863) >>> 14 & 255);
         out[45 + outPos] = (byte)(((in[13 + inPos] & 67108863) >>> 22 | (in[14 + inPos] & 67108863) << 4) & 255);
         out[46 + outPos] = (byte)((in[14 + inPos] & 67108863) >>> 4 & 255);
         out[47 + outPos] = (byte)((in[14 + inPos] & 67108863) >>> 12 & 255);
         out[48 + outPos] = (byte)(((in[14 + inPos] & 67108863) >>> 20 | (in[15 + inPos] & 67108863) << 6) & 255);
         out[49 + outPos] = (byte)((in[15 + inPos] & 67108863) >>> 2 & 255);
         out[50 + outPos] = (byte)((in[15 + inPos] & 67108863) >>> 10 & 255);
         out[51 + outPos] = (byte)((in[15 + inPos] & 67108863) >>> 18 & 255);
         out[52 + outPos] = (byte)(in[16 + inPos] & 67108863 & 255);
         out[53 + outPos] = (byte)((in[16 + inPos] & 67108863) >>> 8 & 255);
         out[54 + outPos] = (byte)((in[16 + inPos] & 67108863) >>> 16 & 255);
         out[55 + outPos] = (byte)(((in[16 + inPos] & 67108863) >>> 24 | (in[17 + inPos] & 67108863) << 2) & 255);
         out[56 + outPos] = (byte)((in[17 + inPos] & 67108863) >>> 6 & 255);
         out[57 + outPos] = (byte)((in[17 + inPos] & 67108863) >>> 14 & 255);
         out[58 + outPos] = (byte)(((in[17 + inPos] & 67108863) >>> 22 | (in[18 + inPos] & 67108863) << 4) & 255);
         out[59 + outPos] = (byte)((in[18 + inPos] & 67108863) >>> 4 & 255);
         out[60 + outPos] = (byte)((in[18 + inPos] & 67108863) >>> 12 & 255);
         out[61 + outPos] = (byte)(((in[18 + inPos] & 67108863) >>> 20 | (in[19 + inPos] & 67108863) << 6) & 255);
         out[62 + outPos] = (byte)((in[19 + inPos] & 67108863) >>> 2 & 255);
         out[63 + outPos] = (byte)((in[19 + inPos] & 67108863) >>> 10 & 255);
         out[64 + outPos] = (byte)((in[19 + inPos] & 67108863) >>> 18 & 255);
         out[65 + outPos] = (byte)(in[20 + inPos] & 67108863 & 255);
         out[66 + outPos] = (byte)((in[20 + inPos] & 67108863) >>> 8 & 255);
         out[67 + outPos] = (byte)((in[20 + inPos] & 67108863) >>> 16 & 255);
         out[68 + outPos] = (byte)(((in[20 + inPos] & 67108863) >>> 24 | (in[21 + inPos] & 67108863) << 2) & 255);
         out[69 + outPos] = (byte)((in[21 + inPos] & 67108863) >>> 6 & 255);
         out[70 + outPos] = (byte)((in[21 + inPos] & 67108863) >>> 14 & 255);
         out[71 + outPos] = (byte)(((in[21 + inPos] & 67108863) >>> 22 | (in[22 + inPos] & 67108863) << 4) & 255);
         out[72 + outPos] = (byte)((in[22 + inPos] & 67108863) >>> 4 & 255);
         out[73 + outPos] = (byte)((in[22 + inPos] & 67108863) >>> 12 & 255);
         out[74 + outPos] = (byte)(((in[22 + inPos] & 67108863) >>> 20 | (in[23 + inPos] & 67108863) << 6) & 255);
         out[75 + outPos] = (byte)((in[23 + inPos] & 67108863) >>> 2 & 255);
         out[76 + outPos] = (byte)((in[23 + inPos] & 67108863) >>> 10 & 255);
         out[77 + outPos] = (byte)((in[23 + inPos] & 67108863) >>> 18 & 255);
         out[78 + outPos] = (byte)(in[24 + inPos] & 67108863 & 255);
         out[79 + outPos] = (byte)((in[24 + inPos] & 67108863) >>> 8 & 255);
         out[80 + outPos] = (byte)((in[24 + inPos] & 67108863) >>> 16 & 255);
         out[81 + outPos] = (byte)(((in[24 + inPos] & 67108863) >>> 24 | (in[25 + inPos] & 67108863) << 2) & 255);
         out[82 + outPos] = (byte)((in[25 + inPos] & 67108863) >>> 6 & 255);
         out[83 + outPos] = (byte)((in[25 + inPos] & 67108863) >>> 14 & 255);
         out[84 + outPos] = (byte)(((in[25 + inPos] & 67108863) >>> 22 | (in[26 + inPos] & 67108863) << 4) & 255);
         out[85 + outPos] = (byte)((in[26 + inPos] & 67108863) >>> 4 & 255);
         out[86 + outPos] = (byte)((in[26 + inPos] & 67108863) >>> 12 & 255);
         out[87 + outPos] = (byte)(((in[26 + inPos] & 67108863) >>> 20 | (in[27 + inPos] & 67108863) << 6) & 255);
         out[88 + outPos] = (byte)((in[27 + inPos] & 67108863) >>> 2 & 255);
         out[89 + outPos] = (byte)((in[27 + inPos] & 67108863) >>> 10 & 255);
         out[90 + outPos] = (byte)((in[27 + inPos] & 67108863) >>> 18 & 255);
         out[91 + outPos] = (byte)(in[28 + inPos] & 67108863 & 255);
         out[92 + outPos] = (byte)((in[28 + inPos] & 67108863) >>> 8 & 255);
         out[93 + outPos] = (byte)((in[28 + inPos] & 67108863) >>> 16 & 255);
         out[94 + outPos] = (byte)(((in[28 + inPos] & 67108863) >>> 24 | (in[29 + inPos] & 67108863) << 2) & 255);
         out[95 + outPos] = (byte)((in[29 + inPos] & 67108863) >>> 6 & 255);
         out[96 + outPos] = (byte)((in[29 + inPos] & 67108863) >>> 14 & 255);
         out[97 + outPos] = (byte)(((in[29 + inPos] & 67108863) >>> 22 | (in[30 + inPos] & 67108863) << 4) & 255);
         out[98 + outPos] = (byte)((in[30 + inPos] & 67108863) >>> 4 & 255);
         out[99 + outPos] = (byte)((in[30 + inPos] & 67108863) >>> 12 & 255);
         out[100 + outPos] = (byte)(((in[30 + inPos] & 67108863) >>> 20 | (in[31 + inPos] & 67108863) << 6) & 255);
         out[101 + outPos] = (byte)((in[31 + inPos] & 67108863) >>> 2 & 255);
         out[102 + outPos] = (byte)((in[31 + inPos] & 67108863) >>> 10 & 255);
         out[103 + outPos] = (byte)((in[31 + inPos] & 67108863) >>> 18 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 16777215 | in[3 + inPos] << 24 & 67108863;
         out[1 + outPos] = in[3 + inPos] >> 2 & 63 | in[4 + inPos] << 6 & 16383 | in[5 + inPos] << 14 & 4194303 | in[6 + inPos] << 22 & 67108863;
         out[2 + outPos] = in[6 + inPos] >> 4 & 15 | in[7 + inPos] << 4 & 4095 | in[8 + inPos] << 12 & 1048575 | in[9 + inPos] << 20 & 67108863;
         out[3 + outPos] = in[9 + inPos] >> 6 & 3 | in[10 + inPos] << 2 & 1023 | in[11 + inPos] << 10 & 262143 | in[12 + inPos] << 18 & 67108863;
         out[4 + outPos] = in[13 + inPos] & 255 | in[14 + inPos] << 8 & '\uffff' | in[15 + inPos] << 16 & 16777215 | in[16 + inPos] << 24 & 67108863;
         out[5 + outPos] = in[16 + inPos] >> 2 & 63 | in[17 + inPos] << 6 & 16383 | in[18 + inPos] << 14 & 4194303 | in[19 + inPos] << 22 & 67108863;
         out[6 + outPos] = in[19 + inPos] >> 4 & 15 | in[20 + inPos] << 4 & 4095 | in[21 + inPos] << 12 & 1048575 | in[22 + inPos] << 20 & 67108863;
         out[7 + outPos] = in[22 + inPos] >> 6 & 3 | in[23 + inPos] << 2 & 1023 | in[24 + inPos] << 10 & 262143 | in[25 + inPos] << 18 & 67108863;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 16777215 | in.get(3 + inPos) << 24 & 67108863;
         out[1 + outPos] = in.get(3 + inPos) >> 2 & 63 | in.get(4 + inPos) << 6 & 16383 | in.get(5 + inPos) << 14 & 4194303 | in.get(6 + inPos) << 22 & 67108863;
         out[2 + outPos] = in.get(6 + inPos) >> 4 & 15 | in.get(7 + inPos) << 4 & 4095 | in.get(8 + inPos) << 12 & 1048575 | in.get(9 + inPos) << 20 & 67108863;
         out[3 + outPos] = in.get(9 + inPos) >> 6 & 3 | in.get(10 + inPos) << 2 & 1023 | in.get(11 + inPos) << 10 & 262143 | in.get(12 + inPos) << 18 & 67108863;
         out[4 + outPos] = in.get(13 + inPos) & 255 | in.get(14 + inPos) << 8 & '\uffff' | in.get(15 + inPos) << 16 & 16777215 | in.get(16 + inPos) << 24 & 67108863;
         out[5 + outPos] = in.get(16 + inPos) >> 2 & 63 | in.get(17 + inPos) << 6 & 16383 | in.get(18 + inPos) << 14 & 4194303 | in.get(19 + inPos) << 22 & 67108863;
         out[6 + outPos] = in.get(19 + inPos) >> 4 & 15 | in.get(20 + inPos) << 4 & 4095 | in.get(21 + inPos) << 12 & 1048575 | in.get(22 + inPos) << 20 & 67108863;
         out[7 + outPos] = in.get(22 + inPos) >> 6 & 3 | in.get(23 + inPos) << 2 & 1023 | in.get(24 + inPos) << 10 & 262143 | in.get(25 + inPos) << 18 & 67108863;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 16777215 | in[3 + inPos] << 24 & 67108863;
         out[1 + outPos] = in[3 + inPos] >> 2 & 63 | in[4 + inPos] << 6 & 16383 | in[5 + inPos] << 14 & 4194303 | in[6 + inPos] << 22 & 67108863;
         out[2 + outPos] = in[6 + inPos] >> 4 & 15 | in[7 + inPos] << 4 & 4095 | in[8 + inPos] << 12 & 1048575 | in[9 + inPos] << 20 & 67108863;
         out[3 + outPos] = in[9 + inPos] >> 6 & 3 | in[10 + inPos] << 2 & 1023 | in[11 + inPos] << 10 & 262143 | in[12 + inPos] << 18 & 67108863;
         out[4 + outPos] = in[13 + inPos] & 255 | in[14 + inPos] << 8 & '\uffff' | in[15 + inPos] << 16 & 16777215 | in[16 + inPos] << 24 & 67108863;
         out[5 + outPos] = in[16 + inPos] >> 2 & 63 | in[17 + inPos] << 6 & 16383 | in[18 + inPos] << 14 & 4194303 | in[19 + inPos] << 22 & 67108863;
         out[6 + outPos] = in[19 + inPos] >> 4 & 15 | in[20 + inPos] << 4 & 4095 | in[21 + inPos] << 12 & 1048575 | in[22 + inPos] << 20 & 67108863;
         out[7 + outPos] = in[22 + inPos] >> 6 & 3 | in[23 + inPos] << 2 & 1023 | in[24 + inPos] << 10 & 262143 | in[25 + inPos] << 18 & 67108863;
         out[8 + outPos] = in[26 + inPos] & 255 | in[27 + inPos] << 8 & '\uffff' | in[28 + inPos] << 16 & 16777215 | in[29 + inPos] << 24 & 67108863;
         out[9 + outPos] = in[29 + inPos] >> 2 & 63 | in[30 + inPos] << 6 & 16383 | in[31 + inPos] << 14 & 4194303 | in[32 + inPos] << 22 & 67108863;
         out[10 + outPos] = in[32 + inPos] >> 4 & 15 | in[33 + inPos] << 4 & 4095 | in[34 + inPos] << 12 & 1048575 | in[35 + inPos] << 20 & 67108863;
         out[11 + outPos] = in[35 + inPos] >> 6 & 3 | in[36 + inPos] << 2 & 1023 | in[37 + inPos] << 10 & 262143 | in[38 + inPos] << 18 & 67108863;
         out[12 + outPos] = in[39 + inPos] & 255 | in[40 + inPos] << 8 & '\uffff' | in[41 + inPos] << 16 & 16777215 | in[42 + inPos] << 24 & 67108863;
         out[13 + outPos] = in[42 + inPos] >> 2 & 63 | in[43 + inPos] << 6 & 16383 | in[44 + inPos] << 14 & 4194303 | in[45 + inPos] << 22 & 67108863;
         out[14 + outPos] = in[45 + inPos] >> 4 & 15 | in[46 + inPos] << 4 & 4095 | in[47 + inPos] << 12 & 1048575 | in[48 + inPos] << 20 & 67108863;
         out[15 + outPos] = in[48 + inPos] >> 6 & 3 | in[49 + inPos] << 2 & 1023 | in[50 + inPos] << 10 & 262143 | in[51 + inPos] << 18 & 67108863;
         out[16 + outPos] = in[52 + inPos] & 255 | in[53 + inPos] << 8 & '\uffff' | in[54 + inPos] << 16 & 16777215 | in[55 + inPos] << 24 & 67108863;
         out[17 + outPos] = in[55 + inPos] >> 2 & 63 | in[56 + inPos] << 6 & 16383 | in[57 + inPos] << 14 & 4194303 | in[58 + inPos] << 22 & 67108863;
         out[18 + outPos] = in[58 + inPos] >> 4 & 15 | in[59 + inPos] << 4 & 4095 | in[60 + inPos] << 12 & 1048575 | in[61 + inPos] << 20 & 67108863;
         out[19 + outPos] = in[61 + inPos] >> 6 & 3 | in[62 + inPos] << 2 & 1023 | in[63 + inPos] << 10 & 262143 | in[64 + inPos] << 18 & 67108863;
         out[20 + outPos] = in[65 + inPos] & 255 | in[66 + inPos] << 8 & '\uffff' | in[67 + inPos] << 16 & 16777215 | in[68 + inPos] << 24 & 67108863;
         out[21 + outPos] = in[68 + inPos] >> 2 & 63 | in[69 + inPos] << 6 & 16383 | in[70 + inPos] << 14 & 4194303 | in[71 + inPos] << 22 & 67108863;
         out[22 + outPos] = in[71 + inPos] >> 4 & 15 | in[72 + inPos] << 4 & 4095 | in[73 + inPos] << 12 & 1048575 | in[74 + inPos] << 20 & 67108863;
         out[23 + outPos] = in[74 + inPos] >> 6 & 3 | in[75 + inPos] << 2 & 1023 | in[76 + inPos] << 10 & 262143 | in[77 + inPos] << 18 & 67108863;
         out[24 + outPos] = in[78 + inPos] & 255 | in[79 + inPos] << 8 & '\uffff' | in[80 + inPos] << 16 & 16777215 | in[81 + inPos] << 24 & 67108863;
         out[25 + outPos] = in[81 + inPos] >> 2 & 63 | in[82 + inPos] << 6 & 16383 | in[83 + inPos] << 14 & 4194303 | in[84 + inPos] << 22 & 67108863;
         out[26 + outPos] = in[84 + inPos] >> 4 & 15 | in[85 + inPos] << 4 & 4095 | in[86 + inPos] << 12 & 1048575 | in[87 + inPos] << 20 & 67108863;
         out[27 + outPos] = in[87 + inPos] >> 6 & 3 | in[88 + inPos] << 2 & 1023 | in[89 + inPos] << 10 & 262143 | in[90 + inPos] << 18 & 67108863;
         out[28 + outPos] = in[91 + inPos] & 255 | in[92 + inPos] << 8 & '\uffff' | in[93 + inPos] << 16 & 16777215 | in[94 + inPos] << 24 & 67108863;
         out[29 + outPos] = in[94 + inPos] >> 2 & 63 | in[95 + inPos] << 6 & 16383 | in[96 + inPos] << 14 & 4194303 | in[97 + inPos] << 22 & 67108863;
         out[30 + outPos] = in[97 + inPos] >> 4 & 15 | in[98 + inPos] << 4 & 4095 | in[99 + inPos] << 12 & 1048575 | in[100 + inPos] << 20 & 67108863;
         out[31 + outPos] = in[100 + inPos] >> 6 & 3 | in[101 + inPos] << 2 & 1023 | in[102 + inPos] << 10 & 262143 | in[103 + inPos] << 18 & 67108863;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 16777215 | in.get(3 + inPos) << 24 & 67108863;
         out[1 + outPos] = in.get(3 + inPos) >> 2 & 63 | in.get(4 + inPos) << 6 & 16383 | in.get(5 + inPos) << 14 & 4194303 | in.get(6 + inPos) << 22 & 67108863;
         out[2 + outPos] = in.get(6 + inPos) >> 4 & 15 | in.get(7 + inPos) << 4 & 4095 | in.get(8 + inPos) << 12 & 1048575 | in.get(9 + inPos) << 20 & 67108863;
         out[3 + outPos] = in.get(9 + inPos) >> 6 & 3 | in.get(10 + inPos) << 2 & 1023 | in.get(11 + inPos) << 10 & 262143 | in.get(12 + inPos) << 18 & 67108863;
         out[4 + outPos] = in.get(13 + inPos) & 255 | in.get(14 + inPos) << 8 & '\uffff' | in.get(15 + inPos) << 16 & 16777215 | in.get(16 + inPos) << 24 & 67108863;
         out[5 + outPos] = in.get(16 + inPos) >> 2 & 63 | in.get(17 + inPos) << 6 & 16383 | in.get(18 + inPos) << 14 & 4194303 | in.get(19 + inPos) << 22 & 67108863;
         out[6 + outPos] = in.get(19 + inPos) >> 4 & 15 | in.get(20 + inPos) << 4 & 4095 | in.get(21 + inPos) << 12 & 1048575 | in.get(22 + inPos) << 20 & 67108863;
         out[7 + outPos] = in.get(22 + inPos) >> 6 & 3 | in.get(23 + inPos) << 2 & 1023 | in.get(24 + inPos) << 10 & 262143 | in.get(25 + inPos) << 18 & 67108863;
         out[8 + outPos] = in.get(26 + inPos) & 255 | in.get(27 + inPos) << 8 & '\uffff' | in.get(28 + inPos) << 16 & 16777215 | in.get(29 + inPos) << 24 & 67108863;
         out[9 + outPos] = in.get(29 + inPos) >> 2 & 63 | in.get(30 + inPos) << 6 & 16383 | in.get(31 + inPos) << 14 & 4194303 | in.get(32 + inPos) << 22 & 67108863;
         out[10 + outPos] = in.get(32 + inPos) >> 4 & 15 | in.get(33 + inPos) << 4 & 4095 | in.get(34 + inPos) << 12 & 1048575 | in.get(35 + inPos) << 20 & 67108863;
         out[11 + outPos] = in.get(35 + inPos) >> 6 & 3 | in.get(36 + inPos) << 2 & 1023 | in.get(37 + inPos) << 10 & 262143 | in.get(38 + inPos) << 18 & 67108863;
         out[12 + outPos] = in.get(39 + inPos) & 255 | in.get(40 + inPos) << 8 & '\uffff' | in.get(41 + inPos) << 16 & 16777215 | in.get(42 + inPos) << 24 & 67108863;
         out[13 + outPos] = in.get(42 + inPos) >> 2 & 63 | in.get(43 + inPos) << 6 & 16383 | in.get(44 + inPos) << 14 & 4194303 | in.get(45 + inPos) << 22 & 67108863;
         out[14 + outPos] = in.get(45 + inPos) >> 4 & 15 | in.get(46 + inPos) << 4 & 4095 | in.get(47 + inPos) << 12 & 1048575 | in.get(48 + inPos) << 20 & 67108863;
         out[15 + outPos] = in.get(48 + inPos) >> 6 & 3 | in.get(49 + inPos) << 2 & 1023 | in.get(50 + inPos) << 10 & 262143 | in.get(51 + inPos) << 18 & 67108863;
         out[16 + outPos] = in.get(52 + inPos) & 255 | in.get(53 + inPos) << 8 & '\uffff' | in.get(54 + inPos) << 16 & 16777215 | in.get(55 + inPos) << 24 & 67108863;
         out[17 + outPos] = in.get(55 + inPos) >> 2 & 63 | in.get(56 + inPos) << 6 & 16383 | in.get(57 + inPos) << 14 & 4194303 | in.get(58 + inPos) << 22 & 67108863;
         out[18 + outPos] = in.get(58 + inPos) >> 4 & 15 | in.get(59 + inPos) << 4 & 4095 | in.get(60 + inPos) << 12 & 1048575 | in.get(61 + inPos) << 20 & 67108863;
         out[19 + outPos] = in.get(61 + inPos) >> 6 & 3 | in.get(62 + inPos) << 2 & 1023 | in.get(63 + inPos) << 10 & 262143 | in.get(64 + inPos) << 18 & 67108863;
         out[20 + outPos] = in.get(65 + inPos) & 255 | in.get(66 + inPos) << 8 & '\uffff' | in.get(67 + inPos) << 16 & 16777215 | in.get(68 + inPos) << 24 & 67108863;
         out[21 + outPos] = in.get(68 + inPos) >> 2 & 63 | in.get(69 + inPos) << 6 & 16383 | in.get(70 + inPos) << 14 & 4194303 | in.get(71 + inPos) << 22 & 67108863;
         out[22 + outPos] = in.get(71 + inPos) >> 4 & 15 | in.get(72 + inPos) << 4 & 4095 | in.get(73 + inPos) << 12 & 1048575 | in.get(74 + inPos) << 20 & 67108863;
         out[23 + outPos] = in.get(74 + inPos) >> 6 & 3 | in.get(75 + inPos) << 2 & 1023 | in.get(76 + inPos) << 10 & 262143 | in.get(77 + inPos) << 18 & 67108863;
         out[24 + outPos] = in.get(78 + inPos) & 255 | in.get(79 + inPos) << 8 & '\uffff' | in.get(80 + inPos) << 16 & 16777215 | in.get(81 + inPos) << 24 & 67108863;
         out[25 + outPos] = in.get(81 + inPos) >> 2 & 63 | in.get(82 + inPos) << 6 & 16383 | in.get(83 + inPos) << 14 & 4194303 | in.get(84 + inPos) << 22 & 67108863;
         out[26 + outPos] = in.get(84 + inPos) >> 4 & 15 | in.get(85 + inPos) << 4 & 4095 | in.get(86 + inPos) << 12 & 1048575 | in.get(87 + inPos) << 20 & 67108863;
         out[27 + outPos] = in.get(87 + inPos) >> 6 & 3 | in.get(88 + inPos) << 2 & 1023 | in.get(89 + inPos) << 10 & 262143 | in.get(90 + inPos) << 18 & 67108863;
         out[28 + outPos] = in.get(91 + inPos) & 255 | in.get(92 + inPos) << 8 & '\uffff' | in.get(93 + inPos) << 16 & 16777215 | in.get(94 + inPos) << 24 & 67108863;
         out[29 + outPos] = in.get(94 + inPos) >> 2 & 63 | in.get(95 + inPos) << 6 & 16383 | in.get(96 + inPos) << 14 & 4194303 | in.get(97 + inPos) << 22 & 67108863;
         out[30 + outPos] = in.get(97 + inPos) >> 4 & 15 | in.get(98 + inPos) << 4 & 4095 | in.get(99 + inPos) << 12 & 1048575 | in.get(100 + inPos) << 20 & 67108863;
         out[31 + outPos] = in.get(100 + inPos) >> 6 & 3 | in.get(101 + inPos) << 2 & 1023 | in.get(102 + inPos) << 10 & 262143 | in.get(103 + inPos) << 18 & 67108863;
      }
   }

   private static final class Packer27 extends BytePacker {
      private Packer27() {
         super(27);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 134217727 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 134217727) >>> 8 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & 134217727) >>> 16 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & 134217727) >>> 24 | (in[1 + inPos] & 134217727) << 3) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 134217727) >>> 5 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & 134217727) >>> 13 & 255);
         out[6 + outPos] = (byte)(((in[1 + inPos] & 134217727) >>> 21 | (in[2 + inPos] & 134217727) << 6) & 255);
         out[7 + outPos] = (byte)((in[2 + inPos] & 134217727) >>> 2 & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & 134217727) >>> 10 & 255);
         out[9 + outPos] = (byte)((in[2 + inPos] & 134217727) >>> 18 & 255);
         out[10 + outPos] = (byte)(((in[2 + inPos] & 134217727) >>> 26 | (in[3 + inPos] & 134217727) << 1) & 255);
         out[11 + outPos] = (byte)((in[3 + inPos] & 134217727) >>> 7 & 255);
         out[12 + outPos] = (byte)((in[3 + inPos] & 134217727) >>> 15 & 255);
         out[13 + outPos] = (byte)(((in[3 + inPos] & 134217727) >>> 23 | (in[4 + inPos] & 134217727) << 4) & 255);
         out[14 + outPos] = (byte)((in[4 + inPos] & 134217727) >>> 4 & 255);
         out[15 + outPos] = (byte)((in[4 + inPos] & 134217727) >>> 12 & 255);
         out[16 + outPos] = (byte)(((in[4 + inPos] & 134217727) >>> 20 | (in[5 + inPos] & 134217727) << 7) & 255);
         out[17 + outPos] = (byte)((in[5 + inPos] & 134217727) >>> 1 & 255);
         out[18 + outPos] = (byte)((in[5 + inPos] & 134217727) >>> 9 & 255);
         out[19 + outPos] = (byte)((in[5 + inPos] & 134217727) >>> 17 & 255);
         out[20 + outPos] = (byte)(((in[5 + inPos] & 134217727) >>> 25 | (in[6 + inPos] & 134217727) << 2) & 255);
         out[21 + outPos] = (byte)((in[6 + inPos] & 134217727) >>> 6 & 255);
         out[22 + outPos] = (byte)((in[6 + inPos] & 134217727) >>> 14 & 255);
         out[23 + outPos] = (byte)(((in[6 + inPos] & 134217727) >>> 22 | (in[7 + inPos] & 134217727) << 5) & 255);
         out[24 + outPos] = (byte)((in[7 + inPos] & 134217727) >>> 3 & 255);
         out[25 + outPos] = (byte)((in[7 + inPos] & 134217727) >>> 11 & 255);
         out[26 + outPos] = (byte)((in[7 + inPos] & 134217727) >>> 19 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 134217727 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 134217727) >>> 8 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & 134217727) >>> 16 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & 134217727) >>> 24 | (in[1 + inPos] & 134217727) << 3) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 134217727) >>> 5 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & 134217727) >>> 13 & 255);
         out[6 + outPos] = (byte)(((in[1 + inPos] & 134217727) >>> 21 | (in[2 + inPos] & 134217727) << 6) & 255);
         out[7 + outPos] = (byte)((in[2 + inPos] & 134217727) >>> 2 & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & 134217727) >>> 10 & 255);
         out[9 + outPos] = (byte)((in[2 + inPos] & 134217727) >>> 18 & 255);
         out[10 + outPos] = (byte)(((in[2 + inPos] & 134217727) >>> 26 | (in[3 + inPos] & 134217727) << 1) & 255);
         out[11 + outPos] = (byte)((in[3 + inPos] & 134217727) >>> 7 & 255);
         out[12 + outPos] = (byte)((in[3 + inPos] & 134217727) >>> 15 & 255);
         out[13 + outPos] = (byte)(((in[3 + inPos] & 134217727) >>> 23 | (in[4 + inPos] & 134217727) << 4) & 255);
         out[14 + outPos] = (byte)((in[4 + inPos] & 134217727) >>> 4 & 255);
         out[15 + outPos] = (byte)((in[4 + inPos] & 134217727) >>> 12 & 255);
         out[16 + outPos] = (byte)(((in[4 + inPos] & 134217727) >>> 20 | (in[5 + inPos] & 134217727) << 7) & 255);
         out[17 + outPos] = (byte)((in[5 + inPos] & 134217727) >>> 1 & 255);
         out[18 + outPos] = (byte)((in[5 + inPos] & 134217727) >>> 9 & 255);
         out[19 + outPos] = (byte)((in[5 + inPos] & 134217727) >>> 17 & 255);
         out[20 + outPos] = (byte)(((in[5 + inPos] & 134217727) >>> 25 | (in[6 + inPos] & 134217727) << 2) & 255);
         out[21 + outPos] = (byte)((in[6 + inPos] & 134217727) >>> 6 & 255);
         out[22 + outPos] = (byte)((in[6 + inPos] & 134217727) >>> 14 & 255);
         out[23 + outPos] = (byte)(((in[6 + inPos] & 134217727) >>> 22 | (in[7 + inPos] & 134217727) << 5) & 255);
         out[24 + outPos] = (byte)((in[7 + inPos] & 134217727) >>> 3 & 255);
         out[25 + outPos] = (byte)((in[7 + inPos] & 134217727) >>> 11 & 255);
         out[26 + outPos] = (byte)((in[7 + inPos] & 134217727) >>> 19 & 255);
         out[27 + outPos] = (byte)(in[8 + inPos] & 134217727 & 255);
         out[28 + outPos] = (byte)((in[8 + inPos] & 134217727) >>> 8 & 255);
         out[29 + outPos] = (byte)((in[8 + inPos] & 134217727) >>> 16 & 255);
         out[30 + outPos] = (byte)(((in[8 + inPos] & 134217727) >>> 24 | (in[9 + inPos] & 134217727) << 3) & 255);
         out[31 + outPos] = (byte)((in[9 + inPos] & 134217727) >>> 5 & 255);
         out[32 + outPos] = (byte)((in[9 + inPos] & 134217727) >>> 13 & 255);
         out[33 + outPos] = (byte)(((in[9 + inPos] & 134217727) >>> 21 | (in[10 + inPos] & 134217727) << 6) & 255);
         out[34 + outPos] = (byte)((in[10 + inPos] & 134217727) >>> 2 & 255);
         out[35 + outPos] = (byte)((in[10 + inPos] & 134217727) >>> 10 & 255);
         out[36 + outPos] = (byte)((in[10 + inPos] & 134217727) >>> 18 & 255);
         out[37 + outPos] = (byte)(((in[10 + inPos] & 134217727) >>> 26 | (in[11 + inPos] & 134217727) << 1) & 255);
         out[38 + outPos] = (byte)((in[11 + inPos] & 134217727) >>> 7 & 255);
         out[39 + outPos] = (byte)((in[11 + inPos] & 134217727) >>> 15 & 255);
         out[40 + outPos] = (byte)(((in[11 + inPos] & 134217727) >>> 23 | (in[12 + inPos] & 134217727) << 4) & 255);
         out[41 + outPos] = (byte)((in[12 + inPos] & 134217727) >>> 4 & 255);
         out[42 + outPos] = (byte)((in[12 + inPos] & 134217727) >>> 12 & 255);
         out[43 + outPos] = (byte)(((in[12 + inPos] & 134217727) >>> 20 | (in[13 + inPos] & 134217727) << 7) & 255);
         out[44 + outPos] = (byte)((in[13 + inPos] & 134217727) >>> 1 & 255);
         out[45 + outPos] = (byte)((in[13 + inPos] & 134217727) >>> 9 & 255);
         out[46 + outPos] = (byte)((in[13 + inPos] & 134217727) >>> 17 & 255);
         out[47 + outPos] = (byte)(((in[13 + inPos] & 134217727) >>> 25 | (in[14 + inPos] & 134217727) << 2) & 255);
         out[48 + outPos] = (byte)((in[14 + inPos] & 134217727) >>> 6 & 255);
         out[49 + outPos] = (byte)((in[14 + inPos] & 134217727) >>> 14 & 255);
         out[50 + outPos] = (byte)(((in[14 + inPos] & 134217727) >>> 22 | (in[15 + inPos] & 134217727) << 5) & 255);
         out[51 + outPos] = (byte)((in[15 + inPos] & 134217727) >>> 3 & 255);
         out[52 + outPos] = (byte)((in[15 + inPos] & 134217727) >>> 11 & 255);
         out[53 + outPos] = (byte)((in[15 + inPos] & 134217727) >>> 19 & 255);
         out[54 + outPos] = (byte)(in[16 + inPos] & 134217727 & 255);
         out[55 + outPos] = (byte)((in[16 + inPos] & 134217727) >>> 8 & 255);
         out[56 + outPos] = (byte)((in[16 + inPos] & 134217727) >>> 16 & 255);
         out[57 + outPos] = (byte)(((in[16 + inPos] & 134217727) >>> 24 | (in[17 + inPos] & 134217727) << 3) & 255);
         out[58 + outPos] = (byte)((in[17 + inPos] & 134217727) >>> 5 & 255);
         out[59 + outPos] = (byte)((in[17 + inPos] & 134217727) >>> 13 & 255);
         out[60 + outPos] = (byte)(((in[17 + inPos] & 134217727) >>> 21 | (in[18 + inPos] & 134217727) << 6) & 255);
         out[61 + outPos] = (byte)((in[18 + inPos] & 134217727) >>> 2 & 255);
         out[62 + outPos] = (byte)((in[18 + inPos] & 134217727) >>> 10 & 255);
         out[63 + outPos] = (byte)((in[18 + inPos] & 134217727) >>> 18 & 255);
         out[64 + outPos] = (byte)(((in[18 + inPos] & 134217727) >>> 26 | (in[19 + inPos] & 134217727) << 1) & 255);
         out[65 + outPos] = (byte)((in[19 + inPos] & 134217727) >>> 7 & 255);
         out[66 + outPos] = (byte)((in[19 + inPos] & 134217727) >>> 15 & 255);
         out[67 + outPos] = (byte)(((in[19 + inPos] & 134217727) >>> 23 | (in[20 + inPos] & 134217727) << 4) & 255);
         out[68 + outPos] = (byte)((in[20 + inPos] & 134217727) >>> 4 & 255);
         out[69 + outPos] = (byte)((in[20 + inPos] & 134217727) >>> 12 & 255);
         out[70 + outPos] = (byte)(((in[20 + inPos] & 134217727) >>> 20 | (in[21 + inPos] & 134217727) << 7) & 255);
         out[71 + outPos] = (byte)((in[21 + inPos] & 134217727) >>> 1 & 255);
         out[72 + outPos] = (byte)((in[21 + inPos] & 134217727) >>> 9 & 255);
         out[73 + outPos] = (byte)((in[21 + inPos] & 134217727) >>> 17 & 255);
         out[74 + outPos] = (byte)(((in[21 + inPos] & 134217727) >>> 25 | (in[22 + inPos] & 134217727) << 2) & 255);
         out[75 + outPos] = (byte)((in[22 + inPos] & 134217727) >>> 6 & 255);
         out[76 + outPos] = (byte)((in[22 + inPos] & 134217727) >>> 14 & 255);
         out[77 + outPos] = (byte)(((in[22 + inPos] & 134217727) >>> 22 | (in[23 + inPos] & 134217727) << 5) & 255);
         out[78 + outPos] = (byte)((in[23 + inPos] & 134217727) >>> 3 & 255);
         out[79 + outPos] = (byte)((in[23 + inPos] & 134217727) >>> 11 & 255);
         out[80 + outPos] = (byte)((in[23 + inPos] & 134217727) >>> 19 & 255);
         out[81 + outPos] = (byte)(in[24 + inPos] & 134217727 & 255);
         out[82 + outPos] = (byte)((in[24 + inPos] & 134217727) >>> 8 & 255);
         out[83 + outPos] = (byte)((in[24 + inPos] & 134217727) >>> 16 & 255);
         out[84 + outPos] = (byte)(((in[24 + inPos] & 134217727) >>> 24 | (in[25 + inPos] & 134217727) << 3) & 255);
         out[85 + outPos] = (byte)((in[25 + inPos] & 134217727) >>> 5 & 255);
         out[86 + outPos] = (byte)((in[25 + inPos] & 134217727) >>> 13 & 255);
         out[87 + outPos] = (byte)(((in[25 + inPos] & 134217727) >>> 21 | (in[26 + inPos] & 134217727) << 6) & 255);
         out[88 + outPos] = (byte)((in[26 + inPos] & 134217727) >>> 2 & 255);
         out[89 + outPos] = (byte)((in[26 + inPos] & 134217727) >>> 10 & 255);
         out[90 + outPos] = (byte)((in[26 + inPos] & 134217727) >>> 18 & 255);
         out[91 + outPos] = (byte)(((in[26 + inPos] & 134217727) >>> 26 | (in[27 + inPos] & 134217727) << 1) & 255);
         out[92 + outPos] = (byte)((in[27 + inPos] & 134217727) >>> 7 & 255);
         out[93 + outPos] = (byte)((in[27 + inPos] & 134217727) >>> 15 & 255);
         out[94 + outPos] = (byte)(((in[27 + inPos] & 134217727) >>> 23 | (in[28 + inPos] & 134217727) << 4) & 255);
         out[95 + outPos] = (byte)((in[28 + inPos] & 134217727) >>> 4 & 255);
         out[96 + outPos] = (byte)((in[28 + inPos] & 134217727) >>> 12 & 255);
         out[97 + outPos] = (byte)(((in[28 + inPos] & 134217727) >>> 20 | (in[29 + inPos] & 134217727) << 7) & 255);
         out[98 + outPos] = (byte)((in[29 + inPos] & 134217727) >>> 1 & 255);
         out[99 + outPos] = (byte)((in[29 + inPos] & 134217727) >>> 9 & 255);
         out[100 + outPos] = (byte)((in[29 + inPos] & 134217727) >>> 17 & 255);
         out[101 + outPos] = (byte)(((in[29 + inPos] & 134217727) >>> 25 | (in[30 + inPos] & 134217727) << 2) & 255);
         out[102 + outPos] = (byte)((in[30 + inPos] & 134217727) >>> 6 & 255);
         out[103 + outPos] = (byte)((in[30 + inPos] & 134217727) >>> 14 & 255);
         out[104 + outPos] = (byte)(((in[30 + inPos] & 134217727) >>> 22 | (in[31 + inPos] & 134217727) << 5) & 255);
         out[105 + outPos] = (byte)((in[31 + inPos] & 134217727) >>> 3 & 255);
         out[106 + outPos] = (byte)((in[31 + inPos] & 134217727) >>> 11 & 255);
         out[107 + outPos] = (byte)((in[31 + inPos] & 134217727) >>> 19 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 16777215 | in[3 + inPos] << 24 & 134217727;
         out[1 + outPos] = in[3 + inPos] >> 3 & 31 | in[4 + inPos] << 5 & 8191 | in[5 + inPos] << 13 & 2097151 | in[6 + inPos] << 21 & 134217727;
         out[2 + outPos] = in[6 + inPos] >> 6 & 3 | in[7 + inPos] << 2 & 1023 | in[8 + inPos] << 10 & 262143 | in[9 + inPos] << 18 & 67108863 | in[10 + inPos] << 26 & 134217727;
         out[3 + outPos] = in[10 + inPos] >> 1 & 127 | in[11 + inPos] << 7 & 32767 | in[12 + inPos] << 15 & 8388607 | in[13 + inPos] << 23 & 134217727;
         out[4 + outPos] = in[13 + inPos] >> 4 & 15 | in[14 + inPos] << 4 & 4095 | in[15 + inPos] << 12 & 1048575 | in[16 + inPos] << 20 & 134217727;
         out[5 + outPos] = in[16 + inPos] >> 7 & 1 | in[17 + inPos] << 1 & 511 | in[18 + inPos] << 9 & 131071 | in[19 + inPos] << 17 & 33554431 | in[20 + inPos] << 25 & 134217727;
         out[6 + outPos] = in[20 + inPos] >> 2 & 63 | in[21 + inPos] << 6 & 16383 | in[22 + inPos] << 14 & 4194303 | in[23 + inPos] << 22 & 134217727;
         out[7 + outPos] = in[23 + inPos] >> 5 & 7 | in[24 + inPos] << 3 & 2047 | in[25 + inPos] << 11 & 524287 | in[26 + inPos] << 19 & 134217727;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 16777215 | in.get(3 + inPos) << 24 & 134217727;
         out[1 + outPos] = in.get(3 + inPos) >> 3 & 31 | in.get(4 + inPos) << 5 & 8191 | in.get(5 + inPos) << 13 & 2097151 | in.get(6 + inPos) << 21 & 134217727;
         out[2 + outPos] = in.get(6 + inPos) >> 6 & 3 | in.get(7 + inPos) << 2 & 1023 | in.get(8 + inPos) << 10 & 262143 | in.get(9 + inPos) << 18 & 67108863 | in.get(10 + inPos) << 26 & 134217727;
         out[3 + outPos] = in.get(10 + inPos) >> 1 & 127 | in.get(11 + inPos) << 7 & 32767 | in.get(12 + inPos) << 15 & 8388607 | in.get(13 + inPos) << 23 & 134217727;
         out[4 + outPos] = in.get(13 + inPos) >> 4 & 15 | in.get(14 + inPos) << 4 & 4095 | in.get(15 + inPos) << 12 & 1048575 | in.get(16 + inPos) << 20 & 134217727;
         out[5 + outPos] = in.get(16 + inPos) >> 7 & 1 | in.get(17 + inPos) << 1 & 511 | in.get(18 + inPos) << 9 & 131071 | in.get(19 + inPos) << 17 & 33554431 | in.get(20 + inPos) << 25 & 134217727;
         out[6 + outPos] = in.get(20 + inPos) >> 2 & 63 | in.get(21 + inPos) << 6 & 16383 | in.get(22 + inPos) << 14 & 4194303 | in.get(23 + inPos) << 22 & 134217727;
         out[7 + outPos] = in.get(23 + inPos) >> 5 & 7 | in.get(24 + inPos) << 3 & 2047 | in.get(25 + inPos) << 11 & 524287 | in.get(26 + inPos) << 19 & 134217727;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 16777215 | in[3 + inPos] << 24 & 134217727;
         out[1 + outPos] = in[3 + inPos] >> 3 & 31 | in[4 + inPos] << 5 & 8191 | in[5 + inPos] << 13 & 2097151 | in[6 + inPos] << 21 & 134217727;
         out[2 + outPos] = in[6 + inPos] >> 6 & 3 | in[7 + inPos] << 2 & 1023 | in[8 + inPos] << 10 & 262143 | in[9 + inPos] << 18 & 67108863 | in[10 + inPos] << 26 & 134217727;
         out[3 + outPos] = in[10 + inPos] >> 1 & 127 | in[11 + inPos] << 7 & 32767 | in[12 + inPos] << 15 & 8388607 | in[13 + inPos] << 23 & 134217727;
         out[4 + outPos] = in[13 + inPos] >> 4 & 15 | in[14 + inPos] << 4 & 4095 | in[15 + inPos] << 12 & 1048575 | in[16 + inPos] << 20 & 134217727;
         out[5 + outPos] = in[16 + inPos] >> 7 & 1 | in[17 + inPos] << 1 & 511 | in[18 + inPos] << 9 & 131071 | in[19 + inPos] << 17 & 33554431 | in[20 + inPos] << 25 & 134217727;
         out[6 + outPos] = in[20 + inPos] >> 2 & 63 | in[21 + inPos] << 6 & 16383 | in[22 + inPos] << 14 & 4194303 | in[23 + inPos] << 22 & 134217727;
         out[7 + outPos] = in[23 + inPos] >> 5 & 7 | in[24 + inPos] << 3 & 2047 | in[25 + inPos] << 11 & 524287 | in[26 + inPos] << 19 & 134217727;
         out[8 + outPos] = in[27 + inPos] & 255 | in[28 + inPos] << 8 & '\uffff' | in[29 + inPos] << 16 & 16777215 | in[30 + inPos] << 24 & 134217727;
         out[9 + outPos] = in[30 + inPos] >> 3 & 31 | in[31 + inPos] << 5 & 8191 | in[32 + inPos] << 13 & 2097151 | in[33 + inPos] << 21 & 134217727;
         out[10 + outPos] = in[33 + inPos] >> 6 & 3 | in[34 + inPos] << 2 & 1023 | in[35 + inPos] << 10 & 262143 | in[36 + inPos] << 18 & 67108863 | in[37 + inPos] << 26 & 134217727;
         out[11 + outPos] = in[37 + inPos] >> 1 & 127 | in[38 + inPos] << 7 & 32767 | in[39 + inPos] << 15 & 8388607 | in[40 + inPos] << 23 & 134217727;
         out[12 + outPos] = in[40 + inPos] >> 4 & 15 | in[41 + inPos] << 4 & 4095 | in[42 + inPos] << 12 & 1048575 | in[43 + inPos] << 20 & 134217727;
         out[13 + outPos] = in[43 + inPos] >> 7 & 1 | in[44 + inPos] << 1 & 511 | in[45 + inPos] << 9 & 131071 | in[46 + inPos] << 17 & 33554431 | in[47 + inPos] << 25 & 134217727;
         out[14 + outPos] = in[47 + inPos] >> 2 & 63 | in[48 + inPos] << 6 & 16383 | in[49 + inPos] << 14 & 4194303 | in[50 + inPos] << 22 & 134217727;
         out[15 + outPos] = in[50 + inPos] >> 5 & 7 | in[51 + inPos] << 3 & 2047 | in[52 + inPos] << 11 & 524287 | in[53 + inPos] << 19 & 134217727;
         out[16 + outPos] = in[54 + inPos] & 255 | in[55 + inPos] << 8 & '\uffff' | in[56 + inPos] << 16 & 16777215 | in[57 + inPos] << 24 & 134217727;
         out[17 + outPos] = in[57 + inPos] >> 3 & 31 | in[58 + inPos] << 5 & 8191 | in[59 + inPos] << 13 & 2097151 | in[60 + inPos] << 21 & 134217727;
         out[18 + outPos] = in[60 + inPos] >> 6 & 3 | in[61 + inPos] << 2 & 1023 | in[62 + inPos] << 10 & 262143 | in[63 + inPos] << 18 & 67108863 | in[64 + inPos] << 26 & 134217727;
         out[19 + outPos] = in[64 + inPos] >> 1 & 127 | in[65 + inPos] << 7 & 32767 | in[66 + inPos] << 15 & 8388607 | in[67 + inPos] << 23 & 134217727;
         out[20 + outPos] = in[67 + inPos] >> 4 & 15 | in[68 + inPos] << 4 & 4095 | in[69 + inPos] << 12 & 1048575 | in[70 + inPos] << 20 & 134217727;
         out[21 + outPos] = in[70 + inPos] >> 7 & 1 | in[71 + inPos] << 1 & 511 | in[72 + inPos] << 9 & 131071 | in[73 + inPos] << 17 & 33554431 | in[74 + inPos] << 25 & 134217727;
         out[22 + outPos] = in[74 + inPos] >> 2 & 63 | in[75 + inPos] << 6 & 16383 | in[76 + inPos] << 14 & 4194303 | in[77 + inPos] << 22 & 134217727;
         out[23 + outPos] = in[77 + inPos] >> 5 & 7 | in[78 + inPos] << 3 & 2047 | in[79 + inPos] << 11 & 524287 | in[80 + inPos] << 19 & 134217727;
         out[24 + outPos] = in[81 + inPos] & 255 | in[82 + inPos] << 8 & '\uffff' | in[83 + inPos] << 16 & 16777215 | in[84 + inPos] << 24 & 134217727;
         out[25 + outPos] = in[84 + inPos] >> 3 & 31 | in[85 + inPos] << 5 & 8191 | in[86 + inPos] << 13 & 2097151 | in[87 + inPos] << 21 & 134217727;
         out[26 + outPos] = in[87 + inPos] >> 6 & 3 | in[88 + inPos] << 2 & 1023 | in[89 + inPos] << 10 & 262143 | in[90 + inPos] << 18 & 67108863 | in[91 + inPos] << 26 & 134217727;
         out[27 + outPos] = in[91 + inPos] >> 1 & 127 | in[92 + inPos] << 7 & 32767 | in[93 + inPos] << 15 & 8388607 | in[94 + inPos] << 23 & 134217727;
         out[28 + outPos] = in[94 + inPos] >> 4 & 15 | in[95 + inPos] << 4 & 4095 | in[96 + inPos] << 12 & 1048575 | in[97 + inPos] << 20 & 134217727;
         out[29 + outPos] = in[97 + inPos] >> 7 & 1 | in[98 + inPos] << 1 & 511 | in[99 + inPos] << 9 & 131071 | in[100 + inPos] << 17 & 33554431 | in[101 + inPos] << 25 & 134217727;
         out[30 + outPos] = in[101 + inPos] >> 2 & 63 | in[102 + inPos] << 6 & 16383 | in[103 + inPos] << 14 & 4194303 | in[104 + inPos] << 22 & 134217727;
         out[31 + outPos] = in[104 + inPos] >> 5 & 7 | in[105 + inPos] << 3 & 2047 | in[106 + inPos] << 11 & 524287 | in[107 + inPos] << 19 & 134217727;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 16777215 | in.get(3 + inPos) << 24 & 134217727;
         out[1 + outPos] = in.get(3 + inPos) >> 3 & 31 | in.get(4 + inPos) << 5 & 8191 | in.get(5 + inPos) << 13 & 2097151 | in.get(6 + inPos) << 21 & 134217727;
         out[2 + outPos] = in.get(6 + inPos) >> 6 & 3 | in.get(7 + inPos) << 2 & 1023 | in.get(8 + inPos) << 10 & 262143 | in.get(9 + inPos) << 18 & 67108863 | in.get(10 + inPos) << 26 & 134217727;
         out[3 + outPos] = in.get(10 + inPos) >> 1 & 127 | in.get(11 + inPos) << 7 & 32767 | in.get(12 + inPos) << 15 & 8388607 | in.get(13 + inPos) << 23 & 134217727;
         out[4 + outPos] = in.get(13 + inPos) >> 4 & 15 | in.get(14 + inPos) << 4 & 4095 | in.get(15 + inPos) << 12 & 1048575 | in.get(16 + inPos) << 20 & 134217727;
         out[5 + outPos] = in.get(16 + inPos) >> 7 & 1 | in.get(17 + inPos) << 1 & 511 | in.get(18 + inPos) << 9 & 131071 | in.get(19 + inPos) << 17 & 33554431 | in.get(20 + inPos) << 25 & 134217727;
         out[6 + outPos] = in.get(20 + inPos) >> 2 & 63 | in.get(21 + inPos) << 6 & 16383 | in.get(22 + inPos) << 14 & 4194303 | in.get(23 + inPos) << 22 & 134217727;
         out[7 + outPos] = in.get(23 + inPos) >> 5 & 7 | in.get(24 + inPos) << 3 & 2047 | in.get(25 + inPos) << 11 & 524287 | in.get(26 + inPos) << 19 & 134217727;
         out[8 + outPos] = in.get(27 + inPos) & 255 | in.get(28 + inPos) << 8 & '\uffff' | in.get(29 + inPos) << 16 & 16777215 | in.get(30 + inPos) << 24 & 134217727;
         out[9 + outPos] = in.get(30 + inPos) >> 3 & 31 | in.get(31 + inPos) << 5 & 8191 | in.get(32 + inPos) << 13 & 2097151 | in.get(33 + inPos) << 21 & 134217727;
         out[10 + outPos] = in.get(33 + inPos) >> 6 & 3 | in.get(34 + inPos) << 2 & 1023 | in.get(35 + inPos) << 10 & 262143 | in.get(36 + inPos) << 18 & 67108863 | in.get(37 + inPos) << 26 & 134217727;
         out[11 + outPos] = in.get(37 + inPos) >> 1 & 127 | in.get(38 + inPos) << 7 & 32767 | in.get(39 + inPos) << 15 & 8388607 | in.get(40 + inPos) << 23 & 134217727;
         out[12 + outPos] = in.get(40 + inPos) >> 4 & 15 | in.get(41 + inPos) << 4 & 4095 | in.get(42 + inPos) << 12 & 1048575 | in.get(43 + inPos) << 20 & 134217727;
         out[13 + outPos] = in.get(43 + inPos) >> 7 & 1 | in.get(44 + inPos) << 1 & 511 | in.get(45 + inPos) << 9 & 131071 | in.get(46 + inPos) << 17 & 33554431 | in.get(47 + inPos) << 25 & 134217727;
         out[14 + outPos] = in.get(47 + inPos) >> 2 & 63 | in.get(48 + inPos) << 6 & 16383 | in.get(49 + inPos) << 14 & 4194303 | in.get(50 + inPos) << 22 & 134217727;
         out[15 + outPos] = in.get(50 + inPos) >> 5 & 7 | in.get(51 + inPos) << 3 & 2047 | in.get(52 + inPos) << 11 & 524287 | in.get(53 + inPos) << 19 & 134217727;
         out[16 + outPos] = in.get(54 + inPos) & 255 | in.get(55 + inPos) << 8 & '\uffff' | in.get(56 + inPos) << 16 & 16777215 | in.get(57 + inPos) << 24 & 134217727;
         out[17 + outPos] = in.get(57 + inPos) >> 3 & 31 | in.get(58 + inPos) << 5 & 8191 | in.get(59 + inPos) << 13 & 2097151 | in.get(60 + inPos) << 21 & 134217727;
         out[18 + outPos] = in.get(60 + inPos) >> 6 & 3 | in.get(61 + inPos) << 2 & 1023 | in.get(62 + inPos) << 10 & 262143 | in.get(63 + inPos) << 18 & 67108863 | in.get(64 + inPos) << 26 & 134217727;
         out[19 + outPos] = in.get(64 + inPos) >> 1 & 127 | in.get(65 + inPos) << 7 & 32767 | in.get(66 + inPos) << 15 & 8388607 | in.get(67 + inPos) << 23 & 134217727;
         out[20 + outPos] = in.get(67 + inPos) >> 4 & 15 | in.get(68 + inPos) << 4 & 4095 | in.get(69 + inPos) << 12 & 1048575 | in.get(70 + inPos) << 20 & 134217727;
         out[21 + outPos] = in.get(70 + inPos) >> 7 & 1 | in.get(71 + inPos) << 1 & 511 | in.get(72 + inPos) << 9 & 131071 | in.get(73 + inPos) << 17 & 33554431 | in.get(74 + inPos) << 25 & 134217727;
         out[22 + outPos] = in.get(74 + inPos) >> 2 & 63 | in.get(75 + inPos) << 6 & 16383 | in.get(76 + inPos) << 14 & 4194303 | in.get(77 + inPos) << 22 & 134217727;
         out[23 + outPos] = in.get(77 + inPos) >> 5 & 7 | in.get(78 + inPos) << 3 & 2047 | in.get(79 + inPos) << 11 & 524287 | in.get(80 + inPos) << 19 & 134217727;
         out[24 + outPos] = in.get(81 + inPos) & 255 | in.get(82 + inPos) << 8 & '\uffff' | in.get(83 + inPos) << 16 & 16777215 | in.get(84 + inPos) << 24 & 134217727;
         out[25 + outPos] = in.get(84 + inPos) >> 3 & 31 | in.get(85 + inPos) << 5 & 8191 | in.get(86 + inPos) << 13 & 2097151 | in.get(87 + inPos) << 21 & 134217727;
         out[26 + outPos] = in.get(87 + inPos) >> 6 & 3 | in.get(88 + inPos) << 2 & 1023 | in.get(89 + inPos) << 10 & 262143 | in.get(90 + inPos) << 18 & 67108863 | in.get(91 + inPos) << 26 & 134217727;
         out[27 + outPos] = in.get(91 + inPos) >> 1 & 127 | in.get(92 + inPos) << 7 & 32767 | in.get(93 + inPos) << 15 & 8388607 | in.get(94 + inPos) << 23 & 134217727;
         out[28 + outPos] = in.get(94 + inPos) >> 4 & 15 | in.get(95 + inPos) << 4 & 4095 | in.get(96 + inPos) << 12 & 1048575 | in.get(97 + inPos) << 20 & 134217727;
         out[29 + outPos] = in.get(97 + inPos) >> 7 & 1 | in.get(98 + inPos) << 1 & 511 | in.get(99 + inPos) << 9 & 131071 | in.get(100 + inPos) << 17 & 33554431 | in.get(101 + inPos) << 25 & 134217727;
         out[30 + outPos] = in.get(101 + inPos) >> 2 & 63 | in.get(102 + inPos) << 6 & 16383 | in.get(103 + inPos) << 14 & 4194303 | in.get(104 + inPos) << 22 & 134217727;
         out[31 + outPos] = in.get(104 + inPos) >> 5 & 7 | in.get(105 + inPos) << 3 & 2047 | in.get(106 + inPos) << 11 & 524287 | in.get(107 + inPos) << 19 & 134217727;
      }
   }

   private static final class Packer28 extends BytePacker {
      private Packer28() {
         super(28);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 268435455 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 268435455) >>> 8 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & 268435455) >>> 16 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & 268435455) >>> 24 | (in[1 + inPos] & 268435455) << 4) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 268435455) >>> 4 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & 268435455) >>> 12 & 255);
         out[6 + outPos] = (byte)((in[1 + inPos] & 268435455) >>> 20 & 255);
         out[7 + outPos] = (byte)(in[2 + inPos] & 268435455 & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & 268435455) >>> 8 & 255);
         out[9 + outPos] = (byte)((in[2 + inPos] & 268435455) >>> 16 & 255);
         out[10 + outPos] = (byte)(((in[2 + inPos] & 268435455) >>> 24 | (in[3 + inPos] & 268435455) << 4) & 255);
         out[11 + outPos] = (byte)((in[3 + inPos] & 268435455) >>> 4 & 255);
         out[12 + outPos] = (byte)((in[3 + inPos] & 268435455) >>> 12 & 255);
         out[13 + outPos] = (byte)((in[3 + inPos] & 268435455) >>> 20 & 255);
         out[14 + outPos] = (byte)(in[4 + inPos] & 268435455 & 255);
         out[15 + outPos] = (byte)((in[4 + inPos] & 268435455) >>> 8 & 255);
         out[16 + outPos] = (byte)((in[4 + inPos] & 268435455) >>> 16 & 255);
         out[17 + outPos] = (byte)(((in[4 + inPos] & 268435455) >>> 24 | (in[5 + inPos] & 268435455) << 4) & 255);
         out[18 + outPos] = (byte)((in[5 + inPos] & 268435455) >>> 4 & 255);
         out[19 + outPos] = (byte)((in[5 + inPos] & 268435455) >>> 12 & 255);
         out[20 + outPos] = (byte)((in[5 + inPos] & 268435455) >>> 20 & 255);
         out[21 + outPos] = (byte)(in[6 + inPos] & 268435455 & 255);
         out[22 + outPos] = (byte)((in[6 + inPos] & 268435455) >>> 8 & 255);
         out[23 + outPos] = (byte)((in[6 + inPos] & 268435455) >>> 16 & 255);
         out[24 + outPos] = (byte)(((in[6 + inPos] & 268435455) >>> 24 | (in[7 + inPos] & 268435455) << 4) & 255);
         out[25 + outPos] = (byte)((in[7 + inPos] & 268435455) >>> 4 & 255);
         out[26 + outPos] = (byte)((in[7 + inPos] & 268435455) >>> 12 & 255);
         out[27 + outPos] = (byte)((in[7 + inPos] & 268435455) >>> 20 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 268435455 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 268435455) >>> 8 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & 268435455) >>> 16 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & 268435455) >>> 24 | (in[1 + inPos] & 268435455) << 4) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 268435455) >>> 4 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & 268435455) >>> 12 & 255);
         out[6 + outPos] = (byte)((in[1 + inPos] & 268435455) >>> 20 & 255);
         out[7 + outPos] = (byte)(in[2 + inPos] & 268435455 & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & 268435455) >>> 8 & 255);
         out[9 + outPos] = (byte)((in[2 + inPos] & 268435455) >>> 16 & 255);
         out[10 + outPos] = (byte)(((in[2 + inPos] & 268435455) >>> 24 | (in[3 + inPos] & 268435455) << 4) & 255);
         out[11 + outPos] = (byte)((in[3 + inPos] & 268435455) >>> 4 & 255);
         out[12 + outPos] = (byte)((in[3 + inPos] & 268435455) >>> 12 & 255);
         out[13 + outPos] = (byte)((in[3 + inPos] & 268435455) >>> 20 & 255);
         out[14 + outPos] = (byte)(in[4 + inPos] & 268435455 & 255);
         out[15 + outPos] = (byte)((in[4 + inPos] & 268435455) >>> 8 & 255);
         out[16 + outPos] = (byte)((in[4 + inPos] & 268435455) >>> 16 & 255);
         out[17 + outPos] = (byte)(((in[4 + inPos] & 268435455) >>> 24 | (in[5 + inPos] & 268435455) << 4) & 255);
         out[18 + outPos] = (byte)((in[5 + inPos] & 268435455) >>> 4 & 255);
         out[19 + outPos] = (byte)((in[5 + inPos] & 268435455) >>> 12 & 255);
         out[20 + outPos] = (byte)((in[5 + inPos] & 268435455) >>> 20 & 255);
         out[21 + outPos] = (byte)(in[6 + inPos] & 268435455 & 255);
         out[22 + outPos] = (byte)((in[6 + inPos] & 268435455) >>> 8 & 255);
         out[23 + outPos] = (byte)((in[6 + inPos] & 268435455) >>> 16 & 255);
         out[24 + outPos] = (byte)(((in[6 + inPos] & 268435455) >>> 24 | (in[7 + inPos] & 268435455) << 4) & 255);
         out[25 + outPos] = (byte)((in[7 + inPos] & 268435455) >>> 4 & 255);
         out[26 + outPos] = (byte)((in[7 + inPos] & 268435455) >>> 12 & 255);
         out[27 + outPos] = (byte)((in[7 + inPos] & 268435455) >>> 20 & 255);
         out[28 + outPos] = (byte)(in[8 + inPos] & 268435455 & 255);
         out[29 + outPos] = (byte)((in[8 + inPos] & 268435455) >>> 8 & 255);
         out[30 + outPos] = (byte)((in[8 + inPos] & 268435455) >>> 16 & 255);
         out[31 + outPos] = (byte)(((in[8 + inPos] & 268435455) >>> 24 | (in[9 + inPos] & 268435455) << 4) & 255);
         out[32 + outPos] = (byte)((in[9 + inPos] & 268435455) >>> 4 & 255);
         out[33 + outPos] = (byte)((in[9 + inPos] & 268435455) >>> 12 & 255);
         out[34 + outPos] = (byte)((in[9 + inPos] & 268435455) >>> 20 & 255);
         out[35 + outPos] = (byte)(in[10 + inPos] & 268435455 & 255);
         out[36 + outPos] = (byte)((in[10 + inPos] & 268435455) >>> 8 & 255);
         out[37 + outPos] = (byte)((in[10 + inPos] & 268435455) >>> 16 & 255);
         out[38 + outPos] = (byte)(((in[10 + inPos] & 268435455) >>> 24 | (in[11 + inPos] & 268435455) << 4) & 255);
         out[39 + outPos] = (byte)((in[11 + inPos] & 268435455) >>> 4 & 255);
         out[40 + outPos] = (byte)((in[11 + inPos] & 268435455) >>> 12 & 255);
         out[41 + outPos] = (byte)((in[11 + inPos] & 268435455) >>> 20 & 255);
         out[42 + outPos] = (byte)(in[12 + inPos] & 268435455 & 255);
         out[43 + outPos] = (byte)((in[12 + inPos] & 268435455) >>> 8 & 255);
         out[44 + outPos] = (byte)((in[12 + inPos] & 268435455) >>> 16 & 255);
         out[45 + outPos] = (byte)(((in[12 + inPos] & 268435455) >>> 24 | (in[13 + inPos] & 268435455) << 4) & 255);
         out[46 + outPos] = (byte)((in[13 + inPos] & 268435455) >>> 4 & 255);
         out[47 + outPos] = (byte)((in[13 + inPos] & 268435455) >>> 12 & 255);
         out[48 + outPos] = (byte)((in[13 + inPos] & 268435455) >>> 20 & 255);
         out[49 + outPos] = (byte)(in[14 + inPos] & 268435455 & 255);
         out[50 + outPos] = (byte)((in[14 + inPos] & 268435455) >>> 8 & 255);
         out[51 + outPos] = (byte)((in[14 + inPos] & 268435455) >>> 16 & 255);
         out[52 + outPos] = (byte)(((in[14 + inPos] & 268435455) >>> 24 | (in[15 + inPos] & 268435455) << 4) & 255);
         out[53 + outPos] = (byte)((in[15 + inPos] & 268435455) >>> 4 & 255);
         out[54 + outPos] = (byte)((in[15 + inPos] & 268435455) >>> 12 & 255);
         out[55 + outPos] = (byte)((in[15 + inPos] & 268435455) >>> 20 & 255);
         out[56 + outPos] = (byte)(in[16 + inPos] & 268435455 & 255);
         out[57 + outPos] = (byte)((in[16 + inPos] & 268435455) >>> 8 & 255);
         out[58 + outPos] = (byte)((in[16 + inPos] & 268435455) >>> 16 & 255);
         out[59 + outPos] = (byte)(((in[16 + inPos] & 268435455) >>> 24 | (in[17 + inPos] & 268435455) << 4) & 255);
         out[60 + outPos] = (byte)((in[17 + inPos] & 268435455) >>> 4 & 255);
         out[61 + outPos] = (byte)((in[17 + inPos] & 268435455) >>> 12 & 255);
         out[62 + outPos] = (byte)((in[17 + inPos] & 268435455) >>> 20 & 255);
         out[63 + outPos] = (byte)(in[18 + inPos] & 268435455 & 255);
         out[64 + outPos] = (byte)((in[18 + inPos] & 268435455) >>> 8 & 255);
         out[65 + outPos] = (byte)((in[18 + inPos] & 268435455) >>> 16 & 255);
         out[66 + outPos] = (byte)(((in[18 + inPos] & 268435455) >>> 24 | (in[19 + inPos] & 268435455) << 4) & 255);
         out[67 + outPos] = (byte)((in[19 + inPos] & 268435455) >>> 4 & 255);
         out[68 + outPos] = (byte)((in[19 + inPos] & 268435455) >>> 12 & 255);
         out[69 + outPos] = (byte)((in[19 + inPos] & 268435455) >>> 20 & 255);
         out[70 + outPos] = (byte)(in[20 + inPos] & 268435455 & 255);
         out[71 + outPos] = (byte)((in[20 + inPos] & 268435455) >>> 8 & 255);
         out[72 + outPos] = (byte)((in[20 + inPos] & 268435455) >>> 16 & 255);
         out[73 + outPos] = (byte)(((in[20 + inPos] & 268435455) >>> 24 | (in[21 + inPos] & 268435455) << 4) & 255);
         out[74 + outPos] = (byte)((in[21 + inPos] & 268435455) >>> 4 & 255);
         out[75 + outPos] = (byte)((in[21 + inPos] & 268435455) >>> 12 & 255);
         out[76 + outPos] = (byte)((in[21 + inPos] & 268435455) >>> 20 & 255);
         out[77 + outPos] = (byte)(in[22 + inPos] & 268435455 & 255);
         out[78 + outPos] = (byte)((in[22 + inPos] & 268435455) >>> 8 & 255);
         out[79 + outPos] = (byte)((in[22 + inPos] & 268435455) >>> 16 & 255);
         out[80 + outPos] = (byte)(((in[22 + inPos] & 268435455) >>> 24 | (in[23 + inPos] & 268435455) << 4) & 255);
         out[81 + outPos] = (byte)((in[23 + inPos] & 268435455) >>> 4 & 255);
         out[82 + outPos] = (byte)((in[23 + inPos] & 268435455) >>> 12 & 255);
         out[83 + outPos] = (byte)((in[23 + inPos] & 268435455) >>> 20 & 255);
         out[84 + outPos] = (byte)(in[24 + inPos] & 268435455 & 255);
         out[85 + outPos] = (byte)((in[24 + inPos] & 268435455) >>> 8 & 255);
         out[86 + outPos] = (byte)((in[24 + inPos] & 268435455) >>> 16 & 255);
         out[87 + outPos] = (byte)(((in[24 + inPos] & 268435455) >>> 24 | (in[25 + inPos] & 268435455) << 4) & 255);
         out[88 + outPos] = (byte)((in[25 + inPos] & 268435455) >>> 4 & 255);
         out[89 + outPos] = (byte)((in[25 + inPos] & 268435455) >>> 12 & 255);
         out[90 + outPos] = (byte)((in[25 + inPos] & 268435455) >>> 20 & 255);
         out[91 + outPos] = (byte)(in[26 + inPos] & 268435455 & 255);
         out[92 + outPos] = (byte)((in[26 + inPos] & 268435455) >>> 8 & 255);
         out[93 + outPos] = (byte)((in[26 + inPos] & 268435455) >>> 16 & 255);
         out[94 + outPos] = (byte)(((in[26 + inPos] & 268435455) >>> 24 | (in[27 + inPos] & 268435455) << 4) & 255);
         out[95 + outPos] = (byte)((in[27 + inPos] & 268435455) >>> 4 & 255);
         out[96 + outPos] = (byte)((in[27 + inPos] & 268435455) >>> 12 & 255);
         out[97 + outPos] = (byte)((in[27 + inPos] & 268435455) >>> 20 & 255);
         out[98 + outPos] = (byte)(in[28 + inPos] & 268435455 & 255);
         out[99 + outPos] = (byte)((in[28 + inPos] & 268435455) >>> 8 & 255);
         out[100 + outPos] = (byte)((in[28 + inPos] & 268435455) >>> 16 & 255);
         out[101 + outPos] = (byte)(((in[28 + inPos] & 268435455) >>> 24 | (in[29 + inPos] & 268435455) << 4) & 255);
         out[102 + outPos] = (byte)((in[29 + inPos] & 268435455) >>> 4 & 255);
         out[103 + outPos] = (byte)((in[29 + inPos] & 268435455) >>> 12 & 255);
         out[104 + outPos] = (byte)((in[29 + inPos] & 268435455) >>> 20 & 255);
         out[105 + outPos] = (byte)(in[30 + inPos] & 268435455 & 255);
         out[106 + outPos] = (byte)((in[30 + inPos] & 268435455) >>> 8 & 255);
         out[107 + outPos] = (byte)((in[30 + inPos] & 268435455) >>> 16 & 255);
         out[108 + outPos] = (byte)(((in[30 + inPos] & 268435455) >>> 24 | (in[31 + inPos] & 268435455) << 4) & 255);
         out[109 + outPos] = (byte)((in[31 + inPos] & 268435455) >>> 4 & 255);
         out[110 + outPos] = (byte)((in[31 + inPos] & 268435455) >>> 12 & 255);
         out[111 + outPos] = (byte)((in[31 + inPos] & 268435455) >>> 20 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 16777215 | in[3 + inPos] << 24 & 268435455;
         out[1 + outPos] = in[3 + inPos] >> 4 & 15 | in[4 + inPos] << 4 & 4095 | in[5 + inPos] << 12 & 1048575 | in[6 + inPos] << 20 & 268435455;
         out[2 + outPos] = in[7 + inPos] & 255 | in[8 + inPos] << 8 & '\uffff' | in[9 + inPos] << 16 & 16777215 | in[10 + inPos] << 24 & 268435455;
         out[3 + outPos] = in[10 + inPos] >> 4 & 15 | in[11 + inPos] << 4 & 4095 | in[12 + inPos] << 12 & 1048575 | in[13 + inPos] << 20 & 268435455;
         out[4 + outPos] = in[14 + inPos] & 255 | in[15 + inPos] << 8 & '\uffff' | in[16 + inPos] << 16 & 16777215 | in[17 + inPos] << 24 & 268435455;
         out[5 + outPos] = in[17 + inPos] >> 4 & 15 | in[18 + inPos] << 4 & 4095 | in[19 + inPos] << 12 & 1048575 | in[20 + inPos] << 20 & 268435455;
         out[6 + outPos] = in[21 + inPos] & 255 | in[22 + inPos] << 8 & '\uffff' | in[23 + inPos] << 16 & 16777215 | in[24 + inPos] << 24 & 268435455;
         out[7 + outPos] = in[24 + inPos] >> 4 & 15 | in[25 + inPos] << 4 & 4095 | in[26 + inPos] << 12 & 1048575 | in[27 + inPos] << 20 & 268435455;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 16777215 | in.get(3 + inPos) << 24 & 268435455;
         out[1 + outPos] = in.get(3 + inPos) >> 4 & 15 | in.get(4 + inPos) << 4 & 4095 | in.get(5 + inPos) << 12 & 1048575 | in.get(6 + inPos) << 20 & 268435455;
         out[2 + outPos] = in.get(7 + inPos) & 255 | in.get(8 + inPos) << 8 & '\uffff' | in.get(9 + inPos) << 16 & 16777215 | in.get(10 + inPos) << 24 & 268435455;
         out[3 + outPos] = in.get(10 + inPos) >> 4 & 15 | in.get(11 + inPos) << 4 & 4095 | in.get(12 + inPos) << 12 & 1048575 | in.get(13 + inPos) << 20 & 268435455;
         out[4 + outPos] = in.get(14 + inPos) & 255 | in.get(15 + inPos) << 8 & '\uffff' | in.get(16 + inPos) << 16 & 16777215 | in.get(17 + inPos) << 24 & 268435455;
         out[5 + outPos] = in.get(17 + inPos) >> 4 & 15 | in.get(18 + inPos) << 4 & 4095 | in.get(19 + inPos) << 12 & 1048575 | in.get(20 + inPos) << 20 & 268435455;
         out[6 + outPos] = in.get(21 + inPos) & 255 | in.get(22 + inPos) << 8 & '\uffff' | in.get(23 + inPos) << 16 & 16777215 | in.get(24 + inPos) << 24 & 268435455;
         out[7 + outPos] = in.get(24 + inPos) >> 4 & 15 | in.get(25 + inPos) << 4 & 4095 | in.get(26 + inPos) << 12 & 1048575 | in.get(27 + inPos) << 20 & 268435455;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 16777215 | in[3 + inPos] << 24 & 268435455;
         out[1 + outPos] = in[3 + inPos] >> 4 & 15 | in[4 + inPos] << 4 & 4095 | in[5 + inPos] << 12 & 1048575 | in[6 + inPos] << 20 & 268435455;
         out[2 + outPos] = in[7 + inPos] & 255 | in[8 + inPos] << 8 & '\uffff' | in[9 + inPos] << 16 & 16777215 | in[10 + inPos] << 24 & 268435455;
         out[3 + outPos] = in[10 + inPos] >> 4 & 15 | in[11 + inPos] << 4 & 4095 | in[12 + inPos] << 12 & 1048575 | in[13 + inPos] << 20 & 268435455;
         out[4 + outPos] = in[14 + inPos] & 255 | in[15 + inPos] << 8 & '\uffff' | in[16 + inPos] << 16 & 16777215 | in[17 + inPos] << 24 & 268435455;
         out[5 + outPos] = in[17 + inPos] >> 4 & 15 | in[18 + inPos] << 4 & 4095 | in[19 + inPos] << 12 & 1048575 | in[20 + inPos] << 20 & 268435455;
         out[6 + outPos] = in[21 + inPos] & 255 | in[22 + inPos] << 8 & '\uffff' | in[23 + inPos] << 16 & 16777215 | in[24 + inPos] << 24 & 268435455;
         out[7 + outPos] = in[24 + inPos] >> 4 & 15 | in[25 + inPos] << 4 & 4095 | in[26 + inPos] << 12 & 1048575 | in[27 + inPos] << 20 & 268435455;
         out[8 + outPos] = in[28 + inPos] & 255 | in[29 + inPos] << 8 & '\uffff' | in[30 + inPos] << 16 & 16777215 | in[31 + inPos] << 24 & 268435455;
         out[9 + outPos] = in[31 + inPos] >> 4 & 15 | in[32 + inPos] << 4 & 4095 | in[33 + inPos] << 12 & 1048575 | in[34 + inPos] << 20 & 268435455;
         out[10 + outPos] = in[35 + inPos] & 255 | in[36 + inPos] << 8 & '\uffff' | in[37 + inPos] << 16 & 16777215 | in[38 + inPos] << 24 & 268435455;
         out[11 + outPos] = in[38 + inPos] >> 4 & 15 | in[39 + inPos] << 4 & 4095 | in[40 + inPos] << 12 & 1048575 | in[41 + inPos] << 20 & 268435455;
         out[12 + outPos] = in[42 + inPos] & 255 | in[43 + inPos] << 8 & '\uffff' | in[44 + inPos] << 16 & 16777215 | in[45 + inPos] << 24 & 268435455;
         out[13 + outPos] = in[45 + inPos] >> 4 & 15 | in[46 + inPos] << 4 & 4095 | in[47 + inPos] << 12 & 1048575 | in[48 + inPos] << 20 & 268435455;
         out[14 + outPos] = in[49 + inPos] & 255 | in[50 + inPos] << 8 & '\uffff' | in[51 + inPos] << 16 & 16777215 | in[52 + inPos] << 24 & 268435455;
         out[15 + outPos] = in[52 + inPos] >> 4 & 15 | in[53 + inPos] << 4 & 4095 | in[54 + inPos] << 12 & 1048575 | in[55 + inPos] << 20 & 268435455;
         out[16 + outPos] = in[56 + inPos] & 255 | in[57 + inPos] << 8 & '\uffff' | in[58 + inPos] << 16 & 16777215 | in[59 + inPos] << 24 & 268435455;
         out[17 + outPos] = in[59 + inPos] >> 4 & 15 | in[60 + inPos] << 4 & 4095 | in[61 + inPos] << 12 & 1048575 | in[62 + inPos] << 20 & 268435455;
         out[18 + outPos] = in[63 + inPos] & 255 | in[64 + inPos] << 8 & '\uffff' | in[65 + inPos] << 16 & 16777215 | in[66 + inPos] << 24 & 268435455;
         out[19 + outPos] = in[66 + inPos] >> 4 & 15 | in[67 + inPos] << 4 & 4095 | in[68 + inPos] << 12 & 1048575 | in[69 + inPos] << 20 & 268435455;
         out[20 + outPos] = in[70 + inPos] & 255 | in[71 + inPos] << 8 & '\uffff' | in[72 + inPos] << 16 & 16777215 | in[73 + inPos] << 24 & 268435455;
         out[21 + outPos] = in[73 + inPos] >> 4 & 15 | in[74 + inPos] << 4 & 4095 | in[75 + inPos] << 12 & 1048575 | in[76 + inPos] << 20 & 268435455;
         out[22 + outPos] = in[77 + inPos] & 255 | in[78 + inPos] << 8 & '\uffff' | in[79 + inPos] << 16 & 16777215 | in[80 + inPos] << 24 & 268435455;
         out[23 + outPos] = in[80 + inPos] >> 4 & 15 | in[81 + inPos] << 4 & 4095 | in[82 + inPos] << 12 & 1048575 | in[83 + inPos] << 20 & 268435455;
         out[24 + outPos] = in[84 + inPos] & 255 | in[85 + inPos] << 8 & '\uffff' | in[86 + inPos] << 16 & 16777215 | in[87 + inPos] << 24 & 268435455;
         out[25 + outPos] = in[87 + inPos] >> 4 & 15 | in[88 + inPos] << 4 & 4095 | in[89 + inPos] << 12 & 1048575 | in[90 + inPos] << 20 & 268435455;
         out[26 + outPos] = in[91 + inPos] & 255 | in[92 + inPos] << 8 & '\uffff' | in[93 + inPos] << 16 & 16777215 | in[94 + inPos] << 24 & 268435455;
         out[27 + outPos] = in[94 + inPos] >> 4 & 15 | in[95 + inPos] << 4 & 4095 | in[96 + inPos] << 12 & 1048575 | in[97 + inPos] << 20 & 268435455;
         out[28 + outPos] = in[98 + inPos] & 255 | in[99 + inPos] << 8 & '\uffff' | in[100 + inPos] << 16 & 16777215 | in[101 + inPos] << 24 & 268435455;
         out[29 + outPos] = in[101 + inPos] >> 4 & 15 | in[102 + inPos] << 4 & 4095 | in[103 + inPos] << 12 & 1048575 | in[104 + inPos] << 20 & 268435455;
         out[30 + outPos] = in[105 + inPos] & 255 | in[106 + inPos] << 8 & '\uffff' | in[107 + inPos] << 16 & 16777215 | in[108 + inPos] << 24 & 268435455;
         out[31 + outPos] = in[108 + inPos] >> 4 & 15 | in[109 + inPos] << 4 & 4095 | in[110 + inPos] << 12 & 1048575 | in[111 + inPos] << 20 & 268435455;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 16777215 | in.get(3 + inPos) << 24 & 268435455;
         out[1 + outPos] = in.get(3 + inPos) >> 4 & 15 | in.get(4 + inPos) << 4 & 4095 | in.get(5 + inPos) << 12 & 1048575 | in.get(6 + inPos) << 20 & 268435455;
         out[2 + outPos] = in.get(7 + inPos) & 255 | in.get(8 + inPos) << 8 & '\uffff' | in.get(9 + inPos) << 16 & 16777215 | in.get(10 + inPos) << 24 & 268435455;
         out[3 + outPos] = in.get(10 + inPos) >> 4 & 15 | in.get(11 + inPos) << 4 & 4095 | in.get(12 + inPos) << 12 & 1048575 | in.get(13 + inPos) << 20 & 268435455;
         out[4 + outPos] = in.get(14 + inPos) & 255 | in.get(15 + inPos) << 8 & '\uffff' | in.get(16 + inPos) << 16 & 16777215 | in.get(17 + inPos) << 24 & 268435455;
         out[5 + outPos] = in.get(17 + inPos) >> 4 & 15 | in.get(18 + inPos) << 4 & 4095 | in.get(19 + inPos) << 12 & 1048575 | in.get(20 + inPos) << 20 & 268435455;
         out[6 + outPos] = in.get(21 + inPos) & 255 | in.get(22 + inPos) << 8 & '\uffff' | in.get(23 + inPos) << 16 & 16777215 | in.get(24 + inPos) << 24 & 268435455;
         out[7 + outPos] = in.get(24 + inPos) >> 4 & 15 | in.get(25 + inPos) << 4 & 4095 | in.get(26 + inPos) << 12 & 1048575 | in.get(27 + inPos) << 20 & 268435455;
         out[8 + outPos] = in.get(28 + inPos) & 255 | in.get(29 + inPos) << 8 & '\uffff' | in.get(30 + inPos) << 16 & 16777215 | in.get(31 + inPos) << 24 & 268435455;
         out[9 + outPos] = in.get(31 + inPos) >> 4 & 15 | in.get(32 + inPos) << 4 & 4095 | in.get(33 + inPos) << 12 & 1048575 | in.get(34 + inPos) << 20 & 268435455;
         out[10 + outPos] = in.get(35 + inPos) & 255 | in.get(36 + inPos) << 8 & '\uffff' | in.get(37 + inPos) << 16 & 16777215 | in.get(38 + inPos) << 24 & 268435455;
         out[11 + outPos] = in.get(38 + inPos) >> 4 & 15 | in.get(39 + inPos) << 4 & 4095 | in.get(40 + inPos) << 12 & 1048575 | in.get(41 + inPos) << 20 & 268435455;
         out[12 + outPos] = in.get(42 + inPos) & 255 | in.get(43 + inPos) << 8 & '\uffff' | in.get(44 + inPos) << 16 & 16777215 | in.get(45 + inPos) << 24 & 268435455;
         out[13 + outPos] = in.get(45 + inPos) >> 4 & 15 | in.get(46 + inPos) << 4 & 4095 | in.get(47 + inPos) << 12 & 1048575 | in.get(48 + inPos) << 20 & 268435455;
         out[14 + outPos] = in.get(49 + inPos) & 255 | in.get(50 + inPos) << 8 & '\uffff' | in.get(51 + inPos) << 16 & 16777215 | in.get(52 + inPos) << 24 & 268435455;
         out[15 + outPos] = in.get(52 + inPos) >> 4 & 15 | in.get(53 + inPos) << 4 & 4095 | in.get(54 + inPos) << 12 & 1048575 | in.get(55 + inPos) << 20 & 268435455;
         out[16 + outPos] = in.get(56 + inPos) & 255 | in.get(57 + inPos) << 8 & '\uffff' | in.get(58 + inPos) << 16 & 16777215 | in.get(59 + inPos) << 24 & 268435455;
         out[17 + outPos] = in.get(59 + inPos) >> 4 & 15 | in.get(60 + inPos) << 4 & 4095 | in.get(61 + inPos) << 12 & 1048575 | in.get(62 + inPos) << 20 & 268435455;
         out[18 + outPos] = in.get(63 + inPos) & 255 | in.get(64 + inPos) << 8 & '\uffff' | in.get(65 + inPos) << 16 & 16777215 | in.get(66 + inPos) << 24 & 268435455;
         out[19 + outPos] = in.get(66 + inPos) >> 4 & 15 | in.get(67 + inPos) << 4 & 4095 | in.get(68 + inPos) << 12 & 1048575 | in.get(69 + inPos) << 20 & 268435455;
         out[20 + outPos] = in.get(70 + inPos) & 255 | in.get(71 + inPos) << 8 & '\uffff' | in.get(72 + inPos) << 16 & 16777215 | in.get(73 + inPos) << 24 & 268435455;
         out[21 + outPos] = in.get(73 + inPos) >> 4 & 15 | in.get(74 + inPos) << 4 & 4095 | in.get(75 + inPos) << 12 & 1048575 | in.get(76 + inPos) << 20 & 268435455;
         out[22 + outPos] = in.get(77 + inPos) & 255 | in.get(78 + inPos) << 8 & '\uffff' | in.get(79 + inPos) << 16 & 16777215 | in.get(80 + inPos) << 24 & 268435455;
         out[23 + outPos] = in.get(80 + inPos) >> 4 & 15 | in.get(81 + inPos) << 4 & 4095 | in.get(82 + inPos) << 12 & 1048575 | in.get(83 + inPos) << 20 & 268435455;
         out[24 + outPos] = in.get(84 + inPos) & 255 | in.get(85 + inPos) << 8 & '\uffff' | in.get(86 + inPos) << 16 & 16777215 | in.get(87 + inPos) << 24 & 268435455;
         out[25 + outPos] = in.get(87 + inPos) >> 4 & 15 | in.get(88 + inPos) << 4 & 4095 | in.get(89 + inPos) << 12 & 1048575 | in.get(90 + inPos) << 20 & 268435455;
         out[26 + outPos] = in.get(91 + inPos) & 255 | in.get(92 + inPos) << 8 & '\uffff' | in.get(93 + inPos) << 16 & 16777215 | in.get(94 + inPos) << 24 & 268435455;
         out[27 + outPos] = in.get(94 + inPos) >> 4 & 15 | in.get(95 + inPos) << 4 & 4095 | in.get(96 + inPos) << 12 & 1048575 | in.get(97 + inPos) << 20 & 268435455;
         out[28 + outPos] = in.get(98 + inPos) & 255 | in.get(99 + inPos) << 8 & '\uffff' | in.get(100 + inPos) << 16 & 16777215 | in.get(101 + inPos) << 24 & 268435455;
         out[29 + outPos] = in.get(101 + inPos) >> 4 & 15 | in.get(102 + inPos) << 4 & 4095 | in.get(103 + inPos) << 12 & 1048575 | in.get(104 + inPos) << 20 & 268435455;
         out[30 + outPos] = in.get(105 + inPos) & 255 | in.get(106 + inPos) << 8 & '\uffff' | in.get(107 + inPos) << 16 & 16777215 | in.get(108 + inPos) << 24 & 268435455;
         out[31 + outPos] = in.get(108 + inPos) >> 4 & 15 | in.get(109 + inPos) << 4 & 4095 | in.get(110 + inPos) << 12 & 1048575 | in.get(111 + inPos) << 20 & 268435455;
      }
   }

   private static final class Packer29 extends BytePacker {
      private Packer29() {
         super(29);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 536870911 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 536870911) >>> 8 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & 536870911) >>> 16 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & 536870911) >>> 24 | (in[1 + inPos] & 536870911) << 5) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 536870911) >>> 3 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & 536870911) >>> 11 & 255);
         out[6 + outPos] = (byte)((in[1 + inPos] & 536870911) >>> 19 & 255);
         out[7 + outPos] = (byte)(((in[1 + inPos] & 536870911) >>> 27 | (in[2 + inPos] & 536870911) << 2) & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & 536870911) >>> 6 & 255);
         out[9 + outPos] = (byte)((in[2 + inPos] & 536870911) >>> 14 & 255);
         out[10 + outPos] = (byte)(((in[2 + inPos] & 536870911) >>> 22 | (in[3 + inPos] & 536870911) << 7) & 255);
         out[11 + outPos] = (byte)((in[3 + inPos] & 536870911) >>> 1 & 255);
         out[12 + outPos] = (byte)((in[3 + inPos] & 536870911) >>> 9 & 255);
         out[13 + outPos] = (byte)((in[3 + inPos] & 536870911) >>> 17 & 255);
         out[14 + outPos] = (byte)(((in[3 + inPos] & 536870911) >>> 25 | (in[4 + inPos] & 536870911) << 4) & 255);
         out[15 + outPos] = (byte)((in[4 + inPos] & 536870911) >>> 4 & 255);
         out[16 + outPos] = (byte)((in[4 + inPos] & 536870911) >>> 12 & 255);
         out[17 + outPos] = (byte)((in[4 + inPos] & 536870911) >>> 20 & 255);
         out[18 + outPos] = (byte)(((in[4 + inPos] & 536870911) >>> 28 | (in[5 + inPos] & 536870911) << 1) & 255);
         out[19 + outPos] = (byte)((in[5 + inPos] & 536870911) >>> 7 & 255);
         out[20 + outPos] = (byte)((in[5 + inPos] & 536870911) >>> 15 & 255);
         out[21 + outPos] = (byte)(((in[5 + inPos] & 536870911) >>> 23 | (in[6 + inPos] & 536870911) << 6) & 255);
         out[22 + outPos] = (byte)((in[6 + inPos] & 536870911) >>> 2 & 255);
         out[23 + outPos] = (byte)((in[6 + inPos] & 536870911) >>> 10 & 255);
         out[24 + outPos] = (byte)((in[6 + inPos] & 536870911) >>> 18 & 255);
         out[25 + outPos] = (byte)(((in[6 + inPos] & 536870911) >>> 26 | (in[7 + inPos] & 536870911) << 3) & 255);
         out[26 + outPos] = (byte)((in[7 + inPos] & 536870911) >>> 5 & 255);
         out[27 + outPos] = (byte)((in[7 + inPos] & 536870911) >>> 13 & 255);
         out[28 + outPos] = (byte)((in[7 + inPos] & 536870911) >>> 21 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 536870911 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 536870911) >>> 8 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & 536870911) >>> 16 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & 536870911) >>> 24 | (in[1 + inPos] & 536870911) << 5) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 536870911) >>> 3 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & 536870911) >>> 11 & 255);
         out[6 + outPos] = (byte)((in[1 + inPos] & 536870911) >>> 19 & 255);
         out[7 + outPos] = (byte)(((in[1 + inPos] & 536870911) >>> 27 | (in[2 + inPos] & 536870911) << 2) & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & 536870911) >>> 6 & 255);
         out[9 + outPos] = (byte)((in[2 + inPos] & 536870911) >>> 14 & 255);
         out[10 + outPos] = (byte)(((in[2 + inPos] & 536870911) >>> 22 | (in[3 + inPos] & 536870911) << 7) & 255);
         out[11 + outPos] = (byte)((in[3 + inPos] & 536870911) >>> 1 & 255);
         out[12 + outPos] = (byte)((in[3 + inPos] & 536870911) >>> 9 & 255);
         out[13 + outPos] = (byte)((in[3 + inPos] & 536870911) >>> 17 & 255);
         out[14 + outPos] = (byte)(((in[3 + inPos] & 536870911) >>> 25 | (in[4 + inPos] & 536870911) << 4) & 255);
         out[15 + outPos] = (byte)((in[4 + inPos] & 536870911) >>> 4 & 255);
         out[16 + outPos] = (byte)((in[4 + inPos] & 536870911) >>> 12 & 255);
         out[17 + outPos] = (byte)((in[4 + inPos] & 536870911) >>> 20 & 255);
         out[18 + outPos] = (byte)(((in[4 + inPos] & 536870911) >>> 28 | (in[5 + inPos] & 536870911) << 1) & 255);
         out[19 + outPos] = (byte)((in[5 + inPos] & 536870911) >>> 7 & 255);
         out[20 + outPos] = (byte)((in[5 + inPos] & 536870911) >>> 15 & 255);
         out[21 + outPos] = (byte)(((in[5 + inPos] & 536870911) >>> 23 | (in[6 + inPos] & 536870911) << 6) & 255);
         out[22 + outPos] = (byte)((in[6 + inPos] & 536870911) >>> 2 & 255);
         out[23 + outPos] = (byte)((in[6 + inPos] & 536870911) >>> 10 & 255);
         out[24 + outPos] = (byte)((in[6 + inPos] & 536870911) >>> 18 & 255);
         out[25 + outPos] = (byte)(((in[6 + inPos] & 536870911) >>> 26 | (in[7 + inPos] & 536870911) << 3) & 255);
         out[26 + outPos] = (byte)((in[7 + inPos] & 536870911) >>> 5 & 255);
         out[27 + outPos] = (byte)((in[7 + inPos] & 536870911) >>> 13 & 255);
         out[28 + outPos] = (byte)((in[7 + inPos] & 536870911) >>> 21 & 255);
         out[29 + outPos] = (byte)(in[8 + inPos] & 536870911 & 255);
         out[30 + outPos] = (byte)((in[8 + inPos] & 536870911) >>> 8 & 255);
         out[31 + outPos] = (byte)((in[8 + inPos] & 536870911) >>> 16 & 255);
         out[32 + outPos] = (byte)(((in[8 + inPos] & 536870911) >>> 24 | (in[9 + inPos] & 536870911) << 5) & 255);
         out[33 + outPos] = (byte)((in[9 + inPos] & 536870911) >>> 3 & 255);
         out[34 + outPos] = (byte)((in[9 + inPos] & 536870911) >>> 11 & 255);
         out[35 + outPos] = (byte)((in[9 + inPos] & 536870911) >>> 19 & 255);
         out[36 + outPos] = (byte)(((in[9 + inPos] & 536870911) >>> 27 | (in[10 + inPos] & 536870911) << 2) & 255);
         out[37 + outPos] = (byte)((in[10 + inPos] & 536870911) >>> 6 & 255);
         out[38 + outPos] = (byte)((in[10 + inPos] & 536870911) >>> 14 & 255);
         out[39 + outPos] = (byte)(((in[10 + inPos] & 536870911) >>> 22 | (in[11 + inPos] & 536870911) << 7) & 255);
         out[40 + outPos] = (byte)((in[11 + inPos] & 536870911) >>> 1 & 255);
         out[41 + outPos] = (byte)((in[11 + inPos] & 536870911) >>> 9 & 255);
         out[42 + outPos] = (byte)((in[11 + inPos] & 536870911) >>> 17 & 255);
         out[43 + outPos] = (byte)(((in[11 + inPos] & 536870911) >>> 25 | (in[12 + inPos] & 536870911) << 4) & 255);
         out[44 + outPos] = (byte)((in[12 + inPos] & 536870911) >>> 4 & 255);
         out[45 + outPos] = (byte)((in[12 + inPos] & 536870911) >>> 12 & 255);
         out[46 + outPos] = (byte)((in[12 + inPos] & 536870911) >>> 20 & 255);
         out[47 + outPos] = (byte)(((in[12 + inPos] & 536870911) >>> 28 | (in[13 + inPos] & 536870911) << 1) & 255);
         out[48 + outPos] = (byte)((in[13 + inPos] & 536870911) >>> 7 & 255);
         out[49 + outPos] = (byte)((in[13 + inPos] & 536870911) >>> 15 & 255);
         out[50 + outPos] = (byte)(((in[13 + inPos] & 536870911) >>> 23 | (in[14 + inPos] & 536870911) << 6) & 255);
         out[51 + outPos] = (byte)((in[14 + inPos] & 536870911) >>> 2 & 255);
         out[52 + outPos] = (byte)((in[14 + inPos] & 536870911) >>> 10 & 255);
         out[53 + outPos] = (byte)((in[14 + inPos] & 536870911) >>> 18 & 255);
         out[54 + outPos] = (byte)(((in[14 + inPos] & 536870911) >>> 26 | (in[15 + inPos] & 536870911) << 3) & 255);
         out[55 + outPos] = (byte)((in[15 + inPos] & 536870911) >>> 5 & 255);
         out[56 + outPos] = (byte)((in[15 + inPos] & 536870911) >>> 13 & 255);
         out[57 + outPos] = (byte)((in[15 + inPos] & 536870911) >>> 21 & 255);
         out[58 + outPos] = (byte)(in[16 + inPos] & 536870911 & 255);
         out[59 + outPos] = (byte)((in[16 + inPos] & 536870911) >>> 8 & 255);
         out[60 + outPos] = (byte)((in[16 + inPos] & 536870911) >>> 16 & 255);
         out[61 + outPos] = (byte)(((in[16 + inPos] & 536870911) >>> 24 | (in[17 + inPos] & 536870911) << 5) & 255);
         out[62 + outPos] = (byte)((in[17 + inPos] & 536870911) >>> 3 & 255);
         out[63 + outPos] = (byte)((in[17 + inPos] & 536870911) >>> 11 & 255);
         out[64 + outPos] = (byte)((in[17 + inPos] & 536870911) >>> 19 & 255);
         out[65 + outPos] = (byte)(((in[17 + inPos] & 536870911) >>> 27 | (in[18 + inPos] & 536870911) << 2) & 255);
         out[66 + outPos] = (byte)((in[18 + inPos] & 536870911) >>> 6 & 255);
         out[67 + outPos] = (byte)((in[18 + inPos] & 536870911) >>> 14 & 255);
         out[68 + outPos] = (byte)(((in[18 + inPos] & 536870911) >>> 22 | (in[19 + inPos] & 536870911) << 7) & 255);
         out[69 + outPos] = (byte)((in[19 + inPos] & 536870911) >>> 1 & 255);
         out[70 + outPos] = (byte)((in[19 + inPos] & 536870911) >>> 9 & 255);
         out[71 + outPos] = (byte)((in[19 + inPos] & 536870911) >>> 17 & 255);
         out[72 + outPos] = (byte)(((in[19 + inPos] & 536870911) >>> 25 | (in[20 + inPos] & 536870911) << 4) & 255);
         out[73 + outPos] = (byte)((in[20 + inPos] & 536870911) >>> 4 & 255);
         out[74 + outPos] = (byte)((in[20 + inPos] & 536870911) >>> 12 & 255);
         out[75 + outPos] = (byte)((in[20 + inPos] & 536870911) >>> 20 & 255);
         out[76 + outPos] = (byte)(((in[20 + inPos] & 536870911) >>> 28 | (in[21 + inPos] & 536870911) << 1) & 255);
         out[77 + outPos] = (byte)((in[21 + inPos] & 536870911) >>> 7 & 255);
         out[78 + outPos] = (byte)((in[21 + inPos] & 536870911) >>> 15 & 255);
         out[79 + outPos] = (byte)(((in[21 + inPos] & 536870911) >>> 23 | (in[22 + inPos] & 536870911) << 6) & 255);
         out[80 + outPos] = (byte)((in[22 + inPos] & 536870911) >>> 2 & 255);
         out[81 + outPos] = (byte)((in[22 + inPos] & 536870911) >>> 10 & 255);
         out[82 + outPos] = (byte)((in[22 + inPos] & 536870911) >>> 18 & 255);
         out[83 + outPos] = (byte)(((in[22 + inPos] & 536870911) >>> 26 | (in[23 + inPos] & 536870911) << 3) & 255);
         out[84 + outPos] = (byte)((in[23 + inPos] & 536870911) >>> 5 & 255);
         out[85 + outPos] = (byte)((in[23 + inPos] & 536870911) >>> 13 & 255);
         out[86 + outPos] = (byte)((in[23 + inPos] & 536870911) >>> 21 & 255);
         out[87 + outPos] = (byte)(in[24 + inPos] & 536870911 & 255);
         out[88 + outPos] = (byte)((in[24 + inPos] & 536870911) >>> 8 & 255);
         out[89 + outPos] = (byte)((in[24 + inPos] & 536870911) >>> 16 & 255);
         out[90 + outPos] = (byte)(((in[24 + inPos] & 536870911) >>> 24 | (in[25 + inPos] & 536870911) << 5) & 255);
         out[91 + outPos] = (byte)((in[25 + inPos] & 536870911) >>> 3 & 255);
         out[92 + outPos] = (byte)((in[25 + inPos] & 536870911) >>> 11 & 255);
         out[93 + outPos] = (byte)((in[25 + inPos] & 536870911) >>> 19 & 255);
         out[94 + outPos] = (byte)(((in[25 + inPos] & 536870911) >>> 27 | (in[26 + inPos] & 536870911) << 2) & 255);
         out[95 + outPos] = (byte)((in[26 + inPos] & 536870911) >>> 6 & 255);
         out[96 + outPos] = (byte)((in[26 + inPos] & 536870911) >>> 14 & 255);
         out[97 + outPos] = (byte)(((in[26 + inPos] & 536870911) >>> 22 | (in[27 + inPos] & 536870911) << 7) & 255);
         out[98 + outPos] = (byte)((in[27 + inPos] & 536870911) >>> 1 & 255);
         out[99 + outPos] = (byte)((in[27 + inPos] & 536870911) >>> 9 & 255);
         out[100 + outPos] = (byte)((in[27 + inPos] & 536870911) >>> 17 & 255);
         out[101 + outPos] = (byte)(((in[27 + inPos] & 536870911) >>> 25 | (in[28 + inPos] & 536870911) << 4) & 255);
         out[102 + outPos] = (byte)((in[28 + inPos] & 536870911) >>> 4 & 255);
         out[103 + outPos] = (byte)((in[28 + inPos] & 536870911) >>> 12 & 255);
         out[104 + outPos] = (byte)((in[28 + inPos] & 536870911) >>> 20 & 255);
         out[105 + outPos] = (byte)(((in[28 + inPos] & 536870911) >>> 28 | (in[29 + inPos] & 536870911) << 1) & 255);
         out[106 + outPos] = (byte)((in[29 + inPos] & 536870911) >>> 7 & 255);
         out[107 + outPos] = (byte)((in[29 + inPos] & 536870911) >>> 15 & 255);
         out[108 + outPos] = (byte)(((in[29 + inPos] & 536870911) >>> 23 | (in[30 + inPos] & 536870911) << 6) & 255);
         out[109 + outPos] = (byte)((in[30 + inPos] & 536870911) >>> 2 & 255);
         out[110 + outPos] = (byte)((in[30 + inPos] & 536870911) >>> 10 & 255);
         out[111 + outPos] = (byte)((in[30 + inPos] & 536870911) >>> 18 & 255);
         out[112 + outPos] = (byte)(((in[30 + inPos] & 536870911) >>> 26 | (in[31 + inPos] & 536870911) << 3) & 255);
         out[113 + outPos] = (byte)((in[31 + inPos] & 536870911) >>> 5 & 255);
         out[114 + outPos] = (byte)((in[31 + inPos] & 536870911) >>> 13 & 255);
         out[115 + outPos] = (byte)((in[31 + inPos] & 536870911) >>> 21 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 16777215 | in[3 + inPos] << 24 & 536870911;
         out[1 + outPos] = in[3 + inPos] >> 5 & 7 | in[4 + inPos] << 3 & 2047 | in[5 + inPos] << 11 & 524287 | in[6 + inPos] << 19 & 134217727 | in[7 + inPos] << 27 & 536870911;
         out[2 + outPos] = in[7 + inPos] >> 2 & 63 | in[8 + inPos] << 6 & 16383 | in[9 + inPos] << 14 & 4194303 | in[10 + inPos] << 22 & 536870911;
         out[3 + outPos] = in[10 + inPos] >> 7 & 1 | in[11 + inPos] << 1 & 511 | in[12 + inPos] << 9 & 131071 | in[13 + inPos] << 17 & 33554431 | in[14 + inPos] << 25 & 536870911;
         out[4 + outPos] = in[14 + inPos] >> 4 & 15 | in[15 + inPos] << 4 & 4095 | in[16 + inPos] << 12 & 1048575 | in[17 + inPos] << 20 & 268435455 | in[18 + inPos] << 28 & 536870911;
         out[5 + outPos] = in[18 + inPos] >> 1 & 127 | in[19 + inPos] << 7 & 32767 | in[20 + inPos] << 15 & 8388607 | in[21 + inPos] << 23 & 536870911;
         out[6 + outPos] = in[21 + inPos] >> 6 & 3 | in[22 + inPos] << 2 & 1023 | in[23 + inPos] << 10 & 262143 | in[24 + inPos] << 18 & 67108863 | in[25 + inPos] << 26 & 536870911;
         out[7 + outPos] = in[25 + inPos] >> 3 & 31 | in[26 + inPos] << 5 & 8191 | in[27 + inPos] << 13 & 2097151 | in[28 + inPos] << 21 & 536870911;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 16777215 | in.get(3 + inPos) << 24 & 536870911;
         out[1 + outPos] = in.get(3 + inPos) >> 5 & 7 | in.get(4 + inPos) << 3 & 2047 | in.get(5 + inPos) << 11 & 524287 | in.get(6 + inPos) << 19 & 134217727 | in.get(7 + inPos) << 27 & 536870911;
         out[2 + outPos] = in.get(7 + inPos) >> 2 & 63 | in.get(8 + inPos) << 6 & 16383 | in.get(9 + inPos) << 14 & 4194303 | in.get(10 + inPos) << 22 & 536870911;
         out[3 + outPos] = in.get(10 + inPos) >> 7 & 1 | in.get(11 + inPos) << 1 & 511 | in.get(12 + inPos) << 9 & 131071 | in.get(13 + inPos) << 17 & 33554431 | in.get(14 + inPos) << 25 & 536870911;
         out[4 + outPos] = in.get(14 + inPos) >> 4 & 15 | in.get(15 + inPos) << 4 & 4095 | in.get(16 + inPos) << 12 & 1048575 | in.get(17 + inPos) << 20 & 268435455 | in.get(18 + inPos) << 28 & 536870911;
         out[5 + outPos] = in.get(18 + inPos) >> 1 & 127 | in.get(19 + inPos) << 7 & 32767 | in.get(20 + inPos) << 15 & 8388607 | in.get(21 + inPos) << 23 & 536870911;
         out[6 + outPos] = in.get(21 + inPos) >> 6 & 3 | in.get(22 + inPos) << 2 & 1023 | in.get(23 + inPos) << 10 & 262143 | in.get(24 + inPos) << 18 & 67108863 | in.get(25 + inPos) << 26 & 536870911;
         out[7 + outPos] = in.get(25 + inPos) >> 3 & 31 | in.get(26 + inPos) << 5 & 8191 | in.get(27 + inPos) << 13 & 2097151 | in.get(28 + inPos) << 21 & 536870911;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 16777215 | in[3 + inPos] << 24 & 536870911;
         out[1 + outPos] = in[3 + inPos] >> 5 & 7 | in[4 + inPos] << 3 & 2047 | in[5 + inPos] << 11 & 524287 | in[6 + inPos] << 19 & 134217727 | in[7 + inPos] << 27 & 536870911;
         out[2 + outPos] = in[7 + inPos] >> 2 & 63 | in[8 + inPos] << 6 & 16383 | in[9 + inPos] << 14 & 4194303 | in[10 + inPos] << 22 & 536870911;
         out[3 + outPos] = in[10 + inPos] >> 7 & 1 | in[11 + inPos] << 1 & 511 | in[12 + inPos] << 9 & 131071 | in[13 + inPos] << 17 & 33554431 | in[14 + inPos] << 25 & 536870911;
         out[4 + outPos] = in[14 + inPos] >> 4 & 15 | in[15 + inPos] << 4 & 4095 | in[16 + inPos] << 12 & 1048575 | in[17 + inPos] << 20 & 268435455 | in[18 + inPos] << 28 & 536870911;
         out[5 + outPos] = in[18 + inPos] >> 1 & 127 | in[19 + inPos] << 7 & 32767 | in[20 + inPos] << 15 & 8388607 | in[21 + inPos] << 23 & 536870911;
         out[6 + outPos] = in[21 + inPos] >> 6 & 3 | in[22 + inPos] << 2 & 1023 | in[23 + inPos] << 10 & 262143 | in[24 + inPos] << 18 & 67108863 | in[25 + inPos] << 26 & 536870911;
         out[7 + outPos] = in[25 + inPos] >> 3 & 31 | in[26 + inPos] << 5 & 8191 | in[27 + inPos] << 13 & 2097151 | in[28 + inPos] << 21 & 536870911;
         out[8 + outPos] = in[29 + inPos] & 255 | in[30 + inPos] << 8 & '\uffff' | in[31 + inPos] << 16 & 16777215 | in[32 + inPos] << 24 & 536870911;
         out[9 + outPos] = in[32 + inPos] >> 5 & 7 | in[33 + inPos] << 3 & 2047 | in[34 + inPos] << 11 & 524287 | in[35 + inPos] << 19 & 134217727 | in[36 + inPos] << 27 & 536870911;
         out[10 + outPos] = in[36 + inPos] >> 2 & 63 | in[37 + inPos] << 6 & 16383 | in[38 + inPos] << 14 & 4194303 | in[39 + inPos] << 22 & 536870911;
         out[11 + outPos] = in[39 + inPos] >> 7 & 1 | in[40 + inPos] << 1 & 511 | in[41 + inPos] << 9 & 131071 | in[42 + inPos] << 17 & 33554431 | in[43 + inPos] << 25 & 536870911;
         out[12 + outPos] = in[43 + inPos] >> 4 & 15 | in[44 + inPos] << 4 & 4095 | in[45 + inPos] << 12 & 1048575 | in[46 + inPos] << 20 & 268435455 | in[47 + inPos] << 28 & 536870911;
         out[13 + outPos] = in[47 + inPos] >> 1 & 127 | in[48 + inPos] << 7 & 32767 | in[49 + inPos] << 15 & 8388607 | in[50 + inPos] << 23 & 536870911;
         out[14 + outPos] = in[50 + inPos] >> 6 & 3 | in[51 + inPos] << 2 & 1023 | in[52 + inPos] << 10 & 262143 | in[53 + inPos] << 18 & 67108863 | in[54 + inPos] << 26 & 536870911;
         out[15 + outPos] = in[54 + inPos] >> 3 & 31 | in[55 + inPos] << 5 & 8191 | in[56 + inPos] << 13 & 2097151 | in[57 + inPos] << 21 & 536870911;
         out[16 + outPos] = in[58 + inPos] & 255 | in[59 + inPos] << 8 & '\uffff' | in[60 + inPos] << 16 & 16777215 | in[61 + inPos] << 24 & 536870911;
         out[17 + outPos] = in[61 + inPos] >> 5 & 7 | in[62 + inPos] << 3 & 2047 | in[63 + inPos] << 11 & 524287 | in[64 + inPos] << 19 & 134217727 | in[65 + inPos] << 27 & 536870911;
         out[18 + outPos] = in[65 + inPos] >> 2 & 63 | in[66 + inPos] << 6 & 16383 | in[67 + inPos] << 14 & 4194303 | in[68 + inPos] << 22 & 536870911;
         out[19 + outPos] = in[68 + inPos] >> 7 & 1 | in[69 + inPos] << 1 & 511 | in[70 + inPos] << 9 & 131071 | in[71 + inPos] << 17 & 33554431 | in[72 + inPos] << 25 & 536870911;
         out[20 + outPos] = in[72 + inPos] >> 4 & 15 | in[73 + inPos] << 4 & 4095 | in[74 + inPos] << 12 & 1048575 | in[75 + inPos] << 20 & 268435455 | in[76 + inPos] << 28 & 536870911;
         out[21 + outPos] = in[76 + inPos] >> 1 & 127 | in[77 + inPos] << 7 & 32767 | in[78 + inPos] << 15 & 8388607 | in[79 + inPos] << 23 & 536870911;
         out[22 + outPos] = in[79 + inPos] >> 6 & 3 | in[80 + inPos] << 2 & 1023 | in[81 + inPos] << 10 & 262143 | in[82 + inPos] << 18 & 67108863 | in[83 + inPos] << 26 & 536870911;
         out[23 + outPos] = in[83 + inPos] >> 3 & 31 | in[84 + inPos] << 5 & 8191 | in[85 + inPos] << 13 & 2097151 | in[86 + inPos] << 21 & 536870911;
         out[24 + outPos] = in[87 + inPos] & 255 | in[88 + inPos] << 8 & '\uffff' | in[89 + inPos] << 16 & 16777215 | in[90 + inPos] << 24 & 536870911;
         out[25 + outPos] = in[90 + inPos] >> 5 & 7 | in[91 + inPos] << 3 & 2047 | in[92 + inPos] << 11 & 524287 | in[93 + inPos] << 19 & 134217727 | in[94 + inPos] << 27 & 536870911;
         out[26 + outPos] = in[94 + inPos] >> 2 & 63 | in[95 + inPos] << 6 & 16383 | in[96 + inPos] << 14 & 4194303 | in[97 + inPos] << 22 & 536870911;
         out[27 + outPos] = in[97 + inPos] >> 7 & 1 | in[98 + inPos] << 1 & 511 | in[99 + inPos] << 9 & 131071 | in[100 + inPos] << 17 & 33554431 | in[101 + inPos] << 25 & 536870911;
         out[28 + outPos] = in[101 + inPos] >> 4 & 15 | in[102 + inPos] << 4 & 4095 | in[103 + inPos] << 12 & 1048575 | in[104 + inPos] << 20 & 268435455 | in[105 + inPos] << 28 & 536870911;
         out[29 + outPos] = in[105 + inPos] >> 1 & 127 | in[106 + inPos] << 7 & 32767 | in[107 + inPos] << 15 & 8388607 | in[108 + inPos] << 23 & 536870911;
         out[30 + outPos] = in[108 + inPos] >> 6 & 3 | in[109 + inPos] << 2 & 1023 | in[110 + inPos] << 10 & 262143 | in[111 + inPos] << 18 & 67108863 | in[112 + inPos] << 26 & 536870911;
         out[31 + outPos] = in[112 + inPos] >> 3 & 31 | in[113 + inPos] << 5 & 8191 | in[114 + inPos] << 13 & 2097151 | in[115 + inPos] << 21 & 536870911;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 16777215 | in.get(3 + inPos) << 24 & 536870911;
         out[1 + outPos] = in.get(3 + inPos) >> 5 & 7 | in.get(4 + inPos) << 3 & 2047 | in.get(5 + inPos) << 11 & 524287 | in.get(6 + inPos) << 19 & 134217727 | in.get(7 + inPos) << 27 & 536870911;
         out[2 + outPos] = in.get(7 + inPos) >> 2 & 63 | in.get(8 + inPos) << 6 & 16383 | in.get(9 + inPos) << 14 & 4194303 | in.get(10 + inPos) << 22 & 536870911;
         out[3 + outPos] = in.get(10 + inPos) >> 7 & 1 | in.get(11 + inPos) << 1 & 511 | in.get(12 + inPos) << 9 & 131071 | in.get(13 + inPos) << 17 & 33554431 | in.get(14 + inPos) << 25 & 536870911;
         out[4 + outPos] = in.get(14 + inPos) >> 4 & 15 | in.get(15 + inPos) << 4 & 4095 | in.get(16 + inPos) << 12 & 1048575 | in.get(17 + inPos) << 20 & 268435455 | in.get(18 + inPos) << 28 & 536870911;
         out[5 + outPos] = in.get(18 + inPos) >> 1 & 127 | in.get(19 + inPos) << 7 & 32767 | in.get(20 + inPos) << 15 & 8388607 | in.get(21 + inPos) << 23 & 536870911;
         out[6 + outPos] = in.get(21 + inPos) >> 6 & 3 | in.get(22 + inPos) << 2 & 1023 | in.get(23 + inPos) << 10 & 262143 | in.get(24 + inPos) << 18 & 67108863 | in.get(25 + inPos) << 26 & 536870911;
         out[7 + outPos] = in.get(25 + inPos) >> 3 & 31 | in.get(26 + inPos) << 5 & 8191 | in.get(27 + inPos) << 13 & 2097151 | in.get(28 + inPos) << 21 & 536870911;
         out[8 + outPos] = in.get(29 + inPos) & 255 | in.get(30 + inPos) << 8 & '\uffff' | in.get(31 + inPos) << 16 & 16777215 | in.get(32 + inPos) << 24 & 536870911;
         out[9 + outPos] = in.get(32 + inPos) >> 5 & 7 | in.get(33 + inPos) << 3 & 2047 | in.get(34 + inPos) << 11 & 524287 | in.get(35 + inPos) << 19 & 134217727 | in.get(36 + inPos) << 27 & 536870911;
         out[10 + outPos] = in.get(36 + inPos) >> 2 & 63 | in.get(37 + inPos) << 6 & 16383 | in.get(38 + inPos) << 14 & 4194303 | in.get(39 + inPos) << 22 & 536870911;
         out[11 + outPos] = in.get(39 + inPos) >> 7 & 1 | in.get(40 + inPos) << 1 & 511 | in.get(41 + inPos) << 9 & 131071 | in.get(42 + inPos) << 17 & 33554431 | in.get(43 + inPos) << 25 & 536870911;
         out[12 + outPos] = in.get(43 + inPos) >> 4 & 15 | in.get(44 + inPos) << 4 & 4095 | in.get(45 + inPos) << 12 & 1048575 | in.get(46 + inPos) << 20 & 268435455 | in.get(47 + inPos) << 28 & 536870911;
         out[13 + outPos] = in.get(47 + inPos) >> 1 & 127 | in.get(48 + inPos) << 7 & 32767 | in.get(49 + inPos) << 15 & 8388607 | in.get(50 + inPos) << 23 & 536870911;
         out[14 + outPos] = in.get(50 + inPos) >> 6 & 3 | in.get(51 + inPos) << 2 & 1023 | in.get(52 + inPos) << 10 & 262143 | in.get(53 + inPos) << 18 & 67108863 | in.get(54 + inPos) << 26 & 536870911;
         out[15 + outPos] = in.get(54 + inPos) >> 3 & 31 | in.get(55 + inPos) << 5 & 8191 | in.get(56 + inPos) << 13 & 2097151 | in.get(57 + inPos) << 21 & 536870911;
         out[16 + outPos] = in.get(58 + inPos) & 255 | in.get(59 + inPos) << 8 & '\uffff' | in.get(60 + inPos) << 16 & 16777215 | in.get(61 + inPos) << 24 & 536870911;
         out[17 + outPos] = in.get(61 + inPos) >> 5 & 7 | in.get(62 + inPos) << 3 & 2047 | in.get(63 + inPos) << 11 & 524287 | in.get(64 + inPos) << 19 & 134217727 | in.get(65 + inPos) << 27 & 536870911;
         out[18 + outPos] = in.get(65 + inPos) >> 2 & 63 | in.get(66 + inPos) << 6 & 16383 | in.get(67 + inPos) << 14 & 4194303 | in.get(68 + inPos) << 22 & 536870911;
         out[19 + outPos] = in.get(68 + inPos) >> 7 & 1 | in.get(69 + inPos) << 1 & 511 | in.get(70 + inPos) << 9 & 131071 | in.get(71 + inPos) << 17 & 33554431 | in.get(72 + inPos) << 25 & 536870911;
         out[20 + outPos] = in.get(72 + inPos) >> 4 & 15 | in.get(73 + inPos) << 4 & 4095 | in.get(74 + inPos) << 12 & 1048575 | in.get(75 + inPos) << 20 & 268435455 | in.get(76 + inPos) << 28 & 536870911;
         out[21 + outPos] = in.get(76 + inPos) >> 1 & 127 | in.get(77 + inPos) << 7 & 32767 | in.get(78 + inPos) << 15 & 8388607 | in.get(79 + inPos) << 23 & 536870911;
         out[22 + outPos] = in.get(79 + inPos) >> 6 & 3 | in.get(80 + inPos) << 2 & 1023 | in.get(81 + inPos) << 10 & 262143 | in.get(82 + inPos) << 18 & 67108863 | in.get(83 + inPos) << 26 & 536870911;
         out[23 + outPos] = in.get(83 + inPos) >> 3 & 31 | in.get(84 + inPos) << 5 & 8191 | in.get(85 + inPos) << 13 & 2097151 | in.get(86 + inPos) << 21 & 536870911;
         out[24 + outPos] = in.get(87 + inPos) & 255 | in.get(88 + inPos) << 8 & '\uffff' | in.get(89 + inPos) << 16 & 16777215 | in.get(90 + inPos) << 24 & 536870911;
         out[25 + outPos] = in.get(90 + inPos) >> 5 & 7 | in.get(91 + inPos) << 3 & 2047 | in.get(92 + inPos) << 11 & 524287 | in.get(93 + inPos) << 19 & 134217727 | in.get(94 + inPos) << 27 & 536870911;
         out[26 + outPos] = in.get(94 + inPos) >> 2 & 63 | in.get(95 + inPos) << 6 & 16383 | in.get(96 + inPos) << 14 & 4194303 | in.get(97 + inPos) << 22 & 536870911;
         out[27 + outPos] = in.get(97 + inPos) >> 7 & 1 | in.get(98 + inPos) << 1 & 511 | in.get(99 + inPos) << 9 & 131071 | in.get(100 + inPos) << 17 & 33554431 | in.get(101 + inPos) << 25 & 536870911;
         out[28 + outPos] = in.get(101 + inPos) >> 4 & 15 | in.get(102 + inPos) << 4 & 4095 | in.get(103 + inPos) << 12 & 1048575 | in.get(104 + inPos) << 20 & 268435455 | in.get(105 + inPos) << 28 & 536870911;
         out[29 + outPos] = in.get(105 + inPos) >> 1 & 127 | in.get(106 + inPos) << 7 & 32767 | in.get(107 + inPos) << 15 & 8388607 | in.get(108 + inPos) << 23 & 536870911;
         out[30 + outPos] = in.get(108 + inPos) >> 6 & 3 | in.get(109 + inPos) << 2 & 1023 | in.get(110 + inPos) << 10 & 262143 | in.get(111 + inPos) << 18 & 67108863 | in.get(112 + inPos) << 26 & 536870911;
         out[31 + outPos] = in.get(112 + inPos) >> 3 & 31 | in.get(113 + inPos) << 5 & 8191 | in.get(114 + inPos) << 13 & 2097151 | in.get(115 + inPos) << 21 & 536870911;
      }
   }

   private static final class Packer30 extends BytePacker {
      private Packer30() {
         super(30);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 1073741823 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 1073741823) >>> 8 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & 1073741823) >>> 16 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & 1073741823) >>> 24 | (in[1 + inPos] & 1073741823) << 6) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 1073741823) >>> 2 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & 1073741823) >>> 10 & 255);
         out[6 + outPos] = (byte)((in[1 + inPos] & 1073741823) >>> 18 & 255);
         out[7 + outPos] = (byte)(((in[1 + inPos] & 1073741823) >>> 26 | (in[2 + inPos] & 1073741823) << 4) & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & 1073741823) >>> 4 & 255);
         out[9 + outPos] = (byte)((in[2 + inPos] & 1073741823) >>> 12 & 255);
         out[10 + outPos] = (byte)((in[2 + inPos] & 1073741823) >>> 20 & 255);
         out[11 + outPos] = (byte)(((in[2 + inPos] & 1073741823) >>> 28 | (in[3 + inPos] & 1073741823) << 2) & 255);
         out[12 + outPos] = (byte)((in[3 + inPos] & 1073741823) >>> 6 & 255);
         out[13 + outPos] = (byte)((in[3 + inPos] & 1073741823) >>> 14 & 255);
         out[14 + outPos] = (byte)((in[3 + inPos] & 1073741823) >>> 22 & 255);
         out[15 + outPos] = (byte)(in[4 + inPos] & 1073741823 & 255);
         out[16 + outPos] = (byte)((in[4 + inPos] & 1073741823) >>> 8 & 255);
         out[17 + outPos] = (byte)((in[4 + inPos] & 1073741823) >>> 16 & 255);
         out[18 + outPos] = (byte)(((in[4 + inPos] & 1073741823) >>> 24 | (in[5 + inPos] & 1073741823) << 6) & 255);
         out[19 + outPos] = (byte)((in[5 + inPos] & 1073741823) >>> 2 & 255);
         out[20 + outPos] = (byte)((in[5 + inPos] & 1073741823) >>> 10 & 255);
         out[21 + outPos] = (byte)((in[5 + inPos] & 1073741823) >>> 18 & 255);
         out[22 + outPos] = (byte)(((in[5 + inPos] & 1073741823) >>> 26 | (in[6 + inPos] & 1073741823) << 4) & 255);
         out[23 + outPos] = (byte)((in[6 + inPos] & 1073741823) >>> 4 & 255);
         out[24 + outPos] = (byte)((in[6 + inPos] & 1073741823) >>> 12 & 255);
         out[25 + outPos] = (byte)((in[6 + inPos] & 1073741823) >>> 20 & 255);
         out[26 + outPos] = (byte)(((in[6 + inPos] & 1073741823) >>> 28 | (in[7 + inPos] & 1073741823) << 2) & 255);
         out[27 + outPos] = (byte)((in[7 + inPos] & 1073741823) >>> 6 & 255);
         out[28 + outPos] = (byte)((in[7 + inPos] & 1073741823) >>> 14 & 255);
         out[29 + outPos] = (byte)((in[7 + inPos] & 1073741823) >>> 22 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & 1073741823 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 1073741823) >>> 8 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & 1073741823) >>> 16 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & 1073741823) >>> 24 | (in[1 + inPos] & 1073741823) << 6) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 1073741823) >>> 2 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & 1073741823) >>> 10 & 255);
         out[6 + outPos] = (byte)((in[1 + inPos] & 1073741823) >>> 18 & 255);
         out[7 + outPos] = (byte)(((in[1 + inPos] & 1073741823) >>> 26 | (in[2 + inPos] & 1073741823) << 4) & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & 1073741823) >>> 4 & 255);
         out[9 + outPos] = (byte)((in[2 + inPos] & 1073741823) >>> 12 & 255);
         out[10 + outPos] = (byte)((in[2 + inPos] & 1073741823) >>> 20 & 255);
         out[11 + outPos] = (byte)(((in[2 + inPos] & 1073741823) >>> 28 | (in[3 + inPos] & 1073741823) << 2) & 255);
         out[12 + outPos] = (byte)((in[3 + inPos] & 1073741823) >>> 6 & 255);
         out[13 + outPos] = (byte)((in[3 + inPos] & 1073741823) >>> 14 & 255);
         out[14 + outPos] = (byte)((in[3 + inPos] & 1073741823) >>> 22 & 255);
         out[15 + outPos] = (byte)(in[4 + inPos] & 1073741823 & 255);
         out[16 + outPos] = (byte)((in[4 + inPos] & 1073741823) >>> 8 & 255);
         out[17 + outPos] = (byte)((in[4 + inPos] & 1073741823) >>> 16 & 255);
         out[18 + outPos] = (byte)(((in[4 + inPos] & 1073741823) >>> 24 | (in[5 + inPos] & 1073741823) << 6) & 255);
         out[19 + outPos] = (byte)((in[5 + inPos] & 1073741823) >>> 2 & 255);
         out[20 + outPos] = (byte)((in[5 + inPos] & 1073741823) >>> 10 & 255);
         out[21 + outPos] = (byte)((in[5 + inPos] & 1073741823) >>> 18 & 255);
         out[22 + outPos] = (byte)(((in[5 + inPos] & 1073741823) >>> 26 | (in[6 + inPos] & 1073741823) << 4) & 255);
         out[23 + outPos] = (byte)((in[6 + inPos] & 1073741823) >>> 4 & 255);
         out[24 + outPos] = (byte)((in[6 + inPos] & 1073741823) >>> 12 & 255);
         out[25 + outPos] = (byte)((in[6 + inPos] & 1073741823) >>> 20 & 255);
         out[26 + outPos] = (byte)(((in[6 + inPos] & 1073741823) >>> 28 | (in[7 + inPos] & 1073741823) << 2) & 255);
         out[27 + outPos] = (byte)((in[7 + inPos] & 1073741823) >>> 6 & 255);
         out[28 + outPos] = (byte)((in[7 + inPos] & 1073741823) >>> 14 & 255);
         out[29 + outPos] = (byte)((in[7 + inPos] & 1073741823) >>> 22 & 255);
         out[30 + outPos] = (byte)(in[8 + inPos] & 1073741823 & 255);
         out[31 + outPos] = (byte)((in[8 + inPos] & 1073741823) >>> 8 & 255);
         out[32 + outPos] = (byte)((in[8 + inPos] & 1073741823) >>> 16 & 255);
         out[33 + outPos] = (byte)(((in[8 + inPos] & 1073741823) >>> 24 | (in[9 + inPos] & 1073741823) << 6) & 255);
         out[34 + outPos] = (byte)((in[9 + inPos] & 1073741823) >>> 2 & 255);
         out[35 + outPos] = (byte)((in[9 + inPos] & 1073741823) >>> 10 & 255);
         out[36 + outPos] = (byte)((in[9 + inPos] & 1073741823) >>> 18 & 255);
         out[37 + outPos] = (byte)(((in[9 + inPos] & 1073741823) >>> 26 | (in[10 + inPos] & 1073741823) << 4) & 255);
         out[38 + outPos] = (byte)((in[10 + inPos] & 1073741823) >>> 4 & 255);
         out[39 + outPos] = (byte)((in[10 + inPos] & 1073741823) >>> 12 & 255);
         out[40 + outPos] = (byte)((in[10 + inPos] & 1073741823) >>> 20 & 255);
         out[41 + outPos] = (byte)(((in[10 + inPos] & 1073741823) >>> 28 | (in[11 + inPos] & 1073741823) << 2) & 255);
         out[42 + outPos] = (byte)((in[11 + inPos] & 1073741823) >>> 6 & 255);
         out[43 + outPos] = (byte)((in[11 + inPos] & 1073741823) >>> 14 & 255);
         out[44 + outPos] = (byte)((in[11 + inPos] & 1073741823) >>> 22 & 255);
         out[45 + outPos] = (byte)(in[12 + inPos] & 1073741823 & 255);
         out[46 + outPos] = (byte)((in[12 + inPos] & 1073741823) >>> 8 & 255);
         out[47 + outPos] = (byte)((in[12 + inPos] & 1073741823) >>> 16 & 255);
         out[48 + outPos] = (byte)(((in[12 + inPos] & 1073741823) >>> 24 | (in[13 + inPos] & 1073741823) << 6) & 255);
         out[49 + outPos] = (byte)((in[13 + inPos] & 1073741823) >>> 2 & 255);
         out[50 + outPos] = (byte)((in[13 + inPos] & 1073741823) >>> 10 & 255);
         out[51 + outPos] = (byte)((in[13 + inPos] & 1073741823) >>> 18 & 255);
         out[52 + outPos] = (byte)(((in[13 + inPos] & 1073741823) >>> 26 | (in[14 + inPos] & 1073741823) << 4) & 255);
         out[53 + outPos] = (byte)((in[14 + inPos] & 1073741823) >>> 4 & 255);
         out[54 + outPos] = (byte)((in[14 + inPos] & 1073741823) >>> 12 & 255);
         out[55 + outPos] = (byte)((in[14 + inPos] & 1073741823) >>> 20 & 255);
         out[56 + outPos] = (byte)(((in[14 + inPos] & 1073741823) >>> 28 | (in[15 + inPos] & 1073741823) << 2) & 255);
         out[57 + outPos] = (byte)((in[15 + inPos] & 1073741823) >>> 6 & 255);
         out[58 + outPos] = (byte)((in[15 + inPos] & 1073741823) >>> 14 & 255);
         out[59 + outPos] = (byte)((in[15 + inPos] & 1073741823) >>> 22 & 255);
         out[60 + outPos] = (byte)(in[16 + inPos] & 1073741823 & 255);
         out[61 + outPos] = (byte)((in[16 + inPos] & 1073741823) >>> 8 & 255);
         out[62 + outPos] = (byte)((in[16 + inPos] & 1073741823) >>> 16 & 255);
         out[63 + outPos] = (byte)(((in[16 + inPos] & 1073741823) >>> 24 | (in[17 + inPos] & 1073741823) << 6) & 255);
         out[64 + outPos] = (byte)((in[17 + inPos] & 1073741823) >>> 2 & 255);
         out[65 + outPos] = (byte)((in[17 + inPos] & 1073741823) >>> 10 & 255);
         out[66 + outPos] = (byte)((in[17 + inPos] & 1073741823) >>> 18 & 255);
         out[67 + outPos] = (byte)(((in[17 + inPos] & 1073741823) >>> 26 | (in[18 + inPos] & 1073741823) << 4) & 255);
         out[68 + outPos] = (byte)((in[18 + inPos] & 1073741823) >>> 4 & 255);
         out[69 + outPos] = (byte)((in[18 + inPos] & 1073741823) >>> 12 & 255);
         out[70 + outPos] = (byte)((in[18 + inPos] & 1073741823) >>> 20 & 255);
         out[71 + outPos] = (byte)(((in[18 + inPos] & 1073741823) >>> 28 | (in[19 + inPos] & 1073741823) << 2) & 255);
         out[72 + outPos] = (byte)((in[19 + inPos] & 1073741823) >>> 6 & 255);
         out[73 + outPos] = (byte)((in[19 + inPos] & 1073741823) >>> 14 & 255);
         out[74 + outPos] = (byte)((in[19 + inPos] & 1073741823) >>> 22 & 255);
         out[75 + outPos] = (byte)(in[20 + inPos] & 1073741823 & 255);
         out[76 + outPos] = (byte)((in[20 + inPos] & 1073741823) >>> 8 & 255);
         out[77 + outPos] = (byte)((in[20 + inPos] & 1073741823) >>> 16 & 255);
         out[78 + outPos] = (byte)(((in[20 + inPos] & 1073741823) >>> 24 | (in[21 + inPos] & 1073741823) << 6) & 255);
         out[79 + outPos] = (byte)((in[21 + inPos] & 1073741823) >>> 2 & 255);
         out[80 + outPos] = (byte)((in[21 + inPos] & 1073741823) >>> 10 & 255);
         out[81 + outPos] = (byte)((in[21 + inPos] & 1073741823) >>> 18 & 255);
         out[82 + outPos] = (byte)(((in[21 + inPos] & 1073741823) >>> 26 | (in[22 + inPos] & 1073741823) << 4) & 255);
         out[83 + outPos] = (byte)((in[22 + inPos] & 1073741823) >>> 4 & 255);
         out[84 + outPos] = (byte)((in[22 + inPos] & 1073741823) >>> 12 & 255);
         out[85 + outPos] = (byte)((in[22 + inPos] & 1073741823) >>> 20 & 255);
         out[86 + outPos] = (byte)(((in[22 + inPos] & 1073741823) >>> 28 | (in[23 + inPos] & 1073741823) << 2) & 255);
         out[87 + outPos] = (byte)((in[23 + inPos] & 1073741823) >>> 6 & 255);
         out[88 + outPos] = (byte)((in[23 + inPos] & 1073741823) >>> 14 & 255);
         out[89 + outPos] = (byte)((in[23 + inPos] & 1073741823) >>> 22 & 255);
         out[90 + outPos] = (byte)(in[24 + inPos] & 1073741823 & 255);
         out[91 + outPos] = (byte)((in[24 + inPos] & 1073741823) >>> 8 & 255);
         out[92 + outPos] = (byte)((in[24 + inPos] & 1073741823) >>> 16 & 255);
         out[93 + outPos] = (byte)(((in[24 + inPos] & 1073741823) >>> 24 | (in[25 + inPos] & 1073741823) << 6) & 255);
         out[94 + outPos] = (byte)((in[25 + inPos] & 1073741823) >>> 2 & 255);
         out[95 + outPos] = (byte)((in[25 + inPos] & 1073741823) >>> 10 & 255);
         out[96 + outPos] = (byte)((in[25 + inPos] & 1073741823) >>> 18 & 255);
         out[97 + outPos] = (byte)(((in[25 + inPos] & 1073741823) >>> 26 | (in[26 + inPos] & 1073741823) << 4) & 255);
         out[98 + outPos] = (byte)((in[26 + inPos] & 1073741823) >>> 4 & 255);
         out[99 + outPos] = (byte)((in[26 + inPos] & 1073741823) >>> 12 & 255);
         out[100 + outPos] = (byte)((in[26 + inPos] & 1073741823) >>> 20 & 255);
         out[101 + outPos] = (byte)(((in[26 + inPos] & 1073741823) >>> 28 | (in[27 + inPos] & 1073741823) << 2) & 255);
         out[102 + outPos] = (byte)((in[27 + inPos] & 1073741823) >>> 6 & 255);
         out[103 + outPos] = (byte)((in[27 + inPos] & 1073741823) >>> 14 & 255);
         out[104 + outPos] = (byte)((in[27 + inPos] & 1073741823) >>> 22 & 255);
         out[105 + outPos] = (byte)(in[28 + inPos] & 1073741823 & 255);
         out[106 + outPos] = (byte)((in[28 + inPos] & 1073741823) >>> 8 & 255);
         out[107 + outPos] = (byte)((in[28 + inPos] & 1073741823) >>> 16 & 255);
         out[108 + outPos] = (byte)(((in[28 + inPos] & 1073741823) >>> 24 | (in[29 + inPos] & 1073741823) << 6) & 255);
         out[109 + outPos] = (byte)((in[29 + inPos] & 1073741823) >>> 2 & 255);
         out[110 + outPos] = (byte)((in[29 + inPos] & 1073741823) >>> 10 & 255);
         out[111 + outPos] = (byte)((in[29 + inPos] & 1073741823) >>> 18 & 255);
         out[112 + outPos] = (byte)(((in[29 + inPos] & 1073741823) >>> 26 | (in[30 + inPos] & 1073741823) << 4) & 255);
         out[113 + outPos] = (byte)((in[30 + inPos] & 1073741823) >>> 4 & 255);
         out[114 + outPos] = (byte)((in[30 + inPos] & 1073741823) >>> 12 & 255);
         out[115 + outPos] = (byte)((in[30 + inPos] & 1073741823) >>> 20 & 255);
         out[116 + outPos] = (byte)(((in[30 + inPos] & 1073741823) >>> 28 | (in[31 + inPos] & 1073741823) << 2) & 255);
         out[117 + outPos] = (byte)((in[31 + inPos] & 1073741823) >>> 6 & 255);
         out[118 + outPos] = (byte)((in[31 + inPos] & 1073741823) >>> 14 & 255);
         out[119 + outPos] = (byte)((in[31 + inPos] & 1073741823) >>> 22 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 16777215 | in[3 + inPos] << 24 & 1073741823;
         out[1 + outPos] = in[3 + inPos] >> 6 & 3 | in[4 + inPos] << 2 & 1023 | in[5 + inPos] << 10 & 262143 | in[6 + inPos] << 18 & 67108863 | in[7 + inPos] << 26 & 1073741823;
         out[2 + outPos] = in[7 + inPos] >> 4 & 15 | in[8 + inPos] << 4 & 4095 | in[9 + inPos] << 12 & 1048575 | in[10 + inPos] << 20 & 268435455 | in[11 + inPos] << 28 & 1073741823;
         out[3 + outPos] = in[11 + inPos] >> 2 & 63 | in[12 + inPos] << 6 & 16383 | in[13 + inPos] << 14 & 4194303 | in[14 + inPos] << 22 & 1073741823;
         out[4 + outPos] = in[15 + inPos] & 255 | in[16 + inPos] << 8 & '\uffff' | in[17 + inPos] << 16 & 16777215 | in[18 + inPos] << 24 & 1073741823;
         out[5 + outPos] = in[18 + inPos] >> 6 & 3 | in[19 + inPos] << 2 & 1023 | in[20 + inPos] << 10 & 262143 | in[21 + inPos] << 18 & 67108863 | in[22 + inPos] << 26 & 1073741823;
         out[6 + outPos] = in[22 + inPos] >> 4 & 15 | in[23 + inPos] << 4 & 4095 | in[24 + inPos] << 12 & 1048575 | in[25 + inPos] << 20 & 268435455 | in[26 + inPos] << 28 & 1073741823;
         out[7 + outPos] = in[26 + inPos] >> 2 & 63 | in[27 + inPos] << 6 & 16383 | in[28 + inPos] << 14 & 4194303 | in[29 + inPos] << 22 & 1073741823;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 16777215 | in.get(3 + inPos) << 24 & 1073741823;
         out[1 + outPos] = in.get(3 + inPos) >> 6 & 3 | in.get(4 + inPos) << 2 & 1023 | in.get(5 + inPos) << 10 & 262143 | in.get(6 + inPos) << 18 & 67108863 | in.get(7 + inPos) << 26 & 1073741823;
         out[2 + outPos] = in.get(7 + inPos) >> 4 & 15 | in.get(8 + inPos) << 4 & 4095 | in.get(9 + inPos) << 12 & 1048575 | in.get(10 + inPos) << 20 & 268435455 | in.get(11 + inPos) << 28 & 1073741823;
         out[3 + outPos] = in.get(11 + inPos) >> 2 & 63 | in.get(12 + inPos) << 6 & 16383 | in.get(13 + inPos) << 14 & 4194303 | in.get(14 + inPos) << 22 & 1073741823;
         out[4 + outPos] = in.get(15 + inPos) & 255 | in.get(16 + inPos) << 8 & '\uffff' | in.get(17 + inPos) << 16 & 16777215 | in.get(18 + inPos) << 24 & 1073741823;
         out[5 + outPos] = in.get(18 + inPos) >> 6 & 3 | in.get(19 + inPos) << 2 & 1023 | in.get(20 + inPos) << 10 & 262143 | in.get(21 + inPos) << 18 & 67108863 | in.get(22 + inPos) << 26 & 1073741823;
         out[6 + outPos] = in.get(22 + inPos) >> 4 & 15 | in.get(23 + inPos) << 4 & 4095 | in.get(24 + inPos) << 12 & 1048575 | in.get(25 + inPos) << 20 & 268435455 | in.get(26 + inPos) << 28 & 1073741823;
         out[7 + outPos] = in.get(26 + inPos) >> 2 & 63 | in.get(27 + inPos) << 6 & 16383 | in.get(28 + inPos) << 14 & 4194303 | in.get(29 + inPos) << 22 & 1073741823;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 16777215 | in[3 + inPos] << 24 & 1073741823;
         out[1 + outPos] = in[3 + inPos] >> 6 & 3 | in[4 + inPos] << 2 & 1023 | in[5 + inPos] << 10 & 262143 | in[6 + inPos] << 18 & 67108863 | in[7 + inPos] << 26 & 1073741823;
         out[2 + outPos] = in[7 + inPos] >> 4 & 15 | in[8 + inPos] << 4 & 4095 | in[9 + inPos] << 12 & 1048575 | in[10 + inPos] << 20 & 268435455 | in[11 + inPos] << 28 & 1073741823;
         out[3 + outPos] = in[11 + inPos] >> 2 & 63 | in[12 + inPos] << 6 & 16383 | in[13 + inPos] << 14 & 4194303 | in[14 + inPos] << 22 & 1073741823;
         out[4 + outPos] = in[15 + inPos] & 255 | in[16 + inPos] << 8 & '\uffff' | in[17 + inPos] << 16 & 16777215 | in[18 + inPos] << 24 & 1073741823;
         out[5 + outPos] = in[18 + inPos] >> 6 & 3 | in[19 + inPos] << 2 & 1023 | in[20 + inPos] << 10 & 262143 | in[21 + inPos] << 18 & 67108863 | in[22 + inPos] << 26 & 1073741823;
         out[6 + outPos] = in[22 + inPos] >> 4 & 15 | in[23 + inPos] << 4 & 4095 | in[24 + inPos] << 12 & 1048575 | in[25 + inPos] << 20 & 268435455 | in[26 + inPos] << 28 & 1073741823;
         out[7 + outPos] = in[26 + inPos] >> 2 & 63 | in[27 + inPos] << 6 & 16383 | in[28 + inPos] << 14 & 4194303 | in[29 + inPos] << 22 & 1073741823;
         out[8 + outPos] = in[30 + inPos] & 255 | in[31 + inPos] << 8 & '\uffff' | in[32 + inPos] << 16 & 16777215 | in[33 + inPos] << 24 & 1073741823;
         out[9 + outPos] = in[33 + inPos] >> 6 & 3 | in[34 + inPos] << 2 & 1023 | in[35 + inPos] << 10 & 262143 | in[36 + inPos] << 18 & 67108863 | in[37 + inPos] << 26 & 1073741823;
         out[10 + outPos] = in[37 + inPos] >> 4 & 15 | in[38 + inPos] << 4 & 4095 | in[39 + inPos] << 12 & 1048575 | in[40 + inPos] << 20 & 268435455 | in[41 + inPos] << 28 & 1073741823;
         out[11 + outPos] = in[41 + inPos] >> 2 & 63 | in[42 + inPos] << 6 & 16383 | in[43 + inPos] << 14 & 4194303 | in[44 + inPos] << 22 & 1073741823;
         out[12 + outPos] = in[45 + inPos] & 255 | in[46 + inPos] << 8 & '\uffff' | in[47 + inPos] << 16 & 16777215 | in[48 + inPos] << 24 & 1073741823;
         out[13 + outPos] = in[48 + inPos] >> 6 & 3 | in[49 + inPos] << 2 & 1023 | in[50 + inPos] << 10 & 262143 | in[51 + inPos] << 18 & 67108863 | in[52 + inPos] << 26 & 1073741823;
         out[14 + outPos] = in[52 + inPos] >> 4 & 15 | in[53 + inPos] << 4 & 4095 | in[54 + inPos] << 12 & 1048575 | in[55 + inPos] << 20 & 268435455 | in[56 + inPos] << 28 & 1073741823;
         out[15 + outPos] = in[56 + inPos] >> 2 & 63 | in[57 + inPos] << 6 & 16383 | in[58 + inPos] << 14 & 4194303 | in[59 + inPos] << 22 & 1073741823;
         out[16 + outPos] = in[60 + inPos] & 255 | in[61 + inPos] << 8 & '\uffff' | in[62 + inPos] << 16 & 16777215 | in[63 + inPos] << 24 & 1073741823;
         out[17 + outPos] = in[63 + inPos] >> 6 & 3 | in[64 + inPos] << 2 & 1023 | in[65 + inPos] << 10 & 262143 | in[66 + inPos] << 18 & 67108863 | in[67 + inPos] << 26 & 1073741823;
         out[18 + outPos] = in[67 + inPos] >> 4 & 15 | in[68 + inPos] << 4 & 4095 | in[69 + inPos] << 12 & 1048575 | in[70 + inPos] << 20 & 268435455 | in[71 + inPos] << 28 & 1073741823;
         out[19 + outPos] = in[71 + inPos] >> 2 & 63 | in[72 + inPos] << 6 & 16383 | in[73 + inPos] << 14 & 4194303 | in[74 + inPos] << 22 & 1073741823;
         out[20 + outPos] = in[75 + inPos] & 255 | in[76 + inPos] << 8 & '\uffff' | in[77 + inPos] << 16 & 16777215 | in[78 + inPos] << 24 & 1073741823;
         out[21 + outPos] = in[78 + inPos] >> 6 & 3 | in[79 + inPos] << 2 & 1023 | in[80 + inPos] << 10 & 262143 | in[81 + inPos] << 18 & 67108863 | in[82 + inPos] << 26 & 1073741823;
         out[22 + outPos] = in[82 + inPos] >> 4 & 15 | in[83 + inPos] << 4 & 4095 | in[84 + inPos] << 12 & 1048575 | in[85 + inPos] << 20 & 268435455 | in[86 + inPos] << 28 & 1073741823;
         out[23 + outPos] = in[86 + inPos] >> 2 & 63 | in[87 + inPos] << 6 & 16383 | in[88 + inPos] << 14 & 4194303 | in[89 + inPos] << 22 & 1073741823;
         out[24 + outPos] = in[90 + inPos] & 255 | in[91 + inPos] << 8 & '\uffff' | in[92 + inPos] << 16 & 16777215 | in[93 + inPos] << 24 & 1073741823;
         out[25 + outPos] = in[93 + inPos] >> 6 & 3 | in[94 + inPos] << 2 & 1023 | in[95 + inPos] << 10 & 262143 | in[96 + inPos] << 18 & 67108863 | in[97 + inPos] << 26 & 1073741823;
         out[26 + outPos] = in[97 + inPos] >> 4 & 15 | in[98 + inPos] << 4 & 4095 | in[99 + inPos] << 12 & 1048575 | in[100 + inPos] << 20 & 268435455 | in[101 + inPos] << 28 & 1073741823;
         out[27 + outPos] = in[101 + inPos] >> 2 & 63 | in[102 + inPos] << 6 & 16383 | in[103 + inPos] << 14 & 4194303 | in[104 + inPos] << 22 & 1073741823;
         out[28 + outPos] = in[105 + inPos] & 255 | in[106 + inPos] << 8 & '\uffff' | in[107 + inPos] << 16 & 16777215 | in[108 + inPos] << 24 & 1073741823;
         out[29 + outPos] = in[108 + inPos] >> 6 & 3 | in[109 + inPos] << 2 & 1023 | in[110 + inPos] << 10 & 262143 | in[111 + inPos] << 18 & 67108863 | in[112 + inPos] << 26 & 1073741823;
         out[30 + outPos] = in[112 + inPos] >> 4 & 15 | in[113 + inPos] << 4 & 4095 | in[114 + inPos] << 12 & 1048575 | in[115 + inPos] << 20 & 268435455 | in[116 + inPos] << 28 & 1073741823;
         out[31 + outPos] = in[116 + inPos] >> 2 & 63 | in[117 + inPos] << 6 & 16383 | in[118 + inPos] << 14 & 4194303 | in[119 + inPos] << 22 & 1073741823;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 16777215 | in.get(3 + inPos) << 24 & 1073741823;
         out[1 + outPos] = in.get(3 + inPos) >> 6 & 3 | in.get(4 + inPos) << 2 & 1023 | in.get(5 + inPos) << 10 & 262143 | in.get(6 + inPos) << 18 & 67108863 | in.get(7 + inPos) << 26 & 1073741823;
         out[2 + outPos] = in.get(7 + inPos) >> 4 & 15 | in.get(8 + inPos) << 4 & 4095 | in.get(9 + inPos) << 12 & 1048575 | in.get(10 + inPos) << 20 & 268435455 | in.get(11 + inPos) << 28 & 1073741823;
         out[3 + outPos] = in.get(11 + inPos) >> 2 & 63 | in.get(12 + inPos) << 6 & 16383 | in.get(13 + inPos) << 14 & 4194303 | in.get(14 + inPos) << 22 & 1073741823;
         out[4 + outPos] = in.get(15 + inPos) & 255 | in.get(16 + inPos) << 8 & '\uffff' | in.get(17 + inPos) << 16 & 16777215 | in.get(18 + inPos) << 24 & 1073741823;
         out[5 + outPos] = in.get(18 + inPos) >> 6 & 3 | in.get(19 + inPos) << 2 & 1023 | in.get(20 + inPos) << 10 & 262143 | in.get(21 + inPos) << 18 & 67108863 | in.get(22 + inPos) << 26 & 1073741823;
         out[6 + outPos] = in.get(22 + inPos) >> 4 & 15 | in.get(23 + inPos) << 4 & 4095 | in.get(24 + inPos) << 12 & 1048575 | in.get(25 + inPos) << 20 & 268435455 | in.get(26 + inPos) << 28 & 1073741823;
         out[7 + outPos] = in.get(26 + inPos) >> 2 & 63 | in.get(27 + inPos) << 6 & 16383 | in.get(28 + inPos) << 14 & 4194303 | in.get(29 + inPos) << 22 & 1073741823;
         out[8 + outPos] = in.get(30 + inPos) & 255 | in.get(31 + inPos) << 8 & '\uffff' | in.get(32 + inPos) << 16 & 16777215 | in.get(33 + inPos) << 24 & 1073741823;
         out[9 + outPos] = in.get(33 + inPos) >> 6 & 3 | in.get(34 + inPos) << 2 & 1023 | in.get(35 + inPos) << 10 & 262143 | in.get(36 + inPos) << 18 & 67108863 | in.get(37 + inPos) << 26 & 1073741823;
         out[10 + outPos] = in.get(37 + inPos) >> 4 & 15 | in.get(38 + inPos) << 4 & 4095 | in.get(39 + inPos) << 12 & 1048575 | in.get(40 + inPos) << 20 & 268435455 | in.get(41 + inPos) << 28 & 1073741823;
         out[11 + outPos] = in.get(41 + inPos) >> 2 & 63 | in.get(42 + inPos) << 6 & 16383 | in.get(43 + inPos) << 14 & 4194303 | in.get(44 + inPos) << 22 & 1073741823;
         out[12 + outPos] = in.get(45 + inPos) & 255 | in.get(46 + inPos) << 8 & '\uffff' | in.get(47 + inPos) << 16 & 16777215 | in.get(48 + inPos) << 24 & 1073741823;
         out[13 + outPos] = in.get(48 + inPos) >> 6 & 3 | in.get(49 + inPos) << 2 & 1023 | in.get(50 + inPos) << 10 & 262143 | in.get(51 + inPos) << 18 & 67108863 | in.get(52 + inPos) << 26 & 1073741823;
         out[14 + outPos] = in.get(52 + inPos) >> 4 & 15 | in.get(53 + inPos) << 4 & 4095 | in.get(54 + inPos) << 12 & 1048575 | in.get(55 + inPos) << 20 & 268435455 | in.get(56 + inPos) << 28 & 1073741823;
         out[15 + outPos] = in.get(56 + inPos) >> 2 & 63 | in.get(57 + inPos) << 6 & 16383 | in.get(58 + inPos) << 14 & 4194303 | in.get(59 + inPos) << 22 & 1073741823;
         out[16 + outPos] = in.get(60 + inPos) & 255 | in.get(61 + inPos) << 8 & '\uffff' | in.get(62 + inPos) << 16 & 16777215 | in.get(63 + inPos) << 24 & 1073741823;
         out[17 + outPos] = in.get(63 + inPos) >> 6 & 3 | in.get(64 + inPos) << 2 & 1023 | in.get(65 + inPos) << 10 & 262143 | in.get(66 + inPos) << 18 & 67108863 | in.get(67 + inPos) << 26 & 1073741823;
         out[18 + outPos] = in.get(67 + inPos) >> 4 & 15 | in.get(68 + inPos) << 4 & 4095 | in.get(69 + inPos) << 12 & 1048575 | in.get(70 + inPos) << 20 & 268435455 | in.get(71 + inPos) << 28 & 1073741823;
         out[19 + outPos] = in.get(71 + inPos) >> 2 & 63 | in.get(72 + inPos) << 6 & 16383 | in.get(73 + inPos) << 14 & 4194303 | in.get(74 + inPos) << 22 & 1073741823;
         out[20 + outPos] = in.get(75 + inPos) & 255 | in.get(76 + inPos) << 8 & '\uffff' | in.get(77 + inPos) << 16 & 16777215 | in.get(78 + inPos) << 24 & 1073741823;
         out[21 + outPos] = in.get(78 + inPos) >> 6 & 3 | in.get(79 + inPos) << 2 & 1023 | in.get(80 + inPos) << 10 & 262143 | in.get(81 + inPos) << 18 & 67108863 | in.get(82 + inPos) << 26 & 1073741823;
         out[22 + outPos] = in.get(82 + inPos) >> 4 & 15 | in.get(83 + inPos) << 4 & 4095 | in.get(84 + inPos) << 12 & 1048575 | in.get(85 + inPos) << 20 & 268435455 | in.get(86 + inPos) << 28 & 1073741823;
         out[23 + outPos] = in.get(86 + inPos) >> 2 & 63 | in.get(87 + inPos) << 6 & 16383 | in.get(88 + inPos) << 14 & 4194303 | in.get(89 + inPos) << 22 & 1073741823;
         out[24 + outPos] = in.get(90 + inPos) & 255 | in.get(91 + inPos) << 8 & '\uffff' | in.get(92 + inPos) << 16 & 16777215 | in.get(93 + inPos) << 24 & 1073741823;
         out[25 + outPos] = in.get(93 + inPos) >> 6 & 3 | in.get(94 + inPos) << 2 & 1023 | in.get(95 + inPos) << 10 & 262143 | in.get(96 + inPos) << 18 & 67108863 | in.get(97 + inPos) << 26 & 1073741823;
         out[26 + outPos] = in.get(97 + inPos) >> 4 & 15 | in.get(98 + inPos) << 4 & 4095 | in.get(99 + inPos) << 12 & 1048575 | in.get(100 + inPos) << 20 & 268435455 | in.get(101 + inPos) << 28 & 1073741823;
         out[27 + outPos] = in.get(101 + inPos) >> 2 & 63 | in.get(102 + inPos) << 6 & 16383 | in.get(103 + inPos) << 14 & 4194303 | in.get(104 + inPos) << 22 & 1073741823;
         out[28 + outPos] = in.get(105 + inPos) & 255 | in.get(106 + inPos) << 8 & '\uffff' | in.get(107 + inPos) << 16 & 16777215 | in.get(108 + inPos) << 24 & 1073741823;
         out[29 + outPos] = in.get(108 + inPos) >> 6 & 3 | in.get(109 + inPos) << 2 & 1023 | in.get(110 + inPos) << 10 & 262143 | in.get(111 + inPos) << 18 & 67108863 | in.get(112 + inPos) << 26 & 1073741823;
         out[30 + outPos] = in.get(112 + inPos) >> 4 & 15 | in.get(113 + inPos) << 4 & 4095 | in.get(114 + inPos) << 12 & 1048575 | in.get(115 + inPos) << 20 & 268435455 | in.get(116 + inPos) << 28 & 1073741823;
         out[31 + outPos] = in.get(116 + inPos) >> 2 & 63 | in.get(117 + inPos) << 6 & 16383 | in.get(118 + inPos) << 14 & 4194303 | in.get(119 + inPos) << 22 & 1073741823;
      }
   }

   private static final class Packer31 extends BytePacker {
      private Packer31() {
         super(31);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & Integer.MAX_VALUE & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & Integer.MAX_VALUE) >>> 8 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & Integer.MAX_VALUE) >>> 16 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & Integer.MAX_VALUE) >>> 24 | (in[1 + inPos] & Integer.MAX_VALUE) << 7) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & Integer.MAX_VALUE) >>> 1 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & Integer.MAX_VALUE) >>> 9 & 255);
         out[6 + outPos] = (byte)((in[1 + inPos] & Integer.MAX_VALUE) >>> 17 & 255);
         out[7 + outPos] = (byte)(((in[1 + inPos] & Integer.MAX_VALUE) >>> 25 | (in[2 + inPos] & Integer.MAX_VALUE) << 6) & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & Integer.MAX_VALUE) >>> 2 & 255);
         out[9 + outPos] = (byte)((in[2 + inPos] & Integer.MAX_VALUE) >>> 10 & 255);
         out[10 + outPos] = (byte)((in[2 + inPos] & Integer.MAX_VALUE) >>> 18 & 255);
         out[11 + outPos] = (byte)(((in[2 + inPos] & Integer.MAX_VALUE) >>> 26 | (in[3 + inPos] & Integer.MAX_VALUE) << 5) & 255);
         out[12 + outPos] = (byte)((in[3 + inPos] & Integer.MAX_VALUE) >>> 3 & 255);
         out[13 + outPos] = (byte)((in[3 + inPos] & Integer.MAX_VALUE) >>> 11 & 255);
         out[14 + outPos] = (byte)((in[3 + inPos] & Integer.MAX_VALUE) >>> 19 & 255);
         out[15 + outPos] = (byte)(((in[3 + inPos] & Integer.MAX_VALUE) >>> 27 | (in[4 + inPos] & Integer.MAX_VALUE) << 4) & 255);
         out[16 + outPos] = (byte)((in[4 + inPos] & Integer.MAX_VALUE) >>> 4 & 255);
         out[17 + outPos] = (byte)((in[4 + inPos] & Integer.MAX_VALUE) >>> 12 & 255);
         out[18 + outPos] = (byte)((in[4 + inPos] & Integer.MAX_VALUE) >>> 20 & 255);
         out[19 + outPos] = (byte)(((in[4 + inPos] & Integer.MAX_VALUE) >>> 28 | (in[5 + inPos] & Integer.MAX_VALUE) << 3) & 255);
         out[20 + outPos] = (byte)((in[5 + inPos] & Integer.MAX_VALUE) >>> 5 & 255);
         out[21 + outPos] = (byte)((in[5 + inPos] & Integer.MAX_VALUE) >>> 13 & 255);
         out[22 + outPos] = (byte)((in[5 + inPos] & Integer.MAX_VALUE) >>> 21 & 255);
         out[23 + outPos] = (byte)(((in[5 + inPos] & Integer.MAX_VALUE) >>> 29 | (in[6 + inPos] & Integer.MAX_VALUE) << 2) & 255);
         out[24 + outPos] = (byte)((in[6 + inPos] & Integer.MAX_VALUE) >>> 6 & 255);
         out[25 + outPos] = (byte)((in[6 + inPos] & Integer.MAX_VALUE) >>> 14 & 255);
         out[26 + outPos] = (byte)((in[6 + inPos] & Integer.MAX_VALUE) >>> 22 & 255);
         out[27 + outPos] = (byte)(((in[6 + inPos] & Integer.MAX_VALUE) >>> 30 | (in[7 + inPos] & Integer.MAX_VALUE) << 1) & 255);
         out[28 + outPos] = (byte)((in[7 + inPos] & Integer.MAX_VALUE) >>> 7 & 255);
         out[29 + outPos] = (byte)((in[7 + inPos] & Integer.MAX_VALUE) >>> 15 & 255);
         out[30 + outPos] = (byte)((in[7 + inPos] & Integer.MAX_VALUE) >>> 23 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & Integer.MAX_VALUE & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & Integer.MAX_VALUE) >>> 8 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & Integer.MAX_VALUE) >>> 16 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & Integer.MAX_VALUE) >>> 24 | (in[1 + inPos] & Integer.MAX_VALUE) << 7) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & Integer.MAX_VALUE) >>> 1 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & Integer.MAX_VALUE) >>> 9 & 255);
         out[6 + outPos] = (byte)((in[1 + inPos] & Integer.MAX_VALUE) >>> 17 & 255);
         out[7 + outPos] = (byte)(((in[1 + inPos] & Integer.MAX_VALUE) >>> 25 | (in[2 + inPos] & Integer.MAX_VALUE) << 6) & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & Integer.MAX_VALUE) >>> 2 & 255);
         out[9 + outPos] = (byte)((in[2 + inPos] & Integer.MAX_VALUE) >>> 10 & 255);
         out[10 + outPos] = (byte)((in[2 + inPos] & Integer.MAX_VALUE) >>> 18 & 255);
         out[11 + outPos] = (byte)(((in[2 + inPos] & Integer.MAX_VALUE) >>> 26 | (in[3 + inPos] & Integer.MAX_VALUE) << 5) & 255);
         out[12 + outPos] = (byte)((in[3 + inPos] & Integer.MAX_VALUE) >>> 3 & 255);
         out[13 + outPos] = (byte)((in[3 + inPos] & Integer.MAX_VALUE) >>> 11 & 255);
         out[14 + outPos] = (byte)((in[3 + inPos] & Integer.MAX_VALUE) >>> 19 & 255);
         out[15 + outPos] = (byte)(((in[3 + inPos] & Integer.MAX_VALUE) >>> 27 | (in[4 + inPos] & Integer.MAX_VALUE) << 4) & 255);
         out[16 + outPos] = (byte)((in[4 + inPos] & Integer.MAX_VALUE) >>> 4 & 255);
         out[17 + outPos] = (byte)((in[4 + inPos] & Integer.MAX_VALUE) >>> 12 & 255);
         out[18 + outPos] = (byte)((in[4 + inPos] & Integer.MAX_VALUE) >>> 20 & 255);
         out[19 + outPos] = (byte)(((in[4 + inPos] & Integer.MAX_VALUE) >>> 28 | (in[5 + inPos] & Integer.MAX_VALUE) << 3) & 255);
         out[20 + outPos] = (byte)((in[5 + inPos] & Integer.MAX_VALUE) >>> 5 & 255);
         out[21 + outPos] = (byte)((in[5 + inPos] & Integer.MAX_VALUE) >>> 13 & 255);
         out[22 + outPos] = (byte)((in[5 + inPos] & Integer.MAX_VALUE) >>> 21 & 255);
         out[23 + outPos] = (byte)(((in[5 + inPos] & Integer.MAX_VALUE) >>> 29 | (in[6 + inPos] & Integer.MAX_VALUE) << 2) & 255);
         out[24 + outPos] = (byte)((in[6 + inPos] & Integer.MAX_VALUE) >>> 6 & 255);
         out[25 + outPos] = (byte)((in[6 + inPos] & Integer.MAX_VALUE) >>> 14 & 255);
         out[26 + outPos] = (byte)((in[6 + inPos] & Integer.MAX_VALUE) >>> 22 & 255);
         out[27 + outPos] = (byte)(((in[6 + inPos] & Integer.MAX_VALUE) >>> 30 | (in[7 + inPos] & Integer.MAX_VALUE) << 1) & 255);
         out[28 + outPos] = (byte)((in[7 + inPos] & Integer.MAX_VALUE) >>> 7 & 255);
         out[29 + outPos] = (byte)((in[7 + inPos] & Integer.MAX_VALUE) >>> 15 & 255);
         out[30 + outPos] = (byte)((in[7 + inPos] & Integer.MAX_VALUE) >>> 23 & 255);
         out[31 + outPos] = (byte)(in[8 + inPos] & Integer.MAX_VALUE & 255);
         out[32 + outPos] = (byte)((in[8 + inPos] & Integer.MAX_VALUE) >>> 8 & 255);
         out[33 + outPos] = (byte)((in[8 + inPos] & Integer.MAX_VALUE) >>> 16 & 255);
         out[34 + outPos] = (byte)(((in[8 + inPos] & Integer.MAX_VALUE) >>> 24 | (in[9 + inPos] & Integer.MAX_VALUE) << 7) & 255);
         out[35 + outPos] = (byte)((in[9 + inPos] & Integer.MAX_VALUE) >>> 1 & 255);
         out[36 + outPos] = (byte)((in[9 + inPos] & Integer.MAX_VALUE) >>> 9 & 255);
         out[37 + outPos] = (byte)((in[9 + inPos] & Integer.MAX_VALUE) >>> 17 & 255);
         out[38 + outPos] = (byte)(((in[9 + inPos] & Integer.MAX_VALUE) >>> 25 | (in[10 + inPos] & Integer.MAX_VALUE) << 6) & 255);
         out[39 + outPos] = (byte)((in[10 + inPos] & Integer.MAX_VALUE) >>> 2 & 255);
         out[40 + outPos] = (byte)((in[10 + inPos] & Integer.MAX_VALUE) >>> 10 & 255);
         out[41 + outPos] = (byte)((in[10 + inPos] & Integer.MAX_VALUE) >>> 18 & 255);
         out[42 + outPos] = (byte)(((in[10 + inPos] & Integer.MAX_VALUE) >>> 26 | (in[11 + inPos] & Integer.MAX_VALUE) << 5) & 255);
         out[43 + outPos] = (byte)((in[11 + inPos] & Integer.MAX_VALUE) >>> 3 & 255);
         out[44 + outPos] = (byte)((in[11 + inPos] & Integer.MAX_VALUE) >>> 11 & 255);
         out[45 + outPos] = (byte)((in[11 + inPos] & Integer.MAX_VALUE) >>> 19 & 255);
         out[46 + outPos] = (byte)(((in[11 + inPos] & Integer.MAX_VALUE) >>> 27 | (in[12 + inPos] & Integer.MAX_VALUE) << 4) & 255);
         out[47 + outPos] = (byte)((in[12 + inPos] & Integer.MAX_VALUE) >>> 4 & 255);
         out[48 + outPos] = (byte)((in[12 + inPos] & Integer.MAX_VALUE) >>> 12 & 255);
         out[49 + outPos] = (byte)((in[12 + inPos] & Integer.MAX_VALUE) >>> 20 & 255);
         out[50 + outPos] = (byte)(((in[12 + inPos] & Integer.MAX_VALUE) >>> 28 | (in[13 + inPos] & Integer.MAX_VALUE) << 3) & 255);
         out[51 + outPos] = (byte)((in[13 + inPos] & Integer.MAX_VALUE) >>> 5 & 255);
         out[52 + outPos] = (byte)((in[13 + inPos] & Integer.MAX_VALUE) >>> 13 & 255);
         out[53 + outPos] = (byte)((in[13 + inPos] & Integer.MAX_VALUE) >>> 21 & 255);
         out[54 + outPos] = (byte)(((in[13 + inPos] & Integer.MAX_VALUE) >>> 29 | (in[14 + inPos] & Integer.MAX_VALUE) << 2) & 255);
         out[55 + outPos] = (byte)((in[14 + inPos] & Integer.MAX_VALUE) >>> 6 & 255);
         out[56 + outPos] = (byte)((in[14 + inPos] & Integer.MAX_VALUE) >>> 14 & 255);
         out[57 + outPos] = (byte)((in[14 + inPos] & Integer.MAX_VALUE) >>> 22 & 255);
         out[58 + outPos] = (byte)(((in[14 + inPos] & Integer.MAX_VALUE) >>> 30 | (in[15 + inPos] & Integer.MAX_VALUE) << 1) & 255);
         out[59 + outPos] = (byte)((in[15 + inPos] & Integer.MAX_VALUE) >>> 7 & 255);
         out[60 + outPos] = (byte)((in[15 + inPos] & Integer.MAX_VALUE) >>> 15 & 255);
         out[61 + outPos] = (byte)((in[15 + inPos] & Integer.MAX_VALUE) >>> 23 & 255);
         out[62 + outPos] = (byte)(in[16 + inPos] & Integer.MAX_VALUE & 255);
         out[63 + outPos] = (byte)((in[16 + inPos] & Integer.MAX_VALUE) >>> 8 & 255);
         out[64 + outPos] = (byte)((in[16 + inPos] & Integer.MAX_VALUE) >>> 16 & 255);
         out[65 + outPos] = (byte)(((in[16 + inPos] & Integer.MAX_VALUE) >>> 24 | (in[17 + inPos] & Integer.MAX_VALUE) << 7) & 255);
         out[66 + outPos] = (byte)((in[17 + inPos] & Integer.MAX_VALUE) >>> 1 & 255);
         out[67 + outPos] = (byte)((in[17 + inPos] & Integer.MAX_VALUE) >>> 9 & 255);
         out[68 + outPos] = (byte)((in[17 + inPos] & Integer.MAX_VALUE) >>> 17 & 255);
         out[69 + outPos] = (byte)(((in[17 + inPos] & Integer.MAX_VALUE) >>> 25 | (in[18 + inPos] & Integer.MAX_VALUE) << 6) & 255);
         out[70 + outPos] = (byte)((in[18 + inPos] & Integer.MAX_VALUE) >>> 2 & 255);
         out[71 + outPos] = (byte)((in[18 + inPos] & Integer.MAX_VALUE) >>> 10 & 255);
         out[72 + outPos] = (byte)((in[18 + inPos] & Integer.MAX_VALUE) >>> 18 & 255);
         out[73 + outPos] = (byte)(((in[18 + inPos] & Integer.MAX_VALUE) >>> 26 | (in[19 + inPos] & Integer.MAX_VALUE) << 5) & 255);
         out[74 + outPos] = (byte)((in[19 + inPos] & Integer.MAX_VALUE) >>> 3 & 255);
         out[75 + outPos] = (byte)((in[19 + inPos] & Integer.MAX_VALUE) >>> 11 & 255);
         out[76 + outPos] = (byte)((in[19 + inPos] & Integer.MAX_VALUE) >>> 19 & 255);
         out[77 + outPos] = (byte)(((in[19 + inPos] & Integer.MAX_VALUE) >>> 27 | (in[20 + inPos] & Integer.MAX_VALUE) << 4) & 255);
         out[78 + outPos] = (byte)((in[20 + inPos] & Integer.MAX_VALUE) >>> 4 & 255);
         out[79 + outPos] = (byte)((in[20 + inPos] & Integer.MAX_VALUE) >>> 12 & 255);
         out[80 + outPos] = (byte)((in[20 + inPos] & Integer.MAX_VALUE) >>> 20 & 255);
         out[81 + outPos] = (byte)(((in[20 + inPos] & Integer.MAX_VALUE) >>> 28 | (in[21 + inPos] & Integer.MAX_VALUE) << 3) & 255);
         out[82 + outPos] = (byte)((in[21 + inPos] & Integer.MAX_VALUE) >>> 5 & 255);
         out[83 + outPos] = (byte)((in[21 + inPos] & Integer.MAX_VALUE) >>> 13 & 255);
         out[84 + outPos] = (byte)((in[21 + inPos] & Integer.MAX_VALUE) >>> 21 & 255);
         out[85 + outPos] = (byte)(((in[21 + inPos] & Integer.MAX_VALUE) >>> 29 | (in[22 + inPos] & Integer.MAX_VALUE) << 2) & 255);
         out[86 + outPos] = (byte)((in[22 + inPos] & Integer.MAX_VALUE) >>> 6 & 255);
         out[87 + outPos] = (byte)((in[22 + inPos] & Integer.MAX_VALUE) >>> 14 & 255);
         out[88 + outPos] = (byte)((in[22 + inPos] & Integer.MAX_VALUE) >>> 22 & 255);
         out[89 + outPos] = (byte)(((in[22 + inPos] & Integer.MAX_VALUE) >>> 30 | (in[23 + inPos] & Integer.MAX_VALUE) << 1) & 255);
         out[90 + outPos] = (byte)((in[23 + inPos] & Integer.MAX_VALUE) >>> 7 & 255);
         out[91 + outPos] = (byte)((in[23 + inPos] & Integer.MAX_VALUE) >>> 15 & 255);
         out[92 + outPos] = (byte)((in[23 + inPos] & Integer.MAX_VALUE) >>> 23 & 255);
         out[93 + outPos] = (byte)(in[24 + inPos] & Integer.MAX_VALUE & 255);
         out[94 + outPos] = (byte)((in[24 + inPos] & Integer.MAX_VALUE) >>> 8 & 255);
         out[95 + outPos] = (byte)((in[24 + inPos] & Integer.MAX_VALUE) >>> 16 & 255);
         out[96 + outPos] = (byte)(((in[24 + inPos] & Integer.MAX_VALUE) >>> 24 | (in[25 + inPos] & Integer.MAX_VALUE) << 7) & 255);
         out[97 + outPos] = (byte)((in[25 + inPos] & Integer.MAX_VALUE) >>> 1 & 255);
         out[98 + outPos] = (byte)((in[25 + inPos] & Integer.MAX_VALUE) >>> 9 & 255);
         out[99 + outPos] = (byte)((in[25 + inPos] & Integer.MAX_VALUE) >>> 17 & 255);
         out[100 + outPos] = (byte)(((in[25 + inPos] & Integer.MAX_VALUE) >>> 25 | (in[26 + inPos] & Integer.MAX_VALUE) << 6) & 255);
         out[101 + outPos] = (byte)((in[26 + inPos] & Integer.MAX_VALUE) >>> 2 & 255);
         out[102 + outPos] = (byte)((in[26 + inPos] & Integer.MAX_VALUE) >>> 10 & 255);
         out[103 + outPos] = (byte)((in[26 + inPos] & Integer.MAX_VALUE) >>> 18 & 255);
         out[104 + outPos] = (byte)(((in[26 + inPos] & Integer.MAX_VALUE) >>> 26 | (in[27 + inPos] & Integer.MAX_VALUE) << 5) & 255);
         out[105 + outPos] = (byte)((in[27 + inPos] & Integer.MAX_VALUE) >>> 3 & 255);
         out[106 + outPos] = (byte)((in[27 + inPos] & Integer.MAX_VALUE) >>> 11 & 255);
         out[107 + outPos] = (byte)((in[27 + inPos] & Integer.MAX_VALUE) >>> 19 & 255);
         out[108 + outPos] = (byte)(((in[27 + inPos] & Integer.MAX_VALUE) >>> 27 | (in[28 + inPos] & Integer.MAX_VALUE) << 4) & 255);
         out[109 + outPos] = (byte)((in[28 + inPos] & Integer.MAX_VALUE) >>> 4 & 255);
         out[110 + outPos] = (byte)((in[28 + inPos] & Integer.MAX_VALUE) >>> 12 & 255);
         out[111 + outPos] = (byte)((in[28 + inPos] & Integer.MAX_VALUE) >>> 20 & 255);
         out[112 + outPos] = (byte)(((in[28 + inPos] & Integer.MAX_VALUE) >>> 28 | (in[29 + inPos] & Integer.MAX_VALUE) << 3) & 255);
         out[113 + outPos] = (byte)((in[29 + inPos] & Integer.MAX_VALUE) >>> 5 & 255);
         out[114 + outPos] = (byte)((in[29 + inPos] & Integer.MAX_VALUE) >>> 13 & 255);
         out[115 + outPos] = (byte)((in[29 + inPos] & Integer.MAX_VALUE) >>> 21 & 255);
         out[116 + outPos] = (byte)(((in[29 + inPos] & Integer.MAX_VALUE) >>> 29 | (in[30 + inPos] & Integer.MAX_VALUE) << 2) & 255);
         out[117 + outPos] = (byte)((in[30 + inPos] & Integer.MAX_VALUE) >>> 6 & 255);
         out[118 + outPos] = (byte)((in[30 + inPos] & Integer.MAX_VALUE) >>> 14 & 255);
         out[119 + outPos] = (byte)((in[30 + inPos] & Integer.MAX_VALUE) >>> 22 & 255);
         out[120 + outPos] = (byte)(((in[30 + inPos] & Integer.MAX_VALUE) >>> 30 | (in[31 + inPos] & Integer.MAX_VALUE) << 1) & 255);
         out[121 + outPos] = (byte)((in[31 + inPos] & Integer.MAX_VALUE) >>> 7 & 255);
         out[122 + outPos] = (byte)((in[31 + inPos] & Integer.MAX_VALUE) >>> 15 & 255);
         out[123 + outPos] = (byte)((in[31 + inPos] & Integer.MAX_VALUE) >>> 23 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 16777215 | in[3 + inPos] << 24 & Integer.MAX_VALUE;
         out[1 + outPos] = in[3 + inPos] >> 7 & 1 | in[4 + inPos] << 1 & 511 | in[5 + inPos] << 9 & 131071 | in[6 + inPos] << 17 & 33554431 | in[7 + inPos] << 25 & Integer.MAX_VALUE;
         out[2 + outPos] = in[7 + inPos] >> 6 & 3 | in[8 + inPos] << 2 & 1023 | in[9 + inPos] << 10 & 262143 | in[10 + inPos] << 18 & 67108863 | in[11 + inPos] << 26 & Integer.MAX_VALUE;
         out[3 + outPos] = in[11 + inPos] >> 5 & 7 | in[12 + inPos] << 3 & 2047 | in[13 + inPos] << 11 & 524287 | in[14 + inPos] << 19 & 134217727 | in[15 + inPos] << 27 & Integer.MAX_VALUE;
         out[4 + outPos] = in[15 + inPos] >> 4 & 15 | in[16 + inPos] << 4 & 4095 | in[17 + inPos] << 12 & 1048575 | in[18 + inPos] << 20 & 268435455 | in[19 + inPos] << 28 & Integer.MAX_VALUE;
         out[5 + outPos] = in[19 + inPos] >> 3 & 31 | in[20 + inPos] << 5 & 8191 | in[21 + inPos] << 13 & 2097151 | in[22 + inPos] << 21 & 536870911 | in[23 + inPos] << 29 & Integer.MAX_VALUE;
         out[6 + outPos] = in[23 + inPos] >> 2 & 63 | in[24 + inPos] << 6 & 16383 | in[25 + inPos] << 14 & 4194303 | in[26 + inPos] << 22 & 1073741823 | in[27 + inPos] << 30 & Integer.MAX_VALUE;
         out[7 + outPos] = in[27 + inPos] >> 1 & 127 | in[28 + inPos] << 7 & 32767 | in[29 + inPos] << 15 & 8388607 | in[30 + inPos] << 23 & Integer.MAX_VALUE;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 16777215 | in.get(3 + inPos) << 24 & Integer.MAX_VALUE;
         out[1 + outPos] = in.get(3 + inPos) >> 7 & 1 | in.get(4 + inPos) << 1 & 511 | in.get(5 + inPos) << 9 & 131071 | in.get(6 + inPos) << 17 & 33554431 | in.get(7 + inPos) << 25 & Integer.MAX_VALUE;
         out[2 + outPos] = in.get(7 + inPos) >> 6 & 3 | in.get(8 + inPos) << 2 & 1023 | in.get(9 + inPos) << 10 & 262143 | in.get(10 + inPos) << 18 & 67108863 | in.get(11 + inPos) << 26 & Integer.MAX_VALUE;
         out[3 + outPos] = in.get(11 + inPos) >> 5 & 7 | in.get(12 + inPos) << 3 & 2047 | in.get(13 + inPos) << 11 & 524287 | in.get(14 + inPos) << 19 & 134217727 | in.get(15 + inPos) << 27 & Integer.MAX_VALUE;
         out[4 + outPos] = in.get(15 + inPos) >> 4 & 15 | in.get(16 + inPos) << 4 & 4095 | in.get(17 + inPos) << 12 & 1048575 | in.get(18 + inPos) << 20 & 268435455 | in.get(19 + inPos) << 28 & Integer.MAX_VALUE;
         out[5 + outPos] = in.get(19 + inPos) >> 3 & 31 | in.get(20 + inPos) << 5 & 8191 | in.get(21 + inPos) << 13 & 2097151 | in.get(22 + inPos) << 21 & 536870911 | in.get(23 + inPos) << 29 & Integer.MAX_VALUE;
         out[6 + outPos] = in.get(23 + inPos) >> 2 & 63 | in.get(24 + inPos) << 6 & 16383 | in.get(25 + inPos) << 14 & 4194303 | in.get(26 + inPos) << 22 & 1073741823 | in.get(27 + inPos) << 30 & Integer.MAX_VALUE;
         out[7 + outPos] = in.get(27 + inPos) >> 1 & 127 | in.get(28 + inPos) << 7 & 32767 | in.get(29 + inPos) << 15 & 8388607 | in.get(30 + inPos) << 23 & Integer.MAX_VALUE;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 16777215 | in[3 + inPos] << 24 & Integer.MAX_VALUE;
         out[1 + outPos] = in[3 + inPos] >> 7 & 1 | in[4 + inPos] << 1 & 511 | in[5 + inPos] << 9 & 131071 | in[6 + inPos] << 17 & 33554431 | in[7 + inPos] << 25 & Integer.MAX_VALUE;
         out[2 + outPos] = in[7 + inPos] >> 6 & 3 | in[8 + inPos] << 2 & 1023 | in[9 + inPos] << 10 & 262143 | in[10 + inPos] << 18 & 67108863 | in[11 + inPos] << 26 & Integer.MAX_VALUE;
         out[3 + outPos] = in[11 + inPos] >> 5 & 7 | in[12 + inPos] << 3 & 2047 | in[13 + inPos] << 11 & 524287 | in[14 + inPos] << 19 & 134217727 | in[15 + inPos] << 27 & Integer.MAX_VALUE;
         out[4 + outPos] = in[15 + inPos] >> 4 & 15 | in[16 + inPos] << 4 & 4095 | in[17 + inPos] << 12 & 1048575 | in[18 + inPos] << 20 & 268435455 | in[19 + inPos] << 28 & Integer.MAX_VALUE;
         out[5 + outPos] = in[19 + inPos] >> 3 & 31 | in[20 + inPos] << 5 & 8191 | in[21 + inPos] << 13 & 2097151 | in[22 + inPos] << 21 & 536870911 | in[23 + inPos] << 29 & Integer.MAX_VALUE;
         out[6 + outPos] = in[23 + inPos] >> 2 & 63 | in[24 + inPos] << 6 & 16383 | in[25 + inPos] << 14 & 4194303 | in[26 + inPos] << 22 & 1073741823 | in[27 + inPos] << 30 & Integer.MAX_VALUE;
         out[7 + outPos] = in[27 + inPos] >> 1 & 127 | in[28 + inPos] << 7 & 32767 | in[29 + inPos] << 15 & 8388607 | in[30 + inPos] << 23 & Integer.MAX_VALUE;
         out[8 + outPos] = in[31 + inPos] & 255 | in[32 + inPos] << 8 & '\uffff' | in[33 + inPos] << 16 & 16777215 | in[34 + inPos] << 24 & Integer.MAX_VALUE;
         out[9 + outPos] = in[34 + inPos] >> 7 & 1 | in[35 + inPos] << 1 & 511 | in[36 + inPos] << 9 & 131071 | in[37 + inPos] << 17 & 33554431 | in[38 + inPos] << 25 & Integer.MAX_VALUE;
         out[10 + outPos] = in[38 + inPos] >> 6 & 3 | in[39 + inPos] << 2 & 1023 | in[40 + inPos] << 10 & 262143 | in[41 + inPos] << 18 & 67108863 | in[42 + inPos] << 26 & Integer.MAX_VALUE;
         out[11 + outPos] = in[42 + inPos] >> 5 & 7 | in[43 + inPos] << 3 & 2047 | in[44 + inPos] << 11 & 524287 | in[45 + inPos] << 19 & 134217727 | in[46 + inPos] << 27 & Integer.MAX_VALUE;
         out[12 + outPos] = in[46 + inPos] >> 4 & 15 | in[47 + inPos] << 4 & 4095 | in[48 + inPos] << 12 & 1048575 | in[49 + inPos] << 20 & 268435455 | in[50 + inPos] << 28 & Integer.MAX_VALUE;
         out[13 + outPos] = in[50 + inPos] >> 3 & 31 | in[51 + inPos] << 5 & 8191 | in[52 + inPos] << 13 & 2097151 | in[53 + inPos] << 21 & 536870911 | in[54 + inPos] << 29 & Integer.MAX_VALUE;
         out[14 + outPos] = in[54 + inPos] >> 2 & 63 | in[55 + inPos] << 6 & 16383 | in[56 + inPos] << 14 & 4194303 | in[57 + inPos] << 22 & 1073741823 | in[58 + inPos] << 30 & Integer.MAX_VALUE;
         out[15 + outPos] = in[58 + inPos] >> 1 & 127 | in[59 + inPos] << 7 & 32767 | in[60 + inPos] << 15 & 8388607 | in[61 + inPos] << 23 & Integer.MAX_VALUE;
         out[16 + outPos] = in[62 + inPos] & 255 | in[63 + inPos] << 8 & '\uffff' | in[64 + inPos] << 16 & 16777215 | in[65 + inPos] << 24 & Integer.MAX_VALUE;
         out[17 + outPos] = in[65 + inPos] >> 7 & 1 | in[66 + inPos] << 1 & 511 | in[67 + inPos] << 9 & 131071 | in[68 + inPos] << 17 & 33554431 | in[69 + inPos] << 25 & Integer.MAX_VALUE;
         out[18 + outPos] = in[69 + inPos] >> 6 & 3 | in[70 + inPos] << 2 & 1023 | in[71 + inPos] << 10 & 262143 | in[72 + inPos] << 18 & 67108863 | in[73 + inPos] << 26 & Integer.MAX_VALUE;
         out[19 + outPos] = in[73 + inPos] >> 5 & 7 | in[74 + inPos] << 3 & 2047 | in[75 + inPos] << 11 & 524287 | in[76 + inPos] << 19 & 134217727 | in[77 + inPos] << 27 & Integer.MAX_VALUE;
         out[20 + outPos] = in[77 + inPos] >> 4 & 15 | in[78 + inPos] << 4 & 4095 | in[79 + inPos] << 12 & 1048575 | in[80 + inPos] << 20 & 268435455 | in[81 + inPos] << 28 & Integer.MAX_VALUE;
         out[21 + outPos] = in[81 + inPos] >> 3 & 31 | in[82 + inPos] << 5 & 8191 | in[83 + inPos] << 13 & 2097151 | in[84 + inPos] << 21 & 536870911 | in[85 + inPos] << 29 & Integer.MAX_VALUE;
         out[22 + outPos] = in[85 + inPos] >> 2 & 63 | in[86 + inPos] << 6 & 16383 | in[87 + inPos] << 14 & 4194303 | in[88 + inPos] << 22 & 1073741823 | in[89 + inPos] << 30 & Integer.MAX_VALUE;
         out[23 + outPos] = in[89 + inPos] >> 1 & 127 | in[90 + inPos] << 7 & 32767 | in[91 + inPos] << 15 & 8388607 | in[92 + inPos] << 23 & Integer.MAX_VALUE;
         out[24 + outPos] = in[93 + inPos] & 255 | in[94 + inPos] << 8 & '\uffff' | in[95 + inPos] << 16 & 16777215 | in[96 + inPos] << 24 & Integer.MAX_VALUE;
         out[25 + outPos] = in[96 + inPos] >> 7 & 1 | in[97 + inPos] << 1 & 511 | in[98 + inPos] << 9 & 131071 | in[99 + inPos] << 17 & 33554431 | in[100 + inPos] << 25 & Integer.MAX_VALUE;
         out[26 + outPos] = in[100 + inPos] >> 6 & 3 | in[101 + inPos] << 2 & 1023 | in[102 + inPos] << 10 & 262143 | in[103 + inPos] << 18 & 67108863 | in[104 + inPos] << 26 & Integer.MAX_VALUE;
         out[27 + outPos] = in[104 + inPos] >> 5 & 7 | in[105 + inPos] << 3 & 2047 | in[106 + inPos] << 11 & 524287 | in[107 + inPos] << 19 & 134217727 | in[108 + inPos] << 27 & Integer.MAX_VALUE;
         out[28 + outPos] = in[108 + inPos] >> 4 & 15 | in[109 + inPos] << 4 & 4095 | in[110 + inPos] << 12 & 1048575 | in[111 + inPos] << 20 & 268435455 | in[112 + inPos] << 28 & Integer.MAX_VALUE;
         out[29 + outPos] = in[112 + inPos] >> 3 & 31 | in[113 + inPos] << 5 & 8191 | in[114 + inPos] << 13 & 2097151 | in[115 + inPos] << 21 & 536870911 | in[116 + inPos] << 29 & Integer.MAX_VALUE;
         out[30 + outPos] = in[116 + inPos] >> 2 & 63 | in[117 + inPos] << 6 & 16383 | in[118 + inPos] << 14 & 4194303 | in[119 + inPos] << 22 & 1073741823 | in[120 + inPos] << 30 & Integer.MAX_VALUE;
         out[31 + outPos] = in[120 + inPos] >> 1 & 127 | in[121 + inPos] << 7 & 32767 | in[122 + inPos] << 15 & 8388607 | in[123 + inPos] << 23 & Integer.MAX_VALUE;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 16777215 | in.get(3 + inPos) << 24 & Integer.MAX_VALUE;
         out[1 + outPos] = in.get(3 + inPos) >> 7 & 1 | in.get(4 + inPos) << 1 & 511 | in.get(5 + inPos) << 9 & 131071 | in.get(6 + inPos) << 17 & 33554431 | in.get(7 + inPos) << 25 & Integer.MAX_VALUE;
         out[2 + outPos] = in.get(7 + inPos) >> 6 & 3 | in.get(8 + inPos) << 2 & 1023 | in.get(9 + inPos) << 10 & 262143 | in.get(10 + inPos) << 18 & 67108863 | in.get(11 + inPos) << 26 & Integer.MAX_VALUE;
         out[3 + outPos] = in.get(11 + inPos) >> 5 & 7 | in.get(12 + inPos) << 3 & 2047 | in.get(13 + inPos) << 11 & 524287 | in.get(14 + inPos) << 19 & 134217727 | in.get(15 + inPos) << 27 & Integer.MAX_VALUE;
         out[4 + outPos] = in.get(15 + inPos) >> 4 & 15 | in.get(16 + inPos) << 4 & 4095 | in.get(17 + inPos) << 12 & 1048575 | in.get(18 + inPos) << 20 & 268435455 | in.get(19 + inPos) << 28 & Integer.MAX_VALUE;
         out[5 + outPos] = in.get(19 + inPos) >> 3 & 31 | in.get(20 + inPos) << 5 & 8191 | in.get(21 + inPos) << 13 & 2097151 | in.get(22 + inPos) << 21 & 536870911 | in.get(23 + inPos) << 29 & Integer.MAX_VALUE;
         out[6 + outPos] = in.get(23 + inPos) >> 2 & 63 | in.get(24 + inPos) << 6 & 16383 | in.get(25 + inPos) << 14 & 4194303 | in.get(26 + inPos) << 22 & 1073741823 | in.get(27 + inPos) << 30 & Integer.MAX_VALUE;
         out[7 + outPos] = in.get(27 + inPos) >> 1 & 127 | in.get(28 + inPos) << 7 & 32767 | in.get(29 + inPos) << 15 & 8388607 | in.get(30 + inPos) << 23 & Integer.MAX_VALUE;
         out[8 + outPos] = in.get(31 + inPos) & 255 | in.get(32 + inPos) << 8 & '\uffff' | in.get(33 + inPos) << 16 & 16777215 | in.get(34 + inPos) << 24 & Integer.MAX_VALUE;
         out[9 + outPos] = in.get(34 + inPos) >> 7 & 1 | in.get(35 + inPos) << 1 & 511 | in.get(36 + inPos) << 9 & 131071 | in.get(37 + inPos) << 17 & 33554431 | in.get(38 + inPos) << 25 & Integer.MAX_VALUE;
         out[10 + outPos] = in.get(38 + inPos) >> 6 & 3 | in.get(39 + inPos) << 2 & 1023 | in.get(40 + inPos) << 10 & 262143 | in.get(41 + inPos) << 18 & 67108863 | in.get(42 + inPos) << 26 & Integer.MAX_VALUE;
         out[11 + outPos] = in.get(42 + inPos) >> 5 & 7 | in.get(43 + inPos) << 3 & 2047 | in.get(44 + inPos) << 11 & 524287 | in.get(45 + inPos) << 19 & 134217727 | in.get(46 + inPos) << 27 & Integer.MAX_VALUE;
         out[12 + outPos] = in.get(46 + inPos) >> 4 & 15 | in.get(47 + inPos) << 4 & 4095 | in.get(48 + inPos) << 12 & 1048575 | in.get(49 + inPos) << 20 & 268435455 | in.get(50 + inPos) << 28 & Integer.MAX_VALUE;
         out[13 + outPos] = in.get(50 + inPos) >> 3 & 31 | in.get(51 + inPos) << 5 & 8191 | in.get(52 + inPos) << 13 & 2097151 | in.get(53 + inPos) << 21 & 536870911 | in.get(54 + inPos) << 29 & Integer.MAX_VALUE;
         out[14 + outPos] = in.get(54 + inPos) >> 2 & 63 | in.get(55 + inPos) << 6 & 16383 | in.get(56 + inPos) << 14 & 4194303 | in.get(57 + inPos) << 22 & 1073741823 | in.get(58 + inPos) << 30 & Integer.MAX_VALUE;
         out[15 + outPos] = in.get(58 + inPos) >> 1 & 127 | in.get(59 + inPos) << 7 & 32767 | in.get(60 + inPos) << 15 & 8388607 | in.get(61 + inPos) << 23 & Integer.MAX_VALUE;
         out[16 + outPos] = in.get(62 + inPos) & 255 | in.get(63 + inPos) << 8 & '\uffff' | in.get(64 + inPos) << 16 & 16777215 | in.get(65 + inPos) << 24 & Integer.MAX_VALUE;
         out[17 + outPos] = in.get(65 + inPos) >> 7 & 1 | in.get(66 + inPos) << 1 & 511 | in.get(67 + inPos) << 9 & 131071 | in.get(68 + inPos) << 17 & 33554431 | in.get(69 + inPos) << 25 & Integer.MAX_VALUE;
         out[18 + outPos] = in.get(69 + inPos) >> 6 & 3 | in.get(70 + inPos) << 2 & 1023 | in.get(71 + inPos) << 10 & 262143 | in.get(72 + inPos) << 18 & 67108863 | in.get(73 + inPos) << 26 & Integer.MAX_VALUE;
         out[19 + outPos] = in.get(73 + inPos) >> 5 & 7 | in.get(74 + inPos) << 3 & 2047 | in.get(75 + inPos) << 11 & 524287 | in.get(76 + inPos) << 19 & 134217727 | in.get(77 + inPos) << 27 & Integer.MAX_VALUE;
         out[20 + outPos] = in.get(77 + inPos) >> 4 & 15 | in.get(78 + inPos) << 4 & 4095 | in.get(79 + inPos) << 12 & 1048575 | in.get(80 + inPos) << 20 & 268435455 | in.get(81 + inPos) << 28 & Integer.MAX_VALUE;
         out[21 + outPos] = in.get(81 + inPos) >> 3 & 31 | in.get(82 + inPos) << 5 & 8191 | in.get(83 + inPos) << 13 & 2097151 | in.get(84 + inPos) << 21 & 536870911 | in.get(85 + inPos) << 29 & Integer.MAX_VALUE;
         out[22 + outPos] = in.get(85 + inPos) >> 2 & 63 | in.get(86 + inPos) << 6 & 16383 | in.get(87 + inPos) << 14 & 4194303 | in.get(88 + inPos) << 22 & 1073741823 | in.get(89 + inPos) << 30 & Integer.MAX_VALUE;
         out[23 + outPos] = in.get(89 + inPos) >> 1 & 127 | in.get(90 + inPos) << 7 & 32767 | in.get(91 + inPos) << 15 & 8388607 | in.get(92 + inPos) << 23 & Integer.MAX_VALUE;
         out[24 + outPos] = in.get(93 + inPos) & 255 | in.get(94 + inPos) << 8 & '\uffff' | in.get(95 + inPos) << 16 & 16777215 | in.get(96 + inPos) << 24 & Integer.MAX_VALUE;
         out[25 + outPos] = in.get(96 + inPos) >> 7 & 1 | in.get(97 + inPos) << 1 & 511 | in.get(98 + inPos) << 9 & 131071 | in.get(99 + inPos) << 17 & 33554431 | in.get(100 + inPos) << 25 & Integer.MAX_VALUE;
         out[26 + outPos] = in.get(100 + inPos) >> 6 & 3 | in.get(101 + inPos) << 2 & 1023 | in.get(102 + inPos) << 10 & 262143 | in.get(103 + inPos) << 18 & 67108863 | in.get(104 + inPos) << 26 & Integer.MAX_VALUE;
         out[27 + outPos] = in.get(104 + inPos) >> 5 & 7 | in.get(105 + inPos) << 3 & 2047 | in.get(106 + inPos) << 11 & 524287 | in.get(107 + inPos) << 19 & 134217727 | in.get(108 + inPos) << 27 & Integer.MAX_VALUE;
         out[28 + outPos] = in.get(108 + inPos) >> 4 & 15 | in.get(109 + inPos) << 4 & 4095 | in.get(110 + inPos) << 12 & 1048575 | in.get(111 + inPos) << 20 & 268435455 | in.get(112 + inPos) << 28 & Integer.MAX_VALUE;
         out[29 + outPos] = in.get(112 + inPos) >> 3 & 31 | in.get(113 + inPos) << 5 & 8191 | in.get(114 + inPos) << 13 & 2097151 | in.get(115 + inPos) << 21 & 536870911 | in.get(116 + inPos) << 29 & Integer.MAX_VALUE;
         out[30 + outPos] = in.get(116 + inPos) >> 2 & 63 | in.get(117 + inPos) << 6 & 16383 | in.get(118 + inPos) << 14 & 4194303 | in.get(119 + inPos) << 22 & 1073741823 | in.get(120 + inPos) << 30 & Integer.MAX_VALUE;
         out[31 + outPos] = in.get(120 + inPos) >> 1 & 127 | in.get(121 + inPos) << 7 & 32767 | in.get(122 + inPos) << 15 & 8388607 | in.get(123 + inPos) << 23 & Integer.MAX_VALUE;
      }
   }

   private static final class Packer32 extends BytePacker {
      private Packer32() {
         super(32);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & -1 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & -1) >>> 8 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & -1) >>> 16 & 255);
         out[3 + outPos] = (byte)((in[0 + inPos] & -1) >>> 24 & 255);
         out[4 + outPos] = (byte)(in[1 + inPos] & -1 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & -1) >>> 8 & 255);
         out[6 + outPos] = (byte)((in[1 + inPos] & -1) >>> 16 & 255);
         out[7 + outPos] = (byte)((in[1 + inPos] & -1) >>> 24 & 255);
         out[8 + outPos] = (byte)(in[2 + inPos] & -1 & 255);
         out[9 + outPos] = (byte)((in[2 + inPos] & -1) >>> 8 & 255);
         out[10 + outPos] = (byte)((in[2 + inPos] & -1) >>> 16 & 255);
         out[11 + outPos] = (byte)((in[2 + inPos] & -1) >>> 24 & 255);
         out[12 + outPos] = (byte)(in[3 + inPos] & -1 & 255);
         out[13 + outPos] = (byte)((in[3 + inPos] & -1) >>> 8 & 255);
         out[14 + outPos] = (byte)((in[3 + inPos] & -1) >>> 16 & 255);
         out[15 + outPos] = (byte)((in[3 + inPos] & -1) >>> 24 & 255);
         out[16 + outPos] = (byte)(in[4 + inPos] & -1 & 255);
         out[17 + outPos] = (byte)((in[4 + inPos] & -1) >>> 8 & 255);
         out[18 + outPos] = (byte)((in[4 + inPos] & -1) >>> 16 & 255);
         out[19 + outPos] = (byte)((in[4 + inPos] & -1) >>> 24 & 255);
         out[20 + outPos] = (byte)(in[5 + inPos] & -1 & 255);
         out[21 + outPos] = (byte)((in[5 + inPos] & -1) >>> 8 & 255);
         out[22 + outPos] = (byte)((in[5 + inPos] & -1) >>> 16 & 255);
         out[23 + outPos] = (byte)((in[5 + inPos] & -1) >>> 24 & 255);
         out[24 + outPos] = (byte)(in[6 + inPos] & -1 & 255);
         out[25 + outPos] = (byte)((in[6 + inPos] & -1) >>> 8 & 255);
         out[26 + outPos] = (byte)((in[6 + inPos] & -1) >>> 16 & 255);
         out[27 + outPos] = (byte)((in[6 + inPos] & -1) >>> 24 & 255);
         out[28 + outPos] = (byte)(in[7 + inPos] & -1 & 255);
         out[29 + outPos] = (byte)((in[7 + inPos] & -1) >>> 8 & 255);
         out[30 + outPos] = (byte)((in[7 + inPos] & -1) >>> 16 & 255);
         out[31 + outPos] = (byte)((in[7 + inPos] & -1) >>> 24 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(in[0 + inPos] & -1 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & -1) >>> 8 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & -1) >>> 16 & 255);
         out[3 + outPos] = (byte)((in[0 + inPos] & -1) >>> 24 & 255);
         out[4 + outPos] = (byte)(in[1 + inPos] & -1 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & -1) >>> 8 & 255);
         out[6 + outPos] = (byte)((in[1 + inPos] & -1) >>> 16 & 255);
         out[7 + outPos] = (byte)((in[1 + inPos] & -1) >>> 24 & 255);
         out[8 + outPos] = (byte)(in[2 + inPos] & -1 & 255);
         out[9 + outPos] = (byte)((in[2 + inPos] & -1) >>> 8 & 255);
         out[10 + outPos] = (byte)((in[2 + inPos] & -1) >>> 16 & 255);
         out[11 + outPos] = (byte)((in[2 + inPos] & -1) >>> 24 & 255);
         out[12 + outPos] = (byte)(in[3 + inPos] & -1 & 255);
         out[13 + outPos] = (byte)((in[3 + inPos] & -1) >>> 8 & 255);
         out[14 + outPos] = (byte)((in[3 + inPos] & -1) >>> 16 & 255);
         out[15 + outPos] = (byte)((in[3 + inPos] & -1) >>> 24 & 255);
         out[16 + outPos] = (byte)(in[4 + inPos] & -1 & 255);
         out[17 + outPos] = (byte)((in[4 + inPos] & -1) >>> 8 & 255);
         out[18 + outPos] = (byte)((in[4 + inPos] & -1) >>> 16 & 255);
         out[19 + outPos] = (byte)((in[4 + inPos] & -1) >>> 24 & 255);
         out[20 + outPos] = (byte)(in[5 + inPos] & -1 & 255);
         out[21 + outPos] = (byte)((in[5 + inPos] & -1) >>> 8 & 255);
         out[22 + outPos] = (byte)((in[5 + inPos] & -1) >>> 16 & 255);
         out[23 + outPos] = (byte)((in[5 + inPos] & -1) >>> 24 & 255);
         out[24 + outPos] = (byte)(in[6 + inPos] & -1 & 255);
         out[25 + outPos] = (byte)((in[6 + inPos] & -1) >>> 8 & 255);
         out[26 + outPos] = (byte)((in[6 + inPos] & -1) >>> 16 & 255);
         out[27 + outPos] = (byte)((in[6 + inPos] & -1) >>> 24 & 255);
         out[28 + outPos] = (byte)(in[7 + inPos] & -1 & 255);
         out[29 + outPos] = (byte)((in[7 + inPos] & -1) >>> 8 & 255);
         out[30 + outPos] = (byte)((in[7 + inPos] & -1) >>> 16 & 255);
         out[31 + outPos] = (byte)((in[7 + inPos] & -1) >>> 24 & 255);
         out[32 + outPos] = (byte)(in[8 + inPos] & -1 & 255);
         out[33 + outPos] = (byte)((in[8 + inPos] & -1) >>> 8 & 255);
         out[34 + outPos] = (byte)((in[8 + inPos] & -1) >>> 16 & 255);
         out[35 + outPos] = (byte)((in[8 + inPos] & -1) >>> 24 & 255);
         out[36 + outPos] = (byte)(in[9 + inPos] & -1 & 255);
         out[37 + outPos] = (byte)((in[9 + inPos] & -1) >>> 8 & 255);
         out[38 + outPos] = (byte)((in[9 + inPos] & -1) >>> 16 & 255);
         out[39 + outPos] = (byte)((in[9 + inPos] & -1) >>> 24 & 255);
         out[40 + outPos] = (byte)(in[10 + inPos] & -1 & 255);
         out[41 + outPos] = (byte)((in[10 + inPos] & -1) >>> 8 & 255);
         out[42 + outPos] = (byte)((in[10 + inPos] & -1) >>> 16 & 255);
         out[43 + outPos] = (byte)((in[10 + inPos] & -1) >>> 24 & 255);
         out[44 + outPos] = (byte)(in[11 + inPos] & -1 & 255);
         out[45 + outPos] = (byte)((in[11 + inPos] & -1) >>> 8 & 255);
         out[46 + outPos] = (byte)((in[11 + inPos] & -1) >>> 16 & 255);
         out[47 + outPos] = (byte)((in[11 + inPos] & -1) >>> 24 & 255);
         out[48 + outPos] = (byte)(in[12 + inPos] & -1 & 255);
         out[49 + outPos] = (byte)((in[12 + inPos] & -1) >>> 8 & 255);
         out[50 + outPos] = (byte)((in[12 + inPos] & -1) >>> 16 & 255);
         out[51 + outPos] = (byte)((in[12 + inPos] & -1) >>> 24 & 255);
         out[52 + outPos] = (byte)(in[13 + inPos] & -1 & 255);
         out[53 + outPos] = (byte)((in[13 + inPos] & -1) >>> 8 & 255);
         out[54 + outPos] = (byte)((in[13 + inPos] & -1) >>> 16 & 255);
         out[55 + outPos] = (byte)((in[13 + inPos] & -1) >>> 24 & 255);
         out[56 + outPos] = (byte)(in[14 + inPos] & -1 & 255);
         out[57 + outPos] = (byte)((in[14 + inPos] & -1) >>> 8 & 255);
         out[58 + outPos] = (byte)((in[14 + inPos] & -1) >>> 16 & 255);
         out[59 + outPos] = (byte)((in[14 + inPos] & -1) >>> 24 & 255);
         out[60 + outPos] = (byte)(in[15 + inPos] & -1 & 255);
         out[61 + outPos] = (byte)((in[15 + inPos] & -1) >>> 8 & 255);
         out[62 + outPos] = (byte)((in[15 + inPos] & -1) >>> 16 & 255);
         out[63 + outPos] = (byte)((in[15 + inPos] & -1) >>> 24 & 255);
         out[64 + outPos] = (byte)(in[16 + inPos] & -1 & 255);
         out[65 + outPos] = (byte)((in[16 + inPos] & -1) >>> 8 & 255);
         out[66 + outPos] = (byte)((in[16 + inPos] & -1) >>> 16 & 255);
         out[67 + outPos] = (byte)((in[16 + inPos] & -1) >>> 24 & 255);
         out[68 + outPos] = (byte)(in[17 + inPos] & -1 & 255);
         out[69 + outPos] = (byte)((in[17 + inPos] & -1) >>> 8 & 255);
         out[70 + outPos] = (byte)((in[17 + inPos] & -1) >>> 16 & 255);
         out[71 + outPos] = (byte)((in[17 + inPos] & -1) >>> 24 & 255);
         out[72 + outPos] = (byte)(in[18 + inPos] & -1 & 255);
         out[73 + outPos] = (byte)((in[18 + inPos] & -1) >>> 8 & 255);
         out[74 + outPos] = (byte)((in[18 + inPos] & -1) >>> 16 & 255);
         out[75 + outPos] = (byte)((in[18 + inPos] & -1) >>> 24 & 255);
         out[76 + outPos] = (byte)(in[19 + inPos] & -1 & 255);
         out[77 + outPos] = (byte)((in[19 + inPos] & -1) >>> 8 & 255);
         out[78 + outPos] = (byte)((in[19 + inPos] & -1) >>> 16 & 255);
         out[79 + outPos] = (byte)((in[19 + inPos] & -1) >>> 24 & 255);
         out[80 + outPos] = (byte)(in[20 + inPos] & -1 & 255);
         out[81 + outPos] = (byte)((in[20 + inPos] & -1) >>> 8 & 255);
         out[82 + outPos] = (byte)((in[20 + inPos] & -1) >>> 16 & 255);
         out[83 + outPos] = (byte)((in[20 + inPos] & -1) >>> 24 & 255);
         out[84 + outPos] = (byte)(in[21 + inPos] & -1 & 255);
         out[85 + outPos] = (byte)((in[21 + inPos] & -1) >>> 8 & 255);
         out[86 + outPos] = (byte)((in[21 + inPos] & -1) >>> 16 & 255);
         out[87 + outPos] = (byte)((in[21 + inPos] & -1) >>> 24 & 255);
         out[88 + outPos] = (byte)(in[22 + inPos] & -1 & 255);
         out[89 + outPos] = (byte)((in[22 + inPos] & -1) >>> 8 & 255);
         out[90 + outPos] = (byte)((in[22 + inPos] & -1) >>> 16 & 255);
         out[91 + outPos] = (byte)((in[22 + inPos] & -1) >>> 24 & 255);
         out[92 + outPos] = (byte)(in[23 + inPos] & -1 & 255);
         out[93 + outPos] = (byte)((in[23 + inPos] & -1) >>> 8 & 255);
         out[94 + outPos] = (byte)((in[23 + inPos] & -1) >>> 16 & 255);
         out[95 + outPos] = (byte)((in[23 + inPos] & -1) >>> 24 & 255);
         out[96 + outPos] = (byte)(in[24 + inPos] & -1 & 255);
         out[97 + outPos] = (byte)((in[24 + inPos] & -1) >>> 8 & 255);
         out[98 + outPos] = (byte)((in[24 + inPos] & -1) >>> 16 & 255);
         out[99 + outPos] = (byte)((in[24 + inPos] & -1) >>> 24 & 255);
         out[100 + outPos] = (byte)(in[25 + inPos] & -1 & 255);
         out[101 + outPos] = (byte)((in[25 + inPos] & -1) >>> 8 & 255);
         out[102 + outPos] = (byte)((in[25 + inPos] & -1) >>> 16 & 255);
         out[103 + outPos] = (byte)((in[25 + inPos] & -1) >>> 24 & 255);
         out[104 + outPos] = (byte)(in[26 + inPos] & -1 & 255);
         out[105 + outPos] = (byte)((in[26 + inPos] & -1) >>> 8 & 255);
         out[106 + outPos] = (byte)((in[26 + inPos] & -1) >>> 16 & 255);
         out[107 + outPos] = (byte)((in[26 + inPos] & -1) >>> 24 & 255);
         out[108 + outPos] = (byte)(in[27 + inPos] & -1 & 255);
         out[109 + outPos] = (byte)((in[27 + inPos] & -1) >>> 8 & 255);
         out[110 + outPos] = (byte)((in[27 + inPos] & -1) >>> 16 & 255);
         out[111 + outPos] = (byte)((in[27 + inPos] & -1) >>> 24 & 255);
         out[112 + outPos] = (byte)(in[28 + inPos] & -1 & 255);
         out[113 + outPos] = (byte)((in[28 + inPos] & -1) >>> 8 & 255);
         out[114 + outPos] = (byte)((in[28 + inPos] & -1) >>> 16 & 255);
         out[115 + outPos] = (byte)((in[28 + inPos] & -1) >>> 24 & 255);
         out[116 + outPos] = (byte)(in[29 + inPos] & -1 & 255);
         out[117 + outPos] = (byte)((in[29 + inPos] & -1) >>> 8 & 255);
         out[118 + outPos] = (byte)((in[29 + inPos] & -1) >>> 16 & 255);
         out[119 + outPos] = (byte)((in[29 + inPos] & -1) >>> 24 & 255);
         out[120 + outPos] = (byte)(in[30 + inPos] & -1 & 255);
         out[121 + outPos] = (byte)((in[30 + inPos] & -1) >>> 8 & 255);
         out[122 + outPos] = (byte)((in[30 + inPos] & -1) >>> 16 & 255);
         out[123 + outPos] = (byte)((in[30 + inPos] & -1) >>> 24 & 255);
         out[124 + outPos] = (byte)(in[31 + inPos] & -1 & 255);
         out[125 + outPos] = (byte)((in[31 + inPos] & -1) >>> 8 & 255);
         out[126 + outPos] = (byte)((in[31 + inPos] & -1) >>> 16 & 255);
         out[127 + outPos] = (byte)((in[31 + inPos] & -1) >>> 24 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 16777215 | in[3 + inPos] << 24 & -1;
         out[1 + outPos] = in[4 + inPos] & 255 | in[5 + inPos] << 8 & '\uffff' | in[6 + inPos] << 16 & 16777215 | in[7 + inPos] << 24 & -1;
         out[2 + outPos] = in[8 + inPos] & 255 | in[9 + inPos] << 8 & '\uffff' | in[10 + inPos] << 16 & 16777215 | in[11 + inPos] << 24 & -1;
         out[3 + outPos] = in[12 + inPos] & 255 | in[13 + inPos] << 8 & '\uffff' | in[14 + inPos] << 16 & 16777215 | in[15 + inPos] << 24 & -1;
         out[4 + outPos] = in[16 + inPos] & 255 | in[17 + inPos] << 8 & '\uffff' | in[18 + inPos] << 16 & 16777215 | in[19 + inPos] << 24 & -1;
         out[5 + outPos] = in[20 + inPos] & 255 | in[21 + inPos] << 8 & '\uffff' | in[22 + inPos] << 16 & 16777215 | in[23 + inPos] << 24 & -1;
         out[6 + outPos] = in[24 + inPos] & 255 | in[25 + inPos] << 8 & '\uffff' | in[26 + inPos] << 16 & 16777215 | in[27 + inPos] << 24 & -1;
         out[7 + outPos] = in[28 + inPos] & 255 | in[29 + inPos] << 8 & '\uffff' | in[30 + inPos] << 16 & 16777215 | in[31 + inPos] << 24 & -1;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 16777215 | in.get(3 + inPos) << 24 & -1;
         out[1 + outPos] = in.get(4 + inPos) & 255 | in.get(5 + inPos) << 8 & '\uffff' | in.get(6 + inPos) << 16 & 16777215 | in.get(7 + inPos) << 24 & -1;
         out[2 + outPos] = in.get(8 + inPos) & 255 | in.get(9 + inPos) << 8 & '\uffff' | in.get(10 + inPos) << 16 & 16777215 | in.get(11 + inPos) << 24 & -1;
         out[3 + outPos] = in.get(12 + inPos) & 255 | in.get(13 + inPos) << 8 & '\uffff' | in.get(14 + inPos) << 16 & 16777215 | in.get(15 + inPos) << 24 & -1;
         out[4 + outPos] = in.get(16 + inPos) & 255 | in.get(17 + inPos) << 8 & '\uffff' | in.get(18 + inPos) << 16 & 16777215 | in.get(19 + inPos) << 24 & -1;
         out[5 + outPos] = in.get(20 + inPos) & 255 | in.get(21 + inPos) << 8 & '\uffff' | in.get(22 + inPos) << 16 & 16777215 | in.get(23 + inPos) << 24 & -1;
         out[6 + outPos] = in.get(24 + inPos) & 255 | in.get(25 + inPos) << 8 & '\uffff' | in.get(26 + inPos) << 16 & 16777215 | in.get(27 + inPos) << 24 & -1;
         out[7 + outPos] = in.get(28 + inPos) & 255 | in.get(29 + inPos) << 8 & '\uffff' | in.get(30 + inPos) << 16 & 16777215 | in.get(31 + inPos) << 24 & -1;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] & 255 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] << 16 & 16777215 | in[3 + inPos] << 24 & -1;
         out[1 + outPos] = in[4 + inPos] & 255 | in[5 + inPos] << 8 & '\uffff' | in[6 + inPos] << 16 & 16777215 | in[7 + inPos] << 24 & -1;
         out[2 + outPos] = in[8 + inPos] & 255 | in[9 + inPos] << 8 & '\uffff' | in[10 + inPos] << 16 & 16777215 | in[11 + inPos] << 24 & -1;
         out[3 + outPos] = in[12 + inPos] & 255 | in[13 + inPos] << 8 & '\uffff' | in[14 + inPos] << 16 & 16777215 | in[15 + inPos] << 24 & -1;
         out[4 + outPos] = in[16 + inPos] & 255 | in[17 + inPos] << 8 & '\uffff' | in[18 + inPos] << 16 & 16777215 | in[19 + inPos] << 24 & -1;
         out[5 + outPos] = in[20 + inPos] & 255 | in[21 + inPos] << 8 & '\uffff' | in[22 + inPos] << 16 & 16777215 | in[23 + inPos] << 24 & -1;
         out[6 + outPos] = in[24 + inPos] & 255 | in[25 + inPos] << 8 & '\uffff' | in[26 + inPos] << 16 & 16777215 | in[27 + inPos] << 24 & -1;
         out[7 + outPos] = in[28 + inPos] & 255 | in[29 + inPos] << 8 & '\uffff' | in[30 + inPos] << 16 & 16777215 | in[31 + inPos] << 24 & -1;
         out[8 + outPos] = in[32 + inPos] & 255 | in[33 + inPos] << 8 & '\uffff' | in[34 + inPos] << 16 & 16777215 | in[35 + inPos] << 24 & -1;
         out[9 + outPos] = in[36 + inPos] & 255 | in[37 + inPos] << 8 & '\uffff' | in[38 + inPos] << 16 & 16777215 | in[39 + inPos] << 24 & -1;
         out[10 + outPos] = in[40 + inPos] & 255 | in[41 + inPos] << 8 & '\uffff' | in[42 + inPos] << 16 & 16777215 | in[43 + inPos] << 24 & -1;
         out[11 + outPos] = in[44 + inPos] & 255 | in[45 + inPos] << 8 & '\uffff' | in[46 + inPos] << 16 & 16777215 | in[47 + inPos] << 24 & -1;
         out[12 + outPos] = in[48 + inPos] & 255 | in[49 + inPos] << 8 & '\uffff' | in[50 + inPos] << 16 & 16777215 | in[51 + inPos] << 24 & -1;
         out[13 + outPos] = in[52 + inPos] & 255 | in[53 + inPos] << 8 & '\uffff' | in[54 + inPos] << 16 & 16777215 | in[55 + inPos] << 24 & -1;
         out[14 + outPos] = in[56 + inPos] & 255 | in[57 + inPos] << 8 & '\uffff' | in[58 + inPos] << 16 & 16777215 | in[59 + inPos] << 24 & -1;
         out[15 + outPos] = in[60 + inPos] & 255 | in[61 + inPos] << 8 & '\uffff' | in[62 + inPos] << 16 & 16777215 | in[63 + inPos] << 24 & -1;
         out[16 + outPos] = in[64 + inPos] & 255 | in[65 + inPos] << 8 & '\uffff' | in[66 + inPos] << 16 & 16777215 | in[67 + inPos] << 24 & -1;
         out[17 + outPos] = in[68 + inPos] & 255 | in[69 + inPos] << 8 & '\uffff' | in[70 + inPos] << 16 & 16777215 | in[71 + inPos] << 24 & -1;
         out[18 + outPos] = in[72 + inPos] & 255 | in[73 + inPos] << 8 & '\uffff' | in[74 + inPos] << 16 & 16777215 | in[75 + inPos] << 24 & -1;
         out[19 + outPos] = in[76 + inPos] & 255 | in[77 + inPos] << 8 & '\uffff' | in[78 + inPos] << 16 & 16777215 | in[79 + inPos] << 24 & -1;
         out[20 + outPos] = in[80 + inPos] & 255 | in[81 + inPos] << 8 & '\uffff' | in[82 + inPos] << 16 & 16777215 | in[83 + inPos] << 24 & -1;
         out[21 + outPos] = in[84 + inPos] & 255 | in[85 + inPos] << 8 & '\uffff' | in[86 + inPos] << 16 & 16777215 | in[87 + inPos] << 24 & -1;
         out[22 + outPos] = in[88 + inPos] & 255 | in[89 + inPos] << 8 & '\uffff' | in[90 + inPos] << 16 & 16777215 | in[91 + inPos] << 24 & -1;
         out[23 + outPos] = in[92 + inPos] & 255 | in[93 + inPos] << 8 & '\uffff' | in[94 + inPos] << 16 & 16777215 | in[95 + inPos] << 24 & -1;
         out[24 + outPos] = in[96 + inPos] & 255 | in[97 + inPos] << 8 & '\uffff' | in[98 + inPos] << 16 & 16777215 | in[99 + inPos] << 24 & -1;
         out[25 + outPos] = in[100 + inPos] & 255 | in[101 + inPos] << 8 & '\uffff' | in[102 + inPos] << 16 & 16777215 | in[103 + inPos] << 24 & -1;
         out[26 + outPos] = in[104 + inPos] & 255 | in[105 + inPos] << 8 & '\uffff' | in[106 + inPos] << 16 & 16777215 | in[107 + inPos] << 24 & -1;
         out[27 + outPos] = in[108 + inPos] & 255 | in[109 + inPos] << 8 & '\uffff' | in[110 + inPos] << 16 & 16777215 | in[111 + inPos] << 24 & -1;
         out[28 + outPos] = in[112 + inPos] & 255 | in[113 + inPos] << 8 & '\uffff' | in[114 + inPos] << 16 & 16777215 | in[115 + inPos] << 24 & -1;
         out[29 + outPos] = in[116 + inPos] & 255 | in[117 + inPos] << 8 & '\uffff' | in[118 + inPos] << 16 & 16777215 | in[119 + inPos] << 24 & -1;
         out[30 + outPos] = in[120 + inPos] & 255 | in[121 + inPos] << 8 & '\uffff' | in[122 + inPos] << 16 & 16777215 | in[123 + inPos] << 24 & -1;
         out[31 + outPos] = in[124 + inPos] & 255 | in[125 + inPos] << 8 & '\uffff' | in[126 + inPos] << 16 & 16777215 | in[127 + inPos] << 24 & -1;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) & 255 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) << 16 & 16777215 | in.get(3 + inPos) << 24 & -1;
         out[1 + outPos] = in.get(4 + inPos) & 255 | in.get(5 + inPos) << 8 & '\uffff' | in.get(6 + inPos) << 16 & 16777215 | in.get(7 + inPos) << 24 & -1;
         out[2 + outPos] = in.get(8 + inPos) & 255 | in.get(9 + inPos) << 8 & '\uffff' | in.get(10 + inPos) << 16 & 16777215 | in.get(11 + inPos) << 24 & -1;
         out[3 + outPos] = in.get(12 + inPos) & 255 | in.get(13 + inPos) << 8 & '\uffff' | in.get(14 + inPos) << 16 & 16777215 | in.get(15 + inPos) << 24 & -1;
         out[4 + outPos] = in.get(16 + inPos) & 255 | in.get(17 + inPos) << 8 & '\uffff' | in.get(18 + inPos) << 16 & 16777215 | in.get(19 + inPos) << 24 & -1;
         out[5 + outPos] = in.get(20 + inPos) & 255 | in.get(21 + inPos) << 8 & '\uffff' | in.get(22 + inPos) << 16 & 16777215 | in.get(23 + inPos) << 24 & -1;
         out[6 + outPos] = in.get(24 + inPos) & 255 | in.get(25 + inPos) << 8 & '\uffff' | in.get(26 + inPos) << 16 & 16777215 | in.get(27 + inPos) << 24 & -1;
         out[7 + outPos] = in.get(28 + inPos) & 255 | in.get(29 + inPos) << 8 & '\uffff' | in.get(30 + inPos) << 16 & 16777215 | in.get(31 + inPos) << 24 & -1;
         out[8 + outPos] = in.get(32 + inPos) & 255 | in.get(33 + inPos) << 8 & '\uffff' | in.get(34 + inPos) << 16 & 16777215 | in.get(35 + inPos) << 24 & -1;
         out[9 + outPos] = in.get(36 + inPos) & 255 | in.get(37 + inPos) << 8 & '\uffff' | in.get(38 + inPos) << 16 & 16777215 | in.get(39 + inPos) << 24 & -1;
         out[10 + outPos] = in.get(40 + inPos) & 255 | in.get(41 + inPos) << 8 & '\uffff' | in.get(42 + inPos) << 16 & 16777215 | in.get(43 + inPos) << 24 & -1;
         out[11 + outPos] = in.get(44 + inPos) & 255 | in.get(45 + inPos) << 8 & '\uffff' | in.get(46 + inPos) << 16 & 16777215 | in.get(47 + inPos) << 24 & -1;
         out[12 + outPos] = in.get(48 + inPos) & 255 | in.get(49 + inPos) << 8 & '\uffff' | in.get(50 + inPos) << 16 & 16777215 | in.get(51 + inPos) << 24 & -1;
         out[13 + outPos] = in.get(52 + inPos) & 255 | in.get(53 + inPos) << 8 & '\uffff' | in.get(54 + inPos) << 16 & 16777215 | in.get(55 + inPos) << 24 & -1;
         out[14 + outPos] = in.get(56 + inPos) & 255 | in.get(57 + inPos) << 8 & '\uffff' | in.get(58 + inPos) << 16 & 16777215 | in.get(59 + inPos) << 24 & -1;
         out[15 + outPos] = in.get(60 + inPos) & 255 | in.get(61 + inPos) << 8 & '\uffff' | in.get(62 + inPos) << 16 & 16777215 | in.get(63 + inPos) << 24 & -1;
         out[16 + outPos] = in.get(64 + inPos) & 255 | in.get(65 + inPos) << 8 & '\uffff' | in.get(66 + inPos) << 16 & 16777215 | in.get(67 + inPos) << 24 & -1;
         out[17 + outPos] = in.get(68 + inPos) & 255 | in.get(69 + inPos) << 8 & '\uffff' | in.get(70 + inPos) << 16 & 16777215 | in.get(71 + inPos) << 24 & -1;
         out[18 + outPos] = in.get(72 + inPos) & 255 | in.get(73 + inPos) << 8 & '\uffff' | in.get(74 + inPos) << 16 & 16777215 | in.get(75 + inPos) << 24 & -1;
         out[19 + outPos] = in.get(76 + inPos) & 255 | in.get(77 + inPos) << 8 & '\uffff' | in.get(78 + inPos) << 16 & 16777215 | in.get(79 + inPos) << 24 & -1;
         out[20 + outPos] = in.get(80 + inPos) & 255 | in.get(81 + inPos) << 8 & '\uffff' | in.get(82 + inPos) << 16 & 16777215 | in.get(83 + inPos) << 24 & -1;
         out[21 + outPos] = in.get(84 + inPos) & 255 | in.get(85 + inPos) << 8 & '\uffff' | in.get(86 + inPos) << 16 & 16777215 | in.get(87 + inPos) << 24 & -1;
         out[22 + outPos] = in.get(88 + inPos) & 255 | in.get(89 + inPos) << 8 & '\uffff' | in.get(90 + inPos) << 16 & 16777215 | in.get(91 + inPos) << 24 & -1;
         out[23 + outPos] = in.get(92 + inPos) & 255 | in.get(93 + inPos) << 8 & '\uffff' | in.get(94 + inPos) << 16 & 16777215 | in.get(95 + inPos) << 24 & -1;
         out[24 + outPos] = in.get(96 + inPos) & 255 | in.get(97 + inPos) << 8 & '\uffff' | in.get(98 + inPos) << 16 & 16777215 | in.get(99 + inPos) << 24 & -1;
         out[25 + outPos] = in.get(100 + inPos) & 255 | in.get(101 + inPos) << 8 & '\uffff' | in.get(102 + inPos) << 16 & 16777215 | in.get(103 + inPos) << 24 & -1;
         out[26 + outPos] = in.get(104 + inPos) & 255 | in.get(105 + inPos) << 8 & '\uffff' | in.get(106 + inPos) << 16 & 16777215 | in.get(107 + inPos) << 24 & -1;
         out[27 + outPos] = in.get(108 + inPos) & 255 | in.get(109 + inPos) << 8 & '\uffff' | in.get(110 + inPos) << 16 & 16777215 | in.get(111 + inPos) << 24 & -1;
         out[28 + outPos] = in.get(112 + inPos) & 255 | in.get(113 + inPos) << 8 & '\uffff' | in.get(114 + inPos) << 16 & 16777215 | in.get(115 + inPos) << 24 & -1;
         out[29 + outPos] = in.get(116 + inPos) & 255 | in.get(117 + inPos) << 8 & '\uffff' | in.get(118 + inPos) << 16 & 16777215 | in.get(119 + inPos) << 24 & -1;
         out[30 + outPos] = in.get(120 + inPos) & 255 | in.get(121 + inPos) << 8 & '\uffff' | in.get(122 + inPos) << 16 & 16777215 | in.get(123 + inPos) << 24 & -1;
         out[31 + outPos] = in.get(124 + inPos) & 255 | in.get(125 + inPos) << 8 & '\uffff' | in.get(126 + inPos) << 16 & 16777215 | in.get(127 + inPos) << 24 & -1;
      }
   }
}
