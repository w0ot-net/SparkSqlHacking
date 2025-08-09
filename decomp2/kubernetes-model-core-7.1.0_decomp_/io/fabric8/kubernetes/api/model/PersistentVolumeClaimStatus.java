package io.fabric8.kubernetes.api.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.builder.Editable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"accessModes", "allocatedResourceStatuses", "allocatedResources", "capacity", "conditions", "currentVolumeAttributesClassName", "modifyVolumeStatus", "phase"})
public class PersistentVolumeClaimStatus implements Editable, KubernetesResource {
   @JsonProperty("accessModes")
   @JsonInclude(Include.NON_EMPTY)
   private List accessModes = new ArrayList();
   @JsonProperty("allocatedResourceStatuses")
   @JsonInclude(Include.NON_EMPTY)
   private Map allocatedResourceStatuses = new LinkedHashMap();
   @JsonProperty("allocatedResources")
   @JsonInclude(Include.NON_EMPTY)
   private Map allocatedResources = new LinkedHashMap();
   @JsonProperty("capacity")
   @JsonInclude(Include.NON_EMPTY)
   private Map capacity = new LinkedHashMap();
   @JsonProperty("conditions")
   @JsonInclude(Include.NON_EMPTY)
   private List conditions = new ArrayList();
   @JsonProperty("currentVolumeAttributesClassName")
   private String currentVolumeAttributesClassName;
   @JsonProperty("modifyVolumeStatus")
   private ModifyVolumeStatus modifyVolumeStatus;
   @JsonProperty("phase")
   private String phase;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PersistentVolumeClaimStatus() {
   }

   public PersistentVolumeClaimStatus(List accessModes, Map allocatedResourceStatuses, Map allocatedResources, Map capacity, List conditions, String currentVolumeAttributesClassName, ModifyVolumeStatus modifyVolumeStatus, String phase) {
      this.accessModes = accessModes;
      this.allocatedResourceStatuses = allocatedResourceStatuses;
      this.allocatedResources = allocatedResources;
      this.capacity = capacity;
      this.conditions = conditions;
      this.currentVolumeAttributesClassName = currentVolumeAttributesClassName;
      this.modifyVolumeStatus = modifyVolumeStatus;
      this.phase = phase;
   }

   @JsonProperty("accessModes")
   @JsonInclude(Include.NON_EMPTY)
   public List getAccessModes() {
      return this.accessModes;
   }

   @JsonProperty("accessModes")
   public void setAccessModes(List accessModes) {
      this.accessModes = accessModes;
   }

   @JsonProperty("allocatedResourceStatuses")
   @JsonInclude(Include.NON_EMPTY)
   public Map getAllocatedResourceStatuses() {
      return this.allocatedResourceStatuses;
   }

   @JsonProperty("allocatedResourceStatuses")
   public void setAllocatedResourceStatuses(Map allocatedResourceStatuses) {
      this.allocatedResourceStatuses = allocatedResourceStatuses;
   }

   @JsonProperty("allocatedResources")
   @JsonInclude(Include.NON_EMPTY)
   public Map getAllocatedResources() {
      return this.allocatedResources;
   }

   @JsonProperty("allocatedResources")
   public void setAllocatedResources(Map allocatedResources) {
      this.allocatedResources = allocatedResources;
   }

   @JsonProperty("capacity")
   @JsonInclude(Include.NON_EMPTY)
   public Map getCapacity() {
      return this.capacity;
   }

   @JsonProperty("capacity")
   public void setCapacity(Map capacity) {
      this.capacity = capacity;
   }

   @JsonProperty("conditions")
   @JsonInclude(Include.NON_EMPTY)
   public List getConditions() {
      return this.conditions;
   }

   @JsonProperty("conditions")
   public void setConditions(List conditions) {
      this.conditions = conditions;
   }

   @JsonProperty("currentVolumeAttributesClassName")
   public String getCurrentVolumeAttributesClassName() {
      return this.currentVolumeAttributesClassName;
   }

   @JsonProperty("currentVolumeAttributesClassName")
   public void setCurrentVolumeAttributesClassName(String currentVolumeAttributesClassName) {
      this.currentVolumeAttributesClassName = currentVolumeAttributesClassName;
   }

   @JsonProperty("modifyVolumeStatus")
   public ModifyVolumeStatus getModifyVolumeStatus() {
      return this.modifyVolumeStatus;
   }

   @JsonProperty("modifyVolumeStatus")
   public void setModifyVolumeStatus(ModifyVolumeStatus modifyVolumeStatus) {
      this.modifyVolumeStatus = modifyVolumeStatus;
   }

   @JsonProperty("phase")
   public String getPhase() {
      return this.phase;
   }

   @JsonProperty("phase")
   public void setPhase(String phase) {
      this.phase = phase;
   }

   @JsonIgnore
   public PersistentVolumeClaimStatusBuilder edit() {
      return new PersistentVolumeClaimStatusBuilder(this);
   }

   @JsonIgnore
   public PersistentVolumeClaimStatusBuilder toBuilder() {
      return this.edit();
   }

   @JsonAnyGetter
   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   @JsonAnySetter
   public void setAdditionalProperty(String name, Object value) {
      this.additionalProperties.put(name, value);
   }

