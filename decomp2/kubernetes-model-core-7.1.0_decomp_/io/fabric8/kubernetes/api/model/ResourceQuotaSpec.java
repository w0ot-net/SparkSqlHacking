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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"hard", "scopeSelector", "scopes"})
public class ResourceQuotaSpec implements Editable, KubernetesResource {
   @JsonProperty("hard")
   @JsonInclude(Include.NON_EMPTY)
   private Map hard = new LinkedHashMap();
   @JsonProperty("scopeSelector")
   private ScopeSelector scopeSelector;
   @JsonProperty("scopes")
   @JsonInclude(Include.NON_EMPTY)
   private List scopes = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ResourceQuotaSpec() {
   }

   public ResourceQuotaSpec(Map hard, ScopeSelector scopeSelector, List scopes) {
      this.hard = hard;
      this.scopeSelector = scopeSelector;
      this.scopes = scopes;
   }

   @JsonProperty("hard")
   @JsonInclude(Include.NON_EMPTY)
   public Map getHard() {
      return this.hard;
   }

   @JsonProperty("hard")
   public void setHard(Map hard) {
      this.hard = hard;
   }

   @JsonProperty("scopeSelector")
   public ScopeSelector getScopeSelector() {
      return this.scopeSelector;
   }

   @JsonProperty("scopeSelector")
   public void setScopeSelector(ScopeSelector scopeSelector) {
      this.scopeSelector = scopeSelector;
   }

   @JsonProperty("scopes")
   @JsonInclude(Include.NON_EMPTY)
   public List getScopes() {
      return this.scopes;
   }

   @JsonProperty("scopes")
   public void setScopes(List scopes) {
      this.scopes = scopes;
   }

   @JsonIgnore
   public ResourceQuotaSpecBuilder edit() {
      return new ResourceQuotaSpecBuilder(this);
   }

   @JsonIgnore
   public ResourceQuotaSpecBuilder toBuilder() {
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
      Map var10000 = this.getHard();
      return "ResourceQuotaSpec(hard=" + var10000 + ", scopeSelector=" + this.getScopeSelector() + ", scopes=" + this.getScopes() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourceQuotaSpec)) {
         return false;
      } else {
         ResourceQuotaSpec other = (ResourceQuotaSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$hard = this.getHard();
            Object other$hard = other.getHard();
            if (this$hard == null) {
               if (other$hard != null) {
                  return false;
               }
            } else if (!this$hard.equals(other$hard)) {
               return false;
            }

            Object this$scopeSelector = this.getScopeSelector();
            Object other$scopeSelector = other.getScopeSelector();
            if (this$scopeSelector == null) {
               if (other$scopeSelector != null) {
                  return false;
               }
            } else if (!this$scopeSelector.equals(other$scopeSelector)) {
               return false;
            }

            Object this$scopes = this.getScopes();
            Object other$scopes = other.getScopes();
            if (this$scopes == null) {
               if (other$scopes != null) {
                  return false;
               }
            } else if (!this$scopes.equals(other$scopes)) {
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
      return other instanceof ResourceQuotaSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $hard = this.getHard();
      result = result * 59 + ($hard == null ? 43 : $hard.hashCode());
      Object $scopeSelector = this.getScopeSelector();
      result = result * 59 + ($scopeSelector == null ? 43 : $scopeSelector.hashCode());
      Object $scopes = this.getScopes();
      result = result * 59 + ($scopes == null ? 43 : $scopes.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
