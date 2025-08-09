package io.vertx.ext.web.client.impl.cache;

import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpHeaders;

public class UserAgent {
   private final NormalizedType type;

   static UserAgent parse(MultiMap headers) {
      String agent = headers.get(HttpHeaders.USER_AGENT);
      return new UserAgent(parseHeader(agent));
   }

   private UserAgent(NormalizedType type) {
      this.type = type;
   }

   public String normalize() {
      return this.type.name();
   }

   public NormalizedType getType() {
      return this.type;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         UserAgent userAgent = (UserAgent)o;
         return this.type.equals(userAgent.type);
      } else {
         return false;
      }
   }

   private static NormalizedType parseHeader(String string) {
      if (string == null) {
         return UserAgent.NormalizedType.DESKTOP;
      } else {
         return string.contains("Mobile") ? UserAgent.NormalizedType.MOBILE : UserAgent.NormalizedType.DESKTOP;
      }
   }

   static enum NormalizedType {
      MOBILE,
      DESKTOP;
   }
}
