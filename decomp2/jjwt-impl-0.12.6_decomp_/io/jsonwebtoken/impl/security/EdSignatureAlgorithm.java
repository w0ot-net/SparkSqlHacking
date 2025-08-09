package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.KeyPairBuilder;
import io.jsonwebtoken.security.Request;
import io.jsonwebtoken.security.SecureRequest;
import io.jsonwebtoken.security.VerifyDigestRequest;
import java.security.Key;
import java.security.PrivateKey;

final class EdSignatureAlgorithm extends AbstractSignatureAlgorithm {
   private static final String ID = "EdDSA";
   private final EdwardsCurve preferredCurve;
   static final EdSignatureAlgorithm INSTANCE = new EdSignatureAlgorithm();

   static boolean isSigningKey(PrivateKey key) {
      EdwardsCurve curve = EdwardsCurve.findByKey(key);
      return curve != null && curve.isSignatureCurve();
   }

   private EdSignatureAlgorithm() {
      super("EdDSA", "EdDSA");
      this.preferredCurve = EdwardsCurve.Ed448;
      Assert.isTrue(this.preferredCurve.isSignatureCurve(), "Must be signature curve, not key agreement curve.");
   }

   protected String getJcaName(Request request) {
      SecureRequest<?, ?> req = (SecureRequest)Assert.isInstanceOf(SecureRequest.class, request, "SecureRequests are required.");
      Key key = (Key)Assert.notNull(req.getKey(), "Request key cannot be null.");
      String jcaName = this.getJcaName();
      if (!(request instanceof VerifyDigestRequest)) {
         jcaName = EdwardsCurve.forKey(key).getJcaName();
      }

      return jcaName;
   }

   public KeyPairBuilder keyPair() {
      return this.preferredCurve.keyPair();
   }

   protected void validateKey(Key key, boolean signing) {
      super.validateKey(key, signing);
      EdwardsCurve curve = EdwardsCurve.forKey(key);
      if (!curve.isSignatureCurve()) {
         String msg = curve.getId() + " keys may not be used with " + this.getId() + " digital signatures per " + "https://www.rfc-editor.org/rfc/rfc8037.html#section-3.2";
         throw new InvalidKeyException(msg);
      }
   }
}
