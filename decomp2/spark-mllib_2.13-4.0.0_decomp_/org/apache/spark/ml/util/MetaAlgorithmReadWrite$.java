package org.apache.spark.ml.util;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.classification.Classifier;
import org.apache.spark.ml.classification.OneVsRest;
import org.apache.spark.ml.classification.OneVsRestModel;
import org.apache.spark.ml.feature.RFormulaModel;
import org.apache.spark.ml.param.Params;
import org.apache.spark.ml.tuning.ValidatorParams;
import scala.MatchError;
import scala.Tuple2;
import scala..less.colon.less.;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;

public final class MetaAlgorithmReadWrite$ {
   public static final MetaAlgorithmReadWrite$ MODULE$ = new MetaAlgorithmReadWrite$();

   public Map getUidMap(final Params instance) {
      List uidList = this.getUidMapImpl(instance);
      Map uidMap = uidList.toMap(.MODULE$.refl());
      if (uidList.size() != uidMap.size()) {
         String var10002 = instance.getClass().getName();
         throw new RuntimeException(var10002 + ".load found a compound estimator with stages with duplicate UIDs. List of UIDs: " + uidList.map((x$6) -> (String)x$6._1()).mkString(", ") + ".");
      } else {
         return uidMap;
      }
   }

   private List getUidMapImpl(final Params instance) {
      Params[] var10000;
      if (instance instanceof Pipeline var5) {
         var10000 = var5.getStages();
      } else if (instance instanceof PipelineModel var6) {
         var10000 = var6.stages();
      } else if (instance instanceof ValidatorParams var7) {
         var10000 = (Params[])(new Params[]{var7.getEstimator(), var7.getEvaluator()});
      } else if (instance instanceof OneVsRest var8) {
         var10000 = (Params[])(new Params[]{var8.getClassifier()});
      } else if (instance instanceof OneVsRestModel var9) {
         var10000 = (Params[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps(new Classifier[]{var9.getClassifier()}), var9.models(), scala.reflect.ClassTag..MODULE$.apply(Params.class));
      } else if (instance instanceof RFormulaModel var10) {
         var10000 = (Params[])(new Params[]{var10.pipelineModel()});
      } else {
         if (instance == null) {
            throw new MatchError(instance);
         }

         var10000 = (Params[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(Params.class));
      }

      Params[] subStages = var10000;
      Tuple2[] subStageMaps = (Tuple2[])scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps(subStages), (instancex) -> MODULE$.getUidMapImpl(instancex), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      return (List)(new scala.collection.immutable..colon.colon(new Tuple2(instance.uid(), instance), scala.collection.immutable.Nil..MODULE$)).$plus$plus(scala.Predef..MODULE$.wrapRefArray((Object[])subStageMaps));
   }

   private MetaAlgorithmReadWrite$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
