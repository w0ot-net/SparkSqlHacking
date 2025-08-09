package io.fabric8.kubernetes.api.model.batch.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class UncountedTerminatedPodsFluent extends BaseFluent {
   private List failed = new ArrayList();
   private List succeeded = new ArrayList();
   private Map additionalProperties;

   public UncountedTerminatedPodsFluent() {
   }

   public UncountedTerminatedPodsFluent(UncountedTerminatedPods instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(UncountedTerminatedPods instance) {
      instance = instance != null ? instance : new UncountedTerminatedPods();
      if (instance != null) {
         this.withFailed(instance.getFailed());
         this.withSucceeded(instance.getSucceeded());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public UncountedTerminatedPodsFluent addToFailed(int index, String item) {
      if (this.failed == null) {
         this.failed = new ArrayList();
      }

      this.failed.add(index, item);
      return this;
   }

   public UncountedTerminatedPodsFluent setToFailed(int index, String item) {
      if (this.failed == null) {
         this.failed = new ArrayList();
      }

      this.failed.set(index, item);
      return this;
   }

   public UncountedTerminatedPodsFluent addToFailed(String... items) {
      if (this.failed == null) {
         this.failed = new ArrayList();
      }

      for(String item : items) {
         this.failed.add(item);
      }

      return this;
   }

   public UncountedTerminatedPodsFluent addAllToFailed(Collection items) {
      if (this.failed == null) {
         this.failed = new ArrayList();
      }

      for(String item : items) {
         this.failed.add(item);
      }

      return this;
   }

   public UncountedTerminatedPodsFluent removeFromFailed(String... items) {
      if (this.failed == null) {
         return this;
      } else {
         for(String item : items) {
            this.failed.remove(item);
         }

         return this;
      }
   }

   public UncountedTerminatedPodsFluent removeAllFromFailed(Collection items) {
      if (this.failed == null) {
         return this;
      } else {
         for(String item : items) {
            this.failed.remove(item);
         }

         return this;
      }
   }

   public List getFailed() {
      return this.failed;
   }

   public String getFailed(int index) {
      return (String)this.failed.get(index);
   }

   public String getFirstFailed() {
      return (String)this.failed.get(0);
   }

   public String getLastFailed() {
      return (String)this.failed.get(this.failed.size() - 1);
   }

   public String getMatchingFailed(Predicate predicate) {
      for(String item : this.failed) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingFailed(Predicate predicate) {
      for(String item : this.failed) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public UncountedTerminatedPodsFluent withFailed(List failed) {
      if (failed != null) {
         this.failed = new ArrayList();

         for(String item : failed) {
            this.addToFailed(item);
         }
      } else {
         this.failed = null;
      }

      return this;
   }

   public UncountedTerminatedPodsFluent withFailed(String... failed) {
      if (this.failed != null) {
         this.failed.clear();
         this._visitables.remove("failed");
      }

      if (failed != null) {
         for(String item : failed) {
            this.addToFailed(item);
         }
      }

      return this;
   }

   public boolean hasFailed() {
      return this.failed != null && !this.failed.isEmpty();
   }

   public UncountedTerminatedPodsFluent addToSucceeded(int index, String item) {
      if (this.succeeded == null) {
         this.succeeded = new ArrayList();
      }

      this.succeeded.add(index, item);
      return this;
   }

   public UncountedTerminatedPodsFluent setToSucceeded(int index, String item) {
      if (this.succeeded == null) {
         this.succeeded = new ArrayList();
      }

      this.succeeded.set(index, item);
      return this;
   }

   public UncountedTerminatedPodsFluent addToSucceeded(String... items) {
      if (this.succeeded == null) {
         this.succeeded = new ArrayList();
      }

      for(String item : items) {
         this.succeeded.add(item);
      }

      return this;
   }

   public UncountedTerminatedPodsFluent addAllToSucceeded(Collection items) {
      if (this.succeeded == null) {
         this.succeeded = new ArrayList();
      }

      for(String item : items) {
         this.succeeded.add(item);
      }

      return this;
   }

   public UncountedTerminatedPodsFluent removeFromSucceeded(String... items) {
      if (this.succeeded == null) {
         return this;
      } else {
         for(String item : items) {
            this.succeeded.remove(item);
         }

         return this;
      }
   }

   public UncountedTerminatedPodsFluent removeAllFromSucceeded(Collection items) {
      if (this.succeeded == null) {
         return this;
      } else {
         for(String item : items) {
            this.succeeded.remove(item);
         }

         return this;
      }
   }

   public List getSucceeded() {
      return this.succeeded;
   }

   public String getSucceeded(int index) {
      return (String)this.succeeded.get(index);
   }

   public String getFirstSucceeded() {
      return (String)this.succeeded.get(0);
   }

   public String getLastSucceeded() {
      return (String)this.succeeded.get(this.succeeded.size() - 1);
   }

   public String getMatchingSucceeded(Predicate predicate) {
      for(String item : this.succeeded) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingSucceeded(Predicate predicate) {
      for(String item : this.succeeded) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public UncountedTerminatedPodsFluent withSucceeded(List succeeded) {
      if (succeeded != null) {
         this.succeeded = new ArrayList();

         for(String item : succeeded) {
            this.addToSucceeded(item);
         }
      } else {
         this.succeeded = null;
      }

      return this;
   }

   public UncountedTerminatedPodsFluent withSucceeded(String... succeeded) {
      if (this.succeeded != null) {
         this.succeeded.clear();
         this._visitables.remove("succeeded");
      }

      if (succeeded != null) {
         for(String item : succeeded) {
            this.addToSucceeded(item);
         }
      }

      return this;
   }

   public boolean hasSucceeded() {
      return this.succeeded != null && !this.succeeded.isEmpty();
   }

   public UncountedTerminatedPodsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public UncountedTerminatedPodsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public UncountedTerminatedPodsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public UncountedTerminatedPodsFluent removeFromAdditionalProperties(Map map) {
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

   public UncountedTerminatedPodsFluent withAdditionalProperties(Map additionalProperties) {
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
            UncountedTerminatedPodsFluent that = (UncountedTerminatedPodsFluent)o;
            if (!Objects.equals(this.failed, that.failed)) {
               return false;
            } else if (!Objects.equals(this.succeeded, that.succeeded)) {
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
      return Objects.hash(new Object[]{this.failed, this.succeeded, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.failed != null && !this.failed.isEmpty()) {
         sb.append("failed:");
         sb.append(this.failed + ",");
      }

      if (this.succeeded != null && !this.succeeded.isEmpty()) {
         sb.append("succeeded:");
         sb.append(this.succeeded + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
