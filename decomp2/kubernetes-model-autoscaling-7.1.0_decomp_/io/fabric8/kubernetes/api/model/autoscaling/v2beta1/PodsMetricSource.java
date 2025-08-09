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
@JsonPropertyOrder({"metricName", "selector", "targetAverageValue"})
public class PodsMetricSource implements Editable, KubernetesResource {
   @JsonProperty("metricName")
   private String metricName;
   @JsonProperty("selector")
   private LabelSelector selector;
   @JsonProperty("targetAverageValue")
   private Quantity targetAverageValue;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PodsMetricSource() {
   }

   public PodsMetricSource(String metricName, LabelSelector selector, Quantity targetAverageValue) {
      this.metricName = metricName;
      this.selector = selector;
      this.targetAverageValue = targetAverageValue;
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

   @JsonProperty("targetAverageValue")
   public Quantity getTargetAverageValue() {
      return this.targetAverageValue;
   }

   @JsonProperty("targetAverageValue")
   public void setTargetAverageValue(Quantity targetAverageValue) {
      this.targetAverageValue = targetAverageValue;
   }

   @JsonIgnore
   public PodsMetricSourceBuilder edit() {
      return new PodsMetricSourceBuilder(this);
   }

   @JsonIgnore
   public PodsMetricSourceBuilder toBuilder() {
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
      return "PodsMetricSource(metricName=" + var10000 + ", selector=" + this.getSelector() + ", targetAverageValue=" + this.getTargetAverageValue() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PodsMetricSource)) {
         return false;
      } else {
         PodsMetricSource other = (PodsMetricSource)o;
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

            Object this$selector = this.getSelector();
            Object other$selector = other.getSelector();
            if (this$selector == null) {
               if (other$selector != null) {
                  return false;
               }
            } else if (!this$selector.equals(other$selector)) {
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
      return other instanceof PodsMetricSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $metricName = this.getMetricName();
      result = result * 59 + ($metricName == null ? 43 : $metricName.hashCode());
      Object $selector = this.getSelector();
      result = result * 59 + ($selector == null ? 43 : $selector.hashCode());
      Object $targetAverageValue = this.getTargetAverageValue();
      result = result * 59 + ($targetAverageValue == null ? 43 : $targetAverageValue.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
