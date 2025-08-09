package io.fabric8.kubernetes.api.model.batch.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.ObjectReference;
import io.fabric8.kubernetes.api.model.ObjectReferenceBuilder;
import io.fabric8.kubernetes.api.model.ObjectReferenceFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class CronJobStatusFluent extends BaseFluent {
   private ArrayList active = new ArrayList();
   private String lastScheduleTime;
   private String lastSuccessfulTime;
   private Map additionalProperties;

   public CronJobStatusFluent() {
   }

   public CronJobStatusFluent(CronJobStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(CronJobStatus instance) {
      instance = instance != null ? instance : new CronJobStatus();
      if (instance != null) {
         this.withActive(instance.getActive());
         this.withLastScheduleTime(instance.getLastScheduleTime());
         this.withLastSuccessfulTime(instance.getLastSuccessfulTime());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public CronJobStatusFluent addToActive(int index, ObjectReference item) {
      if (this.active == null) {
         this.active = new ArrayList();
      }

      ObjectReferenceBuilder builder = new ObjectReferenceBuilder(item);
      if (index >= 0 && index < this.active.size()) {
         this._visitables.get("active").add(index, builder);
         this.active.add(index, builder);
      } else {
         this._visitables.get("active").add(builder);
         this.active.add(builder);
      }

      return this;
   }

   public CronJobStatusFluent setToActive(int index, ObjectReference item) {
      if (this.active == null) {
         this.active = new ArrayList();
      }

      ObjectReferenceBuilder builder = new ObjectReferenceBuilder(item);
      if (index >= 0 && index < this.active.size()) {
         this._visitables.get("active").set(index, builder);
         this.active.set(index, builder);
      } else {
         this._visitables.get("active").add(builder);
         this.active.add(builder);
      }

      return this;
   }

   public CronJobStatusFluent addToActive(ObjectReference... items) {
      if (this.active == null) {
         this.active = new ArrayList();
      }

      for(ObjectReference item : items) {
         ObjectReferenceBuilder builder = new ObjectReferenceBuilder(item);
         this._visitables.get("active").add(builder);
         this.active.add(builder);
      }

      return this;
   }

   public CronJobStatusFluent addAllToActive(Collection items) {
      if (this.active == null) {
         this.active = new ArrayList();
      }

      for(ObjectReference item : items) {
         ObjectReferenceBuilder builder = new ObjectReferenceBuilder(item);
         this._visitables.get("active").add(builder);
         this.active.add(builder);
      }

      return this;
   }

   public CronJobStatusFluent removeFromActive(ObjectReference... items) {
      if (this.active == null) {
         return this;
      } else {
         for(ObjectReference item : items) {
            ObjectReferenceBuilder builder = new ObjectReferenceBuilder(item);
            this._visitables.get("active").remove(builder);
            this.active.remove(builder);
         }

         return this;
      }
   }

   public CronJobStatusFluent removeAllFromActive(Collection items) {
      if (this.active == null) {
         return this;
      } else {
         for(ObjectReference item : items) {
            ObjectReferenceBuilder builder = new ObjectReferenceBuilder(item);
            this._visitables.get("active").remove(builder);
            this.active.remove(builder);
         }

         return this;
      }
   }

   public CronJobStatusFluent removeMatchingFromActive(Predicate predicate) {
      if (this.active == null) {
         return this;
      } else {
         Iterator<ObjectReferenceBuilder> each = this.active.iterator();
         List visitables = this._visitables.get("active");

         while(each.hasNext()) {
            ObjectReferenceBuilder builder = (ObjectReferenceBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildActive() {
      return this.active != null ? build(this.active) : null;
   }

   public ObjectReference buildActive(int index) {
      return ((ObjectReferenceBuilder)this.active.get(index)).build();
   }

   public ObjectReference buildFirstActive() {
      return ((ObjectReferenceBuilder)this.active.get(0)).build();
   }

   public ObjectReference buildLastActive() {
      return ((ObjectReferenceBuilder)this.active.get(this.active.size() - 1)).build();
   }

   public ObjectReference buildMatchingActive(Predicate predicate) {
      for(ObjectReferenceBuilder item : this.active) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingActive(Predicate predicate) {
      for(ObjectReferenceBuilder item : this.active) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public CronJobStatusFluent withActive(List active) {
      if (this.active != null) {
         this._visitables.get("active").clear();
      }

      if (active != null) {
         this.active = new ArrayList();

         for(ObjectReference item : active) {
            this.addToActive(item);
         }
      } else {
         this.active = null;
      }

      return this;
   }

   public CronJobStatusFluent withActive(ObjectReference... active) {
      if (this.active != null) {
         this.active.clear();
         this._visitables.remove("active");
      }

      if (active != null) {
         for(ObjectReference item : active) {
            this.addToActive(item);
         }
      }

      return this;
   }

   public boolean hasActive() {
      return this.active != null && !this.active.isEmpty();
   }

   public ActiveNested addNewActive() {
      return new ActiveNested(-1, (ObjectReference)null);
   }

   public ActiveNested addNewActiveLike(ObjectReference item) {
      return new ActiveNested(-1, item);
   }

   public ActiveNested setNewActiveLike(int index, ObjectReference item) {
      return new ActiveNested(index, item);
   }

   public ActiveNested editActive(int index) {
      if (this.active.size() <= index) {
         throw new RuntimeException("Can't edit active. Index exceeds size.");
      } else {
         return this.setNewActiveLike(index, this.buildActive(index));
      }
   }

   public ActiveNested editFirstActive() {
      if (this.active.size() == 0) {
         throw new RuntimeException("Can't edit first active. The list is empty.");
      } else {
         return this.setNewActiveLike(0, this.buildActive(0));
      }
   }

   public ActiveNested editLastActive() {
      int index = this.active.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last active. The list is empty.");
      } else {
         return this.setNewActiveLike(index, this.buildActive(index));
      }
   }

   public ActiveNested editMatchingActive(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.active.size(); ++i) {
         if (predicate.test((ObjectReferenceBuilder)this.active.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching active. No match found.");
      } else {
         return this.setNewActiveLike(index, this.buildActive(index));
      }
   }

   public String getLastScheduleTime() {
      return this.lastScheduleTime;
   }

   public CronJobStatusFluent withLastScheduleTime(String lastScheduleTime) {
      this.lastScheduleTime = lastScheduleTime;
      return this;
   }

   public boolean hasLastScheduleTime() {
      return this.lastScheduleTime != null;
   }

   public String getLastSuccessfulTime() {
      return this.lastSuccessfulTime;
   }

   public CronJobStatusFluent withLastSuccessfulTime(String lastSuccessfulTime) {
      this.lastSuccessfulTime = lastSuccessfulTime;
      return this;
   }

   public boolean hasLastSuccessfulTime() {
      return this.lastSuccessfulTime != null;
   }

   public CronJobStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public CronJobStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public CronJobStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public CronJobStatusFluent removeFromAdditionalProperties(Map map) {
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

   public CronJobStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            CronJobStatusFluent that = (CronJobStatusFluent)o;
            if (!Objects.equals(this.active, that.active)) {
               return false;
            } else if (!Objects.equals(this.lastScheduleTime, that.lastScheduleTime)) {
               return false;
            } else if (!Objects.equals(this.lastSuccessfulTime, that.lastSuccessfulTime)) {
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
      return Objects.hash(new Object[]{this.active, this.lastScheduleTime, this.lastSuccessfulTime, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.active != null && !this.active.isEmpty()) {
         sb.append("active:");
         sb.append(this.active + ",");
      }

      if (this.lastScheduleTime != null) {
         sb.append("lastScheduleTime:");
         sb.append(this.lastScheduleTime + ",");
      }

      if (this.lastSuccessfulTime != null) {
         sb.append("lastSuccessfulTime:");
         sb.append(this.lastSuccessfulTime + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ActiveNested extends ObjectReferenceFluent implements Nested {
      ObjectReferenceBuilder builder;
      int index;

      ActiveNested(int index, ObjectReference item) {
         this.index = index;
         this.builder = new ObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return CronJobStatusFluent.this.setToActive(this.index, this.builder.build());
      }

      public Object endActive() {
         return this.and();
      }
   }
}
