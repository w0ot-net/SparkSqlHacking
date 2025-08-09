package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.AbstractECLookupTable;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECLookupTable;
import org.bouncycastle.math.ec.ECMultiplier;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.WTauNafMultiplier;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.encoders.Hex;

public class SecT239K1Curve extends ECCurve.AbstractF2m {
   private static final int SECT239K1_DEFAULT_COORDS = 6;
   private static final ECFieldElement[] SECT239K1_AFFINE_ZS;
   protected SecT239K1Point infinity = new SecT239K1Point(this, (ECFieldElement)null, (ECFieldElement)null);

   public SecT239K1Curve() {
      super(239, 158, 0, 0);
      this.a = this.fromBigInteger(BigInteger.valueOf(0L));
      this.b = this.fromBigInteger(BigInteger.valueOf(1L));
      this.order = new BigInteger(1, Hex.decodeStrict("2000000000000000000000000000005A79FEC67CB6E91F1C1DA800E478A5"));
      this.cofactor = BigInteger.valueOf(4L);
      this.coord = 6;
   }

   protected ECCurve cloneCurve() {
      return new SecT239K1Curve();
   }

   public boolean supportsCoordinateSystem(int var1) {
      switch (var1) {
         case 6:
            return true;
         default:
            return false;
      }
   }

   protected ECMultiplier createDefaultMultiplier() {
      return new WTauNafMultiplier();
   }

   public int getFieldSize() {
      return 239;
   }

   public ECFieldElement fromBigInteger(BigInteger var1) {
      return new SecT239FieldElement(var1);
   }

   protected ECPoint createRawPoint(ECFieldElement var1, ECFieldElement var2) {
      return new SecT239K1Point(this, var1, var2);
   }

   protected ECPoint createRawPoint(ECFieldElement var1, ECFieldElement var2, ECFieldElement[] var3) {
      return new SecT239K1Point(this, var1, var2, var3);
   }

   public ECPoint getInfinity() {
      return this.infinity;
   }

   public boolean isKoblitz() {
      return true;
   }

   public int getM() {
      return 239;
   }

   public boolean isTrinomial() {
      return true;
   }

   public int getK1() {
      return 158;
   }

   public int getK2() {
      return 0;
   }

   public int getK3() {
      return 0;
   }

   public ECLookupTable createCacheSafeLookupTable(ECPoint[] var1, int var2, final int var3) {
      final long[] var4 = new long[var3 * 4 * 2];
      int var5 = 0;

      for(int var6 = 0; var6 < var3; ++var6) {
         ECPoint var7 = var1[var2 + var6];
         Nat256.copy64(((SecT239FieldElement)var7.getRawXCoord()).x, 0, var4, var5);
         var5 += 4;
         Nat256.copy64(((SecT239FieldElement)var7.getRawYCoord()).x, 0, var4, var5);
         var5 += 4;
      }

      return new AbstractECLookupTable() {
         public int getSize() {
            return var3;
         }

         public ECPoint lookup(int var1) {
            long[] var2 = Nat256.create64();
            long[] var3x = Nat256.create64();
            int var4x = 0;

            for(int var5 = 0; var5 < var3; ++var5) {
               long var6 = (long)((var5 ^ var1) - 1 >> 31);

               for(int var8 = 0; var8 < 4; ++var8) {
                  var2[var8] ^= var4[var4x + var8] & var6;
                  var3x[var8] ^= var4[var4x + 4 + var8] & var6;
               }

               var4x += 8;
            }

            return this.createPoint(var2, var3x);
         }

         public ECPoint lookupVar(int var1) {
            long[] var2 = Nat256.create64();
            long[] var3x = Nat256.create64();
            int var4x = var1 * 4 * 2;

            for(int var5 = 0; var5 < 4; ++var5) {
               var2[var5] = var4[var4x + var5];
               var3x[var5] = var4[var4x + 4 + var5];
            }

            return this.createPoint(var2, var3x);
         }

         private ECPoint createPoint(long[] var1, long[] var2) {
            return SecT239K1Curve.this.createRawPoint(new SecT239FieldElement(var1), new SecT239FieldElement(var2), SecT239K1Curve.SECT239K1_AFFINE_ZS);
         }
      };
   }

   static {
      SECT239K1_AFFINE_ZS = new ECFieldElement[]{new SecT239FieldElement(ECConstants.ONE)};
   }
}
