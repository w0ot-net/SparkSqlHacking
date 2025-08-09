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
@JsonPropertyOrder({"averageValue", "currentValue", "metricName", "selector", "target"})
public class ObjectMetricStatus implements Editable, KubernetesResource {
   @JsonProperty("averageValue")
   private Quantity averageValue;
   @JsonProperty("currentValue")
   private Quantity currentValue;
   @JsonProperty("metricName")
   private String metricName;
   @JsonProperty("selector")
   private LabelSelector selector;
   @JsonProperty("target")
   private CrossVersionObjectReference target;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ObjectMetricStatus() {
   }

   public ObjectMetricStatus(Quantity averageValue, Quantity currentValue, String metricName, LabelSelector selector, CrossVersionObjectReference target) {
      this.averageValue = averageValue;
      this.currentValue = currentValue;
      this.metricName = metricName;
      this.selector = selector;
      this.target = target;
   }

   @JsonProperty("averageValue")
   public Quantity getAverageValue() {
      return this.averageValue;
   }

   @JsonProperty("averageValue")
   public void setAverageValue(Quantity averageValue) {
      this.averageValue = averageValue;
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

   @JsonProperty("selector")
   public LabelSelector getSelector() {
      return this.selector;
   }

   @JsonProperty("selector")
   public void setSelector(LabelSelector selector) {
      this.selector = selector;
   }

   @JsonProperty("target")
   public CrossVersionObjectReference getTarget() {
      return this.target;
   }

   @JsonProperty("target")
   public void setTarget(CrossVersionObjectReference target) {
      this.target = target;
   }

   @JsonIgnore
   public ObjectMetricStatusBuilder edit() {
      return new ObjectMetricStatusBuilder(this);
   }

   @JsonIgnore
   public ObjectMetricStatusBuilder toBuilder() {
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
      Quantity var10000 = this.getAverageValue();
      return "ObjectMetricStatus(averageValue=" + var10000 + ", currentValue=" + this.getCurrentValue() + ", metricName=" + this.getMetricName() + ", selector=" + this.getSelector() + ", target=" + this.getTarget() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ObjectMetricStatus)) {
         return false;
      } else {
         ObjectMetricStatus other = (ObjectMetricStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$averageValue = this.getAverageValue();
            Object other$averageValue = other.getAverageValue();
            if (this$averageValue == null) {
               if (other$averageValue != null) {
                  return false;
               }
            } else if (!this$averageValue.equals(other$averageValue)) {
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

            Object this$selector = this.getSelector();
            Object other$selector = other.getSelector();
            if (this$selector == null) {
               if (other$selector != null) {
                  return false;
               }
            } else if (!this$selector.equals(other$selector)) {
               return false;
            }

            Object this$target = this.getTarget();
            Object other$target = other.getTarget();
            if (this$target == null) {
               if (other$target != null) {
                  return false;
               }
            } else if (!this$target.equals(other$target)) {
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
      return other instanceof ObjectMetricStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $averageValue = this.getAverageValue();
      result = result * 59 + ($averageValue == null ? 43 : $averageValue.hashCode());
      Object $currentValue = this.getCurrentValue();
      result = result * 59 + ($currentValue == null ? 43 : $currentValue.hashCode());
      Object $metricName = this.getMetricName();
      result = result * 59 + ($metricName == null ? 43 : $metricName.hashCode());
      Object $selector = this.getSelector();
      result = result * 59 + ($selector == null ? 43 : $selector.hashCode());
      Object $target = this.getTarget();
      result = result * 59 + ($target == null ? 43 : $target.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
