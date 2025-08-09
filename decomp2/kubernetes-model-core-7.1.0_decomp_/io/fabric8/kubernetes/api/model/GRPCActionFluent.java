package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class GRPCActionFluent extends BaseFluent {
   private Integer port;
   private String service;
   private Map additionalProperties;

   public GRPCActionFluent() {
   }

   public GRPCActionFluent(GRPCAction instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(GRPCAction instance) {
      instance = instance != null ? instance : new GRPCAction();
      if (instance != null) {
         this.withPort(instance.getPort());
         this.withService(instance.getService());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getPort() {
      return this.port;
   }

   public GRPCActionFluent withPort(Integer port) {
      this.port = port;
      return this;
   }

   public boolean hasPort() {
      return this.port != null;
   }

   public String getService() {
      return this.service;
   }

   public GRPCActionFluent withService(String service) {
      this.service = service;
      return this;
   }

   public boolean hasService() {
      return this.service != null;
   }

   public GRPCActionFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public GRPCActionFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public GRPCActionFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public GRPCActionFluent removeFromAdditionalProperties(Map map) {
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

   public GRPCActionFluent withAdditionalProperties(Map additionalProperties) {
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
            GRPCActionFluent that = (GRPCActionFluent)o;
            if (!Objects.equals(this.port, that.port)) {
               return false;
            } else if (!Objects.equals(this.service, that.service)) {
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
      return Objects.hash(new Object[]{this.port, this.service, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.port != null) {
         sb.append("port:");
         sb.append(this.port + ",");
      }

      if (this.service != null) {
         sb.append("service:");
         sb.append(this.service + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
