package org.apache.spark.storage.memory;

import java.io.Serializable;
import org.apache.spark.memory.MemoryMode;
import org.apache.spark.util.io.ChunkedByteBuffer;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.reflect.ClassTag;
import scala.runtime.ModuleSerializationProxy;

public final class SerializedMemoryEntry$ implements Serializable {
   public static final SerializedMemoryEntry$ MODULE$ = new SerializedMemoryEntry$();

   public final String toString() {
      return "SerializedMemoryEntry";
   }

   public SerializedMemoryEntry apply(final ChunkedByteBuffer buffer, final MemoryMode memoryMode, final ClassTag classTag) {
      return new SerializedMemoryEntry(buffer, memoryMode, classTag);
   }

   public Option unapply(final SerializedMemoryEntry x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.buffer(), x$0.memoryMode(), x$0.classTag())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SerializedMemoryEntry$.class);
   }

   private SerializedMemoryEntry$() {
   }
}
