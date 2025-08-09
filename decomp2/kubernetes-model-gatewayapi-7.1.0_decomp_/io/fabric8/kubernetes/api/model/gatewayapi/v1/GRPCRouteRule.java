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
@JsonPropertyOrder({"backendRefs", "filters", "matches", "name", "sessionPersistence"})
public class GRPCRouteRule implements Editable, KubernetesResource {
   @JsonProperty("backendRefs")
   @JsonInclude(Include.NON_EMPTY)
   private List backendRefs = new ArrayList();
   @JsonProperty("filters")
   @JsonInclude(Include.NON_EMPTY)
   private List filters = new ArrayList();
   @JsonProperty("matches")
   @JsonInclude(Include.NON_EMPTY)
   private List matches = new ArrayList();
   @JsonProperty("name")
   private String name;
   @JsonProperty("sessionPersistence")
   private SessionPersistence sessionPersistence;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public GRPCRouteRule() {
   }

   public GRPCRouteRule(List backendRefs, List filters, List matches, String name, SessionPersistence sessionPersistence) {
      this.backendRefs = backendRefs;
      this.filters = filters;
      this.matches = matches;
      this.name = name;
      this.sessionPersistence = sessionPersistence;
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

   @JsonProperty("filters")
   @JsonInclude(Include.NON_EMPTY)
   public List getFilters() {
      return this.filters;
   }

   @JsonProperty("filters")
   public void setFilters(List filters) {
      this.filters = filters;
   }

   @JsonProperty("matches")
   @JsonInclude(Include.NON_EMPTY)
   public List getMatches() {
      return this.matches;
   }

   @JsonProperty("matches")
   public void setMatches(List matches) {
      this.matches = matches;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("sessionPersistence")
   public SessionPersistence getSessionPersistence() {
      return this.sessionPersistence;
   }

   @JsonProperty("sessionPersistence")
   public void setSessionPersistence(SessionPersistence sessionPersistence) {
      this.sessionPersistence = sessionPersistence;
   }

   @JsonIgnore
   public GRPCRouteRuleBuilder edit() {
      return new GRPCRouteRuleBuilder(this);
   }

   @JsonIgnore
   public GRPCRouteRuleBuilder toBuilder() {
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
      return "GRPCRouteRule(backendRefs=" + var10000 + ", filters=" + this.getFilters() + ", matches=" + this.getMatches() + ", name=" + this.getName() + ", sessionPersistence=" + this.getSessionPersistence() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof GRPCRouteRule)) {
         return false;
      } else {
         GRPCRouteRule other = (GRPCRouteRule)o;
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

            Object this$filters = this.getFilters();
            Object other$filters = other.getFilters();
            if (this$filters == null) {
               if (other$filters != null) {
                  return false;
               }
            } else if (!this$filters.equals(other$filters)) {
               return false;
            }

            Object this$matches = this.getMatches();
            Object other$matches = other.getMatches();
            if (this$matches == null) {
               if (other$matches != null) {
                  return false;
               }
            } else if (!this$matches.equals(other$matches)) {
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

            Object this$sessionPersistence = this.getSessionPersistence();
            Object other$sessionPersistence = other.getSessionPersistence();
            if (this$sessionPersistence == null) {
               if (other$sessionPersistence != null) {
                  return false;
               }
            } else if (!this$sessionPersistence.equals(other$sessionPersistence)) {
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
      return other instanceof GRPCRouteRule;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $backendRefs = this.getBackendRefs();
      result = result * 59 + ($backendRefs == null ? 43 : $backendRefs.hashCode());
      Object $filters = this.getFilters();
      result = result * 59 + ($filters == null ? 43 : $filters.hashCode());
      Object $matches = this.getMatches();
      result = result * 59 + ($matches == null ? 43 : $matches.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $sessionPersistence = this.getSessionPersistence();
      result = result * 59 + ($sessionPersistence == null ? 43 : $sessionPersistence.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
