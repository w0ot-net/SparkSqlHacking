package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class GRPCHeaderMatchFluent extends BaseFluent {
   private String name;
   private String type;
   private String value;
   private Map additionalProperties;

   public GRPCHeaderMatchFluent() {
   }

   public GRPCHeaderMatchFluent(GRPCHeaderMatch instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(GRPCHeaderMatch instance) {
      instance = instance != null ? instance : new GRPCHeaderMatch();
      if (instance != null) {
         this.withName(instance.getName());
         this.withType(instance.getType());
         this.withValue(instance.getValue());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getName() {
      return this.name;
   }

   public GRPCHeaderMatchFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public String getType() {
      return this.type;
   }

   public GRPCHeaderMatchFluent withType(String type) {
      this.type = type;
      return this;
   }

   public boolean hasType() {
      return this.type != null;
   }

   public String getValue() {
      return this.value;
   }

   public GRPCHeaderMatchFluent withValue(String value) {
      this.value = value;
      return this;
   }

   public boolean hasValue() {
      return this.value != null;
   }

   public GRPCHeaderMatchFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public GRPCHeaderMatchFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public GRPCHeaderMatchFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public GRPCHeaderMatchFluent removeFromAdditionalProperties(Map map) {
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

   public GRPCHeaderMatchFluent withAdditionalProperties(Map additionalProperties) {
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
            GRPCHeaderMatchFluent that = (GRPCHeaderMatchFluent)o;
            if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.type, that.type)) {
               return false;
            } else if (!Objects.equals(this.value, that.value)) {
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
      return Objects.hash(new Object[]{this.name, this.type, this.value, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.type != null) {
         sb.append("type:");
         sb.append(this.type + ",");
      }

      if (this.value != null) {
         sb.append("value:");
         sb.append(this.value + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
