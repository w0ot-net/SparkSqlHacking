package org.apache.parquet.column;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.parquet.hadoop.metadata.ColumnPath;

abstract class ColumnProperty {
   public static Builder builder() {
      return new Builder();
   }

   public static Builder builder(ColumnProperty toCopy) {
      Builder<T> builder = new Builder();
      builder.withDefaultValue(((DefaultColumnProperty)toCopy).defaultValue);
      if (toCopy instanceof MultipleColumnProperty) {
         builder.values.putAll(((MultipleColumnProperty)toCopy).values);
      }

      return builder;
   }

   public abstract Object getDefaultValue();

   public abstract Object getValue(ColumnPath var1);

   public Object getValue(String columnPath) {
      return this.getValue(ColumnPath.fromDotString(columnPath));
   }

   public Object getValue(ColumnDescriptor columnDescriptor) {
      return this.getValue(ColumnPath.get(columnDescriptor.getPath()));
   }

   private static class DefaultColumnProperty extends ColumnProperty {
      private final Object defaultValue;

      private DefaultColumnProperty(Object defaultValue) {
         this.defaultValue = defaultValue;
      }

      public Object getDefaultValue() {
         return this.defaultValue;
      }

      public Object getValue(ColumnPath columnPath) {
         return this.getDefaultValue();
      }

      public String toString() {
         return Objects.toString(this.getDefaultValue());
      }
   }

   private static class MultipleColumnProperty extends DefaultColumnProperty {
      private final Map values;

      private MultipleColumnProperty(Object defaultValue, Map values) {
         super(defaultValue, null);

         assert !values.isEmpty();

         this.values = new HashMap(values);
      }

      public Object getValue(ColumnPath columnPath) {
         T value = (T)this.values.get(columnPath);
         return value != null ? value : this.getDefaultValue();
      }

      public String toString() {
         return Objects.toString(this.getDefaultValue()) + ' ' + this.values.toString();
      }
   }

   static class Builder {
      private Object defaultValue;
      private final Map values;

      private Builder() {
         this.values = new HashMap();
      }

      public Builder withDefaultValue(Object defaultValue) {
         this.defaultValue = defaultValue;
         return this;
      }

      public Builder withValue(ColumnPath columnPath, Object value) {
         this.values.put(columnPath, value);
         return this;
      }

      public Builder withValue(String columnPath, Object value) {
         return this.withValue(ColumnPath.fromDotString(columnPath), value);
      }

      public Builder withValue(ColumnDescriptor columnDescriptor, Object value) {
         return this.withValue(ColumnPath.get(columnDescriptor.getPath()), value);
      }

      public ColumnProperty build() {
         return (ColumnProperty)(this.values.isEmpty() ? new DefaultColumnProperty(this.defaultValue) : new MultipleColumnProperty(this.defaultValue, this.values));
      }
   }
}
