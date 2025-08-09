package io.fabric8.kubernetes.api.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"apiVersion", "kind", "metadata", "items"})
public class GenericKubernetesResource implements HasMetadata {
   private static final ObjectMapper MAPPER = new ObjectMapper();
   @JsonProperty("apiVersion")
   private String apiVersion;
   @JsonProperty("kind")
   private String kind;
   @JsonProperty("metadata")
   private ObjectMeta metadata;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   @JsonAnyGetter
   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   public void setAdditionalProperties(Map additionalProperties) {
      this.additionalProperties = additionalProperties;
   }

   @JsonAnySetter
   public void setAdditionalProperty(String name, Object value) {
      this.additionalProperties.put(name, value);
   }

   /** @deprecated */
   @Deprecated
   @JsonIgnore
   public JsonNode getAdditionalPropertiesNode() {
      return (JsonNode)MAPPER.convertValue(this.getAdditionalProperties(), JsonNode.class);
   }

   public Object get(Object... path) {
      return get(this.getAdditionalProperties(), path);
   }

   public static Object get(Map root, Object... path) {
      Object current = root;

      for(Object segment : path) {
         if (segment instanceof Integer && current instanceof Collection && ((Collection)current).size() > (Integer)segment) {
            current = ((Collection)current).toArray()[(Integer)segment];
         } else {
            if (!(segment instanceof String) || !(current instanceof Map)) {
               return null;
            }

            current = ((Map)current).get(segment.toString());
         }
      }

      return current;
   }

   @Generated
   public String getApiVersion() {
      return this.apiVersion;
   }

   @Generated
   public String getKind() {
      return this.kind;
   }

   @Generated
   public ObjectMeta getMetadata() {
      return this.metadata;
   }

   @JsonProperty("apiVersion")
   @Generated
   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
   }

   @JsonProperty("kind")
   @Generated
   public void setKind(String kind) {
      this.kind = kind;
   }

   @JsonProperty("metadata")
   @Generated
   public void setMetadata(ObjectMeta metadata) {
      this.metadata = metadata;
   }

   @Generated
   public String toString() {
      String var10000 = this.getApiVersion();
      return "GenericKubernetesResource(apiVersion=" + var10000 + ", kind=" + this.getKind() + ", metadata=" + this.getMetadata() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof GenericKubernetesResource)) {
         return false;
      } else {
         GenericKubernetesResource other = (GenericKubernetesResource)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$apiVersion = this.getApiVersion();
            Object other$apiVersion = other.getApiVersion();
            if (this$apiVersion == null) {
               if (other$apiVersion != null) {
                  return false;
               }
            } else if (!this$apiVersion.equals(other$apiVersion)) {
               return false;
            }

            Object this$kind = this.getKind();
            Object other$kind = other.getKind();
            if (this$kind == null) {
               if (other$kind != null) {
                  return false;
               }
            } else if (!this$kind.equals(other$kind)) {
               return false;
            }

            Object this$metadata = this.getMetadata();
            Object other$metadata = other.getMetadata();
            if (this$metadata == null) {
               if (other$metadata != null) {
                  return false;
               }
            } else if (!this$metadata.equals(other$metadata)) {
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
      return other instanceof GenericKubernetesResource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $metadata = this.getMetadata();
      result = result * 59 + ($metadata == null ? 43 : $metadata.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
