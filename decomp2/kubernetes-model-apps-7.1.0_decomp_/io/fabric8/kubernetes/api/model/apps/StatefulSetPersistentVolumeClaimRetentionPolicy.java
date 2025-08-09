package io.fabric8.kubernetes.api.model.apps;

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
@JsonPropertyOrder({"whenDeleted", "whenScaled"})
public class StatefulSetPersistentVolumeClaimRetentionPolicy implements Editable, KubernetesResource {
   @JsonProperty("whenDeleted")
   private String whenDeleted;
   @JsonProperty("whenScaled")
   private String whenScaled;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public StatefulSetPersistentVolumeClaimRetentionPolicy() {
   }

   public StatefulSetPersistentVolumeClaimRetentionPolicy(String whenDeleted, String whenScaled) {
      this.whenDeleted = whenDeleted;
      this.whenScaled = whenScaled;
   }

   @JsonProperty("whenDeleted")
   public String getWhenDeleted() {
      return this.whenDeleted;
   }

   @JsonProperty("whenDeleted")
   public void setWhenDeleted(String whenDeleted) {
      this.whenDeleted = whenDeleted;
   }

   @JsonProperty("whenScaled")
   public String getWhenScaled() {
      return this.whenScaled;
   }

   @JsonProperty("whenScaled")
   public void setWhenScaled(String whenScaled) {
      this.whenScaled = whenScaled;
   }

   @JsonIgnore
   public StatefulSetPersistentVolumeClaimRetentionPolicyBuilder edit() {
      return new StatefulSetPersistentVolumeClaimRetentionPolicyBuilder(this);
   }

   @JsonIgnore
   public StatefulSetPersistentVolumeClaimRetentionPolicyBuilder toBuilder() {
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
      String var10000 = this.getWhenDeleted();
      return "StatefulSetPersistentVolumeClaimRetentionPolicy(whenDeleted=" + var10000 + ", whenScaled=" + this.getWhenScaled() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof StatefulSetPersistentVolumeClaimRetentionPolicy)) {
         return false;
      } else {
         StatefulSetPersistentVolumeClaimRetentionPolicy other = (StatefulSetPersistentVolumeClaimRetentionPolicy)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$whenDeleted = this.getWhenDeleted();
            Object other$whenDeleted = other.getWhenDeleted();
            if (this$whenDeleted == null) {
               if (other$whenDeleted != null) {
                  return false;
               }
            } else if (!this$whenDeleted.equals(other$whenDeleted)) {
               return false;
            }

            Object this$whenScaled = this.getWhenScaled();
            Object other$whenScaled = other.getWhenScaled();
            if (this$whenScaled == null) {
               if (other$whenScaled != null) {
                  return false;
               }
            } else if (!this$whenScaled.equals(other$whenScaled)) {
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
      return other instanceof StatefulSetPersistentVolumeClaimRetentionPolicy;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $whenDeleted = this.getWhenDeleted();
      result = result * 59 + ($whenDeleted == null ? 43 : $whenDeleted.hashCode());
      Object $whenScaled = this.getWhenScaled();
      result = result * 59 + ($whenScaled == null ? 43 : $whenScaled.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
