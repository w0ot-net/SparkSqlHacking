package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha3;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class SubjectAltNameFluent extends BaseFluent {
   private String hostname;
   private String type;
   private String uri;
   private Map additionalProperties;

   public SubjectAltNameFluent() {
   }

   public SubjectAltNameFluent(SubjectAltName instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(SubjectAltName instance) {
      instance = instance != null ? instance : new SubjectAltName();
      if (instance != null) {
         this.withHostname(instance.getHostname());
         this.withType(instance.getType());
         this.withUri(instance.getUri());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getHostname() {
      return this.hostname;
   }

   public SubjectAltNameFluent withHostname(String hostname) {
      this.hostname = hostname;
      return this;
   }

   public boolean hasHostname() {
      return this.hostname != null;
   }

   public String getType() {
      return this.type;
   }

   public SubjectAltNameFluent withType(String type) {
      this.type = type;
      return this;
   }

   public boolean hasType() {
      return this.type != null;
   }

   public String getUri() {
      return this.uri;
   }

   public SubjectAltNameFluent withUri(String uri) {
      this.uri = uri;
      return this;
   }

   public boolean hasUri() {
      return this.uri != null;
   }

   public SubjectAltNameFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public SubjectAltNameFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public SubjectAltNameFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public SubjectAltNameFluent removeFromAdditionalProperties(Map map) {
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

   public SubjectAltNameFluent withAdditionalProperties(Map additionalProperties) {
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
            SubjectAltNameFluent that = (SubjectAltNameFluent)o;
            if (!Objects.equals(this.hostname, that.hostname)) {
               return false;
            } else if (!Objects.equals(this.type, that.type)) {
               return false;
            } else if (!Objects.equals(this.uri, that.uri)) {
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
      return Objects.hash(new Object[]{this.hostname, this.type, this.uri, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.hostname != null) {
         sb.append("hostname:");
         sb.append(this.hostname + ",");
      }

      if (this.type != null) {
         sb.append("type:");
         sb.append(this.type + ",");
      }

      if (this.uri != null) {
         sb.append("uri:");
         sb.append(this.uri + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
