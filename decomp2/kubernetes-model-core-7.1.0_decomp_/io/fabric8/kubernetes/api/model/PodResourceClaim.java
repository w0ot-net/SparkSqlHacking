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
@JsonPropertyOrder({"name", "resourceClaimName", "resourceClaimTemplateName"})
public class PodResourceClaim implements Editable, KubernetesResource {
   @JsonProperty("name")
   private String name;
   @JsonProperty("resourceClaimName")
   private String resourceClaimName;
   @JsonProperty("resourceClaimTemplateName")
   private String resourceClaimTemplateName;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PodResourceClaim() {
   }

   public PodResourceClaim(String name, String resourceClaimName, String resourceClaimTemplateName) {
      this.name = name;
      this.resourceClaimName = resourceClaimName;
      this.resourceClaimTemplateName = resourceClaimTemplateName;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("resourceClaimName")
   public String getResourceClaimName() {
      return this.resourceClaimName;
   }

   @JsonProperty("resourceClaimName")
   public void setResourceClaimName(String resourceClaimName) {
      this.resourceClaimName = resourceClaimName;
   }

   @JsonProperty("resourceClaimTemplateName")
   public String getResourceClaimTemplateName() {
      return this.resourceClaimTemplateName;
   }

   @JsonProperty("resourceClaimTemplateName")
   public void setResourceClaimTemplateName(String resourceClaimTemplateName) {
      this.resourceClaimTemplateName = resourceClaimTemplateName;
   }

   @JsonIgnore
   public PodResourceClaimBuilder edit() {
      return new PodResourceClaimBuilder(this);
   }

   @JsonIgnore
   public PodResourceClaimBuilder toBuilder() {
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
      String var10000 = this.getName();
      return "PodResourceClaim(name=" + var10000 + ", resourceClaimName=" + this.getResourceClaimName() + ", resourceClaimTemplateName=" + this.getResourceClaimTemplateName() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PodResourceClaim)) {
         return false;
      } else {
         PodResourceClaim other = (PodResourceClaim)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
               return false;
            }

            Object this$resourceClaimName = this.getResourceClaimName();
            Object other$resourceClaimName = other.getResourceClaimName();
            if (this$resourceClaimName == null) {
               if (other$resourceClaimName != null) {
                  return false;
               }
            } else if (!this$resourceClaimName.equals(other$resourceClaimName)) {
               return false;
            }

            Object this$resourceClaimTemplateName = this.getResourceClaimTemplateName();
            Object other$resourceClaimTemplateName = other.getResourceClaimTemplateName();
            if (this$resourceClaimTemplateName == null) {
               if (other$resourceClaimTemplateName != null) {
                  return false;
               }
            } else if (!this$resourceClaimTemplateName.equals(other$resourceClaimTemplateName)) {
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
      return other instanceof PodResourceClaim;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $resourceClaimName = this.getResourceClaimName();
      result = result * 59 + ($resourceClaimName == null ? 43 : $resourceClaimName.hashCode());
      Object $resourceClaimTemplateName = this.getResourceClaimTemplateName();
      result = result * 59 + ($resourceClaimTemplateName == null ? 43 : $resourceClaimTemplateName.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
