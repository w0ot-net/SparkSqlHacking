package io.fabric8.kubernetes.api.model.batch.v1;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.builder.Editable;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.fabric8.kubernetes.api.model.LabelSelector;
import io.fabric8.kubernetes.api.model.PodTemplateSpec;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"activeDeadlineSeconds", "backoffLimit", "backoffLimitPerIndex", "completionMode", "completions", "managedBy", "manualSelector", "maxFailedIndexes", "parallelism", "podFailurePolicy", "podReplacementPolicy", "selector", "successPolicy", "suspend", "template", "ttlSecondsAfterFinished"})
public class JobSpec implements Editable, KubernetesResource {
   @JsonProperty("activeDeadlineSeconds")
   private Long activeDeadlineSeconds;
   @JsonProperty("backoffLimit")
   private Integer backoffLimit;
   @JsonProperty("backoffLimitPerIndex")
   private Integer backoffLimitPerIndex;
   @JsonProperty("completionMode")
   private String completionMode;
   @JsonProperty("completions")
   private Integer completions;
   @JsonProperty("managedBy")
   private String managedBy;
   @JsonProperty("manualSelector")
   private Boolean manualSelector;
   @JsonProperty("maxFailedIndexes")
   private Integer maxFailedIndexes;
   @JsonProperty("parallelism")
   private Integer parallelism;
   @JsonProperty("podFailurePolicy")
   private PodFailurePolicy podFailurePolicy;
   @JsonProperty("podReplacementPolicy")
   private String podReplacementPolicy;
   @JsonProperty("selector")
   private LabelSelector selector;
   @JsonProperty("successPolicy")
   private SuccessPolicy successPolicy;
   @JsonProperty("suspend")
   private Boolean suspend;
   @JsonProperty("template")
   private PodTemplateSpec template;
   @JsonProperty("ttlSecondsAfterFinished")
   private Integer ttlSecondsAfterFinished;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public JobSpec() {
   }

   public JobSpec(Long activeDeadlineSeconds, Integer backoffLimit, Integer backoffLimitPerIndex, String completionMode, Integer completions, String managedBy, Boolean manualSelector, Integer maxFailedIndexes, Integer parallelism, PodFailurePolicy podFailurePolicy, String podReplacementPolicy, LabelSelector selector, SuccessPolicy successPolicy, Boolean suspend, PodTemplateSpec template, Integer ttlSecondsAfterFinished) {
      this.activeDeadlineSeconds = activeDeadlineSeconds;
      this.backoffLimit = backoffLimit;
      this.backoffLimitPerIndex = backoffLimitPerIndex;
      this.completionMode = completionMode;
      this.completions = completions;
      this.managedBy = managedBy;
      this.manualSelector = manualSelector;
      this.maxFailedIndexes = maxFailedIndexes;
      this.parallelism = parallelism;
      this.podFailurePolicy = podFailurePolicy;
      this.podReplacementPolicy = podReplacementPolicy;
      this.selector = selector;
      this.successPolicy = successPolicy;
      this.suspend = suspend;
      this.template = template;
      this.ttlSecondsAfterFinished = ttlSecondsAfterFinished;
   }

   @JsonProperty("activeDeadlineSeconds")
   public Long getActiveDeadlineSeconds() {
      return this.activeDeadlineSeconds;
   }

   @JsonProperty("activeDeadlineSeconds")
   public void setActiveDeadlineSeconds(Long activeDeadlineSeconds) {
      this.activeDeadlineSeconds = activeDeadlineSeconds;
   }

   @JsonProperty("backoffLimit")
   public Integer getBackoffLimit() {
      return this.backoffLimit;
   }

   @JsonProperty("backoffLimit")
   public void setBackoffLimit(Integer backoffLimit) {
      this.backoffLimit = backoffLimit;
   }

   @JsonProperty("backoffLimitPerIndex")
   public Integer getBackoffLimitPerIndex() {
      return this.backoffLimitPerIndex;
   }

   @JsonProperty("backoffLimitPerIndex")
   public void setBackoffLimitPerIndex(Integer backoffLimitPerIndex) {
      this.backoffLimitPerIndex = backoffLimitPerIndex;
   }

   @JsonProperty("completionMode")
   public String getCompletionMode() {
      return this.completionMode;
   }

