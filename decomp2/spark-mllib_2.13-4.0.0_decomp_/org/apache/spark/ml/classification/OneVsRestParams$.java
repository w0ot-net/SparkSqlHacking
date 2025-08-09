package org.apache.spark.ml.classification;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.Params;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.sql.SparkSession;
import org.json4s.JValue;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Seq;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class OneVsRestParams$ implements ClassifierTypeTrait, Serializable {
   public static final OneVsRestParams$ MODULE$ = new OneVsRestParams$();

   public void validateParams(final OneVsRestParams instance) {
      if (instance instanceof OneVsRestModel var4) {
         .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(var4.models()), (x$1) -> {
            $anonfun$validateParams$1(x$1);
            return BoxedUnit.UNIT;
         });
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var5 = BoxedUnit.UNIT;
      }

      checkElement$1(instance.getClassifier(), "classifier");
   }

   public void saveImpl(final String path, final OneVsRestParams instance, final SparkSession spark, final Option extraMetadata) {
      Seq params = instance.extractParamMap().toSeq();
      JValue jsonParams = org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonDSL..MODULE$.list2jvalue(((IterableOnceOps)((IterableOps)params.filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$saveImpl$1(x0$1)))).map((x0$2) -> {
         if (x0$2 != null) {
            Param p = x0$2.param();
            Object v = x0$2.value();
            return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(p.name()), org.json4s.jackson.JsonMethods..MODULE$.parse(p.jsonEncode(v), org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput()));
         } else {
            throw new MatchError(x0$2);
         }
      })).toList()), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3());
      DefaultParamsWriter$.MODULE$.saveMetadata(instance, path, (SparkSession)spark, extraMetadata, new Some(jsonParams));
      String classifierPath = (new Path(path, "classifier")).toString();
      ((MLWritable)instance.getClassifier()).save(classifierPath);
   }

   public Option saveImpl$default$4() {
      return scala.None..MODULE$;
   }

   public Tuple2 loadImpl(final String path, final SparkSession spark, final String expectedClassName) {
      DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, spark, expectedClassName);
      String classifierPath = (new Path(path, "classifier")).toString();
      Classifier estimator = (Classifier)DefaultParamsReader$.MODULE$.loadParamsInstance(classifierPath, spark);
      return new Tuple2(metadata, estimator);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OneVsRestParams$.class);
   }

   private static final void checkElement$1(final Params elem, final String name) {
      if (elem instanceof MLWritable) {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new UnsupportedOperationException("OneVsRest write will fail  because it contains " + name + " which does not implement MLWritable. Non-Writable " + name + ": " + elem.uid() + " of type " + elem.getClass());
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$validateParams$1(final ClassificationModel x$1) {
      checkElement$1(x$1, "model");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$saveImpl$1(final ParamPair x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         boolean var5;
         label30: {
            Param p = x0$1.param();
            String var10000 = p.name();
            String var4 = "classifier";
            if (var10000 == null) {
               if (var4 != null) {
                  break label30;
               }
            } else if (!var10000.equals(var4)) {
               break label30;
            }

            var5 = false;
            return var5;
         }

         var5 = true;
         return var5;
      }
   }

   private OneVsRestParams$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
