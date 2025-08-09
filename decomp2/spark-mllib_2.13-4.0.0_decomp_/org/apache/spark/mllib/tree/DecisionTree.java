package org.apache.spark.mllib.tree;

import java.io.Serializable;
import java.util.Map;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.mllib.tree.configuration.Strategy;
import org.apache.spark.mllib.tree.impurity.Impurity;
import org.apache.spark.mllib.tree.model.DecisionTreeModel;
import org.apache.spark.mllib.tree.model.RandomForestModel;
import org.apache.spark.rdd.RDD;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t\ra\u0001\u0002\u000b\u0016\u0001\u0001B\u0001\"\u000f\u0001\u0003\u0006\u0004%IA\u000f\u0005\t\u0003\u0002\u0011\t\u0011)A\u0005w!A!\t\u0001BC\u0002\u0013%1\t\u0003\u0005H\u0001\t\u0005\t\u0015!\u0003E\u0011\u0019A\u0005\u0001\"\u0001\u001a\u0013\")\u0001\n\u0001C\u0001\u001d\")\u0011\f\u0001C\u00015\u001e)1/\u0006E\u0001i\u001a)A#\u0006E\u0001k\")\u0001*\u0003C\u0001m\")q/\u0003C\u0001q\")q/\u0003C\u0001y\"1q/\u0003C\u0001\u0003wAaa^\u0005\u0005\u0002\u0005-\u0003bBAB\u0013\u0011\u0005\u0011Q\u0011\u0005\b\u0003\u0007KA\u0011AAP\u0011\u001d\t9.\u0003C\u0001\u00033Dq!a6\n\t\u0003\t9\u000fC\u0005\u0002v&\t\t\u0011\"\u0003\u0002x\naA)Z2jg&|g\u000e\u0016:fK*\u0011acF\u0001\u0005iJ,WM\u0003\u0002\u00193\u0005)Q\u000e\u001c7jE*\u0011!dG\u0001\u0006gB\f'o\u001b\u0006\u00039u\ta!\u00199bG\",'\"\u0001\u0010\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001\tse\r\t\u0003E\u0015j\u0011a\t\u0006\u0002I\u0005)1oY1mC&\u0011ae\t\u0002\u0007\u0003:L(+\u001a4\u0011\u0005!\u0002dBA\u0015/\u001d\tQS&D\u0001,\u0015\tas$\u0001\u0004=e>|GOP\u0005\u0002I%\u0011qfI\u0001\ba\u0006\u001c7.Y4f\u0013\t\t$G\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00020GA\u0011AgN\u0007\u0002k)\u0011a'G\u0001\tS:$XM\u001d8bY&\u0011\u0001(\u000e\u0002\b\u0019><w-\u001b8h\u0003!\u0019HO]1uK\u001eLX#A\u001e\u0011\u0005qzT\"A\u001f\u000b\u0005y*\u0012!D2p]\u001aLw-\u001e:bi&|g.\u0003\u0002A{\tA1\u000b\u001e:bi\u0016<\u00170A\u0005tiJ\fG/Z4zA\u0005!1/Z3e+\u0005!\u0005C\u0001\u0012F\u0013\t15EA\u0002J]R\fQa]3fI\u0002\na\u0001P5oSRtDc\u0001&M\u001bB\u00111\nA\u0007\u0002+!)\u0011(\u0002a\u0001w!)!)\u0002a\u0001\tR\u0011!j\u0014\u0005\u0006s\u0019\u0001\ra\u000f\u0015\u0004\rE;\u0006C\u0001*V\u001b\u0005\u0019&B\u0001+\u001a\u0003)\tgN\\8uCRLwN\\\u0005\u0003-N\u0013QaU5oG\u0016\f\u0013\u0001W\u0001\u0006c9\u0002d\u0006M\u0001\u0004eVtGCA.b!\tav,D\u0001^\u0015\tqV#A\u0003n_\u0012,G.\u0003\u0002a;\n\tB)Z2jg&|g\u000e\u0016:fK6{G-\u001a7\t\u000b\t<\u0001\u0019A2\u0002\u000b%t\u0007/\u001e;\u0011\u0007\u0011<\u0017.D\u0001f\u0015\t1\u0017$A\u0002sI\u0012L!\u0001[3\u0003\u0007I#E\t\u0005\u0002k[6\t1N\u0003\u0002m/\u0005Q!/Z4sKN\u001c\u0018n\u001c8\n\u00059\\'\u0001\u0004'bE\u0016dW\r\u001a)pS:$\bfA\u0004Ra\u0006\n\u0011/A\u00032]Ir\u0003\u0007K\u0002\u0001#^\u000bA\u0002R3dSNLwN\u001c+sK\u0016\u0004\"aS\u0005\u0014\t%\tse\r\u000b\u0002i\u0006)AO]1j]R\u00191,\u001f>\t\u000b\t\\\u0001\u0019A2\t\u000beZ\u0001\u0019A\u001e)\u0007-\tv\u000bF\u0004\\{z\f9#!\u000e\t\u000b\td\u0001\u0019A2\t\r}d\u0001\u0019AA\u0001\u0003\u0011\tGnZ8\u0011\t\u0005\r\u0011\u0011\u0005\b\u0005\u0003\u000b\tiB\u0004\u0003\u0002\b\u0005ma\u0002BA\u0005\u00033qA!a\u0003\u0002\u00189!\u0011QBA\u000b\u001d\u0011\ty!a\u0005\u000f\u0007)\n\t\"C\u0001\u001f\u0013\taR$\u0003\u0002\u001b7%\u0011\u0001$G\u0005\u0003-]I!AP\u000b\n\u0007\u0005}Q(\u0001\u0003BY\u001e|\u0017\u0002BA\u0012\u0003K\u0011A!\u00117h_*\u0019\u0011qD\u001f\t\u000f\u0005%B\u00021\u0001\u0002,\u0005A\u0011.\u001c9ve&$\u0018\u0010\u0005\u0003\u0002.\u0005ERBAA\u0018\u0015\r\tI#F\u0005\u0005\u0003g\tyC\u0001\u0005J[B,(/\u001b;z\u0011\u0019\t9\u0004\u0004a\u0001\t\u0006AQ.\u0019=EKB$\b\u000eK\u0002\r#^#2bWA\u001f\u0003\u007f\t\t%a\u0011\u0002F!)!-\u0004a\u0001G\"1q0\u0004a\u0001\u0003\u0003Aq!!\u000b\u000e\u0001\u0004\tY\u0003\u0003\u0004\u000285\u0001\r\u0001\u0012\u0005\u0007\u0003\u000fj\u0001\u0019\u0001#\u0002\u00159,Xn\u00117bgN,7\u000fK\u0002\u000e#B$\u0012cWA'\u0003\u001f\n\t&a\u0015\u0002V\u0005]\u00131LA7\u0011\u0015\u0011g\u00021\u0001d\u0011\u0019yh\u00021\u0001\u0002\u0002!9\u0011\u0011\u0006\bA\u0002\u0005-\u0002BBA\u001c\u001d\u0001\u0007A\t\u0003\u0004\u0002H9\u0001\r\u0001\u0012\u0005\u0007\u00033r\u0001\u0019\u0001#\u0002\u000f5\f\u0007PQ5og\"9\u0011Q\f\bA\u0002\u0005}\u0013aG9vC:$\u0018\u000e\\3DC2\u001cW\u000f\\1uS>t7\u000b\u001e:bi\u0016<\u0017\u0010\u0005\u0003\u0002b\u0005\u001dd\u0002BA\u0003\u0003GJ1!!\u001a>\u0003A\tV/\u00198uS2,7\u000b\u001e:bi\u0016<\u00170\u0003\u0003\u0002j\u0005-$\u0001E)vC:$\u0018\u000e\\3TiJ\fG/Z4z\u0015\r\t)'\u0010\u0005\b\u0003_r\u0001\u0019AA9\u0003]\u0019\u0017\r^3h_JL7-\u00197GK\u0006$XO]3t\u0013:4w\u000e\u0005\u0004\u0002t\u0005mD\t\u0012\b\u0005\u0003k\n9\b\u0005\u0002+G%\u0019\u0011\u0011P\u0012\u0002\rA\u0013X\rZ3g\u0013\u0011\ti(a \u0003\u00075\u000b\u0007OC\u0002\u0002z\rB3AD)X\u0003=!(/Y5o\u00072\f7o]5gS\u0016\u0014H#D.\u0002\b\u0006%\u00151RAG\u0003+\u000b9\nC\u0003c\u001f\u0001\u00071\r\u0003\u0004\u0002H=\u0001\r\u0001\u0012\u0005\b\u0003_z\u0001\u0019AA9\u0011\u001d\tIc\u0004a\u0001\u0003\u001f\u0003B!a\u001d\u0002\u0012&!\u00111SA@\u0005\u0019\u0019FO]5oO\"1\u0011qG\bA\u0002\u0011Ca!!\u0017\u0010\u0001\u0004!\u0005\u0006B\bR\u00037\u000b#!!(\u0002\u000bEr\u0013G\f\u0019\u0015\u001bm\u000b\t+a-\u00026\u0006=\u0017\u0011[Aj\u0011\u0019\u0011\u0007\u00031\u0001\u0002$B)\u0011QUAXS6\u0011\u0011q\u0015\u0006\u0005\u0003S\u000bY+\u0001\u0003kCZ\f'bAAW3\u0005\u0019\u0011\r]5\n\t\u0005E\u0016q\u0015\u0002\b\u0015\u00064\u0018M\u0015#E\u0011\u0019\t9\u0005\u0005a\u0001\t\"9\u0011q\u000e\tA\u0002\u0005]\u0006\u0003CA]\u0003\u0003\f\u0019-a1\u000e\u0005\u0005m&\u0002BA_\u0003\u007f\u000bA!\u001e;jY*\u0011\u0011\u0011V\u0005\u0005\u0003{\nY\f\u0005\u0003\u0002F\u0006-WBAAd\u0015\u0011\tI-a0\u0002\t1\fgnZ\u0005\u0005\u0003\u001b\f9MA\u0004J]R,w-\u001a:\t\u000f\u0005%\u0002\u00031\u0001\u0002\u0010\"1\u0011q\u0007\tA\u0002\u0011Ca!!\u0017\u0011\u0001\u0004!\u0005\u0006\u0002\tR\u00037\u000ba\u0002\u001e:bS:\u0014Vm\u001a:fgN|'\u000fF\u0006\\\u00037\fi.a8\u0002b\u0006\r\b\"\u00022\u0012\u0001\u0004\u0019\u0007bBA8#\u0001\u0007\u0011\u0011\u000f\u0005\b\u0003S\t\u0002\u0019AAH\u0011\u0019\t9$\u0005a\u0001\t\"1\u0011\u0011L\tA\u0002\u0011CC!E)\u0002\u001cRY1,!;\u0002l\u00065\u0018q^Ay\u0011\u0019\u0011'\u00031\u0001\u0002$\"9\u0011q\u000e\nA\u0002\u0005]\u0006bBA\u0015%\u0001\u0007\u0011q\u0012\u0005\u0007\u0003o\u0011\u0002\u0019\u0001#\t\r\u0005e#\u00031\u0001EQ\u0011\u0011\u0012+a'\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005e\b\u0003BAc\u0003wLA!!@\u0002H\n1qJ\u00196fGRD3!C)XQ\rA\u0011k\u0016"
)
public class DecisionTree implements Serializable, Logging {
   private final Strategy strategy;
   private final int seed;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static DecisionTreeModel trainRegressor(final JavaRDD input, final Map categoricalFeaturesInfo, final String impurity, final int maxDepth, final int maxBins) {
      return DecisionTree$.MODULE$.trainRegressor(input, categoricalFeaturesInfo, impurity, maxDepth, maxBins);
   }

