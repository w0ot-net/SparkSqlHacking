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
import io.fabric8.kubernetes.api.model.Duration;
import io.fabric8.kubernetes.api.model.KubernetesResource;
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
@JsonPropertyOrder({"bootstrap", "clusterName", "failureDomain", "infrastructureRef", "nodeDeletionTimeout", "nodeDrainTimeout", "nodeVolumeDetachTimeout", "providerID", "readinessGates", "version"})
public class MachineSpec implements Editable, KubernetesResource {
   @JsonProperty("bootstrap")
   private Bootstrap bootstrap;
   @JsonProperty("clusterName")
   private String clusterName;
   @JsonProperty("failureDomain")
   private String failureDomain;
   @JsonProperty("infrastructureRef")
   private ObjectReference infrastructureRef;
   @JsonProperty("nodeDeletionTimeout")
   private Duration nodeDeletionTimeout;
   @JsonProperty("nodeDrainTimeout")
   private Duration nodeDrainTimeout;
   @JsonProperty("nodeVolumeDetachTimeout")
   private Duration nodeVolumeDetachTimeout;
   @JsonProperty("providerID")
   private String providerID;
   @JsonProperty("readinessGates")
   @JsonInclude(Include.NON_EMPTY)
   private List readinessGates = new ArrayList();
   @JsonProperty("version")
   private String version;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public MachineSpec() {
   }

   public MachineSpec(Bootstrap bootstrap, String clusterName, String failureDomain, ObjectReference infrastructureRef, Duration nodeDeletionTimeout, Duration nodeDrainTimeout, Duration nodeVolumeDetachTimeout, String providerID, List readinessGates, String version) {
      this.bootstrap = bootstrap;
      this.clusterName = clusterName;
      this.failureDomain = failureDomain;
      this.infrastructureRef = infrastructureRef;
      this.nodeDeletionTimeout = nodeDeletionTimeout;
      this.nodeDrainTimeout = nodeDrainTimeout;
      this.nodeVolumeDetachTimeout = nodeVolumeDetachTimeout;
      this.providerID = providerID;
      this.readinessGates = readinessGates;
      this.version = version;
   }

   @JsonProperty("bootstrap")
   public Bootstrap getBootstrap() {
      return this.bootstrap;
   }

   @JsonProperty("bootstrap")
   public void setBootstrap(Bootstrap bootstrap) {
      this.bootstrap = bootstrap;
   }

   @JsonProperty("clusterName")
   public String getClusterName() {
      return this.clusterName;
   }

   @JsonProperty("clusterName")
   public void setClusterName(String clusterName) {
      this.clusterName = clusterName;
   }

   @JsonProperty("failureDomain")
   public String getFailureDomain() {
      return this.failureDomain;
   }

   @JsonProperty("failureDomain")
   public void setFailureDomain(String failureDomain) {
      this.failureDomain = failureDomain;
   }

   @JsonProperty("infrastructureRef")
   public ObjectReference getInfrastructureRef() {
      return this.infrastructureRef;
   }

   @JsonProperty("infrastructureRef")
   public void setInfrastructureRef(ObjectReference infrastructureRef) {
      this.infrastructureRef = infrastructureRef;
   }

   @JsonProperty("nodeDeletionTimeout")
   public Duration getNodeDeletionTimeout() {
      return this.nodeDeletionTimeout;
   }

   @JsonProperty("nodeDeletionTimeout")
   public void setNodeDeletionTimeout(Duration nodeDeletionTimeout) {
      this.nodeDeletionTimeout = nodeDeletionTimeout;
   }

   @JsonProperty("nodeDrainTimeout")
   public Duration getNodeDrainTimeout() {
      return this.nodeDrainTimeout;
   }

   @JsonProperty("nodeDrainTimeout")
   public void setNodeDrainTimeout(Duration nodeDrainTimeout) {
      this.nodeDrainTimeout = nodeDrainTimeout;
   }

   @JsonProperty("nodeVolumeDetachTimeout")
   public Duration getNodeVolumeDetachTimeout() {
      return this.nodeVolumeDetachTimeout;
   }

   @JsonProperty("nodeVolumeDetachTimeout")
   public void setNodeVolumeDetachTimeout(Duration nodeVolumeDetachTimeout) {
      this.nodeVolumeDetachTimeout = nodeVolumeDetachTimeout;
   }

   @JsonProperty("providerID")
   public String getProviderID() {
      return this.providerID;
   }

   @JsonProperty("providerID")
   public void setProviderID(String providerID) {
      this.providerID = providerID;
   }

   @JsonProperty("readinessGates")
   @JsonInclude(Include.NON_EMPTY)
   public List getReadinessGates() {
      return this.readinessGates;
   }

   @JsonProperty("readinessGates")
   public void setReadinessGates(List readinessGates) {
      this.readinessGates = readinessGates;
   }

   @JsonProperty("version")
   public String getVersion() {
      return this.version;
   }

   @JsonProperty("version")
   public void setVersion(String version) {
      this.version = version;
   }

   @JsonIgnore
   public MachineSpecBuilder edit() {
      return new MachineSpecBuilder(this);
   }

