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

public class ExternalMetricSourceFluent extends BaseFluent {
   private String metricName;
   private LabelSelectorBuilder metricSelector;
   private Quantity targetAverageValue;
   private Quantity targetValue;
   private Map additionalProperties;

   public ExternalMetricSourceFluent() {
   }

   public ExternalMetricSourceFluent(ExternalMetricSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ExternalMetricSource instance) {
      instance = instance != null ? instance : new ExternalMetricSource();
      if (instance != null) {
         this.withMetricName(instance.getMetricName());
         this.withMetricSelector(instance.getMetricSelector());
         this.withTargetAverageValue(instance.getTargetAverageValue());
         this.withTargetValue(instance.getTargetValue());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getMetricName() {
      return this.metricName;
   }

   public ExternalMetricSourceFluent withMetricName(String metricName) {
      this.metricName = metricName;
      return this;
   }

   public boolean hasMetricName() {
      return this.metricName != null;
   }

   public LabelSelector buildMetricSelector() {
      return this.metricSelector != null ? this.metricSelector.build() : null;
   }

   public ExternalMetricSourceFluent withMetricSelector(LabelSelector metricSelector) {
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

   public Quantity getTargetAverageValue() {
      return this.targetAverageValue;
   }

   public ExternalMetricSourceFluent withTargetAverageValue(Quantity targetAverageValue) {
      this.targetAverageValue = targetAverageValue;
      return this;
   }

   public boolean hasTargetAverageValue() {
      return this.targetAverageValue != null;
   }

   public ExternalMetricSourceFluent withNewTargetAverageValue(String amount, String format) {
      return this.withTargetAverageValue(new Quantity(amount, format));
   }

   public ExternalMetricSourceFluent withNewTargetAverageValue(String amount) {
      return this.withTargetAverageValue(new Quantity(amount));
   }

   public Quantity getTargetValue() {
      return this.targetValue;
   }

   public ExternalMetricSourceFluent withTargetValue(Quantity targetValue) {
      this.targetValue = targetValue;
      return this;
   }

   public boolean hasTargetValue() {
      return this.targetValue != null;
   }

   public ExternalMetricSourceFluent withNewTargetValue(String amount, String format) {
      return this.withTargetValue(new Quantity(amount, format));
   }

   public ExternalMetricSourceFluent withNewTargetValue(String amount) {
      return this.withTargetValue(new Quantity(amount));
   }

   public ExternalMetricSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ExternalMetricSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ExternalMetricSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ExternalMetricSourceFluent removeFromAdditionalProperties(Map map) {
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

   public ExternalMetricSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            ExternalMetricSourceFluent that = (ExternalMetricSourceFluent)o;
            if (!Objects.equals(this.metricName, that.metricName)) {
               return false;
            } else if (!Objects.equals(this.metricSelector, that.metricSelector)) {
               return false;
            } else if (!Objects.equals(this.targetAverageValue, that.targetAverageValue)) {
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
      return Objects.hash(new Object[]{this.metricName, this.metricSelector, this.targetAverageValue, this.targetValue, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.metricName != null) {
         sb.append("metricName:");
         sb.append(this.metricName + ",");
      }

      if (this.metricSelector != null) {
         sb.append("metricSelector:");
         sb.append(this.metricSelector + ",");
      }

      if (this.targetAverageValue != null) {
         sb.append("targetAverageValue:");
         sb.append(this.targetAverageValue + ",");
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

   public class MetricSelectorNested extends LabelSelectorFluent implements Nested {
      LabelSelectorBuilder builder;

      MetricSelectorNested(LabelSelector item) {
         this.builder = new LabelSelectorBuilder(this, item);
      }

      public Object and() {
         return ExternalMetricSourceFluent.this.withMetricSelector(this.builder.build());
      }

      public Object endMetricSelector() {
         return this.and();
      }
   }
}
