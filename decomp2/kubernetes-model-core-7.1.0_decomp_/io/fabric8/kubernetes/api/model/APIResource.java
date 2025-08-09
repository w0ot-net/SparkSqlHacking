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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"kind", "categories", "group", "name", "namespaced", "shortNames", "singularName", "storageVersionHash", "verbs", "version"})
public class APIResource implements Editable, KubernetesResource {
   @JsonProperty("categories")
   @JsonInclude(Include.NON_EMPTY)
   private List categories = new ArrayList();
   @JsonProperty("group")
   private String group;
   @JsonProperty("kind")
   private String kind;
   @JsonProperty("name")
   private String name;
   @JsonProperty("namespaced")
   private Boolean namespaced;
   @JsonProperty("shortNames")
   @JsonInclude(Include.NON_EMPTY)
   private List shortNames = new ArrayList();
   @JsonProperty("singularName")
   private String singularName;
   @JsonProperty("storageVersionHash")
   private String storageVersionHash;
   @JsonProperty("verbs")
   @JsonInclude(Include.NON_EMPTY)
   private List verbs = new ArrayList();
   @JsonProperty("version")
   private String version;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public APIResource() {
   }

   public APIResource(List categories, String group, String kind, String name, Boolean namespaced, List shortNames, String singularName, String storageVersionHash, List verbs, String version) {
      this.categories = categories;
      this.group = group;
      this.kind = kind;
      this.name = name;
      this.namespaced = namespaced;
      this.shortNames = shortNames;
      this.singularName = singularName;
      this.storageVersionHash = storageVersionHash;
      this.verbs = verbs;
      this.version = version;
   }

   @JsonProperty("categories")
   @JsonInclude(Include.NON_EMPTY)
   public List getCategories() {
      return this.categories;
   }

   @JsonProperty("categories")
   public void setCategories(List categories) {
      this.categories = categories;
   }

   @JsonProperty("group")
   public String getGroup() {
      return this.group;
   }

   @JsonProperty("group")
   public void setGroup(String group) {
      this.group = group;
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

   @JsonProperty("namespaced")
   public Boolean getNamespaced() {
      return this.namespaced;
   }

   @JsonProperty("namespaced")
   public void setNamespaced(Boolean namespaced) {
      this.namespaced = namespaced;
   }

   @JsonProperty("shortNames")
   @JsonInclude(Include.NON_EMPTY)
   public List getShortNames() {
      return this.shortNames;
   }

   @JsonProperty("shortNames")
   public void setShortNames(List shortNames) {
      this.shortNames = shortNames;
   }

   @JsonProperty("singularName")
   public String getSingularName() {
      return this.singularName;
   }

   @JsonProperty("singularName")
   public void setSingularName(String singularName) {
      this.singularName = singularName;
   }

   @JsonProperty("storageVersionHash")
   public String getStorageVersionHash() {
      return this.storageVersionHash;
   }

   @JsonProperty("storageVersionHash")
   public void setStorageVersionHash(String storageVersionHash) {
      this.storageVersionHash = storageVersionHash;
   }

   @JsonProperty("verbs")
   @JsonInclude(Include.NON_EMPTY)
   public List getVerbs() {
      return this.verbs;
   }

   @JsonProperty("verbs")
   public void setVerbs(List verbs) {
      this.verbs = verbs;
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
   public APIResourceBuilder edit() {
      return new APIResourceBuilder(this);
   }

   @JsonIgnore
   public APIResourceBuilder toBuilder() {
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
      List var10000 = this.getCategories();
      return "APIResource(categories=" + var10000 + ", group=" + this.getGroup() + ", kind=" + this.getKind() + ", name=" + this.getName() + ", namespaced=" + this.getNamespaced() + ", shortNames=" + this.getShortNames() + ", singularName=" + this.getSingularName() + ", storageVersionHash=" + this.getStorageVersionHash() + ", verbs=" + this.getVerbs() + ", version=" + this.getVersion() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof APIResource)) {
         return false;
      } else {
         APIResource other = (APIResource)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$namespaced = this.getNamespaced();
            Object other$namespaced = other.getNamespaced();
            if (this$namespaced == null) {
               if (other$namespaced != null) {
                  return false;
               }
            } else if (!this$namespaced.equals(other$namespaced)) {
               return false;
            }

            Object this$categories = this.getCategories();
            Object other$categories = other.getCategories();
            if (this$categories == null) {
               if (other$categories != null) {
                  return false;
               }
            } else if (!this$categories.equals(other$categories)) {
               return false;
            }

            Object this$group = this.getGroup();
            Object other$group = other.getGroup();
            if (this$group == null) {
               if (other$group != null) {
                  return false;
               }
            } else if (!this$group.equals(other$group)) {
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

            Object this$shortNames = this.getShortNames();
            Object other$shortNames = other.getShortNames();
            if (this$shortNames == null) {
               if (other$shortNames != null) {
                  return false;
               }
            } else if (!this$shortNames.equals(other$shortNames)) {
               return false;
            }

            Object this$singularName = this.getSingularName();
            Object other$singularName = other.getSingularName();
            if (this$singularName == null) {
               if (other$singularName != null) {
                  return false;
               }
            } else if (!this$singularName.equals(other$singularName)) {
               return false;
            }

            Object this$storageVersionHash = this.getStorageVersionHash();
            Object other$storageVersionHash = other.getStorageVersionHash();
            if (this$storageVersionHash == null) {
               if (other$storageVersionHash != null) {
                  return false;
               }
            } else if (!this$storageVersionHash.equals(other$storageVersionHash)) {
               return false;
            }

            Object this$verbs = this.getVerbs();
            Object other$verbs = other.getVerbs();
            if (this$verbs == null) {
               if (other$verbs != null) {
                  return false;
               }
            } else if (!this$verbs.equals(other$verbs)) {
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
      return other instanceof APIResource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $namespaced = this.getNamespaced();
      result = result * 59 + ($namespaced == null ? 43 : $namespaced.hashCode());
      Object $categories = this.getCategories();
      result = result * 59 + ($categories == null ? 43 : $categories.hashCode());
      Object $group = this.getGroup();
      result = result * 59 + ($group == null ? 43 : $group.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $shortNames = this.getShortNames();
      result = result * 59 + ($shortNames == null ? 43 : $shortNames.hashCode());
      Object $singularName = this.getSingularName();
      result = result * 59 + ($singularName == null ? 43 : $singularName.hashCode());
      Object $storageVersionHash = this.getStorageVersionHash();
      result = result * 59 + ($storageVersionHash == null ? 43 : $storageVersionHash.hashCode());
      Object $verbs = this.getVerbs();
      result = result * 59 + ($verbs == null ? 43 : $verbs.hashCode());
      Object $version = this.getVersion();
      result = result * 59 + ($version == null ? 43 : $version.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
