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
@JsonPropertyOrder({"health", "resourceID"})
public class ResourceHealth implements Editable, KubernetesResource {
   @JsonProperty("health")
   private String health;
   @JsonProperty("resourceID")
   private String resourceID;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ResourceHealth() {
   }

   public ResourceHealth(String health, String resourceID) {
      this.health = health;
      this.resourceID = resourceID;
   }

   @JsonProperty("health")
   public String getHealth() {
      return this.health;
   }

   @JsonProperty("health")
   public void setHealth(String health) {
      this.health = health;
   }

   @JsonProperty("resourceID")
   public String getResourceID() {
      return this.resourceID;
   }

   @JsonProperty("resourceID")
   public void setResourceID(String resourceID) {
      this.resourceID = resourceID;
   }

   @JsonIgnore
   public ResourceHealthBuilder edit() {
      return new ResourceHealthBuilder(this);
   }

   @JsonIgnore
   public ResourceHealthBuilder toBuilder() {
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
      String var10000 = this.getHealth();
      return "ResourceHealth(health=" + var10000 + ", resourceID=" + this.getResourceID() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourceHealth)) {
         return false;
      } else {
         ResourceHealth other = (ResourceHealth)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$health = this.getHealth();
            Object other$health = other.getHealth();
            if (this$health == null) {
               if (other$health != null) {
                  return false;
               }
            } else if (!this$health.equals(other$health)) {
               return false;
            }

            Object this$resourceID = this.getResourceID();
            Object other$resourceID = other.getResourceID();
            if (this$resourceID == null) {
               if (other$resourceID != null) {
                  return false;
               }
            } else if (!this$resourceID.equals(other$resourceID)) {
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
      return other instanceof ResourceHealth;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $health = this.getHealth();
      result = result * 59 + ($health == null ? 43 : $health.hashCode());
      Object $resourceID = this.getResourceID();
      result = result * 59 + ($resourceID == null ? 43 : $resourceID.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
