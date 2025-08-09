package io.fabric8.kubernetes.api.model.resource.v1alpha2;

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
@JsonPropertyOrder({"driverName", "requests", "vendorParameters"})
public class DriverRequests implements Editable, KubernetesResource {
   @JsonProperty("driverName")
   private String driverName;
   @JsonProperty("requests")
   @JsonInclude(Include.NON_EMPTY)
   private List requests = new ArrayList();
   @JsonProperty("vendorParameters")
   @JsonDeserialize(
      using = KubernetesDeserializer.class
   )
   private Object vendorParameters;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public DriverRequests() {
   }

   public DriverRequests(String driverName, List requests, Object vendorParameters) {
      this.driverName = driverName;
      this.requests = requests;
      this.vendorParameters = vendorParameters;
   }

   @JsonProperty("driverName")
   public String getDriverName() {
      return this.driverName;
   }

   @JsonProperty("driverName")
   public void setDriverName(String driverName) {
      this.driverName = driverName;
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

   @JsonProperty("vendorParameters")
   public Object getVendorParameters() {
      return this.vendorParameters;
   }

   @JsonProperty("vendorParameters")
   @JsonDeserialize(
      using = KubernetesDeserializer.class
   )
   public void setVendorParameters(Object vendorParameters) {
      this.vendorParameters = vendorParameters;
   }

   @JsonIgnore
   public DriverRequestsBuilder edit() {
      return new DriverRequestsBuilder(this);
   }

   @JsonIgnore
   public DriverRequestsBuilder toBuilder() {
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
      String var10000 = this.getDriverName();
      return "DriverRequests(driverName=" + var10000 + ", requests=" + this.getRequests() + ", vendorParameters=" + this.getVendorParameters() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DriverRequests)) {
         return false;
      } else {
         DriverRequests other = (DriverRequests)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$driverName = this.getDriverName();
            Object other$driverName = other.getDriverName();
            if (this$driverName == null) {
               if (other$driverName != null) {
                  return false;
               }
            } else if (!this$driverName.equals(other$driverName)) {
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

            Object this$vendorParameters = this.getVendorParameters();
            Object other$vendorParameters = other.getVendorParameters();
            if (this$vendorParameters == null) {
               if (other$vendorParameters != null) {
                  return false;
               }
            } else if (!this$vendorParameters.equals(other$vendorParameters)) {
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
      return other instanceof DriverRequests;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $driverName = this.getDriverName();
      result = result * 59 + ($driverName == null ? 43 : $driverName.hashCode());
      Object $requests = this.getRequests();
      result = result * 59 + ($requests == null ? 43 : $requests.hashCode());
      Object $vendorParameters = this.getVendorParameters();
      result = result * 59 + ($vendorParameters == null ? 43 : $vendorParameters.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
