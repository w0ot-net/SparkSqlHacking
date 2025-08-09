package io.fabric8.kubernetes.api.model.clusterapi.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.ObjectReference;
import io.fabric8.kubernetes.api.model.ObjectReferenceBuilder;
import io.fabric8.kubernetes.api.model.ObjectReferenceFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class BootstrapFluent extends BaseFluent {
   private ObjectReferenceBuilder configRef;
   private String dataSecretName;
   private Map additionalProperties;

   public BootstrapFluent() {
   }

   public BootstrapFluent(Bootstrap instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Bootstrap instance) {
      instance = instance != null ? instance : new Bootstrap();
      if (instance != null) {
         this.withConfigRef(instance.getConfigRef());
         this.withDataSecretName(instance.getDataSecretName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ObjectReference buildConfigRef() {
      return this.configRef != null ? this.configRef.build() : null;
   }

   public BootstrapFluent withConfigRef(ObjectReference configRef) {
      this._visitables.remove("configRef");
      if (configRef != null) {
         this.configRef = new ObjectReferenceBuilder(configRef);
         this._visitables.get("configRef").add(this.configRef);
      } else {
         this.configRef = null;
         this._visitables.get("configRef").remove(this.configRef);
      }

      return this;
   }

   public boolean hasConfigRef() {
      return this.configRef != null;
   }

   public ConfigRefNested withNewConfigRef() {
      return new ConfigRefNested((ObjectReference)null);
   }

   public ConfigRefNested withNewConfigRefLike(ObjectReference item) {
      return new ConfigRefNested(item);
   }

   public ConfigRefNested editConfigRef() {
      return this.withNewConfigRefLike((ObjectReference)Optional.ofNullable(this.buildConfigRef()).orElse((Object)null));
   }

   public ConfigRefNested editOrNewConfigRef() {
      return this.withNewConfigRefLike((ObjectReference)Optional.ofNullable(this.buildConfigRef()).orElse((new ObjectReferenceBuilder()).build()));
   }

   public ConfigRefNested editOrNewConfigRefLike(ObjectReference item) {
      return this.withNewConfigRefLike((ObjectReference)Optional.ofNullable(this.buildConfigRef()).orElse(item));
   }

   public String getDataSecretName() {
      return this.dataSecretName;
   }

   public BootstrapFluent withDataSecretName(String dataSecretName) {
      this.dataSecretName = dataSecretName;
      return this;
   }

   public boolean hasDataSecretName() {
      return this.dataSecretName != null;
   }

   public BootstrapFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public BootstrapFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public BootstrapFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public BootstrapFluent removeFromAdditionalProperties(Map map) {
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

   public BootstrapFluent withAdditionalProperties(Map additionalProperties) {
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
            BootstrapFluent that = (BootstrapFluent)o;
            if (!Objects.equals(this.configRef, that.configRef)) {
               return false;
            } else if (!Objects.equals(this.dataSecretName, that.dataSecretName)) {
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
      return Objects.hash(new Object[]{this.configRef, this.dataSecretName, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.configRef != null) {
         sb.append("configRef:");
         sb.append(this.configRef + ",");
      }

      if (this.dataSecretName != null) {
         sb.append("dataSecretName:");
         sb.append(this.dataSecretName + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ConfigRefNested extends ObjectReferenceFluent implements Nested {
      ObjectReferenceBuilder builder;

      ConfigRefNested(ObjectReference item) {
         this.builder = new ObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return BootstrapFluent.this.withConfigRef(this.builder.build());
      }

      public Object endConfigRef() {
         return this.and();
      }
   }
}
