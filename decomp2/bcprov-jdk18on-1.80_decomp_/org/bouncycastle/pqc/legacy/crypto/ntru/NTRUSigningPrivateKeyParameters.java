package org.bouncycastle.pqc.legacy.crypto.ntru;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.DenseTernaryPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.IntegerPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.Polynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.ProductFormPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.SparseTernaryPolynomial;

public class NTRUSigningPrivateKeyParameters extends AsymmetricKeyParameter {
   private List bases;
   private NTRUSigningPublicKeyParameters publicKey;

   public NTRUSigningPrivateKeyParameters(byte[] var1, NTRUSigningKeyGenerationParameters var2) throws IOException {
      this((InputStream)(new ByteArrayInputStream(var1)), (NTRUSigningKeyGenerationParameters)var2);
   }

   public NTRUSigningPrivateKeyParameters(InputStream var1, NTRUSigningKeyGenerationParameters var2) throws IOException {
      super(true);
      this.bases = new ArrayList();

      for(int var3 = 0; var3 <= var2.B; ++var3) {
         this.add(new Basis(var1, var2, var3 != 0));
      }

      this.publicKey = new NTRUSigningPublicKeyParameters(var1, var2.getSigningParameters());
   }

   public NTRUSigningPrivateKeyParameters(List var1, NTRUSigningPublicKeyParameters var2) {
      super(true);
      this.bases = new ArrayList(var1);
      this.publicKey = var2;
   }

   private void add(Basis var1) {
      this.bases.add(var1);
   }

   public Basis getBasis(int var1) {
      return (Basis)this.bases.get(var1);
   }

   public NTRUSigningPublicKeyParameters getPublicKey() {
      return this.publicKey;
   }

   public byte[] getEncoded() throws IOException {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();

      for(int var2 = 0; var2 < this.bases.size(); ++var2) {
         ((Basis)this.bases.get(var2)).encode(var1, var2 != 0);
      }

      var1.write(this.publicKey.getEncoded());
      return var1.toByteArray();
   }

   public void writeTo(OutputStream var1) throws IOException {
      var1.write(this.getEncoded());
   }

   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1;
      if (this.bases == null) {
         return var1;
      } else {
         var1 += this.bases.hashCode();

         for(Basis var3 : this.bases) {
            var1 += var3.hashCode();
         }

         return var1;
      }
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         NTRUSigningPrivateKeyParameters var2 = (NTRUSigningPrivateKeyParameters)var1;
         if (this.bases == null != (var2.bases == null)) {
            return false;
         } else if (this.bases == null) {
            return true;
         } else if (this.bases.size() != var2.bases.size()) {
            return false;
         } else {
            for(int var3 = 0; var3 < this.bases.size(); ++var3) {
               Basis var4 = (Basis)this.bases.get(var3);
               Basis var5 = (Basis)var2.bases.get(var3);
               if (!var4.f.equals(var5.f)) {
                  return false;
               }

               if (!var4.fPrime.equals(var5.fPrime)) {
                  return false;
               }

               if (var3 != 0 && !var4.h.equals(var5.h)) {
                  return false;
               }

               if (!var4.params.equals(var5.params)) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public static class Basis {
      public Polynomial f;
      public Polynomial fPrime;
      public IntegerPolynomial h;
      NTRUSigningKeyGenerationParameters params;

      protected Basis(Polynomial var1, Polynomial var2, IntegerPolynomial var3, NTRUSigningKeyGenerationParameters var4) {
         this.f = var1;
         this.fPrime = var2;
         this.h = var3;
         this.params = var4;
      }

      Basis(InputStream var1, NTRUSigningKeyGenerationParameters var2, boolean var3) throws IOException {
         int var4 = var2.N;
         int var5 = var2.q;
         int var6 = var2.d1;
         int var7 = var2.d2;
         int var8 = var2.d3;
         boolean var9 = var2.sparse;
         this.params = var2;
         if (var2.polyType == 1) {
            this.f = ProductFormPolynomial.fromBinary(var1, var4, var6, var7, var8 + 1, var8);
         } else {
            IntegerPolynomial var10 = IntegerPolynomial.fromBinary3Tight(var1, var4);
            this.f = (Polynomial)(var9 ? new SparseTernaryPolynomial(var10) : new DenseTernaryPolynomial(var10));
         }

         if (var2.basisType == 0) {
            IntegerPolynomial var12 = IntegerPolynomial.fromBinary(var1, var4, var5);

            for(int var11 = 0; var11 < var12.coeffs.length; ++var11) {
               int[] var10000 = var12.coeffs;
               var10000[var11] -= var5 / 2;
            }

            this.fPrime = var12;
         } else if (var2.polyType == 1) {
            this.fPrime = ProductFormPolynomial.fromBinary(var1, var4, var6, var7, var8 + 1, var8);
         } else {
            this.fPrime = IntegerPolynomial.fromBinary3Tight(var1, var4);
         }

         if (var3) {
            this.h = IntegerPolynomial.fromBinary(var1, var4, var5);
         }

      }

      void encode(OutputStream var1, boolean var2) throws IOException {
         int var3 = this.params.q;
         var1.write(this.getEncoded(this.f));
         if (this.params.basisType == 0) {
            IntegerPolynomial var4 = this.fPrime.toIntegerPolynomial();

            for(int var5 = 0; var5 < var4.coeffs.length; ++var5) {
               int[] var10000 = var4.coeffs;
               var10000[var5] += var3 / 2;
            }

            var1.write(var4.toBinary(var3));
         } else {
            var1.write(this.getEncoded(this.fPrime));
         }

         if (var2) {
            var1.write(this.h.toBinary(var3));
         }

      }

      private byte[] getEncoded(Polynomial var1) {
         return var1 instanceof ProductFormPolynomial ? ((ProductFormPolynomial)var1).toBinary() : var1.toIntegerPolynomial().toBinary3Tight();
      }

      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + (this.f == null ? 0 : this.f.hashCode());
         var1 = 31 * var1 + (this.fPrime == null ? 0 : this.fPrime.hashCode());
         var1 = 31 * var1 + (this.h == null ? 0 : this.h.hashCode());
         var1 = 31 * var1 + (this.params == null ? 0 : this.params.hashCode());
         return var1;
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (var1 == null) {
            return false;
         } else if (!(var1 instanceof Basis)) {
            return false;
         } else {
            Basis var2 = (Basis)var1;
            if (this.f == null) {
               if (var2.f != null) {
                  return false;
               }
            } else if (!this.f.equals(var2.f)) {
               return false;
            }

            if (this.fPrime == null) {
               if (var2.fPrime != null) {
                  return false;
               }
            } else if (!this.fPrime.equals(var2.fPrime)) {
               return false;
            }

            if (this.h == null) {
               if (var2.h != null) {
                  return false;
               }
            } else if (!this.h.equals(var2.h)) {
               return false;
            }

            if (this.params == null) {
               if (var2.params != null) {
                  return false;
               }
            } else if (!this.params.equals(var2.params)) {
               return false;
            }

            return true;
         }
      }
   }
}
