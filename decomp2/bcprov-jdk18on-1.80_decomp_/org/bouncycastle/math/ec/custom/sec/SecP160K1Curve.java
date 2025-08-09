package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.ec.AbstractECLookupTable;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECLookupTable;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.raw.Nat160;
import org.bouncycastle.util.encoders.Hex;

public class SecP160K1Curve extends ECCurve.AbstractFp {
   public static final BigInteger q;
   private static final int SECP160K1_DEFAULT_COORDS = 2;
   private static final ECFieldElement[] SECP160K1_AFFINE_ZS;
   protected SecP160K1Point infinity = new SecP160K1Point(this, (ECFieldElement)null, (ECFieldElement)null);

   public SecP160K1Curve() {
      super(q);
      this.a = this.fromBigInteger(ECConstants.ZERO);
      this.b = this.fromBigInteger(BigInteger.valueOf(7L));
      this.order = new BigInteger(1, Hex.decodeStrict("0100000000000000000001B8FA16DFAB9ACA16B6B3"));
      this.cofactor = BigInteger.valueOf(1L);
      this.coord = 2;
   }

   protected ECCurve cloneCurve() {
      return new SecP160K1Curve();
   }

   public boolean supportsCoordinateSystem(int var1) {
      switch (var1) {
         case 2:
            return true;
         default:
            return false;
      }
   }

   public BigInteger getQ() {
      return q;
   }

   public int getFieldSize() {
      return q.bitLength();
   }

   public ECFieldElement fromBigInteger(BigInteger var1) {
      return new SecP160R2FieldElement(var1);
   }

   protected ECPoint createRawPoint(ECFieldElement var1, ECFieldElement var2) {
      return new SecP160K1Point(this, var1, var2);
   }

   protected ECPoint createRawPoint(ECFieldElement var1, ECFieldElement var2, ECFieldElement[] var3) {
      return new SecP160K1Point(this, var1, var2, var3);
   }

   public ECPoint getInfinity() {
      return this.infinity;
   }

   public ECLookupTable createCacheSafeLookupTable(ECPoint[] var1, int var2, final int var3) {
      final int[] var4 = new int[var3 * 5 * 2];
      int var5 = 0;

      for(int var6 = 0; var6 < var3; ++var6) {
         ECPoint var7 = var1[var2 + var6];
         Nat160.copy(((SecP160R2FieldElement)var7.getRawXCoord()).x, 0, var4, var5);
         var5 += 5;
         Nat160.copy(((SecP160R2FieldElement)var7.getRawYCoord()).x, 0, var4, var5);
         var5 += 5;
      }

      return new AbstractECLookupTable() {
         public int getSize() {
            return var3;
         }

         public ECPoint lookup(int var1) {
            int[] var2 = Nat160.create();
            int[] var3x = Nat160.create();
            int var4x = 0;

            for(int var5 = 0; var5 < var3; ++var5) {
               int var6 = (var5 ^ var1) - 1 >> 31;

               for(int var7 = 0; var7 < 5; ++var7) {
                  var2[var7] ^= var4[var4x + var7] & var6;
                  var3x[var7] ^= var4[var4x + 5 + var7] & var6;
               }

               var4x += 10;
            }

            return this.createPoint(var2, var3x);
         }

         public ECPoint lookupVar(int var1) {
            int[] var2 = Nat160.create();
            int[] var3x = Nat160.create();
            int var4x = var1 * 5 * 2;

            for(int var5 = 0; var5 < 5; ++var5) {
               var2[var5] = var4[var4x + var5];
               var3x[var5] = var4[var4x + 5 + var5];
            }

            return this.createPoint(var2, var3x);
         }

         private ECPoint createPoint(int[] var1, int[] var2) {
            return SecP160K1Curve.this.createRawPoint(new SecP160R2FieldElement(var1), new SecP160R2FieldElement(var2), SecP160K1Curve.SECP160K1_AFFINE_ZS);
         }
      };
   }

   public ECFieldElement randomFieldElement(SecureRandom var1) {
      int[] var2 = Nat160.create();
      SecP160R2Field.random(var1, var2);
      return new SecP160R2FieldElement(var2);
   }

   public ECFieldElement randomFieldElementMult(SecureRandom var1) {
      int[] var2 = Nat160.create();
      SecP160R2Field.randomMult(var1, var2);
      return new SecP160R2FieldElement(var2);
   }

   static {
      q = SecP160R2FieldElement.Q;
      SECP160K1_AFFINE_ZS = new ECFieldElement[]{new SecP160R2FieldElement(ECConstants.ONE)};
   }
}
