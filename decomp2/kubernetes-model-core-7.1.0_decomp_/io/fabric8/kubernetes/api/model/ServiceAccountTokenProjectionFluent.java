package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ServiceAccountTokenProjectionFluent extends BaseFluent {
   private String audience;
   private Long expirationSeconds;
   private String path;
   private Map additionalProperties;

   public ServiceAccountTokenProjectionFluent() {
   }

   public ServiceAccountTokenProjectionFluent(ServiceAccountTokenProjection instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ServiceAccountTokenProjection instance) {
      instance = instance != null ? instance : new ServiceAccountTokenProjection();
      if (instance != null) {
         this.withAudience(instance.getAudience());
         this.withExpirationSeconds(instance.getExpirationSeconds());
         this.withPath(instance.getPath());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getAudience() {
      return this.audience;
   }

   public ServiceAccountTokenProjectionFluent withAudience(String audience) {
      this.audience = audience;
      return this;
   }

   public boolean hasAudience() {
      return this.audience != null;
   }

   public Long getExpirationSeconds() {
      return this.expirationSeconds;
   }

   public ServiceAccountTokenProjectionFluent withExpirationSeconds(Long expirationSeconds) {
      this.expirationSeconds = expirationSeconds;
      return this;
   }

   public boolean hasExpirationSeconds() {
      return this.expirationSeconds != null;
   }

   public String getPath() {
      return this.path;
   }

   public ServiceAccountTokenProjectionFluent withPath(String path) {
      this.path = path;
      return this;
   }

   public boolean hasPath() {
      return this.path != null;
   }

   public ServiceAccountTokenProjectionFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ServiceAccountTokenProjectionFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ServiceAccountTokenProjectionFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ServiceAccountTokenProjectionFluent removeFromAdditionalProperties(Map map) {
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

   public ServiceAccountTokenProjectionFluent withAdditionalProperties(Map additionalProperties) {
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
            ServiceAccountTokenProjectionFluent that = (ServiceAccountTokenProjectionFluent)o;
            if (!Objects.equals(this.audience, that.audience)) {
               return false;
            } else if (!Objects.equals(this.expirationSeconds, that.expirationSeconds)) {
               return false;
            } else if (!Objects.equals(this.path, that.path)) {
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
      return Objects.hash(new Object[]{this.audience, this.expirationSeconds, this.path, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.audience != null) {
         sb.append("audience:");
         sb.append(this.audience + ",");
      }

      if (this.expirationSeconds != null) {
         sb.append("expirationSeconds:");
         sb.append(this.expirationSeconds + ",");
      }

      if (this.path != null) {
         sb.append("path:");
         sb.append(this.path + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
