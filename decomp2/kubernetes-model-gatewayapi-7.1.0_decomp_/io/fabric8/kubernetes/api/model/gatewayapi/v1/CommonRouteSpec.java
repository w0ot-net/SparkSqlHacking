package io.fabric8.kubernetes.api.model.gatewayapi.v1;

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
@JsonPropertyOrder({"parentRefs"})
public class CommonRouteSpec implements Editable, KubernetesResource {
   @JsonProperty("parentRefs")
   @JsonInclude(Include.NON_EMPTY)
   private List parentRefs = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public CommonRouteSpec() {
   }

   public CommonRouteSpec(List parentRefs) {
      this.parentRefs = parentRefs;
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

   @JsonIgnore
   public CommonRouteSpecBuilder edit() {
      return new CommonRouteSpecBuilder(this);
   }

   @JsonIgnore
   public CommonRouteSpecBuilder toBuilder() {
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
      List var10000 = this.getParentRefs();
      return "CommonRouteSpec(parentRefs=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CommonRouteSpec)) {
         return false;
      } else {
         CommonRouteSpec other = (CommonRouteSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$parentRefs = this.getParentRefs();
            Object other$parentRefs = other.getParentRefs();
            if (this$parentRefs == null) {
               if (other$parentRefs != null) {
                  return false;
               }
            } else if (!this$parentRefs.equals(other$parentRefs)) {
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
      return other instanceof CommonRouteSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $parentRefs = this.getParentRefs();
      result = result * 59 + ($parentRefs == null ? 43 : $parentRefs.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
