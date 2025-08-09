package io.fabric8.kubernetes.api.model.authorization.v1beta1;

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
@JsonPropertyOrder({"path", "verb"})
public class NonResourceAttributes implements Editable, KubernetesResource {
   @JsonProperty("path")
   private String path;
   @JsonProperty("verb")
   private String verb;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public NonResourceAttributes() {
   }

   public NonResourceAttributes(String path, String verb) {
      this.path = path;
      this.verb = verb;
   }

   @JsonProperty("path")
   public String getPath() {
      return this.path;
   }

   @JsonProperty("path")
   public void setPath(String path) {
      this.path = path;
   }

   @JsonProperty("verb")
   public String getVerb() {
      return this.verb;
   }

   @JsonProperty("verb")
   public void setVerb(String verb) {
      this.verb = verb;
   }

   @JsonIgnore
   public NonResourceAttributesBuilder edit() {
      return new NonResourceAttributesBuilder(this);
   }

   @JsonIgnore
   public NonResourceAttributesBuilder toBuilder() {
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
      String var10000 = this.getPath();
      return "NonResourceAttributes(path=" + var10000 + ", verb=" + this.getVerb() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NonResourceAttributes)) {
         return false;
      } else {
         NonResourceAttributes other = (NonResourceAttributes)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$path = this.getPath();
            Object other$path = other.getPath();
            if (this$path == null) {
               if (other$path != null) {
                  return false;
               }
            } else if (!this$path.equals(other$path)) {
               return false;
            }

            Object this$verb = this.getVerb();
            Object other$verb = other.getVerb();
            if (this$verb == null) {
               if (other$verb != null) {
                  return false;
               }
            } else if (!this$verb.equals(other$verb)) {
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
      return other instanceof NonResourceAttributes;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $path = this.getPath();
      result = result * 59 + ($path == null ? 43 : $path.hashCode());
      Object $verb = this.getVerb();
      result = result * 59 + ($verb == null ? 43 : $verb.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
