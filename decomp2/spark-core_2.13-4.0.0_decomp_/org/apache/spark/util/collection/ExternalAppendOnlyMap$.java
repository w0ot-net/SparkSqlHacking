package org.apache.spark.util.collection;

import java.io.Serializable;
import org.apache.spark.SparkEnv$;
import org.apache.spark.TaskContext;
import org.apache.spark.TaskContext$;
import org.apache.spark.serializer.Serializer;
import org.apache.spark.serializer.SerializerManager;
import org.apache.spark.storage.BlockManager;
import scala.runtime.ModuleSerializationProxy;

public final class ExternalAppendOnlyMap$ implements Serializable {
   public static final ExternalAppendOnlyMap$ MODULE$ = new ExternalAppendOnlyMap$();

   public Serializer $lessinit$greater$default$4() {
      return SparkEnv$.MODULE$.get().serializer();
   }

   public BlockManager $lessinit$greater$default$5() {
      return SparkEnv$.MODULE$.get().blockManager();
   }

   public TaskContext $lessinit$greater$default$6() {
      return TaskContext$.MODULE$.get();
   }

   public SerializerManager $lessinit$greater$default$7() {
      return SparkEnv$.MODULE$.get().serializerManager();
   }

   public int org$apache$spark$util$collection$ExternalAppendOnlyMap$$hash(final Object obj) {
      return obj == null ? 0 : obj.hashCode();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExternalAppendOnlyMap$.class);
   }

   private ExternalAppendOnlyMap$() {
   }
}
