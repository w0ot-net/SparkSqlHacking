package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ReplicationControllerSpecFluent extends BaseFluent {
   private Integer minReadySeconds;
   private Integer replicas;
   private Map selector;
   private PodTemplateSpecBuilder template;
   private Map additionalProperties;

   public ReplicationControllerSpecFluent() {
   }

   public ReplicationControllerSpecFluent(ReplicationControllerSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ReplicationControllerSpec instance) {
      instance = instance != null ? instance : new ReplicationControllerSpec();
      if (instance != null) {
         this.withMinReadySeconds(instance.getMinReadySeconds());
         this.withReplicas(instance.getReplicas());
         this.withSelector(instance.getSelector());
         this.withTemplate(instance.getTemplate());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getMinReadySeconds() {
      return this.minReadySeconds;
   }

   public ReplicationControllerSpecFluent withMinReadySeconds(Integer minReadySeconds) {
      this.minReadySeconds = minReadySeconds;
      return this;
   }

   public boolean hasMinReadySeconds() {
      return this.minReadySeconds != null;
   }

   public Integer getReplicas() {
      return this.replicas;
   }

   public ReplicationControllerSpecFluent withReplicas(Integer replicas) {
      this.replicas = replicas;
      return this;
   }

   public boolean hasReplicas() {
      return this.replicas != null;
   }

   public ReplicationControllerSpecFluent addToSelector(String key, String value) {
      if (this.selector == null && key != null && value != null) {
         this.selector = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.selector.put(key, value);
      }

      return this;
   }

   public ReplicationControllerSpecFluent addToSelector(Map map) {
      if (this.selector == null && map != null) {
         this.selector = new LinkedHashMap();
      }

      if (map != null) {
         this.selector.putAll(map);
      }

      return this;
   }

   public ReplicationControllerSpecFluent removeFromSelector(String key) {
      if (this.selector == null) {
         return this;
      } else {
         if (key != null && this.selector != null) {
            this.selector.remove(key);
         }

         return this;
      }
   }

   public ReplicationControllerSpecFluent removeFromSelector(Map map) {
      if (this.selector == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.selector != null) {
                  this.selector.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getSelector() {
      return this.selector;
   }

   public ReplicationControllerSpecFluent withSelector(Map selector) {
      if (selector == null) {
         this.selector = null;
      } else {
         this.selector = new LinkedHashMap(selector);
      }

      return this;
   }

   public boolean hasSelector() {
      return this.selector != null;
   }

   public PodTemplateSpec buildTemplate() {
      return this.template != null ? this.template.build() : null;
   }

   public ReplicationControllerSpecFluent withTemplate(PodTemplateSpec template) {
      this._visitables.remove("template");
      if (template != null) {
         this.template = new PodTemplateSpecBuilder(template);
         this._visitables.get("template").add(this.template);
      } else {
         this.template = null;
         this._visitables.get("template").remove(this.template);
      }

      return this;
   }

   public boolean hasTemplate() {
      return this.template != null;
   }

   public TemplateNested withNewTemplate() {
      return new TemplateNested((PodTemplateSpec)null);
   }

   public TemplateNested withNewTemplateLike(PodTemplateSpec item) {
      return new TemplateNested(item);
   }

   public TemplateNested editTemplate() {
      return this.withNewTemplateLike((PodTemplateSpec)Optional.ofNullable(this.buildTemplate()).orElse((Object)null));
   }

   public TemplateNested editOrNewTemplate() {
      return this.withNewTemplateLike((PodTemplateSpec)Optional.ofNullable(this.buildTemplate()).orElse((new PodTemplateSpecBuilder()).build()));
   }

   public TemplateNested editOrNewTemplateLike(PodTemplateSpec item) {
      return this.withNewTemplateLike((PodTemplateSpec)Optional.ofNullable(this.buildTemplate()).orElse(item));
   }

   public ReplicationControllerSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ReplicationControllerSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ReplicationControllerSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ReplicationControllerSpecFluent removeFromAdditionalProperties(Map map) {
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

   public ReplicationControllerSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            ReplicationControllerSpecFluent that = (ReplicationControllerSpecFluent)o;
            if (!Objects.equals(this.minReadySeconds, that.minReadySeconds)) {
               return false;
            } else if (!Objects.equals(this.replicas, that.replicas)) {
               return false;
            } else if (!Objects.equals(this.selector, that.selector)) {
               return false;
            } else if (!Objects.equals(this.template, that.template)) {
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
      return Objects.hash(new Object[]{this.minReadySeconds, this.replicas, this.selector, this.template, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.minReadySeconds != null) {
         sb.append("minReadySeconds:");
         sb.append(this.minReadySeconds + ",");
      }

      if (this.replicas != null) {
         sb.append("replicas:");
         sb.append(this.replicas + ",");
      }

      if (this.selector != null && !this.selector.isEmpty()) {
         sb.append("selector:");
         sb.append(this.selector + ",");
      }

      if (this.template != null) {
         sb.append("template:");
         sb.append(this.template + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class TemplateNested extends PodTemplateSpecFluent implements Nested {
      PodTemplateSpecBuilder builder;

      TemplateNested(PodTemplateSpec item) {
         this.builder = new PodTemplateSpecBuilder(this, item);
      }

      public Object and() {
         return ReplicationControllerSpecFluent.this.withTemplate(this.builder.build());
      }

      public Object endTemplate() {
         return this.and();
      }
   }
}
