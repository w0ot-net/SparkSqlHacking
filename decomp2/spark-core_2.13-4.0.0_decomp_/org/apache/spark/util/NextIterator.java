package org.apache.spark.util;

import java.util.NoSuchElementException;
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
   bytes = "\u0006\u0005\u00114aa\u0005\u000b\u0002\u0002Ya\u0002\"\u0002\u001f\u0001\t\u0003i\u0004b\u0002!\u0001\u0001\u0004%I!\u0011\u0005\b\u000b\u0002\u0001\r\u0011\"\u0003G\u0011\u0019a\u0005\u0001)Q\u0005\u0005\"IQ\n\u0001a\u0001\u0002\u0004%IA\u0014\u0005\n\u001f\u0002\u0001\r\u00111A\u0005\nAC\u0011B\u0015\u0001A\u0002\u0003\u0005\u000b\u0015B\u0019\t\u000fM\u0003\u0001\u0019!C\u0005\u0003\"9A\u000b\u0001a\u0001\n\u0013)\u0006BB,\u0001A\u0003&!\tC\u0004Y\u0001\u0001\u0007I\u0011C!\t\u000fe\u0003\u0001\u0019!C\t5\"1A\f\u0001Q!\n\tCQ!\u0018\u0001\u0007\u0012yCQa\u0018\u0001\u0007\u0012\u0001DQ!\u0019\u0001\u0005\u0002\u0001DQA\u0019\u0001\u0005B\u0005CQa\u0019\u0001\u0005By\u0013ABT3yi&#XM]1u_JT!!\u0006\f\u0002\tU$\u0018\u000e\u001c\u0006\u0003/a\tQa\u001d9be.T!!\u0007\u000e\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005Y\u0012aA8sOV\u0011QdM\n\u0004\u0001y!\u0003CA\u0010#\u001b\u0005\u0001#\"A\u0011\u0002\u000bM\u001c\u0017\r\\1\n\u0005\r\u0002#AB!osJ+g\rE\u0002&]Er!A\n\u0017\u000f\u0005\u001dZS\"\u0001\u0015\u000b\u0005%R\u0013A\u0002\u001fs_>$hh\u0001\u0001\n\u0003\u0005J!!\f\u0011\u0002\u000fA\f7m[1hK&\u0011q\u0006\r\u0002\t\u0013R,'/\u0019;pe*\u0011Q\u0006\t\t\u0003eMb\u0001\u0001B\u00035\u0001\t\u0007QGA\u0001V#\t1\u0014\b\u0005\u0002 o%\u0011\u0001\b\t\u0002\b\u001d>$\b.\u001b8h!\ty\"(\u0003\u0002<A\t\u0019\u0011I\\=\u0002\rqJg.\u001b;?)\u0005q\u0004cA \u0001c5\tA#A\u0004h_RtU\r\u001f;\u0016\u0003\t\u0003\"aH\"\n\u0005\u0011\u0003#a\u0002\"p_2,\u0017M\\\u0001\fO>$h*\u001a=u?\u0012*\u0017\u000f\u0006\u0002H\u0015B\u0011q\u0004S\u0005\u0003\u0013\u0002\u0012A!\u00168ji\"91jAA\u0001\u0002\u0004\u0011\u0015a\u0001=%c\u0005Aqm\u001c;OKb$\b%A\u0005oKb$h+\u00197vKV\t\u0011'A\u0007oKb$h+\u00197vK~#S-\u001d\u000b\u0003\u000fFCqa\u0013\u0004\u0002\u0002\u0003\u0007\u0011'\u0001\u0006oKb$h+\u00197vK\u0002\naa\u00197pg\u0016$\u0017AC2m_N,Gm\u0018\u0013fcR\u0011qI\u0016\u0005\b\u0017&\t\t\u00111\u0001C\u0003\u001d\u0019Gn\\:fI\u0002\n\u0001BZ5oSNDW\rZ\u0001\rM&t\u0017n\u001d5fI~#S-\u001d\u000b\u0003\u000fnCqa\u0013\u0007\u0002\u0002\u0003\u0007!)A\u0005gS:L7\u000f[3eA\u00059q-\u001a;OKb$H#A\u0019\u0002\u000b\rdwn]3\u0015\u0003\u001d\u000bQb\u00197pg\u0016LeMT3fI\u0016$\u0017a\u00025bg:+\u0007\u0010^\u0001\u0005]\u0016DH\u000f"
)
public abstract class NextIterator implements Iterator {
   private boolean gotNext;
   private Object nextValue;
   private boolean closed;
   private boolean finished;

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

   private boolean gotNext() {
      return this.gotNext;
   }

   private void gotNext_$eq(final boolean x$1) {
      this.gotNext = x$1;
   }

   private Object nextValue() {
      return this.nextValue;
   }

   private void nextValue_$eq(final Object x$1) {
      this.nextValue = x$1;
   }

   private boolean closed() {
      return this.closed;
   }

   private void closed_$eq(final boolean x$1) {
      this.closed = x$1;
   }

   public boolean finished() {
      return this.finished;
   }

   public void finished_$eq(final boolean x$1) {
      this.finished = x$1;
   }

   public abstract Object getNext();

   public abstract void close();

   public void closeIfNeeded() {
      if (!this.closed()) {
         this.closed_$eq(true);
         this.close();
      }
   }

   public boolean hasNext() {
      if (!this.finished() && !this.gotNext()) {
         this.nextValue_$eq(this.getNext());
         if (this.finished()) {
            this.closeIfNeeded();
         }

         this.gotNext_$eq(true);
      }

      return !this.finished();
   }

   public Object next() {
      if (!this.hasNext()) {
         throw new NoSuchElementException("End of stream");
      } else {
         this.gotNext_$eq(false);
         return this.nextValue();
      }
   }

   public NextIterator() {
      IterableOnce.$init$(this);
      IterableOnceOps.$init$(this);
      Iterator.$init$(this);
      this.gotNext = false;
      this.closed = false;
      this.finished = false;
   }
}
