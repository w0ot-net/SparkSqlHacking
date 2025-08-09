package org.apache.curator.shaded.com.google.common.collect;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.base.Strings;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
final class Platform {
   private static final Logger logger = Logger.getLogger(Platform.class.getName());

   static Map newHashMapWithExpectedSize(int expectedSize) {
      return Maps.newHashMapWithExpectedSize(expectedSize);
   }

   static Map newLinkedHashMapWithExpectedSize(int expectedSize) {
      return Maps.newLinkedHashMapWithExpectedSize(expectedSize);
   }

   static Set newHashSetWithExpectedSize(int expectedSize) {
      return Sets.newHashSetWithExpectedSize(expectedSize);
   }

   static Set newConcurrentHashSet() {
      return ConcurrentHashMap.newKeySet();
   }

   static Set newLinkedHashSetWithExpectedSize(int expectedSize) {
      return Sets.newLinkedHashSetWithExpectedSize(expectedSize);
   }

   static Map preservesInsertionOrderOnPutsMap() {
      return Maps.newLinkedHashMap();
   }

   static Set preservesInsertionOrderOnAddsSet() {
      return Sets.newLinkedHashSet();
   }

   static Object[] newArray(Object[] reference, int length) {
      T[] empty = (T[])(reference.length == 0 ? reference : Arrays.copyOf(reference, 0));
      return Arrays.copyOf(empty, length);
   }

   static Object[] copy(Object[] source, int from, int to, Object[] arrayOfType) {
      return Arrays.copyOfRange(source, from, to, arrayOfType.getClass());
   }

   @J2ktIncompatible
   static MapMaker tryWeakKeys(MapMaker mapMaker) {
      return mapMaker.weakKeys();
   }

   static Class getDeclaringClassOrObjectForJ2cl(Enum e) {
      return e.getDeclaringClass();
   }

   static int reduceIterationsIfGwt(int iterations) {
      return iterations;
   }

   static int reduceExponentIfGwt(int exponent) {
      return exponent;
   }

   static void checkGwtRpcEnabled() {
      String propertyName = "guava.gwt.emergency_reenable_rpc";
      if (!Boolean.parseBoolean(System.getProperty(propertyName, "false"))) {
         throw new UnsupportedOperationException(Strings.lenientFormat("We are removing GWT-RPC support for Guava types. You can temporarily reenable support by setting the system property %s to true. For more about system properties, see %s. For more about Guava's GWT-RPC support, see %s.", propertyName, "https://stackoverflow.com/q/5189914/28465", "https://groups.google.com/d/msg/guava-announce/zHZTFg7YF3o/rQNnwdHeEwAJ"));
      } else {
         logger.log(Level.WARNING, "Later in 2020, we will remove GWT-RPC support for Guava types. You are seeing this warning because you are sending a Guava type over GWT-RPC, which will break. You can identify which type by looking at the class name in the attached stack trace.", new Throwable());
      }
   }

   private Platform() {
   }
}
