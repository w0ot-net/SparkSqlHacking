package org.apache.spark.storage;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.Set;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.mutable.LinkedHashSet;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt.;
import scala.runtime.java8.JFunction1;
import scala.util.Random;

public final class BlockReplicationUtils$ {
   public static final BlockReplicationUtils$ MODULE$ = new BlockReplicationUtils$();

   private List getSampleIds(final int n, final int m, final Random r) {
      LinkedHashSet indices = (LinkedHashSet).MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(n - m + 1), n).foldLeft(scala.collection.mutable.LinkedHashSet..MODULE$.empty(), (x0$1, x1$1) -> $anonfun$getSampleIds$1(r, x0$1, BoxesRunTime.unboxToInt(x1$1)));
      return ((IterableOnceOps)indices.map((JFunction1.mcII.sp)(x$1) -> x$1 - 1)).toList();
   }

   public List getRandomSample(final Seq elems, final int m, final Random r) {
      return elems.size() > m ? this.getSampleIds(elems.size(), m, r).map((x$2) -> $anonfun$getRandomSample$1(elems, BoxesRunTime.unboxToInt(x$2))) : ((IterableOnceOps)r.shuffle(elems, scala.collection.BuildFrom..MODULE$.buildFromIterableOps())).toList();
   }

   // $FF: synthetic method
   public static final LinkedHashSet $anonfun$getSampleIds$1(final Random r$1, final LinkedHashSet x0$1, final int x1$1) {
      Tuple2 var4 = new Tuple2(x0$1, BoxesRunTime.boxToInteger(x1$1));
      if (var4 != null) {
         LinkedHashSet set = (LinkedHashSet)var4._1();
         int i = var4._2$mcI$sp();
         int t = r$1.nextInt(i) + 1;
         return set.contains(BoxesRunTime.boxToInteger(t)) ? (LinkedHashSet)set.union((Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{i}))) : (LinkedHashSet)set.union((Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{t})));
      } else {
         throw new MatchError(var4);
      }
   }

   // $FF: synthetic method
   public static final Object $anonfun$getRandomSample$1(final Seq elems$1, final int x$2) {
      return elems$1.apply(x$2);
   }

   private BlockReplicationUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
