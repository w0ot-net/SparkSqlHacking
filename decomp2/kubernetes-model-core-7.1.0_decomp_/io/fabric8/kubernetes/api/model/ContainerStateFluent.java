package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ContainerStateFluent extends BaseFluent {
   private ContainerStateRunningBuilder running;
   private ContainerStateTerminatedBuilder terminated;
   private ContainerStateWaitingBuilder waiting;
   private Map additionalProperties;

   public ContainerStateFluent() {
   }

   public ContainerStateFluent(ContainerState instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ContainerState instance) {
      instance = instance != null ? instance : new ContainerState();
      if (instance != null) {
         this.withRunning(instance.getRunning());
         this.withTerminated(instance.getTerminated());
         this.withWaiting(instance.getWaiting());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ContainerStateRunning buildRunning() {
      return this.running != null ? this.running.build() : null;
   }

   public ContainerStateFluent withRunning(ContainerStateRunning running) {
      this._visitables.remove("running");
      if (running != null) {
         this.running = new ContainerStateRunningBuilder(running);
         this._visitables.get("running").add(this.running);
      } else {
         this.running = null;
         this._visitables.get("running").remove(this.running);
      }

      return this;
   }

   public boolean hasRunning() {
      return this.running != null;
   }

   public ContainerStateFluent withNewRunning(String startedAt) {
      return this.withRunning(new ContainerStateRunning(startedAt));
   }

   public RunningNested withNewRunning() {
      return new RunningNested((ContainerStateRunning)null);
   }

   public RunningNested withNewRunningLike(ContainerStateRunning item) {
      return new RunningNested(item);
   }

   public RunningNested editRunning() {
      return this.withNewRunningLike((ContainerStateRunning)Optional.ofNullable(this.buildRunning()).orElse((Object)null));
   }

   public RunningNested editOrNewRunning() {
      return this.withNewRunningLike((ContainerStateRunning)Optional.ofNullable(this.buildRunning()).orElse((new ContainerStateRunningBuilder()).build()));
   }

   public RunningNested editOrNewRunningLike(ContainerStateRunning item) {
      return this.withNewRunningLike((ContainerStateRunning)Optional.ofNullable(this.buildRunning()).orElse(item));
   }

   public ContainerStateTerminated buildTerminated() {
      return this.terminated != null ? this.terminated.build() : null;
   }

   public ContainerStateFluent withTerminated(ContainerStateTerminated terminated) {
      this._visitables.remove("terminated");
      if (terminated != null) {
         this.terminated = new ContainerStateTerminatedBuilder(terminated);
         this._visitables.get("terminated").add(this.terminated);
      } else {
         this.terminated = null;
         this._visitables.get("terminated").remove(this.terminated);
      }

      return this;
   }

   public boolean hasTerminated() {
      return this.terminated != null;
   }

   public TerminatedNested withNewTerminated() {
      return new TerminatedNested((ContainerStateTerminated)null);
   }

   public TerminatedNested withNewTerminatedLike(ContainerStateTerminated item) {
      return new TerminatedNested(item);
   }

   public TerminatedNested editTerminated() {
      return this.withNewTerminatedLike((ContainerStateTerminated)Optional.ofNullable(this.buildTerminated()).orElse((Object)null));
   }

   public TerminatedNested editOrNewTerminated() {
      return this.withNewTerminatedLike((ContainerStateTerminated)Optional.ofNullable(this.buildTerminated()).orElse((new ContainerStateTerminatedBuilder()).build()));
   }

   public TerminatedNested editOrNewTerminatedLike(ContainerStateTerminated item) {
      return this.withNewTerminatedLike((ContainerStateTerminated)Optional.ofNullable(this.buildTerminated()).orElse(item));
   }

   public ContainerStateWaiting buildWaiting() {
      return this.waiting != null ? this.waiting.build() : null;
   }

   public ContainerStateFluent withWaiting(ContainerStateWaiting waiting) {
      this._visitables.remove("waiting");
      if (waiting != null) {
         this.waiting = new ContainerStateWaitingBuilder(waiting);
         this._visitables.get("waiting").add(this.waiting);
      } else {
         this.waiting = null;
         this._visitables.get("waiting").remove(this.waiting);
      }

      return this;
   }

   public boolean hasWaiting() {
      return this.waiting != null;
   }

   public ContainerStateFluent withNewWaiting(String message, String reason) {
      return this.withWaiting(new ContainerStateWaiting(message, reason));
   }

   public WaitingNested withNewWaiting() {
      return new WaitingNested((ContainerStateWaiting)null);
   }

   public WaitingNested withNewWaitingLike(ContainerStateWaiting item) {
      return new WaitingNested(item);
   }

   public WaitingNested editWaiting() {
      return this.withNewWaitingLike((ContainerStateWaiting)Optional.ofNullable(this.buildWaiting()).orElse((Object)null));
   }

   public WaitingNested editOrNewWaiting() {
      return this.withNewWaitingLike((ContainerStateWaiting)Optional.ofNullable(this.buildWaiting()).orElse((new ContainerStateWaitingBuilder()).build()));
   }

   public WaitingNested editOrNewWaitingLike(ContainerStateWaiting item) {
      return this.withNewWaitingLike((ContainerStateWaiting)Optional.ofNullable(this.buildWaiting()).orElse(item));
   }

   public ContainerStateFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ContainerStateFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ContainerStateFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ContainerStateFluent removeFromAdditionalProperties(Map map) {
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

   public ContainerStateFluent withAdditionalProperties(Map additionalProperties) {
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
            ContainerStateFluent that = (ContainerStateFluent)o;
            if (!Objects.equals(this.running, that.running)) {
               return false;
            } else if (!Objects.equals(this.terminated, that.terminated)) {
               return false;
            } else if (!Objects.equals(this.waiting, that.waiting)) {
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
      return Objects.hash(new Object[]{this.running, this.terminated, this.waiting, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.running != null) {
         sb.append("running:");
         sb.append(this.running + ",");
      }

      if (this.terminated != null) {
         sb.append("terminated:");
         sb.append(this.terminated + ",");
      }

      if (this.waiting != null) {
         sb.append("waiting:");
         sb.append(this.waiting + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class RunningNested extends ContainerStateRunningFluent implements Nested {
      ContainerStateRunningBuilder builder;

      RunningNested(ContainerStateRunning item) {
         this.builder = new ContainerStateRunningBuilder(this, item);
      }

      public Object and() {
         return ContainerStateFluent.this.withRunning(this.builder.build());
      }

      public Object endRunning() {
         return this.and();
      }
   }

   public class TerminatedNested extends ContainerStateTerminatedFluent implements Nested {
      ContainerStateTerminatedBuilder builder;

      TerminatedNested(ContainerStateTerminated item) {
         this.builder = new ContainerStateTerminatedBuilder(this, item);
      }

      public Object and() {
         return ContainerStateFluent.this.withTerminated(this.builder.build());
      }

      public Object endTerminated() {
         return this.and();
      }
   }

   public class WaitingNested extends ContainerStateWaitingFluent implements Nested {
      ContainerStateWaitingBuilder builder;

      WaitingNested(ContainerStateWaiting item) {
         this.builder = new ContainerStateWaitingBuilder(this, item);
      }

      public Object and() {
         return ContainerStateFluent.this.withWaiting(this.builder.build());
      }

      public Object endWaiting() {
         return this.and();
      }
   }
}
