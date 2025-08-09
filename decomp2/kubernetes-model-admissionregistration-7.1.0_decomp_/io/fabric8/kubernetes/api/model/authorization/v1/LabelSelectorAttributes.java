package io.fabric8.kubernetes.api.model.authorization.v1;

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
import io.fabric8.kubernetes.api.model.LabelSelectorRequirement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"rawSelector", "requirements"})
public class LabelSelectorAttributes implements Editable, KubernetesResource {
   @JsonProperty("rawSelector")
   private String rawSelector;
   @JsonProperty("requirements")
   @JsonInclude(Include.NON_EMPTY)
   private List requirements = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public LabelSelectorAttributes() {
   }

   public LabelSelectorAttributes(String rawSelector, List requirements) {
      this.rawSelector = rawSelector;
      this.requirements = requirements;
   }

   @JsonProperty("rawSelector")
   public String getRawSelector() {
      return this.rawSelector;
   }

   @JsonProperty("rawSelector")
   public void setRawSelector(String rawSelector) {
      this.rawSelector = rawSelector;
   }

   @JsonProperty("requirements")
   @JsonInclude(Include.NON_EMPTY)
   public List getRequirements() {
      return this.requirements;
   }

   @JsonProperty("requirements")
   public void setRequirements(List requirements) {
      this.requirements = requirements;
   }

   @JsonIgnore
   public LabelSelectorAttributesBuilder edit() {
      return new LabelSelectorAttributesBuilder(this);
   }

   @JsonIgnore
   public LabelSelectorAttributesBuilder toBuilder() {
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
      String var10000 = this.getRawSelector();
      return "LabelSelectorAttributes(rawSelector=" + var10000 + ", requirements=" + this.getRequirements() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof LabelSelectorAttributes)) {
         return false;
      } else {
         LabelSelectorAttributes other = (LabelSelectorAttributes)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$rawSelector = this.getRawSelector();
            Object other$rawSelector = other.getRawSelector();
            if (this$rawSelector == null) {
               if (other$rawSelector != null) {
                  return false;
               }
            } else if (!this$rawSelector.equals(other$rawSelector)) {
               return false;
            }

            Object this$requirements = this.getRequirements();
            Object other$requirements = other.getRequirements();
            if (this$requirements == null) {
               if (other$requirements != null) {
                  return false;
               }
            } else if (!this$requirements.equals(other$requirements)) {
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
      return other instanceof LabelSelectorAttributes;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $rawSelector = this.getRawSelector();
      result = result * 59 + ($rawSelector == null ? 43 : $rawSelector.hashCode());
      Object $requirements = this.getRequirements();
      result = result * 59 + ($requirements == null ? 43 : $requirements.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
