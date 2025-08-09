package io.fabric8.kubernetes.api.model.clusterapi.v1beta1;

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
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.fabric8.kubernetes.api.model.NodeSystemInfo;
import io.fabric8.kubernetes.api.model.ObjectReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"addresses", "bootstrapReady", "certificatesExpiryDate", "conditions", "deletion", "failureMessage", "failureReason", "infrastructureReady", "lastUpdated", "nodeInfo", "nodeRef", "observedGeneration", "phase", "v1beta2"})
public class MachineStatus implements Editable, KubernetesResource {
   @JsonProperty("addresses")
   @JsonInclude(Include.NON_EMPTY)
   private List addresses = new ArrayList();
   @JsonProperty("bootstrapReady")
   private Boolean bootstrapReady;
   @JsonProperty("certificatesExpiryDate")
   private String certificatesExpiryDate;
   @JsonProperty("conditions")
   @JsonInclude(Include.NON_EMPTY)
   private List conditions = new ArrayList();
   @JsonProperty("deletion")
   private MachineDeletionStatus deletion;
   @JsonProperty("failureMessage")
   private String failureMessage;
   @JsonProperty("failureReason")
   private String failureReason;
   @JsonProperty("infrastructureReady")
   private Boolean infrastructureReady;
   @JsonProperty("lastUpdated")
   private String lastUpdated;
   @JsonProperty("nodeInfo")
   private NodeSystemInfo nodeInfo;
   @JsonProperty("nodeRef")
   private ObjectReference nodeRef;
   @JsonProperty("observedGeneration")
   private Long observedGeneration;
   @JsonProperty("phase")
   private String phase;
   @JsonProperty("v1beta2")
   private MachineV1Beta2Status v1beta2;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public MachineStatus() {
   }

   public MachineStatus(List addresses, Boolean bootstrapReady, String certificatesExpiryDate, List conditions, MachineDeletionStatus deletion, String failureMessage, String failureReason, Boolean infrastructureReady, String lastUpdated, NodeSystemInfo nodeInfo, ObjectReference nodeRef, Long observedGeneration, String phase, MachineV1Beta2Status v1beta2) {
      this.addresses = addresses;
      this.bootstrapReady = bootstrapReady;
      this.certificatesExpiryDate = certificatesExpiryDate;
      this.conditions = conditions;
      this.deletion = deletion;
      this.failureMessage = failureMessage;
      this.failureReason = failureReason;
      this.infrastructureReady = infrastructureReady;
      this.lastUpdated = lastUpdated;
      this.nodeInfo = nodeInfo;
      this.nodeRef = nodeRef;
      this.observedGeneration = observedGeneration;
      this.phase = phase;
      this.v1beta2 = v1beta2;
   }

   @JsonProperty("addresses")
   @JsonInclude(Include.NON_EMPTY)
   public List getAddresses() {
      return this.addresses;
   }

   @JsonProperty("addresses")
   public void setAddresses(List addresses) {
      this.addresses = addresses;
   }

   @JsonProperty("bootstrapReady")
   public Boolean getBootstrapReady() {
      return this.bootstrapReady;
   }

   @JsonProperty("bootstrapReady")
   public void setBootstrapReady(Boolean bootstrapReady) {
      this.bootstrapReady = bootstrapReady;
   }

   @JsonProperty("certificatesExpiryDate")
   public String getCertificatesExpiryDate() {
      return this.certificatesExpiryDate;
   }

