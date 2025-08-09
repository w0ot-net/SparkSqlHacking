package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.CheckedFunction;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.SecureRequest;
import io.jsonwebtoken.security.SignatureAlgorithm;
import io.jsonwebtoken.security.VerifySecureDigestRequest;
import java.io.InputStream;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.text.MessageFormat;

abstract class AbstractSignatureAlgorithm extends AbstractSecureDigestAlgorithm implements SignatureAlgorithm {
   private static final String KEY_TYPE_MSG_PATTERN = "{0} {1} keys must be {2}s (implement {3}). Provided key type: {4}.";

   AbstractSignatureAlgorithm(String id, String jcaName) {
      super(id, jcaName);
   }

   protected void validateKey(Key key, boolean signing) {
      Class<?> type = signing ? PrivateKey.class : PublicKey.class;
      if (!type.isInstance(key)) {
         String msg = MessageFormat.format("{0} {1} keys must be {2}s (implement {3}). Provided key type: {4}.", this.getId(), keyType(signing), type.getSimpleName(), type.getName(), key.getClass().getName());
         throw new InvalidKeyException(msg);
      }
   }

   protected final byte[] sign(Signature sig, InputStream payload) throws Exception {
      byte[] buf = new byte[2048];
      int len = 0;

      while(len != -1) {
         len = payload.read(buf);
         if (len > 0) {
            sig.update(buf, 0, len);
         }
      }

      return sig.sign();
   }

   protected byte[] doDigest(final SecureRequest request) {
      return (byte[])this.jca(request).withSignature(new CheckedFunction() {
         public byte[] apply(Signature sig) throws Exception {
            sig.initSign((PrivateKey)request.getKey());
            return AbstractSignatureAlgorithm.this.sign(sig, (InputStream)request.getPayload());
         }
      });
   }

   protected boolean verify(Signature sig, InputStream payload, byte[] digest) throws Exception {
      byte[] buf = new byte[1024];
      int len = 0;

      while(len != -1) {
         len = payload.read(buf);
         if (len > 0) {
            sig.update(buf, 0, len);
         }
      }

      return sig.verify(digest);
   }

   protected boolean doVerify(final VerifySecureDigestRequest request) {
      return (Boolean)this.jca(request).withSignature(new CheckedFunction() {
         public Boolean apply(Signature sig) throws Exception {
            sig.initVerify((PublicKey)request.getKey());
            return AbstractSignatureAlgorithm.this.verify(sig, (InputStream)request.getPayload(), request.getDigest());
         }
      });
   }
}
