package org.apache.spark.util.collection;

import java.lang.invoke.SerializedLambda;
import java.util.Comparator;
import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
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
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Queue;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ub!\u0002\f\u0018\u0001m\t\u0003\u0002C \u0001\u0005\u0003\u0005\u000b\u0011\u0002!\t\u000b\r\u0003A\u0011\u0001#\t\u000f\u001d\u0003\u0001\u0019!C\u0005\u0011\"9\u0011\n\u0001a\u0001\n\u0013Q\u0005B\u0002)\u0001A\u0003&\u0001\tC\u0004R\u0001\u0001\u0007I\u0011\u0002%\t\u000fI\u0003\u0001\u0019!C\u0005'\"1Q\u000b\u0001Q!\n\u0001CqA\u0016\u0001A\u0002\u0013%q\u000bC\u0004\\\u0001\u0001\u0007I\u0011\u0002/\t\ry\u0003\u0001\u0015)\u0003Y\u0011\u0015y\u0006\u0001\"\u0001a\u0011\u00159\u0007\u0001\"\u0003i\u0011\u0015I\u0007\u0001\"\u0011k\u0011\u001d\ty\u0001\u0001C\u0005\u0003#9q!a\u0005\u0018\u0011\u0013\t)B\u0002\u0004\u0017/!%\u0011q\u0003\u0005\u0007\u0007F!\t!!\u0007\t\u0011\u0005m\u0011C1A\u0005\u0002!Cq!!\b\u0012A\u0003%\u0001\tC\u0005\u0002 E\t\n\u0011\"\u0001\u0002\"\t)\u0002+\u0019:uSRLwN\\3e!\u0006L'OQ;gM\u0016\u0014(B\u0001\r\u001a\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u00035m\tA!\u001e;jY*\u0011A$H\u0001\u0006gB\f'o\u001b\u0006\u0003=}\ta!\u00199bG\",'\"\u0001\u0011\u0002\u0007=\u0014x-F\u0002#_i\u001aB\u0001A\u0012*yA\u0011AeJ\u0007\u0002K)\ta%A\u0003tG\u0006d\u0017-\u0003\u0002)K\t1\u0011I\\=SK\u001a\u0004BAK\u0016.s5\tq#\u0003\u0002-/\t\tsK]5uC\ndW\rU1si&$\u0018n\u001c8fIB\u000b\u0017N]\"pY2,7\r^5p]B\u0011af\f\u0007\u0001\t\u0015\u0001\u0004A1\u00013\u0005\u0005Y5\u0001A\t\u0003gY\u0002\"\u0001\n\u001b\n\u0005U*#a\u0002(pi\"Lgn\u001a\t\u0003I]J!\u0001O\u0013\u0003\u0007\u0005s\u0017\u0010\u0005\u0002/u\u0011)1\b\u0001b\u0001e\t\ta\u000b\u0005\u0002+{%\u0011ah\u0006\u0002\f'&TX\r\u0016:bG.,'/A\bj]&$\u0018.\u00197DCB\f7-\u001b;z!\t!\u0013)\u0003\u0002CK\t\u0019\u0011J\u001c;\u0002\rqJg.\u001b;?)\t)e\t\u0005\u0003+\u00015J\u0004bB \u0003!\u0003\u0005\r\u0001Q\u0001\tG\u0006\u0004\u0018mY5usV\t\u0001)\u0001\u0007dCB\f7-\u001b;z?\u0012*\u0017\u000f\u0006\u0002L\u001dB\u0011A\u0005T\u0005\u0003\u001b\u0016\u0012A!\u00168ji\"9q\nBA\u0001\u0002\u0004\u0001\u0015a\u0001=%c\u0005I1-\u00199bG&$\u0018\u0010I\u0001\bGV\u00148+\u001b>f\u0003-\u0019WO]*ju\u0016|F%Z9\u0015\u0005-#\u0006bB(\b\u0003\u0003\u0005\r\u0001Q\u0001\tGV\u00148+\u001b>fA\u0005!A-\u0019;b+\u0005A\u0006c\u0001\u0013ZG%\u0011!,\n\u0002\u0006\u0003J\u0014\u0018-_\u0001\tI\u0006$\u0018m\u0018\u0013fcR\u00111*\u0018\u0005\b\u001f*\t\t\u00111\u0001Y\u0003\u0015!\u0017\r^1!\u0003\u0019Ign]3siR!1*Y2f\u0011\u0015\u0011G\u00021\u0001A\u0003%\u0001\u0018M\u001d;ji&|g\u000eC\u0003e\u0019\u0001\u0007Q&A\u0002lKfDQA\u001a\u0007A\u0002e\nQA^1mk\u0016\f\u0011b\u001a:po\u0006\u0013(/Y=\u0015\u0003-\u000bA\u0005]1si&$\u0018n\u001c8fI\u0012+7\u000f\u001e:vGRLg/Z*peR,G-\u0013;fe\u0006$xN\u001d\u000b\u0003Wn\u00042\u0001\u001c;x\u001d\ti'O\u0004\u0002oc6\tqN\u0003\u0002qc\u00051AH]8pizJ\u0011AJ\u0005\u0003g\u0016\nq\u0001]1dW\u0006<W-\u0003\u0002vm\nA\u0011\n^3sCR|'O\u0003\u0002tKA!A\u0005\u001f>:\u0013\tIXE\u0001\u0004UkBdWM\r\t\u0005Ia\u0004U\u0006C\u0003}\u001d\u0001\u0007Q0A\u0007lKf\u001cu.\u001c9be\u0006$xN\u001d\t\u0005Iy\f\t!\u0003\u0002\u0000K\t1q\n\u001d;j_:\u0004R!a\u0001\u0002\f5j!!!\u0002\u000b\u0007i\t9A\u0003\u0002\u0002\n\u0005!!.\u0019<b\u0013\u0011\ti!!\u0002\u0003\u0015\r{W\u000e]1sCR|'/\u0001\u0005ji\u0016\u0014\u0018\r^8s)\u0005Y\u0017!\u0006)beRLG/[8oK\u0012\u0004\u0016-\u001b:Ck\u001a4WM\u001d\t\u0003UE\u0019\"!E\u0012\u0015\u0005\u0005U\u0011\u0001E'B1&kU+T0D\u0003B\u000b5)\u0013+Z\u0003Ei\u0015\tW%N+6{6)\u0011)B\u0007&#\u0016\fI\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u0019\u0016\r\u0005\r\u0012\u0011HA\u001e+\t\t)CK\u0002A\u0003OY#!!\u000b\u0011\t\u0005-\u0012QG\u0007\u0003\u0003[QA!a\f\u00022\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003g)\u0013AC1o]>$\u0018\r^5p]&!\u0011qGA\u0017\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0003\u0006aU\u0011\rA\r\u0003\u0006wU\u0011\rA\r"
)
public class PartitionedPairBuffer implements WritablePartitionedPairCollection, SizeTracker {
   private int capacity;
   private int org$apache$spark$util$collection$PartitionedPairBuffer$$curSize;
   private Object[] org$apache$spark$util$collection$PartitionedPairBuffer$$data;
   private double org$apache$spark$util$collection$SizeTracker$$SAMPLE_GROWTH_RATE;
   private Queue org$apache$spark$util$collection$SizeTracker$$samples;
   private double org$apache$spark$util$collection$SizeTracker$$bytesPerUpdate;
   private long org$apache$spark$util$collection$SizeTracker$$numUpdates;
   private long org$apache$spark$util$collection$SizeTracker$$nextSampleNum;

