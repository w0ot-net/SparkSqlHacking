package io.fabric8.kubernetes.api.model.autoscaling.v2beta2;

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
@JsonPropertyOrder({"container", "name", "target"})
public class ContainerResourceMetricSource implements Editable, KubernetesResource {
   @JsonProperty("container")
   private String container;
   @JsonProperty("name")
   private String name;
   @JsonProperty("target")
   private MetricTarget target;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ContainerResourceMetricSource() {
   }

   public ContainerResourceMetricSource(String container, String name, MetricTarget target) {
      this.container = container;
      this.name = name;
      this.target = target;
   }

   @JsonProperty("container")
   public String getContainer() {
      return this.container;
   }

   @JsonProperty("container")
   public void setContainer(String container) {
      this.container = container;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
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
   public ContainerResourceMetricSourceBuilder edit() {
      return new ContainerResourceMetricSourceBuilder(this);
   }

   @JsonIgnore
   public ContainerResourceMetricSourceBuilder toBuilder() {
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
      String var10000 = this.getContainer();
      return "ContainerResourceMetricSource(container=" + var10000 + ", name=" + this.getName() + ", target=" + this.getTarget() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ContainerResourceMetricSource)) {
         return false;
      } else {
         ContainerResourceMetricSource other = (ContainerResourceMetricSource)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$container = this.getContainer();
            Object other$container = other.getContainer();
            if (this$container == null) {
               if (other$container != null) {
                  return false;
               }
            } else if (!this$container.equals(other$container)) {
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
      return other instanceof ContainerResourceMetricSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $container = this.getContainer();
      result = result * 59 + ($container == null ? 43 : $container.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $target = this.getTarget();
      result = result * 59 + ($target == null ? 43 : $target.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
