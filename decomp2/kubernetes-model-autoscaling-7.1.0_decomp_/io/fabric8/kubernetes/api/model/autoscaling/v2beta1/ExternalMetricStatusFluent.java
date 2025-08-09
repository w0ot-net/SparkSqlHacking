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

public class ExternalMetricStatusFluent extends BaseFluent {
   private Quantity currentAverageValue;
   private Quantity currentValue;
   private String metricName;
   private LabelSelectorBuilder metricSelector;
   private Map additionalProperties;

   public ExternalMetricStatusFluent() {
   }

   public ExternalMetricStatusFluent(ExternalMetricStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ExternalMetricStatus instance) {
      instance = instance != null ? instance : new ExternalMetricStatus();
      if (instance != null) {
         this.withCurrentAverageValue(instance.getCurrentAverageValue());
         this.withCurrentValue(instance.getCurrentValue());
         this.withMetricName(instance.getMetricName());
         this.withMetricSelector(instance.getMetricSelector());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Quantity getCurrentAverageValue() {
      return this.currentAverageValue;
   }

   public ExternalMetricStatusFluent withCurrentAverageValue(Quantity currentAverageValue) {
      this.currentAverageValue = currentAverageValue;
      return this;
   }

   public boolean hasCurrentAverageValue() {
      return this.currentAverageValue != null;
   }

   public ExternalMetricStatusFluent withNewCurrentAverageValue(String amount, String format) {
      return this.withCurrentAverageValue(new Quantity(amount, format));
   }

   public ExternalMetricStatusFluent withNewCurrentAverageValue(String amount) {
      return this.withCurrentAverageValue(new Quantity(amount));
   }

   public Quantity getCurrentValue() {
      return this.currentValue;
   }

   public ExternalMetricStatusFluent withCurrentValue(Quantity currentValue) {
      this.currentValue = currentValue;
      return this;
   }

   public boolean hasCurrentValue() {
      return this.currentValue != null;
   }

   public ExternalMetricStatusFluent withNewCurrentValue(String amount, String format) {
      return this.withCurrentValue(new Quantity(amount, format));
   }

   public ExternalMetricStatusFluent withNewCurrentValue(String amount) {
      return this.withCurrentValue(new Quantity(amount));
   }

   public String getMetricName() {
      return this.metricName;
   }

   public ExternalMetricStatusFluent withMetricName(String metricName) {
      this.metricName = metricName;
      return this;
   }

   public boolean hasMetricName() {
      return this.metricName != null;
   }

   public LabelSelector buildMetricSelector() {
      return this.metricSelector != null ? this.metricSelector.build() : null;
   }

   public ExternalMetricStatusFluent withMetricSelector(LabelSelector metricSelector) {
      this._visitables.remove("metricSelector");
      if (metricSelector != null) {
         this.metricSelector = new LabelSelectorBuilder(metricSelector);
         this._visitables.get("metricSelector").add(this.metricSelector);
      } else {
         this.metricSelector = null;
         this._visitables.get("metricSelector").remove(this.metricSelector);
      }

      return this;
   }

   public boolean hasMetricSelector() {
      return this.metricSelector != null;
   }

   public MetricSelectorNested withNewMetricSelector() {
      return new MetricSelectorNested((LabelSelector)null);
   }

   public MetricSelectorNested withNewMetricSelectorLike(LabelSelector item) {
      return new MetricSelectorNested(item);
   }

   public MetricSelectorNested editMetricSelector() {
      return this.withNewMetricSelectorLike((LabelSelector)Optional.ofNullable(this.buildMetricSelector()).orElse((Object)null));
   }

   public MetricSelectorNested editOrNewMetricSelector() {
      return this.withNewMetricSelectorLike((LabelSelector)Optional.ofNullable(this.buildMetricSelector()).orElse((new LabelSelectorBuilder()).build()));
   }

   public MetricSelectorNested editOrNewMetricSelectorLike(LabelSelector item) {
      return this.withNewMetricSelectorLike((LabelSelector)Optional.ofNullable(this.buildMetricSelector()).orElse(item));
   }

   public ExternalMetricStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ExternalMetricStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ExternalMetricStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ExternalMetricStatusFluent removeFromAdditionalProperties(Map map) {
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

   public ExternalMetricStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            ExternalMetricStatusFluent that = (ExternalMetricStatusFluent)o;
            if (!Objects.equals(this.currentAverageValue, that.currentAverageValue)) {
               return false;
            } else if (!Objects.equals(this.currentValue, that.currentValue)) {
               return false;
            } else if (!Objects.equals(this.metricName, that.metricName)) {
               return false;
            } else if (!Objects.equals(this.metricSelector, that.metricSelector)) {
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
      return Objects.hash(new Object[]{this.currentAverageValue, this.currentValue, this.metricName, this.metricSelector, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.currentAverageValue != null) {
         sb.append("currentAverageValue:");
         sb.append(this.currentAverageValue + ",");
      }

      if (this.currentValue != null) {
         sb.append("currentValue:");
         sb.append(this.currentValue + ",");
      }

      if (this.metricName != null) {
         sb.append("metricName:");
         sb.append(this.metricName + ",");
      }

      if (this.metricSelector != null) {
         sb.append("metricSelector:");
         sb.append(this.metricSelector + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class MetricSelectorNested extends LabelSelectorFluent implements Nested {
      LabelSelectorBuilder builder;

      MetricSelectorNested(LabelSelector item) {
         this.builder = new LabelSelectorBuilder(this, item);
      }

      public Object and() {
         return ExternalMetricStatusFluent.this.withMetricSelector(this.builder.build());
      }

      public Object endMetricSelector() {
         return this.and();
      }
   }
}
