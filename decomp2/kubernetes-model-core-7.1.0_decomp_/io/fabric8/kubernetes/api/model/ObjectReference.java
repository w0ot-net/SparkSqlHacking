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
@JsonPropertyOrder({"apiVersion", "kind", "fieldPath", "name", "namespace", "resourceVersion", "uid"})
public class ObjectReference implements Editable, KubernetesResource {
   @JsonProperty("apiVersion")
   private String apiVersion;
   @JsonProperty("fieldPath")
   private String fieldPath;
   @JsonProperty("kind")
   private String kind;
   @JsonProperty("name")
   private String name;
   @JsonProperty("namespace")
   private String namespace;
   @JsonProperty("resourceVersion")
   private String resourceVersion;
   @JsonProperty("uid")
   private String uid;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ObjectReference() {
   }

   public ObjectReference(String apiVersion, String fieldPath, String kind, String name, String namespace, String resourceVersion, String uid) {
      this.apiVersion = apiVersion;
      this.fieldPath = fieldPath;
      this.kind = kind;
      this.name = name;
      this.namespace = namespace;
      this.resourceVersion = resourceVersion;
      this.uid = uid;
   }

   @JsonProperty("apiVersion")
   public String getApiVersion() {
      return this.apiVersion;
   }

   @JsonProperty("apiVersion")
   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
   }

   @JsonProperty("fieldPath")
   public String getFieldPath() {
      return this.fieldPath;
   }

   @JsonProperty("fieldPath")
   public void setFieldPath(String fieldPath) {
      this.fieldPath = fieldPath;
   }

   @JsonProperty("kind")
   public String getKind() {
      return this.kind;
   }

   @JsonProperty("kind")
   public void setKind(String kind) {
      this.kind = kind;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("namespace")
   public String getNamespace() {
      return this.namespace;
   }

   @JsonProperty("namespace")
   public void setNamespace(String namespace) {
      this.namespace = namespace;
   }

   @JsonProperty("resourceVersion")
   public String getResourceVersion() {
      return this.resourceVersion;
   }

   @JsonProperty("resourceVersion")
   public void setResourceVersion(String resourceVersion) {
      this.resourceVersion = resourceVersion;
   }

   @JsonProperty("uid")
   public String getUid() {
      return this.uid;
   }

   @JsonProperty("uid")
   public void setUid(String uid) {
      this.uid = uid;
   }

   @JsonIgnore
   public ObjectReferenceBuilder edit() {
      return new ObjectReferenceBuilder(this);
   }

   @JsonIgnore
   public ObjectReferenceBuilder toBuilder() {
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
      return "ObjectReference(apiVersion=" + var10000 + ", fieldPath=" + this.getFieldPath() + ", kind=" + this.getKind() + ", name=" + this.getName() + ", namespace=" + this.getNamespace() + ", resourceVersion=" + this.getResourceVersion() + ", uid=" + this.getUid() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ObjectReference)) {
         return false;
      } else {
         ObjectReference other = (ObjectReference)o;
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

            Object this$fieldPath = this.getFieldPath();
            Object other$fieldPath = other.getFieldPath();
            if (this$fieldPath == null) {
               if (other$fieldPath != null) {
                  return false;
               }
            } else if (!this$fieldPath.equals(other$fieldPath)) {
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

            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
               return false;
            }

            Object this$namespace = this.getNamespace();
            Object other$namespace = other.getNamespace();
            if (this$namespace == null) {
               if (other$namespace != null) {
                  return false;
               }
            } else if (!this$namespace.equals(other$namespace)) {
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

            Object this$uid = this.getUid();
            Object other$uid = other.getUid();
            if (this$uid == null) {
               if (other$uid != null) {
                  return false;
               }
            } else if (!this$uid.equals(other$uid)) {
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
      return other instanceof ObjectReference;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $fieldPath = this.getFieldPath();
      result = result * 59 + ($fieldPath == null ? 43 : $fieldPath.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $namespace = this.getNamespace();
      result = result * 59 + ($namespace == null ? 43 : $namespace.hashCode());
      Object $resourceVersion = this.getResourceVersion();
      result = result * 59 + ($resourceVersion == null ? 43 : $resourceVersion.hashCode());
      Object $uid = this.getUid();
      result = result * 59 + ($uid == null ? 43 : $uid.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
