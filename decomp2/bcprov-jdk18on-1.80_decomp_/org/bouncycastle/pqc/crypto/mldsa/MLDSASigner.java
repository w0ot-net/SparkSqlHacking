package org.bouncycastle.pqc.crypto.mldsa;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.crypto.params.ParametersWithContext;
import org.bouncycastle.crypto.params.ParametersWithRandom;

public class MLDSASigner implements Signer {
   private static final byte[] EMPTY_CONTEXT = new byte[0];
   private MLDSAPublicKeyParameters pubKey;
   private MLDSAPrivateKeyParameters privKey;
   private SecureRandom random;
   private MLDSAEngine engine;
   private SHAKEDigest msgDigest;

   public void init(boolean var1, CipherParameters var2) {
      byte[] var3 = EMPTY_CONTEXT;
      if (var2 instanceof ParametersWithContext) {
         ParametersWithContext var4 = (ParametersWithContext)var2;
         var3 = var4.getContext();
         var2 = var4.getParameters();
         if (var3.length > 255) {
            throw new IllegalArgumentException("context too long");
         }
      }

      MLDSAParameters var6;
      if (var1) {
         this.pubKey = null;
         if (var2 instanceof ParametersWithRandom) {
            ParametersWithRandom var5 = (ParametersWithRandom)var2;
            this.privKey = (MLDSAPrivateKeyParameters)var5.getParameters();
            this.random = var5.getRandom();
         } else {
            this.privKey = (MLDSAPrivateKeyParameters)var2;
            this.random = null;
         }

         var6 = this.privKey.getParameters();
         this.engine = var6.getEngine(this.random);
         this.engine.initSign(this.privKey.tr, false, var3);
      } else {
         this.pubKey = (MLDSAPublicKeyParameters)var2;
         this.privKey = null;
         this.random = null;
         var6 = this.pubKey.getParameters();
         this.engine = var6.getEngine((SecureRandom)null);
         this.engine.initVerify(this.pubKey.rho, this.pubKey.t1, false, var3);
      }

      if (var6.isPreHash()) {
         throw new IllegalArgumentException("\"pure\" ml-dsa must use non pre-hash parameters");
      } else {
         this.reset();
      }
   }

   public void update(byte var1) {
      this.msgDigest.update(var1);
   }

   public void update(byte[] var1, int var2, int var3) {
      this.msgDigest.update(var1, var2, var3);
   }

   public byte[] generateSignature() throws CryptoException, DataLengthException {
      byte[] var1 = new byte[32];
      if (this.random != null) {
         this.random.nextBytes(var1);
      }

      byte[] var2 = this.engine.generateSignature(this.msgDigest, this.privKey.rho, this.privKey.k, this.privKey.t0, this.privKey.s1, this.privKey.s2, var1);
      this.reset();
      return var2;
   }

   public boolean verifySignature(byte[] var1) {
      boolean var2 = this.engine.verifyInternal(var1, var1.length, this.msgDigest, this.pubKey.rho, this.pubKey.t1);
      this.reset();
      return var2;
   }

   public void reset() {
      this.msgDigest = this.engine.getShake256Digest();
   }

   protected byte[] internalGenerateSignature(byte[] var1, byte[] var2) {
      MLDSAEngine var3 = this.privKey.getParameters().getEngine(this.random);
      var3.initSign(this.privKey.tr, false, (byte[])null);
      return var3.signInternal(var1, var1.length, this.privKey.rho, this.privKey.k, this.privKey.t0, this.privKey.s1, this.privKey.s2, var2);
   }

   protected boolean internalVerifySignature(byte[] var1, byte[] var2) {
      MLDSAEngine var3 = this.pubKey.getParameters().getEngine(this.random);
      var3.initVerify(this.pubKey.rho, this.pubKey.t1, false, (byte[])null);
      SHAKEDigest var4 = var3.getShake256Digest();
      var4.update(var1, 0, var1.length);
      return var3.verifyInternal(var2, var2.length, var4, this.pubKey.rho, this.pubKey.t1);
   }
}
