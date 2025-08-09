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

public class PodsMetricSourceFluent extends BaseFluent {
   private String metricName;
   private LabelSelectorBuilder selector;
   private Quantity targetAverageValue;
   private Map additionalProperties;

   public PodsMetricSourceFluent() {
   }

   public PodsMetricSourceFluent(PodsMetricSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PodsMetricSource instance) {
      instance = instance != null ? instance : new PodsMetricSource();
      if (instance != null) {
         this.withMetricName(instance.getMetricName());
         this.withSelector(instance.getSelector());
         this.withTargetAverageValue(instance.getTargetAverageValue());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getMetricName() {
      return this.metricName;
   }

   public PodsMetricSourceFluent withMetricName(String metricName) {
      this.metricName = metricName;
      return this;
   }

   public boolean hasMetricName() {
      return this.metricName != null;
   }

   public LabelSelector buildSelector() {
      return this.selector != null ? this.selector.build() : null;
   }

   public PodsMetricSourceFluent withSelector(LabelSelector selector) {
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

   public Quantity getTargetAverageValue() {
      return this.targetAverageValue;
   }

   public PodsMetricSourceFluent withTargetAverageValue(Quantity targetAverageValue) {
      this.targetAverageValue = targetAverageValue;
      return this;
   }

   public boolean hasTargetAverageValue() {
      return this.targetAverageValue != null;
   }

   public PodsMetricSourceFluent withNewTargetAverageValue(String amount, String format) {
      return this.withTargetAverageValue(new Quantity(amount, format));
   }

   public PodsMetricSourceFluent withNewTargetAverageValue(String amount) {
      return this.withTargetAverageValue(new Quantity(amount));
   }

   public PodsMetricSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PodsMetricSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PodsMetricSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PodsMetricSourceFluent removeFromAdditionalProperties(Map map) {
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

   public PodsMetricSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            PodsMetricSourceFluent that = (PodsMetricSourceFluent)o;
            if (!Objects.equals(this.metricName, that.metricName)) {
               return false;
            } else if (!Objects.equals(this.selector, that.selector)) {
               return false;
            } else if (!Objects.equals(this.targetAverageValue, that.targetAverageValue)) {
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
      return Objects.hash(new Object[]{this.metricName, this.selector, this.targetAverageValue, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.metricName != null) {
         sb.append("metricName:");
         sb.append(this.metricName + ",");
      }

      if (this.selector != null) {
         sb.append("selector:");
         sb.append(this.selector + ",");
      }

      if (this.targetAverageValue != null) {
         sb.append("targetAverageValue:");
         sb.append(this.targetAverageValue + ",");
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
         return PodsMetricSourceFluent.this.withSelector(this.builder.build());
      }

      public Object endSelector() {
         return this.and();
      }
   }
}
