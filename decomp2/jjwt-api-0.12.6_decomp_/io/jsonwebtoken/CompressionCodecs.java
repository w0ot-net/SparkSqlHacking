package io.jsonwebtoken;

/** @deprecated */
@Deprecated
public final class CompressionCodecs {
   /** @deprecated */
   @Deprecated
   public static final CompressionCodec DEFLATE;
   /** @deprecated */
   @Deprecated
   public static final CompressionCodec GZIP;

   private CompressionCodecs() {
   }

   static {
      DEFLATE = (CompressionCodec)Jwts.ZIP.DEF;
      GZIP = (CompressionCodec)Jwts.ZIP.GZIP;
   }
}
