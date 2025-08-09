package scala.collection.parallel.mutable;

import java.lang.invoke.SerializedLambda;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.collection.BufferedIterator;
import scala.collection.DebugUtils$;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.generic.DelegatedSignalling;
import scala.collection.generic.Signalling;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.FlatHashTable;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.AugmentedIterableIterator;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.IterableSplitter;
import scala.collection.parallel.RemainsIterator;
import scala.collection.parallel.SeqSplitter;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%ba\u0002\r\u001a!\u0003\r\tA\t\u0005\u0006q\u0001!\t!\u000f\u0005\u0006{\u0001!\tE\u0010\u0004\u0006\u0005\u0002\t\ta\u0011\u0005\t\u0019\u000e\u0011\t\u0019!C\u0001\u001b\"A\u0011k\u0001BA\u0002\u0013\u0005!\u000b\u0003\u0005V\u0007\t\u0005\t\u0015)\u0003O\u0011!16A!b\u0001\n\u0003i\u0005\u0002C,\u0004\u0005\u0003\u0005\u000b\u0011\u0002(\t\u0011a\u001b!Q1A\u0005\u00025C\u0001\"W\u0002\u0003\u0002\u0003\u0006IA\u0014\u0005\u00065\u000e!\ta\u0017\u0005\u0007C\u000e\u0001\u000b\u0015\u0002(\t\r\t\u001c\u0001\u0015!\u0003d\u0011\u001917\u0001)C\u0005s!)qm\u0001D\u0001Q\")Qn\u0001C\u0001\u001b\")an\u0001C\u0001}!)qn\u0001C\u0001a\")\u0011o\u0001C\u0001e\")1o\u0001C\u0001i\")Ap\u0001C!{\"9\u00111C\u0002\u0005\u0012\u0005U\u0001bBA\u000f\u0007\u0011E\u0011q\u0004\u0002\u0011!\u0006\u0014h\t\\1u\u0011\u0006\u001c\b\u000eV1cY\u0016T!AG\u000e\u0002\u000f5,H/\u00192mK*\u0011A$H\u0001\ta\u0006\u0014\u0018\r\u001c7fY*\u0011adH\u0001\u000bG>dG.Z2uS>t'\"\u0001\u0011\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u00111eL\n\u0004\u0001\u0011B\u0003CA\u0013'\u001b\u0005y\u0012BA\u0014 \u0005\u0019\te.\u001f*fMB\u0019\u0011fK\u0017\u000e\u0003)R!AG\u000f\n\u00051R#!\u0004$mCRD\u0015m\u001d5UC\ndW\r\u0005\u0002/_1\u0001A!\u0002\u0019\u0001\u0005\u0004\t$!\u0001+\u0012\u0005I*\u0004CA\u00134\u0013\t!tDA\u0004O_RD\u0017N\\4\u0011\u0005\u00152\u0014BA\u001c \u0005\r\te._\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003i\u0002\"!J\u001e\n\u0005qz\"\u0001B+oSR\f\u0011#\u00197xCf\u001c\u0018J\\5u'&TX-T1q+\u0005y\u0004CA\u0013A\u0013\t\tuDA\u0004C_>dW-\u00198\u00031A\u000b'O\u00127bi\"\u000b7\u000f\u001b+bE2,\u0017\n^3sCR|'o\u0005\u0003\u0004I\u0011C\u0005cA#G[5\t1$\u0003\u0002H7\t\u0001\u0012\n^3sC\ndWm\u00159mSR$XM\u001d\t\u0003\u0013*k\u0011!G\u0005\u0003\u0017f\u0011AbU5{K6\u000b\u0007/\u0016;jYN\f1!\u001b3y+\u0005q\u0005CA\u0013P\u0013\t\u0001vDA\u0002J]R\fq!\u001b3y?\u0012*\u0017\u000f\u0006\u0002;'\"9A+BA\u0001\u0002\u0004q\u0015a\u0001=%c\u0005!\u0011\u000e\u001a=!\u0003\u0015)h\u000e^5m\u0003\u0019)h\u000e^5mA\u0005IAo\u001c;bYNL'0Z\u0001\u000bi>$\u0018\r\\:ju\u0016\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0003]=~\u0003\u0007CA/\u0004\u001b\u0005\u0001\u0001\"\u0002'\f\u0001\u0004q\u0005\"\u0002,\f\u0001\u0004q\u0005\"\u0002-\f\u0001\u0004q\u0015!\u0003;sCZ,'o]3e\u0003%IG/\u001a:uC\ndW\rE\u0002&I\u0012J!!Z\u0010\u0003\u000b\u0005\u0013(/Y=\u0002\tM\u001c\u0017M\\\u0001\f]\u0016<\u0018\n^3sCR|'\u000f\u0006\u0003ES.d\u0007\"\u00026\u0010\u0001\u0004q\u0015!B5oI\u0016D\b\"\u0002,\u0010\u0001\u0004q\u0005\"\u0002-\u0010\u0001\u0004q\u0015!\u0003:f[\u0006Lg.\u001b8h\u0003\u001dA\u0017m\u001d(fqR\fAA\\3yiR\tQ&A\u0002ekB,\u0012\u0001R\u0001\u0006gBd\u0017\u000e^\u000b\u0002kB\u0019a/\u001f#\u000f\u0005\u0015:\u0018B\u0001= \u0003\u001d\u0001\u0018mY6bO\u0016L!A_>\u0003\u0007M+\u0017O\u0003\u0002y?\u0005\u0001B-\u001a2vO&sgm\u001c:nCRLwN\\\u000b\u0002}B\u0019q0!\u0004\u000f\t\u0005\u0005\u0011\u0011\u0002\t\u0004\u0003\u0007yRBAA\u0003\u0015\r\t9!I\u0001\u0007yI|w\u000e\u001e \n\u0007\u0005-q$\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003\u001f\t\tB\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003\u0017y\u0012AC2pk:$X\t\\3ngR)a*a\u0006\u0002\u001c!1\u0011\u0011\u0004\fA\u00029\u000bAA\u001a:p[\")aK\u0006a\u0001\u001d\u0006\u00012m\\;oi\n+8m[3u'&TXm\u001d\u000b\u0006\u001d\u0006\u0005\u0012Q\u0005\u0005\u0007\u0003G9\u0002\u0019\u0001(\u0002\u0015\u0019\u0014x.\u001c2vG.,G\u000f\u0003\u0004\u0002(]\u0001\rAT\u0001\fk:$\u0018\u000e\u001c2vG.,G\u000f"
)
public interface ParFlatHashTable extends FlatHashTable {
   // $FF: synthetic method
   static boolean alwaysInitSizeMap$(final ParFlatHashTable $this) {
      return $this.alwaysInitSizeMap();
   }

