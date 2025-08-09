package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class EventSeriesFluent extends BaseFluent {
   private Integer count;
   private MicroTimeBuilder lastObservedTime;
   private Map additionalProperties;

   public EventSeriesFluent() {
   }

   public EventSeriesFluent(EventSeries instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(EventSeries instance) {
      instance = instance != null ? instance : new EventSeries();
      if (instance != null) {
         this.withCount(instance.getCount());
         this.withLastObservedTime(instance.getLastObservedTime());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getCount() {
      return this.count;
   }

   public EventSeriesFluent withCount(Integer count) {
      this.count = count;
      return this;
   }

   public boolean hasCount() {
      return this.count != null;
   }

   public MicroTime buildLastObservedTime() {
      return this.lastObservedTime != null ? this.lastObservedTime.build() : null;
   }

   public EventSeriesFluent withLastObservedTime(MicroTime lastObservedTime) {
      this._visitables.remove("lastObservedTime");
      if (lastObservedTime != null) {
         this.lastObservedTime = new MicroTimeBuilder(lastObservedTime);
         this._visitables.get("lastObservedTime").add(this.lastObservedTime);
      } else {
         this.lastObservedTime = null;
         this._visitables.get("lastObservedTime").remove(this.lastObservedTime);
      }

      return this;
   }

   public boolean hasLastObservedTime() {
      return this.lastObservedTime != null;
   }

   public EventSeriesFluent withNewLastObservedTime(String time) {
      return this.withLastObservedTime(new MicroTime(time));
   }

   public LastObservedTimeNested withNewLastObservedTime() {
      return new LastObservedTimeNested((MicroTime)null);
   }

   public LastObservedTimeNested withNewLastObservedTimeLike(MicroTime item) {
      return new LastObservedTimeNested(item);
   }

   public LastObservedTimeNested editLastObservedTime() {
      return this.withNewLastObservedTimeLike((MicroTime)Optional.ofNullable(this.buildLastObservedTime()).orElse((Object)null));
   }

   public LastObservedTimeNested editOrNewLastObservedTime() {
      return this.withNewLastObservedTimeLike((MicroTime)Optional.ofNullable(this.buildLastObservedTime()).orElse((new MicroTimeBuilder()).build()));
   }

   public LastObservedTimeNested editOrNewLastObservedTimeLike(MicroTime item) {
      return this.withNewLastObservedTimeLike((MicroTime)Optional.ofNullable(this.buildLastObservedTime()).orElse(item));
   }

   public EventSeriesFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public EventSeriesFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public EventSeriesFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public EventSeriesFluent removeFromAdditionalProperties(Map map) {
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

   public EventSeriesFluent withAdditionalProperties(Map additionalProperties) {
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
            EventSeriesFluent that = (EventSeriesFluent)o;
            if (!Objects.equals(this.count, that.count)) {
               return false;
            } else if (!Objects.equals(this.lastObservedTime, that.lastObservedTime)) {
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
      return Objects.hash(new Object[]{this.count, this.lastObservedTime, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.count != null) {
         sb.append("count:");
         sb.append(this.count + ",");
      }

      if (this.lastObservedTime != null) {
         sb.append("lastObservedTime:");
         sb.append(this.lastObservedTime + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class LastObservedTimeNested extends MicroTimeFluent implements Nested {
      MicroTimeBuilder builder;

      LastObservedTimeNested(MicroTime item) {
         this.builder = new MicroTimeBuilder(this, item);
      }

      public Object and() {
         return EventSeriesFluent.this.withLastObservedTime(this.builder.build());
      }

      public Object endLastObservedTime() {
         return this.and();
      }
   }
}
