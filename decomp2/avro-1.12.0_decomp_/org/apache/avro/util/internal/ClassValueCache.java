package org.apache.avro.util.internal;

import java.util.function.Function;

public class ClassValueCache implements Function {
   private final Function ifAbsent;
   private final ClassValue cache = new ClassValue() {
      protected Object computeValue(Class c) {
         return ClassValueCache.this.ifAbsent.apply(c);
      }
   };

   public ClassValueCache(Function ifAbsent) {
      this.ifAbsent = ifAbsent;
   }

   public Object apply(Class c) {
      return this.cache.get(c);
   }
}
