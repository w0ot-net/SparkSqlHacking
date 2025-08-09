package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.ec.AbstractECLookupTable;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECLookupTable;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.util.encoders.Hex;

public class SecP384R1Curve extends ECCurve.AbstractFp {
   public static final BigInteger q;
   private static final int SECP384R1_DEFAULT_COORDS = 2;
   private static final ECFieldElement[] SECP384R1_AFFINE_ZS;
   protected SecP384R1Point infinity = new SecP384R1Point(this, (ECFieldElement)null, (ECFieldElement)null);

   public SecP384R1Curve() {
      super(q);
      this.a = this.fromBigInteger(new BigInteger(1, Hex.decodeStrict("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFF0000000000000000FFFFFFFC")));
      this.b = this.fromBigInteger(new BigInteger(1, Hex.decodeStrict("B3312FA7E23EE7E4988E056BE3F82D19181D9C6EFE8141120314088F5013875AC656398D8A2ED19D2A85C8EDD3EC2AEF")));
      this.order = new BigInteger(1, Hex.decodeStrict("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFC7634D81F4372DDF581A0DB248B0A77AECEC196ACCC52973"));
      this.cofactor = BigInteger.valueOf(1L);
      this.coord = 2;
   }

   protected ECCurve cloneCurve() {
      return new SecP384R1Curve();
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
      return new SecP384R1FieldElement(var1);
   }

   protected ECPoint createRawPoint(ECFieldElement var1, ECFieldElement var2) {
      return new SecP384R1Point(this, var1, var2);
   }

   protected ECPoint createRawPoint(ECFieldElement var1, ECFieldElement var2, ECFieldElement[] var3) {
      return new SecP384R1Point(this, var1, var2, var3);
   }

   public ECPoint getInfinity() {
      return this.infinity;
   }

   public ECLookupTable createCacheSafeLookupTable(ECPoint[] var1, int var2, final int var3) {
      final int[] var4 = new int[var3 * 12 * 2];
      int var5 = 0;

      for(int var6 = 0; var6 < var3; ++var6) {
         ECPoint var7 = var1[var2 + var6];
         Nat.copy(12, ((SecP384R1FieldElement)var7.getRawXCoord()).x, 0, var4, var5);
         var5 += 12;
         Nat.copy(12, ((SecP384R1FieldElement)var7.getRawYCoord()).x, 0, var4, var5);
         var5 += 12;
      }

      return new AbstractECLookupTable() {
         public int getSize() {
            return var3;
         }

         public ECPoint lookup(int var1) {
            int[] var2 = Nat.create(12);
            int[] var3x = Nat.create(12);
            int var4x = 0;

            for(int var5 = 0; var5 < var3; ++var5) {
               int var6 = (var5 ^ var1) - 1 >> 31;

               for(int var7 = 0; var7 < 12; ++var7) {
                  var2[var7] ^= var4[var4x + var7] & var6;
                  var3x[var7] ^= var4[var4x + 12 + var7] & var6;
               }

               var4x += 24;
            }

            return this.createPoint(var2, var3x);
         }

         public ECPoint lookupVar(int var1) {
            int[] var2 = Nat.create(12);
            int[] var3x = Nat.create(12);
            int var4x = var1 * 12 * 2;

            for(int var5 = 0; var5 < 12; ++var5) {
               var2[var5] = var4[var4x + var5];
               var3x[var5] = var4[var4x + 12 + var5];
            }

            return this.createPoint(var2, var3x);
         }

         private ECPoint createPoint(int[] var1, int[] var2) {
            return SecP384R1Curve.this.createRawPoint(new SecP384R1FieldElement(var1), new SecP384R1FieldElement(var2), SecP384R1Curve.SECP384R1_AFFINE_ZS);
         }
      };
   }

   public ECFieldElement randomFieldElement(SecureRandom var1) {
      int[] var2 = Nat.create(12);
      SecP384R1Field.random(var1, var2);
      return new SecP384R1FieldElement(var2);
   }

   public ECFieldElement randomFieldElementMult(SecureRandom var1) {
      int[] var2 = Nat.create(12);
      SecP384R1Field.randomMult(var1, var2);
      return new SecP384R1FieldElement(var2);
   }

   static {
      q = SecP384R1FieldElement.Q;
      SECP384R1_AFFINE_ZS = new ECFieldElement[]{new SecP384R1FieldElement(ECConstants.ONE)};
   }
}
