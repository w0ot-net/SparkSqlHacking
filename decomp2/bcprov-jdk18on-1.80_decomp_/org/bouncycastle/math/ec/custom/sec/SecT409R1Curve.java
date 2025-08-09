package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.AbstractECLookupTable;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECLookupTable;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.raw.Nat448;
import org.bouncycastle.util.encoders.Hex;

public class SecT409R1Curve extends ECCurve.AbstractF2m {
   private static final int SECT409R1_DEFAULT_COORDS = 6;
   private static final ECFieldElement[] SECT409R1_AFFINE_ZS;
   protected SecT409R1Point infinity = new SecT409R1Point(this, (ECFieldElement)null, (ECFieldElement)null);

   public SecT409R1Curve() {
      super(409, 87, 0, 0);
      this.a = this.fromBigInteger(BigInteger.valueOf(1L));
      this.b = this.fromBigInteger(new BigInteger(1, Hex.decodeStrict("0021A5C2C8EE9FEB5C4B9A753B7B476B7FD6422EF1F3DD674761FA99D6AC27C8A9A197B272822F6CD57A55AA4F50AE317B13545F")));
      this.order = new BigInteger(1, Hex.decodeStrict("010000000000000000000000000000000000000000000000000001E2AAD6A612F33307BE5FA47C3C9E052F838164CD37D9A21173"));
      this.cofactor = BigInteger.valueOf(2L);
      this.coord = 6;
   }

   protected ECCurve cloneCurve() {
      return new SecT409R1Curve();
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
      return 409;
   }

   public ECFieldElement fromBigInteger(BigInteger var1) {
      return new SecT409FieldElement(var1);
   }

   protected ECPoint createRawPoint(ECFieldElement var1, ECFieldElement var2) {
      return new SecT409R1Point(this, var1, var2);
   }

   protected ECPoint createRawPoint(ECFieldElement var1, ECFieldElement var2, ECFieldElement[] var3) {
      return new SecT409R1Point(this, var1, var2, var3);
   }

   public ECPoint getInfinity() {
      return this.infinity;
   }

   public boolean isKoblitz() {
      return false;
   }

   public int getM() {
      return 409;
   }

   public boolean isTrinomial() {
      return true;
   }

   public int getK1() {
      return 87;
   }

   public int getK2() {
      return 0;
   }

   public int getK3() {
      return 0;
   }

   public ECLookupTable createCacheSafeLookupTable(ECPoint[] var1, int var2, final int var3) {
      final long[] var4 = new long[var3 * 7 * 2];
      int var5 = 0;

      for(int var6 = 0; var6 < var3; ++var6) {
         ECPoint var7 = var1[var2 + var6];
         Nat448.copy64(((SecT409FieldElement)var7.getRawXCoord()).x, 0, var4, var5);
         var5 += 7;
         Nat448.copy64(((SecT409FieldElement)var7.getRawYCoord()).x, 0, var4, var5);
         var5 += 7;
      }

      return new AbstractECLookupTable() {
         public int getSize() {
            return var3;
         }

         public ECPoint lookup(int var1) {
            long[] var2 = Nat448.create64();
            long[] var3x = Nat448.create64();
            int var4x = 0;

            for(int var5 = 0; var5 < var3; ++var5) {
               long var6 = (long)((var5 ^ var1) - 1 >> 31);

               for(int var8 = 0; var8 < 7; ++var8) {
                  var2[var8] ^= var4[var4x + var8] & var6;
                  var3x[var8] ^= var4[var4x + 7 + var8] & var6;
               }

               var4x += 14;
            }

            return this.createPoint(var2, var3x);
         }

         public ECPoint lookupVar(int var1) {
            long[] var2 = Nat448.create64();
            long[] var3x = Nat448.create64();
            int var4x = var1 * 7 * 2;

            for(int var5 = 0; var5 < 7; ++var5) {
               var2[var5] = var4[var4x + var5];
               var3x[var5] = var4[var4x + 7 + var5];
            }

            return this.createPoint(var2, var3x);
         }

         private ECPoint createPoint(long[] var1, long[] var2) {
            return SecT409R1Curve.this.createRawPoint(new SecT409FieldElement(var1), new SecT409FieldElement(var2), SecT409R1Curve.SECT409R1_AFFINE_ZS);
         }
      };
   }

   static {
      SECT409R1_AFFINE_ZS = new ECFieldElement[]{new SecT409FieldElement(ECConstants.ONE)};
   }
}
