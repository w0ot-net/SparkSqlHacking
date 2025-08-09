package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class EventFluent extends BaseFluent {
   private String action;
   private String apiVersion;
   private Integer count;
   private MicroTimeBuilder eventTime;
   private String firstTimestamp;
   private ObjectReferenceBuilder involvedObject;
   private String kind;
   private String lastTimestamp;
   private String message;
   private ObjectMetaBuilder metadata;
   private String reason;
   private ObjectReferenceBuilder related;
   private String reportingComponent;
   private String reportingInstance;
   private EventSeriesBuilder series;
   private EventSourceBuilder source;
   private String type;
   private Map additionalProperties;

   public EventFluent() {
   }

   public EventFluent(Event instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Event instance) {
      instance = instance != null ? instance : new Event();
      if (instance != null) {
         this.withAction(instance.getAction());
         this.withApiVersion(instance.getApiVersion());
         this.withCount(instance.getCount());
         this.withEventTime(instance.getEventTime());
         this.withFirstTimestamp(instance.getFirstTimestamp());
         this.withInvolvedObject(instance.getInvolvedObject());
         this.withKind(instance.getKind());
         this.withLastTimestamp(instance.getLastTimestamp());
         this.withMessage(instance.getMessage());
         this.withMetadata(instance.getMetadata());
         this.withReason(instance.getReason());
         this.withRelated(instance.getRelated());
         this.withReportingComponent(instance.getReportingComponent());
         this.withReportingInstance(instance.getReportingInstance());
         this.withSeries(instance.getSeries());
         this.withSource(instance.getSource());
         this.withType(instance.getType());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getAction() {
      return this.action;
   }

   public EventFluent withAction(String action) {
      this.action = action;
      return this;
   }

   public boolean hasAction() {
      return this.action != null;
   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public EventFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public Integer getCount() {
      return this.count;
   }

   public EventFluent withCount(Integer count) {
      this.count = count;
      return this;
   }

   public boolean hasCount() {
      return this.count != null;
   }

   public MicroTime buildEventTime() {
      return this.eventTime != null ? this.eventTime.build() : null;
   }

   public EventFluent withEventTime(MicroTime eventTime) {
      this._visitables.remove("eventTime");
      if (eventTime != null) {
         this.eventTime = new MicroTimeBuilder(eventTime);
         this._visitables.get("eventTime").add(this.eventTime);
      } else {
         this.eventTime = null;
         this._visitables.get("eventTime").remove(this.eventTime);
      }

      return this;
   }

   public boolean hasEventTime() {
      return this.eventTime != null;
   }

   public EventFluent withNewEventTime(String time) {
      return this.withEventTime(new MicroTime(time));
   }

   public EventTimeNested withNewEventTime() {
      return new EventTimeNested((MicroTime)null);
   }

   public EventTimeNested withNewEventTimeLike(MicroTime item) {
      return new EventTimeNested(item);
   }

   public EventTimeNested editEventTime() {
      return this.withNewEventTimeLike((MicroTime)Optional.ofNullable(this.buildEventTime()).orElse((Object)null));
   }

   public EventTimeNested editOrNewEventTime() {
      return this.withNewEventTimeLike((MicroTime)Optional.ofNullable(this.buildEventTime()).orElse((new MicroTimeBuilder()).build()));
   }

   public EventTimeNested editOrNewEventTimeLike(MicroTime item) {
      return this.withNewEventTimeLike((MicroTime)Optional.ofNullable(this.buildEventTime()).orElse(item));
   }

   public String getFirstTimestamp() {
      return this.firstTimestamp;
   }

   public EventFluent withFirstTimestamp(String firstTimestamp) {
      this.firstTimestamp = firstTimestamp;
      return this;
   }

   public boolean hasFirstTimestamp() {
      return this.firstTimestamp != null;
   }

   public ObjectReference buildInvolvedObject() {
      return this.involvedObject != null ? this.involvedObject.build() : null;
   }

   public EventFluent withInvolvedObject(ObjectReference involvedObject) {
      this._visitables.remove("involvedObject");
      if (involvedObject != null) {
         this.involvedObject = new ObjectReferenceBuilder(involvedObject);
         this._visitables.get("involvedObject").add(this.involvedObject);
      } else {
         this.involvedObject = null;
         this._visitables.get("involvedObject").remove(this.involvedObject);
      }

      return this;
   }

   public boolean hasInvolvedObject() {
      return this.involvedObject != null;
   }

   public InvolvedObjectNested withNewInvolvedObject() {
      return new InvolvedObjectNested((ObjectReference)null);
   }

   public InvolvedObjectNested withNewInvolvedObjectLike(ObjectReference item) {
      return new InvolvedObjectNested(item);
   }

   public InvolvedObjectNested editInvolvedObject() {
      return this.withNewInvolvedObjectLike((ObjectReference)Optional.ofNullable(this.buildInvolvedObject()).orElse((Object)null));
   }

   public InvolvedObjectNested editOrNewInvolvedObject() {
      return this.withNewInvolvedObjectLike((ObjectReference)Optional.ofNullable(this.buildInvolvedObject()).orElse((new ObjectReferenceBuilder()).build()));
   }

   public InvolvedObjectNested editOrNewInvolvedObjectLike(ObjectReference item) {
      return this.withNewInvolvedObjectLike((ObjectReference)Optional.ofNullable(this.buildInvolvedObject()).orElse(item));
   }

   public String getKind() {
      return this.kind;
   }

   public EventFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public String getLastTimestamp() {
      return this.lastTimestamp;
   }

   public EventFluent withLastTimestamp(String lastTimestamp) {
      this.lastTimestamp = lastTimestamp;
      return this;
   }

   public boolean hasLastTimestamp() {
      return this.lastTimestamp != null;
   }

   public String getMessage() {
      return this.message;
   }

   public EventFluent withMessage(String message) {
      this.message = message;
      return this;
   }

   public boolean hasMessage() {
      return this.message != null;
   }

   public ObjectMeta buildMetadata() {
      return this.metadata != null ? this.metadata.build() : null;
   }

   public EventFluent withMetadata(ObjectMeta metadata) {
      this._visitables.remove("metadata");
      if (metadata != null) {
         this.metadata = new ObjectMetaBuilder(metadata);
         this._visitables.get("metadata").add(this.metadata);
      } else {
         this.metadata = null;
         this._visitables.get("metadata").remove(this.metadata);
      }

      return this;
   }

   public boolean hasMetadata() {
      return this.metadata != null;
   }

   public MetadataNested withNewMetadata() {
      return new MetadataNested((ObjectMeta)null);
   }

   public MetadataNested withNewMetadataLike(ObjectMeta item) {
      return new MetadataNested(item);
   }

   public MetadataNested editMetadata() {
      return this.withNewMetadataLike((ObjectMeta)Optional.ofNullable(this.buildMetadata()).orElse((Object)null));
   }

   public MetadataNested editOrNewMetadata() {
      return this.withNewMetadataLike((ObjectMeta)Optional.ofNullable(this.buildMetadata()).orElse((new ObjectMetaBuilder()).build()));
   }

   public MetadataNested editOrNewMetadataLike(ObjectMeta item) {
      return this.withNewMetadataLike((ObjectMeta)Optional.ofNullable(this.buildMetadata()).orElse(item));
   }

   public String getReason() {
      return this.reason;
   }

   public EventFluent withReason(String reason) {
      this.reason = reason;
      return this;
   }

   public boolean hasReason() {
      return this.reason != null;
   }

   public ObjectReference buildRelated() {
      return this.related != null ? this.related.build() : null;
   }

   public EventFluent withRelated(ObjectReference related) {
      this._visitables.remove("related");
      if (related != null) {
         this.related = new ObjectReferenceBuilder(related);
         this._visitables.get("related").add(this.related);
      } else {
         this.related = null;
         this._visitables.get("related").remove(this.related);
      }

      return this;
   }

   public boolean hasRelated() {
      return this.related != null;
   }

   public RelatedNested withNewRelated() {
      return new RelatedNested((ObjectReference)null);
   }

   public RelatedNested withNewRelatedLike(ObjectReference item) {
      return new RelatedNested(item);
   }

   public RelatedNested editRelated() {
      return this.withNewRelatedLike((ObjectReference)Optional.ofNullable(this.buildRelated()).orElse((Object)null));
   }

   public RelatedNested editOrNewRelated() {
      return this.withNewRelatedLike((ObjectReference)Optional.ofNullable(this.buildRelated()).orElse((new ObjectReferenceBuilder()).build()));
   }

   public RelatedNested editOrNewRelatedLike(ObjectReference item) {
      return this.withNewRelatedLike((ObjectReference)Optional.ofNullable(this.buildRelated()).orElse(item));
   }

   public String getReportingComponent() {
      return this.reportingComponent;
   }

   public EventFluent withReportingComponent(String reportingComponent) {
      this.reportingComponent = reportingComponent;
      return this;
   }

   public boolean hasReportingComponent() {
      return this.reportingComponent != null;
   }

   public String getReportingInstance() {
      return this.reportingInstance;
   }

   public EventFluent withReportingInstance(String reportingInstance) {
      this.reportingInstance = reportingInstance;
      return this;
   }

   public boolean hasReportingInstance() {
      return this.reportingInstance != null;
   }

   public EventSeries buildSeries() {
      return this.series != null ? this.series.build() : null;
   }

   public EventFluent withSeries(EventSeries series) {
      this._visitables.remove("series");
      if (series != null) {
         this.series = new EventSeriesBuilder(series);
         this._visitables.get("series").add(this.series);
      } else {
         this.series = null;
         this._visitables.get("series").remove(this.series);
      }

      return this;
   }

   public boolean hasSeries() {
      return this.series != null;
   }

   public SeriesNested withNewSeries() {
      return new SeriesNested((EventSeries)null);
   }

   public SeriesNested withNewSeriesLike(EventSeries item) {
      return new SeriesNested(item);
   }

   public SeriesNested editSeries() {
      return this.withNewSeriesLike((EventSeries)Optional.ofNullable(this.buildSeries()).orElse((Object)null));
   }

   public SeriesNested editOrNewSeries() {
      return this.withNewSeriesLike((EventSeries)Optional.ofNullable(this.buildSeries()).orElse((new EventSeriesBuilder()).build()));
   }

   public SeriesNested editOrNewSeriesLike(EventSeries item) {
      return this.withNewSeriesLike((EventSeries)Optional.ofNullable(this.buildSeries()).orElse(item));
   }

   public EventSource buildSource() {
      return this.source != null ? this.source.build() : null;
   }

   public EventFluent withSource(EventSource source) {
      this._visitables.remove("source");
      if (source != null) {
         this.source = new EventSourceBuilder(source);
         this._visitables.get("source").add(this.source);
      } else {
         this.source = null;
         this._visitables.get("source").remove(this.source);
      }

      return this;
   }

   public boolean hasSource() {
      return this.source != null;
   }

   public EventFluent withNewSource(String component, String host) {
      return this.withSource(new EventSource(component, host));
   }

   public SourceNested withNewSource() {
      return new SourceNested((EventSource)null);
   }

   public SourceNested withNewSourceLike(EventSource item) {
      return new SourceNested(item);
   }

   public SourceNested editSource() {
      return this.withNewSourceLike((EventSource)Optional.ofNullable(this.buildSource()).orElse((Object)null));
   }

   public SourceNested editOrNewSource() {
      return this.withNewSourceLike((EventSource)Optional.ofNullable(this.buildSource()).orElse((new EventSourceBuilder()).build()));
   }

   public SourceNested editOrNewSourceLike(EventSource item) {
      return this.withNewSourceLike((EventSource)Optional.ofNullable(this.buildSource()).orElse(item));
   }

   public String getType() {
      return this.type;
   }

   public EventFluent withType(String type) {
      this.type = type;
      return this;
   }

   public boolean hasType() {
      return this.type != null;
   }

   public EventFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public EventFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public EventFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public EventFluent removeFromAdditionalProperties(Map map) {
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

   public EventFluent withAdditionalProperties(Map additionalProperties) {
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
            EventFluent that = (EventFluent)o;
            if (!Objects.equals(this.action, that.action)) {
               return false;
            } else if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.count, that.count)) {
               return false;
            } else if (!Objects.equals(this.eventTime, that.eventTime)) {
               return false;
            } else if (!Objects.equals(this.firstTimestamp, that.firstTimestamp)) {
               return false;
            } else if (!Objects.equals(this.involvedObject, that.involvedObject)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.lastTimestamp, that.lastTimestamp)) {
               return false;
            } else if (!Objects.equals(this.message, that.message)) {
               return false;
            } else if (!Objects.equals(this.metadata, that.metadata)) {
               return false;
            } else if (!Objects.equals(this.reason, that.reason)) {
               return false;
            } else if (!Objects.equals(this.related, that.related)) {
               return false;
            } else if (!Objects.equals(this.reportingComponent, that.reportingComponent)) {
               return false;
            } else if (!Objects.equals(this.reportingInstance, that.reportingInstance)) {
               return false;
            } else if (!Objects.equals(this.series, that.series)) {
               return false;
            } else if (!Objects.equals(this.source, that.source)) {
               return false;
            } else if (!Objects.equals(this.type, that.type)) {
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
      return Objects.hash(new Object[]{this.action, this.apiVersion, this.count, this.eventTime, this.firstTimestamp, this.involvedObject, this.kind, this.lastTimestamp, this.message, this.metadata, this.reason, this.related, this.reportingComponent, this.reportingInstance, this.series, this.source, this.type, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.action != null) {
         sb.append("action:");
         sb.append(this.action + ",");
      }

      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.count != null) {
         sb.append("count:");
         sb.append(this.count + ",");
      }

      if (this.eventTime != null) {
         sb.append("eventTime:");
         sb.append(this.eventTime + ",");
      }

      if (this.firstTimestamp != null) {
         sb.append("firstTimestamp:");
         sb.append(this.firstTimestamp + ",");
      }

      if (this.involvedObject != null) {
         sb.append("involvedObject:");
         sb.append(this.involvedObject + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.lastTimestamp != null) {
         sb.append("lastTimestamp:");
         sb.append(this.lastTimestamp + ",");
      }

      if (this.message != null) {
         sb.append("message:");
         sb.append(this.message + ",");
      }

      if (this.metadata != null) {
         sb.append("metadata:");
         sb.append(this.metadata + ",");
      }

      if (this.reason != null) {
         sb.append("reason:");
         sb.append(this.reason + ",");
      }

      if (this.related != null) {
         sb.append("related:");
         sb.append(this.related + ",");
      }

      if (this.reportingComponent != null) {
         sb.append("reportingComponent:");
         sb.append(this.reportingComponent + ",");
      }

      if (this.reportingInstance != null) {
         sb.append("reportingInstance:");
         sb.append(this.reportingInstance + ",");
      }

      if (this.series != null) {
         sb.append("series:");
         sb.append(this.series + ",");
      }

      if (this.source != null) {
         sb.append("source:");
         sb.append(this.source + ",");
      }

      if (this.type != null) {
         sb.append("type:");
         sb.append(this.type + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class EventTimeNested extends MicroTimeFluent implements Nested {
      MicroTimeBuilder builder;

      EventTimeNested(MicroTime item) {
         this.builder = new MicroTimeBuilder(this, item);
      }

      public Object and() {
         return EventFluent.this.withEventTime(this.builder.build());
      }

      public Object endEventTime() {
         return this.and();
      }
   }

   public class InvolvedObjectNested extends ObjectReferenceFluent implements Nested {
      ObjectReferenceBuilder builder;

      InvolvedObjectNested(ObjectReference item) {
         this.builder = new ObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return EventFluent.this.withInvolvedObject(this.builder.build());
      }

      public Object endInvolvedObject() {
         return this.and();
      }
   }

   public class MetadataNested extends ObjectMetaFluent implements Nested {
      ObjectMetaBuilder builder;

      MetadataNested(ObjectMeta item) {
         this.builder = new ObjectMetaBuilder(this, item);
      }

      public Object and() {
         return EventFluent.this.withMetadata(this.builder.build());
      }

      public Object endMetadata() {
         return this.and();
      }
   }

   public class RelatedNested extends ObjectReferenceFluent implements Nested {
      ObjectReferenceBuilder builder;

      RelatedNested(ObjectReference item) {
         this.builder = new ObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return EventFluent.this.withRelated(this.builder.build());
      }

      public Object endRelated() {
         return this.and();
      }
   }

   public class SeriesNested extends EventSeriesFluent implements Nested {
      EventSeriesBuilder builder;

      SeriesNested(EventSeries item) {
         this.builder = new EventSeriesBuilder(this, item);
      }

      public Object and() {
         return EventFluent.this.withSeries(this.builder.build());
      }

      public Object endSeries() {
         return this.and();
      }
   }

   public class SourceNested extends EventSourceFluent implements Nested {
      EventSourceBuilder builder;

      SourceNested(EventSource item) {
         this.builder = new EventSourceBuilder(this, item);
      }

      public Object and() {
         return EventFluent.this.withSource(this.builder.build());
      }

      public Object endSource() {
         return this.and();
      }
   }
}
