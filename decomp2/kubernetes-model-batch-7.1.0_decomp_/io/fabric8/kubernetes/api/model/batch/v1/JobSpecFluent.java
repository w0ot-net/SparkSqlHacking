package io.fabric8.kubernetes.api.model.batch.v1;

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

public class JobSpecFluent extends BaseFluent {
   private Long activeDeadlineSeconds;
   private Integer backoffLimit;
   private Integer backoffLimitPerIndex;
   private String completionMode;
   private Integer completions;
   private String managedBy;
   private Boolean manualSelector;
   private Integer maxFailedIndexes;
   private Integer parallelism;
   private PodFailurePolicyBuilder podFailurePolicy;
   private String podReplacementPolicy;
   private LabelSelectorBuilder selector;
   private SuccessPolicyBuilder successPolicy;
   private Boolean suspend;
   private PodTemplateSpecBuilder template;
   private Integer ttlSecondsAfterFinished;
   private Map additionalProperties;

   public JobSpecFluent() {
   }

   public JobSpecFluent(JobSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(JobSpec instance) {
      instance = instance != null ? instance : new JobSpec();
      if (instance != null) {
         this.withActiveDeadlineSeconds(instance.getActiveDeadlineSeconds());
         this.withBackoffLimit(instance.getBackoffLimit());
         this.withBackoffLimitPerIndex(instance.getBackoffLimitPerIndex());
         this.withCompletionMode(instance.getCompletionMode());
         this.withCompletions(instance.getCompletions());
         this.withManagedBy(instance.getManagedBy());
         this.withManualSelector(instance.getManualSelector());
         this.withMaxFailedIndexes(instance.getMaxFailedIndexes());
         this.withParallelism(instance.getParallelism());
         this.withPodFailurePolicy(instance.getPodFailurePolicy());
         this.withPodReplacementPolicy(instance.getPodReplacementPolicy());
         this.withSelector(instance.getSelector());
         this.withSuccessPolicy(instance.getSuccessPolicy());
         this.withSuspend(instance.getSuspend());
         this.withTemplate(instance.getTemplate());
         this.withTtlSecondsAfterFinished(instance.getTtlSecondsAfterFinished());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Long getActiveDeadlineSeconds() {
      return this.activeDeadlineSeconds;
   }

   public JobSpecFluent withActiveDeadlineSeconds(Long activeDeadlineSeconds) {
      this.activeDeadlineSeconds = activeDeadlineSeconds;
      return this;
   }

   public boolean hasActiveDeadlineSeconds() {
      return this.activeDeadlineSeconds != null;
   }

   public Integer getBackoffLimit() {
      return this.backoffLimit;
   }

   public JobSpecFluent withBackoffLimit(Integer backoffLimit) {
      this.backoffLimit = backoffLimit;
      return this;
   }

   public boolean hasBackoffLimit() {
      return this.backoffLimit != null;
   }

   public Integer getBackoffLimitPerIndex() {
      return this.backoffLimitPerIndex;
   }

   public JobSpecFluent withBackoffLimitPerIndex(Integer backoffLimitPerIndex) {
      this.backoffLimitPerIndex = backoffLimitPerIndex;
      return this;
   }

   public boolean hasBackoffLimitPerIndex() {
      return this.backoffLimitPerIndex != null;
   }

   public String getCompletionMode() {
      return this.completionMode;
   }

   public JobSpecFluent withCompletionMode(String completionMode) {
      this.completionMode = completionMode;
      return this;
   }

   public boolean hasCompletionMode() {
      return this.completionMode != null;
   }

   public Integer getCompletions() {
      return this.completions;
   }

   public JobSpecFluent withCompletions(Integer completions) {
      this.completions = completions;
      return this;
   }

   public boolean hasCompletions() {
      return this.completions != null;
   }

   public String getManagedBy() {
      return this.managedBy;
   }

   public JobSpecFluent withManagedBy(String managedBy) {
      this.managedBy = managedBy;
      return this;
   }

   public boolean hasManagedBy() {
      return this.managedBy != null;
   }

   public Boolean getManualSelector() {
      return this.manualSelector;
   }

   public JobSpecFluent withManualSelector(Boolean manualSelector) {
      this.manualSelector = manualSelector;
      return this;
   }

   public boolean hasManualSelector() {
      return this.manualSelector != null;
   }

   public Integer getMaxFailedIndexes() {
      return this.maxFailedIndexes;
   }

   public JobSpecFluent withMaxFailedIndexes(Integer maxFailedIndexes) {
      this.maxFailedIndexes = maxFailedIndexes;
      return this;
   }

   public boolean hasMaxFailedIndexes() {
      return this.maxFailedIndexes != null;
   }

   public Integer getParallelism() {
      return this.parallelism;
   }

   public JobSpecFluent withParallelism(Integer parallelism) {
      this.parallelism = parallelism;
      return this;
   }

   public boolean hasParallelism() {
      return this.parallelism != null;
   }

   public PodFailurePolicy buildPodFailurePolicy() {
      return this.podFailurePolicy != null ? this.podFailurePolicy.build() : null;
   }

   public JobSpecFluent withPodFailurePolicy(PodFailurePolicy podFailurePolicy) {
      this._visitables.remove("podFailurePolicy");
      if (podFailurePolicy != null) {
         this.podFailurePolicy = new PodFailurePolicyBuilder(podFailurePolicy);
         this._visitables.get("podFailurePolicy").add(this.podFailurePolicy);
      } else {
         this.podFailurePolicy = null;
         this._visitables.get("podFailurePolicy").remove(this.podFailurePolicy);
      }

      return this;
   }

   public boolean hasPodFailurePolicy() {
      return this.podFailurePolicy != null;
   }

   public PodFailurePolicyNested withNewPodFailurePolicy() {
      return new PodFailurePolicyNested((PodFailurePolicy)null);
   }

   public PodFailurePolicyNested withNewPodFailurePolicyLike(PodFailurePolicy item) {
      return new PodFailurePolicyNested(item);
   }

   public PodFailurePolicyNested editPodFailurePolicy() {
      return this.withNewPodFailurePolicyLike((PodFailurePolicy)Optional.ofNullable(this.buildPodFailurePolicy()).orElse((Object)null));
   }

   public PodFailurePolicyNested editOrNewPodFailurePolicy() {
      return this.withNewPodFailurePolicyLike((PodFailurePolicy)Optional.ofNullable(this.buildPodFailurePolicy()).orElse((new PodFailurePolicyBuilder()).build()));
   }

   public PodFailurePolicyNested editOrNewPodFailurePolicyLike(PodFailurePolicy item) {
      return this.withNewPodFailurePolicyLike((PodFailurePolicy)Optional.ofNullable(this.buildPodFailurePolicy()).orElse(item));
   }

   public String getPodReplacementPolicy() {
      return this.podReplacementPolicy;
   }

   public JobSpecFluent withPodReplacementPolicy(String podReplacementPolicy) {
      this.podReplacementPolicy = podReplacementPolicy;
      return this;
   }

   public boolean hasPodReplacementPolicy() {
      return this.podReplacementPolicy != null;
   }

   public LabelSelector buildSelector() {
      return this.selector != null ? this.selector.build() : null;
   }

   public JobSpecFluent withSelector(LabelSelector selector) {
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

   public SuccessPolicy buildSuccessPolicy() {
      return this.successPolicy != null ? this.successPolicy.build() : null;
   }

   public JobSpecFluent withSuccessPolicy(SuccessPolicy successPolicy) {
      this._visitables.remove("successPolicy");
      if (successPolicy != null) {
         this.successPolicy = new SuccessPolicyBuilder(successPolicy);
         this._visitables.get("successPolicy").add(this.successPolicy);
      } else {
         this.successPolicy = null;
         this._visitables.get("successPolicy").remove(this.successPolicy);
      }

      return this;
   }

   public boolean hasSuccessPolicy() {
      return this.successPolicy != null;
   }

   public SuccessPolicyNested withNewSuccessPolicy() {
      return new SuccessPolicyNested((SuccessPolicy)null);
   }

   public SuccessPolicyNested withNewSuccessPolicyLike(SuccessPolicy item) {
      return new SuccessPolicyNested(item);
   }

   public SuccessPolicyNested editSuccessPolicy() {
      return this.withNewSuccessPolicyLike((SuccessPolicy)Optional.ofNullable(this.buildSuccessPolicy()).orElse((Object)null));
   }

   public SuccessPolicyNested editOrNewSuccessPolicy() {
      return this.withNewSuccessPolicyLike((SuccessPolicy)Optional.ofNullable(this.buildSuccessPolicy()).orElse((new SuccessPolicyBuilder()).build()));
   }

   public SuccessPolicyNested editOrNewSuccessPolicyLike(SuccessPolicy item) {
      return this.withNewSuccessPolicyLike((SuccessPolicy)Optional.ofNullable(this.buildSuccessPolicy()).orElse(item));
   }

   public Boolean getSuspend() {
      return this.suspend;
   }

   public JobSpecFluent withSuspend(Boolean suspend) {
      this.suspend = suspend;
      return this;
   }

   public boolean hasSuspend() {
      return this.suspend != null;
   }

   public PodTemplateSpec buildTemplate() {
      return this.template != null ? this.template.build() : null;
   }

   public JobSpecFluent withTemplate(PodTemplateSpec template) {
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

   public Integer getTtlSecondsAfterFinished() {
      return this.ttlSecondsAfterFinished;
   }

   public JobSpecFluent withTtlSecondsAfterFinished(Integer ttlSecondsAfterFinished) {
      this.ttlSecondsAfterFinished = ttlSecondsAfterFinished;
      return this;
   }

   public boolean hasTtlSecondsAfterFinished() {
      return this.ttlSecondsAfterFinished != null;
   }

   public JobSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public JobSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public JobSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public JobSpecFluent removeFromAdditionalProperties(Map map) {
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

   public JobSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            JobSpecFluent that = (JobSpecFluent)o;
            if (!Objects.equals(this.activeDeadlineSeconds, that.activeDeadlineSeconds)) {
               return false;
            } else if (!Objects.equals(this.backoffLimit, that.backoffLimit)) {
               return false;
            } else if (!Objects.equals(this.backoffLimitPerIndex, that.backoffLimitPerIndex)) {
               return false;
            } else if (!Objects.equals(this.completionMode, that.completionMode)) {
               return false;
            } else if (!Objects.equals(this.completions, that.completions)) {
               return false;
            } else if (!Objects.equals(this.managedBy, that.managedBy)) {
               return false;
            } else if (!Objects.equals(this.manualSelector, that.manualSelector)) {
               return false;
            } else if (!Objects.equals(this.maxFailedIndexes, that.maxFailedIndexes)) {
               return false;
            } else if (!Objects.equals(this.parallelism, that.parallelism)) {
               return false;
            } else if (!Objects.equals(this.podFailurePolicy, that.podFailurePolicy)) {
               return false;
            } else if (!Objects.equals(this.podReplacementPolicy, that.podReplacementPolicy)) {
               return false;
            } else if (!Objects.equals(this.selector, that.selector)) {
               return false;
            } else if (!Objects.equals(this.successPolicy, that.successPolicy)) {
               return false;
            } else if (!Objects.equals(this.suspend, that.suspend)) {
               return false;
            } else if (!Objects.equals(this.template, that.template)) {
               return false;
            } else if (!Objects.equals(this.ttlSecondsAfterFinished, that.ttlSecondsAfterFinished)) {
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
      return Objects.hash(new Object[]{this.activeDeadlineSeconds, this.backoffLimit, this.backoffLimitPerIndex, this.completionMode, this.completions, this.managedBy, this.manualSelector, this.maxFailedIndexes, this.parallelism, this.podFailurePolicy, this.podReplacementPolicy, this.selector, this.successPolicy, this.suspend, this.template, this.ttlSecondsAfterFinished, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.activeDeadlineSeconds != null) {
         sb.append("activeDeadlineSeconds:");
         sb.append(this.activeDeadlineSeconds + ",");
      }

      if (this.backoffLimit != null) {
         sb.append("backoffLimit:");
         sb.append(this.backoffLimit + ",");
      }

      if (this.backoffLimitPerIndex != null) {
         sb.append("backoffLimitPerIndex:");
         sb.append(this.backoffLimitPerIndex + ",");
      }

      if (this.completionMode != null) {
         sb.append("completionMode:");
         sb.append(this.completionMode + ",");
      }

      if (this.completions != null) {
         sb.append("completions:");
         sb.append(this.completions + ",");
      }

      if (this.managedBy != null) {
         sb.append("managedBy:");
         sb.append(this.managedBy + ",");
      }

      if (this.manualSelector != null) {
         sb.append("manualSelector:");
         sb.append(this.manualSelector + ",");
      }

      if (this.maxFailedIndexes != null) {
         sb.append("maxFailedIndexes:");
         sb.append(this.maxFailedIndexes + ",");
      }

      if (this.parallelism != null) {
         sb.append("parallelism:");
         sb.append(this.parallelism + ",");
      }

      if (this.podFailurePolicy != null) {
         sb.append("podFailurePolicy:");
         sb.append(this.podFailurePolicy + ",");
      }

      if (this.podReplacementPolicy != null) {
         sb.append("podReplacementPolicy:");
         sb.append(this.podReplacementPolicy + ",");
      }

      if (this.selector != null) {
         sb.append("selector:");
         sb.append(this.selector + ",");
      }

      if (this.successPolicy != null) {
         sb.append("successPolicy:");
         sb.append(this.successPolicy + ",");
      }

      if (this.suspend != null) {
         sb.append("suspend:");
         sb.append(this.suspend + ",");
      }

      if (this.template != null) {
         sb.append("template:");
         sb.append(this.template + ",");
      }

      if (this.ttlSecondsAfterFinished != null) {
         sb.append("ttlSecondsAfterFinished:");
         sb.append(this.ttlSecondsAfterFinished + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public JobSpecFluent withManualSelector() {
      return this.withManualSelector(true);
   }

   public JobSpecFluent withSuspend() {
      return this.withSuspend(true);
   }

   public class PodFailurePolicyNested extends PodFailurePolicyFluent implements Nested {
      PodFailurePolicyBuilder builder;

      PodFailurePolicyNested(PodFailurePolicy item) {
         this.builder = new PodFailurePolicyBuilder(this, item);
      }

      public Object and() {
         return JobSpecFluent.this.withPodFailurePolicy(this.builder.build());
      }

      public Object endPodFailurePolicy() {
         return this.and();
      }
   }

   public class SelectorNested extends LabelSelectorFluent implements Nested {
      LabelSelectorBuilder builder;

      SelectorNested(LabelSelector item) {
         this.builder = new LabelSelectorBuilder(this, item);
      }

      public Object and() {
         return JobSpecFluent.this.withSelector(this.builder.build());
      }

      public Object endSelector() {
         return this.and();
      }
   }

   public class SuccessPolicyNested extends SuccessPolicyFluent implements Nested {
      SuccessPolicyBuilder builder;

      SuccessPolicyNested(SuccessPolicy item) {
         this.builder = new SuccessPolicyBuilder(this, item);
      }

      public Object and() {
         return JobSpecFluent.this.withSuccessPolicy(this.builder.build());
      }

      public Object endSuccessPolicy() {
         return this.and();
      }
   }

   public class TemplateNested extends PodTemplateSpecFluent implements Nested {
      PodTemplateSpecBuilder builder;

      TemplateNested(PodTemplateSpec item) {
         this.builder = new PodTemplateSpecBuilder(this, item);
      }

      public Object and() {
         return JobSpecFluent.this.withTemplate(this.builder.build());
      }

      public Object endTemplate() {
         return this.and();
      }
   }
}
