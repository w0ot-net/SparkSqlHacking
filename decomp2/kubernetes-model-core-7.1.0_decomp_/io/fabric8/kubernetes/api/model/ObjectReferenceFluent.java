package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ObjectReferenceFluent extends BaseFluent {
   private String apiVersion;
   private String fieldPath;
   private String kind;
   private String name;
   private String namespace;
   private String resourceVersion;
   private String uid;
   private Map additionalProperties;

   public ObjectReferenceFluent() {
   }

   public ObjectReferenceFluent(ObjectReference instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ObjectReference instance) {
      instance = instance != null ? instance : new ObjectReference();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withFieldPath(instance.getFieldPath());
         this.withKind(instance.getKind());
         this.withName(instance.getName());
         this.withNamespace(instance.getNamespace());
         this.withResourceVersion(instance.getResourceVersion());
         this.withUid(instance.getUid());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public ObjectReferenceFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public String getFieldPath() {
      return this.fieldPath;
   }

   public ObjectReferenceFluent withFieldPath(String fieldPath) {
      this.fieldPath = fieldPath;
      return this;
   }

   public boolean hasFieldPath() {
      return this.fieldPath != null;
   }

   public String getKind() {
      return this.kind;
   }

   public ObjectReferenceFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public String getName() {
      return this.name;
   }

   public ObjectReferenceFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public String getNamespace() {
      return this.namespace;
   }

   public ObjectReferenceFluent withNamespace(String namespace) {
      this.namespace = namespace;
      return this;
   }

   public boolean hasNamespace() {
      return this.namespace != null;
   }

   public String getResourceVersion() {
      return this.resourceVersion;
   }

   public ObjectReferenceFluent withResourceVersion(String resourceVersion) {
      this.resourceVersion = resourceVersion;
      return this;
   }

   public boolean hasResourceVersion() {
      return this.resourceVersion != null;
   }

   public String getUid() {
      return this.uid;
   }

   public ObjectReferenceFluent withUid(String uid) {
      this.uid = uid;
      return this;
   }

   public boolean hasUid() {
      return this.uid != null;
   }

   public ObjectReferenceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ObjectReferenceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ObjectReferenceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ObjectReferenceFluent removeFromAdditionalProperties(Map map) {
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

   public ObjectReferenceFluent withAdditionalProperties(Map additionalProperties) {
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
            ObjectReferenceFluent that = (ObjectReferenceFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.fieldPath, that.fieldPath)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.namespace, that.namespace)) {
               return false;
            } else if (!Objects.equals(this.resourceVersion, that.resourceVersion)) {
               return false;
            } else if (!Objects.equals(this.uid, that.uid)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.fieldPath, this.kind, this.name, this.namespace, this.resourceVersion, this.uid, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.fieldPath != null) {
         sb.append("fieldPath:");
         sb.append(this.fieldPath + ",");
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

      if (this.resourceVersion != null) {
         sb.append("resourceVersion:");
         sb.append(this.resourceVersion + ",");
      }

      if (this.uid != null) {
         sb.append("uid:");
         sb.append(this.uid + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
