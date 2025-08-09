package io.fabric8.kubernetes.api.model.authentication;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class TokenRequestStatusFluent extends BaseFluent {
   private String expirationTimestamp;
   private String token;
   private Map additionalProperties;

   public TokenRequestStatusFluent() {
   }

   public TokenRequestStatusFluent(TokenRequestStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(TokenRequestStatus instance) {
      instance = instance != null ? instance : new TokenRequestStatus();
      if (instance != null) {
         this.withExpirationTimestamp(instance.getExpirationTimestamp());
         this.withToken(instance.getToken());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getExpirationTimestamp() {
      return this.expirationTimestamp;
   }

   public TokenRequestStatusFluent withExpirationTimestamp(String expirationTimestamp) {
      this.expirationTimestamp = expirationTimestamp;
      return this;
   }

   public boolean hasExpirationTimestamp() {
      return this.expirationTimestamp != null;
   }

   public String getToken() {
      return this.token;
   }

   public TokenRequestStatusFluent withToken(String token) {
      this.token = token;
      return this;
   }

   public boolean hasToken() {
      return this.token != null;
   }

   public TokenRequestStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public TokenRequestStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public TokenRequestStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public TokenRequestStatusFluent removeFromAdditionalProperties(Map map) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.additionalProperties != null) {
                  this.additionalProperties.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   public TokenRequestStatusFluent withAdditionalProperties(Map additionalProperties) {
      if (additionalProperties == null) {
         this.additionalProperties = null;
      } else {
         this.additionalProperties = new LinkedHashMap(additionalProperties);
      }

      return this;
   }

   public boolean hasAdditionalProperties() {
      return this.additionalProperties != null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            TokenRequestStatusFluent that = (TokenRequestStatusFluent)o;
            if (!Objects.equals(this.expirationTimestamp, that.expirationTimestamp)) {
               return false;
            } else if (!Objects.equals(this.token, that.token)) {
               return false;
            } else {
               return Objects.equals(this.additionalProperties, that.additionalProperties);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.expirationTimestamp, this.token, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.expirationTimestamp != null) {
         sb.append("expirationTimestamp:");
         sb.append(this.expirationTimestamp + ",");
      }

      if (this.token != null) {
         sb.append("token:");
         sb.append(this.token + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
