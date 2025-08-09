package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class EndpointPortFluent extends BaseFluent {
   private String appProtocol;
   private String name;
   private Integer port;
   private String protocol;
   private Map additionalProperties;

   public EndpointPortFluent() {
   }

   public EndpointPortFluent(EndpointPort instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(EndpointPort instance) {
      instance = instance != null ? instance : new EndpointPort();
      if (instance != null) {
         this.withAppProtocol(instance.getAppProtocol());
         this.withName(instance.getName());
         this.withPort(instance.getPort());
         this.withProtocol(instance.getProtocol());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getAppProtocol() {
      return this.appProtocol;
   }

   public EndpointPortFluent withAppProtocol(String appProtocol) {
      this.appProtocol = appProtocol;
      return this;
   }

   public boolean hasAppProtocol() {
      return this.appProtocol != null;
   }

   public String getName() {
      return this.name;
   }

   public EndpointPortFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public Integer getPort() {
      return this.port;
   }

   public EndpointPortFluent withPort(Integer port) {
      this.port = port;
      return this;
   }

   public boolean hasPort() {
      return this.port != null;
   }

   public String getProtocol() {
      return this.protocol;
   }

   public EndpointPortFluent withProtocol(String protocol) {
      this.protocol = protocol;
      return this;
   }

   public boolean hasProtocol() {
      return this.protocol != null;
   }

   public EndpointPortFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public EndpointPortFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public EndpointPortFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public EndpointPortFluent removeFromAdditionalProperties(Map map) {
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

   public EndpointPortFluent withAdditionalProperties(Map additionalProperties) {
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
            EndpointPortFluent that = (EndpointPortFluent)o;
            if (!Objects.equals(this.appProtocol, that.appProtocol)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
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
      return Objects.hash(new Object[]{this.appProtocol, this.name, this.port, this.protocol, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.appProtocol != null) {
         sb.append("appProtocol:");
         sb.append(this.appProtocol + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
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
