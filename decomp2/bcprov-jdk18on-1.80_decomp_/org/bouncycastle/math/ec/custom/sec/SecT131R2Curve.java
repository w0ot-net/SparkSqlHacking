package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.AbstractECLookupTable;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECLookupTable;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.raw.Nat192;
import org.bouncycastle.util.encoders.Hex;

public class SecT131R2Curve extends ECCurve.AbstractF2m {
   private static final int SECT131R2_DEFAULT_COORDS = 6;
   private static final ECFieldElement[] SECT131R2_AFFINE_ZS;
   protected SecT131R2Point infinity = new SecT131R2Point(this, (ECFieldElement)null, (ECFieldElement)null);

   public SecT131R2Curve() {
      super(131, 2, 3, 8);
      this.a = this.fromBigInteger(new BigInteger(1, Hex.decodeStrict("03E5A88919D7CAFCBF415F07C2176573B2")));
      this.b = this.fromBigInteger(new BigInteger(1, Hex.decodeStrict("04B8266A46C55657AC734CE38F018F2192")));
      this.order = new BigInteger(1, Hex.decodeStrict("0400000000000000016954A233049BA98F"));
      this.cofactor = BigInteger.valueOf(2L);
      this.coord = 6;
   }

   protected ECCurve cloneCurve() {
      return new SecT131R2Curve();
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
      return 131;
   }

   public ECFieldElement fromBigInteger(BigInteger var1) {
      return new SecT131FieldElement(var1);
   }

   protected ECPoint createRawPoint(ECFieldElement var1, ECFieldElement var2) {
      return new SecT131R2Point(this, var1, var2);
   }

   protected ECPoint createRawPoint(ECFieldElement var1, ECFieldElement var2, ECFieldElement[] var3) {
      return new SecT131R2Point(this, var1, var2, var3);
   }

   public ECPoint getInfinity() {
      return this.infinity;
   }

   public boolean isKoblitz() {
      return false;
   }

   public int getM() {
      return 131;
   }

   public boolean isTrinomial() {
      return false;
   }

   public int getK1() {
      return 2;
   }

   public int getK2() {
      return 3;
   }

   public int getK3() {
      return 8;
   }

   public ECLookupTable createCacheSafeLookupTable(ECPoint[] var1, int var2, final int var3) {
      final long[] var4 = new long[var3 * 3 * 2];
      int var5 = 0;

      for(int var6 = 0; var6 < var3; ++var6) {
         ECPoint var7 = var1[var2 + var6];
         Nat192.copy64(((SecT131FieldElement)var7.getRawXCoord()).x, 0, var4, var5);
         var5 += 3;
         Nat192.copy64(((SecT131FieldElement)var7.getRawYCoord()).x, 0, var4, var5);
         var5 += 3;
      }

      return new AbstractECLookupTable() {
         public int getSize() {
            return var3;
         }

         public ECPoint lookup(int var1) {
            long[] var2 = Nat192.create64();
            long[] var3x = Nat192.create64();
            int var4x = 0;

            for(int var5 = 0; var5 < var3; ++var5) {
               long var6 = (long)((var5 ^ var1) - 1 >> 31);

               for(int var8 = 0; var8 < 3; ++var8) {
                  var2[var8] ^= var4[var4x + var8] & var6;
                  var3x[var8] ^= var4[var4x + 3 + var8] & var6;
               }

               var4x += 6;
            }

            return this.createPoint(var2, var3x);
         }

         public ECPoint lookupVar(int var1) {
            long[] var2 = Nat192.create64();
            long[] var3x = Nat192.create64();
            int var4x = var1 * 3 * 2;

            for(int var5 = 0; var5 < 3; ++var5) {
               var2[var5] = var4[var4x + var5];
               var3x[var5] = var4[var4x + 3 + var5];
            }

            return this.createPoint(var2, var3x);
         }

         private ECPoint createPoint(long[] var1, long[] var2) {
            return SecT131R2Curve.this.createRawPoint(new SecT131FieldElement(var1), new SecT131FieldElement(var2), SecT131R2Curve.SECT131R2_AFFINE_ZS);
         }
      };
   }

   static {
      SECT131R2_AFFINE_ZS = new ECFieldElement[]{new SecT131FieldElement(ECConstants.ONE)};
   }
}
