package org.apache.spark.util.collection;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

public class OpenHashMap$mcJ$sp extends OpenHashMap {
   public long[] _values$mcJ$sp;
   public transient long[] _oldValues$mcJ$sp;
   public long nullValue$mcJ$sp;

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

   public long nullValue$mcJ$sp() {
      return this.nullValue$mcJ$sp;
   }

   public long nullValue() {
      return this.nullValue$mcJ$sp();
   }

   public void nullValue$mcJ$sp_$eq(final long x$1) {
      this.nullValue$mcJ$sp = x$1;
   }

   public void nullValue_$eq(final long x$1) {
      this.nullValue$mcJ$sp_$eq(x$1);
   }

   public long apply(final Object k) {
      return this.apply$mcJ$sp(k);
   }

   public long apply$mcJ$sp(final Object k) {
      if (k == null) {
         return this.nullValue();
      } else {
         int pos = this._keySet().getPos(k);
         return pos < 0 ? BoxesRunTime.unboxToLong((Object)null) : this._values()[pos];
      }
   }

   public void update(final Object k, final long v) {
      this.update$mcJ$sp(k, v);
   }

   public void update$mcJ$sp(final Object k, final long v) {
      if (k == null) {
         this.org$apache$spark$util$collection$OpenHashMap$$haveNullValue_$eq(true);
         this.nullValue_$eq(v);
      } else {
         int pos = this._keySet().addWithoutResize(k) & OpenHashSet$.MODULE$.POSITION_MASK();
         this._values()[pos] = v;
         this._keySet().rehashIfNeeded(k, (JFunction1.mcVI.sp)(newCapacity) -> this.org$apache$spark$util$collection$OpenHashMap$$grow(newCapacity), (JFunction2.mcVII.sp)(oldPos, newPos) -> this.org$apache$spark$util$collection$OpenHashMap$$move(oldPos, newPos));
         this._oldValues_$eq((long[])null);
      }
   }

   public long changeValue(final Object k, final Function0 defaultValue, final Function1 mergeValue) {
      return this.changeValue$mcJ$sp(k, defaultValue, mergeValue);
   }

   public long changeValue$mcJ$sp(final Object k, final Function0 defaultValue, final Function1 mergeValue) {
      if (k == null) {
         if (this.org$apache$spark$util$collection$OpenHashMap$$haveNullValue()) {
            this.nullValue_$eq(mergeValue.apply$mcJJ$sp(this.nullValue()));
         } else {
            this.org$apache$spark$util$collection$OpenHashMap$$haveNullValue_$eq(true);
            this.nullValue_$eq(defaultValue.apply$mcJ$sp());
         }

         return this.nullValue();
      } else {
         int pos = this._keySet().addWithoutResize(k);
         if ((pos & OpenHashSet$.MODULE$.NONEXISTENCE_MASK()) != 0) {
            long newValue = defaultValue.apply$mcJ$sp();
            this._values()[pos & OpenHashSet$.MODULE$.POSITION_MASK()] = newValue;
            this._keySet().rehashIfNeeded(k, (JFunction1.mcVI.sp)(newCapacity) -> this.org$apache$spark$util$collection$OpenHashMap$$grow(newCapacity), (JFunction2.mcVII.sp)(oldPos, newPos) -> this.org$apache$spark$util$collection$OpenHashMap$$move(oldPos, newPos));
            return newValue;
         } else {
            this._values()[pos] = mergeValue.apply$mcJJ$sp(this._values()[pos]);
            return this._values()[pos];
         }
      }
   }

   public boolean specInstance$() {
      return true;
   }

   public OpenHashMap$mcJ$sp(final int initialCapacity, final ClassTag evidence$1, final ClassTag evidence$2) {
      super(initialCapacity, evidence$1, evidence$2);
      this.org$apache$spark$util$collection$OpenHashMap$$_keySet = new OpenHashSet(initialCapacity, evidence$1);
      this._values_$eq(evidence$2.newArray(this._keySet().capacity()));
      this._oldValues$mcJ$sp = null;
      this.org$apache$spark$util$collection$OpenHashMap$$haveNullValue = false;
      this.nullValue$mcJ$sp = BoxesRunTime.unboxToLong((Object)null);
   }

   public OpenHashMap$mcJ$sp(final ClassTag evidence$3, final ClassTag evidence$4) {
      this(64, evidence$3, evidence$4);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
