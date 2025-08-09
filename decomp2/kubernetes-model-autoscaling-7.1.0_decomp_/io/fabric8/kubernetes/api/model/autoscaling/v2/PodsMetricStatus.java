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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"current", "metric"})
public class PodsMetricStatus implements Editable, KubernetesResource {
   @JsonProperty("current")
   private MetricValueStatus current;
   @JsonProperty("metric")
   private MetricIdentifier metric;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PodsMetricStatus() {
   }

   public PodsMetricStatus(MetricValueStatus current, MetricIdentifier metric) {
      this.current = current;
      this.metric = metric;
   }

   @JsonProperty("current")
   public MetricValueStatus getCurrent() {
      return this.current;
   }

   @JsonProperty("current")
   public void setCurrent(MetricValueStatus current) {
      this.current = current;
   }

   @JsonProperty("metric")
   public MetricIdentifier getMetric() {
      return this.metric;
   }

   @JsonProperty("metric")
   public void setMetric(MetricIdentifier metric) {
      this.metric = metric;
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
      MetricValueStatus var10000 = this.getCurrent();
      return "PodsMetricStatus(current=" + var10000 + ", metric=" + this.getMetric() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
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
            Object this$current = this.getCurrent();
            Object other$current = other.getCurrent();
            if (this$current == null) {
               if (other$current != null) {
                  return false;
               }
            } else if (!this$current.equals(other$current)) {
               return false;
            }

            Object this$metric = this.getMetric();
            Object other$metric = other.getMetric();
            if (this$metric == null) {
               if (other$metric != null) {
                  return false;
               }
            } else if (!this$metric.equals(other$metric)) {
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
      Object $current = this.getCurrent();
      result = result * 59 + ($current == null ? 43 : $current.hashCode());
      Object $metric = this.getMetric();
      result = result * 59 + ($metric == null ? 43 : $metric.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
