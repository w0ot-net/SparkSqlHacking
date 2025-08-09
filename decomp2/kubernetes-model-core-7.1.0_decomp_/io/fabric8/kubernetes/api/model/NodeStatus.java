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
@JsonPropertyOrder({"addresses", "allocatable", "capacity", "conditions", "config", "daemonEndpoints", "features", "images", "nodeInfo", "phase", "runtimeHandlers", "volumesAttached", "volumesInUse"})
public class NodeStatus implements Editable, KubernetesResource {
   @JsonProperty("addresses")
   @JsonInclude(Include.NON_EMPTY)
   private List addresses = new ArrayList();
   @JsonProperty("allocatable")
   @JsonInclude(Include.NON_EMPTY)
   private Map allocatable = new LinkedHashMap();
   @JsonProperty("capacity")
   @JsonInclude(Include.NON_EMPTY)
   private Map capacity = new LinkedHashMap();
   @JsonProperty("conditions")
   @JsonInclude(Include.NON_EMPTY)
   private List conditions = new ArrayList();
   @JsonProperty("config")
   private NodeConfigStatus config;
   @JsonProperty("daemonEndpoints")
   private NodeDaemonEndpoints daemonEndpoints;
   @JsonProperty("features")
   private NodeFeatures features;
   @JsonProperty("images")
   @JsonInclude(Include.NON_EMPTY)
   private List images = new ArrayList();
   @JsonProperty("nodeInfo")
   private NodeSystemInfo nodeInfo;
   @JsonProperty("phase")
   private String phase;
   @JsonProperty("runtimeHandlers")
   @JsonInclude(Include.NON_EMPTY)
   private List runtimeHandlers = new ArrayList();
   @JsonProperty("volumesAttached")
   @JsonInclude(Include.NON_EMPTY)
   private List volumesAttached = new ArrayList();
   @JsonProperty("volumesInUse")
   @JsonInclude(Include.NON_EMPTY)
   private List volumesInUse = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public NodeStatus() {
   }

