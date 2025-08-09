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
import io.fabric8.kubernetes.api.model.KubernetesResource;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"adminAccess", "device", "driver", "pool", "request"})
public class DeviceRequestAllocationResult implements Editable, KubernetesResource {
   @JsonProperty("adminAccess")
   private Boolean adminAccess;
   @JsonProperty("device")
   private String device;
   @JsonProperty("driver")
   private String driver;
   @JsonProperty("pool")
   private String pool;
   @JsonProperty("request")
   private String request;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public DeviceRequestAllocationResult() {
   }

   public DeviceRequestAllocationResult(Boolean adminAccess, String device, String driver, String pool, String request) {
      this.adminAccess = adminAccess;
      this.device = device;
      this.driver = driver;
      this.pool = pool;
      this.request = request;
   }

   @JsonProperty("adminAccess")
   public Boolean getAdminAccess() {
      return this.adminAccess;
   }

   @JsonProperty("adminAccess")
   public void setAdminAccess(Boolean adminAccess) {
      this.adminAccess = adminAccess;
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

   @JsonProperty("pool")
   public String getPool() {
      return this.pool;
   }

   @JsonProperty("pool")
   public void setPool(String pool) {
      this.pool = pool;
   }

   @JsonProperty("request")
   public String getRequest() {
      return this.request;
   }

   @JsonProperty("request")
   public void setRequest(String request) {
      this.request = request;
   }

   @JsonIgnore
   public DeviceRequestAllocationResultBuilder edit() {
      return new DeviceRequestAllocationResultBuilder(this);
   }

   @JsonIgnore
   public DeviceRequestAllocationResultBuilder toBuilder() {
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
      Boolean var10000 = this.getAdminAccess();
      return "DeviceRequestAllocationResult(adminAccess=" + var10000 + ", device=" + this.getDevice() + ", driver=" + this.getDriver() + ", pool=" + this.getPool() + ", request=" + this.getRequest() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DeviceRequestAllocationResult)) {
         return false;
      } else {
         DeviceRequestAllocationResult other = (DeviceRequestAllocationResult)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$adminAccess = this.getAdminAccess();
            Object other$adminAccess = other.getAdminAccess();
            if (this$adminAccess == null) {
               if (other$adminAccess != null) {
                  return false;
               }
            } else if (!this$adminAccess.equals(other$adminAccess)) {
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

            Object this$pool = this.getPool();
            Object other$pool = other.getPool();
            if (this$pool == null) {
               if (other$pool != null) {
                  return false;
               }
            } else if (!this$pool.equals(other$pool)) {
               return false;
            }

            Object this$request = this.getRequest();
            Object other$request = other.getRequest();
            if (this$request == null) {
               if (other$request != null) {
                  return false;
               }
            } else if (!this$request.equals(other$request)) {
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
      return other instanceof DeviceRequestAllocationResult;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $adminAccess = this.getAdminAccess();
      result = result * 59 + ($adminAccess == null ? 43 : $adminAccess.hashCode());
      Object $device = this.getDevice();
      result = result * 59 + ($device == null ? 43 : $device.hashCode());
      Object $driver = this.getDriver();
      result = result * 59 + ($driver == null ? 43 : $driver.hashCode());
      Object $pool = this.getPool();
      result = result * 59 + ($pool == null ? 43 : $pool.hashCode());
      Object $request = this.getRequest();
      result = result * 59 + ($request == null ? 43 : $request.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
