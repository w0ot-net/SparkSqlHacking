package com.twitter.chill.java;

import com.twitter.chill.IKryoRegistrar;
import com.twitter.chill.SingleRegistrar;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;

public class UnmodifiableMapSerializer extends UnmodifiableJavaCollectionSerializer {
   public static IKryoRegistrar registrar() {
      return new SingleRegistrar(Collections.unmodifiableMap(Collections.EMPTY_MAP).getClass(), new UnmodifiableMapSerializer());
   }

   protected Field getInnerField() throws Exception {
      return Class.forName("java.util.Collections$UnmodifiableMap").getDeclaredField("m");
   }

   protected Map newInstance(Map var1) {
      return Collections.unmodifiableMap(var1);
   }
}
