package cats.kernel.compat;

import java.io.Serializable;
import java.util.NoSuchElementException;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
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
import scala.collection.MapFactory;
import scala.collection.MapFactoryDefaults;
import scala.collection.MapView;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.View;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.MapOps;
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

@ScalaSignature(
   bytes = "\u0006\u0005M3a!\u0002\u0004\u0002\u0002!a\u0001\u0002C\u001c\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001d\t\u000b}\u0002A\u0011\u0001!\t\u000b\u0011\u0003A\u0011A#\t\u000bA\u0003A\u0011A)\u0003+]\u0013\u0018\r\u001d9fI6+H/\u00192mK6\u000b\u0007OQ1tK*\u0011q\u0001C\u0001\u0007G>l\u0007/\u0019;\u000b\u0005%Q\u0011AB6fe:,GNC\u0001\f\u0003\u0011\u0019\u0017\r^:\u0016\u00075\u0011Cf\u0005\u0003\u0001\u001dQq\u0003CA\b\u0013\u001b\u0005\u0001\"\"A\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005M\u0001\"AB!osJ+g\r\u0005\u0003\u0016;\u0001ZcB\u0001\f\u001c!\t9\u0002#D\u0001\u0019\u0015\tI\"$\u0001\u0004=e>|GOP\u0002\u0001\u0013\ta\u0002#\u0001\u0004Qe\u0016$WMZ\u0005\u0003=}\u00111!T1q\u0015\ta\u0002\u0003\u0005\u0002\"E1\u0001A!B\u0012\u0001\u0005\u0004!#!A&\u0012\u0005\u0015B\u0003CA\b'\u0013\t9\u0003CA\u0004O_RD\u0017N\\4\u0011\u0005=I\u0013B\u0001\u0016\u0011\u0005\r\te.\u001f\t\u0003C1\"Q!\f\u0001C\u0002\u0011\u0012\u0011A\u0016\t\u0003_Qr!\u0001\r\u001a\u000f\u0005]\t\u0014\"A\t\n\u0005M\u0002\u0012a\u00029bG.\fw-Z\u0005\u0003kY\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!a\r\t\u0002\u00035\u0004B!\u000f !W5\t!H\u0003\u0002<y\u00059Q.\u001e;bE2,'BA\u001f\u0011\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003=i\na\u0001P5oSRtDCA!D!\u0011\u0011\u0005\u0001I\u0016\u000e\u0003\u0019AQa\u000e\u0002A\u0002a\nq!\u001e9eCR,G-\u0006\u0002G\u0013R\u0019q\t\u0014(\u0011\tUi\u0002\u0005\u0013\t\u0003C%#QAS\u0002C\u0002-\u0013!A\u0016\u001a\u0012\u0005-B\u0003\"B'\u0004\u0001\u0004\u0001\u0013aA6fs\")qj\u0001a\u0001\u0011\u0006)a/\u00197vK\u00069!/Z7pm\u0016$GC\u0001\u000bS\u0011\u0015iE\u00011\u0001!\u0001"
)
public abstract class WrappedMutableMapBase implements Map, Serializable {
   private final scala.collection.mutable.Map m;

   public MapFactory mapFactory() {
      return Map.mapFactory$(this);
   }

   public final Map toMap(final .less.colon.less ev) {
      return Map.toMap$(this, ev);
   }

   public Map withDefault(final Function1 d) {
      return Map.withDefault$(this, d);
   }

   public Map withDefaultValue(final Object d) {
      return Map.withDefaultValue$(this, d);
   }

   public final MapOps $minus(final Object key) {
      return MapOps.$minus$(this, key);
   }

   /** @deprecated */
   public MapOps $minus(final Object key1, final Object key2, final Seq keys) {
      return MapOps.$minus$(this, key1, key2, keys);
   }

   public MapOps removedAll(final IterableOnce keys) {
      return MapOps.removedAll$(this, keys);
   }

   public final MapOps $minus$minus(final IterableOnce keys) {
      return MapOps.$minus$minus$(this, keys);
   }

   public MapOps updatedWith(final Object key, final Function1 remappingFunction) {
      return MapOps.updatedWith$(this, key, remappingFunction);
   }

   public MapOps $plus(final Tuple2 kv) {
      return MapOps.$plus$(this, kv);
   }

   public MapOps transform(final Function2 f) {
      return MapOps.transform$(this, f);
   }

   public Set keySet() {
      return MapOps.keySet$(this);
   }

   public boolean canEqual(final Object that) {
      return scala.collection.Map.canEqual$(this, that);
   }

   public boolean equals(final Object o) {
      return scala.collection.Map.equals$(this, o);
   }

   public int hashCode() {
      return scala.collection.Map.hashCode$(this);
   }

   public String stringPrefix() {
      return scala.collection.Map.stringPrefix$(this);
   }

