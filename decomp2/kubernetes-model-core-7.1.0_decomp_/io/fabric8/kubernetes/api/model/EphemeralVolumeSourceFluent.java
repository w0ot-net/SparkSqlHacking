package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class EphemeralVolumeSourceFluent extends BaseFluent {
   private PersistentVolumeClaimTemplateBuilder volumeClaimTemplate;
   private Map additionalProperties;

   public EphemeralVolumeSourceFluent() {
   }

   public EphemeralVolumeSourceFluent(EphemeralVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(EphemeralVolumeSource instance) {
      instance = instance != null ? instance : new EphemeralVolumeSource();
      if (instance != null) {
         this.withVolumeClaimTemplate(instance.getVolumeClaimTemplate());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public PersistentVolumeClaimTemplate buildVolumeClaimTemplate() {
      return this.volumeClaimTemplate != null ? this.volumeClaimTemplate.build() : null;
   }

   public EphemeralVolumeSourceFluent withVolumeClaimTemplate(PersistentVolumeClaimTemplate volumeClaimTemplate) {
      this._visitables.remove("volumeClaimTemplate");
      if (volumeClaimTemplate != null) {
         this.volumeClaimTemplate = new PersistentVolumeClaimTemplateBuilder(volumeClaimTemplate);
         this._visitables.get("volumeClaimTemplate").add(this.volumeClaimTemplate);
      } else {
         this.volumeClaimTemplate = null;
         this._visitables.get("volumeClaimTemplate").remove(this.volumeClaimTemplate);
      }

      return this;
   }

   public boolean hasVolumeClaimTemplate() {
      return this.volumeClaimTemplate != null;
   }

   public VolumeClaimTemplateNested withNewVolumeClaimTemplate() {
      return new VolumeClaimTemplateNested((PersistentVolumeClaimTemplate)null);
   }

   public VolumeClaimTemplateNested withNewVolumeClaimTemplateLike(PersistentVolumeClaimTemplate item) {
      return new VolumeClaimTemplateNested(item);
   }

   public VolumeClaimTemplateNested editVolumeClaimTemplate() {
      return this.withNewVolumeClaimTemplateLike((PersistentVolumeClaimTemplate)Optional.ofNullable(this.buildVolumeClaimTemplate()).orElse((Object)null));
   }

   public VolumeClaimTemplateNested editOrNewVolumeClaimTemplate() {
      return this.withNewVolumeClaimTemplateLike((PersistentVolumeClaimTemplate)Optional.ofNullable(this.buildVolumeClaimTemplate()).orElse((new PersistentVolumeClaimTemplateBuilder()).build()));
   }

   public VolumeClaimTemplateNested editOrNewVolumeClaimTemplateLike(PersistentVolumeClaimTemplate item) {
      return this.withNewVolumeClaimTemplateLike((PersistentVolumeClaimTemplate)Optional.ofNullable(this.buildVolumeClaimTemplate()).orElse(item));
   }

   public EphemeralVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public EphemeralVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public EphemeralVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public EphemeralVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public EphemeralVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            EphemeralVolumeSourceFluent that = (EphemeralVolumeSourceFluent)o;
            if (!Objects.equals(this.volumeClaimTemplate, that.volumeClaimTemplate)) {
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
      return Objects.hash(new Object[]{this.volumeClaimTemplate, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.volumeClaimTemplate != null) {
         sb.append("volumeClaimTemplate:");
         sb.append(this.volumeClaimTemplate + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class VolumeClaimTemplateNested extends PersistentVolumeClaimTemplateFluent implements Nested {
      PersistentVolumeClaimTemplateBuilder builder;

      VolumeClaimTemplateNested(PersistentVolumeClaimTemplate item) {
         this.builder = new PersistentVolumeClaimTemplateBuilder(this, item);
      }

      public Object and() {
         return EphemeralVolumeSourceFluent.this.withVolumeClaimTemplate(this.builder.build());
      }

      public Object endVolumeClaimTemplate() {
         return this.and();
      }
   }
}
