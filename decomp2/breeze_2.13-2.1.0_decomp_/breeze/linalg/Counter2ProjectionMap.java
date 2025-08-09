package breeze.linalg;

import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
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
import scala.collection.Set;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Stream;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Cloneable;
import scala.collection.mutable.Growable;
import scala.collection.mutable.Map;
import scala.collection.mutable.MapOps;
import scala.collection.mutable.Shrinkable;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005}3AAC\u0006\u0005!!Aa\u0006\u0001B\u0001B\u0003%q\u0006\u0003\u00057\u0001\t\u0005\t\u0015!\u00034\u0011\u00159\u0004\u0001\"\u00019\u0011\u0015a\u0004\u0001\"\u0001>\u0011\u0015!\u0005\u0001\"\u0011F\u0011\u0015Q\u0005\u0001\"\u0011L\u0011\u0015\t\u0006\u0001\"\u0011S\u0011\u0015!\u0006\u0001\"\u0011V\u0011\u0015a\u0006\u0001\"\u0011^\u0005U\u0019u.\u001e8uKJ\u0014\u0004K]8kK\u000e$\u0018n\u001c8NCBT!\u0001D\u0007\u0002\r1Lg.\u00197h\u0015\u0005q\u0011A\u00022sK\u0016TXm\u0001\u0001\u0016\tE\u0011C\u0007L\n\u0004\u0001IA\u0002CA\n\u0017\u001b\u0005!\"\"A\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005]!\"AB!osJ+g\r\u0005\u0003\u001a=\u0001ZS\"\u0001\u000e\u000b\u0005ma\u0012aB7vi\u0006\u0014G.\u001a\u0006\u0003;Q\t!bY8mY\u0016\u001cG/[8o\u0013\ty\"DA\u0002NCB\u0004\"!\t\u0012\r\u0001\u0011)1\u0005\u0001b\u0001I\t\u00111*M\t\u0003K!\u0002\"a\u0005\u0014\n\u0005\u001d\"\"a\u0002(pi\"Lgn\u001a\t\u0003'%J!A\u000b\u000b\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\"Y\u0011)Q\u0006\u0001b\u0001I\t\ta+A\u0004d_VtG/\u001a:\u0011\u000bA\n\u0004eM\u0016\u000e\u0003-I!AM\u0006\u0003\u0011\r{WO\u001c;feJ\u0002\"!\t\u001b\u0005\u000bU\u0002!\u0019\u0001\u0013\u0003\u0005-\u0013\u0014aA2pY\u00061A(\u001b8jiz\"2!\u000f\u001e<!\u0015\u0001\u0004\u0001I\u001a,\u0011\u0015q3\u00011\u00010\u0011\u001514\u00011\u00014\u0003\u0019\tG\rZ(oKR\u0011ahP\u0007\u0002\u0001!)\u0001\t\u0002a\u0001\u0003\u0006!Q\r\\3n!\u0011\u0019\"\tI\u0016\n\u0005\r#\"A\u0002+va2,''\u0001\u0005ji\u0016\u0014\u0018\r^8s+\u00051\u0005cA$I\u00036\tA$\u0003\u0002J9\tA\u0011\n^3sCR|'/A\u0002hKR$\"\u0001T(\u0011\u0007Mi5&\u0003\u0002O)\t1q\n\u001d;j_:DQ\u0001\u0015\u0004A\u0002\u0001\n!a[\u0019\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0005-\u001a\u0006\"\u0002)\b\u0001\u0004\u0001\u0013AB;qI\u0006$X\rF\u0002W3j\u0003\"aE,\n\u0005a#\"\u0001B+oSRDQ\u0001\u0015\u0005A\u0002\u0001BQa\u0017\u0005A\u0002-\n\u0011A^\u0001\fgV\u0014GO]1di>sW\r\u0006\u0002?=\")\u0001+\u0003a\u0001A\u0001"
)
public class Counter2ProjectionMap implements Map {
   private final Counter2 counter;
   private final Object col;

   public MapFactory mapFactory() {
      return Map.mapFactory$(this);
   }

   public Map withDefault(final Function1 d) {
      return Map.withDefault$(this, d);
   }

   public Map withDefaultValue(final Object d) {
      return Map.withDefaultValue$(this, d);
   }

   public MapOps result() {
      return MapOps.result$(this);
   }

   /** @deprecated */
   public final MapOps $minus(final Object key) {
      return MapOps.$minus$(this, key);
   }

   /** @deprecated */
   public final MapOps $minus(final Object key1, final Object key2, final Seq keys) {
      return MapOps.$minus$(this, key1, key2, keys);
   }

   public Option put(final Object key, final Object value) {
      return MapOps.put$(this, key, value);
   }

   public Option updateWith(final Object key, final Function1 remappingFunction) {
      return MapOps.updateWith$(this, key, remappingFunction);
   }

   public Object getOrElseUpdate(final Object key, final Function0 op) {
      return MapOps.getOrElseUpdate$(this, key, op);
   }

   public Option remove(final Object key) {
      return MapOps.remove$(this, key);
   }

   public void clear() {
      MapOps.clear$(this);
   }

   public MapOps clone() {
      return MapOps.clone$(this);
   }

   /** @deprecated */
   public final MapOps retain(final Function2 p) {
      return MapOps.retain$(this, p);
   }

   public MapOps filterInPlace(final Function2 p) {
      return MapOps.filterInPlace$(this, p);
   }

