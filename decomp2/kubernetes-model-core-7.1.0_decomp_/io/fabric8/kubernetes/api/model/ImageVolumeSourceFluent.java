package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ImageVolumeSourceFluent extends BaseFluent {
   private String pullPolicy;
   private String reference;
   private Map additionalProperties;

   public ImageVolumeSourceFluent() {
   }

   public ImageVolumeSourceFluent(ImageVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ImageVolumeSource instance) {
      instance = instance != null ? instance : new ImageVolumeSource();
      if (instance != null) {
         this.withPullPolicy(instance.getPullPolicy());
         this.withReference(instance.getReference());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getPullPolicy() {
      return this.pullPolicy;
   }

   public ImageVolumeSourceFluent withPullPolicy(String pullPolicy) {
      this.pullPolicy = pullPolicy;
      return this;
   }

   public boolean hasPullPolicy() {
      return this.pullPolicy != null;
   }

   public String getReference() {
      return this.reference;
   }

   public ImageVolumeSourceFluent withReference(String reference) {
      this.reference = reference;
      return this;
   }

   public boolean hasReference() {
      return this.reference != null;
   }

   public ImageVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ImageVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ImageVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ImageVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public ImageVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            ImageVolumeSourceFluent that = (ImageVolumeSourceFluent)o;
            if (!Objects.equals(this.pullPolicy, that.pullPolicy)) {
               return false;
            } else if (!Objects.equals(this.reference, that.reference)) {
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
      return Objects.hash(new Object[]{this.pullPolicy, this.reference, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.pullPolicy != null) {
         sb.append("pullPolicy:");
         sb.append(this.pullPolicy + ",");
      }

      if (this.reference != null) {
         sb.append("reference:");
         sb.append(this.reference + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
