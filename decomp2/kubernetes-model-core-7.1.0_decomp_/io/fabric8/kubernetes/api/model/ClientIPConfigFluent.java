package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ClientIPConfigFluent extends BaseFluent {
   private Integer timeoutSeconds;
   private Map additionalProperties;

   public ClientIPConfigFluent() {
   }

   public ClientIPConfigFluent(ClientIPConfig instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ClientIPConfig instance) {
      instance = instance != null ? instance : new ClientIPConfig();
      if (instance != null) {
         this.withTimeoutSeconds(instance.getTimeoutSeconds());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getTimeoutSeconds() {
      return this.timeoutSeconds;
   }

   public ClientIPConfigFluent withTimeoutSeconds(Integer timeoutSeconds) {
      this.timeoutSeconds = timeoutSeconds;
      return this;
   }

   public boolean hasTimeoutSeconds() {
      return this.timeoutSeconds != null;
   }

   public ClientIPConfigFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ClientIPConfigFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ClientIPConfigFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ClientIPConfigFluent removeFromAdditionalProperties(Map map) {
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

   public ClientIPConfigFluent withAdditionalProperties(Map additionalProperties) {
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
            ClientIPConfigFluent that = (ClientIPConfigFluent)o;
            if (!Objects.equals(this.timeoutSeconds, that.timeoutSeconds)) {
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
      return Objects.hash(new Object[]{this.timeoutSeconds, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.timeoutSeconds != null) {
         sb.append("timeoutSeconds:");
         sb.append(this.timeoutSeconds + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
