package org.bouncycastle.pqc.legacy.crypto.rainbow.util;

public class ComputeInField {
   private short[][] A;
   short[] x;

   public short[] solveEquation(short[][] var1, short[] var2) {
      if (var1.length != var2.length) {
         return null;
      } else {
         try {
            this.A = new short[var1.length][var1.length + 1];
            this.x = new short[var1.length];

            for(int var3 = 0; var3 < var1.length; ++var3) {
               for(int var4 = 0; var4 < var1[0].length; ++var4) {
                  this.A[var3][var4] = var1[var3][var4];
               }
            }

            for(int var6 = 0; var6 < var2.length; ++var6) {
               this.A[var6][var2.length] = GF2Field.addElem(var2[var6], this.A[var6][var2.length]);
            }

            this.computeZerosUnder(false);
            this.substitute();
            return this.x;
         } catch (RuntimeException var5) {
            return null;
         }
      }
   }

   public short[][] inverse(short[][] var1) {
      try {
         this.A = new short[var1.length][2 * var1.length];
         if (var1.length != var1[0].length) {
            throw new RuntimeException("The matrix is not invertible. Please choose another one!");
         } else {
            for(int var4 = 0; var4 < var1.length; ++var4) {
               for(int var5 = 0; var5 < var1.length; ++var5) {
                  this.A[var4][var5] = var1[var4][var5];
               }

               for(int var9 = var1.length; var9 < 2 * var1.length; ++var9) {
                  this.A[var4][var9] = 0;
               }

               this.A[var4][var4 + this.A.length] = 1;
            }

            this.computeZerosUnder(true);

            for(int var7 = 0; var7 < this.A.length; ++var7) {
               short var2 = GF2Field.invElem(this.A[var7][var7]);

               for(int var10 = var7; var10 < 2 * this.A.length; ++var10) {
                  this.A[var7][var10] = GF2Field.multElem(this.A[var7][var10], var2);
               }
            }

            this.computeZerosAbove();
            short[][] var3 = new short[this.A.length][this.A.length];

            for(int var8 = 0; var8 < this.A.length; ++var8) {
               for(int var11 = this.A.length; var11 < 2 * this.A.length; ++var11) {
                  var3[var8][var11 - this.A.length] = this.A[var8][var11];
               }
            }

            return var3;
         }
      } catch (RuntimeException var6) {
         return null;
      }
   }

   private void computeZerosUnder(boolean var1) throws RuntimeException {
      short var3 = 0;
      int var2;
      if (var1) {
         var2 = 2 * this.A.length;
      } else {
         var2 = this.A.length + 1;
      }

      for(int var4 = 0; var4 < this.A.length - 1; ++var4) {
         for(int var5 = var4 + 1; var5 < this.A.length; ++var5) {
            short var6 = this.A[var5][var4];
            short var7 = GF2Field.invElem(this.A[var4][var4]);
            if (var7 == 0) {
               throw new IllegalStateException("Matrix not invertible! We have to choose another one!");
            }

            for(int var8 = var4; var8 < var2; ++var8) {
               var3 = GF2Field.multElem(this.A[var4][var8], var7);
               var3 = GF2Field.multElem(var6, var3);
               this.A[var5][var8] = GF2Field.addElem(this.A[var5][var8], var3);
            }
         }
      }

   }

   private void computeZerosAbove() throws RuntimeException {
      short var1 = 0;

      for(int var2 = this.A.length - 1; var2 > 0; --var2) {
         for(int var3 = var2 - 1; var3 >= 0; --var3) {
            short var4 = this.A[var3][var2];
            short var5 = GF2Field.invElem(this.A[var2][var2]);
            if (var5 == 0) {
               throw new RuntimeException("The matrix is not invertible");
            }

            for(int var6 = var2; var6 < 2 * this.A.length; ++var6) {
               var1 = GF2Field.multElem(this.A[var2][var6], var5);
               var1 = GF2Field.multElem(var4, var1);
               this.A[var3][var6] = GF2Field.addElem(this.A[var3][var6], var1);
            }
         }
      }

   }

