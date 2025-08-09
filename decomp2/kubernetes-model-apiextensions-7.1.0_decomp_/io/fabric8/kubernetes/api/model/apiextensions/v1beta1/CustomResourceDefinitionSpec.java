package io.fabric8.kubernetes.api.model.apiextensions.v1beta1;

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
@JsonPropertyOrder({"additionalPrinterColumns", "conversion", "group", "names", "preserveUnknownFields", "scope", "subresources", "validation", "version", "versions"})
public class CustomResourceDefinitionSpec implements Editable, KubernetesResource {
   @JsonProperty("additionalPrinterColumns")
   @JsonInclude(Include.NON_EMPTY)
   private List additionalPrinterColumns = new ArrayList();
   @JsonProperty("conversion")
   private CustomResourceConversion conversion;
   @JsonProperty("group")
   private String group;
   @JsonProperty("names")
   private CustomResourceDefinitionNames names;
   @JsonProperty("preserveUnknownFields")
   private Boolean preserveUnknownFields;
   @JsonProperty("scope")
   private String scope;
   @JsonProperty("subresources")
   private CustomResourceSubresources subresources;
   @JsonProperty("validation")
   private CustomResourceValidation validation;
   @JsonProperty("version")
   private String version;
   @JsonProperty("versions")
   @JsonInclude(Include.NON_EMPTY)
   private List versions = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public CustomResourceDefinitionSpec() {
   }

