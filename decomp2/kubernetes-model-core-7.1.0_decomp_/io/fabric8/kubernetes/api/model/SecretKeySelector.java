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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"key", "name", "optional"})
public class SecretKeySelector implements Editable, KubernetesResource {
   @JsonProperty("key")
   private String key;
   @JsonProperty("name")
   private String name;
   @JsonProperty("optional")
   private Boolean optional;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public SecretKeySelector() {
   }

   public SecretKeySelector(String key, String name, Boolean optional) {
      this.key = key;
      this.name = name;
      this.optional = optional;
   }

   @JsonProperty("key")
   public String getKey() {
      return this.key;
   }

   @JsonProperty("key")
   public void setKey(String key) {
      this.key = key;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("optional")
   public Boolean getOptional() {
      return this.optional;
   }

   @JsonProperty("optional")
   public void setOptional(Boolean optional) {
      this.optional = optional;
   }

   @JsonIgnore
   public SecretKeySelectorBuilder edit() {
      return new SecretKeySelectorBuilder(this);
   }

   @JsonIgnore
   public SecretKeySelectorBuilder toBuilder() {
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
      String var10000 = this.getKey();
      return "SecretKeySelector(key=" + var10000 + ", name=" + this.getName() + ", optional=" + this.getOptional() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SecretKeySelector)) {
         return false;
      } else {
         SecretKeySelector other = (SecretKeySelector)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$optional = this.getOptional();
            Object other$optional = other.getOptional();
            if (this$optional == null) {
               if (other$optional != null) {
                  return false;
               }
            } else if (!this$optional.equals(other$optional)) {
               return false;
            }

            Object this$key = this.getKey();
            Object other$key = other.getKey();
            if (this$key == null) {
               if (other$key != null) {
                  return false;
               }
            } else if (!this$key.equals(other$key)) {
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
      return other instanceof SecretKeySelector;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $optional = this.getOptional();
      result = result * 59 + ($optional == null ? 43 : $optional.hashCode());
      Object $key = this.getKey();
      result = result * 59 + ($key == null ? 43 : $key.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
