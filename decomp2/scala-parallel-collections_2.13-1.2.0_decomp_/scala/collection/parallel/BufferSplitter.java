package scala.collection.parallel;

import java.lang.invoke.SerializedLambda;
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
import scala.collection.generic.DelegatedSignalling;
import scala.collection.generic.Signalling;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005U4Q\u0001E\t\u0001#]A\u0001\"\f\u0001\u0003\u0006\u0004%IA\f\u0005\tk\u0001\u0011\t\u0011)A\u0005_!Aa\u0007\u0001BA\u0002\u0013%q\u0007\u0003\u0005<\u0001\t\u0005\r\u0011\"\u0003=\u0011!\u0011\u0005A!A!B\u0013A\u0004\u0002C\"\u0001\u0005\u000b\u0007I\u0011B\u001c\t\u0011\u0011\u0003!\u0011!Q\u0001\naB\u0001\"\u0012\u0001\u0003\u0002\u0003\u0006IA\u0012\u0005\u0006\u0019\u0002!\t!\u0014\u0005\u0006'\u0002!\t\u0001\u0016\u0005\u00061\u0002!\t!\u0017\u0005\u00065\u0002!\ta\u000e\u0005\u00067\u0002!\t\u0001\u0018\u0005\u0006;\u0002!\tA\u0018\u0005\u0007W\u0002!\t%\u00057\u0003\u001d\t+hMZ3s'Bd\u0017\u000e\u001e;fe*\u0011!cE\u0001\ta\u0006\u0014\u0018\r\u001c7fY*\u0011A#F\u0001\u000bG>dG.Z2uS>t'\"\u0001\f\u0002\u000bM\u001c\u0017\r\\1\u0016\u0005a\u00193c\u0001\u0001\u001a;A\u0011!dG\u0007\u0002+%\u0011A$\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0007yy\u0012%D\u0001\u0012\u0013\t\u0001\u0013C\u0001\tJi\u0016\u0014\u0018M\u00197f'Bd\u0017\u000e\u001e;feB\u0011!e\t\u0007\u0001\t\u0015!\u0003A1\u0001'\u0005\u0005!6\u0001A\t\u0003O)\u0002\"A\u0007\u0015\n\u0005%*\"a\u0002(pi\"Lgn\u001a\t\u00035-J!\u0001L\u000b\u0003\u0007\u0005s\u00170\u0001\u0004ck\u001a4WM]\u000b\u0002_A\u0019\u0001gM\u0011\u000e\u0003ER!AM\n\u0002\u000f5,H/\u00192mK&\u0011A'\r\u0002\f\u0003J\u0014\u0018-\u001f\"vM\u001a,'/A\u0004ck\u001a4WM\u001d\u0011\u0002\u000b%tG-\u001a=\u0016\u0003a\u0002\"AG\u001d\n\u0005i*\"aA%oi\u0006I\u0011N\u001c3fq~#S-\u001d\u000b\u0003{\u0001\u0003\"A\u0007 \n\u0005}*\"\u0001B+oSRDq!\u0011\u0003\u0002\u0002\u0003\u0007\u0001(A\u0002yIE\na!\u001b8eKb\u0004\u0013!B;oi&d\u0017AB;oi&d\u0007%A\u0004`g&<G-\u001a7\u0011\u0005\u001dSU\"\u0001%\u000b\u0005%\u001b\u0012aB4f]\u0016\u0014\u0018nY\u0005\u0003\u0017\"\u0013!bU5h]\u0006dG.\u001b8h\u0003\u0019a\u0014N\\5u}Q)aj\u0014)R%B\u0019a\u0004A\u0011\t\u000b5J\u0001\u0019A\u0018\t\u000bYJ\u0001\u0019\u0001\u001d\t\u000b\rK\u0001\u0019\u0001\u001d\t\u000b\u0015K\u0001\u0019\u0001$\u0002\u000f!\f7OT3yiV\tQ\u000b\u0005\u0002\u001b-&\u0011q+\u0006\u0002\b\u0005>|G.Z1o\u0003\u0011qW\r\u001f;\u0015\u0003\u0005\n\u0011B]3nC&t\u0017N\\4\u0002\u0007\u0011,\b/F\u0001O\u0003\u0015\u0019\b\u000f\\5u+\u0005y\u0006c\u00011i;9\u0011\u0011M\u001a\b\u0003E\u0016l\u0011a\u0019\u0006\u0003I\u0016\na\u0001\u0010:p_Rt\u0014\"\u0001\f\n\u0005\u001d,\u0012a\u00029bG.\fw-Z\u0005\u0003S*\u00141aU3r\u0015\t9W#\u0001\teK\n,x-\u00138g_Jl\u0017\r^5p]V\tQ\u000e\u0005\u0002oe:\u0011q\u000e\u001d\t\u0003EVI!!]\u000b\u0002\rA\u0013X\rZ3g\u0013\t\u0019HO\u0001\u0004TiJLgn\u001a\u0006\u0003cV\u0001"
)
public class BufferSplitter implements IterableSplitter {
   private final ArrayBuffer buffer;
   private int index;
   private final int until;
   private Signalling signalDelegate;

   public Seq splitWithSignalling() {
      return IterableSplitter.splitWithSignalling$(this);
   }

   public boolean shouldSplitFurther(final ParIterable coll, final int parallelismLevel) {
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

   private ArrayBuffer buffer() {
      return this.buffer;
   }

   private int index() {
      return this.index;
   }

   private void index_$eq(final int x$1) {
      this.index = x$1;
   }

   private int until() {
      return this.until;
   }

   public boolean hasNext() {
      return this.index() < this.until();
   }

   public Object next() {
      Object r = this.buffer().apply(this.index());
      this.index_$eq(this.index() + 1);
      return r;
   }

   public int remaining() {
      return this.until() - this.index();
   }

   public BufferSplitter dup() {
      return new BufferSplitter(this.buffer(), this.index(), this.until(), this.signalDelegate());
   }

   public Seq split() {
      if (this.remaining() > 1) {
         int divsz = (this.until() - this.index()) / 2;
         return new scala.collection.immutable..colon.colon(new BufferSplitter(this.buffer(), this.index(), this.index() + divsz, this.signalDelegate()), new scala.collection.immutable..colon.colon(new BufferSplitter(this.buffer(), this.index() + divsz, this.until(), this.signalDelegate()), scala.collection.immutable.Nil..MODULE$));
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

   // $FF: synthetic method
   public static final void $anonfun$debugInformation$1(final BufferSplitter $this, final Function1 append) {
      append.apply("---------------");
      append.apply("Buffer iterator");
      append.apply((new java.lang.StringBuilder(8)).append("buffer: ").append($this.buffer()).toString());
      append.apply((new java.lang.StringBuilder(7)).append("index: ").append($this.index()).toString());
      append.apply((new java.lang.StringBuilder(7)).append("until: ").append($this.until()).toString());
      append.apply("---------------");
   }

   public BufferSplitter(final ArrayBuffer buffer, final int index, final int until, final Signalling _sigdel) {
      this.buffer = buffer;
      this.index = index;
      this.until = until;
      super();
      IterableOnce.$init$(this);
      IterableOnceOps.$init$(this);
      Iterator.$init$(this);
      RemainsIterator.$init$(this);
      AugmentedIterableIterator.$init$(this);
      DelegatedSignalling.$init$(this);
      IterableSplitter.$init$(this);
      this.signalDelegate_$eq(_sigdel);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
