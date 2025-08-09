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
import java.util.Optional;
import java.util.function.Predicate;

public class HorizontalPodAutoscalerSpecFluent extends BaseFluent {
   private HorizontalPodAutoscalerBehaviorBuilder behavior;
   private Integer maxReplicas;
   private ArrayList metrics = new ArrayList();
   private Integer minReplicas;
   private CrossVersionObjectReferenceBuilder scaleTargetRef;
   private Map additionalProperties;

   public HorizontalPodAutoscalerSpecFluent() {
   }

   public HorizontalPodAutoscalerSpecFluent(HorizontalPodAutoscalerSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(HorizontalPodAutoscalerSpec instance) {
      instance = instance != null ? instance : new HorizontalPodAutoscalerSpec();
      if (instance != null) {
         this.withBehavior(instance.getBehavior());
         this.withMaxReplicas(instance.getMaxReplicas());
         this.withMetrics(instance.getMetrics());
         this.withMinReplicas(instance.getMinReplicas());
         this.withScaleTargetRef(instance.getScaleTargetRef());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public HorizontalPodAutoscalerBehavior buildBehavior() {
      return this.behavior != null ? this.behavior.build() : null;
   }

   public HorizontalPodAutoscalerSpecFluent withBehavior(HorizontalPodAutoscalerBehavior behavior) {
      this._visitables.remove("behavior");
      if (behavior != null) {
         this.behavior = new HorizontalPodAutoscalerBehaviorBuilder(behavior);
         this._visitables.get("behavior").add(this.behavior);
      } else {
         this.behavior = null;
         this._visitables.get("behavior").remove(this.behavior);
      }

      return this;
   }

   public boolean hasBehavior() {
      return this.behavior != null;
   }

   public BehaviorNested withNewBehavior() {
      return new BehaviorNested((HorizontalPodAutoscalerBehavior)null);
   }

   public BehaviorNested withNewBehaviorLike(HorizontalPodAutoscalerBehavior item) {
      return new BehaviorNested(item);
   }

   public BehaviorNested editBehavior() {
      return this.withNewBehaviorLike((HorizontalPodAutoscalerBehavior)Optional.ofNullable(this.buildBehavior()).orElse((Object)null));
   }

   public BehaviorNested editOrNewBehavior() {
      return this.withNewBehaviorLike((HorizontalPodAutoscalerBehavior)Optional.ofNullable(this.buildBehavior()).orElse((new HorizontalPodAutoscalerBehaviorBuilder()).build()));
   }

   public BehaviorNested editOrNewBehaviorLike(HorizontalPodAutoscalerBehavior item) {
      return this.withNewBehaviorLike((HorizontalPodAutoscalerBehavior)Optional.ofNullable(this.buildBehavior()).orElse(item));
   }

   public Integer getMaxReplicas() {
      return this.maxReplicas;
   }

   public HorizontalPodAutoscalerSpecFluent withMaxReplicas(Integer maxReplicas) {
      this.maxReplicas = maxReplicas;
      return this;
   }

   public boolean hasMaxReplicas() {
      return this.maxReplicas != null;
   }

   public HorizontalPodAutoscalerSpecFluent addToMetrics(int index, MetricSpec item) {
      if (this.metrics == null) {
         this.metrics = new ArrayList();
      }

      MetricSpecBuilder builder = new MetricSpecBuilder(item);
      if (index >= 0 && index < this.metrics.size()) {
         this._visitables.get("metrics").add(index, builder);
         this.metrics.add(index, builder);
      } else {
         this._visitables.get("metrics").add(builder);
         this.metrics.add(builder);
      }

      return this;
   }

   public HorizontalPodAutoscalerSpecFluent setToMetrics(int index, MetricSpec item) {
      if (this.metrics == null) {
         this.metrics = new ArrayList();
      }

      MetricSpecBuilder builder = new MetricSpecBuilder(item);
      if (index >= 0 && index < this.metrics.size()) {
         this._visitables.get("metrics").set(index, builder);
         this.metrics.set(index, builder);
      } else {
         this._visitables.get("metrics").add(builder);
         this.metrics.add(builder);
      }

      return this;
   }

   public HorizontalPodAutoscalerSpecFluent addToMetrics(MetricSpec... items) {
      if (this.metrics == null) {
         this.metrics = new ArrayList();
      }

      for(MetricSpec item : items) {
         MetricSpecBuilder builder = new MetricSpecBuilder(item);
         this._visitables.get("metrics").add(builder);
         this.metrics.add(builder);
      }

      return this;
   }

   public HorizontalPodAutoscalerSpecFluent addAllToMetrics(Collection items) {
      if (this.metrics == null) {
         this.metrics = new ArrayList();
      }

      for(MetricSpec item : items) {
         MetricSpecBuilder builder = new MetricSpecBuilder(item);
         this._visitables.get("metrics").add(builder);
         this.metrics.add(builder);
      }

      return this;
   }

   public HorizontalPodAutoscalerSpecFluent removeFromMetrics(MetricSpec... items) {
      if (this.metrics == null) {
         return this;
      } else {
         for(MetricSpec item : items) {
            MetricSpecBuilder builder = new MetricSpecBuilder(item);
            this._visitables.get("metrics").remove(builder);
            this.metrics.remove(builder);
         }

         return this;
      }
   }

   public HorizontalPodAutoscalerSpecFluent removeAllFromMetrics(Collection items) {
      if (this.metrics == null) {
         return this;
      } else {
         for(MetricSpec item : items) {
            MetricSpecBuilder builder = new MetricSpecBuilder(item);
            this._visitables.get("metrics").remove(builder);
            this.metrics.remove(builder);
         }

         return this;
      }
   }

   public HorizontalPodAutoscalerSpecFluent removeMatchingFromMetrics(Predicate predicate) {
      if (this.metrics == null) {
         return this;
      } else {
         Iterator<MetricSpecBuilder> each = this.metrics.iterator();
         List visitables = this._visitables.get("metrics");

         while(each.hasNext()) {
            MetricSpecBuilder builder = (MetricSpecBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildMetrics() {
      return this.metrics != null ? build(this.metrics) : null;
   }

   public MetricSpec buildMetric(int index) {
      return ((MetricSpecBuilder)this.metrics.get(index)).build();
   }

   public MetricSpec buildFirstMetric() {
      return ((MetricSpecBuilder)this.metrics.get(0)).build();
   }

   public MetricSpec buildLastMetric() {
      return ((MetricSpecBuilder)this.metrics.get(this.metrics.size() - 1)).build();
   }

   public MetricSpec buildMatchingMetric(Predicate predicate) {
      for(MetricSpecBuilder item : this.metrics) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingMetric(Predicate predicate) {
      for(MetricSpecBuilder item : this.metrics) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public HorizontalPodAutoscalerSpecFluent withMetrics(List metrics) {
      if (this.metrics != null) {
         this._visitables.get("metrics").clear();
      }

      if (metrics != null) {
         this.metrics = new ArrayList();

         for(MetricSpec item : metrics) {
            this.addToMetrics(item);
         }
      } else {
         this.metrics = null;
      }

      return this;
   }

   public HorizontalPodAutoscalerSpecFluent withMetrics(MetricSpec... metrics) {
      if (this.metrics != null) {
         this.metrics.clear();
         this._visitables.remove("metrics");
      }

      if (metrics != null) {
         for(MetricSpec item : metrics) {
            this.addToMetrics(item);
         }
      }

      return this;
   }

   public boolean hasMetrics() {
      return this.metrics != null && !this.metrics.isEmpty();
   }

   public MetricsNested addNewMetric() {
      return new MetricsNested(-1, (MetricSpec)null);
   }

   public MetricsNested addNewMetricLike(MetricSpec item) {
      return new MetricsNested(-1, item);
   }

   public MetricsNested setNewMetricLike(int index, MetricSpec item) {
      return new MetricsNested(index, item);
   }

   public MetricsNested editMetric(int index) {
      if (this.metrics.size() <= index) {
         throw new RuntimeException("Can't edit metrics. Index exceeds size.");
      } else {
         return this.setNewMetricLike(index, this.buildMetric(index));
      }
   }

   public MetricsNested editFirstMetric() {
      if (this.metrics.size() == 0) {
         throw new RuntimeException("Can't edit first metrics. The list is empty.");
      } else {
         return this.setNewMetricLike(0, this.buildMetric(0));
      }
   }

   public MetricsNested editLastMetric() {
      int index = this.metrics.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last metrics. The list is empty.");
      } else {
         return this.setNewMetricLike(index, this.buildMetric(index));
      }
   }

   public MetricsNested editMatchingMetric(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.metrics.size(); ++i) {
         if (predicate.test((MetricSpecBuilder)this.metrics.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching metrics. No match found.");
      } else {
         return this.setNewMetricLike(index, this.buildMetric(index));
      }
   }

   public Integer getMinReplicas() {
      return this.minReplicas;
   }

   public HorizontalPodAutoscalerSpecFluent withMinReplicas(Integer minReplicas) {
      this.minReplicas = minReplicas;
      return this;
   }

   public boolean hasMinReplicas() {
      return this.minReplicas != null;
   }

   public CrossVersionObjectReference buildScaleTargetRef() {
      return this.scaleTargetRef != null ? this.scaleTargetRef.build() : null;
   }

   public HorizontalPodAutoscalerSpecFluent withScaleTargetRef(CrossVersionObjectReference scaleTargetRef) {
      this._visitables.remove("scaleTargetRef");
      if (scaleTargetRef != null) {
         this.scaleTargetRef = new CrossVersionObjectReferenceBuilder(scaleTargetRef);
         this._visitables.get("scaleTargetRef").add(this.scaleTargetRef);
      } else {
         this.scaleTargetRef = null;
         this._visitables.get("scaleTargetRef").remove(this.scaleTargetRef);
      }

      return this;
   }

   public boolean hasScaleTargetRef() {
      return this.scaleTargetRef != null;
   }

   public HorizontalPodAutoscalerSpecFluent withNewScaleTargetRef(String apiVersion, String kind, String name) {
      return this.withScaleTargetRef(new CrossVersionObjectReference(apiVersion, kind, name));
   }

   public ScaleTargetRefNested withNewScaleTargetRef() {
      return new ScaleTargetRefNested((CrossVersionObjectReference)null);
   }

   public ScaleTargetRefNested withNewScaleTargetRefLike(CrossVersionObjectReference item) {
      return new ScaleTargetRefNested(item);
   }

   public ScaleTargetRefNested editScaleTargetRef() {
      return this.withNewScaleTargetRefLike((CrossVersionObjectReference)Optional.ofNullable(this.buildScaleTargetRef()).orElse((Object)null));
   }

   public ScaleTargetRefNested editOrNewScaleTargetRef() {
      return this.withNewScaleTargetRefLike((CrossVersionObjectReference)Optional.ofNullable(this.buildScaleTargetRef()).orElse((new CrossVersionObjectReferenceBuilder()).build()));
   }

   public ScaleTargetRefNested editOrNewScaleTargetRefLike(CrossVersionObjectReference item) {
      return this.withNewScaleTargetRefLike((CrossVersionObjectReference)Optional.ofNullable(this.buildScaleTargetRef()).orElse(item));
   }

   public HorizontalPodAutoscalerSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public HorizontalPodAutoscalerSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public HorizontalPodAutoscalerSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public HorizontalPodAutoscalerSpecFluent removeFromAdditionalProperties(Map map) {
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

   public HorizontalPodAutoscalerSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            HorizontalPodAutoscalerSpecFluent that = (HorizontalPodAutoscalerSpecFluent)o;
            if (!Objects.equals(this.behavior, that.behavior)) {
               return false;
            } else if (!Objects.equals(this.maxReplicas, that.maxReplicas)) {
               return false;
            } else if (!Objects.equals(this.metrics, that.metrics)) {
               return false;
            } else if (!Objects.equals(this.minReplicas, that.minReplicas)) {
               return false;
            } else if (!Objects.equals(this.scaleTargetRef, that.scaleTargetRef)) {
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
      return Objects.hash(new Object[]{this.behavior, this.maxReplicas, this.metrics, this.minReplicas, this.scaleTargetRef, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.behavior != null) {
         sb.append("behavior:");
         sb.append(this.behavior + ",");
      }

      if (this.maxReplicas != null) {
         sb.append("maxReplicas:");
         sb.append(this.maxReplicas + ",");
      }

      if (this.metrics != null && !this.metrics.isEmpty()) {
         sb.append("metrics:");
         sb.append(this.metrics + ",");
      }

      if (this.minReplicas != null) {
         sb.append("minReplicas:");
         sb.append(this.minReplicas + ",");
      }

      if (this.scaleTargetRef != null) {
         sb.append("scaleTargetRef:");
         sb.append(this.scaleTargetRef + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class BehaviorNested extends HorizontalPodAutoscalerBehaviorFluent implements Nested {
      HorizontalPodAutoscalerBehaviorBuilder builder;

      BehaviorNested(HorizontalPodAutoscalerBehavior item) {
         this.builder = new HorizontalPodAutoscalerBehaviorBuilder(this, item);
      }

      public Object and() {
         return HorizontalPodAutoscalerSpecFluent.this.withBehavior(this.builder.build());
      }

      public Object endBehavior() {
         return this.and();
      }
   }

   public class MetricsNested extends MetricSpecFluent implements Nested {
      MetricSpecBuilder builder;
      int index;

      MetricsNested(int index, MetricSpec item) {
         this.index = index;
         this.builder = new MetricSpecBuilder(this, item);
      }

      public Object and() {
         return HorizontalPodAutoscalerSpecFluent.this.setToMetrics(this.index, this.builder.build());
      }

      public Object endMetric() {
         return this.and();
      }
   }

   public class ScaleTargetRefNested extends CrossVersionObjectReferenceFluent implements Nested {
      CrossVersionObjectReferenceBuilder builder;

      ScaleTargetRefNested(CrossVersionObjectReference item) {
         this.builder = new CrossVersionObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return HorizontalPodAutoscalerSpecFluent.this.withScaleTargetRef(this.builder.build());
      }

      public Object endScaleTargetRef() {
         return this.and();
      }
   }
}
