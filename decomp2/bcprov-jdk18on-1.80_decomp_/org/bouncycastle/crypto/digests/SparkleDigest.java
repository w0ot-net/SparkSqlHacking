package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.engines.SparkleEngine;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;

public class SparkleDigest implements ExtendedDigest {
   private static final int RATE_BYTES = 16;
   private static final int RATE_WORDS = 4;
   private String algorithmName;
   private final int[] state;
   private final byte[] m_buf = new byte[16];
   private final int DIGEST_BYTES;
   private final int SPARKLE_STEPS_SLIM;
   private final int SPARKLE_STEPS_BIG;
   private final int STATE_WORDS;
   private int m_bufPos = 0;

   public SparkleDigest(SparkleParameters var1) {
      switch (var1.ordinal()) {
         case 0:
            this.algorithmName = "ESCH-256";
            this.DIGEST_BYTES = 32;
            this.SPARKLE_STEPS_SLIM = 7;
            this.SPARKLE_STEPS_BIG = 11;
            this.STATE_WORDS = 12;
            break;
         case 1:
            this.algorithmName = "ESCH-384";
            this.DIGEST_BYTES = 48;
            this.SPARKLE_STEPS_SLIM = 8;
            this.SPARKLE_STEPS_BIG = 12;
            this.STATE_WORDS = 16;
            break;
         default:
            throw new IllegalArgumentException("Invalid definition of SCHWAEMM instance");
      }

      this.state = new int[this.STATE_WORDS];
   }

   public String getAlgorithmName() {
      return this.algorithmName;
   }

   public int getDigestSize() {
      return this.DIGEST_BYTES;
   }

   public int getByteLength() {
      return 16;
   }

   public void update(byte var1) {
      if (this.m_bufPos == 16) {
         this.processBlock(this.m_buf, 0, this.SPARKLE_STEPS_SLIM);
         this.m_bufPos = 0;
      }

      this.m_buf[this.m_bufPos++] = var1;
   }

   public void update(byte[] var1, int var2, int var3) {
      if (var2 > var1.length - var3) {
         throw new DataLengthException(this.algorithmName + " input buffer too short");
      } else if (var3 >= 1) {
         int var4 = 16 - this.m_bufPos;
         if (var3 <= var4) {
            System.arraycopy(var1, var2, this.m_buf, this.m_bufPos, var3);
            this.m_bufPos += var3;
         } else {
            int var5 = 0;
            if (this.m_bufPos > 0) {
               System.arraycopy(var1, var2, this.m_buf, this.m_bufPos, var4);
               this.processBlock(this.m_buf, 0, this.SPARKLE_STEPS_SLIM);
               var5 += var4;
            }

            int var6;
            while((var6 = var3 - var5) > 16) {
               this.processBlock(var1, var2 + var5, this.SPARKLE_STEPS_SLIM);
               var5 += 16;
            }

            System.arraycopy(var1, var2 + var5, this.m_buf, 0, var6);
            this.m_bufPos = var6;
         }
      }
   }

   public int doFinal(byte[] var1, int var2) {
      if (var2 > var1.length - this.DIGEST_BYTES) {
         throw new OutputLengthException(this.algorithmName + " input buffer too short");
      } else {
         if (this.m_bufPos < 16) {
            int[] var10000 = this.state;
            int var10001 = (this.STATE_WORDS >> 1) - 1;
            var10000[var10001] ^= 16777216;

            for(this.m_buf[this.m_bufPos] = -128; ++this.m_bufPos < 16; this.m_buf[this.m_bufPos] = 0) {
            }
         } else {
            int[] var3 = this.state;
            int var4 = (this.STATE_WORDS >> 1) - 1;
            var3[var4] ^= 33554432;
         }

         this.processBlock(this.m_buf, 0, this.SPARKLE_STEPS_BIG);
         Pack.intToLittleEndian(this.state, 0, 4, var1, var2);
         if (this.STATE_WORDS == 16) {
            SparkleEngine.sparkle_opt16(SparkleDigest.Friend.INSTANCE, this.state, this.SPARKLE_STEPS_SLIM);
            Pack.intToLittleEndian(this.state, 0, 4, var1, var2 + 16);
            SparkleEngine.sparkle_opt16(SparkleDigest.Friend.INSTANCE, this.state, this.SPARKLE_STEPS_SLIM);
            Pack.intToLittleEndian(this.state, 0, 4, var1, var2 + 32);
         } else {
            SparkleEngine.sparkle_opt12(SparkleDigest.Friend.INSTANCE, this.state, this.SPARKLE_STEPS_SLIM);
            Pack.intToLittleEndian(this.state, 0, 4, var1, var2 + 16);
         }

         this.reset();
         return this.DIGEST_BYTES;
      }
   }

   public void reset() {
      Arrays.fill((int[])this.state, (int)0);
      Arrays.fill((byte[])this.m_buf, (byte)0);
      this.m_bufPos = 0;
   }

   private void processBlock(byte[] var1, int var2, int var3) {
      int var4 = Pack.littleEndianToInt(var1, var2);
      int var5 = Pack.littleEndianToInt(var1, var2 + 4);
      int var6 = Pack.littleEndianToInt(var1, var2 + 8);
      int var7 = Pack.littleEndianToInt(var1, var2 + 12);
      int var8 = ELL(var4 ^ var6);
      int var9 = ELL(var5 ^ var7);
      int[] var10000 = this.state;
      var10000[0] ^= var4 ^ var9;
      var10000 = this.state;
      var10000[1] ^= var5 ^ var8;
      var10000 = this.state;
      var10000[2] ^= var6 ^ var9;
      var10000 = this.state;
      var10000[3] ^= var7 ^ var8;
      var10000 = this.state;
      var10000[4] ^= var9;
      var10000 = this.state;
      var10000[5] ^= var8;
      if (this.STATE_WORDS == 16) {
         var10000 = this.state;
         var10000[6] ^= var9;
         var10000 = this.state;
         var10000[7] ^= var8;
         SparkleEngine.sparkle_opt16(SparkleDigest.Friend.INSTANCE, this.state, var3);
      } else {
         SparkleEngine.sparkle_opt12(SparkleDigest.Friend.INSTANCE, this.state, var3);
      }

   }

   private static int ELL(int var0) {
      return Integers.rotateRight(var0, 16) ^ var0 & '\uffff';
   }

   public static class Friend {
      private static final Friend INSTANCE = new Friend();

      private Friend() {
      }
   }

   public static enum SparkleParameters {
      ESCH256,
      ESCH384;

      // $FF: synthetic method
      private static SparkleParameters[] $values() {
         return new SparkleParameters[]{ESCH256, ESCH384};
      }
   }
}
