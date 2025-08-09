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
@JsonPropertyOrder({"pullPolicy", "reference"})
public class ImageVolumeSource implements Editable, KubernetesResource {
   @JsonProperty("pullPolicy")
   private String pullPolicy;
   @JsonProperty("reference")
   private String reference;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ImageVolumeSource() {
   }

   public ImageVolumeSource(String pullPolicy, String reference) {
      this.pullPolicy = pullPolicy;
      this.reference = reference;
   }

   @JsonProperty("pullPolicy")
   public String getPullPolicy() {
      return this.pullPolicy;
   }

   @JsonProperty("pullPolicy")
   public void setPullPolicy(String pullPolicy) {
      this.pullPolicy = pullPolicy;
   }

   @JsonProperty("reference")
   public String getReference() {
      return this.reference;
   }

   @JsonProperty("reference")
   public void setReference(String reference) {
      this.reference = reference;
   }

   @JsonIgnore
   public ImageVolumeSourceBuilder edit() {
      return new ImageVolumeSourceBuilder(this);
   }

   @JsonIgnore
   public ImageVolumeSourceBuilder toBuilder() {
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
      String var10000 = this.getPullPolicy();
      return "ImageVolumeSource(pullPolicy=" + var10000 + ", reference=" + this.getReference() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ImageVolumeSource)) {
         return false;
      } else {
         ImageVolumeSource other = (ImageVolumeSource)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$pullPolicy = this.getPullPolicy();
            Object other$pullPolicy = other.getPullPolicy();
            if (this$pullPolicy == null) {
               if (other$pullPolicy != null) {
                  return false;
               }
            } else if (!this$pullPolicy.equals(other$pullPolicy)) {
               return false;
            }

            Object this$reference = this.getReference();
            Object other$reference = other.getReference();
            if (this$reference == null) {
               if (other$reference != null) {
                  return false;
               }
            } else if (!this$reference.equals(other$reference)) {
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
      return other instanceof ImageVolumeSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $pullPolicy = this.getPullPolicy();
      result = result * 59 + ($pullPolicy == null ? 43 : $pullPolicy.hashCode());
      Object $reference = this.getReference();
      result = result * 59 + ($reference == null ? 43 : $reference.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
