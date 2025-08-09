package io.fabric8.kubernetes.api.model.networking.v1;

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

public class IngressListFluent extends BaseFluent {
   private String apiVersion;
   private ArrayList items = new ArrayList();
   private String kind;
   private ListMeta metadata;
   private Map additionalProperties;

   public IngressListFluent() {
   }

   public IngressListFluent(IngressList instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(IngressList instance) {
      instance = instance != null ? instance : new IngressList();
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

   public IngressListFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public IngressListFluent addToItems(int index, Ingress item) {
      if (this.items == null) {
         this.items = new ArrayList();
      }

      IngressBuilder builder = new IngressBuilder(item);
      if (index >= 0 && index < this.items.size()) {
         this._visitables.get("items").add(index, builder);
         this.items.add(index, builder);
      } else {
         this._visitables.get("items").add(builder);
         this.items.add(builder);
      }

      return this;
   }

   public IngressListFluent setToItems(int index, Ingress item) {
      if (this.items == null) {
         this.items = new ArrayList();
      }

      IngressBuilder builder = new IngressBuilder(item);
      if (index >= 0 && index < this.items.size()) {
         this._visitables.get("items").set(index, builder);
         this.items.set(index, builder);
      } else {
         this._visitables.get("items").add(builder);
         this.items.add(builder);
      }

      return this;
   }

   public IngressListFluent addToItems(Ingress... items) {
      if (this.items == null) {
         this.items = new ArrayList();
      }

      for(Ingress item : items) {
         IngressBuilder builder = new IngressBuilder(item);
         this._visitables.get("items").add(builder);
         this.items.add(builder);
      }

      return this;
   }

   public IngressListFluent addAllToItems(Collection items) {
      if (this.items == null) {
         this.items = new ArrayList();
      }

      for(Ingress item : items) {
         IngressBuilder builder = new IngressBuilder(item);
         this._visitables.get("items").add(builder);
         this.items.add(builder);
      }

      return this;
   }

   public IngressListFluent removeFromItems(Ingress... items) {
      if (this.items == null) {
         return this;
      } else {
         for(Ingress item : items) {
            IngressBuilder builder = new IngressBuilder(item);
            this._visitables.get("items").remove(builder);
            this.items.remove(builder);
         }

         return this;
      }
   }

   public IngressListFluent removeAllFromItems(Collection items) {
      if (this.items == null) {
         return this;
      } else {
         for(Ingress item : items) {
            IngressBuilder builder = new IngressBuilder(item);
            this._visitables.get("items").remove(builder);
            this.items.remove(builder);
         }

         return this;
      }
   }

   public IngressListFluent removeMatchingFromItems(Predicate predicate) {
      if (this.items == null) {
         return this;
      } else {
         Iterator<IngressBuilder> each = this.items.iterator();
         List visitables = this._visitables.get("items");

         while(each.hasNext()) {
            IngressBuilder builder = (IngressBuilder)each.next();
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

   public Ingress buildItem(int index) {
      return ((IngressBuilder)this.items.get(index)).build();
   }

   public Ingress buildFirstItem() {
      return ((IngressBuilder)this.items.get(0)).build();
   }

   public Ingress buildLastItem() {
      return ((IngressBuilder)this.items.get(this.items.size() - 1)).build();
   }

   public Ingress buildMatchingItem(Predicate predicate) {
      for(IngressBuilder item : this.items) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingItem(Predicate predicate) {
      for(IngressBuilder item : this.items) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public IngressListFluent withItems(List items) {
      if (this.items != null) {
         this._visitables.get("items").clear();
      }

      if (items != null) {
         this.items = new ArrayList();

         for(Ingress item : items) {
            this.addToItems(item);
         }
      } else {
         this.items = null;
      }

      return this;
   }

   public IngressListFluent withItems(Ingress... items) {
      if (this.items != null) {
         this.items.clear();
         this._visitables.remove("items");
      }

      if (items != null) {
         for(Ingress item : items) {
            this.addToItems(item);
         }
      }

      return this;
   }

   public boolean hasItems() {
      return this.items != null && !this.items.isEmpty();
   }

   public ItemsNested addNewItem() {
      return new ItemsNested(-1, (Ingress)null);
   }

   public ItemsNested addNewItemLike(Ingress item) {
      return new ItemsNested(-1, item);
   }

   public ItemsNested setNewItemLike(int index, Ingress item) {
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
         if (predicate.test((IngressBuilder)this.items.get(i))) {
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

   public IngressListFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public ListMeta getMetadata() {
      return this.metadata;
   }

   public IngressListFluent withMetadata(ListMeta metadata) {
      this.metadata = metadata;
      return this;
   }

   public boolean hasMetadata() {
      return this.metadata != null;
   }

   public IngressListFluent withNewMetadata(String _continue, Long remainingItemCount, String resourceVersion, String selfLink) {
      return this.withMetadata(new ListMeta(_continue, remainingItemCount, resourceVersion, selfLink));
   }

   public IngressListFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public IngressListFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public IngressListFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public IngressListFluent removeFromAdditionalProperties(Map map) {
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

   public IngressListFluent withAdditionalProperties(Map additionalProperties) {
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
            IngressListFluent that = (IngressListFluent)o;
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

   public class ItemsNested extends IngressFluent implements Nested {
      IngressBuilder builder;
      int index;

      ItemsNested(int index, Ingress item) {
         this.index = index;
         this.builder = new IngressBuilder(this, item);
      }

      public Object and() {
         return IngressListFluent.this.setToItems(this.index, this.builder.build());
      }

      public Object endItem() {
         return this.and();
      }
   }
}
