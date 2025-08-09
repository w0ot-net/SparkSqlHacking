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

public class PodsMetricStatusFluent extends BaseFluent {
   private Quantity currentAverageValue;
   private String metricName;
   private LabelSelectorBuilder selector;
   private Map additionalProperties;

   public PodsMetricStatusFluent() {
   }

   public PodsMetricStatusFluent(PodsMetricStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PodsMetricStatus instance) {
      instance = instance != null ? instance : new PodsMetricStatus();
      if (instance != null) {
         this.withCurrentAverageValue(instance.getCurrentAverageValue());
         this.withMetricName(instance.getMetricName());
         this.withSelector(instance.getSelector());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Quantity getCurrentAverageValue() {
      return this.currentAverageValue;
   }

   public PodsMetricStatusFluent withCurrentAverageValue(Quantity currentAverageValue) {
      this.currentAverageValue = currentAverageValue;
      return this;
   }

   public boolean hasCurrentAverageValue() {
      return this.currentAverageValue != null;
   }

   public PodsMetricStatusFluent withNewCurrentAverageValue(String amount, String format) {
      return this.withCurrentAverageValue(new Quantity(amount, format));
   }

   public PodsMetricStatusFluent withNewCurrentAverageValue(String amount) {
      return this.withCurrentAverageValue(new Quantity(amount));
   }

   public String getMetricName() {
      return this.metricName;
   }

   public PodsMetricStatusFluent withMetricName(String metricName) {
      this.metricName = metricName;
      return this;
   }

   public boolean hasMetricName() {
      return this.metricName != null;
   }

   public LabelSelector buildSelector() {
      return this.selector != null ? this.selector.build() : null;
   }

   public PodsMetricStatusFluent withSelector(LabelSelector selector) {
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

   public PodsMetricStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PodsMetricStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PodsMetricStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PodsMetricStatusFluent removeFromAdditionalProperties(Map map) {
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

   public PodsMetricStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            PodsMetricStatusFluent that = (PodsMetricStatusFluent)o;
            if (!Objects.equals(this.currentAverageValue, that.currentAverageValue)) {
               return false;
            } else if (!Objects.equals(this.metricName, that.metricName)) {
               return false;
            } else if (!Objects.equals(this.selector, that.selector)) {
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
      return Objects.hash(new Object[]{this.currentAverageValue, this.metricName, this.selector, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.currentAverageValue != null) {
         sb.append("currentAverageValue:");
         sb.append(this.currentAverageValue + ",");
      }

      if (this.metricName != null) {
         sb.append("metricName:");
         sb.append(this.metricName + ",");
      }

      if (this.selector != null) {
         sb.append("selector:");
         sb.append(this.selector + ",");
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
         return PodsMetricStatusFluent.this.withSelector(this.builder.build());
      }

      public Object endSelector() {
         return this.and();
      }
   }
}
