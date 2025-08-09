package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class DeploymentStrategyFluent extends BaseFluent {
   private RollingUpdateDeploymentBuilder rollingUpdate;
   private String type;
   private Map additionalProperties;

   public DeploymentStrategyFluent() {
   }

   public DeploymentStrategyFluent(DeploymentStrategy instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(DeploymentStrategy instance) {
      instance = instance != null ? instance : new DeploymentStrategy();
      if (instance != null) {
         this.withRollingUpdate(instance.getRollingUpdate());
         this.withType(instance.getType());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public RollingUpdateDeployment buildRollingUpdate() {
      return this.rollingUpdate != null ? this.rollingUpdate.build() : null;
   }

   public DeploymentStrategyFluent withRollingUpdate(RollingUpdateDeployment rollingUpdate) {
      this._visitables.remove("rollingUpdate");
      if (rollingUpdate != null) {
         this.rollingUpdate = new RollingUpdateDeploymentBuilder(rollingUpdate);
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
      return new RollingUpdateNested((RollingUpdateDeployment)null);
   }

   public RollingUpdateNested withNewRollingUpdateLike(RollingUpdateDeployment item) {
      return new RollingUpdateNested(item);
   }

   public RollingUpdateNested editRollingUpdate() {
      return this.withNewRollingUpdateLike((RollingUpdateDeployment)Optional.ofNullable(this.buildRollingUpdate()).orElse((Object)null));
   }

   public RollingUpdateNested editOrNewRollingUpdate() {
      return this.withNewRollingUpdateLike((RollingUpdateDeployment)Optional.ofNullable(this.buildRollingUpdate()).orElse((new RollingUpdateDeploymentBuilder()).build()));
   }

   public RollingUpdateNested editOrNewRollingUpdateLike(RollingUpdateDeployment item) {
      return this.withNewRollingUpdateLike((RollingUpdateDeployment)Optional.ofNullable(this.buildRollingUpdate()).orElse(item));
   }

   public String getType() {
      return this.type;
   }

   public DeploymentStrategyFluent withType(String type) {
      this.type = type;
      return this;
   }

   public boolean hasType() {
      return this.type != null;
   }

   public DeploymentStrategyFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public DeploymentStrategyFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public DeploymentStrategyFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public DeploymentStrategyFluent removeFromAdditionalProperties(Map map) {
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

   public DeploymentStrategyFluent withAdditionalProperties(Map additionalProperties) {
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
            DeploymentStrategyFluent that = (DeploymentStrategyFluent)o;
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

   public class RollingUpdateNested extends RollingUpdateDeploymentFluent implements Nested {
      RollingUpdateDeploymentBuilder builder;

      RollingUpdateNested(RollingUpdateDeployment item) {
         this.builder = new RollingUpdateDeploymentBuilder(this, item);
      }

      public Object and() {
         return DeploymentStrategyFluent.this.withRollingUpdate(this.builder.build());
      }

      public Object endRollingUpdate() {
         return this.and();
      }
   }
}
