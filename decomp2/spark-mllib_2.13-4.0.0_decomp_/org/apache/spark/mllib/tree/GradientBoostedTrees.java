package org.apache.spark.mllib.tree;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.ml.feature.Instance;
import org.apache.spark.ml.regression.DecisionTreeRegressionModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.tree.configuration.BoostingStrategy;
import org.apache.spark.mllib.tree.model.DecisionTreeModel;
import org.apache.spark.mllib.tree.model.GradientBoostedTreesModel;
import org.apache.spark.rdd.RDD;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015c\u0001B\t\u0013\u0001uA\u0001B\u000e\u0001\u0003\u0006\u0004%Ia\u000e\u0005\t}\u0001\u0011\t\u0011)A\u0005q!Aq\b\u0001BC\u0002\u0013%\u0001\t\u0003\u0005E\u0001\t\u0005\t\u0015!\u0003B\u0011\u0019)\u0005\u0001\"\u0001\u0017\r\")Q\t\u0001C\u0001\u0017\")a\u000b\u0001C\u0001/\")a\u000b\u0001C\u0001[\")\u0001\u0010\u0001C\u0001s\"1\u0001\u0010\u0001C\u0001\u0003\u00039q!a\u0003\u0013\u0011\u0003\tiA\u0002\u0004\u0012%!\u0005\u0011q\u0002\u0005\u0007\u000b2!\t!!\b\t\u000f\u0005}A\u0002\"\u0001\u0002\"!9\u0011q\u0004\u0007\u0005\u0002\u0005%\u0002\"CA\u0019\u0019\u0005\u0005I\u0011BA\u001a\u0005Q9%/\u00193jK:$(i\\8ti\u0016$GK]3fg*\u00111\u0003F\u0001\u0005iJ,WM\u0003\u0002\u0016-\u0005)Q\u000e\u001c7jE*\u0011q\u0003G\u0001\u0006gB\f'o\u001b\u0006\u00033i\ta!\u00199bG\",'\"A\u000e\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001qB\u0005\r\t\u0003?\tj\u0011\u0001\t\u0006\u0002C\u0005)1oY1mC&\u00111\u0005\t\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u0015jcB\u0001\u0014,\u001d\t9#&D\u0001)\u0015\tIC$\u0001\u0004=e>|GOP\u0005\u0002C%\u0011A\u0006I\u0001\ba\u0006\u001c7.Y4f\u0013\tqsF\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002-AA\u0011\u0011\u0007N\u0007\u0002e)\u00111GF\u0001\tS:$XM\u001d8bY&\u0011QG\r\u0002\b\u0019><w-\u001b8h\u0003A\u0011wn\\:uS:<7\u000b\u001e:bi\u0016<\u00170F\u00019!\tID(D\u0001;\u0015\tY$#A\u0007d_:4\u0017nZ;sCRLwN\\\u0005\u0003{i\u0012\u0001CQ8pgRLgnZ*ue\u0006$XmZ=\u0002#\t|wn\u001d;j]\u001e\u001cFO]1uK\u001eL\b%\u0001\u0003tK\u0016$W#A!\u0011\u0005}\u0011\u0015BA\"!\u0005\rIe\u000e^\u0001\u0006g\u0016,G\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007\u001dK%\n\u0005\u0002I\u00015\t!\u0003C\u00037\u000b\u0001\u0007\u0001\bC\u0003@\u000b\u0001\u0007\u0011\t\u0006\u0002H\u0019\")aG\u0002a\u0001q!\u001aaA\u0014+\u0011\u0005=\u0013V\"\u0001)\u000b\u0005E3\u0012AC1o]>$\u0018\r^5p]&\u00111\u000b\u0015\u0002\u0006'&t7-Z\u0011\u0002+\u0006)\u0011G\f\u001a/a\u0005\u0019!/\u001e8\u0015\u0005as\u0006CA-]\u001b\u0005Q&BA.\u0013\u0003\u0015iw\u000eZ3m\u0013\ti&LA\rHe\u0006$\u0017.\u001a8u\u0005>|7\u000f^3e)J,Wm]'pI\u0016d\u0007\"B0\b\u0001\u0004\u0001\u0017!B5oaV$\bcA1eM6\t!M\u0003\u0002d-\u0005\u0019!\u000f\u001a3\n\u0005\u0015\u0014'a\u0001*E\tB\u0011qM[\u0007\u0002Q*\u0011\u0011\u000eF\u0001\u000be\u0016<'/Z:tS>t\u0017BA6i\u00051a\u0015MY3mK\u0012\u0004v.\u001b8uQ\r9a\n\u0016\u000b\u00031:DQa\u0018\u0005A\u0002=\u00042\u0001];g\u001b\u0005\t(B\u0001:t\u0003\u0011Q\u0017M^1\u000b\u0005Q4\u0012aA1qS&\u0011a/\u001d\u0002\b\u0015\u00064\u0018M\u0015#EQ\rAa\nV\u0001\u0012eVtw+\u001b;i-\u0006d\u0017\u000eZ1uS>tGc\u0001-{w\")q,\u0003a\u0001A\")A0\u0003a\u0001A\u0006ya/\u00197jI\u0006$\u0018n\u001c8J]B,H\u000fK\u0002\n\u001dz\f\u0013a`\u0001\u0006c9\"d\u0006\r\u000b\u00061\u0006\r\u0011Q\u0001\u0005\u0006?*\u0001\ra\u001c\u0005\u0006y*\u0001\ra\u001c\u0015\u0004\u00159s\bf\u0001\u0001O)\u0006!rI]1eS\u0016tGOQ8pgR,G\r\u0016:fKN\u0004\"\u0001\u0013\u0007\u0014\u000b1q\u0002'!\u0005\u0011\t\u0005M\u00111D\u0007\u0003\u0003+QA!a\u0006\u0002\u001a\u0005\u0011\u0011n\u001c\u0006\u0002e&\u0019a&!\u0006\u0015\u0005\u00055\u0011!\u0002;sC&tG#\u0002-\u0002$\u0005\u0015\u0002\"B0\u000f\u0001\u0004\u0001\u0007\"\u0002\u001c\u000f\u0001\u0004A\u0004f\u0001\bO)R)\u0001,a\u000b\u0002.!)ql\u0004a\u0001_\")ag\u0004a\u0001q!\u001aqB\u0014+\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005U\u0002\u0003BA\u001c\u0003{i!!!\u000f\u000b\t\u0005m\u0012\u0011D\u0001\u0005Y\u0006tw-\u0003\u0003\u0002@\u0005e\"AB(cU\u0016\u001cG\u000fK\u0002\r\u001dRC3a\u0003(U\u0001"
)
public class GradientBoostedTrees implements Serializable, Logging {
   private final BoostingStrategy boostingStrategy;
   private final int seed;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static GradientBoostedTreesModel train(final JavaRDD input, final BoostingStrategy boostingStrategy) {
      return GradientBoostedTrees$.MODULE$.train(input, boostingStrategy);
   }

