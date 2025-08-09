package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class SessionAffinityConfigFluent extends BaseFluent {
   private ClientIPConfigBuilder clientIP;
   private Map additionalProperties;

   public SessionAffinityConfigFluent() {
   }

   public SessionAffinityConfigFluent(SessionAffinityConfig instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(SessionAffinityConfig instance) {
      instance = instance != null ? instance : new SessionAffinityConfig();
      if (instance != null) {
         this.withClientIP(instance.getClientIP());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ClientIPConfig buildClientIP() {
      return this.clientIP != null ? this.clientIP.build() : null;
   }

   public SessionAffinityConfigFluent withClientIP(ClientIPConfig clientIP) {
      this._visitables.remove("clientIP");
      if (clientIP != null) {
         this.clientIP = new ClientIPConfigBuilder(clientIP);
         this._visitables.get("clientIP").add(this.clientIP);
      } else {
         this.clientIP = null;
         this._visitables.get("clientIP").remove(this.clientIP);
      }

      return this;
   }

   public boolean hasClientIP() {
      return this.clientIP != null;
   }

   public SessionAffinityConfigFluent withNewClientIP(Integer timeoutSeconds) {
      return this.withClientIP(new ClientIPConfig(timeoutSeconds));
   }

   public ClientIPNested withNewClientIP() {
      return new ClientIPNested((ClientIPConfig)null);
   }

   public ClientIPNested withNewClientIPLike(ClientIPConfig item) {
      return new ClientIPNested(item);
   }

   public ClientIPNested editClientIP() {
      return this.withNewClientIPLike((ClientIPConfig)Optional.ofNullable(this.buildClientIP()).orElse((Object)null));
   }

   public ClientIPNested editOrNewClientIP() {
      return this.withNewClientIPLike((ClientIPConfig)Optional.ofNullable(this.buildClientIP()).orElse((new ClientIPConfigBuilder()).build()));
   }

   public ClientIPNested editOrNewClientIPLike(ClientIPConfig item) {
      return this.withNewClientIPLike((ClientIPConfig)Optional.ofNullable(this.buildClientIP()).orElse(item));
   }

   public SessionAffinityConfigFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public SessionAffinityConfigFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public SessionAffinityConfigFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public SessionAffinityConfigFluent removeFromAdditionalProperties(Map map) {
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

   public SessionAffinityConfigFluent withAdditionalProperties(Map additionalProperties) {
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
            SessionAffinityConfigFluent that = (SessionAffinityConfigFluent)o;
            if (!Objects.equals(this.clientIP, that.clientIP)) {
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
      return Objects.hash(new Object[]{this.clientIP, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.clientIP != null) {
         sb.append("clientIP:");
         sb.append(this.clientIP + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ClientIPNested extends ClientIPConfigFluent implements Nested {
      ClientIPConfigBuilder builder;

      ClientIPNested(ClientIPConfig item) {
         this.builder = new ClientIPConfigBuilder(this, item);
      }

      public Object and() {
         return SessionAffinityConfigFluent.this.withClientIP(this.builder.build());
      }

      public Object endClientIP() {
         return this.and();
      }
   }
}
