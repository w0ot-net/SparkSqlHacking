package io.fabric8.kubernetes.api.model.apiextensions.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class CustomResourceDefinitionNamesFluent extends BaseFluent {
   private List categories = new ArrayList();
   private String kind;
   private String listKind;
   private String plural;
   private List shortNames = new ArrayList();
   private String singular;
   private Map additionalProperties;

   public CustomResourceDefinitionNamesFluent() {
   }

   public CustomResourceDefinitionNamesFluent(CustomResourceDefinitionNames instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(CustomResourceDefinitionNames instance) {
      instance = instance != null ? instance : new CustomResourceDefinitionNames();
      if (instance != null) {
         this.withCategories(instance.getCategories());
         this.withKind(instance.getKind());
         this.withListKind(instance.getListKind());
         this.withPlural(instance.getPlural());
         this.withShortNames(instance.getShortNames());
         this.withSingular(instance.getSingular());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public CustomResourceDefinitionNamesFluent addToCategories(int index, String item) {
      if (this.categories == null) {
         this.categories = new ArrayList();
      }

      this.categories.add(index, item);
      return this;
   }

   public CustomResourceDefinitionNamesFluent setToCategories(int index, String item) {
      if (this.categories == null) {
         this.categories = new ArrayList();
      }

      this.categories.set(index, item);
      return this;
   }

   public CustomResourceDefinitionNamesFluent addToCategories(String... items) {
      if (this.categories == null) {
         this.categories = new ArrayList();
      }

      for(String item : items) {
         this.categories.add(item);
      }

      return this;
   }

   public CustomResourceDefinitionNamesFluent addAllToCategories(Collection items) {
      if (this.categories == null) {
         this.categories = new ArrayList();
      }

      for(String item : items) {
         this.categories.add(item);
      }

      return this;
   }

   public CustomResourceDefinitionNamesFluent removeFromCategories(String... items) {
      if (this.categories == null) {
         return this;
      } else {
         for(String item : items) {
            this.categories.remove(item);
         }

         return this;
      }
   }

   public CustomResourceDefinitionNamesFluent removeAllFromCategories(Collection items) {
      if (this.categories == null) {
         return this;
      } else {
         for(String item : items) {
            this.categories.remove(item);
         }

         return this;
      }
   }

   public List getCategories() {
      return this.categories;
   }

   public String getCategory(int index) {
      return (String)this.categories.get(index);
   }

   public String getFirstCategory() {
      return (String)this.categories.get(0);
   }

   public String getLastCategory() {
      return (String)this.categories.get(this.categories.size() - 1);
   }

   public String getMatchingCategory(Predicate predicate) {
      for(String item : this.categories) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingCategory(Predicate predicate) {
      for(String item : this.categories) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public CustomResourceDefinitionNamesFluent withCategories(List categories) {
      if (categories != null) {
         this.categories = new ArrayList();

         for(String item : categories) {
            this.addToCategories(item);
         }
      } else {
         this.categories = null;
      }

      return this;
   }

   public CustomResourceDefinitionNamesFluent withCategories(String... categories) {
      if (this.categories != null) {
         this.categories.clear();
         this._visitables.remove("categories");
      }

      if (categories != null) {
         for(String item : categories) {
            this.addToCategories(item);
         }
      }

      return this;
   }

   public boolean hasCategories() {
      return this.categories != null && !this.categories.isEmpty();
   }

   public String getKind() {
      return this.kind;
   }

   public CustomResourceDefinitionNamesFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public String getListKind() {
      return this.listKind;
   }

   public CustomResourceDefinitionNamesFluent withListKind(String listKind) {
      this.listKind = listKind;
      return this;
   }

   public boolean hasListKind() {
      return this.listKind != null;
   }

   public String getPlural() {
      return this.plural;
   }

   public CustomResourceDefinitionNamesFluent withPlural(String plural) {
      this.plural = plural;
      return this;
   }

   public boolean hasPlural() {
      return this.plural != null;
   }

   public CustomResourceDefinitionNamesFluent addToShortNames(int index, String item) {
      if (this.shortNames == null) {
         this.shortNames = new ArrayList();
      }

      this.shortNames.add(index, item);
      return this;
   }

   public CustomResourceDefinitionNamesFluent setToShortNames(int index, String item) {
      if (this.shortNames == null) {
         this.shortNames = new ArrayList();
      }

      this.shortNames.set(index, item);
      return this;
   }

   public CustomResourceDefinitionNamesFluent addToShortNames(String... items) {
      if (this.shortNames == null) {
         this.shortNames = new ArrayList();
      }

      for(String item : items) {
         this.shortNames.add(item);
      }

      return this;
   }

   public CustomResourceDefinitionNamesFluent addAllToShortNames(Collection items) {
      if (this.shortNames == null) {
         this.shortNames = new ArrayList();
      }

      for(String item : items) {
         this.shortNames.add(item);
      }

      return this;
   }

   public CustomResourceDefinitionNamesFluent removeFromShortNames(String... items) {
      if (this.shortNames == null) {
         return this;
      } else {
         for(String item : items) {
            this.shortNames.remove(item);
         }

         return this;
      }
   }

   public CustomResourceDefinitionNamesFluent removeAllFromShortNames(Collection items) {
      if (this.shortNames == null) {
         return this;
      } else {
         for(String item : items) {
            this.shortNames.remove(item);
         }

         return this;
      }
   }

   public List getShortNames() {
      return this.shortNames;
   }

   public String getShortName(int index) {
      return (String)this.shortNames.get(index);
   }

   public String getFirstShortName() {
      return (String)this.shortNames.get(0);
   }

   public String getLastShortName() {
      return (String)this.shortNames.get(this.shortNames.size() - 1);
   }

   public String getMatchingShortName(Predicate predicate) {
      for(String item : this.shortNames) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingShortName(Predicate predicate) {
      for(String item : this.shortNames) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public CustomResourceDefinitionNamesFluent withShortNames(List shortNames) {
      if (shortNames != null) {
         this.shortNames = new ArrayList();

         for(String item : shortNames) {
            this.addToShortNames(item);
         }
      } else {
         this.shortNames = null;
      }

      return this;
   }

   public CustomResourceDefinitionNamesFluent withShortNames(String... shortNames) {
      if (this.shortNames != null) {
         this.shortNames.clear();
         this._visitables.remove("shortNames");
      }

      if (shortNames != null) {
         for(String item : shortNames) {
            this.addToShortNames(item);
         }
      }

      return this;
   }

   public boolean hasShortNames() {
      return this.shortNames != null && !this.shortNames.isEmpty();
   }

   public String getSingular() {
      return this.singular;
   }

   public CustomResourceDefinitionNamesFluent withSingular(String singular) {
      this.singular = singular;
      return this;
   }

   public boolean hasSingular() {
      return this.singular != null;
   }

   public CustomResourceDefinitionNamesFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public CustomResourceDefinitionNamesFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public CustomResourceDefinitionNamesFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public CustomResourceDefinitionNamesFluent removeFromAdditionalProperties(Map map) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.additionalProperties != null) {
                  this.additionalProperties.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   public CustomResourceDefinitionNamesFluent withAdditionalProperties(Map additionalProperties) {
      if (additionalProperties == null) {
         this.additionalProperties = null;
      } else {
         this.additionalProperties = new LinkedHashMap(additionalProperties);
      }

      return this;
   }

   public boolean hasAdditionalProperties() {
      return this.additionalProperties != null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            CustomResourceDefinitionNamesFluent that = (CustomResourceDefinitionNamesFluent)o;
            if (!Objects.equals(this.categories, that.categories)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.listKind, that.listKind)) {
               return false;
            } else if (!Objects.equals(this.plural, that.plural)) {
               return false;
            } else if (!Objects.equals(this.shortNames, that.shortNames)) {
               return false;
            } else if (!Objects.equals(this.singular, that.singular)) {
               return false;
            } else {
               return Objects.equals(this.additionalProperties, that.additionalProperties);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.categories, this.kind, this.listKind, this.plural, this.shortNames, this.singular, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.categories != null && !this.categories.isEmpty()) {
         sb.append("categories:");
         sb.append(this.categories + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.listKind != null) {
         sb.append("listKind:");
         sb.append(this.listKind + ",");
      }

      if (this.plural != null) {
         sb.append("plural:");
         sb.append(this.plural + ",");
      }

      if (this.shortNames != null && !this.shortNames.isEmpty()) {
         sb.append("shortNames:");
         sb.append(this.shortNames + ",");
      }

      if (this.singular != null) {
         sb.append("singular:");
         sb.append(this.singular + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
