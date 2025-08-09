package org.apache.spark.status;

import java.util.NoSuchElementException;
import org.apache.spark.status.api.v1.RDDPartitionInfo;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.BufferedIterator;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableFactory;
import scala.collection.IterableFactoryDefaults;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.LazyZip2;
import scala.collection.Searching;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.SeqView;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.View;
import scala.collection.WithFilter;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Range;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Map;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-4A\u0001E\t\u00055!)Q\u0007\u0001C\u0001m!9\u0011\b\u0001a\u0001\n\u0013Q\u0004b\u0002 \u0001\u0001\u0004%Ia\u0010\u0005\u0007\u000b\u0002\u0001\u000b\u0015B\u001e\t\u000f)\u0003\u0001\u0019!C\u0005u!91\n\u0001a\u0001\n\u0013a\u0005B\u0002(\u0001A\u0003&1\bC\u0004Q\u0001\u0001\u0007I\u0011A)\t\u000fU\u0003\u0001\u0019!C\u0001-\"1\u0001\f\u0001Q!\nICQA\u0017\u0001\u0005BmCQA\u0018\u0001\u0005B}CQa\u0019\u0001\u0005BECQ\u0001\u001a\u0001\u0005\u0002\u0015DQ\u0001\u001b\u0001\u0005\u0002%\u0014qB\u0015#E!\u0006\u0014H/\u001b;j_:\u001cV-\u001d\u0006\u0003%M\taa\u001d;biV\u001c(B\u0001\u000b\u0016\u0003\u0015\u0019\b/\u0019:l\u0015\t1r#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00021\u0005\u0019qN]4\u0004\u0001M\u0019\u0001aG\u0011\u0011\u0005qyR\"A\u000f\u000b\u0003y\tQa]2bY\u0006L!\u0001I\u000f\u0003\r\u0005s\u0017PU3g!\r\u0011#&\f\b\u0003G!r!\u0001J\u0014\u000e\u0003\u0015R!AJ\r\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0012BA\u0015\u001e\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u000b\u0017\u0003\u0007M+\u0017O\u0003\u0002*;A\u0011afM\u0007\u0002_)\u0011\u0001'M\u0001\u0003mFR!AM\t\u0002\u0007\u0005\u0004\u0018.\u0003\u00025_\t\u0001\"\u000b\u0012#QCJ$\u0018\u000e^5p]&sgm\\\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003]\u0002\"\u0001\u000f\u0001\u000e\u0003E\tQa\u00185fC\u0012,\u0012a\u000f\t\u0003qqJ!!P\t\u0003!1Kg/\u001a*E\tB\u000b'\u000f^5uS>t\u0017!C0iK\u0006$w\fJ3r)\t\u00015\t\u0005\u0002\u001d\u0003&\u0011!)\b\u0002\u0005+:LG\u000fC\u0004E\u0007\u0005\u0005\t\u0019A\u001e\u0002\u0007a$\u0013'\u0001\u0004`Q\u0016\fG\r\t\u0015\u0003\t\u001d\u0003\"\u0001\b%\n\u0005%k\"\u0001\u0003<pY\u0006$\u0018\u000e\\3\u0002\u000b}#\u0018-\u001b7\u0002\u0013}#\u0018-\u001b7`I\u0015\fHC\u0001!N\u0011\u001d!e!!AA\u0002m\naa\u0018;bS2\u0004\u0003FA\u0004H\u0003\u0015\u0019w.\u001e8u+\u0005\u0011\u0006C\u0001\u000fT\u0013\t!VDA\u0002J]R\f\u0011bY8v]R|F%Z9\u0015\u0005\u0001;\u0006b\u0002#\n\u0003\u0003\u0005\rAU\u0001\u0007G>,h\u000e\u001e\u0011)\u0005)9\u0015!B1qa2LHCA\u0017]\u0011\u0015i6\u00021\u0001S\u0003\rIG\r_\u0001\tSR,'/\u0019;peV\t\u0001\rE\u0002#C6J!A\u0019\u0017\u0003\u0011%#XM]1u_J\fa\u0001\\3oORD\u0017\u0001D1eIB\u000b'\u000f^5uS>tGC\u0001!g\u0011\u00159g\u00021\u0001<\u0003\u0011\u0001\u0018M\u001d;\u0002\u001fI,Wn\u001c<f!\u0006\u0014H/\u001b;j_:$\"\u0001\u00116\t\u000b\u001d|\u0001\u0019A\u001e"
)
public class RDDPartitionSeq implements Seq {
   private volatile LiveRDDPartition org$apache$spark$status$RDDPartitionSeq$$_head;
   private volatile LiveRDDPartition _tail;
   private volatile int count;

