package io.fabric8.kubernetes.api.model;

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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"finalizers"})
public class NamespaceSpec implements Editable, KubernetesResource {
   @JsonProperty("finalizers")
   @JsonInclude(Include.NON_EMPTY)
   private List finalizers = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public NamespaceSpec() {
   }

   public NamespaceSpec(List finalizers) {
      this.finalizers = finalizers;
   }

   @JsonProperty("finalizers")
   @JsonInclude(Include.NON_EMPTY)
   public List getFinalizers() {
      return this.finalizers;
   }

   @JsonProperty("finalizers")
   public void setFinalizers(List finalizers) {
      this.finalizers = finalizers;
   }

   @JsonIgnore
   public NamespaceSpecBuilder edit() {
      return new NamespaceSpecBuilder(this);
   }

   @JsonIgnore
   public NamespaceSpecBuilder toBuilder() {
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
      List var10000 = this.getFinalizers();
      return "NamespaceSpec(finalizers=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NamespaceSpec)) {
         return false;
      } else {
         NamespaceSpec other = (NamespaceSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$finalizers = this.getFinalizers();
            Object other$finalizers = other.getFinalizers();
            if (this$finalizers == null) {
               if (other$finalizers != null) {
                  return false;
               }
            } else if (!this$finalizers.equals(other$finalizers)) {
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
      return other instanceof NamespaceSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $finalizers = this.getFinalizers();
      result = result * 59 + ($finalizers == null ? 43 : $finalizers.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
