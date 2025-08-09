package org.apache.hive.service.auth;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public enum SaslQOP {
   AUTH("auth"),
   AUTH_INT("auth-int"),
   AUTH_CONF("auth-conf");

   public final String saslQop;
   private static final Map STR_TO_ENUM = new HashMap();

   private SaslQOP(String saslQop) {
      this.saslQop = saslQop;
   }

   public String toString() {
      return this.saslQop;
   }

   public static SaslQOP fromString(String str) {
      if (str != null) {
         str = str.toLowerCase(Locale.ROOT);
      }

      SaslQOP saslQOP = (SaslQOP)STR_TO_ENUM.get(str);
      if (saslQOP == null) {
         throw new IllegalArgumentException("Unknown auth type: " + str + " Allowed values are: " + String.valueOf(STR_TO_ENUM.keySet()));
      } else {
         return saslQOP;
      }
   }

   // $FF: synthetic method
   private static SaslQOP[] $values() {
      return new SaslQOP[]{AUTH, AUTH_INT, AUTH_CONF};
   }

   static {
      for(SaslQOP saslQop : values()) {
         STR_TO_ENUM.put(saslQop.toString(), saslQop);
      }

   }
}
