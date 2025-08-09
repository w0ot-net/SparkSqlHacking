package org.bouncycastle.pqc.legacy.math.linearalgebra;

import java.security.SecureRandom;

public abstract class GF2nField {
   protected final SecureRandom random;
   protected int mDegree;
   protected GF2Polynomial fieldPolynomial;
   protected java.util.Vector fields;
   protected java.util.Vector matrices;

   protected GF2nField(SecureRandom var1) {
      this.random = var1;
   }

   public final int getDegree() {
      return this.mDegree;
   }

   public final GF2Polynomial getFieldPolynomial() {
      if (this.fieldPolynomial == null) {
         this.computeFieldPolynomial();
      }

      return new GF2Polynomial(this.fieldPolynomial);
   }

   public final boolean equals(Object var1) {
      if (var1 != null && var1 instanceof GF2nField) {
         GF2nField var2 = (GF2nField)var1;
         if (var2.mDegree != this.mDegree) {
            return false;
         } else if (!this.fieldPolynomial.equals(var2.fieldPolynomial)) {
            return false;
         } else if (this instanceof GF2nPolynomialField && !(var2 instanceof GF2nPolynomialField)) {
            return false;
         } else {
            return !(this instanceof GF2nONBField) || var2 instanceof GF2nONBField;
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.mDegree + this.fieldPolynomial.hashCode();
   }

   protected abstract GF2nElement getRandomRoot(GF2Polynomial var1);

   protected abstract void computeCOBMatrix(GF2nField var1);

   protected abstract void computeFieldPolynomial();

   protected final GF2Polynomial[] invertMatrix(GF2Polynomial[] var1) {
      GF2Polynomial[] var2 = new GF2Polynomial[var1.length];
      GF2Polynomial[] var3 = new GF2Polynomial[var1.length];

      for(int var5 = 0; var5 < this.mDegree; ++var5) {
         var2[var5] = new GF2Polynomial(var1[var5]);
         var3[var5] = new GF2Polynomial(this.mDegree);
         var3[var5].setBit(this.mDegree - 1 - var5);
      }

      for(int var8 = 0; var8 < this.mDegree - 1; ++var8) {
         int var6;
         for(var6 = var8; var6 < this.mDegree && !var2[var6].testBit(this.mDegree - 1 - var8); ++var6) {
         }

         if (var6 >= this.mDegree) {
            throw new RuntimeException("GF2nField.invertMatrix: Matrix cannot be inverted!");
         }

         if (var8 != var6) {
            GF2Polynomial var4 = var2[var8];
            var2[var8] = var2[var6];
            var2[var6] = var4;
            var4 = var3[var8];
            var3[var8] = var3[var6];
            var3[var6] = var4;
         }

         for(int var10 = var8 + 1; var10 < this.mDegree; ++var10) {
            if (var2[var10].testBit(this.mDegree - 1 - var8)) {
               var2[var10].addToThis(var2[var8]);
               var3[var10].addToThis(var3[var8]);
            }
         }
      }

      for(int var9 = this.mDegree - 1; var9 > 0; --var9) {
         for(int var11 = var9 - 1; var11 >= 0; --var11) {
            if (var2[var11].testBit(this.mDegree - 1 - var9)) {
               var2[var11].addToThis(var2[var9]);
               var3[var11].addToThis(var3[var9]);
            }
         }
      }

      return var3;
   }

   public final GF2nElement convert(GF2nElement var1, GF2nField var2) throws RuntimeException {
      if (var2 == this) {
         return (GF2nElement)var1.clone();
      } else if (this.fieldPolynomial.equals(var2.fieldPolynomial)) {
         return (GF2nElement)var1.clone();
      } else if (this.mDegree != var2.mDegree) {
         throw new RuntimeException("GF2nField.convert: B1 has a different degree and thus cannot be coverted to!");
      } else {
         int var3 = this.fields.indexOf(var2);
         if (var3 == -1) {
            this.computeCOBMatrix(var2);
            var3 = this.fields.indexOf(var2);
         }

         GF2Polynomial[] var4 = (GF2Polynomial[])this.matrices.elementAt(var3);
         GF2nElement var5 = (GF2nElement)var1.clone();
         if (var5 instanceof GF2nONBElement) {
            ((GF2nONBElement)var5).reverseOrder();
         }

         GF2Polynomial var6 = new GF2Polynomial(this.mDegree, var5.toFlexiBigInt());
         var6.expandN(this.mDegree);
         GF2Polynomial var7 = new GF2Polynomial(this.mDegree);

         for(int var9 = 0; var9 < this.mDegree; ++var9) {
            if (var6.vectorMult(var4[var9])) {
               var7.setBit(this.mDegree - 1 - var9);
            }
         }

         if (var2 instanceof GF2nPolynomialField) {
            return new GF2nPolynomialElement((GF2nPolynomialField)var2, var7);
         } else if (var2 instanceof GF2nONBField) {
            GF2nONBElement var8 = new GF2nONBElement((GF2nONBField)var2, var7.toFlexiBigInt());
            var8.reverseOrder();
            return var8;
         } else {
            throw new RuntimeException("GF2nField.convert: B1 must be an instance of GF2nPolynomialField or GF2nONBField!");
         }
      }
   }
}
