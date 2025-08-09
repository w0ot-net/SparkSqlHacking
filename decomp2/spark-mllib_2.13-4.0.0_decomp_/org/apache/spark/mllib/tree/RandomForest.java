package org.apache.spark.mllib.tree;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.ml.tree.DecisionTreeModel;
import org.apache.spark.ml.tree.TreeEnsembleParams$;
import org.apache.spark.mllib.tree.configuration.Strategy;
import org.apache.spark.mllib.tree.model.RandomForestModel;
import org.apache.spark.rdd.RDD;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015h\u0001\u0002\r\u001a\t\u0011B\u0001\"\u0010\u0001\u0003\u0006\u0004%IA\u0010\u0005\t\u000b\u0002\u0011\t\u0011)A\u0005\u007f!Aa\t\u0001BC\u0002\u0013%q\t\u0003\u0005L\u0001\t\u0005\t\u0015!\u0003I\u0011!a\u0005A!A!\u0002\u0013i\u0005\u0002C+\u0001\u0005\u000b\u0007I\u0011B$\t\u0011Y\u0003!\u0011!Q\u0001\n!CQa\u0016\u0001\u0005\u0002aCQa\u0018\u0001\u0005\u0002\u0001<Q!^\r\t\u0002Y4Q\u0001G\r\t\u0002]DQaV\u0006\u0005\u0002aDQ!_\u0006\u0005\u0002iDa!_\u0006\u0005\u0002\u0005M\u0001\"CA\u001d\u0017E\u0005I\u0011AA\u001e\u0011\u0019I8\u0002\"\u0001\u0002P!9\u0011QR\u0006\u0005\u0002\u0005=\u0005bBAG\u0017\u0011\u0005\u0011Q\u0014\u0005\n\u0003c[\u0011\u0013!C\u0001\u0003wAq!!$\f\t\u0003\t\u0019\fC\u0005\u0002H.\u0011\r\u0011\"\u0001\u0002J\"A\u00111[\u0006!\u0002\u0013\tY\rC\u0005\u0002X.\t\t\u0011\"\u0003\u0002Z\na!+\u00198e_64uN]3ti*\u0011!dG\u0001\u0005iJ,WM\u0003\u0002\u001d;\u0005)Q\u000e\u001c7jE*\u0011adH\u0001\u0006gB\f'o\u001b\u0006\u0003A\u0005\na!\u00199bG\",'\"\u0001\u0012\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001)3f\u000e\t\u0003M%j\u0011a\n\u0006\u0002Q\u0005)1oY1mC&\u0011!f\n\u0002\u0007\u0003:L(+\u001a4\u0011\u00051\"dBA\u00173\u001d\tq\u0013'D\u00010\u0015\t\u00014%\u0001\u0004=e>|GOP\u0005\u0002Q%\u00111gJ\u0001\ba\u0006\u001c7.Y4f\u0013\t)dG\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00024OA\u0011\u0001hO\u0007\u0002s)\u0011!(H\u0001\tS:$XM\u001d8bY&\u0011A(\u000f\u0002\b\u0019><w-\u001b8h\u0003!\u0019HO]1uK\u001eLX#A \u0011\u0005\u0001\u001bU\"A!\u000b\u0005\tK\u0012!D2p]\u001aLw-\u001e:bi&|g.\u0003\u0002E\u0003\nA1\u000b\u001e:bi\u0016<\u00170A\u0005tiJ\fG/Z4zA\u0005Aa.^7Ue\u0016,7/F\u0001I!\t1\u0013*\u0003\u0002KO\t\u0019\u0011J\u001c;\u0002\u00139,X\u000e\u0016:fKN\u0004\u0013!\u00064fCR,(/Z*vEN,Go\u0015;sCR,w-\u001f\t\u0003\u001dJs!a\u0014)\u0011\u00059:\u0013BA)(\u0003\u0019\u0001&/\u001a3fM&\u00111\u000b\u0016\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005E;\u0013\u0001B:fK\u0012\fQa]3fI\u0002\na\u0001P5oSRtD#B-\\9vs\u0006C\u0001.\u0001\u001b\u0005I\u0002\"B\u001f\t\u0001\u0004y\u0004\"\u0002$\t\u0001\u0004A\u0005\"\u0002'\t\u0001\u0004i\u0005\"B+\t\u0001\u0004A\u0015a\u0001:v]R\u0011\u0011m\u001a\t\u0003E\u0016l\u0011a\u0019\u0006\u0003If\tQ!\\8eK2L!AZ2\u0003#I\u000bg\u000eZ8n\r>\u0014Xm\u001d;N_\u0012,G\u000eC\u0003i\u0013\u0001\u0007\u0011.A\u0003j]B,H\u000fE\u0002k[>l\u0011a\u001b\u0006\u0003Yv\t1A\u001d3e\u0013\tq7NA\u0002S\t\u0012\u0003\"\u0001]:\u000e\u0003ET!A]\u000e\u0002\u0015I,wM]3tg&|g.\u0003\u0002uc\naA*\u00192fY\u0016$\u0007k\\5oi\u0006a!+\u00198e_64uN]3tiB\u0011!lC\n\u0005\u0017\u0015Zs\u0007F\u0001w\u0003=!(/Y5o\u00072\f7o]5gS\u0016\u0014HCB1|yvtx\u0010C\u0003i\u001b\u0001\u0007\u0011\u000eC\u0003>\u001b\u0001\u0007q\bC\u0003G\u001b\u0001\u0007\u0001\nC\u0003M\u001b\u0001\u0007Q\nC\u0003V\u001b\u0001\u0007\u0001\nK\u0003\u000e\u0003\u0007\ty\u0001\u0005\u0003\u0002\u0006\u0005-QBAA\u0004\u0015\r\tI!H\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\u0007\u0003\u000f\u0011QaU5oG\u0016\f#!!\u0005\u0002\u000bEr#G\f\u0019\u0015'\u0005\f)\"a\u0006\u0002\u001c\u0005\u0015\u0012qEA\u0015\u0003[\t\t$!\u000e\t\u000b!t\u0001\u0019A5\t\r\u0005ea\u00021\u0001I\u0003)qW/\\\"mCN\u001cXm\u001d\u0005\b\u0003;q\u0001\u0019AA\u0010\u0003]\u0019\u0017\r^3h_JL7-\u00197GK\u0006$XO]3t\u0013:4w\u000eE\u0003O\u0003CA\u0005*C\u0002\u0002$Q\u00131!T1q\u0011\u00151e\u00021\u0001I\u0011\u0015ae\u00021\u0001N\u0011\u0019\tYC\u0004a\u0001\u001b\u0006A\u0011.\u001c9ve&$\u0018\u0010\u0003\u0004\u000209\u0001\r\u0001S\u0001\t[\u0006DH)\u001a9uQ\"1\u00111\u0007\bA\u0002!\u000bq!\\1y\u0005&t7\u000fC\u0004V\u001dA\u0005\t\u0019\u0001%)\u000b9\t\u0019!a\u0004\u00023Q\u0014\u0018-\u001b8DY\u0006\u001c8/\u001b4jKJ$C-\u001a4bk2$H%O\u000b\u0003\u0003{Q3\u0001SA W\t\t\t\u0005\u0005\u0003\u0002D\u0005-SBAA#\u0015\u0011\t9%!\u0013\u0002\u0013Ut7\r[3dW\u0016$'bAA\u0005O%!\u0011QJA#\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u000b\u0014C\u0006E\u00131MA3\u0003\u007f\n\t)a!\u0002\u0006\u0006\u001d\u0015\u0011\u0012\u0005\u0007QB\u0001\r!a\u0015\u0011\u000b\u0005U\u0013qL8\u000e\u0005\u0005]#\u0002BA-\u00037\nAA[1wC*\u0019\u0011QL\u000f\u0002\u0007\u0005\u0004\u0018.\u0003\u0003\u0002b\u0005]#a\u0002&bm\u0006\u0014F\t\u0012\u0005\u0007\u00033\u0001\u0002\u0019\u0001%\t\u000f\u0005u\u0001\u00031\u0001\u0002hAA\u0011\u0011NA9\u0003g\n\u0019(\u0004\u0002\u0002l)!\u0011QNA8\u0003\u0011)H/\u001b7\u000b\u0005\u0005e\u0013\u0002BA\u0012\u0003W\u0002B!!\u001e\u0002|5\u0011\u0011q\u000f\u0006\u0005\u0003s\ny'\u0001\u0003mC:<\u0017\u0002BA?\u0003o\u0012q!\u00138uK\u001e,'\u000fC\u0003G!\u0001\u0007\u0001\nC\u0003M!\u0001\u0007Q\n\u0003\u0004\u0002,A\u0001\r!\u0014\u0005\u0007\u0003_\u0001\u0002\u0019\u0001%\t\r\u0005M\u0002\u00031\u0001I\u0011\u0015)\u0006\u00031\u0001IQ\u0015\u0001\u00121AA\b\u00039!(/Y5o%\u0016<'/Z:t_J$2\"YAI\u0003'\u000b)*a&\u0002\u001a\")\u0001.\u0005a\u0001S\")Q(\u0005a\u0001\u007f!)a)\u0005a\u0001\u0011\")A*\u0005a\u0001\u001b\")Q+\u0005a\u0001\u0011\"*\u0011#a\u0001\u0002\u0010Q\t\u0012-a(\u0002\"\u0006\r\u0016QUAT\u0003S\u000bY+!,\t\u000b!\u0014\u0002\u0019A5\t\u000f\u0005u!\u00031\u0001\u0002 !)aI\u0005a\u0001\u0011\")AJ\u0005a\u0001\u001b\"1\u00111\u0006\nA\u00025Ca!a\f\u0013\u0001\u0004A\u0005BBA\u001a%\u0001\u0007\u0001\nC\u0004V%A\u0005\t\u0019\u0001%)\u000bI\t\u0019!a\u0004\u00021Q\u0014\u0018-\u001b8SK\u001e\u0014Xm]:pe\u0012\"WMZ1vYR$\u0003\bF\tb\u0003k\u000b9,!/\u0002<\u0006u\u0016qXAa\u0003\u0007Da\u0001\u001b\u000bA\u0002\u0005M\u0003bBA\u000f)\u0001\u0007\u0011q\r\u0005\u0006\rR\u0001\r\u0001\u0013\u0005\u0006\u0019R\u0001\r!\u0014\u0005\u0007\u0003W!\u0002\u0019A'\t\r\u0005=B\u00031\u0001I\u0011\u0019\t\u0019\u0004\u0006a\u0001\u0011\")Q\u000b\u0006a\u0001\u0011\"*A#a\u0001\u0002\u0010\u0005\u00013/\u001e9q_J$X\r\u001a$fCR,(/Z*vEN,Go\u0015;sCR,w-[3t+\t\tY\r\u0005\u0003'\u0003\u001bl\u0015bAAhO\t)\u0011I\u001d:bs\"*Q#a\u0001\u0002\u0010\u0005\t3/\u001e9q_J$X\r\u001a$fCR,(/Z*vEN,Go\u0015;sCR,w-[3tA!*a#a\u0001\u0002\u0010\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u001c\t\u0005\u0003k\ni.\u0003\u0003\u0002`\u0006]$AB(cU\u0016\u001cG\u000fK\u0003\f\u0003\u0007\ty\u0001K\u0003\u000b\u0003\u0007\ty\u0001"
)
public class RandomForest implements Serializable, Logging {
   private final Strategy strategy;
   private final int numTrees;
   private final String featureSubsetStrategy;
   private final int seed;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static String[] supportedFeatureSubsetStrategies() {
      return RandomForest$.MODULE$.supportedFeatureSubsetStrategies();
   }

