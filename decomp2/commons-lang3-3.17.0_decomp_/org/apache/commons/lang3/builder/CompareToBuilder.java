package org.apache.commons.lang3.builder;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;

public class CompareToBuilder implements Builder {
   private int comparison = 0;

   private static void reflectionAppend(Object lhs, Object rhs, Class clazz, CompareToBuilder builder, boolean useTransients, String[] excludeFields) {
      Field[] fields = clazz.getDeclaredFields();
      AccessibleObject.setAccessible(fields, true);

      for(int i = 0; i < fields.length && builder.comparison == 0; ++i) {
         Field field = fields[i];
         if (!ArrayUtils.contains(excludeFields, field.getName()) && !field.getName().contains("$") && (useTransients || !Modifier.isTransient(field.getModifiers())) && !Modifier.isStatic(field.getModifiers())) {
            builder.append(Reflection.getUnchecked(field, lhs), Reflection.getUnchecked(field, rhs));
         }
      }

   }

   public static int reflectionCompare(Object lhs, Object rhs) {
      return reflectionCompare(lhs, rhs, false, (Class)null);
   }

   public static int reflectionCompare(Object lhs, Object rhs, boolean compareTransients) {
      return reflectionCompare(lhs, rhs, compareTransients, (Class)null);
   }

   public static int reflectionCompare(Object lhs, Object rhs, boolean compareTransients, Class reflectUpToClass, String... excludeFields) {
      if (lhs == rhs) {
         return 0;
      } else {
         Objects.requireNonNull(lhs, "lhs");
         Objects.requireNonNull(rhs, "rhs");
         Class<?> lhsClazz = lhs.getClass();
         if (!lhsClazz.isInstance(rhs)) {
            throw new ClassCastException();
         } else {
            CompareToBuilder compareToBuilder = new CompareToBuilder();
            reflectionAppend(lhs, rhs, lhsClazz, compareToBuilder, compareTransients, excludeFields);

            while(lhsClazz.getSuperclass() != null && lhsClazz != reflectUpToClass) {
               lhsClazz = lhsClazz.getSuperclass();
               reflectionAppend(lhs, rhs, lhsClazz, compareToBuilder, compareTransients, excludeFields);
            }

            return compareToBuilder.toComparison();
         }
      }
   }

   public static int reflectionCompare(Object lhs, Object rhs, Collection excludeFields) {
      return reflectionCompare(lhs, rhs, ReflectionToStringBuilder.toNoNullStringArray(excludeFields));
   }

   public static int reflectionCompare(Object lhs, Object rhs, String... excludeFields) {
      return reflectionCompare(lhs, rhs, false, (Class)null, excludeFields);
   }

   public CompareToBuilder append(boolean lhs, boolean rhs) {
      if (this.comparison != 0) {
         return this;
      } else if (lhs == rhs) {
         return this;
      } else {
         if (lhs) {
            this.comparison = 1;
         } else {
            this.comparison = -1;
         }

         return this;
      }
   }

   public CompareToBuilder append(boolean[] lhs, boolean[] rhs) {
      if (this.comparison != 0) {
         return this;
      } else if (lhs == rhs) {
         return this;
      } else if (lhs == null) {
         this.comparison = -1;
         return this;
      } else if (rhs == null) {
         this.comparison = 1;
         return this;
      } else if (lhs.length != rhs.length) {
         this.comparison = lhs.length < rhs.length ? -1 : 1;
         return this;
      } else {
         for(int i = 0; i < lhs.length && this.comparison == 0; ++i) {
            this.append(lhs[i], rhs[i]);
         }

         return this;
      }
   }

   public CompareToBuilder append(byte lhs, byte rhs) {
      if (this.comparison != 0) {
         return this;
      } else {
         this.comparison = Byte.compare(lhs, rhs);
         return this;
      }
   }

