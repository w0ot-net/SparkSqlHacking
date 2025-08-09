package io.fabric8.kubernetes.api.model.gatewayapi.v1;

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

public class HTTPHeaderFilterFluent extends BaseFluent {
   private ArrayList add = new ArrayList();
   private List remove = new ArrayList();
   private ArrayList set = new ArrayList();
   private Map additionalProperties;

   public HTTPHeaderFilterFluent() {
   }

   public HTTPHeaderFilterFluent(HTTPHeaderFilter instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(HTTPHeaderFilter instance) {
      instance = instance != null ? instance : new HTTPHeaderFilter();
      if (instance != null) {
         this.withAdd(instance.getAdd());
         this.withRemove(instance.getRemove());
         this.withSet(instance.getSet());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public HTTPHeaderFilterFluent addToAdd(int index, HTTPHeader item) {
      if (this.add == null) {
         this.add = new ArrayList();
      }

      HTTPHeaderBuilder builder = new HTTPHeaderBuilder(item);
      if (index >= 0 && index < this.add.size()) {
         this._visitables.get("add").add(index, builder);
         this.add.add(index, builder);
      } else {
         this._visitables.get("add").add(builder);
         this.add.add(builder);
      }

      return this;
   }

   public HTTPHeaderFilterFluent setToAdd(int index, HTTPHeader item) {
      if (this.add == null) {
         this.add = new ArrayList();
      }

      HTTPHeaderBuilder builder = new HTTPHeaderBuilder(item);
      if (index >= 0 && index < this.add.size()) {
         this._visitables.get("add").set(index, builder);
         this.add.set(index, builder);
      } else {
         this._visitables.get("add").add(builder);
         this.add.add(builder);
      }

      return this;
   }

   public HTTPHeaderFilterFluent addToAdd(HTTPHeader... items) {
      if (this.add == null) {
         this.add = new ArrayList();
      }

      for(HTTPHeader item : items) {
         HTTPHeaderBuilder builder = new HTTPHeaderBuilder(item);
         this._visitables.get("add").add(builder);
         this.add.add(builder);
      }

      return this;
   }

   public HTTPHeaderFilterFluent addAllToAdd(Collection items) {
      if (this.add == null) {
         this.add = new ArrayList();
      }

      for(HTTPHeader item : items) {
         HTTPHeaderBuilder builder = new HTTPHeaderBuilder(item);
         this._visitables.get("add").add(builder);
         this.add.add(builder);
      }

      return this;
   }

   public HTTPHeaderFilterFluent removeFromAdd(HTTPHeader... items) {
      if (this.add == null) {
         return this;
      } else {
         for(HTTPHeader item : items) {
            HTTPHeaderBuilder builder = new HTTPHeaderBuilder(item);
            this._visitables.get("add").remove(builder);
            this.add.remove(builder);
         }

         return this;
      }
   }

   public HTTPHeaderFilterFluent removeAllFromAdd(Collection items) {
      if (this.add == null) {
         return this;
      } else {
         for(HTTPHeader item : items) {
            HTTPHeaderBuilder builder = new HTTPHeaderBuilder(item);
            this._visitables.get("add").remove(builder);
            this.add.remove(builder);
         }

         return this;
      }
   }

   public HTTPHeaderFilterFluent removeMatchingFromAdd(Predicate predicate) {
      if (this.add == null) {
         return this;
      } else {
         Iterator<HTTPHeaderBuilder> each = this.add.iterator();
         List visitables = this._visitables.get("add");

         while(each.hasNext()) {
            HTTPHeaderBuilder builder = (HTTPHeaderBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildAdd() {
      return this.add != null ? build(this.add) : null;
   }

   public HTTPHeader buildAdd(int index) {
      return ((HTTPHeaderBuilder)this.add.get(index)).build();
   }

   public HTTPHeader buildFirstAdd() {
      return ((HTTPHeaderBuilder)this.add.get(0)).build();
   }

   public HTTPHeader buildLastAdd() {
      return ((HTTPHeaderBuilder)this.add.get(this.add.size() - 1)).build();
   }

   public HTTPHeader buildMatchingAdd(Predicate predicate) {
      for(HTTPHeaderBuilder item : this.add) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingAdd(Predicate predicate) {
      for(HTTPHeaderBuilder item : this.add) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public HTTPHeaderFilterFluent withAdd(List add) {
      if (this.add != null) {
         this._visitables.get("add").clear();
      }

      if (add != null) {
         this.add = new ArrayList();

         for(HTTPHeader item : add) {
            this.addToAdd(item);
         }
      } else {
         this.add = null;
      }

      return this;
   }

   public HTTPHeaderFilterFluent withAdd(HTTPHeader... add) {
      if (this.add != null) {
         this.add.clear();
         this._visitables.remove("add");
      }

      if (add != null) {
         for(HTTPHeader item : add) {
            this.addToAdd(item);
         }
      }

      return this;
   }

   public boolean hasAdd() {
      return this.add != null && !this.add.isEmpty();
   }

   public HTTPHeaderFilterFluent addNewAdd(String name, String value) {
      return this.addToAdd(new HTTPHeader(name, value));
   }

   public AddNested addNewAdd() {
      return new AddNested(-1, (HTTPHeader)null);
   }

   public AddNested addNewAddLike(HTTPHeader item) {
      return new AddNested(-1, item);
   }

   public AddNested setNewAddLike(int index, HTTPHeader item) {
      return new AddNested(index, item);
   }

   public AddNested editAdd(int index) {
      if (this.add.size() <= index) {
         throw new RuntimeException("Can't edit add. Index exceeds size.");
      } else {
         return this.setNewAddLike(index, this.buildAdd(index));
      }
   }

   public AddNested editFirstAdd() {
      if (this.add.size() == 0) {
         throw new RuntimeException("Can't edit first add. The list is empty.");
      } else {
         return this.setNewAddLike(0, this.buildAdd(0));
      }
   }

   public AddNested editLastAdd() {
      int index = this.add.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last add. The list is empty.");
      } else {
         return this.setNewAddLike(index, this.buildAdd(index));
      }
   }

   public AddNested editMatchingAdd(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.add.size(); ++i) {
         if (predicate.test((HTTPHeaderBuilder)this.add.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching add. No match found.");
      } else {
         return this.setNewAddLike(index, this.buildAdd(index));
      }
   }

   public HTTPHeaderFilterFluent addToRemove(int index, String item) {
      if (this.remove == null) {
         this.remove = new ArrayList();
      }

      this.remove.add(index, item);
      return this;
   }

   public HTTPHeaderFilterFluent setToRemove(int index, String item) {
      if (this.remove == null) {
         this.remove = new ArrayList();
      }

      this.remove.set(index, item);
      return this;
   }

   public HTTPHeaderFilterFluent addToRemove(String... items) {
      if (this.remove == null) {
         this.remove = new ArrayList();
      }

      for(String item : items) {
         this.remove.add(item);
      }

      return this;
   }

   public HTTPHeaderFilterFluent addAllToRemove(Collection items) {
      if (this.remove == null) {
         this.remove = new ArrayList();
      }

      for(String item : items) {
         this.remove.add(item);
      }

      return this;
   }

   public HTTPHeaderFilterFluent removeFromRemove(String... items) {
      if (this.remove == null) {
         return this;
      } else {
         for(String item : items) {
            this.remove.remove(item);
         }

         return this;
      }
   }

   public HTTPHeaderFilterFluent removeAllFromRemove(Collection items) {
      if (this.remove == null) {
         return this;
      } else {
         for(String item : items) {
            this.remove.remove(item);
         }

         return this;
      }
   }

   public List getRemove() {
      return this.remove;
   }

   public String getRemove(int index) {
      return (String)this.remove.get(index);
   }

   public String getFirstRemove() {
      return (String)this.remove.get(0);
   }

   public String getLastRemove() {
      return (String)this.remove.get(this.remove.size() - 1);
   }

   public String getMatchingRemove(Predicate predicate) {
      for(String item : this.remove) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingRemove(Predicate predicate) {
      for(String item : this.remove) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public HTTPHeaderFilterFluent withRemove(List remove) {
      if (remove != null) {
         this.remove = new ArrayList();

         for(String item : remove) {
            this.addToRemove(item);
         }
      } else {
         this.remove = null;
      }

      return this;
   }

   public HTTPHeaderFilterFluent withRemove(String... remove) {
      if (this.remove != null) {
         this.remove.clear();
         this._visitables.remove("remove");
      }

      if (remove != null) {
         for(String item : remove) {
            this.addToRemove(item);
         }
      }

      return this;
   }

   public boolean hasRemove() {
      return this.remove != null && !this.remove.isEmpty();
   }

   public HTTPHeaderFilterFluent addToSet(int index, HTTPHeader item) {
      if (this.set == null) {
         this.set = new ArrayList();
      }

      HTTPHeaderBuilder builder = new HTTPHeaderBuilder(item);
      if (index >= 0 && index < this.set.size()) {
         this._visitables.get("set").add(index, builder);
         this.set.add(index, builder);
      } else {
         this._visitables.get("set").add(builder);
         this.set.add(builder);
      }

      return this;
   }

   public HTTPHeaderFilterFluent setToSet(int index, HTTPHeader item) {
      if (this.set == null) {
         this.set = new ArrayList();
      }

      HTTPHeaderBuilder builder = new HTTPHeaderBuilder(item);
      if (index >= 0 && index < this.set.size()) {
         this._visitables.get("set").set(index, builder);
         this.set.set(index, builder);
      } else {
         this._visitables.get("set").add(builder);
         this.set.add(builder);
      }

      return this;
   }

   public HTTPHeaderFilterFluent addToSet(HTTPHeader... items) {
      if (this.set == null) {
         this.set = new ArrayList();
      }

      for(HTTPHeader item : items) {
         HTTPHeaderBuilder builder = new HTTPHeaderBuilder(item);
         this._visitables.get("set").add(builder);
         this.set.add(builder);
      }

      return this;
   }

   public HTTPHeaderFilterFluent addAllToSet(Collection items) {
      if (this.set == null) {
         this.set = new ArrayList();
      }

      for(HTTPHeader item : items) {
         HTTPHeaderBuilder builder = new HTTPHeaderBuilder(item);
         this._visitables.get("set").add(builder);
         this.set.add(builder);
      }

      return this;
   }

   public HTTPHeaderFilterFluent removeFromSet(HTTPHeader... items) {
      if (this.set == null) {
         return this;
      } else {
         for(HTTPHeader item : items) {
            HTTPHeaderBuilder builder = new HTTPHeaderBuilder(item);
            this._visitables.get("set").remove(builder);
            this.set.remove(builder);
         }

         return this;
      }
   }

   public HTTPHeaderFilterFluent removeAllFromSet(Collection items) {
      if (this.set == null) {
         return this;
      } else {
         for(HTTPHeader item : items) {
            HTTPHeaderBuilder builder = new HTTPHeaderBuilder(item);
            this._visitables.get("set").remove(builder);
            this.set.remove(builder);
         }

         return this;
      }
   }

   public HTTPHeaderFilterFluent removeMatchingFromSet(Predicate predicate) {
      if (this.set == null) {
         return this;
      } else {
         Iterator<HTTPHeaderBuilder> each = this.set.iterator();
         List visitables = this._visitables.get("set");

         while(each.hasNext()) {
            HTTPHeaderBuilder builder = (HTTPHeaderBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildSet() {
      return this.set != null ? build(this.set) : null;
   }

   public HTTPHeader buildSet(int index) {
      return ((HTTPHeaderBuilder)this.set.get(index)).build();
   }

   public HTTPHeader buildFirstSet() {
      return ((HTTPHeaderBuilder)this.set.get(0)).build();
   }

   public HTTPHeader buildLastSet() {
      return ((HTTPHeaderBuilder)this.set.get(this.set.size() - 1)).build();
   }

   public HTTPHeader buildMatchingSet(Predicate predicate) {
      for(HTTPHeaderBuilder item : this.set) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingSet(Predicate predicate) {
      for(HTTPHeaderBuilder item : this.set) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public HTTPHeaderFilterFluent withSet(List set) {
      if (this.set != null) {
         this._visitables.get("set").clear();
      }

      if (set != null) {
         this.set = new ArrayList();

         for(HTTPHeader item : set) {
            this.addToSet(item);
         }
      } else {
         this.set = null;
      }

      return this;
   }

   public HTTPHeaderFilterFluent withSet(HTTPHeader... set) {
      if (this.set != null) {
         this.set.clear();
         this._visitables.remove("set");
      }

      if (set != null) {
         for(HTTPHeader item : set) {
            this.addToSet(item);
         }
      }

      return this;
   }

   public boolean hasSet() {
      return this.set != null && !this.set.isEmpty();
   }

   public HTTPHeaderFilterFluent addNewSet(String name, String value) {
      return this.addToSet(new HTTPHeader(name, value));
   }

   public SetNested addNewSet() {
      return new SetNested(-1, (HTTPHeader)null);
   }

   public SetNested addNewSetLike(HTTPHeader item) {
      return new SetNested(-1, item);
   }

   public SetNested setNewSetLike(int index, HTTPHeader item) {
      return new SetNested(index, item);
   }

   public SetNested editSet(int index) {
      if (this.set.size() <= index) {
         throw new RuntimeException("Can't edit set. Index exceeds size.");
      } else {
         return this.setNewSetLike(index, this.buildSet(index));
      }
   }

   public SetNested editFirstSet() {
      if (this.set.size() == 0) {
         throw new RuntimeException("Can't edit first set. The list is empty.");
      } else {
         return this.setNewSetLike(0, this.buildSet(0));
      }
   }

   public SetNested editLastSet() {
      int index = this.set.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last set. The list is empty.");
      } else {
         return this.setNewSetLike(index, this.buildSet(index));
      }
   }

   public SetNested editMatchingSet(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.set.size(); ++i) {
         if (predicate.test((HTTPHeaderBuilder)this.set.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching set. No match found.");
      } else {
         return this.setNewSetLike(index, this.buildSet(index));
      }
   }

   public HTTPHeaderFilterFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public HTTPHeaderFilterFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public HTTPHeaderFilterFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public HTTPHeaderFilterFluent removeFromAdditionalProperties(Map map) {
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

   public HTTPHeaderFilterFluent withAdditionalProperties(Map additionalProperties) {
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
            HTTPHeaderFilterFluent that = (HTTPHeaderFilterFluent)o;
            if (!Objects.equals(this.add, that.add)) {
               return false;
            } else if (!Objects.equals(this.remove, that.remove)) {
               return false;
            } else if (!Objects.equals(this.set, that.set)) {
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
      return Objects.hash(new Object[]{this.add, this.remove, this.set, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.add != null && !this.add.isEmpty()) {
         sb.append("add:");
         sb.append(this.add + ",");
      }

      if (this.remove != null && !this.remove.isEmpty()) {
         sb.append("remove:");
         sb.append(this.remove + ",");
      }

      if (this.set != null && !this.set.isEmpty()) {
         sb.append("set:");
         sb.append(this.set + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class AddNested extends HTTPHeaderFluent implements Nested {
      HTTPHeaderBuilder builder;
      int index;

      AddNested(int index, HTTPHeader item) {
         this.index = index;
         this.builder = new HTTPHeaderBuilder(this, item);
      }

      public Object and() {
         return HTTPHeaderFilterFluent.this.setToAdd(this.index, this.builder.build());
      }

      public Object endAdd() {
         return this.and();
      }
   }

   public class SetNested extends HTTPHeaderFluent implements Nested {
      HTTPHeaderBuilder builder;
      int index;

      SetNested(int index, HTTPHeader item) {
         this.index = index;
         this.builder = new HTTPHeaderBuilder(this, item);
      }

      public Object and() {
         return HTTPHeaderFilterFluent.this.setToSet(this.index, this.builder.build());
      }

      public Object endSet() {
         return this.and();
      }
   }
}
