package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class VolumeNodeAffinityFluent extends BaseFluent {
   private NodeSelectorBuilder required;
   private Map additionalProperties;

   public VolumeNodeAffinityFluent() {
   }

   public VolumeNodeAffinityFluent(VolumeNodeAffinity instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(VolumeNodeAffinity instance) {
      instance = instance != null ? instance : new VolumeNodeAffinity();
      if (instance != null) {
         this.withRequired(instance.getRequired());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public NodeSelector buildRequired() {
      return this.required != null ? this.required.build() : null;
   }

   public VolumeNodeAffinityFluent withRequired(NodeSelector required) {
      this._visitables.remove("required");
      if (required != null) {
         this.required = new NodeSelectorBuilder(required);
         this._visitables.get("required").add(this.required);
      } else {
         this.required = null;
         this._visitables.get("required").remove(this.required);
      }

      return this;
   }

   public boolean hasRequired() {
      return this.required != null;
   }

   public RequiredNested withNewRequired() {
      return new RequiredNested((NodeSelector)null);
   }

   public RequiredNested withNewRequiredLike(NodeSelector item) {
      return new RequiredNested(item);
   }

   public RequiredNested editRequired() {
      return this.withNewRequiredLike((NodeSelector)Optional.ofNullable(this.buildRequired()).orElse((Object)null));
   }

   public RequiredNested editOrNewRequired() {
      return this.withNewRequiredLike((NodeSelector)Optional.ofNullable(this.buildRequired()).orElse((new NodeSelectorBuilder()).build()));
   }

   public RequiredNested editOrNewRequiredLike(NodeSelector item) {
      return this.withNewRequiredLike((NodeSelector)Optional.ofNullable(this.buildRequired()).orElse(item));
   }

   public VolumeNodeAffinityFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public VolumeNodeAffinityFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public VolumeNodeAffinityFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public VolumeNodeAffinityFluent removeFromAdditionalProperties(Map map) {
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

   public VolumeNodeAffinityFluent withAdditionalProperties(Map additionalProperties) {
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
            VolumeNodeAffinityFluent that = (VolumeNodeAffinityFluent)o;
            if (!Objects.equals(this.required, that.required)) {
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
      return Objects.hash(new Object[]{this.required, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.required != null) {
         sb.append("required:");
         sb.append(this.required + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class RequiredNested extends NodeSelectorFluent implements Nested {
      NodeSelectorBuilder builder;

      RequiredNested(NodeSelector item) {
         this.builder = new NodeSelectorBuilder(this, item);
      }

      public Object and() {
         return VolumeNodeAffinityFluent.this.withRequired(this.builder.build());
      }

      public Object endRequired() {
         return this.and();
      }
   }
}
