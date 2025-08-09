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
@JsonPropertyOrder({"currentAverageValue", "currentValue", "metricName", "metricSelector"})
public class ExternalMetricStatus implements Editable, KubernetesResource {
   @JsonProperty("currentAverageValue")
   private Quantity currentAverageValue;
   @JsonProperty("currentValue")
   private Quantity currentValue;
   @JsonProperty("metricName")
   private String metricName;
   @JsonProperty("metricSelector")
   private LabelSelector metricSelector;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ExternalMetricStatus() {
   }

   public ExternalMetricStatus(Quantity currentAverageValue, Quantity currentValue, String metricName, LabelSelector metricSelector) {
      this.currentAverageValue = currentAverageValue;
      this.currentValue = currentValue;
      this.metricName = metricName;
      this.metricSelector = metricSelector;
   }

   @JsonProperty("currentAverageValue")
   public Quantity getCurrentAverageValue() {
      return this.currentAverageValue;
   }

   @JsonProperty("currentAverageValue")
   public void setCurrentAverageValue(Quantity currentAverageValue) {
      this.currentAverageValue = currentAverageValue;
   }

   @JsonProperty("currentValue")
   public Quantity getCurrentValue() {
      return this.currentValue;
   }

   @JsonProperty("currentValue")
   public void setCurrentValue(Quantity currentValue) {
      this.currentValue = currentValue;
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

   @JsonIgnore
   public ExternalMetricStatusBuilder edit() {
      return new ExternalMetricStatusBuilder(this);
   }

   @JsonIgnore
   public ExternalMetricStatusBuilder toBuilder() {
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
      Quantity var10000 = this.getCurrentAverageValue();
      return "ExternalMetricStatus(currentAverageValue=" + var10000 + ", currentValue=" + this.getCurrentValue() + ", metricName=" + this.getMetricName() + ", metricSelector=" + this.getMetricSelector() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ExternalMetricStatus)) {
         return false;
      } else {
         ExternalMetricStatus other = (ExternalMetricStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$currentAverageValue = this.getCurrentAverageValue();
            Object other$currentAverageValue = other.getCurrentAverageValue();
            if (this$currentAverageValue == null) {
               if (other$currentAverageValue != null) {
                  return false;
               }
            } else if (!this$currentAverageValue.equals(other$currentAverageValue)) {
               return false;
            }

            Object this$currentValue = this.getCurrentValue();
            Object other$currentValue = other.getCurrentValue();
            if (this$currentValue == null) {
               if (other$currentValue != null) {
                  return false;
               }
            } else if (!this$currentValue.equals(other$currentValue)) {
               return false;
            }

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
      return other instanceof ExternalMetricStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $currentAverageValue = this.getCurrentAverageValue();
      result = result * 59 + ($currentAverageValue == null ? 43 : $currentAverageValue.hashCode());
      Object $currentValue = this.getCurrentValue();
      result = result * 59 + ($currentValue == null ? 43 : $currentValue.hashCode());
      Object $metricName = this.getMetricName();
      result = result * 59 + ($metricName == null ? 43 : $metricName.hashCode());
      Object $metricSelector = this.getMetricSelector();
      result = result * 59 + ($metricSelector == null ? 43 : $metricSelector.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
