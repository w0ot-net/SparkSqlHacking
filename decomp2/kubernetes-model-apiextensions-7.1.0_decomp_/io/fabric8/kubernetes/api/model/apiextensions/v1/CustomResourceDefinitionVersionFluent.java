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

public class CustomResourceDefinitionVersionFluent extends BaseFluent {
   private ArrayList additionalPrinterColumns = new ArrayList();
   private Boolean deprecated;
   private String deprecationWarning;
   private String name;
   private CustomResourceValidationBuilder schema;
   private ArrayList selectableFields = new ArrayList();
   private Boolean served;
   private Boolean storage;
   private CustomResourceSubresourcesBuilder subresources;
   private Map additionalProperties;

   public CustomResourceDefinitionVersionFluent() {
   }

   public CustomResourceDefinitionVersionFluent(CustomResourceDefinitionVersion instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(CustomResourceDefinitionVersion instance) {
      instance = instance != null ? instance : new CustomResourceDefinitionVersion();
      if (instance != null) {
         this.withAdditionalPrinterColumns(instance.getAdditionalPrinterColumns());
         this.withDeprecated(instance.getDeprecated());
         this.withDeprecationWarning(instance.getDeprecationWarning());
         this.withName(instance.getName());
         this.withSchema(instance.getSchema());
         this.withSelectableFields(instance.getSelectableFields());
         this.withServed(instance.getServed());
         this.withStorage(instance.getStorage());
         this.withSubresources(instance.getSubresources());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public CustomResourceDefinitionVersionFluent addToAdditionalPrinterColumns(int index, CustomResourceColumnDefinition item) {
      if (this.additionalPrinterColumns == null) {
         this.additionalPrinterColumns = new ArrayList();
      }

      CustomResourceColumnDefinitionBuilder builder = new CustomResourceColumnDefinitionBuilder(item);
      if (index >= 0 && index < this.additionalPrinterColumns.size()) {
         this._visitables.get("additionalPrinterColumns").add(index, builder);
         this.additionalPrinterColumns.add(index, builder);
      } else {
         this._visitables.get("additionalPrinterColumns").add(builder);
         this.additionalPrinterColumns.add(builder);
      }

      return this;
   }

   public CustomResourceDefinitionVersionFluent setToAdditionalPrinterColumns(int index, CustomResourceColumnDefinition item) {
      if (this.additionalPrinterColumns == null) {
         this.additionalPrinterColumns = new ArrayList();
      }

      CustomResourceColumnDefinitionBuilder builder = new CustomResourceColumnDefinitionBuilder(item);
      if (index >= 0 && index < this.additionalPrinterColumns.size()) {
         this._visitables.get("additionalPrinterColumns").set(index, builder);
         this.additionalPrinterColumns.set(index, builder);
      } else {
         this._visitables.get("additionalPrinterColumns").add(builder);
         this.additionalPrinterColumns.add(builder);
      }

      return this;
   }

   public CustomResourceDefinitionVersionFluent addToAdditionalPrinterColumns(CustomResourceColumnDefinition... items) {
      if (this.additionalPrinterColumns == null) {
         this.additionalPrinterColumns = new ArrayList();
      }

      for(CustomResourceColumnDefinition item : items) {
         CustomResourceColumnDefinitionBuilder builder = new CustomResourceColumnDefinitionBuilder(item);
         this._visitables.get("additionalPrinterColumns").add(builder);
         this.additionalPrinterColumns.add(builder);
      }

      return this;
   }

   public CustomResourceDefinitionVersionFluent addAllToAdditionalPrinterColumns(Collection items) {
      if (this.additionalPrinterColumns == null) {
         this.additionalPrinterColumns = new ArrayList();
      }

      for(CustomResourceColumnDefinition item : items) {
         CustomResourceColumnDefinitionBuilder builder = new CustomResourceColumnDefinitionBuilder(item);
         this._visitables.get("additionalPrinterColumns").add(builder);
         this.additionalPrinterColumns.add(builder);
      }

      return this;
   }

   public CustomResourceDefinitionVersionFluent removeFromAdditionalPrinterColumns(CustomResourceColumnDefinition... items) {
      if (this.additionalPrinterColumns == null) {
         return this;
      } else {
         for(CustomResourceColumnDefinition item : items) {
            CustomResourceColumnDefinitionBuilder builder = new CustomResourceColumnDefinitionBuilder(item);
            this._visitables.get("additionalPrinterColumns").remove(builder);
            this.additionalPrinterColumns.remove(builder);
         }

         return this;
      }
   }

   public CustomResourceDefinitionVersionFluent removeAllFromAdditionalPrinterColumns(Collection items) {
      if (this.additionalPrinterColumns == null) {
         return this;
      } else {
         for(CustomResourceColumnDefinition item : items) {
            CustomResourceColumnDefinitionBuilder builder = new CustomResourceColumnDefinitionBuilder(item);
            this._visitables.get("additionalPrinterColumns").remove(builder);
            this.additionalPrinterColumns.remove(builder);
         }

         return this;
      }
   }

   public CustomResourceDefinitionVersionFluent removeMatchingFromAdditionalPrinterColumns(Predicate predicate) {
      if (this.additionalPrinterColumns == null) {
         return this;
      } else {
         Iterator<CustomResourceColumnDefinitionBuilder> each = this.additionalPrinterColumns.iterator();
         List visitables = this._visitables.get("additionalPrinterColumns");

         while(each.hasNext()) {
            CustomResourceColumnDefinitionBuilder builder = (CustomResourceColumnDefinitionBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildAdditionalPrinterColumns() {
      return this.additionalPrinterColumns != null ? build(this.additionalPrinterColumns) : null;
   }

   public CustomResourceColumnDefinition buildAdditionalPrinterColumn(int index) {
      return ((CustomResourceColumnDefinitionBuilder)this.additionalPrinterColumns.get(index)).build();
   }

   public CustomResourceColumnDefinition buildFirstAdditionalPrinterColumn() {
      return ((CustomResourceColumnDefinitionBuilder)this.additionalPrinterColumns.get(0)).build();
   }

   public CustomResourceColumnDefinition buildLastAdditionalPrinterColumn() {
      return ((CustomResourceColumnDefinitionBuilder)this.additionalPrinterColumns.get(this.additionalPrinterColumns.size() - 1)).build();
   }

   public CustomResourceColumnDefinition buildMatchingAdditionalPrinterColumn(Predicate predicate) {
      for(CustomResourceColumnDefinitionBuilder item : this.additionalPrinterColumns) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingAdditionalPrinterColumn(Predicate predicate) {
      for(CustomResourceColumnDefinitionBuilder item : this.additionalPrinterColumns) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public CustomResourceDefinitionVersionFluent withAdditionalPrinterColumns(List additionalPrinterColumns) {
      if (this.additionalPrinterColumns != null) {
         this._visitables.get("additionalPrinterColumns").clear();
      }

      if (additionalPrinterColumns != null) {
         this.additionalPrinterColumns = new ArrayList();

         for(CustomResourceColumnDefinition item : additionalPrinterColumns) {
            this.addToAdditionalPrinterColumns(item);
         }
      } else {
         this.additionalPrinterColumns = null;
      }

      return this;
   }

   public CustomResourceDefinitionVersionFluent withAdditionalPrinterColumns(CustomResourceColumnDefinition... additionalPrinterColumns) {
      if (this.additionalPrinterColumns != null) {
         this.additionalPrinterColumns.clear();
         this._visitables.remove("additionalPrinterColumns");
      }

      if (additionalPrinterColumns != null) {
         for(CustomResourceColumnDefinition item : additionalPrinterColumns) {
            this.addToAdditionalPrinterColumns(item);
         }
      }

      return this;
   }

   public boolean hasAdditionalPrinterColumns() {
      return this.additionalPrinterColumns != null && !this.additionalPrinterColumns.isEmpty();
   }

   public AdditionalPrinterColumnsNested addNewAdditionalPrinterColumn() {
      return new AdditionalPrinterColumnsNested(-1, (CustomResourceColumnDefinition)null);
   }

   public AdditionalPrinterColumnsNested addNewAdditionalPrinterColumnLike(CustomResourceColumnDefinition item) {
      return new AdditionalPrinterColumnsNested(-1, item);
   }

   public AdditionalPrinterColumnsNested setNewAdditionalPrinterColumnLike(int index, CustomResourceColumnDefinition item) {
      return new AdditionalPrinterColumnsNested(index, item);
   }

   public AdditionalPrinterColumnsNested editAdditionalPrinterColumn(int index) {
      if (this.additionalPrinterColumns.size() <= index) {
         throw new RuntimeException("Can't edit additionalPrinterColumns. Index exceeds size.");
      } else {
         return this.setNewAdditionalPrinterColumnLike(index, this.buildAdditionalPrinterColumn(index));
      }
   }

   public AdditionalPrinterColumnsNested editFirstAdditionalPrinterColumn() {
      if (this.additionalPrinterColumns.size() == 0) {
         throw new RuntimeException("Can't edit first additionalPrinterColumns. The list is empty.");
      } else {
         return this.setNewAdditionalPrinterColumnLike(0, this.buildAdditionalPrinterColumn(0));
      }
   }

   public AdditionalPrinterColumnsNested editLastAdditionalPrinterColumn() {
      int index = this.additionalPrinterColumns.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last additionalPrinterColumns. The list is empty.");
      } else {
         return this.setNewAdditionalPrinterColumnLike(index, this.buildAdditionalPrinterColumn(index));
      }
   }

   public AdditionalPrinterColumnsNested editMatchingAdditionalPrinterColumn(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.additionalPrinterColumns.size(); ++i) {
         if (predicate.test((CustomResourceColumnDefinitionBuilder)this.additionalPrinterColumns.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching additionalPrinterColumns. No match found.");
      } else {
         return this.setNewAdditionalPrinterColumnLike(index, this.buildAdditionalPrinterColumn(index));
      }
   }

   public Boolean getDeprecated() {
      return this.deprecated;
   }

   public CustomResourceDefinitionVersionFluent withDeprecated(Boolean deprecated) {
      this.deprecated = deprecated;
      return this;
   }

   public boolean hasDeprecated() {
      return this.deprecated != null;
   }

   public String getDeprecationWarning() {
      return this.deprecationWarning;
   }

   public CustomResourceDefinitionVersionFluent withDeprecationWarning(String deprecationWarning) {
      this.deprecationWarning = deprecationWarning;
      return this;
   }

   public boolean hasDeprecationWarning() {
      return this.deprecationWarning != null;
   }

   public String getName() {
      return this.name;
   }

   public CustomResourceDefinitionVersionFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public CustomResourceValidation buildSchema() {
      return this.schema != null ? this.schema.build() : null;
   }

   public CustomResourceDefinitionVersionFluent withSchema(CustomResourceValidation schema) {
      this._visitables.remove("schema");
      if (schema != null) {
         this.schema = new CustomResourceValidationBuilder(schema);
         this._visitables.get("schema").add(this.schema);
      } else {
         this.schema = null;
         this._visitables.get("schema").remove(this.schema);
      }

      return this;
   }

   public boolean hasSchema() {
      return this.schema != null;
   }

   public SchemaNested withNewSchema() {
      return new SchemaNested((CustomResourceValidation)null);
   }

   public SchemaNested withNewSchemaLike(CustomResourceValidation item) {
      return new SchemaNested(item);
   }

   public SchemaNested editSchema() {
      return this.withNewSchemaLike((CustomResourceValidation)Optional.ofNullable(this.buildSchema()).orElse((Object)null));
   }

   public SchemaNested editOrNewSchema() {
      return this.withNewSchemaLike((CustomResourceValidation)Optional.ofNullable(this.buildSchema()).orElse((new CustomResourceValidationBuilder()).build()));
   }

   public SchemaNested editOrNewSchemaLike(CustomResourceValidation item) {
      return this.withNewSchemaLike((CustomResourceValidation)Optional.ofNullable(this.buildSchema()).orElse(item));
   }

   public CustomResourceDefinitionVersionFluent addToSelectableFields(int index, SelectableField item) {
      if (this.selectableFields == null) {
         this.selectableFields = new ArrayList();
      }

      SelectableFieldBuilder builder = new SelectableFieldBuilder(item);
      if (index >= 0 && index < this.selectableFields.size()) {
         this._visitables.get("selectableFields").add(index, builder);
         this.selectableFields.add(index, builder);
      } else {
         this._visitables.get("selectableFields").add(builder);
         this.selectableFields.add(builder);
      }

      return this;
   }

   public CustomResourceDefinitionVersionFluent setToSelectableFields(int index, SelectableField item) {
      if (this.selectableFields == null) {
         this.selectableFields = new ArrayList();
      }

      SelectableFieldBuilder builder = new SelectableFieldBuilder(item);
      if (index >= 0 && index < this.selectableFields.size()) {
         this._visitables.get("selectableFields").set(index, builder);
         this.selectableFields.set(index, builder);
      } else {
         this._visitables.get("selectableFields").add(builder);
         this.selectableFields.add(builder);
      }

      return this;
   }

   public CustomResourceDefinitionVersionFluent addToSelectableFields(SelectableField... items) {
      if (this.selectableFields == null) {
         this.selectableFields = new ArrayList();
      }

      for(SelectableField item : items) {
         SelectableFieldBuilder builder = new SelectableFieldBuilder(item);
         this._visitables.get("selectableFields").add(builder);
         this.selectableFields.add(builder);
      }

      return this;
   }

   public CustomResourceDefinitionVersionFluent addAllToSelectableFields(Collection items) {
      if (this.selectableFields == null) {
         this.selectableFields = new ArrayList();
      }

      for(SelectableField item : items) {
         SelectableFieldBuilder builder = new SelectableFieldBuilder(item);
         this._visitables.get("selectableFields").add(builder);
         this.selectableFields.add(builder);
      }

      return this;
   }

   public CustomResourceDefinitionVersionFluent removeFromSelectableFields(SelectableField... items) {
      if (this.selectableFields == null) {
         return this;
      } else {
         for(SelectableField item : items) {
            SelectableFieldBuilder builder = new SelectableFieldBuilder(item);
            this._visitables.get("selectableFields").remove(builder);
            this.selectableFields.remove(builder);
         }

         return this;
      }
   }

   public CustomResourceDefinitionVersionFluent removeAllFromSelectableFields(Collection items) {
      if (this.selectableFields == null) {
         return this;
      } else {
         for(SelectableField item : items) {
            SelectableFieldBuilder builder = new SelectableFieldBuilder(item);
            this._visitables.get("selectableFields").remove(builder);
            this.selectableFields.remove(builder);
         }

         return this;
      }
   }

   public CustomResourceDefinitionVersionFluent removeMatchingFromSelectableFields(Predicate predicate) {
      if (this.selectableFields == null) {
         return this;
      } else {
         Iterator<SelectableFieldBuilder> each = this.selectableFields.iterator();
         List visitables = this._visitables.get("selectableFields");

         while(each.hasNext()) {
            SelectableFieldBuilder builder = (SelectableFieldBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildSelectableFields() {
      return this.selectableFields != null ? build(this.selectableFields) : null;
   }

   public SelectableField buildSelectableField(int index) {
      return ((SelectableFieldBuilder)this.selectableFields.get(index)).build();
   }

   public SelectableField buildFirstSelectableField() {
      return ((SelectableFieldBuilder)this.selectableFields.get(0)).build();
   }

   public SelectableField buildLastSelectableField() {
      return ((SelectableFieldBuilder)this.selectableFields.get(this.selectableFields.size() - 1)).build();
   }

   public SelectableField buildMatchingSelectableField(Predicate predicate) {
      for(SelectableFieldBuilder item : this.selectableFields) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingSelectableField(Predicate predicate) {
      for(SelectableFieldBuilder item : this.selectableFields) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public CustomResourceDefinitionVersionFluent withSelectableFields(List selectableFields) {
      if (this.selectableFields != null) {
         this._visitables.get("selectableFields").clear();
      }

      if (selectableFields != null) {
         this.selectableFields = new ArrayList();

         for(SelectableField item : selectableFields) {
            this.addToSelectableFields(item);
         }
      } else {
         this.selectableFields = null;
      }

      return this;
   }

   public CustomResourceDefinitionVersionFluent withSelectableFields(SelectableField... selectableFields) {
      if (this.selectableFields != null) {
         this.selectableFields.clear();
         this._visitables.remove("selectableFields");
      }

      if (selectableFields != null) {
         for(SelectableField item : selectableFields) {
            this.addToSelectableFields(item);
         }
      }

      return this;
   }

   public boolean hasSelectableFields() {
      return this.selectableFields != null && !this.selectableFields.isEmpty();
   }

   public CustomResourceDefinitionVersionFluent addNewSelectableField(String jsonPath) {
      return this.addToSelectableFields(new SelectableField(jsonPath));
   }

   public SelectableFieldsNested addNewSelectableField() {
      return new SelectableFieldsNested(-1, (SelectableField)null);
   }

   public SelectableFieldsNested addNewSelectableFieldLike(SelectableField item) {
      return new SelectableFieldsNested(-1, item);
   }

   public SelectableFieldsNested setNewSelectableFieldLike(int index, SelectableField item) {
      return new SelectableFieldsNested(index, item);
   }

   public SelectableFieldsNested editSelectableField(int index) {
      if (this.selectableFields.size() <= index) {
         throw new RuntimeException("Can't edit selectableFields. Index exceeds size.");
      } else {
         return this.setNewSelectableFieldLike(index, this.buildSelectableField(index));
      }
   }

   public SelectableFieldsNested editFirstSelectableField() {
      if (this.selectableFields.size() == 0) {
         throw new RuntimeException("Can't edit first selectableFields. The list is empty.");
      } else {
         return this.setNewSelectableFieldLike(0, this.buildSelectableField(0));
      }
   }

   public SelectableFieldsNested editLastSelectableField() {
      int index = this.selectableFields.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last selectableFields. The list is empty.");
      } else {
         return this.setNewSelectableFieldLike(index, this.buildSelectableField(index));
      }
   }

   public SelectableFieldsNested editMatchingSelectableField(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.selectableFields.size(); ++i) {
         if (predicate.test((SelectableFieldBuilder)this.selectableFields.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching selectableFields. No match found.");
      } else {
         return this.setNewSelectableFieldLike(index, this.buildSelectableField(index));
      }
   }

   public Boolean getServed() {
      return this.served;
   }

   public CustomResourceDefinitionVersionFluent withServed(Boolean served) {
      this.served = served;
      return this;
   }

   public boolean hasServed() {
      return this.served != null;
   }

   public Boolean getStorage() {
      return this.storage;
   }

   public CustomResourceDefinitionVersionFluent withStorage(Boolean storage) {
      this.storage = storage;
      return this;
   }

   public boolean hasStorage() {
      return this.storage != null;
   }

   public CustomResourceSubresources buildSubresources() {
      return this.subresources != null ? this.subresources.build() : null;
   }

   public CustomResourceDefinitionVersionFluent withSubresources(CustomResourceSubresources subresources) {
      this._visitables.remove("subresources");
      if (subresources != null) {
         this.subresources = new CustomResourceSubresourcesBuilder(subresources);
         this._visitables.get("subresources").add(this.subresources);
      } else {
         this.subresources = null;
         this._visitables.get("subresources").remove(this.subresources);
      }

      return this;
   }

   public boolean hasSubresources() {
      return this.subresources != null;
   }

   public SubresourcesNested withNewSubresources() {
      return new SubresourcesNested((CustomResourceSubresources)null);
   }

   public SubresourcesNested withNewSubresourcesLike(CustomResourceSubresources item) {
      return new SubresourcesNested(item);
   }

   public SubresourcesNested editSubresources() {
      return this.withNewSubresourcesLike((CustomResourceSubresources)Optional.ofNullable(this.buildSubresources()).orElse((Object)null));
   }

   public SubresourcesNested editOrNewSubresources() {
      return this.withNewSubresourcesLike((CustomResourceSubresources)Optional.ofNullable(this.buildSubresources()).orElse((new CustomResourceSubresourcesBuilder()).build()));
   }

   public SubresourcesNested editOrNewSubresourcesLike(CustomResourceSubresources item) {
      return this.withNewSubresourcesLike((CustomResourceSubresources)Optional.ofNullable(this.buildSubresources()).orElse(item));
   }

   public CustomResourceDefinitionVersionFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public CustomResourceDefinitionVersionFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public CustomResourceDefinitionVersionFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public CustomResourceDefinitionVersionFluent removeFromAdditionalProperties(Map map) {
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

   public CustomResourceDefinitionVersionFluent withAdditionalProperties(Map additionalProperties) {
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
            CustomResourceDefinitionVersionFluent that = (CustomResourceDefinitionVersionFluent)o;
            if (!Objects.equals(this.additionalPrinterColumns, that.additionalPrinterColumns)) {
               return false;
            } else if (!Objects.equals(this.deprecated, that.deprecated)) {
               return false;
            } else if (!Objects.equals(this.deprecationWarning, that.deprecationWarning)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.schema, that.schema)) {
               return false;
            } else if (!Objects.equals(this.selectableFields, that.selectableFields)) {
               return false;
            } else if (!Objects.equals(this.served, that.served)) {
               return false;
            } else if (!Objects.equals(this.storage, that.storage)) {
               return false;
            } else if (!Objects.equals(this.subresources, that.subresources)) {
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
      return Objects.hash(new Object[]{this.additionalPrinterColumns, this.deprecated, this.deprecationWarning, this.name, this.schema, this.selectableFields, this.served, this.storage, this.subresources, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.additionalPrinterColumns != null && !this.additionalPrinterColumns.isEmpty()) {
         sb.append("additionalPrinterColumns:");
         sb.append(this.additionalPrinterColumns + ",");
      }

      if (this.deprecated != null) {
         sb.append("deprecated:");
         sb.append(this.deprecated + ",");
      }

      if (this.deprecationWarning != null) {
         sb.append("deprecationWarning:");
         sb.append(this.deprecationWarning + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.schema != null) {
         sb.append("schema:");
         sb.append(this.schema + ",");
      }

      if (this.selectableFields != null && !this.selectableFields.isEmpty()) {
         sb.append("selectableFields:");
         sb.append(this.selectableFields + ",");
      }

      if (this.served != null) {
         sb.append("served:");
         sb.append(this.served + ",");
      }

      if (this.storage != null) {
         sb.append("storage:");
         sb.append(this.storage + ",");
      }

      if (this.subresources != null) {
         sb.append("subresources:");
         sb.append(this.subresources + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public CustomResourceDefinitionVersionFluent withDeprecated() {
      return this.withDeprecated(true);
   }

   public CustomResourceDefinitionVersionFluent withServed() {
      return this.withServed(true);
   }

   public CustomResourceDefinitionVersionFluent withStorage() {
      return this.withStorage(true);
   }

   public class AdditionalPrinterColumnsNested extends CustomResourceColumnDefinitionFluent implements Nested {
      CustomResourceColumnDefinitionBuilder builder;
      int index;

      AdditionalPrinterColumnsNested(int index, CustomResourceColumnDefinition item) {
         this.index = index;
         this.builder = new CustomResourceColumnDefinitionBuilder(this, item);
      }

      public Object and() {
         return CustomResourceDefinitionVersionFluent.this.setToAdditionalPrinterColumns(this.index, this.builder.build());
      }

      public Object endAdditionalPrinterColumn() {
         return this.and();
      }
   }

   public class SchemaNested extends CustomResourceValidationFluent implements Nested {
      CustomResourceValidationBuilder builder;

      SchemaNested(CustomResourceValidation item) {
         this.builder = new CustomResourceValidationBuilder(this, item);
      }

      public Object and() {
         return CustomResourceDefinitionVersionFluent.this.withSchema(this.builder.build());
      }

      public Object endSchema() {
         return this.and();
      }
   }

   public class SelectableFieldsNested extends SelectableFieldFluent implements Nested {
      SelectableFieldBuilder builder;
      int index;

      SelectableFieldsNested(int index, SelectableField item) {
         this.index = index;
         this.builder = new SelectableFieldBuilder(this, item);
      }

      public Object and() {
         return CustomResourceDefinitionVersionFluent.this.setToSelectableFields(this.index, this.builder.build());
      }

      public Object endSelectableField() {
         return this.and();
      }
   }

   public class SubresourcesNested extends CustomResourceSubresourcesFluent implements Nested {
      CustomResourceSubresourcesBuilder builder;

      SubresourcesNested(CustomResourceSubresources item) {
         this.builder = new CustomResourceSubresourcesBuilder(this, item);
      }

      public Object and() {
         return CustomResourceDefinitionVersionFluent.this.withSubresources(this.builder.build());
      }

      public Object endSubresources() {
         return this.and();
      }
   }
}
