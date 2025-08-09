package org.apache.spark.streaming.util;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
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
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u4Qa\u0004\t\u0001%iA\u0001B\u0011\u0001\u0003\u0002\u0003\u0006Ia\u0011\u0005\t\u0017\u0002\u0011\t\u0011)A\u0005\u0019\")1\u000b\u0001C\u0001)\"9\u0011\f\u0001b\u0001\n\u0013Q\u0006BB1\u0001A\u0003%1\fC\u0004c\u0001\u0001\u0007I\u0011B2\t\u000f\u001d\u0004\u0001\u0019!C\u0005Q\"1a\u000e\u0001Q!\n\u0011Dqa\u001c\u0001A\u0002\u0013%\u0001\u000fC\u0004u\u0001\u0001\u0007I\u0011B;\t\r]\u0004\u0001\u0015)\u0003r\u0011\u0015A\b\u0001\"\u0011d\u0011\u0015I\b\u0001\"\u0011{\u0011\u0015Y\b\u0001\"\u0011}\u0005q1\u0015\u000e\\3CCN,Gm\u0016:ji\u0016\f\u0005.Z1e\u0019><'+Z1eKJT!!\u0005\n\u0002\tU$\u0018\u000e\u001c\u0006\u0003'Q\t\u0011b\u001d;sK\u0006l\u0017N\\4\u000b\u0005U1\u0012!B:qCJ\\'BA\f\u0019\u0003\u0019\t\u0007/Y2iK*\t\u0011$A\u0002pe\u001e\u001cR\u0001A\u000e\"mq\u0002\"\u0001H\u0010\u000e\u0003uQ\u0011AH\u0001\u0006g\u000e\fG.Y\u0005\u0003Au\u0011a!\u00118z%\u00164\u0007c\u0001\u0012,]9\u00111%\u000b\b\u0003I!j\u0011!\n\u0006\u0003M\u001d\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002=%\u0011!&H\u0001\ba\u0006\u001c7.Y4f\u0013\taSF\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0015\tQS\u0004\u0005\u00020i5\t\u0001G\u0003\u00022e\u0005\u0019a.[8\u000b\u0003M\nAA[1wC&\u0011Q\u0007\r\u0002\u000b\u0005f$XMQ;gM\u0016\u0014\bCA\u001c;\u001b\u0005A$BA\u001d3\u0003\tIw.\u0003\u0002<q\tI1\t\\8tK\u0006\u0014G.\u001a\t\u0003{\u0001k\u0011A\u0010\u0006\u0003\u007fQ\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003\u0003z\u0012q\u0001T8hO&tw-\u0001\u0003qCRD\u0007C\u0001#I\u001d\t)e\t\u0005\u0002%;%\u0011q)H\u0001\u0007!J,G-\u001a4\n\u0005%S%AB*ue&twM\u0003\u0002H;\u0005!1m\u001c8g!\ti\u0015+D\u0001O\u0015\tYuJ\u0003\u0002Q-\u00051\u0001.\u00193p_BL!A\u0015(\u0003\u001b\r{gNZ5hkJ\fG/[8o\u0003\u0019a\u0014N\\5u}Q\u0019Qk\u0016-\u0011\u0005Y\u0003Q\"\u0001\t\t\u000b\t\u001b\u0001\u0019A\"\t\u000b-\u001b\u0001\u0019\u0001'\u0002\u0011%t7\u000f\u001e:fC6,\u0012a\u0017\t\u00039~k\u0011!\u0018\u0006\u0003=>\u000b!AZ:\n\u0005\u0001l&!\u0005$T\t\u0006$\u0018-\u00138qkR\u001cFO]3b[\u0006I\u0011N\\:ue\u0016\fW\u000eI\u0001\u0007G2|7/\u001a3\u0016\u0003\u0011\u0004\"\u0001H3\n\u0005\u0019l\"a\u0002\"p_2,\u0017M\\\u0001\u000bG2|7/\u001a3`I\u0015\fHCA5m!\ta\".\u0003\u0002l;\t!QK\\5u\u0011\u001diw!!AA\u0002\u0011\f1\u0001\u001f\u00132\u0003\u001d\u0019Gn\\:fI\u0002\n\u0001B\\3yi&#X-\\\u000b\u0002cB\u0019AD\u001d\u0018\n\u0005Ml\"AB(qi&|g.\u0001\u0007oKb$\u0018\n^3n?\u0012*\u0017\u000f\u0006\u0002jm\"9QNCA\u0001\u0002\u0004\t\u0018!\u00038fqRLE/Z7!\u0003\u001dA\u0017m\u001d(fqR\fAA\\3yiR\ta&A\u0003dY>\u001cX\rF\u0001j\u0001"
)
public class FileBasedWriteAheadLogReader implements Iterator, Closeable, Logging {
   private final String path;
   private final Configuration conf;
   private final FSDataInputStream instream;
   private boolean closed;
   private Option nextItem;
   private transient Logger org$apache$spark$internal$Logging$$log_;

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

   public scala.collection.immutable.Map toMap(final .less.colon.less ev) {
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

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private FSDataInputStream instream() {
      return this.instream;
   }

   private boolean closed() {
      return this.closed;
   }

   private void closed_$eq(final boolean x$1) {
      this.closed = x$1;
   }

   private Option nextItem() {
      return this.nextItem;
   }

   private void nextItem_$eq(final Option x$1) {
      this.nextItem = x$1;
   }

   public synchronized boolean hasNext() {
      if (this.closed()) {
         return false;
      } else if (this.nextItem().isDefined()) {
         return true;
      } else {
         boolean var10000;
         try {
            int length = this.instream().readInt();
            byte[] buffer = new byte[length];
            this.instream().readFully(buffer);
            this.nextItem_$eq(new Some(ByteBuffer.wrap(buffer)));
            this.logTrace((Function0)(() -> "Read next item " + this.nextItem().get()));
            var10000 = true;
         } catch (EOFException var6) {
            this.logDebug((Function0)(() -> "Error reading next item, EOF reached"), var6);
            this.close();
            var10000 = false;
         } catch (IOException var7) {
            this.logWarning((Function0)(() -> "Error while trying to read data. If the file was deleted, this should be okay."), var7);
            this.close();
            if (HdfsUtils$.MODULE$.checkFileExists(this.path, this.conf)) {
               throw var7;
            }

            var10000 = false;
         } catch (Exception var8) {
            this.logWarning((Function0)(() -> "Error while trying to read data from HDFS."), var8);
            this.close();
            throw var8;
         }

         return var10000;
      }
   }

   public synchronized ByteBuffer next() {
      ByteBuffer data = (ByteBuffer)this.nextItem().getOrElse(() -> {
         this.close();
         throw new IllegalStateException("next called without calling hasNext or after hasNext returned false");
      });
      this.nextItem_$eq(scala.None..MODULE$);
      return data;
   }

   public synchronized void close() {
      if (!this.closed()) {
         this.instream().close();
      }

      this.closed_$eq(true);
   }

   public FileBasedWriteAheadLogReader(final String path, final Configuration conf) {
      this.path = path;
      this.conf = conf;
      IterableOnce.$init$(this);
      IterableOnceOps.$init$(this);
      Iterator.$init$(this);
      Logging.$init$(this);
      this.instream = HdfsUtils$.MODULE$.getInputStream(path, conf);
      this.closed = this.instream() == null;
      this.nextItem = scala.None..MODULE$;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
