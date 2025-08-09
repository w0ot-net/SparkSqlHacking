package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class StatefulSetUpdateStrategyFluent extends BaseFluent {
   private RollingUpdateStatefulSetStrategyBuilder rollingUpdate;
   private String type;
   private Map additionalProperties;

   public StatefulSetUpdateStrategyFluent() {
   }

   public StatefulSetUpdateStrategyFluent(StatefulSetUpdateStrategy instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(StatefulSetUpdateStrategy instance) {
      instance = instance != null ? instance : new StatefulSetUpdateStrategy();
      if (instance != null) {
         this.withRollingUpdate(instance.getRollingUpdate());
         this.withType(instance.getType());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public RollingUpdateStatefulSetStrategy buildRollingUpdate() {
      return this.rollingUpdate != null ? this.rollingUpdate.build() : null;
   }

   public StatefulSetUpdateStrategyFluent withRollingUpdate(RollingUpdateStatefulSetStrategy rollingUpdate) {
      this._visitables.remove("rollingUpdate");
      if (rollingUpdate != null) {
         this.rollingUpdate = new RollingUpdateStatefulSetStrategyBuilder(rollingUpdate);
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
      return new RollingUpdateNested((RollingUpdateStatefulSetStrategy)null);
   }

   public RollingUpdateNested withNewRollingUpdateLike(RollingUpdateStatefulSetStrategy item) {
      return new RollingUpdateNested(item);
   }

   public RollingUpdateNested editRollingUpdate() {
      return this.withNewRollingUpdateLike((RollingUpdateStatefulSetStrategy)Optional.ofNullable(this.buildRollingUpdate()).orElse((Object)null));
   }

   public RollingUpdateNested editOrNewRollingUpdate() {
      return this.withNewRollingUpdateLike((RollingUpdateStatefulSetStrategy)Optional.ofNullable(this.buildRollingUpdate()).orElse((new RollingUpdateStatefulSetStrategyBuilder()).build()));
   }

   public RollingUpdateNested editOrNewRollingUpdateLike(RollingUpdateStatefulSetStrategy item) {
      return this.withNewRollingUpdateLike((RollingUpdateStatefulSetStrategy)Optional.ofNullable(this.buildRollingUpdate()).orElse(item));
   }

   public String getType() {
      return this.type;
   }

   public StatefulSetUpdateStrategyFluent withType(String type) {
      this.type = type;
      return this;
   }

   public boolean hasType() {
      return this.type != null;
   }

   public StatefulSetUpdateStrategyFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public StatefulSetUpdateStrategyFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public StatefulSetUpdateStrategyFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public StatefulSetUpdateStrategyFluent removeFromAdditionalProperties(Map map) {
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

   public StatefulSetUpdateStrategyFluent withAdditionalProperties(Map additionalProperties) {
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
            StatefulSetUpdateStrategyFluent that = (StatefulSetUpdateStrategyFluent)o;
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

   public class RollingUpdateNested extends RollingUpdateStatefulSetStrategyFluent implements Nested {
      RollingUpdateStatefulSetStrategyBuilder builder;

      RollingUpdateNested(RollingUpdateStatefulSetStrategy item) {
         this.builder = new RollingUpdateStatefulSetStrategyBuilder(this, item);
      }

      public Object and() {
         return StatefulSetUpdateStrategyFluent.this.withRollingUpdate(this.builder.build());
      }

      public Object endRollingUpdate() {
         return this.and();
      }
   }
}