   public static GradientBoostedTreesModel train(final RDD input, final BoostingStrategy boostingStrategy) {
      return GradientBoostedTrees$.MODULE$.train(input, boostingStrategy);
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

   private BoostingStrategy boostingStrategy() {
      return this.boostingStrategy;
   }

   private int seed() {
      return this.seed;
   }

   public GradientBoostedTreesModel run(final RDD input) {
      Enumeration.Value algo = this.boostingStrategy().treeStrategy().algo();
      Tuple2 var5 = org.apache.spark.ml.tree.impl.GradientBoostedTrees$.MODULE$.run(input.map((x0$1) -> {
         if (x0$1 != null) {
            double label = x0$1.label();
            Vector features = x0$1.features();
            return new Instance(label, (double)1.0F, features.asML());
         } else {
            throw new MatchError(x0$1);
         }
      }, .MODULE$.apply(Instance.class)), this.boostingStrategy(), (long)this.seed(), "all", org.apache.spark.ml.tree.impl.GradientBoostedTrees$.MODULE$.run$default$5());
      if (var5 != null) {
         DecisionTreeRegressionModel[] trees = (DecisionTreeRegressionModel[])var5._1();
         double[] treeWeights = (double[])var5._2();
         Tuple2 var4 = new Tuple2(trees, treeWeights);
         DecisionTreeRegressionModel[] trees = (DecisionTreeRegressionModel[])var4._1();
         double[] treeWeights = (double[])var4._2();
         return new GradientBoostedTreesModel(algo, (DecisionTreeModel[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(trees), (x$2) -> x$2.toOld(), .MODULE$.apply(DecisionTreeModel.class)), treeWeights);
      } else {
         throw new MatchError(var5);
      }
   }

   public GradientBoostedTreesModel run(final JavaRDD input) {
      return this.run(input.rdd());
   }

   public GradientBoostedTreesModel runWithValidation(final RDD input, final RDD validationInput) {
      Enumeration.Value algo = this.boostingStrategy().treeStrategy().algo();
      Tuple2 var6 = org.apache.spark.ml.tree.impl.GradientBoostedTrees$.MODULE$.runWithValidation(input.map((x0$1) -> {
         if (x0$1 != null) {
            double label = x0$1.label();
            Vector features = x0$1.features();
            return new Instance(label, (double)1.0F, features.asML());
         } else {
            throw new MatchError(x0$1);
         }
      }, .MODULE$.apply(Instance.class)), validationInput.map((x0$2) -> {
         if (x0$2 != null) {
            double label = x0$2.label();
            Vector features = x0$2.features();
            return new Instance(label, (double)1.0F, features.asML());
         } else {
            throw new MatchError(x0$2);
         }
      }, .MODULE$.apply(Instance.class)), this.boostingStrategy(), (long)this.seed(), "all", org.apache.spark.ml.tree.impl.GradientBoostedTrees$.MODULE$.runWithValidation$default$6());
      if (var6 != null) {
         DecisionTreeRegressionModel[] trees = (DecisionTreeRegressionModel[])var6._1();
         double[] treeWeights = (double[])var6._2();
         Tuple2 var5 = new Tuple2(trees, treeWeights);
         DecisionTreeRegressionModel[] trees = (DecisionTreeRegressionModel[])var5._1();
         double[] treeWeights = (double[])var5._2();
         return new GradientBoostedTreesModel(algo, (DecisionTreeModel[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(trees), (x$4) -> x$4.toOld(), .MODULE$.apply(DecisionTreeModel.class)), treeWeights);
      } else {
         throw new MatchError(var6);
      }
   }

   public GradientBoostedTreesModel runWithValidation(final JavaRDD input, final JavaRDD validationInput) {
      return this.runWithValidation(input.rdd(), validationInput.rdd());
   }

   public GradientBoostedTrees(final BoostingStrategy boostingStrategy, final int seed) {
      this.boostingStrategy = boostingStrategy;
      this.seed = seed;
      Logging.$init$(this);
   }

   public GradientBoostedTrees(final BoostingStrategy boostingStrategy) {
      this(boostingStrategy, 0);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
