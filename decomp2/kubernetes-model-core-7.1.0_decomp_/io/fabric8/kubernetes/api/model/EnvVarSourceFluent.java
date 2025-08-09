package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class EnvVarSourceFluent extends BaseFluent {
   private ConfigMapKeySelectorBuilder configMapKeyRef;
   private ObjectFieldSelectorBuilder fieldRef;
   private ResourceFieldSelectorBuilder resourceFieldRef;
   private SecretKeySelectorBuilder secretKeyRef;
   private Map additionalProperties;

   public EnvVarSourceFluent() {
   }

   public EnvVarSourceFluent(EnvVarSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(EnvVarSource instance) {
      instance = instance != null ? instance : new EnvVarSource();
      if (instance != null) {
         this.withConfigMapKeyRef(instance.getConfigMapKeyRef());
         this.withFieldRef(instance.getFieldRef());
         this.withResourceFieldRef(instance.getResourceFieldRef());
         this.withSecretKeyRef(instance.getSecretKeyRef());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ConfigMapKeySelector buildConfigMapKeyRef() {
      return this.configMapKeyRef != null ? this.configMapKeyRef.build() : null;
   }

   public EnvVarSourceFluent withConfigMapKeyRef(ConfigMapKeySelector configMapKeyRef) {
      this._visitables.remove("configMapKeyRef");
      if (configMapKeyRef != null) {
         this.configMapKeyRef = new ConfigMapKeySelectorBuilder(configMapKeyRef);
         this._visitables.get("configMapKeyRef").add(this.configMapKeyRef);
      } else {
         this.configMapKeyRef = null;
         this._visitables.get("configMapKeyRef").remove(this.configMapKeyRef);
      }

      return this;
   }

   public boolean hasConfigMapKeyRef() {
      return this.configMapKeyRef != null;
   }

   public EnvVarSourceFluent withNewConfigMapKeyRef(String key, String name, Boolean optional) {
      return this.withConfigMapKeyRef(new ConfigMapKeySelector(key, name, optional));
   }

   public ConfigMapKeyRefNested withNewConfigMapKeyRef() {
      return new ConfigMapKeyRefNested((ConfigMapKeySelector)null);
   }

   public ConfigMapKeyRefNested withNewConfigMapKeyRefLike(ConfigMapKeySelector item) {
      return new ConfigMapKeyRefNested(item);
   }

   public ConfigMapKeyRefNested editConfigMapKeyRef() {
      return this.withNewConfigMapKeyRefLike((ConfigMapKeySelector)Optional.ofNullable(this.buildConfigMapKeyRef()).orElse((Object)null));
   }

   public ConfigMapKeyRefNested editOrNewConfigMapKeyRef() {
      return this.withNewConfigMapKeyRefLike((ConfigMapKeySelector)Optional.ofNullable(this.buildConfigMapKeyRef()).orElse((new ConfigMapKeySelectorBuilder()).build()));
   }

   public ConfigMapKeyRefNested editOrNewConfigMapKeyRefLike(ConfigMapKeySelector item) {
      return this.withNewConfigMapKeyRefLike((ConfigMapKeySelector)Optional.ofNullable(this.buildConfigMapKeyRef()).orElse(item));
   }

   public ObjectFieldSelector buildFieldRef() {
      return this.fieldRef != null ? this.fieldRef.build() : null;
   }

   public EnvVarSourceFluent withFieldRef(ObjectFieldSelector fieldRef) {
      this._visitables.remove("fieldRef");
      if (fieldRef != null) {
         this.fieldRef = new ObjectFieldSelectorBuilder(fieldRef);
         this._visitables.get("fieldRef").add(this.fieldRef);
      } else {
         this.fieldRef = null;
         this._visitables.get("fieldRef").remove(this.fieldRef);
      }

      return this;
   }

   public boolean hasFieldRef() {
      return this.fieldRef != null;
   }

   public EnvVarSourceFluent withNewFieldRef(String apiVersion, String fieldPath) {
      return this.withFieldRef(new ObjectFieldSelector(apiVersion, fieldPath));
   }

   public FieldRefNested withNewFieldRef() {
      return new FieldRefNested((ObjectFieldSelector)null);
   }

   public FieldRefNested withNewFieldRefLike(ObjectFieldSelector item) {
      return new FieldRefNested(item);
   }

   public FieldRefNested editFieldRef() {
      return this.withNewFieldRefLike((ObjectFieldSelector)Optional.ofNullable(this.buildFieldRef()).orElse((Object)null));
   }

   public FieldRefNested editOrNewFieldRef() {
      return this.withNewFieldRefLike((ObjectFieldSelector)Optional.ofNullable(this.buildFieldRef()).orElse((new ObjectFieldSelectorBuilder()).build()));
   }

   public FieldRefNested editOrNewFieldRefLike(ObjectFieldSelector item) {
      return this.withNewFieldRefLike((ObjectFieldSelector)Optional.ofNullable(this.buildFieldRef()).orElse(item));
   }

   public ResourceFieldSelector buildResourceFieldRef() {
      return this.resourceFieldRef != null ? this.resourceFieldRef.build() : null;
   }

   public EnvVarSourceFluent withResourceFieldRef(ResourceFieldSelector resourceFieldRef) {
      this._visitables.remove("resourceFieldRef");
      if (resourceFieldRef != null) {
         this.resourceFieldRef = new ResourceFieldSelectorBuilder(resourceFieldRef);
         this._visitables.get("resourceFieldRef").add(this.resourceFieldRef);
      } else {
         this.resourceFieldRef = null;
         this._visitables.get("resourceFieldRef").remove(this.resourceFieldRef);
      }

      return this;
   }

   public boolean hasResourceFieldRef() {
      return this.resourceFieldRef != null;
   }

   public ResourceFieldRefNested withNewResourceFieldRef() {
      return new ResourceFieldRefNested((ResourceFieldSelector)null);
   }

   public ResourceFieldRefNested withNewResourceFieldRefLike(ResourceFieldSelector item) {
      return new ResourceFieldRefNested(item);
   }

   public ResourceFieldRefNested editResourceFieldRef() {
      return this.withNewResourceFieldRefLike((ResourceFieldSelector)Optional.ofNullable(this.buildResourceFieldRef()).orElse((Object)null));
   }

   public ResourceFieldRefNested editOrNewResourceFieldRef() {
      return this.withNewResourceFieldRefLike((ResourceFieldSelector)Optional.ofNullable(this.buildResourceFieldRef()).orElse((new ResourceFieldSelectorBuilder()).build()));
   }

   public ResourceFieldRefNested editOrNewResourceFieldRefLike(ResourceFieldSelector item) {
      return this.withNewResourceFieldRefLike((ResourceFieldSelector)Optional.ofNullable(this.buildResourceFieldRef()).orElse(item));
   }

   public SecretKeySelector buildSecretKeyRef() {
      return this.secretKeyRef != null ? this.secretKeyRef.build() : null;
   }

   public EnvVarSourceFluent withSecretKeyRef(SecretKeySelector secretKeyRef) {
      this._visitables.remove("secretKeyRef");
      if (secretKeyRef != null) {
         this.secretKeyRef = new SecretKeySelectorBuilder(secretKeyRef);
         this._visitables.get("secretKeyRef").add(this.secretKeyRef);
      } else {
         this.secretKeyRef = null;
         this._visitables.get("secretKeyRef").remove(this.secretKeyRef);
      }

      return this;
   }

   public boolean hasSecretKeyRef() {
      return this.secretKeyRef != null;
   }

   public EnvVarSourceFluent withNewSecretKeyRef(String key, String name, Boolean optional) {
      return this.withSecretKeyRef(new SecretKeySelector(key, name, optional));
   }

   public SecretKeyRefNested withNewSecretKeyRef() {
      return new SecretKeyRefNested((SecretKeySelector)null);
   }

   public SecretKeyRefNested withNewSecretKeyRefLike(SecretKeySelector item) {
      return new SecretKeyRefNested(item);
   }

   public SecretKeyRefNested editSecretKeyRef() {
      return this.withNewSecretKeyRefLike((SecretKeySelector)Optional.ofNullable(this.buildSecretKeyRef()).orElse((Object)null));
   }

   public SecretKeyRefNested editOrNewSecretKeyRef() {
      return this.withNewSecretKeyRefLike((SecretKeySelector)Optional.ofNullable(this.buildSecretKeyRef()).orElse((new SecretKeySelectorBuilder()).build()));
   }

   public SecretKeyRefNested editOrNewSecretKeyRefLike(SecretKeySelector item) {
      return this.withNewSecretKeyRefLike((SecretKeySelector)Optional.ofNullable(this.buildSecretKeyRef()).orElse(item));
   }

   public EnvVarSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public EnvVarSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public EnvVarSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public EnvVarSourceFluent removeFromAdditionalProperties(Map map) {
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

   public EnvVarSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            EnvVarSourceFluent that = (EnvVarSourceFluent)o;
            if (!Objects.equals(this.configMapKeyRef, that.configMapKeyRef)) {
               return false;
            } else if (!Objects.equals(this.fieldRef, that.fieldRef)) {
               return false;
            } else if (!Objects.equals(this.resourceFieldRef, that.resourceFieldRef)) {
               return false;
            } else if (!Objects.equals(this.secretKeyRef, that.secretKeyRef)) {
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
      return Objects.hash(new Object[]{this.configMapKeyRef, this.fieldRef, this.resourceFieldRef, this.secretKeyRef, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.configMapKeyRef != null) {
         sb.append("configMapKeyRef:");
         sb.append(this.configMapKeyRef + ",");
      }

      if (this.fieldRef != null) {
         sb.append("fieldRef:");
         sb.append(this.fieldRef + ",");
      }

      if (this.resourceFieldRef != null) {
         sb.append("resourceFieldRef:");
         sb.append(this.resourceFieldRef + ",");
      }

      if (this.secretKeyRef != null) {
         sb.append("secretKeyRef:");
         sb.append(this.secretKeyRef + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ConfigMapKeyRefNested extends ConfigMapKeySelectorFluent implements Nested {
      ConfigMapKeySelectorBuilder builder;

      ConfigMapKeyRefNested(ConfigMapKeySelector item) {
         this.builder = new ConfigMapKeySelectorBuilder(this, item);
      }

      public Object and() {
         return EnvVarSourceFluent.this.withConfigMapKeyRef(this.builder.build());
      }

      public Object endConfigMapKeyRef() {
         return this.and();
      }
   }

   public class FieldRefNested extends ObjectFieldSelectorFluent implements Nested {
      ObjectFieldSelectorBuilder builder;

      FieldRefNested(ObjectFieldSelector item) {
         this.builder = new ObjectFieldSelectorBuilder(this, item);
      }

      public Object and() {
         return EnvVarSourceFluent.this.withFieldRef(this.builder.build());
      }

      public Object endFieldRef() {
         return this.and();
      }
   }

   public class ResourceFieldRefNested extends ResourceFieldSelectorFluent implements Nested {
      ResourceFieldSelectorBuilder builder;

      ResourceFieldRefNested(ResourceFieldSelector item) {
         this.builder = new ResourceFieldSelectorBuilder(this, item);
      }

      public Object and() {
         return EnvVarSourceFluent.this.withResourceFieldRef(this.builder.build());
      }

      public Object endResourceFieldRef() {
         return this.and();
      }
   }

   public class SecretKeyRefNested extends SecretKeySelectorFluent implements Nested {
      SecretKeySelectorBuilder builder;

      SecretKeyRefNested(SecretKeySelector item) {
         this.builder = new SecretKeySelectorBuilder(this, item);
      }

      public Object and() {
         return EnvVarSourceFluent.this.withSecretKeyRef(this.builder.build());
      }

      public Object endSecretKeyRef() {
         return this.and();
      }
   }
}
