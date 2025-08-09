package org.apache.spark.ml.tuning;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.param.ParamMap$;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.Option;
import scala.collection.ArrayOps.;
import scala.runtime.ModuleSerializationProxy;

public final class TrainValidationSplitModel$ implements MLReadable, Serializable {
   public static final TrainValidationSplitModel$ MODULE$ = new TrainValidationSplitModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public Option copySubModels(final Option subModels) {
      return subModels.map((x$7) -> (Model[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(x$7), (x$8) -> x$8.copy(ParamMap$.MODULE$.empty()), scala.reflect.ClassTag..MODULE$.apply(Model.class)));
   }

   public MLReader read() {
      return new TrainValidationSplitModel.TrainValidationSplitModelReader();
   }

   public TrainValidationSplitModel load(final String path) {
      return (TrainValidationSplitModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TrainValidationSplitModel$.class);
   }

   private TrainValidationSplitModel$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