   private void substitute() throws IllegalStateException {
      short var2 = GF2Field.invElem(this.A[this.A.length - 1][this.A.length - 1]);
      if (var2 == 0) {
         throw new IllegalStateException("The equation system is not solvable");
      } else {
         this.x[this.A.length - 1] = GF2Field.multElem(this.A[this.A.length - 1][this.A.length], var2);

         for(int var3 = this.A.length - 2; var3 >= 0; --var3) {
            short var1 = this.A[var3][this.A.length];

            for(int var4 = this.A.length - 1; var4 > var3; --var4) {
               var2 = GF2Field.multElem(this.A[var3][var4], this.x[var4]);
               var1 = GF2Field.addElem(var1, var2);
            }

            var2 = GF2Field.invElem(this.A[var3][var3]);
            if (var2 == 0) {
               throw new IllegalStateException("Not solvable equation system");
            }

            this.x[var3] = GF2Field.multElem(var1, var2);
         }

      }
   }

   public short[][] multiplyMatrix(short[][] var1, short[][] var2) throws RuntimeException {
      if (var1[0].length != var2.length) {
         throw new RuntimeException("Multiplication is not possible!");
      } else {
         short var3 = 0;
         this.A = new short[var1.length][var2[0].length];

         for(int var4 = 0; var4 < var1.length; ++var4) {
            for(int var5 = 0; var5 < var2.length; ++var5) {
               for(int var6 = 0; var6 < var2[0].length; ++var6) {
                  var3 = GF2Field.multElem(var1[var4][var5], var2[var5][var6]);
                  this.A[var4][var6] = GF2Field.addElem(this.A[var4][var6], var3);
               }
            }
         }

         return this.A;
      }
   }

   public short[] multiplyMatrix(short[][] var1, short[] var2) throws RuntimeException {
      if (var1[0].length != var2.length) {
         throw new RuntimeException("Multiplication is not possible!");
      } else {
         short var3 = 0;
         short[] var4 = new short[var1.length];

         for(int var5 = 0; var5 < var1.length; ++var5) {
            for(int var6 = 0; var6 < var2.length; ++var6) {
               var3 = GF2Field.multElem(var1[var5][var6], var2[var6]);
               var4[var5] = GF2Field.addElem(var4[var5], var3);
            }
         }

         return var4;
      }
   }

   public short[] addVect(short[] var1, short[] var2) {
      if (var1.length != var2.length) {
         throw new RuntimeException("Multiplication is not possible!");
      } else {
         short[] var3 = new short[var1.length];

         for(int var4 = 0; var4 < var3.length; ++var4) {
            var3[var4] = GF2Field.addElem(var1[var4], var2[var4]);
         }

         return var3;
      }
   }

   public short[][] multVects(short[] var1, short[] var2) {
      if (var1.length != var2.length) {
         throw new RuntimeException("Multiplication is not possible!");
      } else {
         short[][] var3 = new short[var1.length][var2.length];

         for(int var4 = 0; var4 < var1.length; ++var4) {
            for(int var5 = 0; var5 < var2.length; ++var5) {
               var3[var4][var5] = GF2Field.multElem(var1[var4], var2[var5]);
            }
         }

         return var3;
      }
   }

   public short[] multVect(short var1, short[] var2) {
      short[] var3 = new short[var2.length];

      for(int var4 = 0; var4 < var3.length; ++var4) {
         var3[var4] = GF2Field.multElem(var1, var2[var4]);
      }

      return var3;
   }

   public short[][] multMatrix(short var1, short[][] var2) {
      short[][] var3 = new short[var2.length][var2[0].length];

      for(int var4 = 0; var4 < var2.length; ++var4) {
         for(int var5 = 0; var5 < var2[0].length; ++var5) {
            var3[var4][var5] = GF2Field.multElem(var1, var2[var4][var5]);
         }
      }

      return var3;
   }

   public short[][] addSquareMatrix(short[][] var1, short[][] var2) {
      if (var1.length == var2.length && var1[0].length == var2[0].length) {
         short[][] var3 = new short[var1.length][var1.length];

         for(int var4 = 0; var4 < var1.length; ++var4) {
            for(int var5 = 0; var5 < var2.length; ++var5) {
               var3[var4][var5] = GF2Field.addElem(var1[var4][var5], var2[var4][var5]);
            }
         }

         return var3;
      } else {
         throw new RuntimeException("Addition is not possible!");
      }
   }
}
