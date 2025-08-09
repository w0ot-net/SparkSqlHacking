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
import io.fabric8.kubernetes.api.model.LabelSelector;
import io.fabric8.kubernetes.api.model.Quantity;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"metricName", "metricSelector", "targetAverageValue", "targetValue"})
public class ExternalMetricSource implements Editable, KubernetesResource {
   @JsonProperty("metricName")
   private String metricName;
   @JsonProperty("metricSelector")
   private LabelSelector metricSelector;
   @JsonProperty("targetAverageValue")
   private Quantity targetAverageValue;
   @JsonProperty("targetValue")
   private Quantity targetValue;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ExternalMetricSource() {
   }

   public ExternalMetricSource(String metricName, LabelSelector metricSelector, Quantity targetAverageValue, Quantity targetValue) {
      this.metricName = metricName;
      this.metricSelector = metricSelector;
      this.targetAverageValue = targetAverageValue;
      this.targetValue = targetValue;
   }

   @JsonProperty("metricName")
   public String getMetricName() {
      return this.metricName;
   }

   @JsonProperty("metricName")
   public void setMetricName(String metricName) {
      this.metricName = metricName;
   }

   @JsonProperty("metricSelector")
   public LabelSelector getMetricSelector() {
      return this.metricSelector;
   }

   @JsonProperty("metricSelector")
   public void setMetricSelector(LabelSelector metricSelector) {
      this.metricSelector = metricSelector;
   }

   @JsonProperty("targetAverageValue")
   public Quantity getTargetAverageValue() {
      return this.targetAverageValue;
   }

   @JsonProperty("targetAverageValue")
   public void setTargetAverageValue(Quantity targetAverageValue) {
      this.targetAverageValue = targetAverageValue;
   }

   @JsonProperty("targetValue")
   public Quantity getTargetValue() {
      return this.targetValue;
   }

   @JsonProperty("targetValue")
   public void setTargetValue(Quantity targetValue) {
      this.targetValue = targetValue;
   }

   @JsonIgnore
   public ExternalMetricSourceBuilder edit() {
      return new ExternalMetricSourceBuilder(this);
   }

   @JsonIgnore
   public ExternalMetricSourceBuilder toBuilder() {
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
      String var10000 = this.getMetricName();
      return "ExternalMetricSource(metricName=" + var10000 + ", metricSelector=" + this.getMetricSelector() + ", targetAverageValue=" + this.getTargetAverageValue() + ", targetValue=" + this.getTargetValue() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ExternalMetricSource)) {
         return false;
      } else {
         ExternalMetricSource other = (ExternalMetricSource)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$metricName = this.getMetricName();
            Object other$metricName = other.getMetricName();
            if (this$metricName == null) {
               if (other$metricName != null) {
                  return false;
               }
            } else if (!this$metricName.equals(other$metricName)) {
               return false;
            }

            Object this$metricSelector = this.getMetricSelector();
            Object other$metricSelector = other.getMetricSelector();
            if (this$metricSelector == null) {
               if (other$metricSelector != null) {
                  return false;
               }
            } else if (!this$metricSelector.equals(other$metricSelector)) {
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

            Object this$targetValue = this.getTargetValue();
            Object other$targetValue = other.getTargetValue();
            if (this$targetValue == null) {
               if (other$targetValue != null) {
                  return false;
               }
            } else if (!this$targetValue.equals(other$targetValue)) {
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
      return other instanceof ExternalMetricSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $metricName = this.getMetricName();
      result = result * 59 + ($metricName == null ? 43 : $metricName.hashCode());
      Object $metricSelector = this.getMetricSelector();
      result = result * 59 + ($metricSelector == null ? 43 : $metricSelector.hashCode());
      Object $targetAverageValue = this.getTargetAverageValue();
      result = result * 59 + ($targetAverageValue == null ? 43 : $targetAverageValue.hashCode());
      Object $targetValue = this.getTargetValue();
      result = result * 59 + ($targetValue == null ? 43 : $targetValue.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
