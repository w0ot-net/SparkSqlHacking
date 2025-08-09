package org.sparkproject.dmg.pmml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.sparkproject.jpmml.model.ArrayUtil;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

public class ComplexArray extends Array {
   public ComplexArray() {
   }

   @ValueConstructor
   public ComplexArray(@Property("type") Array.Type type, @Property("value") Collection value) {
      super(type, requireComplexValue(value));
   }

   public ComplexArray setN(@Property("n") Integer n) {
      return (ComplexArray)super.setN(n);
   }

   public ComplexArray setType(@Property("type") Array.Type type) {
      return (ComplexArray)super.setType(type);
   }

   public Collection getValue() {
      return (Collection)super.getValue();
   }

   public ComplexArray setValue(List values) {
      return (ComplexArray)super.setValue(new ListValue(this, values));
   }

   public ComplexArray setValue(Set values) {
      return (ComplexArray)super.setValue(new SetValue(this, values));
   }

   public ComplexArray setValue(@Property("value") Object value) {
      return (ComplexArray)super.setValue(requireComplexValue(value));
   }

   public static Collection requireComplexValue(Object value) {
      if (value == null) {
         return null;
      } else if (value instanceof Collection && value instanceof ComplexValue) {
         return (Collection)value;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public static class ListValue extends ArrayList implements ComplexValue {
      private Array array = null;

      private ListValue() {
      }

      public ListValue(ComplexArray complexArray, Collection values) {
         super(values);
         this.setArray(complexArray);
      }

      public Object toSimpleValue() {
         Array array = this.getArray();
         return ArrayUtil.format(array.getType(), this);
      }

      public Array getArray() {
         return this.array;
      }

      private void setArray(Array array) {
         this.array = array;
      }
   }

   public static class SetValue extends LinkedHashSet implements ComplexValue {
      private Array array = null;

      private SetValue() {
      }

      public SetValue(ComplexArray complexArray, Collection values) {
         super(values);
         this.setArray(complexArray);
      }

      public Object toSimpleValue() {
         Array array = this.getArray();
         return ArrayUtil.format(array.getType(), this);
      }

      public Array getArray() {
         return this.array;
      }

      private void setArray(Array array) {
         this.array = array;
      }
   }
}
