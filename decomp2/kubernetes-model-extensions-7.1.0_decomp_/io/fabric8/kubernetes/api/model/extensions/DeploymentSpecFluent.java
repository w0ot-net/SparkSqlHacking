package io.fabric8.kubernetes.api.model.extensions;

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

public class DeploymentSpecFluent extends BaseFluent {
   private Integer minReadySeconds;
   private Boolean paused;
   private Integer progressDeadlineSeconds;
   private Integer replicas;
   private Integer revisionHistoryLimit;
   private RollbackConfigBuilder rollbackTo;
   private LabelSelectorBuilder selector;
   private DeploymentStrategyBuilder strategy;
   private PodTemplateSpecBuilder template;
   private Map additionalProperties;

   public DeploymentSpecFluent() {
   }

   public DeploymentSpecFluent(DeploymentSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(DeploymentSpec instance) {
      instance = instance != null ? instance : new DeploymentSpec();
      if (instance != null) {
         this.withMinReadySeconds(instance.getMinReadySeconds());
         this.withPaused(instance.getPaused());
         this.withProgressDeadlineSeconds(instance.getProgressDeadlineSeconds());
         this.withReplicas(instance.getReplicas());
         this.withRevisionHistoryLimit(instance.getRevisionHistoryLimit());
         this.withRollbackTo(instance.getRollbackTo());
         this.withSelector(instance.getSelector());
         this.withStrategy(instance.getStrategy());
         this.withTemplate(instance.getTemplate());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getMinReadySeconds() {
      return this.minReadySeconds;
   }

   public DeploymentSpecFluent withMinReadySeconds(Integer minReadySeconds) {
      this.minReadySeconds = minReadySeconds;
      return this;
   }

   public boolean hasMinReadySeconds() {
      return this.minReadySeconds != null;
   }

   public Boolean getPaused() {
      return this.paused;
   }

   public DeploymentSpecFluent withPaused(Boolean paused) {
      this.paused = paused;
      return this;
   }

   public boolean hasPaused() {
      return this.paused != null;
   }

   public Integer getProgressDeadlineSeconds() {
      return this.progressDeadlineSeconds;
   }

   public DeploymentSpecFluent withProgressDeadlineSeconds(Integer progressDeadlineSeconds) {
      this.progressDeadlineSeconds = progressDeadlineSeconds;
      return this;
   }

   public boolean hasProgressDeadlineSeconds() {
      return this.progressDeadlineSeconds != null;
   }

   public Integer getReplicas() {
      return this.replicas;
   }

   public DeploymentSpecFluent withReplicas(Integer replicas) {
      this.replicas = replicas;
      return this;
   }

   public boolean hasReplicas() {
      return this.replicas != null;
   }

   public Integer getRevisionHistoryLimit() {
      return this.revisionHistoryLimit;
   }

   public DeploymentSpecFluent withRevisionHistoryLimit(Integer revisionHistoryLimit) {
      this.revisionHistoryLimit = revisionHistoryLimit;
      return this;
   }

   public boolean hasRevisionHistoryLimit() {
      return this.revisionHistoryLimit != null;
   }

   public RollbackConfig buildRollbackTo() {
      return this.rollbackTo != null ? this.rollbackTo.build() : null;
   }

   public DeploymentSpecFluent withRollbackTo(RollbackConfig rollbackTo) {
      this._visitables.remove("rollbackTo");
      if (rollbackTo != null) {
         this.rollbackTo = new RollbackConfigBuilder(rollbackTo);
         this._visitables.get("rollbackTo").add(this.rollbackTo);
      } else {
         this.rollbackTo = null;
         this._visitables.get("rollbackTo").remove(this.rollbackTo);
      }

      return this;
   }

   public boolean hasRollbackTo() {
      return this.rollbackTo != null;
   }

   public DeploymentSpecFluent withNewRollbackTo(Long revision) {
      return this.withRollbackTo(new RollbackConfig(revision));
   }

   public RollbackToNested withNewRollbackTo() {
      return new RollbackToNested((RollbackConfig)null);
   }

   public RollbackToNested withNewRollbackToLike(RollbackConfig item) {
      return new RollbackToNested(item);
   }

   public RollbackToNested editRollbackTo() {
      return this.withNewRollbackToLike((RollbackConfig)Optional.ofNullable(this.buildRollbackTo()).orElse((Object)null));
   }

   public RollbackToNested editOrNewRollbackTo() {
      return this.withNewRollbackToLike((RollbackConfig)Optional.ofNullable(this.buildRollbackTo()).orElse((new RollbackConfigBuilder()).build()));
   }

   public RollbackToNested editOrNewRollbackToLike(RollbackConfig item) {
      return this.withNewRollbackToLike((RollbackConfig)Optional.ofNullable(this.buildRollbackTo()).orElse(item));
   }

   public LabelSelector buildSelector() {
      return this.selector != null ? this.selector.build() : null;
   }

   public DeploymentSpecFluent withSelector(LabelSelector selector) {
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

   public DeploymentStrategy buildStrategy() {
      return this.strategy != null ? this.strategy.build() : null;
   }

   public DeploymentSpecFluent withStrategy(DeploymentStrategy strategy) {
      this._visitables.remove("strategy");
      if (strategy != null) {
         this.strategy = new DeploymentStrategyBuilder(strategy);
         this._visitables.get("strategy").add(this.strategy);
      } else {
         this.strategy = null;
         this._visitables.get("strategy").remove(this.strategy);
      }

      return this;
   }

   public boolean hasStrategy() {
      return this.strategy != null;
   }

   public StrategyNested withNewStrategy() {
      return new StrategyNested((DeploymentStrategy)null);
   }

   public StrategyNested withNewStrategyLike(DeploymentStrategy item) {
      return new StrategyNested(item);
   }

   public StrategyNested editStrategy() {
      return this.withNewStrategyLike((DeploymentStrategy)Optional.ofNullable(this.buildStrategy()).orElse((Object)null));
   }

   public StrategyNested editOrNewStrategy() {
      return this.withNewStrategyLike((DeploymentStrategy)Optional.ofNullable(this.buildStrategy()).orElse((new DeploymentStrategyBuilder()).build()));
   }

   public StrategyNested editOrNewStrategyLike(DeploymentStrategy item) {
      return this.withNewStrategyLike((DeploymentStrategy)Optional.ofNullable(this.buildStrategy()).orElse(item));
   }

   public PodTemplateSpec buildTemplate() {
      return this.template != null ? this.template.build() : null;
   }

   public DeploymentSpecFluent withTemplate(PodTemplateSpec template) {
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

   public DeploymentSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public DeploymentSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public DeploymentSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public DeploymentSpecFluent removeFromAdditionalProperties(Map map) {
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

   public DeploymentSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            DeploymentSpecFluent that = (DeploymentSpecFluent)o;
            if (!Objects.equals(this.minReadySeconds, that.minReadySeconds)) {
               return false;
            } else if (!Objects.equals(this.paused, that.paused)) {
               return false;
            } else if (!Objects.equals(this.progressDeadlineSeconds, that.progressDeadlineSeconds)) {
               return false;
            } else if (!Objects.equals(this.replicas, that.replicas)) {
               return false;
            } else if (!Objects.equals(this.revisionHistoryLimit, that.revisionHistoryLimit)) {
               return false;
            } else if (!Objects.equals(this.rollbackTo, that.rollbackTo)) {
               return false;
            } else if (!Objects.equals(this.selector, that.selector)) {
               return false;
            } else if (!Objects.equals(this.strategy, that.strategy)) {
               return false;
            } else if (!Objects.equals(this.template, that.template)) {
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
      return Objects.hash(new Object[]{this.minReadySeconds, this.paused, this.progressDeadlineSeconds, this.replicas, this.revisionHistoryLimit, this.rollbackTo, this.selector, this.strategy, this.template, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.minReadySeconds != null) {
         sb.append("minReadySeconds:");
         sb.append(this.minReadySeconds + ",");
      }

      if (this.paused != null) {
         sb.append("paused:");
         sb.append(this.paused + ",");
      }

      if (this.progressDeadlineSeconds != null) {
         sb.append("progressDeadlineSeconds:");
         sb.append(this.progressDeadlineSeconds + ",");
      }

      if (this.replicas != null) {
         sb.append("replicas:");
         sb.append(this.replicas + ",");
      }

      if (this.revisionHistoryLimit != null) {
         sb.append("revisionHistoryLimit:");
         sb.append(this.revisionHistoryLimit + ",");
      }

      if (this.rollbackTo != null) {
         sb.append("rollbackTo:");
         sb.append(this.rollbackTo + ",");
      }

      if (this.selector != null) {
         sb.append("selector:");
         sb.append(this.selector + ",");
      }

      if (this.strategy != null) {
         sb.append("strategy:");
         sb.append(this.strategy + ",");
      }

      if (this.template != null) {
         sb.append("template:");
         sb.append(this.template + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public DeploymentSpecFluent withPaused() {
      return this.withPaused(true);
   }

   public class RollbackToNested extends RollbackConfigFluent implements Nested {
      RollbackConfigBuilder builder;

      RollbackToNested(RollbackConfig item) {
         this.builder = new RollbackConfigBuilder(this, item);
      }

      public Object and() {
         return DeploymentSpecFluent.this.withRollbackTo(this.builder.build());
      }

      public Object endRollbackTo() {
         return this.and();
      }
   }

   public class SelectorNested extends LabelSelectorFluent implements Nested {
      LabelSelectorBuilder builder;

      SelectorNested(LabelSelector item) {
         this.builder = new LabelSelectorBuilder(this, item);
      }

      public Object and() {
         return DeploymentSpecFluent.this.withSelector(this.builder.build());
      }

      public Object endSelector() {
         return this.and();
      }
   }

   public class StrategyNested extends DeploymentStrategyFluent implements Nested {
      DeploymentStrategyBuilder builder;

      StrategyNested(DeploymentStrategy item) {
         this.builder = new DeploymentStrategyBuilder(this, item);
      }

      public Object and() {
         return DeploymentSpecFluent.this.withStrategy(this.builder.build());
      }

      public Object endStrategy() {
         return this.and();
      }
   }

   public class TemplateNested extends PodTemplateSpecFluent implements Nested {
      PodTemplateSpecBuilder builder;

      TemplateNested(PodTemplateSpec item) {
         this.builder = new PodTemplateSpecBuilder(this, item);
      }

      public Object and() {
         return DeploymentSpecFluent.this.withTemplate(this.builder.build());
      }

      public Object endTemplate() {
         return this.and();
      }
   }
}
