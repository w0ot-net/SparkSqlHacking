package io.vertx.ext.auth;

import io.vertx.codegen.annotations.Nullable;
import java.util.HashMap;
import java.util.Map;

/** @deprecated */
@Deprecated
public final class HashString {
   private final String id;
   private Map params;
   private String salt;
   private String hash;

   public HashString(String id, Map params, String salt) {
      this.id = id;
      this.params = params;
      if (salt != null) {
         this.salt = salt.replace('_', '/').replace('-', '+');
      }

   }

   public HashString(String encoded) {
      if (encoded.length() > 1 && encoded.charAt(0) != '$') {
         encoded = encoded.replaceAll("\\{", "\\$\\{");
         encoded = encoded.replaceAll("\\}", "\\}\\$");
         if (encoded.length() > 1 && encoded.charAt(0) != '$') {
            encoded = "$$" + encoded;
         }
      }

      String[] parts = encoded.split("\\$");
      if (parts.length < 2) {
         throw new IllegalStateException("Not enough segments: " + encoded);
      } else {
         switch (parts.length) {
            case 2:
               this.id = parts[1];
               break;
            case 3:
               this.id = parts[1];
               this.hash = parts[2];
               break;
            case 4:
               this.id = parts[1];
               this.salt = parts[2];
               this.hash = parts[3];
               break;
            case 5:
            default:
               this.id = parts[1];
               this.params = new HashMap();

               for(String kv : parts[2].split(",")) {
                  int eq = kv.indexOf(61);
                  if (eq > 0) {
                     this.params.put(kv.substring(0, eq), kv.substring(eq + 1));
                  }
               }

               this.salt = parts[3];
               this.hash = parts[4];
         }

      }
   }

   public String id() {
      return this.id;
   }

   public @Nullable String param(String param) {
      return this.params == null ? null : (String)this.params.get(param);
   }

   public Map params() {
      return this.params;
   }

   public String salt() {
      return this.salt;
   }

   public String hash() {
      return this.hash;
   }

   public static String encode(HashingAlgorithm algorithm, Map params, String salt, String hash) {
      StringBuilder sb = new StringBuilder();
      if (algorithm.needsSeparator()) {
         sb.append('$');
      }

      sb.append(algorithm.id());
      if (params != null) {
         if (algorithm.needsSeparator()) {
            sb.append('$');
         }

         boolean notEmpty = false;

         for(String key : algorithm.params()) {
            String value = (String)params.get(key);
            if (value != null) {
               if (notEmpty) {
                  sb.append(',');
               }

               sb.append(key);
               sb.append('=');
               sb.append((String)params.get(key));
               notEmpty = true;
            }
         }
      }

      if (salt != null) {
         if (algorithm.needsSeparator()) {
            sb.append('$');
         }

         sb.append(salt);
      }

      if (hash != null) {
         if (algorithm.needsSeparator()) {
            sb.append('$');
         }

         sb.append(hash);
      }

      return sb.toString();
   }

   public String toString() {
      return "id=" + this.id() + ",params=" + this.params() + ",salt=" + this.salt() + ",hash=" + this.hash();
   }
}
