package com.twitter.chill.java;

import com.twitter.chill.IKryoRegistrar;
import com.twitter.chill.SingleRegistrar;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class UnmodifiableListSerializer extends UnmodifiableJavaCollectionSerializer {
   public static IKryoRegistrar registrar() {
      return new IterableRegistrar(new IKryoRegistrar[]{new SingleRegistrar(Collections.unmodifiableList(Collections.EMPTY_LIST).getClass(), new UnmodifiableListSerializer()), new SingleRegistrar(Collections.unmodifiableList(new LinkedList(Collections.EMPTY_LIST)).getClass(), new UnmodifiableListSerializer())});
   }

   protected Field getInnerField() throws Exception {
      return Class.forName("java.util.Collections$UnmodifiableList").getDeclaredField("list");
   }

   protected List newInstance(List var1) {
      return Collections.unmodifiableList(var1);
   }
}
