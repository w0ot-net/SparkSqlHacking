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
import io.fabric8.kubernetes.api.model.gatewayapi.v1.RouteParentStatus;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"parents"})
public class TLSRouteStatus implements Editable, KubernetesResource {
   @JsonProperty("parents")
   @JsonInclude(Include.NON_EMPTY)
   private List parents = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public TLSRouteStatus() {
   }

   public TLSRouteStatus(List parents) {
      this.parents = parents;
   }

   @JsonProperty("parents")
   @JsonInclude(Include.NON_EMPTY)
   public List getParents() {
      return this.parents;
   }

   @JsonProperty("parents")
   public void setParents(List parents) {
      this.parents = parents;
   }

   @JsonIgnore
   public TLSRouteStatusBuilder edit() {
      return new TLSRouteStatusBuilder(this);
   }

   @JsonIgnore
   public TLSRouteStatusBuilder toBuilder() {
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
      List var10000 = this.getParents();
      return "TLSRouteStatus(parents=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TLSRouteStatus)) {
         return false;
      } else {
         TLSRouteStatus other = (TLSRouteStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$parents = this.getParents();
            Object other$parents = other.getParents();
            if (this$parents == null) {
               if (other$parents != null) {
                  return false;
               }
            } else if (!this$parents.equals(other$parents)) {
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
      return other instanceof TLSRouteStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $parents = this.getParents();
      result = result * 59 + ($parents == null ? 43 : $parents.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