   public CustomResourceDefinitionSpec(List additionalPrinterColumns, CustomResourceConversion conversion, String group, CustomResourceDefinitionNames names, Boolean preserveUnknownFields, String scope, CustomResourceSubresources subresources, CustomResourceValidation validation, String version, List versions) {
      this.additionalPrinterColumns = additionalPrinterColumns;
      this.conversion = conversion;
      this.group = group;
      this.names = names;
      this.preserveUnknownFields = preserveUnknownFields;
      this.scope = scope;
      this.subresources = subresources;
      this.validation = validation;
      this.version = version;
      this.versions = versions;
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

   @JsonProperty("conversion")
   public CustomResourceConversion getConversion() {
      return this.conversion;
   }

   @JsonProperty("conversion")
   public void setConversion(CustomResourceConversion conversion) {
      this.conversion = conversion;
   }

   @JsonProperty("group")
   public String getGroup() {
      return this.group;
   }

   @JsonProperty("group")
   public void setGroup(String group) {
      this.group = group;
   }

   @JsonProperty("names")
   public CustomResourceDefinitionNames getNames() {
      return this.names;
   }

   @JsonProperty("names")
   public void setNames(CustomResourceDefinitionNames names) {
      this.names = names;
   }

   @JsonProperty("preserveUnknownFields")
   public Boolean getPreserveUnknownFields() {
      return this.preserveUnknownFields;
   }

   @JsonProperty("preserveUnknownFields")
   public void setPreserveUnknownFields(Boolean preserveUnknownFields) {
      this.preserveUnknownFields = preserveUnknownFields;
   }

   @JsonProperty("scope")
   public String getScope() {
      return this.scope;
   }

   @JsonProperty("scope")
   public void setScope(String scope) {
      this.scope = scope;
   }

   @JsonProperty("subresources")
   public CustomResourceSubresources getSubresources() {
      return this.subresources;
   }

   @JsonProperty("subresources")
   public void setSubresources(CustomResourceSubresources subresources) {
      this.subresources = subresources;
   }

   @JsonProperty("validation")
   public CustomResourceValidation getValidation() {
      return this.validation;
   }

   @JsonProperty("validation")
   public void setValidation(CustomResourceValidation validation) {
      this.validation = validation;
   }

   @JsonProperty("version")
   public String getVersion() {
      return this.version;
   }

   @JsonProperty("version")
   public void setVersion(String version) {
      this.version = version;
   }

   @JsonProperty("versions")
   @JsonInclude(Include.NON_EMPTY)
   public List getVersions() {
      return this.versions;
   }

   @JsonProperty("versions")
   public void setVersions(List versions) {
      this.versions = versions;
   }

   @JsonIgnore
   public CustomResourceDefinitionSpecBuilder edit() {
      return new CustomResourceDefinitionSpecBuilder(this);
   }

   @JsonIgnore
   public CustomResourceDefinitionSpecBuilder toBuilder() {
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
      return "CustomResourceDefinitionSpec(additionalPrinterColumns=" + var10000 + ", conversion=" + this.getConversion() + ", group=" + this.getGroup() + ", names=" + this.getNames() + ", preserveUnknownFields=" + this.getPreserveUnknownFields() + ", scope=" + this.getScope() + ", subresources=" + this.getSubresources() + ", validation=" + this.getValidation() + ", version=" + this.getVersion() + ", versions=" + this.getVersions() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CustomResourceDefinitionSpec)) {
         return false;
      } else {
         CustomResourceDefinitionSpec other = (CustomResourceDefinitionSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$preserveUnknownFields = this.getPreserveUnknownFields();
            Object other$preserveUnknownFields = other.getPreserveUnknownFields();
            if (this$preserveUnknownFields == null) {
               if (other$preserveUnknownFields != null) {
                  return false;
               }
            } else if (!this$preserveUnknownFields.equals(other$preserveUnknownFields)) {
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

            Object this$conversion = this.getConversion();
            Object other$conversion = other.getConversion();
            if (this$conversion == null) {
               if (other$conversion != null) {
                  return false;
               }
            } else if (!this$conversion.equals(other$conversion)) {
               return false;
            }

            Object this$group = this.getGroup();
            Object other$group = other.getGroup();
            if (this$group == null) {
               if (other$group != null) {
                  return false;
               }
            } else if (!this$group.equals(other$group)) {
               return false;
            }

            Object this$names = this.getNames();
            Object other$names = other.getNames();
            if (this$names == null) {
               if (other$names != null) {
                  return false;
               }
            } else if (!this$names.equals(other$names)) {
               return false;
            }

            Object this$scope = this.getScope();
            Object other$scope = other.getScope();
            if (this$scope == null) {
               if (other$scope != null) {
                  return false;
               }
            } else if (!this$scope.equals(other$scope)) {
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

            Object this$validation = this.getValidation();
            Object other$validation = other.getValidation();
            if (this$validation == null) {
               if (other$validation != null) {
                  return false;
               }
            } else if (!this$validation.equals(other$validation)) {
               return false;
            }

            Object this$version = this.getVersion();
            Object other$version = other.getVersion();
            if (this$version == null) {
               if (other$version != null) {
                  return false;
               }
            } else if (!this$version.equals(other$version)) {
               return false;
            }

            Object this$versions = this.getVersions();
            Object other$versions = other.getVersions();
            if (this$versions == null) {
               if (other$versions != null) {
                  return false;
               }
            } else if (!this$versions.equals(other$versions)) {
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
      return other instanceof CustomResourceDefinitionSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $preserveUnknownFields = this.getPreserveUnknownFields();
      result = result * 59 + ($preserveUnknownFields == null ? 43 : $preserveUnknownFields.hashCode());
      Object $additionalPrinterColumns = this.getAdditionalPrinterColumns();
      result = result * 59 + ($additionalPrinterColumns == null ? 43 : $additionalPrinterColumns.hashCode());
      Object $conversion = this.getConversion();
      result = result * 59 + ($conversion == null ? 43 : $conversion.hashCode());
      Object $group = this.getGroup();
      result = result * 59 + ($group == null ? 43 : $group.hashCode());
      Object $names = this.getNames();
      result = result * 59 + ($names == null ? 43 : $names.hashCode());
      Object $scope = this.getScope();
      result = result * 59 + ($scope == null ? 43 : $scope.hashCode());
      Object $subresources = this.getSubresources();
      result = result * 59 + ($subresources == null ? 43 : $subresources.hashCode());
      Object $validation = this.getValidation();
      result = result * 59 + ($validation == null ? 43 : $validation.hashCode());
      Object $version = this.getVersion();
      result = result * 59 + ($version == null ? 43 : $version.hashCode());
      Object $versions = this.getVersions();
      result = result * 59 + ($versions == null ? 43 : $versions.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
