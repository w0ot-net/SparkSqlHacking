package io.fabric8.kubernetes.api.model.apiextensions.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class CustomResourceDefinitionSpecFluent extends BaseFluent {
   private CustomResourceConversionBuilder conversion;
   private String group;
   private CustomResourceDefinitionNamesBuilder names;
   private Boolean preserveUnknownFields;
   private String scope;
   private ArrayList versions = new ArrayList();
   private Map additionalProperties;

   public CustomResourceDefinitionSpecFluent() {
   }

   public CustomResourceDefinitionSpecFluent(CustomResourceDefinitionSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(CustomResourceDefinitionSpec instance) {
      instance = instance != null ? instance : new CustomResourceDefinitionSpec();
      if (instance != null) {
         this.withConversion(instance.getConversion());
         this.withGroup(instance.getGroup());
         this.withNames(instance.getNames());
         this.withPreserveUnknownFields(instance.getPreserveUnknownFields());
         this.withScope(instance.getScope());
         this.withVersions(instance.getVersions());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public CustomResourceConversion buildConversion() {
      return this.conversion != null ? this.conversion.build() : null;
   }

   public CustomResourceDefinitionSpecFluent withConversion(CustomResourceConversion conversion) {
      this._visitables.remove("conversion");
      if (conversion != null) {
         this.conversion = new CustomResourceConversionBuilder(conversion);
         this._visitables.get("conversion").add(this.conversion);
      } else {
         this.conversion = null;
         this._visitables.get("conversion").remove(this.conversion);
      }

      return this;
   }

   public boolean hasConversion() {
      return this.conversion != null;
   }

   public ConversionNested withNewConversion() {
      return new ConversionNested((CustomResourceConversion)null);
   }

   public ConversionNested withNewConversionLike(CustomResourceConversion item) {
      return new ConversionNested(item);
   }

   public ConversionNested editConversion() {
      return this.withNewConversionLike((CustomResourceConversion)Optional.ofNullable(this.buildConversion()).orElse((Object)null));
   }

   public ConversionNested editOrNewConversion() {
      return this.withNewConversionLike((CustomResourceConversion)Optional.ofNullable(this.buildConversion()).orElse((new CustomResourceConversionBuilder()).build()));
   }

   public ConversionNested editOrNewConversionLike(CustomResourceConversion item) {
      return this.withNewConversionLike((CustomResourceConversion)Optional.ofNullable(this.buildConversion()).orElse(item));
   }

   public String getGroup() {
      return this.group;
   }

   public CustomResourceDefinitionSpecFluent withGroup(String group) {
      this.group = group;
      return this;
   }

   public boolean hasGroup() {
      return this.group != null;
   }

   public CustomResourceDefinitionNames buildNames() {
      return this.names != null ? this.names.build() : null;
   }

   public CustomResourceDefinitionSpecFluent withNames(CustomResourceDefinitionNames names) {
      this._visitables.remove("names");
      if (names != null) {
         this.names = new CustomResourceDefinitionNamesBuilder(names);
         this._visitables.get("names").add(this.names);
      } else {
         this.names = null;
         this._visitables.get("names").remove(this.names);
      }

      return this;
   }

   public boolean hasNames() {
      return this.names != null;
   }

   public NamesNested withNewNames() {
      return new NamesNested((CustomResourceDefinitionNames)null);
   }

   public NamesNested withNewNamesLike(CustomResourceDefinitionNames item) {
      return new NamesNested(item);
   }

   public NamesNested editNames() {
      return this.withNewNamesLike((CustomResourceDefinitionNames)Optional.ofNullable(this.buildNames()).orElse((Object)null));
   }

   public NamesNested editOrNewNames() {
      return this.withNewNamesLike((CustomResourceDefinitionNames)Optional.ofNullable(this.buildNames()).orElse((new CustomResourceDefinitionNamesBuilder()).build()));
   }

   public NamesNested editOrNewNamesLike(CustomResourceDefinitionNames item) {
      return this.withNewNamesLike((CustomResourceDefinitionNames)Optional.ofNullable(this.buildNames()).orElse(item));
   }

   public Boolean getPreserveUnknownFields() {
      return this.preserveUnknownFields;
   }

   public CustomResourceDefinitionSpecFluent withPreserveUnknownFields(Boolean preserveUnknownFields) {
      this.preserveUnknownFields = preserveUnknownFields;
      return this;
   }

   public boolean hasPreserveUnknownFields() {
      return this.preserveUnknownFields != null;
   }

   public String getScope() {
      return this.scope;
   }

   public CustomResourceDefinitionSpecFluent withScope(String scope) {
      this.scope = scope;
      return this;
   }

   public boolean hasScope() {
      return this.scope != null;
   }

   public CustomResourceDefinitionSpecFluent addToVersions(int index, CustomResourceDefinitionVersion item) {
      if (this.versions == null) {
         this.versions = new ArrayList();
      }

      CustomResourceDefinitionVersionBuilder builder = new CustomResourceDefinitionVersionBuilder(item);
      if (index >= 0 && index < this.versions.size()) {
         this._visitables.get("versions").add(index, builder);
         this.versions.add(index, builder);
      } else {
         this._visitables.get("versions").add(builder);
         this.versions.add(builder);
      }

      return this;
   }

   public CustomResourceDefinitionSpecFluent setToVersions(int index, CustomResourceDefinitionVersion item) {
      if (this.versions == null) {
         this.versions = new ArrayList();
      }

      CustomResourceDefinitionVersionBuilder builder = new CustomResourceDefinitionVersionBuilder(item);
      if (index >= 0 && index < this.versions.size()) {
         this._visitables.get("versions").set(index, builder);
         this.versions.set(index, builder);
      } else {
         this._visitables.get("versions").add(builder);
         this.versions.add(builder);
      }

      return this;
   }

   public CustomResourceDefinitionSpecFluent addToVersions(CustomResourceDefinitionVersion... items) {
      if (this.versions == null) {
         this.versions = new ArrayList();
      }

      for(CustomResourceDefinitionVersion item : items) {
         CustomResourceDefinitionVersionBuilder builder = new CustomResourceDefinitionVersionBuilder(item);
         this._visitables.get("versions").add(builder);
         this.versions.add(builder);
      }

      return this;
   }

   public CustomResourceDefinitionSpecFluent addAllToVersions(Collection items) {
      if (this.versions == null) {
         this.versions = new ArrayList();
      }

      for(CustomResourceDefinitionVersion item : items) {
         CustomResourceDefinitionVersionBuilder builder = new CustomResourceDefinitionVersionBuilder(item);
         this._visitables.get("versions").add(builder);
         this.versions.add(builder);
      }

      return this;
   }

   public CustomResourceDefinitionSpecFluent removeFromVersions(CustomResourceDefinitionVersion... items) {
      if (this.versions == null) {
         return this;
      } else {
         for(CustomResourceDefinitionVersion item : items) {
            CustomResourceDefinitionVersionBuilder builder = new CustomResourceDefinitionVersionBuilder(item);
            this._visitables.get("versions").remove(builder);
            this.versions.remove(builder);
         }

         return this;
      }
   }

   public CustomResourceDefinitionSpecFluent removeAllFromVersions(Collection items) {
      if (this.versions == null) {
         return this;
      } else {
         for(CustomResourceDefinitionVersion item : items) {
            CustomResourceDefinitionVersionBuilder builder = new CustomResourceDefinitionVersionBuilder(item);
            this._visitables.get("versions").remove(builder);
            this.versions.remove(builder);
         }

         return this;
      }
   }

   public CustomResourceDefinitionSpecFluent removeMatchingFromVersions(Predicate predicate) {
      if (this.versions == null) {
         return this;
      } else {
         Iterator<CustomResourceDefinitionVersionBuilder> each = this.versions.iterator();
         List visitables = this._visitables.get("versions");

         while(each.hasNext()) {
            CustomResourceDefinitionVersionBuilder builder = (CustomResourceDefinitionVersionBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildVersions() {
      return this.versions != null ? build(this.versions) : null;
   }

   public CustomResourceDefinitionVersion buildVersion(int index) {
      return ((CustomResourceDefinitionVersionBuilder)this.versions.get(index)).build();
   }

   public CustomResourceDefinitionVersion buildFirstVersion() {
      return ((CustomResourceDefinitionVersionBuilder)this.versions.get(0)).build();
   }

   public CustomResourceDefinitionVersion buildLastVersion() {
      return ((CustomResourceDefinitionVersionBuilder)this.versions.get(this.versions.size() - 1)).build();
   }

   public CustomResourceDefinitionVersion buildMatchingVersion(Predicate predicate) {
      for(CustomResourceDefinitionVersionBuilder item : this.versions) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingVersion(Predicate predicate) {
      for(CustomResourceDefinitionVersionBuilder item : this.versions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public CustomResourceDefinitionSpecFluent withVersions(List versions) {
      if (this.versions != null) {
         this._visitables.get("versions").clear();
      }

      if (versions != null) {
         this.versions = new ArrayList();

         for(CustomResourceDefinitionVersion item : versions) {
            this.addToVersions(item);
         }
      } else {
         this.versions = null;
      }

      return this;
   }

   public CustomResourceDefinitionSpecFluent withVersions(CustomResourceDefinitionVersion... versions) {
      if (this.versions != null) {
         this.versions.clear();
         this._visitables.remove("versions");
      }

      if (versions != null) {
         for(CustomResourceDefinitionVersion item : versions) {
            this.addToVersions(item);
         }
      }

      return this;
   }

   public boolean hasVersions() {
      return this.versions != null && !this.versions.isEmpty();
   }

   public VersionsNested addNewVersion() {
      return new VersionsNested(-1, (CustomResourceDefinitionVersion)null);
   }

   public VersionsNested addNewVersionLike(CustomResourceDefinitionVersion item) {
      return new VersionsNested(-1, item);
   }

   public VersionsNested setNewVersionLike(int index, CustomResourceDefinitionVersion item) {
      return new VersionsNested(index, item);
   }

   public VersionsNested editVersion(int index) {
      if (this.versions.size() <= index) {
         throw new RuntimeException("Can't edit versions. Index exceeds size.");
      } else {
         return this.setNewVersionLike(index, this.buildVersion(index));
      }
   }

   public VersionsNested editFirstVersion() {
      if (this.versions.size() == 0) {
         throw new RuntimeException("Can't edit first versions. The list is empty.");
      } else {
         return this.setNewVersionLike(0, this.buildVersion(0));
      }
   }

   public VersionsNested editLastVersion() {
      int index = this.versions.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last versions. The list is empty.");
      } else {
         return this.setNewVersionLike(index, this.buildVersion(index));
      }
   }

   public VersionsNested editMatchingVersion(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.versions.size(); ++i) {
         if (predicate.test((CustomResourceDefinitionVersionBuilder)this.versions.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching versions. No match found.");
      } else {
         return this.setNewVersionLike(index, this.buildVersion(index));
      }
   }

   public CustomResourceDefinitionSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public CustomResourceDefinitionSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public CustomResourceDefinitionSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public CustomResourceDefinitionSpecFluent removeFromAdditionalProperties(Map map) {
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

   public CustomResourceDefinitionSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            CustomResourceDefinitionSpecFluent that = (CustomResourceDefinitionSpecFluent)o;
            if (!Objects.equals(this.conversion, that.conversion)) {
               return false;
            } else if (!Objects.equals(this.group, that.group)) {
               return false;
            } else if (!Objects.equals(this.names, that.names)) {
               return false;
            } else if (!Objects.equals(this.preserveUnknownFields, that.preserveUnknownFields)) {
               return false;
            } else if (!Objects.equals(this.scope, that.scope)) {
               return false;
            } else if (!Objects.equals(this.versions, that.versions)) {
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
      return Objects.hash(new Object[]{this.conversion, this.group, this.names, this.preserveUnknownFields, this.scope, this.versions, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.conversion != null) {
         sb.append("conversion:");
         sb.append(this.conversion + ",");
      }

      if (this.group != null) {
         sb.append("group:");
         sb.append(this.group + ",");
      }

      if (this.names != null) {
         sb.append("names:");
         sb.append(this.names + ",");
      }

      if (this.preserveUnknownFields != null) {
         sb.append("preserveUnknownFields:");
         sb.append(this.preserveUnknownFields + ",");
      }

      if (this.scope != null) {
         sb.append("scope:");
         sb.append(this.scope + ",");
      }

      if (this.versions != null && !this.versions.isEmpty()) {
         sb.append("versions:");
         sb.append(this.versions + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public CustomResourceDefinitionSpecFluent withPreserveUnknownFields() {
      return this.withPreserveUnknownFields(true);
   }

   public class ConversionNested extends CustomResourceConversionFluent implements Nested {
      CustomResourceConversionBuilder builder;

      ConversionNested(CustomResourceConversion item) {
         this.builder = new CustomResourceConversionBuilder(this, item);
      }

      public Object and() {
         return CustomResourceDefinitionSpecFluent.this.withConversion(this.builder.build());
      }

      public Object endConversion() {
         return this.and();
      }
   }

   public class NamesNested extends CustomResourceDefinitionNamesFluent implements Nested {
      CustomResourceDefinitionNamesBuilder builder;

      NamesNested(CustomResourceDefinitionNames item) {
         this.builder = new CustomResourceDefinitionNamesBuilder(this, item);
      }

      public Object and() {
         return CustomResourceDefinitionSpecFluent.this.withNames(this.builder.build());
      }

      public Object endNames() {
         return this.and();
      }
   }

   public class VersionsNested extends CustomResourceDefinitionVersionFluent implements Nested {
      CustomResourceDefinitionVersionBuilder builder;
      int index;

      VersionsNested(int index, CustomResourceDefinitionVersion item) {
         this.index = index;
         this.builder = new CustomResourceDefinitionVersionBuilder(this, item);
      }

      public Object and() {
         return CustomResourceDefinitionSpecFluent.this.setToVersions(this.index, this.builder.build());
      }

      public Object endVersion() {
         return this.and();
      }
   }
}
