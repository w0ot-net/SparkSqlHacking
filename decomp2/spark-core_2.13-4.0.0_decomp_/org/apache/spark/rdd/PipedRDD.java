package org.apache.spark.rdd;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.invoke.SerializedLambda;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.spark.Partition;
import org.apache.spark.TaskContext;
import org.apache.spark.TaskContext$;
import org.apache.spark.errors.SparkCoreErrors$;
import org.apache.spark.internal.MDC;
import org.apache.spark.util.Utils$;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.BufferedIterator;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Map;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.jdk.CollectionConverters.;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ee!\u0002\u000e\u001c\u0001u\u0019\u0003\u0002C\u001c\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001d\t\u0011\u0015\u0003!\u0011!Q\u0001\n\u0019C\u0001b\u0014\u0001\u0003\u0002\u0003\u0006I\u0001\u0015\u0005\t-\u0002\u0011\t\u0011)A\u0005/\"Aa\f\u0001B\u0001B\u0003%q\f\u0003\u0005c\u0001\t\u0005\t\u0015!\u0003d\u0011!1\u0007A!A!\u0002\u00139\u0007\u0002\u00036\u0001\u0005\u0003\u0005\u000b\u0011B\u0015\t\u0011-\u0004!1!Q\u0001\f1DQA\u001d\u0001\u0005\u0002MDaa \u0001\u0005B\u0005\u0005aABA\t\u0001\u0001\t\u0019\u0002C\u0005\u000221\u0011\t\u0011)A\u0005S!1!\u000f\u0004C\u0001\u0003gAq!a\u000f\r\t\u0003\ti\u0004C\u0004\u0002N\u0001!\t%a\u0014\b\u000f\u0005\u00154\u0004#\u0003\u0002h\u00191!d\u0007E\u0005\u0003SBaA\u001d\n\u0005\u0002\u0005]\u0004bBA=%\u0011\u0005\u00111\u0010\u0005\n\u0003\u007f\u0012\"\u0019!C\u0001\u0003\u0003C\u0001\"a\"\u0013A\u0003%\u00111\u0011\u0005\n\u0003\u0013\u0013\"\u0019!C\u0001\u0003\u0003C\u0001\"a#\u0013A\u0003%\u00111\u0011\u0005\n\u0003\u001b\u0013\u0012\u0011!C\u0005\u0003\u001f\u0013\u0001\u0002U5qK\u0012\u0014F\t\u0012\u0006\u00039u\t1A\u001d3e\u0015\tqr$A\u0003ta\u0006\u00148N\u0003\u0002!C\u00051\u0011\r]1dQ\u0016T\u0011AI\u0001\u0004_J<WC\u0001\u0013<'\t\u0001Q\u0005E\u0002'O%j\u0011aG\u0005\u0003Qm\u00111A\u0015#E!\tQCG\u0004\u0002,eA\u0011A\u0006M\u0007\u0002[)\u0011afL\u0001\u0007yI|w\u000e\u001e \u0004\u0001)\t\u0011'A\u0003tG\u0006d\u0017-\u0003\u00024a\u00051\u0001K]3eK\u001aL!!\u000e\u001c\u0003\rM#(/\u001b8h\u0015\t\u0019\u0004'\u0001\u0003qe\u00164\bc\u0001\u0014(sA\u0011!h\u000f\u0007\u0001\t\u0015a\u0004A1\u0001>\u0005\u0005!\u0016C\u0001 C!\ty\u0004)D\u00011\u0013\t\t\u0005GA\u0004O_RD\u0017N\\4\u0011\u0005}\u001a\u0015B\u0001#1\u0005\r\te._\u0001\bG>lW.\u00198e!\r9E*\u000b\b\u0003\u0011*s!\u0001L%\n\u0003EJ!a\u0013\u0019\u0002\u000fA\f7m[1hK&\u0011QJ\u0014\u0002\u0004'\u0016\f(BA&1\u0003\u001d)gN\u001e,beN\u0004B!\u0015+*S5\t!K\u0003\u0002Ta\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005U\u0013&aA'ba\u0006\u0001\u0002O]5oiBK\u0007/Z\"p]R,\u0007\u0010\u001e\t\u0005\u007faS6,\u0003\u0002Za\tIa)\u001e8di&|g.\r\t\u0005\u007faK3\f\u0005\u0002@9&\u0011Q\f\r\u0002\u0005+:LG/A\bqe&tGO\u0015#E\u000b2,W.\u001a8u!\u0015y\u0004-\u000f.\\\u0013\t\t\u0007GA\u0005Gk:\u001cG/[8oe\u0005\u00112/\u001a9be\u0006$XmV8sW&tw\rR5s!\tyD-\u0003\u0002fa\t9!i\\8mK\u0006t\u0017A\u00032vM\u001a,'oU5{KB\u0011q\b[\u0005\u0003SB\u00121!\u00138u\u0003!)gnY8eS:<\u0017AC3wS\u0012,gnY3%cA\u0019Q\u000e]\u001d\u000e\u00039T!a\u001c\u0019\u0002\u000fI,g\r\\3di&\u0011\u0011O\u001c\u0002\t\u00072\f7o\u001d+bO\u00061A(\u001b8jiz\"\u0012\u0002^<ysj\\H0 @\u0015\u0005U4\bc\u0001\u0014\u0001s!)1N\u0003a\u0002Y\")qG\u0003a\u0001q!)QI\u0003a\u0001\r\")qJ\u0003a\u0001!\")aK\u0003a\u0001/\")aL\u0003a\u0001?\")!M\u0003a\u0001G\")aM\u0003a\u0001O\")!N\u0003a\u0001S\u0005iq-\u001a;QCJ$\u0018\u000e^5p]N,\"!a\u0001\u0011\u000b}\n)!!\u0003\n\u0007\u0005\u001d\u0001GA\u0003BeJ\f\u0017\u0010\u0005\u0003\u0002\f\u00055Q\"A\u000f\n\u0007\u0005=QDA\u0005QCJ$\u0018\u000e^5p]\n9bj\u001c;FcV\fGn\u001d$jY\u0016t\u0015-\\3GS2$XM]\n\u0006\u0019\u0005U\u0011Q\u0005\t\u0005\u0003/\t\t#\u0004\u0002\u0002\u001a)!\u00111DA\u000f\u0003\u0011a\u0017M\\4\u000b\u0005\u0005}\u0011\u0001\u00026bm\u0006LA!a\t\u0002\u001a\t1qJ\u00196fGR\u0004B!a\n\u0002.5\u0011\u0011\u0011\u0006\u0006\u0005\u0003W\ti\"\u0001\u0002j_&!\u0011qFA\u0015\u000591\u0015\u000e\\3oC6,g)\u001b7uKJ\f!BZ5mi\u0016\u0014h*Y7f)\u0011\t)$!\u000f\u0011\u0007\u0005]B\"D\u0001\u0001\u0011\u0019\t\tD\u0004a\u0001S\u00051\u0011mY2faR$RaYA \u0003\u0013Bq!!\u0011\u0010\u0001\u0004\t\u0019%A\u0002eSJ\u0004B!a\n\u0002F%!\u0011qIA\u0015\u0005\u00111\u0015\u000e\\3\t\r\u0005-s\u00021\u0001*\u0003\u0011q\u0017-\\3\u0002\u000f\r|W\u000e];uKR1\u0011\u0011KA,\u00037\u0002BaRA*S%\u0019\u0011Q\u000b(\u0003\u0011%#XM]1u_JDq!!\u0017\u0011\u0001\u0004\tI!A\u0003ta2LG\u000fC\u0004\u0002^A\u0001\r!a\u0018\u0002\u000f\r|g\u000e^3yiB!\u00111BA1\u0013\r\t\u0019'\b\u0002\f)\u0006\u001c8nQ8oi\u0016DH/\u0001\u0005QSB,GM\u0015#E!\t1#cE\u0003\u0013\u0003W\n\t\bE\u0002@\u0003[J1!a\u001c1\u0005\u0019\te.\u001f*fMB!\u0011qEA:\u0013\u0011\t)(!\u000b\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\u0005\u001d\u0014\u0001\u0003;pW\u0016t\u0017N_3\u0015\u0007\u0019\u000bi\bC\u0003F)\u0001\u0007\u0011&\u0001\u000eT)\u0012KejX,S\u0013R+%k\u0018+I%\u0016\u000bEi\u0018)S\u000b\u001aK\u0005,\u0006\u0002\u0002\u0004B!\u0011qCAC\u0013\r)\u0014\u0011D\u0001\u001c'R#\u0015JT0X%&#VIU0U\u0011J+\u0015\tR0Q%\u00163\u0015\n\u0017\u0011\u00027M#F)\u0012*S?J+\u0015\tR#S?RC%+R!E?B\u0013VIR%Y\u0003q\u0019F\u000bR#S%~\u0013V)\u0011#F%~#\u0006JU#B\t~\u0003&+\u0012$J1\u0002\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u0006"
)
public class PipedRDD extends RDD {
   public final Seq org$apache$spark$rdd$PipedRDD$$command;
   private final Map envVars;
   public final Function1 org$apache$spark$rdd$PipedRDD$$printPipeContext;
   public final Function2 org$apache$spark$rdd$PipedRDD$$printRDDElement;
   private final boolean separateWorkingDir;
   public final int org$apache$spark$rdd$PipedRDD$$bufferSize;
   public final String org$apache$spark$rdd$PipedRDD$$encoding;
   public final ClassTag org$apache$spark$rdd$PipedRDD$$evidence$1;

