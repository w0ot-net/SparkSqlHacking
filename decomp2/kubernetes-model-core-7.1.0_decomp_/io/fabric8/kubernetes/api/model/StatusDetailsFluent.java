package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class StatusDetailsFluent extends BaseFluent {
   private ArrayList causes = new ArrayList();
   private String group;
   private String kind;
   private String name;
   private Integer retryAfterSeconds;
   private String uid;
   private Map additionalProperties;

   public StatusDetailsFluent() {
   }

   public StatusDetailsFluent(StatusDetails instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(StatusDetails instance) {
      instance = instance != null ? instance : new StatusDetails();
      if (instance != null) {
         this.withCauses(instance.getCauses());
         this.withGroup(instance.getGroup());
         this.withKind(instance.getKind());
         this.withName(instance.getName());
         this.withRetryAfterSeconds(instance.getRetryAfterSeconds());
         this.withUid(instance.getUid());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public StatusDetailsFluent addToCauses(int index, StatusCause item) {
      if (this.causes == null) {
         this.causes = new ArrayList();
      }

      StatusCauseBuilder builder = new StatusCauseBuilder(item);
      if (index >= 0 && index < this.causes.size()) {
         this._visitables.get("causes").add(index, builder);
         this.causes.add(index, builder);
      } else {
         this._visitables.get("causes").add(builder);
         this.causes.add(builder);
      }

      return this;
   }

   public StatusDetailsFluent setToCauses(int index, StatusCause item) {
      if (this.causes == null) {
         this.causes = new ArrayList();
      }

      StatusCauseBuilder builder = new StatusCauseBuilder(item);
      if (index >= 0 && index < this.causes.size()) {
         this._visitables.get("causes").set(index, builder);
         this.causes.set(index, builder);
      } else {
         this._visitables.get("causes").add(builder);
         this.causes.add(builder);
      }

      return this;
   }

   public StatusDetailsFluent addToCauses(StatusCause... items) {
      if (this.causes == null) {
         this.causes = new ArrayList();
      }

      for(StatusCause item : items) {
         StatusCauseBuilder builder = new StatusCauseBuilder(item);
         this._visitables.get("causes").add(builder);
         this.causes.add(builder);
      }

      return this;
   }

   public StatusDetailsFluent addAllToCauses(Collection items) {
      if (this.causes == null) {
         this.causes = new ArrayList();
      }

      for(StatusCause item : items) {
         StatusCauseBuilder builder = new StatusCauseBuilder(item);
         this._visitables.get("causes").add(builder);
         this.causes.add(builder);
      }

      return this;
   }

   public StatusDetailsFluent removeFromCauses(StatusCause... items) {
      if (this.causes == null) {
         return this;
      } else {
         for(StatusCause item : items) {
            StatusCauseBuilder builder = new StatusCauseBuilder(item);
            this._visitables.get("causes").remove(builder);
            this.causes.remove(builder);
         }

         return this;
      }
   }

   public StatusDetailsFluent removeAllFromCauses(Collection items) {
      if (this.causes == null) {
         return this;
      } else {
         for(StatusCause item : items) {
            StatusCauseBuilder builder = new StatusCauseBuilder(item);
            this._visitables.get("causes").remove(builder);
            this.causes.remove(builder);
         }

         return this;
      }
   }

   public StatusDetailsFluent removeMatchingFromCauses(Predicate predicate) {
      if (this.causes == null) {
         return this;
      } else {
         Iterator<StatusCauseBuilder> each = this.causes.iterator();
         List visitables = this._visitables.get("causes");

         while(each.hasNext()) {
            StatusCauseBuilder builder = (StatusCauseBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildCauses() {
      return this.causes != null ? build(this.causes) : null;
   }

   public StatusCause buildCause(int index) {
      return ((StatusCauseBuilder)this.causes.get(index)).build();
   }

   public StatusCause buildFirstCause() {
      return ((StatusCauseBuilder)this.causes.get(0)).build();
   }

   public StatusCause buildLastCause() {
      return ((StatusCauseBuilder)this.causes.get(this.causes.size() - 1)).build();
   }

   public StatusCause buildMatchingCause(Predicate predicate) {
      for(StatusCauseBuilder item : this.causes) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingCause(Predicate predicate) {
      for(StatusCauseBuilder item : this.causes) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public StatusDetailsFluent withCauses(List causes) {
      if (this.causes != null) {
         this._visitables.get("causes").clear();
      }

      if (causes != null) {
         this.causes = new ArrayList();

         for(StatusCause item : causes) {
            this.addToCauses(item);
         }
      } else {
         this.causes = null;
      }

      return this;
   }

   public StatusDetailsFluent withCauses(StatusCause... causes) {
      if (this.causes != null) {
         this.causes.clear();
         this._visitables.remove("causes");
      }

      if (causes != null) {
         for(StatusCause item : causes) {
            this.addToCauses(item);
         }
      }

      return this;
   }

   public boolean hasCauses() {
      return this.causes != null && !this.causes.isEmpty();
   }

   public StatusDetailsFluent addNewCause(String field, String message, String reason) {
      return this.addToCauses(new StatusCause(field, message, reason));
   }

   public CausesNested addNewCause() {
      return new CausesNested(-1, (StatusCause)null);
   }

   public CausesNested addNewCauseLike(StatusCause item) {
      return new CausesNested(-1, item);
   }

   public CausesNested setNewCauseLike(int index, StatusCause item) {
      return new CausesNested(index, item);
   }

   public CausesNested editCause(int index) {
      if (this.causes.size() <= index) {
         throw new RuntimeException("Can't edit causes. Index exceeds size.");
      } else {
         return this.setNewCauseLike(index, this.buildCause(index));
      }
   }

   public CausesNested editFirstCause() {
      if (this.causes.size() == 0) {
         throw new RuntimeException("Can't edit first causes. The list is empty.");
      } else {
         return this.setNewCauseLike(0, this.buildCause(0));
      }
   }

   public CausesNested editLastCause() {
      int index = this.causes.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last causes. The list is empty.");
      } else {
         return this.setNewCauseLike(index, this.buildCause(index));
      }
   }

   public CausesNested editMatchingCause(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.causes.size(); ++i) {
         if (predicate.test((StatusCauseBuilder)this.causes.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching causes. No match found.");
      } else {
         return this.setNewCauseLike(index, this.buildCause(index));
      }
   }

   public String getGroup() {
      return this.group;
   }

   public StatusDetailsFluent withGroup(String group) {
      this.group = group;
      return this;
   }

   public boolean hasGroup() {
      return this.group != null;
   }

   public String getKind() {
      return this.kind;
   }

   public StatusDetailsFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public String getName() {
      return this.name;
   }

   public StatusDetailsFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public Integer getRetryAfterSeconds() {
      return this.retryAfterSeconds;
   }

   public StatusDetailsFluent withRetryAfterSeconds(Integer retryAfterSeconds) {
      this.retryAfterSeconds = retryAfterSeconds;
      return this;
   }

   public boolean hasRetryAfterSeconds() {
      return this.retryAfterSeconds != null;
   }

   public String getUid() {
      return this.uid;
   }

   public StatusDetailsFluent withUid(String uid) {
      this.uid = uid;
      return this;
   }

   public boolean hasUid() {
      return this.uid != null;
   }

   public StatusDetailsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public StatusDetailsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public StatusDetailsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public StatusDetailsFluent removeFromAdditionalProperties(Map map) {
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

   public StatusDetailsFluent withAdditionalProperties(Map additionalProperties) {
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
            StatusDetailsFluent that = (StatusDetailsFluent)o;
            if (!Objects.equals(this.causes, that.causes)) {
               return false;
            } else if (!Objects.equals(this.group, that.group)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.retryAfterSeconds, that.retryAfterSeconds)) {
               return false;
            } else if (!Objects.equals(this.uid, that.uid)) {
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
      return Objects.hash(new Object[]{this.causes, this.group, this.kind, this.name, this.retryAfterSeconds, this.uid, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.causes != null && !this.causes.isEmpty()) {
         sb.append("causes:");
         sb.append(this.causes + ",");
      }

      if (this.group != null) {
         sb.append("group:");
         sb.append(this.group + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.retryAfterSeconds != null) {
         sb.append("retryAfterSeconds:");
         sb.append(this.retryAfterSeconds + ",");
      }

      if (this.uid != null) {
         sb.append("uid:");
         sb.append(this.uid + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class CausesNested extends StatusCauseFluent implements Nested {
      StatusCauseBuilder builder;
      int index;

      CausesNested(int index, StatusCause item) {
         this.index = index;
         this.builder = new StatusCauseBuilder(this, item);
      }

      public Object and() {
         return StatusDetailsFluent.this.setToCauses(this.index, this.builder.build());
      }

      public Object endCause() {
         return this.and();
      }
   }
}
