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
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"apiVersion", "kind", "metadata", "data", "immutable", "stringData", "type"})
@Version("v1")
@Group("")
public class Secret implements Editable, HasMetadata, Namespaced {
   @JsonProperty("apiVersion")
   private String apiVersion = "v1";
   @JsonProperty("data")
   @JsonInclude(Include.NON_EMPTY)
   private Map data = new LinkedHashMap();
   @JsonProperty("immutable")
   private Boolean immutable;
   @JsonProperty("kind")
   private String kind = "Secret";
   @JsonProperty("metadata")
   private ObjectMeta metadata;
   @JsonProperty("stringData")
   @JsonInclude(Include.NON_EMPTY)
   private Map stringData = new LinkedHashMap();
   @JsonProperty("type")
   private String type;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public Secret() {
   }

   public Secret(String apiVersion, Map data, Boolean immutable, String kind, ObjectMeta metadata, Map stringData, String type) {
      this.apiVersion = apiVersion;
      this.data = data;
      this.immutable = immutable;
      this.kind = kind;
      this.metadata = metadata;
      this.stringData = stringData;
      this.type = type;
   }

   @JsonProperty("apiVersion")
   public String getApiVersion() {
      return this.apiVersion;
   }

   @JsonProperty("apiVersion")
   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
   }

   @JsonProperty("data")
   @JsonInclude(Include.NON_EMPTY)
   public Map getData() {
      return this.data;
   }

   @JsonProperty("data")
   public void setData(Map data) {
      this.data = data;
   }

   @JsonProperty("immutable")
   public Boolean getImmutable() {
      return this.immutable;
   }

   @JsonProperty("immutable")
   public void setImmutable(Boolean immutable) {
      this.immutable = immutable;
   }

   @JsonProperty("kind")
   public String getKind() {
      return this.kind;
   }

   @JsonProperty("kind")
   public void setKind(String kind) {
      this.kind = kind;
   }

   @JsonProperty("metadata")
   public ObjectMeta getMetadata() {
      return this.metadata;
   }

   @JsonProperty("metadata")
   public void setMetadata(ObjectMeta metadata) {
      this.metadata = metadata;
   }

   @JsonProperty("stringData")
   @JsonInclude(Include.NON_EMPTY)
   public Map getStringData() {
      return this.stringData;
   }

   @JsonProperty("stringData")
   public void setStringData(Map stringData) {
      this.stringData = stringData;
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
   public SecretBuilder edit() {
      return new SecretBuilder(this);
   }

   @JsonIgnore
   public SecretBuilder toBuilder() {
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
      String var10000 = this.getApiVersion();
      return "Secret(apiVersion=" + var10000 + ", data=" + this.getData() + ", immutable=" + this.getImmutable() + ", kind=" + this.getKind() + ", metadata=" + this.getMetadata() + ", stringData=" + this.getStringData() + ", type=" + this.getType() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Secret)) {
         return false;
      } else {
         Secret other = (Secret)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$immutable = this.getImmutable();
            Object other$immutable = other.getImmutable();
            if (this$immutable == null) {
               if (other$immutable != null) {
                  return false;
               }
            } else if (!this$immutable.equals(other$immutable)) {
               return false;
            }

            Object this$apiVersion = this.getApiVersion();
            Object other$apiVersion = other.getApiVersion();
            if (this$apiVersion == null) {
               if (other$apiVersion != null) {
                  return false;
               }
            } else if (!this$apiVersion.equals(other$apiVersion)) {
               return false;
            }

            Object this$data = this.getData();
            Object other$data = other.getData();
            if (this$data == null) {
               if (other$data != null) {
                  return false;
               }
            } else if (!this$data.equals(other$data)) {
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

            Object this$stringData = this.getStringData();
            Object other$stringData = other.getStringData();
            if (this$stringData == null) {
               if (other$stringData != null) {
                  return false;
               }
            } else if (!this$stringData.equals(other$stringData)) {
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
      return other instanceof Secret;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $immutable = this.getImmutable();
      result = result * 59 + ($immutable == null ? 43 : $immutable.hashCode());
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $data = this.getData();
      result = result * 59 + ($data == null ? 43 : $data.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $metadata = this.getMetadata();
      result = result * 59 + ($metadata == null ? 43 : $metadata.hashCode());
      Object $stringData = this.getStringData();
      result = result * 59 + ($stringData == null ? 43 : $stringData.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
