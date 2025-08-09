package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class AzureFileVolumeSourceFluent extends BaseFluent {
   private Boolean readOnly;
   private String secretName;
   private String shareName;
   private Map additionalProperties;

   public AzureFileVolumeSourceFluent() {
   }

   public AzureFileVolumeSourceFluent(AzureFileVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(AzureFileVolumeSource instance) {
      instance = instance != null ? instance : new AzureFileVolumeSource();
      if (instance != null) {
         this.withReadOnly(instance.getReadOnly());
         this.withSecretName(instance.getSecretName());
         this.withShareName(instance.getShareName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Boolean getReadOnly() {
      return this.readOnly;
   }

   public AzureFileVolumeSourceFluent withReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
      return this;
   }

   public boolean hasReadOnly() {
      return this.readOnly != null;
   }

   public String getSecretName() {
      return this.secretName;
   }

   public AzureFileVolumeSourceFluent withSecretName(String secretName) {
      this.secretName = secretName;
      return this;
   }

   public boolean hasSecretName() {
      return this.secretName != null;
   }

   public String getShareName() {
      return this.shareName;
   }

   public AzureFileVolumeSourceFluent withShareName(String shareName) {
      this.shareName = shareName;
      return this;
   }

   public boolean hasShareName() {
      return this.shareName != null;
   }

   public AzureFileVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public AzureFileVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public AzureFileVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public AzureFileVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public AzureFileVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            AzureFileVolumeSourceFluent that = (AzureFileVolumeSourceFluent)o;
            if (!Objects.equals(this.readOnly, that.readOnly)) {
               return false;
            } else if (!Objects.equals(this.secretName, that.secretName)) {
               return false;
            } else if (!Objects.equals(this.shareName, that.shareName)) {
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
      return Objects.hash(new Object[]{this.readOnly, this.secretName, this.shareName, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.readOnly != null) {
         sb.append("readOnly:");
         sb.append(this.readOnly + ",");
      }

      if (this.secretName != null) {
         sb.append("secretName:");
         sb.append(this.secretName + ",");
      }

      if (this.shareName != null) {
         sb.append("shareName:");
         sb.append(this.shareName + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public AzureFileVolumeSourceFluent withReadOnly() {
      return this.withReadOnly(true);
   }
}
