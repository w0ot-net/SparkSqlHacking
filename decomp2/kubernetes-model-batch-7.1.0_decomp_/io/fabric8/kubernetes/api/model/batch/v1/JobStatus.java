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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"active", "completedIndexes", "completionTime", "conditions", "failed", "failedIndexes", "ready", "startTime", "succeeded", "terminating", "uncountedTerminatedPods"})
public class JobStatus implements Editable, KubernetesResource {
   @JsonProperty("active")
   private Integer active;
   @JsonProperty("completedIndexes")
   private String completedIndexes;
   @JsonProperty("completionTime")
   private String completionTime;
   @JsonProperty("conditions")
   @JsonInclude(Include.NON_EMPTY)
   private List conditions = new ArrayList();
   @JsonProperty("failed")
   private Integer failed;
   @JsonProperty("failedIndexes")
   private String failedIndexes;
   @JsonProperty("ready")
   private Integer ready;
   @JsonProperty("startTime")
   private String startTime;
   @JsonProperty("succeeded")
   private Integer succeeded;
   @JsonProperty("terminating")
   private Integer terminating;
   @JsonProperty("uncountedTerminatedPods")
   private UncountedTerminatedPods uncountedTerminatedPods;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public JobStatus() {
   }

   public JobStatus(Integer active, String completedIndexes, String completionTime, List conditions, Integer failed, String failedIndexes, Integer ready, String startTime, Integer succeeded, Integer terminating, UncountedTerminatedPods uncountedTerminatedPods) {
      this.active = active;
      this.completedIndexes = completedIndexes;
      this.completionTime = completionTime;
      this.conditions = conditions;
      this.failed = failed;
      this.failedIndexes = failedIndexes;
      this.ready = ready;
      this.startTime = startTime;
      this.succeeded = succeeded;
      this.terminating = terminating;
      this.uncountedTerminatedPods = uncountedTerminatedPods;
   }

   @JsonProperty("active")
   public Integer getActive() {
      return this.active;
   }

   @JsonProperty("active")
   public void setActive(Integer active) {
      this.active = active;
   }

   @JsonProperty("completedIndexes")
   public String getCompletedIndexes() {
      return this.completedIndexes;
   }

   @JsonProperty("completedIndexes")
   public void setCompletedIndexes(String completedIndexes) {
      this.completedIndexes = completedIndexes;
   }

   @JsonProperty("completionTime")
   public String getCompletionTime() {
      return this.completionTime;
   }

   @JsonProperty("completionTime")
   public void setCompletionTime(String completionTime) {
      this.completionTime = completionTime;
   }

   @JsonProperty("conditions")
   @JsonInclude(Include.NON_EMPTY)
   public List getConditions() {
      return this.conditions;
   }

   @JsonProperty("conditions")
   public void setConditions(List conditions) {
      this.conditions = conditions;
   }

   @JsonProperty("failed")
   public Integer getFailed() {
      return this.failed;
   }

   @JsonProperty("failed")
   public void setFailed(Integer failed) {
      this.failed = failed;
   }

   @JsonProperty("failedIndexes")
   public String getFailedIndexes() {
      return this.failedIndexes;
   }

   @JsonProperty("failedIndexes")
   public void setFailedIndexes(String failedIndexes) {
      this.failedIndexes = failedIndexes;
   }

   @JsonProperty("ready")
   public Integer getReady() {
      return this.ready;
   }

   @JsonProperty("ready")
   public void setReady(Integer ready) {
      this.ready = ready;
   }

   @JsonProperty("startTime")
   public String getStartTime() {
      return this.startTime;
   }

   @JsonProperty("startTime")
   public void setStartTime(String startTime) {
      this.startTime = startTime;
   }

   @JsonProperty("succeeded")
   public Integer getSucceeded() {
      return this.succeeded;
   }

   @JsonProperty("succeeded")
   public void setSucceeded(Integer succeeded) {
      this.succeeded = succeeded;
   }

   @JsonProperty("terminating")
   public Integer getTerminating() {
      return this.terminating;
   }

   @JsonProperty("terminating")
   public void setTerminating(Integer terminating) {
      this.terminating = terminating;
   }

   @JsonProperty("uncountedTerminatedPods")
   public UncountedTerminatedPods getUncountedTerminatedPods() {
      return this.uncountedTerminatedPods;
   }

   @JsonProperty("uncountedTerminatedPods")
   public void setUncountedTerminatedPods(UncountedTerminatedPods uncountedTerminatedPods) {
      this.uncountedTerminatedPods = uncountedTerminatedPods;
   }

   @JsonIgnore
   public JobStatusBuilder edit() {
      return new JobStatusBuilder(this);
   }

