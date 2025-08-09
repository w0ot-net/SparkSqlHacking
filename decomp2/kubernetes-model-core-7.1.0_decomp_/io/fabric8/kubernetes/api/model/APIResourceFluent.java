package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class APIResourceFluent extends BaseFluent {
   private List categories = new ArrayList();
   private String group;
   private String kind;
   private String name;
   private Boolean namespaced;
   private List shortNames = new ArrayList();
   private String singularName;
   private String storageVersionHash;
   private List verbs = new ArrayList();
   private String version;
   private Map additionalProperties;

   public APIResourceFluent() {
   }

   public APIResourceFluent(APIResource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(APIResource instance) {
      instance = instance != null ? instance : new APIResource();
      if (instance != null) {
         this.withCategories(instance.getCategories());
         this.withGroup(instance.getGroup());
         this.withKind(instance.getKind());
         this.withName(instance.getName());
         this.withNamespaced(instance.getNamespaced());
         this.withShortNames(instance.getShortNames());
         this.withSingularName(instance.getSingularName());
         this.withStorageVersionHash(instance.getStorageVersionHash());
         this.withVerbs(instance.getVerbs());
         this.withVersion(instance.getVersion());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public APIResourceFluent addToCategories(int index, String item) {
      if (this.categories == null) {
         this.categories = new ArrayList();
      }

      this.categories.add(index, item);
      return this;
   }

   public APIResourceFluent setToCategories(int index, String item) {
      if (this.categories == null) {
         this.categories = new ArrayList();
      }

      this.categories.set(index, item);
      return this;
   }

   public APIResourceFluent addToCategories(String... items) {
      if (this.categories == null) {
         this.categories = new ArrayList();
      }

      for(String item : items) {
         this.categories.add(item);
      }

      return this;
   }

   public APIResourceFluent addAllToCategories(Collection items) {
      if (this.categories == null) {
         this.categories = new ArrayList();
      }

      for(String item : items) {
         this.categories.add(item);
      }

      return this;
   }

   public APIResourceFluent removeFromCategories(String... items) {
      if (this.categories == null) {
         return this;
      } else {
         for(String item : items) {
            this.categories.remove(item);
         }

         return this;
      }
   }

   public APIResourceFluent removeAllFromCategories(Collection items) {
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

   public APIResourceFluent withCategories(List categories) {
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

   public APIResourceFluent withCategories(String... categories) {
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

   public String getGroup() {
      return this.group;
   }

   public APIResourceFluent withGroup(String group) {
      this.group = group;
      return this;
   }

   public boolean hasGroup() {
      return this.group != null;
   }

   public String getKind() {
      return this.kind;
   }

   public APIResourceFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public String getName() {
      return this.name;
   }

   public APIResourceFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public Boolean getNamespaced() {
      return this.namespaced;
   }

   public APIResourceFluent withNamespaced(Boolean namespaced) {
      this.namespaced = namespaced;
      return this;
   }

   public boolean hasNamespaced() {
      return this.namespaced != null;
   }

   public APIResourceFluent addToShortNames(int index, String item) {
      if (this.shortNames == null) {
         this.shortNames = new ArrayList();
      }

      this.shortNames.add(index, item);
      return this;
   }

   public APIResourceFluent setToShortNames(int index, String item) {
      if (this.shortNames == null) {
         this.shortNames = new ArrayList();
      }

      this.shortNames.set(index, item);
      return this;
   }

   public APIResourceFluent addToShortNames(String... items) {
      if (this.shortNames == null) {
         this.shortNames = new ArrayList();
      }

      for(String item : items) {
         this.shortNames.add(item);
      }

      return this;
   }

   public APIResourceFluent addAllToShortNames(Collection items) {
      if (this.shortNames == null) {
         this.shortNames = new ArrayList();
      }

      for(String item : items) {
         this.shortNames.add(item);
      }

      return this;
   }

   public APIResourceFluent removeFromShortNames(String... items) {
      if (this.shortNames == null) {
         return this;
      } else {
         for(String item : items) {
            this.shortNames.remove(item);
         }

         return this;
      }
   }

   public APIResourceFluent removeAllFromShortNames(Collection items) {
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

   public APIResourceFluent withShortNames(List shortNames) {
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

   public APIResourceFluent withShortNames(String... shortNames) {
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

   public String getSingularName() {
      return this.singularName;
   }

   public APIResourceFluent withSingularName(String singularName) {
      this.singularName = singularName;
      return this;
   }

   public boolean hasSingularName() {
      return this.singularName != null;
   }

   public String getStorageVersionHash() {
      return this.storageVersionHash;
   }

   public APIResourceFluent withStorageVersionHash(String storageVersionHash) {
      this.storageVersionHash = storageVersionHash;
      return this;
   }

   public boolean hasStorageVersionHash() {
      return this.storageVersionHash != null;
   }

   public APIResourceFluent addToVerbs(int index, String item) {
      if (this.verbs == null) {
         this.verbs = new ArrayList();
      }

      this.verbs.add(index, item);
      return this;
   }

   public APIResourceFluent setToVerbs(int index, String item) {
      if (this.verbs == null) {
         this.verbs = new ArrayList();
      }

      this.verbs.set(index, item);
      return this;
   }

   public APIResourceFluent addToVerbs(String... items) {
      if (this.verbs == null) {
         this.verbs = new ArrayList();
      }

      for(String item : items) {
         this.verbs.add(item);
      }

      return this;
   }

   public APIResourceFluent addAllToVerbs(Collection items) {
      if (this.verbs == null) {
         this.verbs = new ArrayList();
      }

      for(String item : items) {
         this.verbs.add(item);
      }

      return this;
   }

   public APIResourceFluent removeFromVerbs(String... items) {
      if (this.verbs == null) {
         return this;
      } else {
         for(String item : items) {
            this.verbs.remove(item);
         }

         return this;
      }
   }

   public APIResourceFluent removeAllFromVerbs(Collection items) {
      if (this.verbs == null) {
         return this;
      } else {
         for(String item : items) {
            this.verbs.remove(item);
         }

         return this;
      }
   }

   public List getVerbs() {
      return this.verbs;
   }

   public String getVerb(int index) {
      return (String)this.verbs.get(index);
   }

   public String getFirstVerb() {
      return (String)this.verbs.get(0);
   }

   public String getLastVerb() {
      return (String)this.verbs.get(this.verbs.size() - 1);
   }

   public String getMatchingVerb(Predicate predicate) {
      for(String item : this.verbs) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingVerb(Predicate predicate) {
      for(String item : this.verbs) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public APIResourceFluent withVerbs(List verbs) {
      if (verbs != null) {
         this.verbs = new ArrayList();

         for(String item : verbs) {
            this.addToVerbs(item);
         }
      } else {
         this.verbs = null;
      }

      return this;
   }

   public APIResourceFluent withVerbs(String... verbs) {
      if (this.verbs != null) {
         this.verbs.clear();
         this._visitables.remove("verbs");
      }

      if (verbs != null) {
         for(String item : verbs) {
            this.addToVerbs(item);
         }
      }

      return this;
   }

   public boolean hasVerbs() {
      return this.verbs != null && !this.verbs.isEmpty();
   }

   public String getVersion() {
      return this.version;
   }

   public APIResourceFluent withVersion(String version) {
      this.version = version;
      return this;
   }

   public boolean hasVersion() {
      return this.version != null;
   }

   public APIResourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public APIResourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public APIResourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public APIResourceFluent removeFromAdditionalProperties(Map map) {
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

   public APIResourceFluent withAdditionalProperties(Map additionalProperties) {
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
            APIResourceFluent that = (APIResourceFluent)o;
            if (!Objects.equals(this.categories, that.categories)) {
               return false;
            } else if (!Objects.equals(this.group, that.group)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.namespaced, that.namespaced)) {
               return false;
            } else if (!Objects.equals(this.shortNames, that.shortNames)) {
               return false;
            } else if (!Objects.equals(this.singularName, that.singularName)) {
               return false;
            } else if (!Objects.equals(this.storageVersionHash, that.storageVersionHash)) {
               return false;
            } else if (!Objects.equals(this.verbs, that.verbs)) {
               return false;
            } else if (!Objects.equals(this.version, that.version)) {
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
      return Objects.hash(new Object[]{this.categories, this.group, this.kind, this.name, this.namespaced, this.shortNames, this.singularName, this.storageVersionHash, this.verbs, this.version, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.categories != null && !this.categories.isEmpty()) {
         sb.append("categories:");
         sb.append(this.categories + ",");
      }

      if (this.group != null) {
         sb.append("group:");
         sb.append(this.group + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.namespaced != null) {
         sb.append("namespaced:");
         sb.append(this.namespaced + ",");
      }

      if (this.shortNames != null && !this.shortNames.isEmpty()) {
         sb.append("shortNames:");
         sb.append(this.shortNames + ",");
      }

      if (this.singularName != null) {
         sb.append("singularName:");
         sb.append(this.singularName + ",");
      }

      if (this.storageVersionHash != null) {
         sb.append("storageVersionHash:");
         sb.append(this.storageVersionHash + ",");
      }

      if (this.verbs != null && !this.verbs.isEmpty()) {
         sb.append("verbs:");
         sb.append(this.verbs + ",");
      }

      if (this.version != null) {
         sb.append("version:");
         sb.append(this.version + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public APIResourceFluent withNamespaced() {
      return this.withNamespaced(true);
   }
}
