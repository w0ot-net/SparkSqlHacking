package io.fabric8.kubernetes.api.model.extensions;

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
@JsonPropertyOrder({"replicas"})
public class ScaleSpec implements Editable, KubernetesResource {
   @JsonProperty("replicas")
   private Integer replicas;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ScaleSpec() {
   }

   public ScaleSpec(Integer replicas) {
      this.replicas = replicas;
   }

   @JsonProperty("replicas")
   public Integer getReplicas() {
      return this.replicas;
   }

   @JsonProperty("replicas")
   public void setReplicas(Integer replicas) {
      this.replicas = replicas;
   }

   @JsonIgnore
   public ScaleSpecBuilder edit() {
      return new ScaleSpecBuilder(this);
   }

   @JsonIgnore
   public ScaleSpecBuilder toBuilder() {
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
      Integer var10000 = this.getReplicas();
      return "ScaleSpec(replicas=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ScaleSpec)) {
         return false;
      } else {
         ScaleSpec other = (ScaleSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$replicas = this.getReplicas();
            Object other$replicas = other.getReplicas();
            if (this$replicas == null) {
               if (other$replicas != null) {
                  return false;
               }
            } else if (!this$replicas.equals(other$replicas)) {
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
      return other instanceof ScaleSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $replicas = this.getReplicas();
      result = result * 59 + ($replicas == null ? 43 : $replicas.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
