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
@JsonPropertyOrder({"opaque", "requests", "source"})
public class DeviceAllocationConfiguration implements Editable, KubernetesResource {
   @JsonProperty("opaque")
   private OpaqueDeviceConfiguration opaque;
   @JsonProperty("requests")
   @JsonInclude(Include.NON_EMPTY)
   private List requests = new ArrayList();
   @JsonProperty("source")
   private String source;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public DeviceAllocationConfiguration() {
   }

   public DeviceAllocationConfiguration(OpaqueDeviceConfiguration opaque, List requests, String source) {
      this.opaque = opaque;
      this.requests = requests;
      this.source = source;
   }

   @JsonProperty("opaque")
   public OpaqueDeviceConfiguration getOpaque() {
      return this.opaque;
   }

   @JsonProperty("opaque")
   public void setOpaque(OpaqueDeviceConfiguration opaque) {
      this.opaque = opaque;
   }

   @JsonProperty("requests")
   @JsonInclude(Include.NON_EMPTY)
   public List getRequests() {
      return this.requests;
   }

   @JsonProperty("requests")
   public void setRequests(List requests) {
      this.requests = requests;
   }

   @JsonProperty("source")
   public String getSource() {
      return this.source;
   }

   @JsonProperty("source")
   public void setSource(String source) {
      this.source = source;
   }

   @JsonIgnore
   public DeviceAllocationConfigurationBuilder edit() {
      return new DeviceAllocationConfigurationBuilder(this);
   }

   @JsonIgnore
   public DeviceAllocationConfigurationBuilder toBuilder() {
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
      OpaqueDeviceConfiguration var10000 = this.getOpaque();
      return "DeviceAllocationConfiguration(opaque=" + var10000 + ", requests=" + this.getRequests() + ", source=" + this.getSource() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DeviceAllocationConfiguration)) {
         return false;
      } else {
         DeviceAllocationConfiguration other = (DeviceAllocationConfiguration)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$opaque = this.getOpaque();
            Object other$opaque = other.getOpaque();
            if (this$opaque == null) {
               if (other$opaque != null) {
                  return false;
               }
            } else if (!this$opaque.equals(other$opaque)) {
               return false;
            }

            Object this$requests = this.getRequests();
            Object other$requests = other.getRequests();
            if (this$requests == null) {
               if (other$requests != null) {
                  return false;
               }
            } else if (!this$requests.equals(other$requests)) {
               return false;
            }

            Object this$source = this.getSource();
            Object other$source = other.getSource();
            if (this$source == null) {
               if (other$source != null) {
                  return false;
               }
            } else if (!this$source.equals(other$source)) {
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
      return other instanceof DeviceAllocationConfiguration;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $opaque = this.getOpaque();
      result = result * 59 + ($opaque == null ? 43 : $opaque.hashCode());
      Object $requests = this.getRequests();
      result = result * 59 + ($requests == null ? 43 : $requests.hashCode());
      Object $source = this.getSource();
      result = result * 59 + ($source == null ? 43 : $source.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