   @JsonProperty("completionMode")
   public void setCompletionMode(String completionMode) {
      this.completionMode = completionMode;
   }

   @JsonProperty("completions")
   public Integer getCompletions() {
      return this.completions;
   }

   @JsonProperty("completions")
   public void setCompletions(Integer completions) {
      this.completions = completions;
   }

   @JsonProperty("managedBy")
   public String getManagedBy() {
      return this.managedBy;
   }

   @JsonProperty("managedBy")
   public void setManagedBy(String managedBy) {
      this.managedBy = managedBy;
   }

   @JsonProperty("manualSelector")
   public Boolean getManualSelector() {
      return this.manualSelector;
   }

   @JsonProperty("manualSelector")
   public void setManualSelector(Boolean manualSelector) {
      this.manualSelector = manualSelector;
   }

   @JsonProperty("maxFailedIndexes")
   public Integer getMaxFailedIndexes() {
      return this.maxFailedIndexes;
   }

   @JsonProperty("maxFailedIndexes")
   public void setMaxFailedIndexes(Integer maxFailedIndexes) {
      this.maxFailedIndexes = maxFailedIndexes;
   }

   @JsonProperty("parallelism")
   public Integer getParallelism() {
      return this.parallelism;
   }

   @JsonProperty("parallelism")
   public void setParallelism(Integer parallelism) {
      this.parallelism = parallelism;
   }

   @JsonProperty("podFailurePolicy")
   public PodFailurePolicy getPodFailurePolicy() {
      return this.podFailurePolicy;
   }

   @JsonProperty("podFailurePolicy")
   public void setPodFailurePolicy(PodFailurePolicy podFailurePolicy) {
      this.podFailurePolicy = podFailurePolicy;
   }

   @JsonProperty("podReplacementPolicy")
   public String getPodReplacementPolicy() {
      return this.podReplacementPolicy;
   }

   @JsonProperty("podReplacementPolicy")
   public void setPodReplacementPolicy(String podReplacementPolicy) {
      this.podReplacementPolicy = podReplacementPolicy;
   }

   @JsonProperty("selector")
   public LabelSelector getSelector() {
      return this.selector;
   }

   @JsonProperty("selector")
   public void setSelector(LabelSelector selector) {
      this.selector = selector;
   }

   @JsonProperty("successPolicy")
   public SuccessPolicy getSuccessPolicy() {
      return this.successPolicy;
   }

   @JsonProperty("successPolicy")
   public void setSuccessPolicy(SuccessPolicy successPolicy) {
      this.successPolicy = successPolicy;
   }

   @JsonProperty("suspend")
   public Boolean getSuspend() {
      return this.suspend;
   }

   @JsonProperty("suspend")
   public void setSuspend(Boolean suspend) {
      this.suspend = suspend;
   }

   @JsonProperty("template")
   public PodTemplateSpec getTemplate() {
      return this.template;
   }

   @JsonProperty("template")
   public void setTemplate(PodTemplateSpec template) {
      this.template = template;
   }

   @JsonProperty("ttlSecondsAfterFinished")
   public Integer getTtlSecondsAfterFinished() {
      return this.ttlSecondsAfterFinished;
   }

   @JsonProperty("ttlSecondsAfterFinished")
   public void setTtlSecondsAfterFinished(Integer ttlSecondsAfterFinished) {
      this.ttlSecondsAfterFinished = ttlSecondsAfterFinished;
   }

   @JsonIgnore
   public JobSpecBuilder edit() {
      return new JobSpecBuilder(this);
   }

   @JsonIgnore
   public JobSpecBuilder toBuilder() {
      return this.edit();
   }

   @JsonAnyGetter
   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   @JsonAnySetter
   public void setAdditionalProperty(String name, Object value) {
      this.additionalProperties.put(name, value);
   }

   public void setAdditionalProperties(Map additionalProperties) {
      this.additionalProperties = additionalProperties;
   }

