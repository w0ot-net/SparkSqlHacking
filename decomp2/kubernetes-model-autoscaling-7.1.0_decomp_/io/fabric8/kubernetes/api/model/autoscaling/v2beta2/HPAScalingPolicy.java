package io.fabric8.kubernetes.api.model.autoscaling.v2beta2;

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
@JsonPropertyOrder({"periodSeconds", "type", "value"})
public class HPAScalingPolicy implements Editable, KubernetesResource {
   @JsonProperty("periodSeconds")
   private Integer periodSeconds;
   @JsonProperty("type")
   private String type;
   @JsonProperty("value")
   private Integer value;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public HPAScalingPolicy() {
   }

   public HPAScalingPolicy(Integer periodSeconds, String type, Integer value) {
      this.periodSeconds = periodSeconds;
      this.type = type;
      this.value = value;
   }

   @JsonProperty("periodSeconds")
   public Integer getPeriodSeconds() {
      return this.periodSeconds;
   }

   @JsonProperty("periodSeconds")
   public void setPeriodSeconds(Integer periodSeconds) {
      this.periodSeconds = periodSeconds;
   }

   @JsonProperty("type")
   public String getType() {
      return this.type;
   }

   @JsonProperty("type")
   public void setType(String type) {
      this.type = type;
   }

   @JsonProperty("value")
   public Integer getValue() {
      return this.value;
   }

   @JsonProperty("value")
   public void setValue(Integer value) {
      this.value = value;
   }

   @JsonIgnore
   public HPAScalingPolicyBuilder edit() {
      return new HPAScalingPolicyBuilder(this);
   }

   @JsonIgnore
   public HPAScalingPolicyBuilder toBuilder() {
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
      Integer var10000 = this.getPeriodSeconds();
      return "HPAScalingPolicy(periodSeconds=" + var10000 + ", type=" + this.getType() + ", value=" + this.getValue() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof HPAScalingPolicy)) {
         return false;
      } else {
         HPAScalingPolicy other = (HPAScalingPolicy)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$periodSeconds = this.getPeriodSeconds();
            Object other$periodSeconds = other.getPeriodSeconds();
            if (this$periodSeconds == null) {
               if (other$periodSeconds != null) {
                  return false;
               }
            } else if (!this$periodSeconds.equals(other$periodSeconds)) {
               return false;
            }

            Object this$value = this.getValue();
            Object other$value = other.getValue();
            if (this$value == null) {
               if (other$value != null) {
                  return false;
               }
            } else if (!this$value.equals(other$value)) {
               return false;
            }

            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
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
      return other instanceof HPAScalingPolicy;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $periodSeconds = this.getPeriodSeconds();
      result = result * 59 + ($periodSeconds == null ? 43 : $periodSeconds.hashCode());
      Object $value = this.getValue();
      result = result * 59 + ($value == null ? 43 : $value.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
