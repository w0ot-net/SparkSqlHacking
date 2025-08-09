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
import io.fabric8.kubernetes.api.model.Quantity;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"container", "currentAverageUtilization", "currentAverageValue", "name"})
public class ContainerResourceMetricStatus implements Editable, KubernetesResource {
   @JsonProperty("container")
   private String container;
   @JsonProperty("currentAverageUtilization")
   private Integer currentAverageUtilization;
   @JsonProperty("currentAverageValue")
   private Quantity currentAverageValue;
   @JsonProperty("name")
   private String name;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ContainerResourceMetricStatus() {
   }

   public ContainerResourceMetricStatus(String container, Integer currentAverageUtilization, Quantity currentAverageValue, String name) {
      this.container = container;
      this.currentAverageUtilization = currentAverageUtilization;
      this.currentAverageValue = currentAverageValue;
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

   @JsonProperty("currentAverageUtilization")
   public Integer getCurrentAverageUtilization() {
      return this.currentAverageUtilization;
   }

   @JsonProperty("currentAverageUtilization")
   public void setCurrentAverageUtilization(Integer currentAverageUtilization) {
      this.currentAverageUtilization = currentAverageUtilization;
   }

   @JsonProperty("currentAverageValue")
   public Quantity getCurrentAverageValue() {
      return this.currentAverageValue;
   }

   @JsonProperty("currentAverageValue")
   public void setCurrentAverageValue(Quantity currentAverageValue) {
      this.currentAverageValue = currentAverageValue;
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
      return "ContainerResourceMetricStatus(container=" + var10000 + ", currentAverageUtilization=" + this.getCurrentAverageUtilization() + ", currentAverageValue=" + this.getCurrentAverageValue() + ", name=" + this.getName() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
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
            Object this$currentAverageUtilization = this.getCurrentAverageUtilization();
            Object other$currentAverageUtilization = other.getCurrentAverageUtilization();
            if (this$currentAverageUtilization == null) {
               if (other$currentAverageUtilization != null) {
                  return false;
               }
            } else if (!this$currentAverageUtilization.equals(other$currentAverageUtilization)) {
               return false;
            }

            Object this$container = this.getContainer();
            Object other$container = other.getContainer();
            if (this$container == null) {
               if (other$container != null) {
                  return false;
               }
            } else if (!this$container.equals(other$container)) {
               return false;
            }

            Object this$currentAverageValue = this.getCurrentAverageValue();
            Object other$currentAverageValue = other.getCurrentAverageValue();
            if (this$currentAverageValue == null) {
               if (other$currentAverageValue != null) {
                  return false;
               }
            } else if (!this$currentAverageValue.equals(other$currentAverageValue)) {
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
      Object $currentAverageUtilization = this.getCurrentAverageUtilization();
      result = result * 59 + ($currentAverageUtilization == null ? 43 : $currentAverageUtilization.hashCode());
      Object $container = this.getContainer();
      result = result * 59 + ($container == null ? 43 : $container.hashCode());
      Object $currentAverageValue = this.getCurrentAverageValue();
      result = result * 59 + ($currentAverageValue == null ? 43 : $currentAverageValue.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
