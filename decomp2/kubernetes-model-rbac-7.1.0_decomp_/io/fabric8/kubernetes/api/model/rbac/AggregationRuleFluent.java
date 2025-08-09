package io.fabric8.kubernetes.api.model.rbac;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.LabelSelector;
import io.fabric8.kubernetes.api.model.LabelSelectorBuilder;
import io.fabric8.kubernetes.api.model.LabelSelectorFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class AggregationRuleFluent extends BaseFluent {
   private ArrayList clusterRoleSelectors = new ArrayList();
   private Map additionalProperties;

   public AggregationRuleFluent() {
   }

   public AggregationRuleFluent(AggregationRule instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(AggregationRule instance) {
      instance = instance != null ? instance : new AggregationRule();
      if (instance != null) {
         this.withClusterRoleSelectors(instance.getClusterRoleSelectors());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public AggregationRuleFluent addToClusterRoleSelectors(int index, LabelSelector item) {
      if (this.clusterRoleSelectors == null) {
         this.clusterRoleSelectors = new ArrayList();
      }

      LabelSelectorBuilder builder = new LabelSelectorBuilder(item);
      if (index >= 0 && index < this.clusterRoleSelectors.size()) {
         this._visitables.get("clusterRoleSelectors").add(index, builder);
         this.clusterRoleSelectors.add(index, builder);
      } else {
         this._visitables.get("clusterRoleSelectors").add(builder);
         this.clusterRoleSelectors.add(builder);
      }

      return this;
   }

   public AggregationRuleFluent setToClusterRoleSelectors(int index, LabelSelector item) {
      if (this.clusterRoleSelectors == null) {
         this.clusterRoleSelectors = new ArrayList();
      }

      LabelSelectorBuilder builder = new LabelSelectorBuilder(item);
      if (index >= 0 && index < this.clusterRoleSelectors.size()) {
         this._visitables.get("clusterRoleSelectors").set(index, builder);
         this.clusterRoleSelectors.set(index, builder);
      } else {
         this._visitables.get("clusterRoleSelectors").add(builder);
         this.clusterRoleSelectors.add(builder);
      }

      return this;
   }

   public AggregationRuleFluent addToClusterRoleSelectors(LabelSelector... items) {
      if (this.clusterRoleSelectors == null) {
         this.clusterRoleSelectors = new ArrayList();
      }

      for(LabelSelector item : items) {
         LabelSelectorBuilder builder = new LabelSelectorBuilder(item);
         this._visitables.get("clusterRoleSelectors").add(builder);
         this.clusterRoleSelectors.add(builder);
      }

      return this;
   }

   public AggregationRuleFluent addAllToClusterRoleSelectors(Collection items) {
      if (this.clusterRoleSelectors == null) {
         this.clusterRoleSelectors = new ArrayList();
      }

      for(LabelSelector item : items) {
         LabelSelectorBuilder builder = new LabelSelectorBuilder(item);
         this._visitables.get("clusterRoleSelectors").add(builder);
         this.clusterRoleSelectors.add(builder);
      }

      return this;
   }

   public AggregationRuleFluent removeFromClusterRoleSelectors(LabelSelector... items) {
      if (this.clusterRoleSelectors == null) {
         return this;
      } else {
         for(LabelSelector item : items) {
            LabelSelectorBuilder builder = new LabelSelectorBuilder(item);
            this._visitables.get("clusterRoleSelectors").remove(builder);
            this.clusterRoleSelectors.remove(builder);
         }

         return this;
      }
   }

   public AggregationRuleFluent removeAllFromClusterRoleSelectors(Collection items) {
      if (this.clusterRoleSelectors == null) {
         return this;
      } else {
         for(LabelSelector item : items) {
            LabelSelectorBuilder builder = new LabelSelectorBuilder(item);
            this._visitables.get("clusterRoleSelectors").remove(builder);
            this.clusterRoleSelectors.remove(builder);
         }

         return this;
      }
   }

   public AggregationRuleFluent removeMatchingFromClusterRoleSelectors(Predicate predicate) {
      if (this.clusterRoleSelectors == null) {
         return this;
      } else {
         Iterator<LabelSelectorBuilder> each = this.clusterRoleSelectors.iterator();
         List visitables = this._visitables.get("clusterRoleSelectors");

         while(each.hasNext()) {
            LabelSelectorBuilder builder = (LabelSelectorBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildClusterRoleSelectors() {
      return this.clusterRoleSelectors != null ? build(this.clusterRoleSelectors) : null;
   }

   public LabelSelector buildClusterRoleSelector(int index) {
      return ((LabelSelectorBuilder)this.clusterRoleSelectors.get(index)).build();
   }

   public LabelSelector buildFirstClusterRoleSelector() {
      return ((LabelSelectorBuilder)this.clusterRoleSelectors.get(0)).build();
   }

   public LabelSelector buildLastClusterRoleSelector() {
      return ((LabelSelectorBuilder)this.clusterRoleSelectors.get(this.clusterRoleSelectors.size() - 1)).build();
   }

   public LabelSelector buildMatchingClusterRoleSelector(Predicate predicate) {
      for(LabelSelectorBuilder item : this.clusterRoleSelectors) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingClusterRoleSelector(Predicate predicate) {
      for(LabelSelectorBuilder item : this.clusterRoleSelectors) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public AggregationRuleFluent withClusterRoleSelectors(List clusterRoleSelectors) {
      if (this.clusterRoleSelectors != null) {
         this._visitables.get("clusterRoleSelectors").clear();
      }

      if (clusterRoleSelectors != null) {
         this.clusterRoleSelectors = new ArrayList();

         for(LabelSelector item : clusterRoleSelectors) {
            this.addToClusterRoleSelectors(item);
         }
      } else {
         this.clusterRoleSelectors = null;
      }

      return this;
   }

   public AggregationRuleFluent withClusterRoleSelectors(LabelSelector... clusterRoleSelectors) {
      if (this.clusterRoleSelectors != null) {
         this.clusterRoleSelectors.clear();
         this._visitables.remove("clusterRoleSelectors");
      }

      if (clusterRoleSelectors != null) {
         for(LabelSelector item : clusterRoleSelectors) {
            this.addToClusterRoleSelectors(item);
         }
      }

      return this;
   }

   public boolean hasClusterRoleSelectors() {
      return this.clusterRoleSelectors != null && !this.clusterRoleSelectors.isEmpty();
   }

   public ClusterRoleSelectorsNested addNewClusterRoleSelector() {
      return new ClusterRoleSelectorsNested(-1, (LabelSelector)null);
   }

   public ClusterRoleSelectorsNested addNewClusterRoleSelectorLike(LabelSelector item) {
      return new ClusterRoleSelectorsNested(-1, item);
   }

   public ClusterRoleSelectorsNested setNewClusterRoleSelectorLike(int index, LabelSelector item) {
      return new ClusterRoleSelectorsNested(index, item);
   }

   public ClusterRoleSelectorsNested editClusterRoleSelector(int index) {
      if (this.clusterRoleSelectors.size() <= index) {
         throw new RuntimeException("Can't edit clusterRoleSelectors. Index exceeds size.");
      } else {
         return this.setNewClusterRoleSelectorLike(index, this.buildClusterRoleSelector(index));
      }
   }

   public ClusterRoleSelectorsNested editFirstClusterRoleSelector() {
      if (this.clusterRoleSelectors.size() == 0) {
         throw new RuntimeException("Can't edit first clusterRoleSelectors. The list is empty.");
      } else {
         return this.setNewClusterRoleSelectorLike(0, this.buildClusterRoleSelector(0));
      }
   }

   public ClusterRoleSelectorsNested editLastClusterRoleSelector() {
      int index = this.clusterRoleSelectors.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last clusterRoleSelectors. The list is empty.");
      } else {
         return this.setNewClusterRoleSelectorLike(index, this.buildClusterRoleSelector(index));
      }
   }

   public ClusterRoleSelectorsNested editMatchingClusterRoleSelector(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.clusterRoleSelectors.size(); ++i) {
         if (predicate.test((LabelSelectorBuilder)this.clusterRoleSelectors.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching clusterRoleSelectors. No match found.");
      } else {
         return this.setNewClusterRoleSelectorLike(index, this.buildClusterRoleSelector(index));
      }
   }

   public AggregationRuleFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public AggregationRuleFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public AggregationRuleFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public AggregationRuleFluent removeFromAdditionalProperties(Map map) {
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

   public AggregationRuleFluent withAdditionalProperties(Map additionalProperties) {
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
            AggregationRuleFluent that = (AggregationRuleFluent)o;
            if (!Objects.equals(this.clusterRoleSelectors, that.clusterRoleSelectors)) {
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
      return Objects.hash(new Object[]{this.clusterRoleSelectors, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.clusterRoleSelectors != null && !this.clusterRoleSelectors.isEmpty()) {
         sb.append("clusterRoleSelectors:");
         sb.append(this.clusterRoleSelectors + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ClusterRoleSelectorsNested extends LabelSelectorFluent implements Nested {
      LabelSelectorBuilder builder;
      int index;

      ClusterRoleSelectorsNested(int index, LabelSelector item) {
         this.index = index;
         this.builder = new LabelSelectorBuilder(this, item);
      }

      public Object and() {
         return AggregationRuleFluent.this.setToClusterRoleSelectors(this.index, this.builder.build());
      }

      public Object endClusterRoleSelector() {
         return this.and();
      }
   }
}
