package org.bouncycastle.pqc.jcajce.provider.rainbow;

import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.rainbow.RainbowParameters;
import org.bouncycastle.pqc.crypto.rainbow.RainbowPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.rainbow.RainbowSigner;
import org.bouncycastle.util.Strings;

public class SignatureSpi extends Signature {
   private ByteArrayOutputStream bOut;
   private RainbowSigner signer;
   private SecureRandom random;
   private RainbowParameters parameters;

   protected SignatureSpi(RainbowSigner var1) {
      super("RAINBOW");
      this.bOut = new ByteArrayOutputStream();
      this.signer = var1;
      this.parameters = null;
   }

   protected SignatureSpi(RainbowSigner var1, RainbowParameters var2) {
      super(Strings.toUpperCase(var2.getName()));
      this.parameters = var2;
      this.bOut = new ByteArrayOutputStream();
      this.signer = var1;
   }

   protected void engineInitVerify(PublicKey var1) throws InvalidKeyException {
      if (!(var1 instanceof BCRainbowPublicKey)) {
         try {
            var1 = new BCRainbowPublicKey(SubjectPublicKeyInfo.getInstance(((PublicKey)var1).getEncoded()));
         } catch (Exception var4) {
            throw new InvalidKeyException("unknown public key passed to Rainbow: " + var4.getMessage(), var4);
         }
      }

      BCRainbowPublicKey var2 = (BCRainbowPublicKey)var1;
      if (this.parameters != null) {
         String var3 = Strings.toUpperCase(this.parameters.getName());
         if (!var3.equals(var2.getAlgorithm())) {
            throw new InvalidKeyException("signature configured for " + var3);
         }
      }

      this.signer.init(false, var2.getKeyParams());
   }

   protected void engineInitSign(PrivateKey var1, SecureRandom var2) throws InvalidKeyException {
      this.random = var2;
      this.engineInitSign(var1);
   }

   protected void engineInitSign(PrivateKey var1) throws InvalidKeyException {
      if (var1 instanceof BCRainbowPrivateKey) {
         BCRainbowPrivateKey var2 = (BCRainbowPrivateKey)var1;
         RainbowPrivateKeyParameters var3 = var2.getKeyParams();
         if (this.parameters != null) {
            String var4 = Strings.toUpperCase(this.parameters.getName());
            if (!var4.equals(var2.getAlgorithm())) {
               throw new InvalidKeyException("signature configured for " + var4);
            }
         }

         if (this.random != null) {
            this.signer.init(true, new ParametersWithRandom(var3, this.random));
         } else {
            this.signer.init(true, var3);
         }

      } else {
         throw new InvalidKeyException("unknown private key passed to Rainbow");
      }
   }

   protected void engineUpdate(byte var1) throws SignatureException {
      this.bOut.write(var1);
   }

   protected void engineUpdate(byte[] var1, int var2, int var3) throws SignatureException {
      this.bOut.write(var1, var2, var3);
   }

   protected byte[] engineSign() throws SignatureException {
      try {
         byte[] var1 = this.bOut.toByteArray();
         this.bOut.reset();
         return this.signer.generateSignature(var1);
      } catch (Exception var2) {
         throw new SignatureException(var2.toString());
      }
   }

   protected boolean engineVerify(byte[] var1) throws SignatureException {
      byte[] var2 = this.bOut.toByteArray();
      this.bOut.reset();
      return this.signer.verifySignature(var2, var1);
   }

   protected void engineSetParameter(AlgorithmParameterSpec var1) {
      throw new UnsupportedOperationException("engineSetParameter unsupported");
   }

   /** @deprecated */
   protected void engineSetParameter(String var1, Object var2) {
      throw new UnsupportedOperationException("engineSetParameter unsupported");
   }

   /** @deprecated */
   protected Object engineGetParameter(String var1) {
      throw new UnsupportedOperationException("engineSetParameter unsupported");
   }

   public static class Base extends SignatureSpi {
      public Base() {
         super(new RainbowSigner());
      }
   }

   public static class RainbowIIIcircum extends SignatureSpi {
      public RainbowIIIcircum() {
         super(new RainbowSigner(), RainbowParameters.rainbowIIIcircumzenithal);
      }
   }

   public static class RainbowIIIclassic extends SignatureSpi {
      public RainbowIIIclassic() {
         super(new RainbowSigner(), RainbowParameters.rainbowIIIclassic);
      }
   }

   public static class RainbowIIIcomp extends SignatureSpi {
      public RainbowIIIcomp() {
         super(new RainbowSigner(), RainbowParameters.rainbowIIIcompressed);
      }
   }

   public static class RainbowVcircum extends SignatureSpi {
      public RainbowVcircum() {
         super(new RainbowSigner(), RainbowParameters.rainbowVcircumzenithal);
      }
   }

   public static class RainbowVclassic extends SignatureSpi {
      public RainbowVclassic() {
         super(new RainbowSigner(), RainbowParameters.rainbowVclassic);
      }
   }

   public static class RainbowVcomp extends SignatureSpi {
      public RainbowVcomp() {
         super(new RainbowSigner(), RainbowParameters.rainbowVcompressed);
      }
   }
}
