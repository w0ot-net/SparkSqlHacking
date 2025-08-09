package io.fabric8.kubernetes.api.model.batch.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class JobStatusFluent extends BaseFluent {
   private Integer active;
   private String completedIndexes;
   private String completionTime;
   private ArrayList conditions = new ArrayList();
   private Integer failed;
   private String failedIndexes;
   private Integer ready;
   private String startTime;
   private Integer succeeded;
   private Integer terminating;
   private UncountedTerminatedPodsBuilder uncountedTerminatedPods;
   private Map additionalProperties;

   public JobStatusFluent() {
   }

   public JobStatusFluent(JobStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(JobStatus instance) {
      instance = instance != null ? instance : new JobStatus();
      if (instance != null) {
         this.withActive(instance.getActive());
         this.withCompletedIndexes(instance.getCompletedIndexes());
         this.withCompletionTime(instance.getCompletionTime());
         this.withConditions(instance.getConditions());
         this.withFailed(instance.getFailed());
         this.withFailedIndexes(instance.getFailedIndexes());
         this.withReady(instance.getReady());
         this.withStartTime(instance.getStartTime());
         this.withSucceeded(instance.getSucceeded());
         this.withTerminating(instance.getTerminating());
         this.withUncountedTerminatedPods(instance.getUncountedTerminatedPods());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getActive() {
      return this.active;
   }

   public JobStatusFluent withActive(Integer active) {
      this.active = active;
      return this;
   }

   public boolean hasActive() {
      return this.active != null;
   }

   public String getCompletedIndexes() {
      return this.completedIndexes;
   }

   public JobStatusFluent withCompletedIndexes(String completedIndexes) {
      this.completedIndexes = completedIndexes;
      return this;
   }

   public boolean hasCompletedIndexes() {
      return this.completedIndexes != null;
   }

   public String getCompletionTime() {
      return this.completionTime;
   }

   public JobStatusFluent withCompletionTime(String completionTime) {
      this.completionTime = completionTime;
      return this;
   }

   public boolean hasCompletionTime() {
      return this.completionTime != null;
   }

   public JobStatusFluent addToConditions(int index, JobCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      JobConditionBuilder builder = new JobConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").add(index, builder);
         this.conditions.add(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public JobStatusFluent setToConditions(int index, JobCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      JobConditionBuilder builder = new JobConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").set(index, builder);
         this.conditions.set(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public JobStatusFluent addToConditions(JobCondition... items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(JobCondition item : items) {
         JobConditionBuilder builder = new JobConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public JobStatusFluent addAllToConditions(Collection items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(JobCondition item : items) {
         JobConditionBuilder builder = new JobConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public JobStatusFluent removeFromConditions(JobCondition... items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(JobCondition item : items) {
            JobConditionBuilder builder = new JobConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public JobStatusFluent removeAllFromConditions(Collection items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(JobCondition item : items) {
            JobConditionBuilder builder = new JobConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public JobStatusFluent removeMatchingFromConditions(Predicate predicate) {
      if (this.conditions == null) {
         return this;
      } else {
         Iterator<JobConditionBuilder> each = this.conditions.iterator();
         List visitables = this._visitables.get("conditions");

         while(each.hasNext()) {
            JobConditionBuilder builder = (JobConditionBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildConditions() {
      return this.conditions != null ? build(this.conditions) : null;
   }

   public JobCondition buildCondition(int index) {
      return ((JobConditionBuilder)this.conditions.get(index)).build();
   }

   public JobCondition buildFirstCondition() {
      return ((JobConditionBuilder)this.conditions.get(0)).build();
   }

   public JobCondition buildLastCondition() {
      return ((JobConditionBuilder)this.conditions.get(this.conditions.size() - 1)).build();
   }

   public JobCondition buildMatchingCondition(Predicate predicate) {
      for(JobConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingCondition(Predicate predicate) {
      for(JobConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public JobStatusFluent withConditions(List conditions) {
      if (this.conditions != null) {
         this._visitables.get("conditions").clear();
      }

      if (conditions != null) {
         this.conditions = new ArrayList();

         for(JobCondition item : conditions) {
            this.addToConditions(item);
         }
      } else {
         this.conditions = null;
      }

      return this;
   }

   public JobStatusFluent withConditions(JobCondition... conditions) {
      if (this.conditions != null) {
         this.conditions.clear();
         this._visitables.remove("conditions");
      }

      if (conditions != null) {
         for(JobCondition item : conditions) {
            this.addToConditions(item);
         }
      }

      return this;
   }

   public boolean hasConditions() {
      return this.conditions != null && !this.conditions.isEmpty();
   }

   public ConditionsNested addNewCondition() {
      return new ConditionsNested(-1, (JobCondition)null);
   }

   public ConditionsNested addNewConditionLike(JobCondition item) {
      return new ConditionsNested(-1, item);
   }

   public ConditionsNested setNewConditionLike(int index, JobCondition item) {
      return new ConditionsNested(index, item);
   }

   public ConditionsNested editCondition(int index) {
      if (this.conditions.size() <= index) {
         throw new RuntimeException("Can't edit conditions. Index exceeds size.");
      } else {
         return this.setNewConditionLike(index, this.buildCondition(index));
      }
   }

   public ConditionsNested editFirstCondition() {
      if (this.conditions.size() == 0) {
         throw new RuntimeException("Can't edit first conditions. The list is empty.");
      } else {
         return this.setNewConditionLike(0, this.buildCondition(0));
      }
   }

   public ConditionsNested editLastCondition() {
      int index = this.conditions.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last conditions. The list is empty.");
      } else {
         return this.setNewConditionLike(index, this.buildCondition(index));
      }
   }

   public ConditionsNested editMatchingCondition(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.conditions.size(); ++i) {
         if (predicate.test((JobConditionBuilder)this.conditions.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching conditions. No match found.");
      } else {
         return this.setNewConditionLike(index, this.buildCondition(index));
      }
   }

   public Integer getFailed() {
      return this.failed;
   }

   public JobStatusFluent withFailed(Integer failed) {
      this.failed = failed;
      return this;
   }

   public boolean hasFailed() {
      return this.failed != null;
   }

   public String getFailedIndexes() {
      return this.failedIndexes;
   }

   public JobStatusFluent withFailedIndexes(String failedIndexes) {
      this.failedIndexes = failedIndexes;
      return this;
   }

   public boolean hasFailedIndexes() {
      return this.failedIndexes != null;
   }

   public Integer getReady() {
      return this.ready;
   }

   public JobStatusFluent withReady(Integer ready) {
      this.ready = ready;
      return this;
   }

   public boolean hasReady() {
      return this.ready != null;
   }

   public String getStartTime() {
      return this.startTime;
   }

   public JobStatusFluent withStartTime(String startTime) {
      this.startTime = startTime;
      return this;
   }

   public boolean hasStartTime() {
      return this.startTime != null;
   }

   public Integer getSucceeded() {
      return this.succeeded;
   }

   public JobStatusFluent withSucceeded(Integer succeeded) {
      this.succeeded = succeeded;
      return this;
   }

   public boolean hasSucceeded() {
      return this.succeeded != null;
   }

   public Integer getTerminating() {
      return this.terminating;
   }

   public JobStatusFluent withTerminating(Integer terminating) {
      this.terminating = terminating;
      return this;
   }

   public boolean hasTerminating() {
      return this.terminating != null;
   }

   public UncountedTerminatedPods buildUncountedTerminatedPods() {
      return this.uncountedTerminatedPods != null ? this.uncountedTerminatedPods.build() : null;
   }

   public JobStatusFluent withUncountedTerminatedPods(UncountedTerminatedPods uncountedTerminatedPods) {
      this._visitables.remove("uncountedTerminatedPods");
      if (uncountedTerminatedPods != null) {
         this.uncountedTerminatedPods = new UncountedTerminatedPodsBuilder(uncountedTerminatedPods);
         this._visitables.get("uncountedTerminatedPods").add(this.uncountedTerminatedPods);
      } else {
         this.uncountedTerminatedPods = null;
         this._visitables.get("uncountedTerminatedPods").remove(this.uncountedTerminatedPods);
      }

      return this;
   }

   public boolean hasUncountedTerminatedPods() {
      return this.uncountedTerminatedPods != null;
   }

   public UncountedTerminatedPodsNested withNewUncountedTerminatedPods() {
      return new UncountedTerminatedPodsNested((UncountedTerminatedPods)null);
   }

   public UncountedTerminatedPodsNested withNewUncountedTerminatedPodsLike(UncountedTerminatedPods item) {
      return new UncountedTerminatedPodsNested(item);
   }

   public UncountedTerminatedPodsNested editUncountedTerminatedPods() {
      return this.withNewUncountedTerminatedPodsLike((UncountedTerminatedPods)Optional.ofNullable(this.buildUncountedTerminatedPods()).orElse((Object)null));
   }

   public UncountedTerminatedPodsNested editOrNewUncountedTerminatedPods() {
      return this.withNewUncountedTerminatedPodsLike((UncountedTerminatedPods)Optional.ofNullable(this.buildUncountedTerminatedPods()).orElse((new UncountedTerminatedPodsBuilder()).build()));
   }

   public UncountedTerminatedPodsNested editOrNewUncountedTerminatedPodsLike(UncountedTerminatedPods item) {
      return this.withNewUncountedTerminatedPodsLike((UncountedTerminatedPods)Optional.ofNullable(this.buildUncountedTerminatedPods()).orElse(item));
   }

   public JobStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public JobStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public JobStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public JobStatusFluent removeFromAdditionalProperties(Map map) {
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

   public JobStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            JobStatusFluent that = (JobStatusFluent)o;
            if (!Objects.equals(this.active, that.active)) {
               return false;
            } else if (!Objects.equals(this.completedIndexes, that.completedIndexes)) {
               return false;
            } else if (!Objects.equals(this.completionTime, that.completionTime)) {
               return false;
            } else if (!Objects.equals(this.conditions, that.conditions)) {
               return false;
            } else if (!Objects.equals(this.failed, that.failed)) {
               return false;
            } else if (!Objects.equals(this.failedIndexes, that.failedIndexes)) {
               return false;
            } else if (!Objects.equals(this.ready, that.ready)) {
               return false;
            } else if (!Objects.equals(this.startTime, that.startTime)) {
               return false;
            } else if (!Objects.equals(this.succeeded, that.succeeded)) {
               return false;
            } else if (!Objects.equals(this.terminating, that.terminating)) {
               return false;
            } else if (!Objects.equals(this.uncountedTerminatedPods, that.uncountedTerminatedPods)) {
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
      return Objects.hash(new Object[]{this.active, this.completedIndexes, this.completionTime, this.conditions, this.failed, this.failedIndexes, this.ready, this.startTime, this.succeeded, this.terminating, this.uncountedTerminatedPods, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.active != null) {
         sb.append("active:");
         sb.append(this.active + ",");
      }

      if (this.completedIndexes != null) {
         sb.append("completedIndexes:");
         sb.append(this.completedIndexes + ",");
      }

      if (this.completionTime != null) {
         sb.append("completionTime:");
         sb.append(this.completionTime + ",");
      }

      if (this.conditions != null && !this.conditions.isEmpty()) {
         sb.append("conditions:");
         sb.append(this.conditions + ",");
      }

      if (this.failed != null) {
         sb.append("failed:");
         sb.append(this.failed + ",");
      }

      if (this.failedIndexes != null) {
         sb.append("failedIndexes:");
         sb.append(this.failedIndexes + ",");
      }

      if (this.ready != null) {
         sb.append("ready:");
         sb.append(this.ready + ",");
      }

      if (this.startTime != null) {
         sb.append("startTime:");
         sb.append(this.startTime + ",");
      }

      if (this.succeeded != null) {
         sb.append("succeeded:");
         sb.append(this.succeeded + ",");
      }

      if (this.terminating != null) {
         sb.append("terminating:");
         sb.append(this.terminating + ",");
      }

      if (this.uncountedTerminatedPods != null) {
         sb.append("uncountedTerminatedPods:");
         sb.append(this.uncountedTerminatedPods + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ConditionsNested extends JobConditionFluent implements Nested {
      JobConditionBuilder builder;
      int index;

      ConditionsNested(int index, JobCondition item) {
         this.index = index;
         this.builder = new JobConditionBuilder(this, item);
      }

      public Object and() {
         return JobStatusFluent.this.setToConditions(this.index, this.builder.build());
      }

      public Object endCondition() {
         return this.and();
      }
   }

   public class UncountedTerminatedPodsNested extends UncountedTerminatedPodsFluent implements Nested {
      UncountedTerminatedPodsBuilder builder;

      UncountedTerminatedPodsNested(UncountedTerminatedPods item) {
         this.builder = new UncountedTerminatedPodsBuilder(this, item);
      }

      public Object and() {
         return JobStatusFluent.this.withUncountedTerminatedPods(this.builder.build());
      }

      public Object endUncountedTerminatedPods() {
         return this.and();
      }
   }
}
