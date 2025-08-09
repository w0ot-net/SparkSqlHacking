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
@JsonPropertyOrder({"current", "describedObject", "metric"})
public class ObjectMetricStatus implements Editable, KubernetesResource {
   @JsonProperty("current")
   private MetricValueStatus current;
   @JsonProperty("describedObject")
   private CrossVersionObjectReference describedObject;
   @JsonProperty("metric")
   private MetricIdentifier metric;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ObjectMetricStatus() {
   }

   public ObjectMetricStatus(MetricValueStatus current, CrossVersionObjectReference describedObject, MetricIdentifier metric) {
      this.current = current;
      this.describedObject = describedObject;
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

   @JsonProperty("describedObject")
   public CrossVersionObjectReference getDescribedObject() {
      return this.describedObject;
   }

   @JsonProperty("describedObject")
   public void setDescribedObject(CrossVersionObjectReference describedObject) {
      this.describedObject = describedObject;
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
      MetricValueStatus var10000 = this.getCurrent();
      return "ObjectMetricStatus(current=" + var10000 + ", describedObject=" + this.getDescribedObject() + ", metric=" + this.getMetric() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
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
            Object this$current = this.getCurrent();
            Object other$current = other.getCurrent();
            if (this$current == null) {
               if (other$current != null) {
                  return false;
               }
            } else if (!this$current.equals(other$current)) {
               return false;
            }

            Object this$describedObject = this.getDescribedObject();
            Object other$describedObject = other.getDescribedObject();
            if (this$describedObject == null) {
               if (other$describedObject != null) {
                  return false;
               }
            } else if (!this$describedObject.equals(other$describedObject)) {
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
      return other instanceof ObjectMetricStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $current = this.getCurrent();
      result = result * 59 + ($current == null ? 43 : $current.hashCode());
      Object $describedObject = this.getDescribedObject();
      result = result * 59 + ($describedObject == null ? 43 : $describedObject.hashCode());
      Object $metric = this.getMetric();
      result = result * 59 + ($metric == null ? 43 : $metric.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
