package org.apache.spark.network.crypto;

import com.google.crypto.tink.subtle.Hex;
import com.google.crypto.tink.subtle.Hkdf;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import javax.crypto.spec.SecretKeySpec;
import org.sparkproject.guava.annotations.VisibleForTesting;

class TransportCipherUtil {
   @VisibleForTesting
   static String getKeyId(SecretKeySpec key) throws GeneralSecurityException {
      byte[] keyIdBytes = Hkdf.computeHkdf("HmacSha256", key.getEncoded(), (byte[])null, "keyID".getBytes(StandardCharsets.UTF_8), 32);
      return Hex.encode(keyIdBytes);
   }
}
