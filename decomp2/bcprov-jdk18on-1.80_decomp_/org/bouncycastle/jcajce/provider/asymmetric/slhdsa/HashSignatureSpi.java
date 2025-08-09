package org.bouncycastle.jcajce.provider.asymmetric.slhdsa;

import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.jcajce.provider.asymmetric.util.BaseDeterministicOrRandomSignature;
import org.bouncycastle.pqc.crypto.slhdsa.HashSLHDSASigner;
import org.bouncycastle.pqc.crypto.slhdsa.SLHDSAPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.slhdsa.SLHDSAPublicKeyParameters;

public class HashSignatureSpi extends BaseDeterministicOrRandomSignature {
   private final HashSLHDSASigner signer;

   protected HashSignatureSpi(HashSLHDSASigner var1) {
      super("HASH-SLH-DSA");
      this.signer = var1;
   }

   protected void verifyInit(PublicKey var1) throws InvalidKeyException {
      if (var1 instanceof BCSLHDSAPublicKey) {
         BCSLHDSAPublicKey var2 = (BCSLHDSAPublicKey)var1;
         this.keyParams = var2.getKeyParams();
      } else {
         throw new InvalidKeyException("unknown public key passed to SLH-DSA");
      }
   }

   protected void signInit(PrivateKey var1, SecureRandom var2) throws InvalidKeyException {
      this.appRandom = var2;
      if (var1 instanceof BCSLHDSAPrivateKey) {
         BCSLHDSAPrivateKey var3 = (BCSLHDSAPrivateKey)var1;
         this.keyParams = var3.getKeyParams();
      } else {
         throw new InvalidKeyException("unknown private key passed to SLH-DSA");
      }
   }

   protected void updateEngine(byte var1) throws SignatureException {
      this.signer.update(var1);
   }

   protected void updateEngine(byte[] var1, int var2, int var3) throws SignatureException {
      this.signer.update(var1, var2, var3);
   }

   protected byte[] engineSign() throws SignatureException {
      AsymmetricKeyParameter var1 = this.keyParams;
      if (!(var1 instanceof SLHDSAPrivateKeyParameters)) {
         throw new SignatureException("engine initialized for verification");
      } else {
         byte[] var3;
         try {
            byte[] var2 = this.signer.generateSignature();
            var3 = var2;
         } catch (Exception var7) {
            throw new SignatureException(var7.toString());
         } finally {
            this.isInitState = true;
         }

         return var3;
      }
   }

   protected boolean engineVerify(byte[] var1) throws SignatureException {
      AsymmetricKeyParameter var2 = this.keyParams;
      if (!(var2 instanceof SLHDSAPublicKeyParameters)) {
         throw new SignatureException("engine initialized for signing");
      } else {
         boolean var3;
         try {
            var3 = this.signer.verifySignature(var1);
         } finally {
            this.isInitState = true;
         }

         return var3;
      }
   }

   protected void reInitialize(boolean var1, CipherParameters var2) {
      this.signer.init(var1, var2);
   }

   public static class Direct extends HashSignatureSpi {
      public Direct() {
         super(new HashSLHDSASigner());
      }
   }
}