   public final Seq toSeq() {
      return Seq.toSeq$(this);
   }

   public SeqFactory iterableFactory() {
      return Seq.iterableFactory$(this);
   }

   public boolean canEqual(final Object that) {
      return scala.collection.Seq.canEqual$(this, that);
   }

   public boolean equals(final Object o) {
      return scala.collection.Seq.equals$(this, o);
   }

   public int hashCode() {
      return scala.collection.Seq.hashCode$(this);
   }

   public String toString() {
      return scala.collection.Seq.toString$(this);
   }

   public String stringPrefix() {
      return scala.collection.Seq.stringPrefix$(this);
   }

   // $FF: synthetic method
   public Object scala$collection$SeqOps$$super$concat(final IterableOnce suffix) {
      return IterableOps.concat$(this, suffix);
   }

   // $FF: synthetic method
   public int scala$collection$SeqOps$$super$sizeCompare(final int otherSize) {
      return IterableOps.sizeCompare$(this, otherSize);
   }

   // $FF: synthetic method
   public int scala$collection$SeqOps$$super$sizeCompare(final Iterable that) {
      return IterableOps.sizeCompare$(this, that);
   }

   public SeqView view() {
      return SeqOps.view$(this);
   }

   public Object prepended(final Object elem) {
      return SeqOps.prepended$(this, elem);
   }

   public final Object $plus$colon(final Object elem) {
      return SeqOps.$plus$colon$(this, elem);
   }

   public Object appended(final Object elem) {
      return SeqOps.appended$(this, elem);
   }

   public final Object $colon$plus(final Object elem) {
      return SeqOps.$colon$plus$(this, elem);
   }

   public Object prependedAll(final IterableOnce prefix) {
      return SeqOps.prependedAll$(this, prefix);
   }

   public final Object $plus$plus$colon(final IterableOnce prefix) {
      return SeqOps.$plus$plus$colon$(this, prefix);
   }

   public Object appendedAll(final IterableOnce suffix) {
      return SeqOps.appendedAll$(this, suffix);
   }

   public final Object $colon$plus$plus(final IterableOnce suffix) {
      return SeqOps.$colon$plus$plus$(this, suffix);
   }

   public final Object concat(final IterableOnce suffix) {
      return SeqOps.concat$(this, suffix);
   }

   /** @deprecated */
   public final Object union(final scala.collection.Seq that) {
      return SeqOps.union$(this, that);
   }

   public final int size() {
      return SeqOps.size$(this);
   }

   public Object distinct() {
      return SeqOps.distinct$(this);
   }

   public Object distinctBy(final Function1 f) {
      return SeqOps.distinctBy$(this, f);
   }

   public Object reverse() {
      return SeqOps.reverse$(this);
   }

   public Iterator reverseIterator() {
      return SeqOps.reverseIterator$(this);
   }

   public boolean startsWith(final IterableOnce that, final int offset) {
      return SeqOps.startsWith$(this, that, offset);
   }

   public int startsWith$default$2() {
      return SeqOps.startsWith$default$2$(this);
   }

   public boolean endsWith(final Iterable that) {
      return SeqOps.endsWith$(this, that);
   }

   public boolean isDefinedAt(final int idx) {
      return SeqOps.isDefinedAt$(this, idx);
   }

   public Object padTo(final int len, final Object elem) {
      return SeqOps.padTo$(this, len, elem);
   }

   public final int segmentLength(final Function1 p) {
      return SeqOps.segmentLength$(this, p);
   }

   public int segmentLength(final Function1 p, final int from) {
      return SeqOps.segmentLength$(this, p, from);
   }

   /** @deprecated */
   public final int prefixLength(final Function1 p) {
      return SeqOps.prefixLength$(this, p);
   }

   public int indexWhere(final Function1 p, final int from) {
      return SeqOps.indexWhere$(this, p, from);
   }

   public int indexWhere(final Function1 p) {
      return SeqOps.indexWhere$(this, p);
   }

   public int indexOf(final Object elem, final int from) {
      return SeqOps.indexOf$(this, elem, from);
   }

   public int indexOf(final Object elem) {
      return SeqOps.indexOf$(this, elem);
   }

