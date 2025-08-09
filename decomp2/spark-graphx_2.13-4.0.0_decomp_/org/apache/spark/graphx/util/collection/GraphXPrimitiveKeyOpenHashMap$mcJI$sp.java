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

public class GraphXPrimitiveKeyOpenHashMap$mcJI$sp extends GraphXPrimitiveKeyOpenHashMap {
   public final OpenHashSet keySet$mcJ$sp;
   public int[] _values$mcI$sp;
   public int[] _oldValues$mcI$sp;

   public OpenHashSet keySet$mcJ$sp() {
      return this.keySet$mcJ$sp;
   }

   public OpenHashSet keySet() {
      return this.keySet$mcJ$sp();
   }

   public int[] _values$mcI$sp() {
      return this._values$mcI$sp;
   }

   public int[] _values() {
      return this._values$mcI$sp();
   }

   public void _values$mcI$sp_$eq(final int[] x$1) {
      this._values$mcI$sp = x$1;
   }

   public void _values_$eq(final int[] x$1) {
      this._values$mcI$sp_$eq(x$1);
   }

   public int[] _oldValues$mcI$sp() {
      return this._oldValues$mcI$sp;
   }

   public int[] _oldValues() {
      return this._oldValues$mcI$sp();
   }

   public void _oldValues$mcI$sp_$eq(final int[] x$1) {
      this._oldValues$mcI$sp = x$1;
   }

   public void _oldValues_$eq(final int[] x$1) {
      this._oldValues$mcI$sp_$eq(x$1);
   }

   public int apply(final long k) {
      return this.apply$mcJI$sp(k);
   }

   public int apply$mcJI$sp(final long k) {
      int pos = this.keySet().getPos$mcJ$sp(k);
      return this._values()[pos];
   }

   public int getOrElse(final long k, final int elseValue) {
      return this.getOrElse$mcJI$sp(k, elseValue);
   }

   public int getOrElse$mcJI$sp(final long k, final int elseValue) {
      int pos = this.keySet().getPos$mcJ$sp(k);
      return pos >= 0 ? this._values()[pos] : elseValue;
   }

   public void update(final long k, final int v) {
      this.update$mcJI$sp(k, v);
   }

   public void update$mcJI$sp(final long k, final int v) {
      int pos = this.keySet().addWithoutResize$mcJ$sp(k) & .MODULE$.POSITION_MASK();
      this._values()[pos] = v;
      this.keySet().rehashIfNeeded$mcJ$sp(k, (JFunction1.mcVI.sp)(newCapacity) -> this.org$apache$spark$graphx$util$collection$GraphXPrimitiveKeyOpenHashMap$$grow(newCapacity), (JFunction2.mcVII.sp)(oldPos, newPos) -> this.org$apache$spark$graphx$util$collection$GraphXPrimitiveKeyOpenHashMap$$move(oldPos, newPos));
      this._oldValues_$eq((int[])null);
   }

   public void setMerge(final long k, final int v, final Function2 mergeF) {
      this.setMerge$mcJI$sp(k, v, mergeF);
   }

   public void setMerge$mcJI$sp(final long k, final int v, final Function2 mergeF) {
      int pos = this.keySet().addWithoutResize$mcJ$sp(k);
      int ind = pos & .MODULE$.POSITION_MASK();
      if ((pos & .MODULE$.NONEXISTENCE_MASK()) != 0) {
         this._values()[ind] = v;
      } else {
         this._values()[ind] = mergeF.apply$mcIII$sp(this._values()[ind], v);
      }

      this.keySet().rehashIfNeeded$mcJ$sp(k, (JFunction1.mcVI.sp)(newCapacity) -> this.org$apache$spark$graphx$util$collection$GraphXPrimitiveKeyOpenHashMap$$grow(newCapacity), (JFunction2.mcVII.sp)(oldPos, newPos) -> this.org$apache$spark$graphx$util$collection$GraphXPrimitiveKeyOpenHashMap$$move(oldPos, newPos));
      this._oldValues_$eq((int[])null);
   }

   public int changeValue(final long k, final Function0 defaultValue, final Function1 mergeValue) {
      return this.changeValue$mcJI$sp(k, defaultValue, mergeValue);
   }

   public int changeValue$mcJI$sp(final long k, final Function0 defaultValue, final Function1 mergeValue) {
      int pos = this.keySet().addWithoutResize$mcJ$sp(k);
      if ((pos & .MODULE$.NONEXISTENCE_MASK()) != 0) {
         int newValue = defaultValue.apply$mcI$sp();
         this._values()[pos & .MODULE$.POSITION_MASK()] = newValue;
         this.keySet().rehashIfNeeded$mcJ$sp(k, (JFunction1.mcVI.sp)(newCapacity) -> this.org$apache$spark$graphx$util$collection$GraphXPrimitiveKeyOpenHashMap$$grow(newCapacity), (JFunction2.mcVII.sp)(oldPos, newPos) -> this.org$apache$spark$graphx$util$collection$GraphXPrimitiveKeyOpenHashMap$$move(oldPos, newPos));
         return newValue;
      } else {
         this._values()[pos] = mergeValue.apply$mcII$sp(this._values()[pos]);
         return this._values()[pos];
      }
   }

   public boolean specInstance$() {
      return true;
   }

   public GraphXPrimitiveKeyOpenHashMap$mcJI$sp(final OpenHashSet keySet$mcJ$sp, final int[] _values$mcI$sp, final ClassTag evidence$1, final ClassTag evidence$2) {
      this.keySet$mcJ$sp = keySet$mcJ$sp;
      this._values$mcI$sp = _values$mcI$sp;
      super((OpenHashSet)null, (Object)null, evidence$1, evidence$2);
      this._oldValues$mcI$sp = null;
   }

   public GraphXPrimitiveKeyOpenHashMap$mcJI$sp(final int initialCapacity, final ClassTag evidence$3, final ClassTag evidence$4) {
      this(new OpenHashSet(initialCapacity, evidence$3), (int[])evidence$4.newArray(initialCapacity), evidence$3, evidence$4);
   }

   public GraphXPrimitiveKeyOpenHashMap$mcJI$sp(final ClassTag evidence$5, final ClassTag evidence$6) {
      this(64, evidence$5, evidence$6);
   }

   public GraphXPrimitiveKeyOpenHashMap$mcJI$sp(final OpenHashSet keySet$mcJ$sp, final ClassTag evidence$7, final ClassTag evidence$8) {
      this(keySet$mcJ$sp, (int[])evidence$8.newArray(keySet$mcJ$sp.capacity()), evidence$7, evidence$8);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
