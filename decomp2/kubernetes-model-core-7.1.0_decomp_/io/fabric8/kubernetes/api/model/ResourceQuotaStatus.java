package io.fabric8.kubernetes.api.model;

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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"hard", "used"})
public class ResourceQuotaStatus implements Editable, KubernetesResource {
   @JsonProperty("hard")
   @JsonInclude(Include.NON_EMPTY)
   private Map hard = new LinkedHashMap();
   @JsonProperty("used")
   @JsonInclude(Include.NON_EMPTY)
   private Map used = new LinkedHashMap();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ResourceQuotaStatus() {
   }

   public ResourceQuotaStatus(Map hard, Map used) {
      this.hard = hard;
      this.used = used;
   }

   @JsonProperty("hard")
   @JsonInclude(Include.NON_EMPTY)
   public Map getHard() {
      return this.hard;
   }

   @JsonProperty("hard")
   public void setHard(Map hard) {
      this.hard = hard;
   }

   @JsonProperty("used")
   @JsonInclude(Include.NON_EMPTY)
   public Map getUsed() {
      return this.used;
   }

   @JsonProperty("used")
   public void setUsed(Map used) {
      this.used = used;
   }

   @JsonIgnore
   public ResourceQuotaStatusBuilder edit() {
      return new ResourceQuotaStatusBuilder(this);
   }

   @JsonIgnore
   public ResourceQuotaStatusBuilder toBuilder() {
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
      Map var10000 = this.getHard();
      return "ResourceQuotaStatus(hard=" + var10000 + ", used=" + this.getUsed() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourceQuotaStatus)) {
         return false;
      } else {
         ResourceQuotaStatus other = (ResourceQuotaStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$hard = this.getHard();
            Object other$hard = other.getHard();
            if (this$hard == null) {
               if (other$hard != null) {
                  return false;
               }
            } else if (!this$hard.equals(other$hard)) {
               return false;
            }

            Object this$used = this.getUsed();
            Object other$used = other.getUsed();
            if (this$used == null) {
               if (other$used != null) {
                  return false;
               }
            } else if (!this$used.equals(other$used)) {
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
      return other instanceof ResourceQuotaStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $hard = this.getHard();
      result = result * 59 + ($hard == null ? 43 : $hard.hashCode());
      Object $used = this.getUsed();
      result = result * 59 + ($used == null ? 43 : $used.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
