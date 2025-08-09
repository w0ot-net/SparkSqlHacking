package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

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
import io.fabric8.kubernetes.api.model.gatewayapi.v1.ParentReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"hostnames", "parentRefs", "rules"})
public class TLSRouteSpec implements Editable, KubernetesResource {
   @JsonProperty("hostnames")
   @JsonInclude(Include.NON_EMPTY)
   private List hostnames = new ArrayList();
   @JsonProperty("parentRefs")
   @JsonInclude(Include.NON_EMPTY)
   private List parentRefs = new ArrayList();
   @JsonProperty("rules")
   @JsonInclude(Include.NON_EMPTY)
   private List rules = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public TLSRouteSpec() {
   }

   public TLSRouteSpec(List hostnames, List parentRefs, List rules) {
      this.hostnames = hostnames;
      this.parentRefs = parentRefs;
      this.rules = rules;
   }

   @JsonProperty("hostnames")
   @JsonInclude(Include.NON_EMPTY)
   public List getHostnames() {
      return this.hostnames;
   }

   @JsonProperty("hostnames")
   public void setHostnames(List hostnames) {
      this.hostnames = hostnames;
   }

   @JsonProperty("parentRefs")
   @JsonInclude(Include.NON_EMPTY)
   public List getParentRefs() {
      return this.parentRefs;
   }

   @JsonProperty("parentRefs")
   public void setParentRefs(List parentRefs) {
      this.parentRefs = parentRefs;
   }

   @JsonProperty("rules")
   @JsonInclude(Include.NON_EMPTY)
   public List getRules() {
      return this.rules;
   }

   @JsonProperty("rules")
   public void setRules(List rules) {
      this.rules = rules;
   }

   @JsonIgnore
   public TLSRouteSpecBuilder edit() {
      return new TLSRouteSpecBuilder(this);
   }

   @JsonIgnore
   public TLSRouteSpecBuilder toBuilder() {
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
      List var10000 = this.getHostnames();
      return "TLSRouteSpec(hostnames=" + var10000 + ", parentRefs=" + this.getParentRefs() + ", rules=" + this.getRules() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TLSRouteSpec)) {
         return false;
      } else {
         TLSRouteSpec other = (TLSRouteSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$hostnames = this.getHostnames();
            Object other$hostnames = other.getHostnames();
            if (this$hostnames == null) {
               if (other$hostnames != null) {
                  return false;
               }
            } else if (!this$hostnames.equals(other$hostnames)) {
               return false;
            }

            Object this$parentRefs = this.getParentRefs();
            Object other$parentRefs = other.getParentRefs();
            if (this$parentRefs == null) {
               if (other$parentRefs != null) {
                  return false;
               }
            } else if (!this$parentRefs.equals(other$parentRefs)) {
               return false;
            }

            Object this$rules = this.getRules();
            Object other$rules = other.getRules();
            if (this$rules == null) {
               if (other$rules != null) {
                  return false;
               }
            } else if (!this$rules.equals(other$rules)) {
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
      return other instanceof TLSRouteSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $hostnames = this.getHostnames();
      result = result * 59 + ($hostnames == null ? 43 : $hostnames.hashCode());
      Object $parentRefs = this.getParentRefs();
      result = result * 59 + ($parentRefs == null ? 43 : $parentRefs.hashCode());
      Object $rules = this.getRules();
      result = result * 59 + ($rules == null ? 43 : $rules.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
