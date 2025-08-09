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
import io.fabric8.kubernetes.api.model.Condition;
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
@JsonPropertyOrder({"attachedRoutes", "conditions", "name", "supportedKinds"})
public class ListenerStatus implements Editable, KubernetesResource {
   @JsonProperty("attachedRoutes")
   private Integer attachedRoutes;
   @JsonProperty("conditions")
   @JsonInclude(Include.NON_EMPTY)
   private List conditions = new ArrayList();
   @JsonProperty("name")
   private String name;
   @JsonProperty("supportedKinds")
   @JsonInclude(Include.NON_EMPTY)
   private List supportedKinds = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ListenerStatus() {
   }

   public ListenerStatus(Integer attachedRoutes, List conditions, String name, List supportedKinds) {
      this.attachedRoutes = attachedRoutes;
      this.conditions = conditions;
      this.name = name;
      this.supportedKinds = supportedKinds;
   }

   @JsonProperty("attachedRoutes")
   public Integer getAttachedRoutes() {
      return this.attachedRoutes;
   }

   @JsonProperty("attachedRoutes")
   public void setAttachedRoutes(Integer attachedRoutes) {
      this.attachedRoutes = attachedRoutes;
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

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("supportedKinds")
   @JsonInclude(Include.NON_EMPTY)
   public List getSupportedKinds() {
      return this.supportedKinds;
   }

   @JsonProperty("supportedKinds")
   public void setSupportedKinds(List supportedKinds) {
      this.supportedKinds = supportedKinds;
   }

   @JsonIgnore
   public ListenerStatusBuilder edit() {
      return new ListenerStatusBuilder(this);
   }

   @JsonIgnore
   public ListenerStatusBuilder toBuilder() {
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
      Integer var10000 = this.getAttachedRoutes();
      return "ListenerStatus(attachedRoutes=" + var10000 + ", conditions=" + this.getConditions() + ", name=" + this.getName() + ", supportedKinds=" + this.getSupportedKinds() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ListenerStatus)) {
         return false;
      } else {
         ListenerStatus other = (ListenerStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$attachedRoutes = this.getAttachedRoutes();
            Object other$attachedRoutes = other.getAttachedRoutes();
            if (this$attachedRoutes == null) {
               if (other$attachedRoutes != null) {
                  return false;
               }
            } else if (!this$attachedRoutes.equals(other$attachedRoutes)) {
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

            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
               return false;
            }

            Object this$supportedKinds = this.getSupportedKinds();
            Object other$supportedKinds = other.getSupportedKinds();
            if (this$supportedKinds == null) {
               if (other$supportedKinds != null) {
                  return false;
               }
            } else if (!this$supportedKinds.equals(other$supportedKinds)) {
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
      return other instanceof ListenerStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $attachedRoutes = this.getAttachedRoutes();
      result = result * 59 + ($attachedRoutes == null ? 43 : $attachedRoutes.hashCode());
      Object $conditions = this.getConditions();
      result = result * 59 + ($conditions == null ? 43 : $conditions.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $supportedKinds = this.getSupportedKinds();
      result = result * 59 + ($supportedKinds == null ? 43 : $supportedKinds.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
