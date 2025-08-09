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

public class PodAffinityFluent extends BaseFluent {
   private ArrayList preferredDuringSchedulingIgnoredDuringExecution = new ArrayList();
   private ArrayList requiredDuringSchedulingIgnoredDuringExecution = new ArrayList();
   private Map additionalProperties;

   public PodAffinityFluent() {
   }

   public PodAffinityFluent(PodAffinity instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PodAffinity instance) {
      instance = instance != null ? instance : new PodAffinity();
      if (instance != null) {
         this.withPreferredDuringSchedulingIgnoredDuringExecution(instance.getPreferredDuringSchedulingIgnoredDuringExecution());
         this.withRequiredDuringSchedulingIgnoredDuringExecution(instance.getRequiredDuringSchedulingIgnoredDuringExecution());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public PodAffinityFluent addToPreferredDuringSchedulingIgnoredDuringExecution(int index, WeightedPodAffinityTerm item) {
      if (this.preferredDuringSchedulingIgnoredDuringExecution == null) {
         this.preferredDuringSchedulingIgnoredDuringExecution = new ArrayList();
      }

      WeightedPodAffinityTermBuilder builder = new WeightedPodAffinityTermBuilder(item);
      if (index >= 0 && index < this.preferredDuringSchedulingIgnoredDuringExecution.size()) {
         this._visitables.get("preferredDuringSchedulingIgnoredDuringExecution").add(index, builder);
         this.preferredDuringSchedulingIgnoredDuringExecution.add(index, builder);
      } else {
         this._visitables.get("preferredDuringSchedulingIgnoredDuringExecution").add(builder);
         this.preferredDuringSchedulingIgnoredDuringExecution.add(builder);
      }

      return this;
   }

   public PodAffinityFluent setToPreferredDuringSchedulingIgnoredDuringExecution(int index, WeightedPodAffinityTerm item) {
      if (this.preferredDuringSchedulingIgnoredDuringExecution == null) {
         this.preferredDuringSchedulingIgnoredDuringExecution = new ArrayList();
      }

      WeightedPodAffinityTermBuilder builder = new WeightedPodAffinityTermBuilder(item);
      if (index >= 0 && index < this.preferredDuringSchedulingIgnoredDuringExecution.size()) {
         this._visitables.get("preferredDuringSchedulingIgnoredDuringExecution").set(index, builder);
         this.preferredDuringSchedulingIgnoredDuringExecution.set(index, builder);
      } else {
         this._visitables.get("preferredDuringSchedulingIgnoredDuringExecution").add(builder);
         this.preferredDuringSchedulingIgnoredDuringExecution.add(builder);
      }

      return this;
   }

   public PodAffinityFluent addToPreferredDuringSchedulingIgnoredDuringExecution(WeightedPodAffinityTerm... items) {
      if (this.preferredDuringSchedulingIgnoredDuringExecution == null) {
         this.preferredDuringSchedulingIgnoredDuringExecution = new ArrayList();
      }

      for(WeightedPodAffinityTerm item : items) {
         WeightedPodAffinityTermBuilder builder = new WeightedPodAffinityTermBuilder(item);
         this._visitables.get("preferredDuringSchedulingIgnoredDuringExecution").add(builder);
         this.preferredDuringSchedulingIgnoredDuringExecution.add(builder);
      }

      return this;
   }

   public PodAffinityFluent addAllToPreferredDuringSchedulingIgnoredDuringExecution(Collection items) {
      if (this.preferredDuringSchedulingIgnoredDuringExecution == null) {
         this.preferredDuringSchedulingIgnoredDuringExecution = new ArrayList();
      }

      for(WeightedPodAffinityTerm item : items) {
         WeightedPodAffinityTermBuilder builder = new WeightedPodAffinityTermBuilder(item);
         this._visitables.get("preferredDuringSchedulingIgnoredDuringExecution").add(builder);
         this.preferredDuringSchedulingIgnoredDuringExecution.add(builder);
      }

      return this;
   }

   public PodAffinityFluent removeFromPreferredDuringSchedulingIgnoredDuringExecution(WeightedPodAffinityTerm... items) {
      if (this.preferredDuringSchedulingIgnoredDuringExecution == null) {
         return this;
      } else {
         for(WeightedPodAffinityTerm item : items) {
            WeightedPodAffinityTermBuilder builder = new WeightedPodAffinityTermBuilder(item);
            this._visitables.get("preferredDuringSchedulingIgnoredDuringExecution").remove(builder);
            this.preferredDuringSchedulingIgnoredDuringExecution.remove(builder);
         }

         return this;
      }
   }

   public PodAffinityFluent removeAllFromPreferredDuringSchedulingIgnoredDuringExecution(Collection items) {
      if (this.preferredDuringSchedulingIgnoredDuringExecution == null) {
         return this;
      } else {
         for(WeightedPodAffinityTerm item : items) {
            WeightedPodAffinityTermBuilder builder = new WeightedPodAffinityTermBuilder(item);
            this._visitables.get("preferredDuringSchedulingIgnoredDuringExecution").remove(builder);
            this.preferredDuringSchedulingIgnoredDuringExecution.remove(builder);
         }

         return this;
      }
   }

   public PodAffinityFluent removeMatchingFromPreferredDuringSchedulingIgnoredDuringExecution(Predicate predicate) {
      if (this.preferredDuringSchedulingIgnoredDuringExecution == null) {
         return this;
      } else {
         Iterator<WeightedPodAffinityTermBuilder> each = this.preferredDuringSchedulingIgnoredDuringExecution.iterator();
         List visitables = this._visitables.get("preferredDuringSchedulingIgnoredDuringExecution");

         while(each.hasNext()) {
            WeightedPodAffinityTermBuilder builder = (WeightedPodAffinityTermBuilder)each.next();
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

   public WeightedPodAffinityTerm buildPreferredDuringSchedulingIgnoredDuringExecution(int index) {
      return ((WeightedPodAffinityTermBuilder)this.preferredDuringSchedulingIgnoredDuringExecution.get(index)).build();
   }

   public WeightedPodAffinityTerm buildFirstPreferredDuringSchedulingIgnoredDuringExecution() {
      return ((WeightedPodAffinityTermBuilder)this.preferredDuringSchedulingIgnoredDuringExecution.get(0)).build();
   }

   public WeightedPodAffinityTerm buildLastPreferredDuringSchedulingIgnoredDuringExecution() {
      return ((WeightedPodAffinityTermBuilder)this.preferredDuringSchedulingIgnoredDuringExecution.get(this.preferredDuringSchedulingIgnoredDuringExecution.size() - 1)).build();
   }

   public WeightedPodAffinityTerm buildMatchingPreferredDuringSchedulingIgnoredDuringExecution(Predicate predicate) {
      for(WeightedPodAffinityTermBuilder item : this.preferredDuringSchedulingIgnoredDuringExecution) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingPreferredDuringSchedulingIgnoredDuringExecution(Predicate predicate) {
      for(WeightedPodAffinityTermBuilder item : this.preferredDuringSchedulingIgnoredDuringExecution) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodAffinityFluent withPreferredDuringSchedulingIgnoredDuringExecution(List preferredDuringSchedulingIgnoredDuringExecution) {
      if (this.preferredDuringSchedulingIgnoredDuringExecution != null) {
         this._visitables.get("preferredDuringSchedulingIgnoredDuringExecution").clear();
      }

      if (preferredDuringSchedulingIgnoredDuringExecution != null) {
         this.preferredDuringSchedulingIgnoredDuringExecution = new ArrayList();

         for(WeightedPodAffinityTerm item : preferredDuringSchedulingIgnoredDuringExecution) {
            this.addToPreferredDuringSchedulingIgnoredDuringExecution(item);
         }
      } else {
         this.preferredDuringSchedulingIgnoredDuringExecution = null;
      }

      return this;
   }

   public PodAffinityFluent withPreferredDuringSchedulingIgnoredDuringExecution(WeightedPodAffinityTerm... preferredDuringSchedulingIgnoredDuringExecution) {
      if (this.preferredDuringSchedulingIgnoredDuringExecution != null) {
         this.preferredDuringSchedulingIgnoredDuringExecution.clear();
         this._visitables.remove("preferredDuringSchedulingIgnoredDuringExecution");
      }

      if (preferredDuringSchedulingIgnoredDuringExecution != null) {
         for(WeightedPodAffinityTerm item : preferredDuringSchedulingIgnoredDuringExecution) {
            this.addToPreferredDuringSchedulingIgnoredDuringExecution(item);
         }
      }

      return this;
   }

   public boolean hasPreferredDuringSchedulingIgnoredDuringExecution() {
      return this.preferredDuringSchedulingIgnoredDuringExecution != null && !this.preferredDuringSchedulingIgnoredDuringExecution.isEmpty();
   }

   public PreferredDuringSchedulingIgnoredDuringExecutionNested addNewPreferredDuringSchedulingIgnoredDuringExecution() {
      return new PreferredDuringSchedulingIgnoredDuringExecutionNested(-1, (WeightedPodAffinityTerm)null);
   }

   public PreferredDuringSchedulingIgnoredDuringExecutionNested addNewPreferredDuringSchedulingIgnoredDuringExecutionLike(WeightedPodAffinityTerm item) {
      return new PreferredDuringSchedulingIgnoredDuringExecutionNested(-1, item);
   }

   public PreferredDuringSchedulingIgnoredDuringExecutionNested setNewPreferredDuringSchedulingIgnoredDuringExecutionLike(int index, WeightedPodAffinityTerm item) {
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
         if (predicate.test((WeightedPodAffinityTermBuilder)this.preferredDuringSchedulingIgnoredDuringExecution.get(i))) {
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

   public PodAffinityFluent addToRequiredDuringSchedulingIgnoredDuringExecution(int index, PodAffinityTerm item) {
      if (this.requiredDuringSchedulingIgnoredDuringExecution == null) {
         this.requiredDuringSchedulingIgnoredDuringExecution = new ArrayList();
      }

      PodAffinityTermBuilder builder = new PodAffinityTermBuilder(item);
      if (index >= 0 && index < this.requiredDuringSchedulingIgnoredDuringExecution.size()) {
         this._visitables.get("requiredDuringSchedulingIgnoredDuringExecution").add(index, builder);
         this.requiredDuringSchedulingIgnoredDuringExecution.add(index, builder);
      } else {
         this._visitables.get("requiredDuringSchedulingIgnoredDuringExecution").add(builder);
         this.requiredDuringSchedulingIgnoredDuringExecution.add(builder);
      }

      return this;
   }

   public PodAffinityFluent setToRequiredDuringSchedulingIgnoredDuringExecution(int index, PodAffinityTerm item) {
      if (this.requiredDuringSchedulingIgnoredDuringExecution == null) {
         this.requiredDuringSchedulingIgnoredDuringExecution = new ArrayList();
      }

      PodAffinityTermBuilder builder = new PodAffinityTermBuilder(item);
      if (index >= 0 && index < this.requiredDuringSchedulingIgnoredDuringExecution.size()) {
         this._visitables.get("requiredDuringSchedulingIgnoredDuringExecution").set(index, builder);
         this.requiredDuringSchedulingIgnoredDuringExecution.set(index, builder);
      } else {
         this._visitables.get("requiredDuringSchedulingIgnoredDuringExecution").add(builder);
         this.requiredDuringSchedulingIgnoredDuringExecution.add(builder);
      }

      return this;
   }

   public PodAffinityFluent addToRequiredDuringSchedulingIgnoredDuringExecution(PodAffinityTerm... items) {
      if (this.requiredDuringSchedulingIgnoredDuringExecution == null) {
         this.requiredDuringSchedulingIgnoredDuringExecution = new ArrayList();
      }

      for(PodAffinityTerm item : items) {
         PodAffinityTermBuilder builder = new PodAffinityTermBuilder(item);
         this._visitables.get("requiredDuringSchedulingIgnoredDuringExecution").add(builder);
         this.requiredDuringSchedulingIgnoredDuringExecution.add(builder);
      }

      return this;
   }

   public PodAffinityFluent addAllToRequiredDuringSchedulingIgnoredDuringExecution(Collection items) {
      if (this.requiredDuringSchedulingIgnoredDuringExecution == null) {
         this.requiredDuringSchedulingIgnoredDuringExecution = new ArrayList();
      }

      for(PodAffinityTerm item : items) {
         PodAffinityTermBuilder builder = new PodAffinityTermBuilder(item);
         this._visitables.get("requiredDuringSchedulingIgnoredDuringExecution").add(builder);
         this.requiredDuringSchedulingIgnoredDuringExecution.add(builder);
      }

      return this;
   }

   public PodAffinityFluent removeFromRequiredDuringSchedulingIgnoredDuringExecution(PodAffinityTerm... items) {
      if (this.requiredDuringSchedulingIgnoredDuringExecution == null) {
         return this;
      } else {
         for(PodAffinityTerm item : items) {
            PodAffinityTermBuilder builder = new PodAffinityTermBuilder(item);
            this._visitables.get("requiredDuringSchedulingIgnoredDuringExecution").remove(builder);
            this.requiredDuringSchedulingIgnoredDuringExecution.remove(builder);
         }

         return this;
      }
   }

   public PodAffinityFluent removeAllFromRequiredDuringSchedulingIgnoredDuringExecution(Collection items) {
      if (this.requiredDuringSchedulingIgnoredDuringExecution == null) {
         return this;
      } else {
         for(PodAffinityTerm item : items) {
            PodAffinityTermBuilder builder = new PodAffinityTermBuilder(item);
            this._visitables.get("requiredDuringSchedulingIgnoredDuringExecution").remove(builder);
            this.requiredDuringSchedulingIgnoredDuringExecution.remove(builder);
         }

         return this;
      }
   }

   public PodAffinityFluent removeMatchingFromRequiredDuringSchedulingIgnoredDuringExecution(Predicate predicate) {
      if (this.requiredDuringSchedulingIgnoredDuringExecution == null) {
         return this;
      } else {
         Iterator<PodAffinityTermBuilder> each = this.requiredDuringSchedulingIgnoredDuringExecution.iterator();
         List visitables = this._visitables.get("requiredDuringSchedulingIgnoredDuringExecution");

         while(each.hasNext()) {
            PodAffinityTermBuilder builder = (PodAffinityTermBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildRequiredDuringSchedulingIgnoredDuringExecution() {
      return this.requiredDuringSchedulingIgnoredDuringExecution != null ? build(this.requiredDuringSchedulingIgnoredDuringExecution) : null;
   }

   public PodAffinityTerm buildRequiredDuringSchedulingIgnoredDuringExecution(int index) {
      return ((PodAffinityTermBuilder)this.requiredDuringSchedulingIgnoredDuringExecution.get(index)).build();
   }

   public PodAffinityTerm buildFirstRequiredDuringSchedulingIgnoredDuringExecution() {
      return ((PodAffinityTermBuilder)this.requiredDuringSchedulingIgnoredDuringExecution.get(0)).build();
   }

   public PodAffinityTerm buildLastRequiredDuringSchedulingIgnoredDuringExecution() {
      return ((PodAffinityTermBuilder)this.requiredDuringSchedulingIgnoredDuringExecution.get(this.requiredDuringSchedulingIgnoredDuringExecution.size() - 1)).build();
   }

   public PodAffinityTerm buildMatchingRequiredDuringSchedulingIgnoredDuringExecution(Predicate predicate) {
      for(PodAffinityTermBuilder item : this.requiredDuringSchedulingIgnoredDuringExecution) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingRequiredDuringSchedulingIgnoredDuringExecution(Predicate predicate) {
      for(PodAffinityTermBuilder item : this.requiredDuringSchedulingIgnoredDuringExecution) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodAffinityFluent withRequiredDuringSchedulingIgnoredDuringExecution(List requiredDuringSchedulingIgnoredDuringExecution) {
      if (this.requiredDuringSchedulingIgnoredDuringExecution != null) {
         this._visitables.get("requiredDuringSchedulingIgnoredDuringExecution").clear();
      }

      if (requiredDuringSchedulingIgnoredDuringExecution != null) {
         this.requiredDuringSchedulingIgnoredDuringExecution = new ArrayList();

         for(PodAffinityTerm item : requiredDuringSchedulingIgnoredDuringExecution) {
            this.addToRequiredDuringSchedulingIgnoredDuringExecution(item);
         }
      } else {
         this.requiredDuringSchedulingIgnoredDuringExecution = null;
      }

      return this;
   }

   public PodAffinityFluent withRequiredDuringSchedulingIgnoredDuringExecution(PodAffinityTerm... requiredDuringSchedulingIgnoredDuringExecution) {
      if (this.requiredDuringSchedulingIgnoredDuringExecution != null) {
         this.requiredDuringSchedulingIgnoredDuringExecution.clear();
         this._visitables.remove("requiredDuringSchedulingIgnoredDuringExecution");
      }

      if (requiredDuringSchedulingIgnoredDuringExecution != null) {
         for(PodAffinityTerm item : requiredDuringSchedulingIgnoredDuringExecution) {
            this.addToRequiredDuringSchedulingIgnoredDuringExecution(item);
         }
      }

      return this;
   }

   public boolean hasRequiredDuringSchedulingIgnoredDuringExecution() {
      return this.requiredDuringSchedulingIgnoredDuringExecution != null && !this.requiredDuringSchedulingIgnoredDuringExecution.isEmpty();
   }

   public RequiredDuringSchedulingIgnoredDuringExecutionNested addNewRequiredDuringSchedulingIgnoredDuringExecution() {
      return new RequiredDuringSchedulingIgnoredDuringExecutionNested(-1, (PodAffinityTerm)null);
   }

   public RequiredDuringSchedulingIgnoredDuringExecutionNested addNewRequiredDuringSchedulingIgnoredDuringExecutionLike(PodAffinityTerm item) {
      return new RequiredDuringSchedulingIgnoredDuringExecutionNested(-1, item);
   }

   public RequiredDuringSchedulingIgnoredDuringExecutionNested setNewRequiredDuringSchedulingIgnoredDuringExecutionLike(int index, PodAffinityTerm item) {
      return new RequiredDuringSchedulingIgnoredDuringExecutionNested(index, item);
   }

   public RequiredDuringSchedulingIgnoredDuringExecutionNested editRequiredDuringSchedulingIgnoredDuringExecution(int index) {
      if (this.requiredDuringSchedulingIgnoredDuringExecution.size() <= index) {
         throw new RuntimeException("Can't edit requiredDuringSchedulingIgnoredDuringExecution. Index exceeds size.");
      } else {
         return this.setNewRequiredDuringSchedulingIgnoredDuringExecutionLike(index, this.buildRequiredDuringSchedulingIgnoredDuringExecution(index));
      }
   }

   public RequiredDuringSchedulingIgnoredDuringExecutionNested editFirstRequiredDuringSchedulingIgnoredDuringExecution() {
      if (this.requiredDuringSchedulingIgnoredDuringExecution.size() == 0) {
         throw new RuntimeException("Can't edit first requiredDuringSchedulingIgnoredDuringExecution. The list is empty.");
      } else {
         return this.setNewRequiredDuringSchedulingIgnoredDuringExecutionLike(0, this.buildRequiredDuringSchedulingIgnoredDuringExecution(0));
      }
   }

   public RequiredDuringSchedulingIgnoredDuringExecutionNested editLastRequiredDuringSchedulingIgnoredDuringExecution() {
      int index = this.requiredDuringSchedulingIgnoredDuringExecution.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last requiredDuringSchedulingIgnoredDuringExecution. The list is empty.");
      } else {
         return this.setNewRequiredDuringSchedulingIgnoredDuringExecutionLike(index, this.buildRequiredDuringSchedulingIgnoredDuringExecution(index));
      }
   }

   public RequiredDuringSchedulingIgnoredDuringExecutionNested editMatchingRequiredDuringSchedulingIgnoredDuringExecution(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.requiredDuringSchedulingIgnoredDuringExecution.size(); ++i) {
         if (predicate.test((PodAffinityTermBuilder)this.requiredDuringSchedulingIgnoredDuringExecution.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching requiredDuringSchedulingIgnoredDuringExecution. No match found.");
      } else {
         return this.setNewRequiredDuringSchedulingIgnoredDuringExecutionLike(index, this.buildRequiredDuringSchedulingIgnoredDuringExecution(index));
      }
   }

   public PodAffinityFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PodAffinityFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PodAffinityFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PodAffinityFluent removeFromAdditionalProperties(Map map) {
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

   public PodAffinityFluent withAdditionalProperties(Map additionalProperties) {
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
            PodAffinityFluent that = (PodAffinityFluent)o;
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

      if (this.requiredDuringSchedulingIgnoredDuringExecution != null && !this.requiredDuringSchedulingIgnoredDuringExecution.isEmpty()) {
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

   public class PreferredDuringSchedulingIgnoredDuringExecutionNested extends WeightedPodAffinityTermFluent implements Nested {
      WeightedPodAffinityTermBuilder builder;
      int index;

      PreferredDuringSchedulingIgnoredDuringExecutionNested(int index, WeightedPodAffinityTerm item) {
         this.index = index;
         this.builder = new WeightedPodAffinityTermBuilder(this, item);
      }

      public Object and() {
         return PodAffinityFluent.this.setToPreferredDuringSchedulingIgnoredDuringExecution(this.index, this.builder.build());
      }

      public Object endPreferredDuringSchedulingIgnoredDuringExecution() {
         return this.and();
      }
   }

   public class RequiredDuringSchedulingIgnoredDuringExecutionNested extends PodAffinityTermFluent implements Nested {
      PodAffinityTermBuilder builder;
      int index;

      RequiredDuringSchedulingIgnoredDuringExecutionNested(int index, PodAffinityTerm item) {
         this.index = index;
         this.builder = new PodAffinityTermBuilder(this, item);
      }

      public Object and() {
         return PodAffinityFluent.this.setToRequiredDuringSchedulingIgnoredDuringExecution(this.index, this.builder.build());
      }

      public Object endRequiredDuringSchedulingIgnoredDuringExecution() {
         return this.and();
      }
   }
}
