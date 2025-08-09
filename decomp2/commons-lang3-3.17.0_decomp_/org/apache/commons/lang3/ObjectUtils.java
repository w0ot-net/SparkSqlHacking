package org.apache.commons.lang3;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.commons.lang3.exception.CloneFailedException;
import org.apache.commons.lang3.function.Suppliers;
import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.text.StrBuilder;
import org.apache.commons.lang3.time.DurationUtils;

public class ObjectUtils {
   private static final char AT_SIGN = '@';
   public static final Null NULL = new Null();

   public static boolean allNotNull(Object... values) {
      return values != null && Stream.of(values).noneMatch(Objects::isNull);
   }

   public static boolean allNull(Object... values) {
      return !anyNotNull(values);
   }

   public static boolean anyNotNull(Object... values) {
      return firstNonNull(values) != null;
   }

   public static boolean anyNull(Object... values) {
      return !allNotNull(values);
   }

   public static Object clone(Object obj) {
      if (!(obj instanceof Cloneable)) {
         return null;
      } else {
         Class<? extends Object> objClass = obj.getClass();
         Object result;
         if (isArray(obj)) {
            Class<?> componentType = objClass.getComponentType();
            if (componentType.isPrimitive()) {
               int length = Array.getLength(obj);
               result = Array.newInstance(componentType, length);

               while(length-- > 0) {
                  Array.set(result, length, Array.get(obj, length));
               }
            } else {
               result = ((Object[])obj).clone();
            }
         } else {
            try {
               result = objClass.getMethod("clone").invoke(obj);
            } catch (ReflectiveOperationException e) {
               throw new CloneFailedException("Exception cloning Cloneable type " + objClass.getName(), e);
            }
         }

         return result;
      }
   }

   public static Object cloneIfPossible(Object obj) {
      T clone = (T)clone(obj);
      return clone == null ? obj : clone;
   }

   public static int compare(Comparable c1, Comparable c2) {
      return compare(c1, c2, false);
   }

   public static int compare(Comparable c1, Comparable c2, boolean nullGreater) {
      if (c1 == c2) {
         return 0;
      } else if (c1 == null) {
         return nullGreater ? 1 : -1;
      } else if (c2 == null) {
         return nullGreater ? -1 : 1;
      } else {
         return c1.compareTo(c2);
      }
   }

   public static boolean CONST(boolean v) {
      return v;
   }

   public static byte CONST(byte v) {
      return v;
   }

   public static char CONST(char v) {
      return v;
   }

   public static double CONST(double v) {
      return v;
   }

   public static float CONST(float v) {
      return v;
   }

   public static int CONST(int v) {
      return v;
   }

   public static long CONST(long v) {
      return v;
   }

   public static short CONST(short v) {
      return v;
   }

   public static Object CONST(Object v) {
      return v;
   }

   public static byte CONST_BYTE(int v) {
      if (v >= -128 && v <= 127) {
         return (byte)v;
      } else {
         throw new IllegalArgumentException("Supplied value must be a valid byte literal between -128 and 127: [" + v + "]");
      }
   }

   public static short CONST_SHORT(int v) {
      if (v >= -32768 && v <= 32767) {
         return (short)v;
      } else {
         throw new IllegalArgumentException("Supplied value must be a valid byte literal between -32768 and 32767: [" + v + "]");
      }
   }

   public static Object defaultIfNull(Object object, Object defaultValue) {
      return object != null ? object : defaultValue;
   }

   /** @deprecated */
   @Deprecated
   public static boolean equals(Object object1, Object object2) {
      return Objects.equals(object1, object2);
   }

   @SafeVarargs
   public static Object firstNonNull(Object... values) {
      return org.apache.commons.lang3.stream.Streams.of(values).filter(Objects::nonNull).findFirst().orElse((Object)null);
   }

   public static Class getClass(Object object) {
      return object == null ? null : object.getClass();
   }

   @SafeVarargs
   public static Object getFirstNonNull(Supplier... suppliers) {
      return org.apache.commons.lang3.stream.Streams.of((Object[])suppliers).filter(Objects::nonNull).map(Supplier::get).filter(Objects::nonNull).findFirst().orElse((Object)null);
   }

   public static Object getIfNull(Object object, Supplier defaultSupplier) {
      return object != null ? object : Suppliers.get(defaultSupplier);
   }

   /** @deprecated */
   @Deprecated
   public static int hashCode(Object obj) {
      return Objects.hashCode(obj);
   }

   public static String hashCodeHex(Object object) {
      return Integer.toHexString(Objects.hashCode(object));
   }

   /** @deprecated */
   @Deprecated
   public static int hashCodeMulti(Object... objects) {
      int hash = 1;
      if (objects != null) {
         for(Object object : objects) {
            int tmpHash = Objects.hashCode(object);
            hash = hash * 31 + tmpHash;
         }
      }

      return hash;
   }

   public static String identityHashCodeHex(Object object) {
      return Integer.toHexString(System.identityHashCode(object));
   }

   public static void identityToString(Appendable appendable, Object object) throws IOException {
      Objects.requireNonNull(object, "object");
      appendable.append(object.getClass().getName()).append('@').append(identityHashCodeHex(object));
   }

   public static String identityToString(Object object) {
      if (object == null) {
         return null;
      } else {
         String name = object.getClass().getName();
         String hexString = identityHashCodeHex(object);
         StringBuilder builder = new StringBuilder(name.length() + 1 + hexString.length());
         builder.append(name).append('@').append(hexString);
         return builder.toString();
      }
   }

