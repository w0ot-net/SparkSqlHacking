package scala.collection;

import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000512QAA\u0002\u0002\u0002!AQa\t\u0001\u0005\u0002\u0011\u0012q\"\u00112tiJ\f7\r^'baZKWm\u001e\u0006\u0003\t\u0015\t!bY8mY\u0016\u001cG/[8o\u0015\u00051\u0011!B:dC2\f7\u0001A\u000b\u0004\u0013Qq2c\u0001\u0001\u000bAA\u00191\u0002\u0004\b\u000e\u0003\rI!!D\u0002\u0003\u0019\u0005\u00137\u000f\u001e:bGR4\u0016.Z<\u0011\t=\u0001\"#H\u0007\u0002\u000b%\u0011\u0011#\u0002\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0005M!B\u0002\u0001\u0003\u0006+\u0001\u0011\rA\u0006\u0002\u0002\u0017F\u0011qC\u0007\t\u0003\u001faI!!G\u0003\u0003\u000f9{G\u000f[5oOB\u0011qbG\u0005\u00039\u0015\u00111!\u00118z!\t\u0019b\u0004\u0002\u0004 \u0001\u0011\u0015\rA\u0006\u0002\u0002-B!1\"\t\n\u001e\u0013\t\u00113AA\u0004NCB4\u0016.Z<\u0002\rqJg.\u001b;?)\u0005)\u0003\u0003B\u0006\u0001%uAC\u0001A\u0014+WA\u0011q\u0002K\u0005\u0003S\u0015\u0011\u0001cU3sS\u0006dg+\u001a:tS>tW+\u0013#\u0002\u000bY\fG.^3\u001f\u0003\r\u0001"
)
public abstract class AbstractMapView extends AbstractView implements MapView {
   private static final long serialVersionUID = 3L;

   public MapView view() {
      return MapView.view$(this);
   }

   public Iterable keys() {
      return MapView.keys$(this);
   }

   public Iterable values() {
      return MapView.values$(this);
   }

   public MapView filterKeys(final Function1 p) {
      return MapView.filterKeys$(this, p);
   }

   public MapView mapValues(final Function1 f) {
      return MapView.mapValues$(this, f);
   }

   public MapView filter(final Function1 pred) {
      return MapView.filter$(this, pred);
   }

   public MapView filterNot(final Function1 pred) {
      return MapView.filterNot$(this, pred);
   }

   public Tuple2 partition(final Function1 p) {
      return MapView.partition$(this, p);
   }

   public MapView tapEach(final Function1 f) {
      return MapView.tapEach$(this, f);
   }

   public MapViewFactory mapFactory() {
      return MapView.mapFactory$(this);
   }

   public MapView empty() {
      return MapView.empty$(this);
   }

   public MapOps.WithFilter withFilter(final Function1 p) {
      return MapView.withFilter$(this, p);
   }

   public String toString() {
      return MapView.toString$(this);
   }

   public String stringPrefix() {
      return MapView.stringPrefix$(this);
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

   public Iterator keysIterator() {
      return MapOps.keysIterator$(this);
   }

   public Iterator valuesIterator() {
      return MapOps.valuesIterator$(this);
   }

   public void foreachEntry(final Function2 f) {
      MapOps.foreachEntry$(this, f);
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
