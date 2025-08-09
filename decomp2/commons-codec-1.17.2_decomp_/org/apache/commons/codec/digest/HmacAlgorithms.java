package org.apache.commons.codec.digest;

public enum HmacAlgorithms {
   HMAC_MD5("HmacMD5"),
   HMAC_SHA_1("HmacSHA1"),
   HMAC_SHA_224("HmacSHA224"),
   HMAC_SHA_256("HmacSHA256"),
   HMAC_SHA_384("HmacSHA384"),
   HMAC_SHA_512("HmacSHA512");

   private final String name;

   private HmacAlgorithms(final String algorithm) {
      this.name = algorithm;
   }

   public String getName() {
      return this.name;
   }

   public String toString() {
      return this.name;
   }

   // $FF: synthetic method
   private static HmacAlgorithms[] $values() {
      return new HmacAlgorithms[]{HMAC_MD5, HMAC_SHA_1, HMAC_SHA_224, HMAC_SHA_256, HMAC_SHA_384, HMAC_SHA_512};
   }
}
