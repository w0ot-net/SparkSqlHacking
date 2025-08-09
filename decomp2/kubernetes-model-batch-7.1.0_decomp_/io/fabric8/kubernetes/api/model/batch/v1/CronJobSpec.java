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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"concurrencyPolicy", "failedJobsHistoryLimit", "jobTemplate", "schedule", "startingDeadlineSeconds", "successfulJobsHistoryLimit", "suspend", "timeZone"})
public class CronJobSpec implements Editable, KubernetesResource {
   @JsonProperty("concurrencyPolicy")
   private String concurrencyPolicy;
   @JsonProperty("failedJobsHistoryLimit")
   private Integer failedJobsHistoryLimit;
   @JsonProperty("jobTemplate")
   private JobTemplateSpec jobTemplate;
   @JsonProperty("schedule")
   private String schedule;
   @JsonProperty("startingDeadlineSeconds")
   private Long startingDeadlineSeconds;
   @JsonProperty("successfulJobsHistoryLimit")
   private Integer successfulJobsHistoryLimit;
   @JsonProperty("suspend")
   private Boolean suspend;
   @JsonProperty("timeZone")
   private String timeZone;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public CronJobSpec() {
   }

   public CronJobSpec(String concurrencyPolicy, Integer failedJobsHistoryLimit, JobTemplateSpec jobTemplate, String schedule, Long startingDeadlineSeconds, Integer successfulJobsHistoryLimit, Boolean suspend, String timeZone) {
      this.concurrencyPolicy = concurrencyPolicy;
      this.failedJobsHistoryLimit = failedJobsHistoryLimit;
      this.jobTemplate = jobTemplate;
      this.schedule = schedule;
      this.startingDeadlineSeconds = startingDeadlineSeconds;
      this.successfulJobsHistoryLimit = successfulJobsHistoryLimit;
      this.suspend = suspend;
      this.timeZone = timeZone;
   }

   @JsonProperty("concurrencyPolicy")
   public String getConcurrencyPolicy() {
      return this.concurrencyPolicy;
   }

   @JsonProperty("concurrencyPolicy")
   public void setConcurrencyPolicy(String concurrencyPolicy) {
      this.concurrencyPolicy = concurrencyPolicy;
   }

   @JsonProperty("failedJobsHistoryLimit")
   public Integer getFailedJobsHistoryLimit() {
      return this.failedJobsHistoryLimit;
   }

   @JsonProperty("failedJobsHistoryLimit")
   public void setFailedJobsHistoryLimit(Integer failedJobsHistoryLimit) {
      this.failedJobsHistoryLimit = failedJobsHistoryLimit;
   }

   @JsonProperty("jobTemplate")
   public JobTemplateSpec getJobTemplate() {
      return this.jobTemplate;
   }

   @JsonProperty("jobTemplate")
   public void setJobTemplate(JobTemplateSpec jobTemplate) {
      this.jobTemplate = jobTemplate;
   }

   @JsonProperty("schedule")
   public String getSchedule() {
      return this.schedule;
   }

   @JsonProperty("schedule")
   public void setSchedule(String schedule) {
      this.schedule = schedule;
   }

   @JsonProperty("startingDeadlineSeconds")
   public Long getStartingDeadlineSeconds() {
      return this.startingDeadlineSeconds;
   }

   @JsonProperty("startingDeadlineSeconds")
   public void setStartingDeadlineSeconds(Long startingDeadlineSeconds) {
      this.startingDeadlineSeconds = startingDeadlineSeconds;
   }

   @JsonProperty("successfulJobsHistoryLimit")
   public Integer getSuccessfulJobsHistoryLimit() {
      return this.successfulJobsHistoryLimit;
   }

   @JsonProperty("successfulJobsHistoryLimit")
   public void setSuccessfulJobsHistoryLimit(Integer successfulJobsHistoryLimit) {
      this.successfulJobsHistoryLimit = successfulJobsHistoryLimit;
   }

   @JsonProperty("suspend")
   public Boolean getSuspend() {
      return this.suspend;
   }

   @JsonProperty("suspend")
   public void setSuspend(Boolean suspend) {
      this.suspend = suspend;
   }

   @JsonProperty("timeZone")
   public String getTimeZone() {
      return this.timeZone;
   }

   @JsonProperty("timeZone")
   public void setTimeZone(String timeZone) {
      this.timeZone = timeZone;
   }

   @JsonIgnore
   public CronJobSpecBuilder edit() {
      return new CronJobSpecBuilder(this);
   }

