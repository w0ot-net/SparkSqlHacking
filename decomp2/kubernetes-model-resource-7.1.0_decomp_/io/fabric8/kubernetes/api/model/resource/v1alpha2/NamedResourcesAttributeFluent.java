package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.Quantity;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class NamedResourcesAttributeFluent extends BaseFluent {
   private Boolean bool;
   private Long _int;
   private NamedResourcesIntSliceBuilder intSlice;
   private String name;
   private Quantity quantity;
   private String string;
   private NamedResourcesStringSliceBuilder stringSlice;
   private String version;
   private Map additionalProperties;

   public NamedResourcesAttributeFluent() {
   }

   public NamedResourcesAttributeFluent(NamedResourcesAttribute instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NamedResourcesAttribute instance) {
      instance = instance != null ? instance : new NamedResourcesAttribute();
      if (instance != null) {
         this.withBool(instance.getBool());
         this.withInt(instance.getInt());
         this.withIntSlice(instance.getIntSlice());
         this.withName(instance.getName());
         this.withQuantity(instance.getQuantity());
         this.withString(instance.getString());
         this.withStringSlice(instance.getStringSlice());
         this.withVersion(instance.getVersion());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Boolean getBool() {
      return this.bool;
   }

   public NamedResourcesAttributeFluent withBool(Boolean bool) {
      this.bool = bool;
      return this;
   }

   public boolean hasBool() {
      return this.bool != null;
   }

   public Long getInt() {
      return this._int;
   }

   public NamedResourcesAttributeFluent withInt(Long _int) {
      this._int = _int;
      return this;
   }

   public boolean hasInt() {
      return this._int != null;
   }

   public NamedResourcesIntSlice buildIntSlice() {
      return this.intSlice != null ? this.intSlice.build() : null;
   }

   public NamedResourcesAttributeFluent withIntSlice(NamedResourcesIntSlice intSlice) {
      this._visitables.remove("intSlice");
      if (intSlice != null) {
         this.intSlice = new NamedResourcesIntSliceBuilder(intSlice);
         this._visitables.get("intSlice").add(this.intSlice);
      } else {
         this.intSlice = null;
         this._visitables.get("intSlice").remove(this.intSlice);
      }

      return this;
   }

   public boolean hasIntSlice() {
      return this.intSlice != null;
   }

   public IntSliceNested withNewIntSlice() {
      return new IntSliceNested((NamedResourcesIntSlice)null);
   }

   public IntSliceNested withNewIntSliceLike(NamedResourcesIntSlice item) {
      return new IntSliceNested(item);
   }

   public IntSliceNested editIntSlice() {
      return this.withNewIntSliceLike((NamedResourcesIntSlice)Optional.ofNullable(this.buildIntSlice()).orElse((Object)null));
   }

   public IntSliceNested editOrNewIntSlice() {
      return this.withNewIntSliceLike((NamedResourcesIntSlice)Optional.ofNullable(this.buildIntSlice()).orElse((new NamedResourcesIntSliceBuilder()).build()));
   }

   public IntSliceNested editOrNewIntSliceLike(NamedResourcesIntSlice item) {
      return this.withNewIntSliceLike((NamedResourcesIntSlice)Optional.ofNullable(this.buildIntSlice()).orElse(item));
   }

   public String getName() {
      return this.name;
   }

   public NamedResourcesAttributeFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public Quantity getQuantity() {
      return this.quantity;
   }

   public NamedResourcesAttributeFluent withQuantity(Quantity quantity) {
      this.quantity = quantity;
      return this;
   }

   public boolean hasQuantity() {
      return this.quantity != null;
   }

   public NamedResourcesAttributeFluent withNewQuantity(String amount, String format) {
      return this.withQuantity(new Quantity(amount, format));
   }

   public NamedResourcesAttributeFluent withNewQuantity(String amount) {
      return this.withQuantity(new Quantity(amount));
   }

   public String getString() {
      return this.string;
   }

   public NamedResourcesAttributeFluent withString(String string) {
      this.string = string;
      return this;
   }

   public boolean hasString() {
      return this.string != null;
   }

   public NamedResourcesStringSlice buildStringSlice() {
      return this.stringSlice != null ? this.stringSlice.build() : null;
   }

   public NamedResourcesAttributeFluent withStringSlice(NamedResourcesStringSlice stringSlice) {
      this._visitables.remove("stringSlice");
      if (stringSlice != null) {
         this.stringSlice = new NamedResourcesStringSliceBuilder(stringSlice);
         this._visitables.get("stringSlice").add(this.stringSlice);
      } else {
         this.stringSlice = null;
         this._visitables.get("stringSlice").remove(this.stringSlice);
      }

      return this;
   }

   public boolean hasStringSlice() {
      return this.stringSlice != null;
   }

   public StringSliceNested withNewStringSlice() {
      return new StringSliceNested((NamedResourcesStringSlice)null);
   }

   public StringSliceNested withNewStringSliceLike(NamedResourcesStringSlice item) {
      return new StringSliceNested(item);
   }

   public StringSliceNested editStringSlice() {
      return this.withNewStringSliceLike((NamedResourcesStringSlice)Optional.ofNullable(this.buildStringSlice()).orElse((Object)null));
   }

   public StringSliceNested editOrNewStringSlice() {
      return this.withNewStringSliceLike((NamedResourcesStringSlice)Optional.ofNullable(this.buildStringSlice()).orElse((new NamedResourcesStringSliceBuilder()).build()));
   }

   public StringSliceNested editOrNewStringSliceLike(NamedResourcesStringSlice item) {
      return this.withNewStringSliceLike((NamedResourcesStringSlice)Optional.ofNullable(this.buildStringSlice()).orElse(item));
   }

   public String getVersion() {
      return this.version;
   }

   public NamedResourcesAttributeFluent withVersion(String version) {
      this.version = version;
      return this;
   }

   public boolean hasVersion() {
      return this.version != null;
   }

   public NamedResourcesAttributeFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NamedResourcesAttributeFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NamedResourcesAttributeFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NamedResourcesAttributeFluent removeFromAdditionalProperties(Map map) {
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

   public NamedResourcesAttributeFluent withAdditionalProperties(Map additionalProperties) {
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
            NamedResourcesAttributeFluent that = (NamedResourcesAttributeFluent)o;
            if (!Objects.equals(this.bool, that.bool)) {
               return false;
            } else if (!Objects.equals(this._int, that._int)) {
               return false;
            } else if (!Objects.equals(this.intSlice, that.intSlice)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.quantity, that.quantity)) {
               return false;
            } else if (!Objects.equals(this.string, that.string)) {
               return false;
            } else if (!Objects.equals(this.stringSlice, that.stringSlice)) {
               return false;
            } else if (!Objects.equals(this.version, that.version)) {
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
      return Objects.hash(new Object[]{this.bool, this._int, this.intSlice, this.name, this.quantity, this.string, this.stringSlice, this.version, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.bool != null) {
         sb.append("bool:");
         sb.append(this.bool + ",");
      }

      if (this._int != null) {
         sb.append("_int:");
         sb.append(this._int + ",");
      }

      if (this.intSlice != null) {
         sb.append("intSlice:");
         sb.append(this.intSlice + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.quantity != null) {
         sb.append("quantity:");
         sb.append(this.quantity + ",");
      }

      if (this.string != null) {
         sb.append("string:");
         sb.append(this.string + ",");
      }

      if (this.stringSlice != null) {
         sb.append("stringSlice:");
         sb.append(this.stringSlice + ",");
      }

      if (this.version != null) {
         sb.append("version:");
         sb.append(this.version + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public NamedResourcesAttributeFluent withBool() {
      return this.withBool(true);
   }

   public class IntSliceNested extends NamedResourcesIntSliceFluent implements Nested {
      NamedResourcesIntSliceBuilder builder;

      IntSliceNested(NamedResourcesIntSlice item) {
         this.builder = new NamedResourcesIntSliceBuilder(this, item);
      }

      public Object and() {
         return NamedResourcesAttributeFluent.this.withIntSlice(this.builder.build());
      }

      public Object endIntSlice() {
         return this.and();
      }
   }

   public class StringSliceNested extends NamedResourcesStringSliceFluent implements Nested {
      NamedResourcesStringSliceBuilder builder;

      StringSliceNested(NamedResourcesStringSlice item) {
         this.builder = new NamedResourcesStringSliceBuilder(this, item);
      }

      public Object and() {
         return NamedResourcesAttributeFluent.this.withStringSlice(this.builder.build());
      }

      public Object endStringSlice() {
         return this.and();
      }
   }
}
