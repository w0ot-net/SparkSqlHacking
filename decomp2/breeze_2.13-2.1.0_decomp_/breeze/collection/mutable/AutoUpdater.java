package breeze.collection.mutable;

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
import scala.collection.Set;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.View;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
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

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=c\u0001B\n\u0015\u0001mA\u0001b\u000e\u0001\u0003\u0006\u0004%\t\u0001\u000f\u0005\ty\u0001\u0011\t\u0011)A\u0005s!AQ\b\u0001B\u0001J\u0003%a\b\u0003\u0005B\u0001\t\u0005\t\u0015a\u0003C\u0011\u0015)\u0005\u0001\"\u0001G\u0011\u0015i\u0005\u0001\"\u0011O\u0011\u0015\t\u0006\u0001\"\u0011S\u0011\u0015I\u0006\u0001\"\u0011[\u0011\u0015\t\u0007\u0001\"\u0001c\u0011\u0015A\u0007\u0001\"\u0001j\u0011\u0015q\u0007\u0001\"\u0011p\u0011\u0015\t\b\u0001\"\u0011s\u000f\u00151H\u0003#\u0001x\r\u0015\u0019B\u0003#\u0001y\u0011\u0015)e\u0002\"\u0001z\u0011\u0015ie\u0002\"\u0001{\u0011\u0019ie\u0002\"\u0001\u0002\u0018!9\u00111\u0006\b\u0005\u0002\u00055\"aC!vi>,\u0006\u000fZ1uKJT!!\u0006\f\u0002\u000f5,H/\u00192mK*\u0011q\u0003G\u0001\u000bG>dG.Z2uS>t'\"A\r\u0002\r\t\u0014X-\u001a>f\u0007\u0001)B\u0001\b\u001e,kM\u0019\u0001!H\u0012\u0011\u0005y\tS\"A\u0010\u000b\u0003\u0001\nQa]2bY\u0006L!AI\u0010\u0003\r\u0005s\u0017PU3g!\u0011!s%\u000b\u001b\u000e\u0003\u0015R!!\u0006\u0014\u000b\u0005]y\u0012B\u0001\u0015&\u0005\ri\u0015\r\u001d\t\u0003U-b\u0001\u0001B\u0003-\u0001\t\u0007QFA\u0001L#\tq\u0013\u0007\u0005\u0002\u001f_%\u0011\u0001g\b\u0002\b\u001d>$\b.\u001b8h!\tq\"'\u0003\u00024?\t\u0019\u0011I\\=\u0011\u0005)*D!\u0002\u001c\u0001\u0005\u0004i#!\u0001,\u0002\rQDW-T1q+\u0005I\u0004C\u0001\u0016;\t\u0015Y\u0004A1\u0001.\u0005\u0005i\u0015a\u0002;iK6\u000b\u0007\u000fI\u0001\bI\u00164\u0017-\u001e7u!\rqr\bN\u0005\u0003\u0001~\u0011\u0001\u0002\u00102z]\u0006lWMP\u0001\u0003KZ\u0004BAH\":G%\u0011Ai\b\u0002\u0011I1,7o\u001d\u0013d_2|g\u000e\n7fgN\fa\u0001P5oSRtDcA$L\u0019R\u0011\u0001J\u0013\t\u0006\u0013\u0002I\u0014\u0006N\u0007\u0002)!)\u0011)\u0002a\u0002\u0005\")q'\u0002a\u0001s!1Q(\u0002CA\u0002y\nQ!\u00199qYf$\"\u0001N(\t\u000bA3\u0001\u0019A\u0015\u0002\u0003-\fa!\u001e9eCR,GcA*W/B\u0011a\u0004V\u0005\u0003+~\u0011A!\u00168ji\")\u0001k\u0002a\u0001S!)\u0001l\u0002a\u0001i\u0005\ta/\u0001\u0004bI\u0012|e.\u001a\u000b\u00037rk\u0011\u0001\u0001\u0005\u0006;\"\u0001\rAX\u0001\u0003WZ\u0004BAH0*i%\u0011\u0001m\b\u0002\u0007)V\u0004H.\u001a\u001a\u0002\u0007\u001d,G\u000f\u0006\u0002dMB\u0019a\u0004\u001a\u001b\n\u0005\u0015|\"AB(qi&|g\u000eC\u0003h\u0013\u0001\u0007\u0011&A\u0002lKf\f\u0001\"\u001b;fe\u0006$xN]\u000b\u0002UB\u00191\u000e\u001c0\u000e\u0003\u0019J!!\u001c\u0014\u0003\u0011%#XM]1u_J\f1b];ciJ\f7\r^(oKR\u00111\f\u001d\u0005\u0006O.\u0001\r!K\u0001\u0005g&TX-F\u0001t!\tqB/\u0003\u0002v?\t\u0019\u0011J\u001c;\u0002\u0017\u0005+Ho\\+qI\u0006$XM\u001d\t\u0003\u0013:\u0019\"AD\u000f\u0015\u0003],ba_@\u0002\u0004\u0005\u001dA#\u0002?\u0002\u0010\u0005MAcA?\u0002\nA9\u0011\n\u0001@\u0002\u0002\u0005\u0015\u0001C\u0001\u0016\u0000\t\u0015Y\u0004C1\u0001.!\rQ\u00131\u0001\u0003\u0006YA\u0011\r!\f\t\u0004U\u0005\u001dA!\u0002\u001c\u0011\u0005\u0004i\u0003BB!\u0011\u0001\b\tY\u0001E\u0003\u001f\u0007z\fi\u0001\u0005\u0004%O\u0005\u0005\u0011Q\u0001\u0005\u0007\u0003#\u0001\u0002\u0019\u0001@\u0002\u00075\f\u0007\u000fC\u0004>!\u0011\u0005\r!!\u0006\u0011\tyy\u0014QA\u000b\u0007\u00033\t\t#!\n\u0015\t\u0005m\u0011q\u0005\t\t\u0013\u0002\ti\"a\b\u0002$A1AeJA\u0010\u0003G\u00012AKA\u0011\t\u0015a\u0013C1\u0001.!\rQ\u0013Q\u0005\u0003\u0006mE\u0011\r!\f\u0005\b{E!\t\u0019AA\u0015!\u0011qr(a\t\u0002\r=47*Z=t+\u0011\ty#!\u0012\u0016\u0005\u0005E\"cAA\u001a;\u00191\u0011Q\u0007\n\u0001\u0003c\u0011A\u0002\u0010:fM&tW-\\3oizB\u0001\"!\u000f\u00024\u0011\u0005\u00111H\u0001\nC:$g+\u00197vKN,B!!\u0010\u0002JQ!\u0011qHA&!!I\u0005!!\u0011\u0002D\u0005\u001d\u0003C\u0002\u0013(\u0003\u0007\n9\u0005E\u0002+\u0003\u000b\"Q\u0001\f\nC\u00025\u00022AKA%\t\u00191\u0014q\u0007b\u0001[!A\u0001,a\u000e\u0005\u0002\u0004\ti\u0005\u0005\u0003\u001f\u007f\u0005\u001d\u0003"
)
public class AutoUpdater implements Map {
   private final Object theMap;
   private final Function0 default;
   private final .less.colon.less ev;

