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
@JsonPropertyOrder({"description", "format", "jsonPath", "name", "priority", "type"})
public class CustomResourceColumnDefinition implements Editable, KubernetesResource {
   @JsonProperty("description")
   private String description;
   @JsonProperty("format")
   private String format;
   @JsonProperty("jsonPath")
   private String jsonPath;
   @JsonProperty("name")
   private String name;
   @JsonProperty("priority")
   private Integer priority;
   @JsonProperty("type")
   private String type;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public CustomResourceColumnDefinition() {
   }

   public CustomResourceColumnDefinition(String description, String format, String jsonPath, String name, Integer priority, String type) {
      this.description = description;
      this.format = format;
      this.jsonPath = jsonPath;
      this.name = name;
      this.priority = priority;
      this.type = type;
   }

   @JsonProperty("description")
   public String getDescription() {
      return this.description;
   }

   @JsonProperty("description")
   public void setDescription(String description) {
      this.description = description;
   }

   @JsonProperty("format")
   public String getFormat() {
      return this.format;
   }

   @JsonProperty("format")
   public void setFormat(String format) {
      this.format = format;
   }

   @JsonProperty("jsonPath")
   public String getJsonPath() {
      return this.jsonPath;
   }

   @JsonProperty("jsonPath")
   public void setJsonPath(String jsonPath) {
      this.jsonPath = jsonPath;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("priority")
   public Integer getPriority() {
      return this.priority;
   }

   @JsonProperty("priority")
   public void setPriority(Integer priority) {
      this.priority = priority;
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
   public CustomResourceColumnDefinitionBuilder edit() {
      return new CustomResourceColumnDefinitionBuilder(this);
   }

   @JsonIgnore
   public CustomResourceColumnDefinitionBuilder toBuilder() {
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
      String var10000 = this.getDescription();
      return "CustomResourceColumnDefinition(description=" + var10000 + ", format=" + this.getFormat() + ", jsonPath=" + this.getJsonPath() + ", name=" + this.getName() + ", priority=" + this.getPriority() + ", type=" + this.getType() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CustomResourceColumnDefinition)) {
         return false;
      } else {
         CustomResourceColumnDefinition other = (CustomResourceColumnDefinition)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$priority = this.getPriority();
            Object other$priority = other.getPriority();
            if (this$priority == null) {
               if (other$priority != null) {
                  return false;
               }
            } else if (!this$priority.equals(other$priority)) {
               return false;
            }

            Object this$description = this.getDescription();
            Object other$description = other.getDescription();
            if (this$description == null) {
               if (other$description != null) {
                  return false;
               }
            } else if (!this$description.equals(other$description)) {
               return false;
            }

            Object this$format = this.getFormat();
            Object other$format = other.getFormat();
            if (this$format == null) {
               if (other$format != null) {
                  return false;
               }
            } else if (!this$format.equals(other$format)) {
               return false;
            }

            Object this$jsonPath = this.getJsonPath();
            Object other$jsonPath = other.getJsonPath();
            if (this$jsonPath == null) {
               if (other$jsonPath != null) {
                  return false;
               }
            } else if (!this$jsonPath.equals(other$jsonPath)) {
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
      return other instanceof CustomResourceColumnDefinition;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $priority = this.getPriority();
      result = result * 59 + ($priority == null ? 43 : $priority.hashCode());
      Object $description = this.getDescription();
      result = result * 59 + ($description == null ? 43 : $description.hashCode());
      Object $format = this.getFormat();
      result = result * 59 + ($format == null ? 43 : $format.hashCode());
      Object $jsonPath = this.getJsonPath();
      result = result * 59 + ($jsonPath == null ? 43 : $jsonPath.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
