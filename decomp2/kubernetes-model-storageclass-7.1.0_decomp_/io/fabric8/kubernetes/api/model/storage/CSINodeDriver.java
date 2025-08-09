package io.fabric8.kubernetes.api.model.storage;

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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"allocatable", "name", "nodeID", "topologyKeys"})
public class CSINodeDriver implements Editable, KubernetesResource {
   @JsonProperty("allocatable")
   private VolumeNodeResources allocatable;
   @JsonProperty("name")
   private String name;
   @JsonProperty("nodeID")
   private String nodeID;
   @JsonProperty("topologyKeys")
   @JsonInclude(Include.NON_EMPTY)
   private List topologyKeys = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public CSINodeDriver() {
   }

   public CSINodeDriver(VolumeNodeResources allocatable, String name, String nodeID, List topologyKeys) {
      this.allocatable = allocatable;
      this.name = name;
      this.nodeID = nodeID;
      this.topologyKeys = topologyKeys;
   }

   @JsonProperty("allocatable")
   public VolumeNodeResources getAllocatable() {
      return this.allocatable;
   }

   @JsonProperty("allocatable")
   public void setAllocatable(VolumeNodeResources allocatable) {
      this.allocatable = allocatable;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("nodeID")
   public String getNodeID() {
      return this.nodeID;
   }

   @JsonProperty("nodeID")
   public void setNodeID(String nodeID) {
      this.nodeID = nodeID;
   }

   @JsonProperty("topologyKeys")
   @JsonInclude(Include.NON_EMPTY)
   public List getTopologyKeys() {
      return this.topologyKeys;
   }

   @JsonProperty("topologyKeys")
   public void setTopologyKeys(List topologyKeys) {
      this.topologyKeys = topologyKeys;
   }

   @JsonIgnore
   public CSINodeDriverBuilder edit() {
      return new CSINodeDriverBuilder(this);
   }

   @JsonIgnore
   public CSINodeDriverBuilder toBuilder() {
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
      VolumeNodeResources var10000 = this.getAllocatable();
      return "CSINodeDriver(allocatable=" + var10000 + ", name=" + this.getName() + ", nodeID=" + this.getNodeID() + ", topologyKeys=" + this.getTopologyKeys() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CSINodeDriver)) {
         return false;
      } else {
         CSINodeDriver other = (CSINodeDriver)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$allocatable = this.getAllocatable();
            Object other$allocatable = other.getAllocatable();
            if (this$allocatable == null) {
               if (other$allocatable != null) {
                  return false;
               }
            } else if (!this$allocatable.equals(other$allocatable)) {
               return false;
            }

            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
               return false;
            }

            Object this$nodeID = this.getNodeID();
            Object other$nodeID = other.getNodeID();
            if (this$nodeID == null) {
               if (other$nodeID != null) {
                  return false;
               }
            } else if (!this$nodeID.equals(other$nodeID)) {
               return false;
            }

            Object this$topologyKeys = this.getTopologyKeys();
            Object other$topologyKeys = other.getTopologyKeys();
            if (this$topologyKeys == null) {
               if (other$topologyKeys != null) {
                  return false;
               }
            } else if (!this$topologyKeys.equals(other$topologyKeys)) {
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
      return other instanceof CSINodeDriver;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $allocatable = this.getAllocatable();
      result = result * 59 + ($allocatable == null ? 43 : $allocatable.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $nodeID = this.getNodeID();
      result = result * 59 + ($nodeID == null ? 43 : $nodeID.hashCode());
      Object $topologyKeys = this.getTopologyKeys();
      result = result * 59 + ($topologyKeys == null ? 43 : $topologyKeys.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
