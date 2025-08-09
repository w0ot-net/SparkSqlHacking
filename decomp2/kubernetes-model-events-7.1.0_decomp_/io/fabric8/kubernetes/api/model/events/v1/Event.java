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
import io.fabric8.kubernetes.api.model.EventSource;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.MicroTime;
import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ObjectReference;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"apiVersion", "kind", "metadata", "action", "deprecatedCount", "deprecatedFirstTimestamp", "deprecatedLastTimestamp", "deprecatedSource", "eventTime", "note", "reason", "regarding", "related", "reportingController", "reportingInstance", "series", "type"})
@Version("v1")
@Group("events.k8s.io")
public class Event implements Editable, HasMetadata, Namespaced {
   @JsonProperty("action")
   private String action;
   @JsonProperty("apiVersion")
   private String apiVersion = "events.k8s.io/v1";
   @JsonProperty("deprecatedCount")
   private Integer deprecatedCount;
   @JsonProperty("deprecatedFirstTimestamp")
   private String deprecatedFirstTimestamp;
   @JsonProperty("deprecatedLastTimestamp")
   private String deprecatedLastTimestamp;
   @JsonProperty("deprecatedSource")
   private EventSource deprecatedSource;
   @JsonProperty("eventTime")
   private MicroTime eventTime;
   @JsonProperty("kind")
   private String kind = "Event";
   @JsonProperty("metadata")
   private ObjectMeta metadata;
   @JsonProperty("note")
   private String note;
   @JsonProperty("reason")
   private String reason;
   @JsonProperty("regarding")
   private ObjectReference regarding;
   @JsonProperty("related")
   private ObjectReference related;
   @JsonProperty("reportingController")
   private String reportingController;
   @JsonProperty("reportingInstance")
   private String reportingInstance;
   @JsonProperty("series")
   private EventSeries series;
   @JsonProperty("type")
   private String type;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public Event() {
   }

