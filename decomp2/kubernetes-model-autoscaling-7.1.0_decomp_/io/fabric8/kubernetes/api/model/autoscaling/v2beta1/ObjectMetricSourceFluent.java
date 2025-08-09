package io.fabric8.kubernetes.api.model.autoscaling.v2beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.LabelSelector;
import io.fabric8.kubernetes.api.model.LabelSelectorBuilder;
import io.fabric8.kubernetes.api.model.LabelSelectorFluent;
import io.fabric8.kubernetes.api.model.Quantity;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ObjectMetricSourceFluent extends BaseFluent {
   private Quantity averageValue;
   private String metricName;
   private LabelSelectorBuilder selector;
   private CrossVersionObjectReferenceBuilder target;
   private Quantity targetValue;
   private Map additionalProperties;

   public ObjectMetricSourceFluent() {
   }

   public ObjectMetricSourceFluent(ObjectMetricSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ObjectMetricSource instance) {
      instance = instance != null ? instance : new ObjectMetricSource();
      if (instance != null) {
         this.withAverageValue(instance.getAverageValue());
         this.withMetricName(instance.getMetricName());
         this.withSelector(instance.getSelector());
         this.withTarget(instance.getTarget());
         this.withTargetValue(instance.getTargetValue());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Quantity getAverageValue() {
      return this.averageValue;
   }

   public ObjectMetricSourceFluent withAverageValue(Quantity averageValue) {
      this.averageValue = averageValue;
      return this;
   }

   public boolean hasAverageValue() {
      return this.averageValue != null;
   }

   public ObjectMetricSourceFluent withNewAverageValue(String amount, String format) {
      return this.withAverageValue(new Quantity(amount, format));
   }

   public ObjectMetricSourceFluent withNewAverageValue(String amount) {
      return this.withAverageValue(new Quantity(amount));
   }

   public String getMetricName() {
      return this.metricName;
   }

   public ObjectMetricSourceFluent withMetricName(String metricName) {
      this.metricName = metricName;
      return this;
   }

   public boolean hasMetricName() {
      return this.metricName != null;
   }

   public LabelSelector buildSelector() {
      return this.selector != null ? this.selector.build() : null;
   }

   public ObjectMetricSourceFluent withSelector(LabelSelector selector) {
      this._visitables.remove("selector");
      if (selector != null) {
         this.selector = new LabelSelectorBuilder(selector);
         this._visitables.get("selector").add(this.selector);
      } else {
         this.selector = null;
         this._visitables.get("selector").remove(this.selector);
      }

      return this;
   }

   public boolean hasSelector() {
      return this.selector != null;
   }

   public SelectorNested withNewSelector() {
      return new SelectorNested((LabelSelector)null);
   }

   public SelectorNested withNewSelectorLike(LabelSelector item) {
      return new SelectorNested(item);
   }

   public SelectorNested editSelector() {
      return this.withNewSelectorLike((LabelSelector)Optional.ofNullable(this.buildSelector()).orElse((Object)null));
   }

   public SelectorNested editOrNewSelector() {
      return this.withNewSelectorLike((LabelSelector)Optional.ofNullable(this.buildSelector()).orElse((new LabelSelectorBuilder()).build()));
   }

   public SelectorNested editOrNewSelectorLike(LabelSelector item) {
      return this.withNewSelectorLike((LabelSelector)Optional.ofNullable(this.buildSelector()).orElse(item));
   }

   public CrossVersionObjectReference buildTarget() {
      return this.target != null ? this.target.build() : null;
   }

   public ObjectMetricSourceFluent withTarget(CrossVersionObjectReference target) {
      this._visitables.remove("target");
      if (target != null) {
         this.target = new CrossVersionObjectReferenceBuilder(target);
         this._visitables.get("target").add(this.target);
      } else {
         this.target = null;
         this._visitables.get("target").remove(this.target);
      }

      return this;
   }

   public boolean hasTarget() {
      return this.target != null;
   }

   public ObjectMetricSourceFluent withNewTarget(String apiVersion, String kind, String name) {
      return this.withTarget(new CrossVersionObjectReference(apiVersion, kind, name));
   }

   public TargetNested withNewTarget() {
      return new TargetNested((CrossVersionObjectReference)null);
   }

   public TargetNested withNewTargetLike(CrossVersionObjectReference item) {
      return new TargetNested(item);
   }

   public TargetNested editTarget() {
      return this.withNewTargetLike((CrossVersionObjectReference)Optional.ofNullable(this.buildTarget()).orElse((Object)null));
   }

   public TargetNested editOrNewTarget() {
      return this.withNewTargetLike((CrossVersionObjectReference)Optional.ofNullable(this.buildTarget()).orElse((new CrossVersionObjectReferenceBuilder()).build()));
   }

   public TargetNested editOrNewTargetLike(CrossVersionObjectReference item) {
      return this.withNewTargetLike((CrossVersionObjectReference)Optional.ofNullable(this.buildTarget()).orElse(item));
   }

   public Quantity getTargetValue() {
      return this.targetValue;
   }

   public ObjectMetricSourceFluent withTargetValue(Quantity targetValue) {
      this.targetValue = targetValue;
      return this;
   }

   public boolean hasTargetValue() {
      return this.targetValue != null;
   }

   public ObjectMetricSourceFluent withNewTargetValue(String amount, String format) {
      return this.withTargetValue(new Quantity(amount, format));
   }

   public ObjectMetricSourceFluent withNewTargetValue(String amount) {
      return this.withTargetValue(new Quantity(amount));
   }

   public ObjectMetricSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ObjectMetricSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ObjectMetricSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ObjectMetricSourceFluent removeFromAdditionalProperties(Map map) {
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

   public ObjectMetricSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            ObjectMetricSourceFluent that = (ObjectMetricSourceFluent)o;
            if (!Objects.equals(this.averageValue, that.averageValue)) {
               return false;
            } else if (!Objects.equals(this.metricName, that.metricName)) {
               return false;
            } else if (!Objects.equals(this.selector, that.selector)) {
               return false;
            } else if (!Objects.equals(this.target, that.target)) {
               return false;
            } else if (!Objects.equals(this.targetValue, that.targetValue)) {
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
      return Objects.hash(new Object[]{this.averageValue, this.metricName, this.selector, this.target, this.targetValue, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.averageValue != null) {
         sb.append("averageValue:");
         sb.append(this.averageValue + ",");
      }

      if (this.metricName != null) {
         sb.append("metricName:");
         sb.append(this.metricName + ",");
      }

      if (this.selector != null) {
         sb.append("selector:");
         sb.append(this.selector + ",");
      }

      if (this.target != null) {
         sb.append("target:");
         sb.append(this.target + ",");
      }

      if (this.targetValue != null) {
         sb.append("targetValue:");
         sb.append(this.targetValue + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class SelectorNested extends LabelSelectorFluent implements Nested {
      LabelSelectorBuilder builder;

      SelectorNested(LabelSelector item) {
         this.builder = new LabelSelectorBuilder(this, item);
      }

      public Object and() {
         return ObjectMetricSourceFluent.this.withSelector(this.builder.build());
      }

      public Object endSelector() {
         return this.and();
      }
   }

   public class TargetNested extends CrossVersionObjectReferenceFluent implements Nested {
      CrossVersionObjectReferenceBuilder builder;

      TargetNested(CrossVersionObjectReference item) {
         this.builder = new CrossVersionObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return ObjectMetricSourceFluent.this.withTarget(this.builder.build());
      }

      public Object endTarget() {
         return this.and();
      }
   }
}
