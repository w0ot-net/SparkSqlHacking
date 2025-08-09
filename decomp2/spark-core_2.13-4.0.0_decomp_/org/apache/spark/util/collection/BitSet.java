package org.apache.spark.util.collection;

import java.io.Serializable;
import java.util.Arrays;
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
import scala.math.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ea\u0001B\r\u001b\u0001\u0015B\u0001\u0002\u000f\u0001\u0003\u0002\u0003\u0006I!\u000f\u0005\u0006y\u0001!\t!\u0010\u0005\b\u0003\u0002\u0011\r\u0011\"\u0003C\u0011\u0019I\u0005\u0001)A\u0005\u0007\"9!\n\u0001b\u0001\n\u0013Y\u0005B\u0002'\u0001A\u0003%\u0011\bC\u0003N\u0001\u0011\u00051\nC\u0003O\u0001\u0011\u0005q\nC\u0003T\u0001\u0011\u0005A\u000bC\u0003X\u0001\u0011\u0005\u0001\fC\u0003[\u0001\u0011\u00051\fC\u0003_\u0001\u0011\u0005q\fC\u0003b\u0001\u0011\u0005!\rC\u0003e\u0001\u0011\u0005Q\rC\u0003h\u0001\u0011\u0005\u0001\u000eC\u0003l\u0001\u0011\u0005A\u000eC\u0003o\u0001\u0011\u0005q\u000eC\u0003u\u0001\u0011\u0005Q\u000fC\u0003z\u0001\u0011\u0005!\u0010C\u0003|\u0001\u0011\u0005A\u0010\u0003\u0004\u0000\u0001\u0011\u0005\u0011\u0011\u0001\u0005\b\u0003\u000b\u0001A\u0011BA\u0004\u0011\u001d\tY\u0001\u0001C!\u0003\u001bAa!a\u0006\u0001\t\u0003R(A\u0002\"jiN+GO\u0003\u0002\u001c9\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0005uq\u0012\u0001B;uS2T!a\b\u0011\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0005\u0012\u0013AB1qC\u000eDWMC\u0001$\u0003\ry'oZ\u0002\u0001'\r\u0001a\u0005\f\t\u0003O)j\u0011\u0001\u000b\u0006\u0002S\u0005)1oY1mC&\u00111\u0006\u000b\u0002\u0007\u0003:L(+\u001a4\u0011\u00055*dB\u0001\u00184\u001d\ty#'D\u00011\u0015\t\tD%\u0001\u0004=e>|GOP\u0005\u0002S%\u0011A\u0007K\u0001\ba\u0006\u001c7.Y4f\u0013\t1tG\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00025Q\u00059a.^7CSR\u001c\bCA\u0014;\u0013\tY\u0004FA\u0002J]R\fa\u0001P5oSRtDC\u0001 A!\ty\u0004!D\u0001\u001b\u0011\u0015A$\u00011\u0001:\u0003\u00159xN\u001d3t+\u0005\u0019\u0005cA\u0014E\r&\u0011Q\t\u000b\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003O\u001dK!\u0001\u0013\u0015\u0003\t1{gnZ\u0001\u0007o>\u0014Hm\u001d\u0011\u0002\u00119,XnV8sIN,\u0012!O\u0001\n]Vlwk\u001c:eg\u0002\n\u0001bY1qC\u000eLG/_\u0001\u0006G2,\u0017M\u001d\u000b\u0002!B\u0011q%U\u0005\u0003%\"\u0012A!\u00168ji\u0006A1/\u001a;V]RLG\u000e\u0006\u0002Q+\")a+\u0003a\u0001s\u0005A!-\u001b;J]\u0012,\u00070\u0001\u0006dY\u0016\f'/\u00168uS2$\"\u0001U-\t\u000bYS\u0001\u0019A\u001d\u0002\t\u0011\nW\u000e\u001d\u000b\u0003}qCQ!X\u0006A\u0002y\nQa\u001c;iKJ\fA\u0001\n2beR\u0011a\b\u0019\u0005\u0006;2\u0001\rAP\u0001\u0004IU\u0004HC\u0001 d\u0011\u0015iV\u00021\u0001?\u0003\u0019\tg\u000e\u001a(piR\u0011aH\u001a\u0005\u0006;:\u0001\rAP\u0001\u0004g\u0016$HC\u0001)j\u0011\u0015Qw\u00021\u0001:\u0003\u0015Ig\u000eZ3y\u0003\u0015)hn]3u)\t\u0001V\u000eC\u0003k!\u0001\u0007\u0011(A\u0002hKR$\"\u0001]:\u0011\u0005\u001d\n\u0018B\u0001:)\u0005\u001d\u0011un\u001c7fC:DQA[\tA\u0002e\n\u0001\"\u001b;fe\u0006$xN]\u000b\u0002mB\u0019Qf^\u001d\n\u0005a<$\u0001C%uKJ\fGo\u001c:\u0002\u0017\r\f'\u000fZ5oC2LG/\u001f\u000b\u0002s\u0005Qa.\u001a=u'\u0016$()\u001b;\u0015\u0005ej\b\"\u0002@\u0015\u0001\u0004I\u0014!\u00034s_6Le\u000eZ3y\u0003\u0015)h.[8o)\r\u0001\u00161\u0001\u0005\u0006;V\u0001\rAP\u0001\nE&$(g^8sIN$2!OA\u0005\u0011\u0015Ad\u00031\u0001:\u0003\u0019)\u0017/^1mgR\u0019\u0001/a\u0004\t\ru;\u0002\u0019AA\t!\r9\u00131C\u0005\u0004\u0003+A#aA!os\u0006A\u0001.Y:i\u0007>$W\r"
)
public class BitSet implements Serializable {
   private final long[] words;
   private final int numWords;

