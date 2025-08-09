package org.apache.spark.ml.tuning;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.evaluation.Evaluator;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.util.Instrumentation;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;
import scala.Option;
import scala.Tuple4;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eg\u0001\u0003\n\u0014!\u0003\r\t!F\u000f\t\u000bA\u0002A\u0011\u0001\u001a\t\u000fY\u0002!\u0019!C\u0001o!)1\n\u0001C\u0001\u0019\"9!\u000b\u0001b\u0001\n\u0003\u0019\u0006\"B.\u0001\t\u0003a\u0006bB/\u0001\u0005\u0004%\tA\u0018\u0005\u0006M\u0002!\ta\u001a\u0005\u0006Q\u0002!\t\"\u001b\u0005\u0006i\u0002!\t\"^\u0004\u0007}NA\t!F@\u0007\u000fI\u0019\u0002\u0012A\u000b\u0002\u0004!9\u0011QC\u0006\u0005\u0002\u0005]\u0001bBA\r\u0017\u0011\u0005\u00111\u0004\u0005\b\u0003GYA\u0011AA\u0013\u0011%\t\u0019gCI\u0001\n\u0003\t)\u0007C\u0004\u0002|-!\t!! \t\u0013\u0005\u00057\"!A\u0005\n\u0005\r'a\u0004,bY&$\u0017\r^8s!\u0006\u0014\u0018-\\:\u000b\u0005Q)\u0012A\u0002;v]&twM\u0003\u0002\u0017/\u0005\u0011Q\u000e\u001c\u0006\u00031e\tQa\u001d9be.T!AG\u000e\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005a\u0012aA8sON!\u0001A\b\u0013-!\ty\"%D\u0001!\u0015\u0005\t\u0013!B:dC2\f\u0017BA\u0012!\u0005\u0019\te.\u001f*fMB\u0011QEK\u0007\u0002M)\u0011q\u0005K\u0001\u0007g\"\f'/\u001a3\u000b\u0005%*\u0012!\u00029be\u0006l\u0017BA\u0016'\u0005\u001dA\u0015m]*fK\u0012\u0004\"!\f\u0018\u000e\u0003!J!a\f\u0015\u0003\rA\u000b'/Y7t\u0003\u0019!\u0013N\\5uI\r\u0001A#A\u001a\u0011\u0005}!\u0014BA\u001b!\u0005\u0011)f.\u001b;\u0002\u0013\u0015\u001cH/[7bi>\u0014X#\u0001\u001d\u0011\u00075J4(\u0003\u0002;Q\t)\u0001+\u0019:b[B\u0012AH\u0011\t\u0004{y\u0002U\"A\u000b\n\u0005}*\"!C#ti&l\u0017\r^8s!\t\t%\t\u0004\u0001\u0005\u0013\r\u0013\u0011\u0011!A\u0001\u0006\u0003!%aA0%cE\u0011Q\t\u0013\t\u0003?\u0019K!a\u0012\u0011\u0003\u000f9{G\u000f[5oOB\u0011q$S\u0005\u0003\u0015\u0002\u00121!\u00118z\u000319W\r^#ti&l\u0017\r^8s+\u0005i\u0005G\u0001(Q!\ridh\u0014\t\u0003\u0003B#\u0011\"U\u0002\u0002\u0002\u0003\u0005)\u0011\u0001#\u0003\u0007}##'\u0001\nfgRLW.\u0019;peB\u000b'/Y7NCB\u001cX#\u0001+\u0011\u00075JT\u000bE\u0002 -bK!a\u0016\u0011\u0003\u000b\u0005\u0013(/Y=\u0011\u00055J\u0016B\u0001.)\u0005!\u0001\u0016M]1n\u001b\u0006\u0004\u0018!F4fi\u0016\u001bH/[7bi>\u0014\b+\u0019:b[6\u000b\u0007o]\u000b\u0002+\u0006IQM^1mk\u0006$xN]\u000b\u0002?B\u0019Q&\u000f1\u0011\u0005\u0005$W\"\u00012\u000b\u0005\r,\u0012AC3wC2,\u0018\r^5p]&\u0011QM\u0019\u0002\n\u000bZ\fG.^1u_J\fAbZ3u\u000bZ\fG.^1u_J,\u0012\u0001Y\u0001\u0014iJ\fgn\u001d4pe6\u001c6\r[3nC&k\u0007\u000f\u001c\u000b\u0003UJ\u0004\"a\u001b9\u000e\u00031T!!\u001c8\u0002\u000bQL\b/Z:\u000b\u0005=<\u0012aA:rY&\u0011\u0011\u000f\u001c\u0002\u000b'R\u0014Xo\u0019;UsB,\u0007\"B:\t\u0001\u0004Q\u0017AB:dQ\u0016l\u0017-A\bm_\u001e$VO\\5oOB\u000b'/Y7t)\t\u0019d\u000fC\u0003x\u0013\u0001\u0007\u00010A\bj]N$(/^7f]R\fG/[8o!\tIH0D\u0001{\u0015\tYX#\u0001\u0003vi&d\u0017BA?{\u0005=Ien\u001d;sk6,g\u000e^1uS>t\u0017a\u0004,bY&$\u0017\r^8s!\u0006\u0014\u0018-\\:\u0011\u0007\u0005\u00051\"D\u0001\u0014'\u0011Ya$!\u0002\u0011\t\u0005\u001d\u0011\u0011C\u0007\u0003\u0003\u0013QA!a\u0003\u0002\u000e\u0005\u0011\u0011n\u001c\u0006\u0003\u0003\u001f\tAA[1wC&!\u00111CA\u0005\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\u0019a\u0014N\\5u}Q\tq0\u0001\bwC2LG-\u0019;f!\u0006\u0014\u0018-\\:\u0015\u0007M\ni\u0002C\u0004\u0002 5\u0001\r!!\t\u0002\u0011%t7\u000f^1oG\u0016\u00042!!\u0001\u0001\u0003!\u0019\u0018M^3J[BdG#C\u001a\u0002(\u0005\u0005\u00131IA'\u0011\u001d\tIC\u0004a\u0001\u0003W\tA\u0001]1uQB!\u0011QFA\u001e\u001d\u0011\ty#a\u000e\u0011\u0007\u0005E\u0002%\u0004\u0002\u00024)\u0019\u0011QG\u0019\u0002\rq\u0012xn\u001c;?\u0013\r\tI\u0004I\u0001\u0007!J,G-\u001a4\n\t\u0005u\u0012q\b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005e\u0002\u0005C\u0004\u0002 9\u0001\r!!\t\t\raq\u0001\u0019AA#!\u0011\t9%!\u0013\u000e\u00039L1!a\u0013o\u00051\u0019\u0006/\u0019:l'\u0016\u001c8/[8o\u0011%\tyE\u0004I\u0001\u0002\u0004\t\t&A\u0007fqR\u0014\u0018-T3uC\u0012\fG/\u0019\t\u0006?\u0005M\u0013qK\u0005\u0004\u0003+\u0002#AB(qi&|g\u000e\u0005\u0003\u0002Z\u0005}SBAA.\u0015\r\tifG\u0001\u0007UN|g\u000eN:\n\t\u0005\u0005\u00141\f\u0002\b\u0015>\u0013'.Z2u\u0003I\u0019\u0018M^3J[BdG\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005\u0005\u001d$\u0006BA)\u0003SZ#!a\u001b\u0011\t\u00055\u0014qO\u0007\u0003\u0003_RA!!\u001d\u0002t\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003k\u0002\u0013AC1o]>$\u0018\r^5p]&!\u0011\u0011PA8\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\tY>\fG-S7qYV!\u0011qPAW)!\t\t)!/\u0002<\u0006u\u0006#C\u0010\u0002\u0004\u0006\u001d\u0015\u0011\u00161V\u0013\r\t)\t\t\u0002\u0007)V\u0004H.\u001a\u001b\u0011\t\u0005%\u00151\u0015\b\u0005\u0003\u0017\u000byJ\u0004\u0003\u0002\u000e\u0006ue\u0002BAH\u00037sA!!%\u0002\u001a:!\u00111SAL\u001d\u0011\t\t$!&\n\u0003qI!AG\u000e\n\u0005aI\u0012B\u0001\f\u0018\u0013\tYX#C\u0002\u0002\"j\f1\u0003R3gCVdG\u000fU1sC6\u001c(+Z1eKJLA!!*\u0002(\nAQ*\u001a;bI\u0006$\u0018MC\u0002\u0002\"j\u0004B!\u0010 \u0002,B\u0019\u0011)!,\u0005\u000f\u0005=\u0006C1\u0001\u00022\n\tQ*E\u0002F\u0003g\u0003R!PA[\u0003WK1!a.\u0016\u0005\u0015iu\u000eZ3m\u0011\u001d\tI\u0003\u0005a\u0001\u0003WAa\u0001\u0007\tA\u0002\u0005\u0015\u0003bBA`!\u0001\u0007\u00111F\u0001\u0012Kb\u0004Xm\u0019;fI\u000ec\u0017m]:OC6,\u0017\u0001D<sSR,'+\u001a9mC\u000e,GCAAc!\u0011\t9-!4\u000e\u0005\u0005%'\u0002BAf\u0003\u001b\tA\u0001\\1oO&!\u0011qZAe\u0005\u0019y%M[3di\u0002"
)
public interface ValidatorParams extends HasSeed {
   static Tuple4 loadImpl(final String path, final SparkSession spark, final String expectedClassName) {
      return ValidatorParams$.MODULE$.loadImpl(path, spark, expectedClassName);
   }

