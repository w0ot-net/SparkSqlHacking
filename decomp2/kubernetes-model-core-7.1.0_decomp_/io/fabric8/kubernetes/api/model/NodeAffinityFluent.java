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
import java.util.Optional;
import java.util.function.Predicate;

public class NodeAffinityFluent extends BaseFluent {
   private ArrayList preferredDuringSchedulingIgnoredDuringExecution = new ArrayList();
   private NodeSelectorBuilder requiredDuringSchedulingIgnoredDuringExecution;
   private Map additionalProperties;

   public NodeAffinityFluent() {
   }

   public NodeAffinityFluent(NodeAffinity instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NodeAffinity instance) {
      instance = instance != null ? instance : new NodeAffinity();
      if (instance != null) {
         this.withPreferredDuringSchedulingIgnoredDuringExecution(instance.getPreferredDuringSchedulingIgnoredDuringExecution());
         this.withRequiredDuringSchedulingIgnoredDuringExecution(instance.getRequiredDuringSchedulingIgnoredDuringExecution());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public NodeAffinityFluent addToPreferredDuringSchedulingIgnoredDuringExecution(int index, PreferredSchedulingTerm item) {
      if (this.preferredDuringSchedulingIgnoredDuringExecution == null) {
         this.preferredDuringSchedulingIgnoredDuringExecution = new ArrayList();
      }

      PreferredSchedulingTermBuilder builder = new PreferredSchedulingTermBuilder(item);
      if (index >= 0 && index < this.preferredDuringSchedulingIgnoredDuringExecution.size()) {
         this._visitables.get("preferredDuringSchedulingIgnoredDuringExecution").add(index, builder);
         this.preferredDuringSchedulingIgnoredDuringExecution.add(index, builder);
      } else {
         this._visitables.get("preferredDuringSchedulingIgnoredDuringExecution").add(builder);
         this.preferredDuringSchedulingIgnoredDuringExecution.add(builder);
      }

      return this;
   }

   public NodeAffinityFluent setToPreferredDuringSchedulingIgnoredDuringExecution(int index, PreferredSchedulingTerm item) {
      if (this.preferredDuringSchedulingIgnoredDuringExecution == null) {
         this.preferredDuringSchedulingIgnoredDuringExecution = new ArrayList();
      }

      PreferredSchedulingTermBuilder builder = new PreferredSchedulingTermBuilder(item);
      if (index >= 0 && index < this.preferredDuringSchedulingIgnoredDuringExecution.size()) {
         this._visitables.get("preferredDuringSchedulingIgnoredDuringExecution").set(index, builder);
         this.preferredDuringSchedulingIgnoredDuringExecution.set(index, builder);
      } else {
         this._visitables.get("preferredDuringSchedulingIgnoredDuringExecution").add(builder);
         this.preferredDuringSchedulingIgnoredDuringExecution.add(builder);
      }

      return this;
   }

   public NodeAffinityFluent addToPreferredDuringSchedulingIgnoredDuringExecution(PreferredSchedulingTerm... items) {
      if (this.preferredDuringSchedulingIgnoredDuringExecution == null) {
         this.preferredDuringSchedulingIgnoredDuringExecution = new ArrayList();
      }

      for(PreferredSchedulingTerm item : items) {
         PreferredSchedulingTermBuilder builder = new PreferredSchedulingTermBuilder(item);
         this._visitables.get("preferredDuringSchedulingIgnoredDuringExecution").add(builder);
         this.preferredDuringSchedulingIgnoredDuringExecution.add(builder);
      }

      return this;
   }

   public NodeAffinityFluent addAllToPreferredDuringSchedulingIgnoredDuringExecution(Collection items) {
      if (this.preferredDuringSchedulingIgnoredDuringExecution == null) {
         this.preferredDuringSchedulingIgnoredDuringExecution = new ArrayList();
      }

      for(PreferredSchedulingTerm item : items) {
         PreferredSchedulingTermBuilder builder = new PreferredSchedulingTermBuilder(item);
         this._visitables.get("preferredDuringSchedulingIgnoredDuringExecution").add(builder);
         this.preferredDuringSchedulingIgnoredDuringExecution.add(builder);
      }

      return this;
   }

   public NodeAffinityFluent removeFromPreferredDuringSchedulingIgnoredDuringExecution(PreferredSchedulingTerm... items) {
      if (this.preferredDuringSchedulingIgnoredDuringExecution == null) {
         return this;
      } else {
         for(PreferredSchedulingTerm item : items) {
            PreferredSchedulingTermBuilder builder = new PreferredSchedulingTermBuilder(item);
            this._visitables.get("preferredDuringSchedulingIgnoredDuringExecution").remove(builder);
            this.preferredDuringSchedulingIgnoredDuringExecution.remove(builder);
         }

         return this;
      }
   }

   public NodeAffinityFluent removeAllFromPreferredDuringSchedulingIgnoredDuringExecution(Collection items) {
      if (this.preferredDuringSchedulingIgnoredDuringExecution == null) {
         return this;
      } else {
         for(PreferredSchedulingTerm item : items) {
            PreferredSchedulingTermBuilder builder = new PreferredSchedulingTermBuilder(item);
            this._visitables.get("preferredDuringSchedulingIgnoredDuringExecution").remove(builder);
            this.preferredDuringSchedulingIgnoredDuringExecution.remove(builder);
         }

         return this;
      }
   }

   public NodeAffinityFluent removeMatchingFromPreferredDuringSchedulingIgnoredDuringExecution(Predicate predicate) {
      if (this.preferredDuringSchedulingIgnoredDuringExecution == null) {
         return this;
      } else {
         Iterator<PreferredSchedulingTermBuilder> each = this.preferredDuringSchedulingIgnoredDuringExecution.iterator();
         List visitables = this._visitables.get("preferredDuringSchedulingIgnoredDuringExecution");

         while(each.hasNext()) {
            PreferredSchedulingTermBuilder builder = (PreferredSchedulingTermBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildPreferredDuringSchedulingIgnoredDuringExecution() {
      return this.preferredDuringSchedulingIgnoredDuringExecution != null ? build(this.preferredDuringSchedulingIgnoredDuringExecution) : null;
   }

   public PreferredSchedulingTerm buildPreferredDuringSchedulingIgnoredDuringExecution(int index) {
      return ((PreferredSchedulingTermBuilder)this.preferredDuringSchedulingIgnoredDuringExecution.get(index)).build();
   }

   public PreferredSchedulingTerm buildFirstPreferredDuringSchedulingIgnoredDuringExecution() {
      return ((PreferredSchedulingTermBuilder)this.preferredDuringSchedulingIgnoredDuringExecution.get(0)).build();
   }

   public PreferredSchedulingTerm buildLastPreferredDuringSchedulingIgnoredDuringExecution() {
      return ((PreferredSchedulingTermBuilder)this.preferredDuringSchedulingIgnoredDuringExecution.get(this.preferredDuringSchedulingIgnoredDuringExecution.size() - 1)).build();
   }

   public PreferredSchedulingTerm buildMatchingPreferredDuringSchedulingIgnoredDuringExecution(Predicate predicate) {
      for(PreferredSchedulingTermBuilder item : this.preferredDuringSchedulingIgnoredDuringExecution) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingPreferredDuringSchedulingIgnoredDuringExecution(Predicate predicate) {
      for(PreferredSchedulingTermBuilder item : this.preferredDuringSchedulingIgnoredDuringExecution) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public NodeAffinityFluent withPreferredDuringSchedulingIgnoredDuringExecution(List preferredDuringSchedulingIgnoredDuringExecution) {
      if (this.preferredDuringSchedulingIgnoredDuringExecution != null) {
         this._visitables.get("preferredDuringSchedulingIgnoredDuringExecution").clear();
      }

      if (preferredDuringSchedulingIgnoredDuringExecution != null) {
         this.preferredDuringSchedulingIgnoredDuringExecution = new ArrayList();

         for(PreferredSchedulingTerm item : preferredDuringSchedulingIgnoredDuringExecution) {
            this.addToPreferredDuringSchedulingIgnoredDuringExecution(item);
         }
      } else {
         this.preferredDuringSchedulingIgnoredDuringExecution = null;
      }

      return this;
   }

   public NodeAffinityFluent withPreferredDuringSchedulingIgnoredDuringExecution(PreferredSchedulingTerm... preferredDuringSchedulingIgnoredDuringExecution) {
      if (this.preferredDuringSchedulingIgnoredDuringExecution != null) {
         this.preferredDuringSchedulingIgnoredDuringExecution.clear();
         this._visitables.remove("preferredDuringSchedulingIgnoredDuringExecution");
      }

      if (preferredDuringSchedulingIgnoredDuringExecution != null) {
         for(PreferredSchedulingTerm item : preferredDuringSchedulingIgnoredDuringExecution) {
            this.addToPreferredDuringSchedulingIgnoredDuringExecution(item);
         }
      }

      return this;
   }

   public boolean hasPreferredDuringSchedulingIgnoredDuringExecution() {
      return this.preferredDuringSchedulingIgnoredDuringExecution != null && !this.preferredDuringSchedulingIgnoredDuringExecution.isEmpty();
   }

   public PreferredDuringSchedulingIgnoredDuringExecutionNested addNewPreferredDuringSchedulingIgnoredDuringExecution() {
      return new PreferredDuringSchedulingIgnoredDuringExecutionNested(-1, (PreferredSchedulingTerm)null);
   }

   public PreferredDuringSchedulingIgnoredDuringExecutionNested addNewPreferredDuringSchedulingIgnoredDuringExecutionLike(PreferredSchedulingTerm item) {
      return new PreferredDuringSchedulingIgnoredDuringExecutionNested(-1, item);
   }

   public PreferredDuringSchedulingIgnoredDuringExecutionNested setNewPreferredDuringSchedulingIgnoredDuringExecutionLike(int index, PreferredSchedulingTerm item) {
      return new PreferredDuringSchedulingIgnoredDuringExecutionNested(index, item);
   }

   public PreferredDuringSchedulingIgnoredDuringExecutionNested editPreferredDuringSchedulingIgnoredDuringExecution(int index) {
      if (this.preferredDuringSchedulingIgnoredDuringExecution.size() <= index) {
         throw new RuntimeException("Can't edit preferredDuringSchedulingIgnoredDuringExecution. Index exceeds size.");
      } else {
         return this.setNewPreferredDuringSchedulingIgnoredDuringExecutionLike(index, this.buildPreferredDuringSchedulingIgnoredDuringExecution(index));
      }
   }

   public PreferredDuringSchedulingIgnoredDuringExecutionNested editFirstPreferredDuringSchedulingIgnoredDuringExecution() {
      if (this.preferredDuringSchedulingIgnoredDuringExecution.size() == 0) {
         throw new RuntimeException("Can't edit first preferredDuringSchedulingIgnoredDuringExecution. The list is empty.");
      } else {
         return this.setNewPreferredDuringSchedulingIgnoredDuringExecutionLike(0, this.buildPreferredDuringSchedulingIgnoredDuringExecution(0));
      }
   }

   public PreferredDuringSchedulingIgnoredDuringExecutionNested editLastPreferredDuringSchedulingIgnoredDuringExecution() {
      int index = this.preferredDuringSchedulingIgnoredDuringExecution.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last preferredDuringSchedulingIgnoredDuringExecution. The list is empty.");
      } else {
         return this.setNewPreferredDuringSchedulingIgnoredDuringExecutionLike(index, this.buildPreferredDuringSchedulingIgnoredDuringExecution(index));
      }
   }

   public PreferredDuringSchedulingIgnoredDuringExecutionNested editMatchingPreferredDuringSchedulingIgnoredDuringExecution(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.preferredDuringSchedulingIgnoredDuringExecution.size(); ++i) {
         if (predicate.test((PreferredSchedulingTermBuilder)this.preferredDuringSchedulingIgnoredDuringExecution.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching preferredDuringSchedulingIgnoredDuringExecution. No match found.");
      } else {
         return this.setNewPreferredDuringSchedulingIgnoredDuringExecutionLike(index, this.buildPreferredDuringSchedulingIgnoredDuringExecution(index));
      }
   }

   public NodeSelector buildRequiredDuringSchedulingIgnoredDuringExecution() {
      return this.requiredDuringSchedulingIgnoredDuringExecution != null ? this.requiredDuringSchedulingIgnoredDuringExecution.build() : null;
   }

   public NodeAffinityFluent withRequiredDuringSchedulingIgnoredDuringExecution(NodeSelector requiredDuringSchedulingIgnoredDuringExecution) {
      this._visitables.remove("requiredDuringSchedulingIgnoredDuringExecution");
      if (requiredDuringSchedulingIgnoredDuringExecution != null) {
         this.requiredDuringSchedulingIgnoredDuringExecution = new NodeSelectorBuilder(requiredDuringSchedulingIgnoredDuringExecution);
         this._visitables.get("requiredDuringSchedulingIgnoredDuringExecution").add(this.requiredDuringSchedulingIgnoredDuringExecution);
      } else {
         this.requiredDuringSchedulingIgnoredDuringExecution = null;
         this._visitables.get("requiredDuringSchedulingIgnoredDuringExecution").remove(this.requiredDuringSchedulingIgnoredDuringExecution);
      }

      return this;
   }

   public boolean hasRequiredDuringSchedulingIgnoredDuringExecution() {
      return this.requiredDuringSchedulingIgnoredDuringExecution != null;
   }

   public RequiredDuringSchedulingIgnoredDuringExecutionNested withNewRequiredDuringSchedulingIgnoredDuringExecution() {
      return new RequiredDuringSchedulingIgnoredDuringExecutionNested((NodeSelector)null);
   }

   public RequiredDuringSchedulingIgnoredDuringExecutionNested withNewRequiredDuringSchedulingIgnoredDuringExecutionLike(NodeSelector item) {
      return new RequiredDuringSchedulingIgnoredDuringExecutionNested(item);
   }

   public RequiredDuringSchedulingIgnoredDuringExecutionNested editRequiredDuringSchedulingIgnoredDuringExecution() {
      return this.withNewRequiredDuringSchedulingIgnoredDuringExecutionLike((NodeSelector)Optional.ofNullable(this.buildRequiredDuringSchedulingIgnoredDuringExecution()).orElse((Object)null));
   }

   public RequiredDuringSchedulingIgnoredDuringExecutionNested editOrNewRequiredDuringSchedulingIgnoredDuringExecution() {
      return this.withNewRequiredDuringSchedulingIgnoredDuringExecutionLike((NodeSelector)Optional.ofNullable(this.buildRequiredDuringSchedulingIgnoredDuringExecution()).orElse((new NodeSelectorBuilder()).build()));
   }

   public RequiredDuringSchedulingIgnoredDuringExecutionNested editOrNewRequiredDuringSchedulingIgnoredDuringExecutionLike(NodeSelector item) {
      return this.withNewRequiredDuringSchedulingIgnoredDuringExecutionLike((NodeSelector)Optional.ofNullable(this.buildRequiredDuringSchedulingIgnoredDuringExecution()).orElse(item));
   }

   public NodeAffinityFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NodeAffinityFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NodeAffinityFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NodeAffinityFluent removeFromAdditionalProperties(Map map) {
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

   public NodeAffinityFluent withAdditionalProperties(Map additionalProperties) {
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
            NodeAffinityFluent that = (NodeAffinityFluent)o;
            if (!Objects.equals(this.preferredDuringSchedulingIgnoredDuringExecution, that.preferredDuringSchedulingIgnoredDuringExecution)) {
               return false;
            } else if (!Objects.equals(this.requiredDuringSchedulingIgnoredDuringExecution, that.requiredDuringSchedulingIgnoredDuringExecution)) {
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
      return Objects.hash(new Object[]{this.preferredDuringSchedulingIgnoredDuringExecution, this.requiredDuringSchedulingIgnoredDuringExecution, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.preferredDuringSchedulingIgnoredDuringExecution != null && !this.preferredDuringSchedulingIgnoredDuringExecution.isEmpty()) {
         sb.append("preferredDuringSchedulingIgnoredDuringExecution:");
         sb.append(this.preferredDuringSchedulingIgnoredDuringExecution + ",");
      }

      if (this.requiredDuringSchedulingIgnoredDuringExecution != null) {
         sb.append("requiredDuringSchedulingIgnoredDuringExecution:");
         sb.append(this.requiredDuringSchedulingIgnoredDuringExecution + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class PreferredDuringSchedulingIgnoredDuringExecutionNested extends PreferredSchedulingTermFluent implements Nested {
      PreferredSchedulingTermBuilder builder;
      int index;

      PreferredDuringSchedulingIgnoredDuringExecutionNested(int index, PreferredSchedulingTerm item) {
         this.index = index;
         this.builder = new PreferredSchedulingTermBuilder(this, item);
      }

      public Object and() {
         return NodeAffinityFluent.this.setToPreferredDuringSchedulingIgnoredDuringExecution(this.index, this.builder.build());
      }

      public Object endPreferredDuringSchedulingIgnoredDuringExecution() {
         return this.and();
      }
   }

   public class RequiredDuringSchedulingIgnoredDuringExecutionNested extends NodeSelectorFluent implements Nested {
      NodeSelectorBuilder builder;

      RequiredDuringSchedulingIgnoredDuringExecutionNested(NodeSelector item) {
         this.builder = new NodeSelectorBuilder(this, item);
      }

      public Object and() {
         return NodeAffinityFluent.this.withRequiredDuringSchedulingIgnoredDuringExecution(this.builder.build());
      }

      public Object endRequiredDuringSchedulingIgnoredDuringExecution() {
         return this.and();
      }
   }
}
