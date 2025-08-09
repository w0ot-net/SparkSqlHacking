package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class GRPCMethodMatchFluent extends BaseFluent {
   private String method;
   private String service;
   private String type;
   private Map additionalProperties;

   public GRPCMethodMatchFluent() {
   }

   public GRPCMethodMatchFluent(GRPCMethodMatch instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(GRPCMethodMatch instance) {
      instance = instance != null ? instance : new GRPCMethodMatch();
      if (instance != null) {
         this.withMethod(instance.getMethod());
         this.withService(instance.getService());
         this.withType(instance.getType());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getMethod() {
      return this.method;
   }

   public GRPCMethodMatchFluent withMethod(String method) {
      this.method = method;
      return this;
   }

   public boolean hasMethod() {
      return this.method != null;
   }

   public String getService() {
      return this.service;
   }

   public GRPCMethodMatchFluent withService(String service) {
      this.service = service;
      return this;
   }

   public boolean hasService() {
      return this.service != null;
   }

   public String getType() {
      return this.type;
   }

   public GRPCMethodMatchFluent withType(String type) {
      this.type = type;
      return this;
   }

   public boolean hasType() {
      return this.type != null;
   }

   public GRPCMethodMatchFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public GRPCMethodMatchFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public GRPCMethodMatchFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public GRPCMethodMatchFluent removeFromAdditionalProperties(Map map) {
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

   public GRPCMethodMatchFluent withAdditionalProperties(Map additionalProperties) {
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
            GRPCMethodMatchFluent that = (GRPCMethodMatchFluent)o;
            if (!Objects.equals(this.method, that.method)) {
               return false;
            } else if (!Objects.equals(this.service, that.service)) {
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
      return Objects.hash(new Object[]{this.method, this.service, this.type, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.method != null) {
         sb.append("method:");
         sb.append(this.method + ",");
      }

      if (this.service != null) {
         sb.append("service:");
         sb.append(this.service + ",");
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
}
