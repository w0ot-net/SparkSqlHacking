package org.apache.parquet.filter2.predicate;

import java.util.HashMap;
import java.util.Map;
import org.apache.parquet.Preconditions;

public class PrimitiveToBoxedClass {
   private static final Map primitiveToBoxed = new HashMap();

   public static Class get(Class c) {
      Preconditions.checkArgument(c.isPrimitive(), "Class %s is not primitive!", c);
      return (Class)primitiveToBoxed.get(c);
   }

   private PrimitiveToBoxedClass() {
   }

   static {
      primitiveToBoxed.put(Boolean.TYPE, Boolean.class);
      primitiveToBoxed.put(Byte.TYPE, Byte.class);
      primitiveToBoxed.put(Short.TYPE, Short.class);
      primitiveToBoxed.put(Character.TYPE, Character.class);
      primitiveToBoxed.put(Integer.TYPE, Integer.class);
      primitiveToBoxed.put(Long.TYPE, Long.class);
      primitiveToBoxed.put(Float.TYPE, Float.class);
      primitiveToBoxed.put(Double.TYPE, Double.class);
   }
}
