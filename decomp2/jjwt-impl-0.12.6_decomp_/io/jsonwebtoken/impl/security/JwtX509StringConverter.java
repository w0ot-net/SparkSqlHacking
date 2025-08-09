package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.Bytes;
import io.jsonwebtoken.impl.lang.Converter;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.SecurityException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

public class JwtX509StringConverter implements Converter {
   public static final JwtX509StringConverter INSTANCE = new JwtX509StringConverter();

   public String applyTo(X509Certificate cert) {
      Assert.notNull(cert, "X509Certificate cannot be null.");

      byte[] der;
      try {
         der = cert.getEncoded();
      } catch (CertificateEncodingException e) {
         String msg = "Unable to access X509Certificate encoded bytes necessary to perform DER Base64-encoding. Certificate: {" + cert + "}. Cause: " + e.getMessage();
         throw new IllegalArgumentException(msg, e);
      }

      if (Bytes.isEmpty(der)) {
         String msg = "X509Certificate encoded bytes cannot be null or empty.  Certificate: {" + cert + "}.";
         throw new IllegalArgumentException(msg);
      } else {
         return (String)Encoders.BASE64.encode(der);
      }
   }

   protected X509Certificate toCert(byte[] der) throws SecurityException {
      return (new JcaTemplate("X.509")).generateX509Certificate(der);
   }

   public X509Certificate applyFrom(CharSequence s) {
      Assert.hasText(s, "X.509 Certificate encoded string cannot be null or empty.");

      try {
         byte[] der = (byte[])Decoders.BASE64.decode(s);
         return this.toCert(der);
      } catch (Exception e) {
         String msg = "Unable to convert Base64 String '" + s + "' to X509Certificate instance. Cause: " + e.getMessage();
         throw new IllegalArgumentException(msg, e);
      }
   }
}
