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
@JsonPropertyOrder({"labelSelector", "name", "optional", "path", "signerName"})
public class ClusterTrustBundleProjection implements Editable, KubernetesResource {
   @JsonProperty("labelSelector")
   private LabelSelector labelSelector;
   @JsonProperty("name")
   private String name;
   @JsonProperty("optional")
   private Boolean optional;
   @JsonProperty("path")
   private String path;
   @JsonProperty("signerName")
   private String signerName;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ClusterTrustBundleProjection() {
   }

   public ClusterTrustBundleProjection(LabelSelector labelSelector, String name, Boolean optional, String path, String signerName) {
      this.labelSelector = labelSelector;
      this.name = name;
      this.optional = optional;
      this.path = path;
      this.signerName = signerName;
   }

   @JsonProperty("labelSelector")
   public LabelSelector getLabelSelector() {
      return this.labelSelector;
   }

   @JsonProperty("labelSelector")
   public void setLabelSelector(LabelSelector labelSelector) {
      this.labelSelector = labelSelector;
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

   @JsonProperty("path")
   public String getPath() {
      return this.path;
   }

   @JsonProperty("path")
   public void setPath(String path) {
      this.path = path;
   }

   @JsonProperty("signerName")
   public String getSignerName() {
      return this.signerName;
   }

   @JsonProperty("signerName")
   public void setSignerName(String signerName) {
      this.signerName = signerName;
   }

   @JsonIgnore
   public ClusterTrustBundleProjectionBuilder edit() {
      return new ClusterTrustBundleProjectionBuilder(this);
   }

   @JsonIgnore
   public ClusterTrustBundleProjectionBuilder toBuilder() {
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
      LabelSelector var10000 = this.getLabelSelector();
      return "ClusterTrustBundleProjection(labelSelector=" + var10000 + ", name=" + this.getName() + ", optional=" + this.getOptional() + ", path=" + this.getPath() + ", signerName=" + this.getSignerName() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ClusterTrustBundleProjection)) {
         return false;
      } else {
         ClusterTrustBundleProjection other = (ClusterTrustBundleProjection)o;
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

            Object this$labelSelector = this.getLabelSelector();
            Object other$labelSelector = other.getLabelSelector();
            if (this$labelSelector == null) {
               if (other$labelSelector != null) {
                  return false;
               }
            } else if (!this$labelSelector.equals(other$labelSelector)) {
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

            Object this$path = this.getPath();
            Object other$path = other.getPath();
            if (this$path == null) {
               if (other$path != null) {
                  return false;
               }
            } else if (!this$path.equals(other$path)) {
               return false;
            }

            Object this$signerName = this.getSignerName();
            Object other$signerName = other.getSignerName();
            if (this$signerName == null) {
               if (other$signerName != null) {
                  return false;
               }
            } else if (!this$signerName.equals(other$signerName)) {
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
      return other instanceof ClusterTrustBundleProjection;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $optional = this.getOptional();
      result = result * 59 + ($optional == null ? 43 : $optional.hashCode());
      Object $labelSelector = this.getLabelSelector();
      result = result * 59 + ($labelSelector == null ? 43 : $labelSelector.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $path = this.getPath();
      result = result * 59 + ($path == null ? 43 : $path.hashCode());
      Object $signerName = this.getSignerName();
      result = result * 59 + ($signerName == null ? 43 : $signerName.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
