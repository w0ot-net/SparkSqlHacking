package breeze.util;

import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableFactory;
import scala.collection.IterableFactoryDefaults;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.LazyZip2;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.View;
import scala.collection.WithFilter;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005A4Aa\u0003\u0007\u0003#!Aa\u0006\u0001B\u0001B\u0003%q\u0006C\u00039\u0001\u0011\u0005\u0011\b\u0003\u0007B\u0001\u0011\u0005\tQ!AC\u0002\u0013%!\tC\u0005G\u0001\t\u0005\t\u0011)A\u0005\u0007\")q\t\u0001C\u0001\u0011\")\u0011\u000b\u0001C\u0001%\")Q\u000b\u0001C\u0001-\")A\f\u0001C\u0001;\")Q\r\u0001C\u0001M\")\u0001\u000e\u0001C!S\nq1i\\7q_NLG/Z%oI\u0016D(BA\u0007\u000f\u0003\u0011)H/\u001b7\u000b\u0003=\taA\u0019:fKj,7\u0001A\u000b\u0003%\u0015\u001a2\u0001A\n\u001a!\t!r#D\u0001\u0016\u0015\u00051\u0012!B:dC2\f\u0017B\u0001\r\u0016\u0005\u0019\te.\u001f*fMB\u0019!dG\u000f\u000e\u00031I!\u0001\b\u0007\u0003\u000b%sG-\u001a=\u0011\tQq\u0002eI\u0005\u0003?U\u0011a\u0001V;qY\u0016\u0014\u0004C\u0001\u000b\"\u0013\t\u0011SCA\u0002J]R\u0004\"\u0001J\u0013\r\u0001\u0011)a\u0005\u0001b\u0001O\t\tQ+\u0005\u0002)WA\u0011A#K\u0005\u0003UU\u0011qAT8uQ&tw\r\u0005\u0002\u0015Y%\u0011Q&\u0006\u0002\u0004\u0003:L\u0018aB5oI&\u001cWm\u001d\t\u0004)A\u0012\u0014BA\u0019\u0016\u0005)a$/\u001a9fCR,GM\u0010\u0019\u0003gU\u00022AG\u000e5!\t!S\u0007B\u00057\u0003\u0005\u0005\t\u0011!B\u0001o\t\u0019q\fJ\u0019\u0012\u0005!\u001a\u0013A\u0002\u001fj]&$h\b\u0006\u0002;wA\u0019!\u0004A\u0012\t\u000b9\u0012\u0001\u0019\u0001\u001f\u0011\u0007Q\u0001T\b\r\u0002?\u0001B\u0019!dG \u0011\u0005\u0011\u0002E!\u0003\u001c<\u0003\u0003\u0005\tQ!\u00018\u0003\r\u0012'/Z3{K\u0012*H/\u001b7%\u0007>l\u0007o\\:ji\u0016Le\u000eZ3yI\u0011zgMZ:fiN,\u0012a\u0011\t\u0004)\u0011\u0003\u0013BA#\u0016\u0005\u0015\t%O]1z\u0003\u0011\u0012'/Z3{K\u0012*H/\u001b7%\u0007>l\u0007o\\:ji\u0016Le\u000eZ3yI\u0011zgMZ:fiN\u0004\u0013\u0001C7ba&sG-\u001a=\u0015\u0007\u0001J5\nC\u0003K\u000b\u0001\u0007\u0001%A\u0005d_6\u0004xN\\3oi\")A*\u0002a\u0001A\u00051Q/\u00138eKbD#!\u0002(\u0011\u0005Qy\u0015B\u0001)\u0016\u0005\u0019Ig\u000e\\5oK\u0006)\u0011\r\u001d9msR\u0011\u0001e\u0015\u0005\u0006)\u001a\u0001\r!H\u0001\u0002i\u00069QO\\1qa2LHCA,[!\r!\u0002,H\u0005\u00033V\u0011aa\u00149uS>t\u0007\"B.\b\u0001\u0004\u0001\u0013!A5\u0002\u000bA\f\u0017N]:\u0016\u0003y\u00032a\u00182e\u001b\u0005\u0001'BA1\u0016\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003G\u0002\u0014\u0001\"\u0013;fe\u0006$xN\u001d\t\u0005)yi\u0002%\u0001\u0005ji\u0016\u0014\u0018\r^8s+\u00059\u0007cA0c;\u0005!1/\u001b>f+\u0005\u0001\u0003\u0006\u0002\u0001l]>\u0004\"\u0001\u00067\n\u00055,\"\u0001E*fe&\fGNV3sg&|g.V%E\u0003\u00151\u0018\r\\;f=\u0005\t\u0001"
)
public final class CompositeIndex implements Index {
   private static final long serialVersionUID = 1L;
   private final Seq indices;
   private final int[] breeze$util$CompositeIndex$$offsets;
   private int defaultHashCode;
   private volatile boolean bitmap$0;