   public CompareToBuilder append(byte[] lhs, byte[] rhs) {
      if (this.comparison != 0) {
         return this;
      } else if (lhs == rhs) {
         return this;
      } else if (lhs == null) {
         this.comparison = -1;
         return this;
      } else if (rhs == null) {
         this.comparison = 1;
         return this;
      } else if (lhs.length != rhs.length) {
         this.comparison = lhs.length < rhs.length ? -1 : 1;
         return this;
      } else {
         for(int i = 0; i < lhs.length && this.comparison == 0; ++i) {
            this.append(lhs[i], rhs[i]);
         }

         return this;
      }
   }

   public CompareToBuilder append(char lhs, char rhs) {
      if (this.comparison != 0) {
         return this;
      } else {
         this.comparison = Character.compare(lhs, rhs);
         return this;
      }
   }

   public CompareToBuilder append(char[] lhs, char[] rhs) {
      if (this.comparison != 0) {
         return this;
      } else if (lhs == rhs) {
         return this;
      } else if (lhs == null) {
         this.comparison = -1;
         return this;
      } else if (rhs == null) {
         this.comparison = 1;
         return this;
      } else if (lhs.length != rhs.length) {
         this.comparison = lhs.length < rhs.length ? -1 : 1;
         return this;
      } else {
         for(int i = 0; i < lhs.length && this.comparison == 0; ++i) {
            this.append(lhs[i], rhs[i]);
         }

         return this;
      }
   }

   public CompareToBuilder append(double lhs, double rhs) {
      if (this.comparison != 0) {
         return this;
      } else {
         this.comparison = Double.compare(lhs, rhs);
         return this;
      }
   }

   public CompareToBuilder append(double[] lhs, double[] rhs) {
      if (this.comparison != 0) {
         return this;
      } else if (lhs == rhs) {
         return this;
      } else if (lhs == null) {
         this.comparison = -1;
         return this;
      } else if (rhs == null) {
         this.comparison = 1;
         return this;
      } else if (lhs.length != rhs.length) {
         this.comparison = lhs.length < rhs.length ? -1 : 1;
         return this;
      } else {
         for(int i = 0; i < lhs.length && this.comparison == 0; ++i) {
            this.append(lhs[i], rhs[i]);
         }

         return this;
      }
   }

   public CompareToBuilder append(float lhs, float rhs) {
      if (this.comparison != 0) {
         return this;
      } else {
         this.comparison = Float.compare(lhs, rhs);
         return this;
      }
   }

   public CompareToBuilder append(float[] lhs, float[] rhs) {
      if (this.comparison != 0) {
         return this;
      } else if (lhs == rhs) {
         return this;
      } else if (lhs == null) {
         this.comparison = -1;
         return this;
      } else if (rhs == null) {
         this.comparison = 1;
         return this;
      } else if (lhs.length != rhs.length) {
         this.comparison = lhs.length < rhs.length ? -1 : 1;
         return this;
      } else {
         for(int i = 0; i < lhs.length && this.comparison == 0; ++i) {
            this.append(lhs[i], rhs[i]);
         }

         return this;
      }
   }

   public CompareToBuilder append(int lhs, int rhs) {
      if (this.comparison != 0) {
         return this;
      } else {
         this.comparison = Integer.compare(lhs, rhs);
         return this;
      }
   }

   public CompareToBuilder append(int[] lhs, int[] rhs) {
      if (this.comparison != 0) {
         return this;
      } else if (lhs == rhs) {
         return this;
      } else if (lhs == null) {
         this.comparison = -1;
         return this;
      } else if (rhs == null) {
         this.comparison = 1;
         return this;
      } else if (lhs.length != rhs.length) {
         this.comparison = lhs.length < rhs.length ? -1 : 1;
         return this;
      } else {
         for(int i = 0; i < lhs.length && this.comparison == 0; ++i) {
            this.append(lhs[i], rhs[i]);
         }

         return this;
      }
   }

   public CompareToBuilder append(long lhs, long rhs) {
      if (this.comparison != 0) {
         return this;
      } else {
         this.comparison = Long.compare(lhs, rhs);
         return this;
      }
   }

