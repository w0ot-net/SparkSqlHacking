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
@JsonPropertyOrder({"container", "current", "name"})
public class ContainerResourceMetricStatus implements Editable, KubernetesResource {
   @JsonProperty("container")
   private String container;
   @JsonProperty("current")
   private MetricValueStatus current;
   @JsonProperty("name")
   private String name;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ContainerResourceMetricStatus() {
   }

   public ContainerResourceMetricStatus(String container, MetricValueStatus current, String name) {
      this.container = container;
      this.current = current;
      this.name = name;
   }

   @JsonProperty("container")
   public String getContainer() {
      return this.container;
   }

   @JsonProperty("container")
   public void setContainer(String container) {
      this.container = container;
   }

   @JsonProperty("current")
   public MetricValueStatus getCurrent() {
      return this.current;
   }

   @JsonProperty("current")
   public void setCurrent(MetricValueStatus current) {
      this.current = current;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonIgnore
   public ContainerResourceMetricStatusBuilder edit() {
      return new ContainerResourceMetricStatusBuilder(this);
   }

   @JsonIgnore
   public ContainerResourceMetricStatusBuilder toBuilder() {
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
      return "ContainerResourceMetricStatus(container=" + var10000 + ", current=" + this.getCurrent() + ", name=" + this.getName() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ContainerResourceMetricStatus)) {
         return false;
      } else {
         ContainerResourceMetricStatus other = (ContainerResourceMetricStatus)o;
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

            Object this$current = this.getCurrent();
            Object other$current = other.getCurrent();
            if (this$current == null) {
               if (other$current != null) {
                  return false;
               }
            } else if (!this$current.equals(other$current)) {
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
      return other instanceof ContainerResourceMetricStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $container = this.getContainer();
      result = result * 59 + ($container == null ? 43 : $container.hashCode());
      Object $current = this.getCurrent();
      result = result * 59 + ($current == null ? 43 : $current.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
