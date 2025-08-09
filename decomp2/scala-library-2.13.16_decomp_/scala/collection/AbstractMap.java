package scala.collection;

import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00192QAA\u0002\u0002\u0002!AQa\t\u0001\u0005\u0002\u0011\u00121\"\u00112tiJ\f7\r^'ba*\u0011A!B\u0001\u000bG>dG.Z2uS>t'\"\u0001\u0004\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u0019\u0011\u0002\u0006\u0010\u0014\u0007\u0001Q\u0001\u0005E\u0002\f\u00199i\u0011aA\u0005\u0003\u001b\r\u0011\u0001#\u00112tiJ\f7\r^%uKJ\f'\r\\3\u0011\t=\u0001\"#H\u0007\u0002\u000b%\u0011\u0011#\u0002\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0005M!B\u0002\u0001\u0003\u0006+\u0001\u0011\rA\u0006\u0002\u0002\u0017F\u0011qC\u0007\t\u0003\u001faI!!G\u0003\u0003\u000f9{G\u000f[5oOB\u0011qbG\u0005\u00039\u0015\u00111!\u00118z!\t\u0019b\u0004\u0002\u0004 \u0001\u0011\u0015\rA\u0006\u0002\u0002-B!1\"\t\n\u001e\u0013\t\u00113AA\u0002NCB\fa\u0001P5oSRtD#A\u0013\u0011\t-\u0001!#\b"
)
public abstract class AbstractMap extends AbstractIterable implements Map {
   public MapFactory mapFactory() {
      return Map.mapFactory$(this);
   }

   public boolean canEqual(final Object that) {
      return Map.canEqual$(this, that);
   }

   public boolean equals(final Object o) {
      return Map.equals$(this, o);
   }

   public int hashCode() {
      return Map.hashCode$(this);
   }

   public String stringPrefix() {
      return Map.stringPrefix$(this);
   }

   public String toString() {
      return Map.toString$(this);
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

   public MapOps.WithFilter withFilter(final Function1 p) {
      return MapFactoryDefaults.withFilter$(this, p);
   }

   public MapView view() {
      return MapOps.view$(this);
   }

   public Stepper keyStepper(final StepperShape shape) {
      return MapOps.keyStepper$(this, shape);
   }

   public Stepper valueStepper(final StepperShape shape) {
      return MapOps.valueStepper$(this, shape);
   }

   public final IterableOps mapFromIterable(final Iterable it) {
      return MapOps.mapFromIterable$(this, it);
   }

   public Object getOrElse(final Object key, final Function0 default) {
      return MapOps.getOrElse$(this, key, default);
   }

   public Object apply(final Object key) throws NoSuchElementException {
      return MapOps.apply$(this, key);
   }

   public Object applyOrElse(final Object x, final Function1 default) {
      return MapOps.applyOrElse$(this, x, default);
   }

   public Set keySet() {
      return MapOps.keySet$(this);
   }

   public Iterable keys() {
      return MapOps.keys$(this);
   }

   public Iterable values() {
      return MapOps.values$(this);
   }

   public Iterator keysIterator() {
      return MapOps.keysIterator$(this);
   }

   public Iterator valuesIterator() {
      return MapOps.valuesIterator$(this);
   }

   public void foreachEntry(final Function2 f) {
      MapOps.foreachEntry$(this, f);
   }

   /** @deprecated */
   public MapView filterKeys(final Function1 p) {
      return MapOps.filterKeys$(this, p);
   }

   /** @deprecated */
   public MapView mapValues(final Function1 f) {
      return MapOps.mapValues$(this, f);
   }

   public Object default(final Object key) throws NoSuchElementException {
      return MapOps.default$(this, key);
   }

   public boolean contains(final Object key) {
      return MapOps.contains$(this, key);
   }

   public boolean isDefinedAt(final Object key) {
      return MapOps.isDefinedAt$(this, key);
   }

   public IterableOps map(final Function1 f) {
      return MapOps.map$(this, f);
   }

   public IterableOps collect(final PartialFunction pf) {
      return MapOps.collect$(this, pf);
   }

   public IterableOps flatMap(final Function1 f) {
      return MapOps.flatMap$(this, f);
   }

   public IterableOps concat(final IterableOnce suffix) {
      return MapOps.concat$(this, suffix);
   }

   public IterableOps $plus$plus(final IterableOnce xs) {
      return MapOps.$plus$plus$(this, xs);
   }

   public StringBuilder addString(final StringBuilder sb, final String start, final String sep, final String end) {
      return MapOps.addString$(this, sb, start, sep, end);
   }

   /** @deprecated */
   public IterableOps $plus(final Tuple2 kv) {
      return MapOps.$plus$(this, kv);
   }

   /** @deprecated */
   public IterableOps $plus(final Tuple2 elem1, final Tuple2 elem2, final scala.collection.immutable.Seq elems) {
      return MapOps.$plus$(this, elem1, elem2, elems);
   }

   /** @deprecated */
   public Object $minus$minus(final IterableOnce keys) {
      return MapOps.$minus$minus$(this, keys);
   }

   /** @deprecated */
   public IterableOps $plus$plus$colon(final IterableOnce that) {
      return MapOps.$plus$plus$colon$(this, that);
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
      return PartialFunction.andThen$(this, (Function1)k);
   }

   public PartialFunction andThen(final PartialFunction k) {
      return PartialFunction.andThen$(this, (PartialFunction)k);
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
}
