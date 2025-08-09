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
@JsonPropertyOrder({"required"})
public class VolumeNodeAffinity implements Editable, KubernetesResource {
   @JsonProperty("required")
   private NodeSelector required;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public VolumeNodeAffinity() {
   }

   public VolumeNodeAffinity(NodeSelector required) {
      this.required = required;
   }

   @JsonProperty("required")
   public NodeSelector getRequired() {
      return this.required;
   }

   @JsonProperty("required")
   public void setRequired(NodeSelector required) {
      this.required = required;
   }

   @JsonIgnore
   public VolumeNodeAffinityBuilder edit() {
      return new VolumeNodeAffinityBuilder(this);
   }

   @JsonIgnore
   public VolumeNodeAffinityBuilder toBuilder() {
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
      NodeSelector var10000 = this.getRequired();
      return "VolumeNodeAffinity(required=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof VolumeNodeAffinity)) {
         return false;
      } else {
         VolumeNodeAffinity other = (VolumeNodeAffinity)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$required = this.getRequired();
            Object other$required = other.getRequired();
            if (this$required == null) {
               if (other$required != null) {
                  return false;
               }
            } else if (!this$required.equals(other$required)) {
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
      return other instanceof VolumeNodeAffinity;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $required = this.getRequired();
      result = result * 59 + ($required == null ? 43 : $required.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
