package org.apache.spark.shuffle;

import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.config.package$;
import org.apache.spark.shuffle.sort.SortShuffleManager;
import org.apache.spark.util.Utils$;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;

public final class ShuffleManager$ {
   public static final ShuffleManager$ MODULE$ = new ShuffleManager$();

   public ShuffleManager create(final SparkConf conf, final boolean isDriver) {
      return (ShuffleManager)Utils$.MODULE$.instantiateSerializerOrShuffleManager(this.getShuffleManagerClassName(conf), conf, isDriver);
   }

   public String getShuffleManagerClassName(final SparkConf conf) {
      Map shortShuffleMgrNames = (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("sort"), SortShuffleManager.class.getName()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("tungsten-sort"), SortShuffleManager.class.getName())})));
      String shuffleMgrName = (String)conf.get(package$.MODULE$.SHUFFLE_MANAGER());
      return (String)shortShuffleMgrNames.getOrElse(shuffleMgrName.toLowerCase(Locale.ROOT), () -> shuffleMgrName);
   }

   private ShuffleManager$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
