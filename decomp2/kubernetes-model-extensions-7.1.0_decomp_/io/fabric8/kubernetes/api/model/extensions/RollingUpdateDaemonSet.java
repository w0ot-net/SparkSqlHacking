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
@JsonPropertyOrder({"maxUnavailable"})
public class RollingUpdateDaemonSet implements Editable, KubernetesResource {
   @JsonProperty("maxUnavailable")
   private IntOrString maxUnavailable;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public RollingUpdateDaemonSet() {
   }

   public RollingUpdateDaemonSet(IntOrString maxUnavailable) {
      this.maxUnavailable = maxUnavailable;
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
   public RollingUpdateDaemonSetBuilder edit() {
      return new RollingUpdateDaemonSetBuilder(this);
   }

   @JsonIgnore
   public RollingUpdateDaemonSetBuilder toBuilder() {
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
      return "RollingUpdateDaemonSet(maxUnavailable=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RollingUpdateDaemonSet)) {
         return false;
      } else {
         RollingUpdateDaemonSet other = (RollingUpdateDaemonSet)o;
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
      return other instanceof RollingUpdateDaemonSet;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $maxUnavailable = this.getMaxUnavailable();
      result = result * 59 + ($maxUnavailable == null ? 43 : $maxUnavailable.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
