package io.fabric8.kubernetes.api.model.apiextensions.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class CustomResourceSubresourceScaleFluent extends BaseFluent {
   private String labelSelectorPath;
   private String specReplicasPath;
   private String statusReplicasPath;
   private Map additionalProperties;

   public CustomResourceSubresourceScaleFluent() {
   }

   public CustomResourceSubresourceScaleFluent(CustomResourceSubresourceScale instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(CustomResourceSubresourceScale instance) {
      instance = instance != null ? instance : new CustomResourceSubresourceScale();
      if (instance != null) {
         this.withLabelSelectorPath(instance.getLabelSelectorPath());
         this.withSpecReplicasPath(instance.getSpecReplicasPath());
         this.withStatusReplicasPath(instance.getStatusReplicasPath());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getLabelSelectorPath() {
      return this.labelSelectorPath;
   }

   public CustomResourceSubresourceScaleFluent withLabelSelectorPath(String labelSelectorPath) {
      this.labelSelectorPath = labelSelectorPath;
      return this;
   }

   public boolean hasLabelSelectorPath() {
      return this.labelSelectorPath != null;
   }

   public String getSpecReplicasPath() {
      return this.specReplicasPath;
   }

   public CustomResourceSubresourceScaleFluent withSpecReplicasPath(String specReplicasPath) {
      this.specReplicasPath = specReplicasPath;
      return this;
   }

   public boolean hasSpecReplicasPath() {
      return this.specReplicasPath != null;
   }

   public String getStatusReplicasPath() {
      return this.statusReplicasPath;
   }

   public CustomResourceSubresourceScaleFluent withStatusReplicasPath(String statusReplicasPath) {
      this.statusReplicasPath = statusReplicasPath;
      return this;
   }

   public boolean hasStatusReplicasPath() {
      return this.statusReplicasPath != null;
   }

   public CustomResourceSubresourceScaleFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public CustomResourceSubresourceScaleFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public CustomResourceSubresourceScaleFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public CustomResourceSubresourceScaleFluent removeFromAdditionalProperties(Map map) {
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

   public CustomResourceSubresourceScaleFluent withAdditionalProperties(Map additionalProperties) {
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
            CustomResourceSubresourceScaleFluent that = (CustomResourceSubresourceScaleFluent)o;
            if (!Objects.equals(this.labelSelectorPath, that.labelSelectorPath)) {
               return false;
            } else if (!Objects.equals(this.specReplicasPath, that.specReplicasPath)) {
               return false;
            } else if (!Objects.equals(this.statusReplicasPath, that.statusReplicasPath)) {
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
      return Objects.hash(new Object[]{this.labelSelectorPath, this.specReplicasPath, this.statusReplicasPath, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.labelSelectorPath != null) {
         sb.append("labelSelectorPath:");
         sb.append(this.labelSelectorPath + ",");
      }

      if (this.specReplicasPath != null) {
         sb.append("specReplicasPath:");
         sb.append(this.specReplicasPath + ",");
      }

      if (this.statusReplicasPath != null) {
         sb.append("statusReplicasPath:");
         sb.append(this.statusReplicasPath + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
