package org.apache.spark.sql.types;

public final class IntegralType$ extends AbstractDataType {
   public static final IntegralType$ MODULE$ = new IntegralType$();

   public DataType defaultConcreteType() {
      return IntegerType$.MODULE$;
   }

   public String simpleString() {
      return "integral";
   }

   public boolean acceptsType(final DataType other) {
      return other instanceof IntegralType;
   }

   private IntegralType$() {
   }
}