   public Event(String action, String apiVersion, Integer deprecatedCount, String deprecatedFirstTimestamp, String deprecatedLastTimestamp, EventSource deprecatedSource, MicroTime eventTime, String kind, ObjectMeta metadata, String note, String reason, ObjectReference regarding, ObjectReference related, String reportingController, String reportingInstance, EventSeries series, String type) {
      this.action = action;
      this.apiVersion = apiVersion;
      this.deprecatedCount = deprecatedCount;
      this.deprecatedFirstTimestamp = deprecatedFirstTimestamp;
      this.deprecatedLastTimestamp = deprecatedLastTimestamp;
      this.deprecatedSource = deprecatedSource;
      this.eventTime = eventTime;
      this.kind = kind;
      this.metadata = metadata;
      this.note = note;
      this.reason = reason;
      this.regarding = regarding;
      this.related = related;
      this.reportingController = reportingController;
      this.reportingInstance = reportingInstance;
      this.series = series;
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

   @JsonProperty("deprecatedCount")
   public Integer getDeprecatedCount() {
      return this.deprecatedCount;
   }

   @JsonProperty("deprecatedCount")
   public void setDeprecatedCount(Integer deprecatedCount) {
      this.deprecatedCount = deprecatedCount;
   }

   @JsonProperty("deprecatedFirstTimestamp")
   public String getDeprecatedFirstTimestamp() {
      return this.deprecatedFirstTimestamp;
   }

   @JsonProperty("deprecatedFirstTimestamp")
   public void setDeprecatedFirstTimestamp(String deprecatedFirstTimestamp) {
      this.deprecatedFirstTimestamp = deprecatedFirstTimestamp;
   }

   @JsonProperty("deprecatedLastTimestamp")
   public String getDeprecatedLastTimestamp() {
      return this.deprecatedLastTimestamp;
   }

   @JsonProperty("deprecatedLastTimestamp")
   public void setDeprecatedLastTimestamp(String deprecatedLastTimestamp) {
      this.deprecatedLastTimestamp = deprecatedLastTimestamp;
   }

   @JsonProperty("deprecatedSource")
   public EventSource getDeprecatedSource() {
      return this.deprecatedSource;
   }

   @JsonProperty("deprecatedSource")
   public void setDeprecatedSource(EventSource deprecatedSource) {
      this.deprecatedSource = deprecatedSource;
   }

   @JsonProperty("eventTime")
   public MicroTime getEventTime() {
      return this.eventTime;
   }

   @JsonProperty("eventTime")
   public void setEventTime(MicroTime eventTime) {
      this.eventTime = eventTime;
   }

   @JsonProperty("kind")
   public String getKind() {
      return this.kind;
   }

   @JsonProperty("kind")
   public void setKind(String kind) {
      this.kind = kind;
   }

   @JsonProperty("metadata")
   public ObjectMeta getMetadata() {
      return this.metadata;
   }

   @JsonProperty("metadata")
   public void setMetadata(ObjectMeta metadata) {
      this.metadata = metadata;
   }

   @JsonProperty("note")
   public String getNote() {
      return this.note;
   }

   @JsonProperty("note")
   public void setNote(String note) {
      this.note = note;
   }

   @JsonProperty("reason")
   public String getReason() {
      return this.reason;
   }

   @JsonProperty("reason")
   public void setReason(String reason) {
      this.reason = reason;
   }

   @JsonProperty("regarding")
   public ObjectReference getRegarding() {
      return this.regarding;
   }

   @JsonProperty("regarding")
   public void setRegarding(ObjectReference regarding) {
      this.regarding = regarding;
   }

   @JsonProperty("related")
   public ObjectReference getRelated() {
      return this.related;
   }

   @JsonProperty("related")
   public void setRelated(ObjectReference related) {
      this.related = related;
   }

   @JsonProperty("reportingController")
   public String getReportingController() {
      return this.reportingController;
   }

   @JsonProperty("reportingController")
   public void setReportingController(String reportingController) {
      this.reportingController = reportingController;
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
      return "Event(action=" + var10000 + ", apiVersion=" + this.getApiVersion() + ", deprecatedCount=" + this.getDeprecatedCount() + ", deprecatedFirstTimestamp=" + this.getDeprecatedFirstTimestamp() + ", deprecatedLastTimestamp=" + this.getDeprecatedLastTimestamp() + ", deprecatedSource=" + this.getDeprecatedSource() + ", eventTime=" + this.getEventTime() + ", kind=" + this.getKind() + ", metadata=" + this.getMetadata() + ", note=" + this.getNote() + ", reason=" + this.getReason() + ", regarding=" + this.getRegarding() + ", related=" + this.getRelated() + ", reportingController=" + this.getReportingController() + ", reportingInstance=" + this.getReportingInstance() + ", series=" + this.getSeries() + ", type=" + this.getType() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
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
            Object this$deprecatedCount = this.getDeprecatedCount();
            Object other$deprecatedCount = other.getDeprecatedCount();
            if (this$deprecatedCount == null) {
               if (other$deprecatedCount != null) {
                  return false;
               }
            } else if (!this$deprecatedCount.equals(other$deprecatedCount)) {
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

            Object this$deprecatedFirstTimestamp = this.getDeprecatedFirstTimestamp();
            Object other$deprecatedFirstTimestamp = other.getDeprecatedFirstTimestamp();
            if (this$deprecatedFirstTimestamp == null) {
               if (other$deprecatedFirstTimestamp != null) {
                  return false;
               }
            } else if (!this$deprecatedFirstTimestamp.equals(other$deprecatedFirstTimestamp)) {
               return false;
            }

            Object this$deprecatedLastTimestamp = this.getDeprecatedLastTimestamp();
            Object other$deprecatedLastTimestamp = other.getDeprecatedLastTimestamp();
            if (this$deprecatedLastTimestamp == null) {
               if (other$deprecatedLastTimestamp != null) {
                  return false;
               }
            } else if (!this$deprecatedLastTimestamp.equals(other$deprecatedLastTimestamp)) {
               return false;
            }

            Object this$deprecatedSource = this.getDeprecatedSource();
            Object other$deprecatedSource = other.getDeprecatedSource();
            if (this$deprecatedSource == null) {
               if (other$deprecatedSource != null) {
                  return false;
               }
            } else if (!this$deprecatedSource.equals(other$deprecatedSource)) {
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

            Object this$kind = this.getKind();
            Object other$kind = other.getKind();
            if (this$kind == null) {
               if (other$kind != null) {
                  return false;
               }
            } else if (!this$kind.equals(other$kind)) {
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

            Object this$note = this.getNote();
            Object other$note = other.getNote();
            if (this$note == null) {
               if (other$note != null) {
                  return false;
               }
            } else if (!this$note.equals(other$note)) {
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

            Object this$regarding = this.getRegarding();
            Object other$regarding = other.getRegarding();
            if (this$regarding == null) {
               if (other$regarding != null) {
                  return false;
               }
            } else if (!this$regarding.equals(other$regarding)) {
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

            Object this$reportingController = this.getReportingController();
            Object other$reportingController = other.getReportingController();
            if (this$reportingController == null) {
               if (other$reportingController != null) {
                  return false;
               }
            } else if (!this$reportingController.equals(other$reportingController)) {
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
      Object $deprecatedCount = this.getDeprecatedCount();
      result = result * 59 + ($deprecatedCount == null ? 43 : $deprecatedCount.hashCode());
      Object $action = this.getAction();
      result = result * 59 + ($action == null ? 43 : $action.hashCode());
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $deprecatedFirstTimestamp = this.getDeprecatedFirstTimestamp();
      result = result * 59 + ($deprecatedFirstTimestamp == null ? 43 : $deprecatedFirstTimestamp.hashCode());
      Object $deprecatedLastTimestamp = this.getDeprecatedLastTimestamp();
      result = result * 59 + ($deprecatedLastTimestamp == null ? 43 : $deprecatedLastTimestamp.hashCode());
      Object $deprecatedSource = this.getDeprecatedSource();
      result = result * 59 + ($deprecatedSource == null ? 43 : $deprecatedSource.hashCode());
      Object $eventTime = this.getEventTime();
      result = result * 59 + ($eventTime == null ? 43 : $eventTime.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $metadata = this.getMetadata();
      result = result * 59 + ($metadata == null ? 43 : $metadata.hashCode());
      Object $note = this.getNote();
      result = result * 59 + ($note == null ? 43 : $note.hashCode());
      Object $reason = this.getReason();
      result = result * 59 + ($reason == null ? 43 : $reason.hashCode());
      Object $regarding = this.getRegarding();
      result = result * 59 + ($regarding == null ? 43 : $regarding.hashCode());
      Object $related = this.getRelated();
      result = result * 59 + ($related == null ? 43 : $related.hashCode());
      Object $reportingController = this.getReportingController();
      result = result * 59 + ($reportingController == null ? 43 : $reportingController.hashCode());
      Object $reportingInstance = this.getReportingInstance();
      result = result * 59 + ($reportingInstance == null ? 43 : $reportingInstance.hashCode());
      Object $series = this.getSeries();
      result = result * 59 + ($series == null ? 43 : $series.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
