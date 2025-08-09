package org.bouncycastle.pqc.legacy.math.linearalgebra;

import java.security.SecureRandom;
import java.util.Random;

public class GF2nONBField extends GF2nField {
   private static final int MAXLONG = 64;
   private int mLength;
   private int mBit;
   private int mType;
   int[][] mMult;

   public GF2nONBField(int var1, SecureRandom var2) throws RuntimeException {
      super(var2);
      if (var1 < 3) {
         throw new IllegalArgumentException("k must be at least 3");
      } else {
         this.mDegree = var1;
         this.mLength = this.mDegree / 64;
         this.mBit = this.mDegree & 63;
         if (this.mBit == 0) {
            this.mBit = 64;
         } else {
            ++this.mLength;
         }

         this.computeType();
         if (this.mType >= 3) {
            throw new RuntimeException("\nThe type of this field is " + this.mType);
         } else {
            this.mMult = new int[this.mDegree][2];

            for(int var3 = 0; var3 < this.mDegree; ++var3) {
               this.mMult[var3][0] = -1;
               this.mMult[var3][1] = -1;
            }

            this.computeMultMatrix();
            this.computeFieldPolynomial();
            this.fields = new java.util.Vector();
            this.matrices = new java.util.Vector();
         }
      }
   }

   int getONBLength() {
      return this.mLength;
   }

   int getONBBit() {
      return this.mBit;
   }

   protected GF2nElement getRandomRoot(GF2Polynomial var1) {
      GF2nPolynomial var7 = new GF2nPolynomial(var1, this);

      for(int var8 = var7.getDegree(); var8 > 1; var8 = var7.getDegree()) {
         GF2nONBElement var4 = new GF2nONBElement(this, this.random);
         GF2nPolynomial var3 = new GF2nPolynomial(2, GF2nONBElement.ZERO(this));
         var3.set(1, var4);
         GF2nPolynomial var2 = new GF2nPolynomial(var3);

         for(int var9 = 1; var9 <= this.mDegree - 1; ++var9) {
            var2 = var2.multiplyAndReduce(var2, var7);
            var2 = var2.add(var3);
         }

         GF2nPolynomial var5 = var2.gcd(var7);
         int var6 = var5.getDegree();
         var8 = var7.getDegree();
         if (var6 != 0 && var6 != var8) {
            if (var6 << 1 > var8) {
               var7 = var7.quotient(var5);
            } else {
               var7 = new GF2nPolynomial(var5);
            }
         }
      }

      return var7.at(0);
   }

   protected void computeCOBMatrix(GF2nField var1) {
      if (this.mDegree != var1.mDegree) {
         throw new IllegalArgumentException("GF2nField.computeCOBMatrix: B1 has a different degree and thus cannot be coverted to!");
      } else {
         GF2Polynomial[] var6 = new GF2Polynomial[this.mDegree];

         for(int var2 = 0; var2 < this.mDegree; ++var2) {
            var6[var2] = new GF2Polynomial(this.mDegree);
         }

         GF2nElement var5;
         do {
            var5 = var1.getRandomRoot(this.fieldPolynomial);
         } while(var5.isZero());

         GF2nPolynomialElement[] var4 = new GF2nPolynomialElement[this.mDegree];
         var4[0] = (GF2nElement)var5.clone();

         for(int var7 = 1; var7 < this.mDegree; ++var7) {
            var4[var7] = var4[var7 - 1].square();
         }

         for(int var8 = 0; var8 < this.mDegree; ++var8) {
            for(int var3 = 0; var3 < this.mDegree; ++var3) {
               if (var4[var8].testBit(var3)) {
                  var6[this.mDegree - var3 - 1].setBit(this.mDegree - var8 - 1);
               }
            }
         }

         this.fields.addElement(var1);
         this.matrices.addElement(var6);
         var1.fields.addElement(this);
         var1.matrices.addElement(this.invertMatrix(var6));
      }
   }

   protected void computeFieldPolynomial() {
      if (this.mType == 1) {
         this.fieldPolynomial = new GF2Polynomial(this.mDegree + 1, "ALL");
      } else if (this.mType == 2) {
         GF2Polynomial var1 = new GF2Polynomial(this.mDegree + 1, "ONE");
         GF2Polynomial var2 = new GF2Polynomial(this.mDegree + 1, "X");
         var2.addToThis(var1);

         for(int var4 = 1; var4 < this.mDegree; ++var4) {
            GF2Polynomial var3 = var1;
            var1 = var2;
            var2 = var2.shiftLeft();
            var2.addToThis(var3);
         }

         this.fieldPolynomial = var2;
      }

   }