   @JsonIgnore
   public MachineSpecBuilder toBuilder() {
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
      Bootstrap var10000 = this.getBootstrap();
      return "MachineSpec(bootstrap=" + var10000 + ", clusterName=" + this.getClusterName() + ", failureDomain=" + this.getFailureDomain() + ", infrastructureRef=" + this.getInfrastructureRef() + ", nodeDeletionTimeout=" + this.getNodeDeletionTimeout() + ", nodeDrainTimeout=" + this.getNodeDrainTimeout() + ", nodeVolumeDetachTimeout=" + this.getNodeVolumeDetachTimeout() + ", providerID=" + this.getProviderID() + ", readinessGates=" + this.getReadinessGates() + ", version=" + this.getVersion() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MachineSpec)) {
         return false;
      } else {
         MachineSpec other = (MachineSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$bootstrap = this.getBootstrap();
            Object other$bootstrap = other.getBootstrap();
            if (this$bootstrap == null) {
               if (other$bootstrap != null) {
                  return false;
               }
            } else if (!this$bootstrap.equals(other$bootstrap)) {
               return false;
            }

            Object this$clusterName = this.getClusterName();
            Object other$clusterName = other.getClusterName();
            if (this$clusterName == null) {
               if (other$clusterName != null) {
                  return false;
               }
            } else if (!this$clusterName.equals(other$clusterName)) {
               return false;
            }

            Object this$failureDomain = this.getFailureDomain();
            Object other$failureDomain = other.getFailureDomain();
            if (this$failureDomain == null) {
               if (other$failureDomain != null) {
                  return false;
               }
            } else if (!this$failureDomain.equals(other$failureDomain)) {
               return false;
            }

            Object this$infrastructureRef = this.getInfrastructureRef();
            Object other$infrastructureRef = other.getInfrastructureRef();
            if (this$infrastructureRef == null) {
               if (other$infrastructureRef != null) {
                  return false;
               }
            } else if (!this$infrastructureRef.equals(other$infrastructureRef)) {
               return false;
            }

            Object this$nodeDeletionTimeout = this.getNodeDeletionTimeout();
            Object other$nodeDeletionTimeout = other.getNodeDeletionTimeout();
            if (this$nodeDeletionTimeout == null) {
               if (other$nodeDeletionTimeout != null) {
                  return false;
               }
            } else if (!this$nodeDeletionTimeout.equals(other$nodeDeletionTimeout)) {
               return false;
            }

            Object this$nodeDrainTimeout = this.getNodeDrainTimeout();
            Object other$nodeDrainTimeout = other.getNodeDrainTimeout();
            if (this$nodeDrainTimeout == null) {
               if (other$nodeDrainTimeout != null) {
                  return false;
               }
            } else if (!this$nodeDrainTimeout.equals(other$nodeDrainTimeout)) {
               return false;
            }

            Object this$nodeVolumeDetachTimeout = this.getNodeVolumeDetachTimeout();
            Object other$nodeVolumeDetachTimeout = other.getNodeVolumeDetachTimeout();
            if (this$nodeVolumeDetachTimeout == null) {
               if (other$nodeVolumeDetachTimeout != null) {
                  return false;
               }
            } else if (!this$nodeVolumeDetachTimeout.equals(other$nodeVolumeDetachTimeout)) {
               return false;
            }

            Object this$providerID = this.getProviderID();
            Object other$providerID = other.getProviderID();
            if (this$providerID == null) {
               if (other$providerID != null) {
                  return false;
               }
            } else if (!this$providerID.equals(other$providerID)) {
               return false;
            }

            Object this$readinessGates = this.getReadinessGates();
            Object other$readinessGates = other.getReadinessGates();
            if (this$readinessGates == null) {
               if (other$readinessGates != null) {
                  return false;
               }
            } else if (!this$readinessGates.equals(other$readinessGates)) {
               return false;
            }

            Object this$version = this.getVersion();
            Object other$version = other.getVersion();
            if (this$version == null) {
               if (other$version != null) {
                  return false;
               }
            } else if (!this$version.equals(other$version)) {
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
      return other instanceof MachineSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $bootstrap = this.getBootstrap();
      result = result * 59 + ($bootstrap == null ? 43 : $bootstrap.hashCode());
      Object $clusterName = this.getClusterName();
      result = result * 59 + ($clusterName == null ? 43 : $clusterName.hashCode());
      Object $failureDomain = this.getFailureDomain();
      result = result * 59 + ($failureDomain == null ? 43 : $failureDomain.hashCode());
      Object $infrastructureRef = this.getInfrastructureRef();
      result = result * 59 + ($infrastructureRef == null ? 43 : $infrastructureRef.hashCode());
      Object $nodeDeletionTimeout = this.getNodeDeletionTimeout();
      result = result * 59 + ($nodeDeletionTimeout == null ? 43 : $nodeDeletionTimeout.hashCode());
      Object $nodeDrainTimeout = this.getNodeDrainTimeout();
      result = result * 59 + ($nodeDrainTimeout == null ? 43 : $nodeDrainTimeout.hashCode());
      Object $nodeVolumeDetachTimeout = this.getNodeVolumeDetachTimeout();
      result = result * 59 + ($nodeVolumeDetachTimeout == null ? 43 : $nodeVolumeDetachTimeout.hashCode());
      Object $providerID = this.getProviderID();
      result = result * 59 + ($providerID == null ? 43 : $providerID.hashCode());
      Object $readinessGates = this.getReadinessGates();
      result = result * 59 + ($readinessGates == null ? 43 : $readinessGates.hashCode());
      Object $version = this.getVersion();
      result = result * 59 + ($version == null ? 43 : $version.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
