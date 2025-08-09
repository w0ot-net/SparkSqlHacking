package org.apache.spark.util.collection;

import java.io.Serializable;
import scala.reflect.ClassTag;
import scala.runtime.ModuleSerializationProxy;

public final class CompactBuffer$ implements Serializable {
   public static final CompactBuffer$ MODULE$ = new CompactBuffer$();

   public CompactBuffer apply(final ClassTag evidence$2) {
      return new CompactBuffer(evidence$2);
   }

   public CompactBuffer apply(final Object value, final ClassTag evidence$3) {
      CompactBuffer buf = new CompactBuffer(evidence$3);
      return buf.$plus$eq(value);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CompactBuffer$.class);
   }

   private CompactBuffer$() {
   }
}
