package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class PersistentVolumeStatusFluent extends BaseFluent {
   private String lastPhaseTransitionTime;
   private String message;
   private String phase;
   private String reason;
   private Map additionalProperties;

   public PersistentVolumeStatusFluent() {
   }

   public PersistentVolumeStatusFluent(PersistentVolumeStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PersistentVolumeStatus instance) {
      instance = instance != null ? instance : new PersistentVolumeStatus();
      if (instance != null) {
         this.withLastPhaseTransitionTime(instance.getLastPhaseTransitionTime());
         this.withMessage(instance.getMessage());
         this.withPhase(instance.getPhase());
         this.withReason(instance.getReason());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getLastPhaseTransitionTime() {
      return this.lastPhaseTransitionTime;
   }

   public PersistentVolumeStatusFluent withLastPhaseTransitionTime(String lastPhaseTransitionTime) {
      this.lastPhaseTransitionTime = lastPhaseTransitionTime;
      return this;
   }

   public boolean hasLastPhaseTransitionTime() {
      return this.lastPhaseTransitionTime != null;
   }

   public String getMessage() {
      return this.message;
   }

   public PersistentVolumeStatusFluent withMessage(String message) {
      this.message = message;
      return this;
   }

   public boolean hasMessage() {
      return this.message != null;
   }

   public String getPhase() {
      return this.phase;
   }

   public PersistentVolumeStatusFluent withPhase(String phase) {
      this.phase = phase;
      return this;
   }

   public boolean hasPhase() {
      return this.phase != null;
   }

   public String getReason() {
      return this.reason;
   }

   public PersistentVolumeStatusFluent withReason(String reason) {
      this.reason = reason;
      return this;
   }

   public boolean hasReason() {
      return this.reason != null;
   }

   public PersistentVolumeStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PersistentVolumeStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PersistentVolumeStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PersistentVolumeStatusFluent removeFromAdditionalProperties(Map map) {
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

   public PersistentVolumeStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            PersistentVolumeStatusFluent that = (PersistentVolumeStatusFluent)o;
            if (!Objects.equals(this.lastPhaseTransitionTime, that.lastPhaseTransitionTime)) {
               return false;
            } else if (!Objects.equals(this.message, that.message)) {
               return false;
            } else if (!Objects.equals(this.phase, that.phase)) {
               return false;
            } else if (!Objects.equals(this.reason, that.reason)) {
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
      return Objects.hash(new Object[]{this.lastPhaseTransitionTime, this.message, this.phase, this.reason, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.lastPhaseTransitionTime != null) {
         sb.append("lastPhaseTransitionTime:");
         sb.append(this.lastPhaseTransitionTime + ",");
      }

      if (this.message != null) {
         sb.append("message:");
         sb.append(this.message + ",");
      }

      if (this.phase != null) {
         sb.append("phase:");
         sb.append(this.phase + ",");
      }

      if (this.reason != null) {
         sb.append("reason:");
         sb.append(this.reason + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
