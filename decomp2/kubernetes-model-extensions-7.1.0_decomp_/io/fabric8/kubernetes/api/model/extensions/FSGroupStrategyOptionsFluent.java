package io.fabric8.kubernetes.api.model.extensions;

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

public class FSGroupStrategyOptionsFluent extends BaseFluent {
   private ArrayList ranges = new ArrayList();
   private String rule;
   private Map additionalProperties;

   public FSGroupStrategyOptionsFluent() {
   }

   public FSGroupStrategyOptionsFluent(FSGroupStrategyOptions instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(FSGroupStrategyOptions instance) {
      instance = instance != null ? instance : new FSGroupStrategyOptions();
      if (instance != null) {
         this.withRanges(instance.getRanges());
         this.withRule(instance.getRule());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public FSGroupStrategyOptionsFluent addToRanges(int index, IDRange item) {
      if (this.ranges == null) {
         this.ranges = new ArrayList();
      }

      IDRangeBuilder builder = new IDRangeBuilder(item);
      if (index >= 0 && index < this.ranges.size()) {
         this._visitables.get("ranges").add(index, builder);
         this.ranges.add(index, builder);
      } else {
         this._visitables.get("ranges").add(builder);
         this.ranges.add(builder);
      }

      return this;
   }

   public FSGroupStrategyOptionsFluent setToRanges(int index, IDRange item) {
      if (this.ranges == null) {
         this.ranges = new ArrayList();
      }

      IDRangeBuilder builder = new IDRangeBuilder(item);
      if (index >= 0 && index < this.ranges.size()) {
         this._visitables.get("ranges").set(index, builder);
         this.ranges.set(index, builder);
      } else {
         this._visitables.get("ranges").add(builder);
         this.ranges.add(builder);
      }

      return this;
   }

   public FSGroupStrategyOptionsFluent addToRanges(IDRange... items) {
      if (this.ranges == null) {
         this.ranges = new ArrayList();
      }

      for(IDRange item : items) {
         IDRangeBuilder builder = new IDRangeBuilder(item);
         this._visitables.get("ranges").add(builder);
         this.ranges.add(builder);
      }

      return this;
   }

   public FSGroupStrategyOptionsFluent addAllToRanges(Collection items) {
      if (this.ranges == null) {
         this.ranges = new ArrayList();
      }

      for(IDRange item : items) {
         IDRangeBuilder builder = new IDRangeBuilder(item);
         this._visitables.get("ranges").add(builder);
         this.ranges.add(builder);
      }

      return this;
   }

   public FSGroupStrategyOptionsFluent removeFromRanges(IDRange... items) {
      if (this.ranges == null) {
         return this;
      } else {
         for(IDRange item : items) {
            IDRangeBuilder builder = new IDRangeBuilder(item);
            this._visitables.get("ranges").remove(builder);
            this.ranges.remove(builder);
         }

         return this;
      }
   }

   public FSGroupStrategyOptionsFluent removeAllFromRanges(Collection items) {
      if (this.ranges == null) {
         return this;
      } else {
         for(IDRange item : items) {
            IDRangeBuilder builder = new IDRangeBuilder(item);
            this._visitables.get("ranges").remove(builder);
            this.ranges.remove(builder);
         }

         return this;
      }
   }

   public FSGroupStrategyOptionsFluent removeMatchingFromRanges(Predicate predicate) {
      if (this.ranges == null) {
         return this;
      } else {
         Iterator<IDRangeBuilder> each = this.ranges.iterator();
         List visitables = this._visitables.get("ranges");

         while(each.hasNext()) {
            IDRangeBuilder builder = (IDRangeBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildRanges() {
      return this.ranges != null ? build(this.ranges) : null;
   }

   public IDRange buildRange(int index) {
      return ((IDRangeBuilder)this.ranges.get(index)).build();
   }

   public IDRange buildFirstRange() {
      return ((IDRangeBuilder)this.ranges.get(0)).build();
   }

   public IDRange buildLastRange() {
      return ((IDRangeBuilder)this.ranges.get(this.ranges.size() - 1)).build();
   }

   public IDRange buildMatchingRange(Predicate predicate) {
      for(IDRangeBuilder item : this.ranges) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingRange(Predicate predicate) {
      for(IDRangeBuilder item : this.ranges) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public FSGroupStrategyOptionsFluent withRanges(List ranges) {
      if (this.ranges != null) {
         this._visitables.get("ranges").clear();
      }

      if (ranges != null) {
         this.ranges = new ArrayList();

         for(IDRange item : ranges) {
            this.addToRanges(item);
         }
      } else {
         this.ranges = null;
      }

      return this;
   }

   public FSGroupStrategyOptionsFluent withRanges(IDRange... ranges) {
      if (this.ranges != null) {
         this.ranges.clear();
         this._visitables.remove("ranges");
      }

      if (ranges != null) {
         for(IDRange item : ranges) {
            this.addToRanges(item);
         }
      }

      return this;
   }

   public boolean hasRanges() {
      return this.ranges != null && !this.ranges.isEmpty();
   }

   public FSGroupStrategyOptionsFluent addNewRange(Long max, Long min) {
      return this.addToRanges(new IDRange(max, min));
   }

   public RangesNested addNewRange() {
      return new RangesNested(-1, (IDRange)null);
   }

   public RangesNested addNewRangeLike(IDRange item) {
      return new RangesNested(-1, item);
   }

   public RangesNested setNewRangeLike(int index, IDRange item) {
      return new RangesNested(index, item);
   }

   public RangesNested editRange(int index) {
      if (this.ranges.size() <= index) {
         throw new RuntimeException("Can't edit ranges. Index exceeds size.");
      } else {
         return this.setNewRangeLike(index, this.buildRange(index));
      }
   }

   public RangesNested editFirstRange() {
      if (this.ranges.size() == 0) {
         throw new RuntimeException("Can't edit first ranges. The list is empty.");
      } else {
         return this.setNewRangeLike(0, this.buildRange(0));
      }
   }

   public RangesNested editLastRange() {
      int index = this.ranges.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last ranges. The list is empty.");
      } else {
         return this.setNewRangeLike(index, this.buildRange(index));
      }
   }

   public RangesNested editMatchingRange(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.ranges.size(); ++i) {
         if (predicate.test((IDRangeBuilder)this.ranges.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching ranges. No match found.");
      } else {
         return this.setNewRangeLike(index, this.buildRange(index));
      }
   }

   public String getRule() {
      return this.rule;
   }

   public FSGroupStrategyOptionsFluent withRule(String rule) {
      this.rule = rule;
      return this;
   }

   public boolean hasRule() {
      return this.rule != null;
   }

   public FSGroupStrategyOptionsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public FSGroupStrategyOptionsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public FSGroupStrategyOptionsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public FSGroupStrategyOptionsFluent removeFromAdditionalProperties(Map map) {
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

   public FSGroupStrategyOptionsFluent withAdditionalProperties(Map additionalProperties) {
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
            FSGroupStrategyOptionsFluent that = (FSGroupStrategyOptionsFluent)o;
            if (!Objects.equals(this.ranges, that.ranges)) {
               return false;
            } else if (!Objects.equals(this.rule, that.rule)) {
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
      return Objects.hash(new Object[]{this.ranges, this.rule, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.ranges != null && !this.ranges.isEmpty()) {
         sb.append("ranges:");
         sb.append(this.ranges + ",");
      }

      if (this.rule != null) {
         sb.append("rule:");
         sb.append(this.rule + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class RangesNested extends IDRangeFluent implements Nested {
      IDRangeBuilder builder;
      int index;

      RangesNested(int index, IDRange item) {
         this.index = index;
         this.builder = new IDRangeBuilder(this, item);
      }

      public Object and() {
         return FSGroupStrategyOptionsFluent.this.setToRanges(this.index, this.builder.build());
      }

      public Object endRange() {
         return this.and();
      }
   }
}
