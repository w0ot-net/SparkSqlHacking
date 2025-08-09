package org.apache.commons.lang3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumUtils {
   private static final String CANNOT_STORE_S_S_VALUES_IN_S_BITS = "Cannot store %s %s values in %s bits";
   private static final String ENUM_CLASS_MUST_BE_DEFINED = "EnumClass must be defined.";
   private static final String NULL_ELEMENTS_NOT_PERMITTED = "null elements not permitted";
   private static final String S_DOES_NOT_SEEM_TO_BE_AN_ENUM_TYPE = "%s does not seem to be an Enum type";

   private static Class asEnum(Class enumClass) {
      Objects.requireNonNull(enumClass, "EnumClass must be defined.");
      Validate.isTrue(enumClass.isEnum(), "%s does not seem to be an Enum type", enumClass);
      return enumClass;
   }

   private static Class checkBitVectorable(Class enumClass) {
      E[] constants = (E[])((Enum[])asEnum(enumClass).getEnumConstants());
      Validate.isTrue(constants.length <= 64, "Cannot store %s %s values in %s bits", constants.length, enumClass.getSimpleName(), 64);
      return enumClass;
   }

   @SafeVarargs
   public static long generateBitVector(Class enumClass, Enum... values) {
      Validate.noNullElements((Object[])values);
      return generateBitVector(enumClass, (Iterable)Arrays.asList(values));
   }

   public static long generateBitVector(Class enumClass, Iterable values) {
      checkBitVectorable(enumClass);
      Objects.requireNonNull(values, "values");
      long total = 0L;

      for(Enum constant : values) {
         Objects.requireNonNull(constant, "null elements not permitted");
         total |= 1L << constant.ordinal();
      }

      return total;
   }

   @SafeVarargs
   public static long[] generateBitVectors(Class enumClass, Enum... values) {
      asEnum(enumClass);
      Validate.noNullElements((Object[])values);
      EnumSet<E> condensed = EnumSet.noneOf(enumClass);
      Collections.addAll(condensed, values);
      long[] result = new long[(((Enum[])enumClass.getEnumConstants()).length - 1) / 64 + 1];

      for(Enum value : condensed) {
         int var10001 = value.ordinal() / 64;
         result[var10001] |= 1L << value.ordinal() % 64;
      }

      ArrayUtils.reverse(result);
      return result;
   }

   public static long[] generateBitVectors(Class enumClass, Iterable values) {
      asEnum(enumClass);
      Objects.requireNonNull(values, "values");
      EnumSet<E> condensed = EnumSet.noneOf(enumClass);
      values.forEach((constant) -> condensed.add((Enum)Objects.requireNonNull(constant, "null elements not permitted")));
      long[] result = new long[(((Enum[])enumClass.getEnumConstants()).length - 1) / 64 + 1];

      for(Enum value : condensed) {
         int var10001 = value.ordinal() / 64;
         result[var10001] |= 1L << value.ordinal() % 64;
      }

      ArrayUtils.reverse(result);
      return result;
   }

   public static Enum getEnum(Class enumClass, String enumName) {
      return getEnum(enumClass, enumName, (Enum)null);
   }

   public static Enum getEnum(Class enumClass, String enumName, Enum defaultEnum) {
      if (enumName == null) {
         return defaultEnum;
      } else {
         try {
            return Enum.valueOf(enumClass, enumName);
         } catch (IllegalArgumentException var4) {
            return defaultEnum;
         }
      }
   }

   public static Enum getEnumIgnoreCase(Class enumClass, String enumName) {
      return getEnumIgnoreCase(enumClass, enumName, (Enum)null);
   }

   public static Enum getEnumIgnoreCase(Class enumClass, String enumName, Enum defaultEnum) {
      return getFirstEnumIgnoreCase(enumClass, enumName, Enum::name, defaultEnum);
   }

   public static List getEnumList(Class enumClass) {
      return new ArrayList(Arrays.asList((Enum[])enumClass.getEnumConstants()));
   }

   public static Map getEnumMap(Class enumClass) {
      return getEnumMap(enumClass, Enum::name);
   }

   public static Map getEnumMap(Class enumClass, Function keyFunction) {
      Stream var10000 = Stream.of((Enum[])enumClass.getEnumConstants());
      Objects.requireNonNull(keyFunction);
      return (Map)var10000.collect(Collectors.toMap(keyFunction::apply, Function.identity()));
   }

   public static Enum getEnumSystemProperty(Class enumClass, String propName, Enum defaultEnum) {
      return enumClass != null && propName != null ? getEnum(enumClass, System.getProperty(propName), defaultEnum) : defaultEnum;
   }

   public static Enum getFirstEnumIgnoreCase(Class enumClass, String enumName, Function stringFunction, Enum defaultEnum) {
      return enumName != null && enumClass.isEnum() ? (Enum)Stream.of((Enum[])enumClass.getEnumConstants()).filter((e) -> enumName.equalsIgnoreCase((String)stringFunction.apply(e))).findFirst().orElse(defaultEnum) : defaultEnum;
   }

   public static boolean isValidEnum(Class enumClass, String enumName) {
      return getEnum(enumClass, enumName) != null;
   }

   public static boolean isValidEnumIgnoreCase(Class enumClass, String enumName) {
      return getEnumIgnoreCase(enumClass, enumName) != null;
   }

   public static EnumSet processBitVector(Class enumClass, long value) {
      checkBitVectorable(enumClass).getEnumConstants();
      return processBitVectors(enumClass, value);
   }

   public static EnumSet processBitVectors(Class enumClass, long... values) {
      EnumSet<E> results = EnumSet.noneOf(asEnum(enumClass));
      long[] lvalues = ArrayUtils.clone((long[])Objects.requireNonNull(values, "values"));
      ArrayUtils.reverse(lvalues);

      for(Enum constant : (Enum[])enumClass.getEnumConstants()) {
         int block = constant.ordinal() / 64;
         if (block < lvalues.length && (lvalues[block] & 1L << constant.ordinal() % 64) != 0L) {
            results.add(constant);
         }
      }

      return results;
   }
}