   @Generated
   public String toString() {
      Long var10000 = this.getActiveDeadlineSeconds();
      return "JobSpec(activeDeadlineSeconds=" + var10000 + ", backoffLimit=" + this.getBackoffLimit() + ", backoffLimitPerIndex=" + this.getBackoffLimitPerIndex() + ", completionMode=" + this.getCompletionMode() + ", completions=" + this.getCompletions() + ", managedBy=" + this.getManagedBy() + ", manualSelector=" + this.getManualSelector() + ", maxFailedIndexes=" + this.getMaxFailedIndexes() + ", parallelism=" + this.getParallelism() + ", podFailurePolicy=" + this.getPodFailurePolicy() + ", podReplacementPolicy=" + this.getPodReplacementPolicy() + ", selector=" + this.getSelector() + ", successPolicy=" + this.getSuccessPolicy() + ", suspend=" + this.getSuspend() + ", template=" + this.getTemplate() + ", ttlSecondsAfterFinished=" + this.getTtlSecondsAfterFinished() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof JobSpec)) {
         return false;
      } else {
         JobSpec other = (JobSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$activeDeadlineSeconds = this.getActiveDeadlineSeconds();
            Object other$activeDeadlineSeconds = other.getActiveDeadlineSeconds();
            if (this$activeDeadlineSeconds == null) {
               if (other$activeDeadlineSeconds != null) {
                  return false;
               }
            } else if (!this$activeDeadlineSeconds.equals(other$activeDeadlineSeconds)) {
               return false;
            }

            Object this$backoffLimit = this.getBackoffLimit();
            Object other$backoffLimit = other.getBackoffLimit();
            if (this$backoffLimit == null) {
               if (other$backoffLimit != null) {
                  return false;
               }
            } else if (!this$backoffLimit.equals(other$backoffLimit)) {
               return false;
            }

            Object this$backoffLimitPerIndex = this.getBackoffLimitPerIndex();
            Object other$backoffLimitPerIndex = other.getBackoffLimitPerIndex();
            if (this$backoffLimitPerIndex == null) {
               if (other$backoffLimitPerIndex != null) {
                  return false;
               }
            } else if (!this$backoffLimitPerIndex.equals(other$backoffLimitPerIndex)) {
               return false;
            }

            Object this$completions = this.getCompletions();
            Object other$completions = other.getCompletions();
            if (this$completions == null) {
               if (other$completions != null) {
                  return false;
               }
            } else if (!this$completions.equals(other$completions)) {
               return false;
            }

            Object this$manualSelector = this.getManualSelector();
            Object other$manualSelector = other.getManualSelector();
            if (this$manualSelector == null) {
               if (other$manualSelector != null) {
                  return false;
               }
            } else if (!this$manualSelector.equals(other$manualSelector)) {
               return false;
            }

            Object this$maxFailedIndexes = this.getMaxFailedIndexes();
            Object other$maxFailedIndexes = other.getMaxFailedIndexes();
            if (this$maxFailedIndexes == null) {
               if (other$maxFailedIndexes != null) {
                  return false;
               }
            } else if (!this$maxFailedIndexes.equals(other$maxFailedIndexes)) {
               return false;
            }

            Object this$parallelism = this.getParallelism();
            Object other$parallelism = other.getParallelism();
            if (this$parallelism == null) {
               if (other$parallelism != null) {
                  return false;
               }
            } else if (!this$parallelism.equals(other$parallelism)) {
               return false;
            }

            Object this$suspend = this.getSuspend();
            Object other$suspend = other.getSuspend();
            if (this$suspend == null) {
               if (other$suspend != null) {
                  return false;
               }
            } else if (!this$suspend.equals(other$suspend)) {
               return false;
            }

            Object this$ttlSecondsAfterFinished = this.getTtlSecondsAfterFinished();
            Object other$ttlSecondsAfterFinished = other.getTtlSecondsAfterFinished();
            if (this$ttlSecondsAfterFinished == null) {
               if (other$ttlSecondsAfterFinished != null) {
                  return false;
               }
            } else if (!this$ttlSecondsAfterFinished.equals(other$ttlSecondsAfterFinished)) {
               return false;
            }

            Object this$completionMode = this.getCompletionMode();
            Object other$completionMode = other.getCompletionMode();
            if (this$completionMode == null) {
               if (other$completionMode != null) {
                  return false;
               }
            } else if (!this$completionMode.equals(other$completionMode)) {
               return false;
            }

            Object this$managedBy = this.getManagedBy();
            Object other$managedBy = other.getManagedBy();
            if (this$managedBy == null) {
               if (other$managedBy != null) {
                  return false;
               }
            } else if (!this$managedBy.equals(other$managedBy)) {
               return false;
            }

            Object this$podFailurePolicy = this.getPodFailurePolicy();
            Object other$podFailurePolicy = other.getPodFailurePolicy();
            if (this$podFailurePolicy == null) {
               if (other$podFailurePolicy != null) {
                  return false;
               }
            } else if (!this$podFailurePolicy.equals(other$podFailurePolicy)) {
               return false;
            }

            Object this$podReplacementPolicy = this.getPodReplacementPolicy();
            Object other$podReplacementPolicy = other.getPodReplacementPolicy();
            if (this$podReplacementPolicy == null) {
               if (other$podReplacementPolicy != null) {
                  return false;
               }
            } else if (!this$podReplacementPolicy.equals(other$podReplacementPolicy)) {
               return false;
            }

            Object this$selector = this.getSelector();
            Object other$selector = other.getSelector();
            if (this$selector == null) {
               if (other$selector != null) {
                  return false;
               }
            } else if (!this$selector.equals(other$selector)) {
               return false;
            }

            Object this$successPolicy = this.getSuccessPolicy();
            Object other$successPolicy = other.getSuccessPolicy();
            if (this$successPolicy == null) {
               if (other$successPolicy != null) {
                  return false;
               }
            } else if (!this$successPolicy.equals(other$successPolicy)) {
               return false;
            }

            Object this$template = this.getTemplate();
            Object other$template = other.getTemplate();
            if (this$template == null) {
               if (other$template != null) {
                  return false;
               }
            } else if (!this$template.equals(other$template)) {
               return false;
            }

            Object this$additionalProperties = this.getAdditionalProperties();
            Object other$additionalProperties = other.getAdditionalProperties();
            if (this$additionalProperties == null) {
               if (other$additionalProperties != null) {
                  return false;
               }
            } else if (!this$additionalProperties.equals(other$additionalProperties)) {
               return false;
            }

            return true;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object other) {
      return other instanceof JobSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $activeDeadlineSeconds = this.getActiveDeadlineSeconds();
      result = result * 59 + ($activeDeadlineSeconds == null ? 43 : $activeDeadlineSeconds.hashCode());
      Object $backoffLimit = this.getBackoffLimit();
      result = result * 59 + ($backoffLimit == null ? 43 : $backoffLimit.hashCode());
      Object $backoffLimitPerIndex = this.getBackoffLimitPerIndex();
      result = result * 59 + ($backoffLimitPerIndex == null ? 43 : $backoffLimitPerIndex.hashCode());
      Object $completions = this.getCompletions();
      result = result * 59 + ($completions == null ? 43 : $completions.hashCode());
      Object $manualSelector = this.getManualSelector();
      result = result * 59 + ($manualSelector == null ? 43 : $manualSelector.hashCode());
      Object $maxFailedIndexes = this.getMaxFailedIndexes();
      result = result * 59 + ($maxFailedIndexes == null ? 43 : $maxFailedIndexes.hashCode());
      Object $parallelism = this.getParallelism();
      result = result * 59 + ($parallelism == null ? 43 : $parallelism.hashCode());
      Object $suspend = this.getSuspend();
      result = result * 59 + ($suspend == null ? 43 : $suspend.hashCode());
      Object $ttlSecondsAfterFinished = this.getTtlSecondsAfterFinished();
      result = result * 59 + ($ttlSecondsAfterFinished == null ? 43 : $ttlSecondsAfterFinished.hashCode());
      Object $completionMode = this.getCompletionMode();
      result = result * 59 + ($completionMode == null ? 43 : $completionMode.hashCode());
      Object $managedBy = this.getManagedBy();
      result = result * 59 + ($managedBy == null ? 43 : $managedBy.hashCode());
      Object $podFailurePolicy = this.getPodFailurePolicy();
      result = result * 59 + ($podFailurePolicy == null ? 43 : $podFailurePolicy.hashCode());
      Object $podReplacementPolicy = this.getPodReplacementPolicy();
      result = result * 59 + ($podReplacementPolicy == null ? 43 : $podReplacementPolicy.hashCode());
      Object $selector = this.getSelector();
      result = result * 59 + ($selector == null ? 43 : $selector.hashCode());
      Object $successPolicy = this.getSuccessPolicy();
      result = result * 59 + ($successPolicy == null ? 43 : $successPolicy.hashCode());
      Object $template = this.getTemplate();
      result = result * 59 + ($template == null ? 43 : $template.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
