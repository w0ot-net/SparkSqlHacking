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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"additionalPrinterColumns", "deprecated", "deprecationWarning", "name", "schema", "selectableFields", "served", "storage", "subresources"})
public class CustomResourceDefinitionVersion implements Editable, KubernetesResource {
   @JsonProperty("additionalPrinterColumns")
   @JsonInclude(Include.NON_EMPTY)
   private List additionalPrinterColumns = new ArrayList();
   @JsonProperty("deprecated")
   private Boolean deprecated;
   @JsonProperty("deprecationWarning")
   private String deprecationWarning;
   @JsonProperty("name")
   private String name;
   @JsonProperty("schema")
   private CustomResourceValidation schema;
   @JsonProperty("selectableFields")
   @JsonInclude(Include.NON_EMPTY)
   private List selectableFields = new ArrayList();
   @JsonProperty("served")
   private Boolean served;
   @JsonProperty("storage")
   private Boolean storage;
   @JsonProperty("subresources")
   private CustomResourceSubresources subresources;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public CustomResourceDefinitionVersion() {
   }

   public CustomResourceDefinitionVersion(List additionalPrinterColumns, Boolean deprecated, String deprecationWarning, String name, CustomResourceValidation schema, List selectableFields, Boolean served, Boolean storage, CustomResourceSubresources subresources) {
      this.additionalPrinterColumns = additionalPrinterColumns;
      this.deprecated = deprecated;
      this.deprecationWarning = deprecationWarning;
      this.name = name;
      this.schema = schema;
      this.selectableFields = selectableFields;
      this.served = served;
      this.storage = storage;
      this.subresources = subresources;
   }

   @JsonProperty("additionalPrinterColumns")
   @JsonInclude(Include.NON_EMPTY)
   public List getAdditionalPrinterColumns() {
      return this.additionalPrinterColumns;
   }

   @JsonProperty("additionalPrinterColumns")
   public void setAdditionalPrinterColumns(List additionalPrinterColumns) {
      this.additionalPrinterColumns = additionalPrinterColumns;
   }

   @JsonProperty("deprecated")
   public Boolean getDeprecated() {
      return this.deprecated;
   }

   @JsonProperty("deprecated")
   public void setDeprecated(Boolean deprecated) {
      this.deprecated = deprecated;
   }

   @JsonProperty("deprecationWarning")
   public String getDeprecationWarning() {
      return this.deprecationWarning;
   }

