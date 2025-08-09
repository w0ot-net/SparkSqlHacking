package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class DaemonSetUpdateStrategyFluent extends BaseFluent {
   private RollingUpdateDaemonSetBuilder rollingUpdate;
   private String type;
   private Map additionalProperties;

   public DaemonSetUpdateStrategyFluent() {
   }

   public DaemonSetUpdateStrategyFluent(DaemonSetUpdateStrategy instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(DaemonSetUpdateStrategy instance) {
      instance = instance != null ? instance : new DaemonSetUpdateStrategy();
      if (instance != null) {
         this.withRollingUpdate(instance.getRollingUpdate());
         this.withType(instance.getType());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public RollingUpdateDaemonSet buildRollingUpdate() {
      return this.rollingUpdate != null ? this.rollingUpdate.build() : null;
   }

   public DaemonSetUpdateStrategyFluent withRollingUpdate(RollingUpdateDaemonSet rollingUpdate) {
      this._visitables.remove("rollingUpdate");
      if (rollingUpdate != null) {
         this.rollingUpdate = new RollingUpdateDaemonSetBuilder(rollingUpdate);
         this._visitables.get("rollingUpdate").add(this.rollingUpdate);
      } else {
         this.rollingUpdate = null;
         this._visitables.get("rollingUpdate").remove(this.rollingUpdate);
      }

      return this;
   }

   public boolean hasRollingUpdate() {
      return this.rollingUpdate != null;
   }

   public RollingUpdateNested withNewRollingUpdate() {
      return new RollingUpdateNested((RollingUpdateDaemonSet)null);
   }

   public RollingUpdateNested withNewRollingUpdateLike(RollingUpdateDaemonSet item) {
      return new RollingUpdateNested(item);
   }

   public RollingUpdateNested editRollingUpdate() {
      return this.withNewRollingUpdateLike((RollingUpdateDaemonSet)Optional.ofNullable(this.buildRollingUpdate()).orElse((Object)null));
   }

   public RollingUpdateNested editOrNewRollingUpdate() {
      return this.withNewRollingUpdateLike((RollingUpdateDaemonSet)Optional.ofNullable(this.buildRollingUpdate()).orElse((new RollingUpdateDaemonSetBuilder()).build()));
   }

   public RollingUpdateNested editOrNewRollingUpdateLike(RollingUpdateDaemonSet item) {
      return this.withNewRollingUpdateLike((RollingUpdateDaemonSet)Optional.ofNullable(this.buildRollingUpdate()).orElse(item));
   }

   public String getType() {
      return this.type;
   }

   public DaemonSetUpdateStrategyFluent withType(String type) {
      this.type = type;
      return this;
   }

   public boolean hasType() {
      return this.type != null;
   }

   public DaemonSetUpdateStrategyFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public DaemonSetUpdateStrategyFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public DaemonSetUpdateStrategyFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public DaemonSetUpdateStrategyFluent removeFromAdditionalProperties(Map map) {
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

   public DaemonSetUpdateStrategyFluent withAdditionalProperties(Map additionalProperties) {
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
            DaemonSetUpdateStrategyFluent that = (DaemonSetUpdateStrategyFluent)o;
            if (!Objects.equals(this.rollingUpdate, that.rollingUpdate)) {
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
      return Objects.hash(new Object[]{this.rollingUpdate, this.type, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.rollingUpdate != null) {
         sb.append("rollingUpdate:");
         sb.append(this.rollingUpdate + ",");
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

   public class RollingUpdateNested extends RollingUpdateDaemonSetFluent implements Nested {
      RollingUpdateDaemonSetBuilder builder;

      RollingUpdateNested(RollingUpdateDaemonSet item) {
         this.builder = new RollingUpdateDaemonSetBuilder(this, item);
      }

      public Object and() {
         return DaemonSetUpdateStrategyFluent.this.withRollingUpdate(this.builder.build());
      }

      public Object endRollingUpdate() {
         return this.and();
      }
   }
}
