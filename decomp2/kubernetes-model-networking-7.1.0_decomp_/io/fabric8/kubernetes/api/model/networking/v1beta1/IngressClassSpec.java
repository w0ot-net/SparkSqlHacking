package io.fabric8.kubernetes.api.model.networking.v1beta1;

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
@JsonPropertyOrder({"controller", "parameters"})
public class IngressClassSpec implements Editable, KubernetesResource {
   @JsonProperty("controller")
   private String controller;
   @JsonProperty("parameters")
   private IngressClassParametersReference parameters;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public IngressClassSpec() {
   }

   public IngressClassSpec(String controller, IngressClassParametersReference parameters) {
      this.controller = controller;
      this.parameters = parameters;
   }

   @JsonProperty("controller")
   public String getController() {
      return this.controller;
   }

   @JsonProperty("controller")
   public void setController(String controller) {
      this.controller = controller;
   }

   @JsonProperty("parameters")
   public IngressClassParametersReference getParameters() {
      return this.parameters;
   }

   @JsonProperty("parameters")
   public void setParameters(IngressClassParametersReference parameters) {
      this.parameters = parameters;
   }

   @JsonIgnore
   public IngressClassSpecBuilder edit() {
      return new IngressClassSpecBuilder(this);
   }

   @JsonIgnore
   public IngressClassSpecBuilder toBuilder() {
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
      String var10000 = this.getController();
      return "IngressClassSpec(controller=" + var10000 + ", parameters=" + this.getParameters() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof IngressClassSpec)) {
         return false;
      } else {
         IngressClassSpec other = (IngressClassSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$controller = this.getController();
            Object other$controller = other.getController();
            if (this$controller == null) {
               if (other$controller != null) {
                  return false;
               }
            } else if (!this$controller.equals(other$controller)) {
               return false;
            }

            Object this$parameters = this.getParameters();
            Object other$parameters = other.getParameters();
            if (this$parameters == null) {
               if (other$parameters != null) {
                  return false;
               }
            } else if (!this$parameters.equals(other$parameters)) {
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
      return other instanceof IngressClassSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $controller = this.getController();
      result = result * 59 + ($controller == null ? 43 : $controller.hashCode());
      Object $parameters = this.getParameters();
      result = result * 59 + ($parameters == null ? 43 : $parameters.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