   public CompareToBuilder append(long[] lhs, long[] rhs) {
      if (this.comparison != 0) {
         return this;
      } else if (lhs == rhs) {
         return this;
      } else if (lhs == null) {
         this.comparison = -1;
         return this;
      } else if (rhs == null) {
         this.comparison = 1;
         return this;
      } else if (lhs.length != rhs.length) {
         this.comparison = lhs.length < rhs.length ? -1 : 1;
         return this;
      } else {
         for(int i = 0; i < lhs.length && this.comparison == 0; ++i) {
            this.append(lhs[i], rhs[i]);
         }

         return this;
      }
   }

   public CompareToBuilder append(Object lhs, Object rhs) {
      return this.append((Object)lhs, (Object)rhs, (Comparator)null);
   }

   public CompareToBuilder append(Object lhs, Object rhs, Comparator comparator) {
      if (this.comparison != 0) {
         return this;
      } else if (lhs == rhs) {
         return this;
      } else if (lhs == null) {
         this.comparison = -1;
         return this;
      } else if (rhs == null) {
         this.comparison = 1;
         return this;
      } else {
         if (ObjectUtils.isArray(lhs)) {
            this.appendArray(lhs, rhs, comparator);
         } else if (comparator == null) {
            Comparable<Object> comparable = (Comparable)lhs;
            this.comparison = comparable.compareTo(rhs);
         } else {
            this.comparison = comparator.compare(lhs, rhs);
         }

         return this;
      }
   }

   public CompareToBuilder append(Object[] lhs, Object[] rhs) {
      return this.append((Object[])lhs, (Object[])rhs, (Comparator)null);
   }

   public CompareToBuilder append(Object[] lhs, Object[] rhs, Comparator comparator) {
      if (this.comparison != 0) {
         return this;
      } else if (lhs == rhs) {
         return this;
      } else if (lhs == null) {
         this.comparison = -1;
         return this;
      } else if (rhs == null) {
         this.comparison = 1;
         return this;
      } else if (lhs.length != rhs.length) {
         this.comparison = lhs.length < rhs.length ? -1 : 1;
         return this;
      } else {
         for(int i = 0; i < lhs.length && this.comparison == 0; ++i) {
            this.append(lhs[i], rhs[i], comparator);
         }

         return this;
      }
   }

   public CompareToBuilder append(short lhs, short rhs) {
      if (this.comparison != 0) {
         return this;
      } else {
         this.comparison = Short.compare(lhs, rhs);
         return this;
      }
   }

   public CompareToBuilder append(short[] lhs, short[] rhs) {
      if (this.comparison != 0) {
         return this;
      } else if (lhs == rhs) {
         return this;
      } else if (lhs == null) {
         this.comparison = -1;
         return this;
      } else if (rhs == null) {
         this.comparison = 1;
         return this;
      } else if (lhs.length != rhs.length) {
         this.comparison = lhs.length < rhs.length ? -1 : 1;
         return this;
      } else {
         for(int i = 0; i < lhs.length && this.comparison == 0; ++i) {
            this.append(lhs[i], rhs[i]);
         }

         return this;
      }
   }

   private void appendArray(Object lhs, Object rhs, Comparator comparator) {
      if (lhs instanceof long[]) {
         this.append((long[])lhs, (long[])rhs);
      } else if (lhs instanceof int[]) {
         this.append((int[])lhs, (int[])rhs);
      } else if (lhs instanceof short[]) {
         this.append((short[])lhs, (short[])rhs);
      } else if (lhs instanceof char[]) {
         this.append((char[])lhs, (char[])rhs);
      } else if (lhs instanceof byte[]) {
         this.append((byte[])lhs, (byte[])rhs);
      } else if (lhs instanceof double[]) {
         this.append((double[])lhs, (double[])rhs);
      } else if (lhs instanceof float[]) {
         this.append((float[])lhs, (float[])rhs);
      } else if (lhs instanceof boolean[]) {
         this.append((boolean[])lhs, (boolean[])rhs);
      } else {
         this.append(lhs, rhs, comparator);
      }

   }

   public CompareToBuilder appendSuper(int superCompareTo) {
      if (this.comparison != 0) {
         return this;
      } else {
         this.comparison = superCompareTo;
         return this;
      }
   }

   public Integer build() {
      return this.toComparison();
   }

   public int toComparison() {
      return this.comparison;
   }
}
