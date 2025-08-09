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
@JsonPropertyOrder({"medium", "sizeLimit"})
public class EmptyDirVolumeSource implements Editable, KubernetesResource {
   @JsonProperty("medium")
   private String medium;
   @JsonProperty("sizeLimit")
   private Quantity sizeLimit;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public EmptyDirVolumeSource() {
   }

   public EmptyDirVolumeSource(String medium, Quantity sizeLimit) {
      this.medium = medium;
      this.sizeLimit = sizeLimit;
   }

   @JsonProperty("medium")
   public String getMedium() {
      return this.medium;
   }

   @JsonProperty("medium")
   public void setMedium(String medium) {
      this.medium = medium;
   }

   @JsonProperty("sizeLimit")
   public Quantity getSizeLimit() {
      return this.sizeLimit;
   }

   @JsonProperty("sizeLimit")
   public void setSizeLimit(Quantity sizeLimit) {
      this.sizeLimit = sizeLimit;
   }

   @JsonIgnore
   public EmptyDirVolumeSourceBuilder edit() {
      return new EmptyDirVolumeSourceBuilder(this);
   }

   @JsonIgnore
   public EmptyDirVolumeSourceBuilder toBuilder() {
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
      String var10000 = this.getMedium();
      return "EmptyDirVolumeSource(medium=" + var10000 + ", sizeLimit=" + this.getSizeLimit() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EmptyDirVolumeSource)) {
         return false;
      } else {
         EmptyDirVolumeSource other = (EmptyDirVolumeSource)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$medium = this.getMedium();
            Object other$medium = other.getMedium();
            if (this$medium == null) {
               if (other$medium != null) {
                  return false;
               }
            } else if (!this$medium.equals(other$medium)) {
               return false;
            }

            Object this$sizeLimit = this.getSizeLimit();
            Object other$sizeLimit = other.getSizeLimit();
            if (this$sizeLimit == null) {
               if (other$sizeLimit != null) {
                  return false;
               }
            } else if (!this$sizeLimit.equals(other$sizeLimit)) {
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
      return other instanceof EmptyDirVolumeSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $medium = this.getMedium();
      result = result * 59 + ($medium == null ? 43 : $medium.hashCode());
      Object $sizeLimit = this.getSizeLimit();
      result = result * 59 + ($sizeLimit == null ? 43 : $sizeLimit.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
