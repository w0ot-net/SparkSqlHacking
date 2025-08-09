package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class DeploymentRollbackFluent extends BaseFluent {
   private String apiVersion;
   private String kind;
   private String name;
   private RollbackConfigBuilder rollbackTo;
   private Map updatedAnnotations;
   private Map additionalProperties;

   public DeploymentRollbackFluent() {
   }

   public DeploymentRollbackFluent(DeploymentRollback instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(DeploymentRollback instance) {
      instance = instance != null ? instance : new DeploymentRollback();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withKind(instance.getKind());
         this.withName(instance.getName());
         this.withRollbackTo(instance.getRollbackTo());
         this.withUpdatedAnnotations(instance.getUpdatedAnnotations());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public DeploymentRollbackFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public String getKind() {
      return this.kind;
   }

   public DeploymentRollbackFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public String getName() {
      return this.name;
   }

   public DeploymentRollbackFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public RollbackConfig buildRollbackTo() {
      return this.rollbackTo != null ? this.rollbackTo.build() : null;
   }

   public DeploymentRollbackFluent withRollbackTo(RollbackConfig rollbackTo) {
      this._visitables.remove("rollbackTo");
      if (rollbackTo != null) {
         this.rollbackTo = new RollbackConfigBuilder(rollbackTo);
         this._visitables.get("rollbackTo").add(this.rollbackTo);
      } else {
         this.rollbackTo = null;
         this._visitables.get("rollbackTo").remove(this.rollbackTo);
      }

      return this;
   }

   public boolean hasRollbackTo() {
      return this.rollbackTo != null;
   }

   public DeploymentRollbackFluent withNewRollbackTo(Long revision) {
      return this.withRollbackTo(new RollbackConfig(revision));
   }

   public RollbackToNested withNewRollbackTo() {
      return new RollbackToNested((RollbackConfig)null);
   }

   public RollbackToNested withNewRollbackToLike(RollbackConfig item) {
      return new RollbackToNested(item);
   }

   public RollbackToNested editRollbackTo() {
      return this.withNewRollbackToLike((RollbackConfig)Optional.ofNullable(this.buildRollbackTo()).orElse((Object)null));
   }

   public RollbackToNested editOrNewRollbackTo() {
      return this.withNewRollbackToLike((RollbackConfig)Optional.ofNullable(this.buildRollbackTo()).orElse((new RollbackConfigBuilder()).build()));
   }

   public RollbackToNested editOrNewRollbackToLike(RollbackConfig item) {
      return this.withNewRollbackToLike((RollbackConfig)Optional.ofNullable(this.buildRollbackTo()).orElse(item));
   }

   public DeploymentRollbackFluent addToUpdatedAnnotations(String key, String value) {
      if (this.updatedAnnotations == null && key != null && value != null) {
         this.updatedAnnotations = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.updatedAnnotations.put(key, value);
      }

      return this;
   }

   public DeploymentRollbackFluent addToUpdatedAnnotations(Map map) {
      if (this.updatedAnnotations == null && map != null) {
         this.updatedAnnotations = new LinkedHashMap();
      }

      if (map != null) {
         this.updatedAnnotations.putAll(map);
      }

      return this;
   }

   public DeploymentRollbackFluent removeFromUpdatedAnnotations(String key) {
      if (this.updatedAnnotations == null) {
         return this;
      } else {
         if (key != null && this.updatedAnnotations != null) {
            this.updatedAnnotations.remove(key);
         }

         return this;
      }
   }

   public DeploymentRollbackFluent removeFromUpdatedAnnotations(Map map) {
      if (this.updatedAnnotations == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.updatedAnnotations != null) {
                  this.updatedAnnotations.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getUpdatedAnnotations() {
      return this.updatedAnnotations;
   }

   public DeploymentRollbackFluent withUpdatedAnnotations(Map updatedAnnotations) {
      if (updatedAnnotations == null) {
         this.updatedAnnotations = null;
      } else {
         this.updatedAnnotations = new LinkedHashMap(updatedAnnotations);
      }

      return this;
   }

   public boolean hasUpdatedAnnotations() {
      return this.updatedAnnotations != null;
   }

   public DeploymentRollbackFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public DeploymentRollbackFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public DeploymentRollbackFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public DeploymentRollbackFluent removeFromAdditionalProperties(Map map) {
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

   public DeploymentRollbackFluent withAdditionalProperties(Map additionalProperties) {
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
            DeploymentRollbackFluent that = (DeploymentRollbackFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.rollbackTo, that.rollbackTo)) {
               return false;
            } else if (!Objects.equals(this.updatedAnnotations, that.updatedAnnotations)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.kind, this.name, this.rollbackTo, this.updatedAnnotations, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.rollbackTo != null) {
         sb.append("rollbackTo:");
         sb.append(this.rollbackTo + ",");
      }

      if (this.updatedAnnotations != null && !this.updatedAnnotations.isEmpty()) {
         sb.append("updatedAnnotations:");
         sb.append(this.updatedAnnotations + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class RollbackToNested extends RollbackConfigFluent implements Nested {
      RollbackConfigBuilder builder;

      RollbackToNested(RollbackConfig item) {
         this.builder = new RollbackConfigBuilder(this, item);
      }

      public Object and() {
         return DeploymentRollbackFluent.this.withRollbackTo(this.builder.build());
      }

      public Object endRollbackTo() {
         return this.and();
      }
   }
}
