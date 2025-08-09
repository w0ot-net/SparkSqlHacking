package org.apache.parquet.filter;

import java.util.Objects;
import org.apache.parquet.column.ColumnReader;
import org.apache.parquet.io.api.Binary;

public class ColumnPredicates {
   public static Predicate equalTo(String target) {
      Objects.requireNonNull(target, "target cannot be null");
      return (input) -> target.equals(input.getBinary().toStringUsingUTF8());
   }

   public static Predicate applyFunctionToString(PredicateFunction fn) {
      return (input) -> fn.functionToApply(input.getBinary().toStringUsingUTF8());
   }

   public static Predicate equalTo(int target) {
      return (input) -> input.getInteger() == target;
   }

   public static Predicate applyFunctionToInteger(IntegerPredicateFunction fn) {
      return (input) -> fn.functionToApply(input.getInteger());
   }

   public static Predicate equalTo(long target) {
      return (input) -> input.getLong() == target;
   }

   public static Predicate applyFunctionToLong(LongPredicateFunction fn) {
      return (input) -> fn.functionToApply(input.getLong());
   }

   public static Predicate equalTo(float target) {
      return (input) -> input.getFloat() == target;
   }

   public static Predicate applyFunctionToFloat(FloatPredicateFunction fn) {
      return (input) -> fn.functionToApply(input.getFloat());
   }

   public static Predicate equalTo(double target) {
      return (input) -> input.getDouble() == target;
   }

   public static Predicate applyFunctionToDouble(DoublePredicateFunction fn) {
      return (input) -> fn.functionToApply(input.getDouble());
   }

   public static Predicate equalTo(boolean target) {
      return (input) -> input.getBoolean() == target;
   }

   public static Predicate applyFunctionToBoolean(BooleanPredicateFunction fn) {
      return (input) -> fn.functionToApply(input.getBoolean());
   }

   public static Predicate equalTo(Enum target) {
      Objects.requireNonNull(target, "target cannot be null");
      String targetAsString = target.name();
      return (input) -> targetAsString.equals(input.getBinary().toStringUsingUTF8());
   }

   public static Predicate applyFunctionToBinary(PredicateFunction fn) {
      return (input) -> fn.functionToApply(input.getBinary());
   }

   public interface BooleanPredicateFunction {
      boolean functionToApply(boolean var1);
   }

   public interface DoublePredicateFunction {
      boolean functionToApply(double var1);
   }

   public interface FloatPredicateFunction {
      boolean functionToApply(float var1);
   }

   public interface IntegerPredicateFunction {
      boolean functionToApply(int var1);
   }

   public interface LongPredicateFunction {
      boolean functionToApply(long var1);
   }

   public interface Predicate {
      boolean apply(ColumnReader var1);
   }

   public interface PredicateFunction {
      boolean functionToApply(Object var1);
   }
}
