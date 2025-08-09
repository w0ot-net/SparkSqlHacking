package io.fabric8.kubernetes.api.model.authorization.v1beta1;

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
@JsonPropertyOrder({"group", "name", "namespace", "resource", "subresource", "verb", "version"})
public class ResourceAttributes implements Editable, KubernetesResource {
   @JsonProperty("group")
   private String group;
   @JsonProperty("name")
   private String name;
   @JsonProperty("namespace")
   private String namespace;
   @JsonProperty("resource")
   private String resource;
   @JsonProperty("subresource")
   private String subresource;
   @JsonProperty("verb")
   private String verb;
   @JsonProperty("version")
   private String version;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ResourceAttributes() {
   }

   public ResourceAttributes(String group, String name, String namespace, String resource, String subresource, String verb, String version) {
      this.group = group;
      this.name = name;
      this.namespace = namespace;
      this.resource = resource;
      this.subresource = subresource;
      this.verb = verb;
      this.version = version;
   }

   @JsonProperty("group")
   public String getGroup() {
      return this.group;
   }

   @JsonProperty("group")
   public void setGroup(String group) {
      this.group = group;
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

   @JsonProperty("resource")
   public String getResource() {
      return this.resource;
   }

   @JsonProperty("resource")
   public void setResource(String resource) {
      this.resource = resource;
   }

   @JsonProperty("subresource")
   public String getSubresource() {
      return this.subresource;
   }

   @JsonProperty("subresource")
   public void setSubresource(String subresource) {
      this.subresource = subresource;
   }

   @JsonProperty("verb")
   public String getVerb() {
      return this.verb;
   }

   @JsonProperty("verb")
   public void setVerb(String verb) {
      this.verb = verb;
   }

   @JsonProperty("version")
   public String getVersion() {
      return this.version;
   }

   @JsonProperty("version")
   public void setVersion(String version) {
      this.version = version;
   }

   @JsonIgnore
   public ResourceAttributesBuilder edit() {
      return new ResourceAttributesBuilder(this);
   }

   @JsonIgnore
   public ResourceAttributesBuilder toBuilder() {
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
      String var10000 = this.getGroup();
      return "ResourceAttributes(group=" + var10000 + ", name=" + this.getName() + ", namespace=" + this.getNamespace() + ", resource=" + this.getResource() + ", subresource=" + this.getSubresource() + ", verb=" + this.getVerb() + ", version=" + this.getVersion() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourceAttributes)) {
         return false;
      } else {
         ResourceAttributes other = (ResourceAttributes)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$group = this.getGroup();
            Object other$group = other.getGroup();
            if (this$group == null) {
               if (other$group != null) {
                  return false;
               }
            } else if (!this$group.equals(other$group)) {
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

            Object this$resource = this.getResource();
            Object other$resource = other.getResource();
            if (this$resource == null) {
               if (other$resource != null) {
                  return false;
               }
            } else if (!this$resource.equals(other$resource)) {
               return false;
            }

            Object this$subresource = this.getSubresource();
            Object other$subresource = other.getSubresource();
            if (this$subresource == null) {
               if (other$subresource != null) {
                  return false;
               }
            } else if (!this$subresource.equals(other$subresource)) {
               return false;
            }

            Object this$verb = this.getVerb();
            Object other$verb = other.getVerb();
            if (this$verb == null) {
               if (other$verb != null) {
                  return false;
               }
            } else if (!this$verb.equals(other$verb)) {
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
      return other instanceof ResourceAttributes;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $group = this.getGroup();
      result = result * 59 + ($group == null ? 43 : $group.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $namespace = this.getNamespace();
      result = result * 59 + ($namespace == null ? 43 : $namespace.hashCode());
      Object $resource = this.getResource();
      result = result * 59 + ($resource == null ? 43 : $resource.hashCode());
      Object $subresource = this.getSubresource();
      result = result * 59 + ($subresource == null ? 43 : $subresource.hashCode());
      Object $verb = this.getVerb();
      result = result * 59 + ($verb == null ? 43 : $verb.hashCode());
      Object $version = this.getVersion();
      result = result * 59 + ($version == null ? 43 : $version.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
