package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class PodResourceClaimFluent extends BaseFluent {
   private String name;
   private String resourceClaimName;
   private String resourceClaimTemplateName;
   private Map additionalProperties;

   public PodResourceClaimFluent() {
   }

   public PodResourceClaimFluent(PodResourceClaim instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PodResourceClaim instance) {
      instance = instance != null ? instance : new PodResourceClaim();
      if (instance != null) {
         this.withName(instance.getName());
         this.withResourceClaimName(instance.getResourceClaimName());
         this.withResourceClaimTemplateName(instance.getResourceClaimTemplateName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getName() {
      return this.name;
   }

   public PodResourceClaimFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public String getResourceClaimName() {
      return this.resourceClaimName;
   }

   public PodResourceClaimFluent withResourceClaimName(String resourceClaimName) {
      this.resourceClaimName = resourceClaimName;
      return this;
   }

   public boolean hasResourceClaimName() {
      return this.resourceClaimName != null;
   }

   public String getResourceClaimTemplateName() {
      return this.resourceClaimTemplateName;
   }

   public PodResourceClaimFluent withResourceClaimTemplateName(String resourceClaimTemplateName) {
      this.resourceClaimTemplateName = resourceClaimTemplateName;
      return this;
   }

   public boolean hasResourceClaimTemplateName() {
      return this.resourceClaimTemplateName != null;
   }

   public PodResourceClaimFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PodResourceClaimFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PodResourceClaimFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PodResourceClaimFluent removeFromAdditionalProperties(Map map) {
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

   public PodResourceClaimFluent withAdditionalProperties(Map additionalProperties) {
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
            PodResourceClaimFluent that = (PodResourceClaimFluent)o;
            if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.resourceClaimName, that.resourceClaimName)) {
               return false;
            } else if (!Objects.equals(this.resourceClaimTemplateName, that.resourceClaimTemplateName)) {
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
      return Objects.hash(new Object[]{this.name, this.resourceClaimName, this.resourceClaimTemplateName, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.resourceClaimName != null) {
         sb.append("resourceClaimName:");
         sb.append(this.resourceClaimName + ",");
      }

      if (this.resourceClaimTemplateName != null) {
         sb.append("resourceClaimTemplateName:");
         sb.append(this.resourceClaimTemplateName + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
