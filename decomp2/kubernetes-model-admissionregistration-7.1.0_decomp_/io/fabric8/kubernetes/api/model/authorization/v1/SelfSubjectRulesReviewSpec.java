package io.fabric8.kubernetes.api.model.authorization.v1;

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
@JsonPropertyOrder({"namespace"})
public class SelfSubjectRulesReviewSpec implements Editable, KubernetesResource {
   @JsonProperty("namespace")
   private String namespace;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public SelfSubjectRulesReviewSpec() {
   }

   public SelfSubjectRulesReviewSpec(String namespace) {
      this.namespace = namespace;
   }

   @JsonProperty("namespace")
   public String getNamespace() {
      return this.namespace;
   }

   @JsonProperty("namespace")
   public void setNamespace(String namespace) {
      this.namespace = namespace;
   }

   @JsonIgnore
   public SelfSubjectRulesReviewSpecBuilder edit() {
      return new SelfSubjectRulesReviewSpecBuilder(this);
   }

   @JsonIgnore
   public SelfSubjectRulesReviewSpecBuilder toBuilder() {
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
      String var10000 = this.getNamespace();
      return "SelfSubjectRulesReviewSpec(namespace=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SelfSubjectRulesReviewSpec)) {
         return false;
      } else {
         SelfSubjectRulesReviewSpec other = (SelfSubjectRulesReviewSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$namespace = this.getNamespace();
            Object other$namespace = other.getNamespace();
            if (this$namespace == null) {
               if (other$namespace != null) {
                  return false;
               }
            } else if (!this$namespace.equals(other$namespace)) {
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
      return other instanceof SelfSubjectRulesReviewSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $namespace = this.getNamespace();
      result = result * 59 + ($namespace == null ? 43 : $namespace.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
