package io.fabric8.kubernetes.api.model.events.v1;

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
import io.fabric8.kubernetes.api.model.MicroTime;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"count", "lastObservedTime"})
public class EventSeries implements Editable, KubernetesResource {
   @JsonProperty("count")
   private Integer count;
   @JsonProperty("lastObservedTime")
   private MicroTime lastObservedTime;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public EventSeries() {
   }

   public EventSeries(Integer count, MicroTime lastObservedTime) {
      this.count = count;
      this.lastObservedTime = lastObservedTime;
   }

   @JsonProperty("count")
   public Integer getCount() {
      return this.count;
   }

   @JsonProperty("count")
   public void setCount(Integer count) {
      this.count = count;
   }

   @JsonProperty("lastObservedTime")
   public MicroTime getLastObservedTime() {
      return this.lastObservedTime;
   }

   @JsonProperty("lastObservedTime")
   public void setLastObservedTime(MicroTime lastObservedTime) {
      this.lastObservedTime = lastObservedTime;
   }

   @JsonIgnore
   public EventSeriesBuilder edit() {
      return new EventSeriesBuilder(this);
   }

   @JsonIgnore
   public EventSeriesBuilder toBuilder() {
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
      Integer var10000 = this.getCount();
      return "EventSeries(count=" + var10000 + ", lastObservedTime=" + this.getLastObservedTime() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventSeries)) {
         return false;
      } else {
         EventSeries other = (EventSeries)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$count = this.getCount();
            Object other$count = other.getCount();
            if (this$count == null) {
               if (other$count != null) {
                  return false;
               }
            } else if (!this$count.equals(other$count)) {
               return false;
            }

            Object this$lastObservedTime = this.getLastObservedTime();
            Object other$lastObservedTime = other.getLastObservedTime();
            if (this$lastObservedTime == null) {
               if (other$lastObservedTime != null) {
                  return false;
               }
            } else if (!this$lastObservedTime.equals(other$lastObservedTime)) {
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
      return other instanceof EventSeries;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $count = this.getCount();
      result = result * 59 + ($count == null ? 43 : $count.hashCode());
      Object $lastObservedTime = this.getLastObservedTime();
      result = result * 59 + ($lastObservedTime == null ? 43 : $lastObservedTime.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
