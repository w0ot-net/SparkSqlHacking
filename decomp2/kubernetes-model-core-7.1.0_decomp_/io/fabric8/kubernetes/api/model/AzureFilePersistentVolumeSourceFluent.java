package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class AzureFilePersistentVolumeSourceFluent extends BaseFluent {
   private Boolean readOnly;
   private String secretName;
   private String secretNamespace;
   private String shareName;
   private Map additionalProperties;

   public AzureFilePersistentVolumeSourceFluent() {
   }

   public AzureFilePersistentVolumeSourceFluent(AzureFilePersistentVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(AzureFilePersistentVolumeSource instance) {
      instance = instance != null ? instance : new AzureFilePersistentVolumeSource();
      if (instance != null) {
         this.withReadOnly(instance.getReadOnly());
         this.withSecretName(instance.getSecretName());
         this.withSecretNamespace(instance.getSecretNamespace());
         this.withShareName(instance.getShareName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Boolean getReadOnly() {
      return this.readOnly;
   }

   public AzureFilePersistentVolumeSourceFluent withReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
      return this;
   }

   public boolean hasReadOnly() {
      return this.readOnly != null;
   }

   public String getSecretName() {
      return this.secretName;
   }

   public AzureFilePersistentVolumeSourceFluent withSecretName(String secretName) {
      this.secretName = secretName;
      return this;
   }

   public boolean hasSecretName() {
      return this.secretName != null;
   }

   public String getSecretNamespace() {
      return this.secretNamespace;
   }

   public AzureFilePersistentVolumeSourceFluent withSecretNamespace(String secretNamespace) {
      this.secretNamespace = secretNamespace;
      return this;
   }

   public boolean hasSecretNamespace() {
      return this.secretNamespace != null;
   }

   public String getShareName() {
      return this.shareName;
   }

   public AzureFilePersistentVolumeSourceFluent withShareName(String shareName) {
      this.shareName = shareName;
      return this;
   }

   public boolean hasShareName() {
      return this.shareName != null;
   }

   public AzureFilePersistentVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public AzureFilePersistentVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public AzureFilePersistentVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public AzureFilePersistentVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public AzureFilePersistentVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            AzureFilePersistentVolumeSourceFluent that = (AzureFilePersistentVolumeSourceFluent)o;
            if (!Objects.equals(this.readOnly, that.readOnly)) {
               return false;
            } else if (!Objects.equals(this.secretName, that.secretName)) {
               return false;
            } else if (!Objects.equals(this.secretNamespace, that.secretNamespace)) {
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
      return Objects.hash(new Object[]{this.readOnly, this.secretName, this.secretNamespace, this.shareName, this.additionalProperties, super.hashCode()});
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

      if (this.secretNamespace != null) {
         sb.append("secretNamespace:");
         sb.append(this.secretNamespace + ",");
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

   public AzureFilePersistentVolumeSourceFluent withReadOnly() {
      return this.withReadOnly(true);
   }
}
