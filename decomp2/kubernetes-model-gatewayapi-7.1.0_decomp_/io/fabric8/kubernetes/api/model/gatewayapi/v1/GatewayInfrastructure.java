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
@JsonPropertyOrder({"annotations", "labels", "parametersRef"})
public class GatewayInfrastructure implements Editable, KubernetesResource {
   @JsonProperty("annotations")
   @JsonInclude(Include.NON_EMPTY)
   private Map annotations = new LinkedHashMap();
   @JsonProperty("labels")
   @JsonInclude(Include.NON_EMPTY)
   private Map labels = new LinkedHashMap();
   @JsonProperty("parametersRef")
   private LocalParametersReference parametersRef;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public GatewayInfrastructure() {
   }

   public GatewayInfrastructure(Map annotations, Map labels, LocalParametersReference parametersRef) {
      this.annotations = annotations;
      this.labels = labels;
      this.parametersRef = parametersRef;
   }

   @JsonProperty("annotations")
   @JsonInclude(Include.NON_EMPTY)
   public Map getAnnotations() {
      return this.annotations;
   }

   @JsonProperty("annotations")
   public void setAnnotations(Map annotations) {
      this.annotations = annotations;
   }

   @JsonProperty("labels")
   @JsonInclude(Include.NON_EMPTY)
   public Map getLabels() {
      return this.labels;
   }

   @JsonProperty("labels")
   public void setLabels(Map labels) {
      this.labels = labels;
   }

   @JsonProperty("parametersRef")
   public LocalParametersReference getParametersRef() {
      return this.parametersRef;
   }

   @JsonProperty("parametersRef")
   public void setParametersRef(LocalParametersReference parametersRef) {
      this.parametersRef = parametersRef;
   }

   @JsonIgnore
   public GatewayInfrastructureBuilder edit() {
      return new GatewayInfrastructureBuilder(this);
   }

   @JsonIgnore
   public GatewayInfrastructureBuilder toBuilder() {
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
      Map var10000 = this.getAnnotations();
      return "GatewayInfrastructure(annotations=" + var10000 + ", labels=" + this.getLabels() + ", parametersRef=" + this.getParametersRef() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof GatewayInfrastructure)) {
         return false;
      } else {
         GatewayInfrastructure other = (GatewayInfrastructure)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$annotations = this.getAnnotations();
            Object other$annotations = other.getAnnotations();
            if (this$annotations == null) {
               if (other$annotations != null) {
                  return false;
               }
            } else if (!this$annotations.equals(other$annotations)) {
               return false;
            }

            Object this$labels = this.getLabels();
            Object other$labels = other.getLabels();
            if (this$labels == null) {
               if (other$labels != null) {
                  return false;
               }
            } else if (!this$labels.equals(other$labels)) {
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
      return other instanceof GatewayInfrastructure;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $annotations = this.getAnnotations();
      result = result * 59 + ($annotations == null ? 43 : $annotations.hashCode());
      Object $labels = this.getLabels();
      result = result * 59 + ($labels == null ? 43 : $labels.hashCode());
      Object $parametersRef = this.getParametersRef();
      result = result * 59 + ($parametersRef == null ? 43 : $parametersRef.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
