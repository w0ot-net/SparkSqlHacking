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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"hardwareAddress", "interfaceName", "ips"})
public class NetworkDeviceData implements Editable, KubernetesResource {
   @JsonProperty("hardwareAddress")
   private String hardwareAddress;
   @JsonProperty("interfaceName")
   private String interfaceName;
   @JsonProperty("ips")
   @JsonInclude(Include.NON_EMPTY)
   private List ips = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public NetworkDeviceData() {
   }

   public NetworkDeviceData(String hardwareAddress, String interfaceName, List ips) {
      this.hardwareAddress = hardwareAddress;
      this.interfaceName = interfaceName;
      this.ips = ips;
   }

   @JsonProperty("hardwareAddress")
   public String getHardwareAddress() {
      return this.hardwareAddress;
   }

   @JsonProperty("hardwareAddress")
   public void setHardwareAddress(String hardwareAddress) {
      this.hardwareAddress = hardwareAddress;
   }

   @JsonProperty("interfaceName")
   public String getInterfaceName() {
      return this.interfaceName;
   }

   @JsonProperty("interfaceName")
   public void setInterfaceName(String interfaceName) {
      this.interfaceName = interfaceName;
   }

   @JsonProperty("ips")
   @JsonInclude(Include.NON_EMPTY)
   public List getIps() {
      return this.ips;
   }

   @JsonProperty("ips")
   public void setIps(List ips) {
      this.ips = ips;
   }

   @JsonIgnore
   public NetworkDeviceDataBuilder edit() {
      return new NetworkDeviceDataBuilder(this);
   }

   @JsonIgnore
   public NetworkDeviceDataBuilder toBuilder() {
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
      String var10000 = this.getHardwareAddress();
      return "NetworkDeviceData(hardwareAddress=" + var10000 + ", interfaceName=" + this.getInterfaceName() + ", ips=" + this.getIps() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NetworkDeviceData)) {
         return false;
      } else {
         NetworkDeviceData other = (NetworkDeviceData)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$hardwareAddress = this.getHardwareAddress();
            Object other$hardwareAddress = other.getHardwareAddress();
            if (this$hardwareAddress == null) {
               if (other$hardwareAddress != null) {
                  return false;
               }
            } else if (!this$hardwareAddress.equals(other$hardwareAddress)) {
               return false;
            }

            Object this$interfaceName = this.getInterfaceName();
            Object other$interfaceName = other.getInterfaceName();
            if (this$interfaceName == null) {
               if (other$interfaceName != null) {
                  return false;
               }
            } else if (!this$interfaceName.equals(other$interfaceName)) {
               return false;
            }

            Object this$ips = this.getIps();
            Object other$ips = other.getIps();
            if (this$ips == null) {
               if (other$ips != null) {
                  return false;
               }
            } else if (!this$ips.equals(other$ips)) {
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
      return other instanceof NetworkDeviceData;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $hardwareAddress = this.getHardwareAddress();
      result = result * 59 + ($hardwareAddress == null ? 43 : $hardwareAddress.hashCode());
      Object $interfaceName = this.getInterfaceName();
      result = result * 59 + ($interfaceName == null ? 43 : $interfaceName.hashCode());
      Object $ips = this.getIps();
      result = result * 59 + ($ips == null ? 43 : $ips.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
