package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class TypedObjectReferenceFluent extends BaseFluent {
   private String apiGroup;
   private String kind;
   private String name;
   private String namespace;
   private Map additionalProperties;

   public TypedObjectReferenceFluent() {
   }

   public TypedObjectReferenceFluent(TypedObjectReference instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(TypedObjectReference instance) {
      instance = instance != null ? instance : new TypedObjectReference();
      if (instance != null) {
         this.withApiGroup(instance.getApiGroup());
         this.withKind(instance.getKind());
         this.withName(instance.getName());
         this.withNamespace(instance.getNamespace());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiGroup() {
      return this.apiGroup;
   }

   public TypedObjectReferenceFluent withApiGroup(String apiGroup) {
      this.apiGroup = apiGroup;
      return this;
   }

   public boolean hasApiGroup() {
      return this.apiGroup != null;
   }

   public String getKind() {
      return this.kind;
   }

   public TypedObjectReferenceFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public String getName() {
      return this.name;
   }

   public TypedObjectReferenceFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public String getNamespace() {
      return this.namespace;
   }

   public TypedObjectReferenceFluent withNamespace(String namespace) {
      this.namespace = namespace;
      return this;
   }

   public boolean hasNamespace() {
      return this.namespace != null;
   }

   public TypedObjectReferenceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public TypedObjectReferenceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public TypedObjectReferenceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public TypedObjectReferenceFluent removeFromAdditionalProperties(Map map) {
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

   public TypedObjectReferenceFluent withAdditionalProperties(Map additionalProperties) {
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
            TypedObjectReferenceFluent that = (TypedObjectReferenceFluent)o;
            if (!Objects.equals(this.apiGroup, that.apiGroup)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.namespace, that.namespace)) {
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
      return Objects.hash(new Object[]{this.apiGroup, this.kind, this.name, this.namespace, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiGroup != null) {
         sb.append("apiGroup:");
         sb.append(this.apiGroup + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.namespace != null) {
         sb.append("namespace:");
         sb.append(this.namespace + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
