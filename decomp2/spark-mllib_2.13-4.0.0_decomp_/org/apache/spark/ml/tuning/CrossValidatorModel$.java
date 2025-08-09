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

public final class CrossValidatorModel$ implements MLReadable, Serializable {
   public static final CrossValidatorModel$ MODULE$ = new CrossValidatorModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public Option copySubModels(final Option subModels) {
      return subModels.map((x$10) -> (Model[][]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(x$10), (x$11) -> (Model[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(x$11), (x$12) -> x$12.copy(ParamMap$.MODULE$.empty()), scala.reflect.ClassTag..MODULE$.apply(Model.class)), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Model.class))));
   }

   public MLReader read() {
      return new CrossValidatorModel.CrossValidatorModelReader();
   }

   public CrossValidatorModel load(final String path) {
      return (CrossValidatorModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CrossValidatorModel$.class);
   }

   private CrossValidatorModel$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