   public static RandomForestModel trainRegressor(final JavaRDD input, final Map categoricalFeaturesInfo, final int numTrees, final String featureSubsetStrategy, final String impurity, final int maxDepth, final int maxBins, final int seed) {
      return RandomForest$.MODULE$.trainRegressor(input, categoricalFeaturesInfo, numTrees, featureSubsetStrategy, impurity, maxDepth, maxBins, seed);
   }

   public static int trainRegressor$default$8() {
      return RandomForest$.MODULE$.trainRegressor$default$8();
   }

   public static RandomForestModel trainRegressor(final RDD input, final scala.collection.immutable.Map categoricalFeaturesInfo, final int numTrees, final String featureSubsetStrategy, final String impurity, final int maxDepth, final int maxBins, final int seed) {
      return RandomForest$.MODULE$.trainRegressor(input, categoricalFeaturesInfo, numTrees, featureSubsetStrategy, impurity, maxDepth, maxBins, seed);
   }

   public static RandomForestModel trainRegressor(final RDD input, final Strategy strategy, final int numTrees, final String featureSubsetStrategy, final int seed) {
      return RandomForest$.MODULE$.trainRegressor(input, strategy, numTrees, featureSubsetStrategy, seed);
   }

   public static RandomForestModel trainClassifier(final JavaRDD input, final int numClasses, final Map categoricalFeaturesInfo, final int numTrees, final String featureSubsetStrategy, final String impurity, final int maxDepth, final int maxBins, final int seed) {
      return RandomForest$.MODULE$.trainClassifier(input, numClasses, categoricalFeaturesInfo, numTrees, featureSubsetStrategy, impurity, maxDepth, maxBins, seed);
   }