   public static String STDERR_READER_THREAD_PREFIX() {
      return PipedRDD$.MODULE$.STDERR_READER_THREAD_PREFIX();
   }

   public static String STDIN_WRITER_THREAD_PREFIX() {
      return PipedRDD$.MODULE$.STDIN_WRITER_THREAD_PREFIX();
   }

   public static Seq tokenize(final String command) {
      return PipedRDD$.MODULE$.tokenize(command);
   }

   public Partition[] getPartitions() {
      return this.firstParent(this.org$apache$spark$rdd$PipedRDD$$evidence$1).partitions();
   }

   public Iterator compute(final Partition split, final TaskContext context) {
      ProcessBuilder pb = new ProcessBuilder(.MODULE$.SeqHasAsJava(this.org$apache$spark$rdd$PipedRDD$$command).asJava());
      java.util.Map currentEnvVars = pb.environment();
      this.envVars.foreach((x0$1) -> {
         if (x0$1 != null) {
            String variable = (String)x0$1._1();
            String value = (String)x0$1._2();
            return (String)currentEnvVars.put(variable, value);
         } else {
            throw new MatchError(x0$1);
         }
      });
      if (split instanceof HadoopPartition var7) {
         currentEnvVars.putAll(.MODULE$.MapHasAsJava(var7.getPipeEnvVars()).asJava());
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var20 = BoxedUnit.UNIT;
      }

      String var21 = File.separator;
      String taskDirectory = "tasks" + var21 + UUID.randomUUID().toString();
      BooleanRef workInTaskDirectory = BooleanRef.create(false);
      this.logDebug(() -> "taskDirectory = " + taskDirectory);
      if (this.separateWorkingDir) {
         File currentDir = new File(".");
         this.logDebug(() -> "currentDir = " + currentDir.getAbsolutePath());
         File taskDirFile = new File(taskDirectory);
         taskDirFile.mkdirs();

         try {
            NotEqualsFileNameFilter tasksDirFilter = new NotEqualsFileNameFilter("tasks");
            scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])currentDir.list(tasksDirFilter)), (file) -> {
               $anonfun$compute$4(currentDir, taskDirectory, file);
               return BoxedUnit.UNIT;
            });
            pb.directory(taskDirFile);
            workInTaskDirectory.elem = true;
         } catch (Exception var19) {
            this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Unable to setup task working directory: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, var19.getMessage())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" (", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, taskDirectory)}))))), var19);
         }
      }

      Process proc = pb.start();
      AtomicReference childThreadException = new AtomicReference((Object)null);
      Thread stderrReaderThread = new Thread(proc, childThreadException) {
         // $FF: synthetic field
         private final PipedRDD $outer;
         private final Process proc$1;
         private final AtomicReference childThreadException$1;

         public void run() {
            InputStream err = this.proc$1.getErrorStream();

            try {
               scala.io.Source..MODULE$.fromInputStream(err, scala.io.Codec..MODULE$.string2codec(this.$outer.org$apache$spark$rdd$PipedRDD$$encoding)).getLines().foreach((line) -> {
                  $anonfun$run$1(line);
                  return BoxedUnit.UNIT;
               });
            } catch (Throwable var6) {
               this.childThreadException$1.set(var6);
            } finally {
               err.close();
            }

         }

         // $FF: synthetic method
         public static final void $anonfun$run$1(final String line) {
            System.err.println(line);
         }

         public {
            if (PipedRDD.this == null) {
               throw null;
            } else {
               this.$outer = PipedRDD.this;
               this.proc$1 = proc$1;
               this.childThreadException$1 = childThreadException$1;
               String var10001 = PipedRDD$.MODULE$.STDERR_READER_THREAD_PREFIX();
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      stderrReaderThread.start();
      Thread stdinWriterThread = new Thread(context, proc, split, childThreadException) {
         // $FF: synthetic field
         private final PipedRDD $outer;
         private final TaskContext context$1;
         private final Process proc$1;
         private final Partition split$1;
         private final AtomicReference childThreadException$1;

         public void run() {
            TaskContext$.MODULE$.setTaskContext(this.context$1);
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.proc$1.getOutputStream(), this.$outer.org$apache$spark$rdd$PipedRDD$$encoding), this.$outer.org$apache$spark$rdd$PipedRDD$$bufferSize));

            try {
               if (this.$outer.org$apache$spark$rdd$PipedRDD$$printPipeContext != null) {
                  this.$outer.org$apache$spark$rdd$PipedRDD$$printPipeContext.apply((Function1)(x$1) -> {
                     $anonfun$run$2(out, x$1);
                     return BoxedUnit.UNIT;
                  });
               } else {
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }

               this.$outer.firstParent(this.$outer.org$apache$spark$rdd$PipedRDD$$evidence$1).iterator(this.split$1, this.context$1).foreach((elem) -> {
                  $anonfun$run$3(this, out, elem);
                  return BoxedUnit.UNIT;
               });
            } catch (Throwable var6) {
               this.childThreadException$1.set(var6);
            } finally {
               out.close();
            }

         }

         // $FF: synthetic method
         public static final void $anonfun$run$2(final PrintWriter out$1, final String x$1) {
            out$1.println(x$1);
         }

         // $FF: synthetic method
         public static final void $anonfun$run$4(final PrintWriter out$1, final String x$1) {
            out$1.println(x$1);
         }

         // $FF: synthetic method
         public static final void $anonfun$run$3(final Object $this, final PrintWriter out$1, final Object elem) {
            if ($this.$outer.org$apache$spark$rdd$PipedRDD$$printRDDElement != null) {
               $this.$outer.org$apache$spark$rdd$PipedRDD$$printRDDElement.apply(elem, (Function1)(x$1) -> {
                  $anonfun$run$4(out$1, x$1);
                  return BoxedUnit.UNIT;
               });
            } else {
               out$1.println(elem);
            }
         }

         public {
            if (PipedRDD.this == null) {
               throw null;
            } else {
               this.$outer = PipedRDD.this;
               this.context$1 = context$1;
               this.proc$1 = proc$1;
               this.split$1 = split$1;
               this.childThreadException$1 = childThreadException$1;
               String var10001 = PipedRDD$.MODULE$.STDIN_WRITER_THREAD_PREFIX();
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      stdinWriterThread.start();
      context.addTaskCompletionListener((Function1)((x$1) -> {
         $anonfun$compute$6(proc, stdinWriterThread, stderrReaderThread, x$1);
         return BoxedUnit.UNIT;
      }));
      Iterator lines = scala.io.Source..MODULE$.fromInputStream(proc.getInputStream(), scala.io.Codec..MODULE$.string2codec(this.org$apache$spark$rdd$PipedRDD$$encoding)).getLines();
      return new Iterator(lines, proc, workInTaskDirectory, taskDirectory, childThreadException) {
         // $FF: synthetic field
         private final PipedRDD $outer;
         private final Iterator lines$1;
         private final Process proc$1;
         private final BooleanRef workInTaskDirectory$1;
         private final String taskDirectory$1;
         private final AtomicReference childThreadException$1;

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

         public String next() {
            if (!this.hasNext()) {
               throw SparkCoreErrors$.MODULE$.noSuchElementError();
            } else {
               return (String)this.lines$1.next();
            }
         }

         public boolean hasNext() {
            boolean var10000;
            if (this.lines$1.hasNext()) {
               var10000 = true;
            } else {
               int exitStatus = this.proc$1.waitFor();
               this.cleanup();
               if (exitStatus != 0) {
                  throw new IllegalStateException("Subprocess exited with status " + exitStatus + ". Command ran: " + this.$outer.org$apache$spark$rdd$PipedRDD$$command.mkString(" "));
               }

               var10000 = false;
            }

            boolean result = var10000;
            this.propagateChildException();
            return result;
         }

         private void cleanup() {
            if (this.workInTaskDirectory$1.elem) {
               scala.util.control.Exception..MODULE$.ignoring(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{IOException.class}))).apply((JFunction0.mcV.sp)() -> Utils$.MODULE$.deleteRecursively(new File(this.taskDirectory$1)));
               this.$outer.logDebug(() -> "Removed task working directory " + this.taskDirectory$1);
            }
         }

         private void propagateChildException() {
            Throwable t = (Throwable)this.childThreadException$1.get();
            if (t != null) {
               String commandRan = this.$outer.org$apache$spark$rdd$PipedRDD$$command.mkString(" ");
               this.$outer.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Caught exception while running pipe() operator. Command ran: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ". Exception: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COMMAND..MODULE$, commandRan), new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, t.getMessage())}))))));
               this.proc$1.destroy();
               this.cleanup();
               throw t;
            }
         }

         public {
            if (PipedRDD.this == null) {
               throw null;
            } else {
               this.$outer = PipedRDD.this;
               this.lines$1 = lines$1;
               this.proc$1 = proc$1;
               this.workInTaskDirectory$1 = workInTaskDirectory$1;
               this.taskDirectory$1 = taskDirectory$1;
               this.childThreadException$1 = childThreadException$1;
               IterableOnce.$init$(this);
               IterableOnceOps.$init$(this);
               Iterator.$init$(this);
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   public static final void $anonfun$compute$4(final File currentDir$1, final String taskDirectory$1, final String file) {
      File fileWithDir = new File(currentDir$1, file);
      Utils$.MODULE$.symlink(new File(fileWithDir.getAbsolutePath()), new File(taskDirectory$1 + File.separator + fileWithDir.getName()));
   }

   // $FF: synthetic method
   public static final void $anonfun$compute$6(final Process proc$1, final Thread stdinWriterThread$1, final Thread stderrReaderThread$1, final TaskContext x$1) {
      if (proc$1.isAlive()) {
         proc$1.destroy();
      }

      if (stdinWriterThread$1.isAlive()) {
         stdinWriterThread$1.interrupt();
      }

      if (stderrReaderThread$1.isAlive()) {
         stderrReaderThread$1.interrupt();
      }
   }

   public PipedRDD(final RDD prev, final Seq command, final Map envVars, final Function1 printPipeContext, final Function2 printRDDElement, final boolean separateWorkingDir, final int bufferSize, final String encoding, final ClassTag evidence$1) {
      super(prev, scala.reflect.ClassTag..MODULE$.apply(String.class));
      this.org$apache$spark$rdd$PipedRDD$$command = command;
      this.envVars = envVars;
      this.org$apache$spark$rdd$PipedRDD$$printPipeContext = printPipeContext;
      this.org$apache$spark$rdd$PipedRDD$$printRDDElement = printRDDElement;
      this.separateWorkingDir = separateWorkingDir;
      this.org$apache$spark$rdd$PipedRDD$$bufferSize = bufferSize;
      this.org$apache$spark$rdd$PipedRDD$$encoding = encoding;
      this.org$apache$spark$rdd$PipedRDD$$evidence$1 = evidence$1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class NotEqualsFileNameFilter implements FilenameFilter {
      private final String filterName;
      // $FF: synthetic field
      public final PipedRDD $outer;

      public boolean accept(final File dir, final String name) {
         return !name.equals(this.filterName);
      }

      // $FF: synthetic method
      public PipedRDD org$apache$spark$rdd$PipedRDD$NotEqualsFileNameFilter$$$outer() {
         return this.$outer;
      }

      public NotEqualsFileNameFilter(final String filterName) {
         this.filterName = filterName;
         if (PipedRDD.this == null) {
            throw null;
         } else {
            this.$outer = PipedRDD.this;
            super();
         }
      }
   }
}
