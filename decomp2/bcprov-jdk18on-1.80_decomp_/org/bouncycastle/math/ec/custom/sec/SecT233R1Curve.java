package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.AbstractECLookupTable;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECLookupTable;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.encoders.Hex;

public class SecT233R1Curve extends ECCurve.AbstractF2m {
   private static final int SECT233R1_DEFAULT_COORDS = 6;
   private static final ECFieldElement[] SECT233R1_AFFINE_ZS;
   protected SecT233R1Point infinity = new SecT233R1Point(this, (ECFieldElement)null, (ECFieldElement)null);

   public SecT233R1Curve() {
      super(233, 74, 0, 0);
      this.a = this.fromBigInteger(BigInteger.valueOf(1L));
      this.b = this.fromBigInteger(new BigInteger(1, Hex.decodeStrict("0066647EDE6C332C7F8C0923BB58213B333B20E9CE4281FE115F7D8F90AD")));
      this.order = new BigInteger(1, Hex.decodeStrict("01000000000000000000000000000013E974E72F8A6922031D2603CFE0D7"));
      this.cofactor = BigInteger.valueOf(2L);
      this.coord = 6;
   }

   protected ECCurve cloneCurve() {
      return new SecT233R1Curve();
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
      return 233;
   }

   public ECFieldElement fromBigInteger(BigInteger var1) {
      return new SecT233FieldElement(var1);
   }

   protected ECPoint createRawPoint(ECFieldElement var1, ECFieldElement var2) {
      return new SecT233R1Point(this, var1, var2);
   }

   protected ECPoint createRawPoint(ECFieldElement var1, ECFieldElement var2, ECFieldElement[] var3) {
      return new SecT233R1Point(this, var1, var2, var3);
   }

   public ECPoint getInfinity() {
      return this.infinity;
   }

   public boolean isKoblitz() {
      return false;
   }

   public int getM() {
      return 233;
   }

   public boolean isTrinomial() {
      return true;
   }

   public int getK1() {
      return 74;
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
         Nat256.copy64(((SecT233FieldElement)var7.getRawXCoord()).x, 0, var4, var5);
         var5 += 4;
         Nat256.copy64(((SecT233FieldElement)var7.getRawYCoord()).x, 0, var4, var5);
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
            return SecT233R1Curve.this.createRawPoint(new SecT233FieldElement(var1), new SecT233FieldElement(var2), SecT233R1Curve.SECT233R1_AFFINE_ZS);
         }
      };
   }

   static {
      SECT233R1_AFFINE_ZS = new ECFieldElement[]{new SecT233FieldElement(ECConstants.ONE)};
   }
}
