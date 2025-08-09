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
@JsonPropertyOrder({"limits"})
public class LimitRangeSpec implements Editable, KubernetesResource {
   @JsonProperty("limits")
   @JsonInclude(Include.NON_EMPTY)
   private List limits = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public LimitRangeSpec() {
   }

   public LimitRangeSpec(List limits) {
      this.limits = limits;
   }

   @JsonProperty("limits")
   @JsonInclude(Include.NON_EMPTY)
   public List getLimits() {
      return this.limits;
   }

   @JsonProperty("limits")
   public void setLimits(List limits) {
      this.limits = limits;
   }

   @JsonIgnore
   public LimitRangeSpecBuilder edit() {
      return new LimitRangeSpecBuilder(this);
   }

   @JsonIgnore
   public LimitRangeSpecBuilder toBuilder() {
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
      List var10000 = this.getLimits();
      return "LimitRangeSpec(limits=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof LimitRangeSpec)) {
         return false;
      } else {
         LimitRangeSpec other = (LimitRangeSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$limits = this.getLimits();
            Object other$limits = other.getLimits();
            if (this$limits == null) {
               if (other$limits != null) {
                  return false;
               }
            } else if (!this$limits.equals(other$limits)) {
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
      return other instanceof LimitRangeSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $limits = this.getLimits();
      result = result * 59 + ($limits == null ? 43 : $limits.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
