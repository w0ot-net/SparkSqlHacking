package com.twitter.chill.java;

import com.twitter.chill.IKryoRegistrar;
import com.twitter.chill.SingleRegistrar;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Set;

public class UnmodifiableSetSerializer extends UnmodifiableJavaCollectionSerializer {
   public static IKryoRegistrar registrar() {
      return new SingleRegistrar(Collections.unmodifiableSet(Collections.EMPTY_SET).getClass(), new UnmodifiableSetSerializer());
   }

   protected Field getInnerField() throws Exception {
      return Class.forName("java.util.Collections$UnmodifiableSet").getSuperclass().getDeclaredField("c");
   }

   protected Set newInstance(Set var1) {
      return Collections.unmodifiableSet(var1);
   }
}
