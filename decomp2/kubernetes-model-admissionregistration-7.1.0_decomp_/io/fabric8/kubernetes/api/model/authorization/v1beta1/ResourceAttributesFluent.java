package io.fabric8.kubernetes.api.model.authorization.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ResourceAttributesFluent extends BaseFluent {
   private String group;
   private String name;
   private String namespace;
   private String resource;
   private String subresource;
   private String verb;
   private String version;
   private Map additionalProperties;

   public ResourceAttributesFluent() {
   }

   public ResourceAttributesFluent(ResourceAttributes instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ResourceAttributes instance) {
      instance = instance != null ? instance : new ResourceAttributes();
      if (instance != null) {
         this.withGroup(instance.getGroup());
         this.withName(instance.getName());
         this.withNamespace(instance.getNamespace());
         this.withResource(instance.getResource());
         this.withSubresource(instance.getSubresource());
         this.withVerb(instance.getVerb());
         this.withVersion(instance.getVersion());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getGroup() {
      return this.group;
   }

   public ResourceAttributesFluent withGroup(String group) {
      this.group = group;
      return this;
   }

   public boolean hasGroup() {
      return this.group != null;
   }

   public String getName() {
      return this.name;
   }

   public ResourceAttributesFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public String getNamespace() {
      return this.namespace;
   }

   public ResourceAttributesFluent withNamespace(String namespace) {
      this.namespace = namespace;
      return this;
   }

   public boolean hasNamespace() {
      return this.namespace != null;
   }

   public String getResource() {
      return this.resource;
   }

   public ResourceAttributesFluent withResource(String resource) {
      this.resource = resource;
      return this;
   }

   public boolean hasResource() {
      return this.resource != null;
   }

   public String getSubresource() {
      return this.subresource;
   }

   public ResourceAttributesFluent withSubresource(String subresource) {
      this.subresource = subresource;
      return this;
   }

   public boolean hasSubresource() {
      return this.subresource != null;
   }

   public String getVerb() {
      return this.verb;
   }

   public ResourceAttributesFluent withVerb(String verb) {
      this.verb = verb;
      return this;
   }

   public boolean hasVerb() {
      return this.verb != null;
   }

   public String getVersion() {
      return this.version;
   }

   public ResourceAttributesFluent withVersion(String version) {
      this.version = version;
      return this;
   }

   public boolean hasVersion() {
      return this.version != null;
   }

   public ResourceAttributesFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ResourceAttributesFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ResourceAttributesFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ResourceAttributesFluent removeFromAdditionalProperties(Map map) {
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

   public ResourceAttributesFluent withAdditionalProperties(Map additionalProperties) {
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
            ResourceAttributesFluent that = (ResourceAttributesFluent)o;
            if (!Objects.equals(this.group, that.group)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.namespace, that.namespace)) {
               return false;
            } else if (!Objects.equals(this.resource, that.resource)) {
               return false;
            } else if (!Objects.equals(this.subresource, that.subresource)) {
               return false;
            } else if (!Objects.equals(this.verb, that.verb)) {
               return false;
            } else if (!Objects.equals(this.version, that.version)) {
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
      return Objects.hash(new Object[]{this.group, this.name, this.namespace, this.resource, this.subresource, this.verb, this.version, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.group != null) {
         sb.append("group:");
         sb.append(this.group + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.namespace != null) {
         sb.append("namespace:");
         sb.append(this.namespace + ",");
      }

      if (this.resource != null) {
         sb.append("resource:");
         sb.append(this.resource + ",");
      }

      if (this.subresource != null) {
         sb.append("subresource:");
         sb.append(this.subresource + ",");
      }

      if (this.verb != null) {
         sb.append("verb:");
         sb.append(this.verb + ",");
      }

      if (this.version != null) {
         sb.append("version:");
         sb.append(this.version + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
