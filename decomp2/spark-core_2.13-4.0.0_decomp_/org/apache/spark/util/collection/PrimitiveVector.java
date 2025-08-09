package org.apache.spark.util.collection;

import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Predef.;
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
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mc!B\r\u001b\u0001y!\u0003\u0002\u0003\u0017\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0018\t\u0011E\u0002!1!Q\u0001\fIBQa\u0017\u0001\u0005\u0002qCqA\u0019\u0001A\u0002\u0013%1\rC\u0004e\u0001\u0001\u0007I\u0011B3\t\r-\u0004\u0001\u0015)\u0003/\u0011%a\u0007\u00011AA\u0002\u0013%Q\u000eC\u0005r\u0001\u0001\u0007\t\u0019!C\u0005e\"IA\u000f\u0001a\u0001\u0002\u0003\u0006KA\u001c\u0005\u0006k\u0002!\tA\u001e\u0005\u0006s\u0002!\tA\u001f\u0005\u0006{\u0002!\ta\u0019\u0005\u0006}\u0002!\ta\u0019\u0005\u0006\u007f\u0002!\ta\u0019\u0005\b\u0003\u0003\u0001A\u0011AA\u0002\u0011\u0019\t\u0019\u0002\u0001C\u0001[\"9\u0011Q\u0003\u0001\u0005\u0002\u0005]\u0001bBA\r\u0001\u0011\u0005\u00111\u0004\u0005\u0007\u0003C\u0001A\u0011A7\t\u000f\u0005\r\u0002\u0001\"\u0003\u0002&\u001dQ\u0011\u0011\u0006\u000e\u0002\u0002#\u0005a$a\u000b\u0007\u0013eQ\u0012\u0011!E\u0001=\u00055\u0002BB.\u0017\t\u0003\ty\u0003C\u0005\u00022Y\t\n\u0011\"\u0001\u00024\ty\u0001K]5nSRLg/\u001a,fGR|'O\u0003\u0002\u001c9\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0005uq\u0012\u0001B;uS2T!a\b\u0011\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0005\u0012\u0013AB1qC\u000eDWMC\u0001$\u0003\ry'oZ\u000b\u0003Ki\u001a\"\u0001\u0001\u0014\u0011\u0005\u001dRS\"\u0001\u0015\u000b\u0003%\nQa]2bY\u0006L!a\u000b\u0015\u0003\r\u0005s\u0017PU3g\u0003-Ig.\u001b;jC2\u001c\u0016N_3\u0004\u0001A\u0011qeL\u0005\u0003a!\u00121!\u00138u\u0003))g/\u001b3f]\u000e,G%\r\t\u0004gYBT\"\u0001\u001b\u000b\u0005UB\u0013a\u0002:fM2,7\r^\u0005\u0003oQ\u0012\u0001b\u00117bgN$\u0016m\u001a\t\u0003sib\u0001\u0001B\u0005<\u0001\u0001\u0006\t\u0011!b\u0001y\t\ta+\u0005\u0002>\u0001B\u0011qEP\u0005\u0003\u007f!\u0012qAT8uQ&tw\r\u0005\u0002(\u0003&\u0011!\t\u000b\u0002\u0004\u0003:L\b&\u0002\u001eE\u000fF3\u0006CA\u0014F\u0013\t1\u0005FA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017'B\u0012I\u0013.SeBA\u0014J\u0013\tQ\u0005&\u0001\u0003M_:<\u0017\u0007\u0002\u0013M!&r!!\u0014)\u000e\u00039S!aT\u0017\u0002\rq\u0012xn\u001c;?\u0013\u0005I\u0013'B\u0012S'V#fBA\u0014T\u0013\t!\u0006&A\u0002J]R\fD\u0001\n'QSE*1e\u0016-[3:\u0011q\u0005W\u0005\u00033\"\na\u0001R8vE2,\u0017\u0007\u0002\u0013M!&\na\u0001P5oSRtDCA/b)\tq\u0006\rE\u0002`\u0001aj\u0011A\u0007\u0005\u0006c\r\u0001\u001dA\r\u0005\bY\r\u0001\n\u00111\u0001/\u00031yf.^7FY\u0016lWM\u001c;t+\u0005q\u0013\u0001E0ok6,E.Z7f]R\u001cx\fJ3r)\t1\u0017\u000e\u0005\u0002(O&\u0011\u0001\u000e\u000b\u0002\u0005+:LG\u000fC\u0004k\u000b\u0005\u0005\t\u0019\u0001\u0018\u0002\u0007a$\u0013'A\u0007`]VlW\t\\3nK:$8\u000fI\u0001\u0007?\u0006\u0014(/Y=\u0016\u00039\u00042aJ89\u0013\t\u0001\bFA\u0003BeJ\f\u00170\u0001\u0006`CJ\u0014\u0018-_0%KF$\"AZ:\t\u000f)D\u0011\u0011!a\u0001]\u00069q,\u0019:sCf\u0004\u0013!B1qa2LHC\u0001\u001dx\u0011\u0015A(\u00021\u0001/\u0003\u0015Ig\u000eZ3y\u0003!!\u0003\u000f\\;tI\u0015\fHC\u00014|\u0011\u0015a8\u00021\u00019\u0003\u00151\u0018\r\\;f\u0003!\u0019\u0017\r]1dSRL\u0018A\u00027f]\u001e$\b.\u0001\u0003tSj,\u0017\u0001C5uKJ\fGo\u001c:\u0016\u0005\u0005\u0015\u0001#BA\u0004\u0003\u001bAdb\u0001'\u0002\n%\u0019\u00111\u0002\u0015\u0002\u000fA\f7m[1hK&!\u0011qBA\t\u0005!IE/\u001a:bi>\u0014(bAA\u0006Q\u0005)\u0011M\u001d:bs\u0006!AO]5n)\u0005q\u0016A\u0002:fg&TX\rF\u0002_\u0003;Aa!a\b\u0013\u0001\u0004q\u0013!\u00038fo2+gn\u001a;i\u0003\u001d!x.\u0011:sCf\f1cY8qs\u0006\u0013(/Y=XSRDG*\u001a8hi\"$2A\\A\u0014\u0011\u0015qH\u00031\u0001/\u0003=\u0001&/[7ji&4XMV3di>\u0014\bCA0\u0017'\t1b\u0005\u0006\u0002\u0002,\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIE*B!!\u000e\u0002LU\u0011\u0011q\u0007\u0016\u0004]\u0005e2FAA\u001e!\u0011\ti$a\u0012\u000e\u0005\u0005}\"\u0002BA!\u0003\u0007\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005\u0015\u0003&\u0001\u0006b]:|G/\u0019;j_:LA!!\u0013\u0002@\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u0013mB\u0002\u0015!A\u0001\u0006\u0004a\u0004&CA&\t\u0006=\u00131KA,c\u0019\u0019\u0003*SA)\u0015F\"A\u0005\u0014)*c\u0019\u0019#kUA+)F\"A\u0005\u0014)*c\u0019\u0019s\u000bWA-3F\"A\u0005\u0014)*\u0001"
)
public class PrimitiveVector {
   public final ClassTag org$apache$spark$util$collection$PrimitiveVector$$evidence$1;
   public int org$apache$spark$util$collection$PrimitiveVector$$_numElements;
   public Object _array;

