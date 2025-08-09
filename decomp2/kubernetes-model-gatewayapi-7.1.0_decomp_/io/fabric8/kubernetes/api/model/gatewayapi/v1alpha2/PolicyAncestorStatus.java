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
import io.fabric8.kubernetes.api.model.Condition;
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
@JsonPropertyOrder({"ancestorRef", "conditions", "controllerName"})
public class PolicyAncestorStatus implements Editable, KubernetesResource {
   @JsonProperty("ancestorRef")
   private ParentReference ancestorRef;
   @JsonProperty("conditions")
   @JsonInclude(Include.NON_EMPTY)
   private List conditions = new ArrayList();
   @JsonProperty("controllerName")
   private String controllerName;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PolicyAncestorStatus() {
   }

   public PolicyAncestorStatus(ParentReference ancestorRef, List conditions, String controllerName) {
      this.ancestorRef = ancestorRef;
      this.conditions = conditions;
      this.controllerName = controllerName;
   }

   @JsonProperty("ancestorRef")
   public ParentReference getAncestorRef() {
      return this.ancestorRef;
   }

   @JsonProperty("ancestorRef")
   public void setAncestorRef(ParentReference ancestorRef) {
      this.ancestorRef = ancestorRef;
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

   @JsonIgnore
   public PolicyAncestorStatusBuilder edit() {
      return new PolicyAncestorStatusBuilder(this);
   }

   @JsonIgnore
   public PolicyAncestorStatusBuilder toBuilder() {
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
      ParentReference var10000 = this.getAncestorRef();
      return "PolicyAncestorStatus(ancestorRef=" + var10000 + ", conditions=" + this.getConditions() + ", controllerName=" + this.getControllerName() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PolicyAncestorStatus)) {
         return false;
      } else {
         PolicyAncestorStatus other = (PolicyAncestorStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$ancestorRef = this.getAncestorRef();
            Object other$ancestorRef = other.getAncestorRef();
            if (this$ancestorRef == null) {
               if (other$ancestorRef != null) {
                  return false;
               }
            } else if (!this$ancestorRef.equals(other$ancestorRef)) {
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

            Object this$controllerName = this.getControllerName();
            Object other$controllerName = other.getControllerName();
            if (this$controllerName == null) {
               if (other$controllerName != null) {
                  return false;
               }
            } else if (!this$controllerName.equals(other$controllerName)) {
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
      return other instanceof PolicyAncestorStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $ancestorRef = this.getAncestorRef();
      result = result * 59 + ($ancestorRef == null ? 43 : $ancestorRef.hashCode());
      Object $conditions = this.getConditions();
      result = result * 59 + ($conditions == null ? 43 : $conditions.hashCode());
      Object $controllerName = this.getControllerName();
      result = result * 59 + ($controllerName == null ? 43 : $controllerName.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
