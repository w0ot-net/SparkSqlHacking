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
@JsonPropertyOrder({"volumeClaimTemplate"})
public class EphemeralVolumeSource implements Editable, KubernetesResource {
   @JsonProperty("volumeClaimTemplate")
   private PersistentVolumeClaimTemplate volumeClaimTemplate;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public EphemeralVolumeSource() {
   }

   public EphemeralVolumeSource(PersistentVolumeClaimTemplate volumeClaimTemplate) {
      this.volumeClaimTemplate = volumeClaimTemplate;
   }

   @JsonProperty("volumeClaimTemplate")
   public PersistentVolumeClaimTemplate getVolumeClaimTemplate() {
      return this.volumeClaimTemplate;
   }

   @JsonProperty("volumeClaimTemplate")
   public void setVolumeClaimTemplate(PersistentVolumeClaimTemplate volumeClaimTemplate) {
      this.volumeClaimTemplate = volumeClaimTemplate;
   }

   @JsonIgnore
   public EphemeralVolumeSourceBuilder edit() {
      return new EphemeralVolumeSourceBuilder(this);
   }

   @JsonIgnore
   public EphemeralVolumeSourceBuilder toBuilder() {
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
      PersistentVolumeClaimTemplate var10000 = this.getVolumeClaimTemplate();
      return "EphemeralVolumeSource(volumeClaimTemplate=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EphemeralVolumeSource)) {
         return false;
      } else {
         EphemeralVolumeSource other = (EphemeralVolumeSource)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$volumeClaimTemplate = this.getVolumeClaimTemplate();
            Object other$volumeClaimTemplate = other.getVolumeClaimTemplate();
            if (this$volumeClaimTemplate == null) {
               if (other$volumeClaimTemplate != null) {
                  return false;
               }
            } else if (!this$volumeClaimTemplate.equals(other$volumeClaimTemplate)) {
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
      return other instanceof EphemeralVolumeSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $volumeClaimTemplate = this.getVolumeClaimTemplate();
      result = result * 59 + ($volumeClaimTemplate == null ? 43 : $volumeClaimTemplate.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
