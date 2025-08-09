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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"controllerName", "description", "parametersRef"})
public class GatewayClassSpec implements Editable, KubernetesResource {
   @JsonProperty("controllerName")
   private String controllerName;
   @JsonProperty("description")
   private String description;
   @JsonProperty("parametersRef")
   private ParametersReference parametersRef;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public GatewayClassSpec() {
   }

   public GatewayClassSpec(String controllerName, String description, ParametersReference parametersRef) {
      this.controllerName = controllerName;
      this.description = description;
      this.parametersRef = parametersRef;
   }

   @JsonProperty("controllerName")
   public String getControllerName() {
      return this.controllerName;
   }

   @JsonProperty("controllerName")
   public void setControllerName(String controllerName) {
      this.controllerName = controllerName;
   }

   @JsonProperty("description")
   public String getDescription() {
      return this.description;
   }

   @JsonProperty("description")
   public void setDescription(String description) {
      this.description = description;
   }

   @JsonProperty("parametersRef")
   public ParametersReference getParametersRef() {
      return this.parametersRef;
   }

   @JsonProperty("parametersRef")
   public void setParametersRef(ParametersReference parametersRef) {
      this.parametersRef = parametersRef;
   }

   @JsonIgnore
   public GatewayClassSpecBuilder edit() {
      return new GatewayClassSpecBuilder(this);
   }

   @JsonIgnore
   public GatewayClassSpecBuilder toBuilder() {
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
      String var10000 = this.getControllerName();
      return "GatewayClassSpec(controllerName=" + var10000 + ", description=" + this.getDescription() + ", parametersRef=" + this.getParametersRef() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof GatewayClassSpec)) {
         return false;
      } else {
         GatewayClassSpec other = (GatewayClassSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$controllerName = this.getControllerName();
            Object other$controllerName = other.getControllerName();
            if (this$controllerName == null) {
               if (other$controllerName != null) {
                  return false;
               }
            } else if (!this$controllerName.equals(other$controllerName)) {
               return false;
            }

            Object this$description = this.getDescription();
            Object other$description = other.getDescription();
            if (this$description == null) {
               if (other$description != null) {
                  return false;
               }
            } else if (!this$description.equals(other$description)) {
               return false;
            }

            Object this$parametersRef = this.getParametersRef();
            Object other$parametersRef = other.getParametersRef();
            if (this$parametersRef == null) {
               if (other$parametersRef != null) {
                  return false;
               }
            } else if (!this$parametersRef.equals(other$parametersRef)) {
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
      return other instanceof GatewayClassSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $controllerName = this.getControllerName();
      result = result * 59 + ($controllerName == null ? 43 : $controllerName.hashCode());
      Object $description = this.getDescription();
      result = result * 59 + ($description == null ? 43 : $description.hashCode());
      Object $parametersRef = this.getParametersRef();
      result = result * 59 + ($parametersRef == null ? 43 : $parametersRef.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
