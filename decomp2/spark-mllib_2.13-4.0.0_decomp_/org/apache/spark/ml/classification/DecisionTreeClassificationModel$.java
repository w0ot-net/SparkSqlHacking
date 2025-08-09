package org.apache.spark.ml.classification;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.tree.Node;
import org.apache.spark.ml.tree.Node$;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.mllib.tree.configuration.Algo$;
import org.apache.spark.mllib.tree.model.DecisionTreeModel;
import scala.Enumeration;
import scala.Predef;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.runtime.ModuleSerializationProxy;

public final class DecisionTreeClassificationModel$ implements MLReadable, Serializable {
   public static final DecisionTreeClassificationModel$ MODULE$ = new DecisionTreeClassificationModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new DecisionTreeClassificationModel.DecisionTreeClassificationModelReader();
   }

   public DecisionTreeClassificationModel load(final String path) {
      return (DecisionTreeClassificationModel)MLReadable.load$(this, path);
   }

   public DecisionTreeClassificationModel fromOld(final DecisionTreeModel oldModel, final DecisionTreeClassifier parent, final Map categoricalFeatures, final int numFeatures) {
      boolean var8;
      Predef var10000;
      label22: {
         label21: {
            var10000 = .MODULE$;
            Enumeration.Value var10001 = oldModel.algo();
            Enumeration.Value var5 = Algo$.MODULE$.Classification();
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

      var10000.require(var8, () -> "Cannot convert non-classification DecisionTreeModel (old API) to DecisionTreeClassificationModel (new API).  Algo is: " + oldModel.algo());
      Node rootNode = Node$.MODULE$.fromOld(oldModel.topNode(), categoricalFeatures);
      String uid = parent != null ? parent.uid() : Identifiable$.MODULE$.randomUID("dtc");
      return new DecisionTreeClassificationModel(uid, rootNode, numFeatures, -1);
   }

   public int fromOld$default$4() {
      return -1;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DecisionTreeClassificationModel$.class);
   }

   private DecisionTreeClassificationModel$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
