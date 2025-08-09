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
import io.fabric8.kubernetes.internal.KubernetesDeserializer;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"extension", "name"})
public class NamedExtension implements Editable, KubernetesResource {
   @JsonProperty("extension")
   @JsonDeserialize(
      using = KubernetesDeserializer.class
   )
   private Object extension;
   @JsonProperty("name")
   private String name;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public NamedExtension() {
   }

   public NamedExtension(Object extension, String name) {
      this.extension = extension;
      this.name = name;
   }

   @JsonProperty("extension")
   public Object getExtension() {
      return this.extension;
   }

   @JsonProperty("extension")
   @JsonDeserialize(
      using = KubernetesDeserializer.class
   )
   public void setExtension(Object extension) {
      this.extension = extension;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonIgnore
   public NamedExtensionBuilder edit() {
      return new NamedExtensionBuilder(this);
   }

   @JsonIgnore
   public NamedExtensionBuilder toBuilder() {
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
      Object var10000 = this.getExtension();
      return "NamedExtension(extension=" + var10000 + ", name=" + this.getName() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NamedExtension)) {
         return false;
      } else {
         NamedExtension other = (NamedExtension)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$extension = this.getExtension();
            Object other$extension = other.getExtension();
            if (this$extension == null) {
               if (other$extension != null) {
                  return false;
               }
            } else if (!this$extension.equals(other$extension)) {
               return false;
            }

            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
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
      return other instanceof NamedExtension;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $extension = this.getExtension();
      result = result * 59 + ($extension == null ? 43 : $extension.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