   @JsonProperty("certificatesExpiryDate")
   public void setCertificatesExpiryDate(String certificatesExpiryDate) {
      this.certificatesExpiryDate = certificatesExpiryDate;
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

   @JsonProperty("deletion")
   public MachineDeletionStatus getDeletion() {
      return this.deletion;
   }

   @JsonProperty("deletion")
   public void setDeletion(MachineDeletionStatus deletion) {
      this.deletion = deletion;
   }

   @JsonProperty("failureMessage")
   public String getFailureMessage() {
      return this.failureMessage;
   }

   @JsonProperty("failureMessage")
   public void setFailureMessage(String failureMessage) {
      this.failureMessage = failureMessage;
   }

   @JsonProperty("failureReason")
   public String getFailureReason() {
      return this.failureReason;
   }

   @JsonProperty("failureReason")
   public void setFailureReason(String failureReason) {
      this.failureReason = failureReason;
   }

   @JsonProperty("infrastructureReady")
   public Boolean getInfrastructureReady() {
      return this.infrastructureReady;
   }

   @JsonProperty("infrastructureReady")
   public void setInfrastructureReady(Boolean infrastructureReady) {
      this.infrastructureReady = infrastructureReady;
   }

   @JsonProperty("lastUpdated")
   public String getLastUpdated() {
      return this.lastUpdated;
   }

   @JsonProperty("lastUpdated")
   public void setLastUpdated(String lastUpdated) {
      this.lastUpdated = lastUpdated;
   }

   @JsonProperty("nodeInfo")
   public NodeSystemInfo getNodeInfo() {
      return this.nodeInfo;
   }

   @JsonProperty("nodeInfo")
   public void setNodeInfo(NodeSystemInfo nodeInfo) {
      this.nodeInfo = nodeInfo;
   }

   @JsonProperty("nodeRef")
   public ObjectReference getNodeRef() {
      return this.nodeRef;
   }

   @JsonProperty("nodeRef")
   public void setNodeRef(ObjectReference nodeRef) {
      this.nodeRef = nodeRef;
   }

   @JsonProperty("observedGeneration")
   public Long getObservedGeneration() {
      return this.observedGeneration;
   }

   @JsonProperty("observedGeneration")
   public void setObservedGeneration(Long observedGeneration) {
      this.observedGeneration = observedGeneration;
   }

   @JsonProperty("phase")
   public String getPhase() {
      return this.phase;
   }

   @JsonProperty("phase")
   public void setPhase(String phase) {
      this.phase = phase;
   }

   @JsonProperty("v1beta2")
   public MachineV1Beta2Status getV1beta2() {
      return this.v1beta2;
   }

   @JsonProperty("v1beta2")
   public void setV1beta2(MachineV1Beta2Status v1beta2) {
      this.v1beta2 = v1beta2;
   }

   @JsonIgnore
   public MachineStatusBuilder edit() {
      return new MachineStatusBuilder(this);
   }

   @JsonIgnore
   public MachineStatusBuilder toBuilder() {
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
      List var10000 = this.getAddresses();
      return "MachineStatus(addresses=" + var10000 + ", bootstrapReady=" + this.getBootstrapReady() + ", certificatesExpiryDate=" + this.getCertificatesExpiryDate() + ", conditions=" + this.getConditions() + ", deletion=" + this.getDeletion() + ", failureMessage=" + this.getFailureMessage() + ", failureReason=" + this.getFailureReason() + ", infrastructureReady=" + this.getInfrastructureReady() + ", lastUpdated=" + this.getLastUpdated() + ", nodeInfo=" + this.getNodeInfo() + ", nodeRef=" + this.getNodeRef() + ", observedGeneration=" + this.getObservedGeneration() + ", phase=" + this.getPhase() + ", v1beta2=" + this.getV1beta2() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MachineStatus)) {
         return false;
      } else {
         MachineStatus other = (MachineStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$bootstrapReady = this.getBootstrapReady();
            Object other$bootstrapReady = other.getBootstrapReady();
            if (this$bootstrapReady == null) {
               if (other$bootstrapReady != null) {
                  return false;
               }
            } else if (!this$bootstrapReady.equals(other$bootstrapReady)) {
               return false;
            }

            Object this$infrastructureReady = this.getInfrastructureReady();
            Object other$infrastructureReady = other.getInfrastructureReady();
            if (this$infrastructureReady == null) {
               if (other$infrastructureReady != null) {
                  return false;
               }
            } else if (!this$infrastructureReady.equals(other$infrastructureReady)) {
               return false;
            }

            Object this$observedGeneration = this.getObservedGeneration();
            Object other$observedGeneration = other.getObservedGeneration();
            if (this$observedGeneration == null) {
               if (other$observedGeneration != null) {
                  return false;
               }
            } else if (!this$observedGeneration.equals(other$observedGeneration)) {
               return false;
            }

            Object this$addresses = this.getAddresses();
            Object other$addresses = other.getAddresses();
            if (this$addresses == null) {
               if (other$addresses != null) {
                  return false;
               }
            } else if (!this$addresses.equals(other$addresses)) {
               return false;
            }

            Object this$certificatesExpiryDate = this.getCertificatesExpiryDate();
            Object other$certificatesExpiryDate = other.getCertificatesExpiryDate();
            if (this$certificatesExpiryDate == null) {
               if (other$certificatesExpiryDate != null) {
                  return false;
               }
            } else if (!this$certificatesExpiryDate.equals(other$certificatesExpiryDate)) {
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

            Object this$deletion = this.getDeletion();
            Object other$deletion = other.getDeletion();
            if (this$deletion == null) {
               if (other$deletion != null) {
                  return false;
               }
            } else if (!this$deletion.equals(other$deletion)) {
               return false;
            }

            Object this$failureMessage = this.getFailureMessage();
            Object other$failureMessage = other.getFailureMessage();
            if (this$failureMessage == null) {
               if (other$failureMessage != null) {
                  return false;
               }
            } else if (!this$failureMessage.equals(other$failureMessage)) {
               return false;
            }

            Object this$failureReason = this.getFailureReason();
            Object other$failureReason = other.getFailureReason();
            if (this$failureReason == null) {
               if (other$failureReason != null) {
                  return false;
               }
            } else if (!this$failureReason.equals(other$failureReason)) {
               return false;
            }

            Object this$lastUpdated = this.getLastUpdated();
            Object other$lastUpdated = other.getLastUpdated();
            if (this$lastUpdated == null) {
               if (other$lastUpdated != null) {
                  return false;
               }
            } else if (!this$lastUpdated.equals(other$lastUpdated)) {
               return false;
            }

            Object this$nodeInfo = this.getNodeInfo();
            Object other$nodeInfo = other.getNodeInfo();
            if (this$nodeInfo == null) {
               if (other$nodeInfo != null) {
                  return false;
               }
            } else if (!this$nodeInfo.equals(other$nodeInfo)) {
               return false;
            }

            Object this$nodeRef = this.getNodeRef();
            Object other$nodeRef = other.getNodeRef();
            if (this$nodeRef == null) {
               if (other$nodeRef != null) {
                  return false;
               }
            } else if (!this$nodeRef.equals(other$nodeRef)) {
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

            Object this$v1beta2 = this.getV1beta2();
            Object other$v1beta2 = other.getV1beta2();
            if (this$v1beta2 == null) {
               if (other$v1beta2 != null) {
                  return false;
               }
            } else if (!this$v1beta2.equals(other$v1beta2)) {
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
      return other instanceof MachineStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $bootstrapReady = this.getBootstrapReady();
      result = result * 59 + ($bootstrapReady == null ? 43 : $bootstrapReady.hashCode());
      Object $infrastructureReady = this.getInfrastructureReady();
      result = result * 59 + ($infrastructureReady == null ? 43 : $infrastructureReady.hashCode());
      Object $observedGeneration = this.getObservedGeneration();
      result = result * 59 + ($observedGeneration == null ? 43 : $observedGeneration.hashCode());
      Object $addresses = this.getAddresses();
      result = result * 59 + ($addresses == null ? 43 : $addresses.hashCode());
      Object $certificatesExpiryDate = this.getCertificatesExpiryDate();
      result = result * 59 + ($certificatesExpiryDate == null ? 43 : $certificatesExpiryDate.hashCode());
      Object $conditions = this.getConditions();
      result = result * 59 + ($conditions == null ? 43 : $conditions.hashCode());
      Object $deletion = this.getDeletion();
      result = result * 59 + ($deletion == null ? 43 : $deletion.hashCode());
      Object $failureMessage = this.getFailureMessage();
      result = result * 59 + ($failureMessage == null ? 43 : $failureMessage.hashCode());
      Object $failureReason = this.getFailureReason();
      result = result * 59 + ($failureReason == null ? 43 : $failureReason.hashCode());
      Object $lastUpdated = this.getLastUpdated();
      result = result * 59 + ($lastUpdated == null ? 43 : $lastUpdated.hashCode());
      Object $nodeInfo = this.getNodeInfo();
      result = result * 59 + ($nodeInfo == null ? 43 : $nodeInfo.hashCode());
      Object $nodeRef = this.getNodeRef();
      result = result * 59 + ($nodeRef == null ? 43 : $nodeRef.hashCode());
      Object $phase = this.getPhase();
      result = result * 59 + ($phase == null ? 43 : $phase.hashCode());
      Object $v1beta2 = this.getV1beta2();
      result = result * 59 + ($v1beta2 == null ? 43 : $v1beta2.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