   static Option saveImpl$default$4() {
      return ValidatorParams$.MODULE$.saveImpl$default$4();
   }

   static void saveImpl(final String path, final ValidatorParams instance, final SparkSession spark, final Option extraMetadata) {
      ValidatorParams$.MODULE$.saveImpl(path, instance, spark, extraMetadata);
   }

   static void validateParams(final ValidatorParams instance) {
      ValidatorParams$.MODULE$.validateParams(instance);
   }

   void org$apache$spark$ml$tuning$ValidatorParams$_setter_$estimator_$eq(final Param x$1);

   void org$apache$spark$ml$tuning$ValidatorParams$_setter_$estimatorParamMaps_$eq(final Param x$1);

   void org$apache$spark$ml$tuning$ValidatorParams$_setter_$evaluator_$eq(final Param x$1);

   Param estimator();

   // $FF: synthetic method
   static Estimator getEstimator$(final ValidatorParams $this) {
      return $this.getEstimator();
   }

   default Estimator getEstimator() {
      return (Estimator)this.$(this.estimator());
   }

   Param estimatorParamMaps();

   // $FF: synthetic method
   static ParamMap[] getEstimatorParamMaps$(final ValidatorParams $this) {
      return $this.getEstimatorParamMaps();
   }

   default ParamMap[] getEstimatorParamMaps() {
      return (ParamMap[])this.$(this.estimatorParamMaps());
   }