   public boolean contains(final Object t) {
      return Index.contains$(this, t);
   }

   public Option indexOpt(final Object t) {
      return Index.indexOpt$(this, t);
   }

   public int indexOf(final Object t) {
      return Index.indexOf$(this, t);
   }

   public Object get(final int i) {
      return Index.get$(this, i);
   }

   public boolean equals(final Object other) {
      return Index.equals$(this, other);
   }

   public int hashCode() {
      return Index.hashCode$(this);
   }

   public String toString() {
      return Index.toString$(this);
   }

   public EitherIndex $bar(final Index right) {
      return Index.$bar$(this, right);
   }

   public boolean apply$mcZD$sp(final double v1) {
      return Function1.apply$mcZD$sp$(this, v1);
   }

   public double apply$mcDD$sp(final double v1) {
      return Function1.apply$mcDD$sp$(this, v1);
   }

   public float apply$mcFD$sp(final double v1) {
      return Function1.apply$mcFD$sp$(this, v1);
   }

   public int apply$mcID$sp(final double v1) {
      return Function1.apply$mcID$sp$(this, v1);
   }

   public long apply$mcJD$sp(final double v1) {
      return Function1.apply$mcJD$sp$(this, v1);
   }

   public void apply$mcVD$sp(final double v1) {
      Function1.apply$mcVD$sp$(this, v1);
   }

   public boolean apply$mcZF$sp(final float v1) {
      return Function1.apply$mcZF$sp$(this, v1);
   }

   public double apply$mcDF$sp(final float v1) {
      return Function1.apply$mcDF$sp$(this, v1);
   }

   public float apply$mcFF$sp(final float v1) {
      return Function1.apply$mcFF$sp$(this, v1);
   }

   public int apply$mcIF$sp(final float v1) {
      return Function1.apply$mcIF$sp$(this, v1);
   }

   public long apply$mcJF$sp(final float v1) {
      return Function1.apply$mcJF$sp$(this, v1);
   }

   public void apply$mcVF$sp(final float v1) {
      Function1.apply$mcVF$sp$(this, v1);
   }

   public boolean apply$mcZI$sp(final int v1) {
      return Function1.apply$mcZI$sp$(this, v1);
   }

   public double apply$mcDI$sp(final int v1) {
      return Function1.apply$mcDI$sp$(this, v1);
   }

   public float apply$mcFI$sp(final int v1) {
      return Function1.apply$mcFI$sp$(this, v1);
   }

   public int apply$mcII$sp(final int v1) {
      return Function1.apply$mcII$sp$(this, v1);
   }

   public long apply$mcJI$sp(final int v1) {
      return Function1.apply$mcJI$sp$(this, v1);
   }

   public void apply$mcVI$sp(final int v1) {
      Function1.apply$mcVI$sp$(this, v1);
   }

   public boolean apply$mcZJ$sp(final long v1) {
      return Function1.apply$mcZJ$sp$(this, v1);
   }

   public double apply$mcDJ$sp(final long v1) {
      return Function1.apply$mcDJ$sp$(this, v1);
   }

   public float apply$mcFJ$sp(final long v1) {
      return Function1.apply$mcFJ$sp$(this, v1);
   }

   public int apply$mcIJ$sp(final long v1) {
      return Function1.apply$mcIJ$sp$(this, v1);
   }

   public long apply$mcJJ$sp(final long v1) {
      return Function1.apply$mcJJ$sp$(this, v1);
   }

   public void apply$mcVJ$sp(final long v1) {
      Function1.apply$mcVJ$sp$(this, v1);
   }

   public Function1 compose(final Function1 g) {
      return Function1.compose$(this, g);
   }

   public Function1 andThen(final Function1 g) {
      return Function1.andThen$(this, g);
   }

   /** @deprecated */
   public final Iterable toIterable() {
      return Iterable.toIterable$(this);
   }

   public final Iterable coll() {
      return Iterable.coll$(this);
   }

   public IterableFactory iterableFactory() {
      return Iterable.iterableFactory$(this);
   }

   /** @deprecated */
   public Iterable seq() {
      return Iterable.seq$(this);
   }

   public String className() {
      return Iterable.className$(this);
   }

   public final String collectionClassName() {
      return Iterable.collectionClassName$(this);
   }

   public String stringPrefix() {
      return Iterable.stringPrefix$(this);
   }

   public LazyZip2 lazyZip(final Iterable that) {
      return Iterable.lazyZip$(this, that);
   }

