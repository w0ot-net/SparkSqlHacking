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
@JsonPropertyOrder({"currentAverageValue", "metricName", "selector"})
public class PodsMetricStatus implements Editable, KubernetesResource {
   @JsonProperty("currentAverageValue")
   private Quantity currentAverageValue;
   @JsonProperty("metricName")
   private String metricName;
   @JsonProperty("selector")
   private LabelSelector selector;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PodsMetricStatus() {
   }

   public PodsMetricStatus(Quantity currentAverageValue, String metricName, LabelSelector selector) {
      this.currentAverageValue = currentAverageValue;
      this.metricName = metricName;
      this.selector = selector;
   }

   @JsonProperty("currentAverageValue")
   public Quantity getCurrentAverageValue() {
      return this.currentAverageValue;
   }

   @JsonProperty("currentAverageValue")
   public void setCurrentAverageValue(Quantity currentAverageValue) {
      this.currentAverageValue = currentAverageValue;
   }

   @JsonProperty("metricName")
   public String getMetricName() {
      return this.metricName;
   }

   @JsonProperty("metricName")
   public void setMetricName(String metricName) {
      this.metricName = metricName;
   }

   @JsonProperty("selector")
   public LabelSelector getSelector() {
      return this.selector;
   }

   @JsonProperty("selector")
   public void setSelector(LabelSelector selector) {
      this.selector = selector;
   }

   @JsonIgnore
   public PodsMetricStatusBuilder edit() {
      return new PodsMetricStatusBuilder(this);
   }

   @JsonIgnore
   public PodsMetricStatusBuilder toBuilder() {
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
      return "PodsMetricStatus(currentAverageValue=" + var10000 + ", metricName=" + this.getMetricName() + ", selector=" + this.getSelector() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PodsMetricStatus)) {
         return false;
      } else {
         PodsMetricStatus other = (PodsMetricStatus)o;
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

            Object this$metricName = this.getMetricName();
            Object other$metricName = other.getMetricName();
            if (this$metricName == null) {
               if (other$metricName != null) {
                  return false;
               }
            } else if (!this$metricName.equals(other$metricName)) {
               return false;
            }

            Object this$selector = this.getSelector();
            Object other$selector = other.getSelector();
            if (this$selector == null) {
               if (other$selector != null) {
                  return false;
               }
            } else if (!this$selector.equals(other$selector)) {
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
      return other instanceof PodsMetricStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $currentAverageValue = this.getCurrentAverageValue();
      result = result * 59 + ($currentAverageValue == null ? 43 : $currentAverageValue.hashCode());
      Object $metricName = this.getMetricName();
      result = result * 59 + ($metricName == null ? 43 : $metricName.hashCode());
      Object $selector = this.getSelector();
      result = result * 59 + ($selector == null ? 43 : $selector.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
