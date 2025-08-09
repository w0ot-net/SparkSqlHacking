package org.apache.spark.sql.types;

public final class AnsiIntervalType$ extends AbstractDataType {
   public static final AnsiIntervalType$ MODULE$ = new AnsiIntervalType$();

   public String simpleString() {
      return "ANSI interval";
   }

   public boolean acceptsType(final DataType other) {
      return other instanceof AnsiIntervalType;
   }

   public DataType defaultConcreteType() {
      return DayTimeIntervalType$.MODULE$.apply();
   }

   private AnsiIntervalType$() {
   }
}
