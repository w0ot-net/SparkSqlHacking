package org.apache.spark.ml.tree;

import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.mllib.tree.configuration.Strategy;
import org.apache.spark.mllib.tree.impurity.Impurity;
import scala.Enumeration;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015rA\u0002\b\u0010\u0011\u0003\u0019\u0012D\u0002\u0004\u001c\u001f!\u00051\u0003\b\u0005\u0006W\u0005!\t!\f\u0005\b]\u0005\u0011\r\u0011\"\u00020\u0011\u0019q\u0014\u0001)A\u0007a!9q(AA\u0001\n\u0013\u0001e\u0001C\u000e\u0010!\u0003\r\t!E$\t\u000b-3A\u0011\u0001'\t\u000fA3!\u0019!C\u0003#\")\u0001L\u0002C\u00033\"1QL\u0002C\u0001#yC\u0011\"a\u0002\u0007\u0005\u0004%)!!\u0003\t\u000f\u0005Ea\u0001\"\u0002\u0002\u0014!q\u0011Q\u0003\u0004\u0011\u0002\u0007\u0005\t\u0011\"\u0003\u0002\u0018\u0005\r\u0012A\u0005+sK\u0016,en]3nE2,\u0007+\u0019:b[NT!\u0001E\t\u0002\tQ\u0014X-\u001a\u0006\u0003%M\t!!\u001c7\u000b\u0005Q)\u0012!B:qCJ\\'B\u0001\f\u0018\u0003\u0019\t\u0007/Y2iK*\t\u0001$A\u0002pe\u001e\u0004\"AG\u0001\u000e\u0003=\u0011!\u0003\u0016:fK\u0016s7/Z7cY\u0016\u0004\u0016M]1ngN\u0019\u0011!H\u0012\u0011\u0005y\tS\"A\u0010\u000b\u0003\u0001\nQa]2bY\u0006L!AI\u0010\u0003\r\u0005s\u0017PU3g!\t!\u0013&D\u0001&\u0015\t1s%\u0001\u0002j_*\t\u0001&\u0001\u0003kCZ\f\u0017B\u0001\u0016&\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\u0019a\u0014N\\5u}\r\u0001A#A\r\u0002AM,\b\u000f]8si\u0016$g)Z1ukJ,7+\u001e2tKR\u001cFO]1uK\u001eLWm]\u000b\u0002aA\u0019a$M\u001a\n\u0005Iz\"!B!se\u0006L\bC\u0001\u001b<\u001d\t)\u0014\b\u0005\u00027?5\tqG\u0003\u00029Y\u00051AH]8pizJ!AO\u0010\u0002\rA\u0013X\rZ3g\u0013\taTH\u0001\u0004TiJLgn\u001a\u0006\u0003u}\t\u0011e];qa>\u0014H/\u001a3GK\u0006$XO]3Tk\n\u001cX\r^*ue\u0006$XmZ5fg\u0002\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012!\u0011\t\u0003\u0005\u0016k\u0011a\u0011\u0006\u0003\t\u001e\nA\u0001\\1oO&\u0011ai\u0011\u0002\u0007\u001f\nTWm\u0019;\u0014\u0007\u0019i\u0002\n\u0005\u0002\u001b\u0013&\u0011!j\u0004\u0002\u0013\t\u0016\u001c\u0017n]5p]R\u0013X-\u001a)be\u0006l7/\u0001\u0004%S:LG\u000f\n\u000b\u0002\u001bB\u0011aDT\u0005\u0003\u001f~\u0011A!\u00168ji\u0006y1/\u001e2tC6\u0004H.\u001b8h%\u0006$X-F\u0001S!\t\u0019f+D\u0001U\u0015\t)\u0016#A\u0003qCJ\fW.\u0003\u0002X)\nYAi\\;cY\u0016\u0004\u0016M]1n\u0003I9W\r^*vEN\fW\u000e\u001d7j]\u001e\u0014\u0016\r^3\u0016\u0003i\u0003\"AH.\n\u0005q{\"A\u0002#pk\ndW-\u0001\bhKR|E\u000eZ*ue\u0006$XmZ=\u0015\u000b}C\u0007O]>\u0011\u0005\u00014W\"A1\u000b\u0005\t\u001c\u0017!D2p]\u001aLw-\u001e:bi&|gN\u0003\u0002\u0011I*\u0011QmE\u0001\u0006[2d\u0017NY\u0005\u0003O\u0006\u0014\u0001b\u0015;sCR,w-\u001f\u0005\u0006S*\u0001\rA[\u0001\u0014G\u0006$XmZ8sS\u000e\fGNR3biV\u0014Xm\u001d\t\u0005i-lW.\u0003\u0002m{\t\u0019Q*\u00199\u0011\u0005yq\u0017BA8 \u0005\rIe\u000e\u001e\u0005\u0006c*\u0001\r!\\\u0001\u000b]Vl7\t\\1tg\u0016\u001c\b\"B:\u000b\u0001\u0004!\u0018aB8mI\u0006cwm\u001c\t\u0003kbt!\u0001\u0019<\n\u0005]\f\u0017\u0001B!mO>L!!\u001f>\u0003\t\u0005cwm\u001c\u0006\u0003o\u0006DQ\u0001 \u0006A\u0002u\f1b\u001c7e\u00136\u0004XO]5usB\u0019a0a\u0001\u000e\u0003}T1!!\u0001d\u0003!IW\u000e];sSRL\u0018bAA\u0003\u007f\nA\u0011*\u001c9ve&$\u00180A\u000bgK\u0006$XO]3Tk\n\u001cX\r^*ue\u0006$XmZ=\u0016\u0005\u0005-\u0001\u0003B*\u0002\u000eMJ1!a\u0004U\u0005\u0015\u0001\u0016M]1n\u0003a9W\r\u001e$fCR,(/Z*vEN,Go\u0015;sCR,w-_\u000b\u0002g\u0005!2/\u001e9fe\u0012:W\r^(mIN#(/\u0019;fOf$2bXA\r\u00037\ti\"a\b\u0002\"!)\u0011.\u0004a\u0001U\")\u0011/\u0004a\u0001[\")1/\u0004a\u0001i\")A0\u0004a\u0001{\")\u0001+\u0004a\u00015&\u0011Q,\u0013"
)
public interface TreeEnsembleParams extends DecisionTreeParams {
   static String[] supportedFeatureSubsetStrategies() {
      return TreeEnsembleParams$.MODULE$.supportedFeatureSubsetStrategies();
   }

