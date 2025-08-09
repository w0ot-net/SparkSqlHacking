package io.fabric8.kubernetes.api.model.node.v1alpha1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class RuntimeClassSpecFluent extends BaseFluent {
   private OverheadBuilder overhead;
   private String runtimeHandler;
   private SchedulingBuilder scheduling;
   private Map additionalProperties;

   public RuntimeClassSpecFluent() {
   }

   public RuntimeClassSpecFluent(RuntimeClassSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(RuntimeClassSpec instance) {
      instance = instance != null ? instance : new RuntimeClassSpec();
      if (instance != null) {
         this.withOverhead(instance.getOverhead());
         this.withRuntimeHandler(instance.getRuntimeHandler());
         this.withScheduling(instance.getScheduling());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Overhead buildOverhead() {
      return this.overhead != null ? this.overhead.build() : null;
   }

   public RuntimeClassSpecFluent withOverhead(Overhead overhead) {
      this._visitables.remove("overhead");
      if (overhead != null) {
         this.overhead = new OverheadBuilder(overhead);
         this._visitables.get("overhead").add(this.overhead);
      } else {
         this.overhead = null;
         this._visitables.get("overhead").remove(this.overhead);
      }

      return this;
   }

   public boolean hasOverhead() {
      return this.overhead != null;
   }

   public OverheadNested withNewOverhead() {
      return new OverheadNested((Overhead)null);
   }

   public OverheadNested withNewOverheadLike(Overhead item) {
      return new OverheadNested(item);
   }

   public OverheadNested editOverhead() {
      return this.withNewOverheadLike((Overhead)Optional.ofNullable(this.buildOverhead()).orElse((Object)null));
   }

   public OverheadNested editOrNewOverhead() {
      return this.withNewOverheadLike((Overhead)Optional.ofNullable(this.buildOverhead()).orElse((new OverheadBuilder()).build()));
   }

   public OverheadNested editOrNewOverheadLike(Overhead item) {
      return this.withNewOverheadLike((Overhead)Optional.ofNullable(this.buildOverhead()).orElse(item));
   }

   public String getRuntimeHandler() {
      return this.runtimeHandler;
   }

   public RuntimeClassSpecFluent withRuntimeHandler(String runtimeHandler) {
      this.runtimeHandler = runtimeHandler;
      return this;
   }

   public boolean hasRuntimeHandler() {
      return this.runtimeHandler != null;
   }

   public Scheduling buildScheduling() {
      return this.scheduling != null ? this.scheduling.build() : null;
   }

   public RuntimeClassSpecFluent withScheduling(Scheduling scheduling) {
      this._visitables.remove("scheduling");
      if (scheduling != null) {
         this.scheduling = new SchedulingBuilder(scheduling);
         this._visitables.get("scheduling").add(this.scheduling);
      } else {
         this.scheduling = null;
         this._visitables.get("scheduling").remove(this.scheduling);
      }

      return this;
   }

   public boolean hasScheduling() {
      return this.scheduling != null;
   }

   public SchedulingNested withNewScheduling() {
      return new SchedulingNested((Scheduling)null);
   }

   public SchedulingNested withNewSchedulingLike(Scheduling item) {
      return new SchedulingNested(item);
   }

   public SchedulingNested editScheduling() {
      return this.withNewSchedulingLike((Scheduling)Optional.ofNullable(this.buildScheduling()).orElse((Object)null));
   }

   public SchedulingNested editOrNewScheduling() {
      return this.withNewSchedulingLike((Scheduling)Optional.ofNullable(this.buildScheduling()).orElse((new SchedulingBuilder()).build()));
   }

   public SchedulingNested editOrNewSchedulingLike(Scheduling item) {
      return this.withNewSchedulingLike((Scheduling)Optional.ofNullable(this.buildScheduling()).orElse(item));
   }

   public RuntimeClassSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public RuntimeClassSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public RuntimeClassSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public RuntimeClassSpecFluent removeFromAdditionalProperties(Map map) {
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

   public RuntimeClassSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            RuntimeClassSpecFluent that = (RuntimeClassSpecFluent)o;
            if (!Objects.equals(this.overhead, that.overhead)) {
               return false;
            } else if (!Objects.equals(this.runtimeHandler, that.runtimeHandler)) {
               return false;
            } else if (!Objects.equals(this.scheduling, that.scheduling)) {
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
      return Objects.hash(new Object[]{this.overhead, this.runtimeHandler, this.scheduling, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.overhead != null) {
         sb.append("overhead:");
         sb.append(this.overhead + ",");
      }

      if (this.runtimeHandler != null) {
         sb.append("runtimeHandler:");
         sb.append(this.runtimeHandler + ",");
      }

      if (this.scheduling != null) {
         sb.append("scheduling:");
         sb.append(this.scheduling + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class OverheadNested extends OverheadFluent implements Nested {
      OverheadBuilder builder;

      OverheadNested(Overhead item) {
         this.builder = new OverheadBuilder(this, item);
      }

      public Object and() {
         return RuntimeClassSpecFluent.this.withOverhead(this.builder.build());
      }

      public Object endOverhead() {
         return this.and();
      }
   }

   public class SchedulingNested extends SchedulingFluent implements Nested {
      SchedulingBuilder builder;

      SchedulingNested(Scheduling item) {
         this.builder = new SchedulingBuilder(this, item);
      }

      public Object and() {
         return RuntimeClassSpecFluent.this.withScheduling(this.builder.build());
      }

      public Object endScheduling() {
         return this.and();
      }
   }
}
