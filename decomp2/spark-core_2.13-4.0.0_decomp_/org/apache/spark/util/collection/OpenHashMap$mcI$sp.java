package org.apache.spark.util.collection;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

public class OpenHashMap$mcI$sp extends OpenHashMap {
   public int[] _values$mcI$sp;
   public transient int[] _oldValues$mcI$sp;
   public int nullValue$mcI$sp;

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

   public int nullValue$mcI$sp() {
      return this.nullValue$mcI$sp;
   }

   public int nullValue() {
      return this.nullValue$mcI$sp();
   }

   public void nullValue$mcI$sp_$eq(final int x$1) {
      this.nullValue$mcI$sp = x$1;
   }

   public void nullValue_$eq(final int x$1) {
      this.nullValue$mcI$sp_$eq(x$1);
   }

   public int apply(final Object k) {
      return this.apply$mcI$sp(k);
   }

   public int apply$mcI$sp(final Object k) {
      if (k == null) {
         return this.nullValue();
      } else {
         int pos = this._keySet().getPos(k);
         return pos < 0 ? BoxesRunTime.unboxToInt((Object)null) : this._values()[pos];
      }
   }

   public void update(final Object k, final int v) {
      this.update$mcI$sp(k, v);
   }

   public void update$mcI$sp(final Object k, final int v) {
      if (k == null) {
         this.org$apache$spark$util$collection$OpenHashMap$$haveNullValue_$eq(true);
         this.nullValue_$eq(v);
      } else {
         int pos = this._keySet().addWithoutResize(k) & OpenHashSet$.MODULE$.POSITION_MASK();
         this._values()[pos] = v;
         this._keySet().rehashIfNeeded(k, (JFunction1.mcVI.sp)(newCapacity) -> this.org$apache$spark$util$collection$OpenHashMap$$grow(newCapacity), (JFunction2.mcVII.sp)(oldPos, newPos) -> this.org$apache$spark$util$collection$OpenHashMap$$move(oldPos, newPos));
         this._oldValues_$eq((int[])null);
      }
   }

   public int changeValue(final Object k, final Function0 defaultValue, final Function1 mergeValue) {
      return this.changeValue$mcI$sp(k, defaultValue, mergeValue);
   }

   public int changeValue$mcI$sp(final Object k, final Function0 defaultValue, final Function1 mergeValue) {
      if (k == null) {
         if (this.org$apache$spark$util$collection$OpenHashMap$$haveNullValue()) {
            this.nullValue_$eq(mergeValue.apply$mcII$sp(this.nullValue()));
         } else {
            this.org$apache$spark$util$collection$OpenHashMap$$haveNullValue_$eq(true);
            this.nullValue_$eq(defaultValue.apply$mcI$sp());
         }

         return this.nullValue();
      } else {
         int pos = this._keySet().addWithoutResize(k);
         if ((pos & OpenHashSet$.MODULE$.NONEXISTENCE_MASK()) != 0) {
            int newValue = defaultValue.apply$mcI$sp();
            this._values()[pos & OpenHashSet$.MODULE$.POSITION_MASK()] = newValue;
            this._keySet().rehashIfNeeded(k, (JFunction1.mcVI.sp)(newCapacity) -> this.org$apache$spark$util$collection$OpenHashMap$$grow(newCapacity), (JFunction2.mcVII.sp)(oldPos, newPos) -> this.org$apache$spark$util$collection$OpenHashMap$$move(oldPos, newPos));
            return newValue;
         } else {
            this._values()[pos] = mergeValue.apply$mcII$sp(this._values()[pos]);
            return this._values()[pos];
         }
      }
   }

   public boolean specInstance$() {
      return true;
   }

   public OpenHashMap$mcI$sp(final int initialCapacity, final ClassTag evidence$1, final ClassTag evidence$2) {
      super(initialCapacity, evidence$1, evidence$2);
      this.org$apache$spark$util$collection$OpenHashMap$$_keySet = new OpenHashSet(initialCapacity, evidence$1);
      this._values_$eq(evidence$2.newArray(this._keySet().capacity()));
      this._oldValues$mcI$sp = null;
      this.org$apache$spark$util$collection$OpenHashMap$$haveNullValue = false;
      this.nullValue$mcI$sp = BoxesRunTime.unboxToInt((Object)null);
   }

   public OpenHashMap$mcI$sp(final ClassTag evidence$3, final ClassTag evidence$4) {
      this(64, evidence$3, evidence$4);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
