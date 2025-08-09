package io.fabric8.kubernetes.api.model.events.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.EventSource;
import io.fabric8.kubernetes.api.model.MicroTime;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.api.model.ObjectMetaFluent;
import io.fabric8.kubernetes.api.model.ObjectReference;
import io.fabric8.kubernetes.api.model.ObjectReferenceBuilder;
import io.fabric8.kubernetes.api.model.ObjectReferenceFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class EventFluent extends BaseFluent {
   private String action;
   private String apiVersion;
   private Integer deprecatedCount;
   private String deprecatedFirstTimestamp;
   private String deprecatedLastTimestamp;
   private EventSource deprecatedSource;
   private MicroTime eventTime;
   private String kind;
   private ObjectMetaBuilder metadata;
   private String note;
   private String reason;
   private ObjectReferenceBuilder regarding;
   private ObjectReferenceBuilder related;
   private String reportingController;
   private String reportingInstance;
   private EventSeriesBuilder series;
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
         this.withDeprecatedCount(instance.getDeprecatedCount());
         this.withDeprecatedFirstTimestamp(instance.getDeprecatedFirstTimestamp());
         this.withDeprecatedLastTimestamp(instance.getDeprecatedLastTimestamp());
         this.withDeprecatedSource(instance.getDeprecatedSource());
         this.withEventTime(instance.getEventTime());
         this.withKind(instance.getKind());
         this.withMetadata(instance.getMetadata());
         this.withNote(instance.getNote());
         this.withReason(instance.getReason());
         this.withRegarding(instance.getRegarding());
         this.withRelated(instance.getRelated());
         this.withReportingController(instance.getReportingController());
         this.withReportingInstance(instance.getReportingInstance());
         this.withSeries(instance.getSeries());
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

   public Integer getDeprecatedCount() {
      return this.deprecatedCount;
   }

   public EventFluent withDeprecatedCount(Integer deprecatedCount) {
      this.deprecatedCount = deprecatedCount;
      return this;
   }

   public boolean hasDeprecatedCount() {
      return this.deprecatedCount != null;
   }

   public String getDeprecatedFirstTimestamp() {
      return this.deprecatedFirstTimestamp;
   }

   public EventFluent withDeprecatedFirstTimestamp(String deprecatedFirstTimestamp) {
      this.deprecatedFirstTimestamp = deprecatedFirstTimestamp;
      return this;
   }

   public boolean hasDeprecatedFirstTimestamp() {
      return this.deprecatedFirstTimestamp != null;
   }

   public String getDeprecatedLastTimestamp() {
      return this.deprecatedLastTimestamp;
   }

   public EventFluent withDeprecatedLastTimestamp(String deprecatedLastTimestamp) {
      this.deprecatedLastTimestamp = deprecatedLastTimestamp;
      return this;
   }

   public boolean hasDeprecatedLastTimestamp() {
      return this.deprecatedLastTimestamp != null;
   }

   public EventSource getDeprecatedSource() {
      return this.deprecatedSource;
   }

   public EventFluent withDeprecatedSource(EventSource deprecatedSource) {
      this.deprecatedSource = deprecatedSource;
      return this;
   }

   public boolean hasDeprecatedSource() {
      return this.deprecatedSource != null;
   }

   public EventFluent withNewDeprecatedSource(String component, String host) {
      return this.withDeprecatedSource(new EventSource(component, host));
   }

   public MicroTime getEventTime() {
      return this.eventTime;
   }

   public EventFluent withEventTime(MicroTime eventTime) {
      this.eventTime = eventTime;
      return this;
   }

   public boolean hasEventTime() {
      return this.eventTime != null;
   }

   public EventFluent withNewEventTime(String time) {
      return this.withEventTime(new MicroTime(time));
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

   public String getNote() {
      return this.note;
   }

   public EventFluent withNote(String note) {
      this.note = note;
      return this;
   }

   public boolean hasNote() {
      return this.note != null;
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

   public ObjectReference buildRegarding() {
      return this.regarding != null ? this.regarding.build() : null;
   }

   public EventFluent withRegarding(ObjectReference regarding) {
      this._visitables.remove("regarding");
      if (regarding != null) {
         this.regarding = new ObjectReferenceBuilder(regarding);
         this._visitables.get("regarding").add(this.regarding);
      } else {
         this.regarding = null;
         this._visitables.get("regarding").remove(this.regarding);
      }

      return this;
   }

   public boolean hasRegarding() {
      return this.regarding != null;
   }

   public RegardingNested withNewRegarding() {
      return new RegardingNested((ObjectReference)null);
   }

   public RegardingNested withNewRegardingLike(ObjectReference item) {
      return new RegardingNested(item);
   }

   public RegardingNested editRegarding() {
      return this.withNewRegardingLike((ObjectReference)Optional.ofNullable(this.buildRegarding()).orElse((Object)null));
   }

   public RegardingNested editOrNewRegarding() {
      return this.withNewRegardingLike((ObjectReference)Optional.ofNullable(this.buildRegarding()).orElse((new ObjectReferenceBuilder()).build()));
   }

   public RegardingNested editOrNewRegardingLike(ObjectReference item) {
      return this.withNewRegardingLike((ObjectReference)Optional.ofNullable(this.buildRegarding()).orElse(item));
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

   public String getReportingController() {
      return this.reportingController;
   }

   public EventFluent withReportingController(String reportingController) {
      this.reportingController = reportingController;
      return this;
   }

   public boolean hasReportingController() {
      return this.reportingController != null;
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
            } else if (!Objects.equals(this.deprecatedCount, that.deprecatedCount)) {
               return false;
            } else if (!Objects.equals(this.deprecatedFirstTimestamp, that.deprecatedFirstTimestamp)) {
               return false;
            } else if (!Objects.equals(this.deprecatedLastTimestamp, that.deprecatedLastTimestamp)) {
               return false;
            } else if (!Objects.equals(this.deprecatedSource, that.deprecatedSource)) {
               return false;
            } else if (!Objects.equals(this.eventTime, that.eventTime)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.metadata, that.metadata)) {
               return false;
            } else if (!Objects.equals(this.note, that.note)) {
               return false;
            } else if (!Objects.equals(this.reason, that.reason)) {
               return false;
            } else if (!Objects.equals(this.regarding, that.regarding)) {
               return false;
            } else if (!Objects.equals(this.related, that.related)) {
               return false;
            } else if (!Objects.equals(this.reportingController, that.reportingController)) {
               return false;
            } else if (!Objects.equals(this.reportingInstance, that.reportingInstance)) {
               return false;
            } else if (!Objects.equals(this.series, that.series)) {
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
      return Objects.hash(new Object[]{this.action, this.apiVersion, this.deprecatedCount, this.deprecatedFirstTimestamp, this.deprecatedLastTimestamp, this.deprecatedSource, this.eventTime, this.kind, this.metadata, this.note, this.reason, this.regarding, this.related, this.reportingController, this.reportingInstance, this.series, this.type, this.additionalProperties, super.hashCode()});
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

      if (this.deprecatedCount != null) {
         sb.append("deprecatedCount:");
         sb.append(this.deprecatedCount + ",");
      }

      if (this.deprecatedFirstTimestamp != null) {
         sb.append("deprecatedFirstTimestamp:");
         sb.append(this.deprecatedFirstTimestamp + ",");
      }

      if (this.deprecatedLastTimestamp != null) {
         sb.append("deprecatedLastTimestamp:");
         sb.append(this.deprecatedLastTimestamp + ",");
      }

      if (this.deprecatedSource != null) {
         sb.append("deprecatedSource:");
         sb.append(this.deprecatedSource + ",");
      }

      if (this.eventTime != null) {
         sb.append("eventTime:");
         sb.append(this.eventTime + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.metadata != null) {
         sb.append("metadata:");
         sb.append(this.metadata + ",");
      }

      if (this.note != null) {
         sb.append("note:");
         sb.append(this.note + ",");
      }

      if (this.reason != null) {
         sb.append("reason:");
         sb.append(this.reason + ",");
      }

      if (this.regarding != null) {
         sb.append("regarding:");
         sb.append(this.regarding + ",");
      }

      if (this.related != null) {
         sb.append("related:");
         sb.append(this.related + ",");
      }

      if (this.reportingController != null) {
         sb.append("reportingController:");
         sb.append(this.reportingController + ",");
      }

      if (this.reportingInstance != null) {
         sb.append("reportingInstance:");
         sb.append(this.reportingInstance + ",");
      }

      if (this.series != null) {
         sb.append("series:");
         sb.append(this.series + ",");
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

   public class RegardingNested extends ObjectReferenceFluent implements Nested {
      ObjectReferenceBuilder builder;

      RegardingNested(ObjectReference item) {
         this.builder = new ObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return EventFluent.this.withRegarding(this.builder.build());
      }

      public Object endRegarding() {
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
}
