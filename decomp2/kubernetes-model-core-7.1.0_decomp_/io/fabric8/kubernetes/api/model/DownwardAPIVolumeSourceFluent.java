package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class DownwardAPIVolumeSourceFluent extends BaseFluent {
   private Integer defaultMode;
   private ArrayList items = new ArrayList();
   private Map additionalProperties;

   public DownwardAPIVolumeSourceFluent() {
   }

   public DownwardAPIVolumeSourceFluent(DownwardAPIVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(DownwardAPIVolumeSource instance) {
      instance = instance != null ? instance : new DownwardAPIVolumeSource();
      if (instance != null) {
         this.withDefaultMode(instance.getDefaultMode());
         this.withItems(instance.getItems());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getDefaultMode() {
      return this.defaultMode;
   }

   public DownwardAPIVolumeSourceFluent withDefaultMode(Integer defaultMode) {
      this.defaultMode = defaultMode;
      return this;
   }

   public boolean hasDefaultMode() {
      return this.defaultMode != null;
   }

   public DownwardAPIVolumeSourceFluent addToItems(int index, DownwardAPIVolumeFile item) {
      if (this.items == null) {
         this.items = new ArrayList();
      }

      DownwardAPIVolumeFileBuilder builder = new DownwardAPIVolumeFileBuilder(item);
      if (index >= 0 && index < this.items.size()) {
         this._visitables.get("items").add(index, builder);
         this.items.add(index, builder);
      } else {
         this._visitables.get("items").add(builder);
         this.items.add(builder);
      }

      return this;
   }

   public DownwardAPIVolumeSourceFluent setToItems(int index, DownwardAPIVolumeFile item) {
      if (this.items == null) {
         this.items = new ArrayList();
      }

      DownwardAPIVolumeFileBuilder builder = new DownwardAPIVolumeFileBuilder(item);
      if (index >= 0 && index < this.items.size()) {
         this._visitables.get("items").set(index, builder);
         this.items.set(index, builder);
      } else {
         this._visitables.get("items").add(builder);
         this.items.add(builder);
      }

      return this;
   }

   public DownwardAPIVolumeSourceFluent addToItems(DownwardAPIVolumeFile... items) {
      if (this.items == null) {
         this.items = new ArrayList();
      }

      for(DownwardAPIVolumeFile item : items) {
         DownwardAPIVolumeFileBuilder builder = new DownwardAPIVolumeFileBuilder(item);
         this._visitables.get("items").add(builder);
         this.items.add(builder);
      }

      return this;
   }

   public DownwardAPIVolumeSourceFluent addAllToItems(Collection items) {
      if (this.items == null) {
         this.items = new ArrayList();
      }

      for(DownwardAPIVolumeFile item : items) {
         DownwardAPIVolumeFileBuilder builder = new DownwardAPIVolumeFileBuilder(item);
         this._visitables.get("items").add(builder);
         this.items.add(builder);
      }

      return this;
   }

   public DownwardAPIVolumeSourceFluent removeFromItems(DownwardAPIVolumeFile... items) {
      if (this.items == null) {
         return this;
      } else {
         for(DownwardAPIVolumeFile item : items) {
            DownwardAPIVolumeFileBuilder builder = new DownwardAPIVolumeFileBuilder(item);
            this._visitables.get("items").remove(builder);
            this.items.remove(builder);
         }

         return this;
      }
   }

   public DownwardAPIVolumeSourceFluent removeAllFromItems(Collection items) {
      if (this.items == null) {
         return this;
      } else {
         for(DownwardAPIVolumeFile item : items) {
            DownwardAPIVolumeFileBuilder builder = new DownwardAPIVolumeFileBuilder(item);
            this._visitables.get("items").remove(builder);
            this.items.remove(builder);
         }

         return this;
      }
   }

   public DownwardAPIVolumeSourceFluent removeMatchingFromItems(Predicate predicate) {
      if (this.items == null) {
         return this;
      } else {
         Iterator<DownwardAPIVolumeFileBuilder> each = this.items.iterator();
         List visitables = this._visitables.get("items");

         while(each.hasNext()) {
            DownwardAPIVolumeFileBuilder builder = (DownwardAPIVolumeFileBuilder)each.next();
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

   public DownwardAPIVolumeFile buildItem(int index) {
      return ((DownwardAPIVolumeFileBuilder)this.items.get(index)).build();
   }

   public DownwardAPIVolumeFile buildFirstItem() {
      return ((DownwardAPIVolumeFileBuilder)this.items.get(0)).build();
   }

   public DownwardAPIVolumeFile buildLastItem() {
      return ((DownwardAPIVolumeFileBuilder)this.items.get(this.items.size() - 1)).build();
   }

   public DownwardAPIVolumeFile buildMatchingItem(Predicate predicate) {
      for(DownwardAPIVolumeFileBuilder item : this.items) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingItem(Predicate predicate) {
      for(DownwardAPIVolumeFileBuilder item : this.items) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public DownwardAPIVolumeSourceFluent withItems(List items) {
      if (this.items != null) {
         this._visitables.get("items").clear();
      }

      if (items != null) {
         this.items = new ArrayList();

         for(DownwardAPIVolumeFile item : items) {
            this.addToItems(item);
         }
      } else {
         this.items = null;
      }

      return this;
   }

   public DownwardAPIVolumeSourceFluent withItems(DownwardAPIVolumeFile... items) {
      if (this.items != null) {
         this.items.clear();
         this._visitables.remove("items");
      }

      if (items != null) {
         for(DownwardAPIVolumeFile item : items) {
            this.addToItems(item);
         }
      }

      return this;
   }

   public boolean hasItems() {
      return this.items != null && !this.items.isEmpty();
   }

   public ItemsNested addNewItem() {
      return new ItemsNested(-1, (DownwardAPIVolumeFile)null);
   }

   public ItemsNested addNewItemLike(DownwardAPIVolumeFile item) {
      return new ItemsNested(-1, item);
   }

   public ItemsNested setNewItemLike(int index, DownwardAPIVolumeFile item) {
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
         if (predicate.test((DownwardAPIVolumeFileBuilder)this.items.get(i))) {
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

   public DownwardAPIVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public DownwardAPIVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public DownwardAPIVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public DownwardAPIVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public DownwardAPIVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            DownwardAPIVolumeSourceFluent that = (DownwardAPIVolumeSourceFluent)o;
            if (!Objects.equals(this.defaultMode, that.defaultMode)) {
               return false;
            } else if (!Objects.equals(this.items, that.items)) {
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
      return Objects.hash(new Object[]{this.defaultMode, this.items, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.defaultMode != null) {
         sb.append("defaultMode:");
         sb.append(this.defaultMode + ",");
      }

      if (this.items != null && !this.items.isEmpty()) {
         sb.append("items:");
         sb.append(this.items + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ItemsNested extends DownwardAPIVolumeFileFluent implements Nested {
      DownwardAPIVolumeFileBuilder builder;
      int index;

      ItemsNested(int index, DownwardAPIVolumeFile item) {
         this.index = index;
         this.builder = new DownwardAPIVolumeFileBuilder(this, item);
      }

      public Object and() {
         return DownwardAPIVolumeSourceFluent.this.setToItems(this.index, this.builder.build());
      }

      public Object endItem() {
         return this.and();
      }
   }
}