   public static DecisionTreeModel trainRegressor(final RDD input, final scala.collection.immutable.Map categoricalFeaturesInfo, final String impurity, final int maxDepth, final int maxBins) {
      return DecisionTree$.MODULE$.trainRegressor(input, categoricalFeaturesInfo, impurity, maxDepth, maxBins);
   }

   public static DecisionTreeModel trainClassifier(final JavaRDD input, final int numClasses, final Map categoricalFeaturesInfo, final String impurity, final int maxDepth, final int maxBins) {
      return DecisionTree$.MODULE$.trainClassifier(input, numClasses, categoricalFeaturesInfo, impurity, maxDepth, maxBins);
   }

   public static DecisionTreeModel trainClassifier(final RDD input, final int numClasses, final scala.collection.immutable.Map categoricalFeaturesInfo, final String impurity, final int maxDepth, final int maxBins) {
      return DecisionTree$.MODULE$.trainClassifier(input, numClasses, categoricalFeaturesInfo, impurity, maxDepth, maxBins);
   }

   public static DecisionTreeModel train(final RDD input, final Enumeration.Value algo, final Impurity impurity, final int maxDepth, final int numClasses, final int maxBins, final Enumeration.Value quantileCalculationStrategy, final scala.collection.immutable.Map categoricalFeaturesInfo) {
      return DecisionTree$.MODULE$.train(input, algo, impurity, maxDepth, numClasses, maxBins, quantileCalculationStrategy, categoricalFeaturesInfo);
   }

   public static DecisionTreeModel train(final RDD input, final Enumeration.Value algo, final Impurity impurity, final int maxDepth, final int numClasses) {
      return DecisionTree$.MODULE$.train(input, algo, impurity, maxDepth, numClasses);
   }

   public static DecisionTreeModel train(final RDD input, final Enumeration.Value algo, final Impurity impurity, final int maxDepth) {
      return DecisionTree$.MODULE$.train(input, algo, impurity, maxDepth);
   }

   public static DecisionTreeModel train(final RDD input, final Strategy strategy) {
      return DecisionTree$.MODULE$.train(input, strategy);
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

   private int seed() {
      return this.seed;
   }

   public DecisionTreeModel run(final RDD input) {
      RandomForest rf = new RandomForest(this.strategy(), 1, "all", this.seed());
      RandomForestModel rfModel = rf.run(input);
      return rfModel.trees()[0];
   }

   public DecisionTree(final Strategy strategy, final int seed) {
      this.strategy = strategy;
      this.seed = seed;
      Logging.$init$(this);
      strategy.assertValid();
   }

   public DecisionTree(final Strategy strategy) {
      this(strategy, 0);
   }
}
