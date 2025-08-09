package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class EnvVarFluent extends BaseFluent {
   private String name;
   private String value;
   private EnvVarSourceBuilder valueFrom;
   private Map additionalProperties;

   public EnvVarFluent() {
   }

   public EnvVarFluent(EnvVar instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(EnvVar instance) {
      instance = instance != null ? instance : new EnvVar();
      if (instance != null) {
         this.withName(instance.getName());
         this.withValue(instance.getValue());
         this.withValueFrom(instance.getValueFrom());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getName() {
      return this.name;
   }

   public EnvVarFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public String getValue() {
      return this.value;
   }

   public EnvVarFluent withValue(String value) {
      this.value = value;
      return this;
   }

   public boolean hasValue() {
      return this.value != null;
   }

   public EnvVarSource buildValueFrom() {
      return this.valueFrom != null ? this.valueFrom.build() : null;
   }

   public EnvVarFluent withValueFrom(EnvVarSource valueFrom) {
      this._visitables.remove("valueFrom");
      if (valueFrom != null) {
         this.valueFrom = new EnvVarSourceBuilder(valueFrom);
         this._visitables.get("valueFrom").add(this.valueFrom);
      } else {
         this.valueFrom = null;
         this._visitables.get("valueFrom").remove(this.valueFrom);
      }

      return this;
   }

   public boolean hasValueFrom() {
      return this.valueFrom != null;
   }

   public ValueFromNested withNewValueFrom() {
      return new ValueFromNested((EnvVarSource)null);
   }

   public ValueFromNested withNewValueFromLike(EnvVarSource item) {
      return new ValueFromNested(item);
   }

   public ValueFromNested editValueFrom() {
      return this.withNewValueFromLike((EnvVarSource)Optional.ofNullable(this.buildValueFrom()).orElse((Object)null));
   }

   public ValueFromNested editOrNewValueFrom() {
      return this.withNewValueFromLike((EnvVarSource)Optional.ofNullable(this.buildValueFrom()).orElse((new EnvVarSourceBuilder()).build()));
   }

   public ValueFromNested editOrNewValueFromLike(EnvVarSource item) {
      return this.withNewValueFromLike((EnvVarSource)Optional.ofNullable(this.buildValueFrom()).orElse(item));
   }

   public EnvVarFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public EnvVarFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public EnvVarFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public EnvVarFluent removeFromAdditionalProperties(Map map) {
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

   public EnvVarFluent withAdditionalProperties(Map additionalProperties) {
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
            EnvVarFluent that = (EnvVarFluent)o;
            if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.value, that.value)) {
               return false;
            } else if (!Objects.equals(this.valueFrom, that.valueFrom)) {
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
      return Objects.hash(new Object[]{this.name, this.value, this.valueFrom, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.value != null) {
         sb.append("value:");
         sb.append(this.value + ",");
      }

      if (this.valueFrom != null) {
         sb.append("valueFrom:");
         sb.append(this.valueFrom + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ValueFromNested extends EnvVarSourceFluent implements Nested {
      EnvVarSourceBuilder builder;

      ValueFromNested(EnvVarSource item) {
         this.builder = new EnvVarSourceBuilder(this, item);
      }

      public Object and() {
         return EnvVarFluent.this.withValueFrom(this.builder.build());
      }

      public Object endValueFrom() {
         return this.and();
      }
   }
}
