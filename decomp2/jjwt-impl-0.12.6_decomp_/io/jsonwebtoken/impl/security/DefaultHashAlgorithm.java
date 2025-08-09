package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.CheckedFunction;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.HashAlgorithm;
import io.jsonwebtoken.security.Request;
import io.jsonwebtoken.security.VerifyDigestRequest;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Locale;

public final class DefaultHashAlgorithm extends CryptoAlgorithm implements HashAlgorithm {
   public static final HashAlgorithm SHA1 = new DefaultHashAlgorithm("sha-1");

   DefaultHashAlgorithm(String id) {
      super(id, id.toUpperCase(Locale.ENGLISH));
   }

   public byte[] digest(Request request) {
      Assert.notNull(request, "Request cannot be null.");
      final InputStream payload = (InputStream)Assert.notNull(request.getPayload(), "Request payload cannot be null.");
      return (byte[])this.jca(request).withMessageDigest(new CheckedFunction() {
         public byte[] apply(MessageDigest md) throws IOException {
            byte[] buf = new byte[1024];
            int len = 0;

            while(len != -1) {
               len = payload.read(buf);
               if (len > 0) {
                  md.update(buf, 0, len);
               }
            }

            return md.digest();
         }
      });
   }

   public boolean verify(VerifyDigestRequest request) {
      Assert.notNull(request, "VerifyDigestRequest cannot be null.");
      byte[] digest = (byte[])Assert.notNull(request.getDigest(), "Digest cannot be null.");
      byte[] computed = this.digest(request);
      return MessageDigest.isEqual(computed, digest);
   }
}
