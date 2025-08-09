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
@JsonPropertyOrder({"drivers"})
public class CSINodeSpec implements Editable, KubernetesResource {
   @JsonProperty("drivers")
   @JsonInclude(Include.NON_EMPTY)
   private List drivers = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public CSINodeSpec() {
   }

   public CSINodeSpec(List drivers) {
      this.drivers = drivers;
   }

   @JsonProperty("drivers")
   @JsonInclude(Include.NON_EMPTY)
   public List getDrivers() {
      return this.drivers;
   }

   @JsonProperty("drivers")
   public void setDrivers(List drivers) {
      this.drivers = drivers;
   }

   @JsonIgnore
   public CSINodeSpecBuilder edit() {
      return new CSINodeSpecBuilder(this);
   }

   @JsonIgnore
   public CSINodeSpecBuilder toBuilder() {
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
      List var10000 = this.getDrivers();
      return "CSINodeSpec(drivers=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CSINodeSpec)) {
         return false;
      } else {
         CSINodeSpec other = (CSINodeSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$drivers = this.getDrivers();
            Object other$drivers = other.getDrivers();
            if (this$drivers == null) {
               if (other$drivers != null) {
                  return false;
               }
            } else if (!this$drivers.equals(other$drivers)) {
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
      return other instanceof CSINodeSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $drivers = this.getDrivers();
      result = result * 59 + ($drivers == null ? 43 : $drivers.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