   public IterableOps fromSpecific(final IterableOnce coll) {
      return IterableFactoryDefaults.fromSpecific$(this, coll);
   }

   public Builder newSpecificBuilder() {
      return IterableFactoryDefaults.newSpecificBuilder$(this);
   }

   public IterableOps empty() {
      return IterableFactoryDefaults.empty$(this);
   }

   /** @deprecated */
   public final Iterable toTraversable() {
      return IterableOps.toTraversable$(this);
   }

   public boolean isTraversableAgain() {
      return IterableOps.isTraversableAgain$(this);
   }

   /** @deprecated */
   public final Object repr() {
      return IterableOps.repr$(this);
   }

   /** @deprecated */
   public IterableFactory companion() {
      return IterableOps.companion$(this);
   }

   public Object head() {
      return IterableOps.head$(this);
   }

   public Option headOption() {
      return IterableOps.headOption$(this);
   }

   public Object last() {
      return IterableOps.last$(this);
   }

   public Option lastOption() {
      return IterableOps.lastOption$(this);
   }

   public View view() {
      return IterableOps.view$(this);
   }

   public int sizeCompare(final int otherSize) {
      return IterableOps.sizeCompare$(this, otherSize);
   }

   public final IterableOps sizeIs() {
      return IterableOps.sizeIs$(this);
   }

   public int sizeCompare(final Iterable that) {
      return IterableOps.sizeCompare$(this, that);
   }

   /** @deprecated */
   public View view(final int from, final int until) {
      return IterableOps.view$(this, from, until);
   }

   public Object transpose(final Function1 asIterable) {
      return IterableOps.transpose$(this, asIterable);
   }

   public Object filter(final Function1 pred) {
      return IterableOps.filter$(this, pred);
   }

   public Object filterNot(final Function1 pred) {
      return IterableOps.filterNot$(this, pred);
   }

   public WithFilter withFilter(final Function1 p) {
      return IterableOps.withFilter$(this, p);
   }

   public Tuple2 partition(final Function1 p) {
      return IterableOps.partition$(this, p);
   }

   public Tuple2 splitAt(final int n) {
      return IterableOps.splitAt$(this, n);
   }

   public Object take(final int n) {
      return IterableOps.take$(this, n);
   }

   public Object takeRight(final int n) {
      return IterableOps.takeRight$(this, n);
   }

   public Object takeWhile(final Function1 p) {
      return IterableOps.takeWhile$(this, p);
   }

   public Tuple2 span(final Function1 p) {
      return IterableOps.span$(this, p);
   }

   public Object drop(final int n) {
      return IterableOps.drop$(this, n);
   }

   public Object dropRight(final int n) {
      return IterableOps.dropRight$(this, n);
   }

   public Object dropWhile(final Function1 p) {
      return IterableOps.dropWhile$(this, p);
   }

   public Iterator grouped(final int size) {
      return IterableOps.grouped$(this, size);
   }

   public Iterator sliding(final int size) {
      return IterableOps.sliding$(this, size);
   }

   public Iterator sliding(final int size, final int step) {
      return IterableOps.sliding$(this, size, step);
   }

   public Object tail() {
      return IterableOps.tail$(this);
   }

   public Object init() {
      return IterableOps.init$(this);
   }

   public Object slice(final int from, final int until) {
      return IterableOps.slice$(this, from, until);
   }

   public Map groupBy(final Function1 f) {
      return IterableOps.groupBy$(this, f);
   }

   public Map groupMap(final Function1 key, final Function1 f) {
      return IterableOps.groupMap$(this, key, f);
   }

