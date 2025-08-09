package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.AbstractECLookupTable;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECLookupTable;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.raw.Nat576;
import org.bouncycastle.util.encoders.Hex;

public class SecT571R1Curve extends ECCurve.AbstractF2m {
   private static final int SECT571R1_DEFAULT_COORDS = 6;
   private static final ECFieldElement[] SECT571R1_AFFINE_ZS;
   protected SecT571R1Point infinity = new SecT571R1Point(this, (ECFieldElement)null, (ECFieldElement)null);
   static final SecT571FieldElement SecT571R1_B;
   static final SecT571FieldElement SecT571R1_B_SQRT;

   public SecT571R1Curve() {
      super(571, 2, 5, 10);
      this.a = this.fromBigInteger(BigInteger.valueOf(1L));
      this.b = SecT571R1_B;
      this.order = new BigInteger(1, Hex.decodeStrict("03FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFE661CE18FF55987308059B186823851EC7DD9CA1161DE93D5174D66E8382E9BB2FE84E47"));
      this.cofactor = BigInteger.valueOf(2L);
      this.coord = 6;
   }

   protected ECCurve cloneCurve() {
      return new SecT571R1Curve();
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
      return 571;
   }

   public ECFieldElement fromBigInteger(BigInteger var1) {
      return new SecT571FieldElement(var1);
   }

   protected ECPoint createRawPoint(ECFieldElement var1, ECFieldElement var2) {
      return new SecT571R1Point(this, var1, var2);
   }

   protected ECPoint createRawPoint(ECFieldElement var1, ECFieldElement var2, ECFieldElement[] var3) {
      return new SecT571R1Point(this, var1, var2, var3);
   }

   public ECPoint getInfinity() {
      return this.infinity;
   }

   public boolean isKoblitz() {
      return false;
   }

   public int getM() {
      return 571;
   }

   public boolean isTrinomial() {
      return false;
   }

   public int getK1() {
      return 2;
   }

   public int getK2() {
      return 5;
   }

   public int getK3() {
      return 10;
   }

   public ECLookupTable createCacheSafeLookupTable(ECPoint[] var1, int var2, final int var3) {
      final long[] var4 = new long[var3 * 9 * 2];
      int var5 = 0;

      for(int var6 = 0; var6 < var3; ++var6) {
         ECPoint var7 = var1[var2 + var6];
         Nat576.copy64(((SecT571FieldElement)var7.getRawXCoord()).x, 0, var4, var5);
         var5 += 9;
         Nat576.copy64(((SecT571FieldElement)var7.getRawYCoord()).x, 0, var4, var5);
         var5 += 9;
      }

      return new AbstractECLookupTable() {
         public int getSize() {
            return var3;
         }

         public ECPoint lookup(int var1) {
            long[] var2 = Nat576.create64();
            long[] var3x = Nat576.create64();
            int var4x = 0;

            for(int var5 = 0; var5 < var3; ++var5) {
               long var6 = (long)((var5 ^ var1) - 1 >> 31);

               for(int var8 = 0; var8 < 9; ++var8) {
                  var2[var8] ^= var4[var4x + var8] & var6;
                  var3x[var8] ^= var4[var4x + 9 + var8] & var6;
               }

               var4x += 18;
            }

            return this.createPoint(var2, var3x);
         }

         public ECPoint lookupVar(int var1) {
            long[] var2 = Nat576.create64();
            long[] var3x = Nat576.create64();
            int var4x = var1 * 9 * 2;

            for(int var5 = 0; var5 < 9; ++var5) {
               var2[var5] = var4[var4x + var5];
               var3x[var5] = var4[var4x + 9 + var5];
            }

            return this.createPoint(var2, var3x);
         }

         private ECPoint createPoint(long[] var1, long[] var2) {
            return SecT571R1Curve.this.createRawPoint(new SecT571FieldElement(var1), new SecT571FieldElement(var2), SecT571R1Curve.SECT571R1_AFFINE_ZS);
         }
      };
   }

   static {
      SECT571R1_AFFINE_ZS = new ECFieldElement[]{new SecT571FieldElement(ECConstants.ONE)};
      SecT571R1_B = new SecT571FieldElement(new BigInteger(1, Hex.decodeStrict("02F40E7E2221F295DE297117B7F3D62F5C6A97FFCB8CEFF1CD6BA8CE4A9A18AD84FFABBD8EFA59332BE7AD6756A66E294AFD185A78FF12AA520E4DE739BACA0C7FFEFF7F2955727A")));
      SecT571R1_B_SQRT = (SecT571FieldElement)SecT571R1_B.sqrt();
   }
}
