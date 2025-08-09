package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class EnvFromSourceFluent extends BaseFluent {
   private ConfigMapEnvSourceBuilder configMapRef;
   private String prefix;
   private SecretEnvSourceBuilder secretRef;
   private Map additionalProperties;

   public EnvFromSourceFluent() {
   }

   public EnvFromSourceFluent(EnvFromSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(EnvFromSource instance) {
      instance = instance != null ? instance : new EnvFromSource();
      if (instance != null) {
         this.withConfigMapRef(instance.getConfigMapRef());
         this.withPrefix(instance.getPrefix());
         this.withSecretRef(instance.getSecretRef());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ConfigMapEnvSource buildConfigMapRef() {
      return this.configMapRef != null ? this.configMapRef.build() : null;
   }

   public EnvFromSourceFluent withConfigMapRef(ConfigMapEnvSource configMapRef) {
      this._visitables.remove("configMapRef");
      if (configMapRef != null) {
         this.configMapRef = new ConfigMapEnvSourceBuilder(configMapRef);
         this._visitables.get("configMapRef").add(this.configMapRef);
      } else {
         this.configMapRef = null;
         this._visitables.get("configMapRef").remove(this.configMapRef);
      }

      return this;
   }

   public boolean hasConfigMapRef() {
      return this.configMapRef != null;
   }

   public EnvFromSourceFluent withNewConfigMapRef(String name, Boolean optional) {
      return this.withConfigMapRef(new ConfigMapEnvSource(name, optional));
   }

   public ConfigMapRefNested withNewConfigMapRef() {
      return new ConfigMapRefNested((ConfigMapEnvSource)null);
   }

   public ConfigMapRefNested withNewConfigMapRefLike(ConfigMapEnvSource item) {
      return new ConfigMapRefNested(item);
   }

   public ConfigMapRefNested editConfigMapRef() {
      return this.withNewConfigMapRefLike((ConfigMapEnvSource)Optional.ofNullable(this.buildConfigMapRef()).orElse((Object)null));
   }

   public ConfigMapRefNested editOrNewConfigMapRef() {
      return this.withNewConfigMapRefLike((ConfigMapEnvSource)Optional.ofNullable(this.buildConfigMapRef()).orElse((new ConfigMapEnvSourceBuilder()).build()));
   }

   public ConfigMapRefNested editOrNewConfigMapRefLike(ConfigMapEnvSource item) {
      return this.withNewConfigMapRefLike((ConfigMapEnvSource)Optional.ofNullable(this.buildConfigMapRef()).orElse(item));
   }

   public String getPrefix() {
      return this.prefix;
   }

   public EnvFromSourceFluent withPrefix(String prefix) {
      this.prefix = prefix;
      return this;
   }

   public boolean hasPrefix() {
      return this.prefix != null;
   }

   public SecretEnvSource buildSecretRef() {
      return this.secretRef != null ? this.secretRef.build() : null;
   }

   public EnvFromSourceFluent withSecretRef(SecretEnvSource secretRef) {
      this._visitables.remove("secretRef");
      if (secretRef != null) {
         this.secretRef = new SecretEnvSourceBuilder(secretRef);
         this._visitables.get("secretRef").add(this.secretRef);
      } else {
         this.secretRef = null;
         this._visitables.get("secretRef").remove(this.secretRef);
      }

      return this;
   }

   public boolean hasSecretRef() {
      return this.secretRef != null;
   }

   public EnvFromSourceFluent withNewSecretRef(String name, Boolean optional) {
      return this.withSecretRef(new SecretEnvSource(name, optional));
   }

   public SecretRefNested withNewSecretRef() {
      return new SecretRefNested((SecretEnvSource)null);
   }

   public SecretRefNested withNewSecretRefLike(SecretEnvSource item) {
      return new SecretRefNested(item);
   }

   public SecretRefNested editSecretRef() {
      return this.withNewSecretRefLike((SecretEnvSource)Optional.ofNullable(this.buildSecretRef()).orElse((Object)null));
   }

   public SecretRefNested editOrNewSecretRef() {
      return this.withNewSecretRefLike((SecretEnvSource)Optional.ofNullable(this.buildSecretRef()).orElse((new SecretEnvSourceBuilder()).build()));
   }

   public SecretRefNested editOrNewSecretRefLike(SecretEnvSource item) {
      return this.withNewSecretRefLike((SecretEnvSource)Optional.ofNullable(this.buildSecretRef()).orElse(item));
   }

   public EnvFromSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public EnvFromSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public EnvFromSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public EnvFromSourceFluent removeFromAdditionalProperties(Map map) {
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

   public EnvFromSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            EnvFromSourceFluent that = (EnvFromSourceFluent)o;
            if (!Objects.equals(this.configMapRef, that.configMapRef)) {
               return false;
            } else if (!Objects.equals(this.prefix, that.prefix)) {
               return false;
            } else if (!Objects.equals(this.secretRef, that.secretRef)) {
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
      return Objects.hash(new Object[]{this.configMapRef, this.prefix, this.secretRef, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.configMapRef != null) {
         sb.append("configMapRef:");
         sb.append(this.configMapRef + ",");
      }

      if (this.prefix != null) {
         sb.append("prefix:");
         sb.append(this.prefix + ",");
      }

      if (this.secretRef != null) {
         sb.append("secretRef:");
         sb.append(this.secretRef + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ConfigMapRefNested extends ConfigMapEnvSourceFluent implements Nested {
      ConfigMapEnvSourceBuilder builder;

      ConfigMapRefNested(ConfigMapEnvSource item) {
         this.builder = new ConfigMapEnvSourceBuilder(this, item);
      }

      public Object and() {
         return EnvFromSourceFluent.this.withConfigMapRef(this.builder.build());
      }

      public Object endConfigMapRef() {
         return this.and();
      }
   }

   public class SecretRefNested extends SecretEnvSourceFluent implements Nested {
      SecretEnvSourceBuilder builder;

      SecretRefNested(SecretEnvSource item) {
         this.builder = new SecretEnvSourceBuilder(this, item);
      }

      public Object and() {
         return EnvFromSourceFluent.this.withSecretRef(this.builder.build());
      }

      public Object endSecretRef() {
         return this.and();
      }
   }
}