   /** @deprecated */
   public final MapOps transform(final Function2 f) {
      return MapOps.transform$(this, f);
   }

   public MapOps mapValuesInPlace(final Function2 f) {
      return MapOps.mapValuesInPlace$(this, f);
   }

   /** @deprecated */
   public MapOps updated(final Object key, final Object value) {
      return MapOps.updated$(this, key, value);
   }

   public int knownSize() {
      return MapOps.knownSize$(this);
   }

   public final Shrinkable $minus$eq(final Object elem) {
      return Shrinkable.$minus$eq$(this, elem);
   }

   /** @deprecated */
   public Shrinkable $minus$eq(final Object elem1, final Object elem2, final Seq elems) {
      return Shrinkable.$minus$eq$(this, elem1, elem2, elems);
   }

   public Shrinkable subtractAll(final IterableOnce xs) {
      return Shrinkable.subtractAll$(this, xs);
   }

   public final Shrinkable $minus$minus$eq(final IterableOnce xs) {
      return Shrinkable.$minus$minus$eq$(this, xs);
   }

   public void sizeHint(final int size) {
      Builder.sizeHint$(this, size);
   }

   public final void sizeHint(final IterableOnce coll, final int delta) {
      Builder.sizeHint$(this, coll, delta);
   }

   public final int sizeHint$default$2() {
      return Builder.sizeHint$default$2$(this);
   }

   public final void sizeHintBounded(final int size, final Iterable boundingColl) {
      Builder.sizeHintBounded$(this, size, boundingColl);
   }

   public Builder mapResult(final Function1 f) {
      return Builder.mapResult$(this, f);
   }

   public final Growable $plus$eq(final Object elem) {
      return Growable.$plus$eq$(this, elem);
   }

   /** @deprecated */
   public final Growable $plus$eq(final Object elem1, final Object elem2, final Seq elems) {
      return Growable.$plus$eq$(this, elem1, elem2, elems);
   }

   public Growable addAll(final IterableOnce xs) {
      return Growable.addAll$(this, xs);
   }

   public final Growable $plus$plus$eq(final IterableOnce xs) {
      return Growable.$plus$plus$eq$(this, xs);
   }

   // $FF: synthetic method
   public Object scala$collection$mutable$Cloneable$$super$clone() {
      return super.clone();
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

   public Object applyOrElse(final Object x, final Function1 default) {
      return scala.collection.MapOps.applyOrElse$(this, x, default);
   }

   public Set keySet() {
      return scala.collection.MapOps.keySet$(this);
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
   public IterableOps $plus(final Tuple2 kv) {
      return scala.collection.MapOps.$plus$(this, kv);
   }

   /** @deprecated */
   public IterableOps $plus(final Tuple2 elem1, final Tuple2 elem2, final Seq elems) {
      return scala.collection.MapOps.$plus$(this, elem1, elem2, elems);
   }

   /** @deprecated */
   public Object $minus$minus(final IterableOnce keys) {
      return scala.collection.MapOps.$minus$minus$(this, keys);
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
      return scala.collection.mutable.Iterable.iterableFactory$(this);
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
   public scala.collection.View view(final int from, final int until) {
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

   public scala.collection.immutable.Vector toVector() {
      return IterableOnceOps.toVector$(this);
   }

   public scala.collection.immutable.Map toMap(final .less.colon.less ev) {
      return IterableOnceOps.toMap$(this, ev);
   }

   public scala.collection.immutable.Set toSet() {
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

   public Counter2ProjectionMap addOne(final Tuple2 elem) {
      ((CounterLike)this.counter.data().apply(elem._1())).update(this.col, elem._2());
      return this;
   }

   public Iterator iterator() {
      return this.counter.data().iterator().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$iterator$1(check$ifrefutable$1))).flatMap((x$1) -> {
         if (x$1 != null) {
            Object k1 = x$1._1();
            Counter map = (Counter)x$1._2();
            Option var2 = map.get(this.col).map((v) -> new Tuple2(k1, v));
            return var2;
         } else {
            throw new MatchError(x$1);
         }
      });
   }

   public Option get(final Object k1) {
      return this.counter.data().get(k1).map((x$2) -> x$2.apply(this.col));
   }

   public Object apply(final Object k1) {
      return this.counter.apply(k1, this.col);
   }

   public void update(final Object k1, final Object v) {
      this.counter.update(k1, this.col, v);
   }

   public Counter2ProjectionMap subtractOne(final Object k1) {
      ((CounterLike)this.counter.data().apply(k1)).update(this.col, this.counter.default());
      return this;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$iterator$1(final Tuple2 check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public Counter2ProjectionMap(final Counter2 counter, final Object col) {
      this.counter = counter;
      this.col = col;
      IterableOnce.$init$(this);
      IterableOnceOps.$init$(this);
      IterableOps.$init$(this);
      IterableFactoryDefaults.$init$(this);
      Iterable.$init$(this);
      scala.collection.mutable.Iterable.$init$(this);
      Function1.$init$(this);
      PartialFunction.$init$(this);
      scala.collection.MapOps.$init$(this);
      MapFactoryDefaults.$init$(this);
      scala.collection.Map.$init$(this);
      Cloneable.$init$(this);
      Growable.$init$(this);
      Builder.$init$(this);
      Shrinkable.$init$(this);
      MapOps.$init$(this);
      Map.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