   default boolean alwaysInitSizeMap() {
      return true;
   }

   static void $init$(final ParFlatHashTable $this) {
   }

   public abstract class ParFlatHashTableIterator implements IterableSplitter, SizeMapUtils {
      private int idx;
      private final int until;
      private final int totalsize;
      private int traversed;
      private final Object[] itertable;
      private Signalling signalDelegate;
      // $FF: synthetic field
      public final ParFlatHashTable $outer;

      public int calcNumElems(final int from, final int until, final int tableLength, final int sizeMapBucketSize) {
         return SizeMapUtils.calcNumElems$(this, from, until, tableLength, sizeMapBucketSize);
      }

      public Seq splitWithSignalling() {
         return IterableSplitter.splitWithSignalling$(this);
      }

      public boolean shouldSplitFurther(final scala.collection.parallel.ParIterable coll, final int parallelismLevel) {
         return IterableSplitter.shouldSplitFurther$(this, coll, parallelismLevel);
      }

      public String buildString(final Function1 closure) {
         return IterableSplitter.buildString$(this, closure);
      }

      public IterableSplitter.Taken newTaken(final int until) {
         return IterableSplitter.newTaken$(this, until);
      }

      public IterableSplitter.Taken newSliceInternal(final IterableSplitter.Taken it, final int from1) {
         return IterableSplitter.newSliceInternal$(this, it, from1);
      }

      public IterableSplitter drop(final int n) {
         return IterableSplitter.drop$(this, n);
      }

      public IterableSplitter take(final int n) {
         return IterableSplitter.take$(this, n);
      }

      public IterableSplitter slice(final int from1, final int until1) {
         return IterableSplitter.slice$(this, from1, until1);
      }

      public IterableSplitter map(final Function1 f) {
         return IterableSplitter.map$(this, f);
      }

      public IterableSplitter.Appended appendParIterable(final IterableSplitter that) {
         return IterableSplitter.appendParIterable$(this, that);
      }

      public IterableSplitter zipParSeq(final SeqSplitter that) {
         return IterableSplitter.zipParSeq$(this, that);
      }

      public IterableSplitter.ZippedAll zipAllParSeq(final SeqSplitter that, final Object thisElem, final Object thatElem) {
         return IterableSplitter.zipAllParSeq$(this, that, thisElem, thatElem);
      }

      public boolean isAborted() {
         return DelegatedSignalling.isAborted$(this);
      }

      public void abort() {
         DelegatedSignalling.abort$(this);
      }

      public int indexFlag() {
         return DelegatedSignalling.indexFlag$(this);
      }