   public int lastIndexOf(final Object elem, final int end) {
      return SeqOps.lastIndexOf$(this, elem, end);
   }

   public int lastIndexOf$default$2() {
      return SeqOps.lastIndexOf$default$2$(this);
   }

   public int lastIndexWhere(final Function1 p, final int end) {
      return SeqOps.lastIndexWhere$(this, p, end);
   }

   public int lastIndexWhere(final Function1 p) {
      return SeqOps.lastIndexWhere$(this, p);
   }

   public int indexOfSlice(final scala.collection.Seq that, final int from) {
      return SeqOps.indexOfSlice$(this, that, from);
   }

   public int indexOfSlice(final scala.collection.Seq that) {
      return SeqOps.indexOfSlice$(this, that);
   }

   public int lastIndexOfSlice(final scala.collection.Seq that, final int end) {
      return SeqOps.lastIndexOfSlice$(this, that, end);
   }

   public int lastIndexOfSlice(final scala.collection.Seq that) {
      return SeqOps.lastIndexOfSlice$(this, that);
   }

   public Option findLast(final Function1 p) {
      return SeqOps.findLast$(this, p);
   }

   public boolean containsSlice(final scala.collection.Seq that) {
      return SeqOps.containsSlice$(this, that);
   }

   public boolean contains(final Object elem) {
      return SeqOps.contains$(this, elem);
   }

   /** @deprecated */
   public Object reverseMap(final Function1 f) {
      return SeqOps.reverseMap$(this, f);
   }

   public Iterator permutations() {
      return SeqOps.permutations$(this);
   }

   public Iterator combinations(final int n) {
      return SeqOps.combinations$(this, n);
   }

   public Object sorted(final Ordering ord) {
      return SeqOps.sorted$(this, ord);
   }

   public Object sortWith(final Function2 lt) {
      return SeqOps.sortWith$(this, lt);
   }

   public Object sortBy(final Function1 f, final Ordering ord) {
      return SeqOps.sortBy$(this, f, ord);
   }

   public Range indices() {
      return SeqOps.indices$(this);
   }

   public final int sizeCompare(final int otherSize) {
      return SeqOps.sizeCompare$(this, otherSize);
   }

   public int lengthCompare(final int len) {
      return SeqOps.lengthCompare$(this, len);
   }

   public final int sizeCompare(final Iterable that) {
      return SeqOps.sizeCompare$(this, that);
   }

   public int lengthCompare(final Iterable that) {
      return SeqOps.lengthCompare$(this, that);
   }

   public final IterableOps lengthIs() {
      return SeqOps.lengthIs$(this);
   }

   public boolean isEmpty() {
      return SeqOps.isEmpty$(this);
   }

   public boolean sameElements(final IterableOnce that) {
      return SeqOps.sameElements$(this, that);
   }

   public boolean corresponds(final scala.collection.Seq that, final Function2 p) {
      return SeqOps.corresponds$(this, that, p);
   }

   public Object diff(final scala.collection.Seq that) {
      return SeqOps.diff$(this, that);
   }

   public Object intersect(final scala.collection.Seq that) {
      return SeqOps.intersect$(this, that);
   }

   public Object patch(final int from, final IterableOnce other, final int replaced) {
      return SeqOps.patch$(this, from, other, replaced);
   }

   public Object updated(final int index, final Object elem) {
      return SeqOps.updated$(this, index, elem);
   }

   public Map occCounts(final scala.collection.Seq sq) {
      return SeqOps.occCounts$(this, sq);
   }

   public Searching.SearchResult search(final Object elem, final Ordering ord) {
      return SeqOps.search$(this, elem, ord);
   }

