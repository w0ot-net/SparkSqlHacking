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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"data", "driverName", "structuredData"})
public class ResourceHandle implements Editable, KubernetesResource {
   @JsonProperty("data")
   private String data;
   @JsonProperty("driverName")
   private String driverName;
   @JsonProperty("structuredData")
   private StructuredResourceHandle structuredData;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ResourceHandle() {
   }

   public ResourceHandle(String data, String driverName, StructuredResourceHandle structuredData) {
      this.data = data;
      this.driverName = driverName;
      this.structuredData = structuredData;
   }

   @JsonProperty("data")
   public String getData() {
      return this.data;
   }

   @JsonProperty("data")
   public void setData(String data) {
      this.data = data;
   }

   @JsonProperty("driverName")
   public String getDriverName() {
      return this.driverName;
   }

   @JsonProperty("driverName")
   public void setDriverName(String driverName) {
      this.driverName = driverName;
   }

   @JsonProperty("structuredData")
   public StructuredResourceHandle getStructuredData() {
      return this.structuredData;
   }

   @JsonProperty("structuredData")
   public void setStructuredData(StructuredResourceHandle structuredData) {
      this.structuredData = structuredData;
   }

   @JsonIgnore
   public ResourceHandleBuilder edit() {
      return new ResourceHandleBuilder(this);
   }

   @JsonIgnore
   public ResourceHandleBuilder toBuilder() {
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
      String var10000 = this.getData();
      return "ResourceHandle(data=" + var10000 + ", driverName=" + this.getDriverName() + ", structuredData=" + this.getStructuredData() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourceHandle)) {
         return false;
      } else {
         ResourceHandle other = (ResourceHandle)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$data = this.getData();
            Object other$data = other.getData();
            if (this$data == null) {
               if (other$data != null) {
                  return false;
               }
            } else if (!this$data.equals(other$data)) {
               return false;
            }

            Object this$driverName = this.getDriverName();
            Object other$driverName = other.getDriverName();
            if (this$driverName == null) {
               if (other$driverName != null) {
                  return false;
               }
            } else if (!this$driverName.equals(other$driverName)) {
               return false;
            }

            Object this$structuredData = this.getStructuredData();
            Object other$structuredData = other.getStructuredData();
            if (this$structuredData == null) {
               if (other$structuredData != null) {
                  return false;
               }
            } else if (!this$structuredData.equals(other$structuredData)) {
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
      return other instanceof ResourceHandle;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $data = this.getData();
      result = result * 59 + ($data == null ? 43 : $data.hashCode());
      Object $driverName = this.getDriverName();
      result = result * 59 + ($driverName == null ? 43 : $driverName.hashCode());
      Object $structuredData = this.getStructuredData();
      result = result * 59 + ($structuredData == null ? 43 : $structuredData.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
