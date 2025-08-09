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
@JsonPropertyOrder({"claimName", "readOnly"})
public class PersistentVolumeClaimVolumeSource implements Editable, KubernetesResource {
   @JsonProperty("claimName")
   private String claimName;
   @JsonProperty("readOnly")
   private Boolean readOnly;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PersistentVolumeClaimVolumeSource() {
   }

   public PersistentVolumeClaimVolumeSource(String claimName, Boolean readOnly) {
      this.claimName = claimName;
      this.readOnly = readOnly;
   }

   @JsonProperty("claimName")
   public String getClaimName() {
      return this.claimName;
   }

   @JsonProperty("claimName")
   public void setClaimName(String claimName) {
      this.claimName = claimName;
   }

   @JsonProperty("readOnly")
   public Boolean getReadOnly() {
      return this.readOnly;
   }

   @JsonProperty("readOnly")
   public void setReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
   }

   @JsonIgnore
   public PersistentVolumeClaimVolumeSourceBuilder edit() {
      return new PersistentVolumeClaimVolumeSourceBuilder(this);
   }

   @JsonIgnore
   public PersistentVolumeClaimVolumeSourceBuilder toBuilder() {
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
      String var10000 = this.getClaimName();
      return "PersistentVolumeClaimVolumeSource(claimName=" + var10000 + ", readOnly=" + this.getReadOnly() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PersistentVolumeClaimVolumeSource)) {
         return false;
      } else {
         PersistentVolumeClaimVolumeSource other = (PersistentVolumeClaimVolumeSource)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$readOnly = this.getReadOnly();
            Object other$readOnly = other.getReadOnly();
            if (this$readOnly == null) {
               if (other$readOnly != null) {
                  return false;
               }
            } else if (!this$readOnly.equals(other$readOnly)) {
               return false;
            }

            Object this$claimName = this.getClaimName();
            Object other$claimName = other.getClaimName();
            if (this$claimName == null) {
               if (other$claimName != null) {
                  return false;
               }
            } else if (!this$claimName.equals(other$claimName)) {
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
      return other instanceof PersistentVolumeClaimVolumeSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $readOnly = this.getReadOnly();
      result = result * 59 + ($readOnly == null ? 43 : $readOnly.hashCode());
      Object $claimName = this.getClaimName();
      result = result * 59 + ($claimName == null ? 43 : $claimName.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
