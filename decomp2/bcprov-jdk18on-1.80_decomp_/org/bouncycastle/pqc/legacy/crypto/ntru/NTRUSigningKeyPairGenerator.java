package org.bouncycastle.pqc.legacy.crypto.ntru;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.pqc.legacy.math.ntru.euclid.BigIntEuclidean;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.BigDecimalPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.BigIntPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.DenseTernaryPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.IntegerPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.Polynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.ProductFormPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.Resultant;

public class NTRUSigningKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
   private NTRUSigningKeyGenerationParameters params;

   public void init(KeyGenerationParameters var1) {
      this.params = (NTRUSigningKeyGenerationParameters)var1;
   }

   public AsymmetricCipherKeyPair generateKeyPair() {
      NTRUSigningPublicKeyParameters var1 = null;
      ExecutorService var2 = Executors.newCachedThreadPool();
      ArrayList var3 = new ArrayList();

      for(int var4 = this.params.B; var4 >= 0; --var4) {
         var3.add(var2.submit(new BasisGenerationTask()));
      }

      var2.shutdown();
      ArrayList var9 = new ArrayList();

      for(int var5 = this.params.B; var5 >= 0; --var5) {
         Future var6 = (Future)var3.get(var5);

         try {
            var9.add((NTRUSigningPrivateKeyParameters.Basis)var6.get());
            if (var5 == this.params.B) {
               var1 = new NTRUSigningPublicKeyParameters(((NTRUSigningPrivateKeyParameters.Basis)var6.get()).h, this.params.getSigningParameters());
            }
         } catch (Exception var8) {
            throw new IllegalStateException(var8);
         }
      }

      NTRUSigningPrivateKeyParameters var10 = new NTRUSigningPrivateKeyParameters(var9, var1);
      AsymmetricCipherKeyPair var11 = new AsymmetricCipherKeyPair(var1, var10);
      return var11;
   }

   public AsymmetricCipherKeyPair generateKeyPairSingleThread() {
      ArrayList var1 = new ArrayList();
      NTRUSigningPublicKeyParameters var2 = null;

      for(int var3 = this.params.B; var3 >= 0; --var3) {
         NTRUSigningPrivateKeyParameters.Basis var4 = this.generateBoundedBasis();
         var1.add(var4);
         if (var3 == 0) {
            var2 = new NTRUSigningPublicKeyParameters(var4.h, this.params.getSigningParameters());
         }
      }

      NTRUSigningPrivateKeyParameters var5 = new NTRUSigningPrivateKeyParameters(var1, var2);
      return new AsymmetricCipherKeyPair(var2, var5);
   }

   private void minimizeFG(IntegerPolynomial var1, IntegerPolynomial var2, IntegerPolynomial var3, IntegerPolynomial var4, int var5) {
      int var6 = 0;

      for(int var7 = 0; var7 < var5; ++var7) {
         var6 += 2 * var5 * (var1.coeffs[var7] * var1.coeffs[var7] + var2.coeffs[var7] * var2.coeffs[var7]);
      }

      var6 -= 4;
      IntegerPolynomial var18 = (IntegerPolynomial)var1.clone();
      IntegerPolynomial var8 = (IntegerPolynomial)var2.clone();
      int var9 = 0;
      int var10 = 0;
      int var11 = var5;

      while(var10 < var11 && var9 < var5) {
         int var12 = 0;

         for(int var13 = 0; var13 < var5; ++var13) {
            int var14 = var3.coeffs[var13] * var1.coeffs[var13];
            int var15 = var4.coeffs[var13] * var2.coeffs[var13];
            int var16 = 4 * var5 * (var14 + var15);
            var12 += var16;
         }

         int var20 = 4 * (var3.sumCoeffs() + var4.sumCoeffs());
         var12 -= var20;
         if (var12 > var6) {
            var3.sub(var18);
            var4.sub(var8);
            ++var10;
            var9 = 0;
         } else if (var12 < -var6) {
            var3.add(var18);
            var4.add(var8);
            ++var10;
            var9 = 0;
         }

         ++var9;
         var18.rotate1();
         var8.rotate1();
      }

   }

   private FGBasis generateBasis() {
      int var1 = this.params.N;
      int var2 = this.params.q;
      int var3 = this.params.d;
      int var4 = this.params.d1;
      int var5 = this.params.d2;
      int var6 = this.params.d3;
      int var7 = this.params.basisType;
      int var16 = 2 * var1 + 1;
      boolean var17 = this.params.primeCheck;

      while(true) {
         Object var8 = this.params.polyType == 0 ? DenseTernaryPolynomial.generateRandom(var1, var3 + 1, var3, CryptoServicesRegistrar.getSecureRandom()) : ProductFormPolynomial.generateRandom(var1, var4, var5, var6 + 1, var6, CryptoServicesRegistrar.getSecureRandom());
         IntegerPolynomial var9 = ((Polynomial)var8).toIntegerPolynomial();
         if (!var17 || !var9.resultant(var16).res.equals(BigInteger.ZERO)) {
            IntegerPolynomial var12 = var9.invertFq(var2);
            if (var12 != null) {
               Resultant var13 = var9.resultant();

               while(true) {
                  Object var10 = this.params.polyType == 0 ? DenseTernaryPolynomial.generateRandom(var1, var3 + 1, var3, CryptoServicesRegistrar.getSecureRandom()) : ProductFormPolynomial.generateRandom(var1, var4, var5, var6 + 1, var6, CryptoServicesRegistrar.getSecureRandom());
                  IntegerPolynomial var11 = ((Polynomial)var10).toIntegerPolynomial();
                  if ((!var17 || !var11.resultant(var16).res.equals(BigInteger.ZERO)) && var11.invertFq(var2) != null) {
                     Resultant var14 = var11.resultant();
                     BigIntEuclidean var15 = BigIntEuclidean.calculate(var13.res, var14.res);
                     if (var15.gcd.equals(BigInteger.ONE)) {
                        BigIntPolynomial var18 = (BigIntPolynomial)var13.rho.clone();
                        var18.mult(var15.x.multiply(BigInteger.valueOf((long)var2)));
                        BigIntPolynomial var19 = (BigIntPolynomial)var14.rho.clone();
                        var19.mult(var15.y.multiply(BigInteger.valueOf((long)(-var2))));
                        BigIntPolynomial var27;
                        if (this.params.keyGenAlg == 0) {
                           int[] var21 = new int[var1];
                           int[] var22 = new int[var1];
                           var21[0] = var9.coeffs[0];
                           var22[0] = var11.coeffs[0];

                           for(int var23 = 1; var23 < var1; ++var23) {
                              var21[var23] = var9.coeffs[var1 - var23];
                              var22[var23] = var11.coeffs[var1 - var23];
                           }

                           IntegerPolynomial var33 = new IntegerPolynomial(var21);
                           IntegerPolynomial var24 = new IntegerPolynomial(var22);
                           IntegerPolynomial var25 = ((Polynomial)var8).mult(var33);
                           var25.add(((Polynomial)var10).mult(var24));
                           Resultant var26 = var25.resultant();
                           BigIntPolynomial var20 = var33.mult(var19);
                           var20.add(var24.mult(var18));
                           var27 = var20.mult(var26.rho);
                           var27.div(var26.res);
                        } else {
                           int var28 = 0;

                           for(int var30 = 1; var30 < var1; var30 *= 10) {
                              ++var28;
                           }

                           BigDecimalPolynomial var31 = var13.rho.div(new BigDecimal(var13.res), var19.getMaxCoeffLength() + 1 + var28);
                           BigDecimalPolynomial var34 = var14.rho.div(new BigDecimal(var14.res), var18.getMaxCoeffLength() + 1 + var28);
                           BigDecimalPolynomial var36 = var31.mult(var19);
                           var36.add(var34.mult(var18));
                           var36.halve();
                           var27 = var36.round();
                        }

                        BigIntPolynomial var29 = (BigIntPolynomial)var19.clone();
                        var29.sub(((Polynomial)var8).mult(var27));
                        BigIntPolynomial var32 = (BigIntPolynomial)var18.clone();
                        var32.sub(((Polynomial)var10).mult(var27));
                        IntegerPolynomial var35 = new IntegerPolynomial(var29);
                        IntegerPolynomial var37 = new IntegerPolynomial(var32);
                        this.minimizeFG(var9, var11, var35, var37, var1);
                        Object var38;
                        IntegerPolynomial var39;
                        if (var7 == 0) {
                           var38 = var35;
                           var39 = ((Polynomial)var10).mult(var12, var2);
                        } else {
                           var38 = var10;
                           var39 = var35.mult(var12, var2);
                        }

                        var39.modPositive(var2);
                        return new FGBasis((Polynomial)var8, (Polynomial)var38, var39, var35, var37, this.params);
                     }
                  }
               }
            }
         }
      }
   }

   public NTRUSigningPrivateKeyParameters.Basis generateBoundedBasis() {
      FGBasis var1;
      do {
         var1 = this.generateBasis();
      } while(!var1.isNormOk());

      return var1;
   }

   private class BasisGenerationTask implements Callable {
      private BasisGenerationTask() {
      }

      public NTRUSigningPrivateKeyParameters.Basis call() throws Exception {
         return NTRUSigningKeyPairGenerator.this.generateBoundedBasis();
      }
   }

   public static class FGBasis extends NTRUSigningPrivateKeyParameters.Basis {
      public IntegerPolynomial F;
      public IntegerPolynomial G;

      FGBasis(Polynomial var1, Polynomial var2, IntegerPolynomial var3, IntegerPolynomial var4, IntegerPolynomial var5, NTRUSigningKeyGenerationParameters var6) {
         super(var1, var2, var3, var6);
         this.F = var4;
         this.G = var5;
      }

      boolean isNormOk() {
         double var1 = this.params.keyNormBoundSq;
         int var3 = this.params.q;
         return (double)this.F.centeredNormSq(var3) < var1 && (double)this.G.centeredNormSq(var3) < var1;
      }
   }
}