   @JsonIgnore
   public JobStatusBuilder toBuilder() {
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
      Integer var10000 = this.getActive();
      return "JobStatus(active=" + var10000 + ", completedIndexes=" + this.getCompletedIndexes() + ", completionTime=" + this.getCompletionTime() + ", conditions=" + this.getConditions() + ", failed=" + this.getFailed() + ", failedIndexes=" + this.getFailedIndexes() + ", ready=" + this.getReady() + ", startTime=" + this.getStartTime() + ", succeeded=" + this.getSucceeded() + ", terminating=" + this.getTerminating() + ", uncountedTerminatedPods=" + this.getUncountedTerminatedPods() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof JobStatus)) {
         return false;
      } else {
         JobStatus other = (JobStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$active = this.getActive();
            Object other$active = other.getActive();
            if (this$active == null) {
               if (other$active != null) {
                  return false;
               }
            } else if (!this$active.equals(other$active)) {
               return false;
            }

            Object this$failed = this.getFailed();
            Object other$failed = other.getFailed();
            if (this$failed == null) {
               if (other$failed != null) {
                  return false;
               }
            } else if (!this$failed.equals(other$failed)) {
               return false;
            }

            Object this$ready = this.getReady();
            Object other$ready = other.getReady();
            if (this$ready == null) {
               if (other$ready != null) {
                  return false;
               }
            } else if (!this$ready.equals(other$ready)) {
               return false;
            }

            Object this$succeeded = this.getSucceeded();
            Object other$succeeded = other.getSucceeded();
            if (this$succeeded == null) {
               if (other$succeeded != null) {
                  return false;
               }
            } else if (!this$succeeded.equals(other$succeeded)) {
               return false;
            }

            Object this$terminating = this.getTerminating();
            Object other$terminating = other.getTerminating();
            if (this$terminating == null) {
               if (other$terminating != null) {
                  return false;
               }
            } else if (!this$terminating.equals(other$terminating)) {
               return false;
            }

            Object this$completedIndexes = this.getCompletedIndexes();
            Object other$completedIndexes = other.getCompletedIndexes();
            if (this$completedIndexes == null) {
               if (other$completedIndexes != null) {
                  return false;
               }
            } else if (!this$completedIndexes.equals(other$completedIndexes)) {
               return false;
            }

            Object this$completionTime = this.getCompletionTime();
            Object other$completionTime = other.getCompletionTime();
            if (this$completionTime == null) {
               if (other$completionTime != null) {
                  return false;
               }
            } else if (!this$completionTime.equals(other$completionTime)) {
               return false;
            }

            Object this$conditions = this.getConditions();
            Object other$conditions = other.getConditions();
            if (this$conditions == null) {
               if (other$conditions != null) {
                  return false;
               }
            } else if (!this$conditions.equals(other$conditions)) {
               return false;
            }

            Object this$failedIndexes = this.getFailedIndexes();
            Object other$failedIndexes = other.getFailedIndexes();
            if (this$failedIndexes == null) {
               if (other$failedIndexes != null) {
                  return false;
               }
            } else if (!this$failedIndexes.equals(other$failedIndexes)) {
               return false;
            }

            Object this$startTime = this.getStartTime();
            Object other$startTime = other.getStartTime();
            if (this$startTime == null) {
               if (other$startTime != null) {
                  return false;
               }
            } else if (!this$startTime.equals(other$startTime)) {
               return false;
            }

            Object this$uncountedTerminatedPods = this.getUncountedTerminatedPods();
            Object other$uncountedTerminatedPods = other.getUncountedTerminatedPods();
            if (this$uncountedTerminatedPods == null) {
               if (other$uncountedTerminatedPods != null) {
                  return false;
               }
            } else if (!this$uncountedTerminatedPods.equals(other$uncountedTerminatedPods)) {
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
      return other instanceof JobStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $active = this.getActive();
      result = result * 59 + ($active == null ? 43 : $active.hashCode());
      Object $failed = this.getFailed();
      result = result * 59 + ($failed == null ? 43 : $failed.hashCode());
      Object $ready = this.getReady();
      result = result * 59 + ($ready == null ? 43 : $ready.hashCode());
      Object $succeeded = this.getSucceeded();
      result = result * 59 + ($succeeded == null ? 43 : $succeeded.hashCode());
      Object $terminating = this.getTerminating();
      result = result * 59 + ($terminating == null ? 43 : $terminating.hashCode());
      Object $completedIndexes = this.getCompletedIndexes();
      result = result * 59 + ($completedIndexes == null ? 43 : $completedIndexes.hashCode());
      Object $completionTime = this.getCompletionTime();
      result = result * 59 + ($completionTime == null ? 43 : $completionTime.hashCode());
      Object $conditions = this.getConditions();
      result = result * 59 + ($conditions == null ? 43 : $conditions.hashCode());
      Object $failedIndexes = this.getFailedIndexes();
      result = result * 59 + ($failedIndexes == null ? 43 : $failedIndexes.hashCode());
      Object $startTime = this.getStartTime();
      result = result * 59 + ($startTime == null ? 43 : $startTime.hashCode());
      Object $uncountedTerminatedPods = this.getUncountedTerminatedPods();
      result = result * 59 + ($uncountedTerminatedPods == null ? 43 : $uncountedTerminatedPods.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
