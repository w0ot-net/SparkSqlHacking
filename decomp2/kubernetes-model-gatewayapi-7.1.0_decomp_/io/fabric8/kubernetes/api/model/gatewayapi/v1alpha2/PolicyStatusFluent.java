package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

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

public class PolicyStatusFluent extends BaseFluent {
   private ArrayList ancestors = new ArrayList();
   private Map additionalProperties;

   public PolicyStatusFluent() {
   }

   public PolicyStatusFluent(PolicyStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PolicyStatus instance) {
      instance = instance != null ? instance : new PolicyStatus();
      if (instance != null) {
         this.withAncestors(instance.getAncestors());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public PolicyStatusFluent addToAncestors(int index, PolicyAncestorStatus item) {
      if (this.ancestors == null) {
         this.ancestors = new ArrayList();
      }

      PolicyAncestorStatusBuilder builder = new PolicyAncestorStatusBuilder(item);
      if (index >= 0 && index < this.ancestors.size()) {
         this._visitables.get("ancestors").add(index, builder);
         this.ancestors.add(index, builder);
      } else {
         this._visitables.get("ancestors").add(builder);
         this.ancestors.add(builder);
      }

      return this;
   }

   public PolicyStatusFluent setToAncestors(int index, PolicyAncestorStatus item) {
      if (this.ancestors == null) {
         this.ancestors = new ArrayList();
      }

      PolicyAncestorStatusBuilder builder = new PolicyAncestorStatusBuilder(item);
      if (index >= 0 && index < this.ancestors.size()) {
         this._visitables.get("ancestors").set(index, builder);
         this.ancestors.set(index, builder);
      } else {
         this._visitables.get("ancestors").add(builder);
         this.ancestors.add(builder);
      }

      return this;
   }

   public PolicyStatusFluent addToAncestors(PolicyAncestorStatus... items) {
      if (this.ancestors == null) {
         this.ancestors = new ArrayList();
      }

      for(PolicyAncestorStatus item : items) {
         PolicyAncestorStatusBuilder builder = new PolicyAncestorStatusBuilder(item);
         this._visitables.get("ancestors").add(builder);
         this.ancestors.add(builder);
      }

      return this;
   }

   public PolicyStatusFluent addAllToAncestors(Collection items) {
      if (this.ancestors == null) {
         this.ancestors = new ArrayList();
      }

      for(PolicyAncestorStatus item : items) {
         PolicyAncestorStatusBuilder builder = new PolicyAncestorStatusBuilder(item);
         this._visitables.get("ancestors").add(builder);
         this.ancestors.add(builder);
      }

      return this;
   }

   public PolicyStatusFluent removeFromAncestors(PolicyAncestorStatus... items) {
      if (this.ancestors == null) {
         return this;
      } else {
         for(PolicyAncestorStatus item : items) {
            PolicyAncestorStatusBuilder builder = new PolicyAncestorStatusBuilder(item);
            this._visitables.get("ancestors").remove(builder);
            this.ancestors.remove(builder);
         }

         return this;
      }
   }

   public PolicyStatusFluent removeAllFromAncestors(Collection items) {
      if (this.ancestors == null) {
         return this;
      } else {
         for(PolicyAncestorStatus item : items) {
            PolicyAncestorStatusBuilder builder = new PolicyAncestorStatusBuilder(item);
            this._visitables.get("ancestors").remove(builder);
            this.ancestors.remove(builder);
         }

         return this;
      }
   }

   public PolicyStatusFluent removeMatchingFromAncestors(Predicate predicate) {
      if (this.ancestors == null) {
         return this;
      } else {
         Iterator<PolicyAncestorStatusBuilder> each = this.ancestors.iterator();
         List visitables = this._visitables.get("ancestors");

         while(each.hasNext()) {
            PolicyAncestorStatusBuilder builder = (PolicyAncestorStatusBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildAncestors() {
      return this.ancestors != null ? build(this.ancestors) : null;
   }

   public PolicyAncestorStatus buildAncestor(int index) {
      return ((PolicyAncestorStatusBuilder)this.ancestors.get(index)).build();
   }

   public PolicyAncestorStatus buildFirstAncestor() {
      return ((PolicyAncestorStatusBuilder)this.ancestors.get(0)).build();
   }

   public PolicyAncestorStatus buildLastAncestor() {
      return ((PolicyAncestorStatusBuilder)this.ancestors.get(this.ancestors.size() - 1)).build();
   }

   public PolicyAncestorStatus buildMatchingAncestor(Predicate predicate) {
      for(PolicyAncestorStatusBuilder item : this.ancestors) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingAncestor(Predicate predicate) {
      for(PolicyAncestorStatusBuilder item : this.ancestors) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PolicyStatusFluent withAncestors(List ancestors) {
      if (this.ancestors != null) {
         this._visitables.get("ancestors").clear();
      }

      if (ancestors != null) {
         this.ancestors = new ArrayList();

         for(PolicyAncestorStatus item : ancestors) {
            this.addToAncestors(item);
         }
      } else {
         this.ancestors = null;
      }

      return this;
   }

   public PolicyStatusFluent withAncestors(PolicyAncestorStatus... ancestors) {
      if (this.ancestors != null) {
         this.ancestors.clear();
         this._visitables.remove("ancestors");
      }

      if (ancestors != null) {
         for(PolicyAncestorStatus item : ancestors) {
            this.addToAncestors(item);
         }
      }

      return this;
   }

   public boolean hasAncestors() {
      return this.ancestors != null && !this.ancestors.isEmpty();
   }

   public AncestorsNested addNewAncestor() {
      return new AncestorsNested(-1, (PolicyAncestorStatus)null);
   }

   public AncestorsNested addNewAncestorLike(PolicyAncestorStatus item) {
      return new AncestorsNested(-1, item);
   }

   public AncestorsNested setNewAncestorLike(int index, PolicyAncestorStatus item) {
      return new AncestorsNested(index, item);
   }

   public AncestorsNested editAncestor(int index) {
      if (this.ancestors.size() <= index) {
         throw new RuntimeException("Can't edit ancestors. Index exceeds size.");
      } else {
         return this.setNewAncestorLike(index, this.buildAncestor(index));
      }
   }

   public AncestorsNested editFirstAncestor() {
      if (this.ancestors.size() == 0) {
         throw new RuntimeException("Can't edit first ancestors. The list is empty.");
      } else {
         return this.setNewAncestorLike(0, this.buildAncestor(0));
      }
   }

   public AncestorsNested editLastAncestor() {
      int index = this.ancestors.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last ancestors. The list is empty.");
      } else {
         return this.setNewAncestorLike(index, this.buildAncestor(index));
      }
   }

   public AncestorsNested editMatchingAncestor(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.ancestors.size(); ++i) {
         if (predicate.test((PolicyAncestorStatusBuilder)this.ancestors.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching ancestors. No match found.");
      } else {
         return this.setNewAncestorLike(index, this.buildAncestor(index));
      }
   }

   public PolicyStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PolicyStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PolicyStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PolicyStatusFluent removeFromAdditionalProperties(Map map) {
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

   public PolicyStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            PolicyStatusFluent that = (PolicyStatusFluent)o;
            if (!Objects.equals(this.ancestors, that.ancestors)) {
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
      return Objects.hash(new Object[]{this.ancestors, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.ancestors != null && !this.ancestors.isEmpty()) {
         sb.append("ancestors:");
         sb.append(this.ancestors + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class AncestorsNested extends PolicyAncestorStatusFluent implements Nested {
      PolicyAncestorStatusBuilder builder;
      int index;

      AncestorsNested(int index, PolicyAncestorStatus item) {
         this.index = index;
         this.builder = new PolicyAncestorStatusBuilder(this, item);
      }

      public Object and() {
         return PolicyStatusFluent.this.setToAncestors(this.index, this.builder.build());
      }

      public Object endAncestor() {
         return this.and();
      }
   }
}
