package org.apache.spark.api.python;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapred.JobConf;
import org.apache.spark.SimpleFutureAction;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkEnv$;
import org.apache.spark.SparkException;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaRDD$;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.input.PortableDataStream;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.rdd.RDD;
import org.apache.spark.rdd.RDD$;
import org.apache.spark.security.SocketAuthHelper;
import org.apache.spark.security.SocketAuthServer$;
import org.apache.spark.security.SocketFuncServer;
import org.apache.spark.util.SerializableConfiguration;
import org.apache.spark.util.ThreadUtils$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Option.;
import scala.collection.BufferedIterator;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.Set;
import scala.collection.mutable.WeakHashMap;
import scala.concurrent.Awaitable;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Null;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;

public final class PythonRDD$ implements Logging, Serializable {
   public static final PythonRDD$ MODULE$ = new PythonRDD$();
   private static SocketAuthHelper authHelper;
   private static final WeakHashMap workerBroadcasts;
   private static transient Logger org$apache$spark$internal$Logging$$log_;
   private static volatile boolean bitmap$0;

   static {
      Logging.$init$(MODULE$);
      workerBroadcasts = new WeakHashMap();
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
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public boolean $lessinit$greater$default$4() {
      return false;
   }

   private WeakHashMap workerBroadcasts() {
      return workerBroadcasts;
   }

   private SocketAuthHelper authHelper$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            SparkConf conf = (SparkConf).MODULE$.apply(SparkEnv$.MODULE$.get()).map((x$3) -> x$3.conf()).getOrElse(() -> new SparkConf());
            authHelper = new SocketAuthHelper(conf);
            bitmap$0 = true;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return authHelper;
   }

   private SocketAuthHelper authHelper() {
      return !bitmap$0 ? this.authHelper$lzycompute() : authHelper;
   }

   public synchronized Set getWorkerBroadcasts(final PythonWorker worker) {
      return (Set)this.workerBroadcasts().getOrElseUpdate(worker, () -> new HashSet());
   }

