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

public class SecretVolumeSourceFluent extends BaseFluent {
   private Integer defaultMode;
   private ArrayList items = new ArrayList();
   private Boolean optional;
   private String secretName;
   private Map additionalProperties;

   public SecretVolumeSourceFluent() {
   }

   public SecretVolumeSourceFluent(SecretVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(SecretVolumeSource instance) {
      instance = instance != null ? instance : new SecretVolumeSource();
      if (instance != null) {
         this.withDefaultMode(instance.getDefaultMode());
         this.withItems(instance.getItems());
         this.withOptional(instance.getOptional());
         this.withSecretName(instance.getSecretName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getDefaultMode() {
      return this.defaultMode;
   }

   public SecretVolumeSourceFluent withDefaultMode(Integer defaultMode) {
      this.defaultMode = defaultMode;
      return this;
   }

   public boolean hasDefaultMode() {
      return this.defaultMode != null;
   }

   public SecretVolumeSourceFluent addToItems(int index, KeyToPath item) {
      if (this.items == null) {
         this.items = new ArrayList();
      }

      KeyToPathBuilder builder = new KeyToPathBuilder(item);
      if (index >= 0 && index < this.items.size()) {
         this._visitables.get("items").add(index, builder);
         this.items.add(index, builder);
      } else {
         this._visitables.get("items").add(builder);
         this.items.add(builder);
      }

      return this;
   }

   public SecretVolumeSourceFluent setToItems(int index, KeyToPath item) {
      if (this.items == null) {
         this.items = new ArrayList();
      }

      KeyToPathBuilder builder = new KeyToPathBuilder(item);
      if (index >= 0 && index < this.items.size()) {
         this._visitables.get("items").set(index, builder);
         this.items.set(index, builder);
      } else {
         this._visitables.get("items").add(builder);
         this.items.add(builder);
      }

      return this;
   }

   public SecretVolumeSourceFluent addToItems(KeyToPath... items) {
      if (this.items == null) {
         this.items = new ArrayList();
      }

      for(KeyToPath item : items) {
         KeyToPathBuilder builder = new KeyToPathBuilder(item);
         this._visitables.get("items").add(builder);
         this.items.add(builder);
      }

      return this;
   }

   public SecretVolumeSourceFluent addAllToItems(Collection items) {
      if (this.items == null) {
         this.items = new ArrayList();
      }

      for(KeyToPath item : items) {
         KeyToPathBuilder builder = new KeyToPathBuilder(item);
         this._visitables.get("items").add(builder);
         this.items.add(builder);
      }

      return this;
   }

   public SecretVolumeSourceFluent removeFromItems(KeyToPath... items) {
      if (this.items == null) {
         return this;
      } else {
         for(KeyToPath item : items) {
            KeyToPathBuilder builder = new KeyToPathBuilder(item);
            this._visitables.get("items").remove(builder);
            this.items.remove(builder);
         }

         return this;
      }
   }

   public SecretVolumeSourceFluent removeAllFromItems(Collection items) {
      if (this.items == null) {
         return this;
      } else {
         for(KeyToPath item : items) {
            KeyToPathBuilder builder = new KeyToPathBuilder(item);
            this._visitables.get("items").remove(builder);
            this.items.remove(builder);
         }

         return this;
      }
   }

   public SecretVolumeSourceFluent removeMatchingFromItems(Predicate predicate) {
      if (this.items == null) {
         return this;
      } else {
         Iterator<KeyToPathBuilder> each = this.items.iterator();
         List visitables = this._visitables.get("items");

         while(each.hasNext()) {
            KeyToPathBuilder builder = (KeyToPathBuilder)each.next();
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

   public KeyToPath buildItem(int index) {
      return ((KeyToPathBuilder)this.items.get(index)).build();
   }

   public KeyToPath buildFirstItem() {
      return ((KeyToPathBuilder)this.items.get(0)).build();
   }

   public KeyToPath buildLastItem() {
      return ((KeyToPathBuilder)this.items.get(this.items.size() - 1)).build();
   }

   public KeyToPath buildMatchingItem(Predicate predicate) {
      for(KeyToPathBuilder item : this.items) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingItem(Predicate predicate) {
      for(KeyToPathBuilder item : this.items) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public SecretVolumeSourceFluent withItems(List items) {
      if (this.items != null) {
         this._visitables.get("items").clear();
      }

      if (items != null) {
         this.items = new ArrayList();

         for(KeyToPath item : items) {
            this.addToItems(item);
         }
      } else {
         this.items = null;
      }

      return this;
   }

   public SecretVolumeSourceFluent withItems(KeyToPath... items) {
      if (this.items != null) {
         this.items.clear();
         this._visitables.remove("items");
      }

      if (items != null) {
         for(KeyToPath item : items) {
            this.addToItems(item);
         }
      }

      return this;
   }

   public boolean hasItems() {
      return this.items != null && !this.items.isEmpty();
   }

   public SecretVolumeSourceFluent addNewItem(String key, Integer mode, String path) {
      return this.addToItems(new KeyToPath(key, mode, path));
   }

   public ItemsNested addNewItem() {
      return new ItemsNested(-1, (KeyToPath)null);
   }

   public ItemsNested addNewItemLike(KeyToPath item) {
      return new ItemsNested(-1, item);
   }

   public ItemsNested setNewItemLike(int index, KeyToPath item) {
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
         if (predicate.test((KeyToPathBuilder)this.items.get(i))) {
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

   public Boolean getOptional() {
      return this.optional;
   }

   public SecretVolumeSourceFluent withOptional(Boolean optional) {
      this.optional = optional;
      return this;
   }

   public boolean hasOptional() {
      return this.optional != null;
   }

   public String getSecretName() {
      return this.secretName;
   }

   public SecretVolumeSourceFluent withSecretName(String secretName) {
      this.secretName = secretName;
      return this;
   }

   public boolean hasSecretName() {
      return this.secretName != null;
   }

   public SecretVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public SecretVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public SecretVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public SecretVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public SecretVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            SecretVolumeSourceFluent that = (SecretVolumeSourceFluent)o;
            if (!Objects.equals(this.defaultMode, that.defaultMode)) {
               return false;
            } else if (!Objects.equals(this.items, that.items)) {
               return false;
            } else if (!Objects.equals(this.optional, that.optional)) {
               return false;
            } else if (!Objects.equals(this.secretName, that.secretName)) {
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
      return Objects.hash(new Object[]{this.defaultMode, this.items, this.optional, this.secretName, this.additionalProperties, super.hashCode()});
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

      if (this.optional != null) {
         sb.append("optional:");
         sb.append(this.optional + ",");
      }

      if (this.secretName != null) {
         sb.append("secretName:");
         sb.append(this.secretName + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public SecretVolumeSourceFluent withOptional() {
      return this.withOptional(true);
   }

   public class ItemsNested extends KeyToPathFluent implements Nested {
      KeyToPathBuilder builder;
      int index;

      ItemsNested(int index, KeyToPath item) {
         this.index = index;
         this.builder = new KeyToPathBuilder(this, item);
      }

      public Object and() {
         return SecretVolumeSourceFluent.this.setToItems(this.index, this.builder.build());
      }

      public Object endItem() {
         return this.and();
      }
   }
}