   private long[] words() {
      return this.words;
   }

   private int numWords() {
      return this.numWords;
   }

   public int capacity() {
      return this.numWords() * 64;
   }

   public void clear() {
      Arrays.fill(this.words(), 0L);
   }

   public void setUntil(final int bitIndex) {
      int wordIndex = bitIndex >> 6;
      Arrays.fill(this.words(), 0, wordIndex, -1L);
      if (wordIndex < this.words().length) {
         long mask = ~(-1L << (bitIndex & 63));
         this.words()[wordIndex] |= mask;
      }
   }

   public void clearUntil(final int bitIndex) {
      int wordIndex = bitIndex >> 6;
      Arrays.fill(this.words(), 0, wordIndex, 0L);
      if (wordIndex < this.words().length) {
         long mask = -1L << (bitIndex & 63);
         this.words()[wordIndex] &= mask;
      }
   }

   public BitSet $amp(final BitSet other) {
      BitSet newBS = new BitSet(.MODULE$.max(this.capacity(), other.capacity()));
      int smaller = .MODULE$.min(this.numWords(), other.numWords());
      scala.Predef..MODULE$.assert(newBS.numWords() >= this.numWords());
      scala.Predef..MODULE$.assert(newBS.numWords() >= other.numWords());

      for(int ind = 0; ind < smaller; ++ind) {
         newBS.words()[ind] = this.words()[ind] & other.words()[ind];
      }

      return newBS;
   }

   public BitSet $bar(final BitSet other) {
      BitSet newBS = new BitSet(.MODULE$.max(this.capacity(), other.capacity()));
      scala.Predef..MODULE$.assert(newBS.numWords() >= this.numWords());
      scala.Predef..MODULE$.assert(newBS.numWords() >= other.numWords());
      int smaller = .MODULE$.min(this.numWords(), other.numWords());

      int ind;
      for(ind = 0; ind < smaller; ++ind) {
         newBS.words()[ind] = this.words()[ind] | other.words()[ind];
      }

      while(ind < this.numWords()) {
         newBS.words()[ind] = this.words()[ind];
         ++ind;
      }

      while(ind < other.numWords()) {
         newBS.words()[ind] = other.words()[ind];
         ++ind;
      }

      return newBS;
   }