   void org$apache$spark$ml$tree$TreeEnsembleParams$_setter_$subsamplingRate_$eq(final DoubleParam x$1);

   void org$apache$spark$ml$tree$TreeEnsembleParams$_setter_$featureSubsetStrategy_$eq(final Param x$1);

   // $FF: synthetic method
   Strategy org$apache$spark$ml$tree$TreeEnsembleParams$$super$getOldStrategy(final Map categoricalFeatures, final int numClasses, final Enumeration.Value oldAlgo, final Impurity oldImpurity, final double subsamplingRate);

   DoubleParam subsamplingRate();

   // $FF: synthetic method
   static double getSubsamplingRate$(final TreeEnsembleParams $this) {
      return $this.getSubsamplingRate();
   }

   default double getSubsamplingRate() {
      return BoxesRunTime.unboxToDouble(this.$(this.subsamplingRate()));
   }

   // $FF: synthetic method
   static Strategy getOldStrategy$(final TreeEnsembleParams $this, final Map categoricalFeatures, final int numClasses, final Enumeration.Value oldAlgo, final Impurity oldImpurity) {
      return $this.getOldStrategy(categoricalFeatures, numClasses, oldAlgo, oldImpurity);
   }

   default Strategy getOldStrategy(final Map categoricalFeatures, final int numClasses, final Enumeration.Value oldAlgo, final Impurity oldImpurity) {
      return this.org$apache$spark$ml$tree$TreeEnsembleParams$$super$getOldStrategy(categoricalFeatures, numClasses, oldAlgo, oldImpurity, this.getSubsamplingRate());
   }

   Param featureSubsetStrategy();

   // $FF: synthetic method
   static String getFeatureSubsetStrategy$(final TreeEnsembleParams $this) {
      return $this.getFeatureSubsetStrategy();
   }

   default String getFeatureSubsetStrategy() {
      return ((String)this.$(this.featureSubsetStrategy())).toLowerCase(Locale.ROOT);
   }

   // $FF: synthetic method
   static boolean $anonfun$featureSubsetStrategy$1(final String value) {
      return .MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])TreeEnsembleParams$.MODULE$.supportedFeatureSubsetStrategies()), value.toLowerCase(Locale.ROOT)) || scala.util.Try..MODULE$.apply((JFunction0.mcI.sp)() -> scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(value))).filter((JFunction1.mcZI.sp)(x$4) -> x$4 > 0).isSuccess() || scala.util.Try..MODULE$.apply((JFunction0.mcD.sp)() -> scala.collection.StringOps..MODULE$.toDouble$extension(scala.Predef..MODULE$.augmentString(value))).filter((JFunction1.mcZD.sp)(x$5) -> x$5 > (double)0).filter((JFunction1.mcZD.sp)(x$6) -> x$6 <= (double)1.0F).isSuccess();
   }

   static void $init$(final TreeEnsembleParams $this) {
      $this.org$apache$spark$ml$tree$TreeEnsembleParams$_setter_$subsamplingRate_$eq(new DoubleParam($this, "subsamplingRate", "Fraction of the training data used for learning each decision tree, in range (0, 1].", ParamValidators$.MODULE$.inRange((double)0.0F, (double)1.0F, false, true)));
      $this.org$apache$spark$ml$tree$TreeEnsembleParams$_setter_$featureSubsetStrategy_$eq(new Param($this, "featureSubsetStrategy", "The number of features to consider for splits at each tree node. Supported options: " + scala.Predef..MODULE$.wrapRefArray((Object[])TreeEnsembleParams$.MODULE$.supportedFeatureSubsetStrategies()).mkString(", ") + ", (0.0-1.0], [1-n].", (value) -> BoxesRunTime.boxToBoolean($anonfun$featureSubsetStrategy$1(value)), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.subsamplingRate().$minus$greater(BoxesRunTime.boxToDouble((double)1.0F)), $this.featureSubsetStrategy().$minus$greater("auto")}));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
