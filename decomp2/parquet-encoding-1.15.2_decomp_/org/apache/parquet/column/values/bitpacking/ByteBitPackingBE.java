package org.apache.parquet.column.values.bitpacking;

import java.nio.ByteBuffer;

public abstract class ByteBitPackingBE {
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
            return ByteBitPackingBE.packers[bitWidth];
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
         out[0 + outPos] = (byte)(((in[0 + inPos] & 1) << 7 | (in[1 + inPos] & 1) << 6 | (in[2 + inPos] & 1) << 5 | (in[3 + inPos] & 1) << 4 | (in[4 + inPos] & 1) << 3 | (in[5 + inPos] & 1) << 2 | (in[6 + inPos] & 1) << 1 | in[7 + inPos] & 1) & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(((in[0 + inPos] & 1) << 7 | (in[1 + inPos] & 1) << 6 | (in[2 + inPos] & 1) << 5 | (in[3 + inPos] & 1) << 4 | (in[4 + inPos] & 1) << 3 | (in[5 + inPos] & 1) << 2 | (in[6 + inPos] & 1) << 1 | in[7 + inPos] & 1) & 255);
         out[1 + outPos] = (byte)(((in[8 + inPos] & 1) << 7 | (in[9 + inPos] & 1) << 6 | (in[10 + inPos] & 1) << 5 | (in[11 + inPos] & 1) << 4 | (in[12 + inPos] & 1) << 3 | (in[13 + inPos] & 1) << 2 | (in[14 + inPos] & 1) << 1 | in[15 + inPos] & 1) & 255);
         out[2 + outPos] = (byte)(((in[16 + inPos] & 1) << 7 | (in[17 + inPos] & 1) << 6 | (in[18 + inPos] & 1) << 5 | (in[19 + inPos] & 1) << 4 | (in[20 + inPos] & 1) << 3 | (in[21 + inPos] & 1) << 2 | (in[22 + inPos] & 1) << 1 | in[23 + inPos] & 1) & 255);
         out[3 + outPos] = (byte)(((in[24 + inPos] & 1) << 7 | (in[25 + inPos] & 1) << 6 | (in[26 + inPos] & 1) << 5 | (in[27 + inPos] & 1) << 4 | (in[28 + inPos] & 1) << 3 | (in[29 + inPos] & 1) << 2 | (in[30 + inPos] & 1) << 1 | in[31 + inPos] & 1) & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] >> 7 & 1;
         out[1 + outPos] = in[0 + inPos] >> 6 & 1;
         out[2 + outPos] = in[0 + inPos] >> 5 & 1;
         out[3 + outPos] = in[0 + inPos] >> 4 & 1;
         out[4 + outPos] = in[0 + inPos] >> 3 & 1;
         out[5 + outPos] = in[0 + inPos] >> 2 & 1;
         out[6 + outPos] = in[0 + inPos] >> 1 & 1;
         out[7 + outPos] = in[0 + inPos] & 1;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) >> 7 & 1;
         out[1 + outPos] = in.get(0 + inPos) >> 6 & 1;
         out[2 + outPos] = in.get(0 + inPos) >> 5 & 1;
         out[3 + outPos] = in.get(0 + inPos) >> 4 & 1;
         out[4 + outPos] = in.get(0 + inPos) >> 3 & 1;
         out[5 + outPos] = in.get(0 + inPos) >> 2 & 1;
         out[6 + outPos] = in.get(0 + inPos) >> 1 & 1;
         out[7 + outPos] = in.get(0 + inPos) & 1;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] >> 7 & 1;
         out[1 + outPos] = in[0 + inPos] >> 6 & 1;
         out[2 + outPos] = in[0 + inPos] >> 5 & 1;
         out[3 + outPos] = in[0 + inPos] >> 4 & 1;
         out[4 + outPos] = in[0 + inPos] >> 3 & 1;
         out[5 + outPos] = in[0 + inPos] >> 2 & 1;
         out[6 + outPos] = in[0 + inPos] >> 1 & 1;
         out[7 + outPos] = in[0 + inPos] & 1;
         out[8 + outPos] = in[1 + inPos] >> 7 & 1;
         out[9 + outPos] = in[1 + inPos] >> 6 & 1;
         out[10 + outPos] = in[1 + inPos] >> 5 & 1;
         out[11 + outPos] = in[1 + inPos] >> 4 & 1;
         out[12 + outPos] = in[1 + inPos] >> 3 & 1;
         out[13 + outPos] = in[1 + inPos] >> 2 & 1;
         out[14 + outPos] = in[1 + inPos] >> 1 & 1;
         out[15 + outPos] = in[1 + inPos] & 1;
         out[16 + outPos] = in[2 + inPos] >> 7 & 1;
         out[17 + outPos] = in[2 + inPos] >> 6 & 1;
         out[18 + outPos] = in[2 + inPos] >> 5 & 1;
         out[19 + outPos] = in[2 + inPos] >> 4 & 1;
         out[20 + outPos] = in[2 + inPos] >> 3 & 1;
         out[21 + outPos] = in[2 + inPos] >> 2 & 1;
         out[22 + outPos] = in[2 + inPos] >> 1 & 1;
         out[23 + outPos] = in[2 + inPos] & 1;
         out[24 + outPos] = in[3 + inPos] >> 7 & 1;
         out[25 + outPos] = in[3 + inPos] >> 6 & 1;
         out[26 + outPos] = in[3 + inPos] >> 5 & 1;
         out[27 + outPos] = in[3 + inPos] >> 4 & 1;
         out[28 + outPos] = in[3 + inPos] >> 3 & 1;
         out[29 + outPos] = in[3 + inPos] >> 2 & 1;
         out[30 + outPos] = in[3 + inPos] >> 1 & 1;
         out[31 + outPos] = in[3 + inPos] & 1;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) >> 7 & 1;
         out[1 + outPos] = in.get(0 + inPos) >> 6 & 1;
         out[2 + outPos] = in.get(0 + inPos) >> 5 & 1;
         out[3 + outPos] = in.get(0 + inPos) >> 4 & 1;
         out[4 + outPos] = in.get(0 + inPos) >> 3 & 1;
         out[5 + outPos] = in.get(0 + inPos) >> 2 & 1;
         out[6 + outPos] = in.get(0 + inPos) >> 1 & 1;
         out[7 + outPos] = in.get(0 + inPos) & 1;
         out[8 + outPos] = in.get(1 + inPos) >> 7 & 1;
         out[9 + outPos] = in.get(1 + inPos) >> 6 & 1;
         out[10 + outPos] = in.get(1 + inPos) >> 5 & 1;
         out[11 + outPos] = in.get(1 + inPos) >> 4 & 1;
         out[12 + outPos] = in.get(1 + inPos) >> 3 & 1;
         out[13 + outPos] = in.get(1 + inPos) >> 2 & 1;
         out[14 + outPos] = in.get(1 + inPos) >> 1 & 1;
         out[15 + outPos] = in.get(1 + inPos) & 1;
         out[16 + outPos] = in.get(2 + inPos) >> 7 & 1;
         out[17 + outPos] = in.get(2 + inPos) >> 6 & 1;
         out[18 + outPos] = in.get(2 + inPos) >> 5 & 1;
         out[19 + outPos] = in.get(2 + inPos) >> 4 & 1;
         out[20 + outPos] = in.get(2 + inPos) >> 3 & 1;
         out[21 + outPos] = in.get(2 + inPos) >> 2 & 1;
         out[22 + outPos] = in.get(2 + inPos) >> 1 & 1;
         out[23 + outPos] = in.get(2 + inPos) & 1;
         out[24 + outPos] = in.get(3 + inPos) >> 7 & 1;
         out[25 + outPos] = in.get(3 + inPos) >> 6 & 1;
         out[26 + outPos] = in.get(3 + inPos) >> 5 & 1;
         out[27 + outPos] = in.get(3 + inPos) >> 4 & 1;
         out[28 + outPos] = in.get(3 + inPos) >> 3 & 1;
         out[29 + outPos] = in.get(3 + inPos) >> 2 & 1;
         out[30 + outPos] = in.get(3 + inPos) >> 1 & 1;
         out[31 + outPos] = in.get(3 + inPos) & 1;
      }
   }

   private static final class Packer2 extends BytePacker {
      private Packer2() {
         super(2);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(((in[0 + inPos] & 3) << 6 | (in[1 + inPos] & 3) << 4 | (in[2 + inPos] & 3) << 2 | in[3 + inPos] & 3) & 255);
         out[1 + outPos] = (byte)(((in[4 + inPos] & 3) << 6 | (in[5 + inPos] & 3) << 4 | (in[6 + inPos] & 3) << 2 | in[7 + inPos] & 3) & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(((in[0 + inPos] & 3) << 6 | (in[1 + inPos] & 3) << 4 | (in[2 + inPos] & 3) << 2 | in[3 + inPos] & 3) & 255);
         out[1 + outPos] = (byte)(((in[4 + inPos] & 3) << 6 | (in[5 + inPos] & 3) << 4 | (in[6 + inPos] & 3) << 2 | in[7 + inPos] & 3) & 255);
         out[2 + outPos] = (byte)(((in[8 + inPos] & 3) << 6 | (in[9 + inPos] & 3) << 4 | (in[10 + inPos] & 3) << 2 | in[11 + inPos] & 3) & 255);
         out[3 + outPos] = (byte)(((in[12 + inPos] & 3) << 6 | (in[13 + inPos] & 3) << 4 | (in[14 + inPos] & 3) << 2 | in[15 + inPos] & 3) & 255);
         out[4 + outPos] = (byte)(((in[16 + inPos] & 3) << 6 | (in[17 + inPos] & 3) << 4 | (in[18 + inPos] & 3) << 2 | in[19 + inPos] & 3) & 255);
         out[5 + outPos] = (byte)(((in[20 + inPos] & 3) << 6 | (in[21 + inPos] & 3) << 4 | (in[22 + inPos] & 3) << 2 | in[23 + inPos] & 3) & 255);
         out[6 + outPos] = (byte)(((in[24 + inPos] & 3) << 6 | (in[25 + inPos] & 3) << 4 | (in[26 + inPos] & 3) << 2 | in[27 + inPos] & 3) & 255);
         out[7 + outPos] = (byte)(((in[28 + inPos] & 3) << 6 | (in[29 + inPos] & 3) << 4 | (in[30 + inPos] & 3) << 2 | in[31 + inPos] & 3) & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] >> 6 & 3;
         out[1 + outPos] = in[0 + inPos] >> 4 & 3;
         out[2 + outPos] = in[0 + inPos] >> 2 & 3;
         out[3 + outPos] = in[0 + inPos] & 3;
         out[4 + outPos] = in[1 + inPos] >> 6 & 3;
         out[5 + outPos] = in[1 + inPos] >> 4 & 3;
         out[6 + outPos] = in[1 + inPos] >> 2 & 3;
         out[7 + outPos] = in[1 + inPos] & 3;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) >> 6 & 3;
         out[1 + outPos] = in.get(0 + inPos) >> 4 & 3;
         out[2 + outPos] = in.get(0 + inPos) >> 2 & 3;
         out[3 + outPos] = in.get(0 + inPos) & 3;
         out[4 + outPos] = in.get(1 + inPos) >> 6 & 3;
         out[5 + outPos] = in.get(1 + inPos) >> 4 & 3;
         out[6 + outPos] = in.get(1 + inPos) >> 2 & 3;
         out[7 + outPos] = in.get(1 + inPos) & 3;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] >> 6 & 3;
         out[1 + outPos] = in[0 + inPos] >> 4 & 3;
         out[2 + outPos] = in[0 + inPos] >> 2 & 3;
         out[3 + outPos] = in[0 + inPos] & 3;
         out[4 + outPos] = in[1 + inPos] >> 6 & 3;
         out[5 + outPos] = in[1 + inPos] >> 4 & 3;
         out[6 + outPos] = in[1 + inPos] >> 2 & 3;
         out[7 + outPos] = in[1 + inPos] & 3;
         out[8 + outPos] = in[2 + inPos] >> 6 & 3;
         out[9 + outPos] = in[2 + inPos] >> 4 & 3;
         out[10 + outPos] = in[2 + inPos] >> 2 & 3;
         out[11 + outPos] = in[2 + inPos] & 3;
         out[12 + outPos] = in[3 + inPos] >> 6 & 3;
         out[13 + outPos] = in[3 + inPos] >> 4 & 3;
         out[14 + outPos] = in[3 + inPos] >> 2 & 3;
         out[15 + outPos] = in[3 + inPos] & 3;
         out[16 + outPos] = in[4 + inPos] >> 6 & 3;
         out[17 + outPos] = in[4 + inPos] >> 4 & 3;
         out[18 + outPos] = in[4 + inPos] >> 2 & 3;
         out[19 + outPos] = in[4 + inPos] & 3;
         out[20 + outPos] = in[5 + inPos] >> 6 & 3;
         out[21 + outPos] = in[5 + inPos] >> 4 & 3;
         out[22 + outPos] = in[5 + inPos] >> 2 & 3;
         out[23 + outPos] = in[5 + inPos] & 3;
         out[24 + outPos] = in[6 + inPos] >> 6 & 3;
         out[25 + outPos] = in[6 + inPos] >> 4 & 3;
         out[26 + outPos] = in[6 + inPos] >> 2 & 3;
         out[27 + outPos] = in[6 + inPos] & 3;
         out[28 + outPos] = in[7 + inPos] >> 6 & 3;
         out[29 + outPos] = in[7 + inPos] >> 4 & 3;
         out[30 + outPos] = in[7 + inPos] >> 2 & 3;
         out[31 + outPos] = in[7 + inPos] & 3;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) >> 6 & 3;
         out[1 + outPos] = in.get(0 + inPos) >> 4 & 3;
         out[2 + outPos] = in.get(0 + inPos) >> 2 & 3;
         out[3 + outPos] = in.get(0 + inPos) & 3;
         out[4 + outPos] = in.get(1 + inPos) >> 6 & 3;
         out[5 + outPos] = in.get(1 + inPos) >> 4 & 3;
         out[6 + outPos] = in.get(1 + inPos) >> 2 & 3;
         out[7 + outPos] = in.get(1 + inPos) & 3;
         out[8 + outPos] = in.get(2 + inPos) >> 6 & 3;
         out[9 + outPos] = in.get(2 + inPos) >> 4 & 3;
         out[10 + outPos] = in.get(2 + inPos) >> 2 & 3;
         out[11 + outPos] = in.get(2 + inPos) & 3;
         out[12 + outPos] = in.get(3 + inPos) >> 6 & 3;
         out[13 + outPos] = in.get(3 + inPos) >> 4 & 3;
         out[14 + outPos] = in.get(3 + inPos) >> 2 & 3;
         out[15 + outPos] = in.get(3 + inPos) & 3;
         out[16 + outPos] = in.get(4 + inPos) >> 6 & 3;
         out[17 + outPos] = in.get(4 + inPos) >> 4 & 3;
         out[18 + outPos] = in.get(4 + inPos) >> 2 & 3;
         out[19 + outPos] = in.get(4 + inPos) & 3;
         out[20 + outPos] = in.get(5 + inPos) >> 6 & 3;
         out[21 + outPos] = in.get(5 + inPos) >> 4 & 3;
         out[22 + outPos] = in.get(5 + inPos) >> 2 & 3;
         out[23 + outPos] = in.get(5 + inPos) & 3;
         out[24 + outPos] = in.get(6 + inPos) >> 6 & 3;
         out[25 + outPos] = in.get(6 + inPos) >> 4 & 3;
         out[26 + outPos] = in.get(6 + inPos) >> 2 & 3;
         out[27 + outPos] = in.get(6 + inPos) & 3;
         out[28 + outPos] = in.get(7 + inPos) >> 6 & 3;
         out[29 + outPos] = in.get(7 + inPos) >> 4 & 3;
         out[30 + outPos] = in.get(7 + inPos) >> 2 & 3;
         out[31 + outPos] = in.get(7 + inPos) & 3;
      }
   }

   private static final class Packer3 extends BytePacker {
      private Packer3() {
         super(3);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(((in[0 + inPos] & 7) << 5 | (in[1 + inPos] & 7) << 2 | (in[2 + inPos] & 7) >>> 1) & 255);
         out[1 + outPos] = (byte)(((in[2 + inPos] & 7) << 7 | (in[3 + inPos] & 7) << 4 | (in[4 + inPos] & 7) << 1 | (in[5 + inPos] & 7) >>> 2) & 255);
         out[2 + outPos] = (byte)(((in[5 + inPos] & 7) << 6 | (in[6 + inPos] & 7) << 3 | in[7 + inPos] & 7) & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(((in[0 + inPos] & 7) << 5 | (in[1 + inPos] & 7) << 2 | (in[2 + inPos] & 7) >>> 1) & 255);
         out[1 + outPos] = (byte)(((in[2 + inPos] & 7) << 7 | (in[3 + inPos] & 7) << 4 | (in[4 + inPos] & 7) << 1 | (in[5 + inPos] & 7) >>> 2) & 255);
         out[2 + outPos] = (byte)(((in[5 + inPos] & 7) << 6 | (in[6 + inPos] & 7) << 3 | in[7 + inPos] & 7) & 255);
         out[3 + outPos] = (byte)(((in[8 + inPos] & 7) << 5 | (in[9 + inPos] & 7) << 2 | (in[10 + inPos] & 7) >>> 1) & 255);
         out[4 + outPos] = (byte)(((in[10 + inPos] & 7) << 7 | (in[11 + inPos] & 7) << 4 | (in[12 + inPos] & 7) << 1 | (in[13 + inPos] & 7) >>> 2) & 255);
         out[5 + outPos] = (byte)(((in[13 + inPos] & 7) << 6 | (in[14 + inPos] & 7) << 3 | in[15 + inPos] & 7) & 255);
         out[6 + outPos] = (byte)(((in[16 + inPos] & 7) << 5 | (in[17 + inPos] & 7) << 2 | (in[18 + inPos] & 7) >>> 1) & 255);
         out[7 + outPos] = (byte)(((in[18 + inPos] & 7) << 7 | (in[19 + inPos] & 7) << 4 | (in[20 + inPos] & 7) << 1 | (in[21 + inPos] & 7) >>> 2) & 255);
         out[8 + outPos] = (byte)(((in[21 + inPos] & 7) << 6 | (in[22 + inPos] & 7) << 3 | in[23 + inPos] & 7) & 255);
         out[9 + outPos] = (byte)(((in[24 + inPos] & 7) << 5 | (in[25 + inPos] & 7) << 2 | (in[26 + inPos] & 7) >>> 1) & 255);
         out[10 + outPos] = (byte)(((in[26 + inPos] & 7) << 7 | (in[27 + inPos] & 7) << 4 | (in[28 + inPos] & 7) << 1 | (in[29 + inPos] & 7) >>> 2) & 255);
         out[11 + outPos] = (byte)(((in[29 + inPos] & 7) << 6 | (in[30 + inPos] & 7) << 3 | in[31 + inPos] & 7) & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] >> 5 & 7;
         out[1 + outPos] = in[0 + inPos] >> 2 & 7;
         out[2 + outPos] = in[0 + inPos] << 1 & 7 | in[1 + inPos] >> 7 & 1;
         out[3 + outPos] = in[1 + inPos] >> 4 & 7;
         out[4 + outPos] = in[1 + inPos] >> 1 & 7;
         out[5 + outPos] = in[1 + inPos] << 2 & 7 | in[2 + inPos] >> 6 & 3;
         out[6 + outPos] = in[2 + inPos] >> 3 & 7;
         out[7 + outPos] = in[2 + inPos] & 7;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) >> 5 & 7;
         out[1 + outPos] = in.get(0 + inPos) >> 2 & 7;
         out[2 + outPos] = in.get(0 + inPos) << 1 & 7 | in.get(1 + inPos) >> 7 & 1;
         out[3 + outPos] = in.get(1 + inPos) >> 4 & 7;
         out[4 + outPos] = in.get(1 + inPos) >> 1 & 7;
         out[5 + outPos] = in.get(1 + inPos) << 2 & 7 | in.get(2 + inPos) >> 6 & 3;
         out[6 + outPos] = in.get(2 + inPos) >> 3 & 7;
         out[7 + outPos] = in.get(2 + inPos) & 7;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] >> 5 & 7;
         out[1 + outPos] = in[0 + inPos] >> 2 & 7;
         out[2 + outPos] = in[0 + inPos] << 1 & 7 | in[1 + inPos] >> 7 & 1;
         out[3 + outPos] = in[1 + inPos] >> 4 & 7;
         out[4 + outPos] = in[1 + inPos] >> 1 & 7;
         out[5 + outPos] = in[1 + inPos] << 2 & 7 | in[2 + inPos] >> 6 & 3;
         out[6 + outPos] = in[2 + inPos] >> 3 & 7;
         out[7 + outPos] = in[2 + inPos] & 7;
         out[8 + outPos] = in[3 + inPos] >> 5 & 7;
         out[9 + outPos] = in[3 + inPos] >> 2 & 7;
         out[10 + outPos] = in[3 + inPos] << 1 & 7 | in[4 + inPos] >> 7 & 1;
         out[11 + outPos] = in[4 + inPos] >> 4 & 7;
         out[12 + outPos] = in[4 + inPos] >> 1 & 7;
         out[13 + outPos] = in[4 + inPos] << 2 & 7 | in[5 + inPos] >> 6 & 3;
         out[14 + outPos] = in[5 + inPos] >> 3 & 7;
         out[15 + outPos] = in[5 + inPos] & 7;
         out[16 + outPos] = in[6 + inPos] >> 5 & 7;
         out[17 + outPos] = in[6 + inPos] >> 2 & 7;
         out[18 + outPos] = in[6 + inPos] << 1 & 7 | in[7 + inPos] >> 7 & 1;
         out[19 + outPos] = in[7 + inPos] >> 4 & 7;
         out[20 + outPos] = in[7 + inPos] >> 1 & 7;
         out[21 + outPos] = in[7 + inPos] << 2 & 7 | in[8 + inPos] >> 6 & 3;
         out[22 + outPos] = in[8 + inPos] >> 3 & 7;
         out[23 + outPos] = in[8 + inPos] & 7;
         out[24 + outPos] = in[9 + inPos] >> 5 & 7;
         out[25 + outPos] = in[9 + inPos] >> 2 & 7;
         out[26 + outPos] = in[9 + inPos] << 1 & 7 | in[10 + inPos] >> 7 & 1;
         out[27 + outPos] = in[10 + inPos] >> 4 & 7;
         out[28 + outPos] = in[10 + inPos] >> 1 & 7;
         out[29 + outPos] = in[10 + inPos] << 2 & 7 | in[11 + inPos] >> 6 & 3;
         out[30 + outPos] = in[11 + inPos] >> 3 & 7;
         out[31 + outPos] = in[11 + inPos] & 7;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) >> 5 & 7;
         out[1 + outPos] = in.get(0 + inPos) >> 2 & 7;
         out[2 + outPos] = in.get(0 + inPos) << 1 & 7 | in.get(1 + inPos) >> 7 & 1;
         out[3 + outPos] = in.get(1 + inPos) >> 4 & 7;
         out[4 + outPos] = in.get(1 + inPos) >> 1 & 7;
         out[5 + outPos] = in.get(1 + inPos) << 2 & 7 | in.get(2 + inPos) >> 6 & 3;
         out[6 + outPos] = in.get(2 + inPos) >> 3 & 7;
         out[7 + outPos] = in.get(2 + inPos) & 7;
         out[8 + outPos] = in.get(3 + inPos) >> 5 & 7;
         out[9 + outPos] = in.get(3 + inPos) >> 2 & 7;
         out[10 + outPos] = in.get(3 + inPos) << 1 & 7 | in.get(4 + inPos) >> 7 & 1;
         out[11 + outPos] = in.get(4 + inPos) >> 4 & 7;
         out[12 + outPos] = in.get(4 + inPos) >> 1 & 7;
         out[13 + outPos] = in.get(4 + inPos) << 2 & 7 | in.get(5 + inPos) >> 6 & 3;
         out[14 + outPos] = in.get(5 + inPos) >> 3 & 7;
         out[15 + outPos] = in.get(5 + inPos) & 7;
         out[16 + outPos] = in.get(6 + inPos) >> 5 & 7;
         out[17 + outPos] = in.get(6 + inPos) >> 2 & 7;
         out[18 + outPos] = in.get(6 + inPos) << 1 & 7 | in.get(7 + inPos) >> 7 & 1;
         out[19 + outPos] = in.get(7 + inPos) >> 4 & 7;
         out[20 + outPos] = in.get(7 + inPos) >> 1 & 7;
         out[21 + outPos] = in.get(7 + inPos) << 2 & 7 | in.get(8 + inPos) >> 6 & 3;
         out[22 + outPos] = in.get(8 + inPos) >> 3 & 7;
         out[23 + outPos] = in.get(8 + inPos) & 7;
         out[24 + outPos] = in.get(9 + inPos) >> 5 & 7;
         out[25 + outPos] = in.get(9 + inPos) >> 2 & 7;
         out[26 + outPos] = in.get(9 + inPos) << 1 & 7 | in.get(10 + inPos) >> 7 & 1;
         out[27 + outPos] = in.get(10 + inPos) >> 4 & 7;
         out[28 + outPos] = in.get(10 + inPos) >> 1 & 7;
         out[29 + outPos] = in.get(10 + inPos) << 2 & 7 | in.get(11 + inPos) >> 6 & 3;
         out[30 + outPos] = in.get(11 + inPos) >> 3 & 7;
         out[31 + outPos] = in.get(11 + inPos) & 7;
      }
   }

   private static final class Packer4 extends BytePacker {
      private Packer4() {
         super(4);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(((in[0 + inPos] & 15) << 4 | in[1 + inPos] & 15) & 255);
         out[1 + outPos] = (byte)(((in[2 + inPos] & 15) << 4 | in[3 + inPos] & 15) & 255);
         out[2 + outPos] = (byte)(((in[4 + inPos] & 15) << 4 | in[5 + inPos] & 15) & 255);
         out[3 + outPos] = (byte)(((in[6 + inPos] & 15) << 4 | in[7 + inPos] & 15) & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(((in[0 + inPos] & 15) << 4 | in[1 + inPos] & 15) & 255);
         out[1 + outPos] = (byte)(((in[2 + inPos] & 15) << 4 | in[3 + inPos] & 15) & 255);
         out[2 + outPos] = (byte)(((in[4 + inPos] & 15) << 4 | in[5 + inPos] & 15) & 255);
         out[3 + outPos] = (byte)(((in[6 + inPos] & 15) << 4 | in[7 + inPos] & 15) & 255);
         out[4 + outPos] = (byte)(((in[8 + inPos] & 15) << 4 | in[9 + inPos] & 15) & 255);
         out[5 + outPos] = (byte)(((in[10 + inPos] & 15) << 4 | in[11 + inPos] & 15) & 255);
         out[6 + outPos] = (byte)(((in[12 + inPos] & 15) << 4 | in[13 + inPos] & 15) & 255);
         out[7 + outPos] = (byte)(((in[14 + inPos] & 15) << 4 | in[15 + inPos] & 15) & 255);
         out[8 + outPos] = (byte)(((in[16 + inPos] & 15) << 4 | in[17 + inPos] & 15) & 255);
         out[9 + outPos] = (byte)(((in[18 + inPos] & 15) << 4 | in[19 + inPos] & 15) & 255);
         out[10 + outPos] = (byte)(((in[20 + inPos] & 15) << 4 | in[21 + inPos] & 15) & 255);
         out[11 + outPos] = (byte)(((in[22 + inPos] & 15) << 4 | in[23 + inPos] & 15) & 255);
         out[12 + outPos] = (byte)(((in[24 + inPos] & 15) << 4 | in[25 + inPos] & 15) & 255);
         out[13 + outPos] = (byte)(((in[26 + inPos] & 15) << 4 | in[27 + inPos] & 15) & 255);
         out[14 + outPos] = (byte)(((in[28 + inPos] & 15) << 4 | in[29 + inPos] & 15) & 255);
         out[15 + outPos] = (byte)(((in[30 + inPos] & 15) << 4 | in[31 + inPos] & 15) & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] >> 4 & 15;
         out[1 + outPos] = in[0 + inPos] & 15;
         out[2 + outPos] = in[1 + inPos] >> 4 & 15;
         out[3 + outPos] = in[1 + inPos] & 15;
         out[4 + outPos] = in[2 + inPos] >> 4 & 15;
         out[5 + outPos] = in[2 + inPos] & 15;
         out[6 + outPos] = in[3 + inPos] >> 4 & 15;
         out[7 + outPos] = in[3 + inPos] & 15;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) >> 4 & 15;
         out[1 + outPos] = in.get(0 + inPos) & 15;
         out[2 + outPos] = in.get(1 + inPos) >> 4 & 15;
         out[3 + outPos] = in.get(1 + inPos) & 15;
         out[4 + outPos] = in.get(2 + inPos) >> 4 & 15;
         out[5 + outPos] = in.get(2 + inPos) & 15;
         out[6 + outPos] = in.get(3 + inPos) >> 4 & 15;
         out[7 + outPos] = in.get(3 + inPos) & 15;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] >> 4 & 15;
         out[1 + outPos] = in[0 + inPos] & 15;
         out[2 + outPos] = in[1 + inPos] >> 4 & 15;
         out[3 + outPos] = in[1 + inPos] & 15;
         out[4 + outPos] = in[2 + inPos] >> 4 & 15;
         out[5 + outPos] = in[2 + inPos] & 15;
         out[6 + outPos] = in[3 + inPos] >> 4 & 15;
         out[7 + outPos] = in[3 + inPos] & 15;
         out[8 + outPos] = in[4 + inPos] >> 4 & 15;
         out[9 + outPos] = in[4 + inPos] & 15;
         out[10 + outPos] = in[5 + inPos] >> 4 & 15;
         out[11 + outPos] = in[5 + inPos] & 15;
         out[12 + outPos] = in[6 + inPos] >> 4 & 15;
         out[13 + outPos] = in[6 + inPos] & 15;
         out[14 + outPos] = in[7 + inPos] >> 4 & 15;
         out[15 + outPos] = in[7 + inPos] & 15;
         out[16 + outPos] = in[8 + inPos] >> 4 & 15;
         out[17 + outPos] = in[8 + inPos] & 15;
         out[18 + outPos] = in[9 + inPos] >> 4 & 15;
         out[19 + outPos] = in[9 + inPos] & 15;
         out[20 + outPos] = in[10 + inPos] >> 4 & 15;
         out[21 + outPos] = in[10 + inPos] & 15;
         out[22 + outPos] = in[11 + inPos] >> 4 & 15;
         out[23 + outPos] = in[11 + inPos] & 15;
         out[24 + outPos] = in[12 + inPos] >> 4 & 15;
         out[25 + outPos] = in[12 + inPos] & 15;
         out[26 + outPos] = in[13 + inPos] >> 4 & 15;
         out[27 + outPos] = in[13 + inPos] & 15;
         out[28 + outPos] = in[14 + inPos] >> 4 & 15;
         out[29 + outPos] = in[14 + inPos] & 15;
         out[30 + outPos] = in[15 + inPos] >> 4 & 15;
         out[31 + outPos] = in[15 + inPos] & 15;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) >> 4 & 15;
         out[1 + outPos] = in.get(0 + inPos) & 15;
         out[2 + outPos] = in.get(1 + inPos) >> 4 & 15;
         out[3 + outPos] = in.get(1 + inPos) & 15;
         out[4 + outPos] = in.get(2 + inPos) >> 4 & 15;
         out[5 + outPos] = in.get(2 + inPos) & 15;
         out[6 + outPos] = in.get(3 + inPos) >> 4 & 15;
         out[7 + outPos] = in.get(3 + inPos) & 15;
         out[8 + outPos] = in.get(4 + inPos) >> 4 & 15;
         out[9 + outPos] = in.get(4 + inPos) & 15;
         out[10 + outPos] = in.get(5 + inPos) >> 4 & 15;
         out[11 + outPos] = in.get(5 + inPos) & 15;
         out[12 + outPos] = in.get(6 + inPos) >> 4 & 15;
         out[13 + outPos] = in.get(6 + inPos) & 15;
         out[14 + outPos] = in.get(7 + inPos) >> 4 & 15;
         out[15 + outPos] = in.get(7 + inPos) & 15;
         out[16 + outPos] = in.get(8 + inPos) >> 4 & 15;
         out[17 + outPos] = in.get(8 + inPos) & 15;
         out[18 + outPos] = in.get(9 + inPos) >> 4 & 15;
         out[19 + outPos] = in.get(9 + inPos) & 15;
         out[20 + outPos] = in.get(10 + inPos) >> 4 & 15;
         out[21 + outPos] = in.get(10 + inPos) & 15;
         out[22 + outPos] = in.get(11 + inPos) >> 4 & 15;
         out[23 + outPos] = in.get(11 + inPos) & 15;
         out[24 + outPos] = in.get(12 + inPos) >> 4 & 15;
         out[25 + outPos] = in.get(12 + inPos) & 15;
         out[26 + outPos] = in.get(13 + inPos) >> 4 & 15;
         out[27 + outPos] = in.get(13 + inPos) & 15;
         out[28 + outPos] = in.get(14 + inPos) >> 4 & 15;
         out[29 + outPos] = in.get(14 + inPos) & 15;
         out[30 + outPos] = in.get(15 + inPos) >> 4 & 15;
         out[31 + outPos] = in.get(15 + inPos) & 15;
      }
   }

   private static final class Packer5 extends BytePacker {
      private Packer5() {
         super(5);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(((in[0 + inPos] & 31) << 3 | (in[1 + inPos] & 31) >>> 2) & 255);
         out[1 + outPos] = (byte)(((in[1 + inPos] & 31) << 6 | (in[2 + inPos] & 31) << 1 | (in[3 + inPos] & 31) >>> 4) & 255);
         out[2 + outPos] = (byte)(((in[3 + inPos] & 31) << 4 | (in[4 + inPos] & 31) >>> 1) & 255);
         out[3 + outPos] = (byte)(((in[4 + inPos] & 31) << 7 | (in[5 + inPos] & 31) << 2 | (in[6 + inPos] & 31) >>> 3) & 255);
         out[4 + outPos] = (byte)(((in[6 + inPos] & 31) << 5 | in[7 + inPos] & 31) & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(((in[0 + inPos] & 31) << 3 | (in[1 + inPos] & 31) >>> 2) & 255);
         out[1 + outPos] = (byte)(((in[1 + inPos] & 31) << 6 | (in[2 + inPos] & 31) << 1 | (in[3 + inPos] & 31) >>> 4) & 255);
         out[2 + outPos] = (byte)(((in[3 + inPos] & 31) << 4 | (in[4 + inPos] & 31) >>> 1) & 255);
         out[3 + outPos] = (byte)(((in[4 + inPos] & 31) << 7 | (in[5 + inPos] & 31) << 2 | (in[6 + inPos] & 31) >>> 3) & 255);
         out[4 + outPos] = (byte)(((in[6 + inPos] & 31) << 5 | in[7 + inPos] & 31) & 255);
         out[5 + outPos] = (byte)(((in[8 + inPos] & 31) << 3 | (in[9 + inPos] & 31) >>> 2) & 255);
         out[6 + outPos] = (byte)(((in[9 + inPos] & 31) << 6 | (in[10 + inPos] & 31) << 1 | (in[11 + inPos] & 31) >>> 4) & 255);
         out[7 + outPos] = (byte)(((in[11 + inPos] & 31) << 4 | (in[12 + inPos] & 31) >>> 1) & 255);
         out[8 + outPos] = (byte)(((in[12 + inPos] & 31) << 7 | (in[13 + inPos] & 31) << 2 | (in[14 + inPos] & 31) >>> 3) & 255);
         out[9 + outPos] = (byte)(((in[14 + inPos] & 31) << 5 | in[15 + inPos] & 31) & 255);
         out[10 + outPos] = (byte)(((in[16 + inPos] & 31) << 3 | (in[17 + inPos] & 31) >>> 2) & 255);
         out[11 + outPos] = (byte)(((in[17 + inPos] & 31) << 6 | (in[18 + inPos] & 31) << 1 | (in[19 + inPos] & 31) >>> 4) & 255);
         out[12 + outPos] = (byte)(((in[19 + inPos] & 31) << 4 | (in[20 + inPos] & 31) >>> 1) & 255);
         out[13 + outPos] = (byte)(((in[20 + inPos] & 31) << 7 | (in[21 + inPos] & 31) << 2 | (in[22 + inPos] & 31) >>> 3) & 255);
         out[14 + outPos] = (byte)(((in[22 + inPos] & 31) << 5 | in[23 + inPos] & 31) & 255);
         out[15 + outPos] = (byte)(((in[24 + inPos] & 31) << 3 | (in[25 + inPos] & 31) >>> 2) & 255);
         out[16 + outPos] = (byte)(((in[25 + inPos] & 31) << 6 | (in[26 + inPos] & 31) << 1 | (in[27 + inPos] & 31) >>> 4) & 255);
         out[17 + outPos] = (byte)(((in[27 + inPos] & 31) << 4 | (in[28 + inPos] & 31) >>> 1) & 255);
         out[18 + outPos] = (byte)(((in[28 + inPos] & 31) << 7 | (in[29 + inPos] & 31) << 2 | (in[30 + inPos] & 31) >>> 3) & 255);
         out[19 + outPos] = (byte)(((in[30 + inPos] & 31) << 5 | in[31 + inPos] & 31) & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] >> 3 & 31;
         out[1 + outPos] = in[0 + inPos] << 2 & 31 | in[1 + inPos] >> 6 & 3;
         out[2 + outPos] = in[1 + inPos] >> 1 & 31;
         out[3 + outPos] = in[1 + inPos] << 4 & 31 | in[2 + inPos] >> 4 & 15;
         out[4 + outPos] = in[2 + inPos] << 1 & 31 | in[3 + inPos] >> 7 & 1;
         out[5 + outPos] = in[3 + inPos] >> 2 & 31;
         out[6 + outPos] = in[3 + inPos] << 3 & 31 | in[4 + inPos] >> 5 & 7;
         out[7 + outPos] = in[4 + inPos] & 31;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) >> 3 & 31;
         out[1 + outPos] = in.get(0 + inPos) << 2 & 31 | in.get(1 + inPos) >> 6 & 3;
         out[2 + outPos] = in.get(1 + inPos) >> 1 & 31;
         out[3 + outPos] = in.get(1 + inPos) << 4 & 31 | in.get(2 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(2 + inPos) << 1 & 31 | in.get(3 + inPos) >> 7 & 1;
         out[5 + outPos] = in.get(3 + inPos) >> 2 & 31;
         out[6 + outPos] = in.get(3 + inPos) << 3 & 31 | in.get(4 + inPos) >> 5 & 7;
         out[7 + outPos] = in.get(4 + inPos) & 31;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] >> 3 & 31;
         out[1 + outPos] = in[0 + inPos] << 2 & 31 | in[1 + inPos] >> 6 & 3;
         out[2 + outPos] = in[1 + inPos] >> 1 & 31;
         out[3 + outPos] = in[1 + inPos] << 4 & 31 | in[2 + inPos] >> 4 & 15;
         out[4 + outPos] = in[2 + inPos] << 1 & 31 | in[3 + inPos] >> 7 & 1;
         out[5 + outPos] = in[3 + inPos] >> 2 & 31;
         out[6 + outPos] = in[3 + inPos] << 3 & 31 | in[4 + inPos] >> 5 & 7;
         out[7 + outPos] = in[4 + inPos] & 31;
         out[8 + outPos] = in[5 + inPos] >> 3 & 31;
         out[9 + outPos] = in[5 + inPos] << 2 & 31 | in[6 + inPos] >> 6 & 3;
         out[10 + outPos] = in[6 + inPos] >> 1 & 31;
         out[11 + outPos] = in[6 + inPos] << 4 & 31 | in[7 + inPos] >> 4 & 15;
         out[12 + outPos] = in[7 + inPos] << 1 & 31 | in[8 + inPos] >> 7 & 1;
         out[13 + outPos] = in[8 + inPos] >> 2 & 31;
         out[14 + outPos] = in[8 + inPos] << 3 & 31 | in[9 + inPos] >> 5 & 7;
         out[15 + outPos] = in[9 + inPos] & 31;
         out[16 + outPos] = in[10 + inPos] >> 3 & 31;
         out[17 + outPos] = in[10 + inPos] << 2 & 31 | in[11 + inPos] >> 6 & 3;
         out[18 + outPos] = in[11 + inPos] >> 1 & 31;
         out[19 + outPos] = in[11 + inPos] << 4 & 31 | in[12 + inPos] >> 4 & 15;
         out[20 + outPos] = in[12 + inPos] << 1 & 31 | in[13 + inPos] >> 7 & 1;
         out[21 + outPos] = in[13 + inPos] >> 2 & 31;
         out[22 + outPos] = in[13 + inPos] << 3 & 31 | in[14 + inPos] >> 5 & 7;
         out[23 + outPos] = in[14 + inPos] & 31;
         out[24 + outPos] = in[15 + inPos] >> 3 & 31;
         out[25 + outPos] = in[15 + inPos] << 2 & 31 | in[16 + inPos] >> 6 & 3;
         out[26 + outPos] = in[16 + inPos] >> 1 & 31;
         out[27 + outPos] = in[16 + inPos] << 4 & 31 | in[17 + inPos] >> 4 & 15;
         out[28 + outPos] = in[17 + inPos] << 1 & 31 | in[18 + inPos] >> 7 & 1;
         out[29 + outPos] = in[18 + inPos] >> 2 & 31;
         out[30 + outPos] = in[18 + inPos] << 3 & 31 | in[19 + inPos] >> 5 & 7;
         out[31 + outPos] = in[19 + inPos] & 31;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) >> 3 & 31;
         out[1 + outPos] = in.get(0 + inPos) << 2 & 31 | in.get(1 + inPos) >> 6 & 3;
         out[2 + outPos] = in.get(1 + inPos) >> 1 & 31;
         out[3 + outPos] = in.get(1 + inPos) << 4 & 31 | in.get(2 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(2 + inPos) << 1 & 31 | in.get(3 + inPos) >> 7 & 1;
         out[5 + outPos] = in.get(3 + inPos) >> 2 & 31;
         out[6 + outPos] = in.get(3 + inPos) << 3 & 31 | in.get(4 + inPos) >> 5 & 7;
         out[7 + outPos] = in.get(4 + inPos) & 31;
         out[8 + outPos] = in.get(5 + inPos) >> 3 & 31;
         out[9 + outPos] = in.get(5 + inPos) << 2 & 31 | in.get(6 + inPos) >> 6 & 3;
         out[10 + outPos] = in.get(6 + inPos) >> 1 & 31;
         out[11 + outPos] = in.get(6 + inPos) << 4 & 31 | in.get(7 + inPos) >> 4 & 15;
         out[12 + outPos] = in.get(7 + inPos) << 1 & 31 | in.get(8 + inPos) >> 7 & 1;
         out[13 + outPos] = in.get(8 + inPos) >> 2 & 31;
         out[14 + outPos] = in.get(8 + inPos) << 3 & 31 | in.get(9 + inPos) >> 5 & 7;
         out[15 + outPos] = in.get(9 + inPos) & 31;
         out[16 + outPos] = in.get(10 + inPos) >> 3 & 31;
         out[17 + outPos] = in.get(10 + inPos) << 2 & 31 | in.get(11 + inPos) >> 6 & 3;
         out[18 + outPos] = in.get(11 + inPos) >> 1 & 31;
         out[19 + outPos] = in.get(11 + inPos) << 4 & 31 | in.get(12 + inPos) >> 4 & 15;
         out[20 + outPos] = in.get(12 + inPos) << 1 & 31 | in.get(13 + inPos) >> 7 & 1;
         out[21 + outPos] = in.get(13 + inPos) >> 2 & 31;
         out[22 + outPos] = in.get(13 + inPos) << 3 & 31 | in.get(14 + inPos) >> 5 & 7;
         out[23 + outPos] = in.get(14 + inPos) & 31;
         out[24 + outPos] = in.get(15 + inPos) >> 3 & 31;
         out[25 + outPos] = in.get(15 + inPos) << 2 & 31 | in.get(16 + inPos) >> 6 & 3;
         out[26 + outPos] = in.get(16 + inPos) >> 1 & 31;
         out[27 + outPos] = in.get(16 + inPos) << 4 & 31 | in.get(17 + inPos) >> 4 & 15;
         out[28 + outPos] = in.get(17 + inPos) << 1 & 31 | in.get(18 + inPos) >> 7 & 1;
         out[29 + outPos] = in.get(18 + inPos) >> 2 & 31;
         out[30 + outPos] = in.get(18 + inPos) << 3 & 31 | in.get(19 + inPos) >> 5 & 7;
         out[31 + outPos] = in.get(19 + inPos) & 31;
      }
   }

   private static final class Packer6 extends BytePacker {
      private Packer6() {
         super(6);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(((in[0 + inPos] & 63) << 2 | (in[1 + inPos] & 63) >>> 4) & 255);
         out[1 + outPos] = (byte)(((in[1 + inPos] & 63) << 4 | (in[2 + inPos] & 63) >>> 2) & 255);
         out[2 + outPos] = (byte)(((in[2 + inPos] & 63) << 6 | in[3 + inPos] & 63) & 255);
         out[3 + outPos] = (byte)(((in[4 + inPos] & 63) << 2 | (in[5 + inPos] & 63) >>> 4) & 255);
         out[4 + outPos] = (byte)(((in[5 + inPos] & 63) << 4 | (in[6 + inPos] & 63) >>> 2) & 255);
         out[5 + outPos] = (byte)(((in[6 + inPos] & 63) << 6 | in[7 + inPos] & 63) & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(((in[0 + inPos] & 63) << 2 | (in[1 + inPos] & 63) >>> 4) & 255);
         out[1 + outPos] = (byte)(((in[1 + inPos] & 63) << 4 | (in[2 + inPos] & 63) >>> 2) & 255);
         out[2 + outPos] = (byte)(((in[2 + inPos] & 63) << 6 | in[3 + inPos] & 63) & 255);
         out[3 + outPos] = (byte)(((in[4 + inPos] & 63) << 2 | (in[5 + inPos] & 63) >>> 4) & 255);
         out[4 + outPos] = (byte)(((in[5 + inPos] & 63) << 4 | (in[6 + inPos] & 63) >>> 2) & 255);
         out[5 + outPos] = (byte)(((in[6 + inPos] & 63) << 6 | in[7 + inPos] & 63) & 255);
         out[6 + outPos] = (byte)(((in[8 + inPos] & 63) << 2 | (in[9 + inPos] & 63) >>> 4) & 255);
         out[7 + outPos] = (byte)(((in[9 + inPos] & 63) << 4 | (in[10 + inPos] & 63) >>> 2) & 255);
         out[8 + outPos] = (byte)(((in[10 + inPos] & 63) << 6 | in[11 + inPos] & 63) & 255);
         out[9 + outPos] = (byte)(((in[12 + inPos] & 63) << 2 | (in[13 + inPos] & 63) >>> 4) & 255);
         out[10 + outPos] = (byte)(((in[13 + inPos] & 63) << 4 | (in[14 + inPos] & 63) >>> 2) & 255);
         out[11 + outPos] = (byte)(((in[14 + inPos] & 63) << 6 | in[15 + inPos] & 63) & 255);
         out[12 + outPos] = (byte)(((in[16 + inPos] & 63) << 2 | (in[17 + inPos] & 63) >>> 4) & 255);
         out[13 + outPos] = (byte)(((in[17 + inPos] & 63) << 4 | (in[18 + inPos] & 63) >>> 2) & 255);
         out[14 + outPos] = (byte)(((in[18 + inPos] & 63) << 6 | in[19 + inPos] & 63) & 255);
         out[15 + outPos] = (byte)(((in[20 + inPos] & 63) << 2 | (in[21 + inPos] & 63) >>> 4) & 255);
         out[16 + outPos] = (byte)(((in[21 + inPos] & 63) << 4 | (in[22 + inPos] & 63) >>> 2) & 255);
         out[17 + outPos] = (byte)(((in[22 + inPos] & 63) << 6 | in[23 + inPos] & 63) & 255);
         out[18 + outPos] = (byte)(((in[24 + inPos] & 63) << 2 | (in[25 + inPos] & 63) >>> 4) & 255);
         out[19 + outPos] = (byte)(((in[25 + inPos] & 63) << 4 | (in[26 + inPos] & 63) >>> 2) & 255);
         out[20 + outPos] = (byte)(((in[26 + inPos] & 63) << 6 | in[27 + inPos] & 63) & 255);
         out[21 + outPos] = (byte)(((in[28 + inPos] & 63) << 2 | (in[29 + inPos] & 63) >>> 4) & 255);
         out[22 + outPos] = (byte)(((in[29 + inPos] & 63) << 4 | (in[30 + inPos] & 63) >>> 2) & 255);
         out[23 + outPos] = (byte)(((in[30 + inPos] & 63) << 6 | in[31 + inPos] & 63) & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] >> 2 & 63;
         out[1 + outPos] = in[0 + inPos] << 4 & 63 | in[1 + inPos] >> 4 & 15;
         out[2 + outPos] = in[1 + inPos] << 2 & 63 | in[2 + inPos] >> 6 & 3;
         out[3 + outPos] = in[2 + inPos] & 63;
         out[4 + outPos] = in[3 + inPos] >> 2 & 63;
         out[5 + outPos] = in[3 + inPos] << 4 & 63 | in[4 + inPos] >> 4 & 15;
         out[6 + outPos] = in[4 + inPos] << 2 & 63 | in[5 + inPos] >> 6 & 3;
         out[7 + outPos] = in[5 + inPos] & 63;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) >> 2 & 63;
         out[1 + outPos] = in.get(0 + inPos) << 4 & 63 | in.get(1 + inPos) >> 4 & 15;
         out[2 + outPos] = in.get(1 + inPos) << 2 & 63 | in.get(2 + inPos) >> 6 & 3;
         out[3 + outPos] = in.get(2 + inPos) & 63;
         out[4 + outPos] = in.get(3 + inPos) >> 2 & 63;
         out[5 + outPos] = in.get(3 + inPos) << 4 & 63 | in.get(4 + inPos) >> 4 & 15;
         out[6 + outPos] = in.get(4 + inPos) << 2 & 63 | in.get(5 + inPos) >> 6 & 3;
         out[7 + outPos] = in.get(5 + inPos) & 63;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] >> 2 & 63;
         out[1 + outPos] = in[0 + inPos] << 4 & 63 | in[1 + inPos] >> 4 & 15;
         out[2 + outPos] = in[1 + inPos] << 2 & 63 | in[2 + inPos] >> 6 & 3;
         out[3 + outPos] = in[2 + inPos] & 63;
         out[4 + outPos] = in[3 + inPos] >> 2 & 63;
         out[5 + outPos] = in[3 + inPos] << 4 & 63 | in[4 + inPos] >> 4 & 15;
         out[6 + outPos] = in[4 + inPos] << 2 & 63 | in[5 + inPos] >> 6 & 3;
         out[7 + outPos] = in[5 + inPos] & 63;
         out[8 + outPos] = in[6 + inPos] >> 2 & 63;
         out[9 + outPos] = in[6 + inPos] << 4 & 63 | in[7 + inPos] >> 4 & 15;
         out[10 + outPos] = in[7 + inPos] << 2 & 63 | in[8 + inPos] >> 6 & 3;
         out[11 + outPos] = in[8 + inPos] & 63;
         out[12 + outPos] = in[9 + inPos] >> 2 & 63;
         out[13 + outPos] = in[9 + inPos] << 4 & 63 | in[10 + inPos] >> 4 & 15;
         out[14 + outPos] = in[10 + inPos] << 2 & 63 | in[11 + inPos] >> 6 & 3;
         out[15 + outPos] = in[11 + inPos] & 63;
         out[16 + outPos] = in[12 + inPos] >> 2 & 63;
         out[17 + outPos] = in[12 + inPos] << 4 & 63 | in[13 + inPos] >> 4 & 15;
         out[18 + outPos] = in[13 + inPos] << 2 & 63 | in[14 + inPos] >> 6 & 3;
         out[19 + outPos] = in[14 + inPos] & 63;
         out[20 + outPos] = in[15 + inPos] >> 2 & 63;
         out[21 + outPos] = in[15 + inPos] << 4 & 63 | in[16 + inPos] >> 4 & 15;
         out[22 + outPos] = in[16 + inPos] << 2 & 63 | in[17 + inPos] >> 6 & 3;
         out[23 + outPos] = in[17 + inPos] & 63;
         out[24 + outPos] = in[18 + inPos] >> 2 & 63;
         out[25 + outPos] = in[18 + inPos] << 4 & 63 | in[19 + inPos] >> 4 & 15;
         out[26 + outPos] = in[19 + inPos] << 2 & 63 | in[20 + inPos] >> 6 & 3;
         out[27 + outPos] = in[20 + inPos] & 63;
         out[28 + outPos] = in[21 + inPos] >> 2 & 63;
         out[29 + outPos] = in[21 + inPos] << 4 & 63 | in[22 + inPos] >> 4 & 15;
         out[30 + outPos] = in[22 + inPos] << 2 & 63 | in[23 + inPos] >> 6 & 3;
         out[31 + outPos] = in[23 + inPos] & 63;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) >> 2 & 63;
         out[1 + outPos] = in.get(0 + inPos) << 4 & 63 | in.get(1 + inPos) >> 4 & 15;
         out[2 + outPos] = in.get(1 + inPos) << 2 & 63 | in.get(2 + inPos) >> 6 & 3;
         out[3 + outPos] = in.get(2 + inPos) & 63;
         out[4 + outPos] = in.get(3 + inPos) >> 2 & 63;
         out[5 + outPos] = in.get(3 + inPos) << 4 & 63 | in.get(4 + inPos) >> 4 & 15;
         out[6 + outPos] = in.get(4 + inPos) << 2 & 63 | in.get(5 + inPos) >> 6 & 3;
         out[7 + outPos] = in.get(5 + inPos) & 63;
         out[8 + outPos] = in.get(6 + inPos) >> 2 & 63;
         out[9 + outPos] = in.get(6 + inPos) << 4 & 63 | in.get(7 + inPos) >> 4 & 15;
         out[10 + outPos] = in.get(7 + inPos) << 2 & 63 | in.get(8 + inPos) >> 6 & 3;
         out[11 + outPos] = in.get(8 + inPos) & 63;
         out[12 + outPos] = in.get(9 + inPos) >> 2 & 63;
         out[13 + outPos] = in.get(9 + inPos) << 4 & 63 | in.get(10 + inPos) >> 4 & 15;
         out[14 + outPos] = in.get(10 + inPos) << 2 & 63 | in.get(11 + inPos) >> 6 & 3;
         out[15 + outPos] = in.get(11 + inPos) & 63;
         out[16 + outPos] = in.get(12 + inPos) >> 2 & 63;
         out[17 + outPos] = in.get(12 + inPos) << 4 & 63 | in.get(13 + inPos) >> 4 & 15;
         out[18 + outPos] = in.get(13 + inPos) << 2 & 63 | in.get(14 + inPos) >> 6 & 3;
         out[19 + outPos] = in.get(14 + inPos) & 63;
         out[20 + outPos] = in.get(15 + inPos) >> 2 & 63;
         out[21 + outPos] = in.get(15 + inPos) << 4 & 63 | in.get(16 + inPos) >> 4 & 15;
         out[22 + outPos] = in.get(16 + inPos) << 2 & 63 | in.get(17 + inPos) >> 6 & 3;
         out[23 + outPos] = in.get(17 + inPos) & 63;
         out[24 + outPos] = in.get(18 + inPos) >> 2 & 63;
         out[25 + outPos] = in.get(18 + inPos) << 4 & 63 | in.get(19 + inPos) >> 4 & 15;
         out[26 + outPos] = in.get(19 + inPos) << 2 & 63 | in.get(20 + inPos) >> 6 & 3;
         out[27 + outPos] = in.get(20 + inPos) & 63;
         out[28 + outPos] = in.get(21 + inPos) >> 2 & 63;
         out[29 + outPos] = in.get(21 + inPos) << 4 & 63 | in.get(22 + inPos) >> 4 & 15;
         out[30 + outPos] = in.get(22 + inPos) << 2 & 63 | in.get(23 + inPos) >> 6 & 3;
         out[31 + outPos] = in.get(23 + inPos) & 63;
      }
   }

   private static final class Packer7 extends BytePacker {
      private Packer7() {
         super(7);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(((in[0 + inPos] & 127) << 1 | (in[1 + inPos] & 127) >>> 6) & 255);
         out[1 + outPos] = (byte)(((in[1 + inPos] & 127) << 2 | (in[2 + inPos] & 127) >>> 5) & 255);
         out[2 + outPos] = (byte)(((in[2 + inPos] & 127) << 3 | (in[3 + inPos] & 127) >>> 4) & 255);
         out[3 + outPos] = (byte)(((in[3 + inPos] & 127) << 4 | (in[4 + inPos] & 127) >>> 3) & 255);
         out[4 + outPos] = (byte)(((in[4 + inPos] & 127) << 5 | (in[5 + inPos] & 127) >>> 2) & 255);
         out[5 + outPos] = (byte)(((in[5 + inPos] & 127) << 6 | (in[6 + inPos] & 127) >>> 1) & 255);
         out[6 + outPos] = (byte)(((in[6 + inPos] & 127) << 7 | in[7 + inPos] & 127) & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)(((in[0 + inPos] & 127) << 1 | (in[1 + inPos] & 127) >>> 6) & 255);
         out[1 + outPos] = (byte)(((in[1 + inPos] & 127) << 2 | (in[2 + inPos] & 127) >>> 5) & 255);
         out[2 + outPos] = (byte)(((in[2 + inPos] & 127) << 3 | (in[3 + inPos] & 127) >>> 4) & 255);
         out[3 + outPos] = (byte)(((in[3 + inPos] & 127) << 4 | (in[4 + inPos] & 127) >>> 3) & 255);
         out[4 + outPos] = (byte)(((in[4 + inPos] & 127) << 5 | (in[5 + inPos] & 127) >>> 2) & 255);
         out[5 + outPos] = (byte)(((in[5 + inPos] & 127) << 6 | (in[6 + inPos] & 127) >>> 1) & 255);
         out[6 + outPos] = (byte)(((in[6 + inPos] & 127) << 7 | in[7 + inPos] & 127) & 255);
         out[7 + outPos] = (byte)(((in[8 + inPos] & 127) << 1 | (in[9 + inPos] & 127) >>> 6) & 255);
         out[8 + outPos] = (byte)(((in[9 + inPos] & 127) << 2 | (in[10 + inPos] & 127) >>> 5) & 255);
         out[9 + outPos] = (byte)(((in[10 + inPos] & 127) << 3 | (in[11 + inPos] & 127) >>> 4) & 255);
         out[10 + outPos] = (byte)(((in[11 + inPos] & 127) << 4 | (in[12 + inPos] & 127) >>> 3) & 255);
         out[11 + outPos] = (byte)(((in[12 + inPos] & 127) << 5 | (in[13 + inPos] & 127) >>> 2) & 255);
         out[12 + outPos] = (byte)(((in[13 + inPos] & 127) << 6 | (in[14 + inPos] & 127) >>> 1) & 255);
         out[13 + outPos] = (byte)(((in[14 + inPos] & 127) << 7 | in[15 + inPos] & 127) & 255);
         out[14 + outPos] = (byte)(((in[16 + inPos] & 127) << 1 | (in[17 + inPos] & 127) >>> 6) & 255);
         out[15 + outPos] = (byte)(((in[17 + inPos] & 127) << 2 | (in[18 + inPos] & 127) >>> 5) & 255);
         out[16 + outPos] = (byte)(((in[18 + inPos] & 127) << 3 | (in[19 + inPos] & 127) >>> 4) & 255);
         out[17 + outPos] = (byte)(((in[19 + inPos] & 127) << 4 | (in[20 + inPos] & 127) >>> 3) & 255);
         out[18 + outPos] = (byte)(((in[20 + inPos] & 127) << 5 | (in[21 + inPos] & 127) >>> 2) & 255);
         out[19 + outPos] = (byte)(((in[21 + inPos] & 127) << 6 | (in[22 + inPos] & 127) >>> 1) & 255);
         out[20 + outPos] = (byte)(((in[22 + inPos] & 127) << 7 | in[23 + inPos] & 127) & 255);
         out[21 + outPos] = (byte)(((in[24 + inPos] & 127) << 1 | (in[25 + inPos] & 127) >>> 6) & 255);
         out[22 + outPos] = (byte)(((in[25 + inPos] & 127) << 2 | (in[26 + inPos] & 127) >>> 5) & 255);
         out[23 + outPos] = (byte)(((in[26 + inPos] & 127) << 3 | (in[27 + inPos] & 127) >>> 4) & 255);
         out[24 + outPos] = (byte)(((in[27 + inPos] & 127) << 4 | (in[28 + inPos] & 127) >>> 3) & 255);
         out[25 + outPos] = (byte)(((in[28 + inPos] & 127) << 5 | (in[29 + inPos] & 127) >>> 2) & 255);
         out[26 + outPos] = (byte)(((in[29 + inPos] & 127) << 6 | (in[30 + inPos] & 127) >>> 1) & 255);
         out[27 + outPos] = (byte)(((in[30 + inPos] & 127) << 7 | in[31 + inPos] & 127) & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] >> 1 & 127;
         out[1 + outPos] = in[0 + inPos] << 6 & 127 | in[1 + inPos] >> 2 & 63;
         out[2 + outPos] = in[1 + inPos] << 5 & 127 | in[2 + inPos] >> 3 & 31;
         out[3 + outPos] = in[2 + inPos] << 4 & 127 | in[3 + inPos] >> 4 & 15;
         out[4 + outPos] = in[3 + inPos] << 3 & 127 | in[4 + inPos] >> 5 & 7;
         out[5 + outPos] = in[4 + inPos] << 2 & 127 | in[5 + inPos] >> 6 & 3;
         out[6 + outPos] = in[5 + inPos] << 1 & 127 | in[6 + inPos] >> 7 & 1;
         out[7 + outPos] = in[6 + inPos] & 127;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) >> 1 & 127;
         out[1 + outPos] = in.get(0 + inPos) << 6 & 127 | in.get(1 + inPos) >> 2 & 63;
         out[2 + outPos] = in.get(1 + inPos) << 5 & 127 | in.get(2 + inPos) >> 3 & 31;
         out[3 + outPos] = in.get(2 + inPos) << 4 & 127 | in.get(3 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(3 + inPos) << 3 & 127 | in.get(4 + inPos) >> 5 & 7;
         out[5 + outPos] = in.get(4 + inPos) << 2 & 127 | in.get(5 + inPos) >> 6 & 3;
         out[6 + outPos] = in.get(5 + inPos) << 1 & 127 | in.get(6 + inPos) >> 7 & 1;
         out[7 + outPos] = in.get(6 + inPos) & 127;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] >> 1 & 127;
         out[1 + outPos] = in[0 + inPos] << 6 & 127 | in[1 + inPos] >> 2 & 63;
         out[2 + outPos] = in[1 + inPos] << 5 & 127 | in[2 + inPos] >> 3 & 31;
         out[3 + outPos] = in[2 + inPos] << 4 & 127 | in[3 + inPos] >> 4 & 15;
         out[4 + outPos] = in[3 + inPos] << 3 & 127 | in[4 + inPos] >> 5 & 7;
         out[5 + outPos] = in[4 + inPos] << 2 & 127 | in[5 + inPos] >> 6 & 3;
         out[6 + outPos] = in[5 + inPos] << 1 & 127 | in[6 + inPos] >> 7 & 1;
         out[7 + outPos] = in[6 + inPos] & 127;
         out[8 + outPos] = in[7 + inPos] >> 1 & 127;
         out[9 + outPos] = in[7 + inPos] << 6 & 127 | in[8 + inPos] >> 2 & 63;
         out[10 + outPos] = in[8 + inPos] << 5 & 127 | in[9 + inPos] >> 3 & 31;
         out[11 + outPos] = in[9 + inPos] << 4 & 127 | in[10 + inPos] >> 4 & 15;
         out[12 + outPos] = in[10 + inPos] << 3 & 127 | in[11 + inPos] >> 5 & 7;
         out[13 + outPos] = in[11 + inPos] << 2 & 127 | in[12 + inPos] >> 6 & 3;
         out[14 + outPos] = in[12 + inPos] << 1 & 127 | in[13 + inPos] >> 7 & 1;
         out[15 + outPos] = in[13 + inPos] & 127;
         out[16 + outPos] = in[14 + inPos] >> 1 & 127;
         out[17 + outPos] = in[14 + inPos] << 6 & 127 | in[15 + inPos] >> 2 & 63;
         out[18 + outPos] = in[15 + inPos] << 5 & 127 | in[16 + inPos] >> 3 & 31;
         out[19 + outPos] = in[16 + inPos] << 4 & 127 | in[17 + inPos] >> 4 & 15;
         out[20 + outPos] = in[17 + inPos] << 3 & 127 | in[18 + inPos] >> 5 & 7;
         out[21 + outPos] = in[18 + inPos] << 2 & 127 | in[19 + inPos] >> 6 & 3;
         out[22 + outPos] = in[19 + inPos] << 1 & 127 | in[20 + inPos] >> 7 & 1;
         out[23 + outPos] = in[20 + inPos] & 127;
         out[24 + outPos] = in[21 + inPos] >> 1 & 127;
         out[25 + outPos] = in[21 + inPos] << 6 & 127 | in[22 + inPos] >> 2 & 63;
         out[26 + outPos] = in[22 + inPos] << 5 & 127 | in[23 + inPos] >> 3 & 31;
         out[27 + outPos] = in[23 + inPos] << 4 & 127 | in[24 + inPos] >> 4 & 15;
         out[28 + outPos] = in[24 + inPos] << 3 & 127 | in[25 + inPos] >> 5 & 7;
         out[29 + outPos] = in[25 + inPos] << 2 & 127 | in[26 + inPos] >> 6 & 3;
         out[30 + outPos] = in[26 + inPos] << 1 & 127 | in[27 + inPos] >> 7 & 1;
         out[31 + outPos] = in[27 + inPos] & 127;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) >> 1 & 127;
         out[1 + outPos] = in.get(0 + inPos) << 6 & 127 | in.get(1 + inPos) >> 2 & 63;
         out[2 + outPos] = in.get(1 + inPos) << 5 & 127 | in.get(2 + inPos) >> 3 & 31;
         out[3 + outPos] = in.get(2 + inPos) << 4 & 127 | in.get(3 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(3 + inPos) << 3 & 127 | in.get(4 + inPos) >> 5 & 7;
         out[5 + outPos] = in.get(4 + inPos) << 2 & 127 | in.get(5 + inPos) >> 6 & 3;
         out[6 + outPos] = in.get(5 + inPos) << 1 & 127 | in.get(6 + inPos) >> 7 & 1;
         out[7 + outPos] = in.get(6 + inPos) & 127;
         out[8 + outPos] = in.get(7 + inPos) >> 1 & 127;
         out[9 + outPos] = in.get(7 + inPos) << 6 & 127 | in.get(8 + inPos) >> 2 & 63;
         out[10 + outPos] = in.get(8 + inPos) << 5 & 127 | in.get(9 + inPos) >> 3 & 31;
         out[11 + outPos] = in.get(9 + inPos) << 4 & 127 | in.get(10 + inPos) >> 4 & 15;
         out[12 + outPos] = in.get(10 + inPos) << 3 & 127 | in.get(11 + inPos) >> 5 & 7;
         out[13 + outPos] = in.get(11 + inPos) << 2 & 127 | in.get(12 + inPos) >> 6 & 3;
         out[14 + outPos] = in.get(12 + inPos) << 1 & 127 | in.get(13 + inPos) >> 7 & 1;
         out[15 + outPos] = in.get(13 + inPos) & 127;
         out[16 + outPos] = in.get(14 + inPos) >> 1 & 127;
         out[17 + outPos] = in.get(14 + inPos) << 6 & 127 | in.get(15 + inPos) >> 2 & 63;
         out[18 + outPos] = in.get(15 + inPos) << 5 & 127 | in.get(16 + inPos) >> 3 & 31;
         out[19 + outPos] = in.get(16 + inPos) << 4 & 127 | in.get(17 + inPos) >> 4 & 15;
         out[20 + outPos] = in.get(17 + inPos) << 3 & 127 | in.get(18 + inPos) >> 5 & 7;
         out[21 + outPos] = in.get(18 + inPos) << 2 & 127 | in.get(19 + inPos) >> 6 & 3;
         out[22 + outPos] = in.get(19 + inPos) << 1 & 127 | in.get(20 + inPos) >> 7 & 1;
         out[23 + outPos] = in.get(20 + inPos) & 127;
         out[24 + outPos] = in.get(21 + inPos) >> 1 & 127;
         out[25 + outPos] = in.get(21 + inPos) << 6 & 127 | in.get(22 + inPos) >> 2 & 63;
         out[26 + outPos] = in.get(22 + inPos) << 5 & 127 | in.get(23 + inPos) >> 3 & 31;
         out[27 + outPos] = in.get(23 + inPos) << 4 & 127 | in.get(24 + inPos) >> 4 & 15;
         out[28 + outPos] = in.get(24 + inPos) << 3 & 127 | in.get(25 + inPos) >> 5 & 7;
         out[29 + outPos] = in.get(25 + inPos) << 2 & 127 | in.get(26 + inPos) >> 6 & 3;
         out[30 + outPos] = in.get(26 + inPos) << 1 & 127 | in.get(27 + inPos) >> 7 & 1;
         out[31 + outPos] = in.get(27 + inPos) & 127;
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
         out[0 + outPos] = (byte)((in[0 + inPos] & 511) >>> 1 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 511) << 7 | (in[1 + inPos] & 511) >>> 2) & 255);
         out[2 + outPos] = (byte)(((in[1 + inPos] & 511) << 6 | (in[2 + inPos] & 511) >>> 3) & 255);
         out[3 + outPos] = (byte)(((in[2 + inPos] & 511) << 5 | (in[3 + inPos] & 511) >>> 4) & 255);
         out[4 + outPos] = (byte)(((in[3 + inPos] & 511) << 4 | (in[4 + inPos] & 511) >>> 5) & 255);
         out[5 + outPos] = (byte)(((in[4 + inPos] & 511) << 3 | (in[5 + inPos] & 511) >>> 6) & 255);
         out[6 + outPos] = (byte)(((in[5 + inPos] & 511) << 2 | (in[6 + inPos] & 511) >>> 7) & 255);
         out[7 + outPos] = (byte)(((in[6 + inPos] & 511) << 1 | (in[7 + inPos] & 511) >>> 8) & 255);
         out[8 + outPos] = (byte)(in[7 + inPos] & 511 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 511) >>> 1 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 511) << 7 | (in[1 + inPos] & 511) >>> 2) & 255);
         out[2 + outPos] = (byte)(((in[1 + inPos] & 511) << 6 | (in[2 + inPos] & 511) >>> 3) & 255);
         out[3 + outPos] = (byte)(((in[2 + inPos] & 511) << 5 | (in[3 + inPos] & 511) >>> 4) & 255);
         out[4 + outPos] = (byte)(((in[3 + inPos] & 511) << 4 | (in[4 + inPos] & 511) >>> 5) & 255);
         out[5 + outPos] = (byte)(((in[4 + inPos] & 511) << 3 | (in[5 + inPos] & 511) >>> 6) & 255);
         out[6 + outPos] = (byte)(((in[5 + inPos] & 511) << 2 | (in[6 + inPos] & 511) >>> 7) & 255);
         out[7 + outPos] = (byte)(((in[6 + inPos] & 511) << 1 | (in[7 + inPos] & 511) >>> 8) & 255);
         out[8 + outPos] = (byte)(in[7 + inPos] & 511 & 255);
         out[9 + outPos] = (byte)((in[8 + inPos] & 511) >>> 1 & 255);
         out[10 + outPos] = (byte)(((in[8 + inPos] & 511) << 7 | (in[9 + inPos] & 511) >>> 2) & 255);
         out[11 + outPos] = (byte)(((in[9 + inPos] & 511) << 6 | (in[10 + inPos] & 511) >>> 3) & 255);
         out[12 + outPos] = (byte)(((in[10 + inPos] & 511) << 5 | (in[11 + inPos] & 511) >>> 4) & 255);
         out[13 + outPos] = (byte)(((in[11 + inPos] & 511) << 4 | (in[12 + inPos] & 511) >>> 5) & 255);
         out[14 + outPos] = (byte)(((in[12 + inPos] & 511) << 3 | (in[13 + inPos] & 511) >>> 6) & 255);
         out[15 + outPos] = (byte)(((in[13 + inPos] & 511) << 2 | (in[14 + inPos] & 511) >>> 7) & 255);
         out[16 + outPos] = (byte)(((in[14 + inPos] & 511) << 1 | (in[15 + inPos] & 511) >>> 8) & 255);
         out[17 + outPos] = (byte)(in[15 + inPos] & 511 & 255);
         out[18 + outPos] = (byte)((in[16 + inPos] & 511) >>> 1 & 255);
         out[19 + outPos] = (byte)(((in[16 + inPos] & 511) << 7 | (in[17 + inPos] & 511) >>> 2) & 255);
         out[20 + outPos] = (byte)(((in[17 + inPos] & 511) << 6 | (in[18 + inPos] & 511) >>> 3) & 255);
         out[21 + outPos] = (byte)(((in[18 + inPos] & 511) << 5 | (in[19 + inPos] & 511) >>> 4) & 255);
         out[22 + outPos] = (byte)(((in[19 + inPos] & 511) << 4 | (in[20 + inPos] & 511) >>> 5) & 255);
         out[23 + outPos] = (byte)(((in[20 + inPos] & 511) << 3 | (in[21 + inPos] & 511) >>> 6) & 255);
         out[24 + outPos] = (byte)(((in[21 + inPos] & 511) << 2 | (in[22 + inPos] & 511) >>> 7) & 255);
         out[25 + outPos] = (byte)(((in[22 + inPos] & 511) << 1 | (in[23 + inPos] & 511) >>> 8) & 255);
         out[26 + outPos] = (byte)(in[23 + inPos] & 511 & 255);
         out[27 + outPos] = (byte)((in[24 + inPos] & 511) >>> 1 & 255);
         out[28 + outPos] = (byte)(((in[24 + inPos] & 511) << 7 | (in[25 + inPos] & 511) >>> 2) & 255);
         out[29 + outPos] = (byte)(((in[25 + inPos] & 511) << 6 | (in[26 + inPos] & 511) >>> 3) & 255);
         out[30 + outPos] = (byte)(((in[26 + inPos] & 511) << 5 | (in[27 + inPos] & 511) >>> 4) & 255);
         out[31 + outPos] = (byte)(((in[27 + inPos] & 511) << 4 | (in[28 + inPos] & 511) >>> 5) & 255);
         out[32 + outPos] = (byte)(((in[28 + inPos] & 511) << 3 | (in[29 + inPos] & 511) >>> 6) & 255);
         out[33 + outPos] = (byte)(((in[29 + inPos] & 511) << 2 | (in[30 + inPos] & 511) >>> 7) & 255);
         out[34 + outPos] = (byte)(((in[30 + inPos] & 511) << 1 | (in[31 + inPos] & 511) >>> 8) & 255);
         out[35 + outPos] = (byte)(in[31 + inPos] & 511 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 1 & 511 | in[1 + inPos] >> 7 & 1;
         out[1 + outPos] = in[1 + inPos] << 2 & 511 | in[2 + inPos] >> 6 & 3;
         out[2 + outPos] = in[2 + inPos] << 3 & 511 | in[3 + inPos] >> 5 & 7;
         out[3 + outPos] = in[3 + inPos] << 4 & 511 | in[4 + inPos] >> 4 & 15;
         out[4 + outPos] = in[4 + inPos] << 5 & 511 | in[5 + inPos] >> 3 & 31;
         out[5 + outPos] = in[5 + inPos] << 6 & 511 | in[6 + inPos] >> 2 & 63;
         out[6 + outPos] = in[6 + inPos] << 7 & 511 | in[7 + inPos] >> 1 & 127;
         out[7 + outPos] = in[7 + inPos] << 8 & 511 | in[8 + inPos] & 255;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 1 & 511 | in.get(1 + inPos) >> 7 & 1;
         out[1 + outPos] = in.get(1 + inPos) << 2 & 511 | in.get(2 + inPos) >> 6 & 3;
         out[2 + outPos] = in.get(2 + inPos) << 3 & 511 | in.get(3 + inPos) >> 5 & 7;
         out[3 + outPos] = in.get(3 + inPos) << 4 & 511 | in.get(4 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(4 + inPos) << 5 & 511 | in.get(5 + inPos) >> 3 & 31;
         out[5 + outPos] = in.get(5 + inPos) << 6 & 511 | in.get(6 + inPos) >> 2 & 63;
         out[6 + outPos] = in.get(6 + inPos) << 7 & 511 | in.get(7 + inPos) >> 1 & 127;
         out[7 + outPos] = in.get(7 + inPos) << 8 & 511 | in.get(8 + inPos) & 255;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 1 & 511 | in[1 + inPos] >> 7 & 1;
         out[1 + outPos] = in[1 + inPos] << 2 & 511 | in[2 + inPos] >> 6 & 3;
         out[2 + outPos] = in[2 + inPos] << 3 & 511 | in[3 + inPos] >> 5 & 7;
         out[3 + outPos] = in[3 + inPos] << 4 & 511 | in[4 + inPos] >> 4 & 15;
         out[4 + outPos] = in[4 + inPos] << 5 & 511 | in[5 + inPos] >> 3 & 31;
         out[5 + outPos] = in[5 + inPos] << 6 & 511 | in[6 + inPos] >> 2 & 63;
         out[6 + outPos] = in[6 + inPos] << 7 & 511 | in[7 + inPos] >> 1 & 127;
         out[7 + outPos] = in[7 + inPos] << 8 & 511 | in[8 + inPos] & 255;
         out[8 + outPos] = in[9 + inPos] << 1 & 511 | in[10 + inPos] >> 7 & 1;
         out[9 + outPos] = in[10 + inPos] << 2 & 511 | in[11 + inPos] >> 6 & 3;
         out[10 + outPos] = in[11 + inPos] << 3 & 511 | in[12 + inPos] >> 5 & 7;
         out[11 + outPos] = in[12 + inPos] << 4 & 511 | in[13 + inPos] >> 4 & 15;
         out[12 + outPos] = in[13 + inPos] << 5 & 511 | in[14 + inPos] >> 3 & 31;
         out[13 + outPos] = in[14 + inPos] << 6 & 511 | in[15 + inPos] >> 2 & 63;
         out[14 + outPos] = in[15 + inPos] << 7 & 511 | in[16 + inPos] >> 1 & 127;
         out[15 + outPos] = in[16 + inPos] << 8 & 511 | in[17 + inPos] & 255;
         out[16 + outPos] = in[18 + inPos] << 1 & 511 | in[19 + inPos] >> 7 & 1;
         out[17 + outPos] = in[19 + inPos] << 2 & 511 | in[20 + inPos] >> 6 & 3;
         out[18 + outPos] = in[20 + inPos] << 3 & 511 | in[21 + inPos] >> 5 & 7;
         out[19 + outPos] = in[21 + inPos] << 4 & 511 | in[22 + inPos] >> 4 & 15;
         out[20 + outPos] = in[22 + inPos] << 5 & 511 | in[23 + inPos] >> 3 & 31;
         out[21 + outPos] = in[23 + inPos] << 6 & 511 | in[24 + inPos] >> 2 & 63;
         out[22 + outPos] = in[24 + inPos] << 7 & 511 | in[25 + inPos] >> 1 & 127;
         out[23 + outPos] = in[25 + inPos] << 8 & 511 | in[26 + inPos] & 255;
         out[24 + outPos] = in[27 + inPos] << 1 & 511 | in[28 + inPos] >> 7 & 1;
         out[25 + outPos] = in[28 + inPos] << 2 & 511 | in[29 + inPos] >> 6 & 3;
         out[26 + outPos] = in[29 + inPos] << 3 & 511 | in[30 + inPos] >> 5 & 7;
         out[27 + outPos] = in[30 + inPos] << 4 & 511 | in[31 + inPos] >> 4 & 15;
         out[28 + outPos] = in[31 + inPos] << 5 & 511 | in[32 + inPos] >> 3 & 31;
         out[29 + outPos] = in[32 + inPos] << 6 & 511 | in[33 + inPos] >> 2 & 63;
         out[30 + outPos] = in[33 + inPos] << 7 & 511 | in[34 + inPos] >> 1 & 127;
         out[31 + outPos] = in[34 + inPos] << 8 & 511 | in[35 + inPos] & 255;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 1 & 511 | in.get(1 + inPos) >> 7 & 1;
         out[1 + outPos] = in.get(1 + inPos) << 2 & 511 | in.get(2 + inPos) >> 6 & 3;
         out[2 + outPos] = in.get(2 + inPos) << 3 & 511 | in.get(3 + inPos) >> 5 & 7;
         out[3 + outPos] = in.get(3 + inPos) << 4 & 511 | in.get(4 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(4 + inPos) << 5 & 511 | in.get(5 + inPos) >> 3 & 31;
         out[5 + outPos] = in.get(5 + inPos) << 6 & 511 | in.get(6 + inPos) >> 2 & 63;
         out[6 + outPos] = in.get(6 + inPos) << 7 & 511 | in.get(7 + inPos) >> 1 & 127;
         out[7 + outPos] = in.get(7 + inPos) << 8 & 511 | in.get(8 + inPos) & 255;
         out[8 + outPos] = in.get(9 + inPos) << 1 & 511 | in.get(10 + inPos) >> 7 & 1;
         out[9 + outPos] = in.get(10 + inPos) << 2 & 511 | in.get(11 + inPos) >> 6 & 3;
         out[10 + outPos] = in.get(11 + inPos) << 3 & 511 | in.get(12 + inPos) >> 5 & 7;
         out[11 + outPos] = in.get(12 + inPos) << 4 & 511 | in.get(13 + inPos) >> 4 & 15;
         out[12 + outPos] = in.get(13 + inPos) << 5 & 511 | in.get(14 + inPos) >> 3 & 31;
         out[13 + outPos] = in.get(14 + inPos) << 6 & 511 | in.get(15 + inPos) >> 2 & 63;
         out[14 + outPos] = in.get(15 + inPos) << 7 & 511 | in.get(16 + inPos) >> 1 & 127;
         out[15 + outPos] = in.get(16 + inPos) << 8 & 511 | in.get(17 + inPos) & 255;
         out[16 + outPos] = in.get(18 + inPos) << 1 & 511 | in.get(19 + inPos) >> 7 & 1;
         out[17 + outPos] = in.get(19 + inPos) << 2 & 511 | in.get(20 + inPos) >> 6 & 3;
         out[18 + outPos] = in.get(20 + inPos) << 3 & 511 | in.get(21 + inPos) >> 5 & 7;
         out[19 + outPos] = in.get(21 + inPos) << 4 & 511 | in.get(22 + inPos) >> 4 & 15;
         out[20 + outPos] = in.get(22 + inPos) << 5 & 511 | in.get(23 + inPos) >> 3 & 31;
         out[21 + outPos] = in.get(23 + inPos) << 6 & 511 | in.get(24 + inPos) >> 2 & 63;
         out[22 + outPos] = in.get(24 + inPos) << 7 & 511 | in.get(25 + inPos) >> 1 & 127;
         out[23 + outPos] = in.get(25 + inPos) << 8 & 511 | in.get(26 + inPos) & 255;
         out[24 + outPos] = in.get(27 + inPos) << 1 & 511 | in.get(28 + inPos) >> 7 & 1;
         out[25 + outPos] = in.get(28 + inPos) << 2 & 511 | in.get(29 + inPos) >> 6 & 3;
         out[26 + outPos] = in.get(29 + inPos) << 3 & 511 | in.get(30 + inPos) >> 5 & 7;
         out[27 + outPos] = in.get(30 + inPos) << 4 & 511 | in.get(31 + inPos) >> 4 & 15;
         out[28 + outPos] = in.get(31 + inPos) << 5 & 511 | in.get(32 + inPos) >> 3 & 31;
         out[29 + outPos] = in.get(32 + inPos) << 6 & 511 | in.get(33 + inPos) >> 2 & 63;
         out[30 + outPos] = in.get(33 + inPos) << 7 & 511 | in.get(34 + inPos) >> 1 & 127;
         out[31 + outPos] = in.get(34 + inPos) << 8 & 511 | in.get(35 + inPos) & 255;
      }
   }

   private static final class Packer10 extends BytePacker {
      private Packer10() {
         super(10);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 1023) >>> 2 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 1023) << 6 | (in[1 + inPos] & 1023) >>> 4) & 255);
         out[2 + outPos] = (byte)(((in[1 + inPos] & 1023) << 4 | (in[2 + inPos] & 1023) >>> 6) & 255);
         out[3 + outPos] = (byte)(((in[2 + inPos] & 1023) << 2 | (in[3 + inPos] & 1023) >>> 8) & 255);
         out[4 + outPos] = (byte)(in[3 + inPos] & 1023 & 255);
         out[5 + outPos] = (byte)((in[4 + inPos] & 1023) >>> 2 & 255);
         out[6 + outPos] = (byte)(((in[4 + inPos] & 1023) << 6 | (in[5 + inPos] & 1023) >>> 4) & 255);
         out[7 + outPos] = (byte)(((in[5 + inPos] & 1023) << 4 | (in[6 + inPos] & 1023) >>> 6) & 255);
         out[8 + outPos] = (byte)(((in[6 + inPos] & 1023) << 2 | (in[7 + inPos] & 1023) >>> 8) & 255);
         out[9 + outPos] = (byte)(in[7 + inPos] & 1023 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 1023) >>> 2 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 1023) << 6 | (in[1 + inPos] & 1023) >>> 4) & 255);
         out[2 + outPos] = (byte)(((in[1 + inPos] & 1023) << 4 | (in[2 + inPos] & 1023) >>> 6) & 255);
         out[3 + outPos] = (byte)(((in[2 + inPos] & 1023) << 2 | (in[3 + inPos] & 1023) >>> 8) & 255);
         out[4 + outPos] = (byte)(in[3 + inPos] & 1023 & 255);
         out[5 + outPos] = (byte)((in[4 + inPos] & 1023) >>> 2 & 255);
         out[6 + outPos] = (byte)(((in[4 + inPos] & 1023) << 6 | (in[5 + inPos] & 1023) >>> 4) & 255);
         out[7 + outPos] = (byte)(((in[5 + inPos] & 1023) << 4 | (in[6 + inPos] & 1023) >>> 6) & 255);
         out[8 + outPos] = (byte)(((in[6 + inPos] & 1023) << 2 | (in[7 + inPos] & 1023) >>> 8) & 255);
         out[9 + outPos] = (byte)(in[7 + inPos] & 1023 & 255);
         out[10 + outPos] = (byte)((in[8 + inPos] & 1023) >>> 2 & 255);
         out[11 + outPos] = (byte)(((in[8 + inPos] & 1023) << 6 | (in[9 + inPos] & 1023) >>> 4) & 255);
         out[12 + outPos] = (byte)(((in[9 + inPos] & 1023) << 4 | (in[10 + inPos] & 1023) >>> 6) & 255);
         out[13 + outPos] = (byte)(((in[10 + inPos] & 1023) << 2 | (in[11 + inPos] & 1023) >>> 8) & 255);
         out[14 + outPos] = (byte)(in[11 + inPos] & 1023 & 255);
         out[15 + outPos] = (byte)((in[12 + inPos] & 1023) >>> 2 & 255);
         out[16 + outPos] = (byte)(((in[12 + inPos] & 1023) << 6 | (in[13 + inPos] & 1023) >>> 4) & 255);
         out[17 + outPos] = (byte)(((in[13 + inPos] & 1023) << 4 | (in[14 + inPos] & 1023) >>> 6) & 255);
         out[18 + outPos] = (byte)(((in[14 + inPos] & 1023) << 2 | (in[15 + inPos] & 1023) >>> 8) & 255);
         out[19 + outPos] = (byte)(in[15 + inPos] & 1023 & 255);
         out[20 + outPos] = (byte)((in[16 + inPos] & 1023) >>> 2 & 255);
         out[21 + outPos] = (byte)(((in[16 + inPos] & 1023) << 6 | (in[17 + inPos] & 1023) >>> 4) & 255);
         out[22 + outPos] = (byte)(((in[17 + inPos] & 1023) << 4 | (in[18 + inPos] & 1023) >>> 6) & 255);
         out[23 + outPos] = (byte)(((in[18 + inPos] & 1023) << 2 | (in[19 + inPos] & 1023) >>> 8) & 255);
         out[24 + outPos] = (byte)(in[19 + inPos] & 1023 & 255);
         out[25 + outPos] = (byte)((in[20 + inPos] & 1023) >>> 2 & 255);
         out[26 + outPos] = (byte)(((in[20 + inPos] & 1023) << 6 | (in[21 + inPos] & 1023) >>> 4) & 255);
         out[27 + outPos] = (byte)(((in[21 + inPos] & 1023) << 4 | (in[22 + inPos] & 1023) >>> 6) & 255);
         out[28 + outPos] = (byte)(((in[22 + inPos] & 1023) << 2 | (in[23 + inPos] & 1023) >>> 8) & 255);
         out[29 + outPos] = (byte)(in[23 + inPos] & 1023 & 255);
         out[30 + outPos] = (byte)((in[24 + inPos] & 1023) >>> 2 & 255);
         out[31 + outPos] = (byte)(((in[24 + inPos] & 1023) << 6 | (in[25 + inPos] & 1023) >>> 4) & 255);
         out[32 + outPos] = (byte)(((in[25 + inPos] & 1023) << 4 | (in[26 + inPos] & 1023) >>> 6) & 255);
         out[33 + outPos] = (byte)(((in[26 + inPos] & 1023) << 2 | (in[27 + inPos] & 1023) >>> 8) & 255);
         out[34 + outPos] = (byte)(in[27 + inPos] & 1023 & 255);
         out[35 + outPos] = (byte)((in[28 + inPos] & 1023) >>> 2 & 255);
         out[36 + outPos] = (byte)(((in[28 + inPos] & 1023) << 6 | (in[29 + inPos] & 1023) >>> 4) & 255);
         out[37 + outPos] = (byte)(((in[29 + inPos] & 1023) << 4 | (in[30 + inPos] & 1023) >>> 6) & 255);
         out[38 + outPos] = (byte)(((in[30 + inPos] & 1023) << 2 | (in[31 + inPos] & 1023) >>> 8) & 255);
         out[39 + outPos] = (byte)(in[31 + inPos] & 1023 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 2 & 1023 | in[1 + inPos] >> 6 & 3;
         out[1 + outPos] = in[1 + inPos] << 4 & 1023 | in[2 + inPos] >> 4 & 15;
         out[2 + outPos] = in[2 + inPos] << 6 & 1023 | in[3 + inPos] >> 2 & 63;
         out[3 + outPos] = in[3 + inPos] << 8 & 1023 | in[4 + inPos] & 255;
         out[4 + outPos] = in[5 + inPos] << 2 & 1023 | in[6 + inPos] >> 6 & 3;
         out[5 + outPos] = in[6 + inPos] << 4 & 1023 | in[7 + inPos] >> 4 & 15;
         out[6 + outPos] = in[7 + inPos] << 6 & 1023 | in[8 + inPos] >> 2 & 63;
         out[7 + outPos] = in[8 + inPos] << 8 & 1023 | in[9 + inPos] & 255;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 2 & 1023 | in.get(1 + inPos) >> 6 & 3;
         out[1 + outPos] = in.get(1 + inPos) << 4 & 1023 | in.get(2 + inPos) >> 4 & 15;
         out[2 + outPos] = in.get(2 + inPos) << 6 & 1023 | in.get(3 + inPos) >> 2 & 63;
         out[3 + outPos] = in.get(3 + inPos) << 8 & 1023 | in.get(4 + inPos) & 255;
         out[4 + outPos] = in.get(5 + inPos) << 2 & 1023 | in.get(6 + inPos) >> 6 & 3;
         out[5 + outPos] = in.get(6 + inPos) << 4 & 1023 | in.get(7 + inPos) >> 4 & 15;
         out[6 + outPos] = in.get(7 + inPos) << 6 & 1023 | in.get(8 + inPos) >> 2 & 63;
         out[7 + outPos] = in.get(8 + inPos) << 8 & 1023 | in.get(9 + inPos) & 255;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 2 & 1023 | in[1 + inPos] >> 6 & 3;
         out[1 + outPos] = in[1 + inPos] << 4 & 1023 | in[2 + inPos] >> 4 & 15;
         out[2 + outPos] = in[2 + inPos] << 6 & 1023 | in[3 + inPos] >> 2 & 63;
         out[3 + outPos] = in[3 + inPos] << 8 & 1023 | in[4 + inPos] & 255;
         out[4 + outPos] = in[5 + inPos] << 2 & 1023 | in[6 + inPos] >> 6 & 3;
         out[5 + outPos] = in[6 + inPos] << 4 & 1023 | in[7 + inPos] >> 4 & 15;
         out[6 + outPos] = in[7 + inPos] << 6 & 1023 | in[8 + inPos] >> 2 & 63;
         out[7 + outPos] = in[8 + inPos] << 8 & 1023 | in[9 + inPos] & 255;
         out[8 + outPos] = in[10 + inPos] << 2 & 1023 | in[11 + inPos] >> 6 & 3;
         out[9 + outPos] = in[11 + inPos] << 4 & 1023 | in[12 + inPos] >> 4 & 15;
         out[10 + outPos] = in[12 + inPos] << 6 & 1023 | in[13 + inPos] >> 2 & 63;
         out[11 + outPos] = in[13 + inPos] << 8 & 1023 | in[14 + inPos] & 255;
         out[12 + outPos] = in[15 + inPos] << 2 & 1023 | in[16 + inPos] >> 6 & 3;
         out[13 + outPos] = in[16 + inPos] << 4 & 1023 | in[17 + inPos] >> 4 & 15;
         out[14 + outPos] = in[17 + inPos] << 6 & 1023 | in[18 + inPos] >> 2 & 63;
         out[15 + outPos] = in[18 + inPos] << 8 & 1023 | in[19 + inPos] & 255;
         out[16 + outPos] = in[20 + inPos] << 2 & 1023 | in[21 + inPos] >> 6 & 3;
         out[17 + outPos] = in[21 + inPos] << 4 & 1023 | in[22 + inPos] >> 4 & 15;
         out[18 + outPos] = in[22 + inPos] << 6 & 1023 | in[23 + inPos] >> 2 & 63;
         out[19 + outPos] = in[23 + inPos] << 8 & 1023 | in[24 + inPos] & 255;
         out[20 + outPos] = in[25 + inPos] << 2 & 1023 | in[26 + inPos] >> 6 & 3;
         out[21 + outPos] = in[26 + inPos] << 4 & 1023 | in[27 + inPos] >> 4 & 15;
         out[22 + outPos] = in[27 + inPos] << 6 & 1023 | in[28 + inPos] >> 2 & 63;
         out[23 + outPos] = in[28 + inPos] << 8 & 1023 | in[29 + inPos] & 255;
         out[24 + outPos] = in[30 + inPos] << 2 & 1023 | in[31 + inPos] >> 6 & 3;
         out[25 + outPos] = in[31 + inPos] << 4 & 1023 | in[32 + inPos] >> 4 & 15;
         out[26 + outPos] = in[32 + inPos] << 6 & 1023 | in[33 + inPos] >> 2 & 63;
         out[27 + outPos] = in[33 + inPos] << 8 & 1023 | in[34 + inPos] & 255;
         out[28 + outPos] = in[35 + inPos] << 2 & 1023 | in[36 + inPos] >> 6 & 3;
         out[29 + outPos] = in[36 + inPos] << 4 & 1023 | in[37 + inPos] >> 4 & 15;
         out[30 + outPos] = in[37 + inPos] << 6 & 1023 | in[38 + inPos] >> 2 & 63;
         out[31 + outPos] = in[38 + inPos] << 8 & 1023 | in[39 + inPos] & 255;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 2 & 1023 | in.get(1 + inPos) >> 6 & 3;
         out[1 + outPos] = in.get(1 + inPos) << 4 & 1023 | in.get(2 + inPos) >> 4 & 15;
         out[2 + outPos] = in.get(2 + inPos) << 6 & 1023 | in.get(3 + inPos) >> 2 & 63;
         out[3 + outPos] = in.get(3 + inPos) << 8 & 1023 | in.get(4 + inPos) & 255;
         out[4 + outPos] = in.get(5 + inPos) << 2 & 1023 | in.get(6 + inPos) >> 6 & 3;
         out[5 + outPos] = in.get(6 + inPos) << 4 & 1023 | in.get(7 + inPos) >> 4 & 15;
         out[6 + outPos] = in.get(7 + inPos) << 6 & 1023 | in.get(8 + inPos) >> 2 & 63;
         out[7 + outPos] = in.get(8 + inPos) << 8 & 1023 | in.get(9 + inPos) & 255;
         out[8 + outPos] = in.get(10 + inPos) << 2 & 1023 | in.get(11 + inPos) >> 6 & 3;
         out[9 + outPos] = in.get(11 + inPos) << 4 & 1023 | in.get(12 + inPos) >> 4 & 15;
         out[10 + outPos] = in.get(12 + inPos) << 6 & 1023 | in.get(13 + inPos) >> 2 & 63;
         out[11 + outPos] = in.get(13 + inPos) << 8 & 1023 | in.get(14 + inPos) & 255;
         out[12 + outPos] = in.get(15 + inPos) << 2 & 1023 | in.get(16 + inPos) >> 6 & 3;
         out[13 + outPos] = in.get(16 + inPos) << 4 & 1023 | in.get(17 + inPos) >> 4 & 15;
         out[14 + outPos] = in.get(17 + inPos) << 6 & 1023 | in.get(18 + inPos) >> 2 & 63;
         out[15 + outPos] = in.get(18 + inPos) << 8 & 1023 | in.get(19 + inPos) & 255;
         out[16 + outPos] = in.get(20 + inPos) << 2 & 1023 | in.get(21 + inPos) >> 6 & 3;
         out[17 + outPos] = in.get(21 + inPos) << 4 & 1023 | in.get(22 + inPos) >> 4 & 15;
         out[18 + outPos] = in.get(22 + inPos) << 6 & 1023 | in.get(23 + inPos) >> 2 & 63;
         out[19 + outPos] = in.get(23 + inPos) << 8 & 1023 | in.get(24 + inPos) & 255;
         out[20 + outPos] = in.get(25 + inPos) << 2 & 1023 | in.get(26 + inPos) >> 6 & 3;
         out[21 + outPos] = in.get(26 + inPos) << 4 & 1023 | in.get(27 + inPos) >> 4 & 15;
         out[22 + outPos] = in.get(27 + inPos) << 6 & 1023 | in.get(28 + inPos) >> 2 & 63;
         out[23 + outPos] = in.get(28 + inPos) << 8 & 1023 | in.get(29 + inPos) & 255;
         out[24 + outPos] = in.get(30 + inPos) << 2 & 1023 | in.get(31 + inPos) >> 6 & 3;
         out[25 + outPos] = in.get(31 + inPos) << 4 & 1023 | in.get(32 + inPos) >> 4 & 15;
         out[26 + outPos] = in.get(32 + inPos) << 6 & 1023 | in.get(33 + inPos) >> 2 & 63;
         out[27 + outPos] = in.get(33 + inPos) << 8 & 1023 | in.get(34 + inPos) & 255;
         out[28 + outPos] = in.get(35 + inPos) << 2 & 1023 | in.get(36 + inPos) >> 6 & 3;
         out[29 + outPos] = in.get(36 + inPos) << 4 & 1023 | in.get(37 + inPos) >> 4 & 15;
         out[30 + outPos] = in.get(37 + inPos) << 6 & 1023 | in.get(38 + inPos) >> 2 & 63;
         out[31 + outPos] = in.get(38 + inPos) << 8 & 1023 | in.get(39 + inPos) & 255;
      }
   }

   private static final class Packer11 extends BytePacker {
      private Packer11() {
         super(11);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 2047) >>> 3 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 2047) << 5 | (in[1 + inPos] & 2047) >>> 6) & 255);
         out[2 + outPos] = (byte)(((in[1 + inPos] & 2047) << 2 | (in[2 + inPos] & 2047) >>> 9) & 255);
         out[3 + outPos] = (byte)((in[2 + inPos] & 2047) >>> 1 & 255);
         out[4 + outPos] = (byte)(((in[2 + inPos] & 2047) << 7 | (in[3 + inPos] & 2047) >>> 4) & 255);
         out[5 + outPos] = (byte)(((in[3 + inPos] & 2047) << 4 | (in[4 + inPos] & 2047) >>> 7) & 255);
         out[6 + outPos] = (byte)(((in[4 + inPos] & 2047) << 1 | (in[5 + inPos] & 2047) >>> 10) & 255);
         out[7 + outPos] = (byte)((in[5 + inPos] & 2047) >>> 2 & 255);
         out[8 + outPos] = (byte)(((in[5 + inPos] & 2047) << 6 | (in[6 + inPos] & 2047) >>> 5) & 255);
         out[9 + outPos] = (byte)(((in[6 + inPos] & 2047) << 3 | (in[7 + inPos] & 2047) >>> 8) & 255);
         out[10 + outPos] = (byte)(in[7 + inPos] & 2047 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 2047) >>> 3 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 2047) << 5 | (in[1 + inPos] & 2047) >>> 6) & 255);
         out[2 + outPos] = (byte)(((in[1 + inPos] & 2047) << 2 | (in[2 + inPos] & 2047) >>> 9) & 255);
         out[3 + outPos] = (byte)((in[2 + inPos] & 2047) >>> 1 & 255);
         out[4 + outPos] = (byte)(((in[2 + inPos] & 2047) << 7 | (in[3 + inPos] & 2047) >>> 4) & 255);
         out[5 + outPos] = (byte)(((in[3 + inPos] & 2047) << 4 | (in[4 + inPos] & 2047) >>> 7) & 255);
         out[6 + outPos] = (byte)(((in[4 + inPos] & 2047) << 1 | (in[5 + inPos] & 2047) >>> 10) & 255);
         out[7 + outPos] = (byte)((in[5 + inPos] & 2047) >>> 2 & 255);
         out[8 + outPos] = (byte)(((in[5 + inPos] & 2047) << 6 | (in[6 + inPos] & 2047) >>> 5) & 255);
         out[9 + outPos] = (byte)(((in[6 + inPos] & 2047) << 3 | (in[7 + inPos] & 2047) >>> 8) & 255);
         out[10 + outPos] = (byte)(in[7 + inPos] & 2047 & 255);
         out[11 + outPos] = (byte)((in[8 + inPos] & 2047) >>> 3 & 255);
         out[12 + outPos] = (byte)(((in[8 + inPos] & 2047) << 5 | (in[9 + inPos] & 2047) >>> 6) & 255);
         out[13 + outPos] = (byte)(((in[9 + inPos] & 2047) << 2 | (in[10 + inPos] & 2047) >>> 9) & 255);
         out[14 + outPos] = (byte)((in[10 + inPos] & 2047) >>> 1 & 255);
         out[15 + outPos] = (byte)(((in[10 + inPos] & 2047) << 7 | (in[11 + inPos] & 2047) >>> 4) & 255);
         out[16 + outPos] = (byte)(((in[11 + inPos] & 2047) << 4 | (in[12 + inPos] & 2047) >>> 7) & 255);
         out[17 + outPos] = (byte)(((in[12 + inPos] & 2047) << 1 | (in[13 + inPos] & 2047) >>> 10) & 255);
         out[18 + outPos] = (byte)((in[13 + inPos] & 2047) >>> 2 & 255);
         out[19 + outPos] = (byte)(((in[13 + inPos] & 2047) << 6 | (in[14 + inPos] & 2047) >>> 5) & 255);
         out[20 + outPos] = (byte)(((in[14 + inPos] & 2047) << 3 | (in[15 + inPos] & 2047) >>> 8) & 255);
         out[21 + outPos] = (byte)(in[15 + inPos] & 2047 & 255);
         out[22 + outPos] = (byte)((in[16 + inPos] & 2047) >>> 3 & 255);
         out[23 + outPos] = (byte)(((in[16 + inPos] & 2047) << 5 | (in[17 + inPos] & 2047) >>> 6) & 255);
         out[24 + outPos] = (byte)(((in[17 + inPos] & 2047) << 2 | (in[18 + inPos] & 2047) >>> 9) & 255);
         out[25 + outPos] = (byte)((in[18 + inPos] & 2047) >>> 1 & 255);
         out[26 + outPos] = (byte)(((in[18 + inPos] & 2047) << 7 | (in[19 + inPos] & 2047) >>> 4) & 255);
         out[27 + outPos] = (byte)(((in[19 + inPos] & 2047) << 4 | (in[20 + inPos] & 2047) >>> 7) & 255);
         out[28 + outPos] = (byte)(((in[20 + inPos] & 2047) << 1 | (in[21 + inPos] & 2047) >>> 10) & 255);
         out[29 + outPos] = (byte)((in[21 + inPos] & 2047) >>> 2 & 255);
         out[30 + outPos] = (byte)(((in[21 + inPos] & 2047) << 6 | (in[22 + inPos] & 2047) >>> 5) & 255);
         out[31 + outPos] = (byte)(((in[22 + inPos] & 2047) << 3 | (in[23 + inPos] & 2047) >>> 8) & 255);
         out[32 + outPos] = (byte)(in[23 + inPos] & 2047 & 255);
         out[33 + outPos] = (byte)((in[24 + inPos] & 2047) >>> 3 & 255);
         out[34 + outPos] = (byte)(((in[24 + inPos] & 2047) << 5 | (in[25 + inPos] & 2047) >>> 6) & 255);
         out[35 + outPos] = (byte)(((in[25 + inPos] & 2047) << 2 | (in[26 + inPos] & 2047) >>> 9) & 255);
         out[36 + outPos] = (byte)((in[26 + inPos] & 2047) >>> 1 & 255);
         out[37 + outPos] = (byte)(((in[26 + inPos] & 2047) << 7 | (in[27 + inPos] & 2047) >>> 4) & 255);
         out[38 + outPos] = (byte)(((in[27 + inPos] & 2047) << 4 | (in[28 + inPos] & 2047) >>> 7) & 255);
         out[39 + outPos] = (byte)(((in[28 + inPos] & 2047) << 1 | (in[29 + inPos] & 2047) >>> 10) & 255);
         out[40 + outPos] = (byte)((in[29 + inPos] & 2047) >>> 2 & 255);
         out[41 + outPos] = (byte)(((in[29 + inPos] & 2047) << 6 | (in[30 + inPos] & 2047) >>> 5) & 255);
         out[42 + outPos] = (byte)(((in[30 + inPos] & 2047) << 3 | (in[31 + inPos] & 2047) >>> 8) & 255);
         out[43 + outPos] = (byte)(in[31 + inPos] & 2047 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 3 & 2047 | in[1 + inPos] >> 5 & 7;
         out[1 + outPos] = in[1 + inPos] << 6 & 2047 | in[2 + inPos] >> 2 & 63;
         out[2 + outPos] = in[2 + inPos] << 9 & 2047 | in[3 + inPos] << 1 & 511 | in[4 + inPos] >> 7 & 1;
         out[3 + outPos] = in[4 + inPos] << 4 & 2047 | in[5 + inPos] >> 4 & 15;
         out[4 + outPos] = in[5 + inPos] << 7 & 2047 | in[6 + inPos] >> 1 & 127;
         out[5 + outPos] = in[6 + inPos] << 10 & 2047 | in[7 + inPos] << 2 & 1023 | in[8 + inPos] >> 6 & 3;
         out[6 + outPos] = in[8 + inPos] << 5 & 2047 | in[9 + inPos] >> 3 & 31;
         out[7 + outPos] = in[9 + inPos] << 8 & 2047 | in[10 + inPos] & 255;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 3 & 2047 | in.get(1 + inPos) >> 5 & 7;
         out[1 + outPos] = in.get(1 + inPos) << 6 & 2047 | in.get(2 + inPos) >> 2 & 63;
         out[2 + outPos] = in.get(2 + inPos) << 9 & 2047 | in.get(3 + inPos) << 1 & 511 | in.get(4 + inPos) >> 7 & 1;
         out[3 + outPos] = in.get(4 + inPos) << 4 & 2047 | in.get(5 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(5 + inPos) << 7 & 2047 | in.get(6 + inPos) >> 1 & 127;
         out[5 + outPos] = in.get(6 + inPos) << 10 & 2047 | in.get(7 + inPos) << 2 & 1023 | in.get(8 + inPos) >> 6 & 3;
         out[6 + outPos] = in.get(8 + inPos) << 5 & 2047 | in.get(9 + inPos) >> 3 & 31;
         out[7 + outPos] = in.get(9 + inPos) << 8 & 2047 | in.get(10 + inPos) & 255;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 3 & 2047 | in[1 + inPos] >> 5 & 7;
         out[1 + outPos] = in[1 + inPos] << 6 & 2047 | in[2 + inPos] >> 2 & 63;
         out[2 + outPos] = in[2 + inPos] << 9 & 2047 | in[3 + inPos] << 1 & 511 | in[4 + inPos] >> 7 & 1;
         out[3 + outPos] = in[4 + inPos] << 4 & 2047 | in[5 + inPos] >> 4 & 15;
         out[4 + outPos] = in[5 + inPos] << 7 & 2047 | in[6 + inPos] >> 1 & 127;
         out[5 + outPos] = in[6 + inPos] << 10 & 2047 | in[7 + inPos] << 2 & 1023 | in[8 + inPos] >> 6 & 3;
         out[6 + outPos] = in[8 + inPos] << 5 & 2047 | in[9 + inPos] >> 3 & 31;
         out[7 + outPos] = in[9 + inPos] << 8 & 2047 | in[10 + inPos] & 255;
         out[8 + outPos] = in[11 + inPos] << 3 & 2047 | in[12 + inPos] >> 5 & 7;
         out[9 + outPos] = in[12 + inPos] << 6 & 2047 | in[13 + inPos] >> 2 & 63;
         out[10 + outPos] = in[13 + inPos] << 9 & 2047 | in[14 + inPos] << 1 & 511 | in[15 + inPos] >> 7 & 1;
         out[11 + outPos] = in[15 + inPos] << 4 & 2047 | in[16 + inPos] >> 4 & 15;
         out[12 + outPos] = in[16 + inPos] << 7 & 2047 | in[17 + inPos] >> 1 & 127;
         out[13 + outPos] = in[17 + inPos] << 10 & 2047 | in[18 + inPos] << 2 & 1023 | in[19 + inPos] >> 6 & 3;
         out[14 + outPos] = in[19 + inPos] << 5 & 2047 | in[20 + inPos] >> 3 & 31;
         out[15 + outPos] = in[20 + inPos] << 8 & 2047 | in[21 + inPos] & 255;
         out[16 + outPos] = in[22 + inPos] << 3 & 2047 | in[23 + inPos] >> 5 & 7;
         out[17 + outPos] = in[23 + inPos] << 6 & 2047 | in[24 + inPos] >> 2 & 63;
         out[18 + outPos] = in[24 + inPos] << 9 & 2047 | in[25 + inPos] << 1 & 511 | in[26 + inPos] >> 7 & 1;
         out[19 + outPos] = in[26 + inPos] << 4 & 2047 | in[27 + inPos] >> 4 & 15;
         out[20 + outPos] = in[27 + inPos] << 7 & 2047 | in[28 + inPos] >> 1 & 127;
         out[21 + outPos] = in[28 + inPos] << 10 & 2047 | in[29 + inPos] << 2 & 1023 | in[30 + inPos] >> 6 & 3;
         out[22 + outPos] = in[30 + inPos] << 5 & 2047 | in[31 + inPos] >> 3 & 31;
         out[23 + outPos] = in[31 + inPos] << 8 & 2047 | in[32 + inPos] & 255;
         out[24 + outPos] = in[33 + inPos] << 3 & 2047 | in[34 + inPos] >> 5 & 7;
         out[25 + outPos] = in[34 + inPos] << 6 & 2047 | in[35 + inPos] >> 2 & 63;
         out[26 + outPos] = in[35 + inPos] << 9 & 2047 | in[36 + inPos] << 1 & 511 | in[37 + inPos] >> 7 & 1;
         out[27 + outPos] = in[37 + inPos] << 4 & 2047 | in[38 + inPos] >> 4 & 15;
         out[28 + outPos] = in[38 + inPos] << 7 & 2047 | in[39 + inPos] >> 1 & 127;
         out[29 + outPos] = in[39 + inPos] << 10 & 2047 | in[40 + inPos] << 2 & 1023 | in[41 + inPos] >> 6 & 3;
         out[30 + outPos] = in[41 + inPos] << 5 & 2047 | in[42 + inPos] >> 3 & 31;
         out[31 + outPos] = in[42 + inPos] << 8 & 2047 | in[43 + inPos] & 255;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 3 & 2047 | in.get(1 + inPos) >> 5 & 7;
         out[1 + outPos] = in.get(1 + inPos) << 6 & 2047 | in.get(2 + inPos) >> 2 & 63;
         out[2 + outPos] = in.get(2 + inPos) << 9 & 2047 | in.get(3 + inPos) << 1 & 511 | in.get(4 + inPos) >> 7 & 1;
         out[3 + outPos] = in.get(4 + inPos) << 4 & 2047 | in.get(5 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(5 + inPos) << 7 & 2047 | in.get(6 + inPos) >> 1 & 127;
         out[5 + outPos] = in.get(6 + inPos) << 10 & 2047 | in.get(7 + inPos) << 2 & 1023 | in.get(8 + inPos) >> 6 & 3;
         out[6 + outPos] = in.get(8 + inPos) << 5 & 2047 | in.get(9 + inPos) >> 3 & 31;
         out[7 + outPos] = in.get(9 + inPos) << 8 & 2047 | in.get(10 + inPos) & 255;
         out[8 + outPos] = in.get(11 + inPos) << 3 & 2047 | in.get(12 + inPos) >> 5 & 7;
         out[9 + outPos] = in.get(12 + inPos) << 6 & 2047 | in.get(13 + inPos) >> 2 & 63;
         out[10 + outPos] = in.get(13 + inPos) << 9 & 2047 | in.get(14 + inPos) << 1 & 511 | in.get(15 + inPos) >> 7 & 1;
         out[11 + outPos] = in.get(15 + inPos) << 4 & 2047 | in.get(16 + inPos) >> 4 & 15;
         out[12 + outPos] = in.get(16 + inPos) << 7 & 2047 | in.get(17 + inPos) >> 1 & 127;
         out[13 + outPos] = in.get(17 + inPos) << 10 & 2047 | in.get(18 + inPos) << 2 & 1023 | in.get(19 + inPos) >> 6 & 3;
         out[14 + outPos] = in.get(19 + inPos) << 5 & 2047 | in.get(20 + inPos) >> 3 & 31;
         out[15 + outPos] = in.get(20 + inPos) << 8 & 2047 | in.get(21 + inPos) & 255;
         out[16 + outPos] = in.get(22 + inPos) << 3 & 2047 | in.get(23 + inPos) >> 5 & 7;
         out[17 + outPos] = in.get(23 + inPos) << 6 & 2047 | in.get(24 + inPos) >> 2 & 63;
         out[18 + outPos] = in.get(24 + inPos) << 9 & 2047 | in.get(25 + inPos) << 1 & 511 | in.get(26 + inPos) >> 7 & 1;
         out[19 + outPos] = in.get(26 + inPos) << 4 & 2047 | in.get(27 + inPos) >> 4 & 15;
         out[20 + outPos] = in.get(27 + inPos) << 7 & 2047 | in.get(28 + inPos) >> 1 & 127;
         out[21 + outPos] = in.get(28 + inPos) << 10 & 2047 | in.get(29 + inPos) << 2 & 1023 | in.get(30 + inPos) >> 6 & 3;
         out[22 + outPos] = in.get(30 + inPos) << 5 & 2047 | in.get(31 + inPos) >> 3 & 31;
         out[23 + outPos] = in.get(31 + inPos) << 8 & 2047 | in.get(32 + inPos) & 255;
         out[24 + outPos] = in.get(33 + inPos) << 3 & 2047 | in.get(34 + inPos) >> 5 & 7;
         out[25 + outPos] = in.get(34 + inPos) << 6 & 2047 | in.get(35 + inPos) >> 2 & 63;
         out[26 + outPos] = in.get(35 + inPos) << 9 & 2047 | in.get(36 + inPos) << 1 & 511 | in.get(37 + inPos) >> 7 & 1;
         out[27 + outPos] = in.get(37 + inPos) << 4 & 2047 | in.get(38 + inPos) >> 4 & 15;
         out[28 + outPos] = in.get(38 + inPos) << 7 & 2047 | in.get(39 + inPos) >> 1 & 127;
         out[29 + outPos] = in.get(39 + inPos) << 10 & 2047 | in.get(40 + inPos) << 2 & 1023 | in.get(41 + inPos) >> 6 & 3;
         out[30 + outPos] = in.get(41 + inPos) << 5 & 2047 | in.get(42 + inPos) >> 3 & 31;
         out[31 + outPos] = in.get(42 + inPos) << 8 & 2047 | in.get(43 + inPos) & 255;
      }
   }

   private static final class Packer12 extends BytePacker {
      private Packer12() {
         super(12);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 4095) >>> 4 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 4095) << 4 | (in[1 + inPos] & 4095) >>> 8) & 255);
         out[2 + outPos] = (byte)(in[1 + inPos] & 4095 & 255);
         out[3 + outPos] = (byte)((in[2 + inPos] & 4095) >>> 4 & 255);
         out[4 + outPos] = (byte)(((in[2 + inPos] & 4095) << 4 | (in[3 + inPos] & 4095) >>> 8) & 255);
         out[5 + outPos] = (byte)(in[3 + inPos] & 4095 & 255);
         out[6 + outPos] = (byte)((in[4 + inPos] & 4095) >>> 4 & 255);
         out[7 + outPos] = (byte)(((in[4 + inPos] & 4095) << 4 | (in[5 + inPos] & 4095) >>> 8) & 255);
         out[8 + outPos] = (byte)(in[5 + inPos] & 4095 & 255);
         out[9 + outPos] = (byte)((in[6 + inPos] & 4095) >>> 4 & 255);
         out[10 + outPos] = (byte)(((in[6 + inPos] & 4095) << 4 | (in[7 + inPos] & 4095) >>> 8) & 255);
         out[11 + outPos] = (byte)(in[7 + inPos] & 4095 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 4095) >>> 4 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 4095) << 4 | (in[1 + inPos] & 4095) >>> 8) & 255);
         out[2 + outPos] = (byte)(in[1 + inPos] & 4095 & 255);
         out[3 + outPos] = (byte)((in[2 + inPos] & 4095) >>> 4 & 255);
         out[4 + outPos] = (byte)(((in[2 + inPos] & 4095) << 4 | (in[3 + inPos] & 4095) >>> 8) & 255);
         out[5 + outPos] = (byte)(in[3 + inPos] & 4095 & 255);
         out[6 + outPos] = (byte)((in[4 + inPos] & 4095) >>> 4 & 255);
         out[7 + outPos] = (byte)(((in[4 + inPos] & 4095) << 4 | (in[5 + inPos] & 4095) >>> 8) & 255);
         out[8 + outPos] = (byte)(in[5 + inPos] & 4095 & 255);
         out[9 + outPos] = (byte)((in[6 + inPos] & 4095) >>> 4 & 255);
         out[10 + outPos] = (byte)(((in[6 + inPos] & 4095) << 4 | (in[7 + inPos] & 4095) >>> 8) & 255);
         out[11 + outPos] = (byte)(in[7 + inPos] & 4095 & 255);
         out[12 + outPos] = (byte)((in[8 + inPos] & 4095) >>> 4 & 255);
         out[13 + outPos] = (byte)(((in[8 + inPos] & 4095) << 4 | (in[9 + inPos] & 4095) >>> 8) & 255);
         out[14 + outPos] = (byte)(in[9 + inPos] & 4095 & 255);
         out[15 + outPos] = (byte)((in[10 + inPos] & 4095) >>> 4 & 255);
         out[16 + outPos] = (byte)(((in[10 + inPos] & 4095) << 4 | (in[11 + inPos] & 4095) >>> 8) & 255);
         out[17 + outPos] = (byte)(in[11 + inPos] & 4095 & 255);
         out[18 + outPos] = (byte)((in[12 + inPos] & 4095) >>> 4 & 255);
         out[19 + outPos] = (byte)(((in[12 + inPos] & 4095) << 4 | (in[13 + inPos] & 4095) >>> 8) & 255);
         out[20 + outPos] = (byte)(in[13 + inPos] & 4095 & 255);
         out[21 + outPos] = (byte)((in[14 + inPos] & 4095) >>> 4 & 255);
         out[22 + outPos] = (byte)(((in[14 + inPos] & 4095) << 4 | (in[15 + inPos] & 4095) >>> 8) & 255);
         out[23 + outPos] = (byte)(in[15 + inPos] & 4095 & 255);
         out[24 + outPos] = (byte)((in[16 + inPos] & 4095) >>> 4 & 255);
         out[25 + outPos] = (byte)(((in[16 + inPos] & 4095) << 4 | (in[17 + inPos] & 4095) >>> 8) & 255);
         out[26 + outPos] = (byte)(in[17 + inPos] & 4095 & 255);
         out[27 + outPos] = (byte)((in[18 + inPos] & 4095) >>> 4 & 255);
         out[28 + outPos] = (byte)(((in[18 + inPos] & 4095) << 4 | (in[19 + inPos] & 4095) >>> 8) & 255);
         out[29 + outPos] = (byte)(in[19 + inPos] & 4095 & 255);
         out[30 + outPos] = (byte)((in[20 + inPos] & 4095) >>> 4 & 255);
         out[31 + outPos] = (byte)(((in[20 + inPos] & 4095) << 4 | (in[21 + inPos] & 4095) >>> 8) & 255);
         out[32 + outPos] = (byte)(in[21 + inPos] & 4095 & 255);
         out[33 + outPos] = (byte)((in[22 + inPos] & 4095) >>> 4 & 255);
         out[34 + outPos] = (byte)(((in[22 + inPos] & 4095) << 4 | (in[23 + inPos] & 4095) >>> 8) & 255);
         out[35 + outPos] = (byte)(in[23 + inPos] & 4095 & 255);
         out[36 + outPos] = (byte)((in[24 + inPos] & 4095) >>> 4 & 255);
         out[37 + outPos] = (byte)(((in[24 + inPos] & 4095) << 4 | (in[25 + inPos] & 4095) >>> 8) & 255);
         out[38 + outPos] = (byte)(in[25 + inPos] & 4095 & 255);
         out[39 + outPos] = (byte)((in[26 + inPos] & 4095) >>> 4 & 255);
         out[40 + outPos] = (byte)(((in[26 + inPos] & 4095) << 4 | (in[27 + inPos] & 4095) >>> 8) & 255);
         out[41 + outPos] = (byte)(in[27 + inPos] & 4095 & 255);
         out[42 + outPos] = (byte)((in[28 + inPos] & 4095) >>> 4 & 255);
         out[43 + outPos] = (byte)(((in[28 + inPos] & 4095) << 4 | (in[29 + inPos] & 4095) >>> 8) & 255);
         out[44 + outPos] = (byte)(in[29 + inPos] & 4095 & 255);
         out[45 + outPos] = (byte)((in[30 + inPos] & 4095) >>> 4 & 255);
         out[46 + outPos] = (byte)(((in[30 + inPos] & 4095) << 4 | (in[31 + inPos] & 4095) >>> 8) & 255);
         out[47 + outPos] = (byte)(in[31 + inPos] & 4095 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 4 & 4095 | in[1 + inPos] >> 4 & 15;
         out[1 + outPos] = in[1 + inPos] << 8 & 4095 | in[2 + inPos] & 255;
         out[2 + outPos] = in[3 + inPos] << 4 & 4095 | in[4 + inPos] >> 4 & 15;
         out[3 + outPos] = in[4 + inPos] << 8 & 4095 | in[5 + inPos] & 255;
         out[4 + outPos] = in[6 + inPos] << 4 & 4095 | in[7 + inPos] >> 4 & 15;
         out[5 + outPos] = in[7 + inPos] << 8 & 4095 | in[8 + inPos] & 255;
         out[6 + outPos] = in[9 + inPos] << 4 & 4095 | in[10 + inPos] >> 4 & 15;
         out[7 + outPos] = in[10 + inPos] << 8 & 4095 | in[11 + inPos] & 255;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 4 & 4095 | in.get(1 + inPos) >> 4 & 15;
         out[1 + outPos] = in.get(1 + inPos) << 8 & 4095 | in.get(2 + inPos) & 255;
         out[2 + outPos] = in.get(3 + inPos) << 4 & 4095 | in.get(4 + inPos) >> 4 & 15;
         out[3 + outPos] = in.get(4 + inPos) << 8 & 4095 | in.get(5 + inPos) & 255;
         out[4 + outPos] = in.get(6 + inPos) << 4 & 4095 | in.get(7 + inPos) >> 4 & 15;
         out[5 + outPos] = in.get(7 + inPos) << 8 & 4095 | in.get(8 + inPos) & 255;
         out[6 + outPos] = in.get(9 + inPos) << 4 & 4095 | in.get(10 + inPos) >> 4 & 15;
         out[7 + outPos] = in.get(10 + inPos) << 8 & 4095 | in.get(11 + inPos) & 255;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 4 & 4095 | in[1 + inPos] >> 4 & 15;
         out[1 + outPos] = in[1 + inPos] << 8 & 4095 | in[2 + inPos] & 255;
         out[2 + outPos] = in[3 + inPos] << 4 & 4095 | in[4 + inPos] >> 4 & 15;
         out[3 + outPos] = in[4 + inPos] << 8 & 4095 | in[5 + inPos] & 255;
         out[4 + outPos] = in[6 + inPos] << 4 & 4095 | in[7 + inPos] >> 4 & 15;
         out[5 + outPos] = in[7 + inPos] << 8 & 4095 | in[8 + inPos] & 255;
         out[6 + outPos] = in[9 + inPos] << 4 & 4095 | in[10 + inPos] >> 4 & 15;
         out[7 + outPos] = in[10 + inPos] << 8 & 4095 | in[11 + inPos] & 255;
         out[8 + outPos] = in[12 + inPos] << 4 & 4095 | in[13 + inPos] >> 4 & 15;
         out[9 + outPos] = in[13 + inPos] << 8 & 4095 | in[14 + inPos] & 255;
         out[10 + outPos] = in[15 + inPos] << 4 & 4095 | in[16 + inPos] >> 4 & 15;
         out[11 + outPos] = in[16 + inPos] << 8 & 4095 | in[17 + inPos] & 255;
         out[12 + outPos] = in[18 + inPos] << 4 & 4095 | in[19 + inPos] >> 4 & 15;
         out[13 + outPos] = in[19 + inPos] << 8 & 4095 | in[20 + inPos] & 255;
         out[14 + outPos] = in[21 + inPos] << 4 & 4095 | in[22 + inPos] >> 4 & 15;
         out[15 + outPos] = in[22 + inPos] << 8 & 4095 | in[23 + inPos] & 255;
         out[16 + outPos] = in[24 + inPos] << 4 & 4095 | in[25 + inPos] >> 4 & 15;
         out[17 + outPos] = in[25 + inPos] << 8 & 4095 | in[26 + inPos] & 255;
         out[18 + outPos] = in[27 + inPos] << 4 & 4095 | in[28 + inPos] >> 4 & 15;
         out[19 + outPos] = in[28 + inPos] << 8 & 4095 | in[29 + inPos] & 255;
         out[20 + outPos] = in[30 + inPos] << 4 & 4095 | in[31 + inPos] >> 4 & 15;
         out[21 + outPos] = in[31 + inPos] << 8 & 4095 | in[32 + inPos] & 255;
         out[22 + outPos] = in[33 + inPos] << 4 & 4095 | in[34 + inPos] >> 4 & 15;
         out[23 + outPos] = in[34 + inPos] << 8 & 4095 | in[35 + inPos] & 255;
         out[24 + outPos] = in[36 + inPos] << 4 & 4095 | in[37 + inPos] >> 4 & 15;
         out[25 + outPos] = in[37 + inPos] << 8 & 4095 | in[38 + inPos] & 255;
         out[26 + outPos] = in[39 + inPos] << 4 & 4095 | in[40 + inPos] >> 4 & 15;
         out[27 + outPos] = in[40 + inPos] << 8 & 4095 | in[41 + inPos] & 255;
         out[28 + outPos] = in[42 + inPos] << 4 & 4095 | in[43 + inPos] >> 4 & 15;
         out[29 + outPos] = in[43 + inPos] << 8 & 4095 | in[44 + inPos] & 255;
         out[30 + outPos] = in[45 + inPos] << 4 & 4095 | in[46 + inPos] >> 4 & 15;
         out[31 + outPos] = in[46 + inPos] << 8 & 4095 | in[47 + inPos] & 255;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 4 & 4095 | in.get(1 + inPos) >> 4 & 15;
         out[1 + outPos] = in.get(1 + inPos) << 8 & 4095 | in.get(2 + inPos) & 255;
         out[2 + outPos] = in.get(3 + inPos) << 4 & 4095 | in.get(4 + inPos) >> 4 & 15;
         out[3 + outPos] = in.get(4 + inPos) << 8 & 4095 | in.get(5 + inPos) & 255;
         out[4 + outPos] = in.get(6 + inPos) << 4 & 4095 | in.get(7 + inPos) >> 4 & 15;
         out[5 + outPos] = in.get(7 + inPos) << 8 & 4095 | in.get(8 + inPos) & 255;
         out[6 + outPos] = in.get(9 + inPos) << 4 & 4095 | in.get(10 + inPos) >> 4 & 15;
         out[7 + outPos] = in.get(10 + inPos) << 8 & 4095 | in.get(11 + inPos) & 255;
         out[8 + outPos] = in.get(12 + inPos) << 4 & 4095 | in.get(13 + inPos) >> 4 & 15;
         out[9 + outPos] = in.get(13 + inPos) << 8 & 4095 | in.get(14 + inPos) & 255;
         out[10 + outPos] = in.get(15 + inPos) << 4 & 4095 | in.get(16 + inPos) >> 4 & 15;
         out[11 + outPos] = in.get(16 + inPos) << 8 & 4095 | in.get(17 + inPos) & 255;
         out[12 + outPos] = in.get(18 + inPos) << 4 & 4095 | in.get(19 + inPos) >> 4 & 15;
         out[13 + outPos] = in.get(19 + inPos) << 8 & 4095 | in.get(20 + inPos) & 255;
         out[14 + outPos] = in.get(21 + inPos) << 4 & 4095 | in.get(22 + inPos) >> 4 & 15;
         out[15 + outPos] = in.get(22 + inPos) << 8 & 4095 | in.get(23 + inPos) & 255;
         out[16 + outPos] = in.get(24 + inPos) << 4 & 4095 | in.get(25 + inPos) >> 4 & 15;
         out[17 + outPos] = in.get(25 + inPos) << 8 & 4095 | in.get(26 + inPos) & 255;
         out[18 + outPos] = in.get(27 + inPos) << 4 & 4095 | in.get(28 + inPos) >> 4 & 15;
         out[19 + outPos] = in.get(28 + inPos) << 8 & 4095 | in.get(29 + inPos) & 255;
         out[20 + outPos] = in.get(30 + inPos) << 4 & 4095 | in.get(31 + inPos) >> 4 & 15;
         out[21 + outPos] = in.get(31 + inPos) << 8 & 4095 | in.get(32 + inPos) & 255;
         out[22 + outPos] = in.get(33 + inPos) << 4 & 4095 | in.get(34 + inPos) >> 4 & 15;
         out[23 + outPos] = in.get(34 + inPos) << 8 & 4095 | in.get(35 + inPos) & 255;
         out[24 + outPos] = in.get(36 + inPos) << 4 & 4095 | in.get(37 + inPos) >> 4 & 15;
         out[25 + outPos] = in.get(37 + inPos) << 8 & 4095 | in.get(38 + inPos) & 255;
         out[26 + outPos] = in.get(39 + inPos) << 4 & 4095 | in.get(40 + inPos) >> 4 & 15;
         out[27 + outPos] = in.get(40 + inPos) << 8 & 4095 | in.get(41 + inPos) & 255;
         out[28 + outPos] = in.get(42 + inPos) << 4 & 4095 | in.get(43 + inPos) >> 4 & 15;
         out[29 + outPos] = in.get(43 + inPos) << 8 & 4095 | in.get(44 + inPos) & 255;
         out[30 + outPos] = in.get(45 + inPos) << 4 & 4095 | in.get(46 + inPos) >> 4 & 15;
         out[31 + outPos] = in.get(46 + inPos) << 8 & 4095 | in.get(47 + inPos) & 255;
      }
   }

   private static final class Packer13 extends BytePacker {
      private Packer13() {
         super(13);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 8191) >>> 5 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 8191) << 3 | (in[1 + inPos] & 8191) >>> 10) & 255);
         out[2 + outPos] = (byte)((in[1 + inPos] & 8191) >>> 2 & 255);
         out[3 + outPos] = (byte)(((in[1 + inPos] & 8191) << 6 | (in[2 + inPos] & 8191) >>> 7) & 255);
         out[4 + outPos] = (byte)(((in[2 + inPos] & 8191) << 1 | (in[3 + inPos] & 8191) >>> 12) & 255);
         out[5 + outPos] = (byte)((in[3 + inPos] & 8191) >>> 4 & 255);
         out[6 + outPos] = (byte)(((in[3 + inPos] & 8191) << 4 | (in[4 + inPos] & 8191) >>> 9) & 255);
         out[7 + outPos] = (byte)((in[4 + inPos] & 8191) >>> 1 & 255);
         out[8 + outPos] = (byte)(((in[4 + inPos] & 8191) << 7 | (in[5 + inPos] & 8191) >>> 6) & 255);
         out[9 + outPos] = (byte)(((in[5 + inPos] & 8191) << 2 | (in[6 + inPos] & 8191) >>> 11) & 255);
         out[10 + outPos] = (byte)((in[6 + inPos] & 8191) >>> 3 & 255);
         out[11 + outPos] = (byte)(((in[6 + inPos] & 8191) << 5 | (in[7 + inPos] & 8191) >>> 8) & 255);
         out[12 + outPos] = (byte)(in[7 + inPos] & 8191 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 8191) >>> 5 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 8191) << 3 | (in[1 + inPos] & 8191) >>> 10) & 255);
         out[2 + outPos] = (byte)((in[1 + inPos] & 8191) >>> 2 & 255);
         out[3 + outPos] = (byte)(((in[1 + inPos] & 8191) << 6 | (in[2 + inPos] & 8191) >>> 7) & 255);
         out[4 + outPos] = (byte)(((in[2 + inPos] & 8191) << 1 | (in[3 + inPos] & 8191) >>> 12) & 255);
         out[5 + outPos] = (byte)((in[3 + inPos] & 8191) >>> 4 & 255);
         out[6 + outPos] = (byte)(((in[3 + inPos] & 8191) << 4 | (in[4 + inPos] & 8191) >>> 9) & 255);
         out[7 + outPos] = (byte)((in[4 + inPos] & 8191) >>> 1 & 255);
         out[8 + outPos] = (byte)(((in[4 + inPos] & 8191) << 7 | (in[5 + inPos] & 8191) >>> 6) & 255);
         out[9 + outPos] = (byte)(((in[5 + inPos] & 8191) << 2 | (in[6 + inPos] & 8191) >>> 11) & 255);
         out[10 + outPos] = (byte)((in[6 + inPos] & 8191) >>> 3 & 255);
         out[11 + outPos] = (byte)(((in[6 + inPos] & 8191) << 5 | (in[7 + inPos] & 8191) >>> 8) & 255);
         out[12 + outPos] = (byte)(in[7 + inPos] & 8191 & 255);
         out[13 + outPos] = (byte)((in[8 + inPos] & 8191) >>> 5 & 255);
         out[14 + outPos] = (byte)(((in[8 + inPos] & 8191) << 3 | (in[9 + inPos] & 8191) >>> 10) & 255);
         out[15 + outPos] = (byte)((in[9 + inPos] & 8191) >>> 2 & 255);
         out[16 + outPos] = (byte)(((in[9 + inPos] & 8191) << 6 | (in[10 + inPos] & 8191) >>> 7) & 255);
         out[17 + outPos] = (byte)(((in[10 + inPos] & 8191) << 1 | (in[11 + inPos] & 8191) >>> 12) & 255);
         out[18 + outPos] = (byte)((in[11 + inPos] & 8191) >>> 4 & 255);
         out[19 + outPos] = (byte)(((in[11 + inPos] & 8191) << 4 | (in[12 + inPos] & 8191) >>> 9) & 255);
         out[20 + outPos] = (byte)((in[12 + inPos] & 8191) >>> 1 & 255);
         out[21 + outPos] = (byte)(((in[12 + inPos] & 8191) << 7 | (in[13 + inPos] & 8191) >>> 6) & 255);
         out[22 + outPos] = (byte)(((in[13 + inPos] & 8191) << 2 | (in[14 + inPos] & 8191) >>> 11) & 255);
         out[23 + outPos] = (byte)((in[14 + inPos] & 8191) >>> 3 & 255);
         out[24 + outPos] = (byte)(((in[14 + inPos] & 8191) << 5 | (in[15 + inPos] & 8191) >>> 8) & 255);
         out[25 + outPos] = (byte)(in[15 + inPos] & 8191 & 255);
         out[26 + outPos] = (byte)((in[16 + inPos] & 8191) >>> 5 & 255);
         out[27 + outPos] = (byte)(((in[16 + inPos] & 8191) << 3 | (in[17 + inPos] & 8191) >>> 10) & 255);
         out[28 + outPos] = (byte)((in[17 + inPos] & 8191) >>> 2 & 255);
         out[29 + outPos] = (byte)(((in[17 + inPos] & 8191) << 6 | (in[18 + inPos] & 8191) >>> 7) & 255);
         out[30 + outPos] = (byte)(((in[18 + inPos] & 8191) << 1 | (in[19 + inPos] & 8191) >>> 12) & 255);
         out[31 + outPos] = (byte)((in[19 + inPos] & 8191) >>> 4 & 255);
         out[32 + outPos] = (byte)(((in[19 + inPos] & 8191) << 4 | (in[20 + inPos] & 8191) >>> 9) & 255);
         out[33 + outPos] = (byte)((in[20 + inPos] & 8191) >>> 1 & 255);
         out[34 + outPos] = (byte)(((in[20 + inPos] & 8191) << 7 | (in[21 + inPos] & 8191) >>> 6) & 255);
         out[35 + outPos] = (byte)(((in[21 + inPos] & 8191) << 2 | (in[22 + inPos] & 8191) >>> 11) & 255);
         out[36 + outPos] = (byte)((in[22 + inPos] & 8191) >>> 3 & 255);
         out[37 + outPos] = (byte)(((in[22 + inPos] & 8191) << 5 | (in[23 + inPos] & 8191) >>> 8) & 255);
         out[38 + outPos] = (byte)(in[23 + inPos] & 8191 & 255);
         out[39 + outPos] = (byte)((in[24 + inPos] & 8191) >>> 5 & 255);
         out[40 + outPos] = (byte)(((in[24 + inPos] & 8191) << 3 | (in[25 + inPos] & 8191) >>> 10) & 255);
         out[41 + outPos] = (byte)((in[25 + inPos] & 8191) >>> 2 & 255);
         out[42 + outPos] = (byte)(((in[25 + inPos] & 8191) << 6 | (in[26 + inPos] & 8191) >>> 7) & 255);
         out[43 + outPos] = (byte)(((in[26 + inPos] & 8191) << 1 | (in[27 + inPos] & 8191) >>> 12) & 255);
         out[44 + outPos] = (byte)((in[27 + inPos] & 8191) >>> 4 & 255);
         out[45 + outPos] = (byte)(((in[27 + inPos] & 8191) << 4 | (in[28 + inPos] & 8191) >>> 9) & 255);
         out[46 + outPos] = (byte)((in[28 + inPos] & 8191) >>> 1 & 255);
         out[47 + outPos] = (byte)(((in[28 + inPos] & 8191) << 7 | (in[29 + inPos] & 8191) >>> 6) & 255);
         out[48 + outPos] = (byte)(((in[29 + inPos] & 8191) << 2 | (in[30 + inPos] & 8191) >>> 11) & 255);
         out[49 + outPos] = (byte)((in[30 + inPos] & 8191) >>> 3 & 255);
         out[50 + outPos] = (byte)(((in[30 + inPos] & 8191) << 5 | (in[31 + inPos] & 8191) >>> 8) & 255);
         out[51 + outPos] = (byte)(in[31 + inPos] & 8191 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 5 & 8191 | in[1 + inPos] >> 3 & 31;
         out[1 + outPos] = in[1 + inPos] << 10 & 8191 | in[2 + inPos] << 2 & 1023 | in[3 + inPos] >> 6 & 3;
         out[2 + outPos] = in[3 + inPos] << 7 & 8191 | in[4 + inPos] >> 1 & 127;
         out[3 + outPos] = in[4 + inPos] << 12 & 8191 | in[5 + inPos] << 4 & 4095 | in[6 + inPos] >> 4 & 15;
         out[4 + outPos] = in[6 + inPos] << 9 & 8191 | in[7 + inPos] << 1 & 511 | in[8 + inPos] >> 7 & 1;
         out[5 + outPos] = in[8 + inPos] << 6 & 8191 | in[9 + inPos] >> 2 & 63;
         out[6 + outPos] = in[9 + inPos] << 11 & 8191 | in[10 + inPos] << 3 & 2047 | in[11 + inPos] >> 5 & 7;
         out[7 + outPos] = in[11 + inPos] << 8 & 8191 | in[12 + inPos] & 255;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 5 & 8191 | in.get(1 + inPos) >> 3 & 31;
         out[1 + outPos] = in.get(1 + inPos) << 10 & 8191 | in.get(2 + inPos) << 2 & 1023 | in.get(3 + inPos) >> 6 & 3;
         out[2 + outPos] = in.get(3 + inPos) << 7 & 8191 | in.get(4 + inPos) >> 1 & 127;
         out[3 + outPos] = in.get(4 + inPos) << 12 & 8191 | in.get(5 + inPos) << 4 & 4095 | in.get(6 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(6 + inPos) << 9 & 8191 | in.get(7 + inPos) << 1 & 511 | in.get(8 + inPos) >> 7 & 1;
         out[5 + outPos] = in.get(8 + inPos) << 6 & 8191 | in.get(9 + inPos) >> 2 & 63;
         out[6 + outPos] = in.get(9 + inPos) << 11 & 8191 | in.get(10 + inPos) << 3 & 2047 | in.get(11 + inPos) >> 5 & 7;
         out[7 + outPos] = in.get(11 + inPos) << 8 & 8191 | in.get(12 + inPos) & 255;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 5 & 8191 | in[1 + inPos] >> 3 & 31;
         out[1 + outPos] = in[1 + inPos] << 10 & 8191 | in[2 + inPos] << 2 & 1023 | in[3 + inPos] >> 6 & 3;
         out[2 + outPos] = in[3 + inPos] << 7 & 8191 | in[4 + inPos] >> 1 & 127;
         out[3 + outPos] = in[4 + inPos] << 12 & 8191 | in[5 + inPos] << 4 & 4095 | in[6 + inPos] >> 4 & 15;
         out[4 + outPos] = in[6 + inPos] << 9 & 8191 | in[7 + inPos] << 1 & 511 | in[8 + inPos] >> 7 & 1;
         out[5 + outPos] = in[8 + inPos] << 6 & 8191 | in[9 + inPos] >> 2 & 63;
         out[6 + outPos] = in[9 + inPos] << 11 & 8191 | in[10 + inPos] << 3 & 2047 | in[11 + inPos] >> 5 & 7;
         out[7 + outPos] = in[11 + inPos] << 8 & 8191 | in[12 + inPos] & 255;
         out[8 + outPos] = in[13 + inPos] << 5 & 8191 | in[14 + inPos] >> 3 & 31;
         out[9 + outPos] = in[14 + inPos] << 10 & 8191 | in[15 + inPos] << 2 & 1023 | in[16 + inPos] >> 6 & 3;
         out[10 + outPos] = in[16 + inPos] << 7 & 8191 | in[17 + inPos] >> 1 & 127;
         out[11 + outPos] = in[17 + inPos] << 12 & 8191 | in[18 + inPos] << 4 & 4095 | in[19 + inPos] >> 4 & 15;
         out[12 + outPos] = in[19 + inPos] << 9 & 8191 | in[20 + inPos] << 1 & 511 | in[21 + inPos] >> 7 & 1;
         out[13 + outPos] = in[21 + inPos] << 6 & 8191 | in[22 + inPos] >> 2 & 63;
         out[14 + outPos] = in[22 + inPos] << 11 & 8191 | in[23 + inPos] << 3 & 2047 | in[24 + inPos] >> 5 & 7;
         out[15 + outPos] = in[24 + inPos] << 8 & 8191 | in[25 + inPos] & 255;
         out[16 + outPos] = in[26 + inPos] << 5 & 8191 | in[27 + inPos] >> 3 & 31;
         out[17 + outPos] = in[27 + inPos] << 10 & 8191 | in[28 + inPos] << 2 & 1023 | in[29 + inPos] >> 6 & 3;
         out[18 + outPos] = in[29 + inPos] << 7 & 8191 | in[30 + inPos] >> 1 & 127;
         out[19 + outPos] = in[30 + inPos] << 12 & 8191 | in[31 + inPos] << 4 & 4095 | in[32 + inPos] >> 4 & 15;
         out[20 + outPos] = in[32 + inPos] << 9 & 8191 | in[33 + inPos] << 1 & 511 | in[34 + inPos] >> 7 & 1;
         out[21 + outPos] = in[34 + inPos] << 6 & 8191 | in[35 + inPos] >> 2 & 63;
         out[22 + outPos] = in[35 + inPos] << 11 & 8191 | in[36 + inPos] << 3 & 2047 | in[37 + inPos] >> 5 & 7;
         out[23 + outPos] = in[37 + inPos] << 8 & 8191 | in[38 + inPos] & 255;
         out[24 + outPos] = in[39 + inPos] << 5 & 8191 | in[40 + inPos] >> 3 & 31;
         out[25 + outPos] = in[40 + inPos] << 10 & 8191 | in[41 + inPos] << 2 & 1023 | in[42 + inPos] >> 6 & 3;
         out[26 + outPos] = in[42 + inPos] << 7 & 8191 | in[43 + inPos] >> 1 & 127;
         out[27 + outPos] = in[43 + inPos] << 12 & 8191 | in[44 + inPos] << 4 & 4095 | in[45 + inPos] >> 4 & 15;
         out[28 + outPos] = in[45 + inPos] << 9 & 8191 | in[46 + inPos] << 1 & 511 | in[47 + inPos] >> 7 & 1;
         out[29 + outPos] = in[47 + inPos] << 6 & 8191 | in[48 + inPos] >> 2 & 63;
         out[30 + outPos] = in[48 + inPos] << 11 & 8191 | in[49 + inPos] << 3 & 2047 | in[50 + inPos] >> 5 & 7;
         out[31 + outPos] = in[50 + inPos] << 8 & 8191 | in[51 + inPos] & 255;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 5 & 8191 | in.get(1 + inPos) >> 3 & 31;
         out[1 + outPos] = in.get(1 + inPos) << 10 & 8191 | in.get(2 + inPos) << 2 & 1023 | in.get(3 + inPos) >> 6 & 3;
         out[2 + outPos] = in.get(3 + inPos) << 7 & 8191 | in.get(4 + inPos) >> 1 & 127;
         out[3 + outPos] = in.get(4 + inPos) << 12 & 8191 | in.get(5 + inPos) << 4 & 4095 | in.get(6 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(6 + inPos) << 9 & 8191 | in.get(7 + inPos) << 1 & 511 | in.get(8 + inPos) >> 7 & 1;
         out[5 + outPos] = in.get(8 + inPos) << 6 & 8191 | in.get(9 + inPos) >> 2 & 63;
         out[6 + outPos] = in.get(9 + inPos) << 11 & 8191 | in.get(10 + inPos) << 3 & 2047 | in.get(11 + inPos) >> 5 & 7;
         out[7 + outPos] = in.get(11 + inPos) << 8 & 8191 | in.get(12 + inPos) & 255;
         out[8 + outPos] = in.get(13 + inPos) << 5 & 8191 | in.get(14 + inPos) >> 3 & 31;
         out[9 + outPos] = in.get(14 + inPos) << 10 & 8191 | in.get(15 + inPos) << 2 & 1023 | in.get(16 + inPos) >> 6 & 3;
         out[10 + outPos] = in.get(16 + inPos) << 7 & 8191 | in.get(17 + inPos) >> 1 & 127;
         out[11 + outPos] = in.get(17 + inPos) << 12 & 8191 | in.get(18 + inPos) << 4 & 4095 | in.get(19 + inPos) >> 4 & 15;
         out[12 + outPos] = in.get(19 + inPos) << 9 & 8191 | in.get(20 + inPos) << 1 & 511 | in.get(21 + inPos) >> 7 & 1;
         out[13 + outPos] = in.get(21 + inPos) << 6 & 8191 | in.get(22 + inPos) >> 2 & 63;
         out[14 + outPos] = in.get(22 + inPos) << 11 & 8191 | in.get(23 + inPos) << 3 & 2047 | in.get(24 + inPos) >> 5 & 7;
         out[15 + outPos] = in.get(24 + inPos) << 8 & 8191 | in.get(25 + inPos) & 255;
         out[16 + outPos] = in.get(26 + inPos) << 5 & 8191 | in.get(27 + inPos) >> 3 & 31;
         out[17 + outPos] = in.get(27 + inPos) << 10 & 8191 | in.get(28 + inPos) << 2 & 1023 | in.get(29 + inPos) >> 6 & 3;
         out[18 + outPos] = in.get(29 + inPos) << 7 & 8191 | in.get(30 + inPos) >> 1 & 127;
         out[19 + outPos] = in.get(30 + inPos) << 12 & 8191 | in.get(31 + inPos) << 4 & 4095 | in.get(32 + inPos) >> 4 & 15;
         out[20 + outPos] = in.get(32 + inPos) << 9 & 8191 | in.get(33 + inPos) << 1 & 511 | in.get(34 + inPos) >> 7 & 1;
         out[21 + outPos] = in.get(34 + inPos) << 6 & 8191 | in.get(35 + inPos) >> 2 & 63;
         out[22 + outPos] = in.get(35 + inPos) << 11 & 8191 | in.get(36 + inPos) << 3 & 2047 | in.get(37 + inPos) >> 5 & 7;
         out[23 + outPos] = in.get(37 + inPos) << 8 & 8191 | in.get(38 + inPos) & 255;
         out[24 + outPos] = in.get(39 + inPos) << 5 & 8191 | in.get(40 + inPos) >> 3 & 31;
         out[25 + outPos] = in.get(40 + inPos) << 10 & 8191 | in.get(41 + inPos) << 2 & 1023 | in.get(42 + inPos) >> 6 & 3;
         out[26 + outPos] = in.get(42 + inPos) << 7 & 8191 | in.get(43 + inPos) >> 1 & 127;
         out[27 + outPos] = in.get(43 + inPos) << 12 & 8191 | in.get(44 + inPos) << 4 & 4095 | in.get(45 + inPos) >> 4 & 15;
         out[28 + outPos] = in.get(45 + inPos) << 9 & 8191 | in.get(46 + inPos) << 1 & 511 | in.get(47 + inPos) >> 7 & 1;
         out[29 + outPos] = in.get(47 + inPos) << 6 & 8191 | in.get(48 + inPos) >> 2 & 63;
         out[30 + outPos] = in.get(48 + inPos) << 11 & 8191 | in.get(49 + inPos) << 3 & 2047 | in.get(50 + inPos) >> 5 & 7;
         out[31 + outPos] = in.get(50 + inPos) << 8 & 8191 | in.get(51 + inPos) & 255;
      }
   }

   private static final class Packer14 extends BytePacker {
      private Packer14() {
         super(14);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 16383) >>> 6 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 16383) << 2 | (in[1 + inPos] & 16383) >>> 12) & 255);
         out[2 + outPos] = (byte)((in[1 + inPos] & 16383) >>> 4 & 255);
         out[3 + outPos] = (byte)(((in[1 + inPos] & 16383) << 4 | (in[2 + inPos] & 16383) >>> 10) & 255);
         out[4 + outPos] = (byte)((in[2 + inPos] & 16383) >>> 2 & 255);
         out[5 + outPos] = (byte)(((in[2 + inPos] & 16383) << 6 | (in[3 + inPos] & 16383) >>> 8) & 255);
         out[6 + outPos] = (byte)(in[3 + inPos] & 16383 & 255);
         out[7 + outPos] = (byte)((in[4 + inPos] & 16383) >>> 6 & 255);
         out[8 + outPos] = (byte)(((in[4 + inPos] & 16383) << 2 | (in[5 + inPos] & 16383) >>> 12) & 255);
         out[9 + outPos] = (byte)((in[5 + inPos] & 16383) >>> 4 & 255);
         out[10 + outPos] = (byte)(((in[5 + inPos] & 16383) << 4 | (in[6 + inPos] & 16383) >>> 10) & 255);
         out[11 + outPos] = (byte)((in[6 + inPos] & 16383) >>> 2 & 255);
         out[12 + outPos] = (byte)(((in[6 + inPos] & 16383) << 6 | (in[7 + inPos] & 16383) >>> 8) & 255);
         out[13 + outPos] = (byte)(in[7 + inPos] & 16383 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 16383) >>> 6 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 16383) << 2 | (in[1 + inPos] & 16383) >>> 12) & 255);
         out[2 + outPos] = (byte)((in[1 + inPos] & 16383) >>> 4 & 255);
         out[3 + outPos] = (byte)(((in[1 + inPos] & 16383) << 4 | (in[2 + inPos] & 16383) >>> 10) & 255);
         out[4 + outPos] = (byte)((in[2 + inPos] & 16383) >>> 2 & 255);
         out[5 + outPos] = (byte)(((in[2 + inPos] & 16383) << 6 | (in[3 + inPos] & 16383) >>> 8) & 255);
         out[6 + outPos] = (byte)(in[3 + inPos] & 16383 & 255);
         out[7 + outPos] = (byte)((in[4 + inPos] & 16383) >>> 6 & 255);
         out[8 + outPos] = (byte)(((in[4 + inPos] & 16383) << 2 | (in[5 + inPos] & 16383) >>> 12) & 255);
         out[9 + outPos] = (byte)((in[5 + inPos] & 16383) >>> 4 & 255);
         out[10 + outPos] = (byte)(((in[5 + inPos] & 16383) << 4 | (in[6 + inPos] & 16383) >>> 10) & 255);
         out[11 + outPos] = (byte)((in[6 + inPos] & 16383) >>> 2 & 255);
         out[12 + outPos] = (byte)(((in[6 + inPos] & 16383) << 6 | (in[7 + inPos] & 16383) >>> 8) & 255);
         out[13 + outPos] = (byte)(in[7 + inPos] & 16383 & 255);
         out[14 + outPos] = (byte)((in[8 + inPos] & 16383) >>> 6 & 255);
         out[15 + outPos] = (byte)(((in[8 + inPos] & 16383) << 2 | (in[9 + inPos] & 16383) >>> 12) & 255);
         out[16 + outPos] = (byte)((in[9 + inPos] & 16383) >>> 4 & 255);
         out[17 + outPos] = (byte)(((in[9 + inPos] & 16383) << 4 | (in[10 + inPos] & 16383) >>> 10) & 255);
         out[18 + outPos] = (byte)((in[10 + inPos] & 16383) >>> 2 & 255);
         out[19 + outPos] = (byte)(((in[10 + inPos] & 16383) << 6 | (in[11 + inPos] & 16383) >>> 8) & 255);
         out[20 + outPos] = (byte)(in[11 + inPos] & 16383 & 255);
         out[21 + outPos] = (byte)((in[12 + inPos] & 16383) >>> 6 & 255);
         out[22 + outPos] = (byte)(((in[12 + inPos] & 16383) << 2 | (in[13 + inPos] & 16383) >>> 12) & 255);
         out[23 + outPos] = (byte)((in[13 + inPos] & 16383) >>> 4 & 255);
         out[24 + outPos] = (byte)(((in[13 + inPos] & 16383) << 4 | (in[14 + inPos] & 16383) >>> 10) & 255);
         out[25 + outPos] = (byte)((in[14 + inPos] & 16383) >>> 2 & 255);
         out[26 + outPos] = (byte)(((in[14 + inPos] & 16383) << 6 | (in[15 + inPos] & 16383) >>> 8) & 255);
         out[27 + outPos] = (byte)(in[15 + inPos] & 16383 & 255);
         out[28 + outPos] = (byte)((in[16 + inPos] & 16383) >>> 6 & 255);
         out[29 + outPos] = (byte)(((in[16 + inPos] & 16383) << 2 | (in[17 + inPos] & 16383) >>> 12) & 255);
         out[30 + outPos] = (byte)((in[17 + inPos] & 16383) >>> 4 & 255);
         out[31 + outPos] = (byte)(((in[17 + inPos] & 16383) << 4 | (in[18 + inPos] & 16383) >>> 10) & 255);
         out[32 + outPos] = (byte)((in[18 + inPos] & 16383) >>> 2 & 255);
         out[33 + outPos] = (byte)(((in[18 + inPos] & 16383) << 6 | (in[19 + inPos] & 16383) >>> 8) & 255);
         out[34 + outPos] = (byte)(in[19 + inPos] & 16383 & 255);
         out[35 + outPos] = (byte)((in[20 + inPos] & 16383) >>> 6 & 255);
         out[36 + outPos] = (byte)(((in[20 + inPos] & 16383) << 2 | (in[21 + inPos] & 16383) >>> 12) & 255);
         out[37 + outPos] = (byte)((in[21 + inPos] & 16383) >>> 4 & 255);
         out[38 + outPos] = (byte)(((in[21 + inPos] & 16383) << 4 | (in[22 + inPos] & 16383) >>> 10) & 255);
         out[39 + outPos] = (byte)((in[22 + inPos] & 16383) >>> 2 & 255);
         out[40 + outPos] = (byte)(((in[22 + inPos] & 16383) << 6 | (in[23 + inPos] & 16383) >>> 8) & 255);
         out[41 + outPos] = (byte)(in[23 + inPos] & 16383 & 255);
         out[42 + outPos] = (byte)((in[24 + inPos] & 16383) >>> 6 & 255);
         out[43 + outPos] = (byte)(((in[24 + inPos] & 16383) << 2 | (in[25 + inPos] & 16383) >>> 12) & 255);
         out[44 + outPos] = (byte)((in[25 + inPos] & 16383) >>> 4 & 255);
         out[45 + outPos] = (byte)(((in[25 + inPos] & 16383) << 4 | (in[26 + inPos] & 16383) >>> 10) & 255);
         out[46 + outPos] = (byte)((in[26 + inPos] & 16383) >>> 2 & 255);
         out[47 + outPos] = (byte)(((in[26 + inPos] & 16383) << 6 | (in[27 + inPos] & 16383) >>> 8) & 255);
         out[48 + outPos] = (byte)(in[27 + inPos] & 16383 & 255);
         out[49 + outPos] = (byte)((in[28 + inPos] & 16383) >>> 6 & 255);
         out[50 + outPos] = (byte)(((in[28 + inPos] & 16383) << 2 | (in[29 + inPos] & 16383) >>> 12) & 255);
         out[51 + outPos] = (byte)((in[29 + inPos] & 16383) >>> 4 & 255);
         out[52 + outPos] = (byte)(((in[29 + inPos] & 16383) << 4 | (in[30 + inPos] & 16383) >>> 10) & 255);
         out[53 + outPos] = (byte)((in[30 + inPos] & 16383) >>> 2 & 255);
         out[54 + outPos] = (byte)(((in[30 + inPos] & 16383) << 6 | (in[31 + inPos] & 16383) >>> 8) & 255);
         out[55 + outPos] = (byte)(in[31 + inPos] & 16383 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 6 & 16383 | in[1 + inPos] >> 2 & 63;
         out[1 + outPos] = in[1 + inPos] << 12 & 16383 | in[2 + inPos] << 4 & 4095 | in[3 + inPos] >> 4 & 15;
         out[2 + outPos] = in[3 + inPos] << 10 & 16383 | in[4 + inPos] << 2 & 1023 | in[5 + inPos] >> 6 & 3;
         out[3 + outPos] = in[5 + inPos] << 8 & 16383 | in[6 + inPos] & 255;
         out[4 + outPos] = in[7 + inPos] << 6 & 16383 | in[8 + inPos] >> 2 & 63;
         out[5 + outPos] = in[8 + inPos] << 12 & 16383 | in[9 + inPos] << 4 & 4095 | in[10 + inPos] >> 4 & 15;
         out[6 + outPos] = in[10 + inPos] << 10 & 16383 | in[11 + inPos] << 2 & 1023 | in[12 + inPos] >> 6 & 3;
         out[7 + outPos] = in[12 + inPos] << 8 & 16383 | in[13 + inPos] & 255;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 6 & 16383 | in.get(1 + inPos) >> 2 & 63;
         out[1 + outPos] = in.get(1 + inPos) << 12 & 16383 | in.get(2 + inPos) << 4 & 4095 | in.get(3 + inPos) >> 4 & 15;
         out[2 + outPos] = in.get(3 + inPos) << 10 & 16383 | in.get(4 + inPos) << 2 & 1023 | in.get(5 + inPos) >> 6 & 3;
         out[3 + outPos] = in.get(5 + inPos) << 8 & 16383 | in.get(6 + inPos) & 255;
         out[4 + outPos] = in.get(7 + inPos) << 6 & 16383 | in.get(8 + inPos) >> 2 & 63;
         out[5 + outPos] = in.get(8 + inPos) << 12 & 16383 | in.get(9 + inPos) << 4 & 4095 | in.get(10 + inPos) >> 4 & 15;
         out[6 + outPos] = in.get(10 + inPos) << 10 & 16383 | in.get(11 + inPos) << 2 & 1023 | in.get(12 + inPos) >> 6 & 3;
         out[7 + outPos] = in.get(12 + inPos) << 8 & 16383 | in.get(13 + inPos) & 255;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 6 & 16383 | in[1 + inPos] >> 2 & 63;
         out[1 + outPos] = in[1 + inPos] << 12 & 16383 | in[2 + inPos] << 4 & 4095 | in[3 + inPos] >> 4 & 15;
         out[2 + outPos] = in[3 + inPos] << 10 & 16383 | in[4 + inPos] << 2 & 1023 | in[5 + inPos] >> 6 & 3;
         out[3 + outPos] = in[5 + inPos] << 8 & 16383 | in[6 + inPos] & 255;
         out[4 + outPos] = in[7 + inPos] << 6 & 16383 | in[8 + inPos] >> 2 & 63;
         out[5 + outPos] = in[8 + inPos] << 12 & 16383 | in[9 + inPos] << 4 & 4095 | in[10 + inPos] >> 4 & 15;
         out[6 + outPos] = in[10 + inPos] << 10 & 16383 | in[11 + inPos] << 2 & 1023 | in[12 + inPos] >> 6 & 3;
         out[7 + outPos] = in[12 + inPos] << 8 & 16383 | in[13 + inPos] & 255;
         out[8 + outPos] = in[14 + inPos] << 6 & 16383 | in[15 + inPos] >> 2 & 63;
         out[9 + outPos] = in[15 + inPos] << 12 & 16383 | in[16 + inPos] << 4 & 4095 | in[17 + inPos] >> 4 & 15;
         out[10 + outPos] = in[17 + inPos] << 10 & 16383 | in[18 + inPos] << 2 & 1023 | in[19 + inPos] >> 6 & 3;
         out[11 + outPos] = in[19 + inPos] << 8 & 16383 | in[20 + inPos] & 255;
         out[12 + outPos] = in[21 + inPos] << 6 & 16383 | in[22 + inPos] >> 2 & 63;
         out[13 + outPos] = in[22 + inPos] << 12 & 16383 | in[23 + inPos] << 4 & 4095 | in[24 + inPos] >> 4 & 15;
         out[14 + outPos] = in[24 + inPos] << 10 & 16383 | in[25 + inPos] << 2 & 1023 | in[26 + inPos] >> 6 & 3;
         out[15 + outPos] = in[26 + inPos] << 8 & 16383 | in[27 + inPos] & 255;
         out[16 + outPos] = in[28 + inPos] << 6 & 16383 | in[29 + inPos] >> 2 & 63;
         out[17 + outPos] = in[29 + inPos] << 12 & 16383 | in[30 + inPos] << 4 & 4095 | in[31 + inPos] >> 4 & 15;
         out[18 + outPos] = in[31 + inPos] << 10 & 16383 | in[32 + inPos] << 2 & 1023 | in[33 + inPos] >> 6 & 3;
         out[19 + outPos] = in[33 + inPos] << 8 & 16383 | in[34 + inPos] & 255;
         out[20 + outPos] = in[35 + inPos] << 6 & 16383 | in[36 + inPos] >> 2 & 63;
         out[21 + outPos] = in[36 + inPos] << 12 & 16383 | in[37 + inPos] << 4 & 4095 | in[38 + inPos] >> 4 & 15;
         out[22 + outPos] = in[38 + inPos] << 10 & 16383 | in[39 + inPos] << 2 & 1023 | in[40 + inPos] >> 6 & 3;
         out[23 + outPos] = in[40 + inPos] << 8 & 16383 | in[41 + inPos] & 255;
         out[24 + outPos] = in[42 + inPos] << 6 & 16383 | in[43 + inPos] >> 2 & 63;
         out[25 + outPos] = in[43 + inPos] << 12 & 16383 | in[44 + inPos] << 4 & 4095 | in[45 + inPos] >> 4 & 15;
         out[26 + outPos] = in[45 + inPos] << 10 & 16383 | in[46 + inPos] << 2 & 1023 | in[47 + inPos] >> 6 & 3;
         out[27 + outPos] = in[47 + inPos] << 8 & 16383 | in[48 + inPos] & 255;
         out[28 + outPos] = in[49 + inPos] << 6 & 16383 | in[50 + inPos] >> 2 & 63;
         out[29 + outPos] = in[50 + inPos] << 12 & 16383 | in[51 + inPos] << 4 & 4095 | in[52 + inPos] >> 4 & 15;
         out[30 + outPos] = in[52 + inPos] << 10 & 16383 | in[53 + inPos] << 2 & 1023 | in[54 + inPos] >> 6 & 3;
         out[31 + outPos] = in[54 + inPos] << 8 & 16383 | in[55 + inPos] & 255;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 6 & 16383 | in.get(1 + inPos) >> 2 & 63;
         out[1 + outPos] = in.get(1 + inPos) << 12 & 16383 | in.get(2 + inPos) << 4 & 4095 | in.get(3 + inPos) >> 4 & 15;
         out[2 + outPos] = in.get(3 + inPos) << 10 & 16383 | in.get(4 + inPos) << 2 & 1023 | in.get(5 + inPos) >> 6 & 3;
         out[3 + outPos] = in.get(5 + inPos) << 8 & 16383 | in.get(6 + inPos) & 255;
         out[4 + outPos] = in.get(7 + inPos) << 6 & 16383 | in.get(8 + inPos) >> 2 & 63;
         out[5 + outPos] = in.get(8 + inPos) << 12 & 16383 | in.get(9 + inPos) << 4 & 4095 | in.get(10 + inPos) >> 4 & 15;
         out[6 + outPos] = in.get(10 + inPos) << 10 & 16383 | in.get(11 + inPos) << 2 & 1023 | in.get(12 + inPos) >> 6 & 3;
         out[7 + outPos] = in.get(12 + inPos) << 8 & 16383 | in.get(13 + inPos) & 255;
         out[8 + outPos] = in.get(14 + inPos) << 6 & 16383 | in.get(15 + inPos) >> 2 & 63;
         out[9 + outPos] = in.get(15 + inPos) << 12 & 16383 | in.get(16 + inPos) << 4 & 4095 | in.get(17 + inPos) >> 4 & 15;
         out[10 + outPos] = in.get(17 + inPos) << 10 & 16383 | in.get(18 + inPos) << 2 & 1023 | in.get(19 + inPos) >> 6 & 3;
         out[11 + outPos] = in.get(19 + inPos) << 8 & 16383 | in.get(20 + inPos) & 255;
         out[12 + outPos] = in.get(21 + inPos) << 6 & 16383 | in.get(22 + inPos) >> 2 & 63;
         out[13 + outPos] = in.get(22 + inPos) << 12 & 16383 | in.get(23 + inPos) << 4 & 4095 | in.get(24 + inPos) >> 4 & 15;
         out[14 + outPos] = in.get(24 + inPos) << 10 & 16383 | in.get(25 + inPos) << 2 & 1023 | in.get(26 + inPos) >> 6 & 3;
         out[15 + outPos] = in.get(26 + inPos) << 8 & 16383 | in.get(27 + inPos) & 255;
         out[16 + outPos] = in.get(28 + inPos) << 6 & 16383 | in.get(29 + inPos) >> 2 & 63;
         out[17 + outPos] = in.get(29 + inPos) << 12 & 16383 | in.get(30 + inPos) << 4 & 4095 | in.get(31 + inPos) >> 4 & 15;
         out[18 + outPos] = in.get(31 + inPos) << 10 & 16383 | in.get(32 + inPos) << 2 & 1023 | in.get(33 + inPos) >> 6 & 3;
         out[19 + outPos] = in.get(33 + inPos) << 8 & 16383 | in.get(34 + inPos) & 255;
         out[20 + outPos] = in.get(35 + inPos) << 6 & 16383 | in.get(36 + inPos) >> 2 & 63;
         out[21 + outPos] = in.get(36 + inPos) << 12 & 16383 | in.get(37 + inPos) << 4 & 4095 | in.get(38 + inPos) >> 4 & 15;
         out[22 + outPos] = in.get(38 + inPos) << 10 & 16383 | in.get(39 + inPos) << 2 & 1023 | in.get(40 + inPos) >> 6 & 3;
         out[23 + outPos] = in.get(40 + inPos) << 8 & 16383 | in.get(41 + inPos) & 255;
         out[24 + outPos] = in.get(42 + inPos) << 6 & 16383 | in.get(43 + inPos) >> 2 & 63;
         out[25 + outPos] = in.get(43 + inPos) << 12 & 16383 | in.get(44 + inPos) << 4 & 4095 | in.get(45 + inPos) >> 4 & 15;
         out[26 + outPos] = in.get(45 + inPos) << 10 & 16383 | in.get(46 + inPos) << 2 & 1023 | in.get(47 + inPos) >> 6 & 3;
         out[27 + outPos] = in.get(47 + inPos) << 8 & 16383 | in.get(48 + inPos) & 255;
         out[28 + outPos] = in.get(49 + inPos) << 6 & 16383 | in.get(50 + inPos) >> 2 & 63;
         out[29 + outPos] = in.get(50 + inPos) << 12 & 16383 | in.get(51 + inPos) << 4 & 4095 | in.get(52 + inPos) >> 4 & 15;
         out[30 + outPos] = in.get(52 + inPos) << 10 & 16383 | in.get(53 + inPos) << 2 & 1023 | in.get(54 + inPos) >> 6 & 3;
         out[31 + outPos] = in.get(54 + inPos) << 8 & 16383 | in.get(55 + inPos) & 255;
      }
   }

   private static final class Packer15 extends BytePacker {
      private Packer15() {
         super(15);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 32767) >>> 7 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 32767) << 1 | (in[1 + inPos] & 32767) >>> 14) & 255);
         out[2 + outPos] = (byte)((in[1 + inPos] & 32767) >>> 6 & 255);
         out[3 + outPos] = (byte)(((in[1 + inPos] & 32767) << 2 | (in[2 + inPos] & 32767) >>> 13) & 255);
         out[4 + outPos] = (byte)((in[2 + inPos] & 32767) >>> 5 & 255);
         out[5 + outPos] = (byte)(((in[2 + inPos] & 32767) << 3 | (in[3 + inPos] & 32767) >>> 12) & 255);
         out[6 + outPos] = (byte)((in[3 + inPos] & 32767) >>> 4 & 255);
         out[7 + outPos] = (byte)(((in[3 + inPos] & 32767) << 4 | (in[4 + inPos] & 32767) >>> 11) & 255);
         out[8 + outPos] = (byte)((in[4 + inPos] & 32767) >>> 3 & 255);
         out[9 + outPos] = (byte)(((in[4 + inPos] & 32767) << 5 | (in[5 + inPos] & 32767) >>> 10) & 255);
         out[10 + outPos] = (byte)((in[5 + inPos] & 32767) >>> 2 & 255);
         out[11 + outPos] = (byte)(((in[5 + inPos] & 32767) << 6 | (in[6 + inPos] & 32767) >>> 9) & 255);
         out[12 + outPos] = (byte)((in[6 + inPos] & 32767) >>> 1 & 255);
         out[13 + outPos] = (byte)(((in[6 + inPos] & 32767) << 7 | (in[7 + inPos] & 32767) >>> 8) & 255);
         out[14 + outPos] = (byte)(in[7 + inPos] & 32767 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 32767) >>> 7 & 255);
         out[1 + outPos] = (byte)(((in[0 + inPos] & 32767) << 1 | (in[1 + inPos] & 32767) >>> 14) & 255);
         out[2 + outPos] = (byte)((in[1 + inPos] & 32767) >>> 6 & 255);
         out[3 + outPos] = (byte)(((in[1 + inPos] & 32767) << 2 | (in[2 + inPos] & 32767) >>> 13) & 255);
         out[4 + outPos] = (byte)((in[2 + inPos] & 32767) >>> 5 & 255);
         out[5 + outPos] = (byte)(((in[2 + inPos] & 32767) << 3 | (in[3 + inPos] & 32767) >>> 12) & 255);
         out[6 + outPos] = (byte)((in[3 + inPos] & 32767) >>> 4 & 255);
         out[7 + outPos] = (byte)(((in[3 + inPos] & 32767) << 4 | (in[4 + inPos] & 32767) >>> 11) & 255);
         out[8 + outPos] = (byte)((in[4 + inPos] & 32767) >>> 3 & 255);
         out[9 + outPos] = (byte)(((in[4 + inPos] & 32767) << 5 | (in[5 + inPos] & 32767) >>> 10) & 255);
         out[10 + outPos] = (byte)((in[5 + inPos] & 32767) >>> 2 & 255);
         out[11 + outPos] = (byte)(((in[5 + inPos] & 32767) << 6 | (in[6 + inPos] & 32767) >>> 9) & 255);
         out[12 + outPos] = (byte)((in[6 + inPos] & 32767) >>> 1 & 255);
         out[13 + outPos] = (byte)(((in[6 + inPos] & 32767) << 7 | (in[7 + inPos] & 32767) >>> 8) & 255);
         out[14 + outPos] = (byte)(in[7 + inPos] & 32767 & 255);
         out[15 + outPos] = (byte)((in[8 + inPos] & 32767) >>> 7 & 255);
         out[16 + outPos] = (byte)(((in[8 + inPos] & 32767) << 1 | (in[9 + inPos] & 32767) >>> 14) & 255);
         out[17 + outPos] = (byte)((in[9 + inPos] & 32767) >>> 6 & 255);
         out[18 + outPos] = (byte)(((in[9 + inPos] & 32767) << 2 | (in[10 + inPos] & 32767) >>> 13) & 255);
         out[19 + outPos] = (byte)((in[10 + inPos] & 32767) >>> 5 & 255);
         out[20 + outPos] = (byte)(((in[10 + inPos] & 32767) << 3 | (in[11 + inPos] & 32767) >>> 12) & 255);
         out[21 + outPos] = (byte)((in[11 + inPos] & 32767) >>> 4 & 255);
         out[22 + outPos] = (byte)(((in[11 + inPos] & 32767) << 4 | (in[12 + inPos] & 32767) >>> 11) & 255);
         out[23 + outPos] = (byte)((in[12 + inPos] & 32767) >>> 3 & 255);
         out[24 + outPos] = (byte)(((in[12 + inPos] & 32767) << 5 | (in[13 + inPos] & 32767) >>> 10) & 255);
         out[25 + outPos] = (byte)((in[13 + inPos] & 32767) >>> 2 & 255);
         out[26 + outPos] = (byte)(((in[13 + inPos] & 32767) << 6 | (in[14 + inPos] & 32767) >>> 9) & 255);
         out[27 + outPos] = (byte)((in[14 + inPos] & 32767) >>> 1 & 255);
         out[28 + outPos] = (byte)(((in[14 + inPos] & 32767) << 7 | (in[15 + inPos] & 32767) >>> 8) & 255);
         out[29 + outPos] = (byte)(in[15 + inPos] & 32767 & 255);
         out[30 + outPos] = (byte)((in[16 + inPos] & 32767) >>> 7 & 255);
         out[31 + outPos] = (byte)(((in[16 + inPos] & 32767) << 1 | (in[17 + inPos] & 32767) >>> 14) & 255);
         out[32 + outPos] = (byte)((in[17 + inPos] & 32767) >>> 6 & 255);
         out[33 + outPos] = (byte)(((in[17 + inPos] & 32767) << 2 | (in[18 + inPos] & 32767) >>> 13) & 255);
         out[34 + outPos] = (byte)((in[18 + inPos] & 32767) >>> 5 & 255);
         out[35 + outPos] = (byte)(((in[18 + inPos] & 32767) << 3 | (in[19 + inPos] & 32767) >>> 12) & 255);
         out[36 + outPos] = (byte)((in[19 + inPos] & 32767) >>> 4 & 255);
         out[37 + outPos] = (byte)(((in[19 + inPos] & 32767) << 4 | (in[20 + inPos] & 32767) >>> 11) & 255);
         out[38 + outPos] = (byte)((in[20 + inPos] & 32767) >>> 3 & 255);
         out[39 + outPos] = (byte)(((in[20 + inPos] & 32767) << 5 | (in[21 + inPos] & 32767) >>> 10) & 255);
         out[40 + outPos] = (byte)((in[21 + inPos] & 32767) >>> 2 & 255);
         out[41 + outPos] = (byte)(((in[21 + inPos] & 32767) << 6 | (in[22 + inPos] & 32767) >>> 9) & 255);
         out[42 + outPos] = (byte)((in[22 + inPos] & 32767) >>> 1 & 255);
         out[43 + outPos] = (byte)(((in[22 + inPos] & 32767) << 7 | (in[23 + inPos] & 32767) >>> 8) & 255);
         out[44 + outPos] = (byte)(in[23 + inPos] & 32767 & 255);
         out[45 + outPos] = (byte)((in[24 + inPos] & 32767) >>> 7 & 255);
         out[46 + outPos] = (byte)(((in[24 + inPos] & 32767) << 1 | (in[25 + inPos] & 32767) >>> 14) & 255);
         out[47 + outPos] = (byte)((in[25 + inPos] & 32767) >>> 6 & 255);
         out[48 + outPos] = (byte)(((in[25 + inPos] & 32767) << 2 | (in[26 + inPos] & 32767) >>> 13) & 255);
         out[49 + outPos] = (byte)((in[26 + inPos] & 32767) >>> 5 & 255);
         out[50 + outPos] = (byte)(((in[26 + inPos] & 32767) << 3 | (in[27 + inPos] & 32767) >>> 12) & 255);
         out[51 + outPos] = (byte)((in[27 + inPos] & 32767) >>> 4 & 255);
         out[52 + outPos] = (byte)(((in[27 + inPos] & 32767) << 4 | (in[28 + inPos] & 32767) >>> 11) & 255);
         out[53 + outPos] = (byte)((in[28 + inPos] & 32767) >>> 3 & 255);
         out[54 + outPos] = (byte)(((in[28 + inPos] & 32767) << 5 | (in[29 + inPos] & 32767) >>> 10) & 255);
         out[55 + outPos] = (byte)((in[29 + inPos] & 32767) >>> 2 & 255);
         out[56 + outPos] = (byte)(((in[29 + inPos] & 32767) << 6 | (in[30 + inPos] & 32767) >>> 9) & 255);
         out[57 + outPos] = (byte)((in[30 + inPos] & 32767) >>> 1 & 255);
         out[58 + outPos] = (byte)(((in[30 + inPos] & 32767) << 7 | (in[31 + inPos] & 32767) >>> 8) & 255);
         out[59 + outPos] = (byte)(in[31 + inPos] & 32767 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 7 & 32767 | in[1 + inPos] >> 1 & 127;
         out[1 + outPos] = in[1 + inPos] << 14 & 32767 | in[2 + inPos] << 6 & 16383 | in[3 + inPos] >> 2 & 63;
         out[2 + outPos] = in[3 + inPos] << 13 & 32767 | in[4 + inPos] << 5 & 8191 | in[5 + inPos] >> 3 & 31;
         out[3 + outPos] = in[5 + inPos] << 12 & 32767 | in[6 + inPos] << 4 & 4095 | in[7 + inPos] >> 4 & 15;
         out[4 + outPos] = in[7 + inPos] << 11 & 32767 | in[8 + inPos] << 3 & 2047 | in[9 + inPos] >> 5 & 7;
         out[5 + outPos] = in[9 + inPos] << 10 & 32767 | in[10 + inPos] << 2 & 1023 | in[11 + inPos] >> 6 & 3;
         out[6 + outPos] = in[11 + inPos] << 9 & 32767 | in[12 + inPos] << 1 & 511 | in[13 + inPos] >> 7 & 1;
         out[7 + outPos] = in[13 + inPos] << 8 & 32767 | in[14 + inPos] & 255;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 7 & 32767 | in.get(1 + inPos) >> 1 & 127;
         out[1 + outPos] = in.get(1 + inPos) << 14 & 32767 | in.get(2 + inPos) << 6 & 16383 | in.get(3 + inPos) >> 2 & 63;
         out[2 + outPos] = in.get(3 + inPos) << 13 & 32767 | in.get(4 + inPos) << 5 & 8191 | in.get(5 + inPos) >> 3 & 31;
         out[3 + outPos] = in.get(5 + inPos) << 12 & 32767 | in.get(6 + inPos) << 4 & 4095 | in.get(7 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(7 + inPos) << 11 & 32767 | in.get(8 + inPos) << 3 & 2047 | in.get(9 + inPos) >> 5 & 7;
         out[5 + outPos] = in.get(9 + inPos) << 10 & 32767 | in.get(10 + inPos) << 2 & 1023 | in.get(11 + inPos) >> 6 & 3;
         out[6 + outPos] = in.get(11 + inPos) << 9 & 32767 | in.get(12 + inPos) << 1 & 511 | in.get(13 + inPos) >> 7 & 1;
         out[7 + outPos] = in.get(13 + inPos) << 8 & 32767 | in.get(14 + inPos) & 255;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 7 & 32767 | in[1 + inPos] >> 1 & 127;
         out[1 + outPos] = in[1 + inPos] << 14 & 32767 | in[2 + inPos] << 6 & 16383 | in[3 + inPos] >> 2 & 63;
         out[2 + outPos] = in[3 + inPos] << 13 & 32767 | in[4 + inPos] << 5 & 8191 | in[5 + inPos] >> 3 & 31;
         out[3 + outPos] = in[5 + inPos] << 12 & 32767 | in[6 + inPos] << 4 & 4095 | in[7 + inPos] >> 4 & 15;
         out[4 + outPos] = in[7 + inPos] << 11 & 32767 | in[8 + inPos] << 3 & 2047 | in[9 + inPos] >> 5 & 7;
         out[5 + outPos] = in[9 + inPos] << 10 & 32767 | in[10 + inPos] << 2 & 1023 | in[11 + inPos] >> 6 & 3;
         out[6 + outPos] = in[11 + inPos] << 9 & 32767 | in[12 + inPos] << 1 & 511 | in[13 + inPos] >> 7 & 1;
         out[7 + outPos] = in[13 + inPos] << 8 & 32767 | in[14 + inPos] & 255;
         out[8 + outPos] = in[15 + inPos] << 7 & 32767 | in[16 + inPos] >> 1 & 127;
         out[9 + outPos] = in[16 + inPos] << 14 & 32767 | in[17 + inPos] << 6 & 16383 | in[18 + inPos] >> 2 & 63;
         out[10 + outPos] = in[18 + inPos] << 13 & 32767 | in[19 + inPos] << 5 & 8191 | in[20 + inPos] >> 3 & 31;
         out[11 + outPos] = in[20 + inPos] << 12 & 32767 | in[21 + inPos] << 4 & 4095 | in[22 + inPos] >> 4 & 15;
         out[12 + outPos] = in[22 + inPos] << 11 & 32767 | in[23 + inPos] << 3 & 2047 | in[24 + inPos] >> 5 & 7;
         out[13 + outPos] = in[24 + inPos] << 10 & 32767 | in[25 + inPos] << 2 & 1023 | in[26 + inPos] >> 6 & 3;
         out[14 + outPos] = in[26 + inPos] << 9 & 32767 | in[27 + inPos] << 1 & 511 | in[28 + inPos] >> 7 & 1;
         out[15 + outPos] = in[28 + inPos] << 8 & 32767 | in[29 + inPos] & 255;
         out[16 + outPos] = in[30 + inPos] << 7 & 32767 | in[31 + inPos] >> 1 & 127;
         out[17 + outPos] = in[31 + inPos] << 14 & 32767 | in[32 + inPos] << 6 & 16383 | in[33 + inPos] >> 2 & 63;
         out[18 + outPos] = in[33 + inPos] << 13 & 32767 | in[34 + inPos] << 5 & 8191 | in[35 + inPos] >> 3 & 31;
         out[19 + outPos] = in[35 + inPos] << 12 & 32767 | in[36 + inPos] << 4 & 4095 | in[37 + inPos] >> 4 & 15;
         out[20 + outPos] = in[37 + inPos] << 11 & 32767 | in[38 + inPos] << 3 & 2047 | in[39 + inPos] >> 5 & 7;
         out[21 + outPos] = in[39 + inPos] << 10 & 32767 | in[40 + inPos] << 2 & 1023 | in[41 + inPos] >> 6 & 3;
         out[22 + outPos] = in[41 + inPos] << 9 & 32767 | in[42 + inPos] << 1 & 511 | in[43 + inPos] >> 7 & 1;
         out[23 + outPos] = in[43 + inPos] << 8 & 32767 | in[44 + inPos] & 255;
         out[24 + outPos] = in[45 + inPos] << 7 & 32767 | in[46 + inPos] >> 1 & 127;
         out[25 + outPos] = in[46 + inPos] << 14 & 32767 | in[47 + inPos] << 6 & 16383 | in[48 + inPos] >> 2 & 63;
         out[26 + outPos] = in[48 + inPos] << 13 & 32767 | in[49 + inPos] << 5 & 8191 | in[50 + inPos] >> 3 & 31;
         out[27 + outPos] = in[50 + inPos] << 12 & 32767 | in[51 + inPos] << 4 & 4095 | in[52 + inPos] >> 4 & 15;
         out[28 + outPos] = in[52 + inPos] << 11 & 32767 | in[53 + inPos] << 3 & 2047 | in[54 + inPos] >> 5 & 7;
         out[29 + outPos] = in[54 + inPos] << 10 & 32767 | in[55 + inPos] << 2 & 1023 | in[56 + inPos] >> 6 & 3;
         out[30 + outPos] = in[56 + inPos] << 9 & 32767 | in[57 + inPos] << 1 & 511 | in[58 + inPos] >> 7 & 1;
         out[31 + outPos] = in[58 + inPos] << 8 & 32767 | in[59 + inPos] & 255;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 7 & 32767 | in.get(1 + inPos) >> 1 & 127;
         out[1 + outPos] = in.get(1 + inPos) << 14 & 32767 | in.get(2 + inPos) << 6 & 16383 | in.get(3 + inPos) >> 2 & 63;
         out[2 + outPos] = in.get(3 + inPos) << 13 & 32767 | in.get(4 + inPos) << 5 & 8191 | in.get(5 + inPos) >> 3 & 31;
         out[3 + outPos] = in.get(5 + inPos) << 12 & 32767 | in.get(6 + inPos) << 4 & 4095 | in.get(7 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(7 + inPos) << 11 & 32767 | in.get(8 + inPos) << 3 & 2047 | in.get(9 + inPos) >> 5 & 7;
         out[5 + outPos] = in.get(9 + inPos) << 10 & 32767 | in.get(10 + inPos) << 2 & 1023 | in.get(11 + inPos) >> 6 & 3;
         out[6 + outPos] = in.get(11 + inPos) << 9 & 32767 | in.get(12 + inPos) << 1 & 511 | in.get(13 + inPos) >> 7 & 1;
         out[7 + outPos] = in.get(13 + inPos) << 8 & 32767 | in.get(14 + inPos) & 255;
         out[8 + outPos] = in.get(15 + inPos) << 7 & 32767 | in.get(16 + inPos) >> 1 & 127;
         out[9 + outPos] = in.get(16 + inPos) << 14 & 32767 | in.get(17 + inPos) << 6 & 16383 | in.get(18 + inPos) >> 2 & 63;
         out[10 + outPos] = in.get(18 + inPos) << 13 & 32767 | in.get(19 + inPos) << 5 & 8191 | in.get(20 + inPos) >> 3 & 31;
         out[11 + outPos] = in.get(20 + inPos) << 12 & 32767 | in.get(21 + inPos) << 4 & 4095 | in.get(22 + inPos) >> 4 & 15;
         out[12 + outPos] = in.get(22 + inPos) << 11 & 32767 | in.get(23 + inPos) << 3 & 2047 | in.get(24 + inPos) >> 5 & 7;
         out[13 + outPos] = in.get(24 + inPos) << 10 & 32767 | in.get(25 + inPos) << 2 & 1023 | in.get(26 + inPos) >> 6 & 3;
         out[14 + outPos] = in.get(26 + inPos) << 9 & 32767 | in.get(27 + inPos) << 1 & 511 | in.get(28 + inPos) >> 7 & 1;
         out[15 + outPos] = in.get(28 + inPos) << 8 & 32767 | in.get(29 + inPos) & 255;
         out[16 + outPos] = in.get(30 + inPos) << 7 & 32767 | in.get(31 + inPos) >> 1 & 127;
         out[17 + outPos] = in.get(31 + inPos) << 14 & 32767 | in.get(32 + inPos) << 6 & 16383 | in.get(33 + inPos) >> 2 & 63;
         out[18 + outPos] = in.get(33 + inPos) << 13 & 32767 | in.get(34 + inPos) << 5 & 8191 | in.get(35 + inPos) >> 3 & 31;
         out[19 + outPos] = in.get(35 + inPos) << 12 & 32767 | in.get(36 + inPos) << 4 & 4095 | in.get(37 + inPos) >> 4 & 15;
         out[20 + outPos] = in.get(37 + inPos) << 11 & 32767 | in.get(38 + inPos) << 3 & 2047 | in.get(39 + inPos) >> 5 & 7;
         out[21 + outPos] = in.get(39 + inPos) << 10 & 32767 | in.get(40 + inPos) << 2 & 1023 | in.get(41 + inPos) >> 6 & 3;
         out[22 + outPos] = in.get(41 + inPos) << 9 & 32767 | in.get(42 + inPos) << 1 & 511 | in.get(43 + inPos) >> 7 & 1;
         out[23 + outPos] = in.get(43 + inPos) << 8 & 32767 | in.get(44 + inPos) & 255;
         out[24 + outPos] = in.get(45 + inPos) << 7 & 32767 | in.get(46 + inPos) >> 1 & 127;
         out[25 + outPos] = in.get(46 + inPos) << 14 & 32767 | in.get(47 + inPos) << 6 & 16383 | in.get(48 + inPos) >> 2 & 63;
         out[26 + outPos] = in.get(48 + inPos) << 13 & 32767 | in.get(49 + inPos) << 5 & 8191 | in.get(50 + inPos) >> 3 & 31;
         out[27 + outPos] = in.get(50 + inPos) << 12 & 32767 | in.get(51 + inPos) << 4 & 4095 | in.get(52 + inPos) >> 4 & 15;
         out[28 + outPos] = in.get(52 + inPos) << 11 & 32767 | in.get(53 + inPos) << 3 & 2047 | in.get(54 + inPos) >> 5 & 7;
         out[29 + outPos] = in.get(54 + inPos) << 10 & 32767 | in.get(55 + inPos) << 2 & 1023 | in.get(56 + inPos) >> 6 & 3;
         out[30 + outPos] = in.get(56 + inPos) << 9 & 32767 | in.get(57 + inPos) << 1 & 511 | in.get(58 + inPos) >> 7 & 1;
         out[31 + outPos] = in.get(58 + inPos) << 8 & 32767 | in.get(59 + inPos) & 255;
      }
   }

   private static final class Packer16 extends BytePacker {
      private Packer16() {
         super(16);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & '\uffff') >>> 8 & 255);
         out[1 + outPos] = (byte)(in[0 + inPos] & '\uffff' & 255);
         out[2 + outPos] = (byte)((in[1 + inPos] & '\uffff') >>> 8 & 255);
         out[3 + outPos] = (byte)(in[1 + inPos] & '\uffff' & 255);
         out[4 + outPos] = (byte)((in[2 + inPos] & '\uffff') >>> 8 & 255);
         out[5 + outPos] = (byte)(in[2 + inPos] & '\uffff' & 255);
         out[6 + outPos] = (byte)((in[3 + inPos] & '\uffff') >>> 8 & 255);
         out[7 + outPos] = (byte)(in[3 + inPos] & '\uffff' & 255);
         out[8 + outPos] = (byte)((in[4 + inPos] & '\uffff') >>> 8 & 255);
         out[9 + outPos] = (byte)(in[4 + inPos] & '\uffff' & 255);
         out[10 + outPos] = (byte)((in[5 + inPos] & '\uffff') >>> 8 & 255);
         out[11 + outPos] = (byte)(in[5 + inPos] & '\uffff' & 255);
         out[12 + outPos] = (byte)((in[6 + inPos] & '\uffff') >>> 8 & 255);
         out[13 + outPos] = (byte)(in[6 + inPos] & '\uffff' & 255);
         out[14 + outPos] = (byte)((in[7 + inPos] & '\uffff') >>> 8 & 255);
         out[15 + outPos] = (byte)(in[7 + inPos] & '\uffff' & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & '\uffff') >>> 8 & 255);
         out[1 + outPos] = (byte)(in[0 + inPos] & '\uffff' & 255);
         out[2 + outPos] = (byte)((in[1 + inPos] & '\uffff') >>> 8 & 255);
         out[3 + outPos] = (byte)(in[1 + inPos] & '\uffff' & 255);
         out[4 + outPos] = (byte)((in[2 + inPos] & '\uffff') >>> 8 & 255);
         out[5 + outPos] = (byte)(in[2 + inPos] & '\uffff' & 255);
         out[6 + outPos] = (byte)((in[3 + inPos] & '\uffff') >>> 8 & 255);
         out[7 + outPos] = (byte)(in[3 + inPos] & '\uffff' & 255);
         out[8 + outPos] = (byte)((in[4 + inPos] & '\uffff') >>> 8 & 255);
         out[9 + outPos] = (byte)(in[4 + inPos] & '\uffff' & 255);
         out[10 + outPos] = (byte)((in[5 + inPos] & '\uffff') >>> 8 & 255);
         out[11 + outPos] = (byte)(in[5 + inPos] & '\uffff' & 255);
         out[12 + outPos] = (byte)((in[6 + inPos] & '\uffff') >>> 8 & 255);
         out[13 + outPos] = (byte)(in[6 + inPos] & '\uffff' & 255);
         out[14 + outPos] = (byte)((in[7 + inPos] & '\uffff') >>> 8 & 255);
         out[15 + outPos] = (byte)(in[7 + inPos] & '\uffff' & 255);
         out[16 + outPos] = (byte)((in[8 + inPos] & '\uffff') >>> 8 & 255);
         out[17 + outPos] = (byte)(in[8 + inPos] & '\uffff' & 255);
         out[18 + outPos] = (byte)((in[9 + inPos] & '\uffff') >>> 8 & 255);
         out[19 + outPos] = (byte)(in[9 + inPos] & '\uffff' & 255);
         out[20 + outPos] = (byte)((in[10 + inPos] & '\uffff') >>> 8 & 255);
         out[21 + outPos] = (byte)(in[10 + inPos] & '\uffff' & 255);
         out[22 + outPos] = (byte)((in[11 + inPos] & '\uffff') >>> 8 & 255);
         out[23 + outPos] = (byte)(in[11 + inPos] & '\uffff' & 255);
         out[24 + outPos] = (byte)((in[12 + inPos] & '\uffff') >>> 8 & 255);
         out[25 + outPos] = (byte)(in[12 + inPos] & '\uffff' & 255);
         out[26 + outPos] = (byte)((in[13 + inPos] & '\uffff') >>> 8 & 255);
         out[27 + outPos] = (byte)(in[13 + inPos] & '\uffff' & 255);
         out[28 + outPos] = (byte)((in[14 + inPos] & '\uffff') >>> 8 & 255);
         out[29 + outPos] = (byte)(in[14 + inPos] & '\uffff' & 255);
         out[30 + outPos] = (byte)((in[15 + inPos] & '\uffff') >>> 8 & 255);
         out[31 + outPos] = (byte)(in[15 + inPos] & '\uffff' & 255);
         out[32 + outPos] = (byte)((in[16 + inPos] & '\uffff') >>> 8 & 255);
         out[33 + outPos] = (byte)(in[16 + inPos] & '\uffff' & 255);
         out[34 + outPos] = (byte)((in[17 + inPos] & '\uffff') >>> 8 & 255);
         out[35 + outPos] = (byte)(in[17 + inPos] & '\uffff' & 255);
         out[36 + outPos] = (byte)((in[18 + inPos] & '\uffff') >>> 8 & 255);
         out[37 + outPos] = (byte)(in[18 + inPos] & '\uffff' & 255);
         out[38 + outPos] = (byte)((in[19 + inPos] & '\uffff') >>> 8 & 255);
         out[39 + outPos] = (byte)(in[19 + inPos] & '\uffff' & 255);
         out[40 + outPos] = (byte)((in[20 + inPos] & '\uffff') >>> 8 & 255);
         out[41 + outPos] = (byte)(in[20 + inPos] & '\uffff' & 255);
         out[42 + outPos] = (byte)((in[21 + inPos] & '\uffff') >>> 8 & 255);
         out[43 + outPos] = (byte)(in[21 + inPos] & '\uffff' & 255);
         out[44 + outPos] = (byte)((in[22 + inPos] & '\uffff') >>> 8 & 255);
         out[45 + outPos] = (byte)(in[22 + inPos] & '\uffff' & 255);
         out[46 + outPos] = (byte)((in[23 + inPos] & '\uffff') >>> 8 & 255);
         out[47 + outPos] = (byte)(in[23 + inPos] & '\uffff' & 255);
         out[48 + outPos] = (byte)((in[24 + inPos] & '\uffff') >>> 8 & 255);
         out[49 + outPos] = (byte)(in[24 + inPos] & '\uffff' & 255);
         out[50 + outPos] = (byte)((in[25 + inPos] & '\uffff') >>> 8 & 255);
         out[51 + outPos] = (byte)(in[25 + inPos] & '\uffff' & 255);
         out[52 + outPos] = (byte)((in[26 + inPos] & '\uffff') >>> 8 & 255);
         out[53 + outPos] = (byte)(in[26 + inPos] & '\uffff' & 255);
         out[54 + outPos] = (byte)((in[27 + inPos] & '\uffff') >>> 8 & 255);
         out[55 + outPos] = (byte)(in[27 + inPos] & '\uffff' & 255);
         out[56 + outPos] = (byte)((in[28 + inPos] & '\uffff') >>> 8 & 255);
         out[57 + outPos] = (byte)(in[28 + inPos] & '\uffff' & 255);
         out[58 + outPos] = (byte)((in[29 + inPos] & '\uffff') >>> 8 & 255);
         out[59 + outPos] = (byte)(in[29 + inPos] & '\uffff' & 255);
         out[60 + outPos] = (byte)((in[30 + inPos] & '\uffff') >>> 8 & 255);
         out[61 + outPos] = (byte)(in[30 + inPos] & '\uffff' & 255);
         out[62 + outPos] = (byte)((in[31 + inPos] & '\uffff') >>> 8 & 255);
         out[63 + outPos] = (byte)(in[31 + inPos] & '\uffff' & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 8 & '\uffff' | in[1 + inPos] & 255;
         out[1 + outPos] = in[2 + inPos] << 8 & '\uffff' | in[3 + inPos] & 255;
         out[2 + outPos] = in[4 + inPos] << 8 & '\uffff' | in[5 + inPos] & 255;
         out[3 + outPos] = in[6 + inPos] << 8 & '\uffff' | in[7 + inPos] & 255;
         out[4 + outPos] = in[8 + inPos] << 8 & '\uffff' | in[9 + inPos] & 255;
         out[5 + outPos] = in[10 + inPos] << 8 & '\uffff' | in[11 + inPos] & 255;
         out[6 + outPos] = in[12 + inPos] << 8 & '\uffff' | in[13 + inPos] & 255;
         out[7 + outPos] = in[14 + inPos] << 8 & '\uffff' | in[15 + inPos] & 255;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 8 & '\uffff' | in.get(1 + inPos) & 255;
         out[1 + outPos] = in.get(2 + inPos) << 8 & '\uffff' | in.get(3 + inPos) & 255;
         out[2 + outPos] = in.get(4 + inPos) << 8 & '\uffff' | in.get(5 + inPos) & 255;
         out[3 + outPos] = in.get(6 + inPos) << 8 & '\uffff' | in.get(7 + inPos) & 255;
         out[4 + outPos] = in.get(8 + inPos) << 8 & '\uffff' | in.get(9 + inPos) & 255;
         out[5 + outPos] = in.get(10 + inPos) << 8 & '\uffff' | in.get(11 + inPos) & 255;
         out[6 + outPos] = in.get(12 + inPos) << 8 & '\uffff' | in.get(13 + inPos) & 255;
         out[7 + outPos] = in.get(14 + inPos) << 8 & '\uffff' | in.get(15 + inPos) & 255;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 8 & '\uffff' | in[1 + inPos] & 255;
         out[1 + outPos] = in[2 + inPos] << 8 & '\uffff' | in[3 + inPos] & 255;
         out[2 + outPos] = in[4 + inPos] << 8 & '\uffff' | in[5 + inPos] & 255;
         out[3 + outPos] = in[6 + inPos] << 8 & '\uffff' | in[7 + inPos] & 255;
         out[4 + outPos] = in[8 + inPos] << 8 & '\uffff' | in[9 + inPos] & 255;
         out[5 + outPos] = in[10 + inPos] << 8 & '\uffff' | in[11 + inPos] & 255;
         out[6 + outPos] = in[12 + inPos] << 8 & '\uffff' | in[13 + inPos] & 255;
         out[7 + outPos] = in[14 + inPos] << 8 & '\uffff' | in[15 + inPos] & 255;
         out[8 + outPos] = in[16 + inPos] << 8 & '\uffff' | in[17 + inPos] & 255;
         out[9 + outPos] = in[18 + inPos] << 8 & '\uffff' | in[19 + inPos] & 255;
         out[10 + outPos] = in[20 + inPos] << 8 & '\uffff' | in[21 + inPos] & 255;
         out[11 + outPos] = in[22 + inPos] << 8 & '\uffff' | in[23 + inPos] & 255;
         out[12 + outPos] = in[24 + inPos] << 8 & '\uffff' | in[25 + inPos] & 255;
         out[13 + outPos] = in[26 + inPos] << 8 & '\uffff' | in[27 + inPos] & 255;
         out[14 + outPos] = in[28 + inPos] << 8 & '\uffff' | in[29 + inPos] & 255;
         out[15 + outPos] = in[30 + inPos] << 8 & '\uffff' | in[31 + inPos] & 255;
         out[16 + outPos] = in[32 + inPos] << 8 & '\uffff' | in[33 + inPos] & 255;
         out[17 + outPos] = in[34 + inPos] << 8 & '\uffff' | in[35 + inPos] & 255;
         out[18 + outPos] = in[36 + inPos] << 8 & '\uffff' | in[37 + inPos] & 255;
         out[19 + outPos] = in[38 + inPos] << 8 & '\uffff' | in[39 + inPos] & 255;
         out[20 + outPos] = in[40 + inPos] << 8 & '\uffff' | in[41 + inPos] & 255;
         out[21 + outPos] = in[42 + inPos] << 8 & '\uffff' | in[43 + inPos] & 255;
         out[22 + outPos] = in[44 + inPos] << 8 & '\uffff' | in[45 + inPos] & 255;
         out[23 + outPos] = in[46 + inPos] << 8 & '\uffff' | in[47 + inPos] & 255;
         out[24 + outPos] = in[48 + inPos] << 8 & '\uffff' | in[49 + inPos] & 255;
         out[25 + outPos] = in[50 + inPos] << 8 & '\uffff' | in[51 + inPos] & 255;
         out[26 + outPos] = in[52 + inPos] << 8 & '\uffff' | in[53 + inPos] & 255;
         out[27 + outPos] = in[54 + inPos] << 8 & '\uffff' | in[55 + inPos] & 255;
         out[28 + outPos] = in[56 + inPos] << 8 & '\uffff' | in[57 + inPos] & 255;
         out[29 + outPos] = in[58 + inPos] << 8 & '\uffff' | in[59 + inPos] & 255;
         out[30 + outPos] = in[60 + inPos] << 8 & '\uffff' | in[61 + inPos] & 255;
         out[31 + outPos] = in[62 + inPos] << 8 & '\uffff' | in[63 + inPos] & 255;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 8 & '\uffff' | in.get(1 + inPos) & 255;
         out[1 + outPos] = in.get(2 + inPos) << 8 & '\uffff' | in.get(3 + inPos) & 255;
         out[2 + outPos] = in.get(4 + inPos) << 8 & '\uffff' | in.get(5 + inPos) & 255;
         out[3 + outPos] = in.get(6 + inPos) << 8 & '\uffff' | in.get(7 + inPos) & 255;
         out[4 + outPos] = in.get(8 + inPos) << 8 & '\uffff' | in.get(9 + inPos) & 255;
         out[5 + outPos] = in.get(10 + inPos) << 8 & '\uffff' | in.get(11 + inPos) & 255;
         out[6 + outPos] = in.get(12 + inPos) << 8 & '\uffff' | in.get(13 + inPos) & 255;
         out[7 + outPos] = in.get(14 + inPos) << 8 & '\uffff' | in.get(15 + inPos) & 255;
         out[8 + outPos] = in.get(16 + inPos) << 8 & '\uffff' | in.get(17 + inPos) & 255;
         out[9 + outPos] = in.get(18 + inPos) << 8 & '\uffff' | in.get(19 + inPos) & 255;
         out[10 + outPos] = in.get(20 + inPos) << 8 & '\uffff' | in.get(21 + inPos) & 255;
         out[11 + outPos] = in.get(22 + inPos) << 8 & '\uffff' | in.get(23 + inPos) & 255;
         out[12 + outPos] = in.get(24 + inPos) << 8 & '\uffff' | in.get(25 + inPos) & 255;
         out[13 + outPos] = in.get(26 + inPos) << 8 & '\uffff' | in.get(27 + inPos) & 255;
         out[14 + outPos] = in.get(28 + inPos) << 8 & '\uffff' | in.get(29 + inPos) & 255;
         out[15 + outPos] = in.get(30 + inPos) << 8 & '\uffff' | in.get(31 + inPos) & 255;
         out[16 + outPos] = in.get(32 + inPos) << 8 & '\uffff' | in.get(33 + inPos) & 255;
         out[17 + outPos] = in.get(34 + inPos) << 8 & '\uffff' | in.get(35 + inPos) & 255;
         out[18 + outPos] = in.get(36 + inPos) << 8 & '\uffff' | in.get(37 + inPos) & 255;
         out[19 + outPos] = in.get(38 + inPos) << 8 & '\uffff' | in.get(39 + inPos) & 255;
         out[20 + outPos] = in.get(40 + inPos) << 8 & '\uffff' | in.get(41 + inPos) & 255;
         out[21 + outPos] = in.get(42 + inPos) << 8 & '\uffff' | in.get(43 + inPos) & 255;
         out[22 + outPos] = in.get(44 + inPos) << 8 & '\uffff' | in.get(45 + inPos) & 255;
         out[23 + outPos] = in.get(46 + inPos) << 8 & '\uffff' | in.get(47 + inPos) & 255;
         out[24 + outPos] = in.get(48 + inPos) << 8 & '\uffff' | in.get(49 + inPos) & 255;
         out[25 + outPos] = in.get(50 + inPos) << 8 & '\uffff' | in.get(51 + inPos) & 255;
         out[26 + outPos] = in.get(52 + inPos) << 8 & '\uffff' | in.get(53 + inPos) & 255;
         out[27 + outPos] = in.get(54 + inPos) << 8 & '\uffff' | in.get(55 + inPos) & 255;
         out[28 + outPos] = in.get(56 + inPos) << 8 & '\uffff' | in.get(57 + inPos) & 255;
         out[29 + outPos] = in.get(58 + inPos) << 8 & '\uffff' | in.get(59 + inPos) & 255;
         out[30 + outPos] = in.get(60 + inPos) << 8 & '\uffff' | in.get(61 + inPos) & 255;
         out[31 + outPos] = in.get(62 + inPos) << 8 & '\uffff' | in.get(63 + inPos) & 255;
      }
   }

   private static final class Packer17 extends BytePacker {
      private Packer17() {
         super(17);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 131071) >>> 9 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 131071) >>> 1 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 131071) << 7 | (in[1 + inPos] & 131071) >>> 10) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 131071) >>> 2 & 255);
         out[4 + outPos] = (byte)(((in[1 + inPos] & 131071) << 6 | (in[2 + inPos] & 131071) >>> 11) & 255);
         out[5 + outPos] = (byte)((in[2 + inPos] & 131071) >>> 3 & 255);
         out[6 + outPos] = (byte)(((in[2 + inPos] & 131071) << 5 | (in[3 + inPos] & 131071) >>> 12) & 255);
         out[7 + outPos] = (byte)((in[3 + inPos] & 131071) >>> 4 & 255);
         out[8 + outPos] = (byte)(((in[3 + inPos] & 131071) << 4 | (in[4 + inPos] & 131071) >>> 13) & 255);
         out[9 + outPos] = (byte)((in[4 + inPos] & 131071) >>> 5 & 255);
         out[10 + outPos] = (byte)(((in[4 + inPos] & 131071) << 3 | (in[5 + inPos] & 131071) >>> 14) & 255);
         out[11 + outPos] = (byte)((in[5 + inPos] & 131071) >>> 6 & 255);
         out[12 + outPos] = (byte)(((in[5 + inPos] & 131071) << 2 | (in[6 + inPos] & 131071) >>> 15) & 255);
         out[13 + outPos] = (byte)((in[6 + inPos] & 131071) >>> 7 & 255);
         out[14 + outPos] = (byte)(((in[6 + inPos] & 131071) << 1 | (in[7 + inPos] & 131071) >>> 16) & 255);
         out[15 + outPos] = (byte)((in[7 + inPos] & 131071) >>> 8 & 255);
         out[16 + outPos] = (byte)(in[7 + inPos] & 131071 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 131071) >>> 9 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 131071) >>> 1 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 131071) << 7 | (in[1 + inPos] & 131071) >>> 10) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 131071) >>> 2 & 255);
         out[4 + outPos] = (byte)(((in[1 + inPos] & 131071) << 6 | (in[2 + inPos] & 131071) >>> 11) & 255);
         out[5 + outPos] = (byte)((in[2 + inPos] & 131071) >>> 3 & 255);
         out[6 + outPos] = (byte)(((in[2 + inPos] & 131071) << 5 | (in[3 + inPos] & 131071) >>> 12) & 255);
         out[7 + outPos] = (byte)((in[3 + inPos] & 131071) >>> 4 & 255);
         out[8 + outPos] = (byte)(((in[3 + inPos] & 131071) << 4 | (in[4 + inPos] & 131071) >>> 13) & 255);
         out[9 + outPos] = (byte)((in[4 + inPos] & 131071) >>> 5 & 255);
         out[10 + outPos] = (byte)(((in[4 + inPos] & 131071) << 3 | (in[5 + inPos] & 131071) >>> 14) & 255);
         out[11 + outPos] = (byte)((in[5 + inPos] & 131071) >>> 6 & 255);
         out[12 + outPos] = (byte)(((in[5 + inPos] & 131071) << 2 | (in[6 + inPos] & 131071) >>> 15) & 255);
         out[13 + outPos] = (byte)((in[6 + inPos] & 131071) >>> 7 & 255);
         out[14 + outPos] = (byte)(((in[6 + inPos] & 131071) << 1 | (in[7 + inPos] & 131071) >>> 16) & 255);
         out[15 + outPos] = (byte)((in[7 + inPos] & 131071) >>> 8 & 255);
         out[16 + outPos] = (byte)(in[7 + inPos] & 131071 & 255);
         out[17 + outPos] = (byte)((in[8 + inPos] & 131071) >>> 9 & 255);
         out[18 + outPos] = (byte)((in[8 + inPos] & 131071) >>> 1 & 255);
         out[19 + outPos] = (byte)(((in[8 + inPos] & 131071) << 7 | (in[9 + inPos] & 131071) >>> 10) & 255);
         out[20 + outPos] = (byte)((in[9 + inPos] & 131071) >>> 2 & 255);
         out[21 + outPos] = (byte)(((in[9 + inPos] & 131071) << 6 | (in[10 + inPos] & 131071) >>> 11) & 255);
         out[22 + outPos] = (byte)((in[10 + inPos] & 131071) >>> 3 & 255);
         out[23 + outPos] = (byte)(((in[10 + inPos] & 131071) << 5 | (in[11 + inPos] & 131071) >>> 12) & 255);
         out[24 + outPos] = (byte)((in[11 + inPos] & 131071) >>> 4 & 255);
         out[25 + outPos] = (byte)(((in[11 + inPos] & 131071) << 4 | (in[12 + inPos] & 131071) >>> 13) & 255);
         out[26 + outPos] = (byte)((in[12 + inPos] & 131071) >>> 5 & 255);
         out[27 + outPos] = (byte)(((in[12 + inPos] & 131071) << 3 | (in[13 + inPos] & 131071) >>> 14) & 255);
         out[28 + outPos] = (byte)((in[13 + inPos] & 131071) >>> 6 & 255);
         out[29 + outPos] = (byte)(((in[13 + inPos] & 131071) << 2 | (in[14 + inPos] & 131071) >>> 15) & 255);
         out[30 + outPos] = (byte)((in[14 + inPos] & 131071) >>> 7 & 255);
         out[31 + outPos] = (byte)(((in[14 + inPos] & 131071) << 1 | (in[15 + inPos] & 131071) >>> 16) & 255);
         out[32 + outPos] = (byte)((in[15 + inPos] & 131071) >>> 8 & 255);
         out[33 + outPos] = (byte)(in[15 + inPos] & 131071 & 255);
         out[34 + outPos] = (byte)((in[16 + inPos] & 131071) >>> 9 & 255);
         out[35 + outPos] = (byte)((in[16 + inPos] & 131071) >>> 1 & 255);
         out[36 + outPos] = (byte)(((in[16 + inPos] & 131071) << 7 | (in[17 + inPos] & 131071) >>> 10) & 255);
         out[37 + outPos] = (byte)((in[17 + inPos] & 131071) >>> 2 & 255);
         out[38 + outPos] = (byte)(((in[17 + inPos] & 131071) << 6 | (in[18 + inPos] & 131071) >>> 11) & 255);
         out[39 + outPos] = (byte)((in[18 + inPos] & 131071) >>> 3 & 255);
         out[40 + outPos] = (byte)(((in[18 + inPos] & 131071) << 5 | (in[19 + inPos] & 131071) >>> 12) & 255);
         out[41 + outPos] = (byte)((in[19 + inPos] & 131071) >>> 4 & 255);
         out[42 + outPos] = (byte)(((in[19 + inPos] & 131071) << 4 | (in[20 + inPos] & 131071) >>> 13) & 255);
         out[43 + outPos] = (byte)((in[20 + inPos] & 131071) >>> 5 & 255);
         out[44 + outPos] = (byte)(((in[20 + inPos] & 131071) << 3 | (in[21 + inPos] & 131071) >>> 14) & 255);
         out[45 + outPos] = (byte)((in[21 + inPos] & 131071) >>> 6 & 255);
         out[46 + outPos] = (byte)(((in[21 + inPos] & 131071) << 2 | (in[22 + inPos] & 131071) >>> 15) & 255);
         out[47 + outPos] = (byte)((in[22 + inPos] & 131071) >>> 7 & 255);
         out[48 + outPos] = (byte)(((in[22 + inPos] & 131071) << 1 | (in[23 + inPos] & 131071) >>> 16) & 255);
         out[49 + outPos] = (byte)((in[23 + inPos] & 131071) >>> 8 & 255);
         out[50 + outPos] = (byte)(in[23 + inPos] & 131071 & 255);
         out[51 + outPos] = (byte)((in[24 + inPos] & 131071) >>> 9 & 255);
         out[52 + outPos] = (byte)((in[24 + inPos] & 131071) >>> 1 & 255);
         out[53 + outPos] = (byte)(((in[24 + inPos] & 131071) << 7 | (in[25 + inPos] & 131071) >>> 10) & 255);
         out[54 + outPos] = (byte)((in[25 + inPos] & 131071) >>> 2 & 255);
         out[55 + outPos] = (byte)(((in[25 + inPos] & 131071) << 6 | (in[26 + inPos] & 131071) >>> 11) & 255);
         out[56 + outPos] = (byte)((in[26 + inPos] & 131071) >>> 3 & 255);
         out[57 + outPos] = (byte)(((in[26 + inPos] & 131071) << 5 | (in[27 + inPos] & 131071) >>> 12) & 255);
         out[58 + outPos] = (byte)((in[27 + inPos] & 131071) >>> 4 & 255);
         out[59 + outPos] = (byte)(((in[27 + inPos] & 131071) << 4 | (in[28 + inPos] & 131071) >>> 13) & 255);
         out[60 + outPos] = (byte)((in[28 + inPos] & 131071) >>> 5 & 255);
         out[61 + outPos] = (byte)(((in[28 + inPos] & 131071) << 3 | (in[29 + inPos] & 131071) >>> 14) & 255);
         out[62 + outPos] = (byte)((in[29 + inPos] & 131071) >>> 6 & 255);
         out[63 + outPos] = (byte)(((in[29 + inPos] & 131071) << 2 | (in[30 + inPos] & 131071) >>> 15) & 255);
         out[64 + outPos] = (byte)((in[30 + inPos] & 131071) >>> 7 & 255);
         out[65 + outPos] = (byte)(((in[30 + inPos] & 131071) << 1 | (in[31 + inPos] & 131071) >>> 16) & 255);
         out[66 + outPos] = (byte)((in[31 + inPos] & 131071) >>> 8 & 255);
         out[67 + outPos] = (byte)(in[31 + inPos] & 131071 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 9 & 131071 | in[1 + inPos] << 1 & 511 | in[2 + inPos] >> 7 & 1;
         out[1 + outPos] = in[2 + inPos] << 10 & 131071 | in[3 + inPos] << 2 & 1023 | in[4 + inPos] >> 6 & 3;
         out[2 + outPos] = in[4 + inPos] << 11 & 131071 | in[5 + inPos] << 3 & 2047 | in[6 + inPos] >> 5 & 7;
         out[3 + outPos] = in[6 + inPos] << 12 & 131071 | in[7 + inPos] << 4 & 4095 | in[8 + inPos] >> 4 & 15;
         out[4 + outPos] = in[8 + inPos] << 13 & 131071 | in[9 + inPos] << 5 & 8191 | in[10 + inPos] >> 3 & 31;
         out[5 + outPos] = in[10 + inPos] << 14 & 131071 | in[11 + inPos] << 6 & 16383 | in[12 + inPos] >> 2 & 63;
         out[6 + outPos] = in[12 + inPos] << 15 & 131071 | in[13 + inPos] << 7 & 32767 | in[14 + inPos] >> 1 & 127;
         out[7 + outPos] = in[14 + inPos] << 16 & 131071 | in[15 + inPos] << 8 & '\uffff' | in[16 + inPos] & 255;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 9 & 131071 | in.get(1 + inPos) << 1 & 511 | in.get(2 + inPos) >> 7 & 1;
         out[1 + outPos] = in.get(2 + inPos) << 10 & 131071 | in.get(3 + inPos) << 2 & 1023 | in.get(4 + inPos) >> 6 & 3;
         out[2 + outPos] = in.get(4 + inPos) << 11 & 131071 | in.get(5 + inPos) << 3 & 2047 | in.get(6 + inPos) >> 5 & 7;
         out[3 + outPos] = in.get(6 + inPos) << 12 & 131071 | in.get(7 + inPos) << 4 & 4095 | in.get(8 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(8 + inPos) << 13 & 131071 | in.get(9 + inPos) << 5 & 8191 | in.get(10 + inPos) >> 3 & 31;
         out[5 + outPos] = in.get(10 + inPos) << 14 & 131071 | in.get(11 + inPos) << 6 & 16383 | in.get(12 + inPos) >> 2 & 63;
         out[6 + outPos] = in.get(12 + inPos) << 15 & 131071 | in.get(13 + inPos) << 7 & 32767 | in.get(14 + inPos) >> 1 & 127;
         out[7 + outPos] = in.get(14 + inPos) << 16 & 131071 | in.get(15 + inPos) << 8 & '\uffff' | in.get(16 + inPos) & 255;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 9 & 131071 | in[1 + inPos] << 1 & 511 | in[2 + inPos] >> 7 & 1;
         out[1 + outPos] = in[2 + inPos] << 10 & 131071 | in[3 + inPos] << 2 & 1023 | in[4 + inPos] >> 6 & 3;
         out[2 + outPos] = in[4 + inPos] << 11 & 131071 | in[5 + inPos] << 3 & 2047 | in[6 + inPos] >> 5 & 7;
         out[3 + outPos] = in[6 + inPos] << 12 & 131071 | in[7 + inPos] << 4 & 4095 | in[8 + inPos] >> 4 & 15;
         out[4 + outPos] = in[8 + inPos] << 13 & 131071 | in[9 + inPos] << 5 & 8191 | in[10 + inPos] >> 3 & 31;
         out[5 + outPos] = in[10 + inPos] << 14 & 131071 | in[11 + inPos] << 6 & 16383 | in[12 + inPos] >> 2 & 63;
         out[6 + outPos] = in[12 + inPos] << 15 & 131071 | in[13 + inPos] << 7 & 32767 | in[14 + inPos] >> 1 & 127;
         out[7 + outPos] = in[14 + inPos] << 16 & 131071 | in[15 + inPos] << 8 & '\uffff' | in[16 + inPos] & 255;
         out[8 + outPos] = in[17 + inPos] << 9 & 131071 | in[18 + inPos] << 1 & 511 | in[19 + inPos] >> 7 & 1;
         out[9 + outPos] = in[19 + inPos] << 10 & 131071 | in[20 + inPos] << 2 & 1023 | in[21 + inPos] >> 6 & 3;
         out[10 + outPos] = in[21 + inPos] << 11 & 131071 | in[22 + inPos] << 3 & 2047 | in[23 + inPos] >> 5 & 7;
         out[11 + outPos] = in[23 + inPos] << 12 & 131071 | in[24 + inPos] << 4 & 4095 | in[25 + inPos] >> 4 & 15;
         out[12 + outPos] = in[25 + inPos] << 13 & 131071 | in[26 + inPos] << 5 & 8191 | in[27 + inPos] >> 3 & 31;
         out[13 + outPos] = in[27 + inPos] << 14 & 131071 | in[28 + inPos] << 6 & 16383 | in[29 + inPos] >> 2 & 63;
         out[14 + outPos] = in[29 + inPos] << 15 & 131071 | in[30 + inPos] << 7 & 32767 | in[31 + inPos] >> 1 & 127;
         out[15 + outPos] = in[31 + inPos] << 16 & 131071 | in[32 + inPos] << 8 & '\uffff' | in[33 + inPos] & 255;
         out[16 + outPos] = in[34 + inPos] << 9 & 131071 | in[35 + inPos] << 1 & 511 | in[36 + inPos] >> 7 & 1;
         out[17 + outPos] = in[36 + inPos] << 10 & 131071 | in[37 + inPos] << 2 & 1023 | in[38 + inPos] >> 6 & 3;
         out[18 + outPos] = in[38 + inPos] << 11 & 131071 | in[39 + inPos] << 3 & 2047 | in[40 + inPos] >> 5 & 7;
         out[19 + outPos] = in[40 + inPos] << 12 & 131071 | in[41 + inPos] << 4 & 4095 | in[42 + inPos] >> 4 & 15;
         out[20 + outPos] = in[42 + inPos] << 13 & 131071 | in[43 + inPos] << 5 & 8191 | in[44 + inPos] >> 3 & 31;
         out[21 + outPos] = in[44 + inPos] << 14 & 131071 | in[45 + inPos] << 6 & 16383 | in[46 + inPos] >> 2 & 63;
         out[22 + outPos] = in[46 + inPos] << 15 & 131071 | in[47 + inPos] << 7 & 32767 | in[48 + inPos] >> 1 & 127;
         out[23 + outPos] = in[48 + inPos] << 16 & 131071 | in[49 + inPos] << 8 & '\uffff' | in[50 + inPos] & 255;
         out[24 + outPos] = in[51 + inPos] << 9 & 131071 | in[52 + inPos] << 1 & 511 | in[53 + inPos] >> 7 & 1;
         out[25 + outPos] = in[53 + inPos] << 10 & 131071 | in[54 + inPos] << 2 & 1023 | in[55 + inPos] >> 6 & 3;
         out[26 + outPos] = in[55 + inPos] << 11 & 131071 | in[56 + inPos] << 3 & 2047 | in[57 + inPos] >> 5 & 7;
         out[27 + outPos] = in[57 + inPos] << 12 & 131071 | in[58 + inPos] << 4 & 4095 | in[59 + inPos] >> 4 & 15;
         out[28 + outPos] = in[59 + inPos] << 13 & 131071 | in[60 + inPos] << 5 & 8191 | in[61 + inPos] >> 3 & 31;
         out[29 + outPos] = in[61 + inPos] << 14 & 131071 | in[62 + inPos] << 6 & 16383 | in[63 + inPos] >> 2 & 63;
         out[30 + outPos] = in[63 + inPos] << 15 & 131071 | in[64 + inPos] << 7 & 32767 | in[65 + inPos] >> 1 & 127;
         out[31 + outPos] = in[65 + inPos] << 16 & 131071 | in[66 + inPos] << 8 & '\uffff' | in[67 + inPos] & 255;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 9 & 131071 | in.get(1 + inPos) << 1 & 511 | in.get(2 + inPos) >> 7 & 1;
         out[1 + outPos] = in.get(2 + inPos) << 10 & 131071 | in.get(3 + inPos) << 2 & 1023 | in.get(4 + inPos) >> 6 & 3;
         out[2 + outPos] = in.get(4 + inPos) << 11 & 131071 | in.get(5 + inPos) << 3 & 2047 | in.get(6 + inPos) >> 5 & 7;
         out[3 + outPos] = in.get(6 + inPos) << 12 & 131071 | in.get(7 + inPos) << 4 & 4095 | in.get(8 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(8 + inPos) << 13 & 131071 | in.get(9 + inPos) << 5 & 8191 | in.get(10 + inPos) >> 3 & 31;
         out[5 + outPos] = in.get(10 + inPos) << 14 & 131071 | in.get(11 + inPos) << 6 & 16383 | in.get(12 + inPos) >> 2 & 63;
         out[6 + outPos] = in.get(12 + inPos) << 15 & 131071 | in.get(13 + inPos) << 7 & 32767 | in.get(14 + inPos) >> 1 & 127;
         out[7 + outPos] = in.get(14 + inPos) << 16 & 131071 | in.get(15 + inPos) << 8 & '\uffff' | in.get(16 + inPos) & 255;
         out[8 + outPos] = in.get(17 + inPos) << 9 & 131071 | in.get(18 + inPos) << 1 & 511 | in.get(19 + inPos) >> 7 & 1;
         out[9 + outPos] = in.get(19 + inPos) << 10 & 131071 | in.get(20 + inPos) << 2 & 1023 | in.get(21 + inPos) >> 6 & 3;
         out[10 + outPos] = in.get(21 + inPos) << 11 & 131071 | in.get(22 + inPos) << 3 & 2047 | in.get(23 + inPos) >> 5 & 7;
         out[11 + outPos] = in.get(23 + inPos) << 12 & 131071 | in.get(24 + inPos) << 4 & 4095 | in.get(25 + inPos) >> 4 & 15;
         out[12 + outPos] = in.get(25 + inPos) << 13 & 131071 | in.get(26 + inPos) << 5 & 8191 | in.get(27 + inPos) >> 3 & 31;
         out[13 + outPos] = in.get(27 + inPos) << 14 & 131071 | in.get(28 + inPos) << 6 & 16383 | in.get(29 + inPos) >> 2 & 63;
         out[14 + outPos] = in.get(29 + inPos) << 15 & 131071 | in.get(30 + inPos) << 7 & 32767 | in.get(31 + inPos) >> 1 & 127;
         out[15 + outPos] = in.get(31 + inPos) << 16 & 131071 | in.get(32 + inPos) << 8 & '\uffff' | in.get(33 + inPos) & 255;
         out[16 + outPos] = in.get(34 + inPos) << 9 & 131071 | in.get(35 + inPos) << 1 & 511 | in.get(36 + inPos) >> 7 & 1;
         out[17 + outPos] = in.get(36 + inPos) << 10 & 131071 | in.get(37 + inPos) << 2 & 1023 | in.get(38 + inPos) >> 6 & 3;
         out[18 + outPos] = in.get(38 + inPos) << 11 & 131071 | in.get(39 + inPos) << 3 & 2047 | in.get(40 + inPos) >> 5 & 7;
         out[19 + outPos] = in.get(40 + inPos) << 12 & 131071 | in.get(41 + inPos) << 4 & 4095 | in.get(42 + inPos) >> 4 & 15;
         out[20 + outPos] = in.get(42 + inPos) << 13 & 131071 | in.get(43 + inPos) << 5 & 8191 | in.get(44 + inPos) >> 3 & 31;
         out[21 + outPos] = in.get(44 + inPos) << 14 & 131071 | in.get(45 + inPos) << 6 & 16383 | in.get(46 + inPos) >> 2 & 63;
         out[22 + outPos] = in.get(46 + inPos) << 15 & 131071 | in.get(47 + inPos) << 7 & 32767 | in.get(48 + inPos) >> 1 & 127;
         out[23 + outPos] = in.get(48 + inPos) << 16 & 131071 | in.get(49 + inPos) << 8 & '\uffff' | in.get(50 + inPos) & 255;
         out[24 + outPos] = in.get(51 + inPos) << 9 & 131071 | in.get(52 + inPos) << 1 & 511 | in.get(53 + inPos) >> 7 & 1;
         out[25 + outPos] = in.get(53 + inPos) << 10 & 131071 | in.get(54 + inPos) << 2 & 1023 | in.get(55 + inPos) >> 6 & 3;
         out[26 + outPos] = in.get(55 + inPos) << 11 & 131071 | in.get(56 + inPos) << 3 & 2047 | in.get(57 + inPos) >> 5 & 7;
         out[27 + outPos] = in.get(57 + inPos) << 12 & 131071 | in.get(58 + inPos) << 4 & 4095 | in.get(59 + inPos) >> 4 & 15;
         out[28 + outPos] = in.get(59 + inPos) << 13 & 131071 | in.get(60 + inPos) << 5 & 8191 | in.get(61 + inPos) >> 3 & 31;
         out[29 + outPos] = in.get(61 + inPos) << 14 & 131071 | in.get(62 + inPos) << 6 & 16383 | in.get(63 + inPos) >> 2 & 63;
         out[30 + outPos] = in.get(63 + inPos) << 15 & 131071 | in.get(64 + inPos) << 7 & 32767 | in.get(65 + inPos) >> 1 & 127;
         out[31 + outPos] = in.get(65 + inPos) << 16 & 131071 | in.get(66 + inPos) << 8 & '\uffff' | in.get(67 + inPos) & 255;
      }
   }

   private static final class Packer18 extends BytePacker {
      private Packer18() {
         super(18);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 262143) >>> 10 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 262143) >>> 2 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 262143) << 6 | (in[1 + inPos] & 262143) >>> 12) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 262143) >>> 4 & 255);
         out[4 + outPos] = (byte)(((in[1 + inPos] & 262143) << 4 | (in[2 + inPos] & 262143) >>> 14) & 255);
         out[5 + outPos] = (byte)((in[2 + inPos] & 262143) >>> 6 & 255);
         out[6 + outPos] = (byte)(((in[2 + inPos] & 262143) << 2 | (in[3 + inPos] & 262143) >>> 16) & 255);
         out[7 + outPos] = (byte)((in[3 + inPos] & 262143) >>> 8 & 255);
         out[8 + outPos] = (byte)(in[3 + inPos] & 262143 & 255);
         out[9 + outPos] = (byte)((in[4 + inPos] & 262143) >>> 10 & 255);
         out[10 + outPos] = (byte)((in[4 + inPos] & 262143) >>> 2 & 255);
         out[11 + outPos] = (byte)(((in[4 + inPos] & 262143) << 6 | (in[5 + inPos] & 262143) >>> 12) & 255);
         out[12 + outPos] = (byte)((in[5 + inPos] & 262143) >>> 4 & 255);
         out[13 + outPos] = (byte)(((in[5 + inPos] & 262143) << 4 | (in[6 + inPos] & 262143) >>> 14) & 255);
         out[14 + outPos] = (byte)((in[6 + inPos] & 262143) >>> 6 & 255);
         out[15 + outPos] = (byte)(((in[6 + inPos] & 262143) << 2 | (in[7 + inPos] & 262143) >>> 16) & 255);
         out[16 + outPos] = (byte)((in[7 + inPos] & 262143) >>> 8 & 255);
         out[17 + outPos] = (byte)(in[7 + inPos] & 262143 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 262143) >>> 10 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 262143) >>> 2 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 262143) << 6 | (in[1 + inPos] & 262143) >>> 12) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 262143) >>> 4 & 255);
         out[4 + outPos] = (byte)(((in[1 + inPos] & 262143) << 4 | (in[2 + inPos] & 262143) >>> 14) & 255);
         out[5 + outPos] = (byte)((in[2 + inPos] & 262143) >>> 6 & 255);
         out[6 + outPos] = (byte)(((in[2 + inPos] & 262143) << 2 | (in[3 + inPos] & 262143) >>> 16) & 255);
         out[7 + outPos] = (byte)((in[3 + inPos] & 262143) >>> 8 & 255);
         out[8 + outPos] = (byte)(in[3 + inPos] & 262143 & 255);
         out[9 + outPos] = (byte)((in[4 + inPos] & 262143) >>> 10 & 255);
         out[10 + outPos] = (byte)((in[4 + inPos] & 262143) >>> 2 & 255);
         out[11 + outPos] = (byte)(((in[4 + inPos] & 262143) << 6 | (in[5 + inPos] & 262143) >>> 12) & 255);
         out[12 + outPos] = (byte)((in[5 + inPos] & 262143) >>> 4 & 255);
         out[13 + outPos] = (byte)(((in[5 + inPos] & 262143) << 4 | (in[6 + inPos] & 262143) >>> 14) & 255);
         out[14 + outPos] = (byte)((in[6 + inPos] & 262143) >>> 6 & 255);
         out[15 + outPos] = (byte)(((in[6 + inPos] & 262143) << 2 | (in[7 + inPos] & 262143) >>> 16) & 255);
         out[16 + outPos] = (byte)((in[7 + inPos] & 262143) >>> 8 & 255);
         out[17 + outPos] = (byte)(in[7 + inPos] & 262143 & 255);
         out[18 + outPos] = (byte)((in[8 + inPos] & 262143) >>> 10 & 255);
         out[19 + outPos] = (byte)((in[8 + inPos] & 262143) >>> 2 & 255);
         out[20 + outPos] = (byte)(((in[8 + inPos] & 262143) << 6 | (in[9 + inPos] & 262143) >>> 12) & 255);
         out[21 + outPos] = (byte)((in[9 + inPos] & 262143) >>> 4 & 255);
         out[22 + outPos] = (byte)(((in[9 + inPos] & 262143) << 4 | (in[10 + inPos] & 262143) >>> 14) & 255);
         out[23 + outPos] = (byte)((in[10 + inPos] & 262143) >>> 6 & 255);
         out[24 + outPos] = (byte)(((in[10 + inPos] & 262143) << 2 | (in[11 + inPos] & 262143) >>> 16) & 255);
         out[25 + outPos] = (byte)((in[11 + inPos] & 262143) >>> 8 & 255);
         out[26 + outPos] = (byte)(in[11 + inPos] & 262143 & 255);
         out[27 + outPos] = (byte)((in[12 + inPos] & 262143) >>> 10 & 255);
         out[28 + outPos] = (byte)((in[12 + inPos] & 262143) >>> 2 & 255);
         out[29 + outPos] = (byte)(((in[12 + inPos] & 262143) << 6 | (in[13 + inPos] & 262143) >>> 12) & 255);
         out[30 + outPos] = (byte)((in[13 + inPos] & 262143) >>> 4 & 255);
         out[31 + outPos] = (byte)(((in[13 + inPos] & 262143) << 4 | (in[14 + inPos] & 262143) >>> 14) & 255);
         out[32 + outPos] = (byte)((in[14 + inPos] & 262143) >>> 6 & 255);
         out[33 + outPos] = (byte)(((in[14 + inPos] & 262143) << 2 | (in[15 + inPos] & 262143) >>> 16) & 255);
         out[34 + outPos] = (byte)((in[15 + inPos] & 262143) >>> 8 & 255);
         out[35 + outPos] = (byte)(in[15 + inPos] & 262143 & 255);
         out[36 + outPos] = (byte)((in[16 + inPos] & 262143) >>> 10 & 255);
         out[37 + outPos] = (byte)((in[16 + inPos] & 262143) >>> 2 & 255);
         out[38 + outPos] = (byte)(((in[16 + inPos] & 262143) << 6 | (in[17 + inPos] & 262143) >>> 12) & 255);
         out[39 + outPos] = (byte)((in[17 + inPos] & 262143) >>> 4 & 255);
         out[40 + outPos] = (byte)(((in[17 + inPos] & 262143) << 4 | (in[18 + inPos] & 262143) >>> 14) & 255);
         out[41 + outPos] = (byte)((in[18 + inPos] & 262143) >>> 6 & 255);
         out[42 + outPos] = (byte)(((in[18 + inPos] & 262143) << 2 | (in[19 + inPos] & 262143) >>> 16) & 255);
         out[43 + outPos] = (byte)((in[19 + inPos] & 262143) >>> 8 & 255);
         out[44 + outPos] = (byte)(in[19 + inPos] & 262143 & 255);
         out[45 + outPos] = (byte)((in[20 + inPos] & 262143) >>> 10 & 255);
         out[46 + outPos] = (byte)((in[20 + inPos] & 262143) >>> 2 & 255);
         out[47 + outPos] = (byte)(((in[20 + inPos] & 262143) << 6 | (in[21 + inPos] & 262143) >>> 12) & 255);
         out[48 + outPos] = (byte)((in[21 + inPos] & 262143) >>> 4 & 255);
         out[49 + outPos] = (byte)(((in[21 + inPos] & 262143) << 4 | (in[22 + inPos] & 262143) >>> 14) & 255);
         out[50 + outPos] = (byte)((in[22 + inPos] & 262143) >>> 6 & 255);
         out[51 + outPos] = (byte)(((in[22 + inPos] & 262143) << 2 | (in[23 + inPos] & 262143) >>> 16) & 255);
         out[52 + outPos] = (byte)((in[23 + inPos] & 262143) >>> 8 & 255);
         out[53 + outPos] = (byte)(in[23 + inPos] & 262143 & 255);
         out[54 + outPos] = (byte)((in[24 + inPos] & 262143) >>> 10 & 255);
         out[55 + outPos] = (byte)((in[24 + inPos] & 262143) >>> 2 & 255);
         out[56 + outPos] = (byte)(((in[24 + inPos] & 262143) << 6 | (in[25 + inPos] & 262143) >>> 12) & 255);
         out[57 + outPos] = (byte)((in[25 + inPos] & 262143) >>> 4 & 255);
         out[58 + outPos] = (byte)(((in[25 + inPos] & 262143) << 4 | (in[26 + inPos] & 262143) >>> 14) & 255);
         out[59 + outPos] = (byte)((in[26 + inPos] & 262143) >>> 6 & 255);
         out[60 + outPos] = (byte)(((in[26 + inPos] & 262143) << 2 | (in[27 + inPos] & 262143) >>> 16) & 255);
         out[61 + outPos] = (byte)((in[27 + inPos] & 262143) >>> 8 & 255);
         out[62 + outPos] = (byte)(in[27 + inPos] & 262143 & 255);
         out[63 + outPos] = (byte)((in[28 + inPos] & 262143) >>> 10 & 255);
         out[64 + outPos] = (byte)((in[28 + inPos] & 262143) >>> 2 & 255);
         out[65 + outPos] = (byte)(((in[28 + inPos] & 262143) << 6 | (in[29 + inPos] & 262143) >>> 12) & 255);
         out[66 + outPos] = (byte)((in[29 + inPos] & 262143) >>> 4 & 255);
         out[67 + outPos] = (byte)(((in[29 + inPos] & 262143) << 4 | (in[30 + inPos] & 262143) >>> 14) & 255);
         out[68 + outPos] = (byte)((in[30 + inPos] & 262143) >>> 6 & 255);
         out[69 + outPos] = (byte)(((in[30 + inPos] & 262143) << 2 | (in[31 + inPos] & 262143) >>> 16) & 255);
         out[70 + outPos] = (byte)((in[31 + inPos] & 262143) >>> 8 & 255);
         out[71 + outPos] = (byte)(in[31 + inPos] & 262143 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 10 & 262143 | in[1 + inPos] << 2 & 1023 | in[2 + inPos] >> 6 & 3;
         out[1 + outPos] = in[2 + inPos] << 12 & 262143 | in[3 + inPos] << 4 & 4095 | in[4 + inPos] >> 4 & 15;
         out[2 + outPos] = in[4 + inPos] << 14 & 262143 | in[5 + inPos] << 6 & 16383 | in[6 + inPos] >> 2 & 63;
         out[3 + outPos] = in[6 + inPos] << 16 & 262143 | in[7 + inPos] << 8 & '\uffff' | in[8 + inPos] & 255;
         out[4 + outPos] = in[9 + inPos] << 10 & 262143 | in[10 + inPos] << 2 & 1023 | in[11 + inPos] >> 6 & 3;
         out[5 + outPos] = in[11 + inPos] << 12 & 262143 | in[12 + inPos] << 4 & 4095 | in[13 + inPos] >> 4 & 15;
         out[6 + outPos] = in[13 + inPos] << 14 & 262143 | in[14 + inPos] << 6 & 16383 | in[15 + inPos] >> 2 & 63;
         out[7 + outPos] = in[15 + inPos] << 16 & 262143 | in[16 + inPos] << 8 & '\uffff' | in[17 + inPos] & 255;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 10 & 262143 | in.get(1 + inPos) << 2 & 1023 | in.get(2 + inPos) >> 6 & 3;
         out[1 + outPos] = in.get(2 + inPos) << 12 & 262143 | in.get(3 + inPos) << 4 & 4095 | in.get(4 + inPos) >> 4 & 15;
         out[2 + outPos] = in.get(4 + inPos) << 14 & 262143 | in.get(5 + inPos) << 6 & 16383 | in.get(6 + inPos) >> 2 & 63;
         out[3 + outPos] = in.get(6 + inPos) << 16 & 262143 | in.get(7 + inPos) << 8 & '\uffff' | in.get(8 + inPos) & 255;
         out[4 + outPos] = in.get(9 + inPos) << 10 & 262143 | in.get(10 + inPos) << 2 & 1023 | in.get(11 + inPos) >> 6 & 3;
         out[5 + outPos] = in.get(11 + inPos) << 12 & 262143 | in.get(12 + inPos) << 4 & 4095 | in.get(13 + inPos) >> 4 & 15;
         out[6 + outPos] = in.get(13 + inPos) << 14 & 262143 | in.get(14 + inPos) << 6 & 16383 | in.get(15 + inPos) >> 2 & 63;
         out[7 + outPos] = in.get(15 + inPos) << 16 & 262143 | in.get(16 + inPos) << 8 & '\uffff' | in.get(17 + inPos) & 255;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 10 & 262143 | in[1 + inPos] << 2 & 1023 | in[2 + inPos] >> 6 & 3;
         out[1 + outPos] = in[2 + inPos] << 12 & 262143 | in[3 + inPos] << 4 & 4095 | in[4 + inPos] >> 4 & 15;
         out[2 + outPos] = in[4 + inPos] << 14 & 262143 | in[5 + inPos] << 6 & 16383 | in[6 + inPos] >> 2 & 63;
         out[3 + outPos] = in[6 + inPos] << 16 & 262143 | in[7 + inPos] << 8 & '\uffff' | in[8 + inPos] & 255;
         out[4 + outPos] = in[9 + inPos] << 10 & 262143 | in[10 + inPos] << 2 & 1023 | in[11 + inPos] >> 6 & 3;
         out[5 + outPos] = in[11 + inPos] << 12 & 262143 | in[12 + inPos] << 4 & 4095 | in[13 + inPos] >> 4 & 15;
         out[6 + outPos] = in[13 + inPos] << 14 & 262143 | in[14 + inPos] << 6 & 16383 | in[15 + inPos] >> 2 & 63;
         out[7 + outPos] = in[15 + inPos] << 16 & 262143 | in[16 + inPos] << 8 & '\uffff' | in[17 + inPos] & 255;
         out[8 + outPos] = in[18 + inPos] << 10 & 262143 | in[19 + inPos] << 2 & 1023 | in[20 + inPos] >> 6 & 3;
         out[9 + outPos] = in[20 + inPos] << 12 & 262143 | in[21 + inPos] << 4 & 4095 | in[22 + inPos] >> 4 & 15;
         out[10 + outPos] = in[22 + inPos] << 14 & 262143 | in[23 + inPos] << 6 & 16383 | in[24 + inPos] >> 2 & 63;
         out[11 + outPos] = in[24 + inPos] << 16 & 262143 | in[25 + inPos] << 8 & '\uffff' | in[26 + inPos] & 255;
         out[12 + outPos] = in[27 + inPos] << 10 & 262143 | in[28 + inPos] << 2 & 1023 | in[29 + inPos] >> 6 & 3;
         out[13 + outPos] = in[29 + inPos] << 12 & 262143 | in[30 + inPos] << 4 & 4095 | in[31 + inPos] >> 4 & 15;
         out[14 + outPos] = in[31 + inPos] << 14 & 262143 | in[32 + inPos] << 6 & 16383 | in[33 + inPos] >> 2 & 63;
         out[15 + outPos] = in[33 + inPos] << 16 & 262143 | in[34 + inPos] << 8 & '\uffff' | in[35 + inPos] & 255;
         out[16 + outPos] = in[36 + inPos] << 10 & 262143 | in[37 + inPos] << 2 & 1023 | in[38 + inPos] >> 6 & 3;
         out[17 + outPos] = in[38 + inPos] << 12 & 262143 | in[39 + inPos] << 4 & 4095 | in[40 + inPos] >> 4 & 15;
         out[18 + outPos] = in[40 + inPos] << 14 & 262143 | in[41 + inPos] << 6 & 16383 | in[42 + inPos] >> 2 & 63;
         out[19 + outPos] = in[42 + inPos] << 16 & 262143 | in[43 + inPos] << 8 & '\uffff' | in[44 + inPos] & 255;
         out[20 + outPos] = in[45 + inPos] << 10 & 262143 | in[46 + inPos] << 2 & 1023 | in[47 + inPos] >> 6 & 3;
         out[21 + outPos] = in[47 + inPos] << 12 & 262143 | in[48 + inPos] << 4 & 4095 | in[49 + inPos] >> 4 & 15;
         out[22 + outPos] = in[49 + inPos] << 14 & 262143 | in[50 + inPos] << 6 & 16383 | in[51 + inPos] >> 2 & 63;
         out[23 + outPos] = in[51 + inPos] << 16 & 262143 | in[52 + inPos] << 8 & '\uffff' | in[53 + inPos] & 255;
         out[24 + outPos] = in[54 + inPos] << 10 & 262143 | in[55 + inPos] << 2 & 1023 | in[56 + inPos] >> 6 & 3;
         out[25 + outPos] = in[56 + inPos] << 12 & 262143 | in[57 + inPos] << 4 & 4095 | in[58 + inPos] >> 4 & 15;
         out[26 + outPos] = in[58 + inPos] << 14 & 262143 | in[59 + inPos] << 6 & 16383 | in[60 + inPos] >> 2 & 63;
         out[27 + outPos] = in[60 + inPos] << 16 & 262143 | in[61 + inPos] << 8 & '\uffff' | in[62 + inPos] & 255;
         out[28 + outPos] = in[63 + inPos] << 10 & 262143 | in[64 + inPos] << 2 & 1023 | in[65 + inPos] >> 6 & 3;
         out[29 + outPos] = in[65 + inPos] << 12 & 262143 | in[66 + inPos] << 4 & 4095 | in[67 + inPos] >> 4 & 15;
         out[30 + outPos] = in[67 + inPos] << 14 & 262143 | in[68 + inPos] << 6 & 16383 | in[69 + inPos] >> 2 & 63;
         out[31 + outPos] = in[69 + inPos] << 16 & 262143 | in[70 + inPos] << 8 & '\uffff' | in[71 + inPos] & 255;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 10 & 262143 | in.get(1 + inPos) << 2 & 1023 | in.get(2 + inPos) >> 6 & 3;
         out[1 + outPos] = in.get(2 + inPos) << 12 & 262143 | in.get(3 + inPos) << 4 & 4095 | in.get(4 + inPos) >> 4 & 15;
         out[2 + outPos] = in.get(4 + inPos) << 14 & 262143 | in.get(5 + inPos) << 6 & 16383 | in.get(6 + inPos) >> 2 & 63;
         out[3 + outPos] = in.get(6 + inPos) << 16 & 262143 | in.get(7 + inPos) << 8 & '\uffff' | in.get(8 + inPos) & 255;
         out[4 + outPos] = in.get(9 + inPos) << 10 & 262143 | in.get(10 + inPos) << 2 & 1023 | in.get(11 + inPos) >> 6 & 3;
         out[5 + outPos] = in.get(11 + inPos) << 12 & 262143 | in.get(12 + inPos) << 4 & 4095 | in.get(13 + inPos) >> 4 & 15;
         out[6 + outPos] = in.get(13 + inPos) << 14 & 262143 | in.get(14 + inPos) << 6 & 16383 | in.get(15 + inPos) >> 2 & 63;
         out[7 + outPos] = in.get(15 + inPos) << 16 & 262143 | in.get(16 + inPos) << 8 & '\uffff' | in.get(17 + inPos) & 255;
         out[8 + outPos] = in.get(18 + inPos) << 10 & 262143 | in.get(19 + inPos) << 2 & 1023 | in.get(20 + inPos) >> 6 & 3;
         out[9 + outPos] = in.get(20 + inPos) << 12 & 262143 | in.get(21 + inPos) << 4 & 4095 | in.get(22 + inPos) >> 4 & 15;
         out[10 + outPos] = in.get(22 + inPos) << 14 & 262143 | in.get(23 + inPos) << 6 & 16383 | in.get(24 + inPos) >> 2 & 63;
         out[11 + outPos] = in.get(24 + inPos) << 16 & 262143 | in.get(25 + inPos) << 8 & '\uffff' | in.get(26 + inPos) & 255;
         out[12 + outPos] = in.get(27 + inPos) << 10 & 262143 | in.get(28 + inPos) << 2 & 1023 | in.get(29 + inPos) >> 6 & 3;
         out[13 + outPos] = in.get(29 + inPos) << 12 & 262143 | in.get(30 + inPos) << 4 & 4095 | in.get(31 + inPos) >> 4 & 15;
         out[14 + outPos] = in.get(31 + inPos) << 14 & 262143 | in.get(32 + inPos) << 6 & 16383 | in.get(33 + inPos) >> 2 & 63;
         out[15 + outPos] = in.get(33 + inPos) << 16 & 262143 | in.get(34 + inPos) << 8 & '\uffff' | in.get(35 + inPos) & 255;
         out[16 + outPos] = in.get(36 + inPos) << 10 & 262143 | in.get(37 + inPos) << 2 & 1023 | in.get(38 + inPos) >> 6 & 3;
         out[17 + outPos] = in.get(38 + inPos) << 12 & 262143 | in.get(39 + inPos) << 4 & 4095 | in.get(40 + inPos) >> 4 & 15;
         out[18 + outPos] = in.get(40 + inPos) << 14 & 262143 | in.get(41 + inPos) << 6 & 16383 | in.get(42 + inPos) >> 2 & 63;
         out[19 + outPos] = in.get(42 + inPos) << 16 & 262143 | in.get(43 + inPos) << 8 & '\uffff' | in.get(44 + inPos) & 255;
         out[20 + outPos] = in.get(45 + inPos) << 10 & 262143 | in.get(46 + inPos) << 2 & 1023 | in.get(47 + inPos) >> 6 & 3;
         out[21 + outPos] = in.get(47 + inPos) << 12 & 262143 | in.get(48 + inPos) << 4 & 4095 | in.get(49 + inPos) >> 4 & 15;
         out[22 + outPos] = in.get(49 + inPos) << 14 & 262143 | in.get(50 + inPos) << 6 & 16383 | in.get(51 + inPos) >> 2 & 63;
         out[23 + outPos] = in.get(51 + inPos) << 16 & 262143 | in.get(52 + inPos) << 8 & '\uffff' | in.get(53 + inPos) & 255;
         out[24 + outPos] = in.get(54 + inPos) << 10 & 262143 | in.get(55 + inPos) << 2 & 1023 | in.get(56 + inPos) >> 6 & 3;
         out[25 + outPos] = in.get(56 + inPos) << 12 & 262143 | in.get(57 + inPos) << 4 & 4095 | in.get(58 + inPos) >> 4 & 15;
         out[26 + outPos] = in.get(58 + inPos) << 14 & 262143 | in.get(59 + inPos) << 6 & 16383 | in.get(60 + inPos) >> 2 & 63;
         out[27 + outPos] = in.get(60 + inPos) << 16 & 262143 | in.get(61 + inPos) << 8 & '\uffff' | in.get(62 + inPos) & 255;
         out[28 + outPos] = in.get(63 + inPos) << 10 & 262143 | in.get(64 + inPos) << 2 & 1023 | in.get(65 + inPos) >> 6 & 3;
         out[29 + outPos] = in.get(65 + inPos) << 12 & 262143 | in.get(66 + inPos) << 4 & 4095 | in.get(67 + inPos) >> 4 & 15;
         out[30 + outPos] = in.get(67 + inPos) << 14 & 262143 | in.get(68 + inPos) << 6 & 16383 | in.get(69 + inPos) >> 2 & 63;
         out[31 + outPos] = in.get(69 + inPos) << 16 & 262143 | in.get(70 + inPos) << 8 & '\uffff' | in.get(71 + inPos) & 255;
      }
   }

   private static final class Packer19 extends BytePacker {
      private Packer19() {
         super(19);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 524287) >>> 11 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 524287) >>> 3 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 524287) << 5 | (in[1 + inPos] & 524287) >>> 14) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 524287) >>> 6 & 255);
         out[4 + outPos] = (byte)(((in[1 + inPos] & 524287) << 2 | (in[2 + inPos] & 524287) >>> 17) & 255);
         out[5 + outPos] = (byte)((in[2 + inPos] & 524287) >>> 9 & 255);
         out[6 + outPos] = (byte)((in[2 + inPos] & 524287) >>> 1 & 255);
         out[7 + outPos] = (byte)(((in[2 + inPos] & 524287) << 7 | (in[3 + inPos] & 524287) >>> 12) & 255);
         out[8 + outPos] = (byte)((in[3 + inPos] & 524287) >>> 4 & 255);
         out[9 + outPos] = (byte)(((in[3 + inPos] & 524287) << 4 | (in[4 + inPos] & 524287) >>> 15) & 255);
         out[10 + outPos] = (byte)((in[4 + inPos] & 524287) >>> 7 & 255);
         out[11 + outPos] = (byte)(((in[4 + inPos] & 524287) << 1 | (in[5 + inPos] & 524287) >>> 18) & 255);
         out[12 + outPos] = (byte)((in[5 + inPos] & 524287) >>> 10 & 255);
         out[13 + outPos] = (byte)((in[5 + inPos] & 524287) >>> 2 & 255);
         out[14 + outPos] = (byte)(((in[5 + inPos] & 524287) << 6 | (in[6 + inPos] & 524287) >>> 13) & 255);
         out[15 + outPos] = (byte)((in[6 + inPos] & 524287) >>> 5 & 255);
         out[16 + outPos] = (byte)(((in[6 + inPos] & 524287) << 3 | (in[7 + inPos] & 524287) >>> 16) & 255);
         out[17 + outPos] = (byte)((in[7 + inPos] & 524287) >>> 8 & 255);
         out[18 + outPos] = (byte)(in[7 + inPos] & 524287 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 524287) >>> 11 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 524287) >>> 3 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 524287) << 5 | (in[1 + inPos] & 524287) >>> 14) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 524287) >>> 6 & 255);
         out[4 + outPos] = (byte)(((in[1 + inPos] & 524287) << 2 | (in[2 + inPos] & 524287) >>> 17) & 255);
         out[5 + outPos] = (byte)((in[2 + inPos] & 524287) >>> 9 & 255);
         out[6 + outPos] = (byte)((in[2 + inPos] & 524287) >>> 1 & 255);
         out[7 + outPos] = (byte)(((in[2 + inPos] & 524287) << 7 | (in[3 + inPos] & 524287) >>> 12) & 255);
         out[8 + outPos] = (byte)((in[3 + inPos] & 524287) >>> 4 & 255);
         out[9 + outPos] = (byte)(((in[3 + inPos] & 524287) << 4 | (in[4 + inPos] & 524287) >>> 15) & 255);
         out[10 + outPos] = (byte)((in[4 + inPos] & 524287) >>> 7 & 255);
         out[11 + outPos] = (byte)(((in[4 + inPos] & 524287) << 1 | (in[5 + inPos] & 524287) >>> 18) & 255);
         out[12 + outPos] = (byte)((in[5 + inPos] & 524287) >>> 10 & 255);
         out[13 + outPos] = (byte)((in[5 + inPos] & 524287) >>> 2 & 255);
         out[14 + outPos] = (byte)(((in[5 + inPos] & 524287) << 6 | (in[6 + inPos] & 524287) >>> 13) & 255);
         out[15 + outPos] = (byte)((in[6 + inPos] & 524287) >>> 5 & 255);
         out[16 + outPos] = (byte)(((in[6 + inPos] & 524287) << 3 | (in[7 + inPos] & 524287) >>> 16) & 255);
         out[17 + outPos] = (byte)((in[7 + inPos] & 524287) >>> 8 & 255);
         out[18 + outPos] = (byte)(in[7 + inPos] & 524287 & 255);
         out[19 + outPos] = (byte)((in[8 + inPos] & 524287) >>> 11 & 255);
         out[20 + outPos] = (byte)((in[8 + inPos] & 524287) >>> 3 & 255);
         out[21 + outPos] = (byte)(((in[8 + inPos] & 524287) << 5 | (in[9 + inPos] & 524287) >>> 14) & 255);
         out[22 + outPos] = (byte)((in[9 + inPos] & 524287) >>> 6 & 255);
         out[23 + outPos] = (byte)(((in[9 + inPos] & 524287) << 2 | (in[10 + inPos] & 524287) >>> 17) & 255);
         out[24 + outPos] = (byte)((in[10 + inPos] & 524287) >>> 9 & 255);
         out[25 + outPos] = (byte)((in[10 + inPos] & 524287) >>> 1 & 255);
         out[26 + outPos] = (byte)(((in[10 + inPos] & 524287) << 7 | (in[11 + inPos] & 524287) >>> 12) & 255);
         out[27 + outPos] = (byte)((in[11 + inPos] & 524287) >>> 4 & 255);
         out[28 + outPos] = (byte)(((in[11 + inPos] & 524287) << 4 | (in[12 + inPos] & 524287) >>> 15) & 255);
         out[29 + outPos] = (byte)((in[12 + inPos] & 524287) >>> 7 & 255);
         out[30 + outPos] = (byte)(((in[12 + inPos] & 524287) << 1 | (in[13 + inPos] & 524287) >>> 18) & 255);
         out[31 + outPos] = (byte)((in[13 + inPos] & 524287) >>> 10 & 255);
         out[32 + outPos] = (byte)((in[13 + inPos] & 524287) >>> 2 & 255);
         out[33 + outPos] = (byte)(((in[13 + inPos] & 524287) << 6 | (in[14 + inPos] & 524287) >>> 13) & 255);
         out[34 + outPos] = (byte)((in[14 + inPos] & 524287) >>> 5 & 255);
         out[35 + outPos] = (byte)(((in[14 + inPos] & 524287) << 3 | (in[15 + inPos] & 524287) >>> 16) & 255);
         out[36 + outPos] = (byte)((in[15 + inPos] & 524287) >>> 8 & 255);
         out[37 + outPos] = (byte)(in[15 + inPos] & 524287 & 255);
         out[38 + outPos] = (byte)((in[16 + inPos] & 524287) >>> 11 & 255);
         out[39 + outPos] = (byte)((in[16 + inPos] & 524287) >>> 3 & 255);
         out[40 + outPos] = (byte)(((in[16 + inPos] & 524287) << 5 | (in[17 + inPos] & 524287) >>> 14) & 255);
         out[41 + outPos] = (byte)((in[17 + inPos] & 524287) >>> 6 & 255);
         out[42 + outPos] = (byte)(((in[17 + inPos] & 524287) << 2 | (in[18 + inPos] & 524287) >>> 17) & 255);
         out[43 + outPos] = (byte)((in[18 + inPos] & 524287) >>> 9 & 255);
         out[44 + outPos] = (byte)((in[18 + inPos] & 524287) >>> 1 & 255);
         out[45 + outPos] = (byte)(((in[18 + inPos] & 524287) << 7 | (in[19 + inPos] & 524287) >>> 12) & 255);
         out[46 + outPos] = (byte)((in[19 + inPos] & 524287) >>> 4 & 255);
         out[47 + outPos] = (byte)(((in[19 + inPos] & 524287) << 4 | (in[20 + inPos] & 524287) >>> 15) & 255);
         out[48 + outPos] = (byte)((in[20 + inPos] & 524287) >>> 7 & 255);
         out[49 + outPos] = (byte)(((in[20 + inPos] & 524287) << 1 | (in[21 + inPos] & 524287) >>> 18) & 255);
         out[50 + outPos] = (byte)((in[21 + inPos] & 524287) >>> 10 & 255);
         out[51 + outPos] = (byte)((in[21 + inPos] & 524287) >>> 2 & 255);
         out[52 + outPos] = (byte)(((in[21 + inPos] & 524287) << 6 | (in[22 + inPos] & 524287) >>> 13) & 255);
         out[53 + outPos] = (byte)((in[22 + inPos] & 524287) >>> 5 & 255);
         out[54 + outPos] = (byte)(((in[22 + inPos] & 524287) << 3 | (in[23 + inPos] & 524287) >>> 16) & 255);
         out[55 + outPos] = (byte)((in[23 + inPos] & 524287) >>> 8 & 255);
         out[56 + outPos] = (byte)(in[23 + inPos] & 524287 & 255);
         out[57 + outPos] = (byte)((in[24 + inPos] & 524287) >>> 11 & 255);
         out[58 + outPos] = (byte)((in[24 + inPos] & 524287) >>> 3 & 255);
         out[59 + outPos] = (byte)(((in[24 + inPos] & 524287) << 5 | (in[25 + inPos] & 524287) >>> 14) & 255);
         out[60 + outPos] = (byte)((in[25 + inPos] & 524287) >>> 6 & 255);
         out[61 + outPos] = (byte)(((in[25 + inPos] & 524287) << 2 | (in[26 + inPos] & 524287) >>> 17) & 255);
         out[62 + outPos] = (byte)((in[26 + inPos] & 524287) >>> 9 & 255);
         out[63 + outPos] = (byte)((in[26 + inPos] & 524287) >>> 1 & 255);
         out[64 + outPos] = (byte)(((in[26 + inPos] & 524287) << 7 | (in[27 + inPos] & 524287) >>> 12) & 255);
         out[65 + outPos] = (byte)((in[27 + inPos] & 524287) >>> 4 & 255);
         out[66 + outPos] = (byte)(((in[27 + inPos] & 524287) << 4 | (in[28 + inPos] & 524287) >>> 15) & 255);
         out[67 + outPos] = (byte)((in[28 + inPos] & 524287) >>> 7 & 255);
         out[68 + outPos] = (byte)(((in[28 + inPos] & 524287) << 1 | (in[29 + inPos] & 524287) >>> 18) & 255);
         out[69 + outPos] = (byte)((in[29 + inPos] & 524287) >>> 10 & 255);
         out[70 + outPos] = (byte)((in[29 + inPos] & 524287) >>> 2 & 255);
         out[71 + outPos] = (byte)(((in[29 + inPos] & 524287) << 6 | (in[30 + inPos] & 524287) >>> 13) & 255);
         out[72 + outPos] = (byte)((in[30 + inPos] & 524287) >>> 5 & 255);
         out[73 + outPos] = (byte)(((in[30 + inPos] & 524287) << 3 | (in[31 + inPos] & 524287) >>> 16) & 255);
         out[74 + outPos] = (byte)((in[31 + inPos] & 524287) >>> 8 & 255);
         out[75 + outPos] = (byte)(in[31 + inPos] & 524287 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 11 & 524287 | in[1 + inPos] << 3 & 2047 | in[2 + inPos] >> 5 & 7;
         out[1 + outPos] = in[2 + inPos] << 14 & 524287 | in[3 + inPos] << 6 & 16383 | in[4 + inPos] >> 2 & 63;
         out[2 + outPos] = in[4 + inPos] << 17 & 524287 | in[5 + inPos] << 9 & 131071 | in[6 + inPos] << 1 & 511 | in[7 + inPos] >> 7 & 1;
         out[3 + outPos] = in[7 + inPos] << 12 & 524287 | in[8 + inPos] << 4 & 4095 | in[9 + inPos] >> 4 & 15;
         out[4 + outPos] = in[9 + inPos] << 15 & 524287 | in[10 + inPos] << 7 & 32767 | in[11 + inPos] >> 1 & 127;
         out[5 + outPos] = in[11 + inPos] << 18 & 524287 | in[12 + inPos] << 10 & 262143 | in[13 + inPos] << 2 & 1023 | in[14 + inPos] >> 6 & 3;
         out[6 + outPos] = in[14 + inPos] << 13 & 524287 | in[15 + inPos] << 5 & 8191 | in[16 + inPos] >> 3 & 31;
         out[7 + outPos] = in[16 + inPos] << 16 & 524287 | in[17 + inPos] << 8 & '\uffff' | in[18 + inPos] & 255;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 11 & 524287 | in.get(1 + inPos) << 3 & 2047 | in.get(2 + inPos) >> 5 & 7;
         out[1 + outPos] = in.get(2 + inPos) << 14 & 524287 | in.get(3 + inPos) << 6 & 16383 | in.get(4 + inPos) >> 2 & 63;
         out[2 + outPos] = in.get(4 + inPos) << 17 & 524287 | in.get(5 + inPos) << 9 & 131071 | in.get(6 + inPos) << 1 & 511 | in.get(7 + inPos) >> 7 & 1;
         out[3 + outPos] = in.get(7 + inPos) << 12 & 524287 | in.get(8 + inPos) << 4 & 4095 | in.get(9 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(9 + inPos) << 15 & 524287 | in.get(10 + inPos) << 7 & 32767 | in.get(11 + inPos) >> 1 & 127;
         out[5 + outPos] = in.get(11 + inPos) << 18 & 524287 | in.get(12 + inPos) << 10 & 262143 | in.get(13 + inPos) << 2 & 1023 | in.get(14 + inPos) >> 6 & 3;
         out[6 + outPos] = in.get(14 + inPos) << 13 & 524287 | in.get(15 + inPos) << 5 & 8191 | in.get(16 + inPos) >> 3 & 31;
         out[7 + outPos] = in.get(16 + inPos) << 16 & 524287 | in.get(17 + inPos) << 8 & '\uffff' | in.get(18 + inPos) & 255;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 11 & 524287 | in[1 + inPos] << 3 & 2047 | in[2 + inPos] >> 5 & 7;
         out[1 + outPos] = in[2 + inPos] << 14 & 524287 | in[3 + inPos] << 6 & 16383 | in[4 + inPos] >> 2 & 63;
         out[2 + outPos] = in[4 + inPos] << 17 & 524287 | in[5 + inPos] << 9 & 131071 | in[6 + inPos] << 1 & 511 | in[7 + inPos] >> 7 & 1;
         out[3 + outPos] = in[7 + inPos] << 12 & 524287 | in[8 + inPos] << 4 & 4095 | in[9 + inPos] >> 4 & 15;
         out[4 + outPos] = in[9 + inPos] << 15 & 524287 | in[10 + inPos] << 7 & 32767 | in[11 + inPos] >> 1 & 127;
         out[5 + outPos] = in[11 + inPos] << 18 & 524287 | in[12 + inPos] << 10 & 262143 | in[13 + inPos] << 2 & 1023 | in[14 + inPos] >> 6 & 3;
         out[6 + outPos] = in[14 + inPos] << 13 & 524287 | in[15 + inPos] << 5 & 8191 | in[16 + inPos] >> 3 & 31;
         out[7 + outPos] = in[16 + inPos] << 16 & 524287 | in[17 + inPos] << 8 & '\uffff' | in[18 + inPos] & 255;
         out[8 + outPos] = in[19 + inPos] << 11 & 524287 | in[20 + inPos] << 3 & 2047 | in[21 + inPos] >> 5 & 7;
         out[9 + outPos] = in[21 + inPos] << 14 & 524287 | in[22 + inPos] << 6 & 16383 | in[23 + inPos] >> 2 & 63;
         out[10 + outPos] = in[23 + inPos] << 17 & 524287 | in[24 + inPos] << 9 & 131071 | in[25 + inPos] << 1 & 511 | in[26 + inPos] >> 7 & 1;
         out[11 + outPos] = in[26 + inPos] << 12 & 524287 | in[27 + inPos] << 4 & 4095 | in[28 + inPos] >> 4 & 15;
         out[12 + outPos] = in[28 + inPos] << 15 & 524287 | in[29 + inPos] << 7 & 32767 | in[30 + inPos] >> 1 & 127;
         out[13 + outPos] = in[30 + inPos] << 18 & 524287 | in[31 + inPos] << 10 & 262143 | in[32 + inPos] << 2 & 1023 | in[33 + inPos] >> 6 & 3;
         out[14 + outPos] = in[33 + inPos] << 13 & 524287 | in[34 + inPos] << 5 & 8191 | in[35 + inPos] >> 3 & 31;
         out[15 + outPos] = in[35 + inPos] << 16 & 524287 | in[36 + inPos] << 8 & '\uffff' | in[37 + inPos] & 255;
         out[16 + outPos] = in[38 + inPos] << 11 & 524287 | in[39 + inPos] << 3 & 2047 | in[40 + inPos] >> 5 & 7;
         out[17 + outPos] = in[40 + inPos] << 14 & 524287 | in[41 + inPos] << 6 & 16383 | in[42 + inPos] >> 2 & 63;
         out[18 + outPos] = in[42 + inPos] << 17 & 524287 | in[43 + inPos] << 9 & 131071 | in[44 + inPos] << 1 & 511 | in[45 + inPos] >> 7 & 1;
         out[19 + outPos] = in[45 + inPos] << 12 & 524287 | in[46 + inPos] << 4 & 4095 | in[47 + inPos] >> 4 & 15;
         out[20 + outPos] = in[47 + inPos] << 15 & 524287 | in[48 + inPos] << 7 & 32767 | in[49 + inPos] >> 1 & 127;
         out[21 + outPos] = in[49 + inPos] << 18 & 524287 | in[50 + inPos] << 10 & 262143 | in[51 + inPos] << 2 & 1023 | in[52 + inPos] >> 6 & 3;
         out[22 + outPos] = in[52 + inPos] << 13 & 524287 | in[53 + inPos] << 5 & 8191 | in[54 + inPos] >> 3 & 31;
         out[23 + outPos] = in[54 + inPos] << 16 & 524287 | in[55 + inPos] << 8 & '\uffff' | in[56 + inPos] & 255;
         out[24 + outPos] = in[57 + inPos] << 11 & 524287 | in[58 + inPos] << 3 & 2047 | in[59 + inPos] >> 5 & 7;
         out[25 + outPos] = in[59 + inPos] << 14 & 524287 | in[60 + inPos] << 6 & 16383 | in[61 + inPos] >> 2 & 63;
         out[26 + outPos] = in[61 + inPos] << 17 & 524287 | in[62 + inPos] << 9 & 131071 | in[63 + inPos] << 1 & 511 | in[64 + inPos] >> 7 & 1;
         out[27 + outPos] = in[64 + inPos] << 12 & 524287 | in[65 + inPos] << 4 & 4095 | in[66 + inPos] >> 4 & 15;
         out[28 + outPos] = in[66 + inPos] << 15 & 524287 | in[67 + inPos] << 7 & 32767 | in[68 + inPos] >> 1 & 127;
         out[29 + outPos] = in[68 + inPos] << 18 & 524287 | in[69 + inPos] << 10 & 262143 | in[70 + inPos] << 2 & 1023 | in[71 + inPos] >> 6 & 3;
         out[30 + outPos] = in[71 + inPos] << 13 & 524287 | in[72 + inPos] << 5 & 8191 | in[73 + inPos] >> 3 & 31;
         out[31 + outPos] = in[73 + inPos] << 16 & 524287 | in[74 + inPos] << 8 & '\uffff' | in[75 + inPos] & 255;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 11 & 524287 | in.get(1 + inPos) << 3 & 2047 | in.get(2 + inPos) >> 5 & 7;
         out[1 + outPos] = in.get(2 + inPos) << 14 & 524287 | in.get(3 + inPos) << 6 & 16383 | in.get(4 + inPos) >> 2 & 63;
         out[2 + outPos] = in.get(4 + inPos) << 17 & 524287 | in.get(5 + inPos) << 9 & 131071 | in.get(6 + inPos) << 1 & 511 | in.get(7 + inPos) >> 7 & 1;
         out[3 + outPos] = in.get(7 + inPos) << 12 & 524287 | in.get(8 + inPos) << 4 & 4095 | in.get(9 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(9 + inPos) << 15 & 524287 | in.get(10 + inPos) << 7 & 32767 | in.get(11 + inPos) >> 1 & 127;
         out[5 + outPos] = in.get(11 + inPos) << 18 & 524287 | in.get(12 + inPos) << 10 & 262143 | in.get(13 + inPos) << 2 & 1023 | in.get(14 + inPos) >> 6 & 3;
         out[6 + outPos] = in.get(14 + inPos) << 13 & 524287 | in.get(15 + inPos) << 5 & 8191 | in.get(16 + inPos) >> 3 & 31;
         out[7 + outPos] = in.get(16 + inPos) << 16 & 524287 | in.get(17 + inPos) << 8 & '\uffff' | in.get(18 + inPos) & 255;
         out[8 + outPos] = in.get(19 + inPos) << 11 & 524287 | in.get(20 + inPos) << 3 & 2047 | in.get(21 + inPos) >> 5 & 7;
         out[9 + outPos] = in.get(21 + inPos) << 14 & 524287 | in.get(22 + inPos) << 6 & 16383 | in.get(23 + inPos) >> 2 & 63;
         out[10 + outPos] = in.get(23 + inPos) << 17 & 524287 | in.get(24 + inPos) << 9 & 131071 | in.get(25 + inPos) << 1 & 511 | in.get(26 + inPos) >> 7 & 1;
         out[11 + outPos] = in.get(26 + inPos) << 12 & 524287 | in.get(27 + inPos) << 4 & 4095 | in.get(28 + inPos) >> 4 & 15;
         out[12 + outPos] = in.get(28 + inPos) << 15 & 524287 | in.get(29 + inPos) << 7 & 32767 | in.get(30 + inPos) >> 1 & 127;
         out[13 + outPos] = in.get(30 + inPos) << 18 & 524287 | in.get(31 + inPos) << 10 & 262143 | in.get(32 + inPos) << 2 & 1023 | in.get(33 + inPos) >> 6 & 3;
         out[14 + outPos] = in.get(33 + inPos) << 13 & 524287 | in.get(34 + inPos) << 5 & 8191 | in.get(35 + inPos) >> 3 & 31;
         out[15 + outPos] = in.get(35 + inPos) << 16 & 524287 | in.get(36 + inPos) << 8 & '\uffff' | in.get(37 + inPos) & 255;
         out[16 + outPos] = in.get(38 + inPos) << 11 & 524287 | in.get(39 + inPos) << 3 & 2047 | in.get(40 + inPos) >> 5 & 7;
         out[17 + outPos] = in.get(40 + inPos) << 14 & 524287 | in.get(41 + inPos) << 6 & 16383 | in.get(42 + inPos) >> 2 & 63;
         out[18 + outPos] = in.get(42 + inPos) << 17 & 524287 | in.get(43 + inPos) << 9 & 131071 | in.get(44 + inPos) << 1 & 511 | in.get(45 + inPos) >> 7 & 1;
         out[19 + outPos] = in.get(45 + inPos) << 12 & 524287 | in.get(46 + inPos) << 4 & 4095 | in.get(47 + inPos) >> 4 & 15;
         out[20 + outPos] = in.get(47 + inPos) << 15 & 524287 | in.get(48 + inPos) << 7 & 32767 | in.get(49 + inPos) >> 1 & 127;
         out[21 + outPos] = in.get(49 + inPos) << 18 & 524287 | in.get(50 + inPos) << 10 & 262143 | in.get(51 + inPos) << 2 & 1023 | in.get(52 + inPos) >> 6 & 3;
         out[22 + outPos] = in.get(52 + inPos) << 13 & 524287 | in.get(53 + inPos) << 5 & 8191 | in.get(54 + inPos) >> 3 & 31;
         out[23 + outPos] = in.get(54 + inPos) << 16 & 524287 | in.get(55 + inPos) << 8 & '\uffff' | in.get(56 + inPos) & 255;
         out[24 + outPos] = in.get(57 + inPos) << 11 & 524287 | in.get(58 + inPos) << 3 & 2047 | in.get(59 + inPos) >> 5 & 7;
         out[25 + outPos] = in.get(59 + inPos) << 14 & 524287 | in.get(60 + inPos) << 6 & 16383 | in.get(61 + inPos) >> 2 & 63;
         out[26 + outPos] = in.get(61 + inPos) << 17 & 524287 | in.get(62 + inPos) << 9 & 131071 | in.get(63 + inPos) << 1 & 511 | in.get(64 + inPos) >> 7 & 1;
         out[27 + outPos] = in.get(64 + inPos) << 12 & 524287 | in.get(65 + inPos) << 4 & 4095 | in.get(66 + inPos) >> 4 & 15;
         out[28 + outPos] = in.get(66 + inPos) << 15 & 524287 | in.get(67 + inPos) << 7 & 32767 | in.get(68 + inPos) >> 1 & 127;
         out[29 + outPos] = in.get(68 + inPos) << 18 & 524287 | in.get(69 + inPos) << 10 & 262143 | in.get(70 + inPos) << 2 & 1023 | in.get(71 + inPos) >> 6 & 3;
         out[30 + outPos] = in.get(71 + inPos) << 13 & 524287 | in.get(72 + inPos) << 5 & 8191 | in.get(73 + inPos) >> 3 & 31;
         out[31 + outPos] = in.get(73 + inPos) << 16 & 524287 | in.get(74 + inPos) << 8 & '\uffff' | in.get(75 + inPos) & 255;
      }
   }

   private static final class Packer20 extends BytePacker {
      private Packer20() {
         super(20);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 1048575) >>> 12 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 1048575) >>> 4 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 1048575) << 4 | (in[1 + inPos] & 1048575) >>> 16) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 1048575) >>> 8 & 255);
         out[4 + outPos] = (byte)(in[1 + inPos] & 1048575 & 255);
         out[5 + outPos] = (byte)((in[2 + inPos] & 1048575) >>> 12 & 255);
         out[6 + outPos] = (byte)((in[2 + inPos] & 1048575) >>> 4 & 255);
         out[7 + outPos] = (byte)(((in[2 + inPos] & 1048575) << 4 | (in[3 + inPos] & 1048575) >>> 16) & 255);
         out[8 + outPos] = (byte)((in[3 + inPos] & 1048575) >>> 8 & 255);
         out[9 + outPos] = (byte)(in[3 + inPos] & 1048575 & 255);
         out[10 + outPos] = (byte)((in[4 + inPos] & 1048575) >>> 12 & 255);
         out[11 + outPos] = (byte)((in[4 + inPos] & 1048575) >>> 4 & 255);
         out[12 + outPos] = (byte)(((in[4 + inPos] & 1048575) << 4 | (in[5 + inPos] & 1048575) >>> 16) & 255);
         out[13 + outPos] = (byte)((in[5 + inPos] & 1048575) >>> 8 & 255);
         out[14 + outPos] = (byte)(in[5 + inPos] & 1048575 & 255);
         out[15 + outPos] = (byte)((in[6 + inPos] & 1048575) >>> 12 & 255);
         out[16 + outPos] = (byte)((in[6 + inPos] & 1048575) >>> 4 & 255);
         out[17 + outPos] = (byte)(((in[6 + inPos] & 1048575) << 4 | (in[7 + inPos] & 1048575) >>> 16) & 255);
         out[18 + outPos] = (byte)((in[7 + inPos] & 1048575) >>> 8 & 255);
         out[19 + outPos] = (byte)(in[7 + inPos] & 1048575 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 1048575) >>> 12 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 1048575) >>> 4 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 1048575) << 4 | (in[1 + inPos] & 1048575) >>> 16) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 1048575) >>> 8 & 255);
         out[4 + outPos] = (byte)(in[1 + inPos] & 1048575 & 255);
         out[5 + outPos] = (byte)((in[2 + inPos] & 1048575) >>> 12 & 255);
         out[6 + outPos] = (byte)((in[2 + inPos] & 1048575) >>> 4 & 255);
         out[7 + outPos] = (byte)(((in[2 + inPos] & 1048575) << 4 | (in[3 + inPos] & 1048575) >>> 16) & 255);
         out[8 + outPos] = (byte)((in[3 + inPos] & 1048575) >>> 8 & 255);
         out[9 + outPos] = (byte)(in[3 + inPos] & 1048575 & 255);
         out[10 + outPos] = (byte)((in[4 + inPos] & 1048575) >>> 12 & 255);
         out[11 + outPos] = (byte)((in[4 + inPos] & 1048575) >>> 4 & 255);
         out[12 + outPos] = (byte)(((in[4 + inPos] & 1048575) << 4 | (in[5 + inPos] & 1048575) >>> 16) & 255);
         out[13 + outPos] = (byte)((in[5 + inPos] & 1048575) >>> 8 & 255);
         out[14 + outPos] = (byte)(in[5 + inPos] & 1048575 & 255);
         out[15 + outPos] = (byte)((in[6 + inPos] & 1048575) >>> 12 & 255);
         out[16 + outPos] = (byte)((in[6 + inPos] & 1048575) >>> 4 & 255);
         out[17 + outPos] = (byte)(((in[6 + inPos] & 1048575) << 4 | (in[7 + inPos] & 1048575) >>> 16) & 255);
         out[18 + outPos] = (byte)((in[7 + inPos] & 1048575) >>> 8 & 255);
         out[19 + outPos] = (byte)(in[7 + inPos] & 1048575 & 255);
         out[20 + outPos] = (byte)((in[8 + inPos] & 1048575) >>> 12 & 255);
         out[21 + outPos] = (byte)((in[8 + inPos] & 1048575) >>> 4 & 255);
         out[22 + outPos] = (byte)(((in[8 + inPos] & 1048575) << 4 | (in[9 + inPos] & 1048575) >>> 16) & 255);
         out[23 + outPos] = (byte)((in[9 + inPos] & 1048575) >>> 8 & 255);
         out[24 + outPos] = (byte)(in[9 + inPos] & 1048575 & 255);
         out[25 + outPos] = (byte)((in[10 + inPos] & 1048575) >>> 12 & 255);
         out[26 + outPos] = (byte)((in[10 + inPos] & 1048575) >>> 4 & 255);
         out[27 + outPos] = (byte)(((in[10 + inPos] & 1048575) << 4 | (in[11 + inPos] & 1048575) >>> 16) & 255);
         out[28 + outPos] = (byte)((in[11 + inPos] & 1048575) >>> 8 & 255);
         out[29 + outPos] = (byte)(in[11 + inPos] & 1048575 & 255);
         out[30 + outPos] = (byte)((in[12 + inPos] & 1048575) >>> 12 & 255);
         out[31 + outPos] = (byte)((in[12 + inPos] & 1048575) >>> 4 & 255);
         out[32 + outPos] = (byte)(((in[12 + inPos] & 1048575) << 4 | (in[13 + inPos] & 1048575) >>> 16) & 255);
         out[33 + outPos] = (byte)((in[13 + inPos] & 1048575) >>> 8 & 255);
         out[34 + outPos] = (byte)(in[13 + inPos] & 1048575 & 255);
         out[35 + outPos] = (byte)((in[14 + inPos] & 1048575) >>> 12 & 255);
         out[36 + outPos] = (byte)((in[14 + inPos] & 1048575) >>> 4 & 255);
         out[37 + outPos] = (byte)(((in[14 + inPos] & 1048575) << 4 | (in[15 + inPos] & 1048575) >>> 16) & 255);
         out[38 + outPos] = (byte)((in[15 + inPos] & 1048575) >>> 8 & 255);
         out[39 + outPos] = (byte)(in[15 + inPos] & 1048575 & 255);
         out[40 + outPos] = (byte)((in[16 + inPos] & 1048575) >>> 12 & 255);
         out[41 + outPos] = (byte)((in[16 + inPos] & 1048575) >>> 4 & 255);
         out[42 + outPos] = (byte)(((in[16 + inPos] & 1048575) << 4 | (in[17 + inPos] & 1048575) >>> 16) & 255);
         out[43 + outPos] = (byte)((in[17 + inPos] & 1048575) >>> 8 & 255);
         out[44 + outPos] = (byte)(in[17 + inPos] & 1048575 & 255);
         out[45 + outPos] = (byte)((in[18 + inPos] & 1048575) >>> 12 & 255);
         out[46 + outPos] = (byte)((in[18 + inPos] & 1048575) >>> 4 & 255);
         out[47 + outPos] = (byte)(((in[18 + inPos] & 1048575) << 4 | (in[19 + inPos] & 1048575) >>> 16) & 255);
         out[48 + outPos] = (byte)((in[19 + inPos] & 1048575) >>> 8 & 255);
         out[49 + outPos] = (byte)(in[19 + inPos] & 1048575 & 255);
         out[50 + outPos] = (byte)((in[20 + inPos] & 1048575) >>> 12 & 255);
         out[51 + outPos] = (byte)((in[20 + inPos] & 1048575) >>> 4 & 255);
         out[52 + outPos] = (byte)(((in[20 + inPos] & 1048575) << 4 | (in[21 + inPos] & 1048575) >>> 16) & 255);
         out[53 + outPos] = (byte)((in[21 + inPos] & 1048575) >>> 8 & 255);
         out[54 + outPos] = (byte)(in[21 + inPos] & 1048575 & 255);
         out[55 + outPos] = (byte)((in[22 + inPos] & 1048575) >>> 12 & 255);
         out[56 + outPos] = (byte)((in[22 + inPos] & 1048575) >>> 4 & 255);
         out[57 + outPos] = (byte)(((in[22 + inPos] & 1048575) << 4 | (in[23 + inPos] & 1048575) >>> 16) & 255);
         out[58 + outPos] = (byte)((in[23 + inPos] & 1048575) >>> 8 & 255);
         out[59 + outPos] = (byte)(in[23 + inPos] & 1048575 & 255);
         out[60 + outPos] = (byte)((in[24 + inPos] & 1048575) >>> 12 & 255);
         out[61 + outPos] = (byte)((in[24 + inPos] & 1048575) >>> 4 & 255);
         out[62 + outPos] = (byte)(((in[24 + inPos] & 1048575) << 4 | (in[25 + inPos] & 1048575) >>> 16) & 255);
         out[63 + outPos] = (byte)((in[25 + inPos] & 1048575) >>> 8 & 255);
         out[64 + outPos] = (byte)(in[25 + inPos] & 1048575 & 255);
         out[65 + outPos] = (byte)((in[26 + inPos] & 1048575) >>> 12 & 255);
         out[66 + outPos] = (byte)((in[26 + inPos] & 1048575) >>> 4 & 255);
         out[67 + outPos] = (byte)(((in[26 + inPos] & 1048575) << 4 | (in[27 + inPos] & 1048575) >>> 16) & 255);
         out[68 + outPos] = (byte)((in[27 + inPos] & 1048575) >>> 8 & 255);
         out[69 + outPos] = (byte)(in[27 + inPos] & 1048575 & 255);
         out[70 + outPos] = (byte)((in[28 + inPos] & 1048575) >>> 12 & 255);
         out[71 + outPos] = (byte)((in[28 + inPos] & 1048575) >>> 4 & 255);
         out[72 + outPos] = (byte)(((in[28 + inPos] & 1048575) << 4 | (in[29 + inPos] & 1048575) >>> 16) & 255);
         out[73 + outPos] = (byte)((in[29 + inPos] & 1048575) >>> 8 & 255);
         out[74 + outPos] = (byte)(in[29 + inPos] & 1048575 & 255);
         out[75 + outPos] = (byte)((in[30 + inPos] & 1048575) >>> 12 & 255);
         out[76 + outPos] = (byte)((in[30 + inPos] & 1048575) >>> 4 & 255);
         out[77 + outPos] = (byte)(((in[30 + inPos] & 1048575) << 4 | (in[31 + inPos] & 1048575) >>> 16) & 255);
         out[78 + outPos] = (byte)((in[31 + inPos] & 1048575) >>> 8 & 255);
         out[79 + outPos] = (byte)(in[31 + inPos] & 1048575 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 12 & 1048575 | in[1 + inPos] << 4 & 4095 | in[2 + inPos] >> 4 & 15;
         out[1 + outPos] = in[2 + inPos] << 16 & 1048575 | in[3 + inPos] << 8 & '\uffff' | in[4 + inPos] & 255;
         out[2 + outPos] = in[5 + inPos] << 12 & 1048575 | in[6 + inPos] << 4 & 4095 | in[7 + inPos] >> 4 & 15;
         out[3 + outPos] = in[7 + inPos] << 16 & 1048575 | in[8 + inPos] << 8 & '\uffff' | in[9 + inPos] & 255;
         out[4 + outPos] = in[10 + inPos] << 12 & 1048575 | in[11 + inPos] << 4 & 4095 | in[12 + inPos] >> 4 & 15;
         out[5 + outPos] = in[12 + inPos] << 16 & 1048575 | in[13 + inPos] << 8 & '\uffff' | in[14 + inPos] & 255;
         out[6 + outPos] = in[15 + inPos] << 12 & 1048575 | in[16 + inPos] << 4 & 4095 | in[17 + inPos] >> 4 & 15;
         out[7 + outPos] = in[17 + inPos] << 16 & 1048575 | in[18 + inPos] << 8 & '\uffff' | in[19 + inPos] & 255;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 12 & 1048575 | in.get(1 + inPos) << 4 & 4095 | in.get(2 + inPos) >> 4 & 15;
         out[1 + outPos] = in.get(2 + inPos) << 16 & 1048575 | in.get(3 + inPos) << 8 & '\uffff' | in.get(4 + inPos) & 255;
         out[2 + outPos] = in.get(5 + inPos) << 12 & 1048575 | in.get(6 + inPos) << 4 & 4095 | in.get(7 + inPos) >> 4 & 15;
         out[3 + outPos] = in.get(7 + inPos) << 16 & 1048575 | in.get(8 + inPos) << 8 & '\uffff' | in.get(9 + inPos) & 255;
         out[4 + outPos] = in.get(10 + inPos) << 12 & 1048575 | in.get(11 + inPos) << 4 & 4095 | in.get(12 + inPos) >> 4 & 15;
         out[5 + outPos] = in.get(12 + inPos) << 16 & 1048575 | in.get(13 + inPos) << 8 & '\uffff' | in.get(14 + inPos) & 255;
         out[6 + outPos] = in.get(15 + inPos) << 12 & 1048575 | in.get(16 + inPos) << 4 & 4095 | in.get(17 + inPos) >> 4 & 15;
         out[7 + outPos] = in.get(17 + inPos) << 16 & 1048575 | in.get(18 + inPos) << 8 & '\uffff' | in.get(19 + inPos) & 255;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 12 & 1048575 | in[1 + inPos] << 4 & 4095 | in[2 + inPos] >> 4 & 15;
         out[1 + outPos] = in[2 + inPos] << 16 & 1048575 | in[3 + inPos] << 8 & '\uffff' | in[4 + inPos] & 255;
         out[2 + outPos] = in[5 + inPos] << 12 & 1048575 | in[6 + inPos] << 4 & 4095 | in[7 + inPos] >> 4 & 15;
         out[3 + outPos] = in[7 + inPos] << 16 & 1048575 | in[8 + inPos] << 8 & '\uffff' | in[9 + inPos] & 255;
         out[4 + outPos] = in[10 + inPos] << 12 & 1048575 | in[11 + inPos] << 4 & 4095 | in[12 + inPos] >> 4 & 15;
         out[5 + outPos] = in[12 + inPos] << 16 & 1048575 | in[13 + inPos] << 8 & '\uffff' | in[14 + inPos] & 255;
         out[6 + outPos] = in[15 + inPos] << 12 & 1048575 | in[16 + inPos] << 4 & 4095 | in[17 + inPos] >> 4 & 15;
         out[7 + outPos] = in[17 + inPos] << 16 & 1048575 | in[18 + inPos] << 8 & '\uffff' | in[19 + inPos] & 255;
         out[8 + outPos] = in[20 + inPos] << 12 & 1048575 | in[21 + inPos] << 4 & 4095 | in[22 + inPos] >> 4 & 15;
         out[9 + outPos] = in[22 + inPos] << 16 & 1048575 | in[23 + inPos] << 8 & '\uffff' | in[24 + inPos] & 255;
         out[10 + outPos] = in[25 + inPos] << 12 & 1048575 | in[26 + inPos] << 4 & 4095 | in[27 + inPos] >> 4 & 15;
         out[11 + outPos] = in[27 + inPos] << 16 & 1048575 | in[28 + inPos] << 8 & '\uffff' | in[29 + inPos] & 255;
         out[12 + outPos] = in[30 + inPos] << 12 & 1048575 | in[31 + inPos] << 4 & 4095 | in[32 + inPos] >> 4 & 15;
         out[13 + outPos] = in[32 + inPos] << 16 & 1048575 | in[33 + inPos] << 8 & '\uffff' | in[34 + inPos] & 255;
         out[14 + outPos] = in[35 + inPos] << 12 & 1048575 | in[36 + inPos] << 4 & 4095 | in[37 + inPos] >> 4 & 15;
         out[15 + outPos] = in[37 + inPos] << 16 & 1048575 | in[38 + inPos] << 8 & '\uffff' | in[39 + inPos] & 255;
         out[16 + outPos] = in[40 + inPos] << 12 & 1048575 | in[41 + inPos] << 4 & 4095 | in[42 + inPos] >> 4 & 15;
         out[17 + outPos] = in[42 + inPos] << 16 & 1048575 | in[43 + inPos] << 8 & '\uffff' | in[44 + inPos] & 255;
         out[18 + outPos] = in[45 + inPos] << 12 & 1048575 | in[46 + inPos] << 4 & 4095 | in[47 + inPos] >> 4 & 15;
         out[19 + outPos] = in[47 + inPos] << 16 & 1048575 | in[48 + inPos] << 8 & '\uffff' | in[49 + inPos] & 255;
         out[20 + outPos] = in[50 + inPos] << 12 & 1048575 | in[51 + inPos] << 4 & 4095 | in[52 + inPos] >> 4 & 15;
         out[21 + outPos] = in[52 + inPos] << 16 & 1048575 | in[53 + inPos] << 8 & '\uffff' | in[54 + inPos] & 255;
         out[22 + outPos] = in[55 + inPos] << 12 & 1048575 | in[56 + inPos] << 4 & 4095 | in[57 + inPos] >> 4 & 15;
         out[23 + outPos] = in[57 + inPos] << 16 & 1048575 | in[58 + inPos] << 8 & '\uffff' | in[59 + inPos] & 255;
         out[24 + outPos] = in[60 + inPos] << 12 & 1048575 | in[61 + inPos] << 4 & 4095 | in[62 + inPos] >> 4 & 15;
         out[25 + outPos] = in[62 + inPos] << 16 & 1048575 | in[63 + inPos] << 8 & '\uffff' | in[64 + inPos] & 255;
         out[26 + outPos] = in[65 + inPos] << 12 & 1048575 | in[66 + inPos] << 4 & 4095 | in[67 + inPos] >> 4 & 15;
         out[27 + outPos] = in[67 + inPos] << 16 & 1048575 | in[68 + inPos] << 8 & '\uffff' | in[69 + inPos] & 255;
         out[28 + outPos] = in[70 + inPos] << 12 & 1048575 | in[71 + inPos] << 4 & 4095 | in[72 + inPos] >> 4 & 15;
         out[29 + outPos] = in[72 + inPos] << 16 & 1048575 | in[73 + inPos] << 8 & '\uffff' | in[74 + inPos] & 255;
         out[30 + outPos] = in[75 + inPos] << 12 & 1048575 | in[76 + inPos] << 4 & 4095 | in[77 + inPos] >> 4 & 15;
         out[31 + outPos] = in[77 + inPos] << 16 & 1048575 | in[78 + inPos] << 8 & '\uffff' | in[79 + inPos] & 255;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 12 & 1048575 | in.get(1 + inPos) << 4 & 4095 | in.get(2 + inPos) >> 4 & 15;
         out[1 + outPos] = in.get(2 + inPos) << 16 & 1048575 | in.get(3 + inPos) << 8 & '\uffff' | in.get(4 + inPos) & 255;
         out[2 + outPos] = in.get(5 + inPos) << 12 & 1048575 | in.get(6 + inPos) << 4 & 4095 | in.get(7 + inPos) >> 4 & 15;
         out[3 + outPos] = in.get(7 + inPos) << 16 & 1048575 | in.get(8 + inPos) << 8 & '\uffff' | in.get(9 + inPos) & 255;
         out[4 + outPos] = in.get(10 + inPos) << 12 & 1048575 | in.get(11 + inPos) << 4 & 4095 | in.get(12 + inPos) >> 4 & 15;
         out[5 + outPos] = in.get(12 + inPos) << 16 & 1048575 | in.get(13 + inPos) << 8 & '\uffff' | in.get(14 + inPos) & 255;
         out[6 + outPos] = in.get(15 + inPos) << 12 & 1048575 | in.get(16 + inPos) << 4 & 4095 | in.get(17 + inPos) >> 4 & 15;
         out[7 + outPos] = in.get(17 + inPos) << 16 & 1048575 | in.get(18 + inPos) << 8 & '\uffff' | in.get(19 + inPos) & 255;
         out[8 + outPos] = in.get(20 + inPos) << 12 & 1048575 | in.get(21 + inPos) << 4 & 4095 | in.get(22 + inPos) >> 4 & 15;
         out[9 + outPos] = in.get(22 + inPos) << 16 & 1048575 | in.get(23 + inPos) << 8 & '\uffff' | in.get(24 + inPos) & 255;
         out[10 + outPos] = in.get(25 + inPos) << 12 & 1048575 | in.get(26 + inPos) << 4 & 4095 | in.get(27 + inPos) >> 4 & 15;
         out[11 + outPos] = in.get(27 + inPos) << 16 & 1048575 | in.get(28 + inPos) << 8 & '\uffff' | in.get(29 + inPos) & 255;
         out[12 + outPos] = in.get(30 + inPos) << 12 & 1048575 | in.get(31 + inPos) << 4 & 4095 | in.get(32 + inPos) >> 4 & 15;
         out[13 + outPos] = in.get(32 + inPos) << 16 & 1048575 | in.get(33 + inPos) << 8 & '\uffff' | in.get(34 + inPos) & 255;
         out[14 + outPos] = in.get(35 + inPos) << 12 & 1048575 | in.get(36 + inPos) << 4 & 4095 | in.get(37 + inPos) >> 4 & 15;
         out[15 + outPos] = in.get(37 + inPos) << 16 & 1048575 | in.get(38 + inPos) << 8 & '\uffff' | in.get(39 + inPos) & 255;
         out[16 + outPos] = in.get(40 + inPos) << 12 & 1048575 | in.get(41 + inPos) << 4 & 4095 | in.get(42 + inPos) >> 4 & 15;
         out[17 + outPos] = in.get(42 + inPos) << 16 & 1048575 | in.get(43 + inPos) << 8 & '\uffff' | in.get(44 + inPos) & 255;
         out[18 + outPos] = in.get(45 + inPos) << 12 & 1048575 | in.get(46 + inPos) << 4 & 4095 | in.get(47 + inPos) >> 4 & 15;
         out[19 + outPos] = in.get(47 + inPos) << 16 & 1048575 | in.get(48 + inPos) << 8 & '\uffff' | in.get(49 + inPos) & 255;
         out[20 + outPos] = in.get(50 + inPos) << 12 & 1048575 | in.get(51 + inPos) << 4 & 4095 | in.get(52 + inPos) >> 4 & 15;
         out[21 + outPos] = in.get(52 + inPos) << 16 & 1048575 | in.get(53 + inPos) << 8 & '\uffff' | in.get(54 + inPos) & 255;
         out[22 + outPos] = in.get(55 + inPos) << 12 & 1048575 | in.get(56 + inPos) << 4 & 4095 | in.get(57 + inPos) >> 4 & 15;
         out[23 + outPos] = in.get(57 + inPos) << 16 & 1048575 | in.get(58 + inPos) << 8 & '\uffff' | in.get(59 + inPos) & 255;
         out[24 + outPos] = in.get(60 + inPos) << 12 & 1048575 | in.get(61 + inPos) << 4 & 4095 | in.get(62 + inPos) >> 4 & 15;
         out[25 + outPos] = in.get(62 + inPos) << 16 & 1048575 | in.get(63 + inPos) << 8 & '\uffff' | in.get(64 + inPos) & 255;
         out[26 + outPos] = in.get(65 + inPos) << 12 & 1048575 | in.get(66 + inPos) << 4 & 4095 | in.get(67 + inPos) >> 4 & 15;
         out[27 + outPos] = in.get(67 + inPos) << 16 & 1048575 | in.get(68 + inPos) << 8 & '\uffff' | in.get(69 + inPos) & 255;
         out[28 + outPos] = in.get(70 + inPos) << 12 & 1048575 | in.get(71 + inPos) << 4 & 4095 | in.get(72 + inPos) >> 4 & 15;
         out[29 + outPos] = in.get(72 + inPos) << 16 & 1048575 | in.get(73 + inPos) << 8 & '\uffff' | in.get(74 + inPos) & 255;
         out[30 + outPos] = in.get(75 + inPos) << 12 & 1048575 | in.get(76 + inPos) << 4 & 4095 | in.get(77 + inPos) >> 4 & 15;
         out[31 + outPos] = in.get(77 + inPos) << 16 & 1048575 | in.get(78 + inPos) << 8 & '\uffff' | in.get(79 + inPos) & 255;
      }
   }

   private static final class Packer21 extends BytePacker {
      private Packer21() {
         super(21);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 2097151) >>> 13 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 2097151) >>> 5 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 2097151) << 3 | (in[1 + inPos] & 2097151) >>> 18) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 2097151) >>> 10 & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 2097151) >>> 2 & 255);
         out[5 + outPos] = (byte)(((in[1 + inPos] & 2097151) << 6 | (in[2 + inPos] & 2097151) >>> 15) & 255);
         out[6 + outPos] = (byte)((in[2 + inPos] & 2097151) >>> 7 & 255);
         out[7 + outPos] = (byte)(((in[2 + inPos] & 2097151) << 1 | (in[3 + inPos] & 2097151) >>> 20) & 255);
         out[8 + outPos] = (byte)((in[3 + inPos] & 2097151) >>> 12 & 255);
         out[9 + outPos] = (byte)((in[3 + inPos] & 2097151) >>> 4 & 255);
         out[10 + outPos] = (byte)(((in[3 + inPos] & 2097151) << 4 | (in[4 + inPos] & 2097151) >>> 17) & 255);
         out[11 + outPos] = (byte)((in[4 + inPos] & 2097151) >>> 9 & 255);
         out[12 + outPos] = (byte)((in[4 + inPos] & 2097151) >>> 1 & 255);
         out[13 + outPos] = (byte)(((in[4 + inPos] & 2097151) << 7 | (in[5 + inPos] & 2097151) >>> 14) & 255);
         out[14 + outPos] = (byte)((in[5 + inPos] & 2097151) >>> 6 & 255);
         out[15 + outPos] = (byte)(((in[5 + inPos] & 2097151) << 2 | (in[6 + inPos] & 2097151) >>> 19) & 255);
         out[16 + outPos] = (byte)((in[6 + inPos] & 2097151) >>> 11 & 255);
         out[17 + outPos] = (byte)((in[6 + inPos] & 2097151) >>> 3 & 255);
         out[18 + outPos] = (byte)(((in[6 + inPos] & 2097151) << 5 | (in[7 + inPos] & 2097151) >>> 16) & 255);
         out[19 + outPos] = (byte)((in[7 + inPos] & 2097151) >>> 8 & 255);
         out[20 + outPos] = (byte)(in[7 + inPos] & 2097151 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 2097151) >>> 13 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 2097151) >>> 5 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 2097151) << 3 | (in[1 + inPos] & 2097151) >>> 18) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 2097151) >>> 10 & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 2097151) >>> 2 & 255);
         out[5 + outPos] = (byte)(((in[1 + inPos] & 2097151) << 6 | (in[2 + inPos] & 2097151) >>> 15) & 255);
         out[6 + outPos] = (byte)((in[2 + inPos] & 2097151) >>> 7 & 255);
         out[7 + outPos] = (byte)(((in[2 + inPos] & 2097151) << 1 | (in[3 + inPos] & 2097151) >>> 20) & 255);
         out[8 + outPos] = (byte)((in[3 + inPos] & 2097151) >>> 12 & 255);
         out[9 + outPos] = (byte)((in[3 + inPos] & 2097151) >>> 4 & 255);
         out[10 + outPos] = (byte)(((in[3 + inPos] & 2097151) << 4 | (in[4 + inPos] & 2097151) >>> 17) & 255);
         out[11 + outPos] = (byte)((in[4 + inPos] & 2097151) >>> 9 & 255);
         out[12 + outPos] = (byte)((in[4 + inPos] & 2097151) >>> 1 & 255);
         out[13 + outPos] = (byte)(((in[4 + inPos] & 2097151) << 7 | (in[5 + inPos] & 2097151) >>> 14) & 255);
         out[14 + outPos] = (byte)((in[5 + inPos] & 2097151) >>> 6 & 255);
         out[15 + outPos] = (byte)(((in[5 + inPos] & 2097151) << 2 | (in[6 + inPos] & 2097151) >>> 19) & 255);
         out[16 + outPos] = (byte)((in[6 + inPos] & 2097151) >>> 11 & 255);
         out[17 + outPos] = (byte)((in[6 + inPos] & 2097151) >>> 3 & 255);
         out[18 + outPos] = (byte)(((in[6 + inPos] & 2097151) << 5 | (in[7 + inPos] & 2097151) >>> 16) & 255);
         out[19 + outPos] = (byte)((in[7 + inPos] & 2097151) >>> 8 & 255);
         out[20 + outPos] = (byte)(in[7 + inPos] & 2097151 & 255);
         out[21 + outPos] = (byte)((in[8 + inPos] & 2097151) >>> 13 & 255);
         out[22 + outPos] = (byte)((in[8 + inPos] & 2097151) >>> 5 & 255);
         out[23 + outPos] = (byte)(((in[8 + inPos] & 2097151) << 3 | (in[9 + inPos] & 2097151) >>> 18) & 255);
         out[24 + outPos] = (byte)((in[9 + inPos] & 2097151) >>> 10 & 255);
         out[25 + outPos] = (byte)((in[9 + inPos] & 2097151) >>> 2 & 255);
         out[26 + outPos] = (byte)(((in[9 + inPos] & 2097151) << 6 | (in[10 + inPos] & 2097151) >>> 15) & 255);
         out[27 + outPos] = (byte)((in[10 + inPos] & 2097151) >>> 7 & 255);
         out[28 + outPos] = (byte)(((in[10 + inPos] & 2097151) << 1 | (in[11 + inPos] & 2097151) >>> 20) & 255);
         out[29 + outPos] = (byte)((in[11 + inPos] & 2097151) >>> 12 & 255);
         out[30 + outPos] = (byte)((in[11 + inPos] & 2097151) >>> 4 & 255);
         out[31 + outPos] = (byte)(((in[11 + inPos] & 2097151) << 4 | (in[12 + inPos] & 2097151) >>> 17) & 255);
         out[32 + outPos] = (byte)((in[12 + inPos] & 2097151) >>> 9 & 255);
         out[33 + outPos] = (byte)((in[12 + inPos] & 2097151) >>> 1 & 255);
         out[34 + outPos] = (byte)(((in[12 + inPos] & 2097151) << 7 | (in[13 + inPos] & 2097151) >>> 14) & 255);
         out[35 + outPos] = (byte)((in[13 + inPos] & 2097151) >>> 6 & 255);
         out[36 + outPos] = (byte)(((in[13 + inPos] & 2097151) << 2 | (in[14 + inPos] & 2097151) >>> 19) & 255);
         out[37 + outPos] = (byte)((in[14 + inPos] & 2097151) >>> 11 & 255);
         out[38 + outPos] = (byte)((in[14 + inPos] & 2097151) >>> 3 & 255);
         out[39 + outPos] = (byte)(((in[14 + inPos] & 2097151) << 5 | (in[15 + inPos] & 2097151) >>> 16) & 255);
         out[40 + outPos] = (byte)((in[15 + inPos] & 2097151) >>> 8 & 255);
         out[41 + outPos] = (byte)(in[15 + inPos] & 2097151 & 255);
         out[42 + outPos] = (byte)((in[16 + inPos] & 2097151) >>> 13 & 255);
         out[43 + outPos] = (byte)((in[16 + inPos] & 2097151) >>> 5 & 255);
         out[44 + outPos] = (byte)(((in[16 + inPos] & 2097151) << 3 | (in[17 + inPos] & 2097151) >>> 18) & 255);
         out[45 + outPos] = (byte)((in[17 + inPos] & 2097151) >>> 10 & 255);
         out[46 + outPos] = (byte)((in[17 + inPos] & 2097151) >>> 2 & 255);
         out[47 + outPos] = (byte)(((in[17 + inPos] & 2097151) << 6 | (in[18 + inPos] & 2097151) >>> 15) & 255);
         out[48 + outPos] = (byte)((in[18 + inPos] & 2097151) >>> 7 & 255);
         out[49 + outPos] = (byte)(((in[18 + inPos] & 2097151) << 1 | (in[19 + inPos] & 2097151) >>> 20) & 255);
         out[50 + outPos] = (byte)((in[19 + inPos] & 2097151) >>> 12 & 255);
         out[51 + outPos] = (byte)((in[19 + inPos] & 2097151) >>> 4 & 255);
         out[52 + outPos] = (byte)(((in[19 + inPos] & 2097151) << 4 | (in[20 + inPos] & 2097151) >>> 17) & 255);
         out[53 + outPos] = (byte)((in[20 + inPos] & 2097151) >>> 9 & 255);
         out[54 + outPos] = (byte)((in[20 + inPos] & 2097151) >>> 1 & 255);
         out[55 + outPos] = (byte)(((in[20 + inPos] & 2097151) << 7 | (in[21 + inPos] & 2097151) >>> 14) & 255);
         out[56 + outPos] = (byte)((in[21 + inPos] & 2097151) >>> 6 & 255);
         out[57 + outPos] = (byte)(((in[21 + inPos] & 2097151) << 2 | (in[22 + inPos] & 2097151) >>> 19) & 255);
         out[58 + outPos] = (byte)((in[22 + inPos] & 2097151) >>> 11 & 255);
         out[59 + outPos] = (byte)((in[22 + inPos] & 2097151) >>> 3 & 255);
         out[60 + outPos] = (byte)(((in[22 + inPos] & 2097151) << 5 | (in[23 + inPos] & 2097151) >>> 16) & 255);
         out[61 + outPos] = (byte)((in[23 + inPos] & 2097151) >>> 8 & 255);
         out[62 + outPos] = (byte)(in[23 + inPos] & 2097151 & 255);
         out[63 + outPos] = (byte)((in[24 + inPos] & 2097151) >>> 13 & 255);
         out[64 + outPos] = (byte)((in[24 + inPos] & 2097151) >>> 5 & 255);
         out[65 + outPos] = (byte)(((in[24 + inPos] & 2097151) << 3 | (in[25 + inPos] & 2097151) >>> 18) & 255);
         out[66 + outPos] = (byte)((in[25 + inPos] & 2097151) >>> 10 & 255);
         out[67 + outPos] = (byte)((in[25 + inPos] & 2097151) >>> 2 & 255);
         out[68 + outPos] = (byte)(((in[25 + inPos] & 2097151) << 6 | (in[26 + inPos] & 2097151) >>> 15) & 255);
         out[69 + outPos] = (byte)((in[26 + inPos] & 2097151) >>> 7 & 255);
         out[70 + outPos] = (byte)(((in[26 + inPos] & 2097151) << 1 | (in[27 + inPos] & 2097151) >>> 20) & 255);
         out[71 + outPos] = (byte)((in[27 + inPos] & 2097151) >>> 12 & 255);
         out[72 + outPos] = (byte)((in[27 + inPos] & 2097151) >>> 4 & 255);
         out[73 + outPos] = (byte)(((in[27 + inPos] & 2097151) << 4 | (in[28 + inPos] & 2097151) >>> 17) & 255);
         out[74 + outPos] = (byte)((in[28 + inPos] & 2097151) >>> 9 & 255);
         out[75 + outPos] = (byte)((in[28 + inPos] & 2097151) >>> 1 & 255);
         out[76 + outPos] = (byte)(((in[28 + inPos] & 2097151) << 7 | (in[29 + inPos] & 2097151) >>> 14) & 255);
         out[77 + outPos] = (byte)((in[29 + inPos] & 2097151) >>> 6 & 255);
         out[78 + outPos] = (byte)(((in[29 + inPos] & 2097151) << 2 | (in[30 + inPos] & 2097151) >>> 19) & 255);
         out[79 + outPos] = (byte)((in[30 + inPos] & 2097151) >>> 11 & 255);
         out[80 + outPos] = (byte)((in[30 + inPos] & 2097151) >>> 3 & 255);
         out[81 + outPos] = (byte)(((in[30 + inPos] & 2097151) << 5 | (in[31 + inPos] & 2097151) >>> 16) & 255);
         out[82 + outPos] = (byte)((in[31 + inPos] & 2097151) >>> 8 & 255);
         out[83 + outPos] = (byte)(in[31 + inPos] & 2097151 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 13 & 2097151 | in[1 + inPos] << 5 & 8191 | in[2 + inPos] >> 3 & 31;
         out[1 + outPos] = in[2 + inPos] << 18 & 2097151 | in[3 + inPos] << 10 & 262143 | in[4 + inPos] << 2 & 1023 | in[5 + inPos] >> 6 & 3;
         out[2 + outPos] = in[5 + inPos] << 15 & 2097151 | in[6 + inPos] << 7 & 32767 | in[7 + inPos] >> 1 & 127;
         out[3 + outPos] = in[7 + inPos] << 20 & 2097151 | in[8 + inPos] << 12 & 1048575 | in[9 + inPos] << 4 & 4095 | in[10 + inPos] >> 4 & 15;
         out[4 + outPos] = in[10 + inPos] << 17 & 2097151 | in[11 + inPos] << 9 & 131071 | in[12 + inPos] << 1 & 511 | in[13 + inPos] >> 7 & 1;
         out[5 + outPos] = in[13 + inPos] << 14 & 2097151 | in[14 + inPos] << 6 & 16383 | in[15 + inPos] >> 2 & 63;
         out[6 + outPos] = in[15 + inPos] << 19 & 2097151 | in[16 + inPos] << 11 & 524287 | in[17 + inPos] << 3 & 2047 | in[18 + inPos] >> 5 & 7;
         out[7 + outPos] = in[18 + inPos] << 16 & 2097151 | in[19 + inPos] << 8 & '\uffff' | in[20 + inPos] & 255;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 13 & 2097151 | in.get(1 + inPos) << 5 & 8191 | in.get(2 + inPos) >> 3 & 31;
         out[1 + outPos] = in.get(2 + inPos) << 18 & 2097151 | in.get(3 + inPos) << 10 & 262143 | in.get(4 + inPos) << 2 & 1023 | in.get(5 + inPos) >> 6 & 3;
         out[2 + outPos] = in.get(5 + inPos) << 15 & 2097151 | in.get(6 + inPos) << 7 & 32767 | in.get(7 + inPos) >> 1 & 127;
         out[3 + outPos] = in.get(7 + inPos) << 20 & 2097151 | in.get(8 + inPos) << 12 & 1048575 | in.get(9 + inPos) << 4 & 4095 | in.get(10 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(10 + inPos) << 17 & 2097151 | in.get(11 + inPos) << 9 & 131071 | in.get(12 + inPos) << 1 & 511 | in.get(13 + inPos) >> 7 & 1;
         out[5 + outPos] = in.get(13 + inPos) << 14 & 2097151 | in.get(14 + inPos) << 6 & 16383 | in.get(15 + inPos) >> 2 & 63;
         out[6 + outPos] = in.get(15 + inPos) << 19 & 2097151 | in.get(16 + inPos) << 11 & 524287 | in.get(17 + inPos) << 3 & 2047 | in.get(18 + inPos) >> 5 & 7;
         out[7 + outPos] = in.get(18 + inPos) << 16 & 2097151 | in.get(19 + inPos) << 8 & '\uffff' | in.get(20 + inPos) & 255;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 13 & 2097151 | in[1 + inPos] << 5 & 8191 | in[2 + inPos] >> 3 & 31;
         out[1 + outPos] = in[2 + inPos] << 18 & 2097151 | in[3 + inPos] << 10 & 262143 | in[4 + inPos] << 2 & 1023 | in[5 + inPos] >> 6 & 3;
         out[2 + outPos] = in[5 + inPos] << 15 & 2097151 | in[6 + inPos] << 7 & 32767 | in[7 + inPos] >> 1 & 127;
         out[3 + outPos] = in[7 + inPos] << 20 & 2097151 | in[8 + inPos] << 12 & 1048575 | in[9 + inPos] << 4 & 4095 | in[10 + inPos] >> 4 & 15;
         out[4 + outPos] = in[10 + inPos] << 17 & 2097151 | in[11 + inPos] << 9 & 131071 | in[12 + inPos] << 1 & 511 | in[13 + inPos] >> 7 & 1;
         out[5 + outPos] = in[13 + inPos] << 14 & 2097151 | in[14 + inPos] << 6 & 16383 | in[15 + inPos] >> 2 & 63;
         out[6 + outPos] = in[15 + inPos] << 19 & 2097151 | in[16 + inPos] << 11 & 524287 | in[17 + inPos] << 3 & 2047 | in[18 + inPos] >> 5 & 7;
         out[7 + outPos] = in[18 + inPos] << 16 & 2097151 | in[19 + inPos] << 8 & '\uffff' | in[20 + inPos] & 255;
         out[8 + outPos] = in[21 + inPos] << 13 & 2097151 | in[22 + inPos] << 5 & 8191 | in[23 + inPos] >> 3 & 31;
         out[9 + outPos] = in[23 + inPos] << 18 & 2097151 | in[24 + inPos] << 10 & 262143 | in[25 + inPos] << 2 & 1023 | in[26 + inPos] >> 6 & 3;
         out[10 + outPos] = in[26 + inPos] << 15 & 2097151 | in[27 + inPos] << 7 & 32767 | in[28 + inPos] >> 1 & 127;
         out[11 + outPos] = in[28 + inPos] << 20 & 2097151 | in[29 + inPos] << 12 & 1048575 | in[30 + inPos] << 4 & 4095 | in[31 + inPos] >> 4 & 15;
         out[12 + outPos] = in[31 + inPos] << 17 & 2097151 | in[32 + inPos] << 9 & 131071 | in[33 + inPos] << 1 & 511 | in[34 + inPos] >> 7 & 1;
         out[13 + outPos] = in[34 + inPos] << 14 & 2097151 | in[35 + inPos] << 6 & 16383 | in[36 + inPos] >> 2 & 63;
         out[14 + outPos] = in[36 + inPos] << 19 & 2097151 | in[37 + inPos] << 11 & 524287 | in[38 + inPos] << 3 & 2047 | in[39 + inPos] >> 5 & 7;
         out[15 + outPos] = in[39 + inPos] << 16 & 2097151 | in[40 + inPos] << 8 & '\uffff' | in[41 + inPos] & 255;
         out[16 + outPos] = in[42 + inPos] << 13 & 2097151 | in[43 + inPos] << 5 & 8191 | in[44 + inPos] >> 3 & 31;
         out[17 + outPos] = in[44 + inPos] << 18 & 2097151 | in[45 + inPos] << 10 & 262143 | in[46 + inPos] << 2 & 1023 | in[47 + inPos] >> 6 & 3;
         out[18 + outPos] = in[47 + inPos] << 15 & 2097151 | in[48 + inPos] << 7 & 32767 | in[49 + inPos] >> 1 & 127;
         out[19 + outPos] = in[49 + inPos] << 20 & 2097151 | in[50 + inPos] << 12 & 1048575 | in[51 + inPos] << 4 & 4095 | in[52 + inPos] >> 4 & 15;
         out[20 + outPos] = in[52 + inPos] << 17 & 2097151 | in[53 + inPos] << 9 & 131071 | in[54 + inPos] << 1 & 511 | in[55 + inPos] >> 7 & 1;
         out[21 + outPos] = in[55 + inPos] << 14 & 2097151 | in[56 + inPos] << 6 & 16383 | in[57 + inPos] >> 2 & 63;
         out[22 + outPos] = in[57 + inPos] << 19 & 2097151 | in[58 + inPos] << 11 & 524287 | in[59 + inPos] << 3 & 2047 | in[60 + inPos] >> 5 & 7;
         out[23 + outPos] = in[60 + inPos] << 16 & 2097151 | in[61 + inPos] << 8 & '\uffff' | in[62 + inPos] & 255;
         out[24 + outPos] = in[63 + inPos] << 13 & 2097151 | in[64 + inPos] << 5 & 8191 | in[65 + inPos] >> 3 & 31;
         out[25 + outPos] = in[65 + inPos] << 18 & 2097151 | in[66 + inPos] << 10 & 262143 | in[67 + inPos] << 2 & 1023 | in[68 + inPos] >> 6 & 3;
         out[26 + outPos] = in[68 + inPos] << 15 & 2097151 | in[69 + inPos] << 7 & 32767 | in[70 + inPos] >> 1 & 127;
         out[27 + outPos] = in[70 + inPos] << 20 & 2097151 | in[71 + inPos] << 12 & 1048575 | in[72 + inPos] << 4 & 4095 | in[73 + inPos] >> 4 & 15;
         out[28 + outPos] = in[73 + inPos] << 17 & 2097151 | in[74 + inPos] << 9 & 131071 | in[75 + inPos] << 1 & 511 | in[76 + inPos] >> 7 & 1;
         out[29 + outPos] = in[76 + inPos] << 14 & 2097151 | in[77 + inPos] << 6 & 16383 | in[78 + inPos] >> 2 & 63;
         out[30 + outPos] = in[78 + inPos] << 19 & 2097151 | in[79 + inPos] << 11 & 524287 | in[80 + inPos] << 3 & 2047 | in[81 + inPos] >> 5 & 7;
         out[31 + outPos] = in[81 + inPos] << 16 & 2097151 | in[82 + inPos] << 8 & '\uffff' | in[83 + inPos] & 255;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 13 & 2097151 | in.get(1 + inPos) << 5 & 8191 | in.get(2 + inPos) >> 3 & 31;
         out[1 + outPos] = in.get(2 + inPos) << 18 & 2097151 | in.get(3 + inPos) << 10 & 262143 | in.get(4 + inPos) << 2 & 1023 | in.get(5 + inPos) >> 6 & 3;
         out[2 + outPos] = in.get(5 + inPos) << 15 & 2097151 | in.get(6 + inPos) << 7 & 32767 | in.get(7 + inPos) >> 1 & 127;
         out[3 + outPos] = in.get(7 + inPos) << 20 & 2097151 | in.get(8 + inPos) << 12 & 1048575 | in.get(9 + inPos) << 4 & 4095 | in.get(10 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(10 + inPos) << 17 & 2097151 | in.get(11 + inPos) << 9 & 131071 | in.get(12 + inPos) << 1 & 511 | in.get(13 + inPos) >> 7 & 1;
         out[5 + outPos] = in.get(13 + inPos) << 14 & 2097151 | in.get(14 + inPos) << 6 & 16383 | in.get(15 + inPos) >> 2 & 63;
         out[6 + outPos] = in.get(15 + inPos) << 19 & 2097151 | in.get(16 + inPos) << 11 & 524287 | in.get(17 + inPos) << 3 & 2047 | in.get(18 + inPos) >> 5 & 7;
         out[7 + outPos] = in.get(18 + inPos) << 16 & 2097151 | in.get(19 + inPos) << 8 & '\uffff' | in.get(20 + inPos) & 255;
         out[8 + outPos] = in.get(21 + inPos) << 13 & 2097151 | in.get(22 + inPos) << 5 & 8191 | in.get(23 + inPos) >> 3 & 31;
         out[9 + outPos] = in.get(23 + inPos) << 18 & 2097151 | in.get(24 + inPos) << 10 & 262143 | in.get(25 + inPos) << 2 & 1023 | in.get(26 + inPos) >> 6 & 3;
         out[10 + outPos] = in.get(26 + inPos) << 15 & 2097151 | in.get(27 + inPos) << 7 & 32767 | in.get(28 + inPos) >> 1 & 127;
         out[11 + outPos] = in.get(28 + inPos) << 20 & 2097151 | in.get(29 + inPos) << 12 & 1048575 | in.get(30 + inPos) << 4 & 4095 | in.get(31 + inPos) >> 4 & 15;
         out[12 + outPos] = in.get(31 + inPos) << 17 & 2097151 | in.get(32 + inPos) << 9 & 131071 | in.get(33 + inPos) << 1 & 511 | in.get(34 + inPos) >> 7 & 1;
         out[13 + outPos] = in.get(34 + inPos) << 14 & 2097151 | in.get(35 + inPos) << 6 & 16383 | in.get(36 + inPos) >> 2 & 63;
         out[14 + outPos] = in.get(36 + inPos) << 19 & 2097151 | in.get(37 + inPos) << 11 & 524287 | in.get(38 + inPos) << 3 & 2047 | in.get(39 + inPos) >> 5 & 7;
         out[15 + outPos] = in.get(39 + inPos) << 16 & 2097151 | in.get(40 + inPos) << 8 & '\uffff' | in.get(41 + inPos) & 255;
         out[16 + outPos] = in.get(42 + inPos) << 13 & 2097151 | in.get(43 + inPos) << 5 & 8191 | in.get(44 + inPos) >> 3 & 31;
         out[17 + outPos] = in.get(44 + inPos) << 18 & 2097151 | in.get(45 + inPos) << 10 & 262143 | in.get(46 + inPos) << 2 & 1023 | in.get(47 + inPos) >> 6 & 3;
         out[18 + outPos] = in.get(47 + inPos) << 15 & 2097151 | in.get(48 + inPos) << 7 & 32767 | in.get(49 + inPos) >> 1 & 127;
         out[19 + outPos] = in.get(49 + inPos) << 20 & 2097151 | in.get(50 + inPos) << 12 & 1048575 | in.get(51 + inPos) << 4 & 4095 | in.get(52 + inPos) >> 4 & 15;
         out[20 + outPos] = in.get(52 + inPos) << 17 & 2097151 | in.get(53 + inPos) << 9 & 131071 | in.get(54 + inPos) << 1 & 511 | in.get(55 + inPos) >> 7 & 1;
         out[21 + outPos] = in.get(55 + inPos) << 14 & 2097151 | in.get(56 + inPos) << 6 & 16383 | in.get(57 + inPos) >> 2 & 63;
         out[22 + outPos] = in.get(57 + inPos) << 19 & 2097151 | in.get(58 + inPos) << 11 & 524287 | in.get(59 + inPos) << 3 & 2047 | in.get(60 + inPos) >> 5 & 7;
         out[23 + outPos] = in.get(60 + inPos) << 16 & 2097151 | in.get(61 + inPos) << 8 & '\uffff' | in.get(62 + inPos) & 255;
         out[24 + outPos] = in.get(63 + inPos) << 13 & 2097151 | in.get(64 + inPos) << 5 & 8191 | in.get(65 + inPos) >> 3 & 31;
         out[25 + outPos] = in.get(65 + inPos) << 18 & 2097151 | in.get(66 + inPos) << 10 & 262143 | in.get(67 + inPos) << 2 & 1023 | in.get(68 + inPos) >> 6 & 3;
         out[26 + outPos] = in.get(68 + inPos) << 15 & 2097151 | in.get(69 + inPos) << 7 & 32767 | in.get(70 + inPos) >> 1 & 127;
         out[27 + outPos] = in.get(70 + inPos) << 20 & 2097151 | in.get(71 + inPos) << 12 & 1048575 | in.get(72 + inPos) << 4 & 4095 | in.get(73 + inPos) >> 4 & 15;
         out[28 + outPos] = in.get(73 + inPos) << 17 & 2097151 | in.get(74 + inPos) << 9 & 131071 | in.get(75 + inPos) << 1 & 511 | in.get(76 + inPos) >> 7 & 1;
         out[29 + outPos] = in.get(76 + inPos) << 14 & 2097151 | in.get(77 + inPos) << 6 & 16383 | in.get(78 + inPos) >> 2 & 63;
         out[30 + outPos] = in.get(78 + inPos) << 19 & 2097151 | in.get(79 + inPos) << 11 & 524287 | in.get(80 + inPos) << 3 & 2047 | in.get(81 + inPos) >> 5 & 7;
         out[31 + outPos] = in.get(81 + inPos) << 16 & 2097151 | in.get(82 + inPos) << 8 & '\uffff' | in.get(83 + inPos) & 255;
      }
   }

   private static final class Packer22 extends BytePacker {
      private Packer22() {
         super(22);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 4194303) >>> 14 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 4194303) >>> 6 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 4194303) << 2 | (in[1 + inPos] & 4194303) >>> 20) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 4194303) >>> 12 & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 4194303) >>> 4 & 255);
         out[5 + outPos] = (byte)(((in[1 + inPos] & 4194303) << 4 | (in[2 + inPos] & 4194303) >>> 18) & 255);
         out[6 + outPos] = (byte)((in[2 + inPos] & 4194303) >>> 10 & 255);
         out[7 + outPos] = (byte)((in[2 + inPos] & 4194303) >>> 2 & 255);
         out[8 + outPos] = (byte)(((in[2 + inPos] & 4194303) << 6 | (in[3 + inPos] & 4194303) >>> 16) & 255);
         out[9 + outPos] = (byte)((in[3 + inPos] & 4194303) >>> 8 & 255);
         out[10 + outPos] = (byte)(in[3 + inPos] & 4194303 & 255);
         out[11 + outPos] = (byte)((in[4 + inPos] & 4194303) >>> 14 & 255);
         out[12 + outPos] = (byte)((in[4 + inPos] & 4194303) >>> 6 & 255);
         out[13 + outPos] = (byte)(((in[4 + inPos] & 4194303) << 2 | (in[5 + inPos] & 4194303) >>> 20) & 255);
         out[14 + outPos] = (byte)((in[5 + inPos] & 4194303) >>> 12 & 255);
         out[15 + outPos] = (byte)((in[5 + inPos] & 4194303) >>> 4 & 255);
         out[16 + outPos] = (byte)(((in[5 + inPos] & 4194303) << 4 | (in[6 + inPos] & 4194303) >>> 18) & 255);
         out[17 + outPos] = (byte)((in[6 + inPos] & 4194303) >>> 10 & 255);
         out[18 + outPos] = (byte)((in[6 + inPos] & 4194303) >>> 2 & 255);
         out[19 + outPos] = (byte)(((in[6 + inPos] & 4194303) << 6 | (in[7 + inPos] & 4194303) >>> 16) & 255);
         out[20 + outPos] = (byte)((in[7 + inPos] & 4194303) >>> 8 & 255);
         out[21 + outPos] = (byte)(in[7 + inPos] & 4194303 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 4194303) >>> 14 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 4194303) >>> 6 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 4194303) << 2 | (in[1 + inPos] & 4194303) >>> 20) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 4194303) >>> 12 & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 4194303) >>> 4 & 255);
         out[5 + outPos] = (byte)(((in[1 + inPos] & 4194303) << 4 | (in[2 + inPos] & 4194303) >>> 18) & 255);
         out[6 + outPos] = (byte)((in[2 + inPos] & 4194303) >>> 10 & 255);
         out[7 + outPos] = (byte)((in[2 + inPos] & 4194303) >>> 2 & 255);
         out[8 + outPos] = (byte)(((in[2 + inPos] & 4194303) << 6 | (in[3 + inPos] & 4194303) >>> 16) & 255);
         out[9 + outPos] = (byte)((in[3 + inPos] & 4194303) >>> 8 & 255);
         out[10 + outPos] = (byte)(in[3 + inPos] & 4194303 & 255);
         out[11 + outPos] = (byte)((in[4 + inPos] & 4194303) >>> 14 & 255);
         out[12 + outPos] = (byte)((in[4 + inPos] & 4194303) >>> 6 & 255);
         out[13 + outPos] = (byte)(((in[4 + inPos] & 4194303) << 2 | (in[5 + inPos] & 4194303) >>> 20) & 255);
         out[14 + outPos] = (byte)((in[5 + inPos] & 4194303) >>> 12 & 255);
         out[15 + outPos] = (byte)((in[5 + inPos] & 4194303) >>> 4 & 255);
         out[16 + outPos] = (byte)(((in[5 + inPos] & 4194303) << 4 | (in[6 + inPos] & 4194303) >>> 18) & 255);
         out[17 + outPos] = (byte)((in[6 + inPos] & 4194303) >>> 10 & 255);
         out[18 + outPos] = (byte)((in[6 + inPos] & 4194303) >>> 2 & 255);
         out[19 + outPos] = (byte)(((in[6 + inPos] & 4194303) << 6 | (in[7 + inPos] & 4194303) >>> 16) & 255);
         out[20 + outPos] = (byte)((in[7 + inPos] & 4194303) >>> 8 & 255);
         out[21 + outPos] = (byte)(in[7 + inPos] & 4194303 & 255);
         out[22 + outPos] = (byte)((in[8 + inPos] & 4194303) >>> 14 & 255);
         out[23 + outPos] = (byte)((in[8 + inPos] & 4194303) >>> 6 & 255);
         out[24 + outPos] = (byte)(((in[8 + inPos] & 4194303) << 2 | (in[9 + inPos] & 4194303) >>> 20) & 255);
         out[25 + outPos] = (byte)((in[9 + inPos] & 4194303) >>> 12 & 255);
         out[26 + outPos] = (byte)((in[9 + inPos] & 4194303) >>> 4 & 255);
         out[27 + outPos] = (byte)(((in[9 + inPos] & 4194303) << 4 | (in[10 + inPos] & 4194303) >>> 18) & 255);
         out[28 + outPos] = (byte)((in[10 + inPos] & 4194303) >>> 10 & 255);
         out[29 + outPos] = (byte)((in[10 + inPos] & 4194303) >>> 2 & 255);
         out[30 + outPos] = (byte)(((in[10 + inPos] & 4194303) << 6 | (in[11 + inPos] & 4194303) >>> 16) & 255);
         out[31 + outPos] = (byte)((in[11 + inPos] & 4194303) >>> 8 & 255);
         out[32 + outPos] = (byte)(in[11 + inPos] & 4194303 & 255);
         out[33 + outPos] = (byte)((in[12 + inPos] & 4194303) >>> 14 & 255);
         out[34 + outPos] = (byte)((in[12 + inPos] & 4194303) >>> 6 & 255);
         out[35 + outPos] = (byte)(((in[12 + inPos] & 4194303) << 2 | (in[13 + inPos] & 4194303) >>> 20) & 255);
         out[36 + outPos] = (byte)((in[13 + inPos] & 4194303) >>> 12 & 255);
         out[37 + outPos] = (byte)((in[13 + inPos] & 4194303) >>> 4 & 255);
         out[38 + outPos] = (byte)(((in[13 + inPos] & 4194303) << 4 | (in[14 + inPos] & 4194303) >>> 18) & 255);
         out[39 + outPos] = (byte)((in[14 + inPos] & 4194303) >>> 10 & 255);
         out[40 + outPos] = (byte)((in[14 + inPos] & 4194303) >>> 2 & 255);
         out[41 + outPos] = (byte)(((in[14 + inPos] & 4194303) << 6 | (in[15 + inPos] & 4194303) >>> 16) & 255);
         out[42 + outPos] = (byte)((in[15 + inPos] & 4194303) >>> 8 & 255);
         out[43 + outPos] = (byte)(in[15 + inPos] & 4194303 & 255);
         out[44 + outPos] = (byte)((in[16 + inPos] & 4194303) >>> 14 & 255);
         out[45 + outPos] = (byte)((in[16 + inPos] & 4194303) >>> 6 & 255);
         out[46 + outPos] = (byte)(((in[16 + inPos] & 4194303) << 2 | (in[17 + inPos] & 4194303) >>> 20) & 255);
         out[47 + outPos] = (byte)((in[17 + inPos] & 4194303) >>> 12 & 255);
         out[48 + outPos] = (byte)((in[17 + inPos] & 4194303) >>> 4 & 255);
         out[49 + outPos] = (byte)(((in[17 + inPos] & 4194303) << 4 | (in[18 + inPos] & 4194303) >>> 18) & 255);
         out[50 + outPos] = (byte)((in[18 + inPos] & 4194303) >>> 10 & 255);
         out[51 + outPos] = (byte)((in[18 + inPos] & 4194303) >>> 2 & 255);
         out[52 + outPos] = (byte)(((in[18 + inPos] & 4194303) << 6 | (in[19 + inPos] & 4194303) >>> 16) & 255);
         out[53 + outPos] = (byte)((in[19 + inPos] & 4194303) >>> 8 & 255);
         out[54 + outPos] = (byte)(in[19 + inPos] & 4194303 & 255);
         out[55 + outPos] = (byte)((in[20 + inPos] & 4194303) >>> 14 & 255);
         out[56 + outPos] = (byte)((in[20 + inPos] & 4194303) >>> 6 & 255);
         out[57 + outPos] = (byte)(((in[20 + inPos] & 4194303) << 2 | (in[21 + inPos] & 4194303) >>> 20) & 255);
         out[58 + outPos] = (byte)((in[21 + inPos] & 4194303) >>> 12 & 255);
         out[59 + outPos] = (byte)((in[21 + inPos] & 4194303) >>> 4 & 255);
         out[60 + outPos] = (byte)(((in[21 + inPos] & 4194303) << 4 | (in[22 + inPos] & 4194303) >>> 18) & 255);
         out[61 + outPos] = (byte)((in[22 + inPos] & 4194303) >>> 10 & 255);
         out[62 + outPos] = (byte)((in[22 + inPos] & 4194303) >>> 2 & 255);
         out[63 + outPos] = (byte)(((in[22 + inPos] & 4194303) << 6 | (in[23 + inPos] & 4194303) >>> 16) & 255);
         out[64 + outPos] = (byte)((in[23 + inPos] & 4194303) >>> 8 & 255);
         out[65 + outPos] = (byte)(in[23 + inPos] & 4194303 & 255);
         out[66 + outPos] = (byte)((in[24 + inPos] & 4194303) >>> 14 & 255);
         out[67 + outPos] = (byte)((in[24 + inPos] & 4194303) >>> 6 & 255);
         out[68 + outPos] = (byte)(((in[24 + inPos] & 4194303) << 2 | (in[25 + inPos] & 4194303) >>> 20) & 255);
         out[69 + outPos] = (byte)((in[25 + inPos] & 4194303) >>> 12 & 255);
         out[70 + outPos] = (byte)((in[25 + inPos] & 4194303) >>> 4 & 255);
         out[71 + outPos] = (byte)(((in[25 + inPos] & 4194303) << 4 | (in[26 + inPos] & 4194303) >>> 18) & 255);
         out[72 + outPos] = (byte)((in[26 + inPos] & 4194303) >>> 10 & 255);
         out[73 + outPos] = (byte)((in[26 + inPos] & 4194303) >>> 2 & 255);
         out[74 + outPos] = (byte)(((in[26 + inPos] & 4194303) << 6 | (in[27 + inPos] & 4194303) >>> 16) & 255);
         out[75 + outPos] = (byte)((in[27 + inPos] & 4194303) >>> 8 & 255);
         out[76 + outPos] = (byte)(in[27 + inPos] & 4194303 & 255);
         out[77 + outPos] = (byte)((in[28 + inPos] & 4194303) >>> 14 & 255);
         out[78 + outPos] = (byte)((in[28 + inPos] & 4194303) >>> 6 & 255);
         out[79 + outPos] = (byte)(((in[28 + inPos] & 4194303) << 2 | (in[29 + inPos] & 4194303) >>> 20) & 255);
         out[80 + outPos] = (byte)((in[29 + inPos] & 4194303) >>> 12 & 255);
         out[81 + outPos] = (byte)((in[29 + inPos] & 4194303) >>> 4 & 255);
         out[82 + outPos] = (byte)(((in[29 + inPos] & 4194303) << 4 | (in[30 + inPos] & 4194303) >>> 18) & 255);
         out[83 + outPos] = (byte)((in[30 + inPos] & 4194303) >>> 10 & 255);
         out[84 + outPos] = (byte)((in[30 + inPos] & 4194303) >>> 2 & 255);
         out[85 + outPos] = (byte)(((in[30 + inPos] & 4194303) << 6 | (in[31 + inPos] & 4194303) >>> 16) & 255);
         out[86 + outPos] = (byte)((in[31 + inPos] & 4194303) >>> 8 & 255);
         out[87 + outPos] = (byte)(in[31 + inPos] & 4194303 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 14 & 4194303 | in[1 + inPos] << 6 & 16383 | in[2 + inPos] >> 2 & 63;
         out[1 + outPos] = in[2 + inPos] << 20 & 4194303 | in[3 + inPos] << 12 & 1048575 | in[4 + inPos] << 4 & 4095 | in[5 + inPos] >> 4 & 15;
         out[2 + outPos] = in[5 + inPos] << 18 & 4194303 | in[6 + inPos] << 10 & 262143 | in[7 + inPos] << 2 & 1023 | in[8 + inPos] >> 6 & 3;
         out[3 + outPos] = in[8 + inPos] << 16 & 4194303 | in[9 + inPos] << 8 & '\uffff' | in[10 + inPos] & 255;
         out[4 + outPos] = in[11 + inPos] << 14 & 4194303 | in[12 + inPos] << 6 & 16383 | in[13 + inPos] >> 2 & 63;
         out[5 + outPos] = in[13 + inPos] << 20 & 4194303 | in[14 + inPos] << 12 & 1048575 | in[15 + inPos] << 4 & 4095 | in[16 + inPos] >> 4 & 15;
         out[6 + outPos] = in[16 + inPos] << 18 & 4194303 | in[17 + inPos] << 10 & 262143 | in[18 + inPos] << 2 & 1023 | in[19 + inPos] >> 6 & 3;
         out[7 + outPos] = in[19 + inPos] << 16 & 4194303 | in[20 + inPos] << 8 & '\uffff' | in[21 + inPos] & 255;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 14 & 4194303 | in.get(1 + inPos) << 6 & 16383 | in.get(2 + inPos) >> 2 & 63;
         out[1 + outPos] = in.get(2 + inPos) << 20 & 4194303 | in.get(3 + inPos) << 12 & 1048575 | in.get(4 + inPos) << 4 & 4095 | in.get(5 + inPos) >> 4 & 15;
         out[2 + outPos] = in.get(5 + inPos) << 18 & 4194303 | in.get(6 + inPos) << 10 & 262143 | in.get(7 + inPos) << 2 & 1023 | in.get(8 + inPos) >> 6 & 3;
         out[3 + outPos] = in.get(8 + inPos) << 16 & 4194303 | in.get(9 + inPos) << 8 & '\uffff' | in.get(10 + inPos) & 255;
         out[4 + outPos] = in.get(11 + inPos) << 14 & 4194303 | in.get(12 + inPos) << 6 & 16383 | in.get(13 + inPos) >> 2 & 63;
         out[5 + outPos] = in.get(13 + inPos) << 20 & 4194303 | in.get(14 + inPos) << 12 & 1048575 | in.get(15 + inPos) << 4 & 4095 | in.get(16 + inPos) >> 4 & 15;
         out[6 + outPos] = in.get(16 + inPos) << 18 & 4194303 | in.get(17 + inPos) << 10 & 262143 | in.get(18 + inPos) << 2 & 1023 | in.get(19 + inPos) >> 6 & 3;
         out[7 + outPos] = in.get(19 + inPos) << 16 & 4194303 | in.get(20 + inPos) << 8 & '\uffff' | in.get(21 + inPos) & 255;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 14 & 4194303 | in[1 + inPos] << 6 & 16383 | in[2 + inPos] >> 2 & 63;
         out[1 + outPos] = in[2 + inPos] << 20 & 4194303 | in[3 + inPos] << 12 & 1048575 | in[4 + inPos] << 4 & 4095 | in[5 + inPos] >> 4 & 15;
         out[2 + outPos] = in[5 + inPos] << 18 & 4194303 | in[6 + inPos] << 10 & 262143 | in[7 + inPos] << 2 & 1023 | in[8 + inPos] >> 6 & 3;
         out[3 + outPos] = in[8 + inPos] << 16 & 4194303 | in[9 + inPos] << 8 & '\uffff' | in[10 + inPos] & 255;
         out[4 + outPos] = in[11 + inPos] << 14 & 4194303 | in[12 + inPos] << 6 & 16383 | in[13 + inPos] >> 2 & 63;
         out[5 + outPos] = in[13 + inPos] << 20 & 4194303 | in[14 + inPos] << 12 & 1048575 | in[15 + inPos] << 4 & 4095 | in[16 + inPos] >> 4 & 15;
         out[6 + outPos] = in[16 + inPos] << 18 & 4194303 | in[17 + inPos] << 10 & 262143 | in[18 + inPos] << 2 & 1023 | in[19 + inPos] >> 6 & 3;
         out[7 + outPos] = in[19 + inPos] << 16 & 4194303 | in[20 + inPos] << 8 & '\uffff' | in[21 + inPos] & 255;
         out[8 + outPos] = in[22 + inPos] << 14 & 4194303 | in[23 + inPos] << 6 & 16383 | in[24 + inPos] >> 2 & 63;
         out[9 + outPos] = in[24 + inPos] << 20 & 4194303 | in[25 + inPos] << 12 & 1048575 | in[26 + inPos] << 4 & 4095 | in[27 + inPos] >> 4 & 15;
         out[10 + outPos] = in[27 + inPos] << 18 & 4194303 | in[28 + inPos] << 10 & 262143 | in[29 + inPos] << 2 & 1023 | in[30 + inPos] >> 6 & 3;
         out[11 + outPos] = in[30 + inPos] << 16 & 4194303 | in[31 + inPos] << 8 & '\uffff' | in[32 + inPos] & 255;
         out[12 + outPos] = in[33 + inPos] << 14 & 4194303 | in[34 + inPos] << 6 & 16383 | in[35 + inPos] >> 2 & 63;
         out[13 + outPos] = in[35 + inPos] << 20 & 4194303 | in[36 + inPos] << 12 & 1048575 | in[37 + inPos] << 4 & 4095 | in[38 + inPos] >> 4 & 15;
         out[14 + outPos] = in[38 + inPos] << 18 & 4194303 | in[39 + inPos] << 10 & 262143 | in[40 + inPos] << 2 & 1023 | in[41 + inPos] >> 6 & 3;
         out[15 + outPos] = in[41 + inPos] << 16 & 4194303 | in[42 + inPos] << 8 & '\uffff' | in[43 + inPos] & 255;
         out[16 + outPos] = in[44 + inPos] << 14 & 4194303 | in[45 + inPos] << 6 & 16383 | in[46 + inPos] >> 2 & 63;
         out[17 + outPos] = in[46 + inPos] << 20 & 4194303 | in[47 + inPos] << 12 & 1048575 | in[48 + inPos] << 4 & 4095 | in[49 + inPos] >> 4 & 15;
         out[18 + outPos] = in[49 + inPos] << 18 & 4194303 | in[50 + inPos] << 10 & 262143 | in[51 + inPos] << 2 & 1023 | in[52 + inPos] >> 6 & 3;
         out[19 + outPos] = in[52 + inPos] << 16 & 4194303 | in[53 + inPos] << 8 & '\uffff' | in[54 + inPos] & 255;
         out[20 + outPos] = in[55 + inPos] << 14 & 4194303 | in[56 + inPos] << 6 & 16383 | in[57 + inPos] >> 2 & 63;
         out[21 + outPos] = in[57 + inPos] << 20 & 4194303 | in[58 + inPos] << 12 & 1048575 | in[59 + inPos] << 4 & 4095 | in[60 + inPos] >> 4 & 15;
         out[22 + outPos] = in[60 + inPos] << 18 & 4194303 | in[61 + inPos] << 10 & 262143 | in[62 + inPos] << 2 & 1023 | in[63 + inPos] >> 6 & 3;
         out[23 + outPos] = in[63 + inPos] << 16 & 4194303 | in[64 + inPos] << 8 & '\uffff' | in[65 + inPos] & 255;
         out[24 + outPos] = in[66 + inPos] << 14 & 4194303 | in[67 + inPos] << 6 & 16383 | in[68 + inPos] >> 2 & 63;
         out[25 + outPos] = in[68 + inPos] << 20 & 4194303 | in[69 + inPos] << 12 & 1048575 | in[70 + inPos] << 4 & 4095 | in[71 + inPos] >> 4 & 15;
         out[26 + outPos] = in[71 + inPos] << 18 & 4194303 | in[72 + inPos] << 10 & 262143 | in[73 + inPos] << 2 & 1023 | in[74 + inPos] >> 6 & 3;
         out[27 + outPos] = in[74 + inPos] << 16 & 4194303 | in[75 + inPos] << 8 & '\uffff' | in[76 + inPos] & 255;
         out[28 + outPos] = in[77 + inPos] << 14 & 4194303 | in[78 + inPos] << 6 & 16383 | in[79 + inPos] >> 2 & 63;
         out[29 + outPos] = in[79 + inPos] << 20 & 4194303 | in[80 + inPos] << 12 & 1048575 | in[81 + inPos] << 4 & 4095 | in[82 + inPos] >> 4 & 15;
         out[30 + outPos] = in[82 + inPos] << 18 & 4194303 | in[83 + inPos] << 10 & 262143 | in[84 + inPos] << 2 & 1023 | in[85 + inPos] >> 6 & 3;
         out[31 + outPos] = in[85 + inPos] << 16 & 4194303 | in[86 + inPos] << 8 & '\uffff' | in[87 + inPos] & 255;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 14 & 4194303 | in.get(1 + inPos) << 6 & 16383 | in.get(2 + inPos) >> 2 & 63;
         out[1 + outPos] = in.get(2 + inPos) << 20 & 4194303 | in.get(3 + inPos) << 12 & 1048575 | in.get(4 + inPos) << 4 & 4095 | in.get(5 + inPos) >> 4 & 15;
         out[2 + outPos] = in.get(5 + inPos) << 18 & 4194303 | in.get(6 + inPos) << 10 & 262143 | in.get(7 + inPos) << 2 & 1023 | in.get(8 + inPos) >> 6 & 3;
         out[3 + outPos] = in.get(8 + inPos) << 16 & 4194303 | in.get(9 + inPos) << 8 & '\uffff' | in.get(10 + inPos) & 255;
         out[4 + outPos] = in.get(11 + inPos) << 14 & 4194303 | in.get(12 + inPos) << 6 & 16383 | in.get(13 + inPos) >> 2 & 63;
         out[5 + outPos] = in.get(13 + inPos) << 20 & 4194303 | in.get(14 + inPos) << 12 & 1048575 | in.get(15 + inPos) << 4 & 4095 | in.get(16 + inPos) >> 4 & 15;
         out[6 + outPos] = in.get(16 + inPos) << 18 & 4194303 | in.get(17 + inPos) << 10 & 262143 | in.get(18 + inPos) << 2 & 1023 | in.get(19 + inPos) >> 6 & 3;
         out[7 + outPos] = in.get(19 + inPos) << 16 & 4194303 | in.get(20 + inPos) << 8 & '\uffff' | in.get(21 + inPos) & 255;
         out[8 + outPos] = in.get(22 + inPos) << 14 & 4194303 | in.get(23 + inPos) << 6 & 16383 | in.get(24 + inPos) >> 2 & 63;
         out[9 + outPos] = in.get(24 + inPos) << 20 & 4194303 | in.get(25 + inPos) << 12 & 1048575 | in.get(26 + inPos) << 4 & 4095 | in.get(27 + inPos) >> 4 & 15;
         out[10 + outPos] = in.get(27 + inPos) << 18 & 4194303 | in.get(28 + inPos) << 10 & 262143 | in.get(29 + inPos) << 2 & 1023 | in.get(30 + inPos) >> 6 & 3;
         out[11 + outPos] = in.get(30 + inPos) << 16 & 4194303 | in.get(31 + inPos) << 8 & '\uffff' | in.get(32 + inPos) & 255;
         out[12 + outPos] = in.get(33 + inPos) << 14 & 4194303 | in.get(34 + inPos) << 6 & 16383 | in.get(35 + inPos) >> 2 & 63;
         out[13 + outPos] = in.get(35 + inPos) << 20 & 4194303 | in.get(36 + inPos) << 12 & 1048575 | in.get(37 + inPos) << 4 & 4095 | in.get(38 + inPos) >> 4 & 15;
         out[14 + outPos] = in.get(38 + inPos) << 18 & 4194303 | in.get(39 + inPos) << 10 & 262143 | in.get(40 + inPos) << 2 & 1023 | in.get(41 + inPos) >> 6 & 3;
         out[15 + outPos] = in.get(41 + inPos) << 16 & 4194303 | in.get(42 + inPos) << 8 & '\uffff' | in.get(43 + inPos) & 255;
         out[16 + outPos] = in.get(44 + inPos) << 14 & 4194303 | in.get(45 + inPos) << 6 & 16383 | in.get(46 + inPos) >> 2 & 63;
         out[17 + outPos] = in.get(46 + inPos) << 20 & 4194303 | in.get(47 + inPos) << 12 & 1048575 | in.get(48 + inPos) << 4 & 4095 | in.get(49 + inPos) >> 4 & 15;
         out[18 + outPos] = in.get(49 + inPos) << 18 & 4194303 | in.get(50 + inPos) << 10 & 262143 | in.get(51 + inPos) << 2 & 1023 | in.get(52 + inPos) >> 6 & 3;
         out[19 + outPos] = in.get(52 + inPos) << 16 & 4194303 | in.get(53 + inPos) << 8 & '\uffff' | in.get(54 + inPos) & 255;
         out[20 + outPos] = in.get(55 + inPos) << 14 & 4194303 | in.get(56 + inPos) << 6 & 16383 | in.get(57 + inPos) >> 2 & 63;
         out[21 + outPos] = in.get(57 + inPos) << 20 & 4194303 | in.get(58 + inPos) << 12 & 1048575 | in.get(59 + inPos) << 4 & 4095 | in.get(60 + inPos) >> 4 & 15;
         out[22 + outPos] = in.get(60 + inPos) << 18 & 4194303 | in.get(61 + inPos) << 10 & 262143 | in.get(62 + inPos) << 2 & 1023 | in.get(63 + inPos) >> 6 & 3;
         out[23 + outPos] = in.get(63 + inPos) << 16 & 4194303 | in.get(64 + inPos) << 8 & '\uffff' | in.get(65 + inPos) & 255;
         out[24 + outPos] = in.get(66 + inPos) << 14 & 4194303 | in.get(67 + inPos) << 6 & 16383 | in.get(68 + inPos) >> 2 & 63;
         out[25 + outPos] = in.get(68 + inPos) << 20 & 4194303 | in.get(69 + inPos) << 12 & 1048575 | in.get(70 + inPos) << 4 & 4095 | in.get(71 + inPos) >> 4 & 15;
         out[26 + outPos] = in.get(71 + inPos) << 18 & 4194303 | in.get(72 + inPos) << 10 & 262143 | in.get(73 + inPos) << 2 & 1023 | in.get(74 + inPos) >> 6 & 3;
         out[27 + outPos] = in.get(74 + inPos) << 16 & 4194303 | in.get(75 + inPos) << 8 & '\uffff' | in.get(76 + inPos) & 255;
         out[28 + outPos] = in.get(77 + inPos) << 14 & 4194303 | in.get(78 + inPos) << 6 & 16383 | in.get(79 + inPos) >> 2 & 63;
         out[29 + outPos] = in.get(79 + inPos) << 20 & 4194303 | in.get(80 + inPos) << 12 & 1048575 | in.get(81 + inPos) << 4 & 4095 | in.get(82 + inPos) >> 4 & 15;
         out[30 + outPos] = in.get(82 + inPos) << 18 & 4194303 | in.get(83 + inPos) << 10 & 262143 | in.get(84 + inPos) << 2 & 1023 | in.get(85 + inPos) >> 6 & 3;
         out[31 + outPos] = in.get(85 + inPos) << 16 & 4194303 | in.get(86 + inPos) << 8 & '\uffff' | in.get(87 + inPos) & 255;
      }
   }

   private static final class Packer23 extends BytePacker {
      private Packer23() {
         super(23);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 8388607) >>> 15 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 8388607) >>> 7 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 8388607) << 1 | (in[1 + inPos] & 8388607) >>> 22) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 8388607) >>> 14 & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 8388607) >>> 6 & 255);
         out[5 + outPos] = (byte)(((in[1 + inPos] & 8388607) << 2 | (in[2 + inPos] & 8388607) >>> 21) & 255);
         out[6 + outPos] = (byte)((in[2 + inPos] & 8388607) >>> 13 & 255);
         out[7 + outPos] = (byte)((in[2 + inPos] & 8388607) >>> 5 & 255);
         out[8 + outPos] = (byte)(((in[2 + inPos] & 8388607) << 3 | (in[3 + inPos] & 8388607) >>> 20) & 255);
         out[9 + outPos] = (byte)((in[3 + inPos] & 8388607) >>> 12 & 255);
         out[10 + outPos] = (byte)((in[3 + inPos] & 8388607) >>> 4 & 255);
         out[11 + outPos] = (byte)(((in[3 + inPos] & 8388607) << 4 | (in[4 + inPos] & 8388607) >>> 19) & 255);
         out[12 + outPos] = (byte)((in[4 + inPos] & 8388607) >>> 11 & 255);
         out[13 + outPos] = (byte)((in[4 + inPos] & 8388607) >>> 3 & 255);
         out[14 + outPos] = (byte)(((in[4 + inPos] & 8388607) << 5 | (in[5 + inPos] & 8388607) >>> 18) & 255);
         out[15 + outPos] = (byte)((in[5 + inPos] & 8388607) >>> 10 & 255);
         out[16 + outPos] = (byte)((in[5 + inPos] & 8388607) >>> 2 & 255);
         out[17 + outPos] = (byte)(((in[5 + inPos] & 8388607) << 6 | (in[6 + inPos] & 8388607) >>> 17) & 255);
         out[18 + outPos] = (byte)((in[6 + inPos] & 8388607) >>> 9 & 255);
         out[19 + outPos] = (byte)((in[6 + inPos] & 8388607) >>> 1 & 255);
         out[20 + outPos] = (byte)(((in[6 + inPos] & 8388607) << 7 | (in[7 + inPos] & 8388607) >>> 16) & 255);
         out[21 + outPos] = (byte)((in[7 + inPos] & 8388607) >>> 8 & 255);
         out[22 + outPos] = (byte)(in[7 + inPos] & 8388607 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 8388607) >>> 15 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 8388607) >>> 7 & 255);
         out[2 + outPos] = (byte)(((in[0 + inPos] & 8388607) << 1 | (in[1 + inPos] & 8388607) >>> 22) & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 8388607) >>> 14 & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 8388607) >>> 6 & 255);
         out[5 + outPos] = (byte)(((in[1 + inPos] & 8388607) << 2 | (in[2 + inPos] & 8388607) >>> 21) & 255);
         out[6 + outPos] = (byte)((in[2 + inPos] & 8388607) >>> 13 & 255);
         out[7 + outPos] = (byte)((in[2 + inPos] & 8388607) >>> 5 & 255);
         out[8 + outPos] = (byte)(((in[2 + inPos] & 8388607) << 3 | (in[3 + inPos] & 8388607) >>> 20) & 255);
         out[9 + outPos] = (byte)((in[3 + inPos] & 8388607) >>> 12 & 255);
         out[10 + outPos] = (byte)((in[3 + inPos] & 8388607) >>> 4 & 255);
         out[11 + outPos] = (byte)(((in[3 + inPos] & 8388607) << 4 | (in[4 + inPos] & 8388607) >>> 19) & 255);
         out[12 + outPos] = (byte)((in[4 + inPos] & 8388607) >>> 11 & 255);
         out[13 + outPos] = (byte)((in[4 + inPos] & 8388607) >>> 3 & 255);
         out[14 + outPos] = (byte)(((in[4 + inPos] & 8388607) << 5 | (in[5 + inPos] & 8388607) >>> 18) & 255);
         out[15 + outPos] = (byte)((in[5 + inPos] & 8388607) >>> 10 & 255);
         out[16 + outPos] = (byte)((in[5 + inPos] & 8388607) >>> 2 & 255);
         out[17 + outPos] = (byte)(((in[5 + inPos] & 8388607) << 6 | (in[6 + inPos] & 8388607) >>> 17) & 255);
         out[18 + outPos] = (byte)((in[6 + inPos] & 8388607) >>> 9 & 255);
         out[19 + outPos] = (byte)((in[6 + inPos] & 8388607) >>> 1 & 255);
         out[20 + outPos] = (byte)(((in[6 + inPos] & 8388607) << 7 | (in[7 + inPos] & 8388607) >>> 16) & 255);
         out[21 + outPos] = (byte)((in[7 + inPos] & 8388607) >>> 8 & 255);
         out[22 + outPos] = (byte)(in[7 + inPos] & 8388607 & 255);
         out[23 + outPos] = (byte)((in[8 + inPos] & 8388607) >>> 15 & 255);
         out[24 + outPos] = (byte)((in[8 + inPos] & 8388607) >>> 7 & 255);
         out[25 + outPos] = (byte)(((in[8 + inPos] & 8388607) << 1 | (in[9 + inPos] & 8388607) >>> 22) & 255);
         out[26 + outPos] = (byte)((in[9 + inPos] & 8388607) >>> 14 & 255);
         out[27 + outPos] = (byte)((in[9 + inPos] & 8388607) >>> 6 & 255);
         out[28 + outPos] = (byte)(((in[9 + inPos] & 8388607) << 2 | (in[10 + inPos] & 8388607) >>> 21) & 255);
         out[29 + outPos] = (byte)((in[10 + inPos] & 8388607) >>> 13 & 255);
         out[30 + outPos] = (byte)((in[10 + inPos] & 8388607) >>> 5 & 255);
         out[31 + outPos] = (byte)(((in[10 + inPos] & 8388607) << 3 | (in[11 + inPos] & 8388607) >>> 20) & 255);
         out[32 + outPos] = (byte)((in[11 + inPos] & 8388607) >>> 12 & 255);
         out[33 + outPos] = (byte)((in[11 + inPos] & 8388607) >>> 4 & 255);
         out[34 + outPos] = (byte)(((in[11 + inPos] & 8388607) << 4 | (in[12 + inPos] & 8388607) >>> 19) & 255);
         out[35 + outPos] = (byte)((in[12 + inPos] & 8388607) >>> 11 & 255);
         out[36 + outPos] = (byte)((in[12 + inPos] & 8388607) >>> 3 & 255);
         out[37 + outPos] = (byte)(((in[12 + inPos] & 8388607) << 5 | (in[13 + inPos] & 8388607) >>> 18) & 255);
         out[38 + outPos] = (byte)((in[13 + inPos] & 8388607) >>> 10 & 255);
         out[39 + outPos] = (byte)((in[13 + inPos] & 8388607) >>> 2 & 255);
         out[40 + outPos] = (byte)(((in[13 + inPos] & 8388607) << 6 | (in[14 + inPos] & 8388607) >>> 17) & 255);
         out[41 + outPos] = (byte)((in[14 + inPos] & 8388607) >>> 9 & 255);
         out[42 + outPos] = (byte)((in[14 + inPos] & 8388607) >>> 1 & 255);
         out[43 + outPos] = (byte)(((in[14 + inPos] & 8388607) << 7 | (in[15 + inPos] & 8388607) >>> 16) & 255);
         out[44 + outPos] = (byte)((in[15 + inPos] & 8388607) >>> 8 & 255);
         out[45 + outPos] = (byte)(in[15 + inPos] & 8388607 & 255);
         out[46 + outPos] = (byte)((in[16 + inPos] & 8388607) >>> 15 & 255);
         out[47 + outPos] = (byte)((in[16 + inPos] & 8388607) >>> 7 & 255);
         out[48 + outPos] = (byte)(((in[16 + inPos] & 8388607) << 1 | (in[17 + inPos] & 8388607) >>> 22) & 255);
         out[49 + outPos] = (byte)((in[17 + inPos] & 8388607) >>> 14 & 255);
         out[50 + outPos] = (byte)((in[17 + inPos] & 8388607) >>> 6 & 255);
         out[51 + outPos] = (byte)(((in[17 + inPos] & 8388607) << 2 | (in[18 + inPos] & 8388607) >>> 21) & 255);
         out[52 + outPos] = (byte)((in[18 + inPos] & 8388607) >>> 13 & 255);
         out[53 + outPos] = (byte)((in[18 + inPos] & 8388607) >>> 5 & 255);
         out[54 + outPos] = (byte)(((in[18 + inPos] & 8388607) << 3 | (in[19 + inPos] & 8388607) >>> 20) & 255);
         out[55 + outPos] = (byte)((in[19 + inPos] & 8388607) >>> 12 & 255);
         out[56 + outPos] = (byte)((in[19 + inPos] & 8388607) >>> 4 & 255);
         out[57 + outPos] = (byte)(((in[19 + inPos] & 8388607) << 4 | (in[20 + inPos] & 8388607) >>> 19) & 255);
         out[58 + outPos] = (byte)((in[20 + inPos] & 8388607) >>> 11 & 255);
         out[59 + outPos] = (byte)((in[20 + inPos] & 8388607) >>> 3 & 255);
         out[60 + outPos] = (byte)(((in[20 + inPos] & 8388607) << 5 | (in[21 + inPos] & 8388607) >>> 18) & 255);
         out[61 + outPos] = (byte)((in[21 + inPos] & 8388607) >>> 10 & 255);
         out[62 + outPos] = (byte)((in[21 + inPos] & 8388607) >>> 2 & 255);
         out[63 + outPos] = (byte)(((in[21 + inPos] & 8388607) << 6 | (in[22 + inPos] & 8388607) >>> 17) & 255);
         out[64 + outPos] = (byte)((in[22 + inPos] & 8388607) >>> 9 & 255);
         out[65 + outPos] = (byte)((in[22 + inPos] & 8388607) >>> 1 & 255);
         out[66 + outPos] = (byte)(((in[22 + inPos] & 8388607) << 7 | (in[23 + inPos] & 8388607) >>> 16) & 255);
         out[67 + outPos] = (byte)((in[23 + inPos] & 8388607) >>> 8 & 255);
         out[68 + outPos] = (byte)(in[23 + inPos] & 8388607 & 255);
         out[69 + outPos] = (byte)((in[24 + inPos] & 8388607) >>> 15 & 255);
         out[70 + outPos] = (byte)((in[24 + inPos] & 8388607) >>> 7 & 255);
         out[71 + outPos] = (byte)(((in[24 + inPos] & 8388607) << 1 | (in[25 + inPos] & 8388607) >>> 22) & 255);
         out[72 + outPos] = (byte)((in[25 + inPos] & 8388607) >>> 14 & 255);
         out[73 + outPos] = (byte)((in[25 + inPos] & 8388607) >>> 6 & 255);
         out[74 + outPos] = (byte)(((in[25 + inPos] & 8388607) << 2 | (in[26 + inPos] & 8388607) >>> 21) & 255);
         out[75 + outPos] = (byte)((in[26 + inPos] & 8388607) >>> 13 & 255);
         out[76 + outPos] = (byte)((in[26 + inPos] & 8388607) >>> 5 & 255);
         out[77 + outPos] = (byte)(((in[26 + inPos] & 8388607) << 3 | (in[27 + inPos] & 8388607) >>> 20) & 255);
         out[78 + outPos] = (byte)((in[27 + inPos] & 8388607) >>> 12 & 255);
         out[79 + outPos] = (byte)((in[27 + inPos] & 8388607) >>> 4 & 255);
         out[80 + outPos] = (byte)(((in[27 + inPos] & 8388607) << 4 | (in[28 + inPos] & 8388607) >>> 19) & 255);
         out[81 + outPos] = (byte)((in[28 + inPos] & 8388607) >>> 11 & 255);
         out[82 + outPos] = (byte)((in[28 + inPos] & 8388607) >>> 3 & 255);
         out[83 + outPos] = (byte)(((in[28 + inPos] & 8388607) << 5 | (in[29 + inPos] & 8388607) >>> 18) & 255);
         out[84 + outPos] = (byte)((in[29 + inPos] & 8388607) >>> 10 & 255);
         out[85 + outPos] = (byte)((in[29 + inPos] & 8388607) >>> 2 & 255);
         out[86 + outPos] = (byte)(((in[29 + inPos] & 8388607) << 6 | (in[30 + inPos] & 8388607) >>> 17) & 255);
         out[87 + outPos] = (byte)((in[30 + inPos] & 8388607) >>> 9 & 255);
         out[88 + outPos] = (byte)((in[30 + inPos] & 8388607) >>> 1 & 255);
         out[89 + outPos] = (byte)(((in[30 + inPos] & 8388607) << 7 | (in[31 + inPos] & 8388607) >>> 16) & 255);
         out[90 + outPos] = (byte)((in[31 + inPos] & 8388607) >>> 8 & 255);
         out[91 + outPos] = (byte)(in[31 + inPos] & 8388607 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 15 & 8388607 | in[1 + inPos] << 7 & 32767 | in[2 + inPos] >> 1 & 127;
         out[1 + outPos] = in[2 + inPos] << 22 & 8388607 | in[3 + inPos] << 14 & 4194303 | in[4 + inPos] << 6 & 16383 | in[5 + inPos] >> 2 & 63;
         out[2 + outPos] = in[5 + inPos] << 21 & 8388607 | in[6 + inPos] << 13 & 2097151 | in[7 + inPos] << 5 & 8191 | in[8 + inPos] >> 3 & 31;
         out[3 + outPos] = in[8 + inPos] << 20 & 8388607 | in[9 + inPos] << 12 & 1048575 | in[10 + inPos] << 4 & 4095 | in[11 + inPos] >> 4 & 15;
         out[4 + outPos] = in[11 + inPos] << 19 & 8388607 | in[12 + inPos] << 11 & 524287 | in[13 + inPos] << 3 & 2047 | in[14 + inPos] >> 5 & 7;
         out[5 + outPos] = in[14 + inPos] << 18 & 8388607 | in[15 + inPos] << 10 & 262143 | in[16 + inPos] << 2 & 1023 | in[17 + inPos] >> 6 & 3;
         out[6 + outPos] = in[17 + inPos] << 17 & 8388607 | in[18 + inPos] << 9 & 131071 | in[19 + inPos] << 1 & 511 | in[20 + inPos] >> 7 & 1;
         out[7 + outPos] = in[20 + inPos] << 16 & 8388607 | in[21 + inPos] << 8 & '\uffff' | in[22 + inPos] & 255;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 15 & 8388607 | in.get(1 + inPos) << 7 & 32767 | in.get(2 + inPos) >> 1 & 127;
         out[1 + outPos] = in.get(2 + inPos) << 22 & 8388607 | in.get(3 + inPos) << 14 & 4194303 | in.get(4 + inPos) << 6 & 16383 | in.get(5 + inPos) >> 2 & 63;
         out[2 + outPos] = in.get(5 + inPos) << 21 & 8388607 | in.get(6 + inPos) << 13 & 2097151 | in.get(7 + inPos) << 5 & 8191 | in.get(8 + inPos) >> 3 & 31;
         out[3 + outPos] = in.get(8 + inPos) << 20 & 8388607 | in.get(9 + inPos) << 12 & 1048575 | in.get(10 + inPos) << 4 & 4095 | in.get(11 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(11 + inPos) << 19 & 8388607 | in.get(12 + inPos) << 11 & 524287 | in.get(13 + inPos) << 3 & 2047 | in.get(14 + inPos) >> 5 & 7;
         out[5 + outPos] = in.get(14 + inPos) << 18 & 8388607 | in.get(15 + inPos) << 10 & 262143 | in.get(16 + inPos) << 2 & 1023 | in.get(17 + inPos) >> 6 & 3;
         out[6 + outPos] = in.get(17 + inPos) << 17 & 8388607 | in.get(18 + inPos) << 9 & 131071 | in.get(19 + inPos) << 1 & 511 | in.get(20 + inPos) >> 7 & 1;
         out[7 + outPos] = in.get(20 + inPos) << 16 & 8388607 | in.get(21 + inPos) << 8 & '\uffff' | in.get(22 + inPos) & 255;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 15 & 8388607 | in[1 + inPos] << 7 & 32767 | in[2 + inPos] >> 1 & 127;
         out[1 + outPos] = in[2 + inPos] << 22 & 8388607 | in[3 + inPos] << 14 & 4194303 | in[4 + inPos] << 6 & 16383 | in[5 + inPos] >> 2 & 63;
         out[2 + outPos] = in[5 + inPos] << 21 & 8388607 | in[6 + inPos] << 13 & 2097151 | in[7 + inPos] << 5 & 8191 | in[8 + inPos] >> 3 & 31;
         out[3 + outPos] = in[8 + inPos] << 20 & 8388607 | in[9 + inPos] << 12 & 1048575 | in[10 + inPos] << 4 & 4095 | in[11 + inPos] >> 4 & 15;
         out[4 + outPos] = in[11 + inPos] << 19 & 8388607 | in[12 + inPos] << 11 & 524287 | in[13 + inPos] << 3 & 2047 | in[14 + inPos] >> 5 & 7;
         out[5 + outPos] = in[14 + inPos] << 18 & 8388607 | in[15 + inPos] << 10 & 262143 | in[16 + inPos] << 2 & 1023 | in[17 + inPos] >> 6 & 3;
         out[6 + outPos] = in[17 + inPos] << 17 & 8388607 | in[18 + inPos] << 9 & 131071 | in[19 + inPos] << 1 & 511 | in[20 + inPos] >> 7 & 1;
         out[7 + outPos] = in[20 + inPos] << 16 & 8388607 | in[21 + inPos] << 8 & '\uffff' | in[22 + inPos] & 255;
         out[8 + outPos] = in[23 + inPos] << 15 & 8388607 | in[24 + inPos] << 7 & 32767 | in[25 + inPos] >> 1 & 127;
         out[9 + outPos] = in[25 + inPos] << 22 & 8388607 | in[26 + inPos] << 14 & 4194303 | in[27 + inPos] << 6 & 16383 | in[28 + inPos] >> 2 & 63;
         out[10 + outPos] = in[28 + inPos] << 21 & 8388607 | in[29 + inPos] << 13 & 2097151 | in[30 + inPos] << 5 & 8191 | in[31 + inPos] >> 3 & 31;
         out[11 + outPos] = in[31 + inPos] << 20 & 8388607 | in[32 + inPos] << 12 & 1048575 | in[33 + inPos] << 4 & 4095 | in[34 + inPos] >> 4 & 15;
         out[12 + outPos] = in[34 + inPos] << 19 & 8388607 | in[35 + inPos] << 11 & 524287 | in[36 + inPos] << 3 & 2047 | in[37 + inPos] >> 5 & 7;
         out[13 + outPos] = in[37 + inPos] << 18 & 8388607 | in[38 + inPos] << 10 & 262143 | in[39 + inPos] << 2 & 1023 | in[40 + inPos] >> 6 & 3;
         out[14 + outPos] = in[40 + inPos] << 17 & 8388607 | in[41 + inPos] << 9 & 131071 | in[42 + inPos] << 1 & 511 | in[43 + inPos] >> 7 & 1;
         out[15 + outPos] = in[43 + inPos] << 16 & 8388607 | in[44 + inPos] << 8 & '\uffff' | in[45 + inPos] & 255;
         out[16 + outPos] = in[46 + inPos] << 15 & 8388607 | in[47 + inPos] << 7 & 32767 | in[48 + inPos] >> 1 & 127;
         out[17 + outPos] = in[48 + inPos] << 22 & 8388607 | in[49 + inPos] << 14 & 4194303 | in[50 + inPos] << 6 & 16383 | in[51 + inPos] >> 2 & 63;
         out[18 + outPos] = in[51 + inPos] << 21 & 8388607 | in[52 + inPos] << 13 & 2097151 | in[53 + inPos] << 5 & 8191 | in[54 + inPos] >> 3 & 31;
         out[19 + outPos] = in[54 + inPos] << 20 & 8388607 | in[55 + inPos] << 12 & 1048575 | in[56 + inPos] << 4 & 4095 | in[57 + inPos] >> 4 & 15;
         out[20 + outPos] = in[57 + inPos] << 19 & 8388607 | in[58 + inPos] << 11 & 524287 | in[59 + inPos] << 3 & 2047 | in[60 + inPos] >> 5 & 7;
         out[21 + outPos] = in[60 + inPos] << 18 & 8388607 | in[61 + inPos] << 10 & 262143 | in[62 + inPos] << 2 & 1023 | in[63 + inPos] >> 6 & 3;
         out[22 + outPos] = in[63 + inPos] << 17 & 8388607 | in[64 + inPos] << 9 & 131071 | in[65 + inPos] << 1 & 511 | in[66 + inPos] >> 7 & 1;
         out[23 + outPos] = in[66 + inPos] << 16 & 8388607 | in[67 + inPos] << 8 & '\uffff' | in[68 + inPos] & 255;
         out[24 + outPos] = in[69 + inPos] << 15 & 8388607 | in[70 + inPos] << 7 & 32767 | in[71 + inPos] >> 1 & 127;
         out[25 + outPos] = in[71 + inPos] << 22 & 8388607 | in[72 + inPos] << 14 & 4194303 | in[73 + inPos] << 6 & 16383 | in[74 + inPos] >> 2 & 63;
         out[26 + outPos] = in[74 + inPos] << 21 & 8388607 | in[75 + inPos] << 13 & 2097151 | in[76 + inPos] << 5 & 8191 | in[77 + inPos] >> 3 & 31;
         out[27 + outPos] = in[77 + inPos] << 20 & 8388607 | in[78 + inPos] << 12 & 1048575 | in[79 + inPos] << 4 & 4095 | in[80 + inPos] >> 4 & 15;
         out[28 + outPos] = in[80 + inPos] << 19 & 8388607 | in[81 + inPos] << 11 & 524287 | in[82 + inPos] << 3 & 2047 | in[83 + inPos] >> 5 & 7;
         out[29 + outPos] = in[83 + inPos] << 18 & 8388607 | in[84 + inPos] << 10 & 262143 | in[85 + inPos] << 2 & 1023 | in[86 + inPos] >> 6 & 3;
         out[30 + outPos] = in[86 + inPos] << 17 & 8388607 | in[87 + inPos] << 9 & 131071 | in[88 + inPos] << 1 & 511 | in[89 + inPos] >> 7 & 1;
         out[31 + outPos] = in[89 + inPos] << 16 & 8388607 | in[90 + inPos] << 8 & '\uffff' | in[91 + inPos] & 255;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 15 & 8388607 | in.get(1 + inPos) << 7 & 32767 | in.get(2 + inPos) >> 1 & 127;
         out[1 + outPos] = in.get(2 + inPos) << 22 & 8388607 | in.get(3 + inPos) << 14 & 4194303 | in.get(4 + inPos) << 6 & 16383 | in.get(5 + inPos) >> 2 & 63;
         out[2 + outPos] = in.get(5 + inPos) << 21 & 8388607 | in.get(6 + inPos) << 13 & 2097151 | in.get(7 + inPos) << 5 & 8191 | in.get(8 + inPos) >> 3 & 31;
         out[3 + outPos] = in.get(8 + inPos) << 20 & 8388607 | in.get(9 + inPos) << 12 & 1048575 | in.get(10 + inPos) << 4 & 4095 | in.get(11 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(11 + inPos) << 19 & 8388607 | in.get(12 + inPos) << 11 & 524287 | in.get(13 + inPos) << 3 & 2047 | in.get(14 + inPos) >> 5 & 7;
         out[5 + outPos] = in.get(14 + inPos) << 18 & 8388607 | in.get(15 + inPos) << 10 & 262143 | in.get(16 + inPos) << 2 & 1023 | in.get(17 + inPos) >> 6 & 3;
         out[6 + outPos] = in.get(17 + inPos) << 17 & 8388607 | in.get(18 + inPos) << 9 & 131071 | in.get(19 + inPos) << 1 & 511 | in.get(20 + inPos) >> 7 & 1;
         out[7 + outPos] = in.get(20 + inPos) << 16 & 8388607 | in.get(21 + inPos) << 8 & '\uffff' | in.get(22 + inPos) & 255;
         out[8 + outPos] = in.get(23 + inPos) << 15 & 8388607 | in.get(24 + inPos) << 7 & 32767 | in.get(25 + inPos) >> 1 & 127;
         out[9 + outPos] = in.get(25 + inPos) << 22 & 8388607 | in.get(26 + inPos) << 14 & 4194303 | in.get(27 + inPos) << 6 & 16383 | in.get(28 + inPos) >> 2 & 63;
         out[10 + outPos] = in.get(28 + inPos) << 21 & 8388607 | in.get(29 + inPos) << 13 & 2097151 | in.get(30 + inPos) << 5 & 8191 | in.get(31 + inPos) >> 3 & 31;
         out[11 + outPos] = in.get(31 + inPos) << 20 & 8388607 | in.get(32 + inPos) << 12 & 1048575 | in.get(33 + inPos) << 4 & 4095 | in.get(34 + inPos) >> 4 & 15;
         out[12 + outPos] = in.get(34 + inPos) << 19 & 8388607 | in.get(35 + inPos) << 11 & 524287 | in.get(36 + inPos) << 3 & 2047 | in.get(37 + inPos) >> 5 & 7;
         out[13 + outPos] = in.get(37 + inPos) << 18 & 8388607 | in.get(38 + inPos) << 10 & 262143 | in.get(39 + inPos) << 2 & 1023 | in.get(40 + inPos) >> 6 & 3;
         out[14 + outPos] = in.get(40 + inPos) << 17 & 8388607 | in.get(41 + inPos) << 9 & 131071 | in.get(42 + inPos) << 1 & 511 | in.get(43 + inPos) >> 7 & 1;
         out[15 + outPos] = in.get(43 + inPos) << 16 & 8388607 | in.get(44 + inPos) << 8 & '\uffff' | in.get(45 + inPos) & 255;
         out[16 + outPos] = in.get(46 + inPos) << 15 & 8388607 | in.get(47 + inPos) << 7 & 32767 | in.get(48 + inPos) >> 1 & 127;
         out[17 + outPos] = in.get(48 + inPos) << 22 & 8388607 | in.get(49 + inPos) << 14 & 4194303 | in.get(50 + inPos) << 6 & 16383 | in.get(51 + inPos) >> 2 & 63;
         out[18 + outPos] = in.get(51 + inPos) << 21 & 8388607 | in.get(52 + inPos) << 13 & 2097151 | in.get(53 + inPos) << 5 & 8191 | in.get(54 + inPos) >> 3 & 31;
         out[19 + outPos] = in.get(54 + inPos) << 20 & 8388607 | in.get(55 + inPos) << 12 & 1048575 | in.get(56 + inPos) << 4 & 4095 | in.get(57 + inPos) >> 4 & 15;
         out[20 + outPos] = in.get(57 + inPos) << 19 & 8388607 | in.get(58 + inPos) << 11 & 524287 | in.get(59 + inPos) << 3 & 2047 | in.get(60 + inPos) >> 5 & 7;
         out[21 + outPos] = in.get(60 + inPos) << 18 & 8388607 | in.get(61 + inPos) << 10 & 262143 | in.get(62 + inPos) << 2 & 1023 | in.get(63 + inPos) >> 6 & 3;
         out[22 + outPos] = in.get(63 + inPos) << 17 & 8388607 | in.get(64 + inPos) << 9 & 131071 | in.get(65 + inPos) << 1 & 511 | in.get(66 + inPos) >> 7 & 1;
         out[23 + outPos] = in.get(66 + inPos) << 16 & 8388607 | in.get(67 + inPos) << 8 & '\uffff' | in.get(68 + inPos) & 255;
         out[24 + outPos] = in.get(69 + inPos) << 15 & 8388607 | in.get(70 + inPos) << 7 & 32767 | in.get(71 + inPos) >> 1 & 127;
         out[25 + outPos] = in.get(71 + inPos) << 22 & 8388607 | in.get(72 + inPos) << 14 & 4194303 | in.get(73 + inPos) << 6 & 16383 | in.get(74 + inPos) >> 2 & 63;
         out[26 + outPos] = in.get(74 + inPos) << 21 & 8388607 | in.get(75 + inPos) << 13 & 2097151 | in.get(76 + inPos) << 5 & 8191 | in.get(77 + inPos) >> 3 & 31;
         out[27 + outPos] = in.get(77 + inPos) << 20 & 8388607 | in.get(78 + inPos) << 12 & 1048575 | in.get(79 + inPos) << 4 & 4095 | in.get(80 + inPos) >> 4 & 15;
         out[28 + outPos] = in.get(80 + inPos) << 19 & 8388607 | in.get(81 + inPos) << 11 & 524287 | in.get(82 + inPos) << 3 & 2047 | in.get(83 + inPos) >> 5 & 7;
         out[29 + outPos] = in.get(83 + inPos) << 18 & 8388607 | in.get(84 + inPos) << 10 & 262143 | in.get(85 + inPos) << 2 & 1023 | in.get(86 + inPos) >> 6 & 3;
         out[30 + outPos] = in.get(86 + inPos) << 17 & 8388607 | in.get(87 + inPos) << 9 & 131071 | in.get(88 + inPos) << 1 & 511 | in.get(89 + inPos) >> 7 & 1;
         out[31 + outPos] = in.get(89 + inPos) << 16 & 8388607 | in.get(90 + inPos) << 8 & '\uffff' | in.get(91 + inPos) & 255;
      }
   }

   private static final class Packer24 extends BytePacker {
      private Packer24() {
         super(24);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 16777215) >>> 16 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 16777215) >>> 8 & 255);
         out[2 + outPos] = (byte)(in[0 + inPos] & 16777215 & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 16777215) >>> 16 & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 16777215) >>> 8 & 255);
         out[5 + outPos] = (byte)(in[1 + inPos] & 16777215 & 255);
         out[6 + outPos] = (byte)((in[2 + inPos] & 16777215) >>> 16 & 255);
         out[7 + outPos] = (byte)((in[2 + inPos] & 16777215) >>> 8 & 255);
         out[8 + outPos] = (byte)(in[2 + inPos] & 16777215 & 255);
         out[9 + outPos] = (byte)((in[3 + inPos] & 16777215) >>> 16 & 255);
         out[10 + outPos] = (byte)((in[3 + inPos] & 16777215) >>> 8 & 255);
         out[11 + outPos] = (byte)(in[3 + inPos] & 16777215 & 255);
         out[12 + outPos] = (byte)((in[4 + inPos] & 16777215) >>> 16 & 255);
         out[13 + outPos] = (byte)((in[4 + inPos] & 16777215) >>> 8 & 255);
         out[14 + outPos] = (byte)(in[4 + inPos] & 16777215 & 255);
         out[15 + outPos] = (byte)((in[5 + inPos] & 16777215) >>> 16 & 255);
         out[16 + outPos] = (byte)((in[5 + inPos] & 16777215) >>> 8 & 255);
         out[17 + outPos] = (byte)(in[5 + inPos] & 16777215 & 255);
         out[18 + outPos] = (byte)((in[6 + inPos] & 16777215) >>> 16 & 255);
         out[19 + outPos] = (byte)((in[6 + inPos] & 16777215) >>> 8 & 255);
         out[20 + outPos] = (byte)(in[6 + inPos] & 16777215 & 255);
         out[21 + outPos] = (byte)((in[7 + inPos] & 16777215) >>> 16 & 255);
         out[22 + outPos] = (byte)((in[7 + inPos] & 16777215) >>> 8 & 255);
         out[23 + outPos] = (byte)(in[7 + inPos] & 16777215 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 16777215) >>> 16 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 16777215) >>> 8 & 255);
         out[2 + outPos] = (byte)(in[0 + inPos] & 16777215 & 255);
         out[3 + outPos] = (byte)((in[1 + inPos] & 16777215) >>> 16 & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 16777215) >>> 8 & 255);
         out[5 + outPos] = (byte)(in[1 + inPos] & 16777215 & 255);
         out[6 + outPos] = (byte)((in[2 + inPos] & 16777215) >>> 16 & 255);
         out[7 + outPos] = (byte)((in[2 + inPos] & 16777215) >>> 8 & 255);
         out[8 + outPos] = (byte)(in[2 + inPos] & 16777215 & 255);
         out[9 + outPos] = (byte)((in[3 + inPos] & 16777215) >>> 16 & 255);
         out[10 + outPos] = (byte)((in[3 + inPos] & 16777215) >>> 8 & 255);
         out[11 + outPos] = (byte)(in[3 + inPos] & 16777215 & 255);
         out[12 + outPos] = (byte)((in[4 + inPos] & 16777215) >>> 16 & 255);
         out[13 + outPos] = (byte)((in[4 + inPos] & 16777215) >>> 8 & 255);
         out[14 + outPos] = (byte)(in[4 + inPos] & 16777215 & 255);
         out[15 + outPos] = (byte)((in[5 + inPos] & 16777215) >>> 16 & 255);
         out[16 + outPos] = (byte)((in[5 + inPos] & 16777215) >>> 8 & 255);
         out[17 + outPos] = (byte)(in[5 + inPos] & 16777215 & 255);
         out[18 + outPos] = (byte)((in[6 + inPos] & 16777215) >>> 16 & 255);
         out[19 + outPos] = (byte)((in[6 + inPos] & 16777215) >>> 8 & 255);
         out[20 + outPos] = (byte)(in[6 + inPos] & 16777215 & 255);
         out[21 + outPos] = (byte)((in[7 + inPos] & 16777215) >>> 16 & 255);
         out[22 + outPos] = (byte)((in[7 + inPos] & 16777215) >>> 8 & 255);
         out[23 + outPos] = (byte)(in[7 + inPos] & 16777215 & 255);
         out[24 + outPos] = (byte)((in[8 + inPos] & 16777215) >>> 16 & 255);
         out[25 + outPos] = (byte)((in[8 + inPos] & 16777215) >>> 8 & 255);
         out[26 + outPos] = (byte)(in[8 + inPos] & 16777215 & 255);
         out[27 + outPos] = (byte)((in[9 + inPos] & 16777215) >>> 16 & 255);
         out[28 + outPos] = (byte)((in[9 + inPos] & 16777215) >>> 8 & 255);
         out[29 + outPos] = (byte)(in[9 + inPos] & 16777215 & 255);
         out[30 + outPos] = (byte)((in[10 + inPos] & 16777215) >>> 16 & 255);
         out[31 + outPos] = (byte)((in[10 + inPos] & 16777215) >>> 8 & 255);
         out[32 + outPos] = (byte)(in[10 + inPos] & 16777215 & 255);
         out[33 + outPos] = (byte)((in[11 + inPos] & 16777215) >>> 16 & 255);
         out[34 + outPos] = (byte)((in[11 + inPos] & 16777215) >>> 8 & 255);
         out[35 + outPos] = (byte)(in[11 + inPos] & 16777215 & 255);
         out[36 + outPos] = (byte)((in[12 + inPos] & 16777215) >>> 16 & 255);
         out[37 + outPos] = (byte)((in[12 + inPos] & 16777215) >>> 8 & 255);
         out[38 + outPos] = (byte)(in[12 + inPos] & 16777215 & 255);
         out[39 + outPos] = (byte)((in[13 + inPos] & 16777215) >>> 16 & 255);
         out[40 + outPos] = (byte)((in[13 + inPos] & 16777215) >>> 8 & 255);
         out[41 + outPos] = (byte)(in[13 + inPos] & 16777215 & 255);
         out[42 + outPos] = (byte)((in[14 + inPos] & 16777215) >>> 16 & 255);
         out[43 + outPos] = (byte)((in[14 + inPos] & 16777215) >>> 8 & 255);
         out[44 + outPos] = (byte)(in[14 + inPos] & 16777215 & 255);
         out[45 + outPos] = (byte)((in[15 + inPos] & 16777215) >>> 16 & 255);
         out[46 + outPos] = (byte)((in[15 + inPos] & 16777215) >>> 8 & 255);
         out[47 + outPos] = (byte)(in[15 + inPos] & 16777215 & 255);
         out[48 + outPos] = (byte)((in[16 + inPos] & 16777215) >>> 16 & 255);
         out[49 + outPos] = (byte)((in[16 + inPos] & 16777215) >>> 8 & 255);
         out[50 + outPos] = (byte)(in[16 + inPos] & 16777215 & 255);
         out[51 + outPos] = (byte)((in[17 + inPos] & 16777215) >>> 16 & 255);
         out[52 + outPos] = (byte)((in[17 + inPos] & 16777215) >>> 8 & 255);
         out[53 + outPos] = (byte)(in[17 + inPos] & 16777215 & 255);
         out[54 + outPos] = (byte)((in[18 + inPos] & 16777215) >>> 16 & 255);
         out[55 + outPos] = (byte)((in[18 + inPos] & 16777215) >>> 8 & 255);
         out[56 + outPos] = (byte)(in[18 + inPos] & 16777215 & 255);
         out[57 + outPos] = (byte)((in[19 + inPos] & 16777215) >>> 16 & 255);
         out[58 + outPos] = (byte)((in[19 + inPos] & 16777215) >>> 8 & 255);
         out[59 + outPos] = (byte)(in[19 + inPos] & 16777215 & 255);
         out[60 + outPos] = (byte)((in[20 + inPos] & 16777215) >>> 16 & 255);
         out[61 + outPos] = (byte)((in[20 + inPos] & 16777215) >>> 8 & 255);
         out[62 + outPos] = (byte)(in[20 + inPos] & 16777215 & 255);
         out[63 + outPos] = (byte)((in[21 + inPos] & 16777215) >>> 16 & 255);
         out[64 + outPos] = (byte)((in[21 + inPos] & 16777215) >>> 8 & 255);
         out[65 + outPos] = (byte)(in[21 + inPos] & 16777215 & 255);
         out[66 + outPos] = (byte)((in[22 + inPos] & 16777215) >>> 16 & 255);
         out[67 + outPos] = (byte)((in[22 + inPos] & 16777215) >>> 8 & 255);
         out[68 + outPos] = (byte)(in[22 + inPos] & 16777215 & 255);
         out[69 + outPos] = (byte)((in[23 + inPos] & 16777215) >>> 16 & 255);
         out[70 + outPos] = (byte)((in[23 + inPos] & 16777215) >>> 8 & 255);
         out[71 + outPos] = (byte)(in[23 + inPos] & 16777215 & 255);
         out[72 + outPos] = (byte)((in[24 + inPos] & 16777215) >>> 16 & 255);
         out[73 + outPos] = (byte)((in[24 + inPos] & 16777215) >>> 8 & 255);
         out[74 + outPos] = (byte)(in[24 + inPos] & 16777215 & 255);
         out[75 + outPos] = (byte)((in[25 + inPos] & 16777215) >>> 16 & 255);
         out[76 + outPos] = (byte)((in[25 + inPos] & 16777215) >>> 8 & 255);
         out[77 + outPos] = (byte)(in[25 + inPos] & 16777215 & 255);
         out[78 + outPos] = (byte)((in[26 + inPos] & 16777215) >>> 16 & 255);
         out[79 + outPos] = (byte)((in[26 + inPos] & 16777215) >>> 8 & 255);
         out[80 + outPos] = (byte)(in[26 + inPos] & 16777215 & 255);
         out[81 + outPos] = (byte)((in[27 + inPos] & 16777215) >>> 16 & 255);
         out[82 + outPos] = (byte)((in[27 + inPos] & 16777215) >>> 8 & 255);
         out[83 + outPos] = (byte)(in[27 + inPos] & 16777215 & 255);
         out[84 + outPos] = (byte)((in[28 + inPos] & 16777215) >>> 16 & 255);
         out[85 + outPos] = (byte)((in[28 + inPos] & 16777215) >>> 8 & 255);
         out[86 + outPos] = (byte)(in[28 + inPos] & 16777215 & 255);
         out[87 + outPos] = (byte)((in[29 + inPos] & 16777215) >>> 16 & 255);
         out[88 + outPos] = (byte)((in[29 + inPos] & 16777215) >>> 8 & 255);
         out[89 + outPos] = (byte)(in[29 + inPos] & 16777215 & 255);
         out[90 + outPos] = (byte)((in[30 + inPos] & 16777215) >>> 16 & 255);
         out[91 + outPos] = (byte)((in[30 + inPos] & 16777215) >>> 8 & 255);
         out[92 + outPos] = (byte)(in[30 + inPos] & 16777215 & 255);
         out[93 + outPos] = (byte)((in[31 + inPos] & 16777215) >>> 16 & 255);
         out[94 + outPos] = (byte)((in[31 + inPos] & 16777215) >>> 8 & 255);
         out[95 + outPos] = (byte)(in[31 + inPos] & 16777215 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 16 & 16777215 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] & 255;
         out[1 + outPos] = in[3 + inPos] << 16 & 16777215 | in[4 + inPos] << 8 & '\uffff' | in[5 + inPos] & 255;
         out[2 + outPos] = in[6 + inPos] << 16 & 16777215 | in[7 + inPos] << 8 & '\uffff' | in[8 + inPos] & 255;
         out[3 + outPos] = in[9 + inPos] << 16 & 16777215 | in[10 + inPos] << 8 & '\uffff' | in[11 + inPos] & 255;
         out[4 + outPos] = in[12 + inPos] << 16 & 16777215 | in[13 + inPos] << 8 & '\uffff' | in[14 + inPos] & 255;
         out[5 + outPos] = in[15 + inPos] << 16 & 16777215 | in[16 + inPos] << 8 & '\uffff' | in[17 + inPos] & 255;
         out[6 + outPos] = in[18 + inPos] << 16 & 16777215 | in[19 + inPos] << 8 & '\uffff' | in[20 + inPos] & 255;
         out[7 + outPos] = in[21 + inPos] << 16 & 16777215 | in[22 + inPos] << 8 & '\uffff' | in[23 + inPos] & 255;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 16 & 16777215 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) & 255;
         out[1 + outPos] = in.get(3 + inPos) << 16 & 16777215 | in.get(4 + inPos) << 8 & '\uffff' | in.get(5 + inPos) & 255;
         out[2 + outPos] = in.get(6 + inPos) << 16 & 16777215 | in.get(7 + inPos) << 8 & '\uffff' | in.get(8 + inPos) & 255;
         out[3 + outPos] = in.get(9 + inPos) << 16 & 16777215 | in.get(10 + inPos) << 8 & '\uffff' | in.get(11 + inPos) & 255;
         out[4 + outPos] = in.get(12 + inPos) << 16 & 16777215 | in.get(13 + inPos) << 8 & '\uffff' | in.get(14 + inPos) & 255;
         out[5 + outPos] = in.get(15 + inPos) << 16 & 16777215 | in.get(16 + inPos) << 8 & '\uffff' | in.get(17 + inPos) & 255;
         out[6 + outPos] = in.get(18 + inPos) << 16 & 16777215 | in.get(19 + inPos) << 8 & '\uffff' | in.get(20 + inPos) & 255;
         out[7 + outPos] = in.get(21 + inPos) << 16 & 16777215 | in.get(22 + inPos) << 8 & '\uffff' | in.get(23 + inPos) & 255;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 16 & 16777215 | in[1 + inPos] << 8 & '\uffff' | in[2 + inPos] & 255;
         out[1 + outPos] = in[3 + inPos] << 16 & 16777215 | in[4 + inPos] << 8 & '\uffff' | in[5 + inPos] & 255;
         out[2 + outPos] = in[6 + inPos] << 16 & 16777215 | in[7 + inPos] << 8 & '\uffff' | in[8 + inPos] & 255;
         out[3 + outPos] = in[9 + inPos] << 16 & 16777215 | in[10 + inPos] << 8 & '\uffff' | in[11 + inPos] & 255;
         out[4 + outPos] = in[12 + inPos] << 16 & 16777215 | in[13 + inPos] << 8 & '\uffff' | in[14 + inPos] & 255;
         out[5 + outPos] = in[15 + inPos] << 16 & 16777215 | in[16 + inPos] << 8 & '\uffff' | in[17 + inPos] & 255;
         out[6 + outPos] = in[18 + inPos] << 16 & 16777215 | in[19 + inPos] << 8 & '\uffff' | in[20 + inPos] & 255;
         out[7 + outPos] = in[21 + inPos] << 16 & 16777215 | in[22 + inPos] << 8 & '\uffff' | in[23 + inPos] & 255;
         out[8 + outPos] = in[24 + inPos] << 16 & 16777215 | in[25 + inPos] << 8 & '\uffff' | in[26 + inPos] & 255;
         out[9 + outPos] = in[27 + inPos] << 16 & 16777215 | in[28 + inPos] << 8 & '\uffff' | in[29 + inPos] & 255;
         out[10 + outPos] = in[30 + inPos] << 16 & 16777215 | in[31 + inPos] << 8 & '\uffff' | in[32 + inPos] & 255;
         out[11 + outPos] = in[33 + inPos] << 16 & 16777215 | in[34 + inPos] << 8 & '\uffff' | in[35 + inPos] & 255;
         out[12 + outPos] = in[36 + inPos] << 16 & 16777215 | in[37 + inPos] << 8 & '\uffff' | in[38 + inPos] & 255;
         out[13 + outPos] = in[39 + inPos] << 16 & 16777215 | in[40 + inPos] << 8 & '\uffff' | in[41 + inPos] & 255;
         out[14 + outPos] = in[42 + inPos] << 16 & 16777215 | in[43 + inPos] << 8 & '\uffff' | in[44 + inPos] & 255;
         out[15 + outPos] = in[45 + inPos] << 16 & 16777215 | in[46 + inPos] << 8 & '\uffff' | in[47 + inPos] & 255;
         out[16 + outPos] = in[48 + inPos] << 16 & 16777215 | in[49 + inPos] << 8 & '\uffff' | in[50 + inPos] & 255;
         out[17 + outPos] = in[51 + inPos] << 16 & 16777215 | in[52 + inPos] << 8 & '\uffff' | in[53 + inPos] & 255;
         out[18 + outPos] = in[54 + inPos] << 16 & 16777215 | in[55 + inPos] << 8 & '\uffff' | in[56 + inPos] & 255;
         out[19 + outPos] = in[57 + inPos] << 16 & 16777215 | in[58 + inPos] << 8 & '\uffff' | in[59 + inPos] & 255;
         out[20 + outPos] = in[60 + inPos] << 16 & 16777215 | in[61 + inPos] << 8 & '\uffff' | in[62 + inPos] & 255;
         out[21 + outPos] = in[63 + inPos] << 16 & 16777215 | in[64 + inPos] << 8 & '\uffff' | in[65 + inPos] & 255;
         out[22 + outPos] = in[66 + inPos] << 16 & 16777215 | in[67 + inPos] << 8 & '\uffff' | in[68 + inPos] & 255;
         out[23 + outPos] = in[69 + inPos] << 16 & 16777215 | in[70 + inPos] << 8 & '\uffff' | in[71 + inPos] & 255;
         out[24 + outPos] = in[72 + inPos] << 16 & 16777215 | in[73 + inPos] << 8 & '\uffff' | in[74 + inPos] & 255;
         out[25 + outPos] = in[75 + inPos] << 16 & 16777215 | in[76 + inPos] << 8 & '\uffff' | in[77 + inPos] & 255;
         out[26 + outPos] = in[78 + inPos] << 16 & 16777215 | in[79 + inPos] << 8 & '\uffff' | in[80 + inPos] & 255;
         out[27 + outPos] = in[81 + inPos] << 16 & 16777215 | in[82 + inPos] << 8 & '\uffff' | in[83 + inPos] & 255;
         out[28 + outPos] = in[84 + inPos] << 16 & 16777215 | in[85 + inPos] << 8 & '\uffff' | in[86 + inPos] & 255;
         out[29 + outPos] = in[87 + inPos] << 16 & 16777215 | in[88 + inPos] << 8 & '\uffff' | in[89 + inPos] & 255;
         out[30 + outPos] = in[90 + inPos] << 16 & 16777215 | in[91 + inPos] << 8 & '\uffff' | in[92 + inPos] & 255;
         out[31 + outPos] = in[93 + inPos] << 16 & 16777215 | in[94 + inPos] << 8 & '\uffff' | in[95 + inPos] & 255;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 16 & 16777215 | in.get(1 + inPos) << 8 & '\uffff' | in.get(2 + inPos) & 255;
         out[1 + outPos] = in.get(3 + inPos) << 16 & 16777215 | in.get(4 + inPos) << 8 & '\uffff' | in.get(5 + inPos) & 255;
         out[2 + outPos] = in.get(6 + inPos) << 16 & 16777215 | in.get(7 + inPos) << 8 & '\uffff' | in.get(8 + inPos) & 255;
         out[3 + outPos] = in.get(9 + inPos) << 16 & 16777215 | in.get(10 + inPos) << 8 & '\uffff' | in.get(11 + inPos) & 255;
         out[4 + outPos] = in.get(12 + inPos) << 16 & 16777215 | in.get(13 + inPos) << 8 & '\uffff' | in.get(14 + inPos) & 255;
         out[5 + outPos] = in.get(15 + inPos) << 16 & 16777215 | in.get(16 + inPos) << 8 & '\uffff' | in.get(17 + inPos) & 255;
         out[6 + outPos] = in.get(18 + inPos) << 16 & 16777215 | in.get(19 + inPos) << 8 & '\uffff' | in.get(20 + inPos) & 255;
         out[7 + outPos] = in.get(21 + inPos) << 16 & 16777215 | in.get(22 + inPos) << 8 & '\uffff' | in.get(23 + inPos) & 255;
         out[8 + outPos] = in.get(24 + inPos) << 16 & 16777215 | in.get(25 + inPos) << 8 & '\uffff' | in.get(26 + inPos) & 255;
         out[9 + outPos] = in.get(27 + inPos) << 16 & 16777215 | in.get(28 + inPos) << 8 & '\uffff' | in.get(29 + inPos) & 255;
         out[10 + outPos] = in.get(30 + inPos) << 16 & 16777215 | in.get(31 + inPos) << 8 & '\uffff' | in.get(32 + inPos) & 255;
         out[11 + outPos] = in.get(33 + inPos) << 16 & 16777215 | in.get(34 + inPos) << 8 & '\uffff' | in.get(35 + inPos) & 255;
         out[12 + outPos] = in.get(36 + inPos) << 16 & 16777215 | in.get(37 + inPos) << 8 & '\uffff' | in.get(38 + inPos) & 255;
         out[13 + outPos] = in.get(39 + inPos) << 16 & 16777215 | in.get(40 + inPos) << 8 & '\uffff' | in.get(41 + inPos) & 255;
         out[14 + outPos] = in.get(42 + inPos) << 16 & 16777215 | in.get(43 + inPos) << 8 & '\uffff' | in.get(44 + inPos) & 255;
         out[15 + outPos] = in.get(45 + inPos) << 16 & 16777215 | in.get(46 + inPos) << 8 & '\uffff' | in.get(47 + inPos) & 255;
         out[16 + outPos] = in.get(48 + inPos) << 16 & 16777215 | in.get(49 + inPos) << 8 & '\uffff' | in.get(50 + inPos) & 255;
         out[17 + outPos] = in.get(51 + inPos) << 16 & 16777215 | in.get(52 + inPos) << 8 & '\uffff' | in.get(53 + inPos) & 255;
         out[18 + outPos] = in.get(54 + inPos) << 16 & 16777215 | in.get(55 + inPos) << 8 & '\uffff' | in.get(56 + inPos) & 255;
         out[19 + outPos] = in.get(57 + inPos) << 16 & 16777215 | in.get(58 + inPos) << 8 & '\uffff' | in.get(59 + inPos) & 255;
         out[20 + outPos] = in.get(60 + inPos) << 16 & 16777215 | in.get(61 + inPos) << 8 & '\uffff' | in.get(62 + inPos) & 255;
         out[21 + outPos] = in.get(63 + inPos) << 16 & 16777215 | in.get(64 + inPos) << 8 & '\uffff' | in.get(65 + inPos) & 255;
         out[22 + outPos] = in.get(66 + inPos) << 16 & 16777215 | in.get(67 + inPos) << 8 & '\uffff' | in.get(68 + inPos) & 255;
         out[23 + outPos] = in.get(69 + inPos) << 16 & 16777215 | in.get(70 + inPos) << 8 & '\uffff' | in.get(71 + inPos) & 255;
         out[24 + outPos] = in.get(72 + inPos) << 16 & 16777215 | in.get(73 + inPos) << 8 & '\uffff' | in.get(74 + inPos) & 255;
         out[25 + outPos] = in.get(75 + inPos) << 16 & 16777215 | in.get(76 + inPos) << 8 & '\uffff' | in.get(77 + inPos) & 255;
         out[26 + outPos] = in.get(78 + inPos) << 16 & 16777215 | in.get(79 + inPos) << 8 & '\uffff' | in.get(80 + inPos) & 255;
         out[27 + outPos] = in.get(81 + inPos) << 16 & 16777215 | in.get(82 + inPos) << 8 & '\uffff' | in.get(83 + inPos) & 255;
         out[28 + outPos] = in.get(84 + inPos) << 16 & 16777215 | in.get(85 + inPos) << 8 & '\uffff' | in.get(86 + inPos) & 255;
         out[29 + outPos] = in.get(87 + inPos) << 16 & 16777215 | in.get(88 + inPos) << 8 & '\uffff' | in.get(89 + inPos) & 255;
         out[30 + outPos] = in.get(90 + inPos) << 16 & 16777215 | in.get(91 + inPos) << 8 & '\uffff' | in.get(92 + inPos) & 255;
         out[31 + outPos] = in.get(93 + inPos) << 16 & 16777215 | in.get(94 + inPos) << 8 & '\uffff' | in.get(95 + inPos) & 255;
      }
   }

   private static final class Packer25 extends BytePacker {
      private Packer25() {
         super(25);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 33554431) >>> 17 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 33554431) >>> 9 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & 33554431) >>> 1 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & 33554431) << 7 | (in[1 + inPos] & 33554431) >>> 18) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 33554431) >>> 10 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & 33554431) >>> 2 & 255);
         out[6 + outPos] = (byte)(((in[1 + inPos] & 33554431) << 6 | (in[2 + inPos] & 33554431) >>> 19) & 255);
         out[7 + outPos] = (byte)((in[2 + inPos] & 33554431) >>> 11 & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & 33554431) >>> 3 & 255);
         out[9 + outPos] = (byte)(((in[2 + inPos] & 33554431) << 5 | (in[3 + inPos] & 33554431) >>> 20) & 255);
         out[10 + outPos] = (byte)((in[3 + inPos] & 33554431) >>> 12 & 255);
         out[11 + outPos] = (byte)((in[3 + inPos] & 33554431) >>> 4 & 255);
         out[12 + outPos] = (byte)(((in[3 + inPos] & 33554431) << 4 | (in[4 + inPos] & 33554431) >>> 21) & 255);
         out[13 + outPos] = (byte)((in[4 + inPos] & 33554431) >>> 13 & 255);
         out[14 + outPos] = (byte)((in[4 + inPos] & 33554431) >>> 5 & 255);
         out[15 + outPos] = (byte)(((in[4 + inPos] & 33554431) << 3 | (in[5 + inPos] & 33554431) >>> 22) & 255);
         out[16 + outPos] = (byte)((in[5 + inPos] & 33554431) >>> 14 & 255);
         out[17 + outPos] = (byte)((in[5 + inPos] & 33554431) >>> 6 & 255);
         out[18 + outPos] = (byte)(((in[5 + inPos] & 33554431) << 2 | (in[6 + inPos] & 33554431) >>> 23) & 255);
         out[19 + outPos] = (byte)((in[6 + inPos] & 33554431) >>> 15 & 255);
         out[20 + outPos] = (byte)((in[6 + inPos] & 33554431) >>> 7 & 255);
         out[21 + outPos] = (byte)(((in[6 + inPos] & 33554431) << 1 | (in[7 + inPos] & 33554431) >>> 24) & 255);
         out[22 + outPos] = (byte)((in[7 + inPos] & 33554431) >>> 16 & 255);
         out[23 + outPos] = (byte)((in[7 + inPos] & 33554431) >>> 8 & 255);
         out[24 + outPos] = (byte)(in[7 + inPos] & 33554431 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 33554431) >>> 17 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 33554431) >>> 9 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & 33554431) >>> 1 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & 33554431) << 7 | (in[1 + inPos] & 33554431) >>> 18) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 33554431) >>> 10 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & 33554431) >>> 2 & 255);
         out[6 + outPos] = (byte)(((in[1 + inPos] & 33554431) << 6 | (in[2 + inPos] & 33554431) >>> 19) & 255);
         out[7 + outPos] = (byte)((in[2 + inPos] & 33554431) >>> 11 & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & 33554431) >>> 3 & 255);
         out[9 + outPos] = (byte)(((in[2 + inPos] & 33554431) << 5 | (in[3 + inPos] & 33554431) >>> 20) & 255);
         out[10 + outPos] = (byte)((in[3 + inPos] & 33554431) >>> 12 & 255);
         out[11 + outPos] = (byte)((in[3 + inPos] & 33554431) >>> 4 & 255);
         out[12 + outPos] = (byte)(((in[3 + inPos] & 33554431) << 4 | (in[4 + inPos] & 33554431) >>> 21) & 255);
         out[13 + outPos] = (byte)((in[4 + inPos] & 33554431) >>> 13 & 255);
         out[14 + outPos] = (byte)((in[4 + inPos] & 33554431) >>> 5 & 255);
         out[15 + outPos] = (byte)(((in[4 + inPos] & 33554431) << 3 | (in[5 + inPos] & 33554431) >>> 22) & 255);
         out[16 + outPos] = (byte)((in[5 + inPos] & 33554431) >>> 14 & 255);
         out[17 + outPos] = (byte)((in[5 + inPos] & 33554431) >>> 6 & 255);
         out[18 + outPos] = (byte)(((in[5 + inPos] & 33554431) << 2 | (in[6 + inPos] & 33554431) >>> 23) & 255);
         out[19 + outPos] = (byte)((in[6 + inPos] & 33554431) >>> 15 & 255);
         out[20 + outPos] = (byte)((in[6 + inPos] & 33554431) >>> 7 & 255);
         out[21 + outPos] = (byte)(((in[6 + inPos] & 33554431) << 1 | (in[7 + inPos] & 33554431) >>> 24) & 255);
         out[22 + outPos] = (byte)((in[7 + inPos] & 33554431) >>> 16 & 255);
         out[23 + outPos] = (byte)((in[7 + inPos] & 33554431) >>> 8 & 255);
         out[24 + outPos] = (byte)(in[7 + inPos] & 33554431 & 255);
         out[25 + outPos] = (byte)((in[8 + inPos] & 33554431) >>> 17 & 255);
         out[26 + outPos] = (byte)((in[8 + inPos] & 33554431) >>> 9 & 255);
         out[27 + outPos] = (byte)((in[8 + inPos] & 33554431) >>> 1 & 255);
         out[28 + outPos] = (byte)(((in[8 + inPos] & 33554431) << 7 | (in[9 + inPos] & 33554431) >>> 18) & 255);
         out[29 + outPos] = (byte)((in[9 + inPos] & 33554431) >>> 10 & 255);
         out[30 + outPos] = (byte)((in[9 + inPos] & 33554431) >>> 2 & 255);
         out[31 + outPos] = (byte)(((in[9 + inPos] & 33554431) << 6 | (in[10 + inPos] & 33554431) >>> 19) & 255);
         out[32 + outPos] = (byte)((in[10 + inPos] & 33554431) >>> 11 & 255);
         out[33 + outPos] = (byte)((in[10 + inPos] & 33554431) >>> 3 & 255);
         out[34 + outPos] = (byte)(((in[10 + inPos] & 33554431) << 5 | (in[11 + inPos] & 33554431) >>> 20) & 255);
         out[35 + outPos] = (byte)((in[11 + inPos] & 33554431) >>> 12 & 255);
         out[36 + outPos] = (byte)((in[11 + inPos] & 33554431) >>> 4 & 255);
         out[37 + outPos] = (byte)(((in[11 + inPos] & 33554431) << 4 | (in[12 + inPos] & 33554431) >>> 21) & 255);
         out[38 + outPos] = (byte)((in[12 + inPos] & 33554431) >>> 13 & 255);
         out[39 + outPos] = (byte)((in[12 + inPos] & 33554431) >>> 5 & 255);
         out[40 + outPos] = (byte)(((in[12 + inPos] & 33554431) << 3 | (in[13 + inPos] & 33554431) >>> 22) & 255);
         out[41 + outPos] = (byte)((in[13 + inPos] & 33554431) >>> 14 & 255);
         out[42 + outPos] = (byte)((in[13 + inPos] & 33554431) >>> 6 & 255);
         out[43 + outPos] = (byte)(((in[13 + inPos] & 33554431) << 2 | (in[14 + inPos] & 33554431) >>> 23) & 255);
         out[44 + outPos] = (byte)((in[14 + inPos] & 33554431) >>> 15 & 255);
         out[45 + outPos] = (byte)((in[14 + inPos] & 33554431) >>> 7 & 255);
         out[46 + outPos] = (byte)(((in[14 + inPos] & 33554431) << 1 | (in[15 + inPos] & 33554431) >>> 24) & 255);
         out[47 + outPos] = (byte)((in[15 + inPos] & 33554431) >>> 16 & 255);
         out[48 + outPos] = (byte)((in[15 + inPos] & 33554431) >>> 8 & 255);
         out[49 + outPos] = (byte)(in[15 + inPos] & 33554431 & 255);
         out[50 + outPos] = (byte)((in[16 + inPos] & 33554431) >>> 17 & 255);
         out[51 + outPos] = (byte)((in[16 + inPos] & 33554431) >>> 9 & 255);
         out[52 + outPos] = (byte)((in[16 + inPos] & 33554431) >>> 1 & 255);
         out[53 + outPos] = (byte)(((in[16 + inPos] & 33554431) << 7 | (in[17 + inPos] & 33554431) >>> 18) & 255);
         out[54 + outPos] = (byte)((in[17 + inPos] & 33554431) >>> 10 & 255);
         out[55 + outPos] = (byte)((in[17 + inPos] & 33554431) >>> 2 & 255);
         out[56 + outPos] = (byte)(((in[17 + inPos] & 33554431) << 6 | (in[18 + inPos] & 33554431) >>> 19) & 255);
         out[57 + outPos] = (byte)((in[18 + inPos] & 33554431) >>> 11 & 255);
         out[58 + outPos] = (byte)((in[18 + inPos] & 33554431) >>> 3 & 255);
         out[59 + outPos] = (byte)(((in[18 + inPos] & 33554431) << 5 | (in[19 + inPos] & 33554431) >>> 20) & 255);
         out[60 + outPos] = (byte)((in[19 + inPos] & 33554431) >>> 12 & 255);
         out[61 + outPos] = (byte)((in[19 + inPos] & 33554431) >>> 4 & 255);
         out[62 + outPos] = (byte)(((in[19 + inPos] & 33554431) << 4 | (in[20 + inPos] & 33554431) >>> 21) & 255);
         out[63 + outPos] = (byte)((in[20 + inPos] & 33554431) >>> 13 & 255);
         out[64 + outPos] = (byte)((in[20 + inPos] & 33554431) >>> 5 & 255);
         out[65 + outPos] = (byte)(((in[20 + inPos] & 33554431) << 3 | (in[21 + inPos] & 33554431) >>> 22) & 255);
         out[66 + outPos] = (byte)((in[21 + inPos] & 33554431) >>> 14 & 255);
         out[67 + outPos] = (byte)((in[21 + inPos] & 33554431) >>> 6 & 255);
         out[68 + outPos] = (byte)(((in[21 + inPos] & 33554431) << 2 | (in[22 + inPos] & 33554431) >>> 23) & 255);
         out[69 + outPos] = (byte)((in[22 + inPos] & 33554431) >>> 15 & 255);
         out[70 + outPos] = (byte)((in[22 + inPos] & 33554431) >>> 7 & 255);
         out[71 + outPos] = (byte)(((in[22 + inPos] & 33554431) << 1 | (in[23 + inPos] & 33554431) >>> 24) & 255);
         out[72 + outPos] = (byte)((in[23 + inPos] & 33554431) >>> 16 & 255);
         out[73 + outPos] = (byte)((in[23 + inPos] & 33554431) >>> 8 & 255);
         out[74 + outPos] = (byte)(in[23 + inPos] & 33554431 & 255);
         out[75 + outPos] = (byte)((in[24 + inPos] & 33554431) >>> 17 & 255);
         out[76 + outPos] = (byte)((in[24 + inPos] & 33554431) >>> 9 & 255);
         out[77 + outPos] = (byte)((in[24 + inPos] & 33554431) >>> 1 & 255);
         out[78 + outPos] = (byte)(((in[24 + inPos] & 33554431) << 7 | (in[25 + inPos] & 33554431) >>> 18) & 255);
         out[79 + outPos] = (byte)((in[25 + inPos] & 33554431) >>> 10 & 255);
         out[80 + outPos] = (byte)((in[25 + inPos] & 33554431) >>> 2 & 255);
         out[81 + outPos] = (byte)(((in[25 + inPos] & 33554431) << 6 | (in[26 + inPos] & 33554431) >>> 19) & 255);
         out[82 + outPos] = (byte)((in[26 + inPos] & 33554431) >>> 11 & 255);
         out[83 + outPos] = (byte)((in[26 + inPos] & 33554431) >>> 3 & 255);
         out[84 + outPos] = (byte)(((in[26 + inPos] & 33554431) << 5 | (in[27 + inPos] & 33554431) >>> 20) & 255);
         out[85 + outPos] = (byte)((in[27 + inPos] & 33554431) >>> 12 & 255);
         out[86 + outPos] = (byte)((in[27 + inPos] & 33554431) >>> 4 & 255);
         out[87 + outPos] = (byte)(((in[27 + inPos] & 33554431) << 4 | (in[28 + inPos] & 33554431) >>> 21) & 255);
         out[88 + outPos] = (byte)((in[28 + inPos] & 33554431) >>> 13 & 255);
         out[89 + outPos] = (byte)((in[28 + inPos] & 33554431) >>> 5 & 255);
         out[90 + outPos] = (byte)(((in[28 + inPos] & 33554431) << 3 | (in[29 + inPos] & 33554431) >>> 22) & 255);
         out[91 + outPos] = (byte)((in[29 + inPos] & 33554431) >>> 14 & 255);
         out[92 + outPos] = (byte)((in[29 + inPos] & 33554431) >>> 6 & 255);
         out[93 + outPos] = (byte)(((in[29 + inPos] & 33554431) << 2 | (in[30 + inPos] & 33554431) >>> 23) & 255);
         out[94 + outPos] = (byte)((in[30 + inPos] & 33554431) >>> 15 & 255);
         out[95 + outPos] = (byte)((in[30 + inPos] & 33554431) >>> 7 & 255);
         out[96 + outPos] = (byte)(((in[30 + inPos] & 33554431) << 1 | (in[31 + inPos] & 33554431) >>> 24) & 255);
         out[97 + outPos] = (byte)((in[31 + inPos] & 33554431) >>> 16 & 255);
         out[98 + outPos] = (byte)((in[31 + inPos] & 33554431) >>> 8 & 255);
         out[99 + outPos] = (byte)(in[31 + inPos] & 33554431 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 17 & 33554431 | in[1 + inPos] << 9 & 131071 | in[2 + inPos] << 1 & 511 | in[3 + inPos] >> 7 & 1;
         out[1 + outPos] = in[3 + inPos] << 18 & 33554431 | in[4 + inPos] << 10 & 262143 | in[5 + inPos] << 2 & 1023 | in[6 + inPos] >> 6 & 3;
         out[2 + outPos] = in[6 + inPos] << 19 & 33554431 | in[7 + inPos] << 11 & 524287 | in[8 + inPos] << 3 & 2047 | in[9 + inPos] >> 5 & 7;
         out[3 + outPos] = in[9 + inPos] << 20 & 33554431 | in[10 + inPos] << 12 & 1048575 | in[11 + inPos] << 4 & 4095 | in[12 + inPos] >> 4 & 15;
         out[4 + outPos] = in[12 + inPos] << 21 & 33554431 | in[13 + inPos] << 13 & 2097151 | in[14 + inPos] << 5 & 8191 | in[15 + inPos] >> 3 & 31;
         out[5 + outPos] = in[15 + inPos] << 22 & 33554431 | in[16 + inPos] << 14 & 4194303 | in[17 + inPos] << 6 & 16383 | in[18 + inPos] >> 2 & 63;
         out[6 + outPos] = in[18 + inPos] << 23 & 33554431 | in[19 + inPos] << 15 & 8388607 | in[20 + inPos] << 7 & 32767 | in[21 + inPos] >> 1 & 127;
         out[7 + outPos] = in[21 + inPos] << 24 & 33554431 | in[22 + inPos] << 16 & 16777215 | in[23 + inPos] << 8 & '\uffff' | in[24 + inPos] & 255;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 17 & 33554431 | in.get(1 + inPos) << 9 & 131071 | in.get(2 + inPos) << 1 & 511 | in.get(3 + inPos) >> 7 & 1;
         out[1 + outPos] = in.get(3 + inPos) << 18 & 33554431 | in.get(4 + inPos) << 10 & 262143 | in.get(5 + inPos) << 2 & 1023 | in.get(6 + inPos) >> 6 & 3;
         out[2 + outPos] = in.get(6 + inPos) << 19 & 33554431 | in.get(7 + inPos) << 11 & 524287 | in.get(8 + inPos) << 3 & 2047 | in.get(9 + inPos) >> 5 & 7;
         out[3 + outPos] = in.get(9 + inPos) << 20 & 33554431 | in.get(10 + inPos) << 12 & 1048575 | in.get(11 + inPos) << 4 & 4095 | in.get(12 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(12 + inPos) << 21 & 33554431 | in.get(13 + inPos) << 13 & 2097151 | in.get(14 + inPos) << 5 & 8191 | in.get(15 + inPos) >> 3 & 31;
         out[5 + outPos] = in.get(15 + inPos) << 22 & 33554431 | in.get(16 + inPos) << 14 & 4194303 | in.get(17 + inPos) << 6 & 16383 | in.get(18 + inPos) >> 2 & 63;
         out[6 + outPos] = in.get(18 + inPos) << 23 & 33554431 | in.get(19 + inPos) << 15 & 8388607 | in.get(20 + inPos) << 7 & 32767 | in.get(21 + inPos) >> 1 & 127;
         out[7 + outPos] = in.get(21 + inPos) << 24 & 33554431 | in.get(22 + inPos) << 16 & 16777215 | in.get(23 + inPos) << 8 & '\uffff' | in.get(24 + inPos) & 255;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 17 & 33554431 | in[1 + inPos] << 9 & 131071 | in[2 + inPos] << 1 & 511 | in[3 + inPos] >> 7 & 1;
         out[1 + outPos] = in[3 + inPos] << 18 & 33554431 | in[4 + inPos] << 10 & 262143 | in[5 + inPos] << 2 & 1023 | in[6 + inPos] >> 6 & 3;
         out[2 + outPos] = in[6 + inPos] << 19 & 33554431 | in[7 + inPos] << 11 & 524287 | in[8 + inPos] << 3 & 2047 | in[9 + inPos] >> 5 & 7;
         out[3 + outPos] = in[9 + inPos] << 20 & 33554431 | in[10 + inPos] << 12 & 1048575 | in[11 + inPos] << 4 & 4095 | in[12 + inPos] >> 4 & 15;
         out[4 + outPos] = in[12 + inPos] << 21 & 33554431 | in[13 + inPos] << 13 & 2097151 | in[14 + inPos] << 5 & 8191 | in[15 + inPos] >> 3 & 31;
         out[5 + outPos] = in[15 + inPos] << 22 & 33554431 | in[16 + inPos] << 14 & 4194303 | in[17 + inPos] << 6 & 16383 | in[18 + inPos] >> 2 & 63;
         out[6 + outPos] = in[18 + inPos] << 23 & 33554431 | in[19 + inPos] << 15 & 8388607 | in[20 + inPos] << 7 & 32767 | in[21 + inPos] >> 1 & 127;
         out[7 + outPos] = in[21 + inPos] << 24 & 33554431 | in[22 + inPos] << 16 & 16777215 | in[23 + inPos] << 8 & '\uffff' | in[24 + inPos] & 255;
         out[8 + outPos] = in[25 + inPos] << 17 & 33554431 | in[26 + inPos] << 9 & 131071 | in[27 + inPos] << 1 & 511 | in[28 + inPos] >> 7 & 1;
         out[9 + outPos] = in[28 + inPos] << 18 & 33554431 | in[29 + inPos] << 10 & 262143 | in[30 + inPos] << 2 & 1023 | in[31 + inPos] >> 6 & 3;
         out[10 + outPos] = in[31 + inPos] << 19 & 33554431 | in[32 + inPos] << 11 & 524287 | in[33 + inPos] << 3 & 2047 | in[34 + inPos] >> 5 & 7;
         out[11 + outPos] = in[34 + inPos] << 20 & 33554431 | in[35 + inPos] << 12 & 1048575 | in[36 + inPos] << 4 & 4095 | in[37 + inPos] >> 4 & 15;
         out[12 + outPos] = in[37 + inPos] << 21 & 33554431 | in[38 + inPos] << 13 & 2097151 | in[39 + inPos] << 5 & 8191 | in[40 + inPos] >> 3 & 31;
         out[13 + outPos] = in[40 + inPos] << 22 & 33554431 | in[41 + inPos] << 14 & 4194303 | in[42 + inPos] << 6 & 16383 | in[43 + inPos] >> 2 & 63;
         out[14 + outPos] = in[43 + inPos] << 23 & 33554431 | in[44 + inPos] << 15 & 8388607 | in[45 + inPos] << 7 & 32767 | in[46 + inPos] >> 1 & 127;
         out[15 + outPos] = in[46 + inPos] << 24 & 33554431 | in[47 + inPos] << 16 & 16777215 | in[48 + inPos] << 8 & '\uffff' | in[49 + inPos] & 255;
         out[16 + outPos] = in[50 + inPos] << 17 & 33554431 | in[51 + inPos] << 9 & 131071 | in[52 + inPos] << 1 & 511 | in[53 + inPos] >> 7 & 1;
         out[17 + outPos] = in[53 + inPos] << 18 & 33554431 | in[54 + inPos] << 10 & 262143 | in[55 + inPos] << 2 & 1023 | in[56 + inPos] >> 6 & 3;
         out[18 + outPos] = in[56 + inPos] << 19 & 33554431 | in[57 + inPos] << 11 & 524287 | in[58 + inPos] << 3 & 2047 | in[59 + inPos] >> 5 & 7;
         out[19 + outPos] = in[59 + inPos] << 20 & 33554431 | in[60 + inPos] << 12 & 1048575 | in[61 + inPos] << 4 & 4095 | in[62 + inPos] >> 4 & 15;
         out[20 + outPos] = in[62 + inPos] << 21 & 33554431 | in[63 + inPos] << 13 & 2097151 | in[64 + inPos] << 5 & 8191 | in[65 + inPos] >> 3 & 31;
         out[21 + outPos] = in[65 + inPos] << 22 & 33554431 | in[66 + inPos] << 14 & 4194303 | in[67 + inPos] << 6 & 16383 | in[68 + inPos] >> 2 & 63;
         out[22 + outPos] = in[68 + inPos] << 23 & 33554431 | in[69 + inPos] << 15 & 8388607 | in[70 + inPos] << 7 & 32767 | in[71 + inPos] >> 1 & 127;
         out[23 + outPos] = in[71 + inPos] << 24 & 33554431 | in[72 + inPos] << 16 & 16777215 | in[73 + inPos] << 8 & '\uffff' | in[74 + inPos] & 255;
         out[24 + outPos] = in[75 + inPos] << 17 & 33554431 | in[76 + inPos] << 9 & 131071 | in[77 + inPos] << 1 & 511 | in[78 + inPos] >> 7 & 1;
         out[25 + outPos] = in[78 + inPos] << 18 & 33554431 | in[79 + inPos] << 10 & 262143 | in[80 + inPos] << 2 & 1023 | in[81 + inPos] >> 6 & 3;
         out[26 + outPos] = in[81 + inPos] << 19 & 33554431 | in[82 + inPos] << 11 & 524287 | in[83 + inPos] << 3 & 2047 | in[84 + inPos] >> 5 & 7;
         out[27 + outPos] = in[84 + inPos] << 20 & 33554431 | in[85 + inPos] << 12 & 1048575 | in[86 + inPos] << 4 & 4095 | in[87 + inPos] >> 4 & 15;
         out[28 + outPos] = in[87 + inPos] << 21 & 33554431 | in[88 + inPos] << 13 & 2097151 | in[89 + inPos] << 5 & 8191 | in[90 + inPos] >> 3 & 31;
         out[29 + outPos] = in[90 + inPos] << 22 & 33554431 | in[91 + inPos] << 14 & 4194303 | in[92 + inPos] << 6 & 16383 | in[93 + inPos] >> 2 & 63;
         out[30 + outPos] = in[93 + inPos] << 23 & 33554431 | in[94 + inPos] << 15 & 8388607 | in[95 + inPos] << 7 & 32767 | in[96 + inPos] >> 1 & 127;
         out[31 + outPos] = in[96 + inPos] << 24 & 33554431 | in[97 + inPos] << 16 & 16777215 | in[98 + inPos] << 8 & '\uffff' | in[99 + inPos] & 255;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 17 & 33554431 | in.get(1 + inPos) << 9 & 131071 | in.get(2 + inPos) << 1 & 511 | in.get(3 + inPos) >> 7 & 1;
         out[1 + outPos] = in.get(3 + inPos) << 18 & 33554431 | in.get(4 + inPos) << 10 & 262143 | in.get(5 + inPos) << 2 & 1023 | in.get(6 + inPos) >> 6 & 3;
         out[2 + outPos] = in.get(6 + inPos) << 19 & 33554431 | in.get(7 + inPos) << 11 & 524287 | in.get(8 + inPos) << 3 & 2047 | in.get(9 + inPos) >> 5 & 7;
         out[3 + outPos] = in.get(9 + inPos) << 20 & 33554431 | in.get(10 + inPos) << 12 & 1048575 | in.get(11 + inPos) << 4 & 4095 | in.get(12 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(12 + inPos) << 21 & 33554431 | in.get(13 + inPos) << 13 & 2097151 | in.get(14 + inPos) << 5 & 8191 | in.get(15 + inPos) >> 3 & 31;
         out[5 + outPos] = in.get(15 + inPos) << 22 & 33554431 | in.get(16 + inPos) << 14 & 4194303 | in.get(17 + inPos) << 6 & 16383 | in.get(18 + inPos) >> 2 & 63;
         out[6 + outPos] = in.get(18 + inPos) << 23 & 33554431 | in.get(19 + inPos) << 15 & 8388607 | in.get(20 + inPos) << 7 & 32767 | in.get(21 + inPos) >> 1 & 127;
         out[7 + outPos] = in.get(21 + inPos) << 24 & 33554431 | in.get(22 + inPos) << 16 & 16777215 | in.get(23 + inPos) << 8 & '\uffff' | in.get(24 + inPos) & 255;
         out[8 + outPos] = in.get(25 + inPos) << 17 & 33554431 | in.get(26 + inPos) << 9 & 131071 | in.get(27 + inPos) << 1 & 511 | in.get(28 + inPos) >> 7 & 1;
         out[9 + outPos] = in.get(28 + inPos) << 18 & 33554431 | in.get(29 + inPos) << 10 & 262143 | in.get(30 + inPos) << 2 & 1023 | in.get(31 + inPos) >> 6 & 3;
         out[10 + outPos] = in.get(31 + inPos) << 19 & 33554431 | in.get(32 + inPos) << 11 & 524287 | in.get(33 + inPos) << 3 & 2047 | in.get(34 + inPos) >> 5 & 7;
         out[11 + outPos] = in.get(34 + inPos) << 20 & 33554431 | in.get(35 + inPos) << 12 & 1048575 | in.get(36 + inPos) << 4 & 4095 | in.get(37 + inPos) >> 4 & 15;
         out[12 + outPos] = in.get(37 + inPos) << 21 & 33554431 | in.get(38 + inPos) << 13 & 2097151 | in.get(39 + inPos) << 5 & 8191 | in.get(40 + inPos) >> 3 & 31;
         out[13 + outPos] = in.get(40 + inPos) << 22 & 33554431 | in.get(41 + inPos) << 14 & 4194303 | in.get(42 + inPos) << 6 & 16383 | in.get(43 + inPos) >> 2 & 63;
         out[14 + outPos] = in.get(43 + inPos) << 23 & 33554431 | in.get(44 + inPos) << 15 & 8388607 | in.get(45 + inPos) << 7 & 32767 | in.get(46 + inPos) >> 1 & 127;
         out[15 + outPos] = in.get(46 + inPos) << 24 & 33554431 | in.get(47 + inPos) << 16 & 16777215 | in.get(48 + inPos) << 8 & '\uffff' | in.get(49 + inPos) & 255;
         out[16 + outPos] = in.get(50 + inPos) << 17 & 33554431 | in.get(51 + inPos) << 9 & 131071 | in.get(52 + inPos) << 1 & 511 | in.get(53 + inPos) >> 7 & 1;
         out[17 + outPos] = in.get(53 + inPos) << 18 & 33554431 | in.get(54 + inPos) << 10 & 262143 | in.get(55 + inPos) << 2 & 1023 | in.get(56 + inPos) >> 6 & 3;
         out[18 + outPos] = in.get(56 + inPos) << 19 & 33554431 | in.get(57 + inPos) << 11 & 524287 | in.get(58 + inPos) << 3 & 2047 | in.get(59 + inPos) >> 5 & 7;
         out[19 + outPos] = in.get(59 + inPos) << 20 & 33554431 | in.get(60 + inPos) << 12 & 1048575 | in.get(61 + inPos) << 4 & 4095 | in.get(62 + inPos) >> 4 & 15;
         out[20 + outPos] = in.get(62 + inPos) << 21 & 33554431 | in.get(63 + inPos) << 13 & 2097151 | in.get(64 + inPos) << 5 & 8191 | in.get(65 + inPos) >> 3 & 31;
         out[21 + outPos] = in.get(65 + inPos) << 22 & 33554431 | in.get(66 + inPos) << 14 & 4194303 | in.get(67 + inPos) << 6 & 16383 | in.get(68 + inPos) >> 2 & 63;
         out[22 + outPos] = in.get(68 + inPos) << 23 & 33554431 | in.get(69 + inPos) << 15 & 8388607 | in.get(70 + inPos) << 7 & 32767 | in.get(71 + inPos) >> 1 & 127;
         out[23 + outPos] = in.get(71 + inPos) << 24 & 33554431 | in.get(72 + inPos) << 16 & 16777215 | in.get(73 + inPos) << 8 & '\uffff' | in.get(74 + inPos) & 255;
         out[24 + outPos] = in.get(75 + inPos) << 17 & 33554431 | in.get(76 + inPos) << 9 & 131071 | in.get(77 + inPos) << 1 & 511 | in.get(78 + inPos) >> 7 & 1;
         out[25 + outPos] = in.get(78 + inPos) << 18 & 33554431 | in.get(79 + inPos) << 10 & 262143 | in.get(80 + inPos) << 2 & 1023 | in.get(81 + inPos) >> 6 & 3;
         out[26 + outPos] = in.get(81 + inPos) << 19 & 33554431 | in.get(82 + inPos) << 11 & 524287 | in.get(83 + inPos) << 3 & 2047 | in.get(84 + inPos) >> 5 & 7;
         out[27 + outPos] = in.get(84 + inPos) << 20 & 33554431 | in.get(85 + inPos) << 12 & 1048575 | in.get(86 + inPos) << 4 & 4095 | in.get(87 + inPos) >> 4 & 15;
         out[28 + outPos] = in.get(87 + inPos) << 21 & 33554431 | in.get(88 + inPos) << 13 & 2097151 | in.get(89 + inPos) << 5 & 8191 | in.get(90 + inPos) >> 3 & 31;
         out[29 + outPos] = in.get(90 + inPos) << 22 & 33554431 | in.get(91 + inPos) << 14 & 4194303 | in.get(92 + inPos) << 6 & 16383 | in.get(93 + inPos) >> 2 & 63;
         out[30 + outPos] = in.get(93 + inPos) << 23 & 33554431 | in.get(94 + inPos) << 15 & 8388607 | in.get(95 + inPos) << 7 & 32767 | in.get(96 + inPos) >> 1 & 127;
         out[31 + outPos] = in.get(96 + inPos) << 24 & 33554431 | in.get(97 + inPos) << 16 & 16777215 | in.get(98 + inPos) << 8 & '\uffff' | in.get(99 + inPos) & 255;
      }
   }

   private static final class Packer26 extends BytePacker {
      private Packer26() {
         super(26);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 67108863) >>> 18 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 67108863) >>> 10 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & 67108863) >>> 2 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & 67108863) << 6 | (in[1 + inPos] & 67108863) >>> 20) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 67108863) >>> 12 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & 67108863) >>> 4 & 255);
         out[6 + outPos] = (byte)(((in[1 + inPos] & 67108863) << 4 | (in[2 + inPos] & 67108863) >>> 22) & 255);
         out[7 + outPos] = (byte)((in[2 + inPos] & 67108863) >>> 14 & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & 67108863) >>> 6 & 255);
         out[9 + outPos] = (byte)(((in[2 + inPos] & 67108863) << 2 | (in[3 + inPos] & 67108863) >>> 24) & 255);
         out[10 + outPos] = (byte)((in[3 + inPos] & 67108863) >>> 16 & 255);
         out[11 + outPos] = (byte)((in[3 + inPos] & 67108863) >>> 8 & 255);
         out[12 + outPos] = (byte)(in[3 + inPos] & 67108863 & 255);
         out[13 + outPos] = (byte)((in[4 + inPos] & 67108863) >>> 18 & 255);
         out[14 + outPos] = (byte)((in[4 + inPos] & 67108863) >>> 10 & 255);
         out[15 + outPos] = (byte)((in[4 + inPos] & 67108863) >>> 2 & 255);
         out[16 + outPos] = (byte)(((in[4 + inPos] & 67108863) << 6 | (in[5 + inPos] & 67108863) >>> 20) & 255);
         out[17 + outPos] = (byte)((in[5 + inPos] & 67108863) >>> 12 & 255);
         out[18 + outPos] = (byte)((in[5 + inPos] & 67108863) >>> 4 & 255);
         out[19 + outPos] = (byte)(((in[5 + inPos] & 67108863) << 4 | (in[6 + inPos] & 67108863) >>> 22) & 255);
         out[20 + outPos] = (byte)((in[6 + inPos] & 67108863) >>> 14 & 255);
         out[21 + outPos] = (byte)((in[6 + inPos] & 67108863) >>> 6 & 255);
         out[22 + outPos] = (byte)(((in[6 + inPos] & 67108863) << 2 | (in[7 + inPos] & 67108863) >>> 24) & 255);
         out[23 + outPos] = (byte)((in[7 + inPos] & 67108863) >>> 16 & 255);
         out[24 + outPos] = (byte)((in[7 + inPos] & 67108863) >>> 8 & 255);
         out[25 + outPos] = (byte)(in[7 + inPos] & 67108863 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 67108863) >>> 18 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 67108863) >>> 10 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & 67108863) >>> 2 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & 67108863) << 6 | (in[1 + inPos] & 67108863) >>> 20) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 67108863) >>> 12 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & 67108863) >>> 4 & 255);
         out[6 + outPos] = (byte)(((in[1 + inPos] & 67108863) << 4 | (in[2 + inPos] & 67108863) >>> 22) & 255);
         out[7 + outPos] = (byte)((in[2 + inPos] & 67108863) >>> 14 & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & 67108863) >>> 6 & 255);
         out[9 + outPos] = (byte)(((in[2 + inPos] & 67108863) << 2 | (in[3 + inPos] & 67108863) >>> 24) & 255);
         out[10 + outPos] = (byte)((in[3 + inPos] & 67108863) >>> 16 & 255);
         out[11 + outPos] = (byte)((in[3 + inPos] & 67108863) >>> 8 & 255);
         out[12 + outPos] = (byte)(in[3 + inPos] & 67108863 & 255);
         out[13 + outPos] = (byte)((in[4 + inPos] & 67108863) >>> 18 & 255);
         out[14 + outPos] = (byte)((in[4 + inPos] & 67108863) >>> 10 & 255);
         out[15 + outPos] = (byte)((in[4 + inPos] & 67108863) >>> 2 & 255);
         out[16 + outPos] = (byte)(((in[4 + inPos] & 67108863) << 6 | (in[5 + inPos] & 67108863) >>> 20) & 255);
         out[17 + outPos] = (byte)((in[5 + inPos] & 67108863) >>> 12 & 255);
         out[18 + outPos] = (byte)((in[5 + inPos] & 67108863) >>> 4 & 255);
         out[19 + outPos] = (byte)(((in[5 + inPos] & 67108863) << 4 | (in[6 + inPos] & 67108863) >>> 22) & 255);
         out[20 + outPos] = (byte)((in[6 + inPos] & 67108863) >>> 14 & 255);
         out[21 + outPos] = (byte)((in[6 + inPos] & 67108863) >>> 6 & 255);
         out[22 + outPos] = (byte)(((in[6 + inPos] & 67108863) << 2 | (in[7 + inPos] & 67108863) >>> 24) & 255);
         out[23 + outPos] = (byte)((in[7 + inPos] & 67108863) >>> 16 & 255);
         out[24 + outPos] = (byte)((in[7 + inPos] & 67108863) >>> 8 & 255);
         out[25 + outPos] = (byte)(in[7 + inPos] & 67108863 & 255);
         out[26 + outPos] = (byte)((in[8 + inPos] & 67108863) >>> 18 & 255);
         out[27 + outPos] = (byte)((in[8 + inPos] & 67108863) >>> 10 & 255);
         out[28 + outPos] = (byte)((in[8 + inPos] & 67108863) >>> 2 & 255);
         out[29 + outPos] = (byte)(((in[8 + inPos] & 67108863) << 6 | (in[9 + inPos] & 67108863) >>> 20) & 255);
         out[30 + outPos] = (byte)((in[9 + inPos] & 67108863) >>> 12 & 255);
         out[31 + outPos] = (byte)((in[9 + inPos] & 67108863) >>> 4 & 255);
         out[32 + outPos] = (byte)(((in[9 + inPos] & 67108863) << 4 | (in[10 + inPos] & 67108863) >>> 22) & 255);
         out[33 + outPos] = (byte)((in[10 + inPos] & 67108863) >>> 14 & 255);
         out[34 + outPos] = (byte)((in[10 + inPos] & 67108863) >>> 6 & 255);
         out[35 + outPos] = (byte)(((in[10 + inPos] & 67108863) << 2 | (in[11 + inPos] & 67108863) >>> 24) & 255);
         out[36 + outPos] = (byte)((in[11 + inPos] & 67108863) >>> 16 & 255);
         out[37 + outPos] = (byte)((in[11 + inPos] & 67108863) >>> 8 & 255);
         out[38 + outPos] = (byte)(in[11 + inPos] & 67108863 & 255);
         out[39 + outPos] = (byte)((in[12 + inPos] & 67108863) >>> 18 & 255);
         out[40 + outPos] = (byte)((in[12 + inPos] & 67108863) >>> 10 & 255);
         out[41 + outPos] = (byte)((in[12 + inPos] & 67108863) >>> 2 & 255);
         out[42 + outPos] = (byte)(((in[12 + inPos] & 67108863) << 6 | (in[13 + inPos] & 67108863) >>> 20) & 255);
         out[43 + outPos] = (byte)((in[13 + inPos] & 67108863) >>> 12 & 255);
         out[44 + outPos] = (byte)((in[13 + inPos] & 67108863) >>> 4 & 255);
         out[45 + outPos] = (byte)(((in[13 + inPos] & 67108863) << 4 | (in[14 + inPos] & 67108863) >>> 22) & 255);
         out[46 + outPos] = (byte)((in[14 + inPos] & 67108863) >>> 14 & 255);
         out[47 + outPos] = (byte)((in[14 + inPos] & 67108863) >>> 6 & 255);
         out[48 + outPos] = (byte)(((in[14 + inPos] & 67108863) << 2 | (in[15 + inPos] & 67108863) >>> 24) & 255);
         out[49 + outPos] = (byte)((in[15 + inPos] & 67108863) >>> 16 & 255);
         out[50 + outPos] = (byte)((in[15 + inPos] & 67108863) >>> 8 & 255);
         out[51 + outPos] = (byte)(in[15 + inPos] & 67108863 & 255);
         out[52 + outPos] = (byte)((in[16 + inPos] & 67108863) >>> 18 & 255);
         out[53 + outPos] = (byte)((in[16 + inPos] & 67108863) >>> 10 & 255);
         out[54 + outPos] = (byte)((in[16 + inPos] & 67108863) >>> 2 & 255);
         out[55 + outPos] = (byte)(((in[16 + inPos] & 67108863) << 6 | (in[17 + inPos] & 67108863) >>> 20) & 255);
         out[56 + outPos] = (byte)((in[17 + inPos] & 67108863) >>> 12 & 255);
         out[57 + outPos] = (byte)((in[17 + inPos] & 67108863) >>> 4 & 255);
         out[58 + outPos] = (byte)(((in[17 + inPos] & 67108863) << 4 | (in[18 + inPos] & 67108863) >>> 22) & 255);
         out[59 + outPos] = (byte)((in[18 + inPos] & 67108863) >>> 14 & 255);
         out[60 + outPos] = (byte)((in[18 + inPos] & 67108863) >>> 6 & 255);
         out[61 + outPos] = (byte)(((in[18 + inPos] & 67108863) << 2 | (in[19 + inPos] & 67108863) >>> 24) & 255);
         out[62 + outPos] = (byte)((in[19 + inPos] & 67108863) >>> 16 & 255);
         out[63 + outPos] = (byte)((in[19 + inPos] & 67108863) >>> 8 & 255);
         out[64 + outPos] = (byte)(in[19 + inPos] & 67108863 & 255);
         out[65 + outPos] = (byte)((in[20 + inPos] & 67108863) >>> 18 & 255);
         out[66 + outPos] = (byte)((in[20 + inPos] & 67108863) >>> 10 & 255);
         out[67 + outPos] = (byte)((in[20 + inPos] & 67108863) >>> 2 & 255);
         out[68 + outPos] = (byte)(((in[20 + inPos] & 67108863) << 6 | (in[21 + inPos] & 67108863) >>> 20) & 255);
         out[69 + outPos] = (byte)((in[21 + inPos] & 67108863) >>> 12 & 255);
         out[70 + outPos] = (byte)((in[21 + inPos] & 67108863) >>> 4 & 255);
         out[71 + outPos] = (byte)(((in[21 + inPos] & 67108863) << 4 | (in[22 + inPos] & 67108863) >>> 22) & 255);
         out[72 + outPos] = (byte)((in[22 + inPos] & 67108863) >>> 14 & 255);
         out[73 + outPos] = (byte)((in[22 + inPos] & 67108863) >>> 6 & 255);
         out[74 + outPos] = (byte)(((in[22 + inPos] & 67108863) << 2 | (in[23 + inPos] & 67108863) >>> 24) & 255);
         out[75 + outPos] = (byte)((in[23 + inPos] & 67108863) >>> 16 & 255);
         out[76 + outPos] = (byte)((in[23 + inPos] & 67108863) >>> 8 & 255);
         out[77 + outPos] = (byte)(in[23 + inPos] & 67108863 & 255);
         out[78 + outPos] = (byte)((in[24 + inPos] & 67108863) >>> 18 & 255);
         out[79 + outPos] = (byte)((in[24 + inPos] & 67108863) >>> 10 & 255);
         out[80 + outPos] = (byte)((in[24 + inPos] & 67108863) >>> 2 & 255);
         out[81 + outPos] = (byte)(((in[24 + inPos] & 67108863) << 6 | (in[25 + inPos] & 67108863) >>> 20) & 255);
         out[82 + outPos] = (byte)((in[25 + inPos] & 67108863) >>> 12 & 255);
         out[83 + outPos] = (byte)((in[25 + inPos] & 67108863) >>> 4 & 255);
         out[84 + outPos] = (byte)(((in[25 + inPos] & 67108863) << 4 | (in[26 + inPos] & 67108863) >>> 22) & 255);
         out[85 + outPos] = (byte)((in[26 + inPos] & 67108863) >>> 14 & 255);
         out[86 + outPos] = (byte)((in[26 + inPos] & 67108863) >>> 6 & 255);
         out[87 + outPos] = (byte)(((in[26 + inPos] & 67108863) << 2 | (in[27 + inPos] & 67108863) >>> 24) & 255);
         out[88 + outPos] = (byte)((in[27 + inPos] & 67108863) >>> 16 & 255);
         out[89 + outPos] = (byte)((in[27 + inPos] & 67108863) >>> 8 & 255);
         out[90 + outPos] = (byte)(in[27 + inPos] & 67108863 & 255);
         out[91 + outPos] = (byte)((in[28 + inPos] & 67108863) >>> 18 & 255);
         out[92 + outPos] = (byte)((in[28 + inPos] & 67108863) >>> 10 & 255);
         out[93 + outPos] = (byte)((in[28 + inPos] & 67108863) >>> 2 & 255);
         out[94 + outPos] = (byte)(((in[28 + inPos] & 67108863) << 6 | (in[29 + inPos] & 67108863) >>> 20) & 255);
         out[95 + outPos] = (byte)((in[29 + inPos] & 67108863) >>> 12 & 255);
         out[96 + outPos] = (byte)((in[29 + inPos] & 67108863) >>> 4 & 255);
         out[97 + outPos] = (byte)(((in[29 + inPos] & 67108863) << 4 | (in[30 + inPos] & 67108863) >>> 22) & 255);
         out[98 + outPos] = (byte)((in[30 + inPos] & 67108863) >>> 14 & 255);
         out[99 + outPos] = (byte)((in[30 + inPos] & 67108863) >>> 6 & 255);
         out[100 + outPos] = (byte)(((in[30 + inPos] & 67108863) << 2 | (in[31 + inPos] & 67108863) >>> 24) & 255);
         out[101 + outPos] = (byte)((in[31 + inPos] & 67108863) >>> 16 & 255);
         out[102 + outPos] = (byte)((in[31 + inPos] & 67108863) >>> 8 & 255);
         out[103 + outPos] = (byte)(in[31 + inPos] & 67108863 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 18 & 67108863 | in[1 + inPos] << 10 & 262143 | in[2 + inPos] << 2 & 1023 | in[3 + inPos] >> 6 & 3;
         out[1 + outPos] = in[3 + inPos] << 20 & 67108863 | in[4 + inPos] << 12 & 1048575 | in[5 + inPos] << 4 & 4095 | in[6 + inPos] >> 4 & 15;
         out[2 + outPos] = in[6 + inPos] << 22 & 67108863 | in[7 + inPos] << 14 & 4194303 | in[8 + inPos] << 6 & 16383 | in[9 + inPos] >> 2 & 63;
         out[3 + outPos] = in[9 + inPos] << 24 & 67108863 | in[10 + inPos] << 16 & 16777215 | in[11 + inPos] << 8 & '\uffff' | in[12 + inPos] & 255;
         out[4 + outPos] = in[13 + inPos] << 18 & 67108863 | in[14 + inPos] << 10 & 262143 | in[15 + inPos] << 2 & 1023 | in[16 + inPos] >> 6 & 3;
         out[5 + outPos] = in[16 + inPos] << 20 & 67108863 | in[17 + inPos] << 12 & 1048575 | in[18 + inPos] << 4 & 4095 | in[19 + inPos] >> 4 & 15;
         out[6 + outPos] = in[19 + inPos] << 22 & 67108863 | in[20 + inPos] << 14 & 4194303 | in[21 + inPos] << 6 & 16383 | in[22 + inPos] >> 2 & 63;
         out[7 + outPos] = in[22 + inPos] << 24 & 67108863 | in[23 + inPos] << 16 & 16777215 | in[24 + inPos] << 8 & '\uffff' | in[25 + inPos] & 255;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 18 & 67108863 | in.get(1 + inPos) << 10 & 262143 | in.get(2 + inPos) << 2 & 1023 | in.get(3 + inPos) >> 6 & 3;
         out[1 + outPos] = in.get(3 + inPos) << 20 & 67108863 | in.get(4 + inPos) << 12 & 1048575 | in.get(5 + inPos) << 4 & 4095 | in.get(6 + inPos) >> 4 & 15;
         out[2 + outPos] = in.get(6 + inPos) << 22 & 67108863 | in.get(7 + inPos) << 14 & 4194303 | in.get(8 + inPos) << 6 & 16383 | in.get(9 + inPos) >> 2 & 63;
         out[3 + outPos] = in.get(9 + inPos) << 24 & 67108863 | in.get(10 + inPos) << 16 & 16777215 | in.get(11 + inPos) << 8 & '\uffff' | in.get(12 + inPos) & 255;
         out[4 + outPos] = in.get(13 + inPos) << 18 & 67108863 | in.get(14 + inPos) << 10 & 262143 | in.get(15 + inPos) << 2 & 1023 | in.get(16 + inPos) >> 6 & 3;
         out[5 + outPos] = in.get(16 + inPos) << 20 & 67108863 | in.get(17 + inPos) << 12 & 1048575 | in.get(18 + inPos) << 4 & 4095 | in.get(19 + inPos) >> 4 & 15;
         out[6 + outPos] = in.get(19 + inPos) << 22 & 67108863 | in.get(20 + inPos) << 14 & 4194303 | in.get(21 + inPos) << 6 & 16383 | in.get(22 + inPos) >> 2 & 63;
         out[7 + outPos] = in.get(22 + inPos) << 24 & 67108863 | in.get(23 + inPos) << 16 & 16777215 | in.get(24 + inPos) << 8 & '\uffff' | in.get(25 + inPos) & 255;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 18 & 67108863 | in[1 + inPos] << 10 & 262143 | in[2 + inPos] << 2 & 1023 | in[3 + inPos] >> 6 & 3;
         out[1 + outPos] = in[3 + inPos] << 20 & 67108863 | in[4 + inPos] << 12 & 1048575 | in[5 + inPos] << 4 & 4095 | in[6 + inPos] >> 4 & 15;
         out[2 + outPos] = in[6 + inPos] << 22 & 67108863 | in[7 + inPos] << 14 & 4194303 | in[8 + inPos] << 6 & 16383 | in[9 + inPos] >> 2 & 63;
         out[3 + outPos] = in[9 + inPos] << 24 & 67108863 | in[10 + inPos] << 16 & 16777215 | in[11 + inPos] << 8 & '\uffff' | in[12 + inPos] & 255;
         out[4 + outPos] = in[13 + inPos] << 18 & 67108863 | in[14 + inPos] << 10 & 262143 | in[15 + inPos] << 2 & 1023 | in[16 + inPos] >> 6 & 3;
         out[5 + outPos] = in[16 + inPos] << 20 & 67108863 | in[17 + inPos] << 12 & 1048575 | in[18 + inPos] << 4 & 4095 | in[19 + inPos] >> 4 & 15;
         out[6 + outPos] = in[19 + inPos] << 22 & 67108863 | in[20 + inPos] << 14 & 4194303 | in[21 + inPos] << 6 & 16383 | in[22 + inPos] >> 2 & 63;
         out[7 + outPos] = in[22 + inPos] << 24 & 67108863 | in[23 + inPos] << 16 & 16777215 | in[24 + inPos] << 8 & '\uffff' | in[25 + inPos] & 255;
         out[8 + outPos] = in[26 + inPos] << 18 & 67108863 | in[27 + inPos] << 10 & 262143 | in[28 + inPos] << 2 & 1023 | in[29 + inPos] >> 6 & 3;
         out[9 + outPos] = in[29 + inPos] << 20 & 67108863 | in[30 + inPos] << 12 & 1048575 | in[31 + inPos] << 4 & 4095 | in[32 + inPos] >> 4 & 15;
         out[10 + outPos] = in[32 + inPos] << 22 & 67108863 | in[33 + inPos] << 14 & 4194303 | in[34 + inPos] << 6 & 16383 | in[35 + inPos] >> 2 & 63;
         out[11 + outPos] = in[35 + inPos] << 24 & 67108863 | in[36 + inPos] << 16 & 16777215 | in[37 + inPos] << 8 & '\uffff' | in[38 + inPos] & 255;
         out[12 + outPos] = in[39 + inPos] << 18 & 67108863 | in[40 + inPos] << 10 & 262143 | in[41 + inPos] << 2 & 1023 | in[42 + inPos] >> 6 & 3;
         out[13 + outPos] = in[42 + inPos] << 20 & 67108863 | in[43 + inPos] << 12 & 1048575 | in[44 + inPos] << 4 & 4095 | in[45 + inPos] >> 4 & 15;
         out[14 + outPos] = in[45 + inPos] << 22 & 67108863 | in[46 + inPos] << 14 & 4194303 | in[47 + inPos] << 6 & 16383 | in[48 + inPos] >> 2 & 63;
         out[15 + outPos] = in[48 + inPos] << 24 & 67108863 | in[49 + inPos] << 16 & 16777215 | in[50 + inPos] << 8 & '\uffff' | in[51 + inPos] & 255;
         out[16 + outPos] = in[52 + inPos] << 18 & 67108863 | in[53 + inPos] << 10 & 262143 | in[54 + inPos] << 2 & 1023 | in[55 + inPos] >> 6 & 3;
         out[17 + outPos] = in[55 + inPos] << 20 & 67108863 | in[56 + inPos] << 12 & 1048575 | in[57 + inPos] << 4 & 4095 | in[58 + inPos] >> 4 & 15;
         out[18 + outPos] = in[58 + inPos] << 22 & 67108863 | in[59 + inPos] << 14 & 4194303 | in[60 + inPos] << 6 & 16383 | in[61 + inPos] >> 2 & 63;
         out[19 + outPos] = in[61 + inPos] << 24 & 67108863 | in[62 + inPos] << 16 & 16777215 | in[63 + inPos] << 8 & '\uffff' | in[64 + inPos] & 255;
         out[20 + outPos] = in[65 + inPos] << 18 & 67108863 | in[66 + inPos] << 10 & 262143 | in[67 + inPos] << 2 & 1023 | in[68 + inPos] >> 6 & 3;
         out[21 + outPos] = in[68 + inPos] << 20 & 67108863 | in[69 + inPos] << 12 & 1048575 | in[70 + inPos] << 4 & 4095 | in[71 + inPos] >> 4 & 15;
         out[22 + outPos] = in[71 + inPos] << 22 & 67108863 | in[72 + inPos] << 14 & 4194303 | in[73 + inPos] << 6 & 16383 | in[74 + inPos] >> 2 & 63;
         out[23 + outPos] = in[74 + inPos] << 24 & 67108863 | in[75 + inPos] << 16 & 16777215 | in[76 + inPos] << 8 & '\uffff' | in[77 + inPos] & 255;
         out[24 + outPos] = in[78 + inPos] << 18 & 67108863 | in[79 + inPos] << 10 & 262143 | in[80 + inPos] << 2 & 1023 | in[81 + inPos] >> 6 & 3;
         out[25 + outPos] = in[81 + inPos] << 20 & 67108863 | in[82 + inPos] << 12 & 1048575 | in[83 + inPos] << 4 & 4095 | in[84 + inPos] >> 4 & 15;
         out[26 + outPos] = in[84 + inPos] << 22 & 67108863 | in[85 + inPos] << 14 & 4194303 | in[86 + inPos] << 6 & 16383 | in[87 + inPos] >> 2 & 63;
         out[27 + outPos] = in[87 + inPos] << 24 & 67108863 | in[88 + inPos] << 16 & 16777215 | in[89 + inPos] << 8 & '\uffff' | in[90 + inPos] & 255;
         out[28 + outPos] = in[91 + inPos] << 18 & 67108863 | in[92 + inPos] << 10 & 262143 | in[93 + inPos] << 2 & 1023 | in[94 + inPos] >> 6 & 3;
         out[29 + outPos] = in[94 + inPos] << 20 & 67108863 | in[95 + inPos] << 12 & 1048575 | in[96 + inPos] << 4 & 4095 | in[97 + inPos] >> 4 & 15;
         out[30 + outPos] = in[97 + inPos] << 22 & 67108863 | in[98 + inPos] << 14 & 4194303 | in[99 + inPos] << 6 & 16383 | in[100 + inPos] >> 2 & 63;
         out[31 + outPos] = in[100 + inPos] << 24 & 67108863 | in[101 + inPos] << 16 & 16777215 | in[102 + inPos] << 8 & '\uffff' | in[103 + inPos] & 255;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 18 & 67108863 | in.get(1 + inPos) << 10 & 262143 | in.get(2 + inPos) << 2 & 1023 | in.get(3 + inPos) >> 6 & 3;
         out[1 + outPos] = in.get(3 + inPos) << 20 & 67108863 | in.get(4 + inPos) << 12 & 1048575 | in.get(5 + inPos) << 4 & 4095 | in.get(6 + inPos) >> 4 & 15;
         out[2 + outPos] = in.get(6 + inPos) << 22 & 67108863 | in.get(7 + inPos) << 14 & 4194303 | in.get(8 + inPos) << 6 & 16383 | in.get(9 + inPos) >> 2 & 63;
         out[3 + outPos] = in.get(9 + inPos) << 24 & 67108863 | in.get(10 + inPos) << 16 & 16777215 | in.get(11 + inPos) << 8 & '\uffff' | in.get(12 + inPos) & 255;
         out[4 + outPos] = in.get(13 + inPos) << 18 & 67108863 | in.get(14 + inPos) << 10 & 262143 | in.get(15 + inPos) << 2 & 1023 | in.get(16 + inPos) >> 6 & 3;
         out[5 + outPos] = in.get(16 + inPos) << 20 & 67108863 | in.get(17 + inPos) << 12 & 1048575 | in.get(18 + inPos) << 4 & 4095 | in.get(19 + inPos) >> 4 & 15;
         out[6 + outPos] = in.get(19 + inPos) << 22 & 67108863 | in.get(20 + inPos) << 14 & 4194303 | in.get(21 + inPos) << 6 & 16383 | in.get(22 + inPos) >> 2 & 63;
         out[7 + outPos] = in.get(22 + inPos) << 24 & 67108863 | in.get(23 + inPos) << 16 & 16777215 | in.get(24 + inPos) << 8 & '\uffff' | in.get(25 + inPos) & 255;
         out[8 + outPos] = in.get(26 + inPos) << 18 & 67108863 | in.get(27 + inPos) << 10 & 262143 | in.get(28 + inPos) << 2 & 1023 | in.get(29 + inPos) >> 6 & 3;
         out[9 + outPos] = in.get(29 + inPos) << 20 & 67108863 | in.get(30 + inPos) << 12 & 1048575 | in.get(31 + inPos) << 4 & 4095 | in.get(32 + inPos) >> 4 & 15;
         out[10 + outPos] = in.get(32 + inPos) << 22 & 67108863 | in.get(33 + inPos) << 14 & 4194303 | in.get(34 + inPos) << 6 & 16383 | in.get(35 + inPos) >> 2 & 63;
         out[11 + outPos] = in.get(35 + inPos) << 24 & 67108863 | in.get(36 + inPos) << 16 & 16777215 | in.get(37 + inPos) << 8 & '\uffff' | in.get(38 + inPos) & 255;
         out[12 + outPos] = in.get(39 + inPos) << 18 & 67108863 | in.get(40 + inPos) << 10 & 262143 | in.get(41 + inPos) << 2 & 1023 | in.get(42 + inPos) >> 6 & 3;
         out[13 + outPos] = in.get(42 + inPos) << 20 & 67108863 | in.get(43 + inPos) << 12 & 1048575 | in.get(44 + inPos) << 4 & 4095 | in.get(45 + inPos) >> 4 & 15;
         out[14 + outPos] = in.get(45 + inPos) << 22 & 67108863 | in.get(46 + inPos) << 14 & 4194303 | in.get(47 + inPos) << 6 & 16383 | in.get(48 + inPos) >> 2 & 63;
         out[15 + outPos] = in.get(48 + inPos) << 24 & 67108863 | in.get(49 + inPos) << 16 & 16777215 | in.get(50 + inPos) << 8 & '\uffff' | in.get(51 + inPos) & 255;
         out[16 + outPos] = in.get(52 + inPos) << 18 & 67108863 | in.get(53 + inPos) << 10 & 262143 | in.get(54 + inPos) << 2 & 1023 | in.get(55 + inPos) >> 6 & 3;
         out[17 + outPos] = in.get(55 + inPos) << 20 & 67108863 | in.get(56 + inPos) << 12 & 1048575 | in.get(57 + inPos) << 4 & 4095 | in.get(58 + inPos) >> 4 & 15;
         out[18 + outPos] = in.get(58 + inPos) << 22 & 67108863 | in.get(59 + inPos) << 14 & 4194303 | in.get(60 + inPos) << 6 & 16383 | in.get(61 + inPos) >> 2 & 63;
         out[19 + outPos] = in.get(61 + inPos) << 24 & 67108863 | in.get(62 + inPos) << 16 & 16777215 | in.get(63 + inPos) << 8 & '\uffff' | in.get(64 + inPos) & 255;
         out[20 + outPos] = in.get(65 + inPos) << 18 & 67108863 | in.get(66 + inPos) << 10 & 262143 | in.get(67 + inPos) << 2 & 1023 | in.get(68 + inPos) >> 6 & 3;
         out[21 + outPos] = in.get(68 + inPos) << 20 & 67108863 | in.get(69 + inPos) << 12 & 1048575 | in.get(70 + inPos) << 4 & 4095 | in.get(71 + inPos) >> 4 & 15;
         out[22 + outPos] = in.get(71 + inPos) << 22 & 67108863 | in.get(72 + inPos) << 14 & 4194303 | in.get(73 + inPos) << 6 & 16383 | in.get(74 + inPos) >> 2 & 63;
         out[23 + outPos] = in.get(74 + inPos) << 24 & 67108863 | in.get(75 + inPos) << 16 & 16777215 | in.get(76 + inPos) << 8 & '\uffff' | in.get(77 + inPos) & 255;
         out[24 + outPos] = in.get(78 + inPos) << 18 & 67108863 | in.get(79 + inPos) << 10 & 262143 | in.get(80 + inPos) << 2 & 1023 | in.get(81 + inPos) >> 6 & 3;
         out[25 + outPos] = in.get(81 + inPos) << 20 & 67108863 | in.get(82 + inPos) << 12 & 1048575 | in.get(83 + inPos) << 4 & 4095 | in.get(84 + inPos) >> 4 & 15;
         out[26 + outPos] = in.get(84 + inPos) << 22 & 67108863 | in.get(85 + inPos) << 14 & 4194303 | in.get(86 + inPos) << 6 & 16383 | in.get(87 + inPos) >> 2 & 63;
         out[27 + outPos] = in.get(87 + inPos) << 24 & 67108863 | in.get(88 + inPos) << 16 & 16777215 | in.get(89 + inPos) << 8 & '\uffff' | in.get(90 + inPos) & 255;
         out[28 + outPos] = in.get(91 + inPos) << 18 & 67108863 | in.get(92 + inPos) << 10 & 262143 | in.get(93 + inPos) << 2 & 1023 | in.get(94 + inPos) >> 6 & 3;
         out[29 + outPos] = in.get(94 + inPos) << 20 & 67108863 | in.get(95 + inPos) << 12 & 1048575 | in.get(96 + inPos) << 4 & 4095 | in.get(97 + inPos) >> 4 & 15;
         out[30 + outPos] = in.get(97 + inPos) << 22 & 67108863 | in.get(98 + inPos) << 14 & 4194303 | in.get(99 + inPos) << 6 & 16383 | in.get(100 + inPos) >> 2 & 63;
         out[31 + outPos] = in.get(100 + inPos) << 24 & 67108863 | in.get(101 + inPos) << 16 & 16777215 | in.get(102 + inPos) << 8 & '\uffff' | in.get(103 + inPos) & 255;
      }
   }

   private static final class Packer27 extends BytePacker {
      private Packer27() {
         super(27);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 134217727) >>> 19 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 134217727) >>> 11 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & 134217727) >>> 3 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & 134217727) << 5 | (in[1 + inPos] & 134217727) >>> 22) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 134217727) >>> 14 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & 134217727) >>> 6 & 255);
         out[6 + outPos] = (byte)(((in[1 + inPos] & 134217727) << 2 | (in[2 + inPos] & 134217727) >>> 25) & 255);
         out[7 + outPos] = (byte)((in[2 + inPos] & 134217727) >>> 17 & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & 134217727) >>> 9 & 255);
         out[9 + outPos] = (byte)((in[2 + inPos] & 134217727) >>> 1 & 255);
         out[10 + outPos] = (byte)(((in[2 + inPos] & 134217727) << 7 | (in[3 + inPos] & 134217727) >>> 20) & 255);
         out[11 + outPos] = (byte)((in[3 + inPos] & 134217727) >>> 12 & 255);
         out[12 + outPos] = (byte)((in[3 + inPos] & 134217727) >>> 4 & 255);
         out[13 + outPos] = (byte)(((in[3 + inPos] & 134217727) << 4 | (in[4 + inPos] & 134217727) >>> 23) & 255);
         out[14 + outPos] = (byte)((in[4 + inPos] & 134217727) >>> 15 & 255);
         out[15 + outPos] = (byte)((in[4 + inPos] & 134217727) >>> 7 & 255);
         out[16 + outPos] = (byte)(((in[4 + inPos] & 134217727) << 1 | (in[5 + inPos] & 134217727) >>> 26) & 255);
         out[17 + outPos] = (byte)((in[5 + inPos] & 134217727) >>> 18 & 255);
         out[18 + outPos] = (byte)((in[5 + inPos] & 134217727) >>> 10 & 255);
         out[19 + outPos] = (byte)((in[5 + inPos] & 134217727) >>> 2 & 255);
         out[20 + outPos] = (byte)(((in[5 + inPos] & 134217727) << 6 | (in[6 + inPos] & 134217727) >>> 21) & 255);
         out[21 + outPos] = (byte)((in[6 + inPos] & 134217727) >>> 13 & 255);
         out[22 + outPos] = (byte)((in[6 + inPos] & 134217727) >>> 5 & 255);
         out[23 + outPos] = (byte)(((in[6 + inPos] & 134217727) << 3 | (in[7 + inPos] & 134217727) >>> 24) & 255);
         out[24 + outPos] = (byte)((in[7 + inPos] & 134217727) >>> 16 & 255);
         out[25 + outPos] = (byte)((in[7 + inPos] & 134217727) >>> 8 & 255);
         out[26 + outPos] = (byte)(in[7 + inPos] & 134217727 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 134217727) >>> 19 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 134217727) >>> 11 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & 134217727) >>> 3 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & 134217727) << 5 | (in[1 + inPos] & 134217727) >>> 22) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 134217727) >>> 14 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & 134217727) >>> 6 & 255);
         out[6 + outPos] = (byte)(((in[1 + inPos] & 134217727) << 2 | (in[2 + inPos] & 134217727) >>> 25) & 255);
         out[7 + outPos] = (byte)((in[2 + inPos] & 134217727) >>> 17 & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & 134217727) >>> 9 & 255);
         out[9 + outPos] = (byte)((in[2 + inPos] & 134217727) >>> 1 & 255);
         out[10 + outPos] = (byte)(((in[2 + inPos] & 134217727) << 7 | (in[3 + inPos] & 134217727) >>> 20) & 255);
         out[11 + outPos] = (byte)((in[3 + inPos] & 134217727) >>> 12 & 255);
         out[12 + outPos] = (byte)((in[3 + inPos] & 134217727) >>> 4 & 255);
         out[13 + outPos] = (byte)(((in[3 + inPos] & 134217727) << 4 | (in[4 + inPos] & 134217727) >>> 23) & 255);
         out[14 + outPos] = (byte)((in[4 + inPos] & 134217727) >>> 15 & 255);
         out[15 + outPos] = (byte)((in[4 + inPos] & 134217727) >>> 7 & 255);
         out[16 + outPos] = (byte)(((in[4 + inPos] & 134217727) << 1 | (in[5 + inPos] & 134217727) >>> 26) & 255);
         out[17 + outPos] = (byte)((in[5 + inPos] & 134217727) >>> 18 & 255);
         out[18 + outPos] = (byte)((in[5 + inPos] & 134217727) >>> 10 & 255);
         out[19 + outPos] = (byte)((in[5 + inPos] & 134217727) >>> 2 & 255);
         out[20 + outPos] = (byte)(((in[5 + inPos] & 134217727) << 6 | (in[6 + inPos] & 134217727) >>> 21) & 255);
         out[21 + outPos] = (byte)((in[6 + inPos] & 134217727) >>> 13 & 255);
         out[22 + outPos] = (byte)((in[6 + inPos] & 134217727) >>> 5 & 255);
         out[23 + outPos] = (byte)(((in[6 + inPos] & 134217727) << 3 | (in[7 + inPos] & 134217727) >>> 24) & 255);
         out[24 + outPos] = (byte)((in[7 + inPos] & 134217727) >>> 16 & 255);
         out[25 + outPos] = (byte)((in[7 + inPos] & 134217727) >>> 8 & 255);
         out[26 + outPos] = (byte)(in[7 + inPos] & 134217727 & 255);
         out[27 + outPos] = (byte)((in[8 + inPos] & 134217727) >>> 19 & 255);
         out[28 + outPos] = (byte)((in[8 + inPos] & 134217727) >>> 11 & 255);
         out[29 + outPos] = (byte)((in[8 + inPos] & 134217727) >>> 3 & 255);
         out[30 + outPos] = (byte)(((in[8 + inPos] & 134217727) << 5 | (in[9 + inPos] & 134217727) >>> 22) & 255);
         out[31 + outPos] = (byte)((in[9 + inPos] & 134217727) >>> 14 & 255);
         out[32 + outPos] = (byte)((in[9 + inPos] & 134217727) >>> 6 & 255);
         out[33 + outPos] = (byte)(((in[9 + inPos] & 134217727) << 2 | (in[10 + inPos] & 134217727) >>> 25) & 255);
         out[34 + outPos] = (byte)((in[10 + inPos] & 134217727) >>> 17 & 255);
         out[35 + outPos] = (byte)((in[10 + inPos] & 134217727) >>> 9 & 255);
         out[36 + outPos] = (byte)((in[10 + inPos] & 134217727) >>> 1 & 255);
         out[37 + outPos] = (byte)(((in[10 + inPos] & 134217727) << 7 | (in[11 + inPos] & 134217727) >>> 20) & 255);
         out[38 + outPos] = (byte)((in[11 + inPos] & 134217727) >>> 12 & 255);
         out[39 + outPos] = (byte)((in[11 + inPos] & 134217727) >>> 4 & 255);
         out[40 + outPos] = (byte)(((in[11 + inPos] & 134217727) << 4 | (in[12 + inPos] & 134217727) >>> 23) & 255);
         out[41 + outPos] = (byte)((in[12 + inPos] & 134217727) >>> 15 & 255);
         out[42 + outPos] = (byte)((in[12 + inPos] & 134217727) >>> 7 & 255);
         out[43 + outPos] = (byte)(((in[12 + inPos] & 134217727) << 1 | (in[13 + inPos] & 134217727) >>> 26) & 255);
         out[44 + outPos] = (byte)((in[13 + inPos] & 134217727) >>> 18 & 255);
         out[45 + outPos] = (byte)((in[13 + inPos] & 134217727) >>> 10 & 255);
         out[46 + outPos] = (byte)((in[13 + inPos] & 134217727) >>> 2 & 255);
         out[47 + outPos] = (byte)(((in[13 + inPos] & 134217727) << 6 | (in[14 + inPos] & 134217727) >>> 21) & 255);
         out[48 + outPos] = (byte)((in[14 + inPos] & 134217727) >>> 13 & 255);
         out[49 + outPos] = (byte)((in[14 + inPos] & 134217727) >>> 5 & 255);
         out[50 + outPos] = (byte)(((in[14 + inPos] & 134217727) << 3 | (in[15 + inPos] & 134217727) >>> 24) & 255);
         out[51 + outPos] = (byte)((in[15 + inPos] & 134217727) >>> 16 & 255);
         out[52 + outPos] = (byte)((in[15 + inPos] & 134217727) >>> 8 & 255);
         out[53 + outPos] = (byte)(in[15 + inPos] & 134217727 & 255);
         out[54 + outPos] = (byte)((in[16 + inPos] & 134217727) >>> 19 & 255);
         out[55 + outPos] = (byte)((in[16 + inPos] & 134217727) >>> 11 & 255);
         out[56 + outPos] = (byte)((in[16 + inPos] & 134217727) >>> 3 & 255);
         out[57 + outPos] = (byte)(((in[16 + inPos] & 134217727) << 5 | (in[17 + inPos] & 134217727) >>> 22) & 255);
         out[58 + outPos] = (byte)((in[17 + inPos] & 134217727) >>> 14 & 255);
         out[59 + outPos] = (byte)((in[17 + inPos] & 134217727) >>> 6 & 255);
         out[60 + outPos] = (byte)(((in[17 + inPos] & 134217727) << 2 | (in[18 + inPos] & 134217727) >>> 25) & 255);
         out[61 + outPos] = (byte)((in[18 + inPos] & 134217727) >>> 17 & 255);
         out[62 + outPos] = (byte)((in[18 + inPos] & 134217727) >>> 9 & 255);
         out[63 + outPos] = (byte)((in[18 + inPos] & 134217727) >>> 1 & 255);
         out[64 + outPos] = (byte)(((in[18 + inPos] & 134217727) << 7 | (in[19 + inPos] & 134217727) >>> 20) & 255);
         out[65 + outPos] = (byte)((in[19 + inPos] & 134217727) >>> 12 & 255);
         out[66 + outPos] = (byte)((in[19 + inPos] & 134217727) >>> 4 & 255);
         out[67 + outPos] = (byte)(((in[19 + inPos] & 134217727) << 4 | (in[20 + inPos] & 134217727) >>> 23) & 255);
         out[68 + outPos] = (byte)((in[20 + inPos] & 134217727) >>> 15 & 255);
         out[69 + outPos] = (byte)((in[20 + inPos] & 134217727) >>> 7 & 255);
         out[70 + outPos] = (byte)(((in[20 + inPos] & 134217727) << 1 | (in[21 + inPos] & 134217727) >>> 26) & 255);
         out[71 + outPos] = (byte)((in[21 + inPos] & 134217727) >>> 18 & 255);
         out[72 + outPos] = (byte)((in[21 + inPos] & 134217727) >>> 10 & 255);
         out[73 + outPos] = (byte)((in[21 + inPos] & 134217727) >>> 2 & 255);
         out[74 + outPos] = (byte)(((in[21 + inPos] & 134217727) << 6 | (in[22 + inPos] & 134217727) >>> 21) & 255);
         out[75 + outPos] = (byte)((in[22 + inPos] & 134217727) >>> 13 & 255);
         out[76 + outPos] = (byte)((in[22 + inPos] & 134217727) >>> 5 & 255);
         out[77 + outPos] = (byte)(((in[22 + inPos] & 134217727) << 3 | (in[23 + inPos] & 134217727) >>> 24) & 255);
         out[78 + outPos] = (byte)((in[23 + inPos] & 134217727) >>> 16 & 255);
         out[79 + outPos] = (byte)((in[23 + inPos] & 134217727) >>> 8 & 255);
         out[80 + outPos] = (byte)(in[23 + inPos] & 134217727 & 255);
         out[81 + outPos] = (byte)((in[24 + inPos] & 134217727) >>> 19 & 255);
         out[82 + outPos] = (byte)((in[24 + inPos] & 134217727) >>> 11 & 255);
         out[83 + outPos] = (byte)((in[24 + inPos] & 134217727) >>> 3 & 255);
         out[84 + outPos] = (byte)(((in[24 + inPos] & 134217727) << 5 | (in[25 + inPos] & 134217727) >>> 22) & 255);
         out[85 + outPos] = (byte)((in[25 + inPos] & 134217727) >>> 14 & 255);
         out[86 + outPos] = (byte)((in[25 + inPos] & 134217727) >>> 6 & 255);
         out[87 + outPos] = (byte)(((in[25 + inPos] & 134217727) << 2 | (in[26 + inPos] & 134217727) >>> 25) & 255);
         out[88 + outPos] = (byte)((in[26 + inPos] & 134217727) >>> 17 & 255);
         out[89 + outPos] = (byte)((in[26 + inPos] & 134217727) >>> 9 & 255);
         out[90 + outPos] = (byte)((in[26 + inPos] & 134217727) >>> 1 & 255);
         out[91 + outPos] = (byte)(((in[26 + inPos] & 134217727) << 7 | (in[27 + inPos] & 134217727) >>> 20) & 255);
         out[92 + outPos] = (byte)((in[27 + inPos] & 134217727) >>> 12 & 255);
         out[93 + outPos] = (byte)((in[27 + inPos] & 134217727) >>> 4 & 255);
         out[94 + outPos] = (byte)(((in[27 + inPos] & 134217727) << 4 | (in[28 + inPos] & 134217727) >>> 23) & 255);
         out[95 + outPos] = (byte)((in[28 + inPos] & 134217727) >>> 15 & 255);
         out[96 + outPos] = (byte)((in[28 + inPos] & 134217727) >>> 7 & 255);
         out[97 + outPos] = (byte)(((in[28 + inPos] & 134217727) << 1 | (in[29 + inPos] & 134217727) >>> 26) & 255);
         out[98 + outPos] = (byte)((in[29 + inPos] & 134217727) >>> 18 & 255);
         out[99 + outPos] = (byte)((in[29 + inPos] & 134217727) >>> 10 & 255);
         out[100 + outPos] = (byte)((in[29 + inPos] & 134217727) >>> 2 & 255);
         out[101 + outPos] = (byte)(((in[29 + inPos] & 134217727) << 6 | (in[30 + inPos] & 134217727) >>> 21) & 255);
         out[102 + outPos] = (byte)((in[30 + inPos] & 134217727) >>> 13 & 255);
         out[103 + outPos] = (byte)((in[30 + inPos] & 134217727) >>> 5 & 255);
         out[104 + outPos] = (byte)(((in[30 + inPos] & 134217727) << 3 | (in[31 + inPos] & 134217727) >>> 24) & 255);
         out[105 + outPos] = (byte)((in[31 + inPos] & 134217727) >>> 16 & 255);
         out[106 + outPos] = (byte)((in[31 + inPos] & 134217727) >>> 8 & 255);
         out[107 + outPos] = (byte)(in[31 + inPos] & 134217727 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 19 & 134217727 | in[1 + inPos] << 11 & 524287 | in[2 + inPos] << 3 & 2047 | in[3 + inPos] >> 5 & 7;
         out[1 + outPos] = in[3 + inPos] << 22 & 134217727 | in[4 + inPos] << 14 & 4194303 | in[5 + inPos] << 6 & 16383 | in[6 + inPos] >> 2 & 63;
         out[2 + outPos] = in[6 + inPos] << 25 & 134217727 | in[7 + inPos] << 17 & 33554431 | in[8 + inPos] << 9 & 131071 | in[9 + inPos] << 1 & 511 | in[10 + inPos] >> 7 & 1;
         out[3 + outPos] = in[10 + inPos] << 20 & 134217727 | in[11 + inPos] << 12 & 1048575 | in[12 + inPos] << 4 & 4095 | in[13 + inPos] >> 4 & 15;
         out[4 + outPos] = in[13 + inPos] << 23 & 134217727 | in[14 + inPos] << 15 & 8388607 | in[15 + inPos] << 7 & 32767 | in[16 + inPos] >> 1 & 127;
         out[5 + outPos] = in[16 + inPos] << 26 & 134217727 | in[17 + inPos] << 18 & 67108863 | in[18 + inPos] << 10 & 262143 | in[19 + inPos] << 2 & 1023 | in[20 + inPos] >> 6 & 3;
         out[6 + outPos] = in[20 + inPos] << 21 & 134217727 | in[21 + inPos] << 13 & 2097151 | in[22 + inPos] << 5 & 8191 | in[23 + inPos] >> 3 & 31;
         out[7 + outPos] = in[23 + inPos] << 24 & 134217727 | in[24 + inPos] << 16 & 16777215 | in[25 + inPos] << 8 & '\uffff' | in[26 + inPos] & 255;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 19 & 134217727 | in.get(1 + inPos) << 11 & 524287 | in.get(2 + inPos) << 3 & 2047 | in.get(3 + inPos) >> 5 & 7;
         out[1 + outPos] = in.get(3 + inPos) << 22 & 134217727 | in.get(4 + inPos) << 14 & 4194303 | in.get(5 + inPos) << 6 & 16383 | in.get(6 + inPos) >> 2 & 63;
         out[2 + outPos] = in.get(6 + inPos) << 25 & 134217727 | in.get(7 + inPos) << 17 & 33554431 | in.get(8 + inPos) << 9 & 131071 | in.get(9 + inPos) << 1 & 511 | in.get(10 + inPos) >> 7 & 1;
         out[3 + outPos] = in.get(10 + inPos) << 20 & 134217727 | in.get(11 + inPos) << 12 & 1048575 | in.get(12 + inPos) << 4 & 4095 | in.get(13 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(13 + inPos) << 23 & 134217727 | in.get(14 + inPos) << 15 & 8388607 | in.get(15 + inPos) << 7 & 32767 | in.get(16 + inPos) >> 1 & 127;
         out[5 + outPos] = in.get(16 + inPos) << 26 & 134217727 | in.get(17 + inPos) << 18 & 67108863 | in.get(18 + inPos) << 10 & 262143 | in.get(19 + inPos) << 2 & 1023 | in.get(20 + inPos) >> 6 & 3;
         out[6 + outPos] = in.get(20 + inPos) << 21 & 134217727 | in.get(21 + inPos) << 13 & 2097151 | in.get(22 + inPos) << 5 & 8191 | in.get(23 + inPos) >> 3 & 31;
         out[7 + outPos] = in.get(23 + inPos) << 24 & 134217727 | in.get(24 + inPos) << 16 & 16777215 | in.get(25 + inPos) << 8 & '\uffff' | in.get(26 + inPos) & 255;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 19 & 134217727 | in[1 + inPos] << 11 & 524287 | in[2 + inPos] << 3 & 2047 | in[3 + inPos] >> 5 & 7;
         out[1 + outPos] = in[3 + inPos] << 22 & 134217727 | in[4 + inPos] << 14 & 4194303 | in[5 + inPos] << 6 & 16383 | in[6 + inPos] >> 2 & 63;
         out[2 + outPos] = in[6 + inPos] << 25 & 134217727 | in[7 + inPos] << 17 & 33554431 | in[8 + inPos] << 9 & 131071 | in[9 + inPos] << 1 & 511 | in[10 + inPos] >> 7 & 1;
         out[3 + outPos] = in[10 + inPos] << 20 & 134217727 | in[11 + inPos] << 12 & 1048575 | in[12 + inPos] << 4 & 4095 | in[13 + inPos] >> 4 & 15;
         out[4 + outPos] = in[13 + inPos] << 23 & 134217727 | in[14 + inPos] << 15 & 8388607 | in[15 + inPos] << 7 & 32767 | in[16 + inPos] >> 1 & 127;
         out[5 + outPos] = in[16 + inPos] << 26 & 134217727 | in[17 + inPos] << 18 & 67108863 | in[18 + inPos] << 10 & 262143 | in[19 + inPos] << 2 & 1023 | in[20 + inPos] >> 6 & 3;
         out[6 + outPos] = in[20 + inPos] << 21 & 134217727 | in[21 + inPos] << 13 & 2097151 | in[22 + inPos] << 5 & 8191 | in[23 + inPos] >> 3 & 31;
         out[7 + outPos] = in[23 + inPos] << 24 & 134217727 | in[24 + inPos] << 16 & 16777215 | in[25 + inPos] << 8 & '\uffff' | in[26 + inPos] & 255;
         out[8 + outPos] = in[27 + inPos] << 19 & 134217727 | in[28 + inPos] << 11 & 524287 | in[29 + inPos] << 3 & 2047 | in[30 + inPos] >> 5 & 7;
         out[9 + outPos] = in[30 + inPos] << 22 & 134217727 | in[31 + inPos] << 14 & 4194303 | in[32 + inPos] << 6 & 16383 | in[33 + inPos] >> 2 & 63;
         out[10 + outPos] = in[33 + inPos] << 25 & 134217727 | in[34 + inPos] << 17 & 33554431 | in[35 + inPos] << 9 & 131071 | in[36 + inPos] << 1 & 511 | in[37 + inPos] >> 7 & 1;
         out[11 + outPos] = in[37 + inPos] << 20 & 134217727 | in[38 + inPos] << 12 & 1048575 | in[39 + inPos] << 4 & 4095 | in[40 + inPos] >> 4 & 15;
         out[12 + outPos] = in[40 + inPos] << 23 & 134217727 | in[41 + inPos] << 15 & 8388607 | in[42 + inPos] << 7 & 32767 | in[43 + inPos] >> 1 & 127;
         out[13 + outPos] = in[43 + inPos] << 26 & 134217727 | in[44 + inPos] << 18 & 67108863 | in[45 + inPos] << 10 & 262143 | in[46 + inPos] << 2 & 1023 | in[47 + inPos] >> 6 & 3;
         out[14 + outPos] = in[47 + inPos] << 21 & 134217727 | in[48 + inPos] << 13 & 2097151 | in[49 + inPos] << 5 & 8191 | in[50 + inPos] >> 3 & 31;
         out[15 + outPos] = in[50 + inPos] << 24 & 134217727 | in[51 + inPos] << 16 & 16777215 | in[52 + inPos] << 8 & '\uffff' | in[53 + inPos] & 255;
         out[16 + outPos] = in[54 + inPos] << 19 & 134217727 | in[55 + inPos] << 11 & 524287 | in[56 + inPos] << 3 & 2047 | in[57 + inPos] >> 5 & 7;
         out[17 + outPos] = in[57 + inPos] << 22 & 134217727 | in[58 + inPos] << 14 & 4194303 | in[59 + inPos] << 6 & 16383 | in[60 + inPos] >> 2 & 63;
         out[18 + outPos] = in[60 + inPos] << 25 & 134217727 | in[61 + inPos] << 17 & 33554431 | in[62 + inPos] << 9 & 131071 | in[63 + inPos] << 1 & 511 | in[64 + inPos] >> 7 & 1;
         out[19 + outPos] = in[64 + inPos] << 20 & 134217727 | in[65 + inPos] << 12 & 1048575 | in[66 + inPos] << 4 & 4095 | in[67 + inPos] >> 4 & 15;
         out[20 + outPos] = in[67 + inPos] << 23 & 134217727 | in[68 + inPos] << 15 & 8388607 | in[69 + inPos] << 7 & 32767 | in[70 + inPos] >> 1 & 127;
         out[21 + outPos] = in[70 + inPos] << 26 & 134217727 | in[71 + inPos] << 18 & 67108863 | in[72 + inPos] << 10 & 262143 | in[73 + inPos] << 2 & 1023 | in[74 + inPos] >> 6 & 3;
         out[22 + outPos] = in[74 + inPos] << 21 & 134217727 | in[75 + inPos] << 13 & 2097151 | in[76 + inPos] << 5 & 8191 | in[77 + inPos] >> 3 & 31;
         out[23 + outPos] = in[77 + inPos] << 24 & 134217727 | in[78 + inPos] << 16 & 16777215 | in[79 + inPos] << 8 & '\uffff' | in[80 + inPos] & 255;
         out[24 + outPos] = in[81 + inPos] << 19 & 134217727 | in[82 + inPos] << 11 & 524287 | in[83 + inPos] << 3 & 2047 | in[84 + inPos] >> 5 & 7;
         out[25 + outPos] = in[84 + inPos] << 22 & 134217727 | in[85 + inPos] << 14 & 4194303 | in[86 + inPos] << 6 & 16383 | in[87 + inPos] >> 2 & 63;
         out[26 + outPos] = in[87 + inPos] << 25 & 134217727 | in[88 + inPos] << 17 & 33554431 | in[89 + inPos] << 9 & 131071 | in[90 + inPos] << 1 & 511 | in[91 + inPos] >> 7 & 1;
         out[27 + outPos] = in[91 + inPos] << 20 & 134217727 | in[92 + inPos] << 12 & 1048575 | in[93 + inPos] << 4 & 4095 | in[94 + inPos] >> 4 & 15;
         out[28 + outPos] = in[94 + inPos] << 23 & 134217727 | in[95 + inPos] << 15 & 8388607 | in[96 + inPos] << 7 & 32767 | in[97 + inPos] >> 1 & 127;
         out[29 + outPos] = in[97 + inPos] << 26 & 134217727 | in[98 + inPos] << 18 & 67108863 | in[99 + inPos] << 10 & 262143 | in[100 + inPos] << 2 & 1023 | in[101 + inPos] >> 6 & 3;
         out[30 + outPos] = in[101 + inPos] << 21 & 134217727 | in[102 + inPos] << 13 & 2097151 | in[103 + inPos] << 5 & 8191 | in[104 + inPos] >> 3 & 31;
         out[31 + outPos] = in[104 + inPos] << 24 & 134217727 | in[105 + inPos] << 16 & 16777215 | in[106 + inPos] << 8 & '\uffff' | in[107 + inPos] & 255;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 19 & 134217727 | in.get(1 + inPos) << 11 & 524287 | in.get(2 + inPos) << 3 & 2047 | in.get(3 + inPos) >> 5 & 7;
         out[1 + outPos] = in.get(3 + inPos) << 22 & 134217727 | in.get(4 + inPos) << 14 & 4194303 | in.get(5 + inPos) << 6 & 16383 | in.get(6 + inPos) >> 2 & 63;
         out[2 + outPos] = in.get(6 + inPos) << 25 & 134217727 | in.get(7 + inPos) << 17 & 33554431 | in.get(8 + inPos) << 9 & 131071 | in.get(9 + inPos) << 1 & 511 | in.get(10 + inPos) >> 7 & 1;
         out[3 + outPos] = in.get(10 + inPos) << 20 & 134217727 | in.get(11 + inPos) << 12 & 1048575 | in.get(12 + inPos) << 4 & 4095 | in.get(13 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(13 + inPos) << 23 & 134217727 | in.get(14 + inPos) << 15 & 8388607 | in.get(15 + inPos) << 7 & 32767 | in.get(16 + inPos) >> 1 & 127;
         out[5 + outPos] = in.get(16 + inPos) << 26 & 134217727 | in.get(17 + inPos) << 18 & 67108863 | in.get(18 + inPos) << 10 & 262143 | in.get(19 + inPos) << 2 & 1023 | in.get(20 + inPos) >> 6 & 3;
         out[6 + outPos] = in.get(20 + inPos) << 21 & 134217727 | in.get(21 + inPos) << 13 & 2097151 | in.get(22 + inPos) << 5 & 8191 | in.get(23 + inPos) >> 3 & 31;
         out[7 + outPos] = in.get(23 + inPos) << 24 & 134217727 | in.get(24 + inPos) << 16 & 16777215 | in.get(25 + inPos) << 8 & '\uffff' | in.get(26 + inPos) & 255;
         out[8 + outPos] = in.get(27 + inPos) << 19 & 134217727 | in.get(28 + inPos) << 11 & 524287 | in.get(29 + inPos) << 3 & 2047 | in.get(30 + inPos) >> 5 & 7;
         out[9 + outPos] = in.get(30 + inPos) << 22 & 134217727 | in.get(31 + inPos) << 14 & 4194303 | in.get(32 + inPos) << 6 & 16383 | in.get(33 + inPos) >> 2 & 63;
         out[10 + outPos] = in.get(33 + inPos) << 25 & 134217727 | in.get(34 + inPos) << 17 & 33554431 | in.get(35 + inPos) << 9 & 131071 | in.get(36 + inPos) << 1 & 511 | in.get(37 + inPos) >> 7 & 1;
         out[11 + outPos] = in.get(37 + inPos) << 20 & 134217727 | in.get(38 + inPos) << 12 & 1048575 | in.get(39 + inPos) << 4 & 4095 | in.get(40 + inPos) >> 4 & 15;
         out[12 + outPos] = in.get(40 + inPos) << 23 & 134217727 | in.get(41 + inPos) << 15 & 8388607 | in.get(42 + inPos) << 7 & 32767 | in.get(43 + inPos) >> 1 & 127;
         out[13 + outPos] = in.get(43 + inPos) << 26 & 134217727 | in.get(44 + inPos) << 18 & 67108863 | in.get(45 + inPos) << 10 & 262143 | in.get(46 + inPos) << 2 & 1023 | in.get(47 + inPos) >> 6 & 3;
         out[14 + outPos] = in.get(47 + inPos) << 21 & 134217727 | in.get(48 + inPos) << 13 & 2097151 | in.get(49 + inPos) << 5 & 8191 | in.get(50 + inPos) >> 3 & 31;
         out[15 + outPos] = in.get(50 + inPos) << 24 & 134217727 | in.get(51 + inPos) << 16 & 16777215 | in.get(52 + inPos) << 8 & '\uffff' | in.get(53 + inPos) & 255;
         out[16 + outPos] = in.get(54 + inPos) << 19 & 134217727 | in.get(55 + inPos) << 11 & 524287 | in.get(56 + inPos) << 3 & 2047 | in.get(57 + inPos) >> 5 & 7;
         out[17 + outPos] = in.get(57 + inPos) << 22 & 134217727 | in.get(58 + inPos) << 14 & 4194303 | in.get(59 + inPos) << 6 & 16383 | in.get(60 + inPos) >> 2 & 63;
         out[18 + outPos] = in.get(60 + inPos) << 25 & 134217727 | in.get(61 + inPos) << 17 & 33554431 | in.get(62 + inPos) << 9 & 131071 | in.get(63 + inPos) << 1 & 511 | in.get(64 + inPos) >> 7 & 1;
         out[19 + outPos] = in.get(64 + inPos) << 20 & 134217727 | in.get(65 + inPos) << 12 & 1048575 | in.get(66 + inPos) << 4 & 4095 | in.get(67 + inPos) >> 4 & 15;
         out[20 + outPos] = in.get(67 + inPos) << 23 & 134217727 | in.get(68 + inPos) << 15 & 8388607 | in.get(69 + inPos) << 7 & 32767 | in.get(70 + inPos) >> 1 & 127;
         out[21 + outPos] = in.get(70 + inPos) << 26 & 134217727 | in.get(71 + inPos) << 18 & 67108863 | in.get(72 + inPos) << 10 & 262143 | in.get(73 + inPos) << 2 & 1023 | in.get(74 + inPos) >> 6 & 3;
         out[22 + outPos] = in.get(74 + inPos) << 21 & 134217727 | in.get(75 + inPos) << 13 & 2097151 | in.get(76 + inPos) << 5 & 8191 | in.get(77 + inPos) >> 3 & 31;
         out[23 + outPos] = in.get(77 + inPos) << 24 & 134217727 | in.get(78 + inPos) << 16 & 16777215 | in.get(79 + inPos) << 8 & '\uffff' | in.get(80 + inPos) & 255;
         out[24 + outPos] = in.get(81 + inPos) << 19 & 134217727 | in.get(82 + inPos) << 11 & 524287 | in.get(83 + inPos) << 3 & 2047 | in.get(84 + inPos) >> 5 & 7;
         out[25 + outPos] = in.get(84 + inPos) << 22 & 134217727 | in.get(85 + inPos) << 14 & 4194303 | in.get(86 + inPos) << 6 & 16383 | in.get(87 + inPos) >> 2 & 63;
         out[26 + outPos] = in.get(87 + inPos) << 25 & 134217727 | in.get(88 + inPos) << 17 & 33554431 | in.get(89 + inPos) << 9 & 131071 | in.get(90 + inPos) << 1 & 511 | in.get(91 + inPos) >> 7 & 1;
         out[27 + outPos] = in.get(91 + inPos) << 20 & 134217727 | in.get(92 + inPos) << 12 & 1048575 | in.get(93 + inPos) << 4 & 4095 | in.get(94 + inPos) >> 4 & 15;
         out[28 + outPos] = in.get(94 + inPos) << 23 & 134217727 | in.get(95 + inPos) << 15 & 8388607 | in.get(96 + inPos) << 7 & 32767 | in.get(97 + inPos) >> 1 & 127;
         out[29 + outPos] = in.get(97 + inPos) << 26 & 134217727 | in.get(98 + inPos) << 18 & 67108863 | in.get(99 + inPos) << 10 & 262143 | in.get(100 + inPos) << 2 & 1023 | in.get(101 + inPos) >> 6 & 3;
         out[30 + outPos] = in.get(101 + inPos) << 21 & 134217727 | in.get(102 + inPos) << 13 & 2097151 | in.get(103 + inPos) << 5 & 8191 | in.get(104 + inPos) >> 3 & 31;
         out[31 + outPos] = in.get(104 + inPos) << 24 & 134217727 | in.get(105 + inPos) << 16 & 16777215 | in.get(106 + inPos) << 8 & '\uffff' | in.get(107 + inPos) & 255;
      }
   }

   private static final class Packer28 extends BytePacker {
      private Packer28() {
         super(28);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 268435455) >>> 20 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 268435455) >>> 12 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & 268435455) >>> 4 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & 268435455) << 4 | (in[1 + inPos] & 268435455) >>> 24) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 268435455) >>> 16 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & 268435455) >>> 8 & 255);
         out[6 + outPos] = (byte)(in[1 + inPos] & 268435455 & 255);
         out[7 + outPos] = (byte)((in[2 + inPos] & 268435455) >>> 20 & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & 268435455) >>> 12 & 255);
         out[9 + outPos] = (byte)((in[2 + inPos] & 268435455) >>> 4 & 255);
         out[10 + outPos] = (byte)(((in[2 + inPos] & 268435455) << 4 | (in[3 + inPos] & 268435455) >>> 24) & 255);
         out[11 + outPos] = (byte)((in[3 + inPos] & 268435455) >>> 16 & 255);
         out[12 + outPos] = (byte)((in[3 + inPos] & 268435455) >>> 8 & 255);
         out[13 + outPos] = (byte)(in[3 + inPos] & 268435455 & 255);
         out[14 + outPos] = (byte)((in[4 + inPos] & 268435455) >>> 20 & 255);
         out[15 + outPos] = (byte)((in[4 + inPos] & 268435455) >>> 12 & 255);
         out[16 + outPos] = (byte)((in[4 + inPos] & 268435455) >>> 4 & 255);
         out[17 + outPos] = (byte)(((in[4 + inPos] & 268435455) << 4 | (in[5 + inPos] & 268435455) >>> 24) & 255);
         out[18 + outPos] = (byte)((in[5 + inPos] & 268435455) >>> 16 & 255);
         out[19 + outPos] = (byte)((in[5 + inPos] & 268435455) >>> 8 & 255);
         out[20 + outPos] = (byte)(in[5 + inPos] & 268435455 & 255);
         out[21 + outPos] = (byte)((in[6 + inPos] & 268435455) >>> 20 & 255);
         out[22 + outPos] = (byte)((in[6 + inPos] & 268435455) >>> 12 & 255);
         out[23 + outPos] = (byte)((in[6 + inPos] & 268435455) >>> 4 & 255);
         out[24 + outPos] = (byte)(((in[6 + inPos] & 268435455) << 4 | (in[7 + inPos] & 268435455) >>> 24) & 255);
         out[25 + outPos] = (byte)((in[7 + inPos] & 268435455) >>> 16 & 255);
         out[26 + outPos] = (byte)((in[7 + inPos] & 268435455) >>> 8 & 255);
         out[27 + outPos] = (byte)(in[7 + inPos] & 268435455 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 268435455) >>> 20 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 268435455) >>> 12 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & 268435455) >>> 4 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & 268435455) << 4 | (in[1 + inPos] & 268435455) >>> 24) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 268435455) >>> 16 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & 268435455) >>> 8 & 255);
         out[6 + outPos] = (byte)(in[1 + inPos] & 268435455 & 255);
         out[7 + outPos] = (byte)((in[2 + inPos] & 268435455) >>> 20 & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & 268435455) >>> 12 & 255);
         out[9 + outPos] = (byte)((in[2 + inPos] & 268435455) >>> 4 & 255);
         out[10 + outPos] = (byte)(((in[2 + inPos] & 268435455) << 4 | (in[3 + inPos] & 268435455) >>> 24) & 255);
         out[11 + outPos] = (byte)((in[3 + inPos] & 268435455) >>> 16 & 255);
         out[12 + outPos] = (byte)((in[3 + inPos] & 268435455) >>> 8 & 255);
         out[13 + outPos] = (byte)(in[3 + inPos] & 268435455 & 255);
         out[14 + outPos] = (byte)((in[4 + inPos] & 268435455) >>> 20 & 255);
         out[15 + outPos] = (byte)((in[4 + inPos] & 268435455) >>> 12 & 255);
         out[16 + outPos] = (byte)((in[4 + inPos] & 268435455) >>> 4 & 255);
         out[17 + outPos] = (byte)(((in[4 + inPos] & 268435455) << 4 | (in[5 + inPos] & 268435455) >>> 24) & 255);
         out[18 + outPos] = (byte)((in[5 + inPos] & 268435455) >>> 16 & 255);
         out[19 + outPos] = (byte)((in[5 + inPos] & 268435455) >>> 8 & 255);
         out[20 + outPos] = (byte)(in[5 + inPos] & 268435455 & 255);
         out[21 + outPos] = (byte)((in[6 + inPos] & 268435455) >>> 20 & 255);
         out[22 + outPos] = (byte)((in[6 + inPos] & 268435455) >>> 12 & 255);
         out[23 + outPos] = (byte)((in[6 + inPos] & 268435455) >>> 4 & 255);
         out[24 + outPos] = (byte)(((in[6 + inPos] & 268435455) << 4 | (in[7 + inPos] & 268435455) >>> 24) & 255);
         out[25 + outPos] = (byte)((in[7 + inPos] & 268435455) >>> 16 & 255);
         out[26 + outPos] = (byte)((in[7 + inPos] & 268435455) >>> 8 & 255);
         out[27 + outPos] = (byte)(in[7 + inPos] & 268435455 & 255);
         out[28 + outPos] = (byte)((in[8 + inPos] & 268435455) >>> 20 & 255);
         out[29 + outPos] = (byte)((in[8 + inPos] & 268435455) >>> 12 & 255);
         out[30 + outPos] = (byte)((in[8 + inPos] & 268435455) >>> 4 & 255);
         out[31 + outPos] = (byte)(((in[8 + inPos] & 268435455) << 4 | (in[9 + inPos] & 268435455) >>> 24) & 255);
         out[32 + outPos] = (byte)((in[9 + inPos] & 268435455) >>> 16 & 255);
         out[33 + outPos] = (byte)((in[9 + inPos] & 268435455) >>> 8 & 255);
         out[34 + outPos] = (byte)(in[9 + inPos] & 268435455 & 255);
         out[35 + outPos] = (byte)((in[10 + inPos] & 268435455) >>> 20 & 255);
         out[36 + outPos] = (byte)((in[10 + inPos] & 268435455) >>> 12 & 255);
         out[37 + outPos] = (byte)((in[10 + inPos] & 268435455) >>> 4 & 255);
         out[38 + outPos] = (byte)(((in[10 + inPos] & 268435455) << 4 | (in[11 + inPos] & 268435455) >>> 24) & 255);
         out[39 + outPos] = (byte)((in[11 + inPos] & 268435455) >>> 16 & 255);
         out[40 + outPos] = (byte)((in[11 + inPos] & 268435455) >>> 8 & 255);
         out[41 + outPos] = (byte)(in[11 + inPos] & 268435455 & 255);
         out[42 + outPos] = (byte)((in[12 + inPos] & 268435455) >>> 20 & 255);
         out[43 + outPos] = (byte)((in[12 + inPos] & 268435455) >>> 12 & 255);
         out[44 + outPos] = (byte)((in[12 + inPos] & 268435455) >>> 4 & 255);
         out[45 + outPos] = (byte)(((in[12 + inPos] & 268435455) << 4 | (in[13 + inPos] & 268435455) >>> 24) & 255);
         out[46 + outPos] = (byte)((in[13 + inPos] & 268435455) >>> 16 & 255);
         out[47 + outPos] = (byte)((in[13 + inPos] & 268435455) >>> 8 & 255);
         out[48 + outPos] = (byte)(in[13 + inPos] & 268435455 & 255);
         out[49 + outPos] = (byte)((in[14 + inPos] & 268435455) >>> 20 & 255);
         out[50 + outPos] = (byte)((in[14 + inPos] & 268435455) >>> 12 & 255);
         out[51 + outPos] = (byte)((in[14 + inPos] & 268435455) >>> 4 & 255);
         out[52 + outPos] = (byte)(((in[14 + inPos] & 268435455) << 4 | (in[15 + inPos] & 268435455) >>> 24) & 255);
         out[53 + outPos] = (byte)((in[15 + inPos] & 268435455) >>> 16 & 255);
         out[54 + outPos] = (byte)((in[15 + inPos] & 268435455) >>> 8 & 255);
         out[55 + outPos] = (byte)(in[15 + inPos] & 268435455 & 255);
         out[56 + outPos] = (byte)((in[16 + inPos] & 268435455) >>> 20 & 255);
         out[57 + outPos] = (byte)((in[16 + inPos] & 268435455) >>> 12 & 255);
         out[58 + outPos] = (byte)((in[16 + inPos] & 268435455) >>> 4 & 255);
         out[59 + outPos] = (byte)(((in[16 + inPos] & 268435455) << 4 | (in[17 + inPos] & 268435455) >>> 24) & 255);
         out[60 + outPos] = (byte)((in[17 + inPos] & 268435455) >>> 16 & 255);
         out[61 + outPos] = (byte)((in[17 + inPos] & 268435455) >>> 8 & 255);
         out[62 + outPos] = (byte)(in[17 + inPos] & 268435455 & 255);
         out[63 + outPos] = (byte)((in[18 + inPos] & 268435455) >>> 20 & 255);
         out[64 + outPos] = (byte)((in[18 + inPos] & 268435455) >>> 12 & 255);
         out[65 + outPos] = (byte)((in[18 + inPos] & 268435455) >>> 4 & 255);
         out[66 + outPos] = (byte)(((in[18 + inPos] & 268435455) << 4 | (in[19 + inPos] & 268435455) >>> 24) & 255);
         out[67 + outPos] = (byte)((in[19 + inPos] & 268435455) >>> 16 & 255);
         out[68 + outPos] = (byte)((in[19 + inPos] & 268435455) >>> 8 & 255);
         out[69 + outPos] = (byte)(in[19 + inPos] & 268435455 & 255);
         out[70 + outPos] = (byte)((in[20 + inPos] & 268435455) >>> 20 & 255);
         out[71 + outPos] = (byte)((in[20 + inPos] & 268435455) >>> 12 & 255);
         out[72 + outPos] = (byte)((in[20 + inPos] & 268435455) >>> 4 & 255);
         out[73 + outPos] = (byte)(((in[20 + inPos] & 268435455) << 4 | (in[21 + inPos] & 268435455) >>> 24) & 255);
         out[74 + outPos] = (byte)((in[21 + inPos] & 268435455) >>> 16 & 255);
         out[75 + outPos] = (byte)((in[21 + inPos] & 268435455) >>> 8 & 255);
         out[76 + outPos] = (byte)(in[21 + inPos] & 268435455 & 255);
         out[77 + outPos] = (byte)((in[22 + inPos] & 268435455) >>> 20 & 255);
         out[78 + outPos] = (byte)((in[22 + inPos] & 268435455) >>> 12 & 255);
         out[79 + outPos] = (byte)((in[22 + inPos] & 268435455) >>> 4 & 255);
         out[80 + outPos] = (byte)(((in[22 + inPos] & 268435455) << 4 | (in[23 + inPos] & 268435455) >>> 24) & 255);
         out[81 + outPos] = (byte)((in[23 + inPos] & 268435455) >>> 16 & 255);
         out[82 + outPos] = (byte)((in[23 + inPos] & 268435455) >>> 8 & 255);
         out[83 + outPos] = (byte)(in[23 + inPos] & 268435455 & 255);
         out[84 + outPos] = (byte)((in[24 + inPos] & 268435455) >>> 20 & 255);
         out[85 + outPos] = (byte)((in[24 + inPos] & 268435455) >>> 12 & 255);
         out[86 + outPos] = (byte)((in[24 + inPos] & 268435455) >>> 4 & 255);
         out[87 + outPos] = (byte)(((in[24 + inPos] & 268435455) << 4 | (in[25 + inPos] & 268435455) >>> 24) & 255);
         out[88 + outPos] = (byte)((in[25 + inPos] & 268435455) >>> 16 & 255);
         out[89 + outPos] = (byte)((in[25 + inPos] & 268435455) >>> 8 & 255);
         out[90 + outPos] = (byte)(in[25 + inPos] & 268435455 & 255);
         out[91 + outPos] = (byte)((in[26 + inPos] & 268435455) >>> 20 & 255);
         out[92 + outPos] = (byte)((in[26 + inPos] & 268435455) >>> 12 & 255);
         out[93 + outPos] = (byte)((in[26 + inPos] & 268435455) >>> 4 & 255);
         out[94 + outPos] = (byte)(((in[26 + inPos] & 268435455) << 4 | (in[27 + inPos] & 268435455) >>> 24) & 255);
         out[95 + outPos] = (byte)((in[27 + inPos] & 268435455) >>> 16 & 255);
         out[96 + outPos] = (byte)((in[27 + inPos] & 268435455) >>> 8 & 255);
         out[97 + outPos] = (byte)(in[27 + inPos] & 268435455 & 255);
         out[98 + outPos] = (byte)((in[28 + inPos] & 268435455) >>> 20 & 255);
         out[99 + outPos] = (byte)((in[28 + inPos] & 268435455) >>> 12 & 255);
         out[100 + outPos] = (byte)((in[28 + inPos] & 268435455) >>> 4 & 255);
         out[101 + outPos] = (byte)(((in[28 + inPos] & 268435455) << 4 | (in[29 + inPos] & 268435455) >>> 24) & 255);
         out[102 + outPos] = (byte)((in[29 + inPos] & 268435455) >>> 16 & 255);
         out[103 + outPos] = (byte)((in[29 + inPos] & 268435455) >>> 8 & 255);
         out[104 + outPos] = (byte)(in[29 + inPos] & 268435455 & 255);
         out[105 + outPos] = (byte)((in[30 + inPos] & 268435455) >>> 20 & 255);
         out[106 + outPos] = (byte)((in[30 + inPos] & 268435455) >>> 12 & 255);
         out[107 + outPos] = (byte)((in[30 + inPos] & 268435455) >>> 4 & 255);
         out[108 + outPos] = (byte)(((in[30 + inPos] & 268435455) << 4 | (in[31 + inPos] & 268435455) >>> 24) & 255);
         out[109 + outPos] = (byte)((in[31 + inPos] & 268435455) >>> 16 & 255);
         out[110 + outPos] = (byte)((in[31 + inPos] & 268435455) >>> 8 & 255);
         out[111 + outPos] = (byte)(in[31 + inPos] & 268435455 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 20 & 268435455 | in[1 + inPos] << 12 & 1048575 | in[2 + inPos] << 4 & 4095 | in[3 + inPos] >> 4 & 15;
         out[1 + outPos] = in[3 + inPos] << 24 & 268435455 | in[4 + inPos] << 16 & 16777215 | in[5 + inPos] << 8 & '\uffff' | in[6 + inPos] & 255;
         out[2 + outPos] = in[7 + inPos] << 20 & 268435455 | in[8 + inPos] << 12 & 1048575 | in[9 + inPos] << 4 & 4095 | in[10 + inPos] >> 4 & 15;
         out[3 + outPos] = in[10 + inPos] << 24 & 268435455 | in[11 + inPos] << 16 & 16777215 | in[12 + inPos] << 8 & '\uffff' | in[13 + inPos] & 255;
         out[4 + outPos] = in[14 + inPos] << 20 & 268435455 | in[15 + inPos] << 12 & 1048575 | in[16 + inPos] << 4 & 4095 | in[17 + inPos] >> 4 & 15;
         out[5 + outPos] = in[17 + inPos] << 24 & 268435455 | in[18 + inPos] << 16 & 16777215 | in[19 + inPos] << 8 & '\uffff' | in[20 + inPos] & 255;
         out[6 + outPos] = in[21 + inPos] << 20 & 268435455 | in[22 + inPos] << 12 & 1048575 | in[23 + inPos] << 4 & 4095 | in[24 + inPos] >> 4 & 15;
         out[7 + outPos] = in[24 + inPos] << 24 & 268435455 | in[25 + inPos] << 16 & 16777215 | in[26 + inPos] << 8 & '\uffff' | in[27 + inPos] & 255;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 20 & 268435455 | in.get(1 + inPos) << 12 & 1048575 | in.get(2 + inPos) << 4 & 4095 | in.get(3 + inPos) >> 4 & 15;
         out[1 + outPos] = in.get(3 + inPos) << 24 & 268435455 | in.get(4 + inPos) << 16 & 16777215 | in.get(5 + inPos) << 8 & '\uffff' | in.get(6 + inPos) & 255;
         out[2 + outPos] = in.get(7 + inPos) << 20 & 268435455 | in.get(8 + inPos) << 12 & 1048575 | in.get(9 + inPos) << 4 & 4095 | in.get(10 + inPos) >> 4 & 15;
         out[3 + outPos] = in.get(10 + inPos) << 24 & 268435455 | in.get(11 + inPos) << 16 & 16777215 | in.get(12 + inPos) << 8 & '\uffff' | in.get(13 + inPos) & 255;
         out[4 + outPos] = in.get(14 + inPos) << 20 & 268435455 | in.get(15 + inPos) << 12 & 1048575 | in.get(16 + inPos) << 4 & 4095 | in.get(17 + inPos) >> 4 & 15;
         out[5 + outPos] = in.get(17 + inPos) << 24 & 268435455 | in.get(18 + inPos) << 16 & 16777215 | in.get(19 + inPos) << 8 & '\uffff' | in.get(20 + inPos) & 255;
         out[6 + outPos] = in.get(21 + inPos) << 20 & 268435455 | in.get(22 + inPos) << 12 & 1048575 | in.get(23 + inPos) << 4 & 4095 | in.get(24 + inPos) >> 4 & 15;
         out[7 + outPos] = in.get(24 + inPos) << 24 & 268435455 | in.get(25 + inPos) << 16 & 16777215 | in.get(26 + inPos) << 8 & '\uffff' | in.get(27 + inPos) & 255;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 20 & 268435455 | in[1 + inPos] << 12 & 1048575 | in[2 + inPos] << 4 & 4095 | in[3 + inPos] >> 4 & 15;
         out[1 + outPos] = in[3 + inPos] << 24 & 268435455 | in[4 + inPos] << 16 & 16777215 | in[5 + inPos] << 8 & '\uffff' | in[6 + inPos] & 255;
         out[2 + outPos] = in[7 + inPos] << 20 & 268435455 | in[8 + inPos] << 12 & 1048575 | in[9 + inPos] << 4 & 4095 | in[10 + inPos] >> 4 & 15;
         out[3 + outPos] = in[10 + inPos] << 24 & 268435455 | in[11 + inPos] << 16 & 16777215 | in[12 + inPos] << 8 & '\uffff' | in[13 + inPos] & 255;
         out[4 + outPos] = in[14 + inPos] << 20 & 268435455 | in[15 + inPos] << 12 & 1048575 | in[16 + inPos] << 4 & 4095 | in[17 + inPos] >> 4 & 15;
         out[5 + outPos] = in[17 + inPos] << 24 & 268435455 | in[18 + inPos] << 16 & 16777215 | in[19 + inPos] << 8 & '\uffff' | in[20 + inPos] & 255;
         out[6 + outPos] = in[21 + inPos] << 20 & 268435455 | in[22 + inPos] << 12 & 1048575 | in[23 + inPos] << 4 & 4095 | in[24 + inPos] >> 4 & 15;
         out[7 + outPos] = in[24 + inPos] << 24 & 268435455 | in[25 + inPos] << 16 & 16777215 | in[26 + inPos] << 8 & '\uffff' | in[27 + inPos] & 255;
         out[8 + outPos] = in[28 + inPos] << 20 & 268435455 | in[29 + inPos] << 12 & 1048575 | in[30 + inPos] << 4 & 4095 | in[31 + inPos] >> 4 & 15;
         out[9 + outPos] = in[31 + inPos] << 24 & 268435455 | in[32 + inPos] << 16 & 16777215 | in[33 + inPos] << 8 & '\uffff' | in[34 + inPos] & 255;
         out[10 + outPos] = in[35 + inPos] << 20 & 268435455 | in[36 + inPos] << 12 & 1048575 | in[37 + inPos] << 4 & 4095 | in[38 + inPos] >> 4 & 15;
         out[11 + outPos] = in[38 + inPos] << 24 & 268435455 | in[39 + inPos] << 16 & 16777215 | in[40 + inPos] << 8 & '\uffff' | in[41 + inPos] & 255;
         out[12 + outPos] = in[42 + inPos] << 20 & 268435455 | in[43 + inPos] << 12 & 1048575 | in[44 + inPos] << 4 & 4095 | in[45 + inPos] >> 4 & 15;
         out[13 + outPos] = in[45 + inPos] << 24 & 268435455 | in[46 + inPos] << 16 & 16777215 | in[47 + inPos] << 8 & '\uffff' | in[48 + inPos] & 255;
         out[14 + outPos] = in[49 + inPos] << 20 & 268435455 | in[50 + inPos] << 12 & 1048575 | in[51 + inPos] << 4 & 4095 | in[52 + inPos] >> 4 & 15;
         out[15 + outPos] = in[52 + inPos] << 24 & 268435455 | in[53 + inPos] << 16 & 16777215 | in[54 + inPos] << 8 & '\uffff' | in[55 + inPos] & 255;
         out[16 + outPos] = in[56 + inPos] << 20 & 268435455 | in[57 + inPos] << 12 & 1048575 | in[58 + inPos] << 4 & 4095 | in[59 + inPos] >> 4 & 15;
         out[17 + outPos] = in[59 + inPos] << 24 & 268435455 | in[60 + inPos] << 16 & 16777215 | in[61 + inPos] << 8 & '\uffff' | in[62 + inPos] & 255;
         out[18 + outPos] = in[63 + inPos] << 20 & 268435455 | in[64 + inPos] << 12 & 1048575 | in[65 + inPos] << 4 & 4095 | in[66 + inPos] >> 4 & 15;
         out[19 + outPos] = in[66 + inPos] << 24 & 268435455 | in[67 + inPos] << 16 & 16777215 | in[68 + inPos] << 8 & '\uffff' | in[69 + inPos] & 255;
         out[20 + outPos] = in[70 + inPos] << 20 & 268435455 | in[71 + inPos] << 12 & 1048575 | in[72 + inPos] << 4 & 4095 | in[73 + inPos] >> 4 & 15;
         out[21 + outPos] = in[73 + inPos] << 24 & 268435455 | in[74 + inPos] << 16 & 16777215 | in[75 + inPos] << 8 & '\uffff' | in[76 + inPos] & 255;
         out[22 + outPos] = in[77 + inPos] << 20 & 268435455 | in[78 + inPos] << 12 & 1048575 | in[79 + inPos] << 4 & 4095 | in[80 + inPos] >> 4 & 15;
         out[23 + outPos] = in[80 + inPos] << 24 & 268435455 | in[81 + inPos] << 16 & 16777215 | in[82 + inPos] << 8 & '\uffff' | in[83 + inPos] & 255;
         out[24 + outPos] = in[84 + inPos] << 20 & 268435455 | in[85 + inPos] << 12 & 1048575 | in[86 + inPos] << 4 & 4095 | in[87 + inPos] >> 4 & 15;
         out[25 + outPos] = in[87 + inPos] << 24 & 268435455 | in[88 + inPos] << 16 & 16777215 | in[89 + inPos] << 8 & '\uffff' | in[90 + inPos] & 255;
         out[26 + outPos] = in[91 + inPos] << 20 & 268435455 | in[92 + inPos] << 12 & 1048575 | in[93 + inPos] << 4 & 4095 | in[94 + inPos] >> 4 & 15;
         out[27 + outPos] = in[94 + inPos] << 24 & 268435455 | in[95 + inPos] << 16 & 16777215 | in[96 + inPos] << 8 & '\uffff' | in[97 + inPos] & 255;
         out[28 + outPos] = in[98 + inPos] << 20 & 268435455 | in[99 + inPos] << 12 & 1048575 | in[100 + inPos] << 4 & 4095 | in[101 + inPos] >> 4 & 15;
         out[29 + outPos] = in[101 + inPos] << 24 & 268435455 | in[102 + inPos] << 16 & 16777215 | in[103 + inPos] << 8 & '\uffff' | in[104 + inPos] & 255;
         out[30 + outPos] = in[105 + inPos] << 20 & 268435455 | in[106 + inPos] << 12 & 1048575 | in[107 + inPos] << 4 & 4095 | in[108 + inPos] >> 4 & 15;
         out[31 + outPos] = in[108 + inPos] << 24 & 268435455 | in[109 + inPos] << 16 & 16777215 | in[110 + inPos] << 8 & '\uffff' | in[111 + inPos] & 255;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 20 & 268435455 | in.get(1 + inPos) << 12 & 1048575 | in.get(2 + inPos) << 4 & 4095 | in.get(3 + inPos) >> 4 & 15;
         out[1 + outPos] = in.get(3 + inPos) << 24 & 268435455 | in.get(4 + inPos) << 16 & 16777215 | in.get(5 + inPos) << 8 & '\uffff' | in.get(6 + inPos) & 255;
         out[2 + outPos] = in.get(7 + inPos) << 20 & 268435455 | in.get(8 + inPos) << 12 & 1048575 | in.get(9 + inPos) << 4 & 4095 | in.get(10 + inPos) >> 4 & 15;
         out[3 + outPos] = in.get(10 + inPos) << 24 & 268435455 | in.get(11 + inPos) << 16 & 16777215 | in.get(12 + inPos) << 8 & '\uffff' | in.get(13 + inPos) & 255;
         out[4 + outPos] = in.get(14 + inPos) << 20 & 268435455 | in.get(15 + inPos) << 12 & 1048575 | in.get(16 + inPos) << 4 & 4095 | in.get(17 + inPos) >> 4 & 15;
         out[5 + outPos] = in.get(17 + inPos) << 24 & 268435455 | in.get(18 + inPos) << 16 & 16777215 | in.get(19 + inPos) << 8 & '\uffff' | in.get(20 + inPos) & 255;
         out[6 + outPos] = in.get(21 + inPos) << 20 & 268435455 | in.get(22 + inPos) << 12 & 1048575 | in.get(23 + inPos) << 4 & 4095 | in.get(24 + inPos) >> 4 & 15;
         out[7 + outPos] = in.get(24 + inPos) << 24 & 268435455 | in.get(25 + inPos) << 16 & 16777215 | in.get(26 + inPos) << 8 & '\uffff' | in.get(27 + inPos) & 255;
         out[8 + outPos] = in.get(28 + inPos) << 20 & 268435455 | in.get(29 + inPos) << 12 & 1048575 | in.get(30 + inPos) << 4 & 4095 | in.get(31 + inPos) >> 4 & 15;
         out[9 + outPos] = in.get(31 + inPos) << 24 & 268435455 | in.get(32 + inPos) << 16 & 16777215 | in.get(33 + inPos) << 8 & '\uffff' | in.get(34 + inPos) & 255;
         out[10 + outPos] = in.get(35 + inPos) << 20 & 268435455 | in.get(36 + inPos) << 12 & 1048575 | in.get(37 + inPos) << 4 & 4095 | in.get(38 + inPos) >> 4 & 15;
         out[11 + outPos] = in.get(38 + inPos) << 24 & 268435455 | in.get(39 + inPos) << 16 & 16777215 | in.get(40 + inPos) << 8 & '\uffff' | in.get(41 + inPos) & 255;
         out[12 + outPos] = in.get(42 + inPos) << 20 & 268435455 | in.get(43 + inPos) << 12 & 1048575 | in.get(44 + inPos) << 4 & 4095 | in.get(45 + inPos) >> 4 & 15;
         out[13 + outPos] = in.get(45 + inPos) << 24 & 268435455 | in.get(46 + inPos) << 16 & 16777215 | in.get(47 + inPos) << 8 & '\uffff' | in.get(48 + inPos) & 255;
         out[14 + outPos] = in.get(49 + inPos) << 20 & 268435455 | in.get(50 + inPos) << 12 & 1048575 | in.get(51 + inPos) << 4 & 4095 | in.get(52 + inPos) >> 4 & 15;
         out[15 + outPos] = in.get(52 + inPos) << 24 & 268435455 | in.get(53 + inPos) << 16 & 16777215 | in.get(54 + inPos) << 8 & '\uffff' | in.get(55 + inPos) & 255;
         out[16 + outPos] = in.get(56 + inPos) << 20 & 268435455 | in.get(57 + inPos) << 12 & 1048575 | in.get(58 + inPos) << 4 & 4095 | in.get(59 + inPos) >> 4 & 15;
         out[17 + outPos] = in.get(59 + inPos) << 24 & 268435455 | in.get(60 + inPos) << 16 & 16777215 | in.get(61 + inPos) << 8 & '\uffff' | in.get(62 + inPos) & 255;
         out[18 + outPos] = in.get(63 + inPos) << 20 & 268435455 | in.get(64 + inPos) << 12 & 1048575 | in.get(65 + inPos) << 4 & 4095 | in.get(66 + inPos) >> 4 & 15;
         out[19 + outPos] = in.get(66 + inPos) << 24 & 268435455 | in.get(67 + inPos) << 16 & 16777215 | in.get(68 + inPos) << 8 & '\uffff' | in.get(69 + inPos) & 255;
         out[20 + outPos] = in.get(70 + inPos) << 20 & 268435455 | in.get(71 + inPos) << 12 & 1048575 | in.get(72 + inPos) << 4 & 4095 | in.get(73 + inPos) >> 4 & 15;
         out[21 + outPos] = in.get(73 + inPos) << 24 & 268435455 | in.get(74 + inPos) << 16 & 16777215 | in.get(75 + inPos) << 8 & '\uffff' | in.get(76 + inPos) & 255;
         out[22 + outPos] = in.get(77 + inPos) << 20 & 268435455 | in.get(78 + inPos) << 12 & 1048575 | in.get(79 + inPos) << 4 & 4095 | in.get(80 + inPos) >> 4 & 15;
         out[23 + outPos] = in.get(80 + inPos) << 24 & 268435455 | in.get(81 + inPos) << 16 & 16777215 | in.get(82 + inPos) << 8 & '\uffff' | in.get(83 + inPos) & 255;
         out[24 + outPos] = in.get(84 + inPos) << 20 & 268435455 | in.get(85 + inPos) << 12 & 1048575 | in.get(86 + inPos) << 4 & 4095 | in.get(87 + inPos) >> 4 & 15;
         out[25 + outPos] = in.get(87 + inPos) << 24 & 268435455 | in.get(88 + inPos) << 16 & 16777215 | in.get(89 + inPos) << 8 & '\uffff' | in.get(90 + inPos) & 255;
         out[26 + outPos] = in.get(91 + inPos) << 20 & 268435455 | in.get(92 + inPos) << 12 & 1048575 | in.get(93 + inPos) << 4 & 4095 | in.get(94 + inPos) >> 4 & 15;
         out[27 + outPos] = in.get(94 + inPos) << 24 & 268435455 | in.get(95 + inPos) << 16 & 16777215 | in.get(96 + inPos) << 8 & '\uffff' | in.get(97 + inPos) & 255;
         out[28 + outPos] = in.get(98 + inPos) << 20 & 268435455 | in.get(99 + inPos) << 12 & 1048575 | in.get(100 + inPos) << 4 & 4095 | in.get(101 + inPos) >> 4 & 15;
         out[29 + outPos] = in.get(101 + inPos) << 24 & 268435455 | in.get(102 + inPos) << 16 & 16777215 | in.get(103 + inPos) << 8 & '\uffff' | in.get(104 + inPos) & 255;
         out[30 + outPos] = in.get(105 + inPos) << 20 & 268435455 | in.get(106 + inPos) << 12 & 1048575 | in.get(107 + inPos) << 4 & 4095 | in.get(108 + inPos) >> 4 & 15;
         out[31 + outPos] = in.get(108 + inPos) << 24 & 268435455 | in.get(109 + inPos) << 16 & 16777215 | in.get(110 + inPos) << 8 & '\uffff' | in.get(111 + inPos) & 255;
      }
   }

   private static final class Packer29 extends BytePacker {
      private Packer29() {
         super(29);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 536870911) >>> 21 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 536870911) >>> 13 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & 536870911) >>> 5 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & 536870911) << 3 | (in[1 + inPos] & 536870911) >>> 26) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 536870911) >>> 18 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & 536870911) >>> 10 & 255);
         out[6 + outPos] = (byte)((in[1 + inPos] & 536870911) >>> 2 & 255);
         out[7 + outPos] = (byte)(((in[1 + inPos] & 536870911) << 6 | (in[2 + inPos] & 536870911) >>> 23) & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & 536870911) >>> 15 & 255);
         out[9 + outPos] = (byte)((in[2 + inPos] & 536870911) >>> 7 & 255);
         out[10 + outPos] = (byte)(((in[2 + inPos] & 536870911) << 1 | (in[3 + inPos] & 536870911) >>> 28) & 255);
         out[11 + outPos] = (byte)((in[3 + inPos] & 536870911) >>> 20 & 255);
         out[12 + outPos] = (byte)((in[3 + inPos] & 536870911) >>> 12 & 255);
         out[13 + outPos] = (byte)((in[3 + inPos] & 536870911) >>> 4 & 255);
         out[14 + outPos] = (byte)(((in[3 + inPos] & 536870911) << 4 | (in[4 + inPos] & 536870911) >>> 25) & 255);
         out[15 + outPos] = (byte)((in[4 + inPos] & 536870911) >>> 17 & 255);
         out[16 + outPos] = (byte)((in[4 + inPos] & 536870911) >>> 9 & 255);
         out[17 + outPos] = (byte)((in[4 + inPos] & 536870911) >>> 1 & 255);
         out[18 + outPos] = (byte)(((in[4 + inPos] & 536870911) << 7 | (in[5 + inPos] & 536870911) >>> 22) & 255);
         out[19 + outPos] = (byte)((in[5 + inPos] & 536870911) >>> 14 & 255);
         out[20 + outPos] = (byte)((in[5 + inPos] & 536870911) >>> 6 & 255);
         out[21 + outPos] = (byte)(((in[5 + inPos] & 536870911) << 2 | (in[6 + inPos] & 536870911) >>> 27) & 255);
         out[22 + outPos] = (byte)((in[6 + inPos] & 536870911) >>> 19 & 255);
         out[23 + outPos] = (byte)((in[6 + inPos] & 536870911) >>> 11 & 255);
         out[24 + outPos] = (byte)((in[6 + inPos] & 536870911) >>> 3 & 255);
         out[25 + outPos] = (byte)(((in[6 + inPos] & 536870911) << 5 | (in[7 + inPos] & 536870911) >>> 24) & 255);
         out[26 + outPos] = (byte)((in[7 + inPos] & 536870911) >>> 16 & 255);
         out[27 + outPos] = (byte)((in[7 + inPos] & 536870911) >>> 8 & 255);
         out[28 + outPos] = (byte)(in[7 + inPos] & 536870911 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 536870911) >>> 21 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 536870911) >>> 13 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & 536870911) >>> 5 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & 536870911) << 3 | (in[1 + inPos] & 536870911) >>> 26) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 536870911) >>> 18 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & 536870911) >>> 10 & 255);
         out[6 + outPos] = (byte)((in[1 + inPos] & 536870911) >>> 2 & 255);
         out[7 + outPos] = (byte)(((in[1 + inPos] & 536870911) << 6 | (in[2 + inPos] & 536870911) >>> 23) & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & 536870911) >>> 15 & 255);
         out[9 + outPos] = (byte)((in[2 + inPos] & 536870911) >>> 7 & 255);
         out[10 + outPos] = (byte)(((in[2 + inPos] & 536870911) << 1 | (in[3 + inPos] & 536870911) >>> 28) & 255);
         out[11 + outPos] = (byte)((in[3 + inPos] & 536870911) >>> 20 & 255);
         out[12 + outPos] = (byte)((in[3 + inPos] & 536870911) >>> 12 & 255);
         out[13 + outPos] = (byte)((in[3 + inPos] & 536870911) >>> 4 & 255);
         out[14 + outPos] = (byte)(((in[3 + inPos] & 536870911) << 4 | (in[4 + inPos] & 536870911) >>> 25) & 255);
         out[15 + outPos] = (byte)((in[4 + inPos] & 536870911) >>> 17 & 255);
         out[16 + outPos] = (byte)((in[4 + inPos] & 536870911) >>> 9 & 255);
         out[17 + outPos] = (byte)((in[4 + inPos] & 536870911) >>> 1 & 255);
         out[18 + outPos] = (byte)(((in[4 + inPos] & 536870911) << 7 | (in[5 + inPos] & 536870911) >>> 22) & 255);
         out[19 + outPos] = (byte)((in[5 + inPos] & 536870911) >>> 14 & 255);
         out[20 + outPos] = (byte)((in[5 + inPos] & 536870911) >>> 6 & 255);
         out[21 + outPos] = (byte)(((in[5 + inPos] & 536870911) << 2 | (in[6 + inPos] & 536870911) >>> 27) & 255);
         out[22 + outPos] = (byte)((in[6 + inPos] & 536870911) >>> 19 & 255);
         out[23 + outPos] = (byte)((in[6 + inPos] & 536870911) >>> 11 & 255);
         out[24 + outPos] = (byte)((in[6 + inPos] & 536870911) >>> 3 & 255);
         out[25 + outPos] = (byte)(((in[6 + inPos] & 536870911) << 5 | (in[7 + inPos] & 536870911) >>> 24) & 255);
         out[26 + outPos] = (byte)((in[7 + inPos] & 536870911) >>> 16 & 255);
         out[27 + outPos] = (byte)((in[7 + inPos] & 536870911) >>> 8 & 255);
         out[28 + outPos] = (byte)(in[7 + inPos] & 536870911 & 255);
         out[29 + outPos] = (byte)((in[8 + inPos] & 536870911) >>> 21 & 255);
         out[30 + outPos] = (byte)((in[8 + inPos] & 536870911) >>> 13 & 255);
         out[31 + outPos] = (byte)((in[8 + inPos] & 536870911) >>> 5 & 255);
         out[32 + outPos] = (byte)(((in[8 + inPos] & 536870911) << 3 | (in[9 + inPos] & 536870911) >>> 26) & 255);
         out[33 + outPos] = (byte)((in[9 + inPos] & 536870911) >>> 18 & 255);
         out[34 + outPos] = (byte)((in[9 + inPos] & 536870911) >>> 10 & 255);
         out[35 + outPos] = (byte)((in[9 + inPos] & 536870911) >>> 2 & 255);
         out[36 + outPos] = (byte)(((in[9 + inPos] & 536870911) << 6 | (in[10 + inPos] & 536870911) >>> 23) & 255);
         out[37 + outPos] = (byte)((in[10 + inPos] & 536870911) >>> 15 & 255);
         out[38 + outPos] = (byte)((in[10 + inPos] & 536870911) >>> 7 & 255);
         out[39 + outPos] = (byte)(((in[10 + inPos] & 536870911) << 1 | (in[11 + inPos] & 536870911) >>> 28) & 255);
         out[40 + outPos] = (byte)((in[11 + inPos] & 536870911) >>> 20 & 255);
         out[41 + outPos] = (byte)((in[11 + inPos] & 536870911) >>> 12 & 255);
         out[42 + outPos] = (byte)((in[11 + inPos] & 536870911) >>> 4 & 255);
         out[43 + outPos] = (byte)(((in[11 + inPos] & 536870911) << 4 | (in[12 + inPos] & 536870911) >>> 25) & 255);
         out[44 + outPos] = (byte)((in[12 + inPos] & 536870911) >>> 17 & 255);
         out[45 + outPos] = (byte)((in[12 + inPos] & 536870911) >>> 9 & 255);
         out[46 + outPos] = (byte)((in[12 + inPos] & 536870911) >>> 1 & 255);
         out[47 + outPos] = (byte)(((in[12 + inPos] & 536870911) << 7 | (in[13 + inPos] & 536870911) >>> 22) & 255);
         out[48 + outPos] = (byte)((in[13 + inPos] & 536870911) >>> 14 & 255);
         out[49 + outPos] = (byte)((in[13 + inPos] & 536870911) >>> 6 & 255);
         out[50 + outPos] = (byte)(((in[13 + inPos] & 536870911) << 2 | (in[14 + inPos] & 536870911) >>> 27) & 255);
         out[51 + outPos] = (byte)((in[14 + inPos] & 536870911) >>> 19 & 255);
         out[52 + outPos] = (byte)((in[14 + inPos] & 536870911) >>> 11 & 255);
         out[53 + outPos] = (byte)((in[14 + inPos] & 536870911) >>> 3 & 255);
         out[54 + outPos] = (byte)(((in[14 + inPos] & 536870911) << 5 | (in[15 + inPos] & 536870911) >>> 24) & 255);
         out[55 + outPos] = (byte)((in[15 + inPos] & 536870911) >>> 16 & 255);
         out[56 + outPos] = (byte)((in[15 + inPos] & 536870911) >>> 8 & 255);
         out[57 + outPos] = (byte)(in[15 + inPos] & 536870911 & 255);
         out[58 + outPos] = (byte)((in[16 + inPos] & 536870911) >>> 21 & 255);
         out[59 + outPos] = (byte)((in[16 + inPos] & 536870911) >>> 13 & 255);
         out[60 + outPos] = (byte)((in[16 + inPos] & 536870911) >>> 5 & 255);
         out[61 + outPos] = (byte)(((in[16 + inPos] & 536870911) << 3 | (in[17 + inPos] & 536870911) >>> 26) & 255);
         out[62 + outPos] = (byte)((in[17 + inPos] & 536870911) >>> 18 & 255);
         out[63 + outPos] = (byte)((in[17 + inPos] & 536870911) >>> 10 & 255);
         out[64 + outPos] = (byte)((in[17 + inPos] & 536870911) >>> 2 & 255);
         out[65 + outPos] = (byte)(((in[17 + inPos] & 536870911) << 6 | (in[18 + inPos] & 536870911) >>> 23) & 255);
         out[66 + outPos] = (byte)((in[18 + inPos] & 536870911) >>> 15 & 255);
         out[67 + outPos] = (byte)((in[18 + inPos] & 536870911) >>> 7 & 255);
         out[68 + outPos] = (byte)(((in[18 + inPos] & 536870911) << 1 | (in[19 + inPos] & 536870911) >>> 28) & 255);
         out[69 + outPos] = (byte)((in[19 + inPos] & 536870911) >>> 20 & 255);
         out[70 + outPos] = (byte)((in[19 + inPos] & 536870911) >>> 12 & 255);
         out[71 + outPos] = (byte)((in[19 + inPos] & 536870911) >>> 4 & 255);
         out[72 + outPos] = (byte)(((in[19 + inPos] & 536870911) << 4 | (in[20 + inPos] & 536870911) >>> 25) & 255);
         out[73 + outPos] = (byte)((in[20 + inPos] & 536870911) >>> 17 & 255);
         out[74 + outPos] = (byte)((in[20 + inPos] & 536870911) >>> 9 & 255);
         out[75 + outPos] = (byte)((in[20 + inPos] & 536870911) >>> 1 & 255);
         out[76 + outPos] = (byte)(((in[20 + inPos] & 536870911) << 7 | (in[21 + inPos] & 536870911) >>> 22) & 255);
         out[77 + outPos] = (byte)((in[21 + inPos] & 536870911) >>> 14 & 255);
         out[78 + outPos] = (byte)((in[21 + inPos] & 536870911) >>> 6 & 255);
         out[79 + outPos] = (byte)(((in[21 + inPos] & 536870911) << 2 | (in[22 + inPos] & 536870911) >>> 27) & 255);
         out[80 + outPos] = (byte)((in[22 + inPos] & 536870911) >>> 19 & 255);
         out[81 + outPos] = (byte)((in[22 + inPos] & 536870911) >>> 11 & 255);
         out[82 + outPos] = (byte)((in[22 + inPos] & 536870911) >>> 3 & 255);
         out[83 + outPos] = (byte)(((in[22 + inPos] & 536870911) << 5 | (in[23 + inPos] & 536870911) >>> 24) & 255);
         out[84 + outPos] = (byte)((in[23 + inPos] & 536870911) >>> 16 & 255);
         out[85 + outPos] = (byte)((in[23 + inPos] & 536870911) >>> 8 & 255);
         out[86 + outPos] = (byte)(in[23 + inPos] & 536870911 & 255);
         out[87 + outPos] = (byte)((in[24 + inPos] & 536870911) >>> 21 & 255);
         out[88 + outPos] = (byte)((in[24 + inPos] & 536870911) >>> 13 & 255);
         out[89 + outPos] = (byte)((in[24 + inPos] & 536870911) >>> 5 & 255);
         out[90 + outPos] = (byte)(((in[24 + inPos] & 536870911) << 3 | (in[25 + inPos] & 536870911) >>> 26) & 255);
         out[91 + outPos] = (byte)((in[25 + inPos] & 536870911) >>> 18 & 255);
         out[92 + outPos] = (byte)((in[25 + inPos] & 536870911) >>> 10 & 255);
         out[93 + outPos] = (byte)((in[25 + inPos] & 536870911) >>> 2 & 255);
         out[94 + outPos] = (byte)(((in[25 + inPos] & 536870911) << 6 | (in[26 + inPos] & 536870911) >>> 23) & 255);
         out[95 + outPos] = (byte)((in[26 + inPos] & 536870911) >>> 15 & 255);
         out[96 + outPos] = (byte)((in[26 + inPos] & 536870911) >>> 7 & 255);
         out[97 + outPos] = (byte)(((in[26 + inPos] & 536870911) << 1 | (in[27 + inPos] & 536870911) >>> 28) & 255);
         out[98 + outPos] = (byte)((in[27 + inPos] & 536870911) >>> 20 & 255);
         out[99 + outPos] = (byte)((in[27 + inPos] & 536870911) >>> 12 & 255);
         out[100 + outPos] = (byte)((in[27 + inPos] & 536870911) >>> 4 & 255);
         out[101 + outPos] = (byte)(((in[27 + inPos] & 536870911) << 4 | (in[28 + inPos] & 536870911) >>> 25) & 255);
         out[102 + outPos] = (byte)((in[28 + inPos] & 536870911) >>> 17 & 255);
         out[103 + outPos] = (byte)((in[28 + inPos] & 536870911) >>> 9 & 255);
         out[104 + outPos] = (byte)((in[28 + inPos] & 536870911) >>> 1 & 255);
         out[105 + outPos] = (byte)(((in[28 + inPos] & 536870911) << 7 | (in[29 + inPos] & 536870911) >>> 22) & 255);
         out[106 + outPos] = (byte)((in[29 + inPos] & 536870911) >>> 14 & 255);
         out[107 + outPos] = (byte)((in[29 + inPos] & 536870911) >>> 6 & 255);
         out[108 + outPos] = (byte)(((in[29 + inPos] & 536870911) << 2 | (in[30 + inPos] & 536870911) >>> 27) & 255);
         out[109 + outPos] = (byte)((in[30 + inPos] & 536870911) >>> 19 & 255);
         out[110 + outPos] = (byte)((in[30 + inPos] & 536870911) >>> 11 & 255);
         out[111 + outPos] = (byte)((in[30 + inPos] & 536870911) >>> 3 & 255);
         out[112 + outPos] = (byte)(((in[30 + inPos] & 536870911) << 5 | (in[31 + inPos] & 536870911) >>> 24) & 255);
         out[113 + outPos] = (byte)((in[31 + inPos] & 536870911) >>> 16 & 255);
         out[114 + outPos] = (byte)((in[31 + inPos] & 536870911) >>> 8 & 255);
         out[115 + outPos] = (byte)(in[31 + inPos] & 536870911 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 21 & 536870911 | in[1 + inPos] << 13 & 2097151 | in[2 + inPos] << 5 & 8191 | in[3 + inPos] >> 3 & 31;
         out[1 + outPos] = in[3 + inPos] << 26 & 536870911 | in[4 + inPos] << 18 & 67108863 | in[5 + inPos] << 10 & 262143 | in[6 + inPos] << 2 & 1023 | in[7 + inPos] >> 6 & 3;
         out[2 + outPos] = in[7 + inPos] << 23 & 536870911 | in[8 + inPos] << 15 & 8388607 | in[9 + inPos] << 7 & 32767 | in[10 + inPos] >> 1 & 127;
         out[3 + outPos] = in[10 + inPos] << 28 & 536870911 | in[11 + inPos] << 20 & 268435455 | in[12 + inPos] << 12 & 1048575 | in[13 + inPos] << 4 & 4095 | in[14 + inPos] >> 4 & 15;
         out[4 + outPos] = in[14 + inPos] << 25 & 536870911 | in[15 + inPos] << 17 & 33554431 | in[16 + inPos] << 9 & 131071 | in[17 + inPos] << 1 & 511 | in[18 + inPos] >> 7 & 1;
         out[5 + outPos] = in[18 + inPos] << 22 & 536870911 | in[19 + inPos] << 14 & 4194303 | in[20 + inPos] << 6 & 16383 | in[21 + inPos] >> 2 & 63;
         out[6 + outPos] = in[21 + inPos] << 27 & 536870911 | in[22 + inPos] << 19 & 134217727 | in[23 + inPos] << 11 & 524287 | in[24 + inPos] << 3 & 2047 | in[25 + inPos] >> 5 & 7;
         out[7 + outPos] = in[25 + inPos] << 24 & 536870911 | in[26 + inPos] << 16 & 16777215 | in[27 + inPos] << 8 & '\uffff' | in[28 + inPos] & 255;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 21 & 536870911 | in.get(1 + inPos) << 13 & 2097151 | in.get(2 + inPos) << 5 & 8191 | in.get(3 + inPos) >> 3 & 31;
         out[1 + outPos] = in.get(3 + inPos) << 26 & 536870911 | in.get(4 + inPos) << 18 & 67108863 | in.get(5 + inPos) << 10 & 262143 | in.get(6 + inPos) << 2 & 1023 | in.get(7 + inPos) >> 6 & 3;
         out[2 + outPos] = in.get(7 + inPos) << 23 & 536870911 | in.get(8 + inPos) << 15 & 8388607 | in.get(9 + inPos) << 7 & 32767 | in.get(10 + inPos) >> 1 & 127;
         out[3 + outPos] = in.get(10 + inPos) << 28 & 536870911 | in.get(11 + inPos) << 20 & 268435455 | in.get(12 + inPos) << 12 & 1048575 | in.get(13 + inPos) << 4 & 4095 | in.get(14 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(14 + inPos) << 25 & 536870911 | in.get(15 + inPos) << 17 & 33554431 | in.get(16 + inPos) << 9 & 131071 | in.get(17 + inPos) << 1 & 511 | in.get(18 + inPos) >> 7 & 1;
         out[5 + outPos] = in.get(18 + inPos) << 22 & 536870911 | in.get(19 + inPos) << 14 & 4194303 | in.get(20 + inPos) << 6 & 16383 | in.get(21 + inPos) >> 2 & 63;
         out[6 + outPos] = in.get(21 + inPos) << 27 & 536870911 | in.get(22 + inPos) << 19 & 134217727 | in.get(23 + inPos) << 11 & 524287 | in.get(24 + inPos) << 3 & 2047 | in.get(25 + inPos) >> 5 & 7;
         out[7 + outPos] = in.get(25 + inPos) << 24 & 536870911 | in.get(26 + inPos) << 16 & 16777215 | in.get(27 + inPos) << 8 & '\uffff' | in.get(28 + inPos) & 255;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 21 & 536870911 | in[1 + inPos] << 13 & 2097151 | in[2 + inPos] << 5 & 8191 | in[3 + inPos] >> 3 & 31;
         out[1 + outPos] = in[3 + inPos] << 26 & 536870911 | in[4 + inPos] << 18 & 67108863 | in[5 + inPos] << 10 & 262143 | in[6 + inPos] << 2 & 1023 | in[7 + inPos] >> 6 & 3;
         out[2 + outPos] = in[7 + inPos] << 23 & 536870911 | in[8 + inPos] << 15 & 8388607 | in[9 + inPos] << 7 & 32767 | in[10 + inPos] >> 1 & 127;
         out[3 + outPos] = in[10 + inPos] << 28 & 536870911 | in[11 + inPos] << 20 & 268435455 | in[12 + inPos] << 12 & 1048575 | in[13 + inPos] << 4 & 4095 | in[14 + inPos] >> 4 & 15;
         out[4 + outPos] = in[14 + inPos] << 25 & 536870911 | in[15 + inPos] << 17 & 33554431 | in[16 + inPos] << 9 & 131071 | in[17 + inPos] << 1 & 511 | in[18 + inPos] >> 7 & 1;
         out[5 + outPos] = in[18 + inPos] << 22 & 536870911 | in[19 + inPos] << 14 & 4194303 | in[20 + inPos] << 6 & 16383 | in[21 + inPos] >> 2 & 63;
         out[6 + outPos] = in[21 + inPos] << 27 & 536870911 | in[22 + inPos] << 19 & 134217727 | in[23 + inPos] << 11 & 524287 | in[24 + inPos] << 3 & 2047 | in[25 + inPos] >> 5 & 7;
         out[7 + outPos] = in[25 + inPos] << 24 & 536870911 | in[26 + inPos] << 16 & 16777215 | in[27 + inPos] << 8 & '\uffff' | in[28 + inPos] & 255;
         out[8 + outPos] = in[29 + inPos] << 21 & 536870911 | in[30 + inPos] << 13 & 2097151 | in[31 + inPos] << 5 & 8191 | in[32 + inPos] >> 3 & 31;
         out[9 + outPos] = in[32 + inPos] << 26 & 536870911 | in[33 + inPos] << 18 & 67108863 | in[34 + inPos] << 10 & 262143 | in[35 + inPos] << 2 & 1023 | in[36 + inPos] >> 6 & 3;
         out[10 + outPos] = in[36 + inPos] << 23 & 536870911 | in[37 + inPos] << 15 & 8388607 | in[38 + inPos] << 7 & 32767 | in[39 + inPos] >> 1 & 127;
         out[11 + outPos] = in[39 + inPos] << 28 & 536870911 | in[40 + inPos] << 20 & 268435455 | in[41 + inPos] << 12 & 1048575 | in[42 + inPos] << 4 & 4095 | in[43 + inPos] >> 4 & 15;
         out[12 + outPos] = in[43 + inPos] << 25 & 536870911 | in[44 + inPos] << 17 & 33554431 | in[45 + inPos] << 9 & 131071 | in[46 + inPos] << 1 & 511 | in[47 + inPos] >> 7 & 1;
         out[13 + outPos] = in[47 + inPos] << 22 & 536870911 | in[48 + inPos] << 14 & 4194303 | in[49 + inPos] << 6 & 16383 | in[50 + inPos] >> 2 & 63;
         out[14 + outPos] = in[50 + inPos] << 27 & 536870911 | in[51 + inPos] << 19 & 134217727 | in[52 + inPos] << 11 & 524287 | in[53 + inPos] << 3 & 2047 | in[54 + inPos] >> 5 & 7;
         out[15 + outPos] = in[54 + inPos] << 24 & 536870911 | in[55 + inPos] << 16 & 16777215 | in[56 + inPos] << 8 & '\uffff' | in[57 + inPos] & 255;
         out[16 + outPos] = in[58 + inPos] << 21 & 536870911 | in[59 + inPos] << 13 & 2097151 | in[60 + inPos] << 5 & 8191 | in[61 + inPos] >> 3 & 31;
         out[17 + outPos] = in[61 + inPos] << 26 & 536870911 | in[62 + inPos] << 18 & 67108863 | in[63 + inPos] << 10 & 262143 | in[64 + inPos] << 2 & 1023 | in[65 + inPos] >> 6 & 3;
         out[18 + outPos] = in[65 + inPos] << 23 & 536870911 | in[66 + inPos] << 15 & 8388607 | in[67 + inPos] << 7 & 32767 | in[68 + inPos] >> 1 & 127;
         out[19 + outPos] = in[68 + inPos] << 28 & 536870911 | in[69 + inPos] << 20 & 268435455 | in[70 + inPos] << 12 & 1048575 | in[71 + inPos] << 4 & 4095 | in[72 + inPos] >> 4 & 15;
         out[20 + outPos] = in[72 + inPos] << 25 & 536870911 | in[73 + inPos] << 17 & 33554431 | in[74 + inPos] << 9 & 131071 | in[75 + inPos] << 1 & 511 | in[76 + inPos] >> 7 & 1;
         out[21 + outPos] = in[76 + inPos] << 22 & 536870911 | in[77 + inPos] << 14 & 4194303 | in[78 + inPos] << 6 & 16383 | in[79 + inPos] >> 2 & 63;
         out[22 + outPos] = in[79 + inPos] << 27 & 536870911 | in[80 + inPos] << 19 & 134217727 | in[81 + inPos] << 11 & 524287 | in[82 + inPos] << 3 & 2047 | in[83 + inPos] >> 5 & 7;
         out[23 + outPos] = in[83 + inPos] << 24 & 536870911 | in[84 + inPos] << 16 & 16777215 | in[85 + inPos] << 8 & '\uffff' | in[86 + inPos] & 255;
         out[24 + outPos] = in[87 + inPos] << 21 & 536870911 | in[88 + inPos] << 13 & 2097151 | in[89 + inPos] << 5 & 8191 | in[90 + inPos] >> 3 & 31;
         out[25 + outPos] = in[90 + inPos] << 26 & 536870911 | in[91 + inPos] << 18 & 67108863 | in[92 + inPos] << 10 & 262143 | in[93 + inPos] << 2 & 1023 | in[94 + inPos] >> 6 & 3;
         out[26 + outPos] = in[94 + inPos] << 23 & 536870911 | in[95 + inPos] << 15 & 8388607 | in[96 + inPos] << 7 & 32767 | in[97 + inPos] >> 1 & 127;
         out[27 + outPos] = in[97 + inPos] << 28 & 536870911 | in[98 + inPos] << 20 & 268435455 | in[99 + inPos] << 12 & 1048575 | in[100 + inPos] << 4 & 4095 | in[101 + inPos] >> 4 & 15;
         out[28 + outPos] = in[101 + inPos] << 25 & 536870911 | in[102 + inPos] << 17 & 33554431 | in[103 + inPos] << 9 & 131071 | in[104 + inPos] << 1 & 511 | in[105 + inPos] >> 7 & 1;
         out[29 + outPos] = in[105 + inPos] << 22 & 536870911 | in[106 + inPos] << 14 & 4194303 | in[107 + inPos] << 6 & 16383 | in[108 + inPos] >> 2 & 63;
         out[30 + outPos] = in[108 + inPos] << 27 & 536870911 | in[109 + inPos] << 19 & 134217727 | in[110 + inPos] << 11 & 524287 | in[111 + inPos] << 3 & 2047 | in[112 + inPos] >> 5 & 7;
         out[31 + outPos] = in[112 + inPos] << 24 & 536870911 | in[113 + inPos] << 16 & 16777215 | in[114 + inPos] << 8 & '\uffff' | in[115 + inPos] & 255;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 21 & 536870911 | in.get(1 + inPos) << 13 & 2097151 | in.get(2 + inPos) << 5 & 8191 | in.get(3 + inPos) >> 3 & 31;
         out[1 + outPos] = in.get(3 + inPos) << 26 & 536870911 | in.get(4 + inPos) << 18 & 67108863 | in.get(5 + inPos) << 10 & 262143 | in.get(6 + inPos) << 2 & 1023 | in.get(7 + inPos) >> 6 & 3;
         out[2 + outPos] = in.get(7 + inPos) << 23 & 536870911 | in.get(8 + inPos) << 15 & 8388607 | in.get(9 + inPos) << 7 & 32767 | in.get(10 + inPos) >> 1 & 127;
         out[3 + outPos] = in.get(10 + inPos) << 28 & 536870911 | in.get(11 + inPos) << 20 & 268435455 | in.get(12 + inPos) << 12 & 1048575 | in.get(13 + inPos) << 4 & 4095 | in.get(14 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(14 + inPos) << 25 & 536870911 | in.get(15 + inPos) << 17 & 33554431 | in.get(16 + inPos) << 9 & 131071 | in.get(17 + inPos) << 1 & 511 | in.get(18 + inPos) >> 7 & 1;
         out[5 + outPos] = in.get(18 + inPos) << 22 & 536870911 | in.get(19 + inPos) << 14 & 4194303 | in.get(20 + inPos) << 6 & 16383 | in.get(21 + inPos) >> 2 & 63;
         out[6 + outPos] = in.get(21 + inPos) << 27 & 536870911 | in.get(22 + inPos) << 19 & 134217727 | in.get(23 + inPos) << 11 & 524287 | in.get(24 + inPos) << 3 & 2047 | in.get(25 + inPos) >> 5 & 7;
         out[7 + outPos] = in.get(25 + inPos) << 24 & 536870911 | in.get(26 + inPos) << 16 & 16777215 | in.get(27 + inPos) << 8 & '\uffff' | in.get(28 + inPos) & 255;
         out[8 + outPos] = in.get(29 + inPos) << 21 & 536870911 | in.get(30 + inPos) << 13 & 2097151 | in.get(31 + inPos) << 5 & 8191 | in.get(32 + inPos) >> 3 & 31;
         out[9 + outPos] = in.get(32 + inPos) << 26 & 536870911 | in.get(33 + inPos) << 18 & 67108863 | in.get(34 + inPos) << 10 & 262143 | in.get(35 + inPos) << 2 & 1023 | in.get(36 + inPos) >> 6 & 3;
         out[10 + outPos] = in.get(36 + inPos) << 23 & 536870911 | in.get(37 + inPos) << 15 & 8388607 | in.get(38 + inPos) << 7 & 32767 | in.get(39 + inPos) >> 1 & 127;
         out[11 + outPos] = in.get(39 + inPos) << 28 & 536870911 | in.get(40 + inPos) << 20 & 268435455 | in.get(41 + inPos) << 12 & 1048575 | in.get(42 + inPos) << 4 & 4095 | in.get(43 + inPos) >> 4 & 15;
         out[12 + outPos] = in.get(43 + inPos) << 25 & 536870911 | in.get(44 + inPos) << 17 & 33554431 | in.get(45 + inPos) << 9 & 131071 | in.get(46 + inPos) << 1 & 511 | in.get(47 + inPos) >> 7 & 1;
         out[13 + outPos] = in.get(47 + inPos) << 22 & 536870911 | in.get(48 + inPos) << 14 & 4194303 | in.get(49 + inPos) << 6 & 16383 | in.get(50 + inPos) >> 2 & 63;
         out[14 + outPos] = in.get(50 + inPos) << 27 & 536870911 | in.get(51 + inPos) << 19 & 134217727 | in.get(52 + inPos) << 11 & 524287 | in.get(53 + inPos) << 3 & 2047 | in.get(54 + inPos) >> 5 & 7;
         out[15 + outPos] = in.get(54 + inPos) << 24 & 536870911 | in.get(55 + inPos) << 16 & 16777215 | in.get(56 + inPos) << 8 & '\uffff' | in.get(57 + inPos) & 255;
         out[16 + outPos] = in.get(58 + inPos) << 21 & 536870911 | in.get(59 + inPos) << 13 & 2097151 | in.get(60 + inPos) << 5 & 8191 | in.get(61 + inPos) >> 3 & 31;
         out[17 + outPos] = in.get(61 + inPos) << 26 & 536870911 | in.get(62 + inPos) << 18 & 67108863 | in.get(63 + inPos) << 10 & 262143 | in.get(64 + inPos) << 2 & 1023 | in.get(65 + inPos) >> 6 & 3;
         out[18 + outPos] = in.get(65 + inPos) << 23 & 536870911 | in.get(66 + inPos) << 15 & 8388607 | in.get(67 + inPos) << 7 & 32767 | in.get(68 + inPos) >> 1 & 127;
         out[19 + outPos] = in.get(68 + inPos) << 28 & 536870911 | in.get(69 + inPos) << 20 & 268435455 | in.get(70 + inPos) << 12 & 1048575 | in.get(71 + inPos) << 4 & 4095 | in.get(72 + inPos) >> 4 & 15;
         out[20 + outPos] = in.get(72 + inPos) << 25 & 536870911 | in.get(73 + inPos) << 17 & 33554431 | in.get(74 + inPos) << 9 & 131071 | in.get(75 + inPos) << 1 & 511 | in.get(76 + inPos) >> 7 & 1;
         out[21 + outPos] = in.get(76 + inPos) << 22 & 536870911 | in.get(77 + inPos) << 14 & 4194303 | in.get(78 + inPos) << 6 & 16383 | in.get(79 + inPos) >> 2 & 63;
         out[22 + outPos] = in.get(79 + inPos) << 27 & 536870911 | in.get(80 + inPos) << 19 & 134217727 | in.get(81 + inPos) << 11 & 524287 | in.get(82 + inPos) << 3 & 2047 | in.get(83 + inPos) >> 5 & 7;
         out[23 + outPos] = in.get(83 + inPos) << 24 & 536870911 | in.get(84 + inPos) << 16 & 16777215 | in.get(85 + inPos) << 8 & '\uffff' | in.get(86 + inPos) & 255;
         out[24 + outPos] = in.get(87 + inPos) << 21 & 536870911 | in.get(88 + inPos) << 13 & 2097151 | in.get(89 + inPos) << 5 & 8191 | in.get(90 + inPos) >> 3 & 31;
         out[25 + outPos] = in.get(90 + inPos) << 26 & 536870911 | in.get(91 + inPos) << 18 & 67108863 | in.get(92 + inPos) << 10 & 262143 | in.get(93 + inPos) << 2 & 1023 | in.get(94 + inPos) >> 6 & 3;
         out[26 + outPos] = in.get(94 + inPos) << 23 & 536870911 | in.get(95 + inPos) << 15 & 8388607 | in.get(96 + inPos) << 7 & 32767 | in.get(97 + inPos) >> 1 & 127;
         out[27 + outPos] = in.get(97 + inPos) << 28 & 536870911 | in.get(98 + inPos) << 20 & 268435455 | in.get(99 + inPos) << 12 & 1048575 | in.get(100 + inPos) << 4 & 4095 | in.get(101 + inPos) >> 4 & 15;
         out[28 + outPos] = in.get(101 + inPos) << 25 & 536870911 | in.get(102 + inPos) << 17 & 33554431 | in.get(103 + inPos) << 9 & 131071 | in.get(104 + inPos) << 1 & 511 | in.get(105 + inPos) >> 7 & 1;
         out[29 + outPos] = in.get(105 + inPos) << 22 & 536870911 | in.get(106 + inPos) << 14 & 4194303 | in.get(107 + inPos) << 6 & 16383 | in.get(108 + inPos) >> 2 & 63;
         out[30 + outPos] = in.get(108 + inPos) << 27 & 536870911 | in.get(109 + inPos) << 19 & 134217727 | in.get(110 + inPos) << 11 & 524287 | in.get(111 + inPos) << 3 & 2047 | in.get(112 + inPos) >> 5 & 7;
         out[31 + outPos] = in.get(112 + inPos) << 24 & 536870911 | in.get(113 + inPos) << 16 & 16777215 | in.get(114 + inPos) << 8 & '\uffff' | in.get(115 + inPos) & 255;
      }
   }

   private static final class Packer30 extends BytePacker {
      private Packer30() {
         super(30);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 1073741823) >>> 22 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 1073741823) >>> 14 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & 1073741823) >>> 6 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & 1073741823) << 2 | (in[1 + inPos] & 1073741823) >>> 28) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 1073741823) >>> 20 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & 1073741823) >>> 12 & 255);
         out[6 + outPos] = (byte)((in[1 + inPos] & 1073741823) >>> 4 & 255);
         out[7 + outPos] = (byte)(((in[1 + inPos] & 1073741823) << 4 | (in[2 + inPos] & 1073741823) >>> 26) & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & 1073741823) >>> 18 & 255);
         out[9 + outPos] = (byte)((in[2 + inPos] & 1073741823) >>> 10 & 255);
         out[10 + outPos] = (byte)((in[2 + inPos] & 1073741823) >>> 2 & 255);
         out[11 + outPos] = (byte)(((in[2 + inPos] & 1073741823) << 6 | (in[3 + inPos] & 1073741823) >>> 24) & 255);
         out[12 + outPos] = (byte)((in[3 + inPos] & 1073741823) >>> 16 & 255);
         out[13 + outPos] = (byte)((in[3 + inPos] & 1073741823) >>> 8 & 255);
         out[14 + outPos] = (byte)(in[3 + inPos] & 1073741823 & 255);
         out[15 + outPos] = (byte)((in[4 + inPos] & 1073741823) >>> 22 & 255);
         out[16 + outPos] = (byte)((in[4 + inPos] & 1073741823) >>> 14 & 255);
         out[17 + outPos] = (byte)((in[4 + inPos] & 1073741823) >>> 6 & 255);
         out[18 + outPos] = (byte)(((in[4 + inPos] & 1073741823) << 2 | (in[5 + inPos] & 1073741823) >>> 28) & 255);
         out[19 + outPos] = (byte)((in[5 + inPos] & 1073741823) >>> 20 & 255);
         out[20 + outPos] = (byte)((in[5 + inPos] & 1073741823) >>> 12 & 255);
         out[21 + outPos] = (byte)((in[5 + inPos] & 1073741823) >>> 4 & 255);
         out[22 + outPos] = (byte)(((in[5 + inPos] & 1073741823) << 4 | (in[6 + inPos] & 1073741823) >>> 26) & 255);
         out[23 + outPos] = (byte)((in[6 + inPos] & 1073741823) >>> 18 & 255);
         out[24 + outPos] = (byte)((in[6 + inPos] & 1073741823) >>> 10 & 255);
         out[25 + outPos] = (byte)((in[6 + inPos] & 1073741823) >>> 2 & 255);
         out[26 + outPos] = (byte)(((in[6 + inPos] & 1073741823) << 6 | (in[7 + inPos] & 1073741823) >>> 24) & 255);
         out[27 + outPos] = (byte)((in[7 + inPos] & 1073741823) >>> 16 & 255);
         out[28 + outPos] = (byte)((in[7 + inPos] & 1073741823) >>> 8 & 255);
         out[29 + outPos] = (byte)(in[7 + inPos] & 1073741823 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & 1073741823) >>> 22 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & 1073741823) >>> 14 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & 1073741823) >>> 6 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & 1073741823) << 2 | (in[1 + inPos] & 1073741823) >>> 28) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & 1073741823) >>> 20 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & 1073741823) >>> 12 & 255);
         out[6 + outPos] = (byte)((in[1 + inPos] & 1073741823) >>> 4 & 255);
         out[7 + outPos] = (byte)(((in[1 + inPos] & 1073741823) << 4 | (in[2 + inPos] & 1073741823) >>> 26) & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & 1073741823) >>> 18 & 255);
         out[9 + outPos] = (byte)((in[2 + inPos] & 1073741823) >>> 10 & 255);
         out[10 + outPos] = (byte)((in[2 + inPos] & 1073741823) >>> 2 & 255);
         out[11 + outPos] = (byte)(((in[2 + inPos] & 1073741823) << 6 | (in[3 + inPos] & 1073741823) >>> 24) & 255);
         out[12 + outPos] = (byte)((in[3 + inPos] & 1073741823) >>> 16 & 255);
         out[13 + outPos] = (byte)((in[3 + inPos] & 1073741823) >>> 8 & 255);
         out[14 + outPos] = (byte)(in[3 + inPos] & 1073741823 & 255);
         out[15 + outPos] = (byte)((in[4 + inPos] & 1073741823) >>> 22 & 255);
         out[16 + outPos] = (byte)((in[4 + inPos] & 1073741823) >>> 14 & 255);
         out[17 + outPos] = (byte)((in[4 + inPos] & 1073741823) >>> 6 & 255);
         out[18 + outPos] = (byte)(((in[4 + inPos] & 1073741823) << 2 | (in[5 + inPos] & 1073741823) >>> 28) & 255);
         out[19 + outPos] = (byte)((in[5 + inPos] & 1073741823) >>> 20 & 255);
         out[20 + outPos] = (byte)((in[5 + inPos] & 1073741823) >>> 12 & 255);
         out[21 + outPos] = (byte)((in[5 + inPos] & 1073741823) >>> 4 & 255);
         out[22 + outPos] = (byte)(((in[5 + inPos] & 1073741823) << 4 | (in[6 + inPos] & 1073741823) >>> 26) & 255);
         out[23 + outPos] = (byte)((in[6 + inPos] & 1073741823) >>> 18 & 255);
         out[24 + outPos] = (byte)((in[6 + inPos] & 1073741823) >>> 10 & 255);
         out[25 + outPos] = (byte)((in[6 + inPos] & 1073741823) >>> 2 & 255);
         out[26 + outPos] = (byte)(((in[6 + inPos] & 1073741823) << 6 | (in[7 + inPos] & 1073741823) >>> 24) & 255);
         out[27 + outPos] = (byte)((in[7 + inPos] & 1073741823) >>> 16 & 255);
         out[28 + outPos] = (byte)((in[7 + inPos] & 1073741823) >>> 8 & 255);
         out[29 + outPos] = (byte)(in[7 + inPos] & 1073741823 & 255);
         out[30 + outPos] = (byte)((in[8 + inPos] & 1073741823) >>> 22 & 255);
         out[31 + outPos] = (byte)((in[8 + inPos] & 1073741823) >>> 14 & 255);
         out[32 + outPos] = (byte)((in[8 + inPos] & 1073741823) >>> 6 & 255);
         out[33 + outPos] = (byte)(((in[8 + inPos] & 1073741823) << 2 | (in[9 + inPos] & 1073741823) >>> 28) & 255);
         out[34 + outPos] = (byte)((in[9 + inPos] & 1073741823) >>> 20 & 255);
         out[35 + outPos] = (byte)((in[9 + inPos] & 1073741823) >>> 12 & 255);
         out[36 + outPos] = (byte)((in[9 + inPos] & 1073741823) >>> 4 & 255);
         out[37 + outPos] = (byte)(((in[9 + inPos] & 1073741823) << 4 | (in[10 + inPos] & 1073741823) >>> 26) & 255);
         out[38 + outPos] = (byte)((in[10 + inPos] & 1073741823) >>> 18 & 255);
         out[39 + outPos] = (byte)((in[10 + inPos] & 1073741823) >>> 10 & 255);
         out[40 + outPos] = (byte)((in[10 + inPos] & 1073741823) >>> 2 & 255);
         out[41 + outPos] = (byte)(((in[10 + inPos] & 1073741823) << 6 | (in[11 + inPos] & 1073741823) >>> 24) & 255);
         out[42 + outPos] = (byte)((in[11 + inPos] & 1073741823) >>> 16 & 255);
         out[43 + outPos] = (byte)((in[11 + inPos] & 1073741823) >>> 8 & 255);
         out[44 + outPos] = (byte)(in[11 + inPos] & 1073741823 & 255);
         out[45 + outPos] = (byte)((in[12 + inPos] & 1073741823) >>> 22 & 255);
         out[46 + outPos] = (byte)((in[12 + inPos] & 1073741823) >>> 14 & 255);
         out[47 + outPos] = (byte)((in[12 + inPos] & 1073741823) >>> 6 & 255);
         out[48 + outPos] = (byte)(((in[12 + inPos] & 1073741823) << 2 | (in[13 + inPos] & 1073741823) >>> 28) & 255);
         out[49 + outPos] = (byte)((in[13 + inPos] & 1073741823) >>> 20 & 255);
         out[50 + outPos] = (byte)((in[13 + inPos] & 1073741823) >>> 12 & 255);
         out[51 + outPos] = (byte)((in[13 + inPos] & 1073741823) >>> 4 & 255);
         out[52 + outPos] = (byte)(((in[13 + inPos] & 1073741823) << 4 | (in[14 + inPos] & 1073741823) >>> 26) & 255);
         out[53 + outPos] = (byte)((in[14 + inPos] & 1073741823) >>> 18 & 255);
         out[54 + outPos] = (byte)((in[14 + inPos] & 1073741823) >>> 10 & 255);
         out[55 + outPos] = (byte)((in[14 + inPos] & 1073741823) >>> 2 & 255);
         out[56 + outPos] = (byte)(((in[14 + inPos] & 1073741823) << 6 | (in[15 + inPos] & 1073741823) >>> 24) & 255);
         out[57 + outPos] = (byte)((in[15 + inPos] & 1073741823) >>> 16 & 255);
         out[58 + outPos] = (byte)((in[15 + inPos] & 1073741823) >>> 8 & 255);
         out[59 + outPos] = (byte)(in[15 + inPos] & 1073741823 & 255);
         out[60 + outPos] = (byte)((in[16 + inPos] & 1073741823) >>> 22 & 255);
         out[61 + outPos] = (byte)((in[16 + inPos] & 1073741823) >>> 14 & 255);
         out[62 + outPos] = (byte)((in[16 + inPos] & 1073741823) >>> 6 & 255);
         out[63 + outPos] = (byte)(((in[16 + inPos] & 1073741823) << 2 | (in[17 + inPos] & 1073741823) >>> 28) & 255);
         out[64 + outPos] = (byte)((in[17 + inPos] & 1073741823) >>> 20 & 255);
         out[65 + outPos] = (byte)((in[17 + inPos] & 1073741823) >>> 12 & 255);
         out[66 + outPos] = (byte)((in[17 + inPos] & 1073741823) >>> 4 & 255);
         out[67 + outPos] = (byte)(((in[17 + inPos] & 1073741823) << 4 | (in[18 + inPos] & 1073741823) >>> 26) & 255);
         out[68 + outPos] = (byte)((in[18 + inPos] & 1073741823) >>> 18 & 255);
         out[69 + outPos] = (byte)((in[18 + inPos] & 1073741823) >>> 10 & 255);
         out[70 + outPos] = (byte)((in[18 + inPos] & 1073741823) >>> 2 & 255);
         out[71 + outPos] = (byte)(((in[18 + inPos] & 1073741823) << 6 | (in[19 + inPos] & 1073741823) >>> 24) & 255);
         out[72 + outPos] = (byte)((in[19 + inPos] & 1073741823) >>> 16 & 255);
         out[73 + outPos] = (byte)((in[19 + inPos] & 1073741823) >>> 8 & 255);
         out[74 + outPos] = (byte)(in[19 + inPos] & 1073741823 & 255);
         out[75 + outPos] = (byte)((in[20 + inPos] & 1073741823) >>> 22 & 255);
         out[76 + outPos] = (byte)((in[20 + inPos] & 1073741823) >>> 14 & 255);
         out[77 + outPos] = (byte)((in[20 + inPos] & 1073741823) >>> 6 & 255);
         out[78 + outPos] = (byte)(((in[20 + inPos] & 1073741823) << 2 | (in[21 + inPos] & 1073741823) >>> 28) & 255);
         out[79 + outPos] = (byte)((in[21 + inPos] & 1073741823) >>> 20 & 255);
         out[80 + outPos] = (byte)((in[21 + inPos] & 1073741823) >>> 12 & 255);
         out[81 + outPos] = (byte)((in[21 + inPos] & 1073741823) >>> 4 & 255);
         out[82 + outPos] = (byte)(((in[21 + inPos] & 1073741823) << 4 | (in[22 + inPos] & 1073741823) >>> 26) & 255);
         out[83 + outPos] = (byte)((in[22 + inPos] & 1073741823) >>> 18 & 255);
         out[84 + outPos] = (byte)((in[22 + inPos] & 1073741823) >>> 10 & 255);
         out[85 + outPos] = (byte)((in[22 + inPos] & 1073741823) >>> 2 & 255);
         out[86 + outPos] = (byte)(((in[22 + inPos] & 1073741823) << 6 | (in[23 + inPos] & 1073741823) >>> 24) & 255);
         out[87 + outPos] = (byte)((in[23 + inPos] & 1073741823) >>> 16 & 255);
         out[88 + outPos] = (byte)((in[23 + inPos] & 1073741823) >>> 8 & 255);
         out[89 + outPos] = (byte)(in[23 + inPos] & 1073741823 & 255);
         out[90 + outPos] = (byte)((in[24 + inPos] & 1073741823) >>> 22 & 255);
         out[91 + outPos] = (byte)((in[24 + inPos] & 1073741823) >>> 14 & 255);
         out[92 + outPos] = (byte)((in[24 + inPos] & 1073741823) >>> 6 & 255);
         out[93 + outPos] = (byte)(((in[24 + inPos] & 1073741823) << 2 | (in[25 + inPos] & 1073741823) >>> 28) & 255);
         out[94 + outPos] = (byte)((in[25 + inPos] & 1073741823) >>> 20 & 255);
         out[95 + outPos] = (byte)((in[25 + inPos] & 1073741823) >>> 12 & 255);
         out[96 + outPos] = (byte)((in[25 + inPos] & 1073741823) >>> 4 & 255);
         out[97 + outPos] = (byte)(((in[25 + inPos] & 1073741823) << 4 | (in[26 + inPos] & 1073741823) >>> 26) & 255);
         out[98 + outPos] = (byte)((in[26 + inPos] & 1073741823) >>> 18 & 255);
         out[99 + outPos] = (byte)((in[26 + inPos] & 1073741823) >>> 10 & 255);
         out[100 + outPos] = (byte)((in[26 + inPos] & 1073741823) >>> 2 & 255);
         out[101 + outPos] = (byte)(((in[26 + inPos] & 1073741823) << 6 | (in[27 + inPos] & 1073741823) >>> 24) & 255);
         out[102 + outPos] = (byte)((in[27 + inPos] & 1073741823) >>> 16 & 255);
         out[103 + outPos] = (byte)((in[27 + inPos] & 1073741823) >>> 8 & 255);
         out[104 + outPos] = (byte)(in[27 + inPos] & 1073741823 & 255);
         out[105 + outPos] = (byte)((in[28 + inPos] & 1073741823) >>> 22 & 255);
         out[106 + outPos] = (byte)((in[28 + inPos] & 1073741823) >>> 14 & 255);
         out[107 + outPos] = (byte)((in[28 + inPos] & 1073741823) >>> 6 & 255);
         out[108 + outPos] = (byte)(((in[28 + inPos] & 1073741823) << 2 | (in[29 + inPos] & 1073741823) >>> 28) & 255);
         out[109 + outPos] = (byte)((in[29 + inPos] & 1073741823) >>> 20 & 255);
         out[110 + outPos] = (byte)((in[29 + inPos] & 1073741823) >>> 12 & 255);
         out[111 + outPos] = (byte)((in[29 + inPos] & 1073741823) >>> 4 & 255);
         out[112 + outPos] = (byte)(((in[29 + inPos] & 1073741823) << 4 | (in[30 + inPos] & 1073741823) >>> 26) & 255);
         out[113 + outPos] = (byte)((in[30 + inPos] & 1073741823) >>> 18 & 255);
         out[114 + outPos] = (byte)((in[30 + inPos] & 1073741823) >>> 10 & 255);
         out[115 + outPos] = (byte)((in[30 + inPos] & 1073741823) >>> 2 & 255);
         out[116 + outPos] = (byte)(((in[30 + inPos] & 1073741823) << 6 | (in[31 + inPos] & 1073741823) >>> 24) & 255);
         out[117 + outPos] = (byte)((in[31 + inPos] & 1073741823) >>> 16 & 255);
         out[118 + outPos] = (byte)((in[31 + inPos] & 1073741823) >>> 8 & 255);
         out[119 + outPos] = (byte)(in[31 + inPos] & 1073741823 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 22 & 1073741823 | in[1 + inPos] << 14 & 4194303 | in[2 + inPos] << 6 & 16383 | in[3 + inPos] >> 2 & 63;
         out[1 + outPos] = in[3 + inPos] << 28 & 1073741823 | in[4 + inPos] << 20 & 268435455 | in[5 + inPos] << 12 & 1048575 | in[6 + inPos] << 4 & 4095 | in[7 + inPos] >> 4 & 15;
         out[2 + outPos] = in[7 + inPos] << 26 & 1073741823 | in[8 + inPos] << 18 & 67108863 | in[9 + inPos] << 10 & 262143 | in[10 + inPos] << 2 & 1023 | in[11 + inPos] >> 6 & 3;
         out[3 + outPos] = in[11 + inPos] << 24 & 1073741823 | in[12 + inPos] << 16 & 16777215 | in[13 + inPos] << 8 & '\uffff' | in[14 + inPos] & 255;
         out[4 + outPos] = in[15 + inPos] << 22 & 1073741823 | in[16 + inPos] << 14 & 4194303 | in[17 + inPos] << 6 & 16383 | in[18 + inPos] >> 2 & 63;
         out[5 + outPos] = in[18 + inPos] << 28 & 1073741823 | in[19 + inPos] << 20 & 268435455 | in[20 + inPos] << 12 & 1048575 | in[21 + inPos] << 4 & 4095 | in[22 + inPos] >> 4 & 15;
         out[6 + outPos] = in[22 + inPos] << 26 & 1073741823 | in[23 + inPos] << 18 & 67108863 | in[24 + inPos] << 10 & 262143 | in[25 + inPos] << 2 & 1023 | in[26 + inPos] >> 6 & 3;
         out[7 + outPos] = in[26 + inPos] << 24 & 1073741823 | in[27 + inPos] << 16 & 16777215 | in[28 + inPos] << 8 & '\uffff' | in[29 + inPos] & 255;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 22 & 1073741823 | in.get(1 + inPos) << 14 & 4194303 | in.get(2 + inPos) << 6 & 16383 | in.get(3 + inPos) >> 2 & 63;
         out[1 + outPos] = in.get(3 + inPos) << 28 & 1073741823 | in.get(4 + inPos) << 20 & 268435455 | in.get(5 + inPos) << 12 & 1048575 | in.get(6 + inPos) << 4 & 4095 | in.get(7 + inPos) >> 4 & 15;
         out[2 + outPos] = in.get(7 + inPos) << 26 & 1073741823 | in.get(8 + inPos) << 18 & 67108863 | in.get(9 + inPos) << 10 & 262143 | in.get(10 + inPos) << 2 & 1023 | in.get(11 + inPos) >> 6 & 3;
         out[3 + outPos] = in.get(11 + inPos) << 24 & 1073741823 | in.get(12 + inPos) << 16 & 16777215 | in.get(13 + inPos) << 8 & '\uffff' | in.get(14 + inPos) & 255;
         out[4 + outPos] = in.get(15 + inPos) << 22 & 1073741823 | in.get(16 + inPos) << 14 & 4194303 | in.get(17 + inPos) << 6 & 16383 | in.get(18 + inPos) >> 2 & 63;
         out[5 + outPos] = in.get(18 + inPos) << 28 & 1073741823 | in.get(19 + inPos) << 20 & 268435455 | in.get(20 + inPos) << 12 & 1048575 | in.get(21 + inPos) << 4 & 4095 | in.get(22 + inPos) >> 4 & 15;
         out[6 + outPos] = in.get(22 + inPos) << 26 & 1073741823 | in.get(23 + inPos) << 18 & 67108863 | in.get(24 + inPos) << 10 & 262143 | in.get(25 + inPos) << 2 & 1023 | in.get(26 + inPos) >> 6 & 3;
         out[7 + outPos] = in.get(26 + inPos) << 24 & 1073741823 | in.get(27 + inPos) << 16 & 16777215 | in.get(28 + inPos) << 8 & '\uffff' | in.get(29 + inPos) & 255;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 22 & 1073741823 | in[1 + inPos] << 14 & 4194303 | in[2 + inPos] << 6 & 16383 | in[3 + inPos] >> 2 & 63;
         out[1 + outPos] = in[3 + inPos] << 28 & 1073741823 | in[4 + inPos] << 20 & 268435455 | in[5 + inPos] << 12 & 1048575 | in[6 + inPos] << 4 & 4095 | in[7 + inPos] >> 4 & 15;
         out[2 + outPos] = in[7 + inPos] << 26 & 1073741823 | in[8 + inPos] << 18 & 67108863 | in[9 + inPos] << 10 & 262143 | in[10 + inPos] << 2 & 1023 | in[11 + inPos] >> 6 & 3;
         out[3 + outPos] = in[11 + inPos] << 24 & 1073741823 | in[12 + inPos] << 16 & 16777215 | in[13 + inPos] << 8 & '\uffff' | in[14 + inPos] & 255;
         out[4 + outPos] = in[15 + inPos] << 22 & 1073741823 | in[16 + inPos] << 14 & 4194303 | in[17 + inPos] << 6 & 16383 | in[18 + inPos] >> 2 & 63;
         out[5 + outPos] = in[18 + inPos] << 28 & 1073741823 | in[19 + inPos] << 20 & 268435455 | in[20 + inPos] << 12 & 1048575 | in[21 + inPos] << 4 & 4095 | in[22 + inPos] >> 4 & 15;
         out[6 + outPos] = in[22 + inPos] << 26 & 1073741823 | in[23 + inPos] << 18 & 67108863 | in[24 + inPos] << 10 & 262143 | in[25 + inPos] << 2 & 1023 | in[26 + inPos] >> 6 & 3;
         out[7 + outPos] = in[26 + inPos] << 24 & 1073741823 | in[27 + inPos] << 16 & 16777215 | in[28 + inPos] << 8 & '\uffff' | in[29 + inPos] & 255;
         out[8 + outPos] = in[30 + inPos] << 22 & 1073741823 | in[31 + inPos] << 14 & 4194303 | in[32 + inPos] << 6 & 16383 | in[33 + inPos] >> 2 & 63;
         out[9 + outPos] = in[33 + inPos] << 28 & 1073741823 | in[34 + inPos] << 20 & 268435455 | in[35 + inPos] << 12 & 1048575 | in[36 + inPos] << 4 & 4095 | in[37 + inPos] >> 4 & 15;
         out[10 + outPos] = in[37 + inPos] << 26 & 1073741823 | in[38 + inPos] << 18 & 67108863 | in[39 + inPos] << 10 & 262143 | in[40 + inPos] << 2 & 1023 | in[41 + inPos] >> 6 & 3;
         out[11 + outPos] = in[41 + inPos] << 24 & 1073741823 | in[42 + inPos] << 16 & 16777215 | in[43 + inPos] << 8 & '\uffff' | in[44 + inPos] & 255;
         out[12 + outPos] = in[45 + inPos] << 22 & 1073741823 | in[46 + inPos] << 14 & 4194303 | in[47 + inPos] << 6 & 16383 | in[48 + inPos] >> 2 & 63;
         out[13 + outPos] = in[48 + inPos] << 28 & 1073741823 | in[49 + inPos] << 20 & 268435455 | in[50 + inPos] << 12 & 1048575 | in[51 + inPos] << 4 & 4095 | in[52 + inPos] >> 4 & 15;
         out[14 + outPos] = in[52 + inPos] << 26 & 1073741823 | in[53 + inPos] << 18 & 67108863 | in[54 + inPos] << 10 & 262143 | in[55 + inPos] << 2 & 1023 | in[56 + inPos] >> 6 & 3;
         out[15 + outPos] = in[56 + inPos] << 24 & 1073741823 | in[57 + inPos] << 16 & 16777215 | in[58 + inPos] << 8 & '\uffff' | in[59 + inPos] & 255;
         out[16 + outPos] = in[60 + inPos] << 22 & 1073741823 | in[61 + inPos] << 14 & 4194303 | in[62 + inPos] << 6 & 16383 | in[63 + inPos] >> 2 & 63;
         out[17 + outPos] = in[63 + inPos] << 28 & 1073741823 | in[64 + inPos] << 20 & 268435455 | in[65 + inPos] << 12 & 1048575 | in[66 + inPos] << 4 & 4095 | in[67 + inPos] >> 4 & 15;
         out[18 + outPos] = in[67 + inPos] << 26 & 1073741823 | in[68 + inPos] << 18 & 67108863 | in[69 + inPos] << 10 & 262143 | in[70 + inPos] << 2 & 1023 | in[71 + inPos] >> 6 & 3;
         out[19 + outPos] = in[71 + inPos] << 24 & 1073741823 | in[72 + inPos] << 16 & 16777215 | in[73 + inPos] << 8 & '\uffff' | in[74 + inPos] & 255;
         out[20 + outPos] = in[75 + inPos] << 22 & 1073741823 | in[76 + inPos] << 14 & 4194303 | in[77 + inPos] << 6 & 16383 | in[78 + inPos] >> 2 & 63;
         out[21 + outPos] = in[78 + inPos] << 28 & 1073741823 | in[79 + inPos] << 20 & 268435455 | in[80 + inPos] << 12 & 1048575 | in[81 + inPos] << 4 & 4095 | in[82 + inPos] >> 4 & 15;
         out[22 + outPos] = in[82 + inPos] << 26 & 1073741823 | in[83 + inPos] << 18 & 67108863 | in[84 + inPos] << 10 & 262143 | in[85 + inPos] << 2 & 1023 | in[86 + inPos] >> 6 & 3;
         out[23 + outPos] = in[86 + inPos] << 24 & 1073741823 | in[87 + inPos] << 16 & 16777215 | in[88 + inPos] << 8 & '\uffff' | in[89 + inPos] & 255;
         out[24 + outPos] = in[90 + inPos] << 22 & 1073741823 | in[91 + inPos] << 14 & 4194303 | in[92 + inPos] << 6 & 16383 | in[93 + inPos] >> 2 & 63;
         out[25 + outPos] = in[93 + inPos] << 28 & 1073741823 | in[94 + inPos] << 20 & 268435455 | in[95 + inPos] << 12 & 1048575 | in[96 + inPos] << 4 & 4095 | in[97 + inPos] >> 4 & 15;
         out[26 + outPos] = in[97 + inPos] << 26 & 1073741823 | in[98 + inPos] << 18 & 67108863 | in[99 + inPos] << 10 & 262143 | in[100 + inPos] << 2 & 1023 | in[101 + inPos] >> 6 & 3;
         out[27 + outPos] = in[101 + inPos] << 24 & 1073741823 | in[102 + inPos] << 16 & 16777215 | in[103 + inPos] << 8 & '\uffff' | in[104 + inPos] & 255;
         out[28 + outPos] = in[105 + inPos] << 22 & 1073741823 | in[106 + inPos] << 14 & 4194303 | in[107 + inPos] << 6 & 16383 | in[108 + inPos] >> 2 & 63;
         out[29 + outPos] = in[108 + inPos] << 28 & 1073741823 | in[109 + inPos] << 20 & 268435455 | in[110 + inPos] << 12 & 1048575 | in[111 + inPos] << 4 & 4095 | in[112 + inPos] >> 4 & 15;
         out[30 + outPos] = in[112 + inPos] << 26 & 1073741823 | in[113 + inPos] << 18 & 67108863 | in[114 + inPos] << 10 & 262143 | in[115 + inPos] << 2 & 1023 | in[116 + inPos] >> 6 & 3;
         out[31 + outPos] = in[116 + inPos] << 24 & 1073741823 | in[117 + inPos] << 16 & 16777215 | in[118 + inPos] << 8 & '\uffff' | in[119 + inPos] & 255;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 22 & 1073741823 | in.get(1 + inPos) << 14 & 4194303 | in.get(2 + inPos) << 6 & 16383 | in.get(3 + inPos) >> 2 & 63;
         out[1 + outPos] = in.get(3 + inPos) << 28 & 1073741823 | in.get(4 + inPos) << 20 & 268435455 | in.get(5 + inPos) << 12 & 1048575 | in.get(6 + inPos) << 4 & 4095 | in.get(7 + inPos) >> 4 & 15;
         out[2 + outPos] = in.get(7 + inPos) << 26 & 1073741823 | in.get(8 + inPos) << 18 & 67108863 | in.get(9 + inPos) << 10 & 262143 | in.get(10 + inPos) << 2 & 1023 | in.get(11 + inPos) >> 6 & 3;
         out[3 + outPos] = in.get(11 + inPos) << 24 & 1073741823 | in.get(12 + inPos) << 16 & 16777215 | in.get(13 + inPos) << 8 & '\uffff' | in.get(14 + inPos) & 255;
         out[4 + outPos] = in.get(15 + inPos) << 22 & 1073741823 | in.get(16 + inPos) << 14 & 4194303 | in.get(17 + inPos) << 6 & 16383 | in.get(18 + inPos) >> 2 & 63;
         out[5 + outPos] = in.get(18 + inPos) << 28 & 1073741823 | in.get(19 + inPos) << 20 & 268435455 | in.get(20 + inPos) << 12 & 1048575 | in.get(21 + inPos) << 4 & 4095 | in.get(22 + inPos) >> 4 & 15;
         out[6 + outPos] = in.get(22 + inPos) << 26 & 1073741823 | in.get(23 + inPos) << 18 & 67108863 | in.get(24 + inPos) << 10 & 262143 | in.get(25 + inPos) << 2 & 1023 | in.get(26 + inPos) >> 6 & 3;
         out[7 + outPos] = in.get(26 + inPos) << 24 & 1073741823 | in.get(27 + inPos) << 16 & 16777215 | in.get(28 + inPos) << 8 & '\uffff' | in.get(29 + inPos) & 255;
         out[8 + outPos] = in.get(30 + inPos) << 22 & 1073741823 | in.get(31 + inPos) << 14 & 4194303 | in.get(32 + inPos) << 6 & 16383 | in.get(33 + inPos) >> 2 & 63;
         out[9 + outPos] = in.get(33 + inPos) << 28 & 1073741823 | in.get(34 + inPos) << 20 & 268435455 | in.get(35 + inPos) << 12 & 1048575 | in.get(36 + inPos) << 4 & 4095 | in.get(37 + inPos) >> 4 & 15;
         out[10 + outPos] = in.get(37 + inPos) << 26 & 1073741823 | in.get(38 + inPos) << 18 & 67108863 | in.get(39 + inPos) << 10 & 262143 | in.get(40 + inPos) << 2 & 1023 | in.get(41 + inPos) >> 6 & 3;
         out[11 + outPos] = in.get(41 + inPos) << 24 & 1073741823 | in.get(42 + inPos) << 16 & 16777215 | in.get(43 + inPos) << 8 & '\uffff' | in.get(44 + inPos) & 255;
         out[12 + outPos] = in.get(45 + inPos) << 22 & 1073741823 | in.get(46 + inPos) << 14 & 4194303 | in.get(47 + inPos) << 6 & 16383 | in.get(48 + inPos) >> 2 & 63;
         out[13 + outPos] = in.get(48 + inPos) << 28 & 1073741823 | in.get(49 + inPos) << 20 & 268435455 | in.get(50 + inPos) << 12 & 1048575 | in.get(51 + inPos) << 4 & 4095 | in.get(52 + inPos) >> 4 & 15;
         out[14 + outPos] = in.get(52 + inPos) << 26 & 1073741823 | in.get(53 + inPos) << 18 & 67108863 | in.get(54 + inPos) << 10 & 262143 | in.get(55 + inPos) << 2 & 1023 | in.get(56 + inPos) >> 6 & 3;
         out[15 + outPos] = in.get(56 + inPos) << 24 & 1073741823 | in.get(57 + inPos) << 16 & 16777215 | in.get(58 + inPos) << 8 & '\uffff' | in.get(59 + inPos) & 255;
         out[16 + outPos] = in.get(60 + inPos) << 22 & 1073741823 | in.get(61 + inPos) << 14 & 4194303 | in.get(62 + inPos) << 6 & 16383 | in.get(63 + inPos) >> 2 & 63;
         out[17 + outPos] = in.get(63 + inPos) << 28 & 1073741823 | in.get(64 + inPos) << 20 & 268435455 | in.get(65 + inPos) << 12 & 1048575 | in.get(66 + inPos) << 4 & 4095 | in.get(67 + inPos) >> 4 & 15;
         out[18 + outPos] = in.get(67 + inPos) << 26 & 1073741823 | in.get(68 + inPos) << 18 & 67108863 | in.get(69 + inPos) << 10 & 262143 | in.get(70 + inPos) << 2 & 1023 | in.get(71 + inPos) >> 6 & 3;
         out[19 + outPos] = in.get(71 + inPos) << 24 & 1073741823 | in.get(72 + inPos) << 16 & 16777215 | in.get(73 + inPos) << 8 & '\uffff' | in.get(74 + inPos) & 255;
         out[20 + outPos] = in.get(75 + inPos) << 22 & 1073741823 | in.get(76 + inPos) << 14 & 4194303 | in.get(77 + inPos) << 6 & 16383 | in.get(78 + inPos) >> 2 & 63;
         out[21 + outPos] = in.get(78 + inPos) << 28 & 1073741823 | in.get(79 + inPos) << 20 & 268435455 | in.get(80 + inPos) << 12 & 1048575 | in.get(81 + inPos) << 4 & 4095 | in.get(82 + inPos) >> 4 & 15;
         out[22 + outPos] = in.get(82 + inPos) << 26 & 1073741823 | in.get(83 + inPos) << 18 & 67108863 | in.get(84 + inPos) << 10 & 262143 | in.get(85 + inPos) << 2 & 1023 | in.get(86 + inPos) >> 6 & 3;
         out[23 + outPos] = in.get(86 + inPos) << 24 & 1073741823 | in.get(87 + inPos) << 16 & 16777215 | in.get(88 + inPos) << 8 & '\uffff' | in.get(89 + inPos) & 255;
         out[24 + outPos] = in.get(90 + inPos) << 22 & 1073741823 | in.get(91 + inPos) << 14 & 4194303 | in.get(92 + inPos) << 6 & 16383 | in.get(93 + inPos) >> 2 & 63;
         out[25 + outPos] = in.get(93 + inPos) << 28 & 1073741823 | in.get(94 + inPos) << 20 & 268435455 | in.get(95 + inPos) << 12 & 1048575 | in.get(96 + inPos) << 4 & 4095 | in.get(97 + inPos) >> 4 & 15;
         out[26 + outPos] = in.get(97 + inPos) << 26 & 1073741823 | in.get(98 + inPos) << 18 & 67108863 | in.get(99 + inPos) << 10 & 262143 | in.get(100 + inPos) << 2 & 1023 | in.get(101 + inPos) >> 6 & 3;
         out[27 + outPos] = in.get(101 + inPos) << 24 & 1073741823 | in.get(102 + inPos) << 16 & 16777215 | in.get(103 + inPos) << 8 & '\uffff' | in.get(104 + inPos) & 255;
         out[28 + outPos] = in.get(105 + inPos) << 22 & 1073741823 | in.get(106 + inPos) << 14 & 4194303 | in.get(107 + inPos) << 6 & 16383 | in.get(108 + inPos) >> 2 & 63;
         out[29 + outPos] = in.get(108 + inPos) << 28 & 1073741823 | in.get(109 + inPos) << 20 & 268435455 | in.get(110 + inPos) << 12 & 1048575 | in.get(111 + inPos) << 4 & 4095 | in.get(112 + inPos) >> 4 & 15;
         out[30 + outPos] = in.get(112 + inPos) << 26 & 1073741823 | in.get(113 + inPos) << 18 & 67108863 | in.get(114 + inPos) << 10 & 262143 | in.get(115 + inPos) << 2 & 1023 | in.get(116 + inPos) >> 6 & 3;
         out[31 + outPos] = in.get(116 + inPos) << 24 & 1073741823 | in.get(117 + inPos) << 16 & 16777215 | in.get(118 + inPos) << 8 & '\uffff' | in.get(119 + inPos) & 255;
      }
   }

   private static final class Packer31 extends BytePacker {
      private Packer31() {
         super(31);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & Integer.MAX_VALUE) >>> 23 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & Integer.MAX_VALUE) >>> 15 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & Integer.MAX_VALUE) >>> 7 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & Integer.MAX_VALUE) << 1 | (in[1 + inPos] & Integer.MAX_VALUE) >>> 30) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & Integer.MAX_VALUE) >>> 22 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & Integer.MAX_VALUE) >>> 14 & 255);
         out[6 + outPos] = (byte)((in[1 + inPos] & Integer.MAX_VALUE) >>> 6 & 255);
         out[7 + outPos] = (byte)(((in[1 + inPos] & Integer.MAX_VALUE) << 2 | (in[2 + inPos] & Integer.MAX_VALUE) >>> 29) & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & Integer.MAX_VALUE) >>> 21 & 255);
         out[9 + outPos] = (byte)((in[2 + inPos] & Integer.MAX_VALUE) >>> 13 & 255);
         out[10 + outPos] = (byte)((in[2 + inPos] & Integer.MAX_VALUE) >>> 5 & 255);
         out[11 + outPos] = (byte)(((in[2 + inPos] & Integer.MAX_VALUE) << 3 | (in[3 + inPos] & Integer.MAX_VALUE) >>> 28) & 255);
         out[12 + outPos] = (byte)((in[3 + inPos] & Integer.MAX_VALUE) >>> 20 & 255);
         out[13 + outPos] = (byte)((in[3 + inPos] & Integer.MAX_VALUE) >>> 12 & 255);
         out[14 + outPos] = (byte)((in[3 + inPos] & Integer.MAX_VALUE) >>> 4 & 255);
         out[15 + outPos] = (byte)(((in[3 + inPos] & Integer.MAX_VALUE) << 4 | (in[4 + inPos] & Integer.MAX_VALUE) >>> 27) & 255);
         out[16 + outPos] = (byte)((in[4 + inPos] & Integer.MAX_VALUE) >>> 19 & 255);
         out[17 + outPos] = (byte)((in[4 + inPos] & Integer.MAX_VALUE) >>> 11 & 255);
         out[18 + outPos] = (byte)((in[4 + inPos] & Integer.MAX_VALUE) >>> 3 & 255);
         out[19 + outPos] = (byte)(((in[4 + inPos] & Integer.MAX_VALUE) << 5 | (in[5 + inPos] & Integer.MAX_VALUE) >>> 26) & 255);
         out[20 + outPos] = (byte)((in[5 + inPos] & Integer.MAX_VALUE) >>> 18 & 255);
         out[21 + outPos] = (byte)((in[5 + inPos] & Integer.MAX_VALUE) >>> 10 & 255);
         out[22 + outPos] = (byte)((in[5 + inPos] & Integer.MAX_VALUE) >>> 2 & 255);
         out[23 + outPos] = (byte)(((in[5 + inPos] & Integer.MAX_VALUE) << 6 | (in[6 + inPos] & Integer.MAX_VALUE) >>> 25) & 255);
         out[24 + outPos] = (byte)((in[6 + inPos] & Integer.MAX_VALUE) >>> 17 & 255);
         out[25 + outPos] = (byte)((in[6 + inPos] & Integer.MAX_VALUE) >>> 9 & 255);
         out[26 + outPos] = (byte)((in[6 + inPos] & Integer.MAX_VALUE) >>> 1 & 255);
         out[27 + outPos] = (byte)(((in[6 + inPos] & Integer.MAX_VALUE) << 7 | (in[7 + inPos] & Integer.MAX_VALUE) >>> 24) & 255);
         out[28 + outPos] = (byte)((in[7 + inPos] & Integer.MAX_VALUE) >>> 16 & 255);
         out[29 + outPos] = (byte)((in[7 + inPos] & Integer.MAX_VALUE) >>> 8 & 255);
         out[30 + outPos] = (byte)(in[7 + inPos] & Integer.MAX_VALUE & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & Integer.MAX_VALUE) >>> 23 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & Integer.MAX_VALUE) >>> 15 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & Integer.MAX_VALUE) >>> 7 & 255);
         out[3 + outPos] = (byte)(((in[0 + inPos] & Integer.MAX_VALUE) << 1 | (in[1 + inPos] & Integer.MAX_VALUE) >>> 30) & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & Integer.MAX_VALUE) >>> 22 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & Integer.MAX_VALUE) >>> 14 & 255);
         out[6 + outPos] = (byte)((in[1 + inPos] & Integer.MAX_VALUE) >>> 6 & 255);
         out[7 + outPos] = (byte)(((in[1 + inPos] & Integer.MAX_VALUE) << 2 | (in[2 + inPos] & Integer.MAX_VALUE) >>> 29) & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & Integer.MAX_VALUE) >>> 21 & 255);
         out[9 + outPos] = (byte)((in[2 + inPos] & Integer.MAX_VALUE) >>> 13 & 255);
         out[10 + outPos] = (byte)((in[2 + inPos] & Integer.MAX_VALUE) >>> 5 & 255);
         out[11 + outPos] = (byte)(((in[2 + inPos] & Integer.MAX_VALUE) << 3 | (in[3 + inPos] & Integer.MAX_VALUE) >>> 28) & 255);
         out[12 + outPos] = (byte)((in[3 + inPos] & Integer.MAX_VALUE) >>> 20 & 255);
         out[13 + outPos] = (byte)((in[3 + inPos] & Integer.MAX_VALUE) >>> 12 & 255);
         out[14 + outPos] = (byte)((in[3 + inPos] & Integer.MAX_VALUE) >>> 4 & 255);
         out[15 + outPos] = (byte)(((in[3 + inPos] & Integer.MAX_VALUE) << 4 | (in[4 + inPos] & Integer.MAX_VALUE) >>> 27) & 255);
         out[16 + outPos] = (byte)((in[4 + inPos] & Integer.MAX_VALUE) >>> 19 & 255);
         out[17 + outPos] = (byte)((in[4 + inPos] & Integer.MAX_VALUE) >>> 11 & 255);
         out[18 + outPos] = (byte)((in[4 + inPos] & Integer.MAX_VALUE) >>> 3 & 255);
         out[19 + outPos] = (byte)(((in[4 + inPos] & Integer.MAX_VALUE) << 5 | (in[5 + inPos] & Integer.MAX_VALUE) >>> 26) & 255);
         out[20 + outPos] = (byte)((in[5 + inPos] & Integer.MAX_VALUE) >>> 18 & 255);
         out[21 + outPos] = (byte)((in[5 + inPos] & Integer.MAX_VALUE) >>> 10 & 255);
         out[22 + outPos] = (byte)((in[5 + inPos] & Integer.MAX_VALUE) >>> 2 & 255);
         out[23 + outPos] = (byte)(((in[5 + inPos] & Integer.MAX_VALUE) << 6 | (in[6 + inPos] & Integer.MAX_VALUE) >>> 25) & 255);
         out[24 + outPos] = (byte)((in[6 + inPos] & Integer.MAX_VALUE) >>> 17 & 255);
         out[25 + outPos] = (byte)((in[6 + inPos] & Integer.MAX_VALUE) >>> 9 & 255);
         out[26 + outPos] = (byte)((in[6 + inPos] & Integer.MAX_VALUE) >>> 1 & 255);
         out[27 + outPos] = (byte)(((in[6 + inPos] & Integer.MAX_VALUE) << 7 | (in[7 + inPos] & Integer.MAX_VALUE) >>> 24) & 255);
         out[28 + outPos] = (byte)((in[7 + inPos] & Integer.MAX_VALUE) >>> 16 & 255);
         out[29 + outPos] = (byte)((in[7 + inPos] & Integer.MAX_VALUE) >>> 8 & 255);
         out[30 + outPos] = (byte)(in[7 + inPos] & Integer.MAX_VALUE & 255);
         out[31 + outPos] = (byte)((in[8 + inPos] & Integer.MAX_VALUE) >>> 23 & 255);
         out[32 + outPos] = (byte)((in[8 + inPos] & Integer.MAX_VALUE) >>> 15 & 255);
         out[33 + outPos] = (byte)((in[8 + inPos] & Integer.MAX_VALUE) >>> 7 & 255);
         out[34 + outPos] = (byte)(((in[8 + inPos] & Integer.MAX_VALUE) << 1 | (in[9 + inPos] & Integer.MAX_VALUE) >>> 30) & 255);
         out[35 + outPos] = (byte)((in[9 + inPos] & Integer.MAX_VALUE) >>> 22 & 255);
         out[36 + outPos] = (byte)((in[9 + inPos] & Integer.MAX_VALUE) >>> 14 & 255);
         out[37 + outPos] = (byte)((in[9 + inPos] & Integer.MAX_VALUE) >>> 6 & 255);
         out[38 + outPos] = (byte)(((in[9 + inPos] & Integer.MAX_VALUE) << 2 | (in[10 + inPos] & Integer.MAX_VALUE) >>> 29) & 255);
         out[39 + outPos] = (byte)((in[10 + inPos] & Integer.MAX_VALUE) >>> 21 & 255);
         out[40 + outPos] = (byte)((in[10 + inPos] & Integer.MAX_VALUE) >>> 13 & 255);
         out[41 + outPos] = (byte)((in[10 + inPos] & Integer.MAX_VALUE) >>> 5 & 255);
         out[42 + outPos] = (byte)(((in[10 + inPos] & Integer.MAX_VALUE) << 3 | (in[11 + inPos] & Integer.MAX_VALUE) >>> 28) & 255);
         out[43 + outPos] = (byte)((in[11 + inPos] & Integer.MAX_VALUE) >>> 20 & 255);
         out[44 + outPos] = (byte)((in[11 + inPos] & Integer.MAX_VALUE) >>> 12 & 255);
         out[45 + outPos] = (byte)((in[11 + inPos] & Integer.MAX_VALUE) >>> 4 & 255);
         out[46 + outPos] = (byte)(((in[11 + inPos] & Integer.MAX_VALUE) << 4 | (in[12 + inPos] & Integer.MAX_VALUE) >>> 27) & 255);
         out[47 + outPos] = (byte)((in[12 + inPos] & Integer.MAX_VALUE) >>> 19 & 255);
         out[48 + outPos] = (byte)((in[12 + inPos] & Integer.MAX_VALUE) >>> 11 & 255);
         out[49 + outPos] = (byte)((in[12 + inPos] & Integer.MAX_VALUE) >>> 3 & 255);
         out[50 + outPos] = (byte)(((in[12 + inPos] & Integer.MAX_VALUE) << 5 | (in[13 + inPos] & Integer.MAX_VALUE) >>> 26) & 255);
         out[51 + outPos] = (byte)((in[13 + inPos] & Integer.MAX_VALUE) >>> 18 & 255);
         out[52 + outPos] = (byte)((in[13 + inPos] & Integer.MAX_VALUE) >>> 10 & 255);
         out[53 + outPos] = (byte)((in[13 + inPos] & Integer.MAX_VALUE) >>> 2 & 255);
         out[54 + outPos] = (byte)(((in[13 + inPos] & Integer.MAX_VALUE) << 6 | (in[14 + inPos] & Integer.MAX_VALUE) >>> 25) & 255);
         out[55 + outPos] = (byte)((in[14 + inPos] & Integer.MAX_VALUE) >>> 17 & 255);
         out[56 + outPos] = (byte)((in[14 + inPos] & Integer.MAX_VALUE) >>> 9 & 255);
         out[57 + outPos] = (byte)((in[14 + inPos] & Integer.MAX_VALUE) >>> 1 & 255);
         out[58 + outPos] = (byte)(((in[14 + inPos] & Integer.MAX_VALUE) << 7 | (in[15 + inPos] & Integer.MAX_VALUE) >>> 24) & 255);
         out[59 + outPos] = (byte)((in[15 + inPos] & Integer.MAX_VALUE) >>> 16 & 255);
         out[60 + outPos] = (byte)((in[15 + inPos] & Integer.MAX_VALUE) >>> 8 & 255);
         out[61 + outPos] = (byte)(in[15 + inPos] & Integer.MAX_VALUE & 255);
         out[62 + outPos] = (byte)((in[16 + inPos] & Integer.MAX_VALUE) >>> 23 & 255);
         out[63 + outPos] = (byte)((in[16 + inPos] & Integer.MAX_VALUE) >>> 15 & 255);
         out[64 + outPos] = (byte)((in[16 + inPos] & Integer.MAX_VALUE) >>> 7 & 255);
         out[65 + outPos] = (byte)(((in[16 + inPos] & Integer.MAX_VALUE) << 1 | (in[17 + inPos] & Integer.MAX_VALUE) >>> 30) & 255);
         out[66 + outPos] = (byte)((in[17 + inPos] & Integer.MAX_VALUE) >>> 22 & 255);
         out[67 + outPos] = (byte)((in[17 + inPos] & Integer.MAX_VALUE) >>> 14 & 255);
         out[68 + outPos] = (byte)((in[17 + inPos] & Integer.MAX_VALUE) >>> 6 & 255);
         out[69 + outPos] = (byte)(((in[17 + inPos] & Integer.MAX_VALUE) << 2 | (in[18 + inPos] & Integer.MAX_VALUE) >>> 29) & 255);
         out[70 + outPos] = (byte)((in[18 + inPos] & Integer.MAX_VALUE) >>> 21 & 255);
         out[71 + outPos] = (byte)((in[18 + inPos] & Integer.MAX_VALUE) >>> 13 & 255);
         out[72 + outPos] = (byte)((in[18 + inPos] & Integer.MAX_VALUE) >>> 5 & 255);
         out[73 + outPos] = (byte)(((in[18 + inPos] & Integer.MAX_VALUE) << 3 | (in[19 + inPos] & Integer.MAX_VALUE) >>> 28) & 255);
         out[74 + outPos] = (byte)((in[19 + inPos] & Integer.MAX_VALUE) >>> 20 & 255);
         out[75 + outPos] = (byte)((in[19 + inPos] & Integer.MAX_VALUE) >>> 12 & 255);
         out[76 + outPos] = (byte)((in[19 + inPos] & Integer.MAX_VALUE) >>> 4 & 255);
         out[77 + outPos] = (byte)(((in[19 + inPos] & Integer.MAX_VALUE) << 4 | (in[20 + inPos] & Integer.MAX_VALUE) >>> 27) & 255);
         out[78 + outPos] = (byte)((in[20 + inPos] & Integer.MAX_VALUE) >>> 19 & 255);
         out[79 + outPos] = (byte)((in[20 + inPos] & Integer.MAX_VALUE) >>> 11 & 255);
         out[80 + outPos] = (byte)((in[20 + inPos] & Integer.MAX_VALUE) >>> 3 & 255);
         out[81 + outPos] = (byte)(((in[20 + inPos] & Integer.MAX_VALUE) << 5 | (in[21 + inPos] & Integer.MAX_VALUE) >>> 26) & 255);
         out[82 + outPos] = (byte)((in[21 + inPos] & Integer.MAX_VALUE) >>> 18 & 255);
         out[83 + outPos] = (byte)((in[21 + inPos] & Integer.MAX_VALUE) >>> 10 & 255);
         out[84 + outPos] = (byte)((in[21 + inPos] & Integer.MAX_VALUE) >>> 2 & 255);
         out[85 + outPos] = (byte)(((in[21 + inPos] & Integer.MAX_VALUE) << 6 | (in[22 + inPos] & Integer.MAX_VALUE) >>> 25) & 255);
         out[86 + outPos] = (byte)((in[22 + inPos] & Integer.MAX_VALUE) >>> 17 & 255);
         out[87 + outPos] = (byte)((in[22 + inPos] & Integer.MAX_VALUE) >>> 9 & 255);
         out[88 + outPos] = (byte)((in[22 + inPos] & Integer.MAX_VALUE) >>> 1 & 255);
         out[89 + outPos] = (byte)(((in[22 + inPos] & Integer.MAX_VALUE) << 7 | (in[23 + inPos] & Integer.MAX_VALUE) >>> 24) & 255);
         out[90 + outPos] = (byte)((in[23 + inPos] & Integer.MAX_VALUE) >>> 16 & 255);
         out[91 + outPos] = (byte)((in[23 + inPos] & Integer.MAX_VALUE) >>> 8 & 255);
         out[92 + outPos] = (byte)(in[23 + inPos] & Integer.MAX_VALUE & 255);
         out[93 + outPos] = (byte)((in[24 + inPos] & Integer.MAX_VALUE) >>> 23 & 255);
         out[94 + outPos] = (byte)((in[24 + inPos] & Integer.MAX_VALUE) >>> 15 & 255);
         out[95 + outPos] = (byte)((in[24 + inPos] & Integer.MAX_VALUE) >>> 7 & 255);
         out[96 + outPos] = (byte)(((in[24 + inPos] & Integer.MAX_VALUE) << 1 | (in[25 + inPos] & Integer.MAX_VALUE) >>> 30) & 255);
         out[97 + outPos] = (byte)((in[25 + inPos] & Integer.MAX_VALUE) >>> 22 & 255);
         out[98 + outPos] = (byte)((in[25 + inPos] & Integer.MAX_VALUE) >>> 14 & 255);
         out[99 + outPos] = (byte)((in[25 + inPos] & Integer.MAX_VALUE) >>> 6 & 255);
         out[100 + outPos] = (byte)(((in[25 + inPos] & Integer.MAX_VALUE) << 2 | (in[26 + inPos] & Integer.MAX_VALUE) >>> 29) & 255);
         out[101 + outPos] = (byte)((in[26 + inPos] & Integer.MAX_VALUE) >>> 21 & 255);
         out[102 + outPos] = (byte)((in[26 + inPos] & Integer.MAX_VALUE) >>> 13 & 255);
         out[103 + outPos] = (byte)((in[26 + inPos] & Integer.MAX_VALUE) >>> 5 & 255);
         out[104 + outPos] = (byte)(((in[26 + inPos] & Integer.MAX_VALUE) << 3 | (in[27 + inPos] & Integer.MAX_VALUE) >>> 28) & 255);
         out[105 + outPos] = (byte)((in[27 + inPos] & Integer.MAX_VALUE) >>> 20 & 255);
         out[106 + outPos] = (byte)((in[27 + inPos] & Integer.MAX_VALUE) >>> 12 & 255);
         out[107 + outPos] = (byte)((in[27 + inPos] & Integer.MAX_VALUE) >>> 4 & 255);
         out[108 + outPos] = (byte)(((in[27 + inPos] & Integer.MAX_VALUE) << 4 | (in[28 + inPos] & Integer.MAX_VALUE) >>> 27) & 255);
         out[109 + outPos] = (byte)((in[28 + inPos] & Integer.MAX_VALUE) >>> 19 & 255);
         out[110 + outPos] = (byte)((in[28 + inPos] & Integer.MAX_VALUE) >>> 11 & 255);
         out[111 + outPos] = (byte)((in[28 + inPos] & Integer.MAX_VALUE) >>> 3 & 255);
         out[112 + outPos] = (byte)(((in[28 + inPos] & Integer.MAX_VALUE) << 5 | (in[29 + inPos] & Integer.MAX_VALUE) >>> 26) & 255);
         out[113 + outPos] = (byte)((in[29 + inPos] & Integer.MAX_VALUE) >>> 18 & 255);
         out[114 + outPos] = (byte)((in[29 + inPos] & Integer.MAX_VALUE) >>> 10 & 255);
         out[115 + outPos] = (byte)((in[29 + inPos] & Integer.MAX_VALUE) >>> 2 & 255);
         out[116 + outPos] = (byte)(((in[29 + inPos] & Integer.MAX_VALUE) << 6 | (in[30 + inPos] & Integer.MAX_VALUE) >>> 25) & 255);
         out[117 + outPos] = (byte)((in[30 + inPos] & Integer.MAX_VALUE) >>> 17 & 255);
         out[118 + outPos] = (byte)((in[30 + inPos] & Integer.MAX_VALUE) >>> 9 & 255);
         out[119 + outPos] = (byte)((in[30 + inPos] & Integer.MAX_VALUE) >>> 1 & 255);
         out[120 + outPos] = (byte)(((in[30 + inPos] & Integer.MAX_VALUE) << 7 | (in[31 + inPos] & Integer.MAX_VALUE) >>> 24) & 255);
         out[121 + outPos] = (byte)((in[31 + inPos] & Integer.MAX_VALUE) >>> 16 & 255);
         out[122 + outPos] = (byte)((in[31 + inPos] & Integer.MAX_VALUE) >>> 8 & 255);
         out[123 + outPos] = (byte)(in[31 + inPos] & Integer.MAX_VALUE & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 23 & Integer.MAX_VALUE | in[1 + inPos] << 15 & 8388607 | in[2 + inPos] << 7 & 32767 | in[3 + inPos] >> 1 & 127;
         out[1 + outPos] = in[3 + inPos] << 30 & Integer.MAX_VALUE | in[4 + inPos] << 22 & 1073741823 | in[5 + inPos] << 14 & 4194303 | in[6 + inPos] << 6 & 16383 | in[7 + inPos] >> 2 & 63;
         out[2 + outPos] = in[7 + inPos] << 29 & Integer.MAX_VALUE | in[8 + inPos] << 21 & 536870911 | in[9 + inPos] << 13 & 2097151 | in[10 + inPos] << 5 & 8191 | in[11 + inPos] >> 3 & 31;
         out[3 + outPos] = in[11 + inPos] << 28 & Integer.MAX_VALUE | in[12 + inPos] << 20 & 268435455 | in[13 + inPos] << 12 & 1048575 | in[14 + inPos] << 4 & 4095 | in[15 + inPos] >> 4 & 15;
         out[4 + outPos] = in[15 + inPos] << 27 & Integer.MAX_VALUE | in[16 + inPos] << 19 & 134217727 | in[17 + inPos] << 11 & 524287 | in[18 + inPos] << 3 & 2047 | in[19 + inPos] >> 5 & 7;
         out[5 + outPos] = in[19 + inPos] << 26 & Integer.MAX_VALUE | in[20 + inPos] << 18 & 67108863 | in[21 + inPos] << 10 & 262143 | in[22 + inPos] << 2 & 1023 | in[23 + inPos] >> 6 & 3;
         out[6 + outPos] = in[23 + inPos] << 25 & Integer.MAX_VALUE | in[24 + inPos] << 17 & 33554431 | in[25 + inPos] << 9 & 131071 | in[26 + inPos] << 1 & 511 | in[27 + inPos] >> 7 & 1;
         out[7 + outPos] = in[27 + inPos] << 24 & Integer.MAX_VALUE | in[28 + inPos] << 16 & 16777215 | in[29 + inPos] << 8 & '\uffff' | in[30 + inPos] & 255;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 23 & Integer.MAX_VALUE | in.get(1 + inPos) << 15 & 8388607 | in.get(2 + inPos) << 7 & 32767 | in.get(3 + inPos) >> 1 & 127;
         out[1 + outPos] = in.get(3 + inPos) << 30 & Integer.MAX_VALUE | in.get(4 + inPos) << 22 & 1073741823 | in.get(5 + inPos) << 14 & 4194303 | in.get(6 + inPos) << 6 & 16383 | in.get(7 + inPos) >> 2 & 63;
         out[2 + outPos] = in.get(7 + inPos) << 29 & Integer.MAX_VALUE | in.get(8 + inPos) << 21 & 536870911 | in.get(9 + inPos) << 13 & 2097151 | in.get(10 + inPos) << 5 & 8191 | in.get(11 + inPos) >> 3 & 31;
         out[3 + outPos] = in.get(11 + inPos) << 28 & Integer.MAX_VALUE | in.get(12 + inPos) << 20 & 268435455 | in.get(13 + inPos) << 12 & 1048575 | in.get(14 + inPos) << 4 & 4095 | in.get(15 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(15 + inPos) << 27 & Integer.MAX_VALUE | in.get(16 + inPos) << 19 & 134217727 | in.get(17 + inPos) << 11 & 524287 | in.get(18 + inPos) << 3 & 2047 | in.get(19 + inPos) >> 5 & 7;
         out[5 + outPos] = in.get(19 + inPos) << 26 & Integer.MAX_VALUE | in.get(20 + inPos) << 18 & 67108863 | in.get(21 + inPos) << 10 & 262143 | in.get(22 + inPos) << 2 & 1023 | in.get(23 + inPos) >> 6 & 3;
         out[6 + outPos] = in.get(23 + inPos) << 25 & Integer.MAX_VALUE | in.get(24 + inPos) << 17 & 33554431 | in.get(25 + inPos) << 9 & 131071 | in.get(26 + inPos) << 1 & 511 | in.get(27 + inPos) >> 7 & 1;
         out[7 + outPos] = in.get(27 + inPos) << 24 & Integer.MAX_VALUE | in.get(28 + inPos) << 16 & 16777215 | in.get(29 + inPos) << 8 & '\uffff' | in.get(30 + inPos) & 255;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 23 & Integer.MAX_VALUE | in[1 + inPos] << 15 & 8388607 | in[2 + inPos] << 7 & 32767 | in[3 + inPos] >> 1 & 127;
         out[1 + outPos] = in[3 + inPos] << 30 & Integer.MAX_VALUE | in[4 + inPos] << 22 & 1073741823 | in[5 + inPos] << 14 & 4194303 | in[6 + inPos] << 6 & 16383 | in[7 + inPos] >> 2 & 63;
         out[2 + outPos] = in[7 + inPos] << 29 & Integer.MAX_VALUE | in[8 + inPos] << 21 & 536870911 | in[9 + inPos] << 13 & 2097151 | in[10 + inPos] << 5 & 8191 | in[11 + inPos] >> 3 & 31;
         out[3 + outPos] = in[11 + inPos] << 28 & Integer.MAX_VALUE | in[12 + inPos] << 20 & 268435455 | in[13 + inPos] << 12 & 1048575 | in[14 + inPos] << 4 & 4095 | in[15 + inPos] >> 4 & 15;
         out[4 + outPos] = in[15 + inPos] << 27 & Integer.MAX_VALUE | in[16 + inPos] << 19 & 134217727 | in[17 + inPos] << 11 & 524287 | in[18 + inPos] << 3 & 2047 | in[19 + inPos] >> 5 & 7;
         out[5 + outPos] = in[19 + inPos] << 26 & Integer.MAX_VALUE | in[20 + inPos] << 18 & 67108863 | in[21 + inPos] << 10 & 262143 | in[22 + inPos] << 2 & 1023 | in[23 + inPos] >> 6 & 3;
         out[6 + outPos] = in[23 + inPos] << 25 & Integer.MAX_VALUE | in[24 + inPos] << 17 & 33554431 | in[25 + inPos] << 9 & 131071 | in[26 + inPos] << 1 & 511 | in[27 + inPos] >> 7 & 1;
         out[7 + outPos] = in[27 + inPos] << 24 & Integer.MAX_VALUE | in[28 + inPos] << 16 & 16777215 | in[29 + inPos] << 8 & '\uffff' | in[30 + inPos] & 255;
         out[8 + outPos] = in[31 + inPos] << 23 & Integer.MAX_VALUE | in[32 + inPos] << 15 & 8388607 | in[33 + inPos] << 7 & 32767 | in[34 + inPos] >> 1 & 127;
         out[9 + outPos] = in[34 + inPos] << 30 & Integer.MAX_VALUE | in[35 + inPos] << 22 & 1073741823 | in[36 + inPos] << 14 & 4194303 | in[37 + inPos] << 6 & 16383 | in[38 + inPos] >> 2 & 63;
         out[10 + outPos] = in[38 + inPos] << 29 & Integer.MAX_VALUE | in[39 + inPos] << 21 & 536870911 | in[40 + inPos] << 13 & 2097151 | in[41 + inPos] << 5 & 8191 | in[42 + inPos] >> 3 & 31;
         out[11 + outPos] = in[42 + inPos] << 28 & Integer.MAX_VALUE | in[43 + inPos] << 20 & 268435455 | in[44 + inPos] << 12 & 1048575 | in[45 + inPos] << 4 & 4095 | in[46 + inPos] >> 4 & 15;
         out[12 + outPos] = in[46 + inPos] << 27 & Integer.MAX_VALUE | in[47 + inPos] << 19 & 134217727 | in[48 + inPos] << 11 & 524287 | in[49 + inPos] << 3 & 2047 | in[50 + inPos] >> 5 & 7;
         out[13 + outPos] = in[50 + inPos] << 26 & Integer.MAX_VALUE | in[51 + inPos] << 18 & 67108863 | in[52 + inPos] << 10 & 262143 | in[53 + inPos] << 2 & 1023 | in[54 + inPos] >> 6 & 3;
         out[14 + outPos] = in[54 + inPos] << 25 & Integer.MAX_VALUE | in[55 + inPos] << 17 & 33554431 | in[56 + inPos] << 9 & 131071 | in[57 + inPos] << 1 & 511 | in[58 + inPos] >> 7 & 1;
         out[15 + outPos] = in[58 + inPos] << 24 & Integer.MAX_VALUE | in[59 + inPos] << 16 & 16777215 | in[60 + inPos] << 8 & '\uffff' | in[61 + inPos] & 255;
         out[16 + outPos] = in[62 + inPos] << 23 & Integer.MAX_VALUE | in[63 + inPos] << 15 & 8388607 | in[64 + inPos] << 7 & 32767 | in[65 + inPos] >> 1 & 127;
         out[17 + outPos] = in[65 + inPos] << 30 & Integer.MAX_VALUE | in[66 + inPos] << 22 & 1073741823 | in[67 + inPos] << 14 & 4194303 | in[68 + inPos] << 6 & 16383 | in[69 + inPos] >> 2 & 63;
         out[18 + outPos] = in[69 + inPos] << 29 & Integer.MAX_VALUE | in[70 + inPos] << 21 & 536870911 | in[71 + inPos] << 13 & 2097151 | in[72 + inPos] << 5 & 8191 | in[73 + inPos] >> 3 & 31;
         out[19 + outPos] = in[73 + inPos] << 28 & Integer.MAX_VALUE | in[74 + inPos] << 20 & 268435455 | in[75 + inPos] << 12 & 1048575 | in[76 + inPos] << 4 & 4095 | in[77 + inPos] >> 4 & 15;
         out[20 + outPos] = in[77 + inPos] << 27 & Integer.MAX_VALUE | in[78 + inPos] << 19 & 134217727 | in[79 + inPos] << 11 & 524287 | in[80 + inPos] << 3 & 2047 | in[81 + inPos] >> 5 & 7;
         out[21 + outPos] = in[81 + inPos] << 26 & Integer.MAX_VALUE | in[82 + inPos] << 18 & 67108863 | in[83 + inPos] << 10 & 262143 | in[84 + inPos] << 2 & 1023 | in[85 + inPos] >> 6 & 3;
         out[22 + outPos] = in[85 + inPos] << 25 & Integer.MAX_VALUE | in[86 + inPos] << 17 & 33554431 | in[87 + inPos] << 9 & 131071 | in[88 + inPos] << 1 & 511 | in[89 + inPos] >> 7 & 1;
         out[23 + outPos] = in[89 + inPos] << 24 & Integer.MAX_VALUE | in[90 + inPos] << 16 & 16777215 | in[91 + inPos] << 8 & '\uffff' | in[92 + inPos] & 255;
         out[24 + outPos] = in[93 + inPos] << 23 & Integer.MAX_VALUE | in[94 + inPos] << 15 & 8388607 | in[95 + inPos] << 7 & 32767 | in[96 + inPos] >> 1 & 127;
         out[25 + outPos] = in[96 + inPos] << 30 & Integer.MAX_VALUE | in[97 + inPos] << 22 & 1073741823 | in[98 + inPos] << 14 & 4194303 | in[99 + inPos] << 6 & 16383 | in[100 + inPos] >> 2 & 63;
         out[26 + outPos] = in[100 + inPos] << 29 & Integer.MAX_VALUE | in[101 + inPos] << 21 & 536870911 | in[102 + inPos] << 13 & 2097151 | in[103 + inPos] << 5 & 8191 | in[104 + inPos] >> 3 & 31;
         out[27 + outPos] = in[104 + inPos] << 28 & Integer.MAX_VALUE | in[105 + inPos] << 20 & 268435455 | in[106 + inPos] << 12 & 1048575 | in[107 + inPos] << 4 & 4095 | in[108 + inPos] >> 4 & 15;
         out[28 + outPos] = in[108 + inPos] << 27 & Integer.MAX_VALUE | in[109 + inPos] << 19 & 134217727 | in[110 + inPos] << 11 & 524287 | in[111 + inPos] << 3 & 2047 | in[112 + inPos] >> 5 & 7;
         out[29 + outPos] = in[112 + inPos] << 26 & Integer.MAX_VALUE | in[113 + inPos] << 18 & 67108863 | in[114 + inPos] << 10 & 262143 | in[115 + inPos] << 2 & 1023 | in[116 + inPos] >> 6 & 3;
         out[30 + outPos] = in[116 + inPos] << 25 & Integer.MAX_VALUE | in[117 + inPos] << 17 & 33554431 | in[118 + inPos] << 9 & 131071 | in[119 + inPos] << 1 & 511 | in[120 + inPos] >> 7 & 1;
         out[31 + outPos] = in[120 + inPos] << 24 & Integer.MAX_VALUE | in[121 + inPos] << 16 & 16777215 | in[122 + inPos] << 8 & '\uffff' | in[123 + inPos] & 255;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 23 & Integer.MAX_VALUE | in.get(1 + inPos) << 15 & 8388607 | in.get(2 + inPos) << 7 & 32767 | in.get(3 + inPos) >> 1 & 127;
         out[1 + outPos] = in.get(3 + inPos) << 30 & Integer.MAX_VALUE | in.get(4 + inPos) << 22 & 1073741823 | in.get(5 + inPos) << 14 & 4194303 | in.get(6 + inPos) << 6 & 16383 | in.get(7 + inPos) >> 2 & 63;
         out[2 + outPos] = in.get(7 + inPos) << 29 & Integer.MAX_VALUE | in.get(8 + inPos) << 21 & 536870911 | in.get(9 + inPos) << 13 & 2097151 | in.get(10 + inPos) << 5 & 8191 | in.get(11 + inPos) >> 3 & 31;
         out[3 + outPos] = in.get(11 + inPos) << 28 & Integer.MAX_VALUE | in.get(12 + inPos) << 20 & 268435455 | in.get(13 + inPos) << 12 & 1048575 | in.get(14 + inPos) << 4 & 4095 | in.get(15 + inPos) >> 4 & 15;
         out[4 + outPos] = in.get(15 + inPos) << 27 & Integer.MAX_VALUE | in.get(16 + inPos) << 19 & 134217727 | in.get(17 + inPos) << 11 & 524287 | in.get(18 + inPos) << 3 & 2047 | in.get(19 + inPos) >> 5 & 7;
         out[5 + outPos] = in.get(19 + inPos) << 26 & Integer.MAX_VALUE | in.get(20 + inPos) << 18 & 67108863 | in.get(21 + inPos) << 10 & 262143 | in.get(22 + inPos) << 2 & 1023 | in.get(23 + inPos) >> 6 & 3;
         out[6 + outPos] = in.get(23 + inPos) << 25 & Integer.MAX_VALUE | in.get(24 + inPos) << 17 & 33554431 | in.get(25 + inPos) << 9 & 131071 | in.get(26 + inPos) << 1 & 511 | in.get(27 + inPos) >> 7 & 1;
         out[7 + outPos] = in.get(27 + inPos) << 24 & Integer.MAX_VALUE | in.get(28 + inPos) << 16 & 16777215 | in.get(29 + inPos) << 8 & '\uffff' | in.get(30 + inPos) & 255;
         out[8 + outPos] = in.get(31 + inPos) << 23 & Integer.MAX_VALUE | in.get(32 + inPos) << 15 & 8388607 | in.get(33 + inPos) << 7 & 32767 | in.get(34 + inPos) >> 1 & 127;
         out[9 + outPos] = in.get(34 + inPos) << 30 & Integer.MAX_VALUE | in.get(35 + inPos) << 22 & 1073741823 | in.get(36 + inPos) << 14 & 4194303 | in.get(37 + inPos) << 6 & 16383 | in.get(38 + inPos) >> 2 & 63;
         out[10 + outPos] = in.get(38 + inPos) << 29 & Integer.MAX_VALUE | in.get(39 + inPos) << 21 & 536870911 | in.get(40 + inPos) << 13 & 2097151 | in.get(41 + inPos) << 5 & 8191 | in.get(42 + inPos) >> 3 & 31;
         out[11 + outPos] = in.get(42 + inPos) << 28 & Integer.MAX_VALUE | in.get(43 + inPos) << 20 & 268435455 | in.get(44 + inPos) << 12 & 1048575 | in.get(45 + inPos) << 4 & 4095 | in.get(46 + inPos) >> 4 & 15;
         out[12 + outPos] = in.get(46 + inPos) << 27 & Integer.MAX_VALUE | in.get(47 + inPos) << 19 & 134217727 | in.get(48 + inPos) << 11 & 524287 | in.get(49 + inPos) << 3 & 2047 | in.get(50 + inPos) >> 5 & 7;
         out[13 + outPos] = in.get(50 + inPos) << 26 & Integer.MAX_VALUE | in.get(51 + inPos) << 18 & 67108863 | in.get(52 + inPos) << 10 & 262143 | in.get(53 + inPos) << 2 & 1023 | in.get(54 + inPos) >> 6 & 3;
         out[14 + outPos] = in.get(54 + inPos) << 25 & Integer.MAX_VALUE | in.get(55 + inPos) << 17 & 33554431 | in.get(56 + inPos) << 9 & 131071 | in.get(57 + inPos) << 1 & 511 | in.get(58 + inPos) >> 7 & 1;
         out[15 + outPos] = in.get(58 + inPos) << 24 & Integer.MAX_VALUE | in.get(59 + inPos) << 16 & 16777215 | in.get(60 + inPos) << 8 & '\uffff' | in.get(61 + inPos) & 255;
         out[16 + outPos] = in.get(62 + inPos) << 23 & Integer.MAX_VALUE | in.get(63 + inPos) << 15 & 8388607 | in.get(64 + inPos) << 7 & 32767 | in.get(65 + inPos) >> 1 & 127;
         out[17 + outPos] = in.get(65 + inPos) << 30 & Integer.MAX_VALUE | in.get(66 + inPos) << 22 & 1073741823 | in.get(67 + inPos) << 14 & 4194303 | in.get(68 + inPos) << 6 & 16383 | in.get(69 + inPos) >> 2 & 63;
         out[18 + outPos] = in.get(69 + inPos) << 29 & Integer.MAX_VALUE | in.get(70 + inPos) << 21 & 536870911 | in.get(71 + inPos) << 13 & 2097151 | in.get(72 + inPos) << 5 & 8191 | in.get(73 + inPos) >> 3 & 31;
         out[19 + outPos] = in.get(73 + inPos) << 28 & Integer.MAX_VALUE | in.get(74 + inPos) << 20 & 268435455 | in.get(75 + inPos) << 12 & 1048575 | in.get(76 + inPos) << 4 & 4095 | in.get(77 + inPos) >> 4 & 15;
         out[20 + outPos] = in.get(77 + inPos) << 27 & Integer.MAX_VALUE | in.get(78 + inPos) << 19 & 134217727 | in.get(79 + inPos) << 11 & 524287 | in.get(80 + inPos) << 3 & 2047 | in.get(81 + inPos) >> 5 & 7;
         out[21 + outPos] = in.get(81 + inPos) << 26 & Integer.MAX_VALUE | in.get(82 + inPos) << 18 & 67108863 | in.get(83 + inPos) << 10 & 262143 | in.get(84 + inPos) << 2 & 1023 | in.get(85 + inPos) >> 6 & 3;
         out[22 + outPos] = in.get(85 + inPos) << 25 & Integer.MAX_VALUE | in.get(86 + inPos) << 17 & 33554431 | in.get(87 + inPos) << 9 & 131071 | in.get(88 + inPos) << 1 & 511 | in.get(89 + inPos) >> 7 & 1;
         out[23 + outPos] = in.get(89 + inPos) << 24 & Integer.MAX_VALUE | in.get(90 + inPos) << 16 & 16777215 | in.get(91 + inPos) << 8 & '\uffff' | in.get(92 + inPos) & 255;
         out[24 + outPos] = in.get(93 + inPos) << 23 & Integer.MAX_VALUE | in.get(94 + inPos) << 15 & 8388607 | in.get(95 + inPos) << 7 & 32767 | in.get(96 + inPos) >> 1 & 127;
         out[25 + outPos] = in.get(96 + inPos) << 30 & Integer.MAX_VALUE | in.get(97 + inPos) << 22 & 1073741823 | in.get(98 + inPos) << 14 & 4194303 | in.get(99 + inPos) << 6 & 16383 | in.get(100 + inPos) >> 2 & 63;
         out[26 + outPos] = in.get(100 + inPos) << 29 & Integer.MAX_VALUE | in.get(101 + inPos) << 21 & 536870911 | in.get(102 + inPos) << 13 & 2097151 | in.get(103 + inPos) << 5 & 8191 | in.get(104 + inPos) >> 3 & 31;
         out[27 + outPos] = in.get(104 + inPos) << 28 & Integer.MAX_VALUE | in.get(105 + inPos) << 20 & 268435455 | in.get(106 + inPos) << 12 & 1048575 | in.get(107 + inPos) << 4 & 4095 | in.get(108 + inPos) >> 4 & 15;
         out[28 + outPos] = in.get(108 + inPos) << 27 & Integer.MAX_VALUE | in.get(109 + inPos) << 19 & 134217727 | in.get(110 + inPos) << 11 & 524287 | in.get(111 + inPos) << 3 & 2047 | in.get(112 + inPos) >> 5 & 7;
         out[29 + outPos] = in.get(112 + inPos) << 26 & Integer.MAX_VALUE | in.get(113 + inPos) << 18 & 67108863 | in.get(114 + inPos) << 10 & 262143 | in.get(115 + inPos) << 2 & 1023 | in.get(116 + inPos) >> 6 & 3;
         out[30 + outPos] = in.get(116 + inPos) << 25 & Integer.MAX_VALUE | in.get(117 + inPos) << 17 & 33554431 | in.get(118 + inPos) << 9 & 131071 | in.get(119 + inPos) << 1 & 511 | in.get(120 + inPos) >> 7 & 1;
         out[31 + outPos] = in.get(120 + inPos) << 24 & Integer.MAX_VALUE | in.get(121 + inPos) << 16 & 16777215 | in.get(122 + inPos) << 8 & '\uffff' | in.get(123 + inPos) & 255;
      }
   }

   private static final class Packer32 extends BytePacker {
      private Packer32() {
         super(32);
      }

      public final void pack8Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & -1) >>> 24 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & -1) >>> 16 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & -1) >>> 8 & 255);
         out[3 + outPos] = (byte)(in[0 + inPos] & -1 & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & -1) >>> 24 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & -1) >>> 16 & 255);
         out[6 + outPos] = (byte)((in[1 + inPos] & -1) >>> 8 & 255);
         out[7 + outPos] = (byte)(in[1 + inPos] & -1 & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & -1) >>> 24 & 255);
         out[9 + outPos] = (byte)((in[2 + inPos] & -1) >>> 16 & 255);
         out[10 + outPos] = (byte)((in[2 + inPos] & -1) >>> 8 & 255);
         out[11 + outPos] = (byte)(in[2 + inPos] & -1 & 255);
         out[12 + outPos] = (byte)((in[3 + inPos] & -1) >>> 24 & 255);
         out[13 + outPos] = (byte)((in[3 + inPos] & -1) >>> 16 & 255);
         out[14 + outPos] = (byte)((in[3 + inPos] & -1) >>> 8 & 255);
         out[15 + outPos] = (byte)(in[3 + inPos] & -1 & 255);
         out[16 + outPos] = (byte)((in[4 + inPos] & -1) >>> 24 & 255);
         out[17 + outPos] = (byte)((in[4 + inPos] & -1) >>> 16 & 255);
         out[18 + outPos] = (byte)((in[4 + inPos] & -1) >>> 8 & 255);
         out[19 + outPos] = (byte)(in[4 + inPos] & -1 & 255);
         out[20 + outPos] = (byte)((in[5 + inPos] & -1) >>> 24 & 255);
         out[21 + outPos] = (byte)((in[5 + inPos] & -1) >>> 16 & 255);
         out[22 + outPos] = (byte)((in[5 + inPos] & -1) >>> 8 & 255);
         out[23 + outPos] = (byte)(in[5 + inPos] & -1 & 255);
         out[24 + outPos] = (byte)((in[6 + inPos] & -1) >>> 24 & 255);
         out[25 + outPos] = (byte)((in[6 + inPos] & -1) >>> 16 & 255);
         out[26 + outPos] = (byte)((in[6 + inPos] & -1) >>> 8 & 255);
         out[27 + outPos] = (byte)(in[6 + inPos] & -1 & 255);
         out[28 + outPos] = (byte)((in[7 + inPos] & -1) >>> 24 & 255);
         out[29 + outPos] = (byte)((in[7 + inPos] & -1) >>> 16 & 255);
         out[30 + outPos] = (byte)((in[7 + inPos] & -1) >>> 8 & 255);
         out[31 + outPos] = (byte)(in[7 + inPos] & -1 & 255);
      }

      public final void pack32Values(int[] in, int inPos, byte[] out, int outPos) {
         out[0 + outPos] = (byte)((in[0 + inPos] & -1) >>> 24 & 255);
         out[1 + outPos] = (byte)((in[0 + inPos] & -1) >>> 16 & 255);
         out[2 + outPos] = (byte)((in[0 + inPos] & -1) >>> 8 & 255);
         out[3 + outPos] = (byte)(in[0 + inPos] & -1 & 255);
         out[4 + outPos] = (byte)((in[1 + inPos] & -1) >>> 24 & 255);
         out[5 + outPos] = (byte)((in[1 + inPos] & -1) >>> 16 & 255);
         out[6 + outPos] = (byte)((in[1 + inPos] & -1) >>> 8 & 255);
         out[7 + outPos] = (byte)(in[1 + inPos] & -1 & 255);
         out[8 + outPos] = (byte)((in[2 + inPos] & -1) >>> 24 & 255);
         out[9 + outPos] = (byte)((in[2 + inPos] & -1) >>> 16 & 255);
         out[10 + outPos] = (byte)((in[2 + inPos] & -1) >>> 8 & 255);
         out[11 + outPos] = (byte)(in[2 + inPos] & -1 & 255);
         out[12 + outPos] = (byte)((in[3 + inPos] & -1) >>> 24 & 255);
         out[13 + outPos] = (byte)((in[3 + inPos] & -1) >>> 16 & 255);
         out[14 + outPos] = (byte)((in[3 + inPos] & -1) >>> 8 & 255);
         out[15 + outPos] = (byte)(in[3 + inPos] & -1 & 255);
         out[16 + outPos] = (byte)((in[4 + inPos] & -1) >>> 24 & 255);
         out[17 + outPos] = (byte)((in[4 + inPos] & -1) >>> 16 & 255);
         out[18 + outPos] = (byte)((in[4 + inPos] & -1) >>> 8 & 255);
         out[19 + outPos] = (byte)(in[4 + inPos] & -1 & 255);
         out[20 + outPos] = (byte)((in[5 + inPos] & -1) >>> 24 & 255);
         out[21 + outPos] = (byte)((in[5 + inPos] & -1) >>> 16 & 255);
         out[22 + outPos] = (byte)((in[5 + inPos] & -1) >>> 8 & 255);
         out[23 + outPos] = (byte)(in[5 + inPos] & -1 & 255);
         out[24 + outPos] = (byte)((in[6 + inPos] & -1) >>> 24 & 255);
         out[25 + outPos] = (byte)((in[6 + inPos] & -1) >>> 16 & 255);
         out[26 + outPos] = (byte)((in[6 + inPos] & -1) >>> 8 & 255);
         out[27 + outPos] = (byte)(in[6 + inPos] & -1 & 255);
         out[28 + outPos] = (byte)((in[7 + inPos] & -1) >>> 24 & 255);
         out[29 + outPos] = (byte)((in[7 + inPos] & -1) >>> 16 & 255);
         out[30 + outPos] = (byte)((in[7 + inPos] & -1) >>> 8 & 255);
         out[31 + outPos] = (byte)(in[7 + inPos] & -1 & 255);
         out[32 + outPos] = (byte)((in[8 + inPos] & -1) >>> 24 & 255);
         out[33 + outPos] = (byte)((in[8 + inPos] & -1) >>> 16 & 255);
         out[34 + outPos] = (byte)((in[8 + inPos] & -1) >>> 8 & 255);
         out[35 + outPos] = (byte)(in[8 + inPos] & -1 & 255);
         out[36 + outPos] = (byte)((in[9 + inPos] & -1) >>> 24 & 255);
         out[37 + outPos] = (byte)((in[9 + inPos] & -1) >>> 16 & 255);
         out[38 + outPos] = (byte)((in[9 + inPos] & -1) >>> 8 & 255);
         out[39 + outPos] = (byte)(in[9 + inPos] & -1 & 255);
         out[40 + outPos] = (byte)((in[10 + inPos] & -1) >>> 24 & 255);
         out[41 + outPos] = (byte)((in[10 + inPos] & -1) >>> 16 & 255);
         out[42 + outPos] = (byte)((in[10 + inPos] & -1) >>> 8 & 255);
         out[43 + outPos] = (byte)(in[10 + inPos] & -1 & 255);
         out[44 + outPos] = (byte)((in[11 + inPos] & -1) >>> 24 & 255);
         out[45 + outPos] = (byte)((in[11 + inPos] & -1) >>> 16 & 255);
         out[46 + outPos] = (byte)((in[11 + inPos] & -1) >>> 8 & 255);
         out[47 + outPos] = (byte)(in[11 + inPos] & -1 & 255);
         out[48 + outPos] = (byte)((in[12 + inPos] & -1) >>> 24 & 255);
         out[49 + outPos] = (byte)((in[12 + inPos] & -1) >>> 16 & 255);
         out[50 + outPos] = (byte)((in[12 + inPos] & -1) >>> 8 & 255);
         out[51 + outPos] = (byte)(in[12 + inPos] & -1 & 255);
         out[52 + outPos] = (byte)((in[13 + inPos] & -1) >>> 24 & 255);
         out[53 + outPos] = (byte)((in[13 + inPos] & -1) >>> 16 & 255);
         out[54 + outPos] = (byte)((in[13 + inPos] & -1) >>> 8 & 255);
         out[55 + outPos] = (byte)(in[13 + inPos] & -1 & 255);
         out[56 + outPos] = (byte)((in[14 + inPos] & -1) >>> 24 & 255);
         out[57 + outPos] = (byte)((in[14 + inPos] & -1) >>> 16 & 255);
         out[58 + outPos] = (byte)((in[14 + inPos] & -1) >>> 8 & 255);
         out[59 + outPos] = (byte)(in[14 + inPos] & -1 & 255);
         out[60 + outPos] = (byte)((in[15 + inPos] & -1) >>> 24 & 255);
         out[61 + outPos] = (byte)((in[15 + inPos] & -1) >>> 16 & 255);
         out[62 + outPos] = (byte)((in[15 + inPos] & -1) >>> 8 & 255);
         out[63 + outPos] = (byte)(in[15 + inPos] & -1 & 255);
         out[64 + outPos] = (byte)((in[16 + inPos] & -1) >>> 24 & 255);
         out[65 + outPos] = (byte)((in[16 + inPos] & -1) >>> 16 & 255);
         out[66 + outPos] = (byte)((in[16 + inPos] & -1) >>> 8 & 255);
         out[67 + outPos] = (byte)(in[16 + inPos] & -1 & 255);
         out[68 + outPos] = (byte)((in[17 + inPos] & -1) >>> 24 & 255);
         out[69 + outPos] = (byte)((in[17 + inPos] & -1) >>> 16 & 255);
         out[70 + outPos] = (byte)((in[17 + inPos] & -1) >>> 8 & 255);
         out[71 + outPos] = (byte)(in[17 + inPos] & -1 & 255);
         out[72 + outPos] = (byte)((in[18 + inPos] & -1) >>> 24 & 255);
         out[73 + outPos] = (byte)((in[18 + inPos] & -1) >>> 16 & 255);
         out[74 + outPos] = (byte)((in[18 + inPos] & -1) >>> 8 & 255);
         out[75 + outPos] = (byte)(in[18 + inPos] & -1 & 255);
         out[76 + outPos] = (byte)((in[19 + inPos] & -1) >>> 24 & 255);
         out[77 + outPos] = (byte)((in[19 + inPos] & -1) >>> 16 & 255);
         out[78 + outPos] = (byte)((in[19 + inPos] & -1) >>> 8 & 255);
         out[79 + outPos] = (byte)(in[19 + inPos] & -1 & 255);
         out[80 + outPos] = (byte)((in[20 + inPos] & -1) >>> 24 & 255);
         out[81 + outPos] = (byte)((in[20 + inPos] & -1) >>> 16 & 255);
         out[82 + outPos] = (byte)((in[20 + inPos] & -1) >>> 8 & 255);
         out[83 + outPos] = (byte)(in[20 + inPos] & -1 & 255);
         out[84 + outPos] = (byte)((in[21 + inPos] & -1) >>> 24 & 255);
         out[85 + outPos] = (byte)((in[21 + inPos] & -1) >>> 16 & 255);
         out[86 + outPos] = (byte)((in[21 + inPos] & -1) >>> 8 & 255);
         out[87 + outPos] = (byte)(in[21 + inPos] & -1 & 255);
         out[88 + outPos] = (byte)((in[22 + inPos] & -1) >>> 24 & 255);
         out[89 + outPos] = (byte)((in[22 + inPos] & -1) >>> 16 & 255);
         out[90 + outPos] = (byte)((in[22 + inPos] & -1) >>> 8 & 255);
         out[91 + outPos] = (byte)(in[22 + inPos] & -1 & 255);
         out[92 + outPos] = (byte)((in[23 + inPos] & -1) >>> 24 & 255);
         out[93 + outPos] = (byte)((in[23 + inPos] & -1) >>> 16 & 255);
         out[94 + outPos] = (byte)((in[23 + inPos] & -1) >>> 8 & 255);
         out[95 + outPos] = (byte)(in[23 + inPos] & -1 & 255);
         out[96 + outPos] = (byte)((in[24 + inPos] & -1) >>> 24 & 255);
         out[97 + outPos] = (byte)((in[24 + inPos] & -1) >>> 16 & 255);
         out[98 + outPos] = (byte)((in[24 + inPos] & -1) >>> 8 & 255);
         out[99 + outPos] = (byte)(in[24 + inPos] & -1 & 255);
         out[100 + outPos] = (byte)((in[25 + inPos] & -1) >>> 24 & 255);
         out[101 + outPos] = (byte)((in[25 + inPos] & -1) >>> 16 & 255);
         out[102 + outPos] = (byte)((in[25 + inPos] & -1) >>> 8 & 255);
         out[103 + outPos] = (byte)(in[25 + inPos] & -1 & 255);
         out[104 + outPos] = (byte)((in[26 + inPos] & -1) >>> 24 & 255);
         out[105 + outPos] = (byte)((in[26 + inPos] & -1) >>> 16 & 255);
         out[106 + outPos] = (byte)((in[26 + inPos] & -1) >>> 8 & 255);
         out[107 + outPos] = (byte)(in[26 + inPos] & -1 & 255);
         out[108 + outPos] = (byte)((in[27 + inPos] & -1) >>> 24 & 255);
         out[109 + outPos] = (byte)((in[27 + inPos] & -1) >>> 16 & 255);
         out[110 + outPos] = (byte)((in[27 + inPos] & -1) >>> 8 & 255);
         out[111 + outPos] = (byte)(in[27 + inPos] & -1 & 255);
         out[112 + outPos] = (byte)((in[28 + inPos] & -1) >>> 24 & 255);
         out[113 + outPos] = (byte)((in[28 + inPos] & -1) >>> 16 & 255);
         out[114 + outPos] = (byte)((in[28 + inPos] & -1) >>> 8 & 255);
         out[115 + outPos] = (byte)(in[28 + inPos] & -1 & 255);
         out[116 + outPos] = (byte)((in[29 + inPos] & -1) >>> 24 & 255);
         out[117 + outPos] = (byte)((in[29 + inPos] & -1) >>> 16 & 255);
         out[118 + outPos] = (byte)((in[29 + inPos] & -1) >>> 8 & 255);
         out[119 + outPos] = (byte)(in[29 + inPos] & -1 & 255);
         out[120 + outPos] = (byte)((in[30 + inPos] & -1) >>> 24 & 255);
         out[121 + outPos] = (byte)((in[30 + inPos] & -1) >>> 16 & 255);
         out[122 + outPos] = (byte)((in[30 + inPos] & -1) >>> 8 & 255);
         out[123 + outPos] = (byte)(in[30 + inPos] & -1 & 255);
         out[124 + outPos] = (byte)((in[31 + inPos] & -1) >>> 24 & 255);
         out[125 + outPos] = (byte)((in[31 + inPos] & -1) >>> 16 & 255);
         out[126 + outPos] = (byte)((in[31 + inPos] & -1) >>> 8 & 255);
         out[127 + outPos] = (byte)(in[31 + inPos] & -1 & 255);
      }

      public final void unpack8Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 24 & -1 | in[1 + inPos] << 16 & 16777215 | in[2 + inPos] << 8 & '\uffff' | in[3 + inPos] & 255;
         out[1 + outPos] = in[4 + inPos] << 24 & -1 | in[5 + inPos] << 16 & 16777215 | in[6 + inPos] << 8 & '\uffff' | in[7 + inPos] & 255;
         out[2 + outPos] = in[8 + inPos] << 24 & -1 | in[9 + inPos] << 16 & 16777215 | in[10 + inPos] << 8 & '\uffff' | in[11 + inPos] & 255;
         out[3 + outPos] = in[12 + inPos] << 24 & -1 | in[13 + inPos] << 16 & 16777215 | in[14 + inPos] << 8 & '\uffff' | in[15 + inPos] & 255;
         out[4 + outPos] = in[16 + inPos] << 24 & -1 | in[17 + inPos] << 16 & 16777215 | in[18 + inPos] << 8 & '\uffff' | in[19 + inPos] & 255;
         out[5 + outPos] = in[20 + inPos] << 24 & -1 | in[21 + inPos] << 16 & 16777215 | in[22 + inPos] << 8 & '\uffff' | in[23 + inPos] & 255;
         out[6 + outPos] = in[24 + inPos] << 24 & -1 | in[25 + inPos] << 16 & 16777215 | in[26 + inPos] << 8 & '\uffff' | in[27 + inPos] & 255;
         out[7 + outPos] = in[28 + inPos] << 24 & -1 | in[29 + inPos] << 16 & 16777215 | in[30 + inPos] << 8 & '\uffff' | in[31 + inPos] & 255;
      }

      public final void unpack8Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 24 & -1 | in.get(1 + inPos) << 16 & 16777215 | in.get(2 + inPos) << 8 & '\uffff' | in.get(3 + inPos) & 255;
         out[1 + outPos] = in.get(4 + inPos) << 24 & -1 | in.get(5 + inPos) << 16 & 16777215 | in.get(6 + inPos) << 8 & '\uffff' | in.get(7 + inPos) & 255;
         out[2 + outPos] = in.get(8 + inPos) << 24 & -1 | in.get(9 + inPos) << 16 & 16777215 | in.get(10 + inPos) << 8 & '\uffff' | in.get(11 + inPos) & 255;
         out[3 + outPos] = in.get(12 + inPos) << 24 & -1 | in.get(13 + inPos) << 16 & 16777215 | in.get(14 + inPos) << 8 & '\uffff' | in.get(15 + inPos) & 255;
         out[4 + outPos] = in.get(16 + inPos) << 24 & -1 | in.get(17 + inPos) << 16 & 16777215 | in.get(18 + inPos) << 8 & '\uffff' | in.get(19 + inPos) & 255;
         out[5 + outPos] = in.get(20 + inPos) << 24 & -1 | in.get(21 + inPos) << 16 & 16777215 | in.get(22 + inPos) << 8 & '\uffff' | in.get(23 + inPos) & 255;
         out[6 + outPos] = in.get(24 + inPos) << 24 & -1 | in.get(25 + inPos) << 16 & 16777215 | in.get(26 + inPos) << 8 & '\uffff' | in.get(27 + inPos) & 255;
         out[7 + outPos] = in.get(28 + inPos) << 24 & -1 | in.get(29 + inPos) << 16 & 16777215 | in.get(30 + inPos) << 8 & '\uffff' | in.get(31 + inPos) & 255;
      }

      public final void unpack32Values(byte[] in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in[0 + inPos] << 24 & -1 | in[1 + inPos] << 16 & 16777215 | in[2 + inPos] << 8 & '\uffff' | in[3 + inPos] & 255;
         out[1 + outPos] = in[4 + inPos] << 24 & -1 | in[5 + inPos] << 16 & 16777215 | in[6 + inPos] << 8 & '\uffff' | in[7 + inPos] & 255;
         out[2 + outPos] = in[8 + inPos] << 24 & -1 | in[9 + inPos] << 16 & 16777215 | in[10 + inPos] << 8 & '\uffff' | in[11 + inPos] & 255;
         out[3 + outPos] = in[12 + inPos] << 24 & -1 | in[13 + inPos] << 16 & 16777215 | in[14 + inPos] << 8 & '\uffff' | in[15 + inPos] & 255;
         out[4 + outPos] = in[16 + inPos] << 24 & -1 | in[17 + inPos] << 16 & 16777215 | in[18 + inPos] << 8 & '\uffff' | in[19 + inPos] & 255;
         out[5 + outPos] = in[20 + inPos] << 24 & -1 | in[21 + inPos] << 16 & 16777215 | in[22 + inPos] << 8 & '\uffff' | in[23 + inPos] & 255;
         out[6 + outPos] = in[24 + inPos] << 24 & -1 | in[25 + inPos] << 16 & 16777215 | in[26 + inPos] << 8 & '\uffff' | in[27 + inPos] & 255;
         out[7 + outPos] = in[28 + inPos] << 24 & -1 | in[29 + inPos] << 16 & 16777215 | in[30 + inPos] << 8 & '\uffff' | in[31 + inPos] & 255;
         out[8 + outPos] = in[32 + inPos] << 24 & -1 | in[33 + inPos] << 16 & 16777215 | in[34 + inPos] << 8 & '\uffff' | in[35 + inPos] & 255;
         out[9 + outPos] = in[36 + inPos] << 24 & -1 | in[37 + inPos] << 16 & 16777215 | in[38 + inPos] << 8 & '\uffff' | in[39 + inPos] & 255;
         out[10 + outPos] = in[40 + inPos] << 24 & -1 | in[41 + inPos] << 16 & 16777215 | in[42 + inPos] << 8 & '\uffff' | in[43 + inPos] & 255;
         out[11 + outPos] = in[44 + inPos] << 24 & -1 | in[45 + inPos] << 16 & 16777215 | in[46 + inPos] << 8 & '\uffff' | in[47 + inPos] & 255;
         out[12 + outPos] = in[48 + inPos] << 24 & -1 | in[49 + inPos] << 16 & 16777215 | in[50 + inPos] << 8 & '\uffff' | in[51 + inPos] & 255;
         out[13 + outPos] = in[52 + inPos] << 24 & -1 | in[53 + inPos] << 16 & 16777215 | in[54 + inPos] << 8 & '\uffff' | in[55 + inPos] & 255;
         out[14 + outPos] = in[56 + inPos] << 24 & -1 | in[57 + inPos] << 16 & 16777215 | in[58 + inPos] << 8 & '\uffff' | in[59 + inPos] & 255;
         out[15 + outPos] = in[60 + inPos] << 24 & -1 | in[61 + inPos] << 16 & 16777215 | in[62 + inPos] << 8 & '\uffff' | in[63 + inPos] & 255;
         out[16 + outPos] = in[64 + inPos] << 24 & -1 | in[65 + inPos] << 16 & 16777215 | in[66 + inPos] << 8 & '\uffff' | in[67 + inPos] & 255;
         out[17 + outPos] = in[68 + inPos] << 24 & -1 | in[69 + inPos] << 16 & 16777215 | in[70 + inPos] << 8 & '\uffff' | in[71 + inPos] & 255;
         out[18 + outPos] = in[72 + inPos] << 24 & -1 | in[73 + inPos] << 16 & 16777215 | in[74 + inPos] << 8 & '\uffff' | in[75 + inPos] & 255;
         out[19 + outPos] = in[76 + inPos] << 24 & -1 | in[77 + inPos] << 16 & 16777215 | in[78 + inPos] << 8 & '\uffff' | in[79 + inPos] & 255;
         out[20 + outPos] = in[80 + inPos] << 24 & -1 | in[81 + inPos] << 16 & 16777215 | in[82 + inPos] << 8 & '\uffff' | in[83 + inPos] & 255;
         out[21 + outPos] = in[84 + inPos] << 24 & -1 | in[85 + inPos] << 16 & 16777215 | in[86 + inPos] << 8 & '\uffff' | in[87 + inPos] & 255;
         out[22 + outPos] = in[88 + inPos] << 24 & -1 | in[89 + inPos] << 16 & 16777215 | in[90 + inPos] << 8 & '\uffff' | in[91 + inPos] & 255;
         out[23 + outPos] = in[92 + inPos] << 24 & -1 | in[93 + inPos] << 16 & 16777215 | in[94 + inPos] << 8 & '\uffff' | in[95 + inPos] & 255;
         out[24 + outPos] = in[96 + inPos] << 24 & -1 | in[97 + inPos] << 16 & 16777215 | in[98 + inPos] << 8 & '\uffff' | in[99 + inPos] & 255;
         out[25 + outPos] = in[100 + inPos] << 24 & -1 | in[101 + inPos] << 16 & 16777215 | in[102 + inPos] << 8 & '\uffff' | in[103 + inPos] & 255;
         out[26 + outPos] = in[104 + inPos] << 24 & -1 | in[105 + inPos] << 16 & 16777215 | in[106 + inPos] << 8 & '\uffff' | in[107 + inPos] & 255;
         out[27 + outPos] = in[108 + inPos] << 24 & -1 | in[109 + inPos] << 16 & 16777215 | in[110 + inPos] << 8 & '\uffff' | in[111 + inPos] & 255;
         out[28 + outPos] = in[112 + inPos] << 24 & -1 | in[113 + inPos] << 16 & 16777215 | in[114 + inPos] << 8 & '\uffff' | in[115 + inPos] & 255;
         out[29 + outPos] = in[116 + inPos] << 24 & -1 | in[117 + inPos] << 16 & 16777215 | in[118 + inPos] << 8 & '\uffff' | in[119 + inPos] & 255;
         out[30 + outPos] = in[120 + inPos] << 24 & -1 | in[121 + inPos] << 16 & 16777215 | in[122 + inPos] << 8 & '\uffff' | in[123 + inPos] & 255;
         out[31 + outPos] = in[124 + inPos] << 24 & -1 | in[125 + inPos] << 16 & 16777215 | in[126 + inPos] << 8 & '\uffff' | in[127 + inPos] & 255;
      }

      public final void unpack32Values(ByteBuffer in, int inPos, int[] out, int outPos) {
         out[0 + outPos] = in.get(0 + inPos) << 24 & -1 | in.get(1 + inPos) << 16 & 16777215 | in.get(2 + inPos) << 8 & '\uffff' | in.get(3 + inPos) & 255;
         out[1 + outPos] = in.get(4 + inPos) << 24 & -1 | in.get(5 + inPos) << 16 & 16777215 | in.get(6 + inPos) << 8 & '\uffff' | in.get(7 + inPos) & 255;
         out[2 + outPos] = in.get(8 + inPos) << 24 & -1 | in.get(9 + inPos) << 16 & 16777215 | in.get(10 + inPos) << 8 & '\uffff' | in.get(11 + inPos) & 255;
         out[3 + outPos] = in.get(12 + inPos) << 24 & -1 | in.get(13 + inPos) << 16 & 16777215 | in.get(14 + inPos) << 8 & '\uffff' | in.get(15 + inPos) & 255;
         out[4 + outPos] = in.get(16 + inPos) << 24 & -1 | in.get(17 + inPos) << 16 & 16777215 | in.get(18 + inPos) << 8 & '\uffff' | in.get(19 + inPos) & 255;
         out[5 + outPos] = in.get(20 + inPos) << 24 & -1 | in.get(21 + inPos) << 16 & 16777215 | in.get(22 + inPos) << 8 & '\uffff' | in.get(23 + inPos) & 255;
         out[6 + outPos] = in.get(24 + inPos) << 24 & -1 | in.get(25 + inPos) << 16 & 16777215 | in.get(26 + inPos) << 8 & '\uffff' | in.get(27 + inPos) & 255;
         out[7 + outPos] = in.get(28 + inPos) << 24 & -1 | in.get(29 + inPos) << 16 & 16777215 | in.get(30 + inPos) << 8 & '\uffff' | in.get(31 + inPos) & 255;
         out[8 + outPos] = in.get(32 + inPos) << 24 & -1 | in.get(33 + inPos) << 16 & 16777215 | in.get(34 + inPos) << 8 & '\uffff' | in.get(35 + inPos) & 255;
         out[9 + outPos] = in.get(36 + inPos) << 24 & -1 | in.get(37 + inPos) << 16 & 16777215 | in.get(38 + inPos) << 8 & '\uffff' | in.get(39 + inPos) & 255;
         out[10 + outPos] = in.get(40 + inPos) << 24 & -1 | in.get(41 + inPos) << 16 & 16777215 | in.get(42 + inPos) << 8 & '\uffff' | in.get(43 + inPos) & 255;
         out[11 + outPos] = in.get(44 + inPos) << 24 & -1 | in.get(45 + inPos) << 16 & 16777215 | in.get(46 + inPos) << 8 & '\uffff' | in.get(47 + inPos) & 255;
         out[12 + outPos] = in.get(48 + inPos) << 24 & -1 | in.get(49 + inPos) << 16 & 16777215 | in.get(50 + inPos) << 8 & '\uffff' | in.get(51 + inPos) & 255;
         out[13 + outPos] = in.get(52 + inPos) << 24 & -1 | in.get(53 + inPos) << 16 & 16777215 | in.get(54 + inPos) << 8 & '\uffff' | in.get(55 + inPos) & 255;
         out[14 + outPos] = in.get(56 + inPos) << 24 & -1 | in.get(57 + inPos) << 16 & 16777215 | in.get(58 + inPos) << 8 & '\uffff' | in.get(59 + inPos) & 255;
         out[15 + outPos] = in.get(60 + inPos) << 24 & -1 | in.get(61 + inPos) << 16 & 16777215 | in.get(62 + inPos) << 8 & '\uffff' | in.get(63 + inPos) & 255;
         out[16 + outPos] = in.get(64 + inPos) << 24 & -1 | in.get(65 + inPos) << 16 & 16777215 | in.get(66 + inPos) << 8 & '\uffff' | in.get(67 + inPos) & 255;
         out[17 + outPos] = in.get(68 + inPos) << 24 & -1 | in.get(69 + inPos) << 16 & 16777215 | in.get(70 + inPos) << 8 & '\uffff' | in.get(71 + inPos) & 255;
         out[18 + outPos] = in.get(72 + inPos) << 24 & -1 | in.get(73 + inPos) << 16 & 16777215 | in.get(74 + inPos) << 8 & '\uffff' | in.get(75 + inPos) & 255;
         out[19 + outPos] = in.get(76 + inPos) << 24 & -1 | in.get(77 + inPos) << 16 & 16777215 | in.get(78 + inPos) << 8 & '\uffff' | in.get(79 + inPos) & 255;
         out[20 + outPos] = in.get(80 + inPos) << 24 & -1 | in.get(81 + inPos) << 16 & 16777215 | in.get(82 + inPos) << 8 & '\uffff' | in.get(83 + inPos) & 255;
         out[21 + outPos] = in.get(84 + inPos) << 24 & -1 | in.get(85 + inPos) << 16 & 16777215 | in.get(86 + inPos) << 8 & '\uffff' | in.get(87 + inPos) & 255;
         out[22 + outPos] = in.get(88 + inPos) << 24 & -1 | in.get(89 + inPos) << 16 & 16777215 | in.get(90 + inPos) << 8 & '\uffff' | in.get(91 + inPos) & 255;
         out[23 + outPos] = in.get(92 + inPos) << 24 & -1 | in.get(93 + inPos) << 16 & 16777215 | in.get(94 + inPos) << 8 & '\uffff' | in.get(95 + inPos) & 255;
         out[24 + outPos] = in.get(96 + inPos) << 24 & -1 | in.get(97 + inPos) << 16 & 16777215 | in.get(98 + inPos) << 8 & '\uffff' | in.get(99 + inPos) & 255;
         out[25 + outPos] = in.get(100 + inPos) << 24 & -1 | in.get(101 + inPos) << 16 & 16777215 | in.get(102 + inPos) << 8 & '\uffff' | in.get(103 + inPos) & 255;
         out[26 + outPos] = in.get(104 + inPos) << 24 & -1 | in.get(105 + inPos) << 16 & 16777215 | in.get(106 + inPos) << 8 & '\uffff' | in.get(107 + inPos) & 255;
         out[27 + outPos] = in.get(108 + inPos) << 24 & -1 | in.get(109 + inPos) << 16 & 16777215 | in.get(110 + inPos) << 8 & '\uffff' | in.get(111 + inPos) & 255;
         out[28 + outPos] = in.get(112 + inPos) << 24 & -1 | in.get(113 + inPos) << 16 & 16777215 | in.get(114 + inPos) << 8 & '\uffff' | in.get(115 + inPos) & 255;
         out[29 + outPos] = in.get(116 + inPos) << 24 & -1 | in.get(117 + inPos) << 16 & 16777215 | in.get(118 + inPos) << 8 & '\uffff' | in.get(119 + inPos) & 255;
         out[30 + outPos] = in.get(120 + inPos) << 24 & -1 | in.get(121 + inPos) << 16 & 16777215 | in.get(122 + inPos) << 8 & '\uffff' | in.get(123 + inPos) & 255;
         out[31 + outPos] = in.get(124 + inPos) << 24 & -1 | in.get(125 + inPos) << 16 & 16777215 | in.get(126 + inPos) << 8 & '\uffff' | in.get(127 + inPos) & 255;
      }
   }
}