   public BitSet $up(final BitSet other) {
      BitSet newBS = new BitSet(.MODULE$.max(this.capacity(), other.capacity()));
      int smaller = .MODULE$.min(this.numWords(), other.numWords());

      int ind;
      for(ind = 0; ind < smaller; ++ind) {
         newBS.words()[ind] = this.words()[ind] ^ other.words()[ind];
      }

      if (ind < this.numWords()) {
         scala.Array..MODULE$.copy(this.words(), ind, newBS.words(), ind, this.numWords() - ind);
      }

      if (ind < other.numWords()) {
         scala.Array..MODULE$.copy(other.words(), ind, newBS.words(), ind, other.numWords() - ind);
      }

      return newBS;
   }

   public BitSet andNot(final BitSet other) {
      BitSet newBS = new BitSet(this.capacity());
      int smaller = .MODULE$.min(this.numWords(), other.numWords());

      int ind;
      for(ind = 0; ind < smaller; ++ind) {
         newBS.words()[ind] = this.words()[ind] & ~other.words()[ind];
      }

      if (ind < this.numWords()) {
         scala.Array..MODULE$.copy(this.words(), ind, newBS.words(), ind, this.numWords() - ind);
      }

      return newBS;
   }

   public void set(final int index) {
      long bitmask = 1L << (index & 63);
      int var4 = index >> 6;
      this.words()[var4] |= bitmask;
   }

   public void unset(final int index) {
      long bitmask = 1L << (index & 63);
      int var4 = index >> 6;
      this.words()[var4] &= ~bitmask;
   }

   public boolean get(final int index) {
      long bitmask = 1L << (index & 63);
      return (this.words()[index >> 6] & bitmask) != 0L;
   }

   public Iterator iterator() {
      return new Iterator() {
         private int ind;
         // $FF: synthetic field
         private final BitSet $outer;

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

         private int ind() {
            return this.ind;
         }

         private void ind_$eq(final int x$1) {
            this.ind = x$1;
         }

         public boolean hasNext() {
            return this.ind() >= 0;
         }

         public int next() {
            int tmp = this.ind();
            this.ind_$eq(this.$outer.nextSetBit(this.ind() + 1));
            return tmp;
         }

         public {
            if (BitSet.this == null) {
               throw null;
            } else {
               this.$outer = BitSet.this;
               IterableOnce.$init$(this);
               IterableOnceOps.$init$(this);
               Iterator.$init$(this);
               this.ind = BitSet.this.nextSetBit(0);
            }
         }
      };
   }

   public int cardinality() {
      int sum = 0;

      for(int i = 0; i < this.numWords(); ++i) {
         sum += Long.bitCount(this.words()[i]);
      }

      return sum;
   }

   public int nextSetBit(final int fromIndex) {
      int wordIndex = fromIndex >> 6;
      if (wordIndex >= this.numWords()) {
         return -1;
      } else {
         int subIndex = fromIndex & 63;
         long word = this.words()[wordIndex] >> subIndex;
         if (word != 0L) {
            return (wordIndex << 6) + subIndex + Long.numberOfTrailingZeros(word);
         } else {
            ++wordIndex;

            while(wordIndex < this.numWords()) {
               word = this.words()[wordIndex];
               if (word != 0L) {
                  return (wordIndex << 6) + Long.numberOfTrailingZeros(word);
               }

               ++wordIndex;
            }

            return -1;
         }
      }
   }

   public void union(final BitSet other) {
      scala.Predef..MODULE$.require(this.numWords() <= other.numWords());

      for(int ind = 0; ind < this.numWords(); ++ind) {
         this.words()[ind] |= other.words()[ind];
      }

   }

   private int bit2words(final int numBits) {
      return (numBits - 1 >> 6) + 1;
   }

   public boolean equals(final Object other) {
      if (other instanceof BitSet var4) {
         return Arrays.equals(this.words(), var4.words());
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(this.words());
   }

   public BitSet(final int numBits) {
      this.words = new long[this.bit2words(numBits)];
      this.numWords = this.words().length;
   }
}
