package org.apache.spark.util.collection;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

public class OpenHashMap$mcD$sp extends OpenHashMap {
   public double[] _values$mcD$sp;
   public transient double[] _oldValues$mcD$sp;
   public double nullValue$mcD$sp;

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

   public double nullValue$mcD$sp() {
      return this.nullValue$mcD$sp;
   }

   public double nullValue() {
      return this.nullValue$mcD$sp();
   }

   public void nullValue$mcD$sp_$eq(final double x$1) {
      this.nullValue$mcD$sp = x$1;
   }

   public void nullValue_$eq(final double x$1) {
      this.nullValue$mcD$sp_$eq(x$1);
   }

   public double apply(final Object k) {
      return this.apply$mcD$sp(k);
   }

   public double apply$mcD$sp(final Object k) {
      if (k == null) {
         return this.nullValue();
      } else {
         int pos = this._keySet().getPos(k);
         return pos < 0 ? BoxesRunTime.unboxToDouble((Object)null) : this._values()[pos];
      }
   }

   public void update(final Object k, final double v) {
      this.update$mcD$sp(k, v);
   }

   public void update$mcD$sp(final Object k, final double v) {
      if (k == null) {
         this.org$apache$spark$util$collection$OpenHashMap$$haveNullValue_$eq(true);
         this.nullValue_$eq(v);
      } else {
         int pos = this._keySet().addWithoutResize(k) & OpenHashSet$.MODULE$.POSITION_MASK();
         this._values()[pos] = v;
         this._keySet().rehashIfNeeded(k, (JFunction1.mcVI.sp)(newCapacity) -> this.org$apache$spark$util$collection$OpenHashMap$$grow(newCapacity), (JFunction2.mcVII.sp)(oldPos, newPos) -> this.org$apache$spark$util$collection$OpenHashMap$$move(oldPos, newPos));
         this._oldValues_$eq((double[])null);
      }
   }

   public double changeValue(final Object k, final Function0 defaultValue, final Function1 mergeValue) {
      return this.changeValue$mcD$sp(k, defaultValue, mergeValue);
   }

   public double changeValue$mcD$sp(final Object k, final Function0 defaultValue, final Function1 mergeValue) {
      if (k == null) {
         if (this.org$apache$spark$util$collection$OpenHashMap$$haveNullValue()) {
            this.nullValue_$eq(mergeValue.apply$mcDD$sp(this.nullValue()));
         } else {
            this.org$apache$spark$util$collection$OpenHashMap$$haveNullValue_$eq(true);
            this.nullValue_$eq(defaultValue.apply$mcD$sp());
         }

         return this.nullValue();
      } else {
         int pos = this._keySet().addWithoutResize(k);
         if ((pos & OpenHashSet$.MODULE$.NONEXISTENCE_MASK()) != 0) {
            double newValue = defaultValue.apply$mcD$sp();
            this._values()[pos & OpenHashSet$.MODULE$.POSITION_MASK()] = newValue;
            this._keySet().rehashIfNeeded(k, (JFunction1.mcVI.sp)(newCapacity) -> this.org$apache$spark$util$collection$OpenHashMap$$grow(newCapacity), (JFunction2.mcVII.sp)(oldPos, newPos) -> this.org$apache$spark$util$collection$OpenHashMap$$move(oldPos, newPos));
            return newValue;
         } else {
            this._values()[pos] = mergeValue.apply$mcDD$sp(this._values()[pos]);
            return this._values()[pos];
         }
      }
   }

   public boolean specInstance$() {
      return true;
   }

   public OpenHashMap$mcD$sp(final int initialCapacity, final ClassTag evidence$1, final ClassTag evidence$2) {
      super(initialCapacity, evidence$1, evidence$2);
      this.org$apache$spark$util$collection$OpenHashMap$$_keySet = new OpenHashSet(initialCapacity, evidence$1);
      this._values_$eq(evidence$2.newArray(this._keySet().capacity()));
      this._oldValues$mcD$sp = null;
      this.org$apache$spark$util$collection$OpenHashMap$$haveNullValue = false;
      this.nullValue$mcD$sp = BoxesRunTime.unboxToDouble((Object)null);
   }

   public OpenHashMap$mcD$sp(final ClassTag evidence$3, final ClassTag evidence$4) {
      this(64, evidence$3, evidence$4);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
