package org.apache.parquet.filter2.predicate;

import java.util.Set;
import org.apache.parquet.hadoop.metadata.ColumnPath;

public final class FilterApi {
   private FilterApi() {
   }

   public static Operators.IntColumn intColumn(String columnPath) {
      return new Operators.IntColumn(ColumnPath.fromDotString(columnPath));
   }

   public static Operators.LongColumn longColumn(String columnPath) {
      return new Operators.LongColumn(ColumnPath.fromDotString(columnPath));
   }

   public static Operators.FloatColumn floatColumn(String columnPath) {
      return new Operators.FloatColumn(ColumnPath.fromDotString(columnPath));
   }

   public static Operators.DoubleColumn doubleColumn(String columnPath) {
      return new Operators.DoubleColumn(ColumnPath.fromDotString(columnPath));
   }

   public static Operators.BooleanColumn booleanColumn(String columnPath) {
      return new Operators.BooleanColumn(ColumnPath.fromDotString(columnPath));
   }

   public static Operators.BinaryColumn binaryColumn(String columnPath) {
      return new Operators.BinaryColumn(ColumnPath.fromDotString(columnPath));
   }

   public static Operators.Eq eq(Operators.Column column, Comparable value) {
      return new Operators.Eq(column, value);
   }

   public static Operators.NotEq notEq(Operators.Column column, Comparable value) {
      return new Operators.NotEq(column, value);
   }

   public static Operators.Lt lt(Operators.Column column, Comparable value) {
      return new Operators.Lt(column, value);
   }

   public static Operators.LtEq ltEq(Operators.Column column, Comparable value) {
      return new Operators.LtEq(column, value);
   }

   public static Operators.Gt gt(Operators.Column column, Comparable value) {
      return new Operators.Gt(column, value);
   }

   public static Operators.GtEq gtEq(Operators.Column column, Comparable value) {
      return new Operators.GtEq(column, value);
   }

   public static Operators.In in(Operators.Column column, Set values) {
      return new Operators.In(column, values);
   }

   public static Operators.NotIn notIn(Operators.Column column, Set values) {
      return new Operators.NotIn(column, values);
   }

   public static Operators.Contains contains(Operators.SingleColumnFilterPredicate pred) {
      return Operators.Contains.of(pred);
   }

   public static Operators.UserDefined userDefined(Operators.Column column, Class clazz) {
      return new Operators.UserDefinedByClass(column, clazz);
   }

   public static Operators.UserDefined userDefined(Operators.Column column, UserDefinedPredicate udp) {
      return new Operators.UserDefinedByInstance(column, udp);
   }

   public static FilterPredicate and(FilterPredicate left, FilterPredicate right) {
      return new Operators.And(left, right);
   }

   public static FilterPredicate or(FilterPredicate left, FilterPredicate right) {
      return new Operators.Or(left, right);
   }

   public static FilterPredicate not(FilterPredicate predicate) {
      return new Operators.Not(predicate);
   }
}
