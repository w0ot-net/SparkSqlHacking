package io.fabric8.kubernetes.api.model.storagemigration.v1alpha1;

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
@JsonPropertyOrder({"conditions", "resourceVersion"})
public class StorageVersionMigrationStatus implements Editable, KubernetesResource {
   @JsonProperty("conditions")
   @JsonInclude(Include.NON_EMPTY)
   private List conditions = new ArrayList();
   @JsonProperty("resourceVersion")
   private String resourceVersion;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public StorageVersionMigrationStatus() {
   }

   public StorageVersionMigrationStatus(List conditions, String resourceVersion) {
      this.conditions = conditions;
      this.resourceVersion = resourceVersion;
   }

   @JsonProperty("conditions")
   @JsonInclude(Include.NON_EMPTY)
   public List getConditions() {
      return this.conditions;
   }

   @JsonProperty("conditions")
   public void setConditions(List conditions) {
      this.conditions = conditions;
   }

   @JsonProperty("resourceVersion")
   public String getResourceVersion() {
      return this.resourceVersion;
   }

   @JsonProperty("resourceVersion")
   public void setResourceVersion(String resourceVersion) {
      this.resourceVersion = resourceVersion;
   }

   @JsonIgnore
   public StorageVersionMigrationStatusBuilder edit() {
      return new StorageVersionMigrationStatusBuilder(this);
   }

   @JsonIgnore
   public StorageVersionMigrationStatusBuilder toBuilder() {
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
      List var10000 = this.getConditions();
      return "StorageVersionMigrationStatus(conditions=" + var10000 + ", resourceVersion=" + this.getResourceVersion() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof StorageVersionMigrationStatus)) {
         return false;
      } else {
         StorageVersionMigrationStatus other = (StorageVersionMigrationStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$conditions = this.getConditions();
            Object other$conditions = other.getConditions();
            if (this$conditions == null) {
               if (other$conditions != null) {
                  return false;
               }
            } else if (!this$conditions.equals(other$conditions)) {
               return false;
            }

            Object this$resourceVersion = this.getResourceVersion();
            Object other$resourceVersion = other.getResourceVersion();
            if (this$resourceVersion == null) {
               if (other$resourceVersion != null) {
                  return false;
               }
            } else if (!this$resourceVersion.equals(other$resourceVersion)) {
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
      return other instanceof StorageVersionMigrationStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $conditions = this.getConditions();
      result = result * 59 + ($conditions == null ? 43 : $conditions.hashCode());
      Object $resourceVersion = this.getResourceVersion();
      result = result * 59 + ($resourceVersion == null ? 43 : $resourceVersion.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
