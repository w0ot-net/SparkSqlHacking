package io.fabric8.kubernetes.api.model.autoscaling.v2beta2;

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

public class HorizontalPodAutoscalerStatusFluent extends BaseFluent {
   private ArrayList conditions = new ArrayList();
   private ArrayList currentMetrics = new ArrayList();
   private Integer currentReplicas;
   private Integer desiredReplicas;
   private String lastScaleTime;
   private Long observedGeneration;
   private Map additionalProperties;

   public HorizontalPodAutoscalerStatusFluent() {
   }

   public HorizontalPodAutoscalerStatusFluent(HorizontalPodAutoscalerStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(HorizontalPodAutoscalerStatus instance) {
      instance = instance != null ? instance : new HorizontalPodAutoscalerStatus();
      if (instance != null) {
         this.withConditions(instance.getConditions());
         this.withCurrentMetrics(instance.getCurrentMetrics());
         this.withCurrentReplicas(instance.getCurrentReplicas());
         this.withDesiredReplicas(instance.getDesiredReplicas());
         this.withLastScaleTime(instance.getLastScaleTime());
         this.withObservedGeneration(instance.getObservedGeneration());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public HorizontalPodAutoscalerStatusFluent addToConditions(int index, HorizontalPodAutoscalerCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      HorizontalPodAutoscalerConditionBuilder builder = new HorizontalPodAutoscalerConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").add(index, builder);
         this.conditions.add(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public HorizontalPodAutoscalerStatusFluent setToConditions(int index, HorizontalPodAutoscalerCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      HorizontalPodAutoscalerConditionBuilder builder = new HorizontalPodAutoscalerConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").set(index, builder);
         this.conditions.set(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public HorizontalPodAutoscalerStatusFluent addToConditions(HorizontalPodAutoscalerCondition... items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(HorizontalPodAutoscalerCondition item : items) {
         HorizontalPodAutoscalerConditionBuilder builder = new HorizontalPodAutoscalerConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public HorizontalPodAutoscalerStatusFluent addAllToConditions(Collection items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(HorizontalPodAutoscalerCondition item : items) {
         HorizontalPodAutoscalerConditionBuilder builder = new HorizontalPodAutoscalerConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public HorizontalPodAutoscalerStatusFluent removeFromConditions(HorizontalPodAutoscalerCondition... items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(HorizontalPodAutoscalerCondition item : items) {
            HorizontalPodAutoscalerConditionBuilder builder = new HorizontalPodAutoscalerConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public HorizontalPodAutoscalerStatusFluent removeAllFromConditions(Collection items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(HorizontalPodAutoscalerCondition item : items) {
            HorizontalPodAutoscalerConditionBuilder builder = new HorizontalPodAutoscalerConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public HorizontalPodAutoscalerStatusFluent removeMatchingFromConditions(Predicate predicate) {
      if (this.conditions == null) {
         return this;
      } else {
         Iterator<HorizontalPodAutoscalerConditionBuilder> each = this.conditions.iterator();
         List visitables = this._visitables.get("conditions");

         while(each.hasNext()) {
            HorizontalPodAutoscalerConditionBuilder builder = (HorizontalPodAutoscalerConditionBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildConditions() {
      return this.conditions != null ? build(this.conditions) : null;
   }

   public HorizontalPodAutoscalerCondition buildCondition(int index) {
      return ((HorizontalPodAutoscalerConditionBuilder)this.conditions.get(index)).build();
   }

   public HorizontalPodAutoscalerCondition buildFirstCondition() {
      return ((HorizontalPodAutoscalerConditionBuilder)this.conditions.get(0)).build();
   }

   public HorizontalPodAutoscalerCondition buildLastCondition() {
      return ((HorizontalPodAutoscalerConditionBuilder)this.conditions.get(this.conditions.size() - 1)).build();
   }

   public HorizontalPodAutoscalerCondition buildMatchingCondition(Predicate predicate) {
      for(HorizontalPodAutoscalerConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingCondition(Predicate predicate) {
      for(HorizontalPodAutoscalerConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public HorizontalPodAutoscalerStatusFluent withConditions(List conditions) {
      if (this.conditions != null) {
         this._visitables.get("conditions").clear();
      }

      if (conditions != null) {
         this.conditions = new ArrayList();

         for(HorizontalPodAutoscalerCondition item : conditions) {
            this.addToConditions(item);
         }
      } else {
         this.conditions = null;
      }

      return this;
   }

   public HorizontalPodAutoscalerStatusFluent withConditions(HorizontalPodAutoscalerCondition... conditions) {
      if (this.conditions != null) {
         this.conditions.clear();
         this._visitables.remove("conditions");
      }

      if (conditions != null) {
         for(HorizontalPodAutoscalerCondition item : conditions) {
            this.addToConditions(item);
         }
      }

      return this;
   }

   public boolean hasConditions() {
      return this.conditions != null && !this.conditions.isEmpty();
   }

   public HorizontalPodAutoscalerStatusFluent addNewCondition(String lastTransitionTime, String message, String reason, String status, String type) {
      return this.addToConditions(new HorizontalPodAutoscalerCondition(lastTransitionTime, message, reason, status, type));
   }

   public ConditionsNested addNewCondition() {
      return new ConditionsNested(-1, (HorizontalPodAutoscalerCondition)null);
   }

   public ConditionsNested addNewConditionLike(HorizontalPodAutoscalerCondition item) {
      return new ConditionsNested(-1, item);
   }

   public ConditionsNested setNewConditionLike(int index, HorizontalPodAutoscalerCondition item) {
      return new ConditionsNested(index, item);
   }

   public ConditionsNested editCondition(int index) {
      if (this.conditions.size() <= index) {
         throw new RuntimeException("Can't edit conditions. Index exceeds size.");
      } else {
         return this.setNewConditionLike(index, this.buildCondition(index));
      }
   }

   public ConditionsNested editFirstCondition() {
      if (this.conditions.size() == 0) {
         throw new RuntimeException("Can't edit first conditions. The list is empty.");
      } else {
         return this.setNewConditionLike(0, this.buildCondition(0));
      }
   }

   public ConditionsNested editLastCondition() {
      int index = this.conditions.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last conditions. The list is empty.");
      } else {
         return this.setNewConditionLike(index, this.buildCondition(index));
      }
   }

   public ConditionsNested editMatchingCondition(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.conditions.size(); ++i) {
         if (predicate.test((HorizontalPodAutoscalerConditionBuilder)this.conditions.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching conditions. No match found.");
      } else {
         return this.setNewConditionLike(index, this.buildCondition(index));
      }
   }

   public HorizontalPodAutoscalerStatusFluent addToCurrentMetrics(int index, MetricStatus item) {
      if (this.currentMetrics == null) {
         this.currentMetrics = new ArrayList();
      }

      MetricStatusBuilder builder = new MetricStatusBuilder(item);
      if (index >= 0 && index < this.currentMetrics.size()) {
         this._visitables.get("currentMetrics").add(index, builder);
         this.currentMetrics.add(index, builder);
      } else {
         this._visitables.get("currentMetrics").add(builder);
         this.currentMetrics.add(builder);
      }

      return this;
   }

   public HorizontalPodAutoscalerStatusFluent setToCurrentMetrics(int index, MetricStatus item) {
      if (this.currentMetrics == null) {
         this.currentMetrics = new ArrayList();
      }

      MetricStatusBuilder builder = new MetricStatusBuilder(item);
      if (index >= 0 && index < this.currentMetrics.size()) {
         this._visitables.get("currentMetrics").set(index, builder);
         this.currentMetrics.set(index, builder);
      } else {
         this._visitables.get("currentMetrics").add(builder);
         this.currentMetrics.add(builder);
      }

      return this;
   }

   public HorizontalPodAutoscalerStatusFluent addToCurrentMetrics(MetricStatus... items) {
      if (this.currentMetrics == null) {
         this.currentMetrics = new ArrayList();
      }

      for(MetricStatus item : items) {
         MetricStatusBuilder builder = new MetricStatusBuilder(item);
         this._visitables.get("currentMetrics").add(builder);
         this.currentMetrics.add(builder);
      }

      return this;
   }

   public HorizontalPodAutoscalerStatusFluent addAllToCurrentMetrics(Collection items) {
      if (this.currentMetrics == null) {
         this.currentMetrics = new ArrayList();
      }

      for(MetricStatus item : items) {
         MetricStatusBuilder builder = new MetricStatusBuilder(item);
         this._visitables.get("currentMetrics").add(builder);
         this.currentMetrics.add(builder);
      }

      return this;
   }

   public HorizontalPodAutoscalerStatusFluent removeFromCurrentMetrics(MetricStatus... items) {
      if (this.currentMetrics == null) {
         return this;
      } else {
         for(MetricStatus item : items) {
            MetricStatusBuilder builder = new MetricStatusBuilder(item);
            this._visitables.get("currentMetrics").remove(builder);
            this.currentMetrics.remove(builder);
         }

         return this;
      }
   }

   public HorizontalPodAutoscalerStatusFluent removeAllFromCurrentMetrics(Collection items) {
      if (this.currentMetrics == null) {
         return this;
      } else {
         for(MetricStatus item : items) {
            MetricStatusBuilder builder = new MetricStatusBuilder(item);
            this._visitables.get("currentMetrics").remove(builder);
            this.currentMetrics.remove(builder);
         }

         return this;
      }
   }

   public HorizontalPodAutoscalerStatusFluent removeMatchingFromCurrentMetrics(Predicate predicate) {
      if (this.currentMetrics == null) {
         return this;
      } else {
         Iterator<MetricStatusBuilder> each = this.currentMetrics.iterator();
         List visitables = this._visitables.get("currentMetrics");

         while(each.hasNext()) {
            MetricStatusBuilder builder = (MetricStatusBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildCurrentMetrics() {
      return this.currentMetrics != null ? build(this.currentMetrics) : null;
   }

   public MetricStatus buildCurrentMetric(int index) {
      return ((MetricStatusBuilder)this.currentMetrics.get(index)).build();
   }

   public MetricStatus buildFirstCurrentMetric() {
      return ((MetricStatusBuilder)this.currentMetrics.get(0)).build();
   }

   public MetricStatus buildLastCurrentMetric() {
      return ((MetricStatusBuilder)this.currentMetrics.get(this.currentMetrics.size() - 1)).build();
   }

   public MetricStatus buildMatchingCurrentMetric(Predicate predicate) {
      for(MetricStatusBuilder item : this.currentMetrics) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingCurrentMetric(Predicate predicate) {
      for(MetricStatusBuilder item : this.currentMetrics) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public HorizontalPodAutoscalerStatusFluent withCurrentMetrics(List currentMetrics) {
      if (this.currentMetrics != null) {
         this._visitables.get("currentMetrics").clear();
      }

      if (currentMetrics != null) {
         this.currentMetrics = new ArrayList();

         for(MetricStatus item : currentMetrics) {
            this.addToCurrentMetrics(item);
         }
      } else {
         this.currentMetrics = null;
      }

      return this;
   }

   public HorizontalPodAutoscalerStatusFluent withCurrentMetrics(MetricStatus... currentMetrics) {
      if (this.currentMetrics != null) {
         this.currentMetrics.clear();
         this._visitables.remove("currentMetrics");
      }

      if (currentMetrics != null) {
         for(MetricStatus item : currentMetrics) {
            this.addToCurrentMetrics(item);
         }
      }

      return this;
   }

   public boolean hasCurrentMetrics() {
      return this.currentMetrics != null && !this.currentMetrics.isEmpty();
   }

   public CurrentMetricsNested addNewCurrentMetric() {
      return new CurrentMetricsNested(-1, (MetricStatus)null);
   }

   public CurrentMetricsNested addNewCurrentMetricLike(MetricStatus item) {
      return new CurrentMetricsNested(-1, item);
   }

   public CurrentMetricsNested setNewCurrentMetricLike(int index, MetricStatus item) {
      return new CurrentMetricsNested(index, item);
   }

   public CurrentMetricsNested editCurrentMetric(int index) {
      if (this.currentMetrics.size() <= index) {
         throw new RuntimeException("Can't edit currentMetrics. Index exceeds size.");
      } else {
         return this.setNewCurrentMetricLike(index, this.buildCurrentMetric(index));
      }
   }

   public CurrentMetricsNested editFirstCurrentMetric() {
      if (this.currentMetrics.size() == 0) {
         throw new RuntimeException("Can't edit first currentMetrics. The list is empty.");
      } else {
         return this.setNewCurrentMetricLike(0, this.buildCurrentMetric(0));
      }
   }

   public CurrentMetricsNested editLastCurrentMetric() {
      int index = this.currentMetrics.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last currentMetrics. The list is empty.");
      } else {
         return this.setNewCurrentMetricLike(index, this.buildCurrentMetric(index));
      }
   }

   public CurrentMetricsNested editMatchingCurrentMetric(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.currentMetrics.size(); ++i) {
         if (predicate.test((MetricStatusBuilder)this.currentMetrics.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching currentMetrics. No match found.");
      } else {
         return this.setNewCurrentMetricLike(index, this.buildCurrentMetric(index));
      }
   }

   public Integer getCurrentReplicas() {
      return this.currentReplicas;
   }

   public HorizontalPodAutoscalerStatusFluent withCurrentReplicas(Integer currentReplicas) {
      this.currentReplicas = currentReplicas;
      return this;
   }

   public boolean hasCurrentReplicas() {
      return this.currentReplicas != null;
   }

   public Integer getDesiredReplicas() {
      return this.desiredReplicas;
   }

   public HorizontalPodAutoscalerStatusFluent withDesiredReplicas(Integer desiredReplicas) {
      this.desiredReplicas = desiredReplicas;
      return this;
   }

   public boolean hasDesiredReplicas() {
      return this.desiredReplicas != null;
   }

   public String getLastScaleTime() {
      return this.lastScaleTime;
   }

   public HorizontalPodAutoscalerStatusFluent withLastScaleTime(String lastScaleTime) {
      this.lastScaleTime = lastScaleTime;
      return this;
   }

   public boolean hasLastScaleTime() {
      return this.lastScaleTime != null;
   }

   public Long getObservedGeneration() {
      return this.observedGeneration;
   }

   public HorizontalPodAutoscalerStatusFluent withObservedGeneration(Long observedGeneration) {
      this.observedGeneration = observedGeneration;
      return this;
   }

   public boolean hasObservedGeneration() {
      return this.observedGeneration != null;
   }

   public HorizontalPodAutoscalerStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public HorizontalPodAutoscalerStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public HorizontalPodAutoscalerStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public HorizontalPodAutoscalerStatusFluent removeFromAdditionalProperties(Map map) {
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

   public HorizontalPodAutoscalerStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            HorizontalPodAutoscalerStatusFluent that = (HorizontalPodAutoscalerStatusFluent)o;
            if (!Objects.equals(this.conditions, that.conditions)) {
               return false;
            } else if (!Objects.equals(this.currentMetrics, that.currentMetrics)) {
               return false;
            } else if (!Objects.equals(this.currentReplicas, that.currentReplicas)) {
               return false;
            } else if (!Objects.equals(this.desiredReplicas, that.desiredReplicas)) {
               return false;
            } else if (!Objects.equals(this.lastScaleTime, that.lastScaleTime)) {
               return false;
            } else if (!Objects.equals(this.observedGeneration, that.observedGeneration)) {
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
      return Objects.hash(new Object[]{this.conditions, this.currentMetrics, this.currentReplicas, this.desiredReplicas, this.lastScaleTime, this.observedGeneration, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.conditions != null && !this.conditions.isEmpty()) {
         sb.append("conditions:");
         sb.append(this.conditions + ",");
      }

      if (this.currentMetrics != null && !this.currentMetrics.isEmpty()) {
         sb.append("currentMetrics:");
         sb.append(this.currentMetrics + ",");
      }

      if (this.currentReplicas != null) {
         sb.append("currentReplicas:");
         sb.append(this.currentReplicas + ",");
      }

      if (this.desiredReplicas != null) {
         sb.append("desiredReplicas:");
         sb.append(this.desiredReplicas + ",");
      }

      if (this.lastScaleTime != null) {
         sb.append("lastScaleTime:");
         sb.append(this.lastScaleTime + ",");
      }

      if (this.observedGeneration != null) {
         sb.append("observedGeneration:");
         sb.append(this.observedGeneration + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ConditionsNested extends HorizontalPodAutoscalerConditionFluent implements Nested {
      HorizontalPodAutoscalerConditionBuilder builder;
      int index;

      ConditionsNested(int index, HorizontalPodAutoscalerCondition item) {
         this.index = index;
         this.builder = new HorizontalPodAutoscalerConditionBuilder(this, item);
      }

      public Object and() {
         return HorizontalPodAutoscalerStatusFluent.this.setToConditions(this.index, this.builder.build());
      }

      public Object endCondition() {
         return this.and();
      }
   }

   public class CurrentMetricsNested extends MetricStatusFluent implements Nested {
      MetricStatusBuilder builder;
      int index;

      CurrentMetricsNested(int index, MetricStatus item) {
         this.index = index;
         this.builder = new MetricStatusBuilder(this, item);
      }

      public Object and() {
         return HorizontalPodAutoscalerStatusFluent.this.setToCurrentMetrics(this.index, this.builder.build());
      }

      public Object endCurrentMetric() {
         return this.and();
      }
   }
}
