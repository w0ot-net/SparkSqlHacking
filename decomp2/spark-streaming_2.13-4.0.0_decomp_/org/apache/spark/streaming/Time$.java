package org.apache.spark.streaming;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Some;
import scala.math.Ordering;
import scala.package.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Time$ implements Serializable {
   public static final Time$ MODULE$ = new Time$();
   private static final Ordering ordering;

   static {
      ordering = .MODULE$.Ordering().by((time) -> BoxesRunTime.boxToLong($anonfun$ordering$1(time)), scala.math.Ordering.Long..MODULE$);
   }

   public Ordering ordering() {
      return ordering;
   }

   public Time apply(final long millis) {
      return new Time(millis);
   }

   public Option unapply(final Time x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToLong(x$0.org$apache$spark$streaming$Time$$millis())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Time$.class);
   }

   // $FF: synthetic method
   public static final long $anonfun$ordering$1(final Time time) {
      return time.org$apache$spark$streaming$Time$$millis();
   }

   private Time$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