      public void setIndexFlag(final int f) {
         DelegatedSignalling.setIndexFlag$(this, f);
      }

      public void setIndexFlagIfGreater(final int f) {
         DelegatedSignalling.setIndexFlagIfGreater$(this, f);
      }

      public void setIndexFlagIfLesser(final int f) {
         DelegatedSignalling.setIndexFlagIfLesser$(this, f);
      }

      public int tag() {
         return DelegatedSignalling.tag$(this);
      }

      public int count(final Function1 p) {
         return AugmentedIterableIterator.count$(this, p);
      }

      public Object reduce(final Function2 op) {
         return AugmentedIterableIterator.reduce$(this, op);
      }

      public Object fold(final Object z, final Function2 op) {
         return AugmentedIterableIterator.fold$(this, z, op);
      }

      public Object sum(final Numeric num) {
         return AugmentedIterableIterator.sum$(this, num);
      }

      public Object product(final Numeric num) {
         return AugmentedIterableIterator.product$(this, num);
      }

      public Object min(final Ordering ord) {
         return AugmentedIterableIterator.min$(this, ord);
      }

      public Object max(final Ordering ord) {
         return AugmentedIterableIterator.max$(this, ord);
      }

      public Object reduceLeft(final int howmany, final Function2 op) {
         return AugmentedIterableIterator.reduceLeft$(this, howmany, op);
      }

      public Combiner map2combiner(final Function1 f, final Combiner cb) {
         return AugmentedIterableIterator.map2combiner$(this, f, cb);
      }

      public Combiner collect2combiner(final PartialFunction pf, final Combiner cb) {
         return AugmentedIterableIterator.collect2combiner$(this, pf, cb);
      }

      public Combiner flatmap2combiner(final Function1 f, final Combiner cb) {
         return AugmentedIterableIterator.flatmap2combiner$(this, f, cb);
      }

      public Builder copy2builder(final Builder b) {
         return AugmentedIterableIterator.copy2builder$(this, b);
      }

      public Combiner filter2combiner(final Function1 pred, final Combiner cb) {
         return AugmentedIterableIterator.filter2combiner$(this, pred, cb);
      }

      public Combiner filterNot2combiner(final Function1 pred, final Combiner cb) {
         return AugmentedIterableIterator.filterNot2combiner$(this, pred, cb);
      }

      public Tuple2 partition2combiners(final Function1 pred, final Combiner btrue, final Combiner bfalse) {
         return AugmentedIterableIterator.partition2combiners$(this, pred, btrue, bfalse);
      }

      public Combiner take2combiner(final int n, final Combiner cb) {
         return AugmentedIterableIterator.take2combiner$(this, n, cb);
      }

      public Combiner drop2combiner(final int n, final Combiner cb) {
         return AugmentedIterableIterator.drop2combiner$(this, n, cb);
      }

      public Combiner slice2combiner(final int from, final int until, final Combiner cb) {
         return AugmentedIterableIterator.slice2combiner$(this, from, until, cb);
      }

      public Tuple2 splitAt2combiners(final int at, final Combiner before, final Combiner after) {
         return AugmentedIterableIterator.splitAt2combiners$(this, at, before, after);
      }

      public Tuple2 takeWhile2combiner(final Function1 p, final Combiner cb) {
         return AugmentedIterableIterator.takeWhile2combiner$(this, p, cb);
      }

      public Tuple2 span2combiners(final Function1 p, final Combiner before, final Combiner after) {
         return AugmentedIterableIterator.span2combiners$(this, p, before, after);
      }

      public void scanToArray(final Object z, final Function2 op, final Object array, final int from) {
         AugmentedIterableIterator.scanToArray$(this, z, op, array, from);
      }

      public Combiner scanToCombiner(final Object startValue, final Function2 op, final Combiner cb) {
         return AugmentedIterableIterator.scanToCombiner$(this, startValue, op, cb);
      }

      public Combiner scanToCombiner(final int howmany, final Object startValue, final Function2 op, final Combiner cb) {
         return AugmentedIterableIterator.scanToCombiner$(this, howmany, startValue, op, cb);
      }

      public Combiner zip2combiner(final RemainsIterator otherpit, final Combiner cb) {
         return AugmentedIterableIterator.zip2combiner$(this, otherpit, cb);
      }

      public Combiner zipAll2combiner(final RemainsIterator that, final Object thiselem, final Object thatelem, final Combiner cb) {
         return AugmentedIterableIterator.zipAll2combiner$(this, that, thiselem, thatelem, cb);
      }

