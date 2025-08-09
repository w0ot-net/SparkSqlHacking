package io.jsonwebtoken.impl;

import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.lang.Parameters;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.Registry;
import java.util.Map;
import java.util.Set;

public class DefaultJwsHeader extends DefaultProtectedHeader implements JwsHeader {
   static final Parameter B64 = (Parameter)Parameters.builder(Boolean.class).setId("b64").setName("Base64url-Encode Payload").build();
   static final Registry PARAMS;

   public DefaultJwsHeader(Map map) {
      super(PARAMS, map);
   }

   public String getName() {
      return "JWS header";
   }

   public boolean isPayloadEncoded() {
      Set<String> crit = Collections.nullSafe(this.getCritical());
      Boolean b64 = (Boolean)this.get(B64);
      return b64 == null || b64 || !crit.contains(B64.getId());
   }

   static {
      PARAMS = Parameters.registry(DefaultProtectedHeader.PARAMS, B64);
   }
}