   public static int $lessinit$greater$default$1() {
      return PartitionedPairBuffer$.MODULE$.$lessinit$greater$default$1();
   }

   public static int MAXIMUM_CAPACITY() {
      return PartitionedPairBuffer$.MODULE$.MAXIMUM_CAPACITY();
   }

   public void resetSamples() {
      SizeTracker.resetSamples$(this);
   }

   public void afterUpdate() {
      SizeTracker.afterUpdate$(this);
   }

   public long estimateSize() {
      return SizeTracker.estimateSize$(this);
   }

   public WritablePartitionedIterator destructiveSortedWritablePartitionedIterator(final Option keyComparator) {
      return WritablePartitionedPairCollection.destructiveSortedWritablePartitionedIterator$(this, keyComparator);
   }

   public double org$apache$spark$util$collection$SizeTracker$$SAMPLE_GROWTH_RATE() {
      return this.org$apache$spark$util$collection$SizeTracker$$SAMPLE_GROWTH_RATE;
   }

   public Queue org$apache$spark$util$collection$SizeTracker$$samples() {
      return this.org$apache$spark$util$collection$SizeTracker$$samples;
   }

   public double org$apache$spark$util$collection$SizeTracker$$bytesPerUpdate() {
      return this.org$apache$spark$util$collection$SizeTracker$$bytesPerUpdate;
   }

