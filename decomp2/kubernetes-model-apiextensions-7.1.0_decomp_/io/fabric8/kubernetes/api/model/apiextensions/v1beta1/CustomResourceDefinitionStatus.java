package io.fabric8.kubernetes.api.model.apiextensions.v1beta1;

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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"acceptedNames", "conditions", "storedVersions"})
public class CustomResourceDefinitionStatus implements Editable, KubernetesResource {
   @JsonProperty("acceptedNames")
   private CustomResourceDefinitionNames acceptedNames;
   @JsonProperty("conditions")
   @JsonInclude(Include.NON_EMPTY)
   private List conditions = new ArrayList();
   @JsonProperty("storedVersions")
   @JsonInclude(Include.NON_EMPTY)
   private List storedVersions = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public CustomResourceDefinitionStatus() {
   }

   public CustomResourceDefinitionStatus(CustomResourceDefinitionNames acceptedNames, List conditions, List storedVersions) {
      this.acceptedNames = acceptedNames;
      this.conditions = conditions;
      this.storedVersions = storedVersions;
   }

   @JsonProperty("acceptedNames")
   public CustomResourceDefinitionNames getAcceptedNames() {
      return this.acceptedNames;
   }

   @JsonProperty("acceptedNames")
   public void setAcceptedNames(CustomResourceDefinitionNames acceptedNames) {
      this.acceptedNames = acceptedNames;
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

   @JsonProperty("storedVersions")
   @JsonInclude(Include.NON_EMPTY)
   public List getStoredVersions() {
      return this.storedVersions;
   }

   @JsonProperty("storedVersions")
   public void setStoredVersions(List storedVersions) {
      this.storedVersions = storedVersions;
   }

   @JsonIgnore
   public CustomResourceDefinitionStatusBuilder edit() {
      return new CustomResourceDefinitionStatusBuilder(this);
   }

   @JsonIgnore
   public CustomResourceDefinitionStatusBuilder toBuilder() {
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
      CustomResourceDefinitionNames var10000 = this.getAcceptedNames();
      return "CustomResourceDefinitionStatus(acceptedNames=" + var10000 + ", conditions=" + this.getConditions() + ", storedVersions=" + this.getStoredVersions() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CustomResourceDefinitionStatus)) {
         return false;
      } else {
         CustomResourceDefinitionStatus other = (CustomResourceDefinitionStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$acceptedNames = this.getAcceptedNames();
            Object other$acceptedNames = other.getAcceptedNames();
            if (this$acceptedNames == null) {
               if (other$acceptedNames != null) {
                  return false;
               }
            } else if (!this$acceptedNames.equals(other$acceptedNames)) {
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

            Object this$storedVersions = this.getStoredVersions();
            Object other$storedVersions = other.getStoredVersions();
            if (this$storedVersions == null) {
               if (other$storedVersions != null) {
                  return false;
               }
            } else if (!this$storedVersions.equals(other$storedVersions)) {
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
      return other instanceof CustomResourceDefinitionStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $acceptedNames = this.getAcceptedNames();
      result = result * 59 + ($acceptedNames == null ? 43 : $acceptedNames.hashCode());
      Object $conditions = this.getConditions();
      result = result * 59 + ($conditions == null ? 43 : $conditions.hashCode());
      Object $storedVersions = this.getStoredVersions();
      result = result * 59 + ($storedVersions == null ? 43 : $storedVersions.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
