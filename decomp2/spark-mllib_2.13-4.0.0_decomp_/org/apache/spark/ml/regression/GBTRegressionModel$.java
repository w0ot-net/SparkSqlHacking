package org.apache.spark.ml.regression;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.mllib.tree.configuration.Algo$;
import org.apache.spark.mllib.tree.model.GradientBoostedTreesModel;
import scala.Enumeration;
import scala.Predef;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.runtime.ModuleSerializationProxy;

public final class GBTRegressionModel$ implements MLReadable, Serializable {
   public static final GBTRegressionModel$ MODULE$ = new GBTRegressionModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new GBTRegressionModel.GBTRegressionModelReader();
   }

   public GBTRegressionModel load(final String path) {
      return (GBTRegressionModel)MLReadable.load$(this, path);
   }

   public GBTRegressionModel fromOld(final GradientBoostedTreesModel oldModel, final GBTRegressor parent, final Map categoricalFeatures, final int numFeatures) {
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

      var10000.require(var8, () -> "Cannot convert GradientBoostedTreesModel with algo=" + oldModel.algo() + " (old API) to GBTRegressionModel (new API).");
      DecisionTreeRegressionModel[] newTrees = (DecisionTreeRegressionModel[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(oldModel.trees()), (tree) -> DecisionTreeRegressionModel$.MODULE$.fromOld(tree, (DecisionTreeRegressor)null, categoricalFeatures, DecisionTreeRegressionModel$.MODULE$.fromOld$default$4()), scala.reflect.ClassTag..MODULE$.apply(DecisionTreeRegressionModel.class));
      String uid = parent != null ? parent.uid() : Identifiable$.MODULE$.randomUID("gbtr");
      return new GBTRegressionModel(uid, newTrees, oldModel.treeWeights(), numFeatures);
   }

   public int fromOld$default$4() {
      return -1;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GBTRegressionModel$.class);
   }

   private GBTRegressionModel$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
