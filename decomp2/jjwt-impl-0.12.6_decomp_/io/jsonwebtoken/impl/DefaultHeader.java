package io.jsonwebtoken.impl;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.impl.lang.CompactMediaTypeIdConverter;
import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.lang.Parameters;
import io.jsonwebtoken.lang.Registry;
import io.jsonwebtoken.lang.Strings;
import java.util.Map;

public class DefaultHeader extends ParameterMap implements Header {
   static final Parameter TYPE = Parameters.string("typ", "Type");
   static final Parameter CONTENT_TYPE;
   static final Parameter ALGORITHM;
   static final Parameter COMPRESSION_ALGORITHM;
   /** @deprecated */
   @Deprecated
   static final Parameter DEPRECATED_COMPRESSION_ALGORITHM;
   static final Registry PARAMS;

   public DefaultHeader(Map values) {
      super(PARAMS, values);
   }

   protected DefaultHeader(Registry registry, Map values) {
      super(registry, values);
   }

   public String getName() {
      return "JWT header";
   }

   public String getType() {
      return (String)this.get(TYPE);
   }

   public String getContentType() {
      return (String)this.get(CONTENT_TYPE);
   }

   public String getAlgorithm() {
      return (String)this.get(ALGORITHM);
   }

   public String getCompressionAlgorithm() {
      String s = (String)this.get(COMPRESSION_ALGORITHM);
      if (!Strings.hasText(s)) {
         s = (String)this.get(DEPRECATED_COMPRESSION_ALGORITHM);
      }

      return s;
   }

   static {
      CONTENT_TYPE = (Parameter)Parameters.builder(String.class).setId("cty").setName("Content Type").setConverter(CompactMediaTypeIdConverter.INSTANCE).build();
      ALGORITHM = Parameters.string("alg", "Algorithm");
      COMPRESSION_ALGORITHM = Parameters.string("zip", "Compression Algorithm");
      DEPRECATED_COMPRESSION_ALGORITHM = Parameters.string("calg", "Deprecated Compression Algorithm");
      PARAMS = Parameters.registry(TYPE, CONTENT_TYPE, ALGORITHM, COMPRESSION_ALGORITHM, DEPRECATED_COMPRESSION_ALGORITHM);
   }
}
