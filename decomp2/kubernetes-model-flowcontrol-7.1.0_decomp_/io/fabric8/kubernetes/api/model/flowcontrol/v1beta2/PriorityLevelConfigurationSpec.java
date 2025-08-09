package io.fabric8.kubernetes.api.model.flowcontrol.v1beta2;

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
@JsonPropertyOrder({"limited", "type"})
public class PriorityLevelConfigurationSpec implements Editable, KubernetesResource {
   @JsonProperty("limited")
   private LimitedPriorityLevelConfiguration limited;
   @JsonProperty("type")
   private String type;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PriorityLevelConfigurationSpec() {
   }

   public PriorityLevelConfigurationSpec(LimitedPriorityLevelConfiguration limited, String type) {
      this.limited = limited;
      this.type = type;
   }

   @JsonProperty("limited")
   public LimitedPriorityLevelConfiguration getLimited() {
      return this.limited;
   }

   @JsonProperty("limited")
   public void setLimited(LimitedPriorityLevelConfiguration limited) {
      this.limited = limited;
   }

   @JsonProperty("type")
   public String getType() {
      return this.type;
   }

   @JsonProperty("type")
   public void setType(String type) {
      this.type = type;
   }

   @JsonIgnore
   public PriorityLevelConfigurationSpecBuilder edit() {
      return new PriorityLevelConfigurationSpecBuilder(this);
   }

   @JsonIgnore
   public PriorityLevelConfigurationSpecBuilder toBuilder() {
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
      LimitedPriorityLevelConfiguration var10000 = this.getLimited();
      return "PriorityLevelConfigurationSpec(limited=" + var10000 + ", type=" + this.getType() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PriorityLevelConfigurationSpec)) {
         return false;
      } else {
         PriorityLevelConfigurationSpec other = (PriorityLevelConfigurationSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$limited = this.getLimited();
            Object other$limited = other.getLimited();
            if (this$limited == null) {
               if (other$limited != null) {
                  return false;
               }
            } else if (!this$limited.equals(other$limited)) {
               return false;
            }

            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
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
      return other instanceof PriorityLevelConfigurationSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $limited = this.getLimited();
      result = result * 59 + ($limited == null ? 43 : $limited.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
