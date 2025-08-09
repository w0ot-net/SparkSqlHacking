package org.apache.spark.graphx.util.collection;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.util.collection.OpenHashSet;
import org.apache.spark.util.collection.OpenHashSet.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.reflect.ClassTag;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

public class GraphXPrimitiveKeyOpenHashMap$mcJJ$sp extends GraphXPrimitiveKeyOpenHashMap {
   public final OpenHashSet keySet$mcJ$sp;
   public long[] _values$mcJ$sp;
   public long[] _oldValues$mcJ$sp;

   public OpenHashSet keySet$mcJ$sp() {
      return this.keySet$mcJ$sp;
   }

   public OpenHashSet keySet() {
      return this.keySet$mcJ$sp();
   }

   public long[] _values$mcJ$sp() {
      return this._values$mcJ$sp;
   }

   public long[] _values() {
      return this._values$mcJ$sp();
   }

   public void _values$mcJ$sp_$eq(final long[] x$1) {
      this._values$mcJ$sp = x$1;
   }

   public void _values_$eq(final long[] x$1) {
      this._values$mcJ$sp_$eq(x$1);
   }

   public long[] _oldValues$mcJ$sp() {
      return this._oldValues$mcJ$sp;
   }

   public long[] _oldValues() {
      return this._oldValues$mcJ$sp();
   }

   public void _oldValues$mcJ$sp_$eq(final long[] x$1) {
      this._oldValues$mcJ$sp = x$1;
   }

   public void _oldValues_$eq(final long[] x$1) {
      this._oldValues$mcJ$sp_$eq(x$1);
   }

   public long apply(final long k) {
      return this.apply$mcJJ$sp(k);
   }

   public long apply$mcJJ$sp(final long k) {
      int pos = this.keySet().getPos$mcJ$sp(k);
      return this._values()[pos];
   }

   public long getOrElse(final long k, final long elseValue) {
      return this.getOrElse$mcJJ$sp(k, elseValue);
   }

   public long getOrElse$mcJJ$sp(final long k, final long elseValue) {
      int pos = this.keySet().getPos$mcJ$sp(k);
      return pos >= 0 ? this._values()[pos] : elseValue;
   }

   public void update(final long k, final long v) {
      this.update$mcJJ$sp(k, v);
   }

   public void update$mcJJ$sp(final long k, final long v) {
      int pos = this.keySet().addWithoutResize$mcJ$sp(k) & .MODULE$.POSITION_MASK();
      this._values()[pos] = v;
      this.keySet().rehashIfNeeded$mcJ$sp(k, (JFunction1.mcVI.sp)(newCapacity) -> this.org$apache$spark$graphx$util$collection$GraphXPrimitiveKeyOpenHashMap$$grow(newCapacity), (JFunction2.mcVII.sp)(oldPos, newPos) -> this.org$apache$spark$graphx$util$collection$GraphXPrimitiveKeyOpenHashMap$$move(oldPos, newPos));
      this._oldValues_$eq((long[])null);
   }

   public void setMerge(final long k, final long v, final Function2 mergeF) {
      this.setMerge$mcJJ$sp(k, v, mergeF);
   }

   public void setMerge$mcJJ$sp(final long k, final long v, final Function2 mergeF) {
      int pos = this.keySet().addWithoutResize$mcJ$sp(k);
      int ind = pos & .MODULE$.POSITION_MASK();
      if ((pos & .MODULE$.NONEXISTENCE_MASK()) != 0) {
         this._values()[ind] = v;
      } else {
         this._values()[ind] = mergeF.apply$mcJJJ$sp(this._values()[ind], v);
      }

      this.keySet().rehashIfNeeded$mcJ$sp(k, (JFunction1.mcVI.sp)(newCapacity) -> this.org$apache$spark$graphx$util$collection$GraphXPrimitiveKeyOpenHashMap$$grow(newCapacity), (JFunction2.mcVII.sp)(oldPos, newPos) -> this.org$apache$spark$graphx$util$collection$GraphXPrimitiveKeyOpenHashMap$$move(oldPos, newPos));
      this._oldValues_$eq((long[])null);
   }

   public long changeValue(final long k, final Function0 defaultValue, final Function1 mergeValue) {
      return this.changeValue$mcJJ$sp(k, defaultValue, mergeValue);
   }

   public long changeValue$mcJJ$sp(final long k, final Function0 defaultValue, final Function1 mergeValue) {
      int pos = this.keySet().addWithoutResize$mcJ$sp(k);
      if ((pos & .MODULE$.NONEXISTENCE_MASK()) != 0) {
         long newValue = defaultValue.apply$mcJ$sp();
         this._values()[pos & .MODULE$.POSITION_MASK()] = newValue;
         this.keySet().rehashIfNeeded$mcJ$sp(k, (JFunction1.mcVI.sp)(newCapacity) -> this.org$apache$spark$graphx$util$collection$GraphXPrimitiveKeyOpenHashMap$$grow(newCapacity), (JFunction2.mcVII.sp)(oldPos, newPos) -> this.org$apache$spark$graphx$util$collection$GraphXPrimitiveKeyOpenHashMap$$move(oldPos, newPos));
         return newValue;
      } else {
         this._values()[pos] = mergeValue.apply$mcJJ$sp(this._values()[pos]);
         return this._values()[pos];
      }
   }

   public boolean specInstance$() {
      return true;
   }

   public GraphXPrimitiveKeyOpenHashMap$mcJJ$sp(final OpenHashSet keySet$mcJ$sp, final long[] _values$mcJ$sp, final ClassTag evidence$1, final ClassTag evidence$2) {
      this.keySet$mcJ$sp = keySet$mcJ$sp;
      this._values$mcJ$sp = _values$mcJ$sp;
      super((OpenHashSet)null, (Object)null, evidence$1, evidence$2);
      this._oldValues$mcJ$sp = null;
   }

   public GraphXPrimitiveKeyOpenHashMap$mcJJ$sp(final int initialCapacity, final ClassTag evidence$3, final ClassTag evidence$4) {
      this(new OpenHashSet(initialCapacity, evidence$3), (long[])evidence$4.newArray(initialCapacity), evidence$3, evidence$4);
   }

   public GraphXPrimitiveKeyOpenHashMap$mcJJ$sp(final ClassTag evidence$5, final ClassTag evidence$6) {
      this(64, evidence$5, evidence$6);
   }

   public GraphXPrimitiveKeyOpenHashMap$mcJJ$sp(final OpenHashSet keySet$mcJ$sp, final ClassTag evidence$7, final ClassTag evidence$8) {
      this(keySet$mcJ$sp, (long[])evidence$8.newArray(keySet$mcJ$sp.capacity()), evidence$7, evidence$8);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