   int[][] invMatrix(int[][] var1) {
      int[][] var10000 = new int[this.mDegree][this.mDegree];
      int[][] var2 = var1;
      int[][] var3 = new int[this.mDegree][this.mDegree];

      for(int var4 = 0; var4 < this.mDegree; ++var4) {
         var3[var4][var4] = 1;
      }

      for(int var6 = 0; var6 < this.mDegree; ++var6) {
         for(int var5 = var6; var5 < this.mDegree; ++var5) {
            var2[this.mDegree - 1 - var6][var5] = var2[var6][var6];
         }
      }

      return null;
   }

   private void computeType() throws RuntimeException {
      if ((this.mDegree & 7) == 0) {
         throw new RuntimeException("The extension degree is divisible by 8!");
      } else {
         int var1 = 0;
         int var2 = 0;
         this.mType = 1;

         for(int var3 = 0; var3 != 1; ++this.mType) {
            var1 = this.mType * this.mDegree + 1;
            if (IntegerFunctions.isPrime(var1)) {
               var2 = IntegerFunctions.order(2, var1);
               var3 = IntegerFunctions.gcd(this.mType * this.mDegree / var2, this.mDegree);
            }
         }

         --this.mType;
         if (this.mType == 1) {
            var1 = (this.mDegree << 1) + 1;
            if (IntegerFunctions.isPrime(var1)) {
               var2 = IntegerFunctions.order(2, var1);
               int var8 = IntegerFunctions.gcd((this.mDegree << 1) / var2, this.mDegree);
               if (var8 == 1) {
                  ++this.mType;
               }
            }
         }

      }
   }

   private void computeMultMatrix() {
      if ((this.mType & 7) == 0) {
         throw new RuntimeException("bisher nur fuer Gausssche Normalbasen implementiert");
      } else {
         int var1 = this.mType * this.mDegree + 1;
         int[] var2 = new int[var1];
         int var3;
         if (this.mType == 1) {
            var3 = 1;
         } else if (this.mType == 2) {
            var3 = var1 - 1;
         } else {
            var3 = this.elementOfOrder(this.mType, var1);
         }

         int var4 = 1;

         for(int var6 = 0; var6 < this.mType; ++var6) {
            int var5 = var4;

            for(int var7 = 0; var7 < this.mDegree; ++var7) {
               var2[var5] = var7;
               var5 = (var5 << 1) % var1;
               if (var5 < 0) {
                  var5 += var1;
               }
            }

            var4 = var3 * var4 % var1;
            if (var4 < 0) {
               var4 += var1;
            }
         }

         if (this.mType == 1) {
            for(int var8 = 1; var8 < var1 - 1; ++var8) {
               if (this.mMult[var2[var8 + 1]][0] == -1) {
                  this.mMult[var2[var8 + 1]][0] = var2[var1 - var8];
               } else {
                  this.mMult[var2[var8 + 1]][1] = var2[var1 - var8];
               }
            }

            int var9 = this.mDegree >> 1;

            for(int var11 = 1; var11 <= var9; ++var11) {
               if (this.mMult[var11 - 1][0] == -1) {
                  this.mMult[var11 - 1][0] = var9 + var11 - 1;
               } else {
                  this.mMult[var11 - 1][1] = var9 + var11 - 1;
               }

               if (this.mMult[var9 + var11 - 1][0] == -1) {
                  this.mMult[var9 + var11 - 1][0] = var11 - 1;
               } else {
                  this.mMult[var9 + var11 - 1][1] = var11 - 1;
               }
            }
         } else {
            if (this.mType != 2) {
               throw new RuntimeException("only type 1 or type 2 implemented");
            }

            for(int var10 = 1; var10 < var1 - 1; ++var10) {
               if (this.mMult[var2[var10 + 1]][0] == -1) {
                  this.mMult[var2[var10 + 1]][0] = var2[var1 - var10];
               } else {
                  this.mMult[var2[var10 + 1]][1] = var2[var1 - var10];
               }
            }
         }

      }
   }

   private int elementOfOrder(int var1, int var2) {
      Random var3 = new Random();
      int var4 = 0;

      while(var4 == 0) {
         var4 = var3.nextInt();
         var4 %= var2 - 1;
         if (var4 < 0) {
            var4 += var2 - 1;
         }
      }

      int var5;
      for(var5 = IntegerFunctions.order(var4, var2); var5 % var1 != 0 || var5 == 0; var5 = IntegerFunctions.order(var4, var2)) {
         while(var4 == 0) {
            var4 = var3.nextInt();
            var4 %= var2 - 1;
            if (var4 < 0) {
               var4 += var2 - 1;
            }
         }
      }

      int var6 = var4;
      var5 = var1 / var5;

      for(int var7 = 2; var7 <= var5; ++var7) {
         var6 *= var4;
      }

      return var6;
   }
}
