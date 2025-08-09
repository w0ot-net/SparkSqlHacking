package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class LifecycleFluent extends BaseFluent {
   private LifecycleHandlerBuilder postStart;
   private LifecycleHandlerBuilder preStop;
   private Map additionalProperties;

   public LifecycleFluent() {
   }

   public LifecycleFluent(Lifecycle instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Lifecycle instance) {
      instance = instance != null ? instance : new Lifecycle();
      if (instance != null) {
         this.withPostStart(instance.getPostStart());
         this.withPreStop(instance.getPreStop());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public LifecycleHandler buildPostStart() {
      return this.postStart != null ? this.postStart.build() : null;
   }

   public LifecycleFluent withPostStart(LifecycleHandler postStart) {
      this._visitables.remove("postStart");
      if (postStart != null) {
         this.postStart = new LifecycleHandlerBuilder(postStart);
         this._visitables.get("postStart").add(this.postStart);
      } else {
         this.postStart = null;
         this._visitables.get("postStart").remove(this.postStart);
      }

      return this;
   }

   public boolean hasPostStart() {
      return this.postStart != null;
   }

   public PostStartNested withNewPostStart() {
      return new PostStartNested((LifecycleHandler)null);
   }

   public PostStartNested withNewPostStartLike(LifecycleHandler item) {
      return new PostStartNested(item);
   }

   public PostStartNested editPostStart() {
      return this.withNewPostStartLike((LifecycleHandler)Optional.ofNullable(this.buildPostStart()).orElse((Object)null));
   }

   public PostStartNested editOrNewPostStart() {
      return this.withNewPostStartLike((LifecycleHandler)Optional.ofNullable(this.buildPostStart()).orElse((new LifecycleHandlerBuilder()).build()));
   }

   public PostStartNested editOrNewPostStartLike(LifecycleHandler item) {
      return this.withNewPostStartLike((LifecycleHandler)Optional.ofNullable(this.buildPostStart()).orElse(item));
   }

   public LifecycleHandler buildPreStop() {
      return this.preStop != null ? this.preStop.build() : null;
   }

   public LifecycleFluent withPreStop(LifecycleHandler preStop) {
      this._visitables.remove("preStop");
      if (preStop != null) {
         this.preStop = new LifecycleHandlerBuilder(preStop);
         this._visitables.get("preStop").add(this.preStop);
      } else {
         this.preStop = null;
         this._visitables.get("preStop").remove(this.preStop);
      }

      return this;
   }

   public boolean hasPreStop() {
      return this.preStop != null;
   }

   public PreStopNested withNewPreStop() {
      return new PreStopNested((LifecycleHandler)null);
   }

   public PreStopNested withNewPreStopLike(LifecycleHandler item) {
      return new PreStopNested(item);
   }

   public PreStopNested editPreStop() {
      return this.withNewPreStopLike((LifecycleHandler)Optional.ofNullable(this.buildPreStop()).orElse((Object)null));
   }

   public PreStopNested editOrNewPreStop() {
      return this.withNewPreStopLike((LifecycleHandler)Optional.ofNullable(this.buildPreStop()).orElse((new LifecycleHandlerBuilder()).build()));
   }

   public PreStopNested editOrNewPreStopLike(LifecycleHandler item) {
      return this.withNewPreStopLike((LifecycleHandler)Optional.ofNullable(this.buildPreStop()).orElse(item));
   }

   public LifecycleFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public LifecycleFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public LifecycleFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public LifecycleFluent removeFromAdditionalProperties(Map map) {
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

   public LifecycleFluent withAdditionalProperties(Map additionalProperties) {
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
            LifecycleFluent that = (LifecycleFluent)o;
            if (!Objects.equals(this.postStart, that.postStart)) {
               return false;
            } else if (!Objects.equals(this.preStop, that.preStop)) {
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
      return Objects.hash(new Object[]{this.postStart, this.preStop, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.postStart != null) {
         sb.append("postStart:");
         sb.append(this.postStart + ",");
      }

      if (this.preStop != null) {
         sb.append("preStop:");
         sb.append(this.preStop + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class PostStartNested extends LifecycleHandlerFluent implements Nested {
      LifecycleHandlerBuilder builder;

      PostStartNested(LifecycleHandler item) {
         this.builder = new LifecycleHandlerBuilder(this, item);
      }

      public Object and() {
         return LifecycleFluent.this.withPostStart(this.builder.build());
      }

      public Object endPostStart() {
         return this.and();
      }
   }

   public class PreStopNested extends LifecycleHandlerFluent implements Nested {
      LifecycleHandlerBuilder builder;

      PreStopNested(LifecycleHandler item) {
         this.builder = new LifecycleHandlerBuilder(this, item);
      }

      public Object and() {
         return LifecycleFluent.this.withPreStop(this.builder.build());
      }

      public Object endPreStop() {
         return this.and();
      }
   }
}