   public JavaRDD valueOfPair(final JavaPairRDD pair) {
      return JavaRDD$.MODULE$.fromRDD(pair.rdd().mapPartitions((it) -> it.map((x$4) -> (byte[])x$4._2()), true, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE))), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)));
   }

   public Object[] runJob(final SparkContext sc, final JavaRDD rdd, final ArrayList partitions) {
      byte[][][] allPartitions = (byte[][][])sc.runJob(JavaRDD$.MODULE$.toRDD(rdd), (Function1)((x) -> (byte[][])x.toArray(scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)))), (Seq)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(partitions).asScala().toSeq(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE))));
      byte[][] flattenedPartition = (byte[][])scala.Array..MODULE$.concat(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(allPartitions).toImmutableArraySeq(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)));
      Iterator var10001 = scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.refArrayOps((Object[])flattenedPartition));
      int var10002 = rdd.id();
      return this.serveIterator(var10001, "serve RDD " + var10002 + " with partitions " + scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(partitions).asScala().mkString(","));
   }

   public Object[] collectAndServe(final RDD rdd) {
      return this.serveIterator(scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.genericArrayOps(rdd.collect())), "serve RDD " + rdd.id());
   }

   public Object[] collectAndServeWithJobGroup(final RDD rdd, final String groupId, final String description, final boolean interruptOnCancel) {
      SparkContext sc = rdd.sparkContext();
      sc.setJobGroup(groupId, description, interruptOnCancel);
      return this.serveIterator(scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.genericArrayOps(rdd.collect())), "serve RDD " + rdd.id());
   }

   public Object[] toLocalIteratorAndServe(final RDD rdd, final boolean prefetchPartitions) {
      Function1 handleFunc = (sock) -> {
         $anonfun$toLocalIteratorAndServe$1(rdd, prefetchPartitions, sock);
         return BoxedUnit.UNIT;
      };
      SocketFuncServer server = new SocketFuncServer(this.authHelper(), "serve toLocalIterator", handleFunc);
      return new Object[]{BoxesRunTime.boxToInteger(server.port()), server.secret(), server};
   }

   public boolean toLocalIteratorAndServe$default$2() {
      return false;
   }

   public JavaRDD readRDDFromFile(final JavaSparkContext sc, final String filename, final int parallelism) {
      return JavaRDD$.MODULE$.readRDDFromFile(sc, filename, parallelism);
   }

   public JavaRDD readRDDFromInputStream(final SparkContext sc, final InputStream in, final int parallelism) {
      return JavaRDD$.MODULE$.readRDDFromInputStream(sc, in, parallelism);
   }

   public PythonBroadcast setupBroadcast(final String path) {
      return new PythonBroadcast(path);
   }

   public boolean writeNextElementToStream(final Iterator iter, final DataOutputStream dataOut) {
      if (iter.hasNext()) {
         this.write$1(iter.next(), dataOut);
         return true;
      } else {
         return false;
      }
   }

   public void writeIteratorToStream(final Iterator iter, final DataOutputStream dataOut) {
      while(this.writeNextElementToStream(iter, dataOut)) {
      }

   }

   public JavaRDD sequenceFile(final JavaSparkContext sc, final String path, final String keyClassMaybeNull, final String valueClassMaybeNull, final String keyConverterClass, final String valueConverterClass, final int minSplits, final int batchSize) {
      String keyClass = (String).MODULE$.apply(keyClassMaybeNull).getOrElse(() -> "org.apache.hadoop.io.Text");
      String valueClass = (String).MODULE$.apply(valueClassMaybeNull).getOrElse(() -> "org.apache.hadoop.io.Text");
      Class kc = Utils$.MODULE$.classForName(keyClass, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3());
      Class vc = Utils$.MODULE$.classForName(valueClass, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3());
      RDD rdd = sc.sc().sequenceFile(path, kc, vc, minSplits);
      Broadcast confBroadcasted = sc.sc().broadcast(new SerializableConfiguration(sc.hadoopConfiguration()), scala.reflect.ClassTag..MODULE$.apply(SerializableConfiguration.class));
      RDD converted = this.convertRDD(rdd, keyConverterClass, valueConverterClass, new WritableToJavaConverter(confBroadcasted));
      return JavaRDD$.MODULE$.fromRDD(SerDeUtil$.MODULE$.pairRDDToPython(converted, batchSize), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)));
   }

   public JavaRDD newAPIHadoopFile(final JavaSparkContext sc, final String path, final String inputFormatClass, final String keyClass, final String valueClass, final String keyConverterClass, final String valueConverterClass, final HashMap confAsMap, final int batchSize) {
      Configuration mergedConf = this.getMergedConf(confAsMap, sc.hadoopConfiguration());
      RDD rdd = this.newAPIHadoopRDDFromClassNames(sc, new Some(path), inputFormatClass, keyClass, valueClass, mergedConf);
      Broadcast confBroadcasted = sc.sc().broadcast(new SerializableConfiguration(mergedConf), scala.reflect.ClassTag..MODULE$.apply(SerializableConfiguration.class));
      RDD converted = this.convertRDD(rdd, keyConverterClass, valueConverterClass, new WritableToJavaConverter(confBroadcasted));
      return JavaRDD$.MODULE$.fromRDD(SerDeUtil$.MODULE$.pairRDDToPython(converted, batchSize), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)));
   }

   public JavaRDD newAPIHadoopRDD(final JavaSparkContext sc, final String inputFormatClass, final String keyClass, final String valueClass, final String keyConverterClass, final String valueConverterClass, final HashMap confAsMap, final int batchSize) {
      Configuration conf = this.getMergedConf(confAsMap, sc.hadoopConfiguration());
      RDD rdd = this.newAPIHadoopRDDFromClassNames(sc, scala.None..MODULE$, inputFormatClass, keyClass, valueClass, conf);
      Broadcast confBroadcasted = sc.sc().broadcast(new SerializableConfiguration(conf), scala.reflect.ClassTag..MODULE$.apply(SerializableConfiguration.class));
      RDD converted = this.convertRDD(rdd, keyConverterClass, valueConverterClass, new WritableToJavaConverter(confBroadcasted));
      return JavaRDD$.MODULE$.fromRDD(SerDeUtil$.MODULE$.pairRDDToPython(converted, batchSize), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)));
   }

   private RDD newAPIHadoopRDDFromClassNames(final JavaSparkContext sc, final Option path, final String inputFormatClass, final String keyClass, final String valueClass, final Configuration conf) {
      Class kc = Utils$.MODULE$.classForName(keyClass, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3());
      Class vc = Utils$.MODULE$.classForName(valueClass, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3());
      Class fc = Utils$.MODULE$.classForName(inputFormatClass, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3());
      return path.isDefined() ? sc.sc().newAPIHadoopFile((String)path.get(), fc, kc, vc, conf) : sc.sc().newAPIHadoopRDD(conf, fc, kc, vc);
   }

   private Option newAPIHadoopRDDFromClassNames$default$2() {
      return scala.None..MODULE$;
   }

   public JavaRDD hadoopFile(final JavaSparkContext sc, final String path, final String inputFormatClass, final String keyClass, final String valueClass, final String keyConverterClass, final String valueConverterClass, final HashMap confAsMap, final int batchSize) {
      Configuration mergedConf = this.getMergedConf(confAsMap, sc.hadoopConfiguration());
      RDD rdd = this.hadoopRDDFromClassNames(sc, new Some(path), inputFormatClass, keyClass, valueClass, mergedConf);
      Broadcast confBroadcasted = sc.sc().broadcast(new SerializableConfiguration(mergedConf), scala.reflect.ClassTag..MODULE$.apply(SerializableConfiguration.class));
      RDD converted = this.convertRDD(rdd, keyConverterClass, valueConverterClass, new WritableToJavaConverter(confBroadcasted));
      return JavaRDD$.MODULE$.fromRDD(SerDeUtil$.MODULE$.pairRDDToPython(converted, batchSize), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)));
   }

   public JavaRDD hadoopRDD(final JavaSparkContext sc, final String inputFormatClass, final String keyClass, final String valueClass, final String keyConverterClass, final String valueConverterClass, final HashMap confAsMap, final int batchSize) {
      Configuration conf = this.getMergedConf(confAsMap, sc.hadoopConfiguration());
      RDD rdd = this.hadoopRDDFromClassNames(sc, scala.None..MODULE$, inputFormatClass, keyClass, valueClass, conf);
      Broadcast confBroadcasted = sc.sc().broadcast(new SerializableConfiguration(conf), scala.reflect.ClassTag..MODULE$.apply(SerializableConfiguration.class));
      RDD converted = this.convertRDD(rdd, keyConverterClass, valueConverterClass, new WritableToJavaConverter(confBroadcasted));
      return JavaRDD$.MODULE$.fromRDD(SerDeUtil$.MODULE$.pairRDDToPython(converted, batchSize), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)));
   }

   private RDD hadoopRDDFromClassNames(final JavaSparkContext sc, final Option path, final String inputFormatClass, final String keyClass, final String valueClass, final Configuration conf) {
      Class kc = Utils$.MODULE$.classForName(keyClass, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3());
      Class vc = Utils$.MODULE$.classForName(valueClass, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3());
      Class fc = Utils$.MODULE$.classForName(inputFormatClass, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3());
      return path.isDefined() ? sc.sc().hadoopFile((String)path.get(), fc, kc, vc, sc.sc().hadoopFile$default$5()) : sc.sc().hadoopRDD(new JobConf(conf), fc, kc, vc, sc.sc().hadoopRDD$default$5());
   }

   private Option hadoopRDDFromClassNames$default$2() {
      return scala.None..MODULE$;
   }

   public void writeUTF(final String str, final DataOutputStream dataOut) {
      PythonWorkerUtils$.MODULE$.writeUTF(str, dataOut);
   }

   public Object[] serveIterator(final Iterator items, final String threadName) {
      return this.serveToStream(threadName, (out) -> {
         $anonfun$serveIterator$1(items, out);
         return BoxedUnit.UNIT;
      });
   }

   public Object[] serveToStream(final String threadName, final Function1 writeFunc) {
      return SocketAuthServer$.MODULE$.serveToStream(threadName, this.authHelper(), writeFunc);
   }

   private Configuration getMergedConf(final HashMap confAsMap, final Configuration baseConf) {
      Configuration conf = PythonHadoopUtil$.MODULE$.mapToConf(confAsMap);
      return PythonHadoopUtil$.MODULE$.mergeConfs(baseConf, conf);
   }

   private Tuple2 inferKeyValueTypes(final RDD rdd, final String keyConverterClass, final String valueConverterClass) {
      Tuple2 var7 = (Tuple2)rdd.first();
      if (var7 != null) {
         Object key = var7._1();
         Object value = var7._2();
         Tuple2 var6 = new Tuple2(key, value);
         Object key = var6._1();
         Object value = var6._2();
         Tuple2 var13 = this.getKeyValueConverters(keyConverterClass, valueConverterClass, new JavaToWritableConverter());
         if (var13 != null) {
            Converter kc = (Converter)var13._1();
            Converter vc = (Converter)var13._2();
            Tuple2 var12 = new Tuple2(kc, vc);
            Converter kc = (Converter)var12._1();
            Converter vc = (Converter)var12._2();
            return new Tuple2(kc.convert(key).getClass(), vc.convert(value).getClass());
         } else {
            throw new MatchError(var13);
         }
      } else {
         throw new MatchError(var7);
      }
   }

   private String inferKeyValueTypes$default$2() {
      return null;
   }

   private String inferKeyValueTypes$default$3() {
      return null;
   }

   private Option getKeyValueTypes(final String keyClass, final String valueClass) {
      return .MODULE$.apply(keyClass).flatMap((k) -> .MODULE$.apply(valueClass).map((v) -> new Tuple2(Utils$.MODULE$.classForName(k, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3()), Utils$.MODULE$.classForName(v, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3()))));
   }

   private Tuple2 getKeyValueConverters(final String keyConverterClass, final String valueConverterClass, final Converter defaultConverter) {
      Converter keyConverter = Converter$.MODULE$.getInstance(.MODULE$.apply(keyConverterClass), defaultConverter);
      Converter valueConverter = Converter$.MODULE$.getInstance(.MODULE$.apply(valueConverterClass), defaultConverter);
      return new Tuple2(keyConverter, valueConverter);
   }

   private RDD convertRDD(final RDD rdd, final String keyConverterClass, final String valueConverterClass, final Converter defaultConverter) {
      Tuple2 var7 = this.getKeyValueConverters(keyConverterClass, valueConverterClass, defaultConverter);
      if (var7 != null) {
         Converter kc = (Converter)var7._1();
         Converter vc = (Converter)var7._2();
         Tuple2 var6 = new Tuple2(kc, vc);
         Converter kc = (Converter)var6._1();
         Converter vc = (Converter)var6._2();
         return PythonHadoopUtil$.MODULE$.convertRDD(rdd, kc, vc);
      } else {
         throw new MatchError(var7);
      }
   }

   public void saveAsSequenceFile(final JavaRDD pyRDD, final boolean batchSerialized, final String path, final String compressionCodecClass) {
      this.saveAsHadoopFile(pyRDD, batchSerialized, path, "org.apache.hadoop.mapred.SequenceFileOutputFormat", (String)null, (String)null, (String)null, (String)null, new HashMap(), compressionCodecClass);
   }

   public void saveAsHadoopFile(final JavaRDD pyRDD, final boolean batchSerialized, final String path, final String outputFormatClass, final String keyClass, final String valueClass, final String keyConverterClass, final String valueConverterClass, final HashMap confAsMap, final String compressionCodecClass) {
      RDD rdd = SerDeUtil$.MODULE$.pythonToPairRDD(JavaRDD$.MODULE$.toRDD(pyRDD), batchSerialized);
      Tuple2 var14 = (Tuple2)this.getKeyValueTypes(keyClass, valueClass).getOrElse(() -> MODULE$.inferKeyValueTypes(rdd, keyConverterClass, valueConverterClass));
      if (var14 != null) {
         Class kc = (Class)var14._1();
         Class vc = (Class)var14._2();
         Tuple2 var13 = new Tuple2(kc, vc);
         Class kc = (Class)var13._1();
         Class vc = (Class)var13._2();
         Configuration mergedConf = this.getMergedConf(confAsMap, pyRDD.context().hadoopConfiguration());
         Option codec = .MODULE$.apply(compressionCodecClass).map((x$10) -> Utils$.MODULE$.classForName(x$10, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3()));
         RDD converted = this.convertRDD(rdd, keyConverterClass, valueConverterClass, new JavaToWritableConverter());
         Class fc = Utils$.MODULE$.classForName(outputFormatClass, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3());
         ClassTag x$2 = scala.reflect.ClassTag..MODULE$.Any();
         ClassTag x$3 = scala.reflect.ClassTag..MODULE$.Any();
         Null var26 = RDD$.MODULE$.rddToPairRDDFunctions$default$4(converted);
         RDD$.MODULE$.rddToPairRDDFunctions(converted, x$2, x$3, (Ordering)null).saveAsHadoopFile(path, kc, vc, fc, new JobConf(mergedConf), codec);
      } else {
         throw new MatchError(var14);
      }
   }

   public void saveAsNewAPIHadoopFile(final JavaRDD pyRDD, final boolean batchSerialized, final String path, final String outputFormatClass, final String keyClass, final String valueClass, final String keyConverterClass, final String valueConverterClass, final HashMap confAsMap) {
      RDD rdd = SerDeUtil$.MODULE$.pythonToPairRDD(JavaRDD$.MODULE$.toRDD(pyRDD), batchSerialized);
      Tuple2 var13 = (Tuple2)this.getKeyValueTypes(keyClass, valueClass).getOrElse(() -> MODULE$.inferKeyValueTypes(rdd, keyConverterClass, valueConverterClass));
      if (var13 != null) {
         Class kc = (Class)var13._1();
         Class vc = (Class)var13._2();
         Tuple2 var12 = new Tuple2(kc, vc);
         Class kc = (Class)var12._1();
         Class vc = (Class)var12._2();
         Configuration mergedConf = this.getMergedConf(confAsMap, pyRDD.context().hadoopConfiguration());
         RDD converted = this.convertRDD(rdd, keyConverterClass, valueConverterClass, new JavaToWritableConverter());
         Class fc = Utils$.MODULE$.classForName(outputFormatClass, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3());
         ClassTag x$2 = scala.reflect.ClassTag..MODULE$.Any();
         ClassTag x$3 = scala.reflect.ClassTag..MODULE$.Any();
         Null var24 = RDD$.MODULE$.rddToPairRDDFunctions$default$4(converted);
         RDD$.MODULE$.rddToPairRDDFunctions(converted, x$2, x$3, (Ordering)null).saveAsNewAPIHadoopFile(path, kc, vc, fc, mergedConf);
      } else {
         throw new MatchError(var13);
      }
   }

   public void saveAsHadoopDataset(final JavaRDD pyRDD, final boolean batchSerialized, final HashMap confAsMap, final String keyConverterClass, final String valueConverterClass, final boolean useNewAPI) {
      Configuration conf = this.getMergedConf(confAsMap, pyRDD.context().hadoopConfiguration());
      RDD converted = this.convertRDD(SerDeUtil$.MODULE$.pythonToPairRDD(JavaRDD$.MODULE$.toRDD(pyRDD), batchSerialized), keyConverterClass, valueConverterClass, new JavaToWritableConverter());
      if (useNewAPI) {
         ClassTag x$2 = scala.reflect.ClassTag..MODULE$.Any();
         ClassTag x$3 = scala.reflect.ClassTag..MODULE$.Any();
         Null x$4 = RDD$.MODULE$.rddToPairRDDFunctions$default$4(converted);
         RDD$.MODULE$.rddToPairRDDFunctions(converted, x$2, x$3, (Ordering)null).saveAsNewAPIHadoopDataset(conf);
      } else {
         ClassTag x$6 = scala.reflect.ClassTag..MODULE$.Any();
         ClassTag x$7 = scala.reflect.ClassTag..MODULE$.Any();
         Null x$8 = RDD$.MODULE$.rddToPairRDDFunctions$default$4(converted);
         RDD$.MODULE$.rddToPairRDDFunctions(converted, x$6, x$7, (Ordering)null).saveAsHadoopDataset(new JobConf(conf));
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PythonRDD$.class);
   }

   // $FF: synthetic method
   public static final void $anonfun$toLocalIteratorAndServe$5(final ObjectRef result$1, final int x$5, final Object[] res) {
      result$1.elem = res;
   }

   // $FF: synthetic method
   public static final SimpleFutureAction $anonfun$toLocalIteratorAndServe$3(final RDD rdd$1, final int i) {
      ObjectRef result = ObjectRef.create((Object)null);
      return rdd$1.sparkContext().submitJob(rdd$1, (iter) -> iter.toArray(scala.reflect.ClassTag..MODULE$.Any()), (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{i})), (x$5, res) -> {
         $anonfun$toLocalIteratorAndServe$5(result, BoxesRunTime.unboxToInt(x$5), res);
         return BoxedUnit.UNIT;
      }, () -> result.elem);
   }

   // $FF: synthetic method
   public static final void $anonfun$toLocalIteratorAndServe$1(final RDD rdd$1, final boolean prefetchPartitions$1, final Socket sock) {
      DataOutputStream out = new DataOutputStream(sock.getOutputStream());
      DataInputStream in = new DataInputStream(sock.getInputStream());
      Utils$.MODULE$.tryWithSafeFinallyAndFailureCallbacks((JFunction0.mcV.sp)() -> {
         Iterator collectPartitionIter = scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps(rdd$1.partitions())).iterator().map((i) -> $anonfun$toLocalIteratorAndServe$3(rdd$1, BoxesRunTime.unboxToInt(i)));
         BufferedIterator prefetchIter = collectPartitionIter.buffered();
         boolean complete = false;

         while(!complete) {
            if (in.readInt() == 0) {
               complete = true;
            } else if (prefetchIter.hasNext()) {
               SimpleFutureAction partitionFuture = (SimpleFutureAction)prefetchIter.next();
               if (prefetchPartitions$1) {
                  prefetchIter.headOption();
               } else {
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }

               Object[] partitionArray = ThreadUtils$.MODULE$.awaitResult((Awaitable)partitionFuture, scala.concurrent.duration.Duration..MODULE$.Inf());
               out.writeInt(1);
               MODULE$.writeIteratorToStream(scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.genericArrayOps(partitionArray)), out);
               out.writeInt(SpecialLengths$.MODULE$.END_OF_DATA_SECTION());
               out.flush();
            } else {
               out.writeInt(0);
               complete = true;
            }
         }

      }, (JFunction0.mcV.sp)() -> out.writeInt(-1), (JFunction0.mcV.sp)() -> {
         out.close();
         in.close();
      });
   }

   private final void write$1(final Object obj, final DataOutputStream dataOut$1) {
      while(true) {
         if (obj == null) {
            dataOut$1.writeInt(SpecialLengths$.MODULE$.NULL());
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else if (obj instanceof byte[]) {
            byte[] var6 = (byte[])obj;
            dataOut$1.writeInt(var6.length);
            dataOut$1.write(var6);
            BoxedUnit var12 = BoxedUnit.UNIT;
         } else {
            if (!(obj instanceof String)) {
               if (obj instanceof PortableDataStream) {
                  PortableDataStream var8 = (PortableDataStream)obj;
                  obj = var8.toArray();
                  continue;
               }

               if (obj instanceof Tuple2) {
                  Tuple2 var9 = (Tuple2)obj;
                  Object key = var9._1();
                  Object value = var9._2();
                  this.write$1(key, dataOut$1);
                  obj = value;
                  continue;
               }

               throw new SparkException("Unexpected element type " + obj.getClass());
            }

            String var7 = (String)obj;
            this.writeUTF(var7, dataOut$1);
            BoxedUnit var13 = BoxedUnit.UNIT;
         }

         BoxedUnit var14 = BoxedUnit.UNIT;
         return;
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$serveIterator$1(final Iterator items$1, final OutputStream out) {
      MODULE$.writeIteratorToStream(items$1, new DataOutputStream(out));
   }

   private PythonRDD$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
