package io.fabric8.kubernetes.api.model;

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
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"apiVersion", "kind", "metadata", "action", "count", "eventTime", "firstTimestamp", "involvedObject", "lastTimestamp", "message", "reason", "related", "reportingComponent", "reportingInstance", "series", "source", "type"})
@Version("v1")
@Group("")
public class Event implements Editable, HasMetadata, Namespaced {
   @JsonProperty("action")
   private String action;
   @JsonProperty("apiVersion")
   private String apiVersion = "v1";
   @JsonProperty("count")
   private Integer count;
   @JsonProperty("eventTime")
   private MicroTime eventTime;
   @JsonProperty("firstTimestamp")
   private String firstTimestamp;
   @JsonProperty("involvedObject")
   private ObjectReference involvedObject;
   @JsonProperty("kind")
   private String kind = "Event";
   @JsonProperty("lastTimestamp")
   private String lastTimestamp;
   @JsonProperty("message")
   private String message;
   @JsonProperty("metadata")
   private ObjectMeta metadata;
   @JsonProperty("reason")
   private String reason;
   @JsonProperty("related")
   private ObjectReference related;
   @JsonProperty("reportingComponent")
   private String reportingComponent;
   @JsonProperty("reportingInstance")
   private String reportingInstance;
   @JsonProperty("series")
   private EventSeries series;
   @JsonProperty("source")
   private EventSource source;
   @JsonProperty("type")
   private String type;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public Event() {
   }

   public Event(String action, String apiVersion, Integer count, MicroTime eventTime, String firstTimestamp, ObjectReference involvedObject, String kind, String lastTimestamp, String message, ObjectMeta metadata, String reason, ObjectReference related, String reportingComponent, String reportingInstance, EventSeries series, EventSource source, String type) {
      this.action = action;
      this.apiVersion = apiVersion;
      this.count = count;
      this.eventTime = eventTime;
      this.firstTimestamp = firstTimestamp;
      this.involvedObject = involvedObject;
      this.kind = kind;
      this.lastTimestamp = lastTimestamp;
      this.message = message;
      this.metadata = metadata;
      this.reason = reason;
      this.related = related;
      this.reportingComponent = reportingComponent;
      this.reportingInstance = reportingInstance;
      this.series = series;
      this.source = source;
      this.type = type;
   }

   @JsonProperty("action")
   public String getAction() {
      return this.action;
   }

   @JsonProperty("action")
   public void setAction(String action) {
      this.action = action;
   }

   @JsonProperty("apiVersion")
   public String getApiVersion() {
      return this.apiVersion;
   }

