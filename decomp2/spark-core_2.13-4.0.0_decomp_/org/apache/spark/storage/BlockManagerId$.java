package org.apache.spark.storage;

import java.io.ObjectInput;
import java.io.Serializable;
import org.sparkproject.guava.cache.CacheBuilder;
import org.sparkproject.guava.cache.CacheLoader;
import org.sparkproject.guava.cache.LoadingCache;
import scala.Option;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class BlockManagerId$ implements Serializable {
   public static final BlockManagerId$ MODULE$ = new BlockManagerId$();
   private static final LoadingCache blockManagerIdCache = CacheBuilder.newBuilder().maximumSize(10000L).build(new CacheLoader() {
      public BlockManagerId load(final BlockManagerId id) {
         return id;
      }
   });
   private static final String SHUFFLE_MERGER_IDENTIFIER = "shuffle-push-merger";
   private static final String INVALID_EXECUTOR_ID = "invalid";

   public BlockManagerId apply(final String execId, final String host, final int port, final Option topologyInfo) {
      return this.getCachedBlockManagerId(new BlockManagerId(execId, host, port, topologyInfo));
   }

   public BlockManagerId apply(final ObjectInput in) {
      BlockManagerId obj = new BlockManagerId();
      obj.readExternal(in);
      return this.getCachedBlockManagerId(obj);
   }

   public Option apply$default$4() {
      return .MODULE$;
   }

   public LoadingCache blockManagerIdCache() {
      return blockManagerIdCache;
   }

   public BlockManagerId getCachedBlockManagerId(final BlockManagerId id) {
      return (BlockManagerId)this.blockManagerIdCache().get(id);
   }

   public String SHUFFLE_MERGER_IDENTIFIER() {
      return SHUFFLE_MERGER_IDENTIFIER;
   }

   public String INVALID_EXECUTOR_ID() {
      return INVALID_EXECUTOR_ID;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BlockManagerId$.class);
   }

   private BlockManagerId$() {
   }
}
