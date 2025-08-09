package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class DeleteOptionsFluent extends BaseFluent {
   private String apiVersion;
   private List dryRun = new ArrayList();
   private Long gracePeriodSeconds;
   private Boolean ignoreStoreReadErrorWithClusterBreakingPotential;
   private String kind;
   private Boolean orphanDependents;
   private PreconditionsBuilder preconditions;
   private String propagationPolicy;
   private Map additionalProperties;

   public DeleteOptionsFluent() {
   }

   public DeleteOptionsFluent(DeleteOptions instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(DeleteOptions instance) {
      instance = instance != null ? instance : new DeleteOptions();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withDryRun(instance.getDryRun());
         this.withGracePeriodSeconds(instance.getGracePeriodSeconds());
         this.withIgnoreStoreReadErrorWithClusterBreakingPotential(instance.getIgnoreStoreReadErrorWithClusterBreakingPotential());
         this.withKind(instance.getKind());
         this.withOrphanDependents(instance.getOrphanDependents());
         this.withPreconditions(instance.getPreconditions());
         this.withPropagationPolicy(instance.getPropagationPolicy());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public DeleteOptionsFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public DeleteOptionsFluent addToDryRun(int index, String item) {
      if (this.dryRun == null) {
         this.dryRun = new ArrayList();
      }

      this.dryRun.add(index, item);
      return this;
   }

   public DeleteOptionsFluent setToDryRun(int index, String item) {
      if (this.dryRun == null) {
         this.dryRun = new ArrayList();
      }

      this.dryRun.set(index, item);
      return this;
   }

   public DeleteOptionsFluent addToDryRun(String... items) {
      if (this.dryRun == null) {
         this.dryRun = new ArrayList();
      }

      for(String item : items) {
         this.dryRun.add(item);
      }

      return this;
   }

   public DeleteOptionsFluent addAllToDryRun(Collection items) {
      if (this.dryRun == null) {
         this.dryRun = new ArrayList();
      }

      for(String item : items) {
         this.dryRun.add(item);
      }

      return this;
   }

   public DeleteOptionsFluent removeFromDryRun(String... items) {
      if (this.dryRun == null) {
         return this;
      } else {
         for(String item : items) {
            this.dryRun.remove(item);
         }

         return this;
      }
   }

   public DeleteOptionsFluent removeAllFromDryRun(Collection items) {
      if (this.dryRun == null) {
         return this;
      } else {
         for(String item : items) {
            this.dryRun.remove(item);
         }

         return this;
      }
   }

   public List getDryRun() {
      return this.dryRun;
   }

   public String getDryRun(int index) {
      return (String)this.dryRun.get(index);
   }

   public String getFirstDryRun() {
      return (String)this.dryRun.get(0);
   }

   public String getLastDryRun() {
      return (String)this.dryRun.get(this.dryRun.size() - 1);
   }

   public String getMatchingDryRun(Predicate predicate) {
      for(String item : this.dryRun) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingDryRun(Predicate predicate) {
      for(String item : this.dryRun) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public DeleteOptionsFluent withDryRun(List dryRun) {
      if (dryRun != null) {
         this.dryRun = new ArrayList();

         for(String item : dryRun) {
            this.addToDryRun(item);
         }
      } else {
         this.dryRun = null;
      }

      return this;
   }

   public DeleteOptionsFluent withDryRun(String... dryRun) {
      if (this.dryRun != null) {
         this.dryRun.clear();
         this._visitables.remove("dryRun");
      }

      if (dryRun != null) {
         for(String item : dryRun) {
            this.addToDryRun(item);
         }
      }

      return this;
   }

   public boolean hasDryRun() {
      return this.dryRun != null && !this.dryRun.isEmpty();
   }

   public Long getGracePeriodSeconds() {
      return this.gracePeriodSeconds;
   }

   public DeleteOptionsFluent withGracePeriodSeconds(Long gracePeriodSeconds) {
      this.gracePeriodSeconds = gracePeriodSeconds;
      return this;
   }

   public boolean hasGracePeriodSeconds() {
      return this.gracePeriodSeconds != null;
   }

   public Boolean getIgnoreStoreReadErrorWithClusterBreakingPotential() {
      return this.ignoreStoreReadErrorWithClusterBreakingPotential;
   }

   public DeleteOptionsFluent withIgnoreStoreReadErrorWithClusterBreakingPotential(Boolean ignoreStoreReadErrorWithClusterBreakingPotential) {
      this.ignoreStoreReadErrorWithClusterBreakingPotential = ignoreStoreReadErrorWithClusterBreakingPotential;
      return this;
   }

   public boolean hasIgnoreStoreReadErrorWithClusterBreakingPotential() {
      return this.ignoreStoreReadErrorWithClusterBreakingPotential != null;
   }

   public String getKind() {
      return this.kind;
   }

   public DeleteOptionsFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public Boolean getOrphanDependents() {
      return this.orphanDependents;
   }

   public DeleteOptionsFluent withOrphanDependents(Boolean orphanDependents) {
      this.orphanDependents = orphanDependents;
      return this;
   }

   public boolean hasOrphanDependents() {
      return this.orphanDependents != null;
   }

   public Preconditions buildPreconditions() {
      return this.preconditions != null ? this.preconditions.build() : null;
   }

   public DeleteOptionsFluent withPreconditions(Preconditions preconditions) {
      this._visitables.remove("preconditions");
      if (preconditions != null) {
         this.preconditions = new PreconditionsBuilder(preconditions);
         this._visitables.get("preconditions").add(this.preconditions);
      } else {
         this.preconditions = null;
         this._visitables.get("preconditions").remove(this.preconditions);
      }

      return this;
   }

   public boolean hasPreconditions() {
      return this.preconditions != null;
   }

   public DeleteOptionsFluent withNewPreconditions(String resourceVersion, String uid) {
      return this.withPreconditions(new Preconditions(resourceVersion, uid));
   }

   public PreconditionsNested withNewPreconditions() {
      return new PreconditionsNested((Preconditions)null);
   }

   public PreconditionsNested withNewPreconditionsLike(Preconditions item) {
      return new PreconditionsNested(item);
   }

   public PreconditionsNested editPreconditions() {
      return this.withNewPreconditionsLike((Preconditions)Optional.ofNullable(this.buildPreconditions()).orElse((Object)null));
   }

   public PreconditionsNested editOrNewPreconditions() {
      return this.withNewPreconditionsLike((Preconditions)Optional.ofNullable(this.buildPreconditions()).orElse((new PreconditionsBuilder()).build()));
   }

   public PreconditionsNested editOrNewPreconditionsLike(Preconditions item) {
      return this.withNewPreconditionsLike((Preconditions)Optional.ofNullable(this.buildPreconditions()).orElse(item));
   }

   public String getPropagationPolicy() {
      return this.propagationPolicy;
   }

   public DeleteOptionsFluent withPropagationPolicy(String propagationPolicy) {
      this.propagationPolicy = propagationPolicy;
      return this;
   }

   public boolean hasPropagationPolicy() {
      return this.propagationPolicy != null;
   }

   public DeleteOptionsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public DeleteOptionsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public DeleteOptionsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public DeleteOptionsFluent removeFromAdditionalProperties(Map map) {
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

   public DeleteOptionsFluent withAdditionalProperties(Map additionalProperties) {
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
            DeleteOptionsFluent that = (DeleteOptionsFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.dryRun, that.dryRun)) {
               return false;
            } else if (!Objects.equals(this.gracePeriodSeconds, that.gracePeriodSeconds)) {
               return false;
            } else if (!Objects.equals(this.ignoreStoreReadErrorWithClusterBreakingPotential, that.ignoreStoreReadErrorWithClusterBreakingPotential)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.orphanDependents, that.orphanDependents)) {
               return false;
            } else if (!Objects.equals(this.preconditions, that.preconditions)) {
               return false;
            } else if (!Objects.equals(this.propagationPolicy, that.propagationPolicy)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.dryRun, this.gracePeriodSeconds, this.ignoreStoreReadErrorWithClusterBreakingPotential, this.kind, this.orphanDependents, this.preconditions, this.propagationPolicy, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.dryRun != null && !this.dryRun.isEmpty()) {
         sb.append("dryRun:");
         sb.append(this.dryRun + ",");
      }

      if (this.gracePeriodSeconds != null) {
         sb.append("gracePeriodSeconds:");
         sb.append(this.gracePeriodSeconds + ",");
      }

      if (this.ignoreStoreReadErrorWithClusterBreakingPotential != null) {
         sb.append("ignoreStoreReadErrorWithClusterBreakingPotential:");
         sb.append(this.ignoreStoreReadErrorWithClusterBreakingPotential + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.orphanDependents != null) {
         sb.append("orphanDependents:");
         sb.append(this.orphanDependents + ",");
      }

      if (this.preconditions != null) {
         sb.append("preconditions:");
         sb.append(this.preconditions + ",");
      }

      if (this.propagationPolicy != null) {
         sb.append("propagationPolicy:");
         sb.append(this.propagationPolicy + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public DeleteOptionsFluent withIgnoreStoreReadErrorWithClusterBreakingPotential() {
      return this.withIgnoreStoreReadErrorWithClusterBreakingPotential(true);
   }

   public DeleteOptionsFluent withOrphanDependents() {
      return this.withOrphanDependents(true);
   }

   public class PreconditionsNested extends PreconditionsFluent implements Nested {
      PreconditionsBuilder builder;

      PreconditionsNested(Preconditions item) {
         this.builder = new PreconditionsBuilder(this, item);
      }

      public Object and() {
         return DeleteOptionsFluent.this.withPreconditions(this.builder.build());
      }

      public Object endPreconditions() {
         return this.and();
      }
   }
}
