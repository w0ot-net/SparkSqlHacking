package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class PersistentVolumeClaimVolumeSourceFluent extends BaseFluent {
   private String claimName;
   private Boolean readOnly;
   private Map additionalProperties;

   public PersistentVolumeClaimVolumeSourceFluent() {
   }

   public PersistentVolumeClaimVolumeSourceFluent(PersistentVolumeClaimVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PersistentVolumeClaimVolumeSource instance) {
      instance = instance != null ? instance : new PersistentVolumeClaimVolumeSource();
      if (instance != null) {
         this.withClaimName(instance.getClaimName());
         this.withReadOnly(instance.getReadOnly());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getClaimName() {
      return this.claimName;
   }

   public PersistentVolumeClaimVolumeSourceFluent withClaimName(String claimName) {
      this.claimName = claimName;
      return this;
   }

   public boolean hasClaimName() {
      return this.claimName != null;
   }

   public Boolean getReadOnly() {
      return this.readOnly;
   }

   public PersistentVolumeClaimVolumeSourceFluent withReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
      return this;
   }

   public boolean hasReadOnly() {
      return this.readOnly != null;
   }

   public PersistentVolumeClaimVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PersistentVolumeClaimVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PersistentVolumeClaimVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PersistentVolumeClaimVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public PersistentVolumeClaimVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            PersistentVolumeClaimVolumeSourceFluent that = (PersistentVolumeClaimVolumeSourceFluent)o;
            if (!Objects.equals(this.claimName, that.claimName)) {
               return false;
            } else if (!Objects.equals(this.readOnly, that.readOnly)) {
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
      return Objects.hash(new Object[]{this.claimName, this.readOnly, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.claimName != null) {
         sb.append("claimName:");
         sb.append(this.claimName + ",");
      }

      if (this.readOnly != null) {
         sb.append("readOnly:");
         sb.append(this.readOnly + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public PersistentVolumeClaimVolumeSourceFluent withReadOnly() {
      return this.withReadOnly(true);
   }
}
