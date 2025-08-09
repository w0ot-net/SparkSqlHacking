package io.jsonwebtoken;

import java.util.Map;

public interface Header extends Map {
   /** @deprecated */
   @Deprecated
   String JWT_TYPE = "JWT";
   /** @deprecated */
   @Deprecated
   String TYPE = "typ";
   /** @deprecated */
   @Deprecated
   String CONTENT_TYPE = "cty";
   /** @deprecated */
   @Deprecated
   String ALGORITHM = "alg";
   /** @deprecated */
   @Deprecated
   String COMPRESSION_ALGORITHM = "zip";
   /** @deprecated */
   @Deprecated
   String DEPRECATED_COMPRESSION_ALGORITHM = "calg";

   String getType();

   String getContentType();

   String getAlgorithm();

   String getCompressionAlgorithm();
}
