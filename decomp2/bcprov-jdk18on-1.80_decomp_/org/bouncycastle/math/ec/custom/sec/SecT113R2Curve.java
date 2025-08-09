package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.AbstractECLookupTable;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECLookupTable;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.raw.Nat128;
import org.bouncycastle.util.encoders.Hex;

public class SecT113R2Curve extends ECCurve.AbstractF2m {
   private static final int SECT113R2_DEFAULT_COORDS = 6;
   private static final ECFieldElement[] SECT113R2_AFFINE_ZS;
   protected SecT113R2Point infinity = new SecT113R2Point(this, (ECFieldElement)null, (ECFieldElement)null);

   public SecT113R2Curve() {
      super(113, 9, 0, 0);
      this.a = this.fromBigInteger(new BigInteger(1, Hex.decodeStrict("00689918DBEC7E5A0DD6DFC0AA55C7")));
      this.b = this.fromBigInteger(new BigInteger(1, Hex.decodeStrict("0095E9A9EC9B297BD4BF36E059184F")));
      this.order = new BigInteger(1, Hex.decodeStrict("010000000000000108789B2496AF93"));
      this.cofactor = BigInteger.valueOf(2L);
      this.coord = 6;
   }

   protected ECCurve cloneCurve() {
      return new SecT113R2Curve();
   }

   public boolean supportsCoordinateSystem(int var1) {
      switch (var1) {
         case 6:
            return true;
         default:
            return false;
      }
   }

   public int getFieldSize() {
      return 113;
   }

   public ECFieldElement fromBigInteger(BigInteger var1) {
      return new SecT113FieldElement(var1);
   }

   protected ECPoint createRawPoint(ECFieldElement var1, ECFieldElement var2) {
      return new SecT113R2Point(this, var1, var2);
   }

   protected ECPoint createRawPoint(ECFieldElement var1, ECFieldElement var2, ECFieldElement[] var3) {
      return new SecT113R2Point(this, var1, var2, var3);
   }

   public ECPoint getInfinity() {
      return this.infinity;
   }

   public boolean isKoblitz() {
      return false;
   }

   public int getM() {
      return 113;
   }

   public boolean isTrinomial() {
      return true;
   }

   public int getK1() {
      return 9;
   }

   public int getK2() {
      return 0;
   }

   public int getK3() {
      return 0;
   }

   public ECLookupTable createCacheSafeLookupTable(ECPoint[] var1, int var2, final int var3) {
      final long[] var4 = new long[var3 * 2 * 2];
      int var5 = 0;

      for(int var6 = 0; var6 < var3; ++var6) {
         ECPoint var7 = var1[var2 + var6];
         Nat128.copy64(((SecT113FieldElement)var7.getRawXCoord()).x, 0, var4, var5);
         var5 += 2;
         Nat128.copy64(((SecT113FieldElement)var7.getRawYCoord()).x, 0, var4, var5);
         var5 += 2;
      }

      return new AbstractECLookupTable() {
         public int getSize() {
            return var3;
         }

         public ECPoint lookup(int var1) {
            long[] var2 = Nat128.create64();
            long[] var3x = Nat128.create64();
            int var4x = 0;

            for(int var5 = 0; var5 < var3; ++var5) {
               long var6 = (long)((var5 ^ var1) - 1 >> 31);

               for(int var8 = 0; var8 < 2; ++var8) {
                  var2[var8] ^= var4[var4x + var8] & var6;
                  var3x[var8] ^= var4[var4x + 2 + var8] & var6;
               }

               var4x += 4;
            }

            return this.createPoint(var2, var3x);
         }

         public ECPoint lookupVar(int var1) {
            long[] var2 = Nat128.create64();
            long[] var3x = Nat128.create64();
            int var4x = var1 * 2 * 2;

            for(int var5 = 0; var5 < 2; ++var5) {
               var2[var5] = var4[var4x + var5];
               var3x[var5] = var4[var4x + 2 + var5];
            }

            return this.createPoint(var2, var3x);
         }

         private ECPoint createPoint(long[] var1, long[] var2) {
            return SecT113R2Curve.this.createRawPoint(new SecT113FieldElement(var1), new SecT113FieldElement(var2), SecT113R2Curve.SECT113R2_AFFINE_ZS);
         }
      };
   }

   static {
      SECT113R2_AFFINE_ZS = new ECFieldElement[]{new SecT113FieldElement(ECConstants.ONE)};
   }
}
