package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
final class Platform {
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

   static Map preservesInsertionOrderOnPutsMapWithExpectedSize(int expectedSize) {
      return Maps.newLinkedHashMapWithExpectedSize(expectedSize);
   }

   static Set preservesInsertionOrderOnAddsSet() {
      return CompactHashSet.create();
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

   private Platform() {
   }
}