   public Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
      return SeqOps.search$(this, elem, from, to, ord);
   }

   public Option unapply(final Object a) {
      return PartialFunction.unapply$(this, a);
   }

   public PartialFunction elementWise() {
      return PartialFunction.elementWise$(this);
   }

   public PartialFunction orElse(final PartialFunction that) {
      return PartialFunction.orElse$(this, that);
   }

   public PartialFunction andThen(final Function1 k) {
      return PartialFunction.andThen$(this, k);
   }

   public PartialFunction andThen(final PartialFunction k) {
      return PartialFunction.andThen$(this, k);
   }

   public PartialFunction compose(final PartialFunction k) {
      return PartialFunction.compose$(this, k);
   }

   public Function1 lift() {
      return PartialFunction.lift$(this);
   }

   public Object applyOrElse(final Object x, final Function1 default) {
      return PartialFunction.applyOrElse$(this, x, default);
   }

   public Function1 runWith(final Function1 action) {
      return PartialFunction.runWith$(this, action);
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

   /** @deprecated */
   public final Iterable toIterable() {
      return Iterable.toIterable$(this);
   }

   public final Iterable coll() {
      return Iterable.coll$(this);
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

   public final IterableOps sizeIs() {
      return IterableOps.sizeIs$(this);
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

   public scala.collection.immutable.Map groupBy(final Function1 f) {
      return IterableOps.groupBy$(this, f);
   }

   public scala.collection.immutable.Map groupMap(final Function1 key, final Function1 f) {
      return IterableOps.groupMap$(this, key, f);
   }

   public scala.collection.immutable.Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
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

   public scala.collection.immutable.Map toMap(final .less.colon.less ev) {
      return IterableOnceOps.toMap$(this, ev);
   }

   public Set toSet() {
      return IterableOnceOps.toSet$(this);
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

   public LiveRDDPartition org$apache$spark$status$RDDPartitionSeq$$_head() {
      return this.org$apache$spark$status$RDDPartitionSeq$$_head;
   }

   private void _head_$eq(final LiveRDDPartition x$1) {
      this.org$apache$spark$status$RDDPartitionSeq$$_head = x$1;
   }

   private LiveRDDPartition _tail() {
      return this._tail;
   }

   private void _tail_$eq(final LiveRDDPartition x$1) {
      this._tail = x$1;
   }

   public int count() {
      return this.count;
   }

   public void count_$eq(final int x$1) {
      this.count = x$1;
   }

   public RDDPartitionInfo apply(final int idx) {
      int curr = 0;

      LiveRDDPartition e;
      for(e = this.org$apache$spark$status$RDDPartitionSeq$$_head(); curr < idx && e != null; e = e.next()) {
         ++curr;
      }

      if (e != null) {
         return e.value();
      } else {
         throw new IndexOutOfBoundsException(Integer.toString(idx));
      }
   }

   public Iterator iterator() {
      return new Iterator() {
         private LiveRDDPartition current;

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

         public scala.collection.immutable.Map toMap(final .less.colon.less ev) {
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

         private LiveRDDPartition current() {
            return this.current;
         }

         private void current_$eq(final LiveRDDPartition x$1) {
            this.current = x$1;
         }

         public boolean hasNext() {
            return this.current() != null;
         }

         public RDDPartitionInfo next() {
            if (this.current() != null) {
               LiveRDDPartition tmp = this.current();
               this.current_$eq(tmp.next());
               return tmp.value();
            } else {
               throw new NoSuchElementException();
            }
         }

         public {
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            this.current = RDDPartitionSeq.this.org$apache$spark$status$RDDPartitionSeq$$_head();
         }
      };
   }

   public int length() {
      return this.count();
   }

   public void addPartition(final LiveRDDPartition part) {
      part.prev_$eq(this._tail());
      if (this._tail() != null) {
         this._tail().next_$eq(part);
      }

      if (this.org$apache$spark$status$RDDPartitionSeq$$_head() == null) {
         this._head_$eq(part);
      }

      this._tail_$eq(part);
      this.count_$eq(this.count() + 1);
   }

   public void removePartition(final LiveRDDPartition part) {
      this.count_$eq(this.count() - 1);
      if (part.prev() != null) {
         part.prev().next_$eq(part.next());
      }

      if (part == this.org$apache$spark$status$RDDPartitionSeq$$_head()) {
         this._head_$eq(part.next());
      }

      if (part.next() != null) {
         part.next().prev_$eq(part.prev());
      }

      if (part == this._tail()) {
         this._tail_$eq(part.prev());
      }
   }

   public RDDPartitionSeq() {
      IterableOnce.$init$(this);
      IterableOnceOps.$init$(this);
      IterableOps.$init$(this);
      IterableFactoryDefaults.$init$(this);
      Iterable.$init$(this);
      scala.collection.immutable.Iterable.$init$(this);
      Function1.$init$(this);
      PartialFunction.$init$(this);
      SeqOps.$init$(this);
      scala.collection.Seq.$init$(this);
      Seq.$init$(this);
      this.org$apache$spark$status$RDDPartitionSeq$$_head = null;
      this._tail = null;
      this.count = 0;
   }
}
