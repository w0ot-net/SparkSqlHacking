package io.fabric8.kubernetes.api.model.apiextensions.v1;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.fabric8.kubernetes.api.builder.Editable;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonSerialize(
   using = JSONSchemaPropsOrBoolSerDe.Serializer.class
)
@JsonDeserialize(
   using = JSONSchemaPropsOrBoolSerDe.Deserializer.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"Allows", "Schema"})
public class JSONSchemaPropsOrBool implements Editable, KubernetesResource {
   @JsonProperty("Allows")
   private Boolean allows;
   @JsonProperty("Schema")
   private JSONSchemaProps schema;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public JSONSchemaPropsOrBool() {
   }

   public JSONSchemaPropsOrBool(Boolean allows, JSONSchemaProps schema) {
      this.allows = allows;
      this.schema = schema;
   }

   @JsonProperty("Allows")
   public Boolean getAllows() {
      return this.allows;
   }

   @JsonProperty("Allows")
   public void setAllows(Boolean allows) {
      this.allows = allows;
   }

   @JsonProperty("Schema")
   public JSONSchemaProps getSchema() {
      return this.schema;
   }

   @JsonProperty("Schema")
   public void setSchema(JSONSchemaProps schema) {
      this.schema = schema;
   }

   @JsonIgnore
   public JSONSchemaPropsOrBoolBuilder edit() {
      return new JSONSchemaPropsOrBoolBuilder(this);
   }

   @JsonIgnore
   public JSONSchemaPropsOrBoolBuilder toBuilder() {
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
      Boolean var10000 = this.getAllows();
      return "JSONSchemaPropsOrBool(allows=" + var10000 + ", schema=" + this.getSchema() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof JSONSchemaPropsOrBool)) {
         return false;
      } else {
         JSONSchemaPropsOrBool other = (JSONSchemaPropsOrBool)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$allows = this.getAllows();
            Object other$allows = other.getAllows();
            if (this$allows == null) {
               if (other$allows != null) {
                  return false;
               }
            } else if (!this$allows.equals(other$allows)) {
               return false;
            }

            Object this$schema = this.getSchema();
            Object other$schema = other.getSchema();
            if (this$schema == null) {
               if (other$schema != null) {
                  return false;
               }
            } else if (!this$schema.equals(other$schema)) {
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
      return other instanceof JSONSchemaPropsOrBool;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $allows = this.getAllows();
      result = result * 59 + ($allows == null ? 43 : $allows.hashCode());
      Object $schema = this.getSchema();
      result = result * 59 + ($schema == null ? 43 : $schema.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
