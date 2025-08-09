package io.fabric8.kubernetes.api.model.apps;

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
@JsonPropertyOrder({"rollingUpdate", "type"})
public class DeploymentStrategy implements Editable, KubernetesResource {
   @JsonProperty("rollingUpdate")
   private RollingUpdateDeployment rollingUpdate;
   @JsonProperty("type")
   private String type;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public DeploymentStrategy() {
   }

   public DeploymentStrategy(RollingUpdateDeployment rollingUpdate, String type) {
      this.rollingUpdate = rollingUpdate;
      this.type = type;
   }

   @JsonProperty("rollingUpdate")
   public RollingUpdateDeployment getRollingUpdate() {
      return this.rollingUpdate;
   }

   @JsonProperty("rollingUpdate")
   public void setRollingUpdate(RollingUpdateDeployment rollingUpdate) {
      this.rollingUpdate = rollingUpdate;
   }

   @JsonProperty("type")
   public String getType() {
      return this.type;
   }

   @JsonProperty("type")
   public void setType(String type) {
      this.type = type;
   }

   @JsonIgnore
   public DeploymentStrategyBuilder edit() {
      return new DeploymentStrategyBuilder(this);
   }

   @JsonIgnore
   public DeploymentStrategyBuilder toBuilder() {
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
      RollingUpdateDeployment var10000 = this.getRollingUpdate();
      return "DeploymentStrategy(rollingUpdate=" + var10000 + ", type=" + this.getType() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DeploymentStrategy)) {
         return false;
      } else {
         DeploymentStrategy other = (DeploymentStrategy)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$rollingUpdate = this.getRollingUpdate();
            Object other$rollingUpdate = other.getRollingUpdate();
            if (this$rollingUpdate == null) {
               if (other$rollingUpdate != null) {
                  return false;
               }
            } else if (!this$rollingUpdate.equals(other$rollingUpdate)) {
               return false;
            }

            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
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
      return other instanceof DeploymentStrategy;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $rollingUpdate = this.getRollingUpdate();
      result = result * 59 + ($rollingUpdate == null ? 43 : $rollingUpdate.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