   @JsonIgnore
   public CronJobSpecBuilder toBuilder() {
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
      String var10000 = this.getConcurrencyPolicy();
      return "CronJobSpec(concurrencyPolicy=" + var10000 + ", failedJobsHistoryLimit=" + this.getFailedJobsHistoryLimit() + ", jobTemplate=" + this.getJobTemplate() + ", schedule=" + this.getSchedule() + ", startingDeadlineSeconds=" + this.getStartingDeadlineSeconds() + ", successfulJobsHistoryLimit=" + this.getSuccessfulJobsHistoryLimit() + ", suspend=" + this.getSuspend() + ", timeZone=" + this.getTimeZone() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CronJobSpec)) {
         return false;
      } else {
         CronJobSpec other = (CronJobSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$failedJobsHistoryLimit = this.getFailedJobsHistoryLimit();
            Object other$failedJobsHistoryLimit = other.getFailedJobsHistoryLimit();
            if (this$failedJobsHistoryLimit == null) {
               if (other$failedJobsHistoryLimit != null) {
                  return false;
               }
            } else if (!this$failedJobsHistoryLimit.equals(other$failedJobsHistoryLimit)) {
               return false;
            }

            Object this$startingDeadlineSeconds = this.getStartingDeadlineSeconds();
            Object other$startingDeadlineSeconds = other.getStartingDeadlineSeconds();
            if (this$startingDeadlineSeconds == null) {
               if (other$startingDeadlineSeconds != null) {
                  return false;
               }
            } else if (!this$startingDeadlineSeconds.equals(other$startingDeadlineSeconds)) {
               return false;
            }

            Object this$successfulJobsHistoryLimit = this.getSuccessfulJobsHistoryLimit();
            Object other$successfulJobsHistoryLimit = other.getSuccessfulJobsHistoryLimit();
            if (this$successfulJobsHistoryLimit == null) {
               if (other$successfulJobsHistoryLimit != null) {
                  return false;
               }
            } else if (!this$successfulJobsHistoryLimit.equals(other$successfulJobsHistoryLimit)) {
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

            Object this$concurrencyPolicy = this.getConcurrencyPolicy();
            Object other$concurrencyPolicy = other.getConcurrencyPolicy();
            if (this$concurrencyPolicy == null) {
               if (other$concurrencyPolicy != null) {
                  return false;
               }
            } else if (!this$concurrencyPolicy.equals(other$concurrencyPolicy)) {
               return false;
            }

            Object this$jobTemplate = this.getJobTemplate();
            Object other$jobTemplate = other.getJobTemplate();
            if (this$jobTemplate == null) {
               if (other$jobTemplate != null) {
                  return false;
               }
            } else if (!this$jobTemplate.equals(other$jobTemplate)) {
               return false;
            }

            Object this$schedule = this.getSchedule();
            Object other$schedule = other.getSchedule();
            if (this$schedule == null) {
               if (other$schedule != null) {
                  return false;
               }
            } else if (!this$schedule.equals(other$schedule)) {
               return false;
            }

            Object this$timeZone = this.getTimeZone();
            Object other$timeZone = other.getTimeZone();
            if (this$timeZone == null) {
               if (other$timeZone != null) {
                  return false;
               }
            } else if (!this$timeZone.equals(other$timeZone)) {
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
      return other instanceof CronJobSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $failedJobsHistoryLimit = this.getFailedJobsHistoryLimit();
      result = result * 59 + ($failedJobsHistoryLimit == null ? 43 : $failedJobsHistoryLimit.hashCode());
      Object $startingDeadlineSeconds = this.getStartingDeadlineSeconds();
      result = result * 59 + ($startingDeadlineSeconds == null ? 43 : $startingDeadlineSeconds.hashCode());
      Object $successfulJobsHistoryLimit = this.getSuccessfulJobsHistoryLimit();
      result = result * 59 + ($successfulJobsHistoryLimit == null ? 43 : $successfulJobsHistoryLimit.hashCode());
      Object $suspend = this.getSuspend();
      result = result * 59 + ($suspend == null ? 43 : $suspend.hashCode());
      Object $concurrencyPolicy = this.getConcurrencyPolicy();
      result = result * 59 + ($concurrencyPolicy == null ? 43 : $concurrencyPolicy.hashCode());
      Object $jobTemplate = this.getJobTemplate();
      result = result * 59 + ($jobTemplate == null ? 43 : $jobTemplate.hashCode());
      Object $schedule = this.getSchedule();
      result = result * 59 + ($schedule == null ? 43 : $schedule.hashCode());
      Object $timeZone = this.getTimeZone();
      result = result * 59 + ($timeZone == null ? 43 : $timeZone.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
