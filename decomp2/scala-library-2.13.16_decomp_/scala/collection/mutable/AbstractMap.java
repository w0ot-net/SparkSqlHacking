package scala.collection.mutable;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.MapFactory;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00152QAA\u0002\u0002\u0002)AQA\t\u0001\u0005\u0002\r\u00121\"\u00112tiJ\f7\r^'ba*\u0011A!B\u0001\b[V$\u0018M\u00197f\u0015\t1q!\u0001\u0006d_2dWm\u0019;j_:T\u0011\u0001C\u0001\u0006g\u000e\fG.Y\u0002\u0001+\rY\u0011\u0003H\n\u0004\u00011q\u0002\u0003B\u0007\u000f\u001fmi\u0011!B\u0005\u0003\u0005\u0015\u0001\"\u0001E\t\r\u0001\u0011)!\u0003\u0001b\u0001'\t\t1*\u0005\u0002\u00151A\u0011QCF\u0007\u0002\u000f%\u0011qc\u0002\u0002\b\u001d>$\b.\u001b8h!\t)\u0012$\u0003\u0002\u001b\u000f\t\u0019\u0011I\\=\u0011\u0005AaB!B\u000f\u0001\u0005\u0004\u0019\"!\u0001,\u0011\t}\u0001sbG\u0007\u0002\u0007%\u0011\u0011e\u0001\u0002\u0004\u001b\u0006\u0004\u0018A\u0002\u001fj]&$h\bF\u0001%!\u0011y\u0002aD\u000e"
)
public abstract class AbstractMap extends scala.collection.AbstractMap implements Map {
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
   public final MapOps $minus(final Object key1, final Object key2, final scala.collection.immutable.Seq keys) {
      return MapOps.$minus$(this, key1, key2, keys);
   }

   public Option put(final Object key, final Object value) {
      return MapOps.put$(this, key, value);
   }

   public void update(final Object key, final Object value) {
      MapOps.update$(this, key, value);
   }

   public Option updateWith(final Object key, final Function1 remappingFunction) {
      return MapOps.updateWith$(this, key, remappingFunction);
   }

   public Object getOrElseUpdate(final Object key, final Function0 defaultValue) {
      return MapOps.getOrElseUpdate$(this, key, defaultValue);
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
   public Shrinkable $minus$eq(final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
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

   public final void sizeHintBounded(final int size, final scala.collection.Iterable boundingColl) {
      Builder.sizeHintBounded$(this, size, boundingColl);
   }

   public Builder mapResult(final Function1 f) {
      return Builder.mapResult$(this, f);
   }

   public final Growable $plus$eq(final Object elem) {
      return Growable.$plus$eq$(this, elem);
   }

   /** @deprecated */
   public final Growable $plus$eq(final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
      return Growable.$plus$eq$(this, elem1, elem2, elems);
   }

   public Growable addAll(final IterableOnce elems) {
      return Growable.addAll$(this, elems);
   }

   public final Growable $plus$plus$eq(final IterableOnce elems) {
      return Growable.$plus$plus$eq$(this, elems);
   }

   // $FF: synthetic method
   public Object scala$collection$mutable$Cloneable$$super$clone() {
      return super.clone();
   }

   public IterableFactory iterableFactory() {
      return Iterable.iterableFactory$(this);
   }
}
