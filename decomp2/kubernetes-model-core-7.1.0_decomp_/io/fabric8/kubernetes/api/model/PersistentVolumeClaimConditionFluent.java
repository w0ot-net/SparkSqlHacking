package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class PersistentVolumeClaimConditionFluent extends BaseFluent {
   private String lastProbeTime;
   private String lastTransitionTime;
   private String message;
   private String reason;
   private String status;
   private String type;
   private Map additionalProperties;

   public PersistentVolumeClaimConditionFluent() {
   }

   public PersistentVolumeClaimConditionFluent(PersistentVolumeClaimCondition instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PersistentVolumeClaimCondition instance) {
      instance = instance != null ? instance : new PersistentVolumeClaimCondition();
      if (instance != null) {
         this.withLastProbeTime(instance.getLastProbeTime());
         this.withLastTransitionTime(instance.getLastTransitionTime());
         this.withMessage(instance.getMessage());
         this.withReason(instance.getReason());
         this.withStatus(instance.getStatus());
         this.withType(instance.getType());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getLastProbeTime() {
      return this.lastProbeTime;
   }

   public PersistentVolumeClaimConditionFluent withLastProbeTime(String lastProbeTime) {
      this.lastProbeTime = lastProbeTime;
      return this;
   }

   public boolean hasLastProbeTime() {
      return this.lastProbeTime != null;
   }

   public String getLastTransitionTime() {
      return this.lastTransitionTime;
   }

   public PersistentVolumeClaimConditionFluent withLastTransitionTime(String lastTransitionTime) {
      this.lastTransitionTime = lastTransitionTime;
      return this;
   }

   public boolean hasLastTransitionTime() {
      return this.lastTransitionTime != null;
   }

   public String getMessage() {
      return this.message;
   }

   public PersistentVolumeClaimConditionFluent withMessage(String message) {
      this.message = message;
      return this;
   }

   public boolean hasMessage() {
      return this.message != null;
   }

   public String getReason() {
      return this.reason;
   }

   public PersistentVolumeClaimConditionFluent withReason(String reason) {
      this.reason = reason;
      return this;
   }

   public boolean hasReason() {
      return this.reason != null;
   }

   public String getStatus() {
      return this.status;
   }

   public PersistentVolumeClaimConditionFluent withStatus(String status) {
      this.status = status;
      return this;
   }

   public boolean hasStatus() {
      return this.status != null;
   }

   public String getType() {
      return this.type;
   }

   public PersistentVolumeClaimConditionFluent withType(String type) {
      this.type = type;
      return this;
   }

   public boolean hasType() {
      return this.type != null;
   }

   public PersistentVolumeClaimConditionFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PersistentVolumeClaimConditionFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PersistentVolumeClaimConditionFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PersistentVolumeClaimConditionFluent removeFromAdditionalProperties(Map map) {
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

   public PersistentVolumeClaimConditionFluent withAdditionalProperties(Map additionalProperties) {
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
            PersistentVolumeClaimConditionFluent that = (PersistentVolumeClaimConditionFluent)o;
            if (!Objects.equals(this.lastProbeTime, that.lastProbeTime)) {
               return false;
            } else if (!Objects.equals(this.lastTransitionTime, that.lastTransitionTime)) {
               return false;
            } else if (!Objects.equals(this.message, that.message)) {
               return false;
            } else if (!Objects.equals(this.reason, that.reason)) {
               return false;
            } else if (!Objects.equals(this.status, that.status)) {
               return false;
            } else if (!Objects.equals(this.type, that.type)) {
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
      return Objects.hash(new Object[]{this.lastProbeTime, this.lastTransitionTime, this.message, this.reason, this.status, this.type, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.lastProbeTime != null) {
         sb.append("lastProbeTime:");
         sb.append(this.lastProbeTime + ",");
      }

      if (this.lastTransitionTime != null) {
         sb.append("lastTransitionTime:");
         sb.append(this.lastTransitionTime + ",");
      }

      if (this.message != null) {
         sb.append("message:");
         sb.append(this.message + ",");
      }

      if (this.reason != null) {
         sb.append("reason:");
         sb.append(this.reason + ",");
      }

      if (this.status != null) {
         sb.append("status:");
         sb.append(this.status + ",");
      }

      if (this.type != null) {
         sb.append("type:");
         sb.append(this.type + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
