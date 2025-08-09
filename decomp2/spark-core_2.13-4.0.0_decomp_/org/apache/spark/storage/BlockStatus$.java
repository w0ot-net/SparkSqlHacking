package org.apache.spark.storage;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.storage.StorageLevel.;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

@DeveloperApi
public final class BlockStatus$ implements Serializable {
   public static final BlockStatus$ MODULE$ = new BlockStatus$();

   public BlockStatus empty() {
      return new BlockStatus(.MODULE$.NONE(), 0L, 0L);
   }

   public BlockStatus apply(final StorageLevel storageLevel, final long memSize, final long diskSize) {
      return new BlockStatus(storageLevel, memSize, diskSize);
   }

   public Option unapply(final BlockStatus x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.storageLevel(), BoxesRunTime.boxToLong(x$0.memSize()), BoxesRunTime.boxToLong(x$0.diskSize()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BlockStatus$.class);
   }

   private BlockStatus$() {
   }
}
