package org.apache.spark.ml.regression;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.mllib.tree.configuration.Algo$;
import org.apache.spark.mllib.tree.model.RandomForestModel;
import scala.Enumeration;
import scala.Predef;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.runtime.ModuleSerializationProxy;

public final class RandomForestRegressionModel$ implements MLReadable, Serializable {
   public static final RandomForestRegressionModel$ MODULE$ = new RandomForestRegressionModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new RandomForestRegressionModel.RandomForestRegressionModelReader();
   }

   public RandomForestRegressionModel load(final String path) {
      return (RandomForestRegressionModel)MLReadable.load$(this, path);
   }

   public RandomForestRegressionModel fromOld(final RandomForestModel oldModel, final RandomForestRegressor parent, final Map categoricalFeatures, final int numFeatures) {
      boolean var8;
      Predef var10000;
      label22: {
         label21: {
            var10000 = .MODULE$;
            Enumeration.Value var10001 = oldModel.algo();
            Enumeration.Value var5 = Algo$.MODULE$.Regression();
            if (var10001 == null) {
               if (var5 == null) {
                  break label21;
               }
            } else if (var10001.equals(var5)) {
               break label21;
            }

            var8 = false;
            break label22;
         }

         var8 = true;
      }

      var10000.require(var8, () -> "Cannot convert RandomForestModel with algo=" + oldModel.algo() + " (old API) to RandomForestRegressionModel (new API).");
      DecisionTreeRegressionModel[] newTrees = (DecisionTreeRegressionModel[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(oldModel.trees()), (tree) -> DecisionTreeRegressionModel$.MODULE$.fromOld(tree, (DecisionTreeRegressor)null, categoricalFeatures, DecisionTreeRegressionModel$.MODULE$.fromOld$default$4()), scala.reflect.ClassTag..MODULE$.apply(DecisionTreeRegressionModel.class));
      String uid = parent != null ? parent.uid() : Identifiable$.MODULE$.randomUID("rfr");
      return new RandomForestRegressionModel(uid, newTrees, numFeatures);
   }

   public int fromOld$default$4() {
      return -1;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RandomForestRegressionModel$.class);
   }

   private RandomForestRegressionModel$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
