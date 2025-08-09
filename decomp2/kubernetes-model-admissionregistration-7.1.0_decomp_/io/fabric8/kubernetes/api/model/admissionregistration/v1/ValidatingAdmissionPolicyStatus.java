package io.fabric8.kubernetes.api.model.admissionregistration.v1;

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
import io.fabric8.kubernetes.api.model.Condition;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"conditions", "observedGeneration", "typeChecking"})
public class ValidatingAdmissionPolicyStatus implements Editable, KubernetesResource {
   @JsonProperty("conditions")
   @JsonInclude(Include.NON_EMPTY)
   private List conditions = new ArrayList();
   @JsonProperty("observedGeneration")
   private Long observedGeneration;
   @JsonProperty("typeChecking")
   private TypeChecking typeChecking;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ValidatingAdmissionPolicyStatus() {
   }

   public ValidatingAdmissionPolicyStatus(List conditions, Long observedGeneration, TypeChecking typeChecking) {
      this.conditions = conditions;
      this.observedGeneration = observedGeneration;
      this.typeChecking = typeChecking;
   }

   @JsonProperty("conditions")
   @JsonInclude(Include.NON_EMPTY)
   public List getConditions() {
      return this.conditions;
   }

   @JsonProperty("conditions")
   public void setConditions(List conditions) {
      this.conditions = conditions;
   }

   @JsonProperty("observedGeneration")
   public Long getObservedGeneration() {
      return this.observedGeneration;
   }

   @JsonProperty("observedGeneration")
   public void setObservedGeneration(Long observedGeneration) {
      this.observedGeneration = observedGeneration;
   }

   @JsonProperty("typeChecking")
   public TypeChecking getTypeChecking() {
      return this.typeChecking;
   }

   @JsonProperty("typeChecking")
   public void setTypeChecking(TypeChecking typeChecking) {
      this.typeChecking = typeChecking;
   }

   @JsonIgnore
   public ValidatingAdmissionPolicyStatusBuilder edit() {
      return new ValidatingAdmissionPolicyStatusBuilder(this);
   }

   @JsonIgnore
   public ValidatingAdmissionPolicyStatusBuilder toBuilder() {
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
      List var10000 = this.getConditions();
      return "ValidatingAdmissionPolicyStatus(conditions=" + var10000 + ", observedGeneration=" + this.getObservedGeneration() + ", typeChecking=" + this.getTypeChecking() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ValidatingAdmissionPolicyStatus)) {
         return false;
      } else {
         ValidatingAdmissionPolicyStatus other = (ValidatingAdmissionPolicyStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$observedGeneration = this.getObservedGeneration();
            Object other$observedGeneration = other.getObservedGeneration();
            if (this$observedGeneration == null) {
               if (other$observedGeneration != null) {
                  return false;
               }
            } else if (!this$observedGeneration.equals(other$observedGeneration)) {
               return false;
            }

            Object this$conditions = this.getConditions();
            Object other$conditions = other.getConditions();
            if (this$conditions == null) {
               if (other$conditions != null) {
                  return false;
               }
            } else if (!this$conditions.equals(other$conditions)) {
               return false;
            }

            Object this$typeChecking = this.getTypeChecking();
            Object other$typeChecking = other.getTypeChecking();
            if (this$typeChecking == null) {
               if (other$typeChecking != null) {
                  return false;
               }
            } else if (!this$typeChecking.equals(other$typeChecking)) {
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
      return other instanceof ValidatingAdmissionPolicyStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $observedGeneration = this.getObservedGeneration();
      result = result * 59 + ($observedGeneration == null ? 43 : $observedGeneration.hashCode());
      Object $conditions = this.getConditions();
      result = result * 59 + ($conditions == null ? 43 : $conditions.hashCode());
      Object $typeChecking = this.getTypeChecking();
      result = result * 59 + ($typeChecking == null ? 43 : $typeChecking.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