   public NodeStatus(List addresses, Map allocatable, Map capacity, List conditions, NodeConfigStatus config, NodeDaemonEndpoints daemonEndpoints, NodeFeatures features, List images, NodeSystemInfo nodeInfo, String phase, List runtimeHandlers, List volumesAttached, List volumesInUse) {
      this.addresses = addresses;
      this.allocatable = allocatable;
      this.capacity = capacity;
      this.conditions = conditions;
      this.config = config;
      this.daemonEndpoints = daemonEndpoints;
      this.features = features;
      this.images = images;
      this.nodeInfo = nodeInfo;
      this.phase = phase;
      this.runtimeHandlers = runtimeHandlers;
      this.volumesAttached = volumesAttached;
      this.volumesInUse = volumesInUse;
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

   @JsonProperty("allocatable")
   @JsonInclude(Include.NON_EMPTY)
   public Map getAllocatable() {
      return this.allocatable;
   }

   @JsonProperty("allocatable")
   public void setAllocatable(Map allocatable) {
      this.allocatable = allocatable;
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

   @JsonProperty("config")
   public NodeConfigStatus getConfig() {
      return this.config;
   }

   @JsonProperty("config")
   public void setConfig(NodeConfigStatus config) {
      this.config = config;
   }

   @JsonProperty("daemonEndpoints")
   public NodeDaemonEndpoints getDaemonEndpoints() {
      return this.daemonEndpoints;
   }

   @JsonProperty("daemonEndpoints")
   public void setDaemonEndpoints(NodeDaemonEndpoints daemonEndpoints) {
      this.daemonEndpoints = daemonEndpoints;
   }

   @JsonProperty("features")
   public NodeFeatures getFeatures() {
      return this.features;
   }

   @JsonProperty("features")
   public void setFeatures(NodeFeatures features) {
      this.features = features;
   }

   @JsonProperty("images")
   @JsonInclude(Include.NON_EMPTY)
   public List getImages() {
      return this.images;
   }

   @JsonProperty("images")
   public void setImages(List images) {
      this.images = images;
   }

   @JsonProperty("nodeInfo")
   public NodeSystemInfo getNodeInfo() {
      return this.nodeInfo;
   }

   @JsonProperty("nodeInfo")
   public void setNodeInfo(NodeSystemInfo nodeInfo) {
      this.nodeInfo = nodeInfo;
   }

   @JsonProperty("phase")
   public String getPhase() {
      return this.phase;
   }

   @JsonProperty("phase")
   public void setPhase(String phase) {
      this.phase = phase;
   }

   @JsonProperty("runtimeHandlers")
   @JsonInclude(Include.NON_EMPTY)
   public List getRuntimeHandlers() {
      return this.runtimeHandlers;
   }

   @JsonProperty("runtimeHandlers")
   public void setRuntimeHandlers(List runtimeHandlers) {
      this.runtimeHandlers = runtimeHandlers;
   }

   @JsonProperty("volumesAttached")
   @JsonInclude(Include.NON_EMPTY)
   public List getVolumesAttached() {
      return this.volumesAttached;
   }

   @JsonProperty("volumesAttached")
   public void setVolumesAttached(List volumesAttached) {
      this.volumesAttached = volumesAttached;
   }

   @JsonProperty("volumesInUse")
   @JsonInclude(Include.NON_EMPTY)
   public List getVolumesInUse() {
      return this.volumesInUse;
   }

   @JsonProperty("volumesInUse")
   public void setVolumesInUse(List volumesInUse) {
      this.volumesInUse = volumesInUse;
   }

   @JsonIgnore
   public NodeStatusBuilder edit() {
      return new NodeStatusBuilder(this);
   }

   @JsonIgnore
   public NodeStatusBuilder toBuilder() {
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
      return "NodeStatus(addresses=" + var10000 + ", allocatable=" + this.getAllocatable() + ", capacity=" + this.getCapacity() + ", conditions=" + this.getConditions() + ", config=" + this.getConfig() + ", daemonEndpoints=" + this.getDaemonEndpoints() + ", features=" + this.getFeatures() + ", images=" + this.getImages() + ", nodeInfo=" + this.getNodeInfo() + ", phase=" + this.getPhase() + ", runtimeHandlers=" + this.getRuntimeHandlers() + ", volumesAttached=" + this.getVolumesAttached() + ", volumesInUse=" + this.getVolumesInUse() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NodeStatus)) {
         return false;
      } else {
         NodeStatus other = (NodeStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$addresses = this.getAddresses();
            Object other$addresses = other.getAddresses();
            if (this$addresses == null) {
               if (other$addresses != null) {
                  return false;
               }
            } else if (!this$addresses.equals(other$addresses)) {
               return false;
            }

            Object this$allocatable = this.getAllocatable();
            Object other$allocatable = other.getAllocatable();
            if (this$allocatable == null) {
               if (other$allocatable != null) {
                  return false;
               }
            } else if (!this$allocatable.equals(other$allocatable)) {
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

            Object this$config = this.getConfig();
            Object other$config = other.getConfig();
            if (this$config == null) {
               if (other$config != null) {
                  return false;
               }
            } else if (!this$config.equals(other$config)) {
               return false;
            }

            Object this$daemonEndpoints = this.getDaemonEndpoints();
            Object other$daemonEndpoints = other.getDaemonEndpoints();
            if (this$daemonEndpoints == null) {
               if (other$daemonEndpoints != null) {
                  return false;
               }
            } else if (!this$daemonEndpoints.equals(other$daemonEndpoints)) {
               return false;
            }

            Object this$features = this.getFeatures();
            Object other$features = other.getFeatures();
            if (this$features == null) {
               if (other$features != null) {
                  return false;
               }
            } else if (!this$features.equals(other$features)) {
               return false;
            }

            Object this$images = this.getImages();
            Object other$images = other.getImages();
            if (this$images == null) {
               if (other$images != null) {
                  return false;
               }
            } else if (!this$images.equals(other$images)) {
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

            Object this$phase = this.getPhase();
            Object other$phase = other.getPhase();
            if (this$phase == null) {
               if (other$phase != null) {
                  return false;
               }
            } else if (!this$phase.equals(other$phase)) {
               return false;
            }

            Object this$runtimeHandlers = this.getRuntimeHandlers();
            Object other$runtimeHandlers = other.getRuntimeHandlers();
            if (this$runtimeHandlers == null) {
               if (other$runtimeHandlers != null) {
                  return false;
               }
            } else if (!this$runtimeHandlers.equals(other$runtimeHandlers)) {
               return false;
            }

            Object this$volumesAttached = this.getVolumesAttached();
            Object other$volumesAttached = other.getVolumesAttached();
            if (this$volumesAttached == null) {
               if (other$volumesAttached != null) {
                  return false;
               }
            } else if (!this$volumesAttached.equals(other$volumesAttached)) {
               return false;
            }

            Object this$volumesInUse = this.getVolumesInUse();
            Object other$volumesInUse = other.getVolumesInUse();
            if (this$volumesInUse == null) {
               if (other$volumesInUse != null) {
                  return false;
               }
            } else if (!this$volumesInUse.equals(other$volumesInUse)) {
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
      return other instanceof NodeStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $addresses = this.getAddresses();
      result = result * 59 + ($addresses == null ? 43 : $addresses.hashCode());
      Object $allocatable = this.getAllocatable();
      result = result * 59 + ($allocatable == null ? 43 : $allocatable.hashCode());
      Object $capacity = this.getCapacity();
      result = result * 59 + ($capacity == null ? 43 : $capacity.hashCode());
      Object $conditions = this.getConditions();
      result = result * 59 + ($conditions == null ? 43 : $conditions.hashCode());
      Object $config = this.getConfig();
      result = result * 59 + ($config == null ? 43 : $config.hashCode());
      Object $daemonEndpoints = this.getDaemonEndpoints();
      result = result * 59 + ($daemonEndpoints == null ? 43 : $daemonEndpoints.hashCode());
      Object $features = this.getFeatures();
      result = result * 59 + ($features == null ? 43 : $features.hashCode());
      Object $images = this.getImages();
      result = result * 59 + ($images == null ? 43 : $images.hashCode());
      Object $nodeInfo = this.getNodeInfo();
      result = result * 59 + ($nodeInfo == null ? 43 : $nodeInfo.hashCode());
      Object $phase = this.getPhase();
      result = result * 59 + ($phase == null ? 43 : $phase.hashCode());
      Object $runtimeHandlers = this.getRuntimeHandlers();
      result = result * 59 + ($runtimeHandlers == null ? 43 : $runtimeHandlers.hashCode());
      Object $volumesAttached = this.getVolumesAttached();
      result = result * 59 + ($volumesAttached == null ? 43 : $volumesAttached.hashCode());
      Object $volumesInUse = this.getVolumesInUse();
      result = result * 59 + ($volumesInUse == null ? 43 : $volumesInUse.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
