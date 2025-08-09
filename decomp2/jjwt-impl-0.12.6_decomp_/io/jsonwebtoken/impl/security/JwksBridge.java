package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.io.NamedSerializer;
import io.jsonwebtoken.impl.lang.Services;
import io.jsonwebtoken.io.Serializer;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.Jwk;
import java.io.ByteArrayOutputStream;
import java.util.Map;

public final class JwksBridge {
   private JwksBridge() {
   }

   public static String UNSAFE_JSON(Jwk jwk) {
      Serializer<Map<String, ?>> serializer = (Serializer)Services.get(Serializer.class);
      Assert.stateNotNull(serializer, "Serializer lookup failed. Ensure JSON impl .jar is in the runtime classpath.");
      NamedSerializer ser = new NamedSerializer("JWK", serializer);
      ByteArrayOutputStream out = new ByteArrayOutputStream(512);
      ser.serialize(jwk, out);
      return Strings.utf8(out.toByteArray());
   }
}
