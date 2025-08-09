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

public class GraphXPrimitiveKeyOpenHashMap$mcJD$sp extends GraphXPrimitiveKeyOpenHashMap {
   public final OpenHashSet keySet$mcJ$sp;
   public double[] _values$mcD$sp;
   public double[] _oldValues$mcD$sp;

   public OpenHashSet keySet$mcJ$sp() {
      return this.keySet$mcJ$sp;
   }

   public OpenHashSet keySet() {
      return this.keySet$mcJ$sp();
   }

   public double[] _values$mcD$sp() {
      return this._values$mcD$sp;
   }

   public double[] _values() {
      return this._values$mcD$sp();
   }

   public void _values$mcD$sp_$eq(final double[] x$1) {
      this._values$mcD$sp = x$1;
   }

   public void _values_$eq(final double[] x$1) {
      this._values$mcD$sp_$eq(x$1);
   }

   public double[] _oldValues$mcD$sp() {
      return this._oldValues$mcD$sp;
   }

   public double[] _oldValues() {
      return this._oldValues$mcD$sp();
   }

   public void _oldValues$mcD$sp_$eq(final double[] x$1) {
      this._oldValues$mcD$sp = x$1;
   }

   public void _oldValues_$eq(final double[] x$1) {
      this._oldValues$mcD$sp_$eq(x$1);
   }

   public double apply(final long k) {
      return this.apply$mcJD$sp(k);
   }

   public double apply$mcJD$sp(final long k) {
      int pos = this.keySet().getPos$mcJ$sp(k);
      return this._values()[pos];
   }

   public double getOrElse(final long k, final double elseValue) {
      return this.getOrElse$mcJD$sp(k, elseValue);
   }

   public double getOrElse$mcJD$sp(final long k, final double elseValue) {
      int pos = this.keySet().getPos$mcJ$sp(k);
      return pos >= 0 ? this._values()[pos] : elseValue;
   }

   public void update(final long k, final double v) {
      this.update$mcJD$sp(k, v);
   }

   public void update$mcJD$sp(final long k, final double v) {
      int pos = this.keySet().addWithoutResize$mcJ$sp(k) & .MODULE$.POSITION_MASK();
      this._values()[pos] = v;
      this.keySet().rehashIfNeeded$mcJ$sp(k, (JFunction1.mcVI.sp)(newCapacity) -> this.org$apache$spark$graphx$util$collection$GraphXPrimitiveKeyOpenHashMap$$grow(newCapacity), (JFunction2.mcVII.sp)(oldPos, newPos) -> this.org$apache$spark$graphx$util$collection$GraphXPrimitiveKeyOpenHashMap$$move(oldPos, newPos));
      this._oldValues_$eq((double[])null);
   }

   public void setMerge(final long k, final double v, final Function2 mergeF) {
      this.setMerge$mcJD$sp(k, v, mergeF);
   }

   public void setMerge$mcJD$sp(final long k, final double v, final Function2 mergeF) {
      int pos = this.keySet().addWithoutResize$mcJ$sp(k);
      int ind = pos & .MODULE$.POSITION_MASK();
      if ((pos & .MODULE$.NONEXISTENCE_MASK()) != 0) {
         this._values()[ind] = v;
      } else {
         this._values()[ind] = mergeF.apply$mcDDD$sp(this._values()[ind], v);
      }

      this.keySet().rehashIfNeeded$mcJ$sp(k, (JFunction1.mcVI.sp)(newCapacity) -> this.org$apache$spark$graphx$util$collection$GraphXPrimitiveKeyOpenHashMap$$grow(newCapacity), (JFunction2.mcVII.sp)(oldPos, newPos) -> this.org$apache$spark$graphx$util$collection$GraphXPrimitiveKeyOpenHashMap$$move(oldPos, newPos));
      this._oldValues_$eq((double[])null);
   }

   public double changeValue(final long k, final Function0 defaultValue, final Function1 mergeValue) {
      return this.changeValue$mcJD$sp(k, defaultValue, mergeValue);
   }

   public double changeValue$mcJD$sp(final long k, final Function0 defaultValue, final Function1 mergeValue) {
      int pos = this.keySet().addWithoutResize$mcJ$sp(k);
      if ((pos & .MODULE$.NONEXISTENCE_MASK()) != 0) {
         double newValue = defaultValue.apply$mcD$sp();
         this._values()[pos & .MODULE$.POSITION_MASK()] = newValue;
         this.keySet().rehashIfNeeded$mcJ$sp(k, (JFunction1.mcVI.sp)(newCapacity) -> this.org$apache$spark$graphx$util$collection$GraphXPrimitiveKeyOpenHashMap$$grow(newCapacity), (JFunction2.mcVII.sp)(oldPos, newPos) -> this.org$apache$spark$graphx$util$collection$GraphXPrimitiveKeyOpenHashMap$$move(oldPos, newPos));
         return newValue;
      } else {
         this._values()[pos] = mergeValue.apply$mcDD$sp(this._values()[pos]);
         return this._values()[pos];
      }
   }

   public boolean specInstance$() {
      return true;
   }

   public GraphXPrimitiveKeyOpenHashMap$mcJD$sp(final OpenHashSet keySet$mcJ$sp, final double[] _values$mcD$sp, final ClassTag evidence$1, final ClassTag evidence$2) {
      this.keySet$mcJ$sp = keySet$mcJ$sp;
      this._values$mcD$sp = _values$mcD$sp;
      super((OpenHashSet)null, (Object)null, evidence$1, evidence$2);
      this._oldValues$mcD$sp = null;
   }

   public GraphXPrimitiveKeyOpenHashMap$mcJD$sp(final int initialCapacity, final ClassTag evidence$3, final ClassTag evidence$4) {
      this(new OpenHashSet(initialCapacity, evidence$3), (double[])evidence$4.newArray(initialCapacity), evidence$3, evidence$4);
   }

   public GraphXPrimitiveKeyOpenHashMap$mcJD$sp(final ClassTag evidence$5, final ClassTag evidence$6) {
      this(64, evidence$5, evidence$6);
   }

   public GraphXPrimitiveKeyOpenHashMap$mcJD$sp(final OpenHashSet keySet$mcJ$sp, final ClassTag evidence$7, final ClassTag evidence$8) {
      this(keySet$mcJ$sp, (double[])evidence$8.newArray(keySet$mcJ$sp.capacity()), evidence$7, evidence$8);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
