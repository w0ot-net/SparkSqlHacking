package org.apache.thrift.partial;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Collection;

public final class Validate {
   private Validate() {
   }

   public static void checkNotNull(Object obj, String argName) {
      checkArgument(obj != null, "'%s' must not be null.", argName);
   }

   public static void checkPositiveInteger(long value, String argName) {
      checkArgument(value > 0L, "'%s' must be a positive integer.", argName);
   }

   public static void checkNotNegative(long value, String argName) {
      checkArgument(value >= 0L, "'%s' must not be negative.", argName);
   }

   public static void checkRequired(boolean isPresent, String argName) {
      checkArgument(isPresent, "'%s' is required.", argName);
   }

   public static void checkValid(boolean isValid, String argName) {
      checkArgument(isValid, "'%s' is invalid.", argName);
   }

   public static void checkValid(boolean isValid, String argName, String validValues) {
      checkArgument(isValid, "'%s' is invalid. Valid values are: %s.", argName, validValues);
   }

   public static void checkNotNullAndNotEmpty(String arg, String argName) {
      checkNotNull(arg, argName);
      checkArgument(arg.length() > 0, "'%s' must not be empty.", argName);
   }

   public static void checkNotNullAndNotEmpty(Object[] array, String argName) {
      checkNotNull(array, argName);
      checkNotEmpty(array.length, argName);
   }

   public static void checkNotNullAndNotEmpty(byte[] array, String argName) {
      checkNotNull(array, argName);
      checkNotEmpty(array.length, argName);
   }

   public static void checkNotNullAndNotEmpty(short[] array, String argName) {
      checkNotNull(array, argName);
      checkNotEmpty(array.length, argName);
   }

   public static void checkNotNullAndNotEmpty(int[] array, String argName) {
      checkNotNull(array, argName);
      checkNotEmpty(array.length, argName);
   }

   public static void checkNotNullAndNotEmpty(long[] array, String argName) {
      checkNotNull(array, argName);
      checkNotEmpty(array.length, argName);
   }

   public static void checkNotNullAndNotEmpty(Iterable iter, String argName) {
      checkNotNull(iter, argName);
      int minNumElements = iter.iterator().hasNext() ? 1 : 0;
      checkNotEmpty(minNumElements, argName);
   }

   public static void checkNotNullAndNumberOfElements(Collection collection, int numElements, String argName) {
      checkNotNull(collection, argName);
      checkArgument(collection.size() == numElements, "Number of elements in '%s' must be exactly %s, %s given.", argName, numElements, collection.size());
   }

   public static void checkValuesEqual(long value1, String value1Name, long value2, String value2Name) {
      checkArgument(value1 == value2, "'%s' (%s) must equal '%s' (%s).", value1Name, value1, value2Name, value2);
   }

   public static void checkIntegerMultiple(long value1, String value1Name, long value2, String value2Name) {
      checkArgument(value1 % value2 == 0L, "'%s' (%s) must be an integer multiple of '%s' (%s).", value1Name, value1, value2Name, value2);
   }

   public static void checkGreater(long value1, String value1Name, long value2, String value2Name) {
      checkArgument(value1 > value2, "'%s' (%s) must be greater than '%s' (%s).", value1Name, value1, value2Name, value2);
   }

   public static void checkGreaterOrEqual(long value1, String value1Name, long value2, String value2Name) {
      checkArgument(value1 >= value2, "'%s' (%s) must be greater than or equal to '%s' (%s).", value1Name, value1, value2Name, value2);
   }

   public static void checkLessOrEqual(long value1, String value1Name, long value2, String value2Name) {
      checkArgument(value1 <= value2, "'%s' (%s) must be less than or equal to '%s' (%s).", value1Name, value1, value2Name, value2);
   }

   public static void checkWithinRange(long value, String valueName, long minValueInclusive, long maxValueInclusive) {
      checkArgument(value >= minValueInclusive && value <= maxValueInclusive, "'%s' (%s) must be within the range [%s, %s].", valueName, value, minValueInclusive, maxValueInclusive);
   }

   public static void checkWithinRange(double value, String valueName, double minValueInclusive, double maxValueInclusive) {
      checkArgument(value >= minValueInclusive && value <= maxValueInclusive, "'%s' (%s) must be within the range [%s, %s].", valueName, value, minValueInclusive, maxValueInclusive);
   }

   public static void checkPathExists(Path path, String argName) {
      checkNotNull(path, argName);
      checkArgument(Files.exists(path, new LinkOption[0]), "Path %s (%s) does not exist.", argName, path);
   }

   public static void checkPathExistsAsDir(Path path, String argName) {
      checkPathExists(path, argName);
      checkArgument(Files.isDirectory(path, new LinkOption[0]), "Path %s (%s) must point to a directory.", argName, path);
   }

   public static void checkPathExistsAsFile(Path path, String argName) {
      checkPathExists(path, argName);
      checkArgument(Files.isRegularFile(path, new LinkOption[0]), "Path %s (%s) must point to a file.", argName, path);
   }

   public static void checkArgument(boolean expression, String format, Object... args) {
      org.apache.commons.lang3.Validate.isTrue(expression, format, args);
   }

   public static void checkState(boolean expression, String format, Object... args) {
      org.apache.commons.lang3.Validate.validState(expression, format, args);
   }

   private static void checkNotEmpty(int arraySize, String argName) {
      checkArgument(arraySize > 0, "'%s' must have at least one element.", argName);
   }
}
