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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"continueToken", "resource"})
public class StorageVersionMigrationSpec implements Editable, KubernetesResource {
   @JsonProperty("continueToken")
   private String continueToken;
   @JsonProperty("resource")
   private GroupVersionResource resource;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public StorageVersionMigrationSpec() {
   }

   public StorageVersionMigrationSpec(String continueToken, GroupVersionResource resource) {
      this.continueToken = continueToken;
      this.resource = resource;
   }

   @JsonProperty("continueToken")
   public String getContinueToken() {
      return this.continueToken;
   }

   @JsonProperty("continueToken")
   public void setContinueToken(String continueToken) {
      this.continueToken = continueToken;
   }

   @JsonProperty("resource")
   public GroupVersionResource getResource() {
      return this.resource;
   }

   @JsonProperty("resource")
   public void setResource(GroupVersionResource resource) {
      this.resource = resource;
   }

   @JsonIgnore
   public StorageVersionMigrationSpecBuilder edit() {
      return new StorageVersionMigrationSpecBuilder(this);
   }

   @JsonIgnore
   public StorageVersionMigrationSpecBuilder toBuilder() {
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
      String var10000 = this.getContinueToken();
      return "StorageVersionMigrationSpec(continueToken=" + var10000 + ", resource=" + this.getResource() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof StorageVersionMigrationSpec)) {
         return false;
      } else {
         StorageVersionMigrationSpec other = (StorageVersionMigrationSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$continueToken = this.getContinueToken();
            Object other$continueToken = other.getContinueToken();
            if (this$continueToken == null) {
               if (other$continueToken != null) {
                  return false;
               }
            } else if (!this$continueToken.equals(other$continueToken)) {
               return false;
            }

            Object this$resource = this.getResource();
            Object other$resource = other.getResource();
            if (this$resource == null) {
               if (other$resource != null) {
                  return false;
               }
            } else if (!this$resource.equals(other$resource)) {
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
      return other instanceof StorageVersionMigrationSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $continueToken = this.getContinueToken();
      result = result * 59 + ($continueToken == null ? 43 : $continueToken.hashCode());
      Object $resource = this.getResource();
      result = result * 59 + ($resource == null ? 43 : $resource.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
