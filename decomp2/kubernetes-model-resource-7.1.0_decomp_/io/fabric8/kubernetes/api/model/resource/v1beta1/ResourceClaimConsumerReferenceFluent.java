package io.fabric8.kubernetes.api.model.resource.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ResourceClaimConsumerReferenceFluent extends BaseFluent {
   private String apiGroup;
   private String name;
   private String resource;
   private String uid;
   private Map additionalProperties;

   public ResourceClaimConsumerReferenceFluent() {
   }

   public ResourceClaimConsumerReferenceFluent(ResourceClaimConsumerReference instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ResourceClaimConsumerReference instance) {
      instance = instance != null ? instance : new ResourceClaimConsumerReference();
      if (instance != null) {
         this.withApiGroup(instance.getApiGroup());
         this.withName(instance.getName());
         this.withResource(instance.getResource());
         this.withUid(instance.getUid());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiGroup() {
      return this.apiGroup;
   }

   public ResourceClaimConsumerReferenceFluent withApiGroup(String apiGroup) {
      this.apiGroup = apiGroup;
      return this;
   }

   public boolean hasApiGroup() {
      return this.apiGroup != null;
   }

   public String getName() {
      return this.name;
   }

   public ResourceClaimConsumerReferenceFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public String getResource() {
      return this.resource;
   }

   public ResourceClaimConsumerReferenceFluent withResource(String resource) {
      this.resource = resource;
      return this;
   }

   public boolean hasResource() {
      return this.resource != null;
   }

   public String getUid() {
      return this.uid;
   }

   public ResourceClaimConsumerReferenceFluent withUid(String uid) {
      this.uid = uid;
      return this;
   }

   public boolean hasUid() {
      return this.uid != null;
   }

   public ResourceClaimConsumerReferenceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ResourceClaimConsumerReferenceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ResourceClaimConsumerReferenceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ResourceClaimConsumerReferenceFluent removeFromAdditionalProperties(Map map) {
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

   public ResourceClaimConsumerReferenceFluent withAdditionalProperties(Map additionalProperties) {
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
            ResourceClaimConsumerReferenceFluent that = (ResourceClaimConsumerReferenceFluent)o;
            if (!Objects.equals(this.apiGroup, that.apiGroup)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.resource, that.resource)) {
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
      return Objects.hash(new Object[]{this.apiGroup, this.name, this.resource, this.uid, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiGroup != null) {
         sb.append("apiGroup:");
         sb.append(this.apiGroup + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.resource != null) {
         sb.append("resource:");
         sb.append(this.resource + ",");
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
