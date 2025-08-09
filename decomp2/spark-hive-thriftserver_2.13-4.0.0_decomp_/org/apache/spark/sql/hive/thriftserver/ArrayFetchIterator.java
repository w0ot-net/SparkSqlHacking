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
   bytes = "\u0006\u0005u3Qa\u0004\t\u0001%qA\u0001\u0002\u000e\u0001\u0003\u0002\u0003\u0006I!\u000e\u0005\u0006q\u0001!\t!\u000f\u0005\by\u0001\u0001\r\u0011\"\u0003>\u0011\u001d\t\u0005\u00011A\u0005\n\tCa\u0001\u0013\u0001!B\u0013q\u0004bB%\u0001\u0001\u0004%I!\u0010\u0005\b\u0015\u0002\u0001\r\u0011\"\u0003L\u0011\u0019i\u0005\u0001)Q\u0005}!)a\n\u0001C!\u001f\")\u0001\u000b\u0001C!#\")A\u000b\u0001C!{!)Q\u000b\u0001C!{!)a\u000b\u0001C!/\")1\f\u0001C!9\n\u0011\u0012I\u001d:bs\u001a+Go\u00195Ji\u0016\u0014\u0018\r^8s\u0015\t\t\"#\u0001\u0007uQJLg\r^:feZ,'O\u0003\u0002\u0014)\u0005!\u0001.\u001b<f\u0015\t)b#A\u0002tc2T!a\u0006\r\u0002\u000bM\u0004\u0018M]6\u000b\u0005eQ\u0012AB1qC\u000eDWMC\u0001\u001c\u0003\ry'oZ\u000b\u0003;)\u001a2\u0001\u0001\u0010%!\ty\"%D\u0001!\u0015\u0005\t\u0013!B:dC2\f\u0017BA\u0012!\u0005\u0019\te.\u001f*fMB\u0019QE\n\u0015\u000e\u0003AI!a\n\t\u0003\u001b\u0019+Go\u00195Ji\u0016\u0014\u0018\r^8s!\tI#\u0006\u0004\u0001\u0005\u000b-\u0002!\u0019A\u0017\u0003\u0003\u0005\u001b\u0001!\u0005\u0002/cA\u0011qdL\u0005\u0003a\u0001\u0012qAT8uQ&tw\r\u0005\u0002 e%\u00111\u0007\t\u0002\u0004\u0003:L\u0018aA:sGB\u0019qD\u000e\u0015\n\u0005]\u0002#!B!se\u0006L\u0018A\u0002\u001fj]&$h\b\u0006\u0002;wA\u0019Q\u0005\u0001\u0015\t\u000bQ\u0012\u0001\u0019A\u001b\u0002\u0015\u0019,Go\u00195Ti\u0006\u0014H/F\u0001?!\tyr(\u0003\u0002AA\t!Aj\u001c8h\u000391W\r^2i'R\f'\u000f^0%KF$\"a\u0011$\u0011\u0005}!\u0015BA#!\u0005\u0011)f.\u001b;\t\u000f\u001d#\u0011\u0011!a\u0001}\u0005\u0019\u0001\u0010J\u0019\u0002\u0017\u0019,Go\u00195Ti\u0006\u0014H\u000fI\u0001\ta>\u001c\u0018\u000e^5p]\u0006a\u0001o\\:ji&|gn\u0018\u0013fcR\u00111\t\u0014\u0005\b\u000f\u001e\t\t\u00111\u0001?\u0003%\u0001xn]5uS>t\u0007%A\u0005gKR\u001c\u0007NT3yiR\t1)A\u0007gKR\u001c\u0007.\u00112t_2,H/\u001a\u000b\u0003\u0007JCQa\u0015\u0006A\u0002y\n1\u0001]8t\u000359W\r\u001e$fi\u000eD7\u000b^1si\u0006Yq-\u001a;Q_NLG/[8o\u0003\u001dA\u0017m\u001d(fqR,\u0012\u0001\u0017\t\u0003?eK!A\u0017\u0011\u0003\u000f\t{w\u000e\\3b]\u0006!a.\u001a=u)\u0005A\u0003"
)
public class ArrayFetchIterator implements FetchIterator {
   private final Object src;
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
      this.position_$eq(scala.runtime.RichLong..MODULE$.min$extension(scala.Predef..MODULE$.longWrapper(scala.runtime.RichLong..MODULE$.max$extension(scala.Predef..MODULE$.longWrapper(pos), 0L)), (long)scala.runtime.ScalaRunTime..MODULE$.array_length(this.src)));
      this.fetchStart_$eq(this.position());
   }

   public long getFetchStart() {
      return this.fetchStart();
   }

   public long getPosition() {
      return this.position();
   }

   public boolean hasNext() {
      return this.position() < (long)scala.runtime.ScalaRunTime..MODULE$.array_length(this.src);
   }

   public Object next() {
      this.position_$eq(this.position() + 1L);
      return scala.runtime.ScalaRunTime..MODULE$.array_apply(this.src, (int)this.position() - 1);
   }

   public ArrayFetchIterator(final Object src) {
      this.src = src;
      IterableOnce.$init$(this);
      IterableOnceOps.$init$(this);
      Iterator.$init$(this);
      FetchIterator.$init$(this);
      this.fetchStart = 0L;
      this.position = 0L;
   }
}
