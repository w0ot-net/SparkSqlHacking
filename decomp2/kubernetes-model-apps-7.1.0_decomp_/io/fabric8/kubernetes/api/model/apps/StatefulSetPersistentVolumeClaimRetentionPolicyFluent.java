package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class StatefulSetPersistentVolumeClaimRetentionPolicyFluent extends BaseFluent {
   private String whenDeleted;
   private String whenScaled;
   private Map additionalProperties;

   public StatefulSetPersistentVolumeClaimRetentionPolicyFluent() {
   }

   public StatefulSetPersistentVolumeClaimRetentionPolicyFluent(StatefulSetPersistentVolumeClaimRetentionPolicy instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(StatefulSetPersistentVolumeClaimRetentionPolicy instance) {
      instance = instance != null ? instance : new StatefulSetPersistentVolumeClaimRetentionPolicy();
      if (instance != null) {
         this.withWhenDeleted(instance.getWhenDeleted());
         this.withWhenScaled(instance.getWhenScaled());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getWhenDeleted() {
      return this.whenDeleted;
   }

   public StatefulSetPersistentVolumeClaimRetentionPolicyFluent withWhenDeleted(String whenDeleted) {
      this.whenDeleted = whenDeleted;
      return this;
   }

   public boolean hasWhenDeleted() {
      return this.whenDeleted != null;
   }

   public String getWhenScaled() {
      return this.whenScaled;
   }

   public StatefulSetPersistentVolumeClaimRetentionPolicyFluent withWhenScaled(String whenScaled) {
      this.whenScaled = whenScaled;
      return this;
   }

   public boolean hasWhenScaled() {
      return this.whenScaled != null;
   }

   public StatefulSetPersistentVolumeClaimRetentionPolicyFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public StatefulSetPersistentVolumeClaimRetentionPolicyFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public StatefulSetPersistentVolumeClaimRetentionPolicyFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public StatefulSetPersistentVolumeClaimRetentionPolicyFluent removeFromAdditionalProperties(Map map) {
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

   public StatefulSetPersistentVolumeClaimRetentionPolicyFluent withAdditionalProperties(Map additionalProperties) {
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
            StatefulSetPersistentVolumeClaimRetentionPolicyFluent that = (StatefulSetPersistentVolumeClaimRetentionPolicyFluent)o;
            if (!Objects.equals(this.whenDeleted, that.whenDeleted)) {
               return false;
            } else if (!Objects.equals(this.whenScaled, that.whenScaled)) {
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
      return Objects.hash(new Object[]{this.whenDeleted, this.whenScaled, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.whenDeleted != null) {
         sb.append("whenDeleted:");
         sb.append(this.whenDeleted + ",");
      }

      if (this.whenScaled != null) {
         sb.append("whenScaled:");
         sb.append(this.whenScaled + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