   public void org$apache$spark$util$collection$SizeTracker$$bytesPerUpdate_$eq(final double x$1) {
      this.org$apache$spark$util$collection$SizeTracker$$bytesPerUpdate = x$1;
   }

   public long org$apache$spark$util$collection$SizeTracker$$numUpdates() {
      return this.org$apache$spark$util$collection$SizeTracker$$numUpdates;
   }

   public void org$apache$spark$util$collection$SizeTracker$$numUpdates_$eq(final long x$1) {
      this.org$apache$spark$util$collection$SizeTracker$$numUpdates = x$1;
   }

   public long org$apache$spark$util$collection$SizeTracker$$nextSampleNum() {
      return this.org$apache$spark$util$collection$SizeTracker$$nextSampleNum;
   }

   public void org$apache$spark$util$collection$SizeTracker$$nextSampleNum_$eq(final long x$1) {
      this.org$apache$spark$util$collection$SizeTracker$$nextSampleNum = x$1;
   }

   public final void org$apache$spark$util$collection$SizeTracker$_setter_$org$apache$spark$util$collection$SizeTracker$$SAMPLE_GROWTH_RATE_$eq(final double x$1) {
      this.org$apache$spark$util$collection$SizeTracker$$SAMPLE_GROWTH_RATE = x$1;
   }

   public final void org$apache$spark$util$collection$SizeTracker$_setter_$org$apache$spark$util$collection$SizeTracker$$samples_$eq(final Queue x$1) {
      this.org$apache$spark$util$collection$SizeTracker$$samples = x$1;
   }

   private int capacity() {
      return this.capacity;
   }

   private void capacity_$eq(final int x$1) {
      this.capacity = x$1;
   }

   public int org$apache$spark$util$collection$PartitionedPairBuffer$$curSize() {
      return this.org$apache$spark$util$collection$PartitionedPairBuffer$$curSize;
   }

   private void curSize_$eq(final int x$1) {
      this.org$apache$spark$util$collection$PartitionedPairBuffer$$curSize = x$1;
   }

   public Object[] org$apache$spark$util$collection$PartitionedPairBuffer$$data() {
      return this.org$apache$spark$util$collection$PartitionedPairBuffer$$data;
   }

   private void data_$eq(final Object[] x$1) {
      this.org$apache$spark$util$collection$PartitionedPairBuffer$$data = x$1;
   }

   public void insert(final int partition, final Object key, final Object value) {
      if (this.org$apache$spark$util$collection$PartitionedPairBuffer$$curSize() == this.capacity()) {
         this.growArray();
      }

      this.org$apache$spark$util$collection$PartitionedPairBuffer$$data()[2 * this.org$apache$spark$util$collection$PartitionedPairBuffer$$curSize()] = new Tuple2(BoxesRunTime.boxToInteger(partition), key);
      this.org$apache$spark$util$collection$PartitionedPairBuffer$$data()[2 * this.org$apache$spark$util$collection$PartitionedPairBuffer$$curSize() + 1] = value;
      this.curSize_$eq(this.org$apache$spark$util$collection$PartitionedPairBuffer$$curSize() + 1);
      this.afterUpdate();
   }

