package io.fabric8.kubernetes.api.model.policy.v1beta1;

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
@JsonPropertyOrder({"apiVersion", "kind", "metadata", "max", "min"})
public class HostPortRange implements Editable, KubernetesResource {
   @JsonProperty("max")
   private Integer max;
   @JsonProperty("min")
   private Integer min;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public HostPortRange() {
   }

   public HostPortRange(Integer max, Integer min) {
      this.max = max;
      this.min = min;
   }

   @JsonProperty("max")
   public Integer getMax() {
      return this.max;
   }

   @JsonProperty("max")
   public void setMax(Integer max) {
      this.max = max;
   }

   @JsonProperty("min")
   public Integer getMin() {
      return this.min;
   }

   @JsonProperty("min")
   public void setMin(Integer min) {
      this.min = min;
   }

   @JsonIgnore
   public HostPortRangeBuilder edit() {
      return new HostPortRangeBuilder(this);
   }

   @JsonIgnore
   public HostPortRangeBuilder toBuilder() {
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

   @Generated
   public String toString() {
      Integer var10000 = this.getMax();
      return "HostPortRange(max=" + var10000 + ", min=" + this.getMin() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof HostPortRange)) {
         return false;
      } else {
         HostPortRange other = (HostPortRange)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$max = this.getMax();
            Object other$max = other.getMax();
            if (this$max == null) {
               if (other$max != null) {
                  return false;
               }
            } else if (!this$max.equals(other$max)) {
               return false;
            }

            Object this$min = this.getMin();
            Object other$min = other.getMin();
            if (this$min == null) {
               if (other$min != null) {
                  return false;
               }
            } else if (!this$min.equals(other$min)) {
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
      return other instanceof HostPortRange;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $max = this.getMax();
      result = result * 59 + ($max == null ? 43 : $max.hashCode());
      Object $min = this.getMin();
      result = result * 59 + ($min == null ? 43 : $min.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }

   @JsonIgnore
   @Generated
   public void setAdditionalProperties(Map additionalProperties) {
      this.additionalProperties = additionalProperties;
   }
}