   public Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
      return IterableOps.groupMapReduce$(this, key, f, reduce);
   }

   public Object scan(final Object z, final Function2 op) {
      return IterableOps.scan$(this, z, op);
   }

   public Object scanLeft(final Object z, final Function2 op) {
      return IterableOps.scanLeft$(this, z, op);
   }

   public Object scanRight(final Object z, final Function2 op) {
      return IterableOps.scanRight$(this, z, op);
   }

   public Object map(final Function1 f) {
      return IterableOps.map$(this, f);
   }

   public Object flatMap(final Function1 f) {
      return IterableOps.flatMap$(this, f);
   }

   public Object flatten(final Function1 asIterable) {
      return IterableOps.flatten$(this, asIterable);
   }

   public Object collect(final PartialFunction pf) {
      return IterableOps.collect$(this, pf);
   }

   public Tuple2 partitionMap(final Function1 f) {
      return IterableOps.partitionMap$(this, f);
   }

   public Object concat(final IterableOnce suffix) {
      return IterableOps.concat$(this, suffix);
   }

   public final Object $plus$plus(final IterableOnce suffix) {
      return IterableOps.$plus$plus$(this, suffix);
   }

   public Object zip(final IterableOnce that) {
      return IterableOps.zip$(this, that);
   }

   public Object zipWithIndex() {
      return IterableOps.zipWithIndex$(this);
   }

   public Object zipAll(final Iterable that, final Object thisElem, final Object thatElem) {
      return IterableOps.zipAll$(this, that, thisElem, thatElem);
   }

   public Tuple2 unzip(final Function1 asPair) {
      return IterableOps.unzip$(this, asPair);
   }

   public Tuple3 unzip3(final Function1 asTriple) {
      return IterableOps.unzip3$(this, asTriple);
   }

   public Iterator tails() {
      return IterableOps.tails$(this);
   }

   public Iterator inits() {
      return IterableOps.inits$(this);
   }

   public Object tapEach(final Function1 f) {
      return IterableOps.tapEach$(this, f);
   }

   /** @deprecated */
   public Object $plus$plus$colon(final IterableOnce that) {
      return IterableOps.$plus$plus$colon$(this, that);
   }

   /** @deprecated */
   public boolean hasDefiniteSize() {
      return IterableOnceOps.hasDefiniteSize$(this);
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

   public boolean isEmpty() {
      return IterableOnceOps.isEmpty$(this);
   }

   public boolean nonEmpty() {
      return IterableOnceOps.nonEmpty$(this);
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

   private int defaultHashCode$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.defaultHashCode = Index.defaultHashCode$(this);
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.defaultHashCode;
   }

   public int defaultHashCode() {
      return !this.bitmap$0 ? this.defaultHashCode$lzycompute() : this.defaultHashCode;
   }

   public int[] breeze$util$CompositeIndex$$offsets() {
      return this.breeze$util$CompositeIndex$$offsets;
   }

   public int mapIndex(final int component, final int uIndex) {
      return uIndex < 0 ? -1 : this.breeze$util$CompositeIndex$$offsets()[component] + uIndex;
   }

   public int apply(final Tuple2 t) {
      return t._1$mcI$sp() < this.indices.length() && t._1$mcI$sp() >= 0 ? ((Index)this.indices.apply(t._1$mcI$sp())).apply(t._2()) + this.breeze$util$CompositeIndex$$offsets()[t._1$mcI$sp()] : -1;
   }

   public Option unapply(final int i) {
      Object var10000;
      if (i >= 0 && i < this.size()) {
         int res = Arrays.binarySearch(this.breeze$util$CompositeIndex$$offsets(), i);
         int index = res >= 0 ? res : -(res + 2);
         var10000 = new Some(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(index)), ((Index)this.indices.apply(index)).get(i - this.breeze$util$CompositeIndex$$offsets()[index])));
      } else {
         var10000 = scala.None..MODULE$;
      }

      return (Option)var10000;
   }

   public Iterator pairs() {
      return this.indices.iterator().zipWithIndex().flatMap((x0$1) -> {
         if (x0$1 != null) {
            Index index = (Index)x0$1._1();
            int i = x0$1._2$mcI$sp();
            Iterator var1 = index.iterator().map((t) -> new Tuple2(BoxesRunTime.boxToInteger(i), t));
            return var1;
         } else {
            throw new MatchError(x0$1);
         }
      }).zipWithIndex();
   }

   public Iterator iterator() {
      return this.indices.iterator().zipWithIndex().flatMap((x0$1) -> {
         if (x0$1 != null) {
            Index index = (Index)x0$1._1();
            int i = x0$1._2$mcI$sp();
            Iterator var1 = index.iterator().map((t) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(i)), t));
            return var1;
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   public int size() {
      return this.breeze$util$CompositeIndex$$offsets()[this.breeze$util$CompositeIndex$$offsets().length - 1];
   }

   // $FF: synthetic method
   public static final int $anonfun$breeze$util$CompositeIndex$$offsets$1(final int n, final Index i) {
      return n + i.size();
   }

   public CompositeIndex(final Seq indices) {
      this.indices = indices;
      IterableOnce.$init$(this);
      IterableOnceOps.$init$(this);
      IterableOps.$init$(this);
      IterableFactoryDefaults.$init$(this);
      Iterable.$init$(this);
      Function1.$init$(this);
      Index.$init$(this);
      this.breeze$util$CompositeIndex$$offsets = (int[])((IterableOnceOps)package$.MODULE$.SeqExtras(indices).unfold(BoxesRunTime.boxToInteger(0), (n, i) -> BoxesRunTime.boxToInteger($anonfun$breeze$util$CompositeIndex$$offsets$1(BoxesRunTime.unboxToInt(n), i)), scala.collection.BuildFrom..MODULE$.buildFromIterableOps())).toArray(scala.reflect.ClassTag..MODULE$.Int());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
