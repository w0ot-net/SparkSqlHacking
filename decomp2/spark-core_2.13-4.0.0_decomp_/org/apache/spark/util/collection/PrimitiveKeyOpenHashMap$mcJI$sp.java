package org.apache.spark.util.collection;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Predef;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

public class PrimitiveKeyOpenHashMap$mcJI$sp extends PrimitiveKeyOpenHashMap {
   public OpenHashSet _keySet$mcJ$sp;
   public int[] _values$mcI$sp;
   public int[] _oldValues$mcI$sp;

   public OpenHashSet _keySet$mcJ$sp() {
      return this._keySet$mcJ$sp;
   }

   public OpenHashSet _keySet() {
      return this._keySet$mcJ$sp();
   }

   public void _keySet$mcJ$sp_$eq(final OpenHashSet x$1) {
      this._keySet$mcJ$sp = x$1;
   }

   public void _keySet_$eq(final OpenHashSet x$1) {
      this._keySet$mcJ$sp_$eq(x$1);
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

   public boolean contains(final long k) {
      return this.contains$mcJ$sp(k);
   }

   public boolean contains$mcJ$sp(final long k) {
      return this._keySet().getPos$mcJ$sp(k) != OpenHashSet$.MODULE$.INVALID_POS();
   }

   public int apply(final long k) {
      return this.apply$mcJI$sp(k);
   }

   public int apply$mcJI$sp(final long k) {
      int pos = this._keySet().getPos$mcJ$sp(k);
      return this._values()[pos];
   }

   public int getOrElse(final long k, final int elseValue) {
      return this.getOrElse$mcJI$sp(k, elseValue);
   }

   public int getOrElse$mcJI$sp(final long k, final int elseValue) {
      int pos = this._keySet().getPos$mcJ$sp(k);
      return pos >= 0 ? this._values()[pos] : elseValue;
   }

   public void update(final long k, final int v) {
      this.update$mcJI$sp(k, v);
   }

   public void update$mcJI$sp(final long k, final int v) {
      int pos = this._keySet().addWithoutResize$mcJ$sp(k) & OpenHashSet$.MODULE$.POSITION_MASK();
      this._values()[pos] = v;
      this._keySet().rehashIfNeeded$mcJ$sp(k, (JFunction1.mcVI.sp)(newCapacity) -> this.org$apache$spark$util$collection$PrimitiveKeyOpenHashMap$$grow(newCapacity), (JFunction2.mcVII.sp)(oldPos, newPos) -> this.org$apache$spark$util$collection$PrimitiveKeyOpenHashMap$$move(oldPos, newPos));
      this._oldValues_$eq((int[])null);
   }

   public int changeValue(final long k, final Function0 defaultValue, final Function1 mergeValue) {
      return this.changeValue$mcJI$sp(k, defaultValue, mergeValue);
   }

   public int changeValue$mcJI$sp(final long k, final Function0 defaultValue, final Function1 mergeValue) {
      int pos = this._keySet().addWithoutResize$mcJ$sp(k);
      if ((pos & OpenHashSet$.MODULE$.NONEXISTENCE_MASK()) != 0) {
         int newValue = defaultValue.apply$mcI$sp();
         this._values()[pos & OpenHashSet$.MODULE$.POSITION_MASK()] = newValue;
         this._keySet().rehashIfNeeded$mcJ$sp(k, (JFunction1.mcVI.sp)(newCapacity) -> this.org$apache$spark$util$collection$PrimitiveKeyOpenHashMap$$grow(newCapacity), (JFunction2.mcVII.sp)(oldPos, newPos) -> this.org$apache$spark$util$collection$PrimitiveKeyOpenHashMap$$move(oldPos, newPos));
         return newValue;
      } else {
         this._values()[pos] = mergeValue.apply$mcII$sp(this._values()[pos]);
         return this._values()[pos];
      }
   }

   public boolean specInstance$() {
      return true;
   }

   public PrimitiveKeyOpenHashMap$mcJI$sp(final int initialCapacity, final ClassTag evidence$1, final ClassTag evidence$2) {
      boolean var7;
      Predef var10000;
      label25: {
         label27: {
            super(initialCapacity, evidence$1, evidence$2);
            var10000 = .MODULE$;
            ClassTag var10001 = scala.reflect.package..MODULE$.classTag(evidence$1);
            ClassTag var4 = scala.reflect.package..MODULE$.classTag(scala.reflect.ClassTag..MODULE$.Long());
            if (var10001 == null) {
               if (var4 == null) {
                  break label27;
               }
            } else if (var10001.equals(var4)) {
               break label27;
            }

            var10001 = scala.reflect.package..MODULE$.classTag(evidence$1);
            ClassTag var5 = scala.reflect.package..MODULE$.classTag(scala.reflect.ClassTag..MODULE$.Int());
            if (var10001 == null) {
               if (var5 == null) {
                  break label27;
               }
            } else if (var10001.equals(var5)) {
               break label27;
            }

            var7 = false;
            break label25;
         }

         var7 = true;
      }

      var10000.require(var7);
      this._keySet_$eq(new OpenHashSet(initialCapacity, evidence$1));
      this._values_$eq(evidence$2.newArray(this._keySet().capacity()));
      this._oldValues$mcI$sp = null;
   }

   public PrimitiveKeyOpenHashMap$mcJI$sp(final ClassTag evidence$3, final ClassTag evidence$4) {
      this(64, evidence$3, evidence$4);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