   public static int trainClassifier$default$9() {
      return RandomForest$.MODULE$.trainClassifier$default$9();
   }

   public static RandomForestModel trainClassifier(final RDD input, final int numClasses, final scala.collection.immutable.Map categoricalFeaturesInfo, final int numTrees, final String featureSubsetStrategy, final String impurity, final int maxDepth, final int maxBins, final int seed) {
      return RandomForest$.MODULE$.trainClassifier(input, numClasses, categoricalFeaturesInfo, numTrees, featureSubsetStrategy, impurity, maxDepth, maxBins, seed);
   }

   public static RandomForestModel trainClassifier(final RDD input, final Strategy strategy, final int numTrees, final String featureSubsetStrategy, final int seed) {
      return RandomForest$.MODULE$.trainClassifier(input, strategy, numTrees, featureSubsetStrategy, seed);
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private Strategy strategy() {
      return this.strategy;
   }

   private int numTrees() {
      return this.numTrees;
   }

   private int seed() {
      return this.seed;
   }

   public RandomForestModel run(final RDD input) {
      Strategy treeStrategy = this.strategy().copy();
      if (this.numTrees() == 1) {
         treeStrategy.bootstrap_$eq(false);
      } else {
         treeStrategy.bootstrap_$eq(true);
      }

      DecisionTreeModel[] trees = org.apache.spark.ml.tree.impl.RandomForest$.MODULE$.run(input, treeStrategy, this.numTrees(), this.featureSubsetStrategy, (long)this.seed());
      return new RandomForestModel(this.strategy().algo(), (org.apache.spark.mllib.tree.model.DecisionTreeModel[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(trees), (x$4) -> x$4.toOld(), scala.reflect.ClassTag..MODULE$.apply(org.apache.spark.mllib.tree.model.DecisionTreeModel.class)));
   }

   public RandomForest(final Strategy strategy, final int numTrees, final String featureSubsetStrategy, final int seed) {
      this.strategy = strategy;
      this.numTrees = numTrees;
      this.featureSubsetStrategy = featureSubsetStrategy;
      this.seed = seed;
      Logging.$init$(this);
      strategy.assertValid();
      scala.Predef..MODULE$.require(numTrees > 0, () -> "RandomForest requires numTrees > 0, but was given numTrees = " + this.numTrees() + ".");
      scala.Predef..MODULE$.require(.MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])RandomForest$.MODULE$.supportedFeatureSubsetStrategies()), featureSubsetStrategy) || scala.util.Try..MODULE$.apply((JFunction0.mcI.sp)() -> scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(this.featureSubsetStrategy))).filter((JFunction1.mcZI.sp)(x$1) -> x$1 > 0).isSuccess() || scala.util.Try..MODULE$.apply((JFunction0.mcD.sp)() -> scala.collection.StringOps..MODULE$.toDouble$extension(scala.Predef..MODULE$.augmentString(this.featureSubsetStrategy))).filter((JFunction1.mcZD.sp)(x$2) -> x$2 > (double)0).filter((JFunction1.mcZD.sp)(x$3) -> x$3 <= (double)1.0F).isSuccess(), () -> {
         String var10000 = this.featureSubsetStrategy;
         return "RandomForest given invalid featureSubsetStrategy: " + var10000 + ". Supported values: " + scala.Predef..MODULE$.wrapRefArray((Object[])TreeEnsembleParams$.MODULE$.supportedFeatureSubsetStrategies()).mkString(", ") + ", (0.0-1.0], [1-n].";
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
