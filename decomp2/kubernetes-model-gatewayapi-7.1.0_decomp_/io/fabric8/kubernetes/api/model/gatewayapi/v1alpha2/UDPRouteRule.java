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
import io.fabric8.kubernetes.api.model.gatewayapi.v1.BackendRef;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"backendRefs", "name"})
public class UDPRouteRule implements Editable, KubernetesResource {
   @JsonProperty("backendRefs")
   @JsonInclude(Include.NON_EMPTY)
   private List backendRefs = new ArrayList();
   @JsonProperty("name")
   private String name;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public UDPRouteRule() {
   }

   public UDPRouteRule(List backendRefs, String name) {
      this.backendRefs = backendRefs;
      this.name = name;
   }

   @JsonProperty("backendRefs")
   @JsonInclude(Include.NON_EMPTY)
   public List getBackendRefs() {
      return this.backendRefs;
   }

   @JsonProperty("backendRefs")
   public void setBackendRefs(List backendRefs) {
      this.backendRefs = backendRefs;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonIgnore
   public UDPRouteRuleBuilder edit() {
      return new UDPRouteRuleBuilder(this);
   }

   @JsonIgnore
   public UDPRouteRuleBuilder toBuilder() {
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
      List var10000 = this.getBackendRefs();
      return "UDPRouteRule(backendRefs=" + var10000 + ", name=" + this.getName() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof UDPRouteRule)) {
         return false;
      } else {
         UDPRouteRule other = (UDPRouteRule)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$backendRefs = this.getBackendRefs();
            Object other$backendRefs = other.getBackendRefs();
            if (this$backendRefs == null) {
               if (other$backendRefs != null) {
                  return false;
               }
            } else if (!this$backendRefs.equals(other$backendRefs)) {
               return false;
            }

            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
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
      return other instanceof UDPRouteRule;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $backendRefs = this.getBackendRefs();
      result = result * 59 + ($backendRefs == null ? 43 : $backendRefs.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
