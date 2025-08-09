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
@JsonPropertyOrder({"conditions", "controllerName", "parentRef"})
public class RouteParentStatus implements Editable, KubernetesResource {
   @JsonProperty("conditions")
   @JsonInclude(Include.NON_EMPTY)
   private List conditions = new ArrayList();
   @JsonProperty("controllerName")
   private String controllerName;
   @JsonProperty("parentRef")
   private ParentReference parentRef;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public RouteParentStatus() {
   }

   public RouteParentStatus(List conditions, String controllerName, ParentReference parentRef) {
      this.conditions = conditions;
      this.controllerName = controllerName;
      this.parentRef = parentRef;
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

   @JsonProperty("controllerName")
   public String getControllerName() {
      return this.controllerName;
   }

   @JsonProperty("controllerName")
   public void setControllerName(String controllerName) {
      this.controllerName = controllerName;
   }

   @JsonProperty("parentRef")
   public ParentReference getParentRef() {
      return this.parentRef;
   }

   @JsonProperty("parentRef")
   public void setParentRef(ParentReference parentRef) {
      this.parentRef = parentRef;
   }

   @JsonIgnore
   public RouteParentStatusBuilder edit() {
      return new RouteParentStatusBuilder(this);
   }

   @JsonIgnore
   public RouteParentStatusBuilder toBuilder() {
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
      List var10000 = this.getConditions();
      return "RouteParentStatus(conditions=" + var10000 + ", controllerName=" + this.getControllerName() + ", parentRef=" + this.getParentRef() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RouteParentStatus)) {
         return false;
      } else {
         RouteParentStatus other = (RouteParentStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$conditions = this.getConditions();
            Object other$conditions = other.getConditions();
            if (this$conditions == null) {
               if (other$conditions != null) {
                  return false;
               }
            } else if (!this$conditions.equals(other$conditions)) {
               return false;
            }

            Object this$controllerName = this.getControllerName();
            Object other$controllerName = other.getControllerName();
            if (this$controllerName == null) {
               if (other$controllerName != null) {
                  return false;
               }
            } else if (!this$controllerName.equals(other$controllerName)) {
               return false;
            }

            Object this$parentRef = this.getParentRef();
            Object other$parentRef = other.getParentRef();
            if (this$parentRef == null) {
               if (other$parentRef != null) {
                  return false;
               }
            } else if (!this$parentRef.equals(other$parentRef)) {
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
      return other instanceof RouteParentStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $conditions = this.getConditions();
      result = result * 59 + ($conditions == null ? 43 : $conditions.hashCode());
      Object $controllerName = this.getControllerName();
      result = result * 59 + ($controllerName == null ? 43 : $controllerName.hashCode());
      Object $parentRef = this.getParentRef();
      result = result * 59 + ($parentRef == null ? 43 : $parentRef.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
