package io.fabric8.kubernetes.api.model.apiextensions.v1beta1;

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
@JsonPropertyOrder({"kind", "categories", "listKind", "plural", "shortNames", "singular"})
public class CustomResourceDefinitionNames implements Editable, KubernetesResource {
   @JsonProperty("categories")
   @JsonInclude(Include.NON_EMPTY)
   private List categories = new ArrayList();
   @JsonProperty("kind")
   private String kind;
   @JsonProperty("listKind")
   private String listKind;
   @JsonProperty("plural")
   private String plural;
   @JsonProperty("shortNames")
   @JsonInclude(Include.NON_EMPTY)
   private List shortNames = new ArrayList();
   @JsonProperty("singular")
   private String singular;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public CustomResourceDefinitionNames() {
   }

   public CustomResourceDefinitionNames(List categories, String kind, String listKind, String plural, List shortNames, String singular) {
      this.categories = categories;
      this.kind = kind;
      this.listKind = listKind;
      this.plural = plural;
      this.shortNames = shortNames;
      this.singular = singular;
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

   @JsonProperty("kind")
   public String getKind() {
      return this.kind;
   }

   @JsonProperty("kind")
   public void setKind(String kind) {
      this.kind = kind;
   }

   @JsonProperty("listKind")
   public String getListKind() {
      return this.listKind;
   }

   @JsonProperty("listKind")
   public void setListKind(String listKind) {
      this.listKind = listKind;
   }

   @JsonProperty("plural")
   public String getPlural() {
      return this.plural;
   }

   @JsonProperty("plural")
   public void setPlural(String plural) {
      this.plural = plural;
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

   @JsonProperty("singular")
   public String getSingular() {
      return this.singular;
   }

   @JsonProperty("singular")
   public void setSingular(String singular) {
      this.singular = singular;
   }

   @JsonIgnore
   public CustomResourceDefinitionNamesBuilder edit() {
      return new CustomResourceDefinitionNamesBuilder(this);
   }

   @JsonIgnore
   public CustomResourceDefinitionNamesBuilder toBuilder() {
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
      return "CustomResourceDefinitionNames(categories=" + var10000 + ", kind=" + this.getKind() + ", listKind=" + this.getListKind() + ", plural=" + this.getPlural() + ", shortNames=" + this.getShortNames() + ", singular=" + this.getSingular() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CustomResourceDefinitionNames)) {
         return false;
      } else {
         CustomResourceDefinitionNames other = (CustomResourceDefinitionNames)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$categories = this.getCategories();
            Object other$categories = other.getCategories();
            if (this$categories == null) {
               if (other$categories != null) {
                  return false;
               }
            } else if (!this$categories.equals(other$categories)) {
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

            Object this$listKind = this.getListKind();
            Object other$listKind = other.getListKind();
            if (this$listKind == null) {
               if (other$listKind != null) {
                  return false;
               }
            } else if (!this$listKind.equals(other$listKind)) {
               return false;
            }

            Object this$plural = this.getPlural();
            Object other$plural = other.getPlural();
            if (this$plural == null) {
               if (other$plural != null) {
                  return false;
               }
            } else if (!this$plural.equals(other$plural)) {
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

            Object this$singular = this.getSingular();
            Object other$singular = other.getSingular();
            if (this$singular == null) {
               if (other$singular != null) {
                  return false;
               }
            } else if (!this$singular.equals(other$singular)) {
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
      return other instanceof CustomResourceDefinitionNames;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $categories = this.getCategories();
      result = result * 59 + ($categories == null ? 43 : $categories.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $listKind = this.getListKind();
      result = result * 59 + ($listKind == null ? 43 : $listKind.hashCode());
      Object $plural = this.getPlural();
      result = result * 59 + ($plural == null ? 43 : $plural.hashCode());
      Object $shortNames = this.getShortNames();
      result = result * 59 + ($shortNames == null ? 43 : $shortNames.hashCode());
      Object $singular = this.getSingular();
      result = result * 59 + ($singular == null ? 43 : $singular.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
