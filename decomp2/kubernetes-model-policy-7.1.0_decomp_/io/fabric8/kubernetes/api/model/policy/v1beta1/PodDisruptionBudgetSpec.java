package io.fabric8.kubernetes.api.model.policy.v1beta1;

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
import io.fabric8.kubernetes.api.model.IntOrString;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.fabric8.kubernetes.api.model.LabelSelector;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"maxUnavailable", "minAvailable", "selector"})
public class PodDisruptionBudgetSpec implements Editable, KubernetesResource {
   @JsonProperty("maxUnavailable")
   private IntOrString maxUnavailable;
   @JsonProperty("minAvailable")
   private IntOrString minAvailable;
   @JsonProperty("selector")
   private LabelSelector selector;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PodDisruptionBudgetSpec() {
   }

   public PodDisruptionBudgetSpec(IntOrString maxUnavailable, IntOrString minAvailable, LabelSelector selector) {
      this.maxUnavailable = maxUnavailable;
      this.minAvailable = minAvailable;
      this.selector = selector;
   }

   @JsonProperty("maxUnavailable")
   public IntOrString getMaxUnavailable() {
      return this.maxUnavailable;
   }

   @JsonProperty("maxUnavailable")
   public void setMaxUnavailable(IntOrString maxUnavailable) {
      this.maxUnavailable = maxUnavailable;
   }

   @JsonProperty("minAvailable")
   public IntOrString getMinAvailable() {
      return this.minAvailable;
   }

   @JsonProperty("minAvailable")
   public void setMinAvailable(IntOrString minAvailable) {
      this.minAvailable = minAvailable;
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
   public PodDisruptionBudgetSpecBuilder edit() {
      return new PodDisruptionBudgetSpecBuilder(this);
   }

   @JsonIgnore
   public PodDisruptionBudgetSpecBuilder toBuilder() {
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
      IntOrString var10000 = this.getMaxUnavailable();
      return "PodDisruptionBudgetSpec(maxUnavailable=" + var10000 + ", minAvailable=" + this.getMinAvailable() + ", selector=" + this.getSelector() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PodDisruptionBudgetSpec)) {
         return false;
      } else {
         PodDisruptionBudgetSpec other = (PodDisruptionBudgetSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$maxUnavailable = this.getMaxUnavailable();
            Object other$maxUnavailable = other.getMaxUnavailable();
            if (this$maxUnavailable == null) {
               if (other$maxUnavailable != null) {
                  return false;
               }
            } else if (!this$maxUnavailable.equals(other$maxUnavailable)) {
               return false;
            }

            Object this$minAvailable = this.getMinAvailable();
            Object other$minAvailable = other.getMinAvailable();
            if (this$minAvailable == null) {
               if (other$minAvailable != null) {
                  return false;
               }
            } else if (!this$minAvailable.equals(other$minAvailable)) {
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
      return other instanceof PodDisruptionBudgetSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $maxUnavailable = this.getMaxUnavailable();
      result = result * 59 + ($maxUnavailable == null ? 43 : $maxUnavailable.hashCode());
      Object $minAvailable = this.getMinAvailable();
      result = result * 59 + ($minAvailable == null ? 43 : $minAvailable.hashCode());
      Object $selector = this.getSelector();
      result = result * 59 + ($selector == null ? 43 : $selector.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
