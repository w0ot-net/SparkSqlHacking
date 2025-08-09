package shaded.parquet.com.fasterxml.jackson.databind.deser.impl;

import java.io.IOException;
import shaded.parquet.com.fasterxml.jackson.databind.deser.SettableAnyProperty;
import shaded.parquet.com.fasterxml.jackson.databind.deser.SettableBeanProperty;

public abstract class PropertyValue {
   public final PropertyValue next;
   public final Object value;

   protected PropertyValue(PropertyValue next, Object value) {
      this.next = next;
      this.value = value;
   }

   public abstract void assign(Object var1) throws IOException;

   public void setValue(Object parameterObject) throws IOException {
      throw new UnsupportedOperationException("Should not be called by this type " + this.getClass().getName());
   }

   static final class Regular extends PropertyValue {
      final SettableBeanProperty _property;

      public Regular(PropertyValue next, Object value, SettableBeanProperty prop) {
         super(next, value);
         this._property = prop;
      }

      public void assign(Object bean) throws IOException {
         this._property.set(bean, this.value);
      }
   }

   static final class Any extends PropertyValue {
      final SettableAnyProperty _property;
      final String _propertyName;

      public Any(PropertyValue next, Object value, SettableAnyProperty prop, String propName) {
         super(next, value);
         this._property = prop;
         this._propertyName = propName;
      }

      public void assign(Object bean) throws IOException {
         this._property.set(bean, this._propertyName, this.value);
      }
   }

   static final class Map extends PropertyValue {
      final Object _key;

      public Map(PropertyValue next, Object value, Object key) {
         super(next, value);
         this._key = key;
      }

      public void assign(Object bean) throws IOException {
         ((java.util.Map)bean).put(this._key, this.value);
      }
   }

   static final class AnyParameter extends PropertyValue {
      final SettableAnyProperty _property;
      final String _propertyName;

      public AnyParameter(PropertyValue next, Object value, SettableAnyProperty prop, String propName) {
         super(next, value);
         this._property = prop;
         this._propertyName = propName;
      }

      public void assign(Object bean) throws IOException {
      }

      public void setValue(Object parameterObject) throws IOException {
         this._property.set(parameterObject, this._propertyName, this.value);
      }
   }
}
