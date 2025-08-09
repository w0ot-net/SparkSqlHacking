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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"adminAccess", "allocationMode", "count", "deviceClassName", "name", "selectors"})
public class DeviceRequest implements Editable, KubernetesResource {
   @JsonProperty("adminAccess")
   private Boolean adminAccess;
   @JsonProperty("allocationMode")
   private String allocationMode;
   @JsonProperty("count")
   private Long count;
   @JsonProperty("deviceClassName")
   private String deviceClassName;
   @JsonProperty("name")
   private String name;
   @JsonProperty("selectors")
   @JsonInclude(Include.NON_EMPTY)
   private List selectors = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public DeviceRequest() {
   }

   public DeviceRequest(Boolean adminAccess, String allocationMode, Long count, String deviceClassName, String name, List selectors) {
      this.adminAccess = adminAccess;
      this.allocationMode = allocationMode;
      this.count = count;
      this.deviceClassName = deviceClassName;
      this.name = name;
      this.selectors = selectors;
   }

   @JsonProperty("adminAccess")
   public Boolean getAdminAccess() {
      return this.adminAccess;
   }

   @JsonProperty("adminAccess")
   public void setAdminAccess(Boolean adminAccess) {
      this.adminAccess = adminAccess;
   }

   @JsonProperty("allocationMode")
   public String getAllocationMode() {
      return this.allocationMode;
   }

   @JsonProperty("allocationMode")
   public void setAllocationMode(String allocationMode) {
      this.allocationMode = allocationMode;
   }

   @JsonProperty("count")
   public Long getCount() {
      return this.count;
   }

   @JsonProperty("count")
   public void setCount(Long count) {
      this.count = count;
   }

   @JsonProperty("deviceClassName")
   public String getDeviceClassName() {
      return this.deviceClassName;
   }

   @JsonProperty("deviceClassName")
   public void setDeviceClassName(String deviceClassName) {
      this.deviceClassName = deviceClassName;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("selectors")
   @JsonInclude(Include.NON_EMPTY)
   public List getSelectors() {
      return this.selectors;
   }

   @JsonProperty("selectors")
   public void setSelectors(List selectors) {
      this.selectors = selectors;
   }

   @JsonIgnore
   public DeviceRequestBuilder edit() {
      return new DeviceRequestBuilder(this);
   }

   @JsonIgnore
   public DeviceRequestBuilder toBuilder() {
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
      return "DeviceRequest(adminAccess=" + var10000 + ", allocationMode=" + this.getAllocationMode() + ", count=" + this.getCount() + ", deviceClassName=" + this.getDeviceClassName() + ", name=" + this.getName() + ", selectors=" + this.getSelectors() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DeviceRequest)) {
         return false;
      } else {
         DeviceRequest other = (DeviceRequest)o;
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

            Object this$count = this.getCount();
            Object other$count = other.getCount();
            if (this$count == null) {
               if (other$count != null) {
                  return false;
               }
            } else if (!this$count.equals(other$count)) {
               return false;
            }

            Object this$allocationMode = this.getAllocationMode();
            Object other$allocationMode = other.getAllocationMode();
            if (this$allocationMode == null) {
               if (other$allocationMode != null) {
                  return false;
               }
            } else if (!this$allocationMode.equals(other$allocationMode)) {
               return false;
            }

            Object this$deviceClassName = this.getDeviceClassName();
            Object other$deviceClassName = other.getDeviceClassName();
            if (this$deviceClassName == null) {
               if (other$deviceClassName != null) {
                  return false;
               }
            } else if (!this$deviceClassName.equals(other$deviceClassName)) {
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

            Object this$selectors = this.getSelectors();
            Object other$selectors = other.getSelectors();
            if (this$selectors == null) {
               if (other$selectors != null) {
                  return false;
               }
            } else if (!this$selectors.equals(other$selectors)) {
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
      return other instanceof DeviceRequest;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $adminAccess = this.getAdminAccess();
      result = result * 59 + ($adminAccess == null ? 43 : $adminAccess.hashCode());
      Object $count = this.getCount();
      result = result * 59 + ($count == null ? 43 : $count.hashCode());
      Object $allocationMode = this.getAllocationMode();
      result = result * 59 + ($allocationMode == null ? 43 : $allocationMode.hashCode());
      Object $deviceClassName = this.getDeviceClassName();
      result = result * 59 + ($deviceClassName == null ? 43 : $deviceClassName.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $selectors = this.getSelectors();
      result = result * 59 + ($selectors == null ? 43 : $selectors.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
