package io.fabric8.kubernetes.api.model.apiextensions.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class CustomResourceColumnDefinitionFluent extends BaseFluent {
   private String jSONPath;
   private String description;
   private String format;
   private String name;
   private Integer priority;
   private String type;
   private Map additionalProperties;

   public CustomResourceColumnDefinitionFluent() {
   }

   public CustomResourceColumnDefinitionFluent(CustomResourceColumnDefinition instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(CustomResourceColumnDefinition instance) {
      instance = instance != null ? instance : new CustomResourceColumnDefinition();
      if (instance != null) {
         this.withJSONPath(instance.getJSONPath());
         this.withDescription(instance.getDescription());
         this.withFormat(instance.getFormat());
         this.withName(instance.getName());
         this.withPriority(instance.getPriority());
         this.withType(instance.getType());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getJSONPath() {
      return this.jSONPath;
   }

   public CustomResourceColumnDefinitionFluent withJSONPath(String jSONPath) {
      this.jSONPath = jSONPath;
      return this;
   }

   public boolean hasJSONPath() {
      return this.jSONPath != null;
   }

   public String getDescription() {
      return this.description;
   }

   public CustomResourceColumnDefinitionFluent withDescription(String description) {
      this.description = description;
      return this;
   }

   public boolean hasDescription() {
      return this.description != null;
   }

   public String getFormat() {
      return this.format;
   }

   public CustomResourceColumnDefinitionFluent withFormat(String format) {
      this.format = format;
      return this;
   }

   public boolean hasFormat() {
      return this.format != null;
   }

   public String getName() {
      return this.name;
   }

   public CustomResourceColumnDefinitionFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public Integer getPriority() {
      return this.priority;
   }

   public CustomResourceColumnDefinitionFluent withPriority(Integer priority) {
      this.priority = priority;
      return this;
   }

   public boolean hasPriority() {
      return this.priority != null;
   }

   public String getType() {
      return this.type;
   }

   public CustomResourceColumnDefinitionFluent withType(String type) {
      this.type = type;
      return this;
   }

   public boolean hasType() {
      return this.type != null;
   }

   public CustomResourceColumnDefinitionFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public CustomResourceColumnDefinitionFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public CustomResourceColumnDefinitionFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public CustomResourceColumnDefinitionFluent removeFromAdditionalProperties(Map map) {
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

   public CustomResourceColumnDefinitionFluent withAdditionalProperties(Map additionalProperties) {
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
            CustomResourceColumnDefinitionFluent that = (CustomResourceColumnDefinitionFluent)o;
            if (!Objects.equals(this.jSONPath, that.jSONPath)) {
               return false;
            } else if (!Objects.equals(this.description, that.description)) {
               return false;
            } else if (!Objects.equals(this.format, that.format)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.priority, that.priority)) {
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
      return Objects.hash(new Object[]{this.jSONPath, this.description, this.format, this.name, this.priority, this.type, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.jSONPath != null) {
         sb.append("jSONPath:");
         sb.append(this.jSONPath + ",");
      }

      if (this.description != null) {
         sb.append("description:");
         sb.append(this.description + ",");
      }

      if (this.format != null) {
         sb.append("format:");
         sb.append(this.format + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.priority != null) {
         sb.append("priority:");
         sb.append(this.priority + ",");
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
