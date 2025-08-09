package org.apache.spark.storage.memory;

import java.io.Serializable;
import org.apache.spark.memory.MemoryMode;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class DeserializedMemoryEntry$ implements Serializable {
   public static final DeserializedMemoryEntry$ MODULE$ = new DeserializedMemoryEntry$();

   public final String toString() {
      return "DeserializedMemoryEntry";
   }

   public DeserializedMemoryEntry apply(final Object value, final long size, final MemoryMode memoryMode, final ClassTag classTag) {
      return new DeserializedMemoryEntry(value, size, memoryMode, classTag);
   }

   public Option unapply(final DeserializedMemoryEntry x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(x$0.value(), BoxesRunTime.boxToLong(x$0.size()), x$0.memoryMode(), x$0.classTag())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DeserializedMemoryEntry$.class);
   }

   private DeserializedMemoryEntry$() {
   }
}