   public String toString() {
      return scala.collection.Map.toString$(this);
   }

   public IterableOps fromSpecific(final IterableOnce coll) {
      return MapFactoryDefaults.fromSpecific$(this, coll);
   }

   public Builder newSpecificBuilder() {
      return MapFactoryDefaults.newSpecificBuilder$(this);
   }

   public IterableOps empty() {
      return MapFactoryDefaults.empty$(this);
   }

   public scala.collection.MapOps.WithFilter withFilter(final Function1 p) {
      return MapFactoryDefaults.withFilter$(this, p);
   }

   public MapView view() {
      return scala.collection.MapOps.view$(this);
   }

   public Stepper keyStepper(final StepperShape shape) {
      return scala.collection.MapOps.keyStepper$(this, shape);
   }

   public Stepper valueStepper(final StepperShape shape) {
      return scala.collection.MapOps.valueStepper$(this, shape);
   }

   public final IterableOps mapFromIterable(final Iterable it) {
      return scala.collection.MapOps.mapFromIterable$(this, it);
   }

   public Object getOrElse(final Object key, final Function0 default) {
      return scala.collection.MapOps.getOrElse$(this, key, default);
   }

   public Object apply(final Object key) throws NoSuchElementException {
      return scala.collection.MapOps.apply$(this, key);
   }

   public Object applyOrElse(final Object x, final Function1 default) {
      return scala.collection.MapOps.applyOrElse$(this, x, default);
   }

   public Iterable keys() {
      return scala.collection.MapOps.keys$(this);
   }

   public Iterable values() {
      return scala.collection.MapOps.values$(this);
   }

   public Iterator keysIterator() {
      return scala.collection.MapOps.keysIterator$(this);
   }

   public Iterator valuesIterator() {
      return scala.collection.MapOps.valuesIterator$(this);
   }

   public void foreachEntry(final Function2 f) {
      scala.collection.MapOps.foreachEntry$(this, f);
   }

   /** @deprecated */
   public MapView filterKeys(final Function1 p) {
      return scala.collection.MapOps.filterKeys$(this, p);
   }

   /** @deprecated */
   public MapView mapValues(final Function1 f) {
      return scala.collection.MapOps.mapValues$(this, f);
   }

   public Object default(final Object key) throws NoSuchElementException {
      return scala.collection.MapOps.default$(this, key);
   }

   public boolean contains(final Object key) {
      return scala.collection.MapOps.contains$(this, key);
   }

   public boolean isDefinedAt(final Object key) {
      return scala.collection.MapOps.isDefinedAt$(this, key);
   }

   public IterableOps map(final Function1 f) {
      return scala.collection.MapOps.map$(this, f);
   }

   public IterableOps collect(final PartialFunction pf) {
      return scala.collection.MapOps.collect$(this, pf);
   }

   public IterableOps flatMap(final Function1 f) {
      return scala.collection.MapOps.flatMap$(this, f);
   }

   public IterableOps concat(final IterableOnce suffix) {
      return scala.collection.MapOps.concat$(this, suffix);
   }

   public IterableOps $plus$plus(final IterableOnce xs) {
      return scala.collection.MapOps.$plus$plus$(this, xs);
   }

   public StringBuilder addString(final StringBuilder sb, final String start, final String sep, final String end) {
      return scala.collection.MapOps.addString$(this, sb, start, sep, end);
   }

   /** @deprecated */
   public IterableOps $plus(final Tuple2 elem1, final Tuple2 elem2, final Seq elems) {
      return scala.collection.MapOps.$plus$(this, elem1, elem2, elems);
   }

   /** @deprecated */
   public IterableOps $plus$plus$colon(final IterableOnce that) {
      return scala.collection.MapOps.$plus$plus$colon$(this, that);
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

   public IterableFactory iterableFactory() {
      return scala.collection.immutable.Iterable.iterableFactory$(this);
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

   public Map updated(final Object key, final Object value) {
      return (Map)this.m.toMap(scala..less.colon.less..MODULE$.refl()).$plus(new Tuple2(key, value));
   }

   public Map removed(final Object key) {
      return (Map)this.m.toMap(scala..less.colon.less..MODULE$.refl()).$minus(key);
   }

   public WrappedMutableMapBase(final scala.collection.mutable.Map m) {
      this.m = m;
      IterableOnce.$init$(this);
      IterableOnceOps.$init$(this);
      IterableOps.$init$(this);
      IterableFactoryDefaults.$init$(this);
      Iterable.$init$(this);
      scala.collection.immutable.Iterable.$init$(this);
      Function1.$init$(this);
      PartialFunction.$init$(this);
      scala.collection.MapOps.$init$(this);
      MapFactoryDefaults.$init$(this);
      scala.collection.Map.$init$(this);
      MapOps.$init$(this);
      Map.$init$(this);
   }
}