   @JsonProperty("deprecationWarning")
   public void setDeprecationWarning(String deprecationWarning) {
      this.deprecationWarning = deprecationWarning;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("schema")
   public CustomResourceValidation getSchema() {
      return this.schema;
   }

   @JsonProperty("schema")
   public void setSchema(CustomResourceValidation schema) {
      this.schema = schema;
   }

   @JsonProperty("selectableFields")
   @JsonInclude(Include.NON_EMPTY)
   public List getSelectableFields() {
      return this.selectableFields;
   }

   @JsonProperty("selectableFields")
   public void setSelectableFields(List selectableFields) {
      this.selectableFields = selectableFields;
   }

   @JsonProperty("served")
   public Boolean getServed() {
      return this.served;
   }

   @JsonProperty("served")
   public void setServed(Boolean served) {
      this.served = served;
   }

   @JsonProperty("storage")
   public Boolean getStorage() {
      return this.storage;
   }

   @JsonProperty("storage")
   public void setStorage(Boolean storage) {
      this.storage = storage;
   }

   @JsonProperty("subresources")
   public CustomResourceSubresources getSubresources() {
      return this.subresources;
   }

   @JsonProperty("subresources")
   public void setSubresources(CustomResourceSubresources subresources) {
      this.subresources = subresources;
   }

   @JsonIgnore
   public CustomResourceDefinitionVersionBuilder edit() {
      return new CustomResourceDefinitionVersionBuilder(this);
   }

   @JsonIgnore
   public CustomResourceDefinitionVersionBuilder toBuilder() {
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
      List var10000 = this.getAdditionalPrinterColumns();
      return "CustomResourceDefinitionVersion(additionalPrinterColumns=" + var10000 + ", deprecated=" + this.getDeprecated() + ", deprecationWarning=" + this.getDeprecationWarning() + ", name=" + this.getName() + ", schema=" + this.getSchema() + ", selectableFields=" + this.getSelectableFields() + ", served=" + this.getServed() + ", storage=" + this.getStorage() + ", subresources=" + this.getSubresources() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CustomResourceDefinitionVersion)) {
         return false;
      } else {
         CustomResourceDefinitionVersion other = (CustomResourceDefinitionVersion)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$deprecated = this.getDeprecated();
            Object other$deprecated = other.getDeprecated();
            if (this$deprecated == null) {
               if (other$deprecated != null) {
                  return false;
               }
            } else if (!this$deprecated.equals(other$deprecated)) {
               return false;
            }

            Object this$served = this.getServed();
            Object other$served = other.getServed();
            if (this$served == null) {
               if (other$served != null) {
                  return false;
               }
            } else if (!this$served.equals(other$served)) {
               return false;
            }

            Object this$storage = this.getStorage();
            Object other$storage = other.getStorage();
            if (this$storage == null) {
               if (other$storage != null) {
                  return false;
               }
            } else if (!this$storage.equals(other$storage)) {
               return false;
            }

            Object this$additionalPrinterColumns = this.getAdditionalPrinterColumns();
            Object other$additionalPrinterColumns = other.getAdditionalPrinterColumns();
            if (this$additionalPrinterColumns == null) {
               if (other$additionalPrinterColumns != null) {
                  return false;
               }
            } else if (!this$additionalPrinterColumns.equals(other$additionalPrinterColumns)) {
               return false;
            }

            Object this$deprecationWarning = this.getDeprecationWarning();
            Object other$deprecationWarning = other.getDeprecationWarning();
            if (this$deprecationWarning == null) {
               if (other$deprecationWarning != null) {
                  return false;
               }
            } else if (!this$deprecationWarning.equals(other$deprecationWarning)) {
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

            Object this$schema = this.getSchema();
            Object other$schema = other.getSchema();
            if (this$schema == null) {
               if (other$schema != null) {
                  return false;
               }
            } else if (!this$schema.equals(other$schema)) {
               return false;
            }

            Object this$selectableFields = this.getSelectableFields();
            Object other$selectableFields = other.getSelectableFields();
            if (this$selectableFields == null) {
               if (other$selectableFields != null) {
                  return false;
               }
            } else if (!this$selectableFields.equals(other$selectableFields)) {
               return false;
            }

            Object this$subresources = this.getSubresources();
            Object other$subresources = other.getSubresources();
            if (this$subresources == null) {
               if (other$subresources != null) {
                  return false;
               }
            } else if (!this$subresources.equals(other$subresources)) {
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
      return other instanceof CustomResourceDefinitionVersion;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $deprecated = this.getDeprecated();
      result = result * 59 + ($deprecated == null ? 43 : $deprecated.hashCode());
      Object $served = this.getServed();
      result = result * 59 + ($served == null ? 43 : $served.hashCode());
      Object $storage = this.getStorage();
      result = result * 59 + ($storage == null ? 43 : $storage.hashCode());
      Object $additionalPrinterColumns = this.getAdditionalPrinterColumns();
      result = result * 59 + ($additionalPrinterColumns == null ? 43 : $additionalPrinterColumns.hashCode());
      Object $deprecationWarning = this.getDeprecationWarning();
      result = result * 59 + ($deprecationWarning == null ? 43 : $deprecationWarning.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $schema = this.getSchema();
      result = result * 59 + ($schema == null ? 43 : $schema.hashCode());
      Object $selectableFields = this.getSelectableFields();
      result = result * 59 + ($selectableFields == null ? 43 : $selectableFields.hashCode());
      Object $subresources = this.getSubresources();
      result = result * 59 + ($subresources == null ? 43 : $subresources.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
