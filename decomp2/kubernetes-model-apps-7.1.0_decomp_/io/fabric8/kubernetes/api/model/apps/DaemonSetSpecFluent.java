package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.LabelSelector;
import io.fabric8.kubernetes.api.model.LabelSelectorBuilder;
import io.fabric8.kubernetes.api.model.LabelSelectorFluent;
import io.fabric8.kubernetes.api.model.PodTemplateSpec;
import io.fabric8.kubernetes.api.model.PodTemplateSpecBuilder;
import io.fabric8.kubernetes.api.model.PodTemplateSpecFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class DaemonSetSpecFluent extends BaseFluent {
   private Integer minReadySeconds;
   private Integer revisionHistoryLimit;
   private LabelSelectorBuilder selector;
   private PodTemplateSpecBuilder template;
   private DaemonSetUpdateStrategyBuilder updateStrategy;
   private Map additionalProperties;

   public DaemonSetSpecFluent() {
   }

   public DaemonSetSpecFluent(DaemonSetSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(DaemonSetSpec instance) {
      instance = instance != null ? instance : new DaemonSetSpec();
      if (instance != null) {
         this.withMinReadySeconds(instance.getMinReadySeconds());
         this.withRevisionHistoryLimit(instance.getRevisionHistoryLimit());
         this.withSelector(instance.getSelector());
         this.withTemplate(instance.getTemplate());
         this.withUpdateStrategy(instance.getUpdateStrategy());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getMinReadySeconds() {
      return this.minReadySeconds;
   }

   public DaemonSetSpecFluent withMinReadySeconds(Integer minReadySeconds) {
      this.minReadySeconds = minReadySeconds;
      return this;
   }

   public boolean hasMinReadySeconds() {
      return this.minReadySeconds != null;
   }

   public Integer getRevisionHistoryLimit() {
      return this.revisionHistoryLimit;
   }

   public DaemonSetSpecFluent withRevisionHistoryLimit(Integer revisionHistoryLimit) {
      this.revisionHistoryLimit = revisionHistoryLimit;
      return this;
   }

   public boolean hasRevisionHistoryLimit() {
      return this.revisionHistoryLimit != null;
   }

   public LabelSelector buildSelector() {
      return this.selector != null ? this.selector.build() : null;
   }

   public DaemonSetSpecFluent withSelector(LabelSelector selector) {
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

   public PodTemplateSpec buildTemplate() {
      return this.template != null ? this.template.build() : null;
   }

   public DaemonSetSpecFluent withTemplate(PodTemplateSpec template) {
      this._visitables.remove("template");
      if (template != null) {
         this.template = new PodTemplateSpecBuilder(template);
         this._visitables.get("template").add(this.template);
      } else {
         this.template = null;
         this._visitables.get("template").remove(this.template);
      }

      return this;
   }

   public boolean hasTemplate() {
      return this.template != null;
   }

   public TemplateNested withNewTemplate() {
      return new TemplateNested((PodTemplateSpec)null);
   }

   public TemplateNested withNewTemplateLike(PodTemplateSpec item) {
      return new TemplateNested(item);
   }

   public TemplateNested editTemplate() {
      return this.withNewTemplateLike((PodTemplateSpec)Optional.ofNullable(this.buildTemplate()).orElse((Object)null));
   }

   public TemplateNested editOrNewTemplate() {
      return this.withNewTemplateLike((PodTemplateSpec)Optional.ofNullable(this.buildTemplate()).orElse((new PodTemplateSpecBuilder()).build()));
   }

   public TemplateNested editOrNewTemplateLike(PodTemplateSpec item) {
      return this.withNewTemplateLike((PodTemplateSpec)Optional.ofNullable(this.buildTemplate()).orElse(item));
   }

   public DaemonSetUpdateStrategy buildUpdateStrategy() {
      return this.updateStrategy != null ? this.updateStrategy.build() : null;
   }

   public DaemonSetSpecFluent withUpdateStrategy(DaemonSetUpdateStrategy updateStrategy) {
      this._visitables.remove("updateStrategy");
      if (updateStrategy != null) {
         this.updateStrategy = new DaemonSetUpdateStrategyBuilder(updateStrategy);
         this._visitables.get("updateStrategy").add(this.updateStrategy);
      } else {
         this.updateStrategy = null;
         this._visitables.get("updateStrategy").remove(this.updateStrategy);
      }

      return this;
   }

   public boolean hasUpdateStrategy() {
      return this.updateStrategy != null;
   }

   public UpdateStrategyNested withNewUpdateStrategy() {
      return new UpdateStrategyNested((DaemonSetUpdateStrategy)null);
   }

   public UpdateStrategyNested withNewUpdateStrategyLike(DaemonSetUpdateStrategy item) {
      return new UpdateStrategyNested(item);
   }

   public UpdateStrategyNested editUpdateStrategy() {
      return this.withNewUpdateStrategyLike((DaemonSetUpdateStrategy)Optional.ofNullable(this.buildUpdateStrategy()).orElse((Object)null));
   }

   public UpdateStrategyNested editOrNewUpdateStrategy() {
      return this.withNewUpdateStrategyLike((DaemonSetUpdateStrategy)Optional.ofNullable(this.buildUpdateStrategy()).orElse((new DaemonSetUpdateStrategyBuilder()).build()));
   }

   public UpdateStrategyNested editOrNewUpdateStrategyLike(DaemonSetUpdateStrategy item) {
      return this.withNewUpdateStrategyLike((DaemonSetUpdateStrategy)Optional.ofNullable(this.buildUpdateStrategy()).orElse(item));
   }

   public DaemonSetSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public DaemonSetSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public DaemonSetSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public DaemonSetSpecFluent removeFromAdditionalProperties(Map map) {
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

   public DaemonSetSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            DaemonSetSpecFluent that = (DaemonSetSpecFluent)o;
            if (!Objects.equals(this.minReadySeconds, that.minReadySeconds)) {
               return false;
            } else if (!Objects.equals(this.revisionHistoryLimit, that.revisionHistoryLimit)) {
               return false;
            } else if (!Objects.equals(this.selector, that.selector)) {
               return false;
            } else if (!Objects.equals(this.template, that.template)) {
               return false;
            } else if (!Objects.equals(this.updateStrategy, that.updateStrategy)) {
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
      return Objects.hash(new Object[]{this.minReadySeconds, this.revisionHistoryLimit, this.selector, this.template, this.updateStrategy, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.minReadySeconds != null) {
         sb.append("minReadySeconds:");
         sb.append(this.minReadySeconds + ",");
      }

      if (this.revisionHistoryLimit != null) {
         sb.append("revisionHistoryLimit:");
         sb.append(this.revisionHistoryLimit + ",");
      }

      if (this.selector != null) {
         sb.append("selector:");
         sb.append(this.selector + ",");
      }

      if (this.template != null) {
         sb.append("template:");
         sb.append(this.template + ",");
      }

      if (this.updateStrategy != null) {
         sb.append("updateStrategy:");
         sb.append(this.updateStrategy + ",");
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
         return DaemonSetSpecFluent.this.withSelector(this.builder.build());
      }

      public Object endSelector() {
         return this.and();
      }
   }

   public class TemplateNested extends PodTemplateSpecFluent implements Nested {
      PodTemplateSpecBuilder builder;

      TemplateNested(PodTemplateSpec item) {
         this.builder = new PodTemplateSpecBuilder(this, item);
      }

      public Object and() {
         return DaemonSetSpecFluent.this.withTemplate(this.builder.build());
      }

      public Object endTemplate() {
         return this.and();
      }
   }

   public class UpdateStrategyNested extends DaemonSetUpdateStrategyFluent implements Nested {
      DaemonSetUpdateStrategyBuilder builder;

      UpdateStrategyNested(DaemonSetUpdateStrategy item) {
         this.builder = new DaemonSetUpdateStrategyBuilder(this, item);
      }

      public Object and() {
         return DaemonSetSpecFluent.this.withUpdateStrategy(this.builder.build());
      }

      public Object endUpdateStrategy() {
         return this.and();
      }
   }
}
