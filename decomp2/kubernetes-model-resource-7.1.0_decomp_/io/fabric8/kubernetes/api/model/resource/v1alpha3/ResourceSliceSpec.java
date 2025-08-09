package io.fabric8.kubernetes.api.model.resource.v1alpha3;

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
import io.fabric8.kubernetes.api.model.NodeSelector;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"allNodes", "devices", "driver", "nodeName", "nodeSelector", "pool"})
public class ResourceSliceSpec implements Editable, KubernetesResource {
   @JsonProperty("allNodes")
   private Boolean allNodes;
   @JsonProperty("devices")
   @JsonInclude(Include.NON_EMPTY)
   private List devices = new ArrayList();
   @JsonProperty("driver")
   private String driver;
   @JsonProperty("nodeName")
   private String nodeName;
   @JsonProperty("nodeSelector")
   private NodeSelector nodeSelector;
   @JsonProperty("pool")
   private ResourcePool pool;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ResourceSliceSpec() {
   }

   public ResourceSliceSpec(Boolean allNodes, List devices, String driver, String nodeName, NodeSelector nodeSelector, ResourcePool pool) {
      this.allNodes = allNodes;
      this.devices = devices;
      this.driver = driver;
      this.nodeName = nodeName;
      this.nodeSelector = nodeSelector;
      this.pool = pool;
   }

   @JsonProperty("allNodes")
   public Boolean getAllNodes() {
      return this.allNodes;
   }

   @JsonProperty("allNodes")
   public void setAllNodes(Boolean allNodes) {
      this.allNodes = allNodes;
   }

   @JsonProperty("devices")
   @JsonInclude(Include.NON_EMPTY)
   public List getDevices() {
      return this.devices;
   }

   @JsonProperty("devices")
   public void setDevices(List devices) {
      this.devices = devices;
   }

   @JsonProperty("driver")
   public String getDriver() {
      return this.driver;
   }

   @JsonProperty("driver")
   public void setDriver(String driver) {
      this.driver = driver;
   }

   @JsonProperty("nodeName")
   public String getNodeName() {
      return this.nodeName;
   }

   @JsonProperty("nodeName")
   public void setNodeName(String nodeName) {
      this.nodeName = nodeName;
   }

   @JsonProperty("nodeSelector")
   public NodeSelector getNodeSelector() {
      return this.nodeSelector;
   }

   @JsonProperty("nodeSelector")
   public void setNodeSelector(NodeSelector nodeSelector) {
      this.nodeSelector = nodeSelector;
   }

   @JsonProperty("pool")
   public ResourcePool getPool() {
      return this.pool;
   }

   @JsonProperty("pool")
   public void setPool(ResourcePool pool) {
      this.pool = pool;
   }

   @JsonIgnore
   public ResourceSliceSpecBuilder edit() {
      return new ResourceSliceSpecBuilder(this);
   }

   @JsonIgnore
   public ResourceSliceSpecBuilder toBuilder() {
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
      Boolean var10000 = this.getAllNodes();
      return "ResourceSliceSpec(allNodes=" + var10000 + ", devices=" + this.getDevices() + ", driver=" + this.getDriver() + ", nodeName=" + this.getNodeName() + ", nodeSelector=" + this.getNodeSelector() + ", pool=" + this.getPool() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourceSliceSpec)) {
         return false;
      } else {
         ResourceSliceSpec other = (ResourceSliceSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$allNodes = this.getAllNodes();
            Object other$allNodes = other.getAllNodes();
            if (this$allNodes == null) {
               if (other$allNodes != null) {
                  return false;
               }
            } else if (!this$allNodes.equals(other$allNodes)) {
               return false;
            }

            Object this$devices = this.getDevices();
            Object other$devices = other.getDevices();
            if (this$devices == null) {
               if (other$devices != null) {
                  return false;
               }
            } else if (!this$devices.equals(other$devices)) {
               return false;
            }

            Object this$driver = this.getDriver();
            Object other$driver = other.getDriver();
            if (this$driver == null) {
               if (other$driver != null) {
                  return false;
               }
            } else if (!this$driver.equals(other$driver)) {
               return false;
            }

            Object this$nodeName = this.getNodeName();
            Object other$nodeName = other.getNodeName();
            if (this$nodeName == null) {
               if (other$nodeName != null) {
                  return false;
               }
            } else if (!this$nodeName.equals(other$nodeName)) {
               return false;
            }

            Object this$nodeSelector = this.getNodeSelector();
            Object other$nodeSelector = other.getNodeSelector();
            if (this$nodeSelector == null) {
               if (other$nodeSelector != null) {
                  return false;
               }
            } else if (!this$nodeSelector.equals(other$nodeSelector)) {
               return false;
            }

            Object this$pool = this.getPool();
            Object other$pool = other.getPool();
            if (this$pool == null) {
               if (other$pool != null) {
                  return false;
               }
            } else if (!this$pool.equals(other$pool)) {
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
      return other instanceof ResourceSliceSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $allNodes = this.getAllNodes();
      result = result * 59 + ($allNodes == null ? 43 : $allNodes.hashCode());
      Object $devices = this.getDevices();
      result = result * 59 + ($devices == null ? 43 : $devices.hashCode());
      Object $driver = this.getDriver();
      result = result * 59 + ($driver == null ? 43 : $driver.hashCode());
      Object $nodeName = this.getNodeName();
      result = result * 59 + ($nodeName == null ? 43 : $nodeName.hashCode());
      Object $nodeSelector = this.getNodeSelector();
      result = result * 59 + ($nodeSelector == null ? 43 : $nodeSelector.hashCode());
      Object $pool = this.getPool();
      result = result * 59 + ($pool == null ? 43 : $pool.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