   public void setAdditionalProperties(Map additionalProperties) {
      this.additionalProperties = additionalProperties;
   }

   @Generated
   public String toString() {
      List var10000 = this.getAccessModes();
      return "PersistentVolumeClaimStatus(accessModes=" + var10000 + ", allocatedResourceStatuses=" + this.getAllocatedResourceStatuses() + ", allocatedResources=" + this.getAllocatedResources() + ", capacity=" + this.getCapacity() + ", conditions=" + this.getConditions() + ", currentVolumeAttributesClassName=" + this.getCurrentVolumeAttributesClassName() + ", modifyVolumeStatus=" + this.getModifyVolumeStatus() + ", phase=" + this.getPhase() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PersistentVolumeClaimStatus)) {
         return false;
      } else {
         PersistentVolumeClaimStatus other = (PersistentVolumeClaimStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$accessModes = this.getAccessModes();
            Object other$accessModes = other.getAccessModes();
            if (this$accessModes == null) {
               if (other$accessModes != null) {
                  return false;
               }
            } else if (!this$accessModes.equals(other$accessModes)) {
               return false;
            }

            Object this$allocatedResourceStatuses = this.getAllocatedResourceStatuses();
            Object other$allocatedResourceStatuses = other.getAllocatedResourceStatuses();
            if (this$allocatedResourceStatuses == null) {
               if (other$allocatedResourceStatuses != null) {
                  return false;
               }
            } else if (!this$allocatedResourceStatuses.equals(other$allocatedResourceStatuses)) {
               return false;
            }

            Object this$allocatedResources = this.getAllocatedResources();
            Object other$allocatedResources = other.getAllocatedResources();
            if (this$allocatedResources == null) {
               if (other$allocatedResources != null) {
                  return false;
               }
            } else if (!this$allocatedResources.equals(other$allocatedResources)) {
               return false;
            }

            Object this$capacity = this.getCapacity();
            Object other$capacity = other.getCapacity();
            if (this$capacity == null) {
               if (other$capacity != null) {
                  return false;
               }
            } else if (!this$capacity.equals(other$capacity)) {
               return false;
            }

            Object this$conditions = this.getConditions();
            Object other$conditions = other.getConditions();
            if (this$conditions == null) {
               if (other$conditions != null) {
                  return false;
               }
            } else if (!this$conditions.equals(other$conditions)) {
               return false;
            }

            Object this$currentVolumeAttributesClassName = this.getCurrentVolumeAttributesClassName();
            Object other$currentVolumeAttributesClassName = other.getCurrentVolumeAttributesClassName();
            if (this$currentVolumeAttributesClassName == null) {
               if (other$currentVolumeAttributesClassName != null) {
                  return false;
               }
            } else if (!this$currentVolumeAttributesClassName.equals(other$currentVolumeAttributesClassName)) {
               return false;
            }

            Object this$modifyVolumeStatus = this.getModifyVolumeStatus();
            Object other$modifyVolumeStatus = other.getModifyVolumeStatus();
            if (this$modifyVolumeStatus == null) {
               if (other$modifyVolumeStatus != null) {
                  return false;
               }
            } else if (!this$modifyVolumeStatus.equals(other$modifyVolumeStatus)) {
               return false;
            }

            Object this$phase = this.getPhase();
            Object other$phase = other.getPhase();
            if (this$phase == null) {
               if (other$phase != null) {
                  return false;
               }
            } else if (!this$phase.equals(other$phase)) {
               return false;
            }

            Object this$additionalProperties = this.getAdditionalProperties();
            Object other$additionalProperties = other.getAdditionalProperties();
            if (this$additionalProperties == null) {
               if (other$additionalProperties != null) {
                  return false;
               }
            } else if (!this$additionalProperties.equals(other$additionalProperties)) {
               return false;
            }

            return true;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object other) {
      return other instanceof PersistentVolumeClaimStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $accessModes = this.getAccessModes();
      result = result * 59 + ($accessModes == null ? 43 : $accessModes.hashCode());
      Object $allocatedResourceStatuses = this.getAllocatedResourceStatuses();
      result = result * 59 + ($allocatedResourceStatuses == null ? 43 : $allocatedResourceStatuses.hashCode());
      Object $allocatedResources = this.getAllocatedResources();
      result = result * 59 + ($allocatedResources == null ? 43 : $allocatedResources.hashCode());
      Object $capacity = this.getCapacity();
      result = result * 59 + ($capacity == null ? 43 : $capacity.hashCode());
      Object $conditions = this.getConditions();
      result = result * 59 + ($conditions == null ? 43 : $conditions.hashCode());
      Object $currentVolumeAttributesClassName = this.getCurrentVolumeAttributesClassName();
      result = result * 59 + ($currentVolumeAttributesClassName == null ? 43 : $currentVolumeAttributesClassName.hashCode());
      Object $modifyVolumeStatus = this.getModifyVolumeStatus();
      result = result * 59 + ($modifyVolumeStatus == null ? 43 : $modifyVolumeStatus.hashCode());
      Object $phase = this.getPhase();
      result = result * 59 + ($phase == null ? 43 : $phase.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
