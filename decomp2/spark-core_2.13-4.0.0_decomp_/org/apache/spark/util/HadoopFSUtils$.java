package org.apache.spark.util;

import java.io.FileNotFoundException;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.fs.viewfs.ViewFileSystem;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkContext$;
import org.apache.spark.SparkUnsupportedOperationException;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.metrics.source.HiveCatalogMetrics$;
import org.apache.spark.rdd.RDD;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.BufferedIterator;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StringOps.;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.util.matching.Regex;

public final class HadoopFSUtils$ implements Logging {
   public static final HadoopFSUtils$ MODULE$ = new HadoopFSUtils$();
   private static final Regex underscore;
   private static final Regex underscoreEnd;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      underscore = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("/_[^=/]*/"));
      underscoreEnd = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("/_[^=/]*$"));
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

   public Seq parallelListLeafFiles(final SparkContext sc, final Seq paths, final Configuration hadoopConf, final PathFilter filter, final boolean ignoreMissingFiles, final boolean ignoreLocality, final int parallelismThreshold, final int parallelismMax) {
      return this.parallelListLeafFilesInternal(sc, paths, hadoopConf, filter, true, ignoreMissingFiles, ignoreLocality, parallelismThreshold, parallelismMax);
   }

   public Seq listFiles(final Path path, final Configuration hadoopConf, final PathFilter filter) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Listing ", " with listFiles API"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, path)})))));

      scala.collection.immutable..colon.colon var10000;
      try {
         int prefixLength = path.toString().length();
         RemoteIterator remoteIter = path.getFileSystem(hadoopConf).listFiles(path, true);
         LocatedFileStatus[] statues = (LocatedFileStatus[])(new Iterator(remoteIter) {
            private final RemoteIterator remoteIter$1;

            /** @deprecated */
            public final boolean hasDefiniteSize() {
               return Iterator.hasDefiniteSize$(this);
            }

            public final Iterator iterator() {
               return Iterator.iterator$(this);
            }

            public Option nextOption() {
               return Iterator.nextOption$(this);
            }

            public boolean contains(final Object elem) {
               return Iterator.contains$(this, elem);
            }

            public BufferedIterator buffered() {
               return Iterator.buffered$(this);
            }

            public Iterator padTo(final int len, final Object elem) {
               return Iterator.padTo$(this, len, elem);
            }

            public Tuple2 partition(final Function1 p) {
               return Iterator.partition$(this, p);
            }

            public Iterator.GroupedIterator grouped(final int size) {
               return Iterator.grouped$(this, size);
            }

            public Iterator.GroupedIterator sliding(final int size, final int step) {
               return Iterator.sliding$(this, size, step);
            }

            public int sliding$default$2() {
               return Iterator.sliding$default$2$(this);
            }

            public Iterator scanLeft(final Object z, final Function2 op) {
               return Iterator.scanLeft$(this, z, op);
            }

            /** @deprecated */
            public Iterator scanRight(final Object z, final Function2 op) {
               return Iterator.scanRight$(this, z, op);
            }

            public int indexWhere(final Function1 p, final int from) {
               return Iterator.indexWhere$(this, p, from);
            }

            public int indexWhere$default$2() {
               return Iterator.indexWhere$default$2$(this);
            }

            public int indexOf(final Object elem) {
               return Iterator.indexOf$(this, elem);
            }

            public int indexOf(final Object elem, final int from) {
               return Iterator.indexOf$(this, elem, from);
            }

            public final int length() {
               return Iterator.length$(this);
            }

            public boolean isEmpty() {
               return Iterator.isEmpty$(this);
            }

            public Iterator filter(final Function1 p) {
               return Iterator.filter$(this, p);
            }

            public Iterator filterNot(final Function1 p) {
               return Iterator.filterNot$(this, p);
            }

            public Iterator filterImpl(final Function1 p, final boolean isFlipped) {
               return Iterator.filterImpl$(this, p, isFlipped);
            }

            public Iterator withFilter(final Function1 p) {
               return Iterator.withFilter$(this, p);
            }

            public Iterator collect(final PartialFunction pf) {
               return Iterator.collect$(this, pf);
            }

            public Iterator distinct() {
               return Iterator.distinct$(this);
            }

            public Iterator distinctBy(final Function1 f) {
               return Iterator.distinctBy$(this, f);
            }

            public Iterator map(final Function1 f) {
               return Iterator.map$(this, f);
            }

            public Iterator flatMap(final Function1 f) {
               return Iterator.flatMap$(this, f);
            }

            public Iterator flatten(final Function1 ev) {
               return Iterator.flatten$(this, ev);
            }

            public Iterator concat(final Function0 xs) {
               return Iterator.concat$(this, xs);
            }

            public final Iterator $plus$plus(final Function0 xs) {
               return Iterator.$plus$plus$(this, xs);
            }

            public Iterator take(final int n) {
               return Iterator.take$(this, n);
            }

            public Iterator takeWhile(final Function1 p) {
               return Iterator.takeWhile$(this, p);
            }

            public Iterator drop(final int n) {
               return Iterator.drop$(this, n);
            }

            public Iterator dropWhile(final Function1 p) {
               return Iterator.dropWhile$(this, p);
            }

            public Tuple2 span(final Function1 p) {
               return Iterator.span$(this, p);
            }

            public Iterator slice(final int from, final int until) {
               return Iterator.slice$(this, from, until);
            }

            public Iterator sliceIterator(final int from, final int until) {
               return Iterator.sliceIterator$(this, from, until);
            }

            public Iterator zip(final IterableOnce that) {
               return Iterator.zip$(this, that);
            }

            public Iterator zipAll(final IterableOnce that, final Object thisElem, final Object thatElem) {
               return Iterator.zipAll$(this, that, thisElem, thatElem);
            }

            public Iterator zipWithIndex() {
               return Iterator.zipWithIndex$(this);
            }

            public boolean sameElements(final IterableOnce that) {
               return Iterator.sameElements$(this, that);
            }

            public Tuple2 duplicate() {
               return Iterator.duplicate$(this);
            }

            public Iterator patch(final int from, final Iterator patchElems, final int replaced) {
               return Iterator.patch$(this, from, patchElems, replaced);
            }

            public Iterator tapEach(final Function1 f) {
               return Iterator.tapEach$(this, f);
            }

            public String toString() {
               return Iterator.toString$(this);
            }

            /** @deprecated */
            public Iterator seq() {
               return Iterator.seq$(this);
            }

            public Tuple2 splitAt(final int n) {
               return IterableOnceOps.splitAt$(this, n);
            }

            public boolean isTraversableAgain() {
               return IterableOnceOps.isTraversableAgain$(this);
            }

            public void foreach(final Function1 f) {
               IterableOnceOps.foreach$(this, f);
            }

            public boolean forall(final Function1 p) {
               return IterableOnceOps.forall$(this, p);
            }

            public boolean exists(final Function1 p) {
               return IterableOnceOps.exists$(this, p);
            }

            public int count(final Function1 p) {
               return IterableOnceOps.count$(this, p);
            }

            public Option find(final Function1 p) {
               return IterableOnceOps.find$(this, p);
            }

            public Object foldLeft(final Object z, final Function2 op) {
               return IterableOnceOps.foldLeft$(this, z, op);
            }

            public Object foldRight(final Object z, final Function2 op) {
               return IterableOnceOps.foldRight$(this, z, op);
            }

            /** @deprecated */
            public final Object $div$colon(final Object z, final Function2 op) {
               return IterableOnceOps.$div$colon$(this, z, op);
            }

            /** @deprecated */
            public final Object $colon$bslash(final Object z, final Function2 op) {
               return IterableOnceOps.$colon$bslash$(this, z, op);
            }

            public Object fold(final Object z, final Function2 op) {
               return IterableOnceOps.fold$(this, z, op);
            }

            public Object reduce(final Function2 op) {
               return IterableOnceOps.reduce$(this, op);
            }

            public Option reduceOption(final Function2 op) {
               return IterableOnceOps.reduceOption$(this, op);
            }

            public Object reduceLeft(final Function2 op) {
               return IterableOnceOps.reduceLeft$(this, op);
            }

            public Object reduceRight(final Function2 op) {
               return IterableOnceOps.reduceRight$(this, op);
            }

            public Option reduceLeftOption(final Function2 op) {
               return IterableOnceOps.reduceLeftOption$(this, op);
            }

            public Option reduceRightOption(final Function2 op) {
               return IterableOnceOps.reduceRightOption$(this, op);
            }

            public boolean nonEmpty() {
               return IterableOnceOps.nonEmpty$(this);
            }

            public int size() {
               return IterableOnceOps.size$(this);
            }

            /** @deprecated */
            public final void copyToBuffer(final Buffer dest) {
               IterableOnceOps.copyToBuffer$(this, dest);
            }

            public int copyToArray(final Object xs) {
               return IterableOnceOps.copyToArray$(this, xs);
            }

            public int copyToArray(final Object xs, final int start) {
               return IterableOnceOps.copyToArray$(this, xs, start);
            }

            public int copyToArray(final Object xs, final int start, final int len) {
               return IterableOnceOps.copyToArray$(this, xs, start, len);
            }

            public Object sum(final Numeric num) {
               return IterableOnceOps.sum$(this, num);
            }

            public Object product(final Numeric num) {
               return IterableOnceOps.product$(this, num);
            }

            public Object min(final Ordering ord) {
               return IterableOnceOps.min$(this, ord);
            }

            public Option minOption(final Ordering ord) {
               return IterableOnceOps.minOption$(this, ord);
            }

            public Object max(final Ordering ord) {
               return IterableOnceOps.max$(this, ord);
            }

            public Option maxOption(final Ordering ord) {
               return IterableOnceOps.maxOption$(this, ord);
            }

            public Object maxBy(final Function1 f, final Ordering ord) {
               return IterableOnceOps.maxBy$(this, f, ord);
            }

            public Option maxByOption(final Function1 f, final Ordering ord) {
               return IterableOnceOps.maxByOption$(this, f, ord);
            }

            public Object minBy(final Function1 f, final Ordering ord) {
               return IterableOnceOps.minBy$(this, f, ord);
            }

            public Option minByOption(final Function1 f, final Ordering ord) {
               return IterableOnceOps.minByOption$(this, f, ord);
            }

            public Option collectFirst(final PartialFunction pf) {
               return IterableOnceOps.collectFirst$(this, pf);
            }

            /** @deprecated */
            public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
               return IterableOnceOps.aggregate$(this, z, seqop, combop);
            }

            public boolean corresponds(final IterableOnce that, final Function2 p) {
               return IterableOnceOps.corresponds$(this, that, p);
            }

            public final String mkString(final String start, final String sep, final String end) {
               return IterableOnceOps.mkString$(this, start, sep, end);
            }

            public final String mkString(final String sep) {
               return IterableOnceOps.mkString$(this, sep);
            }

            public final String mkString() {
               return IterableOnceOps.mkString$(this);
            }

            public StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
               return IterableOnceOps.addString$(this, b, start, sep, end);
            }

            public final StringBuilder addString(final StringBuilder b, final String sep) {
               return IterableOnceOps.addString$(this, b, sep);
            }

            public final StringBuilder addString(final StringBuilder b) {
               return IterableOnceOps.addString$(this, b);
            }

            public Object to(final Factory factory) {
               return IterableOnceOps.to$(this, factory);
            }

            /** @deprecated */
            public final Iterator toIterator() {
               return IterableOnceOps.toIterator$(this);
            }

            public List toList() {
               return IterableOnceOps.toList$(this);
            }

            public Vector toVector() {
               return IterableOnceOps.toVector$(this);
            }

            public scala.collection.immutable.Map toMap(final scala..less.colon.less ev) {
               return IterableOnceOps.toMap$(this, ev);
            }

            public Set toSet() {
               return IterableOnceOps.toSet$(this);
            }

            public Seq toSeq() {
               return IterableOnceOps.toSeq$(this);
            }

            public IndexedSeq toIndexedSeq() {
               return IterableOnceOps.toIndexedSeq$(this);
            }

            /** @deprecated */
            public final Stream toStream() {
               return IterableOnceOps.toStream$(this);
            }

            public final Buffer toBuffer() {
               return IterableOnceOps.toBuffer$(this);
            }

            public Object toArray(final ClassTag evidence$2) {
               return IterableOnceOps.toArray$(this, evidence$2);
            }

            public Iterable reversed() {
               return IterableOnceOps.reversed$(this);
            }

            public Stepper stepper(final StepperShape shape) {
               return IterableOnce.stepper$(this, shape);
            }

            public int knownSize() {
               return IterableOnce.knownSize$(this);
            }

            public LocatedFileStatus next() {
               return (LocatedFileStatus)this.remoteIter$1.next();
            }

            public boolean hasNext() {
               return this.remoteIter$1.hasNext();
            }

            public {
               this.remoteIter$1 = remoteIter$1;
               IterableOnce.$init$(this);
               IterableOnceOps.$init$(this);
               Iterator.$init$(this);
            }
         }).filterNot((status) -> BoxesRunTime.boxToBoolean($anonfun$listFiles$2(prefixLength, status))).filter((f) -> BoxesRunTime.boxToBoolean($anonfun$listFiles$3(filter, f))).toArray(scala.reflect.ClassTag..MODULE$.apply(LocatedFileStatus.class));
         var10000 = new scala.collection.immutable..colon.colon(new Tuple2(path, org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(statues).toImmutableArraySeq()), scala.collection.immutable.Nil..MODULE$);
      } catch (FileNotFoundException var7) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"The root directory ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, path)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"was not found. Was it deleted very recently?"})))).log(scala.collection.immutable.Nil..MODULE$))));
         var10000 = new scala.collection.immutable..colon.colon(new Tuple2(path, scala.package..MODULE$.Seq().empty()), scala.collection.immutable.Nil..MODULE$);
      }

      return var10000;
   }

   private Seq parallelListLeafFilesInternal(final SparkContext sc, final Seq paths, final Configuration hadoopConf, final PathFilter filter, final boolean isRootLevel, final boolean ignoreMissingFiles, final boolean ignoreLocality, final int parallelismThreshold, final int parallelismMax) {
      if (paths.size() <= parallelismThreshold) {
         return (Seq)paths.map((path) -> {
            Seq leafFiles = MODULE$.listLeafFiles(path, hadoopConf, filter, new Some(sc), ignoreMissingFiles, ignoreLocality, isRootLevel, parallelismThreshold, parallelismMax);
            return new Tuple2(path, leafFiles);
         });
      } else {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Listing leaf files and directories in parallel under"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " paths."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_PATHS..MODULE$, BoxesRunTime.boxToInteger(paths.length()))})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" The first several paths are: ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATHS..MODULE$, ((IterableOnceOps)paths.take(10)).mkString(", "))}))))));
         HiveCatalogMetrics$.MODULE$.incrementParallelListingJobCount(1);
         SerializableConfiguration serializableConfiguration = new SerializableConfiguration(hadoopConf);
         int numParallelism = Math.min(paths.size(), parallelismMax);
         String previousJobDescription = sc.getLocalProperty(SparkContext$.MODULE$.SPARK_JOB_DESCRIPTION());

         ArraySeq var22;
         try {
            int var14 = paths.size();
            String var10000;
            switch (var14) {
               case 0 -> var10000 = "Listing leaf files and directories 0 paths";
               case 1 -> var10000 = "Listing leaf files and directories for 1 path:<br/>" + paths.apply(0);
               default -> var10000 = "Listing leaf files and directories for " + var14 + " paths:<br/>" + paths.apply(0) + ", ...";
            }

            String description = var10000;
            sc.setJobDescription(description);
            ArrayImplicits var21 = org.apache.spark.util.ArrayImplicits..MODULE$;
            RDD qual$1 = sc.parallelize(paths, numParallelism, scala.reflect.ClassTag..MODULE$.apply(Path.class));
            Function1 x$1 = (pathsEachPartition) -> {
               Configuration hadoopConf = serializableConfiguration.value();
               return pathsEachPartition.map((path) -> {
                  Seq leafFiles = MODULE$.listLeafFiles(path, hadoopConf, filter, scala.None..MODULE$, ignoreMissingFiles, ignoreLocality, isRootLevel, Integer.MAX_VALUE, 0);
                  return new Tuple2(path, leafFiles);
               });
            };
            boolean x$2 = qual$1.mapPartitions$default$2();
            var22 = var21.SparkArrayOps(qual$1.mapPartitions(x$1, x$2, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)).collect()).toImmutableArraySeq();
         } finally {
            sc.setJobDescription(previousJobDescription);
         }

         return var22;
      }
   }

   private Seq listLeafFiles(final Path path, final Configuration hadoopConf, final PathFilter filter, final Option contextOpt, final boolean ignoreMissingFiles, final boolean ignoreLocality, final boolean isRootPath, final int parallelismThreshold, final int parallelismMax) {
      this.logTrace((Function0)(() -> "Listing " + path));
      FileSystem fs = path.getFileSystem(hadoopConf);

      FileStatus[] var10000;
      try {
         if ((fs instanceof DistributedFileSystem ? true : fs instanceof ViewFileSystem) && !ignoreLocality) {
            RemoteIterator remoteIter = fs.listLocatedStatus(path);
            var10000 = (FileStatus[])(new Iterator(remoteIter) {
               private final RemoteIterator remoteIter$2;

               /** @deprecated */
               public final boolean hasDefiniteSize() {
                  return Iterator.hasDefiniteSize$(this);
               }

               public final Iterator iterator() {
                  return Iterator.iterator$(this);
               }

               public Option nextOption() {
                  return Iterator.nextOption$(this);
               }

               public boolean contains(final Object elem) {
                  return Iterator.contains$(this, elem);
               }

               public BufferedIterator buffered() {
                  return Iterator.buffered$(this);
               }

               public Iterator padTo(final int len, final Object elem) {
                  return Iterator.padTo$(this, len, elem);
               }

               public Tuple2 partition(final Function1 p) {
                  return Iterator.partition$(this, p);
               }

               public Iterator.GroupedIterator grouped(final int size) {
                  return Iterator.grouped$(this, size);
               }

               public Iterator.GroupedIterator sliding(final int size, final int step) {
                  return Iterator.sliding$(this, size, step);
               }

               public int sliding$default$2() {
                  return Iterator.sliding$default$2$(this);
               }

               public Iterator scanLeft(final Object z, final Function2 op) {
                  return Iterator.scanLeft$(this, z, op);
               }

               /** @deprecated */
               public Iterator scanRight(final Object z, final Function2 op) {
                  return Iterator.scanRight$(this, z, op);
               }

               public int indexWhere(final Function1 p, final int from) {
                  return Iterator.indexWhere$(this, p, from);
               }

               public int indexWhere$default$2() {
                  return Iterator.indexWhere$default$2$(this);
               }

               public int indexOf(final Object elem) {
                  return Iterator.indexOf$(this, elem);
               }

               public int indexOf(final Object elem, final int from) {
                  return Iterator.indexOf$(this, elem, from);
               }

               public final int length() {
                  return Iterator.length$(this);
               }

               public boolean isEmpty() {
                  return Iterator.isEmpty$(this);
               }

               public Iterator filter(final Function1 p) {
                  return Iterator.filter$(this, p);
               }

               public Iterator filterNot(final Function1 p) {
                  return Iterator.filterNot$(this, p);
               }

               public Iterator filterImpl(final Function1 p, final boolean isFlipped) {
                  return Iterator.filterImpl$(this, p, isFlipped);
               }

               public Iterator withFilter(final Function1 p) {
                  return Iterator.withFilter$(this, p);
               }

               public Iterator collect(final PartialFunction pf) {
                  return Iterator.collect$(this, pf);
               }

               public Iterator distinct() {
                  return Iterator.distinct$(this);
               }

               public Iterator distinctBy(final Function1 f) {
                  return Iterator.distinctBy$(this, f);
               }

               public Iterator map(final Function1 f) {
                  return Iterator.map$(this, f);
               }

               public Iterator flatMap(final Function1 f) {
                  return Iterator.flatMap$(this, f);
               }

               public Iterator flatten(final Function1 ev) {
                  return Iterator.flatten$(this, ev);
               }

               public Iterator concat(final Function0 xs) {
                  return Iterator.concat$(this, xs);
               }

               public final Iterator $plus$plus(final Function0 xs) {
                  return Iterator.$plus$plus$(this, xs);
               }

               public Iterator take(final int n) {
                  return Iterator.take$(this, n);
               }

               public Iterator takeWhile(final Function1 p) {
                  return Iterator.takeWhile$(this, p);
               }

               public Iterator drop(final int n) {
                  return Iterator.drop$(this, n);
               }

               public Iterator dropWhile(final Function1 p) {
                  return Iterator.dropWhile$(this, p);
               }

               public Tuple2 span(final Function1 p) {
                  return Iterator.span$(this, p);
               }

               public Iterator slice(final int from, final int until) {
                  return Iterator.slice$(this, from, until);
               }

               public Iterator sliceIterator(final int from, final int until) {
                  return Iterator.sliceIterator$(this, from, until);
               }

               public Iterator zip(final IterableOnce that) {
                  return Iterator.zip$(this, that);
               }

               public Iterator zipAll(final IterableOnce that, final Object thisElem, final Object thatElem) {
                  return Iterator.zipAll$(this, that, thisElem, thatElem);
               }

               public Iterator zipWithIndex() {
                  return Iterator.zipWithIndex$(this);
               }

               public boolean sameElements(final IterableOnce that) {
                  return Iterator.sameElements$(this, that);
               }

               public Tuple2 duplicate() {
                  return Iterator.duplicate$(this);
               }

               public Iterator patch(final int from, final Iterator patchElems, final int replaced) {
                  return Iterator.patch$(this, from, patchElems, replaced);
               }

               public Iterator tapEach(final Function1 f) {
                  return Iterator.tapEach$(this, f);
               }

               public String toString() {
                  return Iterator.toString$(this);
               }

               /** @deprecated */
               public Iterator seq() {
                  return Iterator.seq$(this);
               }

               public Tuple2 splitAt(final int n) {
                  return IterableOnceOps.splitAt$(this, n);
               }

               public boolean isTraversableAgain() {
                  return IterableOnceOps.isTraversableAgain$(this);
               }

               public void foreach(final Function1 f) {
                  IterableOnceOps.foreach$(this, f);
               }

               public boolean forall(final Function1 p) {
                  return IterableOnceOps.forall$(this, p);
               }

               public boolean exists(final Function1 p) {
                  return IterableOnceOps.exists$(this, p);
               }

               public int count(final Function1 p) {
                  return IterableOnceOps.count$(this, p);
               }

               public Option find(final Function1 p) {
                  return IterableOnceOps.find$(this, p);
               }

               public Object foldLeft(final Object z, final Function2 op) {
                  return IterableOnceOps.foldLeft$(this, z, op);
               }

               public Object foldRight(final Object z, final Function2 op) {
                  return IterableOnceOps.foldRight$(this, z, op);
               }

               /** @deprecated */
               public final Object $div$colon(final Object z, final Function2 op) {
                  return IterableOnceOps.$div$colon$(this, z, op);
               }

               /** @deprecated */
               public final Object $colon$bslash(final Object z, final Function2 op) {
                  return IterableOnceOps.$colon$bslash$(this, z, op);
               }

               public Object fold(final Object z, final Function2 op) {
                  return IterableOnceOps.fold$(this, z, op);
               }

               public Object reduce(final Function2 op) {
                  return IterableOnceOps.reduce$(this, op);
               }

               public Option reduceOption(final Function2 op) {
                  return IterableOnceOps.reduceOption$(this, op);
               }

               public Object reduceLeft(final Function2 op) {
                  return IterableOnceOps.reduceLeft$(this, op);
               }

               public Object reduceRight(final Function2 op) {
                  return IterableOnceOps.reduceRight$(this, op);
               }

               public Option reduceLeftOption(final Function2 op) {
                  return IterableOnceOps.reduceLeftOption$(this, op);
               }

               public Option reduceRightOption(final Function2 op) {
                  return IterableOnceOps.reduceRightOption$(this, op);
               }

               public boolean nonEmpty() {
                  return IterableOnceOps.nonEmpty$(this);
               }

               public int size() {
                  return IterableOnceOps.size$(this);
               }

               /** @deprecated */
               public final void copyToBuffer(final Buffer dest) {
                  IterableOnceOps.copyToBuffer$(this, dest);
               }

               public int copyToArray(final Object xs) {
                  return IterableOnceOps.copyToArray$(this, xs);
               }

               public int copyToArray(final Object xs, final int start) {
                  return IterableOnceOps.copyToArray$(this, xs, start);
               }

               public int copyToArray(final Object xs, final int start, final int len) {
                  return IterableOnceOps.copyToArray$(this, xs, start, len);
               }

               public Object sum(final Numeric num) {
                  return IterableOnceOps.sum$(this, num);
               }

               public Object product(final Numeric num) {
                  return IterableOnceOps.product$(this, num);
               }

               public Object min(final Ordering ord) {
                  return IterableOnceOps.min$(this, ord);
               }

               public Option minOption(final Ordering ord) {
                  return IterableOnceOps.minOption$(this, ord);
               }

               public Object max(final Ordering ord) {
                  return IterableOnceOps.max$(this, ord);
               }

               public Option maxOption(final Ordering ord) {
                  return IterableOnceOps.maxOption$(this, ord);
               }

               public Object maxBy(final Function1 f, final Ordering ord) {
                  return IterableOnceOps.maxBy$(this, f, ord);
               }

               public Option maxByOption(final Function1 f, final Ordering ord) {
                  return IterableOnceOps.maxByOption$(this, f, ord);
               }

               public Object minBy(final Function1 f, final Ordering ord) {
                  return IterableOnceOps.minBy$(this, f, ord);
               }

               public Option minByOption(final Function1 f, final Ordering ord) {
                  return IterableOnceOps.minByOption$(this, f, ord);
               }

               public Option collectFirst(final PartialFunction pf) {
                  return IterableOnceOps.collectFirst$(this, pf);
               }

               /** @deprecated */
               public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
                  return IterableOnceOps.aggregate$(this, z, seqop, combop);
               }

               public boolean corresponds(final IterableOnce that, final Function2 p) {
                  return IterableOnceOps.corresponds$(this, that, p);
               }

               public final String mkString(final String start, final String sep, final String end) {
                  return IterableOnceOps.mkString$(this, start, sep, end);
               }

               public final String mkString(final String sep) {
                  return IterableOnceOps.mkString$(this, sep);
               }

               public final String mkString() {
                  return IterableOnceOps.mkString$(this);
               }

               public StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
                  return IterableOnceOps.addString$(this, b, start, sep, end);
               }

               public final StringBuilder addString(final StringBuilder b, final String sep) {
                  return IterableOnceOps.addString$(this, b, sep);
               }

               public final StringBuilder addString(final StringBuilder b) {
                  return IterableOnceOps.addString$(this, b);
               }

               public Object to(final Factory factory) {
                  return IterableOnceOps.to$(this, factory);
               }

               /** @deprecated */
               public final Iterator toIterator() {
                  return IterableOnceOps.toIterator$(this);
               }

               public List toList() {
                  return IterableOnceOps.toList$(this);
               }

               public Vector toVector() {
                  return IterableOnceOps.toVector$(this);
               }

               public scala.collection.immutable.Map toMap(final scala..less.colon.less ev) {
                  return IterableOnceOps.toMap$(this, ev);
               }

               public Set toSet() {
                  return IterableOnceOps.toSet$(this);
               }

               public Seq toSeq() {
                  return IterableOnceOps.toSeq$(this);
               }

               public IndexedSeq toIndexedSeq() {
                  return IterableOnceOps.toIndexedSeq$(this);
               }

               /** @deprecated */
               public final Stream toStream() {
                  return IterableOnceOps.toStream$(this);
               }

               public final Buffer toBuffer() {
                  return IterableOnceOps.toBuffer$(this);
               }

               public Object toArray(final ClassTag evidence$2) {
                  return IterableOnceOps.toArray$(this, evidence$2);
               }

               public Iterable reversed() {
                  return IterableOnceOps.reversed$(this);
               }

               public Stepper stepper(final StepperShape shape) {
                  return IterableOnce.stepper$(this, shape);
               }

               public int knownSize() {
                  return IterableOnce.knownSize$(this);
               }

               public LocatedFileStatus next() {
                  return (LocatedFileStatus)this.remoteIter$2.next();
               }

               public boolean hasNext() {
                  return this.remoteIter$2.hasNext();
               }

               public {
                  this.remoteIter$2 = remoteIter$2;
                  IterableOnce.$init$(this);
                  IterableOnceOps.$init$(this);
                  Iterator.$init$(this);
               }
            }).toArray(scala.reflect.ClassTag..MODULE$.apply(FileStatus.class));
         } else {
            var10000 = fs.listStatus(path);
         }
      } catch (Throwable var37) {
         if (!(var37 instanceof FileNotFoundException) || !isRootPath && !ignoreMissingFiles) {
            if (var37 instanceof UnsupportedOperationException) {
               UnsupportedOperationException var21 = (UnsupportedOperationException)var37;
               throw new SparkUnsupportedOperationException("FAILED_READ_FILE.UNSUPPORTED_FILE_SYSTEM", (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("path"), path.toString()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("fileSystemClass"), fs.getClass().getName()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("method"), ((StackTraceElement)scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps((Object[])var21.getStackTrace()))).getMethodName())}))));
            }

            throw var37;
         }

         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"The directory ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, path)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"was not found. Was it deleted very recently?"})))).log(scala.collection.immutable.Nil..MODULE$))));
         var10000 = (FileStatus[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(FileStatus.class));
      }

      FileStatus[] statuses = var10000;
      FileStatus[] filteredStatuses = (FileStatus[])scala.collection.ArrayOps..MODULE$.filterNot$extension(scala.Predef..MODULE$.refArrayOps((Object[])statuses), (status) -> BoxesRunTime.boxToBoolean($anonfun$listLeafFiles$3(status)));
      Tuple2 var25 = scala.collection.ArrayOps..MODULE$.partition$extension(scala.Predef..MODULE$.refArrayOps((Object[])filteredStatuses), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$listLeafFiles$4(x$1)));
      if (var25 == null) {
         throw new MatchError(var25);
      } else {
         FileStatus[] topLevelFiles;
         label58: {
            FileStatus[] dirs = (FileStatus[])var25._1();
            FileStatus[] topLevelFiles = (FileStatus[])var25._2();
            Tuple2 var24 = new Tuple2(dirs, topLevelFiles);
            FileStatus[] dirs = (FileStatus[])var24._1();
            topLevelFiles = (FileStatus[])var24._2();
            if (contextOpt instanceof Some) {
               Some var32 = (Some)contextOpt;
               SparkContext context = (SparkContext)var32.value();
               if (dirs.length > parallelismThreshold) {
                  var38 = (Seq)this.parallelListLeafFilesInternal(context, org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])dirs), (x$3) -> x$3.getPath(), scala.reflect.ClassTag..MODULE$.apply(Path.class))).toImmutableArraySeq(), hadoopConf, filter, false, ignoreMissingFiles, ignoreLocality, parallelismThreshold, parallelismMax).flatMap((x$4) -> (Seq)x$4._2());
                  break label58;
               }
            }

            var38 = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[])dirs), (dir) -> MODULE$.listLeafFiles(dir.getPath(), hadoopConf, filter, contextOpt, ignoreMissingFiles, ignoreLocality, false, parallelismThreshold, parallelismMax), scala.reflect.ClassTag..MODULE$.apply(FileStatus.class))).toImmutableArraySeq();
         }

         Seq filteredNestedFiles = (Seq)var38;
         FileStatus[] filteredTopLevelFiles = filter != null ? (FileStatus[])scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])topLevelFiles), (f) -> BoxesRunTime.boxToBoolean($anonfun$listLeafFiles$8(filter, f))) : topLevelFiles;
         FileStatus[] allLeafStatuses = (FileStatus[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])filteredTopLevelFiles), filteredNestedFiles, scala.reflect.ClassTag..MODULE$.apply(FileStatus.class));
         ArrayBuffer missingFiles = scala.collection.mutable.ArrayBuffer..MODULE$.empty();
         FileStatus[] resolvedLeafStatuses = (FileStatus[])scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[])allLeafStatuses), (x0$1) -> {
            FileStatus var7 = x0$1;
            if (x0$1 instanceof LocatedFileStatus var8) {
               return new Some(var8);
            } else if (!ignoreLocality) {
               Object var10000;
               try {
                  BlockLocation[] locations = (BlockLocation[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])fs.getFileBlockLocations(var7, 0L, var7.getLen())), (loc) -> {
                     Class var10000 = loc.getClass();
                     Class var1 = BlockLocation.class;
                     if (var10000 == null) {
                        if (var1 == null) {
                           return loc;
                        }
                     } else if (var10000.equals(var1)) {
                        return loc;
                     }

                     return new BlockLocation(loc.getNames(), loc.getHosts(), loc.getOffset(), loc.getLength());
                  }, scala.reflect.ClassTag..MODULE$.apply(BlockLocation.class));
                  LocatedFileStatus lfs = new LocatedFileStatus(var7.getLen(), var7.isDirectory(), var7.getReplication(), var7.getBlockSize(), var7.getModificationTime(), 0L, (FsPermission)null, (String)null, (String)null, (Path)null, var7.getPath(), var7.hasAcl(), var7.isEncrypted(), var7.isErasureCoded(), locations);
                  if (var7.isSymlink()) {
                     lfs.setSymlink(var7.getSymlink());
                  }

                  var10000 = new Some(lfs);
               } catch (Throwable var13) {
                  if (!(var13 instanceof FileNotFoundException) || !ignoreMissingFiles) {
                     throw var13;
                  }

                  missingFiles.$plus$eq(x0$1.getPath().toString());
                  var10000 = scala.None..MODULE$;
               }

               return (Option)var10000;
            } else {
               return new Some(x0$1);
            }
         }, scala.reflect.ClassTag..MODULE$.apply(FileStatus.class));
         if (missingFiles.nonEmpty()) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"the following files were missing during file scan:\\n  "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATHS..MODULE$, missingFiles.mkString("\n  "))}))))));
         }

         return org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(resolvedLeafStatuses).toImmutableArraySeq();
      }
   }

   public boolean shouldFilterOutPathName(final String pathName) {
      boolean exclude = pathName.startsWith("_") && !pathName.contains("=") || pathName.startsWith(".") || pathName.endsWith("._COPYING_");
      boolean include = pathName.startsWith("_common_metadata") || pathName.startsWith("_metadata");
      return exclude && !include;
   }

   private Regex underscore() {
      return underscore;
   }

   private Regex underscoreEnd() {
      return underscoreEnd;
   }

   public boolean shouldFilterOutPath(final String path) {
      while(true) {
         if (!path.contains("/.") && !path.endsWith("._COPYING_")) {
            boolean var5 = false;
            Object var6 = null;
            Option var7 = this.underscoreEnd().findFirstIn(path);
            if (var7 instanceof Some) {
               var5 = true;
               Some var14 = (Some)var7;
               String dir = (String)var14.value();
               if (dir.equals("/_metadata") || dir.equals("/_common_metadata")) {
                  return false;
               }
            }

            if (var5) {
               return true;
            }

            if (scala.None..MODULE$.equals(var7)) {
               boolean var9 = false;
               Some var10 = null;
               Option var11 = this.underscore().findFirstIn(path);
               if (var11 instanceof Some) {
                  var9 = true;
                  var10 = (Some)var11;
                  String dir = (String)var10.value();
                  if (dir.equals("/_metadata/")) {
                     path = path.replaceFirst("/_metadata", "");
                     continue;
                  }
               }

               if (var9) {
                  String dir = (String)var10.value();
                  if (dir.equals("/_common_metadata/")) {
                     path = path.replaceFirst("/_common_metadata", "");
                     continue;
                  }
               }

               if (var9) {
                  return true;
               }

               if (scala.None..MODULE$.equals(var11)) {
                  return false;
               }

               throw new MatchError(var11);
            }

            throw new MatchError(var7);
         }

         return true;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$listFiles$2(final int prefixLength$1, final LocatedFileStatus status) {
      return MODULE$.shouldFilterOutPath(status.getPath().toString().substring(prefixLength$1));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$listFiles$3(final PathFilter filter$1, final LocatedFileStatus f) {
      return filter$1.accept(f.getPath());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$listLeafFiles$3(final FileStatus status) {
      return MODULE$.shouldFilterOutPathName(status.getPath().getName());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$listLeafFiles$4(final FileStatus x$1) {
      return x$1.isDirectory();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$listLeafFiles$8(final PathFilter filter$3, final FileStatus f) {
      return filter$3.accept(f.getPath());
   }

   private HadoopFSUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
