package com.twitter.chill.java;

import com.twitter.chill.IKryoRegistrar;
import com.twitter.chill.SingleRegistrar;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public class UnmodifiableSortedSetSerializer extends UnmodifiableJavaCollectionSerializer {
   public static IKryoRegistrar registrar() {
      return new SingleRegistrar(Collections.unmodifiableSortedSet(new TreeSet(Collections.EMPTY_SET)).getClass(), new UnmodifiableSortedSetSerializer());
   }

   protected Field getInnerField() throws Exception {
      return Class.forName("java.util.Collections$UnmodifiableSortedSet").getDeclaredField("ss");
   }

   protected SortedSet newInstance(SortedSet var1) {
      return Collections.unmodifiableSortedSet(var1);
   }
}
