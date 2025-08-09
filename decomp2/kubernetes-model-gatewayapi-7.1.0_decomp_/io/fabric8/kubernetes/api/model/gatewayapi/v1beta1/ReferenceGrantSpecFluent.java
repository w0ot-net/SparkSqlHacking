package io.fabric8.kubernetes.api.model.gatewayapi.v1beta1;

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

public class ReferenceGrantSpecFluent extends BaseFluent {
   private ArrayList from = new ArrayList();
   private ArrayList to = new ArrayList();
   private Map additionalProperties;

   public ReferenceGrantSpecFluent() {
   }

   public ReferenceGrantSpecFluent(ReferenceGrantSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ReferenceGrantSpec instance) {
      instance = instance != null ? instance : new ReferenceGrantSpec();
      if (instance != null) {
         this.withFrom(instance.getFrom());
         this.withTo(instance.getTo());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ReferenceGrantSpecFluent addToFrom(int index, ReferenceGrantFrom item) {
      if (this.from == null) {
         this.from = new ArrayList();
      }

      ReferenceGrantFromBuilder builder = new ReferenceGrantFromBuilder(item);
      if (index >= 0 && index < this.from.size()) {
         this._visitables.get("from").add(index, builder);
         this.from.add(index, builder);
      } else {
         this._visitables.get("from").add(builder);
         this.from.add(builder);
      }

      return this;
   }

   public ReferenceGrantSpecFluent setToFrom(int index, ReferenceGrantFrom item) {
      if (this.from == null) {
         this.from = new ArrayList();
      }

      ReferenceGrantFromBuilder builder = new ReferenceGrantFromBuilder(item);
      if (index >= 0 && index < this.from.size()) {
         this._visitables.get("from").set(index, builder);
         this.from.set(index, builder);
      } else {
         this._visitables.get("from").add(builder);
         this.from.add(builder);
      }

      return this;
   }

   public ReferenceGrantSpecFluent addToFrom(ReferenceGrantFrom... items) {
      if (this.from == null) {
         this.from = new ArrayList();
      }

      for(ReferenceGrantFrom item : items) {
         ReferenceGrantFromBuilder builder = new ReferenceGrantFromBuilder(item);
         this._visitables.get("from").add(builder);
         this.from.add(builder);
      }

      return this;
   }

   public ReferenceGrantSpecFluent addAllToFrom(Collection items) {
      if (this.from == null) {
         this.from = new ArrayList();
      }

      for(ReferenceGrantFrom item : items) {
         ReferenceGrantFromBuilder builder = new ReferenceGrantFromBuilder(item);
         this._visitables.get("from").add(builder);
         this.from.add(builder);
      }

      return this;
   }

   public ReferenceGrantSpecFluent removeFromFrom(ReferenceGrantFrom... items) {
      if (this.from == null) {
         return this;
      } else {
         for(ReferenceGrantFrom item : items) {
            ReferenceGrantFromBuilder builder = new ReferenceGrantFromBuilder(item);
            this._visitables.get("from").remove(builder);
            this.from.remove(builder);
         }

         return this;
      }
   }

   public ReferenceGrantSpecFluent removeAllFromFrom(Collection items) {
      if (this.from == null) {
         return this;
      } else {
         for(ReferenceGrantFrom item : items) {
            ReferenceGrantFromBuilder builder = new ReferenceGrantFromBuilder(item);
            this._visitables.get("from").remove(builder);
            this.from.remove(builder);
         }

         return this;
      }
   }

   public ReferenceGrantSpecFluent removeMatchingFromFrom(Predicate predicate) {
      if (this.from == null) {
         return this;
      } else {
         Iterator<ReferenceGrantFromBuilder> each = this.from.iterator();
         List visitables = this._visitables.get("from");

         while(each.hasNext()) {
            ReferenceGrantFromBuilder builder = (ReferenceGrantFromBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildFrom() {
      return this.from != null ? build(this.from) : null;
   }

   public ReferenceGrantFrom buildFrom(int index) {
      return ((ReferenceGrantFromBuilder)this.from.get(index)).build();
   }

   public ReferenceGrantFrom buildFirstFrom() {
      return ((ReferenceGrantFromBuilder)this.from.get(0)).build();
   }

   public ReferenceGrantFrom buildLastFrom() {
      return ((ReferenceGrantFromBuilder)this.from.get(this.from.size() - 1)).build();
   }

   public ReferenceGrantFrom buildMatchingFrom(Predicate predicate) {
      for(ReferenceGrantFromBuilder item : this.from) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingFrom(Predicate predicate) {
      for(ReferenceGrantFromBuilder item : this.from) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ReferenceGrantSpecFluent withFrom(List from) {
      if (this.from != null) {
         this._visitables.get("from").clear();
      }

      if (from != null) {
         this.from = new ArrayList();

         for(ReferenceGrantFrom item : from) {
            this.addToFrom(item);
         }
      } else {
         this.from = null;
      }

      return this;
   }

   public ReferenceGrantSpecFluent withFrom(ReferenceGrantFrom... from) {
      if (this.from != null) {
         this.from.clear();
         this._visitables.remove("from");
      }

      if (from != null) {
         for(ReferenceGrantFrom item : from) {
            this.addToFrom(item);
         }
      }

      return this;
   }

   public boolean hasFrom() {
      return this.from != null && !this.from.isEmpty();
   }

   public ReferenceGrantSpecFluent addNewFrom(String group, String kind, String namespace) {
      return this.addToFrom(new ReferenceGrantFrom(group, kind, namespace));
   }

   public FromNested addNewFrom() {
      return new FromNested(-1, (ReferenceGrantFrom)null);
   }

   public FromNested addNewFromLike(ReferenceGrantFrom item) {
      return new FromNested(-1, item);
   }

   public FromNested setNewFromLike(int index, ReferenceGrantFrom item) {
      return new FromNested(index, item);
   }

   public FromNested editFrom(int index) {
      if (this.from.size() <= index) {
         throw new RuntimeException("Can't edit from. Index exceeds size.");
      } else {
         return this.setNewFromLike(index, this.buildFrom(index));
      }
   }

   public FromNested editFirstFrom() {
      if (this.from.size() == 0) {
         throw new RuntimeException("Can't edit first from. The list is empty.");
      } else {
         return this.setNewFromLike(0, this.buildFrom(0));
      }
   }

   public FromNested editLastFrom() {
      int index = this.from.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last from. The list is empty.");
      } else {
         return this.setNewFromLike(index, this.buildFrom(index));
      }
   }

   public FromNested editMatchingFrom(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.from.size(); ++i) {
         if (predicate.test((ReferenceGrantFromBuilder)this.from.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching from. No match found.");
      } else {
         return this.setNewFromLike(index, this.buildFrom(index));
      }
   }

   public ReferenceGrantSpecFluent addToTo(int index, ReferenceGrantTo item) {
      if (this.to == null) {
         this.to = new ArrayList();
      }

      ReferenceGrantToBuilder builder = new ReferenceGrantToBuilder(item);
      if (index >= 0 && index < this.to.size()) {
         this._visitables.get("to").add(index, builder);
         this.to.add(index, builder);
      } else {
         this._visitables.get("to").add(builder);
         this.to.add(builder);
      }

      return this;
   }

   public ReferenceGrantSpecFluent setToTo(int index, ReferenceGrantTo item) {
      if (this.to == null) {
         this.to = new ArrayList();
      }

      ReferenceGrantToBuilder builder = new ReferenceGrantToBuilder(item);
      if (index >= 0 && index < this.to.size()) {
         this._visitables.get("to").set(index, builder);
         this.to.set(index, builder);
      } else {
         this._visitables.get("to").add(builder);
         this.to.add(builder);
      }

      return this;
   }

   public ReferenceGrantSpecFluent addToTo(ReferenceGrantTo... items) {
      if (this.to == null) {
         this.to = new ArrayList();
      }

      for(ReferenceGrantTo item : items) {
         ReferenceGrantToBuilder builder = new ReferenceGrantToBuilder(item);
         this._visitables.get("to").add(builder);
         this.to.add(builder);
      }

      return this;
   }

   public ReferenceGrantSpecFluent addAllToTo(Collection items) {
      if (this.to == null) {
         this.to = new ArrayList();
      }

      for(ReferenceGrantTo item : items) {
         ReferenceGrantToBuilder builder = new ReferenceGrantToBuilder(item);
         this._visitables.get("to").add(builder);
         this.to.add(builder);
      }

      return this;
   }

   public ReferenceGrantSpecFluent removeFromTo(ReferenceGrantTo... items) {
      if (this.to == null) {
         return this;
      } else {
         for(ReferenceGrantTo item : items) {
            ReferenceGrantToBuilder builder = new ReferenceGrantToBuilder(item);
            this._visitables.get("to").remove(builder);
            this.to.remove(builder);
         }

         return this;
      }
   }

   public ReferenceGrantSpecFluent removeAllFromTo(Collection items) {
      if (this.to == null) {
         return this;
      } else {
         for(ReferenceGrantTo item : items) {
            ReferenceGrantToBuilder builder = new ReferenceGrantToBuilder(item);
            this._visitables.get("to").remove(builder);
            this.to.remove(builder);
         }

         return this;
      }
   }

   public ReferenceGrantSpecFluent removeMatchingFromTo(Predicate predicate) {
      if (this.to == null) {
         return this;
      } else {
         Iterator<ReferenceGrantToBuilder> each = this.to.iterator();
         List visitables = this._visitables.get("to");

         while(each.hasNext()) {
            ReferenceGrantToBuilder builder = (ReferenceGrantToBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildTo() {
      return this.to != null ? build(this.to) : null;
   }

   public ReferenceGrantTo buildTo(int index) {
      return ((ReferenceGrantToBuilder)this.to.get(index)).build();
   }

   public ReferenceGrantTo buildFirstTo() {
      return ((ReferenceGrantToBuilder)this.to.get(0)).build();
   }

   public ReferenceGrantTo buildLastTo() {
      return ((ReferenceGrantToBuilder)this.to.get(this.to.size() - 1)).build();
   }

   public ReferenceGrantTo buildMatchingTo(Predicate predicate) {
      for(ReferenceGrantToBuilder item : this.to) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingTo(Predicate predicate) {
      for(ReferenceGrantToBuilder item : this.to) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ReferenceGrantSpecFluent withTo(List to) {
      if (this.to != null) {
         this._visitables.get("to").clear();
      }

      if (to != null) {
         this.to = new ArrayList();

         for(ReferenceGrantTo item : to) {
            this.addToTo(item);
         }
      } else {
         this.to = null;
      }

      return this;
   }

   public ReferenceGrantSpecFluent withTo(ReferenceGrantTo... to) {
      if (this.to != null) {
         this.to.clear();
         this._visitables.remove("to");
      }

      if (to != null) {
         for(ReferenceGrantTo item : to) {
            this.addToTo(item);
         }
      }

      return this;
   }

   public boolean hasTo() {
      return this.to != null && !this.to.isEmpty();
   }

   public ReferenceGrantSpecFluent addNewTo(String group, String kind, String name) {
      return this.addToTo(new ReferenceGrantTo(group, kind, name));
   }

   public ToNested addNewTo() {
      return new ToNested(-1, (ReferenceGrantTo)null);
   }

   public ToNested addNewToLike(ReferenceGrantTo item) {
      return new ToNested(-1, item);
   }

   public ToNested setNewToLike(int index, ReferenceGrantTo item) {
      return new ToNested(index, item);
   }

   public ToNested editTo(int index) {
      if (this.to.size() <= index) {
         throw new RuntimeException("Can't edit to. Index exceeds size.");
      } else {
         return this.setNewToLike(index, this.buildTo(index));
      }
   }

   public ToNested editFirstTo() {
      if (this.to.size() == 0) {
         throw new RuntimeException("Can't edit first to. The list is empty.");
      } else {
         return this.setNewToLike(0, this.buildTo(0));
      }
   }

   public ToNested editLastTo() {
      int index = this.to.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last to. The list is empty.");
      } else {
         return this.setNewToLike(index, this.buildTo(index));
      }
   }

   public ToNested editMatchingTo(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.to.size(); ++i) {
         if (predicate.test((ReferenceGrantToBuilder)this.to.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching to. No match found.");
      } else {
         return this.setNewToLike(index, this.buildTo(index));
      }
   }

   public ReferenceGrantSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ReferenceGrantSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ReferenceGrantSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ReferenceGrantSpecFluent removeFromAdditionalProperties(Map map) {
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

   public ReferenceGrantSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            ReferenceGrantSpecFluent that = (ReferenceGrantSpecFluent)o;
            if (!Objects.equals(this.from, that.from)) {
               return false;
            } else if (!Objects.equals(this.to, that.to)) {
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
      return Objects.hash(new Object[]{this.from, this.to, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.from != null && !this.from.isEmpty()) {
         sb.append("from:");
         sb.append(this.from + ",");
      }

      if (this.to != null && !this.to.isEmpty()) {
         sb.append("to:");
         sb.append(this.to + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class FromNested extends ReferenceGrantFromFluent implements Nested {
      ReferenceGrantFromBuilder builder;
      int index;

      FromNested(int index, ReferenceGrantFrom item) {
         this.index = index;
         this.builder = new ReferenceGrantFromBuilder(this, item);
      }

      public Object and() {
         return ReferenceGrantSpecFluent.this.setToFrom(this.index, this.builder.build());
      }

      public Object endFrom() {
         return this.and();
      }
   }

   public class ToNested extends ReferenceGrantToFluent implements Nested {
      ReferenceGrantToBuilder builder;
      int index;

      ToNested(int index, ReferenceGrantTo item) {
         this.index = index;
         this.builder = new ReferenceGrantToBuilder(this, item);
      }

      public Object and() {
         return ReferenceGrantSpecFluent.this.setToTo(this.index, this.builder.build());
      }

      public Object endTo() {
         return this.and();
      }
   }
}
