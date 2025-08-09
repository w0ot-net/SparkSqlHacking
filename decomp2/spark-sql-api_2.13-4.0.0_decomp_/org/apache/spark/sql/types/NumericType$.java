package org.apache.spark.sql.types;

public final class NumericType$ extends AbstractDataType {
   public static final NumericType$ MODULE$ = new NumericType$();

   public DataType defaultConcreteType() {
      return DoubleType$.MODULE$;
   }

   public String simpleString() {
      return "numeric";
   }

   public boolean acceptsType(final DataType other) {
      return other instanceof NumericType;
   }

   private NumericType$() {
   }
}