   private void growArray() {
      if (this.capacity() >= PartitionedPairBuffer$.MODULE$.MAXIMUM_CAPACITY()) {
         throw new IllegalStateException("Can't insert more than " + PartitionedPairBuffer$.MODULE$.MAXIMUM_CAPACITY() + " elements");
      } else {
         int newCapacity = this.capacity() * 2 > PartitionedPairBuffer$.MODULE$.MAXIMUM_CAPACITY() ? PartitionedPairBuffer$.MODULE$.MAXIMUM_CAPACITY() : this.capacity() * 2;
         Object[] newArray = new Object[2 * newCapacity];
         System.arraycopy(this.org$apache$spark$util$collection$PartitionedPairBuffer$$data(), 0, newArray, 0, 2 * this.capacity());
         this.data_$eq(newArray);
         this.capacity_$eq(newCapacity);
         this.resetSamples();
      }
   }

   public Iterator partitionedDestructiveSortedIterator(final Option keyComparator) {
      Comparator comparator = (Comparator)keyComparator.map((keyComparatorx) -> WritablePartitionedPairCollection$.MODULE$.partitionKeyComparator(keyComparatorx)).getOrElse(() -> WritablePartitionedPairCollection$.MODULE$.partitionComparator());
      (new Sorter(new KVArraySortDataFormat(.MODULE$.AnyRef()))).sort(this.org$apache$spark$util$collection$PartitionedPairBuffer$$data(), 0, this.org$apache$spark$util$collection$PartitionedPairBuffer$$curSize(), comparator);
      return this.iterator();
   }

   private Iterator iterator() {
      return new Iterator() {
         private int pos;
         // $FF: synthetic field
         private final PartitionedPairBuffer $outer;

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

         public Map toMap(final scala..less.colon.less ev) {
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

         private int pos() {
            return this.pos;
         }

         private void pos_$eq(final int x$1) {
            this.pos = x$1;
         }

         public boolean hasNext() {
            return this.pos() < this.$outer.org$apache$spark$util$collection$PartitionedPairBuffer$$curSize();
         }

         public Tuple2 next() {
            if (!this.hasNext()) {
               throw new NoSuchElementException();
            } else {
               Tuple2 pair = new Tuple2((Tuple2)this.$outer.org$apache$spark$util$collection$PartitionedPairBuffer$$data()[2 * this.pos()], this.$outer.org$apache$spark$util$collection$PartitionedPairBuffer$$data()[2 * this.pos() + 1]);
               this.pos_$eq(this.pos() + 1);
               return pair;
            }
         }

         public {
            if (PartitionedPairBuffer.this == null) {
               throw null;
            } else {
               this.$outer = PartitionedPairBuffer.this;
               IterableOnce.$init$(this);
               IterableOnceOps.$init$(this);
               Iterator.$init$(this);
               this.pos = 0;
            }
         }
      };
   }

   public PartitionedPairBuffer(final int initialCapacity) {
      WritablePartitionedPairCollection.$init$(this);
      SizeTracker.$init$(this);
      scala.Predef..MODULE$.require(initialCapacity <= PartitionedPairBuffer$.MODULE$.MAXIMUM_CAPACITY(), () -> "Can't make capacity bigger than " + PartitionedPairBuffer$.MODULE$.MAXIMUM_CAPACITY() + " elements");
      scala.Predef..MODULE$.require(initialCapacity >= 1, () -> "Invalid initial capacity");
      this.capacity = initialCapacity;
      this.org$apache$spark$util$collection$PartitionedPairBuffer$$curSize = 0;
      this.org$apache$spark$util$collection$PartitionedPairBuffer$$data = new Object[2 * initialCapacity];
      Statics.releaseFence();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
