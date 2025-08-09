package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class FlockerVolumeSourceFluent extends BaseFluent {
   private String datasetName;
   private String datasetUUID;
   private Map additionalProperties;

   public FlockerVolumeSourceFluent() {
   }

   public FlockerVolumeSourceFluent(FlockerVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(FlockerVolumeSource instance) {
      instance = instance != null ? instance : new FlockerVolumeSource();
      if (instance != null) {
         this.withDatasetName(instance.getDatasetName());
         this.withDatasetUUID(instance.getDatasetUUID());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getDatasetName() {
      return this.datasetName;
   }

   public FlockerVolumeSourceFluent withDatasetName(String datasetName) {
      this.datasetName = datasetName;
      return this;
   }

   public boolean hasDatasetName() {
      return this.datasetName != null;
   }

   public String getDatasetUUID() {
      return this.datasetUUID;
   }

   public FlockerVolumeSourceFluent withDatasetUUID(String datasetUUID) {
      this.datasetUUID = datasetUUID;
      return this;
   }

   public boolean hasDatasetUUID() {
      return this.datasetUUID != null;
   }

   public FlockerVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public FlockerVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public FlockerVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public FlockerVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public FlockerVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            FlockerVolumeSourceFluent that = (FlockerVolumeSourceFluent)o;
            if (!Objects.equals(this.datasetName, that.datasetName)) {
               return false;
            } else if (!Objects.equals(this.datasetUUID, that.datasetUUID)) {
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
      return Objects.hash(new Object[]{this.datasetName, this.datasetUUID, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.datasetName != null) {
         sb.append("datasetName:");
         sb.append(this.datasetName + ",");
      }

      if (this.datasetUUID != null) {
         sb.append("datasetUUID:");
         sb.append(this.datasetUUID + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