   public static Object ofKeys() {
      return AutoUpdater$.MODULE$.ofKeys();
   }

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

   public Object theMap() {
      return this.theMap;
   }

   public Object apply(final Object k) {
      return ((MapOps)this.ev.apply(this.theMap())).getOrElseUpdate(k, this.default);
   }

   public void update(final Object k, final Object v) {
      ((MapOps)this.ev.apply(this.theMap())).update(k, v);
   }

   public AutoUpdater addOne(final Tuple2 kv) {
      ((Growable)this.ev.apply(this.theMap())).$plus$eq(kv);
      return this;
   }

   public Option get(final Object key) {
      return ((scala.collection.MapOps)this.ev.apply(this.theMap())).get(key);
   }

   public Iterator iterator() {
      return ((IterableOnce)this.ev.apply(this.theMap())).iterator();
   }

   public AutoUpdater subtractOne(final Object key) {
      ((Shrinkable)this.ev.apply(this.theMap())).$minus$eq(key);
      return this;
   }

   public int size() {
      return ((IterableOnceOps)this.ev.apply(this.theMap())).size();
   }

   public AutoUpdater(final Object theMap, final Function0 default, final .less.colon.less ev) {
      this.theMap = theMap;
      this.default = default;
      this.ev = ev;
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
}