   Param evaluator();

   // $FF: synthetic method
   static Evaluator getEvaluator$(final ValidatorParams $this) {
      return $this.getEvaluator();
   }

   default Evaluator getEvaluator() {
      return (Evaluator)this.$(this.evaluator());
   }

   // $FF: synthetic method
   static StructType transformSchemaImpl$(final ValidatorParams $this, final StructType schema) {
      return $this.transformSchemaImpl(schema);
   }

   default StructType transformSchemaImpl(final StructType schema) {
      .MODULE$.require(scala.collection.ArrayOps..MODULE$.nonEmpty$extension(.MODULE$.refArrayOps(this.$(this.estimatorParamMaps()))), () -> "Validator requires non-empty estimatorParamMaps");
      ParamMap firstEstimatorParamMap = (ParamMap)scala.collection.ArrayOps..MODULE$.head$extension(.MODULE$.refArrayOps(this.$(this.estimatorParamMaps())));
      Estimator est = (Estimator)this.$(this.estimator());
      scala.collection.ArrayOps..MODULE$.foreach$extension(.MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.tail$extension(.MODULE$.refArrayOps(this.$(this.estimatorParamMaps())))), (paramMap) -> est.copy(paramMap).transformSchema(schema));
      return est.copy(firstEstimatorParamMap).transformSchema(schema);
   }

   // $FF: synthetic method
   static void logTuningParams$(final ValidatorParams $this, final Instrumentation instrumentation) {
      $this.logTuningParams(instrumentation);
   }

   default void logTuningParams(final Instrumentation instrumentation) {
      instrumentation.logNamedValue("estimator", this.$(this.estimator()).getClass().getCanonicalName());
      instrumentation.logNamedValue("evaluator", this.$(this.evaluator()).getClass().getCanonicalName());
      instrumentation.logNamedValue("estimatorParamMapsLength", (long)((ParamMap[])this.$(this.estimatorParamMaps())).length);
   }

   static void $init$(final ValidatorParams $this) {
      $this.org$apache$spark$ml$tuning$ValidatorParams$_setter_$estimator_$eq(new Param($this, "estimator", "estimator for selection", scala.reflect.ClassTag..MODULE$.apply(Estimator.class)));
      $this.org$apache$spark$ml$tuning$ValidatorParams$_setter_$estimatorParamMaps_$eq(new Param($this, "estimatorParamMaps", "param maps for the estimator", scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(ParamMap.class))));
      $this.org$apache$spark$ml$tuning$ValidatorParams$_setter_$evaluator_$eq(new Param($this, "evaluator", "evaluator used to select hyper-parameters that maximize the validated metric", scala.reflect.ClassTag..MODULE$.apply(Evaluator.class)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