      public boolean isRemainingCheap() {
         return RemainsIterator.isRemainingCheap$(this);
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

      public Iterator takeWhile(final Function1 p) {
         return Iterator.takeWhile$(this, p);
      }

      public Iterator dropWhile(final Function1 p) {
         return Iterator.dropWhile$(this, p);
      }

      public Tuple2 span(final Function1 p) {
         return Iterator.span$(this, p);
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

      public Option minOption(final Ordering ord) {
         return IterableOnceOps.minOption$(this, ord);
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

      public Signalling signalDelegate() {
         return this.signalDelegate;
      }

      public void signalDelegate_$eq(final Signalling x$1) {
         this.signalDelegate = x$1;
      }

      public int idx() {
         return this.idx;
      }

      public void idx_$eq(final int x$1) {
         this.idx = x$1;
      }

      public int until() {
         return this.until;
      }

      public int totalsize() {
         return this.totalsize;
      }

      private void scan() {
         while(this.itertable[this.idx()] == null) {
            this.idx_$eq(this.idx() + 1);
         }

      }

      public abstract IterableSplitter newIterator(final int index, final int until, final int totalsize);

      public int remaining() {
         return this.totalsize() - this.traversed;
      }

      public boolean hasNext() {
         return this.traversed < this.totalsize();
      }

      public Object next() {
         if (this.hasNext()) {
            Object r = this.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$$outer().entryToElem(this.itertable[this.idx()]);
            ++this.traversed;
            this.idx_$eq(this.idx() + 1);
            if (this.hasNext()) {
               this.scan();
            }

            return r;
         } else {
            return scala.collection.Iterator..MODULE$.empty().next();
         }
      }

      public IterableSplitter dup() {
         return this.newIterator(this.idx(), this.until(), this.totalsize());
      }

      public Seq split() {
         if (this.remaining() > 1) {
            int divpt = (this.until() + this.idx()) / 2;
            int fstidx = this.idx();
            int fsttotal = this.calcNumElems(this.idx(), divpt, this.itertable.length, this.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$$outer().sizeMapBucketSize());
            IterableSplitter fstit = this.newIterator(fstidx, divpt, fsttotal);
            int snduntil = this.until();
            int sndtotal = this.remaining() - fsttotal;
            IterableSplitter sndit = this.newIterator(divpt, snduntil, sndtotal);
            return new scala.collection.immutable..colon.colon(fstit, new scala.collection.immutable..colon.colon(sndit, scala.collection.immutable.Nil..MODULE$));
         } else {
            return new scala.collection.immutable..colon.colon(this, scala.collection.immutable.Nil..MODULE$);
         }
      }

      public String debugInformation() {
         return this.buildString((append) -> {
            $anonfun$debugInformation$1(this, append);
            return BoxedUnit.UNIT;
         });
      }

      public int countElems(final int from, final int until) {
         int count = 0;

         for(int i = from; i < until; ++i) {
            if (this.itertable[i] != null) {
               ++count;
            }
         }

         return count;
      }

      public int countBucketSizes(final int frombucket, final int untilbucket) {
         int count = 0;

         for(int i = frombucket; i < untilbucket; ++i) {
            count += this.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$$outer().sizemap()[i];
         }

         return count;
      }

      // $FF: synthetic method
      public ParFlatHashTable scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final void $anonfun$debugInformation$1(final ParFlatHashTableIterator $this, final Function1 append) {
         append.apply("Parallel flat hash table iterator");
         append.apply("---------------------------------");
         append.apply((new java.lang.StringBuilder(20)).append("Traversed/total: ").append($this.traversed).append(" / ").append($this.totalsize()).toString());
         append.apply((new java.lang.StringBuilder(20)).append("Table idx/until: ").append($this.idx()).append(" / ").append($this.until()).toString());
         append.apply((new java.lang.StringBuilder(14)).append("Table length: ").append($this.itertable.length).toString());
         append.apply("Table: ");
         append.apply(DebugUtils$.MODULE$.arrayString($this.itertable, 0, $this.itertable.length));
         append.apply("Sizemap: ");
         append.apply(DebugUtils$.MODULE$.arrayString($this.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$$outer().sizemap(), 0, $this.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$$outer().sizemap().length));
      }

      public ParFlatHashTableIterator(final int idx, final int until, final int totalsize) {
         this.idx = idx;
         this.until = until;
         this.totalsize = totalsize;
         if (ParFlatHashTable.this == null) {
            throw null;
         } else {
            this.$outer = ParFlatHashTable.this;
            super();
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            RemainsIterator.$init$(this);
            AugmentedIterableIterator.$init$(this);
            DelegatedSignalling.$init$(this);
            IterableSplitter.$init$(this);
            SizeMapUtils.$init$(this);
            this.traversed = 0;
            this.itertable = ParFlatHashTable.this.table();
            if (this.hasNext()) {
               this.scan();
            }

         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
