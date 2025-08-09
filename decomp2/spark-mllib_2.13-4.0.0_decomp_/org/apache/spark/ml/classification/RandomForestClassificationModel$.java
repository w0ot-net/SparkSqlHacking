package org.apache.spark.ml.classification;

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

public final class RandomForestClassificationModel$ implements MLReadable, Serializable {
   public static final RandomForestClassificationModel$ MODULE$ = new RandomForestClassificationModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new RandomForestClassificationModel.RandomForestClassificationModelReader();
   }

   public RandomForestClassificationModel load(final String path) {
      return (RandomForestClassificationModel)MLReadable.load$(this, path);
   }

   public RandomForestClassificationModel fromOld(final RandomForestModel oldModel, final RandomForestClassifier parent, final Map categoricalFeatures, final int numClasses, final int numFeatures) {
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

      var10000.require(var9, () -> "Cannot convert RandomForestModel with algo=" + oldModel.algo() + " (old API) to RandomForestClassificationModel (new API).");
      DecisionTreeClassificationModel[] newTrees = (DecisionTreeClassificationModel[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(oldModel.trees()), (tree) -> DecisionTreeClassificationModel$.MODULE$.fromOld(tree, (DecisionTreeClassifier)null, categoricalFeatures, DecisionTreeClassificationModel$.MODULE$.fromOld$default$4()), scala.reflect.ClassTag..MODULE$.apply(DecisionTreeClassificationModel.class));
      String uid = parent != null ? parent.uid() : Identifiable$.MODULE$.randomUID("rfc");
      return new RandomForestClassificationModel(uid, newTrees, numFeatures, numClasses);
   }

   public int fromOld$default$5() {
      return -1;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RandomForestClassificationModel$.class);
   }

   private RandomForestClassificationModel$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
