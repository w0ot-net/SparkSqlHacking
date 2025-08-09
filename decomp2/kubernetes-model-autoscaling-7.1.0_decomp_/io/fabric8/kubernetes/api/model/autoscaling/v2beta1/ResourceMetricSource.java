package io.fabric8.kubernetes.api.model.autoscaling.v2beta1;

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
@JsonPropertyOrder({"name", "targetAverageUtilization", "targetAverageValue"})
public class ResourceMetricSource implements Editable, KubernetesResource {
   @JsonProperty("name")
   private String name;
   @JsonProperty("targetAverageUtilization")
   private Integer targetAverageUtilization;
   @JsonProperty("targetAverageValue")
   private Quantity targetAverageValue;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ResourceMetricSource() {
   }

   public ResourceMetricSource(String name, Integer targetAverageUtilization, Quantity targetAverageValue) {
      this.name = name;
      this.targetAverageUtilization = targetAverageUtilization;
      this.targetAverageValue = targetAverageValue;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("targetAverageUtilization")
   public Integer getTargetAverageUtilization() {
      return this.targetAverageUtilization;
   }

   @JsonProperty("targetAverageUtilization")
   public void setTargetAverageUtilization(Integer targetAverageUtilization) {
      this.targetAverageUtilization = targetAverageUtilization;
   }

   @JsonProperty("targetAverageValue")
   public Quantity getTargetAverageValue() {
      return this.targetAverageValue;
   }

   @JsonProperty("targetAverageValue")
   public void setTargetAverageValue(Quantity targetAverageValue) {
      this.targetAverageValue = targetAverageValue;
   }

   @JsonIgnore
   public ResourceMetricSourceBuilder edit() {
      return new ResourceMetricSourceBuilder(this);
   }

   @JsonIgnore
   public ResourceMetricSourceBuilder toBuilder() {
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
      String var10000 = this.getName();
      return "ResourceMetricSource(name=" + var10000 + ", targetAverageUtilization=" + this.getTargetAverageUtilization() + ", targetAverageValue=" + this.getTargetAverageValue() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourceMetricSource)) {
         return false;
      } else {
         ResourceMetricSource other = (ResourceMetricSource)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$targetAverageUtilization = this.getTargetAverageUtilization();
            Object other$targetAverageUtilization = other.getTargetAverageUtilization();
            if (this$targetAverageUtilization == null) {
               if (other$targetAverageUtilization != null) {
                  return false;
               }
            } else if (!this$targetAverageUtilization.equals(other$targetAverageUtilization)) {
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

            Object this$targetAverageValue = this.getTargetAverageValue();
            Object other$targetAverageValue = other.getTargetAverageValue();
            if (this$targetAverageValue == null) {
               if (other$targetAverageValue != null) {
                  return false;
               }
            } else if (!this$targetAverageValue.equals(other$targetAverageValue)) {
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
      return other instanceof ResourceMetricSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $targetAverageUtilization = this.getTargetAverageUtilization();
      result = result * 59 + ($targetAverageUtilization == null ? 43 : $targetAverageUtilization.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $targetAverageValue = this.getTargetAverageValue();
      result = result * 59 + ($targetAverageValue == null ? 43 : $targetAverageValue.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
