package org.apache.spark.storage;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkEnv;
import org.apache.spark.scheduler.ExecutorCacheTaskLocation;
import org.apache.spark.util.IdGenerator;
import scala.Predef.;
import scala.collection.IterableOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashMap;
import scala.runtime.java8.JFunction1;

public final class BlockManager$ {
   public static final BlockManager$ MODULE$ = new BlockManager$();
   private static final IdGenerator org$apache$spark$storage$BlockManager$$ID_GENERATOR = new IdGenerator();

   public IdGenerator org$apache$spark$storage$BlockManager$$ID_GENERATOR() {
      return org$apache$spark$storage$BlockManager$$ID_GENERATOR;
   }

   public Map blockIdsToLocations(final BlockId[] blockIds, final SparkEnv env, final BlockManagerMaster blockManagerMaster) {
      .MODULE$.assert(env != null || blockManagerMaster != null);
      Seq blockLocations = (Seq)(blockManagerMaster == null ? org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(env.blockManager().org$apache$spark$storage$BlockManager$$getLocationBlockIds(blockIds)).toImmutableArraySeq() : blockManagerMaster.getLocations(blockIds));
      HashMap blockManagers = new HashMap();
      scala.collection.ArrayOps..MODULE$.indices$extension(.MODULE$.refArrayOps(blockIds)).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> blockManagers.update(blockIds[i], ((IterableOps)blockLocations.apply(i)).map((loc) -> (new ExecutorCacheTaskLocation(loc.host(), loc.executorId())).toString())));
      return blockManagers.toMap(scala..less.colon.less..MODULE$.refl());
   }

   public BlockManagerMaster blockIdsToLocations$default$3() {
      return null;
   }

   private BlockManager$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
