package io.fabric8.kubernetes.api.model.apiextensions.v1beta1;

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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonSerialize(
   using = JSONSchemaPropsOrArraySerDe.Serializer.class
)
@JsonDeserialize(
   using = JSONSchemaPropsOrArraySerDe.Deserializer.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"JSONSchemas", "Schema"})
public class JSONSchemaPropsOrArray implements Editable, KubernetesResource {
   @JsonProperty("JSONSchemas")
   @JsonInclude(Include.NON_EMPTY)
   private List jSONSchemas = new ArrayList();
   @JsonProperty("Schema")
   private JSONSchemaProps schema;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public JSONSchemaPropsOrArray() {
   }

   public JSONSchemaPropsOrArray(List jSONSchemas, JSONSchemaProps schema) {
      this.jSONSchemas = jSONSchemas;
      this.schema = schema;
   }

   @JsonProperty("JSONSchemas")
   @JsonInclude(Include.NON_EMPTY)
   public List getJSONSchemas() {
      return this.jSONSchemas;
   }

   @JsonProperty("JSONSchemas")
   public void setJSONSchemas(List jSONSchemas) {
      this.jSONSchemas = jSONSchemas;
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
   public JSONSchemaPropsOrArrayBuilder edit() {
      return new JSONSchemaPropsOrArrayBuilder(this);
   }

   @JsonIgnore
   public JSONSchemaPropsOrArrayBuilder toBuilder() {
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
      List var10000 = this.getJSONSchemas();
      return "JSONSchemaPropsOrArray(jSONSchemas=" + var10000 + ", schema=" + this.getSchema() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof JSONSchemaPropsOrArray)) {
         return false;
      } else {
         JSONSchemaPropsOrArray other = (JSONSchemaPropsOrArray)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$jSONSchemas = this.getJSONSchemas();
            Object other$jSONSchemas = other.getJSONSchemas();
            if (this$jSONSchemas == null) {
               if (other$jSONSchemas != null) {
                  return false;
               }
            } else if (!this$jSONSchemas.equals(other$jSONSchemas)) {
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
      return other instanceof JSONSchemaPropsOrArray;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $jSONSchemas = this.getJSONSchemas();
      result = result * 59 + ($jSONSchemas == null ? 43 : $jSONSchemas.hashCode());
      Object $schema = this.getSchema();
      result = result * 59 + ($schema == null ? 43 : $schema.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
