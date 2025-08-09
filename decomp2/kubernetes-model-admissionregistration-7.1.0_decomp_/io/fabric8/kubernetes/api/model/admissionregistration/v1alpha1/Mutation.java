package io.fabric8.kubernetes.api.model.admissionregistration.v1alpha1;

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
@JsonPropertyOrder({"applyConfiguration", "jsonPatch", "patchType"})
public class Mutation implements Editable, KubernetesResource {
   @JsonProperty("applyConfiguration")
   private ApplyConfiguration applyConfiguration;
   @JsonProperty("jsonPatch")
   private JSONPatch jsonPatch;
   @JsonProperty("patchType")
   private String patchType;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public Mutation() {
   }

   public Mutation(ApplyConfiguration applyConfiguration, JSONPatch jsonPatch, String patchType) {
      this.applyConfiguration = applyConfiguration;
      this.jsonPatch = jsonPatch;
      this.patchType = patchType;
   }

   @JsonProperty("applyConfiguration")
   public ApplyConfiguration getApplyConfiguration() {
      return this.applyConfiguration;
   }

   @JsonProperty("applyConfiguration")
   public void setApplyConfiguration(ApplyConfiguration applyConfiguration) {
      this.applyConfiguration = applyConfiguration;
   }

   @JsonProperty("jsonPatch")
   public JSONPatch getJsonPatch() {
      return this.jsonPatch;
   }

   @JsonProperty("jsonPatch")
   public void setJsonPatch(JSONPatch jsonPatch) {
      this.jsonPatch = jsonPatch;
   }

   @JsonProperty("patchType")
   public String getPatchType() {
      return this.patchType;
   }

   @JsonProperty("patchType")
   public void setPatchType(String patchType) {
      this.patchType = patchType;
   }

   @JsonIgnore
   public MutationBuilder edit() {
      return new MutationBuilder(this);
   }

   @JsonIgnore
   public MutationBuilder toBuilder() {
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
      ApplyConfiguration var10000 = this.getApplyConfiguration();
      return "Mutation(applyConfiguration=" + var10000 + ", jsonPatch=" + this.getJsonPatch() + ", patchType=" + this.getPatchType() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Mutation)) {
         return false;
      } else {
         Mutation other = (Mutation)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$applyConfiguration = this.getApplyConfiguration();
            Object other$applyConfiguration = other.getApplyConfiguration();
            if (this$applyConfiguration == null) {
               if (other$applyConfiguration != null) {
                  return false;
               }
            } else if (!this$applyConfiguration.equals(other$applyConfiguration)) {
               return false;
            }

            Object this$jsonPatch = this.getJsonPatch();
            Object other$jsonPatch = other.getJsonPatch();
            if (this$jsonPatch == null) {
               if (other$jsonPatch != null) {
                  return false;
               }
            } else if (!this$jsonPatch.equals(other$jsonPatch)) {
               return false;
            }

            Object this$patchType = this.getPatchType();
            Object other$patchType = other.getPatchType();
            if (this$patchType == null) {
               if (other$patchType != null) {
                  return false;
               }
            } else if (!this$patchType.equals(other$patchType)) {
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
      return other instanceof Mutation;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $applyConfiguration = this.getApplyConfiguration();
      result = result * 59 + ($applyConfiguration == null ? 43 : $applyConfiguration.hashCode());
      Object $jsonPatch = this.getJsonPatch();
      result = result * 59 + ($jsonPatch == null ? 43 : $jsonPatch.hashCode());
      Object $patchType = this.getPatchType();
      result = result * 59 + ($patchType == null ? 43 : $patchType.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
