package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.KeyException;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import io.jsonwebtoken.security.SecureRequest;
import io.jsonwebtoken.security.SecurityException;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.VerifySecureDigestRequest;
import java.io.InputStream;
import java.security.Key;

abstract class AbstractSecureDigestAlgorithm extends CryptoAlgorithm implements SecureDigestAlgorithm {
   AbstractSecureDigestAlgorithm(String id, String jcaName) {
      super(id, jcaName);
   }

   protected static String keyType(boolean signing) {
      return signing ? "signing" : "verification";
   }

   protected abstract void validateKey(Key var1, boolean var2);

   public final byte[] digest(SecureRequest request) throws SecurityException {
      Assert.notNull(request, "Request cannot be null.");
      S key = (S)((Key)Assert.notNull(request.getKey(), "Signing key cannot be null."));
      Assert.notNull(request.getPayload(), "Request content cannot be null.");

      try {
         this.validateKey(key, true);
         return this.doDigest(request);
      } catch (KeyException | SignatureException e) {
         throw e;
      } catch (Exception e) {
         String msg = "Unable to compute " + this.getId() + " signature with JCA algorithm '" + this.getJcaName() + "' " + "using key {" + KeysBridge.toString(key) + "}: " + e.getMessage();
         throw new SignatureException(msg, e);
      }
   }

   protected abstract byte[] doDigest(SecureRequest var1) throws Exception;

   public final boolean verify(VerifySecureDigestRequest request) throws SecurityException {
      Assert.notNull(request, "Request cannot be null.");
      V key = (V)((Key)Assert.notNull(request.getKey(), "Verification key cannot be null."));
      Assert.notNull(request.getPayload(), "Request content cannot be null or empty.");
      Assert.notEmpty(request.getDigest(), "Request signature byte array cannot be null or empty.");

      try {
         this.validateKey(key, false);
         return this.doVerify(request);
      } catch (KeyException | SignatureException e) {
         throw e;
      } catch (Exception e) {
         String msg = "Unable to verify " + this.getId() + " signature with JCA algorithm '" + this.getJcaName() + "' " + "using key {" + KeysBridge.toString(key) + "}: " + e.getMessage();
         throw new SignatureException(msg, e);
      }
   }

   protected abstract boolean doVerify(VerifySecureDigestRequest var1);
}
