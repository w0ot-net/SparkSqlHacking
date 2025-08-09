package io.fabric8.kubernetes.api.model.apiextensions.v1;

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
@JsonPropertyOrder({"jsonPath"})
public class SelectableField implements Editable, KubernetesResource {
   @JsonProperty("jsonPath")
   private String jsonPath;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public SelectableField() {
   }

   public SelectableField(String jsonPath) {
      this.jsonPath = jsonPath;
   }

   @JsonProperty("jsonPath")
   public String getJsonPath() {
      return this.jsonPath;
   }

   @JsonProperty("jsonPath")
   public void setJsonPath(String jsonPath) {
      this.jsonPath = jsonPath;
   }

   @JsonIgnore
   public SelectableFieldBuilder edit() {
      return new SelectableFieldBuilder(this);
   }

   @JsonIgnore
   public SelectableFieldBuilder toBuilder() {
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
      String var10000 = this.getJsonPath();
      return "SelectableField(jsonPath=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SelectableField)) {
         return false;
      } else {
         SelectableField other = (SelectableField)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$jsonPath = this.getJsonPath();
            Object other$jsonPath = other.getJsonPath();
            if (this$jsonPath == null) {
               if (other$jsonPath != null) {
                  return false;
               }
            } else if (!this$jsonPath.equals(other$jsonPath)) {
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
      return other instanceof SelectableField;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $jsonPath = this.getJsonPath();
      result = result * 59 + ($jsonPath == null ? 43 : $jsonPath.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
