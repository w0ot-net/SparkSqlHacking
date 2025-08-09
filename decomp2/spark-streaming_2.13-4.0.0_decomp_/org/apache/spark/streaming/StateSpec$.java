package org.apache.spark.streaming;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.Experimental;
import org.apache.spark.api.java.Optional;
import org.apache.spark.util.SparkClosureCleaner.;
import scala.Function3;
import scala.Function4;
import scala.Option;
import scala.Some;
import scala.runtime.ModuleSerializationProxy;

@Experimental
public final class StateSpec$ implements Serializable {
   public static final StateSpec$ MODULE$ = new StateSpec$();

   public StateSpec function(final Function4 mappingFunction) {
      .MODULE$.clean(mappingFunction, true, .MODULE$.clean$default$3());
      return new StateSpecImpl(mappingFunction);
   }

   public StateSpec function(final Function3 mappingFunction) {
      .MODULE$.clean(mappingFunction, true, .MODULE$.clean$default$3());
      Function4 wrappedFunction = (time, key, value, state) -> new Some(mappingFunction.apply(key, value, state));
      return new StateSpecImpl(wrappedFunction);
   }

   public StateSpec function(final org.apache.spark.api.java.function.Function4 mappingFunction) {
      Function4 wrappedFunc = (time, k, v, s) -> {
         Optional t = (Optional)mappingFunction.call(time, k, org.apache.spark.api.java.JavaUtils..MODULE$.optionToOptional(v), s);
         return (Option)(t.isPresent() ? new Some(t.get()) : scala.None..MODULE$);
      };
      return this.function(wrappedFunc);
   }

   public StateSpec function(final org.apache.spark.api.java.function.Function3 mappingFunction) {
      Function3 wrappedFunc = (k, v, s) -> mappingFunction.call(k, org.apache.spark.api.java.JavaUtils..MODULE$.optionToOptional(v), s);
      return this.function(wrappedFunc);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StateSpec$.class);
   }

   private StateSpec$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