   @JsonProperty("apiVersion")
   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
   }

   @JsonProperty("count")
   public Integer getCount() {
      return this.count;
   }

   @JsonProperty("count")
   public void setCount(Integer count) {
      this.count = count;
   }

   @JsonProperty("eventTime")
   public MicroTime getEventTime() {
      return this.eventTime;
   }

   @JsonProperty("eventTime")
   public void setEventTime(MicroTime eventTime) {
      this.eventTime = eventTime;
   }

   @JsonProperty("firstTimestamp")
   public String getFirstTimestamp() {
      return this.firstTimestamp;
   }

   @JsonProperty("firstTimestamp")
   public void setFirstTimestamp(String firstTimestamp) {
      this.firstTimestamp = firstTimestamp;
   }

   @JsonProperty("involvedObject")
   public ObjectReference getInvolvedObject() {
      return this.involvedObject;
   }

   @JsonProperty("involvedObject")
   public void setInvolvedObject(ObjectReference involvedObject) {
      this.involvedObject = involvedObject;
   }

   @JsonProperty("kind")
   public String getKind() {
      return this.kind;
   }

   @JsonProperty("kind")
   public void setKind(String kind) {
      this.kind = kind;
   }

   @JsonProperty("lastTimestamp")
   public String getLastTimestamp() {
      return this.lastTimestamp;
   }

   @JsonProperty("lastTimestamp")
   public void setLastTimestamp(String lastTimestamp) {
      this.lastTimestamp = lastTimestamp;
   }

   @JsonProperty("message")
   public String getMessage() {
      return this.message;
   }

   @JsonProperty("message")
   public void setMessage(String message) {
      this.message = message;
   }

   @JsonProperty("metadata")
   public ObjectMeta getMetadata() {
      return this.metadata;
   }

   @JsonProperty("metadata")
   public void setMetadata(ObjectMeta metadata) {
      this.metadata = metadata;
   }

   @JsonProperty("reason")
   public String getReason() {
      return this.reason;
   }

   @JsonProperty("reason")
   public void setReason(String reason) {
      this.reason = reason;
   }

   @JsonProperty("related")
   public ObjectReference getRelated() {
      return this.related;
   }

   @JsonProperty("related")
   public void setRelated(ObjectReference related) {
      this.related = related;
   }

   @JsonProperty("reportingComponent")
   public String getReportingComponent() {
      return this.reportingComponent;
   }

   @JsonProperty("reportingComponent")
   public void setReportingComponent(String reportingComponent) {
      this.reportingComponent = reportingComponent;
   }

   @JsonProperty("reportingInstance")
   public String getReportingInstance() {
      return this.reportingInstance;
   }

   @JsonProperty("reportingInstance")
   public void setReportingInstance(String reportingInstance) {
      this.reportingInstance = reportingInstance;
   }

   @JsonProperty("series")
   public EventSeries getSeries() {
      return this.series;
   }

   @JsonProperty("series")
   public void setSeries(EventSeries series) {
      this.series = series;
   }

   @JsonProperty("source")
   public EventSource getSource() {
      return this.source;
   }

   @JsonProperty("source")
   public void setSource(EventSource source) {
      this.source = source;
   }

   @JsonProperty("type")
   public String getType() {
      return this.type;
   }

   @JsonProperty("type")
   public void setType(String type) {
      this.type = type;
   }

   @JsonIgnore
   public EventBuilder edit() {
      return new EventBuilder(this);
   }

   @JsonIgnore
   public EventBuilder toBuilder() {
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
      String var10000 = this.getAction();
      return "Event(action=" + var10000 + ", apiVersion=" + this.getApiVersion() + ", count=" + this.getCount() + ", eventTime=" + this.getEventTime() + ", firstTimestamp=" + this.getFirstTimestamp() + ", involvedObject=" + this.getInvolvedObject() + ", kind=" + this.getKind() + ", lastTimestamp=" + this.getLastTimestamp() + ", message=" + this.getMessage() + ", metadata=" + this.getMetadata() + ", reason=" + this.getReason() + ", related=" + this.getRelated() + ", reportingComponent=" + this.getReportingComponent() + ", reportingInstance=" + this.getReportingInstance() + ", series=" + this.getSeries() + ", source=" + this.getSource() + ", type=" + this.getType() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Event)) {
         return false;
      } else {
         Event other = (Event)o;
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

            Object this$action = this.getAction();
            Object other$action = other.getAction();
            if (this$action == null) {
               if (other$action != null) {
                  return false;
               }
            } else if (!this$action.equals(other$action)) {
               return false;
            }

            Object this$apiVersion = this.getApiVersion();
            Object other$apiVersion = other.getApiVersion();
            if (this$apiVersion == null) {
               if (other$apiVersion != null) {
                  return false;
               }
            } else if (!this$apiVersion.equals(other$apiVersion)) {
               return false;
            }

            Object this$eventTime = this.getEventTime();
            Object other$eventTime = other.getEventTime();
            if (this$eventTime == null) {
               if (other$eventTime != null) {
                  return false;
               }
            } else if (!this$eventTime.equals(other$eventTime)) {
               return false;
            }

            Object this$firstTimestamp = this.getFirstTimestamp();
            Object other$firstTimestamp = other.getFirstTimestamp();
            if (this$firstTimestamp == null) {
               if (other$firstTimestamp != null) {
                  return false;
               }
            } else if (!this$firstTimestamp.equals(other$firstTimestamp)) {
               return false;
            }

            Object this$involvedObject = this.getInvolvedObject();
            Object other$involvedObject = other.getInvolvedObject();
            if (this$involvedObject == null) {
               if (other$involvedObject != null) {
                  return false;
               }
            } else if (!this$involvedObject.equals(other$involvedObject)) {
               return false;
            }

            Object this$kind = this.getKind();
            Object other$kind = other.getKind();
            if (this$kind == null) {
               if (other$kind != null) {
                  return false;
               }
            } else if (!this$kind.equals(other$kind)) {
               return false;
            }

            Object this$lastTimestamp = this.getLastTimestamp();
            Object other$lastTimestamp = other.getLastTimestamp();
            if (this$lastTimestamp == null) {
               if (other$lastTimestamp != null) {
                  return false;
               }
            } else if (!this$lastTimestamp.equals(other$lastTimestamp)) {
               return false;
            }

            Object this$message = this.getMessage();
            Object other$message = other.getMessage();
            if (this$message == null) {
               if (other$message != null) {
                  return false;
               }
            } else if (!this$message.equals(other$message)) {
               return false;
            }

            Object this$metadata = this.getMetadata();
            Object other$metadata = other.getMetadata();
            if (this$metadata == null) {
               if (other$metadata != null) {
                  return false;
               }
            } else if (!this$metadata.equals(other$metadata)) {
               return false;
            }

            Object this$reason = this.getReason();
            Object other$reason = other.getReason();
            if (this$reason == null) {
               if (other$reason != null) {
                  return false;
               }
            } else if (!this$reason.equals(other$reason)) {
               return false;
            }

            Object this$related = this.getRelated();
            Object other$related = other.getRelated();
            if (this$related == null) {
               if (other$related != null) {
                  return false;
               }
            } else if (!this$related.equals(other$related)) {
               return false;
            }

            Object this$reportingComponent = this.getReportingComponent();
            Object other$reportingComponent = other.getReportingComponent();
            if (this$reportingComponent == null) {
               if (other$reportingComponent != null) {
                  return false;
               }
            } else if (!this$reportingComponent.equals(other$reportingComponent)) {
               return false;
            }

            Object this$reportingInstance = this.getReportingInstance();
            Object other$reportingInstance = other.getReportingInstance();
            if (this$reportingInstance == null) {
               if (other$reportingInstance != null) {
                  return false;
               }
            } else if (!this$reportingInstance.equals(other$reportingInstance)) {
               return false;
            }

            Object this$series = this.getSeries();
            Object other$series = other.getSeries();
            if (this$series == null) {
               if (other$series != null) {
                  return false;
               }
            } else if (!this$series.equals(other$series)) {
               return false;
            }

            Object this$source = this.getSource();
            Object other$source = other.getSource();
            if (this$source == null) {
               if (other$source != null) {
                  return false;
               }
            } else if (!this$source.equals(other$source)) {
               return false;
            }

            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
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
      return other instanceof Event;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $count = this.getCount();
      result = result * 59 + ($count == null ? 43 : $count.hashCode());
      Object $action = this.getAction();
      result = result * 59 + ($action == null ? 43 : $action.hashCode());
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $eventTime = this.getEventTime();
      result = result * 59 + ($eventTime == null ? 43 : $eventTime.hashCode());
      Object $firstTimestamp = this.getFirstTimestamp();
      result = result * 59 + ($firstTimestamp == null ? 43 : $firstTimestamp.hashCode());
      Object $involvedObject = this.getInvolvedObject();
      result = result * 59 + ($involvedObject == null ? 43 : $involvedObject.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $lastTimestamp = this.getLastTimestamp();
      result = result * 59 + ($lastTimestamp == null ? 43 : $lastTimestamp.hashCode());
      Object $message = this.getMessage();
      result = result * 59 + ($message == null ? 43 : $message.hashCode());
      Object $metadata = this.getMetadata();
      result = result * 59 + ($metadata == null ? 43 : $metadata.hashCode());
      Object $reason = this.getReason();
      result = result * 59 + ($reason == null ? 43 : $reason.hashCode());
      Object $related = this.getRelated();
      result = result * 59 + ($related == null ? 43 : $related.hashCode());
      Object $reportingComponent = this.getReportingComponent();
      result = result * 59 + ($reportingComponent == null ? 43 : $reportingComponent.hashCode());
      Object $reportingInstance = this.getReportingInstance();
      result = result * 59 + ($reportingInstance == null ? 43 : $reportingInstance.hashCode());
      Object $series = this.getSeries();
      result = result * 59 + ($series == null ? 43 : $series.hashCode());
      Object $source = this.getSource();
      result = result * 59 + ($source == null ? 43 : $source.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
