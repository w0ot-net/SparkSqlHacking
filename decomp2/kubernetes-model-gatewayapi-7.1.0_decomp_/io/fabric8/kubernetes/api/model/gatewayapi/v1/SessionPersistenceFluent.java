package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class SessionPersistenceFluent extends BaseFluent {
   private String absoluteTimeout;
   private CookieConfigBuilder cookieConfig;
   private String idleTimeout;
   private String sessionName;
   private String type;
   private Map additionalProperties;

   public SessionPersistenceFluent() {
   }

   public SessionPersistenceFluent(SessionPersistence instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(SessionPersistence instance) {
      instance = instance != null ? instance : new SessionPersistence();
      if (instance != null) {
         this.withAbsoluteTimeout(instance.getAbsoluteTimeout());
         this.withCookieConfig(instance.getCookieConfig());
         this.withIdleTimeout(instance.getIdleTimeout());
         this.withSessionName(instance.getSessionName());
         this.withType(instance.getType());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getAbsoluteTimeout() {
      return this.absoluteTimeout;
   }

   public SessionPersistenceFluent withAbsoluteTimeout(String absoluteTimeout) {
      this.absoluteTimeout = absoluteTimeout;
      return this;
   }

   public boolean hasAbsoluteTimeout() {
      return this.absoluteTimeout != null;
   }

   public CookieConfig buildCookieConfig() {
      return this.cookieConfig != null ? this.cookieConfig.build() : null;
   }

   public SessionPersistenceFluent withCookieConfig(CookieConfig cookieConfig) {
      this._visitables.remove("cookieConfig");
      if (cookieConfig != null) {
         this.cookieConfig = new CookieConfigBuilder(cookieConfig);
         this._visitables.get("cookieConfig").add(this.cookieConfig);
      } else {
         this.cookieConfig = null;
         this._visitables.get("cookieConfig").remove(this.cookieConfig);
      }

      return this;
   }

   public boolean hasCookieConfig() {
      return this.cookieConfig != null;
   }

   public SessionPersistenceFluent withNewCookieConfig(String lifetimeType) {
      return this.withCookieConfig(new CookieConfig(lifetimeType));
   }

   public CookieConfigNested withNewCookieConfig() {
      return new CookieConfigNested((CookieConfig)null);
   }

   public CookieConfigNested withNewCookieConfigLike(CookieConfig item) {
      return new CookieConfigNested(item);
   }

   public CookieConfigNested editCookieConfig() {
      return this.withNewCookieConfigLike((CookieConfig)Optional.ofNullable(this.buildCookieConfig()).orElse((Object)null));
   }

   public CookieConfigNested editOrNewCookieConfig() {
      return this.withNewCookieConfigLike((CookieConfig)Optional.ofNullable(this.buildCookieConfig()).orElse((new CookieConfigBuilder()).build()));
   }

   public CookieConfigNested editOrNewCookieConfigLike(CookieConfig item) {
      return this.withNewCookieConfigLike((CookieConfig)Optional.ofNullable(this.buildCookieConfig()).orElse(item));
   }

   public String getIdleTimeout() {
      return this.idleTimeout;
   }

   public SessionPersistenceFluent withIdleTimeout(String idleTimeout) {
      this.idleTimeout = idleTimeout;
      return this;
   }

   public boolean hasIdleTimeout() {
      return this.idleTimeout != null;
   }

   public String getSessionName() {
      return this.sessionName;
   }

   public SessionPersistenceFluent withSessionName(String sessionName) {
      this.sessionName = sessionName;
      return this;
   }

   public boolean hasSessionName() {
      return this.sessionName != null;
   }

   public String getType() {
      return this.type;
   }

   public SessionPersistenceFluent withType(String type) {
      this.type = type;
      return this;
   }

   public boolean hasType() {
      return this.type != null;
   }

   public SessionPersistenceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public SessionPersistenceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public SessionPersistenceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public SessionPersistenceFluent removeFromAdditionalProperties(Map map) {
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

   public SessionPersistenceFluent withAdditionalProperties(Map additionalProperties) {
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
            SessionPersistenceFluent that = (SessionPersistenceFluent)o;
            if (!Objects.equals(this.absoluteTimeout, that.absoluteTimeout)) {
               return false;
            } else if (!Objects.equals(this.cookieConfig, that.cookieConfig)) {
               return false;
            } else if (!Objects.equals(this.idleTimeout, that.idleTimeout)) {
               return false;
            } else if (!Objects.equals(this.sessionName, that.sessionName)) {
               return false;
            } else if (!Objects.equals(this.type, that.type)) {
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
      return Objects.hash(new Object[]{this.absoluteTimeout, this.cookieConfig, this.idleTimeout, this.sessionName, this.type, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.absoluteTimeout != null) {
         sb.append("absoluteTimeout:");
         sb.append(this.absoluteTimeout + ",");
      }

      if (this.cookieConfig != null) {
         sb.append("cookieConfig:");
         sb.append(this.cookieConfig + ",");
      }

      if (this.idleTimeout != null) {
         sb.append("idleTimeout:");
         sb.append(this.idleTimeout + ",");
      }

      if (this.sessionName != null) {
         sb.append("sessionName:");
         sb.append(this.sessionName + ",");
      }

      if (this.type != null) {
         sb.append("type:");
         sb.append(this.type + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class CookieConfigNested extends CookieConfigFluent implements Nested {
      CookieConfigBuilder builder;

      CookieConfigNested(CookieConfig item) {
         this.builder = new CookieConfigBuilder(this, item);
      }

      public Object and() {
         return SessionPersistenceFluent.this.withCookieConfig(this.builder.build());
      }

      public Object endCookieConfig() {
         return this.and();
      }
   }
}