   public static int $lessinit$greater$default$1() {
      return PrimitiveVector$.MODULE$.$lessinit$greater$default$1();
   }

   public int org$apache$spark$util$collection$PrimitiveVector$$_numElements() {
      return this.org$apache$spark$util$collection$PrimitiveVector$$_numElements;
   }

   public void org$apache$spark$util$collection$PrimitiveVector$$_numElements_$eq(final int x$1) {
      this.org$apache$spark$util$collection$PrimitiveVector$$_numElements = x$1;
   }

   public Object _array() {
      return this._array;
   }

   public void _array_$eq(final Object x$1) {
      this._array = x$1;
   }

   public Object apply(final int index) {
      .MODULE$.require(index < this.org$apache$spark$util$collection$PrimitiveVector$$_numElements());
      return scala.runtime.ScalaRunTime..MODULE$.array_apply(this._array(), index);
   }

   public void $plus$eq(final Object value) {
      if (this.org$apache$spark$util$collection$PrimitiveVector$$_numElements() == scala.runtime.ScalaRunTime..MODULE$.array_length(this._array())) {
         this.resize(scala.runtime.ScalaRunTime..MODULE$.array_length(this._array()) * 2);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      scala.runtime.ScalaRunTime..MODULE$.array_update(this._array(), this.org$apache$spark$util$collection$PrimitiveVector$$_numElements(), value);
      this.org$apache$spark$util$collection$PrimitiveVector$$_numElements_$eq(this.org$apache$spark$util$collection$PrimitiveVector$$_numElements() + 1);
   }

   public int capacity() {
      return scala.runtime.ScalaRunTime..MODULE$.array_length(this._array());
   }

   public int length() {
      return this.org$apache$spark$util$collection$PrimitiveVector$$_numElements();
   }

   public int size() {
      return this.org$apache$spark$util$collection$PrimitiveVector$$_numElements();
   }

   public Iterator iterator() {
      return new Iterator() {
         private int index;
         // $FF: synthetic field
         private final PrimitiveVector $outer;

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

         private int index() {
            return this.index;
         }

         private void index_$eq(final int x$1) {
            this.index = x$1;
         }

         public boolean hasNext() {
            return this.index() < this.$outer.org$apache$spark$util$collection$PrimitiveVector$$_numElements();
         }

         public Object next() {
            if (!this.hasNext()) {
               throw new NoSuchElementException();
            } else {
               Object value = scala.runtime.ScalaRunTime..MODULE$.array_apply(this.$outer._array(), this.index());
               this.index_$eq(this.index() + 1);
               return value;
            }
         }

         public {
            if (PrimitiveVector.this == null) {
               throw null;
            } else {
               this.$outer = PrimitiveVector.this;
               IterableOnce.$init$(this);
               IterableOnceOps.$init$(this);
               Iterator.$init$(this);
               this.index = 0;
            }
         }
      };
   }

   public Object array() {
      return this._array();
   }

   public PrimitiveVector trim() {
      return this.resize(this.size());
   }

   public PrimitiveVector resize(final int newLength) {
      this._array_$eq(this.copyArrayWithLength(newLength));
      if (newLength < this.org$apache$spark$util$collection$PrimitiveVector$$_numElements()) {
         this.org$apache$spark$util$collection$PrimitiveVector$$_numElements_$eq(newLength);
      }

      return this;
   }

   public Object toArray() {
      return this.copyArrayWithLength(this.size());
   }

   public Object copyArrayWithLength(final int length) {
      Object copy = this.org$apache$spark$util$collection$PrimitiveVector$$evidence$1.newArray(length);
      scala.collection.ArrayOps..MODULE$.copyToArray$extension(.MODULE$.genericArrayOps(this._array()), copy);
      return copy;
   }

   public double[] _array$mcD$sp() {
      return (double[])this._array();
   }

   public int[] _array$mcI$sp() {
      return (int[])this._array();
   }

   public long[] _array$mcJ$sp() {
      return (long[])this._array();
   }

   public void _array$mcD$sp_$eq(final double[] x$1) {
      this._array_$eq(x$1);
   }

   public void _array$mcI$sp_$eq(final int[] x$1) {
      this._array_$eq(x$1);
   }

   public void _array$mcJ$sp_$eq(final long[] x$1) {
      this._array_$eq(x$1);
   }

   public double apply$mcD$sp(final int index) {
      return BoxesRunTime.unboxToDouble(this.apply(index));
   }

   public int apply$mcI$sp(final int index) {
      return BoxesRunTime.unboxToInt(this.apply(index));
   }

   public long apply$mcJ$sp(final int index) {
      return BoxesRunTime.unboxToLong(this.apply(index));
   }

   public void $plus$eq$mcD$sp(final double value) {
      this.$plus$eq(BoxesRunTime.boxToDouble(value));
   }

   public void $plus$eq$mcI$sp(final int value) {
      this.$plus$eq(BoxesRunTime.boxToInteger(value));
   }

   public void $plus$eq$mcJ$sp(final long value) {
      this.$plus$eq(BoxesRunTime.boxToLong(value));
   }

   public double[] array$mcD$sp() {
      return (double[])this.array();
   }

   public int[] array$mcI$sp() {
      return (int[])this.array();
   }

   public long[] array$mcJ$sp() {
      return (long[])this.array();
   }

   public PrimitiveVector trim$mcD$sp() {
      return this.trim();
   }

   public PrimitiveVector trim$mcI$sp() {
      return this.trim();
   }

   public PrimitiveVector trim$mcJ$sp() {
      return this.trim();
   }

   public PrimitiveVector resize$mcD$sp(final int newLength) {
      return this.resize(newLength);
   }

   public PrimitiveVector resize$mcI$sp(final int newLength) {
      return this.resize(newLength);
   }

   public PrimitiveVector resize$mcJ$sp(final int newLength) {
      return this.resize(newLength);
   }

   public double[] toArray$mcD$sp() {
      return (double[])this.toArray();
   }

   public int[] toArray$mcI$sp() {
      return (int[])this.toArray();
   }

   public long[] toArray$mcJ$sp() {
      return (long[])this.toArray();
   }

   public double[] copyArrayWithLength$mcD$sp(final int length) {
      return (double[])this.copyArrayWithLength(length);
   }

   public int[] copyArrayWithLength$mcI$sp(final int length) {
      return (int[])this.copyArrayWithLength(length);
   }

   public long[] copyArrayWithLength$mcJ$sp(final int length) {
      return (long[])this.copyArrayWithLength(length);
   }

   public boolean specInstance$() {
      return false;
   }

   public PrimitiveVector(final int initialSize, final ClassTag evidence$1) {
      this.org$apache$spark$util$collection$PrimitiveVector$$evidence$1 = evidence$1;
      if (!this.specInstance$()) {
         this.org$apache$spark$util$collection$PrimitiveVector$$_numElements = 0;
         this._array_$eq(evidence$1.newArray(initialSize));
      }

   }
}
