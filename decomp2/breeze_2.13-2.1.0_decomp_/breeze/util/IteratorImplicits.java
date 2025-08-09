package breeze.util;

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
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005M3q!\u0003\u0006\u0011\u0002\u0007\u0005q\u0002C\u0003\u0017\u0001\u0011\u0005qC\u0002\u0003\u001c\u0001\u0001a\u0002\u0002\u0003\u0010\u0003\u0005\u0003\u0005\u000b\u0011B\u0010\t\u000bY\u0012A\u0011A\u001c\t\u000bm\u0012A\u0011\u0001\u001f\t\u000b\t\u0013A\u0011A\"\t\u000b%\u0013A\u0011\u0001&\t\u000b-\u0003A1\u0001'\u0003#%#XM]1u_JLU\u000e\u001d7jG&$8O\u0003\u0002\f\u0019\u0005!Q\u000f^5m\u0015\u0005i\u0011A\u00022sK\u0016TXm\u0001\u0001\u0014\u0005\u0001\u0001\u0002CA\t\u0015\u001b\u0005\u0011\"\"A\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005U\u0011\"AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u00021A\u0011\u0011#G\u0005\u00035I\u0011A!\u00168ji\na!+[2i\u0013R,'/\u0019;peV\u0011Q$L\n\u0003\u0005A\tA!\u001b;feB\u0019\u0001\u0005K\u0016\u000f\u0005\u00052cB\u0001\u0012&\u001b\u0005\u0019#B\u0001\u0013\u000f\u0003\u0019a$o\\8u}%\t1#\u0003\u0002(%\u00059\u0001/Y2lC\u001e,\u0017BA\u0015+\u0005!IE/\u001a:bi>\u0014(BA\u0014\u0013!\taS\u0006\u0004\u0001\u0005\u000b9\u0012!\u0019A\u0018\u0003\u0003Q\u000b\"\u0001M\u001a\u0011\u0005E\t\u0014B\u0001\u001a\u0013\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\u0005\u001b\n\u0005U\u0012\"aA!os\u00061A(\u001b8jiz\"\"\u0001\u000f\u001e\u0011\u0007e\u00121&D\u0001\u0001\u0011\u0015qB\u00011\u0001 \u0003\r!X-\u001a\u000b\u0003?uBQAP\u0003A\u0002}\n\u0011A\u001a\t\u0005#\u0001[\u0003$\u0003\u0002B%\tIa)\u001e8di&|g.M\u0001\u000ei\u0006\\W-\u00169U_^CWM]3\u0015\u0005}!\u0005\"\u0002 \u0007\u0001\u0004)\u0005\u0003B\tAW\u0019\u0003\"!E$\n\u0005!\u0013\"a\u0002\"p_2,\u0017M\\\u0001\u0005Y\u0006\u001cH/F\u0001,\u0003A\u00198-\u00128sS\u000eD\u0017\n^3sCR|'/\u0006\u0002N!R\u0011a*\u0015\t\u0004s\ty\u0005C\u0001\u0017Q\t\u0015q\u0003B1\u00010\u0011\u0015q\u0002\u00021\u0001S!\r\u0001\u0003f\u0014"
)
public interface IteratorImplicits {
   // $FF: synthetic method
   static RichIterator scEnrichIterator$(final IteratorImplicits $this, final Iterator iter) {
      return $this.scEnrichIterator(iter);
   }

   default RichIterator scEnrichIterator(final Iterator iter) {
      return new RichIterator(iter);
   }

   static void $init$(final IteratorImplicits $this) {
   }

   public class RichIterator {
      public final Iterator breeze$util$IteratorImplicits$RichIterator$$iter;
      // $FF: synthetic field
      public final IteratorImplicits $outer;

      public Iterator tee(final Function1 f) {
         return new Iterator(f) {
            // $FF: synthetic field
            private final RichIterator $outer;
            private final Function1 f$1;

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

            public Object maxBy(final Function1 f, final Ordering cmp) {
               return IterableOnceOps.maxBy$(this, f, cmp);
            }

            public Option maxByOption(final Function1 f, final Ordering cmp) {
               return IterableOnceOps.maxByOption$(this, f, cmp);
            }

            public Object minBy(final Function1 f, final Ordering cmp) {
               return IterableOnceOps.minBy$(this, f, cmp);
            }

            public Option minByOption(final Function1 f, final Ordering cmp) {
               return IterableOnceOps.minByOption$(this, f, cmp);
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

            public Object next() {
               Object n = this.$outer.breeze$util$IteratorImplicits$RichIterator$$iter.next();
               this.f$1.apply(n);
               return n;
            }

            public boolean hasNext() {
               return this.$outer.breeze$util$IteratorImplicits$RichIterator$$iter.hasNext();
            }

            public {
               if (RichIterator.this == null) {
                  throw null;
               } else {
                  this.$outer = RichIterator.this;
                  this.f$1 = f$1;
                  IterableOnce.$init$(this);
                  IterableOnceOps.$init$(this);
                  Iterator.$init$(this);
               }
            }
         };
      }

      public Iterator takeUpToWhere(final Function1 f) {
         return new Iterator(f) {
            private boolean done;
            // $FF: synthetic field
            private final RichIterator $outer;
            private final Function1 f$2;

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

            public Object maxBy(final Function1 f, final Ordering cmp) {
               return IterableOnceOps.maxBy$(this, f, cmp);
            }

            public Option maxByOption(final Function1 f, final Ordering cmp) {
               return IterableOnceOps.maxByOption$(this, f, cmp);
            }

            public Object minBy(final Function1 f, final Ordering cmp) {
               return IterableOnceOps.minBy$(this, f, cmp);
            }

            public Option minByOption(final Function1 f, final Ordering cmp) {
               return IterableOnceOps.minByOption$(this, f, cmp);
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

            private boolean done() {
               return this.done;
            }

            private void done_$eq(final boolean x$1) {
               this.done = x$1;
            }

            public Object next() {
               if (this.done()) {
                  throw new NoSuchElementException();
               } else {
                  Object n = this.$outer.breeze$util$IteratorImplicits$RichIterator$$iter.next();
                  this.done_$eq(BoxesRunTime.unboxToBoolean(this.f$2.apply(n)));
                  return n;
               }
            }

            public boolean hasNext() {
               return !this.done() && this.$outer.breeze$util$IteratorImplicits$RichIterator$$iter.hasNext();
            }

            public {
               if (RichIterator.this == null) {
                  throw null;
               } else {
                  this.$outer = RichIterator.this;
                  this.f$2 = f$2;
                  IterableOnce.$init$(this);
                  IterableOnceOps.$init$(this);
                  Iterator.$init$(this);
                  this.done = false;
               }
            }
         };
      }

      public Object last() {
         Object x;
         for(x = this.breeze$util$IteratorImplicits$RichIterator$$iter.next(); this.breeze$util$IteratorImplicits$RichIterator$$iter.hasNext(); x = this.breeze$util$IteratorImplicits$RichIterator$$iter.next()) {
         }

         return x;
      }

      // $FF: synthetic method
      public IteratorImplicits breeze$util$IteratorImplicits$RichIterator$$$outer() {
         return this.$outer;
      }

      public RichIterator(final Iterator iter) {
         this.breeze$util$IteratorImplicits$RichIterator$$iter = iter;
         if (IteratorImplicits.this == null) {
            throw null;
         } else {
            this.$outer = IteratorImplicits.this;
            super();
         }
      }
   }
}
