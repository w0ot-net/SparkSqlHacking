package com.twitter.chill.java;

import com.twitter.chill.IKryoRegistrar;
import com.twitter.chill.SingleRegistrar;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

public class UnmodifiableSortedMapSerializer extends UnmodifiableJavaCollectionSerializer {
   public static IKryoRegistrar registrar() {
      return new SingleRegistrar(Collections.unmodifiableSortedMap(new TreeMap(Collections.EMPTY_MAP)).getClass(), new UnmodifiableSortedMapSerializer());
   }

   protected Field getInnerField() throws Exception {
      return Class.forName("java.util.Collections$UnmodifiableSortedMap").getDeclaredField("sm");
   }

   protected SortedMap newInstance(SortedMap var1) {
      return Collections.unmodifiableSortedMap(var1);
   }
}
