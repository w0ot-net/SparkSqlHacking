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

public class LimitRangeSpecFluent extends BaseFluent {
   private ArrayList limits = new ArrayList();
   private Map additionalProperties;

   public LimitRangeSpecFluent() {
   }

   public LimitRangeSpecFluent(LimitRangeSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(LimitRangeSpec instance) {
      instance = instance != null ? instance : new LimitRangeSpec();
      if (instance != null) {
         this.withLimits(instance.getLimits());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public LimitRangeSpecFluent addToLimits(int index, LimitRangeItem item) {
      if (this.limits == null) {
         this.limits = new ArrayList();
      }

      LimitRangeItemBuilder builder = new LimitRangeItemBuilder(item);
      if (index >= 0 && index < this.limits.size()) {
         this._visitables.get("limits").add(index, builder);
         this.limits.add(index, builder);
      } else {
         this._visitables.get("limits").add(builder);
         this.limits.add(builder);
      }

      return this;
   }

   public LimitRangeSpecFluent setToLimits(int index, LimitRangeItem item) {
      if (this.limits == null) {
         this.limits = new ArrayList();
      }

      LimitRangeItemBuilder builder = new LimitRangeItemBuilder(item);
      if (index >= 0 && index < this.limits.size()) {
         this._visitables.get("limits").set(index, builder);
         this.limits.set(index, builder);
      } else {
         this._visitables.get("limits").add(builder);
         this.limits.add(builder);
      }

      return this;
   }

   public LimitRangeSpecFluent addToLimits(LimitRangeItem... items) {
      if (this.limits == null) {
         this.limits = new ArrayList();
      }

      for(LimitRangeItem item : items) {
         LimitRangeItemBuilder builder = new LimitRangeItemBuilder(item);
         this._visitables.get("limits").add(builder);
         this.limits.add(builder);
      }

      return this;
   }

   public LimitRangeSpecFluent addAllToLimits(Collection items) {
      if (this.limits == null) {
         this.limits = new ArrayList();
      }

      for(LimitRangeItem item : items) {
         LimitRangeItemBuilder builder = new LimitRangeItemBuilder(item);
         this._visitables.get("limits").add(builder);
         this.limits.add(builder);
      }

      return this;
   }

   public LimitRangeSpecFluent removeFromLimits(LimitRangeItem... items) {
      if (this.limits == null) {
         return this;
      } else {
         for(LimitRangeItem item : items) {
            LimitRangeItemBuilder builder = new LimitRangeItemBuilder(item);
            this._visitables.get("limits").remove(builder);
            this.limits.remove(builder);
         }

         return this;
      }
   }

   public LimitRangeSpecFluent removeAllFromLimits(Collection items) {
      if (this.limits == null) {
         return this;
      } else {
         for(LimitRangeItem item : items) {
            LimitRangeItemBuilder builder = new LimitRangeItemBuilder(item);
            this._visitables.get("limits").remove(builder);
            this.limits.remove(builder);
         }

         return this;
      }
   }

   public LimitRangeSpecFluent removeMatchingFromLimits(Predicate predicate) {
      if (this.limits == null) {
         return this;
      } else {
         Iterator<LimitRangeItemBuilder> each = this.limits.iterator();
         List visitables = this._visitables.get("limits");

         while(each.hasNext()) {
            LimitRangeItemBuilder builder = (LimitRangeItemBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildLimits() {
      return this.limits != null ? build(this.limits) : null;
   }

   public LimitRangeItem buildLimit(int index) {
      return ((LimitRangeItemBuilder)this.limits.get(index)).build();
   }

   public LimitRangeItem buildFirstLimit() {
      return ((LimitRangeItemBuilder)this.limits.get(0)).build();
   }

   public LimitRangeItem buildLastLimit() {
      return ((LimitRangeItemBuilder)this.limits.get(this.limits.size() - 1)).build();
   }

   public LimitRangeItem buildMatchingLimit(Predicate predicate) {
      for(LimitRangeItemBuilder item : this.limits) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingLimit(Predicate predicate) {
      for(LimitRangeItemBuilder item : this.limits) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public LimitRangeSpecFluent withLimits(List limits) {
      if (this.limits != null) {
         this._visitables.get("limits").clear();
      }

      if (limits != null) {
         this.limits = new ArrayList();

         for(LimitRangeItem item : limits) {
            this.addToLimits(item);
         }
      } else {
         this.limits = null;
      }

      return this;
   }

   public LimitRangeSpecFluent withLimits(LimitRangeItem... limits) {
      if (this.limits != null) {
         this.limits.clear();
         this._visitables.remove("limits");
      }

      if (limits != null) {
         for(LimitRangeItem item : limits) {
            this.addToLimits(item);
         }
      }

      return this;
   }

   public boolean hasLimits() {
      return this.limits != null && !this.limits.isEmpty();
   }

   public LimitsNested addNewLimit() {
      return new LimitsNested(-1, (LimitRangeItem)null);
   }

   public LimitsNested addNewLimitLike(LimitRangeItem item) {
      return new LimitsNested(-1, item);
   }

   public LimitsNested setNewLimitLike(int index, LimitRangeItem item) {
      return new LimitsNested(index, item);
   }

   public LimitsNested editLimit(int index) {
      if (this.limits.size() <= index) {
         throw new RuntimeException("Can't edit limits. Index exceeds size.");
      } else {
         return this.setNewLimitLike(index, this.buildLimit(index));
      }
   }

   public LimitsNested editFirstLimit() {
      if (this.limits.size() == 0) {
         throw new RuntimeException("Can't edit first limits. The list is empty.");
      } else {
         return this.setNewLimitLike(0, this.buildLimit(0));
      }
   }

   public LimitsNested editLastLimit() {
      int index = this.limits.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last limits. The list is empty.");
      } else {
         return this.setNewLimitLike(index, this.buildLimit(index));
      }
   }

   public LimitsNested editMatchingLimit(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.limits.size(); ++i) {
         if (predicate.test((LimitRangeItemBuilder)this.limits.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching limits. No match found.");
      } else {
         return this.setNewLimitLike(index, this.buildLimit(index));
      }
   }

   public LimitRangeSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public LimitRangeSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public LimitRangeSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public LimitRangeSpecFluent removeFromAdditionalProperties(Map map) {
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

   public LimitRangeSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            LimitRangeSpecFluent that = (LimitRangeSpecFluent)o;
            if (!Objects.equals(this.limits, that.limits)) {
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
      return Objects.hash(new Object[]{this.limits, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.limits != null && !this.limits.isEmpty()) {
         sb.append("limits:");
         sb.append(this.limits + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class LimitsNested extends LimitRangeItemFluent implements Nested {
      LimitRangeItemBuilder builder;
      int index;

      LimitsNested(int index, LimitRangeItem item) {
         this.index = index;
         this.builder = new LimitRangeItemBuilder(this, item);
      }

      public Object and() {
         return LimitRangeSpecFluent.this.setToLimits(this.index, this.builder.build());
      }

      public Object endLimit() {
         return this.and();
      }
   }
}
