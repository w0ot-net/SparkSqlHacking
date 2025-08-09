package io.fabric8.kubernetes.api.model.networking.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class IngressPortStatusFluent extends BaseFluent {
   private String error;
   private Integer port;
   private String protocol;
   private Map additionalProperties;

   public IngressPortStatusFluent() {
   }

   public IngressPortStatusFluent(IngressPortStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(IngressPortStatus instance) {
      instance = instance != null ? instance : new IngressPortStatus();
      if (instance != null) {
         this.withError(instance.getError());
         this.withPort(instance.getPort());
         this.withProtocol(instance.getProtocol());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getError() {
      return this.error;
   }

   public IngressPortStatusFluent withError(String error) {
      this.error = error;
      return this;
   }

   public boolean hasError() {
      return this.error != null;
   }

   public Integer getPort() {
      return this.port;
   }

   public IngressPortStatusFluent withPort(Integer port) {
      this.port = port;
      return this;
   }

   public boolean hasPort() {
      return this.port != null;
   }

   public String getProtocol() {
      return this.protocol;
   }

   public IngressPortStatusFluent withProtocol(String protocol) {
      this.protocol = protocol;
      return this;
   }

   public boolean hasProtocol() {
      return this.protocol != null;
   }

   public IngressPortStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public IngressPortStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public IngressPortStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public IngressPortStatusFluent removeFromAdditionalProperties(Map map) {
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

   public IngressPortStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            IngressPortStatusFluent that = (IngressPortStatusFluent)o;
            if (!Objects.equals(this.error, that.error)) {
               return false;
            } else if (!Objects.equals(this.port, that.port)) {
               return false;
            } else if (!Objects.equals(this.protocol, that.protocol)) {
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
      return Objects.hash(new Object[]{this.error, this.port, this.protocol, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.error != null) {
         sb.append("error:");
         sb.append(this.error + ",");
      }

      if (this.port != null) {
         sb.append("port:");
         sb.append(this.port + ",");
      }

      if (this.protocol != null) {
         sb.append("protocol:");
         sb.append(this.protocol + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
