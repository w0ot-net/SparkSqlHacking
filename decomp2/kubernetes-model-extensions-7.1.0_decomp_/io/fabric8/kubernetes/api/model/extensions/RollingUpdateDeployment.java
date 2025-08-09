package io.fabric8.kubernetes.api.model.extensions;

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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"maxSurge", "maxUnavailable"})
public class RollingUpdateDeployment implements Editable, KubernetesResource {
   @JsonProperty("maxSurge")
   private IntOrString maxSurge;
   @JsonProperty("maxUnavailable")
   private IntOrString maxUnavailable;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public RollingUpdateDeployment() {
   }

   public RollingUpdateDeployment(IntOrString maxSurge, IntOrString maxUnavailable) {
      this.maxSurge = maxSurge;
      this.maxUnavailable = maxUnavailable;
   }

   @JsonProperty("maxSurge")
   public IntOrString getMaxSurge() {
      return this.maxSurge;
   }

   @JsonProperty("maxSurge")
   public void setMaxSurge(IntOrString maxSurge) {
      this.maxSurge = maxSurge;
   }

   @JsonProperty("maxUnavailable")
   public IntOrString getMaxUnavailable() {
      return this.maxUnavailable;
   }

   @JsonProperty("maxUnavailable")
   public void setMaxUnavailable(IntOrString maxUnavailable) {
      this.maxUnavailable = maxUnavailable;
   }

   @JsonIgnore
   public RollingUpdateDeploymentBuilder edit() {
      return new RollingUpdateDeploymentBuilder(this);
   }

   @JsonIgnore
   public RollingUpdateDeploymentBuilder toBuilder() {
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
      IntOrString var10000 = this.getMaxSurge();
      return "RollingUpdateDeployment(maxSurge=" + var10000 + ", maxUnavailable=" + this.getMaxUnavailable() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RollingUpdateDeployment)) {
         return false;
      } else {
         RollingUpdateDeployment other = (RollingUpdateDeployment)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$maxSurge = this.getMaxSurge();
            Object other$maxSurge = other.getMaxSurge();
            if (this$maxSurge == null) {
               if (other$maxSurge != null) {
                  return false;
               }
            } else if (!this$maxSurge.equals(other$maxSurge)) {
               return false;
            }

            Object this$maxUnavailable = this.getMaxUnavailable();
            Object other$maxUnavailable = other.getMaxUnavailable();
            if (this$maxUnavailable == null) {
               if (other$maxUnavailable != null) {
                  return false;
               }
            } else if (!this$maxUnavailable.equals(other$maxUnavailable)) {
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
      return other instanceof RollingUpdateDeployment;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $maxSurge = this.getMaxSurge();
      result = result * 59 + ($maxSurge == null ? 43 : $maxSurge.hashCode());
      Object $maxUnavailable = this.getMaxUnavailable();
      result = result * 59 + ($maxUnavailable == null ? 43 : $maxUnavailable.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
