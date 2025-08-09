package org.apache.spark.ml.tuning;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.evaluation.Evaluator;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamMap$;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.Params;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MetaAlgorithmReadWrite$;
import org.apache.spark.sql.SparkSession;
import org.json4s.DefaultFormats;
import org.json4s.JString;
import org.json4s.JValue;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple4;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.MapOps;
import scala.collection.ArrayOps.;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.Manifest;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ModuleSerializationProxy;

public final class ValidatorParams$ implements Serializable {
   public static final ValidatorParams$ MODULE$ = new ValidatorParams$();

   public void validateParams(final ValidatorParams instance) {
      checkElement$1(instance.getEvaluator(), "evaluator", instance);
      checkElement$1(instance.getEstimator(), "estimator", instance);
      Map uidToInstance = MetaAlgorithmReadWrite$.MODULE$.getUidMap(instance);
      .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(instance.getEstimatorParamMaps()), (x0$1) -> {
         $anonfun$validateParams$1(uidToInstance, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   public void saveImpl(final String path, final ValidatorParams instance, final SparkSession spark, final Option extraMetadata) {
      IntRef numParamsNotJson = IntRef.create(0);
      String estimatorParamMapsJson = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonDSL..MODULE$.seq2jvalue(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(instance.getEstimatorParamMaps()), (x0$1) -> (Seq)x0$1.toSeq().map((x0$2) -> {
            if (x0$2 != null) {
               Param p = x0$2.param();
               Object v = x0$2.value();
               if (v instanceof DefaultParamsWritable) {
                  DefaultParamsWritable var9 = (DefaultParamsWritable)v;
                  String var10000 = p.name();
                  String relativePath = "epm_" + var10000 + numParamsNotJson.elem;
                  String paramPath = (new Path(path, relativePath)).toString();
                  ++numParamsNotJson.elem;
                  var9.save(paramPath);
                  return (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("parent"), p.parent()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("name"), p.name()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("value"), org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(new JString(relativePath), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("isJson"), org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JBool..MODULE$.apply(false), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3())))})));
               } else if (v instanceof MLWritable) {
                  throw new UnsupportedOperationException("ValidatorParams.saveImpl does not handle parameters of type: MLWritable that are not DefaultParamsWritable");
               } else {
                  return (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("parent"), p.parent()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("name"), p.name()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("value"), p.jsonEncode(v)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("isJson"), org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JBool..MODULE$.apply(true), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3())))})));
               }
            } else {
               throw new MatchError(x0$2);
            }
         }), scala.reflect.ClassTag..MODULE$.apply(Seq.class))).toImmutableArraySeq(), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (m) -> org.json4s.JsonDSL..MODULE$.map2jvalue(m, (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)))), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
      Seq params = instance.extractParamMap().toSeq();
      List skipParams = new scala.collection.immutable..colon.colon("estimator", new scala.collection.immutable..colon.colon("evaluator", new scala.collection.immutable..colon.colon("estimatorParamMaps", scala.collection.immutable.Nil..MODULE$)));
      JValue jsonParams = org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonDSL..MODULE$.list2jvalue((List)((IterableOnceOps)((IterableOps)params.filter((x0$3) -> BoxesRunTime.boxToBoolean($anonfun$saveImpl$6(skipParams, x0$3)))).map((x0$4) -> {
         if (x0$4 != null) {
            Param p = x0$4.param();
            Object v = x0$4.value();
            return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(p.name()), org.json4s.jackson.JsonMethods..MODULE$.parse(p.jsonEncode(v), org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput()));
         } else {
            throw new MatchError(x0$4);
         }
      })).toList().$plus$plus(new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("estimatorParamMaps"), org.json4s.jackson.JsonMethods..MODULE$.parse(estimatorParamMapsJson, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput())), scala.collection.immutable.Nil..MODULE$))), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3());
      DefaultParamsWriter$.MODULE$.saveMetadata(instance, path, (SparkSession)spark, extraMetadata, new Some(jsonParams));
      String evaluatorPath = (new Path(path, "evaluator")).toString();
      ((MLWritable)instance.getEvaluator()).save(evaluatorPath);
      String estimatorPath = (new Path(path, "estimator")).toString();
      ((MLWritable)instance.getEstimator()).save(estimatorPath);
   }

   public Option saveImpl$default$4() {
      return scala.None..MODULE$;
   }

   public Tuple4 loadImpl(final String path, final SparkSession spark, final String expectedClassName) {
      DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, spark, expectedClassName);
      DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
      String evaluatorPath = (new Path(path, "evaluator")).toString();
      Evaluator evaluator = (Evaluator)DefaultParamsReader$.MODULE$.loadParamsInstance(evaluatorPath, spark);
      String estimatorPath = (new Path(path, "estimator")).toString();
      Estimator estimator = (Estimator)DefaultParamsReader$.MODULE$.loadParamsInstance(estimatorPath, spark);
      Map uidToParams = (Map)((MapOps)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(evaluator.uid()), evaluator)})))).$plus$plus(MetaAlgorithmReadWrite$.MODULE$.getUidMap(estimator));
      ParamMap[] estimatorParamMaps = (ParamMap[])((IterableOnceOps)((IterableOps)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata.params()), "estimatorParamMaps")), format, scala.reflect.ManifestFactory..MODULE$.classType(Seq.class, scala.reflect.ManifestFactory..MODULE$.classType(Seq.class, scala.reflect.ManifestFactory..MODULE$.classType(Map.class, scala.reflect.ManifestFactory..MODULE$.classType(String.class), scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Manifest[]{scala.reflect.ManifestFactory..MODULE$.classType(String.class)}))), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$))).map((pMap) -> {
         Seq paramPairs = (Seq)pMap.map((x0$1) -> {
            if (x0$1 == null) {
               throw new MatchError(x0$1);
            } else {
               Params est = (Params)uidToParams.apply(x0$1.apply("parent"));
               Param param = est.getParam((String)x0$1.apply("name"));
               if (x0$1.contains("isJson") && (!x0$1.contains("isJson") || !scala.Predef..MODULE$.boolean2Boolean(scala.collection.StringOps..MODULE$.toBoolean$extension(scala.Predef..MODULE$.augmentString((String)x0$1.apply("isJson")))))) {
                  String relativePath = param.jsonDecode((String)x0$1.apply("value")).toString();
                  MLWritable value = (MLWritable)DefaultParamsReader$.MODULE$.loadParamsInstance((new Path(path, relativePath)).toString(), spark);
                  return param.$minus$greater(value);
               } else {
                  Object value = param.jsonDecode((String)x0$1.apply("value"));
                  return param.$minus$greater(value);
               }
            }
         });
         return ParamMap$.MODULE$.apply(paramPairs);
      })).toArray(scala.reflect.ClassTag..MODULE$.apply(ParamMap.class));
      return new Tuple4(metadata, estimator, evaluator, estimatorParamMaps);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ValidatorParams$.class);
   }

   private static final void checkElement$1(final Params elem, final String name, final ValidatorParams instance$1) {
      if (elem instanceof MLWritable) {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         String var10002 = instance$1.getClass().getName();
         throw new UnsupportedOperationException(var10002 + " write will fail  because it contains " + name + " which does not implement Writable. Non-Writable " + name + ": " + elem.uid() + " of type " + elem.getClass());
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$validateParams$2(final Map uidToInstance$1, final ParamPair x0$2) {
      if (x0$2 != null) {
         Param p = x0$2.param();
         scala.Predef..MODULE$.require(uidToInstance$1.contains(p.parent()), () -> "ValidatorParams save requires all Params in estimatorParamMaps to apply to this ValidatorParams, its Estimator, or its Evaluator. An extraneous Param was found: " + p);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$validateParams$1(final Map uidToInstance$1, final ParamMap x0$1) {
      if (x0$1 != null) {
         x0$1.toSeq().foreach((x0$2) -> {
            $anonfun$validateParams$2(uidToInstance$1, x0$2);
            return BoxedUnit.UNIT;
         });
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$saveImpl$6(final List skipParams$1, final ParamPair x0$3) {
      if (x0$3 != null) {
         Param p = x0$3.param();
         return !skipParams$1.contains(p.name());
      } else {
         throw new MatchError(x0$3);
      }
   }

   private ValidatorParams$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
