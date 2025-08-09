package io.fabric8.kubernetes.api.model.storagemigration.v1alpha1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.ListMeta;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class StorageVersionMigrationListFluent extends BaseFluent {
   private String apiVersion;
   private ArrayList items = new ArrayList();
   private String kind;
   private ListMeta metadata;
   private Map additionalProperties;

   public StorageVersionMigrationListFluent() {
   }

   public StorageVersionMigrationListFluent(StorageVersionMigrationList instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(StorageVersionMigrationList instance) {
      instance = instance != null ? instance : new StorageVersionMigrationList();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withItems(instance.getItems());
         this.withKind(instance.getKind());
         this.withMetadata(instance.getMetadata());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public StorageVersionMigrationListFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public StorageVersionMigrationListFluent addToItems(int index, StorageVersionMigration item) {
      if (this.items == null) {
         this.items = new ArrayList();
      }

      StorageVersionMigrationBuilder builder = new StorageVersionMigrationBuilder(item);
      if (index >= 0 && index < this.items.size()) {
         this._visitables.get("items").add(index, builder);
         this.items.add(index, builder);
      } else {
         this._visitables.get("items").add(builder);
         this.items.add(builder);
      }

      return this;
   }

   public StorageVersionMigrationListFluent setToItems(int index, StorageVersionMigration item) {
      if (this.items == null) {
         this.items = new ArrayList();
      }

      StorageVersionMigrationBuilder builder = new StorageVersionMigrationBuilder(item);
      if (index >= 0 && index < this.items.size()) {
         this._visitables.get("items").set(index, builder);
         this.items.set(index, builder);
      } else {
         this._visitables.get("items").add(builder);
         this.items.add(builder);
      }

      return this;
   }

   public StorageVersionMigrationListFluent addToItems(StorageVersionMigration... items) {
      if (this.items == null) {
         this.items = new ArrayList();
      }

      for(StorageVersionMigration item : items) {
         StorageVersionMigrationBuilder builder = new StorageVersionMigrationBuilder(item);
         this._visitables.get("items").add(builder);
         this.items.add(builder);
      }

      return this;
   }

   public StorageVersionMigrationListFluent addAllToItems(Collection items) {
      if (this.items == null) {
         this.items = new ArrayList();
      }

      for(StorageVersionMigration item : items) {
         StorageVersionMigrationBuilder builder = new StorageVersionMigrationBuilder(item);
         this._visitables.get("items").add(builder);
         this.items.add(builder);
      }

      return this;
   }

   public StorageVersionMigrationListFluent removeFromItems(StorageVersionMigration... items) {
      if (this.items == null) {
         return this;
      } else {
         for(StorageVersionMigration item : items) {
            StorageVersionMigrationBuilder builder = new StorageVersionMigrationBuilder(item);
            this._visitables.get("items").remove(builder);
            this.items.remove(builder);
         }

         return this;
      }
   }

   public StorageVersionMigrationListFluent removeAllFromItems(Collection items) {
      if (this.items == null) {
         return this;
      } else {
         for(StorageVersionMigration item : items) {
            StorageVersionMigrationBuilder builder = new StorageVersionMigrationBuilder(item);
            this._visitables.get("items").remove(builder);
            this.items.remove(builder);
         }

         return this;
      }
   }

   public StorageVersionMigrationListFluent removeMatchingFromItems(Predicate predicate) {
      if (this.items == null) {
         return this;
      } else {
         Iterator<StorageVersionMigrationBuilder> each = this.items.iterator();
         List visitables = this._visitables.get("items");

         while(each.hasNext()) {
            StorageVersionMigrationBuilder builder = (StorageVersionMigrationBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildItems() {
      return this.items != null ? build(this.items) : null;
   }

   public StorageVersionMigration buildItem(int index) {
      return ((StorageVersionMigrationBuilder)this.items.get(index)).build();
   }

   public StorageVersionMigration buildFirstItem() {
      return ((StorageVersionMigrationBuilder)this.items.get(0)).build();
   }

   public StorageVersionMigration buildLastItem() {
      return ((StorageVersionMigrationBuilder)this.items.get(this.items.size() - 1)).build();
   }

   public StorageVersionMigration buildMatchingItem(Predicate predicate) {
      for(StorageVersionMigrationBuilder item : this.items) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingItem(Predicate predicate) {
      for(StorageVersionMigrationBuilder item : this.items) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public StorageVersionMigrationListFluent withItems(List items) {
      if (this.items != null) {
         this._visitables.get("items").clear();
      }

      if (items != null) {
         this.items = new ArrayList();

         for(StorageVersionMigration item : items) {
            this.addToItems(item);
         }
      } else {
         this.items = null;
      }

      return this;
   }

   public StorageVersionMigrationListFluent withItems(StorageVersionMigration... items) {
      if (this.items != null) {
         this.items.clear();
         this._visitables.remove("items");
      }

      if (items != null) {
         for(StorageVersionMigration item : items) {
            this.addToItems(item);
         }
      }

      return this;
   }

   public boolean hasItems() {
      return this.items != null && !this.items.isEmpty();
   }

   public ItemsNested addNewItem() {
      return new ItemsNested(-1, (StorageVersionMigration)null);
   }

   public ItemsNested addNewItemLike(StorageVersionMigration item) {
      return new ItemsNested(-1, item);
   }

   public ItemsNested setNewItemLike(int index, StorageVersionMigration item) {
      return new ItemsNested(index, item);
   }

   public ItemsNested editItem(int index) {
      if (this.items.size() <= index) {
         throw new RuntimeException("Can't edit items. Index exceeds size.");
      } else {
         return this.setNewItemLike(index, this.buildItem(index));
      }
   }

   public ItemsNested editFirstItem() {
      if (this.items.size() == 0) {
         throw new RuntimeException("Can't edit first items. The list is empty.");
      } else {
         return this.setNewItemLike(0, this.buildItem(0));
      }
   }

   public ItemsNested editLastItem() {
      int index = this.items.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last items. The list is empty.");
      } else {
         return this.setNewItemLike(index, this.buildItem(index));
      }
   }

   public ItemsNested editMatchingItem(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.items.size(); ++i) {
         if (predicate.test((StorageVersionMigrationBuilder)this.items.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching items. No match found.");
      } else {
         return this.setNewItemLike(index, this.buildItem(index));
      }
   }

   public String getKind() {
      return this.kind;
   }

   public StorageVersionMigrationListFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public ListMeta getMetadata() {
      return this.metadata;
   }

   public StorageVersionMigrationListFluent withMetadata(ListMeta metadata) {
      this.metadata = metadata;
      return this;
   }

   public boolean hasMetadata() {
      return this.metadata != null;
   }

   public StorageVersionMigrationListFluent withNewMetadata(String _continue, Long remainingItemCount, String resourceVersion, String selfLink) {
      return this.withMetadata(new ListMeta(_continue, remainingItemCount, resourceVersion, selfLink));
   }

   public StorageVersionMigrationListFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public StorageVersionMigrationListFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public StorageVersionMigrationListFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public StorageVersionMigrationListFluent removeFromAdditionalProperties(Map map) {
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

   public StorageVersionMigrationListFluent withAdditionalProperties(Map additionalProperties) {
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
            StorageVersionMigrationListFluent that = (StorageVersionMigrationListFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.items, that.items)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.metadata, that.metadata)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.items, this.kind, this.metadata, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.items != null && !this.items.isEmpty()) {
         sb.append("items:");
         sb.append(this.items + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.metadata != null) {
         sb.append("metadata:");
         sb.append(this.metadata + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ItemsNested extends StorageVersionMigrationFluent implements Nested {
      StorageVersionMigrationBuilder builder;
      int index;

      ItemsNested(int index, StorageVersionMigration item) {
         this.index = index;
         this.builder = new StorageVersionMigrationBuilder(this, item);
      }

      public Object and() {
         return StorageVersionMigrationListFluent.this.setToItems(this.index, this.builder.build());
      }

      public Object endItem() {
         return this.and();
      }
   }
}
