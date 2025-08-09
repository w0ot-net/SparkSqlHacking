package org.apache.spark.rpc;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.util.Utils$;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.concurrent.duration.FiniteDuration;
import scala.concurrent.duration.package;
import scala.concurrent.duration.package.;
import scala.runtime.BoxedUnit;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ObjectRef;

public final class RpcTimeout$ implements Serializable {
   public static final RpcTimeout$ MODULE$ = new RpcTimeout$();

   public RpcTimeout apply(final SparkConf conf, final String timeoutProp) {
      FiniteDuration timeout = (new package.DurationLong(.MODULE$.DurationLong(conf.getTimeAsSeconds(timeoutProp)))).seconds();
      return new RpcTimeout(timeout, timeoutProp);
   }

   public RpcTimeout apply(final SparkConf conf, final String timeoutProp, final String defaultValue) {
      FiniteDuration timeout = (new package.DurationLong(.MODULE$.DurationLong(conf.getTimeAsSeconds(timeoutProp, defaultValue)))).seconds();
      return new RpcTimeout(timeout, timeoutProp);
   }

   public RpcTimeout apply(final SparkConf conf, final Seq timeoutPropList, final String defaultValue) {
      scala.Predef..MODULE$.require(timeoutPropList.nonEmpty());
      Iterator itr = timeoutPropList.iterator();
      ObjectRef foundProp = ObjectRef.create(scala.None..MODULE$);

      while(itr.hasNext() && ((Option)foundProp.elem).isEmpty()) {
         String propKey = (String)itr.next();
         conf.getOption(propKey).foreach((prop) -> {
            $anonfun$apply$1(foundProp, propKey, prop);
            return BoxedUnit.UNIT;
         });
      }

      Tuple2 finalProp = (Tuple2)((Option)foundProp.elem).getOrElse(() -> new Tuple2(timeoutPropList.head(), defaultValue));
      FiniteDuration timeout = (new package.DurationLong(.MODULE$.DurationLong(Utils$.MODULE$.timeStringAsSeconds((String)finalProp._2())))).seconds();
      return new RpcTimeout(timeout, (String)finalProp._1());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RpcTimeout$.class);
   }

   // $FF: synthetic method
   public static final void $anonfun$apply$1(final ObjectRef foundProp$1, final String propKey$1, final String prop) {
      foundProp$1.elem = new Some(new Tuple2(propKey$1, prop));
   }

   private RpcTimeout$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
