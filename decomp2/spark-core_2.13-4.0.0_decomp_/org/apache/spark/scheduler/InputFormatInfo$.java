package org.apache.spark.scheduler;

import java.lang.invoke.SerializedLambda;
import scala..less.colon.less.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.runtime.BoxedUnit;

public final class InputFormatInfo$ {
   public static final InputFormatInfo$ MODULE$ = new InputFormatInfo$();

   public Map computePreferredLocations(final Seq formats) {
      HashMap nodeToSplit = new HashMap();
      formats.foreach((inputSplit) -> {
         $anonfun$computePreferredLocations$1(nodeToSplit, inputSplit);
         return BoxedUnit.UNIT;
      });
      return (Map)nodeToSplit.toMap(.MODULE$.refl()).transform((x$1, v) -> v.toSet());
   }

   // $FF: synthetic method
   public static final void $anonfun$computePreferredLocations$1(final HashMap nodeToSplit$1, final InputFormatInfo inputSplit) {
      Set splits = inputSplit.org$apache$spark$scheduler$InputFormatInfo$$findPreferredLocations();
      splits.foreach((split) -> {
         String location = split.hostLocation();
         HashSet set = (HashSet)nodeToSplit$1.getOrElseUpdate(location, () -> new HashSet());
         return (HashSet)set.$plus$eq(split);
      });
   }

   private InputFormatInfo$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
