package org.apache.spark.storage.memory;

import org.apache.spark.memory.MemoryMode;
import scala.;
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
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q3Qa\u0003\u0007\u0001\u001dYA\u0001B\u000e\u0001\u0003\u0002\u0003\u0006Ia\u000e\u0005\tw\u0001\u0011\t\u0011)A\u0005y!A\u0011\t\u0001B\u0001B\u0003%!\t\u0003\u0005F\u0001\t\u0005\t\u0015)\u0003\u001f\u0011!1\u0005A!A!\u0002\u0013q\u0002\"B$\u0001\t\u0003A\u0005\"B(\u0001\t\u0013\u0001\u0006\"\u0002+\u0001\t\u0003*\u0006\"B-\u0001\t\u0003R\u0006\"B.\u0001\t\u0003\u0001&!\u0007)beRL\u0017\r\u001c7z+:\u0014x\u000e\u001c7fI&#XM]1u_JT!!\u0004\b\u0002\r5,Wn\u001c:z\u0015\ty\u0001#A\u0004ti>\u0014\u0018mZ3\u000b\u0005E\u0011\u0012!B:qCJ\\'BA\n\u0015\u0003\u0019\t\u0007/Y2iK*\tQ#A\u0002pe\u001e,\"aF\u0017\u0014\u0007\u0001Ab\u0004\u0005\u0002\u001a95\t!DC\u0001\u001c\u0003\u0015\u00198-\u00197b\u0013\ti\"D\u0001\u0004B]f\u0014VM\u001a\t\u0004?!ZcB\u0001\u0011'\u001d\t\tS%D\u0001#\u0015\t\u0019C%\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005Y\u0012BA\u0014\u001b\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u000b\u0016\u0003\u0011%#XM]1u_JT!a\n\u000e\u0011\u00051jC\u0002\u0001\u0003\u0006]\u0001\u0011\ra\f\u0002\u0002)F\u0011\u0001g\r\t\u00033EJ!A\r\u000e\u0003\u000f9{G\u000f[5oOB\u0011\u0011\u0004N\u0005\u0003ki\u00111!\u00118z\u0003-iW-\\8ssN#xN]3\u0011\u0005aJT\"\u0001\u0007\n\u0005ib!aC'f[>\u0014\u0018p\u0015;pe\u0016\f!\"\\3n_JLXj\u001c3f!\tit(D\u0001?\u0015\ti\u0001#\u0003\u0002A}\tQQ*Z7peflu\u000eZ3\u0002\u0019Ut'o\u001c7m\u001b\u0016lwN]=\u0011\u0005e\u0019\u0015B\u0001#\u001b\u0005\u0011auN\\4\u0002\u0011Ut'o\u001c7mK\u0012\fAA]3ti\u00061A(\u001b8jiz\"b!\u0013&L\u00196s\u0005c\u0001\u001d\u0001W!)aG\u0002a\u0001o!)1H\u0002a\u0001y!)\u0011I\u0002a\u0001\u0005\")QI\u0002a\u0001=!)aI\u0002a\u0001=\u0005\u0019\"/\u001a7fCN,WK\u001c:pY2lU-\\8ssR\t\u0011\u000b\u0005\u0002\u001a%&\u00111K\u0007\u0002\u0005+:LG/A\u0004iCNtU\r\u001f;\u0016\u0003Y\u0003\"!G,\n\u0005aS\"a\u0002\"p_2,\u0017M\\\u0001\u0005]\u0016DH\u000fF\u0001,\u0003\u0015\u0019Gn\\:f\u0001"
)
public class PartiallyUnrolledIterator implements Iterator {
   private final MemoryStore memoryStore;
   private final MemoryMode memoryMode;
   private final long unrollMemory;
   private Iterator unrolled;
   private final Iterator rest;

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

   public Map toMap(final .less.colon.less ev) {
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

   private void releaseUnrollMemory() {
      this.memoryStore.releaseUnrollMemoryForThisTask(this.memoryMode, this.unrollMemory);
      this.unrolled = null;
   }

   public boolean hasNext() {
      if (this.unrolled == null) {
         return this.rest.hasNext();
      } else if (!this.unrolled.hasNext()) {
         this.releaseUnrollMemory();
         return this.rest.hasNext();
      } else {
         return true;
      }
   }

   public Object next() {
      return this.unrolled != null && this.unrolled.hasNext() ? this.unrolled.next() : this.rest.next();
   }

   public void close() {
      if (this.unrolled != null) {
         this.releaseUnrollMemory();
      }
   }

   public PartiallyUnrolledIterator(final MemoryStore memoryStore, final MemoryMode memoryMode, final long unrollMemory, final Iterator unrolled, final Iterator rest) {
      this.memoryStore = memoryStore;
      this.memoryMode = memoryMode;
      this.unrollMemory = unrollMemory;
      this.unrolled = unrolled;
      this.rest = rest;
      super();
      IterableOnce.$init$(this);
      IterableOnceOps.$init$(this);
      Iterator.$init$(this);
   }
}
