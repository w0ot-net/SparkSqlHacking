package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class NamedContextFluent extends BaseFluent {
   private ContextBuilder context;
   private String name;
   private Map additionalProperties;

   public NamedContextFluent() {
   }

   public NamedContextFluent(NamedContext instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NamedContext instance) {
      instance = instance != null ? instance : new NamedContext();
      if (instance != null) {
         this.withContext(instance.getContext());
         this.withName(instance.getName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Context buildContext() {
      return this.context != null ? this.context.build() : null;
   }

   public NamedContextFluent withContext(Context context) {
      this._visitables.remove("context");
      if (context != null) {
         this.context = new ContextBuilder(context);
         this._visitables.get("context").add(this.context);
      } else {
         this.context = null;
         this._visitables.get("context").remove(this.context);
      }

      return this;
   }

   public boolean hasContext() {
      return this.context != null;
   }

   public ContextNested withNewContext() {
      return new ContextNested((Context)null);
   }

   public ContextNested withNewContextLike(Context item) {
      return new ContextNested(item);
   }

   public ContextNested editContext() {
      return this.withNewContextLike((Context)Optional.ofNullable(this.buildContext()).orElse((Object)null));
   }

   public ContextNested editOrNewContext() {
      return this.withNewContextLike((Context)Optional.ofNullable(this.buildContext()).orElse((new ContextBuilder()).build()));
   }

   public ContextNested editOrNewContextLike(Context item) {
      return this.withNewContextLike((Context)Optional.ofNullable(this.buildContext()).orElse(item));
   }

   public String getName() {
      return this.name;
   }

   public NamedContextFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public NamedContextFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NamedContextFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NamedContextFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NamedContextFluent removeFromAdditionalProperties(Map map) {
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

   public NamedContextFluent withAdditionalProperties(Map additionalProperties) {
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
            NamedContextFluent that = (NamedContextFluent)o;
            if (!Objects.equals(this.context, that.context)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
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
      return Objects.hash(new Object[]{this.context, this.name, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.context != null) {
         sb.append("context:");
         sb.append(this.context + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ContextNested extends ContextFluent implements Nested {
      ContextBuilder builder;

      ContextNested(Context item) {
         this.builder = new ContextBuilder(this, item);
      }

      public Object and() {
         return NamedContextFluent.this.withContext(this.builder.build());
      }

      public Object endContext() {
         return this.and();
      }
   }
}
