package io.fabric8.kubernetes.api.model.batch.v1beta1;

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
import io.fabric8.kubernetes.api.model.ObjectReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"active", "lastScheduleTime", "lastSuccessfulTime"})
public class CronJobStatus implements Editable, KubernetesResource {
   @JsonProperty("active")
   @JsonInclude(Include.NON_EMPTY)
   private List active = new ArrayList();
   @JsonProperty("lastScheduleTime")
   private String lastScheduleTime;
   @JsonProperty("lastSuccessfulTime")
   private String lastSuccessfulTime;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public CronJobStatus() {
   }

   public CronJobStatus(List active, String lastScheduleTime, String lastSuccessfulTime) {
      this.active = active;
      this.lastScheduleTime = lastScheduleTime;
      this.lastSuccessfulTime = lastSuccessfulTime;
   }

   @JsonProperty("active")
   @JsonInclude(Include.NON_EMPTY)
   public List getActive() {
      return this.active;
   }

   @JsonProperty("active")
   public void setActive(List active) {
      this.active = active;
   }

   @JsonProperty("lastScheduleTime")
   public String getLastScheduleTime() {
      return this.lastScheduleTime;
   }

   @JsonProperty("lastScheduleTime")
   public void setLastScheduleTime(String lastScheduleTime) {
      this.lastScheduleTime = lastScheduleTime;
   }

   @JsonProperty("lastSuccessfulTime")
   public String getLastSuccessfulTime() {
      return this.lastSuccessfulTime;
   }

   @JsonProperty("lastSuccessfulTime")
   public void setLastSuccessfulTime(String lastSuccessfulTime) {
      this.lastSuccessfulTime = lastSuccessfulTime;
   }

   @JsonIgnore
   public CronJobStatusBuilder edit() {
      return new CronJobStatusBuilder(this);
   }

   @JsonIgnore
   public CronJobStatusBuilder toBuilder() {
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
      List var10000 = this.getActive();
      return "CronJobStatus(active=" + var10000 + ", lastScheduleTime=" + this.getLastScheduleTime() + ", lastSuccessfulTime=" + this.getLastSuccessfulTime() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CronJobStatus)) {
         return false;
      } else {
         CronJobStatus other = (CronJobStatus)o;
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

            Object this$lastScheduleTime = this.getLastScheduleTime();
            Object other$lastScheduleTime = other.getLastScheduleTime();
            if (this$lastScheduleTime == null) {
               if (other$lastScheduleTime != null) {
                  return false;
               }
            } else if (!this$lastScheduleTime.equals(other$lastScheduleTime)) {
               return false;
            }

            Object this$lastSuccessfulTime = this.getLastSuccessfulTime();
            Object other$lastSuccessfulTime = other.getLastSuccessfulTime();
            if (this$lastSuccessfulTime == null) {
               if (other$lastSuccessfulTime != null) {
                  return false;
               }
            } else if (!this$lastSuccessfulTime.equals(other$lastSuccessfulTime)) {
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
      return other instanceof CronJobStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $active = this.getActive();
      result = result * 59 + ($active == null ? 43 : $active.hashCode());
      Object $lastScheduleTime = this.getLastScheduleTime();
      result = result * 59 + ($lastScheduleTime == null ? 43 : $lastScheduleTime.hashCode());
      Object $lastSuccessfulTime = this.getLastSuccessfulTime();
      result = result * 59 + ($lastSuccessfulTime == null ? 43 : $lastSuccessfulTime.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
