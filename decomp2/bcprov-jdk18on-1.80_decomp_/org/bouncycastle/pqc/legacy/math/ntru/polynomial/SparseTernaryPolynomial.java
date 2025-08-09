package org.bouncycastle.pqc.legacy.math.ntru.polynomial;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.pqc.legacy.math.ntru.util.ArrayEncoder;
import org.bouncycastle.pqc.legacy.math.ntru.util.Util;
import org.bouncycastle.util.Arrays;

public class SparseTernaryPolynomial implements TernaryPolynomial {
   private static final int BITS_PER_INDEX = 11;
   private int N;
   private int[] ones;
   private int[] negOnes;

   SparseTernaryPolynomial(int var1, int[] var2, int[] var3) {
      this.N = var1;
      this.ones = var2;
      this.negOnes = var3;
   }

   public SparseTernaryPolynomial(IntegerPolynomial var1) {
      this(var1.coeffs);
   }

   public SparseTernaryPolynomial(int[] var1) {
      this.N = var1.length;
      this.ones = new int[this.N];
      this.negOnes = new int[this.N];
      int var2 = 0;
      int var3 = 0;

      for(int var4 = 0; var4 < this.N; ++var4) {
         int var5 = var1[var4];
         switch (var5) {
            case -1:
               this.negOnes[var3++] = var4;
            case 0:
               break;
            case 1:
               this.ones[var2++] = var4;
               break;
            default:
               throw new IllegalArgumentException("Illegal value: " + var5 + ", must be one of {-1, 0, 1}");
         }
      }

      this.ones = Arrays.copyOf(this.ones, var2);
      this.negOnes = Arrays.copyOf(this.negOnes, var3);
   }

   public static SparseTernaryPolynomial fromBinary(InputStream var0, int var1, int var2, int var3) throws IOException {
      short var4 = 2048;
      int var5 = 32 - Integer.numberOfLeadingZeros(var4 - 1);
      int var6 = (var2 * var5 + 7) / 8;
      byte[] var7 = Util.readFullLength(var0, var6);
      int[] var8 = ArrayEncoder.decodeModQ((byte[])var7, var2, var4);
      int var9 = (var3 * var5 + 7) / 8;
      byte[] var10 = Util.readFullLength(var0, var9);
      int[] var11 = ArrayEncoder.decodeModQ((byte[])var10, var3, var4);
      return new SparseTernaryPolynomial(var1, var8, var11);
   }

   public static SparseTernaryPolynomial generateRandom(int var0, int var1, int var2, SecureRandom var3) {
      int[] var4 = Util.generateRandomTernary(var0, var1, var2, var3);
      return new SparseTernaryPolynomial(var4);
   }

   public IntegerPolynomial mult(IntegerPolynomial var1) {
      int[] var2 = var1.coeffs;
      if (var2.length != this.N) {
         throw new IllegalArgumentException("Number of coefficients must be the same");
      } else {
         int[] var3 = new int[this.N];

         for(int var4 = 0; var4 != this.ones.length; ++var4) {
            int var5 = this.ones[var4];
            int var6 = this.N - 1 - var5;

            for(int var7 = this.N - 1; var7 >= 0; --var7) {
               var3[var7] += var2[var6];
               --var6;
               if (var6 < 0) {
                  var6 = this.N - 1;
               }
            }
         }

         for(int var8 = 0; var8 != this.negOnes.length; ++var8) {
            int var9 = this.negOnes[var8];
            int var10 = this.N - 1 - var9;

            for(int var11 = this.N - 1; var11 >= 0; --var11) {
               var3[var11] -= var2[var10];
               --var10;
               if (var10 < 0) {
                  var10 = this.N - 1;
               }
            }
         }

         return new IntegerPolynomial(var3);
      }
   }

   public IntegerPolynomial mult(IntegerPolynomial var1, int var2) {
      IntegerPolynomial var3 = this.mult(var1);
      var3.mod(var2);
      return var3;
   }

   public BigIntPolynomial mult(BigIntPolynomial var1) {
      BigInteger[] var2 = var1.coeffs;
      if (var2.length != this.N) {
         throw new IllegalArgumentException("Number of coefficients must be the same");
      } else {
         BigInteger[] var3 = new BigInteger[this.N];

         for(int var4 = 0; var4 < this.N; ++var4) {
            var3[var4] = BigInteger.ZERO;
         }

         for(int var8 = 0; var8 != this.ones.length; ++var8) {
            int var5 = this.ones[var8];
            int var6 = this.N - 1 - var5;

            for(int var7 = this.N - 1; var7 >= 0; --var7) {
               var3[var7] = var3[var7].add(var2[var6]);
               --var6;
               if (var6 < 0) {
                  var6 = this.N - 1;
               }
            }
         }

         for(int var9 = 0; var9 != this.negOnes.length; ++var9) {
            int var10 = this.negOnes[var9];
            int var11 = this.N - 1 - var10;

            for(int var12 = this.N - 1; var12 >= 0; --var12) {
               var3[var12] = var3[var12].subtract(var2[var11]);
               --var11;
               if (var11 < 0) {
                  var11 = this.N - 1;
               }
            }
         }

         return new BigIntPolynomial(var3);
      }
   }

   public int[] getOnes() {
      return this.ones;
   }

   public int[] getNegOnes() {
      return this.negOnes;
   }

   public byte[] toBinary() {
      short var1 = 2048;
      byte[] var2 = ArrayEncoder.encodeModQ(this.ones, var1);
      byte[] var3 = ArrayEncoder.encodeModQ(this.negOnes, var1);
      byte[] var4 = Arrays.copyOf(var2, var2.length + var3.length);
      System.arraycopy(var3, 0, var4, var2.length, var3.length);
      return var4;
   }

   public IntegerPolynomial toIntegerPolynomial() {
      int[] var1 = new int[this.N];

      for(int var2 = 0; var2 != this.ones.length; ++var2) {
         int var3 = this.ones[var2];
         var1[var3] = 1;
      }

      for(int var4 = 0; var4 != this.negOnes.length; ++var4) {
         int var5 = this.negOnes[var4];
         var1[var5] = -1;
      }

      return new IntegerPolynomial(var1);
   }

   public int size() {
      return this.N;
   }

   public void clear() {
      for(int var1 = 0; var1 < this.ones.length; ++var1) {
         this.ones[var1] = 0;
      }

      for(int var2 = 0; var2 < this.negOnes.length; ++var2) {
         this.negOnes[var2] = 0;
      }

   }

   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.N;
      var1 = 31 * var1 + Arrays.hashCode(this.negOnes);
      var1 = 31 * var1 + Arrays.hashCode(this.ones);
      return var1;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         SparseTernaryPolynomial var2 = (SparseTernaryPolynomial)var1;
         if (this.N != var2.N) {
            return false;
         } else if (!Arrays.areEqual(this.negOnes, var2.negOnes)) {
            return false;
         } else {
            return Arrays.areEqual(this.ones, var2.ones);
         }
      }
   }
}
