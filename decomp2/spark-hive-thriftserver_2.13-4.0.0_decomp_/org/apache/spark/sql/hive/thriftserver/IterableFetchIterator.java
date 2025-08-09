package org.apache.spark.sql.hive.thriftserver;

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
   bytes = "\u0006\u0005Q4Qa\u0005\u000b\u0001-\u0001B\u0001\u0002\u000f\u0001\u0003\u0002\u0003\u0006I!\u000f\u0005\u0006\u000b\u0002!\tA\u0012\u0005\b\u0013\u0002\u0001\r\u0011\"\u0003K\u0011\u001dq\u0005\u00011A\u0005\n=Ca!\u0016\u0001!B\u0013Y\u0005b\u0002,\u0001\u0001\u0004%Ia\u0016\u0005\b7\u0002\u0001\r\u0011\"\u0003]\u0011\u0019q\u0006\u0001)Q\u00051\"9q\f\u0001a\u0001\n\u00139\u0006b\u00021\u0001\u0001\u0004%I!\u0019\u0005\u0007G\u0002\u0001\u000b\u0015\u0002-\t\u000b\u0011\u0004A\u0011I3\t\u000b\u0019\u0004A\u0011I4\t\u000b)\u0004A\u0011I,\t\u000b-\u0004A\u0011I,\t\u000b1\u0004A\u0011I7\t\u000bE\u0004A\u0011\t:\t\u000bM\u0004A\u0011B3\u0003+%#XM]1cY\u00164U\r^2i\u0013R,'/\u0019;pe*\u0011QCF\u0001\ri\"\u0014\u0018N\u001a;tKJ4XM\u001d\u0006\u0003/a\tA\u0001[5wK*\u0011\u0011DG\u0001\u0004gFd'BA\u000e\u001d\u0003\u0015\u0019\b/\u0019:l\u0015\tib$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002?\u0005\u0019qN]4\u0016\u0005\u0005r3c\u0001\u0001#QA\u00111EJ\u0007\u0002I)\tQ%A\u0003tG\u0006d\u0017-\u0003\u0002(I\t1\u0011I\\=SK\u001a\u00042!\u000b\u0016-\u001b\u0005!\u0012BA\u0016\u0015\u000551U\r^2i\u0013R,'/\u0019;peB\u0011QF\f\u0007\u0001\t\u0015y\u0003A1\u00012\u0005\u0005\t5\u0001A\t\u0003eU\u0002\"aI\u001a\n\u0005Q\"#a\u0002(pi\"Lgn\u001a\t\u0003GYJ!a\u000e\u0013\u0003\u0007\u0005s\u00170\u0001\u0005ji\u0016\u0014\u0018M\u00197f!\rQ$\t\f\b\u0003w\u0001s!\u0001P \u000e\u0003uR!A\u0010\u0019\u0002\rq\u0012xn\u001c;?\u0013\u0005)\u0013BA!%\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u0011#\u0003\u0011%#XM]1cY\u0016T!!\u0011\u0013\u0002\rqJg.\u001b;?)\t9\u0005\nE\u0002*\u00011BQ\u0001\u000f\u0002A\u0002e\nA!\u001b;feV\t1\nE\u0002;\u00192J!!\u0014#\u0003\u0011%#XM]1u_J\f\u0001\"\u001b;fe~#S-\u001d\u000b\u0003!N\u0003\"aI)\n\u0005I##\u0001B+oSRDq\u0001\u0016\u0003\u0002\u0002\u0003\u00071*A\u0002yIE\nQ!\u001b;fe\u0002\n!BZ3uG\"\u001cF/\u0019:u+\u0005A\u0006CA\u0012Z\u0013\tQFE\u0001\u0003M_:<\u0017A\u00044fi\u000eD7\u000b^1si~#S-\u001d\u000b\u0003!vCq\u0001V\u0004\u0002\u0002\u0003\u0007\u0001,A\u0006gKR\u001c\u0007n\u0015;beR\u0004\u0013\u0001\u00039pg&$\u0018n\u001c8\u0002\u0019A|7/\u001b;j_:|F%Z9\u0015\u0005A\u0013\u0007b\u0002+\u000b\u0003\u0003\u0005\r\u0001W\u0001\na>\u001c\u0018\u000e^5p]\u0002\n\u0011BZ3uG\"tU\r\u001f;\u0015\u0003A\u000bQBZ3uG\"\f%m]8mkR,GC\u0001)i\u0011\u0015IW\u00021\u0001Y\u0003\r\u0001xn]\u0001\u000eO\u0016$h)\u001a;dQN#\u0018M\u001d;\u0002\u0017\u001d,G\u000fU8tSRLwN\\\u0001\bQ\u0006\u001ch*\u001a=u+\u0005q\u0007CA\u0012p\u0013\t\u0001HEA\u0004C_>dW-\u00198\u0002\t9,\u0007\u0010\u001e\u000b\u0002Y\u0005i!/Z:fiB{7/\u001b;j_:\u0004"
)
public class IterableFetchIterator implements FetchIterator {
   private final Iterable iterable;
   private Iterator iter;
   private long fetchStart;
   private long position;

   public void fetchPrior(final long offset) {
      FetchIterator.fetchPrior$(this, offset);
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

   private Iterator iter() {
      return this.iter;
   }

   private void iter_$eq(final Iterator x$1) {
      this.iter = x$1;
   }

   private long fetchStart() {
      return this.fetchStart;
   }

   private void fetchStart_$eq(final long x$1) {
      this.fetchStart = x$1;
   }

   private long position() {
      return this.position;
   }

   private void position_$eq(final long x$1) {
      this.position = x$1;
   }

   public void fetchNext() {
      this.fetchStart_$eq(this.position());
   }

   public void fetchAbsolute(final long pos) {
      long newPos = scala.runtime.RichLong..MODULE$.max$extension(scala.Predef..MODULE$.longWrapper(pos), 0L);
      if (newPos < this.position()) {
         this.resetPosition();
      }

      while(this.position() < newPos && this.hasNext()) {
         this.next();
      }

      this.fetchStart_$eq(this.position());
   }

   public long getFetchStart() {
      return this.fetchStart();
   }

   public long getPosition() {
      return this.position();
   }

   public boolean hasNext() {
      return this.iter().hasNext();
   }

   public Object next() {
      this.position_$eq(this.position() + 1L);
      return this.iter().next();
   }

   private void resetPosition() {
      if (this.position() != 0L) {
         this.iter_$eq(this.iterable.iterator());
         this.position_$eq(0L);
         this.fetchStart_$eq(0L);
      }
   }

   public IterableFetchIterator(final Iterable iterable) {
      this.iterable = iterable;
      IterableOnce.$init$(this);
      IterableOnceOps.$init$(this);
      Iterator.$init$(this);
      FetchIterator.$init$(this);
      this.iter = iterable.iterator();
      this.fetchStart = 0L;
      this.position = 0L;
   }
}
