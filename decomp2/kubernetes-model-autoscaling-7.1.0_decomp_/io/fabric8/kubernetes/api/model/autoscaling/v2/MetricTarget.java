package io.fabric8.kubernetes.api.model.autoscaling.v2;

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
import io.fabric8.kubernetes.api.model.Quantity;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"averageUtilization", "averageValue", "type", "value"})
public class MetricTarget implements Editable, KubernetesResource {
   @JsonProperty("averageUtilization")
   private Integer averageUtilization;
   @JsonProperty("averageValue")
   private Quantity averageValue;
   @JsonProperty("type")
   private String type;
   @JsonProperty("value")
   private Quantity value;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public MetricTarget() {
   }

   public MetricTarget(Integer averageUtilization, Quantity averageValue, String type, Quantity value) {
      this.averageUtilization = averageUtilization;
      this.averageValue = averageValue;
      this.type = type;
      this.value = value;
   }

   @JsonProperty("averageUtilization")
   public Integer getAverageUtilization() {
      return this.averageUtilization;
   }

   @JsonProperty("averageUtilization")
   public void setAverageUtilization(Integer averageUtilization) {
      this.averageUtilization = averageUtilization;
   }

   @JsonProperty("averageValue")
   public Quantity getAverageValue() {
      return this.averageValue;
   }

   @JsonProperty("averageValue")
   public void setAverageValue(Quantity averageValue) {
      this.averageValue = averageValue;
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
   public Quantity getValue() {
      return this.value;
   }

   @JsonProperty("value")
   public void setValue(Quantity value) {
      this.value = value;
   }

   @JsonIgnore
   public MetricTargetBuilder edit() {
      return new MetricTargetBuilder(this);
   }

   @JsonIgnore
   public MetricTargetBuilder toBuilder() {
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
      Integer var10000 = this.getAverageUtilization();
      return "MetricTarget(averageUtilization=" + var10000 + ", averageValue=" + this.getAverageValue() + ", type=" + this.getType() + ", value=" + this.getValue() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MetricTarget)) {
         return false;
      } else {
         MetricTarget other = (MetricTarget)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$averageUtilization = this.getAverageUtilization();
            Object other$averageUtilization = other.getAverageUtilization();
            if (this$averageUtilization == null) {
               if (other$averageUtilization != null) {
                  return false;
               }
            } else if (!this$averageUtilization.equals(other$averageUtilization)) {
               return false;
            }

            Object this$averageValue = this.getAverageValue();
            Object other$averageValue = other.getAverageValue();
            if (this$averageValue == null) {
               if (other$averageValue != null) {
                  return false;
               }
            } else if (!this$averageValue.equals(other$averageValue)) {
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

            Object this$value = this.getValue();
            Object other$value = other.getValue();
            if (this$value == null) {
               if (other$value != null) {
                  return false;
               }
            } else if (!this$value.equals(other$value)) {
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
      return other instanceof MetricTarget;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $averageUtilization = this.getAverageUtilization();
      result = result * 59 + ($averageUtilization == null ? 43 : $averageUtilization.hashCode());
      Object $averageValue = this.getAverageValue();
      result = result * 59 + ($averageValue == null ? 43 : $averageValue.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $value = this.getValue();
      result = result * 59 + ($value == null ? 43 : $value.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
