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
@JsonPropertyOrder({"seconds"})
public class SleepAction implements Editable, KubernetesResource {
   @JsonProperty("seconds")
   private Long seconds;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public SleepAction() {
   }

   public SleepAction(Long seconds) {
      this.seconds = seconds;
   }

   @JsonProperty("seconds")
   public Long getSeconds() {
      return this.seconds;
   }

   @JsonProperty("seconds")
   public void setSeconds(Long seconds) {
      this.seconds = seconds;
   }

   @JsonIgnore
   public SleepActionBuilder edit() {
      return new SleepActionBuilder(this);
   }

   @JsonIgnore
   public SleepActionBuilder toBuilder() {
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
      Long var10000 = this.getSeconds();
      return "SleepAction(seconds=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SleepAction)) {
         return false;
      } else {
         SleepAction other = (SleepAction)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$seconds = this.getSeconds();
            Object other$seconds = other.getSeconds();
            if (this$seconds == null) {
               if (other$seconds != null) {
                  return false;
               }
            } else if (!this$seconds.equals(other$seconds)) {
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
      return other instanceof SleepAction;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $seconds = this.getSeconds();
      result = result * 59 + ($seconds == null ? 43 : $seconds.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
