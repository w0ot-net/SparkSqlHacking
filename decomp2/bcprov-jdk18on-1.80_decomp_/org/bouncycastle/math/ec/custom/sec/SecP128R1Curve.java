package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.ec.AbstractECLookupTable;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECLookupTable;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.raw.Nat128;
import org.bouncycastle.util.encoders.Hex;

public class SecP128R1Curve extends ECCurve.AbstractFp {
   public static final BigInteger q;
   private static final int SECP128R1_DEFAULT_COORDS = 2;
   private static final ECFieldElement[] SECP128R1_AFFINE_ZS;
   protected SecP128R1Point infinity = new SecP128R1Point(this, (ECFieldElement)null, (ECFieldElement)null);

   public SecP128R1Curve() {
      super(q);
      this.a = this.fromBigInteger(new BigInteger(1, Hex.decodeStrict("FFFFFFFDFFFFFFFFFFFFFFFFFFFFFFFC")));
      this.b = this.fromBigInteger(new BigInteger(1, Hex.decodeStrict("E87579C11079F43DD824993C2CEE5ED3")));
      this.order = new BigInteger(1, Hex.decodeStrict("FFFFFFFE0000000075A30D1B9038A115"));
      this.cofactor = BigInteger.valueOf(1L);
      this.coord = 2;
   }

   protected ECCurve cloneCurve() {
      return new SecP128R1Curve();
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
      return new SecP128R1FieldElement(var1);
   }

   protected ECPoint createRawPoint(ECFieldElement var1, ECFieldElement var2) {
      return new SecP128R1Point(this, var1, var2);
   }

   protected ECPoint createRawPoint(ECFieldElement var1, ECFieldElement var2, ECFieldElement[] var3) {
      return new SecP128R1Point(this, var1, var2, var3);
   }

   public ECPoint getInfinity() {
      return this.infinity;
   }

   public ECLookupTable createCacheSafeLookupTable(ECPoint[] var1, int var2, final int var3) {
      final int[] var4 = new int[var3 * 4 * 2];
      int var5 = 0;

      for(int var6 = 0; var6 < var3; ++var6) {
         ECPoint var7 = var1[var2 + var6];
         Nat128.copy(((SecP128R1FieldElement)var7.getRawXCoord()).x, 0, var4, var5);
         var5 += 4;
         Nat128.copy(((SecP128R1FieldElement)var7.getRawYCoord()).x, 0, var4, var5);
         var5 += 4;
      }

      return new AbstractECLookupTable() {
         public int getSize() {
            return var3;
         }

         public ECPoint lookup(int var1) {
            int[] var2 = Nat128.create();
            int[] var3x = Nat128.create();
            int var4x = 0;

            for(int var5 = 0; var5 < var3; ++var5) {
               int var6 = (var5 ^ var1) - 1 >> 31;

               for(int var7 = 0; var7 < 4; ++var7) {
                  var2[var7] ^= var4[var4x + var7] & var6;
                  var3x[var7] ^= var4[var4x + 4 + var7] & var6;
               }

               var4x += 8;
            }

            return this.createPoint(var2, var3x);
         }

         public ECPoint lookupVar(int var1) {
            int[] var2 = Nat128.create();
            int[] var3x = Nat128.create();
            int var4x = var1 * 4 * 2;

            for(int var5 = 0; var5 < 4; ++var5) {
               var2[var5] = var4[var4x + var5];
               var3x[var5] = var4[var4x + 4 + var5];
            }

            return this.createPoint(var2, var3x);
         }

         private ECPoint createPoint(int[] var1, int[] var2) {
            return SecP128R1Curve.this.createRawPoint(new SecP128R1FieldElement(var1), new SecP128R1FieldElement(var2), SecP128R1Curve.SECP128R1_AFFINE_ZS);
         }
      };
   }

   public ECFieldElement randomFieldElement(SecureRandom var1) {
      int[] var2 = Nat128.create();
      SecP128R1Field.random(var1, var2);
      return new SecP128R1FieldElement(var2);
   }

   public ECFieldElement randomFieldElementMult(SecureRandom var1) {
      int[] var2 = Nat128.create();
      SecP128R1Field.randomMult(var1, var2);
      return new SecP128R1FieldElement(var2);
   }

   static {
      q = SecP128R1FieldElement.Q;
      SECP128R1_AFFINE_ZS = new ECFieldElement[]{new SecP128R1FieldElement(ECConstants.ONE)};
   }
}
