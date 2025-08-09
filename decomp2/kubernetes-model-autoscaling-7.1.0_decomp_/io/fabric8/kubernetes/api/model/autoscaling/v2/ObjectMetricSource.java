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
@JsonPropertyOrder({"describedObject", "metric", "target"})
public class ObjectMetricSource implements Editable, KubernetesResource {
   @JsonProperty("describedObject")
   private CrossVersionObjectReference describedObject;
   @JsonProperty("metric")
   private MetricIdentifier metric;
   @JsonProperty("target")
   private MetricTarget target;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ObjectMetricSource() {
   }

   public ObjectMetricSource(CrossVersionObjectReference describedObject, MetricIdentifier metric, MetricTarget target) {
      this.describedObject = describedObject;
      this.metric = metric;
      this.target = target;
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

   @JsonProperty("target")
   public MetricTarget getTarget() {
      return this.target;
   }

   @JsonProperty("target")
   public void setTarget(MetricTarget target) {
      this.target = target;
   }

   @JsonIgnore
   public ObjectMetricSourceBuilder edit() {
      return new ObjectMetricSourceBuilder(this);
   }

   @JsonIgnore
   public ObjectMetricSourceBuilder toBuilder() {
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
      CrossVersionObjectReference var10000 = this.getDescribedObject();
      return "ObjectMetricSource(describedObject=" + var10000 + ", metric=" + this.getMetric() + ", target=" + this.getTarget() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ObjectMetricSource)) {
         return false;
      } else {
         ObjectMetricSource other = (ObjectMetricSource)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
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
      return other instanceof ObjectMetricSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $describedObject = this.getDescribedObject();
      result = result * 59 + ($describedObject == null ? 43 : $describedObject.hashCode());
      Object $metric = this.getMetric();
      result = result * 59 + ($metric == null ? 43 : $metric.hashCode());
      Object $target = this.getTarget();
      result = result * 59 + ($target == null ? 43 : $target.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
