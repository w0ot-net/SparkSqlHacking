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
@JsonPropertyOrder({"kinds", "namespaces"})
public class AllowedRoutes implements Editable, KubernetesResource {
   @JsonProperty("kinds")
   @JsonInclude(Include.NON_EMPTY)
   private List kinds = new ArrayList();
   @JsonProperty("namespaces")
   private RouteNamespaces namespaces;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public AllowedRoutes() {
   }

   public AllowedRoutes(List kinds, RouteNamespaces namespaces) {
      this.kinds = kinds;
      this.namespaces = namespaces;
   }

   @JsonProperty("kinds")
   @JsonInclude(Include.NON_EMPTY)
   public List getKinds() {
      return this.kinds;
   }

   @JsonProperty("kinds")
   public void setKinds(List kinds) {
      this.kinds = kinds;
   }

   @JsonProperty("namespaces")
   public RouteNamespaces getNamespaces() {
      return this.namespaces;
   }

   @JsonProperty("namespaces")
   public void setNamespaces(RouteNamespaces namespaces) {
      this.namespaces = namespaces;
   }

   @JsonIgnore
   public AllowedRoutesBuilder edit() {
      return new AllowedRoutesBuilder(this);
   }

   @JsonIgnore
   public AllowedRoutesBuilder toBuilder() {
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
      List var10000 = this.getKinds();
      return "AllowedRoutes(kinds=" + var10000 + ", namespaces=" + this.getNamespaces() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AllowedRoutes)) {
         return false;
      } else {
         AllowedRoutes other = (AllowedRoutes)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$kinds = this.getKinds();
            Object other$kinds = other.getKinds();
            if (this$kinds == null) {
               if (other$kinds != null) {
                  return false;
               }
            } else if (!this$kinds.equals(other$kinds)) {
               return false;
            }

            Object this$namespaces = this.getNamespaces();
            Object other$namespaces = other.getNamespaces();
            if (this$namespaces == null) {
               if (other$namespaces != null) {
                  return false;
               }
            } else if (!this$namespaces.equals(other$namespaces)) {
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
      return other instanceof AllowedRoutes;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $kinds = this.getKinds();
      result = result * 59 + ($kinds == null ? 43 : $kinds.hashCode());
      Object $namespaces = this.getNamespaces();
      result = result * 59 + ($namespaces == null ? 43 : $namespaces.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
