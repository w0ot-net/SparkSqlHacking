package io.fabric8.kubernetes.api.model.resource.v1alpha2;

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
@JsonPropertyOrder({"kind", "apiGroup", "name", "namespace"})
public class ResourceClassParametersReference implements Editable, KubernetesResource {
   @JsonProperty("apiGroup")
   private String apiGroup;
   @JsonProperty("kind")
   private String kind;
   @JsonProperty("name")
   private String name;
   @JsonProperty("namespace")
   private String namespace;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ResourceClassParametersReference() {
   }

   public ResourceClassParametersReference(String apiGroup, String kind, String name, String namespace) {
      this.apiGroup = apiGroup;
      this.kind = kind;
      this.name = name;
      this.namespace = namespace;
   }

   @JsonProperty("apiGroup")
   public String getApiGroup() {
      return this.apiGroup;
   }

   @JsonProperty("apiGroup")
   public void setApiGroup(String apiGroup) {
      this.apiGroup = apiGroup;
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

   @JsonIgnore
   public ResourceClassParametersReferenceBuilder edit() {
      return new ResourceClassParametersReferenceBuilder(this);
   }

   @JsonIgnore
   public ResourceClassParametersReferenceBuilder toBuilder() {
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
      String var10000 = this.getApiGroup();
      return "ResourceClassParametersReference(apiGroup=" + var10000 + ", kind=" + this.getKind() + ", name=" + this.getName() + ", namespace=" + this.getNamespace() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourceClassParametersReference)) {
         return false;
      } else {
         ResourceClassParametersReference other = (ResourceClassParametersReference)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$apiGroup = this.getApiGroup();
            Object other$apiGroup = other.getApiGroup();
            if (this$apiGroup == null) {
               if (other$apiGroup != null) {
                  return false;
               }
            } else if (!this$apiGroup.equals(other$apiGroup)) {
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
      return other instanceof ResourceClassParametersReference;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $apiGroup = this.getApiGroup();
      result = result * 59 + ($apiGroup == null ? 43 : $apiGroup.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $namespace = this.getNamespace();
      result = result * 59 + ($namespace == null ? 43 : $namespace.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