   /** @deprecated */
   @Deprecated
   public static void identityToString(StrBuilder builder, Object object) {
      Objects.requireNonNull(object, "object");
      String name = object.getClass().getName();
      String hexString = identityHashCodeHex(object);
      builder.ensureCapacity(builder.length() + name.length() + 1 + hexString.length());
      builder.append(name).append('@').append(hexString);
   }

   public static void identityToString(StringBuffer buffer, Object object) {
      Objects.requireNonNull(object, "object");
      String name = object.getClass().getName();
      String hexString = identityHashCodeHex(object);
      buffer.ensureCapacity(buffer.length() + name.length() + 1 + hexString.length());
      buffer.append(name).append('@').append(hexString);
   }

   public static void identityToString(StringBuilder builder, Object object) {
      Objects.requireNonNull(object, "object");
      String name = object.getClass().getName();
      String hexString = identityHashCodeHex(object);
      builder.ensureCapacity(builder.length() + name.length() + 1 + hexString.length());
      builder.append(name).append('@').append(hexString);
   }

   public static boolean isArray(Object object) {
      return object != null && object.getClass().isArray();
   }

   public static boolean isEmpty(Object object) {
      if (object == null) {
         return true;
      } else if (object instanceof CharSequence) {
         return ((CharSequence)object).length() == 0;
      } else if (isArray(object)) {
         return Array.getLength(object) == 0;
      } else if (object instanceof Collection) {
         return ((Collection)object).isEmpty();
      } else if (object instanceof Map) {
         return ((Map)object).isEmpty();
      } else if (object instanceof Optional) {
         return !((Optional)object).isPresent();
      } else {
         return false;
      }
   }

   public static boolean isNotEmpty(Object object) {
      return !isEmpty(object);
   }

   @SafeVarargs
   public static Comparable max(Comparable... values) {
      T result = (T)null;
      if (values != null) {
         for(Comparable value : values) {
            if (compare(value, result, false) > 0) {
               result = value;
            }
         }
      }

      return result;
   }

   @SafeVarargs
   public static Object median(Comparator comparator, Object... items) {
      Validate.notEmpty(items, "null/empty items");
      Validate.noNullElements(items);
      Objects.requireNonNull(comparator, "comparator");
      TreeSet<T> treeSet = new TreeSet(comparator);
      Collections.addAll(treeSet, items);
      return treeSet.toArray()[(treeSet.size() - 1) / 2];
   }

   @SafeVarargs
   public static Comparable median(Comparable... items) {
      Validate.notEmpty((Object[])items);
      Validate.noNullElements((Object[])items);
      TreeSet<T> sort = new TreeSet();
      Collections.addAll(sort, items);
      return (Comparable)sort.toArray()[(sort.size() - 1) / 2];
   }

   @SafeVarargs
   public static Comparable min(Comparable... values) {
      T result = (T)null;
      if (values != null) {
         for(Comparable value : values) {
            if (compare(value, result, true) < 0) {
               result = value;
            }
         }
      }

      return result;
   }

   @SafeVarargs
   public static Object mode(Object... items) {
      if (ArrayUtils.isNotEmpty(items)) {
         HashMap<T, MutableInt> occurrences = new HashMap(items.length);

         for(Object t : items) {
            MutableInt count = (MutableInt)occurrences.get(t);
            if (count == null) {
               occurrences.put(t, new MutableInt(1));
            } else {
               count.increment();
            }
         }

         T result = (T)null;
         int max = 0;

         for(Map.Entry e : occurrences.entrySet()) {
            int cmp = ((MutableInt)e.getValue()).intValue();
            if (cmp == max) {
               result = (T)null;
            } else if (cmp > max) {
               max = cmp;
               result = (T)e.getKey();
            }
         }

         return result;
      } else {
         return null;
      }
   }

   public static boolean notEqual(Object object1, Object object2) {
      return !Objects.equals(object1, object2);
   }

   public static Object requireNonEmpty(Object obj) {
      return requireNonEmpty(obj, "object");
   }

   public static Object requireNonEmpty(Object obj, String message) {
      Objects.requireNonNull(obj, message);
      if (isEmpty(obj)) {
         throw new IllegalArgumentException(message);
      } else {
         return obj;
      }
   }

   /** @deprecated */
   @Deprecated
   public static String toString(Object obj) {
      return Objects.toString(obj, "");
   }

   /** @deprecated */
   @Deprecated
   public static String toString(Object obj, String nullStr) {
      return Objects.toString(obj, nullStr);
   }

   public static String toString(Supplier obj, Supplier supplier) {
      return obj == null ? (String)Suppliers.get(supplier) : toString(obj.get(), supplier);
   }

   public static String toString(Object obj, Supplier supplier) {
      return obj == null ? (String)Suppliers.get(supplier) : obj.toString();
   }

   public static void wait(Object obj, Duration duration) throws InterruptedException {
      Objects.requireNonNull(obj);
      DurationUtils.accept(obj::wait, DurationUtils.zeroIfNull(duration));
   }

   public static class Null implements Serializable {
      private static final long serialVersionUID = 7092611880189329093L;

      Null() {
      }

      private Object readResolve() {
         return ObjectUtils.NULL;
      }
   }
}
