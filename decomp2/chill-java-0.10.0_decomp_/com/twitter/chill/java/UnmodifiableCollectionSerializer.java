package com.twitter.chill.java;

import com.twitter.chill.IKryoRegistrar;
import com.twitter.chill.SingleRegistrar;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;

public class UnmodifiableCollectionSerializer extends UnmodifiableJavaCollectionSerializer {
   public static IKryoRegistrar registrar() {
      return new SingleRegistrar(Collections.unmodifiableCollection(Collections.EMPTY_SET).getClass(), new UnmodifiableCollectionSerializer());
   }

   protected Field getInnerField() throws Exception {
      return Class.forName("java.util.Collections$UnmodifiableCollection").getDeclaredField("c");
   }

   protected Collection newInstance(Collection var1) {
      return Collections.unmodifiableCollection(var1);
   }
}
