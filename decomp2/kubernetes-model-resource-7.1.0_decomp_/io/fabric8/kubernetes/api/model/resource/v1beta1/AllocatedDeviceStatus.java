package io.fabric8.kubernetes.api.model.resource.v1beta1;

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
import io.fabric8.kubernetes.api.model.Condition;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.fabric8.kubernetes.internal.KubernetesDeserializer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"conditions", "data", "device", "driver", "networkData", "pool"})
public class AllocatedDeviceStatus implements Editable, KubernetesResource {
   @JsonProperty("conditions")
   @JsonInclude(Include.NON_EMPTY)
   private List conditions = new ArrayList();
   @JsonProperty("data")
   @JsonDeserialize(
      using = KubernetesDeserializer.class
   )
   private Object data;
   @JsonProperty("device")
   private String device;
   @JsonProperty("driver")
   private String driver;
   @JsonProperty("networkData")
   private NetworkDeviceData networkData;
   @JsonProperty("pool")
   private String pool;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public AllocatedDeviceStatus() {
   }

   public AllocatedDeviceStatus(List conditions, Object data, String device, String driver, NetworkDeviceData networkData, String pool) {
      this.conditions = conditions;
      this.data = data;
      this.device = device;
      this.driver = driver;
      this.networkData = networkData;
      this.pool = pool;
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

   @JsonProperty("data")
   public Object getData() {
      return this.data;
   }

   @JsonProperty("data")
   @JsonDeserialize(
      using = KubernetesDeserializer.class
   )
   public void setData(Object data) {
      this.data = data;
   }

   @JsonProperty("device")
   public String getDevice() {
      return this.device;
   }

   @JsonProperty("device")
   public void setDevice(String device) {
      this.device = device;
   }

   @JsonProperty("driver")
   public String getDriver() {
      return this.driver;
   }

   @JsonProperty("driver")
   public void setDriver(String driver) {
      this.driver = driver;
   }

   @JsonProperty("networkData")
   public NetworkDeviceData getNetworkData() {
      return this.networkData;
   }

   @JsonProperty("networkData")
   public void setNetworkData(NetworkDeviceData networkData) {
      this.networkData = networkData;
   }

   @JsonProperty("pool")
   public String getPool() {
      return this.pool;
   }

   @JsonProperty("pool")
   public void setPool(String pool) {
      this.pool = pool;
   }

   @JsonIgnore
   public AllocatedDeviceStatusBuilder edit() {
      return new AllocatedDeviceStatusBuilder(this);
   }

   @JsonIgnore
   public AllocatedDeviceStatusBuilder toBuilder() {
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
      List var10000 = this.getConditions();
      return "AllocatedDeviceStatus(conditions=" + var10000 + ", data=" + this.getData() + ", device=" + this.getDevice() + ", driver=" + this.getDriver() + ", networkData=" + this.getNetworkData() + ", pool=" + this.getPool() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AllocatedDeviceStatus)) {
         return false;
      } else {
         AllocatedDeviceStatus other = (AllocatedDeviceStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$conditions = this.getConditions();
            Object other$conditions = other.getConditions();
            if (this$conditions == null) {
               if (other$conditions != null) {
                  return false;
               }
            } else if (!this$conditions.equals(other$conditions)) {
               return false;
            }

            Object this$data = this.getData();
            Object other$data = other.getData();
            if (this$data == null) {
               if (other$data != null) {
                  return false;
               }
            } else if (!this$data.equals(other$data)) {
               return false;
            }

            Object this$device = this.getDevice();
            Object other$device = other.getDevice();
            if (this$device == null) {
               if (other$device != null) {
                  return false;
               }
            } else if (!this$device.equals(other$device)) {
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

            Object this$networkData = this.getNetworkData();
            Object other$networkData = other.getNetworkData();
            if (this$networkData == null) {
               if (other$networkData != null) {
                  return false;
               }
            } else if (!this$networkData.equals(other$networkData)) {
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
      return other instanceof AllocatedDeviceStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $conditions = this.getConditions();
      result = result * 59 + ($conditions == null ? 43 : $conditions.hashCode());
      Object $data = this.getData();
      result = result * 59 + ($data == null ? 43 : $data.hashCode());
      Object $device = this.getDevice();
      result = result * 59 + ($device == null ? 43 : $device.hashCode());
      Object $driver = this.getDriver();
      result = result * 59 + ($driver == null ? 43 : $driver.hashCode());
      Object $networkData = this.getNetworkData();
      result = result * 59 + ($networkData == null ? 43 : $networkData.hashCode());
      Object $pool = this.getPool();
      result = result * 59 + ($pool == null ? 43 : $pool.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
