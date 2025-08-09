package org.apache.spark.ml.classification;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.regression.DecisionTreeRegressionModel;
import org.apache.spark.ml.regression.DecisionTreeRegressionModel$;
import org.apache.spark.ml.regression.DecisionTreeRegressor;
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

public final class GBTClassificationModel$ implements MLReadable, Serializable {
   public static final GBTClassificationModel$ MODULE$ = new GBTClassificationModel$();
   private static final String org$apache$spark$ml$classification$GBTClassificationModel$$numFeaturesKey;
   private static final String org$apache$spark$ml$classification$GBTClassificationModel$$numTreesKey;

   static {
      MLReadable.$init$(MODULE$);
      org$apache$spark$ml$classification$GBTClassificationModel$$numFeaturesKey = "numFeatures";
      org$apache$spark$ml$classification$GBTClassificationModel$$numTreesKey = "numTrees";
   }

   public String org$apache$spark$ml$classification$GBTClassificationModel$$numFeaturesKey() {
      return org$apache$spark$ml$classification$GBTClassificationModel$$numFeaturesKey;
   }

   public String org$apache$spark$ml$classification$GBTClassificationModel$$numTreesKey() {
      return org$apache$spark$ml$classification$GBTClassificationModel$$numTreesKey;
   }

   public MLReader read() {
      return new GBTClassificationModel.GBTClassificationModelReader();
   }

   public GBTClassificationModel load(final String path) {
      return (GBTClassificationModel)MLReadable.load$(this, path);
   }

   public GBTClassificationModel fromOld(final GradientBoostedTreesModel oldModel, final GBTClassifier parent, final Map categoricalFeatures, final int numFeatures, final int numClasses) {
      boolean var9;
      Predef var10000;
      label22: {
         label21: {
            var10000 = .MODULE$;
            Enumeration.Value var10001 = oldModel.algo();
            Enumeration.Value var6 = Algo$.MODULE$.Classification();
            if (var10001 == null) {
               if (var6 == null) {
                  break label21;
               }
            } else if (var10001.equals(var6)) {
               break label21;
            }

            var9 = false;
            break label22;
         }

         var9 = true;
      }

      var10000.require(var9, () -> "Cannot convert GradientBoostedTreesModel with algo=" + oldModel.algo() + " (old API) to GBTClassificationModel (new API).");
      DecisionTreeRegressionModel[] newTrees = (DecisionTreeRegressionModel[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(oldModel.trees()), (tree) -> DecisionTreeRegressionModel$.MODULE$.fromOld(tree, (DecisionTreeRegressor)null, categoricalFeatures, DecisionTreeRegressionModel$.MODULE$.fromOld$default$4()), scala.reflect.ClassTag..MODULE$.apply(DecisionTreeRegressionModel.class));
      String uid = parent != null ? parent.uid() : Identifiable$.MODULE$.randomUID("gbtc");
      return new GBTClassificationModel(uid, newTrees, oldModel.treeWeights(), numFeatures, numClasses);
   }

   public int fromOld$default$4() {
      return -1;
   }

   public int fromOld$default$5() {
      return 2;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GBTClassificationModel$.class);
   }

   private GBTClassificationModel$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
