package io.fabric8.kubernetes.api.model.batch.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class CronJobSpecFluent extends BaseFluent {
   private String concurrencyPolicy;
   private Integer failedJobsHistoryLimit;
   private JobTemplateSpecBuilder jobTemplate;
   private String schedule;
   private Long startingDeadlineSeconds;
   private Integer successfulJobsHistoryLimit;
   private Boolean suspend;
   private Map additionalProperties;

   public CronJobSpecFluent() {
   }

   public CronJobSpecFluent(CronJobSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(CronJobSpec instance) {
      instance = instance != null ? instance : new CronJobSpec();
      if (instance != null) {
         this.withConcurrencyPolicy(instance.getConcurrencyPolicy());
         this.withFailedJobsHistoryLimit(instance.getFailedJobsHistoryLimit());
         this.withJobTemplate(instance.getJobTemplate());
         this.withSchedule(instance.getSchedule());
         this.withStartingDeadlineSeconds(instance.getStartingDeadlineSeconds());
         this.withSuccessfulJobsHistoryLimit(instance.getSuccessfulJobsHistoryLimit());
         this.withSuspend(instance.getSuspend());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getConcurrencyPolicy() {
      return this.concurrencyPolicy;
   }

   public CronJobSpecFluent withConcurrencyPolicy(String concurrencyPolicy) {
      this.concurrencyPolicy = concurrencyPolicy;
      return this;
   }

   public boolean hasConcurrencyPolicy() {
      return this.concurrencyPolicy != null;
   }

   public Integer getFailedJobsHistoryLimit() {
      return this.failedJobsHistoryLimit;
   }

   public CronJobSpecFluent withFailedJobsHistoryLimit(Integer failedJobsHistoryLimit) {
      this.failedJobsHistoryLimit = failedJobsHistoryLimit;
      return this;
   }

   public boolean hasFailedJobsHistoryLimit() {
      return this.failedJobsHistoryLimit != null;
   }

   public JobTemplateSpec buildJobTemplate() {
      return this.jobTemplate != null ? this.jobTemplate.build() : null;
   }

   public CronJobSpecFluent withJobTemplate(JobTemplateSpec jobTemplate) {
      this._visitables.remove("jobTemplate");
      if (jobTemplate != null) {
         this.jobTemplate = new JobTemplateSpecBuilder(jobTemplate);
         this._visitables.get("jobTemplate").add(this.jobTemplate);
      } else {
         this.jobTemplate = null;
         this._visitables.get("jobTemplate").remove(this.jobTemplate);
      }

      return this;
   }

   public boolean hasJobTemplate() {
      return this.jobTemplate != null;
   }

   public JobTemplateNested withNewJobTemplate() {
      return new JobTemplateNested((JobTemplateSpec)null);
   }

   public JobTemplateNested withNewJobTemplateLike(JobTemplateSpec item) {
      return new JobTemplateNested(item);
   }

   public JobTemplateNested editJobTemplate() {
      return this.withNewJobTemplateLike((JobTemplateSpec)Optional.ofNullable(this.buildJobTemplate()).orElse((Object)null));
   }

   public JobTemplateNested editOrNewJobTemplate() {
      return this.withNewJobTemplateLike((JobTemplateSpec)Optional.ofNullable(this.buildJobTemplate()).orElse((new JobTemplateSpecBuilder()).build()));
   }

   public JobTemplateNested editOrNewJobTemplateLike(JobTemplateSpec item) {
      return this.withNewJobTemplateLike((JobTemplateSpec)Optional.ofNullable(this.buildJobTemplate()).orElse(item));
   }

   public String getSchedule() {
      return this.schedule;
   }

   public CronJobSpecFluent withSchedule(String schedule) {
      this.schedule = schedule;
      return this;
   }

   public boolean hasSchedule() {
      return this.schedule != null;
   }

   public Long getStartingDeadlineSeconds() {
      return this.startingDeadlineSeconds;
   }

   public CronJobSpecFluent withStartingDeadlineSeconds(Long startingDeadlineSeconds) {
      this.startingDeadlineSeconds = startingDeadlineSeconds;
      return this;
   }

   public boolean hasStartingDeadlineSeconds() {
      return this.startingDeadlineSeconds != null;
   }

   public Integer getSuccessfulJobsHistoryLimit() {
      return this.successfulJobsHistoryLimit;
   }

   public CronJobSpecFluent withSuccessfulJobsHistoryLimit(Integer successfulJobsHistoryLimit) {
      this.successfulJobsHistoryLimit = successfulJobsHistoryLimit;
      return this;
   }

   public boolean hasSuccessfulJobsHistoryLimit() {
      return this.successfulJobsHistoryLimit != null;
   }

   public Boolean getSuspend() {
      return this.suspend;
   }

   public CronJobSpecFluent withSuspend(Boolean suspend) {
      this.suspend = suspend;
      return this;
   }

   public boolean hasSuspend() {
      return this.suspend != null;
   }

   public CronJobSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public CronJobSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public CronJobSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public CronJobSpecFluent removeFromAdditionalProperties(Map map) {
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

   public CronJobSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            CronJobSpecFluent that = (CronJobSpecFluent)o;
            if (!Objects.equals(this.concurrencyPolicy, that.concurrencyPolicy)) {
               return false;
            } else if (!Objects.equals(this.failedJobsHistoryLimit, that.failedJobsHistoryLimit)) {
               return false;
            } else if (!Objects.equals(this.jobTemplate, that.jobTemplate)) {
               return false;
            } else if (!Objects.equals(this.schedule, that.schedule)) {
               return false;
            } else if (!Objects.equals(this.startingDeadlineSeconds, that.startingDeadlineSeconds)) {
               return false;
            } else if (!Objects.equals(this.successfulJobsHistoryLimit, that.successfulJobsHistoryLimit)) {
               return false;
            } else if (!Objects.equals(this.suspend, that.suspend)) {
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
      return Objects.hash(new Object[]{this.concurrencyPolicy, this.failedJobsHistoryLimit, this.jobTemplate, this.schedule, this.startingDeadlineSeconds, this.successfulJobsHistoryLimit, this.suspend, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.concurrencyPolicy != null) {
         sb.append("concurrencyPolicy:");
         sb.append(this.concurrencyPolicy + ",");
      }

      if (this.failedJobsHistoryLimit != null) {
         sb.append("failedJobsHistoryLimit:");
         sb.append(this.failedJobsHistoryLimit + ",");
      }

      if (this.jobTemplate != null) {
         sb.append("jobTemplate:");
         sb.append(this.jobTemplate + ",");
      }

      if (this.schedule != null) {
         sb.append("schedule:");
         sb.append(this.schedule + ",");
      }

      if (this.startingDeadlineSeconds != null) {
         sb.append("startingDeadlineSeconds:");
         sb.append(this.startingDeadlineSeconds + ",");
      }

      if (this.successfulJobsHistoryLimit != null) {
         sb.append("successfulJobsHistoryLimit:");
         sb.append(this.successfulJobsHistoryLimit + ",");
      }

      if (this.suspend != null) {
         sb.append("suspend:");
         sb.append(this.suspend + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public CronJobSpecFluent withSuspend() {
      return this.withSuspend(true);
   }

   public class JobTemplateNested extends JobTemplateSpecFluent implements Nested {
      JobTemplateSpecBuilder builder;

      JobTemplateNested(JobTemplateSpec item) {
         this.builder = new JobTemplateSpecBuilder(this, item);
      }

      public Object and() {
         return CronJobSpecFluent.this.withJobTemplate(this.builder.build());
      }

      public Object endJobTemplate() {
         return this.and();
      }
   }
}
