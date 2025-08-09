package io.fabric8.kubernetes.api.model.authorization.v1beta1;

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
@JsonPropertyOrder({"nonResourceURLs", "verbs"})
public class NonResourceRule implements Editable, KubernetesResource {
   @JsonProperty("nonResourceURLs")
   @JsonInclude(Include.NON_EMPTY)
   private List nonResourceURLs = new ArrayList();
   @JsonProperty("verbs")
   @JsonInclude(Include.NON_EMPTY)
   private List verbs = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public NonResourceRule() {
   }

   public NonResourceRule(List nonResourceURLs, List verbs) {
      this.nonResourceURLs = nonResourceURLs;
      this.verbs = verbs;
   }

   @JsonProperty("nonResourceURLs")
   @JsonInclude(Include.NON_EMPTY)
   public List getNonResourceURLs() {
      return this.nonResourceURLs;
   }

   @JsonProperty("nonResourceURLs")
   public void setNonResourceURLs(List nonResourceURLs) {
      this.nonResourceURLs = nonResourceURLs;
   }

   @JsonProperty("verbs")
   @JsonInclude(Include.NON_EMPTY)
   public List getVerbs() {
      return this.verbs;
   }

   @JsonProperty("verbs")
   public void setVerbs(List verbs) {
      this.verbs = verbs;
   }

   @JsonIgnore
   public NonResourceRuleBuilder edit() {
      return new NonResourceRuleBuilder(this);
   }

   @JsonIgnore
   public NonResourceRuleBuilder toBuilder() {
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
      List var10000 = this.getNonResourceURLs();
      return "NonResourceRule(nonResourceURLs=" + var10000 + ", verbs=" + this.getVerbs() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NonResourceRule)) {
         return false;
      } else {
         NonResourceRule other = (NonResourceRule)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$nonResourceURLs = this.getNonResourceURLs();
            Object other$nonResourceURLs = other.getNonResourceURLs();
            if (this$nonResourceURLs == null) {
               if (other$nonResourceURLs != null) {
                  return false;
               }
            } else if (!this$nonResourceURLs.equals(other$nonResourceURLs)) {
               return false;
            }

            Object this$verbs = this.getVerbs();
            Object other$verbs = other.getVerbs();
            if (this$verbs == null) {
               if (other$verbs != null) {
                  return false;
               }
            } else if (!this$verbs.equals(other$verbs)) {
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
      return other instanceof NonResourceRule;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $nonResourceURLs = this.getNonResourceURLs();
      result = result * 59 + ($nonResourceURLs == null ? 43 : $nonResourceURLs.hashCode());
      Object $verbs = this.getVerbs();
      result = result * 59 + ($verbs == null ? 43 : $verbs.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
