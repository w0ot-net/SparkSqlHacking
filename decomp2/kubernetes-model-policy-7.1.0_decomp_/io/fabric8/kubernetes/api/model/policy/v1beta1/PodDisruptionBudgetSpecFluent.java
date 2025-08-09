package io.fabric8.kubernetes.api.model.policy.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.IntOrString;
import io.fabric8.kubernetes.api.model.IntOrStringBuilder;
import io.fabric8.kubernetes.api.model.IntOrStringFluent;
import io.fabric8.kubernetes.api.model.LabelSelector;
import io.fabric8.kubernetes.api.model.LabelSelectorBuilder;
import io.fabric8.kubernetes.api.model.LabelSelectorFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class PodDisruptionBudgetSpecFluent extends BaseFluent {
   private IntOrStringBuilder maxUnavailable;
   private IntOrStringBuilder minAvailable;
   private LabelSelectorBuilder selector;
   private Map additionalProperties;

   public PodDisruptionBudgetSpecFluent() {
   }

   public PodDisruptionBudgetSpecFluent(PodDisruptionBudgetSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PodDisruptionBudgetSpec instance) {
      instance = instance != null ? instance : new PodDisruptionBudgetSpec();
      if (instance != null) {
         this.withMaxUnavailable(instance.getMaxUnavailable());
         this.withMinAvailable(instance.getMinAvailable());
         this.withSelector(instance.getSelector());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public IntOrString buildMaxUnavailable() {
      return this.maxUnavailable != null ? this.maxUnavailable.build() : null;
   }

   public PodDisruptionBudgetSpecFluent withMaxUnavailable(IntOrString maxUnavailable) {
      this._visitables.remove("maxUnavailable");
      if (maxUnavailable != null) {
         this.maxUnavailable = new IntOrStringBuilder(maxUnavailable);
         this._visitables.get("maxUnavailable").add(this.maxUnavailable);
      } else {
         this.maxUnavailable = null;
         this._visitables.get("maxUnavailable").remove(this.maxUnavailable);
      }

      return this;
   }

   public boolean hasMaxUnavailable() {
      return this.maxUnavailable != null;
   }

   public PodDisruptionBudgetSpecFluent withNewMaxUnavailable(Object value) {
      return this.withMaxUnavailable(new IntOrString(value));
   }

   public MaxUnavailableNested withNewMaxUnavailable() {
      return new MaxUnavailableNested((IntOrString)null);
   }

   public MaxUnavailableNested withNewMaxUnavailableLike(IntOrString item) {
      return new MaxUnavailableNested(item);
   }

   public MaxUnavailableNested editMaxUnavailable() {
      return this.withNewMaxUnavailableLike((IntOrString)Optional.ofNullable(this.buildMaxUnavailable()).orElse((Object)null));
   }

   public MaxUnavailableNested editOrNewMaxUnavailable() {
      return this.withNewMaxUnavailableLike((IntOrString)Optional.ofNullable(this.buildMaxUnavailable()).orElse((new IntOrStringBuilder()).build()));
   }

   public MaxUnavailableNested editOrNewMaxUnavailableLike(IntOrString item) {
      return this.withNewMaxUnavailableLike((IntOrString)Optional.ofNullable(this.buildMaxUnavailable()).orElse(item));
   }

   public IntOrString buildMinAvailable() {
      return this.minAvailable != null ? this.minAvailable.build() : null;
   }

   public PodDisruptionBudgetSpecFluent withMinAvailable(IntOrString minAvailable) {
      this._visitables.remove("minAvailable");
      if (minAvailable != null) {
         this.minAvailable = new IntOrStringBuilder(minAvailable);
         this._visitables.get("minAvailable").add(this.minAvailable);
      } else {
         this.minAvailable = null;
         this._visitables.get("minAvailable").remove(this.minAvailable);
      }

      return this;
   }

   public boolean hasMinAvailable() {
      return this.minAvailable != null;
   }

   public PodDisruptionBudgetSpecFluent withNewMinAvailable(Object value) {
      return this.withMinAvailable(new IntOrString(value));
   }

   public MinAvailableNested withNewMinAvailable() {
      return new MinAvailableNested((IntOrString)null);
   }

   public MinAvailableNested withNewMinAvailableLike(IntOrString item) {
      return new MinAvailableNested(item);
   }

   public MinAvailableNested editMinAvailable() {
      return this.withNewMinAvailableLike((IntOrString)Optional.ofNullable(this.buildMinAvailable()).orElse((Object)null));
   }

   public MinAvailableNested editOrNewMinAvailable() {
      return this.withNewMinAvailableLike((IntOrString)Optional.ofNullable(this.buildMinAvailable()).orElse((new IntOrStringBuilder()).build()));
   }

   public MinAvailableNested editOrNewMinAvailableLike(IntOrString item) {
      return this.withNewMinAvailableLike((IntOrString)Optional.ofNullable(this.buildMinAvailable()).orElse(item));
   }

   public LabelSelector buildSelector() {
      return this.selector != null ? this.selector.build() : null;
   }

   public PodDisruptionBudgetSpecFluent withSelector(LabelSelector selector) {
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

   public PodDisruptionBudgetSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PodDisruptionBudgetSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PodDisruptionBudgetSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PodDisruptionBudgetSpecFluent removeFromAdditionalProperties(Map map) {
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

   public PodDisruptionBudgetSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            PodDisruptionBudgetSpecFluent that = (PodDisruptionBudgetSpecFluent)o;
            if (!Objects.equals(this.maxUnavailable, that.maxUnavailable)) {
               return false;
            } else if (!Objects.equals(this.minAvailable, that.minAvailable)) {
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
      return Objects.hash(new Object[]{this.maxUnavailable, this.minAvailable, this.selector, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.maxUnavailable != null) {
         sb.append("maxUnavailable:");
         sb.append(this.maxUnavailable + ",");
      }

      if (this.minAvailable != null) {
         sb.append("minAvailable:");
         sb.append(this.minAvailable + ",");
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

   public class MaxUnavailableNested extends IntOrStringFluent implements Nested {
      IntOrStringBuilder builder;

      MaxUnavailableNested(IntOrString item) {
         this.builder = new IntOrStringBuilder(this, item);
      }

      public Object and() {
         return PodDisruptionBudgetSpecFluent.this.withMaxUnavailable(this.builder.build());
      }

      public Object endMaxUnavailable() {
         return this.and();
      }
   }

   public class MinAvailableNested extends IntOrStringFluent implements Nested {
      IntOrStringBuilder builder;

      MinAvailableNested(IntOrString item) {
         this.builder = new IntOrStringBuilder(this, item);
      }

      public Object and() {
         return PodDisruptionBudgetSpecFluent.this.withMinAvailable(this.builder.build());
      }

      public Object endMinAvailable() {
         return this.and();
      }
   }

   public class SelectorNested extends LabelSelectorFluent implements Nested {
      LabelSelectorBuilder builder;

      SelectorNested(LabelSelector item) {
         this.builder = new LabelSelectorBuilder(this, item);
      }

      public Object and() {
         return PodDisruptionBudgetSpecFluent.this.withSelector(this.builder.build());
      }

      public Object endSelector() {
         return this.and();
      }
   }
}
